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

import com.models.mnh.Tb_Med_Daily_Hosp_Adm_Off_Vip;
import com.persistance.util.HibernateUtil;

public class Vip_Off_AdmDaoImpl implements Vip_Off_AdmDAO{
	
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource; 
	}
	public List<Map<String, Object>> search_vip_adm(String sus1, String frm_dt1, String to_dt1,String adhar1,String contact1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
		 	
			if(!sus1.equals("")){
				qry += "  r.sus_no = ?";
				
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
		 		
		 	
			 	
			/*	q = "select distinct r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active'),"
			 			+ "to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,r.persnl_no,r.persnl_name,m.icd_code,m.icd_description,r.adhar_card_no,r.contact_no  "
			 			+ "from tb_med_daily_hosp_adm_off_vip r left join tb_med_d_disease_cause m on r.icd_code=m.id where " +qry;   	 	*/
			
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
		 			+ "to_char(r.date_time_admission, 'DD-MM-YYYY') as adm_date,to_char(r.dt_report, 'DD-MM-YYYY') as report_date,"
		 			+ "CASE WHEN r.persnl_no = '-2' OR r.persnl_no = '-1' THEN NULL ELSE r.persnl_no END AS persnl_no,r.persnl_name,r.adhar_card_no,r.contact_no,r.diagnosis_code1d,r.icd_cause_code1d  "
		 			+ "from tb_med_daily_hosp_adm_off_vip r  where " +qry;   	 		 	
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
	public Long checkExitstingvipExist(String sus_no1,String persnl_name,String dt_admission,String id) {
    	if(id == null || id.equals("null"))
     	{
     		id="0";
     	}
		String hql="select count(id) from Tb_Med_Daily_Hosp_Adm_Off_Vip where sus_no=:d1 and persnl_name=:d2 and cast(date_time_admission as date)=cast(:d3 as date) and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q1= session.createQuery(hql);
		q1.setParameter("d1", sus_no1);
		q1.setParameter("d2", persnl_name);
		q1.setParameter("d3", dt_admission);
		q1.setParameter("id", Integer.parseInt(id));
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
	public Tb_Med_Daily_Hosp_Adm_Off_Vip getvipadm(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	Tb_Med_Daily_Hosp_Adm_Off_Vip updateid = (Tb_Med_Daily_Hosp_Adm_Off_Vip) sessionHQL.get(Tb_Med_Daily_Hosp_Adm_Off_Vip.class, id);
	 	tx.commit();
		sessionHQL.close();		
		return updateid;
	}
	
	public String update_vip_adm(Tb_Med_Daily_Hosp_Adm_Off_Vip obj,String username){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		try{
			String hql = "update Tb_Med_Daily_Hosp_Adm_Off_Vip set persnl_no=:persnl_no,rank=:rank,category=:category,appointment=:appointment,persnl_name=:persnl_name,sex=:sex,relation=:relation,persnl_unit=:persnl_unit,"
                    + "remarks=:remarks,service=:service,date_time_admission=:date_time_admission,age_year=:age_year,dt_report=:dt_report,type=:type,"
                    + "adhar_card_no=:adhar_card_no,date_of_birth=:date_of_birth,modified_by=:modified_by,modified_on=:modified_on,contact_no=:contact_no,diagnosis_code1d=:diagnosis_code1d,icd_cause_code1d=:icd_cause_code1d where id=:id";
    
			Query query = sessionHQL.createQuery(hql).setString("persnl_no",obj.getPersnl_no()).setInteger("rank", obj.getV_rank().getId()).setString("category", obj.getCategory()).setString("appointment", obj.getAppointment())
                    .setString("persnl_name",obj.getPersnl_name()).setString("persnl_unit",obj.getPersnl_unit()).setString("remarks", obj.getRemarks()).setString("sex", obj.getSex()).setString("relation", obj.getRelation())
                    .setString("service", obj.getService()).setTimestamp("date_time_admission", obj.getDate_time_admission()).setInteger("age_year", obj.getAge_year())
                    .setTimestamp("dt_report", obj.getDt_report()).setString("type", obj.getType()).setBigInteger("adhar_card_no", obj.getAdhar_card_no()).setTimestamp("date_of_birth", obj.getDate_of_birth())
                    .setString("modified_by",username).setTimestamp("modified_on", new Date()).setBigInteger("contact_no", obj.getContact_no()).setString("diagnosis_code1d", obj.getDiagnosis_code1d()).setString("icd_cause_code1d", obj.getIcd_cause_code1d()).setInteger("id",obj.getId());
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
	 
}
