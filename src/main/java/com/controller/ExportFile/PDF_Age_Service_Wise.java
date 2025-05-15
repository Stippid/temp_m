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
import com.controller.ExportFile.PDF_Arms_ServiceWise_query.ImageBackgroundEvent;
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
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class PDF_Age_Service_Wise extends AbstractPdfView {
	
	String Heading = "";
	String username = "";String foot="";
	
	ArrayList<ArrayList<String>> sevicelist;
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	
	public PDF_Age_Service_Wise(String Heading,String foot,String username,
			ArrayList<ArrayList<String>>sevicelist){
		
		this.Heading = Heading;
		this.username = username;this.foot=foot;
		this.sevicelist=sevicelist;
		
	}
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
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
		logo.scaleAbsoluteHeight(5);
		logo.scaleAbsoluteWidth(5);
		logo.scalePercent(25);
		Chunk chunk = new Chunk(logo, 0, -4);

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
		logo2.scaleAbsoluteHeight(6);
		logo2.scaleAbsoluteWidth(6);
		logo2.scalePercent(28);
		 Date date = new Date();
		 String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		 
		Chunk chunk2 = new Chunk(logo2, 10, -4);
		Chunk underline = new Chunk("RESTRICTED", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("HELD STR OF OFFRS AGE WISE", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		/*Chunk underline3 = new Chunk("DIGHI CAMP, PUNE-15", fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);*/
		Chunk underline4 = new Chunk("DATA AS ON :" + date1, fontTableHeadingSubMainHead);
		underline4.setUnderline(0.1f, -2f);
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
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
		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		Chunk underline1 = new Chunk("RESTRICTED", fontTableHeading1);
		underline1.setUnderline(0.1f, -2f);
		Phrase p1 = new Phrase(""+glue);
		p1.add(underline1);
		p.add(glue);
		p1.setFont(fontTableHeading1);	
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

		
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		
		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		//List<String> l1 = aList.get(0);
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		//Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 10);
		Paragraph p=new Paragraph();
		///Chunk glue = new Chunk(new VerticalPositionMark());
		
		Paragraph pl;
		Paragraph plv;
		
		Paragraph pr;
		Paragraph prv;
		
		PdfPTable tablecenterAGE= new PdfPTable(2);
		///tablecenterAGE.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablecenterAGE.setWidthPercentage(100);
		tablecenterAGE.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablecenterAGE.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		pl= new Paragraph("AGE GROUP",fontTableHeading);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph("NO OF OFFICERS",fontTableHeading);
		tablecenterAGE.addCell(plv);
		pl= new Paragraph("YRS20 ONWARDS LESS THAN 25YRS",fontTableValue);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph(aList.get(0).get(0),fontTableValue);
		tablecenterAGE.addCell(plv);
		pl= new Paragraph("YRS25 ONWARDS LESS THAN 30RS",fontTableValue);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph(aList.get(0).get(1),fontTableValue);
		tablecenterAGE.addCell(plv);
		pl= new Paragraph("YRS30 ONWARDS LESS THAN 35RS",fontTableValue);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph(aList.get(0).get(2),fontTableValue);
		tablecenterAGE.addCell(plv);
		pl= new Paragraph("YRS35 ONWARDS LESS THAN 40RS",fontTableValue);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph(aList.get(0).get(3),fontTableValue);
		tablecenterAGE.addCell(plv);
		pl= new Paragraph("YRS40 ONWARDS LESS THAN 45RS",fontTableValue);
		tablecenterAGE.addCell(pl);
		plv= new Paragraph(aList.get(0).get(4),fontTableValue);
		tablecenterAGE.addCell(plv);
		pr= new Paragraph("YRS45 ONWARDS LESS THAN 50RS",fontTableValue);
		tablecenterAGE.addCell(pr);
		prv= new Paragraph(aList.get(0).get(5),fontTableValue);
		tablecenterAGE.addCell(prv);
		pr= new Paragraph("YRS50 ONWARDS LESS THAN 55RS",fontTableValue);
		tablecenterAGE.addCell(pr);
		prv = new Paragraph(aList.get(0).get(6),fontTableValue);
		tablecenterAGE.addCell(prv);
		pr= new Paragraph("YRS55 ONWARDS LESS THAN 60RS",fontTableValue);
		tablecenterAGE.addCell(pr);
		prv= new Paragraph(aList.get(0).get(7),fontTableValue);
		tablecenterAGE.addCell(prv);
		pr= new Paragraph("YEARS60 ONWARDS",fontTableValue);
		tablecenterAGE.addCell(pr);
		prv= new Paragraph(aList.get(0).get(8),fontTableValue);
		tablecenterAGE.addCell(prv);
		pr= new Paragraph("TOTAL",fontTableHeading);
		tablecenterAGE.addCell(pr);
		prv= new Paragraph(aList.get(0).get(9),fontTableHeading);
		tablecenterAGE.addCell(prv);
		
		table.addCell(tablecenterAGE);
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
	
		
		
		
		PdfPTable tablecenterService= new PdfPTable(2);
		//tablecenterService.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablecenterService.setWidthPercentage(100);
		tablecenterService.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablecenterService.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		pl= new Paragraph("SERVICE GROUP",fontTableHeading);
		tablecenterService.addCell(pl);
		plv= new Paragraph("NO OF OFFICERS",fontTableHeading);
		tablecenterService.addCell(plv);
		pl= new Paragraph("LESS THAN 1YRS",fontTableValue);
		tablecenterService.addCell(pl);
		plv= new Paragraph(sevicelist.get(0).get(0).toString(),fontTableValue);
		tablecenterService.addCell(plv);
		pl= new Paragraph("YRS 1 ONWARDS LESS THAN 3YRS",fontTableValue);
		tablecenterService.addCell(pl);
		plv= new Paragraph(sevicelist.get(0).get(1).toString(),fontTableValue);
		tablecenterService.addCell(plv);
		pl= new Paragraph("YRS 3 ONWARDS LESS THAN 5YRS",fontTableValue);
		tablecenterService.addCell(pl);
		plv= new Paragraph(sevicelist.get(0).get(2).toString(),fontTableValue);
		tablecenterService.addCell(plv);
		pl= new Paragraph("YRS 5 ONWARDS LESS THAN 7YRS",fontTableValue);
		tablecenterService.addCell(pl);
		plv= new Paragraph(sevicelist.get(0).get(3).toString(),fontTableValue);
		tablecenterService.addCell(plv);
		pl= new Paragraph("YRS 7 ONWARDS LESS THAN 9YRS",fontTableValue);
		tablecenterService.addCell(pl);
		plv= new Paragraph(sevicelist.get(0).get(4).toString(),fontTableValue);
		tablecenterService.addCell(plv);
		pr= new Paragraph("YRS 9 ONWARDS LESS THAN 11YRSS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(5).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 11 ONWARDS LESS THAN 13YRS",fontTableValue);
		tablecenterService.addCell(pr);
		prv = new Paragraph(sevicelist.get(0).get(6).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 13 ONWARDS LESS THAN 15YRS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(7).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 15 ONWARDS LESS THAN 20YRS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(8).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 20 ONWARDS LESS THAN 25YRS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(9).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 25 ONWARDS LESS THAN 30YRS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(10).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("YRS 30 ONWARDS",fontTableValue);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(11).toString(),fontTableValue);
		tablecenterService.addCell(prv);
		pr= new Paragraph("TOTAL",fontTableHeading);
		tablecenterService.addCell(pr);
		prv= new Paragraph(sevicelist.get(0).get(12).toString(),fontTableHeading);
		tablecenterService.addCell(prv);
		
		table.addCell(tablecenterService);
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		
		PdfPTable finaltable = new PdfPTable(1);
		finaltable.setWidthPercentage(100);
		finaltable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		finaltable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		finaltable.addCell(table);
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

			graphics.drawString(watermark + watermark + watermark, 0, 20);
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
				
				if (tableWidth == 1119) {

					first = 1500;

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
