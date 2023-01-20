package Practice_package;

import org.testng.annotations.Test;

public class SampleRunner1 {
	@Test(groups = "smoke")
	public void sample1() {
		System.out.println("test script: 1");
	}
	
	@Test(groups = "smoke")
	public void sample2() {
		System.out.println("test script: 2");
	}

}
