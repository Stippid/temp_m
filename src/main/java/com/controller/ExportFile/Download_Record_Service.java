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

public class Download_Record_Service extends AbstractPdfView {

	String Type = "";

	String Heading = "";

	String username = "";

	List<String> TH;

	List<String> TH1;

	List<String> TH2;

	List<String> TH3;

	List<String> TH4;

	List<String> TH5;

	List<String> TH6;

	List<String> TH7;

	List<String> TH8;

	List<String> TH9;

	List<String> TH10;

	List<String> TH11;

	List<String> TH12;

	List<String> TH13;

	List<String> TH14;

	List<String> TH15;

	String foot = "";

	String peace_month = "";

	final static String USER_PASSWORD = "user";

	List<Map<String, Object>> listSH;

	List<Map<String, Object>> listCO;

	List<Map<String, Object>> listTR;

	List<Map<String, Object>> listFM;

	List<Map<String, Object>> listS;

	List<Map<String, Object>> listCHILD;

	List<Map<String, Object>> listAM;

	List<Map<String, Object>> listTENU;

	List<Map<String, Object>> listRegim;

	List<Map<String, Object>> listFIS;

	List<Map<String, Object>> listARM;

	List<Map<String, Object>> listPE;

	List<Map<String, Object>> listAQ;

	List<Map<String, Object>> listPTQ;

	List<Map<String, Object>> listIL;

	List<Map<String, Object>> listFL;

	List<Map<String, Object>> listF;

	List<Map<String, Object>> listB;

	List<Map<String, Object>> listFS;

	List<Map<String, Object>> listBA;

	List<Map<String, Object>> listSE;

	List<Map<String, Object>> listORM;

	List<Map<String, Object>> listPDO;

	List<Map<String, Object>> listPM;

	List<Map<String, Object>> listPS;

	List<Map<String, Object>> listNOK;

	List<Map<String, Object>> listNA;

	List<Map<String, Object>> listAR;

	List<Map<String, Object>> listTENU_T;

	List<Map<String, Object>> listRD;

	String serving_status;

	final static String OWNER_PASSWORD = "owner";

	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	public Download_Record_Service(String Type, String Heading, String username, String foot,

			List<String> TH, List<String> TH1, List<String> TH2, List<String> TH3, List<String> TH4, List<String> TH5,
			List<String> TH6, List<String> TH7,

			List<String> TH8, List<String> TH9, List<String> TH10, List<String> TH11, List<String> TH12,
			List<String> TH13, List<String> TH14, List<String> TH15,

			List<Map<String, Object>> listSH, List<Map<String, Object>> listCO, List<Map<String, Object>> listTR,
			List<Map<String, Object>> listFM,

			List<Map<String, Object>> listS, List<Map<String, Object>> listCHILD, List<Map<String, Object>> listAM,
			List<Map<String, Object>> listTENU,

			List<Map<String, Object>> listRegim, List<Map<String, Object>> listFIS, List<Map<String, Object>> listARM,
			List<Map<String, Object>> listPE,

			List<Map<String, Object>> listAQ, List<Map<String, Object>> listPTQ, List<Map<String, Object>> listIL,
			List<Map<String, Object>> listFL,

			List<Map<String, Object>> listF, List<Map<String, Object>> listB, List<Map<String, Object>> listFS,
			List<Map<String, Object>> listBA,

			List<Map<String, Object>> listSE, List<Map<String, Object>> listORM, List<Map<String, Object>> listPDO,
			List<Map<String, Object>> listPM,

			List<Map<String, Object>> listPS, List<Map<String, Object>> listNOK, List<Map<String, Object>> listNA,
			List<Map<String, Object>> listAR

			, List<Map<String, Object>> listTENU_T, String peace_month, List<Map<String, Object>> listRD,
			String serving_status) {

		this.Type = Type;
		this.Heading = Heading;
		this.username = username;

		this.TH = TH;
		this.TH1 = TH1;
		this.TH2 = TH2;
		this.TH3 = TH3;
		this.TH4 = TH4;
		this.TH5 = TH5;
		this.TH6 = TH6;
		this.TH7 = TH7;
		this.TH8 = TH8;
		this.TH9 = TH9;

		this.TH10 = TH10;
		this.TH11 = TH11;
		this.TH12 = TH12;
		this.TH13 = TH13;
		this.TH14 = TH14;
		this.TH15 = TH15;

		this.listSH = listSH;
		this.listCO = listCO;
		this.listTR = listTR;
		this.listFM = listFM;
		this.listS = listS;
		this.listCHILD = listCHILD;
		this.listAM = listAM;

		this.listTENU = listTENU;
		this.listRegim = listRegim;
		this.listFIS = listFIS;
		this.listARM = listARM;
		this.listPE = listPE;
		this.listAQ = listAQ;
		this.listPTQ = listPTQ;

		this.listIL = listIL;
		this.listFL = listFL;
		this.listF = listF;
		this.listB = listB;
		this.listFS = listFS;
		this.listBA = listBA;
		this.listSE = listSE;
		this.listORM = listORM;

		this.listPDO = listPDO;
		this.listPM = listPM;
		this.listPS = listPS;
		this.listNOK = listNOK;
		this.listNA = listNA;
		this.listAR = listAR;
		this.listTENU_T = listTENU_T;

		this.peace_month = peace_month;
		this.foot = foot;

		this.listRD = listRD;

		this.serving_status = serving_status;

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

		PdfPTable table3 = new PdfPTable(3);

		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase p = new Phrase(underline);

		p.add("\n");

		p.add(underline2);

		p.add("\n");

		// p.add(underline4);

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

		PdfPCell pi = new PdfPCell();

		pi.addElement(new Paragraph("SERVING STATUS:", fontTableHeading));

		pi.setBorder(Rectangle.NO_BORDER);

		pi.setColspan(2);

		tablesleft.addCell(pi);

		PdfPCell pi1 = new PdfPCell();

		pi1.addElement(new Paragraph(serving_status, fontTableValue));

		pi1.setColspan(2);

		pi1.setBorder(Rectangle.NO_BORDER);

		tablesleft.addCell(pi1);

		PdfPCell cellimage = new PdfPCell();

		cellimage.setRowspan(3);

		cellimage.setBorder(Rectangle.NO_BORDER);

		try {

			Image idimage = Image.getInstance(aList.get(0).get("identity_image").toString());

			idimage.scaleAbsoluteHeight(50);

			idimage.scaleAbsoluteWidth(50);

			cellimage.addElement(idimage);

			cellimage.setVerticalAlignment(Rectangle.LEFT);

		}

		catch (Exception e) {

			String no_imagestr = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "img"
					+ File.separator + "No_Image.jpg";

			Image no_image = Image.getInstance(no_imagestr);

			no_image.scaleAbsoluteHeight(50);

			no_image.scaleAbsoluteWidth(50);

			cellimage.addElement(no_image);

		}

		tablesleft.addCell(cellimage);

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

		PdfPCell pi5 = new PdfPCell(new Paragraph("PERSONAL DETAILS", fontTableheader));
		pi5.setColspan(2);
		pi5.setBorder(Rectangle.NO_BORDER);
		tableleft.addCell(pi5);
		pl = new Paragraph("\nPersonnel No:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph("\n"+aList.get(0).get("personnel_no").toString(), fontTableValue);

		tableleft.addCell(plv);
		pl = new Paragraph("Rank:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("rank").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Regiment/Corps:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("regiment").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Date of Commission:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("date_of_commission").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Appointment:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("appointment").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Unit Name:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("unit_name").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Date of Birth:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("date_of_birth").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Gender:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("gender_name").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Nationality:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("nationality_name").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Mother Tongue:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("mothertounge").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Blood Group:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("blood_desc").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Date of Medical Category:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("medical").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("I Card :", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("id_card_no").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("Email:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("gmail").toString(), fontTableValue);

		tableleft.addCell(plv);

		pl = new Paragraph("NIC mail:", fontTableHeading);

		tableleft.addCell(pl);

		plv = new Paragraph(aList.get(0).get("nic_mail").toString(), fontTableValue);

		tableleft.addCell(plv);

		Paragraph pr;

		Paragraph prv;

		PdfPTable tableright = new PdfPTable(2);

		tableright.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell pi6 = new PdfPCell(new Paragraph(" ", fontTableheader));
		pi6.setColspan(2);
		pi6.setBorder(Rectangle.NO_BORDER);
		tableright.addCell(pi6);
		
		pr = new Paragraph("\nName :", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph("\n"+aList.get(0).get("name").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of Rank:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("date_of_rank").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Commission Type:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("comn_name").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of Seniority:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("date_of_seniority").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of Appointment:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("date_of_appointment").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of TOS:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("dt_of_tos").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("CDA(O) A/C No:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("cda_acc_no").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Religion :", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("religion_name").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("State:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("state_name").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Aadhaar No:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("adhar_number").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Height:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("height").toString(), fontTableValue);

		tableright.addCell(prv);
		pr = new Paragraph("Old Personal No:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("old_personal_no").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Marital Status:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("marital_name").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of I Card Issued:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("issue_dt").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Mobile No:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("mobile_no").toString(), fontTableValue);

		tableright.addCell(prv);

		pr = new Paragraph("Date of Permanent Commission:", fontTableHeading);

		tableright.addCell(pr);

		prv = new Paragraph(aList.get(0).get("date_of_permanent_commission").toString(), fontTableValue);

		tableright.addCell(prv);
		
		header1 = new Paragraph("Medical Category", fontTableHeader1);

		PdfPTable table0 = new PdfPTable(listSH.size());

		table0.setWidthPercentage(100);

		table0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table0.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		PdfPCell pi4 = new PdfPCell();
		pi4.addElement(header1);
		pi4.setColspan(listSH.size());
		pi4.setBorder(Rectangle.NO_BORDER);
		table0.addCell(pi4);
		
		PdfPCell pi10= new PdfPCell();
		pi10.addElement(new Paragraph("      "));
		pi10.setColspan(listSH.size());
		pi10.setBorder(Rectangle.NO_BORDER);
		table0.addCell(pi10);
		
		if (listSH.size() > 0) {

			for (Map<String, Object> featureService : listSH) {

				for (Map.Entry<String, Object> entry : featureService.entrySet()) {


					p = new Paragraph(entry.getValue().toString(), fontTableValue);

					table0.addCell(p);

				}

			}

		} else {

			p = new Paragraph("No Data available");

			PdfPCell c0 = new PdfPCell(p);

			c0.setHorizontalAlignment(Element.ALIGN_CENTER);

			table0.addCell(c0);

		}

		PdfPTable table00 = new PdfPTable(listCO.size());

		table00.setWidthPercentage(100);

		table00.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table00.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		if (listCO.size() > 0) {

			for (Map<String, Object> featureService : listCO) {

				for (Map.Entry<String, Object> entry : featureService.entrySet()) {

					p = new Paragraph(entry.getValue().toString(), fontTableValue);

					table00.addCell(p);

				}

			}

		} else {

			p = new Paragraph("No Data available");

			PdfPCell c0 = new PdfPCell(p);

			c0.setHorizontalAlignment(Element.ALIGN_CENTER);

			table00.addCell(c0);

		}

		PdfPTable tablecenter = new PdfPTable(2);

		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		tablecenter.setWidthPercentage(100);

		tablecenter.addCell(tableleft);

		tablecenter.addCell(tableright);

		table111.addCell(tablecenter);

		table111.addCell(table0);

		table111.addCell(new Phrase("\n"));

		table111.addCell(table00);

		table111.addCell(new Phrase("\n"));


		if (listTR.size() > 0) {

			header = new Paragraph("TRAINING DETAILS", fontTableheader);

			table111.addCell(header);

			table111.addCell(new Phrase("\n"));

			PdfPTable tableleftTR = new PdfPTable(2);

			tableleftTR.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			pl = new Paragraph("Cadet No:", fontTableHeading);

			tableleftTR.addCell(pl);

			plv = new Paragraph(listTR.get(0).get("cadet_no").toString(), fontTableValue);

			tableleftTR.addCell(plv);

			pl = new Paragraph("Battalion:", fontTableHeading);

			tableleftTR.addCell(pl);

			plv = new Paragraph(listTR.get(0).get("battalion_name").toString(), fontTableValue);

			tableleftTR.addCell(plv);

			pl = new Paragraph("Course:", fontTableHeading);

			tableleftTR.addCell(pl);

			plv = new Paragraph(listTR.get(0).get("course_name").toString(), fontTableValue);

			tableleftTR.addCell(plv);

			pl = new Paragraph("Type of Entry:", fontTableHeading);

			tableleftTR.addCell(pl);

			plv = new Paragraph(listTR.get(0).get("type_of_entry").toString(), fontTableValue);

			tableleftTR.addCell(plv);

			PdfPTable tablerightTR = new PdfPTable(2);

			tablerightTR.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			pr = new Paragraph("Name of Institution :", fontTableHeading);

			tablerightTR.addCell(pr);

			prv = new Paragraph(listTR.get(0).get("institute_name").toString(), fontTableValue);

			tablerightTR.addCell(prv);

			pr = new Paragraph("Coy:", fontTableHeading);

			tablerightTR.addCell(pr);

			prv = new Paragraph(String.valueOf(listTR.get(0).get("company_name")), fontTableValue);

			tablerightTR.addCell(prv);

			pr = new Paragraph("Course No:", fontTableHeading);

			tablerightTR.addCell(pr);

			prv = new Paragraph(listTR.get(0).get("batch_no").toString(), fontTableValue);

			tablerightTR.addCell(prv);

			pr = new Paragraph("Pre Cadet Status:", fontTableHeading);

			tablerightTR.addCell(pr);

			prv = new Paragraph(listTR.get(0).get("pre_cadet_status").toString(), fontTableValue);

			tablerightTR.addCell(prv);

			PdfPTable tablecenterTR = new PdfPTable(2);

			tablecenterTR.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterTR.setWidthPercentage(100);

			tablecenterTR.addCell(tableleftTR);

			tablecenterTR.addCell(tablerightTR);

			table111.addCell(tablecenterTR);

			table111.addCell(new Phrase("\n"));


		}

		if (listFM.size() > 0) {

			header = new Paragraph("FAMILY DETAILS", fontTableheader);

			

			PdfPTable tableleftFM = new PdfPTable(2);
			tableleftFM.setWidthPercentage(100);
			tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//ADDED BY BISAG-DELHI 20/10/2021
			tableleftFM.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(2);

			pi2.setBorder(Rectangle.NO_BORDER);
			pi2.setPadding(0);
			pi2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			tableleftFM.addCell(pi2);
			
			pl = new Paragraph("\nFather's Name:", fontTableHeading);
			pl.setAlignment(Element.ALIGN_CENTER);

			tableleftFM.addCell(pl);

			plv = new Paragraph("\n"+listFM.get(0).get("father_name").toString(), fontTableValue);
			plv.setAlignment(Element.ALIGN_CENTER);

			tableleftFM.addCell(plv);

			pl = new Paragraph("Mother's Name:", fontTableHeading);
			pl.setAlignment(Element.ALIGN_CENTER);

			tableleftFM.addCell(pl);

			plv = new Paragraph(listFM.get(0).get("mother_name").toString(), fontTableValue);
			plv.setAlignment(Element.ALIGN_CENTER);

			tableleftFM.addCell(plv);

			PdfPTable tablerightFM = new PdfPTable(2);
			tablerightFM.setWidthPercentage(100);
			tablerightFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tablerightFM.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi3=new PdfPCell();
			pi3.setColspan(2);
			pi3.addElement(new Phrase(" "));

			pi3.setBorder(Rectangle.NO_BORDER);


			tablerightFM.addCell(pi3);
			pr = new Paragraph("\nFather's Date of Birth:", fontTableHeading);
			pr.setAlignment(Element.ALIGN_CENTER);

			tablerightFM.addCell(pr);

			prv = new Paragraph("\n"+listFM.get(0).get("father_dob").toString(), fontTableValue);
			prv.setAlignment(Element.ALIGN_CENTER);

			tablerightFM.addCell(prv);

			pr = new Paragraph("Mother's Date of Birth:", fontTableHeading);
			pr.setAlignment(Element.ALIGN_CENTER);

			tablerightFM.addCell(pr);

			prv = new Paragraph(listFM.get(0).get("mother_dob").toString(), fontTableValue);
			prv.setAlignment(Element.ALIGN_CENTER);
//CHANGES END HERE 
			tablerightFM.addCell(prv);

			PdfPTable tablecenterFM = new PdfPTable(2);

			tablecenterFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			tablecenterFM.setWidthPercentage(100);

			tablecenterFM.addCell(tableleftFM);

			tablecenterFM.addCell(tablerightFM);

			table111.addCell(tablecenterFM);
			table111.addCell(new Phrase("\n"));



		}

		if (listS.size() > 0) {

			header = new Paragraph("SPOUSE DETAILS", fontTableheader);


			PdfPTable table2 = new PdfPTable(11);

			table2.setWidthPercentage(100);

			table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(11);
			pi2.setBorder(Rectangle.NO_BORDER);
			table2.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(11);
			pi3.setBorder(Rectangle.NO_BORDER);
			table2.addCell(pi3);
			
			for (int h = 0; h < TH1.size(); h++) {

				p = new Paragraph(TH1.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table2.addCell(p);

			}

			table2.setHeaderRows(1); // table first row will be repeated in all pages

			if (listS.size() > 0) {

				for (Map<String, Object> featureService : listS) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table2.addCell(p);

					}

				}

			} else {

				p = new Paragraph("No Data available");

				PdfPCell c1 = new PdfPCell(p);

				c1.setColspan(10);

				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table2.addCell(c1);

			}

			table111.addCell(table2);

			table111.addCell(new Phrase("\n"));


		}

		if (listCHILD.size() > 0) {

			header = new Paragraph("CHILDREN DETAILS", fontTableheader);


			PdfPTable table3 = new PdfPTable(3);

			table3.setWidthPercentage(100);

			table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(3);
			pi2.setBorder(Rectangle.NO_BORDER);
			table3.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(3);
			pi3.setBorder(Rectangle.NO_BORDER);
			table3.addCell(pi3);
			
			for (int h = 0; h < TH2.size(); h++) {

				p = new Paragraph(TH2.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table3.addCell(p);

			}

			table3.setHeaderRows(1); // table first row will be repeated in all pages

			if (listCHILD.size() > 0) {

				for (Map<String, Object> featureService : listCHILD) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table3.addCell(p);

					}

				}

			} else {

				p = new Paragraph("No Data available");

				PdfPCell c2 = new PdfPCell(p);

				c2.setColspan(3);

				c2.setHorizontalAlignment(Element.ALIGN_CENTER);

				table3.addCell(c2);

			}

			table111.addCell(table3);

			table111.addCell(new Phrase("\n"));


		}

		if (listAM.size() > 0) {

			header = new Paragraph("AWARDS AND MEDALS", fontTableheader);


			PdfPTable table4 = new PdfPTable(4);

			table4.setWidthPercentage(100);

			table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table4.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(4);
			pi2.setBorder(Rectangle.NO_BORDER);
			table4.addCell(pi2);
			
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(4);
			pi3.setBorder(Rectangle.NO_BORDER);
			table4.addCell(pi3);
			
			for (int h = 0; h < TH3.size(); h++) {

				p = new Paragraph(TH3.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table4.addCell(p);

			}

			table4.setHeaderRows(1); // table first row will be repeated in all pages

			if (listAM.size() > 0) {

				for (Map<String, Object> featureService : listAM) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {

						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table4.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c3 = new PdfPCell(p);

				c3.setColspan(4);

				c3.setHorizontalAlignment(Element.ALIGN_CENTER);

				table4.addCell(c3);

			}

			table111.addCell(table4);

			table111.addCell(new Phrase("\n"));


		}

		if (listTENU.size() > 0) {

			header = new Paragraph("TENURE DETAILS", fontTableheader);


			PdfPTable table5 = new PdfPTable(7);

			table5.setWidthPercentage(100);

			table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table5.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(7);
			pi2.setBorder(Rectangle.NO_BORDER);
			table5.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(7);
			pi3.setBorder(Rectangle.NO_BORDER);
			table5.addCell(pi3);
			
			for (int h = 0; h < TH4.size(); h++) {

				p = new Paragraph(TH4.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table5.addCell(p);

			}

			table5.setHeaderRows(1); // table first row will be repeated in all pages

			if (listTENU.size() > 0) {

				for (Map<String, Object> featureService : listTENU) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table5.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c4 = new PdfPCell(p);

				c4.setColspan(7);

				c4.setHorizontalAlignment(Element.ALIGN_CENTER);

				table5.addCell(c4);

			}

			p = new Paragraph("Total", fontTableHeading);

			PdfPCell c4 = new PdfPCell(p);

			c4.setColspan(6);

			c4.setHorizontalAlignment(Element.ALIGN_CENTER);

			table5.addCell(c4);

			if (listTENU_T.size() > 0) {

				Paragraph p1 = new Paragraph(listTENU_T.get(0).get("Total_tenure").toString(), fontTableValue);

				PdfPCell c0 = new PdfPCell(p1);

				c0.setColspan(1);

				c0.setHorizontalAlignment(Element.ALIGN_CENTER);

				table5.addCell(c0);

			}

			table111.addCell(table5);

			table111.addCell(new Phrase("\n"));


		}
		/// bisag v1 220822 (commented as per miso sir guidance)
//		if (listRegim.size() > 0) {
//
//			header = new Paragraph("RANK AND APPOINTMENT", fontTableheader);
//
//
//			PdfPTable table6 = new PdfPTable(7);
//
//			table6.setWidthPercentage(100);
//
//			table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//
//			table6.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//			PdfPCell pi2=new PdfPCell();
//			pi2.addElement(header);
//			pi2.setColspan(7);
//			pi2.setBorder(Rectangle.NO_BORDER);
//			table6.addCell(pi2);
//			
//			PdfPCell pi3=new PdfPCell();
//			pi3.addElement(new Phrase(" "));
//			pi3.setColspan(7);
//			pi3.setBorder(Rectangle.NO_BORDER);
//			table6.addCell(pi3);
//			
//			for (int h = 0; h < TH5.size(); h++) {
//
//				p = new Paragraph(TH5.get(h), fontTableHeading);
//
//				p.setAlignment(Element.ALIGN_CENTER);
//
//				table6.addCell(p);
//
//			}
//
//			table6.setHeaderRows(1); // table first row will be repeated in all pages
//
//			if (listRegim.size() > 0) {
//
//				for (Map<String, Object> featureService : listRegim) {
//
//					for (Map.Entry<String, Object> entry : featureService.entrySet()) {
//
//
//						p = new Paragraph(entry.getValue().toString(), fontTableValue);
//
//						table6.addCell(p);
//
//					}
//
//				}
//
//			}
//
//			else {
//
//				p = new Paragraph("No Data available");
//
//				PdfPCell c5 = new PdfPCell(p);
//
//				c5.setColspan(7);
//
//				c5.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//				table6.addCell(c5);
//
//			}
//
//			table111.addCell(table6);
//
//			table111.addCell(new Phrase("\n"));
//
//
//		}

		if (listFIS.size() > 0) {

			header = new Paragraph("FIELD SERVICE", fontTableheader);


			PdfPTable tableleftFIS = new PdfPTable(2);

			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);

			tableleftFIS.addCell(pi2);
			
			tableleftFIS.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			pl = new Paragraph("\nPeace:", fontTableHeading);
			pl.setAlignment(Element.ALIGN_CENTER);

			tableleftFIS.addCell(pl);

			plv = new Paragraph("\n"+peace_month + " MONTHS", fontTableValue);
			plv.setAlignment(Element.ALIGN_CENTER);

			tableleftFIS.addCell(plv);

			pl = new Paragraph("High Alt:", fontTableHeading);
     		pl.setAlignment(Element.ALIGN_CENTER);

			tableleftFIS.addCell(pl);

			plv = new Paragraph(listFIS.get(0).get("high_alt").toString() + " MONTHS", fontTableValue);
			plv.setAlignment(Element.ALIGN_CENTER);

			tableleftFIS.addCell(plv);

			PdfPTable tablerightFIS = new PdfPTable(2);
			tablerightFIS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			tablerightFIS.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
     		tablerightFIS.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			pi3.setPaddingTop(8);
			tablerightFIS.addCell(pi3);
			
			pr = new Paragraph("\nField:", fontTableHeading);
			pr.setAlignment(Element.ALIGN_CENTER);
     		pr.setAlignment(Element.ALIGN_MIDDLE);
			tablerightFIS.addCell(pr);

			prv = new Paragraph("\n"+listFIS.get(0).get("field").toString() + " MONTHS", fontTableValue);
			prv.setAlignment(Element.ALIGN_CENTER);
     		prv.setAlignment(Element.ALIGN_MIDDLE);
			tablerightFIS.addCell(prv);

			pr = new Paragraph("Ci Ops:", fontTableHeading);
			pr.setAlignment(Element.ALIGN_CENTER);
			pr.setAlignment(Element.ALIGN_MIDDLE);
			tablerightFIS.addCell(pr);

			prv = new Paragraph(listFIS.get(0).get("ci_ops").toString() + " MONTHS", fontTableValue);
			prv.setAlignment(Element.ALIGN_CENTER);
			prv.setAlignment(Element.ALIGN_MIDDLE);
			tablerightFIS.addCell(prv);

			PdfPTable tablecenterFIS = new PdfPTable(2);

			tablecenterFIS.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterFIS.setWidthPercentage(100);

			tablecenterFIS.addCell(tableleftFIS);

			tablecenterFIS.addCell(tablerightFIS);

			table111.addCell(tablecenterFIS);

			table111.addCell(new Phrase("\n"));


		}

		if (listARM.size() > 0) {

			header = new Paragraph("ARMY COURSE", fontTableheader);


			PdfPTable table7 = new PdfPTable(5);

			table7.setWidthPercentage(100);

			table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table7.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(5);
			pi2.setBorder(Rectangle.NO_BORDER);
			table7.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(5);
			pi3.setBorder(Rectangle.NO_BORDER);
			table7.addCell(pi3);
			
			for (int h = 0; h < TH6.size(); h++) {

				

				p = new Paragraph(TH6.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table7.addCell(p);

			}

			table7.setHeaderRows(1); // table first row will be repeated in all pages

			if (listARM.size() > 0) {

				for (Map<String, Object> featureService : listARM) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table7.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c6 = new PdfPCell(p);

				c6.setColspan(2);

				c6.setHorizontalAlignment(Element.ALIGN_CENTER);

				table7.addCell(c6);

			}

			table111.addCell(table7);

			table111.addCell(new Phrase("\n"));


		}

		if (listPE.size() > 0) {

			header = new Paragraph("PROMOTIONAL EXAM", fontTableheader);


			PdfPTable table8 = new PdfPTable(2);

			table8.setWidthPercentage(100);

			table8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table8.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			table8.addCell(pi2);
			
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			table8.addCell(pi3);
			
			for (int h = 0; h < TH7.size(); h++) {

				p = new Paragraph(TH7.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table8.addCell(p);

			}

			table8.setHeaderRows(1); // table first row will be repeated in all pages

			if (listPE.size() > 0) {

				for (Map<String, Object> featureService : listPE) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table8.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c7 = new PdfPCell(p);

				c7.setColspan(2);

				c7.setHorizontalAlignment(Element.ALIGN_CENTER);

				table8.addCell(c7);

			}

			table111.addCell(table8);

			table111.addCell(new Phrase("\n"));


		}

		if (listAQ.size() > 0) {

			header = new Paragraph("ACADEMIC QUALIFICATIONS", fontTableheader);


			PdfPTable table9 = new PdfPTable(7);

			table9.setWidthPercentage(100);

			table9.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table9.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(7);
			pi2.setBorder(Rectangle.NO_BORDER);
			table9.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(7);
			pi3.setBorder(Rectangle.NO_BORDER);
			table9.addCell(pi3);
			
			for (int h = 0; h < TH8.size(); h++) {

				p = new Paragraph(TH8.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table9.addCell(p);

			}

			table9.setHeaderRows(1); // table first row will be repeated in all pages

			if (listAQ.size() > 0) {

				for (Map<String, Object> featureService : listAQ) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table9.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c8 = new PdfPCell(p);

				c8.setColspan(7);

				c8.setHorizontalAlignment(Element.ALIGN_CENTER);

				table9.addCell(c8);

			}

			table111.addCell(table9);

			table111.addCell(new Phrase("\n"));


		}

		if (listPTQ.size() > 0) {

			header = new Paragraph("PROFESSIONAL/TECHNICAL QUALIFICATIONS", fontTableheader);


			PdfPTable table10 = new PdfPTable(7);

			table10.setWidthPercentage(100);

			table10.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table10.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(7);
			pi2.setBorder(Rectangle.NO_BORDER);
			table10.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(7);
			pi3.setBorder(Rectangle.NO_BORDER);
			table10.addCell(pi3);
			
			for (int h = 0; h < TH9.size(); h++) {

				p = new Paragraph(TH9.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table10.addCell(p);

			}

			table10.setHeaderRows(1); // table first row will be repeated in all pages

			if (listPTQ.size() > 0) {

				for (Map<String, Object> featureService : listPTQ) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table10.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c9 = new PdfPCell(p);

				c9.setColspan(7);

				c9.setHorizontalAlignment(Element.ALIGN_CENTER);

				table10.addCell(c9);

			}

			table111.addCell(table10);

			table111.addCell(new Phrase("\n"));


		}

		if (listIL.size() > 0 || listFL.size() > 0) {

			header = new Paragraph("LANGUAGE DETAILS", fontTableheader);


			PdfPTable table11 = new PdfPTable(2);

			table11.setWidthPercentage(100);

			table11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table11.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			table11.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			table11.addCell(pi3);
			
			for (int h = 0; h < TH10.size(); h++) {

				p = new Paragraph(TH10.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table11.addCell(p);

			}

			table11.setHeaderRows(1); // table first row will be repeated in all pages

			if (listIL.size() > 0) {

				for (Map<String, Object> featureService : listIL) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table11.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c10 = new PdfPCell(p);

				c10.setColspan(2);

				c10.setHorizontalAlignment(Element.ALIGN_CENTER);

				table11.addCell(c10);

			}

			table111.addCell(table11);

			table111.addCell(new Phrase("\n"));

			PdfPTable table12 = new PdfPTable(3);

			table12.setWidthPercentage(100);

			table12.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table12.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			for (int h = 0; h < TH11.size(); h++) {

				p = new Paragraph(TH11.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table12.addCell(p);

			}

			table12.setHeaderRows(1); // table first row will be repeated in all pages

			if (listFL.size() > 0) {

				for (Map<String, Object> featureService : listFL) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table12.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c11 = new PdfPCell(p);

				c11.setColspan(2);

				c11.setHorizontalAlignment(Element.ALIGN_CENTER);

				table12.addCell(c11);

			}

			table111.addCell(table12);

			table111.addCell(new Phrase("\n"));


		}

		if (listF.size() > 0) {

			header = new Paragraph("FOREIGN COUNTRIES VISITED", fontTableheader);


			PdfPTable table13 = new PdfPTable(5);

			table13.setWidthPercentage(100);

			table13.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table13.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(5);
			pi2.setBorder(Rectangle.NO_BORDER);
			table13.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(5);
			pi3.setBorder(Rectangle.NO_BORDER);
			table13.addCell(pi3);
			
			for (int h = 0; h < TH12.size(); h++) {

				p = new Paragraph(TH12.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table13.addCell(p);

			}

			table13.setHeaderRows(1); // table first row will be repeated in all pages

			if (listF.size() > 0) {

				for (Map<String, Object> featureService : listF) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table13.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c12 = new PdfPCell(p);

				c12.setColspan(5);

				c12.setHorizontalAlignment(Element.ALIGN_CENTER);

				table13.addCell(c12);

			}

			table111.addCell(table13);

			table111.addCell(new Phrase("\n"));


		}

		if (listB.size() > 0) {

			header = new Paragraph("BPET", fontTableheader);


			PdfPTable table14 = new PdfPTable(3);

			table14.setWidthPercentage(100);

			table14.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table14.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(3);
			pi2.setBorder(Rectangle.NO_BORDER);
			table14.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(3);
			pi3.setBorder(Rectangle.NO_BORDER);
			table14.addCell(pi3);
			
			for (int h = 0; h < TH13.size(); h++) {

				p = new Paragraph(TH13.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table14.addCell(p);

			}

			table14.setHeaderRows(1); // table first row will be repeated in all pages

			if (listB.size() > 0) {

				for (Map<String, Object> featureService : listB) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table14.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c13 = new PdfPCell(p);

				c13.setColspan(3);

				c13.setHorizontalAlignment(Element.ALIGN_CENTER);

				table14.addCell(c13);

			}

			table111.addCell(table14);

			table111.addCell(new Phrase("\n"));


		}

		if (listFS.size() > 0) {

			header = new Paragraph("FIRING STANDARD", fontTableheader);


			PdfPTable table15 = new PdfPTable(3);

			table15.setWidthPercentage(100);

			table15.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table15.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(3);
			pi2.setBorder(Rectangle.NO_BORDER);
			table15.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(3);
			pi3.setBorder(Rectangle.NO_BORDER);
			table15.addCell(pi3);
			
			for (int h = 0; h < TH14.size(); h++) {

				p = new Paragraph(TH14.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table15.addCell(p);

			}

			table15.setHeaderRows(1); // table first row will be repeated in all pages

			if (listFS.size() > 0) {

				for (Map<String, Object> featureService : listFS) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table15.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c14 = new PdfPCell(p);

				c14.setColspan(3);

				c14.setHorizontalAlignment(Element.ALIGN_CENTER);

				table15.addCell(c14);

			}

			table111.addCell(table15);

			table111.addCell(new Phrase("\n"));


		}

		if (listBA.size() > 0) {

			header = new Paragraph("BATTLE AND PHYSICAL CASULTY", fontTableheader);


			PdfPTable table16 = new PdfPTable(6);

			table16.setWidthPercentage(100);

			table16.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			table16.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header);
			pi2.setColspan(6);
			pi2.setBorder(Rectangle.NO_BORDER);
			table16.addCell(pi2);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(6);
			pi3.setBorder(Rectangle.NO_BORDER);
			table16.addCell(pi3);
			
			for (int h = 0; h < TH15.size(); h++) {

				p = new Paragraph(TH15.get(h), fontTableHeading);

				p.setAlignment(Element.ALIGN_CENTER);

				table16.addCell(p);

			}

			table16.setHeaderRows(1); // table first row will be repeated in all pages

			if (listBA.size() > 0) {

				for (Map<String, Object> featureService : listBA) {

					for (Map.Entry<String, Object> entry : featureService.entrySet()) {


						p = new Paragraph(entry.getValue().toString(), fontTableValue);

						table16.addCell(p);

					}

				}

			}

			else {

				p = new Paragraph("No Data available");

				PdfPCell c15 = new PdfPCell(p);

				c15.setColspan(6);

				c15.setHorizontalAlignment(Element.ALIGN_CENTER);

				table16.addCell(c15);

			}

			table111.addCell(table16);

			table111.addCell(new Phrase("\n"));

			table111.addCell(new Phrase("\n"));

		}

		if (listSE.size() > 0) {

			header = new Paragraph("SECONDMENT", fontTableheader);

			table111.addCell(header);

			table111.addCell(new Phrase("\n"));

			PdfPTable tableleftSE = new PdfPTable(2);

			tableleftSE.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			pl = new Paragraph("\n Seconded To:", fontTableHeading);

			tableleftSE.addCell(pl);

			plv = new Paragraph("\n"+listSE.get(0).get("seconded_to").toString(), fontTableValue);

			tableleftSE.addCell(plv);

			PdfPTable tablerightSE = new PdfPTable(2);

			tablerightSE.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			pr = new Paragraph("\n Date of Secondment:", fontTableHeading);

			tablerightSE.addCell(pr);

			prv = new Paragraph("\n"+listSE.get(0).get("secondment_effect").toString(), fontTableValue);

			tablerightSE.addCell(prv);

			PdfPTable tablecenterSE = new PdfPTable(2);

			tablecenterSE.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterSE.setWidthPercentage(100);

			tablecenterSE.addCell(tableleftSE);

			tablecenterSE.addCell(tablerightSE);

			table111.addCell(tablecenterSE);

			table111.addCell(new Phrase("\n"));


		}

		header = new Paragraph("ADDRESS DETAILS", fontTableheader);


		if (listORM.size() > 0) {

			header1 = new Paragraph("ORIGINAL DOMICILE OF", fontTableHeader1);


			PdfPTable tableleftORM = new PdfPTable(2);

			tableleftORM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi11=new PdfPCell();
			pi11.addElement(header);
			pi11.setColspan(11);
			pi11.setBorder(Rectangle.NO_BORDER);
			tableleftORM.addCell(pi11);
			
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(11);
			pi3.setBorder(Rectangle.NO_BORDER);
			tableleftORM.addCell(pi3);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header1);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tableleftORM.addCell(pi2);
			
			PdfPCell pi13=new PdfPCell();
			pi13.addElement(new Phrase(" "));
			pi13.setColspan(11);
			pi13.setBorder(Rectangle.NO_BORDER);
			tableleftORM.addCell(pi13);
			
			pl = new Paragraph("Country:", fontTableHeading);

			tableleftORM.addCell(pl);

			plv = new Paragraph(listORM.get(0).get("ori_country").toString(), fontTableValue);

			tableleftORM.addCell(plv);

			pl = new Paragraph("District:", fontTableHeading);

			tableleftORM.addCell(pl);

			plv = new Paragraph(listORM.get(0).get("ori_dis").toString(), fontTableValue);

			tableleftORM.addCell(plv);

			PdfPTable tablerightORM = new PdfPTable(2);

			tablerightORM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi23=new PdfPCell();
			pi23.addElement(new Phrase(" "));
			pi23.setColspan(2);
			pi23.setBorder(Rectangle.NO_BORDER);
			tablerightORM.addCell(pi23);
			
			PdfPCell pi24=new PdfPCell();
			pi24.addElement(new Phrase(" "));
			pi24.setColspan(2);
			pi24.setBorder(Rectangle.NO_BORDER);
			tablerightORM.addCell(pi24);
			PdfPCell pi25=new PdfPCell();
			pi25.addElement(new Phrase(" "));
			pi25.setColspan(2);
			pi25.setBorder(Rectangle.NO_BORDER);
			tablerightORM.addCell(pi25);
			PdfPCell pi26=new PdfPCell();
			pi26.addElement(new Phrase(" "));
			pi26.setColspan(2);
			pi26.setBorder(Rectangle.NO_BORDER);
			tablerightORM.addCell(pi26);
			
			pr = new Paragraph("\nState:", fontTableHeading);

			tablerightORM.addCell(pr);

			prv = new Paragraph("\n"+listORM.get(0).get("ori_state").toString(), fontTableValue);

			tablerightORM.addCell(prv);

			pr = new Paragraph("Tehsil:", fontTableHeading);

			tablerightORM.addCell(pr);

			prv = new Paragraph(listORM.get(0).get("ori_teh").toString(), fontTableValue);

			tablerightORM.addCell(prv);

			PdfPTable tablecenterORM = new PdfPTable(2);

			tablecenterORM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterORM.setWidthPercentage(100);

			tablecenterORM.addCell(tableleftORM);

			tablecenterORM.addCell(tablerightORM);

			table111.addCell(tablecenterORM);

			table111.addCell(new Phrase("\n"));

			table111.addCell(new Phrase("\n"));

		}

		if (listPDO.size() > 0) {

			header1 = new Paragraph("PRESENTLY DOMICILE OF", fontTableHeader1);


			PdfPTable tableleftPDO = new PdfPTable(2);
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(header1);
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tableleftPDO.addCell(pi3);
			
			PdfPCell pi23=new PdfPCell();
			pi23.addElement(new Phrase("     "));
			pi23.setColspan(2);
			pi23.setBorder(Rectangle.NO_BORDER);
			tableleftPDO.addCell(pi23);
			tableleftPDO.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			

			pl = new Paragraph("Country:", fontTableHeading);

			tableleftPDO.addCell(pl);

			plv = new Paragraph(listPDO.get(0).get("pre_country").toString(), fontTableValue);

			tableleftPDO.addCell(plv);

			pl = new Paragraph("District:", fontTableHeading);

			tableleftPDO.addCell(pl);

			plv = new Paragraph(listPDO.get(0).get("pre_dis").toString(), fontTableValue);

			tableleftPDO.addCell(plv);

			PdfPTable tablerightPDO = new PdfPTable(2);

			tablerightPDO.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(new Phrase(" "));
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tablerightPDO.addCell(pi2);
			
			PdfPCell pi24=new PdfPCell();
			pi24.addElement(new Phrase("     "));
			pi24.setColspan(2);
			pi24.setBorder(Rectangle.NO_BORDER);
			tablerightPDO.addCell(pi24);
			
			pr = new Paragraph("State:", fontTableHeading);

			tablerightPDO.addCell(pr);

			prv = new Paragraph(listPDO.get(0).get("pre_state").toString(), fontTableValue);

			tablerightPDO.addCell(prv);

			pr = new Paragraph("Tehsil:", fontTableHeading);

			tablerightPDO.addCell(pr);

			prv = new Paragraph(listPDO.get(0).get("pre_teh").toString(), fontTableValue);

			tablerightPDO.addCell(prv);

			PdfPTable tablecenterPDO = new PdfPTable(2);

			tablecenterPDO.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterPDO.setWidthPercentage(100);

			tablecenterPDO.addCell(tableleftPDO);

			tablecenterPDO.addCell(tablerightPDO);

			table111.addCell(tablecenterPDO);

			table111.addCell(new Phrase("\n"));


		}

		if (listPM.size() > 0) {

			header1 = new Paragraph("PERMANENT  ADDRESS", fontTableHeader1);


			PdfPTable tableleftPM = new PdfPTable(2);

			tableleftPM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			PdfPCell pi3=new PdfPCell();
			pi3.addElement(header1);
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tableleftPM.addCell(pi3);
			
			pl = new Paragraph("\nCountry:", fontTableHeading);

			tableleftPM.addCell(pl);

			plv = new Paragraph("\n"+listPM.get(0).get("pm_country").toString(), fontTableValue);

			tableleftPM.addCell(plv);

			pl = new Paragraph("District:", fontTableHeading);

			tableleftPM.addCell(pl);

			plv = new Paragraph(listPM.get(0).get("pm_dis").toString(), fontTableValue);

			tableleftPM.addCell(plv);

			pl = new Paragraph("Village/Town/City:", fontTableHeading);

			tableleftPM.addCell(pl);

			plv = new Paragraph(listPM.get(0).get("permanent_village").toString(), fontTableValue);

			tableleftPM.addCell(plv);

			pl = new Paragraph("Nearest Railway Station:", fontTableHeading);

			tableleftPM.addCell(pl);

			plv = new Paragraph(listPM.get(0).get("permanent_near_railway_station").toString(), fontTableValue);

			tableleftPM.addCell(plv);

			PdfPTable tablerightPM = new PdfPTable(2);

			tablerightPM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(new Phrase(" "));
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tablerightPM.addCell(pi2);
			
			pr = new Paragraph("\nState:", fontTableHeading);

			tablerightPM.addCell(pr);

			prv = new Paragraph("\n"+listPM.get(0).get("pm_state").toString(), fontTableValue);

			tablerightPM.addCell(prv);

			pr = new Paragraph("Tehsil:", fontTableHeading);

			tablerightPM.addCell(pr);

			prv = new Paragraph(listPM.get(0).get("pm_teh").toString(), fontTableValue);

			tablerightPM.addCell(prv);

			pr = new Paragraph("Pin:", fontTableHeading);

			tablerightPM.addCell(pr);

			prv = new Paragraph(listPM.get(0).get("permanent_pin_code").toString(), fontTableValue);

			tablerightPM.addCell(prv);

			pr = new Paragraph("Border Area:", fontTableHeading);

			tablerightPM.addCell(pr);

			prv = new Paragraph(listPM.get(0).get("permanent_border_area").toString(), fontTableValue);

			tablerightPM.addCell(prv);

			PdfPTable tablecenterPM = new PdfPTable(2);

			tablecenterPM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterPM.setWidthPercentage(100);

			tablecenterPM.addCell(tableleftPM);

			tablecenterPM.addCell(tablerightPM);

			table111.addCell(tablecenterPM);

			table111.addCell(new Phrase("\n"));


		}

		if (listPS.size() > 0) {

			header1 = new Paragraph("PRESENT ADDRESS", fontTableHeader1);


			PdfPTable tableleftPS = new PdfPTable(2);

			tableleftPS.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header1);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tableleftPS.addCell(pi2);
			
			pl = new Paragraph("\nCountry:", fontTableHeading);

			tableleftPS.addCell(pl);

			plv = new Paragraph("\n"+listPS.get(0).get("ps_country").toString(), fontTableValue);

			tableleftPS.addCell(plv);

			pl = new Paragraph("District:", fontTableHeading);

			tableleftPS.addCell(pl);

			plv = new Paragraph(listPS.get(0).get("ps_dis").toString(), fontTableValue);

			tableleftPS.addCell(plv);

			pl = new Paragraph("Village/Town/City:", fontTableHeading);

			tableleftPS.addCell(pl);

			plv = new Paragraph(listPS.get(0).get("permanent_village").toString(), fontTableValue);

			tableleftPS.addCell(plv);

			pl = new Paragraph("Nearest Railway Station:", fontTableHeading);

			tableleftPS.addCell(pl);

			plv = new Paragraph(listPS.get(0).get("present_near_railway_station").toString(), fontTableValue);

			tableleftPS.addCell(plv);

			PdfPTable tablerightPS = new PdfPTable(2);

			tablerightPS.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tablerightPS.addCell(pi3);
			
			pr = new Paragraph("\n State:", fontTableHeading);

			tablerightPS.addCell(pr);

			prv = new Paragraph("\n"+listPS.get(0).get("ps_state").toString(), fontTableValue);

			tablerightPS.addCell(prv);

			pr = new Paragraph("Tehsil:", fontTableHeading);

			tablerightPS.addCell(pr);

			prv = new Paragraph(listPS.get(0).get("ps_teh").toString(), fontTableValue);

			tablerightPS.addCell(prv);

			pr = new Paragraph("Pin:", fontTableHeading);

			tablerightPS.addCell(pr);

			prv = new Paragraph(listPS.get(0).get("present_pin_code").toString(), fontTableValue);

			tablerightPS.addCell(prv);

			//
			// pr= new Paragraph("Border Area:",fontTableHeading);
			//
			//
			//
			// tablerightPS.addCell(pr);
			//
			//
			//
			// prv= new Paragraph(String.valueOf(listPS.get(0).get("permanent_border_area"))
			// ,
			//
			//
			//
			// fontTableValue); tablerightPS.addCell(prv);

			PdfPTable tablecenterPS = new PdfPTable(2);

			tablecenterPS.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterPS.setWidthPercentage(100);

			tablecenterPS.addCell(tableleftPS);

			tablecenterPS.addCell(tablerightPS);

			table111.addCell(tablecenterPS);

			table111.addCell(new Phrase("\n"));


		}

		if (listNOK.size() > 0) {

			header1 = new Paragraph("NOK DETAILS", fontTableHeader1);


			PdfPTable tableleftNOK = new PdfPTable(2);

			tableleftNOK.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(header1);
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tableleftNOK.addCell(pi3);
			pl = new Paragraph("\n Name Of NOK:", fontTableHeading);

			tableleftNOK.addCell(pl);

			plv = new Paragraph("\n"+listNOK.get(0).get("nok_name").toString(), fontTableValue);

			tableleftNOK.addCell(plv);

			PdfPTable tablerightNOK = new PdfPTable(2);

			tablerightNOK.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(new Phrase(" "));
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tablerightNOK.addCell(pi2);
			pr = new Paragraph("\n Nok Relation:", fontTableHeading);

			tablerightNOK.addCell(pr);

			prv = new Paragraph("\n"+listNOK.get(0).get("relation_name").toString(), fontTableValue);

			tablerightNOK.addCell(prv);

			PdfPTable tablecenterNOK = new PdfPTable(2);

			tablecenterNOK.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterNOK.setWidthPercentage(100);

			tablecenterNOK.addCell(tableleftNOK);

			tablecenterNOK.addCell(tablerightNOK);

			table111.addCell(tablecenterNOK);

			table111.addCell(new Phrase("\n"));


		}

		if (listNA.size() > 0) {

			header1 = new Paragraph("NOK ADDRESS", fontTableHeader1);


			PdfPTable tableleftNA = new PdfPTable(2);

			tableleftNA.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header1);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tableleftNA.addCell(pi2);
			pl = new Paragraph("\nCountry:", fontTableHeading);

			tableleftNA.addCell(pl);

			plv = new Paragraph("\n"+listNA.get(0).get("nok_country").toString(), fontTableValue);

			tableleftNA.addCell(plv);

			pl = new Paragraph("District:", fontTableHeading);

			tableleftNA.addCell(pl);

			plv = new Paragraph(listNA.get(0).get("nok_dis").toString(), fontTableValue);

			tableleftNA.addCell(plv);

			pl = new Paragraph("Village/Town/City:", fontTableHeading);

			tableleftNA.addCell(pl);

			plv = new Paragraph(listNA.get(0).get("nok_village").toString(), fontTableValue);

			tableleftNA.addCell(plv);

			pl = new Paragraph("Nearest Railway Station:", fontTableHeading);

			tableleftNA.addCell(pl);

			plv = new Paragraph(listNA.get(0).get("nok_near_railway_station").toString(), fontTableValue);

			tableleftNA.addCell(plv);

			PdfPTable tablerightNA = new PdfPTable(2);

			tablerightNA.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tablerightNA.addCell(pi3);
			pr = new Paragraph("\n State:", fontTableHeading);

			tablerightNA.addCell(pr);

			prv = new Paragraph("\n"+listNA.get(0).get("nok_state").toString(), fontTableValue);

			tablerightNA.addCell(prv);

			pr = new Paragraph("Tehsil:", fontTableHeading);

			tablerightNA.addCell(pr);

			prv = new Paragraph(listNA.get(0).get("nok_teh").toString(), fontTableValue);

			tablerightNA.addCell(prv);

			pr = new Paragraph("Pin:", fontTableHeading);

			tablerightNA.addCell(pr);

			prv = new Paragraph(listNA.get(0).get("nok_pin").toString(), fontTableValue);

			tablerightNA.addCell(prv);

			pr = new Paragraph("Nok's Mobile No:", fontTableHeading);

			tablerightNA.addCell(pr);

			prv = new Paragraph(listNA.get(0).get("nok_mobile_no").toString(), fontTableValue);

			tablerightNA.addCell(prv);

			PdfPTable tablecenterNA = new PdfPTable(2);

			tablecenterNA.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterNA.setWidthPercentage(100);

			tablecenterNA.addCell(tableleftNA);

			tablecenterNA.addCell(tablerightNA);

			table111.addCell(tablecenterNA);

			table111.addCell(new Phrase("\n"));


		}

		if (listRD.size() > 0) {

			if (listAR.size() > 0) {

				header1 = new Paragraph("ADDRESS RETIREMENT", fontTableHeader1);


				PdfPTable tableleftAR = new PdfPTable(2);

				tableleftAR.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				PdfPCell pi2=new PdfPCell();
				pi2.addElement(header1);
				pi2.setColspan(2);
				pi2.setBorder(Rectangle.NO_BORDER);
				tableleftAR.addCell(pi2);
				pl = new Paragraph("\n Country:", fontTableHeading);

				tableleftAR.addCell(pl);

				plv = new Paragraph("\n"+listAR.get(0).get("ar_country").toString(), fontTableValue);

				tableleftAR.addCell(plv);

				pl = new Paragraph("District:", fontTableHeading);

				tableleftAR.addCell(pl);

				plv = new Paragraph(listAR.get(0).get("ar_dis").toString(), fontTableValue);

				tableleftAR.addCell(plv);

				pl = new Paragraph("Village/Town/City:", fontTableHeading);

				tableleftAR.addCell(pl);

				plv = new Paragraph(listAR.get(0).get("permanent_village").toString(), fontTableValue);

				tableleftAR.addCell(plv);

				pl = new Paragraph("Nearest Railway Station:", fontTableHeading);

				tableleftAR.addCell(pl);

				plv = new Paragraph(listAR.get(0).get("permanent_near_railway_station").toString(), fontTableValue);

				tableleftAR.addCell(plv);

				PdfPTable tablerightAR = new PdfPTable(2);

				tablerightAR.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				PdfPCell pi3=new PdfPCell();
				pi3.addElement(new Phrase(" "));
				pi3.setColspan(2);
				pi3.setBorder(Rectangle.NO_BORDER);
				tablerightAR.addCell(pi3);
				pr = new Paragraph("\n State:", fontTableHeading);

				tablerightAR.addCell(pr);

				prv = new Paragraph("\n"+listAR.get(0).get("ar_state").toString(), fontTableValue);

				tablerightAR.addCell(prv);

				pr = new Paragraph("Tehsil:", fontTableHeading);

				tablerightAR.addCell(pr);

				prv = new Paragraph(listAR.get(0).get("ar_teh").toString(), fontTableValue);

				tablerightAR.addCell(prv);

				pr = new Paragraph("Pin:", fontTableHeading);

				tablerightAR.addCell(pr);

				prv = new Paragraph(listAR.get(0).get("permanent_pin_code").toString(), fontTableValue);

				tablerightAR.addCell(prv);

				pr = new Paragraph("Nok's Mobile No:", fontTableHeading);

				tablerightAR.addCell(pr);

				prv = new Paragraph(listAR.get(0).get("nok_mobile_no").toString(), fontTableValue);

				tablerightAR.addCell(prv);

				PdfPTable tablecenterAR = new PdfPTable(2);

				tablecenterAR.getDefaultCell().setBorder(Rectangle.NO_BORDER);

				tablecenterAR.setWidthPercentage(100);

				tablecenterAR.addCell(tableleftAR);

				tablecenterAR.addCell(tablerightAR);

				table111.addCell(tablecenterAR);

				table111.addCell(new Phrase("\n"));


			}

			header1 = new Paragraph("RETIREMENT DETAILS", fontTableHeader1);


			PdfPTable tableleftRD = new PdfPTable(2);

			tableleftRD.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi2=new PdfPCell();
			pi2.addElement(header1);
			pi2.setColspan(2);
			pi2.setBorder(Rectangle.NO_BORDER);
			tableleftRD.addCell(pi2);
			pl = new Paragraph("\n Cause of Retirement:", fontTableHeading);

			tableleftRD.addCell(pl);

			prv = new Paragraph("\n"+listRD.get(0).get("causes_name").toString(), fontTableValue);

			tableleftRD.addCell(prv);

			pl = new Paragraph("Cause of Release From Re-Employment:", fontTableHeading);

			tableleftRD.addCell(pl);

			prv = new Paragraph(listRD.get(0).get("cause_of_release").toString(), fontTableValue);

			tableleftRD.addCell(prv);

			PdfPTable tablerightRD = new PdfPTable(2);

			tablerightRD.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell pi3=new PdfPCell();
			pi3.addElement(new Phrase(" "));
			pi3.setColspan(2);
			pi3.setBorder(Rectangle.NO_BORDER);
			tablerightRD.addCell(pi3);
			pr = new Paragraph("\n Date of Retirement:", fontTableHeading);

			tablerightRD.addCell(pr);

			prv = new Paragraph("\n"+listRD.get(0).get("date_of_non_effective").toString(), fontTableValue);

			tablerightRD.addCell(prv);

			pr = new Paragraph("Date of Release from Re-Employement:", fontTableHeading);

			tablerightRD.addCell(pr);

			prv = new Paragraph(listRD.get(0).get("date_of_release").toString(), fontTableValue);

			tablerightRD.addCell(prv);

			PdfPTable tablecenterRD = new PdfPTable(2);

			tablecenterRD.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			tablecenterRD.setWidthPercentage(100);

			tablecenterRD.addCell(tableleftRD);

			tablecenterRD.addCell(tablerightRD);

			table111.addCell(tablecenterRD);

		}

		// table111.addCell(new Phrase("\n"));

		//// table111.addCell(new Phrase("\n"));

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
