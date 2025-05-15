package com.dao.psg.Jco_Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Jco_Transaction.TB_PSG_CERTIFY_BC_PC_JCO;
import com.persistance.util.HibernateUtil;

public class Certify_BCPC_DAOImpl implements Certify_BCPC_DAO{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_CertifyBCPC(String personnel_no,
			String status, String unit_sus_no, String unit_name,String rank,String cr_by,String cr_date,
			String roleSusNo,
			String roleType)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String qry="";

	try{	

			conn = dataSource.getConnection();
			PreparedStatement stmt=null;

			if (!status.equals("")) {
				qry += " sd.status = cast(? as integer)  ";
			}
			if (!roleSusNo.equals("")) {
				qry += " and pro.unit_sus_no = ?";
			}
			if(!personnel_no.equals("")) {
				qry += "  and upper(pro.army_no) like ? ";
			}

			if(!rank.equals("") ) {
			qry += "  and pro.rank = cast(? as integer) ";
			}
			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}

			if (!cr_date.equals("")) {
				qry += " and cast(sd.created_date as date) = cast(? as date)";
			}
	


				q="select distinct\r\n" + 
						"						sd.id,sd.jco_id,r.rank,\r\n" + 
						"						pro.army_no,\r\n" + 
						"						to_char(sd.date_of_casualty,'DD-MON-YYYY')as date_of_casualty,\r\n" + 
						"						sd.authority,sd.reject_remarks,\r\n" + 
						"						to_char(sd.date_of_authority,'DD-MON-YYYY')as date_of_authority\r\n" + 
						"						from tb_psg_certify_bc_pc_jco sd\r\n" + 
						"						left join logininformation l1 on l1.username =sd.created_by\r\n" + 
						"						inner join tb_psg_census_jco_or_p pro on sd.jco_id=pro.id\r\n" + 
						"						inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pro.unit_sus_no \r\n" + 
						"						inner join tb_psg_mstr_rank_jco r on r.id = pro.rank\r\n" + 
						"						where "+ qry +"order by sd.id desc" ;

			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{ 
					int j =1;
					
					if (!status.equals("")) {
						stmt.setString(j, status);
						j += 1;
					}
					if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
		
					if (!personnel_no.equals("")) {
						stmt.setString(j, personnel_no.toUpperCase() + "%");
						j += 1;
					}
					if(!rank.equals("")) {
						stmt.setString(j, rank);
						j += 1;
					}
					if (!cr_by.equals("")) {
						stmt.setString(j, cr_by);
						j += 1;

					}
					if (!cr_date.equals("")) {
							stmt.setString(j, cr_date);
							j += 1;

						}
		

			}
			
			 ResultSet rs = stmt.executeQuery();   
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("army_no"));
				list.add(rs.getString("rank"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				list.add(rs.getString("date_of_casualty"));
				//list.add(rs.getString("reject_remarks"));

				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";
				String f5 = "";

				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("jco_id")+ ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
					

					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";					
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("1"))
				{
					String downloadData = "onclick=\" downloaddata('"+ rs.getString("jco_id") +"');\"";
					f5 = "<i class='action_icons action_download'  " + downloadData + " title='Download Data'></i>";
				}
				
				
				if(roleType.equals("ALL") || roleType.equals("DEO") && status.equals("1"))
				{
					String downloadData = "onclick=\" downloaddata('"+ rs.getString("jco_id") +"');\"";
					f5 = "<i class='action_icons action_download'  " + downloadData + " title='Download Data'></i>";
				}
				
				if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{

					String editData = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("
							+ rs.getString("id") + ","+rs.getString("jco_id")+")}else{ return false;}\"";
					f4 = "<i class='action_icons action_update'  " + editData + " title='Edit Data'></i>";
					
					String downloadData = "onclick=\" downloaddata('"+ rs.getString("jco_id") +"');\"";
					f5 = "<i class='action_icons action_download'  " + downloadData + " title='Download Data'></i>";

				}

				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(f4);
				list.add(f5);
				list.add(rs.getString("reject_remarks"));
				alist.add(list);
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
		return alist;

	}
	
	public TB_PSG_CERTIFY_BC_PC_JCO getCertifyBCPC_Byid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_PSG_CERTIFY_BC_PC_JCO updateid = (TB_PSG_CERTIFY_BC_PC_JCO) sessionHQL.get(TB_PSG_CERTIFY_BC_PC_JCO.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	
	
	public ArrayList<ArrayList<String>> pdf_Certify_BCPC(String army_no)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
				
			q="select  \r\n" + 
					"					pc.jco_id,\r\n" + 
					"					col.army_no, \r\n" + 
					"					col.full_name,\r\n" + 
					"					ra.rank,\r\n" + 
					"					udp.unit_name as present_unit,\r\n" + 
					"					ud.unit_name as parent_unit,\r\n" + 
					"					p.arm_desc as parent_arm, \r\n" + 
					"					reli.religion_name,\r\n" + 
					"					TO_CHAR(pc.date_of_casuality ,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
					"					pc.time_of_casuality,\r\n" + 
					"					tdc.label as classification_of_casuality,\r\n" + 
					"					td.label as nature_of_casuality,\r\n" + 
					"					pc.onduty,\r\n" + 
					"					case when con.name = 'OTHERS' then pc.country_other \r\n" + 
					"					else con.name end as country ,\r\n" + 
					"					case when st.state_name = 'OTHERS' then pc.state_other\r\n" + 
					"					else st.state_name end as state ,\r\n" + 
					"					case when dis.district_name = 'OTHERS' then pc.district_other \r\n" + 
					"					else dis.district_name end as district , \r\n" + 
					"					case when te.city_name = 'OTHERS' then pc.tehsil_other  \r\n" + 
					"					else te.city_name end as tehsil ,\r\n" + 
					"					pc.village,\r\n" + 
					"					pc.name_of_operation, \r\n" + 
					"					pc.sector,\r\n" + 
					"					pc.field_services,\r\n" + 
					"					bd.bde_name, \r\n" + 
					"					di.div_name,\r\n" + 
					"					co.coprs_name,\r\n" + 
					"					cm.cmd_name,\r\n" + 
					"					pc.hospital_name,\r\n" + 
					"					pc.hospital_location, \r\n" + 
					"					pc.cause_of_casuality,\r\n" + 
					"					pc.circumstances, \r\n" + 
					"					pc.diagnosis, \r\n" + 
					"					pc.aid_to_civ, \r\n" + 
					"					cno.nok_name, \r\n" + 
					"					rel.relation_name,\r\n" + 
					"					nst.state_name as nok_state, \r\n" + 
					"					ndis.district_name as nok_district,\r\n" + 
					"					cno.nok_pin, \r\n" + 
					"					PGP_SYM_DECRYPT(cno.nok_mobile_no ::bytea,current_setting('miso.version')) as nok_mobile,pc.whether_on \r\n" + 
					"					from tb_psg_census_battle_physical_casuality_jco pc \r\n" + 
					"					inner join  orbat_view_cmd cm on cm.sus_no=pc.command   \r\n" + 
					"					inner join orbat_view_corps co on co.sus_no=pc.corps_area \r\n" + 
					"					inner join orbat_view_div di on di.sus_no=pc.div_subarea \r\n" + 
					"					inner join orbat_view_bde bd on bd.sus_no=pc.bde\r\n" + 
					"					inner join t_domain_value td on td.codevalue=pc.nature_of_casuality  and td.domainid='NATURE_OF_CASUALITY' \r\n" + 
					"					inner join tb_psg_mstr_country con on con.id=cast(pc.country as integer)\r\n" + 
					"					inner join tb_psg_mstr_state st on st.state_id=cast(pc.state as integer) \r\n" + 
					"					inner join tb_psg_mstr_district dis on dis.district_id=cast(pc.district as integer) \r\n" + 
					"					inner join tb_psg_mstr_city te on te.city_id=cast(pc.tehsil as integer)\r\n" + 
					"					inner join tb_psg_census_jco_or_p col on col.id=pc.jco_id   \r\n" + 
					"					inner join tb_psg_posting_in_out_jco poi on poi.jco_id=pc.jco_id and poi.to_sus_no = col.unit_sus_no and col.status = '1'\r\n" + 
					"					inner join tb_miso_orbat_unt_dtl ud on ud.sus_no=poi.to_sus_no and ud.status_sus_no='Active'\r\n" + 
					"					inner join tb_miso_orbat_unt_dtl udp on udp.sus_no=col.unit_sus_no and udp.status_sus_no='Active'\r\n" + 
					"					inner join tb_psg_change_of_rank_jco cr on cr.jco_id = col.id and cr.status = 1\r\n" + 
					"					inner join tb_psg_mstr_rank_jco ra on ra.id = col.rank  \r\n" + 
					"					left join tb_psg_census_nok_jco cno on cno.jco_id=pc.jco_id and cno.status=1 \r\n" + 
					"					left join tb_psg_mstr_relation rel on rel.id=cno.nok_relation  \r\n" + 
					"					left join tb_psg_mstr_state nst on nst.state_id=cast(cno.nok_state as integer)\r\n" + 
					"					left join tb_psg_mstr_district ndis on ndis.district_id=cast(cno.nok_district as integer)\r\n" + 
					"					inner join tb_psg_mstr_religion reli on reli.religion_id = col.religion\r\n" + 
					"					inner join tb_miso_orbat_arm_code p on p.arm_code = col.arm_service\r\n" + 
					"					inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" + 
					"					where col.army_no= ?" ;
				stmt=conn.prepareStatement(q);
					
				stmt.setString(1, army_no);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("army_no"));//0
					list.add(rs.getString("full_name"));//1
					list.add(rs.getString("rank"));//2
					list.add(rs.getString("present_unit"));//4
					list.add(rs.getString("parent_unit"));//5
					list.add(rs.getString("parent_arm"));//6
					list.add(rs.getString("religion_name"));//7
					list.add(rs.getString("date_of_casuality"));//8
					list.add(rs.getString("time_of_casuality"));//9
					list.add(rs.getString("classification_of_casuality"));//10
					list.add(rs.getString("nature_of_casuality"));//11
					list.add(rs.getString("onduty"));//12
					list.add(rs.getString("country"));//13
					list.add(rs.getString("state"));//14
					list.add(rs.getString("district"));//15
					list.add(rs.getString("tehsil"));//16
					list.add(rs.getString("village"));//17
					list.add(rs.getString("name_of_operation"));//18
					list.add(rs.getString("sector"));//19
					list.add(rs.getString("field_services"));//20
					list.add(rs.getString("bde_name"));//21
					list.add(rs.getString("div_name"));//22
					list.add(rs.getString("coprs_name"));//23
					list.add(rs.getString("cmd_name"));//24
					list.add(rs.getString("hospital_name"));//25
					list.add(rs.getString("hospital_location"));//26
					list.add(rs.getString("cause_of_casuality"));//27
					list.add(rs.getString("circumstances"));//28
					list.add(rs.getString("diagnosis"));//29
					list.add(rs.getString("aid_to_civ"));//30
					list.add(rs.getString("nok_name"));//31
					list.add(rs.getString("relation_name"));//32
					list.add(rs.getString("nok_state"));//33
					list.add(rs.getString("nok_district"));//34
					list.add(rs.getString("nok_pin"));//35
					list.add(rs.getString("nok_mobile"));//36
					list.add(rs.getString("whether_on"));//37
					
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
	
}
