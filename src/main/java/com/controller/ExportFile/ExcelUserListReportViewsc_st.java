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


public class ExcelUserListReportViewsc_st extends AbstractExcelView  {
	String Type = "";
	List<String> TH;
	//List<String> CauseList;
	List<String> CauseList1;
	ArrayList<ArrayList<String>>smlist;
	String Heading = "";
	String username = "";
	//ArrayList<ArrayList<String>> Excel_P;
	
	public ExcelUserListReportViewsc_st(String Type,List<String> TH,List<String> CauseList1,String Heading,String username,ArrayList<ArrayList<String>>smlist){
		this.Type = Type;
		this.TH = TH;
		//this.CauseList = CauseList;
		this.CauseList1 = CauseList1;
		this.Heading = Heading;
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
		  
		  
		  CellStyle cs1 = workbook.createCellStyle();

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
		  
		 
		  int rowNum1 = 1;	
		  int k = 0;
		  int n=27;
		  int q=54;
		  int s=62;
		  int size_k=3;
		  int size_n=30;
		  int size_q=55;
		  int size_b=64;
		  for (Object o : CauseList1) {
			  Row row = sheet.createRow(rowNum1++);
			  Cell cell = row.createCell(0);
			  cell.setCellValue(o.toString());
			  cell.setCellStyle(cs1);
			/*
			 * Cell cell1 = row.createCell(1); cell1.setCellValue(smlist.get(0).get(k));
			 * cell1.setCellStyle(cs1);
			 */
			  int j=0;
			
			  if(size_k < 28)
			  {
				  for(k=k;k<size_k;k++)
				  {
					  j++;
					 
					  Cell cell1 = row.createCell(j); 
					  cell1.setCellValue(smlist.get(0).get(k));
					  cell1.setCellStyle(cs1);
				  }
			  }
			
			  size_k=size_k+3;
			  
			  
			   int p =4;
				  
				  if(size_q < 64)
				  {
					  for(q=q;q<size_q;q++)
					  {
						  
						 
						  Cell cell2 = row.createCell(p); 
						  cell2.setCellValue(smlist.get(0).get(q));
						  cell2.setCellStyle(cs1);
					  }
				  }
				  size_q=size_q+1;
				  
				
			  int m =4;
			  
			  if(size_n < 55)
			  {
				  for(n=n;n<size_n;n++)
				  {
					  m++;
					 
					  Cell cell1 = row.createCell(m); 
					  cell1.setCellValue(smlist.get(0).get(n));
					  cell1.setCellStyle(cs1);
				  }
			  }
			  size_n=size_n+3;
			  
			  
			  
			  
			  ///////////////
			  
			  
			  
			  int b =8;
			  
			  if(size_b < 73)
			  {
				  for(s=s;s<size_b;s++)
				  {
					  
					 
					  Cell cell2 = row.createCell(b); 
					  cell2.setCellValue(smlist.get(0).get(s));
					  cell2.setCellStyle(cs1);
				  }
			  }
			  size_b=size_b+1;
			  
			/*
			 * Cell cell2 = row.createCell(1); cell2.setCellValue(smlist.get(0).get(k));
			 * cell2.setCellStyle(cs1); k++;
			 */
			  
			  
			 
   
			  
			  
		  }
		  
		  
		  
		  
		  for(int d=0;d<TH.size();d++) {
			  sheet.autoSizeColumn(d,true);
		  } 
		  
		  //table 1 end-------------------------------
		  
	}
}