/**
 * Copyright 2012
 */
package assign3.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import assign3.test.PostTest;
import assign3.test.PreTest;

/**
 * Provides basic test functionality to test classes
 * 
 * @author Michael Anderson and Alonzo Rose
 */
public abstract class TestBase {
	private List<Method> lastPassedTestMethods;
	private List<Method> lastFailedTestMethods;
	private List<Method> foundTestMethods;
	private List<Method> preTestMethods;
	private List<Method> postTestMethods;
	
	public TestBase() {
		this.foundTestMethods = new ArrayList<Method>();
		this.preTestMethods = new ArrayList<Method>();
		this.postTestMethods = new ArrayList<Method>();

		// Collect all of the test methods for this class
		Method methods[] = this.getClass().getMethods();
		for (Method each : methods) {
			boolean isPreTest = each.isAnnotationPresent(PreTest.class);
			boolean isPostTest = each.isAnnotationPresent(PostTest.class);
			
			if (each.getName().indexOf("test") == 0 &&
			    !isPreTest && !isPostTest) {
				this.foundTestMethods.add(each);
			}
			else if (isPreTest) {
				preTestMethods.add(each);
			}
			else if (isPostTest) {
				postTestMethods.add(each);
			}
		}
	}
	
	/**
	 * Invokes all methods in this instance named with the 'test' prefix and outputs test information.
	 */
	public void runTests() {
		runTests(this.foundTestMethods);
	}
	
	/**
	 * Invokes test methods that failed in the last run and outputs test information.
	 */
	public void runLastFailedTests() {
		runTests(this.lastFailedTestMethods);
	}
	
	/**
	 * Retrieves a list of strings of the last failing test methods
	 * 
	 * @return a list of strings -- with each string containing the name of a failed test method
	 */
	public List<String> getLastFailedTestMethods() {
		List<String> result = new ArrayList<String>();
		
		for (Method each : this.lastFailedTestMethods) {
			result.add(each.getName());
		}
		
		return result;
	}
	
	/**
	 * Runs the given methods as tests -- printing information on each test method invoked
	 * 
	 * @param testMethods a List of methods to invoke 
	 */
	private void runTests(List<Method> testMethods) {
		this.lastPassedTestMethods = new ArrayList<Method>();
		this.lastFailedTestMethods = new ArrayList<Method>();

		System.out.println("Running " + testMethods.size() + " tests in class " + this.getClass().getName());
		System.out.println("---------------------------------------------");
		System.out.println();

		// Invoke all test methods
		int currentTestIndex = 0;
		for (Method each : testMethods) {
			currentTestIndex++;
			try {
				System.out.print("Running Test #" + currentTestIndex + ": " + each.getName());
				runTestSetup();
				each.invoke(this);
				runTestTeardown();
				// Track passed methods
				lastPassedTestMethods.add(each);
				System.out.println(" -- Passed");
			}
			catch (InvocationTargetException e) {
				// Track failed methods
				lastFailedTestMethods.add(each);
				System.out.flush();
				System.out.println(" -- Failed");
				System.err.println("Attempt to run test " + each.getName() + " resulted in an exception!");
				
				// Since this was caused by an invocation, there was an underlying exception that occurred. 
				Exception cause = (Exception) e.getCause();
				if (cause != null) {
					cause.printStackTrace();
				}
				System.err.flush();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("Tests Passed: " + lastPassedTestMethods.size());
		System.out.println("Tests Failed: " + lastFailedTestMethods.size());
	}
	
	/**
	 * Runs any methods with the @PreTest annotation.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void runTestSetup() throws InvocationTargetException, IllegalAccessException {
		for (Method each : this.preTestMethods) {
			each.invoke(this);
		}
	}
	
	/**
	 * Runs any methods with the @PostTest annotation.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void runTestTeardown() throws InvocationTargetException, IllegalAccessException {
		for (Method each : this.postTestMethods) {
			each.invoke(this);
		}
	}

}
