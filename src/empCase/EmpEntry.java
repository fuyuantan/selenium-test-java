package empCase;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dto.CaseDto;
import startup.StartUp;
import utils.ExcelUtils;

public class EmpEntry {
	
	@BeforeTest
	public void initial() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
	}
	
	@Test
	@Parameters({"sheetAt","case3StartRow","case3EndRow"})
	public void case3(int sheetAt,int case3StartRow,int case3EndRow) {
		Map<Object, List<CaseDto>> map = ExcelUtils.execlImport("D:/MyData/TANFY3/Desktop/TestCases.xlsx", sheetAt, case3StartRow, case3EndRow, 1, 13);
		try {
			StartUp.startUp(map);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
