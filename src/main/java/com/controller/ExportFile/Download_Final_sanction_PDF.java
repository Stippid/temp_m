package com.controller.ExportFile;

import java.awt.Color;
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


public class Download_Final_sanction_PDF extends AbstractPdfView {


	List<String> TH;
	String foot="";
	String username = "";
	final static String USER_PASSWORD = "user";
	String batchId;
	String unit_name;
	
	String per_no;
	String name;
	String rk;
	String appt;
	String sanction_date;

	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public Download_Final_sanction_PDF(List<String> TH,String foot,String username,String batchId,String unit_name,String sanction_date,
			String per_no,String name,String rk,String appt){

		this.TH = TH;this.foot=foot;this.username = username;this.batchId = batchId; this.unit_name = unit_name;this.sanction_date = sanction_date;this.per_no = per_no;
		this.name = name; this.rk = rk; this.appt = appt;
	}
	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = formatter.format(date);
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		//Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Image logo = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"indianarmy_smrm5aaa.jpg";
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
			String indian_Army =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"dgis-logo_new.png";
			logo2 = Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg"
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
		Chunk underline2 = new Chunk("SANCTION OF COMPETENT AUTHORITY TO OFFRS OF "+ unit_name +" DESIROUS TO PLACE DEMAND FOR PURCHASE OF LAPTOP & SIMILAR DEVICES AS PER DDG IT POLICY B/03568/GEN/DDGIT (PROJ) DT 29 NOV 24", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		//Chunk underline4 = new Chunk(MainHead, fontTableHeadingSubMainHead);
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		//p.add(underline4);
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



		/*Phrase p = new Phrase();
		p.add("RESTRICTED");
		Font fontTable= FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		p.setFont(fontTable);
		p.add(Heading);*/

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

		///document.setPageCount(1);
		document.setPageSize(PageSize.A4.rotate()); // set document landscape

		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = batchId;

		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		System.out.println(file_name);
		System.out.println("filename=\""+file_name+".pdf\"");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");


		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		Chunk glue = new Chunk(new VerticalPositionMark());
		Paragraph p44 = new Paragraph() ;
		Paragraph p60 = new Paragraph() ;



		Paragraph pl;

		Paragraph plv;
		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);
		Font fontTableValue2 = FontFactory.getFont(FontFactory.TIMES, 9);

		////////////////////////////seal ////////

		PdfPTable table1 = new PdfPTable(11);
		table1.setWidths(new int[]{1,3,2,2,2,2,2,2,2,2,2});
		Paragraph p;
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table1.addCell(p);
		}

		table1.setHeaderRows(1);

		if(aList.size() > 0) {
			for(int i=0;i< aList.size();i++) {
				List<String> l = aList.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					PdfPCell cell = new PdfPCell(p); 

					if (j == 10) {
						cell.setMinimumHeight(50);
					}

					table1.addCell(cell);
				}
			}
		}else {
			p = new Paragraph("No Data available");
			PdfPCell c4 = new PdfPCell(p);
			c4.setColspan(13);
			table1.addCell(c4);
		}


		///////// Remarks ///////////
		PdfPTable tableRemarks = new PdfPTable(4);
		tableRemarks.setWidthPercentage(100);
		tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		PdfPCell remarkscell1;
		remarkscell1 = new PdfPCell();
		remarkscell1.setBorder(Rectangle.NO_BORDER);
		Paragraph p1 = new Paragraph("Date: "+sanction_date+ "",fontTableHeading);
		remarkscell1.addElement(p1);
		remarkscell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableRemarks.addCell(remarkscell1);
		
		
		PdfPCell remarkscell2;
		remarkscell2 = new PdfPCell();
		remarkscell2.setBorder(Rectangle.NO_BORDER);
	
		remarkscell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableRemarks.addCell(remarkscell2);
		

		PdfPCell remarkscell;
		remarkscell = new PdfPCell();
		remarkscell.setBorder(Rectangle.NO_BORDER);
	
		remarkscell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableRemarks.addCell(remarkscell);
		
		

		PdfPCell remarkscell3;
		remarkscell3 = new PdfPCell();
		remarkscell3.setBorder(Rectangle.NO_BORDER);
		Paragraph p3 = new Paragraph("Digitally Signed by",fontTableHeading);
		remarkscell3.addElement(p3);

		remarkscell3.addElement(new Phrase("\n"));

		p3 = new Paragraph("Pers No: "+per_no+"",fontTableHeading);
		remarkscell3.addElement(p3);

		p3 = new Paragraph("Name: "+name+"",fontTableHeading);
		remarkscell3.addElement(p3);
		
		p3 = new Paragraph("Rk: "+rk+"",fontTableHeading);
		remarkscell3.addElement(p3);
		
		p3 = new Paragraph("Appt: "+appt+"",fontTableHeading);
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
		
		Paragraph firstPageHeading = new Paragraph("Under provn of DDG IT Policy issued vide letter No B/03568/GEN/DDGIT (PROJ) dt 29 Nov 24, sanction is hereby accorded to u/ m  offrs to place demand for Knowledge Enhancement IT Device as per policy in vogue: -", fontTableHeading12);
		firstPageHeading.setAlignment(Element.ALIGN_JUSTIFIED);

		


		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(firstPageHeading);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table1);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(tableRemarks);
		cell1.addElement(lastPageHeading);
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
		table11.setTableEvent(new ImageBackgroundEvent(request,batchId));
		document.add(table11);
		//document.add(table);


		super.buildPdfMetadata(model, document, request);
	}

	private String get(Integer sum_auth2) {
		// TODO Auto-generated method stub
		return null;
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;
		HttpServletRequest request;
		private String batchId; // Add field for batch ID

		// Modified constructor to accept batchId
		ImageBackgroundEvent(HttpServletRequest request, String batchId) {
			this.request = request;
			this.batchId = batchId;
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
			String watermark = "Generated by " + username + " on " + dateString + " with IP " + ip;
			String batchWatermark = "Batch Id = " + batchId;

			try {
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
				cb.saveState();

				// Set font and color for watermark
				BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				cb.setFontAndSize(bf, 25); // Size for main watermark

				// Set color with transparency (light gray with 20% opacity)
				cb.setColorFill(new Color(192, 192, 192, 51));

				// Get page dimensions
				Rectangle pageSize = cb.getPdfDocument().getPageSize();
				float width = pageSize.getWidth();
				float height = pageSize.getHeight();

				// Calculate center points for landscape orientation
				float centerX = width / 2;
				float centerY = height / 2;

				// Add first watermark (existing text)
				cb.beginText();
				cb.showTextAligned(Element.ALIGN_CENTER, watermark, centerX - 50, centerY + 40, 30);
				cb.endText();

				// Add second watermark (batch ID)
				// Using slightly smaller font for batch ID
				cb.setFontAndSize(bf, 22);
				cb.beginText();
				cb.showTextAligned(Element.ALIGN_CENTER, batchWatermark, centerX - 50, centerY - 40, 30);
				cb.endText();

				cb.restoreState();
			} catch (Exception e) {
				throw new ExceptionConverter(e);
			}
		}
	}

	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		PdfTemplate total1;
		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(30, 15); // Increased width to 30
				total1 = writer.getDirectContent().createTemplate(30, 15); // Increased width to 30
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
				if(writer.getPageNumber() > 1){



					PdfPTable table = new PdfPTable(3);

					table.setWidths(new float[] { (float)250.95, (float)12.50, (float)2.55 });

					table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
					table.setLockedWidth(true);
					table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					table.addCell("");
					table.addCell(new Paragraph(writer.getPageNumber()+" OF",fontTableHeading1));
					PdfPCell cell = new PdfPCell(Image.getInstance(total));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell);
					table.writeSelectedRows(0, -1, document.leftMargin(), document.topMargin()+550, writer.getDirectContent());
				}else {

					PdfPTable table = new PdfPTable(3);
					table.setWidths(new float[] { (float)250.95, (float)12.50, (float)2.55 });
					table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
					table.setLockedWidth(true);
					table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					table.addCell("");
					//table.addCell(new Paragraph("TOTAL SHEET :",fontTableHeading1));
					//PdfPCell cell1 = new PdfPCell(Image.getInstance(total1));
					//cell1.setBorder(Rectangle.NO_BORDER);
					//cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					//table.addCell(cell1);

					table.addCell(new Paragraph(writer.getPageNumber()+" OF",fontTableHeading1));
					PdfPCell cell = new PdfPCell(Image.getInstance(total));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(cell);

					table.writeSelectedRows(0, -1, document.leftMargin(), document.topMargin()+550, writer.getDirectContent());
					//table.writeSelectedRows(0, -1, document.leftMargin(), document.topMargin()+730, writer.getDirectContent());
				}

			} catch (DocumentException de) {

				throw new ExceptionConverter(de);
			}
		}
		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {
			Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
			ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
			ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
		}
	}

}