package com.dao.Reports;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class TMS_OLAP_CRONTABDAOImpl implements TMS_OLAP_CRONTABDAO{
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public String set_OLAP_TMS_BVEH_UE_UH_DATA(List<Map<String, Object>> list,String week) {
        Connection conn = null;
        try {
			conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        conn.setAutoCommit(false);
	        for(int i=0;i<list.size();i++) {
	        	String q = "INSERT INTO olap_tms_bveh_ue_uh (comd,corps,div,bde,unit_name,prf_code,group_name,mct_main_id,mct_nomen,ue,against_ue,over_ue,loan,sec_store,acsfp,fresh_release,total_uh,gift,surplus,defi,week,mnh_yr,sus_no,created_date) "
	        			+ " VALUES ('"+list.get(i).get("comd")+"', '"+list.get(i).get("corps")+"', '"+list.get(i).get("div")+"','"+list.get(i).get("bde")+"', '"+list.get(i).get("unit_name")+"',"
	        			+ "'"+list.get(i).get("prf_code")+"', '"+list.get(i).get("group_name")+"','"+list.get(i).get("mct_main_id")+"','"+list.get(i).get("mct_nomen")+"','"+list.get(i).get("ue")+"',"
	        			+ "'"+list.get(i).get("against_ue")+"','"+list.get(i).get("over_ue")+"', '"+list.get(i).get("loan")+"','"+list.get(i).get("sec_store")+"','"+list.get(i).get("acsfp")+"','"+list.get(i).get("fresh_release")+"',"
	        			+ "'"+list.get(i).get("total_uh")+"', '"+list.get(i).get("gift")+"','"+list.get(i).get("surplus")+"','"+list.get(i).get("defi")+"','"+week+"','"+list.get(i).get("mnt_yr")+"','"+list.get(i).get("sus_no")+"','"+new Date()+"')";
	        	stmt.addBatch(q);
	        }
	        stmt.executeBatch();
	        conn.commit();
	        stmt.close();
	        return "OLAP_TMS_BVEH_UE_UH Data Generated on "+ new Date();
        }catch (SQLException e){
        	return "OLAP_TMS_BVEH_UE_UH Data not Generated on "+ new Date() + e.getMessage();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch (SQLException e) {}
			}
		}
    }
}
