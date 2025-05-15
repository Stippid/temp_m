package com.controller.tms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;

public class Export_C_Vehicle_Excel extends AbstractExcelView{
	
	String Type = "";
	List<String> TH;
	ArrayList<List<String>>smlist;
	
	String username = "";
	
	public Export_C_Vehicle_Excel(String Type,List<String> TH,String username,
			ArrayList<List<String>>smlist){
		this.Type = Type;
		this.TH = TH;
		
		this.smlist=smlist;
		this.username = username;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		 response.setHeader("Content-disposition", "attachment; filename=\""+file_name+".xls\"");
		 
		 @SuppressWarnings("unchecked")
		  //List<User> list = (List<User>) model.get("userList");
		  ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList"); 
		 
		 
		  Font cFont = workbook.createFont();
		  Font dFont = workbook.createFont();
		  
		  
		  cFont.setColor(IndexedColors.WHITE.index);
		  dFont.setColor(IndexedColors.BLACK.index);
		  
		  HSSFFont font = workbook.createFont();
		  font.setFontName(HSSFFont.FONT_ARIAL);
		  font.setFontHeightInPoints((short)10);
		  font.setBold(true);
		 // dFont.setBold(true);

		  CellStyle cs = workbook.createCellStyle();

		  CellStyle csH = workbook.createCellStyle();

		  csH.setFont(font);
		  
		  Sheet sheet = workbook.createSheet("C Vehicle EMAR");
		  
		  final CellCopyPolicy options = new CellCopyPolicy();
		  options.setCopyCellValue(false);
		  
		  Row heading = sheet.createRow(0);
		  Cell cell_header = heading.createCell(0);
		 
		  
		  //table 1
		  
		  Row header = sheet.createRow(0);
		  for(int h=0;h<TH.size();h++) {
			  Cell cell = header.createCell(h);
			  cell.setCellValue(TH.get(h));
			  cell.setCellStyle(csH);
		  }
		  
		  int rowNum = 1;	  	  
		  for(int i=0;i< smlist.size();i++) {
				List<String> l = smlist.get(i);
				Row row = sheet.createRow(rowNum++);
		  		for(int j = 0;j<l.size();j++) {
		  			Cell cell = row.createCell(j);
					  cell.setCellValue(l.get(j));
					  cell.setCellStyle(cs);
		  		}
			}
		  
		  for(int d=0;d<TH.size();d++) {
			  sheet.autoSizeColumn(d,true);
		  }
		  
		  

		  Sheet sheet1 = workbook.getSheetAt(0); 
				
			  /*List<Integer> lockedColumns = Arrays.asList(0);*/
			  List<Integer> lockedColumns = Arrays.asList(0,1,2,3,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22);
			
			  DataValidationHelper helper = sheet1.getDataValidationHelper();
		
			  DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[] {});
			
			  for (int columnIndex : lockedColumns) {
			      CellRangeAddressList addressList = new CellRangeAddressList(0, sheet1.getLastRowNum(), columnIndex, columnIndex);
			      DataValidation dataValidation = helper.createValidation(constraint, addressList);
			      dataValidation.setSuppressDropDownArrow(true);
			      dataValidation.setShowErrorBox(true);
			      sheet1.addValidationData(dataValidation);
			  }
		  
	}
	
}
