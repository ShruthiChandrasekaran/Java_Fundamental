package fr.epita.Services;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.*;

public class TableBuilder {

	/**
	 * Linked list for rows
	 */
	List<String[]> rows = new LinkedList<String[]>();
	
	/**
	 * Add a row in table
	 * @param cols Number of columns in table
	 */
	public void addRow(String... cols) {
		rows.add(cols);
	}
	
	/**
	 * Set the column width for the table
	 * @return width of column
	 */
	private int[] colWidths() {
		int cols= -1;
		
		for (String[] row: rows)
			cols = Math.max(cols, row.length);
		
		int[] widths = new int[cols];
		
		for(String[] row: rows) {
			for(int colNum =0; colNum < row.length; colNum++) {
				widths[colNum] =
						Math.max(widths[colNum], StringUtils.length(row[colNum]));
			}
		}
		return widths;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		int[] colWidths = colWidths();
		
		for(String[] row: rows) {
			for(int colNum = 0; colNum < row.length; colNum++) {
				buf.append(
						StringUtils.rightPad(
								StringUtils.defaultString(
										row[colNum]), colWidths[colNum]));
				buf.append(' ');
			}
			buf.append('\n');
		}
		return buf.toString();
	}
}
