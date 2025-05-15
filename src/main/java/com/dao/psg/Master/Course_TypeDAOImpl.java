package com.dao.psg.Master;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.models.psg.Master.TB_COURSE_TYPE;
import com.persistance.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Course_TypeDAOImpl implements Course_TypeDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Course_type(String rank_type,String course_name,String course_gp,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!rank_type.equals("0")) {
				qry += " and c.rank_type = ? ";
			}
			
			if( !course_name.equals("")) {
				qry += " and upper(c.course_name) like upper(?) ";
			}
//			if( !course_type.equals("0")) {
//				qry += " and upper(c.course_type) = ? ";
//			}
			if( !course_gp.equals("")) {
				qry += " and upper(c.course_gp) like upper(?) ";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
			}
			q = "select  \r\n" + 
					"		c.id,\r\n" + 
					"		c.course_name as course_name,\r\n" + 
					"		c.course_gp as course_gp,\r\n" + 
					"		p.label as category,\r\n" + 
					"		c.status\r\n" + 
					"	    FROM tb_psg_mstr_course c \r\n" + 
					" 		INNER join t_domain_value p on  p.label=cast(c.rank_type as text) and p.domainid='SERVICE_CATEGORY' \r\n" + 
					" 		where c.id !=0"+qry;
				
			/*q="select  DISTINCT\r\n" + 
					"					c.id,\r\n" + 
					"					c.course_name,\r\n" + 
					"					c.course_gp,\r\n" + 
					"					m.label as status,\r\n" + 
					"					p.label as category\r\n" + 
					"					FROM tb_psg_mstr_course c \r\n" + 
					"					inner join t_domain_value p on  p.codevalue=cast(c.rank_type as text) and p.domainid='SERVICE_CATEGORY'\r\n" + 
					"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					"					where c.id !=0"+qry ;*/
			
		/*	q="select  \r\n" + 
					"					c.id,\r\n" + 
					"					p.personnel_name,\r\n" + 
					"					c.course_name,\r\n" + 
					"					c.course_gp,\r\n" + 
					"					m.label as status\r\n" + 
					"					p.label as status1\r\n" + 
					"					FROM tb_psg_mstr_course c \r\n" + 
					"					inner join tb_psg_mstr_personnel_type p on cast(c.rank_type as int) = p.id and p.status='active'\r\n" + 
					"					inner join t_domain_value p on domainid='SERVICE_CATEGORY'\r\n" + 
					"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					"					 where c.id !=0"+qry ;*/
				stmt=conn.prepareStatement(q);
				int j =1;
								 				
				if(!rank_type.equals("0")) {
					stmt.setString(j, rank_type);
					j += 1;	
				}
				
					
				if(!course_name.equals("")) {
					stmt.setString(j, course_name.toUpperCase() + '%');
					j += 1;
				}
					
//				if(!course_type.equals("0")) {
//					stmt.setString(j, course_type.toUpperCase());
//					j += 1;
//				}
				if(!course_gp.equals("")) {
					stmt.setString(j, course_gp.toUpperCase() + '%');
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
					list.add(rs.getString("course_name"));
					list.add(rs.getString("course_gp"));
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Course Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Course Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	 public TB_COURSE_TYPE getcourse_typeByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_COURSE_TYPE updateid = (TB_COURSE_TYPE) sessionHQL.get(TB_COURSE_TYPE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	
	/* public String updatecourse_type(TB_COURSE_TYPE obj,String username,int id){
		 
	        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
	        String msg = "";
	        
	        try{
	    	  String hql = "update TB_COURSE_TYPE set rank_type=:rank_type,course_name=:course_name,course_type=:course_type,course_gp=:course_gp,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    			  	.setString("rank_type",obj.getRank_type())
	    			  	.setString("course_name", obj.getCourse_name())
						.setString("course_type", obj.getCourse_type())
						.setString("course_gp",obj.getCourse_gp())
						.setString("modified_by", username).setDate("modified_date", new Date())
						.setInteger("id",id);
	                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	                    tx.commit(); 
	                  
	         }catch (Exception e) {
	               msg = "Data Not Updated.";
	                tx.rollback();
	        }
	        finally {
	              sessionHQL.close();
	        }
	        return msg;
	}*/
	 
	 public ArrayList<ArrayList<String>> search_Course_typeReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
									
				q = "select  \r\n" + 
						"		c.id,\r\n" + 
						"		c.course_name,\r\n" + 
						"		c.course_gp,\r\n" + 
						"		p.label as category,\r\n" + 
						"		c.status\r\n" + 
						"	    FROM tb_psg_mstr_course c \r\n" + 
						" 		INNER join t_domain_value p on  p.label=cast(c.rank_type as text) and p.domainid='SERVICE_CATEGORY' \r\n" + 
						" 		where c.id !=0";
					stmt=conn.prepareStatement(q);
					int i = 1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("category"));
						list.add(rs.getString("course_name"));
						list.add(rs.getString("course_gp"));
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
