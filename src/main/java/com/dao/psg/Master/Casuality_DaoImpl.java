package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_CASUALITY1;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY2;
import com.models.psg.Master.TB_PSG_MSTR_CASUALITY3;
import com.persistance.util.HibernateUtil;

public class Casuality_DaoImpl implements Casuality_Dao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_Casuality(String casuality_v1,String status)
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !casuality_v1.equals("")) {
				qry += " and upper(casuality1) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct id,casuality1,status from tb_psg_mstr_casuality1  \r\n" + 
					"where id is not null "+qry+" order by casuality1" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				if(!casuality_v1.equals("")) {
					stmt.setString(j, "%"+casuality_v1.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("casuality1"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Casulity Detail?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Casulity Detail?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_PSG_MSTR_CASUALITY1 getCasuality1Byid(int id) { 
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
		@SuppressWarnings("unused")
		Transaction tx =sessionHQL.beginTransaction();
		TB_PSG_MSTR_CASUALITY1 updateid = (TB_PSG_MSTR_CASUALITY1) sessionHQL.get(TB_PSG_MSTR_CASUALITY1.class, id); 
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
		}
	
	public ArrayList<ArrayList<String>> Report_Casuality1()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				
			q="select distinct id,casuality1,status from tb_psg_mstr_casuality1  \r\n" + 
					"where id is not null order by casuality1" ;
				stmt=conn.prepareStatement(q);
				int i =1;
					
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("casuality1"));
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
	
	
	
	
	///-----------------------CASUALITY 2---------------
	
	
	public ArrayList<ArrayList<String>> Search_Casuality2(int casuality1_id,String casuality2_v2,String status){
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(casuality1_id != 0) {
				qry += "  and s.casuality1_id  = ? ";
			}	
			if( !casuality2_v2.equals("")) {
				qry += " and upper(s.casuality2) like ? ";
			}
			if (!status.equals("0") ) {
				qry += " and s.status = ?";
				//flag = "N";
			}
			
			q="select distinct s.id,s.casuality2,c.casuality1 from tb_psg_mstr_casuality2 s \r\n" + 
					"				left join tb_psg_mstr_casuality1 c on s.casuality1_id = c.id " + 
					"					 where s.id!=0 "+ qry +" order by s.id desc";
			
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(casuality1_id != 0) {
					stmt.setInt(j, casuality1_id);
					j += 1;	
				}
				if(!casuality2_v2.equals("")) {
					stmt.setString(j, casuality2_v2.toUpperCase());
					j += 1;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("casuality1"));
					list.add(rs.getString("casuality2"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This State?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This State?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	 public TB_PSG_MSTR_CASUALITY2 getCasuality2Byid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_CASUALITY2 updateid = (TB_PSG_MSTR_CASUALITY2) sessionHQL.get(TB_PSG_MSTR_CASUALITY2.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> Search_Casuality2Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select distinct s.id,s.casuality2,c.casuality1 from tb_psg_mstr_casuality2 s \r\n" + 
						"				left join tb_psg_mstr_casuality1 c on s.casuality1_id = c.id " + 
						"					 where s.id!=0 order by s.id desc";
				
				
					stmt=conn.prepareStatement(q);
					int i =1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("casuality1"));
						list.add(rs.getString("casuality2"));
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
	 
	 ///-------------CASUALITY 3
	 
	 public ArrayList<ArrayList<String>> Search_Casuality3(int casuality1_id, int casuality2_id,String casuality3_v1, String status)
	 {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				if( casuality1_id!=0) {
					qry += " and d.casuality1_id = ? ";
				}
				if( casuality2_id!=0) {
					qry += " and d.casuality2_id = ? ";
				}
				if( !casuality3_v1.equals("")) {
					qry += " and upper(d.casuality3) = upper(?) ";
				}
				if (!status.equals("0") ) {
					qry += " and upper(d.status) like ?";
				}
					
				q="select d.id,d.casuality3,b.casuality1,e.casuality2 \r\n"
						+ "from tb_psg_mstr_casuality3 d \r\n"
						+ "	left join tb_psg_mstr_casuality1 b on b.id = d.casuality1_id  \r\n"
						+ "	left join tb_psg_mstr_casuality2 e on e.id = d.casuality2_id  \r\n"
						+ "	left join t_domain_value m on m.codevalue=cast(d.status as text) and m.domainid='STATUS_MSTR'\r\n"
						+ "	where d.id !=0 "+qry ;
					
					stmt=conn.prepareStatement(q);
					int j =1;
					
					if( casuality1_id !=0) {
						stmt.setInt(j, casuality1_id);
						j += 1;
					}
					if( casuality2_id!=0) {
						stmt.setInt(j, casuality2_id);
						j += 1;
					}
					if(!casuality3_v1.equals("")) {
						stmt.setString(j, casuality3_v1.toUpperCase());
						j += 1;
					}
					if (!status.equals("0") ) {
						stmt.setString(j, status.toUpperCase());
						j+= 1;
					}
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("casuality1"));
						list.add(rs.getString("casuality2"));
						list.add(rs.getString("casuality3"));
						String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This District?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This District?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
		public TB_PSG_MSTR_CASUALITY3 getCasuality3Byid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			TB_PSG_MSTR_CASUALITY3 updateid = (TB_PSG_MSTR_CASUALITY3) sessionHQL.get(TB_PSG_MSTR_CASUALITY3.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			return updateid;
		}
		
		public ArrayList<ArrayList<String>> Search_Casuality3_Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					
				q="select d.id,d.casuality3,b.casuality1,e.casuality2 \r\n"
						+ "from tb_psg_mstr_casuality3 d \r\n"
						+ "	left join tb_psg_mstr_casuality1 b on b.id = d.casuality1_id  \r\n"
						+ "	left join tb_psg_mstr_casuality2 e on e.id = d.casuality2_id  \r\n"
						+ "	where d.id !=0 " ;
					
					stmt=conn.prepareStatement(q);
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("casuality1"));
						list.add(rs.getString("casuality2"));
						list.add(rs.getString("casuality3"));
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
