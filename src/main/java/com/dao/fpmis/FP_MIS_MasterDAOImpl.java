package com.dao.fpmis;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.fpmis.FP_MIS_Master_Model;
import com.models.fpmis.fp_tb_admin_control_model;
import com.models.fpmis.fp_tb_norbat_dtl_model;
import com.models.fpmis.fp_tb_proj_detl_model;
import com.persistance.util.HibernateUtil;

public class FP_MIS_MasterDAOImpl implements FP_MIS_MasterDAO {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private FP_MIS_TransactionDAO fpTr_Dao;

	public List<String> getMNHEncList(List<String> l, HttpSession s1) {
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

	public String updatefpheadcode(FP_MIS_Master_Model obj) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update FP_MIS_Master_Model set major_head=:major_head,minor_head=:minor_head,sub_head=:sub_head,head_desc=:head_desc,status=:status,data_upd_date=now(),data_upd_by=:data_upd_by,head_code=:head_code where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("major_head", obj.getMajor_head())
					.setString("minor_head", obj.getMinor_head()).setString("sub_head", obj.getSub_head())
					.setString("head_desc", obj.getHead_desc()).setString("status", obj.getStatus())
					.setString("data_upd_by", obj.getData_upd_by()).setString("head_code", obj.getHead_code())
					.setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
			tx.commit();
		} catch (Exception e) {
			msg = "Error - unable to perform action";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}

	public String RegdDataTransfer(String tr_head, String tr_level, String major_head, String minor_head,
			String sub_head, String head_desc, String status, String head_code, String username) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String nrSql = "";
			if (major_head.equals("-1")) {
				tr_level = "1";
				tr_head = head_code;
				major_head = head_code;
				// head_code="";
			} else if (minor_head.equals("-1")) {
				tr_level = "2";
				tr_head = major_head + ":" + head_code;
				minor_head = head_code;
				// head_code="";
			} else if (sub_head.equals("-1")) {
				tr_level = "3";
				tr_head = major_head + ":" + minor_head + ":" + head_code;
				sub_head = head_code;
				// head_code="";
			} else {
				tr_level = "4";
				tr_head = major_head + ":" + minor_head + ":" + sub_head + ":" + head_code;
				// head_code="";
			}

			nrSql = "insert into fp.fp_tb_head_mstr (tr_head,tr_level,major_head,minor_head,sub_head,head_desc,head_code,status,data_cr_date,data_cr_by,data_upd_date,data_upd_by) values ("
					+ "?,?,?,?,?,?,?,?,now(),?,now(),?)";

			PreparedStatement ps = conn.prepareStatement(nrSql);

			// ps.setString(1, tr_head + ":" + head_code);
			ps.setString(1, tr_head);
			// ps.setInt(2, (Integer.parseInt(tr_level) + 1));
			ps.setInt(2, (Integer.parseInt(tr_level)));
			if (Integer.parseInt(tr_level) >= 1) {
				ps.setString(3, major_head);
			} else {
				ps.setString(3, "");
			}
			if (Integer.parseInt(tr_level) >= 2) {
				ps.setString(4, minor_head);
			} else {
				ps.setString(4, "");
			}
			if (Integer.parseInt(tr_level) >= 3) {
				ps.setString(5, sub_head);
			} else {
				ps.setString(5, "");
			}
			ps.setString(6, head_desc);
			ps.setString(7, head_code);
			ps.setString(8, status);
			ps.setString(9, username);
			ps.setString(10, username);
			ps.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ArrayList<String>> getSearchHeadMaster(String head_code1, String head_desc1, String major_head1,
			String minor_head1, String sub_head11) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!major_head1.equals("")) {

				qry += " major_head  = ? ";
			}
			if (!minor_head1.equals("")) {
				qry += " minor_head  = ? ";
			}
			if (!major_head1.equals("") || !minor_head1.equals("")) {
				q = "select id,head_code,head_desc,status,tr_head,tr_level,major_head,minor_head,sub_head,major_head||':'||minor_head||':'||sub_head s_head from fp.fp_tb_head_mstr where "
						+ qry + " order by tr_head";
			} else {
				q = "select id,head_code,head_desc,status,tr_head,tr_level,major_head,minor_head,sub_head,major_head||':'||minor_head||':'||sub_head s_head from fp.fp_tb_head_mstr order by tr_head";
			}

			stmt = conn.prepareStatement(q);
			if (!qry.equals("")) {
				int j = 1;
				if (!major_head1.equals("")) {

					stmt.setString(j, major_head1);
					j += 1;
				}
				if (!minor_head1.equals("")) {
					stmt.setString(j, minor_head1);
					j += 1;
				}
			}
			ResultSet rs = stmt.executeQuery();

			int idd = 0;
			String f = "";
			String f1 = "";
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				idd = rs.getInt("id");
				list.add(rs.getString("head_code"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("status"));
				list.add(rs.getString("tr_head"));
				list.add(rs.getString("tr_level"));
				f = "";
				f1 = "";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData(" + idd + ",'"
						+ rs.getString("head_code") + "','" + rs.getString("head_desc") + "','"
						+ rs.getString("major_head") + "','" + rs.getString("minor_head") + "','"
						+ rs.getString("sub_head") + "','" + rs.getString("status") + "')}else{ return false;}\"";
				f = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Record ?') ){deleteData("
						+ idd + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				list.add(f);
				list.add(f1);
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

	public @ResponseBody ArrayList<ArrayList<String>> FindDomainList(String enc, HttpSession s1, String nParaValue) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split(":");
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";

			sql1 = " select id,domainid,codevalue,label,label_s,disp_order from fp.fp_domain_values";
			if (nPara[0].length() > 0) {
				sql1 = sql1 + " where domainid='" + nPara[0].toUpperCase() + "'";
			}
			if (nPara[1].length() <= 0) {
				sql1 = sql1 + " order by domainid,codevalue,disp_order";
			} else {
				String[] nOdrBy = nPara[1].split("#");
				sql1 = sql1 + " order by ";
				for (int i = 0; i < nOdrBy.length; i++) {
					if (i > 1) {
						sql1 = sql1 + ",";
					}
					sql1 = sql1 + nOdrBy[i];
				}
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));
					list.add(rs.getString("domainid"));
					list.add(rs.getString("codevalue"));
					list.add(rs.getString("label"));
					list.add(rs.getString("label_s"));
					list.add(rs.getString("disp_order"));
					li.add(list);
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

	public String UnitDataTransfer(String sus_no, String form_code_control, String unit_name, String status_sus_no,
			String remarks, String hq_type, String username,String psus_no) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			// String fp_user = "fp_dte";
			String nrSql = "";
			String sq1 = "";
			String sus_gen = "";
			nrSql = "insert into fp.fp_tb_norbat_dtl (sus_no,form_code_control,unit_name,status_sus_no,remarks,hq_type,psus_no) values('"
					+ sus_no + "','" + form_code_control + "','" + unit_name + "','" + status_sus_no + "','" + remarks
					+ "','" + hq_type + "','"+ psus_no +"')";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updateUnitProfilee(fp_tb_norbat_dtl_model obj) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update fp_tb_norbat_dtl_model set form_code_control=:form_code_control,unit_name=:unit_name,status_sus_no=:status_sus_no,remarks=:remarks,hq_type=:hq_type where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("form_code_control", obj.getForm_code_control())
					.setString("unit_name", obj.getUnit_name()).setString("status_sus_no", obj.getStatus_sus_no())
					.setString("remarks", obj.getRemarks()).setString("hq_type", obj.getHq_type());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}

	public boolean createProjection(fp_tb_proj_detl_model m) {
		String list = fpTr_Dao.nGetAdmFinYear("CPY");
		String fileYr = list.substring(2, 4);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sql = "insert into fp.fp_tb_proj_detl" + fileYr
					+ " (form_code_control,est_type,remarks,data_upd_by,data_upd_date,amount,proj_amt,sus_no,tr_head) "
					+ "values(?,'PRJ',?,?,now(),?,?,?,?)";
			if (checkProjectionExists(m.getSus_no(), "PRJ", m.getTr_head())) {
				sql = "update fp.fp_tb_proj_detl" + fileYr
						+ " set form_code_control=?,est_type='PRJ',remarks=?,data_upd_by=?,data_upd_date=now(),amount=?,proj_amt=? where sus_no=? and tr_head=?";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, m.getForm_code_control());
			stmt.setString(2, m.getRemarks());
			stmt.setString(3, m.getData_upd_by());
			stmt.setFloat(4, m.getAmount());
			stmt.setFloat(5, m.getAmount());
			stmt.setString(6, m.getSus_no());
			stmt.setString(7, m.getTr_head());
			stmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveAdminControl(fp_tb_admin_control_model m) {
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sql = "insert into fp.fp_tb_adm_control (est_type,date_from,date_to,fin_year,data_upd_by,data_upd_date,status) "
					+ "values(?,?,?,?,?,now(),?) on conflict (est_type) do "
					+ "update set date_from=Excluded.date_from,date_to=Excluded.date_to,fin_year=Excluded.fin_year,"
					+ "data_upd_by=Excluded.data_upd_by,data_upd_date=Excluded.data_upd_date,status=Excluded.status";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getEst_type());
			stmt.setDate(2, new java.sql.Date(m.getDate_from().getTime()));
			stmt.setDate(3, new java.sql.Date(m.getDate_to().getTime()));
			stmt.setString(4, m.getFin_year());
			stmt.setString(5, m.getData_upd_by());
			stmt.setString(6, m.getStatus());
			stmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isWindowOpened(String est_type) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select dv.codevalue from fp.fp_tb_adm_control ac join fp.fp_domain_values dv"
					+ " on ac.est_type = dv.codevalue and now()::date between ac.date_from and ac.date_to"
					+ " and UPPER(dv.codevalue)=UPPER(?) and status='1'";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, est_type);
			ResultSet rs = stmt.executeQuery();
			conn.close();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<ArrayList<String>> getSearchCDAFunds(String fp_finYr1, String roleSusNo) {
		String fileYr = fp_finYr1.substring(2, 4);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select id as exp_id,tr_head_to,(select head_desc from fp.fp_tv_head_dtl b where b.tr_head=a.tr_head_to) as head_desc,";
			q += " sum(exp_amt) as exp_amt, sum(fw.fwd_amt) as fwd_amt,sum(bk.book_amt) as book_amt,max(fw.fwd_date) as fwd_date";
			q += " from ( ";
						
			q += " select min(id) as id,pid,tr_head_to,sum(exp_amt) as exp_amt,max(pd) as pd from ("; 
			q += " select id,(case when pid=1 then id else pid end) as pid,tr_head_to,exp_amt,to_char(period,'dd-mm-yyyy hh:mm:ss') as pd";
			q += " from fp.fp_tb_trans_detl"+fileYr+" where tr_type in ('EXP','GST','OTH','EDT') and to_sus_no=?";
			q += " ) b group by pid,tr_head_to";
			
			q += " ) a  left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,";
			q += " to_char(max(fwd_date::Date),'dd-MM-yyyy') as fwd_date  from fp.fp_tb_cda_detl"+fileYr;
			q += " c group by exp_id) fw on fw.exp_id=a.id  left join (select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt";
			q += " from fp.fp_tb_cda_detl"+fileYr+" c group by exp_id) bk on bk.exp_id=a.id group by a.id,a.tr_head_to order by a.id";
			q="";
			
			q += " select a.id as exp_id,alt.fr_sus_no,o1.unit_name as hlbh,alt.alt_date,alt.alt_remarks, a.to_sus_no,";
			q += " o2.unit_name as bh_name,a.tr_head_to,split_part(a.tr_head_to,':',4) as code_head,alt.alt_amt,(select head_desc from fp.fp_tv_head_dtl b ";
			q += " where b.tr_head=a.tr_head_to) as head_desc, exp_amt as exp_amt, exp_remarks,fw.fwd_amt as fwd_amt,";
			q += " bk.book_amt as book_amt,fw.fwd_date as fwd_date,fw.fwd_ref as fwd_ref,bk.bkd_ref as bkd_ref,";
			q += " bk.bkd_remarks as bkd_remarks,pd as exp_date from (  select min(id) as id,pid,max(to_sus_no) as to_sus_no,";
			q += " tr_head_to,sum(exp_amt) as exp_amt,to_char(max(pd::Date),'dd-MM-yyyy') as pd,max(to_remarks) as exp_remarks from ( ";
			q += " select id,to_sus_no,(case when pid=1 then id else pid end) as pid,tr_head_to,exp_amt,";
			q += " to_char(period,'dd-mm-yyyy hh:mm:ss') as pd,to_remarks from fp.fp_tb_trans_detl"+fileYr;
			q += " where tr_type in ('EXP','GST','OTH','EDT') ";
			q += " and to_sus_no=? ";
			q += " ) b group by pid,tr_head_to ) a  left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,";
			q += " to_char(max(fwd_date::Date),'dd-MM-yyyy') as fwd_date,max(fwd_ref) as fwd_ref  ";
			q += " from fp.fp_tb_cda_detl"+fileYr+" c group by exp_id) fw on fw.exp_id=a.id  left join (";
			q += " select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt,max(bkd_ref) as bkd_ref,";
			q += " max(bkd_remarks) as bkd_remarks from fp.fp_tb_cda_detl"+fileYr+" c group by exp_id) bk on bk.exp_id=a.id";			
			q += " left join (select id,fr_sus_no,to_sus_no,tr_head_to,coalesce(sum(c.exp_amt),0) as alt_amt,";
			q += " to_char(max(period::Date),'dd-MM-yyyy') as alt_date,max(to_remarks) as alt_remarks  ";
			q += " from fp.fp_tb_funds_detl"+fileYr+" c ";
			q += " where to_sus_no=?";
			q += " and (fr_sus_no,fund_lvl) in (select fr_sus_no,max(fund_lvl) ";
			q += " from fp.fp_tb_funds_detl"+fileYr+" where to_sus_no=? ";
			
			
			
			
			q += " group by fr_sus_no) group by id,fr_sus_no,to_sus_no,tr_head_to) alt on alt.tr_head_to=a.tr_head_to";
			q += " left join fp.fp_tv_orbat_dtl o1 on alt.fr_sus_no=o1.sus_no";
			q += " left join fp.fp_tv_orbat_dtl o2 on a.to_sus_no=o2.sus_no";
			q += " order by o1.hq_type,alt.fr_sus_no,a.id,o2.hq_type,a.to_sus_no";
		
			stmt = conn.prepareStatement(q);			
			stmt.setString(1, roleSusNo);
			stmt.setString(2, roleSusNo);
			stmt.setString(3, roleSusNo);
			System.out.println("getSearchCDAFunds-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			int idd = 0;

			String f = "";
			String f1 = "";
			String fwd_cda_btn = "";
			String book_cda_btn = "";
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				idd = rs.getInt("exp_id");
				list.add(idd+"");
				list.add(rs.getString("fr_sus_no"));
				list.add(rs.getString("hlbh"));
				list.add(rs.getString("alt_date"));
				list.add(rs.getString("alt_remarks"));
				list.add(rs.getString("to_sus_no"));
				list.add(rs.getString("bh_name"));
				list.add(rs.getString("tr_head_to"));
				list.add(rs.getString("alt_amt"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("exp_amt")); //10
				list.add(rs.getString("exp_remarks"));
				list.add(rs.getString("fwd_amt"));
				list.add(rs.getString("book_amt"));
				list.add(rs.getString("fwd_date"));
				list.add(rs.getString("fwd_ref"));
				list.add(rs.getString("bkd_ref"));
				list.add(rs.getString("bkd_remarks"));
				list.add(rs.getString("exp_date"));
				list.add(rs.getString("code_head"));	//19				
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

	public ArrayList<ArrayList<String>> getSearchCDAFunds1(String fp_finYr1, String roleSusNo) {
		String fileYr = fp_finYr1.substring(2, 4);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select a.id as exp_id, a.tr_head_to,(select coalesce(sum(c.exp_amt),0) as exp_amt from fp.fp_tb_trans_detl"
					+ fileYr + " c "
					+ " where c.to_sus_no=a.to_sus_no and c.tr_head_to=a.tr_head_to) as exp_amt,(select head_desc from fp.fp_tv_head_dtl b where b.tr_head=a.tr_head_to) as head_desc,"
					+ " (select coalesce(sum(c.fwd_amt),0) as fwd_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as fwd_amt,"
					+ " (select to_char(max(fwd_date::Date),'dd-MM-yyyy') from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as fwd_date,"
					+ " (select coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as book_amt from fp.fp_tb_trans_detl" + fileYr
					+ " a " + "where tr_type='EXP' and to_sus_no=? order by 1";

			q = " select min(id) as exp_id,tr_head_to,(select head_desc from fp.fp_tv_head_dtl b where b.tr_head=a.tr_head_to) as head_desc,";
			q += " sum(exp_amt) as exp_amt,sum(fw.fwd_amt) as fwd_amt,sum(bk.book_amt) as book_amt,max(fw.fwd_date) as fwd_date from fp.fp_tb_trans_detl"
					+ fileYr + " a ";
			q += " left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,to_char(max(fwd_date::Date),'dd-MM-yyyy') as fwd_date from fp.fp_tb_cda_detl"
					+ fileYr + " c group by exp_id) fw on fw.exp_id=a.id";
			q += " left join (select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c group by exp_id) bk on bk.exp_id=a.id";
			q += " where tr_type in ('EXP','GST','OTH','EDT') and to_sus_no=? ";
			q += " group by tr_head_to,to_char(data_cr_date::timestamp,'dd-mm-yyyy hh:mm')";
			q += " order by exp_id";
			
			q="";
			
			q += " select n.exp_id,n.tr_head_to,n.head_desc,n.exp_amt, fw.fwd_amt,fw.fwd_date,bk.book_amt from (";
			q += " select tr_head_to,id as exp_id,(select head_desc from fp.fp_tv_head_dtl b1 where b1.tr_head=b.tr_head_to) ";
			q += " as head_desc,sum(exp_amt) as exp_amt from (";	
			q += " select tr_head_to,(case when tr_type='EXP' then id else pid end) as id,exp_amt as exp_amt from fp.fp_tb_trans_detl" + fileYr +" a";
			q += " where tr_type in ('EXP','GST','OTH','EDT') and to_sus_no=?";
			q += " ) b group by tr_head_to,id ) n"; 
			q += " left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,to_char(max(fwd_date::Date),'dd-MM-yyyy') ";
			q += " as fwd_date from fp.fp_tb_cda_detl" + fileYr +" c group by exp_id) fw on fw.exp_id=n.exp_id ";
			q += " left join (select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr+" c ";
			q += " group by exp_id) bk on bk.exp_id=n.exp_id"; 
			
			
			
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			System.out.println("getSearchCDAFunds1-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			int idd = 0;
			String f = "";
			String f1 = "";
			String fwd_cda_btn = "";
			String book_cda_btn = "";
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				idd = rs.getInt("exp_id");
				list.add(rs.getString("tr_head_to"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("exp_amt"));
				list.add(rs.getString("fwd_amt"));
				list.add(rs.getString("book_amt"));
				list.add(rs.getString("fwd_date"));

				/*fwd_cda_btn = "onclick=\"  if (confirm('Are you sure you want to Fwd CDA?') ){editData(" + idd + ",'"
						+ rs.getString("tr_head_to") + "'," + rs.getFloat("exp_amt") + "," + rs.getFloat("fwd_amt")
						+ "," + rs.getFloat("book_amt") + ")}else{ return false;}\"";
				f = "<input type='button' class='btn btn-primary btn-sm' value='Bill Fwd to CDA' style='background-color: blue;color:yellow;font-size:1vw;border-radius:7px;margin-bottom:5px'"
						+ fwd_cda_btn + " title='Bill Fwd to CDA'/>";
				list.add(f);

				if (rs.getFloat("fwd_amt") != 0 && rs.getFloat("fwd_amt") > rs.getFloat("book_amt")) {
					book_cda_btn = "onclick=\"  if (confirm('Are you sure you want to Book CDA') ){BookedData(" + idd
							+ ")}else{ return false;}\"";
					f1 = "&nbsp;&nbsp;<input type='button' class='btn btn-primary btn-sm' value='Bill Booked by CDA' style='background-color: yellow;color:blue;font-size:1vw;border-radius:7px;margin-bottom:5px' "
							+ book_cda_btn + " title='Bill Booked by CDA'/>";
				} else
					f1 = "";

				list.add(f1);
				alist.add(list);*/
				
				
				String disabled="";
				if(rs.getDouble("fwd_amt") >= rs.getDouble("exp_amt")) {
					disabled = "disabled";
				}

				fwd_cda_btn = "onclick=\"  if (confirm('Are you sure you want to Fwd CDA?') ){editData(" + idd + ",'"
						+ rs.getString("tr_head_to") + "'," + rs.getDouble("exp_amt") + "," + rs.getDouble("fwd_amt")
						+ "," + rs.getDouble("book_amt") + ")}else{ return false;}\"";
				f = "<input type='button' "+disabled+" class='btn btn-primary btn-sm nActionBtn' value='Bill Fwd to CDA' style='background-color: blue;color:yellow;font-size:1vw;border-radius:7px;margin-bottom:5px'"
						+ fwd_cda_btn + " title='Bill Fwd to CDA'/>";
				list.add(f);
				
				String del_cda_btn = "onclick=\"  if (confirm('Are you sure you want to Delete Exenditure?') ){delData(" + idd + ",'"
						+ rs.getString("tr_head_to") + "'," + rs.getDouble("exp_amt") + "," + rs.getDouble("fwd_amt")
						+ "," + rs.getDouble("book_amt") + ")}else{ return false;}\"";
				String f2 = "<input type='button' class='btn btn-primary btn-sm' value='Delete Expenditure' style='background-color:RED;color:yellow;font-size:1vw;border-radius:7px;margin-bottom:5px'"
						+ del_cda_btn + " title='Delete Expenditure'/>";
				list.add(f2);

				if (rs.getDouble("fwd_amt") != 0 && rs.getDouble("fwd_amt") > rs.getDouble("book_amt")) {
					book_cda_btn = "onclick=\"  if (confirm('Are you sure you want to Book CDA') ){BookedData(" + idd
							+ ")}else{ return false;}\"";
					f1 = "&nbsp;&nbsp;<input type='button' class='btn btn-primary btn-sm' value='Bill Booked by CDA' style='background-color: yellow;color:blue;font-size:1vw;border-radius:7px;margin-bottom:5px' "
							+ book_cda_btn + " title='Bill Booked by CDA'/>";
				} else
					f1 = "";

				list.add(f1);
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

	public List<String> getSearchCDABook(String id, String roleSusNo) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select sum(c.fwd_amt) as fwd_amt,c.fwd_ref,c.fwd_date, sum(c.bkd_amt) as book_amt,a.id as exp_id,c.id as id from fp.fp_tb_trans_detl20 a,fp_tv_head_dtl b, fp.fp_tb_cda_detl20 c where a.tr_type='EXP' and a.to_sus_no='"
					+ roleSusNo + "' and a.id=" + id
					+ " and a.to_sus_no=c.sus_no and a.tr_head_to=b.tr_head group by a.id,c.id, a.tr_head_to,b.head_desc";
			PreparedStatement stmt = conn.prepareStatement(sq1);

			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("exp_id");
				str1 = str1 + "--" + rs.getString("id");
				str1 = str1 + "--" + rs.getString("fwd_amt");
				str1 = str1 + "--" + rs.getString("fwd_ref");
				str1 = str1 + "--" + rs.getString("fwd_date");
				str1 = str1 + "--" + rs.getString("book_amt");
				str1 = str1 + ",";
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

	public String FwdDataTransfer(String sus_no, String fwd_amt, String fwd_ref, String fwd_date, String fwdid2,
			String username, String tr_head_to12, String finyr2,String unitcd2,String cdaunit2) {
		Connection conn = null;
		String fileYr = finyr2.substring(2, 4);
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmtd = null;
			Double exp_amt = 0.00;
			Double fwd_amtd = 0.00;
			Double bal_amt = 0.00;
			/*String query = "select coalesce(a.exp_amt,0) exp_amt,(select coalesce(sum(c.fwd_amt),0) as fwd_amt from fp.fp_tb_cda_detl"
					+ fileYr + " c" + " where c.sus_no=a.to_sus_no and c.exp_id=a.id) as fwd_amt,"
					+ "(select coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as book_amt from fp.fp_tb_trans_detl" + fileYr
					+ " a where tr_type='EXP' and " + "to_sus_no=? and a.id=? ";

			query = "select coalesce(a.exp_amt,0) exp_amt,(select coalesce(sum(c.fwd_amt),0) as fwd_amt from fp.fp_tb_cda_detl"
					+ fileYr + " c" + " where c.sus_no=a.to_sus_no and c.exp_id=a.id) as fwd_amt,"
					+ "(select coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as book_amt from ("
					+ " select min(id) as id,to_sus_no,tr_head_to,to_char(data_cr_date::timestamp,'dd-mm-yyyy hh:mm:ss') as exp_date,sum(exp_amt) as exp_amt from fp.fp_tb_trans_detl" + fileYr
					+ "	a where tr_type in ('EXP','GST','OTH','EDT') and to_sus_no=?"  
					+ "	group by to_sus_no,tr_head_to,to_char(data_cr_date::timestamp,'dd-mm-yyyy hh:mm:ss'))" 
					+ " a where a.id=? ";   

			
			String query = " select id as exp_id,tr_head_to,sum(exp_amt) as exp_amt, sum(fw.fwd_amt) as fwd_amt,sum(bk.book_amt) as book_amt,";
			query += " max(fw.fwd_date) as fwd_date,(sum(exp_amt::numeric)-sum(fw.fwd_amt)) as bal_amt  from ( select id,tr_head_to,sum(exp_amt) as exp_amt from ( ";
			query += " select id,tr_head_to,exp_amt from fp.fp_tb_trans_detl" + fileYr +" where tr_type in ('EXP') and ";
			query += " to_sus_no=? ";
			query += " union all  select min(id)-1 as id,tr_head_to,sum(exp_amt) as exp_amt from fp.fp_tb_trans_detl" + fileYr;
			query += " where tr_type in ('GST','OTH','EDT') and ";
			query += " to_sus_no=? ";
			query += " group by tr_head_to  ) b group by id,tr_head_to ) a  ";
			query += " left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,to_char(max(fwd_date::Date),'dd-MM-yyyy') as fwd_date";
			query += " from fp.fp_tb_cda_detl" + fileYr +" c group by exp_id) fw on fw.exp_id=a.id  ";
			query += " left join (select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt  from fp.fp_tb_cda_detl" + fileYr +" c ";
			query += " group by exp_id) bk on bk.exp_id=a.id  group by a.id,a.tr_head_to having a.id=? order by a.id";
						*/
			String query = " select id as exp_id,tr_head_to,(select head_desc from fp.fp_tv_head_dtl b where b.tr_head=a.tr_head_to) as head_desc,";
			query += " sum(exp_amt) as exp_amt, sum(fw.fwd_amt) as fwd_amt,sum(bk.book_amt) as book_amt,max(fw.fwd_date) as fwd_date";
			query += " ,(sum(exp_amt::numeric)-sum(fw.fwd_amt)) as bal_amt from ( ";
						
			query += " select min(id) as id,pid,tr_head_to,sum(exp_amt) as exp_amt,max(pd) as pd from ("; 
			query += " select id,(case when pid=1 then id else pid end) as pid,tr_head_to,exp_amt,to_char(period,'dd-mm-yyyy hh:mm:ss') as pd";
			query += " from fp.fp_tb_trans_detl"+fileYr+" where tr_type in ('EXP','GST','OTH','EDT') and to_sus_no=?";
			query += " ) b group by pid,tr_head_to";
			
			query += " ) a  left join (select exp_id,coalesce(sum(c.fwd_amt),0) as fwd_amt,";
			query += " to_char(max(fwd_date::Date),'dd-MM-yyyy') as fwd_date  from fp.fp_tb_cda_detl"+fileYr;
			query += " c group by exp_id) fw on fw.exp_id=a.id  left join (select exp_id,coalesce(sum(c.bkd_amt),0) as book_amt";
			query += " from fp.fp_tb_cda_detl"+fileYr+" c group by exp_id) bk on bk.exp_id=a.id group by a.id,a.tr_head_to having a.id=? order by a.id";
			
			
			
			
			
			stmtd = conn.prepareStatement(query);
			System.out.println("query-"+stmtd);

			stmtd.setString(1, sus_no);
			//stmtd.setString(2, sus_no);
			stmtd.setInt(2, Integer.parseInt(fwdid2));
			ResultSet rsd = stmtd.executeQuery();

			while (rsd.next()) {
				fwd_amtd += rsd.getDouble("fwd_amt");
				exp_amt += rsd.getDouble("exp_amt");
				bal_amt += rsd.getDouble("bal_amt");
			}

			//bal_amt = exp_amt - fwd_amtd;
			//bal_amt = (double) Math.round(bal_amt);
			//bal_amt = new BigDecimal(bal_amt).setScale(2,RoundingMode.HALF_UP).doubleValue();
			//if (bal_amt > Double.parseDouble(fwd_amt)) {
			if (bal_amt >= 0) {
				PreparedStatement stmt1 = null;
				String form_code_control = "";
				String fmn_sql = "";
				fmn_sql = "SELECT distinct form_code_control FROM fp.fp_tv_orbat_dtl where sus_no=?";
				stmt1 = conn.prepareStatement(fmn_sql);
				stmt1.setString(1, sus_no);
				ResultSet rs = stmt1.executeQuery();
				while (rs.next()) {
					form_code_control = rs.getString("form_code_control");
				}

				String nrSql = "insert into fp.fp_tb_cda_detl" + fileYr
						+ " (exp_id,sus_no,form_code_control,fwd_amt,fwd_ref,fwd_by,fwd_date,data_upd_by,data_udp_date,tr_head,sus_unit_code,sus_cda_code) values(?,?,?,?,?,?,?,?,now(),?,?,?)";

				PreparedStatement stmt = conn.prepareStatement(nrSql);
				stmt.setInt(1, Integer.parseInt(fwdid2));
				stmt.setString(2, sus_no);
				stmt.setString(3, form_code_control);
				stmt.setDouble(4, Double.parseDouble(fwd_amt));
				stmt.setString(5, fwd_ref);
				stmt.setString(6, username);
				stmt.setDate(7, java.sql.Date.valueOf(fwd_date));
				stmt.setString(8, username);
				stmt.setString(9, tr_head_to12);
				stmt.setString(10, unitcd2);
				stmt.setString(11, cdaunit2);
				stmt.executeUpdate();
				conn.close();
				return "Amount Fwd to CDA Successfully";
			} else {
				return "You can't exceed the expenditure amount \nBalance Amount to fwd Rs " + bal_amt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error - unable to perform action";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error - unable to perform action1";
		}
	}

	public String FwdDataTransfer1(String sus_no, String fwd_amt, String fwd_ref, String fwd_date, String fwdid2,
			String username, String tr_head_to12, String finyr2) {
		Connection conn = null;
		String fileYr = finyr2.substring(2, 4);

		try {
			conn = dataSource.getConnection();

			PreparedStatement stmtd = null;

			float exp_amt = 0;
			float fwd_amtd = 0;

			String query = "select (select coalesce(sum(c.exp_amt),0) as exp_amt from fp.fp_tb_trans_detl" + fileYr
					+ " c where c.to_sus_no=a.to_sus_no and c.tr_head_to=a.tr_head_to) as exp_amt,(select coalesce(sum(c.fwd_amt),0) as fwd_amt from fp.fp_tb_cda_detl"
					+ fileYr + " c" + " where c.sus_no=a.to_sus_no and c.exp_id=a.id) as fwd_amt,"
					+ "(select coalesce(sum(c.bkd_amt),0) as book_amt from fp.fp_tb_cda_detl" + fileYr
					+ " c where c.sus_no=a.to_sus_no and c.exp_id=a.id) as book_amt from fp.fp_tb_trans_detl" + fileYr
					+ " a where tr_type='EXP' and " + "to_sus_no=? and a.id=? ";

			stmtd = conn.prepareStatement(query);
			stmtd.setString(1, sus_no);
			stmtd.setInt(2, Integer.parseInt(fwdid2));
			ResultSet rsd = stmtd.executeQuery();

			while (rsd.next()) {
				fwd_amtd += rsd.getFloat("fwd_amt");
				exp_amt += rsd.getFloat("exp_amt");
			}

			float bal_amt = exp_amt - fwd_amtd;

			if (bal_amt >= Float.parseFloat(fwd_amt)) {

				PreparedStatement stmt1 = null;
				String form_code_control = "";
				String fmn_sql = "";
				fmn_sql = "SELECT distinct form_code_control FROM fp.fp_tv_orbat_dtl where sus_no=?";
				stmt1 = conn.prepareStatement(fmn_sql);
				stmt1.setString(1, sus_no);
				ResultSet rs = stmt1.executeQuery();
				while (rs.next()) {
					form_code_control = rs.getString("form_code_control");
				}

				String nrSql = "insert into fp.fp_tb_cda_detl" + fileYr
						+ " (exp_id,sus_no,form_code_control,fwd_amt,fwd_ref,fwd_by,fwd_date,data_upd_by,data_udp_date,tr_head) values(?,?,?,?,?,?,?,?,now(),?)";

				PreparedStatement stmt = conn.prepareStatement(nrSql);
				stmt.setInt(1, Integer.parseInt(fwdid2));
				stmt.setString(2, sus_no);
				stmt.setString(3, form_code_control);
				// stmt.setInt(4, Integer.parseInt(fwd_amt));
				stmt.setFloat(4, Float.parseFloat(fwd_amt));
				stmt.setString(5, fwd_ref);
				stmt.setString(6, username);
				stmt.setDate(7, java.sql.Date.valueOf(fwd_date));
				stmt.setString(8, username);
				stmt.setString(9, tr_head_to12);
				stmt.executeUpdate();
				conn.close();
				return "Amount Fwd to CDA Successfully";
			} else {
				return "You can't exceed the expenditure amount \nBalance Amount to fwd Rs " + bal_amt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error - unable to perform action";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error - unable to perform action1";
		}
	}

	public String BkdDataTransfer(String sus_no, Double bkd_amt, String bkd_ref, String bkd_remarks, String bkd_date,
			int exp_id, int bkd_id, String username) {

		Connection conn = null;
		String msg = "";
		try {

			conn = dataSource.getConnection();
			String finYr = fpTr_Dao.nGetAdmFinYear("CFY");
			finYr = finYr.substring(2, 4);

			PreparedStatement stmt = null;

			String sql = "select c.id,coalesce(c.fwd_amt,0) fwd_amt,coalesce(c.bkd_amt,0) bkd_amt,c.fwd_date::date,c.bkd_date::date,t.tr_head from fp.fp_tb_cda_detl"
					+ finYr + " c join fp.fp_tb_trans_detl" + finYr
					+ " t on t.id = c.exp_id where c.exp_id=? and c.sus_no=? and c.id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, exp_id);
			stmt.setString(2, sus_no);
			stmt.setInt(3, bkd_id);

			Double fwd_amt = 0.0;
			Double bal_amt = 0.0;
			Double bkd_amt1 = 0.0;
			String fwd_date1 = null;
			String tr_head = null;
			bkd_id = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				bkd_id = rs.getInt("id");
				fwd_amt = rs.getDouble("fwd_amt");
				bkd_amt1 = rs.getDouble("bkd_amt");
				fwd_date1 = rs.getString("fwd_date");
				tr_head = rs.getString("tr_head");
			}

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date bk_date = formatter.parse(bkd_date);
			java.util.Date fwd_date = formatter.parse(fwd_date1);

			if (bkd_id == 0) {
				msg = "Error - unable to peform action";
				return msg;
			} else if (fwd_amt == 0.0) {
				msg = "Please fwd the bill to CDA";
				return msg;
			} else if (fwd_date.after(bk_date)) {
				msg = "Booking date should be greater than fwd date";
				return msg;
			} else if (fwd_amt < bkd_amt + bkd_amt1) {
				bal_amt = fwd_amt - bkd_amt;
				msg = "You can't exceed the bill amount\nBalance Amount to book for the selected bill Rs "
						+ bal_amt.toString();
				return msg;
			}

			bkd_amt += bkd_amt1;

			sql = "update fp.fp_tb_cda_detl" + finYr
					+ " set bkd_amt = ?,bkd_ref=?,bkd_by=?,bkd_remarks=?,bkd_date=?,tr_head=?,data_upd_by=?,data_udp_date=now() where id=? and exp_id=? and sus_no=?";

			stmt = null;

			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, bkd_amt);
			stmt.setString(2, bkd_ref);
			stmt.setString(3, username);
			stmt.setString(4, bkd_remarks);
			stmt.setDate(5, new java.sql.Date(bk_date.getTime()));
			stmt.setString(6, tr_head);
			stmt.setString(7, username);
			stmt.setInt(8, bkd_id);
			stmt.setInt(9, exp_id);
			stmt.setString(10, sus_no);
			int hasUpdated = stmt.executeUpdate();
			rs.close();
			conn.close();
			msg = hasUpdated > 0 ? "Amount successfully booked" : "Error - unable to perform action";

		} catch (Exception e) {
			msg = "Error - unable to peform action";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return msg;
	}

	public String BkdDataTransfer1(String sus_no, Double bkd_amt, String bkd_ref, String bkd_remarks, String bkd_date,
			int exp_id, int bkd_id, String username) {

		Connection conn = null;
		String msg = "";
		try {

			conn = dataSource.getConnection();
			String finYr = fpTr_Dao.nGetAdmFinYear("CFY");
			finYr = finYr.substring(2, 4);

			PreparedStatement stmt = null;

			String sql = "select c.id,coalesce(c.fwd_amt,0) fwd_amt,coalesce(c.bkd_amt,0) bkd_amt,c.fwd_date::date,c.bkd_date::date,t.tr_head from fp.fp_tb_cda_detl"
					+ finYr + " c join fp.fp_tb_trans_detl" + finYr
					+ " t on t.id = c.exp_id where c.exp_id=? and c.sus_no=? and c.id=?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, exp_id);
			stmt.setString(2, sus_no);
			stmt.setInt(3, bkd_id);

			Double fwd_amt = 0.0;
			Double bal_amt = 0.0;
			Double bkd_amt1 = 0.0;
			String fwd_date1 = null;
			String tr_head = null;
			bkd_id = 0;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				bkd_id = rs.getInt("id");
				fwd_amt = rs.getDouble("fwd_amt");
				bkd_amt1 = rs.getDouble("bkd_amt");
				fwd_date1 = rs.getString("fwd_date");
				tr_head = rs.getString("tr_head");
			}

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date bk_date = formatter.parse(bkd_date);
			java.util.Date fwd_date = formatter.parse(fwd_date1);

			if (bkd_id == 0) {
				msg = "Error - unable to peform action";
				return msg;
			} else if (fwd_amt == 0.0) {
				msg = "Please fwd the bill to CDA";
				return msg;
			} else if (fwd_date.after(bk_date)) {
				msg = "Booking date should be greater than fwd date";
				return msg;
			} else if (fwd_amt < bkd_amt + bkd_amt1) {
				bal_amt = fwd_amt - bkd_amt;
				msg = "You can't exceed the bill amount\nBalance Amount to book for the selected bill Rs "
						+ bal_amt.toString();
				return msg;
			}

			bkd_amt += bkd_amt1;

			sql = "update fp.fp_tb_cda_detl" + finYr
					+ " set bkd_amt = ?,bkd_ref=?,bkd_by=?,bkd_remarks=?,bkd_date=?,tr_head=?,data_upd_by=?,data_udp_date=now() where id=? and exp_id=? and sus_no=?";

			stmt = null;

			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, bkd_amt);
			stmt.setString(2, bkd_ref);
			stmt.setString(3, username);
			stmt.setString(4, bkd_remarks);
			stmt.setDate(5, new java.sql.Date(bk_date.getTime()));
			stmt.setString(6, tr_head);
			stmt.setString(7, username);
			stmt.setInt(8, bkd_id);
			stmt.setInt(9, exp_id);
			stmt.setString(10, sus_no);
			int hasUpdated = stmt.executeUpdate();
			rs.close();
			conn.close();
			msg = hasUpdated > 0 ? "Amount successfully booked" : "Error - unable to perform action";

		} catch (Exception e) {
			msg = "Error - unable to peform action";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return msg;
	}

	public boolean checkProjectionExists(String sus_no, String est_type, String tr_head) {
		Connection conn = null;
		Boolean isExists = true;

		String m1_tryear = fpTr_Dao.nGetAdmFinYear("CPY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT sus_no FROM fp.fp_tb_proj_detl" + fileYr
					+ " WHERE sus_no=? AND tr_head = ? and est_type = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, tr_head);
			stmt.setString(3, est_type);

			ResultSet rs = stmt.executeQuery();
			isExists = rs.next() ? true : false;

			rs.close();
			conn.close();
			return isExists;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public String DomDataTransfer(String domainid, String codevalue, String label, String lable_s, String disp_order) {
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			int srno = 0;
			String fp_user = "fp_user";
			String nrSql = "";

			nrSql = "insert into fp.fp_domain_values (domainid,codevalue,label,label_s,disp_order) values('" + domainid
					+ "','" + codevalue + "','" + label + "','" + lable_s + "','" + disp_order + "')";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ArrayList<String>> getSearchFinYr(String year1) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!year1.equals("")) {

				qry += " codevalue  = ? ";
			}
			if (!year1.equals("") && !year1.equals("") && year1 != null) {
				q = "select id,codevalue,label from fp.fp_domain_values where domainid='FINYEAR' and " + qry
						+ " order by tr_head";
			} else {
				q = "select id,codevalue,label from fp.fp_domain_values where domainid='FINYEAR' order by disp_order";
			}

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> list = null;
			while (rs.next()) {
				list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("codevalue"));
				list.add(rs.getString("label"));
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

	public ArrayList<ArrayList<String>> getSearchTable(String year2) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select table_name from information_schema.tables where table_schema='fp' and table_name like '%"
					+ year2.substring(2, 4) + "%' order by table_name";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("table_name"));

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

	public ArrayList<ArrayList<String>> getCheckDomain(String codevalue) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select codevalue from fp.fp_domain_values where codevalue= '" + codevalue + "'";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("codevalue"));

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

	public ArrayList<ArrayList<String>> getCheckTable(String year3) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select table_name from information_schema.tables where table_schema='fp' and table_name like '%"
					+ year3.substring(2, 4) + "' order by table_name";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("table_name"));

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

	public String getCreateTableF1111(String year3) {
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String cdaSql = "";
			String projSql = "";
			String tranSql = "";
			String uplSql = "";
			String uplogSql = "";
			String fundSql = "";
			int yrprev = Integer.parseInt(year3) - 1;
			String yrprev1 = String.valueOf(yrprev);

			cdaSql = " create table fp.fp_tb_cda_detl" + year3.substring(2, 4) + " as select * from fp.fp_tb_cda_detl"
					+ yrprev1.substring(2, 4) + " where sus_no='0000000'";

			stmt = conn.prepareStatement(cdaSql);
			stmt.executeUpdate();

			tranSql = " create table fp.fp_tb_trans_detl" + year3.substring(2, 4)
					+ " as select * from fp.fp_tb_trans_detl" + yrprev1.substring(2, 4) + " where fr_sus_no='0000000'";

			stmt = conn.prepareStatement(tranSql);
			stmt.executeUpdate();

			projSql = " create table fp.fp_tb_proj_detl" + year3.substring(2, 4)
					+ " as select * from fp.fp_tb_proj_detl" + yrprev1.substring(2, 4) + " where sus_no='0000000'";

			stmt = conn.prepareStatement(projSql);
			stmt.executeUpdate();

			fundSql = " create table fp.fp_tb_funds_detl" + year3.substring(2, 4)
					+ " as select * from fp.fp_tb_funds_detl" + yrprev1.substring(2, 4) + " where fr_sus_no='0'";

			stmt = conn.prepareStatement(fundSql);
			stmt.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ArrayList<String>> search_upd_datay(String est_type2, String fin_year2, String sus1) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select a.id,a.fr_sus_no,a.dflag,a.tr_head,p.head_desc,a.bg_sus,a.bg_hlder,a.prv_allot_s,a.cur_proj_s,";
			q = q + " a.cur_allot_s,a.fin_year,a.upd_flag,a.rs_fmt from fp.fp_tmp_be_upload a ";
			q = q + " inner join fp.fp_tb_head_mstr p on p.tr_head=a.tr_head ";
			q = q + " where a.fr_sus_no='" + sus1 + "' and a.upload_for='" + est_type2 + "' and a.fin_year='"
					+ fin_year2 + "'";
			q = q + " order by a.fr_sus_no,a.tr_head,a.bg_hlder_fmn,a.bg_sus";
			System.out.println("search_upd_datay-"+q);
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int idd = 0;
			int idd1 = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				idd = rs.getInt("id");
				list.add(rs.getString("id"));
				list.add(rs.getString("fr_sus_no"));
				list.add(rs.getString("dflag"));
				list.add(rs.getString("tr_head"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("bg_sus"));
				list.add(rs.getString("bg_hlder"));
				list.add(rs.getString("prv_allot_s"));
				list.add(rs.getString("cur_proj_s"));
				list.add(rs.getString("cur_allot_s"));
				list.add(rs.getString("fin_year"));
				list.add(rs.getString("upd_flag"));
				list.add(rs.getString("rs_fmt"));
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

	public String updatefpupload(String updid, String cur_allot2) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		Connection conn = null;

		String cur_allot_s = cur_allot2;
		int idd = Integer.parseInt(updid);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String hql = "update fp.fp_tmp_be_upload set cur_allot_s='" + cur_allot_s + "',cur_allot=(" + cur_allot_s
					+ "::float) * 10000000,upd_flag='Y',app_flag='N',conc_flag='N',submit_flag='N' where id=" + idd
					+ "";
			stmt = conn.prepareStatement(hql);

			stmt.executeUpdate();
			tx.commit();
			msg = "Data Updated Successfully.";
			stmt.close();
			conn.close();
		} catch (Exception e) {

			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}

	public ArrayList<ArrayList<String>> getCheckUpdData(String conc_flag, String app_flag, String submit_flag) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select conc_flag,app_flag,submit_flag from fp.fp_tmp_be_upload where conc_flag= '" + conc_flag
					+ "' and app_flag='" + app_flag + "' and submit_flag='" + submit_flag + "'";
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("conc_flag"));
				list.add(rs.getString("app_flag"));
				list.add(rs.getString("submit_flag"));

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

	public boolean nSaveAlertMsg(String nMsg, String nPara, String nParaValue) {
		Connection conn = null;

		int idd = 0;
		String sql;
		String tt = "N";
		try {
			conn = dataSource.getConnection();
			String sql0 = "select id,msg_type from fp.mms_tb_msg_mstr where msg_type='" + nPara + "'";
			PreparedStatement stmt0 = conn.prepareStatement(sql0);
			ResultSet rs0 = stmt0.executeQuery();
			if (rs0.next()) {
				sql = "update fp.mms_tb_msg_mstr set msg_desc=? where id=" + rs0.getInt("id");
				tt = "1";
			} else {
				sql = "insert into fp.mms_tb_msg_mstr (msg_id,msg_desc,msg_status,msg_type,data_cr_date,data_cr_by) values(0,?,1,'"
						+ nPara + "',localtimestamp,?)";
				tt = "2";
			}
			rs0.close();
			stmt0.close();

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nMsg);
			if (tt.equals("2")) {
				stmt.setString(2, "fp");
			}
			stmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean getCheckDataUpdation(String conc_flag, String app_flag, String submit_flag) {

		Connection conn = null;
		boolean isExists = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String q = "select conc_flag,app_flag,submit_flag from fp.fp_tmp_be_upload where conc_flag=? and app_flag=? and submit_flag=?";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, conc_flag);
			stmt.setString(2, app_flag);
			stmt.setString(3, submit_flag);

			ResultSet rs = stmt.executeQuery();

			isExists = rs.next();
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			isExists = false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					isExists = false;
				}
			}
		}
		return isExists;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getCDAFwdBookingDetails(String finYr, String sus_no, int expID) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "select c.id,coalesce((c.fwd_amt),0) fwd_amt,coalesce(fwd_ref,'-') fwd_ref,coalesce(to_char(fwd_date,'dd-MM-yyyy') ,'-') fwd_date,coalesce((c.bkd_amt),0) bkd_amt,coalesce(bkd_ref,'-') bkd_ref,coalesce(to_char(bkd_date,'dd-MM-yyyy'),'-') bkd_date from "
					+ "fp.fp_tb_trans_detl" + finYr + " t left join  fp.fp_tb_cda_detl" + finYr
					+ " c on c.sus_no=t.to_sus_no and c.exp_id=t.id where to_sus_no=? and t.tr_type='EXP' and t.id=? order by id desc";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, sus_no);
			stmt.setInt(2, expID);
			System.out.println("getCDAFwdBookingDetails-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("fwd_amt"));
				list.add(rs.getString("fwd_ref"));
				list.add(rs.getString("fwd_date"));
				list.add(rs.getString("bkd_amt"));
				list.add(rs.getString("bkd_ref"));
				list.add(rs.getString("bkd_date"));
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			alist = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					alist = null;
				}
			}
		}
		System.out.println("getCDAFwdBookingDetails-alist-"+alist);
		return alist;
	}
	
	public @ResponseBody ArrayList<ArrayList<String>> getexpBookingDetails(String finYr, String sus_no, int expID) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
/*			String qry = "select c.id,coalesce((c.fwd_amt),0) fwd_amt,coalesce(fwd_ref,'-') fwd_ref,coalesce(to_char(fwd_date,'dd-MM-yyyy') ,'-') fwd_date,coalesce((c.bkd_amt),0) bkd_amt,coalesce(bkd_ref,'-') bkd_ref,coalesce(to_char(bkd_date,'dd-MM-yyyy'),'-') bkd_date from "
					+ "fp.fp_tb_trans_detl" + finYr + " t left join  fp.fp_tb_cda_detl" + finYr
					+ " c on c.sus_no=t.to_sus_no and c.exp_id=t.id where to_sus_no=? and t.tr_type='EXP' and t.id=? order by id desc";
			
*/			String qry = " select a.id,a.tr_head,a.exp_amt,a.period,a.to_remarks,b.fwd_amt, b.fwd_ref,b.fwd_date,b.bkd_amt,b.bkd_ref,b.bkd_date,b.bkd_remarks,b.id as cda_id from fp.fp_tb_trans_detl" + finYr + " a";
			qry += " left join fp.fp_tb_cda_detl" + finYr + " b on a.id=b.exp_id where a.id=? or a.pid=?";
			qry="";
			qry += " select a.id,a.pid,a.tr_head,a.exp_amt,a.period,a.to_remarks,b.fwd_amt, b.fwd_ref,b.fwd_date,b.bkd_amt,b.bkd_ref,b.bkd_date,b.bkd_remarks,b.id as cda_id,";
			qry +=" (case when a.tr_type='EDT' then 'EX DUTY' when a.tr_type='OTH' then 'OTHER TAX' else a.tr_type end) as tr_type from"; 
			qry += " (select id,max(pid) as pid,max(tr_head) as tr_head,sum(exp_amt) as exp_amt,max(period) as period,max(to_remarks) as to_remarks,max(tr_type) as tr_type from ";
			qry += " fp.fp_tb_trans_detl" + finYr + " where id=? or pid=? group by id,tr_head) a left join fp.fp_tb_cda_detl" + finYr + " b on a.id=b.exp_id";
			
			stmt = conn.prepareStatement(qry);
			stmt.setInt(1, expID);
			stmt.setInt(2, expID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("tr_head"));
				list.add(rs.getString("exp_amt"));
				list.add(rs.getString("period"));
				list.add(rs.getString("to_remarks"));								
				list.add(rs.getString("fwd_amt"));
				list.add(rs.getString("fwd_ref"));
				list.add(rs.getString("fwd_date"));
				list.add(rs.getString("bkd_amt"));
				list.add(rs.getString("bkd_ref"));
				list.add(rs.getString("bkd_date"));
				list.add(rs.getString("bkd_remarks"));
				list.add(rs.getString("cda_id"));
				list.add(rs.getString("tr_type"));
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			alist = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					alist = null;
				}
			}
		}
		return alist;
	}
	
	public String createBudgetHolder(String sus_no,String psus_no, String form_code_control, String unit_name, String status_sus_no,String remarks, String hq_type,String username) {
		Connection conn = null;
		String msg = "";
		try {
			conn = dataSource.getConnection();
			String sql = "select unit_name from fp.fp_tv_orbat_dtl where upper(sus_no) =upper(?) and upper(unit_name)=upper(?) and upper(status_sus_no)= upper('Active')";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, unit_name);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return "Budget holder already exists with same Unit Name/SUS No";
			}
			rs.close();
			stmt.close();
			
			stmt = null;
			sql = "insert into fp.fp_tb_norbat_dtl (sus_no,psus_no,form_code_control,unit_name,status_sus_no,remarks,hq_type,data_cr_by,data_cr_date) values(?,?,?,?,?,?,?,?,now())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, psus_no);
			stmt.setString(3, form_code_control);
			stmt.setString(4, unit_name);
			stmt.setString(5, status_sus_no);
			stmt.setString(6, remarks);
			stmt.setInt(7, Integer.parseInt(hq_type));
			stmt.setString(8, username);
			msg = stmt.executeUpdate() > 0 ? "Data Saved Successfully." : "Error - unable to perform action";
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			msg = "Error - unable to perform action";
		}
		return msg;
	}
	
	
	public String deletecdaupload(String crs_dt) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String hqld = "delete from fp.fp_tb_cda_upload WHERE booking_date='"+crs_dt+"'";			
			PreparedStatement stmt2 = conn.prepareStatement(hqld);
			stmt2.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public @ResponseBody ArrayList<ArrayList<String>> getCDAUploadedSheet(String expID) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "select * from fp.fp_tb_cda_upload where booking_date=?";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, expID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("ser_no"));
				list.add(rs.getString("acc_head"));
				list.add(rs.getString("allocation_amt"));
				list.add(rs.getString("charged_amt"));
				list.add(rs.getString("booking_amt"));
				list.add(rs.getString("booking_percent"));
				list.add(rs.getString("booking_date"));
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			alist = null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					alist = null;
				}
			}
		}
		return alist;
	}
	
	public String delExpData(String sus_no,	String del_remarks, String exp_id, String bkd_id, String username) {
		Connection conn = null;
		String msg = "";
		String sql1="";
		String sql2="";
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try {
			conn = dataSource.getConnection();
			String finYr = fpTr_Dao.nGetAdmFinYear("CFY");
			finYr = finYr.substring(2, 4);
			PreparedStatement stmt = null;
			String sql = "select a.to_sus_no,a.id,b.id as cda_id,a.exp_amt,a.tr_head_to,a.data_cr_by from fp.fp_tb_trans_detl" + finYr + " a left join fp.fp_tb_cda_detl" + finYr + " b on a.id=b.exp_id where a.id=? and a.to_sus_no=?";			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(exp_id));
			stmt.setString(2, sus_no);
			String bkdid="";
			String susn="";
			String expamt="";
			String exp_head="";
			String exp_by="";
			int expid=0;
			int bi=0;
			System.out.println("delExpData-select-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (bi>0) {
					bkdid +=",";					
				}
				bi=bi+1;
				bkdid +=""+rs.getInt("cda_id")+"";
				expid =rs.getInt("id");
				susn =rs.getString("to_sus_no");	
				expamt=rs.getString("exp_amt");
				exp_head=rs.getString("tr_head_to");
				exp_by= rs.getString("data_cr_by");
			}

			if (bkdid.length()<=0) {
				msg = "Error - unable to peform action-bkd";
				return msg;
			}
			sql = "delete from fp.fp_tb_trans_detl" + finYr+ " where (id=? or pid=?) and to_sus_no=?";
			stmt = null;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(exp_id));
			stmt.setInt(2, Integer.parseInt(exp_id));
			stmt.setString(3, sus_no);
			int hasUpdated = stmt.executeUpdate();
			stmt.close();
			sql1 = "delete from fp.fp_tb_cda_detl" + finYr+ " where id in ("+bkdid+") and sus_no=?";
			stmt1 = null;
			stmt1 = conn.prepareStatement(sql1);
			System.out.println("delExpData-del-cda-"+stmt1.toString());
			//stmt1.setInt(1, Integer.parseInt(bkdid));
			stmt1.setString(1, sus_no);
			int hasUpdated1 = stmt1.executeUpdate();
			stmt1.close();
			
			sql2= "insert into fp.fp_tb_trans_log" + finYr+ " (exp_id,bkd_id,remarks,tr_userid,to_sus_no,data_cr_by,data_cr_date,tr_value,tr_head) ";
			sql2 += " values (?,?,?,?,?,?,localtimestamp,?,?)";
			stmt2 = null;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, expid+"");
			stmt2.setString(2, bkdid);
			stmt2.setString(3, del_remarks);
			stmt2.setString(4, exp_by);
			stmt2.setString(5, sus_no);
			stmt2.setString(6, username);
			stmt2.setDouble(7, Double.parseDouble(expamt));
			stmt2.setString(8, exp_head);			
			int hasUpdated2 = stmt2.executeUpdate();
			stmt2.close();
			rs.close();
			conn.close();
			msg = hasUpdated > 0 ? "Expenditure Deleted Successfully" : "Error - Unable to perform Action";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Error - unable to peform action-Try";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return msg;
	}
	
	public boolean getCreateTableF(String year) {
		Connection conn = null;
		boolean hasSaved = false;

		try {
			conn = dataSource.getConnection();

			try {
				PreparedStatement stmt = null;

				int prev_yr = Integer.parseInt(year) - 1;
				String prv_yr = String.valueOf(prev_yr);
				
				System.out.println("year3 "+year+"prev_yr "+prev_yr);

				conn.setAutoCommit(false);

				String cdaSql = "create table fp.fp_tb_cda_detl"+year.substring(2, 4)+" as select * from fp.fp_tb_cda_detl"+prv_yr.substring(2, 4)+" where sus_no='0000000';"
								+ "ALTER TABLE fp.fp_tb_cda_detl"+year.substring(2, 4)+" DROP COLUMN id; "
								+ "ALTER TABLE fp.fp_tb_cda_detl"+year.substring(2, 4)+" ADD COLUMN id serial";
				stmt = conn.prepareStatement(cdaSql);
				stmt.executeUpdate();
				
				String tranSql = " create table fp.fp_tb_trans_detl"+year.substring(2, 4)+" as select * from fp.fp_tb_trans_detl"+prv_yr.substring(2, 4)+" where fr_sus_no='0000000';"
									+ "ALTER TABLE fp.fp_tb_trans_detl"+year.substring(2, 4)+" DROP COLUMN id; "
									+ "ALTER TABLE fp.fp_tb_trans_detl"+year.substring(2, 4)+" ADD COLUMN id serial";
				stmt = null;
				stmt = conn.prepareStatement(tranSql);
				stmt.executeUpdate();

				String projSql = "create table fp.fp_tb_proj_detl"+year.substring(2, 4)+" as select * from fp.fp_tb_proj_detl"+prv_yr.substring(2, 4)+" where sus_no='0000000';"
									+ "ALTER TABLE fp.fp_tb_proj_detl"+year.substring(2, 4)+" DROP COLUMN id; "
									+ "ALTER TABLE fp.fp_tb_proj_detl"+year.substring(2, 4)+" ADD COLUMN id serial";
				stmt = null;
				stmt = conn.prepareStatement(projSql);
				stmt.executeUpdate();

				String fundSql = " create table fp.fp_tb_funds_detl"+year.substring(2, 4)+" as select * from fp.fp_tb_funds_detl"+prv_yr.substring(2, 4)+" where fr_sus_no='0';"
									+ "ALTER TABLE fp.fp_tb_funds_detl"+year.substring(2, 4)+" DROP COLUMN id; "
									+ "ALTER TABLE fp.fp_tb_funds_detl"+year.substring(2, 4)+" ADD COLUMN id serial";
				stmt = null;
				stmt = conn.prepareStatement(fundSql);
				stmt.executeUpdate();
				
				conn.commit();
				stmt.close();
				hasSaved = true;
			} catch (Exception ex) {
				System.out.println("Exception "+ex);
				hasSaved = false;
				conn.rollback();
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Exception "+e);
			hasSaved = false;
		}
		return hasSaved;
	}
	
	public @ResponseBody ArrayList<ArrayList<String>> CDAOffList(String enc, HttpSession s1, String nParaValue) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String fltval="";
			String ordval="";
			if (nParaValue.length()>0) {
				String[] nPara = nParaValue.split(":");
				fltval=nPara[0];
				ordval=nPara[1];
			} 
			
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			
			sql1 = " select id,cda_office,cda_addr,cda_off_tel_no,cda_off_fax_no, cda_off_std_code,cda_off_location,cda_off_code from fp.fp_tb_cda_offc a";
			if (fltval.length() > 0) {
				sql1 = sql1 + " where cda_off_code='" + fltval.toUpperCase() + "'";
			}
			if (ordval.length() <= 0) {
				sql1 = sql1 + " order by cda_off_location,cda_office";
			} else {
				String[] nOdrBy = ordval.split("#");
				sql1 = sql1 + " order by ";
				for (int i = 0; i < nOdrBy.length; i++) {
					if (i > 1) {
						sql1 = sql1 + ",";
					}
					sql1 = sql1 + nOdrBy[i];
				}
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));
					list.add(rs.getString("cda_office"));
					list.add(rs.getString("cda_addr"));
					list.add(rs.getString("cda_off_tel_no"));
					list.add(rs.getString("cda_off_fax_no"));
					list.add(rs.getString("cda_off_std_code"));
					list.add(rs.getString("cda_off_location"));
					list.add(rs.getString("cda_off_code"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("List-"+li);
		return li;
	}


}