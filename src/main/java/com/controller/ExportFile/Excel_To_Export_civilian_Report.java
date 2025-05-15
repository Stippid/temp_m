package com.controller.ExportFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.lowagie.text.Paragraph;

public class Excel_To_Export_civilian_Report extends AbstractExcelView {

	String Type = "";
	List<String> TH;List<String> TH1;List<String> TH2;List<String> TH3;List<String> TH4;
	ArrayList<ArrayList<String>>authnominalrolllist;ArrayList<ArrayList<String>>authcivlist;ArrayList<ArrayList<String>>postedcivlist;
	ArrayList<ArrayList<String>>summarylist;ArrayList<ArrayList<String>>non_regular_civ_list;	
	String Heading = "";
	String username = "";
	String civilian_status = "";
	
	public Excel_To_Export_civilian_Report(String Type,List<String> TH,List<String> TH1,List<String> TH2,List<String> TH3,List<String> TH4,
			String Heading,String username,String civilian_status,
			ArrayList<ArrayList<String>>authnominalrolllist,ArrayList<ArrayList<String>>authcivlist,ArrayList<ArrayList<String>>postedcivlist,
		ArrayList<ArrayList<String>> summarylist,ArrayList<ArrayList<String>> non_regular_civ_list){
		this.Type = Type;
		this.TH = TH;this.TH1 = TH1;this.TH2 = TH2;this.TH3 = TH3;this.TH4 = TH4;
		this.Heading = Heading;
		this.authnominalrolllist=authnominalrolllist;this.authcivlist=authcivlist;this.postedcivlist=postedcivlist;this.summarylist=summarylist;this.non_regular_civ_list=non_regular_civ_list;	
		this.username = username;this.civilian_status = civilian_status;
	}

	
	
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
		  
		  Sheet sheet = workbook.createSheet("nominalroll List");
		  
		  final CellCopyPolicy options = new CellCopyPolicy();
		  options.setCopyCellValue(false);
		  
		  Row heading = sheet.createRow(0);
		  Cell cell_header = heading.createCell(0);
		  cell_header.setCellValue(Heading);
		  
		  
		  //table 1
		  
		  Row header = sheet.createRow(0);
		  for(int h=0;h<TH.size();h++) {
			  Cell cell = header.createCell(h);
			  cell.setCellValue(TH.get(h));
			  cell.setCellStyle(csH);
		  }
		
		  int rowNum = 1;	  	  
		  for(int i=0;i< authnominalrolllist.size();i++) {
				List<String> l = authnominalrolllist.get(i);
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
		  
		  
		  //end table 1
		  
		  
		  if(civilian_status.equals("R")) {
		  //table 2
		  
		  Sheet sheet2 = workbook.createSheet("Authciv List");
		  
		  final CellCopyPolicy options2 = new CellCopyPolicy();
		  options2.setCopyCellValue(false);
		  
		  Row heading2 = sheet2.createRow(0);
		  Cell cell_header2 = heading2.createCell(0);
		  cell_header2.setCellValue(Heading);
		  
		  
		  Row header2 = sheet2.createRow(0);
		  for(int h2=0;h2<TH1.size();h2++) {
			  Cell cell = header2.createCell(h2);
			  cell.setCellValue(TH1.get(h2));
			  cell.setCellStyle(csH);
		  }
		  
		  int secNum = 1; 
		  for(int i2=0;i2< authcivlist.size();i2++) {
			  List<String> l = authcivlist.get(i2);
			  Row row = sheet2.createRow(secNum++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d1=0;d1<TH1.size();d1++) {
			  sheet2.autoSizeColumn(d1,true);
		  }
		  		
		  //end table 2		  
		  
		  
		  //table 3
		  
		  Sheet sheet3 = workbook.createSheet("Postedciv List");
		  
		  final CellCopyPolicy options3 = new CellCopyPolicy();
		  options3.setCopyCellValue(false);
		  
		  Row heading3 = sheet3.createRow(0);
		  Cell cell_header3 = heading3.createCell(0);
		  cell_header3.setCellValue(Heading);
		  
		  
		  Row header3 = sheet3.createRow(0);
		  for(int h3=0;h3<TH2.size();h3++) {
			  Cell cell = header3.createCell(h3);
			  cell.setCellValue(TH2.get(h3));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthNum = 1; 
		  for(int i3=0;i3< postedcivlist.size();i3++) {
			  List<String> l = postedcivlist.get(i3);
			  Row row = sheet3.createRow(forthNum++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH2.size();d3++) {
			  sheet3.autoSizeColumn(d3,true);
		  }
		  
		
		  //end table 3
		  
		  

		  
 Sheet sheet4 = workbook.createSheet("Summary List");
		  
		  final CellCopyPolicy options4 = new CellCopyPolicy();
		  options4.setCopyCellValue(false);
		  
		  Row heading4 = sheet4.createRow(0);
		  Cell cell_header4 = heading4.createCell(0);
		  cell_header4.setCellValue(Heading);
		  
		  
		  Row header4 = sheet4.createRow(0);
		  for(int h4=0;h4<TH3.size();h4++) {
			  Cell cell = header4.createCell(h4);
			  cell.setCellValue(TH3.get(h4));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthNum4 = 1; 
		  for(int i4=0;i4< summarylist.size();i4++) {
			  List<String> l = summarylist.get(i4);
			  Row row = sheet4.createRow(forthNum4++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH3.size();d3++) {
			  sheet4.autoSizeColumn(d3,true);
		  }
		  }
		  
		  else {
		  
  Sheet sheet5 = workbook.createSheet("Non Regular Civilian List");
		  
		  final CellCopyPolicy options5 = new CellCopyPolicy();
		  options5.setCopyCellValue(false);
		  
		  Row heading5 = sheet5.createRow(0);
		  Cell cell_header5 = heading5.createCell(0);
		  cell_header5.setCellValue(Heading);
		  
		  
		  Row header5 = sheet5.createRow(0);
		  for(int h4=0;h4<TH4.size();h4++) {
			  Cell cell = header5.createCell(h4);
			  cell.setCellValue(TH4.get(h4));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthN = 1; 
		  for(int i3=0;i3< non_regular_civ_list.size();i3++) {
			  List<String> l = non_regular_civ_list.get(i3);
			  Row row = sheet5.createRow(forthN++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH4.size();d3++) {
			  sheet5.autoSizeColumn(d3,true);
		  }
		  

		  }
	}
}
