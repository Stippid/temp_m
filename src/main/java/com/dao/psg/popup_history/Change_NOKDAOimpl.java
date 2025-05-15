package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_BANK;

import com.persistance.util.HibernateUtil;

public class Change_NOKDAOimpl implements Change_NOKDAO {
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Override
	public ArrayList<ArrayList<String>> change_nok_history_dtl(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
		
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
			
							
					//q="select * from tb_psg_census_nok where (status='1' or status='2') and comm_id='281' and census_id = '397' ";
				q="SELECT DISTINCT nok.id, nok.authority, ltrim(TO_CHAR(nok.date_authority  ,'DD-MON-YYYY'),'0') as date_authority, nok.created_by, \n"
						+ " ltrim(TO_CHAR(nok.created_date,'DD-MON-YYYY'),'0') AS created_date, \n"
						+ "	nok.modified_by, \n"
						+ "ltrim(TO_CHAR(nok.modified_date,'DD-MON-YYYY'),'0') as modified_date, \n"
						+ "PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no, nok.nok_name, nok.nok_near_railway_station, nok.nok_pin, nok.nok_rural_urban_semi, \n"
						+ " nok.nok_village, \n"
						+ "case when c.name = 'OTHERS' then nok.ctry_other\n"
						+ "else c.name end as Country,\n"
						+ "case when s.state_name = 'OTHERS' then nok.st_other\n"
						+ "else s.state_name end as State,\n"
						+ "case when d.district_name = 'OTHERS' then nok.dist_other\n"
						+ "else  d.district_name  end as District,\n"
						+ "case when city.city_name = 'OTHERS' then nok.tehsil_other\n"
						+ "else  city.city_name  end as Tehsil,\n"
						+ "case when r.relation_name = 'OTHERS' then nok.relation_other\n"
						+ "else  r.relation_name  end as Relation\n"
						+ " \n"
						+ "FROM public.tb_psg_census_nok nok\n"
						+ "INNER JOIN tb_psg_mstr_country c ON nok.nok_country = c.id\n"
						+ "INNER JOIN tb_psg_mstr_state s ON nok.nok_state = s.state_id\n"
						+ "INNER JOIN tb_psg_mstr_district d ON nok.nok_district = d.district_id\n"
						+ "INNER JOIN tb_psg_mstr_city city ON cast(nok.nok_tehsil  as integer)= city.city_id \n"
						+ "INNER JOIN tb_psg_mstr_relation r ON nok.nok_relation= r.id\n"
						+ "WHERE (nok.status='1' or nok.status='2')\n"
						+ "\n"
						+ "and cast(comm_id as character varying)=?  and census_id = ? order by nok.id \n";
				
			
					
				
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
				stmt.setInt(2,census_id);
				
				
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


