package com.dao.Jco_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Jco_Master.TB_PSG_MSTR_RECORD_OFFICE_JCO;
import com.persistance.util.HibernateUtil;

public class Record_OfficeDaoImpl_JCO implements Record_OfficeDAO_JCO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_record_office(String record_office,String sus_no,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			/*if( !country_id.e ) {
				qry += " and country_id = ? ";
			}*/
			if( !record_office.equals("")) {
				qry += " and upper(record_office) like ? ";
			}
			if( !sus_no.equals("")) {
				qry += " and upper(sus_no) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and status = ?";
				//flag = "N";
			}
			
			q="select distinct id,record_office,sus_no,status from tb_psg_mstr_record_office_jco  " + 
					"where id !=0 "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				
				/*if(!religion_name.equals("")) {
					stmt.setString(j, religion_name.toUpperCase());
					j += 1;
				}*/
				if(!record_office.equals("")) {
					stmt.setString(j, record_office.toUpperCase()+"%");
					j += 1;
				}
				if(!sus_no.equals("")) {
					stmt.setString(j, sus_no.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("record_office"));
					list.add(rs.getString("sus_no"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Data?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_PSG_MSTR_RECORD_OFFICE_JCO getrecordoffByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_RECORD_OFFICE_JCO updateid = (TB_PSG_MSTR_RECORD_OFFICE_JCO) sessionHQL.get(TB_PSG_MSTR_RECORD_OFFICE_JCO.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> search_Record_Office_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="select distinct id,record_office,status from tb_psg_mstr_record_office_jco  " + 
						"where id !=0 " ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("record_office"));
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
