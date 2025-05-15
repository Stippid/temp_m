package com.dao.psg.Civilian_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Civilian_Master.TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN;
import com.persistance.util.HibernateUtil;

public class Cause_Of_Non_Effective_CivilianDAOImpl implements Cause_Of_Non_Effective_CivilianDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Cause_Of_Non_Effe_civilian(String causes_name,String type_of_civilian,String status,String type_of_regular_or_nonregular)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !causes_name.equals("")) {
				qry += "and UPPER(c.causes_name) like ? ";
			}
			
			if (!type_of_civilian.equals("0") ) {
				qry += " and c.type_of_civilian = ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ? ";
			}
			if (!type_of_regular_or_nonregular.equals("0") ) {
				qry += " and c.type_of_regular_or_nonregular like  ?";
			}
			
				q=" select  c.id,c.causes_name,t.label AS type_of_civilian ,c.type_of_regular_or_nonregular,c.status  \r\n" + 
						"	    from tb_psg_mstr_cause_of_non_effective_civilian c\r\n" + 
						"		inner join t_domain_value t on t.codevalue = cast(type_of_civilian as char) and t.domainid='TYPE_OF_NON_EFFECTIVE' WHERE c.id !=0 "+qry ;
				
			//}
				stmt=conn.prepareStatement(q);
				
				int j =1;
				
				
				if(!causes_name.equals("")) {
					stmt.setString(j,causes_name.toUpperCase()+"%");
					j += 1;
				}
				
				if (!type_of_civilian.equals("0") ) {
					stmt.setInt(j, Integer.parseInt(type_of_civilian));
					j++;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				if (!type_of_regular_or_nonregular.equals("0") ) {
					stmt.setString(j,(type_of_regular_or_nonregular));
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("causes_name"));
					list.add(rs.getString("type_of_civilian"));
					list.add(rs.getString("type_of_regular_or_nonregular"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Record?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Record?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
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
	 public TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN getCauNonEffeCivilianByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN updateid = (TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN) sessionHQL.get(TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> Cause_Of_Noneff_civilian_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select  c.id,c.causes_name,t.label AS type_of_civilian ,c.type_of_regular_or_nonregular,c.status \r\n" + 
						" from tb_psg_mstr_cause_of_non_effective_civilian c \r\n" + 
						"inner join t_domain_value t on t.codevalue = cast(c.type_of_civilian as char) and t.domainid='TYPE_OF_NON_EFFECTIVE' WHERE c.id !=0" ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("causes_name"));
						list.add(rs.getString("type_of_civilian"));
						list.add(rs.getString("type_of_regular_or_nonregular"));
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
