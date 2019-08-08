package helperFunctions;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static void main(String[] args) {

		System.out.println("test");
try {
	FileInputStream fis=new FileInputStream(new File("./src/test/resources/TestData.xlsx"));
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sheetname=wb.getSheetAt(0);
	XSSFRow row=sheetname.getRow(1);
	XSSFCell cell=row.getCell(1);
	System.out.println(cell.getStringCellValue());
} catch (Exception e) {
	// TODO: handle exception
	System.out.println(e);
}
	}

}
