package com.dao.psg.Master;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_SUB_CLAUSE;
import com.persistance.util.HibernateUtil;


public class Sub_clauseDAOImpl implements Sub_clauseDAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
	}
	public ArrayList<ArrayList<String>> search_Sub_Clause(String sub_clause,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !sub_clause.equals("")) {
				qry += " and upper(sub_clause) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and status = ?";
			}
			
			q="select distinct id,sub_clause,status from tb_psg_mstr_sub_clause  " + 
					"where id !=0 "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!sub_clause.equals("")) {
					stmt.setString(j, sub_clause.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sub_clause"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Sub Clause?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Sub Clause?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_PSG_MSTR_SUB_CLAUSE getSub_clauseByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_SUB_CLAUSE updateid = (TB_PSG_MSTR_SUB_CLAUSE) sessionHQL.get(TB_PSG_MSTR_SUB_CLAUSE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> search_Sub_ClauseReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select distinct id,sub_clause,status from tb_psg_mstr_sub_clause  " + 
						"where id !=0 " ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("sub_clause"));
						list.add(rs.getString("status"));
						alist.add(list);
		 	        }
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch (SQLException e) {
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



