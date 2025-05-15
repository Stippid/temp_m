package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_MOTHERTOUNGE;
import com.persistance.util.HibernateUtil;

public class Mother_TongueDAOImpl implements Mother_TongueDAO{


	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_M_T_dtl(String mothertounge,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !mothertounge.equals("")) {
				qry += " and upper(mothertounge) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct \r\n" + 
					"id,\r\n" +
					"mothertounge,status\r\n" + 
					"FROM tb_psg_mothertounge c\r\n" + 
					"where c.id is not null "+qry+" order by mothertounge" ;
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
				if(!mothertounge.equals("")) {
					stmt.setString(j, "%"+mothertounge.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("mothertounge"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Mother Tongue Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Mother Tongue Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	/*
	 * public TB_BANK getbankByid(int id) { Session sessionHQL =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx =
	 * sessionHQL.beginTransaction(); TB_BANK updateid = (TB_BANK)
	 * sessionHQL.get(TB_BANK.class, id); sessionHQL.getTransaction().commit();
	 * sessionHQL.close(); return updateid; }
	 */
	public TB_MOTHERTOUNGE getmtByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_MOTHERTOUNGE updateid = (TB_MOTHERTOUNGE) sessionHQL.get(TB_MOTHERTOUNGE.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	 
	  public ArrayList<ArrayList<String>> search_M_T_Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
					
				q="select distinct \r\n" + 
						"id,\r\n" +
						"mothertounge,status\r\n" + 
						"FROM tb_psg_mothertounge c\r\n" + 
						"where c.id is not null  order by mothertounge" ;
					stmt=conn.prepareStatement(q);
					int i = 1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("mothertounge"));
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
