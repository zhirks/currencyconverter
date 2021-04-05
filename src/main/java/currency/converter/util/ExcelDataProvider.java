package currency.converter.util;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;

	public ExcelDataProvider() {

		File srcFile = new File("C:\\Users\\RATutor\\eclipse-workspace\\Troubleshoot_PaymentService\\CurrencyConverter\\src\\main\\java\\currency\\converter\\testdata\\TestData.xlsx");

		try {
			FileInputStream fis = new FileInputStream(srcFile);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to read test data! " + e.getMessage());
		}

	}

	public int printSample() {
		return wb.getNumberOfSheets();
		//System.out.println("passed");
	}
	
	public String getStringData(int sheetIndex, int row, int column) {
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();

	}
	
	public String getStringData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();

	}
	
	public double getNumericData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();

	}
}
