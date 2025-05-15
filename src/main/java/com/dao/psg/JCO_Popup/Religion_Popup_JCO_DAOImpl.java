package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Religion_Popup_JCO_DAOImpl implements Religion_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Religion_PopUp_JCO(int religion_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				
				q="SELECT\r\n" + 
						"cr.authority,\r\n" + 
						"TO_CHAR(cr.date_of_authority ,'DD-MON-YYYY') as date_of_authority, \r\n" + 
						"case when mr.religion_name = 'OTHERS' then cr. other\r\n" + 
						"else mr.religion_name end as religion_name,\r\n" + 
						"cr.created_by,\r\n" + 
						"TO_CHAR(cr.created_date ,'DD-MON-YYYY') as created_date,  \r\n" + 
						"cr.modified_by,\r\n" + 
						"TO_CHAR(cr.modified_date,'DD-MON-YYYY') as modified_date\r\n" + 
						"FROM tb_psg_change_religion_jco cr \r\n" + 
						"LEFT JOIN tb_psg_mstr_religion mr ON cr.religion = mr.religion_id \r\n" + 
						"inner join tb_psg_census_jco_or_p pc on cr.jco_id=pc.id and pc.status in ('1','5') \r\n" + 
						"WHERE cr.jco_id =? and cr.status in (1,2)  order by cr.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,religion_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("authority"));//0
						list.add(rs.getString("date_of_authority"));//1
						list.add(rs.getString("religion_name"));//2
					    list.add(rs.getString("created_by"));//3
						list.add(rs.getString("created_date"));//4
						list.add(rs.getString("modified_by"));//5
						list.add(rs.getString("modified_date"));//6
						
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
