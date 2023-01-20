package Practice_package;

import org.testng.annotations.Test;

public class SampleRunner2 {
	@Test(groups = "regression")
	public void sample3() {
		System.out.println("test script: 3");
	}
	
	@Test(groups = {"smoke","regression"})
	public void sample4() {
		System.out.println("test script: 4");
	}
}
