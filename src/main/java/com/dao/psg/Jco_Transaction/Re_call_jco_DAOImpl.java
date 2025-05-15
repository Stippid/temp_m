package com.dao.psg.Jco_Transaction;

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

public class Re_call_jco_DAOImpl implements Re_call_jco_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> search_re_callJCO(String army_no1,
			String status, String unit_sus_no, String unit_name,
			 String cr_by,String cr_date,
			String roleSusNo, String roleType,String roleAccess) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
 
			if (!roleSusNo.equals("")) {
				qry += " and re.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			/*if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}*/

			if (!army_no1.equals("")) {
				qry += "  and upper(b.army_no) like ? ";
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

			if(status.equals("4")) {
				/*
				 * q =
				 * "select distinct re.id,re.status,re.modified_date,mrh.created_date as mrh_c,mrh.modified_date as mer_m, re.comm_id,b.personnel_no,b.name,COALESCE(r.description,'') as appt, re.unit_sus_no as unit_sus_no,\n"
				 * +
				 * "			ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt, m.unit_name,\n"
				 * + "			 re.unit_posted_to,\n" +
				 * "	(select user_sus_no from logininformation where username=re.created_by ) unit_sus,"
				 * +
				 * "			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n"
				 * + "			 re.census_id,re.comm_id,\n" +
				 * "			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt\n"
				 * + "			 from tb_psg_re_employment re \n" +
				 * "			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n"
				 * +
				 * "			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n"
				 * +
				 * "			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n"
				 * +
				 * "			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active'\n"
				 * +
				 * "			left join tb_psg_miso_role_hdr_status  mrh on mrh.comm_id=re.comm_id\n"
				 * + "	left join tb_psg_census_detail_p cp on cp.comm_id=re.comm_id "+
				 * "			where re.id in (select id from tb_psg_re_employment  where comm_id=re.comm_id  order by id desc limit 1) 			 \n"
				 * +
				 * "			and ?=(select user_sus_no from logininformation where username=re.created_by )\n"
				 * +
				 * "			and ((mrh.modified_date is null and mrh.created_date is null ) or (mrh.created_date is not null and mrh.modified_date is null and mrh.created_date<=re.modified_date )\n"
				 * +
				 * "			 or ( mrh.modified_date is not null and mrh.modified_date<=re.modified_date ))   and ( cp.modified_date is not null and cp.modified_date<=re.modified_date )  and  re_emp_select='1' "
				 * +qry ;
				 */
				q = " select distinct re.id,re.status,re.reject_remarks,re.modified_date,mrh.created_date as mrh_c,mrh.modified_date as mer_m,\r\n" + 
						"	 re.jco_id,b.army_no,b.full_name,cu.rank as appt, re.unit_sus_no as unit_sus_no,\r\n" + 
						"	 m.unit_name,\r\n" + 
						"	 re.unit_posted_to,\r\n" + 
						"	(select user_sus_no from logininformation where username=re.created_by ) unit_sus,			\r\n" + 
						"	ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, \r\n" + 
						"	ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,re.jco_id,\r\n" + 
						"	ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt  from tb_psg_re_call_jco re " +
						" inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  " +
						" inner join tb_psg_census_jco_or_p b on re.jco_id=b.id  " +
						" inner join tb_psg_mstr_rank_jco cu on cu.id=b.rank  and cu.status = 'active'" +
						" left join tb_psg_change_of_appointment_jco a on a.jco_id=b.id " +
					    "left join tb_psg_miso_role_hdr_status_jco  mrh on mrh.jco_id=re.jco_id" +
					    "left join logininformation l1 on l1.username =re.created_by\r\n" + 
						" where ?=(select user_sus_no from logininformation where username=re.created_by )" + 
						" and ((mrh.modified_date is null and mrh.created_date is null ) \r\n" + 
						"	or (mrh.created_date is not null and mrh.modified_date is null and mrh.created_date<=re.modified_date )" + 
						"	or ( mrh.modified_date is not null and mrh.modified_date<=re.modified_date ))  " + qry;
				
				
			}
			else {
				/*q = "select distinct re.id,b.personnel_no,b.name,r.description as appt, re.unit_sus_no as unit_sus_no,\n"
					+
					"			ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt, m.unit_name,\n" +
					"			 re.unit_posted_to,\n" +
					"			 ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority, ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority,\n" +
					"			 re.census_id,re.comm_id,\n" +
					"			 ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt\n" +
					"			 from tb_psg_re_employment re \n" +
					"			 inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \n" +
					"			inner join tb_psg_trans_proposed_comm_letter b on re.comm_id=b.id  \n" +
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = b.unit_sus_no and orb.status_sus_no='Active'\r\n" +
					"			inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank  and cu.status_active = 'Active' left join tb_psg_change_of_appointment\n" +
					"			a on a.comm_id=b.id left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' where re.id !=  0 and \n" +
					"		  re_emp_select='1'   "
					+ qry +
					" order by re.id desc \n" ;*/
			
			q = " select distinct re.id,b.army_no,re.reject_remarks,b.full_name,cu.rank as appt, re.unit_sus_no as unit_sus_no,\r\n" + 
					/*" ltrim(TO_CHAR(a.date_of_appointment,'DD-MON-YYYY'),'0') as date_of_appt, \r\n" + */
					" m.unit_name, re.unit_posted_to,ltrim(TO_CHAR(re.date_of_tos,'DD-MON-YYYY'),'0') as date_of_tos,re.authority,\r\n" + 
					" ltrim(TO_CHAR(re.auth_dt,'DD-MON-YYYY'),'0') as date_of_authority, re.jco_id,ltrim(TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY'),'0') as from_dt\r\n" + 
					" from tb_psg_re_call_jco re " +
					" inner join tb_miso_orbat_unt_dtl m on m.sus_no=re.unit_sus_no and m.status_sus_no='Active'  \r\n" + 
					" inner join tb_psg_census_jco_or_p b on re.jco_id=b.id  \r\n" + 
					" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = b.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					" inner join tb_psg_mstr_rank_jco cu on cu.id=b.rank  and cu.status = 'active'\r\n" + 
					" left join tb_psg_change_of_appointment_jco a on a.jco_id=b.id "+
					" left join logininformation l1 on l1.username =re.created_by\r\n" 
					+ " where re.id !=  0 "
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
				if (!army_no1.equals("")) {
					stmt.setString(j, army_no1.toUpperCase() + "%");
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
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("army_no"));//1
				list.add(rs.getString("full_name"));//2
				list.add(rs.getString("appt"));//3
			//	list.add(rs.getString("date_of_appt"));//4
				list.add(rs.getString("unit_sus_no"));//5
				list.add(rs.getString("unit_name"));//6
				//list.add(rs.getString("unit_posted_to"));
				list.add(rs.getString("date_of_tos"));//7
				list.add(rs.getString("authority"));//8
				list.add(rs.getString("date_of_authority"));//9
				list.add(rs.getString("from_dt"));//10
				list.add(rs.getString("reject_remarks"));//11
				
//				list.add(rs.getString("to_dt"));

				String f = "";
				String f1 = "";
				String f2 = "";


				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{


					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){Approve("
							+ rs.getString("id") + ","+ rs.getString("jco_id") +")}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve'  " + Approve + " title='Edit Data'></i>";

			
					
					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";					
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";


				}


				else if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{


					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("
								+ rs.getString("id") + ",'"+ rs.getString("jco_id") +"')}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";



				}
				else if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' )}else{ return false;}\"";



					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

					+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";

					 f1="<i class='action_icons action_reject' "+rejectData+" title='Reject Data'></i></a>";

					

				

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))



				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";

							

					 f="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";
	}


				list.add(f);
				list.add(f1);
				list.add(f2);
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

	
}
