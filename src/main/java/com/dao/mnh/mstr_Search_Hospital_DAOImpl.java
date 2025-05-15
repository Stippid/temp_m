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

import com.persistance.util.HibernateUtil;

public class mstr_Search_Hospital_DAOImpl implements mstr_Search_Hospital_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>>gethospitalassignList(String username, String sus_no){

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		   try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "  id !=  0";					
			
			if( !username.equals("-1")) {
				qry += " and user_id = ?";
			}									
			if(!sus_no.equals("") && !sus_no.equals(null)) {
				qry += "  and sus_no like ?";				
			}												
			if(username.equals("") && sus_no.equals(""))
			{
				qry ="";
			}
			if(qry.equals(""))
			{				
				/* q="select distinct a.id,u.username,a.sus_no,\r\n" + 
				 		"(select unit_name from tb_miso_orbat_unt_dtl where sus_no=a.sus_no and status_sus_no='Active') \r\n" + 
				 		"from  tb_med_hosp_assign a \r\n" + 
				 		"inner join\r\n" + 
				 		"(select a.userName,a.userId from logininformation a, userroleinformation b, roleinformation c \r\n" + 
				 		"where a.userId=b.user_id and b.role_id=c.role_id and c.role_type='DEO' and c.access_lvl='MISO' and c.sub_access_lvl = 'Medical') u\r\n" + 
				 		"on u.userId = a.user_id"; */
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				 q="select distinct a.id,u.username,a.sus_no,\r\n" + 
					 		"(select unit_name from orbat_all_details_view_mnh where sus_no=a.sus_no and status_sus_no='Active') \r\n" + 
					 		"from  tb_med_hosp_assign a \r\n" + 
					 		"inner join\r\n" + 
					 		"(select a.userName,a.userId from logininformation a, userroleinformation b, roleinformation c \r\n" + 
					 		"where a.userId=b.user_id and b.role_id=c.role_id and c.role_type='DEO' and c.access_lvl='MISO' and c.sub_access_lvl = 'Medical') u\r\n" + 
					 		"on u.userId = a.user_id"; 
			}
			else
			{
				/*q="select distinct a.id,u.username,a.sus_no,\r\n" + 
						"(select unit_name from tb_miso_orbat_unt_dtl where sus_no=a.sus_no and status_sus_no='Active') \r\n" + 
						"from  tb_med_hosp_assign a \r\n" + 
						"inner join\r\n" + 
						"(select a.userName,a.userId from logininformation a, userroleinformation b, roleinformation c \r\n" + 
						"where a.userId=b.user_id and b.role_id=c.role_id and c.role_type='DEO' and c.access_lvl='MISO' and c.sub_access_lvl = 'Medical') u\r\n" + 
						"on u.userId = a.user_id where " + qry+ " order by id desc" ;*/
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				
				q="select distinct a.id,u.username,a.sus_no,\r\n" + 
						"(select unit_name from orbat_all_details_view_mnh where sus_no=a.sus_no and status_sus_no='Active') \r\n" + 
						"from  tb_med_hosp_assign a \r\n" + 
						"inner join\r\n" + 
						"(select a.userName,a.userId from logininformation a, userroleinformation b, roleinformation c \r\n" + 
						"where a.userId=b.user_id and b.role_id=c.role_id and c.role_type='DEO' and c.access_lvl='MISO' and c.sub_access_lvl = 'Medical') u\r\n" + 
						"on u.userId = a.user_id where " + qry+ " order by id desc" ;
			}
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  int j =1;
				if( !username.equals("-1")) {
					stmt.setInt(j, Integer.parseInt(username));
					j += 1;	
				}
				if( !sus_no.equals("") && !sus_no.equals(null)) {
					stmt.setString(j, sus_no+"%");					
				} 				
			}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  
					list.add(rs.getString("username"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));		
					
					String f = "";
					String f1 = "";
					
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Search Hospital Details?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
					 f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
					list.add(f);
					list.add(f1);
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
	public Long checkExitstinghosp(String sus_no,int id) {
		String hql="select count(id) from Tb_Med_Hosp_Assign where sus_no=:d1 and user_id!=:id ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q1= session.createQuery(hql);
		q1.setParameter("d1", sus_no);
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
}