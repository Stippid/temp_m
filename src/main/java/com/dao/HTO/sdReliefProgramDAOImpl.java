package com.dao.HTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class sdReliefProgramDAOImpl implements sdReliefProgramDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Map<String, Object>> getVehDeyailsTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String sus_no, String veh_type, HttpSession sessionUserId) {

		String SearchValue = GenerateQueryWhere_SQL(search, sus_no,veh_type);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(veh_type.equals("A")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'  "+SearchValue+" \r\n"
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11  " + " limit " + pageL + " OFFSET " + startPage;

			}else if(veh_type.equals("B")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'  "+SearchValue+" \r\n"
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11  " + " limit " + pageL + " OFFSET " + startPage;

			} else if(veh_type.equals("C")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'  "+SearchValue+" \r\n"
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11  " + " limit " + pageL + " OFFSET " + startPage;

			}
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt, search, sus_no,veh_type);
			ResultSet rs = stmt.executeQuery();
			System.out.println("tableeee:\n" + stmt);
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

	@Override
	public long getVehDeyailsTableCount(String search, String orderColunm, String orderType, String sus_no, String veh_type,
			HttpSession sessionUserId) {
		String SearchValue = GenerateQueryWhere_SQL(search, sus_no,veh_type);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			if(veh_type.equals("A")) {
				q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'   "+SearchValue+"\r\n"
						+ "  GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";

			}else if(veh_type.equals("B")) {
				q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'   "+SearchValue+"\r\n"
						+ "  GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";

			} else if(veh_type.equals("C")) {
				q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "      CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "     ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "              cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
						+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
						+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
						+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
						+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
						+ "  WHERE   bd.veh_cat = 'B'   "+SearchValue+"\r\n"
						+ "  GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";

			}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt, search, sus_no,veh_type);

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
		return total;
	}

	public String GenerateQueryWhere_SQL(String search, String sus_no, String veh_type) {

		String SearchValue = "";

		if (!search.equals("")) { // for Input Filter
			SearchValue = " and  ";

			SearchValue += "( upper(b.mct_nomen) like ? or cast(bd.mct as character varying) like ? or upper(part.ba_no) like ? "
					+ "or cast(bd.engine_no as character varying) like ? or upper(bd.chasis_no) like ? ) ";
		}

		if (!sus_no.equals("")) {
			SearchValue += " and  part.sus_no = ?  ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhere_SQL(PreparedStatement stmt, String search, String sus_no, String veh_type) {
		int flag = 0;
		try {
			if (!search.equals("")) {

				flag += 1;
				stmt.setString(flag, search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase() + "%");

			}

			if (!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toUpperCase());

			}

		} catch (Exception e) {
		}

		return stmt;

	}
	
	@Override
	public long getPersonnelDetailsCount(String search, String orderColunm, String orderType, String sus_no, HttpSession sessionUserId) {

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";
			if (!sus_no.equals("")) {
				SearchValue += " and  comm.unit_sus_no = ?  ";
			}

				q = "select count (app.*) from(select  comm.personnel_no,comm.name,rk.description from tb_psg_trans_proposed_comm_letter comm \r\n"
						+ "left join CUE_TB_PSG_RANK_APP_MASTER rk on rk.id= comm.rank \r\n"
						+ "where upper(rk.level_in_hierarchy) = 'RANK' \r\n"
						+ "and rk.parent_code ='0' and rk.code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') \r\n"
						+ "and rk.status_active = 'Active' " + SearchValue+") app ";

		
			PreparedStatement stmt = conn.prepareStatement(q);

			int flag = 0;
			if (!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toUpperCase());
			}
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
		return total;
	}


	@Override
	public List<Map<String, Object>> getPersonnelDetailsTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String sus_no, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			String SearchValue = "";

			if (!sus_no.equals("")) {
				SearchValue += " and  comm.unit_sus_no = ?  ";
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

				q = "select  comm.personnel_no,comm.name,rk.description from tb_psg_trans_proposed_comm_letter comm \r\n"
						+ "left join CUE_TB_PSG_RANK_APP_MASTER rk on rk.id= comm.rank \r\n"
						+ "where upper(rk.level_in_hierarchy) = 'RANK' \r\n"
						+ "and rk.parent_code ='0' and rk.code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') \r\n"
						+ "and rk.status_active = 'Active'" + SearchValue +" limit " + pageL + " OFFSET " + startPage;

		
			stmt = conn.prepareStatement(q);
			int flag = 0;
			if (!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toUpperCase());
			}
			
			ResultSet rs = stmt.executeQuery();
			System.out.println("personnel data:\n" + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String RadioButton = "<input class='nrRadioButton' type='radio' id='" + rs.getString("personnel_no")
		        + "' name='personnelRadio' value='" + rs.getString("personnel_no")
		        + "' data-personnel-id='" + rs.getString("personnel_no") + "' onclick='handleRadioClick(this);' />";
				String radio = "";
				radio += RadioButton;
				columns.put("radio", radio); 
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

	
	
	
	
	
	








	
	
	@Override
	public ArrayList<ArrayList<String>> getViewVoucherUnits(String sus_no, HttpSession sessionUserId) {

		String  role = sessionUserId.getAttribute("role").toString();
		Connection conn = null;
		String q = "";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		try {
			String pageL = "";

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct rplc_by_unit_sus_no,sus_no,period_from,period_to,auth_let_no,date1 from tb_Miso_Orbat_Relief_Prgm where sus_no = ? and (miso_status is null or miso_status!='1')  and sd_status='1' ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no.toUpperCase());
			ResultSet rs = stmt.executeQuery();
			System.out.println("view --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				list.add(rs.getString("rplc_by_unit_sus_no"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("period_from"));
				list.add(rs.getString("period_to"));
				list.add(rs.getString("auth_let_no"));
				list.add(rs.getString("date1"));
				alist.add(list);
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
		return alist;
	}




	@Override
	public ArrayList<ArrayList<String>> getViewVoucherData(String sus_no, String ba_no,HttpSession sessionUserId) {

		String  role = sessionUserId.getAttribute("role").toString();
		Connection conn = null;
		String q = "";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		try {
			String pageL = "";

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT distinct on (part.ba_no) part.ba_no,  b.mct_nomen, \r\n"
					+ " bd.engine_no,bd.chasis_no\r\n"
					+ "  FROM tb_tms_mvcr_parta_dtl part\r\n"
					+ "  INNER JOIN tb_tms_banum_dirctry bd  ON part.ba_no = bd.ba_no\r\n"
					+ "  INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id  AND b.type_of_veh::text = 'B'::text\r\n"
					+ "  LEFT JOIN tb_tms_mct_slot_master s  ON s.prf_code = b.prf_code\r\n"
					+ "  LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a  ON a.mct_main_id = b.mct_main_id\r\n"
					+ "  WHERE bd.veh_cat = 'B' and part.ba_no IN (?)  and  part.sus_no = ?   GROUP BY 1,2,3,4 limit ALL OFFSET 0 ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, ba_no.toUpperCase());
			stmt.setString(2, sus_no.toUpperCase());
			ResultSet rs = stmt.executeQuery();
			System.out.println("view --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				list.add(rs.getString("ba_no"));
				list.add(rs.getString("mct_nomen"));
				list.add(rs.getString("engine_no"));
				list.add(rs.getString("chasis_no"));

				alist.add(list);
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
		return alist;
	}
	
	@Override
	public long getOCPrepareCount(String search, String orderColunm, String orderType, String sus_no, String veh_type,
	String voucher_status, HttpSession sessionUserId) {
	String SearchValue = GenerateQueryWhere_SQL(search, sus_no,veh_type);
	String   role = sessionUserId.getAttribute("role").toString();
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
	conn = dataSource.getConnection();

	String status = "";

	if(voucher_status.equals("-1")) {
	status = " and part.ba_no NOT IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('0','1','2')) ";
	} else if (voucher_status.equals("0")) {
	status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('0')) ";
	} else if (voucher_status.equals("1")) {
	status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('1')) ";
	} else if (voucher_status.equals("2")) {
	status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('2')) ";
	}

	// if((voucher_status.equals("-1") && role.equals("unit_deo")) || (voucher_status.equals("0") && role.equals("oc_adv_party"))) {

	if(veh_type.equals("A")) {
	q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
	+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
	+ "         ELSE 'UNSV'\r\n"
	+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
	+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
	+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
	+ "   FROM tb_tms_mvcr_parta_dtl part\r\n"
	+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
	+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
	+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
	+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
	+ "   WHERE bd.veh_cat = 'B' " + status +SearchValue+"\r\n"
	+ "   GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";

	}else if(veh_type.equals("B")) {
	q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
	+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
	+ "         ELSE 'UNSV'\r\n"
	+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
	+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
	+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
	+ "   FROM tb_tms_mvcr_parta_dtl part\r\n"
	+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
	+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
	+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
	+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
	+ "   WHERE bd.veh_cat = 'B' " + status +SearchValue+"\r\n"
	+ "   GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";

	} else if(veh_type.equals("C")) {
	q = "select count (app.*) from(SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
	+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
	+ "         ELSE 'UNSV'\r\n"
	+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
	+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
	+ " bd.engine_no,bd.chasis_no,bd.mct\r\n"
	+ "   FROM tb_tms_mvcr_parta_dtl part\r\n"
	+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
	+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
	+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
	+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
	+ "   WHERE bd.veh_cat = 'B' " + status + SearchValue+"\r\n"
	+ "   GROUP BY 1,2,3,4,5,6,7, 8 ,9,10,11 ) app ";
	}

	// }
	PreparedStatement stmt = conn.prepareStatement(q);
	stmt = setQueryWhere_SQL(stmt, search, sus_no,veh_type);

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
	return total;
	}








	@Override
	public List<Map<String, Object>> getOCPrepareTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, String sus_no, String veh_type, String voucher_status, HttpSession sessionUserId) {

		String SearchValue = GenerateQueryWhere_SQL(search, sus_no, veh_type);
		String role = sessionUserId.getAttribute("role").toString();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			String status = "";

			if (voucher_status.equals("-1")) {
				status = " and part.ba_no NOT IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('0','1','2')) ";
			} else if (voucher_status.equals("0")) {
				status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('0')) ";
			} else if (voucher_status.equals("1")) {
				status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('1')) ";
			} else if (voucher_status.equals("2")) {
				status = " and part.ba_no IN (SELECT ba_no FROM TB_ORBAT_OC_VOUCHER WHERE voucher_status IN ('2')) ";
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			// if((voucher_status.equals("-1") && role.equals("unit_deo")) ||
			// (voucher_status.equals("0") && role.equals("oc_adv_party"))) {

			if (veh_type.equals("A")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "         ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n" + "   FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
						+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
						+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
						+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
						+ "   WHERE     bd.veh_cat = 'B' " + status + SearchValue
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11 limit " + pageL + " OFFSET " + startPage;

			} else if (veh_type.equals("B")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "         ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n" + "   FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
						+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
						+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
						+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
						+ "   WHERE bd.veh_cat = 'B' " + status + SearchValue
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11 limit " + pageL + " OFFSET " + startPage;

			} else if (veh_type.equals("C")) {
				q = "SELECT distinct on (part.ba_no) part.ba_no,   b.mct_nomen, bd.veh_cat, s.group_name AS veh_type,part.classification AS classification,\r\n"
						+ "           CASE WHEN part.ser_status is null or part.ser_status = '0' THEN 'SERV'\r\n"
						+ "         ELSE 'UNSV'\r\n"
						+ "END AS serv_unsv, round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1)) * 0),0) AS kms_run,\r\n"
						+ "                           cast(right(cast(((100 + cast(to_char(now(),'yy') AS integer)) - cast(substr(part.ba_no,1,2) AS integer)) AS varchar),2) AS integer) + 1 + 0 AS vintage,\r\n"
						+ " bd.engine_no,bd.chasis_no,bd.mct\r\n" + "   FROM tb_tms_mvcr_parta_dtl part\r\n"
						+ "   INNER JOIN tb_tms_banum_dirctry bd   ON part.ba_no = bd.ba_no\r\n"
						+ "   INNER JOIN tb_tms_mct_main_master b ON substr(mct::varchar,1,4) = b.mct_main_id   AND b.type_of_veh::text = 'B'::text\r\n"
						+ "   LEFT JOIN tb_tms_mct_slot_master s   ON s.prf_code = b.prf_code\r\n"
						+ "   LEFT JOIN tms_vehicle_status_a_b_c_with_ue_uh a   ON a.mct_main_id = b.mct_main_id\r\n"
						+ "   WHERE     bd.veh_cat = 'B' " + status + SearchValue
						+ " GROUP BY 1,2,3,4,5,6,7, 8,9,10 ,11   limit " + pageL + " OFFSET " + startPage;

			}
			// }
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhere_SQL(stmt, search, sus_no, veh_type);
			System.out.println("tableeee:\n" + stmt);
			ResultSet rs = stmt.executeQuery();
			System.out.println("tableeee:\n" + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String View = "onclick=\"   if (confirm('Are You Sure You Want to View Data for BA No.:"
						+ rs.getString("ba_no") + "') ){viewDataPopUp('" + rs.getString("ba_no")
						+ "')}else{ return false;}\"";
				String f1 = "<i class='fa fa-eye' " + View + " title='VIEW'></i>";

				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getString("ba_no")
						+ "' name='cbox' onchange='checkbox_count(this,\"" + rs.getString("ba_no") + "\");' />";
				String CheckboxId = "<input   type='hidden' id='id" + rs.getString("ba_no") + "' name='id"
						+ rs.getString("ba_no") + "' value='" + rs.getString("ba_no") + "'     />";

				String Printreport = "onclick=\"   if (confirm('Are You Sure You Want to Download This Report ?') ){print_report('"
						+ rs.getString("ba_no") + "')}else{ return false;}\"";
				String f2 = "<i class='fa fa-download'   " + Printreport + " title='Download Report'></i>";

				String action = f1 + f2;

				String checkbox = "";
				checkbox += Checkbox;
				checkbox += CheckboxId;
				columns.put("checkbox", checkbox);
				columns.put("action", action);

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
