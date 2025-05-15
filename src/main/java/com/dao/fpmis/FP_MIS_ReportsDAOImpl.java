package com.dao.fpmis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.sql.DataSource;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class FP_MIS_ReportsDAOImpl implements FP_MIS_ReportsDAO {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection nConn;

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	public ArrayList<ArrayList<String>> nFundStatus(String nParaValue, String nPara, String domainid) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		nParaValue = nParaValue.toUpperCase();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select a.tr_head,max(b.head_desc) as head,sum(a.recd_amt) as totrecd,sum(a.exp_amt) as totexp,(sum(a.recd_amt)-sum(a.exp_amt)) as balamt from fp.fp_tb_trans_detl a";
			sql1 = sql1 + " left join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " where a.tr_type in ('FND','TFR') ";

			sql1 = sql1 + " group by a.tr_head";
			sql1 = sql1 + " order by a.tr_head";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head"));
					list2.add(rs.getString("totrecd"));
					list2.add(rs.getString("totexp"));
					list2.add(rs.getString("balamt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public List<String> getEncList(List<String> l, HttpSession s1) {
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

		if (l.size() != 0) {
			FList.add(enckey + "0fsjyg==");
		}

		return FList;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHeadNew(String m1_tryear, String m1_nomen, String m1_nomenin,
			String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nTrType[] = { "FND", "TFR", "EXP" };
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select min(a.ppid) as ppid,min(b.tr_level) as tr_level,min(h1.major_head) as major_head,min(h1.mh_desc) as major_desc,min(h1.minor_head) as minor_head,min(h1.mnh_desc) as minor_desc,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt,max(period) as period from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				if (m1_hdlvl.equalsIgnoreCase("1")) {
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1)  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user' and to_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[0]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1)  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head,':',1)  as tr_head,period,fr_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[2]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user' and to_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[0]
							+ "'";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "'";
					sql1 = sql1 + " union all";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,fr_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[2]
							+ "'";
				}
			} else {

				if (m1_hdlvl.equalsIgnoreCase("1")) {
					sql1 = sql1
							+ " select min(a.ppid) as ppid,min(b.tr_level) as tr_level,min(h1.major_head) as major_head,min(h1.mh_desc) as major_desc,min(h1.minor_head) as minor_head,min(h1.mnh_desc) as minor_desc,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt,max(a.period) as period from (";
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1)  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user' and to_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1)  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head,':',1)  as tr_head,period,fr_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[2]
							+ "' and (split_part(tr_head_to,':',2) is null or split_part(tr_head_to,':',2)='')";
					sql1 = sql1 + " union all";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user' and to_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "'";
					sql1 = sql1 + " union all  ";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[1]
							+ "'";
					sql1 = sql1 + " union all";
					sql1 = sql1
							+ " SELECT id as ppid,concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2))  as tr_head,period,fr_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp";
					sql1 = sql1 + " where userid='fp_user'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='" + nTrType[2]
							+ "'";
				}
			}
			sql1 = sql1 + " ) a  inner join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_head_dtl h1 on a.tr_head=h1.tr_head";
			sql1 = sql1 + " group by a.tr_head order by a.tr_head";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("tr_level"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("major_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("minor_desc"));
					list2.add(rs.getString("period"));
					list2.add(rs.getString("fmn_code"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHead1(String m1_tryear, String m1_nomen, String m1_nomenin,
			String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select min(a.ppid) as ppid,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
						+ m1lvl[2] + "' and tr_type='FND'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where fr_sus_no='"
						+ m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp where fr_sus_no='"
						+ m1lvl[2] + "' and tr_type='EXP'";
			} else {

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
						+ m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where fr_sus_no='"
						+ m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp where fr_sus_no='"
						+ m1lvl[2] + "' and tr_type='EXP'";
			}
			sql1 = sql1 + " ) a ";
			sql1 = sql1 + " inner join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " group by a.tr_head";
			sql1 = sql1 + " order by a.tr_head";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHead_dgok(String m1_tryear, String m1_nomen, String m1_nomenin,
			String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd0 = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd0 = "split_part(fr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select min(a.ppid) as ppid,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt,sum(a.prjamt) as prjamt,sum(fwdamt) as fwdamt,sum(bkdamt) as bkdamt from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				if (!m1_hdlvl.equalsIgnoreCase("1")) {
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1) as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
							+ m1lvl[2] + "' and tr_type='FND'";
					sql1 = sql1 + " union all ";
				}
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
						+ m1lvl[2] + "' and tr_type='FND'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and tr_type='PRJ'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {

				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " select a.ppid,a.tr_head,a.period,a.fmn_code,a.sus_no,0 as fndamt,0 as altamt,a.expamt,0 as prjamt,b.fwdamt,b.bkdamt from ";
				sql1 = sql1 + " (SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'"
						+ m1lvl[2] + "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt ";
				sql1 = sql1 + " FROM fp.fp_tb_trans_tmp where userid='" + nUsrId + "'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='EXP') a ";
				sql1 = sql1
						+ " left join (SELECT a.exp_id,a.sus_no,sum(a.fwd_amt) as fwdamt,sum(a.bkd_amt) as bkdamt from fp.fp_tb_cda_detl20 a ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
				} else {
					sql1 = sql1 + " where sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " group by a.exp_id,a.sus_no) b on a.ppid=b.exp_id";

			} else {
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and tr_type='PRJ'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "'";
				}
				sql1 = sql1 + " union all ";

				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and to_sus_no<>fr_sus_no and exp_amt<0";
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and exp_amt<0";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and to_sus_no<>fr_sus_no and exp_amt>=0";
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and exp_amt>=0";
				}

				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";

				sql1 = sql1
						+ " select a.ppid,a.tr_head,a.period,a.fmn_code,a.sus_no,0 as fndamt,0 as altamt,a.expamt,0 as prjamt,b.fwdamt,b.bkdamt from ";
				sql1 = sql1 + " (SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'"
						+ m1lvl[2] + "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt ";
				sql1 = sql1 + " FROM fp.fp_tb_trans_tmp where userid='" + nUsrId + "'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='EXP') a ";
				sql1 = sql1
						+ " left join (SELECT a.exp_id,a.sus_no,sum(a.fwd_amt) as fwdamt,sum(a.bkd_amt) as bkdamt from fp.fp_tb_cda_detl20 a ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " where substring(form_code_control,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " where substring(form_code_control,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " where substring(form_code_control,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " where substring(form_code_control,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " where sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " group by a.exp_id,a.sus_no) b on a.ppid=b.exp_id";

			}
			sql1 = sql1 + " ) a ";
			sql1 = sql1 + " inner join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " group by a.tr_head";
			sql1 = sql1 + " order by a.tr_head";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHead(HttpSession sessionA, String m1_tryear, String m1_nomen,
			String m1_nomenin, String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId,String rsfmt) {
		
		////System.out.println("Para-"+m1_tryear +","+m1_nomen +","+m1_nomenin +","+m1_slvl+","+m1_lvl+","+m1_hdlvl+","+nUsrId+","+rsfmt);
		
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String m1lvl[] = m1_lvl.split("_");
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + m1lvl[2]);

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) + 1) + "";
		String sql1 = "";
		String hdCnd = "";
		String hdCnd0 = "";
		String hdCnd1 = "";
		//String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		String fmncnd = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}
		String trhead = m1_hdlvl + ":";
		try {
			conn = dataSource.getConnection();

			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd0 = "split_part(fr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 3) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 6) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 10) + "%'";
			}

			if (m1lvl[0].equalsIgnoreCase("-1")) {

				sql1 = sql1
						+ " select max(o1.unit_name) as fr_sus_name,max(p.fr_sus_no) as fr_sus_no,max(p.tr_head) as tr_head,max(p.to_sus_no) as to_sus_no,"
						+ hdCnd + " as tr_head_to,max(b.head_desc) as head_name,";

				sql1 = sql1 + " nconvertinr(coalesce(sum(p.prj_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as prjamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as altamt,";
				sql1 = sql1
						+ " nconvertinr(coalesce(sum(p.exp_amt)+sum(p.gst_amt)+sum(p.edt_amt)+sum(p.oth_amt),0)::numeric,"
						+ rsfmtd + ",'" + rsfmtdt + "')  as expamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fwdamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as bkdamt from (";

				sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
						":FND:PRV:PRJ:ALT:EXP:CDA");

				sql1 = sql1 + " ) p  ";
				sql1 = sql1 + " left join fp.fp_tb_head_mstr b on " + hdCnd + "=b.tr_head";
				sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o1 on fr_sus_no=o1.sus_no";
				sql1 = sql1 + " group by " + hdCnd;
				sql1 = sql1 + " order by " + hdCnd;

			} else {				
				if (m1_hdlvl.equalsIgnoreCase("4")) {
					sql1 = sql1 + " select max(o1.unit_name) as fr_sus_name,max(p.fr_sus_no) as fr_sus_no,max(p.tr_head) as tr_head,max(p.to_sus_no) as to_sus_no,p.tr_head_to as tr_head_to,max(b.head_desc) as head_name,";
				} else {
					sql1 = sql1
							+ " select max(o1.unit_name) as fr_sus_name,max(p.fr_sus_no) as fr_sus_no,max(p.tr_head) as tr_head,max(p.to_sus_no) as to_sus_no,"
							+ hdCnd + " as tr_head_to,max(b.head_desc) as head_name,";	
				}

				sql1 = sql1 + " nconvertinr(coalesce(sum(p.prj_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as prjamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as altamt,";
				sql1 = sql1
						+ " nconvertinr(coalesce(sum(p.exp_amt)+sum(p.gst_amt)+sum(p.edt_amt)+sum(p.oth_amt),0)::numeric,"
						+ rsfmtd + ",'" + rsfmtdt + "')  as expamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fwdamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(p.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as bkdamt,(case when sum(p.alt_amt)>0 then 'Y' else 'N' end) as altok from (";
				
				sql1 = sql1 + " select tr, (case when (lag(a.to_sus_no,1,a.fr_sus_no) over (partition by a.tr_head_to order by a.tr_head_to,a.fr_sus_no,a.to_sus_No)";
				sql1 = sql1 + " = a.fr_sus_no and a.fr_sus_no=a.to_sus_no) then (lag(a.fr_sus_no,1,a.fr_sus_no) ";
				sql1 = sql1 + " over(partition by a.tr_head_to order by a.tr_head_to,a.fr_sus_no,a.to_sus_No)) ";
				sql1 = sql1 + " else a.fr_sus_no end) as fr_sus_no,a.tr_head as tr_head,a.to_sus_no as to_sus_no,";
				sql1 = sql1 + " a.tr_head_to as tr_head_to, a.prv_amt as prv_amt,a.prj_amt as prj_amt, a.";
				sql1 = sql1 + " fnd_amt as fnd_amt,a.alt_amt as alt_amt,a.exp_amt as exp_amt,a.gst_amt as gst_amt,";
				sql1 = sql1 + " a.edt_amt as edt_amt, a.oth_amt as oth_amt,a.fwd_amt as fwd_amt,a.bkd_amt as bkd_amt,a.tr_type,a.period  from (";

				sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
						":FND:PRV:PRJ:ALT:EXP:CDA");
				
				sql1 = sql1 + " ) a ) p  ";
				sql1 = sql1 + " left join fp.fp_tb_head_mstr b on " + hdCnd + "=b.tr_head and b.status='1'";
				sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o1 on fr_sus_no=o1.sus_no";				
				if (m1_hdlvl.equalsIgnoreCase("4")) {
					/*sql1 = sql1 + " group by fr_sus_no,to_sus_no,tr_head_to";*/
					sql1 = sql1 + " group by tr_head_to";
					sql1 = sql1 + " order by tr_head_to";					
				} else {
					/*sql1 = sql1 + " group by fr_sus_no," + hdCnd;*/
					sql1 = sql1 + " group by " + hdCnd;
					sql1 = sql1 + " order by " + hdCnd;
				}				
				
			}
			System.out.println("nFundStatusHQHead-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add("");
					list2.add("");
					list2.add("");
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add(nUnt.get(0).get(3));
					list2.add(rs.getString("fr_sus_name"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHead_detl(String m1_tryear, String m1_nomen, String m1_nomenin,
			String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd0 = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd0 = "split_part(fr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd0 = "concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select min(a.ppid) as ppid,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt,sum(a.prjamt) as prjamt,sum(fwdamt) as fwdamt,sum(bkdamt) as bkdamt from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				if (!m1_hdlvl.equalsIgnoreCase("1")) {
					sql1 = sql1
							+ " SELECT id as ppid,split_part(tr_head_to,':',1) as tr_head,period,to_fmn_code as fmn_code,'"
							+ m1lvl[2]
							+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
							+ m1lvl[2] + "' and tr_type='FND'";
					sql1 = sql1 + " union all ";
				}
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where to_sus_no='"
						+ m1lvl[2] + "' and tr_type='FND'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and tr_type='PRJ'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {

				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " select a.ppid,a.tr_head,a.period,a.fmn_code,a.sus_no,0 as fndamt,0 as altamt,a.expamt,0 as prjamt,b.fwdamt,b.bkdamt from ";
				sql1 = sql1 + " (SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'"
						+ m1lvl[2] + "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt ";
				sql1 = sql1 + " FROM fp.fp_tb_trans_tmp where userid='" + nUsrId + "'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='EXP') a ";
				sql1 = sql1
						+ " left join (SELECT a.exp_id,a.sus_no,sum(a.fwd_amt) as fwdamt,sum(a.bkd_amt) as bkdamt from fp.fp_tb_cda_detl20 a ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
				} else {
					sql1 = sql1 + " where sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " group by a.exp_id,a.sus_no) b on a.ppid=b.exp_id";

			} else {
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and tr_type='PRJ'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " and substring(to_fmn_code,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "'";
				}
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and to_sus_no<>fr_sus_no and exp_amt<0";
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and exp_amt<0";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and to_sus_no<>fr_sus_no and exp_amt>=0";
				} else {
					sql1 = sql1 + " and to_sus_no='" + m1lvl[2] + "' and exp_amt>=0";
				}

				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,0 as fwdamt,0 as bkdamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " select a.ppid,a.tr_head,a.period,a.fmn_code,a.sus_no,0 as fndamt,0 as altamt,a.expamt,0 as prjamt,b.fwdamt,b.bkdamt from ";
				sql1 = sql1 + " (SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'"
						+ m1lvl[2] + "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt ";
				sql1 = sql1 + " FROM fp.fp_tb_trans_tmp where userid='" + nUsrId + "'";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " and substring(fr_fmn_code,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " and fr_sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " and tr_type='EXP') a ";
				sql1 = sql1
						+ " left join (SELECT a.exp_id,a.sus_no,sum(a.fwd_amt) as fwdamt,sum(a.bkd_amt) as bkdamt from fp.fp_tb_cda_detl20 a ";
				if (m1_slvl.equalsIgnoreCase("HQ")) {
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " where substring(form_code_control,1,1)=substring('" + m1lvl[1] + "',1,1)";
					}
					if (m1lvl[0].equalsIgnoreCase("2")) {
						sql1 = sql1 + " where substring(form_code_control,1,3)=substring('" + m1lvl[1] + "',1,3)";
					}
					if (m1lvl[0].equalsIgnoreCase("3")) {
						sql1 = sql1 + " where substring(form_code_control,1,6)=substring('" + m1lvl[1] + "',1,6)";
					}
					if (m1lvl[0].equalsIgnoreCase("4")) {
						sql1 = sql1 + " where substring(form_code_control,1,10)=substring('" + m1lvl[1] + "',1,10)";
					}
				} else {
					sql1 = sql1 + " where sus_no='" + m1lvl[2] + "' ";
				}
				sql1 = sql1 + " group by a.exp_id,a.sus_no) b on a.ppid=b.exp_id";
			}
			sql1 = sql1 + " ) a ";
			sql1 = sql1 + " inner join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " group by a.tr_head";
			sql1 = sql1 + " order by a.tr_head";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHead_ok_fund_recd(String m1_tryear, String m1_nomen,
			String m1_nomenin, String m1_slvl, String m1_lvl, String m1_hdlvl, String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select min(a.ppid) as ppid,a.tr_head,max(b.head_desc) as head_name,max(fmn_code) as fmn_code,max(sus_no) as sus_no,sum(a.fndamt) as fndamt,sum(a.altamt) as altamt,sum(a.expamt) as expamt from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and to_sus_no='" + m1lvl[2] + "' and tr_type='FND'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='EXP'";
			} else {

				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and to_sus_no='" + m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd + " as tr_head,period,to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id as ppid," + hdCnd1 + " as tr_head,period,fr_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "'  and fr_sus_no='" + m1lvl[2] + "' and tr_type='EXP'";
			}
			sql1 = sql1 + " ) a ";
			sql1 = sql1 + " inner join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " group by a.tr_head";
			sql1 = sql1 + " order by a.tr_head";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundBEPrint(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId) {
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) + 1) + "";
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_lvl.equalsIgnoreCase(null)) {
				m1_lvl = "BE";
			}
			sql1 = " select q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,q.sub_head,q.sbh_desc,p.tr_head,q.head_code,q.head_desc,p.ppid,p.fmn_code,p.sus_no,p.fndamt,p.altamt,p.expamt,p.period,q.idx_1,q.idx_name from (";
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				sql1 = sql1
						+ " SELECT a.id as ppid,concat(split_part(a.tr_head_to,':',1),':',split_part(a.tr_head_to,':',2),':',split_part(a.tr_head_to,':',3),':',split_part(a.tr_head_to,':',4))  as tr_head,";
				sql1 = sql1 + " a.period,a.to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_funds_detl" + fileYr
						+ " a";
				sql1 = sql1 + " where a.fr_sus_no='" + m1lvl[2] + "' and a.tr_type='TFR' and a.allot_for='" + m1_rpt
						+ "'";
			} else {

				sql1 = sql1
						+ " SELECT a.id as ppid,concat(split_part(a.tr_head_to,':',1),':',split_part(a.tr_head_to,':',2),':',split_part(a.tr_head_to,':',3),':',split_part(a.tr_head_to,':',4))  as tr_head,";
				sql1 = sql1 + " a.period,a.to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt FROM fp.fp_tb_funds_detl" + fileYr
						+ " a";
				sql1 = sql1 + " where a.fr_sus_no='" + m1lvl[2] + "' and a.tr_type='TFR' and a.allot_for='" + m1_rpt
						+ "'";
			}
			sql1 = sql1 + " ) p ";
			sql1 = sql1 + " left join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head";
			sql1 = sql1 + " order by q.major_head,q.minor_head,q.sub_head,q.idx_1,q.head_code";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("period"));

					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundDraftPrint(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,
			String m1_rptLvl, HttpSession sessionA, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String trhead = m1_rptLvl + ":";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			sql1 = " select ";
			/*
			 * if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) { sql1 = sql1 +
			 * " distinct on (p.tr_head,p.tr,o.hq_type,p.sus_no)"; } else { sql1 = sql1 +
			 * " distinct on (p.tr_head)"; }
			 */
			sql1 += " p.tr as tr,p.tr_head as tr_head,q.head_code,q.head_desc,q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1
						+ " q.sub_head, q.sbh_desc,q.idx_1,q.idx_name, p.sus_no,o.hq_type,o.form_code_control,o.unit_name,";
				sql1 = sql1 + " nconvertinr(coalesce(p.fndamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
			} else {
				sql1 = sql1 + " q.sub_head, q.sbh_desc,q.idx_1,q.idx_name,";
				sql1 = sql1 + " nconvertinr(coalesce(p.fndamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
			}
			sql1 = sql1 + " nconvertinr(coalesce(p.prjamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt + "')  as prjamt,";
			sql1 = sql1 + " nconvertinr(coalesce(p.altamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt + "')  as altamt,";
			sql1 = sql1 + " '" + rsfmt + "' as rs_fmt,";
			sql1 = sql1 + " nconvertinr(coalesce(p.prvamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as prvamt from (";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1
						+ " select min(tr) as tr,a.tr_head_to as tr_head,a.to_sus_no as sus_no,sum(fnd_amt) as fndamt,sum(prj_amt) as prjamt,";
			} else {
				sql1 = sql1
						+ " select min(tr) as tr,a.tr_head_to as tr_head,sum(fnd_amt) as fndamt,sum(prj_amt) as prjamt,";

			}
			sql1 = sql1 + " sum(prv_amt) as prvamt,sum(alt_amt) as altamt from (";
			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, "HQ_" + m1_lvl, m1_tryear, sessionA, ":FND:PRV:PRJ:DALT");

			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " ) a group by a.tr_head_to,a.to_sus_no";
			} else {
				sql1 = sql1 + " ) a group by a.tr_head_to";
			}

			sql1 = sql1 + " ) p inner  join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o on p.sus_no=o.sus_no";
			}
			sql1 = sql1 + " where (q.head_desc is not null or q.head_desc<>'')";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " and p.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"
						+ m1lvl[2] + "')";
			}
			sql1 = sql1 + " and p.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no='"
					+ m1lvl[2] + "')";
			if (m1lvl[0].equalsIgnoreCase("0")) {

			} else {
				if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
					// sql1 = sql1 + " and o.hq_type::numeric <="+(Integer.parseInt(m1lvl[0])+1);
				}
			}

			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " order by p.tr_head,p.tr,o.hq_type,p.sus_no";
			} else {
				sql1 = sql1 + " order by p.tr_head";
			}
			PreparedStatement stmt = conn.prepareStatement(sql1);
			System.out.println("nFundDraftPrint-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("prvamt"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("altamt"));
					if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
						list2.add(rs.getString("sus_no"));
						list2.add(rs.getString("unit_name"));
						list2.add(rs.getString("form_code_control"));
					} else {
						list2.add("");
						list2.add("");
						list2.add("");
					}
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("tr"));
					list2.add(rs.getString("rs_fmt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public ArrayList<ArrayList<String>> nFundRecv(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,
			String m1_rptLvl, HttpSession sessionA, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		////System.out.println("-para-"+m1_tryear+","+m1_lvl+","+m1_rpt+","+nUsrId+","+m1_rptLvl+","+rsfmt);

		String role=sessionA.getAttribute("role").toString();
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String trhead = m1_rptLvl + ":";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			
			if (m1lvl[0].equalsIgnoreCase("-1")) {				
				sql1 = sql1 + " select to_sus_no as sus_no,tr_head_to as tr_head,c.head_desc,fr_sus_no,'Minitry of Defence' as unit_name, ";
				sql1 = sql1 + " nconvertinr(coalesce(recd_amt,0)::numeric," + rsfmtd + ",'" + rsfmtdt+"')  as exp_amt, to_char(period,'dd-mm-yyyy') as lst_upd ";
				sql1 = sql1 + " from fp.fp_tb_trans_detl"+fileYr+" a left join fp.fp_tv_head_dtl c on a.tr_head_to=c.tr_head ";
				sql1 = sql1 + " where to_sus_no='"+m1lvl[2]+"' and recd_amt>0 and tr_type='FND' ";
				sql1 = sql1 + " order by to_sus_no,tr_head_to,fr_sus_no";				
			} else {
				sql1 = sql1 + " select to_sus_no as sus_no,tr_head_to as tr_head,c.head_desc,fr_sus_no,b.unit_name,";
				sql1 = sql1 + " nconvertinr(coalesce(exp_amt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as exp_amt,";
				sql1 = sql1 + " to_char(period,'dd-mm-yyyy') as lst_upd from fp.fp_tb_funds_detl"+fileYr+" a"; 
				sql1 = sql1 + " left join fp.fp_tv_orbat_dtl b on a.fr_sus_no=b.sus_no";
				sql1 = sql1 + " left join fp.fp_tv_head_dtl c on a.tr_head_to=c.tr_head";
				sql1 = sql1 + " where (fr_sus_no,fund_lvl) in (select fr_sus_no,max(fund_lvl) from fp.fp_tb_funds_detl"+fileYr+" where fr_sus_no in (";
				if (role.equalsIgnoreCase("FP_VIEW")) {
					sql1 = sql1 + " select distinct psus_no as sus_no  from fp.fp_tb_pref_head"; 
					sql1 = sql1 + " where sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where psus_no='"+m1lvl[2]+"'  and hq_type::integer<="+m1lvl[0]+"))";
				} else {
					sql1 = sql1 + " select distinct a.psus_no from fp.fp_tb_pref_head a ";				
					sql1 = sql1 + " where a.sus_no='"+m1lvl[2]+"')";	
				}
				sql1 = sql1 + " group by fr_sus_no) and fr_sus_no<>to_sus_no ";
				if (role.equalsIgnoreCase("FP_VIEW")) {
					sql1 = sql1 + " and to_sus_no in (select distinct sus_no as sus_no  from fp.fp_tb_pref_head ";
					sql1 = sql1 + " where sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where psus_no='"+m1lvl[2]+"'  and hq_type::integer<="+m1lvl[0]+")) and exp_amt>0";
				} else {
					sql1 = sql1 + " and to_sus_no='"+m1lvl[2]+"' and exp_amt>0";
				}
				if (m1_rpt.contains(":RPTFR2")) {
					sql1 = sql1 + " order by to_sus_no,tr_head_to,b.hq_type,fr_sus_no";
				} else {
					sql1 = sql1 + " order by b.hq_type,fr_sus_no,to_sus_no,tr_head_to";
				}
			}
			////System.out.println("nFundRecv-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					if (m1_rpt.contains(":RPTFR2")) {
						list2.add(rs.getString("tr_head"));
						list2.add(rs.getString("head_desc"));
						list2.add(rs.getString("sus_no"));
						list2.add(rs.getString("unit_name"));
					} else {
						list2.add(rs.getString("sus_no"));
						list2.add(rs.getString("unit_name"));						
						list2.add(rs.getString("tr_head"));
						list2.add(rs.getString("head_desc"));
					}
/*					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
*/										
					list2.add(rs.getString("exp_amt"));
					list2.add(rsfmt);
					list2.add(rs.getString("lst_upd"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundAllot(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,
			String m1_rptLvl, HttpSession sessionA, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		////System.out.println("-para-"+m1_tryear+","+m1_lvl+","+m1_rpt+","+nUsrId+","+m1_rptLvl+","+rsfmt);

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String trhead = m1_rptLvl + ":";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
				
			sql1 = sql1 + " select max(c.hq_type),max(p.fr_sus_no) as fr_sus_no,max(p.tr_head) as tr_head,p.to_sus_no as sus_no,";				
			sql1 = sql1 + " max(c.unit_name) as unit_name,tr_head_to as tr_head_to,max(b.head_desc) as head_name,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(p.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt +"') as fndamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(p.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt +"') as altamt from (";
			
			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, "HQ_" + m1_lvl, m1_tryear, sessionA, ":FND:ALT");
			
			sql1 = sql1 + " ) p left join fp.fp_tb_head_mstr b on tr_head_to=b.tr_head"; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl c on p.to_sus_no=c.sus_no";
			sql1 = sql1 + " where p.alt_amt<>0";
			
			if (m1lvl[2].equalsIgnoreCase("A000001")) {
				sql1 = sql1 + " or p.fnd_amt<>0";
			}
			
			if (m1_rpt.equalsIgnoreCase("RPTALT2")) {
				sql1 = sql1 + " group by to_sus_no,tr_head_to"; 
				sql1 = sql1 + " order by tr_head_to,max(c.hq_type),sus_no";				
			} else {
				sql1 = sql1 + " group by to_sus_no,tr_head_to"; 
				sql1 = sql1 + " order by max(c.hq_type),sus_no,tr_head_to";
			}			
			
			////System.out.println("nFundAllot-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					if (m1_rpt.contains(":RPTALT2")) {
						list2.add(rs.getString("tr_head_to"));
						list2.add(rs.getString("head_name"));
						list2.add(rs.getString("sus_no"));
						list2.add(rs.getString("unit_name"));
					} else {
						list2.add(rs.getString("sus_no"));
						list2.add(rs.getString("unit_name"));						
						list2.add(rs.getString("tr_head_to"));
						list2.add(rs.getString("head_name"));
					}
/*					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
*/										
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rsfmt);					
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	public ArrayList<ArrayList<String>> nFundFlow(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,
			String m1_rptLvl, HttpSession sessionA, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		////System.out.println("nFundFlow-para-"+m1_tryear+","+m1_lvl+","+m1_rpt+","+nUsrId+","+m1_rptLvl+","+rsfmt);

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String trhead = m1_rptLvl + ":";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			
			if (m1lvl[2].equalsIgnoreCase("A000001")) {
				sql1 = sql1 + " select (case when fr_sus_no='"+m1lvl[2]+"' then 'MOD' else fr_sus_no end) as hlbh_sus,"; 
				sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then 'MOD' else max(b.unit_name) end) as hlbh_name,"; 				
			}  else {
				sql1 = sql1 + " select (case when fr_sus_no='"+m1lvl[2]+"' then '.' else fr_sus_no end) as hlbh_sus,"; 
				sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then '.' else max(b.unit_name) end) as hlbh_name,"; 				
			}				
			sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then fr_sus_no else to_sus_no end) as bh_sus,"; 
			sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then max(b.unit_name) else max(b1.unit_name) end) as bh_name," ; 
			sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then to_sus_no else '' end) as alt_sus,"; 
			sql1 = sql1 + " (case when fr_sus_no='"+m1lvl[2]+"' then max(b1.unit_name) else '' end) as alt_name,"; 
			sql1 = sql1 + " tr_head_to as tr_head,max(c.head_desc) as head_desc,nconvertinr(sum(exp_amt)::numeric,"+rsfmtd+",'"+rsfmtdt+"') as alt_amt,to_char(max(period),'dd-mm-yyyy') as alt_date,max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl"+fileYr+" z "; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl b on z.fr_sus_no=b.sus_no"; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl b1 on z.to_sus_no=b1.sus_no"; 
			sql1 = sql1 + " left join fp.fp_tv_head_dtl c on z.tr_head_to=c.tr_head"; 
			sql1 = sql1 + " where (fr_sus_no,to_sus_no,tr_head_to,fund_lvl) in ("; 
			sql1 = sql1 + " select fr_sus_no,to_sus_no,tr_head_to,max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl"+fileYr+" where ";
/*			sql1 = sql1 + " to_sus_no in ( WITH RECURSIVE nRecPrefUnit AS ( "; 
			sql1 = sql1 + " select distinct p.psus_no,p.sus_no from fp.fp_tb_pref_head p where p.psus_no='"+m1lvl[2]+"' union  "; 
			sql1 = sql1 + " select distinct n.psus_no,n.sus_no as lvl from fp.fp_tb_pref_head n "; 
			sql1 = sql1 + " inner join nRecPrefUnit s on s.sus_no=n.psus_no "; 
			sql1 = sql1 + " ) select distinct p.sus_no from nRecPrefUnit p ) and"; 
*/			sql1 = sql1 + "  (fr_sus_no,to_sus_no,tr_head_to) in ("; 
			sql1 = sql1 + " select distinct psus_no,sus_no,chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl[2]+"' or sus_no='"+m1lvl[2]+"'"; 
			sql1 = sql1 + " ) group by fr_sus_no,to_sus_no,tr_head_to"; 
			sql1 = sql1 + " ) and exp_amt>0 group by b.hq_type,z.fr_sus_no,z.to_sus_no,z.tr_head_to";
			if (m1_rpt.contains(":RPTBH1")) {
				sql1 = sql1 + " order by b.hq_type,z.fr_sus_no,z.to_sus_no,z.tr_head_to";
			} else {
				sql1 = sql1 + " order by z.tr_head_to,b.hq_type,z.fr_sus_no,z.to_sus_no";	
			}
			////System.out.println("nFundFlow-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					if (m1_rpt.contains(":RPTBH1")) {
						list2.add(rs.getString("hlbh_sus"));
						list2.add(rs.getString("hlbh_name"));
						list2.add(rs.getString("bh_sus"));
						list2.add(rs.getString("bh_name"));						
						list2.add(rs.getString("alt_sus"));
						list2.add(rs.getString("alt_name"));						
						list2.add(rs.getString("tr_head"));
						list2.add(rs.getString("head_desc"));
					} else {
						list2.add(rs.getString("tr_head"));
						list2.add(rs.getString("head_desc"));
						list2.add(rs.getString("hlbh_sus"));
						list2.add(rs.getString("hlbh_name"));
						list2.add(rs.getString("bh_sus"));
						list2.add(rs.getString("bh_name"));						
						list2.add(rs.getString("alt_sus"));
						list2.add(rs.getString("alt_name"));	
					}
					list2.add(rs.getString("alt_amt"));
					list2.add(rs.getString("alt_date"));
					list2.add(rsfmt);					
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public ArrayList<ArrayList<String>> nFundTree(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,
			String m1_rptLvl, HttpSession sessionA, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String role=sessionA.getAttribute("role").toString();		
		Connection conn = null;
		////System.out.println("nFundTree-para-"+m1_tryear+","+m1_lvl+","+m1_rpt+","+nUsrId+","+m1_rptLvl+","+rsfmt+","+role);

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String trhead = m1_rptLvl + ":";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}
		String m1lvl[] = m1_lvl.split("_");
		String m1lvlstr="";
		if (m1lvl[0].equalsIgnoreCase("1")) {
			m1lvlstr="left(form_code_control,1)";
		} else if (m1lvl[0].equalsIgnoreCase("2")) {
			m1lvlstr="left(form_code_control,3)";			
		} else if (m1lvl[0].equalsIgnoreCase("3")) {
			m1lvlstr="left(form_code_control,6)";			
		} else if (m1lvl[0].equalsIgnoreCase("4")) {
			m1lvlstr="left(form_code_control,10)";			
		}
		

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			
/*			m1_rpt=":RPTFT2";
			sql1 = sql1 + " select t.lvl,t.psus_no as hlbh_sus,o1.unit_name as hlbh_name,t.sus_no as bh_sus,o2.unit_name as bh_name,chl_head_code as tr_head,";
			sql1 = sql1 + " h1.head_desc as head_desc,t.path,array_to_string(t.path,'_') as pathid from (";
			sql1 = sql1 + " with recursive parents as (	select distinct psus_no,sus_no,chl_head_code,array[psus_no::text,sus_no::text] as path"; 
			sql1 = sql1 + " from fp.fp_tb_pref_head where psus_no='"+m1lvl[2]+"'";
			sql1 = sql1 + " union select ch.psus_no,ch.sus_no,ch.chl_head_code,p.path || array[ch.sus_no::text] as path	from fp.fp_tb_pref_head ch";
			sql1 = sql1 + " inner join parents p on p.sus_no=ch.psus_no and p.chl_head_code=ch.chl_head_code";
			sql1 = sql1 + " and ch.sus_no !=all(p.path)) select array_length(path,1) as lvl,psus_no,sus_no,chl_head_code,path from parents) t"; 
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o1 on t.psus_no=o1.sus_no";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o2 on t.sus_no=o2.sus_no";
			sql1 = sql1 + " inner join fp.fp_tv_head_dtl h1 on t.chl_head_code=h1.tr_head";
			sql1 = sql1 + " order by path";
			
			sql1 = " select t.lvl,t.psus_no as hlbh_sus,o1.unit_name as hlbh_name,t.sus_no as bh_sus,o2.unit_name as bh_name,t.path,array_to_string(t.path,'_') as pathid from ( with recursive parents as (	select distinct psus_no,sus_no,array[sus_no::text] as path from fp.fp_tb_pref_head where psus_no='"+m1lvl[2]+"' union select ch.psus_no,ch.sus_no,p.path || array[ch.sus_no::text] as path	from fp.fp_tb_pref_head ch inner join parents p on p.sus_no=ch.psus_no and ch.sus_no !=all(p.path)) select array_length(path,1) as lvl,psus_no,sus_no,path from parents) t inner join fp.fp_tv_orbat_dtl o1 on t.psus_no=o1.sus_no inner join fp.fp_tv_orbat_dtl o2 on t.sus_no=o2.sus_no order by path";
*/			
			sql1="";
			if (m1_rpt.contains(":RPTFT1")) {
				sql1 += " select distinct r.pathid,r.lvl,r.hlbh_sus,r.hlbh_name,r.bh_sus,r.bh_name,r.path from ("; 			
			}
			
			sql1 += " select t.lvl,t.psus_no as hlbh_sus,o1.unit_name as hlbh_name,t.sus_no as bh_sus,o2.unit_name as bh_name,";
			sql1 += " t.chl_head_code as tr_head,h1.head_desc,";
			sql1 += " t.path,array_to_string(t.path,'_') as pathid from ("; 
			sql1 += " select distinct a.psus_no,a.sus_no,";
			sql1 += " a.chl_head_code,";
			sql1 += " b.lvl,b.path from fp.fp_tb_pref_head a"; 
			sql1 += " inner join (	";
			
			
			/*sql1 += " with recursive parents as (";				
			sql1 += " select distinct psus_no,sus_no,array[sus_no::text] as path,0 as lvl from fp.fp_tb_pref_head ";
			sql1 += " where psus_no='"+m1lvl[2]+"' and sus_no='"+m1lvl[2]+"' ";
			sql1 += " union select ch.psus_no,ch.sus_no,p.path || array[ch.sus_no::text] as path,p.lvl+1 as lvl from fp.fp_tb_pref_head ch ";
			sql1 += " inner join parents p on p.sus_no=ch.psus_no and ch.sus_no !=all(p.path)) ";
			sql1 += " select lvl,psus_no,sus_no,path from parents";*/
			
			
			sql1 += " select psus_no,sus_no,npath as path,lvl from fp.fp_tv_pref_head where npath like '%"+m1lvl[2]+"%'";
			
			
			
			/*sql1 += " with recursive parents as ( "; 
			sql1 += " select distinct psus_no,sus_no,array[psus_no,sus_no] as path from fp.fp_tb_pref_head  ";
			sql1 += " where psus_no='"+m1lvl[2]+"' ";
			sql1 += " union select ch.psus_no,ch.sus_no,array[ch.psus_no,ch.sus_no] as path from fp.fp_tb_pref_head ch  ";
			sql1 += " inner join parents p on p.sus_no=ch.psus_no and ch.sus_no !=all(p.path))  ";
			sql1 += " select array_length(path,1) as lvl,psus_no,sus_no,path from parents";*/
			
			sql1 += " ) b";
			sql1 += " on a.psus_no=b.psus_no and a.sus_no=b.sus_no ) t"; 
			sql1 += " inner join fp.fp_tv_orbat_dtl o1 on t.psus_no=o1.sus_no"; 
			sql1 += " inner join fp.fp_tv_orbat_dtl o2 on t.sus_no=o2.sus_no";
			sql1 += " inner join fp.fp_tv_head_dtl h1 on t.chl_head_code=h1.tr_head";
			sql1 += " where (t.chl_head_code) in (select distinct chl_head_code from fp.fp_tb_pref_head ";
			sql1 += " where psus_no='"+m1lvl[2]+"')";
			sql1 += " order by o1.hq_type,t.path,t.lvl,t.chl_head_code";
			if (m1_rpt.contains(":RPTFT1")) {
				sql1 += " ) r order by r.pathid,r.lvl";
			}
			
			
			
			
			sql1 = "";
			if (m1_rpt.contains(":RPTFT")) {
				sql1 += " select distinct pathid,a.psus_no as hlbh_sus,s1.unit_name as hlbh_name,a.sus_no as bh_sus,s2.unit_name as bh_name,path,";
				sql1 += " (case when split_part(pathid,'_',1) is not null  then s1.unit_name else '' end) as lvl1,";
				sql1 += " (case when split_part(pathid,'_',2) is not null  then s2.unit_name else '' end) as lvl2,";
				sql1 += " (case when split_part(pathid,'_',3) is not null  then s3.unit_name else '' end) as lvl3,";
				sql1 += " (case when split_part(pathid,'_',4) is not null  then s4.unit_name else '' end) as lvl4,";
				sql1 += " (case when split_part(pathid,'_',5) is not null  then s5.unit_name else '' end) as lvl5,";
				sql1 += " (case when split_part(pathid,'_',6) is not null  then s6.unit_name else '' end) as lvl6,";
				sql1 += " (case when split_part(pathid,'_',7) is not null  then s7.unit_name else '' end) as lvl7,";
				sql1 += " (case when split_part(pathid,'_',8) is not null  then s8.unit_name else '' end) as lvl8,";
				sql1 += " (case when split_part(pathid,'_',9) is not null  then s9.unit_name else '' end) as lvl9";
			}
			if (m1_rpt.contains(":RPTFT2")) {
				sql1 += " ,chl_head_code as tr_head,h1.head_desc as head_desc,nconvertinr(fnd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as fnd_amt,nconvertinr(exp_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as exp_amt,nconvertinr(fwd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as fwd_amt,nconvertinr(bkd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as bkd_amt";
			} else {
				sql1 += " ,'' as tr_head,'' as head_desc,nconvertinr(fnd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as fnd_amt,nconvertinr(exp_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as exp_amt,nconvertinr(fwd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as fwd_amt,nconvertinr(bkd_amt::numeric,"+rsfmtd+",'"+rsfmtdt+"') as bkd_amt ";
			}
			if (m1_rpt.contains(":RPTFT")) {
				sql1 += " from ( select distinct b.path as pathid,a.psus_no,a.sus_no, a.chl_head_code,b.path,b.fnd_amt,b.exp_amt,b.fwd_amt,b.bkd_amt ";
				sql1 += " from fp.fp_tb_pref_head a inner join (";
				
				if (m1_rpt.contains(":RPTFT1")) {				
					//sql1 += " select psus_no,sus_no,npath as path,lvl,0 as fnd_amt,0 as exp_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tv_pref_head where ";
					sql1 += " select distinct psus_no,sus_no,replace(npath,'A000001_A000001','A000001') as path,0 as fnd_amt,0 as exp_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tv_pref_head where (psus_no,sus_no,chl_head_code,lvl) in (";
					sql1 += " select distinct psus_no,sus_no,chl_head_code,max(lvl) as lvl from fp.fp_tv_pref_head where ";
				}
				if (m1_rpt.contains(":RPTFT2")) {				
					//sql1 += " select psus_no,sus_no,chl_head_code,npath as path,lvl,0 as fnd_amt,0 as exp_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tv_pref_head where ";
					sql1 += " select distinct psus_no,sus_no,chl_head_code,replace(npath,'A000001_A000001','A000001') as path,0 as fnd_amt,0 as exp_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tv_pref_head where (psus_no,sus_no,chl_head_code,lvl) in (";
					sql1 += " select distinct psus_no,sus_no,chl_head_code,max(lvl) as lvl from fp.fp_tv_pref_head where ";
				}
				if (role.equalsIgnoreCase("FP_VIEW")) {				
					sql1 += " sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where "+m1lvlstr+" in (select "+m1lvlstr+" from fp.fp_tv_orbat_dtl where sus_no='"+m1lvl[2]+"'))";
				} else {
					//sql1 += " npath like '"+m1lvl[2]+"%'";
					sql1 += " base='"+m1lvl[2]+"'";					
				}
				sql1 += " group by psus_no,sus_no,chl_head_code) and split_part(npath,'_',1)='A000001'";
				/*if (m1_rpt.contains(":RPTFT1")) {
					sql1 += " group by psus_no,sus_no,npath,lvl";
				}*/
				
				/*sql1 += " select distinct psus_no,sus_no,array[psus_no::text] as path,0 as lvl from fp.fp_tb_pref_head  ";				
				if (role.equalsIgnoreCase("FP_VIEW")) {
					sql1 += " where (psus_no,sus_no) in (select distinct psus_no,sus_no from fp.fp_tv_orbat_dtl where "+m1lvlstr+" in (select "+m1lvlstr+" from fp.fp_tv_orbat_dtl where sus_no='"+m1lvl[2]+"'))";
					
					//sql1 += " where psus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where psus_no='"+m1lvl[2]+"')";
					
				} else {
					sql1 += " where psus_no='"+m1lvl[2]+"'";
					sql1 += " and sus_no='"+m1lvl[2]+"'";
				}
				sql1 += " union select ch.psus_no,ch.sus_no,p.path || array[ch.sus_no::text] as path,p.lvl+1 as lvl ";
				sql1 += " from fp.fp_tb_pref_head ch  inner join parents p on p.sus_no=ch.psus_no and ch.sus_no !=all(p.path)) ";
*/				
				
				sql1 += " ) b on a.psus_no=b.psus_no and a.sus_no=b.sus_no";
				if (m1_rpt.contains(":RPTFT2")) {
					sql1 += " and a.chl_head_code=b.chl_head_code";
				}
				sql1 += " ) a";
				
				
				sql1 += " left join fp.fp_tv_orbat_dtl s1 on split_part(pathid,'_',1)=s1.sus_no";													 
				sql1 += " left join fp.fp_tv_orbat_dtl s2 on split_part(pathid,'_',2)=s2.sus_no";													 
				sql1 += " left join fp.fp_tv_orbat_dtl s3 on split_part(pathid,'_',3)=s3.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s4 on split_part(pathid,'_',4)=s4.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s5 on split_part(pathid,'_',5)=s5.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s6 on split_part(pathid,'_',6)=s6.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s7 on split_part(pathid,'_',7)=s7.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s8 on split_part(pathid,'_',8)=s8.sus_no";
				sql1 += " left join fp.fp_tv_orbat_dtl s9 on split_part(pathid,'_',9)=s9.sus_no";
				if (m1_rpt.contains(":RPTFT2")) {
					sql1 += " inner join fp.fp_tv_head_dtl h1 on chl_head_code=h1.tr_head";
				}
/*				if (role.equalsIgnoreCase("FP_VIEW")) {
					sql1 += " where a.path like '"+m1lvl[2]+"%'";
				}
*/			}
			sql1 += " order by pathid";     
/*			if (m1_rpt.contains(":RPTBH1")) {
				sql1 = sql1 + " order by b.hq_type,z.fr_sus_no,z.to_sus_no,z.tr_head_to";
			} else {
				sql1 = sql1 + " order by z.tr_head_to,b.hq_type,z.fr_sus_no,z.to_sus_no";	
			}
*/			
			
			
			
			
			////System.out.println("nFundTree-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					if (m1_rpt.contains(":RPTFT1")) {
						list2.add(rs.getString("hlbh_sus"));
						list2.add(rs.getString("hlbh_name"));
						list2.add(rs.getString("bh_sus"));
						list2.add(rs.getString("bh_name"));						
						list2.add("");
						list2.add("");
						list2.add("");
						//list2.add(rs.getString("lvl"));
						list2.add(rs.getString("pathid"));
						list2.add(rs.getString("lvl1"));
						list2.add(rs.getString("lvl2"));
						list2.add(rs.getString("lvl3"));
						list2.add(rs.getString("lvl4"));
						list2.add(rs.getString("lvl5"));
						list2.add(rs.getString("lvl6"));
						list2.add(rs.getString("lvl7"));
						list2.add(rs.getString("lvl8"));
						list2.add(rs.getString("lvl9"));
						list2.add(rs.getString("fnd_amt"));
						list2.add(rs.getString("exp_amt"));
						list2.add(rs.getString("fwd_amt"));
						list2.add(rs.getString("bkd_amt"));
					} else {
						list2.add(rs.getString("hlbh_sus"));
						list2.add(rs.getString("hlbh_name"));
						list2.add(rs.getString("bh_sus"));
						list2.add(rs.getString("bh_name"));						
						list2.add(rs.getString("tr_head"));
						list2.add(rs.getString("head_desc"));
						//list2.add(rs.getString("lvl"));
						list2.add("");
						list2.add(rs.getString("pathid"));
						list2.add(rs.getString("lvl1"));
						list2.add(rs.getString("lvl2"));
						list2.add(rs.getString("lvl3"));
						list2.add(rs.getString("lvl4"));
						list2.add(rs.getString("lvl5"));
						list2.add(rs.getString("lvl6"));
						list2.add(rs.getString("lvl7"));
						list2.add(rs.getString("lvl8"));
						list2.add(rs.getString("lvl9"));
						list2.add(rs.getString("fnd_amt"));
						list2.add(rs.getString("exp_amt"));
						list2.add(rs.getString("fwd_amt"));
						list2.add(rs.getString("bkd_amt"));
					}
/*					list2.add(rs.getString("alt_amt"));
					list2.add(rs.getString("alt_date"));
					list2.add(rsfmt);					
*/					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundDraftPrint_Ent(String m1_tryear, String m1_lvl, String m1_rpt,
			String nUsrId, String m1_rptLvl, String rsfmt) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		String rsfmtd = "5";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			sql1 = " select distinct p.tr,p.tr_head,q.head_code,q.head_desc,q.major_head,q.mh_desc,q.minor_head,q.mnh_desc, ";
			sql1 = sql1 + " q.sub_head, q.sbh_desc,q.idx_1,q.idx_name, p.sus_no,o.form_code_control,o.unit_name,";
			sql1 = sql1
					+ " coalesce(p.fndamt,0) as fndamt, coalesce(p.prjamt,0) as prjamt,coalesce(p.prvamt,0) as prvamt,";
			sql1 = sql1 + " coalesce(p.altamt,0) as altamt,rs_fmt from (";
			sql1 = sql1 + " select distinct 1 as tr,tr_head,bg_sus as sus_no, cur_fund_s::numeric as fndamt,";
			sql1 = sql1
					+ " cur_proj_s::numeric as prjamt,prv_allot_s::numeric as prvamt,cur_allot_s::numeric as altamt,rs_fmt";
			sql1 = sql1 + " from fp.fp_tb_dft_allot where fr_sus_no='" + m1lvl[2]
					+ "' and upload_date in (select max(upload_date) as upload_date from fp.fp_tb_dft_allot where fr_sus_no='"
					+ m1lvl[2] + "')";
			sql1 = sql1
					+ " ) p left join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head left join fp.fp_tv_orbat_dtl o on p.sus_no=o.sus_no where (q.head_desc is not null or q.head_desc<>'') order by p.tr_head,p.tr,p.sus_no";
			////System.out.println("nFundDraftPrint_Ent-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("prvamt"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("form_code_control"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("tr"));
					list2.add(rs.getString("rs_fmt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nCrFundDraftAllot(String m1_tryear, String m1_lvl, String para, String para1,
			HttpSession sessionA) {
		
		////System.out.println("nCrFundDraftAllot-m1_tryear-"+m1_tryear);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (para1.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (para1.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (para1.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (para.equalsIgnoreCase("DRAFT")) {
				//System.out.println("Proc-DRAFT");
				sql1 = " select p.tr,p.tr_head,q.head_code,left(q.head_desc,150) head_desc,q.major_head,q.mh_desc,q.minor_head,q.mnh_desc, q.sub_head, q.sbh_desc,q.idx_1,q.idx_name, p.sus_no,o.form_code_control,o.unit_name,coalesce(p.fndamt,0) as fndamt, coalesce(p.prjamt,0) as prjamt,coalesce(p.prvamt,0) as prvamt,coalesce(p.altamt,0) as altamt,rs_fmt from (";
				sql1 = sql1 + " select distinct 1 as tr,tr_head,bg_sus as sus_no, ";
				sql1 = sql1
						+ " (case when rs_fmt='CR' then trunc((cur_fund_s::numeric),5) else trunc((cur_fund_s::numeric),2) end) as fndamt,";
				sql1 = sql1
						+ " (case when rs_fmt='CR' then trunc((cur_proj_s::numeric),5) else trunc((cur_proj_s::numeric),2) end) as prjamt,";
				sql1 = sql1
						+ " (case when rs_fmt='CR' then trunc((prv_allot_s::numeric),5) else trunc((prv_allot_s::numeric),2) end) as prvamt,";
				sql1 = sql1
						+ " (case when rs_fmt='CR' then trunc((cur_allot_s::numeric),5) else trunc((cur_allot_s::numeric),2) end) as altamt,rs_fmt ";
				sql1 = sql1 + " from fp.fp_tb_dft_allot where fr_sus_no='" + m1lvl[2]
						+ "' and upload_date=(select max(upload_date) as upload_date from fp.fp_tb_dft_allot where fr_sus_no='"
						+ m1lvl[2] + "' and fin_year='"+m1_tryear+"')";
				sql1 = sql1
						+ " ) p left join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head left join fp.fp_tv_orbat_dtl o on p.sus_no=o.sus_no where (q.head_desc is not null or q.head_desc<>'') order by p.tr_head,p.tr,p.sus_no";
			} else {
				//System.out.println("Proc-Others");
				sql1 = " select p.tr as tr,p.tr_head,q.head_code,left(q.head_desc,150) head_desc,q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,";
				sql1 = sql1 + " q.sub_head, q.sbh_desc,q.idx_1,q.idx_name, p.sus_no,o.form_code_control,o.unit_name,";
				sql1 = sql1 + " nconvertinr(coalesce(p.fndamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
				sql1 = sql1 + " nconvertinr(coalesce(p.prjamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as prjamt,";
				sql1 = sql1 + " nconvertinr(coalesce(p.prvamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as prvamt,";
				sql1 = sql1 + " nconvertinr(coalesce(p.altamt,0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as altamt,'" + para1 + "' as rs_fmt from (";
				sql1 = sql1
						+ " select min(a.tr) as tr,a.tr_head_to as tr_head,a.to_sus_no as sus_no,sum(fnd_amt) as fndamt,sum(prj_amt) as prjamt,";
				sql1 = sql1 + " sum(prv_amt) as prvamt,sum(alt_amt) as altamt from (";

				sql1 = sql1 + nFundbaseSql(":LVL", "", "4:", "HQ_" + m1_lvl, m1_tryear, sessionA, ":FND:PRJ:PRV:DALT");

				sql1 = sql1 + " ) a group by a.tr_head_to,a.to_sus_no";

				sql1 = sql1 + " ) p inner join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head";
				sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o on p.sus_no=o.sus_no";
				sql1 = sql1 + " where (q.head_desc is not null or q.head_desc<>'')";
				if (!m1lvl[0].equalsIgnoreCase("-1")) {
					sql1 = sql1 + " and p.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"
							+ m1lvl[2] + "')";
					sql1 = sql1
							+ " and p.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no='"
							+ m1lvl[2] + "')";
				}
				sql1 = sql1 + " and p.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"
						+ m1lvl[2] + "')";
				sql1 = sql1 + " order by p.tr_head,p.tr,o.hq_type,p.sus_no";
			}
			System.out.println("nk-nCrFundDraftAllot-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("prvamt"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("form_code_control"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("tr"));
					list2.add(rs.getString("rs_fmt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundDraftPrint_cndok(String m1_tryear, String m1_lvl, String m1_rpt,
			String nUsrId, String m1_rptLvl) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");

			sql1 = "select a.chl_head_code as tr_head,q.head_code,q.head_desc,q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,q.sub_head, q.sbh_desc,q.idx_1,q.idx_name,";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + "  o.sus_no as sus_no,o.unit_name,o.form_code_control,";
			} else {
				sql1 = sql1 + " '' as sus_no,'' as unit_name,'' as form_code_control,";
			}
			sql1 = sql1
					+ " b.fr_sus_no,coalesce(b.prvamt,0) as prvamt,coalesce(b.prjamt,0) as prjamt,coalesce(b.altamt,0) as altamt from ";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1
						+ " (select distinct w.sus_no,w.chl_head_code from (select sus_no,chl_head_code from fp.fp_tb_pref_head where chl_head_code in (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no='"
						+ m1lvl[2] + "')) w ";
				sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl x on w.sus_no=x.sus_no ";
				if (m1lvl[0].equalsIgnoreCase("-1")) {
					sql1 = sql1 + " where x.hq_type::integer >=-1 and x.hq_type::integer<=1";
				} else {
					sql1 = sql1 + " where x.hq_type::integer >=" + m1lvl[0] + " and x.hq_type::integer<="
							+ (Integer.parseInt(m1lvl[0]) + 1);
					if (m1lvl[0].equalsIgnoreCase("1")) {
						sql1 = sql1 + " and x.form_code_control like '" + m1lvl[1].substring(0, 1) + "%'";
					}
				}
				sql1 = sql1 + " ) a";
			} else {
				sql1 = sql1
						+ " (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where hq_type::integer<=1)	and chl_head_code in (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no='"
						+ m1lvl[2] + "')) a";
			}
			sql1 = sql1 + " left join (";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1
						+ " select t.fr_sus_no,t.tr_head_to as tr_head,t.to_sus_no,sum(prv_amt) as prvamt,sum(prj_amt) as prjamt,sum(alt_amt) as altamt from (";
			} else {
				sql1 = sql1
						+ " select max(t.fr_sus_no) as fr_sus_no,t.tr_head_to as tr_head,max(t.to_sus_no) as to_sus_no,sum(prv_amt) as prvamt,sum(prj_amt) as prjamt,sum(alt_amt) as altamt from (";
			}
			sql1 = sql1
					+ " select sus_no as fr_sus_no,tr_head,sus_no as to_sus_no,tr_head as tr_head_to,0 as prv_amt,proj_amt as prj_amt,0 as alt_amt from fp.fp_tb_proj_detl"
					+ fileYr + " where sus_no in (";
			sql1 = sql1 + " WITH RECURSIVE nRecPrefUnit AS (";
			sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from";
			sql1 = sql1 + " fp.fp_tb_pref_unit p where p.sus_no='" + m1lvl[2] + "'";
			sql1 = sql1
					+ " union select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no";
			sql1 = sql1 + " ) select distinct p.chl_sus_no as sus_no";
			sql1 = sql1 + " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no";
			sql1 = sql1 + " where q.hq_type::integer>=1";
			sql1 = sql1 + " ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head where sus_no='"
					+ m1lvl[2] + "')";
			sql1 = sql1 + " union all";
			sql1 = sql1
					+ " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as prv_amt,0 as prj_amt,0 as alt_amt from fp.fp_tb_trans_detl"
					+ fileYrPrv + " where fr_sus_no='" + m1lvl[2] + "'";
			sql1 = sql1 + " and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head where sus_no='"
					+ m1lvl[2] + "') and tr_type='TFR'";
			sql1 = sql1 + " union all ";
			sql1 = sql1
					+ " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as prv_amt,0 as prj_amt,exp_amt as alt_amt from fp.fp_tb_funds_detl"
					+ fileYr + " where to_sus_no='" + m1lvl[2] + "' ";
			sql1 = sql1 + " and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head where sus_no='"
					+ m1lvl[2] + "') and tr_type='TFR'";
			sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),1) as maxlvl from fp.fp_tb_funds_detl" + fileYr;
			sql1 = sql1 + " where fr_sus_no='" + m1lvl[2] + "')";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " ) t group by t.fr_sus_no,t.tr_head_to,t.to_sus_no";
			} else {
				sql1 = sql1 + " ) t group by t.tr_head_to";
			}
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " ) b on a.sus_no=b.to_sus_no and a.chl_head_code=b.tr_head ";
			} else {
				sql1 = sql1 + " ) b on a.chl_head_code=b.tr_head";
			}
			sql1 = sql1 + " left join fp.fp_tv_head_dtl q on a.chl_head_code=q.tr_head";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.sus_no=o.sus_no ";
				sql1 = sql1 + " order by a.chl_head_code,o.hq_type,o.form_code_control,b.to_sus_no";
			} else {
				sql1 = sql1 + " order by a.chl_head_code";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head") == null ? "N/A" : rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("prvamt"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("form_code_control"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundDraftPrint_old(String m1_tryear, String m1_lvl, String m1_rpt,
			String nUsrId, String m1_rptLvl) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String m1lvl[] = m1_lvl.split("_");
			if (m1_lvl.equalsIgnoreCase(null)) {
				m1_lvl = "BE";
			}
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = " select q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,q.sub_head,q.sbh_desc,p.tr_head,q.head_code,q.head_desc,p.ppid,p.fmn_code,p.sus_no,p.fndamt,p.altamt,p.expamt,p.period,q.idx_1,q.idx_name,p.prjamt,p.prvamt,r.unit_name from (";
			} else {
				sql1 = " select q.major_head,q.mh_desc,q.minor_head,q.mnh_desc,q.sub_head,q.sbh_desc,p.tr_head,q.head_code,q.head_desc,p.ppid,p.fmn_code,p.sus_no,p.fndamt,p.altamt,p.expamt,p.period,q.idx_1,q.idx_name,p.prjamt,p.prvamt,'' as unit_name from (";
			}
			if (m1lvl[0].equalsIgnoreCase("-1")) {

				sql1 = sql1
						+ " select a.id as ppid,a.tr_head,a.data_upd_date as period,form_code_control as fmn_code,sus_no,0 as fndamt,0 as altamt,0 as expamt,proj_amt/10000000 as prjamt,0 as prvamt from fp.fp_tb_proj_detl20 a where a.sus_no='A000001' and a.est_type='PRJ'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT a.id as ppid,tr_head_to as tr_head, ";
				sql1 = sql1
						+ " a.period,a.to_fmn_code as fmn_code,to_sus_no as sus_no,0 as fndamt,exp_amt/10000000 as altamt,0 as expamt,0 as prjamt,0 as prvamt FROM fp.fp_tb_trans_detl20 a";
				sql1 = sql1 + " where a.fr_sus_no='" + m1lvl[2]
						+ "' and a.tr_type='TFR' and not (split_part(tr_head_to,':',1) is null or split_part(tr_head_to,':',1)='') and exp_amt>0";
			} else {

				sql1 = sql1
						+ " SELECT a.id as ppid,concat(split_part(a.tr_head_to,':',1),':',split_part(a.tr_head_to,':',2),':',split_part(a.tr_head_to,':',3),':',split_part(a.tr_head_to,':',4))  as tr_head,";
				sql1 = sql1 + " a.period,a.to_fmn_code as fmn_code,'" + m1lvl[2]
						+ "' as sus_no,0 as fndamt,exp_amt/10000000 as altamt,0 as expamt,0 as prjamt,0 as prvamt FROM fp.fp_tb_trans_detl20 a";
				sql1 = sql1 + " where a.fr_sus_no='" + m1lvl[2] + "' and a.tr_type='TFR'";
			}
			sql1 = sql1 + " ) p ";
			sql1 = sql1 + " left join fp.fp_tv_head_dtl q on p.tr_head=q.tr_head";
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				sql1 = sql1 + " left join fp.fp_tv_orbat_dtl r on p.sus_no=r.sus_no";
			}
			sql1 = sql1 + " order by q.major_head,q.minor_head,q.sub_head,q.idx_1,q.head_code";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("major_head"));
					list2.add(rs.getString("mh_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("sub_head"));
					list2.add(rs.getString("sbh_desc"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("idx_1"));
					list2.add(rs.getString("idx_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("period"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("prvamt"));
					list2.add(rs.getString("unit_name"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInDetl(String nPara, String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");

		String m1_hdlvlq = nParaS[1];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String fileYr = "20";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}
		try {
			conn = dataSource.getConnection();

			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}

			sql1 = " select a.fr_sus_no,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			sql1 = sql1 + " trunc((sum(a.alt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as altamt,";
			sql1 = sql1 + " trunc((sum(a.exp_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as expamt,";
			sql1 = sql1 + " trunc((sum(a.edt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as edtamt,";
			sql1 = sql1 + " trunc((sum(a.gst_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as gstamt,";
			sql1 = sql1 + " trunc((sum(a.oth_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as othamt,";
			sql1 = sql1 + " trunc((sum(a.fwd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as fwdamt,";
			sql1 = sql1 + " trunc((sum(a.bkd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as bkdamt";
			sql1 = sql1 + " from (";
			sql1 = sql1
					+ " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,0 as gst_amt,";
			sql1 = sql1 + " 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"
					+ fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "' ";
			sql1 = sql1
					+ " and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head where tr_type in ('TFR') ";
			sql1 = sql1 + " and sus_no='" + nParaS[4] + "') ";
			sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl" + fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt,";
			sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt,";
			sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt,";
			sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt,";
			sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,0 as fwd_amt,0 as bkd_amt,";
			sql1 = sql1 + " tr_type from fp.fp_tb_trans_detl" + fileYr
					+ " where fr_sus_no in  ( select distinct t.chl_sus_no from ( ";
			sql1 = sql1
					+ " WITH RECURSIVE nRecPrefUnit AS (  select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4] + "' ";
			sql1 = sql1 + " union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2] + ") ";
			sql1 = sql1 + " t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "') and tr_type in ('EXP','GST','EDT','OTH')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select '" + nParaS[4]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to, 0 as alt_amt,";
			sql1 = sql1
					+ " 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,a.bkd_amt as bkd_amt,";
			sql1 = sql1 + " 'CDA' as tr_type  from fp.fp_tb_cda_detl" + fileYr + " a where a.sus_no in  ( ";
			sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (  ";
			sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4]
					+ "' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no   ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2] + ") ";
			sql1 = sql1 + " t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			sql1 = sql1 + " group by fr_sus_no,tr_head_to,to_sus_no";
			sql1 = sql1 + " order by fr_sus_no,tr_head_to,to_sus_no";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add(rs.getString("fr_sus_no"));
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInDetlTr(String nPara, String nUsrId, HttpSession sessionA) {
		//System.out.println("Method-nFundInDetlTr-para-"+nPara+","+nUsrId);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String hdCnd2 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");

		String m1_hdlvlq = nParaS[1];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = nParaS[7];
		String fileYr = fp_trDao.nGetAdmFinYear("CFY");
		String rsfmt = "CR";

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}
		String trhead = m1_hdlvlq;
		String m1_lvl = nParaS[2] + "_" + nParaS[3] + "_" + nParaS[4];

		try {
			conn = dataSource.getConnection();

			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				hdCnd2 = "split_part(chl_head_code,':',1) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				hdCnd2 = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				hdCnd2 = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2),':',split_part(chl_head_code,':',3)) ";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				hdCnd2 = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2),':',split_part(chl_head_code,':',3),':',split_part(chl_head_code,':',4)) ";
			}
			sql1 = " select 0 as ppid,p.fr_sus_no,o1.unit_name,o1.hq_type,p.tr_head,b.head_desc as head_name,p.to_sus_no as sus_no,o2.unit_name,o2.form_code_control as fmn_code,";
			sql1 = sql1
					+ " o2.hq_type,p.tr_head_to,p.prj_amt as prjamt,p.fnd_amt as fndamt,p.alt_amt as altamt,";
			sql1 = sql1	+ " (case when tr_type='EXP' then p.exp_amt when tr_type='GST' then p.gst_amt when tr_type='OTH' then p.oth_amt when tr_type='EDT' then p.edt_amt end) as expamt,";
			sql1 = sql1	+ " p.fwd_amt as fwdamt,p.bkd_amt as bkdamt,p.tr_type,";
			sql1 = sql1 + " (case when tr_type='FND' then concat('Fund Recieved from ',o1.unit_name) else (";
			sql1 = sql1 + " case when tr_type in ('EXP','GST','OTH','EDT') then concat('Expenditure by ',o2.unit_name) else (";
			sql1 = sql1
					+ " case when tr_type='TFR' then concat('Trf/Allot to ',o2.unit_name) else '' end) end ) end ) as rem,to_char(period,'dd-mm-yyyy') as period,period as period1";
			sql1 = sql1 + " from (";

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, fileYr, sessionA,
					":FND:EXP:PRJ:ALT:CDA");

			sql1 = sql1 + " ) p  ";
			//sql1 = sql1 + " left join fp.fp_tb_head_mstr b on " + hdCnd + "=b.tr_head";
			sql1 = sql1 + " left join fp.fp_tb_head_mstr b on p.tr_head_to=b.tr_head";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o1 on p.fr_sus_no=o1.sus_no";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl o2 on p.to_sus_no=o2.sus_no";
			sql1 = sql1 + " order by " + hdCnd + ",period1";
			//System.out.println("nFundInDetlTr-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head_name"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("ppid"));
					list2.add(rs.getString("prjamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add(rs.getString("rem"));
					list2.add(rs.getString("period"));
					list2.add(rs.getString("tr_type"));
					list2.add(rs.getString("tr_head_to"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInDetl_old(String nPara, String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String hdCnd = "";
			String hdCnd1 = "";
			String nParaS[] = nPara.replace("ln", "").split("_");
			String m1_hdlvlq = nParaS[1];
			String m1_hdlvl[] = m1_hdlvlq.split(":");
			String m1lvl = nParaS[4];
			String m1lvl1 = nParaS[2];

			if (m1_hdlvl[0].equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
			}
			if (m1_hdlvl[0].equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			}
			if (m1_hdlvl[0].equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			}
			if (m1_hdlvl[0].equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			}
			sql1 = " select p.pid,p.id,p.tr_head,p.head,b.head_desc,p.fmn_code,p.sus_no,c.unit_name,p.fndamt,p.altamt,p.expamt,p.tr_type,p.to_remarks,";
			if (m1lvl1.equalsIgnoreCase("-1")) {

				sql1 = sql1 + " (case when tr_type='FND' then concat('Fund Recieved in Head-',b.head_desc) else (";
				sql1 = sql1 + " case when tr_type='EXP' then concat('Expense') else (";
				sql1 = sql1
						+ " case when tr_type='TFR' then concat('Trf/Allot to ',c.unit_name) else '' end) end ) end ) as rem,to_char(period,'dd-mm-yyyy') as period,allot_for,prjamt";
				sql1 = sql1 + " from (";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,to_fmn_code as fmn_code,to_sus_no as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and tr_type='PRJ'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,to_fmn_code as fmn_code,to_sus_no as sus_no,recd_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and to_sus_no='" + m1lvl + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,to_fmn_code as fmn_code,to_sus_no as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and fr_sus_no='" + m1lvl + "' and  tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " SELECT id,pid,tr_head_to as tr_head,tr_head_to as head,period,fr_fmn_code as fmn_code,fr_sus_no as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and fr_sus_no='" + m1lvl + "' and  tr_type='EXP'";
			} else {

				sql1 = sql1
						+ " (case when tr_type='TFR' and fndamt>0 then concat('Fund Recieved from ',c.unit_name) else (";
				sql1 = sql1 + " case when tr_type='EXP' then concat('Expense') else (";
				sql1 = sql1
						+ " case when tr_type='TFR' and altamt>0 then concat('Trf/Allot to ',c.unit_name) else '' end) end ) end ) as rem,period,allot_for";
				sql1 = sql1 + " from ( ";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,to_fmn_code as fmn_code,to_sus_no as sus_no,0 as fndamt,0 as altamt,0 as expamt,exp_amt as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and to_sus_no='" + m1lvl + "' and tr_type='PRJ'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,fr_fmn_code as fmn_code,fr_sus_no as sus_no,exp_amt as fndamt,0 as altamt,0 as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and to_sus_no='" + m1lvl + "' and tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1 + " SELECT id,pid," + hdCnd
						+ " as tr_head,tr_head_to as head,period,to_fmn_code as fmn_code,to_sus_no as sus_no,0 as fndamt,exp_amt as altamt,0 as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and fr_sus_no='" + m1lvl + "' and  tr_type='TFR'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " SELECT id,pid,tr_head_to as tr_head,tr_head_to as head,period,fr_fmn_code as fmn_code,fr_sus_no as sus_no,0 as fndamt,0 as altamt,exp_amt as expamt,0 as prjamt,tr_type,to_remarks,allot_for FROM fp.fp_tb_trans_tmp where userid='"
						+ nUsrId + "' and fr_sus_no='" + m1lvl + "' and  tr_type='EXP'";
			}
			sql1 = sql1 + " ) p ";
			sql1 = sql1 + " inner join fp.fp_tb_head_mstr b on p.head=b.tr_head";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl c on p.sus_no=c.sus_no";
			sql1 = sql1 + " order by pid,id,period";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("pid"));
					list2.add(rs.getString("id"));
					list2.add(rs.getString("head"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("tr_type"));
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fmn_code"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("to_remarks"));
					list2.add(rs.getString("rem"));
					list2.add(rs.getString("period"));
					list2.add(rs.getString("allot_for"));
					list2.add(rs.getString("prjamt"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundStatusHQHeadD(String nParaValue, String nPara, String domainid) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		nParaValue = nParaValue.toUpperCase();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select fr_fmn_code,max(q.unit_name) as hq_name,a.tr_head,max(head_desc) as head,sum(recd_amt) as totrecd,sum(exp_amt) as totexp from fp.fp_tb_trans_detl a";
			sql1 = sql1 + " left join fp.fp_tb_head_mstr b on a.tr_head=b.tr_head";
			sql1 = sql1 + " left join nrv_hq q on a.fr_fmn_code=q.form_code_control";
			sql1 = sql1 + " where fr_fmn_code='1000000000'";
			sql1 = sql1 + " group by fr_fmn_code,a.tr_head";
			sql1 = sql1 + " order by fr_fmn_code,a.tr_head";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("head"));
					list2.add(rs.getString("totrecd"));
					list2.add(rs.getString("totexp"));
					list2.add("0");
					list2.add(rs.getString("fr_fmn_code"));
					list2.add(rs.getString("hq_name"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public int nFindFundL0(String id, HttpSession nSesn, String nFlag, String nUntTy, String m1_lvl, String rlLvl,
			String m1_tryear) {
		String hqLvl[] = m1_lvl.split("_");
		String usrnm = nSesn.getAttribute("username").toString();
		String hqid = "0";

		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		try {
			nConn = dataSource.getConnection();
			String sql1 = "";
			sql1 = "select min(pid) as id from fp.fp_tb_trans_detl" + fileYr + " where to_sus_no=?";
			PreparedStatement stmt = nConn.prepareStatement(sql1);
			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				hqid = "0";
				rs.close();
				stmt.close();
			} else {
				if (rs.next()) {
					hqid = rs.getString("id");

				} else {
					hqid = "0";
				}
			}
			rs.close();
			stmt.close();

			String sql0 = "";
			sql0 = " delete from fp.fp_tb_trans_tmp where userid=?";

			PreparedStatement stmt0 = nConn.prepareStatement(sql0);
			stmt0.setString(1, usrnm);
			stmt0.execute();
			stmt0.close();
			String sql2 = "";
			sql2 = " insert into fp.fp_tb_trans_tmp (userid,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,allot_for) ";
			sql2 = sql2
					+ " select ?,id,tr_head,data_upd_date,form_code_control,sus_no,0,amount,form_code_control,sus_no,tr_head,'PRJ' as tr_type,data_upd_date,data_upd_by,data_upd_date,data_upd_by,remarks,id,'PJ' as allot_for from fp.fp_tb_proj_detl"
					+ fileYr + " where est_type='PRJ'";
			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {

				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						sql2 = sql2 + " and sus_no=?";
					} else {
						if (nUntTy.equalsIgnoreCase("0")) {
							sql2 = sql2 + " and sus_no=?";
						}
						if (nUntTy.equalsIgnoreCase("1")) {
							sql2 = sql2 + " and substring(form_code_control,1,1)=substring(?,1,1)";
						}
						if (nUntTy.equalsIgnoreCase("2")) {
							sql2 = sql2 + " and substring(form_code_control,1,3)=substring(?,1,3)";
						}
						if (nUntTy.equalsIgnoreCase("3")) {
							sql2 = sql2 + " and substring(form_code_control,1,6)=substring(?,1,6)";
						}
						if (nUntTy.equalsIgnoreCase("4")) {
							sql2 = sql2 + " and substring(form_code_control,1,10)=substring(?,1,10)";
						}
					}
				}
			} else {
				sql2 = sql2 + " and sus_no=?";
			}

			PreparedStatement stmt2 = nConn.prepareStatement(sql2);
			stmt2.setString(1, usrnm);
			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {
				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						stmt2.setString(2, hqLvl[2]);
					} else {
						if (nUntTy.equalsIgnoreCase("0")) {
							stmt2.setString(2, hqLvl[2]);
						} else {
							stmt2.setString(2, hqLvl[1]);
						}
					}
				}
			} else {
				stmt2.setString(2, hqLvl[2]);
			}
			stmt2.execute();
			stmt2.close();

			String sql3 = "";
			sql3 = " insert into fp.fp_tb_trans_tmp (userid,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,allot_for) ";
			sql3 = sql3
					+ " select ?,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,tr_sub_type from fp.fp_tb_trans_detl"
					+ fileYr + " where tr_type='EXP'";

			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {
					sql3 = sql3 + " or tr_type='FND'";
				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						sql3 = sql3 + " and to_sus_no=?";
					} else {
						if (nUntTy.equalsIgnoreCase("0")) {
							sql3 = sql3 + " and to_sus_no=?";
						}
						if (nUntTy.equalsIgnoreCase("1")) {
							sql3 = sql3 + " and substring(to_fmn_code,1,1)=substring(?,1,1)";
						}
						if (nUntTy.equalsIgnoreCase("2")) {
							sql3 = sql3 + " and substring(to_fmn_code,1,3)=substring(?,1,3)";
						}
						if (nUntTy.equalsIgnoreCase("3")) {
							sql3 = sql3 + " and substring(to_fmn_code,1,6)=substring(?,1,6)";
						}
						if (nUntTy.equalsIgnoreCase("4")) {
							sql3 = sql3 + " and substring(to_fmn_code,1,10)=substring(?,1,10)";
						}
					}
				}
			} else {
				sql3 = sql3 + " and to_sus_no=? and fr_sus_no=?";
			}

			PreparedStatement stmt3 = nConn.prepareStatement(sql3);
			stmt3.setString(1, usrnm);
			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {
				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						stmt3.setString(2, hqLvl[2]);
					} else {
						if (nUntTy.equalsIgnoreCase("5")) {
							stmt3.setString(2, hqLvl[2]);
						} else {
							stmt3.setString(2, hqLvl[1]);
						}
					}
				}
			} else {
				stmt3.setString(2, hqLvl[2]);
				stmt3.setString(3, hqLvl[2]);
			}
			stmt3.execute();
			stmt3.close();

			nFindFundL2(hqid, nSesn, nConn, "P", nUntTy, m1_lvl, rlLvl, fileYr.substring(0, 2) + fileYrPrv);
			nFindFundL2(hqid, nSesn, nConn, nFlag, nUntTy, m1_lvl, rlLvl, m1_tryear);

			nConn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int nFindFundL2(String id, HttpSession nSesn, Connection nConn, String nFlag, String nUntTy, String m1_lvl,
			String rlLvl, String m1_tryear) {
		String usrnm = nSesn.getAttribute("username").toString();
		String hqLvl[] = m1_lvl.split("_");
		String nPflg = "N";
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) + 1) + "";
		try {
			String sql1 = "";
			sql1 = " insert into fp.fp_tb_trans_tmp (userid,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,allot_for) ";
			if (nFlag.equalsIgnoreCase("P")) {
				sql1 = sql1 + " SELECT '" + usrnm
						+ "' as userid,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,tr_sub_type from fp.fp_tb_trans_detl"
						+ fileYrPrv + " where tr_type='TFR'";
			} else {
				sql1 = sql1 + " SELECT '" + usrnm
						+ "' as userid,id,tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,tr_sub_type from fp.fp_tb_trans_detl"
						+ fileYr + " where tr_type='TFR'";
			}
			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {
					sql1 = sql1 + " and fr_fmn_code='X000000000'";
					nPflg = "N";
				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						sql1 = sql1 + " and to_sus_no=?";
					} else {
						if (nUntTy.equalsIgnoreCase("0")) {
							sql1 = sql1 + " and (to_sus_no=? or fr_sus_no=?)";
						}
						if (nUntTy.equalsIgnoreCase("1")) {
							sql1 = sql1 + " and substring(to_fmn_code,1,1)=substring(?,1,1)";
						}
						if (nUntTy.equalsIgnoreCase("2")) {
							sql1 = sql1 + " and substring(to_fmn_code,1,3)=substring(?,1,3)";
						}
						if (nUntTy.equalsIgnoreCase("3")) {
							sql1 = sql1 + " and substring(to_fmn_code,1,6)=substring(?,1,6)";
						}
						if (nUntTy.equalsIgnoreCase("4")) {
							sql1 = sql1 + " and substring(to_fmn_code,1,10)=substring(?,1,10)";
						}
					}
					nPflg = "N";
				}
			} else {
				if (nUntTy.equalsIgnoreCase("5")) {
					sql1 = sql1 + " and to_sus_no=?";
					nPflg = "N";
				} else {
					sql1 = sql1 + " and fr_sus_no=? and to_sus_no=?";
					nPflg = "N";
				}
			}

			PreparedStatement stmt = nConn.prepareStatement(sql1);
			int filcnt = 1;
			if (rlLvl.equalsIgnoreCase("HQ")) {
				if (nUntTy.equalsIgnoreCase("-1")) {
				} else {
					if (nUntTy.equalsIgnoreCase("5")) {
						stmt.setString(filcnt, hqLvl[2]);
						filcnt = filcnt + 1;
					} else {
						if (nUntTy.equalsIgnoreCase("0")) {
							stmt.setString(filcnt, hqLvl[2]);
							filcnt = filcnt + 1;
							stmt.setString(filcnt, hqLvl[2]);
							filcnt = filcnt + 1;
						} else {
							stmt.setString(filcnt, hqLvl[1]);
							filcnt = filcnt + 1;
						}
					}
				}
			} else {
				if (nUntTy.equalsIgnoreCase("5")) {
					stmt.setString(filcnt, hqLvl[2]);
					filcnt = filcnt + 1;
				} else {
					stmt.setString(filcnt, hqLvl[2]);
					filcnt = filcnt + 1;
					stmt.setString(filcnt, hqLvl[2]);
				}
			}
			stmt.execute();
			stmt.close();

			if (nPflg.equalsIgnoreCase("Y")) {

				String sql0 = "";
				sql0 = " update fp.fp_tb_trans_tmp set active='N' where userid=? and active='Y' and id=?";

				PreparedStatement stmt0 = nConn.prepareStatement(sql0);
				stmt0.setString(1, usrnm);
				stmt0.setInt(2, Integer.parseInt(id));
				stmt0.execute();
				stmt0.close();
				String sql2 = "";
				sql2 = " select * from fp.fp_tb_trans_tmp where active='Y' and userid=?";

				PreparedStatement stmt2 = nConn.prepareStatement(sql2);
				stmt2.setString(1, usrnm);

				ResultSet rs = stmt2.executeQuery();

				if (!rs.isBeforeFirst()) {
					rs.close();
					stmt.close();

					nConn.close();
					return 0;
				} else {
					if (rs.next()) {
						id = rs.getString("id");

						nFindFundL2(id, nSesn, nConn, "N", nUntTy, m1_lvl, rlLvl, m1_tryear);
					}
				}
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int nUpdUpload(String nUsrId) {
		Connection conn = null;
		int rs = 0;
		try {
			conn = dataSource.getConnection();
			String sql1 = "update fp.fp_tmp_be_upload set be_amount = (case when split_part(be_amt_str,'.',1)::integer>0 then be_amt_str::numeric * 10000000 else be_amt_str::numeric * 10000000 end) where length(be_amt_str)>0 and not (be_amt_str like '%BE%' or be_amt_str like '%REF%')";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			rs = stmt.executeUpdate();
			stmt.close();
			String sql2 = "update fp.fp_tmp_be_upload a set to_dte = b.cmd_name from ( select (case when split_part(code,'/',3)='83' then 'HQ NORTHERN COMMAND' when split_part(code,'/',3)='84' then 'HQ SOUTHERN COMMAND' when split_part(code,'/',3)='85' then 'HQ EASTERN COMMAND' when split_part(code,'/',3)='86' then 'HQ WESTERN COMMAND' when split_part(code,'/',3)='87' then 'HQ CENTRAL COMMAND' when split_part(code,'/',3)='89' then 'HQ SOUTH WESTERN COMMAND' when (split_part(code,'/',1)='433' and split_part(code,'/',3)='88') then 'DGIS DDGIT' else '' end) as cmd_name, concat('/',split_part(code,'/',3)) as cmd_cd,code from fp.fp_tmp_be_upload where split_part(code,'/',3) <>'') b where a.code=b.code";

			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			rs = stmt2.executeUpdate();
			stmt.close();

			String q4 = "";
			q4 = "insert into fp.fp_tb_trans_detl20 (tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,allot_for)";
			q4 = q4 + " select major_head as tr_head,";
			q4 = q4 + " localtimestamp as period,'X000000000' as fr_fmn_code,'A000001' as fr_sus_no,0 as recd_amt,be_amount as exp_amt,";
			q4 = q4 + " form_code_control as to_fmn_code,sus_no as to_sus_no,concat(major_head,':',minor_head,':',sub_head,':',head_code) as tr_head_to,";
			q4 = q4 + " 'TFR' as tr_type,localtimestamp as data_cr_date,'fp_user' as data_cr_by,localtimestamp as data_upd_date,'fp_user' as data_upd_by,'' as to_remarks,(select max(id) as id from fp.fp_tb_trans_detl20 where tr_type='FND' and to_sus_no='A000001') as pid,allot_for";
			q4 = q4 + " from (select b.form_code_control,b.sus_no,upper(b.unit_name) as unit_name,c.major_head,c.minor_head,c.sub_head,c.head_code,a.head_desc,a.to_dte,a.code,a.be_amount,a.id,a.be_amt_str,a.upload_for as allot_for from fp.fp_tmp_be_upload a";
			q4 = q4 + " left join fp.fp_tv_orbat_dtl b on upper(a.to_dte)=upper(b.unit_name)";
			q4 = q4 + " left join fp.fp_tb_head_mstr c on c.head_code=left(a.code,6)";
			q4 = q4 + " where length(a.code)>0 and b.sus_no is not null ) a";

			PreparedStatement stmt3 = conn.prepareStatement(q4);
			rs = stmt3.executeUpdate();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int nUploadSubmit(HttpSession nSesn, String nPara, String nEstType, String m1_tryear) {
		String usrnm = nSesn.getAttribute("username").toString();
		String ususno = nSesn.getAttribute("roleSusNo").toString();

		//m1_tryear = fp_trDao.nGetAdmFinYear("CFY");

		String fileYr = m1_tryear.substring(2, 4);

		Connection conn = null;
		int rs = 0;
		String nUplLvl = "";
		try {
			conn = dataSource.getConnection();

			String sql1 = "";
			sql1 = "select coalesce(max(fund_lvl),0)+1 as maxlvl from fp.fp_tb_funds_detl" + fileYr;
			sql1 = sql1 + " where fr_sus_no='" + ususno + "' and allot_for='" + nEstType + "'";
			PreparedStatement stmt1 = conn.prepareStatement(sql1);

			ResultSet rs1 = stmt1.executeQuery();

			if (!rs1.isBeforeFirst()) {
				nUplLvl = "1";
				rs1.close();
				stmt1.close();
			} else {
				if (rs1.next()) {
					nUplLvl = rs1.getString("maxlvl");
				} else {
					nUplLvl = "1";
				}
			}
			rs1.close();
			stmt1.close();

			String q4 = "";
			q4 = "insert into fp.fp_tb_funds_detl" + fileYr + " (";
			q4 = q4 + " tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,";
			q4 = q4 + " to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,";
			q4 = q4 + " to_remarks,pid,allot_for,tr_sub_type,fund_lvl)";
			q4 = q4 + " select (case when a.fr_sus_no='A000001' then split_part(a.tr_head,':',1) else a.tr_head end) as tr_head,";
			q4 = q4 + " a.upload_date,'' as fr_fmn_code,a.fr_sus_no,0 as recd_amt,(case when upper(a.rs_fmt) ='CR' then 1e+7 when upper(a.rs_fmt) ='LC' then 1e+5 else 1 end)*a.cur_allot_s::numeric as exp_amt,a.bg_hlder_fmn as to_fmn_code,";
			q4 = q4 + " a.bg_sus as to_sus_no,a.tr_head as tr_head_to,'TFR' as tr_type,a.data_upd_date as data_cr_date,a.data_upd_by as data_cr_by,";
			q4 = q4 + " a.data_upd_date as data_upd_date,a.data_upd_by as data_upd_by,'' as to_remarks,0 as pid,";
			q4 = q4 + " a.upload_for as allot_for,'ST' as tr_sub_type," + nUplLvl
					+ " as fund_lvl from fp.fp_tmp_be_upload a";
			//q4 = q4 + " where length(tr_head)>0 and cur_allot_s::numeric>0 and length(cur_allot_s)>0 and fr_sus_no='"
			q4 = q4 + " where length(tr_head)>0 and length(cur_allot_s)>0 and fr_sus_no='"
					+ ususno + "'";
			q4 = q4 + " and upload_date in (select max(upload_date) as dt from fp.fp_tmp_be_upload where fr_sus_no='"
					+ ususno + "')";
			////System.out.println("Inser excel - "+q4);
			PreparedStatement stmt3 = conn.prepareStatement(q4);
			rs = stmt3.executeUpdate();
			stmt3.close();
			
			if (ususno.equalsIgnoreCase("A000001")) {
				String nFin = m1_tryear;
				int nFin1 = (Integer.parseInt(nFin) + 1);
				String nMsg = "New Fund for Financial Year " + nFin + " - " + nFin1;
				nMsg = nMsg + " has been Allocated by DGFP";
				Boolean b = fp1_Dao.nSaveAlertMsg(nMsg, "", "");
			} else {

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ArrayList<ArrayList<String>> nGetAlertMsg(String nPara, String nUsrId) {
		//System.out.println("nGetAlertMsg-"+nPara);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		//System.out.println("nGetAlertMsg-"+nParaS.length);
		if(nParaS.length>3) {
			try {
				conn = dataSource.getConnection();
				sql1 = " select *  from (";
				sql1 += " select a.id,a.msg_id,a.msg_status,";
				sql1 += " b.msg_desc,b.msg_type,to_char(a.msg_start_date, 'DD Mon YYYY') as dt,a.msg_start_date as tr_dt  from fp.mms_tb_msg_hld_detl a left join fp.mms_tb_msg_mstr b on a.msg_id=b.msg_id";
				sql1 += " where now()::date between a.msg_start_date and a.msg_stop_date ";
				sql1 += " and ((upper(msg_target)='ALL')";
				sql1 += " or a.msg_src in (select distinct sus_no from fp.fp_tb_pref_unit ";
				
				sql1 += " where chl_sus_no ='" + nParaS[3] + "'))";
				
				sql1 += " union all ";
				sql1 += " select a.id,a.msg_id,a.msg_status,a.msg_desc,a.msg_type,a.data_cr_date as dt, a.data_cr_date as tr_dt from fp.mms_tb_msg_mstr a where (upper(msg_type) like '%WIN')";
				sql1 += " ) p order by dt desc,msg_id desc";
				
				
				sql1 = " select *  from ("; 					
				sql1 += " select a.id,a.msg_id,a.msg_status, b.msg_desc,b.msg_type,to_char(a.msg_start_date, 'DD Mon YYYY') as dt,a.msg_start_date as tr_dt  from fp.mms_tb_msg_hld_detl a ";
				sql1 += " left join fp.mms_tb_msg_mstr b on a.msg_id=b.msg_id where now()::date between a.msg_start_date and a.msg_stop_date  and (";
				sql1 += " (upper(msg_target)='ALL')	or (upper(msg_target)='AFP' and a.msg_src in (select distinct sus_no from fp.fp_tb_pref_unit ))"; 
				sql1 += " or (upper(msg_target)='BH' and a.msg_src in (select distinct sus_no from fp.fp_tb_pref_unit where chl_sus_no ='" + nParaS[3] + "')))"; 
				sql1 += " union all ";  
				sql1 += " select a.id,a.msg_id,a.msg_status,a.msg_desc,a.msg_type,to_char(a.data_cr_date, 'DD Mon YYYY') as dt, a.data_cr_date as tr_dt from fp.mms_tb_msg_mstr a ";
				sql1 += " left join fp.mms_tb_msg_hld_detl b on b.msg_id=a.msg_id where (upper(a.msg_type) like '%WIN') ";
				sql1 += " and (now()::date between b.msg_start_date and b.msg_stop_date)"; 					   
				sql1 += " ) p order by tr_dt desc,msg_id desc";
				
				
				
				//System.out.println("nGetAlertMsg-"+sql1);
				PreparedStatement stmt = conn.prepareStatement(sql1);
				ResultSet rs = stmt.executeQuery();
				if (!rs.isBeforeFirst()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add("NIL");
					li.add(list);
				} else {
					while (rs.next()) {
						ArrayList<String> list2 = new ArrayList<String>();
						list2.add(rs.getString("id"));
						list2.add(rs.getString("msg_id"));
						list2.add(rs.getString("msg_desc"));
						list2.add(rs.getString("msg_type"));
						list2.add(rs.getString("dt"));
						li.add(list2);
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInfoDBDetl_nk(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");
		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String susdetl = "HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");

		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		String trhead = "4:";
		String aa = nFundbaseSql(nPara, nUsrId, trhead, susdetl, cfy, sessionA, ":FND:PRV:PRJ:ALT");

		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}
		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '1' as tlvl,a.tr_head_to as tr_head_to,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '1' as tlvl,a.to_sus_no as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(a.tr_head_to) as tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " trunc((sum(a.alt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as altamt,";
			sql1 = sql1 + " trunc((sum(a.exp_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as expamt,";
			sql1 = sql1 + " trunc((sum(a.edt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as edtamt,";
			sql1 = sql1 + " trunc((sum(a.gst_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as gstamt,";
			sql1 = sql1 + " trunc((sum(a.oth_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as othamt,";
			sql1 = sql1 + " trunc((sum(a.fwd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as fwdamt,";
			sql1 = sql1 + " trunc((sum(a.bkd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as bkdamt";
			sql1 = sql1 + " from (";
			sql1 = sql1
					+ " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,0 as gst_amt,";
			sql1 = sql1 + " 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"
					+ fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "' ";
			sql1 = sql1
					+ " and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head where tr_type in ('TFR') ";
			sql1 = sql1 + " and sus_no='" + nParaS[4] + "') ";
			sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl" + fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt,";
			sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt,";
			sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt,";
			sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt,";
			sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,0 as fwd_amt,0 as bkd_amt,";
			sql1 = sql1 + " tr_type from fp.fp_tb_trans_detl" + fileYr
					+ " where fr_sus_no in  ( select distinct t.chl_sus_no from ( ";
			sql1 = sql1
					+ " WITH RECURSIVE nRecPrefUnit AS (  select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4] + "' ";
			sql1 = sql1 + " union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2];
			if (fmncnd.length() > 0) {
				sql1 = sql1 + " and " + fmncnd;
			}
			sql1 = sql1 + ") ";
			sql1 = sql1 + " t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "') and tr_type in ('EXP','GST','EDT','OTH')";
			sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='" + nParaS[4]
					+ "')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select '" + nParaS[4]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to, 0 as alt_amt,";
			sql1 = sql1
					+ " 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,a.bkd_amt as bkd_amt,";
			sql1 = sql1 + " 'CDA' as tr_type  from fp.fp_tb_cda_detl" + fileYr + " a where a.sus_no in  ( ";
			sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (  ";
			sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4]
					+ "' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no   ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2];
			if (fmncnd.length() > 0) {
				sql1 = sql1 + " and " + fmncnd;
			}
			sql1 = sql1 + ") ";
			sql1 = sql1 + " t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by a.tr_head_to";
			} else {
				sql1 = sql1 + " group by to_sus_no";
			}
			sql1 = sql1 + " union all ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '2' as tlvl,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '2' as tlvl,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " trunc((sum(a.alt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as altamt,";
			sql1 = sql1 + " trunc((sum(a.exp_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as expamt,";
			sql1 = sql1 + " trunc((sum(a.edt_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as edtamt,";
			sql1 = sql1 + " trunc((sum(a.gst_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as gstamt,";
			sql1 = sql1 + " trunc((sum(a.oth_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as othamt,";
			sql1 = sql1 + " trunc((sum(a.fwd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as fwdamt,";
			sql1 = sql1 + " trunc((sum(a.bkd_amt)::numeric/" + rsfmtv + ")," + rsfmtvd + ") as bkdamt";
			sql1 = sql1 + " from (";
			sql1 = sql1
					+ " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,0 as gst_amt,";
			sql1 = sql1 + " 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"
					+ fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "' ";
			sql1 = sql1
					+ " and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head where tr_type in ('TFR') ";
			sql1 = sql1 + " and sus_no='" + nParaS[4] + "') ";
			sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl" + fileYr;
			sql1 = sql1 + " where fr_sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt,";
			sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt,";
			sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt,";
			sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt,";
			sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,0 as fwd_amt,0 as bkd_amt,";
			sql1 = sql1 + " tr_type from fp.fp_tb_trans_detl" + fileYr
					+ " where fr_sus_no in  ( select distinct t.chl_sus_no from ( ";
			sql1 = sql1
					+ " WITH RECURSIVE nRecPrefUnit AS (  select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4] + "' ";
			sql1 = sql1 + " union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2];
			if (fmncnd.length() > 0) {
				sql1 = sql1 + " and " + fmncnd;
			}
			sql1 = sql1 + ") ";
			sql1 = sql1 + " t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "') and tr_type in ('EXP','GST','EDT','OTH')";
			sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='" + nParaS[4]
					+ "')";
			sql1 = sql1 + " union all ";
			sql1 = sql1 + " select '" + nParaS[4]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to, 0 as alt_amt,";
			sql1 = sql1
					+ " 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,a.bkd_amt as bkd_amt,";
			sql1 = sql1 + " 'CDA' as tr_type  from fp.fp_tb_cda_detl" + fileYr + " a where a.sus_no in  ( ";
			sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (  ";
			sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p  ";
			sql1 = sql1 + " where p.sus_no='" + nParaS[4]
					+ "' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n ";
			sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  ) ";
			sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
			sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no   ";
			sql1 = sql1 + " where q.hq_type::integer>=" + nParaS[2];
			if (fmncnd.length() > 0) {
				sql1 = sql1 + " and " + fmncnd;
			}
			sql1 = sql1 + ") ";
			sql1 = sql1 + " t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
			sql1 = sql1 + " where sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='" + nParaS[4] + "')";
			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by tr_head_to,to_sus_no";
			} else {
				sql1 = sql1 + " group by to_sus_no,tr_head_to";
			}
			sql1 = sql1 + " ) p ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}

			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add("");
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));

					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInfoDBDetl_Sandeep(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");
		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String susdetl = "HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");

		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		String trhead = "4:";

		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}
		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '1' as tlvl,a.tr_head_to as tr_head_to,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '1' as tlvl,a.to_sus_no as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(a.tr_head_to) as tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric, 5,'R2C')  as altamt,";
			sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric, 5,'R2C')  as expamt,";
			sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric, 5,'R2C')  as edtamt,";
			sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric, 5,'R2C')  as gstamt,";
			sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric, 5,'R2C')  as othamt,";
			sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric, 5,'R2C')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric, 5,'R2C')  as bkdamt";
			sql1 = sql1 + " from (";

			sql1 = sql1 + nFundbaseSql(nPara, nUsrId, trhead, susdetl, cfy, sessionA, ":ALT:EXP:FWD:BKD");

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (m1_hdlvl.equalsIgnoreCase("-1")) {
				sql1 = sql1 + " where o.hq_type::numeric <=0 ";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by a.tr_head_to";
			} else {
				sql1 = sql1 + " group by to_sus_no";
			}
			sql1 = sql1 + " union all ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '2' as tlvl,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '2' as tlvl,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric, 5,'R2C')  as altamt,";
			sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric, 5,'R2C')  as expamt,";
			sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric, 5,'R2C')  as edtamt,";
			sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric, 5,'R2C')  as gstamt,";
			sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric, 5,'R2C')  as othamt,";
			sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric, 5,'R2C')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric, 5,'R2C')  as bkdamt";
			sql1 = sql1 + " from (";

			sql1 = sql1 + nFundbaseSql(nPara, nUsrId, trhead, susdetl, cfy, sessionA, ":ALT:EXP:FWD:BKD");

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (m1_hdlvl.equalsIgnoreCase("-1")) {
				sql1 = sql1 + " where o.hq_type::numeric <=0 ";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by tr_head_to,to_sus_no";
			} else {
				sql1 = sql1 + " group by to_sus_no,tr_head_to";
			}
			sql1 = sql1 + " ) p ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}

			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add("");
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));

					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> nFundInfoDBDetl_old(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {

		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");

		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " (case when (fr_sus_no is not null and to_sus_no is null and tr_head_to is null) then '0' else (case when (fr_sus_no is not null and to_sus_no is not null and tr_head_to is null) then '1' else '2' end) end) as tlvl,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " (case when (fr_sus_no is not null and to_sus_no is null and tr_head_to is null) then '0' else (case when (fr_sus_no is not null and to_sus_no is not null and tr_head_to is null) then '1' else '2' end) end) as tlvl,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
					":FND:ALT:EXP:CDA");

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by rollup(tr_head_to,fr_sus_no,to_sus_no)";
			} else {
				sql1 = sql1 + " group by rollup(fr_sus_no,to_sus_no,tr_head_to)";
			}
			sql1 = sql1 + " ) p ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.fr_sus_no,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.fr_sus_no,p.to_sus_no,tlvl,p.tr_head_to";
			}
			//System.out.println("nFundInfoDBDetl-"+sql1);
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add("");
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	public ArrayList<ArrayList<String>> nFundInfoDBDetl(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		//System.out.println("nFundInfoDBDetl-para-"+nPara+","+nUsrId+","+rolesus+","+cfy);
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");

		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		String role=sessionA.getAttribute("role").toString();

		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		//System.out.println("m1lvl-"+m1lvl);
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.fndamt,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.fndamt,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '1' as tlvl,a.tr_head_to as tr_head_to,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '1' as tlvl,a.to_sus_no as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(a.tr_head_to) as tr_head_to,max(h.head_desc) as head_desc,";
			}
			if (role.equalsIgnoreCase("FP_VIEW")) {			
				sql1 = sql1 + " nconvertinr(coalesce(sum(a.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as altamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
			} else {
				sql1 = sql1 + " nconvertinr(coalesce(sum(a.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as fndamt,";
				sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
						+ "')  as altamt,";
			}
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
					":FND:ALT:EXP:CDA");

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by a.tr_head_to";
			} else {
				sql1 = sql1 + " group by to_sus_no";

			}
			sql1 = sql1 + " union all ";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '2' as tlvl,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '2' as tlvl,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}
			
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fnd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fndamt,";			
			
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";   

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
					":FND:ALT:EXP:CDA");

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by tr_head_to,to_sus_no";
			} else {
				sql1 = sql1 + " group by to_sus_no,tr_head_to";
			}
			sql1 = sql1 + " ) p ";
			/// for exp of own est
			/*if (!role.equalsIgnoreCase("FP_VIEW")) {
				sql1 = sql1 + " where (p.to_sus_no,p.tr_head_to) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
				sql1 = sql1 + " where psus_no='"+m1lvl+"' ) ";
			}*/
			/// for exp
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}
			//System.out.println("nFundInfoDBDetl-"+sql1);
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));					
					list2.add(rs.getString("fndamt"));
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_n(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		//System.out.println("nFundInfoDBDetl_n-para-"+nPara+","+nUsrId+","+rolesus+","+cfy);
		
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");
		
		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");			
		String fileYr = m1_tryear.substring(2, 4);
		//System.out.println("nFundInfoDBDetl_n-CFY-"+m1_tryear+","+fileYr);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		String role=sessionA.getAttribute("role").toString();
		String rolen=sessionA.getAttribute("roleSusNo").toString();
		
		
		String orolesus[] = rolesus.split("_");
		String entsus=orolesus[2];
		if (orolesus[2].equalsIgnoreCase("ALL")) {
			orolesus[2]=rolen;
		}
		//System.out.println("nFundInfoDBDetl_n-orolesus-"+orolesus);
				
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + orolesus[2]);
		//System.out.println("----"+nUnt+","+nUnt.get(0).get(4));
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		//System.out.println("nFundInfoDBDetl_n-nPara-"+nPara+","+m1_lvl);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];  //sus
		String m1lvlq = nParaS[3]; // fmn
		String m1lvl1 = nParaS[2]; //hqlvl
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		String hedcnd = "";
		//System.out.println("m1lvl-"+m1lvl);
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		
		
		
		try {
			conn = dataSource.getConnection();
			//if (entsus.equalsIgnoreCase("ALL")) {
				if (m1_hdlvl.equalsIgnoreCase("0")) {
					hdCnd = "split_part(tr_head_to,':',1) ";
					hdCnd1 = "split_part(tr_head,':',1) ";
					//fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
					fmncnd = " a.sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
					hedcnd = " n.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
				}
				if (m1_hdlvl.equalsIgnoreCase("1")) {
					hdCnd = "split_part(tr_head_to,':',1) ";
					hdCnd1 = "split_part(tr_head,':',1) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("2")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 3) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("3")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 6) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("4")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 10) + "%'";
					hedcnd = "";
				}
				
				if(nUnt.get(0).get(4).equalsIgnoreCase("Y")){	
					//sql1 = sql1 + " where n1.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
					hedcnd = " n.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
				}
				
			//} else {
				//fmncnd=" a.sus_no='"+m1lvl+"'";
			//	fmncnd=" a.form_code_control in (select distinct form_code_control from fp.fp_tv_orbat_dtl where sus_no='"+m1lvl+"')";
			//	hedcnd = "";
			//}
/*			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.fndamt,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.fndamt,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
*/			
			/*sql1 = "";			
			sql1 = sql1 + " select (case when to_sus_no is null and fr_sus_no is not null then 'T1' else (case when n1.tr_head is null then 'T2' else 'T3' end) end) as tlvl,";
			sql1 = sql1 + " o3.hq_type,o1.form_code_control,n1.base,o3.unit_name as hq_fmn,n1.fr_sus_no as fr_sus_no,o2.unit_name as fr_sus_name,n1.to_sus_no,o1.unit_name as to_sus_name,n1.tr_head,split_part(n1.tr_head,':',4) as head_code,h1.head_desc,h1.mnh_desc as minor_head,h1.sbh_desc as sub_head,n1.altamt,n1.alt_dt,n1.expamt,exp_dt from (";
			sql1 = sql1 + " select base,fr_sus_no,to_sus_no,tr_head,sum(altamt) as altamt,max(alt_dt) as alt_dt, sum(expamt) as expamt,max(exp_dt) as exp_dt from (";
			sql1 = sql1 + " select d.base,a.fr_sus_no,to_sus_no,tr_head,altamt,alt_dt,expamt,exp_dt from (select a.form_code_control,a.hq_type,a.psus_no as base,a.sus_no,a.unit_name,b.chl_head_code,b.psus_no from fp.fp_tv_orbat_dtl a";
			sql1 = sql1 + " left join fp.fp_tb_pref_head b on a.sus_no=b.sus_no";
			
			if (fmncnd.length()>0) {
				sql1 = sql1 + " where "+fmncnd;  //a.form_code_control like '311%'";
			}
			
			sql1 = sql1 + " ) d left join (select a.fr_sus_no,a.to_sus_no,a.tr_head_to as tr_head,fndamt as altamt,to_char(a.fnd_date,'dd-mm-yyyy') as alt_dt,0 as expamt,'01-04-2021' as exp_dt from fp.fp_tv_fund_detl21 a ) a ";
			sql1 = sql1 + " on a.fr_sus_no=d.psus_no and a.to_sus_no=d.sus_no and a.tr_head=d.chl_head_code	where a.fr_sus_no<>a.to_sus_no"; 
			sql1 = sql1 + " union all ";				
			sql1 = sql1 + " select max(a1.psus_no) as base,max(b.psus_no) as fr_sus_no,to_sus_no,tr_head_to as tr_head,0 as altamt,";
			sql1 = sql1 + " '01-04-2021' as alt_dt,sum(exp_amt) as exp_amt,max(period)::text as exp_dt from fp.fp_tb_trans_detl21 a ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl a1 on a.to_sus_no=a1.sus_no left join (select max(psus_no) as psus_no,sus_no,chl_head_code ";
			sql1 = sql1 + " from fp.fp_tb_pref_head group by sus_no,chl_head_code) b on a.to_sus_no=b.sus_no and a.tr_head_to=b.chl_head_code";
			sql1 = sql1 + " where to_sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a ";
			
			//sql1 = sql1 + " where form_code_control like '311%'";
			if (fmncnd.length()>0) {
				sql1 = sql1 + " where "+fmncnd;  //a.form_code_control like '311%'";
			}
			sql1 = sql1 + " ) group by to_sus_no,tr_head_to	) n group by rollup(base,fr_sus_no,to_sus_no,tr_head) ) n1";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o1 on n1.to_sus_no=o1.sus_no";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o2 on n1.fr_sus_no=o2.sus_no";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o3 on n1.base=o3.sus_no";
			sql1 = sql1 + " left join fp.fp_tv_head_dtl h1 on n1.tr_head=h1.tr_head";
			sql1 = sql1 + " order by base,fr_sus_no,to_sus_no,tr_head";*/
			
			sql1 = ""; 
			sql1 = sql1 + " select (case when (to_sus_no is null and fr_sus_no is null and base is null) then 'T0' ";
			sql1 = sql1 + " when (to_sus_no is null and fr_sus_no is not null) then 'T1' ";
			sql1 = sql1 + " when (to_sus_no is not null and n1.tr_head is null) then 'T2' else 'T3' end) as tlvl,";
			sql1 = sql1 + " o3.hq_type,o1.form_code_control,n1.base,o3.unit_name as hq_fmn,n1.fr_sus_no as fr_sus_no,";
			sql1 = sql1 + " o2.unit_name as fr_sus_name,n1.to_sus_no,o1.unit_name as to_sus_name,n1.tr_head,";
			sql1 = sql1 + " split_part(n1.tr_head,':',4) as head_code,h1.head_desc,h1.mnh_desc as minor_head,";
			sql1 = sql1 + " h1.sbh_desc as sub_head,n1.altamt,n1.alt_dt,n1.expamt,n1.exp_dt,n1.fwdamt,n1.fwd_dt,n1.bkdamt,n1.bkd_dt,ngetper(n1.altamt::numeric,n1.expamt::numeric) as alt_exp from (";					
			sql1 = sql1 + " select base,fr_sus_no,to_sus_no,tr_head,sum(altamt) as altamt,max(alt_dt) as alt_dt, sum(expamt) as expamt,";
			sql1 = sql1 + " max(exp_dt) as exp_dt,sum(fwdamt) as fwdamt,max(fwd_dt) as fwd_dt,sum(bkdamt) as bkdamt,max(bkd_dt) as bkd_dt from ("; 						
			sql1 = sql1 + " select d.base,a.fr_sus_no,to_sus_no,tr_head,altamt,alt_dt,expamt,exp_dt,fwdamt,fwd_dt,bkdamt,bkd_dt from (";
			sql1 = sql1 + " select a.form_code_control,a.hq_type,a.psus_no as base,a.sus_no,a.unit_name,b.chl_head_code,b.psus_no ";
			sql1 = sql1 + " from fp.fp_tv_orbat_dtl a left join fp.fp_tb_pref_head b on a.sus_no=b.sus_no ";
			
			if (fmncnd.length()>0) {
				sql1 = sql1 + " where "+fmncnd;  //a.form_code_control like '311%'";
			}
			
			sql1 = sql1 + " ) d left join (select a.fr_sus_no,a.to_sus_no,a.tr_head_to as tr_head,fndamt as altamt,";
			sql1 = sql1 + " to_char(a.fnd_date,'yyyy-mm-dd') as alt_dt,0 as expamt,'2021-04-01' as exp_dt,0 as fwdamt,'2021-04-01' as fwd_dt,";
			sql1 = sql1 + " 0 as bkdamt,'2021-04-01' as bkd_dt from fp.fp_tv_fund_detl"+fileYr+" a ) a  on a.fr_sus_no=d.psus_no and a.to_sus_no=d.sus_no";
			sql1 = sql1 + " and a.tr_head=d.chl_head_code	where a.fr_sus_no<>a.to_sus_no";
			sql1 = sql1 + " union all ";  						
			sql1 = sql1 + " select max(a1.psus_no) as base,max(b.psus_no) as fr_sus_no,to_sus_no,tr_head_to as tr_head,0 as altamt, ";
			sql1 = sql1 + " '2021-04-01' as alt_dt,sum(exp_amt) as exp_amt,to_char(max(period),'YYYY-mm-dd') as exp_dt,0 as fwdamt,";
			sql1 = sql1 + " '2021-04-01' as fwd_dt,0 as bkdamt,'2021-04-01' as bkd_dt from fp.fp_tb_trans_detl"+fileYr+" a  ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl a1 on a.to_sus_no=a1.sus_no left join (select max(psus_no) as psus_no,sus_no,chl_head_code ";
			sql1 = sql1 + " from fp.fp_tb_pref_head group by sus_no,chl_head_code) b on a.to_sus_no=b.sus_no and a.tr_head_to=b.chl_head_code ";
			sql1 = sql1 + " where to_sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a  ";
			
			if (fmncnd.length()>0) {
				sql1 = sql1 + " where "+fmncnd;  //a.form_code_control like '311%'";
			}
			
			sql1 = sql1 + " ) group by to_sus_no,tr_head_to";	
			sql1 = sql1 + " union all ";  						
			sql1 = sql1 + " select max(a1.psus_no) as base,max(b.psus_no) as fr_sus_no,a.sus_no as to_sus_no,a.tr_head,0 as altamt,";
			sql1 = sql1 + " '2021-04-01' as alt_dt,0 as exp_amt,'2021-04-01' as exp_dt,sum(fwd_amt) as fwdamt,";
			sql1 = sql1 + " to_char(max(fwd_date),'YYYY-mm-dd') as fwd_dt,sum(bkd_amt) as bkdamt,to_char(max(bkd_date),'YYYY-mm-dd') as bkd_dt ";
			sql1 = sql1 + " from fp.fp_tb_cda_detl"+fileYr+" a  left join fp.fp_tv_orbat_dtl a1 on a.sus_no=a1.sus_no ";
			sql1 = sql1 + " left join (select max(psus_no) as psus_no,sus_no,chl_head_code  from fp.fp_tb_pref_head group by sus_no,chl_head_code) b ";
			sql1 = sql1 + " on a.sus_no=b.sus_no and a.tr_head=b.chl_head_code where a.sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a  ";
			
			if (fmncnd.length()>0) {
				sql1 = sql1 + " where "+fmncnd;  //a.form_code_control like '311%'";
			}
			
			sql1 = sql1 + " ) group by a.sus_no,a.tr_head ) n ";
			
			if (hedcnd.length()>0) {				
				sql1 = sql1 + " where "+hedcnd;
			}
			
			sql1 = sql1 + " group by rollup(base,fr_sus_no,to_sus_no,tr_head)";
			sql1 = sql1 + " ) n1 left join fp.fp_tv_orbat_dtl o1 on n1.to_sus_no=o1.sus_no left join fp.fp_tv_orbat_dtl o2 on n1.fr_sus_no=o2.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o3 on n1.base=o3.sus_no left join fp.fp_tv_head_dtl h1 on n1.tr_head=h1.tr_head ";
			
			sql1 = sql1 + " order by base,fr_sus_no,to_sus_no,tr_head";

			//System.out.println("nFundInfoDBDetl_n-"+sql1);
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tlvl"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("form_code_control"));
					list2.add(rs.getString("base"));
					list2.add(rs.getString("hq_fmn"));
					list2.add(rs.getString("fr_sus_no"));
					list2.add(rs.getString("fr_sus_name"));
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("to_sus_name"));
					list2.add(rs.getString("tr_head"));
					list2.add(rs.getString("altamt")); //10
					list2.add(rs.getString("alt_dt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("exp_dt"));					
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("minor_head"));
					list2.add(rs.getString("sub_head")); //17
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("fwd_dt"));
					list2.add(rs.getString("bkdamt"));
					list2.add(rs.getString("bkd_dt"));
					list2.add(rs.getString("alt_exp"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_n1(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		//System.out.println("nFundInfoDBDetl_n1-para-"+nPara+","+nUsrId+","+rolesus+","+cfy);
		
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");
		
		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");			
		String fileYr = m1_tryear.substring(2, 4);
		//System.out.println("nFundInfoDBDetl_n1-CFY-"+m1_tryear+","+fileYr);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";
		String role=sessionA.getAttribute("role").toString();
		String rolen=sessionA.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> nRUnt = new ArrayList<ArrayList<String>>();
		nRUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolen);
		String rolent=nRUnt.get(0).get(4);
		//System.out.println("rolent-"+rolent);
		
		String orolesus[] = rolesus.split("_");
		String entsus=orolesus[2];
		if (orolesus[2].equalsIgnoreCase("ALL")) {
			orolesus[2]=rolen;
		}
		////System.out.println("nFundInfoDBDetl_n1-orolesus-"+orolesus);
				
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + orolesus[2]);
		////System.out.println("----"+nUnt+","+nUnt.get(0).get(4));
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		////System.out.println("nFundInfoDBDetl_n1-nPara-"+nPara+","+m1_lvl);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];  //sus
		String m1lvlq = nParaS[3]; // fmn
		String m1lvl1 = nParaS[2]; //hqlvl
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		String fmncnd1="";
		String hedcnd = "";
		//System.out.println("m1lvl-"+m1lvl);
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		
		
		
		try {
			conn = dataSource.getConnection();
			//if (entsus.equalsIgnoreCase("ALL")) {
				if (m1_hdlvl.equalsIgnoreCase("0")) {
					hdCnd = "split_part(tr_head_to,':',1) ";
					hdCnd1 = "split_part(tr_head,':',1) ";
					//fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
					fmncnd = " a.sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
					hedcnd = " n.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
				}
				if (m1_hdlvl.equalsIgnoreCase("1")) {
					hdCnd = "split_part(tr_head_to,':',1) ";
					hdCnd1 = "split_part(tr_head,':',1) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("2")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 3) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("3")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 6) + "%'";
					hedcnd = "";
				}
				if (m1_hdlvl.equalsIgnoreCase("4")) {
					hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
					hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
					fmncnd = "a.form_code_control like '" + nUnt.get(0).get(0).substring(0, 10) + "%'";
					hedcnd = "";
				}
				
				if(nUnt.get(0).get(4).equalsIgnoreCase("Y")){	
					//sql1 = sql1 + " where n1.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
					hedcnd = " n.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+m1lvl+"')";
				}
				

			/* new approach */
			
			sql1 = ""; 
			
			sql1 = sql1 + " select distinct n1.fmn_hq,n1.form_code_control,n1.psus_no,n1.psus_name,n1.sus_no,n1.sus_name,";
			sql1 = sql1 + " n1.chl_head_code,n1.mnh_desc,n1.head_desc,";
			//sql1 = sql1 + " n1.fndamt,n1.altamt,";
			sql1 = sql1 + " n1.amt,n1.exp_amt,n1.fwd_amt,n1.bkd_amt,";
			sql1 = sql1 + " n1.head_code,n1.cbm_gp,n1.cbm_detl,n1.cbm_gpl,n1.pathid,";
			sql1 = sql1 + " n1.lvl1,n1.lvl2,n1.lvl3,n1.lvl4,n1.lvl5,n1.lvl6,n1.lvl7,n1.lvl8,n1.lvl9,";
			sql1 = sql1 + " ngetper(n1.amt::numeric,n1.exp_amt::numeric) as alt_exp from (";
			sql1 = sql1 + " select hq.unit_name as fmn_hq,ss.form_code_control,n.psus_no,sf.unit_name as psus_name,n.sus_no,ss.unit_name as sus_name,n.chl_head_code,hd.mnh_desc,hd.head_desc,";			
			//sql1 = sql1 + " (case when n.sus_no='"+m1lvl+"' then b.fndamt else 0 end) as fndamt, (case when n.psus_no='"+m1lvl+"' then b.fndamt else 0 end) as altamt,";
			sql1 = sql1 + " b.fndamt as amt,";
			//sql1 = sql1 + " (case when n.psus_no='"+m1lvl+"' and b.fndamt>0 then (select sum(exp_amt) as exp_amt from fp.fp_tb_trans_detl"+fileYr+" where (tr_head_to,to_sus_no) in (";
			sql1 = sql1 + " (case when hq.sus_no<>n.sus_no and b.fndamt>0 then (select sum(exp_amt) as exp_amt from fp.fp_tb_trans_detl"+fileYr+" where (tr_head_to,to_sus_no) in (";
			sql1 = sql1 + " select chl_head_code,sus_no from fp.fp_tv_pref_head where npath like concat(n.pathid,'%') and chl_head_code=n.chl_head_code)) else 0 end) as exp_amt,";
			//sql1 = sql1 + " (case when n.psus_no='"+m1lvl+"' and b.fndamt>0 then (select sum(fwd_amt) as fwd_amt from fp.fp_tb_cda_detl"+fileYr+" where (tr_head,sus_no) in (";
			sql1 = sql1 + " (case when hq.sus_no<>n.sus_no and b.fndamt>0 then (select sum(fwd_amt) as fwd_amt from fp.fp_tb_cda_detl"+fileYr+" where (tr_head,sus_no) in (";
			sql1 = sql1 + " select chl_head_code,sus_no from fp.fp_tv_pref_head where npath like concat(n.pathid,'%') and chl_head_code=n.chl_head_code))  else 0 end) as fwd_amt,";
			//sql1 = sql1 + " (case when n.psus_no='"+m1lvl+"' and b.fndamt>0 then (select sum(bkd_amt) as bkd_amt from fp.fp_tb_cda_detl"+fileYr+" where (tr_head,sus_no) in (";
			sql1 = sql1 + " (case when hq.sus_no<>n.sus_no and b.fndamt>0 then (select sum(bkd_amt) as bkd_amt from fp.fp_tb_cda_detl"+fileYr+" where (tr_head,sus_no) in (";
			sql1 = sql1 + " select chl_head_code,sus_no from fp.fp_tv_pref_head where npath like concat(n.pathid,'%') and chl_head_code=n.chl_head_code))  else 0 end) as bkd_amt,";
			sql1 = sql1 + " split_part(n.chl_head_code,':',4) as head_code,cb.cbm_gp,cb.cbm_detl,cb.sub_head3 as cbm_gpl,n.pathid, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',1) is not null  then s1.unit_name else '' end) as lvl1, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',2) is not null  then s2.unit_name else '' end) as lvl2, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',3) is not null  then s3.unit_name else '' end) as lvl3, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',4) is not null  then s4.unit_name else '' end) as lvl4, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',5) is not null  then s5.unit_name else '' end) as lvl5, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',6) is not null  then s6.unit_name else '' end) as lvl6, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',7) is not null  then s7.unit_name else '' end) as lvl7, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',8) is not null  then s8.unit_name else '' end) as lvl8, ";
			sql1 = sql1 + " (case when split_part(pathid,'_',9) is not null  then s9.unit_name else '' end) as lvl9 ";
			//sql1 = sql1 + " 0 as alt_exp";
			sql1 = sql1 + " from (";
			sql1 = sql1 + " select distinct psus_no,sus_no,chl_head_code,replace(npath,'A000001_A000001','A000001') as pathid,";
			sql1 = sql1 + " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tv_pref_head ";
			sql1 = sql1 + " where (psus_no,sus_no,chl_head_code,lvl) in ( select distinct psus_no,sus_no,chl_head_code,max(lvl) as lvl ";
			sql1 = sql1 + " from fp.fp_tv_pref_head where  ";
			//System.out.println("-rolesusrolesus-"+rolesus+","+m1lvl+","+entsus+","+role);
			if (entsus.toUpperCase().indexOf("ALL")>=0) {				
				sql1 = sql1 + "sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a where "+fmncnd+")";
			} else {	
				if (role.toUpperCase().contains("VIEW") && orolesus[3].equalsIgnoreCase("N")) {
					sql1 = sql1 + " psus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl where psus_no='"+m1lvl+"') ";
				} 				
				if (role.toUpperCase().contains("VIEW") && orolesus[3].equalsIgnoreCase("N")) {
					sql1 = sql1 + " or ";
				}
				sql1 = sql1 + " (psus_no='"+m1lvl+"' or sus_no='"+m1lvl+"')";					
				if (rolent.equalsIgnoreCase("Y")) {
					////System.out.println("Branch Found");
					sql1 = sql1 + " and chl_head_code in (select distinct chl_head_code from fp.fp_tb_pref_head where psus_no='"+rolen+"')";
				}
			}
			
			//sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='D415002W') 
			sql1 = sql1 + " group by psus_no,sus_no,chl_head_code) and split_part(npath,'_',1)='A000001'"; 
			sql1 = sql1 + " ) n"; 
			if (role.toUpperCase().contains("VIEW")) {
				sql1 = sql1 + " left ";
			} else {
				sql1 = sql1 + " inner ";
			}
			sql1 = sql1 + " join fp.fp_tv_fund_detl"+fileYr+" b on n.psus_no=b.fr_sus_no and n.sus_no=b.to_sus_no and n.chl_head_code=b.tr_head_to";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s1 on split_part(pathid,'_',1)=s1.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s2 on split_part(pathid,'_',2)=s2.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s3 on split_part(pathid,'_',3)=s3.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s4 on split_part(pathid,'_',4)=s4.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s5 on split_part(pathid,'_',5)=s5.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s6 on split_part(pathid,'_',6)=s6.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s7 on split_part(pathid,'_',7)=s7.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s8 on split_part(pathid,'_',8)=s8.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl s9 on split_part(pathid,'_',9)=s9.sus_no "; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl sf on n.psus_no=sf.sus_no "; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl ss on n.sus_no=ss.sus_no ";
			sql1 = sql1 + " left join fp.fp_tv_head_dtl hd on n.chl_head_code=hd.tr_head ";
			sql1 = sql1 + " left join nrv_hq hq on ss.form_code_control=hq.form_code_control ";
			sql1 = sql1 + " left join fp.cbm_detl cb on n.chl_head_code=cb.tr_head";
			sql1 = sql1 + " ) n1";
			sql1 = sql1 + " order by n1.chl_head_code,pathid ";
			//order by pathid,n.chl_head_code
			
			
			
			//System.out.println("nFundInfoDBDetl_n1-"+sql1);
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					//list2.add(rs.getString("tlvl"));
					list2.add("T3");
					list2.add(rs.getString("fmn_hq"));
					list2.add(rs.getString("form_code_control"));
					list2.add(rs.getString("psus_no"));
					list2.add(rs.getString("psus_name"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("sus_name"));
					list2.add(rs.getString("chl_head_code"));
					list2.add(rs.getString("mnh_desc"));
					list2.add(rs.getString("head_desc"));
					/*list2.add(rs.getString("fndamt"));					
					list2.add(rs.getString("altamt")); //10
*/					list2.add(""); //10
					list2.add(rs.getString("amt")); //10
					list2.add(rs.getString("exp_amt"));
					list2.add(rs.getString("head_code"));
					list2.add(rs.getString("fwd_amt"));
					list2.add(rs.getString("bkd_amt"));					
					list2.add(rs.getString("pathid"));
					list2.add(rs.getString("lvl1"));
					list2.add(rs.getString("lvl2"));
					list2.add(rs.getString("lvl3"));
					list2.add(rs.getString("lvl4"));
					list2.add(rs.getString("lvl5"));
					list2.add(rs.getString("lvl6"));
					list2.add(rs.getString("lvl7"));
					list2.add(rs.getString("lvl8"));
					list2.add(rs.getString("lvl9"));
					list2.add(rs.getString("alt_exp"));
					list2.add(rs.getString("cbm_gp"));
					list2.add(rs.getString("cbm_detl"));
					list2.add(rs.getString("cbm_gpl"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		////System.out.println("li-"+li);
		return li;
	}

	
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_notok_30072021(String nPara, String nUsrId, String rolesus, String cfy,
			HttpSession sessionA) {
		//System.out.println("nFundInfoDBDetl-para-"+nPara+","+nUsrId+","+rolesus+","+cfy);
		String oPara = nPara;
		String oParaS[] = oPara.replace("ln", "").split("_");

		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		//System.out.println("m1lvl-"+m1lvl);
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 += " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {			
				sql1 += " select tlvl,p.fr_sus_no,p.psus_no,p.to_sus_no,p.hq_type,p.unit_name, p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}

			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 += " select (case when a.to_sus_no is null then '1' else '2' end) as tlvl,max(a.fr_sus_no) as fr_sus_no,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 += " select (case when a.tr_head_to is null then '1' else '2' end) as tlvl,max(a.fr_sus_no) as fr_sus_no, a.psus_no as psus_no,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,"; 
			}		
			
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";
				
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 += " select tr,max(fr_sus_no) as fr_sus_no,max(o.psus_no) as psus_no,tr_head,max(to_sus_no) as to_sus_no,tr_head_to,sum(prv_amt) as prv_amt,sum(prj_amt) as prj_amt, sum(fnd_amt) as fnd_amt, sum(alt_amt) as alt_amt, sum(exp_amt) as exp_amt, sum(gst_amt) as gst_amt, sum(edt_amt) as edt_amt, sum(oth_amt) as oth_amt, sum(fwd_amt) as fwd_amt, sum(bkd_amt) as bkd_amt,max(tr_type) as tr_type, max(period) as period from (";
			} else {
				sql1 += " select tr,max(fr_sus_no) as fr_sus_no,max(o.psus_no) as psus_no,tr_head,max(to_sus_no) as to_sus_no,tr_head_to,sum(prv_amt) as prv_amt,sum(prj_amt) as prj_amt, sum(fnd_amt) as fnd_amt, sum(alt_amt) as alt_amt, sum(exp_amt) as exp_amt, sum(gst_amt) as gst_amt, sum(edt_amt) as edt_amt, sum(oth_amt) as oth_amt, sum(fwd_amt) as fwd_amt, sum(bkd_amt) as bkd_amt,max(tr_type) as tr_type, max(period) as period from (";
			}
			
			
			
			
/*			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 += " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 += " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
						
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select (case when a.to_sus_no is null then '1' else '2' end) as tlvl,max(a.fr_sus_no) as fr_sus_no,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select (case when a.tr_head_to is null then '1' else '2' end) as tlvl,max(a.fr_sus_no) as fr_sus_no, a.psus_no,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}			
			
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";

			sql1 +=" select tr,max(fr_sus_no) as fr_sus_no,max(o.psus_no) as psus_no,tr_head,max(to_sus_no) as to_sus_no,tr_head_to,";
			sql1 +=" sum(prv_amt) as prv_amt,sum(prj_amt) as prj_amt, sum(fnd_amt) as fnd_amt, sum(alt_amt) as alt_amt, sum(exp_amt) as exp_amt,";
			sql1 +=" sum(gst_amt) as gst_amt, sum(edt_amt) as edt_amt, sum(oth_amt) as oth_amt, sum(fwd_amt) as fwd_amt, sum(bkd_amt) as bkd_amt,";
			sql1 +=" max(tr_type) as tr_type, max(period) as period from (";
*/			
			
			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA,
					":FND:ALT:EXP:CDA");

			
			
			sql1 += " ) q "; 
			sql1 += " left join fp.fp_tv_orbat_dtl o on q.to_sus_no=o.sus_no";
			
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 += " group by tr,fr_sus_no,tr_head,to_sus_no,tr_head_to";
			} else {
				sql1 += " group by tr,fr_sus_no,tr_head,to_sus_no,tr_head_to";
			}				
				
			sql1 += " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (oParaS[1].equalsIgnoreCase("BHD")) {			
				sql1 += " group by a.psus_no,rollup(a.tr_head_to)";
			} else {
				sql1 += " group by a.psus_no,rollup(a.tr_head_to)";
			}

			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 +=" ) p  order by p.hq_type,p.psus_no,tlvl,p.tr_head_to";
			} else {
				sql1 +=" ) p  where (p.psus_no) in (select distinct psus_no from fp.fp_tv_orbat_dtl where sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='A000001'))";
				sql1 +=" order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}




			
			
			
			
			
/*			
			sql1 = sql1 + " ) q "; 
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on q.to_sus_no=o.sus_no";
			sql1 = sql1 + " group by tr,fr_sus_no,tr_head,to_sus_no,tr_head_to";
			
			
			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			
			
			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head left join fp.fp_tv_orbat_dtl o on a.psus_no=o.sus_no"; 
			
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by a.tr_head_to,rollup(a.psus_no)";
			} else {
				sql1 = sql1 + " group by a.psus_no,rollup(a.tr_head_to)";
			}
			sql1 = sql1 + " ) p ";
			/// for exp of own est
			//sql1 = sql1 + " where (p.to_sus_no,p.tr_head_to) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
			//sql1 = sql1 + " where psus_no='"+m1lvl+"' ) ";
			/// for exp
			
			if (oParaS[1].equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}
*/			
			//System.out.println("nFundInfoDBDetl-"+sql1);
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("BLANK");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add("");
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));
					list2.add(rs.getString("psus_no"));
					li.add(list2);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	
	
	public String nFundbaseSql(String nPara, String nUsrId, String trhead, String susdetl, String cfy,
			HttpSession sessionA, String colList) {
		//System.out.println("nFundbaseSql-Para-"+nPara+","+nUsrId+","+trhead+","+susdetl+","+cfy+","+colList);
		String nSql = "";
		String hdCnd = "";
		String hdCndv = "";
		String hdCndvb = "";
		String hdCndvb1 = "";
		String hdCndvb2 = "";
		String hdCnd1 = "";
		String fmncnd = "";
		int unCnt = 0;
		String role=sessionA.getAttribute("role").toString();
		String fileYr = cfy.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(cfy.substring(2, 4)) - 1) + "";
		String m1_lvlh[] = susdetl.split("_");

		String m1_hdlvl[] = trhead.split(":");
		if (m1_hdlvl.length == 1) {
			hdCndv = "";
		}
		if (m1_hdlvl.length == 2) {
			hdCndv = m1_hdlvl[1];
			hdCndvb = "split_part(chl_head_code,':',1) ";
			hdCndvb1 = "split_part(tr_head,':',1) ";
			hdCndvb2 = "split_part(tr_head_to,':',1) ";
		}
		if (m1_hdlvl.length == 3) {
			hdCndv = m1_hdlvl[1] + ":" + m1_hdlvl[2];
			hdCndvb = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2)) ";
			hdCndvb1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			hdCndvb2 = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
		}
		if (m1_hdlvl.length == 4) {
			hdCndv = m1_hdlvl[1] + ":" + m1_hdlvl[2] + ":" + m1_hdlvl[3];
			hdCndvb = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2),':',split_part(chl_head_code,':',3)) ";
			hdCndvb1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			hdCndvb2 = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
		}
		if (m1_hdlvl.length == 5) {
			hdCndv = m1_hdlvl[1] + ":" + m1_hdlvl[2] + ":" + m1_hdlvl[3] + ":" + m1_hdlvl[4];
			hdCndvb = "concat(split_part(chl_head_code,':',1),':',split_part(chl_head_code,':',2),':',split_part(chl_head_code,':',3),':',split_part(chl_head_code,':',4)) ";
			hdCndvb1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			hdCndvb2 = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
		}

		if (m1_lvlh[1].equalsIgnoreCase("1")) {
			hdCnd = "split_part(tr_head_to,':',1) ";
			hdCnd1 = "split_part(tr_head,':',1) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 1) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("2")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 3) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("3")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 6) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("4")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 10) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("5")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			fmncnd = "";
		}
		nSql = nSql + " select distinct ";
		if (nPara.indexOf(":LVL") >= 0) {
			nSql = nSql + "'2' as tr,";
		}
		nSql = nSql + " '" + m1_lvlh[3] + "' as fr_sus_no,chl_head_code as tr_head,sus_no as to_sus_no,";
		nSql = nSql + " chl_head_code as tr_head_to,0 as prv_amt,0 as prj_amt, 0 as fnd_amt,0 as alt_amt,0 as exp_amt,";
		nSql = nSql
				+ " 0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'HBH' as tr_type,'01-01-1990'::timestamp as period  from fp.fp_tb_pref_head ";
		nSql = nSql + " where (sus_no,chl_head_code) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
		nSql = nSql + " where psus_no='" + m1_lvlh[3] + "'";
		if (!m1_lvlh[0].equalsIgnoreCase("HQ")) {
			nSql = nSql + "and sus_no='" + m1_lvlh[3] + "'";
		}		
		if (hdCndv.length() > 0) {
			nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
		}
		nSql += " )";
		unCnt = unCnt + 1;
		if (colList.indexOf(":PRV") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,exp_amt as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,data_cr_date as period from fp.fp_tb_funds_detl"
						+ fileYrPrv + " a";
				nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl" + fileYrPrv
						+ " where fr_sus_no='" + m1_lvlh[3] + "' and fr_sus_no<>to_sus_no)";
				nSql += " and a.tr_type='TFR'";
				nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";
				nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
				nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
				}
				nSql += " )";
				unCnt = unCnt + 1;
			} else {
				if (!m1_lvlh[1].equalsIgnoreCase("5")) {
					nSql = nSql + " select ";
					if (nPara.indexOf(":LVL") >= 0) {
						nSql = nSql + "'2' as tr,";
					}
					nSql = nSql
							+ "  a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,exp_amt as prv_amt,0 as prj_amt,";
					nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,data_cr_date as period from fp.fp_tb_funds_detl"
							+ fileYrPrv + " a";
					nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl"
							+ fileYrPrv + " where fr_sus_no='" + m1_lvlh[3] + "'";
					//nSql += " and fr_sus_no<>to_sus_no)";
					nSql += " )";
					nSql += " and a.tr_type='TFR'";

					nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";
					nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
					nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
					if (hdCndv.length() > 0) {
						nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
					}
					nSql += " )";
					unCnt = unCnt + 1;
				}
			}
		}

		if (colList.indexOf(":PRJ") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			nSql = nSql + " '" + m1_lvlh[3]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,proj_amt as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'PRJ' as tr_type,data_upd_date as period from fp.fp_tb_proj_detl"
					+ fileYr + " a";

			if (m1_lvlh[1].equalsIgnoreCase("0")) {

				nSql += " where (a.sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
				nSql += " where psus_no='" + m1_lvlh[3] + "' ";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
				}
				nSql += " )";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where a.sus_no in (select distinct t.sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select distinct p.psus_no,p.sus_no from fp.fp_tb_pref_head p";
					nSql += " where p.psus_no='" + m1_lvlh[3] + "' union select distinct n.psus_no,n.sus_no from fp.fp_tb_pref_head n";
					nSql += " inner join nRecPrefUnit s on s.sus_no=n.psus_no ) select distinct p.psus_no,p.sus_no ";
					nSql += " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					//nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						//nSql = nSql + " and " + fmncnd;
						nSql = nSql + " where " + fmncnd;
					}
					nSql += " ) t) ";
				} else {
					nSql += " where a.sus_no in ('" + m1_lvlh[3] + "')";
				}
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
				}
				nSql += " )";

			}
			unCnt = unCnt + 1;
		}
		if (colList.indexOf(":FND") >= 0) {
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				if (unCnt > 0) {
					nSql += " union all ";
				}
				nSql = nSql + " select distinct ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'1' as tr,";
				}
				nSql = nSql
						+ " fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as prv_amt,0 as prj_amt,recd_amt as fnd_amt,";
				nSql = nSql
						+ " 0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,";
				nSql = nSql + " 'FND' as tr_type,data_cr_date as period from fp.fp_tb_trans_detl" + fileYr
						+ " where tr_type='FND' ";
				nSql = nSql + " and to_sus_no='" + m1_lvlh[3] + "'";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb2 + "='" + hdCndv + "'";
				}

				unCnt = unCnt + 1;
			} else {
				if (unCnt > 0) {
					nSql += " union all ";
				}
					nSql = nSql + " select distinct ";
					if (nPara.indexOf(":LVL") >= 0) {
						nSql = nSql + "'1' as tr,";
					}
					
					nSql = nSql + " a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";					
					nSql += " exp_amt as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'FND' as tr_type,data_cr_date as period from fp.fp_tb_funds_detl"
							+ fileYr + " a";
					
					//// new change
					nSql += " where (fr_sus_no,to_sus_no,tr_head_to,fund_lvl) in ( ";
					nSql += " select fr_sus_no,to_sus_no,tr_head_to,max(fund_lvl) as fund_lvl ";
					nSql += " from fp.fp_tb_funds_detl" + fileYr ;
					nSql += " where (fr_sus_no,to_sus_no) in (select distinct psus_no,sus_no from fp.fp_tb_pref_head";
					
					if (role.equalsIgnoreCase("FP_VIEW") && m1_lvlh[0].equalsIgnoreCase("HQ")) {
						if (role.equalsIgnoreCase("FP_VIEW")) {
							nSql += " where psus_no='" + m1_lvlh[3] + "'";
						} else {
							nSql += " where psus_no='" + m1_lvlh[3] + "' and hq_type::integer<="+m1_lvlh[1];
						}
					} else {
						nSql += " where sus_no='" + m1_lvlh[3] + "'";	
					}
					
					nSql += " ) group by fr_sus_no,to_sus_no,tr_head_to ) and  a.tr_type='TFR'";

					
					nSql = " select distinct '1' as tr, a.fr_sus_no,a.tr_head_to as tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,";
					nSql += " 0 as prv_amt,0 as prj_amt, fndamt as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,";
					nSql += " 0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'FND' as tr_type,fnd_date as period ";
					nSql += " from fp.fp_tv_fund_detl" + fileYr +" a ";
					
					if (role.equalsIgnoreCase("FP_VIEW") && m1_lvlh[0].equalsIgnoreCase("HQ")) {
						if (role.equalsIgnoreCase("FP_VIEW")) {
							nSql += " where a.fr_sus_no='" + m1_lvlh[3] + "'";
						} else {
							nSql += " where a.fr_sus_no='" + m1_lvlh[3] + "'";  //and hq_type::integer<="+m1_lvlh[1];
						}
					} else {
						nSql += " where a.to_sus_no='" + m1_lvlh[3] + "'";	
					}
					//System.out.println("--FND-1-"+m1_lvlh[1]);
					if (!m1_lvlh[1].equalsIgnoreCase("5")) {	
						//System.out.println("--FND-2-"+m1_lvlh[0]);
						if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
							nSql += " and fr_sus_no<>to_sus_no";   /// add after 11 corps obsn
						} else {
							//System.out.println("--FND-3-"+m1_lvlh[0]);
							nSql += " and fr_sus_no=to_sus_no";
						}
					}
				unCnt = unCnt + 1;
			}
		}

		if (colList.indexOf(":ALT") >= 0) {
			if (unCnt > 0) {
				if (!m1_lvlh[1].equalsIgnoreCase("5")) {
					nSql += " union all ";
				}
			}
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head_to as tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,fndamt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,fnd_date as period from fp.fp_tv_fund_detl"
						+ fileYr + " a";
				/*nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl" + fileYr
						+ " where fr_sus_no='" + m1_lvlh[3] + "' and fr_sus_no<>to_sus_no)";
				nSql += " and a.tr_type='TFR'";*/
				nSql += " where a.fr_sus_no='" + m1_lvlh[3] + "'";
				nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
				nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
				}
				nSql += " )";
				unCnt = unCnt + 1;
			} else {
				if (!m1_lvlh[1].equalsIgnoreCase("5")) {
					nSql = nSql + " select ";
					if (nPara.indexOf(":LVL") >= 0) {
						nSql = nSql + "'2' as tr,";
					}
					nSql = nSql
							+ "  a.fr_sus_no,a.tr_head_to as tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
					nSql += " 0 as fnd_amt,fndamt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,fnd_date as period from fp.fp_tv_fund_detl"
							+ fileYr + " a";
					nSql += " where ";
					//nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl"
					//		+ fileYr + " where fr_sus_no='" + m1_lvlh[3] + "'";
					//nSql += " and fr_sus_no<>to_sus_no)";
					//nSql += " )";
					//nSql += " and a.tr_type='TFR'";
					//nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";
					
					nSql += " a.fr_sus_no='" + m1_lvlh[3] + "'";
					nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
					nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
					if (hdCndv.length() > 0) {
						nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
					}
					nSql += " )";
					unCnt = unCnt + 1;
				}
			}
		}
		if (colList.indexOf(":DALT") >= 0) {
			if (unCnt > 0) {
				if (!m1_lvlh[1].equalsIgnoreCase("5")) {
					nSql += " union all ";
				}
			}
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head_to as tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,fndamt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,fnd_date as period from fp.fp_tv_fund_detl"
						+ fileYr + " a";
				/*nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl" + fileYr
						+ " where fr_sus_no='" + m1_lvlh[3] + "' and fr_sus_no<>to_sus_no)";
				nSql += " and a.tr_type='TFR'";*/
				nSql += " where a.fr_sus_no='" + m1_lvlh[3] + "'";
				nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
				nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
				}
				nSql += " )";
				unCnt = unCnt + 1;
			} else {
				if (!m1_lvlh[1].equalsIgnoreCase("5")) {
					nSql = nSql + " select distinct ";
					if (nPara.indexOf(":LVL") >= 0) {
						nSql = nSql + "'2' as tr,";
					}
					
					nSql = nSql
							+ "  coalesce(b.fr_sus_no,psus_no) as fr_sus_no ,coalesce(b.tr_head,chl_head_code) as tr_head,";
					nSql = nSql
							+ "  coalesce(b.to_sus_no,sus_no) as to_sus_no,coalesce(b.tr_head_to,chl_head_code) as tr_head_to,";
					nSql = nSql
							+ "  0 as prv_amt,0 as prj_amt, 0 as fnd_amt,coalesce(b.alt_amt,0) as alt_amt,0 as exp_amt,";
					nSql = nSql
							+ "  0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,coalesce(b.tr_type,'TFR') as tr_type,";
					nSql = nSql
							+ "  b.period from fp.fp_tb_pref_head a	left join (";
					
					
					
					nSql = nSql
							+ "  select a.fr_sus_no,a.tr_head_to as tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
					nSql += " 0 as fnd_amt,fndamt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,'TFR' as tr_type,fnd_date as period from fp.fp_tv_fund_detl"
							+ fileYr + " a";
					nSql += " where ";
					//nSql += " where fund_lvl=(select distinct max(fund_lvl) as fund_lvl from fp.fp_tb_funds_detl"
					//		+ fileYr + " where fr_sus_no='" + m1_lvlh[3] + "'";
					//nSql += " and fr_sus_no<>to_sus_no)";
					//nSql += " )";
					//nSql += " and a.tr_type='TFR'";
					//nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";
					
					nSql += " a.fr_sus_no='" + m1_lvlh[3] + "'";
					nSql += " and (a.tr_head_to,a.to_sus_no) in (select distinct chl_head_code as tr_head,sus_no as sus_no ";
					nSql += " from fp.fp_tb_pref_head where psus_no='" + m1_lvlh[3] + "'";
					if (hdCndv.length() > 0) {
						nSql = nSql + " and " + hdCndvb + "='" + hdCndv + "'";
					}
					nSql += " )";
					
					nSql += ") b on a.psus_no=b.fr_sus_no and a.sus_no=b.to_sus_no and a.chl_head_code=b.tr_head_to";
					nSql += " where psus_no='" + m1_lvlh[3] + "'"; 
					
					unCnt = unCnt + 1;
				}
			}
		}
		if (colList.indexOf(":EXP") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}

			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			
			
			//nSql += " (case when a.fr_sus_no=a.to_sus_no then (select psus_no from fp.fp_tb_pref_head where sus_no=a.to_sus_no and chl_head_code=a.tr_head_to and psus_no<>sus_no limit 1)  else a.fr_sus_no end)";
			nSql += " b.psus_no";
			
			nSql += " as fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,0 as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,";
			nSql += " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt,";
			nSql += " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt,";
			nSql += " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt,";
			nSql += " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
			nSql += " 0 as fwd_amt,0 as bkd_amt,a.tr_type,data_cr_date as period from fp.fp_tb_trans_detl" + fileYr
					+ " a";
			nSql += " left join (select distinct psus_no,sus_no,chl_head_code from fp.fp_tb_pref_head where psus_no<>sus_no and (psus_no,sus_no,chl_head_code) in (select distinct fr_sus_no,to_sus_no,tr_head_to from fp.fp_tv_fund_detl"+fileYr+" where fndamt>0 )) b on a.to_sus_no=b.sus_no and a.tr_head_to=b.chl_head_code";
						
			if (m1_lvlh[1].equalsIgnoreCase("Q")) {
				nSql += " where (a.to_sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where psus_no='" + m1_lvlh[3] + "' ";
				} else {
					nSql += " where sus_no='" + m1_lvlh[3] + "' ";
				}
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
				}
				nSql += " )";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
						nSql += " where (a.to_sus_no,a.tr_head) in (";					
						nSql += " select distinct sus_no,chl_head_code from fp.fp_tv_pref_head ";
					if (!m1_lvlh[1].equalsIgnoreCase("-1")) {	
						nSql += " where (psus_no,sus_no,chl_head_code,lvl) in ( ";
						nSql += " select distinct psus_no,sus_no,chl_head_code,max(lvl) as lvl  from fp.fp_tv_pref_head ";						
						/*if (role.equalsIgnoreCase("FP_VIEW")) {*/
							nSql += " where split_part(npath,'_',1)='A000001' and npath like '%"+m1_lvlh[3]+"%' group by psus_no,sus_no,chl_head_code)";					
						/*} else {
							nSql += " where sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a ";
							nSql += " where psus_no='" + m1_lvlh[3] + "' or sus_no='" + m1_lvlh[3] + "'";
							nSql += " union all select distinct sus_no from fp.fp_tb_pref_head a ";
							nSql += " where psus_no='" + m1_lvlh[3] + "' or sus_no='" + m1_lvlh[3] + "'";
							nSql += " )  and split_part(npath,'_',1)='A000001' and npath like '%"+m1_lvlh[3]+"%' group by psus_no,sus_no,chl_head_code)";
						}*/
						/*  old recursive method, was ok 
						nSql += " select distinct t.sus_no,t.chl_head_code from ( WITH RECURSIVE nRecPrefUnit AS ( ";
						nSql += " select distinct p.psus_no,p.sus_no,p.chl_head_code from fp.fp_tb_pref_head p ";
						nSql += " where p.psus_no='" + m1_lvlh[3] +"'";
						nSql += " union  select distinct n.psus_no,n.sus_no,n.chl_head_code from fp.fp_tb_pref_head n ";
						nSql += " inner join nRecPrefUnit s on s.sus_no=n.psus_no and s.chl_head_code=n.chl_head_code) ";
						nSql += " select distinct p.psus_no,p.sus_no,p.chl_head_code  from nRecPrefUnit p) t";
						//nSql += " where t.psus_no='" + m1_lvlh[3] +"'";  /// chg for getting all underneeth */
					}
						nSql += " )";  
					
					
/*					nSql += " where a.to_sus_no in (select distinct t.sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select distinct p.psus_no,p.sus_no from fp.fp_tb_pref_head p";
					nSql += " where p.psus_no='" + m1_lvlh[3]
							+ "'  union  select distinct n.psus_no,n.sus_no from fp.fp_tb_pref_head n";
					nSql += " inner join nRecPrefUnit s on s.sus_no=n.psus_no ) select distinct p.psus_no,p.sus_no ";
					nSql += " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					//nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						//nSql = nSql + " and " + fmncnd;
						nSql = nSql + " Where " + fmncnd;
					}
					nSql += " ) t) ";
*/				} else {
					//nSql += " where a.to_sus_no in ('" + m1_lvlh[3] + "')";
					nSql += " where (a.to_sus_no,a.tr_head_to) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head where sus_no='" + m1_lvlh[3] + "'";
					if (!m1_lvlh[1].equalsIgnoreCase("5")) {
						nSql += " and psus_no='" + m1_lvlh[3] + "'";
					}
					nSql += ")";
				}
				nSql += " and a.tr_type in ('EXP','GST','EDT','OTH') ";
/*					nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
					nSql += " and (a.to_sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head ";
					nSql += " where psus_no='" + m1_lvlh[3] + "' ";
					if (hdCndv.length() > 0) {
						nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
					}
					nSql += " )";
*/				
			}
			unCnt = unCnt + 1;
		}
		if (colList.indexOf(":CDA") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			//nSql += " (case when a.fr_sus_no=a.to_sus_no then (select psus_no from fp.fp_tb_pref_head where sus_no=a.sus_no and chl_head_code=a.tr_head and psus_no<>sus_no limit 1)  else a.fr_sus_no end)";
			nSql += " b.psus_no";
			nSql += " as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,0 as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,fwd_amt as fwd_amt,bkd_amt as bkd_amt,'CDA' as tr_type,data_udp_date as period from fp.fp_tb_cda_detl"
					+ fileYr + " a";
			nSql += " left join (select distinct psus_no,sus_no,chl_head_code from fp.fp_tb_pref_head where psus_no<>sus_no and (psus_no,sus_no,chl_head_code) in (select distinct fr_sus_no,to_sus_no,tr_head_to from fp.fp_tv_fund_detl"+fileYr+" where fndamt>0)) b on a.sus_no=b.sus_no and a.tr_head=b.chl_head_code";
			if (m1_lvlh[1].equalsIgnoreCase("Q")) {
				nSql += " where (a.sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head";
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where psus_no='" + m1_lvlh[3] + "' ";
				} else {
					nSql += " where sus_no='" + m1_lvlh[3] + "' ";
				}
				if (hdCndv.length() > 0) {
					nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
				}
				nSql += " )";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					
					nSql += " where (a.sus_no,a.tr_head) in (";					
					nSql += " select distinct sus_no,chl_head_code from fp.fp_tv_pref_head ";
				if (!m1_lvlh[1].equalsIgnoreCase("-1")) {	
					nSql += " where (psus_no,sus_no,chl_head_code,lvl) in ( ";
					nSql += " select distinct psus_no,sus_no,chl_head_code,max(lvl) as lvl  from fp.fp_tv_pref_head ";					
					/*if (role.equalsIgnoreCase("FP_VIEW")) {*/
							nSql += " where split_part(npath,'_',1)='A000001' and npath like '%"+m1_lvlh[3]+"%' group by psus_no,sus_no,chl_head_code)";					
					/*} else {
						nSql += " where sus_no in (select distinct sus_no from fp.fp_tv_orbat_dtl a ";
						nSql += " where psus_no='" + m1_lvlh[3] + "' or sus_no='" + m1_lvlh[3] + "'";
						nSql += " union all select distinct sus_no from fp.fp_tb_pref_head a ";
						nSql += " where psus_no='" + m1_lvlh[3] + "' or sus_no='" + m1_lvlh[3] + "'";
						nSql += " )  and split_part(npath,'_',1)='A000001' and npath like '%"+m1_lvlh[3]+"%' group by psus_no,sus_no,chl_head_code)";
					}*/
					/*  old recursive method, was ok 
					nSql += " where (a.sus_no,a.tr_head) in ( select distinct t.sus_no,t.chl_head_code from ( WITH RECURSIVE nRecPrefUnit AS (";
					nSql += " select distinct p.psus_no,p.sus_no,p.chl_head_code from fp.fp_tb_pref_head p  ";
					nSql += " where p.psus_no='" + m1_lvlh[3] + "' ";
					nSql += " union  select distinct n.psus_no,n.sus_no,n.chl_head_code from fp.fp_tb_pref_head n  ";
					nSql += " inner join nRecPrefUnit s on s.sus_no=n.psus_no and s.chl_head_code=n.chl_head_code)  ";
					nSql += " select distinct p.psus_no,p.sus_no,p.chl_head_code  from nRecPrefUnit p) t"; */
					//nSql += " where t.psus_no='" + m1_lvlh[3] +"'";  
				}
					nSql += " )";
					
/*					nSql += " where a.sus_no in (select distinct t.sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select distinct p.psus_no,p.sus_no from fp.fp_tb_pref_head p";
					nSql += " where p.psus_no='" + m1_lvlh[3]
							+ "'  union  select distinct n.psus_no,n.sus_no from fp.fp_tb_pref_head n";
					nSql += " inner join nRecPrefUnit s on s.sus_no=n.psus_no ) select distinct p.psus_no,p.sus_no ";
					nSql += " from nRecPrefUnit p  inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					//nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						//nSql = nSql + " and " + fmncnd;
						nSql = nSql + " Where " + fmncnd;
					}
					nSql += " ) t) ";
*/				} else {
					//nSql += " where a.sus_no in ('" + m1_lvlh[3] + "')";
					nSql += " where (a.sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head where sus_no='" + m1_lvlh[3] + "'";
					if (!m1_lvlh[1].equalsIgnoreCase("5")) {
						nSql += " and psus_no='" + m1_lvlh[3] + "'";
					}
					nSql += ")";
				}				
				
/*					nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
					nSql += " and (a.sus_no,a.tr_head) in (select distinct sus_no,chl_head_code from fp.fp_tb_pref_head ";
					nSql += " where psus_no='" + m1_lvlh[3] + "'";
					if (hdCndv.length() > 0) {
						nSql = nSql + " and " + hdCndvb1 + "='" + hdCndv + "'";
					}
					nSql += " )";
*/			}
			unCnt = unCnt + 1;
		}
		return nSql.replaceAll("union all  union all","union all");
	}

	public String nFundbaseSql_nkok_oldmth(String nPara, String nUsrId, String trhead, String susdetl, String cfy,
			HttpSession sessionA, String colList) {
		String nSql = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String fmncnd = "";
		int unCnt = 0;
		String fileYr = cfy.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(cfy.substring(2, 4)) - 1) + "";
		String m1_lvlh[] = susdetl.split("_");

		String m1_hdlvl[] = trhead.split(":");

		if (m1_lvlh[1].equalsIgnoreCase("1")) {
			hdCnd = "split_part(tr_head_to,':',1) ";
			hdCnd1 = "split_part(tr_head,':',1) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 1) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("2")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 3) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("3")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 6) + "%'";
		}
		if (m1_lvlh[1].equalsIgnoreCase("4")) {
			hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
			hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			fmncnd = "q.form_code_control like '" + m1_lvlh[2].substring(0, 10) + "%'";
		}
		nSql = nSql + " select distinct ";
		if (nPara.indexOf(":LVL") >= 0) {
			nSql = nSql + "'2' as tr,";
		}
		nSql = nSql + " '" + m1_lvlh[3] + "' as fr_sus_no,chl_head_code as tr_head,sus_no as to_sus_no,";
		nSql = nSql + " chl_head_code as tr_head_to,0 as prv_amt,0 as prj_amt, 0 as fnd_amt,0 as alt_amt,0 as exp_amt,";
		nSql = nSql + " 0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_pref_head ";
		nSql = nSql + " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='" + m1_lvlh[3] + "') ";
		nSql = nSql + " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head where sus_no='" + m1_lvlh[3]
				+ "')";
		unCnt = unCnt + 1;
		if (colList.indexOf(":PRV") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,exp_amt as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_funds_detl"
						+ fileYrPrv + " a";
				nSql += " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to";
				nSql += " from fp.fp_tb_funds_detl" + fileYrPrv;
				nSql += " where fr_sus_no='" + m1_lvlh[3] + "'";
				nSql += " group by fr_sus_no,to_sus_no,tr_head_to) b";
				nSql += " on b.to_sus_no=a.to_sus_no and b.tr_head_to=a.tr_head_to";
				nSql += " and a.fund_lvl=b.fund_lvl where a.tr_type='TFR'";
				nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";

				nSql += " and (a.tr_head_to,a.to_sus_no) in (select chl_head_code as tr_head,sus_no as sus_no from fp.fp_tb_pref_head";
				nSql += " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')  ";
				nSql += " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'))";
				unCnt = unCnt + 1;
			} else {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ "  a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head as tr_head_to,exp_amt as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_funds_detl"
						+ fileYrPrv + " a";
				nSql += " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to";
				nSql += " from fp.fp_tb_funds_detl" + fileYrPrv;
				nSql += " where fr_sus_no='" + m1_lvlh[3] + "'";
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " and fr_sus_no<>to_sus_no";
				} else {
					nSql += " and fr_sus_no=to_sus_no";
				}
				nSql += " group by fr_sus_no,to_sus_no,tr_head_to) b";
				nSql += " on b.to_sus_no=a.to_sus_no and b.tr_head_to=a.tr_head_to";
				nSql += " and a.fund_lvl=b.fund_lvl where a.tr_type='TFR'";
				nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";

				nSql += " and (a.tr_head_to,a.to_sus_no) in (select chl_head_code as tr_head,sus_no as sus_no from fp.fp_tb_pref_head";
				nSql += " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')  ";
				nSql += " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'))";
				unCnt = unCnt + 1;
			}
		}

		if (colList.indexOf(":PRJ") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}

			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			nSql = nSql + " '" + m1_lvlh[3]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,proj_amt as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_proj_detl"
					+ fileYr + " a";
			if (m1_lvlh[1].equalsIgnoreCase("0")) {
				nSql += " where a.sus_no in (select distinct t.chl_sus_no from fp.fp_tb_pref_unit t ";
				nSql += " where t.sus_no='" + m1_lvlh[3] + "') ";
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where a.sus_no in (select distinct t.chl_sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p";
					nSql += " where p.sus_no='" + m1_lvlh[3]
							+ "' union select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n";
					nSql += " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no ) select distinct p.sus_no,p.chl_sus_no ";
					nSql += " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						nSql = nSql + " and " + fmncnd;
					}
					nSql += " ) t) ";
				} else {
					nSql += " where a.sus_no in ('" + m1_lvlh[3] + "')";
				}
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			}
			unCnt = unCnt + 1;
		}
		if (colList.indexOf(":FND") >= 0) {
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				if (unCnt > 0) {
					nSql += " union all ";
				}
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'1' as tr,";
				}
				nSql = nSql
						+ " fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as prv_amt,0 as prj_amt,recd_amt as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_trans_detl"
						+ fileYr + " where tr_type='FND' ";
				nSql = nSql + " and to_sus_no='" + m1_lvlh[3] + "'";
				unCnt = unCnt + 1;
			} else {
				if (unCnt > 0) {
					nSql += " union all ";
				}
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'1' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
				nSql += " exp_amt as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_funds_detl"
						+ fileYr + " a";
				nSql += " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to";
				nSql += " from fp.fp_tb_funds_detl" + fileYr;
				nSql += " where to_sus_no='" + m1_lvlh[3] + "'";
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " and fr_sus_no<>to_sus_no";
				} else {
					nSql += " and fr_sus_no=to_sus_no";
				}
				nSql += " group by fr_sus_no,to_sus_no,tr_head_to) b";
				nSql += " on b.to_sus_no=a.to_sus_no and b.tr_head_to=a.tr_head_to";
				nSql += " and a.fund_lvl=b.fund_lvl where a.tr_type='TFR'";
				nSql += " and a.to_sus_no='" + m1_lvlh[3] + "'";

				nSql += " and (a.tr_head_to,a.to_sus_no) in (select chl_head_code as tr_head,sus_no as sus_no from fp.fp_tb_pref_head";
				nSql += " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')  ";
				nSql += " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'))";
				unCnt = unCnt + 1;
			}
		}

		if (colList.indexOf(":ALT") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			if (m1_lvlh[1].equalsIgnoreCase("-1")) {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ " a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to as tr_head_to,0 as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,exp_amt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_funds_detl"
						+ fileYr + " a";
				nSql += " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to";
				nSql += " from fp.fp_tb_funds_detl" + fileYr;
				nSql += " where fr_sus_no='" + m1_lvlh[3] + "'";
				nSql += " group by fr_sus_no,to_sus_no,tr_head_to) b";
				nSql += " on b.to_sus_no=a.to_sus_no and b.tr_head_to=a.tr_head_to";
				nSql += " and a.fund_lvl=b.fund_lvl where a.tr_type='TFR'";
				nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";

				nSql += " and (a.tr_head_to,a.to_sus_no) in (select chl_head_code as tr_head,sus_no as sus_no from fp.fp_tb_pref_head";
				nSql += " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')  ";
				nSql += " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'))";
				unCnt = unCnt + 1;
			} else {
				nSql = nSql + " select ";
				if (nPara.indexOf(":LVL") >= 0) {
					nSql = nSql + "'2' as tr,";
				}
				nSql = nSql
						+ "  a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,0 as prj_amt,";
				nSql += " 0 as fnd_amt,exp_amt as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt from fp.fp_tb_funds_detl"
						+ fileYr + " a";
				nSql += " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to";
				nSql += " from fp.fp_tb_funds_detl" + fileYr;
				nSql += " where fr_sus_no='" + m1_lvlh[3] + "'";
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " and fr_sus_no<>to_sus_no";
				} else {
					nSql += " and fr_sus_no=to_sus_no";
				}
				nSql += " group by fr_sus_no,to_sus_no,tr_head_to) b";
				nSql += " on b.to_sus_no=a.to_sus_no and b.tr_head_to=a.tr_head_to";
				nSql += " and a.fund_lvl=b.fund_lvl where a.tr_type='TFR'";
				nSql += " and a.fr_sus_no='" + m1_lvlh[3] + "'";

				nSql += " and (a.tr_head_to,a.to_sus_no) in (select chl_head_code as tr_head,sus_no as sus_no from fp.fp_tb_pref_head";
				nSql += " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')  ";
				nSql += " and chl_head_code in (select chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "'))";
				unCnt = unCnt + 1;
			}
		}
		if (colList.indexOf(":EXP") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			nSql = nSql + " '" + m1_lvlh[3]
					+ "' as fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,0 as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,";
			nSql += " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt,";
			nSql += " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt,";
			nSql += " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt,";
			nSql += " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
			nSql += " 0 as fwd_amt,0 as bkd_amt from fp.fp_tb_trans_detl" + fileYr + " a";
			if (m1_lvlh[1].equalsIgnoreCase("0")) {
				nSql += " where a.to_sus_no in (select distinct t.chl_sus_no from fp.fp_tb_pref_unit t ";
				nSql += " where t.sus_no='" + m1_lvlh[3] + "') ";
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where a.to_sus_no in (select distinct t.chl_sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p";
					nSql += " where p.sus_no='" + m1_lvlh[3]
							+ "'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n";
					nSql += " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no ) select distinct p.sus_no,p.chl_sus_no ";
					nSql += " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						nSql = nSql + " and " + fmncnd;
					}
					nSql += " ) t) ";
				} else {
					nSql += " where a.to_sus_no in ('" + m1_lvlh[3] + "')";
				}
				nSql += " and a.tr_type in ('EXP','GST','EDT','OTH') and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			}
			unCnt = unCnt + 1;
		}
		if (colList.indexOf(":CDA") >= 0) {
			if (unCnt > 0) {
				nSql += " union all ";
			}
			nSql = nSql + " select ";
			if (nPara.indexOf(":LVL") >= 0) {
				nSql = nSql + "'2' as tr,";
			}
			nSql = nSql + " '" + m1_lvlh[3]
					+ "' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,0 as prv_amt,0 as prj_amt,";
			nSql += " 0 as fnd_amt,0 as alt_amt,0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,fwd_amt as fwd_amt,bkd_amt as bkd_amt from fp.fp_tb_cda_detl"
					+ fileYr + " a";
			if (m1_lvlh[1].equalsIgnoreCase("0")) {
				nSql += " where a.sus_no in (select distinct t.chl_sus_no from fp.fp_tb_pref_unit t ";
				nSql += " where t.sus_no='" + m1_lvlh[3] + "') ";
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			} else {
				if (m1_lvlh[0].equalsIgnoreCase("HQ")) {
					nSql += " where a.sus_no in (select distinct t.chl_sus_no from (";
					nSql += " WITH RECURSIVE nRecPrefUnit AS ( select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p";
					nSql += " where p.sus_no='" + m1_lvlh[3]
							+ "'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n";
					nSql += " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no ) select distinct p.sus_no,p.chl_sus_no ";
					nSql += " from nRecPrefUnit p inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
					nSql += " where q.hq_type::integer>=" + m1_lvlh[1];
					if (fmncnd.length() > 0) {
						nSql = nSql + " and " + fmncnd;
					}
					nSql += " ) t) ";
				} else {
					nSql += " where a.sus_no in ('" + m1_lvlh[3] + "')";
				}
				nSql += " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head ";
				nSql += " where sus_no='" + m1_lvlh[3] + "')";
			}
			unCnt = unCnt + 1;
		}
		return nSql;
	}

	public ArrayList<ArrayList<String>> nFundInfoDBReport(String rptName, String bhllst, String bhdlst, String coln,
			String dspty, String rptagr, String rolesus, String cfy, HttpSession sessionA) {

		String oPara = coln;
		String oParaS[] = oPara.replace("ln", "").split("_");

		String m1_tryear = fp_trDao.nGetAdmFinYear("CFY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		String nUsrId = (String) sessionA.getAttribute("userSusNo");

		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();
		nUnt = fp_trDao.getunitBuglist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		if (nUnt.get(0).get(0).equalsIgnoreCase(null)) {
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			ArrayList<String> list = new ArrayList<String>();
			list.add("NIL");
			li.add(list);
			return li;
		}
		String nPara = "4_HQ_" + nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		String m1_lvl = nUnt.get(0).get(3) + "_" + nUnt.get(0).get(0) + "_" + nUnt.get(0).get(1);
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 = "";
		String hdCnd = "";
		String hdCnd1 = "";
		String nParaS[] = nPara.replace("ln", "").split("_");
		String trhead = "4:";
		String m1_hdlvlq = nParaS[2];
		String m1_hdlvlh[] = m1_hdlvlq.split(":");
		String m1_hdlvl = m1_hdlvlh[0];
		String m1lvl = nParaS[4];
		String m1lvlq = nParaS[3];
		String m1lvl1 = nParaS[2];
		String m1_slvl = "HQ";
		String rsfmt = "CR";
		String rsfmtv = "1";
		String rsfmtvd = "2";
		String fmncnd = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtv = "10000000";
			rsfmtvd = "5";
		} else {
			rsfmtv = "1";
			rsfmtvd = "2";
		}

		String rsfmtd = "5";
		String rsfmtdt = "";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd = "5";
			rsfmtdt = "R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd = "5";
			rsfmtdt = "R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd = "2";
			rsfmtdt = "R2R";
		}

		try {
			conn = dataSource.getConnection();
			if (m1_hdlvl.equalsIgnoreCase("1")) {
				hdCnd = "split_part(tr_head_to,':',1) ";
				hdCnd1 = "split_part(tr_head,':',1) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("2")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("3")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (m1_hdlvl.equalsIgnoreCase("4")) {
				hdCnd = "concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				hdCnd1 = "concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				fmncnd = "q.form_code_control like '" + nUnt.get(0).get(0).substring(0, 1) + "%'";
			}
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = " select tlvl,p.tr_head_to,p.to_sus_no,p.hq_type,p.unit_name,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			} else {
				sql1 = " select tlvl,p.to_sus_no,p.hq_type,p.unit_name,p.tr_head_to,p.head_desc,p.altamt,p.expamt,p.edtamt,p.gstamt,p.othamt,p.fwdamt,p.bkdamt from (";
			}
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '1' as tlvl,a.tr_head_to as tr_head_to,max(a.to_sus_no) as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '1' as tlvl,a.to_sus_no as to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(a.tr_head_to) as tr_head_to,max(h.head_desc) as head_desc,";
			}

			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA, ":" + coln);

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by a.tr_head_to";
			} else {
				sql1 = sql1 + " group by to_sus_no";

			}
			sql1 = sql1 + " union all ";
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = sql1
						+ " select '2' as tlvl,a.tr_head_to,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,max(h.head_desc) as head_desc,";
			} else {
				sql1 = sql1
						+ " select '2' as tlvl,a.to_sus_no,max(o.hq_type) as hq_type,max(o.unit_name) as unit_name,a.tr_head_to,max(h.head_desc) as head_desc,";
			}
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.alt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as altamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.exp_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as expamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.edt_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as edtamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.gst_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as gstamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.oth_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as othamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.fwd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as fwdamt,";
			sql1 = sql1 + " nconvertinr(coalesce(sum(a.bkd_amt),0)::numeric," + rsfmtd + ",'" + rsfmtdt
					+ "')  as bkdamt from (";

			sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead, m1_slvl + "_" + m1_lvl, m1_tryear, sessionA, ":" + coln);

			sql1 = sql1 + " ) a left join fp.fp_tv_head_dtl h on a.tr_head_to=h.tr_head";
			sql1 = sql1 + " left join fp.fp_tv_orbat_dtl o on a.to_sus_no=o.sus_no";
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " group by tr_head_to,to_sus_no";
			} else {
				sql1 = sql1 + " group by to_sus_no,tr_head_to";
			}
			sql1 = sql1 + " ) p ";
			if (dspty.equalsIgnoreCase("BHD")) {
				sql1 = sql1 + " order by p.tr_head_to,tlvl,p.hq_type,p.to_sus_no";
			} else {
				sql1 = sql1 + " order by p.hq_type,p.to_sus_no,tlvl,p.tr_head_to";
			}
			String enc = "";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(rs.getString("tr_head_to"));
					list2.add(rs.getString("head_desc"));
					list2.add(rs.getString("altamt"));
					list2.add(rs.getString("expamt"));
					list2.add(rs.getString("fwdamt"));
					list2.add(rs.getString("bkdamt"));
					list2.add("");
					list2.add(rs.getString("to_sus_no"));
					list2.add(rs.getString("hq_type"));
					list2.add(rs.getString("unit_name"));
					list2.add(rs.getString("gstamt"));
					list2.add(rs.getString("edtamt"));
					list2.add(rs.getString("othamt"));
					list2.add(rs.getString("tlvl"));
					li.add(list2);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> getUserDetails(String user_id) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sql1 = ""
					+ "select l.userid,o.sus_no,l.army_no,o.unit_name,o.arm_code,o.form_code_control form_code,trim(l.username) username,name_nodal_officer,o1.unit_name as fmn_name from logininformation l "
					+ "left join fp.fp_tv_orbat_dtl o on l.user_sus_no = o.sus_no and o.status_sus_no = 'Active' "
					+ "left join nrv_hq o1 on o.psus_no=o1.sus_no "
					+ "where upper(trim(l.username)) = upper(trim(?))";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, user_id);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("userid"));
				list.add(rs.getString("username"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("army_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("arm_code"));
				list.add(rs.getString("form_code"));
				list.add(rs.getString("name_nodal_officer"));
				list.add(rs.getString("fmn_name"));
				li.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return li;
	}

	public boolean updateUserDetails(String user_id, String army_no, String sus_no, String uid, String sus_nodal) {
		boolean ret = false;

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String formation_no = "";
			String arm_code = "";

			String sql = " select o.arm_code,o.form_code_control form_code from fp.fp_tv_orbat_dtl o where o.status_sus_no = 'Active' and upper(o.sus_no) = upper(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				formation_no = rs.getString("form_code");
				arm_code = rs.getString("arm_code");
			}

			sql = " update logininformation set army_no = UPPER(?),user_sus_no=?,user_formation_no=?,user_arm_code=?,name_nodal_officer=UPPER(?) where lower(trim(username)) = lower(?) and userid = ?::int";
			stmt = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, army_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, formation_no);
			stmt.setString(4, arm_code);
			stmt.setString(5, sus_nodal);
			stmt.setString(6, user_id);
			stmt.setString(7, uid);
			int c = stmt.executeUpdate();

			ret = c > 0 ? true : false;

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			//System.out.println("Ex " + e);
			ret = false;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getAllUnitDtls(String param, int limit, HttpSession sess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "select o.unit_name unit,o.sus_no as sus_no,o.arm_code arm_code,o.form_code_control form_code from fp.fp_tv_orbat_dtl o "
					+ "where o.status_sus_no = 'Active' and (o.sus_no ilike ? or o.unit_name ilike ?) limit ?";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, param + "%");
			stmt.setString(2, "%" + param + "%");
			stmt.setInt(3, limit);

			ResultSet rs = stmt.executeQuery();
			List<String> skipCols = new ArrayList<String>();
			skipCols.add("arm_code");
			skipCols.add("form_code");
			
			list = encryptList(rs, sess, skipCols);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> encryptList(ResultSet rs, HttpSession s) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();

			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher ci = null;

			try {
				ci = hex_asciiDao.EncryptionSHA256Algo(s, enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}

			byte[] encCode = null;
			String b64 = "";

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					encCode = null;
					try {
						encCode = ci.doFinal(rs.getObject(i).toString().getBytes());
					} catch (IllegalBlockSizeException | BadPaddingException e) {
						e.printStackTrace();
					}
					b64 = new String(Base64.encodeBase64(encCode));
					columns.put(metaData.getColumnLabel(i), b64);
				}
				columns.put("meta", enckey);
				list.add(columns);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// @Param 1 - Data Resultset
	// @Param 2 - HttpSession Obj
	// @Param 3 - Cols to Skip for Encryption
	public List<Map<String, Object>> encryptList(ResultSet rs, HttpSession s, List<String> skipCols) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();

			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher ci = null;

			try {
				ci = hex_asciiDao.EncryptionSHA256Algo(s, enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}

			byte[] encCode = null;
			String b64 = "";
			String ColumnLabel = "";

			int columnCount = metaData.getColumnCount();
			int c = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					encCode = null;
					b64 = "";
					ColumnLabel = metaData.getColumnLabel(i);
					if (skipCols.contains(ColumnLabel)) {// skipping
						b64 = rs.getObject(i).toString();
					} else {
						try {
							encCode = ci.doFinal(rs.getObject(i).toString().getBytes());
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}
						b64 = new String(Base64.encodeBase64(encCode));
					}
					columns.put(ColumnLabel, b64);
				}
				if (c == 1)
					columns.put("meta", enckey);
				c++;
				list.add(columns);
			}
			rs.close();
		} catch (SQLException e) {
			list = null;
		}
		return list;
	}
	public ArrayList<ArrayList<String>> nFundStatusHQHeadCDB(HttpSession sessionA,String m1_tryear,String m1_nomen,String m1_nomenin,String m1_slvl,String m1_lvl,String m1_hdlvl,String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;    	
		String m1lvl[]=m1_lvl.split("_");
		ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();    	
		nUnt=fp_trDao.getunitBuglist("",sessionA,"SUS_5_XXXXXXXXXX_"+m1lvl[2]);
		
		String fileYr=m1_tryear.substring(2,4);
		String fileYrPrv=(Integer.parseInt(m1_tryear.substring(2,4))+1)+"";
		String sql1="";
	    String hdCnd="";
	    String hdCnd0="";
	    String hdCnd1="";
	    String rsfmt="CR";
	    String rsfmtv="1";
	    String rsfmtvd="2";
	    
	    String rsfmtd="5";
		String rsfmtdt="";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd="5";
			rsfmtdt="R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd="5";
			rsfmtdt="R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd="2";
			rsfmtdt="R2R";
		}    	
	    
	    String fmncnd="";
	    if (rsfmt.equalsIgnoreCase("CR")) {
	    	rsfmtv="10000000";
	    	rsfmtvd="5";
	    } else {
	    	rsfmtv="1";
	    	rsfmtvd="2";
	    }
	    String trhead =m1_hdlvl+":";
		try{	  
			conn = dataSource.getConnection();
		    
		    if(m1_hdlvl.equalsIgnoreCase("1")) {
			    hdCnd="split_part(tr_head_to,':',1) ";
			    hdCnd0="split_part(fr_head_to,':',1) ";
			    hdCnd1="split_part(tr_head,':',1) ";
			    fmncnd="q.form_code_control like '" +nUnt.get(0).get(0).substring(0, 1) +"%'";
		    }
		    if(m1_hdlvl.equalsIgnoreCase("2")) {
			    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
			    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
			    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";		
			    fmncnd="q.form_code_control like '" +nUnt.get(0).get(0).substring(0, 3) +"%'";
		    }
		    if(m1_hdlvl.equalsIgnoreCase("3")) {
			    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
			    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
			    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
			    fmncnd="q.form_code_control like '" +nUnt.get(0).get(0).substring(0, 6) +"%'";
		    }
		    if(m1_hdlvl.equalsIgnoreCase("4")) {
			    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
			    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
			    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
			    fmncnd="q.form_code_control like '" +nUnt.get(0).get(0).substring(0, 10) +"%'";
		    } 		    
		    		    
				//if (m1lvl[0].equalsIgnoreCase("-1")) {
					
					sql1 = sql1 + " select max(altunits) as altunits,(case when to_char(max(altupd)::timestamp,'dd Mon yyyy')='01 Jan 1990' then 'NIL' else to_char(max(altupd)::timestamp,'dd Mon yyyy') end) as altupd,";
					sql1 = sql1 + " (case when to_char(max(fndupd)::timestamp,'dd Mon yyyy')='01 Jan 1990' then 'NIL' else to_char(max(fndupd)::timestamp,'dd Mon yyyy') end) as fndupd,";
					sql1 = sql1 + " (case when to_char(max(expupd)::timestamp,'dd Mon yyyy')='01 Jan 1990' then 'NIL' else to_char(max(expupd)::timestamp,'dd Mon yyyy') end) as expupd,";
					sql1 = sql1 + " (case when to_char(max(bkdupd)::timestamp,'dd Mon yyyy')='01 Jan 1990' then 'NIL' else to_char(max(bkdupd)::timestamp,'dd Mon yyyy') end) as bkdupd from (";
			    	  	    					    
					sql1 = sql1 + " select 0 as altunits,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as altupd,to_char(max(period)::timestamp,'dd Mon yyyy') as fndupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as expupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as bkdupd from (";		    	
			    	sql1=sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":FND");		    	
			    	sql1 = sql1 + " ) a where fnd_amt>0   ";
					
			    	sql1 = sql1 + " union all ";
			    	
			   		sql1 = sql1 + " select count(to_sus_no) as altunits,to_char(max(period)::timestamp,'dd Mon yyyy') as altupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as fndupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as expupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as bkdupd  from (select distinct p.to_sus_no,p.period from (";		    	
			    	sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":ALT");		    	
			    	sql1 = sql1 + " ) p where alt_amt>0 ) q   ";
			    	
			    	sql1 = sql1 + " union all ";
			    	
			    	sql1 = sql1 + " select 0 as altunits,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as altupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as fndupd,to_char(max(period)::timestamp,'dd Mon yyyy') as expupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as bkdupd from (";		    	
			    	sql1=sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":EXP");		    	
			    	sql1 = sql1 + " ) a  ";

			    	sql1 = sql1 + " union all ";
			    	
			    	sql1 = sql1 + " select 0 as altunits,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as altupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as fndupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as expupd,to_char(max(period)::timestamp,'dd Mon yyyy') as bkdupd from (";		    	
			    	sql1=sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":CDA");		    	
			    	sql1 = sql1 + " ) a where bkd_amt>0   ";
			    	
			    	sql1 = sql1 + " ) t   ";

			   /* } else {
			    	sql1 = sql1 + " select max(altunits) as altunits,to_char(max(expupd)::timestamp,'dd Mon yyyy') as expupd,to_char(max(bkdupd)::timestamp,'dd Mon yyyy') as bkdupd from (";
					    
			   		sql1 = sql1 + " select count(to_sus_no) as altunits,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as expupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as bkdupd  from (select distinct p.to_sus_no from (";		    	
			    	sql1 = sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":ALT");		    	
			    	sql1 = sql1 + " ) p where alt_amt>0 ) q   ";
			    	
			    	sql1 = sql1 + " union all ";
			    	
			    	sql1 = sql1 + " select 0 as altunits,to_char(max(period)::timestamp,'dd Mon yyyy') as expupd,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as bkdupd from (";		    	
			    	sql1=sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":EXP");		    	
			    	sql1 = sql1 + " ) a  ";

			    	sql1 = sql1 + " union all ";
			    	
			    	sql1 = sql1 + " select 0 as altunits,to_char('01-01-1990'::timestamp,'dd Mon yyyy') as expupd,to_char(max(period)::timestamp,'dd Mon yyyy') as bkdupd from (";		    	
			    	sql1=sql1 + nFundbaseSql(":LVL", nUsrId, trhead,m1_slvl+"_"+m1_lvl, m1_tryear, sessionA,":CDA");		    	
			    	sql1 = sql1 + " ) a where bkd_amt>0   ";
			    	
			    	sql1 = sql1 + " ) t   ";
			    }*/
			//System.out.println("nFundStatusHQHeadCDB-"+sql1);
		    PreparedStatement stmt = conn.prepareStatement(sql1);
		    ResultSet rs = stmt.executeQuery();   
		   
		    if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list);
		    }else{
		    	while(rs.next()){
		        	ArrayList<String> list2 = new ArrayList<String>();
		        	list2.add(rs.getString("fndupd"));
		        	list2.add(rs.getString("altunits"));
	 	    		list2.add(rs.getString("expupd"));
	 	    		list2.add(rs.getString("bkdupd"));
	 	    		list2.add(rs.getString("altupd"));
	 	    		list2.add(m1_tryear);	 	    		
	 	        	li.add(list2); 
	 		    }
		    }
	 	    rs.close();
	 	    stmt.close();
	 		conn.close();
	 	}catch(SQLException e){
	 		e.printStackTrace();
	 	}	
	 	return li;
	}
	
	public ArrayList<ArrayList<String>> getNewfaqdetail1() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			Statement stmt = conn.createStatement();
			sql += "  select question,answer,section,id from hd_tb_miso_faq order by section ";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
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
	
	public ArrayList<ArrayList<String>> nLoginInfoData(HttpSession sessionA,String m1_tryear,String m1_nomen,String m1_nomenin,String m1_slvl,String m1_lvl,String m1_hdlvl,String nUsrId) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		//m1_lvl="-1_X000000000_A000001";
		String m1lvl[]=m1_lvl.split("_");
		/*ArrayList<ArrayList<String>> nUnt = new ArrayList<ArrayList<String>>();    	
		
		nUnt=fp_trDao.getunitBuglist("",sessionA,"SUS_"+m1lvl[2]);*/
		
		//String fileYr=m1_tryear.substring(2,4);
		//String fileYrPrv=(Integer.parseInt(m1_tryear.substring(2,4))+1)+"";
		String sql1="";
	    String hdCnd="";
	    String hdCnd0="";
	    String hdCnd1="";
	    String rsfmt="CR";
	    String rsfmtv="1";
	    String rsfmtvd="2";
	    
	    String rsfmtd="5";
		String rsfmtdt="";
		if (rsfmt.equalsIgnoreCase("CR")) {
			rsfmtd="5";
			rsfmtdt="R2C";
		} else if (rsfmt.equalsIgnoreCase("LC")) {
			rsfmtd="5";
			rsfmtdt="R2L";
		} else if (rsfmt.equalsIgnoreCase("RS")) {
			rsfmtd="2";
			rsfmtdt="R2R";
		}    	
	    
	    String fmncnd="";
	    if (rsfmt.equalsIgnoreCase("CR")) {
	    	rsfmtv="10000000";
	    	rsfmtvd="5";
	    } else {
	    	rsfmtv="1";
	    	rsfmtvd="2";
	    }
	    String trhead =m1_hdlvl+":";
		try{	  
			conn = dataSource.getConnection();
					
					    	sql1 += " select distinct max(b.userid) as userid, username,max(b.login_name) as name,max(user_sus_no) as sus_no,max(o.unit_name) as unit_name,";
					    	sql1 += " (case when max(o.hq_type) is null then 'NOT MAPPED YET' when max(o.hq_type)::integer<=0 then 'DTE' else max(o2.unit_name) end) as comd_name,";
					    	sql1 += " max(o.form_code_control) as form_code_control,";
					    	sql1 += " (case when max(o.hq_type) is null then 'NOT ASSIGNED' when max(o.hq_type)::integer<=0 then 'DTE' else max(o1.unit_name) end) as fmn_hq,";
					    	sql1 += " max(o.hq_type) as hq_type,typ,max(a1.userid) as g1,max(a1.createddate) as lastlogin,to_char(max(a1.createddate),'dd-mm-yyyy HH:mm:ss') as lastlogindt";
					    	sql1 += " ,(case when max(a1.createddate) is not null then 'Logged' else 'Not Logged' end) as logstat";
					    	sql1 += " ,(case when max(a1.createddate) is not null then age(max(a1.createddate)) else '0' end) as logperiod,max(b.role_type) as role_type,max(b.access_lvl) as access_lvl";
					    	sql1 += " from (select distinct userid,username,login_name,army_no,user_sus_no,user_formation_no,q.role_id,q.moduleid,";
					    	sql1 += " (case when q.role like '%fp%' then 'FP' else 'MISO' end) as typ,q.role_type,q.access_lvl";
					    	sql1 += " from public.logininformation p inner join (";
					    	sql1 += " select distinct a.user_id,a.role_id,b.moduleid,c.role,c.role_type,c.access_lvl from userroleinformation a"; 
					    	sql1 += " inner join tb_ldap_rolemaster b on a.role_id=b.roleid"; 
					    	sql1 += " left join roleinformation c on a.role_id=c.role_id";
					    	sql1 += " where b.moduleid=32 or b.moduleid=8 or b.moduleid=4";
					    	sql1 += " ) q on p.userid=q.user_id) b"; 
					    	sql1 += " left join userlogincountinfo a1 on b.userid=a1.userid";
					    	sql1 += " left join fp.fp_tv_orbat_dtl o on b.user_sus_no=o.sus_no";
					    	sql1 += " left join ( select a.form_code_control,a.unit_name as unit_name,b.hq_type as hq_type from nrv_hq a";
					    	sql1 += " left join (select distinct form_code_control,hq_type from fp.fp_tv_orbat_dtl where hq_type::integer<=4) b";
					    	sql1 += " on a.form_code_control=b.form_code_control"; 
					    	sql1 += " ) o1 on o.form_code_control=o1.form_code_control";
					    	sql1 += " left join nrv_hq o2 on concat(left(o.form_code_control,1),'000000000')=o2.form_code_control";
					    	sql1 += " right join (select distinct t.sus_no,o.unit_name,o.psus_no,o.hq_type,o.form_code_control from (";
					    	sql1 += " WITH RECURSIVE nRecPrefUnit AS ( select distinct p.psus_no,p.sus_no,p.chl_head_code from fp.fp_tb_pref_head p";
					    	sql1 += " where p.psus_no='"+m1lvl[2]+"' or p.sus_no='"+m1lvl[2]+"' ";
					    	sql1 += " union  select distinct n.psus_no,n.sus_no,n.chl_head_code from fp.fp_tb_pref_head n";
					    	sql1 += " inner join nRecPrefUnit s on s.sus_no=n.psus_no and s.chl_head_code=n.chl_head_code)";
					    	sql1 += " select distinct p.psus_no,p.sus_no,p.chl_head_code  from nRecPrefUnit p) t";
					    	sql1 += " left join fp.fp_tv_orbat_dtl o on t.sus_no=o.sus_no";
					    	sql1 += " ) n1 on n1.sus_no=b.user_sus_no";
					    	sql1 += " group by username,typ ";
					    	//sql1 += " having typ='FP'";
					    	sql1 += " order by hq_type,form_code_control,sus_no,typ,sus_no,username";

			//System.out.println("nLoginInfoData-"+sql1);
		    PreparedStatement stmt = conn.prepareStatement(sql1);
		    ResultSet rs = stmt.executeQuery();   
		   
		    if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list);
		    }else{
		    	while(rs.next()){
		        	ArrayList<String> list2 = new ArrayList<String>();
		        	list2.add(rs.getString("userid"));
		        	list2.add(rs.getString("username"));
	 	    		list2.add(rs.getString("name"));
	 	    		list2.add(rs.getString("sus_no"));
	 	    		list2.add(rs.getString("unit_name"));
	 	    		list2.add(rs.getString("comd_name"));
	 	    		list2.add(rs.getString("form_code_control"));
	 	    		list2.add(rs.getString("fmn_hq"));
	 	    		list2.add(rs.getString("hq_type"));
	 	    		list2.add(rs.getString("typ"));
	 	    		list2.add(rs.getString("g1"));
	 	    		list2.add(rs.getString("lastlogin"));
	 	    		list2.add(rs.getString("lastlogindt"));
	 	    		list2.add(rs.getString("logstat").toUpperCase());
	 	    		list2.add(rs.getString("logperiod").toUpperCase());
	 	    		list2.add(rs.getString("role_type"));
	 	    		list2.add(rs.getString("access_lvl"));
	 	        	li.add(list2); 
	 		    }
		    }
	 	    rs.close();
	 	    stmt.close();
	 		conn.close();
	 	}catch(SQLException e){
	 		e.printStackTrace();
	 	}	
	 	return li;
	}

	public ArrayList<ArrayList<String>> getDOLetterData(String m1lvl,String m1rptlvl) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1 ="";
		try {
			conn = dataSource.getConnection();
			sql1 += " select * from (select ic_no,rk,name,parent_unit,present_unit,present_appt,doc,age,part_d,serv_days,(case when (serv_days>=2920 and serv_days<=3285) and part_d='YES' then 'E' else 'F' end) as elgb_st from (";
			sql1 +=" select ic_no,rk,name,parent_unit,present_unit,present_appt,doc,upper(age(current_date,doc)::text) as age,part_d,";
			sql1 +=" date_part('year', age(current_date,doc))*365 + date_part('month', age(current_date,doc))*30 + date_part('days', age(current_date,doc)) as serv_days";
			sql1 +=" from fp.sikhli_do_ltr ) p ) p1 ";
			if (m1lvl.equalsIgnoreCase("DSSCE")) {
				sql1 += " where part_d='YES' and elgb_st='E'";
			}
			if (m1lvl.equalsIgnoreCase("ICNO")) {
				sql1 += " where ic_no in ('"+m1rptlvl+"')";
			}
			//System.out.println("getDOLetterData-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);			
			ResultSet rs = stmt.executeQuery();		
			 if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list);
			 }else{
		    	while(rs.next()){
		    		ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ic_no"));
					list.add(rs.getString("rk"));
					list.add(rs.getString("name"));
					list.add(rs.getString("present_appt"));
					list.add(rs.getString("present_unit"));
					list.add(rs.getString("parent_unit"));
					list.add(rs.getString("age"));
					list.add(rs.getString("part_d"));
					list.add(rs.getString("serv_days"));
					list.add(rs.getString("elgb_st"));
					li.add(list);
	 		    }
			 }
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("getDOLetterData-li-"+li);
		return li;
	}

	public ArrayList<ArrayList<String>> fpCORBaseData(HttpSession sessionA,String nPara) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String sql1="";
	    String hdCnd="";
	    String hdCnd0="";
	    String hdCnd1="";
	    String rsfmt="CR";
	    String rsfmtv="1";
	    String rsfmtvd="2";
	    String fmncnd="";
	    String lpidx="";
		try{	  
			conn = dataSource.getConnection();
			sql1 +=" select id,regt,(case when did='PAGEBG' then 1 when did='HLOGO' THEN 2 WHEN did='TITLE' THEN 3 ELSE 4 END ) as lp_idx,";
			sql1 +=" did,nval1,nval2,nval3,nval4,nval5,tab_idx,ord,mode,tabsgp,tabsgpidx from fp.fp_tb_cor_base where upper(regt)='"+nPara+"'";
			sql1 +=" order by lp_idx,tabsgpidx,tab_idx,ord";
			//System.out.println("fpCORBaseData-"+sql1);
		    PreparedStatement stmt = conn.prepareStatement(sql1);
		    ResultSet rs = stmt.executeQuery();   		   
		    if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list);
		    }else{
		    	while(rs.next()){
		        	ArrayList<String> list2 = new ArrayList<String>();
		        	lpidx=rs.getString("lp_idx");
		        	list2.add(rs.getString("id"));
		        	list2.add(rs.getString("regt"));
		        	list2.add(lpidx);
	 	    		list2.add(rs.getString("did"));
	 	    		list2.add(rs.getString("nval1"));
	 	    		list2.add(rs.getString("nval2"));
	 	    		list2.add(rs.getString("nval3"));
	 	    		list2.add(rs.getString("nval4"));
	 	    		list2.add(rs.getString("nval5"));
	 	    		list2.add(rs.getString("tab_idx"));
	 	    		list2.add(rs.getString("ord"));
	 	    		list2.add(rs.getString("mode"));
	 	    		list2.add(rs.getString("tabsgp"));
	 	    		list2.add(rs.getString("tabsgpidx"));     
	 	        	li.add(list2); 
	 		    }
		    }
	 	    rs.close();
	 	    stmt.close();
	 		conn.close();
	 	}catch(SQLException e){
	 		e.printStackTrace();
	 	}	
		System.out.println(li);
	 	return li;
	}
	
}