package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Change_In_Medicle_History_DAOImpl implements Change_In_Medicle_History_DAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> change_in_medicle(BigInteger comm_id,int cen_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select \n"
					+ "				med.authority,ltrim(TO_CHAR(med.date_of_authority,'DD-MON-YYYY'),'0') as date_of_authority,med.date_of_authority,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='S') as s_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='S') as s_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='H') as h_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='H') as h_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='A') as a_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='A') as a_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='P') as p_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='P') as p_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='E') as e_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='E') as e_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='C_C') as c_c_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_C') as c_c_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='C_O') as c_o_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_O') as c_o_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='C_P') as c_p_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_P') as c_p_value,\n"
					+ "STRING_AGG(med.id::text,',') FILTER (where med.shape='C_E') as c_e_id ,STRING_AGG(med.shape_value ,',') FILTER (where med.shape='C_E') as c_e_value,\n"
					+ "ltrim(TO_CHAR(med.modify_on,'DD-MON-YYYY'),'0') as modify_on,med.modify_on,med.modify_by,med.created_by,ltrim(TO_CHAR(med.created_on,'DD-MON-YYYY'),'0') as created_on,med.created_on,"
					+ "STRING_AGG(ltrim(TO_CHAR(med.from_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='S') as s_fd,STRING_AGG(ltrim(TO_CHAR(med.to_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='S') as s_td,\n" + 
					"STRING_AGG(ltrim(TO_CHAR(med.from_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='H') as h_fd,STRING_AGG(ltrim(TO_CHAR(med.to_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='H') as h_td,\n" + 
					"STRING_AGG(ltrim(TO_CHAR(med.from_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='A') as a_fd,STRING_AGG(ltrim(TO_CHAR(med.to_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='A') as a_td,\n" + 
					"STRING_AGG(ltrim(TO_CHAR(med.from_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='P') as p_fd,STRING_AGG(ltrim(TO_CHAR(med.to_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='P') as p_td,\n" + 
					"STRING_AGG(ltrim(TO_CHAR(med.from_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='E') as e_fd,STRING_AGG(ltrim(TO_CHAR(med.to_date,'DD-MON-YYYY'),'0')::text,',') FILTER (where med.shape='E') as e_td\n" + 
					"\n"
					+ "from tb_psg_medical_category med \n"
					+ "where med.status in (1,2)  and cast(med.comm_id as character varying)=? \n"
					+ "group by med.modify_on,med.modify_by,med.authority,med.date_of_authority,med.created_by,med.created_on ORDER BY med.created_on";
			  
			stmt=conn.prepareStatement(q);
			System.err.println("query change medical history: \n"+ stmt);
			//stmt.setInt(1,cen_id);
			stmt.setString(1,String.valueOf(comm_id));
			
			ResultSet rs = stmt.executeQuery();     
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("authority"));//0
				list.add(rs.getString("date_of_authority"));//1
				list.add(rs.getString("s_value"));//2
				list.add(rs.getString("h_value"));//3
				list.add(rs.getString("a_value"));//4
				list.add(rs.getString("p_value"));//5
				list.add(rs.getString("e_value"));//6
				list.add(rs.getString("c_c_value"));//7
				list.add(rs.getString("c_o_value"));//8
				list.add(rs.getString("c_p_value"));//9
				list.add(rs.getString("c_e_value"));//10
				list.add(rs.getString("created_by"));//11
				list.add(rs.getString("created_on"));//12
				list.add(rs.getString("modify_by"));//13
				list.add(rs.getString("modify_on"));//14
				list.add(rs.getString("s_fd"));//15
				list.add(rs.getString("s_td"));//16
				list.add(rs.getString("h_fd"));//17
				list.add(rs.getString("h_td"));//18
				list.add(rs.getString("a_fd"));//19
				list.add(rs.getString("a_td"));//20
				list.add(rs.getString("p_fd"));//21
				list.add(rs.getString("p_td"));//22
				list.add(rs.getString("e_fd"));//23
				list.add(rs.getString("e_td"));//24
				
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
