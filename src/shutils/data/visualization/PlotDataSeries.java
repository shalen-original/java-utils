package shutils.data.visualization;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents a data series for a plot.
 *
 * @author Matteo Nardini
 * @param <T>
 *            The type of data contained in this series.
 */
public class PlotDataSeries<T extends Number>
{

	DataTable<T> data;
	String seriesName;

	/**
	 * Creates a new two-dimensional data series without name.
	 */
	public PlotDataSeries()
	{
		this("");
	}

	/**
	 * Creates a new two-dimensional data series with a name.
	 *
	 * @param seriesName
	 *            The name of the series.
	 */
	public PlotDataSeries(String seriesName)
	{
		this(seriesName, SeriesDimensions.TWO_DIMENSIONAL_SERIES);
	}

	/**
	 * Creates a new data series with the given name and number of dimensions.
	 *
	 * @param seriesName
	 *            The name of the series.
	 * @param dim
	 *            The number of dimensions of the series.
	 */
	public PlotDataSeries(String seriesName, SeriesDimensions dim)
	{
		this.seriesName = seriesName;

		data = new DataTable<T>(dim.getDimensionNumber());
	}

	public void addRow(T[] row)
	{
		// Simple shortcut for getData().addRow(T[] row)
		data.addRow(row);
	}

	/**
	 * Allows to fill the entire graph on a "per-dimension" basis. Instead of
	 * adding the data point to point to the graph,
	 * this methods takes {@code n} arrays, each of which represents a dimension
	 * of the series and contains all the data for that dimension.
	 * Therefore, the {@code i}-th point of the plot will have its dimension one
	 * value in the first array, its dimension two value in the second array and
	 * so on.
	 *
	 * @param newData
	 *            The list of all the {@code n} arrays.
	 * @param type
	 *            The type of the objects to be added to the data table.
	 */
	@SuppressWarnings("unchecked")
	public void fillWithData(ArrayList<T[]> newData, Class<T> type)
	{
		if (newData.size() != data.getColumnNumber())
		{
			throw new IllegalArgumentException(
					"The number of elements in each row of the new data has to be equal to the number of dimensions of this series.");
		}

		if (newData.size() == 0)
		{
			data.clear();
			return;
		}

		int lenFirst = newData.get(0).length;

		for (int i = 0; i < data.getColumnNumber(); i++)
		{
			if (newData.get(i).length != lenFirst)
			{
				throw new IllegalArgumentException("The number of values in all the dimensions must be the same");
			}
		}

		T[] a;

		for (int i = 0; i < lenFirst; i++)
		{
			a = (T[]) Array.newInstance(type, data.getColumnNumber());

			for (int j = 0; j < data.getColumnNumber(); j++)
			{
				a[j] = newData.get(j)[i];
			}
			addRow(a);
		}
	}

	/**
	 * Returns all the data contained in a dimension of the current series as a
	 * list.
	 *
	 * @param dimension
	 *            The dimension which data have to be extracted.
	 * @return The list of all the data contained in the {@code dimension}
	 *         dimension of the current series.
	 */
	public ArrayList<T> getAxisData(int dimension)
	{
		ArrayList<T> ans = new ArrayList<T>();

		for (int i = 0; i < data.getRowCount(false); i++)
		{
			ans.add(data.getRow(i)[dimension]);
		}

		return ans;
	}

	/**
	 * Returns the DataTable object containing the data of this series.
	 *
	 * @return The DataTable object containing the data of this series.
	 */
	public DataTable<T> getData()
	{
		return data;
	}

	/**
	 * Returns the name of the current series.
	 *
	 * @return The name of the current series.
	 */
	public String getSeriesName()
	{
		return seriesName;
	}

	/**
	 * Sets the name of the current series.
	 *
	 * @param seriesName
	 *            The new name of the current series.
	 */
	public void setSeriesName(String seriesName)
	{
		this.seriesName = seriesName;
	}

}
