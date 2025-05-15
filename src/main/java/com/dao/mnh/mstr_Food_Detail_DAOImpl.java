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

import com.models.mnh.Tb_Med_Food_Master;
import com.models.mnh.Tb_Med_Surv_Master;
import com.persistance.util.HibernateUtil;


public class mstr_Food_Detail_DAOImpl implements mstr_Food_Detail_DAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>>search_food_detaillist(String food)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "  id !=  0";
			
			
			if( !food.equals("")) {
				qry += " and upper(food) like ?";
			}									
			
			
			if(food.equals(""))
			{
				qry ="";
			}
			if(qry.equals(""))
			{				
				 q="select distinct id,food from tb_med_food_master order by id desc"; 
			}
			else
			{
				q="select distinct id,food from tb_med_food_master where " + qry+ " order by id desc" ;
			}
			stmt=conn.prepareStatement(q);
			
			if(!qry.equals("food"))     
			{  int j =1;
				if( !food.equals("")) {
					stmt.setString(j, food.toUpperCase()+"%");
					j += 1;	
				}
				
				
			}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  
					list.add(rs.getString("food"));
									
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Food Detail?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("food") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Food Detail?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
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
	
	public String updatefood(Tb_Med_Food_Master obj){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try{
			String hql = "update Tb_Med_Food_Master set food=:food,modify_by=:modify_by,modify_on=:modify_on where id=:id";		
			Query query = sessionHQL.createQuery(hql).setString("food",obj.getFood())
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
