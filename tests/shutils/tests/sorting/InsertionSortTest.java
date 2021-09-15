package shutils.tests.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import shutils.sorting.InsertionSort;

public class InsertionSortTest
{

	@Test
	public void testInsertionSort_00()
	{
		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { -8, 3, 4, 5, 5, 6, 9 };

		InsertionSort.insertionSort(A);

		for (int i = 0; i < A.length; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}
	}

	@Test
	public void testInsertionSort_01()
	{
		Integer[] A = {};

		InsertionSort.insertionSort(A);

		assertEquals(true, A.length == 0);
	}

	@Test
	public void testInsertionSortBetween_00()
	{

		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };
		Integer[] sortedA = { 3, -8, 4, 5, 6, 9, 5 };

		InsertionSort.insertionSortBetween(A, 1, 4);

		for (int i = 1; i < 5; i++)
		{
			if (A[i] != sortedA[i])
			{
				fail("The array is not sorted.");
			}
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertionSortBetween_01()
	{

		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };

		InsertionSort.insertionSortBetween(A, 4, 1);

	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testInsertionSortBetween_02()
	{

		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };

		InsertionSort.insertionSortBetween(A, -5, 4);

	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testInsertionSortBetween_03()
	{

		Integer[] A = { 3, 6, 4, -8, 5, 9, 5 };

		InsertionSort.insertionSortBetween(A, 0, 698);

	}

}
