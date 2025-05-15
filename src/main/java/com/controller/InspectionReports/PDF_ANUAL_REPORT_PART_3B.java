package com.controller.InspectionReports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.Locale;
import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.InspectionReports.PDF_ANUAL_REPORT_PART_2.PageNumeration;
import com.controller.InspectionReports.PDF_ANUAL_REPORT_PART_3A.ImageBackgroundEvent;
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

public class PDF_ANUAL_REPORT_PART_3B extends AbstractPdfView {

	String Type = "";
	String foot = "";
	List<String> TH;
	ArrayList<ArrayList<String>> list1;
	String username = "";
	String wksp_unit_name;
	String wo_dt = "";
	String dt_eqpt_reqd_fwd_wksp = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_ANUAL_REPORT_PART_3B(String Type, List<String> TH, String username, ArrayList<ArrayList<String>> list1) {
		this.Type = Type;
		this.TH = TH;
		this.list1 = list1;
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
		// Add page count below "RESTRICTED"
//		Chunk pageCountChunk = new Chunk("Page " + document.getPageNumber(), fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk); // Add page count to the header

		// Add page count placeholder in the header
//		Chunk pageCountChunk = new Chunk("Page {pageNumber}", fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk); // Add page count placeholder to the header

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

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
		
		@SuppressWarnings("unchecked")
		 List<Map<String, Object>> getOverall_Str_and_Challengesdataurl =  (List<Map<String, Object>>) model.get("Overall_Str_and_Challengesdataurl");

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

		Chunk underline69 = new Chunk("PART - III (B)", fontTableHeading);
		Paragraph headingParagraph1 = new Paragraph();
		headingParagraph1.add(underline69);
		headingParagraph1.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph1);

		Chunk underline68 = new Chunk("REPORT OF INSPECTING OFFICER", fontTableHeading);
		Paragraph headingParagraph = new Paragraph();
		headingParagraph.add(underline68);
		headingParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph);
		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);
		
		
		
		PdfPTable tableleftFM399 = new PdfPTable(1);
		tableleftFM399.setWidthPercentage(100);
		tableleftFM399.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell59 = createNumberedCell("    ", "Overall Str and Challenges", fontTableHeading);
		establishmentCell59.setPaddingTop(20f);
		tableleftFM399.addCell(establishmentCell59);
		document.add(tableleftFM399);

		PdfPTable tableleftFM36 = new PdfPTable(1);
		tableleftFM36.setWidthPercentage(100);
		tableleftFM36.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell54 = createNumberedCellNounderline("1.    ", "Strengths :      " + getOverall_Str_and_Challengesdataurl.get(0).get("strengths").toString(), fontTableHeading);
		
		establishmentCell54.setPaddingTop(20f);
		tableleftFM36.addCell(establishmentCell54);
		document.add(tableleftFM36);

		PdfPTable tableleftFM37 = new PdfPTable(1);
		tableleftFM37.setWidthPercentage(100);
		tableleftFM37.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell55 = createNumberedCellNounderline("2.    ", "Challenges :     " + getOverall_Str_and_Challengesdataurl.get(0).get("challenges").toString(), fontTableHeading);
		establishmentCell55.setPaddingTop(20f);
		tableleftFM37.addCell(establishmentCell55);
		document.add(tableleftFM37);
		
		PdfPTable tableleftFM632 = new PdfPTable(1);
		tableleftFM632.setWidthPercentage(100);
		tableleftFM632.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell555 = createNumberedCellNounderline("3.    ", "Advisories :     " + getOverall_Str_and_Challengesdataurl.get(0).get("advisories").toString(), fontTableHeading);
		establishmentCell555.setPaddingTop(20f);
		tableleftFM632.addCell(establishmentCell555);
		document.add(tableleftFM632);

		PdfPTable tableleftFM39 = new PdfPTable(1);
		tableleftFM39.setWidthPercentage(100);
		tableleftFM39.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell57 = createNoBordertext1("4.    ",
				"Certificate. I, IC _______Rank _______Name _________, (Appointment)___________certify that:-",
				fontTableData);
		establishmentCell57.setPaddingTop(20f);
		establishmentCell57.setPaddingBottom(20f);
		tableleftFM39.addCell(establishmentCell57);
		document.add(tableleftFM39);

		PdfPTable table38 = new PdfPTable(2);
		table38.setWidthPercentage(100);
		float[] columnWidths38 = { 9f, 1f }; // Adjust widths as needed
		table38.setWidths(columnWidths38);

		// Header Row
		table38.addCell(textCell1(
				"(a)	  The gradation has been awarded after carrying out a thorough check of all aspects related to the issue.",
				fontTableData));

		table38.addCell(textCell1("", fontTableData));

		table38.addCell(textCell1(
				"(b)	  I have inspected the issues that were reported during the Book Inspection.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(c)   I have inspected both Regimental and Public Accounts and confirm that all expenditure being incurred is as per Defence Procurement Manual 2011.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(d)   I have inspected the issues indicated as ‘Needs Improvement’ in detail and have issued suitable advisories.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(e)   I have instituted periodic checks to monitor all aspects that ‘Need Improvement’.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(f)   I shall personally monitor the progress of the shortcomings observed, till their eradication.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table38);

		PdfPTable table39 = new PdfPTable(2);
		table39.setWidthPercentage(100);
		float[] columnWidths39 = { 6f, 4f }; // Adjust widths as needed
		table39.setWidths(columnWidths39);

		table39.addCell(textCell1("Station :", fontTableData));
		table39.addCell(textCell1("", fontTableData));

		table39.addCell(textCell1("Dated :", fontTableData));
		table39.addCell(textCell1("(Signature of Inspecting Officer)", fontTableData));
		table39.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table39);

		super.buildPdfMetadata(model, document, request);
	}

	private PdfPCell textCell1(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setPadding(10f); 

		return cell;
	}

	private static PdfPCell createNoBordertext1(String number, String text, Font font) {

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font regularFont = FontFactory.getFont(FontFactory.TIMES, 12);
		Chunk numberChunk = new Chunk(number, fontTableHeading); 
		Chunk textChunk = new Chunk(text, regularFont);

		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setPaddingLeft(-5f);

		return cell;
	}

	private PdfPCell createNumberedCell(String number, String text, Font font) {
		Chunk numberChunk = new Chunk(number, font);
		Chunk textChunk = new Chunk(text, font);
		textChunk.setUnderline(0.1f, -2f); 
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setPaddingLeft(-5f); 

		return cell;
	}
	
	private PdfPCell createNumberedCellNounderline(String number, String text, Font font) {
		Chunk numberChunk = new Chunk(number, font);
		Chunk textChunk = new Chunk(text, font);
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setPaddingLeft(-5f);

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