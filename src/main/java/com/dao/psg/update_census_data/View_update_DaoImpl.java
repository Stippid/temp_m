package com.dao.psg.update_census_data;

import java.math.BigInteger;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_FOREIGN_COUNTRY;
import com.models.psg.Transaction.TB_CENSUS_IDENTITY_CARD;
import com.models.psg.Transaction.TB_CENSUS_LANGUAGE;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_CENSUS_QUALIFICATION;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_PSG_CENSUS_ALLERGICTOMEDICINE;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.models.psg.update_census_data.TB_CENSUS_BPET;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CENSUS_DISCIPLINE;
import com.models.psg.update_census_data.TB_CENSUS_FIRING_STANDARD;
import com.models.psg.update_census_data.TB_CENSUS_PROMOTIONAL_EXAM;
import com.models.psg.update_census_data.TB_CENSUS_SECONDMENT;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_CHANGE_RELIGION;
import com.models.psg.update_census_data.TB_CHANGE_TA_STATUS;
import com.models.psg.update_census_data.TB_CHANGE_TNAI_NO;
import com.models.psg.update_census_data.TB_DESERTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_EXTENSION_OF_COMISSION;
import com.persistance.util.HibernateUtil;

public class View_update_DaoImpl implements View_update_Dao {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	//	bisag 301222 v2 (change qries removing active or inactive status only master table and non effetive time )
	
	public ArrayList<ArrayList<String>> Search_UpdateOffData(String unit_name,String unit_sus_no,String personnel_no,String status,String rank,String roleSusNo,String roleType
			,String name,String y_comm,String y_dob,String p_arm,String comm_status,String roleAccess,Boolean IsMns)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_status.equals("1") || comm_status.equals("5")) {
				qry+=" and  tp.status in ('1','5')  and UPPER(u.status_sus_no) IN ('INACTIVE','ACTIVE') " ;
			}
			if(comm_status.equals("4")) {
				qry+=" and  tp.status ='4' and UPPER(u.status_sus_no) IN ('INACTIVE','ACTIVE') " ;
			}
			if(!roleSusNo.equals("")) {
				qry+=" and tp.unit_sus_no = ?";
			}
			else if(!unit_sus_no.equals("")) {
				qry+=" and tp.unit_sus_no = ?";
			}		
			if(!personnel_no.equals("")) {
				qry += "  and tp.personnel_no = ? ";
			}
			if(!name.equals("")) {
				qry += "  and upper(tp.name) like ? ";
			}
			if(!rank.equals("") && !rank.equals("0")) {
				qry += "  and tp.rank= ? ";
			}
			if(!y_comm.equals("") && !y_comm.equals("0")) {
				qry += "and  date_part('year',date_of_commission)::text= ? ";
			}
			if(!y_dob.equals("") && !y_dob.equals("0")) {
				qry += "and  date_part('year',date_of_birth)::text= ? ";
			}
			if(!p_arm.equals("") && !p_arm.equals("0") ) {
				qry += "  and tp.parent_arm= ? ";
			}
			
			if (IsMns == true) {
				qry += "and substr(tp.personnel_no,1,2) in ('NR','NS') ";
			} else if (IsMns == false) {
				qry += "and substr(tp.personnel_no,1,2) Not in ('NR','NS') ";
			}
			
			if (roleAccess.equals("Unit")) {

				q="select distinct \r\n" + 
						"cl.id ,\r\n" + 
						"tp.cadet_no,\r\n" + 
						"tp.personnel_no,\r\n" + 
						"r.description as rank,\r\n" + 
						"tp.name,tp.unit_sus_no,\r\n" + 
						"(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY')) as date_of_birth,\r\n" + 
						"arm.arm_desc as parent_arm,\r\n" + 
						"cl.status,\r\n" + 
						"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date ,u.unit_name,tp.status as comm_status\r\n" + 
						"FROM tb_psg_census_detail_p cl \r\n" + 
						"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id  \r\n" + 
						"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank  \r\n" + 
						"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" + 
						"inner join tb_miso_orbat_unt_dtl  u on u.sus_no =tp.unit_sus_no  \r\n" + 
						"inner join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" + 
						"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" + 
						"where cl.id !=  0    and cl.status = '1' " + qry+ " order by cl.modified_date desc" ;

				

			}
			
			else {
			
			q="select distinct \r\n" + 
					"cl.id ,\r\n" + 
					"tp.cadet_no,\r\n" + 
					"tp.personnel_no,\r\n" + 
					"r.description as rank,\r\n" + 
					"tp.name,tp.unit_sus_no,\r\n" + 
					"(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY')) as date_of_birth,\r\n" + 
					"arm.arm_desc as parent_arm,\r\n" + 
					"cl.status,\r\n" + 
					"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date ,u.unit_name,tp.status as comm_status\r\n" + 
					"FROM tb_psg_census_detail_p cl \r\n" + 
					"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id  \r\n" + 
					"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank  \r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.sus_no =tp.unit_sus_no  \r\n" + 
					"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" + 
					"left join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" + 
					"where cl.id !=  0    and cl.status = '1' " + qry+ " order by cl.modified_date desc" ;

			}
			stmt=conn.prepareStatement(q);
			
			
		    
			
				int j =1;
				if(!qry.equals(""))     
				{
			
					if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
					else if(!unit_sus_no.equals("")) {
						stmt.setString(j, unit_sus_no);
						j += 1;
					}		
					if(!personnel_no.equals("")) {
						stmt.setString(j, personnel_no);
						j += 1;
					}
					if(!name.equals("")) {
						stmt.setString(j, name.toUpperCase()+"%");
						j += 1;
					}
					if(!rank.equals("") && !rank.equals("0")) {
						stmt.setInt(j, Integer.parseInt(rank));
						j += 1;
					}
					if(!y_comm.equals("") && !y_comm.equals("0")) {
						stmt.setString(j, y_comm);
						j += 1;
					}
					if(!y_dob.equals("") && !y_dob.equals("0")) {
						stmt.setString(j, y_dob);
						j += 1;
					}
					if(!p_arm.equals("") && !p_arm.equals("0") ) {
						stmt.setString(j, p_arm);
						j += 1;
					}
				 
				
			}
				 
		 System.err.println("search service mns offr\n"+stmt);	 
	      ResultSet rs = stmt.executeQuery();   
	      
	 
	      
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("cadet_no")); //0
				list.add(rs.getString("personnel_no")); //1
				list.add(rs.getString("rank"));				//2
				list.add(rs.getString("name")); //3
				list.add(rs.getString("unit_name")); //4
				//list.add(rs.getString("status")); //5
				String f1 = "";
				/*
				 * f1 = "onclick=\"  {View1Data("+ rs.getString("id") +
				 * ",'"+rs.getString("comm_id")+"','"+rs.getString("unit_sus_no")+"','"+rs.
				 * getInt("comm_status")+"')}\"";
				 */
                
				 String View = "onclick=\"  {View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"','"+rs.getString("unit_sus_no")+"','"+rs.getInt("comm_status")+"')}\"";
	               f1 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='View Data'></i>";
			     list.add(f1); //5
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
	public List<Map<String, Object>> View_UpadteOfficerDataByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		
	
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1 || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("") ) {
				qry += " and cast(cdp.comm_id  as character varying)=? ";
			}
			
		//	bisag 301222 v2 (change qry removing active or inactive status )
			
			
			
		if(comm_status != 4)
		{
	        q = "select distinct pcl.id,\r\n" + 
	        		"COALESCE(comm.old_personal_no,'') as old_personal_no,\r\n" + 
	        		"COALESCE(TO_CHAR(cdp.modified_date ,'DD-MON-YYYY'),'') as modified_date,\r\n" + 
	        		"COALESCE(pcl.personnel_no,'') as personnel_no,\r\n" + 
	        		"COALESCE(pcl.name,'') as name,\r\n" + 
	        		"COALESCE(r.description,'') as rank,\r\n" + 
	        		"COALESCE(TO_CHAR(pcl.date_of_rank ,'DD-MON-YYYY'),'') as date_of_rank,\r\n" + 
	        		"COALESCE(arm.arm_desc,'') as regiment,\r\n" + 
	        		"COALESCE(tc.comn_name,'') as comn_name,\r\n" + 
	        		"COALESCE(TO_CHAR(pcl.date_of_commission ,'DD-MON-YYYY'),'') as  date_of_commission,\r\n" + 
	        		"COALESCE(TO_CHAR(comm.date_of_permanent_commission ,'DD-MON-YYYY'),'') as  date_of_permanent_commission,\r\n" + 
	        		"COALESCE(TO_CHAR(pcl.date_of_seniority ,'DD-MON-YYYY'),'') as date_of_seniority,\r\n" + 
	        		"COALESCE(rm.description,'')as appointment,\r\n" + 
	        		"COALESCE(TO_CHAR(app.date_of_appointment ,'DD-MON-YYYY'), '') as  date_of_appointment,\r\n" + 
	        		"COALESCE( m.unit_name,'') as unit_name,\r\n" + 
	        		"COALESCE((TO_CHAR(po.dt_of_tos ,'DD-MON-YYYY')),'') as dt_of_tos,\r\n" + 
	        		"COALESCE((TO_CHAR(pcl.date_of_birth ,'DD-MON-YYYY')),'') as date_of_birth,\r\n" + 
	        		"COALESCE(an.cda_acc_no,'') as cda_acc_no,\r\n" + 
	        		"COALESCE((TO_CHAR(po.dt_of_tos ,'DD-MON-YYYY')),'') as dt_of_tos,\r\n" + 
	        		"COALESCE( gen.gender_name,'') as gender_name,\r\n" + 
	        		"case when COALESCE( rel.religion_name,'') = 'OTHERS' THEN cdp.religion_other else rel.religion_name end as religion_name,\r\n"
	        		+ "case when COALESCE( nati.nationality_name,'') = 'OTHERS' then cdp.nationality_other else nati.nationality_name end as nationality_name,\r\n"
	        		+ "case when COALESCE( st.state_name,'')='OTHERS' THEN cdp.org_domicile_state_other else st.state_name end as state_name,\r\n"
	        		+ "case when COALESCE( mol.mothertounge,'') = 'OTHERS' THEN cdp.mother_tongue_other else mol.mothertounge end  as mothertounge, " + 
	        		"COALESCE(PGP_SYM_DECRYPT(cdp.adhar_number ::bytea,current_setting('miso.version')),'0') as adhar_number,\r\n" + 
	        		"COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
	        		"COALESCE(hgt.centimeter,'') as height,\r\n" + 
	        		"COALESCE(idc.id_card_no,'') as id_card_no,\r\n" + 
	        		"COALESCE(TO_CHAR(idc.issue_dt ,'DD-MON-YYYY'),'') as issue_dt,\r\n" + 
	        		"COALESCE(mari.marital_name,'') as marital_name,        		\r\n" + 
	        		" case when med.date_of_authority is not null  then COALESCE(TO_CHAR(med.date_of_authority ,'DD-MON-YYYY'),'')\r\n" + 
	        		"else COALESCE(TO_CHAR(pcl.date_of_commission ,'DD-MON-YYYY'),'') end as  medical,COALESCE(PGP_SYM_DECRYPT(cac.gmail ::bytea,current_setting('miso.version')),'') as gmail,\r\n" + 
	        		"case when cac.mobile_no is null   OR PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version')) = '0' THEN ''  else COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'0') end as mobile_no, \r\n" + 
	        		"\r\n" + 
	        		"COALESCE(PGP_SYM_DECRYPT(cac.nic_mail ::bytea,current_setting('miso.version')),'') as nic_mail,\r\n" + 
	        		"cdp.id as census_id,idc.id as imgid,idc.identity_image    \r\n" + 
	        		"	from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"	left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status='1' and pcl.status in  \r\n" + comm_stats +
	        		"	inner join cue_tb_psg_rank_app_master r on r.id = pcl.rank \r\n" + 
	        		"	inner join tb_psg_change_of_rank cr on cr.comm_id= pcl.id\r\n" + 
	        		"	inner join tb_psg_mstr_type_of_commission tc on tc.id = pcl.type_of_comm_granted \r\n" + 
	        		"	inner join tb_miso_orbat_arm_code  arm on (arm.arm_code =  pcl.parent_arm or arm.arm_code = pcl.regiment)\r\n" + 
	        		"	inner join tb_miso_orbat_unt_dtl m on m.sus_no = pcl.unit_sus_no  and m.status_sus_no='Active' \r\n" + 
	        		"	inner join tb_psg_posting_in_out po on po.comm_id=pcl.id  \r\n" + 
	        		"	inner join tb_psg_mstr_religion rel on rel.religion_id=cdp.religion 				\r\n" + 
	        		"	inner join tb_psg_mstr_gender gen on gen.id=pcl.gender \r\n" + 
	        		"	inner join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality  \r\n" + 
	        		"	inner join tb_psg_mstr_state st on st.state_id = cdp.org_state					\r\n" + 
	        		"	inner join tb_psg_mothertounge mol on mol.id=cdp.mother_tongue \r\n" + 
	        		"	inner join tb_psg_mstr_height hgt on hgt.height_id = cdp.height \r\n" + 
	        		"	inner join tb_psg_mstr_blood bd on bd.id=cdp.blood_group \r\n" + 
	        		"	left join tb_psg_identity_card idc on idc.comm_id = pcl.id and idc.status=1 \r\n" + 
	        		"	left join tb_psg_change_of_comission comm on comm.comm_id = pcl.id and comm.status='1'\r\n" + 
	        		"	left join tb_psg_change_of_appointment app on app.comm_id = pcl.id and app.status='1' \r\n" + 
	        		"	left join cue_tb_psg_rank_app_master rm on rm.id =app.appointment \r\n" + 
	        		"	left join tb_psg_cda_acc_num an on an.comm_id=pcl.id\r\n" + 
	        		"	left join tb_psg_mstr_marital_status mari on mari.marital_id = cdp.marital_status \r\n" + 
//	        		"	left join tb_psg_census_contact_cda_account_details cdaco on cdaco.comm_id=pcl.id\r\n" + 
	        		"	\r\n" + 
	        		"	left join tb_psg_census_contact_cda_account_details cac on cac.comm_id=pcl.id  and cac.status=1 \r\n" + 
	        		"	left join tb_psg_medical_category med on med.comm_id=pcl.id and med.status=1 \r\n" + 
	        		"	where  cdp.status=1 and po.tenure_date is null  "  +qry ;
				}
		
		if(comm_status == 4)
		{
	        q = "select distinct pcl.id,\r\n" + 
	        		"COALESCE(comm.old_personal_no,'') as old_personal_no,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(cdp.modified_date ,'DD-MON-YYYY'),'0'),'') as modified_date,\r\n" + 
	        		"COALESCE(pcl.personnel_no,'') as personnel_no,\r\n" + 
	        		"COALESCE(pcl.name,'') as name,\r\n" + 
	        		"COALESCE(r.description,'') as rank,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(pcl.date_of_rank ,'DD-MON-YYYY'),'0'),'') as date_of_rank,\r\n" + 
	        		"COALESCE(arm.arm_desc,'') as regiment,\r\n" + 
	        		"COALESCE(tc.comn_name,'') as comn_name,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(pcl.date_of_commission ,'DD-MON-YYYY'),'0'),'') as  date_of_commission,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(comm.date_of_permanent_commission ,'DD-MON-YYYY'),'0'),'') as  date_of_permanent_commission,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(pcl.date_of_seniority ,'DD-MON-YYYY'),'0'),'') as date_of_seniority,\r\n" + 
	        		"COALESCE(rm.description,'')as appointment,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(app.date_of_appointment ,'DD-MON-YYYY'),'0'), '') as  date_of_appointment,\r\n" + 
	        		"COALESCE( m.unit_name,'') as unit_name,\r\n" + 
	        		"COALESCE((TO_CHAR(po.dt_of_tos ,'DD-MON-YYYY')),'') as dt_of_tos,\r\n" + 
	        		"COALESCE((TO_CHAR(pcl.date_of_birth ,'DD-MON-YYYY')),'') as date_of_birth,\r\n" + 
	        		"COALESCE(an.cda_acc_no,'') as cda_acc_no,\r\n" + 
	        		"COALESCE((TO_CHAR(po.dt_of_tos ,'DD-MON-YYYY')),'') as dt_of_tos,\r\n" + 
	        		"COALESCE( gen.gender_name,'') as gender_name,\r\n" + 
	        		"case when COALESCE( rel.religion_name,'') = 'OTHERS' THEN cdp.religion_other else rel.religion_name end as religion_name,\r\n"
	        		+ "case when COALESCE( nati.nationality_name,'') = 'OTHERS' then cdp.nationality_other else nati.nationality_name end as nationality_name,\r\n"
	        		+ "case when COALESCE( st.state_name,'')='OTHERS' THEN cdp.org_domicile_state_other else st.state_name end as state_name,\r\n"
	        		+ "case when COALESCE( mol.mothertounge,'') = 'OTHERS' THEN cdp.mother_tongue_other else mol.mothertounge end  as mothertounge,\r\n" + 
	        		"COALESCE(PGP_SYM_DECRYPT(cdp.adhar_number ::bytea,current_setting('miso.version')),'0') as adhar_number,\r\n" + 
	        		"COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
	        		"COALESCE(hgt.centimeter,'') as height,\r\n" + 
	        		"COALESCE(idc.id_card_no,'') as id_card_no,\r\n" + 
	        		"COALESCE(ltrim(TO_CHAR(idc.issue_dt ,'DD-MON-YYYY'),'0'),'') as issue_dt,\r\n" + 
	        		"COALESCE(mari.marital_name,'') as marital_name,        		\r\n" + 
	        		" case when med.date_of_authority is not null  then COALESCE(TO_CHAR(med.date_of_authority ,'DD-MON-YYYY'),'')\r\n" + 
	        		"else COALESCE(TO_CHAR(pcl.date_of_commission ,'DD-MON-YYYY'),'') end as  medical,COALESCE(PGP_SYM_DECRYPT(cac.gmail ::bytea,current_setting('miso.version')),'') as gmail,\r\n" + 
	        		" case when cac.mobile_no is null   OR PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version')) = '0' THEN ''  else COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'0') end as mobile_no, \r\n" + 
	        		"\r\n" + 
	        		"COALESCE(PGP_SYM_DECRYPT(cac.nic_mail ::bytea,current_setting('miso.version')),'') as nic_mail,\r\n" + 
	        		"cdp.id as census_id,idc.id as imgid,idc.identity_image    \r\n" + 
	        		"	from tb_psg_trans_proposed_comm_letter pcl \r\n" + 	        		
	        		"	inner join cue_tb_psg_rank_app_master r on r.id = pcl.rank \r\n" + 
	        		"	inner join tb_psg_change_of_rank cr on cr.comm_id= pcl.id\r\n" + 
	        		"	inner join tb_psg_mstr_type_of_commission tc on tc.id = pcl.type_of_comm_granted \r\n" + 
	        		"	inner join tb_miso_orbat_arm_code  arm on (arm.arm_code =  pcl.parent_arm or arm.arm_code = pcl.regiment)\r\n" + 
	        		"	inner join tb_miso_orbat_unt_dtl m on m.sus_no = pcl.unit_sus_no  \r\n" + 
	        		"	inner join tb_psg_posting_in_out po on po.comm_id=pcl.id  \r\n" + 
	        		"	left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status='1' and pcl.status in  \r\n" + comm_stats +
	        		"	inner join tb_psg_mstr_religion rel on rel.religion_id=cdp.religion 					\r\n" + 
	        		"	inner join tb_psg_mstr_gender gen on gen.id=pcl.gender \r\n" + 
	        		"	inner join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality  \r\n" + 
	        		"	inner join tb_psg_mstr_state st on st.state_id = cdp.org_state					\r\n" + 
	        		"	inner join tb_psg_mothertounge mol on mol.id=cdp.mother_tongue \r\n" + 
	        		"	inner join tb_psg_mstr_height hgt on hgt.height_id = cdp.height \r\n" + 
	        		"	inner join tb_psg_mstr_blood bd on bd.id=cdp.blood_group \r\n" + 
	        		"	left join tb_psg_identity_card idc on idc.comm_id = pcl.id and idc.status=1 \r\n" + 
	        		"	left join tb_psg_change_of_comission comm on comm.comm_id = pcl.id and comm.status='1'\r\n" + 
	        		"	left join tb_psg_change_of_appointment app on app.comm_id = pcl.id and app.status='1' \r\n" + 
	        		"	left join cue_tb_psg_rank_app_master rm on rm.id =app.appointment \r\n" + 
	        		"	left join tb_psg_cda_acc_num an on an.comm_id=pcl.id\r\n" + 
	        		"	left join tb_psg_mstr_marital_status mari on mari.marital_id = cdp.marital_status \r\n" + 
//	        		"	left join tb_psg_census_contact_cda_account_details cdaco on cdaco.comm_id=pcl.id\r\n" + 
	        		"	\r\n" + 
	        		"	left join tb_psg_census_contact_cda_account_details cac on cac.comm_id=pcl.id and cac.status=1\r\n" + 
	        		"	left join tb_psg_medical_category med on med.comm_id=pcl.id and med.status=1 \r\n" + 
	        		"	where  cdp.status=1  "  +qry +" limit 1 ";
				}
		    stmt=conn.prepareStatement(q);   	
		   
		    int j =1;
		    
			if(comm_status == 1  || comm_status == 5) {
				
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}
		   
		    if( !comm_id.equals("")) {
		    	stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 	
		    
		    
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	
		 	    	if(rs.getObject(i)==null  )
		 	    	{
		 	    		columns.put(metaData.getColumnLabel(i), "");
		 	    	}
		 	    	else if(metaData.getColumnLabel(i) == "identity_image" || metaData.getColumnLabel(i).equals("identity_image"))
		 	    	{
		 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
		 	    	
		 	    	}
					else
					{
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
					}
						
		 	    	
		 	    	
		 	    }
		 	    list.add(columns);
		 	 }
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> Sh_UpadteOfficerDataByid(int id,BigInteger comm_id,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(m.comm_id  as character varying)=? ";
			} 
	        q= "select held.shape  from (select distinct CONCAT(shape, shape_value) as shape,'1' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='S'\r\n" + qry +
	        		"UNION ALL\r\n" + 
	        		"select distinct CONCAT(shape, shape_value) as shape,'2' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and  pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='H'\r\n" + qry + 
	        		"UNION ALL\r\n" + 
	        		"select distinct CONCAT(shape, shape_value) as shape,'3' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and  pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='A'\r\n" + qry + 
	        		"UNION ALL\r\n" + 
	        		"select distinct CONCAT(shape, shape_value) as shape,'4' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and  pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='P'\r\n" + qry +
	        		"UNION ALL\r\n" + 
	        		"select distinct CONCAT(shape, shape_value) as shape,'5' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and  pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='E'  "+ qry  
	        				+ ") held order by held.sq" ;
	        		
	        stmt=conn.prepareStatement(q);   	
		    int j =1;
		    
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
			
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> Cop_UpadteOfficerDataByid(int id,BigInteger comm_id,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(m.comm_id  as character varying)=? ";
			}
	        q= "select held.shape   from (select DISTINCT CONCAT('C' ,shape_value) as shape,'1' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in "+ comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and  m.shape='C_C'\r\n" + qry + 
	        		"UNION ALL\r\n" + 
	        		"select DISTINCT CONCAT( 'O' ,shape_value) as shape,'2' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and m.shape='C_O'\r\n"+ qry + 
	        		"UNION ALL\r\n" + 
	        		"select DISTINCT CONCAT('P',shape_value) as shape,'3' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and  m.shape='C_P'\r\n" + qry +
	        		"UNION ALL\r\n" + 
	        		"select DISTINCT CONCAT('E',shape_value) as shape,'4' as sq from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in " + comm_stats +
	        		"inner join tb_psg_medical_category m on pcl.id=m.comm_id and m.status=1 and  m.shape='C_E' "+ qry + ")held order by held.sq" ;
	        		
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			}
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    	
		    ResultSet rs = stmt.executeQuery();    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> TR_UpadteOfficerDataByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and p.unit_sus_no = ?";
			}
			
			if(!comm_id.equals("")) {
				qry += " and cast(p.id  as character varying)=? ";
			}
	        /*q="select distinct p.id,\r\n" + 
	        		"COALESCE(cw.course_name,'') as course_name,\r\n" + 
	        		"COALESCE(p.cadet_no,'') as cadet_no,\r\n" + 
	        		"COALESCE(ins.institute_name,'') as institute_name,\r\n" + 
	        		"COALESCE(b.battalion_name,'') as battalion_name,\r\n" + 
	        		"COALESCE(com.company_name ,'') as company_name,\r\n" + 
	        		"COALESCE(p.batch_no,'') as batch_no,\r\n" + 
	        		"COALESCE(cs.description,'') as pre_cadet_status,\r\n" + 
	        		"typ.label as type_of_entry\r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter p \r\n" + 
	        		"left join tb_psg_census_detail_p cp on cp.comm_id= p.id and cp.status=1  \r\n" + 
	        		"inner join tb_psg_mstr_course_comm cw on cw.id=p.course\r\n" + 
	        		"inner join tb_psg_mstr_institute_list ins on ins.id =cp.com_institute \r\n" + 
	        		"inner join tb_psg_mstr_institute i on i.institute_id= ins.id\r\n" + 
	        		/// bisag v1 220822 (changed left join to inner)
	        		"inner join tb_psg_mstr_battalion b on b.id=i.bn_id  and b.id=cp.com_bn_id \r\n" + 
	        		"left join tb_psg_mstr_company com on com.id =cp.com_company_id  \r\n" + 
	        		"inner join tb_psg_ncc_type_entry typ on cp.ncc_type = typ.id\r\n" + 
	        		"left join tb_psg_census_cadet cc on cc.comm_id=p.id\r\n" + 
	        		"inner join tb_psg_mstr_pre_cadet_status cs on cs.id=cc.pre_cadet_status \r\n" + 
	        		" where p.status  in " + comm_stats +       		
	        		" " +qry ;*/
			q="select distinct p.id,\r\n" + 
	        		"COALESCE(cw.course_name,'') as course_name,\r\n" + 
	        		"COALESCE(p.cadet_no,'') as cadet_no,\r\n" + 
	        		"COALESCE(ins.institute_name,'') as institute_name,\r\n" + 
	        		"COALESCE(b.battalion_name,'') as battalion_name,\r\n" + 
	        		"COALESCE(com.company_name ,'') as company_name,\r\n" + 
	        		"COALESCE(p.batch_no,'') as batch_no,\r\n" + 
	        		"COALESCE(cs.description,'') as pre_cadet_status,\r\n" + 
	        		"typ.label as type_of_entry\r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter p \r\n" + 	        		 
	        		"inner join tb_psg_mstr_course_comm cw on cw.id=p.course\r\n" + 
	        		"left join tb_psg_census_detail_p cp on cp.comm_id= p.id and cp.status=1  \r\n" +
	        		"left join tb_psg_mstr_institute_list ins on ins.id =cp.com_institute \r\n" + 
	        		"left join tb_psg_mstr_institute i on i.institute_id= ins.id\r\n" + 
	        		"left join tb_psg_mstr_battalion b on b.id=i.bn_id \r\n" + 
	        		"left join tb_psg_mstr_company com on com.id =cp.com_company_id  \r\n" + 
	        		"inner join tb_psg_ncc_type_entry typ on cp.ncc_type = typ.id\r\n" + 
	        		"left join tb_psg_census_cadet cc on cc.comm_id=p.id\r\n" + 
	        		"inner join tb_psg_mstr_pre_cadet_status cs on cs.id=cc.pre_cadet_status \r\n" + 
	        		" where p.status  in " + comm_stats +       		
	        		" " +qry ;
	       
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    
			 
		    ResultSet rs = stmt.executeQuery();    
		  
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> Spouce_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(pcl.id  as character varying)=? ";
			}
	       
			/* q="select distinct \r\n" + 
		        		"COALESCE(mrr.marital_name, '') as marital_status,\r\n" + 
		        		"COALESCE(ms.maiden_name,'') as maiden_name,\r\n" + 	        	
		        		"COALESCE(ltrim(TO_CHAR(ms.marriage_date ,'DD-MON-YYYY'),'0'),'') as marriage_date,\r\n" + 
		        		"COALESCE(ltrim(TO_CHAR(ms.date_of_birth ,'DD-MON-YYYY'),'0'),'') as date_of_birth,\r\n" + 
		        		
		        		"COALESCE(ms.place_of_birth,'') as place_of_birth,\r\n" + 
		        		"case when  upper(nati.nationality_name)='OTHERS' or  upper(nati.nationality_name)='OTHER' then ms.other_nationality\r\n" + 
		        		" else COALESCE(nati.nationality_name,'') end as nationality_name,\r\n" + 
		        		"COALESCE(PGP_SYM_DECRYPT(ms.adhar_number ::bytea,current_setting('miso.version')),'0') as adhar_number,\r\n" + 
		        		"COALESCE(PGP_SYM_DECRYPT(cdp.pancard_number ::bytea,current_setting('miso.version'))" + 
		        		",'') as pancard_number,\r\n" + 
		        		"COALESCE(ltrim(TO_CHAR(  ms.divorce_date,'DD-MON-YYYY'),'0'),'') as divorce_date,\r\n" + 
		        		" case when  upper(ev.label)='OTHERS' or  upper(ev.label)='OTHER' then ms.other_spouse_ser\r\n" + 
		        		" else COALESCE(ev.label,'') end as spouse_service ,"
		        		+ "COALESCE(ms.spouse_personal_no,'') as spouse_personal_no " + 
		        		"		from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
		        		"		left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id  and cdp.status=1 and pcl.status in " + comm_stats +
		        		"		left join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality and nati.status='active'\r\n" + 
		        		"		left join tb_psg_census_family_married ms on ms.comm_id =pcl.id "
		        		+ "	LEFT JOIN tb_psg_mstr_marital_status mrr ON ms.marital_status = mrr.marital_id\r\n" + 
		        		"	LEFT JOIN t_domain_value ev ON ms.spouse_service::text = ev.codevalue and ev.domainid='EX_SERVICEMAN'"
		        		+ "where ms.status= 1  " +qry ;*/
			
			
			 q="select distinct \r\n" + 
			 		"COALESCE(mrr.marital_name, '') as marital_status,\r\n" + 
			 		"COALESCE(ms.maiden_name,'') as maiden_name,\r\n" + 
			 		"COALESCE((TO_CHAR(ms.marriage_date ,'DD-MON-YYYY')),'') as marriage_date,\r\n" + 
			 		"COALESCE((TO_CHAR(ms.date_of_birth ,'DD-MON-YYYY')),'') as date_of_birth,\r\n" + 
			 		"COALESCE(ms.place_of_birth,'') as place_of_birth,\r\n" + 
			 		"case when  upper(nati.nationality_name)='OTHERS' or  upper(nati.nationality_name)='OTHER' then ms.other_nationality\r\n" + 
			 		" else COALESCE(nati.nationality_name,'') end as nationality_name,\r\n" + 
			 		"COALESCE(PGP_SYM_DECRYPT(ms.adhar_number ::bytea,current_setting('miso.version')),'0') as adhar_number,\r\n" + 
			 		"COALESCE(PGP_SYM_DECRYPT(cdp.pancard_number ::bytea,current_setting('miso.version')),'') as pancard_number,\r\n" + 
			 		"COALESCE((TO_CHAR(  ms.divorce_date,'DD-MON-YYYY')),'') as divorce_date,\r\n" + 
			 		"case when  upper(t1.ex_servicemen)='OTHERS' or  upper(t1.ex_servicemen)='OTHER' then ms.other_spouse_ser\r\n" + 
			 		" else\r\n" + 
			 		"COALESCE(t1.ex_servicemen::text,'') End as spouse_service,\r\n" + 
			 		" COALESCE(ms.spouse_personal_no,'') as spouse_personal_no 		\r\n" + 
			 		" from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
			 		"		left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id  and cdp.status=1 and pcl.status in " + comm_stats +	
			 		"		left join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality \r\n" + 
			 		"		left join tb_psg_census_family_married ms on ms.comm_id =pcl.id 	\r\n" + 
			 		"		LEFT JOIN tb_psg_mstr_marital_status mrr ON ms.marital_status = mrr.marital_id\r\n" + 
			 		"	left join tb_psg_mstr_exservicemen t1 on t1.id = ms.spouse_service "
		        		+ "where ms.status= 1  " + qry ;
	        	
	        
		    stmt=conn.prepareStatement(q);   	
		    
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		     	
	
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			    int k = 0;  
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> AWARD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(awm.comm_id  as character varying)=? ";
			}
	        q="select distinct \r\n" + 
	        		"COALESCE(td.award_cat,'') as medal_type,\r\n" + 
	        		"COALESCE(m.medal_name,'') as award_medal, "+
	        		/// bisag v1 220822 (commented because of issue in pdf)
//	        		"awm.date_of_award as doa, \r\n" + 
	        		"COALESCE((TO_CHAR(awm.date_of_award ,'DD-MON-YYYY')),'') as date_of_award,\r\n" + 
	        		"COALESCE(awm.unit,'') as unit \r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter pcl  \r\n" + 	        		
	        		"inner join tb_psg_census_awardsnmedal awm on awm.comm_id=pcl.id\r\n" + 
	        		"inner join tb_psg_mstr_award_category td on cast(td.id as character varying)= awm.category_8 \r\n" + 
	        		"inner join tb_psg_mstr_medal m on cast(m.id as character varying) = awm.select_desc \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and pcl.status in " + comm_stats +
	        		"where awm.status= 1 " +qry +" order by date_of_award asc";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
		
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	public List<Map<String, Object>> ARM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}	
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(ac.comm_id  as character varying)=? ";
			}
	        q="   select COALESCE(ac.course_name,'') as course_name,\r\n" + 
	        		" case when upper(dv.div)='OTHERS' or  upper(dv.div)='OTHER' then  COALESCE(ac.ar_course_div_ot,'')\r\n" + 
	        		"					else COALESCE(dv.div,'') end as grade ,\r\n" + 
	        		"					case when upper(ci.course_institute)='OTHERS' or  upper(ci.course_institute)='OTHER' then  COALESCE(ac.course_institute_other,'')\r\n" + 
	        		"					else COALESCE(ci.course_institute,'') end as course_institute, "+
	        		/// bisag v1 220822 (commented because of issue in pdf)
//	        		" ac.start_date as s_dt," + 
	        		"			COALESCE((TO_CHAR(ac.start_date ,'DD-MON-YYYY')),'') as from_date,\r\n" + 
	        		"			COALESCE((TO_CHAR(ac.date_of_completion ,'DD-MON-YYYY')),'') as to_date\r\n" + 
	        		"			from tb_psg_census_army_course ac\r\n" + 
	        		"	        		inner join tb_psg_trans_proposed_comm_letter pcl on pcl.id=ac.comm_id \r\n" + 
	        		"			inner join tb_psg_mstr_course_institute ci on ci.id=ac.course_institute \r\n" + 
	        		"	        		inner join tb_psg_mstr_div_grade dv on dv.id= cast (ac.div_grade as integer)  \r\n" + 
	        		"	        		where ac.status=1 and pcl.status in "+ comm_stats  + qry +" order by ac.start_date asc";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
			
			if(comm_status == 1  || comm_status == 5) {
				 	stmt.setString(j, "1");
					j += 1;
				 	stmt.setString(j, "5");
					j += 1;
				}
				if(comm_status == 4) {
				 	stmt.setString(j, "4");
					j += 1;
				}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> PE_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}	
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(ex.comm_id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		" case when upper(td.promo_exam)='OTHERS' or  upper(td.promo_exam)='OTHER' then  COALESCE(ex.exam_other,'')\r\n" + 
	        		"					else COALESCE(td.promo_exam,'') end as exam , \r\n" + 
	        		"to_char(CAST(concat(SUBSTRING(date_of_passing, 1, 4), '-' ,\r\n" + 
	        		"					SUBSTRING(date_of_passing, 6, 7) , '-' , '01') AS DATE) ,'Mon YYYY') as date_of_passing \r\n" + 
	        		"from  tb_psg_census_promo_exam ex\r\n" + 
	        		"inner join tb_psg_trans_proposed_comm_letter pcl on pcl.id=ex.comm_id\r\n" + 
	        		"inner join tb_psg_mstr_promotional_exam td on cast(td.id as character varying)= ex.exam  \r\n" + 
	        		"where ex.status=1 and pcl.status in " + comm_stats  +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    	
		
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> Field_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			if(!comm_id.equals("")) {
				qry += " and  cast(ch.comm_id  as character varying)=? ";
			}
		/*	if(!roleSusNo.equals("")) {
				qry+="  and f.sus_id = ?";
			}*/
			/* q="SELECT ch.personnel_no,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='peace')),0) as PEACE,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='field')),0) as FIELD,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='high_alt')),0) as HIGH_ALT,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')),0) as CI_OPS\r\n" + 
			 		"FROM tb_psg_field_service_p f \r\n" + 
			 		"inner join tb_psg_field_service_ch ch on f.id=ch.p_id \r\n" + 
			 		" where  "+qry+" group by ch.personnel_no" ;*/
			
			
			 q="SELECT ch.personnel_no,TO_CHAR(\r\n" + 
			 		"COALESCE((select   sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and (fd_services in (select area_id from tb_psg_field_area_combination where cat_name='peace')\r\n" + 
			 		"		 or  ((SELECT count(chi.id) from tb_psg_field_service_p pi inner join tb_psg_field_service_ch chi on pi.id=chi.p_id where chi.id!=ch1.id and ch1.comm_id=chi.comm_id and chi.to_date is not  null and\r\n" + 
			 		"		(ch1.from_date between chi.from_date and chi.to_date or ch1.to_date between chi.from_date and chi.to_date) and pi.fd_services not in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')) = 0 \r\n" + 
			 		"			  and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='field'))\r\n" + 
			 		"		 )),0),'fm00') as PEACE,\r\n" + 
			 		"TO_CHAR(COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='field')\r\n" + 
			 		"		 and  (SELECT count(chi.id) from tb_psg_field_service_p pi inner join tb_psg_field_service_ch chi on pi.id=chi.p_id where chi.id!=ch1.id and ch1.comm_id=chi.comm_id and chi.to_date is not  null and\r\n" + 
			 		"		(ch1.from_date between chi.from_date and chi.to_date or ch1.to_date between chi.from_date and chi.to_date) and pi.fd_services not in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')) = 0\r\n" + 
			 		"		 ),0),'fm00') as FIELD,\r\n" + 
			 		"TO_CHAR(COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='high_alt')),0),'fm00') as HIGH_ALT,\r\n" + 
			 		"TO_CHAR(COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p p inner join tb_psg_field_service_ch ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')),0),'fm00') as CI_OPS\r\n" + 
			 		"FROM tb_psg_field_service_p f\r\n" + 
			 		"inner join tb_psg_field_service_ch ch on f.id=ch.p_id \r\n" + 
			 		"where ch.cancel_status != 1 "+qry+"    group by ch.personnel_no \r\n " ;
	        		
		    stmt=conn.prepareStatement(q); 
		    
		    int j =1;
		    if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		   /* if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}*/

	
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			if (list.size()==0) {
				 Map<String, Object> columns1 = new LinkedHashMap<String, Object>();
			 	    for (int i = 1; i <= columnCount; i++) {
			 	    	columns1.put(metaData.getColumnLabel(i), 0);
			 	    }
			 	    list.add(columns1);
			}
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> PTQ_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(cq.comm_id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		"COALESCE(eq.examination_passed,'') as examination_pass, deg.degree as d_name,spe.specialization as spce_name, \r\n" + 
	        		"COALESCE(cq.passing_year,'0') as passing_year,\r\n" + 
	        		"case when upper(dg.div)='OTHERS' or  upper(dg.div)='OTHER' then  COALESCE(cq.class_other,'')\r\n" + 
	        		"					else COALESCE(dg.div,'') end as div_class ,\r\n" + 
	        		"COALESCE(STRING_AGG (DISTINCT s.description , ','),'') as description , COALESCE(cq.institute ,'') as  institute "
	        		+ " \r\n" + 
	        		" 	from tb_psg_trans_proposed_comm_letter pcl   \r\n" + 	        	
	        		"inner join tb_psg_census_qualification cq on cq.comm_id = pcl.id  "
	        		+ "inner join tb_psg_mstr_degree deg on deg.id=cq.degree \r\n"
	        		+ "inner join tb_psg_mstr_specialization spe on spe.id=cq.specialization "
	        		+ "inner join tb_psg_mstr_examination_passed eq on cq.examination_pass = eq.id \r\n" + 
	        		"inner join tb_psg_mstr_div_grade dg on dg.id=cq.div_class  \r\n" + 
	        		"inner join tb_psg_mstr_degree d on d.id =cq.degree  \r\n" + 
	        		"inner join tb_psg_mstr_specialization s1 on s1.id=cq.specialization  \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status='1'  \r\n" + 
	        		"left join tb_psg_census_subject s on cast(s.id as character varying) = any(string_to_array(cq.subject  , ','))\r\n" + 
	        		"where cq.status= 1 and  pcl.status in 	"+comm_stats + " and cq.type='6' " +qry +"group by 1,2,3,4,5,7  order by passing_year asc";
	     
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    
		
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	public List<Map<String, Object>> AQ_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(cq.comm_id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		"COALESCE(eq.examination_passed,'') as examination_pass, deg.degree as d_name,spe.specialization as spce_name, \r\n" + 
	        		"COALESCE(cq.passing_year) as passing_year,\r\n" + 
	        		"case when upper(dg.div)='OTHERS' or  upper(dg.div)='OTHER' then  COALESCE(cq.class_other,'')\r\n" + 
	        		"					else COALESCE(dg.div,'') end as div_class , \r\n" + 
	        		"COALESCE(STRING_AGG (DISTINCT s.description , ','),'') as description,"+
	        		"COALESCE(cq.institute ,'') as  institute "
	        		+ " \r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter pcl\r\n" + 	        		
	        		"inner join tb_psg_census_qualification cq on cq.comm_id = pcl.id "
	        		+ "inner join tb_psg_mstr_degree deg on deg.id=cq.degree \r\n"
	        		+ "inner join tb_psg_mstr_specialization spe on spe.id=cq.specialization \r\n" + 
	        		"inner join tb_psg_mstr_examination_passed eq on cq.examination_pass = eq.id \r\n" + 
	        		"inner join tb_psg_mstr_div_grade dg on dg.id=cq.div_class \r\n" + 
	        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 
	        		"left join tb_psg_census_subject s on  cast(s.id as character varying) = any(string_to_array(cq.subject  , ','))	\r\n" + 
	        		"where cq.status= 1  and  pcl.status in "+comm_stats + " and cq.type='2'"  +qry  + " group by 1,2,3,4,5,7  order by passing_year asc";
	        		
		    stmt=conn.prepareStatement(q);  	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
			
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
		 	
		 	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());	 	   	   
		 	   	    
		 	    }
		 	  
		 	    list.add(columns);
		 
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> ILan_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			
			if(!comm_id.equals("")) {
				qry += " and cast(cl.comm_id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		" DISTINCT case when upper(il.ind_language)='OTHERS' or  upper(il.ind_language)='OTHER' then  COALESCE(cl.other_language,'')\r\n" + 
	        		"					else COALESCE(il.ind_language,'') end as ind_language ,\r\n" + 
	        		"					case when upper(ls.name)='OTHERS' or  upper(ls.name)='OTHER' then  COALESCE(cl.other_lang_std,'')\r\n" + 
	        		"					else COALESCE(ls.name,'') end as lan_std  \r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	       
	        		"inner join tb_psg_census_language cl on cl.comm_id=pcl.id\r\n" + 
	        		"inner join tb_psg_lang_std ls on ls.id = cl.lang_std  \r\n" + 
	        		"inner join tb_psg_mstr_indian_language il on il.id = cl.language  "+
	         		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id  and cdp.status=1 and pcl.status in "+comm_stats + 
	        		 "where cl.status= 1   " +qry + "" ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    
			
		    ResultSet rs = stmt.executeQuery();    
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> FLan_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(cl.comm_id  as character varying)=? ";
			}
			 q="select  DISTINCT \r\n" + 
		        		"case when  upper(fl.foreign_language_name)='OTHERS' or  upper(fl.foreign_language_name)='OTHER' then cl.f_other_language\r\n" + 
		        		" else COALESCE(fl.foreign_language_name,'') end as foreign_language_name,\r\n" + 
		        		
		        		" case when  upper(ls.name)='OTHERS' or  upper(ls.name)='OTHER' then cl.f_other_lang_std\r\n" + 
		        		" else COALESCE(ls.name,'') end as lang_std, " + 
		        		" case when  upper(lp.name)='OTHERS' or  upper(lp.name)='OTHER' then cl.f_other_prof\r\n" + 
		        		" else COALESCE(lp.name,'') end as lang_prof \r\n" + 
		        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
		        		"left join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id and cdp.status=1 and pcl.status in "+comm_stats + 
		        		"inner join tb_psg_census_language cl on cl.comm_id=pcl.id\r\n" + 
		        		"inner join tb_psg_lang_proof lp on lp.id= cl.f_lang_prof "
		        		+ " inner join tb_psg_lang_std ls on ls.id = cl.lang_std   \r\n" + 
		        		"inner join tb_psg_mstr_foreign_language fl on fl.id= cl.foreign_language where cl.status= 1   " +qry + " ";
		        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    	
		    ResultSet rs = stmt.executeQuery();    

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> F_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}	
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(fc.comm_id  as character varying)=? ";
			}
			//26-01-1994
//			q="select "
//					+ " case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(fc.other_country,'')\r\n" + 
//					"					else COALESCE(co.name,'') end as name ,\r\n" + 
//					"			case when upper(td.label)='OTHERS' or  upper(td.label)='OTHER' then  COALESCE(fc.other_purpose_visit,'')\r\n" + 
//					"					else COALESCE(td.label,'') end as purpose_visit ,"
//					+ "(TO_CHAR(fc.from_dt ,'DD-MON-YYYY')) as from_dt,\r\n" + 
//					"	        		(TO_CHAR(fc.to_dt ,'DD-MON-YYYY')) as  to_dt,fc.period\r\n" + 
//					"	        		 from tb_psg_trans_proposed_comm_letter pcl 					\r\n" + 
//					"	        		inner join tb_psg_census_foreign_country fc on fc.comm_id= pcl.id \r\n" + 
//					"	        		inner join tb_psg_foreign_country co on co.id=fc.country  					\r\n" + 
//					"					inner join t_domain_value td on td.codevalue=cast(fc.purpose_visit as character varying)  \r\n" + 
//					"					and domainid='FOREIGN_COUNTRY' and pcl.status in "+comm_stats + " where fc.status= 1 \r\n" + 
//					"					  " +qry + " order by fc.id";
			q="select DISTINCT case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(fc.other_country,'')\r\n"
					+ "					else COALESCE(co.name,'') end as name ,\r\n"
					+ "			case when upper(td.visit_purpose)='OTHERS' or  upper(td.visit_purpose)='OTHER' then  COALESCE(fc.other_purpose_visit,'')\r\n"
					+ "					else COALESCE(td.visit_purpose,'') end as purpose_visit ,(TO_CHAR(fc.from_dt ,'DD-MON-YYYY')) as from_dt, "
					/// bisag v1 220822 (commented because of issue in pdf)
//					+ " fc.from_dt as f_dt,\r\n"
					+ "	        		(TO_CHAR(fc.to_dt ,'DD-MON-YYYY')) as  to_dt,fc.period\r\n"
					+ "	        		 from tb_psg_trans_proposed_comm_letter pcl 					\r\n"
					+ "	        		inner join tb_psg_census_foreign_country fc on fc.comm_id= pcl.id \r\n"
					+ "	        		inner join tb_psg_foreign_country co on co.id=fc.country  					\r\n"
					+ "					inner join tb_psg_mstr_purposeof_visit td on td.id = fc.purpose_visit\r\n"
					+ "					and pcl.status in "+comm_stats + " where fc.status= 1 \r\n" + 
					"					  " +qry + " ";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
			
			if(comm_status == 1  || comm_status == 5) {
				 	stmt.setString(j, "1");
					j += 1;
				 	stmt.setString(j, "5");
					j += 1;
				}
				if(comm_status == 4) {
				 	stmt.setString(j, "4");
					j += 1;
				}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
		
		    ResultSet rs = stmt.executeQuery();    
	  
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> B_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(bp.comm_id  as character varying)=? ";
			}
	        q=" select distinct \r\n" + 
	        		" case when upper(fr.bpet_result)='OTHERS' or  upper(fr.bpet_result)='OTHER' then  COALESCE(bp.bept_result_others,'')\r\n" + 
	        		"					else COALESCE(fr.bpet_result,'') end as bpet_result ,\r\n" + 
	        		"COALESCE(di.bpet_qt,'') as bpet_qt,\r\n" + 
	        		"COALESCE(bp.year,'0') as yearb \r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter pcl\r\n" + 
	        		"inner join tb_psg_census_bpet bp  on bp.comm_id= pcl.id\r\n" + 
	        		"inner join tb_psg_mstr_bpet_result  fr on cast(fr.id as character varying)= bp.bpet_result\r\n" + 
	        		"inner join tb_psg_mstr_bpet_qt di on cast(di.id as character varying)=bp.bpet_qe \r\n" + 
	        		" where pcl.status in "+comm_stats + " and  bp.status = 1  " +qry +" order by yearb asc";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		
		    ResultSet rs = stmt.executeQuery();    
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}



	public List<Map<String, Object>> FS_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(fs.comm_id  as character varying)=? ";
			}
	        q="select distinct \r\n" + 
	        		" case when upper(fr.firing_result)='OTHERS' or  upper(fr.firing_result)='OTHER' then  COALESCE(fs.ot_firing_grade,'')\r\n" + 
	        		"					else COALESCE(fr.firing_result,'') end as firing_grade ,\r\n" + 
	        		"COALESCE(di.bpet_qt,'') as firing_event_qe,\r\n" + 
	        		"COALESCE(fs.year,'0') as yearf \r\n" + 
	        		" from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		" inner join tb_psg_census_firing_standard  fs on fs.comm_id= pcl.id\r\n" + 
	        		" inner join tb_psg_mstr_firing_result  fr on cast(fr.id as character varying)= fs.firing_grade\r\n" + 
	        		" inner join tb_psg_mstr_bpet_qt di on cast(di.id as character varying)=fs.firing_event_qe \r\n" + 
	        		" where pcl.status in "+comm_stats + " and  fs.status = 1  " +qry +" order by yearf asc";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    	
		
		    ResultSet rs = stmt.executeQuery();    
		 
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}



	public List<Map<String, Object>> BA_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}	
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(pc.comm_id  as character varying)=? ";
			}
	        q="select distinct\r\n" + 
	        		"COALESCE(tdc.label ,'') as classification_of_casuality,\r\n" + 
	        		"COALESCE(td.label,'') as nature_of_casuality,\r\n" + 
	        		"COALESCE(pc.name_of_operation,'') as name_of_operation, \r\n" + 
	        		"COALESCE((TO_CHAR(pc.date_of_casuality ,'DD-MON-YYYY')),'') as  date_of_casuality ,\r\n" + 
	        		"COALESCE(pc.cause_of_casuality,'') as cause_of_casuality,\r\n" + 
	        		"COALESCE(pc.exact_place,'') as exact_place\r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"inner join tb_psg_census_battle_physical_casuality pc on pc.comm_id= pcl.id and pcl.status in "+comm_stats + 
	        		"inner join t_domain_value td on td.codevalue=pc.nature_of_casuality    and domainid='NATURE_OF_CASUALITY'  \r\n" + 
	        		"inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" +
	        		"where pc.status = 1 " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		   

		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> SC_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        String q="";
        String qry="";
        String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		         
                if(!roleSusNo.equals("")) {
                        qry+=" and pcl.unit_sus_no = ?";
                }
                if(!comm_id.equals("")) {
                        qry += " and cast(sc.comm_id  as character varying)=? ";
                }
        q="select distinct"+
        		 " COALESCE(sm.seconded_to,'') as seconded_to,"+
        		 " COALESCE((TO_CHAR(sc.secondment_effect ,'DD-MON-YYYY')),'') as secondment_effect \r\n" + 
                 "from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
                 " inner join tb_psg_census_secondment sc on sc.comm_id= pcl.id and  pcl.status in "+comm_stats +
                 " inner join tb_psg_mstr_seconded_to sm on sm.id= sc.seconded_to  \r\n" +
                 " where sc.status = 1 " +qry ;
                        
            stmt=conn.prepareStatement(q);           
            int j =1;
            if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
            if (!roleSusNo.equals("")) {
                        stmt.setString(j, roleSusNo);
                        j += 1;
                }

                if( !comm_id.equals("")) {
                        stmt.setString(j,String.valueOf(comm_id));
                        j += 1;        
                } 
 
            ResultSet rs = stmt.executeQuery();    
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                     
                while (rs.next()) {
                        Map<String, Object> columns = new LinkedHashMap<String, Object>();
                     for (int i = 1; i <= columnCount; i++) {
                    	 if(rs.getObject(i)==null)
     						columns.put(metaData.getColumnLabel(i), "");
     					else
     						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
                     }
                     list.add(columns);
                  }
                      
                 rs.close();
                 stmt.close();
                 conn.close();
                 
     }catch (SQLException e){
             e.printStackTrace();
         }finally{
                 if(conn != null){
                         try{
                                 conn.close();
                         }catch (SQLException e){
                         }
                 }
         }
         return list;
        }

	public List<Map<String, Object>> PDO_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(pcl.id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		"	case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(ca.pre_country_other,'')\r\n" + 
	        		"					else COALESCE(co.name,'') end as pre_country ,\r\n" + 
	        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(ca.pre_domicile_state_other,'')\r\n" + 
	        		"					else COALESCE(st.state_name,'') end as pre_state ,\r\n" + 
	        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(ca.pre_domicile_district_other,'')\r\n" + 
	        		"					else COALESCE(di.district_name,'') end as pre_dis ,\r\n" + 
	        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(ca.pre_domicile_tesil_other,'')\r\n" + 
	        		"					else COALESCE(th.city_name,'') end as pre_teh " + 
	        		"from  tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"	        		inner join tb_psg_census_address ca  on pcl.id=ca.comm_id  and ca.status=1 and pcl.status in "+comm_stats +
	        		"	        		inner join tb_psg_mstr_country co on co.id= ca.pre_country \r\n" + 
	        		"	        		inner join tb_psg_mstr_state st on st.state_id = ca.pre_state \r\n" + 
	        		"	        		inner join tb_psg_mstr_district di on di.district_id = ca.pre_district \r\n" + 
	        		"	        		inner join tb_psg_mstr_city th on th.city_id = ca.pre_tesil \r\n" + 
	        		"	        		where ca.status !=0   " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
			
		    ResultSet rs = stmt.executeQuery();    
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> ORM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(pcl.id  as character varying)=? ";
			}
	        q=" select \r\n" + 
	        		"case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(c.org_domicile_country_other,'')\r\n" + 
	        		"					else COALESCE(co.name,'') end as ori_country ,\r\n" + 
	        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(c.org_domicile_state_other,'')\r\n" + 
	        		"					else COALESCE(st.state_name,'') end as ori_state ,\r\n" + 
	        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(c.org_domicile_district_other,'')\r\n" + 
	        		"					else COALESCE(di.district_name,'') end as ori_dis ,\r\n" + 
	        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(c.org_domicile_tehsil_other,'')\r\n" + 
	        		"					else COALESCE(th.city_name,'') end as ori_teh  "+
	        	    "from tb_psg_trans_proposed_comm_letter pcl  \r\n" + 
	        		"left join tb_psg_census_detail_p c  on pcl.id=c.comm_id and c.status=1 and pcl.status in "+comm_stats + 
	        		"inner join tb_psg_mstr_country co on co.id= c.org_country \r\n" + 
	        		"inner join tb_psg_mstr_state st on st.state_id = c.org_state \r\n" + 
	        		"inner join tb_psg_mstr_district di on di.district_id = c.org_district \r\n" + 
	        		"inner join tb_psg_mstr_city th on th.city_id = c.org_tehsil  \r\n" + 
	        		"where c.status !=0   " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    	
		    ResultSet rs = stmt.executeQuery();    
	
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> PM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(add.comm_id  as character varying)=? ";
			}
	        q=" select \r\n" + 
	        		" case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(add.per_home_addr_country_other,'')\r\n" + 
	        		"					else COALESCE(co.name,'') end as pm_country ,\r\n" + 
	        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(add.per_home_addr_state_other,'')\r\n" + 
	        		"					else COALESCE(st.state_name,'') end as pm_state ,\r\n" + 
	        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(add.per_home_addr_district_other,'')\r\n" + 
	        		"					else COALESCE(di.district_name,'') end as pm_dis ,\r\n" + 
	        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(add.per_home_addr_tehsil_other,'')\r\n" + 
	        		"					else COALESCE(th.city_name,'') end as pm_teh ," + 
	        		"COALESCE(add.permanent_village,'') as permanent_village,\r\n" + 
	        		"COALESCE(add.permanent_pin_code,'0') as permanent_pin_code,\r\n" + 
	        		"COALESCE(add.permanent_near_railway_station,'') as permanent_near_railway_station,\r\n" + 
	        		"COALESCE(add.permanent_border_area,'') as permanent_border_area "+
	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" +	        	
	        		"inner join tb_psg_census_address add  on pcl.id=add.comm_id and add.status=1 and pcl.status in "+comm_stats + 
	        		"inner join tb_psg_mstr_country co on co.id=add.permanent_country \r\n" + 
	        		"inner join tb_psg_mstr_state st on st.state_id = add.permanent_state \r\n" + 
	        		"inner join tb_psg_mstr_district di on di.district_id = add.permanent_district \r\n" + 
	        		"inner join tb_psg_mstr_city th on th.city_id= add.permanent_tehsil  "
	        		+ "where add.status !=0   " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		  
		    ResultSet rs = stmt.executeQuery();    
		  
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> PS_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(add.comm_id  as character varying)=? ";
			}
	        q="select "
	        		+ " case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(add.pers_addr_country_other,'')\r\n" + 
	        		"					else COALESCE(co.name,'') end as ps_country ,\r\n" + 
	        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(add.pers_addr_state_other,'')\r\n" + 
	        		"					else COALESCE(st.state_name,'') end as ps_state ,\r\n" + 
	        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(add.pers_addr_district_other,'')\r\n" + 
	        		"					else COALESCE(di.district_name,'') end as ps_dis ,\r\n" + 
	        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(add.pers_addr_tehsil_other,'')\r\n" + 
	        		"					else COALESCE(th.city_name,'') end as ps_teh ,	" + 
	        		"COALESCE(add.present_village,'') as permanent_village,\r\n" + 
	        		"COALESCE(add.present_pin_code,'0') as present_pin_code,\r\n" + 
	        		"COALESCE(add.present_near_railway_station,'') as present_near_railway_station\r\n" + 
	        		" from tb_psg_trans_proposed_comm_letter pcl \r\n" + 
	        		"inner join tb_psg_census_address add on pcl.id=add.comm_id and add.status=1 and pcl.status in "+comm_stats +
	        		"inner join tb_psg_mstr_country co on co.id=add.present_country \r\n" + 
	        		"inner join tb_psg_mstr_state st on st.state_id = add.present_state  \r\n" + 
	        		"inner join tb_psg_mstr_district di on di.district_id = add.present_district  \r\n" + 
	        		"inner join tb_psg_mstr_city th on th.city_id = add.present_tehsil \r\n" + 
	        		" where add.status !=0   " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    ResultSet rs = stmt.executeQuery();    
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> NOK_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(nok.comm_id  as character varying)=? ";
			}
	        q="select distinct\r\n" + 
	        		"COALESCE(nok.nok_name ,'') as nok_name,\r\n" + 
	        		" case when upper(rel.relation_name)='OTHERS' or  upper(rel.relation_name)='OTHER' then  COALESCE(nok.relation_other,'')\r\n" + 
	        		"					else COALESCE(rel.relation_name,'') end as relation_name \r\n" + 
	        		"from  tb_psg_trans_proposed_comm_letter  pcl\r\n" + 
	        		"inner join tb_psg_census_nok nok  on pcl.id = nok.comm_id and nok.status=1 and  pcl.status in "+comm_stats +
	        		"inner join tb_psg_mstr_relation rel on rel.id=nok.nok_relation  \r\n" + 
	        		" where nok.status !=0 " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 

		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> NA_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}		
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(nok.comm_id  as character varying)=? ";
			}
	        q="select "
	        		+ " case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(nok.ctry_other,'')\r\n" + 
	        		"					else COALESCE(co.name,'') end as nok_country ,\r\n" + 
	        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(nok.st_other,'')\r\n" + 
	        		"					else COALESCE(st.state_name,'') end as nok_state ,\r\n" + 
	        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(nok.dist_other,'')\r\n" + 
	        		"					else COALESCE(di.district_name,'') end as nok_dis ,\r\n" + 
	        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(nok.tehsil_other,'')\r\n" + 
	        		"					else COALESCE(th.city_name,'') end as nok_teh ," + 
	        		"COALESCE(nok.nok_village,'') as nok_village,\r\n" + 
	        		"COALESCE(nok.nok_pin,'0') as nok_pin,\r\n" + 
	        		"COALESCE(nok.nok_near_railway_station,'') as nok_near_railway_station,\r\n" + 
	        		"COALESCE(CAST(PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version')) AS decimal(10,0)) ,'0') as nok_mobile_no \r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter  pcl \r\n" + 
	        		"inner join tb_psg_census_nok nok on pcl.id = nok.comm_id and nok.status=1 and pcl.status in "+comm_stats +
	        		"inner join tb_psg_mstr_country co on co.id=nok.nok_country \r\n" + 
	        		"inner join tb_psg_mstr_state st on st.state_id = nok.nok_state  \r\n" + 
	        		"inner join tb_psg_mstr_district di on di.district_id = nok.nok_district \r\n" + 
	        		"inner join tb_psg_mstr_city th on th.city_id = nok.nok_tehsil \r\n" + 	        		        		
	        		" where nok.status !=0  " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 

		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> FM_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!comm_id.equals("")) {
				qry += " and cast(ce.comm_id  as character varying)=? ";
			}
	        q="select \r\n" + 
	        		"COALESCE(ce.father_name,'') as father_name,\r\n" + 
	        		"COALESCE((TO_CHAR(ce.father_dob ,'DD-MON-YYYY')),'') as father_dob,\r\n" + 
	        		"COALESCE(ce.mother_name,'') as mother_name,\r\n" + 
	        		"COALESCE((TO_CHAR(ce.mother_dob ,'DD-MON-YYYY')),'') as mother_dob,\r\n" + 
	        		"(TO_CHAR(fs.date_of_birth ,'DD-MON-YYYY')) as date_of_birth,\r\n" + 
	        		"fs.name\r\n" + 
	        		"from tb_psg_trans_proposed_comm_letter  pcl\r\n" + 
	        		"left  join tb_psg_census_detail_p ce  on pcl.id = ce.comm_id and ce.status=1 and pcl.status in "+comm_stats + 
	        		"left join tb_psg_census_family_siblings fs on fs.comm_id=pcl.id\r\n" +       			        		        		
	        		" where ce.status !=0  " +qry ;
	        		
		    stmt=conn.prepareStatement(q);   	
		   
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		  
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> AR_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        String q="";
        String qry="";
        String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		           
                if(!roleSusNo.equals("")) {
                        qry+=" and pcl.unit_sus_no = ?";
                }
                if(!comm_id.equals("")) {
                        qry += " and cast(add.comm_id  as character varying)=? ";
                }
                q="select \r\n" + 
		        		"COALESCE(CAST(PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version')) AS decimal(10,0)),'0') as nok_mobile_no ,\r\n" + 
		        		"case when (upper(co.name)='OTHERS' or  upper(co.name)='OTHER') then COALESCE(add.per_home_addr_country_other,'')\r\n" + 
		        		"else COALESCE(co.name,'')\r\n" + 
		        		"end as ar_country,\r\n" + 
		        		"case when (upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER') then COALESCE(add.per_home_addr_state_other,'')\r\n" + 
		        		"else COALESCE(st.state_name,'')\r\n" + 
		        		"end as ar_state,\r\n" + 
		        		"case when (upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER') then COALESCE(add.per_home_addr_district_other,'')\r\n" + 
		        		"else COALESCE(di.district_name,'')\r\n" + 
		        		"end as ar_dis,\r\n" + 
		        		"case when (upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER') then COALESCE(add.per_home_addr_tehsil_other,'')\r\n" + 
		        		"else COALESCE(th.city_name,'')\r\n" + 
		        		"end as ar_teh," + 
		        		"COALESCE(add.permanent_village,'') as permanent_village,\r\n" + 
		        		"COALESCE(add.permanent_pin_code,'0') as permanent_pin_code,\r\n" + 
		        		"COALESCE(add.permanent_near_railway_station,'') as permanent_near_railway_station,\r\n" + 
		        		"COALESCE(add.permanent_border_area,'') as permanent_border_area \r\n" + 
                        " from tb_psg_trans_proposed_comm_letter  pcl \r\n" + 
                        " inner join tb_psg_census_nok nok on pcl.id = nok.comm_id and nok.status=1 and  pcl.status in "+comm_stats +
                        "inner join tb_psg_census_address add on pcl.id=add.comm_id and add.status=1 \r\n" + 
                        "left join tb_psg_mstr_country co on co.id=add.permanent_country  \r\n" + 
                        "left join tb_psg_mstr_state st on st.state_id = add.permanent_state \r\n" + 
                        "left join tb_psg_mstr_district di on di.district_id = add.permanent_district   \r\n" + 
                        "left join tb_psg_mstr_city th on th.city_id = add.permanent_tehsil  " + 
                        " where add.status !=0  " +qry ;
                        
            stmt=conn.prepareStatement(q);           
            int j =1;
            if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
            if (!roleSusNo.equals("")) {
                        stmt.setString(j, roleSusNo);
                        j += 1;
                }

                
                if( !comm_id.equals("")) {
                        stmt.setString(j,String.valueOf(comm_id));
                        j += 1;        
                } 
                    
            ResultSet rs = stmt.executeQuery();    

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                     
                while (rs.next()) {
                        Map<String, Object> columns = new LinkedHashMap<String, Object>();
                     for (int i = 1; i <= columnCount; i++) {
                    	 if(rs.getObject(i)==null)
     						columns.put(metaData.getColumnLabel(i), "");
     					else
     						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
                     }
                     list.add(columns);
                  }
                      
                 rs.close();
                 stmt.close();
                 conn.close();
                 
	     }catch (SQLException e){
	             e.printStackTrace();
	         }finally{
	                 if(conn != null){
	                         try{
	                                 conn.close();
	                         }catch (SQLException e){
	                         }
	                 }
	         }
         return list;
       }
	
//	public List<Map<String, Object>> TENU_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo) throws ParseException {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		
//		Connection conn = null;
//		String q="";
//		String qry="";
//		try{	  
//			conn = dataSource.getConnection();					
//			PreparedStatement stmt =null;
//			PreparedStatement stmt1 =null;
//			
//			
//			if(!comm_id.equals("")) {
//				qry += "   post.comm_id=? ";
//			}
//			if(!roleSusNo.equals("")) {
//				qry+=" and pcl.unit_sus_no = ?";
//			}
//	        q="select distinct r.description as rank,r1.description as appointment,orb.unit_name,l.code_value as place,l1.label as unit_loc_type,fv.unit_name as command,post.from_sus_no\r\n" + 
//	        		",post.dt_of_sos,post.dt_of_tos\r\n" + 
//	        		"from\r\n" + 
//	        		"tb_psg_trans_proposed_comm_letter p inner join\r\n" + 
//	        		"tb_psg_census_detail_p c on p.id=c.comm_id --and p.personnel_no='NTS21482N'\r\n" + 
//	        		"inner join  cue_tb_psg_rank_app_master r on r.id = p.rank and r.status_active = 'Active'\r\n" + 
//	        		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active'\r\n" + 
//	        		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
//	        		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
//	        		"inner join all_fmn_view fv  on orb.sus_no = p.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
//	        		"left join   tb_psg_change_of_appointment a on a.comm_id=p.id\r\n" + 
//	        		"left join  cue_tb_psg_rank_app_master r1 on r1.id =a.appointment and r1.status_active = 'Active'\r\n" + 
//	        		"left join tb_psg_posting_in_out post on p.id = post.comm_id and   post.status = 1\r\n" + 
//	        		"left join tb_psg_posting_in_out post1 on p.id = post1.comm_id and  post1.status = 1\r\n" + 
//	        		"where   " +qry +" order by dt_of_tos asc";
//	        		
//		    stmt=conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);;   
//		    stmt1=conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);;  
//		  
//		    int j =1;
//			
//		    if( !comm_id.equals("")) {
//				stmt.setString(j,String.valueOf(comm_id));
//				stmt1.setInt(j, Integer.parseInt(comm_id));
//				j += 1;	
//			} 
//		    
//		    if (!roleSusNo.equals("")) {
//				stmt.setString(j, roleSusNo);
//				stmt1.setString(j, roleSusNo);
//				j += 1;
//			}
//
//			
//			 /* if (roleSusNo != "") {
//					stmt1.setString(j, roleSusNo);
//					j += 1;
//				}*/
//
//				/*if( !comm_id.equals("")) {
//					stmt1.setInt(j, Integer.parseInt(comm_id));
//					j += 1;	
//				} */
//		
//		    ResultSet rs = stmt.executeQuery();  
//		    ResultSet rs1 = stmt1.executeQuery();      
//		    
//		  
//		
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();			
//			int j1 =2;     
//			while (rs.next()) {
//				 int diffInMonth = 0;
//				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.ENGLISH);
//			//	String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
//				Date date = new Date(); 	
//				String from_sus_no = rs.getString("from_sus_no");					
//				Date da = formatter1.parse(rs.getString("dt_of_sos"));	
//				
//				if(!rs.isLast())
//				{				
//						
//					rs1.absolute(j1);
//					j1++;
//					Date da1 = formatter1.parse(rs1.getString("dt_of_sos"));			
//					 long diff = da1.getTime() - da.getTime();
//					 diffInMonth  = (int) (diff / (60 * 60 * 1000 * 24 * 30.41666666 ));	
//				}
//				else {
//					if(rs.isLast()) {
//						long diffL = date.getTime() - da.getTime();
//					    diffInMonth  = (int) (diffL / (60 * 60 * 1000 * 24 * 30.41666666 ));
//					}
//				}
//				
//				
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();				
//		 	    for (int i = 1; i <= columnCount; i++) {
//		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//		 	    		columns.put("month", diffInMonth);
//		 	    }
//		 	    list.add(columns);	
//		 	 }
//			 
//			 rs.close();
//			 stmt.close();
//			 conn.close();
//			 
//	     }catch (SQLException e){
//	    	 e.printStackTrace();
//		 }finally{
//			 if(conn != null){
//				 try{
//					 conn.close();
//				 }catch (SQLException e){
//				 }
//			 }
//		 }
//		 return list;
//		}
	public List<Map<String, Object>> TENU_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?)  ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!comm_id.equals("")) {
				qry += " cast(op.comm_id  as character varying)=? ";
			}
	       /* q="select op.to_sus_no,\r\n" + 
	        		"r.description as rank,case when (op.app_name is null or op.app_name = 0)\r\n" + 
	        		"then \r\n" + 
	        		"\r\n" + 
	        		"(select  ra1.description \r\n" + 
	        		" \r\n" + 
	        		"from tb_psg_change_of_appointment ap \r\n" + 
	        		"inner join tb_psg_posting_in_out op1 on ap.comm_id=op1.comm_id \r\n" + 
	        		"inner join cue_tb_psg_rank_app_master ra1 on  ra1.id = appointment and upper(ra1.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
	        		"where ap.comm_id=? and op1.from_sus_no is null and to_date(to_char(ap.date_of_appointment,'Mon YYYY'),'Mon YYYY')  <= to_date(to_char(op1.tenure_date,'Mon YYYY'),'Mon YYYY') order by ap.id limit 1)\r\n" + 
	        		"\r\n" + 
	        		"else r1.description end as  appointment,\r\n" + 
	        		"orb.unit_name,l.code_value as place,l1.label as unit_loc_type,fv.unit_name as command,\r\n" + 
	        		"case when op.from_sus_no is null then ''\r\n" + 
	        		"else orb1.unit_name end as from_unit_name,case when op.tenure_date is null then\r\n" + 
	        		"(EXTRACT(year FROM age(now()::timestamp without time zone,op.dt_of_tos))*12 + EXTRACT(month FROM age(now()::timestamp without time zone,op.dt_of_tos)))::int\r\n" + 
	        		"else\r\n" + 
	        		"(EXTRACT(year FROM age(op.tenure_date,op.dt_of_tos))*12 + EXTRACT(month FROM age(op.tenure_date,op.dt_of_tos)))::int\r\n" + 
	        		"end as month\r\n" + 
	        		"from\r\n" + 
	        		"tb_psg_trans_proposed_comm_letter c \r\n" + 
	        		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status = '1'\r\n" + 
	        		"inner join  cue_tb_psg_rank_app_master r on r.id = op.rank and r.status_active = 'Active'\r\n" + 
	        		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = op.to_sus_no and status_sus_no='Active'\r\n" + 
	        		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
	        		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
	        		"inner join all_fmn_view fv  on orb.sus_no = op.to_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
	        		"left join  cue_tb_psg_rank_app_master r1 on r1.id =op.app_name and r1.status_active = 'Active'\r\n" + 
	        		"left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = op.from_sus_no and orb1.status_sus_no='Active' \r\n" + 
	        		"where " + qry +
	        		"order by op.id ";
	        */
	        q="select\r\n" + 
	        		"COALESCE(orb.unit_name ,'') as unit_name ,\r\n" + 
	        		"COALESCE(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY'),'') as dt_of_tos,\r\n" + 
	        		"case when COALESCE(LEAD(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY')) OVER (ORDER BY op.id ),'') != '' then \r\n" + 
	        		"TO_CHAR((LEAD(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY')) OVER (ORDER BY op.id ))::TIMESTAMP - INTERVAL '1 DAY' ,'DD-MON-YYYY')\r\n" + 
	        		"ELSE '' END as nextvalue ,\r\n" + 
	        		"COALESCE(l.code_value,'') as place,\r\n" + 
	        		"COALESCE(l1.label,'') as unit_loc_type,\r\n" + 
	        		"COALESCE(fv.unit_name,'') as command,\r\n" + 
	        		"CASE when  op.tenure_date is null then\r\n" + 
	        		"CAST(EXTRACT(year FROM age(now()::timestamp without time zone,op.dt_of_tos))*12 + \r\n" + 
	        		"         EXTRACT(month FROM age(now()::timestamp without time zone,op.dt_of_tos)) AS INTEGER)\r\n" + 
	        		"else\r\n" + 
	        		"     CAST(EXTRACT(year FROM age(op.tenure_date,op.dt_of_tos))*12 + \r\n" + 
	        		"                  EXTRACT(month FROM age(op.tenure_date,op.dt_of_tos)) AS INTEGER) + 1 end as month   \r\n" + 
	        		"from\r\n" + 
	        		"tb_psg_trans_proposed_comm_letter c \r\n" + 
	        		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status in "+comm_stats + " and op.status='1'\r\n" + 
	        		"inner join  cue_tb_psg_rank_app_master r on r.id = op.rank \r\n" + 
	        		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = op.to_sus_no and upper(orb.status_sus_no) in ('INACTIVE','ACTIVE' )  \r\n" + 
	        		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
	        		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
	        		"inner join all_fmn_view fv  on orb.sus_no = op.to_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) \r\n" + 
	        		"            =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
	        		"left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = op.from_sus_no and upper(orb1.status_sus_no) in ('INACTIVE','ACTIVE' )  \r\n" + 
	        		"left join  cue_tb_psg_rank_app_master r1 on r1.id =op.app_name \r\n" + 
	        		"where  " + qry +"  order by op.id";
	        		
		    stmt=conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);   	
		    int j =1;
//		    if (!roleSusNo.equals("")) {
//				stmt.setString(j, roleSusNo);
//				j += 1;
//			}
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
				
			} 
		
		    ResultSet rs = stmt.executeQuery();    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
		
			    int sum=0;
			    int i=1;
			    Map<String, Object> columns = null;
			while (rs.next()) {
				 columns = new LinkedHashMap<String, Object>();
		 	    for ( i = 1; i <= columnCount; i++) {
		 	  	   
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	   /* if(rs.getObject(7) != null && !rs.getObject(7).equals(""))
		 	    {
		 	    	sum+=Integer.parseInt(String.valueOf(rs.getObject(7)));
		 	    }
		 		if (rs.isLast()) 
		 		{
		 			columns.put("Total_tenure", sum);		
		 		}*/
		 	    list.add(columns);
		 	 }
			
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	
	public List<Map<String, Object>> TENU_Total_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status)  {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?)  ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
				if(!comm_id.equals("")) {
				qry += " cast(op.comm_id  as character varying)=? ";
			}
	
	        q="select --op.to_sus_no, \r\n" + 
	        		" orb.unit_name ,TO_CHAR(op.dt_of_tos,'DD-MON-YYYY') as dt_of_tos,\r\n" + 
	        		"COALESCE(LEAD(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY')) OVER (ORDER BY op.id ),'')as nextvalue ,\r\n" + 
	        		"l.code_value as place,l1.label as unit_loc_type,fv.unit_name as command,\r\n" + 
	        		"case when  op.tenure_date is null then\r\n" + 
	        		"CAST(EXTRACT(year FROM age(now()::timestamp without time zone,op.dt_of_tos))*12 + \r\n" + 
	        		"	 EXTRACT(month FROM age(now()::timestamp without time zone,op.dt_of_tos)) AS INTEGER)\r\n" + 
	        		"else\r\n" + 
	        		"     CAST(EXTRACT(year FROM age(op.tenure_date,op.dt_of_tos))*12 + \r\n" + 
	        		"		  EXTRACT(month FROM age(op.tenure_date,op.dt_of_tos)) AS INTEGER)+1 end as month   \r\n" + 
	        		"from\r\n" + 
	        		"tb_psg_trans_proposed_comm_letter c \r\n" + 
	        		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status in "+comm_stats + " and op.status='1'\r\n" +
	        		"inner join  cue_tb_psg_rank_app_master r on r.id = op.rank \r\n" + 
	        		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = op.to_sus_no and upper(orb.status_sus_no) in ('INACTIVE','ACTIVE' ) \r\n" + 
	        		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
	        		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
	        		"inner join all_fmn_view fv  on orb.sus_no = op.to_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
	        		"left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = op.from_sus_no  and upper(orb1.status_sus_no) in ('INACTIVE','ACTIVE' ) \r\n" + 
	        		"left join  cue_tb_psg_rank_app_master r1 on r1.id =op.app_name \r\n" + 
	        		"where " + qry + "order by op.id";
	        
	        
	        
	        
	     
		    stmt=conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);   	
		    int j =1;
//		    if (!roleSusNo.equals("")) {
//				stmt.setString(j, roleSusNo);
//				j += 1;
//			}

		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setString(j, "1");
				j += 1;
			 	stmt.setString(j, "5");
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setString(j, "4");
				j += 1;
			}
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
				
			} 
		    ResultSet rs = stmt.executeQuery();    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			    int sum=0;
			    int i=1;
			    Map<String, Object> columns = null;
			while (rs.next()) {
				 columns = new LinkedHashMap<String, Object>();
		 	  
		 	    if(rs.getObject(7) != null && !rs.getObject(7).equals(""))
		 	    {
		 	    	sum+=Integer.parseInt(String.valueOf(rs.getObject(7)));
		 		}
		 	  
		 	 }
			 
			 columns.put("Total_tenure", sum);	
		 	    list.add(columns);
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	
	@Override
	public Date getParentModifiedDate(int census_id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DETAIL_Parent where id=:census_id and status = '1' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DETAIL_Parent> list = (List<TB_CENSUS_DETAIL_Parent>) query.list();
		tx.commit();
		sessionHQL.close();
		return list.get(0).getModified_date();
	}
	
	public List<TB_CHANGE_OF_RANK> getChangeOfRankData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_RANK where census_id=:census_id and status = '1' and comm_id=:comm_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_OF_RANK> list = (List<TB_CHANGE_OF_RANK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public List<TB_CHANGE_TNAI_NO> getChangeOfTnaiNoData(int census_id, BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TNAI_NO where census_id=:census_id and status = '1' and comm_id=:comm_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TNAI_NO> list = (List<TB_CHANGE_TNAI_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public List<TB_CHANGE_NAME> getChangeOfNameData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME where census_id=:census_id and status='1' and comm_id=:comm_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public List<TB_NON_EFFECTIVE> getNon_effective(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE where census_id=:census_id and status='1' and comm_id=:comm_id  and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE> list = (List<TB_NON_EFFECTIVE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	public List<TB_CENSUS_CHILDREN> getm_children_detailsData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CHILDREN where cen_id=:cen_id and status='1' and comm_id=:comm_id and  modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where census_id=:census_id and comm_id=:comm_id and status = '1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_CHANGE_RELIGION> religion_GetData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_RELIGION where census_id=:census_id and status='1' and comm_id=:comm_id and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_RELIGION> list = (List<TB_CHANGE_RELIGION>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	public List<TB_PSG_CHANGE_OF_COMISSION> getcocData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CHANGE_OF_COMISSION where census_id=:census_id and comm_id=:comm_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CHANGE_OF_COMISSION> list = (List<TB_PSG_CHANGE_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_PSG_EXTENSION_OF_COMISSION> geteocData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_EXTENSION_OF_COMISSION where census_id=:id and comm_id=:comm_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_EXTENSION_OF_COMISSION> list = (List<TB_PSG_EXTENSION_OF_COMISSION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	public List<TB_INTER_ARM_TRANSFER> getInterArm(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where census_id=:census_id and status='1' and comm_id=:comm_id and modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}
	
	public List<TB_INTER_ARM_TRANSFER> getInterArm_old(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_INTER_ARM_TRANSFER where  status='2' and comm_id=:comm_id  order by id desc";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
		@SuppressWarnings("unchecked")List<TB_INTER_ARM_TRANSFER> list = (List<TB_INTER_ARM_TRANSFER>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	public List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where census_id=:census_id and status='1' and comm_id=:comm_id and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_LANGUAGE> update_census_getlanguagedetailsData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_LANGUAGE where cen_id=:census_id and comm_id=:comm_id and status=1 and modify_on=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_LANGUAGE> list = (List<TB_CENSUS_LANGUAGE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_QUALIFICATION> update_census_getQualificationData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_QUALIFICATION where cen_id=:cen_id and comm_id=:comm_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_QUALIFICATION> list = (List<TB_CENSUS_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Override
	public List<TB_CENSUS_SPOUSE_QUALIFICATION> getUpdatedSpouseQualificationData( BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where comm_id=:comm_id and  status = '1' and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_SECONDMENT> get_Secondment(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_SECONDMENT where census_id=:census_id and comm_id=:comm_id and status='1'  and modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_SECONDMENT> list = (List<TB_CENSUS_SECONDMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CHANGE_OF_APPOINTMENT> get_Appointment(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where census_id=:census_id and comm_id=:comm_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_FOREIGN_COUNTRY> getUPForeginCountryData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_FOREIGN_COUNTRY where cen_id=:cen_id and comm_id=:comm_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FOREIGN_COUNTRY> list = (List<TB_CENSUS_FOREIGN_COUNTRY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FIRING_STANDARD> getfire_detailsData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from TB_CENSUS_FIRING_STANDARD where census_id=:census_id and comm_id=:comm_id and status = '1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FIRING_STANDARD> list = (List<TB_CENSUS_FIRING_STANDARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData(int census_id, BigInteger comm_id,Date modifiedDate, int event) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id and modified_date=:modified_date ";
//		
//		if(event==2) {
//			hqlUpdate+=" and status=1 and (marital_status=8 OR marital_status=13) ";
//		}
//		else if(event==4) {
//			hqlUpdate+=" and status=1 and (marital_status=8) ";
//		}
//		else {
			hqlUpdate+=" and status=1 ";
//		}
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")	
		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_NOK> nok_details_GetData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_NOK where census_id=:census_id and comm_id=:comm_id and  status = '1' and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_NOK> list = (List<TB_CENSUS_NOK>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_BPET> getbpet_Data(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = "from TB_CENSUS_BPET where census_id=:census_id and comm_id=:comm_id and status = '1'  and modified_date=:modified_date  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BPET> list = (List<TB_CENSUS_BPET>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}

	@Override
	public List<TB_CENSUS_IDENTITY_CARD> Ide_card_getData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_IDENTITY_CARD where census_id=:census_id and comm_id=:comm_id and status = '1'  and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_IDENTITY_CARD> list = (List<TB_CENSUS_IDENTITY_CARD>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_ADDRESS> address_details_GetData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		

		String hqlUpdate = " from TB_CENSUS_ADDRESS where cen_id=:census_id and comm_id=:comm_id and  status = '1'  and modified_date=:modified_date";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;
	}

	@Override
	public List<TB_CENSUS_BATT_PHY_CASUALITY> Battle_and_Physical_Casuality_GetData(int census_id, BigInteger comm_id,Date modifiedDate) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_BATT_PHY_CASUALITY where census_id=:census_id and comm_id=:comm_id and status=1  and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_BATT_PHY_CASUALITY> list = (List<TB_CENSUS_BATT_PHY_CASUALITY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	@Override
	public List<TB_CENSUS_DISCIPLINE> get_Discipline(int census_id, BigInteger comm_id,Date modifiedDate) {
	

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_DISCIPLINE where census_id=:census_id and comm_id=:comm_id and status='1'  and modified_date=:modified_date order by id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_DISCIPLINE> list = (List<TB_CENSUS_DISCIPLINE>) query.list();
		tx.commit();
		sessionHQL.close();
		 
		return list;
	}

	@Override
	public List<TB_CENSUS_PROMOTIONAL_EXAM> update_census_promo_examData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_PROMOTIONAL_EXAM where census_id=:cen_id and comm_id=:comm_id and status='1'  and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_PROMOTIONAL_EXAM> list = (List<TB_CENSUS_PROMOTIONAL_EXAM>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_ARMY_COURSE> update_census_army_courseData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_ARMY_COURSE where census_id=:cen_id and comm_id=:comm_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_ARMY_COURSE> list = (List<TB_CENSUS_ARMY_COURSE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> update_census_allergicData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_CENSUS_ALLERGICTOMEDICINE where cen_id=:cen_id and comm_id=:comm_id and status='1' and modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_CENSUS_ALLERGICTOMEDICINE> list = (List<TB_PSG_CENSUS_ALLERGICTOMEDICINE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	@Override
	public List<TB_CENSUS_MEDICAL_CATEGORY> getUpdatedmadicalData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_MEDICAL_CATEGORY where cen_id=:cen_id and comm_id=:comm_id and status='1' and modify_on=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_MEDICAL_CATEGORY> list = (List<TB_CENSUS_MEDICAL_CATEGORY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	

	public List<TB_DESERTER> get_Deserter(int census_id, BigInteger comm_id,Date modifiedDate) {
			
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_DESERTER where  comm_id=:comm_id and status='1' and approved_date=:modified_date";
			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
			@SuppressWarnings("unchecked")
			List<TB_DESERTER> list = (List<TB_DESERTER>) query.list();
			tx.commit();
			sessionHQL.close();
			 
			return list;
		}


    public List<TB_PSG_CANTEEN_CARD_DETAIL1> getCSD_detailsData(int census_id, BigInteger comm_id,Date modifiedDate) {//kevaldao
        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
        //String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where pr_no=:personnel_no  and  modified_date=:modified_date order by id";
        String hqlUpdate = " from TB_PSG_CANTEEN_CARD_DETAIL1 where  census_id=:census_id and comm_id=:comm_id and status='1' and modified_date=:modified_date  order by id";
        Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
//                        .setTimestamp("modified_date", modifiedDate);
        @SuppressWarnings("unchecked")
        List<TB_PSG_CANTEEN_CARD_DETAIL1> list = (List<TB_PSG_CANTEEN_CARD_DETAIL1>) query.list();
        tx.commit();
        sessionHQL.close();
        return list;
}
    
    
    public List<Map<String, Object>> Regimental_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo)  {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(!comm_id.equals("")) {
				qry += " cast(op.comm_id  as character varying)=? ";
			}
	      
	         q="select orb.unit_name,appt.rank,appt.appt,appt.dt as dt,l.code_value as place,l1.label as unit_loc_type,fv.unit_name as command\r\n" + 
			 		"from (select distinct op.to_sus_no as sus_no,'' as appt,case when d.label != '' then concat( ra1.description ,'(',d.label,')') else ra1.description end as rank, TO_CHAR(rk_main.date_of_rank,'DD-MON-YYYY') as dt,rk_main.date_of_rank from \r\n" + 
			 		"tb_psg_trans_proposed_comm_letter c \r\n" + 
			 		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1\r\n" + 
			 		"inner join\r\n" + 
			 		"tb_psg_change_of_rank rk_main on c.id=rk_main.comm_id and rk_main.status != '-1'\r\n" + 
			 		"inner join cue_tb_psg_rank_app_master ra1 on  ra1.id = rk_main.rank and upper(ra1.level_in_hierarchy) = 'RANK' \r\n" + 
			 		"and --op.dt_of_tos <= rk_main.date_of_rank  \r\n" + 
			 		"rk_main.date_of_rank  >= case when op.from_sus_no is null then c.date_of_commission \r\n" + 
			 		"else op.dt_of_tos end\r\n" + 
			 		"AND (op.tenure_date is null OR op.tenure_date >=  rk_main.date_of_rank)\r\n" + 
			 		"left join t_domain_value d on rk_main.rank_type::character varying = d.codevalue and d.domainid='OFFR_RANK_TYPE'\r\n" + 
			 		"where\r\n" + qry +
			 		" group by 1,2,3,4,5 \r\n" + 
			 		"UNION ALL\r\n" + 
			 		"select distinct op.to_sus_no as sus_no,ra2.description as appt,'' as rank , TO_CHAR(app_main.date_of_appointment,'DD-MON-YYYY')  as dt,app_main.date_of_appointment as date_of_rank from \r\n" + 
			 		"tb_psg_trans_proposed_comm_letter c \r\n" + 
			 		"inner join tb_psg_posting_in_out op on c.id=op.comm_id and c.status not in ('0','3')  and op.status=1\r\n" + 
			 		"inner join\r\n" + 
			 		"tb_psg_change_of_appointment app_main on c.id=app_main.comm_id and app_main.status !='-1' \r\n" + 
			 		"inner join cue_tb_psg_rank_app_master ra2 on  ra2.id = app_main.appointment and upper(ra2.level_in_hierarchy) = 'APPOINTMENT'\r\n" + 
			 		"and --op.dt_of_tos <= rk_main.date_of_rank  \r\n" + 
			 		"app_main.date_of_appointment  >= case when op.from_sus_no is null then c.date_of_commission \r\n" + 
			 		"else op.dt_of_tos end\r\n" + 
			 		"AND (op.tenure_date is null OR op.tenure_date >=  app_main.date_of_appointment)\r\n" + 
			 		"where\r\n" + qry +
			 		"  group by 1,2,3,4,5\r\n" + 
			 		"order by date_of_rank) appt\r\n" + 
			 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = appt.sus_no  and orb.status_sus_no='Active' \r\n" + 
			 		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
			 		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
			 		"inner join all_fmn_view fv  on orb.sus_no = appt.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
			 		"left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = appt.sus_no and orb1.status_sus_no='Active'  order by date_of_rank";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
//		    if (!roleSusNo.equals("")) {
//				stmt.setString(j, roleSusNo);
//				j += 1;
//			}

			
			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
				
			} 

		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			    int sum=0;
			    String c_appt = "";
			    String c_rank="";
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	
		 	    	if(i == 2)
		 	    	{
		 	    
			 	    	if(rs.getObject(i) !=null && !rs.getObject(i).equals(""))
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
			 	    		c_appt =String.valueOf( rs.getObject(i)).toUpperCase(); 
			 	    	}
			 	    	else
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), c_appt);
			 	    	}
		 			
		 	    	}
		 	    	if(i == 3)
		 	    	{
		 	    
		 	    		if(rs.getObject(i) !=null && !rs.getObject(i).equals(""))
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
			 	    		c_rank =String.valueOf( rs.getObject(i)).toUpperCase(); 
			 	    	}
			 	    	else
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), c_rank);
			 	    	}
		 			
		 	    	}
		 	    	if(i != 2 && i !=3)
		 	    	{

		 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    	}
		 	    }
		 	  
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
    
	public List<Map<String, Object>> CHILD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			if(!comm_id.equals("")) {
				qry += " and cast(c.comm_id  as character varying)=? ";
			}
	        q="SELECT distinct a.name,a.dob,a.gender from (SELECT \r\n" + 
	        		"COALESCE(name ,'') as name,\r\n" + 
	        		"COALESCE(TO_CHAR(date_of_birth,'DD-MON-YYYY'),'') as dob,\r\n" + 
	        		"d.label as gender  \r\n" + 
	        		"FROM tb_psg_census_children c \r\n" + 
	        		"inner join t_domain_value d on cast(c.relationship as character varying)=d.codevalue \r\n" + 
	        		"            and  d.domainid='CHILD_RELATIONSHIP' where c.status = 1 " +qry + "ORDER BY c.id) a";
	        		
		    stmt=conn.prepareStatement(q);   
		    int j =1;
			
		  

			if( !comm_id.equals("")) {
				stmt.setString(j,String.valueOf(comm_id));
				j += 1;	
			} 
		    
			
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	public ArrayList<ArrayList<String>> GetDataApprove_noneffective(BigInteger comm_id)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	
	try{	  
	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
				
			
			q = "select  distinct\r\n" + 
					"c.id,\r\n" + 
					"cd.id as census_id,\r\n" + 
					"cd.marital_status as marital_status,\r\n" + 
					"c.name,\r\n" + 
					"cd.father_name,\r\n" +
					"cd.father_dob,\r\n" +
					"cd.mother_name,\r\n" +
					"cd.mother_dob,\r\n" +
					"ra.description as rank,\r\n" + 
					"r.description as appt,\r\n" + 
					"a.date_of_appointment as date_appointment,\r\n" + 
					"(select post.dt_of_tos  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n" + 
					"c.unit_sus_no,\r\n" + 
					"i.id_card_no,\r\n" + 
					"re.religion_name,\r\n" + 
					"p.arm_desc as parent_arm,\r\n" + 
					"c.date_of_birth,\r\n" + 
					"c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" + 
					"fv.unit_name as command,c.regiment,cd.update_ofr_status\r\n" + 
					"from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"left join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n" + 
					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' \r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no  \r\n" + 
					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n" + 
					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' \r\n" + 
					"left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" + 
					"left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n" + 
					"left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" + 
					"left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 
					"where c.status in ('1','5','4') and  c.id::text=? ";


				
			
		
			stmt=conn.prepareStatement(q);
			

			stmt.setString(1,String.valueOf(comm_id));
		  
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("id"));
				list.add(rs.getString("census_id"));
				list.add(rs.getString("name"));				
				list.add(rs.getString("rank"));
				list.add(rs.getString("appt"));
				list.add(rs.getString("date_appointment"));
				list.add(rs.getString("dt_of_tos"));
				list.add(rs.getString("unit_sus_no"));
				
				list.add(rs.getString("id_card_no"));
				list.add(rs.getString("religion_name"));
				list.add(rs.getString("parent_arm"));
				list.add(rs.getString("date_of_birth"));//11
				list.add(rs.getString("date_of_commission"));
				list.add(rs.getString("marital_status"));//13
				list.add(rs.getString("rank_id"));//14
				list.add(rs.getString("appoint_id"));//15
				list.add(rs.getString("command"));//16
				list.add(rs.getString("regiment"));//17
				list.add(rs.getString("update_ofr_status"));//18
				list.add(rs.getString("father_name"));//19
				list.add(rs.getString("father_dob"));//20
				list.add(rs.getString("mother_name"));//21
				list.add(rs.getString("mother_dob"));//22
				
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

	
	public List<Map<String, Object>> RD_View_UpadteByid(int id,BigInteger comm_id,String roleSusNo,int comm_status) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        String q="";
        String qry="";
        String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
//			if(comm_status == 1  || comm_status == 4) {
//				comm_stats = "(?,?) ";
//			}
//		           
                if(!roleSusNo.equals("")) {
                        qry+=" and pcl.unit_sus_no = ?";
                }
                if(!comm_id.equals("")) {
                        qry += " and cast(ne.comm_id  as character varying)=? ";
                }
                q="select a.causes_name ,a.date_of_non_effective, COALESCE(b.date_of_non_effective,'') as date_of_release,COALESCE(b.causes_name,'') as cause_of_release from (select mne.causes_name,ne.comm_id,\r\n" + 
                		"COALESCE((TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY')),'') as  date_of_non_effective\r\n" + 
                		"		        		from tb_psg_trans_proposed_comm_letter pcl\r\n" + 
                		"		        		inner join tb_psg_non_effective ne on ne.comm_id=pcl.id and ne.status in ('1','2')\r\n" + 
                		"		        		inner join tb_psg_mstr_cause_of_non_effective mne on ne.cause_of_non_effective=mne.id::text and  mne.type_of_officer=1        		\r\n" + 
                		"						where pcl.status in ('1','4')  "+qry+" order by ne.id limit 1) a\r\n" + 
                		"left join 			\r\n" + 
                		"(select mne.causes_name,ne.comm_id,\r\n" + 
                		"COALESCE((TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY')),'') as  date_of_non_effective\r\n" + 
                		"		        		from tb_psg_trans_proposed_comm_letter pcl\r\n" + 
                		"		        		inner join tb_psg_non_effective ne on ne.comm_id=pcl.id and ne.status=1\r\n" + 
                		"		        		inner join tb_psg_mstr_cause_of_non_effective mne on ne.cause_of_non_effective=mne.id::text and  mne.type_of_officer=2    \r\n" + 
                		" 						inner join tb_psg_re_employment ren on ne.comm_id=ren.comm_id and ren.status=1\r\n" + 
                		"						where pcl.status in ('1','4') "+qry+"  order by ne.id desc limit 1	)	b on a.comm_id=b.comm_id" ;
                        
            stmt=conn.prepareStatement(q);           
            int j =1;
//            if(comm_status == 1  || comm_status == 4) {
//			 	stmt.setString(j, "1");
//				j += 1;
//			 	stmt.setString(j, "4");
//				j += 1;
//			}
            if (!roleSusNo.equals("")) {
                        stmt.setString(j, roleSusNo);
                        j += 1;
                }

                
                if( !comm_id.equals("")) {
                        stmt.setString(j,String.valueOf(comm_id));
                        j += 1;        
                } 
                if (!roleSusNo.equals("")) {
                    stmt.setString(j, roleSusNo);
                    j += 1;
            }

            
            if( !comm_id.equals("")) {
                    stmt.setString(j,String.valueOf(comm_id));
                    j += 1;        
            } 
                    
            ResultSet rs = stmt.executeQuery();    
          
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                     
                while (rs.next()) {
                        Map<String, Object> columns = new LinkedHashMap<String, Object>();
                     for (int i = 1; i <= columnCount; i++) {
                    	 if(rs.getObject(i)==null)
     						columns.put(metaData.getColumnLabel(i), "");
     					else
     						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
                     }
                     list.add(columns);
                  }
                      
                 rs.close();
                 stmt.close();
                 conn.close();
                 
	     }catch (SQLException e){
	             e.printStackTrace();
	         }finally{
	                 if(conn != null){
	                         try{
	                                 conn.close();
	                         }catch (SQLException e){
	                         }
	                 }
	         }
         return list;
       }
	public List<TB_CHANGE_TA_STATUS> getChangeOfTAData(int census_id, BigInteger comm_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_TA_STATUS where census_id=:census_id and status='1' and comm_id=:comm_id and  modified_date=:modified_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", census_id).setBigInteger("comm_id", comm_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_TA_STATUS> list = (List<TB_CHANGE_TA_STATUS>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}


}
