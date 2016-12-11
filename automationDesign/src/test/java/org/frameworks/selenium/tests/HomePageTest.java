package org.frameworks.selenium.tests;

import org.framework.selenium.pages.BasePage;
import org.framework.resource.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.framework.resource.*;
import org.framework.selenium.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest{
	
	public static String INPUT_PATH_EXCEL = "D:\\facebookHome.xlsx";
	public static String INPUT_PATH_EXCEL_BASE_WORKSHEET = "TestCases";
	public static String INPUT_PATH_EXCEL_TD_WORKSHEET = "D:\\facebookTestdata.xlsx";
	public static String OUTPUT_PATH="D:\\Results\\";
	ExcelDataSetReader excelop = new ExcelDataSetReader();
	XSSFSheet excelSheet = null;
	LinkedHashMap<String, ArrayList<String>> testCaseData = new LinkedHashMap<>();
	LinkedHashMap<String, ArrayList<String>> testDataRun = new LinkedHashMap<>();
	HomePage hp;
	Sheet wbTestcase, wbTestData;
	
	@Test(priority=0)
	public void ExcelTestCaseSheetGetter() throws IOException{
		wbTestcase = excelop.getExcelTestCase(INPUT_PATH_EXCEL);		
	}

	@Test(priority=1)
	public void ExcelTestDataSheetGetter() throws IOException{
		wbTestData = excelop.getExcelTestData(INPUT_PATH_EXCEL_TD_WORKSHEET);
	}
	
	@Test(priority=2)
	public void ExcelTestCaseData() throws IOException{
		testCaseData = excelop.readExcelTestCase(wbTestcase);
		for (Entry<String, ArrayList<String>> item : testCaseData.entrySet()) {
			  String key = item.getKey();
			  ArrayList<String> value = item.getValue();
			  System.out.println("Key: "+key);
			  System.out.println("Value" +value);
			}
	}

	@Test(priority=4)
	public void homePageFunction(){
		hp = new HomePage();
		hp.getTitle();
	}

	@Test(priority=5)
	public void signinFunctionwithDataset() throws IOException{
		
		//int repeatCounter = Integer.parseInt(testCaseData.get("1").get(2));
		Cell repeatCounterCell = wbTestData.getRow(1).getCell(10);
		int repeatCounter = (int) repeatCounterCell.getNumericCellValue();
		testDataRun = excelop.readExcelDataSet(wbTestData, repeatCounter);
		for(int i=0; i<=repeatCounter; i++){
			String testDataId = "TD_"+(i+1);
			System.out.println(testDataRun.get(testDataId));
			hp.firstNameInput((testDataRun.get(testDataId)).get(1));
			hp.surnameInput((testDataRun.get(testDataId)).get(2));
		}
	}
	
}
