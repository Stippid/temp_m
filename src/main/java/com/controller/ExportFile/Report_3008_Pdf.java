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

import org.apache.tiles.request.Request;
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



public class Report_3008_Pdf extends AbstractPdfView {
	
	
	List<String> TH;
	List<String> TH4;
	List<String> TH5;
	List<String> TH6;
	String foot="";
	String username = "";
	final static String USER_PASSWORD = "user";
	ArrayList<ArrayList<String>> list_super;
	ArrayList<ArrayList<String>> list_ReEmp;
	ArrayList<ArrayList<String>> list_Des;
	ArrayList<ArrayList<String>> list_auth;
	ArrayList<ArrayList<String>> list_held;
	ArrayList<ArrayList<String>> list_Main;
	String cont_comd = "";String cont_corps = "";String cont_div= "";String cont_bde= "";
	Integer sum_auth = 0;Integer sum_held = 0;Integer defi= 0;Integer sur= 0;
	String hd_remarks1="";
	String hd_remarks2="";
	String hd_remarks3="";
	String hd_remarks4="";
	String hd_remarks5="";
	String hd_remarks6="";
	String hd_remarks7="";
	
	
	
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public Report_3008_Pdf(List<String> TH,List<String> TH4,List<String> TH5,String foot,String username,
			ArrayList<ArrayList<String>> list_super,
			ArrayList<ArrayList<String>> list_ReEmp,
			ArrayList<ArrayList<String>> list_Des,
			ArrayList<ArrayList<String>> list_auth,
			ArrayList<ArrayList<String>> list_Main,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,
			Integer sum_auth,Integer sum_held,Integer defi,Integer sur, String hd_remarks1, String hd_remarks2,
			String hd_remarks3, String hd_remarks4, String hd_remarks5, String hd_remarks6, String hd_remarks7,List<String> TH6,ArrayList<ArrayList<String>> list_held){
	
		this.TH = TH;this.TH4 = TH4;this.TH5 = TH5;this.TH6 = TH6;this.foot=foot;this.username = username;
		this.list_super = list_super;this.list_ReEmp = list_ReEmp;this.list_Des = list_Des;this.list_auth = list_auth;this.list_held = list_held;this.list_Main = list_Main;
		this.cont_comd=cont_comd;this.cont_corps=cont_corps;this.cont_div=cont_div;this.cont_bde=cont_bde;
		this.sum_auth=sum_auth;this.sum_held=sum_held;this.defi=defi;this.sur=sur;
		this.hd_remarks1=hd_remarks1;this.hd_remarks2=hd_remarks2;
		this.hd_remarks4=hd_remarks4;this.hd_remarks3=hd_remarks3;
		this.hd_remarks5=hd_remarks5;this.hd_remarks6=hd_remarks6;this.hd_remarks7=hd_remarks7;
	}
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
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
		Chunk underline2 = new Chunk("IAFF - 3008: STR RETURN OFFRS ", fontTableHeadingMainHead);
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
		String file_name = datetimestamp.currentDateWithTimeStampString();
		
		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
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
		PdfPTable tableseal = new PdfPTable(1);
		tableseal.getDefaultCell().setBorder(Rectangle.BOX);
		PdfPTable tableseali = new PdfPTable(3);
		tableseali.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		tableseali.setWidthPercentage(100);
//		pl = new Paragraph("Unit Seal: ", fontTableHeading);
//		tableseali.addCell(pl);
		pl = new Paragraph("        I certify that all known personal occurrences have been reported on IAFF-3008.\n\n\n\n\n ", fontTableValue);
		PdfPCell psi6 = new PdfPCell(pl);
		psi6.setColspan(3);
		//psi6.setHorizontalAlignment(Element.ALIGN_LEFT);
		psi6.setBorder(Rectangle.NO_BORDER);
		tableseali.addCell(psi6);
		pl = new Paragraph("\nUnit Seal: ", fontTableHeading);
		tableseali.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tableseali.addCell(pl);
		pl = new Paragraph("(___________)\nSign of Officer", fontTableHeading);
		tableseali.addCell(pl);
		tableseal.addCell(tableseali);
		////////////////////////////////
		PdfPTable tablesealS = new PdfPTable(4);
		tablesealS.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablesealS.setWidthPercentage(100);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("", fontTableHeading);
		tablesealS.addCell(pl);
		pl = new Paragraph("Sign of Officer", fontTableHeading);
		tablesealS.addCell(pl);
		//////////////////////////////////////
		
		PdfPTable tableleft = new PdfPTable(2);

		tableleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//		PdfPCell pi5 = new PdfPCell(new Paragraph("PERSONAL DETAILS", fontTableheader));
//		pi5.setColspan(2);
//		pi5.setBorder(Rectangle.NO_BORDER);
//		tableleft.addCell(pi5);
		
		//260194
		pl = new Paragraph("\nSUS No: ", fontTableHeading);
		tableleft.addCell(pl);
		if(list_Main.size() > 0)
		{
			plv = new Paragraph("\n"+list_Main.get(0).get(8), fontTableValue);
		}
		else
		{
			plv = new Paragraph("", fontTableValue);
		}
		
		
		tableleft.addCell(plv);
		pl = new Paragraph("\nMonth: ", fontTableHeading);
		tableleft.addCell(pl);
		
		if(list_Main.size() > 0)
		{
			plv = new Paragraph("\n"+list_Main.get(0).get(6), fontTableValue);
		}
		else
		{
			plv = new Paragraph("", fontTableValue);
		}
		
		tableleft.addCell(plv);
		pl = new Paragraph("\nPresent Return No: ", fontTableHeading);
		tableleft.addCell(pl);
		
		if(list_Main.size() > 0)
		{
			plv = new Paragraph("\n"+list_Main.get(0).get(1), fontTableValue);
		}
		else
		{
			plv = new Paragraph("", fontTableValue);
		}
		//e 260194
		tableleft.addCell(plv);
		pl = new Paragraph("\nLast Return No: ", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n", fontTableValue);
		tableleft.addCell(plv);
		
		pl = new Paragraph("\nCont Comd: ", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n"+cont_comd, fontTableValue);
		tableleft.addCell(plv);
		pl = new Paragraph("\nCont Corps:", fontTableHeading);
		tableleft.addCell(pl);
		plv = new Paragraph("\n"+cont_corps, fontTableValue);
		tableleft.addCell(plv);
		
		
		
		

		
		Paragraph pr;
		Paragraph prv;
		PdfPTable tableright = new PdfPTable(2);
		tableright.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		PdfPCell pi6 = new PdfPCell(new Paragraph(" ", fontTableheader));
//		pi6.setColspan(2);
//		pi6.setBorder(Rectangle.NO_BORDER);
//		tableright.addCell(pi6);
		
		pr = new Paragraph("\nUnit Name :", fontTableHeading);
		tableright.addCell(pr);
		if(list_Main.size() > 0)
		{
			prv = new Paragraph("\n"+list_Main.get(0).get(9), fontTableValue);
		}
		else
		{
			prv = new Paragraph("", fontTableValue);
		}
		tableright.addCell(prv);
		pr = new Paragraph("\nYear :", fontTableHeading);
		tableright.addCell(pr);
		if(list_Main.size() > 0)
		{
			prv = new Paragraph("\n"+list_Main.get(0).get(7), fontTableValue);
		}
		else
		{
			prv = new Paragraph("", fontTableValue);
		}
		
		tableright.addCell(prv);
		pr = new Paragraph("\nPresent Return Date :", fontTableHeading);
		tableright.addCell(pr);
		if(list_Main.size() > 0)
		{
			prv = new Paragraph("\n"+list_Main.get(0).get(2), fontTableValue);
		}
		else
		{
			prv = new Paragraph("", fontTableValue);
		}
		tableright.addCell(prv);
		pr = new Paragraph("\nLast Return Date :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n", fontTableValue);
		tableright.addCell(prv);
		
		pr = new Paragraph("\nCont Div: ", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+cont_div, fontTableValue);
		tableright.addCell(prv);
		pr = new Paragraph("\nCont Bde :", fontTableHeading);
		tableright.addCell(pr);
		prv = new Paragraph("\n"+cont_bde, fontTableValue);
		tableright.addCell(prv);
		



		
		
		PdfPTable tablecenter = new PdfPTable(3);

		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.setWidthPercentage(100);

		tablecenter.addCell(tableleft);
		tablecenter.addCell(tableseal);

		tablecenter.addCell(tableright);

		//table111.addCell(tablecenter);

		/*if (list_Main.size() > 0) {
		
		
			p44.add("\nPresent Return No               "+list_Main.get(0).get(1));
			p44.add(glue);
			p44.add("\nPresent Return Date             "+list_Main.get(0).get(2));
			p44.add(glue);
			p44.add(glue);
		}
		*/
		
		
		Phrase p1 = new Phrase();
		
		Chunk pSERVING = new Chunk("SERVING", fontTableHeading);
		pSERVING.setUnderline(0.1f, -2f);
		p1.add(pSERVING);
//		Phrase p1 = new Phrase("SERVING", fontTableHeading);
		
//		p1.setUnderline(0.1f, -2f);
		//p1.add(p2);
		
		PdfPTable table1 = new PdfPTable(13);
		table1.setWidths(new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2});
		Paragraph p;
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table1.addCell(p);
		}
		
		table1.setHeaderRows(1); // table first row will be repeated in all pages
		//Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 10); chandani
		
		if(aList.size() > 0) {
			for(int i=0;i< aList.size();i++) {
				List<String> l = aList.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table1.addCell(p);
				}
			}
		}else {
			p = new Paragraph("No Data available");
			PdfPCell c4 = new PdfPCell(p);
			c4.setColspan(13);
			table1.addCell(c4);
		}
		
//		Phrase p2 = new Phrase("SUPERNUMERARY STR", fontTableHeading);
		Phrase p2 = new Phrase();
		
		Chunk pSUP = new Chunk("SUPERNUMERARY STR", fontTableHeading);
		pSUP.setUnderline(0.1f, -2f);
		p2.add(pSUP);
		
		PdfPTable table2 = new PdfPTable(13);
		table2.setWidths(new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2});
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		//Phrase p2 = new Phrase(Heading1);
		
		for(int t=0;t<TH.size();t++) {
			p = new Paragraph(TH.get(t),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table2.addCell(p);
		}
		
		table2.setHeaderRows(1); // table first row will be repeated in all pages

		if(list_super.size() > 0) {
			for(int i=0;i< list_super.size();i++) {
				List<String> l = list_super.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table2.addCell(p);
				}
			}
		}else {
			p = new Paragraph("No Data available");
			PdfPCell c3 = new PdfPCell(p);
			c3.setColspan(13);
			table2.addCell(c3);
		}
		
//		Phrase p3 = new Phrase("RE-EMPLOYED", fontTableHeading);
		Phrase p3 = new Phrase();
		
		Chunk pRE = new Chunk("RE-EMPLOYED", fontTableHeading);
		pRE.setUnderline(0.1f, -2f);
		p3.add(pRE);
		
		PdfPTable table3 = new PdfPTable(13);
		table3.setWidths(new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2});
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		//Phrase p3 = new Phrase(Heading2);
		
		for(int r=0;r<TH.size();r++) {
			p = new Paragraph(TH.get(r),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}
		
		table3.setHeaderRows(1); // table first row will be repeated in all pages
		
		if(list_ReEmp.size() > 0) {
			for(int i=0;i< list_ReEmp.size();i++) {
				List<String> l = list_ReEmp.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table3.addCell(p);
				}
			}
		}else {
			p = new Paragraph("No Data available");
			PdfPCell c2 = new PdfPCell(p);
			c2.setColspan(13);
			table3.addCell(c2);
		}
		
//		Phrase p4 = new Phrase("DESERTER", fontTableHeading);
		Phrase p4 = new Phrase();
		
		Chunk pDESERTER = new Chunk("DESERTER", fontTableHeading);
		pDESERTER.setUnderline(0.1f, -2f);
		p4.add(pDESERTER);
		
		PdfPTable table4 = new PdfPTable(13);
		table4.setWidths(new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2});
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		///Phrase p4 = new Phrase(Heading3);
		
		for(int k=0;k<TH.size();k++) {
			p = new Paragraph(TH.get(k),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table4.addCell(p);
		}
		
		table4.setHeaderRows(1); // table first row will be repeated in all pages
		if(list_Des.size() > 0) {
			for(int i=0;i< list_Des.size();i++) {
				List<String> l = list_Des.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table4.addCell(p);
				}
			}
		}
		else {
			p = new Paragraph("No Data available");
			PdfPCell c1 = new PdfPCell(p);
			c1.setColspan(13);
			table4.addCell(c1);
		}
		
		
		
		
		
		//Phrase p5 = new Phrase(" AUTH AS PER WE & HELD STR AS PER IAFF-3008 ", fontTableHeading);
		Phrase p5 = new Phrase();
		
//		Chunk pauth = new Chunk(" AUTH AS PER WE & HELD STR AS PER IAFF-3008 ", fontTableHeading);
		Chunk pauth = new Chunk(" AUTH AS PER WE/PE  ", fontTableHeading);
		pauth.setUnderline(0.1f, -2f);
		p5.add(pauth);
		
		PdfPTable table5 = new PdfPTable(6);
		table5.setWidths(new int[]{1,2,2,2,2,2});
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		///Phrase p4 = new Phrase(Heading3);
		
		for(int k=0;k<TH4.size();k++) {
			p = new Paragraph(TH4.get(k),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table5.addCell(p);
		}
		
		table5.setHeaderRows(1); // table first row will be repeated in all pages
		if(list_auth.size() > 0) {
			for(int i=0;i< list_auth.size();i++) {
				List<String> l = list_auth.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table5.addCell(p);
				}
			}
		}
		else {
			p = new Paragraph("No Data available");
			PdfPCell c1 = new PdfPCell(p);
			c1.setColspan(6);
			table5.addCell(c1);
		}
		// bisag v2 290822 (split auth and held)
Phrase p7 = new Phrase();
		
		Chunk pheld = new Chunk(" HELD STR AS PER IAFF-3008 ", fontTableHeading);
		pheld.setUnderline(0.1f, -2f);
		p7.add(pheld);
		
		PdfPTable table8 = new PdfPTable(3);
		table8.setWidths(new int[]{1,2,2});
		table8.setWidthPercentage(100);
		table8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table8.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		///Phrase p4 = new Phrase(Heading3);
		
		for(int k=0;k<TH6.size();k++) {
			p = new Paragraph(TH6.get(k),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table8.addCell(p);
		}
		
		table8.setHeaderRows(1); // table first row will be repeated in all pages
		if(list_held.size() > 0) {
			for(int i=0;i< list_held.size();i++) {
				List<String> l = list_held.get(i);
				for(int j = 0;j<l.size();j++) {
					p = new Paragraph(l.get(j),fontTableValue2);
					table8.addCell(p);
				}
			}
		}
		else {
			p = new Paragraph("No Data available");
			PdfPCell c1 = new PdfPCell(p);
			c1.setColspan(3);
			table8.addCell(c1);
		}
		
		/////
		
		//Phrase p5 = new Phrase(" AUTH AS PER WE & HELD STR AS PER IAFF-3008 ", fontTableHeading);
				Phrase p6 = new Phrase();
				
				Chunk psum = new Chunk("  SUMMARY ", fontTableHeading);
				psum.setUnderline(0.1f, -2f);
				p6.add(psum);
				
				PdfPTable table7 = new PdfPTable(4);
				table7.setWidths(new int[]{1,2,2,2});
				//setWidths(new int[],'{1,2,2,2,2,2,2}')
				table7.setWidthPercentage(100);
				table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table7.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				///Phrase p4 = new Phrase(Heading3);
				
				for(int k=0;k<TH5.size();k++) {
					p = new Paragraph(TH5.get(k),fontTableHeading);
					p.setAlignment(Element.ALIGN_CENTER);
					table7.addCell(p);
				}
				
				           table7.setHeaderRows(1); // table first row will be repeated in all pages
				       
								p = new Paragraph(sum_auth.toString(),fontTableValue2);
								table7.addCell(p);
								p = new Paragraph(sum_held.toString(),fontTableValue2);
								table7.addCell(p);
								p = new Paragraph(defi.toString(),fontTableValue2);
								table7.addCell(p);
								p = new Paragraph(sur.toString(),fontTableValue2);
								table7.addCell(p);
							
							
				
				
		
//		  p = new Paragraph("No Data available"); PdfPCell c1 = new PdfPCell(p);
//		  c1.setColspan(13); table7.addCell(c1);
		 
				
				///////// Remarks ///////////
								///////// Remarks ///////////
								PdfPTable tableRemarks = new PdfPTable(4);
								tableRemarks.setWidthPercentage(100);
								tableRemarks.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//								tableRemarks.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								
								///remarks 1
								PdfPCell remarkscell1;
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph("  Offr TOS since submission of last report : ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								PdfPCell remarkscell2;
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks1,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								///remarks 2
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph(" Offr SOS since submission of last report : ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks2,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								///remarks 3
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph("  Offrs under order of posting out :  ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks3,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								///remarks 4
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph("  Offrs under order of posting in :  ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks4,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								
								
								///remarks 5
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph(" Offrs under order of Retirement / Release/SSC Release : ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks5,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								
								
								///remarks 6
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph("  Remarks : ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks6,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
								
								
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								tableRemarks.addCell(new Phrase("\n"));
								
								
								
								///remarks 7
								remarkscell1 = new PdfPCell();
								remarkscell1.setBorder(Rectangle.NO_BORDER);
								p = new Paragraph("  Distr : ",fontTableHeading);
								p.setAlignment(Element.ALIGN_TOP);
								remarkscell1.addElement(p);
								
								tableRemarks.addCell(remarkscell1);
								
								remarkscell2 = new PdfPCell();
								remarkscell2.setBorder(Rectangle.BOX);
								remarkscell2.setColspan(3);
								p = new Paragraph(hd_remarks7,fontTableValue);
								p.setAlignment(Element.ALIGN_JUSTIFIED);
								remarkscell2.addElement(p);
								
								tableRemarks.addCell(remarkscell2);
			
			
			
		
		
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
//		cell1.addElement(new Phrase("\n")); 
//		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(tableseal);
////		cell1.addElement(tablesealS);
////		cell1.addElement(new Phrase("Unit Seal:                  I certified that all known personal occurences have been reported on IAAF-3008."+"                                                                              "+"(___________)"+"                                                     "+"                                                       Sign of Officer",fontTableHeading));
//		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p2);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table2);
		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(tableseal);
//		cell1.addElement(tablesealS);
//		cell1.addElement(new Phrase("Unit Seal : It Is certified that all known personal occurences have been reported on IAAF-3008."+"                                                                                           "+"Sign Of Officer(___________)",fontTableHeading));
//		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p3);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table3);
		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(tableseal);
//		cell1.addElement(tablesealS);
//		cell1.addElement(new Phrase("Unit Seal : It Is certified that all known personal occurences have been reported on IAAF-3008."+"                                                                                           "+"Sign Of Officer(___________)",fontTableHeading));
//		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p4);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table4);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p5);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table5);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p7);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table8);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(p6);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table7);
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(tableRemarks);
		//cell1.addElement(new Phrase("\n"));
//		cell1.addElement(new Phrase("\n"));
//		cell1.addElement(tableseal);
////		cell1.addElement(tablesealS);
////		cell1.addElement(new Phrase("UnitSeal: It Is certified that all known personal occurences have been reported on IAAF-3008."+"                                                                                           "+"Sign Of Officer(___________)",fontTableHeading));
//		cell1.addElement(new Phrase("\n"));
		//cell1.addElement(p5);
		//cell1.addElement(table5);
		//cell1.addElement(new Phrase("\n"));
		//cell1.addElement(p5);
		//table.setTableEvent(new ImageBackgroundEvent(request));
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
//			String watermark = "";
			Image img = null;

			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
					BufferedImage.TYPE_INT_ARGB);

			Graphics graphics = bufferedImage.getGraphics();
			
			Color myColor = new Color(200, 200, 200);
			graphics.setColor(myColor);
			graphics.setFont(new java.awt.Font("Arial", Font.UNDEFINED, 11));

//			graphics.setColor(Color.white);
//			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));

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
		PdfTemplate total;PdfTemplate total1;
		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(10, 15);total1 = writer.getDirectContent().createTemplate(10,15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void onOpenDocument(PdfWriter writer, Document document) {
			// total = writer.getDirectContent().createTemplate(30, 12);
		}
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
		public void onCloseDocument(PdfWriter writer, Document document) {
			Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1); 
			ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
			ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber() - 1),fontTableHeading1), 2, 2, 0);
		}
	}
	
}

