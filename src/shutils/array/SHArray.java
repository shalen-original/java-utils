package shutils.array;

import java.lang.reflect.Array;
import java.util.function.Function;

/**
 * This class contains various utilities implemented as static methods that
 * allow to work with standard arrays.
 *
 * @author Matteo Nardini
 */
public class SHArray
{
	/**
	 * Internal utility to check if the endpoints are valid.
	 *
	 * @param l
	 *            The length of the array.
	 * @param i
	 *            The left (lower) endpoint.
	 * @param j
	 *            The right (greater) endpoint.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	private static void checkEndpoints(int l, int i, int j)
	{
		if (i > j)
		{
			throw new IllegalArgumentException("The lower endpoint is greater than the upper one.");
		}

		if ((i < 0) || (j >= l))
		{
			throw new IndexOutOfBoundsException("The endpoints are not valid.");
		}
	}

	/**
	 * Copies an array to another array. The copy is shallow.
	 *
	 * @param A
	 *            The array to be copied.
	 * @param c
	 *            The class of the elements contained in A.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return A shallow copy of the array.
	 */
	public static <T> T[] copyArray(T[] A, Class<T> c)
	{
		if (A == null)
		{
			return null;
		}

		@SuppressWarnings("unchecked")
		T[] copy = (T[]) Array.newInstance(c, A.length);

		for (int i = 0; i < A.length; i++)
		{
			copy[i] = A[i];
		}

		return copy;
	}

	/**
	 * Generates a random array of a certain size.
	 *
	 * @param size
	 *            The size of the array to be generated.
	 * @param randomGenerator
	 *            The function that generates the random elements of the array.
	 * @param type
	 *            The class of the random elements to be generated.
	 * @param <T>
	 *            The type of elements contained in the randomly generated
	 *            array.
	 * @return The random array.
	 * @throws IllegalArgumentException
	 *             If the {@code randomGenerator} is null or if {@code size} is
	 *             negative.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] createRandomArray(int size, Class<T> type, Function<Integer, T> randomGenerator)
	{
		if (randomGenerator == null)
		{
			throw new IllegalArgumentException("The random generator cannot be null");
		}

		if (size < 0)
		{
			throw new IllegalArgumentException("The size must be non negative");
		}

		T[] ans = (T[]) Array.newInstance(type, size);

		for (int i = 0; i < ans.length; i++)
		{
			ans[i] = randomGenerator.apply(i);
		}

		return ans;
	}

	/**
	 * Generates a random matrix of a certain size.
	 *
	 * @param rowNumber
	 *            The number of rows of the matrix to be generated.
	 * @param colNumber
	 *            The number of columns of the matrix to be generated.
	 * @param randomGenerator
	 *            The function that generates the random elements of the matrix.
	 * @param type
	 *            The class of the random elements to be generated.
	 * @param <T>
	 *            The type of elements contained in the randomly generated
	 *            matrix.
	 * @return The random matrix.
	 * @throws IllegalArgumentException
	 *             If the {@code randomGenerator} is null or if
	 *             {@code rowNumber} or {@code colNumber} are negative.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] createRandomMatrix(int rowNumber, int colNumber, Class<T> type,
			Function<Integer, T> randomGenerator)
	{
		if (randomGenerator == null)
		{
			throw new IllegalArgumentException("The random generator cannot be null");
		}

		if ((rowNumber < 0) || (colNumber < 0))
		{
			throw new IllegalArgumentException("The size must be non negative");
		}

		T[][] ans = (T[][]) Array.newInstance(type, rowNumber, colNumber);

		for (int i = 0; i < rowNumber; i++)
		{
			for (int j = 0; j < colNumber; j++)
			{
				ans[i][j] = randomGenerator.apply((i * colNumber) + j);
			}
		}

		return ans;
	}

	/**
	 * Finds the max element of the array.
	 *
	 * @param A
	 *            The array which max has to be found.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The max element of the array {@code A}.
	 */
	public static <T extends Comparable<T>> T findMax(T[] A)
	{
		return findMaxInRange(A, 0, A.length - 1);
	}

	/**
	 * Finds the max element of the array in the interval {@code (i;j)}.
	 *
	 * @param A
	 *            The array which max has to be found.
	 * @param i
	 *            The lower endpoint of the search interval (inclusive).
	 * @param j
	 *            The upper endpoint of the search interval (inclusive).
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The max element of the array {@code A} in the interval
	 *         {@code (i;j)}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T extends Comparable<T>> T findMaxInRange(T[] A, int i, int j)
	{
		if (A.length == 0)
		{
			return null;
		}

		checkEndpoints(A.length, i, j);

		T max = A[i];
		for (; i <= j; i++)
		{
			if (A[i].compareTo(max) > 0)
			{
				max = A[i];
			}
		}

		return max;
	}

	/**
	 * Finds the max element position of the array.
	 *
	 * @param A
	 *            The array which max position has to be found.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The max element position of the array {@code A}.
	 */
	public static <T extends Comparable<T>> Integer findMaxPos(T[] A)
	{
		return findMaxPosInRange(A, 0, A.length - 1);
	}

	/**
	 * Finds the max element position of the array in the interval {@code (i;j)}
	 * .
	 *
	 * @param A
	 *            The array which max position has to be found.
	 * @param i
	 *            The lower endpoint of the search interval (inclusive).
	 * @param j
	 *            The upper endpoint of the search interval (inclusive).
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The max element position of the array {@code A} in the interval
	 *         {@code (i;j)}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T extends Comparable<T>> Integer findMaxPosInRange(T[] A, int i, int j)
	{
		if (A.length == 0)
		{
			return null;
		}

		checkEndpoints(A.length, i, j);

		int maxPos = i;

		for (; i <= j; i++)
		{
			if (A[i].compareTo(A[maxPos]) > 0)
			{
				maxPos = i;
			}
		}

		return maxPos;
	}

	/**
	 * Finds the min element of the array.
	 *
	 * @param A
	 *            The array which min has to be found.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The min element of the array {@code A}.
	 */
	public static <T extends Comparable<T>> T findMin(T[] A)
	{
		return findMinInRange(A, 0, A.length - 1);
	}

	/**
	 * Finds the min element of the array in the interval {@code (i;j)}.
	 *
	 * @param A
	 *            The array which min has to be found.
	 * @param i
	 *            The lower endpoint of the search interval (inclusive).
	 * @param j
	 *            The upper endpoint of the search interval (inclusive).
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The min element of the array {@code A} in the interval
	 *         {@code (i;j)}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T extends Comparable<T>> T findMinInRange(T[] A, int i, int j)
	{
		if (A.length == 0)
		{
			return null;
		}

		if (i > j)
		{
			throw new IllegalArgumentException("The lower endpoint is greater than the upper one.");
		}

		checkEndpoints(A.length, i, j);

		T min = A[i];
		for (; i <= j; i++)
		{
			if (A[i].compareTo(min) < 0)
			{
				min = A[i];
			}
		}

		return min;
	}

	/**
	 * Finds the min element position of the array.
	 *
	 * @param A
	 *            The array which min position has to be found.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The min element position of the array {@code A}.
	 */
	public static <T extends Comparable<T>> Integer findMinPos(T[] A)
	{
		return findMinPosInRange(A, 0, A.length - 1);
	}

	/**
	 * Finds the min element position of the array in the interval {@code (i;j)}
	 * .
	 *
	 * @param A
	 *            The array which min position has to be found.
	 * @param i
	 *            The lower endpoint of the search interval (inclusive).
	 * @param j
	 *            The upper endpoint of the search interval (inclusive).
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @return The min element position of the array {@code A} in the interval
	 *         {@code (i;j)}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T extends Comparable<T>> Integer findMinPosInRange(T[] A, int i, int j)
	{
		if (A.length == 0)
		{
			return null;
		}

		checkEndpoints(A.length, i, j);

		int minPos = i;

		for (; i <= j; i++)
		{
			if (A[i].compareTo(A[minPos]) < 0)
			{
				minPos = i;
			}
		}

		return minPos;
	}

	/**
	 * Generates a <b>positive</b> random integer between two endpoints.
	 *
	 * @param min
	 *            The lower endpoint
	 * @param max
	 *            The upper endpoint
	 * @return A positive random integer between {@code min} and {@code max}
	 *         (inclusive).
	 */
	public static Integer generateRandomBetween(Integer min, Integer max)
	{
		if ((max < 0) || (min < 0))
		{
			throw new IllegalArgumentException("Min and max have to be positive");
		}

		return (int) (min + (Math.random() * (max - min)) + 1);
	}

	/**
	 * Shifts all the elements of {@code A} starting from position {@code j} and
	 * reaching position {@code i}.
	 * The element {@code A[j]} is unchanged.
	 *
	 * @param A
	 *            The array which element are to be shifted.
	 * @param i
	 *            The end position for the shift.
	 * @param j
	 *            The start position for the shift.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T> void shiftLeft(T[] A, int i, int j)
	{
		checkEndpoints(A.length, i, j);

		for (; i < j; i++)
		{
			A[i] = A[i + 1];
		}
	}

	/**
	 * Shifts all the elements of {@code A} starting from position {@code i} and
	 * reaching position {@code j}.
	 * The element {@code A[i]} is unchanged.
	 *
	 * @param A
	 *            The array which element are to be shifted.
	 * @param i
	 *            The starting position for the shift.
	 * @param j
	 *            The end position for the shift.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @throws IllegalArgumentException
	 *             When {@code i > j}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i < 0} or {@code j >= A.length}.
	 */
	public static <T> void shiftRight(T[] A, int i, int j)
	{
		checkEndpoints(A.length, i, j);

		for (; j > i; j--)
		{
			A[j] = A[j - 1];
		}
	}

	/**
	 * Swaps two elements of the array.
	 *
	 * @param A
	 *            The array which elements are to be swapped.
	 * @param i
	 *            The index of the first element to be swapped.
	 * @param j
	 *            The index of the second element to be swapped.
	 * @param <T>
	 *            The type of elements contained in {@code A}.
	 * @throws IndexOutOfBoundsException
	 *             When {@code i} or {@code j} are not valid.
	 */
	public static <T> void swap(T[] A, int i, int j)
	{
		if (((i < 0) || (i >= A.length)) || ((j < 0) || (j >= A.length)))
		{
			throw new IndexOutOfBoundsException("The two indexes are not valid");
		}

		T tmp;
		tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

}
