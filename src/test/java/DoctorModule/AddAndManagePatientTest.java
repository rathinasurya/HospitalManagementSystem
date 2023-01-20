package DoctorModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.BaseClass;
import com.hms.objectRepo.AdminHomePage;
import com.hms.objectRepo.AdminLoginPage;
import com.hms.objectRepo.DoctorHomePage;
import com.hms.objectRepo.DoctorLoginPage;
import com.hms.objectRepo.PatientHomePage;
import com.hms.objectRepo.WelcomePage;

public class AddAndManagePatientTest extends BaseClass {
@Test
public void addAndManagepatient() throws Throwable
{
		String ADMIN_USERNAME = fLib.getPropertyKeyValue("admin_username");
		String ADMIN_PASSWORD = fLib.getPropertyKeyValue("admin_password");
		String DOC_USERNAME = fLib.getPropertyKeyValue("doc_username");
		String DOC_PASSWORD = fLib.getPropertyKeyValue("doc_password");

		WelcomePage wp = new WelcomePage(driver);
		PatientHomePage ph = new PatientHomePage(driver);
		AdminLoginPage al = new AdminLoginPage(driver);
		AdminHomePage ah = new AdminHomePage(driver);
		DoctorLoginPage dl = new DoctorLoginPage(driver);
		DoctorHomePage dh = new DoctorHomePage(driver);

		wp.clickOnDoctorLogin();
		dl.LoginToDoctor(DOC_USERNAME, DOC_PASSWORD);
		dh.clickOnPatientDD();
		dh.clickOnAddPatient();
		ph.addPatient(eLib, jLib);
		ph.clickOnpatientLogout();

		wp.clickOnAdminLogin();
		al.LoginToAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);
		ah.clickOnPatientsDD();
		ah.clickOnManagepatients();
		String patname = eLib.getDataFromExcel("Patient Details", 1, 1);
		List<WebElement> paname = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr/td[2]"));
		wLib.waitForPageLoad(driver);
		boolean flag = false;
		for (int i = 0; i < paname.size(); i++) {
			String pnames = paname.get(i).getText();
			if (patname.equalsIgnoreCase(pnames)) {
				flag = true;
				break;
			}

		}
		if (flag)
			System.out.println(patname + " is displayed");
		else
			System.out.println(patname + " is not displayed");

	}

}
