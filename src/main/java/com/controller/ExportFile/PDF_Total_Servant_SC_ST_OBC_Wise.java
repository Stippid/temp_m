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

public class PDF_Total_Servant_SC_ST_OBC_Wise extends AbstractPdfView{

	List<String> TH;List<String> TH1;
	String Heading = "";
	String username = "";String foot="";
	ArrayList<ArrayList<String>>smlist;ArrayList<ArrayList<String>>tyrechildlist;
	String cont_comd = "";String cont_corps = "";String cont_div= "";String cont_bde= "";String unit_name= "";String sus_no= "";String service_status= "";
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	
	public PDF_Total_Servant_SC_ST_OBC_Wise(String Heading,List<String> TH,List<String> TH1,String foot,String username,
			ArrayList<ArrayList<String>>smlist,ArrayList<ArrayList<String>>tyrechildlist,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String sus_no,String service_status){
		
		this.TH = TH;this.TH1 = TH1;
		this.Heading = Heading;
		this.username = username;this.foot=foot;
		this.smlist=smlist;this.tyrechildlist=tyrechildlist;
		this.cont_comd=cont_comd;
		this.cont_corps=cont_corps;
		this.cont_div=cont_div;
		this.cont_bde=cont_bde;
		this.unit_name=unit_name;
		this.sus_no=sus_no;		
		this.service_status=service_status;	
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
		Chunk underline2 = new Chunk("TOTAL NUMBER OF GOVT SERVANT AND THE NUMBER OF SC/ST/OBC (CIVILIANS) AMONGST THE ARMY", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);
		/*Chunk underline3 = new Chunk("DIGHI CAMP, PUNE-15", fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		Chunk underline4 = new Chunk("TELE NO 02027170099 AND FAX NO 0194 2300137", fontTableHeadingSubMainHead);
		underline4.setUnderline(0.1f, -2f);*/
		Chunk underline6 = new Chunk("DATA AS ON  : " + date1, fontTableHeadingMainHead);
		underline6.setUnderline(0.1f, -2f);
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		p.add("\n");
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		p.add(underline6);
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
		
		//@SuppressWarnings("unchecked")
		//ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		//List<String> l1 = aList.get(0);
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		/*Chunk underline3 = new Chunk("IN INSPECTION", fontTableHeadingMainHead);
		underline3.setUnderline(0.1f, -2f);
		Phrase p14 = new Phrase("                                                         ");
		p14.add(underline3);*/
		
		Paragraph p=new Paragraph();
		Chunk glue = new Chunk(new VerticalPositionMark());
		
		
		
		
		PdfPTable table2 = new PdfPTable(5);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
	/*	table2.addCell(new Paragraph("Cont Comd"));
		table2.addCell(new Paragraph(cont_comd));
		table2.addCell(new Paragraph(glue));
		table2.addCell(new Paragraph("Cont Corps"));
		table2.addCell(new Paragraph(cont_corps));
		
		table2.addCell(new Paragraph("Cont Div"));
		table2.addCell(new Paragraph(cont_div));
		table2.addCell(new Paragraph(glue));
		table2.addCell(new Paragraph("Cont Bde"));
		table2.addCell(new Paragraph(cont_bde));
		
		table2.addCell(new Paragraph("Unit Name"));
		table2.addCell(new Paragraph(unit_name));
		table2.addCell(new Paragraph(glue));
		table2.addCell(new Paragraph("SUS No"));
		table2.addCell(new Paragraph(sus_no));
		
		table2.addCell(new Paragraph("Service Status"));
		table2.addCell(new Paragraph(service_status));
		table2.addCell(new Paragraph(glue));
		table2.addCell(new Paragraph(""));
		table2.addCell(new Paragraph(""));
		
		*/
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add("\n");
		
		Chunk underline5 = new Chunk("REGULAR EST (PERMANENT/TEMPORARY)", fontTableHeadingMainHead);
		underline5.setUnderline(0.1f, -2f);
		Font fontTableHeadingNonBoldData1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		PdfPTable table3 = new PdfPTable(12);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		
		for(int h=0;h<TH.size()-3;h++) {
			if(h==3 || h==4 || h==5) {
				p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				PdfPCell p45 = new PdfPCell();
				p45.setColspan(3);
				p45.addElement(p);
				table3.addCell(p45);
			}else {
			p = new Paragraph(TH.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
			}
		}
		for(int j=0;j<TH.size()-3;j++) {
			if(j == 3 || j == 4 || j == 5) {
				
				PdfPCell sh = new PdfPCell();
				p = new Paragraph(TH.get(6),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh.addElement(p);
				table3.addCell(sh);
		
				
				PdfPCell sh1 = new PdfPCell();
				p = new Paragraph(TH.get(7),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh1.addElement(p);
				table3.addCell(sh1);
				
				
				
				PdfPCell sh2 = new PdfPCell();
				p = new Paragraph(TH.get(8),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh2.addElement(p);
				table3.addCell(sh2);
			}else {
				
				PdfPCell sh3 = new PdfPCell();
				p = new Paragraph("",fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh3.addElement(p);
				table3.addCell(sh3);
			}
		}
		
		table3.setHeaderRows(1); // table first row will be repeated in all pages
		int s_total = 0; int s_gp_a_sc = 0; int s_gp_a_st =0; int s_gp_a_obc =0; int s_gp_b_sc =0;
		int s_gp_b_st = 0; int s_gp_b_obc = 0; int s_gp_c_sc =0; int s_gp_c_st =0; int s_gp_c_obc =0;
		
		for(int i=0;i< smlist.size();i++) {
			List<String> l = smlist.get(i);
			
			s_total+=Integer.parseInt(l.get(2)); s_gp_a_sc+=Integer.parseInt(l.get(3)); 
			s_gp_a_st+=Integer.parseInt(l.get(4)); s_gp_a_obc+=Integer.parseInt(l.get(5));
			s_gp_b_sc+=Integer.parseInt(l.get(6)); s_gp_b_st+=Integer.parseInt(l.get(7));
			s_gp_b_obc+=Integer.parseInt(l.get(8)); s_gp_c_sc+=Integer.parseInt(l.get(9));
			s_gp_c_st+=Integer.parseInt(l.get(10)); s_gp_c_obc+=Integer.parseInt(l.get(11));
			
			for(int j = 0;j<l.size();j++) {
				PdfPCell sh1 = new PdfPCell();
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh1.addElement(p);
				table3.addCell(sh1);
			}
		}
		table3.addCell("");
		table3.addCell("TOTAL");
		
		
		PdfPCell sh1 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_total),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh1.addElement(p);
		
		table3.addCell(sh1);
		
		PdfPCell sh2 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_a_sc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh2.addElement(p);
		
		table3.addCell(sh2);
		
		
		
		PdfPCell sh3 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_a_st),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh3.addElement(p);
		
		table3.addCell(sh3);
		
		PdfPCell sh4 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_a_obc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh4.addElement(p);
		
		table3.addCell(sh4);
		
		
		PdfPCell sh5 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_b_sc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh5.addElement(p);
		
		
		table3.addCell(sh5);
		
		
		PdfPCell sh6 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_b_st),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh6.addElement(p);
		
		
		table3.addCell(sh6);
		
		
		PdfPCell sh7 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_b_obc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh7.addElement(p);
		table3.addCell(sh7);
		
		PdfPCell sh8 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_c_sc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh8.addElement(p);
		table3.addCell(sh8);
		
		
		PdfPCell sh9 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_c_st),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh9.addElement(p);
		table3.addCell(sh9);
		
		PdfPCell sh10 = new PdfPCell();
		p = new Paragraph(String.valueOf(s_gp_c_obc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh10.addElement(p);
		table3.addCell(sh10);

		
		
		Chunk underline6 = new Chunk("NON REGULAR EST", fontTableHeadingMainHead);
		underline6.setUnderline(0.1f, -2f);
		
		PdfPTable table4 = new PdfPTable(6);
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		for(int h=0;h<TH1.size();h++) {
			PdfPCell sh19 = new PdfPCell();
			p = new Paragraph(TH1.get(h),fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			sh19.addElement(p);
			table4.addCell(sh19);
		}
		
		table4.setHeaderRows(1); // table first row will be repeated in all pages

		int n_total = 0; int n_sc = 0; int n_st =0; int n_obc =0; 		
		for(int i=0;i< tyrechildlist.size();i++) {
			List<String> l = tyrechildlist.get(i);
			n_total+=Integer.parseInt(l.get(2)); n_sc+=Integer.parseInt(l.get(3)); 
			n_st+=Integer.parseInt(l.get(4)); n_obc+=Integer.parseInt(l.get(5));
			
			for(int j = 0;j<l.size();j++) {
				
				PdfPCell sh15 = new PdfPCell();
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldData);
				p.setAlignment(Element.ALIGN_CENTER);
				sh15.addElement(p);
				table4.addCell(sh15);
			}
		}
		table4.addCell("");
		PdfPCell st = new PdfPCell();
		
		p = new Paragraph("TOTAL",fontTableHeadingNonBoldData1);
		
		p.setAlignment(Element.ALIGN_CENTER);
		st.addElement(p);
//		st.setColspan(1);
		table4.addCell(st);
		
		
		
		PdfPCell sh11 = new PdfPCell();
		p = new Paragraph(String.valueOf(n_total),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh11.addElement(p);
		
		table4.addCell(sh11);
		
		PdfPCell sh12 = new PdfPCell();
		p = new Paragraph(String.valueOf(n_sc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh12.addElement(p);
		
		table4.addCell(sh12);
		
		
		PdfPCell sh13 = new PdfPCell();
		p = new Paragraph(String.valueOf(n_st),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh13.addElement(p);
		
		table4.addCell(sh13);
		
		
		PdfPCell sh14 = new PdfPCell();
		p = new Paragraph(String.valueOf(n_obc),fontTableHeadingNonBoldData1);
		p.setAlignment(Element.ALIGN_CENTER);
		sh14.addElement(p);
		
		table4.addCell(sh14);
		
		table.addCell(table2);
		//table.addCell(new Phrase("\n"));
		table.addCell(new Paragraph(underline5));
//		table.addCell(new Phrase("\n"));
		table.addCell(table3);
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Phrase("\n"));
		table.addCell(new Paragraph(underline6));
		//table.addCell(new Phrase("\n"));
		table.addCell(table4);
		
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
