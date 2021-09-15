package shutils.data.visualization;

import java.util.ArrayList;

/**
 * This class represents a generic 2D scatter plot
 *
 * @author Matteo Nardini
 *
 * @param <T>
 *            The type of data to be represented
 */
public class ScatterPlot2D<T extends Number>
{

	private String title;
	private String xAxisLabel;
	private String yAxisLabel;

	ArrayList<PlotDataSeries<T>> series;

	/**
	 * Creates a new 2D scatter plot without title and without axis labels.
	 */
	public ScatterPlot2D()
	{
		this("");
	}

	/**
	 * Create a new 2D scatter plot with a title but without axis labels.
	 *
	 * @param title
	 *            The title of the new scatter plot.
	 */
	public ScatterPlot2D(String title)
	{
		this(title, "", "");
	}

	/**
	 * Create a new 2D scatter plot with a title but without axis labels.
	 *
	 * @param title
	 *            The title of the new scatter plot.
	 * @param xAxisLabel
	 *            The label of the x-axis of the new scatter plot.
	 * @param yAxisLabel
	 *            The label of the y-axis of the new scatter plot.
	 */
	public ScatterPlot2D(String title, String xAxisLabel, String yAxisLabel)
	{
		this.title = title;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;

		series = new ArrayList<PlotDataSeries<T>>();
	}

	/**
	 * Adds a new data series to the current scatter plot.
	 *
	 * @param newSeries
	 *            The new data series to be added to the current scatter plot.
	 */
	public void addDataSeries(PlotDataSeries<T> newSeries)
	{
		series.add(newSeries);
	}

	/**
	 * Returns a specific data series of the current scatter plot.
	 *
	 * @param i
	 *            The index of the data series to be returned.
	 * @return The data series at the {@code i-th} position.
	 */
	public PlotDataSeries<T> getDataSeries(int i)
	{
		return series.get(i);
	}

	/**
	 * Returns the total number of data series of the current scatter plot.
	 *
	 * @return The total number of data series of the current scatter plot.
	 */
	public int getDataSeriesCount()
	{
		return series.size();
	}

	/**
	 * Returns the title of the current scatter plot.
	 *
	 * @return The title of the current scatter plot.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the x-axis label of the current scatter plot.
	 *
	 * @return The x-axis label of the current scatter plot.
	 */
	public String getXAxisLabel()
	{
		return xAxisLabel;
	}

	/**
	 * Returns the y-axis label of the current scatter plot.
	 *
	 * @return The y-axis label of the current scatter plot.
	 */
	public String getYAxisLabel()
	{
		return yAxisLabel;
	}

	/**
	 * Converts this graph to a Latex string representation. The Latex code uses
	 * the plugin {@code pgfplots}.
	 *
	 * @return The string that draws the current scatter plot in a Latex
	 *         environment.
	 */
	public String plotToLatexPGFPlotString()
	{
		StringBuilder ans = new StringBuilder();

		ans.append("% Hey, remember to add these on top of the document:\n");
		ans.append("%\t \\usepackage{pgfplots}\n");
		ans.append("%\t \\pgfplotsset{compat=1.6}\n");

		ans.append("\\begin{tikzpicture}");

		ans.append("\\begin{axis}[\n");
		ans.append("\t title=" + this.title + ",\n");
		ans.append("\t xlabel=" + this.xAxisLabel + ",\n");
		ans.append("\t ylabel=" + this.yAxisLabel + ",\n");
		ans.append("\t %xmin=-2 , xmax=2,\n");
		ans.append("\t %ymin=-2 , ymax=2,\n");
		ans.append("\t %minor x tick num=1,\n");
		ans.append("\t %minor y tick num=1,\n");
		ans.append("\t grid=major,\n");
		ans.append("\t width=\\textwidth,\n");
		ans.append("\t legend pos=north west,\n");
		ans.append("\t legend entries = {");
		for (PlotDataSeries<T> s : series)
		{
			ans.append(s.getSeriesName() + ",");
		}
		ans.append("},\n");
		ans.append("]\n");

		for (PlotDataSeries<T> s : series)
		{
			ans.append("\\addplot coordinates{");

			for (int i = 0; i < s.getData().getRowCount(); i++)
			{
				ans.append("(");
				ans.append(s.getData().getRow(i)[0]);
				ans.append(",");
				ans.append(s.getData().getRow(i)[1]);
				ans.append(")");
			}

			ans.append("};");
		}

		ans.append("");

		ans.append("\\end{axis}");

		ans.append("\\end{tikzpicture}");

		return ans.toString();
	}

	/**
	 * Deletes a data series from the current scatter plot.
	 *
	 * @param i
	 *            The index of the data series to be deleted.
	 */
	public void removeDataSeriesAtIndex(int i)
	{
		series.remove(i);
	}

	/**
	 * Sets the title of the current scatter plot.
	 *
	 * @param title
	 *            The title of the current scatter plot.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Sets the x-axis label of the current scatter plot.
	 *
	 * @param xAxisLabel
	 *            The x-axis label of the current scatter plot.
	 */
	public void setXAxisLabel(String xAxisLabel)
	{
		this.xAxisLabel = xAxisLabel;
	}

	/**
	 * Sets the y-axis label of the current scatter plot.
	 *
	 * @param yAxisLabel
	 *            The y-axis label of the current scatter plot.
	 */
	public void setYAxisLabel(String yAxisLabel)
	{
		this.yAxisLabel = yAxisLabel;
	}

}
