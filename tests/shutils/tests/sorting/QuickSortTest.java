package shutils.tests.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import shutils.sorting.QuickSort;

public class QuickSortTest
{

	@Test
	public void testHybridQuickSort_00()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { -8, 3, 4, 5, 5, 6, 9 };

		QuickSort.hybridQuickSort(A, 3);

		for (int i = 0; i < A.length; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}
	}

	@Test
	public void testHybridQuickSort_01()
	{
		Integer[] A = {};

		QuickSort.hybridQuickSort(A, 3);

		assertEquals(true, A.length == 0);
	}

	@Test
	public void testHybridQuickSortBetween_00()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { 3, -8, 4, 5, 6, 9, 5 };

		QuickSort.hybridQuickSortBetween(A, 1, 4, 3);

		for (int i = 1; i < 5; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testHybridQuickSortBetween_01()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };

		QuickSort.hybridQuickSortBetween(A, -5, 4, 3);
	}

	@Test
	public void testQuickSort_00()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { -8, 3, 4, 5, 5, 6, 9 };

		QuickSort.quickSort(A);

		for (int i = 0; i < A.length; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}
	}

	@Test
	public void testQuickSort_01()
	{
		Integer[] A = {};

		QuickSort.quickSort(A);

		assertEquals(true, A.length == 0);
	}

	@Test
	public void testQuickSortBetween_00()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { 3, -8, 4, 5, 6, 9, 5 };

		QuickSort.quickSortBetween(A, 1, 4);

		for (int i = 1; i < 5; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testQuickSortBetween_01()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };

		QuickSort.quickSortBetween(A, -5, 4);
	}

}
