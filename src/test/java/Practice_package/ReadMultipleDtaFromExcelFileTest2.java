package Practice_package;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDtaFromExcelFileTest2 {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Organization");
		
		for(int i=0;i<=sheet.getLastRowNum();i++)
		{
			Row row = sheet.getRow(i);
			for(int j=0;j<row.getLastCellNum();j++)
			{
			String data = row.getCell(j).getStringCellValue();
	
			System.out.println(data+" ");
		}
			System.out.println();

		
}
}
}