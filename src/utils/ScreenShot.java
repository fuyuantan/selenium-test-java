package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenShot {
	private WebDriver driver;
	private final static String SCREEN_SHOT_PATH = "test-output/screen-shot";
	private static String SCREEN_SHOT_NAME = null;
	
    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }
	
	public void takeScreentShot(String classname, String methodname) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH时mm分ss秒");
        String dateStr = dateFormat.format(new Date());
		File screenShotDir = new File(SCREEN_SHOT_PATH);
		if (!screenShotDir.exists()) {
			screenShotDir.mkdirs();
		}
		SCREEN_SHOT_NAME = classname + "_" + methodname +  "_" + dateStr + ".png";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(SCREEN_SHOT_PATH + "/" + SCREEN_SHOT_NAME);
		FileUtils.copyFile(srcFile, targetFile);
		//失败截图放入测试报告
		String log = targetFile.getAbsolutePath();
	    Reporter.log("<br/><img src=\"" + log + "\" />");
	}

	public String getScreenShotPath() {
		return SCREEN_SHOT_PATH;
	}

	public String getScreenShotName() {
		return SCREEN_SHOT_NAME;
	}
}
