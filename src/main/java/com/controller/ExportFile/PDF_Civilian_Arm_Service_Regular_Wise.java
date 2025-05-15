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

public class PDF_Civilian_Arm_Service_Regular_Wise  extends AbstractPdfView{
	List<String> TH;
	String Heading = "";
	String username = "";String foot="";
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	
	public PDF_Civilian_Arm_Service_Regular_Wise(String Heading,List<String> TH,String foot,String username){
		
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;this.foot=foot;
	
	}
	
	@SuppressWarnings("unused")
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
		Chunk underline2 = new Chunk("HELD STRENGTH OF CIVILIANS IN THE ARMY - ARM / SERVICE WISE", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		Chunk underline4 = new Chunk("REGULAR ESTABLISHMENT ", fontTableHeadingMainHead);
		underline4.setUnderline(0.1f, -2f);
		Chunk underline3 = new Chunk("DATA AS ON : " + date1, fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		/*Chunk underline4 = new Chunk("TELE NO 02027170099 AND FAX NO 0194 2300137", fontTableHeadingSubMainHead);
		underline4.setUnderline(0.1f, -2f);*/
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
		p.add(underline3);
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
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		//List<String> l1 = aList.get(0);
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Font fontTableHeadingNonBoldData1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Paragraph p=new Paragraph();
	
		PdfPTable table3 = new PdfPTable(9);
		table3.setWidthPercentage(100);
		//26-01-1994
		table3.setWidths(new int[]{1,3,2,2,2,2,2,2,2});
//		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//		for(int o=0;o<TH.size()-5;o++) {
//					if(o==2) {
						PdfPCell s = new PdfPCell();
						p = new Paragraph(TH.get(9)+"\n ",fontTableHeadingNonBoldData);
						p.setAlignment(Element.ALIGN_CENTER);
//						s.addElement(new Phrase(TH.get(9)));
						s.addElement(p);
						s.setColspan(9);
						table3.addCell(s);
//					}if(o==0||o==1||o==7) {
//						PdfPCell e = new PdfPCell();
//						e.addElement(new Phrase(""));
//						table3.addCell(e);
//					}
//		}
		for(int onion=0;onion<TH.size()-5;onion++) {
			if(onion==2) {
				PdfPCell sh = new PdfPCell();
				p = new Paragraph(TH.get(10),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
//				sh.addElement(new Phrase(TH.get(10)));
				sh.setColspan(2);
				table3.addCell(sh);
			}if(onion==3) {
				PdfPCell sh = new PdfPCell();
				p = new Paragraph(TH.get(11),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
//				sh.addElement(new Phrase(TH.get(11)));
				sh.setColspan(4);
				table3.addCell(sh);
			}if(onion==0||onion==1||onion==8) {
				PdfPCell ee = new PdfPCell();
				ee.addElement(new Phrase(""));
				table3.addCell(ee);
			}
		}
		for(int orange=0;orange<TH.size()-5;orange++) {
			if(orange==4) {
				PdfPCell sh = new PdfPCell();
				p = new Paragraph(TH.get(12),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
//				sh.addElement(new Phrase(TH.get(12)));
				sh.setColspan(2);
				table3.addCell(sh);
			}if(orange==5) {
				PdfPCell sh = new PdfPCell();
				p = new Paragraph(TH.get(13),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
//				sh.addElement(new Phrase(TH.get(13)));
				sh.setColspan(2);
				table3.addCell(sh);
			}if(orange==0||orange==1||orange==2||orange==3||orange==6) {
				PdfPCell ee = new PdfPCell();
				ee.addElement(new Phrase(""));
				table3.addCell(ee);
			}
		}
		for(int h=0;h<TH.size()-5;h++) {
//			p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
//			p.setAlignment(Element.ALIGN_CENTER);
//			table3.addCell(p);
			PdfPCell sh = new PdfPCell();
			if (h==0) {
				p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
				table3.addCell(sh);
			}
			else if (h==1) {
				p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_LEFT);
				sh.addElement(p);
				table3.addCell(sh);
			}
			else {
				p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
				table3.addCell(sh);
			}
		}
		
		table3.setHeaderRows(4); // table first row will be repeated in all pages
		   int g_total = 0;
		   int col = 0;
		   int col1 = 0;
		   int col2 = 0;
		   int col3 = 0;
		   int col4 = 0;
		   int col5 = 0;
		   int col6 = 0;
		   int col7 = 0;
		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			col +=Integer.parseInt(l.get(2));
			
			
			col1 +=Integer.parseInt(l.get(3));
			
			
			col2 +=Integer.parseInt(l.get(4));
		
			
			col3 +=Integer.parseInt(l.get(5));
			
			
			col4 +=Integer.parseInt(l.get(6));
	
			
			col5 +=Integer.parseInt(l.get(7));
		
			
			col6 +=Integer.parseInt(l.get(8));
			
			
			
				
			g_total +=Integer.parseInt(l.get(8));
			for(int j = 0;j<l.size();j++) {
//				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
//				table3.addCell(p);
				PdfPCell sh = new PdfPCell();
				if (j==0) {
					p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
					p.setAlignment(Element.ALIGN_CENTER);
					sh.addElement(p);
					table3.addCell(sh);
				}
				else if (j==1) {
					p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
					p.setAlignment(Element.ALIGN_LEFT);
					sh.addElement(p);
					table3.addCell(sh);
				}
				else {
					p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
					p.setAlignment(Element.ALIGN_RIGHT);
					sh.addElement(p);
					table3.addCell(sh);
				}
			}
		}
	
		PdfPCell sh = new PdfPCell();
	
		
		
		
		p = new Paragraph("TOTAL",fontTableHeadingNonBoldData1);
//		sh.addElement(new Phrase("  "+"TOTAL"));
		p.setAlignment(Element.ALIGN_CENTER);
		sh.addElement(p);
		sh.setColspan(2);
		table3.addCell(sh);
		
		PdfPCell sh1 = new PdfPCell();
		p = new Paragraph(String.valueOf(col),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh1.addElement(p);
		
		
		table3.addCell(sh1);
		
		
		PdfPCell sh2 = new PdfPCell();
		p = new Paragraph(String.valueOf(col1),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh2.addElement(p);
		
		table3.addCell(sh2);
		
		PdfPCell sh3 = new PdfPCell();
		p = new Paragraph(String.valueOf(col2),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh3.addElement(p);
		
		table3.addCell(sh3);
		
		PdfPCell sh4 = new PdfPCell();
		p = new Paragraph(String.valueOf(col3),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh4.addElement(p);
		
		table3.addCell(sh4);
		
		
		
		PdfPCell sh5 = new PdfPCell();
		p = new Paragraph(String.valueOf(col4),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh5.addElement(p);
		
		table3.addCell(sh5);
		
		PdfPCell sh6 = new PdfPCell();
		p = new Paragraph(String.valueOf(col5),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh6.addElement(p);
		
		table3.addCell(sh6);
		
		PdfPCell sh7 = new PdfPCell();
		p = new Paragraph(String.valueOf(col6),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_RIGHT);
		sh7.addElement(p);
		
		table3.addCell(sh7);
		
		
		
//		table3.addCell(String.valueOf(col2));
//		table3.addCell(String.valueOf(col3));
//		table3.addCell(String.valueOf(col4));
//		table3.addCell(String.valueOf(col5));
//		table3.addCell(String.valueOf(col6));
		
		
//		PdfPCell sht = new PdfPCell();
//		p = new Paragraph(String.valueOf(g_total),fontTableHeadingNonBoldData);
//		p.setAlignment(Element.ALIGN_RIGHT);
//		sht.addElement(p);
//		table3.addCell(sht);
//		table3.addCell(String.valueOf(g_total));
		
		table.addCell(table3);
		
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

					first = 630;
				}

				if (tableWidth == 770) {

					first = 430;

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
