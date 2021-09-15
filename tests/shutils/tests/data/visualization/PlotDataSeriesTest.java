package shutils.tests.data.visualization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import shutils.data.visualization.PlotDataSeries;
import shutils.data.visualization.SeriesDimensions;

public class PlotDataSeriesTest
{

	@Test
	public void testAddRow_00()
	{
		PlotDataSeries<Integer> a = new PlotDataSeries<>();

		Integer[] b = { 1, 2 };
		a.addRow(b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRow_01()
	{
		PlotDataSeries<Integer> a = new PlotDataSeries<>("test", SeriesDimensions.THREE_DIMENSIONAL_SERIES);

		Integer[] b = { 1, 2, 3, 4 };
		a.addRow(b);
	}

	@Test
	public void testFillWithData_00()
	{

		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		Integer[] x = { 1, 2, 3, 4 };
		Integer[] y = { 3, 2, 6, 1 };

		ArrayList<Integer[]> c = new ArrayList<>();
		c.add(x);
		c.add(y);

		b.fillWithData(c, Integer.class);

		for (int i = 0; i < x.length; i++)
		{
			if ((b.getData().getRow(i)[0] != x[i]) || (b.getData().getRow(i)[1] != y[i]))
			{
				fail("The data is not the same");
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFillWithData_01()
	{

		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		Integer[] x = { 1, 2, 3, 4, 6 };
		Integer[] y = { 3, 2, 6, 1 };

		ArrayList<Integer[]> c = new ArrayList<>();
		c.add(x);
		c.add(y);

		b.fillWithData(c, Integer.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFillWithData_02()
	{

		PlotDataSeries<Integer> b = new PlotDataSeries<>();
		Integer[] x = { 1, 2, 3, 4 };
		Integer[] y = { 3, 2, 6, 1 };
		Integer[] z = { 3, 2, 6, 1 };

		ArrayList<Integer[]> c = new ArrayList<>();
		c.add(x);
		c.add(y);
		c.add(z);

		b.fillWithData(c, Integer.class);
	}

	@Test
	public void testGetAxisData()
	{
		PlotDataSeries<Integer> a = new PlotDataSeries<>();

		Integer[] b = { 1, 2 };
		a.addRow(b);

		Integer[] c = { 3, 4 };
		a.addRow(c);

		Integer[] ans = { 1, 3 };

		ArrayList<Integer> d = a.getAxisData(0);
		for (int i = 0; i < a.getData().getRowCount(); i++)
		{
			if (d.get(i) != ans[i])
			{
				fail("The wrong data has been returned");
			}
		}
	}

	@Test
	public void testGetSeriesName()
	{
		PlotDataSeries<Integer> a = new PlotDataSeries<>("test");

		assertEquals("test", a.getSeriesName());
	}

	@Test
	public void testPlotDataSeries()
	{
		@SuppressWarnings("unused")
		PlotDataSeries<Integer> a = new PlotDataSeries<>();
	}

	@Test
	public void testPlotDataSeriesString()
	{
		@SuppressWarnings("unused")
		PlotDataSeries<Integer> a = new PlotDataSeries<>("test");
	}

	@Test
	public void testPlotDataSeriesStringSeriesDimensions()
	{
		@SuppressWarnings("unused")
		PlotDataSeries<Integer> a = new PlotDataSeries<>("test", SeriesDimensions.THREE_DIMENSIONAL_SERIES);
	}

	@Test
	public void testSetSeriesName()
	{
		PlotDataSeries<Integer> a = new PlotDataSeries<>();

		a.setSeriesName("testw");

		assertEquals("testw", a.getSeriesName());
	}

}
