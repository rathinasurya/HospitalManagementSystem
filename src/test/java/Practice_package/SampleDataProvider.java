package Practice_package;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleDataProvider {
@Test(dataProvider = "data")
public void travel(String src,String dst,String price)
{
	System.out.println("from " +src+ "to " +dst+" price is "+price );
}


@DataProvider
public Object[][] data()
{
	Object[][] objArr = new Object[3][3];
	objArr[0][0]="bangalore";
	objArr[0][1]="chennai";
	objArr[0][2]="200";
	
	objArr[1][0]="chennai";
	objArr[1][1]="bangalore";
	objArr[1][2]="250";
	
	objArr[2][0]="kochin";
	objArr[2][1]="mysore";
	objArr[2][2]="400";
	return objArr;
}
}