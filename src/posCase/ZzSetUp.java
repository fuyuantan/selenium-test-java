package posCase;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dto.CaseDto;
import startup.StartUp;
import utils.ExcelUtils;

public class ZzSetUp {
	@BeforeTest
	public void initial() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
	}
	
	@Test
	@Parameters({"PosExcelPath","sheetAt","ZzCaseStartRow","ZzCaseEndRow"})
	public void ZzCase(String PosExcelPath, int sheetAt,int ZzCaseStartRow,int ZzCaseEndRow) {
		Map<Object, List<CaseDto>> map = ExcelUtils.execlImport(PosExcelPath, sheetAt, ZzCaseStartRow, ZzCaseEndRow, 1, 13);
		try {
			StartUp.startUp(map);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
