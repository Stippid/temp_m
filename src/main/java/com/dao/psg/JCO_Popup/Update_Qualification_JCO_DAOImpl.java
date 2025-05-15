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

public class Update_Qualification_JCO_DAOImpl implements Update_Qualification_JCO_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> Update_Qualification_JCO(int jco_id)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select qua.id,qua.authority, qua.date_of_authority,dom.label as type,ep.examination_passed as exp_name,deg.degree as d_name,spe.specialization as spce_name,qua.passing_year,\r\n" + 
							"dc.div as div_class,qua.institute,  array_to_string(ARRAY(select sub.description from unnest(string_to_array((select subject from tb_psg_census_qualification where id=qua.id), ','))   qsub \r\n" + 
							"inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),',') as subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,\r\n" + 
							"qua.modify_by,qua.modify_on,qua.created_by,qua.created_on,qua.jco_id,qua.status															 \r\n" + 
							"from tb_psg_census_qualification_jco qua\r\n" + 
							"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass \r\n" + 
							"inner join tb_psg_mstr_degree deg on deg.id=qua.degree \r\n" + 
							"inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization \r\n" + 
							"inner join tb_psg_mstr_div_grade dc on cast(qua.div_class as   integer)=dc.id\r\n" + 
							"inner join t_domain_value dom on dom.codevalue=cast(qua.type as char) and dom.domainid='QUALIFICATION_TYPE' \r\n" + 
							"inner join tb_psg_census_jco_or_p pc on pc.id= qua.jco_id and pc.status in ('1','5') \r\n" + 
							"WHERE   jco_id =? and qua.status in (1,2)  order by id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
				
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
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
			
			return list;
      }

}
