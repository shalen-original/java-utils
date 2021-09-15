package shutils.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import shutils.data.visualization.DataTable;
import shutils.data.visualization.PlotDataSeries;

/**
 * This class allows to profile a simple algorithm which requires as a parameter
 * a single
 * parameter of type T. This class internally uses {@code System.nanoTime()} to
 * compute the
 * running time of the algorithm. However, this class does not guarantee a
 * nanosecond precision.
 *
 * @author Matteo Nardini
 *
 * @param <T>
 *            The type of the parameter accepted by the algorithm to be
 *            profiled.
 */
public class Profiler<T>
{

	/**
	 * The method/algorithm to be profiled.
	 */
	private Consumer<T> algorithm;

	/**
	 * The method that allows to generate on the fly a test input set.
	 */
	private Function<Integer, T> inputProducer;

	/**
	 * The method that copies one of the elements of the test list. This is
	 * needed to run
	 * multiple time the same test.
	 */
	private Function<T, T> copyProducer;

	/**
	 * The input set to use in order to profile the desired method.
	 */
	private List<T> inputSet;

	/**
	 * Reports if the current test set has already been profiled or not.
	 */
	private boolean profiledForCurrentSet;

	/**
	 * Contains the results of the profiling.
	 */
	private ArrayList<Statistic> results;

	/**
	 * Contains general statistics elaborated from the profiling of the method.
	 */
	private Statistic globalStatistics;

	/**
	 * Stores the number of time every entry of the inputSet has been tested
	 * during the last test
	 */
	private int lastTestRepetition;

	/**
	 * Initializes this profiler with a certain algorithm to be profiled and a
	 * certain input
	 * generator which can be used to generate the test input set that allows to
	 * profile the algorithm.
	 *
	 * @param algorithm
	 *            The algorithm to be profiled.
	 * @param inputProducer
	 *            The test input set generator to be used to create the test set
	 *            required to profile the algorithm.
	 * @param copyProducer
	 *            The routine that copies a given element of the test set. This
	 *            is required
	 *            in order to repeat the test multiple times.
	 */
	public Profiler(Consumer<T> algorithm, Function<Integer, T> inputProducer, Function<T, T> copyProducer)
	{
		this(algorithm, copyProducer);
		this.inputSet = null;
		this.inputProducer = inputProducer;
	}

	/**
	 * Internal constructor, used to initialized most of the internal details of
	 * the class.
	 *
	 * @param algorithm
	 *            The method/algorithm to be profiled.
	 * @param copyProducer
	 *            The routine that copies a given element of the test set. This
	 *            is required
	 *            in order to repeat the test multiple times.
	 */
	private Profiler(Consumer<T> algorithm, Function<T, T> copyProducer)
	{
		this.algorithm = algorithm;
		this.copyProducer = copyProducer;

		profiledForCurrentSet = false;

		results = new ArrayList<Statistic>();
		globalStatistics = new Statistic();
	}

	/**
	 * Initializes this profiled with a certain algorithm to be profiled and a
	 * certain input
	 * test used to profile it.
	 *
	 * @param algorithm
	 *            The algorithm to be profiled.
	 * @param inputSet
	 *            The test input set to be used to profile the algorithm.
	 * @param copyProducer
	 *            The routine that copies a given element of the test set. This
	 *            is required
	 *            in order to repeat the test multiple times.
	 */
	public Profiler(Consumer<T> algorithm, List<T> inputSet, Function<T, T> copyProducer)
	{
		this(algorithm, copyProducer);
		this.inputSet = inputSet;
		this.inputProducer = null;

	}

	/**
	 * Used to "clean" the results statistics. It deletes all the numbers that
	 * are not included in the interval
	 * {@code [getAverage() - 2*getStDev(), getAverage() + 2*getStDev()]}
	 * from all the results statistics.
	 */
	public void cleanAllResultStatistics()
	{
		// Beware, this is not the only way to clean the results statistics
		// from the outside. One could do o.getResultStatistics(i).clean()
		results.forEach(i -> i.clean());
	}

	/**
	 * Used to "clean" the global statistics. It deletes all the numbers that
	 * are not included in the interval
	 * {@code [getAverage() - 2*getStDev(), getAverage() + 2*getStDev()]}
	 * from the global statistics result.
	 */
	public void cleanGlobalStatistics()
	{
		// Beware, this is not the only way to clean the global statistics
		// from the outside. One could do o.getGlobalStatistics().clean()
		globalStatistics.clean();
	}

	/**
	 * Internal routine that clears the previous results
	 */
	private void clearPreviousResults()
	{
		results.clear();
		globalStatistics.clearResults();
	}

	/**
	 * Returns the global statistics. Global statistics are given by the sum of
	 * all the time
	 * taken by all the tests.
	 *
	 * @return Returns the global statistics.
	 */
	public Statistic getGlobalStatistics()
	{
		return globalStatistics;
	}

	/**
	 * Returns the statistics of a single result.
	 *
	 * @param i
	 *            The index of the result to return.
	 * @return The statistics of the {@code i}-th result.
	 */
	public Statistic getResultStatistics(int i)
	{
		return results.get(i);
	}

	/**
	 * Returns the global results as a data table.
	 *
	 * @return A an object of type {@code DataTable<Long>} containing the global
	 *         results.
	 */
	public DataTable<Long> globalResultsToDataTable()
	{
		DataTable<Long> ans = new DataTable<>(3);

		ans.setHeadings("Number of tests & Global average [ns]", "Global st. dev. [ns]", "Global median [ns]");

		Long[] row = { globalStatistics.getAverage(), globalStatistics.getStDev(), globalStatistics.getMedian() };

		ans.addRow(row);

		return ans;
	}

	/**
	 * Allows to check if the current test input set has already been used to
	 * profile the algorithm
	 * or not. This method also allows to know if the result of the profiling
	 * are already available or not.
	 *
	 * @return This method returns {@code true} if the current test input set
	 *         has already been profiled and
	 *         the results are ready. This method returns {@code false} if the
	 *         current test input set has <b>NOT</b>
	 *         already been profiled. In this second case, the results and the
	 *         statistics for this set are not
	 *         available.
	 */
	public boolean isCurrentSetAlreadyProfiled()
	{
		return profiledForCurrentSet;
	}

	/**
	 * Profiles the algorithm with the given test input set.
	 *
	 * @param numberOfTimeToTestEachInput
	 *            Is the number of time each element of the input set will be
	 *            tested.
	 * @throws NullPointerException
	 *             If the test input set is not defined.
	 */
	public void performTest(int numberOfTimeToTestEachInput)
	{
		if (inputSet == null)
		{
			throw new NullPointerException("Test set is not defined");
		}

		lastTestRepetition = numberOfTimeToTestEachInput;
		clearPreviousResults();

		T tmpCopy;
		Statistic tmpStat;
		long tmp;

		for (int i = 0; i < inputSet.size(); i++)
		{
			tmpStat = new Statistic();

			for (int k = 0; k < numberOfTimeToTestEachInput; k++)
			{
				tmpCopy = copyProducer.apply(inputSet.get(i));

				tmp = System.nanoTime();
				algorithm.accept(tmpCopy);
				tmp = System.nanoTime() - tmp;

				tmpStat.appendNumber(tmp);
				globalStatistics.appendNumber(tmp);
			}

			results.add(tmpStat);
		}

		profiledForCurrentSet = true;
	}

	/**
	 * Profiles the algorithm with an input set generated on the fly. This
	 * method generates the test input set to be used to profile the algorithm
	 * by
	 * using the {@code inputProducer} function provided. This method will call
	 * the {@code inputProducer}
	 * function {@code max-min} times. At each call, an increasing counter will
	 * be passed to the function.
	 * For example, the first call will receive as a parameter @{min}, the
	 * second @{min + 1} and so on
	 * until {@code max} is reached. This method doesn't change
	 * {@code isCurrentSetAlreadyProfiled()}.
	 *
	 * @param numberOfTimeToTestEachInput
	 *            Is the number of time each element of the input set will be
	 *            tested.
	 * @param min
	 *            The lower bound of the interval to be used to generate the
	 *            input set.
	 * @param max
	 *            The upper bound of the interval to be used to generate the
	 *            input set.
	 * @throws NullPointerException
	 *             If the test input set is not defined.
	 * @throws IllegalArgumentException
	 *             When the {@code min} is greater then {@code max}.
	 */
	public void performTest(int numberOfTimeToTestEachInput, int min, int max)
	{
		if (inputProducer == null)
		{
			throw new NullPointerException("Input generator not defined");
		}
		if (min > max)
		{
			throw new IllegalArgumentException("Min should be lower or equal than max");
		}

		lastTestRepetition = numberOfTimeToTestEachInput;
		clearPreviousResults();

		T tmpCopy;
		Statistic tmpStat;
		T currentInputTest;
		long tmp;

		for (int i = min; i <= max; i++)
		{
			tmpStat = new Statistic();
			currentInputTest = inputProducer.apply(i);

			for (int k = 0; k < numberOfTimeToTestEachInput; k++)
			{
				tmpCopy = copyProducer.apply(currentInputTest);

				tmp = System.nanoTime();
				algorithm.accept(tmpCopy);
				tmp = System.nanoTime() - tmp;

				tmpStat.appendNumber(tmp);
				globalStatistics.appendNumber(tmp);
			}

			results.add(tmpStat);
		}
	}

	/**
	 * Returns a data series containing the test number on the first dimension
	 * and the average time on the second dimension.
	 *
	 * @return A {@code PlotDataSeries<Long>} containing two dimension: the test
	 *         number and the average time for that test. Useful for data
	 *         plotting on graphs.
	 */
	public PlotDataSeries<Long> resultAsDataSeries()
	{
		PlotDataSeries<Long> ans = new PlotDataSeries<Long>();

		Long[] row;
		for (int i = 0; i < results.size(); i++)
		{
			row = new Long[2];
			row[0] = (long) (i + 1);
			row[1] = results.get(i).getAverage();
			ans.addRow(row);
		}

		return ans;
	}

	/**
	 * Returns the results details as a data table.
	 *
	 * @return A an object of type {@code DataTable<Long>} containing the
	 *         results details.
	 */
	public DataTable<Long> resultDetailsToDataTable()
	{
		// +2 allows to add the "Test number" column and the "Average" column
		String[] headings = new String[lastTestRepetition + 2];

		headings[0] = "N";
		headings[lastTestRepetition + 1] = "Average [ns]";

		for (int i = 0; i < lastTestRepetition; i++)
		{
			headings[i + 1] = "T " + i + 1;
		}

		DataTable<Long> ans = new DataTable<>(headings);

		Long[] row;
		for (int i = 0; i < results.size(); i++)
		{
			row = new Long[headings.length];
			row[0] = (long) (i + 1);
			for (int k = 1; k < lastTestRepetition; k++)
			{
				row[k] = results.get(i).getNumberAt(k - 1);
			}
			row[lastTestRepetition + 1] = results.get(i).getAverage();
			ans.addRow(row);
		}

		return ans;
	}

	/**
	 * Returns the results summary as a data table.
	 *
	 * @return A an object of type {@code DataTable<Long>} containing the
	 *         results summary.
	 */
	public DataTable<Long> resultSummaryToDataTable()
	{
		DataTable<Long> ans = new DataTable<>(4);
		ans.setHeadings("Test number", "Average (" + lastTestRepetition + " attempts) [ns]",
				"St. dev. (" + lastTestRepetition + " attempts) [ns]",
				"Median (" + lastTestRepetition + " attempts) [ns]");

		Long[] row;
		for (int i = 0; i < results.size(); i++)
		{
			row = new Long[4];
			row[0] = (long) (i + 1);
			row[1] = results.get(i).getAverage();
			row[2] = results.get(i).getStDev();
			row[3] = results.get(i).getMedian();
			ans.addRow(row);
		}

		return ans;
	}

	/**
	 * This method allows to update the test input set to be used to profile the
	 * algorithm by
	 * using the {@code inputProducer} function provided. This method will call
	 * the {@code inputProducer}
	 * function {@code max-min} times. At each call, an increasing counter will
	 * be passed to the function.
	 * For example, the first call will receive as a parameter @{min}, the
	 * second @{min + 1} and so on
	 * until {@code max} is reached.
	 *
	 * @param min
	 *            The lower bound of the interval to be used to generate the
	 *            input set.
	 * @param max
	 *            The upper bound of the interval to be used to generate the
	 *            input set.
	 * @throws NullPointerException
	 *             When the {@code inputProducer} function is not defined.
	 * @throws IllegalArgumentException
	 *             When the {@code min} is greater then {@code max}.
	 */
	public void updateInputSet(int min, int max)
	{
		if (inputProducer == null)
		{
			throw new NullPointerException("Input generator not defined");
		}
		if (min > max)
		{
			throw new IllegalArgumentException("Min should be lower or equal than max");
		}

		List<T> tmp = new ArrayList<T>();

		for (int i = min; i <= max; i++)
		{
			tmp.add(inputProducer.apply(i));
		}

		updateInputSet(tmp);
	}

	/**
	 * This method allows to update the test input set to be used to profile the
	 * algorithm.
	 * It also resets any previously computed result and/or statistic.
	 *
	 * @param newInputSet
	 *            The new input set that will be used to profile the algorithm
	 */
	public void updateInputSet(List<T> newInputSet)
	{
		profiledForCurrentSet = false;
		inputSet = newInputSet;

		clearPreviousResults();
	}

}
