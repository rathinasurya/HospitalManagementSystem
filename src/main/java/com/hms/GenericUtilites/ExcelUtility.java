package com.hms.GenericUtilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {


	public String getDataFromExcel(String SheetName, int rowNum,int cellNum) throws Throwable, Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(rowNum);
	    String data = row.getCell(cellNum).getStringCellValue();
	    return data;
	}
	
	public void setDataFromExcel(String SheetName, int rowNum, int cellNum, String data) throws Throwable, Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(rowNum);
		row.getCell(cellNum).setCellValue(data);
		FileOutputStream fos=new FileOutputStream(IpathConstants.Excelpath);
		wb.write(fos);
		wb.close();
		
	}
	
	public int getRowCount(String SheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb = WorkbookFactory.create(fis);
		int sheetName = wb.getSheet(SheetName).getLastRowNum();
		return sheetName;
	}
	
	public short getCellCount(String SheetName,int Row) throws Throwable, IOException
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb = WorkbookFactory.create(fis);
		 short sheet = wb.getSheet(SheetName).getRow(Row).getLastCellNum();
		 return sheet;
		
	}
	
	public Map<String, String> getList(String SheetName,int keyCell, int valueCell) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int count=sh.getLastRowNum();
		Map<String, String> map=new HashMap<String, String>();
		for(int i=0;i<=count;i++)
		{
			String key=sh.getRow(i).getCell(keyCell).getStringCellValue();
			String value=sh.getRow(i).getCell(valueCell).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}
	
	public String[][] readMultipleSetOfData(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.Excelpath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum()+1;
		int lastcell = sheet.getRow(0).getLastCellNum();
		String[][] ob=new String[lastRow][lastcell];
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastcell;j++)
			{
				 ob[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return ob;
		
	}
	
	
}
