package Practice_package;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.ExcelUtility;

public class DataProviderTest {
	
	@Test(dataProvider = "getData")
	public void readData(String from,String to)
	{
	System.out.println(from+"---->"+to);	
	}
	
	@DataProvider
	public String[][] getData() throws Throwable
	{
		ExcelUtility eu=new ExcelUtility();
		String[][] value = eu.readMultipleSetOfData("DataProvider");
		
		return value;	
	}

}
