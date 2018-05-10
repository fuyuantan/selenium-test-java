package seleniumTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Test {
	
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys(new String[] {"hello"});
		driver.findElement(By.id("su")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.quit();
		
	}
	
	@org.junit.Test
	public void switchTest() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.baidu.com/");
		
		driver.findElement(By.id("setf")).click();
		
		System.out.println(driver.getWindowHandles().size());
		
		Set<String> handles = driver.getWindowHandles();
		
		for (String string : handles) {
			System.out.println(string);
		}
		
		List<String> handlesList = new ArrayList<>(handles);
		
		System.out.println("before switch:"+driver.getCurrentUrl());
		
		if(handlesList.size()>1) {
			driver.switchTo().window(handlesList.get(1));
		}
		
		System.out.println("after switch:"+driver.getCurrentUrl());
		
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : elements) {
			
		}
		
	}
	
	@org.junit.Test
	public void yonghuiDemo() throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		
		//打开网页
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.16.85.138/self.html#/login");
		
		//多开
		driver = new ChromeDriver();
		driver.get("http://10.16.85.138/self.html#/login");
		
		//登陆
		List<WebElement> elements = driver.findElements(By.name("username"));
		
		int count = 0;
		for (WebElement webElement : elements) {
			if(webElement.getAttribute("type") != null) {
				if(webElement.getAttribute("type").equals("text")) {
					webElement.sendKeys("80007323");
					count++;
				}
			}
			
			if(webElement.getAttribute("type") != null) {
				if(webElement.getAttribute("type").equals("password")){
					webElement.sendKeys("test_1234");
					count++;
				}
			}
		}
		
		if(count==2) {
			driver.findElement(By.tagName("button")).click();;
		}
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		//流程中心
		driver.findElement(By.linkText("流程中心")).click();
		
		//发起流程
		//driver.findElement(By.tagName("input")).sendKeys("联保人变更流程");
		driver.findElement(By.tagName("input")).sendKeys("员工入职流程");
		
		driver.findElement(By.tagName("input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1*1000);
		
		WebElement start = driver.findElement(By.name("check"));
		Actions action = new Actions(driver);
		action.doubleClick(start).perform();
		
		Thread.sleep(1*1000);
		
		//System.out.println("句柄数量:"+driver.getWindowHandles().size());
		
		//转化到新窗口
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<>(windowHandles);
		/*for (String string : windowHandles) {
			System.out.println(string);
		}*/
		
		driver.switchTo().window(handlesList.get(1));
		
		Thread.sleep(1000);
		
		
		//入职
		WebElement e = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/section/form/div[1]/div[12]/div/div/div[1]/input"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",e);
		
		Thread.sleep(5000);
		
		//填表单-表单人
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[1]/div/div/div[1]/span/span/i")).click();
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[1]/div/input")).sendKeys("80784671");
		Thread.sleep(1*1000);
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[1]/button/span")).click();
		WebElement person = driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[2]/div/div/div[2]/div[2]/div[1]/ul/li"));
		Actions action1 = new Actions(driver);
		action1.doubleClick(person).perform();
		driver.findElement(By.xpath("//*[@id=\"personnelSelect\"]/div/div[3]/div/button[3]/span")).click();
		
		//点击下拉框
		Thread.sleep(1*1000);
		WebElement select1 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[4]/div/div/div[2]/div[1]/input"));
		if (select1.getAttribute("disabled")!=null) {
			
		}
		else {
			select1.sendKeys(Keys.DOWN);
			select1.sendKeys(Keys.ENTER);
		}
		
		Thread.sleep(1*1000);
		WebElement select2 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[5]/div/div/div[2]/div[1]/input"));
		if (select2.getAttribute("disabled")!=null) {
			
		}else {
			select2.sendKeys(Keys.DOWN);
			select2.sendKeys(Keys.DOWN);
			select2.sendKeys(Keys.ENTER);
		}
		
		Thread.sleep(1*1000);		
		//填写时间
		WebElement time = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/div/main/div[2]/form/div[6]/div/div/div/input"));
		((JavascriptExecutor)driver).executeScript("document.querySelector(\"#app > div > section > main > div > div > div > div > main > div:nth-child(2) > form > div:nth-child(6) > div > div > div > input\").removeAttribute(\"readonly\")");
		time.clear();
		time.sendKeys("2018-4-30");
		
		//提交流程
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/main/div/div/div/footer/div/button[4]/span")).click();
		Thread.sleep(1*1000);
		//driver.findElement(By.xpath("/html/body/div[7]")).findElement(By.xpath("/html/body/div[5]/div/div[3]/button[2]/span")).click();
		driver.findElement(By.xpath("//div[3]/button[2]/span")).click();
		Thread.sleep(1*1000);
		driver.findElement(By.xpath("//div[3]/button[2]/span")).click();
		
	}
	
}
