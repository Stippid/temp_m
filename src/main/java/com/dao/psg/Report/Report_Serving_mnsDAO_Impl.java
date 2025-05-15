package com.dao.psg.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN_MNS;
import com.persistance.util.HibernateUtil;

public class Report_Serving_mnsDAO_Impl implements Report_Serving_mnsDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> getldate(String LDate)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";	
			try{	
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				q="SELECT (date_trunc('MONTH', (?||'01')::date) + INTERVAL '1 MONTH - 1 day')::DATE as lastdate  " ;
			  stmt=conn.prepareStatement(q);
			  stmt.setString(1,LDate);
			  
			  int i = 0;
		      ResultSet rs = stmt.executeQuery();   	      
	   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    		list.add(rs.getString("lastdate"));
					alist.add(list);
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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

	
	public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String FDate,String LDate,HttpSession session)
{
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			q="select distinct c.personnel_no,ltrim(TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
					"ltrim(TO_CHAR(op.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,op.comm_id,op.to_sus_no,OP.id,\r\n" + 
					"ltrim(TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY'),'0') as dt_of_rk,ra.description as rank,ra1.description as appt,\r\n" + 
					"cda_main.cda_acc_no,\r\n" + 
					"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
					"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_commission ,'DD-MON-YYYY'),'0') as date_of_commission ,\r\n" + 
					"ltrim(TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority,\r\n" + 
					"ltrim(TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment,case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
					"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
					"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
					"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
					"as med_cat,ro.id,sen_main.date_of_seniority  \r\n" + 
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
					"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK' "+
					"inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
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
					"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
					"left join\r\n" + 
					"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
					"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
					"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
					"on c.id = med.comm_id\r\n" + 
					"left join\r\n" + 
					"\r\n" + 
					"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
					"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
					"where op.to_sus_no=?\r\n" + 
					/// bisag 180822 v1 (commented as per miso sir guidance)
//					"and \r\n" + 
//					"(ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
					"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
					"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
					"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
					"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
					"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20 order by ro.id,sen_main.date_of_seniority ASC ";
		          stmt=conn.prepareStatement(q);	
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
		         if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") ) {
		        	 stmt.setString(10, unit_sus_no);
		         }
				  stmt.setString(11,FDate);
				  stmt.setString(12,FDate);
				  stmt.setString(13,LDate);
		  
		  int i = 0;
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  
	    	  
	    	    list.add(rs.getString("appt"));//0
				list.add(rs.getString("rank"));//1
				list.add(rs.getString("name"));//2
				list.add(rs.getString("personnel_no"));//3
				list.add(rs.getString("cda_acc_no"));//4
				list.add(rs.getString("parent_arm"));//5
				list.add(rs.getString("dt_of_tos"));//6
				list.add(rs.getString("date_of_birth"));//7
				list.add(rs.getString("date_of_commission"));//8
				list.add(rs.getString("date_of_seniority"));//9
				list.add(rs.getString("date_of_appointment"));//10	
				list.add(rs.getString("med_cat"));
				list.add(rs.getString("tenure_date"));					
				list.add(rs.getString("comm_id"));		
				list.add(rs.getString("to_sus_no"));
				list.add(rs.getString("dt_of_rk"));
				list.add(rs.getString("parent_arm"));
				alist.add(list);				
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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


	public ArrayList<ArrayList<String>> Search_Report_3008_Super(String unit_sus_no,String FDate,String LDate,HttpSession session)
    {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();		
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select distinct c.personnel_no,\r\n" + 
					"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
					"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
					"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
					"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
					"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
					"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
					"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
					"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
					"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
					"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
					"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
					"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
					"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
					"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
					"as med_cat,ro.id,sen_main.date_of_seniority \r\n" + 
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
					"\r\n" + 
					"where op.to_sus_no= ? and \r\n" + 
					"ra1.description in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n" + 
					"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
					"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
					"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
					"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
					"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20 order by ro.id,sen_main.date_of_seniority ASC "  ;
			
		    stmt=conn.prepareStatement(q);	
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
	        	 stmt.setString(10, unit_sus_no);
	         }
			  stmt.setString(11,FDate);
			  stmt.setString(12,FDate);
			  stmt.setString(13,FDate);
			  
		  int i = 0;
			
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("appt"));//0
				list.add(rs.getString("rank"));//1
				list.add(rs.getString("name"));//2
				list.add(rs.getString("personnel_no"));//3
				list.add(rs.getString("cda_acc_no"));//4
				list.add(rs.getString("parent_arm"));//5
				list.add(rs.getString("dt_of_tos"));//6
				list.add(rs.getString("date_of_birth"));//7
				list.add(rs.getString("date_of_commission"));//8
				list.add(rs.getString("date_of_seniority"));//9
				list.add(rs.getString("date_of_appointment"));//10
				list.add(rs.getString("med_cat"));
				list.add(rs.getString("tenure_date"));					
				list.add(rs.getString("comm_id"));		
				list.add(rs.getString("to_sus_no"));
				list.add(rs.getString("dt_of_rk"));
				list.add(rs.getString("parent_arm"));
				
			
				alist.add(list);				
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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

	public ArrayList<ArrayList<String>> Search_Report_3008_ReEmployed(String unit_sus_no,String FDate,String LDate,HttpSession session)

	{
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";	
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select distinct c.personnel_no,\r\n" + 
					"TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
					"TO_CHAR(op.tenure_date ,'DD-MON-YYYY') as tenure_date,\r\n" + 
					"op.comm_id,op.to_sus_no,OP.id,\r\n" + 
					"TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY') as dt_of_rk,\r\n" + 
					"ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
					"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
					"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
					"TO_CHAR(c.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"TO_CHAR(c.date_of_commission ,'DD-MON-YYYY') as date_of_commission,\r\n" + 
					"TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
					"TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
					"case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
					"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
					"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
					"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
					"as med_cat,ro.id,sen_main.date_of_seniority \r\n" + 
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
					"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20 order by ro.id,sen_main.date_of_seniority ASC";
				
				
		  stmt=conn.prepareStatement(q);	
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
        	 stmt.setString(10, unit_sus_no);
         }
		 
		  stmt.setString(11,FDate);
		  stmt.setString(12,FDate);
		  stmt.setString(13,FDate);
		  
		  int i = 0;
		
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("appt"));//0
				list.add(rs.getString("rank"));//1
				list.add(rs.getString("name"));//2
				list.add(rs.getString("personnel_no"));//3
				list.add(rs.getString("cda_acc_no"));//4
				list.add(rs.getString("parent_arm"));//5
				list.add(rs.getString("dt_of_tos"));//6
				list.add(rs.getString("date_of_birth"));//7
				list.add(rs.getString("date_of_commission"));//8
				list.add(rs.getString("date_of_seniority"));//9
				list.add(rs.getString("date_of_appointment"));//10	
				list.add(rs.getString("med_cat"));
				list.add(rs.getString("tenure_date"));					
				list.add(rs.getString("comm_id"));		
				list.add(rs.getString("to_sus_no"));
				list.add(rs.getString("dt_of_rk"));
				list.add(rs.getString("parent_arm"));
				alist.add(list);				
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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

public ArrayList<ArrayList<String>> Search_Report_3008_Deserter(String unit_sus_no,String FDate,String LDate,HttpSession session)
{
	String roleAccess = session.getAttribute("roleAccess").toString();
	String roleSusNo = session.getAttribute("roleSusNo").toString();		
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q="select distinct c.personnel_no, \r\n" + 
					"	 TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY')  as dt_of_tos,\r\n" + 
					"	 TO_CHAR(op.tenure_date ,'DD-MON-YYYY')  as tenure_date,\r\n" + 
					"	op.comm_id,op.to_sus_no,OP.id,\r\n" + 
					"	 TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY')  as dt_of_rk,\r\n" + 
					"	ra.description as rank,ra1.description as appt,cda_main.cda_acc_no,\r\n" + 
					"	case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
					"	case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment, \r\n" + 
					"	 TO_CHAR(c.date_of_birth ,'DD-MON-YYYY')  as date_of_birth, \r\n" + 
					"	 TO_CHAR(c.date_of_commission ,'DD-MON-YYYY')  as date_of_commission,\r\n" + 
					"	 TO_CHAR(sen_main.date_of_seniority ,'DD-MON-YYYY')  as date_of_seniority, \r\n" + 
					"	 TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY')  as date_of_appointment,\r\n" + 
					"	case when nm_main.name is null then c.name else nm_main.name end ,\r\n" + 
					"	'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H') \r\n" + 
					"	|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P') \r\n" + 
					"	|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E') as med_cat,ro.id,sen_main.date_of_seniority,TO_CHAR(dsn.dt_desertion ,'DD-MON-YYYY')  as dt_desertion    \r\n" + 
					"	from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"	inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n" + 
					"	to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n" + 
					"	AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')) \r\n" + 
					"	inner join \r\n" + 
					"	(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank where status in ('1','2') and\r\n" + 
					"	to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk \r\n" + 
					"	on rk.comm_id=c.id \r\n" + 
					"	inner join\r\n" + 
					"	tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
					"	inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
					"   inner join tb_psg_iaff_3008_rank_wise_data ro on ro.rank_code = ra.code\r\n" + 
					"	left join \r\n" + 
					"	(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
					"	where status in ('1','2') and \r\n" + 
					"	to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen \r\n" + 
					"	on sen.comm_id=c.id  \r\n" + 
					"	left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2')\r\n" + 
					"	left join\r\n" + 
					"	(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment\r\n" + 
					"	where status in ('1','2') and \r\n" + 
					"	to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
					"	on app.comm_id=c.id\r\n" + 
					"	left join \r\n" + 
					"	tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')\r\n" + 
					"  left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT' \r\n" + 
					"	left join \r\n" + 
					"	(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
					"	where status in ('1','2') and\r\n" + 
					"	to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
					"	on cda.comm_id=c.id\r\n" + 
					"	left join\r\n" + 
					"	tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2')\r\n" + 
					"	left join \r\n" + 
					"	(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
					"	where status in ('1','2') and\r\n" + 
					"	to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
					"	on arm.comm_id=c.id \r\n" + 
					"	left join\r\n" + 
					"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2') \r\n" + 
					"	left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service  \r\n" + 
					"	left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
					"	left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
					"	left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment"
					+ " left join tb_psg_deserter dsn on dsn.comm_id = c.id and dsn.status in ('1','2')	\r\n" + 
					"	left join (select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name\r\n" + 
					"	where status in ('1','2') and\r\n" + 
					"	to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm \r\n" + 
					"	on nm.comm_id=c.id \r\n" + 
					"	left join \r\n" + 
					"	tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
					"	left join\r\n" + 
					"	(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
					"	where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
					"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med \r\n" + 
					"	on c.id = med.comm_id\r\n" + 
					"	left join tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
					"	COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'') \r\n" + 
					"	where op.to_sus_no=?  \r\n" + 
					"	--and (ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
					"	and c.id not in(select rm.comm_id from tb_psg_re_employment rm where rm.status=1 and  \r\n" + 
					"	to_date(to_char(rm.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
					"	and rm.comm_id not in (select ds1.comm_id from tb_psg_deserter ds1 where status=1 and  rm.comm_id = ds1.comm_id and \r\n" + 
					"	(to_date(to_char(ds1.dt_desertion,'YYYY/MM/DD'),'YYYY/MM/DD'))  >=  (to_date(to_char(rm.date_of_tos,'YYYY/MM/DD'),'YYYY/MM/DD')) )) \r\n" + 
					"	and c.id  in (select comm_id from tb_psg_deserter ds where status=1 and \r\n" + 
					"	to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
					"	AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') > to_date(?,'YYYY/MM/DD')))  \r\n" + 
					"	GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21 order by ro.id,sen_main.date_of_seniority ASC  " ;
			
							
		    stmt=conn.prepareStatement(q);	
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
	        	 stmt.setString(10, unit_sus_no);
	         }
			  
			  stmt.setString(11,FDate);
			  stmt.setString(12,FDate);
			  stmt.setString(13,FDate);
		  int i = 0;
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("appt"));//0
				list.add(rs.getString("rank"));//1
				list.add(rs.getString("name"));//2
				list.add(rs.getString("personnel_no"));//3
				list.add(rs.getString("cda_acc_no"));//4
				list.add(rs.getString("parent_arm"));//5
				list.add(rs.getString("dt_of_tos"));//6
				list.add(rs.getString("date_of_birth"));//7
				list.add(rs.getString("date_of_commission"));//8
				list.add(rs.getString("date_of_seniority"));//9
				list.add(rs.getString("date_of_appointment"));//10	
				list.add(rs.getString("med_cat"));//11
				list.add(rs.getString("tenure_date"));		//12			
				list.add(rs.getString("comm_id"));		//13
				list.add(rs.getString("to_sus_no")); //14
				list.add(rs.getString("dt_of_rk")); //15
				list.add(rs.getString("parent_arm"));//16
				list.add(rs.getString("dt_desertion")); //17
				alist.add(list);				
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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

public ArrayList<ArrayList<String>> Search_Report_3008_Auth_Held(String roleSusNo,String FDate,String LDate)

{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";	
		try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select \r\n" + 
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
					" group by c.rank,to_sus_no) held  on auth.rank_id = held.rank_id\r\n" + 
					"inner join cue_tb_psg_rank_app_master rk on rk.id = held.rank_id " ;
					
			
			
		  stmt=conn.prepareStatement(q);
		  stmt.setString(1,roleSusNo);
		  stmt.setString(2,roleSusNo);
		  stmt.setString(3,FDate);
		  stmt.setString(4,FDate);
		  stmt.setString(5,roleSusNo);
		  
		  int i = 0;
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("rank"));
				list.add(rs.getString("base_auth"));					
				list.add(rs.getString("mod_auth"));
				list.add(rs.getString("foot_auth"));
				list.add(rs.getString("total_auth"));	
				list.add(rs.getString("total_held"));	
				
				alist.add(list);				
	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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



//bisag v2 290822 (split auth and held)
public ArrayList<ArrayList<String>> Search_Report_3008_Auth(String roleSusNo,String FDate,String LDate)

{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";	
		try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select \r\n"
					+ "COALESCE(base_auth,'0') as base_auth,\r\n"
					+ "COALESCE(mod_auth,'0') as mod_auth,\r\n"
					+ "COALESCE(foot_auth,'0') as foot_auth,\r\n"
					+ "COALESCE(total_auth,'0') as total_auth,\r\n"
					+ "COALESCE(auth.rank,'0') as rank from \r\n"
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
					+ "left join tb_psg_iaff_3008_rank_wise_data ro on upper(ro.rank) = upper(auth.rank) " ;
					
			
			
		  stmt=conn.prepareStatement(q);
		  stmt.setString(1,roleSusNo);
//		  stmt.setString(2,roleSusNo);
//		  stmt.setString(3,FDate);
//		  stmt.setString(4,FDate);
//		  stmt.setString(5,roleSusNo);
		  
		  int i = 0;
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("rank"));
				list.add(rs.getString("base_auth"));					
				list.add(rs.getString("mod_auth"));
				list.add(rs.getString("foot_auth"));
				list.add(rs.getString("total_auth"));	
//				list.add(rs.getString("total_held"));	
				
				alist.add(list);				
	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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
public ArrayList<ArrayList<String>> Search_Report_3008_Held(String roleSusNo,String FDate,String LDate)

{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";	
		try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select held.total_held,held.rank_id,held.to_sus_no,held.description as rank\r\n"
					+ "from (select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no, rm.description \r\n"
					+ " from\r\n"
					+ "tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join cue_tb_psg_rank_app_master rm on rm.id = c.rank and upper(rm.level_in_hierarchy) = 'RANK'\r\n"
					+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3','4') and op.status=1 inner join\r\n"
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
					+ "  and left(c.personnel_no,2) in ('NR','NS') "
					+ " group by c.rank,to_sus_no,rm.description) held  \r\n"
					+ "left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank::character varying = held.description::character varying\r\n"
					+ "order by ro.id" ;
					
			
			
		  stmt=conn.prepareStatement(q);
//		  stmt.setString(1,roleSusNo);
		  stmt.setString(1,FDate);
		  stmt.setString(2,FDate);
		  stmt.setString(3,FDate);
		  stmt.setString(4,roleSusNo);
		  
		  int i = 0;
	      ResultSet rs = stmt.executeQuery();   	      
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("rank"));
//				list.add(rs.getString("base_auth"));					
//				list.add(rs.getString("mod_auth"));
//				list.add(rs.getString("foot_auth"));
//				list.add(rs.getString("total_auth"));	
				list.add(rs.getString("total_held"));	
				
				alist.add(list);				
	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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

//bisag v2 290822 (split auth and held) ends


///------Search 3008
public ArrayList<ArrayList<String>> Search_Report_Version(String month, String year ,HttpSession session,String status ,String unit_sus_no){
	String roleAccess = session.getAttribute("roleAccess").toString();
	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String roleType = session.getAttribute("roleType").toString();
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
	     if (roleAccess.equals("Unit")) {
		 if(!roleSusNo.equals("")) {
				qry += " and ms.sus_no = ? ";
			}
         }
         if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {
        	 if(!unit_sus_no.equals("")) {
     			qry += " and ms.sus_no = ? ";
     		}
         }
		if(!month.equals("0")) {
			qry += " and m.month = ? ";
		}
		if(!year.equals("")) {
			qry += " and m.year = ? ";
		}
		if(!status.equals("")) {
			qry += "and m.status= cast(? as integer)";
		}
		q="select distinct m.sus_no,ms.unit_name,TO_CHAR( TO_DATE (m.month::text, 'MM'), 'Month') AS month_name,"
				+ "m.month,m.year,m.version from tb_psg_iaff_3008_main_mns m \r\n" +
			" inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = m.sus_no and ms.status_sus_no='Active' "+
			" where m.id!= 0  "+qry;
		
		 stmt=conn.prepareStatement(q);	
		 
		 
	
			if(!qry.equals("")) {  
				
				 int j =1;	
				 
		     if (roleAccess.equals("Unit")) {
			   if (roleSusNo != "") {
					 stmt.setString(j, roleSusNo);
					 j += 1;
				  }	
	         }
	         if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")) {
	        	 if (unit_sus_no != "") {
					 stmt.setString(j, unit_sus_no);
					 j += 1;
				  }	
	         }
			  if(!month.equals("0")) {
				 stmt.setString(j, month);
				 j += 1;
			  }	
		      if (year != "") {
				 stmt.setString(j, year);
				 j += 1;
			  }	
		      if (status != "") {
				 stmt.setString(j, status);
					 //j += 1;
			   }
			}
			
			
		
		int i = 0;
		
		ResultSet rs = stmt.executeQuery();   	      
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("unit_name"));//0
			list.add(rs.getString("month_name"));//1
			list.add(rs.getString("year"));//2
			list.add(rs.getString("version"));//3
			
			
			String f1 = "";
			String f4 = "";
			if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")){
				
				String View = "onclick=\"  if (confirm('Are You Sure You Want to Approve This ?') ){editData("+rs.getString("month")+ ",'"+ rs.getString("year") +"' ,'"+ rs.getString("version") +"' ,'"+ rs.getString("sus_no") +"','"+ status +"')}else{ return false;}\"";
			     f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";
			     
			     String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This ?') ){deleteData(" + rs.getString("month") + ",'"+ rs.getString("year") +"' ,'"+ rs.getString("version") +"' ,'"+ rs.getString("sus_no") +"')}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Dustbin'></i>";
	
			}
			 if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("1")){

				 String View = "onclick=\"  if (confirm('Are You Sure You Want to View This ?') ){editData("
							+ rs.getString("month") + ",'" + rs.getString("year") + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "','"+ status +"')}else{ return false;}\"";
					f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";

						
				 
				 String Printreport = "onclick=\"  if (confirm('Are You Sure You Want to Download This Report ?') ){print_report("
							+ rs.getString("month") + ",'" + rs.getString("year") + "' ,'" + rs.getString("version")
							+ "' ,'" + rs.getString("sus_no") + "')}else{ return false;}\"";
					f1 = "<i class='fa fa-download'  " + Printreport + " title='Download Report'></i>";
					
					 } 
			list.add(f4);
			list.add(f1);
			alist.add(list);	
		}
		rs.close();
		stmt.close();
		conn.close();
	}catch (SQLException e) {
		//throw new RuntimeException(e);
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
public Boolean Delete_Report_3008(String username,String roleSusNo,String month, String year,String version) throws SQLException 
{
	PreparedStatement pstmt = null;
	Connection conn = dataSource.getConnection();	
	  try {
			conn.setAutoCommit(false);
			
		  List<TB_PSG_IAFF_3008_MAIN_MNS> version0 = Get_3008_VersionData(month,year,roleSusNo);
				 
				 
			    pstmt = conn.prepareStatement(Delete_Report_3008_Version());
				pstmt.setString(1,username);
				pstmt.setInt(2,version0.get(0).getId());
				pstmt.setString(3,roleSusNo);
				pstmt.setString(4,month);
			    pstmt.setString(5,year);
				pstmt.executeUpdate();
				
			    pstmt = conn.prepareStatement(Delete_Report_3008_Main());
				pstmt.setString(1,username);
				pstmt.setString(2,roleSusNo);
				pstmt.setString(3,month);
			    pstmt.setString(4,year);
			    pstmt.setString(5, version);
				pstmt.executeUpdate();

			    pstmt = conn.prepareStatement(Delete_Report_3008_Serving());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
			    pstmt.setString(3,year);
			    pstmt.setString(4, version);
				pstmt.executeUpdate();
			
			    pstmt = conn.prepareStatement(Delete_Report_3008_SuperNumarary());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
			    pstmt.setString(3,year);
			    pstmt.setString(4, version);
				pstmt.executeUpdate();
					
			    pstmt = conn.prepareStatement(Delete_Report_3008_Re_Employment());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
			    pstmt.setString(3,year);
			    pstmt.setString(4, version);
				pstmt.executeUpdate();
						
			    pstmt = conn.prepareStatement(Delete_Report_3008_Deserter());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
			    pstmt.setString(3,year);
			    pstmt.setString(4, version);
				pstmt.executeUpdate();
				
				
				 pstmt = conn.prepareStatement(Delete_Report_3008_auth());
					pstmt.setString(1,roleSusNo);
					pstmt.setString(2,month);
				    pstmt.setString(3,year);
				    pstmt.setString(4, version);
					pstmt.executeUpdate();
								
							
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1)
		{
			conn.rollback();
			return false;
		}
	return true;
}

public String Delete_Report_3008_Version()
{
	return  "update tb_psg_iaff_3008_main_mns set status='3', modified_by= ?, modified_date= now()::timestamp \r\n" + 
	  " where id=? and sus_no=? and month=? and year=? " ;
}
public String Delete_Report_3008_Main()
{
	return  "update tb_psg_iaff_3008_main_details_mns set status='3', modified_by= ?, modified_date= now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Delete_Report_3008_Serving()
{
	return  "update tb_psg_iaff_3008_serving_mns set status='3' \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Delete_Report_3008_SuperNumarary()
{
	return  "update tb_psg_iaff_3008_supernumerary_mns set status='3' \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Delete_Report_3008_Re_Employment()
{
	return  "update tb_psg_iaff_3008_re_employeement_mns set status='3' \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Delete_Report_3008_Deserter()
{
	return  "update tb_psg_iaff_3008_deserter_mns set status='3' \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}




public String Delete_Report_3008_auth()
{
	return  "update tb_psg_iaff_3008_auth_mns set status='3' \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}
////--------------------For Approval

public ArrayList<ArrayList<String>> Search_Report_Serving_Approval(String month, String year , HttpSession session,String version,String unit_sus_no)
{
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			

			
			q="select * from (select distinct sr.personal_no as personnel_no,\r\n" + 
					"TO_CHAR(sr.date_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
					"TO_CHAR(sr.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"TO_CHAR(sr.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n" + 
					"TO_CHAR(sr.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
					"TO_CHAR(sr.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
					"sr.parent_arm,sr.regiment,sr.cda_acc_no,sr.rank,sr.appointment as appt,sr.name,sr.med_cat,sr.date_of_seniority as dt_sen,"
					+ "   TO_CHAR(sr.date_of_rank,'DD-MON-YYYY') as date_of_rank,\r\n"
					+ "               sr.tnai_no,\r\n"
					+ "               sr.marital_status,\r\n"
					+ "               sr.date_of_marriage,\r\n"
					+ "               remarks\r\n" + 
					"						from tb_psg_iaff_3008_main_mns m \r\n" + 
					"inner join tb_psg_iaff_3008_serving_mns  sr on sr.month = m.month and \r\n" + 
					"sr.year= m.year and sr.version = m.version and sr.sus_no = m.sus_no "+
					" where m.sus_no = ?  and m.year = ? and m.month = ? and m.version = ?  )np left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank = np.rank\r\n" + 
					" order by ro.id,np.dt_sen ";
			
						
			 stmt=conn.prepareStatement(q);	
		
			 if (roleAccess.equals("Unit")) {
				 stmt.setString(1, roleSusNo);
			 }
			 if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
				 stmt.setString(1, unit_sus_no);
			 }
			  stmt.setString(2, year);
			  stmt.setString(3, month);
			  stmt.setString(4, version);
			  
			int i = 0;
		
			ResultSet rs = stmt.executeQuery();   	      
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("appt"));//0
			list.add(rs.getString("rank"));//1
			list.add(rs.getString("name"));//2
			list.add(rs.getString("personnel_no"));//3
			list.add(rs.getString("cda_acc_no"));//4
			if(rs.getString("regiment")!= null ) {
				list.add(rs.getString("regiment"));//5				
			}else {
				list.add(rs.getString("parent_arm"));//5
			}
			list.add(rs.getString("dt_of_tos"));//6
			list.add(rs.getString("date_of_birth"));//7
			list.add(rs.getString("date_of_commission"));//8
			list.add(rs.getString("date_of_seniority"));//9
			list.add(rs.getString("date_of_appointment"));//10	
			list.add(rs.getString("med_cat"));//11
			list.add(rs.getString("date_of_rank"));//12
			list.add(rs.getString("tnai_no"));//13
			list.add(rs.getString("marital_status"));//14
			list.add(rs.getString("date_of_marriage"));//15
			list.add(rs.getString("remarks"));//16
			
			alist.add(list);	
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			//throw new RuntimeException(e);
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


public ArrayList<ArrayList<String>> Search_Report_3008_Super_Approval(String month, String year , HttpSession session ,String version,String unit_sus_no)
{
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			q="select distinct sp.personal_no as personnel_no,\r\n" + 
			"TO_CHAR(sp.date_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
			"TO_CHAR(sp.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
			"TO_CHAR(sp.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n" + 
			"TO_CHAR(sp.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
			"TO_CHAR(sp.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
			"sp.parent_arm,sp.regiment,sp.cda_acc_no,sp.rank,sp.appointment as appt,sp.name,sp.med_cat,"
			+ "  TO_CHAR(sp.date_of_rank,'DD-MON-YYYY') as date_of_rank,\r\n"
			+ "               sp.tnai_no,\r\n"
			+ "               sp.marital_status,\r\n"
			+ "               sp.date_of_marriage,\r\n"
			+ "               sp.remarks\r\n" + 
			"						from tb_psg_iaff_3008_main_mns m \r\n" + 
			"inner join tb_psg_iaff_3008_supernumerary_mns sp on sp.month = m.month and \r\n" + 
			"sp.year= m.year and sp.version = m.version  and sp.sus_no = m.sus_no " +
			" where m.sus_no = ?  and m.year = ? and m.month = ? and m.version = ? ";
			
					
			stmt=conn.prepareStatement(q);	
			if (roleAccess.equals("Unit")) {
			stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("DGMS")|| roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
			stmt.setString(1, unit_sus_no);
			}
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			int i = 0;
			
			System.err.println("super num mns report\n"+stmt);
			ResultSet rs = stmt.executeQuery();   	      
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("appt"));//0
			list.add(rs.getString("rank"));//1
			list.add(rs.getString("name"));//2
			list.add(rs.getString("personnel_no"));//3
			list.add(rs.getString("cda_acc_no"));//4
//			list.add(rs.getString("regiment"));//5
			//26-01-1994
			if(rs.getString("regiment")!= null ) {
				list.add(rs.getString("regiment"));//5
				
			}else {
				list.add(rs.getString("parent_arm"));//5
			}
			list.add(rs.getString("dt_of_tos"));//6
			list.add(rs.getString("date_of_birth"));//7
			list.add(rs.getString("date_of_commission"));//8
			list.add(rs.getString("date_of_seniority"));//9
			list.add(rs.getString("date_of_appointment"));//10	
			list.add(rs.getString("med_cat"));//11
			list.add(rs.getString("date_of_rank"));//12
			list.add(rs.getString("tnai_no"));//13
			list.add(rs.getString("marital_status"));//14
			list.add(rs.getString("date_of_marriage"));//15
			list.add(rs.getString("remarks"));//16
			alist.add(list);				
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			//throw new RuntimeException(e);
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

public ArrayList<ArrayList<String>> Search_Report_3008_ReEmployed_Approval(String month, String year , HttpSession session ,String version,String unit_sus_no)
{
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			
			q="select distinct re.personal_no as personnel_no,\r\n" + 
			"TO_CHAR(re.date_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
			"TO_CHAR(re.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
			"TO_CHAR(re.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n" + 
			"TO_CHAR(re.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
			"TO_CHAR(re.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
			"re.parent_arm,re.regiment,re.cda_acc_no,re.rank,re.appointment as appt,re.name,re.med_cat\r\n" + 
			"						from tb_psg_iaff_3008_main_mns m \r\n" + 
			"inner join tb_psg_iaff_3008_re_employeement_mns re on re.month = m.month and \r\n" + 
			"re.year= m.year and re.version = m.version  and re.sus_no = m.sus_no "+
			" where m.sus_no = ?  and m.year = ? and m.month = ? and m.version = ? ";
			
					
			stmt=conn.prepareStatement(q);	
			if (roleAccess.equals("Unit")) {
			stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
			stmt.setString(1, unit_sus_no);
			}
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			int i = 0;
			ResultSet rs = stmt.executeQuery();   	      
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("appt"));//0
			list.add(rs.getString("rank"));//1
			list.add(rs.getString("name"));//2
			list.add(rs.getString("personnel_no"));//3
			list.add(rs.getString("cda_acc_no"));//4
//			list.add(rs.getString("regiment"));//5
			//26-01-1994
			if(rs.getString("regiment")!= null ) {
				list.add(rs.getString("regiment"));//5
				
			}else {
				list.add(rs.getString("parent_arm"));//5
			}
			list.add(rs.getString("dt_of_tos"));//6
			list.add(rs.getString("date_of_birth"));//7
			list.add(rs.getString("date_of_commission"));//8
			list.add(rs.getString("date_of_seniority"));//9
			list.add(rs.getString("date_of_appointment"));//10	
			list.add(rs.getString("med_cat"));//11
			alist.add(list);				
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			//throw new RuntimeException(e);
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


public ArrayList<ArrayList<String>> Search_Report_3008_Deserter_Approval(String month, String year , HttpSession session ,String version,String unit_sus_no)
{
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select distinct ds.personal_no as personnel_no,\r\n" + 
			"TO_CHAR(ds.date_of_tos ,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
			"TO_CHAR(ds.date_of_birth ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
			"TO_CHAR(ds.date_of_commission ,'DD-MON-YYYY') as date_of_commission ,\r\n" + 
			"TO_CHAR(ds.date_of_seniority ,'DD-MON-YYYY') as date_of_seniority,\r\n" + 
			"TO_CHAR(ds.date_of_appointment ,'DD-MON-YYYY') as date_of_appointment,\r\n" + 
			"ds.parent_arm,ds.regiment,ds.cda_acc_no,ds.rank,ds.appointment as appt,ds.name,ds.med_cat,"
			+ "   TO_CHAR(ds.date_of_rank,'DD-MON-YYYY') as date_of_rank,\r\n"
			+ "               ds.tnai_no,\r\n"
			+ "               ds.marital_status,\r\n"
			+ "               ds.date_of_marriage,\r\n"
			+ "               ds.remarks\r\n" + 
			"						from tb_psg_iaff_3008_main_mns m \r\n" + 
			"inner join tb_psg_iaff_3008_deserter_mns ds on ds.month = m.month and \r\n" + 
			"ds.year= m.year and ds.version = m.version  and ds.sus_no = m.sus_no  "+
			" where m.sus_no = ?  and m.year = ? and m.month = ? and m.version = ? ";
			
			stmt=conn.prepareStatement(q);	
			if (roleAccess.equals("Unit")) {
			 stmt.setString(1, roleSusNo);
			}
			if (roleAccess.equals("MISO") || roleAccess.equals("DGMS") || roleAccess.equals("Line_dte") || roleAccess.equals("Formation")) {
			 stmt.setString(1, unit_sus_no);
			}
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, version);
			int i = 0;
			System.err.println("Deserter mns report\n"+stmt);
			ResultSet rs = stmt.executeQuery();   	      
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("appt"));//0
			list.add(rs.getString("rank"));//1
			list.add(rs.getString("name"));//2
			list.add(rs.getString("personnel_no"));//3
			list.add(rs.getString("cda_acc_no"));//4
//			list.add(rs.getString("regiment"));//5
			//26-01-1994
			if(rs.getString("regiment")!= null ) {
				list.add(rs.getString("regiment"));//5
				
			}else {
				list.add(rs.getString("parent_arm"));//5
			}
			list.add(rs.getString("dt_of_tos"));//6
			list.add(rs.getString("date_of_birth"));//7
			list.add(rs.getString("date_of_commission"));//8
			list.add(rs.getString("date_of_seniority"));//9
			list.add(rs.getString("date_of_appointment"));//10	
			list.add(rs.getString("med_cat"));//11
			list.add(rs.getString("date_of_rank"));//12
			list.add(rs.getString("tnai_no"));//13
			list.add(rs.getString("marital_status"));//14
			list.add(rs.getString("date_of_marriage"));//15
			list.add(rs.getString("remarks"));//16
			alist.add(list);				
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
			//throw new RuntimeException(e);
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
public Boolean Approve_Report_3008(String username,String roleSusNo,String month, String year,String version) throws SQLException 
{
	PreparedStatement pstmt = null;
	Connection conn = dataSource.getConnection();	
	  try {
			conn.setAutoCommit(false);
			
		  List<TB_PSG_IAFF_3008_MAIN_MNS> version0 = Get_3008_VersionData(month,year,roleSusNo);
				 
			    pstmt = conn.prepareStatement(Approve_Report_3008_Version());
				pstmt.setString(1,username);
				pstmt.setInt(2,version0.get(0).getId());
				pstmt.setString(3,roleSusNo);
				pstmt.setString(4,month);
			    pstmt.setString(5,year);
				pstmt.executeUpdate();
				
			    pstmt = conn.prepareStatement(Approve_Report_3008_Main());
				pstmt.setString(1,username);
				pstmt.setString(2,roleSusNo);
				pstmt.setString(3,month);
			    pstmt.setString(4,year);
			    pstmt.setString(5, version);
				pstmt.executeUpdate();

			    pstmt = conn.prepareStatement(Approve_Report_3008_Serving());
			    pstmt.setString(1,username);
			    pstmt.setString(2,roleSusNo);
				pstmt.setString(3,month);
			    pstmt.setString(4,year);
			    pstmt.setString(5, version);
				pstmt.executeUpdate();
			
			    pstmt = conn.prepareStatement(Approve_Report_3008_SuperNumarary());
			    pstmt.setString(1,username);
			    pstmt.setString(2,roleSusNo);
				pstmt.setString(3,month);
			    pstmt.setString(4,year);
			    pstmt.setString(5, version);
				pstmt.executeUpdate();
					
//			    pstmt = conn.prepareStatement(Approve_Report_3008_Re_Employment());
//			    pstmt.setString(1,username);
//			    pstmt.setString(2,roleSusNo);
//				pstmt.setString(3,month);
//			    pstmt.setString(4,year);
//			    pstmt.setString(5, version);
//				pstmt.executeUpdate();
						
			    pstmt = conn.prepareStatement(Approve_Report_3008_Deserter());
			    pstmt.setString(1,username);
			    pstmt.setString(2,roleSusNo);
				pstmt.setString(3,month);
			    pstmt.setString(4,year);
			    pstmt.setString(5, version);
				pstmt.executeUpdate();
					
				
				/*
				 * pstmt = conn.prepareStatement(Approve_Report_3008_auth());
				 * pstmt.setString(1,username); pstmt.setString(2,roleSusNo);
				 * pstmt.setString(3,month); pstmt.setString(4,year); pstmt.setString(5,
				 * version); pstmt.executeUpdate();
				 */
					// bisag v2 290822 (split auth and held)
					pstmt = conn.prepareStatement(Approve_Report_3008_held());
				    pstmt.setString(1,username);
				    pstmt.setString(2,roleSusNo);
					pstmt.setString(3,month);
				    pstmt.setString(4,year);
				    pstmt.setString(5, version);
					pstmt.executeUpdate();
					// bisag v2 290822 (split auth and held) end
							
			pstmt.close();
			conn.commit();
			conn.close();
		} catch (Exception e1)
		{
			conn.rollback();
			return false;
		}
	return true;
}

public String Approve_Report_3008_Version()
{
	return  "update tb_psg_iaff_3008_main_mns set status='1', modified_by= ?, modified_date= now()::timestamp \r\n" + 
	  " where id=? and sus_no=? and month=? and year=? " ;
}
public String Approve_Report_3008_Main()
{
	return  "update tb_psg_iaff_3008_main_details_mns set status='1', modified_by= ?, modified_date= now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Approve_Report_3008_Serving()
{
	return  "update tb_psg_iaff_3008_serving_mns set status='1' , approved_by = ? , approved_date = now()::timestamp  \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Approve_Report_3008_SuperNumarary()
{
	return  "update tb_psg_iaff_3008_supernumerary_mns set status='1', approved_by = ? , approved_date = now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

//public String Approve_Report_3008_Re_Employment()
//{
//	return  "update tb_psg_iaff_3008_re_employeement_mns set status='1' , approved_by = ? , approved_date = now()::timestamp \r\n" + 
//	  " where sus_no=? and month=? and year=? and version=?" ;
//}

public String Approve_Report_3008_Deserter()
{
	return  "update tb_psg_iaff_3008_deserter_mns set status='1', approved_by = ? , approved_date = now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}


public String Approve_Report_3008_auth()
{
	return  "update tb_psg_iaff_3008_auth_mns set status='1', approved_by = ? , approved_date = now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}

public String Approve_Report_3008_held()
{
	return  "update tb_psg_iaff_3008_held set status='1', approved_by = ? , approved_date = now()::timestamp \r\n" + 
	  " where sus_no=? and month=? and year=? and version=?" ;
}


////-------------INSERT 3008
@Autowired
private Report_3008DAO report_3008DAO;

public Boolean Insert_Report_mns_3008(String username,String roleSusNo,String FDate ,String month, String year ,
		String userId,String present_return_no,String present_return_dt,String remarks,String LDate,String remarks1,String remarks2,String remarks3,String remarks4,String remarks5,String remarks6) throws SQLException 
{
	PreparedStatement pstmt = null;
	Connection conn = dataSource.getConnection();
	LocalDate currentDate = LocalDate.now();
	int currentYear = currentDate.getYear();
    int currentMonth = currentDate.getMonthValue();
    int currentDay = currentDate.getDayOfMonth();
    String currentdate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
	
//	  try {
			conn.setAutoCommit(false);
			int val = 0;
			int count = 0;
			

			
//				        ArrayList<ArrayList<String>> list_auth_held =	Search_Report_3008_Auth_Held(roleSusNo,FDate,LDate);
				        ArrayList<ArrayList<String>> list_auth =	Search_Report_3008_Auth(roleSusNo,FDate,LDate);
				        ArrayList<ArrayList<String>> list_held =	Search_Report_3008_Held(roleSusNo,FDate,LDate);
			    int totalAuth = 0;
				int totalHeld = 0;
				int sum_held=0;
				int sum_auth=0;
				

				
				
				for(int i=0;i<list_auth.size();i++) {
					String totalAuth1= list_auth.get(i).get(4);
											
					if(totalAuth1 == null || totalAuth1.equals(null)) {
						totalAuth =0;
					}else{
						totalAuth = Integer.parseInt(totalAuth1);	
					}
					sum_auth += totalAuth;
					}
				for(int i=0;i<list_held.size();i++) {
					String totalHeld1= list_held.get(i).get(1);
					
					if(totalHeld1 == null || totalHeld1.equals(null)) {
						totalHeld =0;
					}else{
						totalHeld = Integer.parseInt(totalHeld1);
					}						
					sum_held += totalHeld;		
					}
				
				ArrayList<ArrayList<String>> wepe = report_3008DAO.getEstablishNo(roleSusNo);
				
				String we_pe_no = "";
				if(wepe.size() > 0) {
					we_pe_no = wepe.get(0).get(0);
				}else {
					we_pe_no = "";
				}
				
			ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
			
			
		
		  List<TB_PSG_IAFF_3008_MAIN_MNS> version = Get_3008_VersionData(month,year,roleSusNo);
			if (version.size() == 0) {
				
				val = 1;
				pstmt = conn.prepareStatement(Insert_Report_3008_Version());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
				pstmt.setString(3,year);
				pstmt.setString(4,String.valueOf(val));
				pstmt.setInt(5,0);
				pstmt.setString(6, username);
				
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(Insert_Report_3008_Main_Details());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
				pstmt.setString(3,year);
				pstmt.setString(4,String.valueOf(val));
				
				pstmt.setString(5, orbatlist.get(0).get(1));//cmd sus
				pstmt.setString(6, orbatlist.get(0).get(2));//corp sus
				pstmt.setString(7, orbatlist.get(0).get(3));//div sus
				pstmt.setString(8, orbatlist.get(0).get(4));//bde sus
				
				pstmt.setString(9, String.valueOf(sum_auth));//auth
				pstmt.setString(10, String.valueOf(sum_held));//held
				pstmt.setString(11, present_return_no);
				pstmt.setString(12, present_return_dt);
				pstmt.setString(13, we_pe_no);//we_pe_no
				
				pstmt.setString(14, orbatlist.get(0).get(5));//cmd unit 
				pstmt.setString(15, orbatlist.get(0).get(6));//corp unit
				pstmt.setString(16, orbatlist.get(0).get(7));//div unit
				pstmt.setString(17, orbatlist.get(0).get(8));//bde unit
				pstmt.setString(18, username);
				pstmt.setString(19, remarks);
				pstmt.setString(20, remarks1);
				pstmt.setString(21, remarks2);
				pstmt.setString(22, remarks3);
				pstmt.setString(23, remarks4);
				pstmt.setString(24, remarks5);
				pstmt.setString(25, remarks6);
				
				pstmt.executeUpdate();
				
			} else {
				 count=  Integer.parseInt(version.get(0).getVersion());
				 val = count + 1;
				 
			    pstmt = conn.prepareStatement(Update_Report_3008_Version());
			    pstmt.setString(1,month);
				pstmt.setString(2,year);
				pstmt.setString(3,String.valueOf(val));
				pstmt.setString(4,username);
				pstmt.setInt(5,version.get(0).getId());
				
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(Insert_Report_3008_Main_Details());
				pstmt.setString(1,roleSusNo);
				pstmt.setString(2,month);
				pstmt.setString(3,year);
				pstmt.setString(4,String.valueOf(val));
				
				pstmt.setString(5, orbatlist.get(0).get(1));//cmd sus
				pstmt.setString(6, orbatlist.get(0).get(2));//corp sus
				pstmt.setString(7, orbatlist.get(0).get(3));//div sus
				pstmt.setString(8, orbatlist.get(0).get(4));//bde sus
				
				pstmt.setString(9, String.valueOf(sum_auth));//auth
				pstmt.setString(10, String.valueOf(sum_held));//held
				pstmt.setString(11, present_return_no);
				pstmt.setString(12, present_return_dt);
				pstmt.setString(13, we_pe_no);//we_pe_no
				
				pstmt.setString(14, orbatlist.get(0).get(5));//cmd unit 
				pstmt.setString(15, orbatlist.get(0).get(6));//corp unit
				pstmt.setString(16, orbatlist.get(0).get(7));//div unit
				pstmt.setString(17, orbatlist.get(0).get(8));//bde unit
				pstmt.setString(18, username);
				pstmt.setString(19, remarks);
				pstmt.setString(20, remarks1);
				pstmt.setString(21, remarks2);
				pstmt.setString(22, remarks3);
				pstmt.setString(23, remarks4);
				pstmt.setString(24, remarks5);
				pstmt.setString(25, remarks6);
				pstmt.executeUpdate();
				
			}
		
			
			pstmt = conn.prepareStatement(Insert_Report_3008_Serving());
			pstmt.setString(1,userId);
			pstmt.setString(2,month);
			pstmt.setString(3,year);
			
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5,month);
			pstmt.setString(6, year);

			pstmt.setString(7,FDate);
			pstmt.setString(8,FDate);
			
			pstmt.setString(9,LDate);
			pstmt.setString(10,LDate);
			pstmt.setString(11,LDate);
			//pstmt.setString(12,LDate);
			pstmt.setString(12,currentdate);
			pstmt.setString(13,LDate);
			pstmt.setString(14,LDate);
			pstmt.setString(15,LDate);
			
	    	 pstmt.setString(16, roleSusNo);
	    	 pstmt.setString(17,FDate);
	    	 pstmt.setString(18,FDate);
	    	 pstmt.setString(19,LDate);
	    		
				 pstmt.executeUpdate(); 
		
			
			
			pstmt = conn.prepareStatement(Insert_Report_3008_SuperNumarary());
			pstmt.setString(1,userId);
			pstmt.setString(2,month);
			pstmt.setString(3,year);
			
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5,month);
			pstmt.setString(6, year);

			pstmt.setString(7,FDate);
			pstmt.setString(8,FDate);
			pstmt.setString(9,LDate);
			pstmt.setString(10,LDate);
			
			pstmt.setString(11,LDate);
			//pstmt.setString(12,FDate);
			pstmt.setString(12,LDate);
			pstmt.setString(13,LDate);
			pstmt.setString(14,LDate);
			pstmt.setString(15,LDate);
			pstmt.setString(16, roleSusNo);
			pstmt.setString(17,FDate);
			pstmt.setString(18,FDate);
			pstmt.setString(19,FDate);
			
			pstmt.executeUpdate();			
			

			
			pstmt = conn.prepareStatement(Insert_Report_3008_Deserter());
			pstmt.setString(1,userId);
			pstmt.setString(2,month);
			pstmt.setString(3,year);
			
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5,month);
			pstmt.setString(6, year);

			pstmt.setString(7,FDate);
			pstmt.setString(8,FDate);
			pstmt.setString(9,LDate);
			pstmt.setString(10,LDate);
			
			pstmt.setString(11,LDate);
			pstmt.setString(12,LDate);
			pstmt.setString(13,LDate);
			pstmt.setString(14,LDate);
			pstmt.setString(15,LDate);
			pstmt.setString(16, roleSusNo);
			pstmt.setString(17,FDate);
			pstmt.setString(18,FDate);
			pstmt.setString(19,FDate);
			
			pstmt.executeUpdate();
			

			
			pstmt = conn.prepareStatement(Insert_Report_3008_held());
			pstmt.setString(1,userId);
			
			pstmt.setString(2,month);
			pstmt.setString(3,year);
			
			pstmt.setString(4, roleSusNo);
			pstmt.setString(5,month);
			pstmt.setString(6, year);

			pstmt.setString(7,FDate);
			pstmt.setString(8,FDate);
			
			pstmt.setString(9,FDate);
			
			
			
			
			pstmt.setString(10, roleSusNo);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.commit();
			conn.close();
//		} catch (Exception e1)
//		{

//			conn.rollback();
//			return false;
//		}
	return true;
}

/////////////////////For Insert ---------------

	
	public  List<TB_PSG_IAFF_3008_MAIN_MNS> Get_3008_VersionData(String month, String year ,String roleSusNo) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_IAFF_3008_MAIN_MNS where month=:month and year=:year and sus_no=:roleSusNo ";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("month", month).setString("year", year).setString("roleSusNo", roleSusNo);
		@SuppressWarnings("unchecked")
		List<TB_PSG_IAFF_3008_MAIN_MNS> list = (List<TB_PSG_IAFF_3008_MAIN_MNS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	
	
	
	
	public String Insert_Report_3008_Version()
	{ 
		return  "INSERT INTO tb_psg_iaff_3008_main_mns \r\n" + 
		"(sus_no,month,year,version,status,created_by,created_date) VALUES (?,?,?,?,?,?,now()::timestamp) ";
	
	}

	public String Update_Report_3008_Version()
	{
		return  "update tb_psg_iaff_3008_main_mns set month= ?, year= ?, version= ?,status='0',\r\n" + 
		  "modified_by= ?, modified_date= now()::timestamp where id=?" ;
	}
	
	public String Insert_Report_3008_Main_Details()
	{
		
		return  "INSERT INTO tb_psg_iaff_3008_main_details_mns \r\n" + 
		"(sus_no,month,year,version,cmd_sus,corp_sus,div_sus,bde_sus,auth,held,present_return_no,present_return_dt,we_pe_no,"
		+ "cmd_unit,corp_unit,div_unit,bde_unit,created_by,created_date,remarks,remarksfornursingoffrstos,remarksfornursingoffrssos,remarksfornursingoffrspostout,remarksfornursingoffrspostin"
		+ ",remarksfornursingoffrsunderretire,remarksdistr) "+
	    "VALUES (?,?,?,?,?,?,?,?,?,?,?,CAST(? as Date),?,?,?,?,?,?,now()::timestamp,?,?,?,?,?,?,?) ";
		
	}
	public String Insert_Report_3008_Serving()
	{
	return  "INSERT INTO tb_psg_iaff_3008_serving_mns \r\n" + 
			"(personal_no,sus_no,unit_name,parent_arm,regiment,cda_acc_no,rank,appointment,\r\n" + 
			"	date_of_tos, tenure_date, date_of_birth, date_of_seniority, date_of_commission,date_of_appointment," +
			"  created_date,created_by,month,year,version,name,med_cat,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks)\r\n" + 
			"select DISTINCT c.personnel_no ,op.to_sus_no,\r\n" + 
			"ms.unit_name,\r\n" + 
			"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
			"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
			"cda_main.cda_acc_no,\r\n" + 
			"ra.description as rank,\r\n" + 
			"ra1.description as appt,\r\n" + 
			"op.dt_of_tos,\r\n" + 
			"op.tenure_date,\r\n" + 
			"c.date_of_birth,\r\n" + 
			"sen_main.date_of_seniority,\r\n" + 
			"c.date_of_commission,app_main.date_of_appointment,\r\n" + 
			"now()::timestamp,\r\n" + 
			"(select username from  logininformation U where U.userid= cast(? as integer)) as userid, ? as month, ? as year , \r\n" + 
			"(select version from tb_psg_iaff_3008_main_mns where \r\n" + 
			" sus_no = ? and month = ? and year = ? ) as version,  \r\n" + 
			"case when nm_main.name is null then c.name else nm_main.name end, " +
			"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
			"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
			"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
			"as med_cat ,\r\n"
			+ " rk.dt_of_rk,\r\n"
			+ "       c.tnai_no,\r\n"
			+ "        \r\n"
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
			+ ") AS marital_status_description ,"

			+ ""
			+ "'' as remarks\r\n" + 
			"from\r\n" + 
			"tb_psg_trans_proposed_comm_letter c \r\n" + 
			"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3','4') and op.status=1 and \r\n" + 
			"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
			"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
			"inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = c.unit_sus_no and ms.status_sus_no='Active'\r\n" + 
			"inner join\r\n" + 
			"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
			"on rk.comm_id=c.id\r\n" + 
			"inner join\r\n" + 
			"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
			"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
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
			"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2') \r\n" + 
			"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
			"left join\r\n" + 
			"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
			"on cda.comm_id=c.id\r\n" + 
			"left join\r\n" + 
			"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2') \r\n" + 
			"left join\r\n" + 
			"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
			"on arm.comm_id=c.id\r\n" + 
			"left join\r\n" + 
			"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2') \r\n" + 
			"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
			"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
			"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
			"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 
			"left join\r\n" + 
			"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm \r\n" + 
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
			"\r\n"
			+ "               LEFT JOIN tb_psg_census_family_married ma\r\n"
			+ "    ON ma.comm_id = c.id\r\n"
			+ "   AND ma.status = 1\r\n"
			+ "  LEFT JOIN tb_psg_census_detail_p census\r\n"
			+ "    ON census.comm_id = c.id\r\n"
			+ "   AND census.status = '1'\r\n" + 
			"where op.to_sus_no=?\r\n" + 
			"and \r\n" + 
			"(ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
			"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
			"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
			"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
			"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
			" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" +
			"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,20,22,23,24,25";
	
	}
	
	public String Insert_Report_3008_SuperNumarary() {
		
		return "INSERT INTO tb_psg_iaff_3008_supernumerary_mns \r\n" + 
				"(personal_no,sus_no,unit_name,parent_arm,regiment,cda_acc_no,rank,appointment,\r\n" + 
				"	date_of_tos, tenure_date, date_of_birth, date_of_seniority, date_of_commission,date_of_appointment," +
				"  created_date,created_by,month,year,version,name,med_cat,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks)\r\n" + 
				"select DISTINCT c.personnel_no ,op.to_sus_no,\r\n" + 
				"ms.unit_name,\r\n" + 
				"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
				"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
				"cda_main.cda_acc_no,\r\n" + 
				"ra.description as rank,\r\n" + 
				"ra1.description as appt,\r\n" + 
				"op.dt_of_tos,\r\n" + 
				"op.tenure_date,\r\n" + 
				"c.date_of_birth,\r\n" + 
				"sen_main.date_of_seniority,\r\n" + 
				"c.date_of_commission,app_main.date_of_appointment,\r\n" + 
				"now()::timestamp,\r\n" + 
				"(select username from  logininformation U where U.userid= cast(? as integer)) as userid, ? as month, ? as year , \r\n" + 
				"(select version from tb_psg_iaff_3008_main_mns where \r\n" + 
				" sus_no = ? and month = ? and year = ? ) as version,  \r\n" + 
				"case when nm_main.name is null then c.name else nm_main.name end, " +
				"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
				"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
				"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" +
				"as med_cat,\r\n"
				+ "rk.dt_of_rk,\r\n"
				+ "       c.tnai_no,\r\n"
				+ "             to_char(ma.marriage_date,'DD-MON-YYYY') as marriage_date,       \r\n"
				+ "        \r\n"
				+ "       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
				+ "            WHEN census.marital_status = 8  THEN 'Married'\r\n"
				+ "            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
				+ "            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
				+ "            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
				+ "            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
				+ "             END AS marital_status_description,'' as remarks\r\n" +
				"from\r\n" + 
				"tb_psg_trans_proposed_comm_letter c \r\n" + 
				"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1 \r\n" + 
				"and\r\n" + 
				"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
				"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))"+
				"inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = c.unit_sus_no and ms.status_sus_no='Active'\r\n" + 
				"inner join\r\n" + 
				"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
				"where status in ('1','2') and\r\n" + 
				"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
				"on rk.comm_id=c.id\r\n" + 
				"inner join\r\n" + 
				"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
				"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
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
				"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2') \r\n" + 
				"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
				"left join\r\n" + 
				"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
				"where status in ('1','2') and\r\n" + 
				"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
				"on cda.comm_id=c.id\r\n" + 
				"left join\r\n" + 
				"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2') \r\n" + 
				"left join\r\n" + 
				"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
				"where status in ('1','2') and\r\n" + 
				"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
				"on arm.comm_id=c.id\r\n" + 
				"left join\r\n" + 
				"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2') \r\n" + 
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
				"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
				"left join\r\n" + 
				"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
				"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
				"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
				"on c.id = med.comm_id\r\n" + 
				"left join\r\n" + 
				"\r\n" + 
				"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
				"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
				"  LEFT JOIN tb_psg_census_family_married ma\r\n"
				+ "    ON ma.comm_id = c.id\r\n"
				+ "   AND ma.status = 1\r\n"
				+ "  LEFT JOIN tb_psg_census_detail_p census\r\n"
				+ "    ON census.comm_id = c.id\r\n"
				+ "   AND census.status = '1'\r\n" + 
				"where op.to_sus_no= ? and \r\n" + 
				"ra1.description in (select appointment from tb_psg_link_appoinment_with_super_supernumerary)\r\n" + 
				"and c.id not in (select comm_id from tb_psg_re_employment where status=1 and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
				"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
				"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
				"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
				" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" +
				"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,20,22,23,24,25 "  ;
		
		}

//	public String Insert_Report_3008_Re_Employeement()
//	{
//	
//	return "INSERT INTO tb_psg_iaff_3008_re_employeement_mns \r\n" + 
//		"(personal_no,sus_no,unit_name,parent_arm,regiment,cda_acc_no,rank,appointment,\r\n" + 
//		"	date_of_tos, tenure_date, date_of_birth, date_of_seniority, date_of_commission,date_of_appointment," +
//		"  created_date,created_by,month,year,version,name,med_cat)\r\n" + 
//		"select DISTINCT c.personnel_no ,op.to_sus_no,\r\n" + 
//		"ms.unit_name,\r\n" + 
//		"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
//		"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
//		"cda_main.cda_acc_no,\r\n" + 
//		"ra.description as rank,\r\n" + 
//		"ra1.description as appt,\r\n" + 
//		"op.dt_of_tos,\r\n" + 
//		"op.tenure_date,\r\n" + 
//		"c.date_of_birth,\r\n" + 
//		"sen_main.date_of_seniority,\r\n" + 
//		"c.date_of_commission,app_main.date_of_appointment,\r\n" + 
//		"now()::timestamp,\r\n" + 
//		"(select username from  logininformation U where U.userid= cast(? as integer)) as userid, ? as month, ? as year , \r\n" + 
//		"(select version from tb_psg_iaff_3008_main_mns where \r\n" + 
//		" sus_no = ? and month = ? and year = ? ) as version,  \r\n" + 
//		"case when nm_main.name is null then c.name else nm_main.name end, " +
//		"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
//		"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
//		"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
//		"as med_cat\r\n" +
//		"from\r\n" + 
//		"tb_psg_trans_proposed_comm_letter c \r\n" + 
//		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and \r\n" + 
//		"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
//		"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
//		"inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = c.unit_sus_no and ms.status_sus_no='Active'\r\n" + 
//		"inner join\r\n" + 
//		"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
//		"on rk.comm_id=c.id\r\n" + 
//		"inner join\r\n" + 
//		"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
//		"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
//		"left join\r\n" + 
//		"(select MAX(applicablity_date) as date_of_app,comm_id from tb_psg_change_of_seniority \r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen\r\n" + 
//		"on sen.comm_id=c.id\r\n" + 
//		"left join tb_psg_change_of_seniority sen_main on sen.date_of_app = sen_main.applicablity_date and sen.comm_id = sen_main.comm_id and sen_main.status in ('1','2') \r\n" + 
//		"left join\r\n" + 
//		"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
//		"on app.comm_id=c.id\r\n" + 
//		"left join\r\n" + 
//		"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2') \r\n" + 
//		"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
//		"left join\r\n" + 
//		"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
//		"on cda.comm_id=c.id\r\n" + 
//		"left join\r\n" + 
//		"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2') \r\n" + 
//		"left join\r\n" + 
//		"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
//		"on arm.comm_id=c.id\r\n" + 
//		"left join\r\n" + 
//		"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2') \r\n" + 
//		"left join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
//		"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" +
//		"left join tb_miso_orbat_arm_code arm_p1 on arm_p1.arm_code = c.parent_arm  \r\n" + 
//		"left join tb_miso_orbat_arm_code arm_r1 on arm_r1.arm_code =  c.regiment \r\n" + 		
//		"left join\r\n" + 
//		"(select MAX(created_date) as dt_of_name,comm_id from tb_psg_change_name \r\n" + 
//		"where status in ('1','2') and\r\n" + 
//		"to_date(to_char(created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) nm\r\n" + 
//		"on nm.comm_id=c.id\r\n" + 
//		"left join\r\n" + 
//		"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
//		"left join\r\n" + 
//		"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
//		"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
//		"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
//		"on c.id = med.comm_id\r\n" + 
//		"left join\r\n" + 
//		"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
//		"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n" + 
//		"\r\n" + 
//		"where op.to_sus_no=? \r\n" + 
//		"--and (ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
//		"and c.id  in (select comm_id from tb_psg_re_employment where status=1  and re_emp_select='2' and  to_date(to_char(date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD'))\r\n" + 
//		"and c.id not in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
//		"to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
//		"AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))) \r\n" + 
//		" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" +
//		"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,20";
//	
//	}

	public String Insert_Report_3008_Deserter()
	{

		return "INSERT INTO tb_psg_iaff_3008_deserter_mns \r\n" + 
			"(personal_no,sus_no,unit_name,parent_arm,regiment,cda_acc_no,rank,appointment,\r\n" + 
			"	date_of_tos, tenure_date, date_of_birth, date_of_seniority, date_of_commission,date_of_appointment," +
			"  created_date,created_by,month,year,version,name,med_cat,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks)\r\n" + 
			"select DISTINCT c.personnel_no ,op.to_sus_no,\r\n" + 
			"ms.unit_name,\r\n" + 
			"case when arm_p.arm_desc is null then arm_p1.arm_desc else arm_p.arm_desc end as parent_arm,\r\n" + 
			"case when arm_r.arm_desc is null then arm_r1.arm_desc else arm_r.arm_desc end as regiment,\r\n" + 
			"cda_main.cda_acc_no,\r\n" + 
			"ra.description as rank,\r\n" + 
			"ra1.description as appt,\r\n" + 
			"op.dt_of_tos,\r\n" + 
			"op.tenure_date,\r\n" + 
			"c.date_of_birth,\r\n" + 
			"sen_main.date_of_seniority,\r\n" + 
			"c.date_of_commission,app_main.date_of_appointment,\r\n" + 
			"now()::timestamp,\r\n" + 
			"(select username from  logininformation U where U.userid= cast(? as integer)) as userid, ? as month, ? as year , \r\n" + 
			"(select version from tb_psg_iaff_3008_main_mns where \r\n" + 
			" sus_no = ? and month = ? and year = ? ) as version,  \r\n" + 
			"case when nm_main.name is null then c.name else nm_main.name end ," +
			"'S' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='S')  || ';H' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='H')\r\n" + 
			"|| ';A' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='A') || ';P' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='P')\r\n" + 
			"|| ';E' || STRING_AGG(med_main.shape_value ,',') FILTER (where med_main.shape='E')\r\n" + 
			"as med_cat,\r\n"
			+ "rk.dt_of_rk,\r\n"
			+ "       c.tnai_no,\r\n"
			+ "             to_char(ma.marriage_date,'DD-MON-YYYY') as marriage_date,       \r\n"
			+ "        \r\n"
			+ "       CASE WHEN census.marital_status = 9  THEN 'Divorced'\r\n"
			+ "            WHEN census.marital_status = 8  THEN 'Married'\r\n"
			+ "            WHEN census.marital_status = 13 THEN 'Separated'\r\n"
			+ "            WHEN census.marital_status = 10 THEN 'Unmarried'\r\n"
			+ "            WHEN census.marital_status = 11 THEN 'Widow'\r\n"
			+ "            WHEN census.marital_status = 12 THEN 'Widower'\r\n"
			+ "             END AS marital_status_description,'' as remarks\r\n" + 
			/*"--- ? as present_return_no,  CAST(? as Date) as present_return_dt \r\n" + */
			"from\r\n" + 
			"tb_psg_trans_proposed_comm_letter c \r\n" + 
			"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 and\r\n" + 
			"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
			"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
			"inner join tb_miso_orbat_unt_dtl ms on ms.sus_no = c.unit_sus_no and ms.status_sus_no='Active'\r\n" + 
			"inner join\r\n" + 
			"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
			"on rk.comm_id=c.id\r\n" + 
			"inner join\r\n" + 
			"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2')  \r\n" + 
			"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
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
			"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id and app_main.status in ('1','2')  \r\n" + 
			"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
			"left join\r\n" + 
			"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
			"on cda.comm_id=c.id\r\n" + 
			"left join\r\n" + 
			"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id and cda_main.status in ('1','2') \r\n" + 
			"left join\r\n" + 
			"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
			"where status in ('1','2') and\r\n" + 
			"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
			"on arm.comm_id=c.id\r\n" + 
			"left join\r\n" + 
			"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id and arm_main.status in ('1','2') \r\n" + 
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
			"tb_psg_change_name nm_main on nm.dt_of_name = nm_main.created_date and nm.comm_id=nm_main.comm_id and nm_main.status in ('1','2') \r\n" + 
			"left join\r\n" + 
			"(select MAX(m.date_of_authority) as dt_of_medical,m.comm_id from tb_psg_medical_category m\r\n" + 
			"where status in ('1','2') and m.shape='S'  and (m.date_of_authority is null or\r\n" + 
			"(to_date(to_char(m.date_of_authority,'YYYY/MM/DD'),'YYYY/MM/DD')  <= to_date(?,'YYYY/MM/DD'))) group by 2) med\r\n" + 
			"on c.id = med.comm_id\r\n" + 
			"left join\r\n" + 
			"\r\n" + 
			"tb_psg_medical_category  med_main on med_main.comm_id=med.comm_id and med_main.status in ('1','2')  and \r\n" + 
			"COALESCE(to_char(med.dt_of_medical,'YYYY/MM/DD'),'') = COALESCE(to_char(med_main.date_of_authority,'YYYY/MM/DD'),'')\r\n"
			+ "  LEFT JOIN tb_psg_census_family_married ma\r\n"
			+ "    ON ma.comm_id = c.id\r\n"
			+ "   AND ma.status = 1\r\n"
			+ "  LEFT JOIN tb_psg_census_detail_p census\r\n"
			+ "    ON census.comm_id = c.id\r\n"
			+ "   AND census.status = '1'\r\n" + 
			"where op.to_sus_no=?  \r\n" + 
			"--and (ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
			" and c.id not in(select rm.comm_id from tb_psg_re_employment rm where rm.status=1 and   \r\n" + 
			"	to_date(to_char(rm.date_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')  \r\n" + 
			"	and rm.comm_id not in (select ds1.comm_id from tb_psg_deserter ds1 where status=1 and  rm.comm_id = ds1.comm_id and  \r\n" + 
			"	(to_date(to_char(ds1.dt_desertion,'YYYY/MM/DD'),'YYYY/MM/DD'))  >=  (to_date(to_char(rm.date_of_tos,'YYYY/MM/DD'),'YYYY/MM/DD')) ))  \r\n" + 
			"	and c.id  in (select comm_id from tb_psg_deserter ds where status=1 and  \r\n" + 
			"	to_date(to_char(ds.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') \r\n" + 
			"	AND (ds.dt_recovered is null OR to_date(to_char(ds.dt_recovered,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))  \r\n" + 
			" AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')" +
			"GROUP BY 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,20 ,22,23,24,25" ;
    }
	
//	public String Insert_Report_3008_auth()
//	{
//
//		return "INSERT INTO tb_psg_iaff_3008_auth_mns \r\n" + 
//				"(base_auth,mod_auth,foot_auth,total_auth,total_held,rank,user_id,month,year,version,sus_no\r\n" + 
//				" )" + 
//			"select \r\n" + 
//			"COALESCE(base_auth,'0') as base_auth,\r\n" + 
//			"COALESCE(mod_auth,'0') as mod_auth,\r\n" + 
//			"COALESCE(foot_auth,'0') as foot_auth,\r\n" + 
//			"COALESCE(total_auth,'0') as total_auth," + 
//			"COALESCE(total_held,'0') as total_held," + 
//			"COALESCE(description,'0') as rank ," + 
//			 "(select username from  logininformation U where U.userid= cast(? as integer)) as user_id, ? as month, ? as year , " + 
//			" (select version from tb_psg_iaff_3008_main_mns where  \r\n" + 
//			" sus_no = ? and month = ? and year = ? ) as version,held.to_sus_no as sus_no from \r\n" + 
//			"(select\r\n" + 
//			"COALESCE(sum(n.base_auth),'0') as base_auth ,\r\n" + 
//			"COALESCE(sum(n.mod_auth),'0') as mod_auth,\r\n" + 
//			"COALESCE(sum(n.foot_auth),'0') as foot_auth,\r\n" + 
//			"COALESCE(sum(base_auth + mod_auth + foot_auth),'0') as total_auth,\r\n" + 
//			"r.id as rank_id\r\n" + 
//			"from sus_pers_stdauth n\r\n" + 
//			"inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper('Rank')\r\n" + 
//			"where  n.sus_no = ? group by rank_id) auth\r\n" + 
//			"right join\r\n" + 
//			"(select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no\r\n" + 
//			" from\r\n" + 
//			"tb_psg_trans_proposed_comm_letter c \r\n" + 
//			"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1 inner join\r\n" + 
//			"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
//			"where status in ('1','2') and\r\n" + 
//			"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
//			"on rk.comm_id=c.id\r\n" + 
//			"inner join\r\n" + 
//			"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id and rk_main.status in ('1','2') \r\n" + 
//			"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
//			"where \r\n" + 
//			"(to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
//			"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')))\r\n" + 
//			"and op.to_sus_no=? \r\n" + 
//			" group by c.rank,to_sus_no) held  on auth.rank_id = held.rank_id\r\n" + 
//			"inner join cue_tb_psg_rank_app_master rk on rk.id = held.rank_id " ;
//    }
	
	
	public String Insert_Report_3008_held()
	{

		return "INSERT INTO tb_psg_iaff_3008_Held_mns \r\n" + 
				"(base_auth,mod_auth,foot_auth,total_auth,total_held,rank,user_id,month,year,version,sus_no\r\n" + 
				" )" + 
			"select '0' as base_auth,\r\n"
			+ "			'0' as mod_auth,\r\n"
			+ "			'0' as foot_auth,\r\n"
			+ "			'0' as total_auth, held.total_held,held.description as rank,\r\n"
			+ "			(select username from  logininformation U where U.userid= cast(? as integer)) as user_id, ? as month, ? as year , \r\n"
			+ "			 (select version from tb_psg_iaff_3008_main_mns where  \r\n"
			+ "			 sus_no = ? and month = ? and year = ?\r\n"
			+ "			 ) as version,held.to_sus_no as sus_no\r\n"
			+ "from (select count(c.personnel_no) as total_held,c.rank as rank_id,op.to_sus_no, rm.description \r\n"
			+ " from\r\n"
			+ "tb_psg_trans_proposed_comm_letter c \r\n"
			+ "inner join cue_tb_psg_rank_app_master rm on rm.id = c.rank and upper(rm.level_in_hierarchy) = 'RANK' \r\n"
			+ "inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3','4') and op.status=1 inner join\r\n"
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
			+ "and op.to_sus_no=? and  rm.parent_code = '0' \r\n"
			+ " AND LEFT(c.personnel_no, 2)  IN ('NR', 'NS')"
			+ " group by c.rank,to_sus_no,rm.description) held  \r\n"
			+ "left join tb_psg_iaff_3008_rank_wise_data ro on upper(ro.rank::character varying) = upper(held.description::character varying)\r\n"
			+ "order by ro.id " ;
    }
	// bisag v2 290822 (split auth and held) end
	
	//26-01-1994
	public ArrayList<String> getSusNoListForIAFF3008( )
	{
			ArrayList<String> alist = new ArrayList<String>();		
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select sus_no from tb_miso_orbat_unt_dtl ms "
						+ " inner join tb_psg_trans_proposed_comm_letter com on ms.sus_no = com.unit_sus_no "
						+ " where ms.status_sus_no='Active'  and com.status not in ('0','3') and sus_no not in (\r\n"
						+ "\r\n"
						+ "select sus_no from tb_psg_iaff_3008_main_mns where month=to_char( date_trunc('month'::text, CURRENT_DATE::timestamp with time zone) ,'MM') and"
						+ " year=to_char( date_trunc('year'::text, CURRENT_DATE::timestamp with time zone) ,'YYYY')) ";
				
				stmt=conn.prepareStatement(q);	
//				stmt.setString(1, month);
//				stmt.setString(2, year);
			
				ResultSet rs = stmt.executeQuery();   	      
				while (rs.next()) {
					alist.add(rs.getString("sus_no"));//0
				}
				rs.close();
				stmt.close();
				conn.close();
			}catch (SQLException e) {
				//throw new RuntimeException(e);
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
