package com.dao.InspectionReports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.InspectionReports.TB_DETAIL_COURSES;
import com.model.InspectionReports.TB_INSP_CELL_PHONE_LAPSES;
import com.model.InspectionReports.TB_INSP_ESPIONAGE_LAPSES;
import com.model.InspectionReports.TB_INSP_FATAL_INCIDENTS;
import com.model.InspectionReports.TB_INSP_FFRS;
import com.model.InspectionReports.TB_INSP_FINANCIAL_GRANTS;
import com.model.InspectionReports.TB_INSP_FS_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_LOST_CDS_DVDS;
import com.model.InspectionReports.TB_INSP_LOST_ID_ECR;
import com.model.InspectionReports.TB_INSP_OUTSIDE_ATTACHMENTS;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_LOSS_MT;
import com.model.InspectionReports.TB_INSP_OUTSTANDING_RENT_ALLIED;
import com.model.InspectionReports.TB_INSP_PIO_CALL_LAPSES;
import com.model.InspectionReports.TB_INSP_REGT_FUNDS;
import com.model.InspectionReports.TB_INSP_RPT_CLASSIFICATION_RANGES;
import com.model.InspectionReports.TB_INSP_SECURITY_LAPSES;
import com.model.InspectionReports.TB_INSP_SOCIAL_MEDIA_LAPSES;
import com.model.InspectionReports.TB_INSP_STANDARDS_ACHIEVED;
import com.model.InspectionReports.TB_INSP_TD_PROCEEDED;
import com.model.InspectionReports.TB_MISO_INSP_COURSES_CAT_A_B;
import com.model.InspectionReports.TB_MISO_INSP_DEFI_EQUP_EFFI;
import com.model.InspectionReports.TB_MISO_INSP_ESTABLISHMENT;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_III_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_PHASE_IV_TBL;
import com.model.InspectionReports.TB_MISO_INSP_MAIN_TBL;
import com.model.InspectionReports.TB_MISO_INSP_SUMMARY_TECH_OTHER;
import com.model.InspectionReports.TB_MISO_INSP_TRAINING_AMMUNITION;
import com.model.InspectionReports.TB_MISO_INSP_UPGRADATION;


public interface InspectionReportsDao  {

	public List<Map<String, Object>> getEstablishmentData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getEquiment_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getAnimal_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getDeficiencies_of_Equipment2_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getDeficiencies_of_Equipment_url_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getWTResultData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getBPETResultData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getCategory_url_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getUp_Gradation_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> State_of_Sector_Stores_url_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getPromoExamData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getEducationStandardsData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getPpt_Result_Data(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getFinancialGrantsData(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getCoursesData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getStandardsAchievedData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getTDOfficersData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getSMLData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getPCLData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getELData(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getCPCLData(HttpSession session, HttpServletRequest request);

	public List<TB_MISO_INSP_UPGRADATION> getupGradationdataAction(String sus_no);
	public List<TB_MISO_INSP_COURSES_CAT_A_B> getCourseCatAandBAction(String sus_no);
	public List<TB_MISO_INSP_ESTABLISHMENT> getEstablishmentAttachedAction(String sus_no) ;


	ArrayList<ArrayList<String>> getCategory_url_Data_addmore(HttpSession session, HttpServletRequest request);

	ArrayList<ArrayList<String>> getUp_Gradation_Data_addmore(HttpSession session, HttpServletRequest request);
	public List<TB_MISO_INSP_DEFI_EQUP_EFFI> getInspectionDefiEqpEffiData(String sus_no);
	public List<TB_INSP_FINANCIAL_GRANTS> getFinancialGrantsAction(String sus_no);

	public List<TB_INSP_RPT_CLASSIFICATION_RANGES> getClassSaveAction(String sus_no);
	public List<TB_INSP_FFRS> getFfrsSaveAction(String sus_no);

	public List<Map<String, Object>> getequpAnnualMeterageData(String sus_no);


	public boolean equipmentAandBvehSaveAction(HttpSession session, HttpServletRequest request);
	public List<Map<String, Object>> getRegLanguageExamData(HttpSession session, HttpServletRequest request);
	public List<TB_MISO_INSP_TRAINING_AMMUNITION> gettrainningAmmunationData(String sus_no);
	public List<TB_MISO_INSP_SUMMARY_TECH_OTHER> getSummaryTechInspOtherData(String sus_no);
	public ArrayList<ArrayList<String>> summary_tech_insp_Data(HttpSession session, HttpServletRequest request);

	public List<TB_INSP_OUTSTANDING_RENT_ALLIED> getoutstandingData(String roleSusNo);

	public List<TB_INSP_OUTSTANDING_LOSS_MT> getoutstandingLossMTData(String roleSusNo);

	public List<TB_INSP_FATAL_INCIDENTS> getfatalIncidentData(String roleSusNo);

	public List<TB_INSP_SECURITY_LAPSES> getsequrityLapsesData(String roleSusNo);

	public List<TB_INSP_OUTSIDE_ATTACHMENTS> getoutsideAttachmentData(String roleSusNo);

	public List<TB_DETAIL_COURSES> getCourseUndertakenAction(String sus_no, HttpSession session);
	public List<TB_INSP_STANDARDS_ACHIEVED> getOtherCoursesAction(String sus_no, HttpSession session);
	public List<TB_INSP_TD_PROCEEDED> getTDOfficersAction(String sus_no, HttpSession session);
	public List<TB_INSP_SOCIAL_MEDIA_LAPSES> getSMLAction(String sus_no, HttpSession session);
	public List<TB_INSP_PIO_CALL_LAPSES> getPCLAction(String sus_no, HttpSession session);
	public List<TB_INSP_ESPIONAGE_LAPSES> getELAction(String sus_no, HttpSession session);
	public List<TB_INSP_CELL_PHONE_LAPSES> getCPCLAction(String sus_no, HttpSession session);
	public boolean wtResultOffrsJcoOrSaveAction(HttpSession session, HttpServletRequest request);
	public ArrayList<ArrayList<String>> getwtResultOtherData(HttpSession session, HttpServletRequest request);
	public List<TB_INSP_FS_SECURITY_LAPSES> getuntracebleData(String roleSusNo);

	public List<TB_INSP_LOST_CDS_DVDS> getlostCdDvdData(String roleSusNo);

	public List<TB_INSP_LOST_ID_ECR> getlostidEcrData(String roleSusNo);
	public List<Map<String, String>> stateOfSectorStoresGetData(HttpSession session, HttpServletRequest request);




	//PDF

	ArrayList<ArrayList<String>> getEstablishmentPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getEquipmentPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getAnnualMeterage(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getAnimalPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getEqptPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getEqptPdf2(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getSectorStoresPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getWtPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getCategoryPdf(HttpSession session, HttpServletRequest request);
	ArrayList<ArrayList<String>> getUpgradationPdf(HttpSession session, HttpServletRequest request);
	public List<TB_MISO_INSP_MAIN_TBL> getinsp_main_table_data(String sus_no, String currenYear);
	public List<TB_INSP_FS_SECURITY_LAPSES> getInspFsSecurityData(String sus_no);


	public Boolean checkDownload(String roleSusNo, String currentYear);


	public List<TB_INSP_REGT_FUNDS> getRegtFundsAction(String sus_no);


	public List<Map<String, Object>> getLandData(HttpSession session, HttpServletRequest request);

	public ArrayList<ArrayList<String>>   getDataBasedOnRoleSusNo(HttpSession session, HttpServletRequest request);
	public ArrayList<ArrayList<String>> getTableData(HttpSession session, String tableName);
	
	///-----------------------------------part 3--------------------------------------
	List<TB_MISO_INSP_MAIN_PHASE_III_TBL> getinsp_main_table_data_phaseiii(String sus_no, String currenYear);
	  public List<Map<String, Object>> getOverall_Str_and_Challengesdataurl(HttpSession session, HttpServletRequest request,String roleSusno);
	  
	  
//		PHASE 4

		public List<TB_MISO_INSP_MAIN_PHASE_IV_TBL> getinsp_main_table_data_4(String sus_no, String currenYear);

		public List<Map<String, Object>> getfitness_for_war_or_designated_role_url(HttpSession session,
				HttpServletRequest request);

		public List<Map<String, Object>> getfitness_for_war_or_designated_role_unit_url(HttpSession session,
				HttpServletRequest request);

		public List<Map<String, Object>> getfitness_for_war_or_designated_role_critical_url(HttpSession session,
				HttpServletRequest request);

		public Map<String, String> ufiList(HttpSession session);
		public Map<String, String> udiList(HttpSession session);
		public Map<String, String> ciiList(HttpSession session);

	
}

