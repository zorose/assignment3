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
	
	public void getLargestAnagramGroupTest(){
		//Test getLargestAnagramGroup method w/File name:
		String[] results = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] provenResults = new String[]{"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
		//Sort each array
		results 		= AnagramUtil.sort(results);
		provenResults 	= AnagramUtil.sort(provenResults);
		
		//Compare each element
		for(String s1 : results)
			for(String s2 : provenResults)
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
