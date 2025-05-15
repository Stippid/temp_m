//package com.controller.ExportFile;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellCopyPolicy;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.view.document.AbstractExcelView;
//import com.controller.DateWithTimestamp.DateWithTimeStampController;

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
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.controller.DateWithTimestamp.DateWithTimeStampController;

public class Excel_Report_Main extends AbstractExcelView {

	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	
	

	public Excel_Report_Main(String Type, List<String> TH, String Heading, String username) {
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		response.setHeader("Content-disposition", "attachment; filename=\"" + file_name + ".xls\"");

		@SuppressWarnings("unchecked")
		// List<User> list = (List<User>) model.get("userList");
		ArrayList<List<String>> list = (ArrayList<List<String>>) model.get(" REPORT");

		Font cFont = workbook.createFont();
		Font dFont = workbook.createFont();

		cFont.setColor(IndexedColors.WHITE.index);
		dFont.setColor(IndexedColors.BLACK.index);
		// dFont.setBold(true);

		CellStyle cs = workbook.createCellStyle();
		// cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// cs.setBorderBottom(BorderStyle.THIN);
		// cs.setBorderLeft(BorderStyle.THIN);
		// cs.setBorderTop(BorderStyle.THIN);
		// cs.setBorderRight(BorderStyle.THIN);
//		  cs.setLocked(true);
//		  cs.setWrapText(true);

		CellStyle csH = workbook.createCellStyle();
		// csH.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		// csH.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		csH.setFont(dFont);
		// csH.setBorderBottom(BorderStyle.THIN);
		// csH.setBorderLeft(BorderStyle.THIN);
		// csH.setBorderTop(BorderStyle.THIN);
		// csH.setBorderRight(BorderStyle.THIN);
//		  csH.setLocked(true);
		csH.setFont(dFont);

		Sheet sheet = workbook.createSheet(" REPORT");
//		  sheet.protectSheet("password");

		final CellCopyPolicy options = new CellCopyPolicy();
		options.setCopyCellValue(false);

		Row heading = sheet.createRow(0);
		Cell cell_header = heading.createCell(0);
		cell_header.setCellValue(Heading);

		Row header = sheet.createRow(0);
		for (int h = 0; h < TH.size(); h++) {
			Cell cell = header.createCell(h);
			cell.setCellValue(TH.get(h));
			cell.setCellStyle(csH);
		}

		int rowNum = 1;
		for (int i = 0; i < list.size(); i++) {
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