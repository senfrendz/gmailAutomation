package dataParse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {
	
	public static Logger log = Logger.getLogger(readExcel.class);

	public static ArrayList<Object[]> readFromExcel() {
		ArrayList<Object[]> dataStore= new ArrayList<Object[]>();
		
		String path = "dataprovider\\TestData.xlsx";
		
		FileInputStream readFile;
		try {
			readFile = new FileInputStream(path);
			
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(readFile); 
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int RowCount = sheet.getLastRowNum();
			@SuppressWarnings("unused")
			int colCount = sheet.getRow(0).getLastCellNum();
			log.info("Data Parsing from Excel Begins");
			for(int row =1;row<=RowCount;row++) {
				@SuppressWarnings("unused")
				XSSFRow rows = sheet.getRow(row);
				
					String userName = sheet.getRow(row).getCell(0).getStringCellValue();
					String passWord = sheet.getRow(row).getCell(1).getStringCellValue();
					String receiverEmailAddress = sheet.getRow(row).getCell(2).getStringCellValue();
					String emailSubject = sheet.getRow(row).getCell(3).getStringCellValue();

					String mailDraft = sheet.getRow(row).getCell(4).getStringCellValue();
					
			/*		System.out.println(userName);
					System.out.println(passWord);
					System.out.println(receiverEmailAddress);
					System.out.println(emailSubject);
					System.out.println(mailDraft);
					*/
					
					Object excelValues[] = {userName,passWord,receiverEmailAddress,emailSubject,mailDraft};
					dataStore.add(excelValues);
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found " + e.getMessage());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		log.info("Data Parsing completed - Necessary data's are transferred from excel into an Array");
		return dataStore;
	}	
	
	public static void main(String args[]) {
		readFromExcel();
	}
}


