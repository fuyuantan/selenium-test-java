package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import dto.CaseDto;


public class ExcelUtils {
	
	public static Map<Object, List<CaseDto>> execlImport(String path,int sheetAt,int startRow,int endRow,int startCell,int endCell){
		InputStream input = null;
        Workbook workbook = null;
        
		try {
			input = new FileInputStream(path);
			workbook = WorkbookFactory.create(input);
			Sheet sheet = workbook.getSheetAt(sheetAt);
			String sheetName = sheet.getSheetName();
			String [][] datas = new String[endRow-startRow+1][endCell-startCell+1];
			for(int i=startRow;i<=endRow;i++) {
				Row row = sheet.getRow(i-1);
				for(int j=startCell;j<=endCell;j++) {
					Cell cell = row.getCell(j-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					datas[i-startRow][j-startCell] = value;
				}
			}
			return datasToMap(sheetName,datas, startRow, endRow, startCell, endCell);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<Object, List<CaseDto>> datasToMap(String sheetName,String[][] datas,int startRow,int endRow,int startCell,int endCell){
		Map<Object, List<CaseDto>> map = new HashMap<>();
		List<CaseDto> caseList = new ArrayList<CaseDto>();
		
		for (int i = 0; i < datas.length; i++) {
			CaseDto caseDto = new CaseDto();
			if (datas[i] != null) {
				caseDto.setCaseId(datas[i][0]);
				caseDto.setStepNum(datas[i][1]);
				caseDto.setStepName(datas[i][2]);
				caseDto.setUrl(datas[i][3]);
				caseDto.setUrlAction(datas[i][4]);
				caseDto.setFindBy(datas[i][5]);
				caseDto.setLocation(datas[i][6]);
				caseDto.setAction(datas[i][7]);
				caseDto.setText(datas[i][8]);
				caseDto.setExpect(datas[i][9]);
				caseDto.setResult(datas[i][10]);
				caseDto.setScreenshot(datas[i][11]);
				caseDto.setEmail(datas[i][12]);
				caseList.add(caseDto);
			} else {
				
			}
		}
		map.put(sheetName,caseList);
		return map;
	}
	
	public static void main(String[] args) {
		/*Map<Object, List<CaseDto>> map = execlImport("D:/MyData/TANFY3/Desktop/TestCases.xlsx", 0, 3, 6, 1, 15);
		System.out.println(map);*/
		String prefix = "//span[contains(text(),\"";
		String suffix = "\")]";
		String location = prefix + "全省" + suffix;
		System.out.println(location);
	}
}
