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
		String result 	= input;
		AnagramUtil.sort(result);
		String expected = "Seehnpt";
//		TestUtil.assertEquals(result, expected);
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
		String[] results 		= AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] expected 	= new String[]{"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
		//Sort each array
		results 		= AnagramUtil.sort(results);
		expected 	= AnagramUtil.sort(expected);
		
		//Compare each element
		for(String s1 : results)
			for(String s2 : expected)
				TestUtil.assertEquals(s1, s2);
		
		
		//Test getLargestAnagramGroup method w/String[]:
		String[] input = new String[]{"carets", "Caters"};
		results = AnagramUtil.getLargestAnagramGroup(input);
		for(String s : results)
			System.out.println(s);
	}
	
	/**
	 * Test different run times of each sorting method
	 */
	public void testRunTime(){
		
	}
}
