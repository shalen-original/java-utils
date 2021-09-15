package shutils.sorting;

import shutils.array.SHArray;

public class QuickSort
{
	public static <T extends Comparable<T>> void hybridQuickSort(T[] A, int k)
	{
		hybridQuickSortBetween(A, 0, A.length - 1, k);
	}

	public static <T extends Comparable<T>> void hybridQuickSortBetween(T[] A, int l, int r, int k)
	{
		if (l < r)
		{
			// In the number of element between l and r (given by r - l + 1) is
			// lower or equal to a certain key k
			if (((r - l) + 1) <= k)
			{
				// The algorithm performs insertionSort between l and r
				InsertionSort.insertionSortBetween(A, l, r);
			} else
			{
				// Otherwise we go with the standard quicksort procedure
				int m = Partition(A, l, r);
				hybridQuickSortBetween(A, l, m - 1, k);
				hybridQuickSortBetween(A, m + 1, r, k);
			}
		}

	}

	private static <T extends Comparable<T>> int Partition(T[] A, int l, int r)
	{
		T p = A[r];
		int endLittle = l - 1;

		for (int i = l; i < r; i++)
		{
			if (A[i].compareTo(p) < 0)
			{
				SHArray.swap(A, endLittle + 1, i);
				endLittle++;
			}
		}

		SHArray.swap(A, endLittle + 1, r);

		return endLittle + 1;
	}

	public static <T extends Comparable<T>> void quickSort(T[] A)
	{
		quickSortBetween(A, 0, A.length - 1);
	}

	public static <T extends Comparable<T>> void quickSortBetween(T[] A, int l, int r)
	{
		if ((l < 0) || (r >= A.length))
		{
			throw new ArrayIndexOutOfBoundsException("The given indexes are not valid");
		}

		if (l < r)
		{
			int m = Partition(A, l, r);
			quickSortBetween(A, l, m - 1);
			quickSortBetween(A, m + 1, r);
		}
	}

}
