package com.dao.psg.cancellation;

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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class ViewHistory_updateOffrDataDaoImpl implements ViewHistory_updateOffrDataDao{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getHisChangeOfRank(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
	
		String accesslvl = session.getAttribute("roleAccess").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;		
			//String flag = "Y";
		
			if(status==-1)
				q="cr.cancel_status in (-1,2)";
			else if(status==0)
				q="cr.cancel_status=0";
			 
		q = "select cr.id,cr.authority ,cr.date_of_authority ,cr.date_of_rank,td.label as rank_type,cue.description as rank, cr.modified_by, cr.modified_date,cr.census_id,cr.status from tb_psg_change_of_rank cr\n" + 
				"left join t_domain_value td on td.codevalue=cast(cr.rank_type  as char) and td.domainid='OFFR_RANK_TYPE'\n" + 
				"inner join cue_tb_psg_rank_app_master cue on cue.id=cr.rank \n" + 
				"where cast(cr.comm_id  as character varying)=? and cr.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeOfRankData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeOfRankData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisChangeOfName(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="cancel_status in (-1,2)";
			else if(status==0)
				q="cancel_status=0";
			 
		q = "select id,authority,date_of_authority,name, modified_by, modified_date,census_id,status  from  tb_psg_change_name \n" + 
				"where cast(comm_id  as character varying)=? and status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i),"");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeOfNameData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeOfNameData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisChangeOfAppointment(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="ap.cancel_status in (-1,2)";
			else if(status==0)
				q="ap.cancel_status=0";
			 
		q = "select ap.id,ap.appt_authority,ap.appt_date_of_authority,ap.date_of_appointment,ap.appointment, ap.modified_by, ap.modified_date,\n" + 
				"cue.description,ap.census_id,ap.status from tb_psg_change_of_appointment ap\n" + 
				"inner join  cue_tb_psg_rank_app_master cue on cue.id=ap.appointment and upper(cue.level_in_hierarchy) = 'APPOINTMENT' and parent_code ='0' \n" + 
				"where cast(ap.comm_id  as character varying)=? and ap.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeInAppointment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeInAppointment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisIdentity_Card(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="ic.cancel_status in (-1,2)";
			else if(status==0)
				q="ic.cancel_status=0";
			 
		q = "select ic.id,ic.id_card_no,ic.Issue_dt,ic.id_marks,ic.hair_other,ic.eye_other,hc.hair_cl_name,\n" + 
				"ec.eye_cl_name,ic.identity_image,ic.image_updated_date,ic.Issue_authority,ic.modified_by,ic.modified_date\n" + 
				",ic.census_id,ic.status from tb_psg_identity_card ic\n" + 
				"inner join tb_psg_mstr_hair_colour hc on hc.id=ic.hair_colour\n" + 
				"inner join tb_psg_mstr_eye_colour ec on ec.id=ic.eye_colour\n" + 
				"where cast(ic.comm_id  as character varying)=? and ic.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeIdentityCard("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeIdentityCard("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisChangeOfReligion(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="cr.cancel_status in (-1,2)";
			else if(status==0)
				q="cr.cancel_status=0";
			 
		q = "select cr.id,cr.authority,cr.date_of_authority,cr.modified_by,cr.modified_date,mr.religion_name,\n" + 
				"cr.other,cr.census_id,cr.status from tb_psg_change_religion cr\n" + 
				"inner join tb_psg_mstr_religion mr on mr.religion_id=cr.religion\n" + 
				"where cast(cr.comm_id  as character varying)=? and cr.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeInReligion("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeInReligion("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisfamily_married(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="cfm.cancel_status in (-1,2)";
			else if(status==0)
				q="cfm.cancel_status=0";
			
			 
		q =" SELECT \n" + 
				"	  distinct cfm.id, \n" + 
				"	  cfm.cen_id, cfm.status, \n" + 
				"	  cfm.created_by, \n" + 
				"	  ltrim(TO_CHAR(cfm.created_date ,'DD-MON-YYYY'),'0') as created_date,\n" + 
				"	  ltrim(TO_CHAR(cfm.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\n" + 
				"	  cfm.maiden_name, \n" + 
				"	  ltrim(TO_CHAR(cfm.marriage_date ,'DD-MON-YYYY'),'0') as marriage_date, \n" + 
				"	  cfm.modified_by, \n" + 
				"	  ltrim(TO_CHAR(cfm.modified_date,'DD-MON-YYYY'),'0') as modified_date,  \n" + 
				"	  ltrim(TO_CHAR(  cfm.divorce_date,'DD-MON-YYYY'),'0') as divorce_date,\n" + 
				"	  ltrim(TO_CHAR(  cfm.separated_form_dt,'DD-MON-YYYY'),'0') as separated_form_dt,\n" + 
				"	  ltrim(TO_CHAR(  cfm.separated_to_dt,'DD-MON-YYYY'),'0') as separated_to_dt,\n" + 
				"	  \n" + 
				"	  cfm.authority, \n" + 
				"	  ltrim(TO_CHAR(  cfm.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 
				"	  cfm.comm_id,\n" + 
				"	  ms.marital_name, cfm.marital_status,	  \n" + 
				"	  cfm.place_of_birth, 	\n" + 
				"	  PGP_SYM_DECRYPT(cfm.pan_card ::bytea,current_setting('miso.version'))  as   pan_card,\n" + 
				"	  PGP_SYM_DECRYPT(cfm.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,	  \n" + 
				"	   case when upper(n.nationality_name)='OTHERS' or  upper(n.nationality_name)='OTHER' then cfm.other_nationality      else  n.nationality_name end as nationality_name, \n" + 
				"	   case when upper(ev.label)='OTHERS' or  upper(ev.label)='OTHER' then cfm.other_spouse_ser      else  ev.label end as spouse_service, \n" + 
				"	  cfm.spouse_personal_no \n" + 
				"	FROM tb_psg_census_family_married cfm\n" + 
				"	LEFT JOIN tb_psg_mstr_nationality n ON cfm.nationality = n.nationality_id\n" + 
				"	LEFT JOIN tb_psg_mstr_marital_status ms ON cfm.marital_status = ms.marital_id\n" + 
				" LEFT JOIN t_domain_value ev ON cfm.spouse_service::text = ev.codevalue and ev.domainid='EX_SERVICEMAN' \n" + 
				"	WHERE cast(cfm.comm_id  as character varying)=? and cfm.status in (1,2) "
				+" and "+q+" order by id";
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisMarriage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisMarriage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisSpouseQualification(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="qua.cancel_status in (-1,2)";
			else if(status==0)
				q="qua.cancel_status=0";
			 
		q = "select distinct qua.id,fm.maiden_name,dom.label as type,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year,qua.modified_by,qua.modified_date, \n" + 
				"dc.div,qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification where id=qua.id), ','))   qsub\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.cen_id,qua.status from tb_psg_census_spouse_qualification qua\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE' "
				+ " left join tb_psg_census_family_married fm on qua.spouse_id=fm.id " + 
				"where cast(qua.comm_id  as character varying)=? and qua.status in (2,1) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisSpouseQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisSpouseQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisPendingSpouseQualification(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="qua.cancel_status in (-1,2)";
			else if(status==0)
				q="qua.cancel_status=0";
			 
		q = "select distinct qua.id,dom.label as type,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year,qua.modified_by,qua.modified_date, \n" + 
				"dc.div,qua.institute, array_to_string(ARRAY(select sub.description from (select unnest(string_to_array(subject, ','))  as sub_id from tb_psg_census_spouse_qualification ) qsub\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub.sub_id as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.cen_id,qua.status from tb_psg_census_spouse_qualification qua\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE'" + 
				"where \n" + 
				"cast(qua.comm_id  as character varying)=? and qua.status in (2,1) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisSpouseQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisSpouseQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	
	public List<Map<String, Object>> getHisChild(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="ch.cancel_status in (-1,2)";
			else if(status==0)
				q="ch.cancel_status=0";
			
			 
		q = "select ch.id,ch.type,ch.name,PGP_SYM_DECRYPT(ch.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,ch.adoted,ch.date_of_adpoted,ch.date_of_birth,ch.modified_by,\n" + 
				"ch.modified_date,PGP_SYM_DECRYPT(ch.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td.label as relationship,ch.cen_id,ch.status from tb_psg_census_children ch\n" + 
				"inner join  t_domain_value td on td.codevalue=cast(ch.relationship as char) and td.domainid='CHILD_RELATIONSHIP'\n" + 
				"where cast(ch.comm_id  as character varying)=? and ch.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChildDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChildDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisNokAddress(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="no.cancel_status in (-1,2)";
			else if(status==0)
				q="no.cancel_status=0";
			
			 
		q = "select  no.id,no.nok_name,no.authority,no.date_authority ,re.relation_name,PGP_SYM_DECRYPT(no.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,no.nok_village,\n" + 
				"no.nok_pin,no.nok_near_railway_station,no.nok_rural_urban_semi,no.modified_date,no.modified_by,\n" + 
				"no.ctry_other,no.st_other,no.dist_other,no.tehsil_other,no.relation_other,co.name as country,\n" + 
				"st.state_name,dis.district_name,te.city_name,no.census_id,no.status from tb_psg_census_nok no\n" + 
				"inner join tb_psg_mstr_relation re on re.id=no.nok_relation\n" + 
				"inner join tb_psg_mstr_country co on co.id=no.nok_country\n" + 
				"inner join tb_psg_mstr_state st on st.state_id=no.nok_state\n" + 
				"inner join tb_psg_mstr_district dis on dis.district_id=no.nok_district\n" + 
				"inner join tb_psg_mstr_city te on te.city_id=cast(no.nok_tehsil as integer)\n" + 
				"where cast(no.comm_id  as character varying)=? and no.status in  (1,2)\n" + 
				"and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisNokDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisNokDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisAddress(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="addr.cancel_status in (-1,2)";
			else if(status==0)
				q="addr.cancel_status=0";
			
			 
		q = "\n" + 
				"select  addr.id,addr.authority,addr.date_authority,addr.modified_date,addr.modified_by,\n" + 
				"co.name as pre_country,st.state_name as pre_state,dis.district_name as pre_district,te.city_name as pre_tehsil\n" + 
				",addr.pre_country_other,addr.pre_domicile_state_other,addr.pre_domicile_district_other,addr.pre_domicile_tesil_other \n" + 
				",coh.name as home_country,sth.state_name as home_state,dish.district_name as home_district,teh.city_name as home_tehsil\n" + 
				",addr.per_home_addr_country_other,addr.per_home_addr_state_other,addr.per_home_addr_district_other,addr.per_home_addr_tehsil_other \n" + 
				",addr.permanent_rural_urban_semi,addr.permanent_border_area,addr.permanent_village,addr.permanent_pin_code,addr.permanent_near_railway_station\n" + 
				",cop.name as present_country,stp.state_name as present_state,disp.district_name as present_district,tep.city_name as present_tehsil\n" + 
				",addr.pers_addr_country_other,addr.pers_addr_state_other,addr.pers_addr_district_other,addr.pers_addr_tehsil_other \n" + 
				",addr.present_rural_urban_semi,addr.present_village,addr.present_pin_code,addr.present_near_railway_station\n" + 
				",cos.name as spouse_country,sts.state_name as spouse_state,diss.district_name as spouse_district,tep.city_name as spouse_tehsil\n" + 
				",addr.spouse_addr_tehsil_other,addr.spouse_addr_state_other,addr.spouse_addr_district_other,addr.spouse_addr_tehsil_other \n" + 
				",addr.spouse_village,addr.spouse_pin_code,addr.spouse_near_railway_station,addr.cen_id,addr.status\n" + 
				"\n" + 
				",addr.spouse_rural_urban_semi,addr.stn_hq_sus_no  from tb_psg_census_address addr\n" + 
				"inner join tb_psg_mstr_country co on co.id=addr.pre_country\n" + 
				"inner join tb_psg_mstr_state st on st.state_id=addr.pre_state\n" + 
				"inner join tb_psg_mstr_district dis on dis.district_id=addr.pre_district\n" + 
				"inner join tb_psg_mstr_city te on te.city_id=addr.pre_tesil\n" + 
				"inner join tb_psg_mstr_country coh on coh.id=addr.permanent_country\n" + 
				"inner join tb_psg_mstr_state sth on sth.state_id=addr.permanent_state\n" + 
				"inner join tb_psg_mstr_district dish on dish.district_id=addr.permanent_district\n" + 
				"inner join tb_psg_mstr_city teh on teh.city_id=cast(addr.permanent_tehsil as integer)\n" + 
				"inner join tb_psg_mstr_country cop on cop.id=addr.present_country\n" + 
				"inner join tb_psg_mstr_state stp on stp.state_id=addr.present_state\n" + 
				"inner join tb_psg_mstr_district disp on disp.district_id=addr.present_district\n" + 
				"inner join tb_psg_mstr_city tep on tep.city_id=cast(addr.permanent_tehsil as integer)\n" + 
				"left join tb_psg_mstr_country cos on cos.id=addr.spouse_country\n" + 
				"left join tb_psg_mstr_state sts on sts.state_id=addr.spouse_state\n" + 
				"left join tb_psg_mstr_district diss on diss.district_id=addr.spouse_district\n" + 
				"left join tb_psg_mstr_city tes on tes.city_id=cast(addr.spouse_tehsil as integer)\n" + 
				"where cast(addr.comm_id  as character varying)=? and addr.status in  (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisAddressDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisAddressDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisContactDetails(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="cancel_status in (-1,2)";
			else if(status==0)
				q="cancel_status=0";
			 
		q = "select id,PGP_SYM_DECRYPT(gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,modified_by,modified_date,census_id,status from tb_psg_census_contact_cda_account_details\n" + 
				"where cast(comm_id  as character varying)=? and status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisContactDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisContactDetails("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisLanguageDetails(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="la.cancel_status in (-1,2)";
			else if(status==0)
				q="la.cancel_status=0";
			
			 
		q = "select la.id, la.authority, la.date_of_authority, lp.ind_language,ls.name as lang_std,la.other_language,la.other_lang_std,la.modify_by,la.modify_on,la.cen_id,la.status from tb_psg_census_language la\n" + 
				"inner join tb_psg_lang_std ls on ls.id=la.lang_std\n" + 
				"inner join tb_psg_mstr_indian_language lp on lp.id=la.language\n" + 
				"where  la.language!=0 and  cast(la.comm_id  as character varying)=? and la.status in (1,2)\n" + 
				"and "+q;
			stmt = conn.prepareStatement(q);
            stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisLanguage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisLanguage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisForeignLang(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="la.cancel_status in (-1,2)";
			else if(status==0)
				q="la.cancel_status=0";
			
			 
		q = "select la.id, la.authority, la.date_of_authority, la.f_exam_pass,lp.foreign_language_name,ls.name as lang_std,lo.name as lang_proff,la.f_other_language,la.f_other_lang_std,la.f_other_prof,la.modify_by,la.modify_on,la.cen_id,la.status from tb_psg_census_language la\n" + 
				"				inner join tb_psg_lang_std ls on ls.id=la.lang_std \n" + 
				"				inner join tb_psg_lang_proof lo on lo.id=la.f_lang_prof\n" + 
				"				inner join tb_psg_mstr_foreign_language lp on lp.id=la.foreign_language\n" + 
				"				where la.language =0 and cast(la.comm_id  as character varying)=? and la.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisLanguage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisLanguage("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisQualification(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="qua.cancel_status in (-1,2)";
			else if(status==0)
				q="qua.cancel_status=0";
			 
			q = "select qua.id,qua.authority, qua.date_of_authority,dom.label as type,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year, \n" + 
				"dc.div,qua.institute, array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_qualification where id=qua.id), ','))   qsub\n" + 
				"				inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,\n" + 
				"				qua.modify_by,qua.modify_on	,qua.cen_id,qua.status															  \n" + 
				"				from tb_psg_census_qualification qua\n" + 
				"				inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass \n" + 
				"				inner join tb_psg_mstr_degree deg on deg.id=qua.degree \n" + 
				"				inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"				inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"				inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE'\n" + 
				"				where cast(qua.comm_id  as character varying)=? and qua.status in (2,1) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisQualification("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisPromotionalExam(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="pe.cancel_status in (-1,2)";
			else if(status==0)
				q="pe.cancel_status=0";
			
			 
		q = "select pe.id,pe.modify_on ,pe.modify_by,pe.authority,pe.date_of_authority,pe.exam_other,td.promo_exam as exam,pe.date_of_passing,pe.census_id,pe.status from tb_psg_census_promo_exam pe\n" + 
				"inner join tb_psg_mstr_promotional_exam td on td.id::text=pe.exam \n" + 
				"where cast(pe.comm_id  as character varying)=? and pe.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisPromotionalExam("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisPromotionalExam("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisArmyCourse(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			
			if(status==-1)
				q="ac.cancel_status in (-1,2)";
			else if(status==0)
				q="ac.cancel_status=0";
			
		q = "select ac.id,td.label as course_type,ac.course_abbreviation,dg.div,ac.course_name,\n" + 
				"case when ins.course_institute='OTHERS' then ac.course_institute_other\n"
			    +"else ins.course_institute end as institute,"
				+"ac.ar_course_div_ot,ac.course_type_ot,ac.start_date,ac.date_of_completion,\n" + 
				"ac.authority,ac.date_of_authority,ac.modify_on,ac.modify_by,ac.census_id,ac.status \n" + 
				"from tb_psg_census_army_course ac\n" + 
				"inner join tb_psg_mstr_div_grade dg on dg.id=cast(ac.div_grade as integer)\n" + 
				"inner join t_domain_value td on td.codevalue=ac.course_type  and td.domainid='COURSE_TYPE'\n" + 
				"inner join tb_psg_mstr_course_institute ins on ins.id=ac.course_institute and ins.status='active' "
				+"where cast(ac.comm_id  as character varying)=? and ac.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisArmyCourse("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisArmyCourse("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String rejectbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'Reject'  "+rejectData+"'><i class='fa fa-times'></i></a>";
				
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
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
	
	public List<Map<String, Object>> getHisBpetDetails(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="bt.cancel_status in (-1,2)";
			else if(status==0)
				q="bt.cancel_status=0";
			
			 
		q = "select bt.id,bt.modified_date,bt.modified_by,bt.bept_result_others,td.bpet_qt as bpet_qe,tdb.bpet_result as bpet_result,bt.year,bt.unit_sus_no,bt.census_id,bt.status from tb_psg_census_bpet bt \n" + 
				"				inner join  tb_psg_mstr_bpet_qt td on td.id::text=bt.bpet_qe \n" + 
				"				inner join tb_psg_mstr_bpet_result tdb on tdb.id::text=bt.bpet_result " + 
				"where cast(bt.comm_id  as character varying)=? and bt.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisBPET("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisBPET("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	public List<Map<String, Object>> getHisFiringDetails(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="bt.cancel_status in (-1,2)";
			else if(status==0)
				q="bt.cancel_status=0";
			 
		q = "select bt.id,bt.modified_date,bt.modified_by,bt.ot_firing_grade,td.firing_qt as firing_event_qe,tdb.firing_result as firing_grade,bt.year,bt.firing_unit_sus_no,bt.census_id,bt.status from tb_psg_census_firing_standard bt\n" + 
				"				inner join tb_psg_mstr_firing_qt td on td.id::text=bt.firing_event_qe \n" + 
				"				inner join tb_psg_mstr_firing_result tdb on tdb.id::text=bt.firing_grade \n" + 
				"where cast(bt.comm_id  as character varying)=? and bt.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisFiringStandard("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisFiringStandard("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisAllergy(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="cancel_status in (-1,2)";
			else if(status==0)
				q="cancel_status=0";
			
			 
		q = "select id,medicine,modified_date,modified_by,cen_id,status from tb_psg_allergic_to_medicine\n" + 
				"where cast(comm_id  as character varying)=? and status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
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

				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisAllergy("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisAllergy("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisMedicalCategory(BigInteger comm_id ,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="med.cancel_status in (-1,2)";
			else if(status==0)
				q="med.cancel_status=0";
			 
		q = "select \n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='S') as s_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='S') as s_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='H') as h_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='H') as h_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='A') as a_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='A') as a_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='P') as p_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='P') as p_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='E') as e_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='E') as e_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='C_C') as c_c_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_C') as c_c_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='C_O') as c_o_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_O') as c_o_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='C_P') as c_p_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_P') as c_p_value,\n" + 
				"STRING_AGG(med.id::text,'I') FILTER (where med.shape='C_E') as c_e_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_E') as c_e_value,\n" + 
				"med.modify_on,med.modify_by,med.authority,med.date_of_authority\n" + 
				"from tb_psg_medical_category med \n" + 
				"where cast(med.comm_id  as character varying)=? and med.status in (1,2) and "+q+" \n" + 
				"group by med.modify_on,med.modify_by,med.authority,med.date_of_authority";
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChangeOfRankData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChangeOfRankData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1) {
					columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	
	public List<Map<String, Object>> getHisForeignCountry(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="pfc.cancel_status in (-1,2)";
			else if(status==0)
				q="pfc.cancel_status=0";
			 //26-01-1994
//		q = "select  pfc.id,td.visit_purpose as purpose_visit,fc.name as country,pfc.period,pfc.from_dt,pfc.to_dt,pfc.other_country,pfc.other_purpose_visit,pfc.modified_date,pfc.modified_by,pfc.cen_id,pfc.status \n" + 
//				"from tb_psg_census_foreign_country pfc\n" + 
//				"inner join tb_psg_mstr_purposeof_visit td on td.id = pfc.purpose_visit\n" + 
//				"inner join tb_psg_foreign_country fc on fc.id=pfc.country\n" + 
//				"where cast(pfc.comm_id  as character varying)=? and pfc.status in (1,2) and "+q;
			
			q = "select  pfc.id,td.visit_purpose as purpose_visit,\n"
					+ "case when fc.name='Other' then pfc.other_country\n"
					+ "else fc.name end as country,\n"
					+ "pfc.period,pfc.from_dt,pfc.to_dt,pfc.other_country,\n"
					+ "case when td.visit_purpose='OTHER' then pfc.other_purpose_visit\n"
					+ "else  td.visit_purpose end as purpose_visit,\n"
					+ "pfc.modified_date,pfc.modified_by,pfc.cen_id,pfc.status \n"
					+ "from tb_psg_census_foreign_country pfc\n"
					+ "inner join tb_psg_mstr_purposeof_visit td on td.id = pfc.purpose_visit\n"
					+ "inner join tb_psg_foreign_country fc on fc.id=pfc.country " + 
					"where cast(pfc.comm_id  as character varying)=? and pfc.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);

           stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisForeignCountry("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisForeignCountry("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("cen_id")==null || rs.getString("cen_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisAwardAndMedal(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="am.cancel_status in (-1,2)";
			else if(status==0)
				q="am.cancel_status=0";	
			
			 
		q = "select am.id,td.award_cat as category_8 ,mm.medal_name,am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\n" + 
				"am.date_of_award,am.authority,am.date_of_authority,am.modify_by,am.modify_on,am.census_id,am.status from tb_psg_census_awardsnmedal am\n" + 
				"inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n" + 
				"inner join orbat_view_corps co on co.sus_no=am.corps_area\n" + 
				"inner join orbat_view_div di on di.sus_no=am.div_subarea\n" + 
				"inner join orbat_view_bde bd on bd.sus_no=am.bde\n" + 
				"inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n" + 
				"inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n" + 
				"where cast(am.comm_id  as character varying)=? and am.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisAwardsNmedal("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisAwardsNmedal("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	
	public List<Map<String, Object>> getHisBattle_physical_casuality(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="pc.cancel_status in (-1,2)";
			else if(status==0)
				q="pc.cancel_status=0";
			
			 
			q="select  pc.id,pc.census_id,\r\n" + 
					"pc.authority,\r\n" + 
					"ltrim(TO_CHAR(pc.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
					"tdc.label as classification_of_casuality, \r\n" + 
					"pc.name_of_operation,\r\n" + 
					"ltrim(TO_CHAR(pc.date_of_casuality ,'DD-MON-YYYY'),'0') as date_of_casuality,\r\n" + 
					"pc.exact_place,\r\n" + 
					"pc.whether_on,\r\n" + 
					"pc.unit,\r\n" + 
					"td.label as nature_of_casuality,pc.nature_of_casuality as noc,\r\n" + 
					"c1.casuality1,\r\n" + 
					"c2.casuality2,\r\n" + 
					"c3.casuality3,\r\n"+
					"pc.village,\r\n" + 
					"pc.pin,\r\n" + 
					"pc.diagnosis,\r\n" + 
					"pc.desc_others,\r\n" + 
					"cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\r\n" + 
					"case when con.name = 'OTHERS' then pc.country_other \r\n" + 
					"else con.name end as country ,\r\n" + 
					"case when st.state_name = 'OTHERS' then pc.state_other \r\n" + 
					"else st.state_name end as state ,\r\n" + 
					"case when dis.district_name = 'OTHERS' then pc.district_other \r\n" + 
					"else dis.district_name end as district ,\r\n" + 
					"case when te.city_name = 'OTHERS' then pc.tehsil_other \r\n" + 
					"else te.city_name end as tehsil ,\r\n" + 
					"(select tv.label from t_domain_value tv where tv.domainid=pc.cause_of_casuality and tv.codevalue=pc.description) as description,\r\n" + 
					"pc.created_by, \r\n" + 
					"ltrim(TO_CHAR(pc.created_on ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
					"pc.modify_by,\r\n" + 
					"ltrim(TO_CHAR(pc.modify_on ,'DD-MON-YYYY'),'0') as modify_on\r\n" + 
					"from tb_psg_census_battle_physical_casuality pc\r\n" + 
					"inner join  orbat_view_cmd cm on cm.sus_no=pc.command  \r\n" + 
					"inner join orbat_view_corps co on co.sus_no=pc.corps_area\r\n" + 
					"inner join orbat_view_div di on di.sus_no=pc.div_subarea\r\n" + 
					"inner join orbat_view_bde bd on bd.sus_no=pc.bde \r\n" + 
					"left join tb_psg_mstr_casuality1 c1 on c1.id = cast(pc.cause_of_casuality_1 as integer)\r\n" + 
					"left join tb_psg_mstr_casuality2 c2 on c2.id = cast(pc.cause_of_casuality_2 as integer)\r\n" + 
					"left join tb_psg_mstr_casuality3 c3 on c3.id = cast(pc.cause_of_casuality_3 as integer)\r\n"+
					"inner join t_domain_value td on td.codevalue=pc.nature_of_casuality  and td.domainid='NATURE_OF_CASUALITY'\r\n" + 
					"inner join tb_psg_mstr_country con on con.id=cast(pc.country as integer)\r\n" + 
					"inner join tb_psg_mstr_state st on st.state_id=cast(pc.state as integer)\r\n" + 
					"inner join tb_psg_mstr_district dis on dis.district_id=cast(pc.district as integer)\r\n" + 
					"inner join tb_psg_mstr_city te on te.city_id=cast(pc.tehsil as integer)\r\n" + 
					"inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" +
				"where cast(pc.comm_id  as character varying)=? and pc.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				if (rs.getString("casuality3")!=null) {
					columns.put("casuality", rs.getString("casuality3"));
				}
				else if (rs.getString("casuality2")!=null) {
					columns.put("casuality", rs.getString("casuality2"));
				}
				else {
					columns.put("casuality", rs.getString("casuality1"));
				}
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisBatt_phy_casuality("
						+ rs.getObject(1) + ",'"+rs.getString("noc")+"')}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisBatt_phy_casuality("
						+ rs.getObject(1) + ",'"+rs.getString("noc")+"')}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisDiscipline(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="d.cancel_status in (-1,2)";
			else if(status==0)
				q="d.cancel_status=0";
			
			 
		q = "SELECT d.id, d.disi_authority, \n" + 
				"d.disi_authority_date, \n" + 
				"ac.army_act_sec, \n" + 
				"sc.sub_clause,\n" + 
				"t.disc_trialed, \n" + 
				"d.description, \n" + 
				"td.label as type_of_entry,\n" + 
				"d.type_of_entry_other, \n" + 
				"d.award_dt,d.unit_name, \n" + 
				"d.modified_by, \n" + 
				"d.modified_date,\n" + 
				"d.census_id,\n" + 
				"d.status  \n" + 
				"FROM tb_psg_census_discipline d\n" + 
				"inner join t_domain_value td on cast(d.type_of_entry as char)=td.codevalue and td.domainid='DISCIPLINE'\n" + 
				"inner join tb_psg_mstr_army_act_sec ac on ac.id = d.army_act_sec\n" + 
				"inner join tb_psg_mstr_disc_trialed t on t.id = d.trialed_by\n" + 
				"inner join tb_psg_mstr_sub_clause sc on sc.id = d.sub_clause\n" + 
				"where cast(d.comm_id  as character varying)=? and d.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisDiscipline("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisDiscipline("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	
	public List<Map<String, Object>> getHisSsc_to_permt_commission(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="cc.cancel_status in (-1,2)";
			else if(status==0)
				q="cc.cancel_status=0";
			
			 
		q = "SELECT cc.id, cc.authority, cc.date_of_authority, cc.new_personal_no, cc.previous_commission, tc.comn_name as type_of_commission, cc.date_of_permanent_commission, cc.date_of_seniority,cc.eff_date_of_seniority, cc.modified_by, cc.modified_date,cc.census_id,cc.status  FROM tb_psg_change_of_comission cc\n" + 
				"left join tb_psg_mstr_type_of_commission tc on tc.id=cc.type_of_commission_granted\n" + 
				"where cast(comm_id  as character varying)=? and census_id != 0 and cc.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisChange_of_commission("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisChange_of_commission("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisInter_arm_service_transfer(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="iat.cancel_status in (-1,2)";
			else if(status==0)
				q="iat.cancel_status=0";
			
			 
		q = "SELECT iat.id,a.arm_desc as parent_arm_service, iat.authority, iat.date_of_authority,  ac.arm_desc as regt, iat.with_effect_from, iat.modified_by, iat.modified_date,iat.census_id,iat.status  FROM tb_psg_inter_arm_transfer iat\n" + 
				"left join tb_miso_orbat_arm_code ac on ac.arm_code=iat.regt\n" + 
				"inner join  tb_miso_orbat_arm_code  a on  a.arm_code=iat.parent_arm_service\n" + 
				"where cast(iat.comm_id  as character varying)=? and iat.census_id != 0 and iat.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisInter_arm_transfer("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisInter_arm_transfer("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisExtension_of_ssc(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="cancel_status in (-1,2)";
			else if(status==0)
				q="cancel_status=0";
			
			 
		q = "SELECT id, authority, date_of_authority,fromdt, todt, modified_by, modified_date,census_id,status  FROM tb_psg_extension_of_comission\n" + 
				"where cast(comm_id  as character varying)=? and status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisExtension_of_comission("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisExtension_of_comission("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisNon_effective(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="ne.cancel_status in (-1,2)";
			else if(status==0)
				q="ne.cancel_status=0";
			
			 
			q = "SELECT ne.id, ne.non_ef_authority as authority, ne.date_of_authority_non_ef as date_of_authority,td.causes_name as cause_of_non_effective,ne.cause_of_non_effective as cause_ne, ne.date_of_non_effective, ne.modified_by, ne.modified_date,  \n" + 
					"ne.census_id,ne.status\n" + 
					"FROM tb_psg_non_effective ne \n" + 
					"inner join tb_psg_mstr_cause_of_non_effective td on ne.cause_of_non_effective=td.id::text " + 
					"where cast(ne.comm_id  as character varying)=? and ne.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?"
						+ "Plz also Cancel Address Entry.') ){CancelHisNon_effective("
						+ rs.getObject(1) + ",'"+rs.getString("cause_ne")+"')}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisNon_effective("
						+ rs.getObject(1) + ",'"+rs.getString("cause_ne")+"')}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public List<Map<String, Object>> getHisSecondment(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			if(status==-1)
				q="s.cancel_status in (-1,2)";
			else if(status==0)
				q="s.cancel_status=0";
			 
		q = "SELECT s.id, s.authority, s.date_of_authority,td.seconded_to as seconded_to, secondment_effect, s.modified_by, s.modified_date,s.census_id,s.status  FROM tb_psg_census_secondment s\n" + 
				"inner join tb_psg_mstr_seconded_to td on s.seconded_to =td.id \n" + 
				"where cast(s.comm_id  as character varying)=? and s.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisSecondment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisSecondment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	
	public List<Map<String, Object>> getHisDeserter(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="d.cancel_status in (-1,2)";
			else if(status==0)
				q="d.cancel_status=0";
			
			 
		q = "SELECT d.id,d.arm_type_weapon,td.label as arm_type,d.dt_desertion, d.dt_recovered, td1.label as desertion_cause, ot_desertion_cause, d.indl_class, d.approved_by, d.approved_date,d.census_id,d.status  FROM tb_psg_deserter d\n" + 
				"inner join t_domain_value td on d.arm_type=td.codevalue and td.domainid='ARM_TYPE'\n" + 
				"inner join t_domain_value td1 on d.desertion_cause=td1.codevalue and td1.domainid='CAUSE_OF_DESRTION'\n" + 
				"where cast(comm_id  as character varying)=? and status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisDeserter("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisDeserter("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
	
	public ArrayList<ArrayList<String>> madical_cat_HisGetData(String id)
    {
            ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
            Connection conn = null;
            String q="";
    
            try{          
                    conn = dataSource.getConnection();                         
                    PreparedStatement stmt=null;
                    String[] myArr=id.split("I");
                    StringBuilder idList = new StringBuilder();
                    		for (String idA : myArr) {
                    		   if (idList.length() > 0) {
                    		     idList.append(",");
                    		   }
                    		   idList.append("?");
                    		}
                    
                    
                    q="select authority,date_of_authority,clasification,id,shape_status,shape_value,from_date,to_date,diagnosis,shape_sub_value,other,from_date_1bx,to_date_1bx,diagnosis_1bx,\r\n" + 
                                    "(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis) as des_1,(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis_1bx) as bxdes_1 \r\n" + 
                                    "from tb_psg_medical_category where id in ("+idList+") order by id" ;
                           
                            stmt=conn.prepareStatement(q);
                            for (int i = 0; i < myArr.length; i++) {
                            	  stmt.setInt(i+1,Integer.parseInt(myArr[i]));
                            	}
                           
                    
                            ResultSet rs = stmt.executeQuery();      
                            while (rs.next()) {
                                    ArrayList<String> list = new ArrayList<String>();
                                    list.add(rs.getString("shape_status")); //0
                                    list.add(rs.getString("shape_value")); //1
                                    list.add(rs.getString("from_date"));        //2
                                    list.add(rs.getString("to_date"));        //3
                                    if(rs.getString("des_1")!=null)
                                            list.add(rs.getString("diagnosis")+"-"+rs.getString("des_1"));        //4
                                    else
                                            list.add("");

                                    list.add(rs.getString("id")); //10 -> 5
                                    list.add(rs.getString("authority")); //11 -> 6
                                    list.add(rs.getString("date_of_authority")); //12 -> 7
                                    list.add(rs.getString("clasification")); //13 -> 8
                                    list.add(rs.getString("shape_sub_value")); //9
                                    list.add(rs.getString("other")); //10
                                    list.add(rs.getString("from_date_1bx")); //11
                                    list.add(rs.getString("to_date_1bx")); //12
                                    
                                    if(rs.getString("bxdes_1")!=null)
                                            list.add(rs.getString("diagnosis_1bx")+"-"+rs.getString("bxdes_1"));        //13
                                    else
                                            list.add("");
                                    
                                    alist.add(list);
                     }
                  rs.close();
                  stmt.close();
                  conn.close();
               }catch (SQLException e) {
                            //throw new RuntimeException(e);
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
	public List<Map<String, Object>> getHisCSD(BigInteger comm_id, int status, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="cd.cancel_status in (-1,2)";
			else if(status==0)
				q="cd.cancel_status=0";
			
			 
		q = "select cd.id,td.label as relation ,cd.name,cd.date_of_birth,cd.type_of_card,cd.card_no,cd.modified_by,cd.modified_date,cd.census_id,cd.status from tb_psg_canteen_card_details1 cd\n" + 
				"inner join t_domain_value td on td.codevalue=cd.relation::text and td.domainid='CSD_CARD'\n" + 
				"where cast(cd.comm_id  as character varying)=? and cd.status in (1,2) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisCSD("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisCSD("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("census_id")==null || rs.getString("census_id").equals("0")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectData);
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
