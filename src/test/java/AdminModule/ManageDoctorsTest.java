package AdminModule;


import static org.testng.Assert.fail;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.BaseClass;
import com.hms.objectRepo.AdminHomePage;
import com.hms.objectRepo.AdminLoginPage;
import com.hms.objectRepo.DoctorHomePage;
import com.hms.objectRepo.WelcomePage;
@Listeners(com.hms.GenericUtilites.ListenerImplementationClass.class)
public class ManageDoctorsTest extends BaseClass {
	@Test
	public void manageDoctor() throws Throwable
	{
		String ADMIN_USERNAME = fLib.getPropertyKeyValue("admin_username");
		String ADMIN_PASSWORD = fLib.getPropertyKeyValue("admin_password");

		WelcomePage wp = new WelcomePage(driver);
		AdminLoginPage al = new AdminLoginPage(driver);
		AdminHomePage ah = new AdminHomePage(driver);
		DoctorHomePage dh=new DoctorHomePage(driver);

		wp.clickOnAdminLogin();
		al.LoginToAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);
		ah.clickOnDoctorsDD();
		ah.clickOnDoctorSpecialization();
		ah.addDoctorSpecilaization(eLib);
		//System.out.println("Doctor Specialization added successfully !! is Displayed");
		Reporter.log("Doctor Specialization added successfully !! is Displayed");
		ah.clickOnAddDoctor();
		ah.addDoctor(eLib, jLib);
		wLib.AlertSIsPresent(driver);
		//System.out.println(wLib.getAlertText(driver));
		Reporter.log(wLib.getAlertText(driver));
		wLib.acceptAlert(driver);
		ah.clickOnManageDoctors();
		int i=0;
		while(i<250)
		{
			dh.removeDoctor();
			wLib.AlertSIsPresent(driver);
			wLib.acceptAlert(driver);
			break;
		}
		ah.Admlogout();
		//fail();

	}
}