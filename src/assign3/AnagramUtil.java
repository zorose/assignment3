package assign3;

import java.util.Comparator;

public class AnagramUtil {

	public enum SortMethod { INSERTION, SELECTION };
	private static SortMethod method = SortMethod.INSERTION;
	
	public static String sort(String s){
		//This method returns the sorted version of the input string.
		//The sorting must be accomplished using either an insertion
		//sort or a selection sort, depending on the method state variable.
		return s;
	}
	
	public static <T> void insertionSort(T[] arr, Comparator<? super T> c){
		//This generic method sorts the input array using an insertion sort and the input Comparator object.
	}
	
	public static <T> void selectionSort(T[] arr, Comparator<? super T> c){
		//This generic method sorts the input array using a selection sort and the input Comparator object.
	}
	
	public static boolean areAnagrams(String s1, String s2){
		//This method returns true if the two input strings are anagrams of each other, otherwise returns false.
		return false;
	}
	
	public static String[] getLargestAnagramGroup(String[] arr){
		//This method returns the largest group of anagrams in the input array of words,
		//in no particular order. It returns an empty array if there are no anagrams in the input array.
		return arr;
	}
	
	public static String[] getLargestAnagramGroup(String filename){
		//Behaves the same as the previous method, but reads the list of words from the input filename.
		//It is assumed that the file contains one word per line. If the file does not exist or is empty,
		//the method returns an empty array because there are no anagrams.
		return null;
	}
}
