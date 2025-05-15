package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Nok_Popup_JCO_DAOImpl implements Nok_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Nok_PopUp_JCO(int nok_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				
				q="SELECT nok.id, nok.authority, ltrim(TO_CHAR(nok.date_authority  ,'DD-MON-YYYY'),'0') as date_authority, nok.created_by,\r\n" + 
						"ltrim(TO_CHAR(r.created_date,'DD-MON-YYYY'),'0') AS created_date,\r\n" + 
						"nok.modified_by, \r\n" + 
						"ltrim(TO_CHAR(r.modified_date,'DD-MON-YYYY'),'0') as modified_date, \r\n" + 
						"PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no, nok.nok_name, nok.nok_near_railway_station, nok.nok_pin, nok.nok_rural_urban_semi, \r\n" + 
						"nok.nok_village, \r\n" + 
						"case when c.name = 'OTHERS' then nok.ctry_other\r\n" + 
						"else c.name end as Country,\r\n" + 
						"case when s.state_name = 'OTHERS' then nok.st_other\r\n" + 
						"else s.state_name end as State,\r\n" + 
						"case when d.district_name = 'OTHERS' then nok.dist_other\r\n" + 
						"else  d.district_name  end as District,\r\n" + 
						"case when city.city_name = 'OTHERS' then nok.tehsil_other\r\n" + 
						"else  city.city_name  end as Tehsil,\r\n" + 
						"case when r.relation_name = 'OTHERS' then nok.relation_other\r\n" + 
						"else  r.relation_name  end as Relation\r\n" + 
						"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
						"tb_psg_census_nok_jco nok on p.id = nok.jco_id and p.status in ('1','5') \r\n" + 
						"INNER JOIN tb_psg_mstr_country c ON nok.nok_country = c.id\r\n" + 
						"INNER JOIN tb_psg_mstr_state s ON nok.nok_state = s.state_id\r\n" + 
						"INNER JOIN tb_psg_mstr_district d ON nok.nok_district = d.district_id\r\n" + 
						"INNER JOIN tb_psg_mstr_city city ON cast(nok.nok_tehsil  as integer)= city.city_id \r\n" + 
						"INNER JOIN tb_psg_mstr_relation r ON nok.nok_relation= r.id\r\n" + 
						"WHERE nok.jco_id=? and nok.status in (1,2) order by nok.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,nok_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("authority"));//0
						list.add(rs.getString("date_authority"));//1
						list.add(rs.getString("nok_name"));//2
						list.add(rs.getString("nok_mobile_no"));//3
						list.add(rs.getString("Relation"));//4
						list.add(rs.getString("nok_village"));//5
						list.add(rs.getString("State"));//6
						list.add(rs.getString("District"));//7
						list.add(rs.getString("Tehsil"));//8
						list.add(rs.getString("nok_pin"));//9
						list.add(rs.getString("Country"));//10
						list.add(rs.getString("nok_near_railway_station"));//11
						list.add(rs.getString("nok_rural_urban_semi"));//12
						list.add(rs.getString("created_by"));//13
						list.add(rs.getString("created_date"));//14
						list.add(rs.getString("modified_by"));//15
						list.add(rs.getString("modified_date"));//16
						
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
