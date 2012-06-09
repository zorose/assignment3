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
	
	// END testAreAnagrams
	
	/**
	 * Test different run times of each sorting method
	 */
	public void testRunTime(){
		
	}
}
