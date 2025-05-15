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
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class Unit_Dashboard_eqpt_held_report extends AbstractPdfView {

	List<String> TH;
	List<String> TH2;

	String foot = "";
	String username = "";
	final static String USER_PASSWORD = "user";
	ArrayList<ArrayList<String>> pdfprint;
	ArrayList<ArrayList<String>> pdf_summary;

	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	public Unit_Dashboard_eqpt_held_report(List<String> TH, List<String> TH2, String foot,
			String username, ArrayList<ArrayList<String>> pdfprint, ArrayList<ArrayList<String>> pdf_summary) {
		this.TH = TH;
		this.TH2 = TH2;
		this.foot = foot;
		this.username = username;
		this.pdfprint = pdfprint;
		this.pdf_summary = pdf_summary;

	}

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		// Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial",
		// BaseFont.IDENTITY_H, false, 12, 0);
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
		logo.scaleAbsoluteHeight(60);
		logo.scaleAbsoluteWidth(60);
		logo.scalePercent(30);
		Chunk chunk = new Chunk(logo, -5, 4);

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
		logo2.scaleAbsoluteHeight(100);
		logo2.scaleAbsoluteWidth(100);
		logo2.scalePercent(40);
		Chunk chunk2 = new Chunk(logo2, -5, 4);
		Chunk underline = new Chunk("RESTRICTED", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("Wpn & Eqpts Held", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		// Chunk underline4 = new Chunk(MainHead, fontTableHeadingSubMainHead);
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		// p.add(underline4);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.setWidthPercentage(120);
		p2.add(table3);

		/*
		 * Phrase p = new Phrase(); p.add("RESTRICTED"); Font fontTable=
		 * FontFactory.getFont(FontFactory.TIMES_BOLD, 12); p.setFont(fontTable);
		 * p.add(Heading);
		 */

		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		Phrase p1 = new Phrase();

		Chunk p89 = new Chunk(foot, fontTableHeadingMainHead);
		p89.setUnderline(0.1f, -2f);
		p1.add(p89);
		p1.add("\n");
		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);
		p90.setUnderline(0.1f, -2f);
		p1.add(p90);

		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);

		/// document.setPageCount(1);
		document.setPageSize(PageSize.A4.rotate()); // set document landscape

		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph p44 = new Paragraph();
		Paragraph p60 = new Paragraph();

		Paragraph pl;

		Paragraph plv;
		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);
		Font fontTableValue2 = FontFactory.getFont(FontFactory.TIMES, 9);

		//////////////////////////// seal ////////
		PdfPTable tableseal = new PdfPTable(1);
		tableseal.getDefaultCell().setBorder(Rectangle.BOX);
		PdfPTable tableseali = new PdfPTable(3);
		tableseali.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tableseali.setWidthPercentage(100);//

		PdfPTable tableleft = new PdfPTable(2);

		tableleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable tablecenter = new PdfPTable(3);

		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.setWidthPercentage(100);

		tablecenter.addCell(tableleft);
		tablecenter.addCell(tableseal);

		Phrase p1 = new Phrase();

		Chunk pSERVING = new Chunk("wpn & Eqpta", fontTableHeading);
		pSERVING.setUnderline(0.1f, -2f);
		p1.add(pSERVING);
		//		Phrase p1 = new Phrase("SERVING", fontTableHeading);

		//		p1.setUnderline(0.1f, -2f);
		// p1.add(p2);

		PdfPTable table1 = new PdfPTable(9);
		table1.setWidths(new int[] { 2, 2, 2, 2, 2, 2,2,2, 2});
		Paragraph p;
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		for (int h = 0; h < TH.size(); h++) {
			p = new Paragraph(TH.get(h), fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table1.addCell(p);
		}

		table1.setHeaderRows(1); // table first row will be repeated in all pages
		// Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 10); chandani

		if (aList.size() > 0) {
			for (int i = 0; i < aList.size(); i++) {
				List<String> l = aList.get(i);
				for (int j = 0; j < l.size(); j++) {
					p = new Paragraph(l.get(j), fontTableValue2);
					table1.addCell(p);
				}
			}
		} else {
			p = new Paragraph("No Data available");
			PdfPCell c4 = new PdfPCell(p);
			c4.setColspan(6);
			table1.addCell(c4);
		}

		Phrase p2 = new Phrase();

		Chunk pSUP = new Chunk("CIVILIAN EMPLOYEE", fontTableHeading);
		pSUP.setUnderline(0.1f, -2f);
		p2.add(pSUP);

		//		Phrase p3 = new Phrase("RE-EMPLOYED", fontTableHeading);
		Phrase p3 = new Phrase();

		Chunk pRE = new Chunk("Summary of the  qty", fontTableHeading);
		pRE.setUnderline(0.1f, -2f);
		p3.add(pRE);

		PdfPTable table3 = new PdfPTable(3);
		table3.setWidths(new int[] { 2, 2, 2 });
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		// Phrase p3 = new Phrase(Heading2);

		for (int r = 0; r < TH2.size(); r++) {
			p = new Paragraph(TH2.get(r), fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}

		table3.setHeaderRows(1); // table first row will be repeated in all pages

		if (pdf_summary.size() > 0) {
			for (int i = 0; i < pdf_summary.size(); i++) {
				List<String> l = pdf_summary.get(i);
				for (int j = 0; j < l.size(); j++) {
					p = new Paragraph(l.get(j), fontTableValue2);
					table3.addCell(p);
				}
			}
		} else {
			p = new Paragraph("No Data available");
			PdfPCell c2 = new PdfPCell(p);
			c2.setColspan(20);
			table3.addCell(c2);
		}

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		//		cell1.addElement(new Phrase("\n"));
		//		cell1.addElement(tableseal);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(tablecenter);
		cell1.addElement(p1);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table1);

		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p3);
		cell1.addElement(table3);
		cell1.addElement(new Phrase("\n"));
		table.addCell(cell1);

		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);
		PdfPTable table11 = new PdfPTable(1);
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell11;
		cell11 = new PdfPCell();
		cell11.setBorder(Rectangle.NO_BORDER);
		cell11.addElement(table);
		table11.addCell(cell11);
		table11.setTableEvent(new ImageBackgroundEvent(request));
		document.add(table11);
		// document.add(table);

		super.buildPdfMetadata(model, document, request);
	}

	private String get(Integer sum_auth2) {
		// TODO Auto-generated method stub
		return null;
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
			//			String watermark = "";
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

				// Portrait Page size 700 * 523

				// Landscape page size 453 * 770

				int tableWidth = (int) table.getTotalWidth();

				int first = 0;

				if (tableWidth == 523) {

					//					first = 750;
					first = 700;
				}

				if (tableWidth == 770) {

					//					first = 500;
					first = 450;

				}

				int loop = (int) table.getRowHeight(0);

				int last = first - (int) table.getRowHeight(0);

				Phrase p = new Phrase();

				p.add(String.valueOf(page));

				float width = ColumnText.getWidth(p);

				/*
				 * ColumnText.showTextAligned(cb, PdfContentByte.ALIGN_RIGHT, p,
				 * cb.getPdfDocument().right() - width, cb.getPdfDocument().top() + 9, 0);
				 */

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

	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		PdfTemplate total1;

		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(10, 15);
				total1 = writer.getDirectContent().createTemplate(10, 15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			// total = writer.getDirectContent().createTemplate(30, 12);
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			try {
				Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
				if (writer.getPageNumber() > 1) {

					PdfPTable table = new PdfPTable(3);

					table.setWidths(new float[] { (float) 250.95, (float) 12.50, (float) 2.55 });

					table.setTotalWidth(
							document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
					table.setLockedWidth(true);
					table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					table.addCell("");
					table.addCell(new Paragraph(writer.getPageNumber() + " OF", fontTableHeading1));
					PdfPCell cell = new PdfPCell(Image.getInstance(total));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell);
					table.writeSelectedRows(0, -1, document.leftMargin(), document.topMargin() + 550,
							writer.getDirectContent());
				} else {

					PdfPTable table = new PdfPTable(3);
					table.setWidths(new float[] { (float) 250.95, (float) 12.50, (float) 2.55 });
					table.setTotalWidth(
							document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
					table.setLockedWidth(true);
					table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					table.addCell("");
					// table.addCell(new Paragraph("TOTAL SHEET :",fontTableHeading1));
					// PdfPCell cell1 = new PdfPCell(Image.getInstance(total1));
					// cell1.setBorder(Rectangle.NO_BORDER);
					// cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					// table.addCell(cell1);

					table.addCell(new Paragraph(writer.getPageNumber() + " OF", fontTableHeading1));
					PdfPCell cell = new PdfPCell(Image.getInstance(total));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell);

					table.writeSelectedRows(0, -1, document.leftMargin(), document.topMargin() + 550,
							writer.getDirectContent());
					// table.writeSelectedRows(0, -1, document.leftMargin(),
					// document.topMargin()+730, writer.getDirectContent());
				}

			} catch (DocumentException de) {

				throw new ExceptionConverter(de);
			}
		}

		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {
			Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
			ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
					new Paragraph(String.valueOf(writer.getPageNumber() - 1), fontTableHeading1), 2, 2, 0);
			ColumnText.showTextAligned(total1, Element.ALIGN_LEFT,
					new Paragraph(String.valueOf(writer.getPageNumber() - 1), fontTableHeading1), 2, 2, 0);
		}
	}

}
