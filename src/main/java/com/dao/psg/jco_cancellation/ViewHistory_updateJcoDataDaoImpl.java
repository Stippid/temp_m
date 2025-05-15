package com.dao.psg.jco_cancellation;

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

public class ViewHistory_updateJcoDataDaoImpl implements ViewHistory_updateJcoDataDao{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> getHisChangeOfpost(int jco_id,int status,HttpSession session) {
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
			 
		q = "select \n" + 
				"ra.type_of_post ,\n" + 
				"r.created_by,\n" + 
				"TO_CHAR(r.created_date,'DD-MON-YYYY') as created_date ,r.modified_by,\n" + 
				"TO_CHAR(r.modified_date,'DD-MON-YYYY') as modified_date \n" + 
				"from tb_psg_census_jco_or_p p \n" + 
				"inner join tb_psg_change_type_of_posting_jco r on p.id = r.jco_id \n" + 
				"inner join tb_psg_mstr_type_of_post ra on ra.id = r.type_of_post\n" + 
				"\n" + 
				"and p.status in ('1','5') \n" + 
				"where r.jco_id =? and r.status in (1,2) and "+q +" order by cr.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c")  )
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
	
	public List<Map<String, Object>> getHisChangeOfRank(int jco_id,int status,HttpSession session) {
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
			 
		q = "select cr.id,acc.army_no, acc.id as army_id,cr.authority ,cr.date_of_attestation,cr.date_of_authority ,cr.date_of_rank,\n" + 
				"COALESCE((select user_sus_no from logininformation where username=cr.created_by),'') as sus,cr.initiated_from,\n"+
				"cue.rank as rank, cr.modified_by, cr.modified_date,cr.jco_id,cr.status from tb_psg_change_of_rank_jco cr\n" + 
				"inner join tb_psg_mstr_rank_jco cue on cue.id=cr.rank\n" + 
				"left join tb_psg_change_in_army_no_jco acc on cr.id=acc.rank_id \n" + 
				"where cr.jco_id=? and cr.status in (1,2) and "+q +" order by cr.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c")  )
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
	
	public List<Map<String, Object>> getHisChangeOfName(int jco_id,int status,HttpSession session) {
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
			 
		q = "select na.id,na.authority,COALESCE((select user_sus_no from logininformation where username=na.created_by),'') as sus,na.initiated_from,na.date_of_authority,na.first_name,na.middle_name,na.last_name, na.modified_by, na.modified_date,na.status  from  tb_psg_change_name_jco na \n" + 
				"where jco_id=? and status in (1,2) and "+q+" order by na.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if(((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )  )
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
	public List<Map<String, Object>> getHisChangeOfAppointment(int jco_id,int status,HttpSession session) {
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
			 
		q = "select ap.id,ap.appt_authority,ap.appt_date_of_authority,ap.date_of_appointment,cue.appointment as description, ap.modified_by, ap.modified_date,ap.x_list_loc,ap.x_list_sus_no,\n" +
				"COALESCE((select user_sus_no from logininformation where username=ap.created_by),'') as sus,ap.initiated_from,\n"+
				"cue.appointment,ap.status from tb_psg_change_of_appointment_jco ap\n" + 
				"inner join  tb_psg_mstr_appt_jco cue on cue.id=ap.appointment  \n" + 
				"where ap.jco_id=? and ap.status in (1,2) and "+q+" order by ap.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisIdentity_Card(int jco_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=ic.created_by),'') as sus,ic.initiated_from,\n"+
				"ec.eye_cl_name,ic.identity_image,ic.image_updated_date,ic.Issue_authority,ic.modified_by,ic.modified_date\n" + 
				",ic.status from tb_psg_identity_card_jco ic\n" + 
				"inner join tb_psg_mstr_hair_colour hc on hc.id=ic.hair_colour\n" + 
				"inner join tb_psg_mstr_eye_colour ec on ec.id=ic.eye_colour\n" + 
				"where ic.jco_id=? and ic.status in (1,2) and "+q+" order by ic.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisChangeOfReligion(int jco_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=cr.created_by),'') as sus,cr.initiated_from,\n"+
				"cr.other,cr.status from tb_psg_change_religion_jco cr\n" + 
				"inner join tb_psg_mstr_religion mr on mr.religion_id=cr.religion\n" + 
				"where cr.jco_id=? and cr.status in (1,2) and "+q+" order by cr.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisfamily_married(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "SELECT \n" + 
				"	  distinct cfm.id, \n" + 
				"	  COALESCE((select user_sus_no from logininformation where username=cfm.created_by),'') as sus,cfm.initiated_from,\n" + 
				"	   cfm.status, \n" + 
				"	  cfm.created_by, \n" + 
				"	  ltrim(TO_CHAR(cfm.created_date ,'DD-MON-YYYY'),'0') as created_date,\n" + 
				"	  ltrim(TO_CHAR(cfm.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\n" + 
				"	  cfm.maiden_name, \n" + 
				"	  ltrim(TO_CHAR(cfm.marriage_date ,'DD-MON-YYYY'),'0') as marriage_date, \n" + 
				"	  cfm.modified_by, \n" + 
				"	  ltrim(TO_CHAR(cfm.modified_date,'DD-MON-YYYY'),'0') as modified_date,  \n" + 
				"	  ltrim(TO_CHAR(  cfm.divorce_date,'DD-MON-YYYY'),'0') as divorce_date,\n" + 
				"	  ltrim(TO_CHAR(  cfm.separated_from_dt,'DD-MON-YYYY'),'0') as separated_from_dt,\n" + 
				"	  ltrim(TO_CHAR(  cfm.separated_to_dt,'DD-MON-YYYY'),'0') as separated_to_dt,\n" + 
				"	  \n" + 
				"	  cfm.authority, \n" + 
				"	  ltrim(TO_CHAR(  cfm.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 
				"	  cfm.jco_id,\n" + 
				"	  ms.marital_name, cfm.marital_status,	  \n" + 
				"	  cfm.place_of_birth, 	\n" + 
				"	  PGP_SYM_DECRYPT(cfm.pan_card ::bytea,current_setting('miso.version'))  as   pan_card,\n" + 
				"	  PGP_SYM_DECRYPT(cfm.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,	  \n" + 
				"	   case when upper(n.nationality_name)='OTHERS' or  upper(n.nationality_name)='OTHER' then cfm.other_nationality      else  n.nationality_name end as nationality_name, \n" + 
				"	   case when upper(ev.label)='OTHERS' or  upper(ev.label)='OTHER' then cfm.other_spouse_ser      else  ev.label end as spouse_service, \n" + 
				"	  cfm.spouse_personal_no \n" + 
				"	FROM tb_psg_census_family_married_jco cfm\n" + 
				"	LEFT JOIN tb_psg_mstr_nationality n ON cfm.nationality = n.nationality_id\n" + 
				"	LEFT JOIN tb_psg_mstr_marital_status ms ON cfm.marital_status = ms.marital_id\n" + 
				" LEFT JOIN t_domain_value ev ON cfm.spouse_service::text = ev.codevalue and ev.domainid='EX_SERVICEMAN' \n" + 
				"	WHERE cfm.jco_id=? and cfm.status in (1,2)  " + 
				"and "+q+" order by cfm.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") )   )
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

	public List<Map<String, Object>> getHisSpouseQualification(int spouse_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=qua.created_by),'') as sus,qua.initiated_from,\n"+
				"dc.div,qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification_jco where id=qua.id), ','))   qsub\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.status from tb_psg_census_spouse_qualification_jco qua\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE'" + 
				"where qua.spouse_id=? and qua.status in (2,1) and "+q;
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,spouse_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	public List<Map<String, Object>> getHisPendingSpouseQualification(int jco_id,int status,HttpSession session) {
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
			 
		q = "select distinct qua.id,dom.label as type,fm.maiden_name,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year,qua.modified_by,qua.modified_date, \n" + 
				"COALESCE((select user_sus_no from logininformation where username=qua.created_by),'') as sus,qua.initiated_from,\n" + 
				"dc.div,qua.institute, array_to_string(ARRAY(select sub.description from (select unnest(string_to_array(subject, ','))  as sub_id from tb_psg_census_spouse_qualification_jco where id=qua.id) qsub\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub.sub_id as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.status from tb_psg_census_spouse_qualification_jco qua\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE' left join tb_psg_census_family_married_jco fm on qua.spouse_id=fm.id where qua.jco_id=? and qua.status in (2,1) and "
				+ ""+q+" order by qua.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	
	public List<Map<String, Object>> getHisChild(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select ch.id,ch.type,ch.name,ch.aadhar_no,ch.adoted,ch.date_of_adpoted,ch.date_of_birth,ch.modified_by,\n" + 
				"COALESCE((select user_sus_no from logininformation where username=ch.created_by),'') as sus,ch.initiated_from,\n"+
				"ch.modified_date,ch.pan_no,td.label as relationship,ch.status from tb_psg_census_children_jco ch\n" + 
				"inner join  t_domain_value td on td.codevalue=cast(ch.relationship as char) and td.domainid='CHILD_RELATIONSHIP'\n" + 
				"where ch.jco_id =? and ch.status in (1,2) and "+q+" order by ch.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisNokAddress(int jco_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=no.created_by),'') as sus,no.initiated_from,\n"+
				"st.state_name,dis.district_name,te.city_name,no.status from tb_psg_census_nok_jco no\n" + 
				"inner join tb_psg_mstr_relation re on re.id=no.nok_relation\n" + 
				"inner join tb_psg_mstr_country co on co.id=no.nok_country\n" + 
				"inner join tb_psg_mstr_state st on st.state_id=no.nok_state\n" + 
				"inner join tb_psg_mstr_district dis on dis.district_id=no.nok_district\n" + 
				"inner join tb_psg_mstr_city te on te.city_id=cast(no.nok_tehsil as integer)\n" + 
				"where no.jco_id=? and no.status in  (1,2)\n" + 
				"and "+q+" order by no.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisAddress(int jco_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=addr.created_by),'') as sus,addr.initiated_from,\n"+
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
				",addr.spouse_village,addr.spouse_pin_code,addr.spouse_near_railway_station,addr.status\n" + 
				"\n" + 
				",addr.spouse_rural_urban_semi from tb_psg_census_address_jco addr\n" + 
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
				"where addr.jco_id=? and addr.status in  (1,2) and "+q+" order by addr.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisContactDetails(int jco_id,int status,HttpSession session) {
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
			 
		q = "select ac.id,PGP_SYM_DECRYPT(ac.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(ac.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(ac.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,ac.modified_by,ac.modified_date,\n"+ 
				"COALESCE((select user_sus_no from logininformation where username=ac.created_by),'') as sus,ac.initiated_from,ac.status from tb_psg_census_contact_cda_account_details_jco ac\n" + 
				"where ac.jco_id=? and status in (1,2) and "+q+" order by ac.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if(rs.getString("sus").equals("")  && !accesslvl.equals("MISO") )
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
	public List<Map<String, Object>> getHisLanguageDetails(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select la.id, la.authority, la.date_of_authority,"+
				"COALESCE((select user_sus_no from logininformation where username=la.created_by),'') as sus,la.initiated_from,\n"+
				" lp.ind_language,ls.name as lang_std,la.other_language,la.other_lang_std,la.modify_by,la.modify_on,la.status from tb_psg_census_language_jco la\n" + 
				"inner join tb_psg_lang_std ls on ls.id=la.lang_std\n" + 
				"inner join tb_psg_mstr_indian_language lp on lp.id=la.language\n" + 
				"where  la.language!=0 and  la.jco_id=? and la.status in (1,2)\n" + 
				"and "+q+" order by la.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	public List<Map<String, Object>> getHisForeignLang(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select la.id, la.authority, la.date_of_authority,"+
				"COALESCE((select user_sus_no from logininformation where username=la.created_by),'') as sus,la.initiated_from,\n"+
				" la.f_exam_pass,lp.foreign_language_name,ls.name as lang_std,lo.name as lang_proff,la.f_other_language,la.f_other_lang_std,la.f_other_prof,la.modify_by,la.modify_on,la.status from tb_psg_census_language_jco la\n" + 
				"				inner join tb_psg_lang_std ls on ls.id=la.lang_std \n" + 
				"				inner join tb_psg_lang_proof lo on lo.id=la.f_lang_prof\n" + 
				"				inner join tb_psg_mstr_foreign_language lp on lp.id=la.foreign_language\n" + 
				"				where la.language =0 and la.jco_id=? and la.status in (1,2) and "+q+" order by la.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisQualification(int jco_id,int status,HttpSession session) {
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
				"dc.div,qua.institute, array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_qualification_jco where id=qua.id), ','))   qsub\n" + 
				"				inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,\n" + 
				"				qua.modify_by,qua.modify_on	,qua.status,															  \n" + 
				"COALESCE((select user_sus_no from logininformation where username=qua.created_by),'') as sus,qua.initiated_from\n"+
				"				from tb_psg_census_qualification_jco qua\n" + 
				"				inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass \n" + 
				"				inner join tb_psg_mstr_degree deg on deg.id=qua.degree \n" + 
				"				inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"				inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"				inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE'\n" + 
				"				where qua.jco_id=? and qua.status in (2,1) and "+q+" order by qua.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisPromotionalExam(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select pe.id,pe.modify_on ,pe.modify_by,pe.authority,pe.date_of_authority,"+
				"COALESCE((select user_sus_no from logininformation where username=pe.created_by),'') as sus,pe.initiated_from,\n"+
				"pe.exam_other,td.promo_exam as exam,pe.date_of_passing,pe.status from tb_psg_census_promo_exam_jco pe\n" + 
				"inner join tb_psg_mstr_promotional_exam_jco td on td.id::text=pe.exam \n" + 
				"where pe.jco_id=? and pe.status in (1,2) and "+q+" order by pe.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	public List<Map<String, Object>> getHisArmyCourse(int jco_id,int status,HttpSession session) {
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
				"ac.authority,ac.date_of_authority,ac.modify_on,ac.modify_by,ac.status, \n" + 
				"COALESCE((select user_sus_no from logininformation where username=ac.created_by),'') as sus,ac.initiated_from\n"+
				"from tb_psg_census_army_course_jco ac\n" + 
				"inner join tb_psg_mstr_div_grade dg on dg.id=cast(ac.div_grade as integer)\n" + 
				"inner join t_domain_value td on td.codevalue=ac.course_type  and td.domainid='COURSE_TYPE'\n" + 
				"inner join tb_psg_mstr_course_institute ins on ins.id=ac.course_institute and ins.status='active' and category='2' "
				+"where ac.jco_id=? and ac.status in (1,2) and "+q+" order by ac.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisBpetDetails(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select bt.id,bt.modified_date,bt.modified_by,"+
				"COALESCE((select user_sus_no from logininformation where username=bt.created_by),'') as sus,bt.initiated_from,\n"+
				"bt.bept_result_others,td.bpet_qt as bpet_qe,tdb.bpet_result as bpet_result,bt.year,bt.unit_sus_no,bt.status from tb_psg_census_bpet_jco bt \n" +
		
				"				inner join  tb_psg_mstr_bpet_qt td on td.id::text=bt.bpet_qe \n" + 
				"				inner join tb_psg_mstr_bpet_result tdb on tdb.id::text=bt.bpet_result " + 
				"where bt.jco_id=? and bt.status in (1,2) and "+q+" order by bt.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	public List<Map<String, Object>> getHisFiringDetails(int jco_id,int status,HttpSession session) {
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
			 
		q = "select bt.id,bt.modified_date,bt.modified_by,"+
				"COALESCE((select user_sus_no from logininformation where username=bt.created_by),'') as sus,bt.initiated_from,\n"+
				"bt.ot_firing_grade,td.firing_qt as firing_event_qe,tdb.firing_result as firing_grade,bt.year,bt.firing_unit_sus_no,bt.status from tb_psg_census_firing_standard_jco bt\n" + 
				"				inner join tb_psg_mstr_firing_qt td on td.id::text=bt.firing_event_qe \n" + 
				"				inner join tb_psg_mstr_firing_result tdb on tdb.id::text=bt.firing_grade \n" + 
				"where bt.jco_id=? and bt.status in (1,2) and "+q+" order by bt.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisAllergy(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "select al.id,al.medicine,al.modified_date,al.modified_by,al.status,"+
				"COALESCE((select user_sus_no from logininformation where username=al.created_by),'') as sus,al.initiated_from\n"+
				" from tb_psg_allergic_to_medicine_jco al\n" + 
				"where jco_id=? and status in (1,2) and "+q+" order by al.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisMedicalCategory(int jco_id ,int status,HttpSession session) {
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
				"med.modify_on,med.modify_by,med.authority,med.date_of_authority, \n" + 
				"COALESCE((select user_sus_no from logininformation where username=med.created_by),'') as sus,med.initiated_from\n"+
				"from tb_psg_medical_category_jco med \n" + 
				"where med.jco_id=? and med.status in (1,2) and "+q+" \n" + 
				"group by med.modify_on,med.modify_by,med.authority,med.date_of_authority,med.created_by,med.initiated_from";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	
	public List<Map<String, Object>> getHisForeignCountry(int jco_id,int status,HttpSession session) {
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
			 
		q = "select  pfc.id,td.visit_purpose as purpose_visit,fc.name as country,pfc.period,pfc.from_dt,pfc.to_dt,pfc.other_country,pfc.other_purpose_visit,pfc.modified_date,pfc.modified_by,pfc.status, \n" + 
				"COALESCE((select user_sus_no from logininformation where username=pfc.created_by),'') as sus,pfc.initiated_from\n"+
				"from tb_psg_census_foreign_country_jco pfc\n" + 
				"inner join tb_psg_mstr_purposeof_visit td on td.id = pfc.purpose_visit\n" + 
				"inner join tb_psg_foreign_country fc on fc.id=pfc.country\n" + 
				"where pfc.jco_id=? and pfc.status in (1,2) and "+q+" order by pfc.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisAwardAndMedal(int jco_id,int status,HttpSession session) {
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
				"COALESCE((select user_sus_no from logininformation where username=am.created_by),'') as sus,am.initiated_from,\n"+
				"am.date_of_award,am.authority,am.date_of_authority,am.modify_by,am.modify_on,am.status from tb_psg_census_awardsnmedal_jco am\n" + 
				"inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n" + 
				"inner join orbat_view_corps co on co.sus_no=am.corps_area\n" + 
				"inner join orbat_view_div di on di.sus_no=am.div_subarea\n" + 
				"inner join orbat_view_bde bd on bd.sus_no=am.bde\n" + 
				"inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n" + 
				"inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n" + 
				"where am.jco_id=? and am.status in (1,2) and "+q+" order by am.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	
	public List<Map<String, Object>> getHisBattle_physical_casuality(int jco_id,int status,HttpSession session) {
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
			
			 
			q="select  pc.id,\r\n" + 
					"pc.authority,\r\n" + 
					"COALESCE((select user_sus_no from logininformation where username=pc.created_by),'') as sus,pc.initiated_from,\n"+
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
					"from tb_psg_census_battle_physical_casuality_jco pc\r\n" + 
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
					"inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY' \r\n" +
				"where pc.jco_id=? and pc.status in (1,2) and "+q+" order by pc.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	public List<Map<String, Object>> getHisDiscipline(int jco_id,int status,HttpSession session) {
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
				"d.status,  \n" + 
				"COALESCE((select user_sus_no from logininformation where username=d.created_by),'') as sus,d.initiated_from\n"+
				"FROM tb_psg_census_discipline_jco d\n" + 
				"inner join t_domain_value td on cast(d.type_of_entry as char)=td.codevalue and td.domainid='DISCIPLINE'\n" + 
				"inner join tb_psg_mstr_army_act_sec ac on ac.id = d.army_act_sec\n" + 
				"inner join tb_psg_mstr_disc_trialed t on t.id = d.trialed_by\n" + 
				"inner join tb_psg_mstr_sub_clause sc on sc.id = d.sub_clause\n" + 
				"where d.jco_id=? and d.status in (1,2) and "+q+" order by d.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	
	
	public List<Map<String, Object>> getHisInter_arm_service_transfer(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "SELECT iat.id,a.arm_desc as parent_arm_service, iat.authority, iat.date_of_authority, iat.record_office_unit, "+
				"COALESCE((select user_sus_no from logininformation where username=iat.created_by),'') as sus,iat.initiated_from,\n"+
				"  ac.arm_desc as regt, iat.with_effect_from, iat.modified_by, iat.modified_date,iat.status  FROM tb_psg_inter_arm_transfer_jco iat\n" + 
				"left join tb_miso_orbat_arm_code ac on ac.arm_code=iat.regt\n" + 
				"inner join  tb_miso_orbat_arm_code  a on  a.arm_code=iat.parent_arm_service\n" + 
				"where jco_id=? and iat.status in (1,2) and "+q+" order by iat.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) || rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c")  )
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
	
	
	public List<Map<String, Object>> getHisNon_effective(int jco_id,int status,HttpSession session) {
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
					"ne.jco_id,ne.status, \n" + 
					"COALESCE((select user_sus_no from logininformation where username=ne.created_by),'') as sus,ne.initiated_from\n"+
					"FROM tb_psg_non_effective_jco ne \n" + 
					"inner join tb_psg_mstr_cause_of_non_effective_jco td on ne.cause_of_non_effective=td.id::text " + 
					"where ne.jco_id=? and ne.status in (1,2) and "+q+" order by ne.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisNon_effective("
						+ rs.getObject(1) + ",'"+rs.getString("cause_ne")+"')}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisNon_effective("
						+ rs.getObject(1) + ",'"+rs.getString("cause_ne")+"')}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	
	
	public List<Map<String, Object>> getHisDeserter(int jco_id,int status,HttpSession session) {
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
			
			 
		q = "SELECT d.id,d.arm_type_weapon,td.label as arm_type,d.dt_desertion, d.dt_recovered,td2.label as recovered_arms,"+
				"COALESCE((select user_sus_no from logininformation where username=d.created_by),'') as sus,d.initiated_from,\n"+
				" td1.label as desertion_cause, ot_desertion_cause, d.indl_class, d.approved_by, d.approved_date,d.status  FROM tb_psg_deserter_jco d\n" + 
				"inner join t_domain_value td on d.arm_type=td.codevalue and td.domainid='ARM_TYPE'\n" + 
				"left join t_domain_value td2 on d.recovered_arms=td2.codevalue and td2.domainid='ARM_TYPE'\n" + 
				"inner join t_domain_value td1 on d.desertion_cause=td1.codevalue and td1.domainid='CAUSE_OF_DESRTION'\n" + 
				"where jco_id=? and status in (1,2) and "+q+" order by d.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
                                    "from tb_psg_medical_category_jco where id in ("+idList+") order by id" ;
                           
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
	public List<Map<String, Object>> getHisCSD(int jco_id, int status, HttpSession session) {
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
			
			 
		q = "select cd.id,td.label as relation ,cd.name,cd.date_of_birth,cd.type_of_card,cd.card_no,cd.modified_by,cd.modified_date,"+
				"COALESCE((select user_sus_no from logininformation where username=cd.created_by),'') as sus,cd.initiated_from,\n"+
				"cd.status from tb_psg_canteen_card_details1_jco cd\n" + 
				"inner join t_domain_value td on td.codevalue=cd.relation::text and td.domainid='CSD_CARD'\n" + 
				"where cd.jco_id=? and cd.status in (1,2) and "+q+" order by cd.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> getHisTrade(int jco_id, int status, HttpSession session) {
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
			
			 
		q = "select cd.id,cd.td_authority,cd.td_date_authority,cd.date_of_trade,td.trade,cd.modified_by,cd.modified_date,\n" + 
				"				COALESCE((select user_sus_no from logininformation where username=cd.created_by),'') as sus,cd.initiated_from,\n" + 
				"				cd.status from tb_psg_census_change_in_trade_jco cd\n" + 
				"				inner join tb_psg_mstr_trade_jco td on cd.trade=td.id\n" + 
				"				where cd.jco_id=? and cd.status in (1,2) and "+q+" order by cd.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisTrade("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisTrade("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> getHisPayClass(int jco_id, int status, HttpSession session) {
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
			
			 
		q = "select cd.id,cd.cla_authority,cd.cla_date_authority,cd.date_of_class,td.class_pay,cd.modified_by,cd.modified_date,\n" + 
				"				COALESCE((select user_sus_no from logininformation where username=cd.created_by),'') as sus,cd.initiated_from,\n" + 
				"				cd.status from tb_psg_census_change_in_class_pay_jco cd\n" + 
				"				inner join tb_psg_mstr_class_pay_jco td on cd.cla_class=td.id\n" + 
				"				where cd.jco_id=? and cd.status in (1,2) and "+q+" order by cd.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisPayClass("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisPayClass("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> getHisPayGroup(int jco_id, int status, HttpSession session) {
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
			
			 
		q = "select cd.id,cd.gp_authority,cd.gp_date_authority,cd.date_of_group,td.pay_group,cd.modified_by,cd.modified_date,\n" + 
				"			COALESCE(	(select user_sus_no from logininformation where username=cd.created_by),'') as sus,cd.initiated_from,\n" + 
				"				cd.status from tb_psg_census_change_in_pay_group_jco cd\n" + 
				"				inner join tb_psg_mstr_pay_group_jco td on cd.gp_group=td.id\n" + 
				"				where cd.jco_id=? and cd.status in (1,2) and "+q+" order by cd.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisPayGroup("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisPayGroup("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> getHisSeniority(int jco_id, int status, HttpSession session) {
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
			
			 
		q = "select cd.id,cd.se_authority,cd.se_date_authority,cd.reason_for_change,cd.date_of_seniority,cd.modified_by,cd.modified_date,\n" + 
				"				COALESCE((select user_sus_no from logininformation where username=cd.created_by),'') as sus,cd.initiated_from,\n" + 
				"				cd.status from tb_psg_census_change_in_date_of_seniority_jco cd		\n" + 
				"				where cd.jco_id=? and cd.status in (1,2) and "+q+" order by cd.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisSeniorityDT("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisSeniorityDT("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> getHisAttachmentDetails(int jco_id, int status, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		 String accesslvl = session.getAttribute("roleAccess").toString();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";
			if(status==-1)
				q="ad.cancel_status in (-1,2)";
			else if(status==0)
				q="ad.cancel_status=0";
			
			 
		q = "select ad.id,ad.att_authority,ltrim(TO_CHAR(ad.att_date_authority  ,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 
				"COALESCE((select user_sus_no from logininformation where username=ad.created_by),'') as sus,ad.initiated_from,\n"+
				"ad.att_movement_number,ltrim(TO_CHAR(ad.att_date_of_move  ,'DD-MON-YYYY'),'0') as date_of_move,ad.att_sus_no,\n" + 
				"ad.att_place,ltrim(TO_CHAR(ad.att_from  ,'DD-MON-YYYY'),'0') as from_date,ltrim(TO_CHAR(ad.att_to  ,'DD-MON-YYYY'),'0') as to_date,ut.unit_name,\n" + 
				"ad.att_duration,ad.att_reasons,ad.modified_by,ltrim(TO_CHAR(ad.modified_date,'DD-MON-YYYY'),'0') as modified_date,\n" + 
				"ad.created_by,ltrim(TO_CHAR(ad.created_date,'DD-MON-YYYY'),'0') as created_date, ad.status\n" + 
				"from  tb_psg_census_attachment_details_jco ad\n" + 
				"left join tb_miso_orbat_unt_dtl ut on  ad.att_sus_no=ut.sus_no\n" + 
				"where ad.jco_id=? and ad.status in (1,2) and \n" + 
				" "+q+" order by ad.id ";
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
			
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
				
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisAttachment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisAttachment("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				if(status==-1 ) {
					if((rs.getString("sus").equals("") && !accesslvl.equals("MISO") ) ||  rs.getString("initiated_from")==null ||  rs.getString("initiated_from").equals("c") )
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
	
	@Override
	public List<Map<String, Object>> GetJcoOrCensusDataCancelHistory(int jco_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct 	\n" + "c.id, \n" + "c.marital_status as marital_status,\n" + "c.full_name,\n"
					+ "c.father_name,\n" + "c.father_dob,\n" + "c.mother_name,\n" + "c.mother_dob,\n"
					+ "ra.rank as rank, \n" + "aj.appointment as appointment,c.record_office_sus,c.record_office,c.date_of_attestation,\n"
					+ "a.date_of_appointment as date_appointment, \n"
					+ "(select post.dt_of_tos  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\n"
					+ "(select post.unit_description  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\n"
					+ "c.unit_sus_no,\n" + "i.id_card_no,\n" 
					+ "case when upper(re.religion_name)='OTHERS' OR   upper(re.religion_name)='OTHERS' then ret.other\n" + 
					"     else re.religion_name\n" + 
					"	 end as religion_name,"
					+ "p.arm_desc as parent_arm,\n"
					+ "c.date_of_birth, \n" + "c.enroll_dt,ra.id as rank_id ,aj.id as appoint_id,\n"
					+ "fv.unit_name as command,c.regiment,c.update_jco_status,"
					+ "t.trade as trade,cl.class_pay as class_pay,gp.pay_group as pay_group,c.date_of_seniority as date_of_seniority\n" + "from tb_psg_census_jco_or_p c\n"
					+ "inner join tb_psg_mstr_rank_jco ra on  ra.id = c.rank \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\n"
					+ "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\n"
					+ "inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
					+ "left join tb_psg_change_of_appointment_jco a on c.id = a.jco_id and  a.status ='1'\n"
					+ "left join tb_psg_mstr_appt_jco aj on aj.id=a.appointment \n"
					+ "left join tb_psg_identity_card_jco i on i.jco_id = c.id and i.status=1  \n"
					+ "left join tb_psg_change_religion_jco ret on ret.jco_id=c.id and ret.status=1\n"
					+ "left join tb_psg_mstr_religion re on re.religion_id = ret.religion    \n"
					+ "left join tb_miso_orbat_arm_code p on p.arm_code = c.arm_service\n"
					+ "left join tb_psg_mstr_trade_jco t on t.id = c.trade\n"
					+ "left join tb_psg_mstr_class_pay_jco cl on cl.id = c.class_pay\n"
					+ "left join tb_psg_mstr_pay_group_jco gp on gp.id = c.pay_group\n"
					+ "where (c.status='1' or c.status='5' or c.status='4') and  c.id=? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
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
}
