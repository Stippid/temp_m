package com.dao.psg.JCO_Report;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class User_ParentArms_Jco_DAOImpl implements User_ParentArms_Jco_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getUser_ParentArms_ReportDataListDetailsJco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
					"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
					"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
					"	from (\r\n" + 
					"	select \r\n" + 
					"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
					"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
					"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
					"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
					"		from (	\r\n" + 
					"			select\r\n" + 
					"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
					"			from tb_psg_census_jco_or_p c\r\n" + 
					"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
					"		) hld\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartI' \r\n" + 
					"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
					") app  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("query held user and parent " + stmt);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
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
	
	  public long getUser_ParentArms_TotalCountDtlJco(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (\r\n" + 
						" select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
						"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
						"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
						"	from (\r\n" + 
						"	select \r\n" + 
						"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
						"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
						"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
						"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
						"		from (	\r\n" + 
						"			select\r\n" + 
						"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
						"			from tb_psg_census_jco_or_p c\r\n" + 
						"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
						"		) hld\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartI' \r\n" + 
						"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
						") app" +SearchValue +") app1";
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					total = rs.getInt(1);
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
			return (long) total;
		}

	  public String GenerateQueryWhereClause_SQL(String Search) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.ct_part_i_ii) like ? or lower(app.arm_code) like ? or lower(app.arm_desc) like ? or "
 					+ " cast(app.ac as character varying) = ? or cast(app.arty as character varying) = ? or cast(app.aad as character varying) = ? or cast(app.avn as character varying) = ? or cast(app.eng as character varying) = ? or "
 					+ " cast(app.sig as character varying) = ? or cast(app.gr as character varying) = ? or cast(app.inf as character varying) = ? or cast(app.mech as character varying) = ? or cast(app.asc1 as character varying) = ? or "
 					+ " cast(app.aoc as character varying) = ? or cast(app.eme as character varying) = ? or cast(app.aps as character varying) = ? or cast(app.aec as character varying) = ? or cast(app.int1 as character varying) = ? or "
 					+ " cast(app.jag as character varying) = ? or cast(app.aptc as character varying) = ? or cast(app.slro as character varying) = ? or cast(app.amc as character varying) = ? or cast(app.adc as character varying) = ? or "
 					+ " cast(app.rvc as character varying) = ? or cast(app.mf as character varying) = ? or "
 					+ " cast(app.total as character varying) = ? "
 					+ " )";
 		}
  	
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				
    		}	
				
 		}catch (Exception e) {}
 		return stmt;
 	}
  
//-------------------------ct part 2
  
  public List<Map<String, Object>> getUser_ParentArms_reporttbl_ctpart2DetailsJco(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_ctpart2(Search);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
					"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
					"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
					"	from (\r\n" + 
					"	select \r\n" + 
					"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
					"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
					"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
					"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
					"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
					"		from (	\r\n" + 
					"			select\r\n" + 
					"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
					"			from tb_psg_census_jco_or_p c\r\n" + 
					"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
					"		) hld\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartII' \r\n" + 
					"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
					") app  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_ctpart2(stmt,Search);
			System.err.println("query held user \n " + stmt);
		
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
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
	
	  public long getUser_ParentArms_reporttbl_ctpart2_TotalCountDtlJco(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL_ctpart2(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (\r\n" + 
						" select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
						"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
						"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
						"	from (\r\n" + 
						"	select \r\n" + 
						"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
						"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
						"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
						"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
						"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
						"		from (	\r\n" + 
						"			select\r\n" + 
						"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
						"			from tb_psg_census_jco_or_p c\r\n" + 
						"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
						"		) hld\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartII' \r\n" + 
						"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
						") app" +SearchValue +") app1";
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL_ctpart2(stmt,Search);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					total = rs.getInt(1);
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
			return (long) total;
		}

	  public String GenerateQueryWhereClause_SQL_ctpart2(String Search) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(app.ct_part_i_ii) like ? or lower(app.arm_code) like ? or lower(app.arm_desc) like ? or "
					+ " cast(app.ac as character varying) = ? or cast(app.arty as character varying) = ? or cast(app.aad as character varying) = ? or cast(app.avn as character varying) = ? or cast(app.eng as character varying) = ? or "
					+ " cast(app.sig as character varying) = ? or cast(app.gr as character varying) = ? or cast(app.inf as character varying) = ? or cast(app.mech as character varying) = ? or cast(app.asc1 as character varying) = ? or "
					+ " cast(app.aoc as character varying) = ? or cast(app.eme as character varying) = ? or cast(app.aps as character varying) = ? or cast(app.aec as character varying) = ? or cast(app.int1 as character varying) = ? or "
					+ " cast(app.jag as character varying) = ? or cast(app.aptc as character varying) = ? or cast(app.slro as character varying) = ? or cast(app.amc as character varying) = ? or cast(app.adc as character varying) = ? or "
					+ " cast(app.rvc as character varying) = ? or cast(app.mf as character varying) = ? or "
					+ " cast(app.total as character varying) = ? "
					+ " )";
		}
	
	 return SearchValue;
	}


public PreparedStatement setQueryWhereClause_SQL_ctpart2(PreparedStatement stmt,String Search) {
		int flag = 0;
		try {
  		if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				
  			}	
		}catch (Exception e) {}
		return stmt;
	}





public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataList_Jco()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
	
    	   	
		
		q="select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
				"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
				"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
				"	from (\r\n" + 
				"	select \r\n" + 
				"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
				"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
				"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
				"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
				"		from (	\r\n" + 
				"			select\r\n" + 
				"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
				"			from tb_psg_census_jco_or_p c\r\n" + 
				"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
				"		) hld\r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartI' \r\n" + 
				"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
				") app " + SearchValue ;
			stmt=conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("ct_part_i_ii"));//1
				list.add(rs.getString("arm_code"));//2
				list.add(rs.getString("arm_desc"));//3
				list.add(rs.getString("ac"));//4
				list.add(rs.getString("arty"));//5
				list.add(rs.getString("aad"));//6
				list.add(rs.getString("avn"));//7
				list.add(rs.getString("eng"));//8
				list.add(rs.getString("sig"));//9
				list.add(rs.getString("gr"));//10
				list.add(rs.getString("inf"));//11
				
				list.add(rs.getString("mech"));//12
				list.add(rs.getString("asc1"));//13
				list.add(rs.getString("aoc"));//14
				list.add(rs.getString("eme"));//15
				list.add(rs.getString("aps"));//16
				
				list.add(rs.getString("aec"));//17
				list.add(rs.getString("int1"));//18
				list.add(rs.getString("jag"));//19
				list.add(rs.getString("aptc"));//20
				list.add(rs.getString("slro"));//21
				list.add(rs.getString("amc"));//22
				list.add(rs.getString("adc"));//23
				list.add(rs.getString("rvc"));//24
				list.add(rs.getString("mf"));//25
				list.add(rs.getString("total"));//26
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}
  
public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataListctpart2_Jco()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
	
    	   	
		
		q="select app.ct_part_i_ii,app.arm_code,app.arm_desc,\r\n" + 
				"	app.ac,app.arty,app.aad,app.avn,app.eng,app.sig,app.gr,app.inf,app.mech,app.asc1,app.aoc,app.eme,app.aps,app.aec,app.int1,app.jag,app.aptc,app.slro,\r\n" + 
				"	app.amc,app.adc,app.rvc,app.mf,app.total\r\n" + 
				"	from (\r\n" + 
				"	select \r\n" + 
				"		orb.ct_part_i_ii,hld.arm_code,hld.arm_desc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMOURED CORPS') as ac,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARTILLERY') as arty,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AIR DEFENCE') as aad,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY AVIATION') as avn,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ENGINEERS') as eng,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'SIGNALS') as sig,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GORKHA') as gr,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INFANTRY') as inf,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MECHANISED INFANTRY') as mech,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY SERVICE CORPS') as asc1,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY ORDNANCE CORPS') as aoc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as eme,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY POSTAL SERVICES') as aps,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY EDUCATION CORPS') as aec,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'INTELLIGENCE CORPS') as int1,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'JUDGE ADVOCATE GENERAL') as jag,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as aptc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'GL & RASO') as slro,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY MEDICAL CORPS') as amc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'ARMY DENTAL CORPS') as adc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'REMOUNT AND VETERINARY CORPS') as rvc,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc = 'MILITARY FARMS') as mf,\r\n" + 
				"		count(hld.unit_sus_no) filter (where hld.arm_desc in ('ARMOURED CORPS','ARTILLERY','ARMY AIR DEFENCE','ARMY AVIATION','ENGINEERS','SIGNALS','GORKHA','INFANTRY','MECHANISED INFANTRY',\r\n" + 
				"															  'ARMY SERVICE CORPS','ARMY ORDNANCE CORPS','ELECTRONICS AND MECHANICAL ENGINEERS','ARMY POSTAL SERVICES','ARMY EDUCATION CORPS',\r\n" + 
				"															  'INTELLIGENCE CORPS','JUDGE ADVOCATE GENERAL','ARMY PHYSICAL TRAINING CORPS','GL & RASO','ARMY MEDICAL CORPS',\r\n" + 
				"															  'ARMY DENTAL CORPS','REMOUNT AND VETERINARY CORPS','MILITARY FARMS')) as total \r\n" + 
				"		from (	\r\n" + 
				"			select\r\n" + 
				"				 c.unit_sus_no,a.arm_code,a.arm_desc \r\n" + 
				"			from tb_psg_census_jco_or_p c\r\n" + 
				"			inner join tb_miso_orbat_arm_code a on a.arm_code=c.arm_service and c.status in ('1','5') \r\n" + 
				"		) hld\r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.ct_part_i_ii != '' and orb.ct_part_i_ii ='CTPartII' \r\n" + 
				"group by orb.ct_part_i_ii,hld.arm_code,hld.arm_desc order by orb.ct_part_i_ii\r\n" + 
				") app " + SearchValue ;
			stmt=conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("ct_part_i_ii"));//1
				list.add(rs.getString("arm_code"));//2
				list.add(rs.getString("arm_desc"));//3
				list.add(rs.getString("ac"));//4
				list.add(rs.getString("arty"));//5
				list.add(rs.getString("aad"));//6
				list.add(rs.getString("avn"));//7
				list.add(rs.getString("eng"));//8
				list.add(rs.getString("sig"));//9
				list.add(rs.getString("gr"));//10
				list.add(rs.getString("inf"));//11
				
				list.add(rs.getString("mech"));//12
				list.add(rs.getString("asc1"));//13
				list.add(rs.getString("aoc"));//14
				list.add(rs.getString("eme"));//15
				list.add(rs.getString("aps"));//16
				
				list.add(rs.getString("aec"));//17
				list.add(rs.getString("int1"));//18
				list.add(rs.getString("jag"));//19
				list.add(rs.getString("aptc"));//20
				list.add(rs.getString("slro"));//21
				list.add(rs.getString("amc"));//22
				list.add(rs.getString("adc"));//23
				list.add(rs.getString("rvc"));//24
				list.add(rs.getString("mf"));//25
				list.add(rs.getString("total"));//26
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}

}
