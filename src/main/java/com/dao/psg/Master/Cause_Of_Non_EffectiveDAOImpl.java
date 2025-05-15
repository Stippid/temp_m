package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE;
import com.persistance.util.HibernateUtil;

public class Cause_Of_Non_EffectiveDAOImpl implements Cause_Of_Non_EffectiveDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Cause_Of_Non_Effe(String causes_name,String type_of_officer,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !causes_name.equals("")) {
				qry += " and UPPER(c.causes_name) like ? ";
			}
			
			if (!type_of_officer.equals("0") ) {
				qry += " and type_of_officer = ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
			}
			
			q="select  c.id, UPPER(c.causes_name) AS causes_name,t.label AS type_of_officer,\r\n" + 
					"		c.status  \r\n" + 
					"from tb_psg_mstr_cause_of_non_effective c\r\n" + 
					"inner join t_domain_value t on t.codevalue = cast(c.type_of_officer as char) and t.domainid='TYPE_OF_NON_EFFECTIVE'"
					+ "where c.id !=0 "+qry ;
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				
				if(!causes_name.equals("")) {
					stmt.setString(j,causes_name.toUpperCase()+"%");
					j += 1;
				}
				
				if (!type_of_officer.equals("0") ) {
					stmt.setInt(j, Integer.parseInt(type_of_officer));
					j++;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("causes_name"));
					list.add(rs.getString("type_of_officer"));
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Causes of Non Effective Detail?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Causes of Non Effective Detail?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	 @SuppressWarnings("unused")
	 public TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE getCauNonEffeByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE updateid = (TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE) sessionHQL.get(TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> Cause_Of_Noneff_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select  c.id,c.causes_name,t.label AS type_of_officer,\r\n" + 
						"		c.status  \r\n" + 
						"from tb_psg_mstr_cause_of_non_effective c\r\n" + 
						"inner join t_domain_value t on t.codevalue = cast(c.type_of_officer as char) and t.domainid='TYPE_OF_NON_EFFECTIVE'\r\n" + 
						"where c.id != 0" ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("causes_name"));
						list.add(rs.getString("type_of_officer"));
						list.add(rs.getString("status"));
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
