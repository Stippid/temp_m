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

import com.models.mnh.Tb_Med_RankCode;
import com.persistance.util.HibernateUtil;


public class mstr_Rank_CodeDAOImp implements mstr_Rank_CodeDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    public String updaterankcode(Tb_Med_RankCode obj){
   	 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   	 Transaction tx = sessionHQL.beginTransaction();
   	String msg = "";
   	try{
   		String hql = "update Tb_Med_RankCode set service=:service,category_code=:category_code,rank_code=:rank_code,rank_desc=:rank_desc,modified_by=:modified_by,modified_on=:modified_on where id=:id";		
   		Query query = sessionHQL.createQuery(hql).setString("service",obj.getService()).setString("category_code",obj.getCategory_code()).setString("rank_code",obj.getRank_code()).setString("rank_desc", obj.getRank_desc())
   				.setString("modified_by",obj.getModified_by())
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
    
   
  
    public ArrayList<ArrayList<String>> getSearchRankMaster(String service1,String category_code1,String rank_code1,String rank_desc1)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !service1.equals("-1") ) {
				qry += " service  = ? ";
			}
			if(!category_code1.equals("-1")  && !qry.equals("")) {
				qry += " and category_code  = ? ";
			}
			if(!category_code1.equals("-1")  && qry.equals("")) {
				qry += " category_code  = ? ";
			}
			if(!rank_code1.equals("") && !qry.equals("")) {         
				qry += "  and upper(rank_code) LIKE upper(?) ";
			}
			if(!rank_code1.equals("") && qry.equals("")) {         
				qry += " upper(rank_code) LIKE  upper(?) ";
			}
			if(!rank_desc1.equals("") && !qry.equals("")) {
				qry += " and upper(rank_desc) LIKE  upper(?) ";
			}
			if(!rank_desc1.equals("") && qry.equals("")) {
				qry += " upper(rank_desc) LIKE  upper(?) ";
			}
			if(service1.equals("-1") && category_code1.equals("-1") && rank_code1.equals("") && rank_desc1.equals(""))
			{
				qry ="";
			}
			if(qry.equals(""))
			{
				q="select distinct id,service,category_code,rank_code,rank_desc from tb_med_rankcode where id!='-1' order by service,rank_code  ";
			}
			else
			{
				q="select distinct id,service,category_code,rank_code,rank_desc from tb_med_rankcode where " +qry + " order by service,rank_code";							
			}
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  
				int j =1;
				if( !service1.equals("-1")) {
					stmt.setString(j, service1);
					j += 1;	
				}
				if( !category_code1.equals("-1")) {
					stmt.setString(j, category_code1);
					j += 1;	
				} 
				if( !rank_code1.equals("")) {
					stmt.setString(j, "%" + rank_code1 +"%");
					j += 1;	
				}
				if( !rank_desc1.equals("")) {
					stmt.setString(j,"%" + rank_desc1 +"%");
				}
			}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	
					list.add(rs.getString("service"));
					list.add(rs.getString("category_code"));
					list.add(rs.getString("rank_code"));
					list.add(rs.getString("rank_desc"));
					
					
					String f = "";
					String f1 = "";
				
					 
					    String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getInt("id")+",'"+rs.getString("service")+"','"+rs.getString("category_code")+"','"+rs.getString("rank_code")+"','"+rs.getString("rank_desc")+"')}else{ return false;}\"";
						f ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Rank ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
						f1 ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
		 	  			

					list.add(f);
					list.add(f1);
					alist.add(list);
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				
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
}