package assign3;

import java.util.Comparator;

/**
 * This comparator uses the natural comparison capability of the given type
 * 
 * @author Alonzo Rose & Michael Anderson
 *
 * @param <T> any type that implements Comparable
 */
public class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
