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

public class PDF_User_ParentArms_query extends AbstractPdfView{
	
	
	List<String> TH;List<String> TH1;
	String Heading = "";
	String username = "";String foot="";
	ArrayList<ArrayList<String>>smlist;ArrayList<ArrayList<String>>fmnlist;
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	
	public PDF_User_ParentArms_query(String Heading,List<String> TH,List<String> TH1,String foot,String username,
			ArrayList<ArrayList<String>>smlist,ArrayList<ArrayList<String>>fmnlist){
		
		this.TH = TH;
		this.TH1 = TH1;
		this.Heading = Heading;
		this.username = username;this.foot=foot;
		this.smlist=smlist;	
		this.fmnlist=fmnlist;	
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
		Chunk chunk2 = new Chunk(logo2, 10, -4);
		
		 Date date = new Date();
		 String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			
			
		Chunk underline = new Chunk("RESTRICTED", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("HELD STR OF OFFRS BY USER ARMS/ FMN AND PARENT ARM/SERVICES.", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		Chunk underline3 = new Chunk("DATA AS ON " + date1 , fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		Chunk underline4 = new Chunk("REPORT NO MISO/OFFRS/03", fontTableHeadingSubMainHead);
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
		p.add(underline3);
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
		document.setPageSize(PageSize.A2); // set document landscape
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
		
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		
		
		Paragraph p=new Paragraph();
	
		Chunk underline3 = new Chunk("CTPART I", fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		/* p=new Paragraph("CTPART I");
			table.addCell(p);*/
	/*	PdfPTable tb = new PdfPTable(1);
		tb.addCell("CTPART I");*/
		PdfPTable table3 = new PdfPTable(27);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}
		
		table3.setHeaderRows(1); // table first row will be repeated in all pages

		int s_ac = 0; int s_arty = 0; int s_aad = 0; int s_avn = 0; int s_eng = 0; int s_sig = 0; int s_gr = 0; 
		int s_inf = 0; int s_mech = 0; int s_asc1 = 0; int s_aoc = 0; int s_eme = 0; 
		int s_aps = 0; int s_aec = 0; int s_int1 = 0; int s_jag = 0; int s_aptc = 0;
		int s_slro = 0; int s_amc = 0; int s_adc = 0; int s_rvs = 0; int s_mf = 0; int s_total = 0;
		
		for(int i=0;i< smlist.size();i++) {
			List<String> l = smlist.get(i);
			
			s_ac += Integer.parseInt(l.get(4)); s_arty += Integer.parseInt(l.get(5)); s_aad += Integer.parseInt(l.get(6));
			s_avn += Integer.parseInt(l.get(7)); s_eng += Integer.parseInt(l.get(8)); s_sig += Integer.parseInt(l.get(9)); 
			s_gr += Integer.parseInt(l.get(10));s_inf += Integer.parseInt(l.get(11)); s_mech += Integer.parseInt(l.get(12)); 
			s_asc1 += Integer.parseInt(l.get(13)); s_aoc += Integer.parseInt(l.get(14)); s_eme += Integer.parseInt(l.get(15));
			s_aps += Integer.parseInt(l.get(16)); s_aec += Integer.parseInt(l.get(17)); s_int1 += Integer.parseInt(l.get(18)); 
			s_jag += Integer.parseInt(l.get(19)); s_aptc += Integer.parseInt(l.get(20));s_slro += Integer.parseInt(l.get(21));
			s_amc += Integer.parseInt(l.get(22)); s_adc += Integer.parseInt(l.get(23)); s_rvs += Integer.parseInt(l.get(24)); 
			s_mf += Integer.parseInt(l.get(25)); s_total += Integer.parseInt(l.get(26));
			 
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}
		PdfPCell sh = new PdfPCell();
		sh.addElement(new Phrase("     "+"GRAND TOTAL"));
		sh.setColspan(4);
		table3.addCell(sh);
		table3.addCell(String.valueOf(s_ac));
		table3.addCell(String.valueOf(s_arty));
		table3.addCell(String.valueOf(s_aad));
		table3.addCell(String.valueOf(s_avn));
		table3.addCell(String.valueOf(s_eng));
		table3.addCell(String.valueOf(s_sig));
		table3.addCell(String.valueOf(s_gr));
		table3.addCell(String.valueOf(s_inf));
		table3.addCell(String.valueOf(s_mech));
		table3.addCell(String.valueOf(s_asc1));
		table3.addCell(String.valueOf(s_aoc));
		table3.addCell(String.valueOf(s_eme));
		table3.addCell(String.valueOf(s_aps));
		table3.addCell(String.valueOf(s_aec));
		table3.addCell(String.valueOf(s_int1));
		table3.addCell(String.valueOf(s_jag));
		table3.addCell(String.valueOf(s_aptc));
		table3.addCell(String.valueOf(s_slro));
		table3.addCell(String.valueOf(s_amc));
		table3.addCell(String.valueOf(s_adc));
		table3.addCell(String.valueOf(s_rvs));
		table3.addCell(String.valueOf(s_mf));
		table3.addCell(String.valueOf(s_total));
		
		Chunk underline6 = new Chunk("CTPART II", fontTableHeadingMainHead);
		underline6.setUnderline(0.1f, -2f);
		
		PdfPTable table4 = new PdfPTable(27);
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for(int h=0;h<TH1.size();h++) {
			p = new Paragraph(TH1.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table4.addCell(p);
		}
		
		table4.setHeaderRows(1); // table first row will be repeated in all pages
		int a_ac = 0; int a_arty = 0; int a_aad = 0; int a_avn = 0; int a_eng = 0; int a_sig = 0; int a_gr = 0; 
		int a_inf = 0; int a_mech = 0; int a_asc1 = 0; int a_aoc = 0; int a_eme = 0; 
		int a_aps = 0; int a_aec = 0; int a_int1 = 0; int a_jag = 0; int a_aptc = 0;
		int a_slro = 0; int a_amc = 0; int a_adc = 0; int a_rvs = 0; int a_mf = 0; int a_total = 0;
		
		for(int i=0;i< fmnlist.size();i++) {
			List<String> l = fmnlist.get(i);
			
			a_ac += Integer.parseInt(l.get(4)); a_arty += Integer.parseInt(l.get(5)); a_aad += Integer.parseInt(l.get(6));
			a_avn += Integer.parseInt(l.get(7)); a_eng += Integer.parseInt(l.get(8)); a_sig += Integer.parseInt(l.get(9)); 
			a_gr += Integer.parseInt(l.get(10)); a_inf += Integer.parseInt(l.get(11)); a_mech += Integer.parseInt(l.get(12)); 
			a_asc1 += Integer.parseInt(l.get(13)); a_aoc += Integer.parseInt(l.get(14)); a_eme += Integer.parseInt(l.get(15));
			a_aps += Integer.parseInt(l.get(16)); a_aec += Integer.parseInt(l.get(17)); a_int1 += Integer.parseInt(l.get(18)); 
			a_jag += Integer.parseInt(l.get(19)); a_aptc += Integer.parseInt(l.get(20));a_slro += Integer.parseInt(l.get(21));
			a_amc += Integer.parseInt(l.get(22)); a_adc += Integer.parseInt(l.get(23)); a_rvs += Integer.parseInt(l.get(24)); 
			a_mf += Integer.parseInt(l.get(25)); a_total += Integer.parseInt(l.get(26));
			
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				table4.addCell(p);
			}
		}		
		
		PdfPCell sh1 = new PdfPCell();
		sh1.addElement(new Phrase("     "+"GRAND TOTAL"));
		sh1.setColspan(4);
		table4.addCell(sh1);
		table4.addCell(String.valueOf(a_ac));
		table4.addCell(String.valueOf(a_arty));
		table4.addCell(String.valueOf(a_aad));
		table4.addCell(String.valueOf(a_avn));
		table4.addCell(String.valueOf(a_eng));
		table4.addCell(String.valueOf(a_sig));
		table4.addCell(String.valueOf(a_gr));
		table4.addCell(String.valueOf(a_inf));
		table4.addCell(String.valueOf(a_mech));
		table4.addCell(String.valueOf(a_asc1));
		table4.addCell(String.valueOf(a_aoc));
		table4.addCell(String.valueOf(a_eme));
		table4.addCell(String.valueOf(a_aps));
		table4.addCell(String.valueOf(a_aec));
		table4.addCell(String.valueOf(a_int1));
		table4.addCell(String.valueOf(a_jag));
		table4.addCell(String.valueOf(a_aptc));
		table4.addCell(String.valueOf(a_slro));
		table4.addCell(String.valueOf(a_amc));
		table4.addCell(String.valueOf(a_adc));
		table4.addCell(String.valueOf(a_rvs));
		table4.addCell(String.valueOf(a_mf));
		table4.addCell(String.valueOf(a_total));
		
		
		table.addCell(new Paragraph(underline3));
		table.addCell(table3);
		table.addCell(new Phrase("\n"));
		table.addCell(new Paragraph(underline6));
		table.addCell(new Phrase("\n"));
		table.addCell(table4);
		//table3.setTableEvent(new ImageBackgroundEvent(request));
		//table4.setTableEvent(new ImageBackgroundEvent(request));
		
		
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
