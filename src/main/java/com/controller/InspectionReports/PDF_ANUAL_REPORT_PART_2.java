package com.controller.InspectionReports;

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
import com.itextpdf.text.pdf.BaseFont;
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
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


public class PDF_ANUAL_REPORT_PART_2 extends AbstractPdfView {


	String foot = "";
	Map<String, String> phase2Data;
	Map<String, String> digitalSign;
	List<Map<String, String>> weaponDeficiencyList;
	List<Map<String, String>> getciricaldefimanpowerlist;
	List<Map<String, String>> discipline;
	String username = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_ANUAL_REPORT_PART_2(String username, Map<String, String> phase2Data, Map<String, String> digitalSign, List<Map<String, String>> weaponDeficiencyList,
			List<Map<String, String>> getciricaldefimanpowerlist, List<Map<String, String>> discipline) {

		this.phase2Data = phase2Data;
		this.digitalSign=digitalSign;
		this.weaponDeficiencyList= weaponDeficiencyList;
		this.getciricaldefimanpowerlist= getciricaldefimanpowerlist;
		this.discipline= discipline;
		this.username = username;
	}
	float indentLevel1 = 20f;
	// float indentLevel2 = 40f; // Add if needed for deeper levels later
	// float indentLevel3 = 60f; // Add if needed for deeper levels later

	// Helper method to create table cells (add this to your class)
	private PdfPCell createTableCell(String text, Font font, int horizontalAlignment, int verticalAlignment, int rowspan, int colspan) {
		Paragraph p = new Paragraph(text, font);
		PdfPCell cell = new PdfPCell(p);
		cell.setHorizontalAlignment(horizontalAlignment);
		cell.setVerticalAlignment(verticalAlignment);
		if (rowspan > 1) {
			cell.setRowspan(rowspan);
		}
		if (colspan > 1) {
			cell.setColspan(colspan);
		}
		// Optional: Add some padding
		cell.setPadding(3f);
		cell.setUseAscender(true); // Better vertical alignment esp. with padding
		cell.setUseDescender(true);
		return cell;
	}

	// Overload for simple cells
	private PdfPCell createTableCell(String text, Font font, int horizontalAlignment) {
		return createTableCell(text, font, horizontalAlignment, Element.ALIGN_MIDDLE, 1, 1);
	}
	// Overload with vertical alignment

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
		logo.scaleAbsoluteHeight(40);
		logo.scaleAbsoluteWidth(30);
		logo.scalePercent(12);
		Chunk chunk = new Chunk(logo, -5, 10);

		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String indian_Army = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "dgis-logo_new.png";

			logo2 = Image.getInstance(indian_Army);// "d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(40);
		logo2.scaleAbsoluteWidth(30);
		logo2.scalePercent(12);
		Chunk chunk2 = new Chunk(logo2, -5, 10);
		Chunk underline = new Chunk(" ", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);

		Chunk underline2 = new Chunk("RESTRICTED", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);

		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline2);

		p.add("\n");
		p.add("\n");

		p.add("\n");
		p.add("\n");

		//		p.add(underline3);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.setWidthPercentage(120);
		p2.add(table3);

		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		Phrase p1 = new Phrase();

		Chunk p89 = new Chunk(foot, fontTableHeadingMainHead);
		p89.setUnderline(0.1f, -2f);
		p1.add(p89);
		p1.add("\n");
		//		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);
		//		p90.setUnderline(0.1f, -2f);
		//		p1.add(p90);

		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);

		document.setPageCount(1);

		document.setPageSize(PageSize.A4); // set document landscape

		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Font fontTableHeading2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=\"" + file_name + ".pdf\"");

		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");

		Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);

		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);



		Chunk underline70 = new Chunk("PART - II", fontTableHeading);
		underline70.setUnderline(0.1f, -2f); // Set underline
		Paragraph headingParagraph70 = new Paragraph();
		headingParagraph70.add(underline70);
		headingParagraph70.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph70);

		document.add(new Phrase("\n"));

		String informationText = "SELF APPRAISAL BY COMMANDING OFFICER WHICH \r\n"
				+ "SHOULD COVER THE FOLLOWING ASPECTS\r\n"
				+ "";
		Paragraph informationParagraph = new Paragraph(informationText, fontTableValue);
		informationParagraph.setAlignment(Element.ALIGN_CENTER); // Center align the text
		document.add(informationParagraph);
		document.add(new Phrase("\n"));




		// ==================== 1. Op Preparedness Section ====================
		// 1. Op Preparedness section
		PdfPTable tableleftFM4 = new PdfPTable(2);
		tableleftFM4.setWidthPercentage(100);
		tableleftFM4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell opPrepTitleCell = createNumberedCell("1.    " , "Op Preparedness", fontTableHeading);
		opPrepTitleCell.setPaddingTop(5f);
		tableleftFM4.addCell(opPrepTitleCell);
		PdfPCell emptyCell = createNumberedCell("", "", fontTableHeading);
		tableleftFM4.addCell(emptyCell);
		tableleftFM4.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableleftFM4);
		document.add(new Paragraph(" ", new Font()));

		// Add subsection (a) - directly attached format
		PdfPTable tableSubA = new PdfPTable(1);
		tableSubA.setWidthPercentage(100);
		tableSubA.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph = new Paragraph();
		Chunk labelA = new Chunk("(a) ", fontTableHeading);
		Chunk contentA = new Chunk("Capability of unit to undertake operational task at short notice.", fontTableHeadingNonBoldData);
		subAParagraph.add(labelA);
		subAParagraph.add(contentA);
		subAParagraph.setIndentationLeft(20f);

		PdfPCell subACell = new PdfPCell(subAParagraph);
		subACell.setBorder(Rectangle.NO_BORDER);
		tableSubA.addCell(subACell);
		tableSubA.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA);
		document.add(new Paragraph(" ", new Font()));


		// Add subsection (b) - directly attached format
		PdfPTable tableSubB = new PdfPTable(1);
		tableSubB.setWidthPercentage(100);
		tableSubB.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subBParagraph = new Paragraph();
		Chunk labelB = new Chunk("(b) ", fontTableHeading);
		Chunk contentB = new Chunk("Challenges, if any, in operational preparedness for the envisaged operational task with reasons such as:- ", fontTableHeadingNonBoldData);
		subBParagraph.add(labelB);
		subBParagraph.add(contentB);
		subBParagraph.setIndentationLeft(20f);

		PdfPCell subBCell = new PdfPCell(subBParagraph);
		subBCell.setBorder(Rectangle.NO_BORDER);
		tableSubB.addCell(subBCell);
		document.add(tableSubB);
		document.add(new Paragraph(" ", new Font()));

		// Add point (i) - directly attached format
		PdfPTable tablePointI = new PdfPTable(1);
		tablePointI.setWidthPercentage(100);
		tablePointI.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pointIParagraph = new Paragraph();
		Chunk labelI = new Chunk("        (i) ", fontTableHeading);
		Chunk contentI1 = new Chunk("Shortage of weapons/ ammunition/ transport facilities/ equipment/ Sector Stores authorised in the location. ", fontTableHeadingNonBoldData);
		pointIParagraph.add(labelI);
		pointIParagraph.add(contentI1);
		pointIParagraph.setIndentationLeft(40f);

		PdfPCell pointICell = new PdfPCell(pointIParagraph);
		pointICell.setBorder(Rectangle.NO_BORDER);
		tablePointI.addCell(pointICell);
		document.add(tablePointI);
		PdfPTable tablePointII = new PdfPTable(1);
		tablePointII.setWidthPercentage(100);
		tablePointII.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pointIIParagraph = new Paragraph();
		Chunk labelII = new Chunk("        (ii) ", fontTableHeading);
		Chunk contentII1 = new Chunk(" Critical deficiency of manpower, with special reference to officers state. ", fontTableHeadingNonBoldData);
		pointIIParagraph.add(labelII);
		pointIIParagraph.add(contentII1);
		pointIIParagraph.setIndentationLeft(40f);

		PdfPCell pointIICell = new PdfPCell(pointIIParagraph);
		pointIICell.setBorder(Rectangle.NO_BORDER);
		tablePointII.addCell(pointIICell);
		document.add(tablePointII);
		// Add point (iii) - directly attached format
		PdfPTable tablePointIII = new PdfPTable(1);
		tablePointIII.setWidthPercentage(100);
		tablePointIII.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pointIIIParagraph = new Paragraph();
		Chunk labelIII = new Chunk("        (iii) ", fontTableHeading);
		Chunk contentIII = new Chunk("Challenges, due to lack of training for want of Army courses.", fontTableHeadingNonBoldData);
		pointIIIParagraph.add(labelIII);
		pointIIIParagraph.add(contentIII);
		pointIIIParagraph.setIndentationLeft(40f);

		PdfPCell pointIIICell = new PdfPCell(pointIIIParagraph);
		pointIIICell.setBorder(Rectangle.NO_BORDER);
		tablePointIII.addCell(pointIIICell);
		document.add(tablePointIII);
		// Add point (iv) - directly attached format
		PdfPTable tablePointIV = new PdfPTable(1);
		tablePointIV.setWidthPercentage(100);
		tablePointIV.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pointIVParagraph = new Paragraph();
		Chunk labelIV = new Chunk("        (iv) ", fontTableHeading);
		Chunk contentIV1 = new Chunk("Degree of operational preparedness, on account of new ", fontTableHeadingNonBoldData);
		Chunk raisingChunk = new Chunk("raising", fontTableHeadingNonBoldData);
		raisingChunk.setUnderline(0.1f, -2f);
		Chunk contentIV2 = new Chunk(" or a disbanded unit.", fontTableHeadingNonBoldData);
		pointIVParagraph.add(labelIV);
		pointIVParagraph.add(contentIV1);
		pointIVParagraph.add(raisingChunk);
		pointIVParagraph.add(contentIV2);
		pointIVParagraph.setIndentationLeft(40f);

		PdfPCell pointIVCell = new PdfPCell(pointIVParagraph);
		pointIVCell.setBorder(Rectangle.NO_BORDER);
		tablePointIV.addCell(pointIVCell);
		tablePointIV.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tablePointIV);
		document.add(new Paragraph(" ", new Font()));

		//================== Start: Expandable Box with Static Data ==================
		String op_preparedness_item = phase2Data.get("op_preparedness_item");
		addExpandableInfoBox(document, op_preparedness_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		//document.add(new Phrase("\n\n"));
		//=======================end Op Preparedness==================================


		// ==================== Training ====================

		PdfPTable tableTraining = new PdfPTable(2);
		tableTraining.setWidthPercentage(100);
		tableTraining.getDefaultCell().setBorder(Rectangle.NO_BORDER);


		PdfPCell tableTrainingCell1 = createNumberedCell("2.", "Training.", fontTableHeading);
		tableTrainingCell1.setPaddingTop(5f);
		tableTraining.addCell(tableTrainingCell1);
		PdfPCell emptyCellTraining = createNumberedCell("", "", fontTableHeading);
		tableTraining.addCell(emptyCellTraining);
		//	tableTraining.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableTraining);
		document.add(new Paragraph(" ", new Font()));

		// Indentation levels
		float indentLevel1 = 20f;
		float indentLevel2 = 40f;
		float indentLevel3 = 60f;

		//==================sub group start (a)===========
		PdfPTable tableSubA49 = new PdfPTable(1);
		tableSubA49.setWidthPercentage(100);
		tableSubA49.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph49 = new Paragraph();
		Chunk labelA49 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA49 = new Chunk("State of Training of Unit.", fontTableHeadingNonBoldData);
		subAParagraph49.add(labelA49);
		subAParagraph49.add(contentA49);
		subAParagraph49.setIndentationLeft(indentLevel1);

		PdfPCell subACell49 = new PdfPCell(subAParagraph49);
		subACell49.setBorder(Rectangle.NO_BORDER);
		tableSubA49.addCell(subACell49);
		tableSubA49.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA49);
		//==================sub group End===========

		//==================sub group start (a)(i)===========
		PdfPTable tableSubA50 = new PdfPTable(1);
		tableSubA50.setWidthPercentage(100);
		tableSubA50.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph50 = new Paragraph();
		Chunk labelA50 = new Chunk("  (i) ", fontTableHeading);
		Chunk contentA50 = new Chunk("Individual Training. The under mentioned aspects shall be included:-", fontTableHeadingNonBoldData);
		subAParagraph50.add(labelA50);
		subAParagraph50.add(contentA50);
		subAParagraph50.setIndentationLeft(indentLevel2); // Level 2 Indent

		PdfPCell subACell50 = new PdfPCell(subAParagraph50);
		subACell50.setBorder(Rectangle.NO_BORDER);
		tableSubA50.addCell(subACell50);
		tableSubA50.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA50);
		//==================sub group End===========

		//==================sub group start (a)(i)(aa)===========
		PdfPTable tableSubA53 = new PdfPTable(1);
		tableSubA53.setWidthPercentage(100);
		tableSubA53.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph53 = new Paragraph();
		Chunk labelA53 = new Chunk("     (aa) ", fontTableHeading);
		Chunk contentA53 = new Chunk("Physical Fitness standards to include BPET, PPT and Route Marches.", fontTableHeadingNonBoldData);
		subAParagraph53.add(labelA53);
		subAParagraph53.add(contentA53);
		subAParagraph53.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell53 = new PdfPCell(subAParagraph53);
		subACell53.setBorder(Rectangle.NO_BORDER);
		tableSubA53.addCell(subACell53);
		tableSubA53.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA53);
		//==================sub group End===========

		//==================sub group start (a)(i)(ab)===========
		PdfPTable tableSubA54 = new PdfPTable(1);
		tableSubA54.setWidthPercentage(100);
		tableSubA54.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph54 = new Paragraph();
		Chunk labelA54 = new Chunk("     (ab) ", fontTableHeading);
		Chunk contentA54 = new Chunk("Weapon Training including Firing standards.", fontTableHeadingNonBoldData);
		subAParagraph54.add(labelA54);
		subAParagraph54.add(contentA54);
		tableSubA54.setTableEvent(new ImageBackgroundEvent(request,username));
		subAParagraph54.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell54 = new PdfPCell(subAParagraph54);
		subACell54.setBorder(Rectangle.NO_BORDER);
		tableSubA54.addCell(subACell54);
		document.add(tableSubA54);
		//==================sub group End===========

		//==================sub group start (a)(i)(ac)===========
		PdfPTable tableSubA55 = new PdfPTable(1);
		tableSubA55.setWidthPercentage(100);
		tableSubA55.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph55 = new Paragraph();
		Chunk labelA55 = new Chunk("     (ac) ", fontTableHeading);
		Chunk contentA55 = new Chunk("Night Training.", fontTableHeadingNonBoldData);
		subAParagraph55.add(labelA55);
		subAParagraph55.add(contentA55);
		subAParagraph55.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell55 = new PdfPCell(subAParagraph55);
		subACell55.setBorder(Rectangle.NO_BORDER);
		tableSubA55.addCell(subACell55);
		tableSubA55.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA55);
		//==================sub group End===========

		//==================sub group start (a)(i)(ad)===========
		PdfPTable tableSubA56 = new PdfPTable(1);
		tableSubA56.setWidthPercentage(100);
		tableSubA56.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph56 = new Paragraph();
		Chunk labelA56 = new Chunk("     (ad) ", fontTableHeading);
		Chunk contentA56 = new Chunk("Specialist Training.", fontTableHeadingNonBoldData);
		subAParagraph56.add(labelA56);
		subAParagraph56.add(contentA56);
		subAParagraph56.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell56 = new PdfPCell(subAParagraph56);
		subACell56.setBorder(Rectangle.NO_BORDER);
		tableSubA56.addCell(subACell56);
		document.add(tableSubA56);
		//==================sub group End===========

		//==================sub group start (a)(i)(ae)===========
		PdfPTable tableSubA57 = new PdfPTable(1);
		tableSubA57.setWidthPercentage(100);
		tableSubA57.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph57 = new Paragraph();
		Chunk labelA57 = new Chunk("     (ae) ", fontTableHeading);
		Chunk contentA57 = new Chunk("Junior Leaders Training (YOs and JCOs).", fontTableHeadingNonBoldData);
		subAParagraph57.add(labelA57);
		subAParagraph57.add(contentA57);
		subAParagraph57.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell57 = new PdfPCell(subAParagraph57);
		subACell57.setBorder(Rectangle.NO_BORDER);
		tableSubA57.addCell(subACell57);
		document.add(tableSubA57);
		//==================sub group End===========

		//==================sub group start (a)(i)(af)===========
		PdfPTable tableSubA58 = new PdfPTable(1);
		tableSubA58.setWidthPercentage(100);
		tableSubA58.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph58 = new Paragraph();
		Chunk labelA58 = new Chunk("     (af) ", fontTableHeading);
		Chunk contentA58 = new Chunk("Officers Training.", fontTableHeadingNonBoldData);
		subAParagraph58.add(labelA58);
		subAParagraph58.add(contentA58);
		subAParagraph58.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell58 = new PdfPCell(subAParagraph58);
		subACell58.setBorder(Rectangle.NO_BORDER);
		tableSubA58.addCell(subACell58);
		document.add(tableSubA58);
		//==================sub group End===========

		//==================sub group start (a)(i)(ag)===========
		PdfPTable tableSubA59 = new PdfPTable(1);
		tableSubA59.setWidthPercentage(100);
		tableSubA59.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph59 = new Paragraph();
		Chunk labelA59 = new Chunk("     (ag) ", fontTableHeading);
		Chunk contentA59 = new Chunk("Courses of instructions attended by personnel at Category 'A' and Category 'B' Establishments.", fontTableHeadingNonBoldData);
		subAParagraph59.add(labelA59);
		subAParagraph59.add(contentA59);
		subAParagraph59.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell59 = new PdfPCell(subAParagraph59);
		subACell59.setBorder(Rectangle.NO_BORDER);
		tableSubA59.addCell(subACell59);
		document.add(tableSubA59);
		//==================sub group End===========

		//==================sub group start (a)(ii)===========
		PdfPTable tableSubA51 = new PdfPTable(1);
		tableSubA51.setWidthPercentage(100);
		tableSubA51.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph51 = new Paragraph();
		Chunk labelA51 = new Chunk("  (ii) ", fontTableHeading);
		Chunk contentA51 = new Chunk("Sub-Unit Level Training. Appraisal be carried out in respect of sub-unit level training as applicable to all Arms and Services, e.g. Infantry at Company level, Armoured Corps at Squadron level, Artillery at Battery level, etc.", fontTableHeadingNonBoldData);
		subAParagraph51.add(labelA51);
		subAParagraph51.add(contentA51);
		subAParagraph51.setIndentationLeft(indentLevel2); // Level 2 Indent

		PdfPCell subACell51 = new PdfPCell(subAParagraph51);
		subACell51.setBorder(Rectangle.NO_BORDER);
		tableSubA51.addCell(subACell51);
		document.add(tableSubA51);
		//==================sub group End===========

		//==================sub group start (a)(iii)===========
		PdfPTable tableSubA52 = new PdfPTable(1);
		tableSubA52.setWidthPercentage(100);
		tableSubA52.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph52 = new Paragraph();
		Chunk labelA52 = new Chunk("  (iii) ", fontTableHeading);
		Chunk contentA52 = new Chunk("Collective Training. The following may be covered under this:-", fontTableHeadingNonBoldData);
		subAParagraph52.add(labelA52);
		subAParagraph52.add(contentA52);
		subAParagraph52.setIndentationLeft(indentLevel2); // Level 2 Indent

		PdfPCell subACell52 = new PdfPCell(subAParagraph52);
		subACell52.setBorder(Rectangle.NO_BORDER);
		tableSubA52.addCell(subACell52);
		document.add(tableSubA52);
		//==================sub group End===========

		//==================sub group start (a)(iii)(aa)===========
		PdfPTable tableSubA60 = new PdfPTable(1);
		tableSubA60.setWidthPercentage(100);
		tableSubA60.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph60 = new Paragraph();
		Chunk labelA60 = new Chunk("     (aa) ", fontTableHeading);
		Chunk contentA60 = new Chunk("Training exercises and training camps.", fontTableHeadingNonBoldData);
		subAParagraph60.add(labelA60);
		subAParagraph60.add(contentA60);
		subAParagraph60.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell60 = new PdfPCell(subAParagraph60);
		subACell60.setBorder(Rectangle.NO_BORDER);
		tableSubA60.addCell(subACell60);
		document.add(tableSubA60);
		//==================sub group End===========

		//==================sub group start (a)(iii)(ab)===========
		PdfPTable tableSubA61 = new PdfPTable(1);
		tableSubA61.setWidthPercentage(100);
		tableSubA61.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph61 = new Paragraph();
		Chunk labelA61 = new Chunk("     (ab) ", fontTableHeading);
		Chunk contentA61 = new Chunk("Field Firing.", fontTableHeadingNonBoldData);
		subAParagraph61.add(labelA61);
		subAParagraph61.add(contentA61);
		subAParagraph61.setIndentationLeft(indentLevel3); // Level 3 Indent

		PdfPCell subACell61 = new PdfPCell(subAParagraph61);
		subACell61.setBorder(Rectangle.NO_BORDER);
		tableSubA61.addCell(subACell61);
		document.add(tableSubA61);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA62 = new PdfPTable(1);
		tableSubA62.setWidthPercentage(100);
		tableSubA62.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph62 = new Paragraph();
		Chunk labelA62 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA62 = new Chunk("Factors having affected or contributed towards unit training (could include opportunities afforded for training, availability of training areas / operational areas, allocation of funds etc).", fontTableHeadingNonBoldData);
		subAParagraph62.add(labelA62);
		subAParagraph62.add(contentA62);
		subAParagraph62.setIndentationLeft(indentLevel1); // Level 1 Indent

		PdfPCell subACell62 = new PdfPCell(subAParagraph62);
		subACell62.setBorder(Rectangle.NO_BORDER);
		tableSubA62.addCell(subACell62);
		document.add(tableSubA62);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA63 = new PdfPTable(1);
		tableSubA63.setWidthPercentage(100);
		tableSubA63.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph63 = new Paragraph();
		Chunk labelA63 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA63 = new Chunk("Actions taken to conduct training effectively.", fontTableHeadingNonBoldData);
		subAParagraph63.add(labelA63);
		subAParagraph63.add(contentA63);
		subAParagraph63.setIndentationLeft(indentLevel1); // Level 1 Indent

		PdfPCell subACell63 = new PdfPCell(subAParagraph63);
		subACell63.setBorder(Rectangle.NO_BORDER);
		tableSubA63.addCell(subACell63);
		document.add(tableSubA63);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA64 = new PdfPTable(1);
		tableSubA64.setWidthPercentage(100);
		tableSubA64.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph64 = new Paragraph();
		Chunk labelA64 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA64 = new Chunk("Steps taken for development of JCOs and NCOs", fontTableHeadingNonBoldData);
		subAParagraph64.add(labelA64);
		subAParagraph64.add(contentA64);
		subAParagraph64.setIndentationLeft(indentLevel1); // Level 1 Indent

		PdfPCell subACell64 = new PdfPCell(subAParagraph64);
		subACell64.setBorder(Rectangle.NO_BORDER);
		tableSubA64.addCell(subACell64);
		document.add(tableSubA64);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String training_item = phase2Data.get("training_item");
		addExpandableInfoBox(document, training_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= Training End ====================

		// ==================== 3. State of Weapons/Equipment ====================
		PdfPTable tableWeaponsEquip = new PdfPTable(2);
		tableWeaponsEquip.setWidthPercentage(100);
		tableWeaponsEquip.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableWeaponsEquipCell1 = createNumberedCell("3.", "State of Weapons/Equipment", fontTableHeading);
		tableWeaponsEquipCell1.setPaddingTop(5f);
		tableWeaponsEquip.addCell(tableWeaponsEquipCell1);
		PdfPCell emptyCellWeaponsEquip = createNumberedCell("", "", fontTableHeading);
		tableWeaponsEquip.addCell(emptyCellWeaponsEquip);
		document.add(tableWeaponsEquip);
		document.add(new Paragraph(" ", new Font())); // Space after main heading

		//==================sub group start (a) Label/Content===========
		PdfPTable tableSubA68 = new PdfPTable(1); // Continuing sequence A68
		tableSubA68.setWidthPercentage(100);
		tableSubA68.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA68 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA68 = new Chunk("Critical deficiencies of arms, weapons, major equipment and transport having bearing on unit training and administration.", fontTableHeadingNonBoldData);
		float labelWidth68 = labelA68.getWidthPoint(); // For hanging indent

		Paragraph subAParagraph68 = new Paragraph();
		subAParagraph68.setIndentationLeft(indentLevel1 + labelWidth68);
		subAParagraph68.setFirstLineIndent(-labelWidth68);
		subAParagraph68.add(labelA68);
		subAParagraph68.add(contentA68);
		subAParagraph68.setSpacingAfter(5f); // Add space before the table

		PdfPCell subACell68 = new PdfPCell(subAParagraph68);
		subACell68.setBorder(Rectangle.NO_BORDER);
		tableSubA68.addCell(subACell68);
		//tableSubA68.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA68);
		//==================sub group End===========

		//==================Table for (a) Weapons Deficiency===========
		PdfPTable tableWeaponDeficiency = new PdfPTable(5); // 5 columns based on (a) to (e)
		tableWeaponDeficiency.setWidthPercentage(100);
		tableWeaponDeficiency.setWidths(new float[]{1f, 3f, 3f, 3f, 2f}); // Adjust relative widths as needed

		// --- Header Row ---
		tableWeaponDeficiency.addCell(createTableCell("Ser No", fontTableHeading, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("Nature of Deficiency", fontTableHeading, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("Action Taken to Meet the Deficiency", fontTableHeading, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("Effect on Conduct of Training or Operational Preparedness", fontTableHeading, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("Remarks", fontTableHeading, Element.ALIGN_CENTER));

		// --- Sub-Header Row ---
		tableWeaponDeficiency.addCell(createTableCell("(a)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("(b)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("(c)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("(d)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableWeaponDeficiency.addCell(createTableCell("(e)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));

		// --- Data Row (Example) ---
		int serialNumber = 1;
		for (Map<String, String> row : weaponDeficiencyList) {
			tableWeaponDeficiency.addCell(createTableCell(String.valueOf(serialNumber++), fontTableHeadingNonBoldData, Element.ALIGN_CENTER));


			tableWeaponDeficiency.addCell(createTableCell(row.getOrDefault("nature_deficiency", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));


			tableWeaponDeficiency.addCell(createTableCell(row.getOrDefault("action_deficiency", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));


			tableWeaponDeficiency.addCell(createTableCell(row.getOrDefault("effect_conduct", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));


			tableWeaponDeficiency.addCell(createTableCell(row.getOrDefault("remarks", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
		}

		tableWeaponDeficiency.setTableEvent(new ImageBackgroundEvent(request,username));

		document.add(tableWeaponDeficiency);
		document.add(new Paragraph(" ", new Font())); // Space after table

		//==================sub group start (b) Label/Content===========
		PdfPTable tableSubA69 = new PdfPTable(1); // A69
		tableSubA69.setWidthPercentage(100);
		tableSubA69.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA69 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA69 = new Chunk("Adequacy of administrative support provided to facilitate training and administration in the unit.", fontTableHeadingNonBoldData);
		float labelWidth69 = labelA69.getWidthPoint();

		Paragraph subAParagraph69 = new Paragraph();
		subAParagraph69.setIndentationLeft(indentLevel1 + labelWidth69);
		subAParagraph69.setFirstLineIndent(-labelWidth69);
		subAParagraph69.add(labelA69);
		subAParagraph69.add(contentA69);
		subAParagraph69.setSpacingAfter(5f); // Optional spacing

		PdfPCell subACell69 = new PdfPCell(subAParagraph69);
		subACell69.setBorder(Rectangle.NO_BORDER);
		tableSubA69.addCell(subACell69);
		tableSubA69.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA69);
		//==================sub group End===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String weaponB = phase2Data.get("state_weapon_item");
		addExpandableInfoBox(document, weaponB, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================


		//==================sub group start (c) Label/Content===========
		PdfPTable tableSubA70 = new PdfPTable(1); // A70
		tableSubA70.setWidthPercentage(100);
		tableSubA70.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA70 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA70 = new Chunk("Adequacy of technical maintenance support and availability of spares in respect of arms, weapons, major equipment and transport affecting operational efficiency.", fontTableHeadingNonBoldData);
		float labelWidth70 = labelA70.getWidthPoint();

		Paragraph subAParagraph70 = new Paragraph();
		subAParagraph70.setIndentationLeft(indentLevel1 + labelWidth70);
		subAParagraph70.setFirstLineIndent(-labelWidth70);
		subAParagraph70.add(labelA70);
		subAParagraph70.add(contentA70);
		// No spacing after the last item in this section (adjust if needed)

		PdfPCell subACell70 = new PdfPCell(subAParagraph70);
		subACell70.setBorder(Rectangle.NO_BORDER);
		tableSubA70.addCell(subACell70);
		tableSubA70.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA70);
		//==================sub group End===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String state_weapon_item = phase2Data.get("state_weapon_item_ii");
		addExpandableInfoBox(document, state_weapon_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 3. State of Weapons/Equipment End ====================
		// ==================== 4. State of Personnel ====================
		PdfPTable tableStatePersonnel = new PdfPTable(2);
		tableStatePersonnel.setWidthPercentage(100);
		tableStatePersonnel.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableStatePersonnelCell1 = createNumberedCell("4.", "State of Personnel", fontTableHeading);
		tableStatePersonnelCell1.setPaddingTop(5f);
		tableStatePersonnel.addCell(tableStatePersonnelCell1);
		PdfPCell emptyCellStatePersonnel = createNumberedCell("", "", fontTableHeading);
		tableStatePersonnel.addCell(emptyCellStatePersonnel);
		document.add(tableStatePersonnel);
		document.add(new Paragraph(" ", new Font())); // Space after main heading


		//==================sub group start (a) Label/Content===========
		PdfPTable tableSubA65 = new PdfPTable(1);
		tableSubA65.setWidthPercentage(100);
		tableSubA65.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA65 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA65 = new Chunk("Critical Deficiency of Manpower Affecting Operational Efficiency.", fontTableHeadingNonBoldData);
		float labelWidth65 = labelA65.getWidthPoint(); // For hanging indent

		Paragraph subAParagraph65 = new Paragraph();
		subAParagraph65.setIndentationLeft(indentLevel1 + labelWidth65);
		subAParagraph65.setFirstLineIndent(-labelWidth65);
		subAParagraph65.add(labelA65);
		subAParagraph65.add(contentA65);
		subAParagraph65.setSpacingAfter(5f); // Add space before the table

		PdfPCell subACell65 = new PdfPCell(subAParagraph65);
		subACell65.setBorder(Rectangle.NO_BORDER);
		tableSubA65.addCell(subACell65);
		document.add(tableSubA65);
		//==================sub group End===========


		//==================Table for (a) Deficiency===========
		PdfPTable tableDeficiency = new PdfPTable(7); // 7 columns based on (a) to (g)
		tableDeficiency.setWidthPercentage(100);
		tableDeficiency.setWidths(new float[]{1f, 1f, 1f, 1f, 2f, 2f, 2f}); // Adjust relative widths as needed

		// --- Header Row 1 & 2 (using rowspan/colspan) ---
		PdfPCell cellH1a = createTableCell("Serial Number", fontTableHeading, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 2, 1); // rowspan=2
		tableDeficiency.addCell(cellH1a);

		PdfPCell cellH1b = createTableCell("Deficiency of Manpower", fontTableHeading, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 1, 3); // colspan=3
		tableDeficiency.addCell(cellH1b);

		PdfPCell cellH1c = createTableCell("Action Taken to Meet the Deficiency", fontTableHeading, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 2, 1); // rowspan=2
		tableDeficiency.addCell(cellH1c);

		PdfPCell cellH1d = createTableCell("Effect on Conduct of Training", fontTableHeading, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 2, 1); // rowspan=2
		tableDeficiency.addCell(cellH1d);

		PdfPCell cellH1e = createTableCell("Remarks", fontTableHeading, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 2, 1); // rowspan=2
		tableDeficiency.addCell(cellH1e);

		// --- Header Row 2 (Cells under Deficiency of Manpower) ---
		tableDeficiency.addCell(createTableCell("Officers", fontTableHeading, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("JCOs", fontTableHeading, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("OR", fontTableHeading, Element.ALIGN_CENTER));

		// --- Sub-Header Row ---
		tableDeficiency.addCell(createTableCell("(a)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(b)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(c)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(d)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(e)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(f)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDeficiency.addCell(createTableCell("(g)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));

		int serialNumberDefi = 1;
		for (Map<String, String> row : getciricaldefimanpowerlist) {
			tableDeficiency.addCell(createTableCell(String.valueOf(serialNumberDefi++), fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("manpower_deficiencyoffrs", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("manpower_deficiencyjco", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("manpower_deficiencyor", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("action_taken", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("training_effect", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDeficiency.addCell(createTableCell(row.getOrDefault("remarks", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
		}
		tableDeficiency.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableDeficiency);
		document.add(new Paragraph(" ", new Font())); // Space after table


		//==================sub group start (b) Label/Content===========
		PdfPTable tableSubA66 = new PdfPTable(1);
		tableSubA66.setWidthPercentage(100);
		tableSubA66.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA66 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA66 = new Chunk("Discipline State including Court Martial and Summary Disposal Cases.", fontTableHeadingNonBoldData);
		float labelWidth66 = labelA66.getWidthPoint();

		Paragraph subAParagraph66 = new Paragraph();
		subAParagraph66.setIndentationLeft(indentLevel1 + labelWidth66);
		subAParagraph66.setFirstLineIndent(-labelWidth66);
		subAParagraph66.add(labelA66);
		subAParagraph66.add(contentA66);
		subAParagraph66.setSpacingAfter(5f); // Add space before the table

		PdfPCell subACell66 = new PdfPCell(subAParagraph66);
		subACell66.setBorder(Rectangle.NO_BORDER);
		tableSubA66.addCell(subACell66);
		document.add(tableSubA66);
		//==================sub group End===========

		//==================Table for (b) Discipline===========
		PdfPTable tableDiscipline = new PdfPTable(5); // 5 columns based on (a) to (e)
		tableDiscipline.setWidthPercentage(100);
		tableDiscipline.setWidths(new float[]{1f, 3f, 2f, 3f, 2f}); // Adjust relative widths

		// --- Header Row ---
		tableDiscipline.addCell(createTableCell("Ser No", fontTableHeading, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("Ongoing Court Martial Cases in the unit/ FormationHQ including very Old Cases", fontTableHeading, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("Cases Pending Decision", fontTableHeading, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("Cases Reported in the Current Reporting Year and Summarily Disposed Off by the Commanding Officer", fontTableHeading, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("Remarks", fontTableHeading, Element.ALIGN_CENTER));

		// --- Sub-Header Row ---
		tableDiscipline.addCell(createTableCell("(a)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("(b)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("(c)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("(d)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
		tableDiscipline.addCell(createTableCell("(e)", fontTableHeadingNonBoldData, Element.ALIGN_CENTER));

		// --- Data Row (Example) ---
		int serialDisciplpine = 1;
		for (Map<String, String> row : discipline) {
			tableDiscipline.addCell(createTableCell(String.valueOf(serialDisciplpine++), fontTableHeadingNonBoldData, Element.ALIGN_CENTER));
			tableDiscipline.addCell(createTableCell(row.getOrDefault("ongoing_count", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDiscipline.addCell(createTableCell(row.getOrDefault("pending_cases", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDiscipline.addCell(createTableCell(row.getOrDefault("cases_current", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
			tableDiscipline.addCell(createTableCell(row.getOrDefault("remarks", ""), fontTableHeadingNonBoldData, Element.ALIGN_LEFT));
		}
		tableDiscipline.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableDiscipline);
		document.add(new Paragraph(" ", new Font())); // Space after table

		//==================sub group start (c) Label/Content===========
		PdfPTable tableSubA67 = new PdfPTable(1);
		tableSubA67.setWidthPercentage(100);
		tableSubA67.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk labelA67 = new Chunk("(c) ", fontTableHeading);
		// Combine the lines from the image into one Chunk, iText will wrap it
		Chunk contentA67 = new Chunk("Availability of Officers in the Unit. Deficiency of officers/ profile etc. affecting operational preparedness of the Unit/ Formation HQ/ Establishment be highlighted", fontTableHeadingNonBoldData);
		float labelWidth67 = labelA67.getWidthPoint();

		Paragraph subAParagraph67 = new Paragraph();
		subAParagraph67.setIndentationLeft(indentLevel1 + labelWidth67);
		subAParagraph67.setFirstLineIndent(-labelWidth67);
		subAParagraph67.add(labelA67);
		subAParagraph67.add(contentA67);

		PdfPCell subACell67 = new PdfPCell(subAParagraph67);
		subACell67.setBorder(Rectangle.NO_BORDER);
		tableSubA67.addCell(subACell67);
		document.add(tableSubA67);
		//==================sub group End===========
		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String state_personnel_item = phase2Data.get("state_personnel_item");
		addExpandableInfoBox(document, state_personnel_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================


		document.add(new Phrase("\n"));
		// ============================= 4. State of Personnel End ====================
		// ==================== 5.Administration ====================
		PdfPTable tableAdministration = new PdfPTable(2);
		tableAdministration.setWidthPercentage(100);
		tableAdministration.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableAdministrationCell2 = createNumberedCell("5.", "Administration", fontTableHeading);
		tableAdministrationCell2.setPaddingTop(5f);
		tableAdministration.addCell(tableAdministrationCell2);
		PdfPCell emptyCellAdministration = createNumberedCell("", "", fontTableHeading);
		tableAdministration.addCell(emptyCellAdministration);
		document.add(tableAdministration);
		//document.add(new Paragraph(" ", new Font()));

		//==================sub group start===========
		PdfPTable tableSubA3 = new PdfPTable(1);
		tableSubA3.setWidthPercentage(100);
		tableSubA3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph3 = new Paragraph();
		Chunk labelA3 = new Chunk("(a)", fontTableHeading);
		Chunk contentA3 = new Chunk(" Accommodation and Ancilliaries", fontTableHeadingNonBoldData);
		subAParagraph3.add(labelA3);
		subAParagraph3.add(contentA3);
		subAParagraph3.setIndentationLeft(20f);

		PdfPCell subACell3 = new PdfPCell(subAParagraph3);
		subACell3.setBorder(Rectangle.NO_BORDER);
		tableSubA3.addCell(subACell3);
		document.add(tableSubA3);
		//document.add(new Paragraph(" ", new Font()));

		//==================sub group End===========

		//==================sub group start===========
		PdfPTable tableSubA4 = new PdfPTable(1);
		tableSubA4.setWidthPercentage(100);
		tableSubA4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph4 = new Paragraph();
		Chunk labelA4 = new Chunk("(b)", fontTableHeading);
		Chunk contentA4 = new Chunk(" Water Supply to troops (including married accommodation).", fontTableHeadingNonBoldData);
		subAParagraph4.add(labelA4);
		subAParagraph4.add(contentA4);
		subAParagraph4.setIndentationLeft(20f);

		PdfPCell subACell4 = new PdfPCell(subAParagraph4);
		subACell4.setBorder(Rectangle.NO_BORDER);
		tableSubA4.addCell(subACell4);
		document.add(tableSubA4);
		//		document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========

		//==================sub group start===========
		PdfPTable tableSubA5 = new PdfPTable(1);
		tableSubA5.setWidthPercentage(100);
		tableSubA5.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph5 = new Paragraph();
		Chunk labelA5 = new Chunk("(c)", fontTableHeading);
		Chunk contentA5 = new Chunk(" Timely adjustment of pay and allowances in Quarterly Statement of Accounts by \r\n"
				+ "PAO (OR)	\r\n"
				+ "", fontTableHeadingNonBoldData);
		subAParagraph5.add(labelA5);
		subAParagraph5.add(contentA5);
		subAParagraph5.setIndentationLeft(20f);

		PdfPCell subACell5 = new PdfPCell(subAParagraph5);
		subACell5.setBorder(Rectangle.NO_BORDER);
		tableSubA5.addCell(subACell5);
		document.add(tableSubA5);
		//		document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========

		//==================sub group start===========
		PdfPTable tableSubA6 = new PdfPTable(1);
		tableSubA6.setWidthPercentage(100);
		tableSubA6.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph6 = new Paragraph();
		Chunk labelA6 = new Chunk("(d)", fontTableHeading);
		Chunk contentA6 = new Chunk(" Health/ Hygiene.", fontTableHeadingNonBoldData);
		subAParagraph6.add(labelA6);
		subAParagraph6.add(contentA6);
		subAParagraph6.setIndentationLeft(20f);

		PdfPCell subACell6 = new PdfPCell(subAParagraph6);
		subACell6.setBorder(Rectangle.NO_BORDER);
		tableSubA6.addCell(subACell6);
		document.add(tableSubA6);
		//				document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========

		//==================sub group start (e)===========
		PdfPTable tableSubA7 = new PdfPTable(1);
		tableSubA7.setWidthPercentage(100);
		tableSubA7.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph7 = new Paragraph();
		Chunk labelA7 = new Chunk("(e) ", fontTableHeading); // Changed label to (e)
		Chunk contentA7 = new Chunk(" Leave State", fontTableHeadingNonBoldData); // *** REPLACE THIS CONTENT ***
		subAParagraph7.add(labelA7);
		subAParagraph7.add(contentA7);
		subAParagraph7.setIndentationLeft(20f);

		PdfPCell subACell7 = new PdfPCell(subAParagraph7);
		subACell7.setBorder(Rectangle.NO_BORDER);
		tableSubA7.addCell(subACell7);
		document.add(tableSubA7);
		//		      document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========


		//==================sub group start (f)===========
		PdfPTable tableSubA8 = new PdfPTable(1);
		tableSubA8.setWidthPercentage(100);
		tableSubA8.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph8 = new Paragraph();
		Chunk labelA8 = new Chunk("(f) ", fontTableHeading); // Changed label to (f)
		Chunk contentA8 = new Chunk(" State of rations provided to the troops including variety, quality and as authorised", fontTableHeadingNonBoldData); // *** REPLACE THIS CONTENT ***
		subAParagraph8.add(labelA8);
		subAParagraph8.add(contentA8);
		subAParagraph8.setIndentationLeft(20f);

		PdfPCell subACell8 = new PdfPCell(subAParagraph8);
		subACell8.setBorder(Rectangle.NO_BORDER);
		tableSubA8.addCell(subACell8);
		document.add(tableSubA8);
		//		      document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========


		//==================sub group start (g)===========
		PdfPTable tableSubA9 = new PdfPTable(1);
		tableSubA9.setWidthPercentage(100);
		tableSubA9.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph9 = new Paragraph();
		Chunk labelA9 = new Chunk("(g) ", fontTableHeading);
		Chunk contentA9 = new Chunk(" Welfare measures in place for families residing in Field Area Family Accommodation (FAFA)", fontTableHeadingNonBoldData);
		subAParagraph9.add(labelA9);
		subAParagraph9.add(contentA9);
		subAParagraph9.setIndentationLeft(20f);

		PdfPCell subACell9 = new PdfPCell(subAParagraph9);
		subACell9.setBorder(Rectangle.NO_BORDER);
		tableSubA9.addCell(subACell9);
		document.add(tableSubA9);
		//		      document.add(new Paragraph(" ", new Font()));
		//==================sub group end===========

		//==================sub group start (h)===========
		PdfPTable tableSubA10 = new PdfPTable(1);
		tableSubA10.setWidthPercentage(100);
		tableSubA10.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph10 = new Paragraph();
		Chunk labelA10 = new Chunk("(h) ", fontTableHeading);
		Chunk contentA10 = new Chunk(" Sports facilities in terms of equipment and accessories made available to all ranks and opportunity afforded to them to make judicious use of the same.", fontTableHeadingNonBoldData); // *** REPLACE THIS CONTENT ***, Variable suffix 10
		subAParagraph10.add(labelA10);
		subAParagraph10.add(contentA10);
		subAParagraph10.setIndentationLeft(20f);

		PdfPCell subACell10 = new PdfPCell(subAParagraph10);
		subACell10.setBorder(Rectangle.NO_BORDER);
		tableSubA10.addCell(subACell10);
		document.add(tableSubA10);
		//		      document.add(new Paragraph(" ", new Font())); // Or use document.add(Chunk.NEWLINE);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String administation_item = phase2Data.get("administation_item");
		addExpandableInfoBox(document, administation_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));


		// ==================== 6. Aspects Affecting Morale and Motivation ====================
		PdfPTable tableMorale = new PdfPTable(2); // Changed variable name
		tableMorale.setWidthPercentage(100);
		tableMorale.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Using your createNumberedCell method for the main heading
		PdfPCell tableMoraleCell1 = createNumberedCell("6.", "Aspects Affecting Morale and Motivation.", fontTableHeading); // Updated number and text
		tableMoraleCell1.setPaddingTop(5f);
		tableMorale.addCell(tableMoraleCell1);
		PdfPCell emptyCellMorale = createNumberedCell("", "", fontTableHeading); // Changed variable name
		tableMorale.addCell(emptyCellMorale);
		document.add(tableMorale);
		//document.add(new Paragraph(" ", new Font())); // Space after main heading

		//==================sub group start (a)===========
		PdfPTable tableSubA11 = new PdfPTable(1); // Continue numbering: A11
		tableSubA11.setWidthPercentage(100);
		tableSubA11.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph11 = new Paragraph(); // A11
		Chunk labelA11 = new Chunk("(a) ", fontTableHeading); // A11, label (a)
		Chunk contentA11 = new Chunk("Number of untoward incidents with details", fontTableHeadingNonBoldData); // A11, Content for (a)
		subAParagraph11.add(labelA11);
		subAParagraph11.add(contentA11);
		subAParagraph11.setIndentationLeft(20f);

		PdfPCell subACell11 = new PdfPCell(subAParagraph11); // A11
		subACell11.setBorder(Rectangle.NO_BORDER);
		tableSubA11.addCell(subACell11);
		document.add(tableSubA11);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA12 = new PdfPTable(1); // A12
		tableSubA12.setWidthPercentage(100);
		tableSubA12.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph12 = new Paragraph(); // A12
		Chunk labelA12 = new Chunk("(b) ", fontTableHeading); // A12, label (b)
		Chunk contentA12 = new Chunk("Number of Mechanical Transport accidents", fontTableHeadingNonBoldData); // A12, Content for (b)
		subAParagraph12.add(labelA12);
		subAParagraph12.add(contentA12);
		subAParagraph12.setIndentationLeft(20f);

		PdfPCell subACell12 = new PdfPCell(subAParagraph12); // A12
		subACell12.setBorder(Rectangle.NO_BORDER);
		tableSubA12.addCell(subACell12);
		document.add(tableSubA12);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA13 = new PdfPTable(1); // A13
		tableSubA13.setWidthPercentage(100);
		tableSubA13.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph13 = new Paragraph(); // A13
		Chunk labelA13 = new Chunk("(c) ", fontTableHeading); // A13, label (c)
		Chunk contentA13 = new Chunk("ACC/ SL/ RCO commission.", fontTableHeadingNonBoldData); // A13, Content for (c)
		subAParagraph13.add(labelA13);
		subAParagraph13.add(contentA13);
		subAParagraph13.setIndentationLeft(20f);

		PdfPCell subACell13 = new PdfPCell(subAParagraph13); // A13
		subACell13.setBorder(Rectangle.NO_BORDER);
		tableSubA13.addCell(subACell13);
		document.add(tableSubA13);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA14 = new PdfPTable(1); // A14
		tableSubA14.setWidthPercentage(100);
		tableSubA14.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph14 = new Paragraph(); // A14
		Chunk labelA14 = new Chunk("(d) ", fontTableHeading); // A14, label (d)
		Chunk contentA14 = new Chunk("Measures taken to improve level of morale and motivation of personnel", fontTableHeadingNonBoldData); // A14, Content for (d)
		subAParagraph14.add(labelA14);
		subAParagraph14.add(contentA14);
		subAParagraph14.setIndentationLeft(20f);

		PdfPCell subACell14 = new PdfPCell(subAParagraph14); // A14
		subACell14.setBorder(Rectangle.NO_BORDER);
		tableSubA14.addCell(subACell14);
		document.add(tableSubA14);
		//==================sub group end===========

		//==================sub group start (e)===========
		PdfPTable tableSubA15 = new PdfPTable(1); // A15
		tableSubA15.setWidthPercentage(100);
		tableSubA15.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph15 = new Paragraph(); // A15
		Chunk labelA15 = new Chunk("(e) ", fontTableHeading); // A15, label (e)
		Chunk contentA15 = new Chunk("Measures to ensure adherence to and imbibing of Indian Army Core Values by all ranks", fontTableHeadingNonBoldData); // A15, Content for (e)
		subAParagraph15.add(labelA15);
		subAParagraph15.add(contentA15);
		subAParagraph15.setIndentationLeft(20f);

		PdfPCell subACell15 = new PdfPCell(subAParagraph15); // A15
		subACell15.setBorder(Rectangle.NO_BORDER);
		tableSubA15.addCell(subACell15);
		document.add(tableSubA15);
		//==================sub group end===========

		//==================sub group start (f)===========
		PdfPTable tableSubA16 = new PdfPTable(1); // A16
		tableSubA16.setWidthPercentage(100);
		tableSubA16.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph16 = new Paragraph(); // A16
		Chunk labelA16 = new Chunk("(f) ", fontTableHeading); // A16, label (f)
		Chunk contentA16 = new Chunk("Measures taken for resolution of problems of individual soldiers", fontTableHeadingNonBoldData); // A16, Content for (f)
		subAParagraph16.add(labelA16);
		subAParagraph16.add(contentA16);
		subAParagraph16.setIndentationLeft(20f);

		PdfPCell subACell16 = new PdfPCell(subAParagraph16); // A16
		subACell16.setBorder(Rectangle.NO_BORDER);
		tableSubA16.addCell(subACell16);
		document.add(tableSubA16);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String aspect_item = phase2Data.get("aspect_item");
		addExpandableInfoBox(document, aspect_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 6. Aspects Affecting Morale and Motivation End ====================


		// ==================== 7. Interior Economy ====================
		PdfPTable tableInteriorEconomy = new PdfPTable(2); // Changed variable name
		tableInteriorEconomy.setWidthPercentage(100);
		tableInteriorEconomy.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Using your createNumberedCell method for the main heading
		PdfPCell tableInteriorEconomyCell1 = createNumberedCell("7.", "Interior Economy.", fontTableHeading); // Updated number and text
		tableInteriorEconomyCell1.setPaddingTop(5f);
		tableInteriorEconomy.addCell(tableInteriorEconomyCell1);
		PdfPCell emptyCellInteriorEconomy = createNumberedCell("", "", fontTableHeading); // Changed variable name
		tableInteriorEconomy.addCell(emptyCellInteriorEconomy);
		document.add(tableInteriorEconomy);
		// No space needed here if the intro sentence follows immediately

		// Add the introductory sentence as a separate paragraph
		Paragraph introParagraph7 = new Paragraph(
				"The issues included in the interior economy shall dwell on following aspects :-",
				fontTableHeadingNonBoldData); // Using non-bold font for the intro
		introParagraph7.setIndentationLeft(20f); // Indent similar to sub-items, or adjust as needed
		introParagraph7.setSpacingAfter(5f); // Add some space before the first sub-item
		document.add(introParagraph7);


		//==================sub group start (a)===========
		PdfPTable tableSubA17 = new PdfPTable(1); // Continue numbering: A17
		tableSubA17.setWidthPercentage(100);
		tableSubA17.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph17 = new Paragraph(); // A17
		Chunk labelA17 = new Chunk("(a) ", fontTableHeading); // A17, label (a)
		Chunk contentA17 = new Chunk("Living conditions of all ranks and families.", fontTableHeadingNonBoldData); // A17, Content for (a)
		subAParagraph17.add(labelA17);
		subAParagraph17.add(contentA17);
		subAParagraph17.setIndentationLeft(20f);

		PdfPCell subACell17 = new PdfPCell(subAParagraph17); // A17
		subACell17.setBorder(Rectangle.NO_BORDER);
		tableSubA17.addCell(subACell17);
		document.add(tableSubA17);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA18 = new PdfPTable(1); // A18
		tableSubA18.setWidthPercentage(100);
		tableSubA18.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph18 = new Paragraph(); // A18
		Chunk labelA18 = new Chunk("(b) ", fontTableHeading); // A18, label (b)
		Chunk contentA18 = new Chunk("State of issue of personal clothing.", fontTableHeadingNonBoldData); // A18, Content for (b)
		subAParagraph18.add(labelA18);
		subAParagraph18.add(contentA18);
		subAParagraph18.setIndentationLeft(20f);

		PdfPCell subACell18 = new PdfPCell(subAParagraph18); // A18
		subACell18.setBorder(Rectangle.NO_BORDER);
		tableSubA18.addCell(subACell18);
		document.add(tableSubA18);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA19 = new PdfPTable(1); // A19
		tableSubA19.setWidthPercentage(100);
		tableSubA19.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph19 = new Paragraph(); // A19
		Chunk labelA19 = new Chunk("(c) ", fontTableHeading); // A19, label (c)
		Chunk contentA19 = new Chunk("Initiative taken by units to carry out improvement in their living habitat.", fontTableHeadingNonBoldData); // A19, Content for (c)
		subAParagraph19.add(labelA19);
		subAParagraph19.add(contentA19);
		subAParagraph19.setIndentationLeft(20f);

		PdfPCell subACell19 = new PdfPCell(subAParagraph19); // A19
		subACell19.setBorder(Rectangle.NO_BORDER);
		tableSubA19.addCell(subACell19);
		document.add(tableSubA19);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA20 = new PdfPTable(1); // A20
		tableSubA20.setWidthPercentage(100);
		tableSubA20.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph20 = new Paragraph(); // A20
		Chunk labelA20 = new Chunk("(d) ", fontTableHeading); // A20, label (d)
		Chunk contentA20 = new Chunk("Facilities provided in the living area in terms of comfort", fontTableHeadingNonBoldData); // A20, Content for (d)
		subAParagraph20.add(labelA20);
		subAParagraph20.add(contentA20);
		subAParagraph20.setIndentationLeft(20f);

		PdfPCell subACell20 = new PdfPCell(subAParagraph20); // A20
		subACell20.setBorder(Rectangle.NO_BORDER);
		tableSubA20.addCell(subACell20);
		document.add(tableSubA20);
		//==================sub group end===========

		//==================sub group start (e)===========
		PdfPTable tableSubA21 = new PdfPTable(1); // A21
		tableSubA21.setWidthPercentage(100);
		tableSubA21.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph21 = new Paragraph(); // A21
		Chunk labelA21 = new Chunk("(e) ", fontTableHeading); // A21, label (e)
		Chunk contentA21 = new Chunk("Modern equipment provided in the company kitchens to facilitate cooking, as well as save manpower.", fontTableHeadingNonBoldData); // A21, Content for (e)
		subAParagraph21.add(labelA21);
		subAParagraph21.add(contentA21);
		subAParagraph21.setIndentationLeft(20f);

		PdfPCell subACell21 = new PdfPCell(subAParagraph21); // A21
		subACell21.setBorder(Rectangle.NO_BORDER);
		tableSubA21.addCell(subACell21);
		document.add(tableSubA21);
		//==================sub group end===========

		//==================sub group start (f)===========
		PdfPTable tableSubA22 = new PdfPTable(1); // A22
		tableSubA22.setWidthPercentage(100);
		tableSubA22.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph22 = new Paragraph(); // A22
		Chunk labelA22 = new Chunk("(f) ", fontTableHeading); // A22, label (f)
		Chunk contentA22 = new Chunk("Equipment procured by the unit for area maintenance to economise on the manpower that can be usefully employed on maintenance duties or for training.", fontTableHeadingNonBoldData); // A22, Content for (f)
		subAParagraph22.add(labelA22);
		subAParagraph22.add(contentA22);
		subAParagraph22.setIndentationLeft(20f);

		PdfPCell subACell22 = new PdfPCell(subAParagraph22); // A22
		subACell22.setBorder(Rectangle.NO_BORDER);
		tableSubA22.addCell(subACell22);
		document.add(tableSubA22);
		//==================sub group end===========

		//==================sub group start (g)===========
		PdfPTable tableSubA23 = new PdfPTable(1); // A23
		tableSubA23.setWidthPercentage(100);
		tableSubA23.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph23 = new Paragraph(); // A23
		Chunk labelA23 = new Chunk("(g) ", fontTableHeading); // A23, label (g)
		Chunk contentA23 = new Chunk("Timely and correct publication of individual casualties.", fontTableHeadingNonBoldData); // A23, Content for (g)
		subAParagraph23.add(labelA23);
		subAParagraph23.add(contentA23);
		subAParagraph23.setIndentationLeft(20f);

		PdfPCell subACell23 = new PdfPCell(subAParagraph23); // A23
		subACell23.setBorder(Rectangle.NO_BORDER);
		tableSubA23.addCell(subACell23);
		document.add(tableSubA23);
		//==================sub group end===========

		//==================sub group start (h)===========
		PdfPTable tableSubA24 = new PdfPTable(1); // A24
		tableSubA24.setWidthPercentage(100);
		tableSubA24.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph24 = new Paragraph(); // A24
		Chunk labelA24 = new Chunk("(h) ", fontTableHeading); // A24, label (h)
		Chunk contentA24 = new Chunk("State of individual documentation.", fontTableHeadingNonBoldData); // A24, Content for (h)
		subAParagraph24.add(labelA24);
		subAParagraph24.add(contentA24);
		subAParagraph24.setIndentationLeft(20f);

		PdfPCell subACell24 = new PdfPCell(subAParagraph24); // A24
		subACell24.setBorder(Rectangle.NO_BORDER);
		tableSubA24.addCell(subACell24);
		tableSubA24.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA24);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String interior_item = phase2Data.get("interior_item");
		addExpandableInfoBox(document, interior_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 7. Interior Economy End ====================


		// ==================== 8. Major Achievements ====================
		PdfPTable tableMajorAchievements = new PdfPTable(2);
		tableMajorAchievements.setWidthPercentage(100);
		tableMajorAchievements.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableMajorAchievementsCell1 = createNumberedCell("8.", "Major Achievements.", fontTableHeading);
		tableMajorAchievementsCell1.setPaddingTop(5f);
		tableMajorAchievements.addCell(tableMajorAchievementsCell1);
		PdfPCell emptyCellMajorAchievements = createNumberedCell("", "", fontTableHeading);
		tableMajorAchievements.addCell(emptyCellMajorAchievements);
		document.add(tableMajorAchievements);
		//  document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA25 = new PdfPTable(1);
		tableSubA25.setWidthPercentage(100);
		tableSubA25.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph25 = new Paragraph();
		Chunk labelA25 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA25 = new Chunk("Operations.", fontTableHeadingNonBoldData);
		subAParagraph25.add(labelA25);
		subAParagraph25.add(contentA25);
		subAParagraph25.setIndentationLeft(20f);

		PdfPCell subACell25 = new PdfPCell(subAParagraph25);
		subACell25.setBorder(Rectangle.NO_BORDER);
		tableSubA25.addCell(subACell25);
		document.add(tableSubA25);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA26 = new PdfPTable(1);
		tableSubA26.setWidthPercentage(100);
		tableSubA26.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph26 = new Paragraph();
		Chunk labelA26 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA26 = new Chunk("Training / improvement of training infrastructure.", fontTableHeadingNonBoldData);
		subAParagraph26.add(labelA26);
		subAParagraph26.add(contentA26);
		subAParagraph26.setIndentationLeft(20f);

		PdfPCell subACell26 = new PdfPCell(subAParagraph26);
		subACell26.setBorder(Rectangle.NO_BORDER);
		tableSubA26.addCell(subACell26);
		document.add(tableSubA26);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA27 = new PdfPTable(1);
		tableSubA27.setWidthPercentage(100);
		tableSubA27.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph27 = new Paragraph();
		Chunk labelA27 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA27 = new Chunk("Administration.", fontTableHeadingNonBoldData);
		subAParagraph27.add(labelA27);
		subAParagraph27.add(contentA27);
		subAParagraph27.setIndentationLeft(20f);

		PdfPCell subACell27 = new PdfPCell(subAParagraph27);
		subACell27.setBorder(Rectangle.NO_BORDER);
		tableSubA27.addCell(subACell27);
		document.add(tableSubA27);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA28 = new PdfPTable(1);
		tableSubA28.setWidthPercentage(100);
		tableSubA28.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph28 = new Paragraph();
		Chunk labelA28 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA28 = new Chunk("Sports", fontTableHeadingNonBoldData);
		subAParagraph28.add(labelA28);
		subAParagraph28.add(contentA28);
		subAParagraph28.setIndentationLeft(20f);

		PdfPCell subACell28 = new PdfPCell(subAParagraph28);
		subACell28.setBorder(Rectangle.NO_BORDER);
		tableSubA28.addCell(subACell28);
		document.add(tableSubA28);
		//==================sub group end===========

		//==================sub group start (e)===========
		PdfPTable tableSubA29 = new PdfPTable(1);
		tableSubA29.setWidthPercentage(100);
		tableSubA29.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph29 = new Paragraph();
		Chunk labelA29 = new Chunk("(e) ", fontTableHeading);
		Chunk contentA29 = new Chunk("Innovations made in the field of training and training aids etc.", fontTableHeadingNonBoldData);
		subAParagraph29.add(labelA29);
		subAParagraph29.add(contentA29);
		subAParagraph29.setIndentationLeft(20f);

		PdfPCell subACell29 = new PdfPCell(subAParagraph29);
		subACell29.setBorder(Rectangle.NO_BORDER);
		tableSubA29.addCell(subACell29);
		document.add(tableSubA29);
		//==================sub group end===========

		//==================sub group start (f)===========
		PdfPTable tableSubA30 = new PdfPTable(1);
		tableSubA30.setWidthPercentage(100);
		tableSubA30.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph30 = new Paragraph();
		Chunk labelA30 = new Chunk("(f) ", fontTableHeading);
		Chunk contentA30 = new Chunk("Individual achievements of special nature.", fontTableHeadingNonBoldData);
		subAParagraph30.add(labelA30);
		subAParagraph30.add(contentA30);
		subAParagraph30.setIndentationLeft(20f);

		PdfPCell subACell30 = new PdfPCell(subAParagraph30);
		subACell30.setBorder(Rectangle.NO_BORDER);
		tableSubA30.addCell(subACell30);
		document.add(tableSubA30);
		//==================sub group end===========

		//==================sub group start (g)===========
		PdfPTable tableSubA31 = new PdfPTable(1);
		tableSubA31.setWidthPercentage(100);
		tableSubA31.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph31 = new Paragraph();
		Chunk labelA31 = new Chunk("(g) ", fontTableHeading);
		Chunk contentA31 = new Chunk("Achievements Resulting in the Overall Improvement of the Unit.", fontTableHeadingNonBoldData);
		subAParagraph31.add(labelA31);
		subAParagraph31.add(contentA31);
		subAParagraph31.setIndentationLeft(20f);

		PdfPCell subACell31 = new PdfPCell(subAParagraph31);
		subACell31.setBorder(Rectangle.NO_BORDER);
		tableSubA31.addCell(subACell31);
		tableSubA31.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA31);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String major_achievements_item = phase2Data.get("major_achievements_item");
		addExpandableInfoBox(document, major_achievements_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 8. Major Achievements End ====================


		// ==================== 9. Strength of the Unit ====================
		PdfPTable tableStrength = new PdfPTable(2);
		tableStrength.setWidthPercentage(100);
		tableStrength.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableStrengthCell1 = createNumberedCell("9.", "Strength of the Unit.", fontTableHeading);
		tableStrengthCell1.setPaddingTop(5f);
		tableStrength.addCell(tableStrengthCell1);
		PdfPCell emptyCellStrength = createNumberedCell("", "", fontTableHeading);
		tableStrength.addCell(emptyCellStrength);
		document.add(tableStrength);
		//document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA32 = new PdfPTable(1);
		tableSubA32.setWidthPercentage(100);
		tableSubA32.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph32 = new Paragraph();
		Chunk labelA32 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA32 = new Chunk("Operations.", fontTableHeadingNonBoldData);
		subAParagraph32.add(labelA32);
		subAParagraph32.add(contentA32);
		subAParagraph32.setIndentationLeft(20f);

		PdfPCell subACell32 = new PdfPCell(subAParagraph32);
		subACell32.setBorder(Rectangle.NO_BORDER);
		tableSubA32.addCell(subACell32);
		document.add(tableSubA32);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA33 = new PdfPTable(1);
		tableSubA33.setWidthPercentage(100);
		tableSubA33.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph33 = new Paragraph();
		Chunk labelA33 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA33 = new Chunk("Training", fontTableHeadingNonBoldData);
		subAParagraph33.add(labelA33);
		subAParagraph33.add(contentA33);
		subAParagraph33.setIndentationLeft(20f);

		PdfPCell subACell33 = new PdfPCell(subAParagraph33);
		subACell33.setBorder(Rectangle.NO_BORDER);
		tableSubA33.addCell(subACell33);
		document.add(tableSubA33);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA34 = new PdfPTable(1);
		tableSubA34.setWidthPercentage(100);
		tableSubA34.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph34 = new Paragraph();
		Chunk labelA34 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA34 = new Chunk("Administration", fontTableHeadingNonBoldData);
		subAParagraph34.add(labelA34);
		subAParagraph34.add(contentA34);
		subAParagraph34.setIndentationLeft(20f);

		PdfPCell subACell34 = new PdfPCell(subAParagraph34);
		subACell34.setBorder(Rectangle.NO_BORDER);
		tableSubA34.addCell(subACell34);
		document.add(tableSubA34);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA35 = new PdfPTable(1);
		tableSubA35.setWidthPercentage(100);
		tableSubA35.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph35 = new Paragraph();
		Chunk labelA35 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA35 = new Chunk("Sports and Miscellaneous.", fontTableHeadingNonBoldData);
		subAParagraph35.add(labelA35);
		subAParagraph35.add(contentA35);
		subAParagraph35.setIndentationLeft(20f);

		PdfPCell subACell35 = new PdfPCell(subAParagraph35);
		subACell35.setBorder(Rectangle.NO_BORDER);
		tableSubA35.addCell(subACell35);
		document.add(tableSubA35);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String strength_of_unit_item = phase2Data.get("strength_of_unit_item");
		addExpandableInfoBox(document, strength_of_unit_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================
		document.add(new Phrase("\n"));
		// ============================= 9. Strength of the Unit End ====================


		// ==================== 10. Challenges ====================
		PdfPTable tableChallenges = new PdfPTable(2);
		tableChallenges.setWidthPercentage(100);
		tableChallenges.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableChallengesCell1 = createNumberedCell("10.", "Challenges.", fontTableHeading);
		tableChallengesCell1.setPaddingTop(5f);
		tableChallenges.addCell(tableChallengesCell1);
		PdfPCell emptyCellChallenges = createNumberedCell("", "", fontTableHeading);
		tableChallenges.addCell(emptyCellChallenges);
		document.add(tableChallenges);
		// document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA36 = new PdfPTable(1);
		tableSubA36.setWidthPercentage(100);
		tableSubA36.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph36 = new Paragraph();
		Chunk labelA36 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA36 = new Chunk("Operational shortcomings with respect to manpower, weapons, ammunition, vehicles and equipment.", fontTableHeadingNonBoldData);
		subAParagraph36.add(labelA36);
		subAParagraph36.add(contentA36);
		subAParagraph36.setIndentationLeft(20f);

		PdfPCell subACell36 = new PdfPCell(subAParagraph36);
		subACell36.setBorder(Rectangle.NO_BORDER);
		tableSubA36.addCell(subACell36);
		document.add(tableSubA36);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA37 = new PdfPTable(1);
		tableSubA37.setWidthPercentage(100);
		tableSubA37.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph37 = new Paragraph();
		Chunk labelA37 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA37 = new Chunk("Training with respect to training infrastructure, training area, equipment and training aids.", fontTableHeadingNonBoldData);
		subAParagraph37.add(labelA37);
		subAParagraph37.add(contentA37);
		subAParagraph37.setIndentationLeft(20f);

		PdfPCell subACell37 = new PdfPCell(subAParagraph37);
		subACell37.setBorder(Rectangle.NO_BORDER);
		tableSubA37.addCell(subACell37);
		document.add(tableSubA37);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA38 = new PdfPTable(1);
		tableSubA38.setWidthPercentage(100);
		tableSubA38.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph38 = new Paragraph();
		Chunk labelA38 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA38 = new Chunk("Administration", fontTableHeadingNonBoldData);
		subAParagraph38.add(labelA38);
		subAParagraph38.add(contentA38);
		subAParagraph38.setIndentationLeft(20f);

		PdfPCell subACell38 = new PdfPCell(subAParagraph38);
		subACell38.setBorder(Rectangle.NO_BORDER);
		tableSubA38.addCell(subACell38);
		document.add(tableSubA38);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA39 = new PdfPTable(1);
		tableSubA39.setWidthPercentage(100);
		tableSubA39.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph39 = new Paragraph();
		Chunk labelA39 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA39 = new Chunk("Sports with respect to sports field and sports equipment", fontTableHeadingNonBoldData);
		subAParagraph39.add(labelA39);
		subAParagraph39.add(contentA39);
		subAParagraph39.setIndentationLeft(20f);

		PdfPCell subACell39 = new PdfPCell(subAParagraph39);
		subACell39.setBorder(Rectangle.NO_BORDER);
		tableSubA39.addCell(subACell39);
		tableSubA39.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA39);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String challenges_item = phase2Data.get("challenges_item");
		addExpandableInfoBox(document, challenges_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================
		document.add(new Phrase("\n"));
		// ============================= 10. Challenges End ====================

		// ==================== 11. Recommendations / Innovations ====================
		PdfPTable tableRecommendations = new PdfPTable(2);
		tableRecommendations.setWidthPercentage(100);
		tableRecommendations.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableRecommendationsCell1 = createNumberedCell("11.", "Recommendations / Innovations to improve the following.", fontTableHeading);
		tableRecommendationsCell1.setPaddingTop(5f);
		tableRecommendations.addCell(tableRecommendationsCell1);
		PdfPCell emptyCellRecommendations = createNumberedCell("", "", fontTableHeading);
		tableRecommendations.addCell(emptyCellRecommendations);
		document.add(tableRecommendations);
		// document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA40 = new PdfPTable(1);
		tableSubA40.setWidthPercentage(100);
		tableSubA40.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph40 = new Paragraph();
		Chunk labelA40 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA40 = new Chunk("Training", fontTableHeadingNonBoldData);
		subAParagraph40.add(labelA40);
		subAParagraph40.add(contentA40);
		subAParagraph40.setIndentationLeft(20f);

		PdfPCell subACell40 = new PdfPCell(subAParagraph40);
		subACell40.setBorder(Rectangle.NO_BORDER);
		tableSubA40.addCell(subACell40);
		document.add(tableSubA40);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA41 = new PdfPTable(1);
		tableSubA41.setWidthPercentage(100);
		tableSubA41.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph41 = new Paragraph();
		Chunk labelA41 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA41 = new Chunk("Administration", fontTableHeadingNonBoldData);
		subAParagraph41.add(labelA41);
		subAParagraph41.add(contentA41);
		subAParagraph41.setIndentationLeft(20f);

		PdfPCell subACell41 = new PdfPCell(subAParagraph41);
		subACell41.setBorder(Rectangle.NO_BORDER);
		tableSubA41.addCell(subACell41);
		document.add(tableSubA41);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA42 = new PdfPTable(1);
		tableSubA42.setWidthPercentage(100);
		tableSubA42.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph42 = new Paragraph();
		Chunk labelA42 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA42 = new Chunk("Infrastructure", fontTableHeadingNonBoldData);
		subAParagraph42.add(labelA42);
		subAParagraph42.add(contentA42);
		subAParagraph42.setIndentationLeft(20f);

		PdfPCell subACell42 = new PdfPCell(subAParagraph42);
		subACell42.setBorder(Rectangle.NO_BORDER);
		tableSubA42.addCell(subACell42);
		document.add(tableSubA42);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA43 = new PdfPTable(1);
		tableSubA43.setWidthPercentage(100);
		tableSubA43.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph43 = new Paragraph();
		Chunk labelA43 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA43 = new Chunk("Welfare and Quality of Life", fontTableHeadingNonBoldData);
		subAParagraph43.add(labelA43);
		subAParagraph43.add(contentA43);
		subAParagraph43.setIndentationLeft(20f);

		PdfPCell subACell43 = new PdfPCell(subAParagraph43);
		subACell43.setBorder(Rectangle.NO_BORDER);
		tableSubA43.addCell(subACell43);
		document.add(tableSubA43);
		//==================sub group end===========

		//==================sub group start (e)===========
		PdfPTable tableSubA44 = new PdfPTable(1);
		tableSubA44.setWidthPercentage(100);
		tableSubA44.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph44 = new Paragraph();
		Chunk labelA44 = new Chunk("(e) ", fontTableHeading);
		Chunk contentA44 = new Chunk("Any other Special points.", fontTableHeadingNonBoldData);
		subAParagraph44.add(labelA44);
		subAParagraph44.add(contentA44);
		subAParagraph44.setIndentationLeft(20f);

		PdfPCell subACell44 = new PdfPCell(subAParagraph44);
		subACell44.setBorder(Rectangle.NO_BORDER);
		tableSubA44.addCell(subACell44);
		tableSubA44.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA44);
		//==================sub group end===========
		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String recom = phase2Data.get("improve_following_item");
		addExpandableInfoBox(document, recom, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 11. Recommendations / Innovations End ====================

		// Assuming 'document', 'fontTableHeading', 'fontTableHeadingNonBoldData',
		// and the 'createNumberedCell' method are defined earlier in your code.

		// ==================== 12. Issues Requiring Attention of Higher HQ ====================
		PdfPTable tableIssuesHQ = new PdfPTable(2);
		tableIssuesHQ.setWidthPercentage(100);
		tableIssuesHQ.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableIssuesHQCell1 = createNumberedCell("12.", "Issues Requiring Attention of Higher HQ.", fontTableHeading);
		tableIssuesHQCell1.setPaddingTop(5f);
		tableIssuesHQ.addCell(tableIssuesHQCell1);
		PdfPCell emptyCellIssuesHQ = createNumberedCell("", "", fontTableHeading);
		tableIssuesHQ.addCell(emptyCellIssuesHQ);
		document.add(tableIssuesHQ);
		// document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA45 = new PdfPTable(1);
		tableSubA45.setWidthPercentage(100);
		tableSubA45.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph45 = new Paragraph();
		Chunk labelA45 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA45 = new Chunk("Operational efficiency", fontTableHeadingNonBoldData);
		subAParagraph45.add(labelA45);
		subAParagraph45.add(contentA45);
		subAParagraph45.setIndentationLeft(20f);

		PdfPCell subACell45 = new PdfPCell(subAParagraph45);
		subACell45.setBorder(Rectangle.NO_BORDER);
		tableSubA45.addCell(subACell45);
		document.add(tableSubA45);
		//==================sub group End===========

		//==================sub group start (b)===========
		PdfPTable tableSubA46 = new PdfPTable(1);
		tableSubA46.setWidthPercentage(100);
		tableSubA46.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph46 = new Paragraph();
		Chunk labelA46 = new Chunk("(b) ", fontTableHeading);
		Chunk contentA46 = new Chunk("Training", fontTableHeadingNonBoldData);
		subAParagraph46.add(labelA46);
		subAParagraph46.add(contentA46);
		subAParagraph46.setIndentationLeft(20f);

		PdfPCell subACell46 = new PdfPCell(subAParagraph46);
		subACell46.setBorder(Rectangle.NO_BORDER);
		tableSubA46.addCell(subACell46);
		document.add(tableSubA46);
		//==================sub group end===========

		//==================sub group start (c)===========
		PdfPTable tableSubA47 = new PdfPTable(1);
		tableSubA47.setWidthPercentage(100);
		tableSubA47.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph47 = new Paragraph();
		Chunk labelA47 = new Chunk("(c) ", fontTableHeading);
		Chunk contentA47 = new Chunk("Administration", fontTableHeadingNonBoldData);
		subAParagraph47.add(labelA47);
		subAParagraph47.add(contentA47);
		subAParagraph47.setIndentationLeft(20f);

		PdfPCell subACell47 = new PdfPCell(subAParagraph47);
		subACell47.setBorder(Rectangle.NO_BORDER);
		tableSubA47.addCell(subACell47);
		document.add(tableSubA47);
		//==================sub group end===========

		//==================sub group start (d)===========
		PdfPTable tableSubA48 = new PdfPTable(1);
		tableSubA48.setWidthPercentage(100);
		tableSubA48.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph48 = new Paragraph();
		Chunk labelA48 = new Chunk("(d) ", fontTableHeading);
		Chunk contentA48 = new Chunk("Miscellaneous", fontTableHeadingNonBoldData);
		subAParagraph48.add(labelA48);
		subAParagraph48.add(contentA48);
		subAParagraph48.setIndentationLeft(20f);

		PdfPCell subACell48 = new PdfPCell(subAParagraph48);
		subACell48.setBorder(Rectangle.NO_BORDER);
		tableSubA48.addCell(subACell48);
		document.add(tableSubA48);
		//==================sub group end===========

		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String attention_of_higher_item = phase2Data.get("attention_of_higher_item");
		addExpandableInfoBox(document, attention_of_higher_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 12. Issues Requiring Attention of Higher HQ End ====================

		// ==================== 13. Mitigation of Action ====================
		PdfPTable tableMitigation = new PdfPTable(2);
		tableMitigation.setWidthPercentage(100);
		tableMitigation.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableMitigationCell1 = createNumberedCell("13.", "Mitigation Action Summary", fontTableHeading);
		tableMitigationCell1.setPaddingTop(5f);
		tableMitigation.addCell(tableMitigationCell1);
		PdfPCell emptyCellMitigation = createNumberedCell("", "", fontTableHeading);
		tableMitigation.addCell(emptyCellMitigation);
		document.add(tableMitigation);
		// document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA100 = new PdfPTable(1);
		tableSubA100.setWidthPercentage(100);
		tableSubA100.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph100 = new Paragraph();

		Chunk contentA100 = new Chunk("I have taken following actions to mitigate the observations of the Inspection Team highlighted in Extracts of Inspection Reports as per Annexure to Part I of Appendix 'F'.", fontTableHeadingNonBoldData);

		subAParagraph100.add(contentA100);
		subAParagraph100.setIndentationLeft(20f);

		PdfPCell subACell100 = new PdfPCell(subAParagraph100);
		subACell100.setBorder(Rectangle.NO_BORDER);
		tableSubA100.addCell(subACell100);
		tableSubA100.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(tableSubA100);
		//==================sub group End===========



		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String mitigation_item = phase2Data.get("mitigation_item");
		addExpandableInfoBox(document, mitigation_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 13. Mitigation of Action END ====================

		// ==================== 14. Points For Discussion ====================
		PdfPTable tableDiscussion = new PdfPTable(2);
		tableDiscussion.setWidthPercentage(100);
		tableDiscussion.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell tableDiscussionCell1 = createNumberedCell("14 ", "Points for Discussion", fontTableHeading);
		tableDiscussionCell1.setPaddingTop(5f);
		tableDiscussion.addCell(tableDiscussionCell1);
		PdfPCell emptyCellDiscussion = createNumberedCell("", "", fontTableHeading);
		tableDiscussion.addCell(emptyCellDiscussion);
		document.add(tableDiscussion);
		// document.add(new Paragraph(" ", new Font()));

		//==================sub group start (a)===========
		PdfPTable tableSubA101 = new PdfPTable(1);
		tableSubA101.setWidthPercentage(100);
		tableSubA101.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph101 = new Paragraph();

		Chunk contentA101 = new Chunk("Points for Discussion", fontTableHeadingNonBoldData);

		subAParagraph101.add(contentA101);
		subAParagraph101.setIndentationLeft(20f);

		PdfPCell subACell101 = new PdfPCell(subAParagraph101);
		subACell101.setBorder(Rectangle.NO_BORDER);
		tableSubA101.addCell(subACell101);

		document.add(tableSubA101);
		//==================sub group End===========



		//================== Start: Expandable Box with Static Data ==================
		document.add(new Paragraph(" ", new Font()));
		String points_discussion_item = phase2Data.get("points_discussion_item");
		addExpandableInfoBox(document, points_discussion_item, fontTableHeadingNonBoldData);
		//================== End: Expandable Box with Static Data ====================

		document.add(new Phrase("\n"));
		// ============================= 13. Mitigation of Action END ====================

		//		----------------------------------------------------------------------------------

		PdfPTable tableRemarks = new PdfPTable(4);
		tableRemarks.setWidthPercentage(100);
		tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell remarkscell3;
		remarkscell3 = new PdfPCell();
		remarkscell3.setBorder(Rectangle.NO_BORDER);
		Paragraph p3 = new Paragraph("Digitally Signed by",fontTableHeading);
		remarkscell3.addElement(p3);

		remarkscell3.addElement(new Phrase("\n"));

		p3 = new Paragraph("Pers No:" + (digitalSign.get("personnel_no") != null ? digitalSign.get("personnel_no") : "") + " ", fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Name: " + (digitalSign.get("name") != null ? digitalSign.get("name") : "") + " ", fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Rk: " + (digitalSign.get("rank") != null ? digitalSign.get("rank") : "") + " ", fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Appt: " + (digitalSign.get("appointment") != null ? digitalSign.get("appointment") : "") + " ", fontTableHeading);
		remarkscell3.addElement(p3);

		remarkscell3.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tableRemarks.addCell(remarkscell3);


		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));
		tableRemarks.addCell(new Phrase("\n"));



		Font fontTableHeading12 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 8, 1);
		Paragraph lastPageHeading = new Paragraph("THIS IS A DIGITALLY SIGNED DOCUMENT. NO SEPRATE INK SIGN COPY REQD.", fontTableHeading12);
		lastPageHeading.setAlignment(Element.ALIGN_CENTER);


		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(tableRemarks);
		cell1.addElement(lastPageHeading);
		table.addCell(cell1);
		table.setTableEvent(new ImageBackgroundEvent(request,username));



		PdfPTable table11 = new PdfPTable(1);
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell11;
		cell11 = new PdfPCell();
		cell11.setBorder(Rectangle.NO_BORDER);
		cell11.addElement(table);
		table11.addCell(cell11);
		table11.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table11);

		super.buildPdfMetadata(model, document, request);





	}

	private PdfPCell createNumberedCell(String number, String text, Font font) {
		// Create the number chunk
		Chunk numberChunk = new Chunk(number, font);

		// Create the text chunk
		Chunk textChunk = new Chunk(text, font);
		textChunk.setUnderline(0.1f, -2f); // Set underline for the text chunk

		// Create a paragraph and add the chunks
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		// Create a PdfPCell and set its properties
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(-5f); // Adjust padding as needed

		return cell;
	}


	public class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;
		private HttpServletRequest request;
		private String username; // Assume username is passed or set

		public ImageBackgroundEvent(HttpServletRequest request, String username) {
			this.request = request;
			this.username = username;
		}

		@Override
		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
				PdfContentByte[] canvases) {
			// Get IP address
			String ip = "";
			if (request != null) {
				ip = request.getHeader("X-FORWARDED-FOR");
				if (ip == null || ip.isEmpty()) {
					ip = request.getRemoteAddr();
				}
			}

			// Create watermark text
			Date now = new Date();
			String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(now);
			String watermark = "Generated by " + username + " on " + dateString + " with IP " + ip;

			// Create watermark image
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(new Color(211, 211, 211, 100)); // Semi-transparent light gray
			//  graphics.setFont(new Font("Arial", Font.NORMAL, 12));
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
			graphics.drawString(watermark, 10, 20);
			graphics.dispose();

			try {
				image = Image.getInstance(bufferedImage, null);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			try {
				// Use background canvas to ensure watermark is behind table
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
				float tableHeight = 0;
				for (float height : heights) {
					tableHeight += height; // Sum all row heights
				}

				// Rotate watermark for diagonal effect
				image.setRotationDegrees(0);
				float tableWidth = table.getTotalWidth();
				float pageWidth = table.getWidthPercentage() == 100 ? 595 : tableWidth; // Approximate A4 width if 100%
				float startY = tableHeight ; // Start from bottom of table
				float endY = 0; // Bottom of table area
				float x = 50; // Left margin

				// Repeat watermark diagonally across table height
				for (float y = endY; y < startY; y += 40) {
					image.setAbsolutePosition(x, y);
					cb.addImage(image);
				}
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void addExpandableInfoBox(Document document, String textContent, Font textFont) throws DocumentException {

		// --- Basic Check: Don't add empty boxes ---
		if (textContent == null || textContent.trim().isEmpty()) {
			return;
		}

		// --- Create the table that acts as the box container ---
		PdfPTable staticBoxTable = new PdfPTable(1);
		staticBoxTable.setWidthPercentage(100f);
		staticBoxTable.setSpacingBefore(5f);
		staticBoxTable.setSpacingAfter(5f);


		Paragraph staticParagraph = new Paragraph(textContent, textFont);
		staticParagraph.setAlignment(Element.ALIGN_JUSTIFIED);


		PdfPCell staticBoxCell = new PdfPCell(staticParagraph);
		staticBoxCell.setBorder(Rectangle.BOX);
		staticBoxCell.setBorderWidth(0.5f);
		staticBoxCell.setPadding(8f);

		staticBoxCell.setUseAscender(true);
		staticBoxCell.setUseDescender(true);

		// --- Add the cell to the table ---
		staticBoxTable.addCell(staticBoxCell);

		// --- Add the table (our box) to the document ---
		document.add(staticBoxTable);
	}

	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		private int totalPages = 0;
		private Phrase footerPhrase; // Store the phrase for the footer

		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(30, 15);
			} catch (Exception e) {
				e.printStackTrace();
			}
			totalPages = 0;
			// Initialize the footer phrase here.  The total pages part will be updated later.
			footerPhrase = new Phrase("Page 1 of 1", fontTableHeading1); // Default starting value
			writer.setPageEvent(this);
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			totalPages = 0;
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {

			try {
				PdfContentByte cb = writer.getDirectContent(); // Get direct content

				// Update the footer phrase on each page
				footerPhrase = new Phrase("Page No.:" + writer.getPageNumber(), fontTableHeading1);

				// Add the footer to the page
				ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footerPhrase,
						(document.right() - document.left()) / 2 + document.leftMargin(),
						document.bottom() - 20, // Adjust vertical position as needed
						0);

			} catch (Exception de) {
				throw new ExceptionConverter(de);
			}
		}

		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {
			// Now, `totalPages` should have the correct value.
			try {
				// Update the footer phrase one last time, now that we know the final total
				footerPhrase = new Phrase("Page " + totalPages + " of " + totalPages, fontTableHeading1);

			} catch (Exception ex) {
				// Handle exceptions as needed
				ex.printStackTrace();
			}
		}
	}



}