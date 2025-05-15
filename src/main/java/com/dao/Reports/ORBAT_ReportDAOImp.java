package com.dao.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class ORBAT_ReportDAOImp implements ORBAT_ReportDAO{

	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }	

    // 1. Monthly Report
    
    public  ArrayList<ArrayList<String>> search_conver_restruc_reorga_redes_actionlist(String report_type,String ddlYears,String month) {
    	
    	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    	
    	Connection conn = null;
    	try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt =null;
    		String sql1="";
    		if(report_type.equals("crrr"))
    		{
    			sql1 = "select \r\n" + 
    					"	u12.form_code_control,\r\n" + 
    					"	u.id,\r\n" + 
    					"	u.status_sus_no,\r\n" + 
    					"	u.sus_no_for_comb_disint,\r\n" + 
    					"	u.sus_no as from_sus,\r\n" + 
    					"	u.unit_name as from_unit,\r\n" + 
    					"	c.command as from_command,\r\n" + 
    					"	cp.corps as from_corps,\r\n" + 
    					"	dv.div as from_div,\r\n" + 
    					"	bd.bde as from_bde,\r\n" + 
    					"	a.arm_desc,\r\n" + 
    					"	t.label as act,\r\n" + 
    					"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n" + 
    					"	s.letter_no as auth_letter,\r\n" + 
    					"	u12.sus_no as to_sus,\r\n" + 
    					"	u12.unit_name as to_unit,\r\n" + 
    					"	c1.command as to_command,\r\n" + 
    					"	cp1.corps as to_corps,\r\n" + 
    					"	dv1.div as to_div,\r\n" + 
    					"	bd1.bde as to_bde\r\n" + 
    					"from tb_miso_orbat_unt_dtl u\r\n" + 
    					"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
    					"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('1','2','3','11')\r\n" + 
    					"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
    					"left join tb_miso_orbat_unt_dtl u12 on u.sus_no_for_comb_disint = u12.sus_no and  u12.status_sus_no in ('Inactive')\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as command,\r\n" + 
    					"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as corps,\r\n" + 
    					"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as div,\r\n" + 
    					"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as bde,\r\n" + 
    					"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as command,\r\n" + 
    					"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c1 on substr(u12.form_code_control,1,1)=c1.command_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as corps,\r\n" + 
    					"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp1 on substr(u12.form_code_control,2,2)=cp1.corps_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as div,\r\n" + 
    					"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv1 on substr(u12.form_code_control,4,3)=dv1.div_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as bde,\r\n" + 
    					"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd1 on substr(u12.form_code_control,7,4)=bd1.bde_code\r\n" + 
    					"where u.status_sus_no='Active' and (date_part('year',u.creation_on)) =cast(? as integer) and date_part('month',u.creation_on)= cast(? as integer) \r\n" + 
    					"order by act\r\n" + 
    					"";
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1,ddlYears);
				stmt.setString(2,month);
			
				ResultSet rs1 = stmt.executeQuery();
				while (rs1.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs1.getString("from_sus"));
					list.add(rs1.getString("from_unit"));
					list.add(rs1.getString("to_sus"));
					list.add(rs1.getString("to_unit"));
					list.add(rs1.getString("from_command"));
					list.add(rs1.getString("to_command"));
					list.add(rs1.getString("arm_desc"));
					list.add(rs1.getString("chkd"));
					list.add(rs1.getString("auth_letter"));
					list.add(rs1.getString("act"));
					list.add(rs1.getString("chkd"));
					alist.add(list);
				}
				rs1.close();
			}
    		else if(report_type.equals("d"))
    		{
    			sql1 = "select \r\n" + 
    					"	u.sus_no as from_sus,\r\n" + 
    					"	u.unit_name as from_unit,\r\n" + 
    					"	c.command as from_command,\r\n" + 
    					"	cp.corps as from_corps,\r\n" + 
    					"	dv.div as from_div,\r\n" + 
    					"	bd.bde as from_bde,\r\n" + 
    					"	a.arm_desc,\r\n" + 
    					"	t.label as act,\r\n" + 
    					"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n"
    					+ "s.letter_no as auth_letter\r\n" + 
    					"from tb_miso_orbat_unt_dtl u\r\n" + 
    					"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
    					"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('4')\r\n" + 
    					"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as command,\r\n" + 
    					"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as corps,\r\n" + 
    					"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as div,\r\n" + 
    					"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as bde,\r\n" + 
    					"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
    					"where u.status_sus_no='Inactive' and (date_part('year',u.creation_on)) =cast(? as integer) and date_part('month',u.creation_on)= cast(? as integer)\r\n" + 
    					"order by act\r\n" + 
    					" ";
    			
    			stmt = conn.prepareStatement(sql1);
    			stmt.setString(1,ddlYears);
				stmt.setString(2,month);
				System.out.println("disband report >>>>>>>>>>  " + stmt);
    			ResultSet rs1 = stmt.executeQuery(); 
    		    while(rs1.next()){
    		    	  ArrayList<String> list = new ArrayList<String>();
    		    	  list.add(rs1.getString("from_sus"));
    		    	  list.add(rs1.getString("from_unit"));
    		    	  list.add(rs1.getString("arm_desc"));
    		    	  list.add(rs1.getString("from_command"));
    		    	  list.add(rs1.getString("from_corps"));
    		    	  list.add(rs1.getString("from_div"));
    		    	  list.add(rs1.getString("from_bde"));
    		    	  list.add(rs1.getString("arm_desc"));
    		    	  list.add(rs1.getString("act"));
    		    	  list.add(rs1.getString("chkd"));
    		    	  list.add(rs1.getString("auth_letter"));
    		    	  alist.add(list);
    	        }
    		    rs1.close();
    		}
    		else if(report_type.equals("rr"))
    		{
    			sql1  = "select \r\n" + 
    					"	u.sus_no as from_sus,\r\n" + 
    					"	u.unit_name as from_unit,\r\n" + 
    					"	c.command as from_command,\r\n" + 
    					"	cp.corps as from_corps,\r\n" + 
    					"	dv.div as from_div,\r\n" + 
    					"	bd.bde as from_bde,\r\n" + 
    					"	a.arm_desc,\r\n" + 
    					"	t.label as act,\r\n" + 
    					"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n" + 
    					"	s.letter_no as auth_letter\r\n"	+
    					"from tb_miso_orbat_unt_dtl u\r\n" + 
    					"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
    					"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('0','5')\r\n" + 
    					"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as command,\r\n" + 
    					"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as corps,\r\n" + 
    					"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as div,\r\n" + 
    					"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
    					"left join (select\r\n" + 
    					"		u1.unit_name as bde,\r\n" + 
    					"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
    					"	from tb_miso_orbat_unt_dtl u1\r\n" + 
    					"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
    					"where u.status_sus_no='Active'and (date_part('year',u.creation_on)) =cast(? as integer) and date_part('month',u.creation_on)= cast(? as integer) \r\n" + 
    					"order by act\r\n" + 
    					" ";
    						
    			stmt = conn.prepareStatement(sql1);
    			stmt.setString(1,ddlYears);
				stmt.setString(2,month);
				
    			ResultSet rs1 = stmt.executeQuery(); 
    			while(rs1.next()){
    		    	  ArrayList<String> list = new ArrayList<String>();
    		    	  list.add(rs1.getString("from_sus"));
    		    	  list.add(rs1.getString("from_unit"));
    		    	  list.add(rs1.getString("arm_desc"));
    		    	  list.add(rs1.getString("from_command"));
    		    	  list.add(rs1.getString("from_corps"));
    		    	  list.add(rs1.getString("from_div"));
    		    	  list.add(rs1.getString("from_bde"));
    		    	  list.add(rs1.getString("arm_desc"));
    		    	  list.add(rs1.getString("act"));
    		    	  list.add(rs1.getString("chkd"));
    		    	  list.add(rs1.getString("auth_letter"));
    		    	  alist.add(list);
    	        }
    		    rs1.close();
    		}
    		else
    		{}
    		stmt.close();
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	} 
    	finally {
    		if (conn != null) {
    			try {
    				conn.close();
    			} 
    			catch (SQLException e) {
    			}
    		}
    	}
    	return alist;
    }
    
    // 1. Monthly Report End
    
    // 2. Yearly Report 
    
	public ArrayList<ArrayList<String>> disbandmentReportaction(String report_type,String ddlYears) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			if (report_type.equals("cr")) {
				sql1 = "select \r\n" + 
						"	u12.form_code_control,\r\n" + 
						"	u.id,\r\n" + 
						"	u.status_sus_no,\r\n" + 
						"	u.sus_no_for_comb_disint,\r\n" + 
						"	u.sus_no as from_sus,\r\n" + 
						"	u.unit_name as from_unit,\r\n" + 
						"	c.command as from_command,\r\n" + 
						"	cp.corps as from_corps,\r\n" + 
						"	dv.div as from_div,\r\n" + 
						"	bd.bde as from_bde,\r\n" + 
						"	a.arm_desc,\r\n" + 
						"	t.label as act,\r\n" + 
						"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n" + 
						"	u12.sus_no as to_sus,\r\n" + 
						"	u12.unit_name as to_unit,\r\n" + 
						"	c1.command as to_command,\r\n" + 
						"	cp1.corps as to_corps,\r\n" + 
						"	dv1.div as to_div,\r\n" + 
						"	bd1.bde as to_bde,\r\n" +
						"	u.letter_no as auth_letter	\r\n" +
						"from tb_miso_orbat_unt_dtl u\r\n" + 
						"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
						"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('1','2','3','11')\r\n" + 
						"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
						"left join tb_miso_orbat_unt_dtl u12 on u.sus_no_for_comb_disint = u12.sus_no and  u12.status_sus_no in ('Inactive')\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as command,\r\n" + 
						"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as corps,\r\n" + 
						"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as div,\r\n" + 
						"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as bde,\r\n" + 
						"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as command,\r\n" + 
						"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c1 on substr(u12.form_code_control,1,1)=c1.command_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as corps,\r\n" + 
						"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp1 on substr(u12.form_code_control,2,2)=cp1.corps_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as div,\r\n" + 
						"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv1 on substr(u12.form_code_control,4,3)=dv1.div_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as bde,\r\n" + 
						"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd1 on substr(u12.form_code_control,7,4)=bd1.bde_code\r\n" + 
						"where u.status_sus_no='Active' and (date_part('year',u.creation_on)) =cast(? as integer) \r\n" + 
						"order by act\r\n" + 
						"";
				
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1,ddlYears);
				
				ResultSet rs1 = stmt.executeQuery();
				while (rs1.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs1.getString("from_sus"));
					list.add(rs1.getString("from_unit"));
					list.add(rs1.getString("to_sus"));
					list.add(rs1.getString("to_unit"));
					list.add(rs1.getString("from_command"));
					list.add(rs1.getString("to_command"));
					list.add(rs1.getString("arm_desc"));
					list.add(rs1.getString("chkd"));
					list.add(rs1.getString("auth_letter"));
					list.add(rs1.getString("act"));
					list.add(rs1.getString("chkd"));
					alist.add(list);
				}
				rs1.close();

			} 
			else if (report_type.equals("d")) 
			{
				sql1 = "select \r\n" + 
						"	u.sus_no as from_sus,\r\n" + 
						"	u.unit_name as from_unit,\r\n" + 
						"	c.command as from_command,\r\n" + 
						"	cp.corps as from_corps,\r\n" + 
						"	dv.div as from_div,\r\n" + 
						"	bd.bde as from_bde,\r\n" + 
						"	a.arm_desc,\r\n" + 
						"	t.label as act,\r\n" + 
						"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n" + 
						"   s.letter_no as auth_letter\r\n"+
						"from tb_miso_orbat_unt_dtl u\r\n" + 
						"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
						"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('4')\r\n" + 
						"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as command,\r\n" + 
						"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as corps,\r\n" + 
						"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as div,\r\n" + 
						"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as bde,\r\n" + 
						"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
						"where u.status_sus_no='Inactive' and (date_part('year',u.creation_on)) =cast(? as integer) \r\n" + 
						"order by act\r\n" + 
						" ";
				
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1,ddlYears);
			
				ResultSet rs1 = stmt.executeQuery();
				while (rs1.next()) {
					ArrayList<String> list = new ArrayList<String>();
					 list.add(rs1.getString("from_sus"));
   		    	  list.add(rs1.getString("from_unit"));
   		    	  list.add(rs1.getString("arm_desc"));
   		    	  list.add(rs1.getString("from_command"));
   		    	  list.add(rs1.getString("from_corps"));
   		    	  list.add(rs1.getString("from_div"));
   		    	  list.add(rs1.getString("from_bde"));
   		    	  list.add(rs1.getString("arm_desc"));
   		    	  list.add(rs1.getString("act"));
   		    	  list.add(rs1.getString("chkd"));
   		    	 list.add(rs1.getString("auth_letter"));
					alist.add(list);
				}
				rs1.close();
			} 
			else if (report_type.equals("rr")) 
			{
				sql1  = "select \r\n" + 
						"	u.sus_no as from_sus,\r\n" + 
						"	u.unit_name as from_unit,\r\n" + 
						"	c.command as from_command,\r\n" + 
						"	cp.corps as from_corps,\r\n" + 
						"	dv.div as from_div,\r\n" + 
						"	bd.bde as from_bde,\r\n" + 
						"	a.arm_desc,\r\n" + 
						"	t.label as act,\r\n" + 
						"	to_char(s.approved_rejected_on,'dd-mm-yyyy') as chkd,\r\n" + 
						"   s.letter_no as auth_letter\r\n"+
						"from tb_miso_orbat_unt_dtl u\r\n" + 
						"inner join tb_miso_orbat_arm_code a on u.arm_code=a.arm_code\r\n" + 
						"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id and s.type_of_letter in ('0','5')\r\n" + 
						"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' \r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as command,\r\n" + 
						"		substr(u1.form_code_control,1,1) as command_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Command' and u1.status_sus_no='Active') c on substr(u.form_code_control,1,1)=c.command_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as corps,\r\n" + 
						"		substr(u1.form_code_control,2,2) as corps_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Corps' and u1.status_sus_no='Active') cp on substr(u.form_code_control,2,2)=cp.corps_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as div,\r\n" + 
						"		substr(u1.form_code_control,4,3) as div_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Division' and u1.status_sus_no='Active') dv on substr(u.form_code_control,4,3)=dv.div_code\r\n" + 
						"left join (select\r\n" + 
						"		u1.unit_name as bde,\r\n" + 
						"		substr(u1.form_code_control,7,4) as bde_code\r\n" + 
						"	from tb_miso_orbat_unt_dtl u1\r\n" + 
						"	inner join tb_miso_orbat_codesform c1 on u1.sus_no=c1.sus_no and c1.level_in_hierarchy='Brigade' and u1.status_sus_no='Active') bd on substr(u.form_code_control,7,4)=bd.bde_code\r\n" + 
						"where u.status_sus_no='Active' and (date_part('year',u.creation_on)) =cast(? as integer) \r\n" + 
						"order by act\r\n" + 
						" ";

				stmt = conn.prepareStatement(sql1);
				stmt.setString(1,ddlYears);
			
				ResultSet rs1 = stmt.executeQuery();
				while (rs1.next()) {
					ArrayList<String> list = new ArrayList<String>();
					 list.add(rs1.getString("from_sus"));
   		    	  list.add(rs1.getString("from_unit"));
   		    	  list.add(rs1.getString("arm_desc"));
   		    	  list.add(rs1.getString("from_command"));
   		    	  list.add(rs1.getString("from_corps"));
   		    	  list.add(rs1.getString("from_div"));
   		    	  list.add(rs1.getString("from_bde"));
   		    	  list.add(rs1.getString("arm_desc"));
   		    	  list.add(rs1.getString("act"));
   		    	  list.add(rs1.getString("chkd"));
   		    	 list.add(rs1.getString("auth_letter"));
	  		    	  alist.add(list);
				}
				rs1.close();
			}
			stmt.close();
		} catch (SQLException e) {
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
	
	// 2. Yearly Report End
	
	// 3. Half Yearly Report
	
	public  ArrayList<ArrayList<String>> search_movement_in_out_command_actionList(String report_type,String from_date, String to_date) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
		Calendar cal = Calendar.getInstance();
		cal.setTime(today); 
		int year = cal.get(Calendar.YEAR);
		String yr = Integer.toString(year);
		String whr = "";
		int flag = 0;
		Connection conn = null;
		try{	
			
			if(!from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
				whr +=" and cast(u.creation_on as date) >= cast(? as date) ";
			}
			
			if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")) {
				whr +="and cast(u.creation_on as date) <= cast(? as date) ";
			}
//			if (!from_date.equals("")) {
//				f_dt = yr+"-06-01";
//				t_dt = yr+"-06-30";
//			}
//			else if(duration.equals("2"))
//			{
//				f_dt = yr+"-12-01";
//				t_dt = yr+"-12-31";
//			}
			conn = dataSource.getConnection();
			PreparedStatement stmt =null;
			String sql1="";
			if(report_type.equals("in"))
			{
				sql1 = "select distinct\r\n" + 
						"	cm1.command,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '1' AND a.to_cmd_code = '1' THEN '1' END) AS sc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '2' AND a.to_cmd_code = '2' THEN '2' END) AS ec,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '3' AND a.to_cmd_code = '3' THEN '3' END) AS wc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '4' AND a.to_cmd_code = '4' THEN '4' END) AS cc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '5' AND a.to_cmd_code = '5' THEN '5' END) AS nc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '6' AND a.to_cmd_code = '6' THEN '6' END) AS atc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '7' AND a.to_cmd_code = '7' THEN '7' END) AS anc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '8' AND a.to_cmd_code = '8' THEN '8' END) AS swc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '9' AND a.to_cmd_code = '9' THEN '9' END) AS un,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = 'A' AND a.to_cmd_code = 'A' THEN 'A' END) AS sfc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '0' AND a.to_cmd_code = '0' THEN '0' END) AS mod,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '1' AND a.to_cmd_code = '1' THEN '1' END)+\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '2' AND a.to_cmd_code = '2' THEN '2' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '3' AND a.to_cmd_code = '3' THEN '3' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '4' AND a.to_cmd_code = '4' THEN '4' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '5' AND a.to_cmd_code = '5' THEN '5' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '6' AND a.to_cmd_code = '6' THEN '6' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '7' AND a.to_cmd_code = '7' THEN '7' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '8' AND a.to_cmd_code = '8' THEN '8' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '9' AND a.to_cmd_code = '9' THEN '9' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = 'A' AND a.to_cmd_code = 'A' THEN 'A' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '0' AND a.to_cmd_code = '0' THEN '0' END) as total\r\n" + 
						"from\r\n" + 
						"(select\r\n" + 
						"	substr(a.fc,1,1) as to_cmd_code,\r\n" + 
						"	substr(u1.form_code_control,1,1) as from_cmd_code,\r\n" + 
						"	c2.command AS to_cmd_name,\r\n" + 
						"	c1.command AS from_cmd_name,\r\n" + 
						"	a.approved_rejected_on,\r\n" + 
						"	a.label,\r\n" + 
						"	count(a.sus) AS count\r\n" + 
						"from \r\n" + 
						"(select \r\n" + 
						"	u.sus_no as sus,\r\n" + 
						"	u.sus_version as version,\r\n" + 
						"	u.form_code_control as fc,\r\n" + 
						"	s.type_of_letter,\r\n" + 
						"	s.approved_rejected_on,\r\n" + 
						"	t.label,\r\n" + 
						"	u.creation_on\r\n" + 
						"from tb_miso_orbat_unt_dtl u\r\n" + 
						"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id\r\n" + 
						"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' and t.label like 'Main%'\r\n" + 
						"where u.status_sus_no != 'Pending' "+ whr+") a \r\n" + 
						"inner join tb_miso_orbat_unt_dtl u1 on a.sus=u1.sus_no and a.version-1=u1.sus_version and substr(u1.form_code_control,1,1)!='x'\r\n" + 
						"left join (	select\r\n" + 
						"			u.unit_name as command,\r\n" + 
						"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
						"		from tb_miso_orbat_unt_dtl u\r\n" + 
						"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
						"		where u.status_sus_no='Active') c1 on substr(a.fc,1,1)=c1.command_code\r\n" + 
						"left join (	select\r\n" + 
						"			u.unit_name as command,\r\n" + 
						"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
						"		from tb_miso_orbat_unt_dtl u\r\n" + 
						"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
						"		where u.status_sus_no='Active') c2 on substr(a.fc,1,1)=c2.command_code and c1.command!=c2.command\r\n" + 
						"group by 1,2,3,4,5,6) a\r\n" + 
						"right join (	select\r\n" + 
						"			u.unit_name as command,\r\n" + 
						"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
						"		from tb_miso_orbat_unt_dtl u\r\n" + 
						"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
						"		where u.status_sus_no='Active') cm1 on a.to_cmd_code=cm1.command_code\r\n" + 
						"group by 1";			
				stmt = conn.prepareStatement(sql1);
				if(!from_date.equals("")  && !from_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, from_date);
					
				}
				if(!to_date.equals("")  && !to_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, to_date);
					
				}
				
				ResultSet rs1 = stmt.executeQuery(); 
				while(rs1.next()){
			    	  ArrayList<String> list = new ArrayList<String>();
			    	  list.add(rs1.getString("command"));
			    	  list.add(rs1.getString("sc"));
			    	  list.add(rs1.getString("ec"));
			    	  list.add(rs1.getString("wc"));
			    	  list.add(rs1.getString("cc"));
			    	  list.add(rs1.getString("nc"));
			    	  list.add(rs1.getString("atc"));
			    	  list.add(rs1.getString("anc"));
			    	  list.add(rs1.getString("swc"));
			    	  list.add(rs1.getString("un"));
			    	  list.add(rs1.getString("sfc"));
			    	  list.add(rs1.getString("mod"));
			    	  list.add(rs1.getString("total"));
			    	  alist.add(list);
		        }
			rs1.close();
		}
		if(report_type.equals("out"))
		{
			sql1 = "select distinct\r\n" + 
					"	cm1.command,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '1' AND a.from_cmd_code != '1' THEN '1' END) AS sc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '2' AND a.from_cmd_code != '2' THEN '2' END) AS ec,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '3' AND a.from_cmd_code != '3' THEN '3' END) AS wc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '4' AND a.from_cmd_code != '4' THEN '4' END) AS cc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '5' AND a.from_cmd_code != '5' THEN '5' END) AS nc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '6' AND a.from_cmd_code != '6' THEN '6' END) AS atc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '7' AND a.from_cmd_code != '7' THEN '7' END) AS anc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '8' AND a.from_cmd_code != '8' THEN '8' END) AS swc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '9' AND a.from_cmd_code != '9' THEN '9' END) AS un,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = 'A' AND a.from_cmd_code != 'A' THEN 'A' END) AS sfc,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '0' AND a.from_cmd_code != '0' THEN '0' END) AS mod,\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '1' AND a.from_cmd_code != '1' THEN '1' END)+\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '2' AND a.from_cmd_code != '2' THEN '2' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '3' AND a.from_cmd_code != '3' THEN '3' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '4' AND a.from_cmd_code != '4' THEN '4' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '5' AND a.from_cmd_code != '5' THEN '5' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '6' AND a.from_cmd_code != '6' THEN '6' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '7' AND a.from_cmd_code != '7' THEN '7' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '8' AND a.from_cmd_code != '8' THEN '8' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '9' AND a.from_cmd_code != '9' THEN '9' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = 'A' AND a.from_cmd_code != 'A' THEN 'A' END) +\r\n" + 
					"	count( CASE WHEN a.to_cmd_code = '0' AND a.from_cmd_code != '0' THEN '0' END) as total\r\n" + 
					"from\r\n" + 
					"(select\r\n" + 
					"	substr(a.fc,1,1) as to_cmd_code,\r\n" + 
					"	substr(u1.form_code_control,1,1) as from_cmd_code,\r\n" + 
					"	c2.command AS to_cmd_name,\r\n" + 
					"	c1.command AS from_cmd_name,\r\n" + 
					"	a.approved_rejected_on,\r\n" + 
					"	a.label,\r\n" + 
					"	count(a.sus) AS count\r\n" + 
					"from \r\n" + 
					"(select \r\n" + 
					"	u.sus_no as sus,\r\n" + 
					"	u.sus_version as version,\r\n" + 
					"	u.form_code_control as fc,\r\n" + 
					"	s.type_of_letter,\r\n" + 
					"	s.approved_rejected_on,\r\n" + 
					"	t.label\r\n" + 
					"from tb_miso_orbat_unt_dtl u\r\n" + 
					"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id\r\n" + 
					"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' and t.label like 'Main%'\r\n" + 
					"where u.status_sus_no != 'Pending' "+ whr+") a \r\n" + 
					"inner join tb_miso_orbat_unt_dtl u1 on a.sus=u1.sus_no and a.version-1=u1.sus_version and substr(u1.form_code_control,1,1)!='x'\r\n" + 
					"left join (	select\r\n" + 
					"			u.sus_no,\r\n" + 
					"			u.unit_name as command,\r\n" + 
					"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
					"		from tb_miso_orbat_unt_dtl u\r\n" + 
					"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
					"		where u.status_sus_no='Active') c1 on substr(a.fc,1,1)=c1.command_code\r\n" + 
					"left join (	select\r\n" + 
					"			u.sus_no,\r\n" + 
					"			u.unit_name as command,\r\n" + 
					"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
					"		from tb_miso_orbat_unt_dtl u\r\n" + 
					"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
					"		where u.status_sus_no='Active') c2 on substr(a.fc,1,1)=c2.command_code and c1.command!=c2.command\r\n" + 
					"group by 1,2,3,4,5,6) a\r\n" + 
					"right join (	select\r\n" + 
					"			u.sus_no,\r\n" + 
					"			u.unit_name as command,\r\n" + 
					"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
					"		from tb_miso_orbat_unt_dtl u\r\n" + 
					"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
					"		where u.status_sus_no='Active') cm1 on a.to_cmd_code=cm1.command_code\r\n" + 
					"group by 1\r\n" + 
					"";		
			stmt = conn.prepareStatement(sql1);
			if(!from_date.equals("")  && !from_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, from_date);
				
			}
			if(!to_date.equals("")  && !to_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, to_date);
				
			}
			
		
			ResultSet rs1 = stmt.executeQuery(); 
			while(rs1.next()){
				 ArrayList<String> list = new ArrayList<String>();
				  list.add(rs1.getString("command"));
		    	  list.add(rs1.getString("sc"));
		    	  list.add(rs1.getString("ec"));
		    	  list.add(rs1.getString("wc"));
		    	  list.add(rs1.getString("cc"));
		    	  list.add(rs1.getString("nc"));
		    	  list.add(rs1.getString("atc"));
		    	  list.add(rs1.getString("anc"));
		    	  list.add(rs1.getString("swc"));
		    	  list.add(rs1.getString("un"));
		    	  list.add(rs1.getString("sfc"));
		    	  list.add(rs1.getString("mod"));
		    	  list.add(rs1.getString("total"));
		    	  alist.add(list);
	      }
		rs1.close();
		}
		if(report_type.equals("within"))
		{
				sql1 = "select distinct\r\n" + 
						"	a.label,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '1' AND a.to_cmd_code = '1' THEN '1' END) AS sc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '2' AND a.to_cmd_code = '2' THEN '2' END) AS ec,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '3' AND a.to_cmd_code = '3' THEN '3' END) AS wc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '4' AND a.to_cmd_code = '4' THEN '4' END) AS cc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '5' AND a.to_cmd_code = '5' THEN '5' END) AS nc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '6' AND a.to_cmd_code = '6' THEN '6' END) AS atc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '7' AND a.to_cmd_code = '7' THEN '7' END) AS anc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '8' AND a.to_cmd_code = '8' THEN '8' END) AS swc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '9' AND a.to_cmd_code = '9' THEN '9' END) AS un,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = 'A' AND a.to_cmd_code = 'A' THEN 'A' END) AS sfc,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '0' AND a.to_cmd_code = '0' THEN '0' END) AS mod,\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '1' AND a.to_cmd_code = '1' THEN '1' END)+\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '2' AND a.to_cmd_code = '2' THEN '2' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '3' AND a.to_cmd_code = '3' THEN '3' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '4' AND a.to_cmd_code = '4' THEN '4' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '5' AND a.to_cmd_code = '5' THEN '5' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '6' AND a.to_cmd_code = '6' THEN '6' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '7' AND a.to_cmd_code = '7' THEN '7' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '8' AND a.to_cmd_code = '8' THEN '8' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '9' AND a.to_cmd_code = '9' THEN '9' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = 'A' AND a.to_cmd_code = 'A' THEN 'A' END) +\r\n" + 
						"	count( CASE WHEN a.from_cmd_code = '0' AND a.to_cmd_code = '0' THEN '0' END) as total\r\n" + 
						"from\r\n" + 
						"(select\r\n" + 
						"	substr(a.fc,1,1) as to_cmd_code,\r\n" + 
						"	substr(u1.form_code_control,1,1) as from_cmd_code,\r\n" + 
						"	c2.command AS to_cmd_name,\r\n" + 
						"	c1.command AS from_cmd_name,\r\n" + 
						"	a.approved_rejected_on,\r\n" + 
						"	a.label,\r\n" + 
						"	count(a.sus) AS count\r\n" + 
						"from \r\n" + 
						"(select \r\n" + 
						"	u.sus_no as sus,\r\n" + 
						"	u.sus_version as version,\r\n" + 
						"	u.form_code_control as fc,\r\n" + 
						"	s.type_of_letter,\r\n" + 
						"	s.approved_rejected_on,\r\n" + 
						"	t.label\r\n" + 
						"from tb_miso_orbat_unt_dtl u\r\n" + 
						"inner join tb_miso_orbat_shdul_detl s on u.id=s.letter_id\r\n" + 
						"inner join t_domain_value t on s.type_of_letter=t.codevalue and t.domainid='SCHEDULETYPE' and t.label like 'Main%'\r\n" + 
						"where u.status_sus_no != 'Pending'  "+ whr+") a \r\n" + 
						"inner join tb_miso_orbat_unt_dtl u1 on a.sus=u1.sus_no and a.version-1=u1.sus_version and substr(u1.form_code_control,1,1)!='x'\r\n" + 
						"left join (	select\r\n" + 
						"			u.sus_no,\r\n" + 
						"			u.unit_name as command,\r\n" + 
						"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
						"		from tb_miso_orbat_unt_dtl u\r\n" + 
						"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
						"		where u.status_sus_no='Active') c1 on substr(a.fc,1,1)=c1.command_code\r\n" + 
						"left join (	select\r\n" + 
						"			u.sus_no,\r\n" + 
						"			u.unit_name as command,\r\n" + 
						"			substr(u.form_code_control::text, 1, 1) AS command_code\r\n" + 
						"		from tb_miso_orbat_unt_dtl u\r\n" + 
						"		inner join tb_miso_orbat_codesform c on c.sus_no=u.sus_no and c.level_in_hierarchy='Command'\r\n" + 
						"		where u.status_sus_no='Active') c2 on substr(a.fc,1,1)=c2.command_code and c1.command!=c2.command\r\n" + 
						"group by 1,2,3,4,5,6) a\r\n" + 
						"group by 1\r\n" + 
						"";
				stmt = conn.prepareStatement(sql1);
				if(!from_date.equals("")  && !from_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, from_date);
					
				}
				if(!to_date.equals("") && !to_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, to_date);
					
				}
			
			System.out.println("====="+stmt);
				ResultSet rs1 = stmt.executeQuery();

				while (rs1.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs1.getString("label"));
					list.add(rs1.getString("sc"));
					list.add(rs1.getString("ec"));
					list.add(rs1.getString("wc"));
					list.add(rs1.getString("cc"));
					list.add(rs1.getString("nc"));
					list.add(rs1.getString("atc"));
					list.add(rs1.getString("anc"));
					list.add(rs1.getString("swc"));
					list.add(rs1.getString("un"));
					list.add(rs1.getString("sfc"));
					list.add(rs1.getString("mod"));
					list.add(rs1.getString("total"));
					alist.add(list);
				}
				rs1.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		return alist;
	}	
	
	// 3. Half Yearly Report End
}
