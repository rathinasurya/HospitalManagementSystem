package Practice_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertDataIntoExcelFile {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet("Organization").createRow(2).createCell(0).setCellValue("testyantra");
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		
	}

}
