package shutils.tests.data.visualization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import shutils.data.visualization.DataTable;

/**
 * Test cases for the shutils.data.visualization.DataTable class
 *
 * @author Matteo Nardini
 *
 */
public class DataTableTests
{

	@Test
	public void testAddRow_00()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);

		assertEquals("Test 1;Test 2;Test 3\n1;2;3", t.toString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddRow_01()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3, 4 };
		t.addRow(a);

	}

	@Test
	public void testClear()
	{
		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);

		t.clear();

		assertEquals(t.getRowCount(false) == 0, true);
	}

	@Test
	public void testGetColumnNumber_00()
	{
		DataTable<Integer> d = new DataTable<>(3);
		assertEquals(d.getColumnNumber(), 3);
	}

	@Test
	public void testGetHeadings_00()
	{

		DataTable<Integer> t = new DataTable<>(3);

		assertEquals(true, t.getHeadings() == null);

	}

	@Test
	public void testGetHeadings_01()
	{

		DataTable<Integer> t = new DataTable<>("Test", "Test 2");

		boolean same = true;
		String[] h = t.getHeadings();
		String[] a = { "Test", "Test 2" };
		for (int i = 0; i < h.length; i++)
		{
			if (!h[i].equals(a[i]))
			{
				same = false;
			}
		}

		assertEquals(true, same);

	}

	@Test
	public void testGetHeadings_02()
	{

		DataTable<Integer> t = new DataTable<>("");

		assertEquals(true, (t.getHeadings().length == 1) && t.getHeadings()[0].equals(""));

	}

	@Test
	public void testGetRow_00()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);
		Integer[] b = { 4, 5, 6 };
		t.addRow(b);

		Integer[] c = t.getRow(1);

		for (int i = 0; i < c.length; i++)
		{
			if (c[i] != b[i])
			{
				fail("The wrong line was returned");
			}
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetRow_01()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);
		t.getRow(6);
	}

	@Test
	public void testGetRowCount_00()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(3, d.getRowCount());

	}

	@Test
	public void testGetRowCount_01()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(3, d.getRowCount(true));

	}

	@Test
	public void testGetRowCount_02()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(2, d.getRowCount(false));

	}

	@Test
	public void testGetRowCount_03()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		assertEquals(1, d.getRowCount(true));

	}

	@Test
	public void testGetRowCount_04()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(2, d.getRowCount(true));

	}

	@Test
	public void testGetRowCount_05()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(2, d.getRowCount(false));

	}

	@Test
	public void testGetRowCount_06()
	{

		DataTable<Integer> d = new DataTable<>(3);

		assertEquals(0, d.getRowCount(true));

	}

	@Test
	public void testRemoveRowAtIndex_00()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);
		t.addRow(a);
		t.removeRowAtIndex(1);

		assertEquals("Test 1;Test 2;Test 3\n1;2;3", t.toString());

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveRowAtIndex_01()
	{

		DataTable<Integer> t = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		t.addRow(a);
		t.removeRowAtIndex(6);
	}

	@Test
	public void testSetHeadings_00()
	{

		DataTable<Integer> t = new DataTable<>(2);

		t.setHeadings("Test", "Test 2");

		boolean same = true;
		String[] h = t.getHeadings();
		String[] a = { "Test", "Test 2" };
		for (int i = 0; i < h.length; i++)
		{
			if (!h[i].equals(a[i]))
			{
				same = false;
			}
		}

		assertEquals(true, same);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHeadings_01()
	{

		DataTable<Integer> t = new DataTable<>(2);

		t.setHeadings("Test", "Test 2", "Test 3");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHeadings_02()
	{

		DataTable<Integer> t = new DataTable<>(2);

		t.setHeadings("Test");

	}

	@Test
	public void testToCSVTableString_00()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("Test 1;Test 2;Test 3\n1;2;3\n1;2;3", d.toCSVTableString(true, ";"));

	}

	@Test
	public void testToCSVTableString_01()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1;2;3\n1;2;3", d.toCSVTableString(true, ";"));

	}

	@Test
	public void testToCSVTableString_02()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("Test 1 ; Test 2 ; Test 3\n1 ; 2 ; 3\n1 ; 2 ; 3", d.toCSVTableString(true, " ; "));

	}

	@Test
	public void testToCSVTableString_03()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1 ; 2 ; 3\n1 ; 2 ; 3", d.toCSVTableString(true, " ; "));

	}

	@Test
	public void testToCSVTableString_04()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1;2;3\n1;2;3", d.toCSVTableString(false, ";"));

	}

	@Test
	public void testToCSVTableString_05()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1;2;3\n1;2;3", d.toCSVTableString(false, ";"));

	}

	@Test
	public void testToCSVTableString_06()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1 ; 2 ; 3\n1 ; 2 ; 3", d.toCSVTableString(false, " ; "));

	}

	@Test
	public void testToCSVTableString_07()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("1 ; 2 ; 3\n1 ; 2 ; 3", d.toCSVTableString(false, " ; "));

	}

	@Test
	public void testToCSVTableString_08()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		assertEquals("Test 1;Test 2;Test 3", d.toCSVTableString(true, ";"));

	}

	@Test
	public void testToCSVTableString_09()
	{

		DataTable<Integer> d = new DataTable<>(3);

		assertEquals("", d.toCSVTableString(true, ";"));

	}

	@Test
	public void testToCSVTableString_10()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		Integer[] b = { 4, 5, 6 };
		d.addRow(b);
		Integer[] c = { 1, 5, 6 };
		d.addRow(c);

		assertEquals("1 ; 2 ; 3\n1 ; 5 ; 6", d.toCSVTableString(false, " ; ", (i) -> i[0] == 1));

	}

	@Test
	public void testToHTMLTableString_00()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("<table><tr><td>Test 1</td><td>Test 2</td><td>Test 3</td></tr><tr><td>1</td><td>2</td>"
				+ "<td>3</td></tr><tr><td>1</td><td>2</td><td>3</table>", d.toHTMLTableString(true));

	}

	@Test
	public void testToHTMLTableString_01()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("<table><tr><td>1</td><td>2</td><td>3</td></tr>" + "<tr><td>1</td><td>2</td><td>3</table>",
				d.toHTMLTableString(true));

	}

	@Test
	public void testToHTMLTableString_02()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("<table><tr><td>1</td><td>2</td><td>3</td></tr>" + "<tr><td>1</td><td>2</td><td>3</table>",
				d.toHTMLTableString(false));

	}

	@Test
	public void testToHTMLTableString_03()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("<table><tr><td>1</td><td>2</td><td>3</td></tr>" + "<tr><td>1</td><td>2</td><td>3</table>",
				d.toHTMLTableString(false));

	}

	@Test
	public void testToHTMLTableString_04()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		assertEquals("<table><tr><td>Test 1</td><td>Test 2</td><td>Test 3</table>", d.toHTMLTableString(true));

	}

	@Test
	public void testToHTMLTableString_05()
	{

		DataTable<Integer> d = new DataTable<>(3);

		assertEquals("", d.toHTMLTableString(true));

	}

	@Test
	public void testToHTMLTableString_06()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		Integer[] b = { 4, 5, 6 };
		d.addRow(b);
		Integer[] c = { 1, 5, 6 };
		d.addRow(c);

		assertEquals(
				"<table><tr><td>Test 1</td><td>Test 2</td><td>Test 3</td></tr><tr><td>1</td><td>2</td>"
						+ "<td>3</td></tr><tr><td>1</td><td>5</td><td>6</table>",
				d.toHTMLTableString(true, i -> i[0] == 1));

	}

	@Test
	public void testToLatexTableString_00()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals(
				"\\begin{center}{\\renewcommand{\\arraystretch}{1.5}\\renewcommand{\\tabcolsep}"
						+ "{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline Test 1 & Test 2 & Test 3\\\\ \\hline 1 & 2 & 3"
						+ "\\\\ \\hline 1 & 2 & 3\\\\ \\hline \\end{tabular}}\\end{center}",
				d.toLatexTableString(true));

	}

	@Test
	public void testToLatexTableString_01()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("\\begin{center}{\\renewcommand{\\arraystretch}{1.5}\\renewcommand{\\tabcolsep}"
				+ "{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline 1 & 2 & 3\\\\ \\hline 1 & 2 & 3\\\\ \\hline "
				+ "\\end{tabular}}\\end{center}", d.toLatexTableString(true));

	}

	@Test
	public void testToLatexTableString_02()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("\\begin{center}{\\renewcommand{\\arraystretch}{1.5}\\renewcommand{\\tabcolsep}"
				+ "{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline 1 & 2 & 3\\\\ \\hline 1 & 2 & 3\\\\ \\hline "
				+ "\\end{tabular}}\\end{center}", d.toLatexTableString(false));

	}

	@Test
	public void testToLatexTableString_03()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("\\begin{center}{\\renewcommand{\\arraystretch}{1.5}\\renewcommand{\\tabcolsep}"
				+ "{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline 1 & 2 & 3\\\\ \\hline 1 & 2 & 3\\\\ \\hline "
				+ "\\end{tabular}}\\end{center}", d.toLatexTableString(false));

	}

	@Test
	public void testToLatexTableString_04()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		assertEquals("\\begin{center}{\\renewcommand{\\arraystretch}{1.5}"
				+ "\\renewcommand{\\tabcolsep}{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline Test "
				+ "1 & Test 2 & Test 3\\\\ \\hline \\end{tabular}}\\end{center}", d.toLatexTableString(true));

	}

	@Test
	public void testToLatexTableString_05()
	{

		DataTable<Integer> d = new DataTable<>(3);

		assertEquals("", d.toLatexTableString(true));

	}

	@Test
	public void testToLatexTableString_06()
	{

		DataTable<Integer> d = new DataTable<>(3);

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		Integer[] b = { 4, 5, 6 };
		d.addRow(b);
		Integer[] c = { 1, 5, 6 };
		d.addRow(c);

		assertEquals("\\begin{center}{\\renewcommand{\\arraystretch}{1.5}\\renewcommand{\\tabcolsep}"
				+ "{0.2cm}\\begin{tabular}{|c|c|c|c|}\\hline 1 & 2 & 3\\\\ \\hline 1 & 5 & 6\\\\ \\hline "
				+ "\\end{tabular}}\\end{center}", d.toLatexTableString(false, i -> i[0] == 1));

	}

	@Test
	public void testToMatlabMatrixString_00()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		d.addRow(a);

		assertEquals("Test = [1 2 3;1 2 3];", d.toMatlabMatrixString("Test"));

	}

	@Test
	public void testToMatlabMatrixString_01()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		assertEquals("Test = [];", d.toMatlabMatrixString("Test"));

	}

	@Test
	public void testToMatlabMatrixString_02()
	{

		DataTable<Integer> d = new DataTable<>(3);

		assertEquals("Test = [];", d.toMatlabMatrixString("Test"));

	}

	@Test
	public void testToMatlabMatrixString_03()
	{

		DataTable<Integer> d = new DataTable<>("Test 1", "Test 2", "Test 3");

		Integer[] a = { 1, 2, 3 };
		d.addRow(a);
		Integer[] b = { 4, 5, 6 };
		d.addRow(b);
		Integer[] c = { 1, 5, 6 };
		d.addRow(c);

		assertEquals("Test = [1 2 3;1 5 6];", d.toMatlabMatrixString("Test", i -> i[0] == 1));

	}

}
