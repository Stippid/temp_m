package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class Re_EmploymentDAOImpl implements Re_EmploymentDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public List<Map<String, Object>> getre_emp_call_data(String personnel_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select c.id,c.cadet_no,c.course,g.gender_name,c.date_of_birth ,c.name,cu.description," +
					"	 cu1.description as appt,a.appointment,a.date_of_appointment , " +
					"	 ct.course_name, c.parent_arm,c.regiment,cu.code as rankcode  " +
					"	 from tb_psg_trans_proposed_comm_letter c " +
					"	 inner join tb_psg_mstr_gender g on g.id=c.gender " +
					"	 inner join tb_psg_mstr_course_comm ct on c.course=ct.id " +
					"	 inner join cue_tb_psg_rank_app_master cu on cu.id=c.rank  and cu.status_active = 'Active'" +
					"	 left join tb_psg_change_of_appointment a on a.comm_id=c.id " +
					"	 left join cue_tb_psg_rank_app_master cu1 on cu1.id =a.appointment and cu1.status_active = 'Active'" +
					"	 where upper(personnel_no) = ?  " +  /*and c.status='4'*/
					"	  order by personnel_no";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, personnel_no);

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
	@Override
	public ArrayList<ArrayList<String>> search_re_employment(String unit_name,String unit_sus_no,String personnel_no ,String status,String cr_by,String cr_date,String type_radio,String roleSusNo,String roleType) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		String QUERY_RE = "SELECT distinct\n"
				+ "	RE.ID,\n"
				+ "B.PERSONNEL_NO,\n"
				+ "	B.NAME,\n"
				+ "	R.DESCRIPTION AS APPT,\n"
				+ "	RE.UNIT_SUS_NO AS UNIT_SUS_NO,\n"
				+ "	M.UNIT_NAME,\n"
				+ "	TO_CHAR(A.DATE_OF_APPOINTMENT, 'DD-MON-YYYY') AS DATE_OF_APPT,\n"
				+ "	COALESCE(TO_CHAR(RE.DATE_OF_TOS, 'DD-MON-YYYY'), '') AS DATE_OF_TOS,\n"
				+ "	RE.AUTHORITY,\n"
				+ "	TO_CHAR(RE.AUTH_DT, 'DD-MON-YYYY') AS DATE_OF_AUTHORITY,\n"
				+ "	RE.CENSUS_ID,\n"
				+ "	RE.COMM_ID,\n"
				+ "	COALESCE((TO_CHAR(ER.FROM_DT, 'DD-MON-YYYY')), '') AS FROM_DT,\n"
				+ "	COALESCE((TO_CHAR(ER.TO_DT, 'DD-MON-YYYY')), '') AS TO_DT,\n"
				+ "	COALESCE(\n"
				+ "	TO_CHAR(NF.DATE_OF_NON_EFFECTIVE, 'DD-MON-YYYY'),\n"
				+ "		''\n"
				+ "	) AS DATE_OF_NON_EFFECTIVE,\n"
				+ "	COALESCE((TO_CHAR(RE.GRANTED_FR_DT, 'DD-MON-YYYY')), '') AS GRANTED_FR_DT,\n"
				+ "	RE.REJECT_REMARKS,\n"
				+ "	CASE WHEN  RE.STATUS IN ('0','3') THEN 'Re-Employed' ELSE 'N/A' END AS status_type\n"
				+ "	FROM\n"
				+ "	TB_PSG_RE_EMPLOYMENT RE\n"
				+ "	INNER JOIN TB_MISO_ORBAT_UNT_DTL M ON M.SUS_NO = RE.UNIT_SUS_NO AND M.STATUS_SUS_NO = 'Active'\n"
				+ "	INNER JOIN TB_PSG_TRANS_PROPOSED_COMM_LETTER B ON RE.COMM_ID = B.ID\n"
				+ "	LEFT JOIN LOGININFORMATION L1 ON L1.USERNAME = RE.CREATED_BY\n"
				+ "	INNER JOIN CUE_TB_PSG_RANK_APP_MASTER CU ON CU.ID = B.RANK AND CU.STATUS_ACTIVE = 'Active'\n"
				+ "	LEFT JOIN TB_PSG_CHANGE_OF_APPOINTMENT A ON A.COMM_ID = B.ID AND A.STATUS = '1'\n"
				+ "	LEFT JOIN CUE_TB_PSG_RANK_APP_MASTER R ON R.ID = A.APPOINTMENT AND R.STATUS_ACTIVE = 'Active'\n"
				+ "	LEFT JOIN TB_PSG_RE_EMP_EXTENTION ER ON ER.RE_EMP_ID = RE.ID AND ER.STATUS = 1\n"
				+ "	INNER JOIN TB_PSG_NON_EFFECTIVE NF ON NF.COMM_ID = RE.COMM_ID AND NF.STATUS = 1 \n"
				+ "WHERE RE.ID != 0 AND RE_EMP_SELECT = '2' \n";


		String QUERY_NF = "select  distinct\n"
				+ "NF.ID,\n"
				+ "	B.PERSONNEL_NO,\n"
				+ "	B.NAME,\n"
				+ "	R.DESCRIPTION AS APPT,\n"
				+ "	b.UNIT_SUS_NO AS UNIT_SUS_NO,\n"
				+ "	M.UNIT_NAME,\n"
				+ "	TO_CHAR(A.DATE_OF_APPOINTMENT, 'DD-MON-YYYY') AS DATE_OF_APPT,\n"
				+ "	COALESCE(TO_CHAR(b.DATE_OF_TOS, 'DD-MON-YYYY'), '') AS DATE_OF_TOS,\n"
				+ "	nf.AUTHORITY,\n"
				+ "	TO_CHAR(nf.date_of_authority_non_ef, 'DD-MON-YYYY') AS DATE_OF_AUTHORITY,\n"
				+ "	nf.CENSUS_ID,\n"
				+ "	nf.COMM_ID,\n"
				+ "	COALESCE((TO_CHAR(ER.FROM_DT, 'DD-MON-YYYY')), '') AS FROM_DT,\n"
				+ "	COALESCE((TO_CHAR(ER.TO_DT, 'DD-MON-YYYY')), '') AS TO_DT,\n"
				+ "	COALESCE(\n"
				+ "		TO_CHAR(NF.DATE_OF_NON_EFFECTIVE, 'DD-MON-YYYY'),\n"
				+ "		''\n"
				+ "	) AS DATE_OF_NON_EFFECTIVE,\n"
				+ "COALESCE((TO_CHAR(NF.DATE_OF_NON_EFFECTIVE, 'DD-MON-YYYY')), '') AS GRANTED_FR_DT, \n"
				+ "	nf.REJECT_REMARKS,\n"
				+ " CASE  WHEN NF.STATUS IN ('0','3') THEN 'Non-Effective' ELSE 'N/A' END AS status_type\n"
				+ "from TB_PSG_NON_EFFECTIVE nf\n"
				+ "left JOIN TB_PSG_TRANS_PROPOSED_COMM_LETTER B ON nf.COMM_ID = B.ID\n"
				+ "left JOIN TB_MISO_ORBAT_UNT_DTL M ON M.SUS_NO = b.UNIT_SUS_NO \n"
				+ "LEFT JOIN LOGININFORMATION L1 ON L1.USERNAME = nf.CREATED_BY\n"
				+ "	left JOIN CUE_TB_PSG_RANK_APP_MASTER CU ON CU.ID = B.RANK\n"
				+ "	LEFT JOIN TB_PSG_CHANGE_OF_APPOINTMENT A ON A.COMM_ID = B.ID \n"
				+ "	LEFT JOIN CUE_TB_PSG_RANK_APP_MASTER R ON R.ID = A.APPOINTMENT \n"
				+ "	LEFT JOIN TB_PSG_RE_EMP_EXTENTION ER ON ER.RE_EMP_ID = nf.ID\n"
				+ "WHERE nf.ID != 0 \n";


		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(!unit_sus_no.equals("")) {
				qry += "  and m.sus_no = ?";
			}
			/*if(!unit_name.equals("")) {
				qry += "  and m.unit_name = ?";
			}*/
			if(!roleSusNo.equals("")) {
				qry+=" and re.unit_sus_no = ?";
			}
			if(!personnel_no.equals("")) {
				qry += "  and  b.personnel_no = ? ";
			}
			//			if(status.equals("0")) {
			//				qry += " and re.status = '0'  ";  //and cl.update_ofr_status = '0'
			//			}
			//			else if(status.equals("1")) {
			//				qry += " and re.status = '1' ";     //and cl.update_ofr_status = '1'
			//			}
			//			else if(status.equals("3")) {
			//				qry += " and re.status = '3' ";			//	and cl.update_ofr_status = '2'
			//			}
			//			else if (status.equals("4")) {
			//				qry += "and re.status not in (0,3)  ";
			//				if(roleType.equals("DEO")){
			//					qry += "and  re.cancel_status in (-1,2)  ";
			//				}
			//				else if(roleType.equals("APP")) {
			//					qry += "and  re.cancel_status=0  ";
			//				}
			//			}
			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if(!cr_date.equals("")) {
				qry +=" and cast(re.created_date as date) = cast(? as date)";
			}

			if(status.equals("4")) {
				//KAJAL UPDATE ON 24/06/2022
				q="select re.id,re.status,re.modified_date,mrh.created_date as mrh_c,mrh.modified_date as mer_m, b.personnel_no,b.name,r.description as appt, re.unit_sus_no as unit_sus_no, m.unit_name,\n" +
						"			 re.unit_posted_to,ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt,\n" +
						"			 TO_CHAR(re.date_of_tos,'DD-MON-YYYY')as date_of_tos,re.authority, TO_CHAR(re.auth_dt,'DD-MON-YYYY') as date_of_authority,\n" +
						"			 re.census_id,re.comm_id,\n" +
						" COALESCE((TO_CHAR(er.from_dt,'DD-MON-YYYY')),'') as from_dt,COALESCE((TO_CHAR(er.to_dt,'DD-MON-YYYY')),'') as to_dt\n" +
						"			 ,COALESCE((TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY')),'') as date_of_non_effective "+
						"	,COALESCE((TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY')),'') as granted_fr_dt,re.reject_remarks "
						+ "		 from tb_psg_re_employment re \n" +
						"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no  and m.status_sus_no='Active'\n" +
						"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
						"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
						"			a on a.comm_id=b.id and a.status='1' left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' \n" +
						"			left join tb_psg_miso_role_hdr_status  mrh on mrh.comm_id=re.comm_id\n" +
						"			left join tb_psg_census_detail_p cp on cp.comm_id=re.comm_id	"
						+ " left join tb_psg_re_emp_extention er on er.re_emp_id = re.id  and er.status = 1 \n" +
						"			inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id   \n" +
						"left join logininformation l1 on l1.username = re.created_by\r\n" +
						"			where re.id in (select id from tb_psg_re_employment  where comm_id=re.comm_id  order by id desc limit 1) 			 \n" +
						"			and ?=(select user_sus_no from logininformation where username=re.created_by )\n" +
						"			and ((mrh.modified_date is null and mrh.created_date is null ) or (mrh.created_date is not null and mrh.modified_date is null and mrh.created_date<=re.modified_date )			 	 \n" +
						"			 or ( mrh.modified_date is not null and mrh.modified_date<=re.modified_date )) \n" +
						"			-- and ( cp.modified_date is not null and cp.modified_date<=re.modified_date ) \n" +
						"			 and  re_emp_select='2'  and nf.status in (1)   \n" +
						"			 and (select count(id) from tb_psg_re_emp_extention where re_emp_id =re.id and status::text != '-1')=0 "+qry+
						" order by re.id desc \n" ;
			}
			else if(status.equals("0")) {
				q = QUERY_RE + " AND RE.STATUS = '0' " + " UNION ALL " + QUERY_NF + " AND NF.STATUS = '0' ";

				if(type_radio != null && type_radio.equals("nonEffective")) {
					q = QUERY_NF + " AND NF.STATUS = '0' " + qry + "order by id desc";
				} else if (type_radio != null && type_radio.equals("reEmployement")) {
					q = QUERY_RE  + " AND RE.STATUS = '0' " + qry + "order by id desc";
				}

			} else if (status.equals("1")) {
				if(type_radio != null && type_radio.equals("nonEffective")) {
					q = QUERY_NF + " AND NF.STATUS = '1' " + qry + "order by id desc";
				} else if (type_radio != null && type_radio.equals("reEmployement")) {
					q = QUERY_RE + " AND RE.STATUS = '1' " + qry + "order by id desc";
				}

			} else if (status.equals("3")) {
				if(type_radio != null && type_radio.equals("nonEffective")) {
					q = QUERY_NF + " AND NF.STATUS = '3' " + qry + "order by id desc";
				} else if (type_radio != null && type_radio.equals("reEmployement")) {
					q = QUERY_RE + " AND RE.STATUS = '3' " + qry + "order by id desc";
				}

			} else if (status.equals("4")) {
				if(type_radio != null && type_radio.equals("nonEffective")) {
					q = QUERY_NF + " AND NF.STATUS NOT IN (0,3) " + qry + "order by id desc";
					if(roleType.equals("DEO")){
						qry += "and  nf.cancel_status in (-1,2)  ";
					}
					else if(roleType.equals("APP")) {
						qry += "and  nf.cancel_status=0  ";
					}
				} else if (type_radio != null && type_radio.equals("reEmployement")) {
					q = QUERY_RE + " AND RE.STATUS NOT IN (0,3) " + qry + "order by id desc";
					if(roleType.equals("DEO")){
						qry += "and  re.cancel_status in (-1,2)  ";
					}
					else if(roleType.equals("APP")) {
						qry += "and  re.cancel_status=0  ";
					}
				}
			}

			stmt = conn.prepareStatement(q);
			int j =1;


			if(!qry.equals(""))
			{
				if(status.equals("4")) {
					stmt.setString(j, roleSusNo);
					j++;
				}
				if(!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				/*if(!unit_name.equals("")) {
					stmt.setString(j, unit_name);
					j += 1;
				} */

				if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !personnel_no.equals("")) {
					stmt.setString(j, personnel_no );
					j += 1;
				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
					stmt.setString(j, cr_date);
					j += 1;

				}

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("Uqery --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("personnel_no"));
				list.add(rs.getString("name"));
				list.add(rs.getString("appt"));
				list.add(rs.getString("date_of_appt"));
				list.add(rs.getString("unit_sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("date_of_tos"));
				list.add(rs.getString("date_of_non_effective"));
				list.add(rs.getString("granted_fr_dt"));
				list.add(rs.getString("from_dt"));
				list.add(rs.getString("to_dt"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				list.add(rs.getString("status_type"));

				String f = "";
				String f1 = "";
				String f2 = "";

				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){Approve("
							+ rs.getString("id") + ","+ rs.getString("comm_id") +",'"+rs.getString("status_type")+"')}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter?') ){addRemarkModel2('Reject', '"+rs.getString("id")+"','"+rs.getString("comm_id")+"','"+rs.getString("status_type")+"')}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
				}

				if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("
							+ rs.getString("id") + ","+ rs.getString("census_id") +","+ rs.getString("comm_id") +",'"+rs.getString("status_type")+"')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				}
				else if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("
							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' )}else{ return false;}\"";
					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("
							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";
					f1="<i class='action_icons action_reject' "+rejectData+" title='Reject Data'></i></a>";
				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
				{
					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("
							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";
					f="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";
				}

				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(rs.getString("reject_remarks"));
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

	/*@Override
	public ArrayList<ArrayList<String>> search_re_call(String personnel_no1,
			String status, String unit_sus_no, String unit_name,
			String roleSusNo, String roleType,String roleAccess) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;





	 * if (!roleSusNo.equals("")) { qry += " and b.unit_sus_no = ?"; }
	 * if (!unit_sus_no.equals("")) { qry += "  and orb.sus_no = ?"; }
	 * if (!unit_name.equals("")) { qry += "  and orb.unit_name = ?"; }
	 * if(!personnel_no1.equals("")) { qry +=
	 * "  and upper(b.personnel_no) like ? "; } if(status.equals("0")) {
	 * qry += " and re.status = '0'  "; //and cl.update_ofr_status = '0'
	 * } if(status.equals("1")) { qry += " and re.status = '1' "; //and
	 * cl.update_ofr_status = '1' } if(status.equals("3")) { qry +=
	 * " and re.status = '3' "; // and cl.update_ofr_status = '2' }


			if (!roleSusNo.equals("")) {
				qry += " and re.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}

			if (!personnel_no1.equals("")) {
				qry += "  and upper(b.personnel_no) like ? ";
			}

			if(status.equals("0")) {
				qry += " and re.status = '0'  "; // and cl.update_ofr_status =
													// '0'
			}
			else if (status.equals("1")) {
				qry += " and re.status = '1' ";
			}
			else if (status.equals("3")) {
				qry += " and re.status = '3' ";
			}
			else if (status.equals("4")) {
				qry += "and re.status not in (0,3)  ";
				if(roleType.equals("DEO")){
					qry += "and  re.cancel_status in (-1,2)  ";
				}
				else if(roleType.equals("APP")) {
					qry += "and  re.cancel_status=0  ";
				}
			}

			q= "select re.id,b.personnel_no,b.name,r.description as appt, re.unit_sus_no as unit_sus_no,ltrim(TO_CHAR(re.date_of_appt,'DD-MON-YYYY'),'0') as date_of_appt, \r\n" +
					" re.unit_posted_to,\r\n" +
					" ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" +
					" re.census_id,re.comm_id,\r\n" +
					" ltrim(TO_CHAR(re.to_dt,'DD-MON-YYYY'),'0') as to_dt, ltrim(TO_CHAR(re.from_dt,'DD-MON-YYYY'),'0') as from_dt\r\n" +
					" from tb_psg_re_employment re \r\n" +
					" inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \r\n" +

					"inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  "+
					"left join tb_psg_change_of_appointment a on a.comm_id=b.id "+
					"left join cue_tb_psg_rank_app_master r on r.id =a.appointment"+
					" where re.id !=  0 and  re.unit_sus_no = ?   and  re_emp_select='1'  "+qry
					+ " order by re.id desc";


			if(status.equals("4")) {
				q = "select distinct re.id,re.status,re.modified_date,mrh.created_date as mrh_c,mrh.modified_date as mer_m, re.comm_id,b.personnel_no,b.name,COALESCE(r.description,'') as appt, re.unit_sus_no as unit_sus_no,\n" +
						"			ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt, m.unit_name,\n" +
						"			 re.unit_posted_to,\n" +
						"	(select user_sus_no from logininformation where username=re.created_by ) unit_sus,"+
						"			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n" +
						"			 re.census_id,re.comm_id,\n" +
						"			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt,ltrim(TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective,re.reject_remarks \n" +
						"			 from tb_psg_re_employment re \n" +
						"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n" +
						"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
						"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
						"			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active'\n" +
						"			left join tb_psg_miso_role_hdr_status  mrh on mrh.comm_id=re.comm_id\n" +
						"	left join tb_psg_census_detail_p cp on cp.comm_id=re.comm_id "
						+ " inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1  "+
						"			where re.id in (select id from tb_psg_re_employment  where comm_id=re.comm_id  order by id desc limit 1) 			 \n" +
						"			and ?=(select user_sus_no from logininformation where username=re.created_by )\n" +
						"			and ((mrh.modified_date is null and mrh.created_date is null ) or (mrh.created_date is not null and mrh.modified_date is null and mrh.created_date<=re.modified_date )\n" +
						"			 or ( mrh.modified_date is not null and mrh.modified_date<=re.modified_date ))   and ( cp.modified_date is not null and cp.modified_date<=re.modified_date )  and  re_emp_select='1' "+qry ;
			}
			else {
			q = "select distinct re.id,b.personnel_no,b.name,r.description as appt, re.unit_sus_no as unit_sus_no,\n"
					+
					"			ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt, m.unit_name,\n" +
					"			 re.unit_posted_to,\n" +
					"			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n" +
					"			 re.census_id,re.comm_id,\n" +
					"			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt,ltrim(TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective,re.reject_remarks \n" +
					"			 from tb_psg_re_employment re \n" +
					"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n" +
					"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = b.unit_sus_no and orb.status_sus_no='Active'\r\n" +
					"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
					"			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' "
					+ " inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1  "
					+ "where re.id !=  0 and \n" +
					"		  re_emp_select='1'   "
					+ qry +
					" order by re.id desc \n" ;
			}
			stmt = conn.prepareStatement(q);
			int j =1;
			{
				if(status.equals("4")) {
					stmt.setString(j, roleSusNo);
					j++;
				}

				if (!roleSusNo.equals("") ) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!unit_name.equals("")) {
					stmt.setString(j, unit_name);
					j += 1;
				}
				if (!personnel_no1.equals("")) {
					stmt.setString(j, personnel_no1.toUpperCase() + "%");
					j += 1;
				}

			}


	 * if (roleSusNo != "") { stmt.setString(j, roleSusNo); j += 1; }
	 * if(!qry.equals("")) {
	 *
	 * if( !personnel_no1.equals("")) { stmt.setString(j,
	 * personnel_no1.toUpperCase()+"%"); j += 1; }
	 *
	 *
	 * if (!unit_sus_no.equals("")) { stmt.setString(j, unit_sus_no); j
	 * += 1; } if (!unit_name.equals("")) { stmt.setString(j,
	 * unit_name); j += 1; } }

			   System.err.println("stmtrecall-  ++++++++++-" +stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("personnel_no"));
				list.add(rs.getString("name"));
				list.add(rs.getString("appt"));
				list.add(rs.getString("date_of_appt"));
				list.add(rs.getString("unit_sus_no"));
				list.add(rs.getString("unit_name"));
				//list.add(rs.getString("unit_posted_to"));
				list.add(rs.getString("date_of_tos"));

				list.add(rs.getString("date_of_non_effective"));
				list.add(rs.getString("from_dt"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));


				String f = "";
				String f1 = "";
				String f2 = "";


				if(roleType.equals("APP") && status.equals("0"))
				{


					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){Approve("
							+ rs.getString("id") + ","+ rs.getString("comm_id") +")}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve'  " + Approve + " title='Edit Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){Reject("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";


				}


				else if(roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{


					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("
								+ rs.getString("id") + ","+ rs.getString("census_id") +","+ rs.getString("comm_id") +")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";



				}
				else if(roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

					+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					 f1="<i class='action_icons action_reject' "+rejectData+" title='Reject Data'></i></a>";

				}

				else if (roleType.equals("DEO") &&  status.equals("4"))
				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					 f="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";

				}
				list.add(f);//11
				list.add(f1);
				list.add(f2);
				list.add(rs.getString("reject_remarks"));
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
	}*/




	@Override
	public ArrayList<ArrayList<String>> search_re_call(String personnel_no1,
			String status, String unit_sus_no, String unit_name,
			String cr_by,String cr_date,String roleSusNo, String roleType,String roleAccess) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			/*
			 * if (!roleSusNo.equals("")) { qry += " and b.unit_sus_no = ?"; }
			 * if (!unit_sus_no.equals("")) { qry += "  and orb.sus_no = ?"; }
			 * if (!unit_name.equals("")) { qry += "  and orb.unit_name = ?"; }
			 * if(!personnel_no1.equals("")) { qry +=
			 * "  and upper(b.personnel_no) like ? "; } if(status.equals("0")) {
			 * qry += " and re.status = '0'  "; //and cl.update_ofr_status = '0'
			 * } if(status.equals("1")) { qry += " and re.status = '1' "; //and
			 * cl.update_ofr_status = '1' } if(status.equals("3")) { qry +=
			 * " and re.status = '3' "; // and cl.update_ofr_status = '2' }
			 */

			if (!roleSusNo.equals("")) {
				qry += " and re.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			/*if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}*/

			if (!personnel_no1.equals("")) {
				qry += "  and upper(b.personnel_no) like ? ";
			}


			if(status.equals("0")) {
				qry += " and re.status = '0'  "; // and cl.update_ofr_status =
				// '0'
			}
			else if (status.equals("1")) {
				qry += " and re.status = '1' ";
			}
			else if (status.equals("3")) {
				qry += " and re.status = '3' ";
			}
			else if (status.equals("4")) {
				qry += "and re.status not in (0,3)  ";
				if(roleType.equals("DEO")){
					qry += "and  re.cancel_status in (-1,2)  ";
				}
				else if(roleType.equals("APP")) {
					qry += "and  re.cancel_status=0  ";
				}
			}
			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}

			if (!cr_date.equals("")) {
				qry += " and cast(re.created_date as date) = cast(? as date)";
			}


			/*			q= "select re.id,b.personnel_no,b.name,r.description as appt, re.unit_sus_no as unit_sus_no,ltrim(TO_CHAR(re.date_of_appt,'DD-MON-YYYY'),'0') as date_of_appt, \r\n" +
					" re.unit_posted_to,\r\n" +
					" ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" +
					" re.census_id,re.comm_id,\r\n" +
					" ltrim(TO_CHAR(re.to_dt,'DD-MON-YYYY'),'0') as to_dt, ltrim(TO_CHAR(re.from_dt,'DD-MON-YYYY'),'0') as from_dt\r\n" +
					" from tb_psg_re_employment re \r\n" +
					" inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \r\n" +

					"inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  "+
					"left join tb_psg_change_of_appointment a on a.comm_id=b.id "+
					"left join cue_tb_psg_rank_app_master r on r.id =a.appointment"+
					" where re.id !=  0 and  re.unit_sus_no = ?   and  re_emp_select='1'  "+qry
					+ " order by re.id desc";*/


			if(status.equals("4")) {
				q = "select distinct re.id,re.status,re.modified_date,mrh.created_date as mrh_c,mrh.modified_date as mer_m, re.comm_id,b.personnel_no,b.name,re.unit_sus_no as unit_sus_no,\n" +
						"			 m.unit_name,\n" +
						"			 re.unit_posted_to,\n" +
						"	(select user_sus_no from logininformation where username=re.created_by ) unit_sus,"+
						"			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n" +
						"			 re.census_id,re.comm_id,\n" +
						"			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt,ltrim(TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective,re.reject_remarks \n" +
						"			 from tb_psg_re_employment re \n" +
						"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n" +
						"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
						"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
						"			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active'\n" +
						"			left join tb_psg_miso_role_hdr_status  mrh on mrh.comm_id=re.comm_id\n" +
						"	left join tb_psg_census_detail_p cp on cp.comm_id=re.comm_id "
						+ " inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1  "+
						" left join logininformation l1 on l1.username =re.created_by\r\n" +
						"			where re.id in (select id from tb_psg_re_employment  where comm_id=re.comm_id  order by id desc limit 1) 			 \n" +
						"			and ?=(select user_sus_no from logininformation where username=re.created_by )\n" +
						"			and ((mrh.modified_date is null and mrh.created_date is null ) or (mrh.created_date is not null and mrh.modified_date is null and mrh.created_date<=re.modified_date )\n" +
						"			 or ( mrh.modified_date is not null and mrh.modified_date<=re.modified_date ))   and ( cp.modified_date is not null and cp.modified_date<=re.modified_date )  and  re_emp_select='1' "+qry ;
			}
			else {
				q = "select distinct re.id,b.personnel_no,b.name, re.unit_sus_no as unit_sus_no,\n"
						+
						"			m.unit_name,\n" +
						"			 re.unit_posted_to,\n" +
						"			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n" +
						"			 re.census_id,re.comm_id,\n" +
						"			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt,ltrim(TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective,re.reject_remarks \n" +
						"			 from tb_psg_re_employment re \n" +
						"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n" +
						"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = b.unit_sus_no and orb.status_sus_no='Active'\r\n" +
						"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
						"			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' "
						+ " inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1  "
						+" left join logininformation l1 on l1.username =re.created_by\r\n"
						+ "where re.id !=  0 and \n" +
						"		  re_emp_select='1'   "
						+ qry +
						" order by re.id desc \n" ;
			}
			stmt = conn.prepareStatement(q);
			int j =1;
			{
				if(status.equals("4")) {
					stmt.setString(j, roleSusNo);
					j++;
				}

				if (!roleSusNo.equals("") ) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				/*if (!unit_name.equals("")) {
					stmt.setString(j, unit_name);
					j += 1;
				}*/
				if (!personnel_no1.equals("")) {
					stmt.setString(j, personnel_no1.toUpperCase() + "%");
					j += 1;
				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
					stmt.setString(j, cr_date);
					j += 1;

				}

			}

			/*
			 * if (roleSusNo != "") { stmt.setString(j, roleSusNo); j += 1; }
			 * if(!qry.equals("")) {
			 *
			 * if( !personnel_no1.equals("")) { stmt.setString(j,
			 * personnel_no1.toUpperCase()+"%"); j += 1; }
			 *
			 *
			 * if (!unit_sus_no.equals("")) { stmt.setString(j, unit_sus_no); j
			 * += 1; } if (!unit_name.equals("")) { stmt.setString(j,
			 * unit_name); j += 1; } }
			 */

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("personnel_no"));
				list.add(rs.getString("name"));
				/*
				 * list.add(rs.getString("appt")); list.add(rs.getString("date_of_appt"));
				 */
				list.add(rs.getString("unit_sus_no"));
				list.add(rs.getString("unit_name"));
				//list.add(rs.getString("unit_posted_to"));
				list.add(rs.getString("date_of_tos"));

				list.add(rs.getString("date_of_non_effective"));
				list.add(rs.getString("from_dt"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));


				String f = "";
				String f1 = "";
				String f2 = "";


				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{


					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){Approve("
							+ rs.getString("id") + ","+ rs.getString("comm_id") +")}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

					/*String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){Reject("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";*/

					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";


				}


				else if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{


					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("
							+ rs.getString("id") + ","+ rs.getString("census_id") +","+ rs.getString("comm_id") +")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";



				}
				else if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

					+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f1="<i class='action_icons action_reject' "+rejectData+" title='Reject Data'></i></a>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";

				}
				list.add(f);//11
				list.add(f1);
				list.add(f2);
				list.add(rs.getString("reject_remarks"));
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

	public List<Map<String, Object>> getPost_OutByid(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select p.id, b.personnel_no,\n" + "b.name,\n" + "r.description as rank,\n" + "p.out_auth,\n"
					+ "p.out_auth_dt,\n" + "p.to_sus_no as unit_sus_no,\n" + "m.unit_name,\n" + "p.dt_of_sos\n"
					+ "from tb_psg_posting_in_out p \n" + "inner join  tb_psg_census_detail_p c on p.census_id=c.id \n"
					+ "inner join tb_psg_trans_proposed_comm_letter b on c.comm_id=b.id \n"
					+ "inner join cue_tb_psg_rank_app_master r on r.code = cast(b.rank as character varying) and r.status_active = 'Active'\n"
					+ "inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active'  where p.id = ? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);

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

	//get personnel no..(validation)

	public String getpernoAlreadyExits(String census_id) {
		Connection conn = null;
		String msg = "false";
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select case when count(p.id) > 0 then true else false end \n" + "from tb_psg_posting_in_out p\n"
					+ "inner join tb_psg_census_detail_p c on p.census_id=c.id \n"
					+ "inner join tb_psg_trans_proposed_comm_letter b on c.comm_id=b.id \n"
					+ "where p.status = '0' and b.personnel_no= ?   ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, census_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				msg = rs.getString("case");
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
		return msg;
	}


	@Override
	public ArrayList<ArrayList<String>> gettenuredate(BigInteger comm_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{

			conn = dataSource.getConnection();
			PreparedStatement stmt=null;

			q="select op.id,to_char(op.tenure_date,'DD/MM/YYYY') as tenure_date,\n" +
					"(to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY')) + INTERVAL '1 MONTH' as date\n" +
					"from tb_psg_posting_in_out op where comm_id::text=? ORDER BY op.id desc limit 1" ;

			stmt=conn.prepareStatement(q);
			stmt.setString(1, comm_id.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("tenure_date"));
				list.add(rs.getString("date"));
				alist.add(list);

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
		return alist;

	}
}
