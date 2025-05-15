package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_COURSE_INSTITUTE;
import com.persistance.util.HibernateUtil;

public class Course_InstituteDaoImpl implements Course_InstituteDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Course_Institute(String category,String course_institute,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if (!category.equals("0") ) {
				qry += " and category = ?";
			}
			
			if( !course_institute.equals("")) {
				qry += " and upper(course_institute) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and status = ?";
			}
			
			q="	select distinct c.id,p.label as category,upper(c.course_institute) as course_institute,c.status from tb_psg_mstr_course_institute c\r\n" + 
					"					inner join t_domain_value p on  p.codevalue=cast(c.category as text) and p.domainid='SERVICE_CATEGORY'\r\n" + 
					"					where c.id !=0"+qry ;
				stmt=conn.prepareStatement(q);
				
				int j =1;
				if (!category.equals("0") ) {
					stmt.setString(j, category);
					j++;
				}
				
				if(!course_institute.equals("")) {
					stmt.setString(j, course_institute.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("category"));
					list.add(rs.getString("course_institute"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Course Institute?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Course Institute?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
					//}
					
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
	 public TB_PSG_MSTR_COURSE_INSTITUTE getCourse_InstituteByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_COURSE_INSTITUTE updateid = (TB_PSG_MSTR_COURSE_INSTITUTE) sessionHQL.get(TB_PSG_MSTR_COURSE_INSTITUTE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> search_Course_Institute_Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="	select distinct c.id,p.label as category,c.course_institute,c.status from tb_psg_mstr_course_institute c\r\n" + 
						"					inner join t_domain_value p on  p.codevalue=cast(c.category as text) and p.domainid='SERVICE_CATEGORY'\r\n" + 
						"					where c.id !=0" ;
					stmt=conn.prepareStatement(q);
					
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("category"));
						list.add(rs.getString("course_institute"));
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
