package org.framework.resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelDataSetReader {

	final DataFormatter df = new DataFormatter();

	FileInputStream testCaseStream;
	FileInputStream testDataStream;
	Workbook testCaseworkbook;
	Workbook testDataworkbook;
	Sheet wbTestcase;
	Sheet wbTestdata;

	ArrayList<String> dummy = new ArrayList<String>();
	LinkedHashMap<String, ArrayList<String>> testCaseSet = new LinkedHashMap();
	LinkedHashMap<String, ArrayList<String>> dataSet = new LinkedHashMap();

	@Test
	public Sheet getExcelTestCase(String testCaseExcel) throws IOException{
		testCaseStream = new FileInputStream(new File(testCaseExcel));
		testCaseworkbook = new XSSFWorkbook(testCaseStream);
		wbTestcase = testCaseworkbook.getSheetAt(0);
		readExcelTestCase(wbTestcase);
		return wbTestcase;
	}

	@Test
	public Sheet getExcelTestData(String testDataExcel) throws IOException{
		testDataStream = new FileInputStream(new File(testDataExcel));
		testDataworkbook = new XSSFWorkbook(testDataStream);
		wbTestdata = testDataworkbook.getSheetAt(0);
		return wbTestdata;
	}


	public LinkedHashMap<String, ArrayList<String>> readExcelTestCase(Sheet wbTestcase) throws IOException{ 

		Iterator<Row> iterator = wbTestcase.iterator();
		boolean ignoreHeader = true;
		while (iterator.hasNext()) {

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			ArrayList testCasesToRun = new ArrayList();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue());
					testCasesToRun.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					testCasesToRun.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue());
					String valueAsString = df.formatCellValue(cell);

					testCasesToRun.add(valueAsString);
					break;
				}
			}
			testCaseSet.put((String) testCasesToRun.get(0), testCasesToRun);

		}
		testCaseSet.remove("TC_ID");
		System.out.println("testCaseSet is"+testCaseSet);
		return testCaseSet;
	}
	@Test
	public LinkedHashMap<String, ArrayList<String>> readExcelDataSet(Sheet wbTestData, int dataSetCounter) throws IOException{ 
		dataSetCounter = dataSetCounter+1;
		Iterator<Row> iterator = wbTestData.iterator();
		int tempdataSetCounter = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			ArrayList testCasesToRun = new ArrayList();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue());
					testCasesToRun.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					testCasesToRun.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue());
					String valueAsString = df.formatCellValue(cell);

					testCasesToRun.add(valueAsString);
					break;
				}
			}
			dataSet.put((String) testCasesToRun.get(0), testCasesToRun);

			tempdataSetCounter++;
			if(dataSetCounter==tempdataSetCounter)
				break;
		}
		
		Entry<String, ArrayList<String>> entry=dataSet.entrySet().iterator().next();
		String key= entry.getKey();
		dataSet.remove(key);

		System.out.println("dataSet is"+dataSet);
		testCaseworkbook.close();
		testCaseStream.close();
		return dataSet;
	}

}