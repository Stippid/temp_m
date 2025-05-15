package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_TYPE_OF_EMPLOYMENT;
import com.persistance.util.HibernateUtil;

public class Type_of_EmploymentDAOImpl implements Type_of_EmploymentDAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

public ArrayList<ArrayList<String>> search_type_of_employment (String name,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if( !name.equals("")) {
					qry += " and upper(name) like ?";
				}
				if (!status.equals("0") ) {
					qry += " and upper(status) like ?";
				}
				
				q="select id,upper(name) as name,status from tb_psg_mstr_type_of_employment where id !=0"+ qry;
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if( !name.equals("")) {
						stmt.setString(j, name.toUpperCase()+"%");
						j += 1;	
					}	
					if (!status.equals("0") ) {
						stmt.setString(j, status.toUpperCase());
						j++;
					}
				}
		      ResultSet rs = stmt.executeQuery();      
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Type of Employment ?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("name") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Type of Employment ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
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
		
public TB_TYPE_OF_EMPLOYMENT getTypeofemploymentByid (int id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_TYPE_OF_EMPLOYMENT updateid = (TB_TYPE_OF_EMPLOYMENT) sessionHQL.get(TB_TYPE_OF_EMPLOYMENT.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}

public ArrayList<ArrayList<String>> search_type_of_employmentReport()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

	Connection conn = null;
	String q="";
	try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select id,name from tb_psg_mstr_type_of_employment where id !=0";
			
			stmt=conn.prepareStatement(q);
	      ResultSet rs = stmt.executeQuery();      
	      int i = 1;
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("name"));					
				
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
