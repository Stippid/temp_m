package com.dao.psg.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class ReportSearch_3008_mnsDAOImpl implements ReportSearch_3008_mnsDAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getServing3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String FDate, String LDate,String sus_no) throws SQLException
	{
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
    	String SearchValue = GenerateQueryWhereClause_SQL(Search);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			q = "select *from (select distinct c.personnel_no,TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n"
                    + "TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,op.comm_id,op.to_sus_no,OP.id,\r\n"
                    + "TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,ra.description as rank,  CASE \r\n"
                    + "        WHEN app_main.date_of_appointment > op.dt_of_tos   THEN ra1.description\r\n"
                    + "        ELSE ''\r\n"
                    + "    END AS appt,\r\n"
                    + "cda_main.cda_acc_no,\r\n"
                    + "case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n"
                    + "case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n"
                    + "TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n"
                    + "TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n"
                    + "TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n"
                    + "  CASE \r\n"
                    + "        WHEN app_main.date_of_appointment > op.dt_of_tos   THEN to_char(app_main.date_of_appointment,'DD-MON-YYYY')\r\n"
                    + "        ELSE ''\r\n"
                    + "    END AS date_of_appointment,"
                    + "case when nm_main.name is null then c.name else nm_main.name end ,\r\n"
                    + "'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')   || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n"
                    + "|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n"
                    + "|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n"
                    + "as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT ,\r\n"
                    + " c.tnai_no,\r\n"
                    + "         \r\n"
                    + "				COALESCE(\r\n"
                    + "    ltrim(to_char(ma.marriage_date, 'DD-Mon-YYYY'), '0'), \r\n"
                    + "    (SELECT ltrim(to_char(marital_status_date, 'DD-Mon-YYYY'), '0') \r\n"
                    + "     FROM tb_psg_married_3008mns_dtl \r\n"
                    + "     WHERE comm_id = c.id and status=1 order by id desc limit 1)\r\n"
                    + ") AS dom,COALESCE(\r\n"
                    + "    CASE \r\n"
                    + "        WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
                    + "        WHEN census.marital_status = 8  THEN 'Married'\r\n"
                    + "        WHEN census.marital_status = 13 THEN 'Separated'\r\n"
                    + "        WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
                    + "        WHEN census.marital_status = 11 THEN 'Widow'\r\n"
                    + "        WHEN census.marital_status = 12 THEN 'Widower'\r\n"
                    + "    END,\r\n"
                    + "    (SELECT \r\n"
                    + "        CASE \r\n"
                    + "            WHEN marital_status ='9'  THEN 'Divorced'\r\n"
                    + "            WHEN marital_status = '8'  THEN 'Married'\r\n"
                    + "            WHEN marital_status = '13' THEN 'Separated'\r\n"
                    + "            WHEN marital_status = '10' THEN 'Unmarried'\r\n"
                    + "            WHEN marital_status = '11' THEN 'Widow'\r\n"
                    + "            WHEN marital_status = '12' THEN 'Widower'\r\n"
                    + "        END\r\n"
                    + "     FROM tb_psg_married_3008mns_dtl \r\n"
                    + "     WHERE comm_id = c.id and status=1 order by id desc limit 1)\r\n"
                    + ") AS marital_status_description"+
                   
                    "  from\r\n"
                    + "tb_psg_trans_proposed_comm_letter c \r\n"
                    + "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n"
                    + "to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
                    + "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n"
                    + "inner join\r\n" + "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n"
                    + "where status in ('1','2') and\r\n"
                    + "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n"
                    + "on rk.comm_id=c.id\r\n" + "inner join\r\n"
                    + "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n"
                    + "inner join cue_tb_psg_rank_app_master ra on   ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK' "
                    + "left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + "left join\r\n"
                    + "(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n"
                    + "where status in ('1','2') and \r\n"
                    + "to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n"
                    + "on sen.comm_id=c.id \r\n"
                    + "left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n"
                    + "left join\r\n"
                    + "(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n"
                    + "where status in ('1','2') and\r\n"
                    + "to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n"
                    + "on app.comm_id=c.id\r\n" + "left join\r\n"
                    + "tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n"
                    + "left join cue_tb_psg_rank_app_master ra1 on   ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n"
                    + "left join\r\n"
                    + "(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n"
                    + "where status in ('1','2') and\r\n"
                    + "to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n"
                    + "on cda.comm_id=c.id\r\n" + "left join\r\n"
                    + "tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n"
                    + "left join\r\n"
                    + "(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n"
                    + "where status in ('1','2') and\r\n"
                    + "to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n"
                    + "on arm.comm_id=c.id\r\n" + "left join\r\n"
                    + "tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n"
                    + "left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n"
                    + "left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n"
                    + "left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm   \r\n"
                    + "left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =   c.regiment \r\n" + "left join\r\n"
                    + "(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n"
                    + "where status in ('1','2') and\r\n"
                    + "to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n"
                    + "on nm.comm_id=c.id\r\n" + "left join\r\n"
                    + "tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n"
                    + "left join\r\n"
                    + "(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n"
                    + "where status in ('1','2') and m.shape='S'   and (m.date_of_authority is null or\r\n"
                    + "(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')   <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n"
                    + "on c.id = med.comm_id\r\n" + "left join\r\n" + "\r\n"
                    + "tb_psg_medical_category   med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')   and \r\n"
                    + "COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n"
                    + "    LEFT JOIN tb_psg_census_family_married ma\r\n"
                    + "            ON ma.comm_id = c.id\r\n and ma.status ='1'"
                    + "          LEFT JOIN tb_psg_census_detail_p census\r\n"
                    + "            ON census.comm_id = c.id and census.status = '1' \r\n"
                    + "where op.to_sus_no=?\r\n" + "and \r\n"
                    + "(ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n"
                    + "and c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2' and   to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n"
                    + "and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and   \r\n"
                    + "to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
                    + "AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n"
                    + " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
                    + "GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23 order by   "
                    + " rankT,sen_main.date_of_seniority asc   limit " + pageL + " OFFSET " + startPage + ") subq "
                    + SearchValue;
			
		
		
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,FDate, LDate,roleAccess,roleSusNo,sus_no);
			System.err.println("serving mns: \n" +stmt);
	      ResultSet rs = stmt.executeQuery();   
	      
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    		  columns.put("action","<i class=\"action_icons action_update\" onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){openPopup('"+rs.getObject(4).toString()+"','Update_officier_3008_mns')}else{ return false;}\" title=\"Edit Data\"></i>");
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
	
	public long getServing3008CountList(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no )throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
			  
				 q="select count(app.*) from(select * from(select distinct c.personnel_no,TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
	                        "TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,op.comm_id,op.to_sus_no,OP.id,\r\n" + 
	                        "TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,ra.description as rank,ra1.description as appt,\r\n" + 
	                        "cda_main.cda_acc_no,\r\n" + 
	                        "case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
	                        "case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
	                        "TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
	                        "TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n" + 
	                        "TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
	                        "TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
	                        "'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')   || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
	                        "|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
	                        "|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
	                        "as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT, \r\n"
	                        + " c.tnai_no,\r\n"
	                        + "               ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
	                        + "               CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
	                        + "                    WHEN census.marital_status = 8  THEN 'Married'\r\n"
	                        + "                    WHEN census.marital_status = 13 THEN 'Separated'\r\n"
	                        + "                    WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
	                        + "                    WHEN census.marital_status = 11 THEN 'Widow'\r\n"
	                        + "                    WHEN census.marital_status = 12 THEN 'Widower'\r\n"
	                        + "                     END AS marital_status_description\r\n" + 
	                        "from\r\n" + 
	                        "tb_psg_trans_proposed_comm_letter c \r\n" + 
	                        "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n" + 
	                        "to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
	                        "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
	                        "inner join\r\n" + 
	                        "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
	                        "where status in ('1','2') and\r\n" + 
	                        "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
	                        "on rk.comm_id=c.id\r\n" + 
	                        "inner join\r\n" + 
	                        "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n" + 
	                        "inner join cue_tb_psg_rank_app_master ra on   ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK' "+
	                        "left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
	                        "where status in ('1','2') and \r\n" + 
	                        "to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n" + 
	                        "on sen.comm_id=c.id \r\n" + 
	                        "left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
	                        "where status in ('1','2') and\r\n" + 
	                        "to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
	                        "on app.comm_id=c.id\r\n" + 
	                        "left join\r\n" + 
	                        "tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n" + 
	                        "left join cue_tb_psg_rank_app_master ra1 on   ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
	                        "where status in ('1','2') and\r\n" + 
	                        "to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
	                        "on cda.comm_id=c.id\r\n" + 
	                        "left join\r\n" + 
	                        "tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
	                        "where status in ('1','2') and\r\n" + 
	                        "to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
	                        "on arm.comm_id=c.id\r\n" + 
	                        "left join\r\n" + 
	                        "tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n" + 
	                        "left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
	                        "left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
	                        "left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm   \r\n" + 
	                        "left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =   c.regiment \r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
	                        "where status in ('1','2') and\r\n" + 
	                        "to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
	                        "on nm.comm_id=c.id\r\n" + 
	                        "left join\r\n" + 
	                        "tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
	                        "left join\r\n" + 
	                        "(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
	                        "where status in ('1','2') and m.shape='S'   and (m.date_of_authority is null or\r\n" + 
	                        "(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')   <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
	                        "on c.id = med.comm_id\r\n" + 
	                        "left join\r\n" + 
	                        "\r\n" + 
	                        "tb_psg_medical_category   med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')   and \r\n" + 
	                        "COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n"
	                        + "    LEFT JOIN tb_psg_census_family_married ma\r\n"
	                        + "            ON ma.comm_id = c.id and  ma.status ='1'\r\n"
	                        + "          LEFT JOIN tb_psg_census_detail_p census\r\n"
	                        + "            ON census.comm_id = c.id and  census.status ='1' \r\n" + 
	                        "where op.to_sus_no=?\r\n" + 
	                        "and \r\n" + 
	                        "(ra1.description is null or coalesce (ra1.description,'0') not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
	                        "and c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2' and   to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
	                        "and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and   \r\n" + 
	                        "to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
	                        "AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
	                        " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
	                        "GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23 ) subq "+SearchValue+" ) app";         
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt,Search,FDate,  LDate,roleAccess,roleSusNo,sus_no);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {  
					total = rs.getInt(1);
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
			return (long) total;
		}
	 
	 public String GenerateQueryWhereClause_SQL(String Search) {
			String SearchValue ="";
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
				SearchValue += " where upper(subq.appt) like ? or upper(subq.rank) like ? or  upper(subq.name) like ?  or upper(subq.personnel_no) like ? or upper(subq.cda_acc_no) like ?"
						+ " or upper(subq.parent_arm) like ? or upper(subq.med_cat) like ? or upper(subq.dt_of_tos) like ? or upper(subq.date_of_birth) like ? or upper(subq.date_of_commission) like ?"
						+ " or upper(subq.date_of_seniority) like ? or upper(subq.date_of_appointment) like ?  or upper(subq.tnai_no) like ? or upper(subq.dom) like ? or upper(subq.marital_status_description) like ?";
						
				
			}
			return SearchValue;
	 }
	 public String GenerateAHQueryWhereClause_SQL(String Search) {
			String SearchValue ="";
			
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
				SearchValue += " where upper(subq.rank) like ? or upper(subq.base_auth) like ? or  upper(subq.mod_auth) like ?  or upper(subq.foot_auth) like ? or upper(subq.total_auth) like ?"
						+ " or upper(subq.total_held) like ? ";
						
				
			}
			return SearchValue;
	 }
	 
		// bisag v2 290822 (split auth and held)
	 public String GenerateAuthQueryWhereClause_SQL(String Search) {
			String SearchValue ="";
			
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
				SearchValue += " where upper(subq.rank) like ? or upper(subq.base_auth) like ? or  upper(subq.mod_auth) like ?  or upper(subq.foot_auth) like ? or upper(subq.total_auth) like ?";
					
				
			}
			return SearchValue;
	 }
	 public String GenerateHeldQueryWhereClause_SQL(String Search) {
			String SearchValue ="";
			
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "") { // for Input Filter
				SearchValue += " where upper(held.description) like ? "
						+ " or upper(held.total_held::text) like ? ";
						
				
			}
			return SearchValue;
	 }
	 
		// bisag v2 290822 (split auth and held) ends
	 
	 public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String FDate, String LDate,String roleAccess,String roleSusNo,String sus_no) {
			
			int flag = 0;
			LocalDate currentDate = LocalDate.now();
			int currentYear = currentDate.getYear();
	        int currentMonth = currentDate.getMonthValue();
	        int currentDay = currentDate.getDayOfMonth();
	        String currentdate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
			
			try {
				
				
				stmt.setString(1,FDate);
				stmt.setString(2,FDate);
				  stmt.setString(3,LDate);
				  stmt.setString(4,LDate);
				  stmt.setString(5,LDate);
				  stmt.setString(6,currentdate);
				  stmt.setString(7,LDate);
				  stmt.setString(8,LDate);
				  stmt.setString(9,LDate);
			     if (roleAccess.equals("Unit")) {
			    	 stmt.setString(10, roleSusNo);
		         }
		         if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
		        	 stmt.setString(10, sus_no);
		         }
				  stmt.setString(11,FDate);
				  stmt.setString(12,FDate);
				  stmt.setString(13,LDate);
				  flag = 13;
				  if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	 
	 
	 public PreparedStatement setdesQueryWhereClause_SQL(PreparedStatement stmt,String Search,String FDate, String LDate,String roleAccess,String roleSusNo,String sus_no) {
			
			int flag = 0;
			
			try {
				
				
				stmt.setString(1,FDate);
				stmt.setString(2,FDate);
				  stmt.setString(3,LDate);
				  stmt.setString(4,LDate);
				  stmt.setString(5,LDate);
				  stmt.setString(6,LDate);
				  stmt.setString(7,LDate);
				  stmt.setString(8,LDate);
				  stmt.setString(9,LDate);
			     if (roleAccess.equals("Unit")) {
			    	 stmt.setString(10, roleSusNo);
		         }
		         if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {
		        	 stmt.setString(10, sus_no);
		         }
				  stmt.setString(11,FDate);
				  //stmt.setString(12,FDate);
				  stmt.setString(12,LDate);
				  flag = 12;
				  if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	 
	 public PreparedStatement setAHQueryWhereClause_SQL(PreparedStatement stmt,String Search,String FDate, String LDate,String roleAccess,String roleSusNo,String sus_no) {
			
			int flag = 0;
			
			try {
				stmt.setString(1,sus_no);
                stmt.setString(2,FDate);
                 stmt.setString(3,FDate);
                 stmt.setString(4,FDate);
                 if (roleAccess.equals("Unit")) {
			    	 stmt.setString(5, roleSusNo);
		         }
		         if (roleAccess.equals("MISO")) {
		        	 stmt.setString(5, sus_no);
		         }
//                 stmt.setString(5,sus_no);
//                 stmt.setString(6,roleSusNo);


                 	flag = 5;

				  if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
						
					}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	 
		// bisag v2 290822 (split auth and held)
	 public PreparedStatement setAuthQueryWhereClause_SQL(PreparedStatement stmt,String Search,String FDate, String LDate,String roleAccess,String roleSusNo,String sus_no) {
			
			int flag = 0;
			
			try {
				stmt.setString(1,sus_no);
//             stmt.setString(2,FDate);
//              stmt.setString(3,FDate);
//              stmt.setString(4,FDate);
//              if (roleAccess.equals("Unit")) {
//			    	 stmt.setString(5, roleSusNo);
//		         }
//		         if (roleAccess.equals("MISO")) {
//		        	 stmt.setString(5, sus_no);
//		         }
//              stmt.setString(5,sus_no);
//              stmt.setString(6,roleSusNo);


              	flag = 1;

				  if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//						flag += 1;
//						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
						
					}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	 
	 
	 public PreparedStatement setHeldQueryWhereClause_SQL(PreparedStatement stmt,String Search,String FDate, String LDate,String roleAccess,String roleSusNo,String sus_no) {
			
			int flag = 0;
			
			try {
//				stmt.setString(1,sus_no);
          stmt.setString(1,FDate);
           stmt.setString(2,FDate);
           stmt.setString(3,FDate);
           if (roleAccess.equals("Unit")) {
			    	 stmt.setString(4, roleSusNo);
		         }
		         if (roleAccess.equals("MISO")|| roleAccess.equals("DGMS")) {
		        	 stmt.setString(4, sus_no);
		         }
//           stmt.setString(5,sus_no);
//           stmt.setString(6,roleSusNo);


           	flag = 4;

				  if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//						flag += 1;
//						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//						flag += 1;
//						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//						flag += 1;
//						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//						flag += 1;
//						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
						
					}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
		// bisag v2 290822 (split auth and held) ends
	
	///////////////////// SuperNum/////////////////
	 public List<Map<String, Object>> getSupernum3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String FDate, String LDate,String sus_no) throws SQLException
		{
			
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";


		try{	
			String pageL = "";
	        
	        if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select * from(select distinct c.personnel_no,\r\n" + 
						"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
						"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
						"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
						"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
						"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
						"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
						"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" +  
						"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
						"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
						"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
						"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
						"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
						"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
						"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
						"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
						"as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT, \r\n"
						+ "c.tnai_no,\r\n"
						+ "                       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
						+ "                       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
						+ "                            WHEN census.marital_status = 8  THEN 'Married'\r\n"
						+ "                            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
						+ "                            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
						+ "                            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
						+ "                            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
						+ "                             END AS marital_status_description \r\n" + 
						"from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1 \r\n" + 
						"and\r\n" + 
						"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
						"left join\r\n" + 
						"(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
						"where status in ('1','2') and \r\n" + 
						"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n" + 
						"on sen.comm_id=c.id \r\n" + 
						"left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2') \r\n" + 
						"left join\r\n" + 
						"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
						"on app.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n" + 
						"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
						"left join\r\n" + 
						"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
						"on cda.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
						"on arm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n" + 
						"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
						"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 
						"left join\r\n" + 
						"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
						"on nm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
						"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
						"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
						"on c.id = med.comm_id\r\n" + 
						"left join\r\n" + 
						"\r\n" + 
						"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
						"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
						"             LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "                    ON ma.comm_id = c.id and  ma.status ='1'\r\n"
						+ "                  LEFT JOIN tb_psg_census_detail_p census\r\n"
						+ "                    ON census.comm_id = c.id  and  census.status ='1'\r\n" + 
						"where op.to_sus_no= ? and \r\n" + 
						"coalesce (ra1.description,'0') in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n" + 
						"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
						"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
						" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
						"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23 order by "
						+ " rankT,sen_main.date_of_seniority asc  limit " +pageL+" OFFSET "+startPage 
						+ ") subq "+SearchValue;;
				
			
				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,FDate, LDate,roleAccess,roleSusNo,sus_no);
				
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
		
	 public long getSupernum3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no )throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				q="select count(app.*) from(select * from(select distinct c.personnel_no,\r\n" + 
						"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
						"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
						"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
						"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
						"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
						"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
						"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
						"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
						"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
						"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
						"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
						"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
						"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
						"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
						"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
						"as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT, \r\n"
						+ "c.tnai_no,\r\n"
						+ "                       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
						+ "                       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
						+ "                            WHEN census.marital_status = 8  THEN 'Married'\r\n"
						+ "                            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
						+ "                            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
						+ "                            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
						+ "                            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
						+ "                             END AS marital_status_description \r\n" + 
						"from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1 \r\n" + 
						"and\r\n" + 
						"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
						"left join\r\n" + 
						"(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
						"where status in ('1','2') and \r\n" + 
						"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n" + 
						"on sen.comm_id=c.id \r\n" + 
						"left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2') \r\n" + 
						"left join\r\n" + 
						"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
						"on app.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n" + 
						"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
						"left join\r\n" + 
						"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
						"on cda.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
						"on arm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n" + 
						"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
						"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 
						"left join\r\n" + 
						"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
						"on nm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
						"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
						"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
						"on c.id = med.comm_id\r\n" + 
						"left join\r\n" + 
						"\r\n" + 
						"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
						"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
						"  \r\n"
						+ "                             LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "                    ON ma.comm_id = c.id  and  ma.status ='1'\r\n"
						+ "                  LEFT JOIN tb_psg_census_detail_p census\r\n"
						+ "                    ON census.comm_id = c.id and  census.status ='1' \r\n" + 
						"where op.to_sus_no= ? and \r\n" + 
						"coalesce (ra1.description,'0') in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n" + 
						"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
						"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
						" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
						"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23 ) subq "+SearchValue+") app"  ;
				
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt,Search,FDate,  LDate,roleAccess,roleSusNo,sus_no);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {  
					total = rs.getInt(1);
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
			return (long) total;
		}
		 
///////////////////// ReEmployee /////////////////
	 public List<Map<String, Object>> getReEmployee3008(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String FDate, String LDate,String sus_no) throws SQLException
		{
			
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";


		try{	
			String pageL = "";
		 
		 if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select * from(select distinct c.personnel_no,\r\n" + 
						"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
						"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
						"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
						"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
						"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
						"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
						"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
						"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
						"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
						"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
						"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
						"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
						"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
						"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
						"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
						"as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT \r\n" + 
						"from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n" + 
						"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
						"left join\r\n" + 
						"(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen\r\n" + 
						"on sen.comm_id=c.id\r\n" + 
						"left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
						"on app.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2') \r\n" + 
						"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
						"left join\r\n" + 
						"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
						"on cda.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
						"on arm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n" + 
						"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" +
						"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 		
						"left join\r\n" + 
						"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
						"on nm.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n" + 
						"left join\r\n" + 
						"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
						"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
						"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
						"on c.id = med.comm_id\r\n" + 
						"left join\r\n" + 
						"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
						"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
						"\r\n" + 
						"where op.to_sus_no=? \r\n" + 
						"--and (ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
						"and c.id  in (select comm_id from tb_psg_re_employment where status=1  and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
						"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
						" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
						"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20 order by "
						+ " rankT,sen_main.date_of_seniority asc limit " +pageL+" OFFSET "+startPage 
						+ ") subq "+SearchValue;
				
			
				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,FDate, LDate,roleAccess,roleSusNo,sus_no);
			
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

	 public long getReEmployee3008Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String FDate, String LDate,String sus_no )throws SQLException {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
				int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					
					q="select count(app.*) from(select * from(select distinct c.personnel_no,\r\n" + 
							"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
							"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
							"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
							"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
							"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
							"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
							"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
							"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
							"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
							"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
							"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
							"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
							"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
							"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
							"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
							"as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT\r\n" + 
							"from\r\n" + 
							"tb_psg_trans_proposed_comm_letter c \r\n" + 
							"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n" + 
							"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
							"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
							"inner join\r\n" + 
							"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
							"on rk.comm_id=c.id\r\n" + 
							"inner join\r\n" + 
							"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
							"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
							"inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
							"left join\r\n" + 
							"(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen\r\n" + 
							"on sen.comm_id=c.id\r\n" + 
							"left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n" + 
							"left join\r\n" + 
							"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
							"on app.comm_id=c.id\r\n" + 
							"left join\r\n" + 
							"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2') \r\n" + 
							"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
							"left join\r\n" + 
							"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
							"on cda.comm_id=c.id\r\n" + 
							"left join\r\n" + 
							"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
							"left join\r\n" + 
							"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
							"on arm.comm_id=c.id\r\n" + 
							"left join\r\n" + 
							"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n" + 
							"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
							"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" +
							"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
							"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 		
							"left join\r\n" + 
							"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
							"where status in ('1','2') and\r\n" + 
							"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
							"on nm.comm_id=c.id\r\n" + 
							"left join\r\n" + 
							"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n" + 
							"left join\r\n" + 
							"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
							"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
							"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
							"on c.id = med.comm_id\r\n" + 
							"left join\r\n" + 
							"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
							"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
							"\r\n" + 
							"where op.to_sus_no=? \r\n" + 
							"--and (ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
							"and c.id  in (select comm_id from tb_psg_re_employment where status=1  and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
							"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
							"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
							"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
							" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
							"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20 ) subq "+SearchValue+") app";
					
					
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					stmt = setQueryWhereClause_SQL(stmt,Search,FDate,  LDate,roleAccess,roleSusNo,sus_no);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {  
						total = rs.getInt(1);
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
				return (long) total;
			}


	///////////////////// Deserter /////////////////
	 public List<Map<String, Object>> getDeserter3008(int startPage, int pageLength, String Search, String orderColunm,
				String orderType, HttpSession sessionUserId, String FDate, String LDate, String sus_no)
				throws SQLException {

			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				String pageL = "";

				if (pageLength == -1) {
					pageL = "ALL";
				} else {
					pageL = String.valueOf(pageLength);
				}
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select * from(select distinct c.personnel_no,\r\n"
						+ "TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n"
						+ "TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n"
						+ "op.comm_id,op.to_sus_no,OP.id,\r\n"
						+ "TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n"
						+ "ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n"
						+"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n"
						+"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" 
						+ "TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n"
						+ "TO_CHAR(c.date_of_commission ,'DD-MON-YYYY')as date_of_commission,\r\n"
						+ "TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n"
						+ "TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n"
						+ "case when nm_main.name is null then c.name else nm_main.name end ,\r\n"
						+ "'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n"
						+ "|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n"
						+ "|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n"
						+ "as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT,TO_CHAR(dsn.dt_desertion ,'DD-MON-YYYY') as dt_desertion  \r\n" + ""
								+ ",c.tnai_no,\r\n"
								+ "                       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
								+ "                       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
								+ "                            WHEN census.marital_status = 8  THEN 'Married'\r\n"
								+ "                            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
								+ "                            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
								+ "                            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
								+ "                            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
								+ "                             END AS marital_status_description \r\n"
								+ "from\r\n"
						+ "tb_psg_trans_proposed_comm_letter c \r\n"
						+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1 \r\n"
						+ "and\r\n" + "to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
						+ "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n"
						+ "inner join\r\n" + "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n"
						+ "on rk.comm_id=c.id\r\n" + "inner join\r\n"
						+ "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n"
						+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n"
						+ "inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + "left join\r\n"
						+ "(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n"
						+ "where status in ('1','2') and \r\n"
						+ "to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n"
						+ "on sen.comm_id=c.id \r\n"
						+ "left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2') \r\n"
						+ "left join\r\n"
						+ "(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n"
						+ "on app.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n"
						+ "left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n"
						+ "left join\r\n"
						+ "(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n"
						+ "on cda.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n"
						+ "left join\r\n"
						+ "(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n"
						+ "on arm.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n"
						+ "left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n"
						+ "left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n"
						+ "left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n"
						+ "left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment left join tb_psg_deserter dsn on dsn.comm_id = c.id and dsn.status in ('1') \r\n" + "left join\r\n"
						+ "(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name 	\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n"
						+ "on nm.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n"
						+ "left join\r\n"
						+ "(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n"
						+ "where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n"
						+ "(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n"
						+ "on c.id = med.comm_id\r\n" + "left join\r\n" + "\r\n"
						+ "tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n"
						+ "COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "                    ON ma.comm_id = c.id and  ma.status ='1'\r\n"
						+ "                  LEFT JOIN tb_psg_census_detail_p census\r\n"
						+ "                    ON census.comm_id = c.id and census.status ='1' \r\n" + 
						"where op.to_sus_no= ?  \r\n"
						/// bisag 180822 v1 (commented as per miso sir guidance)
//						+ "and coalesce (ra1.description,'0') not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n"
						/*+ "and c.id not in (select case when comm_id is null then 0 else comm_id end from tb_psg_re_employment where status=1 and re_emp_select='2'  and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n"*/
						+ "and c.id  in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n"
						+ "to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
						+ "AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n"
						+ " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
						+ "GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23,24 order by "
						+ " rankT,sen_main.date_of_seniority asc limit " +pageL+" OFFSET "+startPage 
						+ ") subq "+SearchValue;

				stmt = conn.prepareStatement(q);
				stmt = setdesQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);

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

	 public long getDeserter3008Count(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
				String FDate, String LDate, String sus_no) throws SQLException {
			String SearchValue = GenerateQueryWhereClause_SQL(Search);
			int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();

				q = "select count(app.*) from(select * from(select distinct c.personnel_no,\r\n"
						+ "TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n"
						+ "TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n"
						+ "op.comm_id,op.to_sus_no,OP.id,\r\n"
						+ "TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n"
						+ "ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n"+
						"case when arm_r.arm_desc is null then arm_p1.arm_desc else arm_r.arm_desc end as parent_arm,\r\n" + 
						"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" 
						+ "TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n"
						+ "TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n"
						+ "TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n"
						+ "TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n"
						+ "case when nm_main.name is null then c.name else nm_main.name end ,\r\n"
						+ "'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n"
						+ "|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n"
						+ "|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n"
						+ "as med_cat,ro.id as rankT,sen_main.date_of_seniority as date_of_seniorityT,TO_CHAR(dsn.dt_desertion ,'DD-MON-YYYY') as dt_desertion  \r\n" + ""
								+ ",c.tnai_no,\r\n"
								+ "                       ltrim(to_char(ma.marriage_date,'DD-Mon-YYYY'),'0') AS dom,\r\n"
								+ "                       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
								+ "                            WHEN census.marital_status = 8  THEN 'Married'\r\n"
								+ "                            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
								+ "                            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
								+ "                            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
								+ "                            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
								+ "                             END AS marital_status_description\r\n"
								+ "                             "
								+ "from\r\n"
						+ "tb_psg_trans_proposed_comm_letter c \r\n"
						+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1 \r\n"
						+ "and\r\n" + "to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
						+ "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n"
						+ "inner join\r\n" + "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n"
						+ "on rk.comm_id=c.id\r\n" + "inner join\r\n"
						+ "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')\r\n"
						+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n"
						+ "inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + "left join\r\n"
						+ "(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n"
						+ "where status in ('1','2') and \r\n"
						+ "to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n"
						+ "on sen.comm_id=c.id \r\n"
						+ "left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2') \r\n"
						+ "left join\r\n"
						+ "(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n"
						+ "on app.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n"
						+ "left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n"
						+ "left join\r\n"
						+ "(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n"
						+ "on cda.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n"
						+ "left join\r\n"
						+ "(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer 	\r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n"
						+ "on arm.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2')\r\n"
						+ "left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n"
						+ "left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n"
						+ "left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n"
						+ "left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment left join tb_psg_deserter dsn on dsn.comm_id = c.id and dsn.status in ('1')	\r\n" + "left join\r\n"
						+ "(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n"
						+ "where status in ('1','2') and\r\n"
						+ "to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n"
						+ "on nm.comm_id=c.id\r\n" + "left join\r\n"
						+ "tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2')\r\n"
						+ "left join\r\n"
						+ "(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n"
						+ "where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n"
						+ "(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n"
						+ "on c.id = med.comm_id\r\n" + "left join\r\n" + "\r\n"
						+ "tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n"
						+ "COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n"
						+ "  LEFT JOIN tb_psg_census_family_married ma\r\n"
						+ "                    ON ma.comm_id = c.id and  ma.status ='1'\r\n"
						+ "                  LEFT JOIN tb_psg_census_detail_p census\r\n"
						+ "                    ON census.comm_id = c.id and  census.status ='1'\r\n" +
						"where op.to_sus_no= ?  \r\n"
						/// bisag 180822 v1 (commented as per miso sir guidance)
//						+ "and ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n"
						/*+ "and c.id not in (select case when comm_id is null then 0 else comm_id end from tb_psg_re_employment where status=1 and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n"*/
						+ "and c.id  in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n"
						+ "to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
						+ "AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n"
						+ " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
						+ "GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23,24 ) subq "+SearchValue+") app";

				PreparedStatement stmt = conn.prepareStatement(q);

				stmt = setdesQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					total = rs.getInt(1);
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
			return (long) total;
		}
	
	
///////////////////// AuthHeld /////////////////
	 public List<Map<String, Object>> getAuthHeld3008(int startPage, int pageLength, String Search, String orderColunm,
				String orderType, HttpSession sessionUserId, String FDate, String LDate, String sus_no)
				throws SQLException {

			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			
			String SearchValue = GenerateAHQueryWhereClause_SQL(Search);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				String pageL = "";

				if (pageLength == -1) {
					pageL = "ALL";
				} else {
					pageL = String.valueOf(pageLength);
				}
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q="select *, ro.id as rankT from(select \r\n" + 
						"COALESCE(base_auth,'0') as base_auth,\r\n" + 
						"COALESCE(mod_auth,'0') as mod_auth,\r\n" + 
						"COALESCE(foot_auth,'0') as foot_auth,\r\n" + 
						"COALESCE(total_auth,'0') as total_auth,\r\n" + 
						"COALESCE(total_held,'0') as total_held,\r\n" + 
						"COALESCE(description,'0') as rank from \r\n" + 
						"(select\r\n" + 
						"COALESCE(sum(n.base_auth),'0') as base_auth ,\r\n" + 
						"COALESCE(sum(n.mod_auth),'0') as mod_auth,\r\n" + 
						"COALESCE(sum(n.foot_auth),'0') as foot_auth,\r\n" + 
						"COALESCE(sum(base_auth + mod_auth + foot_auth),'0') as total_auth,\r\n" + 
						"r.id as rank_id\r\n" + 
						"from sus_pers_stdauth n\r\n" + 
						"inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper('Rank')\r\n" + 
						"where  n.sus_no = ? group by rank_id) auth\r\n" + 
						"right join\r\n" + 
						"(select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no\r\n" + 
						" from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 "+
					    "inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"where \r\n" + 
						"(to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
						"and op.to_sus_no=? \r\n" + 
						" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
						" group by c.rank,to_sus_no) held  on auth.rank_id = held.rank_id\r\n" + 
						"inner join cue_tb_psg_rank_app_master rk on rk.id = held.rank_id ORDER BY "
						+ "  rk.id asc limit " +pageL+" OFFSET "+startPage 
						+ ") subq left join tb_psg_iaff_3008_rank_wise_data ro on upper(ro.rank) = upper(subq.rank) "+SearchValue;

				stmt = conn.prepareStatement(q);
				stmt = setAHQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
				
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

	 public long getAuthHeld3008Count(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
				String FDate, String LDate, String sus_no) throws SQLException {
			String SearchValue = GenerateAHQueryWhereClause_SQL(Search);
			int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();

				q="select count(app.*) from(select *, ro.id as rankT from(select \r\n" + 
						"COALESCE(base_auth,'0') as base_auth,\r\n" + 
						"COALESCE(mod_auth,'0') as mod_auth,\r\n" + 
						"COALESCE(foot_auth,'0') as foot_auth,\r\n" + 
						"COALESCE(total_auth,'0') as total_auth,\r\n" + 
						"COALESCE(total_held,'0') as total_held,\r\n" + 
						"COALESCE(description,'0') as rank from \r\n" + 
						"(select\r\n" + 
						"COALESCE(sum(n.base_auth),'0') as base_auth ,\r\n" + 
						"COALESCE(sum(n.mod_auth),'0') as mod_auth,\r\n" + 
						"COALESCE(sum(n.foot_auth),'0') as foot_auth,\r\n" + 
						"COALESCE(sum(base_auth + mod_auth + foot_auth),'0') as total_auth,\r\n" + 
						"r.id as rank_id\r\n" + 
						"from sus_pers_stdauth n\r\n" + 
						"inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper('Rank')\r\n" + 
						"where  n.sus_no = ? group by rank_id) auth\r\n" + 
						"right join\r\n" + 
						"(select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no\r\n" + 
						" from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 "+
					    "inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"where \r\n" + 
						"(to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
						"and op.to_sus_no=?\r\n" + 
						" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" + 
						" group by c.rank,to_sus_no) held  on auth.rank_id = held.rank_id\r\n" + 
						"inner join cue_tb_psg_rank_app_master rk on rk.id = held.rank_id ) subq left join tb_psg_iaff_3008_rank_wise_data ro on upper(ro.rank) = upper(subq.rank) "+SearchValue+") app" ;

				PreparedStatement stmt = conn.prepareStatement(q);

				stmt = setAHQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					total = rs.getInt(1);
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
			return (long) total;
		}
	
	// bisag v2 290822 (split auth and held)
///////////////////// Auth /////////////////
	public List<Map<String, Object>> getAuth3008(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String FDate, String LDate, String sus_no)
			throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateAuthQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select * from (select \r\n"
					+ "ro.id as rankT,\r\n"
					+ "COALESCE(base_auth,'0') :: char as base_auth,\r\n"
					+ "COALESCE(mod_auth,'0') :: char as mod_auth,\r\n"
					+ "COALESCE(foot_auth,'0'):: char as foot_auth,\r\n"
					+ "COALESCE(total_auth,'0') :: char as total_auth,\r\n"
					+ "COALESCE(auth.rank,'0') :: char as rank from \r\n"
					+ "(select\r\n"
					+ "COALESCE(sum(n.base_auth),'0') as base_auth ,\r\n"
					+ "COALESCE(sum(n.mod_auth),'0') as mod_auth,\r\n"
					+ "COALESCE(sum(n.foot_auth),'0') as foot_auth,\r\n"
					+ "COALESCE(sum(base_auth + mod_auth + foot_auth),'0') as total_auth,\r\n"
					+ " COALESCE(description,'0') as rank,\r\n"
					+ "r.id as rank_id\r\n"
					+ "from sus_pers_stdauth n\r\n"
					+ "inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper('Rank')  \r\n"
					+ "where  PARENT_CODE='0' and sus_no=?   group by rank_id) auth\r\n"
					+ "left join tb_psg_iaff_3008_rank_wise_data_auth ro on upper(ro.rank) = upper(auth.rank)\r\n"
					+ "\r\n"
					+ "ORDER BY   ro.id asc) subq  "+SearchValue+" ";
				
				

			stmt = conn.prepareStatement(q);
			stmt = setAuthQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
			
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

	public long getAuth3008Count(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String FDate, String LDate, String sus_no) throws SQLException {
		String SearchValue = GenerateAuthQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(subq.*) from(select \r\n"
					+ "ro.id as rankT,\r\n"
					+ "COALESCE(base_auth,'0')::char as base_auth,\r\n"
					+ "COALESCE(mod_auth,'0')::char as mod_auth,\r\n"
					+ "COALESCE(foot_auth,'0')::char as foot_auth,\r\n"
					+ "COALESCE(total_auth,'0')::char as total_auth,\r\n"
					+ "COALESCE(auth.rank,'0')::char as rank from \r\n"
					+ "(select\r\n"
					+ "COALESCE(sum(n.base_auth),'0') as base_auth ,\r\n"
					+ "COALESCE(sum(n.mod_auth),'0') as mod_auth,\r\n"
					+ "COALESCE(sum(n.foot_auth),'0') as foot_auth,\r\n"
					+ "COALESCE(sum(base_auth + mod_auth + foot_auth),'0') as total_auth,\r\n"
					+ " COALESCE(description,'0') as rank,\r\n"
					+ "r.id as rank_id\r\n"
					+ "from sus_pers_stdauth n\r\n"
					+ "inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper('Rank')  \r\n"
					+ "where  PARENT_CODE='0' and sus_no=?   group by rank_id) auth\r\n"
					+ "left join tb_psg_iaff_3008_rank_wise_data_auth ro on upper(ro.rank) = upper(auth.rank)\r\n"
					+ "\r\n"
					+ "ORDER BY   ro.id asc "
					+ "  ) app "+SearchValue+" ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setAuthQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
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
		return (long) total;
	}
	
	
///////////////////// Held /////////////////
	public List<Map<String, Object>> getHeld3008(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String FDate, String LDate, String sus_no)
			throws SQLException {

		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

		String SearchValue = GenerateHeldQueryWhereClause_SQL(Search);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select held.total_held,held.rank_id,held.to_sus_no,held.description as rank\r\n"
					+ "from (select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no, rm.description \r\n"
					+ " from\r\n"
					+ "tb_psg_trans_proposed_comm_letter c \r\n"
					+ " inner join cue_tb_psg_rank_app_master rm on rm.id = c.rank and upper(rm.level_in_hierarchy) = 'RANK' "
					+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 inner join\r\n"
					+ "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n"
					+ "where status in ('1','2') and\r\n"
					+ "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n"
					+ "on rk.comm_id=c.id\r\n"
					+ "inner join\r\n"
					+ "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n"
					+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n"
					+ "where \r\n"
					+ "(to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n"
					+ "and op.to_sus_no=? \r\n"
					+ " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
					+ " group by c.rank,to_sus_no,rm.description) held  \r\n"
					+ "left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank::character varying = held.description::character varying\r\n"
					+  "  " + SearchValue+ " order by ro.id limit " + pageL + " OFFSET " + startPage ;

			stmt = conn.prepareStatement(q);
			stmt = setHeldQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
		System.out.println("stmt====="+stmt);
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

	public long getHeld3008Count(String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String FDate, String LDate, String sus_no) throws SQLException {
		String SearchValue = GenerateHeldQueryWhereClause_SQL(Search);
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(select held.total_held,held.rank_id,held.to_sus_no,held.description as rank\r\n"
					+ "from (select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no, rm.description \r\n"
					+ " from\r\n"
					+ "tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join cue_tb_psg_rank_app_master rm on rm.id = c.rank and upper(rm.level_in_hierarchy) = 'RANK' "
					+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 inner join\r\n"
					+ "(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n"
					+ "where status in ('1','2') and\r\n"
					+ "to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n"
					+ "on rk.comm_id=c.id\r\n"
					+ "inner join\r\n"
					+ "tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n"
					+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n"
					+ "where \r\n"
					+ "(to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n"
					+ "AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n"
					+ "and op.to_sus_no=? \r\n"
					+ " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
					+ " group by c.rank,to_sus_no,rm.description) held  \r\n"
					+ "left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank::character varying = held.description::character varying\r\n"
					+ "  " + SearchValue + "order by ro.id " +  ") app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setHeldQueryWhereClause_SQL(stmt, Search, FDate, LDate, roleAccess, roleSusNo, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
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
		return (long) total;
	}
	
	// bisag v2 290822 (split auth and held) ends


}
