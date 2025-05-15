package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Contact_Details_Popup_JCO_DAOImpl implements Contact_Details_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> ContactDetails_PopUp_JCO(int contactdetails_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				
				q="SELECT cd.id,cd.created_by,ltrim(to_char(cd.created_date,'DD-MON-YYYY'),'0') as created_date,PGP_SYM_DECRYPT(cd.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cd.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,cd.modified_by,ltrim(to_char(cd.modified_date,'DD-MON-YYYY'),'0') as modified_date,PGP_SYM_DECRYPT(cd.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,cd.status\r\n" + 
						"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
						"tb_psg_census_contact_cda_account_details_jco cd on p.id = cd.jco_id and p.status in ('1','5') \r\n" + 
						"WHERE cd.jco_id=? and cd.status in (1,2) order by cd.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,contactdetails_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						//list.add(rs.getString("id"));//0
						list.add(rs.getString("gmail"));//1
						list.add(rs.getString("nic_mail"));//2
						list.add(rs.getString("mobile_no"));//3
						list.add(rs.getString("created_by"));//5
						list.add(rs.getString("created_date"));//6
						list.add(rs.getString("modified_by"));//7
						list.add(rs.getString("modified_date"));//8
						
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
