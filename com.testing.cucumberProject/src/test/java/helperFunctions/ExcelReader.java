package helperFunctions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static HashMap<String,String> storeValues = new HashMap();
	public static List<HashMap<String,String>> readExceldata(String filepath,String sheetName)
	{
		List<HashMap<String,String>> mydata = new ArrayList<HashMap<String,String>>();
		try
		{
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow HeaderRow = sheet.getRow(0);
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
			{
				XSSFRow currentRow = sheet.getRow(i);
				HashMap<String,String> currentHash = new HashMap<String,String>();
				for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
				{
					XSSFCell currentCell = currentRow.getCell(j);
					currentCell.setCellType(Cell.CELL_TYPE_STRING);
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());	
				}
				mydata.add(currentHash);
			}
			fs.close();
			workbook.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mydata;
	}
	/*public static void main(String[] args) {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		list=data(GetProperties.getProperty("filepath"), "Test");
		System.out.println(list.get(0).get("name"));
	}*/
}
