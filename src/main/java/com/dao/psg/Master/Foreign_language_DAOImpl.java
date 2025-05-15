package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_FOREIGN_LANGUAGE;
import com.persistance.util.HibernateUtil;

public class Foreign_language_DAOImpl implements Foreign_language_DAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> GetSearch_Foreign_Language(String foreign_language,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				if( !foreign_language.equals("")) {
					qry += " and upper(foreign_language_name) like ?";
				}
				
				if (!status.equals("0") ) {
					qry += " and upper(status) like ?";
					//flag = "N";
				}

				
				
					q="select id,foreign_language_name,status from tb_psg_mstr_foreign_language where id !=0 " + qry+ " order by id desc" ;
				
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if( !foreign_language.equals("")) {
						stmt.setString(j, foreign_language.toUpperCase()+"%");
						j += 1;	
					}
					
					if (!status.equals("0") ) {
						stmt.setString(j, status.toUpperCase());
						j++;
					}
				}
				
		      ResultSet rs = stmt.executeQuery();      
		    //  ResultSetMetaData metaData = rs.getMetaData();
		      //int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("foreign_language_name"));					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Foreign Language ?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("foreign_language_name") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Foreign Language ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
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
		
	public TB_FOREIGN_LANGUAGE getforeign_languageByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_FOREIGN_LANGUAGE updateid = (TB_FOREIGN_LANGUAGE) sessionHQL.get(TB_FOREIGN_LANGUAGE.class, id);
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
	public ArrayList<ArrayList<String>> GetSearch_Foreign_LanguageReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					q="select id,foreign_language_name from tb_psg_mstr_foreign_language where id !=0 order by id desc" ;
				
				stmt=conn.prepareStatement(q);
				
		      ResultSet rs = stmt.executeQuery();      
		      int i = 1;
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	String id = String.valueOf(i++);
		    	  	list.add(id);
					list.add(rs.getString("foreign_language_name"));					
					
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
