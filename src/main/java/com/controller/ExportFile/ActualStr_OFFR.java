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

public class ActualStr_OFFR extends AbstractPdfView {
	
	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String foot="";
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public ActualStr_OFFR(String Type,List<String> TH,String Heading,String username,String foot){
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		this.foot=foot;
	}
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
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
		Chunk underline2 = new Chunk("DTE GENERAL INFO SYS", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		Chunk underline4 = new Chunk(Heading, fontTableHeadingSubMainHead);
		//Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		p.add(underline4);
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

		document.setPageCount(1);
		
		
		if(Type.equals("L")) {
			document.setPageSize(PageSize.A4.rotate()); // set document landscape
		}
		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		
		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		List<String> l1 = aList.get(0);
		int colunmSize = l1.size(); // get Colunm Size
	
		float[] relativeWidths;
		relativeWidths = new float[colunmSize];
		Arrays.fill(relativeWidths, 0, colunmSize, 1);
		PdfPTable table = new PdfPTable(colunmSize);
		Paragraph p;

		table.setWidthPercentage(100);
		//table.setWidths(new int[] {1, 3, 3, 3 , 3,3,3 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 
		
		
		for(int h=0;h<TH.size();h++) {
			
			
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table.addCell(p);
		}
		
		table.setHeaderRows(1); // table first row will be repeated in all pages

		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			for(int j = 0;j<l.size();j++) {
				if(TH.get(0).equals("SR NO")) {
					relativeWidths[0] = (float) 0.40;
				}if(TH.get(1).equals("CT PART I & II")) {
					relativeWidths[1] = (float) 0.80;
				}if(TH.get(2).equals("Arm Code")) {
					relativeWidths[2] = (float) 0.60;
				}if(TH.get(3).equals("Arm Desc")) {
					relativeWidths[3] = (float) 2.00;
				}if(TH.get(4).equals("AC")) {
					relativeWidths[4] = (float) 0.40;
				}if(TH.get(5).equals("ARTY")) {
					relativeWidths[5] = (float) 0.40;
				}if(TH.get(6).equals("AAD")) {
					relativeWidths[6] = (float) 0.40;
				}if(TH.get(7).equals("AVN")) {
					relativeWidths[7] = (float) 0.40;
				}if(TH.get(8).equals("ENG")) {
					relativeWidths[8] = (float) 0.40;
				}if(TH.get(9).equals("SNG")) {
					relativeWidths[9] = (float) 0.40;
				}if(TH.get(10).equals("GR")) {
					relativeWidths[10] = (float) 0.40;
				}if(TH.get(11).equals("INF")) {
					relativeWidths[11] = (float) 0.40;
				}if(TH.get(12).equals("MECH")) {
					relativeWidths[12] = (float) 0.40;
				}if(TH.get(13).equals("ASC")) {
					relativeWidths[13] = (float) 0.40;
				}if(TH.get(14).equals("EME")) {
					relativeWidths[14] = (float) 0.40;
				}if(TH.get(15).equals("APS")) {
					relativeWidths[15] = (float) 0.40;
				}if(TH.get(16).equals("AEC")) {
					relativeWidths[16] = (float) 0.40;
				}if(TH.get(17).equals("INT")) {
					relativeWidths[17] = (float) 0.40;
				}if(TH.get(18).equals("APTC")) {
					relativeWidths[18] = (float) 0.40;
				}if(TH.get(19).equals("SLRO")) {
					relativeWidths[19] = (float) 0.40;
				}if(TH.get(20).equals("AMC")) {
					relativeWidths[20] = (float) 0.40;
				}if(TH.get(21).equals("ADC")) {
					relativeWidths[21] = (float) 0.40;
				}if(TH.get(22).equals("RVC")) {
					relativeWidths[22] = (float) 0.40;
				}if(TH.get(23).equals("MF")) {
					relativeWidths[23] = (float) 0.40;
				}if(TH.get(24).equals("TOTAL")) {
					relativeWidths[24] = (float) 0.40;
				}
					table.setWidths(relativeWidths);
					table.addCell(l.get(j));
			}
		}
		
		PdfPTable table1 = new PdfPTable(1);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(table);
		table1.setTableEvent(new ImageBackgroundEvent(request));
		table1.addCell(cell1);

		document.add(table1);
		
		
		
		super.buildPdfMetadata(model, document, request);
	}

	class ImageBackgroundEvent implements PdfPTableEvent {

		protected Image image;

		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {

			this.request = request;

		}

		int page = 1;

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

				// Portrait Page size 700 * 523

				// Landscape page size 453 * 770

				int tableWidth = (int) table.getTotalWidth();

				int first = 0;

				if (tableWidth == 523) {

					first = 750;
				}

				if (tableWidth == 770) {

					first = 500;

				}

				int loop = (int) table.getRowHeight(0);

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


