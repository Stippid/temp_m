package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Pay_Group_Popup_JCO_DAOImpl implements Pay_Group_Popup_JCO_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public ArrayList<ArrayList<String>> Pay_Group_PopUp_JCO(int pay_group_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
							
					q="select pg.id,pg.jco_id,pg.gp_authority,ltrim(TO_CHAR(pg.gp_date_authority,'DD-MON-YYYY'),'0') as date_of_authority,gp.pay_group,ltrim(TO_CHAR(pg.date_of_group,'DD-MON-YYYY'),'0') as date_of_group,pg.created_by,ltrim(TO_CHAR(pg.created_date,'DD-MON-YYYY'),'0') as created_date,pg.modified_by,ltrim(TO_CHAR(pg.modified_date,'DD-MON-YYYY'),'0') as modified_date\r\n" + 
							"from  tb_psg_census_jco_or_p p inner join \r\n" + 
							"tb_psg_census_change_in_pay_group_jco pg on p.id = pg.jco_id and p.status in ('1','5') \r\n" + 
							"inner join tb_psg_mstr_pay_group_jco gp on pg.gp_group=gp.id \r\n" + 
							"where   jco_id=? and pg.status in (1,2) order by pg.id desc";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setInt(1,pay_group_jco_id);
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("gp_authority"));//0
					list.add(rs.getString("date_of_authority"));//1
					list.add(rs.getString("pay_group"));//2
					list.add(rs.getString("date_of_group"));//3
					list.add(rs.getString("created_by"));//4
					list.add(rs.getString("created_date"));//5
					list.add(rs.getString("modified_by"));//6
					list.add(rs.getString("modified_date"));//7

					
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
