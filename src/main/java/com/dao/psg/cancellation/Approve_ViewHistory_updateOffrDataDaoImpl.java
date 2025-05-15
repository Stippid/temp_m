package com.dao.psg.cancellation;

import java.math.BigInteger;
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

import com.models.psg.Master.TB_BLOOD_GROUP;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

public class Approve_ViewHistory_updateOffrDataDaoImpl implements Approve_ViewHistory_updateOffrDataDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public String approveHisChangeOfRank(BigInteger comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
		// TODO Auto-generated method stub
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
				String hql_n = "update TB_CHANGE_OF_RANK set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_OF_RANK where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				

					TB_CHANGE_OF_RANK rank_obj = (TB_CHANGE_OF_RANK) sessionhql.get(TB_CHANGE_OF_RANK.class, chang_id);
					rank_obj.setModified_by(username);
					rank_obj.setModified_date(mod_date);
					rank_obj.setStatus(1);
					sessionhql.update(rank_obj);
					sessionhql.flush();
					sessionhql.clear();
					TB_TRANS_PROPOSED_COMM_LETTER p_obj = (TB_TRANS_PROPOSED_COMM_LETTER) sessionhql.get(TB_TRANS_PROPOSED_COMM_LETTER.class, comm_id);
					p_obj.setModified_by(username);
					p_obj.setModified_date(mod_date);				
					p_obj.setRank(rank_obj.getRank());
					p_obj.setDate_of_rank(rank_obj.getDate_of_rank());
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
	@Override
	public String approveHisChangeOfName(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
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
				String hql_n = "update TB_CHANGE_NAME set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_NAME where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();	
					TB_CHANGE_NAME name_obj = (TB_CHANGE_NAME) sessionhql.get(TB_CHANGE_NAME.class, chang_id);
					name_obj.setModified_by(username);
					name_obj.setModified_date(mod_date);
					name_obj.setStatus(1);
					sessionhql.update(name_obj);
					sessionhql.flush();
					sessionhql.clear();
					TB_TRANS_PROPOSED_COMM_LETTER p_obj = (TB_TRANS_PROPOSED_COMM_LETTER) sessionhql.get(TB_TRANS_PROPOSED_COMM_LETTER.class, comm_id);
					p_obj.setModified_by(username);
					p_obj.setModified_date(mod_date);
					p_obj.setName(name_obj.getName());				
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
	@Override
	public String approveHisChangeOfAppointment(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
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
				String hql_n = "update TB_CHANGE_OF_APPOINTMENT set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_OF_APPOINTMENT where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					String hql_n = "update TB_CHANGE_OF_APPOINTMENT set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisIdentity_Card(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
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
				String hql_n = "update TB_CENSUS_IDENTITY_CARD set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_IDENTITY_CARD where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_IDENTITY_CARD set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisChangeOfReligion(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		int census_id=0;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				census_id=Integer.parseInt(Lobj.get(i).get("census_id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_CHANGE_RELIGION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CHANGE_RELIGION where comm_id=:comm_id and status in (1,2) order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					TB_CHANGE_RELIGION rel_obj = (TB_CHANGE_RELIGION) sessionhql.get(TB_CHANGE_RELIGION.class, chang_id);
					rel_obj.setModified_by(username);
					rel_obj.setModified_date(mod_date);
					rel_obj.setStatus(1);
					sessionhql.update(rel_obj);
					sessionhql.flush();
					sessionhql.clear();
					TB_CENSUS_DETAIL_Parent p_obj = (TB_CENSUS_DETAIL_Parent) sessionhql.get(TB_CENSUS_DETAIL_Parent.class, census_id);					
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
	public String approveHisfamily_married(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		int app_id=0;
		int marital_status=0;
		int cen_id=0;
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				 cen_id=Integer.parseInt(Lobj.get(i).get("cen_id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
					marital_status=Integer.parseInt(Lobj.get(i).get("marital_status").toString());
				}
				String hql_n = "update TB_CENSUS_FAMILY_MRG set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
					
					
					
					TB_CENSUS_DETAIL_Parent  p_obj=  (TB_CENSUS_DETAIL_Parent) sessionhql.get(TB_CENSUS_DETAIL_Parent.class, cen_id);
					String hqlUpdate1="select id from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status = 2 order by marriage_date desc ";
					Query query1 = sessionhql.createQuery(hqlUpdate1).setBigInteger("comm_id", comm_id).setMaxResults(1);
					Integer c1 = (Integer)  query1.uniqueResult();
						if(c1!=null && c1>0) {
							
							
							
							int chang_id=c1.intValue();
							
							TB_CENSUS_FAMILY_MRG  marr_obj=  (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class, chang_id);
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
	public String approveHisSpouseQualification(BigInteger comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
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
				String hql_n = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_SPOUSE_QUALIFICATION where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					TB_CENSUS_SPOUSE_QUALIFICATION rel_obj = (TB_CENSUS_SPOUSE_QUALIFICATION) sessionhql.get(TB_CENSUS_SPOUSE_QUALIFICATION.class, chang_id);
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
	public String approveHisChild(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_CHILDREN set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisNokAddress(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		
		
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
				String hql_n = "update TB_CENSUS_NOK set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_NOK where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_NOK set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisAddress(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		
		
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
				String hql_n = "update TB_CENSUS_ADDRESS set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_ADDRESS where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_ADDRESS set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisContactDetails(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
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
				String hql_n = "update TB_CENSUS_CDA_ACCOUNT_NO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_CDA_ACCOUNT_NO where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_CDA_ACCOUNT_NO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisLanguageDetails(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
			
				String hql_n = "update TB_CENSUS_LANGUAGE set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
//	public String approveHisForeignLang(int comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
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
	public String approveHisQualification(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_QUALIFICATION set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisPromotionalExam(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_PROMOTIONAL_EXAM set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisArmyCourse(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
			
				String hql_n = "update TB_CENSUS_ARMY_COURSE set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisBpetDetails(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_BPET set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisFiringDetails(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_FIRING_STANDARD set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisAllergy(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_PSG_CENSUS_ALLERGICTOMEDICINE set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisMedicalCategory(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int k=0;k<Lobj.size();k++) {
				String[] inArr1=Lobj.get(k).get("s_id").toString().split("I");
				TB_CENSUS_MEDICAL_CATEGORY med_obj = (TB_CENSUS_MEDICAL_CATEGORY) sessionhql.get(TB_CENSUS_MEDICAL_CATEGORY.class, inArr1[0]);
			String id=Lobj.get(k).get("s_id").toString()+","+Lobj.get(k).get("h_id").toString()+","+Lobj.get(k).get("a_id").toString()+","+Lobj.get(k).get("p_id").toString()+","+Lobj.get(k).get("e_id").toString();
			id+=","+Lobj.get(k).get("c_c_id").toString()+","+Lobj.get(k).get("c_o_id").toString()+","+Lobj.get(k).get("c_p_id").toString()+","+Lobj.get(k).get("c_e_id").toString();
		
			String[] outArr=id.split(",");
			
			  for(int i=0;i<outArr.length;i++) {
				  String[] inArr=outArr[i].split("I");
				  for(int j=0;j<inArr.length;j++) {
						String hql_n = "update TB_CENSUS_MEDICAL_CATEGORY set modify_on=:modified_date ,modify_by=:modified_by , cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by,status=:status "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id",Integer.parseInt( inArr[j])).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";

				  }
			  }
			  String hqlUpdate = "delete from TB_MEDICAL_CATEGORY_HISTORY where date_of_authority=:date_of_authority";
				int app = sessionhql.createQuery(hqlUpdate).setTimestamp("date_of_authority",med_obj.getDate_of_authority()).executeUpdate();
				
			}
			
			String hqlUpdate="select id from TB_CENSUS_MEDICAL_CATEGORY where comm_id=:comm_id and status in (1,2) order by id desc ";
			Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
			Integer c = (Integer)  query.uniqueResult();
			
			if(c!=null && c>0) {
				int chang_id=c.intValue();
				TB_CENSUS_MEDICAL_CATEGORY med_obj = (TB_CENSUS_MEDICAL_CATEGORY) sessionhql.get(TB_CENSUS_MEDICAL_CATEGORY.class, chang_id);
				 String hql_n = "update TB_CENSUS_MEDICAL_CATEGORY set modify_on=:modified_date ,modify_by=:modified_by ,status=:status "
							+ " where  date_of_authority=:date_of_authority and comm_id=:comm_id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setTimestamp("date_of_authority",med_obj.getDate_of_authority()).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date).setBigInteger("comm_id", comm_id);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					 String hql_n1 = "update TB_MEDICAL_CATEGORY_HISTORY set status=:status "
								+ " where  date_of_authority=:date_of_authority and comm_id=:comm_id";
						Query query_n1 = sessionhql.createQuery(hql_n1).setInteger("status", 1)
								.setTimestamp("date_of_authority",med_obj.getDate_of_authority()).setBigInteger("comm_id", comm_id);
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
	public String approveHisForeignCountry(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			String msg = "0";
			
			try {
				
				for(int i=0;i<Lobj.size();i++) {
					int id=Integer.parseInt(Lobj.get(i).get("id").toString());
					
					String hql_n = "update TB_CENSUS_FOREIGN_COUNTRY set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisAwardAndMedal(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_AWARDSNMEDAL set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisBattle_physical_casuality(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_BATT_PHY_CASUALITY set modify_on=:modified_date ,modify_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisDiscipline(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_CENSUS_DISCIPLINE set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	public String approveHisSsc_to_permt_commission(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TB_PSG_CHANGE_OF_COMISSION updateid=null;
		TB_PSG_CHANGE_OF_SENIORITY senoupdateid=null;
		String msg = "0";
		int app_id=0;
		String personal_no=null;
		Transaction tx = sessionhql.beginTransaction();
		
		try {
			for(int i=0;i<Lobj.size();i++) {
				
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String date_of_seniority="";
				if(Lobj.get(i).get("date_of_seniority")!=null)
					date_of_seniority=Lobj.get(i).get("date_of_seniority").toString();
				
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				
				Date dos=null;
				if(!date_of_seniority.equals("")) {
					try {
						dos=df.parse(date_of_seniority);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_PSG_CHANGE_OF_COMISSION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				if(dos!=null) {
					String hqlUpdate_s="select id from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and date_of_seniority=:date_of_seniority order by id desc ";
					Query query_s = sessionhql.createQuery(hqlUpdate_s).setBigInteger("comm_id", comm_id).setTimestamp("date_of_seniority", dos).setMaxResults(1);
					Integer c = (Integer)  query_s.uniqueResult();
				
					if(c!=null && c>0) {
						int chang_id=c.intValue();
					String hql_su = "update TB_PSG_CHANGE_OF_SENIORITY set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
							+ " where  id=:id";
					Query query_su = sessionhql.createQuery(hql_su).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_su.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
					
					String hqlUpdate_s1="select id from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status not  in (-1,0)  order by id desc ";
					Query query_s1 = sessionhql.createQuery(hqlUpdate_s1).setBigInteger("comm_id", comm_id).setMaxResults(1);
					Integer c2 = (Integer)  query_s1.uniqueResult();
					
					if(c2!=null && c2>0) {
						int seno_id=c2.intValue();
						senoupdateid = (TB_PSG_CHANGE_OF_SENIORITY) sessionhql.get(TB_PSG_CHANGE_OF_SENIORITY.class, seno_id);
						senoupdateid.setModified_by(username);
						senoupdateid.setModified_date(mod_date);
						senoupdateid.setStatus(1);
						sessionhql.update(senoupdateid);
				
			
			}
					
					}
					}
				
				
			}
			
			
			if(app_id!=0) {
				
				String hqlUpdate="select id from TB_PSG_CHANGE_OF_COMISSION where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					updateid = (TB_PSG_CHANGE_OF_COMISSION) sessionhql.get(TB_PSG_CHANGE_OF_COMISSION.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);
					personal_no=updateid.getNew_personal_no();
							
					sessionhql.flush();
					sessionhql.clear();
				
						
				
			}
			}
			
			if(app_id!=0 && personal_no!=null && updateid!=null) {
				
				  String hql_c = "update TB_TRANS_PROPOSED_COMM_LETTER set "
							+ "type_of_comm_granted=:type_of_comm_granted,date_of_seniority=:date_of_seniority,personnel_no=:personnel_no,"
							+ "date_of_commission=:date_of_commission"
							+ " where id=:comm_id ";
				  
					 Query query_c = sessionhql.createQuery(hql_c)
							.setBigInteger("comm_id",comm_id).setInteger("type_of_comm_granted", updateid.getType_of_commission_granted())
							.setString("personnel_no", updateid.getNew_personal_no())
							.setDate("date_of_commission", updateid.getDate_of_permanent_commission());
					 if(senoupdateid!=null)
						 query_c.setDate("date_of_seniority", senoupdateid.getDate_of_seniority());
					 else
						 query_c.setDate("date_of_seniority", updateid.getDate_of_seniority());
				
			          msg = query_c.executeUpdate() > 0 ? "1" :"0";
			          sessionhql.flush();
					  sessionhql.clear();
			}
			
			if(app_id!=0 && personal_no==null && updateid==null) {
				tx.rollback();
				return "0";
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
	public String approveHisInter_arm_service_transfer(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
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
				String hql_n = "update TB_INTER_ARM_TRANSFER set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {

				String hqlUpdate="select id from TB_INTER_ARM_TRANSFER where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					TB_INTER_ARM_TRANSFER updateid = (TB_INTER_ARM_TRANSFER) sessionhql.get(TB_INTER_ARM_TRANSFER.class, chang_id);
					updateid.setModified_by(username);
					updateid.setModified_date(mod_date);
					updateid.setStatus(1);
					sessionhql.update(updateid);					
					sessionhql.flush();
					sessionhql.clear();
					if(updateid.getParent_arm_service()!=null && !updateid.getParent_arm_service().equals("0")) {
						String hql_c = "update TB_TRANS_PROPOSED_COMM_LETTER set parent_arm=:parent_arm,regiment=:regiment where id=:comm_id ";

						Query query_c = sessionhql.createQuery(hql_c).setBigInteger("comm_id", comm_id)
								.setString("parent_arm",updateid.getParent_arm_service()).setString("regiment", updateid.getRegt());

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
	public String approveHisExtension_of_ssc(BigInteger comm_id, List<Map<String, Object>> Lobj, String username,
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
				String hql_n = "update TB_PSG_EXTENSION_OF_COMISSION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_PSG_EXTENSION_OF_COMISSION where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
				
					String hql_n = "update TB_PSG_EXTENSION_OF_COMISSION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisNon_effective(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_NON_EFFECTIVE set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
				
				TB_TRANS_PROPOSED_COMM_LETTER cl=(TB_TRANS_PROPOSED_COMM_LETTER)sessionhql.get(TB_TRANS_PROPOSED_COMM_LETTER.class,  comm_id);
				String hqlUpdate3="select id from TB_POSTING_IN_OUT where comm_id=:comm_id and status=1 and to_sus_no=:to_sus_no order by id desc ";
				Query query3 = sessionhql.createQuery(hqlUpdate3).setBigInteger("comm_id", cl.getId()).setString("to_sus_no", cl.getUnit_sus_no()).setMaxResults(1);
				Integer c = (Integer)  query3.uniqueResult();
				
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					String hql2 = "update TB_POSTING_IN_OUT set modified_by=:modified_by,modified_date=:modified_date,tenure_date=:tenure_date "
							+ " where id=:id ";
				
					Query query2 = sessionhql.createQuery(hql2).setString("modified_by", username)
							.setTimestamp("modified_date",mod_date).setTimestamp("tenure_date",null )
					.setInteger("id", chang_id);
					msg = query2.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
					sessionhql.flush();
					sessionhql.clear();
				}
				
				String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
						+ "status=:status where id=:id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date",mod_date).setInteger("status", 1).setBigInteger("id", comm_id);

				msg = query.executeUpdate() > 0 ? "1" : "0";
				
				
				Query q0 = sessionhql.createQuery("SELECT id FROM TB_NON_EFFECTIVE where comm_id=:comm_id and status=2 ORDER BY ID DESC").setMaxResults(1);
				q0.setParameter("comm_id", comm_id);   
				List<String> list = (List<String>) q0.list();
			
				if(list.size() > 0)
				{
					String hqlUpdate31 = "UPDATE TB_NON_EFFECTIVE main\r\n" + 
							"SET status=1 WHERE  id =:id ";
					 int app3 = sessionhql.createQuery(hqlUpdate31)
							.setInteger("id", Integer.parseInt(String.valueOf(list.get(0)) )).executeUpdate();
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
	public String approveHisSecondment(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
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
				String hql_n = "update TB_CENSUS_SECONDMENT set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
		
			if(app_id!=0) {

				String hqlUpdate="select id from TB_CENSUS_SECONDMENT where comm_id=:comm_id and status=2 order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
					
					String hql_n = "update TB_CENSUS_SECONDMENT set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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
	public String approveHisDeserter(BigInteger comm_id, List<Map<String, Object>> Lobj, String username, Date mod_date) {
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
				String hql_n = "update TB_DESERTER set approved_date=:modified_date ,approved_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();

				
				
			}
			
			
			if(app_id!=0) {


				String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,"
						+ "status=:status where id=:id ";

				Query query = sessionhql.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date",mod_date).setInteger("status", 1).setBigInteger("id", comm_id);

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
	public String approveHisCSD(BigInteger parseInt, List<Map<String, Object>> Lobj, String username,
			Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		
		try {
			
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_PSG_CANTEEN_CARD_DETAIL1  set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by "
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
	
	
	
	
}
