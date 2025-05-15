package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Ssc_To_Permit_Commission_Popup_JCO_DAOImpl implements Ssc_To_Permit_Commission_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Ssc_To_Permit_Commission_JCO(int ssc_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				
				q="SELECT distinct id,authority,TO_CHAR(date_of_authority ,'DD-MON-YYYY') as date_of_authority ,\r\n" + 
						"first_name,middle_name,last_name, status, created_by, \r\n" + 
						"TO_CHAR(created_date ,'DD-MON-YYYY') as created_date, \r\n" + 
						"modified_by,TO_CHAR(modified_date ,'DD-MON-YYYY') as modified_date\r\n" + 
						"FROM tb_psg_change_name_jco WHERE status in (1,2) AND jco_id =?  order by id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,ssc_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						
						list.add(rs.getString("authority"));//0
						list.add(rs.getString("date_of_authority"));//1
						list.add(rs.getString("new_personal_no"));//2
						list.add(rs.getString("previous_commission"));//3
						list.add(rs.getString("type_of_commission"));//4
						list.add(rs.getString("date_of_permanent_commission"));//5
						list.add(rs.getString("date_of_seniority"));//6
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
