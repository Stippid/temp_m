package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.persistance.util.HibernateUtil;

public class ExtentionDAOImpl implements ExtentionDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_extention(String unit_name,String unit_sus_no,String personnel_no1 ,String status, String cr_by,String cr_date,
		    String roleSusNo,String roleType) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String qs="";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(!roleSusNo.equals("")) {
				qry+=" and b.unit_sus_no = ?";
			}
			if(!unit_sus_no.equals("")) {
				qry += "  and m.sus_no = ?";
			}
			if(!unit_name.equals("")) {
				qry += "  and m.unit_name = ?";
			}
			if(!personnel_no1.equals("")) {
				qry += " and  b.personnel_no = ? ";
			}
			if(status.equals("0")) {
				qry += " and ex.status = '0'  ";   
			}
			if(status.equals("1")) {
				qry += " and ex.status = '1' ";     
			}
			if(status.equals("3")) {
				qry += " and ex.status = '3' ";			 
			}
			 if (status.equals("4")) {
				qry += "and ex.status not in (0,3)  ";
				qs+="and status not in (0,3)  ";
				if(roleType.equals("DEO")){
					qry += "and  ex.cancel_status in (-1,2)  ";
					qs+="and cancel_status in (-1,2)  ";
				}
				 if(roleType.equals("APP")) {
					qry += "and  ex.cancel_status=0  ";
					qs+="and  cancel_status=0  ";
				}
			}
			 if (!cr_by.equals("")) {

					qry += "  and cast(l1.userid as character varying)= ? ";

				}

		
		
		if(!cr_date.equals("")) {
					qry +=" and cast(ex.created_date as date) = cast(? as date)";
				}
		
			
			if(status.equals("4")) {
						
				q="select ex.id,re.id as remp_id,b.personnel_no,b.name,re.unit_sus_no as unit_sus_no,\r\n" + 
						"m.unit_name, \r\n" + 
						"re.unit_posted_to,\r\n" + 
						"TO_CHAR(re.date_of_tos,'DD-MON-YYYY') as date_of_tos,ex.auth_no as authority, TO_CHAR(ex.auth_dt,'DD-MON-YYYY') as date_of_authority,\r\n" + 
						"ex.census_id,ex.comm_id,\r\n" + 
						"TO_CHAR(ex.to_dt,'DD-MON-YYYY') as to_dt, TO_CHAR(ex.from_dt,'DD-MON-YYYY') as from_dt,\r\n" + 
						"TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY') as granted_fr_dt,\r\n" + 
						"TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY') as date_of_non_effective,ex.reject_remarks " +
						"from tb_psg_re_employment re \r\n" + 
						"inner join tb_psg_re_emp_extention ex on ex.re_emp_id=re.id \r\n" + 
						"inner join tb_psg_trans_proposed_comm_letter b on ex.comm_id=b.id  \r\n" + 
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no=b.unit_sus_no and m.status_sus_no='Active' \r\n" + 
						"inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank and cu.status_active = 'Active'"
						+ " left join logininformation l1 on l1.username =ex.created_by \r\n" + 
						"left join tb_psg_change_of_appointment\r\n" + 
						"a on a.comm_id=b.id and a.status='1' \r\n" + 
						"left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' " +
						" inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1  \r\n" + 
						"where ex.id in (select id from tb_psg_re_emp_extention  where comm_id=re.comm_id "+qs+ "   order by id desc limit 1) 			 \r\n" + 
						"and\r\n" + 
						"re_emp_select='2'   "+qry +
						" order by ex.id desc \n";
					}
					else {
						  		
				  		q= "select ex.id,b.personnel_no,b.name,re.unit_sus_no as unit_sus_no,\r\n" + 
				  				"m.unit_name, \r\n" + 
				  				" re.unit_posted_to,\r\n" + 
				  				"TO_CHAR(re.date_of_tos,'DD-MON-YYYY') as date_of_tos,ex.auth_no as authority, TO_CHAR(ex.auth_dt,'DD-MON-YYYY') as date_of_authority,\r\n" + 
				  				"ex.census_id,ex.comm_id,\r\n" + 
				  				"TO_CHAR(ex.to_dt,'DD-MON-YYYY') as to_dt, TO_CHAR(ex.from_dt,'DD-MON-YYYY') as from_dt\r\n" + 
				  				",TO_CHAR(re.granted_fr_dt,'DD-MON-YYYY') as granted_fr_dt,\r\n" + 
				  				"TO_CHAR(nf.date_of_non_effective,'DD-MON-YYYY') as date_of_non_effective,ex.reject_remarks "+ 
				  				"from tb_psg_re_employment re \r\n" + 				  				
				  				"inner join tb_psg_re_emp_extention ex on ex.re_emp_id=re.id \r\n" + 
				  				"inner join tb_psg_trans_proposed_comm_letter b on ex.comm_id=b.id  \r\n" + 
				  				"inner join tb_miso_orbat_unt_dtl m on m.sus_no=b.unit_sus_no and m.status_sus_no='Active'  \r\n" + 
				  				"inner join cue_tb_psg_rank_app_master cu on cu.id=b.rank and cu.status_active = 'Active'"
				  				+ " left join logininformation l1 on l1.username =ex.created_by \r\n" + 
				  				"left join tb_psg_change_of_appointment\r\n" + 
				  				"a on a.comm_id=b.id and a.status='1' left join cue_tb_psg_rank_app_master r on r.id =a.appointment and r.status_active = 'Active' "+
				  				"inner join tb_psg_non_effective  nf on nf.comm_id = re.comm_id  and nf.status = 1 " +
				  				"where ex.id !=  0 and  \r\n" + 
				  				"re_emp_select='2' "+qry +
								" order by re.id desc \n" ;
			}	
			stmt = conn.prepareStatement(q);			
			int j =1;
					
			if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}			
			if(!unit_sus_no.equals("")) {
				stmt.setString(j, unit_sus_no);
				j += 1;	
			} 
		 	if(!unit_name.equals("")) {
				stmt.setString(j, unit_name);
				j += 1;	
			} 		  				
			if( !personnel_no1.equals("")) {
				stmt.setString(j, personnel_no1);
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
	
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("personnel_no"));
				list.add(rs.getString("name"));								
				list.add(rs.getString("unit_sus_no"));				
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("date_of_tos"));
				list.add(rs.getString("date_of_non_effective"));
				list.add(rs.getString("granted_fr_dt"));
				list.add(rs.getString("from_dt"));
				list.add(rs.getString("to_dt"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				String f = "";
				String f1 = "";
				String f2 = "";
				
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{				
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("+rs.getString("id")+"  )}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";	
					
					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";					
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
				
				}
				
				else if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("
							+ rs.getString("id") + ","+ rs.getString("census_id") +","+ rs.getString("comm_id") +")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				
				}
				else if(roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
				{
					String View1 = "onclick=\"  {ViewCancelHistoryData('"+rs.getString("remp_id")+"','"+rs.getString("comm_id")+"','-1')}\"";
			        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				else if(roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("4"))
				{
					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve Officer Extension Data?') ){ViewCancelHistoryData('"+rs.getString("remp_id")+"','"+rs.getString("comm_id")+"','0')}else{ return false;}\"";
				    f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
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
	//
	public List<Map<String, Object>>  getExtn_data(int census_id,BigInteger comm_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct  \r\n" + 
					"r.id as remp_id, CASE WHEN ex.id is null THEN 0 else ex.id END as ex_id ,r.granted_fr_dt,r.date_of_tos,r.unit_sus_no,ex.from_dt,ex.to_dt,ex.auth_no,ex.auth_dt, r.comm_id,m.unit_name \r\n" + 
					"from tb_psg_re_employment r left join tb_psg_re_emp_extention ex on r.id =ex.re_emp_id  "
					+ " inner join tb_miso_orbat_unt_dtl m on m.sus_no=r.unit_sus_no  and m.status_sus_no='Active' where  r.status='1'  and --ex.status='0' and \r\n" + 
					" r.census_id = ? and r.comm_id::text = ? order by ex_id desc limit 1";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, census_id);
			stmt.setString(2, comm_id.toString());
		
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
	public String approveHisExtension(int remp_id, BigInteger comm_id, List<Map<String, Object>> Lobj,
			String username, Date mod_date) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		try {
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				
				String hql_n = "update TB_REEMP_EXTENTION set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
			}

			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
		
	}

	@Override
	public List<Map<String, Object>> getHisOfExtension(int remp_id, BigInteger comm_id, int status, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String accesslvl = session.getAttribute("roleAccess").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;		
			//String flag = "Y";
		
			if(status==-1)
				q="ex.cancel_status in (-1,2)";
			else if(status==0) {
				q="ex.cancel_status=0";
				if(!accesslvl.equals("MISO")) {
					q+="and (select user_sus_no from logininformation where username=ex.created_by )!='' ";
				}
			}
			q = "select ex.id,ex.comm_id,\r\n" + 
					"case \r\n" + 
					"	when  ex.created_date>(select case when modified_date is null then created_date else modified_date end  from tb_psg_posting_in_out where comm_id=27 and status=1 order by id desc limit 1) then 1\r\n" + 
					"	else 0\r\n" + 
					"	end as available,\r\n" + 
					"ex.re_emp_id,ex.auth_no as authority,b.unit_sus_no, (select user_sus_no from logininformation where username=ex.created_by) as c_sus,TO_CHAR(ex.auth_dt,'DD-MON-YYYY') as date_of_authority,\r\n" + 
					"TO_CHAR(ex.to_dt,'DD-MON-YYYY') as to_dt, TO_CHAR(ex.from_dt,'DD-MON-YYYY') as from_dt,\r\n" + 
					"ex.modified_by,TO_CHAR(ex.modified_date,'DD-MON-YYYY') as modified_date\r\n" + 
					"from tb_psg_re_emp_extention ex\r\n" + 
					"inner join tb_psg_trans_proposed_comm_letter b on ex.comm_id=b.id\r\n" + 
					"where ex.status not in (0,3) and ex.comm_id::text=? and ex.re_emp_id=? and "+q+" order by id desc";

			stmt = conn.prepareStatement(q);
			stmt.setString(1,comm_id.toString());
			stmt.setInt(2,remp_id);
		
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisExtensionData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisExtensionData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String rejectbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'Reject'  "+rejectData+"'><i class='fa fa-times'></i></a>";
				if(status==-1 ) {
					if((rs.getString("c_sus")==null || rs.getString("c_sus").equals("")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else if(rs.getString("available").equals("1"))
						columns.put("action", cancelbtn);
					else
						columns.put("action", "");
				}
				if(status==0) {
					columns.put("action", rejectbtn);
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

	




}
