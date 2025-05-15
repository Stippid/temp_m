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

public class PDF_ANUAL_REPORT_PART_4 extends AbstractPdfView {
	String foot = "";
	Map<String, String> digitalSign;
	Map<String, String> ufi;
	Map<String, String> udi;
	Map<String, String> cii;

	String username = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_ANUAL_REPORT_PART_4(String username, Map<String, String> digitalSign, Map<String, String> ufi, Map<String, String> udi, Map<String, String> cii) {

		this.digitalSign=digitalSign;
		this.ufi= ufi;
		this.udi= udi;
		this.cii= cii;
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




		// ==================== 1. Strengths/ Challenges for the Unit/ Formation HQ/ Establishment ====================
		PdfPTable tableleftFM4 = new PdfPTable(2);
		tableleftFM4.setWidthPercentage(100);
		tableleftFM4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell opPrepTitleCell = createNumberedCell("1. " , "Strengths/ Challenges for the Unit/ Formation HQ/ Establishment (with respect to the under mentioned)", fontTableHeading);
		opPrepTitleCell.setPaddingTop(5f);
		tableleftFM4.addCell(opPrepTitleCell);
		PdfPCell emptyCell = createNumberedCell("", "", fontTableHeading);
		tableleftFM4.addCell(emptyCell);
		document.add(tableleftFM4);
		document.add(new Paragraph(" ", new Font()));

		// Add subsection (a) - directly attached format
		PdfPTable tableSubA = new PdfPTable(1);
		tableSubA.setWidthPercentage(100);
		tableSubA.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph = new Paragraph();
		Chunk labelA = new Chunk("(a) ", fontTableHeading);
		Chunk contentA = new Chunk("Training and operational preparation for the assigned role/task:", fontTableHeadingNonBoldData);
		subAParagraph.add(labelA);
		subAParagraph.add(contentA);
		subAParagraph.setIndentationLeft(20f);

		PdfPCell subACell = new PdfPCell(subAParagraph);
		subACell.setBorder(Rectangle.NO_BORDER);
		tableSubA.addCell(subACell);
		document.add(tableSubA);

		//================== Start: Expandable Box with Static Data ==================
		String training_and_operational = ufi.get("training_and_operational");
		addExpandableInfoBox(document, training_and_operational, fontTableHeadingNonBoldData);
		document.add(new Paragraph(" ", new Font()));
		//================== End: Expandable Box with Static Data ====================

		// Add subsection (b) - directly attached format
		PdfPTable tableSubB = new PdfPTable(1);
		tableSubB.setWidthPercentage(100);
		tableSubB.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subBParagraph = new Paragraph();
		Chunk labelB = new Chunk("(b) ", fontTableHeading);
		Chunk contentB = new Chunk("Equipment profile as necessitated to undertake the assigned role/task:", fontTableHeadingNonBoldData);
		subBParagraph.add(labelB);
		subBParagraph.add(contentB);
		subBParagraph.setIndentationLeft(20f);

		PdfPCell subBCell = new PdfPCell(subBParagraph);
		subBCell.setBorder(Rectangle.NO_BORDER);
		tableSubB.addCell(subBCell);
		document.add(tableSubB);

		//================== Start: Expandable Box with Static Data ==================
		String equipment_profile = ufi.get("equipment_profile");
		addExpandableInfoBox(document, equipment_profile, fontTableHeadingNonBoldData);
		document.add(new Paragraph(" ", new Font()));
		//================== End: Expandable Box with Static Data ====================


		// Add subsection (c) - directly attached format
		PdfPTable tableSubC = new PdfPTable(1);
		tableSubC.setWidthPercentage(100);
		tableSubC.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subCParagraph = new Paragraph();
		Chunk labelC = new Chunk("(c) ", fontTableHeading);
		Chunk contentC = new Chunk("Administration of the unit including troops welfare measures:", fontTableHeadingNonBoldData);
		subCParagraph.add(labelC);
		subCParagraph.add(contentC);
		subCParagraph.setIndentationLeft(20f);

		PdfPCell subCCell = new PdfPCell(subCParagraph);
		subCCell.setBorder(Rectangle.NO_BORDER);
		tableSubC.addCell(subCCell);
		document.add(tableSubC);

		//================== Start: Expandable Box with Static Data ==================
		String administration = ufi.get("administration");
		addExpandableInfoBox(document, administration, fontTableHeadingNonBoldData);
		document.add(new Paragraph(" ", new Font()));
		//================== End: Expandable Box with Static Data ====================

		//======================= End Strengths/ Challenges for the Unit/ Formation HQ/ Establishment ==================================

		// ==================== 2. Is the Unit ====================
		// Add subsection (a) - directly attached format
		PdfPTable tableA = new PdfPTable(1);
		tableA.setWidthPercentage(100);
		tableA.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph aParagraph = new Paragraph();
		Chunk labelA2 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA2 = new Chunk("Fully fit for war in its operational role/designated role? ", fontTableHeadingNonBoldData);
		aParagraph.add(labelA2);
		aParagraph.add(contentA2);
		aParagraph.setIndentationLeft(20f);

		PdfPCell aCell = new PdfPCell(aParagraph);
		aCell.setBorder(Rectangle.NO_BORDER);
		tableA.addCell(aCell);
		document.add(tableA);

		String fully_fit_for_war = udi.get("fully_fit_for_war");

		PdfPTable yesNoRadioTable = new PdfPTable(2);
		yesNoRadioTable.setWidthPercentage(100);
		yesNoRadioTable.setSpacingBefore(5f);
		yesNoRadioTable.setSpacingAfter(5f);

		addRadioButton(yesNoRadioTable, "Yes", "Yes", fully_fit_for_war, fontTableHeadingNonBoldData);
		addRadioButton(yesNoRadioTable, "No", "No", fully_fit_for_war, fontTableHeadingNonBoldData);

		document.add(yesNoRadioTable);
		document.add(new Paragraph(" ", new com.lowagie.text.Font()));

		// Add subsection (b) - directly attached format
		PdfPTable tableSubB2 = new PdfPTable(1);
		tableSubB2.setWidthPercentage(100);
		tableSubB2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subBParagraph2 = new Paragraph();
		Chunk labelB2 = new Chunk("(b) ", fontTableHeading);
		Chunk contentB2 = new Chunk("If partially fit or unfit for war/designated role, is it due to: ", fontTableHeadingNonBoldData);
		subBParagraph2.add(labelB2);
		subBParagraph2.add(contentB2);
		subBParagraph2.setIndentationLeft(20f);

		PdfPCell subBCell2 = new PdfPCell(subBParagraph2);
		subBCell2.setBorder(Rectangle.NO_BORDER);
		tableSubB2.addCell(subBCell2);
		document.add(tableSubB2);

		String fit_or_unfit = udi.get("fit_or_unfit");

		PdfPTable reasonRadioTable = new PdfPTable(3);
		reasonRadioTable.setWidthPercentage(100);
		reasonRadioTable.setSpacingBefore(5f);
		reasonRadioTable.setSpacingAfter(5f);

		addRadioButton(reasonRadioTable, "Lack of Equipment", "Lack of Equipment", fit_or_unfit, fontTableHeadingNonBoldData);
		addRadioButton(reasonRadioTable, "Lack of Training", "Lack of Training", fit_or_unfit, fontTableHeadingNonBoldData);
		addRadioButton(reasonRadioTable, "Both", "Both", fit_or_unfit, fontTableHeadingNonBoldData);

		document.add(reasonRadioTable);
		document.add(new Paragraph(" ", new com.lowagie.text.Font()));


		// Add subsection (c) - directly attached format
		PdfPTable tableSubC2 = new PdfPTable(1);
		tableSubC2.setWidthPercentage(100);
		tableSubC2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subCParagraph2 = new Paragraph();
		Chunk labelC2 = new Chunk("(c) ", fontTableHeading);
		Chunk contentC2 = new Chunk("Administration of the unit including troops welfare measures:", fontTableHeadingNonBoldData);
		subCParagraph2.add(labelC2);
		subCParagraph2.add(contentC2);
		subCParagraph2.setIndentationLeft(20f);

		PdfPCell subCCell2 = new PdfPCell(subCParagraph2);
		subCCell2.setBorder(Rectangle.NO_BORDER);
		tableSubC2.addCell(subCCell2);
		document.add(tableSubC2);

		//================== Start: Expandable Box with Static Data ==================
		String recommendations = udi.get("recommendations");
		addExpandableInfoBox(document, recommendations, fontTableHeadingNonBoldData);
		document.add(new Paragraph(" ", new Font()));
		//================== End: Expandable Box with Static Data ====================

		//======================= End Is the Unit ==================================

		// ==================== 3. Critical Issues ====================
		PdfPTable tableleftCI4 = new PdfPTable(2);
		tableleftCI4.setWidthPercentage(100);
		tableleftCI4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell opPrepTitleCell3 = createNumberedCell("3. " , "Critical Issues", fontTableHeading);
		opPrepTitleCell3.setPaddingTop(5f);
		tableleftCI4.addCell(opPrepTitleCell3);
		PdfPCell emptyCell3 = createNumberedCell("", "", fontTableHeading);
		tableleftCI4.addCell(emptyCell3);
		document.add(tableleftCI4);
		document.add(new Paragraph(" ", new Font()));

		// Add subsection (a) - directly attached format
		PdfPTable tableSubA3 = new PdfPTable(1);
		tableSubA3.setWidthPercentage(100);
		tableSubA3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph subAParagraph3 = new Paragraph();
		Chunk labelA3 = new Chunk("(a) ", fontTableHeading);
		Chunk contentA3 = new Chunk("Fully fit for war in its operational role/designated role?", fontTableHeadingNonBoldData);
		subAParagraph3.add(labelA3);
		subAParagraph3.add(contentA3);
		subAParagraph3.setIndentationLeft(20f);

		PdfPCell subACell3 = new PdfPCell(subAParagraph3);
		subACell3.setBorder(Rectangle.NO_BORDER);
		tableSubA3.addCell(subACell3);
		document.add(tableSubA3);

		//================== Start: Expandable Box with Static Data ==================
		String critical_issues = cii.get("critical_issues");
		addExpandableInfoBox(document, critical_issues, fontTableHeadingNonBoldData);
		document.add(new Paragraph(" ", new Font()));
		//================== End: Expandable Box with Static Data ====================

		//======================= End Critical Issues ==================================

		document.add(new Phrase("\n"));

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

		p3 = new Paragraph("Pers No:"+digitalSign.get("personnel_no")+" " ,fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Name: "+digitalSign.get("name")+" ",fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Rk: "+digitalSign.get("rank")+" ",fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Appt: "+digitalSign.get("appointment")+" ",fontTableHeading);
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




		PdfPTable table11 = new PdfPTable(1);
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell11;
		cell11 = new PdfPCell();
		cell11.setBorder(Rectangle.NO_BORDER);
		cell11.addElement(table);
		table11.addCell(cell11);

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

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {
			this.request = request;
		}

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
			graphics.setColor(Color.BLUE);
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
					first = 750;
				}
				if (tableWidth == 770) {
					first = 500;
				}

				int last = first - (int) table.getRowHeight(0);
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

	// Helper method to create radio buttons
	private void addRadioButton(PdfPTable table, String label, String value, String selectedValue, com.lowagie.text.Font font) {
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		Paragraph p = new Paragraph();

		//		Character 'l' (lowercase L, code 108) for a filled circle
		//		Character 'm' (lowercase M, code 109) for an empty circle
		char radioSymbol = value.equals(selectedValue) ? 'l' : 'm';
		Chunk radioButton = new Chunk(radioSymbol, new com.lowagie.text.Font(com.lowagie.text.Font.ZAPFDINGBATS, 12));

		// Add the radio button symbol and label
		p.add(radioButton);
		p.add(new Chunk(" " + label, font));

		cell.addElement(p);
		table.addCell(cell);
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
