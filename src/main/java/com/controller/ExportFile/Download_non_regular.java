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

import org.hsqldb.rights.Right;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;

import com.itextpdf.text.TabStop.Alignment;

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

public class Download_non_regular extends AbstractPdfView {

	String Type = "";

	String Heading = "";

	String username = "";

	
	String foot = "";

	String peace_month = "";

	final static String USER_PASSWORD = "user";

	List<Map<String, Object>> listSH;

	

	final static String OWNER_PASSWORD = "owner";

	
	public Download_non_regular(String Type, String Heading, String username, String foot,List<Map<String, Object>> listSH) {

		this.Type = Type;
		this.Heading = Heading;
		this.username = username;

		
		this.peace_month = peace_month;
		this.foot = foot;
		this.listSH = listSH;
	

	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		document.open();

		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);

		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);

		Image logo = null;

		try {

			@SuppressWarnings("deprecation")

			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "indianarmy_smrm5aaa.jpg";

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

			String indian_Army = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "miso"
					+ File.separator + "images" + File.separator + "dgis-logo_new.png";

			logo2 = Image.getInstance(indian_Army);// "d://indianarmy_smrm5aaa.jpg"

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

		Chunk underline2 = new Chunk("SERVICE RECORD", fontTableHeadingMainHead);

		underline2.setUnderline(0.1f, -2f);

		// Chunk underline4 = new Chunk(MainHead, fontTableHeadingSubMainHead);

		
		Chunk underline3 = new Chunk("CIVILIAN", fontTableHeadingMainHead);

		underline3.setUnderline(0.1f, -2f);
		
		
		Chunk underline4 = new Chunk("NON REGULAR EMPLOYEE", fontTableHeadingMainHead);

		underline4.setUnderline(0.1f, -2f);
		
		
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
		
	
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add(underline4);
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
		String foot =" Record Generated on " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		table3.setWidthPercentage(120);

		p2.add(table3);

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

		

		if (Type.equals("L")) {

			document.setPageSize(PageSize.A4); // set document landscape

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

		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")

		List<Map<String, Object>> aList = new ArrayList<Map<String, Object>>();

		aList = (List<Map<String, Object>>) model.get("userList");

		Font fontTableheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);

		Font fontTableHeader1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 12);

		/// Chunk glue = new Chunk(new VerticalPositionMark());

		PdfPTable table111 = new PdfPTable(1);

		table111.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		table111.setWidthPercentage(100);

		Paragraph p;

		Paragraph header;

		Paragraph header1;

		PdfPTable tablesleft = new PdfPTable(5);

		tablesleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		
		PdfPCell wfg1 = new PdfPCell(new Phrase("\n"));

		wfg1.setColspan(4);

		wfg1.setBorder(Rectangle.NO_BORDER);

		tablesleft.addCell(wfg1);

		PdfPCell wfg = new PdfPCell(new Paragraph(" ", fontTableheader));

		wfg.setColspan(4);

		wfg.setBorder(Rectangle.NO_BORDER);

		tablesleft.addCell(wfg);

		table111.addCell(tablesleft);

		Paragraph pl;

		Paragraph plv;

		PdfPTable tableleft = new PdfPTable(2);

		tableleft.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		 if (aList.get(0).get("non_effective") == null) {
				
				
				pl = new Paragraph("SERVING STATUS", fontTableHeading);

				tableleft.addCell(pl);

				plv = new Paragraph("SERVING", fontTableValue);

				tableleft.addCell(plv);
				
			
			
			}
		
		 if (aList.get(0).get("non_effective") != null) {
				
			 
				pl = new Paragraph("SERVING STATUS", fontTableHeading);

				tableleft.addCell(pl);

				plv = new Paragraph("NON EFFECTIVE", fontTableValue);

				tableleft.addCell(plv);
				
			
			
			}

		 pl = new Paragraph("\n", fontTableHeading);

		 tableleft.addCell(pl);

			plv = new Paragraph("\n", fontTableValue);

			tableleft.addCell(plv);

		pl = new Paragraph("Employee No. :", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("employee_no").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Unit Sus No. :", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("sus_no").toString(), fontTableValue);

		tableleft.addCell(plv);

		

		pl = new Paragraph("Authority :", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("authority").toString(), fontTableValue);

		tableleft.addCell(plv);

		

		pl = new Paragraph("Date of Birth:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("dob").toString(), fontTableValue);

		tableleft.addCell(plv);

		

		pl = new Paragraph("Category:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("category_belongs").toString(), fontTableValue);

		tableleft.addCell(plv);

		

		pl = new Paragraph("Pay Head:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("pay_level").toString(), fontTableValue);

		tableleft.addCell(plv);
		 
		pl = new Paragraph("Relegion:", fontTableHeading);

		tableleft.addCell(pl);
		
		if (listSH.get(0).get("Relegion") != null) {
		plv = new Paragraph(aList.get(0).get("religion").toString(), fontTableValue);

		tableleft.addCell(plv);
		}
		else {
			plv = new Paragraph("", fontTableValue);

			tableleft.addCell(plv);
		}
		
		pl = new Paragraph("Aadhaar No:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("aadhar_card").toString(), fontTableValue);

		tableleft.addCell(plv);
		
		
		 pl = new Paragraph("\n", fontTableHeading);
		
		 tableleft.addCell(pl);
		
		plv = new Paragraph("\n", fontTableValue);
		
		tableleft.addCell(plv);
			
		pl = new Paragraph("ORGINAL ", fontTableHeader1);

		tableleft.addCell(pl);

		plv = new Paragraph("DOMICILE", fontTableHeader1);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("Country:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("country_original").toString(), fontTableValue);

		tableleft.addCell(plv);

		
		
		pl = new Paragraph("District:", fontTableHeading);

		tableleft.addCell(pl);
		if (listSH.get(0).get("district_original") != null) {
		plv = new Paragraph(aList.get(0).get("district_original").toString(), fontTableValue);

		tableleft.addCell(plv);
		}else {
			pl = new Paragraph("\n", fontTableHeading);
			
			 tableleft.addCell(pl);
		}
		
		 pl = new Paragraph("\n", fontTableHeading);
			
		 tableleft.addCell(pl);
		
		plv = new Paragraph("\n", fontTableValue);
		
		tableleft.addCell(plv);
		
		
		pl = new Paragraph("PRESENT ", fontTableHeader1);

		tableleft.addCell(pl);

		plv = new Paragraph("DOMICILE", fontTableHeader1);

		tableleft.addCell(plv);
		
		
		
		pl = new Paragraph("", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph("", fontTableValue);

		tableleft.addCell(plv);
		
		
		pl = new Paragraph("Country:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("country_present").toString(), fontTableValue);

		tableleft.addCell(plv);

		
		
		pl = new Paragraph("District:", fontTableHeading);

		tableleft.addCell(pl);
		if (listSH.get(0).get("district_present") != null) {
		plv = new Paragraph(aList.get(0).get("district_present").toString(), fontTableValue);

		tableleft.addCell(plv);
		}
		else {
			plv = new Paragraph("", fontTableValue);

			tableleft.addCell(plv);
		}
		 pl = new Paragraph("\n", fontTableHeading);
			
		 tableleft.addCell(pl);
		
		plv = new Paragraph("\n", fontTableValue);
		
		tableleft.addCell(plv);
		
		//if (aList.get(0).get("district_present").toString().equals(null)) {
			 if (aList.get(0).get("non_effective") == null) {
		
		
			pl = new Paragraph("Non Effective:", fontTableHeading);

			tableleft.addCell(pl);

			plv = new Paragraph("No", fontTableValue);

			tableleft.addCell(plv);
			
		
		
		}
			 else {
				 
				 if (aList.get(0).get("non_effective") != null) {
				 
				 pl = new Paragraph("Non Effective:", fontTableHeading);

					tableleft.addCell(pl);

					plv = new Paragraph("Yes", fontTableValue);

					tableleft.addCell(plv);
				 
				 }
				 pl = new Paragraph("Cause of Non Effective:", fontTableHeading);

					tableleft.addCell(pl);

					plv = new Paragraph(aList.get(0).get("non_effective").toString(), fontTableValue);

					tableleft.addCell(plv);
					
					
					 pl = new Paragraph("Non Effective Date:", fontTableHeading);

						tableleft.addCell(pl);

						plv = new Paragraph(aList.get(0).get("date_non_effective").toString(), fontTableValue);

						tableleft.addCell(plv);
			 }


		

		Paragraph pr;

		Paragraph prv;

		PdfPTable tableright = new PdfPTable(2);

		tableright.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//		PdfPCell pi6 = new PdfPCell(new Paragraph(" ", fontTableheader));
//		pi6.setColspan(2);
//		pi6.setBorder(Rectangle.NO_BORDER);
//		tableright.addCell(pi6);
		
		
		pl = new Paragraph("\n", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph("\n", fontTableValue);

		tableright.addCell(plv);

		pl = new Paragraph("\n", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph("\n", fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("Employee Name:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("first_name").toString()+aList.get(0).get("middle_name").toString()+aList.get(0).get("last_name").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
	


		pl = new Paragraph("Unit Posted To:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("unit_name").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("Date of Authority :", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("dt_of_authority").toString(), fontTableValue);

		tableright.addCell(plv);
		

		
		pl = new Paragraph("Gender:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("gender").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("Type :", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("civ_type").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("Date of Joining:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("joining_date_gov_service").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("\n", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph("\n", fontTableValue);

		tableright.addCell(plv);
		
		
		
		
		pl = new Paragraph("Mother Tongue:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("mother_tongue").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("PAN No:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("pan_no").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		
		pl = new Paragraph("\n", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph("\n", fontTableValue);

		tableright.addCell(plv);
		
		
		
		 pl = new Paragraph("\n", fontTableHeading);
			
		 tableright.addCell(pl);
		
		plv = new Paragraph("\n", fontTableValue);
		
		tableright.addCell(plv);
		
		
		pl = new Paragraph("State:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("state_original").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		pl = new Paragraph("Tehsil:", fontTableHeading);

		tableright.addCell(pl);
		 if (aList.get(0).get("tehsil_origin") != null) {
		plv = new Paragraph(aList.get(0).get("tehsil_origin").toString(), fontTableValue);

		tableright.addCell(plv);

		 }
		 else {
			 plv = new Paragraph("", fontTableValue);

				tableright.addCell(plv);
		 }
		pl = new Paragraph("\n", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph("\n", fontTableValue);

		tableright.addCell(plv);
		
		 pl = new Paragraph("\n", fontTableHeading);
			
		 tableright.addCell(pl);
		
		plv = new Paragraph("\n", fontTableValue);
		
		tableright.addCell(plv);
		
		
		pl = new Paragraph("State:", fontTableHeading);

		tableright.addCell(pl);

		plv = new Paragraph(aList.get(0).get("state_present").toString(), fontTableValue);

		tableright.addCell(plv);
		
		
		pl = new Paragraph("Tehsil:", fontTableHeading);

		tableright.addCell(pl);
		 if (aList.get(0).get("tehsil_present") != null) {
		plv = new Paragraph(aList.get(0).get("tehsil_present").toString(), fontTableValue);

		tableright.addCell(plv);
		 }else {
			 plv = new Paragraph("", fontTableValue);

				tableright.addCell(plv);
		 }
		 
		/*
		 * pr = new Paragraph("\nName :", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph("\n"+aList.get(0).get("name").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of Rank:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("date_of_rank").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Commission Type:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("comn_name").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of Seniority:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("date_of_seniority").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of Appointment:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("date_of_appointment").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of TOS:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("dt_of_tos").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("CDA(O) A/C No:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("cda_acc_no").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Religion :", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("religion_name").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("State:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("state_name").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Aadhaar No:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("adhar_number").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Height:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("height").toString(), fontTableValue);
		 * 
		 * tableright.addCell(prv); pr = new Paragraph("Old Personal No:",
		 * fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("old_personal_no").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Marital Status:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("marital_name").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of I Card Issued:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("issue_dt").toString(), fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Mobile No:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new Paragraph(aList.get(0).get("mobile_no").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * pr = new Paragraph("Date of Permanent Commission:", fontTableHeading);
		 * 
		 * tableright.addCell(pr);
		 * 
		 * prv = new
		 * Paragraph(aList.get(0).get("date_of_permanent_commission").toString(),
		 * fontTableValue);
		 * 
		 * tableright.addCell(prv);
		 * 
		 * header1 = new Paragraph("Medical Category", fontTableHeader1);
		 */

		/*
		 * PdfPTable table0 = new PdfPTable(listSH.size());
		 * 
		 * table0.setWidthPercentage(100);
		 * 
		 * table0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 * 
		 * table0.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE); PdfPCell
		 * pi4 = new PdfPCell(); //pi4.addElement(header1);
		 * pi4.setColspan(listSH.size()); pi4.setBorder(Rectangle.NO_BORDER);
		 * table0.addCell(pi4);
		 * 
		 * PdfPCell pi10= new PdfPCell(); pi10.addElement(new Paragraph("      "));
		 * pi10.setColspan(listSH.size()); pi10.setBorder(Rectangle.NO_BORDER);
		 * table0.addCell(pi10);
		 */
		
	

		
		 

		PdfPTable tablecenter = new PdfPTable(2);

		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.setWidthPercentage(100);

		tablecenter.addCell(tableleft);

		tablecenter.addCell(tableright);

		table111.addCell(tablecenter);

		//table111.addCell(table0);

		table111.addCell(new Phrase("\n"));

	
		

		PdfPTable table1 = new PdfPTable(1);

		table1.setWidthPercentage(100);

		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell1;

		cell1 = new PdfPCell();

		cell1.setBorder(Rectangle.NO_BORDER);

		cell1.addElement(new Phrase("\n"));

		cell1.addElement(table111);

		cell1.addElement(new Phrase("\n"));

		/*
		 * cell1.addElement(table);
		 * 
		 * 
		 * 
		 * cell1.addElement(new Phrase("\n"));
		 */

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

					first = 650;
				}

				if (tableWidth == 770) {

					first = 450;

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
