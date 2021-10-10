package com.qa.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class AccessExcel {
	
	
	public String path;
	public FileInputStream fis;
	public HSSFWorkbook workbook;
	private HSSFSheet sheet = null;
	private HSSFRow row   =null;
	private HSSFCell cell = null;

	public AccessExcel(String path){
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
			sheet = workbook.getSheetAt(index);
			int number=sheet.getLastRowNum();
			return number;

		}
	}

	public int getColumnCount(String sheetName){

		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
		}

		if(row==null)
			return -1;

		return row.getLastCellNum();
	}
	
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return "";

			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(0);

			int col_Num=-1;
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1)
				return null;

			row = sheet.getRow(rowNum);
			if(row==null)
				return "";
			cell = row.getCell(col_Num);

			if(cell==null)
				return "";

			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();

			else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC )
				return String.valueOf(cell.getNumericCellValue()).trim();

			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue()).trim();

		}
		catch(Exception e){
			e.printStackTrace();
			// return "row "+rowNum+" or column "+colName +" does not exist in xls";
			return "";
		}

	}

	public boolean CheckTestCaseNamedSheetExist(String filelocation, String TESTCASE_NAME) throws Exception{
		FileInputStream ipstr = null;
		HSSFWorkbook wb = null;
		boolean sheetExist=false;
		try{
			ipstr = new FileInputStream(filelocation);
			wb = new HSSFWorkbook(ipstr);
			ipstr.close();
			for(int k=0; k<wb.getNumberOfSheets(); k++){
				if(wb.getSheetName(k).equalsIgnoreCase(TESTCASE_NAME)){
					sheetExist=true;
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			ipstr = null;
			if(wb!=null){
				wb.close();
				wb=null;
			}
		}
		return sheetExist;
	}


}
