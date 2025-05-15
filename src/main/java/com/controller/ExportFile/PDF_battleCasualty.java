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

public class PDF_battleCasualty extends AbstractPdfView {
	
	String Heading = "";String foot = "";
	String username = "";	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	ArrayList<ArrayList<String>> li2;
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public PDF_battleCasualty(String Heading,ArrayList<ArrayList<String>> list2,String foot,String username){
		this.Heading = Heading;this.foot=foot;this.username=username;
		this.li2=list2;
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
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		
		PdfPTable table1=new PdfPTable(4);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		Chunk underline1 = new Chunk("DETAILED REPORT (BATTLE CASUALTY/PHYSICAL CASUALTY)");
//		underline1.setUnderline(0.1f, -2f);
//		Paragraph p1=new Paragraph("                        "+underline1,fontTableHeading1);
//		p1.setAlignment(Element.ALIGN_CENTER);
//		PdfPCell c50=new PdfPCell(p1);
//		c50.setBorder(Rectangle.NO_BORDER);
//		c50.setColspan(4);
//		table1.addCell(c50);

		
		///////////////Start Table///////////
		
//		Paragraph M1= new Paragraph("1.Refer to AO 01/2003/MP and this unit initial report No "+li2.get(0).get(0)+" at 19 Feb 2022.",fontTableValue);
//		document.add(M1);
//		document.add(new Phrase("\n"));
//		Paragraph M2= new Paragraph("2. Detailed report of death case in respect the deceased soldier is fwd here with in the succeeding paras.",fontTableValue);
//		document.add(M2);
		
		Paragraph pl;

		Paragraph plv;

		
		
		PdfPTable tableleft = new PdfPTable(2);

		tableleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		Chunk underline = new Chunk("1. Personal Details.", fontTableHeading);
		underline.setUnderline(0.1f, -2f);
		Phrase p = new Phrase(underline);

		tableleft.addCell(p);
	

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("  (a)  Army No with Suffix :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(0), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (b) Rank:", fontTableValue);

		tableleft.addCell(pl);
		
		plv = new Paragraph(li2.get(0).get(2),fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("     (i)  Substantive with Date :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(3), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("     (ii) Acting with Date :", fontTableValue);

		tableleft.addCell(pl);
		
		plv = new Paragraph(" ",fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		

		pl = new Paragraph("  (c) Full Name:", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(1), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (d) Unit:", fontTableValue);

		tableleft.addCell(pl);
		
		plv = new Paragraph(li2.get(0).get(4), fontTableValue);

		tableleft.addCell(plv);
		

		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (e)Arms/Sercice:", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(6), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		pl = new Paragraph("  (f) Regt Corps:", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (g) Religion :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(7), fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		
		Chunk underline1 = new Chunk("2. Casualty Details.", fontTableHeading);
		underline1.setUnderline(0.1f, -2f);
		Phrase p1 = new Phrase(underline1);

		tableleft.addCell(p1);
	

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		

//		pl = new Paragraph("2. Casualty Details.", fontTableHeading);
//
//		tableleft.addCell(pl);
//		
//		plv = new Paragraph(" ",fontTableValue);
//
//		tableleft.addCell(plv);
//		
		
		pl = new Paragraph("  (a) Date :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(8), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("  (b) Time of Casualty :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(9), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("  (c) Recommeded For :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(10), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("  (d) Nature of Casualty :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(11), fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (e) Location :", fontTableValue);

		tableleft.addCell(pl);
		
		plv = new Paragraph(" ",fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (i)     On duty or otherwise (Specify) :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(12), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (ii)    State :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(14), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (iii)   District :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(15), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (iv)   Area/Post/Village/Town :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(17), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (v)    Operation :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(18), fontTableValue);

		tableleft.addCell(plv);

		
		
		pl = new Paragraph("      (vi)   Sector(Under location falls) :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(19), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (vii)  Peace/Fd/CI Area/CT :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(20), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (viii) On International Boundary (IB) \n                Line of Control(LC)/Line of Actual \n                Control(LAC)/Actual Ground Position \n                Line(AGPL)", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(37), fontTableValue);

		tableleft.addCell(plv);
		
	
		
		
		pl = new Paragraph("      (ix)   Brigade :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(21), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (x)    Division :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(22), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (xi)   Crops :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(23), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (xii)  Command :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(24), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (xiii) Hospital name :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(25), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (xiv)  Location of Hospital :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(26), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		pl = new Paragraph("  (f) Cause of Casualty :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(27), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (g) Brief descriptionof circumstances \n        Leading to Casualty in One Para", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(28), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (h) Diagnosis :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(29), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (j) In Aid to Civil Power :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(30)+"(YES/NO TO BE GIVEN) ", fontTableValue);

		tableleft.addCell(plv);

		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		Chunk underline2 = new Chunk("3. NOK Details.", fontTableHeading);
		underline2.setUnderline(0.1f, -2f);
		Phrase p2 = new Phrase(underline2);

		tableleft.addCell(p2);
	

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		
//		pl = new Paragraph("3 NOK Details.", fontTableHeading);
//
//		tableleft.addCell(pl);
//		
//		plv = new Paragraph(" ",fontTableValue);
//
//		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("  (a) Name of NOK :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(31), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("  (b) Relationship :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(32), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("  (c) Permenant Address :", fontTableValue);

		tableleft.addCell(pl);
		
		plv = new Paragraph(" ",fontTableValue);

		tableleft.addCell(plv);

		
		pl = new Paragraph("      (i)    District :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(34), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (ii)   State :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(33), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (iii)  PIN Code :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(35), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (iv)  Mobile/Tele No :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(36), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (v)   Email ID :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(32), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (d) Present Address of NOK :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(" ",fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (i)    District :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(34), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (ii)   State :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(33), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (iii)  PIN Code :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(35), fontTableValue);

		tableleft.addCell(plv);
		
		pl = new Paragraph("      (iv)  Mobile/Tele No :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(36), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("      (v)   Email ID :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(32), fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		pl = new Paragraph("", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("  (e) Whether NOK Informed or Not (Yes/NO) :", fontTableValue);

		tableleft.addCell(pl);

		plv = new Paragraph(li2.get(0).get(38),fontTableValue);

		tableleft.addCell(plv);
		
		
		
		
		table1.addCell("");
		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table2.addCell(tableleft);
		table2.addCell(table1);
		table2.setTableEvent(new ImageBackgroundEvent(request));
		document.add(table2);
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


