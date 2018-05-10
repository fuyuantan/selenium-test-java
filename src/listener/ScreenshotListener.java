package listener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import startup.StartUp;
import utils.ScreenShot;

public class ScreenshotListener extends TestListenerAdapter {

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		String classname = tr.getTestClass().getName();
		String methodname = tr.getMethod().getMethodName();
		WebDriver driver = StartUp.getWebDriver();
		ScreenShot shot = new ScreenShot(driver);
		try {
			shot.takeScreentShot(classname, methodname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		String classname = tr.getTestClass().getName();
		String methodname = tr.getMethod().getMethodName();
		WebDriver driver = StartUp.getWebDriver();
		ScreenShot shot = new ScreenShot(driver);
		try {
			shot.takeScreentShot(classname, methodname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}