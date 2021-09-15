package shutils.sorting;

import shutils.array.SHArray;

/**
 * This class gives a common implementation of the Insertion Sort algorithm.
 *
 * @author Matteo Nardini
 *
 */
public class InsertionSort
{

	/**
	 * Sorts an array using the Insertion Sort algorithm. Just as a remainder,
	 * we have that the best case complexity is {@code O(n)}, the
	 * average case complexity is {@code O(n^2)} and the worst case complexity
	 * is {@code O(n^2)}.
	 *
	 * @param A
	 *            The array to be sorted.
	 * @param <T>
	 *            The type of items contained in A.
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] A)
	{
		insertionSortBetween(A, 0, A.length > 0 ? A.length - 1 : 0);
	}

	/**
	 * Sorts an array using the Insertion Sort algorithm. Just as a remainder,
	 * we have that the best case complexity is {@code O(n)}, the
	 * average case complexity is {@code O(n^2)} and the worst case complexity
	 * is {@code O(n^2)}.
	 *
	 * @param A
	 *            A The array to be sorted.
	 * @param l
	 *            The index of the leftmost element to sort.
	 * @param r
	 *            The index of the rightmost element to sort.
	 * @param <T>
	 *            The type of items contained in A.
	 */
	public static <T extends Comparable<T>> void insertionSortBetween(T[] A, int l, int r)
	{
		if (l == r)
		{
			return;
		}

		if (l > r)
		{
			throw new IllegalArgumentException("The lower bound cannot be grater than the upper one");
		}

		if ((l < 0) || (r >= A.length))
		{
			throw new ArrayIndexOutOfBoundsException("The given indexes are not valid");
		}

		int k;
		for (int i = l; i <= r; i++)
		{
			k = i;
			while ((k > l) && (A[k].compareTo(A[k - 1]) < 0))
			{
				SHArray.swap(A, k, k - 1);
				k--;
			}

		}
	}

}
