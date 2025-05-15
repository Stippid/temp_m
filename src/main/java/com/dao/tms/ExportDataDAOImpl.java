package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ExportDataDAOImpl implements ExportDataDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
    public ArrayList<ArrayList<String>> getAttributeFromMctMainMaster(String table_name) {
		ArrayList<ArrayList<String>> alist =  new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{	 
			conn = dataSource.getConnection();
			String sql= table_name;
			PreparedStatement stmt=conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			ArrayList<String> list1 = new ArrayList<String>();
	
			for (int j = 1; j < columnCount+1; j++){
				if(j==1 & table_name.contains("tb_tms_upload_document_mvcr")) {
					list1.add("ACTION");
				}
				if(j==1 & table_name.contains("tb_tms_upload_document_mcr")) {
					list1.add("ACTION");
				}	
				list1.add(rsmd.getColumnName(j));
			}
			int flag = 0;
			
			while(rs.next()){
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount+1; i++){
					if(i==1 & table_name.contains("tb_tms_upload_document_mvcr")) {
						list.add("<a hreF='#' onclick='getDownloadImageMVCR1("+rs.getString(1)+")' class='btn btn-primary btn-sm'>Download</a>");
					}
					if(i==1 & table_name.contains("tb_tms_upload_document_mcr")) {
						list.add("<a hreF='#' onclick='getDownloadImageMCR1("+rs.getString(1)+")' class='btn btn-primary btn-sm'>Download</a>");
					}
					String name = rs.getString(i);
					list.add(name);
				}   
				if(flag == 0) {
					alist.add(list1);
				}
				flag++;
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
				} catch (SQLException e) {
				}
			}
		}
	return alist;
	}	
	
	
}