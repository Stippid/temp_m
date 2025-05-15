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
import com.controller.ExportFile.PDF_Civilian_Arm_Service_Regular_Wise.ImageBackgroundEvent;
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


public class PDF_Civilian_Services_SC_ST_Wise extends AbstractPdfView{

	List<String> TH;
	String Heading = "";
	String username = "";String foot="";
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	
	public PDF_Civilian_Services_SC_ST_Wise(String Heading,List<String> TH,String foot,String username){
		
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;this.foot=foot;
	
	}
	
	@SuppressWarnings("unused")
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
//		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, );
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
		Chunk underline2 = new Chunk("SPECIAL REPRSENTATION IN SERVICES OF SC/ST(CIVILIANS)", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		Chunk underline3 = new Chunk("STATEMENT SHOWING THE TOTAL NUMBER OF GOVT SERVANT AND ", fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		Chunk underline4 = new Chunk("THE NUMBER OF SC/ST AMONGST THEM IN THE LOWER FORMATIONS * OF THE ARMY", fontTableHeadingMainHead);
		underline4.setUnderline(0.1f, -2f);
		Chunk underline5 = new Chunk("(PERMANENT & TEMPORARY WISE)", fontTableHeadingMainHead);
		underline5.setUnderline(0.1f, -2f);
		Chunk underline6 = new Chunk("DATA AS ON  : " + date1, fontTableHeadingMainHead);
		underline6.setUnderline(0.1f, -2f);
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		p.add(underline3);
		p.add("\n");
		p.add(underline4);
		p.add("\n");
		p.add(underline5);
		p.add("\n");
		p.add(underline6);
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
		Font fontTableHeadingNonBoldData2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Paragraph p=new Paragraph();
	
		PdfPTable table3 = new PdfPTable(10);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPTable table4 = new PdfPTable(10);
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPTable table5 = new PdfPTable(6);
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPTable table6 = new PdfPTable(8);
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		/*for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}
		
		table3.setHeaderRows(1); // table first row will be repeated in all pages

		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}*/
		for(int h=0;h<4;h++) {
			if(h==2) {
				PdfPCell p1=new PdfPCell();
//				p1.addElement(new Phrase(TH.get(2)));
				p = new Paragraph(new Phrase(TH.get(2)));
				p.setAlignment(Element.ALIGN_CENTER);
				p1.addElement(p);
				p1.setColspan(4);
				table3.addCell(p1);
			}if(h==3) {
				PdfPCell p1=new PdfPCell();
//				p1.addElement(new Phrase(TH.get(3)));
				p = new Paragraph(new Phrase(TH.get(3)));
				p.setAlignment(Element.ALIGN_CENTER);
				p1.addElement(p);
				p1.setColspan(4);
				table3.addCell(p1);
			}if(h==0||h==1){
				PdfPCell p1=new PdfPCell();
				p1.addElement(new Phrase(TH.get(h)));
				p1.setRowspan(2);
				table3.addCell(p1);
			}
		}
		for(int o=0;o<TH.size()-4;o++) {
			if(o==2||o==3) {
				for(int n=TH.size()-10;n<TH.size()-6;n++) {
				
					
					PdfPCell p1=new PdfPCell();
//					p1.addElement(new Phrase(TH.get(n)));
					p = new Paragraph(new Phrase(TH.get(n)));
					p.setAlignment(Element.ALIGN_CENTER);
					p1.addElement(p);
					table3.addCell(p1);
				}
			}
		}
		int w=0;
		for(int e=0;e<=2;e++) {
			if(e==0) {
				PdfPCell p1=new PdfPCell();
				p1.addElement(new Phrase(TH.get(8)));
				p1.setRowspan(4);
				table4.addCell(p1);
				
				for(int b=10;b<=13;b++) {
					PdfPCell p2=new PdfPCell();
					p2.addElement(new Phrase(TH.get(b)));
					table4.addCell(p2);
									
						List<String> l = aList.get(w);
						for(int j = 0;j<l.size();j++) {
							
							
							PdfPCell p6=new PdfPCell();
							p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
							p.setAlignment(Element.ALIGN_RIGHT);
							p6.addElement(p);
							table4.addCell(p6);
						}
						w++;
				}
				
				
			}if(e==1) {
				PdfPCell p1=new PdfPCell();
				p1.addElement(new Phrase(TH.get(9)));
				p1.setRowspan(4);
				table4.addCell(p1);
				
				for(int b=10;b<=13;b++) {
					PdfPCell p2=new PdfPCell();
					p2.addElement(new Phrase(TH.get(b)));
					table4.addCell(p2);
										
						List<String> l = aList.get(w);
						for(int j = 0;j<l.size();j++) {
							PdfPCell p4=new PdfPCell();
							p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
							p.setAlignment(Element.ALIGN_RIGHT);
							p4.addElement(p);
							table4.addCell(p4);
						}
					w++;	
				}
			}if(e==2) {
				PdfPCell p1=new PdfPCell();
				p = new Paragraph("GRAND TOTAL");
				p.setAlignment(Element.ALIGN_CENTER);
				p1.addElement(p);
				p1.setColspan(2);
				table4.addCell(p1);
							
					List<String> l = aList.get(w);
					for(int j = 0;j<l.size();j++) {
						PdfPCell p5=new PdfPCell();
						p = new Paragraph(l.get(j),fontTableHeadingNonBoldData2);
						p.setAlignment(Element.ALIGN_RIGHT);
						p5.addElement(p);
						table4.addCell(p5);
					}			
			}
		}
		
		p = new Paragraph("(*) - All Units Under Comds including Comd HQs excluding Br/Dtes/Units under Ministry of Defence \n\n",fontTableHeadingNonBoldData);
		
			
		table.addCell(table3);
		table.addCell(table4);
		
		PdfPTable finaltable = new PdfPTable(1);
		finaltable.setWidthPercentage(100);
		finaltable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		finaltable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		finaltable.addCell(table);
		finaltable.setTableEvent(new ImageBackgroundEvent(request));
		document.add(finaltable);
		document.add(p);
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
