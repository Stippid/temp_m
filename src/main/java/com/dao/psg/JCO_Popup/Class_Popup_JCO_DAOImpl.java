package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Class_Popup_JCO_DAOImpl implements Class_Popup_JCO_DAO{

private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}


public ArrayList<ArrayList<String>> Class_PopUp_JCO(int class_jco_id)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
				
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
						
				q="SELECT ne.id,tm.class_pay,ne.cla_authority,ltrim(TO_CHAR(ne.cla_date_authority,'DD-MON-YYYY'),'0') as date_of_authority,ltrim(TO_CHAR(ne.date_of_class,'DD-MON-YYYY'),'0') as date_of_class, ne.modified_by,ltrim(TO_CHAR(ne.modified_date,'DD-MON-YYYY'),'0') as modified_date,ne.created_by,ltrim(TO_CHAR(ne.created_date,'DD-MON-YYYY'),'0') as created_date \r\n" + 
						"FROM  tb_psg_census_jco_or_p p inner join \r\n" + 
						"tb_psg_census_change_in_class_pay_jco ne on p.id = ne.jco_id and p.status in ('1','5') \r\n" + 
						"inner join tb_psg_mstr_class_pay_jco tm on ne.cla_class=tm.id\r\n" + 
						"where   jco_id=? and ne.status in (1,2) order by ne.id desc";
			
			
			stmt=conn.prepareStatement(q);
			
			
			stmt.setInt(1,class_jco_id);
			
			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("cla_authority"));//1
				list.add(rs.getString("date_of_authority"));//2
				list.add(rs.getString("class_pay"));//3
				list.add(rs.getString("date_of_class"));//4
				list.add(rs.getString("created_by"));//5
				list.add(rs.getString("created_date"));//6
				list.add(rs.getString("modified_by"));//7
				list.add(rs.getString("modified_date"));//8

				
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
