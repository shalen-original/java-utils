package shutils.tests.profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import shutils.profile.Statistic;

/**
 * Test cases for the shutils.profile.Statistic class
 *
 * @author Matteo Nardini
 *
 */
public class StatisticTest
{

	Statistic s;
	Long[] values = { (long) 1234, (long) 4738, (long) 7942, (long) 6732, (long) 7963 };

	@Before
	public void beforeEach()
	{
		if (s == null)
		{
			s = new Statistic();
		}

		if (s.getNumberCount() == 0)
		{
			Arrays.asList(values).forEach(i -> s.appendNumber(i));
		}
	}

	@Test
	public void testAppendNumber_00()
	{
		s.appendNumber((long) 1000);
	}

	@Test
	public void testClean_00()
	{

		s.appendNumber((long) 999999999);
		s.appendNumber((long) -999999999);

		s.clean(1);

		for (int i = 0; i < s.getNumberCount(); i++)
		{
			if ((s.getNumberAt(i) == 999999999) || (s.getNumberAt(i) == -999999999))
			{
				fail("The method didn't remove the extreme values");
			}
		}

	}

	@Test
	public void testClean_01()
	{
		Statistic t = new Statistic();
		t.clean();
	}

	@Test
	public void testClearResults_00()
	{
		s.clearResults();
		assertEquals(true, s.getNumberCount() == 0);
	}

	@Test
	public void testGetAverage_00()
	{
		assertEquals((long) 5721, (long) s.getAverage());
	}

	@Test
	public void testGetAverage_01()
	{
		s.clearResults();
		assertEquals((long) 0, (long) s.getAverage());
	}

	@Test
	public void testGetMedian_00()
	{
		assertEquals((long) 6732, (long) s.getMedian());

	}

	@Test
	public void testGetMedian_01()
	{
		s.clearResults();
		assertEquals((long) 0, (long) s.getMedian());

	}

	@Test
	public void testGetNumberAt_00()
	{
		assertEquals(true, s.getNumberAt(0) == 1234);
	}

	@Test
	public void testGetNumberCount_00()
	{
		assertEquals(true, s.getNumberCount() == 5);
	}

	@Test
	public void testGetStDev_00()
	{
		assertEquals((long) 2533, (long) s.getStDev());

	}

	@Test
	public void testGetStDev_01()
	{
		s.clearResults();
		assertEquals((long) 0, (long) s.getStDev());

	}

	@Test
	public void testStatistic_00()
	{
		@SuppressWarnings("unused")
		Statistic v = new Statistic();
	}

}
