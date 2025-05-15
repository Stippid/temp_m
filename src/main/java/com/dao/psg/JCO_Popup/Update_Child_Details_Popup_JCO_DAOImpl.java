package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Child_Details_Popup_JCO_DAOImpl implements Update_Child_Details_Popup_JCO_DAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> UpdateChildDetails_PopUp_JCO(int updatechild_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				
				q=" select ch.id,ch.type,ch.name,PGP_SYM_DECRYPT(ch.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,ch.adoted,\r\n" + 
						"ltrim(TO_CHAR(ch.date_of_adpoted,'dd-MON-yyyy'),'0')  as date_of_adpoted,\r\n" + 
						"ltrim(TO_CHAR(ch.date_of_birth,'dd-MON-yyyy'),'0') as date_of_birth,ch.modified_by,\r\n" + 
						"ltrim(TO_CHAR(ch.modified_date,'dd-MON-yyyy'),'0') as modified_date,ch.created_by,\r\n" + 
						"ltrim(TO_CHAR(ch.created_date,'dd-MON-yyyy'),'0') as created_date,\r\n" + 
						"PGP_SYM_DECRYPT(ch.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td.label as relationship \r\n" + 
						"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
						"tb_psg_census_children_jco ch on p.id = ch.jco_id and p.status in ('1','5') \r\n" + 
						"inner join  t_domain_value td on td.codevalue=cast(ch.relationship as char) and td.domainid='CHILD_RELATIONSHIP'\r\n" + 
						"where ch.jco_id =? and ch.status in (1,2) order by ch.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,updatechild_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();  
					System.err.println("MISO CHILD HISTORY" + stmt);
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("name"));
						list.add(rs.getString("date_of_birth"));
						list.add(rs.getString("relationship"));
						list.add(rs.getString("adoted"));
						list.add(rs.getString("date_of_adpoted"));
						list.add(rs.getString("aadhar_no"));
						list.add(rs.getString("pan_no"));
						list.add(rs.getString("created_by"));
						list.add(rs.getString("created_date"));
						list.add(rs.getString("modified_by"));
						list.add(rs.getString("modified_date"));
						
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
