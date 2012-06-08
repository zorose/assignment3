/**
 * 
 */
package assign3.test;

/**
 * Represents test generated exceptions
 * 
 * @author Michael Anderson and Alonzo Rose
 */
public class TestException extends RuntimeException {
	
	private static final String EXCEPTION_PREFIX = "Test Exception: ";

	private static final long serialVersionUID = 1L;

	/**
	 * Customizes the exception message
	 * 
	 * @param message A description of the issue
	 */
	public TestException(String message) {
		super(EXCEPTION_PREFIX + message);
	}

}
