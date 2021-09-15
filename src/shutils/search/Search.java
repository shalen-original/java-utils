package shutils.search;

/**
 * This class contains some of the most frequently used search algorithms.
 * @author Matteo Nardini
 *
 */
public class Search
{
	/**
	 * Finds an element in the array.
	 * @param A The array to be searched.
	 * @param q The value to be found.
	 * @param <T> The type of data contained in the array {@code A}.
	 * @return The position of the searched element in the array. If it doesn't exist, the method returns {@code -1}.
	 */
	public static <T> int linearSearch(T[] A, T q)
	{
		for (int i = 0; i < A.length; i++)
		{
			if (A[i].equals(q)) return i;
		}
		
		return -1;
	}
	
	/**
	 * Finds an element in a sorted array.
	 * @param A The sorted array to be searched.
	 * @param q The value to be found.
	 * @param <T> The type of data contained in the array {@code A}.
	 * @return The position of the searched element in the array. If it doesn't exist, the method returns {@code -1}.
	 */
	public static <T extends Comparable<T>> int binarySearch(T[] A, T q)
	{
		// The array is sorted, I can perform a binarySearch
		return binarySearchBetween(A, 0, A.length - 1, q);
	}

	/**
	 * Performs a binary search on an array.	
	 * @param A The array on which the search has to be performed.
	 * @param i The lower index endpoint of the current search interval.
	 * @param j The upper index endpoint of the current search interval.
	 * @param q The object to be found.
	 * @param <T> The type of data contained in the array {@code A}.
	 * @return The position of the searched element in the array. If it doesn't exist, the method returns {@code -1}.
	 */
	public static <T extends Comparable<T>> int binarySearchBetween(T[] A, int i, int j, T q)
	{
		if (i > j) return -1;
		
		// Finds the middle element
		int middle = (i + j) / 2;
		
		// If it is equal to q, then I found what was requested.
		if (A[middle].compareTo(q) == 0) return middle;
		
		if (q.compareTo(A[middle]) < 0) //q < A 
		{
			// If the searched value is lower than A[middle], then I search
			// in the left half of this subset of A
			return binarySearchBetween(A, i, middle - 1, q);
		}else{
			//otherwise in its right half
			return binarySearchBetween(A, middle + 1, j, q);	
		}
	}
}
