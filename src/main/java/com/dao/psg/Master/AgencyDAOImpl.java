package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_COUNTRY;
import com.models.psg.Master.TB_PSG_AGENCY_MASTER;
import com.persistance.util.HibernateUtil;

public class AgencyDAOImpl implements AgencyDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Agency_name(String agency_name,String status)
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
			if( !agency_name.equals("")) {
				qry += " and upper(agency_name) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and status = ?";
				//flag = "N";
			}
			
			q="select distinct id,agency_name,status from tb_psg_agency_mstr  " + 
					"where id !=0 "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				
				/*if(!religion_name.equals("")) {
					stmt.setString(j, religion_name.toUpperCase());
					j += 1;
				}*/
				if(!agency_name.equals("")) {
					stmt.setString(j, agency_name.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("agency_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country?') ){editData("+ rs.getString("Id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Country?') ){deleteData(" + rs.getString("Id") + ")}else{ return false;}\"";
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
	
	
	///edit
	
	@SuppressWarnings("unused")
	public TB_PSG_AGENCY_MASTER getAgencyByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_AGENCY_MASTER updateid = (TB_PSG_AGENCY_MASTER) sessionHQL.get(TB_PSG_AGENCY_MASTER.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
//	 public ArrayList<ArrayList<String>> search_Country_report()
//		{
//			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//			Connection conn = null;
//			String q="";
//			try{	  
//				conn = dataSource.getConnection();			 
//				PreparedStatement stmt=null;
//				
//				
//				q="select distinct id,agency_name from tb_psg_agency_mstr  " + 
//						"where id !=0 " ;
//					stmt=conn.prepareStatement(q);
//					
//					ResultSet rs = stmt.executeQuery(); 
//					int i = 1;
//					while (rs.next()) {
//						ArrayList<String> list = new ArrayList<String>();
//						String id = String.valueOf(i++);
//						list.add(id);
//						list.add(rs.getString("name"));
//						alist.add(list);
//		 	        }
//			      rs.close();
//			      stmt.close();
//			      conn.close();
//			   }catch (SQLException e) {
//					//throw new RuntimeException(e);
//					e.printStackTrace();
//				} finally {
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e) {
//					  }
//					}
//				}
//			return alist;
//		}

}
