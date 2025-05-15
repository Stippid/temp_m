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
import com.model.InspectionReports.TB_MISO_INSP_ADDITIONAL_INFORMATION;
import com.model.InspectionReports.TB_MISO_INSP_ARMY_CORE_VALUES;
import com.model.InspectionReports.TB_MISO_INSP_COLLECTIVE_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_COMMENTS_ON_PARTII;
import com.model.InspectionReports.TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT;
import com.model.InspectionReports.TB_MISO_INSP_INDIVIDUAL_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_INTERIOR_ECONOMY;
import com.model.InspectionReports.TB_MISO_INSP_MANAGEMENT_TRAINING;
import com.model.InspectionReports.TB_MISO_INSP_MATERIAL_MANAGEMENT;
import com.model.InspectionReports.TB_MISO_INSP_MORALE_MOTIVATION;
import com.model.InspectionReports.TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS;
import com.model.InspectionReports.TB_MISO_INSP_PERSONNEL_MANAGEMENT;

public class PDF_ANUAL_REPORT_PART_3A extends AbstractPdfView {

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

	public PDF_ANUAL_REPORT_PART_3A(String Type, List<String> TH, String username, ArrayList<ArrayList<String>> list1) {
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
		// Add page count below "RESTRICTED"
//		Chunk pageCountChunk = new Chunk("Page " + document.getPageNumber(), fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk); // Add page count to the header

		// Add page count placeholder in the header
//		Chunk pageCountChunk = new Chunk("Page {pageNumber}", fontTableHeading1);
//		pageCountChunk.setUnderline(0.1f, -2f);
//		p.add(pageCountChunk); // Add page count placeholder to the header

		p.add("\n");
		p.add("\n");

//		p.add(underline3);
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
//		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);
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
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");

		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_INDIVIDUAL_TRAINING> getIndividual_Trainingurl = (List<TB_MISO_INSP_INDIVIDUAL_TRAINING>) model
				.get("Individual_Training");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_COLLECTIVE_TRAINING> getCollective_Trainingurl = (List<TB_MISO_INSP_COLLECTIVE_TRAINING>) model
				.get("Collective_Training");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_MANAGEMENT_TRAINING> getManagement_of_Trainingurl = (List<TB_MISO_INSP_MANAGEMENT_TRAINING>) model
				.get("Management_of_Training");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_PERSONNEL_MANAGEMENT> getPersonnel_Managementurl = (List<TB_MISO_INSP_PERSONNEL_MANAGEMENT>) model
				.get("Personnel_Management");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_INTERIOR_ECONOMY> getInterior_Economyurl = (List<TB_MISO_INSP_INTERIOR_ECONOMY>) model
				.get("Interior_Economy");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_MORALE_MOTIVATION> getMorale_and_Motivationurl = (List<TB_MISO_INSP_MORALE_MOTIVATION>) model
				.get("Morale_and_Motivation");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_MATERIAL_MANAGEMENT> getMaterial_Management = (List<TB_MISO_INSP_MATERIAL_MANAGEMENT>) model
				.get("Material_Management");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS> getMiscellaneous_Aspects = (List<TB_MISO_INSP_OTHER_MISCELLANEOUS_ASPECTS>) model
				.get("Miscellaneous_Aspects");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_ARMY_CORE_VALUES> getArmy_Core_Values = (List<TB_MISO_INSP_ARMY_CORE_VALUES>) model
				.get("Army_Core_Values");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT> getHuman_Resource_Developement = (List<TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT>) model
				.get("Human_Resource_Developement");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_ADDITIONAL_INFORMATION> getAdditional_Information = (List<TB_MISO_INSP_ADDITIONAL_INFORMATION>) model
				.get("Additional_Information");
		
		@SuppressWarnings("unchecked")
		List<TB_MISO_INSP_COMMENTS_ON_PARTII> getComments_of_Insp = (List<TB_MISO_INSP_COMMENTS_ON_PARTII>) model
				.get("Comments_of_Insp");


		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		Font fontTableHeadingNoBold = FontFactory.getFont(FontFactory.TIMES, 12);


		Font fontTableData = new Font(Font.HELVETICA, 12);


		PageNumeration event = new PageNumeration(arg2);
		arg2.setPageEvent(event);
		document.setPageCount(1);
		Chunk underline69 = new Chunk("PART - III (A)", fontTableHeading);
		Paragraph headingParagraph1 = new Paragraph();
		headingParagraph1.add(underline69);
		headingParagraph1.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph1);

		Chunk underline68 = new Chunk("REPORT OF INSPECTING OFFICER", fontTableHeading);
		Paragraph headingParagraph = new Paragraph();
		headingParagraph.add(underline68);
		headingParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraph);

		Chunk underlinetitle = new Chunk("TRAINING", fontTableHeading);
		Paragraph headingParagraphTitle = new Paragraph();
		headingParagraphTitle.add(underlinetitle);
		headingParagraphTitle.setAlignment(Element.ALIGN_CENTER);
		document.add(headingParagraphTitle);

		PdfPTable Individual_Training = new PdfPTable(1);
		Individual_Training.setWidthPercentage(100);
		Individual_Training.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Individual_Training_text = createNumberedCell("    ", "Individual Training", fontTableHeading);
		Individual_Training_text.setPaddingTop(20f);
		Individual_Training.addCell(Individual_Training_text);
		document.add(Individual_Training);

		PdfPTable T1 = new PdfPTable(1);
		T1.setWidthPercentage(100);
		T1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 1: Physical Training
		PdfPCell physicalTrainingCell = createNumberedCellNounderlineBold("1.    ", "Physical Training : ", 
				getIndividual_Trainingurl.get(0).getPhysical_training(), fontTableHeadingNoBold);
		physicalTrainingCell.setPaddingTop(20f);
		T1.addCell(physicalTrainingCell);

		// Item 2: Weapon Training
		PdfPCell weaponTrainingCell = createNumberedCellNounderlineBold("2.    ", "Weapon Training : ", 
				getIndividual_Trainingurl.get(0).getWeapon_training(), fontTableHeadingNoBold);
		weaponTrainingCell.setPaddingTop(20f);
		T1.addCell(weaponTrainingCell);

		// Item 3: Weapon Training Results
		PdfPCell weaponTrainingResultsCell = createNumberedCellNounderlineBold("3.    ", "Weapon Training Results : ", 
				getIndividual_Trainingurl.get(0).getWeapon_training_results(), fontTableHeadingNoBold);
		weaponTrainingResultsCell.setPaddingTop(20f);
		T1.addCell(weaponTrainingResultsCell);

		// Item 4: Night Training
		PdfPCell nightTrainingCell = createNumberedCellNounderlineBold("4.    ", "Night Training : ", 
				getIndividual_Trainingurl.get(0).getNight_training(), fontTableHeadingNoBold);
		nightTrainingCell.setPaddingTop(20f);
		T1.addCell(nightTrainingCell);

		// Item 5: Specialist Training
		PdfPCell specialistTrainingCell = createNumberedCellNounderlineBold("5.    ", "Specialist Training : ", 
				getIndividual_Trainingurl.get(0).getSpecialist_training(), fontTableHeadingNoBold);
		specialistTrainingCell.setPaddingTop(20f);
		T1.addCell(specialistTrainingCell);

		// Item 6: YOs Training
		PdfPCell yoTrainingCell = createNumberedCellNounderlineBold("6.    ", "YOs Training : ", 
				getIndividual_Trainingurl.get(0).getYos_training(), fontTableHeadingNoBold);
		yoTrainingCell.setPaddingTop(20f);
		T1.addCell(yoTrainingCell);

		// Item 7: Officers Training
		PdfPCell officersTrainingCell = createNumberedCellNounderlineBold("7.    ", "Officers Training : ", 
				getIndividual_Trainingurl.get(0).getOfficers_training(), fontTableHeadingNoBold);
		officersTrainingCell.setPaddingTop(20f);
		T1.addCell(officersTrainingCell);

		// Item 8: Training of JCOs & NCOs
		PdfPCell jcoNcoTrainingCell = createNumberedCellNounderlineBold("8.    ", "Training of JCOs & NCOs : ", 
				getIndividual_Trainingurl.get(0).getTraining_jco_nco(), fontTableHeadingNoBold);
		jcoNcoTrainingCell.setPaddingTop(20f);
		T1.addCell(jcoNcoTrainingCell);

		// Item 9: Training of ACC Commission/SCO
		PdfPCell accCommissionCell = createNumberedCellNounderlineBold("9.    ", "Training of ACC Commission/SCO : ", 
				getIndividual_Trainingurl.get(0).getTraining_acc_sco(), fontTableHeadingNoBold);
		accCommissionCell.setPaddingTop(20f);
		T1.addCell(accCommissionCell);
		T1.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T1);
		
		PdfPTable Collective_Training = new PdfPTable(1);
		Collective_Training.setWidthPercentage(100);
		Collective_Training.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Collective_Training_text = createNumberedCell("    ", "Collective Training", fontTableHeading);
		Collective_Training_text.setPaddingTop(20f);
		Collective_Training.addCell(Collective_Training_text);
		document.add(Collective_Training);

		PdfPTable T2 = new PdfPTable(1);
		T2.setWidthPercentage(100);
		T2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 10: Training Exercise / Training Camp
		PdfPCell trainingExerciseCell = createNumberedCellNounderlineBold("10.    ", 
				"Training Exercise / Training Camp :     ", getCollective_Trainingurl.get(0).getTraining_exercise(), fontTableHeadingNoBold);
		trainingExerciseCell.setPaddingTop(20f);
		T2.addCell(trainingExerciseCell);

		// Item 11: Field Firing
		PdfPCell fieldFiringCell = createNumberedCellNounderlineBold("11.    ", "Field Firing :     ", getCollective_Trainingurl.get(0).getField_firing(),
				fontTableHeadingNoBold);
		fieldFiringCell.setPaddingTop(20f);
		T2.addCell(fieldFiringCell);

		// Item 12: Mobilisation
		PdfPCell mobilisationCell = createNumberedCellNounderlineBold("12.    ", "Mobilisation :     ", getCollective_Trainingurl.get(0).getMobilisation(),
				fontTableHeadingNoBold);
		mobilisationCell.setPaddingTop(20f);
		T2.addCell(mobilisationCell);
		T2.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T2);

		PdfPTable Management_of_Training = new PdfPTable(1);
		Management_of_Training.setWidthPercentage(100);
		Management_of_Training.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Management_of_Training_text = createNumberedCell("    ", "Management of Training", fontTableHeading);
		Management_of_Training_text.setPaddingTop(20f);
		Management_of_Training.addCell(Management_of_Training_text);
		document.add(Management_of_Training);

		PdfPTable T3 = new PdfPTable(1);
		T3.setWidthPercentage(100);
		T3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 13: Use of Training Infrastructure including Training Aids
		PdfPCell trainingInfraCell = createNumberedCellNounderlineBold("13.    ",
				"Use of Training Infrastructure including Training Aids :     ", getManagement_of_Trainingurl.get(0).getTraining_infrastructure() , fontTableHeadingNoBold);
		trainingInfraCell.setPaddingTop(20f);
		T3.addCell(trainingInfraCell);

		// Item 14: Special Points
		PdfPCell specialPointsCell = createNumberedCellNounderlineBold("14.    ", "Special Points :		",  getManagement_of_Trainingurl.get(0).getSpecial_points(),
				fontTableHeadingNoBold);
		specialPointsCell.setPaddingTop(20f);
		T3.addCell(specialPointsCell);
		T3.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T3);

		PdfPTable Personnel_Management = new PdfPTable(1);
		Personnel_Management.setWidthPercentage(100);
		Personnel_Management.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Personnel_Management_text = createNumberedCell("    ", "Personnel Management", fontTableHeading);
		Personnel_Management_text.setPaddingTop(20f);
		Personnel_Management.addCell(Personnel_Management_text);
		document.add(Personnel_Management);

		PdfPTable T4 = new PdfPTable(1);
		T4.setWidthPercentage(100);
		T4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 15: Discipline
		PdfPCell disciplineCell = createNumberedCellNounderlineBold("15.    ", "Discipline :     ", getPersonnel_Managementurl.get(0).getDiscipline(), fontTableHeadingNoBold);
		disciplineCell.setPaddingTop(20f);
		T4.addCell(disciplineCell);

		// Item 16: Health of Troops
		PdfPCell healthTroopsCell = createNumberedCellNounderlineBold("16.    ", "Health of Troops :     ", getPersonnel_Managementurl.get(0).getHealth_troops(),
				fontTableHeadingNoBold);
		healthTroopsCell.setPaddingTop(20f);
		T4.addCell(healthTroopsCell);

		// Item 17: Personal Documentation
		PdfPCell personalDocCell = createNumberedCellNounderlineBold("17.    ", "Personal Documentation :     ", getPersonnel_Managementurl.get(0).getPersonal_documentation(),
				fontTableHeadingNoBold);
		personalDocCell.setPaddingTop(20f);
		T4.addCell(personalDocCell);
		T4.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T4);

		PdfPTable T5 = createFormattedTable("T5", "18", "Interior Economy", "");
		document.add(T5);

		PdfPTable T6 = new PdfPTable(1);
		T6.setWidthPercentage(100);
		T6.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Sub-item (i): Living Condition of all ranks and families
		PdfPCell livingConditionCell = createNumberedCellNounderlineandExtraPaddingleftBold("(i)    ",
				"Living Condition of all ranks and families :     ", getInterior_Economyurl.get(0).getLiving_condition(), fontTableHeadingNoBold);
		livingConditionCell.setPaddingTop(20f);
		T6.addCell(livingConditionCell);

		// Sub-item (ii): State of issue of personal clothing
		PdfPCell personalClothingCell = createNumberedCellNounderlineandExtraPaddingleftBold("(ii)    ",
				"State of issue of personal clothing :     ", getInterior_Economyurl.get(0).getState_personal_clothing(), fontTableHeadingNoBold);
		personalClothingCell.setPaddingTop(20f);
		T6.addCell(personalClothingCell);

		// Sub-item (iii): Initiative taken by units to carry out improvement in their
		// living habitat
		PdfPCell initiativeCell = createNumberedCellNounderlineandExtraPaddingleftBold("(iii)    ",
				"Initiative taken by units to carry out improvement in their living habitat :     ", getInterior_Economyurl.get(0).getInitiative_units(),
				fontTableHeadingNoBold);
		initiativeCell.setPaddingTop(20f);
		T6.addCell(initiativeCell);

		// Sub-item (iv): Facilities provided in the living area, in terms of comfort
		PdfPCell facilitiesCell = createNumberedCellNounderlineandExtraPaddingleftBold("(iv)    ",
				"Facilities provided in the living area, in terms of comfort :     ", getInterior_Economyurl.get(0).getFacilities_living_area(), fontTableHeadingNoBold);
		facilitiesCell.setPaddingTop(20f);
		T6.addCell(facilitiesCell);

		// Sub-item (v): Modern equipment provided in the Company kitchens
		PdfPCell kitchenEquipmentCell = createNumberedCellNounderlineandExtraPaddingleftBold("(v)    ",
				"Modern equipment provided in the Company kitchens to facilitate cooking as well as to save manpower :     ", getInterior_Economyurl.get(0).getModern_equipment_Company(),
				fontTableHeadingNoBold);
		kitchenEquipmentCell.setPaddingTop(20f);
		T6.addCell(kitchenEquipmentCell);

		// Sub-item (vi): Equipment Procured by unit for area maintenance
		PdfPCell areaMaintenanceCell = createNumberedCellNounderlineandExtraPaddingleftBold("(vi)    ",
				"Equipment Procured by unit for area maintenance to economise on manpower, that can be employed on operational duties or for training :     ", getInterior_Economyurl.get(0).getEquipment_procured_manpower(),
				fontTableHeadingNoBold);
		areaMaintenanceCell.setPaddingTop(20f);
		T6.addCell(areaMaintenanceCell);

		// Sub-item (vii): Timely and correct Publication of individual casualties
		PdfPCell casualtiesCell = createNumberedCellNounderlineandExtraPaddingleftBold("(vii)    ",
				"Timely and correct Publication of individual casualties :     ", getInterior_Economyurl.get(0).getTimely_correct_Publication(), fontTableHeadingNoBold);
		casualtiesCell.setPaddingTop(20f);
		T6.addCell(casualtiesCell);

		// Sub-item (viii): State of individual Documentation
		PdfPCell documentationCell = createNumberedCellNounderlineandExtraPaddingleftBold("(viii)    ",
				"State of individual Documentation :     ", getInterior_Economyurl.get(0).getState_documentation(), fontTableHeadingNoBold);
		documentationCell.setPaddingTop(20f);
		T6.addCell(documentationCell);

		document.add(T6);
		T6.setTableEvent(new ImageBackgroundEvent(request,username));
		document.newPage();

		PdfPTable T7 = createFormattedTable("T7", "19", "Morale and Motivation", "");
		T7.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T7);

		PdfPTable T8 = new PdfPTable(1);
		T8.setWidthPercentage(100);
		T8.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Sub-item (i): State of Leave
		PdfPCell leaveCell = createNumberedCellNounderlineandExtraPaddingleftBold("(i)    ", "State of Leave :     ", getMorale_and_Motivationurl.get(0).getState_leave(),
				fontTableHeadingNoBold);
		leaveCell.setPaddingTop(20f);
		T8.addCell(leaveCell);

		// Sub-item (ii): Discipline State
		PdfPCell disciplineNewCell = createNumberedCellNounderlineandExtraPaddingleftBold("(ii)    ",
				"Discipline State :     ", getMorale_and_Motivationurl.get(0).getDiscipline_state(), fontTableHeadingNoBold);
		disciplineNewCell.setPaddingTop(20f);
		T8.addCell(disciplineNewCell);

		// Sub-item (iii): State of Sick Report
		PdfPCell sickReportCell = createNumberedCellNounderlineandExtraPaddingleftBold("(iii)    ",
				"State of Sick Report :     ", getMorale_and_Motivationurl.get(0).getSick_report(), fontTableHeadingNoBold);
		sickReportCell.setPaddingTop(20f);
		T8.addCell(sickReportCell);

		// Sub-item (iv): Performance in Trg/Professional Competitions
		PdfPCell trainingPerformanceCell = createNumberedCellNounderlineandExtraPaddingleftBold("(iv)    ",
				"Performance in Trg/Professional Competitions :     ", getMorale_and_Motivationurl.get(0).getPerformance_trg_professional(), fontTableHeadingNoBold);
		trainingPerformanceCell.setPaddingTop(20f);
		T8.addCell(trainingPerformanceCell);

		// Sub-item (v): Performance on Course
		PdfPCell coursePerformanceCell = createNumberedCellNounderlineandExtraPaddingleftBold("(v)    ",
				"Performance on Course :     ", getMorale_and_Motivationurl.get(0).getPerformance_course(), fontTableHeadingNoBold);
		coursePerformanceCell.setPaddingTop(20f);
		T8.addCell(coursePerformanceCell);

		// Sub-item (vi): Performance in Sports
		PdfPCell sportsPerformanceCell = createNumberedCellNounderlineandExtraPaddingleftBold("(vi)    ",
				"Performance in Sports :     ", getMorale_and_Motivationurl.get(0).getPerformance_sports(), fontTableHeadingNoBold);
		sportsPerformanceCell.setPaddingTop(20f);
		T8.addCell(sportsPerformanceCell);

		// Sub-item (vii): State of Eqpt Maint
		PdfPCell equipmentMaintenanceCell = createNumberedCellNounderlineandExtraPaddingleftBold("(vii)    ",
				"State of Eqpt Maint :     ", getMorale_and_Motivationurl.get(0).getState_eqpt_maint(), fontTableHeadingNoBold);
		equipmentMaintenanceCell.setPaddingTop(20f);
		T8.addCell(equipmentMaintenanceCell);

		// Sub-item (viii): Interior Economy
		PdfPCell interiorEconomyCell = createNumberedCellNounderlineandExtraPaddingleftBold("(viii)    ",
				"Interior Economy :     ", getMorale_and_Motivationurl.get(0).getInterior_economy(), fontTableHeadingNoBold);
		interiorEconomyCell.setPaddingTop(20f);
		T8.addCell(interiorEconomyCell);

		// Sub-item (ix): Documentation
		PdfPCell documentationNewCell = createNumberedCellNounderlineandExtraPaddingleftBold("(ix)    ",
				"Documentation :     ", getMorale_and_Motivationurl.get(0).getInterior_economy(), fontTableHeadingNoBold);
		documentationNewCell.setPaddingTop(20f);
		T8.addCell(documentationNewCell);

		// Sub-item (x): Involvement of Junior Leader in unit/Formation tasks
		PdfPCell juniorLeaderCell = createNumberedCellNounderlineandExtraPaddingleftBold("(x)    ",
				"Involvement of Junior Leader in unit/Formation tasks :     ", getMorale_and_Motivationurl.get(0).getInvolvement_junior_leader(), fontTableHeadingNoBold);
		juniorLeaderCell.setPaddingTop(20f);
		T8.addCell(juniorLeaderCell);

		// Sub-item (xi): State of Regimental Institutions
		PdfPCell regimentalInstitutionsCell = createNumberedCellNounderlineandExtraPaddingleftBold("(xi)    ",
				"State of Regimental Institutions :     ", getMorale_and_Motivationurl.get(0).getState_regimental(), fontTableHeadingNoBold);
		regimentalInstitutionsCell.setPaddingTop(20f);
		T8.addCell(regimentalInstitutionsCell);

		// Sub-item (xii): Pers Discipline and Turnout
		PdfPCell disciplineTurnoutCell = createNumberedCellNounderlineandExtraPaddingleftBold("(xii)    ",
				"Pers Discipline and Turnout :     ", getMorale_and_Motivationurl.get(0).getPers_discipline(), fontTableHeadingNoBold);
		disciplineTurnoutCell.setPaddingTop(20f);
		T8.addCell(disciplineTurnoutCell);
		T8.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T8);

		PdfPTable Material_Management = new PdfPTable(1);
		Material_Management.setWidthPercentage(100);
		Material_Management.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Material_Management_text = createNumberedCell("    ", "Material Management", fontTableHeading);
		Material_Management_text.setPaddingTop(20f);
		Material_Management.addCell(Material_Management_text);
		document.add(Material_Management);

		PdfPTable T9 = new PdfPTable(1);
		T9.setWidthPercentage(100);
		T9.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 20: Vehicles
		PdfPCell vehiclesCell = createNumberedCellNounderlineBold("20.    ", "Vehicles :     ", getMaterial_Management.get(0).getVehicles(), fontTableHeadingNoBold);
		vehiclesCell.setPaddingTop(20f);
		T9.addCell(vehiclesCell);

		// Item 21: Eqpt including off rd eqpt for more than six months
		PdfPCell equipmentCell = createNumberedCellNounderlineBold("21.    ",
				"Eqpt including off rd eqpt for more than six months :     ", getMaterial_Management.get(0).getVehicles(), fontTableHeadingNoBold);
		equipmentCell.setPaddingTop(20f);
		T9.addCell(equipmentCell);

		// Item 22: Maintenance of Arms
		PdfPCell armsMaintenanceCell = createNumberedCellNounderlineBold("22.    ", "Maintenance of Arms :     ", getMaterial_Management.get(0).getMaintenance_arms(),
				fontTableHeadingNoBold);
		armsMaintenanceCell.setPaddingTop(20f);
		T9.addCell(armsMaintenanceCell);

		// Item 23: Maintenance of Amn
		PdfPCell amnMaintenanceCell = createNumberedCellNounderlineBold("23.    ", "Maintenance of Amn :     ", getMaterial_Management.get(0).getMaintenance_amn(),
				fontTableHeadingNoBold);
		amnMaintenanceCell.setPaddingTop(20f);
		T9.addCell(amnMaintenanceCell);

		// Item 24: Maintenance of Ordnance Stores
		PdfPCell ordnanceStoresCell = createNumberedCellNounderlineBold("24.    ", "Maintenance of Ordnance Stores :     ", getMaterial_Management.get(0).getMaintenance_ordnance_stores(),
				fontTableHeadingNoBold);
		ordnanceStoresCell.setPaddingTop(20f);
		T9.addCell(ordnanceStoresCell);

		// Item 25: Management of Public Funds & Financial Grants
		PdfPCell fundsManagementCell = createNumberedCellNounderlineBold("25.    ",
				"Management of Public Funds & Financial Grants :     ", getMaterial_Management.get(0).getManagement_public_funds(), fontTableHeadingNoBold);
		fundsManagementCell.setPaddingTop(20f);
		T9.addCell(fundsManagementCell);
		T9.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T9);

		PdfPTable Other_Miscellaneous_Aspects = new PdfPTable(1);
		Other_Miscellaneous_Aspects.setWidthPercentage(100);
		Other_Miscellaneous_Aspects.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell Other_Miscellaneous_Aspects_text = createNumberedCell("    ", "Other Miscellaneous Aspects",
				fontTableHeading);
		Other_Miscellaneous_Aspects_text.setPaddingTop(20f);
		Other_Miscellaneous_Aspects.addCell(Other_Miscellaneous_Aspects_text);
		document.add(Other_Miscellaneous_Aspects);

		PdfPTable T10 = new PdfPTable(1);
		T10.setWidthPercentage(100);
		T10.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		// Item 26: Security
		PdfPCell securityCell = createNumberedCellNounderlineBold("26.    ", "Security :     ", getMiscellaneous_Aspects.get(0).getSecurity(), fontTableHeadingNoBold);
		securityCell.setPaddingTop(20f);
		T10.addCell(securityCell);
		T10.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T10);

		PdfPTable T11 = createFormattedTable("T11", "27",
				"Measures taken to imbibe the following Indian Army  Core Values", "");
		document.add(T11);

		PdfPTable T12 = new PdfPTable(1);
		T12.setWidthPercentage(100);
		T12.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell A = createNumberedCellNounderlineandExtraPaddingleftBold("(a)    ", "Integrity (Imaandari) :     ", getArmy_Core_Values.get(0).getIntegrity(),
				fontTableHeadingNoBold);
		A.setPaddingTop(20f);
		T12.addCell(A);

		PdfPCell B = createNumberedCellNounderlineandExtraPaddingleftBold("(b)    ", "Loyalty (Wafadari) :     ",  getArmy_Core_Values.get(0).getLoyalty(),
				fontTableHeadingNoBold);
		B.setPaddingTop(20f);
		T12.addCell(B);

		PdfPCell C = createNumberedCellNounderlineandExtraPaddingleftBold("(c)    ", "Duty (Kartavya) :     ", getArmy_Core_Values.get(0).getDuty(),
				fontTableHeadingNoBold);
		C.setPaddingTop(20f);
		T12.addCell(C);

		PdfPCell D = createNumberedCellNounderlineandExtraPaddingleftBold("(d)    ", "Respect (Samman) :     ", getArmy_Core_Values.get(0).getRespect(),
				fontTableHeadingNoBold);
		D.setPaddingTop(20f);
		T12.addCell(D);

		PdfPCell E = createNumberedCellNounderlineandExtraPaddingleftBold("(e)    ",
				"Selfless Service (Niswarth Seva) :     ", getArmy_Core_Values.get(0).getSelfless_service(), fontTableHeadingNoBold);
		E.setPaddingTop(20f);
		T12.addCell(E);

		PdfPCell F = createNumberedCellNounderlineandExtraPaddingleftBold("(f)    ", "Courage (Himmat) :     ", getArmy_Core_Values.get(0).getCourage(),
				fontTableHeadingNoBold);
		F.setPaddingTop(20f);
		T12.addCell(F);

		PdfPCell G = createNumberedCellNounderlineandExtraPaddingleftBold("(g)    ", "Honour (Izzat) :     ", getArmy_Core_Values.get(0).getHonour(),
				fontTableHeadingNoBold);
		G.setPaddingTop(20f);
		T12.addCell(G);
		T12.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(T12);

		PdfPTable T13 = createFormattedTable("T13", "28", "Human Resource Developement", "");
		
		document.add(T13);

		PdfPTable T14 = new PdfPTable(1);
		T14.setWidthPercentage(100);
		T14.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell A1 = createNumberedCellNounderlineandExtraPaddingleftBold("(a)    ", "Junior Leader Development :     ", getHuman_Resource_Developement.get(0).getJunior_leader(),
				fontTableHeadingNoBold);
		A1.setPaddingTop(20f);
		T14.addCell(A1);

		PdfPCell B1 = createNumberedCellNounderlineandExtraPaddingleftBold("(b)    ", "Welfare of Troops :     ", getHuman_Resource_Developement.get(0).getWelfare_troops(),
				fontTableHeadingNoBold);
		B1.setPaddingTop(20f);
		T14.addCell(B1);

		PdfPCell C1 = createNumberedCellNounderlineandExtraPaddingleftBold("(c)    ",
				"Measures for resolution of individual problems of soldiers :     ", getHuman_Resource_Developement.get(0).getMeasures_resolution(), fontTableHeadingNoBold);
		C1.setPaddingTop(20f);
		T14.addCell(C1);

		PdfPCell D1 = createNumberedCellNounderlineandExtraPaddingleftBold("(d)    ",
				"Enhancement of education qualifications in the unit :     ", getHuman_Resource_Developement.get(0).getEnhancement_education(), fontTableHeadingNoBold);
		D1.setPaddingTop(20f);
		T14.addCell(D1);

		PdfPCell E1 = createNumberedCellNounderlineandExtraPaddingleftBold("(e)    ",
				"Training for career enhancement with respect to ACC/SCO Commission :     ", getHuman_Resource_Developement.get(0).getTraining_career(), fontTableHeadingNoBold);
		E1.setPaddingTop(20f);
		T14.addCell(E1);

		PdfPCell F1 = createNumberedCellNounderlineandExtraPaddingleftBold("(f)    ",
				"Trainin provided for sports/ professional activities like SA Firing etc to the tps to excel at Army/Service level:     ", getHuman_Resource_Developement.get(0).getTraining_provided(),
				fontTableHeadingNoBold);
		F1.setPaddingTop(20f);
		T14.addCell(F1);

		document.add(T14);

		PdfPTable T15 = new PdfPTable(1);
		T15.setWidthPercentage(100);
		T15.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell ObjectionCell = createNumberedCellNounderlineBold("29.    ", "Audit Objection :     ", getHuman_Resource_Developement.get(0).getAudit_objection(),
				fontTableHeadingNoBold);
		ObjectionCell.setPaddingTop(20f);
		T15.addCell(ObjectionCell);

		PdfPCell Complaints = createNumberedCellNounderlineBold("30.    ", "State of Complaints  :     ", getHuman_Resource_Developement.get(0).getState_complaints(),
				fontTableHeadingNoBold);
		Complaints.setPaddingTop(20f);
		T15.addCell(Complaints);

		PdfPCell ACR = createNumberedCellNounderlineBold("31.    ", "ACR Related Aspects :     ", getHuman_Resource_Developement.get(0).getArc(), fontTableHeadingNoBold);
		ACR.setPaddingTop(20f);
		T15.addCell(ACR);

		PdfPCell Miscellaneous = createNumberedCellNounderlineBold("32.    ", "Miscellaneous Aspects :     ", getHuman_Resource_Developement.get(0).getMiscellaneous(),
				fontTableHeadingNoBold);
		Miscellaneous.setPaddingTop(20f);
		T15.addCell(Miscellaneous);

		document.add(T15);

		PdfPTable T16 = createFormattedTable("T16", "33", "Additional Information", "");
		document.add(T16);

		PdfPTable T17 = new PdfPTable(1);
		T17.setWidthPercentage(100);
		T17.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell A11 = createNumberedCellNounderlineandExtraPaddingleftBold("(a)    ", "State of all Public Funds :     ", getAdditional_Information.get(0).getState_public_Funds(),
				fontTableHeadingNoBold);
		A11.setPaddingTop(20f);
		T17.addCell(A11);

		PdfPCell B11 = createNumberedCellNounderlineandExtraPaddingleftBold("(b)    ",
				"State of discp in the fmn incl state pending GCMs and DCMs, as applicable :     ", getAdditional_Information.get(0).getState_discp(),
				fontTableHeadingNoBold);
		B11.setPaddingTop(20f);
		T17.addCell(B11);

		PdfPCell C11 = createNumberedCellNounderlineandExtraPaddingleftBold("(c)    ", 
				"State of complaints and petitions :     ", getAdditional_Information.get(0).getState_complaints_petitions(), fontTableHeadingNoBold);
		C11.setPaddingTop(20f);
		T17.addCell(C11);

		PdfPCell D11 = createNumberedCellNounderlineandExtraPaddingleftBold("(d)    ", 
				"State of work in the Formation :     ", getAdditional_Information.get(0).getState_work_formation(), fontTableHeadingNoBold);
		D11.setPaddingTop(20f);
		T17.addCell(D11);

		PdfPCell E11 = createNumberedCellNounderlineandExtraPaddingleftBold("(e)    ", 
				"Any peculiar aspect observed :     ", getAdditional_Information.get(0).getPeculiar_aspect(), fontTableHeadingNoBold);
		E11.setPaddingTop(20f);
		T17.addCell(E11);

		document.add(T17);

		PdfPTable T18 = createFormattedTable("T18", "34", "Comments of Insp Offr on Part II", "");
		document.add(T18);

		PdfPTable T19 = new PdfPTable(1);
		T19.setWidthPercentage(100);
		T19.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPCell E111 = createNumberedCellNounderlineandExtraPaddingleftBold("    ",
				getComments_of_Insp.get(0).getComments_on_partii() , "",  fontTableHeadingNoBold);
		E111.setPaddingTop(20f);	
		T19.addCell(E111);

		document.add(T19);
		document.add(new Phrase("\n"));

		PdfPTable table39 = new PdfPTable(2);
		table39.setWidthPercentage(100);
		float[] columnWidths39 = { 6f, 4f };
		table39.setWidths(columnWidths39);

		table39.addCell(textCell1("Station :", fontTableData));

		table39.addCell(textCell1("(Signature of Inspecting Officer)", fontTableData));
		table39.addCell(textCell1("Dated :", fontTableData));
		table39.addCell(textCell1("", fontTableData));
		table39.setTableEvent(new ImageBackgroundEvent(request,username));
		document.add(table39);
		document.close();
		super.buildPdfMetadata(model, document, request);
	}

	private PdfPCell textCell1(String text, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(10f);

		return cell;
	}

	private PdfPCell createNumberedCell(String number, String text, Font font) {
		Chunk numberChunk = new Chunk(number, font);
		Chunk textChunk = new Chunk(text, font);
		textChunk.setUnderline(0.1f, -2f);
		Paragraph paragraph = new Paragraph();
		paragraph.add(numberChunk);
		paragraph.add(textChunk);

		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingLeft(-5f);

		return cell;
	}

	
	private PdfPCell createNumberedCellNounderlineBold(String number, String prefix, String value, Font fontNoBold) {
	    // Create a bold font for the value
	    Font fontBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

	    // Capitalize the first letter of the value
	    String capitalizedValue = (value == null || value.isEmpty()) ? "" : 
	        value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();

	    // Create a nested PdfPTable with 2 columns
	    PdfPTable nestedTable = new PdfPTable(2);
	    nestedTable.setWidthPercentage(100);
	    try {
	        // Set column widths: first column for number+prefix, second for value
	        nestedTable.setWidths(new float[] { 76.5f, 23.5f }); // Adjust these values as needed
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    // First column: Number + Prefix
	    Paragraph prefixParagraph = new Paragraph();
	    prefixParagraph.add(new Chunk(number, fontNoBold)); // Number (e.g., "1.    ")
	    prefixParagraph.add(new Chunk(prefix, fontNoBold)); // Prefix (e.g., "Physical Training : ")
	    
	    PdfPCell prefixCell = new PdfPCell(prefixParagraph);
	    prefixCell.setBorder(PdfPCell.NO_BORDER);
	    prefixCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    prefixCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    nestedTable.addCell(prefixCell);

	    // Second column: Value
	    PdfPCell valueCell = new PdfPCell(new Phrase(capitalizedValue, fontBold));
	    valueCell.setBorder(PdfPCell.NO_BORDER);
	    valueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    nestedTable.addCell(valueCell);

	    // Create the main PdfPCell to hold the nested table
	    PdfPCell cell = new PdfPCell(nestedTable);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setPaddingLeft(15f);

	    return cell;
	}


	private PdfPCell createNumberedCellNounderlineandExtraPaddingleftBold(String number, String prefix, String value, Font font) {
	    // Create a bold font for the value
	    Font fontBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

	    // Capitalize the first letter of the value
	    String capitalizedValue = (value == null || value.isEmpty()) ? "" : 
	        value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();

	    // Create a nested PdfPTable with 2 columns
	    PdfPTable nestedTable = new PdfPTable(2);
	    nestedTable.setWidthPercentage(100);
	    try {
	        // Set column widths: first column for number+prefix, second for value
	        nestedTable.setWidths(new float[] { 75f, 25f }); // Adjusted for distance as per previous request
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    // First column: Number + Prefix
	    Paragraph prefixParagraph = new Paragraph();
	    prefixParagraph.add(new Chunk(number, font)); // Number (e.g., "1.    ")
	    prefixParagraph.add(new Chunk(prefix, font)); // Prefix (e.g., "Physical Training : ")
	    
	    PdfPCell prefixCell = new PdfPCell(prefixParagraph);
	    prefixCell.setBorder(PdfPCell.NO_BORDER);
	    prefixCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    prefixCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    nestedTable.addCell(prefixCell);

	    // Second column: Value
	    PdfPCell valueCell = new PdfPCell(new Phrase(capitalizedValue, fontBold));
	    valueCell.setBorder(PdfPCell.NO_BORDER);
	    valueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    nestedTable.addCell(valueCell);

	    // Create the main PdfPCell to hold the nested table
	    PdfPCell cell = new PdfPCell(nestedTable);
	    cell.setBorder(PdfPCell.NO_BORDER);
	   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setPaddingLeft(40f); // Preserve original paddingLeft

	    return cell;
	}

	private PdfPTable createFormattedTable(String tableName, String number, String mainText, String additionalText) {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER); // Explicitly hide border

		// Create cell
		PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER); // Additional assurance of no border
		Phrase phrase = new Phrase();
		phrase.add(new Chunk(number + ".    ", FontFactory.getFont(FontFactory.TIMES, 12))); // Normal font for number
		Chunk textChunk = new Chunk(mainText, FontFactory.getFont(FontFactory.TIMES_BOLD, 12)); // Bold font for main
																								// text
		textChunk.setUnderline(0.1f, -2f); // Underline with specified thickness and position
		phrase.add(textChunk);
		if (!additionalText.isEmpty()) {
			phrase.add(new Chunk(" :     " + additionalText, FontFactory.getFont(FontFactory.TIMES, 12))); // Normal
																											// font for
																											// additional
																											// text
		}
		cell.addElement(phrase);
		cell.setPaddingTop(15f);
		cell.setPaddingLeft(15f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Align left
		table.addCell(cell);

		return table;
	}


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

	public static void addExpandableInfoBox(Document document, String textContent, Font textFont) throws DocumentException {

		// --- Basic Check: Don't add empty boxes ---
		if (textContent == null || textContent.trim().isEmpty()) {
			return;
		}

		// --- Create the table that acts as the box container ---
		PdfPTable staticBoxTable = new PdfPTable(1);
		staticBoxTable.setWidthPercentage(100f);
		staticBoxTable.setSpacingBefore(5f);
		staticBoxTable.setSpacingAfter(5f);


		Paragraph staticParagraph = new Paragraph(textContent, textFont);
		staticParagraph.setAlignment(Element.ALIGN_JUSTIFIED);


		PdfPCell staticBoxCell = new PdfPCell(staticParagraph);
		staticBoxCell.setBorder(Rectangle.BOX);
		staticBoxCell.setBorderWidth(0.5f);
		staticBoxCell.setPadding(8f);

		staticBoxCell.setUseAscender(true);
		staticBoxCell.setUseDescender(true);

		// --- Add the cell to the table ---
		staticBoxTable.addCell(staticBoxCell);

		// --- Add the table (our box) to the document ---
		document.add(staticBoxTable);
	}

	class PageNumeration extends PdfPageEventHelper {
		PdfTemplate total;
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
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
				footerPhrase = new Phrase("Page No.:" + writer.getPageNumber(), fontTableHeading1);

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