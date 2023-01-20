package PatientModule;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.BaseClass;
import com.hms.objectRepo.AdminHomePage;
import com.hms.objectRepo.AdminLoginPage;
import com.hms.objectRepo.BookAppointmentpage;
import com.hms.objectRepo.PatientHomePage;
import com.hms.objectRepo.PatientLoginPage;
import com.hms.objectRepo.WelcomePage;
@Listeners(com.hms.GenericUtilites.ListenerImplementationClass.class)
public class AppointmentHistoryTest extends BaseClass {
	
	@Test
	public void appointmentHistory() throws Throwable {
		WelcomePage wp = new WelcomePage(driver);
		PatientLoginPage pl = new PatientLoginPage(driver);
		PatientHomePage ph = new PatientHomePage(driver);
		BookAppointmentpage ba = new BookAppointmentpage(driver);
		AdminLoginPage al = new AdminLoginPage(driver);
		AdminHomePage ah = new AdminHomePage(driver);
		wp.clickOnPatientLogin();
		String PAT_USERNAME = fLib.getPropertyKeyValue("pat_username");
		String PAT_PASSWORD = fLib.getPropertyKeyValue("pat_password");
		pl.loginToPatient(PAT_USERNAME, PAT_PASSWORD);
		ph.clickOnBookMyAppointment();
		ba.bookAppointment(eLib);
		wLib.AlertSIsPresent(driver);
		System.out.println(wLib.getAlertText(driver) + " is displayed");
		wLib.acceptAlert(driver);
		ph.clickOnpatientLogout();
		wp.clickOnAdminLogin();
		String ADMIN_USERNAME = fLib.getPropertyKeyValue("admin_username");
		String ADMIN_PASSWORD = fLib.getPropertyKeyValue("admin_password");
		al.LoginToAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);
		ah.clickOnAdminAppoinmentHistory();
		String APatName = eLib.getDataFromExcel("Booking Details", 1, 0);
		// Validate the user details is presented or not.
		boolean EpatName = driver.findElement(By.xpath("//td[contains(text(),'" + APatName + "')]")).isDisplayed();

		if (EpatName == true) {
			//System.out.println(APatName + " appointment is booked and Status as 'Active'");
			Reporter.log(APatName + " appoinment is booked and Status as 'Active'");
		}

		else

		{
			//System.out.println(APatName + " appoinment is not booked and Status as 'Active'");
			Reporter.log(APatName + " appoinment is not booked and Status as 'Active'");

		}
		//fail();
	}

}
