package seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Case {

	private static WebDriver driver;
	public static Utils utils = new Utils();
	
	private static String url = "http://10.16.85.138/self.html#/login";
	private static String username = "80007323";
	private static String password = "test_1234";
	
	@BeforeTest
	public void login() {
		utils.initial();
		driver = new ChromeDriver();
		utils.login(driver, url, username, password);
	}

	@Test
	public void yonghuiTest() throws Exception {
		String processName = "联保人变更流程";
		//发起流程
		utils.startProcess(driver, processName);

		//填表单-表单人
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[1]/div/div/div[1]/span/span/i"))
				.click();
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[1]/div/input"))
				.sendKeys("80784671");
		Thread.sleep(1*000);
		
		//点击搜索
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[1]/button/span"))
				.click();
		Thread.sleep(1*1000);
		
		//双击搜索到的人
		WebElement person = driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[2]/div[1]/ul/li"));
		utils.doubleClick(driver, person);
		
		//确认
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[3]/div/button[3]/span")).click();
		Thread.sleep(1*1000);
		
		//点击下拉框-新联保人1
		WebElement select1 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[4]/div/div/div[2]/div[1]/input"));
		utils.select(driver, select1, true);
		Thread.sleep(1*1000);
		
		//点击下拉框-新联保人2
		WebElement select2 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[5]/div/div/div[2]/div[1]/input"));
		utils.select(driver, select2, false);
		Thread.sleep(1*1000);
		
		//填写时间
		WebElement clock = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[6]/div/div/div/input"));
		((JavascriptExecutor)driver).executeScript("document.querySelector(\"#app > div > section > main > div > div > div > div > main > div:nth-child(2) > form > div:nth-child(6) > div > div > div > input\").removeAttribute(\"readonly\")");
		clock.clear();
		clock.sendKeys("2018-4-30");
		
		//提交流程
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/footer/div/button[4]/span")).click();
		Thread.sleep(1*1000);
		driver.findElement(By.xpath("//div[3]/button[2]/span")).click();
		Thread.sleep(1*1000);
		driver.findElement(By.xpath("//div[3]/button[2]/span")).click();
	}
	
	@DataProvider(name="users")
	public Object[][] users(){
		return new Object[][] { { "80403380", "test_1234" }, { "80548384", "test_1234" }, { "80642340", "test_1234" } };
	}
	
	@AfterTest
	public void close() {
		//关闭驱动 关闭所有窗口
		//driver.quit();
		//关闭当前窗口
		//driver.close();
	}

}
