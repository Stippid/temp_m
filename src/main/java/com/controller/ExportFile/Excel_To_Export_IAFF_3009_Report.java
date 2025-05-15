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

public class Excel_To_Export_IAFF_3009_Report extends AbstractExcelView {

	String Type = "";
	List<String> TH;List<String> TH1;List<String> TH2;List<String> TH3;List<String> TH4;List<String> TH5;List<String> TH6;
	ArrayList<ArrayList<String>>authoffrslist;ArrayList<ArrayList<String>>authcivlist;ArrayList<ArrayList<String>>postedoffrslist;ArrayList<ArrayList<String>>postedcivlist;
	ArrayList<ArrayList<String>>tradelist;ArrayList<ArrayList<String>>rdlist;ArrayList<ArrayList<String>>summarylist;	
	String Heading = "";
	String username = "";
	
	public Excel_To_Export_IAFF_3009_Report(String Type,List<String> TH,List<String> TH1,List<String> TH2,List<String> TH3,
			List<String> TH4,List<String> TH5,List<String> TH6,String Heading,String username,
			ArrayList<ArrayList<String>>authoffrslist,ArrayList<ArrayList<String>>authcivlist,ArrayList<ArrayList<String>>postedoffrslist,ArrayList<ArrayList<String>>postedcivlist,
			ArrayList<ArrayList<String>>tradelist,ArrayList<ArrayList<String>> rdlist,ArrayList<ArrayList<String>> summarylist){
		this.Type = Type;
		this.TH = TH;this.TH1 = TH1;this.TH2 = TH2;this.TH3 = TH3;this.TH4 = TH4;this.TH5 = TH5;this.TH6 = TH6;
		this.Heading = Heading;
		this.authoffrslist=authoffrslist;this.authcivlist=authcivlist;this.postedoffrslist=postedoffrslist;this.postedcivlist=postedcivlist;this.tradelist=tradelist;this.rdlist=rdlist;this.summarylist=summarylist;	
		this.username = username;
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
		  
		  Sheet sheet = workbook.createSheet("User List");
		  
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
		  for(int i=0;i< authoffrslist.size();i++) {
				List<String> l = authoffrslist.get(i);
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
		  
		  //table 2
		  
		  Sheet sheet2 = workbook.createSheet("User List2");
		  
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
		  
		  Sheet sheet3 = workbook.createSheet("User List3");
		  
		  final CellCopyPolicy options3 = new CellCopyPolicy();
		  options2.setCopyCellValue(false);
		  
		  Row heading3 = sheet3.createRow(0);
		  Cell cell_header3 = heading3.createCell(0);
		  cell_header3.setCellValue(Heading);
		  
		  
		  Row header3 = sheet3.createRow(0);
		  for(int h3=0;h3<TH2.size();h3++) {
			  Cell cell = header3.createCell(h3);
			  cell.setCellValue(TH2.get(h3));
			  cell.setCellStyle(csH);
		  }
		  
		  int thirdNum = 1; 
		  for(int i3=0;i3< postedoffrslist.size();i3++) {
			  List<String> l = postedoffrslist.get(i3);
			  Row row = sheet3.createRow(thirdNum++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d2=0;d2<TH2.size();d2++) {
			  sheet3.autoSizeColumn(d2,true);
		  }
		  
		
		  //end table 3
		  
		  //table 4
		  
		  Sheet sheet4 = workbook.createSheet("User List4");
		  
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
		  
		  int forthNum = 1; 
		  for(int i4=0;i4< postedcivlist.size();i4++) {
			  List<String> l = postedcivlist.get(i4);
			  Row row = sheet4.createRow(forthNum++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH3.size();d3++) {
			  sheet4.autoSizeColumn(d3,true);
		  }
		  
		
		  //end table 4
		  
		  
 Sheet sheet5 = workbook.createSheet("User List5");
		  
		  final CellCopyPolicy options5 = new CellCopyPolicy();
		  options5.setCopyCellValue(false);
		  
		  Row heading5 = sheet5.createRow(0);
		  Cell cell_header5 = heading5.createCell(0);
		  cell_header5.setCellValue(Heading);
		  
		  
		  Row header5 = sheet5.createRow(0);
		  for(int h5=0;h5<TH4.size();h5++) {
			  Cell cell = header5.createCell(h5);
			  cell.setCellValue(TH4.get(h5));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthNum1 = 1; 
		  for(int i4=0;i4< tradelist.size();i4++) {
			  List<String> l = tradelist.get(i4);
			  Row row = sheet5.createRow(forthNum1++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH4.size();d3++) {
			  sheet5.autoSizeColumn(d3,true);
		  }
		  
		  
 Sheet sheet8 = workbook.createSheet("User List8");
		  
		  final CellCopyPolicy options8 = new CellCopyPolicy();
		  options8.setCopyCellValue(false);
		  
		  Row heading8 = sheet8.createRow(0);
		  Cell cell_header8 = heading8.createCell(0);
		  cell_header8.setCellValue(Heading);
		  
		  
		  Row header8 = sheet8.createRow(0);
		  for(int h6=0;h6<TH6.size();h6++) {
			  Cell cell = header8.createCell(h6);
			  cell.setCellValue(TH6.get(h6));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthNum8 = 1; 
		  for(int i4=0;i4< summarylist.size();i4++) {
			  List<String> l = summarylist.get(i4);
			  Row row = sheet8.createRow(forthNum8++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH6.size();d3++) {
			  sheet8.autoSizeColumn(d3,true);
		  }
		  
Sheet sheet7 = workbook.createSheet("User List7");
		  
		  final CellCopyPolicy options7 = new CellCopyPolicy();
		  options7.setCopyCellValue(false);
		  
		  Row heading7 = sheet7.createRow(0);
		  Cell cell_header7 = heading7.createCell(0);
		  cell_header7.setCellValue(Heading);
		  
		  
		  Row header7 = sheet7.createRow(0);
          for(int h6=0;h6<TH5.size();h6++) {
                  Cell cell = header7.createCell(h6);
                  cell.setCellValue(TH5.get(h6));
                  cell.setCellStyle(csH);
          }
		  
		  int forthNum7 = 1; 
		  for(int i4=0;i4< rdlist.size();i4++) {
			  List<String> l = rdlist.get(i4);
			  Row row = sheet7.createRow(forthNum7++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		
		  

		  
	}
}
