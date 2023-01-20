package DoctorModule;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.hms.GenericUtilites.BaseClass;
import com.hms.objectRepo.DoctorHomePage;
import com.hms.objectRepo.DoctorLoginPage;
import com.hms.objectRepo.PatientHomePage;
import com.hms.objectRepo.PatientLoginPage;
import com.hms.objectRepo.WelcomePage;

public class GenerateReportForPatientTest extends BaseClass {
	@Test
	public void generatePatientReport() throws Throwable {
		String DOC_USERNAME = fLib.getPropertyKeyValue("doc_username");
		String DOC_PASSWORD = fLib.getPropertyKeyValue("doc_password");
		String PAT_USERNAME = fLib.getPropertyKeyValue("pat_username");
		String PAT_PASSWORD = fLib.getPropertyKeyValue("pat_password");

		WelcomePage wp = new WelcomePage(driver);
		PatientLoginPage pl = new PatientLoginPage(driver);
		PatientHomePage ph = new PatientHomePage(driver);
		DoctorLoginPage dl = new DoctorLoginPage(driver);
		DoctorHomePage dh = new DoctorHomePage(driver);

		wp.clickOnDoctorLogin();
		dl.LoginToDoctor(DOC_USERNAME, DOC_PASSWORD);

		dh.clickOnSearchPatient();

		String pat = eLib.getDataFromExcel("Search Patient", 0, 1);
		dh.searchpatient(eLib);
		dh.clickOnPatientDD();
		dh.clickOnManagePatient();
		String CreatedPatient = driver.findElement(By.xpath("//td[.='" + pat + "']")).getText();

		if (CreatedPatient.equalsIgnoreCase("thanuja")) {
			System.out.println(pat + " is displayed");
			dh.clickOnPatientviewEyeIcon();
		}

		else

		{
			System.out.println(pat + " is not displayed");
		}

		dh.clickOnAddMediHistoryBtn();
		dh.AddMediHistory(eLib);
		wLib.AlertSIsPresent(driver);
		wLib.acceptAlert(driver);
		dh.Dlogout();

		wp.clickOnPatientLogin();
		pl.loginToPatient(PAT_USERNAME, PAT_PASSWORD);
		ph.clickOnMedicalHistoryBtn();
		ph.clickOnMediEyeIcon();
		ph.clickOnpatientLogout();

	}
}
