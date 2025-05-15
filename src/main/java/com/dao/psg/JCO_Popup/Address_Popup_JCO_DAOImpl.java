package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class Address_Popup_JCO_DAOImpl implements Address_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Map<String, Object>> Address_PopUp_JCO(int address_jco_id)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				
				q="select  addr.id,addr.authority,addr.date_authority,addr.modified_date,addr.modified_by,addr.created_date,addr.created_by,\r\n" + 
						"co.name as pre_country,st.state_name as pre_state,dis.district_name as pre_district,te.city_name as pre_tehsil\r\n" + 
						",addr.pre_country_other,addr.pre_domicile_state_other,addr.pre_domicile_district_other,addr.pre_domicile_tesil_other \r\n" + 
						",coh.name as home_country,sth.state_name as home_state,dish.district_name as home_district,teh.city_name as home_tehsil\r\n" + 
						",addr.per_home_addr_country_other,addr.per_home_addr_state_other,addr.per_home_addr_district_other,addr.per_home_addr_tehsil_other \r\n" + 
						",addr.permanent_rural_urban_semi,addr.permanent_border_area,addr.permanent_village,addr.permanent_pin_code,addr.permanent_near_railway_station\r\n" + 
						",cop.name as present_country,stp.state_name as present_state,disp.district_name as present_district,tep.city_name as present_tehsil\r\n" + 
						",addr.pers_addr_country_other,addr.pers_addr_state_other,addr.pers_addr_district_other,addr.pers_addr_tehsil_other \r\n" + 
						",addr.present_rural_urban_semi,addr.present_village,addr.present_pin_code,addr.present_near_railway_station\r\n" + 
						",cos.name as spouse_country,sts.state_name as spouse_state,diss.district_name as spouse_district,tep.city_name as spouse_tehsil\r\n" + 
						",addr.spouse_addr_tehsil_other,addr.spouse_addr_state_other,addr.spouse_addr_district_other,addr.spouse_addr_tehsil_other \r\n" + 
						",addr.spouse_village,addr.spouse_pin_code,addr.spouse_near_railway_station,addr.status\r\n" + 
						",addr.spouse_rural_urban_semi from  tb_psg_census_jco_or_p p inner join \r\n" + 
						"tb_psg_census_address_jco addr on p.id = addr.jco_id and p.status in ('1','5') \r\n" + 
						"inner join tb_psg_mstr_country co on co.id=addr.pre_country \r\n" + 
						"inner join tb_psg_mstr_state st on st.state_id=addr.pre_state\r\n" + 
						"inner join tb_psg_mstr_district dis on dis.district_id=addr.pre_district\r\n" + 
						"inner join tb_psg_mstr_city te on te.city_id=addr.pre_tesil\r\n" + 
						"inner join tb_psg_mstr_country coh on coh.id=addr.permanent_country\r\n" + 
						"inner join tb_psg_mstr_state sth on sth.state_id=addr.permanent_state\r\n" + 
						"inner join tb_psg_mstr_district dish on dish.district_id=addr.permanent_district \r\n" + 
						"inner join tb_psg_mstr_city teh on teh.city_id=cast(addr.permanent_tehsil as integer)\r\n" + 
						"inner join tb_psg_mstr_country cop on cop.id=addr.present_country\r\n" + 
						"inner join tb_psg_mstr_state stp on stp.state_id=addr.present_state\r\n" + 
						"inner join tb_psg_mstr_district disp on disp.district_id=addr.present_district\r\n" + 
						"inner join tb_psg_mstr_city tep on tep.city_id=cast(addr.permanent_tehsil as integer) \r\n" + 
						"left join tb_psg_mstr_country cos on cos.id=addr.spouse_country\r\n" + 
						"left join tb_psg_mstr_state sts on sts.state_id=addr.spouse_state \r\n" + 
						"left join tb_psg_mstr_district diss on diss.district_id=addr.spouse_district\r\n" + 
						"left join tb_psg_mstr_city tes on tes.city_id=cast(addr.spouse_tehsil as integer)\r\n" + 
						"where addr.jco_id=? and addr.status in (1,2) order by addr.id desc";
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,address_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery(); 
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
					while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
						for (int i = 1; i <= columnCount; i++) {
							if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						}

						
						list.add(columns);
						
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
			
			return list;
      }
}
