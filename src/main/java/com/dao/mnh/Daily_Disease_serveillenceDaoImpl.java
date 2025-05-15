package com.dao.mnh;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;
import com.persistance.util.HibernateUtil;

public class Daily_Disease_serveillenceDaoImpl implements Daily_Disease_serveillenceDAO {
	
	private static final List<Map<String, Object>> List = null;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	

	
	//////// note: exit code daily disease in input ///////////////
	
	
	
	 public Long checkExitstingSURExistlNo(String sus_no1,String persnl_no,String dt_report,int id) {
			String hql="select count(id) from Tb_Med_Daily_Disease_Surv_Details where  sus_no=:d1 and persnl_no=:d2 and cast(dt_report as date)=cast(:d3 as date) and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no1);
			q1.setParameter("d2", persnl_no);
			q1.setParameter("d3", dt_report);
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 //test by guru
	 public Long checkExitstingBasicData(String sus_no1,String dt_report,int id) {
			String hql="select count(id) from Tb_Med_Daily_Disease_Surv_Details where  sus_no=:d1 and cast(dt_report as date)=cast(:d3 as date) and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no1);
			q1.setParameter("d3", dt_report);
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 public Long checkExitstingNilData(String sus_no1,String persnl_no, String dt_report,int id) {
			String hql="select count(id) from Tb_Med_Daily_Disease_Surv_Details where  sus_no=:d1 and persnl_no=:d2 and cast(dt_report as date)=cast(:d3 as date) and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no1);
			q1.setParameter("d2", persnl_no);
			q1.setParameter("d3", dt_report);
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 
	 
	 //upto
	
///////////////////////////////end///////////////////////
	 
		//////// note: serach code daily disease in input ///////////////
	
	 public List<Map<String, Object>> search_daily_activity_data(String sus1, String frm_dt1, String to_dt1,String adhar1,String contact1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				String flag = "Y";
				if(!sus1.equals("")){
					qry += " Where r.sus_no = ?";
					flag = "N";
				} 
				if(!adhar1.equals("")){
					qry += " and r.adhar_card_no = ?";
				} 
				if(!contact1.equals("")){
					qry += " and r.contact_no= ?";
				} 

				if(!frm_dt1.equals("") && !to_dt1.equals("")) {
                    qry +=" and cast(r.created_on as DATE) between cast(? as DATE) and cast(? as DATE)";
            }
             else
             {
                    if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
                             qry += " and cast(r.created_on as DATE)  >= cast(? as DATE)";
                     }
                             
                     if(!to_dt1.equals("") && !to_dt1.equals(null)){
                             qry += " and cast(r.created_on as DATE)  <= cast(? as DATE)";
                     } 
            }
			 	/*	q = "select distinct r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active'),"
				 			+ "to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,r.persnl_no,r.persnl_name,r.diagnosis,s.disease_name,r.adhar_card_no,r.contact_no "
				 			+ "from tb_med_daily_disease_surv_details r left join  tb_med_daily_surv_disease_mstr s on r.diagnosis = s.id "  +qry;    */
				 	
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				q = "SELECT DISTINCT r.id, (SELECT unit_name FROM orbat_all_details_view_mnh WHERE sus_no = r.sus_no AND status_sus_no = 'Active'), "
					    + "TO_CHAR(r.date_time_admission, 'DD-MM-YYYY') AS adm_date, TO_CHAR(r.dt_report, 'DD-MM-YYYY') AS report_date, "
					    + "CASE WHEN r.persnl_no = '-2' OR r.persnl_no = '-1' THEN NULL ELSE r.persnl_no END AS persnl_no, r.persnl_name, r.diagnosis, s.disease_name, r.adhar_card_no, r.contact_no "
					    + "FROM tb_med_daily_disease_surv_details r LEFT JOIN tb_med_daily_surv_disease_mstr s ON r.diagnosis = s.id"  +qry;    
				

				stmt=conn.prepareStatement(q);
				
				int j=1;  
				if(!sus1.equals("")){
			 		stmt.setString(j, sus1);
			 		j+=1;
			 	}
				if(!adhar1.equals("")){
					stmt.setBigDecimal(j, BigDecimal.valueOf(Long.parseLong(adhar1))); 
			    	j++;
				} 
			
				if(!contact1.equals("")){
					stmt.setBigDecimal(j, BigDecimal.valueOf(Long.parseLong(contact1))); //or you can try below
			    	j++;
				} 
				if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
			 		stmt.setString(j, frm_dt1);
			 		j+=1;
			 	} 
				if(!to_dt1.equals("") && !to_dt1.equals(null)){
			 		stmt.setString(j, to_dt1);
			 		j+=1;
			 	}
					 
			 	ResultSet rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
			 	    for(int i = 1; i <= columnCount; i++){
			 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			 	    }
	 	            String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
					String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
	 	  			String f = "";
					f += updateButton;
					f += deleteButton;
	  				columns.put(metaData.getColumnLabel(1), f);
	 	            list.add(columns);
			 	 }
			     rs.close();
			     stmt.close();
			     conn.close();
		     }catch(SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch(SQLException e){
					 }
				 }
			 }
			 return list;
		}
		//////// note: end ///////////////
	 
	// *******************Note::update daily disease in input *****************************************//

	
			
	 public Tb_Med_Daily_Disease_Surv_Details updatedaily_diseaseByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Daily_Disease_Surv_Details updateid = (Tb_Med_Daily_Disease_Surv_Details) sessionHQL.get(Tb_Med_Daily_Disease_Surv_Details.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	
	 
	 

			public String updatedailysdisease(Tb_Med_Daily_Disease_Surv_Details obj,String username){
			                 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			                 Transaction tx = sessionHQL.beginTransaction();
			                
			                String msg = "";
			                try{
			                        String hql = "update Tb_Med_Daily_Disease_Surv_Details set persnl_no=:t1,rank=:t2,category=:t3,appointment=:t4,sex=:t21,relation=:t22,persnl_name=:t5,persnl_unit=:t6,"
			                    + "diagnosis=:t8,remarks=:t9,service=:t11,date_time_admission=:t12,date_time_incident=:t19,age_year=:t14,dt_report=:t15,type=:t16,"
			                    + "adhar_card_no=:t17,date_of_birth=:t18,contact_no=:t20,modified_by=:m1,modified_on=:m2 where id=:id";
			    
			                        Query query = sessionHQL.createQuery(hql).setString("t1",obj.getPersnl_no()).setInteger("t2", obj.getRank().getId()).setString("t3", obj.getCategory())
			                        		.setString("t21", obj.getSex()).setString("t22", obj.getRelation()).setString("t4", obj.getAppointment())
			                    .setString("t5",obj.getPersnl_name()).setString("t6",obj.getPersnl_unit()).setInteger("t8", obj.getDiagnosis().getId()).setString("t9", obj.getRemarks())
			                    .setString("t11", obj.getService()).setTimestamp("t12", obj.getDate_time_admission()).setTimestamp("t19", obj.getDate_time_incident()).setInteger("t14", obj.getAge_year())
			                    .setTimestamp("t15", obj.getDt_report()).setString("t16", obj.getType()).setBigInteger("t17", obj.getAdhar_card_no())
			                    .setTimestamp("t18", obj.getDate_of_birth()).setBigInteger("t20", obj.getContact_no())
			                    .setString("m1",username).setTimestamp("m2", new Date()).setInteger("id",obj.getId());
			                        
			                        msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			                        tx.commit();
			              }
			              catch (Exception e) {
			                     msg = "Data Not Updated.";
			                    tx.rollback();
			               }
			              finally {
			                        sessionHQL.close();
			               }
			                return msg;
			        }
			public Long checkExitstingSURExistlNo_nil(String sus_no1,int id) {
				String hql="select count(id) from Tb_Med_Daily_Disease_Surv_Details where  sus_no=:d1  and id!=:id";
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q1= session.createQuery(hql);
				q1.setParameter("d1", sus_no1);
				q1.setParameter("id", id);
				Long count_list1 =  (Long) q1.uniqueResult();	
				tx.commit();
				session.close();
				if(count_list1 != null)
				{
					return count_list1;
				}
				else
				{
					return (long) 0;
				}
				
			}

			// *******************Note::END*****************************************//
			
}
