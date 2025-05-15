package com.dao.psg.Civilian_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Civilian_Master.TB_PSG_MSTR_CADRE_CIVILIAN;

import com.persistance.util.HibernateUtil;

public class Cadre_CivilianDAOImpl implements Cadre_CivilianDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Cadre_civilian(String cadre,String status)
	{
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !cadre.equals("")) {
				qry += "and ca.cadre like ? ";
			}
			if (!status.equals("0") ) {
				qry += " and ca.status = ? ";
			}
			
			
			q=" SELECT ca.id,ca.cadre,ca.status FROM tb_psg_mstr_cadre_civilian ca WHERE ca.id !=0 "+qry ;
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				
				if(!cadre.equals("")) {
					stmt.setString(j,cadre+"%");
					j += 1;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cadre"));
				
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Record?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Record?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		 {
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
	 public TB_PSG_MSTR_CADRE_CIVILIAN getCadreCivilianByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_CADRE_CIVILIAN updateid = (TB_PSG_MSTR_CADRE_CIVILIAN) sessionHQL.get(TB_PSG_MSTR_CADRE_CIVILIAN.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> Cadre_civilian_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="SELECT ca.id,ca.cadre,ca.status FROM tb_psg_mstr_cadre_civilian ca WHERE ca.id !=0" ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("cadre"));
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
	 
	 public ArrayList<ArrayList<String>> GetAllcomm_details(String comm_id) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = " select id from tb_psg_posting_in_out where comm_id  = ? ORDER BY dt_of_tos desc limit 2";
				stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(comm_id));
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id")); // 0
					/*alist.add(rs.getString("date_of_birth")); // 1
					alist.add(rs.getString("relationship")); // 2
					alist.add(rs.getString("aadhar_no")); // 3
					alist.add(rs.getString("pan_no")); // 4
					alist.add(rs.getString("sibling_service")); // 5
					alist.add(rs.getString("sibling_personal_no")); // 6
					alist.add(rs.getString("other_sibling_ser")); // 7
*/
					list.add(alist);

				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return list;
		}

}
