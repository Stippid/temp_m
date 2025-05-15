package com.dao.psg.Civilian_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Civilian_Master.TB_EX_SERVICEMEN;
import com.persistance.util.HibernateUtil;

public class ExservicemenDAOImpl implements ExservicemenDAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_exservicemen_details(String ex_servicemen,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !ex_servicemen.equals("")) {
				qry += " and upper(c.ex_servicemen) = ? ";
			}
			if (!status.equals("0") ) {
				qry += " and upper(c.status) =  ? ";
			}
			
				
			q="select distinct \r\n" + 
					"					c.id,\r\n" + 
					"					c.ex_servicemen,\r\n" + 
					"					m.label as status\r\n" + 
					"					FROM tb_psg_mstr_exservicemen c		\r\n" + 
					"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					"					where c.id !=0 "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
				if(!ex_servicemen.equals("")) {
					stmt.setString(j, ex_servicemen.toUpperCase());
					j += 1;	
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status.toUpperCase());
					j++ ;
				}
			
				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ex_servicemen"));
					
					
				    String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ExServicemen?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This ExServicemen?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
					 if(status.equals("inactive"))
						{
							
							list.add(f);
							list.add(f1);

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


	 public TB_EX_SERVICEMEN getexservicemendtlByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_EX_SERVICEMEN updateid = (TB_EX_SERVICEMEN) sessionHQL.get(TB_EX_SERVICEMEN.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}


	 public ArrayList<ArrayList<String>> search_exservicemenReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select distinct \r\n" + 
						"					c.id,\r\n" + 
						"					c.ex_servicemen\r\n" + 
						"					FROM tb_psg_mstr_exservicemen c		\r\n" + 
						"					where c.id !=0 ";
					stmt=conn.prepareStatement(q);
					ResultSet rs = stmt.executeQuery();
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("ex_servicemen"));
						
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
