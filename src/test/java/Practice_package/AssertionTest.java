package Practice_package;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class AssertionTest {
@Test
public void sample1()
{
	System.out.println("---TestScript 1---");
	System.out.println("---TestScript 2---");
	assertNotEquals("A", "B");
	System.out.println("---TestScript 3---");
	System.out.println("---TestScript 4---");
}
@Test
public void sample2()
{
	System.out.println("---TestScript 5---");
	System.out.println("---TestScript 6---");
	assertEquals("B", "B","Script is passed");
	System.out.println("---TestScript 7---");
	System.out.println("---TestScript 8---");
}
@Test
public void sample3()
{
	String a=null;
	assertNotNull(a);
	System.out.println("--------+++--------");
}
@Test
public void sample4()
{
	String a="";
	assertNull(a);
	System.out.println("---------***---------");
}
@Test
public void sample5()
{
	System.out.println("---TestScript 9---");
	SoftAssert sa=new SoftAssert();
	sa.assertEquals("A", "B");
	System.out.println("---TestScript 10---");
	sa.assertAll();
}
@Test
public void sample6()
{
	System.out.println("---TestScript 11---");
	SoftAssert sa=new SoftAssert();
	sa.assertNotEquals("A", "B");
	System.out.println("---TestScript 12---");
	sa.assertAll();
}
}
