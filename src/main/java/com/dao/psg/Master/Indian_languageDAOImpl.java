package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_INDIAN_LANGUAGE;
import com.persistance.util.HibernateUtil;

public class Indian_languageDAOImpl implements Indian_languageDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> search_indian_language (String ind_language,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				//qry = "  id !=  0";
				if( !ind_language.equals("")) {
					qry += " and upper(ind_language) like ?";
				}
				

				if (!status.equals("0") ) {
					qry += " and upper(status) like ?";
					//flag = "N";
				}
				 q="select id,ind_language,status from tb_psg_mstr_indian_language where id !=0"+ qry;
				
				/*
				 * if(ind_language.equals("")) { qry =""; } if(qry.equals("")) {
				 * q="select id,ind_language from tb_psg_mstr_indian_language order by id desc";
				 * } else { q="select id,ind_language from tb_psg_mstr_indian_language where " +
				 * qry+ " order by id desc" ; }
				 */
				 
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if( !ind_language.equals("")) {
						stmt.setString(j, ind_language.toUpperCase()+"%");
						j += 1;	
					}	
					if (!status.equals("0") ) {
						stmt.setString(j, status.toUpperCase());
						j++;
					}				
				}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      @SuppressWarnings("unused")
			int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ind_language"));					
					
					String f = "";
					String f1 = "";
					
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Language Name?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("ind_language") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete Language Name ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
					 f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					 if(status.equals("inactive"))
						{
							
							list.add("");
							list.add("");

						}
						else {
						list.add(f);
						list.add(f1);
						}
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
	
	public TB_INDIAN_LANGUAGE getindianlanByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_INDIAN_LANGUAGE updateid = (TB_INDIAN_LANGUAGE) sessionHQL.get(TB_INDIAN_LANGUAGE.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	public ArrayList<ArrayList<String>> search_indian_languageReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				 q="select id,ind_language from tb_psg_mstr_indian_language where id !=0";
				
				stmt=conn.prepareStatement(q);
		      ResultSet rs = stmt.executeQuery();      
		      int i = 1;
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("ind_language"));					
					
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

}
