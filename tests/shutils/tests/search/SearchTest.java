package shutils.tests.search;

import static org.junit.Assert.*;

import org.junit.Test;

import shutils.search.Search;

public class SearchTest
{
	
	@Test
	public void linearSearch_00()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.linearSearch(A, 2), 1);
	}
	
	@Test
	public void linearSearch_01()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.linearSearch(A, 7), -1);
	}
	
	@Test
	public void linearSearch_02()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.linearSearch(A, 1), 0);
	}
	
	@Test
	public void linearSearch_03()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.linearSearch(A, 6), 5);
	}
	
	
	@Test
	public void binarySearch_00()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.binarySearch(A, 2), 1);
	}
	
	@Test
	public void binarySearch_01()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.binarySearch(A, 7), -1);
	}
	
	@Test
	public void binarySearch_02()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.binarySearch(A, 1), 0);
	}
	
	@Test
	public void binarySearch_03()
	{
		Integer[] A = {1, 2, 3, 4, 5, 6};
		assertEquals(Search.binarySearch(A, 6), 5);
	}

}
