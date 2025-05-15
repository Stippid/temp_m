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

import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Amc;
import com.persistance.util.HibernateUtil;

public class Offic_Admi_AmcAdcMns_DaoImpl implements Offic_Admi_AmcAdcMns_Dao{
	    private DataSource dataSource;
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
				
		public List<Map<String, Object>> SearchOffi_Admi_AmcAdcMns(String sus_no, String frm_dt1, String to_dt1,String adhar1,String contact1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				qry = "  r.id !=  0";
			
				if(!sus_no.equals("")){
					qry += " and r.sus_no = ?";
				} 
				if(!adhar1.equals("")){
					qry += " and r.adhar_card_no = ?";
				} 
				if(!contact1.equals("")){
					qry += " and r.contact_no= ?";
				} 
		 		if(!frm_dt1.equals("") && !to_dt1.equals("")) {
					qry +=" and cast(r.created_on as DATE) between cast(? as DATE) and cast(? as DATE)";
				}else {
					if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
				 		qry += " and cast(r.created_on as DATE)  >= cast(? as DATE)";
				 	}
				 		
			 		if(!to_dt1.equals("") && !to_dt1.equals(null)){
			 			qry += " and cast(r.created_on as DATE)  <= cast(? as DATE)";
				 	} 
				}
				if(sus_no.equals("") && frm_dt1.equals("") && frm_dt1.equals(null) && to_dt1.equals("") && to_dt1.equals(null))
				{
					qry ="";
				}
				if(qry.equals(""))
				{
					
					// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
					
					/*q="select distinct r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active')," + 
							"	to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,r.persnl_no,r.persnl_name,d.icd_code,d.icd_description " + 
							"	from tb_med_daily_hosp_adm_amc r left join tb_med_d_disease_cause d on r.icd_code = d.id order by id desc" ;*/ 
				
					q="select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active')," + 
							"	to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,to_char(r.dt_report, 'DD-MM-YYYY') as report_date,"
							+ "CASE WHEN r.persnl_no = '-2' OR r.persnl_no = '-1' THEN NULL ELSE r.persnl_no END AS persnl_no,r.persnl_name,r.diagnosis_code1d,r.icd_cause_code1d " + 
							"	from tb_med_daily_hosp_adm_amc r  order by id desc" ; 
				}
				else
				{
					/*q="select distinct r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active')," + 
							"to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,r.persnl_no,r.persnl_name,d.icd_code,d.icd_description,r.adhar_card_no,r.contact_no " + 
							"from tb_med_daily_hosp_adm_amc r left join tb_med_d_disease_cause d on r.icd_code = d.id where" +qry+ " order by id desc" ;*/
					
					q="select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active')," + 
							"to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,to_char(r.dt_report, 'DD-MM-YYYY') as report_date,"
							+ "CASE WHEN r.persnl_no = '-2' OR r.persnl_no = '-1' THEN NULL ELSE r.persnl_no END AS persnl_no,r.persnl_name,r.adhar_card_no,r.contact_no,r.diagnosis_code1d,r.icd_cause_code1d " + 
							"from tb_med_daily_hosp_adm_amc r  where" +qry+ " order by id desc" ;
				}
				stmt=conn.prepareStatement(q);
				
				int j=1;  
				if(!sus_no.equals("")){
			 		stmt.setString(j, sus_no);
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
			 		///j+=1;
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
		
		public Tb_Med_Daily_Hosp_Adm_Amc getOffi_Admi_AmcAdcMnsByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Daily_Hosp_Adm_Amc updateid = (Tb_Med_Daily_Hosp_Adm_Amc) sessionHQL.get(Tb_Med_Daily_Hosp_Adm_Amc.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
		
		
		 
		 public String update_Offic_Admi_AmcAdcMns(Tb_Med_Daily_Hosp_Adm_Amc obj,String username){
		      Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
              Transaction tx = sessionHQL.beginTransaction();
             
             String msg = "";
            // try{
                     String hql = "update Tb_Med_Daily_Hosp_Adm_Amc set persnl_no=:t1,rank=:t2,category=:t3,appointment=:t4,persnl_name=:t5,persnl_unit=:t6,sex=:t20,relation=:t21,"
                 + "remarks=:t9,service=:t11,date_time_admission=:t12,age_year=:t14,dt_report=:t15,type=:t16,diagnosis_code1d=:diagnosis_code1d,"
                 + "icd_cause_code1d=:icd_cause_code1d,adhar_card_no=:t17,date_of_birth=:t18,contact_no=:contact_no,modified_by=:m1,modified_on=:m2 where id=:id";
 
           Query query = sessionHQL.createQuery(hql).setString("t1",obj.getPersnl_no()).setInteger("t2", obj.getA_rank().getId()).setString("t3", obj.getCategory()).setString("t4", obj.getAppointment())
                 .setString("t5",obj.getPersnl_name()).setString("t6",obj.getPersnl_unit()).setString("t20",obj.getSex()).setString("t21",obj.getRelation())
            .setString("t9", obj.getRemarks())
                 .setString("t11", obj.getService()).setTimestamp("t12", obj.getDate_time_admission()).setInteger("t14", obj.getAge_year())
                 .setTimestamp("t15", obj.getDt_report()).setString("t16", obj.getType()).setBigInteger("t17", obj.getAdhar_card_no())
                 .setTimestamp("t18", obj.getDate_of_birth()).setBigInteger("contact_no", obj.getContact_no()).setString("diagnosis_code1d", obj.getDiagnosis_code1d()).setString("icd_cause_code1d", obj.getIcd_cause_code1d())
                 .setString("m1",username).setTimestamp("m2", new Date()).setInteger("id",obj.getId());
                     msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
                     tx.commit();
            // }
     /*        catch (Exception e) {
                     msg = "Data Not Updated.";
                     tx.rollback();
             }
             finally {
                     sessionHQL.close();
             }*/
             return msg;

	  }
		 
	 public Long checkExitstingadmExistlNo(String sus_no1,String persnl_name,String dt_admission,int id) {
 		String hql="select count(id) from Tb_Med_Daily_Hosp_Adm_Amc where sus_no=:d1 and persnl_name=:d2 and cast(date_time_admission as date)=cast(:d3 as date) and id!=:id";
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		Transaction tx = session.beginTransaction();
 		Query q1= session.createQuery(hql);
 		q1.setParameter("d1", sus_no1);
 		q1.setParameter("d2", persnl_name);
 		q1.setParameter("d3", dt_admission);
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

}
