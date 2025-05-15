package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Army_Course_Popup_JCO_DAOImpl implements Army_Course_Popup_JCO_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> Army_Course_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select  a.authority,a.course_abbreviation,\r\n" + 
							"ltrim(TO_CHAR(a.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
							"a.course_name,\r\n" + 
							"case when ins.course_institute='OTHERS' then a.course_institute_other\r\n" + 
							"else ins.course_institute end as institute,\r\n" + 
							"case when d.div='OTHERS' then a.ar_course_div_ot\r\n" + 
							"else d.div end as div_grade,\r\n" + 
							"case when t.label='OTHERS' then a.course_type_ot\r\n" + 
							"else t.label end as course_type,\r\n" + 
							"ltrim(TO_CHAR(a.start_date,'DD-MON-YYYY'),'0') as start_date,\r\n" + 
							"ltrim(TO_CHAR(a.date_of_completion,'DD-MON-YYYY'),'0') as date_of_completion, \r\n" + 
							"a.created_by,\r\n" + 
							"ltrim(TO_CHAR(a.created_on,'DD-MON-YYYY'),'0') as created_on,\r\n" + 
							"a.modify_by,\r\n" + 
							"ltrim(TO_CHAR(a.modify_on,'DD-MON-YYYY'),'0') as modify_on\r\n" + 
							"from tb_psg_census_army_course_jco a\r\n" + 
							"inner join tb_psg_mstr_div_grade d on d.id::text = a.div_grade\r\n" + 
							"inner join t_domain_value t on t.codevalue=a.course_type and t.domainid='COURSE_TYPE' \r\n" + 
							"inner join tb_psg_mstr_course_institute ins on ins.id=a.course_institute and ins.status='active' \r\n" + 
							"inner join tb_psg_census_jco_or_p pc on pc.id=a.jco_id and pc.status in ('1','5')\r\n" + 
							"WHERE   jco_id =?  and a.status in ('1','2') order by a.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("course_name"));//3
					list.add(rs.getString("course_abbreviation"));//3
					list.add(rs.getString("institute"));//3
					list.add(rs.getString("div_grade"));//4
					list.add(rs.getString("course_type"));//5
					list.add(rs.getString("start_date"));//6
					list.add(rs.getString("date_of_completion"));//7
					list.add(rs.getString("created_by"));//8
					list.add(rs.getString("created_on"));//9
					list.add(rs.getString("modify_by"));//10
					list.add(rs.getString("modify_on"));//11
					
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


}
