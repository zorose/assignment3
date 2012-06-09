package assign3;

import assign3.AnagramUtil.SortMethod;
import assign3.test.*;

/**
 * 
 * @author Alonzo Rose & Michael Anderson
 *
 */
public class TestAnagramUtil extends TestBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestAnagramUtil().runTests();
	}
	
	@PreTest
	public void setup() {
		// Test setup goes here
	}
	
	@PostTest
	public void teardown() {
		// Test teardown goes here
	}
	
	// BEGIN testSortCharacters
	
	public void testSortCharacters_Insertion(){
		// Insertion
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		testSortCharacters();
	}
	
	public void testSortCharacters_Selection() {
		// Selection sort
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		testSortCharacters();
	}
	
	private void testSortCharacters() {
		String input 	= "Stephen";
		String result 	= AnagramUtil.sort(input);
		String expected = "Seehnpt";
		TestUtil.assertEquals(result, expected);
	}
	
	public void testSortCharacters_Parameters() {
		// Test exception
		try {
			AnagramUtil.sort((String)null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
	}
	
	// END testSortCharacters
	
	// BEGIN testSortStrings
	
	public void testSortStrings_Insertion() {
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		testSortStrings();
	}
	
	public void testSortStrings_Selection() {
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		testSortStrings();
	}
	
	private void testSortStrings(){
		String[] input = new String[]{"carets", "conservationalists", "caters", "caster", "conversationalists"};
		String[] expected = new String[]{"conservationalists", "conversationalists", "carets", "caters", "caster"};
		
		String[] results = AnagramUtil.sort(input);
		TestUtil.assertEquals(results, expected);
	}
	
	public void testSortStrings_Parameters() {
		// Test exception
		try {
			AnagramUtil.sort((String[])null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
	}
	
	// END testSortStrings

	// BEGIN testGetLargestAnagramGropu
	
	public void testGetLargestAnagramGroup_Insertion() {
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		testGetLargestAnagramGroup();
	}
	
	public void testGetLargestAnagramGroup_Selection() {
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		testGetLargestAnagramGroup();
	}
	
	private void testGetLargestAnagramGroup(){
		//Test getLargestAnagramGroup method w/File name:
		String[] results 	= AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] expected 	= new String[]{"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
		//Test if results contains expected
		int count = 0;
		for(String s1 : expected)
			for(String s2 : results)
				if(s1.equals(s2))
					count ++;
		TestUtil.assertEquals(count, expected.length, "Results did not contain all that was expected!");
		
		//Test getLargestAnagramGroup method w/String[]:
		String[] input	= new String[]{"hydroxydeoxycorticosterones", "hydroxydesoxycorticosterone", "not_an_anagram"};
		expected 		= new String[]{"hydroxydeoxycorticosterones", "hydroxydesoxycorticosterone"};
		results = AnagramUtil.getLargestAnagramGroup(input);
		
		// Test no anagrams present
		input = new String[]{"There", "are", "no", "anagrams", "here"};
		results = AnagramUtil.getLargestAnagramGroup(input);
		TestUtil.assertEquals(results.length, 0);
	}
	
	public void testGetLargestAnagramGroup_Parameters() {
		try {
			AnagramUtil.getLargestAnagramGroup((String)null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
		
		try {
			AnagramUtil.getLargestAnagramGroup("nonexistent.txt");
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
		
		try {
			AnagramUtil.getLargestAnagramGroup(new String[0]);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
		
		try {
			AnagramUtil.getLargestAnagramGroup((String[])null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}
	}
	
	// END testGetLargestAnagramGroup
	
	// START testAreAnagrams
	
	public void testAreAnagrams_Insertion() {
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		testAreAnagrams();
	}
	
	public void testAreAnagrams_Selection() {
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		testAreAnagrams();
	}
	
	private void testAreAnagrams() {
		// Same case
		TestUtil.assertTrue(AnagramUtil.areAnagrams("carets", "caters"));
		TestUtil.assertTrue(AnagramUtil.areAnagrams("caters", "carets"));
		
		// Mixed case
		TestUtil.assertTrue(AnagramUtil.areAnagrams("carets", "CaTeRs"));
		TestUtil.assertTrue(AnagramUtil.areAnagrams("CaReTs", "caters"));
		
		// Not anagrams
		TestUtil.assertFalse(AnagramUtil.areAnagrams("Last", "Lest"));
		TestUtil.assertFalse(AnagramUtil.areAnagrams("carets", "carters"));
	}
	
	public void testAreAnagrams_Parameters() {
		try {
			AnagramUtil.areAnagrams("Anagram", null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			AnagramUtil.areAnagrams(null, "Anagram");
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			AnagramUtil.areAnagrams(null, null);
			TestUtil.fail("Should have thrown an exception!");
		}
		catch (IllegalArgumentException e) {
			// Expected
		}

	}
	
	// END testAreAnagrams
	
	/**
	 * Test different run times of each sorting method
	 */
	public void testRunTime(){
		//Set variables
		String[] wordList 	 = new String[10];
		long	 cycles		 = 10000;
		long	 maxCycles	 = 100000;
		long 	 start;
		long 	 mid;
		long 	 end;
		/**
		 * Generates two random strings and tests for anagrams n number of times using INSERTION
		 */
		System.out.println("\n" + "Test areAnagrams INSERTION:");
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		while(cycles < maxCycles){
			String s1 = (int)(Math.random() * ((99999999) + 1)) + "";
			String s2 = (int)(Math.random() * ((99999999) + 1)) + "";
			
			//Spin down
			start = System.nanoTime();
			while (System.nanoTime() - start < 1000000000) { }
			
			//Set Start time
			start = System.nanoTime();
			
			//Test
			for (long i = 0; i < cycles; i ++){
				AnagramUtil.areAnagrams(s1, s2);
			}
			//Set mid time
			mid = System.nanoTime();
			
			//Get time it took to run the loop
			for (long i = 0; i < cycles; i ++) { }
			
			//Set stop time
			end = System.nanoTime();
			
			//Calculate average run time
			double averageTime = ((mid - start) - (end - mid)) / cycles;
			System.out.print("\n" + averageTime + "  nanoseconds " + "n = : " + cycles);
			cycles += 500;
		}
		
		/**
		 * Generates two random strings and tests for anagrams n number of times using SELECTION
		 */
		cycles = 10000;
		System.out.println("\n" +  "Test areAnagrams SELECTION:");
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		while(cycles < maxCycles){
			String s1 = (int)(Math.random() * ((99999999) + 1)) + "";
			String s2 = (int)(Math.random() * ((99999999) + 1)) + "";
			
			//Spin down
			start = System.nanoTime();
			while (System.nanoTime() - start < 1000000000) { }
			
			//Set Start time
			start = System.nanoTime();
			
			//Test
			for (long i = 0; i < cycles; i ++){
				AnagramUtil.areAnagrams(s1, s2);
			}
			//Set mid time
			mid = System.nanoTime();
			
			//Get time it took to run the loop
			for (long i = 0; i < cycles; i ++) { }
			
			//Set stop time
			end = System.nanoTime();
			
			//Calculate average run time
			double averageTime = ((mid - start) - (end - mid)) / cycles;
			System.out.print("\n" + averageTime + "  nanoseconds " + "n = : " + cycles);
			cycles += 500;
		}
		
		//Create a 50,000 element array and fills it with random strings, some anagrams and some not
		for(int i = 0; i < wordList.length; i ++){
			wordList[i] = (int)(Math.random() * ((99999999) + 1)) + "";
		}
		
		/**
		 * Tests getLargestAnagramGroup using INSERTION n number of times.
		 */
		cycles = 10000;
		System.out.println("\n" +  "Test getLargestAnagramGroup INSERTION:");
		AnagramUtil.setSortMethod(SortMethod.INSERTION);
		while(cycles < maxCycles){
			
			//Spin down
			start = System.nanoTime();
			while (System.nanoTime() - start < 1000000000) { }
			
			//Set Start time
			start = System.nanoTime();
			
			//Test
			for (int i = 0; i < cycles; i ++){
				AnagramUtil.getLargestAnagramGroup(wordList);
			}
			//Set mid time
			mid = System.nanoTime();
			
			//Get time it took to run the loop
			for (int i = 0; i < cycles; i ++) { }
			
			//Set stop time
			end = System.nanoTime();
			
			//Calculate average run time
			double averageTime = ((mid - start) - (end - mid)) / cycles;
			System.out.print("\n" + averageTime + "  nanoseconds " + "n = : " + cycles);
			cycles += 500;
		}
		
		/**
		 * Tests getLargestAnagramGroup using SELECTION n number of times.
		 */
		cycles = 10000;
		System.out.println("\n" +  "Test getLargestAnagramGroup SELECTION:");
		AnagramUtil.setSortMethod(SortMethod.SELECTION);
		while(cycles < maxCycles){
			
			//Spin down
			start = System.nanoTime();
			while (System.nanoTime() - start < 1000000000) { }
			
			//Set Start time
			start = System.nanoTime();
			
			//Test
			for (int i = 0; i < cycles; i ++){
				AnagramUtil.getLargestAnagramGroup(wordList);
			}
			//Set mid time
			mid = System.nanoTime();
			
			//Get time it took to run the loop
			for (int i = 0; i < cycles; i ++) { }
			
			//Set stop time
			end = System.nanoTime();
			
			//Calculate average run time
			double averageTime = ((mid - start) - (end - mid)) / cycles;
			System.out.print("\n" + averageTime + "  nanoseconds " + "n = : " + cycles);
			cycles += 500;
		}
	}
}
