package com.dao.psg.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.persistance.util.HibernateUtil;

public class Report_3008DAOImpl implements Report_3008DAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
//public ArrayList<ArrayList<String>> Search_Report_3008_Serving(String roleSusNo,String month,String year,Object[] ah)
public ArrayList<ArrayList<String>> Search_Report_3008_Serving(String roleSusNo,String FDate)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

		/*		StringBuilder personal_list = new StringBuilder();
				for (int i = 0; i < ah.length; i++) {						
					if(i == ah.length-1)
					{
						personal_list.append(ah[i]).append("");
					}
					else
					{
						personal_list.append(ah[i]).append(",");
					}
				}	
				
				String[] arrOfStr = String.valueOf(personal_list).split(","); 			
				StringBuilder question_personal_list1 = new StringBuilder();

				for (int i = 0; i < ah.length; i++) {							
					if(i == ah.length-1)
					{
						question_personal_list1.append("?");
					}
					else
					{
						question_personal_list1.append("?").append(",");
					}				
				}
								
				 if(personal_list.length() > 0){
					qry += " and personnel_no NOT IN ( "+ question_personal_list1 +" ) ";
				}*/
				
			/*	if(!month.equals("0")) {
					qry += " and EXTRACT(MONTH FROM c.date_of_commission) = cast ( ? as double precision) ";
				}
				if(!year.equals("")) {
					qry += " and EXTRACT(YEAR FROM c.date_of_commission) = cast ( ? as double precision) ";
				}
				*/
				q="select c.personnel_no,ltrim(TO_CHAR(op.dt_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
						"ltrim(TO_CHAR(op.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,op.comm_id,op.to_sus_no,OP.id,\r\n" + 
						"ltrim(TO_CHAR(rk.dt_of_rk ,'DD-MON-YYYY'),'0') as dt_of_rk,ra.description as rank,ra1.description as appt,\r\n" + 
						"cda_main.cda_acc_no,c.name\r\n" + 
						",arm_p.arm_desc as parent_arm,arm_r.arm_desc as regiment,\r\n" + 
						"ltrim(TO_CHAR(c.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
						"ltrim(TO_CHAR(c.date_of_commission ,'DD-MON-YYYY'),'0') as date_of_commission ,\r\n" + 
						"ltrim(TO_CHAR(sen.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority,\r\n" + 
						"ltrim(TO_CHAR(app_main.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment\r\n" + 
						"from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c \r\n" + 
						"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3') and op.status=1\r\n" + 
						"and\r\n" + 
						"to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')\r\n" + 
						"AND (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))\r\n" + 
						"inner join\r\n" + 
						"(select MAX(date_of_rank) as dt_of_rk,comm_id from tb_psg_change_of_rank\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_rank,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) rk\r\n" + 
						"on rk.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_change_of_rank rk_main on rk.dt_of_rk = rk_main.date_of_rank and rk.comm_id=rk_main.comm_id\r\n" + 
						"inner join cue_tb_psg_rank_app_master ra on  ra.id = rk_main.rank and upper(ra.level_in_hierarchy) = 'RANK'\r\n" + 
						"inner join\r\n" + 
						"(select MAX(date_of_seniority) as date_of_seniority,comm_id from tb_psg_change_of_seniority \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_seniority,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) sen\r\n" + 
						"on sen.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"(select MAX(date_of_appointment) as dt_of_app,comm_id from tb_psg_change_of_appointment \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(date_of_appointment,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) app\r\n" + 
						"on app.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_change_of_appointment app_main on app.dt_of_app = app_main.date_of_appointment and app.comm_id=app_main.comm_id\r\n" + 
						"left join cue_tb_psg_rank_app_master ra1 on  ra1.id = app_main.appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
						"left join\r\n" + 
						"(select MAX(cd.created_date) as dt_of_cda,comm_id from tb_psg_cda_acc_num cd\r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(cd.created_date,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) cda\r\n" + 
						"on cda.comm_id=c.id\r\n" + 
						"left join\r\n" + 
						"tb_psg_cda_acc_num cda_main on cda.dt_of_cda = cda_main.created_date and cda.comm_id=cda_main.comm_id\r\n" + 
						"inner join\r\n" + 
						"(select MAX(with_effect_from) as dt_of_arm,comm_id from tb_psg_inter_arm_transfer \r\n" + 
						"where status in ('1','2') and\r\n" + 
						"to_date(to_char(with_effect_from,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(?,'YYYY/MM/DD') group by 2) arm\r\n" + 
						"on arm.comm_id=c.id\r\n" + 
						"inner join\r\n" + 
						"tb_psg_inter_arm_transfer arm_main on arm.dt_of_arm = arm_main.with_effect_from and arm.comm_id=arm_main.comm_id\r\n" + 
						"inner join tb_miso_orbat_arm_code arm_p on arm_p.arm_code = arm_main.parent_arm_service \r\n" + 
						"left join tb_miso_orbat_arm_code arm_r on arm_r.arm_code = arm_main.regt \r\n" + 
						"where\r\n" + 
						"op.to_sus_no=?\r\n" + 
						"and \r\n" + 
						"(ra1.description is null or ra1.description not in (select appointment from tb_psg_link_appoinment_with_super_supernumerary))\r\n" + 
						"and c.id not in (select comm_id from tb_psg_re_employment where status=1)\r\n" + 
						"and c.id not in (select comm_id from tb_psg_deserter where status=1) ";
				
								
			         stmt=conn.prepareStatement(q);	
					  stmt.setString(1,FDate);
					  stmt.setString(2,FDate);
					  stmt.setString(3,FDate);
					  stmt.setString(4,FDate);
					  stmt.setString(5,FDate);
					  stmt.setString(6,FDate);
					  stmt.setString(7,FDate);
					  stmt.setString(8, roleSusNo);
					  
			  
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
					list.add(rs.getString("regiment"));//5
					list.add(rs.getString("dt_of_tos"));//6
					list.add(rs.getString("date_of_birth"));//7
					list.add(rs.getString("date_of_commission"));//8
					list.add(rs.getString("date_of_seniority"));//9
					list.add(rs.getString("date_of_appointment"));//10	
		    	  
					/*String update_ofr_status = "";
					update_ofr_status = "<input type='hidden' id = 'update_ofr_status" + i + "' name='update_ofr_status" + i + "' value = '"
								+ rs.getString("update_ofr_status") + "' >";
		    	  
					String f = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
						+ rs.getString("id") + ",'"+rs.getString("personnel_no")+"')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					list.add(f);
					list.add(update_ofr_status);*/
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

//public ArrayList<ArrayList<String>> Search_Report_3008_Auth_Held(String roleSusNo,String month,String year,Object[] cp)


public ArrayList<ArrayList<String>> getEstablishNo(String roleSusNo) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";

		qry = "select distinct COALESCE(s.we_pe_no,'') as we_pe_no \r\n" + 
				"from sus_pers_stdauth s\r\n" + 
				"left join logininformation l on s.sus_no = l.user_sus_no\r\n" + 
				"where s.sus_no = ? ";
		stmt = conn.prepareStatement(qry);
		stmt.setString(1,roleSusNo);
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("we_pe_no"));							
			alist.add(list);
					
		}
		rs.close();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return alist;
}


public ArrayList<ArrayList<String>> getcommandpost_inout(String roleSusNo) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";
		
	/*	qry = "select distinct c.id,\r\n" + 
				"fv.sus_no as command,\r\n" + 
				"fvm.sus_no as corps,\r\n" + 
				"div.sus_no as division,\r\n" + 
				"bde.sus_no as brigade, " +
				"fv.unit_name as cmd_unit,\r\n" + 
				"fvm.unit_name as corp_unit,\r\n" + 
				"div.unit_name as div_unit,\r\n" + 
				"bde.unit_name as bde_unit \r\n" + 
				"from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"left join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" + 
				"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no "
				+ " and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
				"where c.unit_sus_no = ?";*/
		/*testing start: swati_s 21 dec 23 */
		qry = "select distinct c.id,\r\n" + 
				"o.comd_sus as command,\r\n" + 
				"o.corps_sus as corps,\r\n" + 
				"o.div_sus as division,\r\n" + 
				"o.bde_sus as brigade, " +
				"o.comd_name as cmd_unit,\r\n" + 
				"o.corps_name as corp_unit,\r\n" + 
				"o.div_name as div_unit,\r\n" + 
				"o.bde_name as bde_unit \r\n" + 
				"from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"right join orbat_all_units_view o on o.unit_sus=c.unit_sus_no\r\n" + 
				"left join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and ra.level_in_hierarchy = 'RANK' \r\n"+
				"left join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" + 
				"where o.unit_sus = ?";
		/*testing end: swati_s 21 dec 23 */
		stmt = conn.prepareStatement(qry);
		stmt.setString(1,roleSusNo);
		
		ResultSet rs = stmt.executeQuery();
	
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));	
			list.add(rs.getString("command"));	
			list.add(rs.getString("corps"));	
			list.add(rs.getString("division"));	
			list.add(rs.getString("brigade"));	
			list.add(rs.getString("cmd_unit"));	
			list.add(rs.getString("corp_unit"));	
			list.add(rs.getString("div_unit"));	
			list.add(rs.getString("bde_unit"));	
			alist.add(list);
			
	
		}
		rs.close();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return alist;
}
////////////////////////////////////////////17/01/2021////////////////
public ArrayList<ArrayList<String>> getcommand(String roleSusNo) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";

		qry = "select distinct c.id,\r\n" + 
				"fv.sus_no as command,\r\n" + 
				"fvm.sus_no as corps,\r\n" + 
				"div.sus_no as division,\r\n" + 
				"bde.sus_no as brigade, " +
				"fv.unit_name as cmd_unit,\r\n" + 
				"fvm.unit_name as corp_unit,\r\n" + 
				"div.unit_name as div_unit,\r\n" + 
				"bde.unit_name as bde_unit \r\n" + 
				"from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"left join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" + 
				"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no "
				+ " and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
				"where c.unit_sus_no = ?";
		/*testing start: swati_s 21 dec 23 */
		/*qry = "select distinct c.id,\r\n" + 
				"o.comd_sus as command,\r\n" + 
				"o.corps_sus as corps,\r\n" + 
				"o.div_sus as division,\r\n" + 
				"o.bde_sus as brigade, " +
				"o.comd_name as cmd_unit,\r\n" + 
				"o.corps_name as corp_unit,\r\n" + 
				"o.div_name as div_unit,\r\n" + 
				"o.bde_name as bde_unit \r\n" + 
				"from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"right join orbat_all_units_view o on o.unit_sus=c.unit_sus_no\r\n" + 
				"left join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and ra.level_in_hierarchy = 'RANK' \r\n"+
				"left join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" + 
				"where o.unit_sus = ?";*/
		/*testing end: swati_s 21 dec 23 */
		stmt = conn.prepareStatement(qry);
		stmt.setString(1,roleSusNo);
		
		ResultSet rs = stmt.executeQuery();
	
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));	
			list.add(rs.getString("command"));	
			list.add(rs.getString("corps"));	
			list.add(rs.getString("division"));	
			list.add(rs.getString("brigade"));	
			list.add(rs.getString("cmd_unit"));	
			list.add(rs.getString("corp_unit"));	
			list.add(rs.getString("div_unit"));	
			list.add(rs.getString("bde_unit"));	
			alist.add(list);
			
	
		}
		rs.close();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return alist;
}



/*******************************************PDF FOR PRINT*******************************************/

public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Serving(String roleSusNo,String month,String year,Object[] pdf_ah)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";		
	 try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;		

			StringBuilder personal_list = new StringBuilder();
			for (int i = 0; i < pdf_ah.length; i++) {						
				if(i == pdf_ah.length-1)
				{
					personal_list.append(pdf_ah[i]).append("");
				}
				else
				{
					personal_list.append(pdf_ah[i]).append(",");
				}
			}	
			
			String[] arrOfStr = String.valueOf(personal_list).split(","); 			
			StringBuilder question_personal_list1 = new StringBuilder();

			for (int i = 0; i < pdf_ah.length; i++) {							
				if(i == pdf_ah.length-1)
				{
					question_personal_list1.append("?");
				}
				else
				{
					question_personal_list1.append("?").append(",");
				}				
			}						
			 if(personal_list.length() > 0){
				qry += " and personnel_no NOT IN ( "+ question_personal_list1 +" ) ";
			}
			
			if(!month.equals("0")) {
				qry += " and EXTRACT(MONTH FROM c.date_of_commission) = cast ( ? as double precision) ";
			}
			if(!year.equals("")) {
				qry += " and EXTRACT(YEAR FROM c.date_of_commission) = cast ( ? as double precision) ";
			}
			
			q="select distinct\r\n" + 
					"c.id,\r\n" + 
					"r.description as appt,\r\n" + 
					"ra.description as rank,\r\n" + 
					"c.name,\r\n" + 
					"c.personnel_no,\r\n" + 
					"ac.cda_acc_no,\r\n" + 
					"m.arm_desc as regiment, \r\n" + 
					"ltrim(TO_CHAR(post.dt_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos, \r\n" + 
					"ltrim(TO_CHAR(c.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_commission ,'DD-MON-YYYY'),'0') as date_of_commission,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority,\r\n" + 
					"ltrim(TO_CHAR(a.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment \r\n" + 
					"from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"inner join tb_psg_census_detail_p cd on c.id = cd.comm_id \r\n" + 
					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" + 
					"inner join logininformation l on c.unit_sus_no = l.user_sus_no \r\n" + 
					"left join tb_psg_change_of_appointment a on c.id = a.comm_id \r\n" + 
					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT' and a.status = '1' \r\n" + 
					"left join tb_miso_orbat_arm_code m on m.arm_code = c.regiment \r\n" + 
					"left join tb_psg_cda_acc_num ac on c.id = ac.comm_id \r\n" + 
					"left join tb_psg_posting_in_out post on cd.id = post.census_id and post.to_sus_no = ? and post.status=1 \r\n" + 
					"where c.status='1' and cd.status = '1' \r\n" + 
					"and c.unit_sus_no = ? " + qry;	
				
		  stmt=conn.prepareStatement(q);

			if(!qry.equals("")) {  
				int j =1;
				
			  if (roleSusNo != "") {
				 stmt.setString(j, roleSusNo);
				 j += 1;
			  }	
			  if (roleSusNo != "") {
				 stmt.setString(j, roleSusNo);
				 j += 1;
			  }
			  if(personal_list.length() > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {																	
				  	stmt.setString(j, arrOfStr[i] );					
					j += 1;
				  }														 
			  }	
			  if(!month.equals("0")) {
				stmt.setString(j, month);
				j += 1;	
			  } 
			  if(!year.equals("")) {
				stmt.setString(j, year);
				j += 1;	
			  } 
		  
		}
		  
	      ResultSet rs = stmt.executeQuery();
	      int i = 1;
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    		list.add(String.valueOf(i++));
	    	  	list.add(rs.getString("appt"));
				list.add(rs.getString("rank"));
				list.add(rs.getString("name"));					
				list.add(rs.getString("personnel_no"));	
				list.add(rs.getString("cda_acc_no"));
				list.add(rs.getString("regiment"));
				list.add(rs.getString("dt_of_tos"));
				list.add(rs.getString("date_of_birth"));
				list.add(rs.getString("date_of_commission"));
				list.add(rs.getString("date_of_seniority"));
				list.add(rs.getString("date_of_appointment"));								
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

public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Super(String roleSusNo,String month,String year)
{
	
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
Connection conn = null;
String q="";
String qry="";	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		if(!month.equals("0")) {
			qry += " and EXTRACT(MONTH FROM c.date_of_commission) = cast ( ? as double precision) ";
		}
		if(!year.equals("")) {
			qry += " and EXTRACT(YEAR FROM c.date_of_commission) = cast ( ? as double precision) ";
		}
		
		q="select distinct \r\n" + 
				"r.description as appt,\r\n" + 
				"ra.description as rank,\r\n" + 
				"c.name,\r\n" + 
				"c.personnel_no," + 
				"ac.cda_acc_no, \r\n" + 
				"m.arm_desc as regiment, \r\n" + 
				"ltrim(TO_CHAR(post.dt_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos, \r\n" + 
				"ltrim(TO_CHAR(c.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
				"ltrim(TO_CHAR(c.date_of_commission ,'DD-MON-YYYY'),'0') as date_of_commission,\r\n" + 
				"ltrim(TO_CHAR(c.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(a.date_of_appointment ,'DD-MON-YYYY'),'0') as date_of_appointment \r\n" + 
				"from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"inner join tb_psg_census_detail_p cd on c.id = cd.comm_id  \r\n" + 
				"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" + 
				"inner join logininformation l on c.unit_sus_no = l.user_sus_no \r\n" + 
				"left join tb_psg_change_of_appointment a on c.id = a.comm_id \r\n" + 
				"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT' and a.status = '1' \r\n" + 
				"and r.description IN ('ON ATTACHMENT','ON COURSE ABROAD','ON COURSE EXCEEDING 10 WEEKS',\r\n" + 
				"					  'ON DEPUTATION','ON FURLOUGH LEAVE','ON LEAVE PENDING RETIREMENT','ON STUDY LEAVE') \r\n" + 
				"left join tb_miso_orbat_arm_code m on m.arm_code = c.regiment \r\n" + 
				"left join tb_psg_cda_acc_num ac on c.id = ac.comm_id \r\n" + 
				"left join tb_psg_posting_in_out post on cd.id = post.census_id and post.to_sus_no = ? and post.status=1 \r\n" + 
				"where c.status='1' and cd.status = '1' and c.unit_sus_no = ? " + qry;
						
	  stmt=conn.prepareStatement(q);	
	  if(!qry.equals("")) {  
		  int j =1;
			
		  if (roleSusNo != "") {
			  stmt.setString(j, roleSusNo);
			  j += 1;
		  }	
		  if (roleSusNo != "") {
			  stmt.setString(j, roleSusNo);
			  j += 1;
			}
		  if(!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;	
		  } 
		  if(!year.equals("")) {
			stmt.setString(j, year);
			j += 1;	
		  }
	  }
	  
      ResultSet rs = stmt.executeQuery(); 
      int i = 1;
      while (rs.next()) {
    	  ArrayList<String> list = new ArrayList<String>();
    	  	list.add(String.valueOf(i++));
    	  	list.add(rs.getString("appt"));
    	  	list.add(rs.getString("rank"));
			list.add(rs.getString("name"));					
			list.add(rs.getString("personnel_no"));	
			list.add(rs.getString("cda_acc_no"));
			list.add(rs.getString("regiment"));
			list.add(rs.getString("dt_of_tos"));
			list.add(rs.getString("date_of_birth"));
			list.add(rs.getString("date_of_commission"));
			list.add(rs.getString("date_of_seniority"));
			list.add(rs.getString("date_of_appointment"));				
			
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


public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_ReEmployed(String roleSusNo,String month,String year,Object[] pdf_sp)
{
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
Connection conn = null;
String q="";
String qry="";	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		StringBuilder personal_list = new StringBuilder();
		for (int i = 0; i < pdf_sp.length; i++) {		
			
			if(i == pdf_sp.length-1)
			{
				personal_list.append(pdf_sp[i]).append("");
			}
			else
			{
				personal_list.append(pdf_sp[i]).append(",");
			}
		}	
		
		String[] arrOfStr = String.valueOf(personal_list).split(","); 			
		StringBuilder question_personal_list2 = new StringBuilder();

		for (int i = 0; i < pdf_sp.length; i++) {							
			if(i == pdf_sp.length-1)
			{
				question_personal_list2.append("?");
			}
			else
			{
				question_personal_list2.append("?").append(",");
			}				
		}
		
		if(personal_list.length() > 0){
			qry += " and personnel_no NOT IN ( "+ question_personal_list2 +" ) ";
		}
		
		if(!month.equals("0")) {
			qry += "   and EXTRACT(MONTH FROM c.date_of_commission) = cast ( ? as double precision) ";
		}
		if(!year.equals("")) {
			qry += "   and EXTRACT(YEAR FROM c.date_of_commission) = cast ( ? as double precision) ";
		}
													
		q="select distinct \r\n" + 
				"ra.description as app_name, \r\n" + 
				"r.description as rank, \r\n" + 
				"c.name, \r\n" + 
				"c.personnel_no, \r\n" + 
				"ac.cda_acc_no, \r\n" + 
				"m.arm_desc as regiment, \r\n" + 
				"ltrim(TO_CHAR(e.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos, \r\n" + 
				"ltrim(TO_CHAR(c.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth, \r\n" + 
				"ltrim(TO_CHAR(c.date_of_commission ,'DD-MON-YYYY'),'0') as date_of_commission, \r\n" + 
				"ltrim(TO_CHAR(c.date_of_seniority ,'DD-MON-YYYY'),'0') as date_of_seniority, \r\n" + 
				"ltrim(TO_CHAR(a.date_of_appointment ,'DD-MON-YYYY'),'0') as app_dt \r\n" + 
				"from tb_psg_re_employment e  \r\n" + 
				"inner join tb_psg_trans_proposed_comm_letter c on c.id = e.comm_id   \r\n" + 
				"inner join cue_tb_psg_rank_app_master r on  r.id = c.rank and upper(r.level_in_hierarchy) = 'RANK'  \r\n" + 
				"inner join logininformation l on c.unit_sus_no = l.user_sus_no  \r\n" + 
				"left join tb_psg_change_of_appointment a on c.id = a.comm_id  \r\n" + 
				"left join cue_tb_psg_rank_app_master ra on  ra.id = a.appointment and upper(ra.level_in_hierarchy) = 'APPOINTMENT' and a.status ='1' \r\n" + 
				"left join tb_miso_orbat_arm_code m on m.arm_code = c.regiment   \r\n" + 
				"left join tb_psg_cda_acc_num ac on c.id = ac.comm_id  \r\n" + 
				"where e.status='1' and c.status='1' and e.unit_sus_no = ?   " + qry; ;
			
	  stmt=conn.prepareStatement(q);	
	  
	  if(!qry.equals("")) {  
			int j =1;			
		  if (roleSusNo != "") {
			stmt.setString(j, roleSusNo);
			j += 1;
		  }	
		  if(personal_list.length() > 0) {
			  for (int i = 0; i < arrOfStr.length; i++) {																	
			  	stmt.setString(j, arrOfStr[i] );					
				j += 1;
			  }														 
		  }	
		  if(!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;	
		  } 
		  if(!year.equals("")) {
			stmt.setString(j, year);
			j += 1;	
		  } 
		
	}		  
      ResultSet rs = stmt.executeQuery();  
      int i = 1;
      while (rs.next()) {
    	  ArrayList<String> list = new ArrayList<String>();
    		list.add(String.valueOf(i++));
    	  	list.add(rs.getString("app_name"));
			list.add(rs.getString("rank"));
			list.add(rs.getString("name"));					
			list.add(rs.getString("personnel_no"));	
			list.add(rs.getString("cda_acc_no"));
			list.add(rs.getString("regiment"));
			list.add(rs.getString("dt_of_tos"));
			list.add(rs.getString("date_of_birth"));
			list.add(rs.getString("date_of_commission"));
			list.add(rs.getString("date_of_seniority"));
			list.add(rs.getString("app_dt"));						
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

public ArrayList<ArrayList<String>> Pdf_Search_Report_3008_Auth_Held(String roleSusNo,String month,String year,Object[] pdf_cp)
{
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
Connection conn = null;
String q="";
String qry="";	
	try{	
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		StringBuilder personal_list = new StringBuilder();
		for (int i = 0; i < pdf_cp.length; i++) {						
			if(i == pdf_cp.length-1)
			{
				personal_list.append(pdf_cp[i]).append("");
			}
			else
			{
				personal_list.append(pdf_cp[i]).append(",");
			}								
		}	
		
		String[] arrOfStr = String.valueOf(personal_list).split(","); 			
		StringBuilder question_personal_list3 = new StringBuilder();

		for (int i = 0; i < pdf_cp.length; i++) {							
			if(i == pdf_cp.length-1)
			{
				question_personal_list3.append("?");
			}
			else
			{
				question_personal_list3.append("?").append(",");
			}				
		}
		
		if(personal_list.length() > 0){
			qry += " and personnel_no NOT IN ( "+ question_personal_list3 +" ) ";
		}
		if(!month.equals("0")) {
			qry += "   and EXTRACT(MONTH FROM com.date_of_commission) = cast ( ? as double precision) ";
		}
		if(!year.equals("")) {
			qry += "   and EXTRACT(YEAR FROM com.date_of_commission) = cast ( ? as double precision) ";
		}
		
		q="select r.description as rank,sum(base_auth) as base_auth ,sum(mod_auth) as mod_auth,sum(foot_auth) as foot_auth,\r\n" + 
				"sum(base_auth + mod_auth + foot_auth) as total_auth,\r\n" + 
				"count(com.personnel_no) as total_held\r\n" + 
				"from sus_pers_stdauth n\r\n" + 
				"inner join cue_tb_psg_rank_app_master r on r.id  = cast(n.rank_code as integer)\r\n" + 
				"inner join tb_psg_trans_proposed_comm_letter com on com.rank = r.id and com.unit_sus_no = n.sus_no\r\n" + 
				"where com.status='1' and n.sus_no = ?  \r\n" + 
				"group by 1 " ;

	  stmt=conn.prepareStatement(q);
	  
	  if(!qry.equals("")) {  
			int j =1;
			
		  if (roleSusNo != "") {
			stmt.setString(j, roleSusNo);
			j += 1;
		  }				  
		  if(personal_list.length() > 0) {
			  for (int i = 0; i < arrOfStr.length; i++) {																	
			  	stmt.setString(j, arrOfStr[i] );					
				j += 1;
			  }														 
		  }		  
		  if(!month.equals("0")) {
			stmt.setString(j, month);
			j += 1;	
		  } 
		  if(!year.equals("")) {
			stmt.setString(j, year);
			j += 1;	
		  }				  
	}
	  
      ResultSet rs = stmt.executeQuery();  
      int i = 1;
      while (rs.next()) {
    	  ArrayList<String> list = new ArrayList<String>();
    	  	list.add(String.valueOf(i++));
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


public ArrayList<ArrayList<String>> getReport_Data(String roleSusNo) {	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";

		qry = "select distinct \r\n" + 
				"s.id,\r\n" + 
				"s.present_return_no,\r\n" + 
				"ltrim(TO_CHAR(s.present_return_dt,'dd/MM/yyyy'),'0') as present_return_dt,\r\n" + 
				"s.last_return_no,\r\n" + 
				"ltrim(TO_CHAR(s.last_return_dt ,'dd/MM/yyyy'),'0') as last_return_dt,\r\n" + 
				"s.month,\r\n" + 
				"s.year\r\n" + 
				"from tb_psg_iaff_3008_main s\r\n" + 
				"inner join logininformation l on s.sus_no = l.user_sus_no\r\n" + 
				"where s.sus_no = ? order by id desc ";
		
		stmt = conn.prepareStatement(qry);
		stmt.setString(1,roleSusNo);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("present_return_no"));	
			list.add(rs.getString("present_return_dt"));	
			list.add(rs.getString("last_return_no"));	
			list.add(rs.getString("last_return_dt"));	
			list.add(rs.getString("month"));
			list.add(rs.getString("year"));
			alist.add(list);					
		}
		rs.close();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return alist;
}

public boolean refreshIAF_3008MaterializedViews() {	
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String qry = "";

		qry = "REFRESH MATERIALIZED VIEW \"iaff_3008ServingView\"";
		
		stmt = conn.prepareStatement(qry);
		int rs = stmt.executeUpdate();
		stmt.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return true;
}



}
