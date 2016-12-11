/**
 * 
 */


/**
 * @author TS2-1213
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*import org.apache.velocity.texen.util.FileUtil;*/
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.thoughtworks.selenium.SeleneseTestCase;
public class ExcelOperations {
	private static final String String = null;
	// Global variables - accessed any where in this class
	public String[][] ExcelData; 
	public int ExcelRows, ExcelCols;
	public static String INPUT_PATH = "D:\\googleHome.xlsx";
	public static String OUTPUT_PATH="D:\\googleHomeResult.xlsx";
	public static int EXCEL_ROW_TO_START = 1;
	
	ArrayList<String> testCasesToRun = new ArrayList<String>();
	HashMap<String, ArrayList<String>> dataSet = new HashMap<>();
	String[] strs = null;
	
	//Excel Read operations
	@Test
	public void excelRead() throws IOException{
		File excelFile = new File(INPUT_PATH);
		FileInputStream excelFileStream = new FileInputStream(excelFile);
		XSSFWorkbook excelwb = new XSSFWorkbook(excelFileStream);
		XSSFSheet excelSheet = excelwb.getSheetAt(0);//getting first sheet in workbook
		ExcelRows = excelSheet.getLastRowNum();
		ExcelCols = excelSheet.getRow(1).getLastCellNum();
		System.out.println("Total Excel Rows are: "+ExcelRows);
		System.out.println("Total Excel Columns are: "+ExcelCols);
		
		for(int i=EXCEL_ROW_TO_START; i<ExcelRows+1; i++){
			testCasesToRun.clear();
			XSSFRow rowIterate =  excelSheet.getRow(i);
			XSSFCell executeCell = rowIterate.getCell(0);
			String userStory = cellToString(executeCell);
			String testScenarioId = cellToString(rowIterate.getCell(1));
			String testcaseId = cellToString(rowIterate.getCell(2));
			String testCaseName = cellToString(rowIterate.getCell(3));
			String testCaseDec = cellToString(rowIterate.getCell(4));
			String testData = cellToString(rowIterate.getCell(5));
			String testOperator = cellToString(rowIterate.getCell(6));
			String locatorName = cellToString(rowIterate.getCell(7));
			String locatorObj = cellToString(rowIterate.getCell(8));
			String iterateCounter = cellToString(rowIterate.getCell(9));
			strs = new String[]{userStory, testcaseId, testScenarioId, testCaseName, testCaseDec, testCaseDec, testData, testOperator, locatorName, locatorObj, iterateCounter};
			for (String s : strs) { 
				
				testCasesToRun.add(s);
				}
			System.out.println("Array testcasestorun: "+testCasesToRun);
			dataSet.put(testcaseId, testCasesToRun);
			System.out.println("Data Set is: "+dataSet);
			
		}
		System.out.println(dataSet);
	}
	public static String cellToString(Cell cell) {
		if(cell == null)
			return "";
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC: //0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING: //1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: //2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK: //3
			result = "-";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: //4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR: //5
			throw new RuntimeException ("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: " + type);
		}
		return result.toString();
	}	
	//@Test
	//Excel Write Operation
	public void excelWrite() throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

		String resultPath = OUTPUT_PATH+"\\"+dateFormat.format(date)+"_Report.xlsx";
		System.out.println(resultPath);
		FileUtils.copyFile(new File(ExcelOperations.INPUT_PATH), new File(resultPath));
		FileInputStream outStream = new FileInputStream(resultPath);
		Workbook workbook = new XSSFWorkbook(outStream);
		Sheet sheet = workbook.getSheetAt(0);
		ExcelRows = sheet.getLastRowNum()+1;
		ExcelCols = sheet.getRow(1).getLastCellNum();
		ExcelData = new String[ExcelRows][ExcelCols];
		
		
	}

	//Excel Write 
}
