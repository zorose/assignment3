package assign3;

import java.util.Comparator;

/**
 * A comparator that uses the AnagramUtil to compare strings as anagrams
 * 
 * @author Alonzo Rose & Michael Anderson
 *
 */
public class AnagramComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		String s1_anagram = AnagramUtil.sort(s1.toLowerCase());
		String s2_anagram = AnagramUtil.sort(s2.toLowerCase());
		return s1_anagram.compareTo(s2_anagram);
	}
}
