package Practice_package;

import org.testng.annotations.Test;

public class SampleRunner3 {
	@Test(groups = "smoke")
	public void sample5() {
		System.out.println("test script: 5");
	}
	
	@Test
	public void sample6() {
		System.out.println("test script: 6");
	}
}
