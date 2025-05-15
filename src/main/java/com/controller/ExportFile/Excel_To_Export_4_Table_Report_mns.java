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


public class Excel_To_Export_4_Table_Report_mns extends AbstractExcelView{

	String Type = "";
	List<String> TH;List<String> TH1;
//	List<String> TH2;
	List<String> TH3;List<String> TH4;List<String> TH5;List<String> TH6;
	ArrayList<ArrayList<String>>smlist;ArrayList<ArrayList<String>>relist;
//	ArrayList<ArrayList<String>>agelist;
	ArrayList<ArrayList<String>>stlist;
	ArrayList<ArrayList<String>>authlist;ArrayList<ArrayList<String>>list_held;
	Integer sum_auth = 0;Integer sum_held = 0;Integer defi= 0;Integer sur= 0;
	String Heading = "";
	String username = "";
	
	public Excel_To_Export_4_Table_Report_mns(String Type,List<String> TH,List<String> TH1,List<String> TH3,
			List<String> TH4,List<String> TH5,String Heading,String username,
			ArrayList<ArrayList<String>>smlist,ArrayList<ArrayList<String>>relist,
//			ArrayList<ArrayList<String>>agelist,
			ArrayList<ArrayList<String>>stlist,
			ArrayList<ArrayList<String>>authlist,Integer sum_auth,Integer sum_held,Integer defi,Integer sur,List<String> TH6,ArrayList<ArrayList<String>> list_held){
		this.Type = Type;
		this.TH = TH;this.TH1 = TH1;
//		this.TH2 = TH2;
		this.TH3 = TH3;this.TH4 = TH4;this.TH5 = TH5;this.TH6 = TH6;
		this.Heading = Heading;
		this.smlist=smlist;this.relist=relist;
//		this.agelist=agelist;
		this.stlist=stlist;this.authlist=authlist;this.list_held=list_held;
		this.sum_auth=sum_auth;this.sum_held=sum_held;this.defi=defi;this.sur=sur;
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
		  for(int i2=0;i2< relist.size();i2++) {
			  List<String> l = relist.get(i2);
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
		  
//		  //table 3
//		  
//		  Sheet sheet3 = workbook.createSheet("User List3");
//		  
//		  final CellCopyPolicy options3 = new CellCopyPolicy();
//		  options2.setCopyCellValue(false);
//		  
//		  Row heading3 = sheet3.createRow(0);
//		  Cell cell_header3 = heading3.createCell(0);
//		  cell_header3.setCellValue(Heading);
//		  
//		  
//		  Row header3 = sheet3.createRow(0);
//		  for(int h3=0;h3<TH2.size();h3++) {
//			  Cell cell = header3.createCell(h3);
//			  cell.setCellValue(TH2.get(h3));
//			  cell.setCellStyle(csH);
//		  }
//		  
//		  int thirdNum = 1; 
//		  for(int i3=0;i3< agelist.size();i3++) {
//			  List<String> l = agelist.get(i3);
//			  Row row = sheet3.createRow(thirdNum++);
//			  for(int j = 0;j<l.size();j++) {
//				  Cell cell = row.createCell(j);
//				  cell.setCellValue(l.get(j));
//				  cell.setCellStyle(cs);
//			  }
//		  }
//		  
//		  for(int d2=0;d2<TH2.size();d2++) {
//			  sheet3.autoSizeColumn(d2,true);
//		  }
//		  
//		
//		  //end table 3
		  
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
		  for(int i4=0;i4< stlist.size();i4++) {
			  List<String> l = stlist.get(i4);
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
		  for(int i4=0;i4< authlist.size();i4++) {
			  List<String> l = authlist.get(i4);
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
		  
Sheet sheet7 = workbook.createSheet("User List7");
		  
		  final CellCopyPolicy options7 = new CellCopyPolicy();
		  options7.setCopyCellValue(false);
		  
		  Row heading7 = sheet7.createRow(0);
		  Cell cell_header7 = heading7.createCell(0);
		  cell_header7.setCellValue(Heading);
		  
		  
		  Row header7 = sheet7.createRow(0);
		  for(int h5=0;h5<TH6.size();h5++) {
			  Cell cell = header7.createCell(h5);
			  cell.setCellValue(TH6.get(h5));
			  cell.setCellStyle(csH);
		  }
		  
		  int forthNum7 = 1; 
		  for(int i4=0;i4< list_held.size();i4++) {
			  List<String> l = list_held.get(i4);
			  Row row = sheet7.createRow(forthNum7++);
			  for(int j = 0;j<l.size();j++) {
				  Cell cell = row.createCell(j);
				  cell.setCellValue(l.get(j));
				  cell.setCellStyle(cs);
			  }
		  }
		  
		  for(int d3=0;d3<TH6.size();d3++) {
			  sheet7.autoSizeColumn(d3,true);
		  }
		  
 Sheet sheet6 = workbook.createSheet("User List6");
		  
		  final CellCopyPolicy option6 = new CellCopyPolicy();
		  option6.setCopyCellValue(false);
		  
		  Row heading6 = sheet6.createRow(0);
		  Cell cell_header6 = heading6.createCell(0);
		  cell_header6.setCellValue(Heading);
		  
		  
		  Row heading61 = sheet6.createRow(0);
		  for(int h5=0;h5<TH5.size();h5++) {
			  Cell cell = heading61.createCell(h5);
			  cell.setCellValue(TH5.get(h5));
			  cell.setCellStyle(csH);
		  }
		  
				
				  Row row = sheet6.createRow(1);
				  Cell cell = row.createCell(0);
				  cell.setCellValue(sum_auth);
				   cell.setCellStyle(cs);
				   Cell cell2 = row.createCell(1);
				   cell2.setCellValue(sum_held);
				   cell2.setCellStyle(cs);
					   Cell cell3 = row.createCell(2);
					   cell3.setCellValue(sur);
					   cell3.setCellStyle(cs);
						   Cell cell4 = row.createCell(3);
						   cell4.setCellValue(defi);
						   cell4.setCellStyle(cs);
						  
					  
		  
		  
		  for(int d3=0;d3<TH5.size();d3++) {
			  sheet5.autoSizeColumn(d3,true);
		  }
		  
	}
}
