package com.dao.Jco_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Jco_Master.TB_PSG_MSTR_APPOINTMENT_JCO;
import com.persistance.util.HibernateUtil;

public class ApptDAOImpl implements ApptDAO{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
	}

	
	
	public ArrayList<ArrayList<String>> search_Appt(String appointment,String category,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !appointment.equals("")) {
				qry += " and upper(appointment) like ? ";
			}
			if( !category.equals("")) {
				qry += " and upper(category) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and status = ?";
			}
			
			q="select distinct id,appointment,category,status from tb_psg_mstr_appt_jco  " + 
					"where id !=0 "+qry ;
				stmt=conn.prepareStatement(q);

				int j =1;
				
				if(!appointment.equals("")) {
					stmt.setString(j, appointment.toUpperCase()+"%");
					j += 1;
				}
				if(!category.equals("")) {
					stmt.setString(j, category.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("appointment"));
					list.add(rs.getString("category"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Appointment?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Appointment?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
		public TB_PSG_MSTR_APPOINTMENT_JCO getApptByid(int id) {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	Transaction tx = sessionHQL.beginTransaction();
			 	TB_PSG_MSTR_APPOINTMENT_JCO updateid = (TB_PSG_MSTR_APPOINTMENT_JCO) sessionHQL.get(TB_PSG_MSTR_APPOINTMENT_JCO.class, id);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();		
				return updateid;
			}
		 
	  public ArrayList<ArrayList<String>> Appt_Report()
			{
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q="";
				try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
					
					q="select distinct id,appointment,category,status from tb_psg_mstr_appt_jco   " + 
							"where id !=0 " ;
						stmt=conn.prepareStatement(q);
						
						ResultSet rs = stmt.executeQuery(); 
						int i = 1;
						while (rs.next()) {
							ArrayList<String> list = new ArrayList<String>();
							String id = String.valueOf(i++);
							list.add(id);
							list.add(rs.getString("appointment"));
							list.add(rs.getString("category"));
							list.add(rs.getString("status"));
							alist.add(list);
			 	        }
				      rs.close();
				      stmt.close();
				      conn.close();
				   }catch (SQLException e) {
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
