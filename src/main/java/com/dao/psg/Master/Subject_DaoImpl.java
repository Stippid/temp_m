package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_DIV_GRADE;
import com.models.psg.Transaction.TB_PSG_CENSUS_SUBJECT;
import com.persistance.util.HibernateUtil;

public class Subject_DaoImpl implements Subject_Dao {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	

	public ArrayList<ArrayList<String>> search_sub_details(String subject)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !subject.equals("")) {
				qry += " and upper(description) like upper(?) ";
			}
				
			q=
					"select distinct id,upper(description) as subject from tb_psg_census_subject where id !=0  " +qry;
								
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
				if(!subject.equals("")) {
					stmt.setString(j, subject.toUpperCase()+"%");
					j += 1;	
				}
			
				ResultSet rs = stmt.executeQuery();
				
				
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("subject"));
					
					
				    String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This subject?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This subject?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
					
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
	 public TB_PSG_CENSUS_SUBJECT getsubdtlByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_CENSUS_SUBJECT updateid = (TB_PSG_CENSUS_SUBJECT) sessionHQL.get(TB_PSG_CENSUS_SUBJECT.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> search_SubReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select distinct \r\n" + 
						"					c.id,\r\n" + 
						"					c.description\r\n" + 
						"					FROM tb_psg_census_subject c		\r\n" + 
						"					where c.id !=0 ";
					stmt=conn.prepareStatement(q);
					ResultSet rs = stmt.executeQuery();
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("subject"));
						
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
