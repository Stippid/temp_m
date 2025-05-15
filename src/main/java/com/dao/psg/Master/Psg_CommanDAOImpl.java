package com.dao.psg.Master;

import java.math.BigInteger;
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
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.codec.binary.Base64;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Psg_CommanDAOImpl implements Psg_CommanDAO {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/*
	 * public List<String> getParentArmList(String arm_desc) { List<String> alist =
	 * new ArrayList<String>(); Connection conn = null; String q=""; String qry="";
	 * try{ conn = dataSource.getConnection(); PreparedStatement stmt=null;
	 * 
	 * if( !arm_desc.equals("")) { qry += " and upper(arm_desc) like upper(?) "; }
	 * 
	 * q="SELECT SUBSTR(a.arm_code,1,2)|| '00' AS arm, a.arm_desc FROM tb_miso_orbat_arm_code a WHERE SUBSTR(a.arm_code,3,4)= '00'"
	 * +qry+ "ORDER  BY 1 ";
	 * stmt=conn.prepareStatement(q); int j =1;
	 * 
	 * if(!arm_desc.equals("")) { stmt.setString(j, arm_desc); j += 1; }
	 * 
	 * 
	 * ResultSet rs = stmt.executeQuery(); while (rs.next()) { ArrayList<String>
	 * list = new ArrayList<String>(); list.add(rs.getString("arm"));
	 * list.add(rs.getString("arm_desc"));
	 * 
	 * 
	 * String f = ""; String f1 = ""; String Update =
	 * "onclick=\"  if (confirm('Are You Sure You Want to Update This Religion?') ){editData("
	 * + rs.getString("religion_id") + ")}else{ return false;}\""; f =
	 * "<i class='action_icons action_update'  " + Update +
	 * " title='Edit Data'></i>"; String Delete1 =
	 * "onclick=\"  if (confirm('Are You Sure You Want to Delete This Religion?') ){deleteData("
	 * + rs.getString("religion_id") + ")}else{ return false;}\""; f1 =
	 * "<i class='action_icons action_delete' " + Delete1 +
	 * " title='Delete Data'></i>";
	 * 
	 * list.add(f); list.add(f1);
	 * 
	 * alist.addAll(list); } rs.close(); stmt.close(); conn.close(); }catch
	 * (SQLException e) { //throw new RuntimeException(e); e.printStackTrace(); }
	 * finally { if (conn != null) { try { conn.close(); } catch (SQLException e) {
	 * } } } return alist; }
	 */

	/*
	 * List<Tb_Miso_Orabt_Arm_Code> getTargetUnitNameList(String arm_desc,
	 * HttpSession sessionUserId) { Session session =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction tx =
	 * session.beginTransaction(); Query q = session.createQuery(
	 * "select arm_code from Tb_Miso_Orabt_Arm_Code where arm_desc=:arm_desc");
	 * q.setParameter("arm_desc", arm_desc);
	 * 
	 * @SuppressWarnings("unchecked") List<Tb_Miso_Orabt_Arm_Code> list =
	 * (List<Tb_Miso_Orabt_Arm_Code>) q.list(); tx.commit(); session.close(); return
	 * list; }
	 */

	public ArrayList<List<String>> getCorpsList(String fcode) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select SUBSTR(form_code_control,1,3) as form_code_control,unit_name FROM tb_miso_orbat_unt_dtl "
					+ " where sus_no in (select sus_no from tb_miso_orbat_codesform where level_in_hierarchy ='Corps') and "
					+ " form_code_control like ? and status_sus_no = 'Active'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, fcode + "%");

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("form_code_control"));
				list.add(rs1.getString("unit_name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getPSG_CommandList() {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT sus_no, cmd_name FROM orbat_view_cmd";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("cmd_name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getPSG_CorpsList() {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT sus_no, coprs_name FROM orbat_view_corps";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("coprs_name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getPSG_DivList() {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT sus_no, div_name FROM orbat_view_div";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("div_name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getPSG_BdeList() {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT sus_no, bde_name FROM orbat_view_bde";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("bde_name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

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

		// Enc Key Append Last value of List
		if (l.size() != 0) {
			FList.add(enckey + "0fsjyg==");
		}

		return FList;
	}

	public List<String> getIssuingAuthorityList(String unit_name) {
		List<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!unit_name.equals("")) {
				qry += " where upper(unit_name) like upper(?) and upper(status_sus_no) in  ('ACTIVE','INACTIVE') ";
			}

			/*
			 * q="select unit_name from all_fmn_view where level_in_hierarchy!='Brigade'" +
			 * qry + "limit 10";
			 */

			q = "select distinct unit_name from tb_miso_orbat_unt_dtl " + qry + "limit 10";
			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!unit_name.equals("")) {
				stmt.setString(j, unit_name.toUpperCase() + "%");
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));
				// ist.add(rs.getString("arm_desc"));

				/*
				 * String f = ""; String f1 = ""; String Update =
				 * "onclick=\"  if (confirm('Are You Sure You Want to Update This Religion?') ){editData("
				 * + rs.getString("religion_id") + ")}else{ return false;}\""; f =
				 * "<i class='action_icons action_update'  " + Update +
				 * " title='Edit Data'></i>"; String Delete1 =
				 * "onclick=\"  if (confirm('Are You Sure You Want to Delete This Religion?') ){deleteData("
				 * + rs.getString("religion_id") + ")}else{ return false;}\""; f1 =
				 * "<i class='action_icons action_delete' " + Delete1 +
				 * " title='Delete Data'></i>";
				 * 
				 * list.add(f); list.add(f1);
				 */

				alist.addAll(list);
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

	public ArrayList<ArrayList<String>> getShapeData(String shape, int status, String comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select authority,date_of_authority,clasification,id,shape_status,shape_value,from_date,to_date,diagnosis,shape_sub_value,other,from_date_1bx,to_date_1bx,diagnosis_1bx,\r\n"
					+ "(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis) as des_1,(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis_1bx) as bxdes_1,reject_remarks \r\n"
					+ "from tb_psg_medical_category where shape=?  and comm_id =CAST(? AS BIGINT) and status=? order by id";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, shape);
			stmt.setString(2, comm_id);
			stmt.setInt(3, status);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("shape_status")); // 0
				list.add(rs.getString("shape_value")); // 1
				list.add(rs.getString("from_date")); // 2
				list.add(rs.getString("to_date")); // 3
				if (rs.getString("des_1") != null)
					list.add(rs.getString("diagnosis") + "-" + rs.getString("des_1")); // 4
				else
					list.add("");

				list.add(rs.getString("id")); // 10 -> 5
				list.add(rs.getString("authority")); // 11 -> 6
				list.add(rs.getString("date_of_authority")); // 12 -> 7
				list.add(rs.getString("clasification")); // 13 -> 8
				list.add(rs.getString("shape_sub_value")); // 9
				list.add(rs.getString("other")); // 10
				list.add(rs.getString("from_date_1bx")); // 11
				list.add(rs.getString("to_date_1bx")); // 12

				if (rs.getString("bxdes_1") != null)
					list.add(rs.getString("diagnosis_1bx") + "-" + rs.getString("bxdes_1")); // 13
				else
					list.add("");
				list.add(rs.getString("reject_remarks")); // 14
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

	public ArrayList<ArrayList<String>> getforiegnCountry() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {

			conn = dataSource.getConnection();
			String sql = "SELECT id,name  FROM tb_psg_foreign_country";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("id"));
				list.add(rs1.getString("name"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alist;
	}

	@Override
	public String getCmdSusFromUnitSus(String sus_no) {

		Connection conn = null;
		String q = "";
		String cmd_sus = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  ov.sus_no as cmd_sus from tb_miso_orbat_unt_dtl orb \r\n"
					+ "inner join orbat_view_cmd ov on ov.cmd_code=SUBSTRING (orb.form_code_control, 1, 1)\r\n"
					+ "where orb.sus_no=? and orb.status_sus_no='Active'";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cmd_sus = rs.getString("cmd_sus");
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
		return cmd_sus;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ArrayList<ArrayList<String>> getSelfMotFatName(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!comm_id.toString().equals("")) {
				qry += " and  c.id::text= ? ";
			}
			q = "select  distinct\n" + "c.id,\n" + "c.name,\n" + "c.date_of_birth,\n" + "cd.mother_name,\n"
					+ "cd.mother_dob,\n" + "cd.father_name,cd.father_dob\n"
					+ "from tb_psg_trans_proposed_comm_letter c \n"
					+ "inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \n"
					+ "where (c.status='1' or c.status='5') " + qry + "  ";
			stmt = conn.prepareStatement(q);
			if (!qry.equals("")) {
				int j = 1;
				if (!comm_id.toString().equals("")) {
					stmt.setString(j, comm_id.toString());
					j += 1;
				}
			}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name"));// 1
				list.add(rs.getString("date_of_birth"));// 2
				list.add(rs.getString("mother_name"));// 3
				list.add(rs.getString("mother_dob"));// 4
				list.add(rs.getString("father_name"));// 5
				list.add(rs.getString("father_dob"));// 6
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
	public ArrayList<ArrayList<String>> getSpouseName(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!comm_id.toString().equals("")) {
				qry += " and  c.id::text= ? ";
			}
			q = "select  distinct\r\n" + "fm.id,fm.maiden_name,fm.date_of_birth\r\n"
					+ "from tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n"
					+ "inner join tb_psg_census_family_married fm on  c.id = fm.comm_id  and fm.marital_status='8' and fm.status=1\r\n"
					+ "where (c.status='1' or c.status='5') " + qry + " order by id desc limit 1 ";
			stmt = conn.prepareStatement(q);
			if (!qry.equals("")) {
				int j = 1;
				if (!comm_id.toString().equals("")) {
					stmt.setString(j, comm_id.toString());
					j += 1;
				}
			}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("maiden_name"));// 1
				list.add(rs.getString("date_of_birth"));// 2
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
	public ArrayList<ArrayList<String>> getChildName(BigInteger comm_id, String rela) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!comm_id.toString().equals("")) {
				qry += " and  c.comm_id::text= ? ";
			}
			if (!rela.equals("")) {
				qry += " and  c.relationship= ? ";
			}
			q = "select  distinct\r\n" + "c.id,\r\n" + "c.name,\r\n" + "c.date_of_birth\r\n"
					+ "from tb_psg_census_children c \r\n" + "where  c.status='1' " + qry + " ";
			stmt = conn.prepareStatement(q);
			if (!qry.equals("")) {
				int j = 1;
				if (!comm_id.toString().equals("")) {
					stmt.setString(j, comm_id.toString());
					j += 1;
				}
				if (!rela.equals("")) {
					if (rela.equals("5"))
						stmt.setInt(j, 2);
					else
						stmt.setInt(j, 1);
					j += 1;
				}
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name"));// 1
				list.add(rs.getString("date_of_birth"));// 2
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

	public ArrayList<ArrayList<String>> getChilddob(String id, BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct\r\n" + "cm.date_of_birth\r\n" + "from tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n"
					+ "inner join tb_psg_census_children cm on cm.cen_id =cd.id  and cm.status='1'\r\n"
					+ "where cm.name = ? and c.id::text=?";
			stmt = conn.prepareStatement(q);
			int j = 1;
			stmt.setString(j, id);
			j += 1;
			stmt.setString(j, comm_id.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("date_of_birth"));// 0
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

	public ArrayList<List<String>> getPersonnelNofromcomm(BigInteger comm_id) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = " select com.personnel_no,COALESCE(cen.id,0) as id,COALESCE((select type_of_event from tb_psg_census_family_married where comm_id=com.id and status not in (-1,2) order by id desc limit 1),0) marital_status,com.unit_sus_no from tb_psg_trans_proposed_comm_letter com"
					+ " left join tb_psg_census_detail_p cen on cen.comm_id=com.id"
					+ " where com.id= cast(? as bigint)  ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));


			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("personnel_no"));
				list.add(rs1.getString("id"));
				list.add(rs1.getString("marital_status"));
				list.add(rs1.getString("unit_sus_no"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getArmyNofromJco(int jco_id) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select com.army_no,COALESCE((select type_of_event from tb_psg_census_family_married_jco where jco_id=com.id and status not in (-1,2) order by id desc limit 1),0) marital_status,com.unit_sus_no from tb_psg_census_jco_or_p com\r\n"
					+ " where com.id=? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, jco_id);

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("army_no"));
				list.add(rs1.getString("marital_status"));
				list.add(rs1.getString("unit_sus_no"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public List<String> getColumnList() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "SELECT column_name from INFORMATION_SCHEMA.COLUMNS \r\n"
					+ "WHERE TABLE_NAME = 'tb_ad_hoc_parent'";
			stmt = conn.prepareStatement(sql1);
			// stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("column_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}

		return list1;
	}
	public int checkMedicalDtlFillIn3008(String comm_id) {
	    int total = 0;

	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM tb_psg_medical_category WHERE comm_id = CAST(? AS BIGINT) AND status = 1");
	    ) {
	        stmt.setString(1, comm_id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                total = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}
	
	public List<String> getIssuingAuthorityListIdCard(String unit_name) {
		List<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!unit_name.equals("")) {
				qry += " where upper(unit_name) like upper(?) and upper(status_sus_no) in  ('ACTIVE','INACTIVE','INVALID') ";
			}	
			q = "select distinct unit_name from tb_miso_orbat_unt_dtl " + qry + "limit 10";
			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!unit_name.equals("")) {
				stmt.setString(j, unit_name.toUpperCase() + "%");
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));		
				alist.addAll(list);
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
	
	public List<String> getIssuingAuthoritySusListIdCard(String sus_no) {
		List<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!sus_no.equals("")) {
				qry += " where upper(sus_no) like upper(?) and upper(status_sus_no) in  ('ACTIVE','INACTIVE','INVALID') ";
			}	
			q = "select distinct sus_no from tb_miso_orbat_unt_dtl " + qry + "limit 10";
			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no.toUpperCase() + "%");
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));		
				alist.addAll(list);
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


}
