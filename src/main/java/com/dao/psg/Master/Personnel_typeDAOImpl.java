package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PERSONNEL_TYPE;
import com.persistance.util.HibernateUtil;

public class Personnel_typeDAOImpl implements Personnel_typeDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Personnel_type_dtl(String personnel_name,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			if( !personnel_name.equals("")) {
				qry += " and upper(personnel_name) like ? ";
			}
			if (!status.equals("0") ) {
				qry += " and upper(status) like ?";
				//flag = "N";
			}
		
				
			q="select distinct \r\n" + 
					"id,\r\n" +
					"status,\r\n" +
					"upper(personnel_name) as personnel_name\r\n" + 
					"FROM tb_psg_mstr_personnel_type \r\n" + 
					"where id is not null "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
					
				if(!personnel_name.equals("")) {
					stmt.setString(j, personnel_name.toUpperCase() + '%');
					j += 1;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status.toUpperCase());
					j++;
				}
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("personnel_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Personnel Type?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Personnel Type?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	 public TB_PERSONNEL_TYPE getPersonnel_typeByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_PERSONNEL_TYPE updateid = (TB_PERSONNEL_TYPE) sessionHQL.get(TB_PERSONNEL_TYPE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	
	/* public String updatePersonnel_type(TB_PERSONNEL_TYPE obj,String username,int id){
		 
	        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
	        String msg = "";
	        
	        try{
	    	  String hql = "update TB_PERSONNEL_TYPE set personnel_name=:personnel_name,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    			  	.setString("personnel_name",obj.getPersonnel_name())
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
	 
	 public ArrayList<ArrayList<String>> search_Personnel_type_Report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
			
				q="select distinct \r\n" + 
						"id,\r\n" +
						"personnel_name\r\n" + 
						"FROM tb_psg_mstr_personnel_type \r\n" + 
						"where id is not null " ;
					stmt=conn.prepareStatement(q);
					int i = 1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("personnel_name"));
						
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
