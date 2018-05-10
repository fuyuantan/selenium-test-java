package startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import dto.CaseDto;
import utils.ExcelUtils;
import utils.SeleniumUtils;

public class StartUp {
	
	private static WebDriver driver = new ChromeDriver();
	
	public static WebDriver getWebDriver() {
		return driver;
	}
	
	public static void startUp(Map<Object, List<CaseDto>> map) throws InterruptedException {
		List<CaseDto> caseList = new ArrayList<CaseDto>();
		
		for (Entry<Object, List<CaseDto>> entry : map.entrySet()) {
			caseList = entry.getValue();
			WebElement element = null;
			for (CaseDto caseDto : caseList) {
				SeleniumUtils.urlAction(driver, caseDto.getUrl(), caseDto.getUrlAction());
				
				if(!("".equals(caseDto.getLocation()))&&caseDto.getLocation()!=null) {
					element = SeleniumUtils.locateBy(driver, caseDto.getFindBy(), caseDto.getLocation(),caseDto.getText());
				}
				
				SeleniumUtils.action(driver, element, caseDto.getAction(), caseDto.getText());
				Reporter.log(caseDto.getStepNum()+"."+caseDto.getStepName()+":"+caseDto.getText());
				System.out.println(caseDto.getStepName());
			}
			Thread.sleep(1*1000);
			//driver.quit();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		Map<Object, List<CaseDto>> map = ExcelUtils.execlImport("D:/MyData/TANFY3/Desktop/TestCases.xlsx", 0, 3, 23, 1, 13);
		startUp(map);
	}
	
}
