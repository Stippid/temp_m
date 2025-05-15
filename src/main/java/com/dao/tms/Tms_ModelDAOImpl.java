package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class Tms_ModelDAOImpl implements Tms_ModelDAO {
	
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public String gettmsmodelid(String m_s,String m_k){
		String whr="";
		Connection conn = null;
		try {	
			
			conn = dataSource.getConnection();
	 		String query = null;
			query="select min(ser) from generate_series(1,999) ser left join( select CAST (model_id AS integer)  as rightval  from TB_TMS_MODEL_MASTER where make_id =? and mct_main_id =? )as seq on ser =  seq.rightval where rightval is null";	
			 PreparedStatement stmt=conn.prepareStatement(query);
			 stmt.setString(1, m_k);
			 stmt.setString(2, m_s);
			 ResultSet rs = stmt.executeQuery();
     	        while(rs.next()){
     	           whr=rs.getString("min");             	
     	        }
     		    rs.close();
     	    	stmt.close();
     			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
        return whr;
	}
}