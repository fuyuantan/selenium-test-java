package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumUtils {

	public static WebElement locateBy(WebDriver driver, String findBy, String location, String text)
			throws InterruptedException {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		if (checkEmpty(location)) {
			return null;
		}
		if (("xpathContains").equals(findBy)) {
			return xpathContains(driver, location, text);
		}

		element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				switch (findBy) {
				case "id":
					return driver.findElement(By.id(location));
				case "name":
					return driver.findElement(By.name(location));
				case "className":
					return driver.findElement(By.className(location));
				case "tagName":
					return driver.findElement(By.tagName(location));
				case "linkText":
					return driver.findElement(By.linkText(location));
				case "pLinkText":
					return driver.findElement(By.partialLinkText(location));
				case "xpath":
					return driver.findElement(By.xpath(location));
				case "cssSelector":
					return driver.findElement(By.cssSelector(location));
				default:
					break;
				}
				return null;
			}
		});
		return element;
	}

	public static void action(WebDriver driver, WebElement element, String action, String text)
			throws InterruptedException {
		switch (action) {
		case "click":
			click(element);
			break;
		case "sendKeys":
			sendKeys(element, text);
			break;
		case "doubleClick":
			doubleClick(driver, element);
			break;
		case "click&sendKeys":
			clickSendKeys(element, text);
			break;
		case "sendKeys&enter":
			SendKeysEnter(driver, element, text);
			break;
		// 单纯下拉框
		case "dropList":
			dropList(element);
			break;
		case "clock":
			clock(driver, element, text);
			break;
		case "scroll":
			scroll(driver, element);
			break;
		case "sleep":
			Thread.sleep(1 * 1000);
			break;
		// 点击、输入再进行下拉框操作
		case "click&sendKeys&drop":
			clickSendKeysDrop(element, text);
			break;
		default:
			break;
		}
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public static void clear(WebElement element) {
		element.clear();
	}

	public static void sendKeys(WebElement element, String string) {
		element.sendKeys(string);
	}

	public static void clickSendKeys(WebElement element, String text) throws InterruptedException {
		Thread.sleep(1 * 1000);
		if (element.getAttribute("disabled") != null) {

		} else {
			element.click();
			element.sendKeys(text);
		}
	}

	public static void SendKeysEnter(WebDriver driver, WebElement element, String text) {
		element.sendKeys(text);
		element.sendKeys(Keys.ENTER);
	}

	public static void dropList(WebElement element) {
		if (element.getAttribute("disabled") != null) {

		} else {
			element.sendKeys(Keys.DOWN);
			element.sendKeys(Keys.ENTER);
		}
	}

	public static void urlAction(WebDriver driver, String url, String urlAction) {
		switch (urlAction) {
		case "":
			break;
		case "get":
			driver.get(url);
			driver.manage().window().maximize();
			break;
		case "switch":
			try {
				switchHandle(driver);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "refresh":
			driver.navigate().refresh();
			break;
		}
	}

	public static void switchHandle(WebDriver driver) throws InterruptedException {
		/*
		 * Thread.sleep(1*1000); Set<String> windowHandles = driver.getWindowHandles();
		 * List<String> handlesList = new ArrayList<>(windowHandles); //TODO 多个窗口的情况未考虑
		 * driver.switchTo().window(handlesList.get(1));
		 */

		Thread.sleep(2 * 1000);
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<>(handles);
		/*
		 * System.out.println(handlesList.size()+"==============================="+
		 * handlesList); System.out.println(handlesList.get(0));
		 * System.out.println(handlesList.get(handles.size()-1));
		 */
		driver.switchTo().window(handlesList.get(handles.size() - 1));
	}

	public static void clock(WebDriver driver, WebElement clock, String cssAndTime) {
		String[] by = cssAndTime.split(";");
		if (by.length != 2) {
			Reporter.log(cssAndTime + "出现语法错误！");
			System.out.println(cssAndTime + "出现语法错误！");
			return;
		}
		String css = by[0];
		String time = by[1];
		((JavascriptExecutor) driver)
				.executeScript("document.querySelector('" + css + "').removeAttribute(\"readonly\")");
		clock.clear();
		clock.sendKeys(time);
		clock.sendKeys(Keys.ENTER);
	}

	public static void scroll(WebDriver driver, WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void clickSendKeysDrop(WebElement element, String text) {
		if (element.getAttribute("disabled") != null) {

		} else {
			element.click();
			element.sendKeys(text);
			element.sendKeys(Keys.DOWN);
			element.sendKeys(Keys.ENTER);
		}
	}

	private static boolean checkEmpty(String str) {
		return !(str != null && !"".equals(str.trim()));
	}

	public static WebElement xpathContains(WebDriver driver, String location, String text) {
		String[] prefix = location.split(",");
		String suffix = "\")]";
		String by = prefix[0] + ",\"" + text + suffix;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(By.xpath(by));
			}

		});
		return element;
	}

}
