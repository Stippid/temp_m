/*package com.dao.mnh_Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.models.mnh.Med_principle_dis;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class MNHDashboardFinalDAOImpl implements MNHDashboardFinalDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
  
    public String getChart51List() {
			ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();
				String sql1="";
				ResultSet rs1 = null;
				sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and command is not null group by command";
			    rs1 = stmt.executeQuery(sql1);
			    while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("Command"));
					list.add(rs1.getString("count"));
					listA.add(list);
		        }	
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String list = "[";
			for(int i =0 ;i<listA.size();i++) {
				String Command ="";
				if(listA.get(i).get(0).equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
				if(listA.get(i).get(0).equals("MIN OF DEFENCE")) { Command = "MOD"; }
				if(listA.get(i).get(0).equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
				if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
				if(listA.get(i).get(0).equals("HQ UNITED NATION")) { Command = "UN"; }
				if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
				if(listA.get(i).get(0).equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
				if(listA.get(i).get(0).equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
				if(listA.get(i).get(0).equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
				if(listA.get(i).get(0).equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
				if(listA.get(i).get(0).equals("HQ WESTERN COMMAND")) { Command = "WC"; }
			
				if(i == 0) {
					if(!Command.equals("")) {
						list +="{'Command' : '"+Command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +="{'Command' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}else {
					if(!Command.equals("")) {
						list +=",{'Command' : '"+Command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +=",{'Command' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}
			}
			list += "]";
			
			return list;
		}
    
  //Chart 1 Methods
	 public String getChart1List() {
			ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();

				String sql1="select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and relationship='SELF' and service in ('AR','RR','DS','MC','MA') and yr = extract(year from now()) group by upper(category)";
			    ResultSet rs1 = stmt.executeQuery(sql1);
			    while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("cat"));
					list.add(rs1.getString("count"));
					listA.add(list);
		        }
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String list = "[";
			for(int i =0 ;i<listA.size();i++) {
				String command ="";
				if(listA.get(i).get(0).equals("OFFICER")) { command = "OFFR"; }
				if(listA.get(i).get(0).equals("MNS")) { command = "MNS"; }
				if(listA.get(i).get(0).equals("JCOs")) { command = "JCO"; }
				if(listA.get(i).get(0).equals("OR")) { command = "OR"; }
				
				
				if(i == 0) {
					if(!command.equals("")) {
						list +="{'cat' : '"+command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}else {
					if(!command.equals("")) {
						list +=",{'cat' : '"+command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}
			}
			list += "]";
			
			return list;
		}
	 
		//Ex-Serv Chart
     public String getChart1ExList() {
			ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();
				String sql1="";
				ResultSet rs1 = null;
				sql1="select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where service in ('XR', 'XE','XF','XH','XN','XS') and (category is not null or category <> '') and yr = extract(year from now()) group by upper(category)";
			    rs1 = stmt.executeQuery(sql1); 
			    while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("cat"));
					list.add(rs1.getString("count"));
					listA.add(list);
			    }
				rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String list = "[";
			for(int i =0 ;i<listA.size();i++) {
				String command ="";
				if(listA.get(i).get(0).equals("OFFICER")) { command = "OFFR"; }
				if(listA.get(i).get(0).equals("MNS")) { command = "MNS"; }
				if(listA.get(i).get(0).equals("JCOs")) { command = "JCO"; }
				if(listA.get(i).get(0).equals("OR")) { command = "OR"; }
				if(i == 0) {
					if(!command.equals("")) {
						list +="{'cat' : '"+command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}else {
					if(!command.equals("")) {
						list +=",{'cat' : '"+command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}
			}
			list += "]";
			return list;
		}
     
   //Start Chart 4 TOP 10 Principal Cause 
     public String getChart4PCList() {
    	 ArrayList<List<String>> listA = new ArrayList<List<String>>();
    	 Connection conn = null;
    	 try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1="";
			ResultSet rs1 = null;
			sql1="select distinct disease_principal as cat, sum(count) as count from med_cmd_mn_count where yr = extract(year from now()) and disease_principal is not null group by disease_principal order by count desc limit 10";
			rs1 = stmt.executeQuery(sql1);   
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();	     
    	 }catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    	 String list = "[";
    	 for(int i =listA.size()-1 ;i >= 0;i--) {
    		 if(i == listA.size()-1) {
    			 list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
    		 }else {
    			 list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
    		 }
    	 }
    	 list += "]";
    	 return list;
     }
     //End Chart 4 TOP 10 Principal Cause 
     //LMC Chart
 	 public String getLMCList() {
			ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();

				String sql1="select distinct c.cmd_name as Command,"
						+ "count(a.*) as count,"
						+ "	sum(b.off_male+b.off_female+b.cadet+b.mns+b.mns_cadet) as off_str,"
						+ "	sum(b.jco+b.ort+b.dsc_jco+b.dsc_or+b.rect) as jco_or_str "
						+ "from tb_med_low_medical_category a,"
						+ "		orbat_view_cmd c, "
						+ "		tb_med_strength b "
						+ "where c.cmd_code=substring(b.cmd,1,1) group by a.category,c.cmd_name order by c.cmd_name";
			    ResultSet rs1 = stmt.executeQuery(sql1);     
				while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("Command"));
					list.add(rs1.getString("count"));
					list.add(rs1.getString("off_str"));
					list.add(rs1.getString("jco_or_str"));
					listA.add(list);
		        }	
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String list = "[";
			for(int i =0 ;i<listA.size();i++) {
				String Command ="";
				if(listA.get(i).get(0).equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
				if(listA.get(i).get(0).equals("MIN OF DEFENCE")) { Command = "MOD"; }
				if(listA.get(i).get(0).equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
				if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
				if(listA.get(i).get(0).equals("HQ UNITED NATION")) { Command = "UN"; }
				if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
				if(listA.get(i).get(0).equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
				if(listA.get(i).get(0).equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
				if(listA.get(i).get(0).equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
				if(listA.get(i).get(0).equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
				if(listA.get(i).get(0).equals("HQ WESTERN COMMAND")) { Command = "WC"; }
			
				if(i == 0) {
					if(!Command.equals("")) {
						list +="{'Command' : '"+Command+"','count' : "+listA.get(i).get(1)+",'off_str' : "+listA.get(i).get(2)+",'jco_or_str' : "+listA.get(i).get(3)+"}";
					}else{
						list +="{'Command' : '"+listA.get(i).get(0)+"','count' : "+listA.get(i).get(1)+",'off_str' : "+listA.get(i).get(2)+",'jco_or_str' : "+listA.get(i).get(3)+"}";
					}
				}else {
					if(!Command.equals("")) {
						list +=",{'Command' : '"+Command+"' ,'count' : "+listA.get(i).get(1)+",'off_str' : "+listA.get(i).get(2)+",'jco_or_str' : "+listA.get(i).get(3)+"}";
					}else{
						list +=",{'Command' : '"+listA.get(i).get(0)+"','count' : "+listA.get(i).get(1)+",'off_str' : "+listA.get(i).get(2)+",'jco_or_str' : "+listA.get(i).get(3)+"}";
					}
				}
			}
			list += "]";
			
			return list;
		}
 	 
 	 ///////////Start Daily Bed State///////
 	 public String getLMCChart1List() {
 		 ArrayList<List<String>> listA = new ArrayList<List<String>>();
 		 Connection conn = null;
 		 try{	  
 			 conn = dataSource.getConnection();
 			 PreparedStatement stmt = null;
 			 String sql1="";
 			 sql1="select distinct c.cmd_name as Command, sum(f.off) as off,sum(f.jco_or) as jco_or from \r\n" + 
						" (select distinct substr(b.form_code_control,1,1) as Command ,sum(a.off_army) as off,sum(a.jco_or_army) as jco_or\r\n" + 
						" from tb_med_daily_bed_occupancy a, tb_miso_orbat_unt_dtl b where a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null \r\n" +
						" and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
						" group by b.form_code_control ,a.off_army,a.jco_or_army) f inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
				stmt=conn.prepareStatement(sql1);
				ResultSet rs1 = stmt.executeQuery();
			    while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("Command"));
					list.add(rs1.getString("off"));
					list.add(rs1.getString("jco_or"));
					listA.add(list);
		        }	
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
			String list = "[";
			for(int i =0 ;i<listA.size();i++) {
				String Command ="";
				if(listA.get(i).get(0).equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
				if(listA.get(i).get(0).equals("MIN OF DEFENCE")) { Command = "MOD"; }
				if(listA.get(i).get(0).equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
				if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
				if(listA.get(i).get(0).equals("HQ UNITED NATION")) { Command = "UN"; }
				if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
				if(listA.get(i).get(0).equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
				if(listA.get(i).get(0).equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
				if(listA.get(i).get(0).equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
				if(listA.get(i).get(0).equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
				if(listA.get(i).get(0).equals("HQ WESTERN COMMAND")) { Command = "WC"; }
			
				if(i == 0) {
					if(!Command.equals("")) {
						list +="{'Command' : '"+Command+"','off' : "+listA.get(i).get(1)+",'jco_or' : "+listA.get(i).get(2)+"}";
					}else{
						list +="{'Command' : '"+listA.get(i).get(0)+"','off' : "+listA.get(i).get(1)+",'jco_or' : "+listA.get(i).get(2)+"}";
					}
				}else {
					if(!Command.equals("")) {
						list +=",{'Command' : '"+Command+"' ,'off' : "+listA.get(i).get(1)+",'jco_or' : "+listA.get(i).get(2)+"}";
					}else{
						list +=",{'Command' : '"+listA.get(i).get(0)+"','off' : "+listA.get(i).get(1)+",'jco_or' : "+listA.get(i).get(2)+"}";
					}
				}
			}
			list += "]";
			return list;
  	}
  	public ArrayList<List<String>> getLMCChart1ListWithParameter(String from_date,String to_date) {
  		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try{  
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1="";
			if(from_date.equals("") && to_date.equals("")) {
				sql1="select \r\n" + 
						"	distinct c.cmd_name as Command, \r\n" + 
						"	sum(f.off) as off,\r\n" + 
						"	sum(f.jco_or) as jco_or \r\n" + 
						"from \r\n" + 
						"	(select \r\n" + 
						"		distinct substr(b.form_code_control,1,1) as Command ,\r\n" + 
						"		sum(a.off_army) as off,\r\n" + 
						"		sum(a.jco_or_army) as jco_or\r\n" + 
						"	from 	tb_med_daily_bed_occupancy a\r\n" + 
						"		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
						"	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n" + 
						"	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
				stmt=conn.prepareStatement(sql1);
			}
			if(!from_date.equals("") && to_date.equals("")) {
			sql1="select \r\n" + 
					"	distinct c.cmd_name as Command, \r\n" + 
					"	sum(f.off) as off,\r\n" + 
					"	sum(f.jco_or) as jco_or \r\n" + 
					"from \r\n" + 
					"	(select \r\n" + 
					"		distinct substr(b.form_code_control,1,1) as Command ,\r\n" + 
					"		sum(a.off_army) as off,\r\n" + 
					"		sum(a.jco_or_army) as jco_or\r\n" + 
					"	from 	tb_med_daily_bed_occupancy a\r\n" + 
					"		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
					"		where a.dt between date(?) and now()\r\n" + 
					"	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n" + 
					"	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
			
				stmt=conn.prepareStatement(sql1);
				stmt.setString(1, from_date);
			}
			if(!from_date.equals("") && !to_date.equals("")) {
				sql1="select \r\n" + 
						"	distinct c.cmd_name as Command, \r\n" + 
						"	sum(f.off) as off,\r\n" + 
						"	sum(f.jco_or) as jco_or \r\n" + 
						"from \r\n" + 
						"	(select \r\n" + 
						"		distinct substr(b.form_code_control,1,1) as Command ,\r\n" + 
						"		sum(a.off_army) as off,\r\n" + 
						"		sum(a.jco_or_army) as jco_or\r\n" + 
						"	from 	tb_med_daily_bed_occupancy a\r\n" + 
						"		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
						"		where a.dt between date(?) and date(?) \r\n" + 
						"	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n" + 
						"	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
				stmt=conn.prepareStatement(sql1);
				stmt.setString(1, from_date);
				stmt.setString(2, to_date);
			}
			
			ResultSet rs1 = stmt.executeQuery();
		    while(rs1.next()){
				List<String> list = new ArrayList<String>();
				String Command ="";
				if(rs1.getString("Command").equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
				if(rs1.getString("Command").equals("MIN OF DEFENCE")) { Command = "MOD"; }
				if(rs1.getString("Command").equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
				if(rs1.getString("Command").equals("HQ EASTERN COMMAND")) { Command = "EC"; }
				if(rs1.getString("Command").equals("HQ UNITED NATION")) { Command = "UN"; }
				if(rs1.getString("Command").equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
				if(rs1.getString("Command").equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
				if(rs1.getString("Command").equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
				if(rs1.getString("Command").equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
				if(rs1.getString("Command").equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
				if(rs1.getString("Command").equals("HQ WESTERN COMMAND")) { Command = "WC"; }
				if(Command.equals("")) {
					list.add(rs1.getString("Command"));
				}else {
					list.add(Command);
				}
				list.add(rs1.getString("off"));
				list.add(rs1.getString("jco_or"));
				listA.add(list);
	        }	
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
  	}
  	///////////End Daily Bed State///////
  	
    //Data Approved Status
	 public  ArrayList<List<String>> dataappstatusList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 =	"select distinct patient_status as patient_status, count(apprvr_at_unit_by) as unit_app, count(fmn_approved_by) as fmn_app, count(apprvr_at_miso_by) as miso_app from tb_med_patient_details where extract(year from admsn_date)=2019 group by patient_status";
			stmt=conn.prepareStatement(sql1);
			ResultSet rs1 = stmt.executeQuery();
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("patient_status"));
				list.add(rs1.getString("unit_app"));
				list.add(rs1.getString("fmn_app"));
				list.add(rs1.getString("miso_app"));
				lista.add(list);
	        }	
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		return lista;
    }
	 
	 //Data Upload Status
 	 public  ArrayList<List<String>> datauploadstatusList() {
 		ArrayList<List<String>> lista = new ArrayList<List<String>>();
 		Connection conn = null;
 		try{	  
 			conn = dataSource.getConnection();
 			Statement stmt = (Statement)conn.createStatement();
 			
 			String sql1 = "select c.cmd_name as Command, \r\n" + 
 					"	f.month as mn, \r\n" + 
 					"	f.count as uploaded,\r\n" + 
 					"	sum(total.t) - f.count as pending,\r\n" + 
 					"	sum(total.t) as total\r\n" + 
 					"from\r\n" + 
 					"	(select \r\n" + 
 					"		distinct substr(b.form_code_control,1,1) as Command ,\r\n" + 
 					"		a.month,\r\n" + 
 					"		count(a.*) as count\r\n" + 
 					"	from 	tb_med_upload_data a  \r\n" + 
 					"		inner join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and b.status_sus_no = 'Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
 					"	where 	a.year = cast(extract(year from now()) as character varying)\r\n" + 
 					"	group by b.form_code_control ,a.month\r\n" + 
 					"	) f \r\n" + 
 					"	inner join orbat_view_cmd c on c.cmd_code = f.Command\r\n" + 
 					"	left join (select substr(a.form_code_control,1,1) as f,1 as t from tb_miso_orbat_unt_dtl a\r\n" + 
 					"			inner join tb_miso_orbat_codesform b on b.level_in_hierarchy='Unit' and a.sus_no = b.sus_no\r\n" + 
 					"			where a.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
 					"		) as total on total.f = c.cmd_code\r\n" + 
 					"	group by c.cmd_name,f.month,f.count";
 			ResultSet rs1 = stmt.executeQuery(sql1);     
 			while(rs1.next()){
 				List<String> list = new ArrayList<String>();
 				list.add(rs1.getString("Command"));
 				list.add(rs1.getString("mn"));
 				list.add(rs1.getString("uploaded"));
 				list.add(rs1.getString("pending"));
 				list.add(rs1.getString("total"));
 				lista.add(list);
 	        }	
 		    rs1.close();
 		    stmt.close();	     
 		}catch (SQLException e) {
 				e.printStackTrace();
 		} finally {
 			if (conn != null) {
 				try {
 					conn.close();
 				} 
 				catch (SQLException e) {
 				}
 			}
 		}
 		return lista;
     }
 	 
 	  public  ArrayList<List<String>> UnitInfoTblList() {
 	    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
 			Connection conn = null;
 			try{	  
 				conn = dataSource.getConnection();
 				Statement stmt = (Statement)conn.createStatement();
 				String sql1="select distinct uname, sum(count) as count from med_unit_mn_count group by uname order by count desc";
 				ResultSet rs1 = stmt.executeQuery(sql1);     
 				while(rs1.next()){
 					List<String> list = new ArrayList<String>();
 					
 					list.add(rs1.getString("uname"));
 					list.add(rs1.getString("count"));
 					lista.add(list);
 		        }	
 			    rs1.close();
 			    stmt.close();	     
 			}catch (SQLException e) {
 					e.printStackTrace();
 			} finally {
 				if (conn != null) {
 					try {
 						conn.close();
 					} 
 					catch (SQLException e) {
 					}
 				}
 			}
 			return lista;
 	    }
 	  
 	 public List<String> getihdList(String yr) {
     	List<String> list = new ArrayList<String>();
   		Connection conn = null;
   		try{	  
   			conn = dataSource.getConnection();
   			Statement stmt = (Statement)conn.createStatement();
   			String sql1="";
   			if(yr.equals("")) {
   				sql1="select count as ihd from med_life_disease_count where d_desc='IHD' and yr=extract(year from now())-1";
   			}else {
   				sql1="select count as ihd from med_life_disease_count where d_desc='IHD' and yr='"+yr+"'";
   			}
   			ResultSet rs1 = stmt.executeQuery(sql1);
   			if (rs1.next() == false) { 
   				list.add("0"); 
   			} else {
   				String count = rs1.getString("ihd");
 				list.add(count);
   			}
   			rs1.close();
   		    stmt.close();	     
   		}catch (SQLException e) {
 			e.printStackTrace();
   		} finally {
   			if (conn != null) {
   				try {
   					conn.close();
   				} 
   				catch (SQLException e) {
   				}
   			}
   		}
   		return list;
   	}
 	 
 	 
 	 public List<String> gethyperList(String yr) {
		 List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  			if(yr.equals("")) {
	  				sql1="select count as htn from med_life_disease_count where d_desc='HTN' and yr=extract(year from now())-1";
	  			}else {
	  				sql1="select count as htn from med_life_disease_count where d_desc='HTN' and	yr='"+yr+"'";
	  			}
	  			
	  			ResultSet rs1 = stmt.executeQuery(sql1); 
	  			if (rs1.next() == false) { 
	  				list.add("0"); 
	  			} else {
	  				String count = rs1.getString("htn");
					list.add(count);
	  			}
	  			rs1.close();
	  		    stmt.close();
	  		}catch (SQLException e) {
				e.printStackTrace();
	  		} finally {
	  			if (conn != null) {
	  				try {
	  					conn.close();
	  				} 
	  				catch (SQLException e) {
	  				}
	  			}
	  		}
	  		return list;
	  	}
 	 
 	 public List<String> getdmList(String yr) {
 		List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  			
	  			if(yr.equals("")) {
	  				sql1="select count as dm from med_life_disease_count where d_desc='DM' and yr=extract(year from now())-1";
	  			}else {
	  				sql1="select count as dm from med_life_disease_count where d_desc='DM' and	yr='"+yr+"'";
	  			}
	  	  		ResultSet rs1 = stmt.executeQuery(sql1);     
		  			if (rs1.next() == false) { 
		  				list.add("0"); 
		  			} else {
		  				String count = rs1.getString("dm");
						list.add(count);
		  			}
		  			rs1.close();
		  		    stmt.close();
		  		}catch (SQLException e) {
					e.printStackTrace();
		  		} finally {
		  			if (conn != null) {
		  				try {
		  					conn.close();
		  				} 
		  				catch (SQLException e) {
		  				}
		  			}
		  		}
		  		return list;
		  	}
 	 
 	public List<String> getobesList(String yr) {
	    List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  		
	  				if(yr.equals("")) {
		  				sql1="select count as obst from med_life_disease_count where d_desc='OBESITY' and yr=extract(year from now())-1";
		  			}else {
		  				sql1="select count as obst from med_life_disease_count where d_desc='OBESITY' and	yr='"+yr+"'";
		  			}
			
		ResultSet rs1 = stmt.executeQuery(sql1);     
		if (rs1.next() == false) { 
			list.add("0"); 
		} else {
			String count = rs1.getString("obst");
		list.add(count);
		}
		rs1.close();
	    stmt.close();
	}catch (SQLException e) {
	e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} 
			catch (SQLException e) {
			}
		}
	}
	return list;
}
 	
 	 public List<String> getinjurisList(String yr) {
		 List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  			
	  			if(yr.equals("")) {
	  				sql1="select count as inju from med_life_disease_count where d_desc='INJURIES NEA' and yr=extract(year from now())-1";
	  			}else {
	  				sql1="select count as inju from med_life_disease_count where d_desc='INJURIES NEA' and	yr='"+yr+"'";
	  			}
  			ResultSet rs1 = stmt.executeQuery(sql1);     
	  			if (rs1.next() == false) { 
	  				list.add("0"); 
	  			} else {
	  				String count = rs1.getString("inju");
					list.add(count);
	  			}
	  			rs1.close();
	  		    stmt.close();
	  		}catch (SQLException e) {
				e.printStackTrace();
	  		} finally {
	  			if (conn != null) {
	  				try {
	  					conn.close();
	  				} 
	  				catch (SQLException e) {
	  				}
	  			}
	  		}
	  		return list;
	  	}
 	public List<String> getadmList(String yr) {
		 List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  			
	  			if(yr.equals("")) {
	  				sql1="select count as adm from med_life_disease_count where yr=extract(year from now())-1";
	  			}else {
	  				sql1="select count as adm from med_life_disease_count where yr='"+yr+"'";
	  			}
	  			ResultSet rs1 = stmt.executeQuery(sql1);     
	  			if (rs1.next() == false) { 
	  				list.add("0"); 
	  			} else {
	  				String count = rs1.getString("adm");
					list.add(count);
	  			}
	  			rs1.close();
	  		    stmt.close();
	  		}catch (SQLException e) {
				e.printStackTrace();
	  		} finally {
	  			if (conn != null) {
	  				try {
	  					conn.close();
	  				} 
	  				catch (SQLException e) {
	  				}
	  			}
	  		}
	  		return list;
	  	}
 	
 	public List<String> getADSList(String yr) {
		 List<String> list = new ArrayList<String>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();
	  			Statement stmt = (Statement)conn.createStatement();
	  			String sql1="";
	  			if(yr.equals("")) {
	  				sql1="select count as ads from med_life_disease_count where d_desc='ADS' and yr=extract(year from now())-1";
	  			}else {
	  				sql1="select count as ads from med_life_disease_count where d_desc='ADS' and yr='"+yr+"'";
	  			}
 	  		ResultSet rs1 = stmt.executeQuery(sql1);     
	  			if (rs1.next() == false) { 
	  				list.add("0"); 
	  			} else {
	  				String count = rs1.getString("ads");
					list.add(count);
	  			}
	  			rs1.close();
	  		    stmt.close();
	  		}catch (SQLException e) {
				e.printStackTrace();
	  		} finally {
	  			if (conn != null) {
	  				try {
	  					conn.close();
	  				} 
	  				catch (SQLException e) {
	  				}
	  			}
	  		}
	  		return list;
	  	}
	
 	//Disease Principal
    public  ArrayList<List<String>> DiseasePrincipalTblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1="";
			ResultSet rs1 = null;
			sql1="select distinct disease_principal as b_desc, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) group by disease_principal order by disease_principal";
			rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				
				list.add(rs1.getString("b_desc"));
				list.add(rs1.getString("count"));
				lista.add(list);
	        }	
			  
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
				}
			}
		}
		return lista;
    }
    
    
    //ESM
    public ArrayList<List<String>>  getChartESMRelationship(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		String sql1 = "";
		int year = 2020;
		if(yr != null && !yr.equals(""))
		{
			year = Integer.parseInt(yr);
		}
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			if(yr.equals("") || (relationship.equals("SELF"))) {
				sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "+year+" and relationship ='SELF' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
			}
			else if(yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "+year+" and relationship !='SELF' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
			}
			else {
				sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = extract(year from now()) and relationship ='SELF' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
			}
		    ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
	        }	
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
    }
    
  //Graph serving & esm refresh STart here
    public ArrayList<List<String>>  getChartRelationship(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		String sql1 = "";
		int year = 2020;
		if(yr != null && !yr.equals(""))
		{
			year = Integer.parseInt(yr);
		}
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			if(yr.equals("") || (relationship.equals("SELF"))) {
				sql1="select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "+year+" and relationship ='SELF' and service in ('AR', 'RR', 'DS', 'MC', 'MA', 'DA') group by upper(category)";
			}
			else if(yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1="select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "+year+" and relationship !='SELF' and service in ('AR', 'RR', 'DS', 'MC', 'MA', 'DA') group by upper(category)";
			}
			else {
				sql1="select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = extract(year from now()) and relationship ='SELF' and service in ('AR', 'RR', 'DS', 'MC', 'MA', 'DA') group by upper(category)";
			}
		    ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
	        }	
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
    }
    
    public List<Med_principle_dis> getmnhPrincipalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("SELECT distinct disease_principal as principal_cause FROM Tb_Med_Disease_Cause where disease_principal is not null and disease_principal != '' order by disease_principal");
		@SuppressWarnings("unchecked")
		List<Med_principle_dis> count = (List<Med_principle_dis>) query.list();
		tx.commit();
		session.close();
		return count;
	}
    
   
	
	 public  ArrayList<List<String>> hospital_datastatus() {
	    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();
				String sql1 =	"select distinct a.sus_no,a.unit_name as unit_name,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '01') as jan,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '02') as feb,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '03') as mar,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '04') as apr,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '05') as may,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '06') as jun,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '07') as jul,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '08') as aug,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '09') as sep,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '10') as oct,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '11') as nov,\r\n" + 
						" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '12') as dec,\r\n" + 
						" count(b.medical_unit) as total\r\n" + 
						" from tb_med_patient_details b\r\n" + 
						" left join tb_miso_orbat_unt_dtl a on a.sus_no=b.medical_unit\r\n" + 
						" where a.status_sus_no='Active' and extract(year from b.admsn_date)= 2019\r\n" + 
						" group by a.sus_no,a.unit_name order by a.unit_name";
				ResultSet rs1 = stmt.executeQuery(sql1);     
				while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("unit_name"));
					list.add(rs1.getString("jan"));
					list.add(rs1.getString("feb"));
					list.add(rs1.getString("mar"));
					list.add(rs1.getString("apr"));
					list.add(rs1.getString("may"));
					list.add(rs1.getString("jun"));
					list.add(rs1.getString("jul"));
					list.add(rs1.getString("aug"));
					list.add(rs1.getString("sep"));
					list.add(rs1.getString("oct"));
					list.add(rs1.getString("nov"));
					list.add(rs1.getString("dec"));
					list.add(rs1.getString("total"));
					lista.add(list);
		        }	
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
					e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} 
					catch (SQLException e) {
					}
				}
			}
			return lista;
	    }
	 
	 ////////////////////////////command
		 public String  getChartCOMMANDRelationship(String relationship, String yr) {
		 ArrayList<List<String>> listA = new ArrayList<List<String>>();
				Connection conn = null;
				try{	  
					conn = dataSource.getConnection();
					Statement stmt = (Statement)conn.createStatement();
					String sql1="";
					ResultSet rs1 = null;
					int year = 2020;
					if(yr != null && !yr.equals(""))
					{
						year = Integer.parseInt(yr);
					}
					//sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and command is not null group by command";
					if(yr.equals("") || (relationship.equals("SELF"))) {
						sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = "+year+" and relationship ='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
					}
					else if(yr.equals("") || (relationship.equals("DEPENDENT"))) {
						sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = "+year+" and relationship !='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
					}
					else {
						sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and relationship ='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
					}
				    rs1 = stmt.executeQuery(sql1);
				    if(rs1.wasNull() == false) {
	 				//sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now())-1 and command is not null group by command";
				    	if(yr.equals("") || (relationship.equals("SELF"))) {
							sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = "+year+" and relationship ='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
						}
						else if(yr.equals("") || (relationship.equals("DEPENDENT"))) {
							sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = "+year+" and relationship !='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
						}
						else {
							sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and relationship ='SELF' and service in ('AR','RR','DS','MC','MA') and command is not null group by command";
						}
	 			    rs1 = stmt.executeQuery(sql1);
	 			   while(rs1.next()){
						List<String> list = new ArrayList<String>();
						list.add(rs1.getString("Command"));
						list.add(rs1.getString("count"));
						listA.add(list);
			        }	
				    }else {
					while(rs1.next()){
						List<String> list = new ArrayList<String>();
						list.add(rs1.getString("Command"));
						list.add(rs1.getString("count"));
						listA.add(list);
			        }	
				    }
				    rs1.close();
				    stmt.close();	     
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				String list = "[";
				for(int i =0 ;i<listA.size();i++) {
				String Command ="";
				
				if(listA.get(i).get(0).equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
				if(listA.get(i).get(0).equals("MIN OF DEFENCE")) { Command = "MOD"; }
				if(listA.get(i).get(0).equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
				if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
				if(listA.get(i).get(0).equals("HQ UNITED NATION")) { Command = "UN"; }
				if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
				if(listA.get(i).get(0).equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
				if(listA.get(i).get(0).equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
				if(listA.get(i).get(0).equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
				if(listA.get(i).get(0).equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
				if(listA.get(i).get(0).equals("HQ WESTERN COMMAND")) { Command = "WC"; }
			
				if(i == 0) {
					if(!Command.equals("")) {
						list +="{'Command' : '"+Command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +="{'Command' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}else {
					if(!Command.equals("")) {
						list +=",{'Command' : '"+Command+"' ,'count' : "+listA.get(i).get(1)+"}";
					}else{
						list +=",{'Command' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
					}
				}
			}
				list += "]";
				
				return list;
			}
		 
		 ////////////////////////////////Start low madical category //////////////////////
		
		public String getlowmadicalchart() {
	  		ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String sql1= "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n" + 
		  					"sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n" + 
		  					"from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where a.year = extract(year from now()) and substring(a.cmd,1,1)=b.cmd_code\r\n" + 
		  					"group by b.cmd_name";
			
					stmt=conn.prepareStatement(sql1);
					ResultSet rs1 = stmt.executeQuery();
				    while(rs1.next()){
						List<String> list = new ArrayList<String>();
						list.add(rs1.getString("command"));
						list.add(rs1.getString("off_total"));
						list.add(rs1.getString("jco"));
						list.add(rs1.getString("ort"));
						list.add(rs1.getString("total"));
						listA.add(list);
			        }	
				    rs1.close();
				    stmt.close();	     
				}catch (SQLException e) {
					e.printStackTrace();
				}
			
				String list = "[";
				for(int i =0 ;i<listA.size();i++) {
					String Command ="";
					if(listA.get(i).get(0).equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
					if(listA.get(i).get(0).equals("MIN OF DEFENCE")) { Command = "MOD"; }
					if(listA.get(i).get(0).equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
					if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
					if(listA.get(i).get(0).equals("HQ UNITED NATION")) { Command = "UN"; }
					if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
					if(listA.get(i).get(0).equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
					if(listA.get(i).get(0).equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
					if(listA.get(i).get(0).equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
					if(listA.get(i).get(0).equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
					if(listA.get(i).get(0).equals("HQ WESTERN COMMAND")) { Command = "WC"; }
				
					if(i == 0) {
						if(!Command.equals("")) {
							list +="{'command' : '"+Command+"','off_total' : "+listA.get(i).get(1)+",'jco' : "+listA.get(i).get(2)+",'ort' : "+listA.get(i).get(3)+",'total' : "+listA.get(i).get(4)+"}";
						}else{
							list +="{'command' : '"+listA.get(i).get(0)+"','off_total' : "+listA.get(i).get(1)+",'jco' : "+listA.get(i).get(2)+",'ort' : "+listA.get(i).get(3)+",'total' : "+listA.get(i).get(4)+"}";
						}
					}else {
						if(!Command.equals("")) {
							list +=",{'command' : '"+Command+"' ,'off_total' : "+listA.get(i).get(1)+",'jco' : "+listA.get(i).get(2)+",'ort' : "+listA.get(i).get(3)+",'total' : "+listA.get(i).get(4)+"}";
						}else{
							list +=",{'command' : '"+listA.get(i).get(0)+"','off_total' : "+listA.get(i).get(1)+",'jco' : "+listA.get(i).get(2)+",'ort' : "+listA.get(i).get(3)+",'total' : "+listA.get(i).get(4)+"}";
						}
					}
				}
				list += "]";
				return list;
			}
		
		public ArrayList<List<String>> getlowmadicalchartWithParameter(String from_date,String to_date) {
	  		ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String sql1="";
					if(from_date.equals("") && to_date.equals("")) {
						sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n" + 
			  					"sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n" + 
			  					"from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where a.year = extract(year from now()) and substring(a.cmd,1,1)=b.cmd_code\r\n" + 
			  					"group by b.cmd_name";
						stmt=conn.prepareStatement(sql1);

					}else {
						if(!from_date.equals("") && to_date.equals("")) {
							sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n" + 
								"	sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n" + 
								"	from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where substring(a.cmd,1,1)=b.cmd_code and date_of_board between date(?) and now() \r\n" + 
								"	group by b.cmd_name";
							stmt=conn.prepareStatement(sql1);
							stmt.setString(1, from_date);
						}
						if(!from_date.equals("") && !to_date.equals("")) {
							sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n" + 
									"	sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n" + 
									"	from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where substring(a.cmd,1,1)=b.cmd_code and date_of_board between date(?) and date(?) \r\n" + 
									"	group by b.cmd_name";
							stmt=conn.prepareStatement(sql1);
							stmt.setString(1, from_date);
							stmt.setString(2, to_date);
						}
					}
					ResultSet rs1 = stmt.executeQuery();
				    while(rs1.next()){
						List<String> list = new ArrayList<String>();
						String Command ="";
						if(rs1.getString("command").equals("HQ SOUTH WESTERN COMMAND")) { Command = "SWC"; }
						if(rs1.getString("command").equals("MIN OF DEFENCE")) { Command = "MOD"; }
						if(rs1.getString("command").equals("HQ ARMY TRG COMMAND (ARTRAC)")) { Command = "ARTRAC"; }
						if(rs1.getString("command").equals("HQ EASTERN COMMAND")) { Command = "EC"; }
						if(rs1.getString("command").equals("HQ UNITED NATION")) { Command = "UN"; }
						if(rs1.getString("command").equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
						if(rs1.getString("command").equals("HQ STRATEGIC FORCES COMMAND (SFC)")) { Command = "SFC"; }
						if(rs1.getString("command").equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) { Command = "ANC"; }
						if(rs1.getString("command").equals("HQ CENTRAL COMMAND")) { Command = "CC"; }
						if(rs1.getString("command").equals("HQ NORTHERN COMMAND")) { Command = "NC"; }
						if(rs1.getString("command").equals("HQ WESTERN COMMAND")) { Command = "WC"; }
						
						if(Command.equals("")) {
							list.add(rs1.getString("command"));
						}else {
							list.add(Command);
						}

						list.add(rs1.getString("off_total"));
						list.add(rs1.getString("jco"));
						list.add(rs1.getString("ort"));
						list.add(rs1.getString("total"));
						listA.add(list);
			        }	
				    rs1.close();
				    stmt.close();	     
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return listA;
			}
		 ////////////////////////////////End low madical category //////////////////////
}
*/