package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Update_Battel_And_Physical_Casualty_Popup_JCO_DAOImpl implements Update_Battel_And_Physical_Casualty_Popup_JCO_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Update_Battel_And_Physical_Casualty_JCO(int battel_physical_casualty_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select  pc.id,\r\n" + 
							"					pc.authority, \r\n" + 
							"					ltrim(TO_CHAR(pc.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
							"					tdc.label as classification_of_casuality,\r\n" + 
							"					pc.name_of_operation,\r\n" + 
							"					ltrim(TO_CHAR(pc.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality,\r\n" + 
							"					pc.exact_place,\r\n" + 
							"					pc.whether_on,\r\n" + 
							"					pc.unit, \r\n" + 
							"					td.label as nature_of_casuality, \r\n" + 
							"					c1.casuality1,\r\n" + 
							"					c2.casuality2,\r\n" + 
							"					c3.casuality3,\r\n" + 
							"					pc.village, \r\n" + 
							"					pc.pin, \r\n" + 
							"					pc.diagnosis,\r\n" + 
							"					pc.desc_others, \r\n" + 
							"					cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name, \r\n" + 
							"					case when con.name = 'OTHERS' then pc.country_other \r\n" + 
							"					else con.name end as country , \r\n" + 
							"					case when st.state_name = 'OTHERS' then pc.state_other  \r\n" + 
							"					else st.state_name end as state ,\r\n" + 
							"					case when dis.district_name = 'OTHERS' then pc.district_other \r\n" + 
							"					else dis.district_name end as district , \r\n" + 
							"					case when te.city_name = 'OTHERS' then pc.tehsil_other  \r\n" + 
							"					else te.city_name end as tehsil ,\r\n" + 
							"					(select tv.label from t_domain_value tv where tv.domainid=pc.cause_of_casuality and tv.codevalue=pc.description) as description, \r\n" + 
							"					pc.created_by,  \r\n" + 
							"					ltrim(TO_CHAR(pc.created_on ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
							"					pc.modify_by,\r\n" + 
							"					ltrim(TO_CHAR(pc.modify_on ,'DD-MON-YYYY'),'0') as modify_on\r\n" + 
							"					from tb_psg_census_battle_physical_casuality_jco pc\r\n" + 
							"					inner join  orbat_view_cmd cm on cm.sus_no=pc.command  \r\n" + 
							"					inner join orbat_view_corps co on co.sus_no=pc.corps_area\r\n" + 
							"					inner join orbat_view_div di on di.sus_no=pc.div_subarea\r\n" + 
							"					inner join orbat_view_bde bd on bd.sus_no=pc.bde \r\n" + 
							"					left join tb_psg_mstr_casuality1 c1 on c1.id = cast(pc.cause_of_casuality_1 as integer) \r\n" + 
							"					left join tb_psg_mstr_casuality2 c2 on c2.id = cast(pc.cause_of_casuality_2 as integer)\r\n" + 
							"					left join tb_psg_mstr_casuality3 c3 on c3.id = cast(pc.cause_of_casuality_3 as integer)\r\n" + 
							"					inner join t_domain_value td on td.codevalue=pc.nature_of_casuality  and td.domainid='NATURE_OF_CASUALITY'\r\n" + 
							"					inner join tb_psg_mstr_country con on con.id=cast(pc.country as integer)\r\n" + 
							"					inner join tb_psg_mstr_state st on st.state_id=cast(pc.state as integer) \r\n" + 
							"					inner join tb_psg_mstr_district dis on dis.district_id=cast(pc.district as integer) \r\n" + 
							"					inner join tb_psg_mstr_city te on te.city_id=cast(pc.tehsil as integer)\r\n" + 
							"					inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" + 
							"					inner join tb_psg_census_jco_or_p pq on pq.id=pc.jco_id and pq.status in ('1','5') \r\n" + 
							"					WHERE   jco_id =? and pc.status in (1,2)  order by id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,battel_physical_casualty_jco_id);
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_of_authority"));//1
					list.add(rs.getString("classification_of_casuality"));//2
					list.add(rs.getString("name_of_operation"));//3
					list.add(rs.getString("date_of_casuality"));//4
					list.add(rs.getString("exact_place"));//5
					list.add(rs.getString("whether_on"));//6
					list.add(rs.getString("unit"));//7
					list.add(rs.getString("village"));//8
					list.add(rs.getString("pin"));//9
					list.add(rs.getString("diagnosis"));//10
					list.add(rs.getString("desc_others"));//11
					list.add(rs.getString("cmd_name"));//12
					list.add(rs.getString("coprs_name"));//13
					list.add(rs.getString("div_name"));//14
					list.add(rs.getString("bde_name"));//15
					list.add(rs.getString("country"));//16
					list.add(rs.getString("state"));//17
					list.add(rs.getString("district"));//18
					list.add(rs.getString("tehsil"));//19
					list.add(rs.getString("description"));//20
					list.add(rs.getString("created_by"));//21
					list.add(rs.getString("created_date"));//22
					list.add(rs.getString("modify_by"));//23
					list.add(rs.getString("modify_on"));//24
					list.add(rs.getString("nature_of_casuality"));//25
					
					if (rs.getString("casuality3")!=null) {
						list.add(rs.getString("casuality3"));//26
					}
					else if (rs.getString("casuality2")!=null) {
						list.add(rs.getString("casuality2"));//26
					}
					else {
						list.add(rs.getString("casuality1"));//26
					}
					
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
