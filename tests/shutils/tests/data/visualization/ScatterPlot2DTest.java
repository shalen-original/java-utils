package shutils.tests.data.visualization;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import shutils.data.visualization.PlotDataSeries;
import shutils.data.visualization.ScatterPlot2D;

public class ScatterPlot2DTest
{

	@Test
	public void testAddDataSeries()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		a.addDataSeries(b);
	}

	@Test
	public void testGetDataSeries()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		a.addDataSeries(b);

		assertEquals(a.getDataSeries(0).equals(b), true);
	}

	@Test
	public void testGetDataSeriesCount()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		a.addDataSeries(b);

		assertEquals(1, a.getDataSeriesCount());
	}

	@Test
	public void testGetTitle()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");

		assertEquals("test", a.getTitle());
	}

	@Test
	public void testGetxAxisLabel()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");

		assertEquals("testx", a.getXAxisLabel());
	}

	@Test
	public void testGetyAxisLabel()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");

		assertEquals("testy", a.getYAxisLabel());
	}

	@Test
	public void testPlotToLatexPGFPlotString()
	{

		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "$x$", "$x^2$");

		PlotDataSeries<Integer> b = new PlotDataSeries<>("series 1");
		Integer[] x = { 1, 2, 3, 4 };
		Integer[] y = { 3, 2, 6, 1 };
		ArrayList<Integer[]> c = new ArrayList<>();
		c.add(x);
		c.add(y);
		b.fillWithData(c, Integer.class);

		a.addDataSeries(b);

		PlotDataSeries<Integer> b1 = new PlotDataSeries<>("series 2");
		Integer[] x1 = { 1, 2, 3, 4 };
		Integer[] y1 = { 5, 1, 3, 8 };
		ArrayList<Integer[]> c1 = new ArrayList<>();
		c1.add(x1);
		c1.add(y1);
		b1.fillWithData(c1, Integer.class);

		a.addDataSeries(b1);

		String expected = "% Hey, remember to add these on top of the document:\n" + "%\t \\usepackage{pgfplots}\n"
				+ "%\t \\pgfplotsset{compat=1.6}\n" + "\\begin{tikzpicture}\\begin{axis}[\n" + "\t title=test,\n"
				+ "\t xlabel=$x$,\n" + "\t ylabel=$x^2$,\n" + "\t %xmin=-2 , xmax=2,\n" + "\t %ymin=-2 , ymax=2,\n"
				+ "\t %minor x tick num=1,\n" + "\t %minor y tick num=1,\n" + "\t grid=major,\n"
				+ "\t width=\\textwidth,\n" + "\t legend pos=north west,\n" + "\t legend entries = {series 1,series 2,},\n"
				+ "]\n\\addplot coordinates{(1,3)(2,2)(3,6)(4,1)};\\addplot coordinates{(1,5)(2,1)(3,3)(4,8)};\\end{axis}\\end{tikzpicture}";

		assertEquals(expected, a.plotToLatexPGFPlotString());

	}

	@Test
	public void testRemoveDataSeriesAtIndex()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		a.addDataSeries(b);
		a.removeDataSeriesAtIndex(0);
	}

	@Test
	public void testScatterPlot2D()
	{
		@SuppressWarnings("unused")
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>();
	}

	@Test
	public void testScatterPlot2DString()
	{
		@SuppressWarnings("unused")
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test");
	}

	@Test
	public void testScatterPlot2DStringStringString()
	{
		@SuppressWarnings("unused")
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
	}

	@Test
	public void testSetTitle()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		a.setTitle("test2");
		assertEquals("test2", a.getTitle());
	}

	@Test
	public void testSetxAxisLabel()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		a.setXAxisLabel("test2");
		assertEquals("test2", a.getXAxisLabel());
	}

	@Test
	public void testSetyAxisLabel()
	{
		ScatterPlot2D<Integer> a = new ScatterPlot2D<Integer>("test", "testx", "testy");
		a.setYAxisLabel("test2");
		assertEquals("test2", a.getYAxisLabel());
	}

}
