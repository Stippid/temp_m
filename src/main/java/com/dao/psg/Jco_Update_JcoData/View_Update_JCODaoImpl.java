package com.dao.psg.Jco_Update_JcoData;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
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
import com.persistance.util.HibernateUtil;

public class View_Update_JCODaoImpl implements View_Update_JCODao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Override
	public Date getParentModifiedDate(int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_JCO_OR_PARENT where status in (1,4,5) and id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list.get(0).getModified_date();
	}
	
	@Override
	public List<TB_CHANGE_OF_RANK_JCO> getChangeOfRankData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK_JCO where  status = '1' and jco_id=:jco_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK_JCO> list = (List<TB_CHANGE_OF_RANK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Override
	public List<TB_CHANGE_NAME_JCO> getChangeOfNameData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME_JCO where  status='1' and jco_id=:jco_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME_JCO> list = (List<TB_CHANGE_NAME_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Override
	public List<TB_CHANGE_TA_STATUS_JCO> getChangeOfTAStatusData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS_JCO where  status='1' and jco_id=:jco_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS_JCO> list = (List<TB_CHANGE_TA_STATUS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Override
	public List<TB_NON_EFFECTIVE_JCO> getNon_effective(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE_JCO where  status='1' and jco_id=:jco_id  and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE_JCO> list = (List<TB_NON_EFFECTIVE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@Override
	public List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CHILDREN_JCO where  status='1' and jco_id=:jco_id and  modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CHILDREN_JCO> list = (List<TB_CENSUS_CHILDREN_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> cda_acnt_no_GetData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO where  jco_id=:jco_id and status = '1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO> list = (List<TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CHANGE_RELIGION_JCO> religion_GetData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_RELIGION_JCO where  status='1' and jco_id=:jco_id and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_RELIGION_JCO> list = (List<TB_CHANGE_RELIGION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	

	@Override
	public List<TB_INTER_ARM_TRANSFER_JCO> getInterArm(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER_JCO where  status='1' and jco_id=:jco_id and modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")List<TB_INTER_ARM_TRANSFER_JCO> list = (List<TB_INTER_ARM_TRANSFER_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_CENSUS_AWARDSNMEDAL_JCO> getawardsNmedalData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL_JCO where  status='1' and jco_id=:jco_id and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL_JCO> list = (List<TB_CENSUS_AWARDSNMEDAL_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_CENSUS_LANGUAGE_JCO> update_census_getlanguagedetailsData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE_JCO where  jco_id=:jco_id and status=1 and modify_on=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_LANGUAGE_JCO> list = (List<TB_CENSUS_LANGUAGE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_QUALIFICATION_JCO> update_census_getQualificationData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION_JCO where  jco_id=:jco_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION_JCO> list = (List<TB_CENSUS_QUALIFICATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


	@Override
	public List<TB_CHANGE_OF_APPOINTMENT_JCO> get_Appointment(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT_JCO where  jco_id=:jco_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CHANGE_OF_APPOINTMENT_JCO> list = (List<TB_CHANGE_OF_APPOINTMENT_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@Override
	public List<TB_CENSUS_FOREIGN_COUNTRY_JCO> getUPForeginCountryData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY_JCO where  jco_id=:jco_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FOREIGN_COUNTRY_JCO> list = (List<TB_CENSUS_FOREIGN_COUNTRY_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FIRING_STANDARD_JCO> getfire_detailsData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD_JCO where  jco_id=:jco_id and status = '1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FIRING_STANDARD_JCO> list = (List<TB_CENSUS_FIRING_STANDARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FAMILY_MARRIED_JCO> update_getfamily_marriageData(int jco_id,Date modifiedDate, int event) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and modified_date=:modified_date ";
		
		if(event==2) {
			hqlUpdate+=" and status=1 and (marital_status=8 OR marital_status=13 OR marital_status=9) ";
		}
		else if(event==4) {
			hqlUpdate+=" and status=1 and (marital_status=8 OR marital_status=13 ) ";
		}
		else {
			hqlUpdate+=" and status=1 ";
		}
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_NOK_JCO> nok_details_GetData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NOK_JCO where  jco_id=:jco_id and  status = '1' and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_NOK_JCO> list = (List<TB_NOK_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_BPET_JCO> getbpet_Data(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = "from TB_CENSUS_BPET_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BPET_JCO> list = (List<TB_CENSUS_BPET_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_IDENTITY_CARD_JCO> Ide_card_getData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_IDENTITY_CARD_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_IDENTITY_CARD_JCO> list = (List<TB_IDENTITY_CARD_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_ADDRESS_JCO> address_details_GetData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		

		String hqlUpdate = " from TB_CENSUS_ADDRESS_JCO where  jco_id=:jco_id and  status = '1'  and modified_date=:modified_date";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")

		List<TB_CENSUS_ADDRESS_JCO> list = (List<TB_CENSUS_ADDRESS_JCO>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;
	}

	@Override
	public List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> Battle_and_Physical_Casuality_GetData(int jco_id,Date modifiedDate) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO where  jco_id=:jco_id and status=1  and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO> list = (List<TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	@Override
	public List<TB_CENSUS_DISCIPLINE_JCO> get_Discipline(int jco_id,Date modifiedDate) {
	

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE_JCO where  jco_id=:jco_id and status='1'  and modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE_JCO> list = (List<TB_CENSUS_DISCIPLINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@Override
	public List<TB_CENSUS_PROMO_EXAM_JCO> update_census_promo_examData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMO_EXAM_JCO where   jco_id=:jco_id and status='1'  and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMO_EXAM_JCO> list = (List<TB_CENSUS_PROMO_EXAM_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_ARMY_COURSE_JCO> update_census_army_courseData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE_JCO where   jco_id=:jco_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE_JCO> list = (List<TB_CENSUS_ARMY_COURSE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_ALLERGIC_TO_MEDICINE_JCO> update_census_allergicData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_ALLERGIC_TO_MEDICINE_JCO where  jco_id=:jco_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_ALLERGIC_TO_MEDICINE_JCO> list = (List<TB_ALLERGIC_TO_MEDICINE_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_MEDICAL_CATEGORY_JCO> getUpdatedmadicalData(int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_MEDICAL_CATEGORY_JCO where  jco_id=:jco_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_MEDICAL_CATEGORY_JCO> list = (List<TB_MEDICAL_CATEGORY_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	
	@Override
	public List<TB_DESERTER_JCO> get_Deserter(int jco_id,Date modifiedDate) {
			
		
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_DESERTER_JCO where  jco_id=:jco_id and status='1' and approved_date=:modified_date";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
			@SuppressWarnings("unchecked")
			List<TB_DESERTER_JCO> list = (List<TB_DESERTER_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			
			return list;
		}

	@Override
    public List<TB_CANTEEN_CARD_DETAILS1_JCO> getCSD_detailsData(int jco_id,Date modifiedDate) {//kevaldao
        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
        //String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where pr_no=:personnel_no  and  modified_date=:modified_date order by id";
        String hqlUpdate = " from TB_CANTEEN_CARD_DETAILS1_JCO where   jco_id=:jco_id and status='1' and modified_date=:modified_date  order by id";
        Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
//                        .setTimestamp("modified_date", modifiedDate);
        @SuppressWarnings("unchecked")
        List<TB_CANTEEN_CARD_DETAILS1_JCO> list = (List<TB_CANTEEN_CARD_DETAILS1_JCO>) query.list();
        tx.commit();
        sessionHQL.close();
        return list;
}
    
    @Override
   	public List<TB_CHANGE_IN_TRADE_JCO> Trade_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_CHANGE_IN_TRADE_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
   		@SuppressWarnings("unchecked")
   		List<TB_CHANGE_IN_TRADE_JCO> list = (List<TB_CHANGE_IN_TRADE_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	
   	@Override
   	public List<TB_CHANGE_IN_CLASS_PAY_JCO> Class_Pay_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_CHANGE_IN_CLASS_PAY_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
   		@SuppressWarnings("unchecked")
   		List<TB_CHANGE_IN_CLASS_PAY_JCO> list = (List<TB_CHANGE_IN_CLASS_PAY_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	
   	
   	@Override
   	public List<TB_CHANGE_IN_PAY_GROUP_JCO> Gorup_Pay_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_CHANGE_IN_PAY_GROUP_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
   		@SuppressWarnings("unchecked")
   		List<TB_CHANGE_IN_PAY_GROUP_JCO> list = (List<TB_CHANGE_IN_PAY_GROUP_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	
   	
   	@Override
   	public List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> Seniority_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
   		@SuppressWarnings("unchecked")
   		List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	
	@Override
   	public List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> old_Seniority_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where  jco_id=:jco_id and status = '2'  ";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
   		@SuppressWarnings("unchecked")
   		List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO> list = (List<TB_CHANGE_IN_DATE_OF_SENIORITY_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	
   	
	@Override
   	public List<TB_ATTACHMENT_DETAILS_JCO> Attachment_getData(int jco_id,Date modifiedDate) {
   		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = sessionHQL.beginTransaction();
   		String hqlUpdate = " from TB_ATTACHMENT_DETAILS_JCO where  jco_id=:jco_id and status = '1'  and modified_date=:modified_date";
   		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
   		@SuppressWarnings("unchecked")
   		List<TB_ATTACHMENT_DETAILS_JCO> list = (List<TB_ATTACHMENT_DETAILS_JCO>) query.list();
   		tx.commit();
   		sessionHQL.close();
   		return list;
   	}
   	@Override
	public List<TB_CHANGE_TYPE_OF_POSTING_JCO> getChangeOfpostingviewData(int jco_id,Date modifiedDate) {
	
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TYPE_OF_POSTING_JCO where  status='1' and jco_id=:jco_id  and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TYPE_OF_POSTING_JCO> list = (List<TB_CHANGE_TYPE_OF_POSTING_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Override
	public List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> getUpdatedSpouseQualificationData( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:jco_id and  status = '1' and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
