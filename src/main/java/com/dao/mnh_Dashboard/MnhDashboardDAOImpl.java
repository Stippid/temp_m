/*package com.dao.mnh_Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import com.models.Miso_Orbat_Unt_Dtl;
import com.models.mnh.Med_principle_dis;
import com.models.mnh.Medical_Cmd_Corp_Disease_Details_View;
import com.models.mnh.Scrutiny_Search_Model;
import com.models.mnh.Tb_Med_Daily_Surv_Disease_Mstr;
import com.models.mnh.Tb_Med_Hosp_Assign;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class MnhDashboardDAOImpl implements MnhDashboardDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  ArrayList<List<String>> dataserutinitystatusList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select extract(month from a.admsn_date) as month,\r\n" + 
					"(select count(distinct a.apprvr_at_miso_by) from tb_med_patient_details a, logininformation b where a.apprvr_at_miso_by is not null and extract(month from a.admsn_date) between\r\n" + 
					"extract(month from now() - Interval '3 months') and extract(month from now()) group by extract(month from a.admsn_date) ) as Scrutinized, \r\n" + 
					"(select count(distinct a.apprvr_at_miso_by) from tb_med_patient_details a, logininformation b where a.apprvr_at_miso_by is null and extract(month from a.admsn_date) between\r\n" + 
					"extract(month from now() - Interval '3 months') and extract(month from now()) group by extract(month from a.admsn_date) ) as Yet_to_Scrutinize, \r\n" + 
					"count(*) as Total_Data from tb_med_patient_details a, logininformation b\r\n" + 
					"where \r\n" + 
					"a.apprvr_at_miso_by=b.username and\r\n" + 
					"extract(month from a.admsn_date) between\r\n" + 
					"extract(month from now() - Interval '3 months') and extract(month from now()) \r\n" + 
					"group by extract(month from a.admsn_date)\r\n" + 
					"order by extract(month from a.admsn_date)";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("month"));
				list.add(rs1.getString("Scrutinized"));
				list.add(rs1.getString("Yet_to_Scrutinize"));
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
    ///////////////////////////////////////////////////////
    
    public  ArrayList<List<String>> morbidityTblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select distinct extract(month from admsn_date) as month, \r\n" + 
					"count(category) filter (where (category = 'OFFICER') or (category = 'MNS')) as off,\r\n" + 
					"count(category) filter (where (category = 'JCO') or (category = 'OR')) as jco,count(*) as allcat from tb_med_patient_details\r\n" + 
					"where extract(month from admsn_date) between\r\n" + 
					"extract(month from now() - Interval '3 months') and extract(month from now()) \r\n" + 
					"group by extract(month from admsn_date)\r\n" + 
					"order by extract(month from admsn_date) limit 3";
			ResultSet rs1 = stmt.executeQuery(sql1);  
			String mn="";
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				mn = rs1.getString("month");
				if(mn.equals("1")) {
					mn = "Jan";
				}
				else if(mn.equals("2")) {
					mn = "Feb";
				}
				else if(mn.equals("3")) {
					mn = "Mar";
				}
				else if(mn.equals("4")) {
					mn = "Apr";
				}
				else if(mn.equals("5")) {
					mn = "May";
				}
				else if(mn.equals("6")) {
					mn = "Jun";
				}
				else if(mn.equals("7")) {
					mn = "Jul";
				}
				else if(mn.equals("8")) {
					mn = "Aug";
				}
				else if(mn.equals("9")) {
					mn = "Sep";
				}
				else if(mn.equals("10")) {
					mn = "Oct";
				}
				else if(mn.equals("11")) {
					mn = "Nov";
				}
				else if(mn.equals("12")) {
					mn = "Dec";
				}
				list.add(mn);
				list.add(rs1.getString("off"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("allcat"));
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
    /////////////////////////////////////////
    
    public  ArrayList<List<String>> topprincipalcausestblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select distinct b.disease_principal as count1,count(*) as count\r\n" + 
					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
					"and a.admsn_type='FRESH'\r\n" + 
					"and a.relationship='SELF'\r\n" + 
					"and a.icd_cause_code1d=b.icd_code\r\n" + 
					"and SUBSTR(a.icd_cause_code1d,1,1) in ('V','W','X','Y')\r\n" + 
					"and b.disease_principal is not null\r\n" + 
					"group by b.disease_principal\r\n" + 
					"UNION\r\n" + 
					"select distinct b.disease_principal,count(*) as count\r\n" + 
					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
					"and a.admsn_type='FRESH'\r\n" + 
					"and a.relationship='SELF'\r\n" + 
					"and a.diagnosis_code1d=b.icd_code\r\n" + 
					"and SUBSTR(a.diagnosis_code1d,1,1) not in ('S','T','V','W','X','Y')\r\n" + 
					"and b.disease_principal is not null\r\n" + 
					"group by b.disease_principal\r\n" + 
					"order by count1 desc limit 10";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("count1"));
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
    /////////////////////
    
    public ArrayList<List<String>> datastatushospitalwiseTblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select distinct a.level_c as comand,\r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '01') as jan,\r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '02') as feb, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '03') as mar, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '04') as apr, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '05') as may, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '06') as jun, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '07') as jul, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '08') as aug, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '09') as sep, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '10') as oct, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '11') as nov, \r\n" + 
					"count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '12') as dec \r\n" + 
					"from tb_med_patient_details b ,tb_miso_orbat_unt_dtl a\r\n" + 
					"where a.sus_no = b.medical_unit and a.status_sus_no='Active'\r\n" + 
					"and extract(year from now()) = extract(year from admsn_date)\r\n" + 
					"group by a.level_c";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("comand"));
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
    ///////////////////////////////////////
    public String getMorbidityList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 = "select a.category as cat,count(a.*) as count\r\n" + 
					"from tb_med_patient_details a, tb_med_system_code b\r\n" + 
					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
					"and a.admsn_type='FRESH'\r\n" + 
					"and a.relationship='SELF'\r\n" + 
					"and a.category=b.sys_code_value\r\n" + 
					"and b.sys_code='CATEGORY'\r\n" + 
					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
					"group by a.category,b.order_index\r\n" + 
					"order by b.order_index";
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
			
			if(listA.get(i).get(0).equals("OFFICER")) { command = "OFFICER"; }
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
    ////////////////////////////////////
    public String getMortalityList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 = "select a.category as cat,count(a.*) as count\r\n" + 
					"from tb_med_patient_details a, tb_med_system_code b\r\n" + 
					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
					"and  a.relationship='SELF' and a.disposal in ('DEATH','FOUNDDEAD')\r\n" + 
					"and a.category=b.sys_code_value\r\n" + 
					"and b.sys_code='CATEGORY'\r\n" + 
					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
					"group by a.category,b.order_index\r\n" + 
					"order by b.order_index";
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
			
			if(listA.get(i).get(0).equals("OFFICER")) { command = "OFFICER"; }
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
    //////////////////////////////
    public String getInvalidmentList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 = "select a.category as cat,count(a.*) as count\r\n" + 
					"from tb_med_patient_details a, tb_med_system_code b\r\n" + 
					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
					"and  a.relationship='SELF' and a.disposal in ('IMB','INVALIDMNT')\r\n" + 
					"and a.category=b.sys_code_value\r\n" + 
					"and b.sys_code='CATEGORY'\r\n" + 
					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
					"group by a.category,b.order_index\r\n" + 
					"order by b.order_index";
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
			
			if(listA.get(i).get(0).equals("OFFICER")) { command = "OFFICER"; }
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
    ///////////////////////////
    public String getDatastatusList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 = "select distinct b.username as name,count(a.*) as total,(select distinct count(apprvr_at_miso_by) from tb_med_patient_details a, tb_med_hosp_assign b \r\n" + 
					" where a.medical_unit=b.sus_no and a.apprvr_at_miso_by is not null and extract(year from a.admsn_date) = extract(year from now())) as app\r\n" + 
					" from tb_med_patient_details a,tb_med_hosp_assign b\r\n" + 
					" where a.medical_unit = b.sus_no\r\n" + 
					" and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
					"group by b.username";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("name"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("app"));
				listA.add(list);
			}	
		    rs1.close();
		    stmt.close();	     
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
		String list = "[";
		for(int i =0 ;i<listA.size();i++) {
			int pending = Integer.parseInt(listA.get(i).get(1)) - Integer.parseInt(listA.get(i).get(2));
			if(i == 0) {
				list +="{'category' : '"+listA.get(i).get(0)+"' ,'value1' : "+listA.get(i).get(1)+",'value2' : "+listA.get(i).get(2)+",'value3' : "+pending+"}";
			}else {
				list +=",{'category' : '"+listA.get(i).get(0)+"' ,'value1' : "+listA.get(i).get(1)+",'value2' : "+listA.get(i).get(2)+",'value3' : "+pending+"}";
			}
		}
		list += "]";
		return list;
	}
   
   /////////////////deshboard 2 ////////////////////////////
    
    public  ArrayList<List<String>> datauploadstatusTblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select distinct extract(month from a.admsn_date) as month,\r\n" + 
					"(select count(distinct a.medical_unit) from tb_med_patient_details a, tb_med_hosp_assign b where a.medical_unit=b.sus_no) as Hospital_uploaded,\r\n" + 
					"((select count(distinct sus_no) from tb_med_hosp_assign) - (select count(distinct a.medical_unit) from tb_med_patient_details a))as Not_yet_uploaded,\r\n" + 
					"(select count(distinct sus_no) from tb_med_hosp_assign)as total\r\n" + 
					"from tb_med_patient_details a, tb_med_hosp_assign b\r\n" + 
					"where a.medical_unit=b.sus_no\r\n" + 
					"and extract(year from a.admsn_date)=extract(year from now())\r\n" + 
					"and extract(month from a.admsn_date) between\r\n" + 
					"extract(month from now() - Interval '6 months') and extract(month from now())\r\n" + 
					"order by extract(month from a.admsn_date)";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				String mn = rs1.getString("month");
				if(mn.equals("1")) {
					mn = "Jan";
				}
				else if(mn.equals("2")) {
					mn = "Feb";
				}
				else if(mn.equals("3")) {
					mn = "Mar";
				}
				else if(mn.equals("4")) {
					mn = "Apr";
				}
				else if(mn.equals("5")) {
					mn = "May";
				}
				else if(mn.equals("6")) {
					mn = "Jun";
				}
				else if(mn.equals("7")) {
					mn = "Jul";
				}
				else if(mn.equals("8")) {
					mn = "Aug";
				}
				else if(mn.equals("9")) {
					mn = "Sep";
				}
				else if(mn.equals("10")) {
					mn = "Oct";
				}
				else if(mn.equals("11")) {
					mn = "Nov";
				}
				else if(mn.equals("12")) {
					mn = "Dec";
				}
				list.add(mn);
				list.add(rs1.getString("Hospital_uploaded"));
				list.add(rs1.getString("Not_yet_uploaded"));
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
    ///////////////////////////////////////////
    public  ArrayList<List<String>> commadwiseTblList() {
    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
			String sql1 =	"select b.unit_name as command,count(*) as count\r\n" + 
					"from tb_med_patient_details a, tb_miso_orbat_unt_dtl b\r\n" + 
					"where a.medical_unit=b.sus_no\r\n" + 
					"and b.status_sus_no = 'Active'\r\n" + 
					"group by b.unit_name\r\n" + 
					"order by b.unit_name desc limit 10";
			ResultSet rs1 = stmt.executeQuery(sql1);     
			while(rs1.next()){
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
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
    /////////////////////
    public List<String> getihdList(String yr) {
    	List<String> list = new ArrayList<String>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();
  			Statement stmt = (Statement)conn.createStatement();
  			String sql1="";
  			if(yr.equals("")) {
  				sql1="select count as ihd from med_life_disease_count where d_desc='IHD' and yr=extract(year from now())";
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
    	 //////////////////////
    	 public List<String> gethyperList(String yr) {
    		 List<String> list = new ArrayList<String>();
 	  		Connection conn = null;
 	  		try{	  
 	  			conn = dataSource.getConnection();
 	  			Statement stmt = (Statement)conn.createStatement();
 	  			String sql1="";
 	  			if(yr.equals("")) {
 	  				sql1="select count as htn from med_life_disease_count where d_desc='HTN' and yr=extract(year from now())";
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
  	  			 sql1 = "select count(*) as dm\r\n" + 
  	  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
  	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
  	  					"and a.admsn_type='FRESH'\r\n" + 
  	  					"and a.relationship='SELF'\r\n" + 
  	  					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
  	  					"and a.diagnosis_code1d=b.icd_code\r\n" + 
  	  					"and b.disease_principal='DIABETES MELLITUS'";
  	  		}else {
	  	  		sql1 = "select count(*) as dm\r\n" + 
		  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
		  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
		  					"and a.admsn_type='FRESH'\r\n" + 
		  					"and a.relationship='SELF'\r\n" + 
		  					"and extract(year from a.admsn_date) = '"+yr+"'\r\n" + 
		  					"and a.diagnosis_code1d=b.icd_code\r\n" + 
		  					"and b.disease_principal='DIABETES MELLITUS' ";
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
   	  			 sql1 = "select count(*) as obst\r\n" + 
   	  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
   	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
   	  					"and a.admsn_type='FRESH'\r\n" + 
   	  					"and a.relationship='SELF'\r\n" + 
   	  					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
   	  					"and a.diagnosis_code1d=b.icd_code\r\n" + 
   	  					"and b.disease_principal='OBESITY'";
    	 	  			}else {
    	 	  				sql1 = "select count(*) as obst\r\n" + 
    	 	   	  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
    	 	   	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
    	 	   	  					"and a.admsn_type='FRESH'\r\n" + 
    	 	   	  					"and a.relationship='SELF'\r\n" + 
    	 	   	  					"and extract(year from a.admsn_date) = '"+yr+"'\r\n" + 
    	 	   	  					"and a.diagnosis_code1d=b.icd_code\r\n" + 
    	 	   	  					"and b.disease_principal='OBESITY' ";
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
    		
    	 public List<String> getADSList(String yr) {
    		 List<String> list = new ArrayList<String>();
  	  		Connection conn = null;
  	  		try{	  
  	  			conn = dataSource.getConnection();
  	  			Statement stmt = (Statement)conn.createStatement();
  	  			String sql1="";
  	  			if(yr.equals("")) {
  	  				sql1="select count as ads from med_life_disease_count where d_desc='ADS' and yr=extract(year from now())";
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
    	
    		 public List<String> getinjurisList(String yr) {
        		 List<String> list = new ArrayList<String>();
     	  		Connection conn = null;
     	  		try{	  
     	  			conn = dataSource.getConnection();
     	  			Statement stmt = (Statement)conn.createStatement();
     	  			String sql1="";
     	  			if(yr.equals("")) {
    	  			 sql1 = "select count(*) as inju\r\n" + 
    	  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
    	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
    	  					"and a.admsn_type='FRESH'\r\n" + 
    	  					"and a.relationship='SELF'\r\n" + 
    	  					"and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
    	  					"and a.icd_cause_code1d=b.icd_code\r\n" + 
    	  					"and b.disease_principal='INJURIES NEA'";
     	  			}else {
     	  			 sql1 = "select count(*) as inju\r\n" + 
        	  					"from tb_med_patient_details a, tb_med_d_disease_cause b\r\n" + 
        	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
        	  					"and a.admsn_type='FRESH'\r\n" + 
        	  					"and a.relationship='SELF'\r\n" + 
        	  					"and extract(year from a.admsn_date) = '"+yr+"'\r\n" + 
        	  					"and a.icd_cause_code1d=b.icd_code\r\n" + 
        	  					"and b.disease_principal='INJURIES NEA' ";
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
 	  			 sql1 = "select count(*) as adm\r\n" + 
 	  					"from tb_med_patient_details a\r\n" + 
 	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
 	  					"and a.admsn_type='FRESH'\r\n" + 
 	  					"and a.relationship='SELF'\r\n" + 
 	  					"and extract(year from a.admsn_date) = extract(year from now())";
 	  			}else {
 	  			 sql1 = "select count(*) as adm\r\n" + 
  	  					"from tb_med_patient_details a\r\n" + 
  	  					"where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
  	  					"and a.admsn_type='FRESH'\r\n" + 
  	  					"and a.relationship='SELF'\r\n" + 
  	  					"and extract(year from a.admsn_date) = '"+yr+"'";
 	  			 
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
    	 
    	 public String getmonthwiseList() {
 			String qry="select TO_CHAR(a.admsn_date,'YYYY-MM') as month ,count(*)\r\n" + 
 					"from tb_med_patient_details a, tb_miso_orbat_unt_dtl b\r\n" + 
 					"where \r\n" + 
 					"extract(year from a.admsn_date) = extract(year from now()) \r\n" + 
 					"and a.medical_unit=b.sus_no\r\n" + 
 					"and b.status_sus_no = 'Active'\r\n" + 
 					"group by TO_CHAR(a.admsn_date,'YYYY-MM')\r\n" + 
 					"order by TO_CHAR(a.admsn_date,'YYYY-MM') ";
 			List<Map<String, Object>> list = getmonthwiseListJdbc(qry);	
 			String list1 = "";
 			for(int i = 0 ; i<list.size();i++) {
 				if(!list.get(i).get("count").equals(0))
 					list1 +=",{'month': "+"'"+list.get(i).get("month").toString()+"'"+", 'count': "+list.get(i).get("count")+"}";
 				}
 				if(list1.length() > 0) {
 					list1 ="["+ list1.substring(1,list1.length());
 					list1 += "]";
 				}
 			return list1;
 		}
    	 public List<Map<String, Object>> getmonthwiseListJdbc(String qry)
 	  	{				
 	  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 	  		Connection conn = null;
 	  		try{	  
 	  			conn = dataSource.getConnection();					
 	  			PreparedStatement stmt=conn.prepareStatement(qry);
 	  			ResultSet rs = stmt.executeQuery();      
 	  			ResultSetMetaData metaData = rs.getMetaData();

 	  			int columnCount = metaData.getColumnCount();
 	  			while (rs.next()) {
 	   	            Map<String, Object> columns = new LinkedHashMap<String, Object>();
 	   	            for (int i = 1; i <= columnCount; i++) {
 	   	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
 	   	            }       
 	   	            
 	   	            list.add(columns);
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
 	  		return list;
 	  	}
    	 /////////simple////////////////////////
    	 public String getchartsimpleList() {
    			ArrayList<List<String>> listA = new ArrayList<List<String>>();
    			Connection conn = null;
    			try{	  
    				conn = dataSource.getConnection();
    				Statement stmt = (Statement)conn.createStatement();
    				String sql1 = "select c.cmd_name as Command, f.count as count from \r\n" + 
    						"(select substr(b.form_code_control,1,1) as Command ,count(*) as count\r\n" + 
    						"from tb_med_patient_details a, tb_miso_orbat_unt_dtl b\r\n" + 
    						"where \r\n" + 
    						"extract(year from a.admsn_date) = extract(year from now()) \r\n" + 
    						"and a.medical_unit=b.sus_no\r\n" + 
    						"and b.status_sus_no = 'Active'\r\n" + 
    						"and b.form_code_control is not null\r\n" + 
    						"group by b.form_code_control\r\n" + 
    						"order by b.form_code_control) f\r\n" + 
    						"inner join orbat_view_cmd c on c.cmd_code = f.Command";
    			    ResultSet rs1 = stmt.executeQuery(sql1);     
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
					if(listA.get(i).get(0).equals("HQ EASTERN COMMAND")) { Command = "EC"; }
					if(listA.get(i).get(0).equals("HQ SOUTHERN COMMAND")) { Command = "SC"; }
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
    	 ///////////pie////////////////////
    	 public String getcategoryList() {
    			ArrayList<List<String>> listA = new ArrayList<List<String>>();
    			Connection conn = null;
    			try{	  
    				conn = dataSource.getConnection();
    				Statement stmt = (Statement)conn.createStatement();
    				String sql1 = "select a.category as cat,count(a.*) as count\r\n" + 
    						"					from tb_med_patient_details a, tb_med_system_code b\r\n" + 
    						"					where substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA')\r\n" + 
    						"					and a.admsn_type='FRESH'\r\n" + 
    						"					and a.relationship='SELF'\r\n" + 
    						"					and a.category=b.sys_code_value\r\n" + 
    						"					and b.sys_code='CATEGORY'\r\n" + 
    						"					and extract(year from a.admsn_date) = extract(year from now())\r\n" + 
    						"					group by a.category,b.order_index\r\n" + 
    						"					order by b.order_index";
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
    				String command1 ="";
    				
    				if(listA.get(i).get(0).equals("OFFICER")) { command1 = "OFFICER"; }
    				if(listA.get(i).get(0).equals("MNS")) { command1 = "MNS"; }
    				if(listA.get(i).get(0).equals("JCOs")) { command1 = "JCO"; }
    				if(listA.get(i).get(0).equals("OR")) { command1 = "OR"; }
    				
    				if(i == 0) {
    					if(!command1.equals("")) {
    						list +="{'cat' : '"+command1+"' ,'count' : "+listA.get(i).get(1)+"}";
    					}else{
    						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
    					}
    				}else {
    					if(!command1.equals("")) {
    						list +=",{'cat' : '"+command1+"' ,'count' : "+listA.get(i).get(1)+"}";
    					}else{
    						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
    					}
    				}
    			}
    			list += "]";
    			
    			return list;
    		}
    	 ///////////////////////////report ihd///////////////////
    	 
    	 public DataSet<Med_principle_dis> DatatablesCriteriasprinciple(DatatablesCriterias criterias,String whr) {
             List<Med_principle_dis> metadata = findDepartmentCriteriasihd(criterias ,whr);
        		Long countFiltered = getFilteredCounthd(criterias ,whr);
        		Long count = getTotalCounthd(whr);
        		return new DataSet<Med_principle_dis>(metadata, count, countFiltered);
        	}
        	@SuppressWarnings("unchecked")
        	private List<Med_principle_dis> findDepartmentCriteriasihd(DatatablesCriterias criterias,String whr) {
        		StringBuilder queryBuilder = null;
        		queryBuilder = new StringBuilder("SELECT d FROM Med_principle_dis d " +whr);
        		queryBuilder.append(getFilterQueryhd(criterias , queryBuilder));
        		if (criterias.hasOneSortedColumn()) {
        			List<String> orderParams = new ArrayList<String>();
        			queryBuilder.append(" ORDER BY ");
        			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
        				if(columnDef.getName().equals(""))
        				{
        					String st=" ORDER BY";
        					int i = queryBuilder.indexOf(st);
        					if (i != -1) {
        						queryBuilder.delete(i, i + st.length());
        					}
        				}
        				else if(columnDef.getName().contains("hodProfile.fullName")){
        					 orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
        				}
        				else{
        					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
        				}
        			}
        			Iterator<String> itr2 = orderParams.iterator();
        			while (itr2.hasNext()) {
        				queryBuilder.append(itr2.next());
        				if (itr2.hasNext()) {
        					queryBuilder.append(" , ");
        				}
        			}
        		}
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = session.createQuery(queryBuilder.toString());
        		q.setFirstResult(criterias.getDisplayStart());
        		q.setMaxResults(criterias.getDisplaySize());
        		List<Med_principle_dis> list = (List<Med_principle_dis>) q.list();
        		tx.commit();
        		session.close();
        		return list;
        	}

        	private static StringBuilder getFilterQueryhd(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
        		StringBuilder queryBuilder = new StringBuilder();
        		List<String> paramList = new ArrayList<String>();
        		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
        			if(!queryBuilder1.toString().contains("where")){
        				queryBuilder.append(" WHERE ");
        			}
        			else{
        				queryBuilder.append(" AND (");
        			}
        			for (ColumnDef columnDef : criterias.getColumnDefs()) {
        				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
   						if(columnDef.getName().equalsIgnoreCase("off")
   								||columnDef.getName().equalsIgnoreCase("mns")
   								||columnDef.getName().equalsIgnoreCase("jco") 
   								||columnDef.getName().equalsIgnoreCase("or")
   								||columnDef.getName().equalsIgnoreCase("count"))
   								{
   									if(criterias.getSearch().matches("[0-9]+"))
   									{
   										paramList.add(" d." + columnDef.getName()	+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
   									}
   								}
   								else{
   									paramList.add(" LOWER(d." + columnDef.getName()	+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
   								}
   						}
        			}
        			Iterator<String> itr = paramList.iterator();
        			while (itr.hasNext()) {
        				queryBuilder.append(itr.next());
        				if (itr.hasNext()) {
        					queryBuilder.append(" OR ");
        				}
        			}
        			queryBuilder.append(")");
        		}
        		return queryBuilder;
        	}
        	private Long getTotalCounthd(String whr) {
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = null;
        		q = session.createQuery("SELECT COUNT(d) FROM Med_principle_dis d " +whr);
        		Long count = (Long) q.list().get(0);
        		tx.commit();
        		session.close();
        		return count;
        	}
        	@SuppressWarnings("unchecked")
        	private Long getFilteredCounthd(DatatablesCriterias criterias,String whr) {
        		StringBuilder queryBuilder = null;
        		queryBuilder = new StringBuilder("SELECT d FROM Med_principle_dis d " +whr);
        		queryBuilder.append(getFilterQueryhd(criterias,queryBuilder));
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = session.createQuery(queryBuilder.toString());
        		List<Med_principle_dis> list = (List<Med_principle_dis>) q.list();
        		tx.commit();
        		session.close();
        		return Long.parseLong(String.valueOf(list.size()));
        	}
      ///data upload///////////////////
        	
    	 public DataSet<Tb_Med_Hosp_Assign> DatatablesCriteriasdataupload(DatatablesCriterias criterias,String whr) {
             List<Tb_Med_Hosp_Assign> metadata = findDepartmentCriteriasupload(criterias ,whr);
        		Long countFiltered = getFilteredCountupl(criterias ,whr);
        		Long count = getTotalCountupl(whr);
        		return new DataSet<Tb_Med_Hosp_Assign>(metadata, count, countFiltered);
        	}
        	@SuppressWarnings("unchecked")
        	private List<Tb_Med_Hosp_Assign> findDepartmentCriteriasupload(DatatablesCriterias criterias,String whr) {
        		StringBuilder queryBuilder = null;
        		queryBuilder = new StringBuilder("SELECT d FROM Tb_Med_Hosp_Assign d " +whr);
        		queryBuilder.append(getFilterQueryupl(criterias , queryBuilder));
        		if (criterias.hasOneSortedColumn()) {
        			List<String> orderParams = new ArrayList<String>();
        			queryBuilder.append(" ORDER BY ");
        			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
        				if(columnDef.getName().equals(""))
        				{
        					String st=" ORDER BY";
        					int i = queryBuilder.indexOf(st);
        					if (i != -1) {
        						queryBuilder.delete(i, i + st.length());
        					}
        				}
        				else if(columnDef.getName().contains("hodProfile.fullName")){
        					 orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
        				}
        				else{
        					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
        				}
        			}
        			Iterator<String> itr2 = orderParams.iterator();
        			while (itr2.hasNext()) {
        				queryBuilder.append(itr2.next());
        				if (itr2.hasNext()) {
        					queryBuilder.append(" , ");
        				}
        			}
        		}
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = session.createQuery(queryBuilder.toString());
        		q.setFirstResult(criterias.getDisplayStart());
        		q.setMaxResults(criterias.getDisplaySize());
        		List<Tb_Med_Hosp_Assign> list = (List<Tb_Med_Hosp_Assign>) q.list();
        		tx.commit();
        		session.close();
        		return list;
        	}

        	private static StringBuilder getFilterQueryupl(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
        		StringBuilder queryBuilder = new StringBuilder();
        		List<String> paramList = new ArrayList<String>();
        		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
        			if(!queryBuilder1.toString().contains("where")){
        				queryBuilder.append(" WHERE ");
        			}
        			else{
        				queryBuilder.append(" AND (");
        			}
        			for (ColumnDef columnDef : criterias.getColumnDefs()) {
        				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
   						if(columnDef.getName().equalsIgnoreCase("id")
   								||columnDef.getName().equalsIgnoreCase("roleid"))
   								{
   									if(criterias.getSearch().matches("[0-9]+"))
   									{
   										paramList.add(" d." + columnDef.getName()	+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
   									}
   								}
   								else{
   									paramList.add(" LOWER(d." + columnDef.getName()	+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
   								}
   						}
        			}
        			Iterator<String> itr = paramList.iterator();
        			while (itr.hasNext()) {
        				queryBuilder.append(itr.next());
        				if (itr.hasNext()) {
        					queryBuilder.append(" OR ");
        				}
        			}
        			queryBuilder.append(")");
        		}
        		return queryBuilder;
        	}
        	private Long getTotalCountupl(String whr) {
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = null;
        		q = session.createQuery("SELECT COUNT(d) FROM Tb_Med_Hosp_Assign d " +whr);
        		Long count = (Long) q.list().get(0);
        		tx.commit();
        		session.close();
        		return count;
        	}
        	@SuppressWarnings("unchecked")
        	private Long getFilteredCountupl(DatatablesCriterias criterias,String whr) {
        		StringBuilder queryBuilder = null;
        		queryBuilder = new StringBuilder("SELECT d FROM Tb_Med_Hosp_Assign d " +whr);
        		queryBuilder.append(getFilterQueryupl(criterias,queryBuilder));
        		Session session= HibernateUtil.getSessionFactory().openSession();
        		Transaction tx = session.beginTransaction();
        		Query q = session.createQuery(queryBuilder.toString());
        		List<Tb_Med_Hosp_Assign> list = (List<Tb_Med_Hosp_Assign>) q.list();
        		tx.commit();
        		session.close();
        		return Long.parseLong(String.valueOf(list.size()));
        	}
        	
        	///Sandeep Added
        	public List<Med_principle_dis> getmnhPrincipalList() {
    			Session session= HibernateUtil.getSessionFactory().openSession();
    			session.setFlushMode(FlushMode.ALWAYS);
    			Transaction tx = session.beginTransaction();
    			Query query = session.createQuery("SELECT distinct disease_principal as principal_cause FROM Med_principle_dis order by disease_principal");
    			@SuppressWarnings("unchecked")
				List<Med_principle_dis> count = (List<Med_principle_dis>) query.list();
    			tx.commit();
    			session.close();
    			return count;
    		}
        	
        	public List<Miso_Orbat_Unt_Dtl> getmnhCommandList() {
    			Session session= HibernateUtil.getSessionFactory().openSession();
    			session.setFlushMode(FlushMode.ALWAYS);
    			Transaction tx = session.beginTransaction();
    			String sql1 = "select distinct c.cmd_name as Command from (select substr(b.form_code_control,1,1) as Command from Miso_Orbat_Unt_Dtl b where b.status_sus_no = 'Active' and b.form_code_control is not null group by b.form_code_control order by b.form_code_control) as f left join orbat_view_cmd c on c.cmd_code = f.Command where c.cmd_name is not null";
    			Query query = session.createQuery(sql1);
    			@SuppressWarnings("unchecked")
				List<Miso_Orbat_Unt_Dtl> count = (List<Miso_Orbat_Unt_Dtl>) query.list();
    			tx.commit();
    			session.close();
    			return count;
    		}
        	
        	//Chart 1 Methods
        	 public String getChart1List() {
        			ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();

        				String sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where category is not null or category <> '' and yr = extract(year from now()) group by upper(category)";
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
        				//if(listA.get(i).get(0).equals(null)) { command = "OR"; }
        				//if(listA.get(i).get(0).equals("Officer")) { command = "OFFR"; }
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
        	 
        	 //Chart 2 Methods
        	 public String getChart2List() {
     			ArrayList<List<String>> listA = new ArrayList<List<String>>();
     			Connection conn = null;
     			try{	  
     				conn = dataSource.getConnection();
     				Statement stmt = (Statement)conn.createStatement();

     				String sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and command is not null group by command";
     			    ResultSet rs1 = stmt.executeQuery(sql1);     
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
        	 
        	 //Chart 3 line
    
        	 public String getChart3List() {
      			ArrayList<List<String>> listA = new ArrayList<List<String>>();
      			Connection conn = null;
      			try{	  
      				conn = dataSource.getConnection();
      				Statement stmt = (Statement)conn.createStatement();
      				
      				//String qry="select distinct mn as month, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) group by mn order by mn";
      				
      				
      				String qry = "select distinct b.mn as month," + 
      						"	sum(case when extract(year from now()) = a.yr and b.mn=a.mn then a.count else 0 end) as value,\r\n" + 
      						"	sum(case when extract(year from now())-1 = a.yr and b.mn=a.mn then a.count else 0  end) as value1,\r\n" + 
      						"	sum(case when extract(year from now())-2 = a.yr and b.mn=a.mn then a.count else 0  end) as value2\r\n" + 
      						"	from " + 
      						"	(select mn,yr,sum(count) as count from med_cmd_mn_count group by yr,mn order by mn) as a,\r\n" + 
      						"	(select distinct mn from med_cmd_mn_count order by mn) as b group by b.mn order by 1\r\n";
      				
      			    ResultSet rs1 = stmt.executeQuery(qry);     
      				while(rs1.next()){
      					List<String> list = new ArrayList<String>();
      					list.add(rs1.getString("month"));
      					list.add(rs1.getString("value"));
      					list.add(rs1.getString("value1"));
      					list.add(rs1.getString("value2"));
      					listA.add(list);
      			    }	
      			    rs1.close();
      			    stmt.close();	     
      			}catch (SQLException e) {
      				e.printStackTrace();
      			}
      			
      			String list = "[";
      			for(int i =0 ;i<listA.size();i++) {
      				
      				if(i == 0) {
     					list +="{'month' : '"+listA.get(i).get(0)+"' ,'value' : "+listA.get(i).get(1)+" ,'value1' : "+listA.get(i).get(2)+" ,'value2' : "+listA.get(i).get(3)+"}";
     				}else {
     					//list +=",{'Month' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
     					list +=",{'month' : '"+listA.get(i).get(0)+"' ,'value' : "+listA.get(i).get(1)+" ,'value1' : "+listA.get(i).get(2)+" ,'value2' : "+listA.get(i).get(3)+"}";
     				}
      				
      				
     				//String month ="";
     			}
      			list += "]";
      			return list;
      		}
        	 
        	 
        	 public String getChart4List() {
       			ArrayList<List<String>> listA = new ArrayList<List<String>>();
       			Connection conn = null;
       			try{	  
       				conn = dataSource.getConnection();
       				Statement stmt = (Statement)conn.createStatement();
       				
       				String qry = "select distinct qtr as diag, sum(officer_self) as offr, sum(jco_ors_self) as jco, sum(ex_serv_self) as ex_serv,sum(total_during_month) as total_all from tb_med_opdcases group by qtr order by qtr desc";
       			    ResultSet rs1 = stmt.executeQuery(qry);     
       				while(rs1.next()){
       					List<String> list = new ArrayList<String>();
       					list.add(rs1.getString("diag"));
       					//list.add(rs1.getString("count"));
       					list.add(rs1.getString("offr"));
       					list.add(rs1.getString("jco"));
       					list.add(rs1.getString("ex_serv"));
       					list.add(rs1.getString("total_all"));
       					
       					listA.add(list);
       				}	
       			    rs1.close();
       			    stmt.close();	     
       			}catch (SQLException e) {
       				e.printStackTrace();
       			}
       			
       			String list = "[";
       			for(int i =0 ;i<listA.size();i++) {
      				String month ="";
      				
      				if(listA.get(i).get(0).equals("JAN - MAR")) { month = "Q1"; }
  					if(listA.get(i).get(0).equals("APR - JUN")) { month = "Q2"; }
  					if(listA.get(i).get(0).equals("JUL - SEP")) { month = "Q3"; }
  					if(listA.get(i).get(0).equals("OCT - DEC")) { month = "Q4"; }
      				
  					if(i == 0) {
      					if(!month.equals("")) {
      						list +="{'Month' : '"+month+"' ,'count1' : "+listA.get(i).get(1)+",'count2' : "+listA.get(i).get(2)+",'count3' : 3"+listA.get(i).get(3)+",'count4' : "+listA.get(i).get(4)+"}";
      					}else{
      						list +="{'Month' : '"+listA.get(i).get(0)+"' ,'count1' : "+listA.get(i).get(1)+",'count2' : "+listA.get(i).get(2)+",'count3' : "+listA.get(i).get(3)+",'count4' : "+listA.get(i).get(4)+"}";
      					}
      				}else {
      					if(!month.equals("")) {
      						list +=",{'Month' : '"+month+"' ,'count1' : "+listA.get(i).get(1)+",'count2' : "+listA.get(i).get(2)+",'count3' : "+listA.get(i).get(3)+",'count4' : "+listA.get(i).get(4)+"}";
      					}else{
      						list +=",{'Month' : '"+listA.get(i).get(0)+"' ,'count1' : "+listA.get(i).get(1)+",'count2' : "+listA.get(i).get(2)+",'count3' : "+listA.get(i).get(3)+",'count4' : "+listA.get(i).get(4)+"}";
      					}
      				}
      			}
       			list += "]";
       			
       			return list;
       		}
         	 
        	 
        	   public List<Map<String, Object>> getAllReportListJdbc(String qry)
        	  	{				
        	  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        	  		Connection conn = null;
        	  		try{	  
        	  			conn = dataSource.getConnection();					
        	  			PreparedStatement stmt=conn.prepareStatement(qry);
        	  			ResultSet rs = stmt.executeQuery();      
        	  			ResultSetMetaData metaData = rs.getMetaData();

        	  			int columnCount = metaData.getColumnCount();
        	  			while (rs.next()) {
        	   	            Map<String, Object> columns = new LinkedHashMap<String, Object>();
        	   	            for (int i = 1; i <= columnCount; i++) {
        	   	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
        	   	            }       
        	   	            
        	   	            list.add(columns);
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
        	  		return list;
        	  	}
        	   
        	   public  ArrayList<List<String>> BlockDescTblList() {
        	    	ArrayList<List<String>> lista = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();

        				String sql1="select distinct block_description as b_desc, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) group by block_description order by count desc";
        				ResultSet rs1 = stmt.executeQuery(sql1);     
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					
        					list.add(rs1.getString("b_desc"));
        					list.add(rs1.getString("count"));
        					//list.add(rs1.getString("total"));
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
       					//list.add(rs1.getString("total"));
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
        	   
        	   public DataSet<Scrutiny_Search_Model> DatatablesCriteriasdataupload1(DatatablesCriterias criterias,String whr) {
                   List<Scrutiny_Search_Model> metadata = findDepartmentCriteriasUnitInfo(criterias ,whr);
              		Long countFiltered = getFilteredCountupl(criterias ,whr);
              		Long count = getTotalCountupl(whr);
              		return new DataSet<Scrutiny_Search_Model>(metadata, count, countFiltered);
              	}
        	   
        	     public DataSet<Scrutiny_Search_Model> DatatablesCriteriasUnitInfo(DatatablesCriterias criterias,String whr) {
                   List<Scrutiny_Search_Model> metadata = findDepartmentCriteriasUnitInfo(criterias ,whr);
              		Long countFiltered = getFilteredCounthd1(criterias ,whr);
              		Long count = getTotalCounthd1(whr);
              		return new DataSet<Scrutiny_Search_Model>(metadata, count, countFiltered);
              	}
        	 	private Long getTotalCounthd1(String whr) {
            		Session session= HibernateUtil.getSessionFactory().openSession();
            		Transaction tx = session.beginTransaction();
            		Query q = null;
            		q = session.createQuery("SELECT COUNT(d) FROM Scrutiny_Search_Model d " +whr);
            		Long count = (Long) q.list().get(0);
            		tx.commit();
            		session.close();
            		return count;
            	}
        	 	
        	 	@SuppressWarnings("unchecked")
            	private Long getFilteredCounthd1(DatatablesCriterias criterias,String whr) {
            		StringBuilder queryBuilder = null;
            		queryBuilder = new StringBuilder("SELECT d FROM Scrutiny_Search_Model d " +whr);
            		queryBuilder.append(getFilterQueryhd(criterias,queryBuilder));
            		Session session= HibernateUtil.getSessionFactory().openSession();
            		Transaction tx = session.beginTransaction();
            		Query q = session.createQuery(queryBuilder.toString());
            		List<Scrutiny_Search_Model> list = (List<Scrutiny_Search_Model>) q.list();
            		tx.commit();
            		session.close();
            		return Long.parseLong(String.valueOf(list.size()));
            	}
        	 	
        		@SuppressWarnings("unchecked")
            	private List<Scrutiny_Search_Model> findDepartmentCriteriasUnitInfo(DatatablesCriterias criterias,String whr) {
            		StringBuilder queryBuilder = null;
            		queryBuilder = new StringBuilder("SELECT d FROM Scrutiny_Search_Model d " +whr);
            		queryBuilder.append(getFilterQueryhd(criterias , queryBuilder));
            		if (criterias.hasOneSortedColumn()) {
            			List<String> orderParams = new ArrayList<String>();
            			queryBuilder.append(" ORDER BY ");
            			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
            				if(columnDef.getName().equals(""))
            				{
            					String st=" ORDER BY";
            					int i = queryBuilder.indexOf(st);
            					if (i != -1) {
            						queryBuilder.delete(i, i + st.length());
            					}
            				}
            				else if(columnDef.getName().contains("hodProfile.fullName")){
            					 orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
            				}
            				else{
            					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
            				}
            			}
            			Iterator<String> itr2 = orderParams.iterator();
            			while (itr2.hasNext()) {
            				queryBuilder.append(itr2.next());
            				if (itr2.hasNext()) {
            					queryBuilder.append(" , ");
            				}
            			}
            		}
            		Session session= HibernateUtil.getSessionFactory().openSession();
            		Transaction tx = session.beginTransaction();
            		Query q = session.createQuery(queryBuilder.toString());
            		q.setFirstResult(criterias.getDisplayStart());
            		q.setMaxResults(criterias.getDisplaySize());
            		List<Scrutiny_Search_Model> list = (List<Scrutiny_Search_Model>) q.list();
            		tx.commit();
            		session.close();
            		return list;
            	}
        	///Sandeep Added end
            	
            	public List<Tb_Med_Daily_Surv_Disease_Mstr> getmnhDailyDiseaseList() {
        			Session session= HibernateUtil.getSessionFactory().openSession();
        			session.setFlushMode(FlushMode.ALWAYS);
        			Transaction tx = session.beginTransaction();
        			Query query = session.createQuery("select distinct disease_name from Tb_Med_Daily_Surv_Disease_Mstr where disease_type='NOTIFY' order by disease_name");
        			@SuppressWarnings("unchecked")
    				List<Tb_Med_Daily_Surv_Disease_Mstr> count = (List<Tb_Med_Daily_Surv_Disease_Mstr>) query.list();
        			tx.commit();
        			session.close();
        			return count;
        		}
            	
            //Dashboard 4 chart 1 Start here
           	 public String getDash41List() {
     			ArrayList<List<String>> listA = new ArrayList<List<String>>();
     			Connection conn = null;
     			try{	  
     				conn = dataSource.getConnection();
     				Statement stmt = (Statement)conn.createStatement();

     				String sql1="SELECT distinct upper(category) as cat, count(category) as count FROM tb_med_daily_disease_surv_details a,tb_miso_orbat_unt_dtl b where a.sus_no = b.sus_no and b.status_sus_no='Active' and extract(year from date_time_admission) = extract(year from now()) group by upper(category)";
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
            //Dashboard 4 Chart 1 End here
            
           	//Dashboard 4 Chart 2 Start here
        	 public String getDash42List() {
      			ArrayList<List<String>> listA = new ArrayList<List<String>>();
      			Connection conn = null;
      			try{	  
      				conn = dataSource.getConnection();
      				Statement stmt = (Statement)conn.createStatement();

      				String sql1="select distinct c.cmd_name as Command, count(*) as count from tb_med_daily_disease_surv_details a,tb_miso_orbat_unt_dtl b,orbat_view_cmd c where a.sus_no = b.sus_no and b.status_sus_no='Active' and c.cmd_code = substring(b.form_code_control,1,1) and extract(year from date_time_admission) = extract(year from now()) group by c.cmd_name";
      			    ResultSet rs1 = stmt.executeQuery(sql1);     
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
           	//Dashboard 4 Chart 2 End here
        	
        	//Dashboard 4 Chart 3 Start Here
        	    public String getDash43List() {
        			ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();
        				String sql1 = "SELECT distinct diagnosis as cat, count(*) as count FROM tb_med_daily_disease_surv_details a,tb_miso_orbat_unt_dtl b where a.sus_no = b.sus_no and b.status_sus_no='Active' group by diagnosis";
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
        				
        			
        				
        				if(i == 0) {
        					if(!command.equals("")) {
        						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        					}else{
        						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        					}
        				}else {
        					if(!command.equals("")) {
        						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        					}else{
        						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        					}
        				}
        			}
        			list += "]";
        			
        			return list;
        		}
        	    
	    //Graph - 5
        	    
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
         			    if(rs1.wasNull() == false) {
             				sql1="select distinct command as Command, count(*) as count from med_cmd_mn_count where yr = extract(year from now())-1 and command is not null group by command";
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
        	    
        	    //Corps
        	    public ArrayList<List<String>>  getCorpsWise_Count_List(String Command) {
        			if(Command.equals("MOD")) { Command = "0"; }
        			if(Command.equals("SC")) { Command = "1"; }
        			if(Command.equals("EC")) { Command = "2"; }
        			if(Command.equals("SWC")) { Command = "8"; }
        			if(Command.equals("ARTRAC")) { Command = "6"; }
        			if(Command.equals("UN")) { Command = "9"; }
        			if(Command.equals("SFC")) { Command = "A"; }
        			if(Command.equals("ANC")) { Command = "7"; }
        			if(Command.equals("CC")) { Command = "4"; }
        			if(Command.equals("NC")) { Command = "5"; }
        			if(Command.equals("WC")) { Command = "3"; }
        			ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();
        				String sql1 = "";
        				if(Command.equals("4")) {
        					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
            						"where yr = extract(year from now()) and command is not null \r\n" + 
            						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
            						"and substr(b.form_code_control,1,1) = '"+Command+"' and b.div_name is not null\r\n" + 
            						"and a.sus_no=b.sus_no\r\n" + 
            						"group by b.div_name";
        				}else {
        					sql1 = "select distinct b.coprs_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
        						"where yr = extract(year from now()) and command is not null \r\n" + 
        						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
        						"and substr(b.form_code_control,1,1) = '"+Command+"' and b.coprs_name is not null\r\n" + 
        						"and a.sus_no=b.sus_no\r\n" + 
        						"group by b.coprs_name";
        				}
        			    ResultSet rs1 = stmt.executeQuery(sql1);
        			    
        			    if(rs1.wasNull() == false) {
        			    	if(Command.equals("4")) {
            					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
                						"where yr = extract(year from now())-1 and command is not null \r\n" + 
                						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
                						"and substr(b.form_code_control,1,1) = '"+Command+"' and b.div_name is not null\r\n" + 
                						"and a.sus_no=b.sus_no\r\n" + 
                						"group by b.div_name";
            				}else {
            					sql1 = "select distinct b.coprs_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
            						"where yr = extract(year from now())-1 and command is not null \r\n" + 
            						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
            						"and substr(b.form_code_control,1,1) = '"+Command+"' and b.coprs_name is not null\r\n" + 
            						"and a.sus_no=b.sus_no\r\n" + 
            						"group by b.coprs_name";
            				}
    	 	 			    rs1 = stmt.executeQuery(sql1);
            				while(rs1.next()){
            					List<String> list = new ArrayList<String>();
            					list.add(rs1.getString("Command"));
            					list.add(rs1.getString("count"));
            					//list.add(rs1.getString("uh"));
            					listA.add(list);
            		        }
     				    }else {
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					list.add(rs1.getString("Command"));
        					list.add(rs1.getString("count"));
        					//list.add(rs1.getString("uh"));
        					listA.add(list);
        		        }
     				    }
        			    rs1.close();
        			    stmt.close();	     
        			}catch (SQLException e) {
        				e.printStackTrace();
        			}
        			return listA;
        	    }
        	    
        	    //Div wise
        	    public ArrayList<List<String>>  getDivsWise_Count_List(String Command) {
        			
        			ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();
        				String sql1 = "";
        				if(Command.equals("HQ UTTAR BHARAT AREA") || Command.equals("HQ MADHYA BHARAT AREA")) {
        					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
            						"where yr = extract(year from now()) and command is not null \r\n" + 
            						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
            						"and div_name = '"+Command+"' and b.div_name is not null\r\n" + 
            						"and a.sus_no=b.sus_no\r\n" + 
            						"group by b.div_name";
        				}else {
        					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
        						"where yr = extract(year from now()) and command is not null \r\n" + 
        						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
        						"and coprs_name = '"+Command+"' and b.div_name is not null\r\n" + 
        						"and a.sus_no=b.sus_no\r\n" + 
        						"group by b.div_name";
        				}
        			    ResultSet rs1 = stmt.executeQuery(sql1);
        			    
        			    if(rs1.wasNull() == false) {
        			    	if(Command.equals("HQ UTTAR BHARAT AREA") || Command.equals("HQ MADHYA BHARAT AREA")) {
            					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
                						"where yr = extract(year from now())-1 and command is not null \r\n" + 
                						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
                						"and div_name = '"+Command+"' and b.div_name is not null\r\n" + 
                						"and a.sus_no=b.sus_no\r\n" + 
                						"group by b.div_name";
            				}else {
            					sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
            						"where yr = extract(year from now())-1 and command is not null \r\n" + 
            						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
            						"and coprs_name = '"+Command+"' and b.div_name is not null\r\n" + 
            						"and a.sus_no=b.sus_no\r\n" + 
            						"group by b.div_name";
            				}
    	 	 			    rs1 = stmt.executeQuery(sql1);
    	 	 			  while(rs1.next()){
          					List<String> list = new ArrayList<String>();
          					list.add(rs1.getString("Command"));
          					list.add(rs1.getString("count"));
          					//list.add(rs1.getString("uh"));
          					listA.add(list);
          		        }
     				    }else {
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					list.add(rs1.getString("Command"));
        					list.add(rs1.getString("count"));
        					//list.add(rs1.getString("uh"));
        					listA.add(list);
        		        }
     				    }
        			    rs1.close();
        			    stmt.close();	     
        			}catch (SQLException e) {
        				e.printStackTrace();
        			}
        			return listA;
        	    }
        	    
        	    //Bde
        	    public ArrayList<List<String>>  getBdesWise_Count_List(String Command) {
        			
        			ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();
        				String sql1 = "";
        				ResultSet rs1 = null;
        				sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
        						"where yr = extract(year from now()) and command is not null \r\n" + 
        						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
        						"and div_name = '"+Command+"' and b.div_name is not null\r\n" + 
        						"and a.sus_no=b.sus_no\r\n" + 
        						"group by b.div_name";
        			    rs1 = stmt.executeQuery(sql1);   
        			    
        			    if(rs1.wasNull() == false) {
        			    	sql1 = "select distinct b.div_name as Command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n" + 
            						"where yr = extract(year from now())-1 and command is not null \r\n" + 
            						"and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
            						"and div_name = '"+Command+"' and b.div_name is not null\r\n" + 
            						"and a.sus_no=b.sus_no\r\n" + 
            						"group by b.div_name";
    	 	 			    rs1 = stmt.executeQuery(sql1);
    	 	 			  while(rs1.next()){
          					List<String> list = new ArrayList<String>();
          					list.add(rs1.getString("Command"));
          					list.add(rs1.getString("count"));
          					//list.add(rs1.getString("uh"));
          					listA.add(list);
          		        }	
     				    }else {
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					list.add(rs1.getString("Command"));
        					list.add(rs1.getString("count"));
        					//list.add(rs1.getString("uh"));
        					listA.add(list);
        		        }	
     				    }
        			    rs1.close();
        			    stmt.close();	     
        			}catch (SQLException e) {
        				e.printStackTrace();
        			}
        			return listA;
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
        				
        				if(rs1.wasNull() == false) {
        					sql1="select distinct disease_principal as b_desc, count(*) as count from med_cmd_mn_count where yr = extract(year from now())-1 group by disease_principal order by disease_principal";
    	 	 			    rs1 = stmt.executeQuery(sql1);
	        				while(rs1.next()){
	        					List<String> list = new ArrayList<String>();
	        					
	        					list.add(rs1.getString("b_desc"));
	        					list.add(rs1.getString("count"));
	        					//list.add(rs1.getString("total"));
	        					lista.add(list);
	        		        }
     				    }else {
	        				while(rs1.next()){
	        					List<String> list = new ArrayList<String>();
	        					
	        					list.add(rs1.getString("b_desc"));
	        					list.add(rs1.getString("count"));
	        					//list.add(rs1.getString("total"));
	        					lista.add(list);
	        		        }	
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
        	    
        	    //Report cmd corps 
        	    public DataSet<Medical_Cmd_Corp_Disease_Details_View> DatatablesCriteriasCmdCorps(DatatablesCriterias criterias,String whr) {
                    List<Medical_Cmd_Corp_Disease_Details_View> metadata = findCmdCorpsCriteriasihd(criterias ,whr);
               		Long countFiltered = getFilteredCmdCorpsihd(criterias ,whr);
               		Long count = getTotalCmdCorpsihd(whr);
               		return new DataSet<Medical_Cmd_Corp_Disease_Details_View>(metadata, count, countFiltered);
               	}
        	    
               	@SuppressWarnings("unchecked")
               	private List<Medical_Cmd_Corp_Disease_Details_View> findCmdCorpsCriteriasihd(DatatablesCriterias criterias,String whr) {
               		StringBuilder queryBuilder = null;
               		queryBuilder = new StringBuilder("SELECT d FROM Medical_Cmd_Corp_Disease_Details_View d " +whr);
               		queryBuilder.append(getFilterQueryhd(criterias , queryBuilder));
               		if (criterias.hasOneSortedColumn()) {
               			List<String> orderParams = new ArrayList<String>();
               			queryBuilder.append(" ORDER BY ");
               			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
               				if(columnDef.getName().equals(""))
               				{
               					String st=" ORDER BY";
               					int i = queryBuilder.indexOf(st);
               					if (i != -1) {
               						queryBuilder.delete(i, i + st.length());
               					}
               				}
               				else if(columnDef.getName().contains("hodProfile.fullName")){
               					 orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
               				}
               				else{
               					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
               				}
               			}
               			Iterator<String> itr2 = orderParams.iterator();
               			while (itr2.hasNext()) {
               				queryBuilder.append(itr2.next());
               				if (itr2.hasNext()) {
               					queryBuilder.append(" , ");
               				}
               			}
               		}
               		Session session= HibernateUtil.getSessionFactory().openSession();
               		Transaction tx = session.beginTransaction();
               		Query q = session.createQuery(queryBuilder.toString());
               		q.setFirstResult(criterias.getDisplayStart());
               		q.setMaxResults(criterias.getDisplaySize());
               		List<Medical_Cmd_Corp_Disease_Details_View> list = (List<Medical_Cmd_Corp_Disease_Details_View>) q.list();
               		tx.commit();
               		session.close();
               		return list;
               	}
               	
               	@SuppressWarnings("unchecked")
            	private Long getFilteredCmdCorpsihd(DatatablesCriterias criterias,String whr) {
            		StringBuilder queryBuilder = null;
            		queryBuilder = new StringBuilder("SELECT d FROM Medical_Cmd_Corp_Disease_Details_View d " +whr);
            		queryBuilder.append(getFilterQueryhd(criterias,queryBuilder));
            		Session session= HibernateUtil.getSessionFactory().openSession();
            		Transaction tx = session.beginTransaction();
            		Query q = session.createQuery(queryBuilder.toString());
            		List<Medical_Cmd_Corp_Disease_Details_View> list = (List<Medical_Cmd_Corp_Disease_Details_View>) q.list();
            		tx.commit();
            		session.close();
            		return Long.parseLong(String.valueOf(list.size()));
            	}
               	
               	private Long getTotalCmdCorpsihd(String whr) {
            		Session session= HibernateUtil.getSessionFactory().openSession();
            		Transaction tx = session.beginTransaction();
            		Query q = null;
            		q = session.createQuery("SELECT COUNT(d) FROM Medical_Cmd_Corp_Disease_Details_View d " +whr);
            		Long count = (Long) q.list().get(0);
            		tx.commit();
            		session.close();
            		return count;
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
        				sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where service in ('XR', 'XE','XF','XH','XN','XS') and (category is not null or category <> '') and yr = extract(year from now()) group by upper(category)";
        			    rs1 = stmt.executeQuery(sql1); 
        			    
        			    if(rs1.wasNull() == false) {
        			    	sql1="select distinct upper(category) as cat, count(category) as count from med_cmd_mn_count where service in ('XR', 'XE','XF','XH','XN','XS') and (category is not null or category <> '') and yr = extract(year from now())-1 group by upper(category)";
             			    rs1 = stmt.executeQuery(sql1);
             			   while(rs1.next()){
           					List<String> list = new ArrayList<String>();
           					list.add(rs1.getString("cat"));
           					list.add(rs1.getString("count"));
           					listA.add(list);
             			   }	
         			    }else {
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					list.add(rs1.getString("cat"));
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
        				String command ="";
        				//if(listA.get(i).get(0).equals(null)) { command = "OR"; }
        				//if(listA.get(i).get(0).equals("Officer")) { command = "OFFR"; }
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
                
                //Chart 4 Principal Cause 
                public String getChart4PCList() {
                	ArrayList<List<String>> listA = new ArrayList<List<String>>();
        			Connection conn = null;
        			try{	  
        				conn = dataSource.getConnection();
        				Statement stmt = (Statement)conn.createStatement();
        				String sql1="";
        				ResultSet rs1 = null;
        				sql1="select distinct disease_principal as cat, count(*) as count from med_cmd_mn_count where yr = extract(year from now()) and disease_principal is not null group by disease_principal order by count desc limit 10";
        			    rs1 = stmt.executeQuery(sql1);   
        			    
        			    if(rs1.wasNull() == false) {
        			    	sql1="select distinct disease_principal as cat, count(*) as count from med_cmd_mn_count where yr = extract(year from now())-1 and disease_principal is not null group by disease_principal order by count desc limit 10";
             			    rs1 = stmt.executeQuery(sql1);
             			   while(rs1.next()){
           					List<String> list = new ArrayList<String>();
           					list.add(rs1.getString("cat"));
           					list.add(rs1.getString("count"));
           					listA.add(list);
             			   }
         			    }else {
        				while(rs1.next()){
        					List<String> list = new ArrayList<String>();
        					list.add(rs1.getString("cat"));
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
        				String command ="";
        				
        				if(i == 0) {
        						list +="{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        				}else {
        						list +=",{'cat' : '"+listA.get(i).get(0)+"' ,'count' : "+listA.get(i).get(1)+"}";
        				}
        			}
        			list += "]";
        			
        			return list;
        		}
        	 
             //Graph serving & esm refresh End Here
                
                public ArrayList<ArrayList<String>> getMMSRData(int userid) {
            		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
            		Connection conn = null;
            		try{
            			conn = dataSource.getConnection();
            			String sql1="";
            		    String sql2="";
            		    String nSUS="";
            	    	String nRoleType="";
            	    	String nRoleAccess="";
            	    	String nRoleAccess1="";
            	    	String nRoleAccess2="";
            	    	String nCnd="";
            	    	String nfmncode="";
            	        String narmcode="";
            	        
            	        sql1 = "select a.userid,a.username,a.user_sus_no,a.user_formation_no,a.user_arm_code,d.role_id,c.role_type,c.access_lvl,c.sub_access_lvl,c.staff_lvl from"; 
            		    sql1 = sql1 + " (select distinct userid,username,user_sus_no,user_formation_no,user_arm_code from logininformation where userid=?) a";
            		    sql1 = sql1 + " left join (select distinct user_id,role_id from userroleinformation) d on a.userid=d.user_id";
            		    sql1 = sql1 + " left join (select role_id,role_type,access_lvl,sub_access_lvl,staff_lvl from roleinformation) c on d.role_id=c.role_id";
            		    
            		    PreparedStatement stmt = conn.prepareStatement(sql1);
            	        stmt.setInt(1, userid);
            	        ResultSet rs = stmt.executeQuery();
            	        
            	        while(rs.next()){
            	        	nSUS=rs.getString("user_sus_no");
            	        	nRoleAccess=rs.getString("access_lvl");
            	        	nRoleType=rs.getString("role_type");
            	        	if (nRoleType==null || nRoleType.equals(null)) { 
            			    	nRoleType="DEO";
            			    }	
            			    nRoleAccess1=rs.getString("sub_access_lvl");
            			    nRoleAccess2=rs.getString("staff_lvl");
            			    nfmncode=rs.getString("user_formation_no");
            			    narmcode=rs.getString("user_arm_code");
            			}        
            			
            	        rs.close();
            	        stmt.close();
            	        
            	        if(nRoleAccess.equalsIgnoreCase("UNIT")){
            	        	nCnd=" and a.sus_no=?";//nSUS
            	    	}
            	    	if(nRoleAccess.equalsIgnoreCase("MISO")){
            	    		nCnd="";
            	    	}
            	    	if(nRoleAccess.equalsIgnoreCase("FORMATION")){
            	    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
            		    		nCnd=" and substring(a.form_code_control,1,1)=?";
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
            	    			nCnd=" and substring(a.form_code_control,1,3)=?";
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
            	    			nCnd=" and substring(a.form_code_control,1,6)=?";
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
            	    			nCnd=" and a.form_code_control=?";
            	    		}
            	    	}
            	    	if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
            	    		nCnd=" and a.arm_code=?";//narmcode
            	    	}
            	    	
            	    	sql2 = "select distinct upper(COALESCE(b.level_in_hierarchy,'UNIT')) as lvl,a.sus_no,a.unit_name,a.form_code_control,a.arm_code from tb_miso_orbat_unt_dtl a left join tb_miso_orbat_codesform b on a.sus_no=b.sus_no where a.status_sus_no='Active' "; 
            		    sql2 = sql2 + nCnd;
            		    sql2 = sql2 +" order by a.form_code_control,a.sus_no";
            		   
            		    PreparedStatement stmt2 = conn.prepareStatement(sql2);
            		    
            		    if(nRoleAccess.equalsIgnoreCase("UNIT")){
            		    	stmt2.setString(1, nSUS);
            		    }
            		    if(nRoleAccess.equalsIgnoreCase("MISO")){
            	    	}
            		    if(nRoleAccess.equalsIgnoreCase("FORMATION")){
            	    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
            		    		stmt2.setString(1, nfmncode.substring(0, 1));
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
            	    			stmt2.setString(1, nfmncode.substring(0, 3));
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
            	    			stmt2.setString(1, nfmncode.substring(0, 6));
            	    		}
            	    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
            	    			stmt2.setString(1, nfmncode);
            	    		}
            	    	}
            		    if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
            		    	stmt2.setString(1, narmcode);
            		    }
            		    
            	        ResultSet rs2 = stmt2.executeQuery();
            	        
            	        if(!rs2.isBeforeFirst()) {	
            				ArrayList<String> list = new ArrayList<String>();
            				list.add("NIL");
            				li.add(list); 
            			}else{
            				while(rs2.next()){
            					ArrayList<String> list = new ArrayList<String>();
            					if(nRoleAccess.equalsIgnoreCase("UNIT")){
            						list.add("1");
            						list.add("UNIT");
            				    }
            					if(nRoleAccess.equalsIgnoreCase("MISO")){
            						list.add("9");
            						list.add("MISO");
            			    	}
            					if(nRoleAccess.equalsIgnoreCase("FORMATION")){
            			    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
            			    			list.add("5");
            			    			list.add("COMMAND");
            			    		}
            			    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
            			    			list.add("4");
            			    			list.add("CORPS");
            			    		}
            			    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
            			    			list.add("3");
            			    			list.add("DIVISION");
            			    		}
            			    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
            			    			list.add("2");
            			    			list.add("BRIGADE");
            			    		}
            			    	}
            					if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
            						list.add("6");
            						list.add("LINE_DTE");
            				    }
            					list.add(rs2.getString("sus_no"));
            					list.add(rs2.getString("unit_name"));
            					list.add(rs2.getString("form_code_control"));
            					list.add(rs2.getString("arm_code"));
            					list.add(rs2.getString("lvl"));
            					list.add(nRoleAccess.toUpperCase());
            					list.add(nRoleType.toUpperCase());					
            					li.add(list); 
            				}
            			}
            	        
            	        rs2.close();    
            	        stmt2.close();
            	        conn.close();
            	        
            		}catch(SQLException e){
            	    	e.printStackTrace();
            	    }
            		
            		return li;
            	}      
                
                //LMC Chart
             	 public String getLMCList() {
          			ArrayList<List<String>> listA = new ArrayList<List<String>>();
          			Connection conn = null;
          			try{	  
          				conn = dataSource.getConnection();
          				Statement stmt = (Statement)conn.createStatement();

          				//String sql1="select distinct a.category as cat,count(a.*) as count,sum(b.off_male+b.off_female+b.cadet+b.mns+b.mns_cadet) as off_str, sum(b.jco+b.ort+b.dsc_jco+b.dsc_or+b.rect) as jco_or_str,c.cmd_name as Command\r\n" + 
          					//	"from tb_med_low_medical_category a,orbat_view_cmd c, tb_med_strength b where c.cmd_code=substring(b.cmd,1,1) group by a.category,c.cmd_name order by c.cmd_name,a.category\r\n";
          				String sql1="select distinct c.cmd_name as Command,count(a.*) as count,sum(b.off_male+b.off_female+b.cadet+b.mns+b.mns_cadet) as off_str,sum(b.jco+b.ort+b.dsc_jco+b.dsc_or+b.rect) as jco_or_str from tb_med_low_medical_category a,orbat_view_cmd c, tb_med_strength b where c.cmd_code=substring(b.cmd,1,1) group by a.category,c.cmd_name order by c.cmd_name";
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
             	 
             	 //Daily Bed State
             	public String getLMCChart1List() {
         			ArrayList<List<String>> listA = new ArrayList<List<String>>();
         			Connection conn = null;
         			try{	  
         				conn = dataSource.getConnection();
         				Statement stmt = (Statement)conn.createStatement();
         				String sql1="";
         				ResultSet rs1 = null;
         				sql1="select sum(off_army) as off,sum(jco_or_army) as jco_or from tb_med_daily_bed_occupancy where dt = date(now())-1 ";
         			    rs1 = stmt.executeQuery(sql1);
         			    if(rs1.wasNull() == false) {
             				sql1="select sum(off_army) as off,sum(jco_or_army) as jco_or from tb_med_daily_bed_occupancy";
             			    rs1 = stmt.executeQuery(sql1);
             			   while(rs1.next()){
            					List<String> list = new ArrayList<String>();
            					list.add(rs1.getString("off"));
            					list.add(rs1.getString("jco_or"));
            					listA.add(list);
            		        }	
         			    }else {
         				while(rs1.next()){
         					List<String> list = new ArrayList<String>();
         					list.add(rs1.getString("off"));
        					list.add(rs1.getString("jco_or"));
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
        				//String Command ="";
       			
    					if(i == 0) {
        					
        						list +="{'off' : '"+listA.get(i).get(0)+"' ,'jco_or' : "+listA.get(i).get(1)+"}";
        					
        				}
        			}
         			list += "]";
         			
         			return list;
         		}
             	
           //Data Approved Status
             	 public  ArrayList<List<String>> dataappstatusList() {
                 	ArrayList<List<String>> lista = new ArrayList<List<String>>();
             		Connection conn = null;
             		try{	  
             			conn = dataSource.getConnection();
             			Statement stmt = (Statement)conn.createStatement();
             			String sql1 =	"select distinct patient_status as patient_status, count(apprvr_at_unit_by) as unit_app, count(fmn_approved_by) as fmn_app, count(apprvr_at_miso_by) as miso_app from tb_med_patient_details where extract(year from admsn_date)=2019 group by patient_status";
             			ResultSet rs1 = stmt.executeQuery(sql1);     
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
             			String sql1 =	"select distinct c.cmd_name as Command, f.month as mn, f.year as yr, f.count as count, f.cnt_sus as cnt_sus from \r\n" + 
             					" (select distinct substr(b.form_code_control,1,1) as Command ,a.month,a.year, count(a.*) as count, count(substring(b.sus_no,1,4)) as cnt_sus\r\n" + 
             					" from tb_med_upload_data a, tb_miso_orbat_unt_dtl b where a.year = '2020' and a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747')\r\n" + 
             					" group by b.form_code_control ,a.month,a.year) f inner join orbat_view_cmd c on c.cmd_code = f.Command";
             		
             			ResultSet rs1 = stmt.executeQuery(sql1);     
             			while(rs1.next()){
             				List<String> list = new ArrayList<String>();
             				list.add(rs1.getString("Command"));
             				list.add(rs1.getString("mn"));
             				list.add(rs1.getString("yr"));
             				list.add(rs1.getString("count"));
             				list.add(rs1.getString("cnt_sus"));
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
}
*/