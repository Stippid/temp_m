package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Add_Language_History_DAOImpl implements Add_Language_History_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> add_forigen_languages(BigInteger comm_id,int cen_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			q = "select la.id,la.authority, TO_CHAR(la.date_of_authority,'DD-MON-YYYY') as date_of_authority, la.f_exam_pass,\n" + 
					"case when lp.foreign_language_name='OTHERS' then la.f_other_language else lp.foreign_language_name end as language,\n" + 
					"case when ls.name ='OTHERS' then la.f_other_lang_std else ls.name end as lang_std,\n" + 
					"case when lo.name ='OTHERS' then la.f_other_prof else lo.name end as lang_proff,\n" + 
					"la.modify_by,ltrim(TO_CHAR(la.modify_on,'DD-MON-YYYY'),'0') as modify_on,la.created_by,ltrim(TO_CHAR(la.created_on,'DD-MON-YYYY'),'0') as created_on\n" + 
					"from tb_psg_census_language la\n" + 
					"				inner join tb_psg_lang_std ls on ls.id=la.lang_std \n" + 
					"				inner join tb_psg_lang_proof lo on lo.id=la.f_lang_prof\n" + 
					"				inner join tb_psg_mstr_foreign_language lp on lp.id=la.foreign_language\n" + 
					"				where la.language =0  and la.status in (1,2) and la.cen_id=? and cast(la.comm_id as character varying)=? order by la.id";			   
			    
			stmt=conn.prepareStatement(q);
			stmt.setInt(1,cen_id);
			stmt.setString(2,String.valueOf(comm_id));
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("authority"));//0
				list.add(rs.getString("date_of_authority"));//1
				list.add(rs.getString("language"));//2
				list.add(rs.getString("lang_std"));//3
				list.add(rs.getString("f_exam_pass"));//4
				list.add(rs.getString("lang_proff"));//5
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
	public ArrayList<ArrayList<String>> add_languages(BigInteger comm_id,int cen_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			    q="select la.id, la.authority, TO_CHAR(la.date_of_authority,'DD-MON-YYYY') as date_of_authority,\n" + 
			    		"case when lp.ind_language='OTHERS' then la.other_language else lp.ind_language end as language,\n" + 
			    		"case when ls.name ='OTHERS' then la.other_lang_std else ls.name end as lang_std,\n" + 
			    		"la.other_language,la.other_lang_std,"+
			    		"la.modify_by,ltrim(TO_CHAR(la.modify_on,'DD-MON-YYYY'),'0') as modify_on,la.created_by,ltrim(TO_CHAR(la.created_on,'DD-MON-YYYY'),'0') as created_on\n" +
			    		"from tb_psg_census_language la\n" + 
			    		"inner join tb_psg_lang_std ls on ls.id=la.lang_std\n" + 
			    		"inner join tb_psg_mstr_indian_language lp on lp.id=la.language\n" + 
			    		"where  la.language!=0  and la.status in (1,2)	and la.cen_id=? and cast(la.comm_id as character varying)=? order by la.id";
			   
			    
			stmt=conn.prepareStatement(q);
		
			stmt.setInt(1,cen_id);
			stmt.setString(2,String.valueOf(comm_id));
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
