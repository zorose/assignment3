package assign3;

import assign3.test.TestBase;
import assign3.test.TestUtil;

public class TestAnagramUtil extends TestBase {

	/**
	 * @author Alonzo Rose & Michael Anderson
	 * @param args
	 */
	public static void main(String[] args) {
		new TestAnagramUtil().runTests();
	}
	
	//TODO Use both selection and insertion sorting methods
	
	public void testSort(){
		String input 	= "Stephen";
		String result 	= AnagramUtil.sort(input);
		String expected = "Seehnpt";
		TestUtil.assertEquals(result, expected);
	}
	
	public void testComparator(){
		String[] input = new String[]{"z", "a", "f"};
		String[] expected = new String[]{"a", "f", "z"};
		String[] results = AnagramUtil.sort(input);
		
		for(int i = 0; i < results.length; i ++){
			TestUtil.assertEquals(results[i], expected[i]);
		}
		
	}
	
	public void testGetLargestAnagramGroup(){
		//Test getLargestAnagramGroup method w/File name:
		String[] results 	= AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] expected 	= new String[]{"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
		//Test if results contains expected
		int count = 0;
		for(String s1 : expected)
			for(String s2 : results)
				if(s1.equals(s2))
					count ++;
		TestUtil.assertEquals(count, expected.length);
		
		
		
		//Test getLargestAnagramGroup method w/String[]:
		String[] input	= new String[]{"hydroxydeoxycorticosterones", "hydroxydesoxycorticosterone", "not_an_anagram"};
		expected 		= new String[]{"hydroxydeoxycorticosterones", "hydroxydesoxycorticosterone"};
		results = AnagramUtil.getLargestAnagramGroup(input);
		
		//Sort arrays for testing
		results  = AnagramUtil.sort(results);
		expected = AnagramUtil.sort(expected);
		
		for(int i = 0; i < expected.length; i ++)
			TestUtil.assertEquals(results[i], expected[i]);
	}
	
	/**
	 * Test different run times of each sorting method
	 */
	public void testRunTime(){
		
	}
}
