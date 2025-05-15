package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_Height;
import com.persistance.util.HibernateUtil;

public class HeightDAOImpl implements HeightDAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
		public ArrayList<ArrayList<String>> search_height_Master(String centimeter,String inch,String status){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !centimeter.equals("")) {
				qry += " and upper(c.centimeter) = ? ";
			}
			if( !inch.equals("")) {
				qry += " and upper(c.inch) = ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
				//flag = "N";
			}
			q="select distinct c.height_id,c.centimeter,c.inch,m.label as status from tb_psg_mstr_height c \r\n" + 
					"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					"					where c.height_id !=0"+qry ;
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!centimeter.equals("")) {
					stmt.setString(j, centimeter.toUpperCase());
					j += 1;
				}
				if(!inch.equals("")) {
					stmt.setString(j, inch.toUpperCase());
					j += 1;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("centimeter"));
					list.add(rs.getString("inch"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Height?') ){editData("+ rs.getString("height_id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Height?') ){deleteData(" + rs.getString("height_id") + ")}else{ return false;}\"";
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
	
	
		 public TB_Height getheightByid(int id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	@SuppressWarnings("unused")
				Transaction tx = sessionHQL.beginTransaction();
			 	TB_Height updateid = (TB_Height) sessionHQL.get(TB_Height.class, id);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();		
				return updateid;
			}

		 public ArrayList<ArrayList<String>> search_height_Report(){
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q="";
				try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					q="select distinct c.height_id,c.centimeter,c.inch from tb_psg_mstr_height c \r\n" + 
							"					where c.height_id !=0" ;
					
						stmt=conn.prepareStatement(q);
						int i = 1;
						ResultSet rs = stmt.executeQuery();      
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							String id = String.valueOf(i++);
							list.add(id);
							list.add(rs.getString("centimeter"));
							list.add(rs.getString("inch"));
							
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
