package com.dao.psg.jco_cancellation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_JcoData.TB_ATTACHMENT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_ARMY_NO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_CLASS_PAY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_PAY_GROUP_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_TRADE_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_NAME_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_OF_RANK_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_RELIGION_JCO;
import com.models.psg.Jco_Update_JcoData.TB_INTER_ARM_TRANSFER_JCO;
import com.models.psg.Jco_Update_JcoData.TB_MEDICAL_CATEGORY_JCO;
import com.models.psg.Master.TB_BLOOD_GROUP;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

public class Approve_ViewHistory_updateJcoDataDaoImpl implements Approve_ViewHistory_updateJcoDataDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public String approveHisChangeOfRank(int jco_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
		// TODO Auto-generated method stub
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String msg = "0";
		int app_id=0;
		Date date_of_attestation=null;
		boolean change_army_no=false;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int army_id=0;
					if(!Lobj.get(i).get("army_id").toString().equals(""))
					army_id=Integer.parseInt(Lobj.get(i).get("army_id").toString());
				
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
					if(!Lobj.get(i).get("date_of_attestation").toString().equals(""))
						date_of_attestation=dateFormat.parse(Lobj.get(i).get("date_of_attestation").toString().substring(0,10));
				
				}
				String hql_n = "update TB_CHANGE_OF_RANK_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				if(army_id>0) {
				String hql_n_a = "update TB_CHANGE_ARMY_NO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
						+ " where  id=:id";
				Query query_n_a = sessionhql.createQuery(hql_n_a).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", army_id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n_a.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				if(status==1) {
				change_army_no=true;
				}
				}
				

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_OF_RANK_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					
					TB_CHANGE_OF_RANK_JCO rank_obj = (TB_CHANGE_OF_RANK_JCO) sessionhql.get(TB_CHANGE_OF_RANK_JCO.class, chang_id);
					rank_obj.setModified_by(username);
					rank_obj.setModified_date(mod_date);
					rank_obj.setStatus(1);
					sessionhql.update(rank_obj);
					sessionhql.flush();
					sessionhql.clear();
				
					String hqlUpdate1="select id from TB_CHANGE_ARMY_NO where jco_id=:jco_id and rank_id=:rank_id and status=2 order by id desc ";
					Query query1 = sessionhql.createQuery(hqlUpdate1).setInteger("jco_id", jco_id).setInteger("rank_id", rank_obj.getId()).setMaxResults(1);
					Integer c1 = (Integer)  query1.uniqueResult();
					
					String army_no="";
					if(c1!=null && c1>0) {
						int army_id=c1.intValue();
						TB_CHANGE_ARMY_NO army_obj = (TB_CHANGE_ARMY_NO) sessionhql.get(TB_CHANGE_ARMY_NO.class, army_id);
						army_obj.setModified_by(username);
						army_obj.setModified_date(mod_date);
						army_obj.setStatus(1);
						sessionhql.update(army_obj);						
						army_no=army_obj.getArmy_no();
						sessionhql.flush();
						sessionhql.clear();
					}
					TB_CENSUS_JCO_OR_PARENT p_obj = (TB_CENSUS_JCO_OR_PARENT) sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class, jco_id);
					p_obj.setModified_by(username);
					p_obj.setModified_date(mod_date);
					p_obj.setCategory(rank_obj.getCategory());
					p_obj.setRank(rank_obj.getRank());
					//p_obj.setEnroll_dt(rank_obj.getDate_of_rank());
					if(date_of_attestation!=null) {
						p_obj.setDate_of_attestation(rank_obj.getDate_of_attestation());
					}
					if(!army_no.equals("")) {
						p_obj.setArmy_no(army_no);
					}
					
					sessionhql.update(p_obj);
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
		
	}
	@Override
	public String approveHisChangeOfName(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_NAME_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_NAME_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					TB_CHANGE_NAME_JCO name_obj = (TB_CHANGE_NAME_JCO) sessionhql.get(TB_CHANGE_NAME_JCO.class, chang_id);
					name_obj.setModified_by(username);
					name_obj.setModified_date(mod_date);
					name_obj.setStatus(1);
					sessionhql.update(name_obj);
					sessionhql.flush();
					sessionhql.clear();
					TB_CENSUS_JCO_OR_PARENT p_obj = (TB_CENSUS_JCO_OR_PARENT) sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class, jco_id);
				
					p_obj.setModified_by(username);
					p_obj.setModified_date(mod_date);
					p_obj.setFirst_name(name_obj.getFirst_name());
					p_obj.setMiddle_name(name_obj.getMiddle_name());
					p_obj.setLast_name(name_obj.getLast_name());
					p_obj.setFull_name(name_obj.getFull_name());
					sessionhql.update(p_obj);
					sessionhql.flush();
					sessionhql.clear();
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
		
	}
	public String approveHisChangeOfAppointment(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_OF_APPOINTMENT_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_OF_APPOINTMENT_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CHANGE_OF_APPOINTMENT_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
			
		
	}
	@Override
	public String approveHisIdentity_Card(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_IDENTITY_CARD_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_IDENTITY_CARD_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_IDENTITY_CARD_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
		
		
	}
	@Override
	public String approveHisChangeOfReligion(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_RELIGION_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_RELIGION_JCO where jco_id=:jco_id and status in (1,2) order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					TB_CHANGE_RELIGION_JCO rel_obj = (TB_CHANGE_RELIGION_JCO) sessionhql.get(TB_CHANGE_RELIGION_JCO.class, chang_id);
					rel_obj.setModified_by(username);
					rel_obj.setModified_date(mod_date);
					rel_obj.setStatus(1);
					sessionhql.update(rel_obj);
					sessionhql.flush();
					sessionhql.clear();
					TB_CENSUS_JCO_OR_PARENT p_obj = (TB_CENSUS_JCO_OR_PARENT) sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class, jco_id);					
					p_obj.setModified_by(username);
					p_obj.setModified_date(mod_date);					
					p_obj.setReligion(rel_obj.getReligion());
					sessionhql.update(p_obj);
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
		
		
			
	}
	@Override
	public String approveHisfamily_married(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		int app_id=0;
		int marital_status=0;
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
					marital_status=Integer.parseInt(Lobj.get(i).get("marital_status").toString());
				}
				String hql_n = "update TB_CENSUS_FAMILY_MARRIED_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
			}
		
			
			if(app_id!=0) {

				
				if(marital_status!=0) {
					
					
					
					TB_CENSUS_JCO_OR_PARENT  p_obj=  (TB_CENSUS_JCO_OR_PARENT) sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class, jco_id);
					String hqlUpdate1="select id from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and status = 2 order by id desc ";
					Query query1 = sessionhql.createQuery(hqlUpdate1).setInteger("jco_id", jco_id).setMaxResults(1);
					Integer c1 = (Integer)  query1.uniqueResult();
						if(c1!=null && c1>0) {
							
							
							
							int chang_id=c1.intValue();
							
							TB_CENSUS_FAMILY_MARRIED_JCO  marr_obj=  (TB_CENSUS_FAMILY_MARRIED_JCO) sessionhql.get(TB_CENSUS_FAMILY_MARRIED_JCO.class, chang_id);
							marr_obj.setModified_by(username);
							marr_obj.setModified_date(mod_date);
							marr_obj.setStatus(1);
							
							if(marr_obj.getMarital_status()==13) {
								marr_obj.setSeparated_to_dt(null);
							}
							sessionhql.update(marr_obj);					
							sessionhql.flush();
							sessionhql.clear();
							
							p_obj.setMarital_status(marr_obj.getMarital_status());
							sessionhql.update(p_obj);					
							sessionhql.flush();
							sessionhql.clear();
						
						}
						else {
							p_obj.setMarital_status(10);
							sessionhql.update(p_obj);					
							sessionhql.flush();
							sessionhql.clear();
						}
				
				
					}
				
			}
			
			tx.commit();
			
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisSpouseQualification(int jco_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
		
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			String msg = "0";
			int app_id=0;
			try {
				
				for(int i=0;i<Lobj.size();i++) {
					int id=Integer.parseInt(Lobj.get(i).get("id").toString());
					int status=Integer.parseInt(Lobj.get(i).get("status").toString());
					if(status==1) {
						app_id=id;					
					}
					String hql_n = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
							.setInteger("id", id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
					
				}
				
				
				if(app_id!=0) {

					String hqlUpdate="select id from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:jco_id and status=2 order by id desc ";
					Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
					Integer c = (Integer)  query.uniqueResult();
					
					if(c!=null && c>0) {
						int chang_id=c.intValue();
						TB_CENSUS_SPOUSE_QUALIFICATION_JCO rel_obj = (TB_CENSUS_SPOUSE_QUALIFICATION_JCO) sessionhql.get(TB_CENSUS_SPOUSE_QUALIFICATION_JCO.class, chang_id);
						rel_obj.setModified_by(username);
						rel_obj.setModified_date(mod_date);
						rel_obj.setStatus(1);
						sessionhql.update(rel_obj);
						sessionhql.flush();
						sessionhql.clear();					
					
					}
					
				}
				
				tx.commit();
				}catch (Exception e) {
					// TODO: handle exception
					tx.rollback();
					return "0";
				}
				return msg;
				
	}
	@Override
	public String approveHisChild(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_CHILDREN_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
		
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisNokAddress(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_NOK_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_NOK_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_NOK_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
			
		
	}
	@Override
	public String approveHisAddress(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CENSUS_ADDRESS_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_ADDRESS_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					String hql_n = "update TB_CENSUS_ADDRESS_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
			
		
			
		
	}
	@Override
	public String approveHisContactDetails(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_CONTACT_CDA_ACCOUNT_DETAILS_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
			
		
	}
	@Override
	public String approveHisLanguageDetails(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
			
				String hql_n = "update TB_CENSUS_LANGUAGE_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			tx.commit();
			
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
//	@Override
//	public String approveHisForeignLang(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
//		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = sessionhql.beginTransaction();
//		String msg = "0";
//		
//		try {
//			
//			for(int i=0;i<Lobj.size();i++) {
//				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
//			
//				String hql_n = "update TB_CENSUS_LANGUAGE set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status "
//						+ " where  id=:id";
//				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
//						.setInteger("id", id).setString("modified_by", username)
//						.setTimestamp("modified_date", mod_date);
//				msg = query_n.executeUpdate() > 0 ? "1" : "0";
//				sessionhql.flush();
//				sessionhql.clear();
//				
//				
//			}
//			tx.commit();
//			
//			}catch (Exception e) {
//				// TODO: handle exception
//				tx.rollback();
//				return "0";
//			}
//			return msg;
//	}
	@Override
	public String approveHisQualification(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_QUALIFICATION_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisPromotionalExam(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_PROMO_EXAM_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisArmyCourse(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
			
				String hql_n = "update TB_CENSUS_ARMY_COURSE_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisBpetDetails(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_BPET_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisFiringDetails(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_FIRING_STANDARD_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisAllergy(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_ALLERGIC_TO_MEDICINE_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisMedicalCategory(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int k=0;k<Lobj.size();k++) { 
			String[] inArr=Lobj.get(k).get("s_id").toString().split("I");
				  TB_MEDICAL_CATEGORY_JCO med_obj = (TB_MEDICAL_CATEGORY_JCO) sessionhql.get(TB_MEDICAL_CATEGORY_JCO.class, Integer.parseInt(inArr[0]));
				  
						String hql_n = "update TB_MEDICAL_CATEGORY_JCO set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by,status=:status "
						+ " where  date_of_authority=:date_of_authority";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setTimestamp("date_of_authority",med_obj.getDate_of_authority()).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				String hqlUpdate = "delete from TB_MEDICAL_CATEGORY_HISTORY_JCO where date_of_authority=:date_of_authority";
				int app = sessionhql.createQuery(hqlUpdate).setTimestamp("date_of_authority",med_obj.getDate_of_authority()).executeUpdate();
				
			}
			
			String hqlUpdate="select id from TB_MEDICAL_CATEGORY_JCO where jco_id=:jco_id and status in (1,2) order by id desc ";
			Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
			Integer c = (Integer)  query.uniqueResult();
			
			if(c!=null && c>0) {
				int chang_id=c.intValue();
				 TB_MEDICAL_CATEGORY_JCO med_obj = (TB_MEDICAL_CATEGORY_JCO) sessionhql.get(TB_MEDICAL_CATEGORY_JCO.class, chang_id);
				 String hql_n = "update TB_MEDICAL_CATEGORY_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status "
							+ " where  date_of_authority=:date_of_authority and jco_id=:jco_id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1).setInteger("jco_id", jco_id)
							.setTimestamp("date_of_authority",med_obj.getDate_of_authority()).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					 String hql_n1 = "update TB_MEDICAL_CATEGORY_HISTORY_JCO set status=:status "
								+ " where  date_of_authority=:date_of_authority and jco_id=:jco_id";
						Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", 1).setInteger("jco_id", jco_id)
								.setTimestamp("date_of_authority",med_obj.getDate_of_authority());
						msg = query_n1.executeUpdate() > 0 ? "1" : "0";
						
					
				sessionhql.flush();
				sessionhql.clear();
				
			
			}
			
			
			
			  tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return "0";
		}
		return msg;
	}

	@Override
	public String approveHisForeignCountry(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			String msg = "0";
			
			try {
				
				for(int i=0;i<Lobj.size();i++) {
					int id=Integer.parseInt(Lobj.get(i).get("id").toString());
					
					String hql_n = "update TB_CENSUS_FOREIGN_COUNTRY_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
							.setInteger("id", id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
					
				}
			
				
				tx.commit();
				}catch (Exception e) {
					// TODO: handle exception
					tx.rollback();
					return "0";
				}
				return msg;
	}
	@Override
	public String approveHisAwardAndMedal(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_AWARDSNMEDAL_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status, cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisBattle_physical_casuality(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisDiscipline(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_DISCIPLINE_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisInter_arm_service_transfer(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_INTER_ARM_TRANSFER_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_INTER_ARM_TRANSFER_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					TB_INTER_ARM_TRANSFER_JCO updateid = (TB_INTER_ARM_TRANSFER_JCO) sessionhql.get(TB_INTER_ARM_TRANSFER_JCO.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
					if(updateid.getParent_arm_service()!=null && !updateid.getParent_arm_service().equals("0")) {
						String hql_c = "update TB_CENSUS_JCO_OR_PARENT set arm_service=:arm_service,regiment=:regiment,record_office_sus=:record_office_sus,record_office=:record_office where id=:jco_id ";

						Query query_c = sessionhql.createQuery(hql_c).setInteger("jco_id", jco_id)
								.setString("arm_service",updateid.getParent_arm_service()).setString("regiment", updateid.getRegt())
								.setString("record_office_sus", updateid.getRecord_office_sus()).setString("record_office", updateid.getRecord_office_unit());
								
						msg = query_c.executeUpdate() > 0 ? "1" : "0";
					}
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisNon_effective(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_NON_EFFECTIVE_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				TB_CENSUS_JCO_OR_PARENT cl=(TB_CENSUS_JCO_OR_PARENT)sessionhql.get(TB_CENSUS_JCO_OR_PARENT.class,  jco_id);
				String hqlUpdate3="select id from TB_POSTING_IN_OUT_JCO where jco_id=:jco_id and status=1 and to_sus_no=:to_sus_no order by id desc ";
				Query query3 = sessionhql.createQuery(hqlUpdate3).setInteger("jco_id", cl.getId()).setString("to_sus_no", cl.getUnit_sus_no()).setMaxResults(1);
				Integer c = (Integer)  query3.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					String hql2 = "update TB_POSTING_IN_OUT_JCO set modified_by=:modified_by,modified_date=:modified_date,tenure_date=:tenure_date "
							+ " where id=:id ";
				
					Query query2 = sessionhql.createQuery(hql2).setString("modified_by", username)
							.setTimestamp("modified_date",mod_date).setTimestamp("tenure_date",null )
					.setInteger("id", chang_id);
					msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
					sessionhql.flush();
					sessionhql.clear();
				}
				
				String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"
						+ "status=:status where id=:id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date",mod_date).setInteger("status", 1).setInteger("id", jco_id);

				msg = query.executeUpdate() > 0 ? "1" : "0";
				
			}
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisDeserter(int jco_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_DESERTER_JCO set approved_date=:modified_date ,approved_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

//				String hqlUpdate="select id from TB_DESERTER where jco_id=:jco_id and status=2 order by id desc ";
//				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
//				Integer c = (Integer)  query.uniqueResult();

//				if(c!=null && c>0) {
//					int chang_id=c.intValue();

//					String hql_n = "update TB_DESERTER set approved_date=:modified_date ,approved_by=:modified_by ,status=:status "
//							+ " where  id=:id";
//					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
//							.setInteger("id", chang_id).setString("modified_by", username)
//							.setTimestamp("modified_date", mod_date);
//					msg = query_n.executeUpdate() > 0 ? "1" : "0";
//					sessionhql.flush();
//					sessionhql.clear();
//			}
				String hql = "update TB_CENSUS_JCO_OR_PARENT set modified_by=:modified_by,modified_date=:modified_date,"
						+ "status=:status where id=:id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date",mod_date).setInteger("status", 1).setInteger("id", jco_id);

				msg = query.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
					
				
				
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisCSD(int parseInt, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CANTEEN_CARD_DETAILS1_JCO  set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
		
			
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	
	
	@Override
	public String approveHisTrade(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_IN_TRADE_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_IN_TRADE_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
			
					TB_CHANGE_IN_TRADE_JCO updateid = (TB_CHANGE_IN_TRADE_JCO) sessionhql.get(TB_CHANGE_IN_TRADE_JCO.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
				
						String hql_c = "update TB_CENSUS_JCO_OR_PARENT set trade=:trade where id=:jco_id ";

						Query query_c = sessionhql.createQuery(hql_c).setInteger("jco_id", jco_id)
								.setInteger("trade",updateid.getTrade());
								
								
						msg = query_c.executeUpdate() > 0 ? "1" : "0";
					
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	@Override
	public String approveHisClassPay(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_IN_CLASS_PAY_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_IN_CLASS_PAY_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					TB_CHANGE_IN_CLASS_PAY_JCO updateid = (TB_CHANGE_IN_CLASS_PAY_JCO) sessionhql.get(TB_CHANGE_IN_CLASS_PAY_JCO.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
				
						String hql_c = "update TB_CENSUS_JCO_OR_PARENT set class_pay=:class_pay where id=:jco_id ";

						Query query_c = sessionhql.createQuery(hql_c).setInteger("jco_id", jco_id)
								.setInteger("class_pay",updateid.getCla_class());
								
								
						msg = query_c.executeUpdate() > 0 ? "1" : "0";
					
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	
	@Override
	public String approveHisPayGroup(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_IN_PAY_GROUP_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_IN_PAY_GROUP_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					TB_CHANGE_IN_PAY_GROUP_JCO updateid = (TB_CHANGE_IN_PAY_GROUP_JCO) sessionhql.get(TB_CHANGE_IN_PAY_GROUP_JCO.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
				
						String hql_c = "update TB_CENSUS_JCO_OR_PARENT set pay_group=:pay_group where id=:jco_id ";

						Query query_c = sessionhql.createQuery(hql_c).setInteger("jco_id", jco_id)
								.setInteger("pay_group",updateid.getGp_group());
								
								
						msg = query_c.executeUpdate() > 0 ? "1" : "0";
					
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	@Override
	public String approveHisSeniority(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_IN_DATE_OF_SENIORITY_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_IN_DATE_OF_SENIORITY_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					TB_CHANGE_IN_DATE_OF_SENIORITY_JCO updateid = (TB_CHANGE_IN_DATE_OF_SENIORITY_JCO) sessionhql.get(TB_CHANGE_IN_DATE_OF_SENIORITY_JCO.class, chang_id);
					updateid.setModified_by(username);
							updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
				
						String hql_c = "update TB_CENSUS_JCO_OR_PARENT set date_of_seniority=:date_of_seniority where id=:jco_id ";

						Query query_c = sessionhql.createQuery(hql_c).setInteger("jco_id", jco_id)
								.setTimestamp("date_of_seniority",updateid.getDate_of_seniority());
								
								
						msg = query_c.executeUpdate() > 0 ? "1" : "0";
					
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	@Override
	public String approveHisAttachmentDetails(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_ATTACHMENT_DETAILS_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_ATTACHMENT_DETAILS_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					TB_ATTACHMENT_DETAILS_JCO updateid = (TB_ATTACHMENT_DETAILS_JCO) sessionhql.get(TB_ATTACHMENT_DETAILS_JCO.class, chang_id);
					updateid.setModified_by(username);
							updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();

				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	//////////////////////pki
	public String approveHisChangeOfposting(int jco_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_TYPE_OF_POSTING_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_TYPE_OF_POSTING_JCO where jco_id=:jco_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					String hql_n = "update TB_CHANGE_TYPE_OF_POSTING_JCO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
				
				}
				
			}
			
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
			
		
	}
}
