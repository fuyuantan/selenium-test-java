package empCase;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dto.CaseDto;
import startup.StartUp;
import utils.ExcelUtils;

public class EmpChangeSurety {
	
	@BeforeTest
	public void initial() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
	}
	
	@Test
	@Parameters({"excelPath","sheetAt","case1StartRow","case1EndRow"})
	public void case1(String excelPath, int sheetAt,int case1StartRow,int case1EndRow) {
		Map<Object, List<CaseDto>> map = ExcelUtils.execlImport(excelPath, sheetAt, case1StartRow, case1EndRow, 1, 13);
		try {
			StartUp.startUp(map);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Parameters({"excelPath","sheetAt","case2StartRow","case2EndRow"})
	public void case2(String excelPath, int sheetAt,int case2StartRow,int case2EndRow) {
		Map<Object, List<CaseDto>> map = ExcelUtils.execlImport(excelPath, sheetAt, case2StartRow, case2EndRow, 1, 13);
		try {
			StartUp.startUp(map);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
