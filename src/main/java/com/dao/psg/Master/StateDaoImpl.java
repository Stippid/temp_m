package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_STATE;
import com.persistance.util.HibernateUtil;

public class StateDaoImpl implements StateDao {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_State_name(int country_id,String state_name,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(country_id != 0) {
				qry += "  and s.country_id  = ? ";
			}	
			if( !state_name.equals("")) {
				qry += " and upper(s.state_name) like upper(?) ";
			}
			if (!status.equals("0") ) {
				qry += " and s.status = ?";
				//flag = "N";
			}
			
			q="select distinct s.state_id,upper(s.state_name) as state_name,c.name,m.label as status from tb_psg_mstr_state s \r\n" + 
					"					 left join tb_psg_mstr_country c on s.country_id = c.id \r\n" + 
					"					 left join t_domain_value m on m.codevalue=cast(s.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					"					 where s.state_id!=0 "+ qry +" order by s.state_id desc";
			
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(country_id != 0) {
					stmt.setInt(j, country_id);
					j += 1;	
				}
				if(!state_name.equals("")) {
					stmt.setString(j, state_name.toUpperCase() + '%');
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));
					list.add(rs.getString("state_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This State?') ){editData("+ rs.getString("state_id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This State?') ){deleteData(" + rs.getString("state_id") + ")}else{ return false;}\"";
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
	 public TB_STATE getstateByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_STATE updateid = (TB_STATE) sessionHQL.get(TB_STATE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 public ArrayList<ArrayList<String>> search_StateReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select distinct s.state_id,s.state_name,c.name from tb_psg_mstr_state s \r\n" + 
						"					 left join tb_psg_mstr_country c on s.country_id = c.id \r\n" + 
						"					order by s.state_id desc";
				
				
					stmt=conn.prepareStatement(q);
					int i =1;
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("name"));
						list.add(rs.getString("state_name"));
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
