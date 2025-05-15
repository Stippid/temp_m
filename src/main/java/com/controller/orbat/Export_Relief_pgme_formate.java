package com.controller.orbat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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

public class Export_Relief_pgme_formate extends AbstractExcelView{
	
	String Type = "";
	List<String> TH;
	ArrayList<List<String>>smlist;
	
	String username = "";
	
	public Export_Relief_pgme_formate(String Type,List<String> TH,String username,
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
		 response.setHeader("Content-disposition", "attachment; filename=\"" + username + "_" + file_name + ".xls\"");
		 
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
		  
		  Sheet sheet = workbook.createSheet(username);
		  
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
		  
		    DataValidationHelper validationHelper = sheet.getDataValidationHelper();

		 // Add dropdown list for "Indn/De-Indn Pd" column (index 7)
		    DataValidationConstraint indnDeIndnPdConstraint = validationHelper.createExplicitListConstraint(new String[]{"0 Week","1 Week","2 Week","3 Week","4 Week","5 Week","6 Week","7 Week","8 Week","9 Week","10 Week","11 Week","12 Week","13 Week","14 Week","15 Week"});
		    int indnDeIndnPdCol = 6; // Column index for "Indn/De-Indn Pd"
		    int firstRow = 1; // Start from the first data row (assuming header is at row 0)
		    int lastRow = 1000; // The last row of the data
		    
		    if (lastRow >= firstRow) {
		        CellRangeAddressList indnDeIndnPdAddressList = new CellRangeAddressList(firstRow, lastRow, indnDeIndnPdCol, indnDeIndnPdCol);
		        DataValidation indnDeIndnPdValidation = validationHelper.createValidation(indnDeIndnPdConstraint, indnDeIndnPdAddressList);
//		        indnDeIndnPdValidation.setSuppressDropDownArrow(true); // Suppress the drop-down arrow if desired
		        sheet.addValidationData(indnDeIndnPdValidation);
		    } else {
		        throw new IllegalArgumentException("Invalid row range for Indn/De-Indn Pd data validation.");
		    }
		    
	}
	
}
