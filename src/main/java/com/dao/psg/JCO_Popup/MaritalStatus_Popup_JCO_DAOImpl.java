package com.dao.psg.JCO_Popup;

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

public class MaritalStatus_Popup_JCO_DAOImpl implements MaritalStatus_Popup_JCO_DAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> MaritalStatus_PopUp_JCO(int maritalstatus_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				
				
				q="SELECT \r\n" + 
						"	  distinct cfm.id, \r\n" + 
						"	  cfm.created_by, \r\n" + 
						"	  ltrim(TO_CHAR(cfm.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
						"	  ltrim(TO_CHAR(cfm.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
						"	  cfm.maiden_name, \r\n" + 
						"	  ltrim(TO_CHAR(cfm.marriage_date ,'DD-MON-YYYY'),'0') as marriage_date, \r\n" + 
						"	  cfm.modified_by, \r\n" + 
						"	  ltrim(TO_CHAR(cfm.modified_date,'DD-MON-YYYY'),'0') as modified_date,  \r\n" + 
						"	  ltrim(TO_CHAR(  cfm.divorce_date,'DD-MON-YYYY'),'0') as divorce_date,\r\n" + 
						"	  ltrim(TO_CHAR(  cfm.separated_from_dt,'DD-MON-YYYY'),'0') as separated_from_dt,\r\n" + 
						"	  ltrim(TO_CHAR(  cfm.separated_to_dt,'DD-MON-YYYY'),'0') as separated_to_dt,\r\n" + 
						"	  cfm.authority, \r\n" + 
						"	  ltrim(TO_CHAR(  cfm.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
						"	  cfm.jco_id,\r\n" + 
						"	  ms.marital_name, \r\n" + 
						"	  PGP_SYM_DECRYPT(cfm.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number, \r\n" + 
						"	  cfm.place_of_birth, \r\n" + 
						"	 PGP_SYM_DECRYPT(cfm.pan_card ::bytea,current_setting('miso.version'))  as pan_card, \r\n" + 
						"	   case when upper(n.nationality_name)='OTHERS' or  upper(n.nationality_name)='OTHER' then cfm.other_nationality      else  n.nationality_name end as nationality_name, \r\n" + 
						"	   case when upper(ev.label)='OTHERS' or  upper(ev.label)='OTHER' then cfm.other_spouse_ser      else  ev.label end as spouse_service, \r\n" + 
						"	  cfm.spouse_personal_no \r\n" + 
						"	FROM tb_psg_census_family_married_jco cfm\r\n" + 
						"	LEFT JOIN tb_psg_mstr_nationality n ON cfm.nationality = n.nationality_id\r\n" + 
						"	LEFT JOIN tb_psg_mstr_marital_status ms ON cfm.marital_status = ms.marital_id\r\n" + 
						" LEFT JOIN t_domain_value ev ON cfm.spouse_service::text = ev.codevalue and ev.domainid='EX_SERVICEMAN' \r\n" + 
						"	WHERE (cfm.status = 1 OR cfm.status = 2) AND  cfm.jco_id = ? ORDER BY cfm.id DESC" ;
				
			
						
	
					stmt=conn.prepareStatement(q);
					stmt.setInt(1,maritalstatus_jco_id);
					
					
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("authority"));//0
						list.add(rs.getString("date_of_authority"));//1
						list.add(rs.getString("marital_name"));//2
						list.add(rs.getString("maiden_name"));//3
						list.add(rs.getString("date_of_birth"));//4
						list.add(rs.getString("place_of_birth"));//5
						list.add(rs.getString("nationality_name"));//6
						list.add(rs.getString("adhar_number"));//7
						list.add(rs.getString("pan_card"));		//8
						list.add(rs.getString("spouse_service"));//9
						list.add(rs.getString("spouse_personal_no"));//10				
						list.add(rs.getString("marriage_date"));//11
						list.add(rs.getString("separated_from_dt"));//12
						list.add(rs.getString("separated_to_dt"));//12
						list.add(rs.getString("divorce_date"));//12
						
						list.add(rs.getString("created_by"));//13
						list.add(rs.getString("created_date"));//14
						list.add(rs.getString("modified_by"));//15
						list.add(rs.getString("modified_date"));//16
						
						/*String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Bank Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Bank Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
						f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			
						list.add(f);
						list.add(f1);*/
						
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
      }
	
	@Override
	public List<Map<String, Object>> getSpouseQualificationJco(int jco_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			
			 
		q = "select distinct fm.maiden_name,qua.id,dom.label as type,\r\n" + 
				"case when upper(ep.examination_passed)='OTHERS' or  upper(ep.examination_passed)='OTHER' then qua.exam_other\r\n" + 
				"else  ep.examination_passed end as exp_name,\r\n" + 
				"case when upper(deg.degree)='OTHERS' or  upper(deg.degree)='OTHER' then qua.degree_other\r\n" + 
				"else  deg.degree end as d_name,\r\n" + 
				"case when upper(spe.specialization)='OTHERS' or  upper(spe.specialization)='OTHER' then qua.specialization_other\r\n" + 
				"else  spe.specialization end as spce_name,\r\n" + 
				"case when upper(dc.div)='OTHERS' or  upper(dc.div)='OTHER' then qua.class_other\r\n" + 
				"else  dc.div end as div_class,					\r\n" + 
				"qua.passing_year,\r\n" + 
				"qua.modified_by,\r\n" + 
				"qua.created_by,\r\n" + 
				"ltrim(TO_CHAR(qua.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
				"ltrim(TO_CHAR(qua.modified_date ,'DD-MON-YYYY'),'0') as modified_date,\r\n" + 
				"qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification where id=qua.id), ','))   qsub\r\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject\r\n" + 
				"from tb_psg_census_spouse_qualification_jco qua\r\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\r\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\r\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \r\n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\r\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE' \r\n" + 
				"left join tb_psg_census_family_married_jco fm on qua.spouse_id=fm.id\r\n" + 
				"where qua.jco_id=? and qua.status in (2,1) order by qua.id " ;
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
