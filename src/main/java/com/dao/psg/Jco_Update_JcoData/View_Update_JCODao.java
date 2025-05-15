package com.dao.psg.Jco_Update_JcoData;

import java.util.Date;
import java.util.List;

import com.models.psg.Jco_Update_JcoData.TB_ALLERGIC_TO_MEDICINE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_ATTACHMENT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CANTEEN_CARD_DETAILS1_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ADDRESS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_ARMY_COURSE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_AWARDSNMEDAL_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_BPET_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_CHILDREN_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_DISCIPLINE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FIRING_STANDARD_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FOREIGN_COUNTRY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_LANGUAGE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_PROMO_EXAM_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_CLASS_PAY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_PAY_GROUP_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_TRADE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_APPOINTMENT_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_RANK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_RELIGION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TA_STATUS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_TYPE_OF_POSTING_JCO;
import com.models.psg.Jco_Update_JcoData.TB_DESERTER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_IDENTITY_CARD_JCO;
import com.models.psg.Jco_Update_JcoData.TB_INTER_ARM_TRANSFER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NOK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_NON_EFFECTIVE_JCO;

public interface View_Update_JCODao {

	List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankData(int jco_id, Date modifiedDate);

	List<TB_CHANGE_NAME_JCO> getChangeOfNameData(int jco_id, Date modifiedDate);

	List<TB_NON_EFFECTIVE_JCO> getNon_effective(int jco_id, Date modifiedDate);

	List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> cda_acnt_no_GetData(int jco_id, Date modifiedDate);

	List<TB_CHANGE_RELIGION_JCO> religion_GetData(int jco_id, Date modifiedDate);

	List<TB_INTER_ARM_TRANSFER_JCO> getInterArm(int jco_id, Date modifiedDate);

	List<TB_CENSUS_AWARDSNMEDAL_JCO> getawardsNmedalData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_LANGUAGE_JCO> update_census_getlanguagedetailsData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_QUALIFICATION_JCO> update_census_getQualificationData(int jco_id, Date modifiedDate);

	List<TB_CHANGE_OF_APPOINTMENT_JCO> get_Appointment(int jco_id, Date modifiedDate);

	List<TB_CENSUS_FOREIGN_COUNTRY_JCO> getUPForeginCountryData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData(int jco_id, Date modifiedDate, int event);

	List<TB_NOK_JCO> nok_details_GetData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_BPET_JCO> getbpet_Data(int jco_id, Date modifiedDate);

	List<TB_IDENTITY_CARD_JCO> Ide_card_getData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_ADDRESS_JCO> address_details_GetData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> Battle_and_Physical_Casuality_GetData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_DISCIPLINE_JCO> get_Discipline(int jco_id, Date modifiedDate);

	List<TB_CENSUS_PROMO_EXAM_JCO> update_census_promo_examData(int jco_id, Date modifiedDate);

	List<TB_CENSUS_ARMY_COURSE_JCO> update_census_army_courseData(int jco_id, Date modifiedDate);

	List<TB_ALLERGIC_TO_MEDICINE_JCO> update_census_allergicData(int jco_id, Date modifiedDate);

	List<TB_MEDICAL_CATEGORY_JCO> getUpdatedmadicalData(int jco_id, Date modifiedDate);

	List<TB_DESERTER_JCO> get_Deserter(int jco_id, Date modifiedDate);

	List<TB_CANTEEN_CARD_DETAILS1_JCO> getCSD_detailsData(int jco_id, Date modifiedDate);
	
    List<TB_CHANGE_IN_TRADE_JCO> Trade_getData(int jco_id,Date modifiedDate);
	
	List<TB_CHANGE_IN_CLASS_PAY_JCO> Class_Pay_getData(int jco_id,Date modifiedDate);
	
	List<TB_CHANGE_IN_PAY_GROUP_JCO> Gorup_Pay_getData(int jco_id,Date modifiedDate);
	
	List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> Seniority_getData(int jco_id,Date modifiedDate);
	
	List<TB_ATTACHMENT_DETAILS_JCO> Attachment_getData(int jco_id,Date modifiedDate); 
 List<TB_CHANGE_TYPE_OF_POSTING_JCO> getChangeOfpostingviewData(int jco_id,Date modifiedDate);
 
 List<TB_CHANGE_TA_STATUS_JCO> getChangeOfTAStatusData(int jco_id, Date modifiedDate);

	Date getParentModifiedDate(int jco_id);

	List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> getUpdatedSpouseQualificationData(int jco_id, Date modifiedDate);
	
	public List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> old_Seniority_getData(int jco_id,Date modifiedDate);

}
