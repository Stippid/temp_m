package com.dao.orbat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class Loc_and_NrsDAOImp implements Loc_and_NrsDAO{
	
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ArrayList<ArrayList<String>> getLoc_And_NrsList(String code_value,String code,String status_record,String roleType) {
    	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		
    		String qry=" a.code_type='Location'";
			if(code_value != "") {
				qry += "and Upper(a.code_value) LIKE ? ";
			}
			if(code != "") {
				qry += " and Upper(a.code) LIKE ? ";
			}
			if(status_record != "") {
				qry += " and a.status_record = ? ";
			}
			String sql="";
			if(qry == "") {
				sql= "select distinct a.code_value as code_value,a.code as code,a.id,(select b.code_value from Tb_Miso_Orbat_Code b where b.code= a.nrs_code and b.code_type='Location')as nrs_code,type_loc,mod_desc from Tb_Miso_Orbat_Code a where a.code_type='Location'";
			}else {
				sql= "select distinct a.code_value,a.code,a.id,(select b.code_value from Tb_Miso_Orbat_Code b where b.code= a.nrs_code and b.code_type='Location')as nrs_code,type_loc,mod_desc from Tb_Miso_Orbat_Code a where "+ qry +" Order by a.code,a.code_value";
			}
			
			PreparedStatement stmt=conn.prepareStatement(sql);
			int j=0;
			if(code_value != "") {
				j += 1;
				stmt.setString(j, "%"+ code_value.toUpperCase() +"%");
			}
			if(code != "") {
				j += 1;
				stmt.setString(j, "%"+ code.toUpperCase() +"%");
			}
			if(status_record != "") {
				j += 1;
				stmt.setString(j, status_record);
			}
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			
    			list.add(rs1.getString("code_value"));
    			list.add(rs1.getString("code"));
    			list.add(rs1.getString("id"));
    			list.add(rs1.getString("nrs_code"));
    			list.add(rs1.getString("type_loc"));
    			list.add(rs1.getString("mod_desc"));
    			
				String Approved = "onclick=\"  if (confirm('Are you sure you want to approve this Loc and NRS ?') ){Approved("+rs1.getString("id")+")}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
				
				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Loc and NRS ?') ){Reject("+rs1.getString("id")+")}else{ return false;}\"";
				String rejectButton ="<i class='action_icons action_reject' "+Reject+" title='Reject Data'></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Loc and NRS ?') ){Delete1("+rs1.getString("id")+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
				
				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Loc and NRS ?') ){Update("+rs1.getString("id")+")}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
				
				String f = "";
				if(status_record.equals("0")){
					if(roleType.equals("ALL")) {
						f += appButton;
						f += rejectButton;
						f += deleteButton;
						f += updateButton;
					}
					if(roleType.equals("APP")) {
						f += appButton;
						f += rejectButton;
					}
					if(roleType.equals("DEO")) {
						f += deleteButton;
						f += updateButton;
					}
				}else if(status_record.equals("1")){
					f += "Approved";
					//f += updateButton;
				}else if(status_record.equals("2")){
					if(roleType.equals("DEO") || roleType.equals("ALL")) {
						f += deleteButton;
						f += updateButton;
					}
				}
				list.add(f);
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
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