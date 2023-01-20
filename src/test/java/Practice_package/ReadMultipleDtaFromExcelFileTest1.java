package Practice_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDtaFromExcelFileTest1 {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Organization");
		for(int i=0;i<=sheet.getLastRowNum();i++)
		{
			String data = sheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println(data);
		}
			
		
	}

}
