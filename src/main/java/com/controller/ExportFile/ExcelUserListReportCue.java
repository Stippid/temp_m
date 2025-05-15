package com.controller.ExportFile;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;

public class ExcelUserListReportCue extends AbstractExcelView {

	String Type = "";
	List<String> TH;
	String Heading = "";
	String Heading1 = "";
	String username = "";
	ArrayList<List<String>> list;

	public ExcelUserListReportCue(String Type,List<String> TH,String Heading,String username){
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		
	}


	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest arg2,
	        HttpServletResponse response) throws Exception {
	    DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
	    String file_name = datetimestamp.currentDateWithTimeStampString();
	    response.setHeader("Content-disposition", "attachment; filename=\""+ Heading + file_name + ".xls\"");

	    @SuppressWarnings("unchecked")
	    ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList");

	    Font cFont = workbook.createFont();
	    Font dFont = workbook.createFont();
	    cFont.setColor(IndexedColors.WHITE.index);
	    dFont.setColor(IndexedColors.BLACK.index);
	    CellStyle cs = workbook.createCellStyle();
	    CellStyle csH = workbook.createCellStyle();
	    csH.setFont(dFont);
	    csH.setFont(dFont);

	    int maxRowsPerSheet = 65535; // Maximum number of rows per sheet
	    int sheetCount = (int) Math.ceil((double) list.size() / maxRowsPerSheet);
	   
	    for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
	        Sheet sheet = workbook.createSheet("Spare Meta Data" + (sheetIndex + 1));
	        Row header = sheet.createRow(0);
	        for (int h = 0; h < TH.size(); h++) {
	            Cell cell = header.createCell(h);
	            cell.setCellValue(TH.get(h));
	            cell.setCellStyle(csH);
	        }
	        int startRow = sheetIndex * maxRowsPerSheet;
	        int endRow = Math.min(startRow + maxRowsPerSheet, list.size());
	        int rowNum = 1;
	        for (int i = startRow; i < endRow; i++) {
	            List<String> l = list.get(i);
	            Row row = sheet.createRow(rowNum++);
	            for (int j = 0; j < l.size(); j++) {
	                Cell cell = row.createCell(j);
	                cell.setCellValue(l.get(j));
	                cell.setCellStyle(cs);
	            }
	        }
	        for (int d = 0; d < TH.size(); d++) {
	            sheet.autoSizeColumn(d, true);
	        }
	    }
	}
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest arg2,
//			HttpServletResponse response) throws Exception {
//		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
//		String file_name = datetimestamp.currentDateWithTimeStampString();
//		 response.setHeader("Content-disposition", "attachment; filename=\""+file_name+".xls\"");
//		 
//		 @SuppressWarnings("unchecked")
//		  ArrayList<List<String>> list = (ArrayList<List<String>>) model.get("userList"); 
//		
//		  Font cFont = workbook.createFont();
//		  Font dFont = workbook.createFont();
//		  cFont.setColor(IndexedColors.WHITE.index);
//		  dFont.setColor(IndexedColors.BLACK.index);
//		  CellStyle cs = workbook.createCellStyle();
//		  CellStyle csH = workbook.createCellStyle();
//		  csH.setFont(dFont);
//		  csH.setFont(dFont);
//		  //
//		  Sheet sheet = workbook.createSheet("User List");
//		  final CellCopyPolicy options = new CellCopyPolicy();
//		  options.setCopyCellValue(false);
//		  Row heading = sheet.createRow(0);
//		  Cell cell_header = heading.createCell(0);
//		  cell_header.setCellValue(Heading);
//		  Row header = sheet.createRow(0);
//		  for(int h=0;h<TH.size();h++) {
//			  Cell cell = header.createCell(h);
//			  cell.setCellValue(TH.get(h));
//			  cell.setCellStyle(csH);
//		  }
//		  int rowNum = 1;
//		  for(int i=0;i< list.size();i++) {
//		  		List<String> l = list.get(i);
//		  		Row row = sheet.createRow(rowNum++);
//		  		for(int j = 0;j<l.size();j++) {
//		  			Cell cell = row.createCell(j);
//					  cell.setCellValue(l.get(j));
//					  cell.setCellStyle(cs);
//		  		}
//		  	}
//		  for(int d=0;d<TH.size();d++) {
//			  sheet.autoSizeColumn(d,true);
//		  }
//	}
}
