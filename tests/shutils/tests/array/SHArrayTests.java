package shutils.tests.array;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import shutils.array.SHArray;

public class SHArrayTests
{
	@Test
	public void testCopyArray_00()
	{
		Integer[] A = { 4, 5, 2, 6 };
		Integer[] B = SHArray.copyArray(A, Integer.class);

		for (int i = 0; i < A.length; i++)
		{
			if (A[i] != B[i])
			{
				Assert.fail();
			}
		}
	}

	@Test
	public void testCopyArray_01()
	{
		Integer[] B = SHArray.copyArray(null, null);

		assertEquals(true, B == null);
	}

	@Test
	public void testCreateRandomArray_00()
	{
		Integer[] A = SHArray.createRandomArray(10, Integer.class, (i) -> (i));

		for (int i = 0; i < 10; i++)
		{
			if (A[i] != i)
			{
				Assert.fail();
			}
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRandomArray_01()
	{
		SHArray.createRandomArray(10, Integer.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRandomArray_02()
	{
		SHArray.createRandomArray(-5, Integer.class, (i) -> (i));
	}

	@Test
	public void testCreateRandomMatrix_00()
	{
		Integer[][] A = SHArray.createRandomMatrix(10, 4, Integer.class, (i) -> (i));

		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (A[i][j] != ((i * 4) + j))
				{
					Assert.fail();
				}
			}
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRandomMatrix_01()
	{
		SHArray.createRandomMatrix(10, 4, Integer.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRandomMatrix_02()
	{
		SHArray.createRandomMatrix(-5, 4, Integer.class, (i) -> (i));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRandomMatrix_03()
	{
		SHArray.createRandomMatrix(10, -4, Integer.class, (i) -> (i));
	}

	@Test
	public void testFindMax_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(8, (int) SHArray.findMax(A));
	}

	@Test
	public void testFindMaxInRange_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(8, (int) SHArray.findMaxInRange(A, 0, A.length - 1));
	}

	@Test
	public void testFindMaxInRange_01()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(7, (int) SHArray.findMaxInRange(A, 1, 3));
	}

	@Test
	public void testFindMaxInRange_02()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(5, (int) SHArray.findMaxInRange(A, 1, 1));
	}

	@Test
	public void testFindMaxInRange_03()
	{

		Integer[] A = {};

		assertEquals(null, SHArray.findMaxInRange(A, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMaxInRange_04()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxInRange(A, 6, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMaxInRange_05()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxInRange(A, -1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMaxInRange_06()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxInRange(A, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMaxInRange_07()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxInRange(A, 1, -1);
	}

	@Test
	public void testFindMaxPos_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(4, (int) SHArray.findMaxPos(A));
	}

	@Test
	public void testFindMaxPosInRange_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(4, (int) SHArray.findMaxPosInRange(A, 0, A.length - 1));
	}

	@Test
	public void testFindMaxPosInRange_01()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(3, (int) SHArray.findMaxPosInRange(A, 1, 3));
	}

	@Test
	public void testFindMaxPosInRange_02()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(1, (int) SHArray.findMaxPosInRange(A, 1, 1));
	}

	@Test
	public void testFindMaxPosInRange_03()
	{

		Integer[] A = {};

		assertEquals(null, SHArray.findMaxPosInRange(A, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMaxPosInRange_04()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxPosInRange(A, 6, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMaxPosInRange_05()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxPosInRange(A, -1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMaxPosInRange_06()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxPosInRange(A, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMaxPosInRange_07()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMaxPosInRange(A, 1, -1);
	}

	@Test
	public void testFindMin_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(2, (int) SHArray.findMin(A));
	}

	@Test
	public void testFindMinInRange_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(2, (int) SHArray.findMinInRange(A, 0, A.length - 1));
	}

	@Test
	public void testFindMinInRange_01()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(4, (int) SHArray.findMinInRange(A, 1, 3));
	}

	@Test
	public void testFindMinInRange_02()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(5, (int) SHArray.findMinInRange(A, 1, 1));
	}

	@Test
	public void testFindMinInRange_03()
	{

		Integer[] A = {};

		assertEquals(null, SHArray.findMinInRange(A, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMinInRange_04()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinInRange(A, 6, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMinInRange_05()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinInRange(A, -1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMinInRange_06()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinInRange(A, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMinInRange_07()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinInRange(A, 1, -1);
	}

	@Test
	public void testFindMinPos_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(0, (int) SHArray.findMinPos(A));
	}

	@Test
	public void testFindMinPosInRange_00()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(0, (int) SHArray.findMinPosInRange(A, 0, A.length - 1));
	}

	@Test
	public void testFindMinPosInRange_01()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(2, (int) SHArray.findMinPosInRange(A, 1, 3));
	}

	@Test
	public void testFindMinPosInRange_02()
	{

		Integer[] A = { 2, 5, 4, 7, 8, 7, 2 };

		assertEquals(1, (int) SHArray.findMinPosInRange(A, 1, 1));
	}

	@Test
	public void testFindMinPosInRange_03()
	{

		Integer[] A = {};

		assertEquals(null, SHArray.findMinPosInRange(A, 0, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMinPosInRange_04()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinPosInRange(A, 6, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMinPosInRange_05()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinPosInRange(A, -1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testFindMinPosInRange_06()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinPosInRange(A, 5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindMinPosInRange_07()
	{

		Integer[] A = { 3, 6, 5, 8 };

		SHArray.findMinPosInRange(A, 1, -1);
	}

	@Test
	public void testGenerateRandomBetween_00()
	{
		for (int i = 0; i < 200; i++)
		{
			Integer a = SHArray.generateRandomBetween(3, 10);
			if ((a < 3) || (a > 10))
			{
				Assert.fail();
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateRandomBetween_01()
	{
		SHArray.generateRandomBetween(-2, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateRandomBetween_02()
	{
		SHArray.generateRandomBetween(1, -4);
	}

	@Test
	public void testShiftLeft_00()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftLeft(A, 0, 2);
		assertEquals(true, (A[0] == 2) && (A[1] == 3) && (A[2] == 3) && (A[3] == 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShiftLeft_01()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftLeft(A, 2, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testShiftLeft_02()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftLeft(A, -1, 2);
	}

	@Test
	public void testShiftRight_00()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftRight(A, 0, 2);
		assertEquals(true, (A[0] == 1) && (A[1] == 1) && (A[2] == 2) && (A[3] == 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShiftRight_01()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftRight(A, 2, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testShiftRight_02()
	{
		Integer[] A = { 1, 2, 3, 4 };
		SHArray.shiftRight(A, -1, 2);
	}

	@Test
	public void testSwap_00()
	{
		Integer[] A = { 1, 2, 3 };
		SHArray.swap(A, 0, 2);

		assertEquals(true, (A[0] == 3) && (A[1] == 2) && (A[2] == 1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSwap_01()
	{
		Integer[] A = { 1, 2, 3 };
		SHArray.swap(A, -1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSwap_02()
	{
		Integer[] A = { 1, 2, 3 };
		SHArray.swap(A, 3, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSwap_03()
	{
		Integer[] A = { 1, 2, 3 };
		SHArray.swap(A, 1, -5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSwap_04()
	{
		Integer[] A = { 1, 2, 3 };
		SHArray.swap(A, 1, 3);
	}

}
