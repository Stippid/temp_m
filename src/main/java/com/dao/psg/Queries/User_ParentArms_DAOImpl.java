package com.dao.psg.Queries;

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

public class User_ParentArms_DAOImpl implements User_ParentArms_DAO{
	
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getUser_ParentArms_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			
			
			
			q = "SELECT CASE WHEN( E.CT_PART_I_II = 'CTPartI' OR E.CT_PART_I_II = 'Others' OR E.CT_PART_I_II IS NULL ) THEN 'CT Part I' END AS ct_part_i_ii,\r\n" + 
					"CASE \r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
					"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM IN('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
					"COUNT(A.ID) TOTAL\r\n" + 
					"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
					"WHERE  a.status in ('1','5')\r\n" + 
					"AND a.type_of_comm_granted <> '20'\r\n" + 
					"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
					"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
					"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
					"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
					"AND A.unit_sus_no = e.SUS_NO\r\n" + 
					"AND e.CT_PART_I_II IN ('CTPartI','Others')\r\n" + 
					"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
					"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
					"D.ARM_DESC END  \r\n" + 
					"" + SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("query "+stmt.toString());
			
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
	
	  public long getUser_ParentArms_TotalCountDtl(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (SELECT CASE WHEN( E.CT_PART_I_II = 'CTPartI' OR E.CT_PART_I_II = 'Others' OR E.CT_PART_I_II IS NULL ) THEN 'CT Part I' END AS ct_part_i_ii,\r\n" + 
						"CASE \r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
						"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM IN('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
						"COUNT(A.ID) TOTAL\r\n" + 
						"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
						"WHERE  a.status in ('1','5')\r\n" + 
						"AND a.type_of_comm_granted <> '20'\r\n" + 
						"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
						"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
						"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
						"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
						"AND A.unit_sus_no = e.SUS_NO\r\n" + 
						"AND e.CT_PART_I_II IN ('CTPartI','Others')\r\n" + 
						"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
						"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
						"D.ARM_DESC END ORDER BY 2 asc " +SearchValue +") app1";
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
  
  public List<Map<String, Object>> getUser_ParentArms_reporttbl_ctpart2Details(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			
		
			
			q = "SELECT CASE WHEN E.CT_PART_I_II = 'CTPartII' THEN 'CT Part II' END AS ct_part_i_ii,\r\n" + 
					"CASE \r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
					"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM IN ('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
					"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
					"COUNT( A.ID) TOTAL\r\n" + 
					"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
					"WHERE  a.status in ('1','5')\r\n" + 
					"AND a.type_of_comm_granted <> '20'\r\n" + 
					"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
					"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
					"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
					"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
					"AND A.unit_sus_no = e.SUS_NO\r\n" + 
					"AND e.CT_PART_I_II = 'CTPartII'  \r\n" + 
					"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
					"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
					"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
					"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
					"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
					"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
					"D.ARM_DESC END  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_ctpart2(stmt,Search);
			System.err.println("query2 "+stmt.toString());
			
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
	
	  public long getUser_ParentArms_reporttbl_ctpart2_TotalCountDtl(String Search) {
			String SearchValue = GenerateQueryWhereClause_SQL_ctpart2(Search);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (SELECT CASE WHEN E.CT_PART_I_II = 'CTPartII' THEN 'CT Part II' END AS ct_part_i_ii,\r\n" + 
						"CASE \r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
						"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM IN ('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
						"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
						"COUNT( A.ID) TOTAL\r\n" + 
						"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
						"WHERE  a.status in ('1','5')\r\n" + 
						"AND a.type_of_comm_granted <> '20'\r\n" + 
						"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
						"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
						"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
						"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
						"AND A.unit_sus_no = e.SUS_NO\r\n" + 
						"AND e.CT_PART_I_II = 'CTPartII'  \r\n" + 
						"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
						"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
						"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
						"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
						"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
						"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
						"D.ARM_DESC END " +SearchValue +") app1";
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





public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
	
    	   	
		
		q = "SELECT CASE WHEN( E.CT_PART_I_II = 'CTPartI' OR E.CT_PART_I_II = 'Others' OR E.CT_PART_I_II IS NULL ) THEN 'CT Part I' END AS ct_part_i_ii,\r\n" + 
				"CASE \r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
				"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM IN('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
				"COUNT(A.ID) TOTAL\r\n" + 
				"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
				"WHERE  a.status in ('1','5')\r\n" + 
				"AND a.type_of_comm_granted <> '20'\r\n" + 
				"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
				"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
				"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
				"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
				"AND A.unit_sus_no = e.SUS_NO\r\n" + 
				"AND e.CT_PART_I_II IN ('CTPartI','Others')\r\n" + 
				"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
				"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
				"D.ARM_DESC END  " + SearchValue ;
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
  
public ArrayList<ArrayList<String>> pdf_getUser_ParentArms_ReportDataListctpart2()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
	
    	   	
		
		q = "SELECT CASE WHEN E.CT_PART_I_II = 'CTPartII' THEN 'CT Part II' END AS ct_part_i_ii,\r\n" + 
				"CASE \r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
				"SUBSTR(e.ARM_code,1,2)|| '00' END AS arm_code, CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE d.ARM_DESC END AS arm_desc, \r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0100' THEN 1 ELSE 0 END)AS  AC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0200' THEN 1 ELSE 0 END)AS  ARTY,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0300' THEN 1 ELSE 0 END)AS  AAD,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0400' THEN 1 ELSE 0 END)AS  AVN,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0500' THEN 1 ELSE 0 END)AS  ENG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0600' THEN 1 ELSE 0 END)AS  SIG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0700' THEN 1 ELSE 0 END)AS  GR,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM IN ('0800','4600','4700') THEN 1 ELSE 0 END)AS  INF,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='0900' THEN 1 ELSE 0 END)AS  MECH,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1000' THEN 1 ELSE 0 END)AS  asc1,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1100' THEN 1 ELSE 0 END)AS  AOC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1200' THEN 1 ELSE 0 END)AS  EME,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1300' THEN 1 ELSE 0 END)AS  APS,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1400' THEN 1 ELSE 0 END)AS  AEC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1500' THEN 1 ELSE 0 END)AS  int1,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1600' THEN 1 ELSE 0 END)AS  JAG,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1700' THEN 1 ELSE 0 END)AS  APTC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='1900' THEN 1 ELSE 0 END)AS  slro,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2000' THEN 1 ELSE 0 END)AS  AMC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2100' THEN 1 ELSE 0 END)AS  ADC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2200' THEN 1 ELSE 0 END)AS  RVC,\r\n" + 
				"SUM(CASE WHEN a.PARENT_ARM ='2300' THEN 1 ELSE 0 END)AS  MF,\r\n" + 
				"COUNT( A.ID) TOTAL\r\n" + 
				"FROM tb_psg_trans_proposed_comm_letter a, TB_MISO_ORBAT_UNT_DTL e,  TB_MISO_ORBAT_ARM_CODE d\r\n" + 
				"WHERE  a.status in ('1','5')\r\n" + 
				"AND a.type_of_comm_granted <> '20'\r\n" + 
				"and a.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2')\r\n" + 
				"and a.id not in (select comm_id from tb_psg_census_secondment where seconded_to IN ('0','1','2','3','4','5','6'))\r\n" + 
				"AND D.ARM_CODE = SUBSTR(e.ARM_code,1,2)|| '00' \r\n" + 
				"AND e.STATUS_SUS_NO = 'Active' \r\n" + 
				"AND A.unit_sus_no = e.SUS_NO\r\n" + 
				"AND e.CT_PART_I_II = 'CTPartII'  \r\n" + 
				"GROUP BY  E.CT_PART_I_II,  CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN '0010'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN '0020'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN '0030'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN '0040' ELSE\r\n" + 
				"SUBSTR(e.ARM_code,1,2)|| '00' END ,CASE\r\n" + 
				"WHEN E.ARM_CODE = '0010' THEN 'MINISTRY_OF_DEFENCE'\r\n" + 
				"WHEN E.ARM_CODE = '0020' THEN 'ARMY_HEADQUARTERS'\r\n" + 
				"WHEN E.ARM_CODE = '0030' THEN 'HEADQUARTERS_FORMATION'\r\n" + 
				"WHEN E.ARM_CODE = '0040' THEN 'STATIC_HEADQUARTERS' ELSE\r\n" + 
				"D.ARM_DESC END  " + SearchValue ;
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
