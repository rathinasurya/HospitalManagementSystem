package ContactModule;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.BaseClass;
import com.hms.objectRepo.AdminHomePage;
import com.hms.objectRepo.AdminLoginPage;
import com.hms.objectRepo.ContactPage;
import com.hms.objectRepo.WelcomePage;

public class ContactQueriesTest extends BaseClass {
	@Test
	public void contactQuery() throws Throwable {
		String ADMIN_USERNAME = fLib.getPropertyKeyValue("admin_username");
		String ADMIN_PASSWORD = fLib.getPropertyKeyValue("admin_password");

		WelcomePage wp = new WelcomePage(driver);
		AdminLoginPage al = new AdminLoginPage(driver);
		AdminHomePage ah = new AdminHomePage(driver);
		ContactPage cp = new ContactPage(driver);

		wp.clickOnContactBtn();
		cp.CreateNewContact(eLib);
		wLib.AlertSIsPresent(driver);
		System.out.println(wLib.getAlertText(driver) + " is displayed");
		wLib.acceptAlert(driver);
		cp.clickOnHome();
		
		wLib.waitForPageLoad(driver);
		wp.clickOnAdminLogin();
		al.LoginToAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);

		ah.clickOnContactQueriesDD();
		ah.clickOnUnreadQuery();

		driver.findElement(By.xpath(
				"//td[.='" + eLib.getDataFromExcel("User's History", 1, 0)
						+ "']/following-sibling::td[4]/div/a/i"))
				.click();
		String queryPg = driver.findElement(By.xpath("//h5[@class='over-title margin-bottom-15']/span")).getText();
		System.out.println(queryPg + " page is dispalyed");
		ah.Admlogout();

	}

}
