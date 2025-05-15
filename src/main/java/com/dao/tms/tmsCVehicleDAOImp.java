package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.models.TB_TMS_EMAR_REPORT;
import com.persistance.util.HibernateUtilNA;

public class tmsCVehicleDAOImp implements tmsCVehicleDAO {
	
	
	private DataSource dataSource;
	int count = 0;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Map<String, Object>> getReportSearchConvertREQ(String sus_no,String status) {
    	 {
    		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    		 Connection conn = null;
    		 String q=null;
    		 try{	  
    		conn = dataSource.getConnection();			 
    			 
    			q = "select distinct id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n" + 
    					"pers,state,balh_for,proc,ser,modify_date,old_ba_no,count(em_no) as UH,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,\r\n" + 
    					"(select distinct unit_name from tb_tms_emar_drr_dir_dtl p join tb_miso_orbat_unt_dtl ud on ud.sus_no=p.sus_no where em_no=a.em_no and  ud.status_sus_no='Active' limit 1) as unit_name,main_status\r\n" + 
    					"from \r\n" + 
    					"(select er.id,\r\n" + 
    					"	er.sus_no,\r\n" + 
    					"	er.em_no,\r\n" + 
    					"	er.proc_from,\r\n" + 
    					"	er.classification,\r\n" + 
    					"	er.seviceable,\r\n" + 
    					"	er.reasons,\r\n" + 
    					"	er.spare_demand,\r\n" + 
    					"	er.current_km_run,\r\n" + 
    					"	er.prev_km_run,\r\n" + 
    					"	er.total_km_run,\r\n" + 
    					"	er.oh_state,\r\n" + 
    					"	ltrim(TO_CHAR(er.date_of_oh ,'dd-mm-yyyy'),'0')  as date_of_oh,\r\n" + 
    					"	er.status,\r\n" + 
    					"	er.filler_1,\r\n" + 
    					"	er.filler_2,\r\n" + 
    					"	er.filler_3,\r\n" + 
    					"	er.rispares,\r\n" + 
    					"	er.pers,\r\n" + 
    					"	er.oh_state as state,\r\n" + 
    					"	er.balh_for,\r\n" + 
    					"	er.proc_from as proc,\r\n" + 
    					"	er.seviceable as ser,\r\n" + 
    					"	ltrim(TO_CHAR(er.modify_date,'dd-mm-yyyy'),'0') as modify_date,b.old_ba_no,\r\n" + 
    					"	\r\n" + 
    					"	(select  distinct \r\n" + 
    					"		(case \r\n" + 
    					"			when (select count(mct_no) from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ? ) > 0 \r\n" + 
    					"			then (v.total)  \r\n" + 
    					"			when (select count(mct_no) as c from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ? ) = 0\r\n" + 
    					"			then 0\r\n" + 
    					"			else NULL\r\n" + 
    					"		end ) as UE\r\n" + 
    					"		from tb_tms_banum_dirctry b\r\n" + 
    					"		inner join cue_transport_ue_final v on cast(v.mct_no as numeric) = b.mct \r\n" + 
    					"		group by v.total,v.mct_no,b.mct),\r\n" + 
    					"	b.engine_no,\r\n" + 
    					"	b.chasis_no,\r\n" + 
    					"	b.mct,\r\n" + 
    					"	m.std_nomclature,\r\n" + 
    					"	ltrim(TO_CHAR(b.approve_date ,'dd-mm-yyyy'),'0')  as Date_Of_Induction,\r\n" + 
    					"	em.status as main_status\r\n" + 
    					"from \r\n" + 
    					"tb_tms_emar_report er\r\n" + 
    					"left join tb_tms_emar_drr_dir_dtl ed on ed.issue_condition in  ('2','3')\r\n" + 
    					"right join tb_tms_banum_dirctry b on b.ba_no=er.em_no and b.ba_no=ed.em_no and b.status='Active'\r\n" + 
    					"left join tb_tms_emar_drr_dir_dtl p on p.em_no=er.em_no and cast(p.classification as integer) < 5 and p.unit_sus_no = er.sus_no\r\n" + 
    					"left join tb_tms_mct_master m on m.mct=b.mct \r\n" +
    					"left join tb_tms_emar_report_main em on er.sus_no = em.sus_no \r\n" + 
    					"where er.sus_no=? and em.status=?  \r\n" + 
    					"group by er.id,b.approve_date,b.engine_no,b.chasis_no,b.mct,m.std_nomclature,b.old_ba_no,main_status)a\r\n" + 
    					"group by id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n" + 
    					"pers,state,balh_for,proc,ser,modify_date,old_ba_no,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,main_status";
	  			
    	      PreparedStatement stmt=conn.prepareStatement(q);
    	      stmt.setString(1, sus_no);
    	      stmt.setString(2, sus_no);
    	      stmt.setString(3, sus_no);
    	      stmt.setString(4, status);
    	      
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
	}
 
    
  //CHANGES by Mitesh (11-02-24)
    public ArrayList<ArrayList<String>> UpdateReportSearchConvertREQ(String sus_no,String roleType,String roleAccess) {	
    	 {
    		 ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    		 Connection conn = null;
    		 String q=null;
    		 try{	  
    			 conn = dataSource.getConnection();					
    			    String sql="";	
    			    PreparedStatement stmt =null;
    		if(sus_no == "")
    		{
    			q="select er.id,\r\n" + 
    					"	er.sus_no,\r\n" + 
    					"	er.em_no,\r\n" + 
    					"	er.proc_from,\r\n" + 
    					"	er.classification,\r\n" + 
    					"	er.seviceable,\r\n" + 
    					"	er.reasons,\r\n" + 
    					"	er.spare_demand,\r\n" + 
    					"	er.current_km_run,\r\n" + 
    					"	er.prev_km_run,\r\n" + 
    					"	er.total_km_run,\r\n" + 
    					"	er.oh_state,\r\n" + 
    					"	ltrim(TO_CHAR(er.date_of_oh ,'yyyy-mm-dd'),'0')  as date_of_oh,\r\n" + 
    					"	er.status,\r\n" + 
    					"	er.filler_1,\r\n" + 
    					"	er.filler_2,\r\n" + 
    					"	er.filler_3,\r\n" + 
    					"	er.rispares,\r\n" + 
    					"	er.pers,\r\n" + 
    					"	er.oh_state,\r\n" + 
    					"	er.balh_for,\r\n" + 
    					"	er.proc_from,\r\n" + 
    					"	er.seviceable,\r\n" +
    					"	er.ser_reason as ser_reason,\r\n" +
    					"	ltrim(TO_CHAR(er.modify_date,'dd-mm-yyyy'),'0') as modify_date,b.old_ba_no,\r\n" + 
    					"	count(b.ba_no) as UH,\r\n" + 
    					"	(select  distinct \r\n" + 
    					"		(case \r\n" + 
    					"			when (select count(mct_no) from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ?) > 0 \r\n" + 
    					"			then (v.total)  \r\n" + 
    					"			when (select count(mct_no) as c from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ?) = 0\r\n" + 
    					"			then 0\r\n" + 
    					"			else NULL\r\n" + 
    					"		end ) as UE\r\n" + 
    					"		from tb_tms_banum_dirctry b\r\n" + 
    					"		inner join cue_transport_ue_final v on cast(v.mct_no as numeric) = b.mct \r\n" + 
    					"		group by v.total,v.mct_no,b.mct),\r\n" + 
    					"	b.engine_no,\r\n" + 
    					"	b.chasis_no,\r\n" + 
    					"	b.mct,\r\n" + 
    					"	m.std_nomclature,\r\n" + 
    					"	ed.approved_date as Date_Of_Induction,\r\n" + 
    					"	ud.unit_name\r\n" + 
    					"from \r\n" + 
    					"tb_tms_emar_report er\r\n" + 
    					"inner join tb_tms_emar_drr_dir_dtl ed on ed.issue_condition = '2'\r\n" + 
    					"inner join tb_tms_banum_dirctry b on b.ba_no=er.em_no and b.ba_no=ed.em_no and b.status='Active'\r\n" + 
    					"left join tb_tms_mvcr_parta_dtl p on p.ba_no=b.ba_no and cast(p.classification as integer) < 5 and p.sus_no = er.sus_no\r\n" + 
    					"left join tb_tms_mct_master m on m.mct=b.mct\r\n" + 
    					"join tb_miso_orbat_unt_dtl ud on ud.sus_no=ed.sus_no and ud.status_sus_no='Active'\r\n" + 
    					"where er.sus_no=?\r\n" + 
    					"group by er.id,ed.approved_date,ud.unit_name,b.engine_no,b.chasis_no,b.mct,m.std_nomclature,b.old_ba_no\r\n" + "";
    		    		
    		}					
    		else
    		{	
    			q = "select distinct id,"
    					+ "sus_no,"
    					+ "em_no,"
    					+ "proc_from,"
    					+ "classification,"
    					+ "seviceable,"
    					+ "ser_reason,"
    					+ "reasons,"
    					+ "spare_demand,"
    					+ "current_km_run,"
    					+ "prev_km_run,"
    					+ "total_km_run,"
    					+ "oh_state,"
    					+ "date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n" + 
    					"pers,state,balh_for,proc,ser,modify_date,old_ba_no,count(em_no) as UH,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,\r\n" + 
    					"(select distinct unit_name from tb_tms_emar_drr_dir_dtl p join tb_miso_orbat_unt_dtl ud on ud.sus_no=p.sus_no where em_no=a.em_no and  ud.status_sus_no='Active' limit 1) as unit_name,main_status,\r\n" + 
    					"oh1_due_date,\r\n" + 
    					"oh2_due_date,oh3_due_date \r\n" +
    					"from " + 
    					"(select er.id,\r\n" + 
    					"	er.sus_no,\r\n" + 
    					"	er.em_no,\r\n" + 
    					"	er.proc_from,\r\n" + 
    					"	er.classification,\r\n" + 
    					"	er.seviceable,\r\n" + 
    					"	er.ser_reason as ser_reason,\r\n" +
    					"	er.reasons,\r\n" + 
    					"	er.spare_demand,\r\n" + 
    					"	er.current_km_run,\r\n" + 
    					"	er.prev_km_run,\r\n" + 
    					"	er.total_km_run,\r\n" + 
    					"	coalesce(er.oh_state,'') as oh_state,\r\n" + 
    					"	ltrim(TO_CHAR(er.date_of_oh ,'yyyy-mm-dd'),'0')  as date_of_oh,\r\n" + 
    					"	er.status,\r\n" + 
    					"	er.filler_1,\r\n" + 
    					"	er.filler_2,\r\n" + 
    					"	er.filler_3,\r\n" + 
    					"	er.rispares,\r\n" + 
    					"	er.pers,\r\n" + 
    					"	er.oh_state as state,\r\n" + 
    					"	er.balh_for,\r\n" + 
    					"	er.proc_from as proc,\r\n" + 
    					"	er.seviceable as ser,\r\n" + 
    					"	ltrim(TO_CHAR(er.modify_date,'dd-mm-yyyy'),'0') as modify_date,b.old_ba_no,\r\n" + 
    					"	\r\n" + 
    					"	(select  distinct \r\n" + 
    					"		(case \r\n" + 
    					"			when (select count(mct_no) from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no =? ) > 0 \r\n" + 
    					"			then (v.total)  \r\n" + 
    					"			when (select count(mct_no) as c from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no =?) = 0\r\n" + 
    					"			then 0\r\n" + 
    					"			else NULL\r\n" + 
    					"		end ) as UE\r\n" + 
    					"		from tb_tms_banum_dirctry b\r\n" + 
    					"		inner join cue_transport_ue_final v on cast(v.mct_no as numeric) = b.mct \r\n" + 
    					"		group by v.total,v.mct_no,b.mct),\r\n" + 
    					"	b.engine_no,\r\n" + 
    					"	b.chasis_no,\r\n" + 
    					"	b.mct,\r\n" + 
    					"	m.std_nomclature,\r\n" + 
    					"	ltrim(TO_CHAR(b.approve_date ,'dd-mm-yyyy'),'0')  as Date_Of_Induction ,\r\n" + 
    					"  (cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1) as oh1_due_date,\r\n" + 
    					"  (cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1) as oh2_due_date,\r\n" + 
    					"  (cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1) as oh3_due_date,\r\n" +
    					"	em.status as main_status\r\n" + 
    					"\r\n" + 
    					"from \r\n" + 
    					"tb_tms_emar_report er\r\n" + 
    					"left join tb_tms_emar_drr_dir_dtl ed on ed.issue_condition in  ('2','3')\r\n" + 
    					"\r\n" + 
    					"right join tb_tms_banum_dirctry b on b.ba_no=er.em_no and b.ba_no=ed.em_no and b.status='Active'\r\n" + 
    					"left join tb_tms_emar_drr_dir_dtl p on p.em_no=er.em_no and cast(p.classification as integer) < 5 and p.unit_sus_no = er.sus_no\r\n" + 
    					" \r\n" + 
    					"left join tb_tms_mct_master m on m.mct=b.mct left join tb_tms_emar_report_main em on er.sus_no = em.sus_no \r\n" + 
    					"\r\n" +
    					" JOIN tb_tms_mct_main_master n ON n.mct_main_id = substr(b.mct::character varying::text, 1, 4) AND n.type_of_veh::text = 'C'::text\r\n" + 
    					"inner   join (\r\n" + 
    					"                   select part2.em_no,\r\n" + 
    					"                		   CASE\r\n" + 
    					"                		            WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
    					"                		              WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n" + 
    					"                	      END AS extracted_year\r\n" + 
    					"                		    from tb_tms_emar_report part2 	group by extracted_year , em_no \r\n" + 
    					"                		) as ba_registraion on ba_registraion.em_no = er.em_no \r\n" + 
    					"                		left JOIN  (SELECT mct_main_id,\r\n" + 
    					"									MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, \r\n" + 
    					"									MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1, \r\n" + 
    					"                		           MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n" + 
    					"									MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n" + 
    					"                		          MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n" + 
    					"									MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n" + 
    					"                		              FROM \r\n" + 
    					"                		                     tb_tms_mct_main_oh_mr\r\n" + 
    					"                		                      GROUP BY mct_main_id\r\n" + 
    					"                		                ) AS oh_cal   ON oh_cal.mct_main_id = n.mct_main_id \r\n" + 
    					"where   er.sus_no=?\r\n" + 
    					"group by er.id,b.approve_date,b.engine_no,b.chasis_no,b.mct,m.std_nomclature,b.old_ba_no,main_status,ba_registraion.extracted_year,oh_cal.vintage1,oh_cal.vintage2,oh_cal.vintage3)a\r\n" + 
    					"group by id,sus_no,em_no,proc_from,classification,seviceable,ser_reason,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n" + 
    					"pers,state,balh_for,proc,ser,modify_date,old_ba_no,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,main_status,oh1_due_date,\r\n" + 
    					"oh2_due_date,oh3_due_date order by id";
 			
    		}		
    		stmt=conn.prepareStatement(q);
            stmt.setString(1,sus_no);
            stmt.setString(2,sus_no);
            stmt.setString(3,sus_no);
    	 
    	  	ResultSet rs = stmt.executeQuery();		
    	  	System.err.println(stmt);
    	  	
    	  	int Total = 0;
			int update = 0;
 	      	while(rs.next()){
 	      		ArrayList<String> list = new ArrayList<String>();
 	      		list.add(rs.getString("uh")); //0
 	      		list.add(rs.getString("ue")); //1
 	      		list.add(rs.getString("unit_name")); //2
 	      		list.add(rs.getString("Date_Of_Induction")); //3
 	      		list.add(rs.getString("ue")); //4
 	      		list.add(rs.getString("std_nomclature"));	//5			          
 	      		list.add(rs.getString("id")); //6
 	      		list.add(rs.getString("sus_no")); //7
 	      		list.add(rs.getString("em_no"));//8				      
 	      		list.add(rs.getString("classification"));//9
 	      		list.add(rs.getString("seviceable"));//10
 	      		list.add(rs.getString("reasons"));//11	
 	      		String demand = "";
 	      		if(rs.getString("spare_demand") == null)
 	      		{
 	      			demand = "";
 	      		}
 	      		else
 	      		{
 	      			demand =rs.getString("spare_demand");
 	      		}
 	      		 String spare_demand = "<input type='text' id = 'spare_demand" + rs.getString("id") + "' name='spare_demand" + rs.getString("id") + "' value = '" + demand + "' size='1' style='display:block;'>";
 		   	     
 		           list.add(spare_demand);//12
 	      
 	      	  list.add(rs.getString("current_km_run"));//13
 	      	  list.add(rs.getString("prev_km_run"));//14
 	          list.add(rs.getString("total_km_run"));//15
 	          list.add(rs.getString("date_of_oh"));//16
 	          list.add(rs.getString("status"));//17
 	          list.add(rs.getString("reasons"));//18			          
 	          list.add(rs.getString("filler_1"));//19
 	          list.add(rs.getString("filler_2"));//20
 	          list.add(rs.getString("filler_3"));//21
 	          list.add(rs.getString("engine_no"));//22
 	          list.add(rs.getString("chasis_no"));//23
 	          String rispares =rs.getString("rispares");
 	           if(rispares == null || rispares.equals(null) || rispares == "undefined" || rispares.equals("undefined"))
 	          {
 	        	   rispares = "";//24
 	          }
 	          else
 	          {
 	        	  rispares = rs.getString("rispares");//24
 	        	  
 	          }
 	           String risp = "<input type='text' id = 'Rispares" + rs.getString("id") + "' name='Rispares" + rs.getString("id") + "' value = '" + rispares + "' size='1' style='display:block;'>";
 	   	     
 	           list.add(risp);//24
 	           
 	          String pers =rs.getString("pers");
 	          if(pers == null || pers.equals(null) || pers == "undefined" || pers.equals("undefined"))
 	          {
 	        	  pers = "";//25
 	          }
 	          else
 	          {
 	        	  pers = rs.getString("pers");//25
 	          }
 	          
 	          String pers1 = "<input type='text' id = 'pers" + rs.getString("id") + "' name='pers" + rs.getString("id") + "' value = '" + pers + "' size='1' style='display:block;'>";
 		   	     
 	          list.add(pers1);//25
 	          list.add(rs.getString("state"));//26
 	          list.add(rs.getString("balh_for"));//27
 	          list.add(rs.getString("proc_from"));//28
 	          list.add(rs.getString("seviceable"));//29
 	          list.add(rs.getString("modify_date"));//30	
 	          if(rs.getString("old_ba_no") == "undefined" || rs.getString("old_ba_no") == "")
 				{
 	        	  list.add(rs.getString(""));//31		
 				}
 				else
 				{
 					list.add(rs.getString("old_ba_no"));//31		
 				}
 	          String Depo = "<span style='display:none;'> <select class='select_option'  id='Depo"+rs.getString("id")+"' class='select'    name='Depo"+rs.getString("id")+"' style='width:100%;'> </select> </span>";
 	          list.add(Depo); //32
 	          
 	          String procfrom = "<select  class='select_option'  id='ProcFrom"+rs.getString("id")+"'    name='ProcFrom"+rs.getString("id")+"'> "+     
 				"<option value='WE'>WE</option>"+
 				"<option value='OPWKS'>OPWKS</option>"+
 				"<option value='ACSFP'>ACSFP</option>"+
 				"<option value='Others'>Others</option>"+
 				"</select>";
 	          list.add(procfrom); //33
 	          Total++;
 	          int prev1 = 0;
 	          if(rs.getString("total_km_run") !=null && !rs.getString("total_km_run").equals("") && rs.getString("current_km_run") !=null && !rs.getString("current_km_run").equals(""))
 	          {
 	        	  prev1 = Integer.parseInt(rs.getString("total_km_run")) - Integer.parseInt(rs.getString("current_km_run"));
 	          }
 	          String prev ="<label  id ='prev" + rs.getString("id") + "'> " + prev1+" </label>" ; 
 		         
 	          
 	          String Balh_for ="<input type='text' id ='Balh_for" + rs.getString("id") + "' readonly='readonly' name='Balh_for" + rs.getString("id") + "' value = '" + rs.getString("total_km_run") + "' size='1' style='display:block;'>" ; 
 	     
 	      Balh_for+="<input type='hidden' value='"+rs.getString("total_km_run")+"'  id ='Balh_tot" + rs.getString("id") + "' ";
 	          String curr =   " <label id='lblcurr"+rs.getString("id")+"'>" + rs.getString("current_km_run") +  "</label>   <input type='text' id = 'current" + rs.getString("id") + "' name='current" + rs.getString("id") + "'  onkeypress=\"return isNumber0_9Only(event);\" max='300000' maxlength='6' "
 	          		+ "onkeyup=\"return uncheck('km_type"+ rs.getString("id") +"')\" value = '0' size='1' style='display:block;'>";
 	        curr+="    Inc<input type=\"radio\" id=\"km_type"+ rs.getString("id") +"\" name=\"km_type"+ rs.getString("id") +"\"  onchange=\"return sum(0,     "+ rs.getString("id") + ")\"  value=\"0\" style=\" margin-top:5px;height:15px;width:15%; \" > \r\n"
 	        		+ "							Dec<input type=\"radio\" id=\"km_2type"+ rs.getString("id") +"\" name=\"km_type"+ rs.getString("id")+"\" onchange=\"return sum(1, "+ rs.getString("id") +" )\"  value=\"1\" style=\"margin-top:5px;height:15px;width:15%;\">\r\n";
 	          String oh_state = "<select  class='select_option'  id='state"+rs.getString("id")+"'  onchange={stateChange('"+rs.getString("id")+"','"+rs.getString("state")+"')}  name='state"+rs.getString("id")+"'> ";
 				if(rs.getString("oh_state").toString().equals("Yes")) {
 					oh_state +=   "<option value='Yes'>Yes</option>";
 					oh_state +=   "<option value='No' >No</option>";
 				}else {
 					oh_state += "<option value='Yes'>Yes</option>";
 					oh_state += "<option value='No' selected='selected'>No</option>";
 				}
 	          oh_state +="</select>";
 	          
 	       /* String remarks = "<textarea  id = 'remarks" + rs.getString("id") + "' name='remarks" + rs.getString("id") + "'  style='display:none;width:100%;'></textarea>";
 	          */
 	       /* String unsv = "<select  class='select_option'  id='unsv"+rs.getString("id")+"'    name='unsv"+rs.getString("id")+"'> "+
 				"<option value='No'>No</option>"+
 				"<option value='Yes'>Yes</option>"+
 				"</select>";*/
 	        
 	    
			
 	      String remarks = "<select id='ser_reason" + rs.getString("id") + "' name='String reason" + rs.getString("id") + "' style='width:100%; display:none;'>" 
				+ "<option value='0'>--Select--</option>"
				+ "<option value='1'>In Wksp for repair</option>"
				+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
				+ "<option value='3'>MUA Demand</option>"
				+ "<option value='4'>Accident</option>" 
				+ "<option value='5'>BOH</option>"
				+ "<option value='6'>Under Discard</option>"
				+ "<option value='7'>Defect/Follow Up Report</option></select>";
			
			
		
			
			
 	    	String unsv = "<select  class='select_option'  id='unsv"+rs.getString("id")+"'    name='unsv"+rs.getString("id")+"'> "+
 	  				"<option value='No'>No</option>"+
 	  				"<option value='Yes'>Yes</option>"+
 	  				"</select>";
				

				
 	        
 	      	String classification = "<select  class='select_option'  id='classification"+rs.getString("id")+"' name='classification"+rs.getString("id")+"'  > "+
 					"<option value='1'>I</option>"+
 	 				"<option value='2'>II</option>"+
 	 				"<option value='3'>III</option>"+
 	 				"<option value='4'>IV</option>"+
 	 				"<option value='5'>V</option>"+
 	 				"<option value='6'>VI</option>"+
 	 				"<option value='7'>VII</option>"+
 	 			"</select>";
 	          
 	          String f="";
 	          String Update = "onclick=\"  if (confirm('Are You Sure you want to Update ?') ){Update('"+rs.getString("id")+"','"+ rs.getString("date_of_oh") +"','"+rs.getString("em_no")+"')}else{ return false;}\"";
 	          f = "<i class='btn btn-default btn-xs' "+Update +" title='Update Data'>Update</i>";
 		 		
 	        update++;
 	          
 	         String dt_oh = rs.getString("date_of_oh");
 			 	if(dt_oh == null)
 			 	{
 			 		dt_oh = "";
 			 	}
 			 	Date date= new Date();
 				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
 				String date_of_oh = "";
 			if(rs.getString("date_of_oh") == null || rs.getString("date_of_oh").equals(null) || rs.getString("date_of_oh").equals(date1))
 			{
 				date_of_oh =  "<input type='date' size='4' id = 'date_of_oh" + rs.getString("id") + "' name='date_of_oh" + rs.getString("id") + "'  min='1899-01-01'   max='"+ date1 +"'  value = '" + date1 + "' style='display:block;'>";
 		    }
 			else
 			{
 				date_of_oh =  "<input type='date' size='4' id = 'date_of_oh" + rs.getString("id") + "' name='date_of_oh" + rs.getString("id") + "' value = '" + rs.getString("date_of_oh") + "' style='display:block;'>";
 			}     
 	        list.add(Balh_for); //34
 	        list.add(curr); //35
 	        list.add(oh_state); //36
 	        list.add(remarks); //37
 	        
 	       
 	        list.add(unsv); //38
 	      if(roleAccess.equals("MISO") || roleAccess.equals("Unit")) {
 	      		list.add(f); //39
 	    }else {
			list.add("NA");// 15
		}
 			list.add(prev); //40
 	        list.add(date_of_oh); //41
 	        list.add(classification); // 42
 	        list.add(rs.getString("main_status"));
 	       
 	       list.add(String.valueOf(Total));// 
			list.add(String.valueOf(Update));// 
			
			//added by Mitesh (11-02-24)
			if (rs.getString("seviceable").equals("Yes")) {
			 String	r = getSer_reason(rs.getString("ser_reason"));
			 
			 String m = rs.getString("ser_reason");
			 
			 remarks =" <select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%;'>" 
						+"<option value="+m+">"+ r +"</option>"
						+ "<option value='1'>In Wksp for repair</option>"
						+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
						+ "<option value='3'>MUA Demand</option>"
						+ "<option value='4'>Accident</option>" 
						+ "<option value='5'>BOH</option>"
						+ "<option value='6'>Under Discard</option>"
						+ "<option value='7'>Defect/Follow Up Report</option>"
						+"</select>";
			}else 
			{
				remarks = "<select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%; display:none;'>" 
						+ "<option value='0'>--Select--</option>"
						+ "<option value='1'>In Wksp for repair</option>"
						+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
						+ "<option value='3'>MUA Demand</option>"
						+ "<option value='4'>Accident</option>" 
						+ "<option value='5'>BOH</option>"
						+ "<option value='6'>Under Discard</option>"
						+ "<option value='7'>Defect/Follow Up Report</option></select>";
			}
		
			System.err.println(remarks);
			list.add(rs.getString("oh1_due_date"));//46
			list.add(rs.getString("oh2_due_date"));//47
			list.add(rs.getString("oh3_due_date"));//48
			list.add(remarks); //49
			
			System.err.println();
		
					
			
			
			 alist.add(list);
 	      }	 
 	      rs.close();				      
 	      stmt.close();
 	      count++;	
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
    
    public TB_TMS_EMAR_REPORT getTB_TMS_EMAR_REPORTid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_EMAR_REPORT where id=:id");
		q.setParameter("id", id);
		TB_TMS_EMAR_REPORT list = (TB_TMS_EMAR_REPORT) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
    }
    
    
    
    public ArrayList<List<String>> getFMVCRList(String sus_no,String roleAccess) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                String sql = "";
                sql = "select distinct id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n"
                		+ "pers,state,balh_for,proc,ser,modify_date,old_ba_no,count(em_no) as UH,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,\r\n"
                		+ "(select distinct unit_name from tb_tms_emar_drr_dir_dtl p join tb_miso_orbat_unt_dtl ud on ud.sus_no=p.sus_no where em_no=a.em_no and  ud.status_sus_no='Active' limit 1) as unit_name,main_status\r\n"
                		+ ",oh1_due_date,oh2_due_date,oh3_due_date,type_of_engine,engine_failure_at,hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser, electrical_sys_ser, steer_brake_sys_ser\r\n"
                		+ "from \r\n"
                		+ "(select er.id,\r\n"
                		+ "	er.sus_no,\r\n"
                		+ "	er.em_no,\r\n"
                		+ "	er.proc_from,\r\n"
                		+ "	er.classification,\r\n"
                		+ "	er.seviceable,\r\n"
                		+ "	er.reasons,\r\n"
                		+ "	er.spare_demand,\r\n"
                		+ "	er.current_km_run,\r\n"
                		+ "	er.prev_km_run,\r\n"
                		+ "	er.total_km_run,\r\n"
                		+ "	coalesce(er.oh_state,'') as oh_state,\r\n"
                		+ "	ltrim(TO_CHAR(er.date_of_oh ,'yyyy-mm-dd'),'0')  as date_of_oh,\r\n"
                		+ "	er.status,\r\n"
                		+ "	er.filler_1,\r\n"
                		+ "	er.filler_2,\r\n"
                		+ "	er.filler_3,\r\n"
                		+ "	er.rispares,\r\n"
                		+ "	er.pers,\r\n"
                		+ "	er.oh_state as state,\r\n"
                		+ "	er.balh_for,\r\n"
                		+ "	er.proc_from as proc,\r\n"
                		+ "	er.seviceable as ser,\r\n"
                		+ "	ltrim(TO_CHAR(er.modify_date,'dd-mm-yyyy'),'0') as modify_date,b.old_ba_no,\r\n"
                		+ "	\r\n"
                		+ "	(select  distinct \r\n"
                		+ "		(case \r\n"
                		+ "			when (select count(mct_no) from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ? ) > 0 \r\n"
                		+ "			then (v.total)  \r\n"
                		+ "			when (select count(mct_no) as c from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ?) = 0\r\n"
                		+ "			then 0\r\n"
                		+ "			else NULL\r\n"
                		+ "		end ) as UE\r\n"
                		+ "		from tb_tms_banum_dirctry b\r\n"
                		+ "		inner join cue_transport_ue_final v on cast(v.mct_no as numeric) = b.mct \r\n"
                		+ "		group by v.total,v.mct_no,b.mct),\r\n"
                		+ "	b.engine_no,\r\n"
                		+ "	b.chasis_no,\r\n"
                		+ "	b.mct,\r\n"
                		+ "	z.std_nomclature,\r\n"
                		+ "	ltrim(TO_CHAR(b.approve_date ,'dd-mm-yyyy'),'0')  as Date_Of_Induction ,\r\n"
                		+ "	em.status as main_status,\r\n"
                		+ " cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1 as oh1_due_date,\r\n"
                		+ "  cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1 as oh2_due_date,\r\n"
                		+ "  cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1 as oh3_due_date,\r\n"
                		+ "engine_failure_at,\r\n"
                		+ "type_of_engine,  hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser, electrical_sys_ser, steer_brake_sys_ser\r\n"
                		+ "\r\n"
                		+ "from \r\n"
                		+ "tb_tms_emar_report er\r\n"
                		+ "left join tb_tms_emar_drr_dir_dtl ed on ed.issue_condition in  ('2','3')\r\n"
                		+ "right join tb_tms_banum_dirctry b on b.ba_no=er.em_no and b.ba_no=ed.em_no and b.status='Active'\r\n"
                		+ "JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(b.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'C'::text\r\n"
                		+ "left join tb_tms_emar_drr_dir_dtl p on p.em_no=er.em_no and cast(p.classification as integer) < 5 and p.unit_sus_no = er.sus_no\r\n"
                		+ "inner   join (\r\n"
                		+ "    select part2.em_no,\r\n"
                		+ "      CASE\r\n"
                		+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n"
                		+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n"
                		+ "        END AS extracted_year\r\n"
                		+ "     from tb_tms_emar_report part2\r\n"
                		+ ") as ba_registraion on ba_registraion.em_no = er.em_no \r\n"
                		+ "left JOIN  (SELECT  mct_main_id,MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n"
                		+ "			   \r\n"
                		+ "             MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2, MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n"
                		+ "            MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n"
                		+ "                FROM \r\n"
                		+ "                        tb_tms_mct_main_oh_mr\r\n"
                		+ "                GROUP BY \r\n"
                		+ "                        mct_main_id\r\n"
                		+ "                ) AS oh_cal   ON oh_cal.mct_main_id = m.mct_main_id \r\n"
                		+ "left join tb_tms_mct_master z on z.mct=b.mct left join tb_tms_emar_report_main em on er.sus_no = em.sus_no \r\n"
                		+ "\r\n"
                		+ "where er.sus_no= ? \r\n"
                		+ " group by er.id,b.approve_date,b.engine_no,b.chasis_no,b.mct,z.std_nomclature,b.old_ba_no,main_status,ba_registraion.extracted_year,oh_cal.vintage1,oh_cal.km1,oh_cal.vintage2\r\n"
                		+ ",oh_cal.km2,oh_cal.vintage3,oh_cal.km3)a\r\n"
                		+ "group by id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n"
                		+ "pers,state,balh_for,proc,ser,modify_date,old_ba_no,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,main_status,oh1_due_date,oh2_due_date,oh3_due_date,type_of_engine,engine_failure_at,hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser,electrical_sys_ser,steer_brake_sys_ser order by id\r\n";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sus_no);
                stmt.setString(2, sus_no);
                stmt.setString(3, sus_no);
               
                ResultSet rs = stmt.executeQuery();
                
                int total = 0;
                int updated = 0;
                String open = "";
                while (rs.next()) {
                        List<String> list = new ArrayList<String>();
                        
                        ResultSetMetaData r = rs.getMetaData();
                      //Dependent on the order metioned in the query
//                        int columnCount = r.getColumnCount();
//                        for (int i = 1; i <= columnCount; i++) {
//                       	 	list.add(rs.getString(r.getColumnName(i)));
//                        }
                        
                        list.add(rs.getString("em_no"));
                        list.add(rs.getString("proc_from"));
                        list.add(rs.getString("classification"));
                        list.add(rs.getString("seviceable"));
                        list.add(rs.getString("reasons"));
                        list.add(rs.getString("spare_demand"));
                        list.add(rs.getString("current_km_run"));
                        list.add(rs.getString("prev_km_run"));
                        list.add(rs.getString("total_km_run"));
                        list.add(rs.getString("oh_state"));
                        list.add(rs.getString("date_of_oh"));       
                        list.add(rs.getString("proc"));
                        list.add(rs.getString("old_ba_no"));                 
                        list.add(rs.getString("engine_no"));
                        list.add(rs.getString("chasis_no"));
                        list.add(rs.getString("mct"));
                        list.add(rs.getString("std_nomclature"));
                        list.add(rs.getString("Date_Of_Induction"));
                        list.add(rs.getString("unit_name"));
                        list.add(rs.getString("oh1_due_date"));
                        list.add(rs.getString("oh2_due_date"));
                        list.add(rs.getString("oh3_due_date"));
                        list.add(rs.getString("type_of_engine"));
                        list.add(rs.getString("engine_failure_at"));
                        list.add(rs.getString("hydraullic_sys_type"));
                        list.add(rs.getString("hydraullic_sys_ser_status"));
                        list.add(rs.getString("work_att_ser"));
                        list.add(rs.getString("main_clutch_ser"));
                        list.add(rs.getString("under_carriage_assy_ser"));
                        list.add(rs.getString("final_drive_ser"));
                        list.add(rs.getString("electrical_sys_ser"));
                        list.add(rs.getString("steer_brake_sys_ser"));
                        
                        alist.add(list);
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
        return alist;
}

//Added By Mitesh 
public ArrayList<List<String>> getFullFMVCRList(String sus_no,String roleAccess) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                String sql = "";
                sql = "select distinct id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n"
                		+ "pers,state,balh_for,proc,ser,modify_date,old_ba_no,count(em_no) as UH,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,\r\n"
                		+ "(select distinct unit_name from tb_tms_emar_drr_dir_dtl p join tb_miso_orbat_unt_dtl ud on ud.sus_no=p.sus_no where em_no=a.em_no and  ud.status_sus_no='Active' limit 1) as unit_name,main_status\r\n"
                		+ ",oh1_due_date,oh2_due_date,oh3_due_date,"
                		
                		+ "type_of_engine,engine_failure_at,hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser, electrical_sys_ser, steer_brake_sys_ser\r\n"
                		+ "from \r\n"
                		+ "(select er.id,\r\n"
                		+ "	er.sus_no,\r\n"
                		+ "	er.em_no,\r\n"
                		+ "	er.proc_from,\r\n"
                		+ "	er.classification,\r\n"
                		+ "	er.seviceable,\r\n"
                		+ "	er.reasons,\r\n"
                		+ "	er.spare_demand,\r\n"
                		+ "	er.current_km_run,\r\n"
                		+ "	er.prev_km_run,\r\n"
                		+ "	er.total_km_run,\r\n"
                		+ "	coalesce(er.oh_state,'') as oh_state,\r\n"
                		+ "	ltrim(TO_CHAR(er.date_of_oh ,'yyyy-mm-dd'),'0')  as date_of_oh,\r\n"
                		+ "	er.status,\r\n"
                		+ "	er.filler_1,\r\n"
                		+ "	er.filler_2,\r\n"
                		+ "	er.filler_3,\r\n"
                		+ "	er.rispares,\r\n"
                		+ "	er.pers,\r\n"
                		+ "	er.oh_state as state,\r\n"
                		+ "	er.balh_for,\r\n"
                		+ "	er.proc_from as proc,\r\n"
                		+ "	er.seviceable as ser,\r\n"
                		+ "	ltrim(TO_CHAR(er.modify_date,'dd-mm-yyyy'),'0') as modify_date,b.old_ba_no,\r\n"
                		+ "	\r\n"
                		+ "	(select  distinct \r\n"
                		+ "		(case \r\n"
                		+ "			when (select count(mct_no) from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ? ) > 0 \r\n"
                		+ "			then (v.total)  \r\n"
                		+ "			when (select count(mct_no) as c from cue_transport_ue_final where cast(mct_no as numeric) = b.mct and sus_no = ?) = 0\r\n"
                		+ "			then 0\r\n"
                		+ "			else NULL\r\n"
                		+ "		end ) as UE\r\n"
                		+ "		from tb_tms_banum_dirctry b\r\n"
                		+ "		inner join cue_transport_ue_final v on cast(v.mct_no as numeric) = b.mct \r\n"
                		+ "		group by v.total,v.mct_no,b.mct),\r\n"
                		+ "	b.engine_no,\r\n"
                		+ "	b.chasis_no,\r\n"
                		+ "	b.mct,\r\n"
                		+ "	z.std_nomclature,\r\n"
                		+ "	ltrim(TO_CHAR(b.approve_date ,'dd-mm-yyyy'),'0')  as Date_Of_Induction ,\r\n"
                		+ "	em.status as main_status,\r\n"
                		+ " cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1 as oh1_due_date,\r\n"
                		+ "  cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1 as oh2_due_date,\r\n"
                		+ "  cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1 as oh3_due_date,\r\n"
                		+ "engine_failure_at,\r\n"
                		+ "type_of_engine,  hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser, electrical_sys_ser, steer_brake_sys_ser\r\n"
                		+ "\r\n"
                		+ "from \r\n"
                		+ "tb_tms_emar_report er\r\n"
                		+ "left join tb_tms_emar_drr_dir_dtl ed on ed.issue_condition in  ('2','3')\r\n"
                		+ "right join tb_tms_banum_dirctry b on b.ba_no=er.em_no and b.ba_no=ed.em_no and b.status='Active'\r\n"
                		+ "JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(b.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'C'::text\r\n"
                		+ "left join tb_tms_emar_drr_dir_dtl p on p.em_no=er.em_no and cast(p.classification as integer) < 5 and p.unit_sus_no = er.sus_no\r\n"
                		+ "inner   join (\r\n"
                		+ "    select part2.em_no,\r\n"
                		+ "      CASE\r\n"
                		+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n"
                		+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n"
                		+ "        END AS extracted_year\r\n"
                		+ "     from tb_tms_emar_report part2\r\n"
                		+ ") as ba_registraion on ba_registraion.em_no = er.em_no \r\n"
                		+ "left JOIN  (SELECT  mct_main_id,MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n"
                		+ "			   \r\n"
                		+ "             MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2, MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n"
                		+ "            MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n"
                		+ "                FROM \r\n"
                		+ "                        tb_tms_mct_main_oh_mr\r\n"
                		+ "                GROUP BY \r\n"
                		+ "                        mct_main_id\r\n"
                		+ "                ) AS oh_cal   ON oh_cal.mct_main_id = m.mct_main_id \r\n"
                		+ "left join tb_tms_mct_master z on z.mct=b.mct left join tb_tms_emar_report_main em on er.sus_no = em.sus_no \r\n"
                		+ "\r\n"
                		+ "where er.sus_no= ? \r\n"
                		+ " group by er.id,b.approve_date,b.engine_no,b.chasis_no,b.mct,z.std_nomclature,b.old_ba_no,main_status,ba_registraion.extracted_year,oh_cal.vintage1,oh_cal.km1,oh_cal.vintage2\r\n"
                		+ ",oh_cal.km2,oh_cal.vintage3,oh_cal.km3)a\r\n"
                		+ "group by id,sus_no,em_no,proc_from,classification,seviceable,reasons,spare_demand,current_km_run,prev_km_run,total_km_run,oh_state,date_of_oh,status,filler_1,filler_2,filler_3,rispares,\r\n"
                		+ "pers,state,balh_for,proc,ser,modify_date,old_ba_no,UE,engine_no,chasis_no,mct,std_nomclature,Date_Of_Induction,main_status,oh1_due_date,oh2_due_date,oh3_due_date,type_of_engine,engine_failure_at,hydraullic_sys_type,\r\n"
                		+ "hydraullic_sys_ser_status, work_att_ser, main_clutch_ser,under_carriage_assy_ser,\r\n"
                		+ "final_drive_ser,electrical_sys_ser,steer_brake_sys_ser order by id\r\n";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sus_no);
                stmt.setString(2, sus_no);
                stmt.setString(3, sus_no);
               
                ResultSet rs = stmt.executeQuery();
                
                int total = 0;
                int updated = 0;
                String open = "";
                while (rs.next()) {
                        List<String> list = new ArrayList<String>();
                        
                        ResultSetMetaData r = rs.getMetaData();
                      //Dependent on the order metioned in the query
//                        int columnCount = r.getColumnCount();
//                        for (int i = 1; i <= columnCount; i++) {
//                       	 	list.add(rs.getString(r.getColumnName(i)));
//                        }
                        
                        list.add(rs.getString("id"));//0
                        list.add(rs.getString("sus_no"));
                        list.add(rs.getString("proc_from"));
                        list.add(rs.getString("classification"));
                        list.add(rs.getString("seviceable"));
                        
                        list.add(rs.getString("reasons"));//5
                        list.add(rs.getString("spare_demand"));
                        list.add(rs.getString("current_km_run"));
                        list.add(rs.getString("prev_km_run"));
                        list.add(rs.getString("total_km_run"));
                        list.add(rs.getString("oh_state"));//10
                        list.add(rs.getString("date_of_oh"));
                        list.add(rs.getString("status"));
                        list.add(rs.getString("filler_1"));
                        list.add(rs.getString("filler_2"));
                        list.add(rs.getString("filler_3"));//15
                        list.add(rs.getString("rispares"));
                        list.add(rs.getString("pers"));
                        list.add(rs.getString("state"));
                        list.add(rs.getString("balh_for"));
                        list.add(rs.getString("proc"));//20
                        
                        list.add(rs.getString("ser"));
                        list.add(rs.getString("modify_date"));
                        list.add(rs.getString("old_ba_no"));
                        list.add(rs.getString("UH"));
                        list.add(rs.getString("UE"));//25
                        list.add(rs.getString("engine_no"));
                        list.add(rs.getString("chasis_no"));
                        list.add(rs.getString("mct"));
                        list.add(rs.getString("std_nomclature"));
                        list.add(rs.getString("Date_Of_Induction"));//30
                        list.add(rs.getString("unit_name"));
                        list.add(rs.getString("main_status"));
                        list.add(rs.getString("oh1_due_date"));
                        list.add(rs.getString("oh2_due_date"));
                        list.add(rs.getString("oh3_due_date"));//35
                        
                        list.add(rs.getString("em_no"));
                        list.add(rs.getString("proc_from"));
                        list.add(rs.getString("classification"));
                        list.add(rs.getString("seviceable"));
                        list.add(rs.getString("reasons"));
                        list.add(rs.getString("spare_demand"));
                        list.add(rs.getString("current_km_run"));
                        list.add(rs.getString("prev_km_run"));
                        list.add(rs.getString("total_km_run"));
                        list.add(rs.getString("oh_state"));
                        list.add(rs.getString("date_of_oh"));       
                        list.add(rs.getString("proc"));
                        list.add(rs.getString("old_ba_no"));                 
                        list.add(rs.getString("engine_no"));
                        list.add(rs.getString("chasis_no"));
                        list.add(rs.getString("mct"));
                        list.add(rs.getString("std_nomclature"));
                        list.add(rs.getString("Date_Of_Induction"));
                        list.add(rs.getString("unit_name"));
                        list.add(rs.getString("oh1_due_date"));
                        list.add(rs.getString("oh2_due_date"));
                        list.add(rs.getString("oh3_due_date"));
                        list.add(rs.getString("type_of_engine"));
                        list.add(rs.getString("engine_failure_at"));
                        list.add(rs.getString("hydraullic_sys_type"));
                        list.add(rs.getString("hydraullic_sys_ser_status"));
                        list.add(rs.getString("work_att_ser"));
                        list.add(rs.getString("main_clutch_ser"));
                        list.add(rs.getString("under_carriage_assy_ser"));
                        list.add(rs.getString("final_drive_ser"));
                        list.add(rs.getString("electrical_sys_ser"));
                        list.add(rs.getString("steer_brake_sys_ser"));
                        
                        alist.add(list);
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
        return alist;
}

	 
	 
	 
		public int checkDataExists(String em_no, String month,String depot_sus_no,String table_name) {
			int count = 0;
			Connection conn = null;
			try{
				String whr ="";
				
				conn = dataSource.getConnection();
				String sql1="";
			    sql1 = "select count(*) as count from "+table_name+"\r\n" + 
			    		"where em_no = ? "+whr;
			    PreparedStatement stmt = conn.prepareStatement(sql1);
			    stmt.setString(1, em_no);
			    ResultSet rs1 = stmt.executeQuery();     
			    while(rs1.next()){
			    	count = Integer.parseInt(rs1.getString("count"));
			   
			    }	
			     rs1.close();
		         stmt.close();
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
			return count;
		}
		
		//added by Mitesh (10-02-24)
	    public String getSer_reason(String id) {
			String ser_reason ="";
			if(id.equals("1")) {
				ser_reason = "In Wksp for repair";
			}else if(id.equals("2")) {
				ser_reason = "Tyre/Tube/Bty Demand";
			}else if(id.equals("3")) {
				ser_reason = "MUA Demand";
			}else if(id.equals("4")) {
				ser_reason = "Accident";
			}else if(id.equals("5")) {
				ser_reason = "BOH";
			}else if(id.equals("6")) {
				ser_reason = "Under Discard";
			}else if(id.equals("7")) {
				ser_reason = "Defect/Follow Up Report";
			}else{
				ser_reason = "";
			}
			return ser_reason;
		}


}