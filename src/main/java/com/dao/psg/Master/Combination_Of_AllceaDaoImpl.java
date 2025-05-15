package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_COMBINATION_OF_ALLCEA;
import com.persistance.util.HibernateUtil;

public class Combination_Of_AllceaDaoImpl implements Combination_Of_AllceaDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Combination_of_allcea(String fd1,String fd2,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !fd1.equals("0")) {
				qry += " and c.fd1 = ? ";
			}
			
			if (!fd2.equals("0") ) {
				qry += " and c.fd2 = ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
				//flag = "N";
			}
			
			q="select c.id,\r\n" + 
					"	f1.field_area as fd1,\r\n" + 
					"	f2.field_area as fd2,c.status \r\n" + 
					"from tb_psg_mstr_combination_of_allcea c\r\n" + 
					"inner join tb_psg_mstr_field_area f1 on f1.id = c.fd1\r\n" + 
					"inner join tb_psg_mstr_field_area f2 on f2.id = c.fd2 where c.id !=0"+qry ;
			
				stmt=conn.prepareStatement(q);
				
				int j =1;
				
				
				if(!fd1.equals("0")) {
					stmt.setInt(j,Integer.parseInt(fd1));
					j += 1;
				}
				
				if (!fd2.equals("0") ) {
					stmt.setInt(j, Integer.parseInt(fd2));
					j++;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("fd1"));
					list.add(rs.getString("fd2"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Combination of Allcea?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Combination of Allcea?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_PSG_MSTR_COMBINATION_OF_ALLCEA getCombinationByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_COMBINATION_OF_ALLCEA updateid = (TB_PSG_MSTR_COMBINATION_OF_ALLCEA) sessionHQL.get(TB_PSG_MSTR_COMBINATION_OF_ALLCEA.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> search_Combination_Of_allcea_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select c.id,\r\n" + 
						"	f1.field_area as fd1,\r\n" + 
						"	f2.field_area as fd2,c.status \r\n" + 
						"from tb_psg_mstr_combination_of_allcea c\r\n" + 
						"inner join tb_psg_mstr_field_area f1 on f1.id = c.fd1\r\n" + 
						"inner join tb_psg_mstr_field_area f2 on f2.id = c.fd2 where c.id !=0" ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("fd1"));
						list.add(rs.getString("fd2"));
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
