package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.Assets.interUnitTransf_DAO;

public class MMS_UploadExcelDAOImpl implements MMS_UploadExcelDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> uploadvalidatelist(String sus_no, String para, String m1_from, String m1_to) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String Sql = "";
			String tbl = null;

			if (para.equalsIgnoreCase("DIR")) {
				tbl = "mms_tb_imp_dir";
				Sql = "select a.upload_date,a.issue_sus_no,b.unit_name,a.totq,a.iv_no,TO_CHAR(a.iv_date,'dd-mm-yyyy') as iv_date from "
						+ " (select cast(upload_date as date) as upload_date,issue_sus_no,count(eqpt_ser_no) as totq,max(iv_no) as iv_no,max(iv_date) as iv_date from "
						+ tbl + " " + " where cast(TO_CHAR(upload_date,'yyyy-MM-dd') as date) between '" + m1_from
						+ "' and '" + m1_to + "' "
						+ " group by cast(upload_date as date),issue_sus_no order by cast(upload_date as date) desc,issue_sus_no) a "
						+ " left join tb_miso_orbat_unt_dtl b on a.issue_sus_no=b.sus_no and b.status_sus_no='Active' ";

				if (!sus_no.equals(" ") && !sus_no.equals(null) && !sus_no.equals("")) {
					Sql = Sql + " and a.issue_sus_no=?";
				}
			}
			if (para.equalsIgnoreCase("DRR")) {
				tbl = "mms_tb_imp_drr";
				Sql = "select a.upload_date,a.recv_sus_no as issue_sus_no,b.unit_name,a.totq,a.rv_no as iv_no,TO_CHAR(a.rv_date,'dd-mm-yyyy') as iv_date from "
						+ " (select cast(data_cr_date as date) as upload_date,recv_sus_no,count(eqpt_ser_no) as totq,max(rv_no) as rv_no,max(rv_date) as rv_date from "
						+ tbl + " " + " where cast(TO_CHAR(data_cr_date,'yyyy-MM-dd') as date) between '" + m1_from
						+ "' and '" + m1_to + "' "
						+ " group by cast(data_cr_date as date),recv_sus_no order by cast(data_cr_date as date) desc,recv_sus_no) a "
						+ " left join tb_miso_orbat_unt_dtl b on a.recv_sus_no=b.sus_no and b.status_sus_no='Active' ";
				if (!sus_no.equals(" ") && !sus_no.equals(null) && !sus_no.equals("")) {
					Sql = Sql + " and a.recv_sus_no=?";
				}
			}
			if (para.equalsIgnoreCase("MSR")) {
				tbl = "mms_tb_imp_msr";
				Sql = "select u.unit_name,m.census_no,m.Stk_type,m.nomen,m.total_free_stock_held,m.Recd_Stk_DGOF,m.Recd_Stk_Trade_Import,\r\n"
						+ "		m.Recd_Stk_Repair,m.Recd_Stk_Other,m.Qty_Issued,m.Qty_UnderIssue,\r\n"
						+ "		m.VintageHeld,m.Res_held,m.Res_Type,m.ro_qty,m.rio_qty,m.dues_in \r\n"
						+ "		from mms_tb_imp_msr m\r\n"
						+ "	left join tb_miso_orbat_unt_dtl u on u.sus_no= m.depot_sus_no "
						+ "where cast(TO_CHAR(m.data_upd_date,'yyyy-MM-dd') as date) between '" + m1_from + "' and '"
						+ m1_to + "' ";

				if (!sus_no.equals(" ") && !sus_no.equals(null) && !sus_no.equals("")) {
					Sql = Sql + " and m.depot_sus_no=?";
				}
			}

			if (para.equalsIgnoreCase("DIR") || para.equalsIgnoreCase("DRR")) {

				PreparedStatement stmt = conn.prepareStatement(Sql);
				if (!sus_no.equals(" ") && !sus_no.equals(null) && !sus_no.equals("")) {
					stmt.setString(1, sus_no);
				}

				ResultSet rs = stmt.executeQuery();
				if (!rs.isBeforeFirst()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("NIL");
					li.add(list);
				} else {
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("upload_date"));
						list.add(rs.getString("issue_sus_no"));
						list.add(rs.getString("unit_name"));
						list.add(rs.getString("totq"));
						list.add(rs.getString("iv_no"));
						list.add(rs.getString("iv_date"));
						li.add(list);
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			}
			if (para.equalsIgnoreCase("MSR")) {

				PreparedStatement stmt = conn.prepareStatement(Sql);
				if (!sus_no.equals(" ") && !sus_no.equals(null) && !sus_no.equals("")) {
					stmt.setString(1, sus_no);
				}
				ResultSet rs = stmt.executeQuery();
				if (!rs.isBeforeFirst()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("NIL");
					li.add(list);
				} else {
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("unit_name"));
						list.add(rs.getString("census_no"));
						list.add(rs.getString("Stk_type"));
						list.add(rs.getString("nomen"));
						list.add(rs.getString("total_free_stock_held"));
						list.add(rs.getString("Recd_Stk_DGOF"));
						list.add(rs.getString("Recd_Stk_Trade_Import"));
						list.add(rs.getString("Recd_Stk_Repair"));
						list.add(rs.getString("Recd_Stk_Other"));
						list.add(rs.getString("Qty_Issued"));
						list.add(rs.getString("Qty_UnderIssue"));
						list.add(rs.getString("VintageHeld"));
						list.add(rs.getString("Res_held"));
						list.add(rs.getString("Res_Type"));
						list.add(rs.getString("ro_qty"));
						list.add(rs.getString("rio_qty"));
						list.add(rs.getString("dues_in"));
						li.add(list);
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	/*
	 * public String RegdTransLog(String regnSeqno, String regnno, String nFSUSNo,
	 * String nFPRF, String nFCensus,String nFMNo, String nFHldType, String
	 * nFEqptType, String nOldval, String nTSUSNo, String nTMNo, String nTHldType,
	 * String nTEqptType, String nNew,String nAuthNo, String nAuthDate, String
	 * nAuthType, String nOStatus,String nPara) {//nPara String[]
	 * tmpRegn=regnno.split(":");
	 * 
	 * try { Connection conn = null; conn = dataSource.getConnection(); Statement
	 * stmt = (Statement)conn.createStatement(); String nrSql=""; String nrCnd="";
	 * nrSql =
	 * "insert into mms_tb_regn_tr_log(regn_seq_no, eqpt_regn_no,from_sus_no,from_prf_code,from_census_no,"
	 * ; nrSql = nrSql +
	 * " from_material_no,from_hldg_ty,from_eqpt_ty,old_value,cur_sus_no,cur_prf_code,cur_census_no,"
	 * ; nrSql = nrSql +
	 * " cur_material_no,cur_hldg_ty,cur_eqpt_ty,new_value,tr_date,tran_type,auth_type,auth_no,auth_date,"
	 * ; nrSql = nrSql + " regn_count,op_status) "; nrSql = nrSql +
	 * " values ('"+regnSeqno+"','"+regnno+"','"+nFSUSNo+"','"+nFPRF+"','"+nFCensus+
	 * "','"+nFMNo+"','"+nFHldType+"','"+nFEqptType+"','"+nOldval+"',"; nrSql =
	 * nrSql +
	 * " '"+nTSUSNo+"','"+nFPRF+"','"+nFCensus+"','"+nTMNo+"','"+nTHldType+"','"+
	 * nTEqptType+"','"+nNew+"',"; nrSql = nrSql +
	 * " localtimestamp,'NEW','"+nAuthType+"','"+nAuthNo+"','"+nAuthDate+"',"+
	 * tmpRegn.length+",'"+nOStatus+"')"; int p=stmt.executeUpdate(nrSql);
	 * stmt.close(); conn.close(); } catch (Exception e) { e.printStackTrace(); }
	 * return ""; }
	 */
	public String RegdTransLog(String regnSeqno, String regnno, String nFSUSNo, String nFPRF, String nFCensus,
			String nFMNo, String nFHldType, String nFEqptType, String nOldval, String nTSUSNo, String nTMNo,
			String nTHldType, String nTEqptType, String nNew, String nAuthNo, String nAuthDate, String nAuthType,
			String nOStatus, String nPara) {// nPara
		String[] tmpRegn = regnno.split(":");
		try {
			Connection conn = null;
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String nrSql = "";
			nrSql = "insert into mms_tb_regn_tr_log(regn_seq_no, eqpt_regn_no,from_sus_no,from_prf_code,from_census_no,";
			nrSql = nrSql
					+ " from_material_no,from_hldg_ty,from_eqpt_ty,old_value,cur_sus_no,cur_prf_code,cur_census_no,";
			nrSql = nrSql
					+ " cur_material_no,cur_hldg_ty,cur_eqpt_ty,new_value,tr_date,tran_type,auth_type,auth_no,auth_date,";
			nrSql = nrSql + " regn_count,op_status,from_unit_status,cur_unit_status) ";
			nrSql = nrSql + " values (?,?,?,?,?,?,?,?,?,";
			nrSql = nrSql + " ?,?,?,?,?,?,?,";
			nrSql = nrSql + " localtimestamp,'NEW',?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, regnSeqno);
			stmt.setString(2, regnno);
			stmt.setString(3, nFSUSNo);
			stmt.setString(4, nFPRF);
			stmt.setString(5, nFCensus);
			stmt.setString(6, nFMNo);
			stmt.setString(7, nFHldType);
			stmt.setString(8, nFEqptType);
			stmt.setString(9, nOldval);
			stmt.setString(10, nTSUSNo);
			stmt.setString(11, nFPRF);
			stmt.setString(12, nFCensus);
			stmt.setString(13, nTMNo);
			stmt.setString(14, nTHldType);
			stmt.setString(15, nTEqptType);
			stmt.setString(16, nNew);
			stmt.setString(17, nAuthType);
			stmt.setString(18, nAuthNo);
			stmt.setString(19, nAuthDate);
			stmt.setInt(20, tmpRegn.length);
			stmt.setString(21, nOStatus);
			stmt.setString(22, "0");
			stmt.setString(23, "0");
			stmt.executeUpdate(nrSql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String GetPRFCodeByCensusNo(String census_no) {
		String PRFCode = "";
		// -------------------------New Query for max number
		// ---------------------------------------

		// String list1 = new String();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = "select distinct prf_code from mms_tb_mlccs_mstr_detl where census_no like ?";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, census_no + "%");
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				PRFCode = rs1.getString("prf_code");
			}
			rs1.close();
			stmt.close();
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
		return PRFCode;
	}

	public String RegnNoGeneration1(String prf_code, String eqpt_regn_no) {
		String Regn_seq_no = "";
		// -------------------------New Query for max number
		// ---------------------------------------

		String list1 = new String();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			sql1 = "select max(prfseq) as count from (select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8)\r\n"
					+ "UNION\r\n"
					+ "select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_oth_mstr where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n"
					+ "UNION\r\n"
					+ "select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_depot_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n"
					+ ") AS tab";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				list1 = rs1.getString("count");
			}
			rs1.close();
			stmt.close();
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
		String serial = "";
		if (list1.equals(null)) {
			serial = "00000000";
		} else {
			String list2 = list1;
			serial = list2;
		}

		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%08d", serialNo);
		Regn_seq_no = prf_code + "N" + serial;
		return Regn_seq_no;
	}

	public String validationConfirmNew(String a) {

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// Statement stmt = (Statement)conn.createStatement();

			String[] rodiv = a.split(":");
			// String roCnd="";
			String nrStr1 = "";

			// int ii=0;
			String nrSql = "";
			String tempprf = "";
			String tempregseq = "";
			String nFMNo = "0";
			String nOldval = "";
			String nTMNo = "0";
			String nNew = "";
			String nAuthType = "DIR";
			String nOStatus = "1";
			String nPara = "";
			String from_sus_no = "";
			String census_no = "";
			String type_of_hldg = "";
			String type_of_eqpt = "";
			String to_sus_no = "";
			String rv_no = "";
			String rv_date = "";
			String tmpsus = "";
			// String tmpcen="";

			ArrayList<String> eqpt_reg_arr = new ArrayList<String>();
			ArrayList<String> eqpt_reg_seq_arr = new ArrayList<String>();
			nrSql = nrSql
					+ " select issue_sus_no as sus_no,prf_code,census_seq_no,census_no,type_of_hldg,type_of_eqpt,eqpt_ser_no as eqpt_regn_no,'xxxxxxxx' as regn_seq_no,";
			nrSql = nrSql
					+ " issue_sus_no as from_sus_no,upload_date as from_tr_date,issue_sus_no as to_sus_no,data_cr_date as to_tr_date,eqpt_ser_no,eqpt_batch_no,";
			nrSql = nrSql
					+ " eqpt_part_no,'1' as service_status,data_cr_by,data_cr_date,data_upd_by,data_upd_date,data_app_by,data_app_date,'1' as op_status,";
			nrSql = nrSql
					+ " 'C' as tfr_status,iv_no as rv_no,iv_date as rv_date from mms_tb_imp_dir where issue_sus_no=? and iv_no=? order by issue_sus_no,census_nod";
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, rodiv[0]);
			stmt.setString(2, rodiv[1]);
			ResultSet rs = stmt.executeQuery();
			String aa = "";
			String bb = "";

			while (rs.next()) {
				nrStr1 = "";
				eqpt_reg_arr.add(rs.getString("eqpt_regn_no"));
				tempprf = GetPRFCodeByCensusNo(rs.getString("census_no"));
				tempregseq = RegnNoGeneration1(tempprf, "");
				eqpt_reg_seq_arr.add(tempregseq);
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "localtimestamp,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "localtimestamp,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "localtimestamp,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				nrStr1 = nrStr1 + "?,";
				from_sus_no = rs.getString("from_sus_no");
				census_no = rs.getString("census_no");
				type_of_hldg = rs.getString("type_of_hldg");
				type_of_eqpt = rs.getString("type_of_eqpt");
				to_sus_no = rs.getString("to_sus_no");
				rv_no = rs.getString("rv_no");
				rv_date = rs.getString("rv_date");
				if (!tmpsus.equalsIgnoreCase(to_sus_no)) {
					aa = String.join(":", eqpt_reg_arr);
					bb = String.join(":", eqpt_reg_seq_arr);
					RegdTransLog(bb, aa, from_sus_no, tempprf, census_no, nFMNo, type_of_hldg, type_of_eqpt, nOldval,
							to_sus_no, nTMNo, type_of_hldg, type_of_eqpt, nNew, rv_no, rv_date, nAuthType, nOStatus,
							nPara);
					aa = "";
					bb = "";
					tmpsus = to_sus_no;
				}

				PreparedStatement stmt1 = conn.prepareStatement(nrSql);
				nrSql = "insert into mms_tb_regn_mstr_detl";
				nrSql = nrSql
						+ " (sus_no,prf_code,census_seq_no,census_no,type_of_hldg,type_of_eqpt,eqpt_regn_no,regn_seq_no,from_sus_no,from_tr_date,to_sus_no,";
				nrSql = nrSql
						+ " to_tr_date,eqpt_ser_no,eqpt_batch_no,eqpt_part_no,service_status,data_cr_by,data_cr_date,data_upd_by,data_upd_date,data_app_by,";
				nrSql = nrSql + " data_app_date,op_status,tfr_status,rv_no,rv_date ) values (" + nrStr1 + ")";

				stmt1.setString(1, rs.getString("sus_no"));
				stmt1.setString(2, tempprf);
				stmt1.setString(3, rs.getString("census_seq_no"));
				stmt1.setString(4, rs.getString("census_no"));
				stmt1.setString(5, rs.getString("type_of_hldg"));
				stmt1.setString(6, rs.getString("type_of_eqpt"));
				stmt1.setString(7, rs.getString("eqpt_regn_no"));
				stmt1.setString(8, tempregseq);
				stmt1.setString(9, rs.getString("from_sus_no"));
				stmt1.setString(10, rs.getString("from_tr_date"));
				stmt1.setString(11, rs.getString("to_sus_no"));
				stmt1.setString(12, rs.getString("from_tr_date"));
				stmt1.setString(13, rs.getString("eqpt_ser_no"));
				stmt1.setString(14, rs.getString("eqpt_batch_no"));
				stmt1.setString(15, rs.getString("eqpt_part_no"));
				stmt1.setString(16, rs.getString("service_status"));
				stmt1.setString(17, rs.getString("data_cr_by"));
				stmt1.setString(18, rs.getString("data_upd_by"));
				stmt1.setString(19, rs.getString("data_app_by"));
				stmt1.setString(20, rs.getString("op_status"));
				stmt1.setString(21, rs.getString("tfr_status"));
				stmt1.setString(22, rs.getString("rv_no"));
				stmt1.setString(23, rs.getString("rv_date"));

				stmt1.executeUpdate();
				stmt1.close();
			}
			aa = String.join(":", eqpt_reg_arr);
			bb = String.join(":", eqpt_reg_seq_arr);
			String qa = RegdTransLog(bb, aa, from_sus_no, tempprf, census_no, nFMNo, type_of_hldg, type_of_eqpt,
					nOldval, to_sus_no, nTMNo, type_of_hldg, type_of_eqpt, nNew, rv_no, rv_date, nAuthType, nOStatus,
					nPara);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public int checkDataExits(String census_no, String month, String depot_sus_no, String table_name) {
		int count = 0;
		Connection conn = null;
		try {
			String whr = "";
			if (table_name.equals("mms_tb_imp_dir")) {
				whr = " and TO_CHAR(upload_date,'mm-yyyy') = ?  and issue_depot_name = ?";
			}
			if (table_name.equals("mms_tb_imp_drr")) {
				whr = " and TO_CHAR(data_cr_date,'mm-yyyy') = ? and deposit_depot_name = ?";
			}
			if (table_name.equals("mms_tb_imp_msr")) {
				whr = " and TO_CHAR(data_upd_date,'mm-yyyy') = ? and depot_sus_no = ?";
			}

			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = "select count(*) as count from " + table_name + "\r\n" + "where census_no = ? " + whr;
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, census_no);
			stmt.setString(2, month);
			stmt.setString(3, depot_sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				count = Integer.parseInt(rs1.getString("count"));

			}
			rs1.close();
			stmt.close();
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

	public String[] getregno(String regn_no, String ro_no, String census_no, String depot_sus_no,
	        String unit_sus_no, HttpSession session) {
	    String[] resultArray = null;

	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();

	        String query = "select rog.yet_to_collect,tmp.regn_no,rog.issue_qnty,rog.id  from mms_tb_ro_generate_dtl rog\r\n"
	                + "inner join mms_tb_tempregn_rowise_dtl tmp\r\n" + "on  tmp.ro_no=rog.ro_no\r\n"
	                + "where regn_no != ?\r\n" + "and rog.ro_no=?\r\n" + "and rog.census_no=?\r\n"
	                + "and rog.depots_sus_no=?\r\n" + "and rog.unit_sus_no=? and tmp.regn_no like'%temp%'\r\n"
	                + "order by tmp.id asc limit 1";

	        PreparedStatement stmtt = conn.prepareStatement(query);

	        stmtt.setString(1, regn_no);
	        stmtt.setString(2, ro_no);
	        stmtt.setString(3, census_no);
	        stmtt.setString(4, depot_sus_no);
	        stmtt.setString(5, unit_sus_no);

	        ResultSet rs = stmtt.executeQuery();

	        if (rs.next()) {
	            String regNo = rs.getString("regn_no");
	            String yetToCollect = rs.getString("yet_to_collect");
	            String issued_qnty = rs.getString("issue_qnty");
	            String ro_id = rs.getString("id");
	           
	            resultArray = new String[]{regNo, yetToCollect,issued_qnty,ro_id};
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return resultArray;
	}
	
	public String[] getrgnoqntymorethanone(String regn_no, String ro_no, String census_no, String depot_sus_no,
	        String unit_sus_no,int seq, HttpSession session) {
	    String[] resultArray = null;

	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();

	        String query = "SELECT yet_to_collect, regn_no, issue_qnty, id\r\n"
	        		+ "FROM (\r\n"
	        		+ "    SELECT rog.yet_to_collect, tmp.regn_no, rog.issue_qnty, rog.id,\r\n"
	        		+ "           ROW_NUMBER() OVER (ORDER BY tmp.id) AS row_num\r\n"
	        		+ "    FROM mms_tb_ro_generate_dtl rog\r\n"
	        		+ "    INNER JOIN mms_tb_tempregn_rowise_dtl tmp ON tmp.ro_no = rog.ro_no\r\n"
	        		+ "    WHERE regn_no != ?\r\n"
	        		+ "    AND rog.ro_no = ?\r\n"
	        		+ "    AND rog.census_no = ?\r\n"
	        		+ "    AND rog.depots_sus_no = ?\r\n"
	        		+ "    AND rog.unit_sus_no = ? \r\n"
	        		+ "    AND tmp.regn_no LIKE '%temp%'\r\n"
	        		+ ") AS sub\r\n"
	        		+ "WHERE row_num =? ;";

	        PreparedStatement stmtt = conn.prepareStatement(query);

	        stmtt.setString(1, regn_no);
	        stmtt.setString(2, ro_no);
	        stmtt.setString(3, census_no);
	        stmtt.setString(4, depot_sus_no);
	        stmtt.setString(5, unit_sus_no);
	        stmtt.setInt(6, seq);

	        ResultSet rs = stmtt.executeQuery();

	        if (rs.next()) {
	            String regNo = rs.getString("regn_no");
	            String yetToCollect = rs.getString("yet_to_collect");
	            String issued_qnty = rs.getString("issue_qnty");
	            String ro_id = rs.getString("id");
	           
	            resultArray = new String[]{regNo, yetToCollect,issued_qnty,ro_id};
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return resultArray;
	}


}
