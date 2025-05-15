package com.controller.InspectionReports;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.itextpdf.text.pdf.BaseFont;
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
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.model.InspectionReports.TB_DETAIL_COURSES;
import com.model.InspectionReports.TB_INSP_CELL_PHONE_LAPSES;
import com.model.InspectionReports.TB_INSP_EDUCATION_STANDARDS;
import com.model.InspectionReports.TB_INSP_EQUP_ANNUAL_METERAGE;
import com.model.InspectionReports.TB_INSP_ESPIONAGE_LAPSES;
import com.model.InspectionReports.TB_INSP_FATAL_INCIDENTS;
import com.model.InspectionReports.TB_INSP_FFRS;
import com.model.InspectionReports.TB_INSP_FINANCIAL_GRANTS;
import com.model.InspectionReports.TB_INSP_FS_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_LOST_CDS_DVDS;
import com.model.InspectionReports.TB_INSP_LOST_ID_ECR;
import com.model.InspectionReports.TB_INSP_OUTSIDE_ATTACHMENTS;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_AUDIT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_LOSS_MT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_RENT_ALLIED;
import com.model.InspectionReports.TB_INSP_PIO_CALL_LAPSES;
import com.model.InspectionReports.TB_INSP_REGT_FUNDS;
import com.model.InspectionReports.TB_INSP_RPT_CLASSIFICATION_RANGES;
import com.model.InspectionReports.TB_INSP_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_SOCIAL_MEDIA_LAPSES;
import com.model.InspectionReports.TB_INSP_STANDARDS_ACHIEVED;
import com.model.InspectionReports.TB_INSP_TD_PROCEEDED;
import com.model.InspectionReports.TB_MISO_INSP_ANIMAL;
import com.model.InspectionReports.TB_MISO_INSP_BPET_RESULT;
import com.model.InspectionReports.TB_MISO_INSP_COURSES_CAT_A_B;
import com.model.InspectionReports.TB_MISO_INSP_DEFI_EQUP_EFFI;
import com.model.InspectionReports.TB_MISO_INSP_DTL_OF_EQUP_OFFROAD;
import com.model.InspectionReports.TB_MISO_INSP_ESTABLISHMENT;
import com.model.InspectionReports.TB_MISO_INSP_LAND;
import com.model.InspectionReports.TB_MISO_INSP_PPT_RESULT;
import com.model.InspectionReports.TB_MISO_INSP_PROMOTION_EXAM;
import com.model.InspectionReports.TB_MISO_INSP_REG_LANG_EXAM;
import com.model.InspectionReports.TB_MISO_INSP_STATE_FUNDS;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH_OTHER;
import com.model.InspectionReports.TB_MISO_INSP_TRAINING_AMMUNITION;
import com.model.InspectionReports.TB_MISO_INSP_UPGRADATION;
import com.model.InspectionReports.TB_MISO_INSP_WT_RESULT_OTHER;

public class PDF_ANUAL_REPORT_PART_1 extends AbstractPdfView {

	String Type = "";
	String foot = "";
	List<String> TH;
	ArrayList<ArrayList<String>> list1;
	String username = "";
	String wksp_unit_name;
	String wo_dt = "";
	String dt_eqpt_reqd_fwd_wksp = "";

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";

	public PDF_ANUAL_REPORT_PART_1(String Type, List<String> TH, String username, ArrayList<ArrayList<String>> list1) {
		this.Type = Type;
		this.TH = TH;
		this.list1 = list1;
		this.username = username;
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
		logo.scaleAbsoluteHeight(40);
		logo.scaleAbsoluteWidth(30);
		logo.scalePercent(12);
		Chunk chunk = new Chunk(logo, -5, 10);

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
		logo2.scaleAbsoluteHeight(40);
		logo2.scaleAbsoluteWidth(30);
		logo2.scalePercent(12);
		Chunk chunk2 = new Chunk(logo2, -5, 10);
		Chunk underline = new Chunk(" ", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);

		Chunk underline2 = new Chunk("RESTRICTED", fontTableHeadingMainHead);
		underline2.setUnderline(0.1f, -2f);

		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline2);

		p.add("\n");
		p.add("\n");

		int currentPageNumber = document.getPageNumber();
//		Chunk pageCountChunk = new Chunk("Page " + currentPageNumber, fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk);

		p.add("\n");
		p.add("\n");

//		p.add(underline3);
	
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

//		Chunk p89 = new Chunk(foot, fontTableHeadingMainHead);
//		p89.setUnderline(0.1f, -2f);
//		p1.add(p89);

//		Chunk p90 = new Chunk("RESTRICTED", fontTableHeadingMainHead);
//		p90.setUnderline(0.1f, -2f);
//		p1.add(p90);

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

		Font fontTableHeading2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=\"" + file_name + ".pdf\"");

		
		
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_SUMMARY_TECH> summarytechinspDataResult = (List<TB_MISO_INSP_SUMMARY_TECH>) model.get("summaryOfTechnicalInspection1");
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_OUTSTANDING_RENT_ALLIED> getoutstandingData = (List<TB_INSP_OUTSTANDING_RENT_ALLIED>) model.get("outstandingRentAllied");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_SUMMARY_TECH_OTHER> summaryOfTechnicalInspection2 = (List<TB_MISO_INSP_SUMMARY_TECH_OTHER>) model.get("SummaryofTechnicalInspection2");

		@SuppressWarnings("unchecked")
		List<TB_INSP_OUTSTANDING_LOSS_MT> getoutstandingLossMTData = (List<TB_INSP_OUTSTANDING_LOSS_MT>) model.get("outstandingLossMt");

		@SuppressWarnings("unchecked")
		List<TB_INSP_FATAL_INCIDENTS> getfatalIncidentData = (List<TB_INSP_FATAL_INCIDENTS>) model.get("fatalIncident");

		@SuppressWarnings("unchecked")
		List<TB_INSP_SECURITY_LAPSES> getsequrityLapsesData = (List<TB_INSP_SECURITY_LAPSES>) model.get("securityLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_OUTSIDE_ATTACHMENTS> getoutsideAttachmentData = (List<TB_INSP_OUTSIDE_ATTACHMENTS>) model.get("outsideAttachments");
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_TD_PROCEEDED> tdOfficersDataResult = (List<TB_INSP_TD_PROCEEDED>) model.get("tdProceeded");

		@SuppressWarnings("unchecked")
		List<TB_INSP_SOCIAL_MEDIA_LAPSES> smlDataResult = (List<TB_INSP_SOCIAL_MEDIA_LAPSES>) model.get("socialMediaLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_PIO_CALL_LAPSES> pclDataResult = (List<TB_INSP_PIO_CALL_LAPSES>) model.get("pioCallLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_ESPIONAGE_LAPSES> elDataResult = (List<TB_INSP_ESPIONAGE_LAPSES>) model.get("espionageLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_CELL_PHONE_LAPSES> cpclDataResult = (List<TB_INSP_CELL_PHONE_LAPSES>) model.get("cellPhoneLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_FS_SECURITY_LAPSES> getInspFsSecurityData = (List<TB_INSP_FS_SECURITY_LAPSES>) model.get("fsSecurityLapses");

		@SuppressWarnings("unchecked")
		List<TB_INSP_LOST_CDS_DVDS> getlostCdDvdData = (List<TB_INSP_LOST_CDS_DVDS>) model.get("lostCdsDvds");

		@SuppressWarnings("unchecked")
		List<TB_INSP_LOST_ID_ECR> getlostidEcrData = (List<TB_INSP_LOST_ID_ECR>) model.get("lostIdEcr");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_LAND> landDataResult = (List<TB_MISO_INSP_LAND>) model.get("land");
		
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_BPET_RESULT> bpetResultDataResult = (List<TB_MISO_INSP_BPET_RESULT>) model.get("bpetResult");

		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_UPGRADATION>upGradationDataResult = (List<TB_MISO_INSP_UPGRADATION>) model
				.get("UpGradationData");

		@SuppressWarnings("unchecked")
		 List<TB_MISO_INSP_REG_LANG_EXAM>regLanguageExamDataResult = ( List<TB_MISO_INSP_REG_LANG_EXAM>) model
				.get("regimentalLanguage");
		
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_FINANCIAL_GRANTS>getFinancialGrantsData = (List<TB_INSP_FINANCIAL_GRANTS>) model
				.get("financialGrant");
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_REGT_FUNDS> 	getRegtFundsData = (List<TB_INSP_REGT_FUNDS>) model
				.get("regtFunds");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_TRAINING_AMMUNITION> 	gettrainningAmmunationData = (List<TB_MISO_INSP_TRAINING_AMMUNITION>) model
				.get("trainingAmmunition");
		
	
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_RPT_CLASSIFICATION_RANGES> 	getClassSaveData = (List<TB_INSP_RPT_CLASSIFICATION_RANGES>) model
				.get("availabilityRangesTbl1");
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_FFRS> 	getFfrsSaveData = (List<TB_INSP_FFRS>) model
				.get("availabilityRangesTbl2");
		
		@SuppressWarnings("unchecked")
		List<TB_INSP_OUTSTANDING_AUDIT> outstandingAuditObjectionsObservationsResult = (List<TB_INSP_OUTSTANDING_AUDIT>) model
				.get("outstandingAudit");

		

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_PPT_RESULT> pptResultDataResult = (List<TB_MISO_INSP_PPT_RESULT>) model.get("pptResult");

	
		@SuppressWarnings("unchecked")
		List<TB_DETAIL_COURSES> coursesDataResult = (List<TB_DETAIL_COURSES>) model.get("detailsOfCoursesTbl1");

//		@SuppressWarnings("unchecked")
//		List<TB_INSP_STANDARDS_ACHIEVED> tdOfficersDataResult = (List<TB_INSP_STANDARDS_ACHIEVED>) model.get("detailsOfCoursesTbl12");

	
	
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_PROMOTION_EXAM> getPromoExamData = (List<TB_MISO_INSP_PROMOTION_EXAM>) model.get("promotionExam");
		
	 
		
		
		
//		================================================NEW=======================================================
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_ESTABLISHMENT> getEstablishmentAttached = (List<TB_MISO_INSP_ESTABLISHMENT>) model.get("getEstablishmentAttached");

		@SuppressWarnings("unchecked")
		List<TB_INSP_EQUP_ANNUAL_METERAGE> getequpAnnualMeterageData = (List<TB_INSP_EQUP_ANNUAL_METERAGE>) model.get("getEqupAnnualMeterage");

		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<String>> equimentDataResult = (ArrayList<ArrayList<String>>) model.get("equipmentData");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_ANIMAL> animalDataResult = (List<TB_MISO_INSP_ANIMAL>) model.get("getAnimalData");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_DEFI_EQUP_EFFI> getEqptPdf = (List<TB_MISO_INSP_DEFI_EQUP_EFFI>) model.get("defiEqup");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_DTL_OF_EQUP_OFFROAD> getEqptPdf2 = (List<TB_MISO_INSP_DTL_OF_EQUP_OFFROAD>) model.get("defiEqupRender");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_STATE_FUNDS> getSectorStoresPdf = (List<TB_MISO_INSP_STATE_FUNDS>) model.get("stateFund");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_WT_RESULT_OTHER> getwtResultOtherData = (List<TB_MISO_INSP_WT_RESULT_OTHER>) model.get("wtResult");

		@SuppressWarnings("unchecked")
		List<TB_INSP_EDUCATION_STANDARDS> EducationStandardsData = (List<TB_INSP_EDUCATION_STANDARDS>) model.get("educationStandard");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_COURSES_CAT_A_B> getCourseCatAandBAction = (List<TB_MISO_INSP_COURSES_CAT_A_B>) model.get("availabilityOfPersonnel");

		
		@SuppressWarnings("unchecked")
		List<TB_INSP_STANDARDS_ACHIEVED> detailsOfCoursesTbl2 = (List<TB_INSP_STANDARDS_ACHIEVED>) model.get("detailsOfCoursesTbl2");


		
		Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);

		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);

		Font fontTableData = new Font(Font.HELVETICA, 12);

		Paragraph p = new Paragraph();

		Paragraph pl;

		Paragraph plv;
		Paragraph pr;

		Paragraph prv;
		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);

		PdfPTable maintenanceTable = new PdfPTable(1);
		maintenanceTable.setWidths(new float[] { 1 });
		maintenanceTable.setWidthPercentage(100);

		// Set the border for the table to NO_BORDER
		maintenanceTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		Chunk underline66 = new Chunk("Appx 'F'", fontTableHeading);
		underline66.setUnderline(0.1f, -2f);
		// Header Cells
		Paragraph paragraph1 = new Paragraph(underline66);
		PdfPCell maintenanceDateHeaderCell = new PdfPCell(paragraph1);
//		maintenanceDateHeaderCell.setMinimumHeight(30);
		maintenanceDateHeaderCell.setPaddingRight(129f);
		maintenanceDateHeaderCell.setVerticalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell.setBorder(PdfPCell.NO_BORDER); // No border for this cell

		maintenanceTable.addCell(maintenanceDateHeaderCell);

		PdfPCell maintenanceSubSystemHeaderCell = new PdfPCell(
				new Paragraph("(Refer to Paragraph 69 (a) of \nAnnual Inspection Manual)"));
		maintenanceSubSystemHeaderCell.setMinimumHeight(30);
		maintenanceSubSystemHeaderCell.setPaddingLeft(350f);
		maintenanceSubSystemHeaderCell.setVerticalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		maintenanceSubSystemHeaderCell.setBorder(PdfPCell.NO_BORDER); // No border for this cell
		maintenanceTable.addCell(maintenanceSubSystemHeaderCell);
		maintenanceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// Add the table to the document

		Chunk underline67 = new Chunk("IAFI-1131 (Revised)", fontTableHeading);
		Paragraph paragraph11 = new Paragraph(underline67);
		PdfPCell maintenanceDateHeaderCell1 = new PdfPCell(paragraph11);
//		maintenanceDateHeaderCell.setMinimumHeight(30);
		maintenanceDateHeaderCell1.setPaddingRight(70f);
		maintenanceDateHeaderCell1.setVerticalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		maintenanceDateHeaderCell1.setBorder(PdfPCell.NO_BORDER); // No border for this cell
		maintenanceDateHeaderCell1.setPaddingTop(-2f);
		maintenanceTable.addCell(maintenanceDateHeaderCell1);
		maintenanceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

		document.add(maintenanceTable);

		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));

		Chunk underline68 = new Chunk("ANNUAL INSPECTION REPORT", fontTableHeading);
		Paragraph headingParagraph = new Paragraph();
		headingParagraph.add(underline68);
		headingParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph);

		document.add(new Phrase("\n"));

		Font fontTable_large = FontFactory.getFont(FontFactory.TIMES, 14);

		Chunk underline69 = new Chunk("For the Year__________", fontTableHeading);
//		underline69.setUnderline(0.1f, -2f); // Set underline
		Paragraph headingParagraph1 = new Paragraph();
		headingParagraph1.add(underline69);
		headingParagraph1.setAlignment(Element.ALIGN_RIGHT);
		document.add(headingParagraph1);

		document.add(new Phrase("\n"));
		document.add(new Phrase("\n"));

		PdfPTable table_text = new PdfPTable(2);
		table_text.setWidthPercentage(100);
		float[] columnWidthstext = { 6f, 3f }; // Adjust widths as needed
		table_text.setWidths(columnWidthstext);

		// Header Row without borders
		table_text.addCell(createNoBordertext("1.       ", "Formation in which serving.", fontTable_large));
		table_text.addCell(createNoBordertext("", ": _____________", fontTable_large));

		table_text.addCell(createNoBordertext("2.       ", "Station at which inspection conducted.", fontTable_large));
		table_text.addCell(createNoBordertext("", ": _____________", fontTable_large));

		table_text.addCell(createNoBordertext("3.       ",
				"Stations at which serving/ served during the \n          past five years with dates (for active units\n          only).",
				fontTable_large));
		table_text.addCell(createNoBordertext("", ":_____________\n(_____________)", fontTable_large));

		table_text.addCell(createNoBordertext("", "", fontTable_large));
		table_text.addCell(createNoBordertext("", ":_____________\n(_____________)", fontTable_large));

		table_text.addCell(createNoBordertext("", "", fontTable_large));
		table_text.addCell(createNoBordertext("", ":_____________\n(_____________)", fontTable_large));

		table_text.addCell(createNoBordertext("", "", fontTable_large));
		table_text.addCell(createNoBordertext("", ":_____________\n(_____________)", fontTable_large));

		table_text.addCell(createNoBordertext("4.       ", "Inspection by", fontTable_large));
		table_text.addCell(createNoBordertext("", ": _____________", fontTable_large));

		table_text.addCell(createNoBordertext("5.       ", "Date", fontTable_large));
		table_text.addCell(createNoBordertext("", ": _____________", fontTable_large));

		table_text.addCell(createNoBordertext("6.       ", "Period covered by the report", fontTable_large));
		table_text.addCell(createNoBordertext("", ": _____________", fontTable_large));

		// Add the table to the document
		table_text.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table_text);

		document.add(new Phrase("\n")); // Add some space before the new page

//		-------------------------------new page----------------------------------
		document.newPage();

		Chunk underline70 = new Chunk("PART - I", fontTableHeading);
		underline70.setUnderline(0.1f, -2f); // Set underline
		Paragraph headingParagraph70 = new Paragraph();
		headingParagraph70.add(underline70);
		headingParagraph70.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph70);

		document.add(new Phrase("\n"));

		Font fontTableValue_tital = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		String informationText = "INFORMATION TO BE SUPPLIED BY OFFICER COMMANDING UNIT/ COMMANDANT\n ESTABLISHMENT/ FORMATION COMMANDER\n"
				+ "(IN RESPECT OF PERMANENT STAFF ONLY FOR\n CATEGORY 'A'/ 'B' ESTABLISHMENTS)";
		Paragraph informationParagraph = new Paragraph(informationText, fontTableValue_tital);
		informationParagraph.setAlignment(Element.ALIGN_CENTER); // Center align the text
		document.add(informationParagraph);
		document.add(new Phrase("\n"));

//		----------------------------page 2-----------------------------

		PdfPTable tableleftFM = new PdfPTable(2);
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable tablecenter = new PdfPTable(2);
		tablecenter.setWidthPercentage(100);
		tablecenter.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable tablerightFM = new PdfPTable(2);
		tablerightFM.setWidthPercentage(100);
		tablerightFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Chunk numberChunk = new Chunk("1.    ", fontTableHeading);

		Chunk establishmentChunk = new Chunk("Establishment.", fontTableHeading);
		establishmentChunk.setUnderline(0.1f, -2f);

		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(establishmentChunk);

		PdfPCell establishmentCell = new PdfPCell(paragraph);
		establishmentCell.setBorder(PdfPCell.NO_BORDER);
		establishmentCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		establishmentCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		establishmentCell.setPaddingLeft(-5f);
		tableleftFM.addCell(establishmentCell);

		PdfPCell testCell = new PdfPCell(new Paragraph("", fontTableHeading));
		testCell.setBorder(PdfPCell.NO_BORDER);
		testCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		testCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableleftFM.addCell(testCell);
		// Add the cell to the left table
		
		
		
		document.add(tableleftFM);

		document.add(new Phrase("\n"));

		PdfPTable personnelTable = new PdfPTable(5); // 5 columns
		personnelTable.setWidthPercentage(100); // Set table width to 100%

		// Create the first row with main headers
		float[] columnWidths = new float[] { 1.5f, 1f, 1f, 1f, 1.5f }; // Adjust these values as needed
		personnelTable.setWidths(columnWidths);

		// Create the first row with main headers
		PdfPCell personnelCell = createCenteredCell("Personnel", fontTableHeading);
		personnelCell.setRowspan(2); // Rowspan of 2 for "Personnel"
		// personnelCell.setBackgroundColor(new BaseColor(211, 211, 211)); // Light gray
		// color
		personnelTable.addCell(personnelCell);

		// Create the "Nos of Personnel" header with colspan
		PdfPCell nosOfPersonnelCell = createCenteredCell("Nos of Personnel", fontTableHeading);
		nosOfPersonnelCell.setColspan(3); // Colspan of 3 for "Nos of Personnel"
//		nosOfPersonnelCell.setBackgroundColor(new BaseColor(211, 211, 211)); // Light gray color
		personnelTable.addCell(nosOfPersonnelCell);

		// Create the "Not Fully Effective due to Low Medical Category" header
		PdfPCell notFullyEffectiveCell = createCenteredCell("Not Fully Effective due to Low Medical Category",
				fontTableHeading);
		notFullyEffectiveCell.setRowspan(2); // Rowspan of 2
//		notFullyEffectiveCell.setBackgroundColor(new BaseColor(211, 211, 211)); // Light gray color
		personnelTable.addCell(notFullyEffectiveCell);

		// Create the second row with sub-headers
		personnelTable.addCell(createCenteredCell("Authorised", fontTableHeading));
		personnelTable.addCell(createCenteredCell("Posted", fontTableHeading));
		personnelTable.addCell(createCenteredCell("Surplus/Deficient", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e)
		personnelTable.addCell(createCenteredCell("(a)", fontTableHeading));
		personnelTable.addCell(createCenteredCell("(b)", fontTableHeading));
		personnelTable.addCell(createCenteredCell("(c)", fontTableHeading));
		personnelTable.addCell(createCenteredCell("(d)", fontTableHeading));
		personnelTable.addCell(createCenteredCell("(e)", fontTableHeading));

		// Add the "Regular" personnel category
		Font boldUnderlineFont = new Font(Font.HELVETICA, 12, Font.BOLD);
		Paragraph regularParagraph = new Paragraph();
		regularParagraph.add(new Chunk("(a) Regular\n", boldUnderlineFont));
		regularParagraph.add(new Chunk("    (i) Officers\n", fontTableHeadingNonBoldData));
		regularParagraph.add(new Chunk("    (ii) JCOs\n", fontTableHeadingNonBoldData));
		regularParagraph.add(new Chunk("    (iii) Armament Art/\n    Warrant Officers\n", fontTableHeadingNonBoldData));
		regularParagraph.add(new Chunk("    (iv) OR\n", fontTableHeadingNonBoldData));
		regularParagraph.add(new Chunk("    (v) Civilians\n", fontTableHeadingNonBoldData));

		// Create a cell for the regular personnel
		PdfPCell regularCell = new PdfPCell(regularParagraph);
		regularCell.setPaddingLeft(10f); // Set left padding
		regularCell.setPaddingRight(10f); // Optional: Set right padding
		regularCell.setPaddingTop(5f); // Optional: Set top padding
		regularCell.setPaddingBottom(5f); // Optional: Set bottom padding
		personnelTable.addCell(regularCell);

		// Add empty cells for (b), (c), (d), (e)  equimentDataResult
		
		if(getEstablishmentAttached.size()>0)
		{
			
		
		
		
		
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getReg_offrs_auth()
				+"\n"+getEstablishmentAttached.get(0).getReg_jco_auth()+"\n"
				+getEstablishmentAttached.get(0).getReg_warrant_auth()+"\n\n"
				+getEstablishmentAttached.get(0).getReg_or_auth()+"\n"
				+getEstablishmentAttached.get(0).getReg_civ_auth()+"\n"
				,fontTableHeadingNonBoldData)
);
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getReg_offrs_posted()
				+"\n"+getEstablishmentAttached.get(0).getReg_jco_posted()+"\n"
				+getEstablishmentAttached.get(0).getReg_warrant_posted()+"\n\n"
				+getEstablishmentAttached.get(0).getReg_or_posted()+"\n"
				+getEstablishmentAttached.get(0).getReg_civ_posted()+"\n"
				,fontTableHeadingNonBoldData)
);
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getReg_offrs_sur()+"/"+getEstablishmentAttached.get(0).getReg_offrs_defi()
				+"\n"+getEstablishmentAttached.get(0).getReg_jco_sur()+"/"+getEstablishmentAttached.get(0).getReg_jco_defi()+"\n"
				+getEstablishmentAttached.get(0).getReg_warrant_sur()+"/"+getEstablishmentAttached.get(0).getReg_warrant_defi()+"\n\n"
				+getEstablishmentAttached.get(0).getReg_or_sur()+"/"+getEstablishmentAttached.get(0).getReg_or_defi()+"\n"
				+getEstablishmentAttached.get(0).getReg_civ_sur()+"/"+getEstablishmentAttached.get(0).getReg_civ_defi()+"\n"
				,fontTableHeadingNonBoldData)
);
		personnelTable.addCell(createCenteredCell("",fontTableHeadingNonBoldData));


		
	
		
		
		
		// Add the "Attached" personnel category
		Paragraph attachedParagraph = new Paragraph();
		attachedParagraph.add(new Chunk("(a) Attached\n", boldUnderlineFont));
		attachedParagraph.add(new Chunk("    (i) Officers\n", fontTableHeadingNonBoldData));
		attachedParagraph.add(new Chunk("    (ii) JCOs\n", fontTableHeadingNonBoldData));
		attachedParagraph
				.add(new Chunk("    (iii) Armament Art/\n    Warrant Officers\n", fontTableHeadingNonBoldData));
		attachedParagraph.add(new Chunk("    (iv) OR\n", fontTableHeadingNonBoldData));
		attachedParagraph.add(new Chunk("    (v) Civilians\n", fontTableHeadingNonBoldData));

		// Create a cell for the attached personnel
		PdfPCell attachedCell = new PdfPCell(attachedParagraph);
		attachedCell.setPaddingLeft(10f); // Set left padding
		attachedCell.setPaddingRight(10f); // Optional: Set right padding
		attachedCell.setPaddingTop(5f); // Optional: Set top padding
		attachedCell.setPaddingBottom(5f); // Optional: Set bottom padding
		personnelTable.addCell(attachedCell);

		// Add empty cells for (b), (c), (d), (e) for Attached
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getAtted_offrs_auth()
				+"\n"+getEstablishmentAttached.get(0).getAtted_jco_auth()+"\n"
				+getEstablishmentAttached.get(0).getAtted_warrant_auth()+"\n\n"
				+getEstablishmentAttached.get(0).getAtted_or_auth()+"\n"
				+getEstablishmentAttached.get(0).getAtted_civ_auth()+"\n"
				,fontTableHeadingNonBoldData)
);
		
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getAtted_offrs_posted()
				+"\n"+getEstablishmentAttached.get(0).getAtted_jco_posted()+"\n"
				+getEstablishmentAttached.get(0).getAtted_warrant_posted()+"\n\n"
				+getEstablishmentAttached.get(0).getAtted_or_posted()+"\n"
				+getEstablishmentAttached.get(0).getAtted_civ_posted()+"\n"
				,fontTableHeadingNonBoldData)
);
		
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getAtted_offrs_sur()+"/"+getEstablishmentAttached.get(0).getAtted_offrs_defi()
				+"\n"+getEstablishmentAttached.get(0).getAtted_jco_sur()+"/"+getEstablishmentAttached.get(0).getAtted_jco_defi()+"\n"
				+getEstablishmentAttached.get(0).getAtted_warrant_sur()+"/"+getEstablishmentAttached.get(0).getAtted_warrant_defi()+"\n\n"
				+getEstablishmentAttached.get(0).getAtted_or_sur()+"/"+getEstablishmentAttached.get(0).getAtted_or_defi()+"\n"
				+getEstablishmentAttached.get(0).getAtted_civ_sur()+"/"+getEstablishmentAttached.get(0).getAtted_civ_defi()+"\n"
				,fontTableHeadingNonBoldData)
);
		personnelTable.addCell(createCenteredCell("\n"+getEstablishmentAttached.get(0).getAtted_offrs_medcat()
				+"\n"+getEstablishmentAttached.get(0).getAtted_jco_medcat()+"\n"
				+getEstablishmentAttached.get(0).getAtted_warrant_medcat()+"\n\n"
				+getEstablishmentAttached.get(0).getAtted_or_medcat()+"\n"
				+getEstablishmentAttached.get(0).getAtted_civ_medcat()+"\n"
				,fontTableHeadingNonBoldData)
);
		}
		personnelTable.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(personnelTable);

//		------------------------------- page 3-------------------------------------
		document.newPage();

		PdfPTable tableleftFM2 = new PdfPTable(2);
		tableleftFM2.setWidthPercentage(100);
		tableleftFM2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell1 = createNumberedCell("2.    ", "Equipment.", fontTableHeading);
		establishmentCell1.setPaddingTop(10f);
		tableleftFM2.addCell(establishmentCell1);

		PdfPCell establishmentCell2 = createNumberedCell("", "", fontTableHeading);
		tableleftFM2.addCell(establishmentCell2);
		document.add(tableleftFM2);

		// Create a new paragraph for the additional text
		Paragraph additionalTextParagraph = new Paragraph("(a) 'A' Vehicles.", fontTableHeading);
		additionalTextParagraph.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph);

		PdfPTable table = new PdfPTable(13); // 13 columns
		table.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths1 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		table.setWidths(columnWidths1);

		// Create the first row with main headers
		PdfPCell typeCell = new PdfPCell(new Phrase("Type", fontTableHeading));
		typeCell.setRowspan(2);
		typeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		typeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(typeCell);

		PdfPCell authorizedCell = new PdfPCell(new Phrase("Authorized", fontTableHeading));
		authorizedCell.setRowspan(2);
		authorizedCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		authorizedCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(authorizedCell);

		PdfPCell heldCell = new PdfPCell(new Phrase("Held", fontTableHeading));
		heldCell.setRowspan(2);
		heldCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		heldCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(heldCell);

		PdfPCell surplusDeficiencyCell = new PdfPCell(new Phrase("Surplus/Deficiency", fontTableHeading));
		surplusDeficiencyCell.setRowspan(2);
		surplusDeficiencyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		surplusDeficiencyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(surplusDeficiencyCell);

		// Create the "Mission Reliability" header with colspan
		PdfPCell missionReliabilityCell = new PdfPCell(
				new Phrase("Mission Reliability in % / Classification", fontTableHeading));
		missionReliabilityCell.setColspan(5);
		missionReliabilityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		missionReliabilityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(missionReliabilityCell);

		// Create the "Serviceability" header with colspan
		PdfPCell serviceabilityCell = new PdfPCell(new Phrase("Serviceability", fontTableHeading));
		serviceabilityCell.setColspan(3);
		serviceabilityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(serviceabilityCell);

		PdfPCell detailofremarksCell = new PdfPCell(new Phrase("Detailed Remark", fontTableHeading));
		detailofremarksCell.setRowspan(2);
		detailofremarksCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		detailofremarksCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(detailofremarksCell);

		// Create the second row with sub-headers for Mission Reliability
		table.addCell(createCenteredCell("I", fontTableHeading));
		table.addCell(createCenteredCell("II", fontTableHeading));
		table.addCell(createCenteredCell("III", fontTableHeading));
		table.addCell(createCenteredCell("IV", fontTableHeading));
		table.addCell(createCenteredCell("V & VI", fontTableHeading));

		// Create the sub-headers for Serviceability
		table.addCell(createCenteredCell("Armament/Quarter of Life / Category", fontTableHeading));
		table.addCell(createCenteredCell("Communication Equipment", fontTableHeading));
		table.addCell(createCenteredCell("Night Vision Devices", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i), (j),
		// (k), (l), (m), (n)
		table.addCell(createCenteredCell("(a)", fontTableHeading));
		table.addCell(createCenteredCell("(b)", fontTableHeading));
		table.addCell(createCenteredCell("(c)", fontTableHeading));
		table.addCell(createCenteredCell("(d)", fontTableHeading));
		table.addCell(createCenteredCell("(e)", fontTableHeading));
		table.addCell(createCenteredCell("(g)", fontTableHeading));
		table.addCell(createCenteredCell("(h)", fontTableHeading));
		table.addCell(createCenteredCell("(i)", fontTableHeading));
		table.addCell(createCenteredCell("(j)", fontTableHeading));
		table.addCell(createCenteredCell("(k)", fontTableHeading));
		table.addCell(createCenteredCell("(l)", fontTableHeading));
		table.addCell(createCenteredCell("(m)", fontTableHeading));
		table.addCell(createCenteredCell("(n)", fontTableHeading));

		table.addCell(createCenteredCell(equimentDataResult.get(0).get(4), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(5), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(6), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(7)+"/"+equimentDataResult.get(0).get(8), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(9), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(10), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(11), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(12), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(13), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(14), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(15), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(16), fontTableHeadingNonBoldData));
		table.addCell(createCenteredCell(equimentDataResult.get(0).get(17), fontTableHeadingNonBoldData));
		table.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table);

		Paragraph additionalTextParagraph1 = new Paragraph("(B) 'B' & 'C' Vehicles.", fontTableHeading);
		additionalTextParagraph1.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph1.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph1.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph1);
//		document.add(new Phrase("\n"));

		// Create a new PdfPTable with the desired number of columns
		PdfPTable table2 = new PdfPTable(10); // 10 columns
		table2.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths2 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f }; // Adjust as needed
		table2.setWidths(columnWidths2);

		// Create the first row with main headers
		PdfPCell typeCell2 = new PdfPCell(new Phrase("Type", fontTableHeading));
		typeCell2.setRowspan(2);
		typeCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		typeCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(typeCell2);

		PdfPCell authorizedCell2 = new PdfPCell(new Phrase("Authorized", fontTableHeading));
		authorizedCell2.setRowspan(2);
		authorizedCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		authorizedCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(authorizedCell2);

		PdfPCell heldCell2 = new PdfPCell(new Phrase("Held", fontTableHeading));
		heldCell2.setRowspan(2);
		heldCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		heldCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(heldCell2);

		PdfPCell surplusDeficiencyCell2 = new PdfPCell(new Phrase("Surplus/Deficiency", fontTableHeading));
		surplusDeficiencyCell2.setRowspan(2);
		surplusDeficiencyCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		surplusDeficiencyCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(surplusDeficiencyCell2);

		// Create the "Mission Reliability" header with colspan
		PdfPCell missionReliabilityCell2 = new PdfPCell(
				new Phrase("Mission Reliability in % / Classification", fontTableHeading));
		missionReliabilityCell2.setColspan(5); // This will take 5 columns
		missionReliabilityCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		missionReliabilityCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(missionReliabilityCell2);

		// Create the "Detailed Remark" header with rowspan
		PdfPCell detailedRemarkCell2 = new PdfPCell(new Phrase("Detailed Remark", fontTableHeading));
		detailedRemarkCell2.setRowspan(2);
		detailedRemarkCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		detailedRemarkCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell(detailedRemarkCell2);

		// Create the second row with sub-headers for Mission Reliability
		table2.addCell(createCenteredCell("I", fontTableHeading));
		table2.addCell(createCenteredCell("II", fontTableHeading));
		table2.addCell(createCenteredCell("III", fontTableHeading));
		table2.addCell(createCenteredCell("IV", fontTableHeading));
		table2.addCell(createCenteredCell("V & VI", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i), (j),
		// (k)
		table2.addCell(createCenteredCell("(a)", fontTableHeading));
		table2.addCell(createCenteredCell("(b)", fontTableHeading));
		table2.addCell(createCenteredCell("(c)", fontTableHeading));
		table2.addCell(createCenteredCell("(d)", fontTableHeading));
		table2.addCell(createCenteredCell("(e)", fontTableHeading));
		table2.addCell(createCenteredCell("(g)", fontTableHeading));
		table2.addCell(createCenteredCell("(h)", fontTableHeading));
		table2.addCell(createCenteredCell("(i)", fontTableHeading));
		table2.addCell(createCenteredCell("(j)", fontTableHeading));
		table2.addCell(createCenteredCell("(k)", fontTableHeading));


		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(4), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(5), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(6), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(7)+"/"+equimentDataResult.get(0).get(8), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(9), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(10), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(11), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(12), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(1).get(13), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(17), fontTableHeadingNonBoldData));
		
		
		
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(4), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(5), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(6), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(7)+"/"+equimentDataResult.get(2).get(8), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(9), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(10), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(11), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(12), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(13), fontTableHeadingNonBoldData));
		table2.addCell(createCenteredCell(equimentDataResult.get(2).get(17), fontTableHeadingNonBoldData));
		// Add the table2 to the document
		table2.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table2);

		Paragraph additionalTextParagraph2 = new Paragraph("(C) State of Annual Meterage.", fontTableHeading);
		additionalTextParagraph2.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph2.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph2.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph2);

		PdfPTable table3 = new PdfPTable(5); // 5 columns
		table3.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths3 = new float[] { 1f, 3f, 1f, 1f, 2f }; // Adjust as needed
		table3.setWidths(columnWidths3);

		// Create the first row with main headers
		PdfPCell serialNumberCell3 = new PdfPCell(new Phrase("Serial Number", fontTableHeading));
		serialNumberCell3.setRowspan(2);
		serialNumberCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		serialNumberCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.addCell(serialNumberCell3);

		PdfPCell typeOfDutyCell3 = new PdfPCell(
				new Phrase("Type of Duty ie Administration, Training and Formation Detailment etc", fontTableHeading));
		typeOfDutyCell3.setRowspan(2);
		typeOfDutyCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		typeOfDutyCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.addCell(typeOfDutyCell3);

		// Create the "Total Mileage During the Year" header with colspan
		PdfPCell totalMileageCell3 = new PdfPCell(new Phrase("Total Mileage During the Year", fontTableHeading));
		totalMileageCell3.setColspan(2); // This will take 2 columns
		totalMileageCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		totalMileageCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.addCell(totalMileageCell3);

		// Create the "Detailed Remarks" header with rowspan
		PdfPCell detailedRemarksCell3 = new PdfPCell(new Phrase("Detailed Remarks", fontTableHeading));
		detailedRemarksCell3.setRowspan(2);
		detailedRemarksCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		detailedRemarksCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.addCell(detailedRemarksCell3);

		// Create the second row with sub-headers for Total Mileage
		table3.addCell(createCenteredCell("Authorised", fontTableHeading));
		table3.addCell(createCenteredCell("Covered", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e)
		table3.addCell(createCenteredCell("(a)", fontTableHeading));
		table3.addCell(createCenteredCell("(b)", fontTableHeading));
		table3.addCell(createCenteredCell("(c)", fontTableHeading));
		table3.addCell(createCenteredCell("(d)", fontTableHeading));
		table3.addCell(createCenteredCell("(e)", fontTableHeading));

		if(getequpAnnualMeterageData.size()<0)
		{
			table3.addCell(createCenteredCell(" ", fontTableHeading));
			table3.addCell(createCenteredCell(" ", fontTableHeading));
			table3.addCell(createCenteredCell(" ", fontTableHeading));
			table3.addCell(createCenteredCell(" ", fontTableHeading));
			table3.addCell(createCenteredCell(" ", fontTableHeading));
		}
		if(getequpAnnualMeterageData.size()>0)
		{
			for(int i=0;i<getequpAnnualMeterageData.size();i++)
			{ 
				table3.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table3.addCell(createCenteredCell(getequpAnnualMeterageData.get(i).getType_of_duty(), fontTableHeadingNonBoldData));
				table3.addCell(createCenteredCell(getequpAnnualMeterageData.get(i).getEqup_authorize(), fontTableHeadingNonBoldData));
				table3.addCell(createCenteredCell(getequpAnnualMeterageData.get(i).getEqup_cove(), fontTableHeadingNonBoldData));
				table3.addCell(createCenteredCell(getequpAnnualMeterageData.get(i).getEqup_remark(), fontTableHeadingNonBoldData));
				
			}
			
		}
	
		// Add the table to the document
		table3.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table3);
//		----------------------------------------------------------------------------------

//		-----------------------------------page 4------------------------------------------

		document.newPage();

		PdfPTable tableleftFM3 = new PdfPTable(2);
		tableleftFM3.setWidthPercentage(100);
		tableleftFM3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell3 = createNumberedCell("3.    ", "Animals.", fontTableHeading);
		establishmentCell3.setPaddingTop(10f);
		tableleftFM3.addCell(establishmentCell3);

		PdfPCell establishmentCell4 = createNumberedCell("", "", fontTableHeading);
		tableleftFM3.addCell(establishmentCell4);
		document.add(tableleftFM3);

		document.add(new Phrase("\n"));

		// Create a new PdfPTable with the desired number of columns
		PdfPTable table4 = new PdfPTable(15); // 14 columns
		table4.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths4 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f }; // Adjust as
																											// needed
		table4.setWidths(columnWidths4);

		// Create the first row with main headers
		PdfPCell typeCell4 = new PdfPCell(new Phrase("Type", fontTableHeading));
		typeCell4.setRowspan(2);
		typeCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		typeCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table4.addCell(typeCell4);

		// Create the "Authorised" header with colspan
		PdfPCell authorisedCell4 = new PdfPCell(new Phrase("Authorised", fontTableHeading));
		authorisedCell4.setRowspan(2);
		authorisedCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		authorisedCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table4.addCell(authorisedCell4);

		// Create the "Held" header
		PdfPCell heldCell4 = new PdfPCell(new Phrase("Held", fontTableHeading));
		heldCell4.setRowspan(2);
		heldCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		heldCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table4.addCell(heldCell4);

		// Create the "Suplus/ Deficiency" header
		PdfPCell surplusDeficiencyCell4 = new PdfPCell(new Phrase("Suplus/ Deficiency", fontTableHeading));
		surplusDeficiencyCell4.setRowspan(2);
		surplusDeficiencyCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		surplusDeficiencyCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table4.addCell(surplusDeficiencyCell4);

		// Create the "Station of Animals" header with colspan
		PdfPCell stationOfAnimalsCell4 = new PdfPCell(new Phrase("Station of Animals", fontTableHeading));
		stationOfAnimalsCell4.setColspan(11);
		stationOfAnimalsCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		stationOfAnimalsCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table4.addCell(stationOfAnimalsCell4);

		// Create the second row with sub-headers for Station of Animals
		table4.addCell(createCenteredCell("Condition", fontTableHeading));
		table4.addCell(createCenteredCell("Treatment", fontTableHeading));
		table4.addCell(createCenteredCell("Grooming", fontTableHeading));
		table4.addCell(createCenteredCell("Feeding", fontTableHeading));
		table4.addCell(createCenteredCell("Watering", fontTableHeading));
		table4.addCell(createCenteredCell("Clipping", fontTableHeading));
		table4.addCell(createCenteredCell("Shoeing and Care of Feet", fontTableHeading));
		table4.addCell(createCenteredCell("Saddlery", fontTableHeading));
		table4.addCell(createCenteredCell("Line Gear", fontTableHeading));
		table4.addCell(createCenteredCell("Accommodation", fontTableHeading));
		table4.addCell(createCenteredCell("Remarks", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i), (j),
		// (k), (l), (m), (n), (o), (q)
		table4.addCell(createCenteredCell("(a)", fontTableHeading));
		table4.addCell(createCenteredCell("(b)", fontTableHeading));
		table4.addCell(createCenteredCell("(c)", fontTableHeading));
		table4.addCell(createCenteredCell("(d)", fontTableHeading));
		table4.addCell(createCenteredCell("(e)", fontTableHeading));
		table4.addCell(createCenteredCell("(f)", fontTableHeading));
		table4.addCell(createCenteredCell("(g)", fontTableHeading));
		table4.addCell(createCenteredCell("(h)", fontTableHeading));
		table4.addCell(createCenteredCell("(i)", fontTableHeading));
		table4.addCell(createCenteredCell("(j)", fontTableHeading));
		table4.addCell(createCenteredCell("(k)", fontTableHeading));
		table4.addCell(createCenteredCell("(l)", fontTableHeading));
		table4.addCell(createCenteredCell("(m)", fontTableHeading));
		table4.addCell(createCenteredCell("(n)", fontTableHeading));
		table4.addCell(createCenteredCell("(o)", fontTableHeading));
if(animalDataResult.size()<0)
{
	for (char letter = 'a'; letter <= 'o'; letter++) {
		table4.addCell(createCenteredCell(" ", fontTableHeading));
	}
}
if(animalDataResult.size()>0)
{
	table4.addCell(createCenteredCell(animalDataResult.get(0).getType(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getAuth(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getHeld(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getSur() + animalDataResult.get(0).getDefi(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getConditon(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getTeatment(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getGrooming(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getFeeding(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getWatering(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getClipping(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getF_feet(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getSaddlery(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getLine_gear(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getAccomodation(), fontTableHeadingNonBoldData));
	table4.addCell(createCenteredCell(animalDataResult.get(0).getRemarks(), fontTableHeadingNonBoldData));
	

}

table4.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table4);

		document.add(new Phrase("\n"));

		PdfPTable tableleftFM4 = new PdfPTable(1);
		tableleftFM4.setWidthPercentage(100);
		tableleftFM4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell5 = createNumberedCell("4.    ",
				"Deficiencies of Equipment/Stores including Arms/ Ammunition Affecting Maintenance Efficiency.",
				fontTableHeading);
		tableleftFM4.addCell(establishmentCell5);

		PdfPCell establishmentCell6 = createNumberedCell("", "", fontTableHeading);
		tableleftFM4.addCell(establishmentCell6);
		document.add(tableleftFM4);

		document.add(new Phrase("\n"));

		// Create a new PdfPTable with the desired number of columns
		PdfPTable table5 = new PdfPTable(9); // 9 columns
		table5.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths5 = new float[] { 1f, 3f, 1f, 1f, 1f, 1f, 1f, 1f, 1f }; // Adjust as needed
		table5.setWidths(columnWidths5);

		// Create the first row with main headers
		PdfPCell serialNumberCell5 = new PdfPCell(new Phrase("Serial Number", fontTableHeading));
		serialNumberCell5.setRowspan(2);
		serialNumberCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		serialNumberCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(serialNumberCell5);

		PdfPCell nomenclatureCell5 = new PdfPCell(new Phrase("Nomenclature", fontTableHeading));
		nomenclatureCell5.setRowspan(2);
		nomenclatureCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		nomenclatureCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(nomenclatureCell5);

		PdfPCell auCell5 = new PdfPCell(new Phrase("A/U", fontTableHeading));
		auCell5.setRowspan(2);
		auCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		auCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(auCell5);

		// Create the "Quantity" header with colspan
		PdfPCell quantityCell5 = new PdfPCell(new Phrase("Quantity", fontTableHeading));
		quantityCell5.setColspan(3);
		quantityCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		quantityCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(quantityCell5);

		// Create the "Not to be included in Holding" header with colspan
		PdfPCell DuesInCell5 = new PdfPCell(new Phrase("Dues In", fontTableHeading));
		DuesInCell5.setColspan(1);
		DuesInCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		DuesInCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(DuesInCell5);

		PdfPCell DuesOutCell5 = new PdfPCell(new Phrase("Dues Out", fontTableHeading));
		DuesOutCell5.setRowspan(1);
		DuesOutCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		DuesOutCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(DuesOutCell5);

		// Create the "Remarks" header
		PdfPCell remarksCell5 = new PdfPCell(new Phrase("Remarks", fontTableHeading));
		remarksCell5.setRowspan(2);
		remarksCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		remarksCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(remarksCell5);

		// Create the second row with sub-headers for Quantity and Dues
		table5.addCell(createCenteredCell("Authorised", fontTableHeading));
		table5.addCell(createCenteredCell("Held", fontTableHeading));
		table5.addCell(createCenteredCell("Deficiency", fontTableHeading));

		PdfPCell notIncludedCell5 = new PdfPCell(new Phrase("(Not to be included in Holding)", fontTableHeading));
		notIncludedCell5.setColspan(2);
		notIncludedCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		notIncludedCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table5.addCell(notIncludedCell5);

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i), (j)
		table5.addCell(createCenteredCell("(a)", fontTableHeading));
		table5.addCell(createCenteredCell("(b)", fontTableHeading));
		table5.addCell(createCenteredCell("(c)", fontTableHeading));
		table5.addCell(createCenteredCell("(d)", fontTableHeading));
		table5.addCell(createCenteredCell("(e)", fontTableHeading));
		table5.addCell(createCenteredCell("(g)", fontTableHeading));
		table5.addCell(createCenteredCell("(h)", fontTableHeading));
		table5.addCell(createCenteredCell("(i)", fontTableHeading));
		table5.addCell(createCenteredCell("(j)", fontTableHeading));
if(getEqptPdf.size()<0)
{
		table5.addCell(createCenteredCell("", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	}
	
	
	else {
		
		for(int i=0;i<getEqptPdf.size();i++ )
		{
			table5.addCell(String.valueOf(i + 1));
			table5.addCell(createCenteredCell(getEqptPdf.get(i).getNomenclature(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell( getEqptPdf.get(i).getA(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell (getEqptPdf.get(i).getAuth(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell (getEqptPdf.get(i).getHeld(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell( getEqptPdf.get(i).getDefi(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell (getEqptPdf.get(i).getDues_in(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell (getEqptPdf.get(i).getDues_out(),fontTableHeadingNonBoldData));
			table5.addCell(createCenteredCell (getEqptPdf.get(i).getRemarks(),fontTableHeadingNonBoldData));

		}
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
		table5.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	}
	
	
		// Add the table to the document
table.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table5);

		document.add(new Phrase("\n"));

		PdfPTable tableleftFM5 = new PdfPTable(1);
		tableleftFM5.setWidthPercentage(100);
		tableleftFM5.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell7 = createNumberedCell("5.    ",
				"Details of Equipment Rendered Off Road for a prolonged period (more than Six Months).",
				fontTableHeading);
		tableleftFM5.addCell(establishmentCell7);

		PdfPCell establishmentCell8 = createNumberedCell("", "", fontTableHeading);
		tableleftFM5.addCell(establishmentCell8);
		document.add(tableleftFM5);

		document.add(new Phrase("\n"));

		// Create a new PdfPTable with the desired number of columns
		PdfPTable table6 = new PdfPTable(9); // 9 columns
		table6.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths6 = new float[] { 1f, 3f, 1f, 1f, 1f, 2f, 2f, 2f, 1f }; // Adjust as needed
		table6.setWidths(columnWidths6);

		// Create the first row with main headers
		PdfPCell serialNumberCell6 = new PdfPCell(new Phrase("Serial Number", fontTableHeading));
		serialNumberCell6.setRowspan(2);
		serialNumberCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		serialNumberCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(serialNumberCell6);

		PdfPCell nomenclatureCell6 = new PdfPCell(new Phrase("Nomenclature", fontTableHeading));
		nomenclatureCell6.setRowspan(2);
		nomenclatureCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		nomenclatureCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(nomenclatureCell6);

		PdfPCell auCell6 = new PdfPCell(new Phrase("A/U", fontTableHeading));
		auCell6.setRowspan(2);
		auCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		auCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(auCell6);

		// Create the "Qty" header with colspan
		PdfPCell qtyCell6 = new PdfPCell(new Phrase("Qty", fontTableHeading));
		qtyCell6.setColspan(3);
		qtyCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		qtyCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(qtyCell6);

		// Create the "Off Road with Reason" header
		PdfPCell offRoadCell6 = new PdfPCell(new Phrase("Off Road with Reason", fontTableHeading));
		offRoadCell6.setRowspan(2);
		offRoadCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		offRoadCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(offRoadCell6);

		// Create the "Action Taken by the Unit" header
		PdfPCell actionTakenCell6 = new PdfPCell(new Phrase("Action Taken by the Unit", fontTableHeading));
		actionTakenCell6.setRowspan(2);
		actionTakenCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		actionTakenCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(actionTakenCell6);

		// Create the "Remarks" header
		PdfPCell remarksCell6 = new PdfPCell(new Phrase("Remarks", fontTableHeading));
		remarksCell6.setRowspan(2);
		remarksCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		remarksCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table6.addCell(remarksCell6);

		// Create the second row with sub-headers for Qty
		table6.addCell(createCenteredCell("Authorised", fontTableHeading));
		table6.addCell(createCenteredCell("Held", fontTableHeading));
		table6.addCell(createCenteredCell("Deficiency", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i), (j)
		table6.addCell(createCenteredCell("(a)", fontTableHeading));
		table6.addCell(createCenteredCell("(b)", fontTableHeading));
		table6.addCell(createCenteredCell("(c)", fontTableHeading));
		table6.addCell(createCenteredCell("(d)", fontTableHeading));
		table6.addCell(createCenteredCell("(e)", fontTableHeading));
		table6.addCell(createCenteredCell("(f)", fontTableHeading));
		table6.addCell(createCenteredCell("(g)", fontTableHeading));
		table6.addCell(createCenteredCell("(h)", fontTableHeading));
		table6.addCell(createCenteredCell("(i)", fontTableHeading));

		
if(getEqptPdf2.size()<0)
{
	
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	table6.addCell(createCenteredCell(" ", fontTableHeading));
	
}


if(getEqptPdf2.size()>0)
{
	for(int i=0;i<getEqptPdf2.size();i++)
	{
		table6.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getNomenclature(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getAu(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getAuth(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getHeld(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getDefi(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getOffroad_reason(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getAction_taken_unit(), fontTableHeadingNonBoldData));
		table6.addCell(createCenteredCell(getEqptPdf2.get(i).getRemarks(), fontTableHeadingNonBoldData));
	}
	
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	table6.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
	
}
		
		
		
		
		
		// Add the table to the document
table6.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table6);

//		------------------------------------page 5-------------------------------------

//		document.newPage();

		PdfPTable tableleftFM6 = new PdfPTable(1);
		tableleftFM6.setWidthPercentage(100);
		tableleftFM6.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell9 = createNumberedCell("6.    ",
				"State of Sector Stores, Maintenance Works Stores, Stores purchased out of SAG, ACSFP Fund and Other Funds.",
				fontTableHeading);
		establishmentCell3.setPaddingTop(10f);
		tableleftFM6.addCell(establishmentCell9);

		PdfPCell establishmentCell10 = createNumberedCell("", "", fontTableHeading);
		tableleftFM6.addCell(establishmentCell10);
		document.add(tableleftFM6);

		document.add(new Phrase("\n"));

		PdfPTable table7 = new PdfPTable(9);
		table7.setWidthPercentage(100);

		// Set the widths of the columns
		float[] columnWidths7 = new float[] { 1f, 3f, 1f, 1f, 1f, 2f, 2f, 2f, 1f };
		table7.setWidths(columnWidths7);

		// Create the first row with main headers
		table7.addCell(createHeaderCell("Serial Number", fontTableHeading, 2, 1));
		table7.addCell(createHeaderCell("Nomenclature", fontTableHeading, 2, 1));
		table7.addCell(createHeaderCell("A/U", fontTableHeading, 2, 1));
		table7.addCell(createHeaderCell("Qty", fontTableHeading, 1, 3));
		table7.addCell(createHeaderCell("Serviceable / Unserviceable", fontTableHeading, 2, 1));
		table7.addCell(createHeaderCell("Reasons for Equipment being Off Rd", fontTableHeading, 2, 1));
		table7.addCell(createHeaderCell("Remarks", fontTableHeading, 2, 1));

		// Create the second row with sub-headers for Qty
		table7.addCell(createCenteredCell("Authorised", fontTableHeading));
		table7.addCell(createCenteredCell("Held", fontTableHeading));
		table7.addCell(createCenteredCell("Deficiency", fontTableHeading));

		// Create the third row with labels (a), (b), (c), (d), (e), (g), (h), (i)
		table7.addCell(createCenteredCell("(a)", fontTableHeading));
		table7.addCell(createCenteredCell("(b)", fontTableHeading));
		table7.addCell(createCenteredCell("(c)", fontTableHeading));
		table7.addCell(createCenteredCell("(d)", fontTableHeading));
		table7.addCell(createCenteredCell("(e)", fontTableHeading));
		table7.addCell(createCenteredCell("(f)", fontTableHeading));
		table7.addCell(createCenteredCell("(g)", fontTableHeading));
		table7.addCell(createCenteredCell("(h)", fontTableHeading));
		table7.addCell(createCenteredCell("(i)", fontTableHeading));

		 if (getSectorStoresPdf.size() < 0) {
			 for (char letter = 'a'; letter <= 'i'; letter++) {
					table7.addCell(createCenteredCell(" ", fontTableHeading));
				}
			}
			if (getSectorStoresPdf.size() > 0) {
				for (int i = 0; i < getoutsideAttachmentData.size(); i++) {
					table7.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getNomenclature(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getAu(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getAuth(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getHeld(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getDefi(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getSer_unser(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getReason_offrd(), fontTableHeadingNonBoldData));
					table7.addCell(createCenteredCell(getSectorStoresPdf.get(i).getRemarks(), fontTableHeadingNonBoldData));

				}

			}
			table7.setTableEvent(new ImageBackgroundEvent(request,username));
		// Add the table to the document
		document.add(table7);

		document.add(new Phrase("\n"));

		PdfPTable tableleftFM7 = new PdfPTable(1);
		tableleftFM7.setWidthPercentage(100);
		tableleftFM7.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell11 = createNumberedCell("7.    ", "WT Results.", fontTableHeading);
		establishmentCell11.setPaddingTop(10f);
		tableleftFM7.addCell(establishmentCell11);

		PdfPCell establishmentCell12 = createNumberedCell("", "", fontTableHeading);
		tableleftFM7.addCell(establishmentCell12);
		document.add(tableleftFM7);

		document.add(new Phrase("\n"));

		PdfPTable table8 = new PdfPTable(10);
		table8.setWidthPercentage(100); // Set table width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths8 = new float[] { 1.5f, 3f, 3f, 2f, 2f, 2f, 2f, 2f, 2f, 2f };
		table8.setWidths(columnWidths8);

		// Create the header row
		table8.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Weapons", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Category of Firers", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Total Number", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Marksmen", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("First Class", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Standard Shot", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Failed", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Numbers Exempted", fontTableHeading, 1, 1));
		table8.addCell(createHeaderCell("Numbers yet to Fire", fontTableHeading, 1, 1));

		// Create the second row with labels (a), (b), (c), (d), (e), (f), (g), (h),
		// (i), (j)
		table8.addCell(createCenteredCell("(a)", fontTableHeading));
		table8.addCell(createCenteredCell("(b)", fontTableHeading));
		table8.addCell(createCenteredCell("(c)", fontTableHeading));
		table8.addCell(createCenteredCell("(d)", fontTableHeading));
		table8.addCell(createCenteredCell("(e)", fontTableHeading));
		table8.addCell(createCenteredCell("(f)", fontTableHeading));
		table8.addCell(createCenteredCell("(g)", fontTableHeading));
		table8.addCell(createCenteredCell("(h)", fontTableHeading));
		table8.addCell(createCenteredCell("(i)", fontTableHeading));
		table8.addCell(createCenteredCell("(j)", fontTableHeading));

		// Define the specific values for the Weapons column
		String[] weaponValues = { "Permanent Staff", "Recruits", "Trained Soldiers" };

		// Loop through the weapon values
		
		
		
		if(getwtResultOtherData.isEmpty())
		{
			for (int i = 0; i < weaponValues.length; i++) {
				table8.addCell(createCenteredCell(" ", fontTableValue)); // Serial Number
				table8.addCell(createCenteredCell(weaponValues[i], fontTableValue)); // Weapons
				table8.addCell(createCenteredCell("", fontTableValue)); // Category of Firers (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Total Number (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Marksmen (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // First Class (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Standard Shot (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Failed (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Numbers Exempted (empty)
				table8.addCell(createCenteredCell("", fontTableValue)); // Numbers yet to Fire (empty)
			}
		}

			if (getwtResultOtherData.size() > 0) {
				for (int i = 0; i < getoutsideAttachmentData.size(); i++) {
					table8.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getWeapons(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getCategory(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getTotal_no(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getMarks_men(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getFirst_class(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getStandard_shot(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getFailed(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getNumber_exempted(), fontTableHeadingNonBoldData));
					table8.addCell(createCenteredCell(getwtResultOtherData.get(i).getNumber_yeto_fire(), fontTableHeadingNonBoldData));

				}

			}
			table8.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table8);

//		document.newPage();

//		document.add(new Phrase("\n"));

		PdfPTable tableleftFM8 = new PdfPTable(1);
		tableleftFM8.setWidthPercentage(100);
		tableleftFM8.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell13 = createNumberedCell("8.    ", "Education Standards.", fontTableHeading);
		establishmentCell13.setPaddingTop(10f);
		tableleftFM8.addCell(establishmentCell13);

		PdfPCell establishmentCell14 = createNumberedCell("", "", fontTableHeading);
		tableleftFM8.addCell(establishmentCell14);
		document.add(tableleftFM8);

		document.add(new Phrase("\n"));

		PdfPTable table9 = new PdfPTable(8);
		table9.setWidthPercentage(100); // Set table9 width to 100%

		// Set the widths of the columns (optional)
		float[] columnWidths9 = new float[] { 1f, 2f, 1f, 1f, 1f, 1f, 1f, 1f };
		table9.setWidths(columnWidths9);

		// Create the header row
		table9.addCell(createHeaderCell("Serial Number", fontTableHeading, 2, 1));
		table9.addCell(createHeaderCell("Category", fontTableHeading, 2, 1));
		table9.addCell(createHeaderCell("Affected", fontTableHeading, 2, 1));
		table9.addCell(createHeaderCell("Qualified", fontTableHeading, 2, 1));
		table9.addCell(createHeaderCell("Not Qualified", fontTableHeading, 2, 1));
		table9.addCell(createHeaderCell("Distribution of Not Qualified due to", fontTableHeading, 1, 3));
		table9.addCell(createHeaderCell("Map Reading", fontTableHeading, 1, 1));
		table9.addCell(createHeaderCell("Education", fontTableHeading, 1, 1));
		table9.addCell(createHeaderCell("Promotion Cadre / JLPT (For JCOs only)", fontTableHeading, 1, 1));

		// Create the second row with labels (a), (b), (c), (d), (e), (f), (g), (h)
		table9.addCell(createCenteredCell("(a)", fontTableHeading));
		table9.addCell(createCenteredCell("(b)", fontTableHeading));
		table9.addCell(createCenteredCell("(c)", fontTableHeading));
		table9.addCell(createCenteredCell("(d)", fontTableHeading));
		table9.addCell(createCenteredCell("(e)", fontTableHeading));
		table9.addCell(createCenteredCell("(f)", fontTableHeading));
		table9.addCell(createCenteredCell("(g)", fontTableHeading));
		table9.addCell(createCenteredCell("(h)", fontTableHeading));

		
		if(EducationStandardsData.size()<0)
		{

			for (char letter = 'a'; letter <= 'h'; letter++) {
				table9.addCell(createCenteredCell(" ", fontTableHeading));
			}

		}
		
		if (EducationStandardsData.size() > 0) {
			for (int i = 0; i < 1; i++) {
				table9.addCell(createCenteredCell("", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("JCO(JLTP)", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_affected(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_not_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_map(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_education(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getJco_promotion(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("NCO", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_affected(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_not_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_map(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_education(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getNco_promotion(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("OR", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_affected(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_not_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_map(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_education(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getOr_promotion(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("", fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell("Total", fontTableHeading));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_affected(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_not_qualified(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_map(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_education(), fontTableHeadingNonBoldData));
				table9.addCell(createCenteredCell(EducationStandardsData.get(i).getTotal_promotion(), fontTableHeadingNonBoldData));
				
			}

		}
		table9.setTableEvent(new ImageBackgroundEvent(request,username));
		
		document.add(table9);

//		document.add(new Phrase("\n"));
		PdfPTable tableleftFM9 = new PdfPTable(1);
		tableleftFM9.setWidthPercentage(100);
		tableleftFM9.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell15 = createNumberedCell("9.    ",
				"Availability of Personnel Trained on Courses at Category 'A' and Category 'B' Establishments.",
				fontTableHeading);
		establishmentCell15.setPaddingTop(10f);
		establishmentCell15.setPaddingBottom(10f);
		tableleftFM9.addCell(establishmentCell15);

		PdfPCell establishmentCell16 = createNumberedCell("", "", fontTableHeading);
		tableleftFM9.addCell(establishmentCell16);
		document.add(tableleftFM9);
//		document.add(new Phrase("\n"));

		PdfPTable table10 = new PdfPTable(13);
		table10.setWidthPercentage(100);

		float[] columnWidths10 = new float[] { 2f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		table10.setWidths(columnWidths10);

		table10.addCell(createHeaderCell("Name of Course", fontTableHeading, 3, 1));
		table10.addCell(createHeaderCell("Number of Personnel trained during", fontTableHeading, 1, 8));
		table10.addCell(createHeaderCell("Total Available", fontTableHeading, 1, 4));

		table10.addCell(createHeaderCell("Period of Report", fontTableHeading, 1, 4));
		table10.addCell(createHeaderCell("Preceding Two Years", fontTableHeading, 1, 4));

		table10.addCell(createHeaderCell("Officers", fontTableHeading, 2, 1));
		table10.addCell(createHeaderCell("JCOs", fontTableHeading, 2, 1));
		table10.addCell(createHeaderCell("NCOs", fontTableHeading, 2, 1));
		table10.addCell(createHeaderCell("OR", fontTableHeading, 2, 1));
		table10.addCell(createHeaderCell("Officers", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("JCOs", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("NCOs", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("OR", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("Officers", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("JCOs", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("NCOs", fontTableHeading, 1, 1));
		table10.addCell(createHeaderCell("OR", fontTableHeading, 1, 1));

		table10.addCell(createCenteredCell("(a)", fontTableHeading));
		table10.addCell(createCenteredCell("(b)", fontTableHeading));
		table10.addCell(createCenteredCell("(c)", fontTableHeading));
		table10.addCell(createCenteredCell("(d)", fontTableHeading));
		table10.addCell(createCenteredCell("(e)", fontTableHeading));
		table10.addCell(createCenteredCell("(f)", fontTableHeading));
		table10.addCell(createCenteredCell("(g)", fontTableHeading));
		table10.addCell(createCenteredCell("(h)", fontTableHeading));
		table10.addCell(createCenteredCell("(i)", fontTableHeading));
		table10.addCell(createCenteredCell("(j)", fontTableHeading));
		table10.addCell(createCenteredCell("(k)", fontTableHeading));
		table10.addCell(createCenteredCell("(l)", fontTableHeading));
		table10.addCell(createCenteredCell("(m)", fontTableHeading));
		
		if(getCourseCatAandBAction.size()<0)
		{
			for (char letter = 'a'; letter <= 'm'; letter++) {
				table10.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
			}
		}
		if(getCourseCatAandBAction.size()>0)
		{
			
			for(int i=0;i<getCourseCatAandBAction.size();i++)
			{
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getCourse_name(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getOfficers_period(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getJcos_period(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getNcos_period(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getOr_period(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getOfficers_preceding(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getJcos_preceding(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getNcos_preceding(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getOr_preceding(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getTotalofficers(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getTotaljcos(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getTotalncos(), fontTableHeadingNonBoldData));
				table10.addCell(createCenteredCell(getCourseCatAandBAction.get(i).getTotalor(), fontTableHeadingNonBoldData));
				
			
			
			}
		}

		
		table10.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table10);

		// Add a new line
		
		PdfPTable tableleftFM10 = new PdfPTable(1);
		tableleftFM10.setWidthPercentage(100);
		tableleftFM10.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell17 = createNumberedCell("10.    ",
				"Up-Gradation Carried Out During the Period of the Report.", fontTableHeading);
		establishmentCell17.setPaddingTop(20f);
		establishmentCell17.setPaddingBottom(20f);
		tableleftFM10.addCell(establishmentCell17);
		document.add(tableleftFM10);
//		document.add(new Phrase("\n"));

		// Create the second table
		PdfPTable table11 = new PdfPTable(13);
		table11.setWidthPercentage(100);
		float[] columnWidths11 = new float[] { 2f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		table11.setWidths(columnWidths11);

		// Add cells to the second table
		table11.addCell(createHeaderCell("Trade", fontTableHeading, 3, 1));
		table11.addCell(createHeaderCell("Total Affected for Up-Gradation", fontTableHeading, 1, 4));
		table11.addCell(createHeaderCell("Upgraded During the Period of Report", fontTableHeading, 1, 4));
		table11.addCell(createHeaderCell("Total Available at the End of the Period of Report", fontTableHeading, 1, 4));

		// Add additional rows to the second table
		table11.addCell(createHeaderCell("Class", fontTableHeading, 1, 4));
		table11.addCell(createHeaderCell("Class", fontTableHeading, 1, 4));
		table11.addCell(createHeaderCell("Class", fontTableHeading, 1, 4));

		// Add data to the table
		table11.addCell(createHeaderCell("4", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("3", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("2", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("1", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("4", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("3", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("2", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("1", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("4", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("3", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("2", fontTableHeading, 1, 1));
		table11.addCell(createHeaderCell("1", fontTableHeading, 1, 1));

		
		
		if(upGradationDataResult.size()<0)
		{
			for(int i=0;i<upGradationDataResult.size();i++)
			{
				table11.addCell(createCenteredCell(" ", fontTableHeadingNonBoldData));
			}
			
			
		}
		
		if(upGradationDataResult.size()>0)
		{
			for(int i=0;i<upGradationDataResult.size();i++)
			{
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getTrade(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getAffected_up_gradation_class_iv(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getAffected_up_gradation_class_iii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getAffected_up_gradation_class_ii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getAffected_up_gradation_class_i(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getDuring_up_gradation_class_iv(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getDuring_up_gradation_class_iii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getDuring_up_gradation_class_ii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getDuring_up_gradation_class_i(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getTotal_available_class_iv(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getTotal_available_class_iii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getTotal_available_class_ii(), fontTableHeadingNonBoldData));
					table11.addCell(createCenteredCell(upGradationDataResult.get(i).getTotal_available_class_i(), fontTableHeadingNonBoldData));
					
			}
			
			
		}
		
		
		
		table11.setTableEvent(new ImageBackgroundEvent(request,username));
		
		// Add the second table to the document
		document.add(table11);
		
		PdfPTable tableleftFM111 = new PdfPTable(1);
		tableleftFM111.setWidthPercentage(100);
		tableleftFM111.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell66 = createNumberedCell("11.    ",
				"Regimental Language Examinations (for Units where such an Examination is applicable)..", fontTableHeading);
		establishmentCell66.setPaddingTop(10f);
		establishmentCell66.setPaddingBottom(10f);
		tableleftFM111.addCell(establishmentCell66);
		document.add(tableleftFM111);
		
		PdfPTable table371 = new PdfPTable(1);
		table371.setWidthPercentage(100);
		float[] columnWidths371 = { 9f }; // Adjust widths as needed
		table371.setWidths(columnWidths371);

		// Header Row
		
		
		
		
		if(regLanguageExamDataResult.size()<0)
		{
			table371.addCell(textCell2(
					"(a)	Total number of officers qualified in the Lower Standard Examination in the language prescribed.",
					fontTableData));
			table371.addCell(textCell2(
					"(b)	Numbers exempted.",
					fontTableData));
			table371.addCell(textCell2(
					"(c)	Numbers qualified during period of report.",
					fontTableData));
			table371.addCell(textCell2(
					"(d)	Number of permanent commissioned officers not yet qualified.",
					fontTableData));
			
		}

		if (!regLanguageExamDataResult.isEmpty()) {
		    table371.addCell(textCell2(
		            "(a) Total number of officers qualified in the Lower Standard Examination in the language prescribed: " + regLanguageExamDataResult.get(0).getQualifiedDuringPeriod(),
		            fontTableHeading));
		    table371.addCell(textCell2(
		            "(b) Numbers exempted: " + regLanguageExamDataResult.get(0).getNumbersExempted(),
		            fontTableHeading));
		    table371.addCell(textCell2(
		            "(c) Numbers qualified during period of report: " + regLanguageExamDataResult.get(0).getOfficersQualified(),
		            fontTableHeading));
		    table371.addCell(textCell2(
		            "(d) Number of permanent commissioned officers not yet qualified: " + regLanguageExamDataResult.get(0).getNotYetQualified(),
		            fontTableHeading));
		}
		table371.setTableEvent(new ImageBackgroundEvent(request,username));
		
		document.add(table371);

//		------------------------------------------------page 6----------------------------------------
		//document.newPage();

		PdfPTable tableleftFM11 = new PdfPTable(1);
		tableleftFM11.setWidthPercentage(100);
		tableleftFM11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell19 = createNumberedCell("12.    ", "BPET Result.", fontTableHeading);
		establishmentCell19.setPaddingTop(10f);
		tableleftFM11.addCell(establishmentCell19);

		PdfPCell establishmentCell20 = createNumberedCell("", "", fontTableHeading);
		tableleftFM11.addCell(establishmentCell20);
		document.add(tableleftFM11);
		document.add(new Phrase("\n"));

		PdfPTable table12 = new PdfPTable(10);
		table12.setWidthPercentage(100);
		float[] columnWidths12 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		table12.setWidths(columnWidths12);

		table12.addCell(createHeaderCell("Serial Number", fontTableHeading, 2, 1));
		table12.addCell(createHeaderCell("Personnel", fontTableHeading, 2, 1));
		table12.addCell(createHeaderCell("Total Number", fontTableHeading, 2, 1));
		table12.addCell(createHeaderCell("Grading", fontTableHeading, 1, 5)); // Merged cell for Grading
		table12.addCell(createHeaderCell("Number Yet to be Tested", fontTableHeading, 2, 1));
		table12.addCell(createHeaderCell("Remarks / Reasons for Not Yet Tested", fontTableHeading, 2, 1));

		table12.addCell(createCenteredCell("Excellent", fontTableHeading));
		table12.addCell(createCenteredCell("Good", fontTableHeading));
		table12.addCell(createCenteredCell("Satisfactory", fontTableHeading));
		table12.addCell(createCenteredCell("Poor", fontTableHeading));
		table12.addCell(createCenteredCell("Fail", fontTableHeading));

		for (char letter = 'a'; letter <= 'j'; letter++) {
			table12.addCell(createCenteredCell(String.valueOf(letter), fontTableHeading));
		}

	
		if(bpetResultDataResult.isEmpty())
		{
			for (char letter = 'a'; letter <= 'j'; letter++) {
				table12.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if(!bpetResultDataResult.isEmpty())
		{
			for (int i=0;i<bpetResultDataResult.size();i++) {
				table12.addCell(createCenteredCell(String.valueOf(i+1),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getPersonnel(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getTotal_no(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getExcellent(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getGood(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getSatisfactory(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getPoor(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getFail(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getNumber_yet_to_tested(),fontTableHeadingNonBoldData ));
				table12.addCell(createCenteredCell(bpetResultDataResult.get(i).getRemarks(),fontTableHeadingNonBoldData ));

				
			}
			
		}
		
		
		
		table12.setTableEvent(new ImageBackgroundEvent(request,username));
		
		
		// Add the table12 to the document
		document.add(table12);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM12 = new PdfPTable(1);
		tableleftFM12.setWidthPercentage(100);
		tableleftFM12.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell21 = createNumberedCell("13.    ", "PPT Result.", fontTableHeading);
		establishmentCell21.setPaddingTop(10f);
		tableleftFM12.addCell(establishmentCell21);

		PdfPCell establishmentCell22 = createNumberedCell("", "", fontTableHeading);
		tableleftFM12.addCell(establishmentCell22);
		document.add(tableleftFM12);
		document.add(new Phrase("\n"));

		PdfPTable table13 = new PdfPTable(10); // Changed table name to table13
		table13.setWidthPercentage(100);
		float[] columnWidths13 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		table13.setWidths(columnWidths13);

		table13.addCell(createHeaderCell("Serial Number", fontTableHeading, 2, 1));
		table13.addCell(createHeaderCell("Personnel", fontTableHeading, 2, 1));
		table13.addCell(createHeaderCell("Total Number", fontTableHeading, 2, 1));
		table13.addCell(createHeaderCell("Grading", fontTableHeading, 1, 5)); // Merged cell for Grading
		table13.addCell(createHeaderCell("Number Yet to be Tested", fontTableHeading, 2, 1));
		table13.addCell(createHeaderCell("Remarks / Reasons for Not Yet Tested", fontTableHeading, 2, 1));

		// Second row with sub-headers for Grading
		table13.addCell(createCenteredCell("Excellent", fontTableHeading));
		table13.addCell(createCenteredCell("Good", fontTableHeading));
		table13.addCell(createCenteredCell("Satisfactory", fontTableHeading));
		table13.addCell(createCenteredCell("Poor", fontTableHeading));
		table13.addCell(createCenteredCell("Fail", fontTableHeading));

		for (char letter = 'a'; letter <= 'j'; letter++) {
			table13.addCell(createCenteredCell(String.valueOf(letter), fontTableHeading));
		}
		
		
		if(pptResultDataResult.isEmpty())
		{
			for (char letter = 'a'; letter <= 'j'; letter++) {
				table13.addCell(createCenteredCell(String.valueOf(letter), fontTableHeading));
			}
		}
		if(!pptResultDataResult.isEmpty())
		{
			for (int i=0;i<pptResultDataResult.size();i++) {
				table13.addCell(createCenteredCell(String.valueOf(i+1),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getPersonnel(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getTotal_no(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getExcellent(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getGood(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getSatisfactory(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getPoor(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getFail(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getNumber_yet_to_tested(),fontTableHeadingNonBoldData ));
				table13.addCell(createCenteredCell(pptResultDataResult.get(i).getRemarks(),fontTableHeadingNonBoldData ));
				
			}
			
		}
		table13.setTableEvent(new ImageBackgroundEvent(request,username));

		document.add(table13);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM13 = new PdfPTable(1);
		tableleftFM13.setWidthPercentage(100);
		tableleftFM13.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell23 = createNumberedCell("14.    ", "Promotion Exam Officers.", fontTableHeading);
		establishmentCell23.setPaddingTop(10f);
		tableleftFM13.addCell(establishmentCell23);

		PdfPCell establishmentCell24 = createNumberedCell("", "", fontTableHeading);
		tableleftFM13.addCell(establishmentCell24);
		document.add(tableleftFM13);
		document.add(new Phrase("\n"));

		PdfPTable table14 = new PdfPTable(6); // Changed to 6 columns based on your HTML structure
		table14.setWidthPercentage(100);
		float[] columnWidths14 = new float[] { 1f, 1f, 1f, 1f, 1f, 3f }; // Adjusted widths
		table14.setWidths(columnWidths14);

		table14.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table14.addCell(createHeaderCell("Type of Exam", fontTableHeading, 1, 1));
		table14.addCell(createHeaderCell("Number Appeared", fontTableHeading, 1, 1));
		table14.addCell(createHeaderCell("Number Successful", fontTableHeading, 1, 1));
		table14.addCell(createHeaderCell("Number Yet to Appear", fontTableHeading, 1, 1));
		table14.addCell(createHeaderCell(
				"Remarks (to include action taken in respect of officers declared unsuccessful for those within two years of the maximum prescribed limit)",
				fontTableHeading, 1, 1));

		// Second row with labels (a) to (f)
		table14.addCell(createCenteredCell("(a)", fontTableHeading));
		table14.addCell(createCenteredCell("(b)", fontTableHeading));
		table14.addCell(createCenteredCell("(c)", fontTableHeading));
		table14.addCell(createCenteredCell("(d)", fontTableHeading));
		table14.addCell(createCenteredCell("(e)", fontTableHeading));
		table14.addCell(createCenteredCell("(f)", fontTableHeading));

		// Add the table to the document

		if (getPromoExamData.size() < 0) {
			 for (char letter = 'a'; letter <= 'f'; letter++) {
					table14.addCell(createCenteredCell(" ", fontTableHeading));
				}
			}
			if (getPromoExamData.size() > 0) {
				for (int i = 0; i < getPromoExamData.size(); i++) {
					table14.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
					table14.addCell(createCenteredCell(getPromoExamData.get(i).getType_of_exam(),
							fontTableHeadingNonBoldData));
					table14.addCell(createCenteredCell(getPromoExamData.get(i).getNumber_appear(),
							fontTableHeadingNonBoldData));
					table14.addCell(createCenteredCell(getPromoExamData.get(i).getNumber_successful(), fontTableHeadingNonBoldData));
					table14.addCell(
							createCenteredCell(getPromoExamData.get(i).getNumber_yet_to_appear(), fontTableHeadingNonBoldData));
					table14.addCell(
							createCenteredCell(getPromoExamData.get(i).getRemarks(), fontTableHeadingNonBoldData));
					
				}

			}
			

		
		
		
		
		
			table14.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table14);
//----------------------------------------------new page 7--------------------------------------
//		document.newPage();

		PdfPTable tableleftFM14 = new PdfPTable(1);
		tableleftFM14.setWidthPercentage(100);
		tableleftFM14.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell25 = createNumberedCell("15.    ", "Financial Grants.", fontTableHeading);
		establishmentCell25.setPaddingTop(10f);
		tableleftFM14.addCell(establishmentCell25);

		PdfPCell establishmentCell26 = createNumberedCell("", "", fontTableHeading);
		tableleftFM14.addCell(establishmentCell26);
		document.add(tableleftFM14);
		document.add(new Phrase("\n"));

		PdfPTable table15 = new PdfPTable(9); // 9 columns based on your HTML structure
		table15.setWidthPercentage(100);
		float[] columnWidths15 = new float[] { 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f }; // Equal widths for all columns
		table15.setWidths(columnWidths15);

		table15.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Type of Fund / Grants", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Amount Authorised", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Amount Allotted with Date", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Reasons for Over / Under Allotment", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Amount Expended", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Amount Unutilised", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Reasons for Non Utilisation", fontTableHeading, 1, 1));
		table15.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		table15.addCell(createCenteredCell("(a)", fontTableHeading));
		table15.addCell(createCenteredCell("(b)", fontTableHeading));
		table15.addCell(createCenteredCell("(c)", fontTableHeading));
		table15.addCell(createCenteredCell("(d)", fontTableHeading));
		table15.addCell(createCenteredCell("(e)", fontTableHeading));
		table15.addCell(createCenteredCell("(f)", fontTableHeading));
		table15.addCell(createCenteredCell("(g)", fontTableHeading));
		table15.addCell(createCenteredCell("(h)", fontTableHeading));
		table15.addCell(createCenteredCell("(i)", fontTableHeading));

		
		
		if(getFinancialGrantsData.isEmpty())
		{
			for (char letter = 'a'; letter <= 'i'; letter++) {
				table15.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if(!getFinancialGrantsData.isEmpty())
		{
			for(int i=0;i<getFinancialGrantsData.size();i++)
			{
				table15.addCell(createCenteredCell(String.valueOf(i+1),fontTableHeadingNonBoldData ));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getType_of_grant(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getAmt_auth(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getAmt_alloted(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getReason_for_over_under(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getAmt_expended(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getAmt_utilised(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getReason_for_non_util(), fontTableHeading));
		table15.addCell(createCenteredCell(getFinancialGrantsData.get(i).getRemarks(), fontTableHeading));
		
			
			}
		}
		
		table15.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table15);
		Font fontTableValue_size = FontFactory.getFont(FontFactory.TIMES, 12);
		document.add(new Phrase("\n"));
		PdfPTable tableleftFM15 = new PdfPTable(1);
		tableleftFM15.setWidthPercentage(100);
		tableleftFM15.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell establishmentCell27 = createNumberedCell("16.    ", "Regt Funds incl Offrs Mess.", fontTableHeading);
		tableleftFM15.addCell(establishmentCell27);

		PdfPCell establishmentCell28 = createNumberedCell("", "", fontTableHeading);
		tableleftFM15.addCell(establishmentCell28);

		// Adding new lines to the table
		PdfPCell stateOfFundsCell = createNumberedCell("(a)  State of the funds: -", "", fontTableValue_size);
		stateOfFundsCell.setPaddingTop(10f);
		stateOfFundsCell.setPaddingLeft(20f);
		tableleftFM15.addCell(stateOfFundsCell);

		PdfPCell lastInspectionsCell = createNumberedCell("(i)  At the time of last two inspections.", "",
				fontTableValue_size);
		lastInspectionsCell.setPaddingLeft(30f);
		tableleftFM15.addCell(lastInspectionsCell);

		PdfPCell presentPositionCell = createNumberedCell(
				"(ii)  Present position, indicating increase/decrease in income and expenditure.", "",
				fontTableValue_size);
		presentPositionCell.setPaddingLeft(30f);
		tableleftFM15.addCell(presentPositionCell);

		PdfPCell reasonsCell = createNumberedCell(
				"(b)  Brief reasons for any large variations in income and expenditure, particularly if there is a decline in funds.",
				"", fontTableValue_size);
		reasonsCell.setPaddingLeft(20f);
		tableleftFM15.addCell(reasonsCell);

		// Add the table to the document
		document.add(tableleftFM15);
		document.add(new Phrase("\n"));

		PdfPTable table16 = new PdfPTable(9);
		table16.setWidthPercentage(100);
		float[] columnWidths16 = new float[] { 1f, 2f, 1f, 1f, 1f, 1f, 1f, 1f, 1f }; // Adjust widths as needed
		table16.setWidths(columnWidths16);

		table16.addCell(createHeaderCell("Serial Number", fontTableHeading, 3, 1));
		table16.addCell(createHeaderCell("Name of Acct", fontTableHeading, 3, 1));
		table16.addCell(createHeaderCell("State of Accts", fontTableHeading, 1, 6));
		table16.addCell(createHeaderCell("Incr/Decr", fontTableHeading, 3, 1));
		table16.addCell(createHeaderCell("Assets", fontTableHeading, 1, 1));
		table16.addCell(createHeaderCell("Liability", fontTableHeading, 1, 1));
		table16.addCell(createHeaderCell("Assets", fontTableHeading, 1, 1));
		table16.addCell(createHeaderCell("Liability", fontTableHeading, 1, 1));
		table16.addCell(createHeaderCell("Assets", fontTableHeading, 1, 1));
		table16.addCell(createHeaderCell("Liability", fontTableHeading, 1, 1));

		// Sub-headers for years
		 int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		 table16.addCell(createHeaderCell((currentYear-3)+"-"+(currentYear-2), fontTableHeading, 1, 2));
	     table16.addCell(createHeaderCell((currentYear-2)+"-"+(currentYear-1), fontTableHeading, 1, 2));
	     table16.addCell(createHeaderCell(currentYear-1+"-"+currentYear, fontTableHeading, 1, 2));
		
	        
		for (char letter = 'a'; letter <= 'i'; letter++) {
			table16.addCell(createCenteredCell(String.valueOf(letter), fontTableHeading));
		}
		
		if(getRegtFundsData.isEmpty())
		{
		for (char letter = 'a'; letter <= 'i'; letter++) {
			table16.addCell(createCenteredCell(" ", fontTableHeading));
		}
		}
		if(!getRegtFundsData.isEmpty())
			{
			for(int i=0;i<getRegtFundsData.size();i++)
			{
				table16.addCell(createCenteredCell(String.valueOf(i+1), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getName_of_acct(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getAsset_i(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getLiability_i(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getAsset_ii(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getLiability_ii(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getAsset_iii(), fontTableHeadingNonBoldData));

				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getLiability_iii(), fontTableHeadingNonBoldData));
				table16.addCell(createCenteredCell(getRegtFundsData.get(i).getIncr_decr_acct(), fontTableHeadingNonBoldData));
				
				
			
			}
			}
		
		
		table16.setTableEvent(new ImageBackgroundEvent(request,username));
			document.add(table16);
			

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM16 = new PdfPTable(1);
		tableleftFM16.setWidthPercentage(100);
		tableleftFM16.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell29 = createNumberedCell("17.    ", "Training Ammunition.", fontTableHeading);
		establishmentCell29.setPaddingTop(10f);
		tableleftFM16.addCell(establishmentCell29);

		PdfPCell establishmentCell30 = createNumberedCell("", "", fontTableHeading);
		tableleftFM16.addCell(establishmentCell30);
		document.add(tableleftFM16);
		document.add(new Phrase("\n"));

		PdfPTable table17 = new PdfPTable(8);
		table17.setWidthPercentage(100);
		float[] columnWidths17 = new float[] { 1f, 2f, 1f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table17.setWidths(columnWidths17);

		table17.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Type of Ammunition", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("A/U", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Quantity Authorised for Full Training Year", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Received Including Carried Forward", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Expended", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Balance", fontTableHeading, 1, 1));
		table17.addCell(createHeaderCell("Reasons for Non-Expenditure", fontTableHeading, 1, 1));

		// Second row with labels (a) to (h)
		table17.addCell(createCenteredCell("(a)", fontTableHeading));
		table17.addCell(createCenteredCell("(b)", fontTableHeading));
		table17.addCell(createCenteredCell("(c)", fontTableHeading));
		table17.addCell(createCenteredCell("(d)", fontTableHeading));
		table17.addCell(createCenteredCell("(e)", fontTableHeading));
		table17.addCell(createCenteredCell("(f)", fontTableHeading));
		table17.addCell(createCenteredCell("(g)", fontTableHeading));
		table17.addCell(createCenteredCell("(h)", fontTableHeading));

		
		if(gettrainningAmmunationData.isEmpty())
		{
			for (char letter = 'a'; letter <= 'h'; letter++) {
		
			table17.addCell(createCenteredCell(" ", fontTableHeading));
			}
			}
		
		if(!gettrainningAmmunationData.isEmpty())
		{
			for(int i=0;i<gettrainningAmmunationData.size();i++)
			{
			table17.addCell(createCenteredCell(String.valueOf(i+1), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getType_of_ammunition(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getAu(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getQty_auth_full_trainning(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getQty_auth_full_trainning(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getRecive_inclu_carried_forward(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getExpended(), fontTableHeadingNonBoldData));
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getBalance(), fontTableHeadingNonBoldData));
			
			table17.addCell(createCenteredCell(gettrainningAmmunationData.get(i).getReason(), fontTableHeadingNonBoldData));
			
			}
			}
		
		table17.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table17);

//         -------------------------------------page 9--------------------------------

		PdfPTable tableleftFM17 = new PdfPTable(1);
		tableleftFM17.setWidthPercentage(100);
		tableleftFM17.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell31 = createNumberedCell("18.    ", "Availability of Ranges.", fontTableHeading);
		establishmentCell31.setPaddingTop(10f);
		tableleftFM17.addCell(establishmentCell31);

		PdfPCell establishmentCell32 = createNumberedCell("", "", fontTableHeading);
		tableleftFM17.addCell(establishmentCell32);
		document.add(tableleftFM17);

		Paragraph additionalTextParagraph3 = new Paragraph("(a) Classification Ranges.", fontTableHeading);
		additionalTextParagraph3.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph3.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph3.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph3);

		PdfPTable table18 = new PdfPTable(4);
		table18.setWidthPercentage(100);
		float[] columnWidths18 = new float[] { 1f, 2f, 2f, 2f }; // Adjust widths as needed
		table18.setWidths(columnWidths18);

		table18.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table18.addCell(createHeaderCell("Number of Ranges Available", fontTableHeading, 1, 1));
		table18.addCell(createHeaderCell("When Used", fontTableHeading, 1, 1));
		table18.addCell(createHeaderCell("Difficulties Experienced, if any", fontTableHeading, 1, 1));

		// Second row with labels (a) to (d)
		table18.addCell(createCenteredCell("(a)", fontTableHeading));
		table18.addCell(createCenteredCell("(b)", fontTableHeading));
		table18.addCell(createCenteredCell("(c)", fontTableHeading));
		table18.addCell(createCenteredCell("(d)", fontTableHeading));

		if(getClassSaveData.isEmpty())
		{

			for (char letter = 'a'; letter <= 'd'; letter++) {
				table18.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if(!getClassSaveData.isEmpty())
		{
			for(int i=0;i<getClassSaveData.size();i++)
			{
				table18.addCell(createCenteredCell(String.valueOf(i+1), fontTableHeadingNonBoldData));
				table18.addCell(createCenteredCell(getClassSaveData.get(i).getNumber_of_ranges(), fontTableHeadingNonBoldData));

				table18.addCell(createCenteredCell(getClassSaveData.get(i).getWhen_used() != null ? getClassSaveData.get(i).getWhen_used().toString() : "", fontTableHeadingNonBoldData));
				table18.addCell(createCenteredCell(getClassSaveData.get(i).getDifficulties_experienced(), fontTableHeadingNonBoldData));
				
			
				
				
			}
		}
		
		
		
		
		
		
		table18.setTableEvent(new ImageBackgroundEvent(request,username));
		// Add the table18 to the document
		document.add(table18);

		Paragraph additionalTextParagraph4 = new Paragraph("(b) FFRs.", fontTableHeading);
		additionalTextParagraph4.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph4.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph4.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph4);

		PdfPTable table19 = new PdfPTable(5); // Table name is table19
		table19.setWidthPercentage(100);
		float[] columnWidths19 = new float[] { 1f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table19.setWidths(columnWidths19);

		table19.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table19.addCell(createHeaderCell("Ranges Available", fontTableHeading, 1, 1));
		table19.addCell(createHeaderCell("Level at which Range Utilised", fontTableHeading, 1, 1));
		table19.addCell(createHeaderCell("When Used", fontTableHeading, 1, 1));
		table19.addCell(createHeaderCell("Difficulties Experienced, if any", fontTableHeading, 1, 1));

		// Second row with labels (a) to (e)
		table19.addCell(createCenteredCell("(a)", fontTableHeading));
		table19.addCell(createCenteredCell("(b)", fontTableHeading));
		table19.addCell(createCenteredCell("(c)", fontTableHeading));
		table19.addCell(createCenteredCell("(d)", fontTableHeading));
		table19.addCell(createCenteredCell("(e)", fontTableHeading));

		// Add the table to the document


		if(getFfrsSaveData.isEmpty())
		{

			for (char letter = 'a'; letter <= 'e'; letter++) {
				table19.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if (!getFfrsSaveData.isEmpty()) {
			for (int i = 0; i < getFfrsSaveData.size(); i++) {
				table19.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeadingNonBoldData));
				table19.addCell(createCenteredCell(getFfrsSaveData.get(i).getRange_available(), fontTableHeadingNonBoldData));
				table19.addCell(createCenteredCell(getFfrsSaveData.get(i).getRange_utilized() != null ? getFfrsSaveData.get(i).getRange_utilized().toString() : "", fontTableHeadingNonBoldData));
				table19.addCell(createCenteredCell(getFfrsSaveData.get(i).getWhenusedffrs() != null ? getFfrsSaveData.get(i).getWhenusedffrs().toString() : "", fontTableHeadingNonBoldData));
				table19.addCell(createCenteredCell(getFfrsSaveData.get(i).getDifficulties_experienced(),fontTableHeadingNonBoldData));

			}
		}
		table19.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table19);

//		----------------------------page10-------------------------------
		

		PdfPTable tableleftFM18 = new PdfPTable(1);
		tableleftFM18.setWidthPercentage(100);
		tableleftFM18.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell33 = createNumberedCell("19.    ", "Outstanding Audit Objections/ Observations.",
				fontTableHeading);
		establishmentCell33.setPaddingTop(10f);
		tableleftFM18.addCell(establishmentCell33);

		PdfPCell establishmentCell34 = createNumberedCell("", "", fontTableHeading);
		tableleftFM18.addCell(establishmentCell34);
		document.add(tableleftFM18);
		document.add(new Phrase("\n"));

		PdfPTable table20 = new PdfPTable(5); // 5 columns
		table20.setWidthPercentage(100);

		float[] columnWidths20 = new float[] { 1.5f, 5f, 2f, 2f, 2f }; // Adjust widths as needed
		table20.setWidths(columnWidths20);

		// Header Row
		table20.addCell(createHeaderCell("Serial Number", fontTableHeading, 2, 1));
		table20.addCell(createHeaderCell("Period", fontTableHeading, 2, 1));
		table20.addCell(createHeaderCell("Numbers Outstanding", fontTableHeading, 1, 2));
		table20.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 2, 1));
		// Second Header Row

		table20.addCell(createCenteredCell("Objections", fontTableHeading));
		table20.addCell(createCenteredCell("Observations", fontTableHeading));

		table20.addCell(createCenteredCell("(a)", fontTableHeading));
		table20.addCell(createCenteredCell("(b)", fontTableHeading));
		table20.addCell(createCenteredCell("(c)", fontTableHeading));
		table20.addCell(createCenteredCell("(d)", fontTableHeading));
		table20.addCell(createCenteredCell("(e)", fontTableHeading));

		// Data Rows
		table20.addCell(createCenteredCell("1", fontTableHeading));
		table20.addCell(textCell("Brought forward from previous year", fontTableData));
		if(outstandingAuditObjectionsObservationsResult.isEmpty())
		{
		table20.addCell(createHeaderCell("", fontTableHeading, 5, 1));
		table20.addCell(createHeaderCell("", fontTableHeading, 5, 1));
		table20.addCell(createHeaderCell("", fontTableHeading, 5, 1));
		}
		if(!outstandingAuditObjectionsObservationsResult.isEmpty())
		{
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getBroughtPreviousObj(), fontTableHeadingNonBoldData));		
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getBroughtPreviousObserv(), fontTableHeadingNonBoldData));
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getBroughtPreviousRemark(), fontTableHeadingNonBoldData));
		}
		
		table20.addCell(createCenteredCell("2", fontTableHeading));
		table20.addCell(textCell("Raised during year of report", fontTableData));
		
		if(!outstandingAuditObjectionsObservationsResult.isEmpty())
		{
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getRaisedReportObj(), fontTableHeadingNonBoldData));
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getRaisedReportObserv(), fontTableHeadingNonBoldData));
		table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getRaisedReportRemark(), fontTableHeadingNonBoldData));
		
		}
		

		table20.addCell(createCenteredCell("3", fontTableHeading));
		table20.addCell(textCell("Settled during the year of report", fontTableData));
		
		
		if(!outstandingAuditObjectionsObservationsResult.isEmpty())
		{
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringObj(), fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringObserv(), fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringRemark(), fontTableHeadingNonBoldData));
			
		
		}

		table20.addCell(createCenteredCell("4", fontTableHeading));
		table20.addCell(textCell("Remaining un-settled at the end of year under report", fontTableData));

		if(!outstandingAuditObjectionsObservationsResult.isEmpty())
		{
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringObj(), fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringObserv(), fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getSettledDuringRemark(), fontTableHeadingNonBoldData));
			
		
		}
		
		
		table20.addCell(createCenteredCell("5", fontTableHeading));
		table20.addCell(textCell("Details of Objections / Observations:\n" + "(a) Three years and above old\n"
				+ "(b) One year and above old (attach details for both as appropriate, including reasons for delay and action taken so far)",
				fontTableData));

		if(!outstandingAuditObjectionsObservationsResult.isEmpty())
		{
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesObj3() +" \n \n"+outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesObj1()+"\n", fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesObserv3() +" \n \n "+outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesObserv1()+"\n", fontTableHeadingNonBoldData));
			table20.addCell(createCenteredCell(outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesRemark3() +" \n \n"+outstandingAuditObjectionsObservationsResult.get(0).getDifficultiesRemark1()+"\n", fontTableHeadingNonBoldData));
			
		
		}
		table20.setTableEvent(new ImageBackgroundEvent(request,username));
		
		document.add(table20);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM19 = new PdfPTable(1);
		tableleftFM19.setWidthPercentage(100);
		tableleftFM19.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell35 = createNumberedCell("20.    (a) ",
				"Details of Courses (Category 'A' and Category 'B' Establishments only).", fontTableHeading);
		establishmentCell35.setPaddingTop(10f);
		tableleftFM19.addCell(establishmentCell35);

		PdfPCell establishmentCell36 = createNumberedCell("", "", fontTableHeading);
		tableleftFM19.addCell(establishmentCell36);
		document.add(tableleftFM19);
		document.add(new Phrase("\n"));

		PdfPTable table21 = new PdfPTable(9); // 8 columns
		table21.setWidthPercentage(100);
		float[] columnWidths21 = new float[] { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f ,2f}; // Adjust widths as needed
		table21.setWidths(columnWidths21);

		// Header Row
		table21.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Name of the Course", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Number of Course", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Period From", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Period to", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Total Allotted", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Attended", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("RTU", fontTableHeading, 1, 1));
		table21.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table21.addCell(createCenteredCell("(a)", fontTableHeading));
		table21.addCell(createCenteredCell("(b)", fontTableHeading));
		table21.addCell(createCenteredCell("(c)", fontTableHeading));
		table21.addCell(createCenteredCell("(d)", fontTableHeading));
		table21.addCell(createCenteredCell("(e)", fontTableHeading));
		table21.addCell(createCenteredCell("(f)", fontTableHeading));
		table21.addCell(createCenteredCell("(g)", fontTableHeading));
		table21.addCell(createCenteredCell("(h)", fontTableHeading));
		table21.addCell(createCenteredCell("(i)", fontTableHeading));

		if(coursesDataResult.isEmpty())
		{
			for (char letter = 'a'; letter <= 'i'; letter++) {
				table21.addCell(createCenteredCell(" ", fontTableHeading));
		
		
			}
		}
		if(!coursesDataResult.isEmpty())
		{
			for (int i = 0; i < coursesDataResult.size(); i++) {
				table21.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getCourse_name(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getNumber_of_course(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getPeriod_from().toString(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getPeriod_to().toString(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getTotal_allotted(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getAttended(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getRtu(),fontTableHeadingNonBoldData  ));
					table21.addCell(createCenteredCell(coursesDataResult.get(i).getDetailed_remarks(),fontTableHeadingNonBoldData  ));
					
			}
			
		}
		
		
		
		
		
		table21.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table21);

		Paragraph additionalTextParagraph5 = new Paragraph("(b) Standards Achieved.", fontTableHeading);
		additionalTextParagraph5.setSpacingBefore(17f); // Optional: Add space before the text
		additionalTextParagraph5.setFirstLineIndent(10f); // Set first line indent
		additionalTextParagraph5.setSpacingAfter(17f); // This will pull the next element closer
		document.add(additionalTextParagraph5);

		PdfPTable table22 = new PdfPTable(8); // 6 columns
		table22.setWidthPercentage(100);
		float[] columnWidths22 = new float[] { 2f, 2f, 2f, 2f, 2f, 2f,2f, 2f }; // Adjust widths as needed
		table22.setWidths(columnWidths22);

		// Header Row
		table22.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("Name of the Course", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("Total", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("Grading", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("/D/A/B/C/E/", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("Failed", fontTableHeading, 1, 1));
		 
		table22.addCell(createHeaderCell("RTU", fontTableHeading, 1, 1));
		table22.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table22.addCell(createCenteredCell("(a)", fontTableHeading));
		table22.addCell(createCenteredCell("(b)", fontTableHeading));
		table22.addCell(createCenteredCell("(c)", fontTableHeading));
		table22.addCell(createCenteredCell("(d)", fontTableHeading));
		table22.addCell(createCenteredCell("(e)", fontTableHeading));
		table22.addCell(createCenteredCell("(f)", fontTableHeading));
		table22.addCell(createCenteredCell("(g)", fontTableHeading));
		table22.addCell(createCenteredCell("(h)", fontTableHeading));
		
if(detailsOfCoursesTbl2.isEmpty())

	{
	for (char letter = 'a'; letter <= 'h'; letter++) {
	
			table22.addCell(createCenteredCell(" ", fontTableHeading));
		}
	}
if(!detailsOfCoursesTbl2.isEmpty())

{
	for (int i = 0; i < detailsOfCoursesTbl2.size(); i++) {
		table22.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getCourse_name(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getTotal(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getGrading(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getDa(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getFailed(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getRtu(),fontTableHeadingNonBoldData  ));
		table22.addCell(createCenteredCell(detailsOfCoursesTbl2.get(i).getDetailed_remarks(),fontTableHeadingNonBoldData  ));
		
	}
}
	
		
table22.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table22);

//        --------------------------------------page11--------------------------------------

		PdfPTable tableleftFM20 = new PdfPTable(1);
		tableleftFM20.setWidthPercentage(100);
		tableleftFM20.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell37 = createNumberedCell("21.    ",
				"Details of Recruit Training (Category ‘B’ Establishments only).", fontTableHeading);
		establishmentCell37.setPaddingTop(10f);
		tableleftFM20.addCell(establishmentCell37);

		PdfPCell establishmentCell38 = createNumberedCell("        To be submitted as a separate Appendix", "",
				fontTableData);
		tableleftFM20.addCell(establishmentCell38);
		document.add(tableleftFM20);
		document.add(new Phrase("\n"));

		PdfPTable tableleftFM21 = new PdfPTable(1);
		tableleftFM21.setWidthPercentage(100);
		tableleftFM21.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell39 = createNumberedCell("22.    ", "Summary of Technical Inspection.",
				fontTableHeading);
		establishmentCell39.setPaddingTop(10f);
		tableleftFM21.addCell(establishmentCell39);
		document.add(tableleftFM21);
		document.add(new Phrase("\n"));

		PdfPTable table23 = new PdfPTable(6); // 6 columns
		table23.setWidthPercentage(100);
		float[] columnWidths23 = new float[] { 2f, 3f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table23.setWidths(columnWidths23);

		table23.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table23.addCell(createHeaderCell("Type of Technical Inspection", fontTableHeading, 1, 1));
		table23.addCell(createHeaderCell("Date Held", fontTableHeading, 1, 1));
		table23.addCell(createHeaderCell("By Whom", fontTableHeading, 1, 1));
		table23.addCell(createHeaderCell("Result", fontTableHeading, 1, 1));
		table23.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table23.addCell(createCenteredCell("(a)", fontTableHeading));
		table23.addCell(createCenteredCell("(b)", fontTableHeading));
		table23.addCell(createCenteredCell("(c)", fontTableHeading));
		table23.addCell(createCenteredCell("(d)", fontTableHeading));
		table23.addCell(createCenteredCell("(e)", fontTableHeading));
		table23.addCell(createCenteredCell("(f)", fontTableHeading));

		

		if (summarytechinspDataResult == null || summarytechinspDataResult.isEmpty()) {
		    for (char letter = 'a'; letter <= 'f'; letter++) {
		        table23.addCell(createCenteredCell(" ", fontTableHeading));
		    }
		} else {
	
		    for (int i = 0; i < summarytechinspDataResult.size(); i++) {
		        table23.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
		        table23.addCell(createCenteredCell(summarytechinspDataResult.get(i).getInspection_type(), fontTableHeadingNonBoldData));
		        table23.addCell(createCenteredCell(summarytechinspDataResult.get(i).getDate_held(), fontTableHeadingNonBoldData));
		        table23.addCell(createCenteredCell(summarytechinspDataResult.get(i).getBy_whom(), fontTableHeadingNonBoldData));
		        table23.addCell(createCenteredCell(summarytechinspDataResult.get(i).getResult(), fontTableHeadingNonBoldData));
		        table23.addCell(createCenteredCell(summarytechinspDataResult.get(i).getDetailed_remarks(), fontTableHeadingNonBoldData));
		    }
		    
		    if(summaryOfTechnicalInspection2.size()>0)
		    {
		    	
		    	for (int i = 0; i < summaryOfTechnicalInspection2.size(); i++) {
			        table23.addCell(createCenteredCell(String.valueOf(summarytechinspDataResult.size() + i+1), fontTableHeading));
			        table23.addCell(createCenteredCell(summaryOfTechnicalInspection2.get(i).getType_of_tech_insp(), fontTableHeadingNonBoldData));
			        table23.addCell(createCenteredCell(summaryOfTechnicalInspection2.get(i).getDate(), fontTableHeadingNonBoldData));
			        table23.addCell(createCenteredCell(summaryOfTechnicalInspection2.get(i).getBy_whome(), fontTableHeadingNonBoldData));
			        table23.addCell(createCenteredCell(summaryOfTechnicalInspection2.get(i).getResult(), fontTableHeadingNonBoldData));
			        table23.addCell(createCenteredCell(summaryOfTechnicalInspection2.get(i).getRemarks(), fontTableHeadingNonBoldData));
			    }
		    	
		    }
		    
		    
		
		    
		}
		table23.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table23);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM22 = new PdfPTable(1);
		tableleftFM22.setWidthPercentage(100);
		tableleftFM22.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell40 = createNumberedCell("23.    ", "Details of Outstanding Rent/Allied Charges.",
				fontTableHeading);
		establishmentCell40.setPaddingTop(10f);
		tableleftFM22.addCell(establishmentCell40);
		document.add(tableleftFM22);
		document.add(new Phrase("\n"));

		PdfPTable table24 = new PdfPTable(5); // 5 columns
		table24.setWidthPercentage(100);
		float[] columnWidths24 = new float[] { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table24.setWidths(columnWidths24);

		// Header Row
		table24.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table24.addCell(createHeaderCell("Year", fontTableHeading, 1, 1));
		table24.addCell(createHeaderCell("Amount Outstanding", fontTableHeading, 1, 1));
		table24.addCell(createHeaderCell("On What Account", fontTableHeading, 1, 1));
		table24.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table24.addCell(createCenteredCell("(a)", fontTableHeading));
		table24.addCell(createCenteredCell("(b)", fontTableHeading));
		table24.addCell(createCenteredCell("(c)", fontTableHeading));
		table24.addCell(createCenteredCell("(d)", fontTableHeading));
		table24.addCell(createCenteredCell("(e)", fontTableHeading));

		if(getoutstandingData.size()<0)
{
	for (char letter = 'a'; letter <= 'e'; letter++) {
		table24.addCell(createCenteredCell(" ", fontTableHeading));
	}
}
if(getoutstandingData.size()>0)
{
	for(int i=0;i<getoutstandingData.size();i++)
	{ 
		table24.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
		table24.addCell(createCenteredCell(getoutstandingData.get(i).getOutstanding_year(), fontTableHeadingNonBoldData));
		table24.addCell(createCenteredCell(getoutstandingData.get(i).getAmount_outstanding(), fontTableHeadingNonBoldData));
		table24.addCell(createCenteredCell(getoutstandingData.get(i).getOn_acc(), fontTableHeadingNonBoldData));
		table24.addCell(createCenteredCell(getoutstandingData.get(i).getRemarks(), fontTableHeadingNonBoldData));
		
	}
	
}
table24.setTableEvent(new ImageBackgroundEvent(request,username));

		document.add(table24);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM23 = new PdfPTable(1);
		tableleftFM23.setWidthPercentage(100);
		tableleftFM23.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell41 = createNumberedCell("24.    ",
				"Details of Outstanding Loss Statements Including MT Accidents.", fontTableHeading);
		establishmentCell41.setPaddingTop(10f);
		tableleftFM23.addCell(establishmentCell41);
		tableleftFM23.setSpacingAfter(30f);
		document.add(tableleftFM23);

		PdfPTable table25 = new PdfPTable(5); // 5 columns
		table25.setWidthPercentage(100);
		float[] columnWidths25 = new float[] { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table25.setWidths(columnWidths25);

		// Header Row
		table25.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table25.addCell(createHeaderCell("Year", fontTableHeading, 1, 1));
		table25.addCell(createHeaderCell("Nature of Loss", fontTableHeading, 1, 1));
		table25.addCell(createHeaderCell("Value", fontTableHeading, 1, 1));
		table25.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table25.addCell(createCenteredCell("(a)", fontTableHeading));
		table25.addCell(createCenteredCell("(b)", fontTableHeading));
		table25.addCell(createCenteredCell("(c)", fontTableHeading));
		table25.addCell(createCenteredCell("(d)", fontTableHeading));
		table25.addCell(createCenteredCell("(e)", fontTableHeading));

		if (getoutstandingLossMTData.size() < 0) {
			for (char letter = 'a'; letter <= 'e'; letter++) {
				table25.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if (getoutstandingLossMTData.size() > 0) {
			for (int i = 0; i < getoutstandingLossMTData.size(); i++) {
				table25.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table25.addCell(createCenteredCell(getoutstandingLossMTData.get(i).getOutstanding_year(),
						fontTableHeadingNonBoldData));
				table25.addCell(createCenteredCell(getoutstandingLossMTData.get(i).getNature_of_loss(),
						fontTableHeadingNonBoldData));
				table25.addCell(createCenteredCell(getoutstandingLossMTData.get(i).getValue(), fontTableHeadingNonBoldData));
				table25.addCell(
						createCenteredCell(getoutstandingLossMTData.get(i).getRemarks(), fontTableHeadingNonBoldData));

			}

		}
		table25.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table25);

		PdfPTable tableleftFM24 = new PdfPTable(1);
		tableleftFM24.setWidthPercentage(100);
		tableleftFM24.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell42 = createNumberedCell("25.    ",
				"Details of Suicides/ Fratricides/ Untoward Incidents of any other Nature.", fontTableHeading);
		establishmentCell42.setPaddingTop(10f);
		tableleftFM24.addCell(establishmentCell42);
		document.add(tableleftFM24);
		document.add(new Phrase("\n"));

		PdfPTable table26 = new PdfPTable(5); // 5 columns
		table26.setWidthPercentage(100);
		float[] columnWidths26 = new float[] { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table26.setWidths(columnWidths26);

		// Header Row
		table26.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table26.addCell(createHeaderCell("Date", fontTableHeading, 1, 1));
		table26.addCell(createHeaderCell("Nature of Incident", fontTableHeading, 1, 1));
		table26.addCell(createHeaderCell("Fatal / Non-Fatal Casualty", fontTableHeading, 1, 1));
		table26.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table26.addCell(createCenteredCell("(a)", fontTableHeading));
		table26.addCell(createCenteredCell("(b)", fontTableHeading));
		table26.addCell(createCenteredCell("(c)", fontTableHeading));
		table26.addCell(createCenteredCell("(d)", fontTableHeading));
		table26.addCell(createCenteredCell("(e)", fontTableHeading));

		if (getfatalIncidentData.size() < 0) {
			for (char letter = 'a'; letter <= 'e'; letter++) {
				table26.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if (getfatalIncidentData.size() > 0) {
			for (int i = 0; i < getfatalIncidentData.size(); i++) {
				table26.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table26.addCell(createCenteredCell(getfatalIncidentData.get(i).getDate(),
						fontTableHeadingNonBoldData));
				table26.addCell(createCenteredCell(getfatalIncidentData.get(i).getNature_of_insident(),
						fontTableHeadingNonBoldData));
				table26.addCell(createCenteredCell(getfatalIncidentData.get(i).getCasualty(), fontTableHeadingNonBoldData));
				table26.addCell(
						createCenteredCell(getfatalIncidentData.get(i).getRemarks(), fontTableHeadingNonBoldData));

			}

		}
		table26.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table26);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM25 = new PdfPTable(1);
		tableleftFM25.setWidthPercentage(100);
		tableleftFM25.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell43 = createNumberedCell("26.    ",
				"Security Lapses Observed in the Unit Functioning.", fontTableHeading);
		establishmentCell43.setPaddingTop(10f);
		tableleftFM25.addCell(establishmentCell43);
		document.add(tableleftFM25);
		document.add(new Phrase("\n"));

		PdfPTable table27 = new PdfPTable(5); // 5 columns
		table27.setWidthPercentage(100);
		float[] columnWidths27 = new float[] { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table27.setWidths(columnWidths27);

		// Header Row
		table27.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table27.addCell(createHeaderCell("Date", fontTableHeading, 1, 1));
		table27.addCell(createHeaderCell("Nature of Security Lapse", fontTableHeading, 1, 1));
		table27.addCell(createHeaderCell("Resulted in", fontTableHeading, 1, 1));
		table27.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table27.addCell(createCenteredCell("(a)", fontTableHeading));
		table27.addCell(createCenteredCell("(b)", fontTableHeading));
		table27.addCell(createCenteredCell("(c)", fontTableHeading));
		table27.addCell(createCenteredCell("(d)", fontTableHeading));
		table27.addCell(createCenteredCell("(e)", fontTableHeading));

		if (getsequrityLapsesData.size() < 0) {
			for (char letter = 'a'; letter <= 'e'; letter++) {
				table27.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if (getsequrityLapsesData.size() > 0) {
			for (int i = 0; i < getsequrityLapsesData.size(); i++) {
				table27.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table27.addCell(createCenteredCell(getsequrityLapsesData.get(i).getDate(),
						fontTableHeadingNonBoldData));
				table27.addCell(createCenteredCell(getsequrityLapsesData.get(i).getNature_of_security_lapse(),
						fontTableHeadingNonBoldData));
				table27.addCell(createCenteredCell(getsequrityLapsesData.get(i).getResulted_in(), fontTableHeadingNonBoldData));
				table27.addCell(
						createCenteredCell(getsequrityLapsesData.get(i).getRemarks(), fontTableHeadingNonBoldData));

			}

		}
		
		
		table27.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table27);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM26 = new PdfPTable(1);
		tableleftFM26.setWidthPercentage(100);
		tableleftFM26.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell44 = createNumberedCell("27.    ", "Details of Attachments Outside the Unit.",
				fontTableHeading);
		establishmentCell44.setPaddingTop(10f);
		tableleftFM26.addCell(establishmentCell44);
		document.add(tableleftFM26);
		document.add(new Phrase("\n"));

		PdfPTable table28 = new PdfPTable(5); // 5 columns
		table28.setWidthPercentage(100);
		float[] columnWidths28 = new float[] { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table28.setWidths(columnWidths28);

		// Header Row
		table28.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table28.addCell(createHeaderCell("Location", fontTableHeading, 1, 1));
		table28.addCell(createHeaderCell("Number of Personnel Attached", fontTableHeading, 1, 1));
		table28.addCell(createHeaderCell("Duration", fontTableHeading, 1, 1));
		table28.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table28.addCell(createCenteredCell("(a)", fontTableHeading));
		table28.addCell(createCenteredCell("(b)", fontTableHeading));
		table28.addCell(createCenteredCell("(c)", fontTableHeading));
		table28.addCell(createCenteredCell("(d)", fontTableHeading));
		table28.addCell(createCenteredCell("(e)", fontTableHeading));

		  if (getoutsideAttachmentData.size() < 0) {
				for (char letter = 'a'; letter <= 'e'; letter++) {
					table28.addCell(createCenteredCell(" ", fontTableHeading));
				}
			}
			if (getoutsideAttachmentData.size() > 0) {
				for (int i = 0; i < getoutsideAttachmentData.size(); i++) {
					table28.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
					table28.addCell(createCenteredCell(getoutsideAttachmentData.get(i).getLocation(),
							fontTableHeadingNonBoldData));
					table28.addCell(createCenteredCell(getoutsideAttachmentData.get(i).getNumber_of_personnel_attached(),
							fontTableHeadingNonBoldData));
					table28.addCell(createCenteredCell(getoutsideAttachmentData.get(i).getDuration(), fontTableHeadingNonBoldData));
					table28.addCell(
							createCenteredCell(getoutsideAttachmentData.get(i).getRemarks(), fontTableHeadingNonBoldData));

				}

			}
			table28.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table28);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM27 = new PdfPTable(1);
		tableleftFM27.setWidthPercentage(100);
		tableleftFM27.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell45 = createNumberedCell("28.    ",
				"Details of Officers Who Have Proceeded on TD in the last 12 months.", fontTableHeading);
		establishmentCell45.setPaddingTop(10f);
		tableleftFM27.addCell(establishmentCell45);
		document.add(tableleftFM27);
		document.add(new Phrase("\n"));

		PdfPTable table29 = new PdfPTable(5);
		table29.setWidthPercentage(100);
		float[] columnWidths29 = { 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table29.setWidths(columnWidths29);

		// Header Row
		table29.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table29.addCell(createHeaderCell("Rank & Name", fontTableHeading, 1, 1));
		table29.addCell(createHeaderCell("Nature of Duty", fontTableHeading, 1, 1));
		table29.addCell(createHeaderCell("Ordered By", fontTableHeading, 1, 1));
		table29.addCell(createHeaderCell("Detailed Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table29.addCell(createCenteredCell("(a)", fontTableHeading));
		table29.addCell(createCenteredCell("(b)", fontTableHeading));
		table29.addCell(createCenteredCell("(c)", fontTableHeading));
		table29.addCell(createCenteredCell("(d)", fontTableHeading));
		table29.addCell(createCenteredCell("(e)", fontTableHeading));

		if(tdOfficersDataResult.size()<0)
		{
			for (char letter = 'a'; letter <= 'e'; letter++) {
				table29.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		if(tdOfficersDataResult.size()>0)
		{
			for(int i=0;i<tdOfficersDataResult.size();i++)
			{ 
				table29.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table29.addCell(createCenteredCell(tdOfficersDataResult.get(i).getRank_and_name(), fontTableHeadingNonBoldData));
				table29.addCell(createCenteredCell(tdOfficersDataResult.get(i).getNature_duty(), fontTableHeadingNonBoldData));
				table29.addCell(createCenteredCell(tdOfficersDataResult.get(i).getOrdered_by(), fontTableHeadingNonBoldData));
				table29.addCell(createCenteredCell(tdOfficersDataResult.get(i).getDetailed_remarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table29.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table29);

//		------------------------------page12---------------------------------------------

		PdfPTable tableleftFM28 = new PdfPTable(1);
		tableleftFM28.setWidthPercentage(100);
		tableleftFM28.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell46 = createNumberedCell("29.    ",
				"Security Lapses (Social Media violation) Observed in the Unit.", fontTableHeading);
		establishmentCell46.setPaddingTop(10f);
		tableleftFM28.addCell(establishmentCell46);
		document.add(tableleftFM28);
		document.add(new Phrase("\n"));

		PdfPTable table30 = new PdfPTable(7);
		table30.setWidthPercentage(100);
		float[] columnWidths30 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table30.setWidths(columnWidths30);

		// Header Row
		table30.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Date of Violation Reported Initially Higher HQ", fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Date of Violation Reported Initially by Unit/Formation to Higher HQ, if any",
				fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Number, Rank and Name", fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Current Status of Progress of the Case", fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Loss of Information, if any", fontTableHeading, 1, 1));
		table30.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table30.addCell(createCenteredCell("(a)", fontTableHeading));
		table30.addCell(createCenteredCell("(b)", fontTableHeading));
		table30.addCell(createCenteredCell("(c)", fontTableHeading));
		table30.addCell(createCenteredCell("(d)", fontTableHeading));
		table30.addCell(createCenteredCell("(e)", fontTableHeading));
		table30.addCell(createCenteredCell("(f)", fontTableHeading));
		table30.addCell(createCenteredCell("(g)", fontTableHeading));

		if(smlDataResult.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table30.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(smlDataResult.size()>0)
		{
			for(int i=0;i<smlDataResult.size();i++)
			{ 
				table30.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				Date date1 = smlDataResult.get(i).getDate_violation_fmn();
				table30.addCell(createCenteredCell(outputFormat.format(date1), fontTableHeadingNonBoldData));
				
				Date date2 = smlDataResult.get(i).getDate_violation_initial();
				table30.addCell(createCenteredCell(outputFormat.format(date2), fontTableHeadingNonBoldData));
				
				table30.addCell(createCenteredCell(smlDataResult.get(i).getNumber_rank_name(), fontTableHeadingNonBoldData));
				table30.addCell(createCenteredCell(smlDataResult.get(i).getCurr_status(), fontTableHeadingNonBoldData));
				table30.addCell(createCenteredCell(smlDataResult.get(i).getLoss_info(), fontTableHeadingNonBoldData));
				table30.addCell(createCenteredCell(smlDataResult.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table30.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table30);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM29 = new PdfPTable(1);
		tableleftFM29.setWidthPercentage(100);
		tableleftFM29.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell47 = createNumberedCell("30.    ",
				"Security Lapses (PIO Calls/ comn with PIO on web messenger Apps) observed in the unit.",
				fontTableHeading);
		establishmentCell47.setPaddingTop(10f);
		tableleftFM29.addCell(establishmentCell47);
		document.add(tableleftFM29);
		document.add(new Phrase("\n"));

		PdfPTable table31 = new PdfPTable(7);
		table31.setWidthPercentage(100);
		float[] columnWidths31 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table31.setWidths(columnWidths31);

		// Header Row
		table31.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Date of Violation Reported from Higher HQ", fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Date of Violation Reported Initially by Unit/Formation to Higher HQ, if any",
				fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Number, Rank and Name", fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Current Status of Progress of Case", fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Loss of Information, if any", fontTableHeading, 1, 1));
		table31.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table31.addCell(createCenteredCell("(a)", fontTableHeading));
		table31.addCell(createCenteredCell("(b)", fontTableHeading));
		table31.addCell(createCenteredCell("(c)", fontTableHeading));
		table31.addCell(createCenteredCell("(d)", fontTableHeading));
		table31.addCell(createCenteredCell("(e)", fontTableHeading));
		table31.addCell(createCenteredCell("(f)", fontTableHeading));
		table31.addCell(createCenteredCell("(g)", fontTableHeading));

		if(pclDataResult.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table31.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}

		if(pclDataResult.size()>0)
		{
			for(int i=0;i<pclDataResult.size();i++)
			{ 
				table31.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				Date date1 = pclDataResult.get(i).getDate_violation_fmn();
				Date date2 = pclDataResult.get(i).getDate_violation_initial();
				table31.addCell(createCenteredCell(outputFormat.format(date2), fontTableHeadingNonBoldData));
				table31.addCell(createCenteredCell(outputFormat.format(date1), fontTableHeadingNonBoldData));
				table31.addCell(createCenteredCell(pclDataResult.get(i).getNumber_rank_name(), fontTableHeadingNonBoldData));
				table31.addCell(createCenteredCell(pclDataResult.get(i).getCurr_status(), fontTableHeadingNonBoldData));
				table31.addCell(createCenteredCell(pclDataResult.get(i).getLoss_info(), fontTableHeadingNonBoldData));
				table31.addCell(createCenteredCell(pclDataResult.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table31.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table31);

		PdfPTable tableleftFM30 = new PdfPTable(1);
		tableleftFM30.setWidthPercentage(100);
		tableleftFM30.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell48 = createNumberedCell("31.    ", "Security Lapses (Espionage).", fontTableHeading);
		establishmentCell48.setPaddingTop(30f);
		establishmentCell48.setPaddingBottom(30f);
		tableleftFM30.addCell(establishmentCell48);
		document.add(tableleftFM30);

		PdfPTable table32 = new PdfPTable(7);
		table32.setWidthPercentage(100);
		float[] columnWidths32 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table32.setWidths(columnWidths32);

		// Header Row
		table32.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Date of Violation Reported from Higher HQ", fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Date of Violation Reported Initially by Unit/Formation to Higher HQ, if any",
				fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Number, Rank and Name", fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Current Status of Progress of Case", fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Loss of Information, if any", fontTableHeading, 1, 1));
		table32.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table32.addCell(createCenteredCell("(a)", fontTableHeading));
		table32.addCell(createCenteredCell("(b)", fontTableHeading));
		table32.addCell(createCenteredCell("(c)", fontTableHeading));
		table32.addCell(createCenteredCell("(d)", fontTableHeading));
		table32.addCell(createCenteredCell("(e)", fontTableHeading));
		table32.addCell(createCenteredCell("(f)", fontTableHeading));
		table32.addCell(createCenteredCell("(g)", fontTableHeading));

		if(elDataResult.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table32.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}

		if(elDataResult.size()>0)
		{
			for(int i=0;i<elDataResult.size();i++)
			{ 
				table32.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				Date date1 = elDataResult.get(i).getDate_violation_fmn();
				table32.addCell(createCenteredCell(outputFormat.format(date1), fontTableHeadingNonBoldData));
				
				Date date2 = elDataResult.get(i).getDate_violation_initial();
				table32.addCell(createCenteredCell(outputFormat.format(date2), fontTableHeadingNonBoldData));
				
				table32.addCell(createCenteredCell(elDataResult.get(i).getNumber_rank_name(), fontTableHeadingNonBoldData));
				table32.addCell(createCenteredCell(elDataResult.get(i).getCurr_status(), fontTableHeadingNonBoldData));
				table32.addCell(createCenteredCell(elDataResult.get(i).getLoss_info(), fontTableHeadingNonBoldData));
				table32.addCell(createCenteredCell(elDataResult.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table32.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table32);

//		----------------------------------------page13--------------------------------

		PdfPTable tableleftFM31 = new PdfPTable(1);
		tableleftFM31.setWidthPercentage(100);
		tableleftFM31.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell49 = createNumberedCell("32.    ",
				"Security Lapses (Compromise of Cell Phone / other Digital Artefacts by Inimical Agency/ Malwares) Observed in Unit.",
				fontTableHeading);
		establishmentCell49.setPaddingTop(10f);
		tableleftFM31.addCell(establishmentCell49);
		document.add(tableleftFM31);
		document.add(new Phrase("\n"));

		PdfPTable table33 = new PdfPTable(7);
		table33.setWidthPercentage(100);
		float[] columnWidths33 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table33.setWidths(columnWidths33);

		// Header Row
		table33.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Date of Violation Reported from Higher HQ", fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Date of Violation Reported Initially by Unit/Formation to Higher HQ, if any",
				fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Number, Rank and Name", fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Current Status of Progress of Case", fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Loss of Information, if any", fontTableHeading, 1, 1));
		table33.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table33.addCell(createCenteredCell("(a)", fontTableHeading));
		table33.addCell(createCenteredCell("(b)", fontTableHeading));
		table33.addCell(createCenteredCell("(c)", fontTableHeading));
		table33.addCell(createCenteredCell("(d)", fontTableHeading));
		table33.addCell(createCenteredCell("(e)", fontTableHeading));
		table33.addCell(createCenteredCell("(f)", fontTableHeading));
		table33.addCell(createCenteredCell("(g)", fontTableHeading));

		if(cpclDataResult.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table33.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}

		if(cpclDataResult.size()>0)
		{
			for(int i=0;i<cpclDataResult.size();i++)
			{ 
				table33.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				Date date1 = cpclDataResult.get(i).getDate_violation_fmn();
				table33.addCell(createCenteredCell(outputFormat.format(date1), fontTableHeadingNonBoldData));
				
				Date date2 = cpclDataResult.get(i).getDate_violation_initial();
				table33.addCell(createCenteredCell(outputFormat.format(date2), fontTableHeadingNonBoldData));
				
				table33.addCell(createCenteredCell(cpclDataResult.get(i).getNumber_rank_name(), fontTableHeadingNonBoldData));
				table33.addCell(createCenteredCell(cpclDataResult.get(i).getCurr_status(), fontTableHeadingNonBoldData));
				table33.addCell(createCenteredCell(cpclDataResult.get(i).getLoss_info(), fontTableHeadingNonBoldData));
				table33.addCell(createCenteredCell(cpclDataResult.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table33.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table33);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM32 = new PdfPTable(1);
		tableleftFM32.setWidthPercentage(100);
		tableleftFM32.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell50 = createNumberedCell("33.    ",
				"Security Lapses (Untraceable/ Loss of Accountable Documents) Observed during FS Check.",
				fontTableHeading);
		establishmentCell50.setPaddingTop(10f);
		tableleftFM32.addCell(establishmentCell50);
		document.add(tableleftFM32);
		document.add(new Phrase("\n"));

		PdfPTable table34 = new PdfPTable(7);
		table34.setWidthPercentage(100);
		float[] columnWidths34 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table34.setWidths(columnWidths34);

		// Header Row
		table34.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table34.addCell(
				createHeaderCell("Untraceable Document (Subject with File Number and Date)", fontTableHeading, 1, 1));
		table34.addCell(createHeaderCell("Classification", fontTableHeading, 1, 1));
		table34.addCell(createHeaderCell("Originator of Document", fontTableHeading, 1, 1));
		table34.addCell(createHeaderCell("Custodian of Documents", fontTableHeading, 1, 1));
		table34.addCell(createHeaderCell("Current Status of the Case", fontTableHeading, 1, 1));
		table34.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table34.addCell(createCenteredCell("(a)", fontTableHeading));
		table34.addCell(createCenteredCell("(b)", fontTableHeading));
		table34.addCell(createCenteredCell("(c)", fontTableHeading));
		table34.addCell(createCenteredCell("(d)", fontTableHeading));
		table34.addCell(createCenteredCell("(e)", fontTableHeading));
		table34.addCell(createCenteredCell("(f)", fontTableHeading));
		table34.addCell(createCenteredCell("(g)", fontTableHeading));

		if(getInspFsSecurityData.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table34.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		
		if(getInspFsSecurityData.size()>0)
		{
			for(int i=0;i<getInspFsSecurityData.size();i++)
			{ table34.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getUntraceable_document(), fontTableHeadingNonBoldData));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getClassification(), fontTableHeadingNonBoldData));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getOriginator_doc(), fontTableHeadingNonBoldData));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getCustodian_doc(), fontTableHeadingNonBoldData));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getCurrent_status(), fontTableHeadingNonBoldData));
				table34.addCell(createCenteredCell(getInspFsSecurityData.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table34.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table34);

		PdfPTable tableleftFM33 = new PdfPTable(1);
		tableleftFM33.setWidthPercentage(100);
		tableleftFM33.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell51 = createNumberedCell("34.    ", "Security Lapses (Loss of CD/DVD).",
				fontTableHeading);
		establishmentCell51.setPaddingTop(30f);
		establishmentCell51.setPaddingBottom(30f);
		tableleftFM33.addCell(establishmentCell51);
		document.add(tableleftFM33);

		PdfPTable table35 = new PdfPTable(7);
		table35.setWidthPercentage(100);
		float[] columnWidths35 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table35.setWidths(columnWidths35);

		// Header Row
		table35.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table35.addCell(
				createHeaderCell("Untraceable CD/DVD (Subject with CD/DVD Number and Date)", fontTableHeading, 1, 1));
		table35.addCell(createHeaderCell("Classification", fontTableHeading, 1, 1));
		table35.addCell(createHeaderCell("Originator of CD/DVD", fontTableHeading, 1, 1));
		table35.addCell(createHeaderCell("Custodian of CD/DVD", fontTableHeading, 1, 1));
		table35.addCell(createHeaderCell("Current Status of the Case", fontTableHeading, 1, 1));
		table35.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table35.addCell(createCenteredCell("(a)", fontTableHeading));
		table35.addCell(createCenteredCell("(b)", fontTableHeading));
		table35.addCell(createCenteredCell("(c)", fontTableHeading));
		table35.addCell(createCenteredCell("(d)", fontTableHeading));
		table35.addCell(createCenteredCell("(e)", fontTableHeading));
		table35.addCell(createCenteredCell("(f)", fontTableHeading));
		table35.addCell(createCenteredCell("(g)", fontTableHeading));

		if(getlostCdDvdData.size()<0)
{
	for (char letter = 'a'; letter <= 'g'; letter++) {
		table35.addCell(createCenteredCell(" ", fontTableHeading));
	}
}

if(getlostCdDvdData.size()>0)
{
	for (int i = 0; i < getlostCdDvdData.size(); i++) {
		table35.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
		table35.addCell(createCenteredCell(getlostCdDvdData.get(i).getUntraceble_cd_dvd(),
				fontTableHeadingNonBoldData));
		table35.addCell(
				createCenteredCell(getlostCdDvdData.get(i).getClassification(), fontTableHeadingNonBoldData));
		table35.addCell(
				createCenteredCell(getlostCdDvdData.get(i).getOriginator_cd(), fontTableHeadingNonBoldData));
		table35.addCell(
				createCenteredCell(getlostCdDvdData.get(i).getCustodian_cd(), fontTableHeadingNonBoldData));
		table35.addCell(
				createCenteredCell(getlostCdDvdData.get(i).getCurrent_status(), fontTableHeadingNonBoldData));
		table35.addCell(createCenteredCell(getlostCdDvdData.get(i).getRemarks(), fontTableHeadingNonBoldData));
		
	}
	
}	
table35.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table35);

//  		------------------------------------page14---------------------------------------

		PdfPTable tableleftFM34 = new PdfPTable(1);
		tableleftFM34.setWidthPercentage(100);
		tableleftFM34.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell52 = createNumberedCell("35.    ", "Loss of Identity Card and ECR token.",
				fontTableHeading);
		establishmentCell52.setPaddingTop(10f);
		tableleftFM34.addCell(establishmentCell52);
		document.add(tableleftFM34);
		document.add(new Phrase("\n"));

		PdfPTable table36 = new PdfPTable(7);
		table36.setWidthPercentage(100);
		float[] columnWidths36 = { 2f, 2f, 2f, 2f, 2f, 2f, 2f }; // Adjust widths as needed
		table36.setWidths(columnWidths36);

		// Header Row
		table36.addCell(createHeaderCell("Serial Number", fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Untraceable Document/Token (Subject with I Card/Token Number and Date)",
				fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Classification", fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Originator of Document/ECR Token", fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Custodian of Document/ECR Token", fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Current Status of the Case", fontTableHeading, 1, 1));
		table36.addCell(createHeaderCell("Remarks", fontTableHeading, 1, 1));

		// Second Header Row
		table36.addCell(createCenteredCell("(a)", fontTableHeading));
		table36.addCell(createCenteredCell("(b)", fontTableHeading));
		table36.addCell(createCenteredCell("(c)", fontTableHeading));
		table36.addCell(createCenteredCell("(d)", fontTableHeading));
		table36.addCell(createCenteredCell("(e)", fontTableHeading));
		table36.addCell(createCenteredCell("(f)", fontTableHeading));
		table36.addCell(createCenteredCell("(g)", fontTableHeading));

		if(getlostidEcrData.size()<0)
		{
			for (char letter = 'a'; letter <= 'g'; letter++) {
				table36.addCell(createCenteredCell(" ", fontTableHeading));
			}
		}
		
		if(getlostidEcrData.size()>0)
		{
			for (int i = 0; i < getlostidEcrData.size(); i++) {
				table36.addCell(createCenteredCell(String.valueOf(i + 1), fontTableHeading));
				table36.addCell(createCenteredCell(getlostidEcrData.get(i).getUntraceable_document_token(),
						fontTableHeadingNonBoldData));
				table36.addCell(
						createCenteredCell(getlostidEcrData.get(i).getClassification(), fontTableHeadingNonBoldData));
				table36.addCell(
						createCenteredCell(getlostidEcrData.get(i).getOriginator_doc_ecr(), fontTableHeadingNonBoldData));
				table36.addCell(
						createCenteredCell(getlostidEcrData.get(i).getCustodian_doc_ecr(), fontTableHeadingNonBoldData));
				table36.addCell(
						createCenteredCell(getlostidEcrData.get(i).getCurrent_status(), fontTableHeadingNonBoldData));
				table36.addCell(createCenteredCell(getlostidEcrData.get(i).getRemarks(), fontTableHeadingNonBoldData));
				
			}
			
		}
		table36.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table36);

		document.add(new Phrase("\n"));
		PdfPTable tableleftFM35 = new PdfPTable(1);
		tableleftFM35.setWidthPercentage(100);
		tableleftFM35.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell53 = createNumberedCell("36.    ", "Land.", fontTableHeading);
		establishmentCell53.setPaddingTop(10f);
		tableleftFM35.addCell(establishmentCell53);
		document.add(tableleftFM35);
		document.add(new Phrase("\n"));

		PdfPTable table37 = new PdfPTable(3);
		table37.setWidthPercentage(100);
		float[] columnWidths37 = { 7f, .5f, 3f }; // Adjust widths as needed
		table37.setWidths(columnWidths37);

		// Header Row
		table37.addCell(textCell(
				"(a) Particulars of Defence Land (category wise such as A1, A2 etc.) allotted & Survey Number.",
				fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getDefenceLandParticulars(), fontTableData));

				      // Row for (b) Land Record Register
				      table37.addCell(textCell("(b) Is a Land Record Register being maintained by the unit?", fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getLandRecordRegisterMaintained(), fontTableData));

				      // Row for (c) Land Demarcated
				      table37.addCell(textCell("(c) Has the land been demarcated?", fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getLandDemarcated(), fontTableData));

				      // Row for (d) Land Utilization
				      table37.addCell(textCell(
				"(d) How is the said land being utilised by the Unit / Formation HQ / Establishment?",
				fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getLandUtilized(), fontTableData));

				      // Row for (e) Vacant Land Details
				      table37.addCell(textCell(
				"(e) Details of vacant area or portion of Defence Land in close proximity of civil inhabitation",
				fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getVacantLandDetails(), fontTableData));

				      // Row for (f) Safety Measures
				      table37.addCell(textCell(
				"(f) Measures being taken by the unit to ensure safety of the Defence Land and save it from encroachment. Are these measures adequate?",
				fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getSafetyMeasuresAdequate(), fontTableData));

				      // Row for (g) Eviction Action
				      table37.addCell(textCell("(g) Action taken to evict encroachment, if any?", fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getEvictionActionDetails(), fontTableData));

				      // Row for (h) Remarks/Suggestions
				      table37.addCell(textCell("(h) Remarks / Suggestions?", fontTableData));
				      table37.addCell(createCenteredCell(":", fontTableData));
				      table37.addCell(createCenteredCell(landDataResult.get(0).getRemarksSuggestions(), fontTableData));
				      table37.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table37);

//		-------------------------------------page 16---------------------------------------
		document.newPage();

		PdfPTable tableleftFM36 = new PdfPTable(1);
		tableleftFM36.setWidthPercentage(100);
		tableleftFM36.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell54 = createNumberedCell("37.    ",
				"Difficulties Experienced with respect to Conduct of Training and Administration.", fontTableHeading);
		establishmentCell54.setPaddingTop(20f);
		tableleftFM36.addCell(establishmentCell54);
		document.add(tableleftFM36);

		PdfPTable tableleftFM37 = new PdfPTable(1);
		tableleftFM37.setWidthPercentage(100);
		tableleftFM37.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell55 = createNumberedCell("38.    ",
				"Employment of Unit during the Period Covered by the Report.", fontTableHeading);
		establishmentCell55.setPaddingTop(20f);
		tableleftFM37.addCell(establishmentCell55);
		document.add(tableleftFM37);

		PdfPTable tableleftFM38 = new PdfPTable(1);
		tableleftFM38.setWidthPercentage(100);
		tableleftFM38.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell56 = createNoBordertext1("39.    ",
				"Summary of Report of Inspecting Officer (Part III & IV) of the Annual Inspection Report of the last five years is attached as Annexure.",
				fontTableData);
		establishmentCell56.setPaddingTop(20f);
		tableleftFM38.addCell(establishmentCell56);
		document.add(tableleftFM38);

		PdfPTable tableleftFM39 = new PdfPTable(1);
		tableleftFM39.setWidthPercentage(100);
		tableleftFM39.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell establishmentCell57 = createNoBordertext1("40.    ",
				"Certificate. I, IC ________ Rank ______________ Name ____________, (Appointment)_________________certify that:-",
				fontTableData);
		establishmentCell57.setPaddingTop(20f);
		establishmentCell57.setPaddingBottom(20f);
		tableleftFM39.addCell(establishmentCell57);
		document.add(tableleftFM39);

		PdfPTable table38 = new PdfPTable(2);
		table38.setWidthPercentage(100);
		float[] columnWidths38 = { 9f, 1f }; // Adjust widths as needed
		table38.setWidths(columnWidths38);

		// Header Row
		table38.addCell(textCell1(
				"(a)	  All information given in the Inspection Reports in respect of my Unit/ Formation HQ/ Establishment is correct.",
				fontTableData));

		table38.addCell(textCell1("", fontTableData));

		table38.addCell(textCell1(
				"(b)	  All accounts, both Regimental and Public have been reflected and no other accounts, Public or Regimental, are being maintained in the unit and that public funds are managed in accordance with DPM-2011.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(c)   All activities of Family Welfare Organisation are in accordance with directives issued by Adjutant General’s Branch, IHQ of MoD (Army) and are aimed at ensuring empowerment of spouses and wards of serving personnel of my Unit / Formation HQ / Establishment.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.addCell(textCell1(
				"(d)   I have ensured maximum availability of officers in the Unit/ Formation HQ/ Establishment during the Annual Inspection.",
				fontTableData));
		table38.addCell(textCell1("", fontTableData));
		table38.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table38);

		PdfPTable table39 = new PdfPTable(2);
		table39.setWidthPercentage(100);
		float[] columnWidths39 = { 6f, 4f }; // Adjust widths as needed
		table39.setWidths(columnWidths39);

		table39.addCell(textCell1("Place :", fontTableData));
		table39.addCell(textCell1("", fontTableData));

		table39.addCell(textCell1("Dated :", fontTableData));
		table39.addCell(textCell1("(Signature of Head of Unit/\nFormation HQ/ Establishment)", fontTableData));
		table39.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table39);

//		---------------------------------------page 17--------------------------------

		
		document.close();
//		----------------------------------------------------------------------------------

		super.buildPdfMetadata(model, document, request);
	}

	private static PdfPCell createNoBordertext(String number, String text, Font font) {
		Chunk numberChunk = new Chunk(number, font);
		Chunk textChunk = new Chunk(text, font);
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(45f); // Adjust padding as needed
		cell.setPaddingBottom(10f); // Adjust padding as needed
		return cell;
	}

	private static PdfPCell createNoBordertext1(String number, String text, Font font) {
		// Create a bold font for the number

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font regularFont = FontFactory.getFont(FontFactory.TIMES, 12);
		Chunk numberChunk = new Chunk(number, fontTableHeading); // Use bold font for the number
		Chunk textChunk = new Chunk(text, regularFont);

		// Create a paragraph and add the chunks
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		// Create a cell and set its properties
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(-5f); // Adjust padding as needed

		return cell;
	}

	private PdfPCell createCenteredCell(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private PdfPCell textCell(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private PdfPCell textCell1(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(10f); // Set padding

		return cell;
	}
	
	private PdfPCell textCell2(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the text horizontally
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Center align the text vertically
		cell.setPadding(5f); // Set padding

		return cell;
	}

	private static PdfPCell createHeaderCell(String text, Font font, int rowspan, int colspan) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setRowspan(rowspan);
		cell.setColspan(colspan); // Set the colspan
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

	private PdfPCell createNumberedCell(String number, String text, Font font) {
		// Create the number chunk
		Chunk numberChunk = new Chunk(number, font);

		// Create the text chunk
		Chunk textChunk = new Chunk(text, font);
		textChunk.setUnderline(0.1f, -2f); // Set underline for the text chunk

		// Create a paragraph and add the chunks
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		// Create a PdfPCell and set its properties
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align text to the left
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align text to the middle vertically
		cell.setPaddingLeft(-5f); // Adjust padding as needed

		return cell;
	}

//	class ImageBackgroundEvent implements PdfPTableEvent {
//		protected Image image;
//
//		HttpServletRequest request;
//
//		ImageBackgroundEvent(HttpServletRequest request) {
//			this.request = request;
//		}
//
//		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
//				PdfContentByte[] canvases) {
//			String ip = "";
//			if (request != null) {
//				ip = request.getHeader("X-FORWARDED-FOR");
//				if (ip == null || "".equals(ip)) {
//					ip = request.getRemoteAddr();
//				}
//			}
//			Date now = new Date();
//			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
//			String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;
//
//			Image img = null;
//			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
//					BufferedImage.TYPE_INT_ARGB);
//			Graphics graphics = bufferedImage.getGraphics();
//			graphics.setColor(Color.lightGray);
//			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
//			graphics.drawString(watermark + watermark, 0, 20);
//
//			try {
//				try {
//					img = com.lowagie.text.Image.getInstance(bufferedImage, null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (BadElementException e) {
//				e.printStackTrace();
//			}
//			this.image = img;
//
//			try {
//
//				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
//
//				int tableWidth = (int) table.getTotalWidth();
//				int first = 0;
//				if (tableWidth == 523) {
//					first = 750;
//				}
//				if (tableWidth == 770) {
//					first = 500;
//				}
//
//				int last = first - (int) table.getRowHeight(0);
//				while (first > last) {
//					image.setAbsolutePosition(30, first);
//					cb.addImage(image, false);
//					first -= 30;
//				}
//			} catch (DocumentException e) {
//				throw new ExceptionConverter(e);
//			}
//		}
//	}
//	
//	

public class ImageBackgroundEvent implements PdfPTableEvent {
    protected Image image;
    private HttpServletRequest request;
    private String username; // Assume username is passed or set

    public ImageBackgroundEvent(HttpServletRequest request, String username) {
        this.request = request;
        this.username = username;
    }

    @Override
    public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
                            PdfContentByte[] canvases) {
        // Get IP address
        String ip = "";
        if (request != null) {
            ip = request.getHeader("X-FORWARDED-FOR");
            if (ip == null || ip.isEmpty()) {
                ip = request.getRemoteAddr();
            }
        }

        // Create watermark text
        Date now = new Date();
        String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(now);
        String watermark = "Generated by " + username + " on " + dateString + " with IP " + ip;

        // Create watermark image
        BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(211, 211, 211, 100)); // Semi-transparent light gray
      //  graphics.setFont(new Font("Arial", Font.NORMAL, 12));
        graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
        graphics.drawString(watermark, 10, 20);
        graphics.dispose();

        try {
            image = Image.getInstance(bufferedImage, null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            // Use background canvas to ensure watermark is behind table
            PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
            float tableHeight = 0;
            for (float height : heights) {
                tableHeight += height; // Sum all row heights
            }

            // Rotate watermark for diagonal effect
            image.setRotationDegrees(0);
            float tableWidth = table.getTotalWidth();
            float pageWidth = table.getWidthPercentage() == 100 ? 595 : tableWidth; // Approximate A4 width if 100%
            float startY = tableHeight ; // Start from bottom of table
            float endY = 0; // Bottom of table area
            float x = 50; // Left margin

            // Repeat watermark diagonally across table height
            for (float y = endY; y < startY; y += 40) {
                image.setAbsolutePosition(x, y);
                cb.addImage(image);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 10, 1);
		private int totalPages = 0;
		private Phrase footerPhrase; // Store the phrase for the footer

		public PageNumeration(PdfWriter writer) {
			try {
				total = writer.getDirectContent().createTemplate(30, 15);
			} catch (Exception e) {
				e.printStackTrace();
			}
			totalPages = 0;
			// Initialize the footer phrase here.  The total pages part will be updated later.
			footerPhrase = new Phrase("Page 1 of 1", fontTableHeading1); // Default starting value
			writer.setPageEvent(this);
		}

		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			totalPages = 0;
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {

			try {
				PdfContentByte cb = writer.getDirectContent(); // Get direct content

				// Update the footer phrase on each page
				footerPhrase = new Phrase("Page No. " + writer.getPageNumber(), fontTableHeading1);

				// Add the footer to the page
				ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footerPhrase,
						(document.right() - document.left()) / 2 + document.leftMargin(),
						document.bottom() - 20, // Adjust vertical position as needed
						0);

			} catch (Exception de) {
				throw new ExceptionConverter(de);
			}
		}

		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {
			// Now, `totalPages` should have the correct value.
			try {
				// Update the footer phrase one last time, now that we know the final total
				footerPhrase = new Phrase("Page " + totalPages + " of " + totalPages, fontTableHeading1);

			} catch (Exception ex) {
				// Handle exceptions as needed
				ex.printStackTrace();
			}
		}
	}

	
	
	

}