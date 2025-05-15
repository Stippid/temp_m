package com.dao.psg.popup_history;

import java.util.ArrayList;
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

public class Change_Marital_Status_DAOImpl implements Change_Marital_Status_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@Override
	public ArrayList<ArrayList<String>> change_in_marital_status_dtl(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			/*q="SELECT \n" + 
					"	  distinct cfm.id, \n" + 
					"	  cfm.cen_id, \n" + 
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
					"	  cfm.authority, \n" + 
					"	  ltrim(TO_CHAR(  cfm.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 					
					"	  cfm.comm_id,\n" + 
					"	  ms.marital_name, \n" + 
					"	  PGP_SYM_DECRYPT(cfm.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number, \n" + 
					"	  cfm.place_of_birth, \n" + 
					"	 PGP_SYM_DECRYPT(cfm.pan_card ::bytea,current_setting('miso.version'))  as pan_card, \n" + 
					"	   case when upper(n.nationality_name)='OTHERS' or  upper(n.nationality_name)='OTHER' then cfm.other_nationality"
					+ "      else  n.nationality_name end as nationality_name, \n" + 
					"	   case when upper(ev.label)='OTHERS' or  upper(ev.label)='OTHER' then cfm.other_spouse_ser"
					+ "      else  ev.label end as spouse_service, \n" + 
					"	  cfm.spouse_personal_no \n" + 
					"	FROM tb_psg_census_family_married cfm\n" + 
					"	LEFT JOIN tb_psg_mstr_nationality n ON cfm.nationality = n.nationality_id\n" + 
					"	LEFT JOIN tb_psg_mstr_marital_status ms ON cfm.marital_status = ms.marital_id\n" + 				
					" LEFT JOIN t_domain_value ev ON cfm.spouse_service::text = ev.codevalue and ev.domainid='EX_SERVICEMAN' \n"+
					"	WHERE (cfm.status = 1 OR cfm.status = 2) AND  cast(cfm.comm_id as character varying)=? AND cfm.cen_id = ? ORDER BY cfm.id " ;
		*/		
			
			q="SELECT \n" + 
					"	  distinct cfm.id, \n" + 
					"	  cfm.cen_id, \n" + 
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
					"	  cfm.authority, \n" + 
					"	  ltrim(TO_CHAR(  cfm.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\n" + 
					"	  cfm.comm_id,\n" + 
					"	  ms.marital_name, \n" + 
					"	  PGP_SYM_DECRYPT(cfm.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number, \n" + 
					"	  cfm.place_of_birth, \n" + 
					"	 PGP_SYM_DECRYPT(cfm.pan_card ::bytea,current_setting('miso.version'))  as pan_card, \n" + 
					"	   case when upper(n.nationality_name)='OTHERS' or  upper(n.nationality_name)='OTHER' then cfm.other_nationality      else  n.nationality_name end as nationality_name, \n" + 
					"	   case when  upper(t1.ex_servicemen)='OTHERS' or  upper(t1.ex_servicemen)='OTHER' then cfm.other_spouse_ser\n" + 
					" else\n" + 
					"COALESCE(t1.ex_servicemen::text,'') End as spouse_service, \n" + 
					"	  cfm.spouse_personal_no \n" + 
					"	FROM tb_psg_census_family_married cfm\n" + 
					"	LEFT JOIN tb_psg_mstr_nationality n ON cfm.nationality = n.nationality_id\n" + 
					"	LEFT JOIN tb_psg_mstr_marital_status ms ON cfm.marital_status = ms.marital_id\n" + 
					" left join tb_psg_mstr_exservicemen t1 on t1.id = cfm.spouse_service  \n"+
					"	WHERE (cfm.status = 1 OR cfm.status = 2) AND  cast(cfm.comm_id as character varying)=? AND cfm.cen_id = ? ORDER BY cfm.id " ;
	
			
			
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				stmt.setInt(2,census_id);
				 				
				
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
					list.add(rs.getString("separated_form_dt"));//12
					list.add(rs.getString("separated_to_dt"));//12
					list.add(rs.getString("divorce_date"));//12
					
					list.add(rs.getString("created_by"));//13
					list.add(rs.getString("created_date"));//14
					list.add(rs.getString("modified_by"));//15
					list.add(rs.getString("modified_date"));//16
					
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
	public List<Map<String, Object>> getSpouseQualification(BigInteger comm_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			//String flag = "Y";

			
			 
		q = "select distinct fm.maiden_name,qua.id,dom.label as type,\n" + 
				"case when upper(ep.examination_passed)='OTHERS' or  upper(ep.examination_passed)='OTHER' then qua.exam_other\n" + 
				"else  ep.examination_passed end as exp_name,\n" + 
				"case when upper(deg.degree)='OTHERS' or  upper(deg.degree)='OTHER' then qua.degree_other\n" + 
				"else  deg.degree end as d_name,\n" + 
				"case when upper(spe.specialization)='OTHERS' or  upper(spe.specialization)='OTHER' then qua.specialization_other\n" + 
				"else  spe.specialization end as spce_name,\n" + 
				"case when upper(dc.div)='OTHERS' or  upper(dc.div)='OTHER' then qua.class_other\n" + 
				"else  dc.div end as div_class,					\n" + 
				"qua.passing_year,\n" + 
				"qua.modified_by,\n" + 
				"qua.created_by,\n" + 
				"ltrim(TO_CHAR(qua.created_date ,'DD-MON-YYYY'),'0') as created_date,\n" + 
				"ltrim(TO_CHAR(qua.modified_date ,'DD-MON-YYYY'),'0') as modified_date,\n" + 
				"qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification where id=qua.id), ','))   qsub\n" + 
				"inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject\n" + 
				"from tb_psg_census_spouse_qualification qua\n" + 
				"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
				"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
				"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \n" + 
				"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\n" + 
				"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE' \n" + 
				"left join tb_psg_census_family_married fm on qua.spouse_id=fm.id\n" + 
				"where cast(qua.comm_id as character varying)=? and qua.status in (2,1) order by qua.id " ;
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
