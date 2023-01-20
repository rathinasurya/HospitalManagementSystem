package Practice_package;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class SampleTestNG {
	@Test(priority = 1,invocationCount = 2)
	public void create()
	{
		System.out.println("created");
	}
	
	@Test(dependsOnMethods = "create")
	public void update()
	{
		System.out.println("updated");
	}
	
	@Test(priority = 2, invocationCount = 2)
	public void delete()
	{
		System.out.println("deleted");
	}

}
