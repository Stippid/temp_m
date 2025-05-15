package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_LANG_STD;
import com.persistance.util.HibernateUtil;

public class LanguageStandardDAOImpl implements LanguageStandardDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_LangSTD_dtl(String lang,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !lang.equals("")) {
				qry += " and upper(name) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct \r\n" + 
					"id,\r\n" +
					"name,status\r\n" + 
					"FROM tb_psg_lang_std c\r\n" + 
					"where c.id is not null "+qry +"order by name" ;
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
					
				if(!lang.equals("")) {
					stmt.setString(j, "%"+lang.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Language Standard Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Language Standard Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					// bisag 091222 v2 (inactive editable)
					/*if(status.equals("inactive"))
					{
						
						list.add("");
						list.add("");

					}
					else {*/
					list.add(f);
					list.add(f1);
				//	}
					
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
	
	 @SuppressWarnings("unused")
	public TB_LANG_STD getLangStdByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_LANG_STD updateid = (TB_LANG_STD) sessionHQL.get(TB_LANG_STD.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 public ArrayList<ArrayList<String>> Search_LangSTD_Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
					
				q="select distinct \r\n" + 
						"id,\r\n" +
						"name,status\r\n" + 
						"FROM tb_psg_lang_std c\r\n" + 
						"where c.id is not null order by name" ;
					stmt=conn.prepareStatement(q);
					int i = 1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("name"));
						list.add(rs.getString("status"));
						
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
