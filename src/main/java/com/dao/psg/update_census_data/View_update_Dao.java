package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
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
import com.models.psg.update_census_data.TB_CHANGE_TNAI_NO;
import com.models.psg.update_census_data.TB_DESERTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;

public interface View_update_Dao {

	/////////////search ////
	public ArrayList<ArrayList<String>> Search_UpdateOffData(String unit_name,String unit_sus_no,String personnel_no,String status,String rank,
			String roleSusNo,String roleType,String name,String y_comm,String y_dob,String p_arm,String comm_status,String roleAccess,Boolean IsMns);
	
	
	///////////
	 List<TB_CHANGE_TA_STATUS> getChangeOfTAData(int census_id, BigInteger comm_id,Date modifiedDate);
	public List<Map<String, Object>> View_UpadteOfficerDataByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> Sh_UpadteOfficerDataByid(int id,BigInteger comm_id,int comm_status) ;
	public List<Map<String, Object>> Cop_UpadteOfficerDataByid(int id,BigInteger comm_id,int comm_status);
	public List<Map<String, Object>> TR_UpadteOfficerDataByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> Spouce_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> AWARD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> ARM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> PE_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> Field_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo);
	public List<Map<String, Object>> PTQ_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> AQ_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> ILan_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> FLan_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	
	public List<Map<String, Object>> F_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> B_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> FS_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> BA_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> SC_View_UpadteByid(int id,BigInteger comm_id,String roleSusNoint,int comm_status);
	
	
	public List<Map<String, Object>> ORM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> PM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> PS_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> NOK_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> NA_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> FM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	public List<Map<String, Object>> PDO_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status);
	public List<Map<String, Object>> AR_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	
	public List<Map<String, Object>> TENU_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status)  ;
	public List<Map<String, Object>> TENU_Total_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;
	public List<TB_CHANGE_TNAI_NO> getChangeOfTnaiNoData(int census_id, BigInteger comm_id);
	
	public Date getParentModifiedDate(int census_id, BigInteger comm_id);
	List<TB_CHANGE_OF_RANK> getChangeOfRankData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CHANGE_NAME> getChangeOfNameData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_NON_EFFECTIVE> getNon_effective(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_CHILDREN> getm_children_detailsData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CHANGE_RELIGION> religion_GetData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_PSG_CHANGE_OF_COMISSION> getcocData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_PSG_EXTENSION_OF_COMISSION> geteocData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_INTER_ARM_TRANSFER> getInterArm(int census_id, BigInteger comm_id,Date modifiedDate);
	
	List<TB_INTER_ARM_TRANSFER> getInterArm_old(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_SECONDMENT> get_Secondment(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CHANGE_OF_APPOINTMENT> get_Appointment(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData(int census_id, BigInteger comm_id,Date modifiedDate, int parseInt3);

	List<TB_CENSUS_NOK> nok_details_GetData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_BPET> getbpet_Data(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_ADDRESS> address_details_GetData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_DISCIPLINE> get_Discipline(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_PROMOTIONAL_EXAM> update_census_promo_examData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> update_census_allergicData(int census_id, BigInteger comm_id,Date modifiedDate);

	List<TB_CENSUS_MEDICAL_CATEGORY> getUpdatedmadicalData(int census_id, BigInteger comm_id,Date modifiedDate);
	
	List<TB_DESERTER> get_Deserter(int census_id, BigInteger comm_id,Date modifiedDate);
	
	List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_detailsData(int census_id, BigInteger comm_id,Date modifiedDate);//keval
	
	public ArrayList<ArrayList<String>> GetDataApprove_noneffective(BigInteger comm_id);//keval
	
	List<Map<String, Object>> Regimental_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo) ;
	public List<Map<String, Object>> CHILD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo) ;

	public List<Map<String, Object>> RD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) ;


	List<TB_CENSUS_SPOUSE_QUALIFICATION> getUpdatedSpouseQualificationData(BigInteger comm_id, Date modifiedDate);
}
