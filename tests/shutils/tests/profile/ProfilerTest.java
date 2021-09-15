package shutils.tests.profile;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import shutils.profile.Profiler;

public class ProfilerTest
{

	@Test
	public void testCleanAllResultStatistics_00()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.cleanGlobalStatistics();
	}

	@Test
	public void testCleanAllResultStatistics_01()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.cleanGlobalStatistics();
	}

	@Test
	public void testCleanGlobalStatistics_00()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.cleanGlobalStatistics();
	}

	@Test
	public void testCleanGlobalStatistics_01()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.cleanGlobalStatistics();
	}

	@Test
	public void testGetGlobalStatistics()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.getGlobalStatistics();
	}

	@Test
	public void testGetResultStatistics_00()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.getResultStatistics(2);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetResultStatistics_01()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.getResultStatistics(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetResultStatistics_02()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.getResultStatistics(23);
	}

	@Test
	public void testGlobalResultsToDataTable()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.globalResultsToDataTable();
	}

	@Test
	public void testIsCurrentSetAlreadyProfiled_00()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);

		assertEquals(false, p.isCurrentSetAlreadyProfiled());
	}

	@Test
	public void testIsCurrentSetAlreadyProfiled_01()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.updateInputSet(1, 10);
		p.performTest(1);
		assertEquals(true, p.isCurrentSetAlreadyProfiled());
	}

	@Test
	public void testPerformTestInt()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.updateInputSet(1, 5);
		p.performTest(5);
	}

	@Test
	public void testPerformTestIntIntInt()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
	}

	@Test
	public void testProfilerConsumerOfTFunctionOfIntegerTFunctionOfTT()
	{
		@SuppressWarnings("unused")
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
	}

	@Test
	public void testProfilerConsumerOfTListOfTFunctionOfTT()
	{
		ArrayList<Integer> test = new ArrayList<>();
		@SuppressWarnings("unused")
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, test, (i) -> i);
	}

	@Test
	public void testResultAsDataSeries()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.resultAsDataSeries();
	}

	@Test
	public void testResultDetailsToDataTable()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.resultDetailsToDataTable();
	}

	@Test
	public void testResultSummaryToDataTable()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.performTest(1, 1, 10);
		p.resultSummaryToDataTable();
	}

	@Test
	public void testUpdateInputSetIntInt_00()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.updateInputSet(1, 5);
		assertEquals(false, p.isCurrentSetAlreadyProfiled());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateInputSetIntInt_01()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		p.updateInputSet(6, 5);
		assertEquals(false, p.isCurrentSetAlreadyProfiled());
	}

	@Test
	public void testUpdateInputSetListOfT()
	{
		Profiler<Integer> p = new Profiler<Integer>(i -> {
		}, i -> i, i -> i);
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(5);
		a.add(6);
		p.updateInputSet(a);
		assertEquals(false, p.isCurrentSetAlreadyProfiled());
	}

}
