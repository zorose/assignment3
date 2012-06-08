package assign3.test;

/**
 * Provides Assertion methods to facilitate testing
 * 
 * @author Michael Anderson and Alonzo Rose
 */
public abstract class TestUtil {
	
	public static void assertEquals(Object actual, Object expected) {
		if (!actual.equals(expected)) {
			throw new TestException("Expected " + expected + ", but was " + actual + "!");
		}
	}
	
	public static void assertNotEquals(Object actual, Object expected) {
		if (actual.equals(expected)) {
			throw new TestException("Expected objects to not be equal!");
		}
	}
	
	public static void assertTrue(Boolean actual) {
		if (!actual) {
			throw new TestException("Expected true, but was false!");
		}
	}
	
	public static void assertFalse(Boolean actual) {
		if (actual) {
			throw new TestException("Expected true, but was false!");
		}
	}
	
	public static void assertNull(Object actual) {
		if (actual != null) {
			throw new TestException("Expected null, but was " + actual + "!");
		}
	}
	
	public static void assertNotNull(Object actual) {
		if (actual == null) {
			throw new TestException("Expected an instance, but was null!");
		}
	}
	
	public static void fail(String message) {
		throw new TestException(message);
	}
}
