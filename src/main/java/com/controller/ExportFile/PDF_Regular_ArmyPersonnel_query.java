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
import com.controller.ExportFile.ActualStr_OFFR.ImageBackgroundEvent;

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
import com.lowagie.text.pdf.draw.LineSeparator;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class PDF_Regular_ArmyPersonnel_query extends AbstractPdfView {
	List<String> TH;List<String> TH1;
	String Heading = "";String foot = "";
	String username = "";	
	ArrayList<ArrayList<String>>smlist;ArrayList<ArrayList<String>>fmnlist;
	final static String USER_PASSWORD = "user";
	
	final static String OWNER_PASSWORD = "owner";

	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public PDF_Regular_ArmyPersonnel_query(String Heading,List<String> TH,List<String> TH1,String foot,String username,
			ArrayList<ArrayList<String>>smlist,ArrayList<ArrayList<String>>fmnlist){
		this.TH = TH;this.TH1 = TH1;
		this.Heading = Heading;
		this.username = username;this.foot=foot;
		this.smlist=smlist;this.fmnlist=fmnlist;
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
			String indian_Army =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"indianarmy_smrm5aaa.jpg";
			logo2 = Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(60);
		logo2.scaleAbsoluteWidth(60);
		logo2.scalePercent(30);
		Chunk chunk2 = new Chunk(logo2, -5, 4);
		Chunk underline = new Chunk("DETAILED REPORT :BC/PC", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("(BATTLE CASUALTY/PHYSICAL CASUALTY)", fontTableHeadingMainHead);
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
		
	    document.setPageSize(PageSize.A4); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);
		
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		
		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
		
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
	
		
		Paragraph p=new Paragraph();
		
		Chunk underline5 = new Chunk("GORKHA", fontTableHeadingMainHead);
		underline5.setUnderline(0.1f, -2f);
		
		PdfPTable table3 = new PdfPTable(4);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}
		
		table3.setHeaderRows(1); 
		table3.setHeaderRows(1); // table first row will be repeated in all pages
		int g_total = 0;
		int g_auth = 0;
		for(int i=0;i< smlist.size();i++) {
			List<String> l = smlist.get(i);
			g_auth += Integer.parseInt(l.get(2));
			g_total += Integer.parseInt(l.get(3));
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}
		table3.addCell("");
		table3.addCell("GRAND TOTAL");
		table3.addCell(String.valueOf(g_auth));
		table3.addCell(String.valueOf(g_total));
		
		
		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		table2.addCell(new Phrase("\n"));
	    table2.addCell(new Paragraph(underline5));
		table2.addCell(new Phrase("\n"));
		table2.addCell(table3);
		table2.addCell(new Phrase("\n"));
		//table2.addCell(new Paragraph(underline6));
		table2.addCell(new Phrase("\n"));
		///table2.addCell(table4);
		
		/*table2.setTableEvent(new ImageBackgroundEvent(request));
		document.add(table2);*/
		PdfPTable finaltable = new PdfPTable(1);
		finaltable.setWidthPercentage(100);
		finaltable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		finaltable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		finaltable.addCell(table2);
		finaltable.setTableEvent(new ImageBackgroundEvent(request));
		
		document.add(finaltable);
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


