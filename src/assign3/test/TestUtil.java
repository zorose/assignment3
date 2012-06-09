package assign3.test;

/**
 * Provides Assertion methods to facilitate testing
 * 
 * @author Michael Anderson and Alonzo Rose
 */
public abstract class TestUtil {
	
	public static void assertEquals(Object actual, Object expected) {
		assertEquals(actual, expected, "");
	}
	
	public static void assertEquals(Object actual, Object expected, String message) {
		if (!actual.equals(expected)) {
			throw new TestException("Expected " + expected + ", but was " + actual + "! " + message);
		}
	}
	
	public static <T> void assertEquals(T[] actual, T[] expected) {
		assertEquals(actual, expected, "");
	}
	
	public static <T> void assertEquals(T[] actual, T[] expected, String message) {
		if (actual.length != expected.length) {
			throw new TestException("Array expected to be of length " +
					expected.length + ", but was " + actual.length + "! " + message);
		}
		
		for (int i = 0; i < expected.length; i++) {
			message = "<Array index [" + i + "]> " + message;
			assertEquals(actual[i], expected[i], message);
		}
	}
	
	public static void assertNotEquals(Object actual, Object expected) {
		assertNotEquals(actual, expected, "");
	}
	
	public static void assertNotEquals(Object actual, Object expected, String message) {
		if (actual.equals(expected)) {
			throw new TestException("Expected objects to not be equal! " + message);
		}
	}
	
	public static void assertTrue(Boolean actual) {
		assertTrue(actual, "");
	}
	
	public static void assertTrue(Boolean actual, String message) {
		if (!actual) {
			throw new TestException("Expected true, but was false! " + message);
		}
	}
	
	public static void assertFalse(Boolean actual) {
		assertFalse(actual, "");
	}
	
	public static void assertFalse(Boolean actual, String message) {
		if (actual) {
			throw new TestException("Expected true, but was false! " + message);
		}
	}
	
	public static void assertNull(Object actual) {
		assertNull(actual, "");
	}
	
	public static void assertNull(Object actual, String message) {
		if (actual != null) {
			throw new TestException("Expected null, but was " + actual + "! " + message);
		}
	}
	
	public static void assertNotNull(Object actual) {
		assertNotNull(actual, "");
	}
	
	public static void assertNotNull(Object actual, String message) {
		if (actual == null) {
			throw new TestException("Expected an instance, but was null! " + message);
		}
	}
	
	public static void fail() {
		fail("");
	}
	
	public static void fail(String message) {
		throw new TestException(message);
	}
}
