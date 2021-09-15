package shutils.data.visualization;

/**
 * Represents the number of dimensions of a data series.
 *
 * @author Matteo Nardini
 */
public enum SeriesDimensions
{
	TWO_DIMENSIONAL_SERIES(2), THREE_DIMENSIONAL_SERIES(3);

	int dim;

	private SeriesDimensions(int dim)
	{
		this.dim = dim;
	}

	/**
	 * Returns the dimension of the series as an integer number.
	 *
	 * @return The number of dimensions of the current series.
	 */
	public int getDimensionNumber()
	{
		return dim;
	}
}