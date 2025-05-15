package com.dao.psg.Jco_Census;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.persistance.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

public class Search_JCOsDaoImpl implements Search_JCOsDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> Search_JCOs(String roleSusNo, String army_no, String status, String rank,
			String unit_name, String unit_sus_no, HttpSession session) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String roleType = session.getAttribute("roleType").toString();

			if (!roleSusNo.equals("")) {
				qry += " and jc.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			if (!army_no.equals("")) {
				qry += "  and upper(jc.army_no) like ? ";
			}
			if (!rank.equals("")) {
				qry += " and cast(jc.rank as character varying) = ? ";
			}
			if (status.equals("0")) {
				qry += " and cast(jc.status as character varying) = ? ";
			}
			if (status.equals("1")) {
				qry += " and cast(jc.status as character varying) = ? ";
			}
			if (status.equals("3")) {
				qry += " and cast(jc.status as character varying) = ? ";
			}

			q = "select distinct jc.id,\r\n" + "orb.unit_name,\r\n" + "jc.arm_service,\r\n" + "jc.army_no,\r\n"
					+ "jc.full_name,\r\n" + "jc.unit_sus_no,jc.reject_remarks,\r\n"
					+ "ltrim(TO_CHAR(jc.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n"
					+ "jc.arm_service,\r\n" + "arm.arm_desc as parent_arm,\r\n" + "r.rank as rank,jc.modified_date \r\n"
					+ "FROM tb_psg_census_jco_or_p jc  \r\n"
					+ "inner join tb_psg_mstr_rank_jco r on r.id = jc.rank  \r\n"
					+ "left join tb_miso_orbat_arm_code  arm on arm.arm_code = jc.arm_service \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jc.unit_sus_no and status_sus_no='Active'\r\n"
					+ "where jc.id != 0 and r.status = 'active' " + qry + " order by jc.modified_date desc";

			stmt = conn.prepareStatement(q);

			if (!qry.equals("")) {
				int j = 1;

				if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!army_no.equals("")) {
					stmt.setString(j, army_no.toUpperCase() + "%");
					j += 1;
				}

				if (!rank.equals("")) {
					stmt.setString(j, rank);
					j += 1;
				}
				if (status.equals("0")) {
					stmt.setString(j, status);
					j += 1;
				}
				if (status.equals("1")) {
					stmt.setString(j, status);
					j += 1;
				}
				if (status.equals("3")) {
					stmt.setString(j, status);
					j += 1;
				}
			}

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("army_no"));
				list.add(rs.getString("rank"));
				list.add(rs.getString("full_name"));
				list.add(rs.getString("date_of_birth"));
				list.add(rs.getString("unit_sus_no"));
				list.add(rs.getString("parent_arm"));
				list.add(rs.getString("reject_remarks"));
				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3"))) {
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){delete1Data("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("1")) {
					String View = "onclick=\"  {ViewData(" + rs.getString("id") + ")}\"";
					f = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
					String Relegate = "onclick=\"  if (confirm('Are You Sure You Want to Relegate This Letter?') ){relegateData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f4 = "<i class='fa fa-refresh' " + Relegate + " title='Relegate Data'></i>";
				}
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3")) {
					String View = "onclick=\" {ViewData(" + rs.getString("id") + ")}\"";
					f = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("1")) {
					String View = "onclick=\" {ViewData(" + rs.getString("id") + ")}\"";
					f = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")) {
					String Approved = "onclick=\" {open_approve(" + rs.getString("id") + ")}\"";
					f3 = "<i " + Approved
						+ " title='Approve Or Reject Data' style=' background: #31af91;border-radius: 24px;font-size: 15px; padding: 5px;'><i class='fa fa-check' style='color:white;'></i><i style='color:white;' class='fa fa-times'></i></i>";
				}

				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(f4);
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

	public TB_CENSUS_JCO_OR_PARENT getJCOsByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_CENSUS_JCO_OR_PARENT updateid = (TB_CENSUS_JCO_OR_PARENT) sessionHQL.get(TB_CENSUS_JCO_OR_PARENT.class,
					id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public TB_CENSUS_JCO_OR_SIBLINGS getJCOSiblingByid(int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_CENSUS_JCO_OR_SIBLINGS updateid1 = (TB_CENSUS_JCO_OR_SIBLINGS) sessionHQL
					.get(TB_CENSUS_JCO_OR_SIBLINGS.class, jco_id);
			tx.commit();

			return updateid1;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	///////////////////////////////// Medical
	///////////////////////////////// DaoImpl//////////////////////////////////////////

	public ArrayList<ArrayList<String>> getShapeData_jco(String shape, int jco_id, int status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  authority,date_of_authority,clasification,id,shape_status,shape_value,from_date,to_date,diagnosis,shape_sub_value,other,from_date_1bx,to_date_1bx,diagnosis_1bx,\r\n"
					+ "(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis) as des_1,(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis_1bx) as bxdes_1 \r\n"
					+ "from tb_psg_medical_category_jco where shape=?  and jco_id=? and status=? order by id";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, shape);
			stmt.setInt(2, jco_id);
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

	public Boolean getArmyNoAlreadyExist(String army_no, String jco_id, String army_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		String queryP = "select count(id) from TB_CENSUS_JCO_OR_PARENT where army_no=:army_no ";
		String queryArmy = "select count(id) from TB_CHANGE_ARMY_NO where army_no=:army_no ";

		if (jco_id != null && !jco_id.equals("") && Integer.parseInt(jco_id) > 0) {
			queryP += " and id!=:id";
		}

		if (army_id != null && !army_id.equals("") && Integer.parseInt(army_id) > 0) {
			queryArmy += " and id!=:id";
		}

		Query q = session.createQuery(queryP);
		q.setParameter("army_no", army_no);
		if (jco_id != null && !jco_id.equals("") && Integer.parseInt(jco_id) > 0) {
			q.setParameter("id", Integer.parseInt(jco_id));
		}

		Query q2 = session.createQuery(queryArmy);
		q2.setParameter("army_no", army_no);
		if (army_id != null && !army_id.equals("") && Integer.parseInt(army_id) > 0) {
			q2.setParameter("id", Integer.parseInt(army_id));
		}

		Long c = (Long) q.uniqueResult();
		Long c2 = (Long) q2.uniqueResult();
		tx.commit();
		session.close();
		if (c == 0 && c2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getjco_IdentityImagePath(String id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select identity_image,id from tb_psg_identity_card_jco where id=? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("identity_image");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public ArrayList<ArrayList<String>> GetAllJCO_OR_Details(int id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct j.id,j.category,\r\n" + 
					"\r\n" + 
					"j.army_no,\r\n" + 
					"j.first_name,\r\n" + 
					"j.middle_name,\r\n" + 
					"j.last_name,\r\n" + 
					"g.gender_name as gender,\r\n" + 
					"r.rank as rank,\r\n" + 
					"t2.label as rank_intake,\r\n" + 
					"TO_CHAR(j.date_of_birth  ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"j.place_of_birth,\r\n" + 
					"case when upper(c.name) like 'OTHERS' then country_other else c.name end as country_of_birth,\r\n" + 
					"case when upper(s.state_name) like 'OTHERS' then state_other else upper(s.state_name) end as state_of_birth,\r\n" + 
					"case when upper( d.district_name) like 'OTHERS' then district_other else upper(d.district_name) end as district_of_birth,\r\n" + 
					"case when upper(n.nationality_name) like 'OTHERS' then nationality_other else n.nationality_name end as nationality,\r\n" + 
					"case when upper(rel.religion_name) like 'OTHERS' then religion_other else rel.religion_name end as religion,\r\n" + 
					"case when upper(mt.mothertounge) like 'OTHERS' then mother_tongue_other else mt.mothertounge end as mother_tongue,\r\n" + 
					"ms.marital_name as marital_status,\r\n" + 
					"b.blood_desc as blood_group,\r\n" + 
					"ht.centimeter as height,\r\n" + 
					"PGP_SYM_DECRYPT(j.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,\r\n" + 
					"PGP_SYM_DECRYPT(j.pan_no ::bytea,current_setting('miso.version'))  as pan_no\r\n" + 
					",\r\n" + 
					"j.record_office_sus,\r\n" + 
					"j.record_office,\r\n" + 
					"TO_CHAR(j.date_of_attestation  ,'DD-MON-YYYY') as date_of_attestation,\r\n" + 
					"TO_CHAR(j.enroll_dt  ,'DD-MON-YYYY') as enroll_dt,TO_CHAR(j.date_of_rank  ,'DD-MON-YYYY') as date_of_rank, \r\n" + 
					"TO_CHAR(j.date_of_seniority  ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
					"tr.trade,\r\n" + 
					"TO_CHAR(j.date_of_tos  ,'DD-MON-YYYY') as date_of_tos,\r\n" + 
					"arm.arm_desc as arm_service,\r\n" + 
					"arm1.arm_desc as regiment,\r\n" + 
					"orb.unit_name as unit_posted_to,\r\n" + 
					"j.unit_sus_no,\r\n" + 
					"j.father_name,\r\n" + 
					"TO_CHAR(j.father_dob  ,'DD-MON-YYYY') as father_dob,\r\n" + 
					"j.father_place_birth,\r\n" + 
					"t.ex_servicemen as father_service,\r\n" + 
					"upper(j.father_other) as father_other,\r\n" + 
					"j.father_personal_no,\r\n" + 
					"j.father_profession,\r\n" + 
					"j.mother_name,\r\n" + 
					"TO_CHAR(j.mother_dob  ,'DD-MON-YYYY') as mother_dob,\r\n" + 
					"j.mother_place_birth,\r\n" + 
					"t1.ex_servicemen as mother_service,\r\n" + 
					"upper(j.mother_other) as mother_other,\r\n" + 
					"j.mother_personal_no,\r\n" + 
					"j.mother_profession,\r\n" + 
					"pg.pay_group,cp.class_pay,\r\n" + 
					"ce.class_e,gd.domisile as domisile,arm.arm_desc,fp.profession_name,mp.profession_name as profession_name1,\r\n" + 
					"sbs.name as sibling_name,\r\n" + 
					"sbs.date_of_birth,\r\n" + 
					"sbs.relationship,\r\n" + 
					"PGP_SYM_DECRYPT(sbs.pan_no ::bytea,current_setting('miso.version'))  as pan_no,\r\n" + 
					"PGP_SYM_DECRYPT(sbs.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,\r\n" + 
					"j.father_proffother,\r\n" + 
					"j.mother_proffother\r\n" + 
					"from tb_psg_census_jco_or_p j\r\n" + 
					"inner join tb_psg_mstr_gender g on g.id= j.gender\r\n" + 
					"inner join tb_psg_mstr_rank_jco r on r.id= j.rank\r\n" + 
					"inner join tb_psg_mstr_religion rel on rel.religion_id= j.religion\r\n" + 
					"inner join tb_psg_mstr_class_pay_jco cp on cp.id = j.class_pay\r\n" + 
					"inner join tb_psg_mstr_pay_group_jco pg on pg.id = j.pay_group\r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = j.arm_service\r\n" + 
					"inner join tb_psg_mstr_trade_jco  tr on tr.id = j.trade\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = j.unit_sus_no and status_sus_no in ('Active','Inactive') \r\n" + 
					"left join tb_psg_mstr_country c on c.id= j.country_of_birth \r\n" + 
					"left join tb_psg_mstr_state s on s.state_id= j.state_of_birth\r\n" + 
					"left join tb_psg_mstr_district d on d.district_id= j.district_of_birth\r\n" + 
					"left join tb_psg_mstr_nationality n on n.nationality_id= j.nationality\r\n" + 
					"left join tb_psg_mothertounge mt on mt.id= j.mother_tongue\r\n" + 
					"left join tb_psg_mstr_marital_status ms on  ms.marital_id = j.marital_status\r\n" + 
					"left join tb_psg_mstr_blood b on b.id = j.blood_group\r\n" + 
					"left join tb_psg_mstr_height ht on ht.height_id = j.height\r\n" + 
					"left join tb_psg_mstr_class_jco ce on ce.id = j.class_enroll\r\n" + 
					"left join tb_miso_orbat_arm_code  arm1 on arm1.arm_code = j.regiment\r\n" + 
					"left join tb_psg_mstr_profession  fp on fp.id = j.father_profession\r\n" + 
					"left join tb_psg_mstr_profession  mp on mp.id = j.mother_profession \r\n" + 
					"left join tb_psg_census_jco_or_siblings sbs on sbs.jco_id = j.id\r\n" + 
					"left join tb_psg_mstr_exservicemen t1 on j.mother_service = t1.id::text  \r\n" + 
					"left join tb_psg_mstr_exservicemen t on j.father_service = t.id::text "
					+ "left join T_Domain_Value t2 on t2.codevalue = cast(j.rank_intake as character varying) and t2.domainid = 'RECT_INTAKE'\r\n" + 
					"left join tb_psg_mstr_gorkha_domisile_jco gd on gd.id = j.domicile" + 
					 " where j.id = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			System.err.println("approve view data pending\n\n" +stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("category")); // 0
				alist.add(rs.getString("army_no")); // 1
				alist.add(rs.getString("first_name")); // 2
				alist.add(rs.getString("middle_name")); // 3
				alist.add(rs.getString("last_name")); // 4
				alist.add(rs.getString("gender")); // 5
				alist.add(rs.getString("rank")); // 6
				alist.add(rs.getString("rank_intake")); // 7
				alist.add(rs.getString("date_of_birth")); // 8
				alist.add(rs.getString("place_of_birth")); // 9
				alist.add(rs.getString("country_of_birth")); // 10
				alist.add(rs.getString("state_of_birth")); // 11
				alist.add(rs.getString("district_of_birth")); // 12
				alist.add(rs.getString("nationality")); // 13
				alist.add(rs.getString("religion")); // 14
				alist.add(rs.getString("mother_tongue"));// 15
				alist.add(rs.getString("marital_status"));// 16
				alist.add(rs.getString("blood_group"));// 17
				alist.add(rs.getString("height"));// 18
				alist.add(rs.getString("aadhar_no"));// 19
				alist.add(rs.getString("pan_no"));// 20
				alist.add(rs.getString("class_e"));// 21
				alist.add(rs.getString("domisile"));// 22
				alist.add(rs.getString("class_pay"));// 23
				alist.add(rs.getString("pay_group"));// 24
				alist.add(rs.getString("record_office_sus"));// 25
				alist.add(rs.getString("record_office"));// 26
				alist.add(rs.getString("date_of_attestation"));// 27
				alist.add(rs.getString("enroll_dt"));// 28
				alist.add(rs.getString("date_of_seniority"));// 29
				alist.add(rs.getString("trade"));// 30
				alist.add(rs.getString("date_of_tos"));// 31
				alist.add(rs.getString("arm_service"));// 32
				alist.add(rs.getString("regiment"));// 33
				alist.add(rs.getString("father_name"));// 34
				alist.add(rs.getString("father_dob"));// 35
				alist.add(rs.getString("father_place_birth"));// 36
				alist.add(rs.getString("father_service"));// 37
				alist.add(rs.getString("father_other"));// 38
				alist.add(rs.getString("father_personal_no"));// 39
				alist.add(rs.getString("profession_name"));// 40
				alist.add(rs.getString("mother_name"));// 41
				alist.add(rs.getString("mother_dob"));// 42
				alist.add(rs.getString("mother_place_birth"));// 43
				alist.add(rs.getString("mother_service"));// 44
				alist.add(rs.getString("mother_other"));// 45
				alist.add(rs.getString("mother_personal_no"));// 46
				alist.add(rs.getString("profession_name1"));// 47
				alist.add(rs.getString("id")); // 48
				alist.add(rs.getString("unit_posted_to")); // 49
				alist.add(rs.getString("unit_sus_no")); // 50
				alist.add(rs.getString("father_proffother")); // 51
				alist.add(rs.getString("mother_proffother")); // 52
				alist.add(rs.getString("date_of_rank")); // 53

				list.add(alist);

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

	public ArrayList<ArrayList<String>> getSiblings_Value(int jco_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct sb.id,sb.name,ltrim(TO_CHAR(sb.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n"
					+ "t.label as relationship,PGP_SYM_DECRYPT(sb.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,"
					+ "PGP_SYM_DECRYPT(sb.pan_no ::bytea,current_setting('miso.version'))  as pan_no,case when (t1.ex_servicemen = '0') then '' else COALESCE(t1.ex_servicemen::text,'') End as  sibling_service,"
					+ "upper(sb.sibling_personal_no) as sibling_personal_no,\r\n"
					+ "case when sb.other_sibling_ser = null then '' else COALESCE(sb.other_sibling_ser,'') end as other_sibling_ser \r\n" + "from tb_psg_census_jco_or_siblings sb\r\n"
					+ "left join T_Domain_Value t on t.codevalue = cast(sb.relationship as character varying)\r\n"
					+ "left join tb_psg_mstr_exservicemen t1 on t1.id  = sb.sibling_service\r\n"
					+ "where t.domainid = 'RELATIONSHIP' and jco_id = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("name")); // 0
				alist.add(rs.getString("date_of_birth")); // 1
				alist.add(rs.getString("relationship")); // 2
				alist.add(rs.getString("aadhar_no")); // 3
				alist.add(rs.getString("pan_no")); // 4
				alist.add(rs.getString("sibling_service")); // 5
				alist.add(rs.getString("sibling_personal_no")); // 6
				alist.add(rs.getString("other_sibling_ser")); // 7

				list.add(alist);

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

	public ArrayList<ArrayList<String>> getSpouse_Value(int jco_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct sp.jco_id,sp.maiden_name,ltrim(TO_CHAR(sp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,ltrim(TO_CHAR(sp.separated_from_dt  ,'DD-MON-YYYY'),'0') as separated_from_dt,\r\n"
					+ "sp.place_of_birth,n.nationality_name as nationality,COALESCE(sp.other_nationality,'') as other_nationality,ltrim(TO_CHAR(sp.marriage_date  ,'DD-MON-YYYY'),'0') as marriage_date,\r\n"
					+ "PGP_SYM_DECRYPT(sp.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number ,PGP_SYM_DECRYPT(sp.pan_card ::bytea,current_setting('miso.version'))  as pan_card,"
					+ "case when (t1.ex_servicemen = '0') then '' else COALESCE(t1.ex_servicemen::text,'') End as spouse_service,COALESCE(upper(sp.spouse_personal_no),'') as spouse_personal_no,\r\n"
					+ "COALESCE(upper(sp.other_spouse_ser),'') as other_spouse_ser\r\n"
					+ "from tb_psg_census_family_married_jco sp\r\n"
					+ "inner join tb_psg_mstr_nationality n on n.nationality_id = sp.nationality\r\n"
					+ "left join tb_psg_mstr_exservicemen t1 on t1.id = sp.spouse_service \r\n"
					+ " where sp.jco_id = ?  and sp.divorce_date is null";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("maiden_name")); // 0
				alist.add(rs.getString("date_of_birth")); // 1
				alist.add(rs.getString("place_of_birth")); // 2
				alist.add(rs.getString("nationality")); // 3
				alist.add(rs.getString("other_nationality")); // 4
				alist.add(rs.getString("marriage_date")); // 5
				alist.add(rs.getString("adhar_number")); // 6
				alist.add(rs.getString("pan_card")); // 7
				alist.add(rs.getString("spouse_service")); // 8
				alist.add(rs.getString("spouse_personal_no")); // 9
				alist.add(rs.getString("other_spouse_ser")); // 10
				alist.add(rs.getString("separated_from_dt"));//11
				list.add(alist);

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

	public ArrayList<ArrayList<String>> getDIV_WID_Value(int jco_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct sp.jco_id,sp.maiden_name,ms.marital_name, ltrim(TO_CHAR(sp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n"
					+ "sp.place_of_birth,n.nationality_name as nationality,COALESCE(sp.other_nationality,'') as other_nationality,ltrim(TO_CHAR(sp.marriage_date  ,'DD-MON-YYYY'),'0') as marriage_date,\r\n"
					+ "PGP_SYM_DECRYPT(sp.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number\r\n" + 
					",PGP_SYM_DECRYPT(sp.pan_card ::bytea,current_setting('miso.version'))  as pan_card,ltrim(TO_CHAR(sp.divorce_date  ,'DD-MON-YYYY'),'0') as divorce_date\r\n"
					+ "from tb_psg_census_family_married_jco sp\r\n"
					+ "inner join tb_psg_mstr_nationality n on n.nationality_id = sp.nationality\r\n"
					+ " LEFT JOIN tb_psg_mstr_marital_status ms ON sp.marital_status = ms.marital_id "
					+ " where sp.jco_id = ? and sp.divorce_date is not null";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("maiden_name")); // 0
				alist.add(rs.getString("date_of_birth")); // 1
				alist.add(rs.getString("place_of_birth")); // 2
				alist.add(rs.getString("nationality")); // 3
				alist.add(rs.getString("other_nationality")); // 4
				alist.add(rs.getString("marriage_date")); // 5
				alist.add(rs.getString("adhar_number")); // 6
				alist.add(rs.getString("pan_card")); // 7
				alist.add(rs.getString("divorce_date")); // 8
				alist.add(rs.getString("marital_name"));
				list.add(alist);

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

	public ArrayList<ArrayList<String>> getSpouse_Education_Value(int jco_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct sq.id,sq.type,sq.examination_pass,sq.degree,sq.specialization,sq.passing_year,sq.div_class,\r\n"
					+ "sq.subject,sq.institute from tb_psg_census_qualification_jco sq where sq.jco_id = ? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("type")); // 0
				alist.add(rs.getString("examination_pass")); // 1
				alist.add(rs.getString("degree")); // 2
				alist.add(rs.getString("specialization")); // 3
				alist.add(rs.getString("passing_year")); // 4
				alist.add(rs.getString("div_class")); // 5
				alist.add(rs.getString("subject")); // 6
				alist.add(rs.getString("institute")); // 7

				list.add(alist);

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

	public ArrayList<ArrayList<String>> View_GetAllJCO_OR_Details(int id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
   //260194
			q = "select distinct j.id,j.category,\r\n" + 
					"j.army_no,\r\n" + 
					"j.first_name,\r\n" + 
					"j.middle_name,\r\n" + 
					"j.last_name,\r\n" + 
					"g.gender_name as gender,\r\n" + 
					"r.rank as rank,\r\n" + 
					"t2.label as rank_intake,\r\n" + 
					"TO_CHAR(j.date_of_birth  ,'DD-MON-YYYY') as date_of_birth, TO_CHAR(j.date_of_rank  ,'DD-MON-YYYY') as date_of_rank,\r\n" + 
					"u_c.place_of_birth,\r\n" + 
					"case when upper(c.name) like 'OTHERS' then u_c.country_other else c.name end as country_of_birth,\r\n" + 
					"case when upper(s.state_name) like 'OTHERS' then u_c.state_other else upper(s.state_name) end as state_of_birth,\r\n" + 
					"case when upper( d.district_name) like 'OTHERS' then u_c.district_other else upper(d.district_name) end as district_of_birth,\r\n" + 
					"case when upper(n.nationality_name) like 'OTHERS' then u_n.nationality_other else n.nationality_name end as nationality,\r\n" + 
					"case when upper(rel.religion_name) like 'OTHERS' then religion_other else rel.religion_name end as religion,\r\n" + 
					"case when upper(mt.mothertounge) like 'OTHERS' then mother_tongue_other else mt.mothertounge end as mother_tongue,\r\n" + 
					"case when j.marital_status=10 then '' else marital_name end as marital_status,\r\n" + 
					"b.blood_desc as blood_group,\r\n" + 
					"case when ht.height_id =0 then '' else  ht.centimeter end as height,\r\n" + 
					"PGP_SYM_DECRYPT(u_a.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,\r\n" + 
					"PGP_SYM_DECRYPT(u_p.pan_no ::bytea,current_setting('miso.version'))  as pan_no,\r\n" + 
					"j.record_office_sus,\r\n" + 
					"j.record_office,\r\n" + 
					"TO_CHAR(j.date_of_attestation  ,'DD-MON-YYYY') as date_of_attestation,\r\n" + 
					"TO_CHAR(j.enroll_dt  ,'DD-MON-YYYY') as enroll_dt,TO_CHAR(j.date_of_rank  ,'DD-MON-YYYY') as date_of_rank, \r\n" + 
					"TO_CHAR(j.date_of_seniority  ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
					"tr.trade,\r\n" + 
					"TO_CHAR(j.date_of_tos  ,'DD-MON-YYYY') as date_of_tos,\r\n" + 
					"arm.arm_desc as arm_service,\r\n" + 
					"arm1.arm_desc as regiment,\r\n" + 
					"orb.unit_name as unit_posted_to,\r\n" + 
					"j.unit_sus_no,\r\n" + 
					"case when u_f.father_name is null then j.father_name else u_f.father_name end,\r\n" + 
					"TO_CHAR(u_f.father_dob  ,'DD-MON-YYYY') as father_dob,\r\n" + 
					"u_f.father_place_birth,\r\n" + 
					"t.ex_servicemen as father_service,\r\n" + 
					"upper(u_f.father_other) as father_other,\r\n" + 
					"u_f.father_personal_no,\r\n" + 
					"u_f.father_profession,\r\n" + 
					"case when u_f.mother_name is null then upper(j.mother_name) else  u_f.mother_name end as mother_name,\r\n" + 
					"TO_CHAR(u_f.mother_dob  ,'DD-MON-YYYY') as mother_dob,\r\n" + 
					"u_f.mother_place_birth,\r\n" + 
					"t1.ex_servicemen as mother_service,\r\n" + 
					"u_f.mother_other,\r\n" + 
					"u_f.mother_personal_no,\r\n" + 
					"u_f.mother_profession,\r\n" + 
					"pg.pay_group,cp.class_pay,\r\n" + 
					"ce.class_e,gd.domisile as domisile,arm.arm_desc,fp.profession_name,mp.profession_name as profession_name1,\r\n" + 
					"u_s.name as sibling_name,\r\n" + 
					"u_s.date_of_birth,\r\n" + 
					"u_s.relationship,\r\n" + 
					"PGP_SYM_DECRYPT(sbs.pan_no ::bytea,current_setting('miso.version'))  as pan_no,\r\n" + 
					"PGP_SYM_DECRYPT(sbs.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,\r\n" + 
					"u_f.father_proffother,\r\n" + 
					"u_f.mother_proffother\r\n" + 
					"from tb_psg_census_jco_or_p j\r\n" + 
					"inner join tb_psg_mstr_gender g on g.id= j.gender\r\n" + 
					"inner join tb_psg_mstr_rank_jco r on r.id= j.rank \r\n" + 
					"left join tb_psg_update_census_address_of_birth_jco u_c on (u_c.jco_id=j.id and u_c.status=1)\r\n" + 
					"left join tb_psg_update_census_nationality_jco u_n on (u_n.jco_id=j.id and u_n.status=1)\r\n" + 
					"left join tb_psg_update_census_height_jco u_h on (u_h.jco_id=j.id and u_h.status=1)\r\n" + 
					"left join tb_psg_update_census_pan_no_jco u_p on (u_p.jco_id=j.id and u_p.status=1)\r\n" + 
					"left join tb_psg_update_census_aadharno_jco u_a on (u_a.jco_id=j.id and u_a.status=1)\r\n" + 
					"left join tb_psg_update_census_class_of_enrol_jco u_e on (u_e.jco_id=j.id and u_e.status=1)\r\n" + 
					"left join tb_psg_update_census_family_details_jco u_f on (u_f.jco_id=j.id and u_f.status=1)\r\n" + 
					"left join tb_psg_update_census_details_of_sibilings_jco u_s on (u_s.jco_id=j.id and u_s.status=1)\r\n" + 
					"\r\n" + 
					"left join tb_psg_mstr_country c on c.id= u_c.country_of_birth \r\n" + 
					"left join tb_psg_mstr_state s on s.state_id= u_c.state_of_birth\r\n" + 
					"left join tb_psg_mstr_district d on d.district_id= u_c.district_of_birth\r\n" + 
					"left join tb_psg_mstr_nationality n on n.nationality_id= u_n.nationality\r\n" + 
					"inner join tb_psg_mstr_religion rel on rel.religion_id= j.religion\r\n" + 
					"left join tb_psg_mothertounge mt on mt.id= j.mother_tongue\r\n" + 
					"left join tb_psg_mstr_marital_status ms on  (ms.marital_id = j.marital_status )\r\n" + 
					"left join tb_psg_mstr_blood b on b.id = j.blood_group\r\n" + 
					"left join tb_psg_mstr_height ht on ht.height_id = u_h.height\r\n" + 
					"left join tb_psg_mstr_class_jco ce on ce.id = u_e.class_enroll\r\n" + 
					"inner join tb_psg_mstr_class_pay_jco cp on cp.id = j.class_pay\r\n" + 
					"inner join tb_psg_mstr_pay_group_jco pg on pg.id = j.pay_group\r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = j.arm_service\r\n" + 
					"left join tb_miso_orbat_arm_code  arm1 on arm1.arm_code = j.regiment\r\n" + 
					"left join tb_psg_mstr_profession  fp on fp.id = u_f.father_profession\r\n" + 
					"left join tb_psg_mstr_profession  mp on mp.id = u_f.mother_profession \r\n" + 
					"inner join tb_psg_mstr_trade_jco  tr on tr.id = j.trade  \r\n" + 
					"left join tb_psg_census_jco_or_siblings sbs on sbs.jco_id = j.id\r\n" + 
					"left join tb_psg_mstr_exservicemen t1 on j.mother_service = t1.id::text  \r\n" + 
					"left join tb_psg_mstr_exservicemen t on j.father_service = t.id::text  \r\n" + 
					"left join T_Domain_Value t2 on t2.codevalue = cast(j.rank_intake as character varying) and t2.domainid = 'RECT_INTAKE'\r\n" + 
					"left join tb_psg_mstr_gorkha_domisile_jco gd on gd.id = j.domicile\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = j.unit_sus_no and status_sus_no in ('Active','Inactive')"
					+ "where j.id = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("census data jco\n"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("category")); // 0
				alist.add(rs.getString("army_no")); // 1
				alist.add(rs.getString("first_name")); // 2
				alist.add(rs.getString("middle_name")); // 3
				alist.add(rs.getString("last_name")); // 4
				alist.add(rs.getString("gender")); // 5
				alist.add(rs.getString("rank")); // 6
				alist.add(rs.getString("rank_intake")); // 7
				alist.add(rs.getString("date_of_birth")); // 8
				alist.add(rs.getString("place_of_birth")); // 9
				alist.add(rs.getString("country_of_birth")); // 10
				alist.add(rs.getString("state_of_birth")); // 11
				alist.add(rs.getString("district_of_birth")); // 12
				alist.add(rs.getString("nationality")); // 13
				alist.add(rs.getString("religion")); // 14
				alist.add(rs.getString("mother_tongue"));// 15
				alist.add(rs.getString("marital_status"));// 16
				alist.add(rs.getString("blood_group"));// 17
				alist.add(rs.getString("height"));// 18
				alist.add(rs.getString("aadhar_no"));// 19
				alist.add(rs.getString("pan_no"));// 20
				alist.add(rs.getString("class_e"));// 21
				alist.add(rs.getString("domisile"));// 22
				alist.add(rs.getString("class_pay"));// 23
				alist.add(rs.getString("pay_group"));// 24
				alist.add(rs.getString("record_office_sus"));// 25
				alist.add(rs.getString("record_office"));// 26
				alist.add(rs.getString("date_of_attestation"));// 27
				alist.add(rs.getString("enroll_dt"));// 28
				alist.add(rs.getString("date_of_seniority"));// 29
				alist.add(rs.getString("trade"));// 30
				alist.add(rs.getString("date_of_tos"));// 31
				alist.add(rs.getString("arm_service"));// 32
				alist.add(rs.getString("regiment"));// 33
				alist.add(rs.getString("father_name"));// 34
				alist.add(rs.getString("father_dob"));// 35
				alist.add(rs.getString("father_place_birth"));// 36
				alist.add(rs.getString("father_service"));// 37
				alist.add(rs.getString("father_other"));// 38
				alist.add(rs.getString("father_personal_no"));// 39
				alist.add(rs.getString("profession_name"));// 40
				alist.add(rs.getString("mother_name"));// 41
				alist.add(rs.getString("mother_dob"));// 42
				alist.add(rs.getString("mother_place_birth"));// 43
				alist.add(rs.getString("mother_service"));// 44
				alist.add(rs.getString("mother_other"));// 45
				alist.add(rs.getString("mother_personal_no"));// 46
				alist.add(rs.getString("profession_name1"));// 47
				alist.add(rs.getString("id")); // 48
				alist.add(rs.getString("unit_posted_to")); // 49
				alist.add(rs.getString("unit_sus_no")); // 50
				alist.add(rs.getString("father_proffother")); // 51
				alist.add(rs.getString("mother_proffother")); // 52
				alist.add(rs.getString("date_of_rank")); // 53
				list.add(alist);
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

	public ArrayList<ArrayList<String>> getcommand_JCO(String roleSusNo) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";

			qry = "select distinct c.id,\r\n" + "fv.sus_no as command,\r\n" + "fvm.sus_no as corps,\r\n"
					+ "div.sus_no as division,\r\n" + "bde.sus_no as brigade, " + "fv.unit_name as cmd_unit,\r\n"
					+ "fvm.unit_name as corp_unit,\r\n" + "div.unit_name as div_unit,\r\n"
					+ "bde.unit_name as bde_unit \r\n" + "from tb_psg_census_jco_or_p c \r\n"
					+ "left join logininformation l on c.unit_sus_no = l.user_sus_no \r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no in ('Active','Inactive') \r\n"
					+ "left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n"
					+ "left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n"
					+ "left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n"
					+ "where c.unit_sus_no = ?";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, roleSusNo);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("command"));
				list.add(rs.getString("corps"));
				list.add(rs.getString("division"));
				list.add(rs.getString("brigade"));
				list.add(rs.getString("cmd_unit"));
				list.add(rs.getString("corp_unit"));
				list.add(rs.getString("div_unit"));
				list.add(rs.getString("bde_unit"));
				alist.add(list);

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public List<Map<String, String>> getQualificationDataSpouse(int jco_id, int status) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select distinct qua.id,dom.label as type,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year,qua.modified_by,qua.modified_date, \n"
					+ "dc.div,qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification_jco where id=qua.id), ','))   qsub\n"
					+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.status from tb_psg_census_spouse_qualification_jco qua\n"
					+ "inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n"
					+ "inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n"
					+ "inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n"
					+ "inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n"
					+ "inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE'"
					+ "where qua.jco_id=? and qua.status=? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			stmt.setInt(2, status);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getString(i));
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
	
	
	/// bisag v2 260822  (converted to Datatable)


		public long GetSearch_censusCount_jco(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String cr_by,String cr_date,String roleType) {
			
			// String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
			String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_sus_no, unit_name,personnel_no,
					 rank, status,cr_by,cr_date,roleType,roleSusNo); 
				int total = 0;
				
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					
				
					
				  q=" select count(app.*) from( select distinct jc.id,orb.unit_name,  jc.arm_service,jc.army_no," + 
				  		" jc.full_name,jc.unit_sus_no,jc.reject_remarks," + 
				  		" ltrim(TO_CHAR(jc.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth, " + 
				  		" jc.arm_service, arm.arm_desc as parent_arm,r.rank as rank,jc.modified_date " + 
				  		" FROM tb_psg_census_jco_or_p jc  " + 
				  		"left join logininformation l1 on l1.username =jc.created_by\r\n" + 
				  		" inner join tb_psg_mstr_rank_jco r on r.id = jc.rank  " + 
				  		" left join tb_miso_orbat_arm_code  arm on arm.arm_code = jc.arm_service " + 
				  		" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jc.unit_sus_no and status_sus_no='Active'"  
				  		+SearchValue+ " and jc.id != 0 and r.status = 'active' order by jc.modified_date desc) app " ;
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name,personnel_no, 
							 rank, status,cr_by,cr_date,roleType , roleSusNo);
				
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
		
		
		
		
		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
				  stmt,String Search,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status, String cr_by,String cr_date,String roleType,String roleSusNo) {
			int flag = 0;
			
			try {
				if(!Search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					

					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					
				}

				
				
				
				
				if(!unit_sus_no.equals("")) {
					flag += 1;
					
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				
				
				if(!unit_name.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_name.toUpperCase());
				}
				
				
				if(!personnel_no.equals("")) {
					flag += 1;
					stmt.setString(flag, "%"+personnel_no.toUpperCase()+"%");
				}
				if(!rank.equals("")) {
					flag += 1;
					stmt.setString(flag, rank);
				}

			if(!status.equals("")) {
					flag += 1;
					stmt.setString(flag, status);
				}
				
				
				
			if(!roleSusNo.equals("")) {
				flag += 1;
			
				stmt.setString(flag, "%"+roleSusNo.toUpperCase()+"%");
				
			}
			 if(!cr_by.equals("")) {
			        
					flag += 1;
					stmt.setString(flag, cr_by);
				}
			  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, cr_date);
				}
				
			
			
			}catch (Exception e) {}
			
			return stmt;
			
		}
		

			
		
		
				

		 public String GenerateQueryWhereClause_SQL(String Search,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status, String cr_by,String cr_date,String roleType,String roleSusNo) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" where  ";
					SearchValue +="(  upper(jc.army_no) like ? or upper(r.rank) like ? "
							+ " or upper(jc.full_name) like ? or upper(ltrim(TO_CHAR(jc.date_of_birth,'DD-MON-YYYY'),'0')) like ? or upper(jc.unit_sus_no) like ? "
							+ " or upper(arm.arm_desc)  like ? or upper(jc.reject_remarks)  like ? "
							+ "  ) ";
				}
			
				
				
				
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.sus_no  = ?";	
					}
					else {
						SearchValue += " where  orb.sus_no = ?";
					}
				}
				if( !unit_name.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.unit_name = ?  ";	
					}
					else {
						SearchValue += " where  orb.unit_name = ? ";
					}
				}
				
				if(!personnel_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += "  and upper(jc.army_no) like ? ";
					}
					else {
						SearchValue += "  where upper(jc.army_no) like ? ";
					}
					
				}			
				if(!rank.equals("")) {
					if (SearchValue.contains("where")) {
						
						SearchValue += " and cast(jc.rank as character varying) = ? ";
						//SearchValue += "  and r.id = ? ";
					}
					else {
						SearchValue += "  where  cast(jc.rank as character varying) = ? ";
					}
					
				}			
				if(!status.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and cast(jc.status as character varying) = ? ";
					}
					else {
						SearchValue += " where  cast(jc.status as character varying) = ? ";
					}
					
				}
				//260194
			         
				if( !roleSusNo.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and upper(jc.unit_sus_no) like ? ";	
					}
					else {
						SearchValue += " where upper(jc.unit_sus_no) like ?";
					}
				}
			
				 if(!cr_by.equals("")) {
						
						if (SearchValue.contains("where")) {
						
							SearchValue += " and cast(l1.userid as character varying) = ? ";				
						}
						else {
						
							SearchValue += " where cast(l1.userid as character varying) = ? ";				
						}
						
					}
				  
				  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
						if (SearchValue.contains("where")) {
							SearchValue += " and cast(jc.created_date as date) = cast(? as date)";	
						}
						else {
							SearchValue += " where cast(jc.created_date as date) = cast(? as date)";	
						}
						
						
					}
				return SearchValue;
			}
		 
		 
		 
		 public List<Map<String, Object>> GetSearch_censusdata_jco(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status, String cr_by,String cr_date,String roleType) 
			{
				
			
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				
		    	String SearchValue = GenerateQueryWhereClause_SQL(Search, unit_sus_no, unit_name,personnel_no, rank, status,cr_by,cr_date, roleType, roleSusNo);
		    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
			Connection conn = null;
			String q="";


			try{	
				String pageL = "";
		        
		        if(pageLength == -1){
					pageL = "ALL";
				}else {
					pageL = String.valueOf(pageLength);
				}
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
				
				
				
					
					
					  q=" select distinct jc.id,orb.unit_name,  jc.arm_service,jc.army_no," + 
					  		" jc.full_name,jc.unit_sus_no,jc.reject_remarks," + 
					  		" ltrim(TO_CHAR(jc.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth, " + 
					  		" jc.arm_service, arm.arm_desc as parent_arm,r.rank as rank,jc.modified_date " + 
					  		" FROM tb_psg_census_jco_or_p jc  " + 
					  		"left join logininformation l1 on l1.username =jc.created_by\r\n" + 
					  		" inner join tb_psg_mstr_rank_jco r on r.id = jc.rank  " + 
					  		" left join tb_miso_orbat_arm_code  arm on arm.arm_code = jc.arm_service " + 
					  		" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jc.unit_sus_no and status_sus_no in ('Active','Inactive')"  
					  		+SearchValue+ " and jc.id != 0 and r.status = 'active' order by jc.modified_date desc limit " +pageL+" OFFSET "+startPage+" ";
					
				
					stmt=conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name, personnel_no, rank, status ,cr_by,cr_date,roleType, roleSusNo);
					
				System.err.println("search census jco: \n"+stmt);
			      ResultSet rs = stmt.executeQuery();   
			     
			      ResultSetMetaData metaData = rs.getMetaData();
			      int columnCount = metaData.getColumnCount();
			      
			  	while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String action = "";
					String f = "";
					String f1 = "";
					String f2 = "";
					String f3 = "";
					String f4 = "";
					String f5 = "";
					String f7 = "";
					
					
					

					
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){delete1Data("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

					     String View = "onclick=\"  {ViewData(" + rs.getString("id") + ")}\"";
						f = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
						
						String Relegate = "onclick=\"  if (confirm('Are You Sure You Want to Delete ALL Data ?') ){relegateData("
								+ rs.getString("id") + ")}else{ return false;}\"";
						f4 = "<i class='fa fa-refresh' " + Relegate + " title='Delete All Data'></i>";
					
					   String Approved = "onclick=\" {open_approve(" + rs.getString("id") + ")}\"";
						f3 = "<i " + Approved
								+ " title='Approve Or Reject Data' style=' background: #31af91;border-radius: 24px;font-size: 15px; padding: 5px;'><i class='fa fa-check' style='color:white;'></i><i style='color:white;' class='fa fa-times'></i></i>";
					
					
					if (roleType.equals("ALL") || roleType.equals("DEO")) {
						if (status.equals("0")) {
							f7 += f1;
							f7 += f2;
							
						}
						if (status.equals("1")) {
							f7 += f;
							
							
						}
						if (status.equals("3")) {
							f7 += f1;
							f7 += f2;
						}
					}
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						if (status.equals("1")) {
							f7 += f;
							f7 += f4;
							
						}
						if (status.equals("0")) {
					         f7 += f3;
							
						}
						if (status.equals("3")) {
							f7 += f;
							
						}
						
					}
					columns.put("action", f7); // 5
				  list.add(columns);
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
						} catch (SQLException e) {
					  }
					}
				}
				return list;
			}
}
