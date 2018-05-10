package seleniumTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Utils {

	private static WebElement element;
	private static List<WebElement> list;
	
	public ChromeOptions initial() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
    	//通过配置参数禁止data;的出现
    	ChromeOptions options = new ChromeOptions(); 
    	options.setExperimentalOption("excludeSwitches", "ignore-certificate-errors");
        options.addArguments("'--user-data-dir=D:\\MyData\\TANFY3\\AppData\\Local\\Google\\Chrome\\User Data\\Default'");
        return options;
	}
	
	public void login(WebDriver driver,String url,String username,String password) {
		driver.get("http://10.16.85.138/self.html#/login");
		List<WebElement> list = this.locateBy(driver,"name:username", true);
		int count = 0;
		for (WebElement e : list) {
			if (e.getAttribute("type") != null) {
				if (e.getAttribute("type").equals("text")) {
					this.sendKeys(e, username);
					count++;
				}
			}

			if (e.getAttribute("type") != null) {
				if (e.getAttribute("type").equals("password")) {
					this.sendKeys(e, password);
					count++;
				}
			}
		}
		if (count == 2) {
			this.locateBy(driver,"tag:button").click();
		}
	}
	
	public void startProcess(WebDriver driver,String processName) throws InterruptedException {
		Thread.sleep(1500);
		this.locateBy(driver, "linkText:流程中心").click();
		Thread.sleep(1*1000);
		this.locateBy(driver, "tag:input").sendKeys(processName);
		this.locateBy(driver, "tag:input").sendKeys(Keys.ENTER);
		Thread.sleep(1*1000);
		WebElement enter = this.locateBy(driver, "name:check");
		this.doubleClick(driver, enter);
		this.switchHandle(driver);
	}
	
	public WebElement locateBy(WebDriver driver,String path) {
		String[] by = path.split(":");
		String prefix = by[0];
		String suffix = by[1];

		switch (prefix) {
		case "id":
			element = driver.findElement(By.id(suffix));
			break;
		case "name":
			element = driver.findElement(By.name(suffix));
			break;
		case "class":
			element = driver.findElement(By.className(suffix));
			break;
		case "tag":
			element = driver.findElement(By.tagName(suffix));
			break;
		case "linkText":
			element = driver.findElement(By.linkText(suffix));
			break;
		case "pLinkText":
			element = driver.findElement(By.partialLinkText(suffix));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(suffix));
			break;
		case "css":
			element = driver.findElement(By.cssSelector(suffix));
			break;
		}
		return element;
	}

	public List<WebElement> locateBy(WebDriver driver,String path, boolean flag) {
		// 返回类型List为true
		if (flag == false) {
			locateBy(driver,path);
			return null;
		}

		String[] by = path.split(":");
		String prefix = by[0];
		String suffix = by[1];
		
		switch (prefix) {
		case "id":
			list = driver.findElements(By.id(suffix));
			break;
		case "name":
			list = driver.findElements(By.name(suffix));
			break;
		case "class":
			list = driver.findElements(By.className(suffix));
			break;
		case "tag":
			list = driver.findElements(By.tagName(suffix));
			break;
		case "linkText":
			list = driver.findElements(By.linkText(suffix));
			break;
		case "pLinkText":
			list = driver.findElements(By.partialLinkText(suffix));
			break;
		case "xpath":
			list = driver.findElements(By.xpath(suffix));
			break;
		case "css":
			list = driver.findElements(By.cssSelector(suffix));
			break;
		}
		return list;
	}

	public void sendKeys(WebElement e,String string) {
		e.sendKeys(string);
	}

	public void switchHandle(WebDriver driver) throws InterruptedException {
		Thread.sleep(1*1000);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<>(windowHandles);
		driver.switchTo().window(handlesList.get(1));
	}
	
	public void clock(WebDriver driver,WebElement clock,String clockPath,String time) {
		((JavascriptExecutor)driver).executeScript("document.querySelector('"+clockPath+"').removeAttribute(\"readonly\")");
		clock.clear();
		clock.sendKeys(time);
	}
	
	public void select(WebDriver driver,WebElement e,boolean flag) {
		if (e.getAttribute("disabled")!=null) {
			
		}
		else if(flag==true){
			e.sendKeys(Keys.DOWN);
			e.sendKeys(Keys.ENTER);
		}else {
			e.sendKeys(Keys.DOWN);
			e.sendKeys(Keys.DOWN);
			e.sendKeys(Keys.ENTER);
		}
	}
	
	public void doubleClick(WebDriver driver,WebElement e) {
		Actions action = new Actions(driver);
		action.doubleClick(e).perform();
	}

}


