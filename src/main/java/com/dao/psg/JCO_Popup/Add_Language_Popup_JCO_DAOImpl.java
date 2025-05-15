package com.dao.psg.JCO_Popup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Add_Language_Popup_JCO_DAOImpl implements Add_Language_Popup_JCO_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<ArrayList<String>> Add_Foreign_Language_JCO(int add_foriegn_language_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			    q="\r\n" + 
			    		"select la.id,la.authority,ltrim(TO_CHAR(la.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority, la.f_exam_pass,\r\n" + 
			    		" case when upper(lp.foreign_language_name)='OTHERS' or  upper(lp.foreign_language_name)='OTHER' then  COALESCE(la.f_other_language,'')\r\n" + 
			    		" else COALESCE(lp.foreign_language_name,'') end as language,\r\n" + 
			    		"case when upper(ls.name)='OTHERS' or  upper(ls.name)='OTHER' then  COALESCE(la.f_other_lang_std,'')\r\n" + 
			    		" else COALESCE(ls.name,'') end as lang_std,\r\n" + 
			    		"case when upper(lo.name)='OTHERS' or  upper(lo.name)='OTHER' then  COALESCE(la.f_other_prof,'')\r\n" + 
			    		"else COALESCE(lo.name,'') end as lang_proff \r\n" + 
			    		",la.modify_by,ltrim(TO_CHAR(la.modify_on,'DD-MON-YYYY'),'0') as modify_on,la.created_by,ltrim(TO_CHAR(la.created_on,'DD-MON-YYYY'),'0') as created_on\r\n" + 
			    		"from tb_psg_census_language_jco la\r\n" + 
			    		"inner join tb_psg_lang_std ls on ls.id=la.lang_std \r\n" + 
			    		"inner join tb_psg_lang_proof lo on lo.id=la.f_lang_prof \r\n" + 
			    		"inner join tb_psg_mstr_foreign_language lp on lp.id=la.foreign_language\r\n" + 
			    		"inner join tb_psg_census_jco_or_p pc on pc.id=la.jco_id and pc.status in ('1','5') \r\n" + 
			    		"WHERE jco_id =? and la.status in (1,2) order by id desc  ";
			   
			    
			stmt=conn.prepareStatement(q);
			stmt.setInt(1,add_foriegn_language_jco_id);
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("authority"));//0
				list.add(rs.getString("date_of_authority"));//1
				list.add(rs.getString("language"));//2
				list.add(rs.getString("lang_std"));//3
				list.add(rs.getString("lang_proff"));//4
				list.add(rs.getString("f_exam_pass"));//5
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
	public ArrayList<ArrayList<String>> Add_Language_JCO(int add_language_jco_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			    q="select la.id, la.authority, ltrim(TO_CHAR(la.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
			    		"case when lp.ind_language='OTHERS' then la.other_language else lp.ind_language end as language,\r\n" + 
			    		"case when ls.name ='OTHERS' then la.other_lang_std else ls.name end as lang_std,\r\n" + 
			    		"la.other_language,la.other_lang_std,\r\n" + 
			    		"la.modify_by,ltrim(TO_CHAR(la.modify_on,'DD-MON-YYYY'),'0') as modify_on,la.created_by,ltrim(TO_CHAR(la.created_on,'DD-MON-YYYY'),'0') as created_on\r\n" + 
			    		"from tb_psg_census_language_jco la \r\n" + 
			    		"inner join tb_psg_lang_std ls on ls.id=la.lang_std\r\n" + 
			    		"inner join tb_psg_mstr_indian_language lp on lp.id=la.language \r\n" + 
			    		"inner join tb_psg_census_jco_or_p pc on pc.id=la.jco_id and pc.status in ('1','5') \r\n" + 
			    		"WHERE   jco_id =? and la.status in (1,2) order by id desc";
			   
			    
			stmt=conn.prepareStatement(q);
		
			stmt.setInt(1,add_language_jco_id);
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("authority"));//0
				list.add(rs.getString("date_of_authority"));//1
				list.add(rs.getString("language"));//2
				list.add(rs.getString("lang_std"));//3
				list.add(rs.getString("created_by"));//4
				list.add(rs.getString("created_on"));//5
				list.add(rs.getString("modify_by"));//6
				list.add(rs.getString("modify_on"));//7
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
