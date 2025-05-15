package com.dao.psg.popup_history;

import java.math.BigInteger;
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

import com.healthmarketscience.jackcess.impl.expr.StringValue;

public class ChangeAddress_DAOImpl implements ChangeAddress_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Map<String, Object>> change_address_details(BigInteger comm_id,int census_id)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			
			
			 
		q = "\n" + 
				"select Distinct addr.id,addr.authority,addr.date_authority,addr.modified_date,addr.modified_by,addr.created_date,addr.created_by,\n" + 
				"co.name as pre_country,st.state_name as pre_state,dis.district_name as pre_district,te.city_name as pre_tehsil\n" + 
				",addr.pre_country_other,addr.pre_domicile_state_other,addr.pre_domicile_district_other,addr.pre_domicile_tesil_other \n" + 
				",coh.name as home_country,sth.state_name as home_state,dish.district_name as home_district,teh.city_name as home_tehsil\n" + 
				",addr.per_home_addr_country_other,addr.per_home_addr_state_other,addr.per_home_addr_district_other,addr.per_home_addr_tehsil_other \n" + 
				",addr.permanent_rural_urban_semi,addr.permanent_border_area,addr.permanent_village,addr.permanent_pin_code,addr.permanent_near_railway_station\n" + 
				",cop.name as present_country,stp.state_name as present_state,disp.district_name as present_district,tep.city_name as present_tehsil\n" + 
				",addr.pers_addr_country_other,addr.pers_addr_state_other,addr.pers_addr_district_other,addr.pers_addr_tehsil_other \n" + 
				",addr.present_rural_urban_semi,addr.present_village,addr.present_pin_code,addr.present_near_railway_station\n" + 
				",cos.name as spouse_country,sts.state_name as spouse_state,diss.district_name as spouse_district,tes.city_name as spouse_tehsil\n" + 
				",addr.spouse_addr_tehsil_other,addr.spouse_addr_state_other,addr.spouse_addr_district_other,addr.spouse_addr_tehsil_other \n" + 
				",addr.spouse_village,addr.spouse_pin_code,addr.spouse_near_railway_station,addr.cen_id,addr.status\n" + 
				"\n" + 
				",addr.spouse_rural_urban_semi,m.sus_no as st_sus_no,m.unit_name as st_unit_name from tb_psg_census_address addr\n" + 
				"inner join tb_psg_mstr_country co on co.id=addr.pre_country\n" + 
				"inner join tb_psg_mstr_state st on st.state_id=addr.pre_state\n" + 
				"inner join tb_psg_mstr_district dis on dis.district_id=addr.pre_district\n" + 
				"inner join tb_psg_mstr_city te on te.city_id=addr.pre_tesil\n" + 
				"inner join tb_psg_mstr_country coh on coh.id=addr.permanent_country\n" + 
				"inner join tb_psg_mstr_state sth on sth.state_id=addr.permanent_state\n" + 
				"inner join tb_psg_mstr_district dish on dish.district_id=addr.permanent_district\n" + 
				"inner join tb_psg_mstr_city teh on teh.city_id=cast(addr.permanent_tehsil as integer)\n" + 
				"inner join tb_psg_mstr_country cop on cop.id=addr.present_country\n" + 
				"inner join tb_psg_mstr_state stp on stp.state_id=addr.present_state\n" + 
				"inner join tb_psg_mstr_district disp on disp.district_id=addr.present_district\n" + 
				"inner join tb_psg_mstr_city tep on tep.city_id=cast(addr.present_tehsil as integer)\n" + 
				"left join tb_psg_mstr_country cos on cos.id=addr.spouse_country\n" + 
				"left join tb_psg_mstr_state sts on sts.state_id=addr.spouse_state\n" + 
				"left join tb_psg_mstr_district diss on diss.district_id=addr.spouse_district\n" + 
				"left join tb_psg_mstr_city tes on tes.city_id=cast(addr.spouse_tehsil as integer)\n" + 
				"left join tb_miso_orbat_unt_dtl m on m.sus_no = addr.stn_hq_sus_no and m.status_sus_no='Active'\n" + 
				"where cast(addr.comm_id as character varying)=? and addr.status in  (1,2) and addr.cen_id=? order by addr.id";
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			stmt.setInt(2,census_id);
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
}
