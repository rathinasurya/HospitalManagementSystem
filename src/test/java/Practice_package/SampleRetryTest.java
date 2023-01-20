package Practice_package;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import org.testng.IRetryAnalyzer;

import com.hms.GenericUtilites.BaseClass;

public class SampleRetryTest extends BaseClass{
	
	@Test(retryAnalyzer = com.hms.GenericUtilites.RetryImplementationClass.class)
	public void test()
	{
		assertEquals("A", "B");
	}

}
