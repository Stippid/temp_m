package com.dao.Dashboard;

import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class JCO_ORDashboardDAOImpl implements JCO_ORDashboardDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

////bisag v2 190922(new screen)

////////////////////////////////////////////////////////////////////////////////////////officer commission /////////////////////////////////////////////////////////////////////////
//
//	public ArrayList<ArrayList<String>> search_officer_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali) {
//
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		String qry = "";
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			qry = "a.id !=  0";
//			if (!cont_comd.equals("")) {
//
//				qry += "  and orb.form_code_control like ?  ";
//			}
//
//			if (!cont_corps.equals("0")) {
//
//				qry += " and  orb.form_code_control like ?  ";
//
//			}
//			if (!cont_div.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!cont_bde.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!unit_name.equals("")) {
//
//				qry += " and  orb.unit_name = ? ";
//
//			}
//
//			if (!sus_no.equals("")) {
//
//				qry += " and orb.sus_no = ? ";
//
//			}
//			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved ,\r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
//					+ "COUNT (a.id) FILTER (WHERE a.status::text IN ('1'::text,'0'::text))as total\r\n"
//					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
//					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
//					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'\r\n"
//					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
//					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
//					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
//					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
//					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
//					+ "and div.level_in_hierarchy = 'Division' \r\n"
//					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
//					+ "left join logininformation l  on l.username=a.created_by\r\n"
//					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
//					+ "left join roleinformation r  on u.role_id=r.role_id   \r\n" + " where " + qry
//					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name ";
//
//			stmt = conn.prepareStatement(q);
//			if (!qry.equals("")) {
//				int j = 1;
//				if (!cont_comd.equals("")) {
//
//					stmt.setString(j, cont_comd.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_corps.equals("0")) {
//
//					stmt.setString(j, cont_corps.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_div.equals("0")) {
//
//					stmt.setString(j, cont_div.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_bde.equals("0")) {
//
//					stmt.setString(j, cont_bde.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!unit_name.equals("")) {
//
//					stmt.setString(j, unit_name.toUpperCase());
//					j += 1;
//				}
//				if (!sus_no.equals("")) {
//
//					stmt.setString(j, sus_no.toUpperCase());
//					j += 1;
//				}
//			}
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				list.add(rs.getString("cmd_name"));// 0
//				list.add(rs.getString("coprs_name"));// 1
//				list.add(rs.getString("div_name"));// 2
//				list.add(rs.getString("bde_name"));// 3
//				list.add(rs.getString("unit_name"));// 4
//				list.add(rs.getString("approved"));// 5
//				list.add(rs.getString("pending"));// 6
//				list.add(rs.getString("TOTAL"));// 7
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
////throw new RuntimeException(e);
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}

/////////////////////////////////////////////////////////////////////////////////////////////////officer Census ///////////////////////////////////////////////////////////////////////////////////////////

//	public ArrayList<ArrayList<String>> search_officer_en_tablecen(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali) {
//
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		String qry = "";
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			qry = "a.id !=  0";
//			if (!cont_comd.equals("")) {
//
//				qry += "  and orb.form_code_control like ?  ";
//			}
//
//			if (!cont_corps.equals("0")) {
//
//				qry += " and  orb.form_code_control like ?  ";
//
//			}
//			if (!cont_div.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!cont_bde.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!unit_name.equals("")) {
//
//				qry += " and  orb.unit_name = ? ";
//
//			}
//
//			if (!sus_no.equals("")) {
//
//				qry += " and orb.sus_no = ? ";
//
//			}
//			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name,\r\n"
//					+ "count(distinct a.id) FILTER (WHERE cen.status = '1') AS approved,\r\n"
//					+ "count(distinct a.id) FILTER (WHERE cen.status = '0') AS pending,\r\n"
//					+ "COUNT (distinct a.id) FILTER (WHERE cen.status IN ('1','0'))  AS TOTAL\r\n"
//					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
//					+ "inner join  tb_psg_census_detail_p cen on cen.comm_id=a.id AND cen.created_by != 'psg|miso'\r\n"
//					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
//					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'                                                                                        \r\n"
//					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
//					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
//					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
//					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
//					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
//					+ "and div.level_in_hierarchy = 'Division' \r\n"
//					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
//					+ "left join logininformation l  on l.username=cen.created_by\r\n"
//					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
//					+ "left join roleinformation r  on u.role_id=r.role_id \r\n" + " where " + qry
//					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name ";
//
//			stmt = conn.prepareStatement(q);
//			if (!qry.equals("")) {
//				int j = 1;
//				if (!cont_comd.equals("")) {
//
//					stmt.setString(j, cont_comd.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_corps.equals("0")) {
//
//					stmt.setString(j, cont_corps.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_div.equals("0")) {
//
//					stmt.setString(j, cont_div.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_bde.equals("0")) {
//
//					stmt.setString(j, cont_bde.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!unit_name.equals("")) {
//
//					stmt.setString(j, unit_name.toUpperCase());
//					j += 1;
//				}
//				if (!sus_no.equals("")) {
//
//					stmt.setString(j, sus_no.toUpperCase());
//					j += 1;
//				}
//			}
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				list.add(rs.getString("cmd_name"));// 0
//				list.add(rs.getString("coprs_name"));// 1
//				list.add(rs.getString("div_name"));// 2
//				list.add(rs.getString("bde_name"));// 3
//				list.add(rs.getString("unit_name"));// 4
//				list.add(rs.getString("approved"));// 5
//				list.add(rs.getString("pending"));// 6
//				list.add(rs.getString("TOTAL"));// 7
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
////throw new RuntimeException(e);
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}

/////////////////////////////////////////////////////////////////////////////////////////////JCO////////////////////////////////////////////////////////////////////////////////
//
//	public ArrayList<ArrayList<String>> search_jco_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali) {
//
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		String qry = "";
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			qry = "a.id !=  0";
//			if (!cont_comd.equals("")) {
//
//				qry += "  and orb.form_code_control like ?  ";
//			}
//
//			if (!cont_corps.equals("0")) {
//
//				qry += " and  orb.form_code_control like ?  ";
//
//			}
//			if (!cont_div.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!cont_bde.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!unit_name.equals("")) {
//
//				qry += " and  orb.unit_name = ? ";
//
//			}
//
//			if (!sus_no.equals("")) {
//
//				qry += " and orb.sus_no = ? ";
//
//			}
//			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
//					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
//					+ "from tb_psg_census_jco_or_p a\r\n"
//					+ "left join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
//					+ "left join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'											\r\n"
//					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
//					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
//					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
//					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
//					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
//					+ "left join logininformation l  on l.username=a.created_by\r\n"
//					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
//					+ "left join roleinformation r  on u.role_id=r.role_id  " + " where " + qry
//					+ " group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name ";
//			stmt = conn.prepareStatement(q);
//			if (!qry.equals("")) {
//				int j = 1;
//				if (!cont_comd.equals("")) {
//
//					stmt.setString(j, cont_comd.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_corps.equals("0")) {
//
//					stmt.setString(j, cont_corps.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_div.equals("0")) {
//
//					stmt.setString(j, cont_div.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_bde.equals("0")) {
//
//					stmt.setString(j, cont_bde.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!unit_name.equals("")) {
//
//					stmt.setString(j, unit_name.toUpperCase());
//					j += 1;
//				}
//				if (!sus_no.equals("")) {
//
//					stmt.setString(j, sus_no.toUpperCase());
//					j += 1;
//				}
//			}
//
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				list.add(rs.getString("cmd_name"));// 0
//				list.add(rs.getString("coprs_name"));// 1
//				list.add(rs.getString("div_name"));// 2
//				list.add(rs.getString("bde_name"));// 3
//				list.add(rs.getString("unit_name"));// 4
//				list.add(rs.getString("approved"));// 5
//				list.add(rs.getString("pending"));// 6
//				list.add(rs.getString("TOTAL"));// 7
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
////throw new RuntimeException(e);
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}

////////////////////////////////////////////////////////////////////////////////////////////////////Civilian///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	public ArrayList<ArrayList<String>> search_civilian_en_table(String cont_comd, String cont_corps, String cont_div,
//			String cont_bde, String unit_name, String sus_no, String user_role, String sub_quali) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		String qry = "";
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			qry = "a.id !=  0";
//			if (!cont_comd.equals("")) {
//
//				qry += "   and orb.form_code_control like ?  ";
//			}
//
//			if (!cont_corps.equals("0")) {
//
//				qry += " and  orb.form_code_control like ?  ";
//
//			}
//			if (!cont_div.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!cont_bde.equals("0")) {
//
//				qry += " and  orb.form_code_control like ? ";
//			}
//
//			if (!unit_name.equals("")) {
//
//				qry += " and  orb.unit_name = ? ";
//
//			}
//
//			if (!sus_no.equals("")) {
//
//				qry += " and orb.sus_no = ? ";
//
//			}
//
//			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
//					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
//					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
//					+ "from tb_psg_civilian_dtl a\r\n"
//					+ "left join orbat_all_details_view c on c.sus_no = a.sus_no and c.status_sus_no = 'Active'\r\n"
//					+ "left join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.sus_no and orb.status_sus_no='Active'											\r\n"
//					+ "left join all_fmn_view fv  on orb.sus_no = a.sus_no\r\n"
//					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
//					+ "left join all_fmn_view fvm  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
//					+ "left join all_fmn_view div  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
//					+ "left join all_fmn_view bde  on orb.sus_no = a.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
//					+ "left join logininformation l  on l.username=a.created_by\r\n"
//					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
//					+ "left join roleinformation r  on u.role_id=r.role_id  \r\n" + " where " + qry
//					+ "  group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  \r\n"
//					+ " order by c.cmd_name";
//
//			stmt = conn.prepareStatement(q);
//			if (!qry.equals("")) {
//				int j = 1;
//				if (!cont_comd.equals("")) {
//
//					stmt.setString(j, cont_comd.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_corps.equals("0")) {
//
//					stmt.setString(j, cont_corps.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_div.equals("0")) {
//
//					stmt.setString(j, cont_div.toUpperCase() + "%");
//					j += 1;
//				}
//				if (!cont_bde.equals("0")) {
//
//					stmt.setString(j, cont_bde.toUpperCase() + "%");
//					
//					j += 1;
//				}
//				if (!unit_name.equals("")) {
//
//					stmt.setString(j, unit_name.toUpperCase());
//					j += 1;
//				}
//				if (!sus_no.equals("")) {
//
//					stmt.setString(j, sus_no.toUpperCase());
//					j += 1;
//				}
//			}
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				list.add(rs.getString("cmd_name"));// 0
//				list.add(rs.getString("coprs_name"));// 1
//				list.add(rs.getString("div_name"));// 2
//				list.add(rs.getString("bde_name"));// 3
//				list.add(rs.getString("unit_name"));// 4
//				list.add(rs.getString("approved"));// 5
//				list.add(rs.getString("pending"));// 6
//				list.add(rs.getString("TOTAL"));// 7
//
//				alist.add(list);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
////throw new RuntimeException(e);
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}

	/////////// officer
	//// bisag v2 200922(converted in data table )
	public String GenerateQueryWhereClause_SQL(String Search, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no, /* String user_role_id, */String from_date,
			String to_date, String user_role, String sub_quali) {

		String SearchValue = "";
		
		if (!Search.equals("")) { // for Input Filter
			SearchValue = " where  ";
			SearchValue += "( upper(c.cmd_name) like ? or upper(c.coprs_name) like ? or upper(c.div_name) like ? "
					+ " or upper(c.bde_name) like ? or upper(c.unit_name) like ?  ) ";
		}

		if (!cont_comd.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where orb.form_code_control like ? ";
			}
		}
		if (!cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}
		if (!cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}
		if (!cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}

		if (!unit_name.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.unit_name = ? ";
			} else {
				SearchValue += " where orb.unit_name like ?";
			}
		}

		if (!unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and orb.sus_no = ? ";
			} else {
				SearchValue += " where orb.sus_no = ? ";
			}
		}
		/*
		 * if( !user_role_id.equals("")) { if (SearchValue.contains("where")) {
		 * SearchValue += " and cast(lm.userId as character varying) = ? "; } else {
		 * SearchValue += " where cast(lm.userId as character varying) = ? "; } }
		 */
		if (!from_date.equals("") && !to_date.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  cast(a.created_date as date) >= cast(? as date) and cast(a.created_date as date) <= cast(? as date) ";
			} else {
				SearchValue += " where cast(a.created_date as date) >= cast(? as date) and cast(a.created_date as date) <= cast(? as date) ";
			}
		}
		if (!user_role.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(r.role_id as character varying) = ? ";
			} else {
				SearchValue += " where cast(r.role_id as character varying) = ? ";
			}
		}

		String prf_whr = "";
		String prf_whr1 = "";
		if (!sub_quali.equals("")) {

			String add_q = "";

			for (int d = 0; d < sub_quali.substring(0, sub_quali.length()).split(",").length; d++) {
				
				add_q += "?,";
			}
			add_q = add_q.substring(0, add_q.length() - 1);
			
			if (SearchValue.contains("where")) {
				SearchValue += " and a.created_by in (" + add_q + ") ";
			} else {
				SearchValue += " where a.created_by in (" + add_q + ") ";
			}

			/*
			 * prf_whr = " and a.created_by in ("+add_q+") "; prf_whr1 =
			 * " a.created_by in ("+add_q+") ";
			 */
		}

		return SearchValue;
	}

	public String GenerateQueryWhereClause_SQL1(String Search, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String unit_name, String unit_sus_no, /* String user_role_id, */String from_date,
			String to_date, String user_role, String sub_quali) {

		String SearchValue = "";
		
		if (!Search.equals("")) { // for Input Filter
			SearchValue = " where  ";
			SearchValue += "( upper(c.cmd_name) like ? or upper(c.coprs_name) like ? or upper(c.div_name) like ? "
					+ " or upper(c.bde_name) like ? or upper(c.unit_name) like ?  ) ";
		}

		if (!cont_comd.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where orb.form_code_control like ? ";
			}
		}
		if (!cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}
		if (!cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}
		if (!cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";
			} else {
				SearchValue += " where  orb.form_code_control like ? ";
			}
		}

		if (!unit_name.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.unit_name = ? ";
			} else {
				SearchValue += " where orb.unit_name like ?";
			}
		}

		if (!unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and orb.sus_no = ? ";
			} else {
				SearchValue += " where orb.sus_no = ? ";
			}
		}
		/*
		 * if( !user_role_id.equals("")) { if (SearchValue.contains("where")) {
		 * SearchValue += " and cast(lm.userId as character varying) = ? "; } else {
		 * SearchValue += " where cast(lm.userId as character varying) = ? "; } }
		 */
		if (!from_date.equals("") && !to_date.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  cast(cen.created_date as date) >= cast(? as date) and cast(cen.created_date as date) <= cast(? as date) ";
			} else {
				SearchValue += " where cast(cen.created_date as date) >= cast(? as date) and cast(cen.created_date as date) <= cast(? as date) ";
			}
		}
		if (!user_role.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(r.role_id as character varying) = ? ";
			} else {
				SearchValue += " where cast(r.role_id as character varying) = ? ";
			}
		}

		String prf_whr = "";
		String prf_whr1 = "";
		if (!sub_quali.equals("")) {

			String add_q = "";

			for (int d = 0; d < sub_quali.substring(0, sub_quali.length()).split(",").length; d++) {
				
				add_q += "?,";
			}
			add_q = add_q.substring(0, add_q.length() - 1);
			
			if (SearchValue.contains("where")) {
				SearchValue += " and cen.created_by in (" + add_q + ") ";
			} else {
				SearchValue += " where cen.created_by in (" + add_q + ") ";
			}

			/*
			 * prf_whr = " and a.created_by in ("+add_q+") "; prf_whr1 =
			 * " a.created_by in ("+add_q+") ";
			 */
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali) {
		int flag = 0;
		try {
			if (!Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase() + "%");

			}
			if (!cont_corps.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase() + "%");

			}
			if (!cont_div.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase() + "%");

			}
			if (!cont_bde.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase() + "%");

			}
			if (!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());

			}
			if (!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());

			}
			/*
			 * if(!user_role_id.equals("")) { flag += 1; stmt.setString(flag, user_role_id);
			 * 
			 * }
			 */

			if (!from_date.equals("") && !to_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
				flag += 1;
				stmt.setString(flag, to_date);

			}

			if (!user_role.equals("0")) {
				flag += 1;
				stmt.setString(flag, user_role);
			}

			if (!sub_quali.equals("")) {
				for (int d = 0; d < sub_quali.substring(0, sub_quali.length()).split(",").length; d++) {
					flag = flag + 1;
					
					stmt.setString(flag, sub_quali.substring(0, sub_quali.length()).split(",")[d].trim());
				}

//						flag = flag + 1;
//						stmt.setString(flag, prf_code);
			}

		} catch (Exception e) {
		}

		return stmt;

	}

	public List<Map<String, Object>> getsearch_officer_en_table(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved ,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (a.id) FILTER (WHERE a.status::text IN ('1'::text,'0'::text))as total\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
					+ "and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
				
					+ "left join roleinformation r  on u.role_id=r.role_id  " + SearchValue
					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name limit "
					+ pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
			
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
//////////////////////////////////////////////////////////////////////////////OFFICER CENSUS//////////////////////

	public List<Map<String, Object>> getsearch_officer_en_table1(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name,\r\n"
					+ "count(distinct a.id) FILTER (WHERE cen.status = '1') AS approved,\r\n"
					+ "count(distinct a.id) FILTER (WHERE cen.status = '0') AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE cen.status IN ('1','0'))  AS TOTAL\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join  tb_psg_census_detail_p cen on cen.comm_id=a.id AND cen.created_by != 'psg|miso'\r\n"
					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'                                                                                        \r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
					+ "and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
					+ "left join logininformation l  on l.username=cen.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id " + SearchValue
					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name limit "
					+ pageL + " OFFSET " + startPage + "";
			stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
			
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 

	public long getsearch_officer_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved ,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (a.id) FILTER (WHERE a.status::text IN ('1'::text,'0'::text))as total\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
					+ "and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id   " + SearchValue
					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */from_date, to_date, user_role, sub_quali);
			
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

	/////////////////////////////////////////////////////////////////// OFFICER
	/////////////////////////////////////////////////////////////////// CENSUS////////////////////

	public long getsearch_officer_en_tablecount1(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name,\r\n"
					+ "count(distinct a.id) FILTER (WHERE cen.status = '1') AS approved,\r\n"
					+ "count(distinct a.id) FILTER (WHERE cen.status = '0') AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE cen.status IN ('1','0'))  AS TOTAL\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join  tb_psg_census_detail_p cen on cen.comm_id=a.id AND cen.created_by != 'psg|miso'\r\n"
					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'                                                                                        \r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
					+ "and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
					+ "left join logininformation l  on l.username=cen.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id " + SearchValue
					+ " group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
			
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

	public List<Map<String, Object>> getsearch_jco_en_table(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "left join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'											\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id  \r\n" + "" + SearchValue
					+ " group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name limit "
					+ pageL + " OFFSET " + startPage + "";
			stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
			
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

	public long getsearch_jco_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "left join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'											\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id \r\n" + "" + SearchValue
					+ " group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name order by c.cmd_name ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

/////civ		 
	public List<Map<String, Object>> getsearch_civilian_en_table(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			/* String user_role_id, */String from_date, String to_date, String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
					+ "from tb_psg_civilian_dtl a\r\n"
					+ "left join orbat_all_details_view c on c.sus_no = a.sus_no and c.status_sus_no = 'Active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb on orb.sus_no = a.sus_no and orb.status_sus_no='Active'											\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id \r\n" + "" + SearchValue
					+ "  group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  \r\n"
					+ " order by c.cmd_name limit " + pageL + " OFFSET " + startPage + "";
			stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

	public long getsearch_civilian_en_tablecount(String Search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String unit_name, String unit_sus_no, /* String user_role_id, */String from_date, String to_date,
			String user_role, String sub_quali) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
				unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (distinct a.id) FILTER (WHERE a.status IN ('1','0')) AS TOTAL\r\n"
					+ "from tb_psg_civilian_dtl a\r\n"
					+ "left join orbat_all_details_view c on c.sus_no = a.sus_no and c.status_sus_no = 'Active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb on orb.sus_no = a.sus_no and orb.status_sus_no='Active'											\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id  \r\n" + " " + SearchValue
					+ " group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  "
					+ " order by c.cmd_name ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, cont_comd, cont_corps, cont_div, cont_bde, unit_name,
					unit_sus_no, /* user_role_id, */ from_date, to_date, user_role, sub_quali);
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

	public List<String> getpsgirarNameBySUS(String FindWhat, String nSUSNo) {
		Connection conn = null;
		List<String> l = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String cond = "";

			/*
			 * if(FindWhat.equalsIgnoreCase("COMMAND")){
			 * cond="concat(substring(a.form_code_control,?,?))";
			 * //cond="concat(substring(a.form_code_control,1,1),'000000000')"; }else
			 * if(FindWhat.equalsIgnoreCase("CORPS")){
			 * cond="concat(substring(a.form_code_control,?,?),?)";
			 * //cond="concat(substring(a.form_code_control,1,3),'0000000')"; }else
			 * if(FindWhat.equalsIgnoreCase("DIVISION")){
			 * cond="concat(substring(a.form_code_control,?,?),?)";
			 * //cond="concat(substring(a.form_code_control,1,6),'0000')"; }else
			 * if(FindWhat.equalsIgnoreCase("BRIGADE")){ cond="a.form_code_control"; }else
			 * if(FindWhat.equalsIgnoreCase("HQ")){ cond="a.form_code_control"; }
			 */

			sq1 = "select p.hq,p.hq1, p.hq2, p.form_code_control, q.sus_no as hq_sus_no,q.unit_name as hq_name,p.form_code_control,p.sus_no,p.unit_name from (";

			// sq1 = sq1 +" select "+ cond +" as hq,a.form_code_control,a.sus_no,a.unit_name
			// from tb_miso_orbat_unt_dtl a";
			// Added view orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl

			sq1 = sq1
					+ " select (substring(a.form_code_control,1,1)) as hq,(substring(a.form_code_control,1,3)) as hq1,(substring(a.form_code_control,1,6)) as hq2\r\n"
					+ ",a.form_code_control,a.sus_no,a.unit_name  from orbat_all_details_view a ";

			sq1 = sq1
					+ " where a.sus_no=? and a.status_sus_no='Active') p left join nrv_hq q on p.hq=q.form_code_control";

			PreparedStatement stmt = conn.prepareStatement(sq1);

			stmt.setString(1, nSUSNo);

			
			ResultSet rs = stmt.executeQuery();
			
			String str1 = "";
			while (rs.next()) {
				str1 = rs.getString("hq");
				str1 = str1 + ":" + rs.getString("hq1");
				str1 = str1 + ":" + rs.getString("hq2");
				str1 = str1 + ":" + rs.getString("form_code_control");
				// str1=str1+":"+rs.getString("hq_name");
//			               str1=str1+":"+rs.getString("form_code_control");
//			               str1=str1+":"+rs.getString("sus_no");
//			               str1=str1+":"+rs.getString("unit_name");
//			               str1=str1+":"+rs.getString("hq_sus_no");
//			               str1=str1+","; 
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}

	public List<String> getpsgEncList(List<String> l, HttpSession s1) {
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher ci = null;

		try {
			ci = hex_asciiDao.EncryptionSHA256Algo(s1, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		List<String> FList = new ArrayList<String>();

		for (int i = 0; i < l.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = ci.doFinal(l.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FList.add(base64EncodedEncryptedCode);
		}

		// Enc Key Append Last value of List
		if (l.size() != 0) {
			FList.add(enckey + "0fsjyg==");
		}

		return FList;
	}

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report1() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "\r\n"
					+ "select distinct comm.id,comm.personnel_no,comm.name,TO_CHAR(comm.date_of_birth ,'DD-MON-YYYY')as \r\n"
					+ "date_of_birth ,a1.description as rank,t1.label as rank_type, g1.gender_name as gender,\r\n"
					+ "case \r\n" + "when comm.status ='5' then 'DESERTER'\r\n"
					+ "WHEN comm.status ='4' then 'Non-Effective'\r\n" + "when comm.status ='1' then 'ACTIVE'\r\n"
					+ "else 'INACTIVE'\r\n"
					+ " end AS STATUS,TO_CHAR(rank.date_of_rank ,'DD-MON-YYYY')as date_of_rank ,\r\n"
					+ " arm_p1.arm_desc as parent_arm,\r\n" + " arm_r1.arm_desc  as regiment,\r\n"
					+ " med.shape,TO_CHAR(med.date_of_authority ,'DD-MON-YYYY')  as date_of_medical ,med.med_classification_lmc,\r\n"
					+ " med.cope,date_part('year',age(now(),comm.date_of_birth)) AS AGE,\r\n"
					+ " date_part('year',age(now(),sen_main.date_of_seniority )) AS service,TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ " TO_CHAR(comm.date_of_commission ,'DD-MON-YYYY') as date_of_commission,comm.unit_sus_no,orb.unit_name,\r\n"
					+ "fv.unit_name as cmd_unit,\r\n" + "fvm.unit_name as corp_unit,\r\n"
					+ "div.unit_name as div_unit,\r\n" + "bde.unit_name as bde_unit ,\r\n" + "orb.ct_part_i_ii,\r\n"
					+ "COALESCE(l.code_value,'') as peace_field,\r\n" + "COALESCE(l1.label,'') as unit_loc_type,\r\n"
					+ "arm2.arm_desc as user_arm,\r\n"
					+ "case when COALESCE( reg.religion_name,'') = 'OTHERS' THEN cen.religion_other else reg.religion_name end as religion_name,\r\n"
					+ "case when COALESCE( mol.mothertounge,'') = 'OTHERS' THEN cen.mother_tongue_other else mol.mothertounge end  as mothertounge,\r\n"
					+ " COALESCE( bd.blood_desc,'')  as blood_group,\r\n"
					+ "case when COALESCE( nati.nationality_name,'') = 'OTHERS' then cen.nationality_other else nati.nationality_name end as nationality_name,\r\n"
					+ "case when COALESCE( st.state_name,'')='OTHERS' THEN cen.org_domicile_state_other else st.state_name end as state_name,\r\n"
					+ "COALESCE(mari.marital_name,'') as marital_name,\r\n"
					+ "COALESCE((TO_CHAR(ms.marriage_date ,'DD-MON-YYYY')),'') as marriage_date,\r\n"
					+ "COALESCE(ms.maiden_name,'') as maiden_name,\r\n"
					+ "COALESCE(ms.spouse_personal_no,'') as spouse_personal_no,\r\n"
					+ "case when  upper(t2.ex_servicemen)='OTHERS' or  upper(t2.ex_servicemen)='OTHER' then ms.other_spouse_ser\r\n"
					+ " else\r\n" + "COALESCE(t2.ex_servicemen::text,'') End as spouse_service,\r\n"
					+ "COALESCE (exam_p.examination_passed,'') as spouse_qualification,\r\n"
					+ "COALESCE(child.name ,'') as child_name,\r\n" + "d_child.label as  child_gender,\r\n"
					+ "child.adoted,child.type as child_disable,\r\n"
					+ "COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective,\r\n"
					+ "desert.dt_desertion as desertion_date,\r\n" + "d_desert.label as  arm_type,\r\n"
					+ "add.permanent_border_area as border_area,\r\n" + "ds.label as CAUSE_OF_DESERTION,\r\n"
					+ "TO_CHAR(desert.dt_recovered ,'DD-MON-YYYY')as DATE_OF_DESERTOR_RECOVERED\r\n" + "\r\n"
					+ "from tb_psg_trans_proposed_comm_letter comm \r\n"
					+ "inner join tb_psg_change_of_rank rank on (comm.id=rank.comm_id and rank.status=1)\r\n"
					+ "inner join cue_tb_psg_rank_app_master a1 on a1.id=comm.rank and  upper(level_in_hierarchy) = 'RANK' and parent_code ='0' and code not in ('R009','R099','R400','R110','R203','R207','R307','R1001','R1002','TQ284','210','R200','R115','R205','R116','R117','R201','R208','R202','R128','R129','R306','R204','R114') \r\n"
					+ "inner join tb_psg_mstr_gender g1 on g1.id=comm.gender\r\n"
					+ "inner join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = comm.parent_arm \r\n"
					+ "left join t_domain_value t1 on t1.codevalue= rank.rank_type::text   and  t1.domainid='OFFR_RANK_TYPE'\r\n"
					+ "left join tb_psg_census_family_married ms on ms.comm_id =comm.id and ms.status= 1\r\n"
					+ "left join tb_psg_mstr_exservicemen t2 on t2.id = ms.spouse_service --where ms.status= 1   --and comm.unit_sus_no = '0418045W' and cast(pcl.id  as character varying)='200048'\r\n"
					+ "inner join\r\n"
					+ "(select MIN(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n"
					+ "where status in ('1','2')  group by 2) sen\r\n" + "on sen.comm_id=comm.id\r\n"
					+ "inner join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n"
					+ "left join tb_psg_census_detail_p cen on (cen.comm_id= comm.id and cen.status=1)\r\n"
					+ "left join tb_psg_mstr_religion reg on reg.religion_id=cen.religion \r\n"
					+ "left join tb_psg_mothertounge mol on mol.id=cen.mother_tongue \r\n"
					+ "left join tb_psg_mstr_blood bd on bd.id=cen.blood_group \r\n"
					+ "left join tb_psg_mstr_nationality nati on nati.nationality_id=cen.nationality \r\n"
					+ "left join tb_psg_mstr_state st on st.state_id = cen.org_state       \r\n"
					+ "left join tb_psg_mstr_marital_status mari on mari.marital_id = cen.marital_status \r\n"
					+ "left join tb_psg_medical_categoryhistory med on  (med.comm_id = comm.id and med.status=1)\r\n"
					+ "left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  comm.regiment \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on  orb.sus_no = comm.unit_sus_no\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n"
					+ "left join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=comm.unit_sus_no and orb1.status_sus_no='Active')\r\n"
					+ "left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "left join tb_psg_census_spouse_qualification squali on squali.comm_id=comm.id and squali.status=1\r\n"
					+ "left join tb_psg_mstr_examination_passed exam_p on exam_p.id=squali.examination_pass\r\n"
					+ "left join tb_psg_census_children child on (child.comm_id=comm.id and child.status=1)\r\n"
					+ "left join t_domain_value d_child on cast(child.relationship as character varying)=d_child.codevalue \r\n"
					+ "            and  d_child.domainid='CHILD_RELATIONSHIP'\r\n"
					+ "left join tb_psg_non_effective effect on effect.comm_id=comm.id and effect.status=1\r\n"
					+ "left join tb_psg_mstr_cause_of_non_effective non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "left join tb_psg_deserter desert on desert.comm_id=comm.id and desert.status=1 \r\n"
					+ "left join t_domain_value d_desert on cast(desert.arm_type as character varying)=d_desert.codevalue \r\n"
					+ "            and  d_desert.domainid='ARM_TYPE'\r\n"
					+ "left join tb_psg_census_address add on add.comm_id=comm.id and add.status=1        \r\n"
					+ "left join t_domain_value ds on cast(desert.desertion_cause as character varying)=ds.codevalue \r\n"
					+ "            and  ds.domainid='CAUSE_OF_DESRTION'\r\n" + "where  comm.status not in ('0','3')\r\n"
					+ "and comm.id not in (select comm_id from tb_psg_re_employment where status=1  and re_emp_select='2'  )\r\n"
					+ "\r\n" + "	union all\r\n" + "\r\n"
					+ "	select distinct comm.id,comm.personnel_no,comm.name,TO_CHAR(comm.date_of_birth ,'DD-MON-YYYY')as \r\n"
					+ "	date_of_birth ,a1.description as rank,t1.label as rank_type, g1.gender_name as gender,\r\n"
					+ "	case \r\n" + "	WHEN remp.re_emp_select ='1' then 'RE_CALL'\r\n"
					+ "	WHEN remp.re_emp_select ='2' then 'RE_EMPLOYMENT'\r\n" + "	else 'INACTIVE' \r\n"
					+ "	 end AS STATUS\r\n" + "	,TO_CHAR(rank.date_of_rank ,'DD-MON-YYYY')as date_of_rank ,\r\n"
					+ "	 arm_p1.arm_desc as parent_arm,\r\n" + "	 arm_r1.arm_desc  as regiment,\r\n"
					+ "	 med.shape,TO_CHAR(med.date_of_authority ,'DD-MON-YYYY')  as date_of_medical ,med.med_classification_lmc,\r\n"
					+ "	 med.cope,date_part('year',age(now(),comm.date_of_birth)) AS AGE,\r\n"
					+ "	 date_part('year',age(now(),sen_main.date_of_seniority )) AS service,TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ "	 TO_CHAR(comm.date_of_commission ,'DD-MON-YYYY') as date_of_commission,comm.unit_sus_no,orb.unit_name,\r\n"
					+ "	fv.unit_name as cmd_unit,\r\n" + "	fvm.unit_name as corp_unit,\r\n"
					+ "	div.unit_name as div_unit,\r\n" + "	bde.unit_name as bde_unit ,\r\n"
					+ "	orb.ct_part_i_ii,\r\n" + "	COALESCE(l.code_value,'') as peace_field,\r\n"
					+ "	COALESCE(l1.label,'') as unit_loc_type,\r\n" + "	arm2.arm_desc as user_arm,\r\n"
					+ "	case when COALESCE( reg.religion_name,'') = 'OTHERS' THEN cen.religion_other else reg.religion_name end as religion_name,\r\n"
					+ "	case when COALESCE( mol.mothertounge,'') = 'OTHERS' THEN cen.mother_tongue_other else mol.mothertounge end  as mothertounge,\r\n"
					+ "	 COALESCE( bd.blood_desc,'')  as blood_group,\r\n"
					+ "	case when COALESCE( nati.nationality_name,'') = 'OTHERS' then cen.nationality_other else nati.nationality_name end as nationality_name,\r\n"
					+ "	case when COALESCE( st.state_name,'')='OTHERS' THEN cen.org_domicile_state_other else st.state_name end as state_name,\r\n"
					+ "	COALESCE(mari.marital_name,'') as marital_name,\r\n"
					+ "	COALESCE((TO_CHAR(ms.marriage_date ,'DD-MON-YYYY')),'') as marriage_date,\r\n"
					+ "	COALESCE(ms.maiden_name,'') as maiden_name,\r\n"
					+ "	COALESCE(ms.spouse_personal_no,'') as spouse_personal_no,\r\n"
					+ "	case when  upper(t2.ex_servicemen)='OTHERS' or  upper(t2.ex_servicemen)='OTHER' then ms.other_spouse_ser\r\n"
					+ "	 else\r\n" + "	COALESCE(t2.ex_servicemen::text,'') End as spouse_service,\r\n"
					+ "	COALESCE (exam_p.examination_passed,'') as spouse_qualification,\r\n"
					+ "	COALESCE(child.name ,'') as child_name,\r\n" + "	d_child.label as  child_gender,\r\n"
					+ "	child.adoted,child.type as child_disable,\r\n"
					+ "	COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective,\r\n"
					+ "	desert.dt_desertion as desertion_date,\r\n" + "	d_desert.label as  arm_type,\r\n"
					+ "	add.permanent_border_area as border_area,\r\n" + "	ds.label as CAUSE_OF_DESERTION,\r\n"
					+ "	TO_CHAR(desert.dt_recovered ,'DD-MON-YYYY')as DATE_OF_DESERTOR_RECOVERED\r\n" + "	from\r\n"
					+ "	tb_psg_trans_proposed_comm_letter comm\r\n"
					+ "	inner join tb_psg_re_employment remp on (comm.id=remp.comm_id and remp.status='1')\r\n"
					+ "	inner join tb_psg_change_of_rank rank on (comm.id=rank.comm_id and rank.status=1)\r\n"
					+ "	left join cue_tb_psg_rank_app_master a1 on a1.id=comm.rank and  upper(level_in_hierarchy) = 'RANK' and parent_code ='0' and code not in ('R009','R099','R400','R110','R203','R207','R307','R1001','R1002','TQ284','210','R200','R115','R205','R116','R117','R201','R208','R202','R128','R129','R306','R204','R114') \r\n"
					+ "	left join t_domain_value t1 on t1.codevalue= rank.rank_type::text  and  t1.domainid='OFFR_RANK_TYPE'\r\n"
					+ "	inner join tb_psg_mstr_gender g1 on g1.id=comm.gender \r\n"
					+ "	inner join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = comm.parent_arm \r\n"
					+ "	left join tb_psg_census_family_married ms on ms.comm_id =comm.id and ms.status= 1\r\n"
					+ "	left join tb_psg_mstr_exservicemen t2 on t2.id = ms.spouse_service --where ms.status= 1   --and comm.unit_sus_no = '0418045W' and cast(pcl.id  as character varying)='200048'\r\n"
					+ "	inner join\r\n"
					+ "	(select MIN(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n"
					+ "	where status in ('1','2')  group by 2) sen\r\n" + "	on sen.comm_id=comm.id\r\n"
					+ "	inner join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n"
					+ "	left join tb_psg_census_detail_p cen on (cen.comm_id= comm.id and cen.status=1)\r\n"
					+ "	left join tb_psg_mstr_religion reg on reg.religion_id=cen.religion and reg.status='active'\r\n"
					+ "	left join tb_psg_mothertounge mol on mol.id=cen.mother_tongue and mol.status='active'\r\n"
					+ "	left join tb_psg_mstr_blood bd on bd.id=cen.blood_group and bd.status='active'\r\n"
					+ "	left join tb_psg_mstr_nationality nati on nati.nationality_id=cen.nationality and nati.status='active' \r\n"
					+ "	left join tb_psg_mstr_state st on st.state_id = cen.org_state        and st.status = 'active'\r\n"
					+ "	left join tb_psg_mstr_marital_status mari on mari.marital_id = cen.marital_status and mari.status='active'\r\n"
					+ "	left join tb_psg_medical_categoryhistory med on  (med.comm_id = comm.id and med.status=1)\r\n"
					+ "	left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  comm.regiment \r\n"
					+ "	INNER JOIN tb_miso_orbat_unt_dtl orb on  orb.sus_no = comm.unit_sus_no\r\n"
					+ "	left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "	left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "	left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "	left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "	left join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n"
					+ "	left join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=comm.unit_sus_no and orb1.status_sus_no='Active')\r\n"
					+ "	left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "	left join tb_psg_census_spouse_qualification squali on squali.comm_id=comm.id and squali.status=1\r\n"
					+ "	left join tb_psg_mstr_examination_passed exam_p on exam_p.id=squali.examination_pass\r\n"
					+ "	left join tb_psg_census_children child on (child.comm_id=comm.id and child.status=1)\r\n"
					+ "	left join t_domain_value d_child on cast(child.relationship as character varying)=d_child.codevalue \r\n"
					+ "				and  d_child.domainid='CHILD_RELATIONSHIP'\r\n"
					+ "	left join tb_psg_non_effective effect on effect.comm_id=comm.id and effect.status=1\r\n"
					+ "	left join tb_psg_mstr_cause_of_non_effective non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "	left join tb_psg_deserter desert on desert.comm_id=comm.id and desert.status=1 \r\n"
					+ "	left join t_domain_value d_desert on cast(desert.arm_type as character varying)=d_desert.codevalue \r\n"
					+ "				and  d_desert.domainid='ARM_TYPE'\r\n"
					+ "	left join tb_psg_census_address add on add.comm_id=comm.id and add.status=1        \r\n"
					+ "	left join t_domain_value ds on cast(desert.desertion_cause as character varying)=ds.codevalue  and  ds.domainid='CAUSE_OF_DESRTION'							  \r\n"
					+ "\r\n"
					+ "	inner join tb_psg_posting_in_out op on comm.id=op.comm_id and comm.status not in ('0','3') and op.status=1"
					+ SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("personnel_no"));// 1
				list.add(rs.getString("name"));// 2
				list.add(rs.getString("date_of_birth"));// 3
				list.add(rs.getString("rank"));// 4
				list.add(rs.getString("rank_type"));// 5
				list.add(rs.getString("gender"));// 6
				list.add(rs.getString("status"));// 7
				list.add(rs.getString("date_of_rank"));// 8
				list.add(rs.getString("parent_arm"));// 9
				list.add(rs.getString("regiment"));// 10
				list.add(rs.getString("shape"));// 11
				list.add(rs.getString("date_of_medical"));// 12
				list.add(rs.getString("med_classification_lmc"));// 13
				list.add(rs.getString("cope"));// 14
				list.add(rs.getString("age"));// 15
				list.add(rs.getString("service"));// 16
				list.add(rs.getString("dt_of_seniority"));// 17
				list.add(rs.getString("date_of_commission"));// 18
				list.add(rs.getString("unit_sus_no"));// 19
				list.add(rs.getString("unit_name"));// 20
				list.add(rs.getString("cmd_unit"));// 21
				list.add(rs.getString("corp_unit"));// 22
				list.add(rs.getString("div_unit"));// 23
				list.add(rs.getString("bde_unit"));// 24
				list.add(rs.getString("ct_part_i_ii"));// 25
				list.add(rs.getString("peace_field"));// 26
				list.add(rs.getString("unit_loc_type"));// 27
				list.add(rs.getString("user_arm"));// 28
				list.add(rs.getString("religion_name"));// 29
				list.add(rs.getString("mothertounge"));// 30
				list.add(rs.getString("blood_group"));// 31
				list.add(rs.getString("nationality_name"));// 32
				list.add(rs.getString("state_name"));// 33
				list.add(rs.getString("marital_name"));// 34
				list.add(rs.getString("marriage_date"));// 35
				list.add(rs.getString("maiden_name"));// 36
				list.add(rs.getString("spouse_personal_no"));// 37
				list.add(rs.getString("spouse_service"));// 38
				list.add(rs.getString("spouse_qualification"));// 39
				list.add(rs.getString("child_name"));// 40
				list.add(rs.getString("child_gender"));// 41
				list.add(rs.getString("adoted"));// 42
				list.add(rs.getString("child_disable"));// 43
				list.add(rs.getString("cause_of_non_effective"));// 44
				list.add(rs.getString("date_of_non_effective"));// 45
				list.add(rs.getString("desertion_date"));// 46
				list.add(rs.getString("arm_type"));// 47
				list.add(rs.getString("border_area"));// 48
				list.add(rs.getString("CAUSE_OF_DESERTION"));// 49
				list.add(rs.getString("DATE_OF_DESERTOR_RECOVERED"));// 50

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

	///////////////////////////////////////////////////////////////// excel
	///////////////////////////////////////////////////////////////// 2/////////////////////////

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report2() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select q.comm_id,case when a.status ='5' then 'DESERTER'WHEN a.status ='4' then 'Non-Effective'\r\n"
					+ "when a.status ='1' then 'ACTIVE'else 'INACTIVE'\r\n"
					+ "end AS STATUS,case when q.type ='2' then 'Academic'\r\n"
					+ "WHEN q.type ='6' then 'Professional/Technical'\r\n"
					+ "end AS STATUS,ex.examination_passed,de.degree,speci.specialization,il.ind_language,fl.foreign_language_name,cou.name as Country_Visited,\r\n"
					+ "pov.visit_purpose as Purpose_of_Visit,army.course_name as Course_Name, army.course_abbreviation as Course_Name_Abbreviation, div.div as Div, award.award_cat as Category,med1.medal_name as Honours_Awards_Meda,TO_CHAR(medal.date_of_award,'DD-MON-YYYY') as Date_of_Award, medal.unit as Unit, bde.bde_name as BDE, div2.div_name as Sub_Area,\r\n"
					+ "corp.coprs_name as Corps,cmd11.cmd_name as award_command,TO_CHAR(bpc.date_of_casuality,'DD-MON-YYYY') as Date_of_Casualty, bpc.time_of_casuality as Time_of_Casualty,bpc.classification_of_casuality as Recommended, \r\n"
					+ "nc.label as Nature_of_Casualty,bpc.onduty as onduty, bpc.name_of_operation as Name_of_Operation, bpc.sector as Sector, bpc.field_services as field_area, bpc.whether_on as Whether_on,\r\n"
					+ "bde1.bde_name as battle_BDE,div1.div_name as battle_div, corps1.coprs_name as battle_corps, cmd1.cmd_name as battle_Command,\r\n"
					+ "casu1.casuality1 as battle_Cause_of_Casualty,bpc.cause_of_casuality as battle_Category_of_Casualty\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a inner join tb_psg_census_detail_p b on b.comm_id=a.id\r\n"
					+ "left join tb_psg_census_qualification q on q.comm_id=b.comm_id and (q.status ='1' and q.comm_id is not null) \r\n"
					+ "left join  tb_psg_mstr_examination_passed ex on ex.id= q.examination_pass\r\n"
					+ "left join  tb_psg_mstr_degree de on de.id= q.degree\r\n"
					+ "left join tb_psg_mstr_specialization speci on speci.id=q.specialization \r\n"
					+ "left join tb_psg_census_language l on l.comm_id =b.comm_id and (l.status ='1' and l.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_foreign_language fl on fl.id=l.foreign_language \r\n"
					+ "left join tb_psg_mstr_indian_language il on il.id= l.language \r\n"
					+ "left join tb_psg_census_foreign_country v on v.comm_id=b.comm_id and (v.status ='1' and v.comm_id is not null) \r\n"
					+ "left join tb_psg_foreign_country cou on cou.id=v.country\r\n"
					+ "left join tb_psg_mstr_purposeof_visit pov on pov.id=v.purpose_visit\r\n"
					+ "left join tb_psg_census_army_course army on b.comm_id=army.comm_id and (army.status ='1' and army.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_div_grade div on cast (div.id as character varying)=army.div_grade\r\n"
					+ "left join tb_psg_census_awardsnmedal medal on b.comm_id=medal.comm_id and (medal.status ='1' and medal.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_award_category award on cast (award.id as character varying)=medal.category_8\r\n"
					+ "left join tb_psg_mstr_medal med1 on med1.id=medal.select_desc ::integer\r\n"
					+ "left join orbat_view_bde bde on bde.sus_no=medal.bde\r\n"
					+ "left join orbat_view_div div2 on div2.sus_no= medal.div_subarea\r\n"
					+ "left join orbat_view_corps corp on corp.sus_no=medal.corps_area\r\n"
					+ "left join orbat_view_cmd cmd1 on cmd1.sus_no=medal.command\r\n"
					+ "left join tb_psg_census_battle_physical_casuality bpc on bpc.comm_id=b.comm_id and  (bpc.status ='1' and bpc.comm_id is not null) \r\n"
					+ "left join T_Domain_Value nc on nc.codevalue=bpc.nature_of_casuality and nc.domainid='NATURE_OF_CASUALITY'\r\n"
					+ "left join orbat_view_bde  bde1 on bde1.sus_no=bpc.bde\r\n"
					+ "left join orbat_view_div div1 on div1.sus_no=bpc.div_subarea\r\n"
					+ "left join orbat_view_corps corps1 on corps1.sus_no=bpc.corps_area\r\n"
					+ "left join orbat_view_cmd cmd11 on cmd11.sus_no=bpc.command\r\n"
					+ "left join tb_psg_mstr_casuality1 casu1 on casu1.id=bpc.cause_of_casuality_1::integer --where a.status in 	('1','5') \r\n"
					+ " \r\n" + " union all\r\n" + " \r\n" + " \r\n" + "select q.comm_id,\r\n" + "case \r\n"
					+ "	WHEN remp.re_emp_select ='1' then 'RE_CALL'\r\n"
					+ "	WHEN remp.re_emp_select ='2' then 'RE_EMPLOYMENT'\r\n" + "	else 'INACTIVE'\r\n"
					+ "	 end AS STATUS\r\n" + "	,case when q.type ='2' then 'Academic'\r\n"
					+ "WHEN q.type ='6' then 'Professional/Technical'\r\n"
					+ "end AS STATUS,ex.examination_passed,de.degree,speci.specialization,il.ind_language,fl.foreign_language_name,cou.name as Country_Visited,\r\n"
					+ "pov.visit_purpose as Purpose_of_Visit,army.course_name as Course_Name, army.course_abbreviation as Course_Name_Abbreviation, div.div as Div, award.award_cat as Category,med1.medal_name as Honours_Awards_Meda,TO_CHAR(medal.date_of_award,'DD-MON-YYYY') as Date_of_Award, medal.unit as Unit, bde.bde_name as BDE, div2.div_name as Sub_Area,\r\n"
					+ "corp.coprs_name as Corps,cmd11.cmd_name as award_command,TO_CHAR(bpc.date_of_casuality,'DD-MON-YYYY') as Date_of_Casualty, bpc.time_of_casuality as Time_of_Casualty,bpc.classification_of_casuality as Recommended, \r\n"
					+ "nc.label as Nature_of_Casualty,bpc.onduty as onduty, bpc.name_of_operation as Name_of_Operation, bpc.sector as Sector, bpc.field_services as field_area, bpc.whether_on as Whether_on,\r\n"
					+ "bde1.bde_name as battle_BDE,div1.div_name as battle_div, corps1.coprs_name as battle_corps, cmd1.cmd_name as battle_Command,\r\n"
					+ "casu1.casuality1 as battle_Cause_of_Casualty,bpc.cause_of_casuality as battle_Category_of_Casualty\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a inner join tb_psg_census_detail_p b on b.comm_id=a.id\r\n"
					+ "left join tb_psg_census_qualification q on q.comm_id=b.comm_id and (q.status ='1' and q.comm_id is not null)\r\n"
					+ "left join tb_psg_re_employment remp on (a.id=remp.comm_id and remp.status='1')\r\n"
					+ "left join  tb_psg_mstr_examination_passed ex on ex.id= q.examination_pass\r\n"
					+ "left join  tb_psg_mstr_degree de on de.id= q.degree\r\n"
					+ "left join tb_psg_mstr_specialization speci on speci.id=q.specialization \r\n"
					+ "left join tb_psg_census_language l on l.comm_id =b.comm_id and (l.status ='1' and l.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_foreign_language fl on fl.id=l.foreign_language \r\n"
					+ "left join tb_psg_mstr_indian_language il on il.id= l.language \r\n"
					+ "left join tb_psg_census_foreign_country v on v.comm_id=b.comm_id and (v.status ='1' and v.comm_id is not null) \r\n"
					+ "left join tb_psg_foreign_country cou on cou.id=v.country\r\n"
					+ "left join tb_psg_mstr_purposeof_visit pov on pov.id=v.purpose_visit\r\n"
					+ "left join tb_psg_census_army_course army on b.comm_id=army.comm_id and (army.status ='1' and army.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_div_grade div on cast (div.id as character varying)=army.div_grade\r\n"
					+ "left join tb_psg_census_awardsnmedal medal on b.comm_id=medal.comm_id and (medal.status ='1' and medal.comm_id is not null) \r\n"
					+ "left join tb_psg_mstr_award_category award on cast (award.id as character varying)=medal.category_8\r\n"
					+ "left join tb_psg_mstr_medal med1 on med1.id=medal.select_desc ::integer\r\n"
					+ "left join orbat_view_bde bde on bde.sus_no=medal.bde\r\n"
					+ "left join orbat_view_div div2 on div2.sus_no= medal.div_subarea\r\n"
					+ "left join orbat_view_corps corp on corp.sus_no=medal.corps_area\r\n"
					+ "left join orbat_view_cmd cmd1 on cmd1.sus_no=medal.command\r\n"
					+ "left join tb_psg_census_battle_physical_casuality bpc on bpc.comm_id=b.comm_id and  (bpc.status ='1' and bpc.comm_id is not null) \r\n"
					+ "left join T_Domain_Value nc on nc.codevalue=bpc.nature_of_casuality and nc.domainid='NATURE_OF_CASUALITY'\r\n"
					+ "left join orbat_view_bde  bde1 on bde1.sus_no=bpc.bde\r\n"
					+ "left join orbat_view_div div1 on div1.sus_no=bpc.div_subarea\r\n"
					+ "left join orbat_view_corps corps1 on corps1.sus_no=bpc.corps_area\r\n"
					+ "left join orbat_view_cmd cmd11 on cmd11.sus_no=bpc.command\r\n"
					+ "left join tb_psg_mstr_casuality1 casu1 on casu1.id=bpc.cause_of_casuality_1::integer where a.status in 	('1','5') \r\n"
					+ "\r\n" + "\r\n" + "\r\n" + "\r\n" + "" + SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("examination_passed"));// 1
				list.add(rs.getString("degree"));// 2
				list.add(rs.getString("specialization"));// 3
				list.add(rs.getString("ind_language"));// 7
				list.add(rs.getString("foreign_language_name"));// 8
				list.add(rs.getString("Country_Visited"));// 9
				list.add(rs.getString("Purpose_of_Visit"));// 10
				list.add(rs.getString("Course_Name"));// 11
				list.add(rs.getString("Course_Name_Abbreviation"));// 12
				list.add(rs.getString("Div"));// 13
				list.add(rs.getString("Category"));// 14
				list.add(rs.getString("Honours_Awards_Meda"));// 15
				list.add(rs.getString("Date_of_Award"));// 16
				list.add(rs.getString("Unit"));// 17
				list.add(rs.getString("BDE"));// 18
				list.add(rs.getString("Sub_Area"));// 19
				list.add(rs.getString("Corps"));// 20
				list.add(rs.getString("award_command"));// 21
				list.add(rs.getString("Date_of_Casualty"));// 22
				list.add(rs.getString("Time_of_Casualty"));// 23
				list.add(rs.getString("Recommended"));// 24
				list.add(rs.getString("Nature_of_Casualty"));// 25
				list.add(rs.getString("onduty"));// 26
				list.add(rs.getString("Name_of_Operation"));// 27
				list.add(rs.getString("Sector"));// 28
				list.add(rs.getString("field_area"));// 29
				list.add(rs.getString("Whether_on"));// 30
				list.add(rs.getString("battle_BDE"));// 31
				list.add(rs.getString("battle_div"));// 32
				list.add(rs.getString("battle_corps"));// 33
				list.add(rs.getString("battle_Command"));// 34
				list.add(rs.getString("battle_Cause_of_Casualty"));// 35
				list.add(rs.getString("battle_Category_of_Casualty"));// 36

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

///////////////////////////////////////////////////////////////Excel 3 ///////////////////////////////////////////

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report3() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct jco.army_no as PERSONNEL_NO, case when jco.status ='1' then 'ACTIVE'\r\n"
					+ "when jco.status ='5' then 'DESERTER'\r\n" + "WHEN jco.status ='4' then 'Non-Effective'\r\n"
					+ "else 'INACTIVE' end AS STATUS,rk.rank as rank,TO_CHAR(jco.date_of_rank,'DD-MON-YYYY') as DATE_RANK, tr.trade as TRADE,jco.full_name as Name,\r\n"
					+ "gen.gender_name as GENDER, arm.arm_desc as ARMS_SERVICES,arm1.arm_desc as REGT, med.shape as MEDICAL_CATEGORY, \r\n"
					+ "TO_CHAR(med.date_of_authority,'DD-MON-YYYY') as DATE_OF_MEDICAL,\r\n"
					+ "med.med_classification_lmc as MED_CLASSIFICATION_LMC, med.cope as cope, TO_CHAR(jco.date_of_birth,'DD-MON-YYYY') as DATE_OF_BIRTH,\r\n"
					+ "TO_CHAR(jco.enroll_dt,'DD-MON-YYYY') as DATE_OF_ENROLLMENT,TO_CHAR(jco.date_of_attestation,'DD-MON-YYYY') as DATE_OF_ATTESTATION,\r\n"
					+ "TO_CHAR(jco.date_of_seniority,'DD-MON-YYYY') as DATE_OF_SENIORITY, jco.unit_sus_no as UNIT_SUS_NO,sus.unit_name,fv.unit_name as cmd_unit,\r\n"
					+ "fvm.unit_name as corp_unit,\r\n" + "div.unit_name as div_unit,\r\n"
					+ "bde.unit_name as bde_unit, sus.ct_part_i_ii,\r\n"
					+ "COALESCE(l1.label,'') as unit_loc_type,arm2.arm_desc as user_arm,\r\n"
					+ "COALESCE(l.code_value,'') as peace_field,\r\n"
					+ " rel.religion_name as RELIGION, mt.mothertounge,nat.nationality_name, bl.blood_desc as blood_group, cou.name as COUNTRY, st.state_name,\r\n"
					+ "add.permanent_border_area as border_area,mar.marital_name,child.adoted,child.type as child_disable,\r\n"
					+ "COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective,\r\n"
					+ "d_desert.label as  arm_type,desert.dt_desertion as desertion_date,\r\n"
					+ "ds.label as CAUSE_OF_DESERTION,\r\n"
					+ "TO_CHAR(desert.dt_recovered ,'DD-MON-YYYY')as DATE_OF_DESERTOR_RECOVERED, cla.class_pay, pay.pay_group \r\n"
					+ "from tb_psg_census_jco_or_p jco \r\n"
					+ "left join tb_psg_re_call_jco remp on (jco.id=remp.jco_id and remp.status='1')\r\n"
					+ "left join tb_psg_mstr_rank_jco rk on rk.id=jco.rank \r\n"
					+ "left join tb_psg_mstr_trade_jco tr on tr.id=jco.trade\r\n"
					+ "left join tb_psg_mstr_gender gen on gen.id=jco.gender\r\n"
					+ "left join tb_miso_orbat_arm_code arm on arm.arm_code = jco.arm_service \r\n"
					+ "and arm.arm_code in ('0100','0120','0200','0300','0400','0500', \r\n"
					+ "				'0600', '0800','0900','1000','1100','1200','1300','1400','1500','1600','1700','1800','1900',\r\n"
					+ "		 '2000','2100','2200','2300','2400','3300','3400','3700','4600','4700') \r\n"
					+ "left join tb_miso_orbat_arm_code arm1 on arm1.arm_code=regiment and SUBSTR(arm.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and arm.arm_code not in ('0700','0800')\r\n"
					+ "left join tb_psg_medical_categoryhistory_jco med on (med.jco_id = jco.id and med.status=1)		 \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl sus on sus.sus_no=jco.unit_sus_no 		 \r\n"
					+ "left join all_fmn_view fv  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on sus.sus_no = jco.unit_sus_no  and sus.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "\r\n" + "left join tb_miso_orbat_code l on sus.code = l.code and sus.code_type = l.code_type\r\n"
					+ "left join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=jco.unit_sus_no and orb1.status_sus_no='Active')\r\n"
					+ "left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "left join tb_psg_mstr_religion rel on rel.religion_id=jco.religion\r\n"
					+ "left join tb_psg_mothertounge mt on mt.id=jco.mother_tongue\r\n"
					+ "left join tb_psg_mstr_nationality nat on nat.nationality_id=jco.nationality\r\n"
					+ "left join tb_psg_mstr_blood bl on bl.id=jco.blood_group\r\n"
					+ "left join tb_psg_mstr_country cou on cou.id=jco.country_of_birth\r\n"
					+ "left join tb_psg_mstr_state st on st.state_id=jco.state_of_birth\r\n"
					+ "--left join tb_psg_census_address br on br.id=jco.id \r\n"
					+ "left join tb_psg_mstr_marital_status mar on mar.marital_id=jco.marital_status\r\n"
					+ "left join tb_psg_mstr_pay_group_jco pay on pay.id=jco.pay_group\r\n"
					+ "left join tb_psg_mstr_class_pay_jco cla on cla.id=jco.class_pay\r\n"
					+ "left join tb_psg_census_children_jco child on (child.jco_id=jco.id and child.status=1)\r\n"
					+ "left join t_domain_value d_child on cast(child.relationship as character varying)=d_child.codevalue \r\n"
					+ "            and  d_child.domainid='CHILD_RELATIONSHIP'\r\n"
					+ "left join tb_psg_non_effective_jco effect on effect.jco_id=jco.id and effect.status=1\r\n"
					+ "left join tb_psg_mstr_cause_of_non_effective non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "left join tb_psg_deserter_jco desert on desert.jco_id=jco.id and desert.status=1 \r\n"
					+ "left join t_domain_value d_desert on cast(desert.arm_type as character varying)=d_desert.codevalue \r\n"
					+ "            and  d_desert.domainid='ARM_TYPE'\r\n"
					+ "left join tb_psg_census_address_jco add on add.jco_id=jco.id and add.status=1        \r\n"
					+ "left join t_domain_value ds on cast(desert.desertion_cause as character varying)=ds.codevalue \r\n"
					+ "            and  ds.domainid='CAUSE_OF_DESRTION'	\r\n"
					+ "		where jco.id not in (select jco_id from tb_psg_re_call_jco where status=1  and re_emp_select='1'  )	\r\n"
					+ "		union all	\r\n" + "			\r\n"
					+ "			select distinct jco.army_no as PERSONNEL_NO, case \r\n"
					+ "WHEN remp.re_emp_select ='1' then 'RE_CALL'\r\n"
					+ "--	WHEN remp.re_emp_select ='2' then 'RE_EMPLOYMENT'\r\n" + "	else 'INACTIVE' \r\n"
					+ "	 end AS STATUS,rk.rank as rank,TO_CHAR(jco.date_of_rank,'DD-MON-YYYY') as DATE_RANK, tr.trade as TRADE,jco.full_name as Name,\r\n"
					+ "gen.gender_name as GENDER, arm.arm_desc as ARMS_SERVICES,arm1.arm_desc as REGT, med.shape as MEDICAL_CATEGORY, \r\n"
					+ "TO_CHAR(med.date_of_authority,'DD-MON-YYYY') as DATE_OF_MEDICAL,\r\n"
					+ "med.med_classification_lmc as MED_CLASSIFICATION_LMC, med.cope as cope, TO_CHAR(jco.date_of_birth,'DD-MON-YYYY') as DATE_OF_BIRTH,\r\n"
					+ "TO_CHAR(jco.enroll_dt,'DD-MON-YYYY') as DATE_OF_ENROLLMENT,TO_CHAR(jco.date_of_attestation,'DD-MON-YYYY') as DATE_OF_ATTESTATION,\r\n"
					+ "TO_CHAR(jco.date_of_seniority,'DD-MON-YYYY') as DATE_OF_SENIORITY, jco.unit_sus_no as UNIT_SUS_NO,sus.unit_name,fv.unit_name as cmd_unit,\r\n"
					+ "fvm.unit_name as corp_unit,\r\n" + "div.unit_name as div_unit,\r\n"
					+ "bde.unit_name as bde_unit, sus.ct_part_i_ii,\r\n"
					+ "COALESCE(l1.label,'') as unit_loc_type,arm2.arm_desc as user_arm,\r\n"
					+ "COALESCE(l.code_value,'') as peace_field,\r\n"
					+ " rel.religion_name as RELIGION, mt.mothertounge,nat.nationality_name, bl.blood_desc as blood_group, cou.name as COUNTRY, st.state_name,\r\n"
					+ "add.permanent_border_area as border_area,mar.marital_name,child.adoted,child.type as child_disable,\r\n"
					+ "COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective,\r\n"
					+ "d_desert.label as  arm_type,desert.dt_desertion as desertion_date,\r\n"
					+ "ds.label as CAUSE_OF_DESERTION,\r\n"
					+ "TO_CHAR(desert.dt_recovered ,'DD-MON-YYYY')as DATE_OF_DESERTOR_RECOVERED, cla.class_pay, pay.pay_group \r\n"
					+ "from tb_psg_census_jco_or_p jco \r\n"
					+ "inner join tb_psg_re_call_jco remp on (jco.id=remp.jco_id and remp.status='1')\r\n"
					+ "left join tb_psg_mstr_rank_jco rk on rk.id=jco.rank \r\n"
					+ "left join tb_psg_mstr_trade_jco tr on tr.id=jco.trade\r\n"
					+ "left join tb_psg_mstr_gender gen on gen.id=jco.gender\r\n"
					+ "left join tb_miso_orbat_arm_code arm on arm.arm_code = jco.arm_service \r\n"
					+ "and arm.arm_code in ('0100','0120','0200','0300','0400','0500', \r\n"
					+ "				'0600', '0800','0900','1000','1100','1200','1300','1400','1500','1600','1700','1800','1900',\r\n"
					+ "		 '2000','2100','2200','2300','2400','3300','3400','3700','4600','4700') \r\n"
					+ "left join tb_miso_orbat_arm_code arm1 on arm1.arm_code=regiment and SUBSTR(arm.arm_code,1,3) IN ('071','072','073','074','075','076','077','081','082','083') and arm.arm_code not in ('0700','0800')\r\n"
					+ "left join tb_psg_medical_categoryhistory_jco med on (med.jco_id = jco.id and med.status=1)		 \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl sus on sus.sus_no=jco.unit_sus_no 		 \r\n"
					+ "left join all_fmn_view fv  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on sus.sus_no = jco.unit_sus_no  and SUBSTRING(sus.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on sus.sus_no = jco.unit_sus_no  and sus.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "\r\n" + "left join tb_miso_orbat_code l on sus.code = l.code and sus.code_type = l.code_type\r\n"
					+ "left join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=jco.unit_sus_no and orb1.status_sus_no='Active')\r\n"
					+ "left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "left join tb_psg_mstr_religion rel on rel.religion_id=jco.religion\r\n"
					+ "left join tb_psg_mothertounge mt on mt.id=jco.mother_tongue\r\n"
					+ "left join tb_psg_mstr_nationality nat on nat.nationality_id=jco.nationality\r\n"
					+ "left join tb_psg_mstr_blood bl on bl.id=jco.blood_group\r\n"
					+ "left join tb_psg_mstr_country cou on cou.id=jco.country_of_birth\r\n"
					+ "left join tb_psg_mstr_state st on st.state_id=jco.state_of_birth\r\n"
					+ "--left join tb_psg_census_address br on br.id=jco.id \r\n"
					+ "left join tb_psg_mstr_marital_status mar on mar.marital_id=jco.marital_status\r\n"
					+ "left join tb_psg_mstr_pay_group_jco pay on pay.id=jco.pay_group\r\n"
					+ "left join tb_psg_mstr_class_pay_jco cla on cla.id=jco.class_pay\r\n"
					+ "left join tb_psg_census_children_jco child on (child.jco_id=jco.id and child.status=1)\r\n"
					+ "left join t_domain_value d_child on cast(child.relationship as character varying)=d_child.codevalue \r\n"
					+ "            and  d_child.domainid='CHILD_RELATIONSHIP'\r\n"
					+ "left join tb_psg_non_effective_jco effect on effect.jco_id=jco.id and effect.status=1\r\n"
					+ "left join tb_psg_mstr_cause_of_non_effective non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "left join tb_psg_deserter_jco desert on desert.jco_id=jco.id and desert.status=1 \r\n"
					+ "left join t_domain_value d_desert on cast(desert.arm_type as character varying)=d_desert.codevalue \r\n"
					+ "            and  d_desert.domainid='ARM_TYPE'\r\n"
					+ "left join tb_psg_census_address_jco add on add.jco_id=jco.id and add.status=1        \r\n"
					+ "left join t_domain_value ds on cast(desert.desertion_cause as character varying)=ds.codevalue \r\n"
					+ "            and  ds.domainid='CAUSE_OF_DESRTION'" + SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("PERSONNEL_NO"));// 1
				list.add(rs.getString("STATUS"));// 2
				list.add(rs.getString("rank"));// 3
				list.add(rs.getString("DATE_RANK"));// 4
				list.add(rs.getString("TRADE"));// 5
				list.add(rs.getString("Name"));// 6
				list.add(rs.getString("GENDER"));// 7
				list.add(rs.getString("ARMS_SERVICES"));// 8
				list.add(rs.getString("REGT"));// 9
				list.add(rs.getString("MEDICAL_CATEGORY"));// 10
				list.add(rs.getString("DATE_OF_MEDICAL"));// 11
				list.add(rs.getString("MED_CLASSIFICATION_LMC"));// 12
				list.add(rs.getString("cope"));// 13
				list.add(rs.getString("DATE_OF_BIRTH"));// 14
				list.add(rs.getString("DATE_OF_ENROLLMENT"));// 15
				list.add(rs.getString("DATE_OF_ATTESTATION"));// 16
				list.add(rs.getString("DATE_OF_SENIORITY"));// 17
				list.add(rs.getString("UNIT_SUS_NO"));// 18
				list.add(rs.getString("unit_name"));// 19
				list.add(rs.getString("cmd_unit"));// 20
				list.add(rs.getString("corp_unit"));// 21
				list.add(rs.getString("div_unit"));// 22
				list.add(rs.getString("bde_unit"));// 23
				list.add(rs.getString("ct_part_i_ii"));// 24
				list.add(rs.getString("unit_loc_type"));// 25
				list.add(rs.getString("user_arm"));// 26
				list.add(rs.getString("peace_field"));// 27
				list.add(rs.getString("RELIGION"));// 28
				list.add(rs.getString("mothertounge"));// 29
				list.add(rs.getString("nationality_name"));// 30
				list.add(rs.getString("blood_group"));// 31
				list.add(rs.getString("COUNTRY"));// 32
				list.add(rs.getString("state_name"));// 33
				list.add(rs.getString("border_area"));// 34
				list.add(rs.getString("marital_name"));// 35
				list.add(rs.getString("adoted"));// 36
				list.add(rs.getString("child_disable"));// 37
				list.add(rs.getString("cause_of_non_effective"));// 38
				list.add(rs.getString("date_of_non_effective"));// 39
				list.add(rs.getString("arm_type"));// 40
				list.add(rs.getString("desertion_date"));// 41
				list.add(rs.getString("CAUSE_OF_DESERTION"));// 42
				list.add(rs.getString("DATE_OF_DESERTOR_RECOVERED"));// 43
				list.add(rs.getString("class_pay"));// 44
				list.add(rs.getString("pay_group"));// 45

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

	/////////////////////////////////////////////////// Excel 4
	/////////////////////////////////////////////////// /////////////////////////////////////////////////

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report4() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select a.id,a.army_no,case when a.status ='5' then 'DESERTER'WHEN a.status ='4' then 'Non-Effective'\r\n"
					+ "when a.status ='1' then 'ACTIVE'else 'INACTIVE'\r\n"
					+ "end AS STATUS,case when q.type ='2' then 'Academic'\r\n"
					+ "WHEN q.type ='6' then 'Professional/Technical'\r\n"
					+ "end AS STATUS,ex.examination_passed,de.degree,speci.specialization,il.ind_language,fl.foreign_language_name,cou.name as Country_Visited,\r\n"
					+ "pov.visit_purpose as Purpose_of_Visit,army.course_name as Course_Name, army.course_abbreviation as Course_Name_Abbreviation, div.div as Div, award.award_cat as Category,med1.medal_name as Honours_Awards_Meda,TO_CHAR(medal.date_of_award,'DD-MON-YYYY') as Date_of_Award, medal.unit as Unit, bde.bde_name as BDE, div2.div_name as Sub_Area,\r\n"
					+ "corp.coprs_name as Corps,cmd11.cmd_name as award_command,TO_CHAR(bpc.date_of_casuality,'DD-MON-YYYY') as Date_of_Casualty, bpc.time_of_casuality as Time_of_Casualty,bpc.classification_of_casuality as Recommended, \r\n"
					+ "nc.label as Nature_of_Casualty,bpc.onduty as onduty, bpc.name_of_operation as Name_of_Operation, bpc.sector as Sector, bpc.field_services as field_area, bpc.whether_on as Whether_on,\r\n"
					+ "bde1.bde_name as battle_BDE,div1.div_name as battle_div, corps1.coprs_name as battle_corps, cmd1.cmd_name as battle_Command,\r\n"
					+ "casu1.casuality1 as battle_Cause_of_Casualty,bpc.cause_of_casuality as battle_Category_of_Casualty\r\n"
					+ "from tb_psg_census_jco_or_p a \r\n"
					+ "left join tb_psg_census_qualification_jco q on q.jco_id=a.id and (q.status ='1' and q.jco_id is not null) \r\n"
					+ "left join  tb_psg_mstr_examination_passed ex on ex.id= q.examination_pass\r\n"
					+ "left join  tb_psg_mstr_degree de on de.id= q.degree\r\n"
					+ "left join tb_psg_mstr_specialization speci on speci.id=q.specialization \r\n"
					+ "left join tb_psg_census_language_jco l on l.jco_id =a.id and (l.status ='1' and l.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_foreign_language fl on fl.id=l.foreign_language \r\n"
					+ "left join tb_psg_mstr_indian_language il on il.id= l.language \r\n"
					+ "left join tb_psg_census_foreign_country_jco v on v.jco_id =a.id and (v.status ='1' and v.jco_id is not null) \r\n"
					+ "left join tb_psg_foreign_country cou on cou.id=v.country\r\n"
					+ "left join tb_psg_mstr_purposeof_visit pov on pov.id=v.purpose_visit\r\n"
					+ "left join tb_psg_census_army_course_jco army on army.jco_id =a.id and (army.status ='1' and army.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_div_grade div on cast (div.id as character varying)=army.div_grade\r\n"
					+ "left join tb_psg_census_awardsnmedal_jco medal on medal.jco_id =a.id and (medal.status ='1' and medal.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_award_category award on cast (award.id as character varying)=medal.category_8\r\n"
					+ "left join tb_psg_mstr_medal med1 on med1.id=medal.select_desc ::integer\r\n"
					+ "left join orbat_view_bde bde on bde.sus_no=medal.bde\r\n"
					+ "left join orbat_view_div div2 on div2.sus_no= medal.div_subarea\r\n"
					+ "left join orbat_view_corps corp on corp.sus_no=medal.corps_area\r\n"
					+ "left join orbat_view_cmd cmd1 on cmd1.sus_no=medal.command\r\n"
					+ "left join tb_psg_census_battle_physical_casuality_jco bpc on bpc.jco_id =a.id and  (bpc.status ='1' and bpc.jco_id is not null) \r\n"
					+ "left join T_Domain_Value nc on nc.codevalue=bpc.nature_of_casuality and nc.domainid='NATURE_OF_CASUALITY'\r\n"
					+ "left join orbat_view_bde  bde1 on bde1.sus_no=bpc.bde\r\n"
					+ "left join orbat_view_div div1 on div1.sus_no=bpc.div_subarea\r\n"
					+ "left join orbat_view_corps corps1 on corps1.sus_no=bpc.corps_area\r\n"
					+ "left join orbat_view_cmd cmd11 on cmd11.sus_no=bpc.command\r\n"
					+ "left join tb_psg_mstr_casuality1 casu1 on casu1.id=bpc.cause_of_casuality_1::integer-- where a.id='2400'\r\n"
					+ "\r\n" + " union all\r\n" + " \r\n" + " select a.id,a.army_no,case \r\n"
					+ "	WHEN remp.re_emp_select ='1' then 'RE_CALL'\r\n"
					+ "	WHEN remp.re_emp_select ='2' then 'RE_EMPLOYMENT'\r\n" + "	else 'INACTIVE'\r\n"
					+ "	 end AS STATUS\r\n" + "	,case when q.type ='2' then 'Academic'\r\n"
					+ "WHEN q.type ='6' then 'Professional/Technical'\r\n"
					+ "end AS STATUS,ex.examination_passed,de.degree,speci.specialization,il.ind_language,fl.foreign_language_name,cou.name as Country_Visited,\r\n"
					+ "pov.visit_purpose as Purpose_of_Visit,army.course_name as Course_Name, army.course_abbreviation as Course_Name_Abbreviation, div.div as Div, award.award_cat as Category,med1.medal_name as Honours_Awards_Meda,TO_CHAR(medal.date_of_award,'DD-MON-YYYY') as Date_of_Award, medal.unit as Unit, bde.bde_name as BDE, div2.div_name as Sub_Area,\r\n"
					+ "corp.coprs_name as Corps,cmd11.cmd_name as award_command,TO_CHAR(bpc.date_of_casuality,'DD-MON-YYYY') as Date_of_Casualty, bpc.time_of_casuality as Time_of_Casualty,bpc.classification_of_casuality as Recommended, \r\n"
					+ "nc.label as Nature_of_Casualty,bpc.onduty as onduty, bpc.name_of_operation as Name_of_Operation, bpc.sector as Sector, bpc.field_services as field_area, bpc.whether_on as Whether_on,\r\n"
					+ "bde1.bde_name as battle_BDE,div1.div_name as battle_div, corps1.coprs_name as battle_corps, cmd1.cmd_name as battle_Command,\r\n"
					+ "casu1.casuality1 as battle_Cause_of_Casualty,bpc.cause_of_casuality as battle_Category_of_Casualty\r\n"
					+ "from tb_psg_census_jco_or_p a \r\n"
					+ "left join tb_psg_census_qualification_jco q on q.jco_id=a.id and (q.status ='1' and q.jco_id is not null) \r\n"
					+ "left join tb_psg_re_call_jco remp on (a.id=remp.jco_id and remp.status='1')\r\n"
					+ "LEFT join  tb_psg_mstr_examination_passed ex on ex.id= q.examination_pass\r\n"
					+ "LEFT join  tb_psg_mstr_degree de on de.id= q.degree\r\n"
					+ "left join tb_psg_mstr_specialization speci on speci.id=q.specialization \r\n"
					+ "left join tb_psg_census_language_jco l on l.jco_id =a.id and (l.status ='1' and l.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_foreign_language fl on fl.id=l.foreign_language \r\n"
					+ "left join tb_psg_mstr_indian_language il on il.id= l.language \r\n"
					+ "left join tb_psg_census_foreign_country_jco v on v.jco_id =a.id and (v.status ='1' and v.jco_id is not null) \r\n"
					+ "left join tb_psg_foreign_country cou on cou.id=v.country\r\n"
					+ "left join tb_psg_mstr_purposeof_visit pov on pov.id=v.purpose_visit\r\n"
					+ "left join tb_psg_census_army_course_jco army on army.jco_id =a.id and (army.status ='1' and army.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_div_grade div on cast (div.id as character varying)=army.div_grade\r\n"
					+ "left join tb_psg_census_awardsnmedal_jco medal on medal.jco_id =a.id and (medal.status ='1' and medal.jco_id is not null) \r\n"
					+ "left join tb_psg_mstr_award_category award on cast (award.id as character varying)=medal.category_8\r\n"
					+ "left join tb_psg_mstr_medal med1 on med1.id=medal.select_desc ::integer\r\n"
					+ "left join orbat_view_bde bde on bde.sus_no=medal.bde\r\n"
					+ "left join orbat_view_div div2 on div2.sus_no= medal.div_subarea\r\n"
					+ "left join orbat_view_corps corp on corp.sus_no=medal.corps_area\r\n"
					+ "left join orbat_view_cmd cmd1 on cmd1.sus_no=medal.command\r\n"
					+ "left join tb_psg_census_battle_physical_casuality_jco bpc on bpc.jco_id =a.id and  (bpc.status ='1' and bpc.jco_id is not null) \r\n"
					+ "left join T_Domain_Value nc on nc.codevalue=bpc.nature_of_casuality and nc.domainid='NATURE_OF_CASUALITY'\r\n"
					+ "left join orbat_view_bde  bde1 on bde1.sus_no=bpc.bde\r\n"
					+ "left join orbat_view_div div1 on div1.sus_no=bpc.div_subarea\r\n"
					+ "left join orbat_view_corps corps1 on corps1.sus_no=bpc.corps_area\r\n"
					+ "left join orbat_view_cmd cmd11 on cmd11.sus_no=bpc.command\r\n"
					+ "left join tb_psg_mstr_casuality1 casu1 on casu1.id=bpc.cause_of_casuality_1::integer "
					+ SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("examination_passed"));// 1
				list.add(rs.getString("degree"));// 2
				list.add(rs.getString("specialization"));// 3
				list.add(rs.getString("ind_language"));// 7
				list.add(rs.getString("foreign_language_name"));// 8
				list.add(rs.getString("Country_Visited"));// 9
				list.add(rs.getString("Purpose_of_Visit"));// 10
				list.add(rs.getString("Course_Name"));// 11
				list.add(rs.getString("Course_Name_Abbreviation"));// 12
				list.add(rs.getString("Div"));// 13
				list.add(rs.getString("Category"));// 14
				list.add(rs.getString("Honours_Awards_Meda"));// 15
				list.add(rs.getString("Date_of_Award"));// 16
				list.add(rs.getString("Unit"));// 17
				list.add(rs.getString("BDE"));// 18
				list.add(rs.getString("Sub_Area"));// 19
				list.add(rs.getString("Corps"));// 20
				list.add(rs.getString("award_command"));// 21
				list.add(rs.getString("Date_of_Casualty"));// 22
				list.add(rs.getString("Time_of_Casualty"));// 23
				list.add(rs.getString("Recommended"));// 24
				list.add(rs.getString("Nature_of_Casualty"));// 25
				list.add(rs.getString("onduty"));// 26
				list.add(rs.getString("Name_of_Operation"));// 27
				list.add(rs.getString("Sector"));// 28
				list.add(rs.getString("field_area"));// 29
				list.add(rs.getString("Whether_on"));// 30
				list.add(rs.getString("battle_BDE"));// 31
				list.add(rs.getString("battle_div"));// 32
				list.add(rs.getString("battle_corps"));// 33
				list.add(rs.getString("battle_Command"));// 34
				list.add(rs.getString("battle_Cause_of_Casualty"));// 35
				list.add(rs.getString("battle_Category_of_Casualty"));// 36

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

///////////////////////////////////////////////////////////Excel 5/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report5() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select civil.employee_no, case when civil.status ='1' then 'ACTIVE'\r\n"
					+ "WHEN civil.status ='4' then 'Non-Effective'\r\n"
					+ "else 'INACTIVE' end AS STATUS, concat(civil.first_name,'  ', civil.middle_name,'  ',civil.last_name) as name,\r\n"
					+ "gen.gender_name, d.label as classification_services,\r\n"
					+ "d1.label as Group,d2.label as civillan_trade, d3.label as Type,c.cadre,d4.label as service_Status, cat.category, \r\n"
					+ "TO_CHAR(civil.joining_date_gov_service,'DD-MON-YYYY') as date_joining_gov_ser,des.description as designation, pay.pay_level,state.state_name,civil.sus_no,\r\n"
					+ "sus.unit_name, fv.unit_name as cmd_unit,\r\n" + "fvm.unit_name as corp_unit,\r\n"
					+ "div.unit_name as div_unit,\r\n"
					+ "bde.unit_name as bde_unit,arm2.arm_desc as user_arm,sus.ct_part_i_ii,COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective\r\n"
					+ "from tb_psg_civilian_dtl civil\r\n"
					+ "inner join tb_psg_mstr_gender gen on gen.id=civil.gender\r\n"
					+ "inner join t_domain_value d on d.codevalue=civil.classification_services and d.domainid='CLASSIFICATION_OF_SERVICES'\r\n"
					+ "inner join T_Domain_Value d1 on d1.codevalue=civil.civ_group  and d1.domainid='CIV_R_GROUP'\r\n"
					+ "inner join  T_Domain_Value d2 on d2.codevalue=cast(civil.classification_trade as character varying)  and d2.domainid='CLASIFICATION_OF_TRADES'\r\n"
					+ "inner join  T_Domain_Value d3 on d3.codevalue=civil.civ_type  and d3.domainid='CIVILIAN_TYPE'\r\n"
					+ "inner join tb_psg_mstr_cadre_civilian c on c.id=civil.cadre and c.status='active' \r\n"
					+ "inner join T_Domain_Value d4 on d4.codevalue=civil.service_status and d4.domainid='SERVICE_STATUS'\r\n"
					+ "inner join tb_psg_mstr_category cat on cat.id=civil.category_belongs  and cat.status='active' \r\n"
					+ "inner join cue_tb_psg_rank_app_master des on des.id=civil.designation  and (level_in_hierarchy) = ('Appointment') and \r\n"
					+ "              parent_code in ('4','5','6','7','8','9','10','11') and des.status_active = 'Active'\r\n"
					+ "inner join tb_psg_mstr_pay_level pay on pay.id=civil.pay_level and pay.status='active'\r\n"
					+ "inner join tb_psg_mstr_state state on state.state_id=civil.state_original and state.status='active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl sus on cast(sus.sus_no as character varying)=civil.sus_no and upper(unit_name) like unit_name and sus.status_sus_no  = 'Active' 	\r\n"
					+ "left join all_fmn_view fv  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on sus.sus_no = civil.sus_no  and sus.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=civil.sus_no and orb1.status_sus_no='Active')\r\n"
					+ "left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "left join tb_psg_non_effective effect on effect.id=civil.non_effective and effect.status=1\r\n"
					+ "left join tb_psg_mstr_cause_of_non_effective_civilian non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "where civil.civilian_status ='R'\r\n" + "" + SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("employee_no"));// 1
				list.add(rs.getString("STATUS"));// 2
				list.add(rs.getString("name"));// 3
				list.add(rs.getString("gender_name"));// 4
				list.add(rs.getString("classification_services"));// 5
				list.add(rs.getString("Group"));// 6
				list.add(rs.getString("civillan_trade"));// 7
				list.add(rs.getString("Type"));// 8
				list.add(rs.getString("cadre"));// 9
				list.add(rs.getString("service_Status"));// 10
				list.add(rs.getString("category"));// 11
				list.add(rs.getString("date_joining_gov_ser"));// 12
				list.add(rs.getString("designation"));// 13
				list.add(rs.getString("pay_level"));// 14
				list.add(rs.getString("state_name"));// 15
				list.add(rs.getString("sus_no"));// 16
				list.add(rs.getString("unit_name"));// 17
				list.add(rs.getString("cmd_unit"));// 18
				list.add(rs.getString("corp_unit"));// 19
				list.add(rs.getString("div_unit"));// 20
				list.add(rs.getString("bde_unit"));// 21
				list.add(rs.getString("user_arm"));// 22
				list.add(rs.getString("ct_part_i_ii"));// 23
				list.add(rs.getString("cause_of_non_effective"));// 24
				list.add(rs.getString("date_of_non_effective"));// 25

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

	///////////////////////////////////////////////////////////////////////// Excel
	///////////////////////////////////////////////////////////////////////// 6///////////////////////////////////////////////

	public ArrayList<ArrayList<String>> Excel_Record_Service_Report6() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select civil.employee_no, case when civil.status ='1' then 'ACTIVE'\r\n"
					+ "WHEN civil.status ='4' then 'Non-Effective'\r\n"
					+ "else 'INACTIVE' end AS STATUS, concat(civil.first_name,'  ', civil.middle_name,'  ',civil.last_name) as name,\r\n"
					+ "gen.gender_name, d.label as classification_services, d1.label as Group,d3.label as Type,\r\n"
					+ "pay.pay_head, \r\n"
					+ "cat.category, TO_CHAR(civil.joining_date_gov_service,'DD-MON-YYYY') as date_joining_gov_ser,state.state_name,civil.sus_no,\r\n"
					+ "sus.unit_name, fv.unit_name as cmd_unit,\r\n" + "fvm.unit_name as corp_unit,\r\n"
					+ "div.unit_name as div_unit,\r\n"
					+ "bde.unit_name as bde_unit,arm2.arm_desc as user_arm,sus.ct_part_i_ii,COALESCE(non_effect.causes_name,'') as cause_of_non_effective,TO_CHAR(effect.date_of_non_effective ,'DD-MON-YYYY')as date_of_non_effective\r\n"
					+ "from tb_psg_civilian_dtl civil\r\n"
					+ "inner join tb_psg_mstr_gender gen on gen.id=civil.gender\r\n"
					+ "left join t_domain_value d on d.codevalue=civil.classification_services and d.domainid='CLASSIFICATION_OF_SERVICES'\r\n"
					+ "left join T_Domain_Value d1 on d1.codevalue=civil.civ_group  and d1.domainid='CIV_R_GROUP'\r\n"
					+ "left join  T_Domain_Value d3 on d3.codevalue=civil.civ_type  and d3.domainid='CIVILIAN_TYPE'\r\n"
					+ "inner join tb_psg_mstr_pay_head pay on pay.id=civil.pay_level and pay.status='active'\r\n"
					+ "inner join tb_psg_mstr_category cat on cat.id=civil.category_belongs  and cat.status='active' \r\n"
					+ "inner join tb_psg_mstr_state state on state.state_id=civil.state_original and state.status='active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl sus on cast(sus.sus_no as character varying)=civil.sus_no and upper(unit_name) like unit_name and sus.status_sus_no  = 'Active' 	\r\n"
					+ "left join all_fmn_view fv  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on sus.sus_no = civil.sus_no  and SUBSTRING(sus.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on sus.sus_no = civil.sus_no  and sus.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb1 on (orb1.sus_no=civil.sus_no and orb1.status_sus_no='Active')\r\n"
					+ "left join tb_miso_orbat_arm_code arm2 on arm2.arm_code=orb1.arm_code\r\n"
					+ "left join tb_psg_non_effective effect on effect.id=civil.non_effective and effect.status=1\r\n"
					+ "left join tb_psg_mstr_cause_of_non_effective_civilian non_effect on non_effect.id=effect.cause_of_non_effective::int\r\n"
					+ "where civil.civilian_status ='NR'" + SearchValue;

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
//							list.add(String.valueOf(i++)); //0
				list.add(rs.getString("employee_no"));// 1
				list.add(rs.getString("STATUS"));// 2
				list.add(rs.getString("name"));// 3
				list.add(rs.getString("gender_name"));// 4
				list.add(rs.getString("classification_services"));// 5
				list.add(rs.getString("Group"));// 6
				list.add(rs.getString("Type"));// 8
				list.add(rs.getString("pay_head"));// 9
				list.add(rs.getString("category"));// 11
				list.add(rs.getString("date_joining_gov_ser"));// 12
				list.add(rs.getString("state_name"));// 15
				list.add(rs.getString("sus_no"));// 16
				list.add(rs.getString("unit_name"));// 17
				list.add(rs.getString("cmd_unit"));// 18
				list.add(rs.getString("corp_unit"));// 19
				list.add(rs.getString("div_unit"));// 20
				list.add(rs.getString("bde_unit"));// 21
				list.add(rs.getString("user_arm"));// 22
				list.add(rs.getString("ct_part_i_ii"));// 23
				list.add(rs.getString("cause_of_non_effective"));// 24
				list.add(rs.getString("date_of_non_effective"));// 25

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

//---------------------------------------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public List<Map<String, Object>> getrankwise() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '1'::text) AS approved ,\r\n"
					+ "count(a.id) FILTER (WHERE a.status::text = '0'::text) AS pending,\r\n"
					+ "COUNT (a.id) FILTER (WHERE a.status::text IN ('1'::text,'0'::text))as total,\r\n"
					+ "COUNT (a.rank) FILTER (WHERE a.status::text IN ('1'::text,'0'::text))as rank\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'\r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) \r\n"
					+ "and fvm.level_in_hierarchy = 'Corps' \r\n"
					+ "left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6)\r\n"
					+ "and div.level_in_hierarchy = 'Division' \r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  \r\n"
					+ "left join logininformation l  on l.username=a.created_by\r\n"
					+ "left join userroleinformation u  on u.user_id=l.userid\r\n"
					+ "left join roleinformation r  on u.role_id=r.role_id   group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name";

			stmt = conn.prepareStatement(q);

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

	public ArrayList<ArrayList<String>> Getcount_no_unitData_para(String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String div1,
			String corps1, String bdes1, String regs1,String unit_name1) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
	String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control  in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm1.equals("")) {
				String temp5 = "";
				String[] datarank = parm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			
			
			qry = "select count(distinct a.unit_sus_no) as unit from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5')  " + q;
		
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm1.equals("")) {

				String[] datarank = parm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
		
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit"));

				alist.add(list);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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

	public ArrayList<ArrayList<String>> Getcount_no_offData_para(String cont_comd1, String cont_corps1,
			String cont_div1, String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String div1,
			String corps1, String bdes1, String regs1,String unit_name1) {

	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm1.equals("")) {
				String temp5 = "";
				String[] datarank = parm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			qry = "select count (distinct a.id) as offr  from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5') " + q;
		
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm1.equals("")) {

				String[] datarank = parm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("offr"));

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
	public int Getcount_no_Jco_OrData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		int count = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select count(distinct p.id) as total from tb_psg_census_jco_or_p p "
					+ " inner join tb_miso_orbat_unt_dtl orb ON orb.sus_no = p.unit_sus_no AND orb.status_sus_no = 'Active'\r\n"
					+ "  where p.status in ('1','5')  \r\n";
				//	+ "and type_of_comm_granted !='20'";

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			

			if (rs.next()) {
			    count = rs.getInt("total");
			}
//			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				list.add(columns);
//			}
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
		return count;
	}

	@Override
	public int Getcount_no_unitJcoData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		int count = 0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select count(distinct p.unit_sus_no) as unit  "
					+ "from tb_psg_census_jco_or_p  p "
					+ " Inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = p.unit_sus_no  AND orb.status_sus_no = 'Active'\r\n"
					+ " where p.status in ('1','5') \r\n";
					//+ "and type_of_comm_granted !='20'";

			stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		

			if (rs.next()) {
			    count = rs.getInt("unit");
			}
//			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				list.add(columns);
//			}
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
		return count;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------	

//------------------------------------------------------start-RK vs Held (officers)-------------------------------------------------------------------------------------------------------

	@Override
	public List<Map<String, Object>> getheldvsrk_jco_ordashboard() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select count(c.id) as total,m.rank,m.id\r\n"
					+ "from tb_psg_census_jco_or_p c\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = c.rank\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code ::int = c.id\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = c.regiment\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = c.arm_service \r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "where c.status in ('1','5')\r\n"
					+ "group by m.rank,m.id\r\n"
					+ "order by m.id desc\r\n" + "";

			stmt = conn.prepareStatement(q);

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

//----------------------------------------------------End---RK vs Held (officers)-------------------------------------------------------------------------------------------------------			

	// ------------------------------rk vs med cat
	// para---------------------------------------------------

//rk vs med cat

	@Override
	public String Getrk_medcatlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";

		try {

			if (cont_comd1 != null && !cont_comd1.equals("")) {
			    String temp = "";
			    String[] datacmd = cont_comd1.split(Pattern.quote("|"));

			    for (int i = 0; i < datacmd.length; i++) {
			        if (i == 0) {
			            temp = "?";
			        } else {
			            temp = temp + ",?";
			        }
			    }

			    q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}


			if (cont_corps1 != null && !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null &&!rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null  && !parent_arm1.equals("") ) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			
			if ( unit_name1 != null && !unit_name1.equals("")  ) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}


if ( regs1 != null  && !regs1.equals("")  ) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			conn = dataSource.getConnection();
	
			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '')  as rank ,m.id, \r\n"
					+ "count(a.id) filter (where mc.med_classification_lmc ='FIT') as fit,\r\n"
					+ "count(a.id) filter (where mc.med_classification_lmc ='PERMANENT') as permanent,\r\n"
					+ "count(a.id) filter (where mc.med_classification_lmc ='TEMPORARY') as temporary\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_psg_medical_categoryhistory_jco mc on mc.jco_id = a.id and mc.status ='1'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					/*
//					 * + "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.parent_arm\r\n" +
//					 * "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
//					 */
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "where a.status in ('1','5')     " + q + "    \r\n"
					+ "group by m.rank ,m.id     order by m.id desc ";

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;
			int flag = 0;
			if ( cont_comd1 != null && !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null && !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1 != null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			ResultSet rs = stmt.executeQuery();
		

			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
//				list += "{'data1': '" + rs.getString("rank") + "', 'data2': " + rs.getString("fit") + ", 'data3':  "
//						+ rs.getString("permanent") + ", 'data4':  " + rs.getString("temporary") + " } ";
				
				list += "{\"data1\": \"" + rs.getString("rank") + "\", \"data2\": " + rs.getString("fit") + ", \"data3\": "
				        + rs.getString("permanent") + ", \"data4\": " + rs.getString("temporary") + " } ";

			}
			list += "]";

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

	// rk vs held
	@Override
	public String Getrk_heldlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {
			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service  in (" + temp5 + ")   ";
			}
		
			if (unit_name1 != null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}

if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();

			/*
			 * qry = "select \r\n" +
			 * "count(a.id) filter (where  m.description ='LT') as lt,\r\n" +
			 * "count(a.id) filter (where  m.description ='CAPT') as capt,\r\n" +
			 * "count(a.id) filter (where  m.description ='MAJ') as maj,\r\n" +
			 * "count(a.id) filter (where  m.description ='LT COL') as ltcol,\r\n" +
			 * "count(a.id) filter (where  m.description ='COL [TS]') as colts,\r\n" +
			 * "count(a.id) filter (where  m.description ='COL') as col,\r\n" +
			 * "count(a.id) filter (where  m.description ='BRIG') as brig,\r\n" +
			 * "count(a.id) filter (where  m.description ='MAJ GEN') as majgen,\r\n" +
			 * "count(a.id) filter (where  m.description ='LT GEN') as ltgen,\r\n" +
			 * "count(a.id) filter (where  m.description ='GEN') as gen\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "left join tb_miso_orbat_unt_dtl o on o.sus_no = a.unit_sus_no \r\n" +
			 * "where a.status in ('1','5')\r\n" + "and a.type_of_comm_granted != '20' "+q;
			 */
			/*
			 * qry ="select count(a.id) as total,m.description\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "left join tb_miso_orbat_unt_dtl o on o.sus_no = a.unit_sus_no \r\n" +
			 * "where a.status in ('1','5')\r\n" +
			 * "and a.type_of_comm_granted != '20' "+q+" \r\n" + "group by 2";
			 */


			
			
			
			qry="select count(c.id) as total, REPLACE(m.rank, '/ EQUIVALENT', '') AS rank_without_equivalent,m.id\r\n"
					+ "from tb_psg_census_jco_or_p c\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = c.rank\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code ::int = c.id\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = c.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE g ON g.arm_code = c.arm_service \r\n"
					+ "where c.status in ('1','5')   " + q + "  \r\n"
					+ "group by m.rank,m.id\r\n"
					+ "order by m.id desc\r\n" + "";

			PreparedStatement stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}

			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (unit_name1 != null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			ResultSet rs = stmt.executeQuery();
	
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				/*
				 * list += "{'data11': '"+rs.getString("total")+"','data21': '" +
				 * rs.getString("description") + "','data31': '" + rs.getString("maj") + "'," +
				 * "'data41': '" + rs.getString("ltcol") + "','data51': '" +
				 * rs.getString("colts") + "','data61': '" + rs.getString("col") + "'," +
				 * "'data71': '" + rs.getString("brig") + "','data81': '" +
				 * rs.getString("majgen") + "','data91': '" + rs.getString("ltgen") +
				 * "','data101': '" + rs.getString("gen") + "' } ";
				 * 
				 * }
				 */

				list += "{'data11': " + rs.getString("total") + ",'data21': '" + rs.getString("rank_without_equivalent") + "'} ";

			}
			list += "]";

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

	// rk vs regiment
	@Override
	public String Getrk_regimentlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null && !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null && !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			

				if (unit_name1 != null && !unit_name1.equals("")) {
				
								String temp6 = "";
								String[] datarank = unit_name1.split(",");
				
								for (int i = 0; i < datarank.length; i++) {
				
									if (i == 0) {
				
										temp6 = temp6 + "?";
									} else {
				
										temp6 = temp6 + ",?";
									}
								}
								q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
							}
				if (regs1 != null && !regs1.equals("")) {
					String temp5 = "";
					String[] datarank = regs1.split(",");

					for (int i = 0; i < datarank.length; i++) {

						if (i == 0) {

							temp5 = temp5 + "?";
						} else {

							temp5 = temp5 + ",?";
						}
					}
					q += "and a.regiment in (" + temp5 + ")   ";
				}
			conn = dataSource.getConnection();

			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '') AS rank,m.id,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='1 GORKHA REGIMENT') as gorkharegiment1,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='3 GORKHA REGIMENT') as gorkharegiment3,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='4 GORKHA REGIMENT') as gorkharegiment4,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='5 GORKHA REGIMENT') as gorkharegiment5,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='8 GORKHA REGIMENT') as gorkharegiment8,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='9 GORKHA REGIMENT') as gorkharegiment9,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='11 GORKHA REGIMENT') as gorkharegiment11,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The PARACHUTE REGT') as parachuteregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The PUNJAB REGT') as pubjab_regt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The MADRAS REGT') as madrasregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The GRENADIERS') as grenadiers,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The MARATHA LIGHT INFANTRY') as marathalightinfantry,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The RAJPUTANA RIFLES') as rajputana_rifles,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The RAJPUT REGT') as rajputregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The JAT REGT') as jatregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The SIKH REGT') as sikhregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The SIKH LIGHT INFANTRY') as sikhlightinfantry,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The DOGRA REGT') as dograregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The GARHWAL RIFLES') as garhwalrifles,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The KUMAON REGT') as kumaonregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The ASSAM REGT') as assamregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The BIHAR REGT') as biharregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The MAHAR REGT') as maharregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The NAGA REGT') as nagaregt,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The JAMMU AND KASHMIR RIFLES') as jammuandkashmirrifles,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='The LADAKH SCOUTS') as ladakhscouts,\r\n"
					+ "count(a.id) filter (where e.arm_desc ='JAMMU AND KASHMIR LIGHT INFANTRY REGIMENT') as jammuandkashmirligthinfregiment\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_miso_orbat_arm_code e on e.arm_code = a.regiment\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"

					+ "where a.status in ('1','5')    " + q + "  \r\n"
					+ "group by m.rank,m.id\r\n";

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null && !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1 != null &&  !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}


				list += "{\"data1112\": \"" + rs.getString("rank") + "\",\"data12\": "
				        + rs.getString("gorkharegiment1") + ", \"data22\": " + rs.getString("gorkharegiment3") + ","
				        + "\"data32\": " + rs.getString("gorkharegiment4") + ", \"data42\": "
				        + rs.getString("gorkharegiment5") + ", \"data52\": " + rs.getString("gorkharegiment8") + ","
				        + "\"data62\": " + rs.getString("gorkharegiment9") + ",\"data72\": "
				        + rs.getString("gorkharegiment11") + ", \"data82\": " + rs.getString("parachuteregt") + ","
				        + "\"data92\": " + rs.getString("pubjab_regt") + ",\"data102\": " + rs.getString("madrasregt")
				        + ",\"data112\": " + rs.getString("grenadiers") + "," + "\"data122\": "
				        + rs.getString("marathalightinfantry") + ",\"data132\": " + rs.getString("rajputana_rifles")
				        + ",\"data142\": " + rs.getString("rajputregt") + "," + "\"data152\": " + rs.getString("jatregt")
				        + ",\"data162\": " + rs.getString("sikhregt") + ",\"data172\": "
				        + rs.getString("sikhlightinfantry") + ",\"data182\": " + rs.getString("dograregt") + ","
				        + "\"data192\": " + rs.getString("garhwalrifles") + ",\"data202\": " + rs.getString("kumaonregt")
				        + ",\"data212\": " + rs.getString("assamregt") + ",\"data222\": " + rs.getString("biharregt")
				        + "," + "\"data232\": " + rs.getString("maharregt") + ",\"data242\": " + rs.getString("nagaregt")
				        + ",\"data252\": " + rs.getString("jammuandkashmirrifles") + ",\"data262\": "
				        + rs.getString("ladakhscouts") + "," + "\"data272\": "
				        + rs.getString("jammuandkashmirligthinfregiment") + "}";



			}
			list += "]";

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

	// rk vs parent arm
	@Override
	public String Getrk_parent_armtlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and  orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service in (" + temp5 + ")   ";
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();

			
			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '')  as rank,m.id,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY AVIATION') as armyaviation,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ENGINEERS') as engineers,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='INFANTRY') as infantry,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='SIGNALS') as signals,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='HEAD QUARTERS') as headquarters,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMOURED CORPS') as armouredcorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARTILLERY') as artillery,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY AIR DEFENCE [AAD]') as armyairdefence,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='GORKHA') as gorkha,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ELECTRONICS AND MECHANICAL ENGINEERS') as electronicsandmechanicalengineers,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY POSTAL SERVICES') as armypostalservices,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY EDUCATION CORPS') as armyeducationcorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='INTELLIGENCE CORPS') as intelligencecorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='JUDGE ADVOCATE GENERAL') as judgeadvocategeneral,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY PHYSICAL TRAINING CORPS') as armyphysicaltrainingcorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='PIONEERS') as pioneers,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='SPECIAL LIST') as speciallist,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY MEDICAL CORPS') as armymedicalcorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='ARMY DENTAL CORPS') as armydentalcorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='CORPS OF MILITARY POLICE[PROVOST]') as corpsofmilitarypolice,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='DEFENCE SECURITY CORPS') as defencesecuritycorps,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='DIRECTORATE GENERAL OF QUALITY ASSURANCE') as directorategeneralofqualityassurance,\r\n"
					+ "count(c.id) filter (where  e.arm_desc ='BRIGADE OF GUARDS [INF]') as brigadeofguards \r\n"
					+ "from tb_psg_census_jco_or_p c\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = c.rank\r\n"
					+ "inner join tb_miso_orbat_arm_code e on e.arm_code ::int= c.id\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"

					+ "where c.status in ('1','5')   "+  q +"  \r\n"
					+ "group by 1,2 order by m.id desc \r\n";

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}

				
				list += "{\"datapa\": \"" + rs.getString("rank") + "\", \"datapa1\": " + rs.getString("armyaviation")
		        + ", \"datapa2\": " + rs.getString("engineers") + ", \"datapa3\": " + rs.getString("infantry")
		        + ", \"datapa4\": " + rs.getString("signals") + ", \"datapa5\": " + rs.getString("headquarters")
		        + ", \"datapa6\": " + rs.getString("armouredcorps") + ", \"datapa7\": " + rs.getString("artillery")
		        + ", \"datapa8\": " + rs.getString("armyairdefence") + ", \"datapa9\": " + rs.getString("gorkha")
		        + ", \"datapa10\": " + rs.getString("electronicsandmechanicalengineers") + ", \"datapa11\": "
		        + rs.getString("armypostalservices") + ", \"datapa12\": " + rs.getString("armyeducationcorps")
		        + ", \"datapa13\": " + rs.getString("intelligencecorps") + ", \"datapa14\": "
		        + rs.getString("judgeadvocategeneral") + ", \"datapa15\": "
		        + rs.getString("armyphysicaltrainingcorps") + ", \"datapa16\": " + rs.getString("pioneers")
		        + ", \"datapa17\": " + rs.getString("speciallist") + ", \"datapa18\": "
		        + rs.getString("armymedicalcorps") + ", \"datapa19\": " + rs.getString("armydentalcorps")
		        + ", \"datapa20\": " + rs.getString("corpsofmilitarypolice") + ", \"datapa21\": "
		        + rs.getString("defencesecuritycorps") + ", \"datapa22\": "
		        + rs.getString("directorategeneralofqualityassurance") + ", \"datapa23\": "
		        + rs.getString("brigadeofguards") + " } ";


			}
			list += "]";

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

	// rk vs blood group
	@Override
	public String Getrk_blood_grouplist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}


if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
		
			qry = "select bl.blood_desc as blood_group,\r\n"
					+ "count(a.id) filter (where m.rank ='WARRANT OFFICER') as warrantofficer,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB MAJ / EQUIVALENT') as submajequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB / EQUIVALENT') as subequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NB SUB / EQUIVALENT') as nbsubequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='HAV / EQUIVALENT') as havequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NK / EQUIVALENT') as nkequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SEP / EQUIVALENT') as sepequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='RECTS') as rects\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_psg_mstr_blood bl on bl.id= a.blood_group\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5')  " + q + "    \r\n"
					+ "group by bl.blood_desc\r\n" + "";
			
			
			

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			
	if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}

			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				list += "{\"databg\": \"" + rs.getString("blood_group") + "\",\"databg1\": " + rs.getString("warrantofficer")
		        + ",\"databg2\": " + rs.getString("submajequivalent") + ",\"databg3\": " + rs.getString("subequivalent") + ","
		        + "\"databg4\": " + rs.getString("nbsubequivalent") + ",\"databg5\": " + rs.getString("havequivalent")
		        + ",\"databg6\": " + rs.getString("nkequivalent") + ",\"databg7\": " + rs.getString("sepequivalent") + ",\"databg8\": "
		        + rs.getString("rects") + " } ";


			}
			list += "]";

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

	// rk vs command
	@Override
	public String Getrk_commandlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";
		
		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service in (" + temp5 + ")   ";
			}
			

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
	

			qry = "SELECT orb.unit_name AS command,\r\n"
					+ "count(c.id) filter (where m.rank ='WARRANT OFFICER') as warrantofficer,\r\n"
					+ "count(c.id) filter (where m.rank ='SUB MAJ / EQUIVALENT') as submajequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='SUB / EQUIVALENT') as subequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='NB SUB / EQUIVALENT') as nbsubequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='HAV / EQUIVALENT') as havequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='NK / EQUIVALENT') as nkequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='SEP / EQUIVALENT') as sepequivalent,\r\n"
					+ "count(c.id) filter (where m.rank ='RECTS') as rects\r\n"
					+ "FROM tb_psg_census_jco_or_p c\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = c.rank\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where c.status in ('1','5')  " + q + "  \r\n"
					+ "group by orb.unit_name\r\n";
		
			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}

			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				
				list += "{\"datacomd\": \"" + rs.getString("command") + "\",\"datacomd1\": " + rs.getString("warrantofficer")
		        + ",\"datacomd2\": " + rs.getString("submajequivalent") + ",\"datacomd3\": " + rs.getString("subequivalent") + ","
		        + "\"datacomd4\": " + rs.getString("nbsubequivalent") + ",\"datacomd5\": " + rs.getString("havequivalent")
		        + ",\"datacomd6\": " + rs.getString("nkequivalent") + ",\"datacomd7\": " + rs.getString("sepequivalent")
		        + ",\"datacomd8\": " + rs.getString("rects") + " } ";


			}
			list += "]";

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
	// rk vs loc

	@Override
	public String Getrk_loclist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1,
			String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {
			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(fv.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(fvm.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(div.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and bde.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service in (" + temp5 + ")   ";
			}
			
	
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
			

			qry = "select n.rank as rank,n.id,\r\n"
					+ "count(c.id) filter (where  m.code_value ='BARODA') as baroda, \r\n"
					+ "count(c.id) filter (where  m.code_value ='CI PEACE') as cipeace, \r\n"
					+ "count(c.id) filter (where  m.code_value ='GANDHINAGAR') as gandhinagar, \r\n"
					+ "count(c.id) filter (where  m.code_value ='HIMMATNAGAR') as himmatnagar, \r\n"
					+ "count(c.id) filter (where  m.code_value ='LOC ABC') as locabc, \r\n"
					+ "count(c.id) filter (where  m.code_value ='LOC BBB') as locbbb,\r\n"
					+ "count(c.id) filter (where  m.code_value ='LOC CDE') as loccde, \r\n"
					+ "count(c.id) filter (where  m.code_value ='LOC PAPA') as locpapa, \r\n"
					+ "count(c.id) filter (where  m.code_value ='LOC PAPUU') as locpapuu, \r\n"
					+ "count(c.id) filter (where  m.code_value ='NEW LOCAION') as newlocaion, \r\n"
					+ "count(c.id) filter (where  m.code_value ='PEACE') as peace, \r\n"
					+ "count(c.id) filter (where  m.code_value ='SIACHEN') as siachen ,\r\n"
					+ "count(c.id) filter (where  m.code_value ='TEST LOC PAR') as testlocpar \r\n"
					+ "FROM TB_MISO_ORBAT_UNT_DTL b\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_CODE m ON b.CODE = m.CODE\r\n"
					+ "right join tb_psg_census_jco_or_p c on c.unit_sus_no = b.sus_no and c.status in ('1','5')\r\n"
					+ "inner join tb_psg_mstr_rank_jco n on n.id = c.rank\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = c.regiment\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = c.arm_service \r\n"
					+ "where B.STATUS_SUS_NO  = 'Active'  "+  q +"   \r\n"
					+ "GROUP BY 1,2\r\n"
					+ "ORDER BY 2 desc";


			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}

			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}

			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

							String[] datarank = parent_arm1.split(",");
							for (int i = 0; i < datarank.length; i++) {
								stmt.setString(j, datarank[i]);
								j += 1;

							}
						}
			
				
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}

				list += "{'dataloc': '" + rs.getString("rank") + "','dataloc1': " + rs.getString("baroda")
						+ ",'dataloc2': " + rs.getString("cipeace") + ",'dataloc3': " + rs.getString("gandhinagar")
						+ "," + "'dataloc4': " + rs.getString("himmatnagar") + ",'dataloc5': " + rs.getString("locabc")
						+ ",'dataloc6': " + rs.getString("locbbb") + ",'dataloc7': " + rs.getString("loccde")
						+ ",'dataloc8': " + rs.getString("locpapa") + " ," + "'dataloc9': " + rs.getString("locpapuu")
						+ ",'dataloc10': " + rs.getString("newlocaion") + ",'dataloc11': " + rs.getString("peace")
						+ ",'dataloc12': " + rs.getString("siachen") + ",'dataloc13': " + rs.getString("testlocpar")
						+ "} ";

			}
			list += "]";

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

	// rk vs mother tongue
	@Override
	public String Getrk_mothertonguelist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			conn = dataSource.getConnection();
			
			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '')   as rank,m.id,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='ASSAMESE') as ASSAMESE,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='BENGALI') as BENGALI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='BODO') as BODO,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='DOGRI') as DOGRI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='ENGLISH') as  ENGLISH,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='GUJARATI') as GUJARATI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='HINDI') as HINDI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='KANNADA') as KANNADA,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='KASHMIRI') as KASHMIRI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='KONKANI') as KONKANI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='MAITHILI') as MAITHILI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='MALAYALAM') as MALAYALAM,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='MARATHI') as  MARATHI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='MEITEI[MANIPURI]') as MEITEIMANIPURI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='NEPALI') as NEPALI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='ORIYA') as ORIYA,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='OTHERS ') as OTHERS,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='PUNJABI') as PUNJABI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='SANSKRIT') as SANSKRIT,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='SANTHALI') as SANTHALI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='SINDHI') as  SINDHI,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='TAMIL') as TAMIL,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='TELUGU') as TELUGU,\r\n"
					+ "count(a.id) filter (where  s.mothertounge ='URDU') as URDU\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_psg_mothertounge s on s.id = a.mother_tongue and s.status = 'active'\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"

					+ "where a.status in ('1','5')  " + q + "  \r\n"
					+ "group by m.rank ,m.id\r\n"
					+ "order by m.id desc\r\n"
				    + "";

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}

			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}


				
				list += "{\"datamot\": \"" + rs.getString("rank") + "\",\"datamot1\": " + rs.getString("assamese")
		        + ",\"datamot2\": " + rs.getString("bengali") + ",\"datamot3\": " + rs.getString("bodo") + ","
		        + "\"datamot4\": " + rs.getString("dogri") + ",\"datamot5\": " + rs.getString("english")
		        + ",\"datamot6\": " + rs.getString("gujarati") + ",\"datamot7\": " + rs.getString("hindi")
		        + ",\"datamot8\": " + rs.getString("kannada") + ","
		        + "\"datamot9\": " + rs.getString("kashmiri") + ",\"datamot10\": " + rs.getString("konkani")
		        + ",\"datamot11\": " + rs.getString("maithili") + ",\"datamot12\": " + rs.getString("malayalam")
		        + ",\"datamot13\": " + rs.getString("marathi") + ","
		        + "\"datamot14\": " + rs.getString("meiteimanipuri") + ",\"datamot15\": " + rs.getString("nepali")
		        + ",\"datamot16\": " + rs.getString("oriya") + ",\"datamot17\": " + rs.getString("others")
		        + ",\"datamot18\": " + rs.getString("punjabi") + ","
		        + "\"datamot19\": " + rs.getString("sanskrit") + ",\"datamot20\": " + rs.getString("santhali")
		        + ",\"datamot21\": " + rs.getString("sindhi") + ",\"datamot22\": " + rs.getString("tamil")
		        + ",\"datamot23\": " + rs.getString("telugu") + ",\"datamot24\": " + rs.getString("urdu") + "}";


			}
			list += "]";

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

	// rk vs user arm

	@Override
	public String Getrk_userarmlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and    orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service in (" + temp5 + ")   ";
			}
			
			

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}

			conn = dataSource.getConnection();


			
			qry="SELECT n.rank as rank,n.id,\r\n"
					+ "count(c.id) filter (where  d.ARM_DESC ='ARMOURED CORPS') as armouredcorps,\r\n"
					+ "count(c.id) filter (where  d.ARM_DESC ='ARTILLERY') as artillery,\r\n"
					+ "count(c.id) filter (where  d.ARM_DESC ='ELECTRONICS AND MECHANICAL ENGINEERS') as electronicsandmechanicalengineers,\r\n"
					+ "count(c.id) filter (where  d.ARM_DESC ='TERRITORIAL ARMY') as territorialarmy\r\n"
					+ "FROM tb_psg_census_jco_or_p c\r\n"
					+ "inner join tb_psg_mstr_rank_jco n on n.id = c.rank \r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text =c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = c.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = c.arm_service \r\n"
					+ "WHERE c.status in ('1','5')   " + q + " \r\n"
					+ "GROUP BY  1,2\r\n"
					+ "order by n.id desc";


			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}

				list += "{'datauserarm': '" + rs.getString("rank") + "','datauserarm1': "
						+ rs.getString("armouredcorps") + ",'datauserarm2': " + rs.getString("artillery")
						+ ",'datauserarm3': " + rs.getString("electronicsandmechanicalengineers") + "," + "'datauserarm4': "
						+ rs.getString("territorialarmy") + "} ";

			}
			list += "]";

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

	// rk vs religion
	@Override
	public String Getrk_religionlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {
			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}

			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}


if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
			/*
			 * qry = "select s.religion_name,\r\n" +
			 * "count(cp.id) filter (where  m.description ='LT') as lt,\r\n" +
			 * "count(cp.id) filter (where  m.description ='CAPT') as capt,\r\n" +
			 * "count(cp.id) filter (where  m.description ='MAJ') as maj,\r\n" +
			 * "count(cp.id) filter (where  m.description ='LT COL') as ltcol,\r\n" +
			 * "count(cp.id) filter (where  m.description ='COL') as col,\r\n" +
			 * "count(cp.id) filter (where  m.description ='BRIG') as brig,\r\n" +
			 * "count(cp.id) filter (where  m.description ='MAJ GEN') as majgen,\r\n" +
			 * "count(cp.id) filter (where  m.description ='LT GEN') as ltgen,\r\n" +
			 * "count(cp.id) filter (where  m.description ='GEN') as gen\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "inner join tb_psg_census_detail_p cp on cp.comm_id = a.id\r\n" +
			 * "inner join tb_psg_mstr_religion s on s.religion_id = cp.religion and s.status='active' \r\n"
			 * + "group by 1";
			 */

			/*
			 * qry ="select s.religion_name,\r\n" +
			 * "count(a.id) filter (where  m.description ='LT') as lt,\r\n" +
			 * "count(a.id) filter (where  m.description ='CAPT') as capt,\r\n" +
			 * "count(a.id) filter (where  m.description ='MAJ') as maj,\r\n" +
			 * "count(a.id) filter (where  m.description ='LT COL') as ltcol,\r\n" +
			 * "count(a.id) filter (where  m.description ='COL [TS]') as colts,\r\n" +
			 * "count(a.id) filter (where  m.description ='COL') as col,\r\n" +
			 * "count(a.id) filter (where  m.description ='BRIG') as brig,\r\n" +
			 * "count(a.id) filter (where  m.description ='MAJ GEN') as majgen,\r\n" +
			 * "count(a.id) filter (where  m.description ='LT GEN') as ltgen,\r\n" +
			 * "count(a.id) filter (where  m.description ='GEN') as gen\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "inner join (select MAX(cs.created_date) as creat_date, cs.comm_id, cs.religion from tb_psg_census_detail_p cs\r\n"
			 * + "	where cs.status = '1'\r\n" +
			 * "	group by cs.comm_id, cs.religion)cp on cp.comm_id = a.id\r\n" +
			 * "inner join tb_psg_mstr_religion s on s.religion_id = cp.religion and s.status='active' \r\n"
			 * + "left join tb_miso_orbat_unt_dtl o on o.sus_no = a.unit_sus_no \r\n"+
			 * "where a.status in ('1','5')\r\n" +
			 * "and a.type_of_comm_granted != '20' "+q+" \r\n" + "group by 1";
			 */

			qry = "select s.religion_name,\r\n"
					+ "count(a.id) filter (where m.rank ='WARRANT OFFICER') as warrantofficer,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB MAJ / EQUIVALENT') as submajequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB / EQUIVALENT') as subequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NB SUB / EQUIVALENT') as nbsubequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='HAV / EQUIVALENT') as havequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NK / EQUIVALENT') as nkequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SEP / EQUIVALENT') as sepequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='RECTS') as rects\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_psg_mstr_religion s on s.religion_id = a.religion and s.status='active'\r\n"
					+ "inner JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
					+ "where a.status in ('1','5')   " + q + "  \r\n"
					+ "group by s.religion_name\r\n" + "";

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
		
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}

//				list += "{'datareli': '" + rs.getString("religion_name") + "','datareli1': " + rs.getString("warrantofficer")
//						+ ",'datareli2': " + rs.getString("submajequivalent") + ",'datareli3': " + rs.getString("subequivalent") + ","
//						+ "'datareli4': " + rs.getString("nbsubequivalent") + ",'datareli5': " + rs.getString("havequivalent")
//						+ ",'datareli6': " + rs.getString("nkequivalent") + ",'datareli7': " + rs.getString("sepequivalent")
//						+ ",'datareli8': " + rs.getString("rects") + " } ";

			
				list += "{\"datareli\": \"" + rs.getString("religion_name") + "\",\"datareli1\": " + rs.getString("warrantofficer")
		        + ",\"datareli2\": " + rs.getString("submajequivalent") + ",\"datareli3\": " + rs.getString("subequivalent") + ","
		        + "\"datareli4\": " + rs.getString("nbsubequivalent") + ",\"datareli5\": " + rs.getString("havequivalent")
		        + ",\"datareli6\": " + rs.getString("nkequivalent") + ",\"datareli7\": " + rs.getString("sepequivalent")
		        + ",\"datareli8\": " + rs.getString("rects") + " } ";

				
				
				
			}
			list += "]";

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

	// rk vs gender
	@Override
	public String Getrk_genderlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.arm_service in (" + temp5 + ")   ";
			}

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and c.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
	
			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '')  as rank,\r\n"
					+ "count (c.id) filter (where c.gender = 6) as male,\r\n"
					+ "count (c.id) filter (where c.gender = 7) as female\r\n"
					+ "from tb_psg_census_jco_or_p c\r\n"
					+ "join tb_psg_mstr_rank_jco m on m.id = c.rank\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = c.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = c.arm_service \r\n"
					+ "where c.status in ('1','5')     " + q + "    \r\n"
					+ "group by m.rank \r\n";


			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
	
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
//				list += "{'datagen': '" + rs.getString("rank") + "', 'datagen1': " + rs.getString("male")
//						+ ", 'datagen2':  " + rs.getString("female") + " } ";
				
				list += "{\"datagen\": \"" + rs.getString("rank") + "\", \"datagen1\": " + rs.getString("male") + ", \"datagen2\": " + rs.getString("female") + " }";


			}
			list += "]";

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

	// rk vs marital status
	@Override
	public String Getrk_marital_statuslist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			

if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
		
			qry = "select  REPLACE(m.rank, '/ EQUIVALENT', '')  as rank,m.id,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Married') as married,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Divorced') as divorced,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Widower') as widower,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Unmarried') as unmarried,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Widow') as widow,\r\n"
					+ "count(a.id) filter (where s.marital_name ='Separated') as separated\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "inner join tb_psg_mstr_marital_status s on s.marital_id = a.marital_status and s.status='active'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
					+ "where a.status in ('1','5')     " + q + "    \r\n"
					+ "group by m.rank,m.id";


			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
				
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
		
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
//				list += "{'datamarital': '" + rs.getString("rank") + "', 'datamarital1': " + rs.getString("married")
//						+ ", 'datamarital2':  " + rs.getString("divorced") + "," + " 'datamarital3':  "
//						+ rs.getString("widower") + " ,'datamarital4':  " + rs.getString("unmarried")
//						+ ",'datamarital5':  " + rs.getString("widow") + ",'datamarital6':  "
//						+ rs.getString("separated") + "} ";
				
				list += "{\"datamarital\": \"" + rs.getString("rank") + "\", \"datamarital1\": " + rs.getString("married")
		        + ", \"datamarital2\": " + rs.getString("divorced") + "," + " \"datamarital3\": "
		        + rs.getString("widower") + " ,\"datamarital4\": " + rs.getString("unmarried")
		        + ",\"datamarital5\": " + rs.getString("widow") + ",\"datamarital6\": "
		        + rs.getString("separated") + "}";


			}
			list += "]";

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

	// rk vs border area
	@Override
	public String Getrk_borderlist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {
			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(fv.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(fvm.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(div.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and bde.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			

			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			conn = dataSource.getConnection();
			/*
			 * qry = "select m.description as rank,m.id,\r\n" +
			 * "count(cp.id) filter (where  s.permanent_border_area ='yes') as yes,\r\n" +
			 * "count(cp.id) filter (where  s.permanent_border_area ='no') as no\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "inner join tb_psg_census_detail_p cp on cp.comm_id = a.id\r\n" +
			 * "inner join tb_psg_census_address s on s.cen_id = cp.id  \r\n" +
			 * "group by 1,2 order by m.id desc";
			 */

			/*
			 * qry ="\r\n" + "select m.description as rank,m.id,\r\n" +
			 * "count(a.id) filter (where  s.permanent_border_area ='yes') as yes,\r\n" +
			 * "count(a.id) filter (where  s.permanent_border_area ='no') as no\r\n" +
			 * "from tb_psg_trans_proposed_comm_letter a\r\n" +
			 * "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n" +
			 * "inner join (select MAX(cs.created_date) as creat_date, cs.comm_id from tb_psg_census_detail_p cs\r\n"
			 * + "			where cs.status = '1'\r\n" +
			 * "			group by cs.comm_id)cp on cp.comm_id = a.id\r\n" +
			 * "inner join tb_psg_census_address s on s.comm_id = cp.comm_id  and s.status = '1'\r\n"
			 * + "left join tb_miso_orbat_unt_dtl o on o.sus_no = a.unit_sus_no  \r\n" +
			 * "where a.status in ('1','5')\r\n" +
			 * "and a.type_of_comm_granted != '20' "+q+" \r\n" +
			 * "group by 1,2 order by m.id desc";
			 */

			qry = "select m.description as rank,m.id,\r\n"
					+ "count(a.id) filter (where s.permanent_border_area ='yes') as yes,\r\n"
					+ "count(a.id) filter (where s.permanent_border_area ='no') as no\r\n"
					+ "from tb_psg_trans_proposed_comm_letter a\r\n"
					+ "inner join cue_tb_psg_rank_app_master m on m.id = a.rank\r\n"
					+ "inner join (select MAX(cs.created_date) as creat_date, cs.comm_id from tb_psg_census_detail_p cs\r\n"
					+ "where cs.status = '1'\r\n" + "group by cs.comm_id)cp on cp.comm_id = a.id\r\n"
					+ "inner join tb_psg_census_address s on s.comm_id = cp.comm_id and s.status = '1'\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.parent_arm\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
					+ "where a.status in ('1','5')\r\n" + "and a.type_of_comm_granted != '20' " + q + " \r\n"
					+ "group by m.description,m.id\r\n" + "order by m.id desc";

			PreparedStatement stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
//				list += "{'databorder': '" + rs.getString("rank") + "', 'databorder1': " + rs.getString("yes")
//						+ ", 'databorder2':  " + rs.getString("no") + "} ";
				list += "{\"databorder\": \"" + rs.getString("rank") + "\", \"databorder1\": " + rs.getString("yes")
		        + ", \"databorder2\": " + rs.getString("no") + "}";


			}
			list += "]";

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

	// rk vs dos
	@Override
	public String Getrk_doslist_jco_or(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1
			,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {
			if (cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank1 != null && !rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and a.rank::varchar in (" + temp4 + ")   ";
			}
			if (parent_arm1 != null && !parent_arm1.equals("")) {
				String temp5 = "";
				String[] datarank = parent_arm1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs1 != null && !regs1.equals("")) {
				String temp5 = "";
				String[] datarank = regs1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			
			conn = dataSource.getConnection();
	

			qry = "select split_part(a.date_of_seniority::TEXT,'-',1) as dos,\r\n"
					+ "count(a.id) filter (where m.rank ='WARRANT OFFICER') as warrantofficer,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB MAJ / EQUIVALENT') as submajequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB / EQUIVALENT') as subequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NB SUB / EQUIVALENT') as nbsubequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='HAV / EQUIVALENT') as havequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NK / EQUIVALENT') as nkequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SEP / EQUIVALENT') as sepequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='RECTS') as rects\r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "where a.status in ('1','5')    " + q + "    \r\n"
					+ "group by split_part(a.date_of_seniority::TEXT,'-',1) \r\n"
					+ "order by dos asc\r\n";
		
			PreparedStatement stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd1 != null &&  !cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps1 != null &&  !cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div1 != null && !cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde1 != null && !cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank1 != null && !rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (parent_arm1 != null && !parent_arm1.equals("")) {

				String[] datarank = parent_arm1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name1!= null && !unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs1 != null && !regs1.equals("")) {

				String[] datarank = regs1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}

//				list += "{'datados': '" + rs.getString("dos") + "','datados1': " + rs.getString("warrantofficer") + ",'datados2': "
//						+ rs.getString("submajequivalent") + ",'datados3': " + rs.getString("subequivalent") + "," + "'datados4': "
//						+ rs.getString("nbsubequivalent") + ",'datados5': " + rs.getString("havequivalent") + ",'datados6': "
//						+ rs.getString("nkequivalent") + ",'datados7': " + rs.getString("sepequivalent") + ",'datados8': "
//						+ rs.getString("rects") +  " } ";
				
				list += "{\"datados\": \"" + rs.getString("dos") + "\",\"datados1\": " + rs.getString("warrantofficer") + ",\"datados2\": "
				        + rs.getString("submajequivalent") + ",\"datados3\": " + rs.getString("subequivalent") + "," + "\"datados4\": "
				        + rs.getString("nbsubequivalent") + ",\"datados5\": " + rs.getString("havequivalent") + ",\"datados6\": "
				        + rs.getString("nkequivalent") + ",\"datados7\": " + rs.getString("sepequivalent") + ",\"datados8\": "
				        + rs.getString("rects") +  " } ";


				
				
				
				
				
			}
			list += "]";

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

//	@Override
//	public String Getrk_state_armtlist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
//			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
//			String regs1, String parent_arm1,String unit_name1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public String Getrk_state_armtlist_jco_or(String cont_comd1, String cont_corps1, String cont_div1,
			String cont_bde1, String rank1, String arm1, String parm1, String cmd1, String div1, String corps1,
			String bdes1, String regs1,String parent_arm1,String unit_name1) {
		// TODO Auto-generated method stub
		String list = "";
		Connection conn = null;
		String qry = "";
		
		String q = "";
		try {
			if(cont_comd1 != null &&  !cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(Pattern.quote("|"));
                 
  				for (int i = 0 ; i < datacmd.length; i++) {
  					
     				
  				if (i == 0)
  				{
  					
  					temp = temp + "?";
  				}
  				else
  				{
  					
  					temp = temp + ",?";
                    }
  				}
	
			q += "and SUBSTR(orb.form_code_control,1,1) in (" +temp+")  ";
			}
			if(cont_corps1 != null &&  !cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(Pattern.quote("|"));
                 
  				for (int i = 0 ; i < datacr.length; i++) {
  					
     				
  				if (i == 0)
  				{
  					
  					temp1 = temp1 + "?";
  				}
  				else
  				{
  					
  					temp1 = temp1 + ",?";
                    }
  				}
				
				q += "and SUBSTR(orb.form_code_control,1,3) in (" +temp1+")  ";
			}
			if(cont_div1 != null && !cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(Pattern.quote("|"));
                 
  				for (int i = 0 ; i < datadiv.length; i++) {
  					
     				
  				if (i == 0)
  				{
  					
  					temp2 = temp2 + "?";
  				}
  				else
  				{
  					
  					temp2 = temp2 + ",?";
                    }
  				}
				q += "and SUBSTR(orb.form_code_control,1,6) in (" +temp2+") ";
			}
			if(cont_bde1 != null && !cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(Pattern.quote("|"));
                 
  				for (int i = 0 ; i < databde.length; i++) {
  					
     				
  				if (i == 0)
  				{
  					
  					temp3 = temp3 + "?";
  				}
  				else
  				{
  					
  					temp3 = temp3 + ",?";
                    }
  				}
				q += "and orb.form_code_control in (" +temp3+")  ";
			}
     if(rank1 != null && !rank1.equals("")) {
				
				String temp4 = "";
				String[] datarank = rank1.split(",");
                 
  				for (int i = 0 ; i < datarank.length; i++) {
  					
     				
  				if (i == 0)
  				{
  					
  					temp4 = temp4 + "?";
  				}
  				else
  				{
  					
  					temp4 = temp4 + ",?";
                    }
  				}
				q += "and a.rank::varchar in (" +temp4+ ")   ";
			}

     if (regs1 != null && !regs1.equals("")) {
			String temp5 = "";
			String[] datarank = regs1.split(",");

			for (int i = 0; i < datarank.length; i++) {

				if (i == 0) {

					temp5 = temp5 + "?";
				} else {

					temp5 = temp5 + ",?";
				}
			}
			q += "and a.regiment in (" + temp5 + ")   ";
		}
		
			conn = dataSource.getConnection();
			
			
			qry ="select a.id as mainid,\r\n"
					+ "count(a.id) filter (where m.rank ='WARRANT OFFICER') as warrantofficer,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB MAJ / EQUIVALENT') as submajequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SUB / EQUIVALENT') as subequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NB SUB / EQUIVALENT') as nbsubequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='HAV / EQUIVALENT') as havequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='NK / EQUIVALENT') as nkequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='SEP / EQUIVALENT') as sepequivalent,\r\n"
					+ "count(a.id) filter (where m.rank ='RECTS') as rects,\r\n"
					+ "count(a.id) as total \r\n"
					+ "from tb_psg_census_jco_or_p a\r\n"
					+ "inner join tb_psg_mstr_rank_jco m on m.id = a.rank\r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
//					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text =a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
//					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = a.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text\r\n"
//					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = a.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE f ON f.arm_code = a.regiment\r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE e ON e.arm_code = a.arm_service \r\n"
//					+ "LEFT JOIN TB_MISO_ORBAT_ARM_CODE d ON  D.ARM_CODE = SUBSTR(orb.ARM_code,1,2)|| '00' \r\n"
					+ "where a.status in ('1','5') "+q+"  \r\n"
					+ "group by a.id\r\n"
					+ "order by 1";
			
//			System.out.println("Himadri----state-----"+qry);
			PreparedStatement stmt = conn.prepareStatement(qry);
			
	            int j =1;
				

         if(cont_comd1 != null &&  !cont_comd1.equals("")) {
				
				 String[] datacmd = cont_comd1.split(Pattern.quote("|"));
      				for (int i = 0 ; i < datacmd.length; i++) {
      				 stmt.setString(j,datacmd[i]);
      					 j += 1;
					
                        }
				}
             if(cont_corps1 != null &&  !cont_corps1.equals("")) {
            	 
             String[] datacr = cont_corps1.split(Pattern.quote("|"));
  				for (int i = 0 ; i < datacr.length; i++) {
  				 stmt.setString(j,datacr[i]);
  					 j += 1;
				
                    }
  						
				}
            
             if(cont_div1 != null && !cont_div1.equals("")) {
					
            	 String[] datadiv = cont_div1.split(Pattern.quote("|"));
      				for (int i = 0 ; i < datadiv.length; i++) {
      				 stmt.setString(j,datadiv[i]);
      					 j += 1;
					
                        }
					}
             if(cont_bde1 != null && !cont_bde1.equals("")) {
					
            	 String[] databde = cont_bde1.split(Pattern.quote("|"));
      				for (int i = 0 ; i < databde.length; i++) {
      				 stmt.setString(j,databde[i]);
      					 j += 1;
					
                        }
					}
             if(rank1 != null && !rank1.equals("")) {
            	 	
					 String[] datarank = rank1.split(",");
      				for (int i = 0 ; i < datarank.length; i++) {
      					stmt.setString(j,datarank[i]);
      					 j += 1;
					
                        }
					}
             if (regs1 != null && !regs1.equals("")) {

 				String[] datarank = regs1.split(",");
 				for (int i = 0; i < datarank.length; i++) {
 					stmt.setString(j, datarank[i]);
 					j += 1;

 				}
 			}
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				//list += "[ 'id': " + rs.getString("id")  +"', 'total': " + rs.getString("total") + "] ";
				list += "{'id': '"+rs.getString("mainid") +"', 'total': " + rs.getString("total")  + "} ";
			}
			list += "]";
			
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

	
	
	//----------------------------------------------------------jco 
	@Override
	public DataSet<TB_CENSUS_JCO_OR_PARENT> DatatablesCriteriasFormationWisejco(DatatablesCriterias criterias) throws SQLException {

			List<TB_CENSUS_JCO_OR_PARENT> metadata = findDepartmentCriteriasWisejco(criterias);
			
			Long countFiltered = getFilteredCountjco(criterias); 
			
			Long count = getTotalCountjco();
		
		return new DataSet<TB_CENSUS_JCO_OR_PARENT>(metadata, count, countFiltered);
	}


//	@SuppressWarnings("unchecked")
//	private List<TB_CENSUS_JCO_OR_PARENT> findDepartmentCriteriasWisejco(
//			DatatablesCriterias criterias) {
//		StringBuilder queryBuilder = null;
//		queryBuilder = new StringBuilder(" select distinct d from TB_CENSUS_JCO_OR_PARENT d , Miso_Orbat_Unt_Dtl u"
//				+ "	 where  d.unit_sus_no=u.sus_no  "
//				+ "	and d.status in ('1','5')   ");
//		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
//		if (criterias.hasOneSortedColumn()) {
//			List<String> orderParams = new ArrayList<String>();
//			queryBuilder.append(" ORDER BY ");
//			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
//				if (columnDef.getName().equals("")) {
//					String st = " ORDER BY";
//					int i = queryBuilder.indexOf(st);
//					if (i != -1) {
//						queryBuilder.delete(i, i + st.length());
//					}
//				} else if (columnDef.getName().contains("hodProfile.fullName")) {
//					orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
//				} else {
//					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
//				}
//			}
//			Iterator<String> itr2 = orderParams.iterator();
//			while (itr2.hasNext()) {
//				queryBuilder.append(itr2.next());
//				if (itr2.hasNext()) {
//					queryBuilder.append(" , ");
//				}
//			}
//		}
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		Query q = session.createQuery(queryBuilder.toString());
//		q.setFirstResult(criterias.getDisplayStart());
//		q.setMaxResults(criterias.getDisplaySize());
//		System.out.println("-----------------------------------"+q);
//		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) q.list();
//		tx.commit();
//		session.close();
//		return list;
//	}
	
	
	@SuppressWarnings("unchecked")
	private List<TB_CENSUS_JCO_OR_PARENT> findDepartmentCriteriasWisejco(
			DatatablesCriterias criterias) throws SQLException {
		List<TB_CENSUS_JCO_OR_PARENT> list =new  ArrayList<TB_CENSUS_JCO_OR_PARENT>();
		Connection conn = null;
		String qry = "";
		try {
					conn = dataSource.getConnection();
					qry="  select  d.id,d.army_no,d.full_name ,d.unit_sus_no from tb_psg_census_jco_or_p d , tb_miso_orbat_unt_dtl u "
							+ "	 where  d.unit_sus_no=u.sus_no  "
							+ "	and d.status in ('1','5') order by d.id  ";
			
					PreparedStatement stmt = conn.prepareStatement(qry);
					ResultSet rs = stmt.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
				
				
					while(rs.next())
					{
						TB_CENSUS_JCO_OR_PARENT p= new TB_CENSUS_JCO_OR_PARENT();
							p.setId( (int)rs.getObject(1));
							p.setArmy_no((String) rs.getObject(2));
							p.setFull_name((String) rs.getObject(3));
							p.setUnit_sus_no((String) rs.getObject(4));
							
			
						list.add(p);
					}
					
			
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
	
	
	
	
	
	private static StringBuilder getFilterQueryueuh(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
				}
			}
			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
			queryBuilder.append(")");
		}
		return queryBuilder;
	}
	
	@SuppressWarnings("unchecked")
	private Long getFilteredCountjco(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
	queryBuilder = new StringBuilder("SELECT  distinct d.id  FROM TB_CENSUS_JCO_OR_PARENT d where d.status='1'   " );
		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery(queryBuilder.toString());
		
		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	
	private Long getTotalCountjco() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(distinct d.id) FROM TB_CENSUS_JCO_OR_PARENT d  where d.status='1'  ");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	
	
	
	////---------------------------------------------------unit
	public DataSet<TB_CENSUS_JCO_OR_PARENT> DatatablesCriteriasUnitWiseoff(
			DatatablesCriterias criterias) {

		List<TB_CENSUS_JCO_OR_PARENT> metadata = findDepartmentUnitWiseueuh(criterias);

		Long countFiltered = getUnitCountueuh(criterias); 

		Long count = getTotalCountunit();
		return new DataSet<TB_CENSUS_JCO_OR_PARENT>(metadata, count, countFiltered);
	}
	private Long getTotalCountunit() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(DISTINCT  d.unit_sus_no) FROM TB_CENSUS_JCO_OR_PARENT d ,Miso_Orbat_Unt_Dtl o  where o.sus_no=d.unit_sus_no and d.status='1'  and o.status_sus_no='Active'");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	@SuppressWarnings("unchecked")
	private List<TB_CENSUS_JCO_OR_PARENT> findDepartmentUnitWiseueuh(
			DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		//queryBuilder = new StringBuilder("SELECT distinct d.unit_sus_no FROM TB_TRANS_PROPOSED_COMM_LETTER d ");
		queryBuilder = new StringBuilder("select d from  Miso_Orbat_Unt_Dtl  d "
				+ "where d.sus_no in(select distinct unit_sus_no from TB_CENSUS_JCO_OR_PARENT where status='1' ) AND 	d.status_sus_no='Active'  ");
		
		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
					String st = " ORDER BY";
					int i = queryBuilder.indexOf(st);
					if (i != -1) {
						queryBuilder.delete(i, i + st.length());
					}
				} else if (columnDef.getName().contains("hodProfile.fullName")) {
					orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
				} else {
					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
				}
			}
			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) q.list();

		tx.commit();
		session.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	private Long getUnitCountueuh(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT distinct d.unit_sus_no FROM TB_CENSUS_JCO_OR_PARENT d   ,Miso_Orbat_Unt_Dtl o  where o.sus_no=d.unit_sus_no and  d.status='1' and o.status_sus_no='Active'  ");
		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		List<TB_CENSUS_JCO_OR_PARENT> list = (List<TB_CENSUS_JCO_OR_PARENT>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	@Override
	public int FindJCOcount(String search, String cont_comd4, String cont_corps4, String cont_div4, String cont_bde4,
			String rank4, String arm4, String parm4, String cmd4, String div4, String corps4, String bdes4,
			String regs4, String parent_arm4, String unit_name4) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		int total=0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd4.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps4.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div4 != null && !cont_div4.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div4.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde4.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank4 != null && !rank4.equals("")) {

				String temp4 = "";
				String[] datarank = rank4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm4.equals("")) {
				String temp5 = "";
				String[] datarank = parm4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name4!= null && !unit_name4.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs4 != null && !regs4.equals("")) {
				String temp5 = "";
				String[] datarank = regs4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}

			if(!search.equals(""))
			{
				q+= " and ( cast(a.id as char) like ? or  a.army_no like ?  or upper(a.full_name) like ? or a.unit_sus_no like ?  ) ";
				
			}
			
			qry = " select count(app.id)as total  from ( select distinct a.id,a.army_no,a.full_name ,a.unit_sus_no  from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5') " + q +") app";
		
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {

				String[] datacmd = cont_comd4.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {

				String[] datacr = cont_corps4.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div4 != null && !cont_div4.equals("")) {

				String[] datadiv = cont_div4.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {

				String[] databde = cont_bde4.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank4 != null && !rank4.equals("")) {

				String[] datarank = rank4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm4.equals("")) {

				String[] datarank = parm4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name4!= null && !unit_name4.equals("")) {

				String[] datarank = unit_name4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs4 != null && !regs4.equals("")) {

				String[] datarank = regs4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if(!search.equals("")) {
//				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
			

			}
	
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				
			total=	Integer.parseInt(rs.getString("total"));

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
	public ArrayList<ArrayList<String>> FindJCOtable(int startPage, int pageLength, String search, String cont_comd4,
			String cont_corps4, String cont_div4, String cont_bde4, String rank4, String arm4, String parm4,
			String cmd4, String div4, String corps4, String bdes4, String regs4, String parent_arm4,
			String unit_name4) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd4.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps4.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div4 != null && !cont_div4.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div4.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde4.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control in (" + temp3 + ")  ";
			}
			if (rank4 != null && !rank4.equals("")) {

				String temp4 = "";
				String[] datarank = rank4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm4.equals("")) {
				String temp5 = "";
				String[] datarank = parm4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name4!= null && !unit_name4.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs4 != null && !regs4.equals("")) {
				String temp5 = "";
				String[] datarank = regs4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			if(!search.equals(""))
			{
				q+= " and ( cast(a.id as char)  like ? or  a.army_no like ?  or upper(a.full_name) like ? or a.unit_sus_no like ?  ) ";
				
			}
			String pageL = "";

if(pageLength == -1){
	pageL = "ALL";
}else {
	pageL = String.valueOf(pageLength);
}
			qry = "select  distinct a.id,a.army_no,a.full_name ,a.unit_sus_no  from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5') " + q;
			qry+="   order by a.id " + " desc  limit " + pageL + " OFFSET " + startPage + "";
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {

				String[] datacmd = cont_comd4.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {

				String[] datacr = cont_corps4.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div4 != null && !cont_div4.equals("")) {

				String[] datadiv = cont_div4.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {

				String[] databde = cont_bde4.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank4 != null && !rank4.equals("")) {

				String[] datarank = rank4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm4.equals("")) {

				String[] datarank = parm4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name4!= null && !unit_name4.equals("")) {

				String[] datarank = unit_name4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs4 != null && !regs4.equals("")) {

				String[] datarank = regs4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if(!search.equals("")) {

				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
			
			}
		
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("army_no"));
				list.add(rs.getString("full_name"));
				list.add(rs.getString("unit_sus_no"));
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
	public ArrayList<ArrayList<String>> Findunittable_jco(int startPage, int pageLength, String search, String cont_comd4,
			String cont_corps4, String cont_div4, String cont_bde4, String rank4, String arm4, String parm4,
			String cmd4, String div4, String corps4, String bdes4, String regs4, String parent_arm4,
			String unit_name4) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd4.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps4.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div4 != null && !cont_div4.equals("")) {
				String temp2 = "";
	String[] datadiv = cont_div4.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde4.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control  in (" + temp3 + ")  ";
			}
			if (rank4 != null && !rank4.equals("")) {

				String temp4 = "";
				String[] datarank = rank4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm4.equals("")) {
				String temp5 = "";
				String[] datarank = parm4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name4!= null && !unit_name4.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs4 != null && !regs4.equals("")) {
				String temp5 = "";
				String[] datarank = regs4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			if(!search.equals(""))
			{
				q+= " and (a.unit_sus_no like ? or  upper(orb.unit_name) like ?   ) ";
				
			}
			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			} 
			qry = "select distinct a.unit_sus_no as unit,orb.unit_name from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5')  " + q;
			qry+="   order by a.unit_sus_no " + " desc  limit " + pageL + " OFFSET " + startPage + "";
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {

				String[] datacmd = cont_comd4.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {

				String[] datacr = cont_corps4.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div4 != null && !cont_div4.equals("")) {

				String[] datadiv = cont_div4.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {

				String[] databde = cont_bde4.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank4 != null && !rank4.equals("")) {

				String[] datarank = rank4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm4.equals("")) {

				String[] datarank = parm4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name4!= null && !unit_name4.equals("")) {

				String[] datarank = unit_name4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs4 != null && !regs4.equals("")) {

				String[] datarank = regs4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if(!search.equals("")) {
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
			}
		
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit"));
				list.add(rs.getString("unit_name"));
				alist.add(list);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
	public int Findunittable_joc_count(String search, String cont_comd4, String cont_corps4, String cont_div4,
			String cont_bde4, String rank4, String arm4, String parm4, String cmd4, String div4, String corps4,
			String bdes4, String regs4, String parent_arm4, String unit_name4) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		int total=0;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd4.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps4.split(Pattern.quote("|"));

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += " and SUBSTR(orb.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (cont_div4 != null && !cont_div4.equals("")) {
				String temp2 = "";
	String[] datadiv = cont_div4.split(Pattern.quote("|"));

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += " and SUBSTR(orb.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde4.split(Pattern.quote("|"));

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += " and orb.form_code_control  in (" + temp3 + ")  ";
			}
			if (rank4 != null && !rank4.equals("")) {

				String temp4 = "";
				String[] datarank = rank4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += " and a.rank::varchar in (" + temp4 + ")   ";
			}

			if (!parm4.equals("")) {
				String temp5 = "";
				String[] datarank = parm4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.arm_service in (" + temp5 + ")   ";
			}
			if (unit_name4!= null && !unit_name4.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			if (regs4 != null && !regs4.equals("")) {
				String temp5 = "";
				String[] datarank = regs4.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp5 = temp5 + "?";
					} else {

						temp5 = temp5 + ",?";
					}
				}
				q += "and a.regiment in (" + temp5 + ")   ";
			}
			
			if(!search.equals(""))
			{
				q+= " and (a.unit_sus_no like ? or  upper(orb.unit_name) like ?   ) ";
				
			}
			
			qry = "select count(app.unit) as total from (select distinct a.unit_sus_no as unit,orb.unit_name from tb_psg_census_jco_or_p a \r\n"
					+ "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = a.unit_sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "where a.status in ('1','5')  " + q+" ) app";
		
			stmt = conn.prepareStatement(qry);

			int j = 1;

			if (cont_comd4 != null &&  !cont_comd4.equals("")) {

				String[] datacmd = cont_comd4.split(Pattern.quote("|"));
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (cont_corps4 != null &&  !cont_corps4.equals("")) {

				String[] datacr = cont_corps4.split(Pattern.quote("|"));
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (cont_div4 != null && !cont_div4.equals("")) {

				String[] datadiv = cont_div4.split(Pattern.quote("|"));
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (cont_bde4 != null && !cont_bde4.equals("")) {

				String[] databde = cont_bde4.split(Pattern.quote("|"));
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (rank4 != null && !rank4.equals("")) {

				String[] datarank = rank4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (!parm4.equals("")) {

				String[] datarank = parm4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			if (unit_name4!= null && !unit_name4.equals("")) {

				String[] datarank = unit_name4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if (regs4 != null && !regs4.equals("")) {

				String[] datarank = regs4.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			if(!search.equals("")) {
				stmt.setString(j, "%" + search.toUpperCase() + "%");
				j += 1;
				stmt.setString(j, "%" + search.toUpperCase() + "%");
			}
		
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
total=Integer.parseInt(rs.getString("total"));
			

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
	
}
