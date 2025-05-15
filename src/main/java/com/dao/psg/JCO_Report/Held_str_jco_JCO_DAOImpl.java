package com.dao.psg.JCO_Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Held_str_jco_JCO_DAOImpl implements  Held_str_jco_JCO_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	 public ArrayList<ArrayList<String>> Search_Report_auth(String unit_sus_no,String unit_name,String month_year,HttpSession session)
	 {
	 		String roleAccess = session.getAttribute("roleAccess").toString();
	 		String roleSusNo = session.getAttribute("roleSusNo").toString();
	 		
	 		
	 	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	 	Connection conn = null;
	 	String q="";
	 		try{	  
	 			conn = dataSource.getConnection();			 
	 			PreparedStatement stmt=null;
	 		
	 			q="  select  count(*) filter (where parent_code = '1') as JCOs,\r\n" + 
	 					"  count(*) filter (where parent_code = '2') as JCOs_OR,\r\n" + 
	 					"  count(*) filter (where parent_code = '3') as OR,\r\n" + 
	 					"  coalesce(count(*) filter (where parent_code = '1') + \r\n" + 
	 					"           count(*) filter (where parent_code = '2') +\r\n" + 
	 					"		   count(*) filter (where parent_code = '3')) as Total\r\n" + 
	 					"  from cue_tb_psg_rank_app_master where upper(level_in_hierarchy) = upper('Rank') ";
	 		          stmt=conn.prepareStatement(q);	
	 				 /* stmt.setString(1,unit_sus_no);
	 				  stmt.setString(2,unit_name);
	 				  stmt.setString(3,month_year);*/
	 		   int i = 0;
	 	      ResultSet rs = stmt.executeQuery();   	      
	 	      while (rs.next()) {
	 	    	  i++;
	 	    	  ArrayList<String> list = new ArrayList<String>();
	 	    	  
	 	    	    list.add(rs.getString("JCOs"));//0
	 				list.add(rs.getString("JCOs_OR"));//1
	 				list.add(rs.getString("OR"));//2
	 				list.add(rs.getString("Total"));//3
	 				
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

///////////////////////////////////////B
	 public ArrayList<ArrayList<String>> Search_Report_held_str(String unit_sus_no,String unit_name,String FDate,HttpSession session)
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
	 		
	 			
	 			
				
				 if(!FDate.equals("") && !FDate.equals("")){
						 qry += " and to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')";
				  }
				 if(!FDate.equals("") && !FDate.equals("")){
					 qry += " and (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD'))";
			  }
				
				 if( !unit_sus_no.equals("")) {
						qry += " and upper(op.to_sus_no) like ? ";		
					}
				 //26-01-1994
				 /*
					 * q="select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos,\r\n"
					 * +
					 * "hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total\r\n"
					 * +
					 * "from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT',\r\n"
					 * +
					 * "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total, \r\n"
					 * + "count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm,\r\n" +
					 * "count(*) filter (where r.rank = 'WARRANT OFFICER') as sub,\r\n" +
					 * "count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" +
					 * "count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" +
					 * "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" +
					 * "count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep\r\n" +
					 * "from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n"
					 * + "inner join tb_psg_posting_in_out_jco op on c.id = op.jco_id " +qry+
					 * "where c.status not in ('0','3')  and op.status=1\r\n" +
					 * " group by c.rank,to_sus_no )hld";
					 */
			 			q="select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos,\r\n" + 
			 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total\r\n" + 
			 					"from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT',\r\n" + 
			 					"'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total, \r\n" + 
			 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm,\r\n" + 
			 					"count(*) filter (where r.rank in ('WARRANT OFFICER','SUB / EQUIVALENT')) as sub,\r\n" + 
			 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" + 
			 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" + 
			 					"count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" + 
			 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep\r\n" + 
			 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
			 					"inner join tb_psg_posting_in_out_jco op on c.id = op.jco_id " +qry+ 
			 					"where c.status not in ('0','3')  and op.status=1\r\n" + 
			 					" and op.dt_of_tos not in (select date_of_tos from tb_psg_re_call_jco  order by date_of_tos limit 1) group by c.rank,to_sus_no )hld";
	 		          stmt=conn.prepareStatement(q);	
	 				  stmt.setString(1,FDate);
	 				  stmt.setString(2,FDate);
	 				  stmt.setString(3,unit_sus_no);
	 		   int i = 0;
	 	      ResultSet rs = stmt.executeQuery();   	      
	 	      while (rs.next()) {
	 	    	  i++;
	 	    	  ArrayList<String> list = new ArrayList<String>();
	 	    	  
	 	    	    list.add(rs.getString("sm"));//0
	 				list.add(rs.getString("sub"));//1
	 				list.add(rs.getString("nb_sub"));//2
	 				list.add(rs.getString("total_jcos"));//3
	 				list.add(rs.getString("hav"));//4
	 				list.add(rs.getString("nk"));//5
	 				list.add(rs.getString("sep"));//6
	 				list.add(rs.getString("total_or"));//7
	 				list.add(rs.getString("total"));//8
	 				
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
	 
	 
	 
	 
	 ///////////////////////////////C////////////////
	 
	 public ArrayList<ArrayList<String>> Search_Report_Deserter(String unit_sus_no,String unit_name,String FDate,HttpSession session)
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
	 			 if(!FDate.equals("") && !FDate.equals("")){
					 qry += "and to_date(to_char(ds1.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')";
			  }
			 if(!FDate.equals("") && !FDate.equals("")){
				 qry += " and (ds1.dt_recovered is null OR to_date(to_char(ds1.dt_recovered,'Mon YYYY'),'Mon YYYY') > to_date(?,'YYYY/MM/DD'))";
		  }
			 
			 
			 if( !unit_sus_no.equals("")) {
					qry += " and upper(c.unit_sus_no) like ? ";		
				}
	 			q="select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
	 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
	 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
	 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
	 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
	 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
	 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
	 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
	 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
	 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
	 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
	 					"inner join tb_psg_deserter_jco ds1 on  c.id = ds1.jco_id  " + qry +
	 					" where c.status not in ('0','3')  and ds1.status=1)hld";
	 			 stmt=conn.prepareStatement(q);	
				  stmt.setString(1,FDate);
				  stmt.setString(2,FDate);
				  stmt.setString(3,unit_sus_no);
	 		   int i = 0;
	 	      ResultSet rs = stmt.executeQuery();   	      
	 	      while (rs.next()) {
	 	    	  i++;
	 	    	  ArrayList<String> list = new ArrayList<String>();
	 	    	  
	 	    	 list.add(rs.getString("sm"));//0
	 				list.add(rs.getString("sub"));//1
	 				list.add(rs.getString("nb_sub"));//2
	 				list.add(rs.getString("total_jcos"));//3
	 				list.add(rs.getString("hav"));//4
	 				list.add(rs.getString("nk"));//5
	 				list.add(rs.getString("sep"));//6
	 				list.add(rs.getString("total_or"));//7
	 				list.add(rs.getString("total"));//8
	 				
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
 ///////////////////////////////DDDDDDDDDDDDDDDDDDDDDDDD////////////////
	 
	 public ArrayList<ArrayList<String>> Search_Report_attachment(String unit_sus_no,String unit_name,String FDate,HttpSession session)
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
 			 if(!FDate.equals("") && !FDate.equals("")){
				 qry += " where (to_char(at1.att_from,'Mon YYYY'),'Mon YYYY') <= (?,'YYYY/MM/DD') ";
		  }
		 if(!FDate.equals("") && !FDate.equals("")){
			 qry += " and (to_char(at1.att_to,'Mon YYYY'),'Mon YYYY') >= (?,'YYYY/MM/DD')";
	  }
		 
		
		 if( !unit_sus_no.equals("")) {
				qry += " and upper(at1.att_sus_no) like ? ";		
			}
 			q="select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
 					"inner join tb_psg_census_attachment_details_jco at1 on   c.id = at1.jco_id " + qry +
 					" and  at1.status=1 and c.status not in ('0','3'))hld";
 			 stmt=conn.prepareStatement(q);	
			  stmt.setString(1,FDate);
			  stmt.setString(2,FDate);
			  stmt.setString(3,unit_sus_no);
 		   int i = 0;
 	      ResultSet rs = stmt.executeQuery();   	      
 	      while (rs.next()) {
 	    	  i++;
 	    	  ArrayList<String> list = new ArrayList<String>();
 	    	  
 	    	 list.add(rs.getString("sm"));//0
 				list.add(rs.getString("sub"));//1
 				list.add(rs.getString("nb_sub"));//2
 				list.add(rs.getString("total_jcos"));//3
 				list.add(rs.getString("hav"));//4
 				list.add(rs.getString("nk"));//5
 				list.add(rs.getString("sep"));//6
 				list.add(rs.getString("total_or"));//7
 				list.add(rs.getString("total"));//8
 				
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
	 
	 
	 public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String unit_name,String month_year,HttpSession session)
	 {
	 		String roleAccess = session.getAttribute("roleAccess").toString();
	 		String roleSusNo = session.getAttribute("roleSusNo").toString();	
	 	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	 	Connection conn = null;
	 	String q="";
	 	
	 		try{	  
	 			conn = dataSource.getConnection();			 
	 			PreparedStatement stmt=null;
	 		
	 			//q="  select rank,full_name,army_no,trade,regiment from tb_psg_census_jco_or_p where unit_sus_no= '"+unit_sus_no+"' ";
	 			q="select distinct on (c.id ) c.id,COALESCE(c.army_no,'') as army_no ,"; 
	 			q +=" COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name,"; 
	 			q +=" COALESCE(r.rank,'') as rank,"; 
	 			q +=" COALESCE(arm.arm_desc,'') as regiment,"; 
	 			q +=" COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,"; 
	 			q +=" COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority,"; 
	 			q +=" COALESCE(a.appointment,'') as appointment,"; 
	 			q +=" COALESCE(m.unit_name,'') as unit_name ,"; 
	 			q +=" COALESCE(ltrim(TO_CHAR(np.dt_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos,"; 
	 			q +=" COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth,"; 
	 			q +=" COALESCE(bd.blood_desc,'') as blood_desc,"; 
	 			q +=" COALESCE(ic.id_card_no,'') as id_card_no,"; 
	 			q +=" COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') as mobile_no,"; 
	 			q +=" n7.shape,n7.cope,n7.date_of_authority, st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') as date_of_attestation"; 
	 			q +=" from tb_psg_census_jco_or_p c  "; 
	 			q +=" left join (select distinct g.jco_id,rank,date_of_rank from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco where status='1' group by jco_id) g1 on g1.jco_id=g.jco_id) cr on cr.jco_id= c.id  "; 
	 			q +=" left join (select dt_of_sos,dt_of_tos,from_sus_no,h.jco_id,location,status,to_sus_no from tb_psg_posting_in_out_jco h left join (select jco_id,max(id) from tb_psg_posting_in_out_jco group by jco_id) h1 on h1.jco_id=h.jco_id) np on np.jco_id=c.id"; 
	 			q +=" left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active'"; 
	 			q +=" left join tb_psg_update_census_address_of_birth_jco n3 on n3.jco_id=c.id and n3.status='1'"; 
	 			q +=" left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id "; 
	 			q +=" left join tb_psg_mstr_appt_jco a on a.id = ca.appointment and a.status = 'active'"; 
	 			q +=" left join tb_miso_orbat_unt_dtl m on m.sus_no = c.unit_sus_no and m.status_sus_no='Active'"; 
	 			q +=" inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service"; 
	 			q +=" left join tb_psg_mstr_blood bd on bd.id=c.blood_group and bd.status='active'"; 
	 			q +=" left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1 "; 
	 			q +=" left join tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id"; 
	 			q +=" left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char(m.date_of_authority,'dd MON yyyy') as date_of_authority"; 
	 			q +=" from tb_psg_census_jco_or_p pcl "; 
	 			q +=" left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5)) n7 on n7.id=c.id"; 
	 			q +=" left join tb_psg_mstr_trade_jco st on st.id=c.trade";
	 			q +=" where  c.status in  (1,5)  and c.unit_sus_no = '"+unit_sus_no+"'";
	 		          stmt=conn.prepareStatement(q);	
	 				 /* stmt.setString(1,unit_sus_no);
	 				  stmt.setString(2,unit_name);
	 				  stmt.setString(3,month_year);*/
	 		   int i = 0;
	 	   
	 	      ResultSet rs = stmt.executeQuery();   	      
	 	      while (rs.next()) {
	 	    	  i++;
	 	    	  ArrayList<String> list = new ArrayList<String>();
	 	    	  
	 	    	    list.add(rs.getString("army_no"));//0
	 				list.add(rs.getString("full_name"));//1
	 				list.add(rs.getString("rank"));//2
	 				list.add(rs.getString("regiment"));//3
	 				list.add(rs.getString("commission_date"));//4
	 				list.add(rs.getString("date_of_seniority"));//5
	 				list.add(rs.getString("appointment"));//6
	 				list.add(rs.getString("unit_name"));//7
	 				list.add(rs.getString("date_of_tos"));//8
	 				list.add(rs.getString("date_of_birth"));//9
	 				list.add(rs.getString("blood_desc"));//10
	 				list.add(rs.getString("id_card_no"));//11
	 				list.add(rs.getString("mobile_no"));//12
	 				list.add(rs.getString("shape"));//13
	 				list.add(rs.getString("cope"));//14
	 				list.add(rs.getString("date_of_authority"));//15
	 				list.add(rs.getString("trade"));//16
	 				list.add(rs.getString("date_of_attestation"));//17
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
	 
	 //bisag v1 180822 (formation wise get data)
	 public ArrayList<ArrayList<String>> GetdaoSearch_Report_jco_holding1111( String month, String year, String hd_cmd_sus,
				String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String unit_name,HttpSession session)
			 
			// public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String unit_name,String month_year,HttpSession session)
			 {
			 		String roleAccess = session.getAttribute("roleAccess").toString();
			 		String roleSusNo = session.getAttribute("roleSusNo").toString();	
			 	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
			 	Connection conn = null;
			 	String q="";
			 	
			 	String qry = "";
		
			 		try{	  
			 			conn = dataSource.getConnection();			 
			 			PreparedStatement stmt=null;
			 		
			 			
			 			if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
			 			
							qry += " and  orb.form_code_control like ? ";
						}
						if (!corp_sus.equals("0") && !corp_sus.equals("")) {
							qry += " and orb.form_code_control like ?";
						}
						if (!div_sus.equals("0") && !div_sus.equals("")) {
							qry += " and orb.form_code_control like ?  ";
						}
						if (!bde_sus.equals("0") && !bde_sus.equals("")) {
							qry += " and  orb.form_code_control = ?  ";
						}
						
			 			
						if (!unit_sus_no.equals("")) {
							qry += " and orb.sus_no = ? ";
						}
			 			
			 			

						if (!unit_name.equals("")) {
							qry += " and orb.unit_name = ? ";
						}
			 			
			 			
			 			
			 		q="select distinct on (c.id ) \r\n" + 
			 				"c.id,COALESCE(c.army_no,'') as army_no , COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name, COALESCE(r.rank,'') \r\n" + 
			 				"as rank, COALESCE(arm.arm_desc,'') as regiment, COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,\r\n" + 
			 				"COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority, COALESCE(a.appointment,'') as appointment,\r\n" + 
			 				"COALESCE(m.unit_name,'') as unit_name , COALESCE(ltrim(TO_CHAR(np.dt_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos, \r\n" + 
			 				"COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth, COALESCE(bd.blood_desc,'') as blood_desc, COALESCE(ic.id_card_no,'') as \r\n" + 
			 				"id_card_no, COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') as mobile_no, n7.shape,n7.cope,n7.date_of_authority, \r\n" + 
			 				"st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') as date_of_attestation from tb_psg_census_jco_or_p c   \r\n" + 
			 				"left join (select distinct g.jco_id,rank,date_of_rank from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco\r\n" + 
			 				"where status='1' group by jco_id) g1 on g1.jco_id=g.jco_id) cr on cr.jco_id= c.id   left join (select dt_of_sos,dt_of_tos,from_sus_no,h.jco_id,\r\n" + 
			 				" location,status,to_sus_no from tb_psg_posting_in_out_jco h left join (select jco_id,max(id) from tb_psg_posting_in_out_jco group by jco_id) h1\r\n" + 
			 				"on h1.jco_id=h.jco_id) np on np.jco_id=c.id left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active' left join tb_psg_update_census_address_of_birth_jco n3 \r\n" + 
			 				"on n3.jco_id=c.id and n3.status='1' left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id  left join tb_psg_mstr_appt_jco a on a.id = ca.appointment and a.status = 'active' \r\n" + 
			 				"left join tb_miso_orbat_unt_dtl m on m.sus_no = c.unit_sus_no and m.status_sus_no='Active' inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service \r\n" + 
			 				"left join tb_psg_mstr_blood bd on bd.id=c.blood_group and bd.status='active' left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1  left join \r\n" + 
			 				"tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char\r\n" + 
			 				"(m.date_of_authority,'dd MON yyyy') as date_of_authority from tb_psg_census_jco_or_p pcl \r\n" + 
			 				"left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5)) \r\n" + 
			 				"n7 on n7.id=c.id left join tb_psg_mstr_trade_jco st on st.id=c.trade\r\n" + 
			 				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'											  	\r\n" + 
			 				"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
			 				"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
			 				"left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
			 				"left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
			 				"left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  \r\n" + 
			 				" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' where  c.status in  (1,5) " +qry ;
			 			
			 			
			 			
			 			
			 			
			 			
			 			
			 		          stmt=conn.prepareStatement(q);	
			 				 /* stmt.setString(1,unit_sus_no);
			 				  stmt.setString(2,unit_name);
			 				  stmt.setString(3,month_year);*/
			 		   int i = 0;
			 		  int j = 1;
			 		  if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
							stmt.setString(j, hd_cmd_sus+'%');
							j += 1;
						}
						if (!corp_sus.equals("0") && !corp_sus.equals("")) {
							stmt.setString(j, corp_sus+'%');
							j += 1;
						}
						if (!div_sus.equals("0") && !div_sus.equals("")) {
							stmt.setString(j, div_sus+'%');
							j += 1;
						}
						if (!bde_sus.equals("0") && !bde_sus.equals("")) {
							stmt.setString(j, bde_sus);
							j += 1;
						}
						if (!unit_sus_no.equals("")) {
							stmt.setString(j, unit_sus_no);
							j += 1;
						}
						
						if (!unit_name.equals("")) {
							stmt.setString(j, unit_name);
							j += 1;
						}
						
			 	      ResultSet rs = stmt.executeQuery();  
			 	     
			 	      while (rs.next()) {
			 	    	  i++;
			 	    	  ArrayList<String> list = new ArrayList<String>();
			 	    	  
			 	    	    list.add(rs.getString("army_no"));//0
			 				list.add(rs.getString("full_name"));//1
			 				list.add(rs.getString("rank"));//2
			 				list.add(rs.getString("regiment"));//3
			 				list.add(rs.getString("commission_date"));//4
			 				list.add(rs.getString("date_of_seniority"));//5
			 				list.add(rs.getString("appointment"));//6
			 				list.add(rs.getString("unit_name"));//7
			 				list.add(rs.getString("date_of_tos"));//8
			 				list.add(rs.getString("date_of_birth"));//9
			 				list.add(rs.getString("blood_desc"));//10
			 				list.add(rs.getString("id_card_no"));//11
			 				list.add(rs.getString("mobile_no"));//12
			 				list.add(rs.getString("shape"));//13
			 				list.add(rs.getString("cope"));//14
			 				list.add(rs.getString("date_of_authority"));//15
			 				list.add(rs.getString("trade"));//16
			 				list.add(rs.getString("date_of_attestation"));//17
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
			 //change 26-07-2023
			 public ArrayList<ArrayList<String>> pdf_Download_Search_Report_jco_holding1111( String hd_cmd_sus,
						String corp_sus, String div_sus, String bde_sus, String unit_name,String sus_no,HttpSession session)
					 
					// public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String unit_name,String month_year,HttpSession session)
					 {
					 		String roleAccess = session.getAttribute("roleAccess").toString();
					 		String roleSusNo = session.getAttribute("roleSusNo").toString();	
					 	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
					 	Connection conn = null;
					 	String q="";
					 	
					 	String qry = "";
				
					 		try{	  
					 			conn = dataSource.getConnection();			 
					 			PreparedStatement stmt=null;
					 		
					 			
					 			if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
					 			
									qry += " and  orb.form_code_control like ? ";
								}
								if (!corp_sus.equals("0") && !corp_sus.equals("")) {
									qry += " and orb.form_code_control like ?";
								}
								if (!div_sus.equals("0") && !div_sus.equals("")) {
									qry += " and orb.form_code_control like ?  ";
								}
								if (!bde_sus.equals("0") && !bde_sus.equals("")) {
									qry += " and  orb.form_code_control = ?  ";
								}
								
					 			
								if (!sus_no.equals("")) {
									qry += " and orb.sus_no = ? ";
								}
					 			
					 			if (!unit_name.equals("")) {
									qry += " and orb.unit_name = ? ";
								}
					 			
					 			
					 			
					 		q="select distinct on (c.id )  c.id,COALESCE(c.army_no,'') as army_no , \r\n" + 
					 				"COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name, COALESCE(r.rank,'')  as rank,\r\n" + 
					 				"COALESCE(arm.arm_desc,'') as regiment, COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,  \r\n" + 
					 				"COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority, COALESCE(a.appointment,'') as appointment,  \r\n" + 
					 				"COALESCE(orb.unit_name,'') as unit_name , COALESCE(ltrim(TO_CHAR(c.date_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos,  \r\n" + 
					 				"COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth, COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
					 				"COALESCE(ic.id_card_no,'') as  id_card_no, COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') \r\n" + 
					 				"as mobile_no, n7.shape,n7.cope,n7.date_of_authority,  st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') \r\n" + 
					 				"as date_of_attestation from tb_psg_census_jco_or_p c   left join (select distinct g.jco_id,rank,date_of_rank \r\n" + 
					 				" from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco  where status='1' group by jco_id) g1 \r\n" + 
					 				"on g1.jco_id=g.jco_id where status='1') cr on cr.jco_id= c.id    left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active' \r\n" + 
					 				" left join tb_psg_update_census_address_of_birth_jco n3  on n3.jco_id=c.id and n3.status='1'\r\n" + 
					 				" left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id  left join tb_psg_mstr_appt_jco a on a.id = ca.appointment\r\n" + 
					 				" and a.status = 'active' \r\n" + 
					 				" inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service  \r\n" + 
					 				" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and UPPER(orb.status_sus_no) in ('INACTIVE','ACTIVE')  left join tb_psg_mstr_blood bd on bd.id=c.blood_group \r\n" + 
					 				" and bd.status='active' left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1  \r\n" + 
					 				" left join  tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id\r\n" + 
					 				" left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char  (m.date_of_authority,'dd MON yyyy') \r\n" + 
					 				"			as date_of_authority from tb_psg_census_jco_or_p pcl  \r\n" + 
					 				"left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5))  n7\r\n" + 
					 				"			on n7.id=c.id left join tb_psg_mstr_trade_jco st on st.id=c.trade left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  \r\n" + 
					 				"			and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'  \r\n" + 
					 				"			left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =\r\n" + 
					 				"			SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'  left join all_fmn_view div  \r\n" + 
					 				"			on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) \r\n" + 
					 				"			and div.level_in_hierarchy = 'Division' left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  \r\n" + 
					 				"			and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  where  c.status in  (1,5) " +qry ;
					 			
					 			
					 			
					 			
					 			
					 		          stmt=conn.prepareStatement(q);	
					 				 /* stmt.setString(1,unit_sus_no);
					 				  stmt.setString(2,unit_name);
					 				  stmt.setString(3,month_year);*/
					 		  
					 		  int j = 1;
					 		  if (!hd_cmd_sus.equals("0") && !hd_cmd_sus.equals("")) {
									stmt.setString(j, hd_cmd_sus+'%');
									j += 1;
								}
								if (!corp_sus.equals("0") && !corp_sus.equals("")) {
									stmt.setString(j, corp_sus+'%');
									j += 1;
								}
								if (!div_sus.equals("0") && !div_sus.equals("")) {
									stmt.setString(j, div_sus+'%');
									j += 1;
								}
								if (!bde_sus.equals("0") && !bde_sus.equals("")) {
									stmt.setString(j, bde_sus);
									j += 1;
								}
								if (!sus_no.equals("")) {
									stmt.setString(j, sus_no);
									j += 1;
								}
								
								if (!unit_name.equals("")) {
									stmt.setString(j, unit_name);
									j += 1;
								}
							
							System.err.println(" \n held str data print: \n" +stmt);
					 	      ResultSet rs = stmt.executeQuery();  
					 	  
					 	    int i =1;  
					 	      while (rs.next()) {
					 	    	 
					 	    	  ArrayList<String> list = new ArrayList<String>();
					 	    	   list.add(String.valueOf(i++)); //0
					 	    	  list.add(rs.getString("rank"));//2
					 	    	 list.add(rs.getString("full_name"));//1
					 	    	    list.add(rs.getString("army_no"));//0
					 	    	   list.add(rs.getString("trade"));//16
					 				list.add(rs.getString("regiment"));//3
					 				list.add(rs.getString("shape"));//13
					 				list.add(rs.getString("date_of_tos"));//8
					 				list.add(rs.getString("date_of_birth"));//9
					 				list.add(rs.getString("commission_date"));//4
					 				list.add(rs.getString("date_of_attestation"));//17
					 				list.add(rs.getString("date_of_seniority"));//5
					 				list.add(null);
					 				/*list.add(rs.getString("appointment"));//6
					 				list.add(rs.getString("unit_name"));//7
					 				
					 				list.add(rs.getString("date_of_birth"));//9
					 				list.add(rs.getString("blood_desc"));//10
					 				list.add(rs.getString("id_card_no"));//11
					 				list.add(rs.getString("mobile_no"));//12
					 			
					 				list.add(rs.getString("cope"));//14
					 				list.add(rs.getString("date_of_authority"));//15
		*/			 				
					 				
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
			 
			 
			 
			 /*Getsearch_heldthstrCountCount*/
			 /*select distinct on (c.id )  c.id,COALESCE(c.army_no,'') as army_no , 
			 COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name, COALESCE(r.rank,'')  as rank,
			 COALESCE(arm.arm_desc,'') as regiment, COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,  
			 COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority, COALESCE(a.appointment,'') as appointment,  
			 COALESCE(m.unit_name,'') as unit_name , COALESCE(ltrim(TO_CHAR(np.dt_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos,  
			 COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth, COALESCE(bd.blood_desc,'') as blood_desc,
			 COALESCE(ic.id_card_no,'') as  id_card_no, COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') 
			 as mobile_no, n7.shape,n7.cope,n7.date_of_authority,  st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') 
			 as date_of_attestation from tb_psg_census_jco_or_p c   left join (select distinct g.jco_id,rank,date_of_rank 
			  from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco  where status='1' group by jco_id) g1 
			 on g1.jco_id=g.jco_id) cr on cr.jco_id= c.id   left join (select dt_of_sos,dt_of_tos,from_sus_no,h.jco_id, location,status,to_sus_no 
			 from tb_psg_posting_in_out_jco h left join (select jco_id,max(id) from tb_psg_posting_in_out_jco group by jco_id) h1 
			  on h1.jco_id=h.jco_id) np on np.jco_id=c.id left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active' 
			  left join tb_psg_update_census_address_of_birth_jco n3  on n3.jco_id=c.id and n3.status='1'
			  left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id  left join tb_psg_mstr_appt_jco a on a.id = ca.appointment
			  and a.status = 'active'  left join tb_miso_orbat_unt_dtl m on m.sus_no = c.unit_sus_no and m.status_sus_no='Active' 
			  inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service  left join tb_psg_mstr_blood bd on bd.id=c.blood_group 
			  and bd.status='active' left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1  
			  left join  tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id
			  left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char  (m.date_of_authority,'dd MON yyyy') 
			 			as date_of_authority from tb_psg_census_jco_or_p pcl  
			 			left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5))  n7
			 			on n7.id=c.id left join tb_psg_mstr_trade_jco st on st.id=c.trade  inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = 
			 			c.unit_sus_no and orb.status_sus_no !='INVALID'	 left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  
			 			and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'  
			 			left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =
			 			SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'  left join all_fmn_view div  
			 			on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) 
			 			and div.level_in_hierarchy = 'Division' left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  
			 			and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 
			 where orb.form_code_control like '%'  and c.status in  (1,5) order by id  desc  limit 10 OFFSET 0*/
			 
			 //bisag v1 2208022 (formation wise get data with datatable)
			// bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam)
			 public long Getsearch_heldthstrCountCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
						String unit_name,String unit_sus_no) {
					
					String SearchValue = GenerateQueryWhereClause_SQL( Search,  cont_comd, cont_corps, cont_div, cont_bde,
							unit_name, unit_sus_no); 
						int total = 0;
						String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
						String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
						String q = null;
						Connection conn = null;
						try {
							conn = dataSource.getConnection();
							
						
						  q="select count(app.*) from(select distinct on (c.id )  c.id,COALESCE(c.army_no,'') as army_no , \r\n" + 
						  		"COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name, COALESCE(r.rank,'')  as rank,\r\n" + 
						  		"COALESCE(arm.arm_desc,'') as regiment, COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,  \r\n" + 
						  		"COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority, COALESCE(a.appointment,'') as appointment,  \r\n" + 
						  		"COALESCE(m.unit_name,'') as unit_name , COALESCE(ltrim(TO_CHAR(c.date_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos,  \r\n" + 
						  		"COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth, COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
						  		"COALESCE(ic.id_card_no,'') as  id_card_no, COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') \r\n" + 
						  		"as mobile_no, n7.shape,n7.cope,n7.date_of_authority,  st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') \r\n" + 
						  		"as date_of_attestation from tb_psg_census_jco_or_p c   left join (select distinct g.jco_id,rank,date_of_rank \r\n" + 
						  		" from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco  where status='1' group by jco_id) g1 \r\n" + 
						  		"on g1.jco_id=g.jco_id) cr on cr.jco_id= c.id    left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active' \r\n" + 
						  		" left join tb_psg_update_census_address_of_birth_jco n3  on n3.jco_id=c.id and n3.status='1'\r\n" + 
						  		" left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id  left join tb_psg_mstr_appt_jco a on a.id = ca.appointment\r\n" + 
						  		" and a.status = 'active'  left join tb_miso_orbat_unt_dtl m on m.sus_no = c.unit_sus_no and m.status_sus_no='Active' \r\n" + 
						  		" inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service "
						  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and upper(orb.status_sus_no) in  ('INACTIVE','ACTIVE')" + 
						  		"	 left join tb_psg_mstr_blood bd on bd.id=c.blood_group \r\n" + 
						  		" and bd.status='active' left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1  \r\n" + 
						  		" left join  tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id\r\n" + 
						  		" left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char  (m.date_of_authority,'dd MON yyyy') \r\n" + 
						  		"			as date_of_authority from tb_psg_census_jco_or_p pcl  \r\n" + 
						  		"			left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5))  n7\r\n" + 
						  		"			on n7.id=c.id left join tb_psg_mstr_trade_jco st on st.id=c.trade  	 left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  \r\n" + 
						  		"			and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'  \r\n" + 
						  		"			left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =\r\n" + 
						  		"			SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'  left join all_fmn_view div  \r\n" + 
						  		"			on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) \r\n" + 
						  		"			and div.level_in_hierarchy = 'Division' left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  \r\n" + 
						  		"			and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' "+SearchValue+" and  c.status in  (1,5)) app " ;
							
							PreparedStatement stmt = conn.prepareStatement(q);
							
							stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
									unit_name, unit_sus_no);
						
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
				
				
				
				
				public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
						  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
							String unit_name,String unit_sus_no) {
					int flag = 0;
					try {
						if(!Search.equals("")) {			
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							

							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
							flag += 1;
							stmt.setString(flag, "%"+Search.toUpperCase()+"%");
							
						}

						if(!cont_comd.equals("0")) {
							flag += 1;
							stmt.setString(flag, cont_comd.toUpperCase()+"%");
							
						}
						if(!cont_corps.equals("0")) {
							flag += 1;
							stmt.setString(flag, cont_corps.toUpperCase()+"%");
							
						}
						if(!cont_div.equals("0")) {
							flag += 1;
							stmt.setString(flag, cont_div.toUpperCase()+"%");
							
						}
						if(!cont_bde.equals("0")) {
							flag += 1;
							stmt.setString(flag, cont_bde.toUpperCase()+"%");
							
						}
						if(!unit_name.equals("")) {
							flag += 1;
							stmt.setString(flag, unit_name.toUpperCase());
							
						}
						if(!unit_sus_no.equals("")) {
							flag += 1;
							stmt.setString(flag, unit_sus_no.toUpperCase());
							
						}
						
					}catch (Exception e) {}
				
					return stmt;
					
				}

				 public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
							String unit_name,String unit_sus_no) {
						String SearchValue ="";
						if(!Search.equals("")) { // for Input Filter
							SearchValue =" where  ";
							SearchValue +="( upper(r.rank) like ? or upper(full_name) like ? or upper(army_no) like ? "
									+ " or upper(st.trade) like ? or upper(arm.arm_desc) like ? or upper(n7.shape) like ? or upper(ltrim(TO_CHAR(c.date_of_tos,'DD-MON-YYYY'),'0')) like ? " 
									+ " or upper(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0')) like ? or upper(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0')) like ? or upper(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0')) like ? or upper(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0')) like ? ) ";
						}
						
						if( !cont_comd.equals("0")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and  orb.form_code_control like ?  ";	
							}
							else {
								SearchValue += " where orb.form_code_control like ? ";
							}
						}
						if( !cont_corps.equals("0")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and  orb.form_code_control like ?  ";	
							}
							else {
								SearchValue += " where  orb.form_code_control like ? ";
							}
						}
						if( !cont_div.equals("0")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and  orb.form_code_control like ?  ";	
							}
							else {
								SearchValue += " where  orb.form_code_control like ? ";
							}
						}
						if( !cont_bde.equals("0")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and  orb.form_code_control like ?  ";	
							}
							else {
								SearchValue += " where  orb.form_code_control like ? ";
							}
						}
						
						if( !unit_name.equals("")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and  orb.unit_name = ? ";	
							}
							else {
								SearchValue += " where orb.unit_name like ?";
							}
						}
						
						
						if( !unit_sus_no.equals("")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and orb.sus_no = ? ";	
							}
							else {
								SearchValue += " where orb.sus_no = ? ";
							}
						}
						
						
						
					
						
						return SearchValue;
					}
				 
				 
				// bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam)
				 public List<Map<String, Object>> Getsearch_heldthstrdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
						 String cont_comd,String cont_corps,String cont_div,String cont_bde,
							String unit_name,String unit_sus_no) 
					{
						
					 
						String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
						String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
						
				    	String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
								unit_name, unit_sus_no);
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
							
						
							q="select distinct on (c.id )  c.id,COALESCE(c.army_no,'') as army_no , \r\n" + 
									"COALESCE(concat(c.first_name,' ',c.middle_name,' ',c.last_name),'') as full_name, COALESCE(r.rank,'')  as rank,\r\n" + 
									"COALESCE(arm.arm_desc,'') as regiment, COALESCE(ltrim(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'0'),'') as commission_date,  \r\n" + 
									"COALESCE(ltrim(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'0'),'') as date_of_seniority, COALESCE(a.appointment,'') as appointment,  \r\n" + 
									"COALESCE(orb.unit_name,'') as unit_name , COALESCE(ltrim(TO_CHAR(c.date_of_tos,'DD-MON-YYYY'),'0'),'') as date_of_tos,  \r\n" + 
									"COALESCE(ltrim(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'0'),'') as date_of_birth, COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
									"COALESCE(ic.id_card_no,'') as  id_card_no, COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') \r\n" + 
									"as mobile_no, n7.shape,n7.cope,n7.date_of_authority,  st.trade,COALESCE(ltrim(TO_CHAR(c.date_of_attestation,'DD-MON-YYYY'),'0'),'') \r\n" + 
									"as date_of_attestation from tb_psg_census_jco_or_p c   left join (select distinct g.jco_id,rank,date_of_rank \r\n" + 
									" from tb_psg_change_of_rank_jco g left join (select jco_id,max(id) from tb_psg_change_of_rank_jco  where status='1' group by jco_id) g1 \r\n" + 
									"on g1.jco_id=g.jco_id where status='1') cr on cr.jco_id= c.id    left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active' \r\n" + 
									" left join tb_psg_update_census_address_of_birth_jco n3  on n3.jco_id=c.id and n3.status='1'\r\n" + 
									" left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id  left join tb_psg_mstr_appt_jco a on a.id = ca.appointment\r\n" + 
									" and a.status = 'active'   \r\n" + 
									" inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.arm_service  "+
									" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and upper(orb.status_sus_no) in  ('INACTIVE','ACTIVE') " +
									"left join tb_psg_mstr_blood bd on bd.id=c.blood_group \r\n" + 
									" and bd.status='active' left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1  \r\n" + 
									" left join  tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id\r\n" + 
									" left join (select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id,to_char  (m.date_of_authority,'dd MON yyyy') \r\n" + 
									"			as date_of_authority from tb_psg_census_jco_or_p pcl  \r\n" + 
									"			left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id and m.status=1 and pcl.status in (1,5))  n7\r\n" + 
									"			on n7.id=c.id left join tb_psg_mstr_trade_jco st on st.id=c.trade  	 left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  \r\n" + 
									"			and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'  \r\n" + 
									"			left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =\r\n" + 
									"			SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'  left join all_fmn_view div  \r\n" + 
									"			on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) \r\n" + 
									"			and div.level_in_hierarchy = 'Division' left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  \r\n" + 
									"			and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'  "+SearchValue +   " and c.status in  (1,5) order by id "  +
									" desc  limit " +pageL+" OFFSET "+startPage+"";
							
						
						
							stmt=conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
									unit_name, unit_sus_no);
							System.err.println(" \n held str data: \n" +stmt);
					      ResultSet rs = stmt.executeQuery();   
					   
					      ResultSetMetaData metaData = rs.getMetaData();
					      int columnCount = metaData.getColumnCount();
					      
					  	while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							
					  
							//columns.put("action",action);
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
				 
				///// bisag v2 050922  (converted to Datatable)

				 public long Search_Report_auth_count_a(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) {
										
										String SearchValue = GenerateQueryWhereClause_SQLauth( Search,  cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no); 
											int total = 0;
											String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
											String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
											String q = null;
											Connection conn = null;
											try {
												conn = dataSource.getConnection();
												
											
											  q="select count(app.*) from(select  count(*) filter (where parent_code = '1') as JCOs," + 
											  		" count(*) filter (where parent_code = '2') as JCOs_OR," + 
											  		"  count(*) filter (where parent_code = '3') as OR," + 
											  		" coalesce(count(*) filter (where parent_code = '1') + " + 
											  		" count(*) filter (where parent_code = '2') + " + 
											  		" count(*) filter (where parent_code = '3')) as Total" + 
											  		" from cue_tb_psg_rank_app_master where upper(level_in_hierarchy) = upper('Rank') ) app " ;
												
												PreparedStatement stmt = conn.prepareStatement(q);
												
												stmt = setQueryWhereClause_SQLauth(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
														unit_name, unit_sus_no);
											
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
									
									
								 public String GenerateQueryWhereClause_SQLauth(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no) {
										String SearchValue ="";
										if(!Search.equals("")) { // for Input Filter
											SearchValue =" where  ";
											SearchValue +="( JCOs = ? or JCOs_OR = ? or OR = ?  or"
													+ " Total = ? ) ";
										}
										
										
										
										return SearchValue;
									}					
								 
								 public PreparedStatement setQueryWhereClause_SQLauth(PreparedStatement
										  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no) {
									int flag = 0;
									try {
										if(!Search.equals("")) {			
											
											flag += 1;
											stmt.setString(flag, Search);
											
											flag += 1;
											stmt.setString(flag, Search);
											

											flag += 1;
											stmt.setString(flag, Search);
											
											
											flag += 1;
											stmt.setString(flag, Search);
											
										
										}
					
									}catch (Exception e) {}
									
									return stmt;
									
								}				 
								 public List<Map<String, Object>> Search_Report_auth_a(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
										 String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) 
									{
										
									 
										String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
										String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
										
								    	String SearchValue = GenerateQueryWhereClause_SQLauth(Search, cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no);
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
											
										
											q="  select  count(*) filter (where parent_code = '1') as jco,\r\n" + 
								 					"  count(*) filter (where parent_code = '2') as jcos_or,\r\n" + 
								 					"  count(*) filter (where parent_code = '3') as or,\r\n" + 
								 					"  coalesce(count(*) filter (where parent_code = '1') + \r\n" + 
								 					"           count(*) filter (where parent_code = '2') +\r\n" + 
								 					"		   count(*) filter (where parent_code = '3')) as total\r\n" + 
								 					"  from cue_tb_psg_rank_app_master where upper(level_in_hierarchy) = upper('Rank')" +
													"   limit " +pageL+" OFFSET "+startPage+"";
											
										
										
											stmt=conn.prepareStatement(q);
											stmt = setQueryWhereClause_SQLauth(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
													unit_name, unit_sus_no);
											
									      ResultSet rs = stmt.executeQuery();   
									    
									      ResultSetMetaData metaData = rs.getMetaData();
									      int columnCount = metaData.getColumnCount();
									      
									  	while (rs.next()) {
											Map<String, Object> columns = new LinkedHashMap<String, Object>();
											
											for (int i = 1; i <= columnCount; i++) {
												columns.put(metaData.getColumnLabel(i), rs.getObject(i));
											}
											
									  
											//columns.put("action",action);
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
								 
								 				 
								 
								 public long Search_Report_held_strCount_b(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) {
										
										String SearchValue = GenerateQueryWhereClause_SQL_held( Search,  cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate); 
									
									
											int total = 0;
											String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
											String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
											String q = null;
											Connection conn = null;
											try {
												conn = dataSource.getConnection();
												
											
											  q="select count(app.*) from(select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
											  		" hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total" + 
											  		" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
											  		" 'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total, " + 
											  		" count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
											  		" count(*) filter (where r.rank in ('WARRANT OFFICER','SUB / EQUIVALENT')) as sub," + 
											  		" count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
											  		" count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
											  		" count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
											  		" count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep" + 
											  		" from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id " + 
											  		"inner join tb_psg_posting_in_out_jco op on c.id = op.jco_id "
											  		+ " inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'	" + 
											  		" left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no " + 
											  		" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' " + 
											  		" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' " + 
											  		"	left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' " + 
											  		"	left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no " + 
											  		"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"+SearchValue+""
											  		+ "and c.status not in ('0','3')  and op.status=1" + 
											  		" and op.dt_of_tos not in (select date_of_tos from tb_psg_re_call_jco  order by date_of_tos limit 1) group by c.rank,to_sus_no )hld) app " ;
												
												PreparedStatement stmt = conn.prepareStatement(q);
												
												stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
														unit_name, unit_sus_no,FDate);
											
											
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
								 public List<Map<String, Object>> Search_Report_held_strdata_b(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
										 String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) 
									{
										
									 
										String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
										String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
										
								    	String SearchValue = GenerateQueryWhereClause_SQL_held(Search, cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate);
								    	
								    	
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
											
										
											
										
											q="select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos,\r\n" + 
								 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total\r\n" + 
								 					"from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT',\r\n" + 
								 					"'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total, \r\n" + 
								 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm,\r\n" + 
								 					"count(*) filter (where r.rank in ('WARRANT OFFICER','SUB / EQUIVALENT')) as sub,\r\n" + 
								 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub,\r\n" + 
								 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav,\r\n" + 
								 					"count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk,\r\n" + 
								 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep\r\n" + 
								 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
								 					"inner join tb_psg_posting_in_out_jco op on c.id = op.jco_id "
								 					+ " inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
								 					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
								 					" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
								 					" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
								 					" left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
								 					" left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no \r\n" + 
								 					"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"+SearchValue+ 
								 					"and c.status not in ('0','3')  and op.status=1\r\n" + 
								 					" and op.dt_of_tos not in (select date_of_tos from tb_psg_re_call_jco  order by date_of_tos limit 1) group by c.rank,to_sus_no )hld limit " +pageL+" OFFSET "+startPage+"";
											
										
											
											stmt=conn.prepareStatement(q);
											stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
													unit_name, unit_sus_no,FDate);
											
									      ResultSet rs = stmt.executeQuery();   
									   
									      ResultSetMetaData metaData = rs.getMetaData();
									      int columnCount = metaData.getColumnCount();
									      
									  	while (rs.next()) {
											Map<String, Object> columns = new LinkedHashMap<String, Object>();
											
											for (int i = 1; i <= columnCount; i++) {
												columns.put(metaData.getColumnLabel(i), rs.getObject(i));
											}
											
									  
											//columns.put("action",action);
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
					////////////////////////c////////
								 
								 
								 public long Search_Report_Desertercount_c(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) {
										
										String SearchValue = GenerateQueryWhereClause_SQL_held_c( Search,  cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate); 
									
											int total = 0;
											String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
											String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
											String q = null;
											Connection conn = null;
											try {
												conn = dataSource.getConnection();
												
											
												q="select count(app.*) from(select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
									 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
									 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
									 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
									 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
									 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
									 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
									 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
									 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
									 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
									 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
									 					"inner join tb_psg_deserter_jco ds1 on  c.id = ds1.jco_id  "
									 					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
									 					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
									 					" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
									 					" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
									 					" left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
									 					" left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no \r\n" + 
									 					"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' " + SearchValue +
									 					" and c.status not in ('0','3')  and ds1.status=1)hld) app";
												
											
												
												PreparedStatement stmt = conn.prepareStatement(q);
												
												stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
														unit_name, unit_sus_no,FDate);
											
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
								 public List<Map<String, Object>> Search_Report_Deserter_c(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
										 String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) 
									{
										
									 
										String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
										String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
										
								    	String SearchValue = GenerateQueryWhereClause_SQL_held_c(Search, cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate);
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
											
										
											
										
											q=" select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
								 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
								 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
								 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
								 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
								 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
								 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
								 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
								 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
								 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
								 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
								 					"inner join tb_psg_deserter_jco ds1 on  c.id = ds1.jco_id  "
								 					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
								 					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
								 					" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
								 					" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
								 					" left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
								 					" left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no \r\n" + 
								 					"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' "+SearchValue+
								 					" and c.status not in ('0','3')  and ds1.status=1 )hld "+
											         " limit " +pageL+" OFFSET "+startPage+"";
											
											
										
										
											
											stmt=conn.prepareStatement(q);
											stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
													unit_name, unit_sus_no,FDate);
											
									      ResultSet rs = stmt.executeQuery();   
									     
									      ResultSetMetaData metaData = rs.getMetaData();
									      int columnCount = metaData.getColumnCount();
									      
									  	while (rs.next()) {
											Map<String, Object> columns = new LinkedHashMap<String, Object>();
											
											for (int i = 1; i <= columnCount; i++) {
												columns.put(metaData.getColumnLabel(i), rs.getObject(i));
											}
											
									  
											//columns.put("action",action);
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
								 
								 
					///////////////atach
								 
								 public long Search_Report_attachmentCount_d(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) {
										
										String SearchValue = GenerateQueryWhereClause_SQL_held_d( Search,  cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate); 
											int total = 0;
											String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
											String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
											String q = null;
											Connection conn = null;
											try {
												conn = dataSource.getConnection();
												
											
											
												q=" select count(app.*) from( select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
									 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
									 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
									 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
									 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
									 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
									 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
									 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
									 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
									 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
									 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
									 					"inner join tb_psg_census_attachment_details_jco at1 on   c.id = at1.jco_id " 
									 					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
									 					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
									 					" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
									 					" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
									 					" left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
									 					" left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no \r\n" + 
									 					"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' "+ SearchValue +
									 					" and  at1.status=1 and c.status not in ('0','3'))hld ) app";
												
												PreparedStatement stmt = conn.prepareStatement(q);
												
												stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
														unit_name, unit_sus_no,FDate);
											
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
								 public List<Map<String, Object>> Search_Report_attachment_d(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
										 String cont_comd,String cont_corps,String cont_div,String cont_bde,
											String unit_name,String unit_sus_no,String FDate) 
									{
									
									 
										String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
										String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
										
								    	String SearchValue = GenerateQueryWhereClause_SQL_held_d(Search, cont_comd, cont_corps, cont_div, cont_bde,
												unit_name, unit_sus_no,FDate);
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
											
											
											q=" select hld.sm,hld.sub,hld.nb_sub,coalesce(hld.sm+hld.sub+hld.nb_sub) as total_jcos," + 
								 					"hld.hav,hld.nk,hld.sep,coalesce(hld.hav+hld.nk+hld.sep) as total_or,hld.total " + 
								 					" from (select count(r.rank) filter (where r.rank in ('WARRANT OFFICER','SUB MAJ / EQUIVALENT'," + 
								 				    "'NB SUB / EQUIVALENT','HAV / EQUIVALENT','NK / EQUIVALENT', 'SEP / EQUIVALENT')) as total," + 
								 					"count(*) filter (where r.rank = 'SUB MAJ / EQUIVALENT') as sm," + 
								 					"count(*) filter (where r.rank = 'WARRANT OFFICER') as sub," + 
								 					"count(*) filter (where r.rank = 'NB SUB / EQUIVALENT') as nb_sub," + 
								 					"count(*) filter (where r.rank = 'HAV / EQUIVALENT') as hav," + 
								 				     "count(*) filter (where r.rank = 'NK / EQUIVALENT') as nk," + 
								 					"count(*) filter (where r.rank = 'SEP / EQUIVALENT') as sep " + 
								 					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_rank_jco r on c.rank=r.id  \r\n" + 
								 					"inner join tb_psg_census_attachment_details_jco at1 on   c.id = at1.jco_id " 
								 					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
								 					"left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no\r\n" + 
								 					" and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
								 					" left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
								 					" left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
								 					" left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no \r\n" + 
								 					"	 and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' "+ SearchValue +
								 					" and  at1.status=1 and c.status not in ('0','3'))hld "+
								 					 " limit " +pageL+" OFFSET "+startPage+"";
										
											
										
											
											
											stmt=conn.prepareStatement(q);
											stmt = setQueryWhereClause_SQLheld(stmt,Search,cont_comd, cont_corps, cont_div, cont_bde,
													unit_name, unit_sus_no,FDate);
											
									      ResultSet rs = stmt.executeQuery();   
									     
									      ResultSetMetaData metaData = rs.getMetaData();
									      int columnCount = metaData.getColumnCount();
									      
									  	while (rs.next()) {
											Map<String, Object> columns = new LinkedHashMap<String, Object>();
											
											for (int i = 1; i <= columnCount; i++) {
												columns.put(metaData.getColumnLabel(i), rs.getObject(i));
											}
											
									  
											//columns.put("action",action);
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
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
				////comman
								
								 
								 
								
							
									public PreparedStatement setQueryWhereClause_SQLheld(PreparedStatement
											  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
												String unit_name,String unit_sus_no,String FDate) {
										int flag = 0;
										try {
											/*if(!Search.equals("")) {			
												
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												flag += 1;
												stmt.setString(flag, Search);
												
											}
				*/
											if(!cont_comd.equals("0")) {
												flag += 1;
												stmt.setString(flag, cont_comd.toUpperCase()+"%");
												
											}
											if(!cont_corps.equals("0")) {
												flag += 1;
												stmt.setString(flag, cont_corps.toUpperCase()+"%");
												
											}
											if(!cont_div.equals("0")) {
												flag += 1;
												stmt.setString(flag, cont_div.toUpperCase()+"%");
												
											}
											if(!cont_bde.equals("0")) {
												flag += 1;
												stmt.setString(flag, cont_bde.toUpperCase()+"%");
												
											}
											if(!unit_name.equals("")) {
												flag += 1;
												stmt.setString(flag, unit_name.toUpperCase());
												
											}
											if(!unit_sus_no.equals("")) {
												flag += 1;
												stmt.setString(flag, unit_sus_no.toUpperCase());
												
											}
											if(!FDate.equals("")) {
												flag += 1;
												stmt.setString(flag, FDate);
												flag += 1;
												stmt.setString(flag, FDate);
											}
										
											
										}catch (Exception e) {}
										return stmt;
										
									}

									 public String GenerateQueryWhereClause_SQL_held(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
												String unit_name,String unit_sus_no,String FDate) {
											String SearchValue ="";
											/*if(!Search.equals("")) { // for Input Filter
												SearchValue =" where  ";
												SearchValue +="( sm = ? or sub = ? or nb_sub = ? or total_jcos = ? or hav = ? or nk = ? or sep =? or total_or = ? or total = ? ) ";
											}*/
											
											
											
											
											if( !cont_comd.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where orb.form_code_control like ? ";
												}
											}
											if( !cont_corps.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_div.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_bde.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											
											if( !unit_name.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.unit_name = ? ";	
												}
												else {
													SearchValue += " where orb.unit_name like ?";
												}
											}
											
											
											if( !unit_sus_no.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and orb.sus_no = ? ";	
												}
												else {
													SearchValue += " where orb.sus_no = ? ";
												}
											}
											
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += "and to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') ";	
												}
												else {
													SearchValue += " where to_date(to_char(op.dt_of_tos,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') ";
												}
											}
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += "and (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')) ";	
												}
												else {
													SearchValue += " where (op.tenure_date is null OR to_date(to_char(op.tenure_date,'Mon YYYY'),'Mon YYYY') >= to_date(?,'YYYY/MM/DD')) ";
												}
											}
											
											
											return SearchValue;
										}
								 
									 
									 
									 
									 public String GenerateQueryWhereClause_SQL_held_c(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
												String unit_name,String unit_sus_no,String FDate) {
											String SearchValue ="";
											/*if(!Search.equals("")) { // for Input Filter
												SearchValue =" where  ";
												SearchValue +="( sm = ? or sub = ? or nb_sub = ? or total_jcos = ? or hav = ? or nk = ? or sep =? or total_or = ? or total = ? ) ";
											}*/
											
											
											
											
											if( !cont_comd.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where orb.form_code_control like ? ";
												}
											}
											if( !cont_corps.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_div.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_bde.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											
											if( !unit_name.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.unit_name = ? ";	
												}
												else {
													SearchValue += " where orb.unit_name like ?";
												}
											}
											
											
											if( !unit_sus_no.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and orb.sus_no = ? ";	
												}
												else {
													SearchValue += " where orb.sus_no = ? ";
												}
											}
											
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += "and to_date(to_char(ds1.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD') ";	
												}
												else {
													SearchValue += " where to_date(to_char(ds1.dt_desertion,'Mon YYYY'),'Mon YYYY') <= to_date(?,'YYYY/MM/DD')  ";
												}
											}
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += "and (ds1.dt_recovered is null OR to_date(to_char(ds1.dt_recovered,'Mon YYYY'),'Mon YYYY') > to_date(?,'YYYY/MM/DD')) ";	
												}
												else {
													SearchValue += " where (ds1.dt_recovered is null OR to_date(to_char(ds1.dt_recovered,'Mon YYYY'),'Mon YYYY') > to_date(?,'YYYY/MM/DD'))";
												}
											}
											
											
											return SearchValue;
										}
								 
									 public String GenerateQueryWhereClause_SQL_held_d(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
												String unit_name,String unit_sus_no,String FDate) {
											String SearchValue ="";
											/*if(!Search.equals("")) { // for Input Filter
												SearchValue =" where  ";
												SearchValue +="( sm = ? or sub = ? or nb_sub = ? or total_jcos = ? or hav = ? or nk = ? or sep =? or total_or = ? or total = ? ) ";
											}*/
											
											
											
											
											if( !cont_comd.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where orb.form_code_control like ? ";
												}
											}
											if( !cont_corps.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_div.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											if( !cont_bde.equals("0")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.form_code_control like ?  ";	
												}
												else {
													SearchValue += " where  orb.form_code_control like ? ";
												}
											}
											
											if( !unit_name.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and  orb.unit_name = ? ";	
												}
												else {
													SearchValue += " where orb.unit_name like ?";
												}
											}
											
											
											if( !unit_sus_no.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and orb.sus_no = ? ";	
												}
												else {
													SearchValue += " where orb.sus_no = ? ";
												}
											}
											
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += " and (to_char(at1.att_from,'Mon YYYY'),'Mon YYYY') >= (?,'YYYY/MM/DD')";	
												}
												else {
													SearchValue += "  where (to_char(at1.att_from,'Mon YYYY'),'Mon YYYY') >= (?,'YYYY/MM/DD') ";
												}
											}
											
											if( !FDate.equals("")) {
												if (SearchValue.contains("where")) {
													SearchValue += "and (to_char(at1.att_to,'Mon YYYY'),'Mon YYYY') <= (?,'YYYY/MM/DD') ";	
												}
												else {
													SearchValue += " where (to_char(at1.att_to,'Mon YYYY'),'Mon YYYY') <= (?,'YYYY/MM/DD') ";
												}
											}
											
											
											return SearchValue;
										}
				 
				 

}
