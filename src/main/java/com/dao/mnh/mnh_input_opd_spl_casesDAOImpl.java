package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;


public class mnh_input_opd_spl_casesDAOImpl implements mnh_input_opd_spl_casesDAO {
	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> getMedProSubProCode(String sus,String quater,int dept_id,String yr) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			String sql1 = "";
			String whr = null;
			if(sus != ""  && sus != null && sus !="null" ) 
			{	
				if(!sus.equals("all")){
					whr = " d.sus_no =?";
				}
			}		
			if(quater != "" && quater !=null) 
			{
				whr += " and d.quater =?";
			}
			if( dept_id != -1 )
			{
				whr += " and d.dept_id =?";
				
			}
			if(yr != "" && yr !=null) 
			{
				whr += " and d.yr =?";
			}
			String sql1Count =  "select  count(d.id) as tid from  tb_med_OPD_SPL d" + 
								" where "+whr;
			
			stmt=conn.prepareStatement(sql1Count);
			
			int j = 1;
			
			if(sus != ""  && sus != null && sus !="null" ) {
				stmt.setString(j, sus);
				j += 1;
				}
			if(quater != "" && quater !=null) {
				stmt.setString(j, quater);
				j += 1;
				}
			
			if( dept_id != -1 ) {
				stmt.setInt(j, dept_id);
				j += 1;
				}
			if(yr != "" && yr !=null) {
				stmt.setString(j, yr);
				}
		
			ResultSet rs1 = stmt.executeQuery();
		
			ResultSetMetaData metaData = rs1.getMetaData();
			int i =0;
			int count =0;
			while (rs1.next()) {
				count = rs1.getInt("tid");
				}
			
			///////////
			
			
			if(count == 0)
			{
				String whr1=null;
				if( dept_id != -1 ) {
					whr1 = " and c.id =?";
				 
					}
				 	
				String sql1_data_ser = "select distinct b.proc_name,a.subproc_name,b.id as proc_id,c.id as dept_id,a.id as subproc_id\r\n" + 
						"from tb_med_opd_sp_subprocedure a inner join tb_med_opd_sp_procedure b on b.id = a.proc_id and b.status='ACTIVE'\r\n" + 
						"inner join tb_med_opd_sp_department c on c.id = a.dept_id and c.status='ACTIVE' \r\n" + whr1+
						"left join tb_med_opd_sp_subprocedure s on a.id=s.proc_id and s.status='ACTIVE'"; 
				
				stmt1=conn.prepareStatement(sql1_data_ser);
				int k=1;
				if( dept_id != -1 ) {
					stmt1.setInt(k, dept_id);
					k += 1;
					}
				
				ResultSet rs2 = stmt1.executeQuery();
				
				
				ResultSetMetaData metaData1 = rs2.getMetaData();
				while (rs2.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs2.getString("proc_name"));//0
					list.add(rs2.getString("subproc_name"));//1
					
					String opd_count ="<input type='text' class='input_control' style='width:50px;'  id = 'opd_count" + i + "' name='opd_count" + i + "' value='0' onkeypress='return isNumberKey(event, this);'  autocomplete='off'>" ; 
					String proc_id ="<input type='hidden' class='input_control' style='width:50px;'  id = 'proc_id" + i + "' name='proc_id" + i + "' value=" +rs2.getInt("proc_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String dept_id1 ="<input type='hidden' class='input_control' style='width:50px;'  id = 'dept_id" + i + "' name='dept_id" + i + "' value=" +rs2.getInt("dept_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String subproc_id ="<input type='hidden' class='input_control' style='width:50px;'  id = 'subproc_id" + i + "' name='subproc_id" + i + "' value=" +rs2.getInt("subproc_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String action_id ="<input type='hidden' class='action' id = 'action" + i + "' name='action" + i + "' value='0' >" ; 
					
					
					
					list.add(opd_count);//2
					list.add(proc_id);//3
					list.add(dept_id1);//4
					list.add(subproc_id);//5
					list.add(action_id);//6
					alist.add(list);
					}
				}
			if(count > 0)
			{
				if(sus != ""  && sus != null && sus !="null" ) {	
					if(!sus.equals("all")){
						whr = " o.sus_no =?";
					}
					}		
				if(quater != "" && quater !=null) {
					whr += " and o.quater =?";
					}
				if( dept_id != -1 ) {
					whr += " and o.dept_id =?";
					}
				if(yr != "" && yr !=null) { 
					whr += " and o.yr =?";
					}
			    
				String sql1_data_ser= "select distinct p.proc_name,s.subproc_name,o.opd_count,p.id as proc_id,s.id as subproc_id,d.id as dept_id,o.id from tb_med_OPD_SPL o\r\n" + 
						"inner join tb_med_opd_sp_department d on o.dept_id=d.id and  d.status='ACTIVE' \r\n" + 
						"left join tb_med_opd_sp_procedure p on p.id=o.proc_id and p.status='ACTIVE'\r\n" + 
						"left join tb_med_opd_sp_subprocedure s on s.id=o.subproc_id and s.status='ACTIVE'\r\n" + 
						"where"+ whr +"order by p.proc_name" ;
						
			 
				stmt1=conn.prepareStatement(sql1_data_ser);
		    		int j1 = 1;
				
				if(sus != ""  && sus != null && sus !="null" ) {
					stmt1.setString(j1, sus);
					j1 += 1;
					}
				if(quater != "" && quater !=null) {
					stmt1.setString(j1, quater);
					j1 += 1;
					}
				if( dept_id != -1 ) {
					stmt1.setInt(j1, dept_id);
					j1 += 1;
					}
				if(yr != "" && yr !=null) {
					stmt1.setString(j1, yr);
					}
				
				ResultSet rs2 = stmt1.executeQuery();
			
				ResultSetMetaData metaData1 = rs2.getMetaData();
				
				while (rs2.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs2.getString("proc_name"));//0
					list.add(rs2.getString("subproc_name"));
					
					String opd_count ="<input type='text' class='input_control opdc' style='width:35px;' id ='opd_count" + i + "' name='opd_count" + i + "' value=" +rs2.getInt("opd_count")+ "  onkeypress='return isNumberKey(event, this);' onkeyup='totalcal();' autocomplete='off'>" ; 
					String proc_id ="<input type='hidden' class='input_control' style='width:50px;'  id = 'proc_id" + i + "' name='proc_id" + i + "' value=" +rs2.getInt("proc_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String dept_id1 ="<input type='hidden' class='input_control' style='width:50px;'  id = 'dept_id" + i + "' name='dept_id" + i + "' value=" +rs2.getInt("dept_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String subproc_id ="<input type='hidden' class='input_control' style='width:50px;'  id = 'subproc_id" + i + "' name='subproc_id" + i + "' value=" +rs2.getInt("subproc_id")+ " onkeypress='return isNumberKey(event, this);' autocomplete='off'>" ; 
					String action_id ="<input type='text' class='action' id = 'action" + i + "' name='action" + i + "' value=" +rs2.getInt("id")+ " >" ; 
				   	  
					list.add(opd_count);
					list.add(proc_id);
					list.add(dept_id1);
					list.add(subproc_id);
					list.add(action_id); 
					alist.add(list);
					}
				}
			rs1.close();
			stmt.close();
			conn.close();
			}
		catch (SQLException e){
			e.printStackTrace();
			}
		finally
		{
			if(conn != null)
			{
				try{
					conn.close();
					}catch (SQLException e){
					 }
		    }
		}
		return alist;
		}



}
