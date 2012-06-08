package assign3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
/**
 * 
 * @author Alonzo Rose & Michael Anderson
 * @param <T>
 *
 */

public class AnagramUtil<T> {
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
	public static <T>String sort(String s){
		//Covert String s to T[]
		T[] tarr = (T[]) new Character[s.toCharArray().length];
		
		char[] carr = s.toCharArray();
		
		for(int i = 0; i < carr.length; i ++){
			Character c = carr[i];
			tarr[i] = (T) c;
		}
		
		//Create String Comparator to pass the sorting method
		Comparator<T> byStringComparator = new StringComparator();
		
		//Choose the sorting method
		if(method == SortMethod.INSERTION){
			insertionSort(tarr, byStringComparator);
		}
		
		else{
			selectionSort(tarr, byStringComparator);
		}
		
		//Create return string
		String result = "";
		
		//Transfer sorted Character array back to a string
		for(T t : tarr){
			result = result + t;
		}
		
		//Return sorted String
		return result;
	}
	
	/**
	 * This method sorts an array of strings
	 * @param sarr
	 * @return
	 */
	public static String[] sort(String[] sarr){

		//Create String Comparator to pass the sorting method
		Comparator<String> byStringComparator = new StringComparator();
		
		//Choose the sorting method
		if(method == SortMethod.INSERTION){
			insertionSort(sarr, byStringComparator);
		}
		
		else{
			selectionSort(sarr, byStringComparator);
		}
		
		//Return sorted String
		return sarr;
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param arr
	 * @param c
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> c){
		T key; //The item to be inserted
		int j; //Number of items sorted so far
		int i;
		
		
		//If arr is not larger than one element: return the array.
		
		for(j = 1; j < arr.length; j ++){
			key = arr[j];
			for(i = j-1; (i >= 0) && ( c.compare(arr[i], key) > 0); i --){
				arr[i+1] = arr[i];
			}
			arr[i+1] = key;
		}
	}
	
	/**
	 * This generic method sorts the input array using a selection sort and the input Comparator object.
	 * @param arr
	 * @param c
	 */
	public static <T> void selectionSort(T[] arr, Comparator<? super T> c){
		//TODO this is sorting backwards
		int first;
		T temp;
		
		for(int i = arr.length -1; i > 0; i --){
			first = 0;
			
			for(int j = 1; j <= i; j ++){
				if(c.compare(arr[j], arr[first]) < 0)
					first = j;
			}
			
			temp		= arr[first];
			arr[first]	= arr[i];
			arr[i]		= temp;
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
		String[] anagramList = new String[0];
		//Check if each set is an anagram
		for(int i = 1; i < arr.length; i ++){
			for(int j = 0; j < i; j ++){
				if(areAnagrams(arr[i], arr[j])){
					//if it is then add eat to the list of anagrams
					anagramList = addToArray(arr[i], anagramList);
					anagramList = addToArray(arr[j], anagramList);
				}
			}
		}
		//return the list of anagrams
		return anagramList;
	}
	
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
	
	/**
	 * This class is used to create a String Comparator
	 * @author Alonzo Rose & Michael Anderson
	 *
	 * @param <T>
	 */
	private static class StringComparator<T> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			Comparable<T> element = (Comparable<T>) o1;
			return element.compareTo(o2);
		}
	}
}
