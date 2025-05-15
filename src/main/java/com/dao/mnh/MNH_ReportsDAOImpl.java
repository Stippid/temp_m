package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.formula.functions.Count;

public class MNH_ReportsDAOImpl implements MNH_ReportsDAO {
	private DataSource dataSource;
		
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public List<Map<String, Object>> search_opd_bed_strength_report(String cmd1, String qtr1, String yr1, String para1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			String tbl_name = null;
			
			if(!cmd1.equals("ALL")){
				qry += " and substr(a.form_code_control,?,?)=?";
		 	} 
			
			if(!qtr1.equals("-1")){
				qry += " and r.qtr=?";
			}
			
			if(!yr1.equals("")){
				qry += " and r.year=?";
			}
			
			if(para1.equalsIgnoreCase("R1")){
				/*q = "select distinct a.unit_name,r.officer_self,r.officer_family,r.jco_ors_self,r.jco_ors_family,r.ex_serv_self,r.ex_serv_family,r.para_mil_pers_self,"
			 			+ "r.para_mil_pers_family,r.civilian_self,r.civilian_family,r.total_during_month,r.remarks from tb_med_opdcases r "
			 			+ "left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no "
			 			+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
			 			+ "where a.status_sus_no='Active' and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)"+qry;*/
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				q = "select distinct a.unit_name,r.officer_self,r.officer_family,r.jco_ors_self,r.jco_ors_family,r.ex_serv_self,r.ex_serv_family,r.para_mil_pers_self,"
			 			+ "r.para_mil_pers_family,r.civilian_self,r.civilian_family,r.total_during_month,r.remarks from tb_med_opdcases r "
			 			+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
			 			+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
			 			+ "where a.status_sus_no='Active' "+qry;
		 	}
			
			if(para1.equalsIgnoreCase("R2")){
				/*q = "select distinct a.unit_name,s.off_auth as offrs,s.jco_or_auth as jcos,s.off_fam_auth,s.jco_or_fam_auth,s.total_all,r.ofcr_max,r.ofcr_avg,r.jco_max,"
						+ "r.jco_avg,r.ofcr_fmly_max,r.ofcr_fmly_avg,r.jco_fmly_max,r.jco_fmly_avg,(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,"
						+ "(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg) as tot_avg,"
						+ "round(((r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max)*100.0)/s.total_all,2) as per_max,"
						+ "round(((r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg)*100.0)::numeric/s.total_all,2) as per_avg,r.remarks from tb_med_bed_serving r "
						+ "left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no "
						+ "left join tb_med_authorisation_all s on s.sus_no = r.sus_no "
						+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
						+ "where a.status_sus_no='Active' and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?) "+qry;*/
				
				/*q = "select distinct a.unit_name,s.off_auth as offrs,s.jco_or_auth as jcos,s.off_fam_auth,s.jco_or_fam_auth,s.total_all,r.ofcr_max,r.ofcr_avg,r.jco_max,"
						+ "r.jco_avg,r.ofcr_fmly_max,r.ofcr_fmly_avg,r.jco_fmly_max,r.jco_fmly_avg,(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,"
						+ "(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg) as tot_avg,"
						+ "round(((r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max)*100.0)/s.total_all,2) as per_max,"
						+ "round(((r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg)*100.0)::numeric/s.total_all,2) as per_avg,r.remarks from tb_med_bed_serving r "
						+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
						+ "left join tb_med_authorisation_all s on s.sus_no = r.sus_no "
						+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
						+ "where a.status_sus_no='Active' "+qry;*/
				q = "select distinct a.unit_name,s.off_auth as offrs,s.jco_or_auth as jcos,s.off_fam_auth,s.jco_or_fam_auth,\r\n" + 
						"s.total_all,r.ofcr_max,\r\n" + 
						"ROUND(cast(r.ofcr_avg as numeric),2) as ofcr_avg,\r\n" + 
						"r.jco_max,\r\n" + 
						"ROUND(cast(r.jco_avg as numeric),2) as jco_avg,\r\n" + 
						"r.ofcr_fmly_max,\r\n" + 
						"ROUND(cast(r.ofcr_fmly_avg as numeric),2) as ofcr_fmly_avg,\r\n" + 
						"r.jco_fmly_max,\r\n" + 
						"ROUND(cast(r.jco_fmly_avg as numeric),2) as jco_fmly_avg,\r\n" + 
						"(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,\r\n" + 
						"ROUND(cast(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg as numeric),2) as tot_avg,\r\n" + 
						"round(((r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max)*100.0)/s.total_all,2) as per_max,\r\n" + 
						"round(((r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg)*100.0)::numeric/s.total_all,2) as per_avg,r.remarks "
						+ "from tb_med_bed_serving r "
						+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
						+ "left join tb_med_authorisation_all s on s.sus_no = r.sus_no "
						+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
						+ "where a.status_sus_no='Active' "+qry;
			}
			
			if(para1.equalsIgnoreCase("R3")){
		 	/*	q = "select distinct a.unit_name,r.ofcr_max,r.ofcr_avg,r.jco_max,r.jco_avg,r.ofcr_fmly_max,r.ofcr_fmly_avg,r.jco_fmly_max,r.jco_fmly_avg,"
		 				+ "(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg) as tot_avg,"
		 				+ "r.need_exms_adm,r.need_exms_bed_days,r.no_of_fmly_ref_adm,r.remarks from tb_med_bed_ex_servicemen r "
		 				+ "left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no "
		 				+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
		 				+ "where a.status_sus_no='Active' and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?) "+qry;*/
				
				/*q = "select distinct a.unit_name,r.ofcr_max,r.ofcr_avg,r.jco_max,r.jco_avg,r.ofcr_fmly_max,r.ofcr_fmly_avg,r.jco_fmly_max,r.jco_fmly_avg,"
		 				+ "(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg) as tot_avg,"
		 				+ "r.need_exms_adm,r.need_exms_bed_days,r.no_of_fmly_ref_adm,r.remarks from tb_med_bed_ex_servicemen r "
		 				+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
		 				+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
		 				+ "where a.status_sus_no='Active' "+qry;*/
		 		q="select distinct a.unit_name,r.ofcr_max,\r\n" + 
		 				"	ROUND(cast(r.ofcr_avg as numeric),2) as ofcr_avg,\r\n" + 
		 				"	r.jco_max,\r\n" + 
		 				"	ROUND(cast(r.jco_avg as numeric),2) as jco_avg,\r\n" + 
		 				"	r.ofcr_fmly_max,\r\n" + 
		 				"	ROUND(cast(r.ofcr_fmly_avg as numeric),2) as ofcr_fmly_avg,\r\n" + 
		 				"	r.jco_fmly_max,\r\n" + 
		 				"	ROUND(cast(r.jco_fmly_avg as numeric),2) as jco_fmly_avg,\r\n" + 
		 				"	(r.ofcr_max+r.jco_max+r.ofcr_fmly_max+r.jco_fmly_max) as tot_max,\r\n" + 
		 				"	ROUND(cast(r.ofcr_avg+r.jco_avg+r.ofcr_fmly_avg+r.jco_fmly_avg as numeric),2) as tot_avg,\r\n" + 
		 				"	r.need_exms_adm,r.need_exms_bed_days,r.no_of_fmly_ref_adm,r.remarks"
		 				+ " from tb_med_bed_ex_servicemen r "
		 				+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
		 				+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
		 				+ "where a.status_sus_no='Active' "+qry;
		 	}
			
			if(para1.equalsIgnoreCase("R4")){
		 		/*q = "select distinct c.unit_name,(r.off_male+r.off_female+r.cadet+r.lady_cadet+r.mns+r.mns_cadet+r.high_offr) as total_off,"
		 				+ "round((r.off_male+r.off_female+r.cadet+r.lady_cadet+r.mns+r.mns_cadet+r.high_offr)/1000.0,7) as rate_off,"
		 				+ "(r.jco+r.ort+r.dsc_jco+r.dsc_or+r.rect+r.high_jco+r.high_or) as total_jco,"
		 				+ "round((r.jco+r.ort+r.dsc_jco+r.dsc_or+r.rect+r.high_jco+r.high_or)/1000.0,7) as rate_jco,r.total,round((r.total/1000.0),7) as total_rate,"
		 				+ "(r.cadet+r.lady_cadet+r.mns_cadet) as total_cadet,round((r.cadet+r.lady_cadet+r.mns_cadet)/1000.0,7) as rate_cadet,"
		 				+ "r.rect,round((r.rect/1000.0),7) as rate_rect,(r.cadet+r.lady_cadet+r.mns_cadet+r.rect) as total_cadet_rect,"
		 				+ "round((r.cadet+r.lady_cadet+r.mns_cadet+r.rect)/1000.0,7) as rate_cadet_rect from tb_med_strength r,tb_miso_orbat_unt_dtl a "
		 				+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
		 				+ "where a.status_sus_no='Active' and substring(a.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)"+qry;*/
				
				q = "select distinct c.unit_name,(r.off_male+r.off_female+r.cadet+r.lady_cadet+r.mns+r.mns_cadet+r.high_offr) as total_off,"
		 				+ "round((r.off_male+r.off_female+r.cadet+r.lady_cadet+r.mns+r.mns_cadet+r.high_offr)/1000.0,7) as rate_off,"
		 				+ "(r.jco+r.ort+r.dsc_jco+r.dsc_or+r.rect+r.high_jco+r.high_or) as total_jco,"
		 				+ "round((r.jco+r.ort+r.dsc_jco+r.dsc_or+r.rect+r.high_jco+r.high_or)/1000.0,7) as rate_jco,r.total,round((r.total/1000.0),7) as total_rate,"
		 				+ "(r.cadet+r.lady_cadet+r.mns_cadet) as total_cadet,round((r.cadet+r.lady_cadet+r.mns_cadet)/1000.0,7) as rate_cadet,"
		 				+ "r.rect,round((r.rect/1000.0),7) as rate_rect,(r.cadet+r.lady_cadet+r.mns_cadet+r.rect) as total_cadet_rect,"
		 				+ "round((r.cadet+r.lady_cadet+r.mns_cadet+r.rect)/1000.0,7) as rate_cadet_rect from tb_med_strength r,orbat_all_details_view_mnh a "
		 				+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
		 				+ "where a.status_sus_no='Active' "+qry;
		 	}
			
		 	stmt=conn.prepareStatement(q);
			stmt.setInt(1, 1);
		    stmt.setInt(2, 1);
		    stmt.setString(3, "000000000");
		   /* stmt.setInt(4, 1);
	    	stmt.setInt(5, 4);
	    	stmt.setString(6, "9609");
	    	stmt.setString(7, "9709");
	    	stmt.setString(8, "3742");
	    	stmt.setString(9, "6323");
	    	stmt.setString(10, "3755");
	    	stmt.setString(11, "3738");
	    	stmt.setString(12, "3735");
	    	stmt.setString(13, "3747");
	    	stmt.setString(14, "1234");*/
	    	
	    	int j = 4;
	    	if(!cmd1.equals("ALL")){
	    		stmt.setInt(j, 1);
	    		j++;
			    stmt.setInt(j, 1);
			    j++;
		    	stmt.setString(j, cmd1.substring(0,1));
		    	j++;
		    }
	    	
	    	if(!qtr1.equals("-1")) {
	    		stmt.setString(j, qtr1);
	    		j++;
	    	}
	    	
	    	if(!yr1.equals("")) {
	    		stmt.setInt(j, Integer.parseInt(yr1));
	    		j++;
	    	}
	    		 
		 	ResultSet rs = stmt.executeQuery();      
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for(int i = 1; i <= columnCount; i++){
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
 	            list.add(columns);
		 	 }
		     rs.close();
		     stmt.close();
		     conn.close();
	     }catch(SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch(SQLException e){
				 }
			 }
		 }
		 return list;
	}
	
	
	
	
	public List<Map<String, Object>> getsearch_mmr_report(String command,String mth_yr1,String disease_principal1,String disease_mmr1,String disease_aso1,String block_description1,String icd_code) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		
		String mnth[] = mth_yr1.split("-");
		
		int month1 = Integer.parseInt(mnth[1]) - 1 ;
		String current_quarter="";
		String previous_quarter="";
		String Year="";
		String previous_Year="";
		String cmd_parameter="";
		String cmd_parameter1="";
		String qry_icdcause="";
		try{
			
			conn = dataSource.getConnection();		
			current_quarter= getQuarter(mnth[1]); //To Get the Quarter of Month
			previous_quarter= getQuarter(String.valueOf(month1)); //To Get the Quarter of Month
			
			Year=(mnth[0]);
			previous_Year =(mnth[0]);
			
			
			
			String cmd1 = command.substring(0,1);
			//IF CURRENT QUARTER DATA IS NOT IN STRENGTH TABLE THEN IT WILL RETURN LAST QUARTER DATA FOR CURRENT QUARTER
			Boolean qtr_str = Chk_qtr_strength(current_quarter,mnth[0],command);
            if(qtr_str == false)
            {
                    
                    String sql = "";
                    PreparedStatement stmt_q = null;
                  
                    
                    if(!command.equals("ALL"))
                    {
                    	 sql = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
              
                    }
                    if(command.equals("ALL"))
                    {
                    	 sql = "select qtr,year from tb_med_strength where   \r\n" + 
                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
              
                    }
    
                      
                    
                    stmt_q = conn.prepareStatement(sql);
                    int s=1;
                    if(!command.equals("ALL"))
                    {
                    	 stmt_q.setString(1, command);
                    	 s++;
                    }
                    stmt_q.setInt(s, Integer.parseInt(mnth[0]));
                    s++;
                    stmt_q.setInt(s, ((Integer.parseInt(mnth[0]) -1)));
                    
                    ResultSet rs1_q = stmt_q.executeQuery();
                    while (rs1_q.next()) {
                    		current_quarter = rs1_q.getString("qtr");
                            Year= String.valueOf(rs1_q.getInt("year"));
                    }
                    rs1_q.close();
                   
            }
            
            
          //FOR PREVIOUS QUARTER
			Boolean qtr_str_p = Chk_qtr_strength(previous_quarter,mnth[0],command);
            if(qtr_str_p == false)
            {
                    
                    String sql_p = "";
                    PreparedStatement stmt_q_p = null;
                    
                    if(!command.equals("ALL"))
                    {
                    	 sql_p = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
             
                    }
                    if(command.equals("ALL"))
                    {
                    	 sql_p = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
             
                    }
                    
                  
                    stmt_q_p = conn.prepareStatement(sql_p);
                    int t=1;
                    if(!command.equals("ALL"))
                    {
                    	  stmt_q_p.setString(1, command);
                    	  t++;
                    }
                  
                    stmt_q_p.setInt(t, Integer.parseInt(mnth[0]));
                    t++;
                    stmt_q_p.setInt(t, ((Integer.parseInt(mnth[0]) -1)));
                    
                    ResultSet rs1_q_p = stmt_q_p.executeQuery();
                    while (rs1_q_p.next()) {
                    		previous_quarter = rs1_q_p.getString("qtr");
                    		previous_Year= String.valueOf(rs1_q_p.getInt("year"));
                    }
                    rs1_q_p.close();
                   
            }
				
			//where condition (and c.disease_principal = 'NEOPLASMS')
            if(!command.equals("ALL"))
            {
            	cmd_parameter1=" and substring(cmd,1,1) = substring(?,1,1) ";
            }
            
            if((disease_mmr1.equals("EFFECTS OF ALTITUDE") || disease_mmr1.equals("EFFECTS OF HEAT")
            	|| disease_mmr1.equals("EFFECTS OF COLD")  || disease_mmr1.equals("INJURIES NEA")))
			{
        		qry1 = " inner join  tb_med_d_disease_cause c on (case when (a.icd_cause_code1d is null or a.icd_cause_code1d = '') then "
        				+ " a.icd_cause_code1a else a.icd_cause_code1d end) = c.icd_code ";
			}
            else   {
            	qry1 = " inner join  tb_med_d_disease_cause c on (case when (a.diagnosis_code1d is null or a.diagnosis_code1d = '') then "
        				+ " a.diagnosis_code1a else a.diagnosis_code1d end) = c.icd_code  ";
            }
            
            
            if(!disease_principal1.equals("-1"))
            {
            	qry_icdcause="and upper(c.disease_principal) =upper(?)";
            }
            if(!disease_mmr1.equals("-1"))
            {
            	qry_icdcause+="and upper(c.disease_mmr) =upper(?)";
            }
            if(!disease_aso1.equals("-1"))
            {
            	qry_icdcause+="and upper(c.disease_aso) =upper(?)";
            }
            if(!block_description1.equals("-1"))
            {
            	qry_icdcause+="and upper(c.block_description) =upper(?)";
            }
            
  
          
           
		
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
		

			
			/* q= "select c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
			 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
			 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
			 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
			 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
			 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
			 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
			 		"\r\n" + 
			 		"from\r\n" + 
			 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
			 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
			 		"\r\n" + 
			 		"from \r\n" + 
			 		"tb_med_patient_details a\r\n" + 
			 		"inner join tb_miso_orbat_unt_dtl b on a.medical_unit = b.sus_no and b.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') "
			 		+" " +cmd_parameter+"  \r\n" + 
			 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by != '')\r\n" + 
			 		"and \r\n" + 
			 		"(CONCAT('20', substr(cast(a.and_no as character varying),14,2))=?\r\n" + 
			 		"and substr(cast(a.and_no as character varying),11,2)= ?)\r\n" + 
			 		" "+qry1+" "+qry_icdcause+" \r\n" + 
			 		" group by 1,2)  c_m\r\n" + 
			 		"\r\n" + 
			 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 +
			 		"group by 1,2,3,4,5\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"union\r\n" + 
			 		"\r\n" + 
			 		"select  c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
			 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
			 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
			 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
			 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
			 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
			 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
			 		"from\r\n" + 
			 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
			 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
			 		"\r\n" + 
			 		"from \r\n" + 
			 		"tb_med_patient_details a\r\n" + 
			 		"inner join tb_miso_orbat_unt_dtl b on a.medical_unit = b.sus_no and b.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') "
			 		+" " +cmd_parameter+"  \r\n" + 
			 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by != '')\r\n" + 
			 		"and \r\n" + 
			 		"(CONCAT('20', substr(cast(a.and_no as character varying),14,2))=?\r\n" + 
			 		"and substr(cast(a.and_no as character varying),11,2) = ?)\r\n" + 
			 		" "+qry1+" "+qry_icdcause+" \r\n" + 
			 		" group by 1,2)  c_m\r\n" + 
			 		"\r\n" + 
			 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 + 
			 		"group by 1,2,3,4,5\r\n" + 
			 		"\r\n" + 
			 		"union\r\n" + 
			 		"\r\n" + 
			 		"select c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
			 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
			 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
			 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
			 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
			 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
			 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
			 		"from\r\n" + 
			 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
			 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
			 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
			 		"\r\n" + 
			 		"from \r\n" + 
			 		"tb_med_patient_details a\r\n" + 
			 		"inner join tb_miso_orbat_unt_dtl b on a.medical_unit = b.sus_no and b.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') "
			 		+" " +cmd_parameter+"  \r\n" + 
			 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by!= '')\r\n" + 
			 		"and \r\n" + 
			 		" (CONCAT('20', substr(cast(a.and_no as character varying),14,2))=? \r\n" + 
			 		"and substr(cast(a.and_no as character varying),11,2)= ?)\r\n" + 
			 		" "+qry1+" "+qry_icdcause+" \r\n" + 
			 		" group by 1,2)  c_m\r\n" + 
			 		"\r\n" + 
			 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 +  
			 		"group by 1,2,3,4,5\r\n" + 
			 		"\r\n" + 
			 		"";*/
			
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			 q= "select c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
				 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
				 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
				 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
				 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
				 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
				 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
				 		"\r\n" + 
				 		"from\r\n" + 
				 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
				 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
				 		"\r\n" + 
				 		"from \r\n" + 
				 		"tb_med_patient_details a\r\n" + 
				 		"inner join orbat_all_details_view_mnh b on a.medical_unit = b.sus_no and b.status_sus_no='Active'  "
				 		+" " +cmd_parameter+"  \r\n" + 
				 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by != '')\r\n" + 
				 		"and \r\n" + 
				 		"(CONCAT('20', substr(cast(a.and_no as character varying),14,2))=?\r\n" + 
				 		"and substr(cast(a.and_no as character varying),11,2)= ?)\r\n" + 
				 		" "+qry1+" "+qry_icdcause+" \r\n" + 
				 		" group by 1,2)  c_m\r\n" + 
				 		"\r\n" + 
				 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 +
				 		"group by 1,2,3,4,5\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"union\r\n" + 
				 		"\r\n" + 
				 		"select  c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
				 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
				 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
				 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
				 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
				 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
				 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
				 		"from\r\n" + 
				 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
				 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
				 		"\r\n" + 
				 		"from \r\n" + 
				 		"tb_med_patient_details a\r\n" + 
				 		"inner join orbat_all_details_view_mnh b on a.medical_unit = b.sus_no and b.status_sus_no='Active' "
				 		+" " +cmd_parameter+"  \r\n" + 
				 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by != '')\r\n" + 
				 		"and \r\n" + 
				 		"(CONCAT('20', substr(cast(a.and_no as character varying),14,2))=?\r\n" + 
				 		"and substr(cast(a.and_no as character varying),11,2) = ?)\r\n" + 
				 		" "+qry1+" "+qry_icdcause+" \r\n" + 
				 		" group by 1,2)  c_m\r\n" + 
				 		"\r\n" + 
				 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 + 
				 		"group by 1,2,3,4,5\r\n" + 
				 		"\r\n" + 
				 		"union\r\n" + 
				 		"\r\n" + 
				 		"select c_m.MonthName,c_m.OFF,c_m.JCO,c_m.total,c_m.cmd1,\r\n" + 
				 		"sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet) as offstr,\r\n" + 
				 		"sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect) as jcostr,\r\n" + 
				 		"round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2) as rate_off,\r\n" + 
				 		"round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2) as rate_jco,\r\n" + 
				 		"(round((c_m.OFF * 1000)/sum(m.off_male+m.off_female+m.cadet+m.lady_cadet+m.mns+m.mns_cadet),2)+\r\n" + 
				 		" round((c_m.JCO * 1000)/sum(m.jco+m.ort+m.dsc_jco+m.dsc_or+m.rect),2)) as rate_total\r\n" + 
				 		"from\r\n" + 
				 		"(select  CONCAT(TO_CHAR(TO_DATE (substr(cast(a.and_no as character varying),11,2)::text, 'MM'), 'Month'), \r\n" + 
				 		"substr(cast(a.and_no as character varying),14,2)) AS MonthName ,substring(b.form_code_control,1,1) as cmd1,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') AS OFF,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') AS JCO,\r\n" + 
				 		"COUNT(a.*) filter(where a.category = 'OFFICER' or a.category = 'MNS') + COUNT(a.*) filter(where a.category = 'JCO' or a.category = 'OR') as total\r\n" + 
				 		"\r\n" + 
				 		"from \r\n" + 
				 		"tb_med_patient_details a\r\n" + 
				 		"inner join orbat_all_details_view_mnh b on a.medical_unit = b.sus_no and b.status_sus_no='Active'  "
				 		+" " +cmd_parameter+"  \r\n" + 
				 		"and upper(a.relationship) = upper('SELF') and upper(a.admsn_type) =upper('FRESH') and substring(a.and_no,1,2) in ('AR','RR','DS','MC','MA','DA') and (a.apprvr_at_miso_by is not null or  a.apprvr_at_miso_by!= '')\r\n" + 
				 		"and \r\n" + 
				 		" (CONCAT('20', substr(cast(a.and_no as character varying),14,2))=? \r\n" + 
				 		"and substr(cast(a.and_no as character varying),11,2)= ?)\r\n" + 
				 		" "+qry1+" "+qry_icdcause+" \r\n" + 
				 		" group by 1,2)  c_m\r\n" + 
				 		"\r\n" + 
				 		"inner join tb_med_strength m on substring(m.cmd,1,1) = c_m.cmd1 and qtr=? and year=? " + cmd_parameter1 +  
				 		"group by 1,2,3,4,5\r\n" + 
				 		"\r\n" + 
				 		"";
			stmt=conn.prepareStatement(q); 
			int j= 1;
			//main table condition
		
			
			stmt.setString(j, mnth[0]);
			j++;
			stmt.setString(j, mnth[1]);
			j++;
			//end
			//strength condition
			//icd cause where condition
			 if(!disease_principal1.equals("-1"))
	            {
				 stmt.setString(j, disease_principal1);
					j++;
	            }
	            if(!disease_mmr1.equals("-1"))
	            {
	            	stmt.setString(j, disease_mmr1);
					j++;
	            }
	            if(!disease_aso1.equals("-1"))
	            {
	            	stmt.setString(j, disease_aso1);
					j++;
	            }
	            if(!block_description1.equals("-1"))
	            {
	            	stmt.setString(j, block_description1);
					j++;
	            }
			//end
			stmt.setString(j, current_quarter);
			j++;
			stmt.setInt(j,Integer.parseInt(Year) ); // change
			j++;
			if(!command.equals("ALL"))
	        {
				stmt.setString(j, cmd1);
				j++;
	        }
			
			//selected previous month n same year
			
			
		
			stmt.setString(j, mnth[0]);
			j++;
			stmt.setString(j, String.valueOf(month1));
			j++;
			
			if(!disease_principal1.equals("-1"))
            {
			 stmt.setString(j, disease_principal1);
				j++;
            }
            if(!disease_mmr1.equals("-1"))
            {
            	stmt.setString(j, disease_mmr1);
				j++;
            }
            if(!disease_aso1.equals("-1"))
            {
            	stmt.setString(j, disease_aso1);
				j++;
            }
            if(!block_description1.equals("-1"))
            {
            	stmt.setString(j, block_description1);
				j++;
            }
			stmt.setString(j, previous_quarter);
			j++;
			stmt.setInt(j, Integer.parseInt(previous_Year)); // change
			j++;
			if(!command.equals("ALL"))
	        {
				stmt.setString(j, cmd1);
				j++;
	        }
			
			//for previous year and same month of this year
			
			
			stmt.setString(j,String.valueOf(Integer.parseInt(mnth[0])-1));
			j++;
			stmt.setString(j,mnth[1]);
			j++;
			
			if(!disease_principal1.equals("-1"))
            {
			 stmt.setString(j, disease_principal1);
				j++;
            }
            if(!disease_mmr1.equals("-1"))
            {
            	stmt.setString(j, disease_mmr1);
				j++;
            }
            if(!disease_aso1.equals("-1"))
            {
            	stmt.setString(j, disease_aso1);
				j++;
            }
            if(!block_description1.equals("-1"))
            {
            	stmt.setString(j, block_description1);
				j++;
            }
			stmt.setString(j, current_quarter);
			j++;
			stmt.setInt(j, Integer.parseInt(mnth[0])-1); // change
			j++;
			if(!command.equals("ALL"))
	        {
				stmt.setString(j, cmd1);
			
	        }
			
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
			}
		catch(SQLException e){
			e.printStackTrace();
			}
		finally{
			if(conn != null){
				try{
					conn.close();
					}
				catch (SQLException e){
					
				}
				}
			}
		return list;
		}
	
	public List<Map<String, Object>> search_imb_reports(String command,String category1,String from_date,String to_date,
			String disease_principal1,String disease_type1,String disease_subtype1,String block_description1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement stmtf = null;
			String qry = "";
			String qry_strength="";
			String qry_icdcause ="";
			String mnth[] = to_date.split("-");
			String quarter="";
			String Year="";
			 String cmd ="";
			
			quarter= getQuarter(mnth[1]); //To Get the Quarter of Month
			Year=(mnth[0]);
			String cmd1 = command.substring(0,1);
			Boolean qtr_str = Chk_qtr_strength(quarter,mnth[0],command);
            if(qtr_str == false)
            {
                    
                    String sql = "";
                    PreparedStatement stmt_q = null;
    
                    if(!command.equals("ALL"))
                    {
                    sql = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
                                    "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
                    }
                    if(command.equals("ALL"))
                    {
                    sql = "select qtr,year from tb_med_strength where   \r\n" + 
                                    "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
                    }
                    
                    stmt_q = conn.prepareStatement(sql);
                    int s=1;
                    if(!command.equals("ALL"))
                    {
                    	stmt_q.setString(s, command);
                    	s++;
                    }
                    stmt_q.setInt(s, Integer.parseInt(mnth[0]));
                    s++;
                    stmt_q.setInt(s, ((Integer.parseInt(mnth[0]) -1)));
                    
                    ResultSet rs1_q = stmt_q.executeQuery();
                    while (rs1_q.next()) {
                            quarter = rs1_q.getString("qtr");
                            Year= String.valueOf(rs1_q.getInt("year"));
                    }
                    rs1_q.close();
                   
            }
        	if(!disease_principal1.equals("-1"))
            {
            	qry_icdcause="and upper(d.disease_principal) =upper(?)";
            }
            if(!disease_type1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.disease_type) =upper(?)";
            }
            if(!disease_subtype1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.disease_subtype) =upper(?)";
            }
            if(!block_description1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.block_description) =upper(?)";
            }
			if (!command.equals("ALL")) {
				qry += " and substring(b.form_code_control,1,1) =?";

			}
			
			if (!category1.equals("-1")) {
				qry += " and a.category = ?";

			}
		
			 if(!from_date.equals("") && !to_date.equals("")){
                  
                    
                    qry += " and to_char(a.date_imb, 'YYYY-MM-DD') >= ? and to_char(a.date_imb, 'YYYY-MM-DD') <= ?";
            }
			 if (qry != "")
					qry = " where " + qry.substring(4, qry.length());
			 
			 if (!command.equals("ALL")) {
				 qry_strength = " and substring(cmd,1,1) =?";

				}
		      
	            
	            
			/* q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off  from\r\n" + 
			 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.sex,' ',p.category)\r\n" + 
			 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'          WHEN p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
			 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
			 		"from \r\n" + 
			 		"(SELECT a.category,r.rank_desc,a.sex,b.form_code_control,a.date_imb FROM tb_med_imb a  \r\n" + 
			 		"inner join tb_miso_orbat_unt_dtl b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
			 		 "inner join tb_med_d_disease_cause d on d.icd_code = a.icd_code  " +qry_icdcause+ " "+
			 		"inner join tb_med_rankcode r on  a.rank=r.id  and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') " +qry+ " ) p  \r\n" + 
			 		"group by 1 ) cnt\r\n" + 
			 		"\r\n" + 
			 		"inner join\r\n" + 
			 		"(\r\n" + 
			 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
			 		"union\r\n" + 
			 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ? " + qry_strength + 
			 		"union\r\n" + 
			 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
			 		"union\r\n" + 
			 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
			 		"union\r\n" + 
			 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=? " +  qry_strength +
			 		"union\r\n" + 
			 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
			 		"union\r\n" + 
			 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
			 		" ) strn \r\n" + 
			 		"on cnt.category = strn.category\r\n" +
			 		" group by cnt.total,strn.strength,cnt.category";*/
			 
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			
			 q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off  from\r\n" + 
				 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.sex,' ',p.category)\r\n" + 
				 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'          WHEN p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
				 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
				 		"from \r\n" + 
				 		"(SELECT a.category,r.rank_desc,a.sex,b.form_code_control,a.date_imb FROM tb_med_imb a  \r\n" + 
				 		"inner join orbat_all_details_view_mnh b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
				 		 "inner join tb_med_d_disease_cause d on d.icd_code = a.icd_code  " +qry_icdcause+ " "+
				 		"inner join tb_med_rankcode r on  a.rank=r.id  " +qry+ " ) p  \r\n" + 
				 		"group by 1 ) cnt\r\n" + 
				 		"\r\n" + 
				 		"inner join\r\n" + 
				 		"(\r\n" + 
				 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
				 		"union\r\n" + 
				 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
				 		"union\r\n" + 
				 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=? " +  qry_strength +
				 		"union\r\n" + 
				 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
				 		"union\r\n" + 
				 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
				 		" ) strn \r\n" + 
				 		"on cnt.category = strn.category\r\n" +
				 		" group by cnt.total,strn.strength,cnt.category";
			 
			stmt = conn.prepareStatement(q);
			
			int j = 1;
			if(!disease_principal1.equals("-1"))
            {
			 stmt.setString(j, disease_principal1);
				j++;
            }
            if(!disease_type1.equals("-1"))
            {
            	stmt.setString(j, disease_type1);
				j++;
            }
            if(!disease_subtype1.equals("-1"))
            {
            	stmt.setString(j, disease_subtype1);
				j++;
            }
            if(!block_description1.equals("-1"))
            {
            	stmt.setString(j, block_description1);
				j++;
            }
			 if(!command.equals("ALL")) {
				  stmt.setString(j, cmd1);
				  j++;
			  }
			   if(!category1.equals("-1")) { 
					 stmt.setString(j, category1);
					 j++;
				  }
			   
			   if(!from_date.equals("") && !to_date.equals("")){
				   stmt.setString(j, from_date); 
				   j++;
					stmt.setString(j, to_date); 
					 j++;
			   }
			  
			   
			   if(quarter != "") {
			        stmt.setString(j, quarter);
			        j++;	 
				   } 
			   if(mnth[0] != "") {
			        stmt.setInt(j, Integer.parseInt(Year));
			        j++;
			   } 
			   
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   
			   if(quarter != "") {
		        stmt.setString(j, quarter);
		        j++;	 
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   if(quarter != "") {
		        	stmt.setString(j, quarter);
		        	j++;
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   if(quarter != "") {
		        	stmt.setString(j, quarter);
		        	j++;
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   if(quarter != "") {
		        	stmt.setString(j, quarter);
		        	j++;
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   if(quarter != "") {
		        	stmt.setString(j, quarter);
		        	j++;
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
			   if(quarter != "") {
		        	stmt.setString(j, quarter);
		        	j++;
			   } 
			   if(mnth[0] != "") {
				   stmt.setInt(j, Integer.parseInt(Year));
				   j++;
			   } 
			   if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					 
				  }
			   /*if(!from_date.equals("")) { 
					 stmt.setString(j, from_date); 
					 j++;
				  }
			   if(!to_date.equals("")) { 
					 stmt.setString(j, to_date); 
				  }*/
			 ResultSet rs =null;
			  
			rs = stmt.executeQuery();
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
		return list;
	}
	
	///MORTALITY REPORTS

	
	public List<Map<String, Object>> search_mortality_reports(String command,String category1,String from_date,String to_date,
			String disease_principal1,String disease_type1,String disease_subtype1,String block_description1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry_icdcause ="";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";
			String qry_strength="";
			
			String mnth[] = to_date.split("-");
			String quarter="";
			String Year="";
			String cmd ="";
			
			quarter= getQuarter(mnth[1]); //To Get the Quarter of Month
			Year=(mnth[0]);
			String cmd1 = command.substring(0,1);
			Boolean qtr_str = Chk_qtr_strength(quarter,mnth[0],command);
            if(qtr_str == false)
            {
                    
                    String sql = "";
                    PreparedStatement stmt_q = null;
    
                    if(!command.equals("ALL"))
                    {
                    sql = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
                                    "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
                    }
                    if(command.equals("ALL"))
                    {
                    sql = "select qtr,year from tb_med_strength where  \r\n" + 
                                    "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
                    }
                    
                    stmt_q = conn.prepareStatement(sql);
                    int s=1;
                    if(!command.equals("ALL"))
                    {
                    	stmt_q.setString(s, command);
                    	s++;
                    }
                    stmt_q.setInt(s, Integer.parseInt(mnth[0]));
                    s++;
                    stmt_q.setInt(s, ((Integer.parseInt(mnth[0]) -1)));
                    
                    ResultSet rs1_q = stmt_q.executeQuery();
                    while (rs1_q.next()) {
                            quarter = rs1_q.getString("qtr");
                            Year= String.valueOf(rs1_q.getInt("year"));
                    }
                    rs1_q.close();
                   
            }
			
			
    		if(!disease_principal1.equals("-1"))
            {
            	qry_icdcause="and upper(d.disease_principal) =upper(?)";
            }
            if(!disease_type1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.disease_type) =upper(?)";
            }
            if(!disease_subtype1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.disease_subtype) =upper(?)";
            }
            if(!block_description1.equals("-1"))
            {
            	qry_icdcause+="and upper(d.block_description) =upper(?)";
            }
			
			if (!command.equals("ALL")) {
				qry += " and substring(b.form_code_control,1,1) =?";

			}
			
			if (!category1.equals("-1")) {
				qry += " and a.category = ?";

			}
	
			 if(!from_date.equals("") && !to_date.equals("")){
                  qry += " and to_char(a.date_of_death, 'YYYY-MM-DD') >= ? and to_char(a.date_of_death, 'YYYY-MM-DD') <= ?";
            }
			
			 if (qry != "")
					qry = " where " + qry.substring(4, qry.length());
			 
			 if (!command.equals("ALL")) {
				 qry_strength = " and substring(cmd,1,1) =?";

				}
			
			 /*q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off from\r\n" + 
				 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.gender,' ',p.category)\r\n" + 
				 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'   WHEN  p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
				 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
				 		"from \r\n" + 
				 		"(SELECT a.category,r.rank_desc,a.gender,b.form_code_control,a.date_of_death FROM tb_med_death a  \r\n" + 
				 		"inner join tb_miso_orbat_unt_dtl b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
				 		 "inner join tb_med_d_disease_cause d on d.icd_code = a.icd_code  " +qry_icdcause+ " "+
				 		"inner join tb_med_rankcode r on  a.rank=r.id  and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') " +qry+ " ) p  \r\n" + 
				 		"group by 1 ) cnt\r\n" + 
				 		"\r\n" + 
				 		"inner join\r\n" + 
				 		"(\r\n" + 
				 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		" ) strn \r\n" + 
				 		"on cnt.category = strn.category\r\n" +
				 		" group by cnt.total,strn.strength,cnt.category";*/

			
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			 q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off from\r\n" + 
				 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.gender,' ',p.category)\r\n" + 
				 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'   WHEN  p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
				 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
				 		"from \r\n" + 
				 		"(SELECT a.category,r.rank_desc,a.gender,b.form_code_control,a.date_of_death FROM tb_med_death a  \r\n" + 
				 		"inner join orbat_all_details_view_mnh b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
				 		 "inner join tb_med_d_disease_cause d on d.icd_code = a.icd_code  " +qry_icdcause+ " "+
				 		"inner join tb_med_rankcode r on  a.rank=r.id   " +qry+ " ) p  \r\n" + 
				 		"group by 1 ) cnt\r\n" + 
				 		"\r\n" + 
				 		"inner join\r\n" + 
				 		"(\r\n" + 
				 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		"union\r\n" + 
				 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength + 
				 		" ) strn \r\n" + 
				 		"on cnt.category = strn.category\r\n" +
				 		" group by cnt.total,strn.strength,cnt.category";
			
			
				stmt = conn.prepareStatement(q);
				int j = 1;
				if(!disease_principal1.equals("-1"))
	            {
				 stmt.setString(j, disease_principal1);
					j++;
	            }
	            if(!disease_type1.equals("-1"))
	            {
	            	stmt.setString(j, disease_type1);
					j++;
	            }
	            if(!disease_subtype1.equals("-1"))
	            {
	            	stmt.setString(j, disease_subtype1);
					j++;
	            }
	            if(!block_description1.equals("-1"))
	            {
	            	stmt.setString(j, block_description1);
					j++;
	            }
				 if(!command.equals("ALL")) {
					  stmt.setString(j, cmd1);
					  j++;
				  }
				   if(!category1.equals("-1")) { 
						 stmt.setString(j, category1);
						 j++;
					  }
				   
				   if(!from_date.equals("") && !to_date.equals("")){
					   stmt.setString(j, from_date); 
					   j++;
						stmt.setString(j, to_date); 
						 j++;
				   }
				  
				   if(quarter != "") {
				        stmt.setString(j, quarter);
				        j++;	 
					   } 
				   if(mnth[0] != "") {
				        stmt.setInt(j, Integer.parseInt(Year));
				        j++;
				   } 
				   
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   
				   if(quarter != "") {
			        stmt.setString(j, quarter);
			        j++;	 
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command.equals("ALL")) {
						  stmt.setString(j, cmd1);
						 
					  }
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
			return list;
		}	

	///HIV REPORTS


	
	public List<Map<String, Object>> getsearch_hiv_aids_rep(String command12, String category1, String service1,String relation1,
			String disease_principal1,String disease_type1, String disease_subtype1,String block_description1,String from_dt1, String to_dt1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String qry = "";
				String qry_strength = "";
				String mnth[] = to_dt1.split("-");
				String quarter="";
				String Year="";
				 String cmd ="";
				quarter= getQuarter(mnth[1]); //To Get the Quarter of Month
				Year=(mnth[0]);
				String cmd1 = command12.substring(0,1);
				Boolean qtr_str = Chk_qtr_strength(quarter,mnth[0],command12);
	            if(qtr_str == false)
	            {
	                    
	                    String sql = "";
	                    PreparedStatement stmt_q = null;
	    
	    
	                    
	                    if(!command12.equals("ALL"))
	                    {
	                    	 sql = "select qtr,year from tb_med_strength where  substr(cmd,1,1) like substr(?,1,1)  and \r\n" + 
	                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
	              
	                    }
	                    if(command12.equals("ALL"))
	                    {
	                    	 sql = "select qtr,year from tb_med_strength where   \r\n" + 
	                                 "(year=? or year=?) order by  substr(qtr,2,2) desc  limit 1";
	              
	                    }
	                    
	                    stmt_q = conn.prepareStatement(sql);
	                    int s=1;
	                    if(!command12.equals("ALL"))
	                    {
	                    	stmt_q.setString(1, command12);
	                    	s++;
	                    }
	                    stmt_q.setInt(s, Integer.parseInt(mnth[0]));
	                    s++;
	                    stmt_q.setInt(s, ((Integer.parseInt(mnth[0]) -1)));
	                    
	                    ResultSet rs1_q = stmt_q.executeQuery();
	                    while (rs1_q.next()) {
	                            quarter = rs1_q.getString("qtr");
	                            Year= String.valueOf(rs1_q.getInt("year"));
	                    }
	                    rs1_q.close();
	                   
	            }
				
				if (!command12.equals("ALL")) {
					qry += " and substr(b.form_code_control,1,1)=?";

				}
				if (!category1.equals("-1")) {
					qry += " and a.category = ?";

				}
				if (!service1.equals("-1")) {
					qry += " and service = ?";

				}
				
				if (!relation1.equals("-1")) {
					if(relation1.equals("SELF")) {
						qry += " and relation = ?";
					}else {
						qry += " and relation != 'SELF' ";
					}
				}


				 if(!from_dt1.equals("") && !to_dt1.equals("")){
	                
	                 qry += " and to_char(a.report_date, 'YYYY-MM-DD') >= ? and to_char(a.report_date, 'YYYY-MM-DD') <= ?";
				 }
				
				 if (qry != "")
						qry = " where " + qry.substring(4, qry.length());
				 
				 if (!command12.equals("ALL")) {
					 qry_strength = " and substring(cmd,1,1) =?";

					}
				
				/* q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off from\r\n" + 
					 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.sex,' ',p.category)\r\n" + 
					 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'   WHEN  p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
					 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
					 		"from \r\n" + 
					 		"(SELECT a.category,r.rank_desc,a.sex,b.form_code_control,a.report_date FROM tb_med_hiv a  \r\n" + 
					 		"inner join tb_miso_orbat_unt_dtl b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
					 		"inner join tb_med_rankcode r on  a.rank=r.id  and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') " +qry+ " ) p  \r\n" + 
					 		"group by 1 ) cnt\r\n" + 
					 		"\r\n" + 
					 		"inner join\r\n" + 
					 		"(\r\n" + 
					 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
					 		"union\r\n" + 
					 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ?  " + qry_strength +
					 		"union\r\n" + 
					 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=?  \r\n" + qry_strength +
					 		"union\r\n" + 
					 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		" ) strn \r\n" + 
					 		"on cnt.category = strn.category\r\n" +
					 		" group by cnt.total,strn.strength,cnt.category";*/
				 
				 
					// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				
				 q="select cnt.category,cnt.total,strn.strength,round((cast(cnt.total as decimal)*1000)/cast(strn.strength as decimal),2) as rate_off from\r\n" + 
					 		"(select CASE WHEN p.category = 'OFFICER' and p.rank_desc !='Cadet'  THEN CONCAT(p.sex,' ',p.category)\r\n" + 
					 		"WHEN p.category = 'OFFICER' and p.rank_desc ='Cadet'  THEN 'Cadet'   WHEN  p.rank_desc = 'Rects' THEN 'Rects'\r\n" + 
					 		"ELSE  p.category END AS category ,count(p.category) as total\r\n" + 
					 		"from \r\n" + 
					 		"(SELECT a.category,r.rank_desc,a.sex,b.form_code_control,a.report_date FROM tb_med_hiv a  \r\n" + 
					 		"inner join orbat_all_details_view_mnh b on a.sus_no = b.sus_no and b.status_sus_no='Active' \r\n" + 
					 		"inner join tb_med_rankcode r on  a.rank=r.id  " +qry+ " ) p  \r\n" + 
					 		"group by 1 ) cnt\r\n" + 
					 		"\r\n" + 
					 		"inner join\r\n" + 
					 		"(\r\n" + 
					 		"select 'Cadet' as category ,sum(cadet + lady_cadet + mns_cadet) as strength from tb_med_strength where qtr= ? and year= ? " + qry_strength +
					 		"union\r\n" + 
					 		"select 'FEMALE OFFICER' as category ,sum(off_female) as strength from tb_med_strength where qtr=? and year= ?  " + qry_strength +
					 		"union\r\n" + 
					 		"select 'JCO' as category ,sum(jco + dsc_jco) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'OR' as category ,sum(ort + dsc_or) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'MALE OFFICER' as category ,sum(off_male) as strength from tb_med_strength where qtr=? and year=?  \r\n" + qry_strength +
					 		"union\r\n" + 
					 		"select 'MNS' as category ,sum(mns) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		"union\r\n" + 
					 		"select 'Rects' as category ,sum(rect) as strength from tb_med_strength where qtr= ? and year= ?  " + qry_strength + 
					 		" ) strn \r\n" + 
					 		"on cnt.category = strn.category\r\n" +
					 		" group by cnt.total,strn.strength,cnt.category";
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (!command12.equals("ALL")) {
					stmt.setString(j, cmd1);
					j++;
				}
				 
				 if(!category1.equals("-1")) {
					 stmt.setString(j, category1); 
					 j++;
					
				 }
				 if(!service1.equals("-1")) { 
					 stmt.setString(j, service1); 
					 j++; 
				  }
				  if(!relation1.equals("-1")) {
					  
					 if(relation1.equals("SELF")) {
						  stmt.setString(j, relation1);
						  j++;
						} 
				  }
				  
				  if(!from_dt1.equals("")) { 
						 stmt.setString(j, from_dt1); 
						 j++;
					  }
				   if(!to_dt1.equals("")) { 
						 stmt.setString(j, to_dt1); 
						 j++;
					  }
				   //
				   if(quarter != "") {
				        stmt.setString(j, quarter);
				        j++;	 
					   } 
				   if(mnth[0] != "") {
				        stmt.setInt(j, Integer.parseInt(Year));
				        j++;
				   } 
				   
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        stmt.setString(j, quarter);
			        j++;	 
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						  j++;
					  }
				   //
				   if(quarter != "") {
			        	stmt.setString(j, quarter);
			        	j++;
				   } 
				   if(mnth[0] != "") {
					   stmt.setInt(j, Integer.parseInt(Year));
					   j++;
				   } 
				   if(!command12.equals("ALL")) {
						  stmt.setString(j, cmd1);
						 
					  }
				   
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
			return list;
		}

////DAILY USER REPORT

public List<Map<String, Object>> getdailyuserreport(String username1,String from_dt1,String to_dt1,String unit_name1,String sus_no1) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";
		
		if (from_dt1 != "" && to_dt1 != "") {

			qry += " and cast(a.apprvd_at_miso_on as date) between cast(? as date) and cast(? as date) + 1";
		}

		if (from_dt1 != "" && to_dt1 == "") {
		
			qry += " and cast(a.apprvd_at_miso_on as date) >= cast(? as date) ";
		}

		if (from_dt1 == "" && to_dt1 != "") {
			qry += " and cast(a.apprvd_at_miso_on as date) <= cast(? as date) ";	

		}
		
		/*q="select a.apprvr_at_miso_by,b.unit_name, count(a.apprvr_at_miso_by) as count from tb_med_patient_details a, tb_miso_orbat_unt_dtl b"
		+" where a.medical_unit=b.sus_no and b.status_sus_no='Active'"
		+" and a.apprvr_at_miso_by ='mnh_deo1' "+qry+" group by a.apprvr_at_miso_by,b.unit_name";*/
	
		// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
		
		q="select a.apprvr_at_miso_by,b.unit_name, count(a.apprvr_at_miso_by) as count from tb_med_patient_details a, orbat_all_details_view_mnh b"
				+" where a.medical_unit=b.sus_no and b.status_sus_no='Active'"
				+" and a.apprvr_at_miso_by ='mnh_deo1' "+qry+" group by a.apprvr_at_miso_by,b.unit_name";
		
		
		stmt = conn.prepareStatement(q);

		  int j =1;

		if (from_dt1 != "" && to_dt1 != "") {
			stmt.setString(j, from_dt1);
			j+=1;
		
			stmt.setString(j, to_dt1);
			j+=1;
		}
		
		if (from_dt1 != "" && to_dt1 == "") {
			stmt.setString(j, from_dt1);
			j+=1;
		}
		
		if (from_dt1 == "" && to_dt1 != "") {		
			stmt.setString(j, to_dt1);
			j+=1;
			
		}
		
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
	return list;
}

public List<Map<String, Object>> search_opd_spl_proc_report(String cmd1, String qtr1, String yr1) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	String qry = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String tbl_name = null;
		if (!cmd1.equals("ALL")) {
			qry += " where substr(b.form_code_control,?,?)=?";
		}

		if (!qtr1.equals("-1")) {
			qry += " and a.quater=?";
		}

		if (!yr1.equals("")) {
			qry += " and a.yr=?";
		}

		/*q="select c.cmd_name as Command,f.dept_name,f.opd_count,proc_name as procedure\r\n" + 
				" from (select distinct substr(b.form_code_control,1,1) as Command,d.dept_name,p.proc_name, sum(opd_count) as opd_count ,a.sus_no\r\n" + 
				" from tb_med_opd_spl a inner join tb_miso_orbat_unt_dtl b \r\n" + 
				"\r\n" + 
				"on a.sus_no = b.sus_no and b.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234')\r\n" + 
				"inner join tb_med_opd_sp_department d on \r\n" + 
				"cast(a.dept_id as integer)=d.id and d.status='ACTIVE' inner join  tb_med_opd_sp_procedure p on d.id =p.dept_id and p.status='ACTIVE' \r\n" + 
				" "+qry+" \r\n" + 
				" group by substr(b.form_code_control,1,1),d.dept_name,p.proc_name,a.sus_no) f \r\n" + 
				" left join orbat_view_cmd c on c.cmd_code = f.Command";*/
		
		// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
		
/*		q="select c.cmd_name as Command,f.dept_name,f.opd_count,proc_name as procedure\r\n" + 
				" from (select distinct substr(b.form_code_control,1,1) as Command,d.dept_name,p.proc_name, sum(opd_count) as opd_count ,a.sus_no\r\n" + 
				" from tb_med_opd_spl a inner join orbat_all_details_view_mnh b \r\n" + 
				"\r\n" + 
				"on a.sus_no = b.sus_no and b.status_sus_no='Active'\r\n" + 
				"inner join tb_med_opd_sp_department d on \r\n" + 
				"cast(a.dept_id as integer)=d.id and d.status='ACTIVE' inner join  tb_med_opd_sp_procedure p on d.id =p.dept_id and p.status='ACTIVE' \r\n" + 
				" "+qry+" \r\n" + 
				" group by substr(b.form_code_control,1,1),d.dept_name,p.proc_name,a.sus_no) f \r\n" + 
				" left join orbat_view_cmd c on c.cmd_code = f.Command";
		*/
		
		//27-10-2022
		q="select c.cmd_name as Command,f.dept_name,f.opd_count,proc_name as procedure\r\n" + 
				" from (select distinct substr(b.form_code_control,1,1) as Command,d.dept_name,p.proc_name, sum(opd_count) as opd_count ,a.sus_no\r\n" + 
				" from tb_med_opd_spl a inner join orbat_all_details_view_mnh b \r\n" + 
				"\r\n" + 
				"on a.sus_no = b.sus_no and b.status_sus_no='Active'\r\n" + 
				"inner join tb_med_opd_sp_department d on \r\n" + 
				"cast(a.dept_id as integer)=d.id and d.status='ACTIVE' inner join  tb_med_opd_sp_procedure p on p.id =a.proc_id and p.status='ACTIVE' \r\n" + 
				" "+qry+" \r\n" + 
				" group by substr(b.form_code_control,1,1),d.dept_name,p.proc_name,a.sus_no) f \r\n" + 
				" left join orbat_view_cmd c on c.cmd_code = f.Command";
		stmt = conn.prepareStatement(q);
		
		int j = 1;
		if (!cmd1.equals("ALL")) {
			stmt.setInt(j, 1);
			j++;
			stmt.setInt(j, 1);
			j++;
			stmt.setString(j, cmd1.substring(0, 1));
			j++;
		}

		if (!qtr1.equals("-1")) {
			stmt.setString(j, qtr1);
			j++;
		}

		if (!yr1.equals("")) {
			stmt.setString(j, yr1);
			j++;
		}
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
	return list;
}

	public String getQuarter(String month) {
		
		 String Month = month;
	
		 String quarter="";
		
		 int mon = Integer.parseInt(Month);
		
		 if(mon > 0 && mon <=3)
		 {
			 quarter="q1";
		 }
		 else if (mon > 3 && mon <=6)
		 {
			 quarter="q2";
		 }
		 else if (mon > 6 && mon <=9)
		 {
			 quarter="q3";
		 }
		 else
		 {
			 quarter="q4";
		 }
		
		return quarter;
	
	}
	public Boolean Chk_qtr_strength(String quarter,String year, String command) {
	 Boolean result = true;
	 Connection conn = null;
	 try {
	         conn = dataSource.getConnection();
	         String sql = "";
	         PreparedStatement stmt = null;
	
	         if(!command.equals("ALL"))
	         {
	        	 sql = "select  case when count(id) > 0 then true else false end \r\n" + 
                         "from tb_med_strength where qtr=? and year=? and  substr(cmd,1,1) like substr(?,1,1) ";
	         }
	         if(command.equals("ALL"))
	         {
	        	 sql = "select  case when count(id) > 0 then true else false end \r\n" + 
                         "from tb_med_strength where qtr=? and year=?  ";
	         }
	         
	         
	         stmt = conn.prepareStatement(sql);
	        
	         stmt.setString(1, quarter);
	         stmt.setInt(2, Integer.parseInt(year));
	         if(!command.equals("ALL"))
	         {
	        	 stmt.setString(3, command);
	         }
	         ResultSet rs1 = stmt.executeQuery();
	         while (rs1.next()) {
	                 result = rs1.getBoolean("case");
	         }
	         rs1.close();
	         stmt.close();
	 } catch (SQLException e) {
	         e.printStackTrace();
	 }
	 return result;
	}

public ArrayList<String> getdis_principal_mmr_dao(String value,String col_name) {
		
		ArrayList<String>  list = new ArrayList<String> ();
		Connection conn = null;
		String q = "";
		String qry = "";
		String result="(";
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			if(col_name.equals("disease_principal"))
			{
				q = " select icd_code from tb_med_d_disease_cause where  disease_principal like ?" ; 
				

			}
			if(col_name.equals("disease_mmr"))
			{
				q = " select icd_code from tb_med_d_disease_cause where  disease_mmr like ?" ; 
				

			}
			if(col_name.equals("disease_aso"))
			{
				q = " select icd_code from tb_med_d_disease_cause where  disease_aso like ?" ; 
				

			}
			if(col_name.equals("block_description"))
			{
				q = " select icd_code from tb_med_d_disease_cause where  block_description like ?" ; 
				

			}
			stmt = conn.prepareStatement(q);
		
			stmt.setString(1,value);
			
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
	
				
				if(result.equals("("))
				{
					result +=  "'"+rs.getString("icd_code") +"'";
				}
				else
				{
					result +="," + "'"+rs.getString("icd_code") +"'";
				}
				
			

			}
			result +=")";
			list.add(result);
			
			rs.close();
			stmt.close();
			conn.close();
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
		return list;
	}


}
