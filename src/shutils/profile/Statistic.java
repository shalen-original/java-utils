package shutils.profile;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a set of number of type Long and allows to perform
 * statistical operations on them.
 *
 * @author Matteo Nardini
 *
 */
public class Statistic
{
	/**
	 * The list of number of the current set
	 */
	ArrayList<Long> results;

	/**
	 * Creates a new statistic for an empty set of numbers.
	 */
	public Statistic()
	{
		results = new ArrayList<Long>();
	}

	/**
	 * Adds a number to the current set of numbers.
	 *
	 * @param r
	 *            The number to be added.
	 */
	public void appendNumber(Long r)
	{
		results.add(r);
	}

	/**
	 * Used to "clean" the result set. It deletes all the numbers that are not
	 * included in the interval
	 * {@code [getAverage() - 2*getStDev(), getAverage() + 2*getStDev()]}
	 * from the current numbers list.
	 */
	public void clean()
	{
		clean(2);
	}

	/**
	 * Used to "clean" the result set. This method scans the current number list
	 * and searches for values
	 * that a too far form the mean value of this number list. Those values are
	 * deleted from the result set.
	 *
	 * @param i
	 *            This parameters sets how far from the mean the results should
	 *            be in order to be deleted. Any number
	 *            that is not included in the interval
	 *            {@code [getAverage() - i*getStDev(), getAverage() + i*getStDev()]}
	 *            will be
	 *            removed from the current number list.
	 */
	public void clean(int i)
	{
		long avg = getAverage();
		long stDev = getStDev();
		ArrayList<Long> newRes = new ArrayList<>();

		results.forEach(k -> {
			if ((k >= (avg - (i * stDev))) && (k <= (avg + (i * stDev))))
			{
				newRes.add(k);
			}
		});

		results = newRes;
	}

	/**
	 * Empties the result set.
	 */
	public void clearResults()
	{
		results.clear();
	}

	/**
	 * Calculates the average of the current set of numbers (floored to the
	 * closest Long).
	 *
	 * @return The average of the set of numbers (floored to the closest Long).
	 */
	public Long getAverage()
	{
		if (results.size() == 0)
		{
			return (long) 0;
		}

		long avg = 0;

		for (long a : results)
		{
			avg += a;
		}

		return avg / results.size();
	}

	/**
	 * Calculates the median of the current set of numbers (floored to the
	 * closest Long).
	 *
	 * @return The median of the set of numbers (floored to the closest Long).
	 */
	public Long getMedian()
	{

		if (results.size() == 0)
		{
			return (long) 0;
		}

		Collections.sort(results);
		int s = results.size();

		if ((s % 2) == 0)
		{
			return (results.get(s / 2 - 1) + results.get(s / 2)) / 2;
		} else
		{
			return results.get(s / 2);
		}
	}

	/**
	 * Returns the number at a certain index of the numbers list.
	 *
	 * @param index
	 *            The index of the result to be obtained.
	 * @return The number at the position {@code i-th} in the numbers list.
	 */
	public Long getNumberAt(int index)
	{
		return results.get(index);
	}

	/**
	 * Returns the number of numbers in the current numbers list.
	 *
	 * @return The number of numbers in the current numbers list.
	 */
	public Integer getNumberCount()
	{
		return results.size();
	}

	/**
	 * Calculates the standard deviation of the current set of numbers (floored
	 * to the closest Long).
	 *
	 * @return The standard deviation of the set of numbers (floored to the
	 *         closest Long).
	 */
	public Long getStDev()
	{
		if (results.size() == 0)
		{
			return (long) 0;
		}

		long StDev = 0;
		long avg = getAverage();

		for (Long a : results)
		{
			StDev += Math.pow(a - avg, 2);
		}

		StDev /= results.size();

		return (long) Math.sqrt(StDev);
	}
}
