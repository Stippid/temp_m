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


public class Excel_To_Export_inf_off_Table_Report extends AbstractExcelView {

	String Type = "";
	List<String> TH;
	//List<String> TH1;
	ArrayList<ArrayList<String>>smlist;
	//ArrayList<ArrayList<String>>fmnlist;
	String Heading = "";
	String username = "";
	
	public Excel_To_Export_inf_off_Table_Report(String Type,
			List<String> TH,
			/*List<String> TH1,*/String Heading,String username,
			ArrayList<ArrayList<String>>smlist/*,ArrayList<ArrayList<String>>fmnlist*/){
		this.Type = Type;
		this.TH = TH;
		//this.TH1 = TH1;
this.Heading = Heading;
		this.smlist=smlist;
		/*this.fmnlist=fmnlist;*/
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
		  
		  
		  //end table 1
		  
		  //table 2
		  
		  /*Sheet sheet2 = workbook.createSheet("User List2");
		  
		  final CellCopyPolicy options2 = new CellCopyPolicy();
		  options2.setCopyCellValue(false);*/
		  /*
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
		  for(int i2=0;i2< fmnlist.size();i2++) {
			  List<String> l = fmnlist.get(i2);
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
		  */
		
		  //end table 2
	}
}
