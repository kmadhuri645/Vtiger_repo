package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	/**
	* its used read the data from excel base don below arguments
	* @param sheetName
	* @param rowNum
	* @param celNum
	* @return Data
	* @throws Throwable
	*/

	public String getDataFromExcel(String sheetName ,int rowNum ,int celNum) throws Throwable {
		
		FileInputStream fis=new FileInputStream("./configAppData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
		
	}
	/**
	* used to get the last used row number on specified Sheet
	* @param sheetName
	* @return
	* @throws Throwable
	*/
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis=new FileInputStream("./configAppData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
		
	}
	/*
	 * it used to write back to the data from the excel
	 */
	public void setDataIntoExcel(String sheetName ,int rowNum ,int celNum ,String data) throws Throwable {
		FileInputStream fis=new FileInputStream("./testData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos=new FileOutputStream("./configAppData/testData.xlsx");
		wb.write(fos);
		wb.close();//back excelsheet side one object open always not in java program side once excecution is done garbase collector will clean the memory.
		           //if you dont close 100times open wb than next time you rty to oepn manually it will crashed.
	}
}
