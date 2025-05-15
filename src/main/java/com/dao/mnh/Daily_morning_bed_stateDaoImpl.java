package com.dao.mnh;

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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.mnh.Tb_Med_Daily_Bed_Occupancy;
import com.persistance.util.HibernateUtil;


public  class Daily_morning_bed_stateDaoImpl implements Daily_morning_bed_stateDAO{
		
		HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
		
	    private DataSource dataSource;
		
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
	
	
	
	public Long checkExitstingbedExistlNo(String sus_no1,String dt,int id) {
	        
   		String hql="select count(id) from Tb_Med_Daily_Bed_Occupancy where sus_no=:d1 and cast(dt as date)=cast(:d2 as date) and id!=:id";
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		Transaction tx = session.beginTransaction();
   		Query q1= session.createQuery(hql);
   		q1.setParameter("d1", sus_no1);
   		q1.setParameter("d2", dt);
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
	   
	   public List<Map<String, Object>> search_morning_bed_status_details(String sus1, String frm_dt1, String to_dt1) {
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
			 			+ "r.sus_no,r.auth_beds,to_char(r.dt, 'DD-MM-YYYY') as date,r.beds_laid_out,r.total_no_of_patients "
			 			+ "from tb_med_daily_bed_occupancy r" +qry;   */ 
						
						// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
						
				q = "select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
					 	+ "r.sus_no,r.auth_beds,to_char(r.dt, 'DD-MM-YYYY') as date,r.beds_laid_out,r.total_no_of_patients "
					 	+ "from tb_med_daily_bed_occupancy r" +qry;   
			 	
				stmt=conn.prepareStatement(q);
				
				int j=1;  
				if(!sus1.equals("")){
			 		stmt.setString(j, sus1);
			 		j+=1;
			 	}
				if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
			 		stmt.setString(j, frm_dt1);
			 		j+=1;
			 	} 
				if(!to_dt1.equals("") && !to_dt1.equals(null)){
			 		stmt.setString(j, to_dt1);
			 		
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
	   
	   
		
			public Tb_Med_Daily_Bed_Occupancy updatemorning_bed_statusByid(int id) {
					Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 	Transaction tx = sessionHQL.beginTransaction();
				 	Tb_Med_Daily_Bed_Occupancy updateid = (Tb_Med_Daily_Bed_Occupancy) sessionHQL.get(Tb_Med_Daily_Bed_Occupancy.class, id);
					sessionHQL.getTransaction().commit();
					sessionHQL.close();		
					return updateid;
				}
			public String updatemornibedstate(Tb_Med_Daily_Bed_Occupancy obj,String username){
				
                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = sessionHQL.beginTransaction();
               
               String msg = "";
               //try{
            		String hql = "update Tb_Med_Daily_Bed_Occupancy set dt=:t1,beds_laid_out=:t2,total_no_of_patients=:t3,"
        					+ "bed_occ_per_as_per_laid_out_bed=:t4,bed_occ_per_as_per_auth_bed=:t5,"
        					+ "off_army=:t6,off_fam_army=:t7,jco_or_army=:t8,jco_or_fam_army=:t9,ex_off_army=:t10,"
        					+ "ex_off_fam_army=:t11,ex_jco_or_army=:t12,ex_jco_or_fam_army=:t13,"
        					+ "para_mil_pers=:t30,para_family=:t31,other_ne=:t32,other_family=:t33,"
        					+ "cadet=:t34,rect_agniveer=:t35,"
        					+ "modified_by=:m1,modified_on=:m2 where id=:id";
            		
        		Query query = sessionHQL.createQuery(hql).setTimestamp("t1",obj.getDt()).setInteger("t2", obj.getBeds_laid_out())
        					.setInteger("t3",obj.getTotal_no_of_patients())
        					.setDouble("t4", obj.getBed_occ_per_as_per_laid_out_bed())
        					.setDouble("t5", obj.getBed_occ_per_as_per_auth_bed())
        					
        					.setInteger("t6",obj.getOff_army())
        					.setInteger("t7",obj.getOff_fam_army())
        					.setInteger("t8", obj.getEx_jco_or_army())
        					.setInteger("t9", obj.getJco_or_fam_army())
        					.setInteger("t10", obj.getEx_off_army())
        					.setInteger("t11", obj.getEx_off_fam_army())
        					.setInteger("t12", obj.getEx_jco_or_army())
        					.setInteger("t13", obj.getEx_jco_or_fam_army())
        					.setInteger("t30", obj.getPara_mil_pers())
        					.setInteger("t31", obj.getPara_family())
        					.setInteger("t32", obj.getOther_ne())
        					.setInteger("t33", obj.getOther_family())
        					.setInteger("t34", obj.getCadet())
        					.setInteger("t35", obj.getRect_agniveer())
        					.setString("m1",username).setTimestamp("m2", new Date()).setInteger("id",obj.getId());
					
				  	
        		
                       msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
                   
                       tx.commit();
            //  }
         /*      catch (Exception e) {
                       msg = "Data Not Updated.";
                       tx.rollback();
               }
               finally {
                       sessionHQL.close();
               }*/
               return msg;
       }
	   

}
