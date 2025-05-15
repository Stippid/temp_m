package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Pramotional_Exam_Popup_JCO_DAOImpl implements Pramotional_Exam_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> Update_Pramotional_Exam_JCO(int jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select p.authority,\r\n" + 
							"ltrim(TO_CHAR(p.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
							"p.date_of_passing,\r\n" + 
							"p.exam_other,\r\n" + 
							"t.label,\r\n" + 
							"p.created_by,\r\n" + 
							"ltrim(TO_CHAR(p.created_on,'DD-MON-YYYY'),'0') as created_on,\r\n" + 
							"p.modify_by,\r\n" + 
							"ltrim(TO_CHAR(p.modify_on,'DD-MON-YYYY'),'0') as modify_on \r\n" + 
							"from tb_psg_census_promo_exam_jco p\r\n" + 
							"left join t_domain_value t on t.codevalue=p.exam and t.domainid='PROMOTIONAL_EXAM' \r\n" + 
							"inner join tb_psg_census_jco_or_p pc on pc.id=p.jco_id and pc.status in ('1','5')  \r\n" + 
							"WHERE   jco_id =? and p.status in (1,2) order by p.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,jco_id);
				
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("label"));//3
					list.add(rs.getString("exam_other"));//4
					list.add(rs.getString("date_of_passing"));//5
					list.add(rs.getString("created_by"));//6
					list.add(rs.getString("created_on"));//7
					list.add(rs.getString("modify_by"));//8
					list.add(rs.getString("modify_on"));//9
					
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
