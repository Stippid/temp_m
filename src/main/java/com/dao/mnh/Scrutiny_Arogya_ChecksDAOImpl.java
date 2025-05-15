package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class Scrutiny_Arogya_ChecksDAOImpl implements Scrutiny_Arogya_ChecksDAO {
	private static final List<Map<String, Object>> List = null;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_arogya_scrutiny(String check1, String yr1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";
			if (!check1.equals("-1")) {
				if (check1.equals("A1") || check1.equals("A2")) {
					qry += " where substring(r.and_no,?,?) in(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					if (check1.equals("A1")) {
						qry += " and r.rank not in(select distinct rank_code from tb_med_rankcode)";
					}

					if (check1.equals("A2")) {
						qry += " and r.category not in(select distinct category_code from tb_med_rankcode)";
					}
					flag = "N";
				}

				if (check1.equals("A3")) {
					qry += " where r.religion not in(select sys_code_value from tb_med_system_code where upper(sys_code) like upper(?))";
					flag = "N";
				}

				if (check1.equals("A4")) {
					qry += " where r.sex not in(?,?,?)";
					flag = "N";
				}

				if (check1.equals("A5")) {
					qry += " where r.nbb not in(select sys_code_value from tb_med_system_code where upper(sys_code) like upper(?))";
					flag = "N";
				}

				if (check1.equals("A6")) {
					qry += " where r.nbb=? and (r.nbb_weight=? or r.nbb_weight=? or r.nbb_weight=?)";
					flag = "N";
				}

				if (check1.equals("A7") || check1.equals("A8")) {
					qry += " where r.nbb=? and (r.name=? or r.name=? or r.name=?)";

					if (check1.equals("A7")) {
						qry += " and r.age_days between ? and ? and r.nbb_weight is null";
					}
					flag = "N";
				}

				if (check1.equals("A9")) {
					qry += " where r.nbb=? and r.relationship not in(?,?) and substring(r.and_no,?,?) in(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A10") || check1.equals("A11")) {
					if (check1.equals("A10")) {
						qry += " where r.ward";
					}
					if (check1.equals("A11")) {
						qry += " where r.discharge_ward";
					}
					qry += " not in(select sys_code_value from tb_med_system_code where upper(sys_code) like upper(?))";
					flag = "N";
				}

				if (check1.equals("A12") || check1.equals("A13") || check1.equals("A14") || check1.equals("A15")
						|| check1.equals("A16") || check1.equals("A17") || check1.equals("A18")) {
					if (check1.equals("A12")) {
						qry += " where r.nok_relation";
					}
					if (check1.equals("A13")) {
						qry += " where r.admsn_type";
					}
					if (check1.equals("A14")) {
						qry += " where r.condition1";
					}
					if (check1.equals("A15")) {
						qry += " where r.on_list";
					}
					if (check1.equals("A16")) {
						qry += " where r.persnl_marital_status";
					}
					if (check1.equals("A17")) {
						qry += " where r.marital_status";
					}
					if (check1.equals("A18")) {
						qry += " where r.arm_corps";
					}
					qry += " not in(select sys_code_value from tb_med_system_code where upper(sys_code) like upper(?))";
					flag = "N";
				}

				/*
				 * if(check1.equals("A19")) { qry +=
				 * " where r.medical_unit not in(select sus_no from tb_miso_orbat_unt_dtl where status_sus_no=?)"
				 * ; flag = "N"; }
				 */
				// Added view orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				if (check1.equals("A19")) {
					qry += " where r.medical_unit not in(select sus_no from orbat_all_details_view_mnh  where status_sus_no=?)";
					flag = "N";
				}

				if (check1.equals("A20")) {
					qry += " where r.relationship=? and r.sex<>r.persnl_sex";
					flag = "N";
				}

				if (check1.equals("A21") || check1.equals("A22") || check1.equals("A23") || check1.equals("A24")) {
					qry += " where r.persnl_unit_desc like ? and substring(r.and_no,?,?) in(?,?,?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A25") || check1.equals("A26")) {
					String u1 = " (?,?,?,?,?,?,?,?,?,?)";
					if (check1.equals("A25")) {
						qry += " where right(r.persnl_no,?) in" + u1;
					}

					if (check1.equals("A26")) {
						qry += " where left(right(r.persnl_no,?),?) not in" + u1 + " and r.persnl_no<>?";
					}
					flag = "N";
				}

				if (check1.equals("A27")) {
					qry += " where left(r.persnl_no,?) in(?,?,?,?,?,?,?,?,?,?) and left(r.and_no,?) in(?,?,?,?,?,?) and r.category in(?,?,?)";
					flag = "N";
				}

				if (check1.equals("A28") || check1.equals("A29")) {
					qry += " where r.sex=? and r.relationship in(?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A30")) {
					qry += " where left(r.diagnosis_code1d,?) in(?,?,?,?) and r.diagnosis_code1d<>r.icd_cause_code1d";
					flag = "N";
				}

				if (check1.equals("A31")) {
					qry += " where r.admsn_date > r.dschrg_date";
					flag = "N";
				}

				if (check1.equals("A32")) {
					qry += " where r.category in(?) and r.persnl_sex=?";
					flag = "N";
				}

				if (check1.equals("A33")) {
					qry += " where r.icd_remarks_d like ? and left(r.diagnosis_code1d,?)<>?";
					flag = "N";
				}

				if (check1.equals("A34")) {
					qry += " where left(r.diagnosis_code1d,?) in(?,?)";
					flag = "N";
				}

				if (check1.equals("A35")) {
					qry += " where left(r.diagnosis_code1d,?) in(?,?,?,?,?,?) "
							+ "and (left(r.icd_cause_code1d,?) not in(?,?,?,?) or r.icd_cause_code1d is null or r.icd_cause_code1d=?)";
					flag = "N";
				}

				if (check1.equals("A36")) {
					qry += " where (length(r.diagnosis_code1d)<? or length(r.diagnosis_code1d)=? or length(r.diagnosis_code1d)>?)";
					flag = "N";
				}

				if (check1.equals("A37")) {
					qry += " where (length(r.icd_cause_code1d)<? or length(r.icd_cause_code1d)=? or length(r.icd_cause_code1d)>?)";
					flag = "N";
				}

				if (check1.equals("A38")) {
					qry += " where r.disposal in(?,?,?)";
					flag = "N";
				}

				if (check1.equals("A39") || check1.equals("A40")) {
					if (check1.equals("A39")) {
						qry += " where r.diagnosis_code1d";
					}
					if (check1.equals("A40")) {
						qry += " where r.icd_cause_code1d";
					}
					qry += " not in(select icd_code from tb_med_d_disease_cause)";
					flag = "N";
				}

				if (check1.equals("A41")) {
					qry += " where r.persnl_sex=? and r.category not in(?,?)";
					flag = "N";
				}

				if (check1.equals("A42") || check1.equals("A43")) {
					String a = "";
					if (check1.equals("A42")) {
						a = "r.age_year>?";
					}
					if (check1.equals("A43")) {
						a = "r.age_year<?";
					}
					qry += " where r.relationship=? and " + a + " and substring(r.and_no,?,?)=?";
					flag = "N";
				}

				if (check1.equals("A44")) {
					qry += " where substring(r.diagnosis_code1d,?,?) in(?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A45") || check1.equals("A46") || check1.equals("A47") || check1.equals("A48")) {
					String a = "";
					if (check1.equals("A45") || check1.equals("A47")) {
						a = "r.icd_remarks_a";
					}
					if (check1.equals("A46") || check1.equals("A48")) {
						a = "r.icd_remarks_d";
					}

					if (check1.equals("A45") || check1.equals("A46")) {
						qry += " where r.admsn_type=? and (" + a + " like ? or " + a + " like ? or " + a + " like ? or "
								+ a + " like ? " + "or " + a + " like ? or " + a + " like ? or " + a + " like ? or " + a
								+ " like ? " + "or " + a + " like ? or " + a + " like ? or " + a + " like ? or " + a
								+ " like ? " + "or " + a + " like ? or " + a + " like ? or " + a + " like ?)";
					}
					if (check1.equals("A47") || check1.equals("A48")) {
						qry += " where (" + a + " like ? or " + a + " like ? or " + a + " like ? or " + a + " like ? "
								+ "or " + a + " like ? or " + a + " like ? or " + a + " like ? or " + a + " like ? "
								+ "or " + a + " like ? or " + a + " like ?)";
					}
					flag = "N";
				}

				if (check1.equals("A49")) {
					qry += " where r.relationship=? and r.persnl_sex=? and (substring(r.diagnosis_code1d,?,?)=? or substring(r.diagnosis_code1d,?,?)=?)";
					flag = "N";
				}

				if (check1.equals("A50")) {
					qry += " where left(right(r.and_no,?),?)<>substring(r.admsn_date::text,?,?)";
					flag = "N";
				}

				if (check1.equals("A51")) {
					qry += " where r.category=? and substring(r.persnl_no,?,?) not in(?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A52")) {
					qry += " where r.rank in(?,?,?,?,?,?,?,?,?,?) and r.relationship=? and r.persnl_age_year<?";
					flag = "N";
				}

				if (check1.equals("A55")) {
					qry += " where r.relationship not in(?,?) and substring(r.diagnosis_code1d,?,?) in(?,?)";
					flag = "N";
				}

				if (check1.equals("A56")) {
					qry += " where r.relationship not in(?) and (substring(r.diagnosis_code1d,?,?) in(?,?) or "
							+ "substring(r.diagnosis_code1d,?,?)>=? and substring(r.diagnosis_code1d,?,?)<=?)";
					flag = "N";
				}

				if (check1.equals("A57")) {
					qry += " where length(r.admsn_date::text)>?";
					flag = "N";
				}

				if (check1.equals("A58")) {
					qry += " where length(r.dschrg_date::text)>?";
					flag = "N";
				}

				if (check1.equals("A59")) {
					qry += " where r.persnl_age_year<=?";
					flag = "N";
				}

				if (check1.equals("A60")) {
					qry += " where (substring(r.diagnosis_code1d,?,?)=? or substring(r.icd_cause_code1d,?,?)=?)";
					flag = "N";
				}

				if (check1.equals("A61")) {
					qry += " where (substring(r.diagnosis_code1d,?,?) in(?,?,?,?,?) or substring(r.icd_cause_code1d,?,?)=?)";
					flag = "N";
				}

				if (check1.equals("A62")) {
					qry += " where (substring(r.diagnosis_code1d,?,?) in(?) or substring(r.icd_cause_code1d,?,?)=?)";
					flag = "N";
				}

				if (check1.equals("A63")) {
					qry += " where r.category=? and substring(r.persnl_no,?,?) not in(?,?) and "
							+ "substring(r.and_no,?,?) in (?,?,?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A64") || check1.equals("A65")) {
					if (check1.equals("A64")) {
						qry += " where r.category=?";
					}
					if (check1.equals("A65")) {
						qry += " where substring(r.and_no,?,?) in(?,?,?,?,?,?)";
					}
					qry += " and left(r.persnl_no,?) not in(?,?,?,?,?,?,?,?,?,?)";
					flag = "N";
				}

				if (check1.equals("A66")) {
					qry += " where r.dschrg_date is null and (r.diagnosis_code1a is null or r.diagnosis_code1a=?)";
					flag = "N";
				}

				if (check1.equals("A67")) {
					qry += " where r.persnl_no in(select persnl_no from tb_med_patient_details "
							+ "group by persnl_no, admsn_type, diagnosis_code1d having count(*)>? and admsn_type=r.admsn_type "
							+ "and r.relationship=? and r.persnl_no is not null and r.admsn_type=? and diagnosis_code1d=r.diagnosis_code1d)";
					flag = "N";
				}

				if (check1.equals("A68")) {
					qry += " where r.medical_unit in(select medical_unit from tb_med_patient_details "
							+ "group by medical_unit, and_no having count(*)>? and and_no=r.and_no)";
					flag = "N";
				}
			}

			if (!yr1.equals("")) {
				if (flag.equalsIgnoreCase("Y")) {
					if (!check1.equals("A54")) {
						qry += " where substring((r.admsn_date::text),?,?)=?";
					}
					if (check1.equals("A54")) {
						qry += " where substring((r.dschrg_date::text),?,?)=?";
					}

					flag = "N";
				} else {
					if (!check1.equals("A54")) {
						qry += " and substring((r.admsn_date::text),?,?)=?";
					}
					if (check1.equals("A54")) {
						qry += " where substring((r.dschrg_date::text),?,?)=?";
					}
				}
			}

			if (check1.equals("A1") || check1.equals("A27") || check1.equals("A52")) {
				qry += " order by r.rank";
			}
			if (check1.equals("A2")) {
				qry += " order by r.category";
			}
			if (check1.equals("A3")) {
				qry += " order by r.religion";
			}
			if (check1.equals("A4") || check1.equals("A6") || check1.equals("A7") || check1.equals("A20")
					|| check1.equals("A32") || check1.equals("A41")) {
				qry += " order by r.sex";
			}
			if (check1.equals("A5") || check1.equals("A8") || check1.equals("A9")) {
				qry += " order by r.nbb";
			}
			if (check1.equals("A10")) {
				qry += " order by r.ward";
			}
			if (check1.equals("A11")) {
				qry += " order by r.discharge_ward";
			}
			if (check1.equals("A12")) {
				qry += " order by r.nok_relation";
			}
			if (check1.equals("A13") || check1.equals("A45") || check1.equals("A46")) {
				qry += " order by r.admsn_type";
			}
			if (check1.equals("A14")) {
				qry += " order by r.condition1";
			}
			if (check1.equals("A15")) {
				qry += " order by r.on_list";
			}
			if (check1.equals("A16")) {
				qry += " order by r.persnl_marital_status";
			}
			if (check1.equals("A17")) {
				qry += " order by r.marital_status";
			}
			if (check1.equals("A18")) {
				qry += " order by r.arm_corps";
			}
			if (check1.equals("A19")) {
				qry += " order by r.medical_unit";
			}
			if (check1.equals("A21") || check1.equals("A22") || check1.equals("A23") || check1.equals("A24")) {
				qry += " order by r.persnl_unit";
			}
			if (check1.equals("A25") || check1.equals("A26") || check1.equals("A67")) {
				qry += " order by r.persnl_no";
			}
			if (check1.equals("A28") || check1.equals("A29") || check1.equals("A55")) {
				qry += " order by r.relationship";
			}
			if (check1.equals("A30") || check1.equals("A38") || check1.equals("A39") || check1.equals("A44")
					|| check1.equals("A47") || check1.equals("A48") || check1.equals("A49") || check1.equals("A50")
					|| check1.equals("A56") || check1.equals("A60") || check1.equals("A61") || check1.equals("A62")
					|| check1.equals("A63") || check1.equals("A64")) {
				qry += " order by r.diagnosis_code1d";
			}
			if (check1.equals("A31") || check1.equals("A57")) {
				qry += " order by r.admsn_date;";
			}
			if (check1.equals("A33") || check1.equals("A34") || check1.equals("A35") || check1.equals("A36")) {
				qry += " order by r.icd_remarks_d;";
			}
			if (check1.equals("A37") || check1.equals("A40")) {
				qry += " order by r.icd_cause_code1d;";
			}
			if (check1.equals("A42") || check1.equals("A43")) {
				qry += " order by r.age_year;";
			}
			if (check1.equals("A51")) {
				qry += " order by r.persnl_unit_desc;";
			}
			if (check1.equals("A58")) {
				qry += " order by r.dschrg_date;";
			}
			if (check1.equals("A59")) {
				qry += " order by r.persnl_age_year;";
			}
			if (check1.equals("A65") || check1.equals("A66") || check1.equals("A68")) {
				qry += " order by r.and_no;";
			}

			q = "select r.id,r.medical_unit,r.and_no,r.name,r.relationship,r.persnl_no,r.category,r.rank,r.religion,r.sex,r.persnl_sex,"
					+ "r.nbb,r.nbb_weight,r.ward,r.discharge_ward,r.nok_relation,r.admsn_type,r.condition1,r.on_list,r.persnl_marital_status,"
					+ "r.marital_status,r.arm_corps,r.persnl_unit,r.persnl_unit_desc,r.diagnosis_code1d,r.icd_cause_code1d,r.admsn_date,"
					+ "r.dschrg_date,r.icd_remarks_a,r.icd_remarks_d,r.disposal,r.age_year,r.age_month,r.age_days,r.diagnosis_code1a,r.icd_cause_code1a "
					+ "from tb_med_patient_details r" + qry;

			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!check1.equals("-1")) {
				if (check1.equals("A3")) {
					stmt.setString(j, "RELIGION");
					j++;
				}
				if (check1.equals("A4")) {
					stmt.setString(j, "M");
					j++;
					stmt.setString(j, "F");
					j++;
					stmt.setString(j, "T");
					j++;
				}
				if (check1.equals("A5")) {
					stmt.setString(j, "NBB");
					j++;
				}
				if (check1.equals("A6")) {
					stmt.setString(j, "Y");
					j++;
					stmt.setNull(j, Types.FLOAT);
					j++;
					stmt.setInt(j, 0);
					j++;
					stmt.setFloat(j, 0);
					j++;
				}
				if (check1.equals("A7") || check1.equals("A8")) {
					stmt.setString(j, "N");
					j++;
					stmt.setString(j, "NBB");
					j++;
					stmt.setString(j, "N B B");
					j++;
					stmt.setString(j, "NEW BORN BABY");
					j++;
					if (check1.equals("A7")) {
						stmt.setInt(j, 0);
						j++;
						stmt.setInt(j, 10);
						j++;
					}
				}
				if (check1.equals("A9")) {
					stmt.setString(j, "Y");
					j++;
					stmt.setString(j, "SONOF");
					j++;
					stmt.setString(j, "DAUGHTEROF");
					j++;
				}
				if (check1.equals("A10") || check1.equals("A11")) {
					stmt.setString(j, "WARD");
					j++;
				}
				if (check1.equals("A12")) {
					stmt.setString(j, "RELATION");
					j++;
				}
				if (check1.equals("A13")) {
					stmt.setString(j, "ADMSNTYPE");
					j++;
				}
				if (check1.equals("A14")) {
					stmt.setString(j, "PATCONDTN");
					j++;
				}
				if (check1.equals("A15")) {
					stmt.setString(j, "ONLIST");
					j++;
				}
				if (check1.equals("A16") || check1.equals("A17")) {
					stmt.setString(j, "MRTLSTAT");
					j++;
				}
				if (check1.equals("A18")) {
					stmt.setString(j, "ARMCORP");
					j++;
				}
				if (check1.equals("A19")) {
					stmt.setString(j, "Active");
					j++;
				}
				if (check1.equals("A20")) {
					stmt.setString(j, "SELF");
					j++;
				}
				if (check1.equals("A21")) {
					stmt.setString(j, "%ASSAM RIF%");
					j++;
				}
				if (check1.equals("A22")) {
					stmt.setString(j, "%AGREF%");
					j++;
				}
				if (check1.equals("A23")) {
					stmt.setString(j, "%ITBP%");
					j++;
				}
				if (check1.equals("A24")) {
					stmt.setString(j, "%VIKAS%");
					j++;
				}
				if (check1.equals("A25") || check1.equals("A26") || check1.equals("A27")) {
					if (check1.equals("A25") || check1.equals("A27")) {
						stmt.setInt(j, 1);
						j++;
					}
					if (check1.equals("A26")) {
						stmt.setInt(j, 2);
						j++;
						stmt.setInt(j, 1);
						j++;
					}

					stmt.setString(j, "0");
					j++;
					stmt.setString(j, "1");
					j++;
					stmt.setString(j, "2");
					j++;
					stmt.setString(j, "3");
					j++;
					stmt.setString(j, "4");
					j++;
					stmt.setString(j, "5");
					j++;
					stmt.setString(j, "6");
					j++;
					stmt.setString(j, "7");
					j++;
					stmt.setString(j, "8");
					j++;
					stmt.setString(j, "9");
					j++;

					if (check1.equals("A26")) {
						stmt.setString(j, "NYA");
						j++;
					}

					if (check1.equals("A27")) {
						stmt.setInt(j, 2);
						j++;
						stmt.setString(j, "AR");
						j++;
						stmt.setString(j, "RR");
						j++;
						stmt.setString(j, "DS");
						j++;
						stmt.setString(j, "MC");
						j++;
						stmt.setString(j, "MA");
						j++;
						stmt.setString(j, "DA");
						j++;

						stmt.setString(j, "OFFICER");
						j++;
						stmt.setString(j, "MNS");
						j++;
						stmt.setString(j, "JCO");
						j++;
					}
				}

				if (check1.equals("A28")) {
					stmt.setString(j, "F");
					j++;
					stmt.setString(j, "FATHEROF");
					j++;
					stmt.setString(j, "HUSBANDOF");
					j++;
					stmt.setString(j, "SONOF");
					j++;
					stmt.setString(j, "BROTHEROF");
					j++;
				}

				if (check1.equals("A29")) {
					stmt.setString(j, "M");
					j++;
					stmt.setString(j, "MOTHEROF");
					j++;
					stmt.setString(j, "DAUGHTEROF");
					j++;
					stmt.setString(j, "WIFEOF");
					j++;
					stmt.setString(j, "SISTEROF");
					j++;
				}

				if (check1.equals("A30")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, "V");
					j++;
					stmt.setString(j, "W");
					j++;
					stmt.setString(j, "X");
					j++;
					stmt.setString(j, "Y");
					j++;
				}

				if (check1.equals("A32")) {
					stmt.setString(j, "MNS");
					j++;
					stmt.setString(j, "M");
					j++;
				}

				if (check1.equals("A33")) {
					stmt.setString(j, "%NAD%");
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "Z03");
					j++;
				}

				if (check1.equals("A34")) {
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "AAA");
					j++;
					stmt.setString(j, "Z09");
					j++;
				}

				if (check1.equals("A35")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, "S");
					j++;
					stmt.setString(j, "T");
					j++;
					stmt.setString(j, "V");
					j++;
					stmt.setString(j, "W");
					j++;
					stmt.setString(j, "X");
					j++;
					stmt.setString(j, "Y");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, "V");
					j++;
					stmt.setString(j, "W");
					j++;
					stmt.setString(j, "X");
					j++;
					stmt.setString(j, "Y");
					j++;
					stmt.setString(j, "");
					j++;
				}

				if (check1.equals("A36") || check1.equals("A37")) {
					stmt.setInt(j, 3);
					j++;
					stmt.setInt(j, 4);
					j++;
					stmt.setInt(j, 5);
					j++;
				}

				if (check1.equals("A38")) {
					stmt.setString(j, "FOUNDDEAD");
					j++;
					stmt.setString(j, "DEATH");
					j++;
					stmt.setString(j, "S/BIRTH");
					j++;
				}

				if (check1.equals("A41")) {
					stmt.setString(j, "F");
					j++;
					stmt.setString(j, "OFFICER");
					j++;
					stmt.setString(j, "MNS");
					j++;
				}

				if (check1.equals("A42") || check1.equals("A43")) {
					stmt.setString(j, "SELF");
					j++;
					if (check1.equals("A42")) {
						stmt.setInt(j, 60);
						j++;
					}
					if (check1.equals("A43")) {
						stmt.setInt(j, 17);
						j++;
					}
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 2);
					j++;
					stmt.setString(j, "AR");
					j++;
				}

				if (check1.equals("A44")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "A20");
					j++;
					stmt.setString(j, "A22");
					j++;
					stmt.setString(j, "A80");
					j++;
					stmt.setString(j, "A82");
					j++;
				}

				if (check1.equals("A45") || check1.equals("A46")) {
					stmt.setString(j, "FRESH");
					j++;
					stmt.setString(j, "%REVIEW%");
					j++;
					stmt.setString(j, "%RE-VIEW%");
					j++;
					stmt.setString(j, "%RE VIEW%");
					j++;
					stmt.setString(j, "%RECAT%");
					j++;
					stmt.setString(j, "%RE-CAT%");
					j++;
					stmt.setString(j, "%FOR RECAT%");
					j++;
					stmt.setString(j, "%FOR ECAT%");
					j++;
					stmt.setString(j, "%FOR RMB%");
					j++;
					stmt.setString(j, "%RMB%");
					j++;
					stmt.setString(j, "%RSMB%");
					j++;
					stmt.setString(j, "%FOR RSMB%");
					j++;
					stmt.setString(j, "%AFTER S/L%");
					j++;
					stmt.setString(j, "%AFTER SICK%");
					j++;
					stmt.setString(j, "%A/S/L%");
					j++;
					stmt.setString(j, "%R/A/S/L%");
					j++;
				}

				if (check1.equals("A47") || check1.equals("A48")) {
					stmt.setString(j, "%POISON%");
					j++;
					stmt.setString(j, "%SUICI%");
					j++;
					stmt.setString(j, "%SELF%");
					j++;
					stmt.setString(j, "%GUN%");
					j++;
					stmt.setString(j, "%GSW%");
					j++;
					stmt.setString(j, "%HANG%");
					j++;
					stmt.setString(j, "%RIFLE%");
					j++;
					stmt.setString(j, "%FIREARM%");
					j++;
					stmt.setString(j, "%DROWN%");
					j++;
					stmt.setString(j, "%ASSAULT%");
					j++;
				}

				if (check1.equals("A49")) {
					stmt.setString(j, "SELF");
					j++;
					stmt.setString(j, "M");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, "O");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "C50");
					j++;
				}

				if (check1.equals("A50")) {
					stmt.setInt(j, 5);
					j++;
					stmt.setInt(j, 2);
					j++;
					stmt.setInt(j, 0);
					j++;
					stmt.setInt(j, 1);
					j++;
				}

				if (check1.equals("A51")) {
					stmt.setString(j, "MNS");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 2);
					j++;
					stmt.setString(j, "NR");
					j++;
					stmt.setString(j, "NS");
					j++;
					stmt.setString(j, "NL");
					j++;
					stmt.setString(j, "PN");
					j++;
				}

				if (check1.equals("A52")) {
					stmt.setString(j, "ACHIEFMSH");
					j++;
					stmt.setString(j, "Air Mshl");
					j++;
					stmt.setString(j, "AVM");
					j++;
					stmt.setString(j, "Adm");
					j++;
					stmt.setString(j, "Vice Adm");
					j++;
					stmt.setString(j, "Rear Adm");
					j++;
					stmt.setString(j, "FdMarshall");
					j++;
					stmt.setString(j, "General");
					j++;
					stmt.setString(j, "Lt Gen");
					j++;
					stmt.setString(j, "Maj Gen");
					j++;
					stmt.setString(j, "SELF");
					j++;
					stmt.setInt(j, 50);
					j++;
				}

				if (check1.equals("A55")) {
					stmt.setString(j, "SONOF");
					j++;
					stmt.setString(j, "DAUGHTEROF");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "Z37");
					j++;
					stmt.setString(j, "Z38");
					j++;
				}

				if (check1.equals("A56")) {
					stmt.setString(j, "WIFEOF");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "O80");
					j++;
					stmt.setString(j, "O82");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "O02");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					stmt.setString(j, "O20");
					j++;
				}

				if (check1.equals("A57") || check1.equals("A58")) {
					stmt.setInt(j, 10);
					j++;
				}

				if (check1.equals("A59")) {
					stmt.setInt(j, 15);
					j++;
				}

				if (check1.equals("A60") || check1.equals("A61") || check1.equals("A62")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					if (check1.equals("A60")) {
						stmt.setString(j, "T67");
						j++;
					}
					if (check1.equals("A61")) {
						stmt.setString(j, "T33");
						j++;
						stmt.setString(j, "T34");
						j++;
						stmt.setString(j, "T35");
						j++;
						stmt.setString(j, "T68");
						j++;
						stmt.setString(j, "T69");
						j++;
					}
					if (check1.equals("A62")) {
						stmt.setString(j, "T70");
						j++;
					}
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 3);
					j++;
					if (check1.equals("A60")) {
						stmt.setString(j, "X30");
						j++;
					}
					if (check1.equals("A61")) {
						stmt.setString(j, "X31");
						j++;
					}
					if (check1.equals("A62")) {
						stmt.setString(j, "W94");
						j++;
					}
				}

				if (check1.equals("A63")) {
					stmt.setString(j, "JCO");
					j++;
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 2);
					j++;
					stmt.setString(j, "JC");
					j++;
					stmt.setString(j, "TJ");
					j++;
				}

				if (check1.equals("A64") || check1.equals("A65")) {
					if (check1.equals("A64")) {
						stmt.setString(j, "OR");
						j++;
					}
					if (check1.equals("A65")) {
						stmt.setInt(j, 1);
						j++;
						stmt.setInt(j, 2);
						j++;
						stmt.setString(j, "AF");
						j++;
						stmt.setString(j, "IN");
						j++;
						stmt.setString(j, "XF");
						j++;
						stmt.setString(j, "XH");
						j++;
						stmt.setString(j, "XN");
						j++;
						stmt.setString(j, "XS");
						j++;
					}
					stmt.setInt(j, 1);
					j++;
					stmt.setString(j, "0");
					j++;
					stmt.setString(j, "1");
					j++;
					stmt.setString(j, "2");
					j++;
					stmt.setString(j, "3");
					j++;
					stmt.setString(j, "4");
					j++;
					stmt.setString(j, "5");
					j++;
					stmt.setString(j, "6");
					j++;
					stmt.setString(j, "7");
					j++;
					stmt.setString(j, "8");
					j++;
					stmt.setString(j, "9");
					j++;
				}

				if (check1.equals("A66")) {
					stmt.setString(j, "");
					j++;
				}

				if (check1.equals("A67") || check1.equals("A68")) {
					stmt.setInt(j, 1);
					j++;
					if (check1.equals("A67")) {
						stmt.setString(j, "SELF");
						j++;
						stmt.setString(j, "FRESH");
						j++;
					}
				}

				if (check1.equals("A1") || check1.equals("A2") || check1.equals("A9") || check1.equals("A21")
						|| check1.equals("A22") || check1.equals("A23") || check1.equals("A24")
						|| check1.equals("A63")) {
					stmt.setInt(j, 1);
					j++;
					stmt.setInt(j, 2);
					j++;
					stmt.setString(j, "AR");
					j++;
					stmt.setString(j, "RR");
					j++;
					stmt.setString(j, "DS");
					j++;
					stmt.setString(j, "MC");
					j++;
					stmt.setString(j, "MA");
					j++;
					stmt.setString(j, "DA");
					j++;

					if (check1.equals("A1") || check1.equals("A2") || check1.equals("A9")) {
						stmt.setString(j, "AF");
						j++;
						stmt.setString(j, "IN");
						j++;
						stmt.setString(j, "XR");
						j++;
						stmt.setString(j, "XS");
						j++;
						stmt.setString(j, "XF");
						j++;
						stmt.setString(j, "XH");
						j++;
						stmt.setString(j, "XN");
						j++;
						stmt.setString(j, "XE");
						j++;
					}
				}
			}

			if (!yr1.equals("")) {
				stmt.setInt(j, 1);
				j++;
				stmt.setInt(j, 4);
				j++;
				stmt.setString(j, yr1);
				j++;
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData
						+ " title='Edit Scrutiny Data'></i>";
				String f = "";
				f += updateButton;
				if (check1.equals("A68")) {
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";
					f += deleteButton;
				}

				columns.put(metaData.getColumnLabel(1), f);

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
