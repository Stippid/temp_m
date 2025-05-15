package com.dao.psg.xml;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
import com.persistance.util.HibernateUtil;

public class ViewApproved_xmlDaoImpl implements ViewApproved_xmlDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<TB_CHANGE_OF_RANK> getChangeOfRankData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK where  status = '1' and comm_id=:comm_id and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CHANGE_NAME> getChangeOfNameData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME where  status='1' and comm_id=:comm_id and   ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_NON_EFFECTIVE> getNon_effective(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE where  status='1' and comm_id=:comm_id  and   ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE> list = (List<TB_NON_EFFECTIVE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_CHILDREN> getm_children_detailsData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CHILDREN where status='1' and comm_id=:comm_id and   ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where  comm_id=:comm_id and status = '1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CHANGE_RELIGION> religion_GetData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_RELIGION where  status='1' and comm_id=:comm_id and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_RELIGION> list = (List<TB_CHANGE_RELIGION>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_PSG_CHANGE_OF_COMISSION> getcocData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where  comm_id=:comm_id and status='1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_PSG_EXTENSION_OF_COMISSION> geteocData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where comm_id=:comm_id and status='1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_INTER_ARM_TRANSFER> getInterArm(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status='1' and comm_id=:comm_id and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_INTER_ARM_TRANSFER> getInterArm_old(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status='2' and comm_id=:comm_id  order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
		@SuppressWarnings("unchecked")List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where  status='1' and comm_id=:comm_id and ltrim(TO_CHAR(modify_on ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE where  comm_id=:comm_id and status=1 and  ltrim(TO_CHAR(modify_on ,'DD-MM-YYYY'),'0') =:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where comm_id=:comm_id and status='1' and  ltrim(TO_CHAR(modify_on ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_SECONDMENT> get_Secondment(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SECONDMENT where  comm_id=:comm_id and status='1'  and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CHANGE_OF_APPOINTMENT> get_Appointment(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where  comm_id=:comm_id and status='1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where comm_id=:comm_id and status='1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where  comm_id=:comm_id and status = '1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData(int census_id, BigInteger comm_id,
			Date modifiedDate, int parseInt3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_CENSUS_NOK> nok_details_GetData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_NOK where  comm_id=:comm_id and  status = '1' and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_NOK> list = (List<TB_CENSUS_NOK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_BPET> getbpet_Data(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = "from TB_CENSUS_BPET where  comm_id=:comm_id and status = '1'  and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BPET> list = (List<TB_CENSUS_BPET>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where  comm_id=:comm_id and status = '1'  and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_ADDRESS> address_details_GetData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		

		String hqlUpdate = " from TB_CENSUS_ADDRESS where  comm_id=:comm_id and  status = '1'  and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;
	}

	@Override
	public List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData(BigInteger comm_id,
			String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where  comm_id=:comm_id and status=1  and    ltrim(TO_CHAR(modify_on ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_DISCIPLINE> get_Discipline(BigInteger comm_id, String modifiedDate) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where  comm_id=:comm_id and status='1'  and  ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	



	@Override
	public List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> update_census_allergicData(BigInteger comm_id, String modifiedDate) {
		return null;
	
	}

	@Override
	public List<TB_CENSUS_MEDICAL_CATEGORY> getUpdatedmadicalData(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_MEDICAL_CATEGORY where comm_id=:comm_id and status='1' and    ltrim(TO_CHAR(modify_on ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_MEDICAL_CATEGORY> list = (List<TB_CENSUS_MEDICAL_CATEGORY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_DESERTER> get_Deserter(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_DESERTER where  comm_id=:comm_id and status='1' and   ltrim(TO_CHAR(approved_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_detailsData(BigInteger comm_id, String modifiedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_CHANGE_TA_STATUS> getChangeOfTAData(BigInteger comm_id, String modifiedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_CENSUS_SPOUSE_QUALIFICATION> getUpdatedSpouseQualificationData(BigInteger comm_id,
			String modifiedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_CENSUS_PROMOTIONAL_EXAM> update_census_promo_examData(BigInteger comm_id, String modifiedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData(BigInteger comm_id, String modifiedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TB_POSTING_IN_OUT> getposting_in_data1_xml(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_POSTING_IN_OUT where  comm_id=:comm_id and status='1' and   ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_POSTING_IN_OUT> list = (List<TB_POSTING_IN_OUT>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CDA_ACC_NO> cda_GetData_xml(BigInteger comm_id, String modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CDA_ACC_NO where  comm_id=:comm_id and status='1' and   ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0') =:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setString("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CDA_ACC_NO> list = (List<TB_CDA_ACC_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

}
