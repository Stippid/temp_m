package com.dao.psg.xml;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.models.psg.update_census_data.TB_CENSUS_BPET;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;
import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;
import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;
import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.models.psg.update_census_data.TB_DESERTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;

public interface ViewApproved_xmlDao {

	List<TB_CHANGE_OF_RANK> getChangeOfRankData(BigInteger comm_id, String modifiedDate);

	List<TB_CHANGE_NAME> getChangeOfNameData(BigInteger comm_id, String modifiedDate);

	List<TB_NON_EFFECTIVE> getNon_effective(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_CHILDREN> getm_children_detailsData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData(BigInteger comm_id, String modifiedDate);

	List<TB_CHANGE_RELIGION> religion_GetData(BigInteger comm_id, String modifiedDate);

	List<TB_PSG_CHANGE_OF_COMISSION> getcocData(BigInteger comm_id, String modifiedDate);

	List<TB_PSG_EXTENSION_OF_COMISSION> geteocData(BigInteger comm_id, String modifiedDate);

	List<TB_INTER_ARM_TRANSFER> getInterArm(BigInteger comm_id, String modifiedDate);
	
	List<TB_INTER_ARM_TRANSFER> getInterArm_old(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_SECONDMENT> get_Secondment(BigInteger comm_id, String modifiedDate);

	List<TB_CHANGE_OF_APPOINTMENT> get_Appointment(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData(int census_id, BigInteger comm_id,Date modifiedDate, int parseInt3);

	List<TB_CENSUS_NOK> nok_details_GetData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_BPET> getbpet_Data(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_ADDRESS> address_details_GetData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_DISCIPLINE> get_Discipline(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_PROMOTIONAL_EXAM> update_census_promo_examData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData(BigInteger comm_id, String modifiedDate);

	List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> update_census_allergicData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_MEDICAL_CATEGORY> getUpdatedmadicalData(BigInteger comm_id, String modifiedDate);
	
	List<TB_DESERTER> get_Deserter(BigInteger comm_id, String modifiedDate);
	
	List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_detailsData(BigInteger comm_id, String modifiedDate);//keval
	
	 List<TB_CHANGE_TA_STATUS> getChangeOfTAData(BigInteger comm_id, String modifiedDate);

	List<TB_CENSUS_SPOUSE_QUALIFICATION> getUpdatedSpouseQualificationData(BigInteger comm_id, String modifiedDate);

	List<TB_POSTING_IN_OUT> getposting_in_data1_xml(BigInteger comm_id, String modifiedDate);

	List<TB_CDA_ACC_NO> cda_GetData_xml(BigInteger comm_id, String modifiedDate);
	
	
	

}
