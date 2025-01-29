package Databasetesting.Databasetesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Dataprovider {
	@DataProvider(name="logindata")
	public Object[][] logindata() throws IOException{
		
		String path = System.getProperty("user.dir");
		FileInputStream file =  new FileInputStream(path+"/utilities/orangehrmdata.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		XSSFSheet sheet= workbook.getSheet("Sheet1");
		int rows= sheet.getLastRowNum();
		int columns= sheet.getRow(0).getLastCellNum();
		DataFormatter dataFormatter = new DataFormatter();
		Object[][] data = new Object[rows][columns];
		for(int i=1;i<=rows;i++) {
			XSSFRow currentrow= sheet.getRow(i);
			for(int j=0;j<columns;j++) {
				XSSFCell currentcell = currentrow.getCell(j);
				String cellValue = dataFormatter.formatCellValue(currentcell);
				data [i-1][j]= cellValue;
			}
		}
		workbook.close();
		return data;
		
	}
	
	

}
