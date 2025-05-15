package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_COMMISSION_TYPE;
import com.persistance.util.HibernateUtil;



public class commisionDaoImpl implements commisionDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public ArrayList<ArrayList<String>> search_commision(String comn_name,String status)
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !comn_name.equals("")) {
				qry += " and Upper(comn_name) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and upper(status) like ?";
				//flag = "N";
			}
			
			q="select distinct id,comn_name,status from tb_psg_mstr_type_of_commission  " + 
					"where id !=0 "+qry ;
			
			stmt=conn.prepareStatement(q);
			
			
				int j =1;
			
				if(!comn_name.equals("")) {
					stmt.setString(j, comn_name.toUpperCase() +  '%');
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status.toUpperCase());
					j++;
				}
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("comn_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Commision Type?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Commision Type?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_COMMISSION_TYPE updatecommision_Byid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_COMMISSION_TYPE updateid = (TB_COMMISSION_TYPE) sessionHQL.get(TB_COMMISSION_TYPE.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	 
	 public Long checkExitstingcommission(String comn_name1,int id,String status1) {
			String hql="select count(id) from TB_COMMISSION_TYPE where  comn_name=:d1 and status1=:s1 and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", comn_name1);
			q1.setParameter("s1", status1);
			
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 public ArrayList<ArrayList<String>> search_commisionReport()
		{
			
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select distinct id,comn_name from tb_psg_mstr_type_of_commission  " + 
						"where id !=0 and status='active' " ;
				
				stmt=conn.prepareStatement(q);
				
				int i = 1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("comn_name"));
						
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
