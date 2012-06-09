package assign3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
/**
 * 
 * @author Alonzo Rose & Michael Anderson
 *
 */

public class AnagramUtil {
	//Variables
	public enum 	SortMethod { INSERTION, SELECTION };
	private static 	SortMethod method	= SortMethod.INSERTION;
	
	
	/**
	 * This method returns the sorted version of the input string.
	 * The sorting must be accomplished using either an insertion sort or a selection sort,
	 * depending on the method state variable. This method should preserve case.
	 * So the result of calling AnagramUtil.sort("Stephen") should be "Seehnpt".
	 * @param s
	 * @return
	 */
	public static String sort(String s){
		if (s == null) {
			throw new IllegalArgumentException("String cannot be null!");
		}
		
		//Covert String s to character array
		Character[] arr = stringToCharacterArray(s);
		
		//Create String Comparator to pass the sorting method
		Comparator<Character> stringComparator = new NaturalComparator<Character>();
		
		//Choose the sorting method
		if (method == SortMethod.INSERTION){
			insertionSort(arr, stringComparator);
		}
		else {
			selectionSort(arr, stringComparator);
		}

		//Return sorted String
		return characterArrayToString(arr);
	}
	
	/**
	 * This method sorts the given strings into groups of anagrams
	 * @param sarr
	 * @return
	 */
	public static String[] sort(String[] sarr){
		// Create String Comparator to pass the sorting method
		Comparator<String> anagramComparator = new AnagramComparator();
		
		// Choose the sorting method
		if(method == SortMethod.INSERTION){
			insertionSort(sarr, anagramComparator);
		}
		else{
			selectionSort(sarr, anagramComparator);
		}
		
		// Return sorted Strings
		return sarr;
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param arr
	 * @param c
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> c){
		// validate parameters
		if (arr == null) {
			throw new IllegalArgumentException("The given array cannot be null!");
		}
		if (c == null) {
			throw new IllegalArgumentException("The given comparator cannot be null!");
		}
		
		T key; // The item to be inserted
		int gapIndex; // Keeps track of the current gap index
		
		// Go through each item
		for (int i = 1; i < arr.length; i++) {
			// Make the gap
			key = arr[i];
			gapIndex = i;
			
			// Position the gap
			while (gapIndex > 0 && c.compare(arr[gapIndex - 1], key) > 0 ) {
				// Move the gap up
				arr[gapIndex] = arr[gapIndex - 1];
				gapIndex--;
			}
			
			// Insert into the gap
			arr[gapIndex] = key;
		}
	}
	
	/**
	 * This generic method sorts the input array using a selection sort and the input Comparator object.
	 * @param arr
	 * @param c
	 */
	public static <T> void selectionSort(T[] arr, Comparator<? super T> c){
		// validate parameters
		if (arr == null) {
			throw new IllegalArgumentException("The given array cannot be null!");
		}
		if (c == null) {
			throw new IllegalArgumentException("The given comparator cannot be null!");
		}

		int smallestIndex;
		for (int i = 0; i < arr.length - 1; i++) {
			smallestIndex = i;

			// Find the index of the smallest element
			for (int j = i + 1; j < arr.length; j++) {
				if (c.compare(arr[j], arr[smallestIndex]) < 0) {
					smallestIndex = j;
				}
			}
			
			// Swap elements if necessary
			if (smallestIndex != i) {
				T temp = arr[i];
				arr[i] = arr[smallestIndex];
				arr[smallestIndex] = temp;
			}
		}
	}
	
	/**
	 * This method returns true if the two input strings (ignoring case) are anagrams of each other, otherwise returns false.
	 * @param s1
	 * @param s2
	 * @return boolean
	 */
	public static boolean areAnagrams(String s1, String s2){
		//Sort each string ignoring case
		s1 = sort(s1.toLowerCase());
		s2 = sort(s2.toLowerCase());
		
		//Compare and return results
		if(s1.length() != s2.length())
			return false;
		
		if(s1.compareTo(s2) != 0)
			return false;
		else
			return true;
	}
	
	/**
	 * This method returns the largest group of anagrams in the input array of words,
	 * in no particular order. It returns an empty array if there are no anagrams in the input array.
	 * only one will be added.
	 * @param arr
	 * @return String[]
	 */
	public static String[] getLargestAnagramGroup(String[] arr){
		if (arr == null) {
			throw new IllegalArgumentException("Cannot accept a null array!");
		}
		if (arr.length < 1) {
			throw new IllegalArgumentException("Cannot accept an empty array!");
		}
		String[] wordList = arr.clone();
		sort(wordList);
		
		// Check for anagram groups
		String firstWordInGroup = wordList[0];
		int groupIndex = 0;
		int groupSize = 1;
		int largestGroupSize = 1;
		int largestGroupIndex = 0;
		int largestGroupIndexEnd = 0;
		for (int i = 1; i < wordList.length; i++) {
			if (areAnagrams(firstWordInGroup, wordList[i])) {
				groupSize++;
				if (groupSize > largestGroupSize) {
					largestGroupIndex = groupIndex;
					largestGroupSize = groupSize;
					largestGroupIndexEnd = i;
				}
			}
			else {
				firstWordInGroup = wordList[i];
				groupSize = 1;
				groupIndex = i;
			}
		}
		
		// Grab the strings in the largest group
		String[] anagramList;
		if (largestGroupSize > 1) {
			anagramList = new String[largestGroupIndexEnd - largestGroupIndex + 1];
			for (int i = 0; i < anagramList.length; i++) {
				anagramList[i] = wordList[i + largestGroupIndex];
			}
		}
		else {
			anagramList = new String[0];
		}
	
		return anagramList;
	}

//	public static String[] getLargestAnagramGroup(String[] arr){
//		String[] anagramList = new String[0];
//		//Check if each set is an anagram
//		for(int i = 1; i < arr.length; i ++){
//			for(int j = 0; j < i; j ++){
//				if(areAnagrams(arr[i], arr[j])){
//					//if it is then add each to the list of anagrams
//					anagramList = addToArray(arr[i], anagramList);
//					anagramList = addToArray(arr[j], anagramList);
//				}
//			}
//		}
//		//return the list of anagrams
//		return anagramList;
//	}
	
	/**
	 * Behaves the same as the previous method, but reads the list of words from the input filename.
	 * It is assumed that the file contains one word per line. If the file does not exist or is empty,
	 * the method returns an empty array because there are no anagrams.
	 * @param filename
	 * @return String[]
	 */
	public static String[] getLargestAnagramGroup(String filename){
		//Create new String array to return
		String[] results = new String[0];
		
		//Get file from String filename
		File file = new File(filename);
		
		//Create scanner from text in file
		Scanner sc;
		try {
			sc = new Scanner(file);
			
			//Copy info from scanner to String array
			while(sc.hasNext()){
				results = addToArray(sc.next(),results);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Return the anagram list
		return getLargestAnagramGroup(results);
	}

	/**
	 * This method is used to set the state of the AnagramUtil class to use either selection or insertion sort
	 * @param sortMethod
	 */
	public static void setSortMethod(SortMethod sortMethod){
		method = sortMethod;
	}
	
	/**
	 * This method adds a String to a String[] excluding all duplicates.
	 * @param s1
	 * @param arr
	 * @return String[]
	 */
	private static String[] addToArray(String s1, String[] arr){
		//Checks for duplicates ignoring case
		if(duplicateInsertion(s1, arr) == true)
			return arr;

		
		//Increase array size by 1
		String[] tempList = arr.clone();
		arr = new String[arr.length + 1];
		
		//Add in original elements
		if(!(tempList.length < 1)){
			for(int i = 0; i < tempList.length; i ++)
				arr[i] = tempList[i];
		}
		
		//Add new String to Array
		arr[tempList.length] = s1;
		
		return arr;
	}
	
	/**
	 * Uses binary searching to check if a String is already in a String[].
	 * Upper and lower case words that are the same are considered duplicates.
	 * ie Hat and hat are duplicates.
	 * @param s1
	 * @param arr
	 * @return
	 */
	private static boolean duplicateInsertion(String s1, String[] arr){
		for(String s : arr)
			if(s1.toLowerCase().equals(s.toLowerCase()))
				return true;
		return false;
	}
	
	private static Character[] stringToCharacterArray(String s) {
		char[] arr = s.toCharArray();
		Character[] carr = new Character[arr.length];
		for (int i = 0; i < arr.length; i++) {
			carr[i] = arr[i];
		}
		
		return carr;
	}
	
	private static String characterArrayToString(Character[] carr) {
		StringBuilder result = new StringBuilder();
		for (Character each : carr) {
			result.append(each);
		}
		
		return result.toString();
	}
}
