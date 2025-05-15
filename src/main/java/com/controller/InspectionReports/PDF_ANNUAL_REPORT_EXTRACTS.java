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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;

public class PDF_ANNUAL_REPORT_EXTRACTS extends AbstractPdfView{

	
	
	String Type = "";
	String foot = "";
	String username = "";
	String wksp_unit_name;
	String wo_dt = "";
	String dt_eqpt_reqd_fwd_wksp = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_ANNUAL_REPORT_EXTRACTS( String username) {

		this.username = username;
	}

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

		int currentPageNumber = document.getPageNumber();
//		Chunk pageCountChunk = new Chunk("Page " + currentPageNumber, fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk);

		p.add("\n");
		p.add("\n");

//		p.add(underline3);
	
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
		Chunk p90 = new Chunk("RESTRICTED", fontTableHeadingMainHead);
		p90.setUnderline(0.1f, -2f);
		p1.add(p90);

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
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

	
	
		Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);

		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);

		Font fontTableData = new Font(Font.HELVETICA, 12);

		Paragraph p = new Paragraph();

		Paragraph pl;

		Paragraph plv;
		Paragraph pr;

		Paragraph prv;

		PdfPTable maintenanceTable = new PdfPTable(1);
		maintenanceTable.setWidths(new float[] { 1 });
		maintenanceTable.setWidthPercentage(100);

		// Set the border for the table to NO_BORDER
		maintenanceTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		PdfPTable maintenanceTable1 = new PdfPTable(1);
		maintenanceTable1.setWidths(new float[] { 1 });
		maintenanceTable1.setWidthPercentage(100);

		// Set the border for the table to NO_BORDER
		maintenanceTable1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		Chunk underline = new Chunk("Annexure", fontTableHeading);
		underline.setUnderline(0.1f, -2f);
		// Header Cells
		Paragraph paragraph2 = new Paragraph(underline);
		PdfPCell maintenanceDateHeaderCell2 = new PdfPCell(paragraph2);
//		maintenanceDateHeaderCell.setMinimumHeight(30);
		maintenanceDateHeaderCell2.setPaddingRight(129f);
		maintenanceDateHeaderCell2.setVerticalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell2.setBorder(PdfPCell.NO_BORDER); // No border for this cell

		maintenanceTable1.addCell(maintenanceDateHeaderCell2);

		PdfPCell maintenanceSubSystemHeaderCell1 = new PdfPCell(
				new Paragraph("(Refer to Paragraph 39 of Part I \nof Annual Inspection Report)"));
		maintenanceSubSystemHeaderCell1.setMinimumHeight(30);
		maintenanceSubSystemHeaderCell1.setPaddingLeft(343f);
		maintenanceSubSystemHeaderCell1.setVerticalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell1.setBorder(PdfPCell.NO_BORDER); // No border for this cell
		maintenanceTable1.addCell(maintenanceSubSystemHeaderCell1);
		maintenanceTable1.setHorizontalAlignment(Element.ALIGN_RIGHT);

		document.add(maintenanceTable1);

		Chunk underline_head = new Chunk("ANNUAL INSPECTION REPORT EXTRACTS WEF _____________ TO _____________",
				fontTableHeading);
		Paragraph headingParagraph2 = new Paragraph();
		headingParagraph2.add(underline_head);
		headingParagraph2.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph2);

		document.add(new Phrase("\n"));

		PdfPTable table40 = new PdfPTable(8);
		table40.setWidthPercentage(110);
		float[] columnWidths40 = { 1f, 5f, 3f, 3f, 3f, 3f, 3f, 2f }; // Adjust widths as needed
		table40.setWidths(columnWidths40);

		// Header Row
		table40.addCell(createHeaderCell("Ser No", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table40.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));
		PdfPCell cell = new PdfPCell(new Phrase("trg", fontTableHeading));
		cell.setRowspan(1);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(10f); // Set padding
		table40.addCell(cell);

		table40.addCell(createHeaderCell("1.", fontTableHeading, 1, 1));
		table40.addCell(textCell("Individual Training\n" + "\n" + "(a) Physical Training\n" + "(b) Weapon Training\n"
				+ "(c) Weapon Training Results\n" + "(d) Night Training\n" + "(e) Specialist Training\n"
				+ " (f) YOs Training\n" + "(g) Officers Training\n" + "(h) Training of JCOs and NCOs\n"
				+ " (j) Training for ACC Commission / SCO.", fontTableData));

		table40.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 1));

		table40.addCell(createHeaderCell("2.", fontTableHeading, 1, 1));
		table40.addCell(textCell("Collective Training\n" + "(a) Training Exercises / Training Camps\n"
				+ "(b) Field Firing\n" + "(c) Mobilisation", fontTableData));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 1));

		table40.addCell(createHeaderCell("3.", fontTableHeading, 1, 1));
		table40.addCell(textCell("Use of Training Infrastructure including Training Aids.", fontTableData));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table40.addCell(createHeaderCell("", fontTableHeading, 1, 1));

		document.add(table40);

//		------------------------------------pages 18 --------------------------------------
		document.newPage();

		document.add(new Phrase("\n"));

		PdfPTable table41 = new PdfPTable(8);
		table41.setWidthPercentage(110);
		float[] columnWidths41 = { 1f, 5f, 3f, 3f, 3f, 3f, 3f, 2f }; // Adjust widths as needed
		table41.setWidths(columnWidths41);

		// Header Row
		table41.addCell(createHeaderCell("Ser No", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));
		PdfPCell cell1 = new PdfPCell(new Phrase("Administration", fontTableHeading));
		cell1.setRowspan(1);
		cell1.setColspan(8);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell1.setPadding(10f); // Set padding
		table41.addCell(cell1);

		table41.addCell(createHeaderCell("4.", fontTableHeading, 1, 1));
		table41.addCell(textCell(
				"Personnel Management.\n\n" + "(a) Discipline\n" + "(b) Health of Troops\n"
						+ "(c) Personal Documentation\n" + "(d) Interior Economy\n" + " (e) Morale and Motivation.",
				fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("5.", fontTableHeading, 1, 1));
		table41.addCell(
				textCell("Material Management.\n\n" + "(a) Vehicles\n" + "(b) Equipment\n" + "(c) Maintenance of Arms\n"
						+ "(d) Maintenance of Ammunition\n" + "(e) Maintenance of Ordnance Stores\n"
						+ "(f) Management of Public Funds and Financial Grants\n" + "", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		PdfPCell cell2 = new PdfPCell(new Phrase("Misc Aspects", fontTableHeading));
		cell2.setRowspan(1);
		cell2.setColspan(8);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell2.setPadding(10f); // Set padding
		table41.addCell(cell2);
		table41.addCell(createHeaderCell("6.", fontTableHeading, 1, 1));
		table41.addCell(textCell( "Security", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("7.", fontTableHeading, 1, 1));
		table41.addCell(textCell( "Adherence to Indian Army Core Values", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("8.", fontTableHeading, 1, 1));
		table41.addCell(textCell( "HRD", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("9.", fontTableHeading, 1, 1));
		table41.addCell(textCell( "Audit Objections", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table41.addCell(createHeaderCell("10.", fontTableHeading, 1, 1));
		table41.addCell(textCell( "State of Complaints", fontTableData));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table41.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		document.add(table41);
		
//		-----------------------------------page 19-----------------------------------
		document.newPage();
		

		PdfPTable table42 = new PdfPTable(8);
		table42.setWidthPercentage(110);
		float[] columnWidths42 = { 1f, 5f, 3f, 3f, 3f, 3f, 3f, 2f }; // Adjust widths as needed
		table42.setWidths(columnWidths42);

		// Header Row
		table42.addCell(createHeaderCell("Ser No", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Training Year\n________", fontTableHeading, 1, 1));
		table42.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));
		
		table42.addCell(createHeaderCell("11.", fontTableHeading, 1, 1));
		table42.addCell(textCell("Additional Information. (For Station, Sub Area and Area HQ only)\n"
				+ "\n"
				+ "(a) State of all Public Funds\n"
				+ "\n"
				+ "(b) State of discipline in the formation including state of pending GCMs / DCMs, as applicable\n"
				+ "\n"
				+ "(c) State of court cases in the formation\n"
				+ "\n"
				+ "(d) State of complaints and petitions,\n"
				+ "\n"
				+ "(e) State of works in the formation\n"
				+ "\n"
				+ "(f) Any peculiar aspects observed", fontTableData));
		table42.addCell(createHeaderCell("", fontTableHeading, 1, 5));
		table42.addCell(createHeaderCell("", fontTableHeading, 1, 1));
		document.add(table42);
		
		PdfPTable tableleftFM40 = new PdfPTable(1);
		tableleftFM40.setWidthPercentage(100);
		tableleftFM40.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell59 = createNumberedCell("12.    ",
				"Comments of Inspecting Officer.",
				fontTableHeading);
		establishmentCell59.setPaddingTop(20f);
		
		tableleftFM40.addCell(establishmentCell59);
		PdfPCell establishmentCell60 = createNumberedCell("Copy of Overall Strength and Weaknesses and Fitness for War / Designated Role (Part IV) as observed by Inspecting Officer for the last five years wef_____ to _______ is attached (Part IV of last five years Annual Inspection Reports to be attached).", "",
				fontTableData);
		tableleftFM40.addCell(establishmentCell60);
		document.add(tableleftFM40);
		
		PdfPTable tableleftFM41 = new PdfPTable(1);
		tableleftFM41.setWidthPercentage(100);
		tableleftFM41.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell62 = createNumberedCell("13    ",
				"Comments of Reviewing Officer.",
				fontTableHeading);
		establishmentCell62.setPaddingTop(20f);
		
		tableleftFM41.addCell(establishmentCell62);
		PdfPCell establishmentCell63 = createNumberedCell("Copy of Remarks of Officer Reviewing the Report for the last five years wef ______ to ______ is attached (Remarks of the Officer Reviewing.", "",
				fontTableData);
		tableleftFM41.addCell(establishmentCell63);
		establishmentCell63.setPaddingBottom(10f);
		document.add(tableleftFM41);
		
		
		document.close();
//		----------------------------------------------------------------------------------

		super.buildPdfMetadata(model, document, request);
	
	
	
	
	
	
	}
	
	
	
	
	private static PdfPCell createNoBordertext(String number, String text, Font font) {
		Chunk numberChunk = new Chunk(number, font);
		Chunk textChunk = new Chunk(text, font);
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(45f); // Adjust padding as needed
		cell.setPaddingBottom(10f); // Adjust padding as needed
		return cell;
	}

	private static PdfPCell createNoBordertext1(String number, String text, Font font) {
		// Create a bold font for the number

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font regularFont = FontFactory.getFont(FontFactory.TIMES, 12);
		Chunk numberChunk = new Chunk(number, fontTableHeading); // Use bold font for the number
		Chunk textChunk = new Chunk(text, regularFont);

		// Create a paragraph and add the chunks
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		// Create a cell and set its properties
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(-5f); // Adjust padding as needed

		return cell;
	}

	private PdfPCell createCenteredCell(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private PdfPCell textCell(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private PdfPCell textCell1(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(10f); // Set padding

		return cell;
	}
	
	private PdfPCell textCell2(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private static PdfPCell createHeaderCell(String text, Font font, int rowspan, int colspan) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setRowspan(rowspan);
		cell.setColspan(colspan); // Set the colspan
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
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
			graphics.setColor(Color.lightGray);
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
}
