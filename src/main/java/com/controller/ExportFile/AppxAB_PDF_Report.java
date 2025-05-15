package com.controller.ExportFile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class AppxAB_PDF_Report extends AbstractPdfView {
	String Type = "";
	List<String> TH_A;
	List<String> TH_B;
	List<String> TH_C;
	List<String> TH_D;
	String Heading = "";
	String username = "";
	String unit_sus_no = "";
	String month = "";
	String year = "";
	List<ArrayList<String>> listA;
	List<ArrayList<String>> listB;
	List<ArrayList<String>> listC;
	List<ArrayList<String>> listD;
	List<ArrayList<String>> listS;
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	int i = 0;

	public AppxAB_PDF_Report(List<String> TH_A, List<String> TH_B, List<String> TH_C, List<String> TH_D, String username,
			String Heading, List<ArrayList<String>> listA, List<ArrayList<String>> listB,
			List<ArrayList<String>> listC, List<ArrayList<String>> listD, List<ArrayList<String>> listS,String unit_sus_no,String month,String year) {
		this.TH_A = TH_A;
		this.TH_B = TH_B;
		this.TH_C = TH_C;
		this.TH_D = TH_D;
		this.Heading = Heading;
		this.username = username;
		this.listA = listA;
		this.listB = listB;
		this.listC = listC;
		this.listD = listD;
		this.listS = listS;
		this.unit_sus_no = unit_sus_no;
		this.month = month;
		this.year = year;
	}

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Image logo = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "indianarmy_smrm5aaa.jpg";
			logo = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(6);
		logo.scaleAbsoluteWidth(6);
		logo.scalePercent(18);
		Chunk chunk = new Chunk(logo, 0, -4);
		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "indianarmy_smrm5aaa.jpg";
			logo2 = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(6);
		logo2.scaleAbsoluteWidth(6);
		logo2.scalePercent(18);
		Chunk chunk2 = new Chunk(logo2, 10, -4);
		Chunk underline = new Chunk("RESTRICTED", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		/*Chunk underline2 = new Chunk("" + Heading + ":" + username, fontTableHeadingMainHead);*/
		Chunk underline2 = new Chunk( "APPX 'A' & APPX 'B' TO IAFF-3009 : CIVILIANS" , fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.setFont(fontTableHeading1);
		Paragraph cell = new Paragraph(p);
		cell.setAlignment(Element.ALIGN_CENTER);
		float[] relativeWidths;
		int colunmSize = 3;
		relativeWidths = new float[colunmSize];
		Arrays.fill(relativeWidths, 0, colunmSize, 1);
		relativeWidths[1] = (float) 2.50;
		table3.addCell(new Phrase(chunk));
		table3.addCell(cell);
		table3.addCell(new Phrase(chunk2));
		Phrase p2 = new Phrase();
		try {
			table3.setWidths(relativeWidths);
		} catch (DocumentException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.setWidthPercentage(150);
		p2.add(table3);
		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		Chunk underline1 = new Chunk("RESTRICTED", fontTableHeading1);
		underline1.setUnderline(0.1f, -2f);
		Phrase p1 = new Phrase("" + glue);
		p1.add(underline1);
		p.add(glue);
		p1.setFont(fontTableHeading1);
		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);
		document.setPageCount(1);
		document.setPageSize(PageSize.A4.rotate()); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}

	// Before the table creation, define a helper method to populate cells
	private void populateCellValue(PdfPTable table, String value, int row, int col, List<ArrayList<String>> data) {
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		// Since data is in a single row, we'll always use index 0 for the row of data
		if (!data.isEmpty() && value != null) {
			// Find the index in the data ArrayList that matches our 'value'
			int dataIndex = -1;
			ArrayList<String> dataRow = data.get(0);  // Get the single row of data

			dataIndex = i;

			// If we found a matching value
			if (dataIndex != -1) {
				String cellValue = dataRow.get(dataIndex);
				if (cellValue != null && !cellValue.trim().isEmpty()) {
					// Use the provided row and col for cell placement
					PdfPCell cell = table.getRow(row).getCells()[col];
					if (cell != null) {
						cell.setPhrase(new Phrase(cellValue, fontTableHeadingNonBoldData));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
				}
			}
			i++;
		}
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		//		@SuppressWarnings("unchecked")
		//		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");


		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph p44 = new Paragraph() ;
		Paragraph p60 = new Paragraph() ;



		Paragraph pl;

		Paragraph plv;
		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);
		Font fontTableValue2 = FontFactory.getFont(FontFactory.TIMES, 9);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		////////////////////////////seal ////////
		PdfPTable tableseal = new PdfPTable(1);
		tableseal.getDefaultCell().setBorder(Rectangle.BOX);
		/*PdfPTable tableseali = new PdfPTable(3);
		tableseali.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tableseali.setWidthPercentage(100);
		//		pl = new Paragraph("Unit Seal: ", fontTableHeading);
		//		tableseali.addCell(pl);
		pl = new Paragraph("        I certify that all known personal occurrences have been reported on IAFF-3009.\n\n\n\n\n ", fontTableValue);
		PdfPCell psi6 = new PdfPCell(pl);
		psi6.setColspan(3);
		//psi6.setHorizontalAlignment(Element.ALIGN_LEFT);
		psi6.setBorder(Rectangle.NO_BORDER);
		tableseali.addCell(psi6);
		pl = new Paragraph("\nUnit Seal: ", fontTableHeading);
		tableseali.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tableseali.addCell(pl);
		pl = new Paragraph("(___________)\nSign of Officer", fontTableHeading);
		tableseali.addCell(pl);
		tableseal.addCell(tableseali);*/
		////////////////////////////////
		PdfPTable tablesealS = new PdfPTable(4);
		tablesealS.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablesealS.setWidthPercentage(100);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("Sign of Officer", fontTableHeading);
		tablesealS.addCell(pl);
		//////////////////////////////////////

		PdfPTable tableleft = new PdfPTable(2);

		tableleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		//		PdfPCell pi5 = new PdfPCell(new Paragraph("PERSONAL DETAILS", fontTableheader));
		//		pi5.setColspan(2);
		//		pi5.setBorder(Rectangle.NO_BORDER);
		//		tableleft.addCell(pi5);

		//260194
		pl = new Paragraph("\nSUS No: ", fontTableHeading);
		tableleft.addCell(pl);
	    plv = new Paragraph("\n"+unit_sus_no, fontTableValue);
		 tableleft.addCell(plv);
	     pl = new Paragraph("\nMonth: ", fontTableHeading);
		 tableleft.addCell(pl);
		 plv = new Paragraph("\n"+month, fontTableValue);
		 tableleft.addCell(plv);
		//		}

	
		/*pl = new Paragraph("\nPresent Return No: ", fontTableHeading);
		tableleft.addCell(pl);

		//		if(list_Main.size() > 0)
		//		{
		//			plv = new Paragraph("\n"+list_Main.get(0).get(1), fontTableValue);
		//		}
		//		else
		//		{
		plv = new Paragraph("", fontTableValue);
		//		}
		//e 260194
		tableleft.addCell(plv);
		pl = new Paragraph("\nLast Return No: ", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		tableleft.addCell(plv);*/


		List<String> cont_comd_list = listS.get(0);
		Paragraph cont_comd = new Paragraph();
		cont_comd = new Paragraph(cont_comd_list.get(0), fontTableHeadingNonBoldData);

		List<String> cont_corps_list = listS.get(0);
		Paragraph cont_corps = new Paragraph();
		cont_corps = new Paragraph(cont_corps_list.get(1), fontTableHeadingNonBoldData);



		pl = new Paragraph("\nCont Comd: ", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n"+cont_comd, fontTableValue);
		tableleft.addCell(plv);
		/*pl = new Paragraph("\nCont Corps:", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n"+cont_corps, fontTableValue);
		tableleft.addCell(plv);*/






		Paragraph pr;
		Paragraph prv;
		PdfPTable tableright = new PdfPTable(2);
		tableright.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//		PdfPCell pi6 = new PdfPCell(new Paragraph(" ", fontTableheader));
		//		pi6.setColspan(2);
		//		pi6.setBorder(Rectangle.NO_BORDER);
		//		tableright.addCell(pi6);
		List<String> unit_name_list = listS.get(0);
		Paragraph unit_name = new Paragraph();
		unit_name = new Paragraph(unit_name_list.get(4), fontTableHeadingNonBoldData);
		
		pr = new Paragraph("\nUnit Name :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+unit_name, fontTableValue);
		tableright.addCell(prv);
		pr = new Paragraph("\nYear :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+year, fontTableValue);
		tableright.addCell(prv);

		/*pr = new Paragraph("\nPresent Return Date :", fontTableHeading);
		tableright.addCell(pr);
		//		if(list_Main.size() > 0)
		//		{
		//			prv = new Paragraph("\n"+list_Main.get(0).get(2), fontTableValue);
		//		}
		//		else
		//		{
		prv = new Paragraph("", fontTableValue);
		//		}
		tableright.addCell(prv);
		pr = new Paragraph("\nLast Return Date :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n", fontTableValue);
		tableright.addCell(prv);*/


		List<String> cont_div_list = listS.get(0);
		Paragraph cont_div = new Paragraph();
		cont_div = new Paragraph(cont_div_list.get(2), fontTableHeadingNonBoldData);

		List<String> cont_bde_list = listS.get(0);
		Paragraph cont_bde = new Paragraph();
		cont_bde = new Paragraph(cont_bde_list.get(3), fontTableHeadingNonBoldData);


		/*pr = new Paragraph("\nCont Div: ", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+cont_div, fontTableValue);
		tableright.addCell(prv);
		pr = new Paragraph("\nCont Bde :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+cont_bde, fontTableValue);
		tableright.addCell(prv);*/

		PdfPTable tablecenter = new PdfPTable(3);

		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.setWidthPercentage(100);

		tablecenter.addCell(tableleft);
		tablecenter.addCell(tableseal);

		tablecenter.addCell(tableright);

		PdfPTable mainTable = new PdfPTable(1);
		mainTable.setWidthPercentage(100);
		PdfPCell tableCenterCell = new PdfPCell(tablecenter);
		tableCenterCell.setBorder(Rectangle.NO_BORDER);
		mainTable.addCell(tableCenterCell);
		document.add(mainTable);
		document.add(new Paragraph("\n"));


		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Font boldFont = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, Font.BOLD);
		Paragraph p = new Paragraph();
		Chunk underline5 = new Chunk("CIVILIAN DETAILS", fontTableHeadingMainHead);
		underline5.setUnderline(0.1f, -2f);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// SECTION A TODO
		Paragraph sectionATitle = new Paragraph("SECTION A : AUTH STR (AS PER WE/PE)", fontTableHeadingMainHead);
		sectionATitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell titleCell = new PdfPCell(sectionATitle);
		titleCell.setBorder(Rectangle.NO_BORDER);
		titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		sectionATitle.add(new Paragraph("\n"));
		sectionATitle.add(new Paragraph("\n"));
		
		table.addCell(titleCell);

		// Create Section A table with 7 columns
		PdfPTable tableA = new PdfPTable(7);
		tableA.setWidthPercentage(100);

		// Row 1: Main headers
		PdfPCell gazettedHeader = new PdfPCell(new Paragraph("GAZETTED", fontTableHeadingNonBoldData));
		gazettedHeader.setColspan(2);
		gazettedHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(gazettedHeader);

		PdfPCell nonGazettedHeader = new PdfPCell(new Paragraph("NON GAZETTED", fontTableHeadingNonBoldData));
		nonGazettedHeader.setColspan(4);
		nonGazettedHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(nonGazettedHeader);

		PdfPCell totalHeader = new PdfPCell(new Paragraph("TOTAL", fontTableHeadingNonBoldData));
		totalHeader.setRowspan(3);  // Spans all three rows
		totalHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
		totalHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableA.addCell(totalHeader);

		// Row 2: Sub-headers
		PdfPCell gpA = new PdfPCell(new Paragraph("GP 'A'", fontTableHeadingNonBoldData));
		gpA.setRowspan(2);
		gpA.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(gpA);

		PdfPCell gpB = new PdfPCell(new Paragraph("GP 'B'", fontTableHeadingNonBoldData));
		gpB.setRowspan(2);
		gpB.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(gpB);

		PdfPCell nonIndustrial = new PdfPCell(new Paragraph("NON INDUSTRIAL", fontTableHeadingNonBoldData));
		nonIndustrial.setColspan(2);
		nonIndustrial.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(nonIndustrial);

		PdfPCell industrial = new PdfPCell(new Paragraph("INDUSTRIAL", fontTableHeadingNonBoldData));
		industrial.setColspan(2);
		industrial.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableA.addCell(industrial);

		// Add GP 'B' and GP 'C' for both NON INDUSTRIAL and INDUSTRIAL
		String[] gpCategories = {"GP 'B'", "GP 'C'", "GP 'B'", "GP 'C'"};
		for (String category : gpCategories) {
			PdfPCell cell = new PdfPCell(new Paragraph(category, fontTableHeadingNonBoldData));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableA.addCell(cell);
		}

		// Add data rows
		for (int i = 0; i < listA.size(); i++) {
			List<String> row = listA.get(i);
			for (String value : row) {
				PdfPCell cell = new PdfPCell(new Paragraph(value, fontTableHeadingNonBoldData));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableA.addCell(cell);
			}
		}

		// Add Section A table to main table
		PdfPCell tableACell = new PdfPCell(tableA);
		tableACell.setBorder(Rectangle.NO_BORDER);
		table.addCell(tableACell);

		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);
		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);
		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);
		
		

		// SECTION B TODO
		Paragraph sectionBTitle = new Paragraph("SECTION B : REG EST APPX 'A' TO IAFF-3009", fontTableHeadingMainHead);
		sectionBTitle.setAlignment(Element.ALIGN_CENTER);
		
		PdfPCell titleCellB = new PdfPCell(sectionBTitle);
		titleCellB.setBorder(Rectangle.NO_BORDER);
		titleCellB.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(titleCellB);
		
		
		// Create Section A table with 7 columns
		PdfPTable tableB = new PdfPTable(7);
		tableB.setWidthPercentage(100);
		/*PdfPCell srNoBCell = new PdfPCell(new Paragraph("SECTION B : REG EST APPX 'A' TO IAFF-3009", fontTableHeadingMainHead));
		srNoBCell.setBorder(Rectangle.NO_BORDER);
		srNoBCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		srNoBCell.setColspan(7);
		tableB.addCell(srNoBCell);*/


		// Add headers
		String[] headers = {"SER NO", "CAT", "GROUP", "AUTH / HELD", "GENDER", "INDUSTRIAL", "NON INDUSTRIAL"};
		for (String header : headers) {
			PdfPCell cell = new PdfPCell(new Paragraph(header, fontTableHeadingNonBoldData));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableB.addCell(cell);
		}

		// 1. GAZETTED
		PdfPCell srNo1Cell = new PdfPCell(new Paragraph("1", boldFont));
		srNo1Cell.setRowspan(8);
		tableB.addCell(srNo1Cell);

		PdfPCell gazettedCell = new PdfPCell(new Paragraph("GAZETTED", boldFont));
		gazettedCell.setRowspan(6);
		tableB.addCell(gazettedCell);

		// GROUP A row
		PdfPCell groupACell = new PdfPCell(new Paragraph("GROUP A", fontTableHeadingNonBoldData));
		groupACell.setRowspan(3);
		tableB.addCell(groupACell);

		PdfPCell heldCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldCell.setRowspan(3);
		tableB.addCell(heldCell);

		// Gender cells for GROUP A
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP A
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP A
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// GROUP B rows (similar structure as GROUP A)
		PdfPCell groupBCell = new PdfPCell(new Paragraph("GROUP B", fontTableHeadingNonBoldData));
		groupBCell.setRowspan(3);
		tableB.addCell(groupBCell);
		PdfPCell heldGroupBCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupBCell.setRowspan(3);
		tableB.addCell(heldGroupBCell);
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// TOTAL HELD/AUTH GAZETTED row
		PdfPCell totalHeldCell = new PdfPCell(new Paragraph("TOTAL HELD/ AUTH GAZETTED", boldFont));
		totalHeldCell.setRowspan(2);
		tableB.addCell(totalHeldCell);

		tableB.addCell(new PdfPCell(new Paragraph("GROUP A", boldFont)));
		tableB.addCell(new PdfPCell(new Paragraph("Total Auth", boldFont)));
		tableB.addCell(new PdfPCell());  // Gender - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// GROUP B total row
		tableB.addCell(new PdfPCell(new Paragraph("GROUP B", boldFont)));
		tableB.addCell(new PdfPCell(new Paragraph("Total Auth", boldFont)));
		tableB.addCell(new PdfPCell());  // Gender - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty


		//2. NON GAZETTED

		PdfPCell srNo2Cell = new PdfPCell(new Paragraph("2", boldFont));
		srNo2Cell.setRowspan(19);
		tableB.addCell(srNo2Cell);

		PdfPCell nonGazettedCell = new PdfPCell(new Paragraph("NON GAZETTED", boldFont));
		nonGazettedCell.setColspan(6);
		tableB.addCell(nonGazettedCell);

		// (a)MINISTRAL ROW

		PdfPCell ministralCell = new PdfPCell(new Paragraph("(a)MINISTRAL", fontTableHeadingNonBoldData));
		ministralCell.setRowspan(6);
		tableB.addCell(ministralCell);

		PdfPCell ministralGroupBCell = new PdfPCell(new Paragraph("GROUP B", fontTableHeadingNonBoldData));
		ministralGroupBCell.setRowspan(3);
		tableB.addCell(ministralGroupBCell);

		PdfPCell heldGroupBMinistralCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupBMinistralCell.setRowspan(3);
		tableB.addCell(heldGroupBMinistralCell);

		// Gender cells for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// GROUP C rows (similar structure as GROUP B)
		PdfPCell ministralGroupCCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		ministralGroupCCell.setRowspan(3);
		tableB.addCell(ministralGroupCCell);
		PdfPCell heldGroupCMinistralCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupCMinistralCell.setRowspan(3);
		tableB.addCell(heldGroupCMinistralCell);
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty



		// (b)EXECUTIVE ROW

		PdfPCell executiveCell = new PdfPCell(new Paragraph("(b)EXECUTIVE", fontTableHeadingNonBoldData));
		executiveCell.setRowspan(6);
		tableB.addCell(executiveCell);

		PdfPCell executiveGroupBCell = new PdfPCell(new Paragraph("GROUP B", fontTableHeadingNonBoldData));
		executiveGroupBCell.setRowspan(3);
		tableB.addCell(executiveGroupBCell);

		PdfPCell heldGroupBExecutiveCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupBExecutiveCell.setRowspan(3);
		tableB.addCell(heldGroupBExecutiveCell);

		// Gender cells for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// GROUP C rows (similar structure as GROUP B)
		PdfPCell executiveGroupCCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		executiveGroupCCell.setRowspan(3);
		tableB.addCell(executiveGroupCCell);

		PdfPCell heldExecutiveGroupCCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldExecutiveGroupCCell.setRowspan(3);
		tableB.addCell(heldExecutiveGroupCCell);

		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty



		// (c)TECHNICAL ROW

		PdfPCell technicalCell = new PdfPCell(new Paragraph("(c)TECHNICAL", fontTableHeadingNonBoldData));
		technicalCell.setRowspan(6);
		tableB.addCell(technicalCell);

		PdfPCell technicalGroupBCell = new PdfPCell(new Paragraph("GROUP B", fontTableHeadingNonBoldData));
		technicalGroupBCell.setRowspan(3);
		tableB.addCell(technicalGroupBCell);

		PdfPCell heldGroupBTechnicalCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupBTechnicalCell.setRowspan(3);
		tableB.addCell(heldGroupBTechnicalCell);

		// Gender cells for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP B
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// GROUP C rows (similar structure as GROUP B)
		PdfPCell technicalGroupCCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		technicalGroupCCell.setRowspan(3);
		tableB.addCell(technicalGroupCCell);

		PdfPCell heldTechnicalGroupCCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldTechnicalGroupCCell.setRowspan(3);
		tableB.addCell(heldTechnicalGroupCCell);

		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Female row for GROUP C
		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		// Total row for GROUP C
		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty


		//3. NON GAZETTED INCL NCsU

		PdfPCell srNo3Cell = new PdfPCell(new Paragraph("3", boldFont));
		srNo3Cell.setRowspan(15);
		tableB.addCell(srNo3Cell);

		PdfPCell nonGazettedIncCell = new PdfPCell(new Paragraph("NON GAZETTED INCL NCsU", boldFont));
		nonGazettedIncCell.setColspan(6);
		tableB.addCell(nonGazettedIncCell);

		// (a)OFFICE WORKERS

		PdfPCell officeWorkersCell = new PdfPCell(new Paragraph("(a)OFFICE WORKERS", fontTableHeadingNonBoldData));
		officeWorkersCell.setRowspan(4);
		tableB.addCell(officeWorkersCell);

		PdfPCell groupCOfficeWorkersCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		groupCOfficeWorkersCell.setRowspan(4);
		tableB.addCell(groupCOfficeWorkersCell);

		tableB.addCell(new Paragraph("AUTH", fontTableHeadingNonBoldData));
		tableB.addCell(new PdfPCell());  // GENDER - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		PdfPCell heldGroupCOfficeWorkersCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupCOfficeWorkersCell.setRowspan(3);
		tableB.addCell(heldGroupCOfficeWorkersCell);
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty



		// (b)SEMI SKILLED

		PdfPCell semiSkilledCell = new PdfPCell(new Paragraph("(b)SEMI SKILLED", fontTableHeadingNonBoldData));
		semiSkilledCell.setRowspan(4);
		tableB.addCell(semiSkilledCell);

		PdfPCell groupCSemiSkilledCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		groupCSemiSkilledCell.setRowspan(4);
		tableB.addCell(groupCSemiSkilledCell);

		tableB.addCell(new Paragraph("AUTH", fontTableHeadingNonBoldData));
		tableB.addCell(new PdfPCell());  // GENDER - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		PdfPCell heldGroupCSemiSkilledCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupCSemiSkilledCell.setRowspan(3);
		tableB.addCell(heldGroupCSemiSkilledCell);
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty



		// (c)OTHERS (SPECIFY IN NOTES)

		PdfPCell othersCell = new PdfPCell(new Paragraph("(c)OTHERS (SPECIFY IN NOTES)", fontTableHeadingNonBoldData));
		othersCell.setRowspan(4);
		tableB.addCell(othersCell);

		PdfPCell groupOthersCell = new PdfPCell(new Paragraph("", fontTableHeadingNonBoldData));
		groupOthersCell.setRowspan(4);
		tableB.addCell(groupOthersCell);

		tableB.addCell(new Paragraph("AUTH", fontTableHeadingNonBoldData));
		tableB.addCell(new PdfPCell());  // GENDER - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		PdfPCell heldGroupOthersCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldGroupOthersCell.setRowspan(3);
		tableB.addCell(heldGroupOthersCell);
		tableB.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty



		// TOTAL HELD/AUTH NON GAZETTED
		PdfPCell totalHeldAuthCell = new PdfPCell(new Paragraph("TOTAL HELD/AUTH NON GAZETTED", boldFont));
		totalHeldAuthCell.setRowspan(2);
		tableB.addCell(totalHeldAuthCell);

		tableB.addCell(new Paragraph("GROUP B", boldFont));
		tableB.addCell(new Paragraph("Total Auth", boldFont));
		tableB.addCell(new PdfPCell());  // GENDER - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty

		tableB.addCell(new Paragraph("GROUP C", boldFont));
		tableB.addCell(new Paragraph("Total Auth", boldFont));
		tableB.addCell(new PdfPCell());  // GENDER - empty
		tableB.addCell(new PdfPCell());  // Industrial - empty
		tableB.addCell(new PdfPCell());  // Non Industrial - empty


		if (listB != null && !listB.isEmpty()) {
			// Populate Gazetted section
			// Group A
			populateCellValue(tableB, "a_gaz_man_ind", 1, 5, listB);      // Male Industrial
			populateCellValue(tableB, "a_gaz_female_ind", 2, 5, listB);    // Female Industrial
			populateCellValue(tableB, "a_gaz_total_ind", 3, 5, listB);    // Total Industrial
			populateCellValue(tableB, "a_gaz_man_nonind", 1, 6, listB);   // Male Non-Industrial
			populateCellValue(tableB, "a_gaz_female_nonind", 2, 6, listB); // Female Non-Industrial
			populateCellValue(tableB, "a_gaz_total_nonind", 3, 6, listB); // Total Non-Industrial

			// Group B
			populateCellValue(tableB, "b_gaz_man_ind", 4, 5, listB);      // Male Industrial
			populateCellValue(tableB, "b_gaz_female_ind", 5, 5, listB);    // Female Industrial
			populateCellValue(tableB, "b_gaz_total_ind", 6, 5, listB);    // Total Industrial
			populateCellValue(tableB, "b_gaz_man_nonind", 4, 6, listB);   // Male Non-Industrial
			populateCellValue(tableB, "b_gaz_female_nonind", 5, 6, listB); // Female Non-Industrial
			populateCellValue(tableB, "b_gaz_total_nonind", 6, 6, listB); // Total Non-Industrial

			// Non-Gazetted section
			// Ministral
			// Group B
			populateCellValue(tableB, "b_nongaz_m_man_ind", 10, 5, listB);    // Group B Male Industrial
			populateCellValue(tableB, "b_nongaz_m_female_ind", 11, 5, listB); // Group B Female Industrial
			populateCellValue(tableB, "b_nongaz_m_total_ind", 12, 5, listB);    // Total Industrial
			populateCellValue(tableB, "b_nongaz_m_man_nonind", 10, 6, listB); // Group B Male Non-Industrial
			populateCellValue(tableB, "b_nongaz_m_female_nonind", 11, 6, listB); // Group B Female Non-Industrial
			populateCellValue(tableB, "b_nongaz_m_total_nonind", 12, 6, listB); // Total Non-Industrial

			// Group C
			populateCellValue(tableB, "c_nongaz_m_man_ind", 13, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_m_female_ind", 14, 5, listB); // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_m_total_ind", 15, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_m_man_nonind", 13, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_m_female_nonind", 14, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_m_total_nonind", 15, 6, listB); // Total Non-Industrial

			// Executive
			// Group B
			populateCellValue(tableB, "b_nongaz_e_man_ind", 16, 5, listB);    // Group B Male Industrial
			populateCellValue(tableB, "b_nongaz_e_female_ind", 17, 5, listB); // Group B Female Industrial
			populateCellValue(tableB, "b_nongaz_e_total_ind", 18, 5, listB);    // Total Industrial
			populateCellValue(tableB, "b_nongaz_e_man_nonind", 16, 6, listB); // Group B Male Non-Industrial
			populateCellValue(tableB, "b_nongaz_e_female_nonind", 17, 6, listB); // Group B Female Non-Industrial
			populateCellValue(tableB, "b_nongaz_e_total_nonind", 18, 6, listB); // Total Non-Industrial

			// Group C
			populateCellValue(tableB, "c_nongaz_e_man_ind", 19, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_e_female_ind", 20, 5, listB); // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_e_total_ind", 21, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_e_man_nonind", 19, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_e_female_nonind", 20, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_e_total_nonind", 21, 6, listB); // Total Non-Industrial

			// Technical
			// Group B
			populateCellValue(tableB, "b_nongaz_t_man_ind", 22, 5, listB);    // Group B Male Industrial
			populateCellValue(tableB, "b_nongaz_t_female_ind", 23, 5, listB); // Group B Female Industrial
			populateCellValue(tableB, "b_nongaz_t_total_ind", 24, 5, listB);    // Total Industrial
			populateCellValue(tableB, "b_nongaz_t_man_nonind", 22, 6, listB); // Group B Male Non-Industrial
			populateCellValue(tableB, "b_nongaz_t_female_nonind", 23, 6, listB); // Group B Female Non-Industrial
			populateCellValue(tableB, "b_nongaz_t_total_nonind", 24, 6, listB); // Total Non-Industrial

			// Group C
			populateCellValue(tableB, "c_nongaz_t_man_ind", 25, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_t_female_ind", 26, 5, listB); // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_t_total_ind", 27, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_t_man_nonind", 25, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_t_female_nonind", 26, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_t_total_nonind", 27, 6, listB); // Total Non-Industrial

			// Office Workers
			populateCellValue(tableB, "c_nongaz_o_man_ind", 30, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_o_female_ind", 31, 5, listB);  // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_o_total_ind", 32, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_o_man_nonind_nonind", 30, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_o_female_nonind_nonind", 31, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_o_total_nonind", 32, 6, listB); // Total Non-Industrial

			// Semi Skilled
			populateCellValue(tableB, "c_nongaz_s_man_ind", 34, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_s_female_ind", 35, 5, listB);  // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_s_total_ind", 36, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_s_man_nonind_nonind", 34, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_s_female_nonind_nonind", 35, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_s_total_nonind", 36, 6, listB); // Total Non-Industrial

			// Others
			populateCellValue(tableB, "c_nongaz_other_man_ind", 38, 5, listB);    // Group C Male Industrial
			populateCellValue(tableB, "c_nongaz_other_female_ind", 39, 5, listB);  // Group C Female Industrial
			populateCellValue(tableB, "c_nongaz_other_total_ind", 40, 5, listB);    // Total Industrial
			populateCellValue(tableB, "c_nongaz_other_man_nonind_nonind", 38, 6, listB); // Group C Male Non-Industrial
			populateCellValue(tableB, "c_nongaz_other_female_nonind_nonind", 39, 6, listB); // Group C Female Non-Industrial
			populateCellValue(tableB, "c_nongaz_other_total_nonind", 40, 6, listB); // Total Non-Industrial

			populateCellValue(tableB, "a_auth_gaz", 7, 4, listB);    // Total Held/Auth GP A
			populateCellValue(tableB, "b_auth_gaz", 8, 4, listB); // Total Held/Auth GP B

			populateCellValue(tableB, "b_non_gaz_auth", 41, 4, listB);    // Total Auth GP B
			populateCellValue(tableB, "c_non_gaz_auth", 42, 4, listB); // Total Auth GP C
		}

		// Add Section B table to main table
		/*PdfPCell tableBCell = new PdfPCell(tableB);
		tableBCell.setBorder(Rectangle.NO_BORDER);*/
		table.addCell(tableB);
		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);
		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);








		// SECTION C TODO
		/*p = new Paragraph("SECTION C : NON REG EST APPX 'A' TO IAFF-3009", fontTableHeadingMainHead);
		p.setAlignment(Element.ALIGN_CENTER);
		table.addCell(p);*/

		Paragraph sectionCTitle = new Paragraph("SECTION C : NON REG EST APPX 'A' TO IAFF-3009", fontTableHeadingMainHead);
		sectionCTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell titleCellC = new PdfPCell(sectionCTitle);
		titleCellC.setBorder(Rectangle.NO_BORDER);
		titleCellC.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(titleCellC);
		
		// Create Section C table with 7 columns
		PdfPTable tableC = new PdfPTable(7);
		tableC.setWidthPercentage(100);

		// Add headers
		String[] headersC = {"SER NO", "CAT", "GROUP", "AUTH / HELD", "GENDER", "INDUSTRIAL", "NON INDUSTRIAL"};
		for (String header : headersC) {
			PdfPCell cell = new PdfPCell(new Paragraph(header, fontTableHeadingNonBoldData));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableC.addCell(cell);
		}

		// 1. PERSON PAID FROM CONTINGENCIES
		PdfPCell srNo1CCell = new PdfPCell(new Paragraph("1", fontTableHeadingNonBoldData));
		srNo1CCell.setRowspan(3);
		tableC.addCell(srNo1CCell);

		PdfPCell personPaidCCell = new PdfPCell(new Paragraph("PERSON PAID FROM CONTINGENCIES", fontTableHeadingNonBoldData));
		personPaidCCell.setRowspan(3);
		tableC.addCell(personPaidCCell);

		// GROUP C row
		PdfPCell groupCCell = new PdfPCell(new Paragraph("GROUP C", fontTableHeadingNonBoldData));
		groupCCell.setRowspan(3);
		tableC.addCell(groupCCell);

		PdfPCell heldSectionCCell = new PdfPCell(new Paragraph("Held", fontTableHeadingNonBoldData));
		heldSectionCCell.setRowspan(3);
		tableC.addCell(heldSectionCCell);

		tableC.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty



		// 2. WORK CHARGES PERSONNEL
		PdfPCell srNo2CCell = new PdfPCell(new Paragraph("2", fontTableHeadingNonBoldData));
		srNo2CCell.setRowspan(3);
		tableC.addCell(srNo2CCell);

		PdfPCell workChargesCCell = new PdfPCell(new Paragraph("WORK CHARGES PERSONNEL", fontTableHeadingNonBoldData));
		workChargesCCell.setRowspan(3);
		tableC.addCell(workChargesCCell);

		// GROUP C AND HELD CELLS REUSED
		tableC.addCell(groupCCell);
		tableC.addCell(heldSectionCCell);

		// Gender cells for GROUP A
		tableC.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty



		// 3. PERSON PAID FROM REGULAR PAY HEAD
		PdfPCell srNo3CCell = new PdfPCell(new Paragraph("3", fontTableHeadingNonBoldData));
		srNo3CCell.setRowspan(3);
		tableC.addCell(srNo3CCell);

		PdfPCell personPaidRegCCell = new PdfPCell(new Paragraph("PERSON PAID FROM REGULAR PAY HEAD", fontTableHeadingNonBoldData));
		personPaidRegCCell.setRowspan(3);
		tableC.addCell(personPaidRegCCell);

		// GROUP C AND HELD CELLS REUSED
		tableC.addCell(groupCCell);
		tableC.addCell(heldSectionCCell);

		tableC.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty



		// 4. OTHER IF ANY (NATURE OF EMPLOYMENT TO BE SPECIFIED IN NOTES)
		PdfPCell srNo4CCell = new PdfPCell(new Paragraph("4", fontTableHeadingNonBoldData));
		srNo4CCell.setRowspan(3);
		tableC.addCell(srNo4CCell);

		PdfPCell otherCCell = new PdfPCell(new Paragraph("OTHER IF ANY (NATURE OF EMPLOYMENT TO BE SPECIFIED IN NOTES)", fontTableHeadingNonBoldData));
		otherCCell.setRowspan(3);
		tableC.addCell(otherCCell);

		// GROUP C AND HELD CELLS REUSED
		tableC.addCell(groupCCell);
		tableC.addCell(heldSectionCCell);

		// Gender cells for GROUP A
		tableC.addCell(new PdfPCell(new Paragraph("Male", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Female", fontTableHeadingNonBoldData)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty

		tableC.addCell(new PdfPCell(new Paragraph("Total", boldFont)));
		tableC.addCell(new PdfPCell());  // Industrial - empty
		tableC.addCell(new PdfPCell());  // Non Industrial - empty


		if (listC != null && !listC.isEmpty()) {
			i = 0;
			// PERSON PAID FROM CONTIGENCIES
			populateCellValue(tableC, "c_nonreg_c_man_ind", 1, 5, listC);      // Male Industrial
			populateCellValue(tableC, "c_nonreg_c_female_ind", 2, 5, listC);    // Female Industrial
			populateCellValue(tableC, "c_nonreg_c_total_ind", 3, 5, listC);    // Total Industrial
			populateCellValue(tableC, "c_nonreg_c_man_nonind", 1, 6, listC);   // Male Non-Industrial
			populateCellValue(tableC, "c_nonreg_c_female_nonind", 2, 6, listC); // Female Non-Industrial
			populateCellValue(tableC, "c_nonreg_c_total_nonind", 3, 6, listC); // Total Non-Industrial

			// WORK CHARGES PERSONNEL
			populateCellValue(tableC, "c_nonreg_p_man_ind", 4, 5, listC);      // Male Industrial
			populateCellValue(tableC, "c_nonreg_p_female_ind", 5, 5, listC);    // Female Industrial
			populateCellValue(tableC, "c_nonreg_p_total_ind", 6, 5, listC);    // Total Industrial
			populateCellValue(tableC, "c_nonreg_p_man_nonind", 4, 6, listC);   // Male Non-Industrial
			populateCellValue(tableC, "c_nonreg_p_female_nonind", 5, 6, listC); // Female Non-Industrial
			populateCellValue(tableC, "c_nonreg_p_total_nonind", 6, 6, listC); // Total Non-Industrial

			// PERSON PAID FROM REGULAR PAY HEAD
			populateCellValue(tableC, "c_nonreg_r_man_ind", 7, 5, listC);    // Group B Male Industrial
			populateCellValue(tableC, "c_nonreg_r_female_ind", 8, 5, listC); // Group B Female Industrial
			populateCellValue(tableC, "c_nonreg_r_total_ind", 9, 5, listC);    // Total Industrial
			populateCellValue(tableC, "c_nonreg_r_man_nonind", 7, 6, listC); // Group B Male Non-Industrial
			populateCellValue(tableC, "c_nonreg_r_female_nonind", 8, 6, listC); // Group B Female Non-Industrial
			populateCellValue(tableC, "c_nonreg_r_total_nonind", 9, 6, listC); // Total Non-Industrial

			// OTHER IF ANY (NATURE OF EMPLOYMENT TO BE SPECIFIED IN NOTES)
			populateCellValue(tableC, "c_nonreg_other_man_ind", 10, 5, listC);    // Group C Male Industrial
			populateCellValue(tableC, "c_nonreg_other_female_ind", 11, 5, listC); // Group C Female Industrial
			populateCellValue(tableC, "c_nonreg_other_total_ind", 12, 5, listC);    // Total Industrial
			populateCellValue(tableC, "c_nonreg_other_man_nonind", 10, 6, listC); // Group C Male Non-Industrial
			populateCellValue(tableC, "c_nonreg_other_female_nonind", 11, 6, listC); // Group C Female Non-Industrial
			populateCellValue(tableC, "c_nonreg_other_total_nonind", 12, 6, listC); // Total Non-Industrial

		}

		// Add Section B table to main table
		PdfPCell tableCCell = new PdfPCell(tableC);
		tableCCell.setBorder(Rectangle.NO_BORDER);
		table.addCell(tableCCell);
		pl = new Paragraph("\n", fontTableHeading);
		table.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		table.addCell(plv);


		// SECTION D TODO
		/*p = new Paragraph("SECTION D: SUMMARY(REGULAR)", fontTableHeadingMainHead);
		p.setAlignment(Element.ALIGN_CENTER);
		table.addCell(p);*/
		
		Paragraph sectionDTitle = new Paragraph("SECTION D: SUMMARY(REGULAR)", fontTableHeadingMainHead);
		sectionDTitle.setAlignment(Element.ALIGN_CENTER);
		PdfPCell titleCellD = new PdfPCell(sectionDTitle);
		titleCellD.setBorder(Rectangle.NO_BORDER);
		titleCellD.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(titleCellD);

		PdfPTable tableD = new PdfPTable(TH_D.size());
		tableD.setWidthPercentage(100);
		tableD.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for (int h = 0; h < TH_D.size(); h++) {
			p = new Paragraph(TH_D.get(h), fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			tableD.addCell(p);
		}
		tableD.setHeaderRows(1);
		for (int i = 0; i < listD.size(); i++) {
			List<String> l = listD.get(i);
			for (int j = 0; j < l.size(); j++) {
				p = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tableD.addCell(p);
			}
		}
		table.addCell(tableD);


		table.setTableEvent(new ImageBackgroundEvent(request));
		document.add(table);
		super.buildPdfMetadata(model, document, request);
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;
		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {
			this.request = request;
		}

		int page = 1;

		@Override
		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
				PdfContentByte[] canvases) {
			String ip = "";
			if (request != null) {
				ip = request.getHeader("X-FORWARDED-FOR");
				if (ip == null || "".equals(ip)) {
					ip = request.getRemoteAddr();
				}
			}
			Date now = new Date();
			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
			String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;
			Image img = null;
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
					BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.white);
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
			graphics.drawString(watermark + watermark, 0, 20);
			try {
				try {
					img = com.lowagie.text.Image.getInstance(bufferedImage, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (BadElementException e) {
				e.printStackTrace();
			}
			this.image = img;
			try {
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
				int tableWidth = (int) table.getTotalWidth();
				int first = 0;
				if (tableWidth == 523) {
					first = 630;
				}

				if (tableWidth == 770) {
					first = 430;
				}

				if (tableWidth == 1119) {
					first = 1500;
				}
				int last = first - (int) table.getRowHeight(0);
				Phrase p = new Phrase();
				p.add(String.valueOf(page));
				float width = ColumnText.getWidth(p);
				ColumnText.showTextAligned(cb, PdfContentByte.ALIGN_RIGHT, p, cb.getPdfDocument().right() - width,
						cb.getPdfDocument().top() + 9, 0);
				page += 1;
				while (first > last) {
					image.setAbsolutePosition(30, first);
					cb.addImage(image, false);
					first -= 30;
				}
			} catch (DocumentException e) {
				throw new ExceptionConverter(e);
			}
		}
	}
}