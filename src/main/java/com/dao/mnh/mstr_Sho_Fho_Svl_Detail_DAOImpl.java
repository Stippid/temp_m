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

import com.models.mnh.Tb_Med_Surv_Master;
import com.persistance.util.HibernateUtil;


public class mstr_Sho_Fho_Svl_Detail_DAOImpl implements mstr_Sho_Fho_Svl_Detail_DAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>>search_surv_detaillist(String target_diseases,String target_diseases_sub,String investigation)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "  id !=  0";
			
			
			if( !target_diseases.equals("")) {
				qry += " and upper(target_diseases) like ?";
			}									
			if(!target_diseases_sub.equals("")) {
				qry += "  and upper(target_diseases_sub) like ?";
				
			}									
			if(!investigation.equals("")) {
				qry += "  and upper(investigation) like ?";
			}
			
			if(target_diseases.equals("") && target_diseases_sub.equals("") && investigation.equals(""))
			{
				qry ="";
			}
			if(qry.equals(""))
			{				
				 q="select distinct id,target_diseases,target_diseases_sub,investigation from tb_med_surv_master order by id desc"; 
			}
			else
			{
				q="select distinct id,target_diseases,target_diseases_sub,investigation from tb_med_surv_master where " + qry+ " order by id desc" ;
			}
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  int j =1;
				if( !target_diseases.equals("")) {
					stmt.setString(j, target_diseases.toUpperCase()+"%");
					j += 1;	
				}
				if( !target_diseases_sub.equals("")) {
					stmt.setString(j, target_diseases_sub.toUpperCase()+"%");
					j += 1;	
				} 
				if( !investigation.equals("")) {
					stmt.setString(j, investigation.toUpperCase()+"%");					
				} 
				
			}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  
					list.add(rs.getString("target_diseases"));
					list.add(rs.getString("target_diseases_sub"));
					list.add(rs.getString("investigation"));					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Sho/Fho Svl Detail?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("target_diseases") + "','" + rs.getString("target_diseases_sub") + "','" + rs.getString("investigation") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Sho/Fho Svl Detail?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
					 f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
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
	
	public String updateSho_Fho(Tb_Med_Surv_Master obj){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try{
			String hql = "update Tb_Med_Surv_Master set target_diseases=:target_diseases,target_diseases_sub=:target_diseases_sub,investigation=:investigation,modify_by=:modify_by,modify_on=:modify_on where id=:id";		
			Query query = sessionHQL.createQuery(hql).setString("target_diseases",obj.getTarget_diseases()).setString("target_diseases_sub",obj.getTarget_diseases_sub()).setString("investigation",obj.getInvestigation())
					.setString("modify_by",obj.getModify_by())
					.setTimestamp("modify_on",obj.getModify_on()).setInteger("id", obj.getId());
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
