package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_LINK_DEGREE;
import com.persistance.util.HibernateUtil;

public class Link_DegreeDAOImpl implements Link_DegreeDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_linkdegree(int degree, int specialization, String status) {
		
		
			
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{
				
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
	
				if(degree != 0) {
					qry += "  and  l.degree  = ? ";
				}	
				if(specialization != 0) {
					qry += "  and  l.specialization  = ? ";
				}	
				if (!status.equals("0") ) {
					qry += " and l.status = ?";
					//flag = "N";
				}
				
				q="select l.id,l.degree,l.specialization,l.status,d.degree as dd,s.specialization  as ss from tb_psg_mstr_link_degree l\r\n" + 
						"left join tb_psg_mstr_degree d on d.id=l.degree\r\n" + 
						"left join tb_psg_mstr_specialization s on s.id=l.specialization where l.status='active'\r\n" + 
						""+qry+"" ;
				
				stmt=conn.prepareStatement(q);
				
				
					int j =1;
					
				
					if(degree != 0) {
						stmt.setInt(j, degree);
						j += 1;	
					}
					
					if(specialization != 0) {
						stmt.setInt(j, specialization);
						j += 1;	
					}
					if (!status.equals("0") ) {
						stmt.setString(j, status);
						j++;
					}
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("dd"));
						list.add(rs.getString("ss"));
						
						
				String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Degree link with Specalization?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Degree link with Specalization?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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

public TB_LINK_DEGREE getlinkdegreeByid(int id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
 	@SuppressWarnings("unused")
	Transaction tx = sessionHQL.beginTransaction();
 	TB_LINK_DEGREE updateid = (TB_LINK_DEGREE) sessionHQL.get(TB_LINK_DEGREE.class, id);
	sessionHQL.getTransaction().commit();
	sessionHQL.close();		
	return updateid;
}
}

