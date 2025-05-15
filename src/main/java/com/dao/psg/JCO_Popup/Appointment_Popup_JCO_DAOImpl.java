package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Appointment_Popup_JCO_DAOImpl implements Appointment_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Appointment_PopUp_JCO(int appointment_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				
				q=" select ap.id,ap.appt_authority,ltrim(TO_CHAR(ap.appt_date_of_authority ,'DD-MON-YYYY'),'0') as appt_date_of_authority,cue.appointment,ltrim(TO_CHAR(ap.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment, ap.modified_by,\r\n"  
						//"ap.x_list_loc,ap.x_list_sus_no,"
						+ "ltrim(TO_CHAR(ap.created_date ,'DD-MON-YYYY'),'0') as created_date,ap.created_by, ltrim(TO_CHAR(ap.modified_date ,'DD-MON-YYYY'),'0') as modified_date,\r\n" + 
						"(select user_sus_no from logininformation where username=ap.created_by) as sus,\r\n" + 
						"cue.appointment,ap.status from tb_psg_change_of_appointment_jco ap \r\n" + 
						"inner join  tb_psg_mstr_appt_jco cue on cue.id=ap.appointment   \r\n" + 
						"where ap.jco_id=? and ap.status in (1,2) and ap.cancel_status in (-1,2) order by ap.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,appointment_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("appt_authority"));//1
						list.add(rs.getString("appt_date_of_authority"));//2
						list.add(rs.getString("appointment"));//3
						list.add(rs.getString("date_of_appointment"));//4
						//list.add(rs.getString("x_list_sus_no"));//5
						//list.add(rs.getString("x_list_loc"));//6
						list.add(rs.getString("created_by"));//7
						list.add(rs.getString("created_date"));//8
						list.add(rs.getString("modified_by"));//9
						list.add(rs.getString("modified_date"));//10
						
						/*String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Bank Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Bank Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			
						list.add(f);
						list.add(f1);*/
						
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
      }
}
