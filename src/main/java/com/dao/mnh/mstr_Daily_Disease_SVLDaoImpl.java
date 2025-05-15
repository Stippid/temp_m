package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;
import com.persistance.util.HibernateUtil;


public class mstr_Daily_Disease_SVLDaoImpl implements mstr_Daily_Disease_SVLDao{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> SearchDailyDiseaseSvl(String disease_name,String disease_type,String status) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "  id !=  0";
		
			if(!disease_name.equals("")){
				
				qry += " and upper(disease_name) LIKE upper(?) ";
			} 
			
			if(!disease_type.equals("")){
					qry += " and upper(disease_type) LIKE upper(?) ";
					
			} 
			
			if(!status.equals("")){
					qry += " and status like ?";
			} 
			
			if(disease_name.equals("") && disease_type.equals("") && status.equals(""))
			{
				qry ="";
			}
			if(qry.equals(""))
			{
				 q="select distinct id,disease_name,disease_type,status from tb_med_daily_surv_disease_mstr order by disease_name"; 
			}
			else
			{
				q="select distinct id,disease_name,disease_type,status from tb_med_daily_surv_disease_mstr where" +qry+ " order by disease_name" ;
			}
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  int j =1;
					if(!disease_name.equals("") && !disease_name.equals(null)){
				 		stmt.setString(j,'%'+disease_name+'%');
				 		j++;	
					} 
				    
				    if(!disease_type.equals("") && !disease_type.equals(null)){
				 		stmt.setString(j,'%'+disease_type+'%');
				 		j++;	
				    }
					
				    if(!status .equals("") && !status.equals(null)){
				    	stmt.setString(j,status);
					} 
			}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("disease_name"));
					list.add(rs.getString("disease_type"));
					list.add(rs.getString("status"));
					
					String f = "";
					String f1 = "";
					 
					 String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("disease_name")+"','"+rs.getString("disease_type")+"','"+rs.getString("status")+"')}else{ return false;}\"";
					 f ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
					f1 ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
					
					list.add(f);
					list.add(f1);
					alist.add(list);
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
				  }
				}
			}
		return alist;
	}
	
	
	
	public String update_daily_dSurve(Tb_Med_Daily_Surv_Disease_Mstr obj){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		try{
	       String hql = "update Tb_Med_Daily_Surv_Disease_Mstr set disease_name=:disease_name,disease_type=:disease_type,status=:status,modified_by=:modified_by,modified_on=:modified_on where id=:id";		
		  Query query = sessionHQL.createQuery(hql).setString("disease_name",obj.getDisease_name()).setString("disease_type",obj.getDisease_type())
				  .setString("status",obj.getStatus()).setString("modified_by",obj.getModified_by())
					.setTimestamp("modified_on",obj.getModified_on()).setInteger("id", obj.getId());
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
