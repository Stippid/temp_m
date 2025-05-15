package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Mms_tb_regn_oth_mstr;
import com.persistance.util.HibernateUtil;

public class MMS_StoresDAOImpl implements MMS_StoresDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
//sana  17-11
	public ArrayList<ArrayList<String>> mms_holdings_list(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		    nrSql = "select b.form_code_control,c.unit_name as hq_name,a.sus_no,b.unit_name,m.prf_code,prf.prf_group,a.census_no,m.nomen,m.cat_part_no,";
		    nrSql = nrSql + "  m.au,t3.label as au_n,m.item_status,t4.label as item_status_n,a.type_of_hldg,t1.label as type_of_hldg_n,";
		    nrSql = nrSql + "  a.type_of_eqpt,t2.label as type_of_eqpt_n,sum(a.tothol) as tothol ,\r\n" + 
		    		"    coalesce (sum(a.tothol) Filter(where a.service_status='0'),'0') as unsv ";		
		    nrSql = nrSql + " from mms_tv_regn_mstr a";
		    nrSql = nrSql + "  left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
		    nrSql = nrSql + "  left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
		    nrSql = nrSql + "  left join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no";
		    nrSql = nrSql + "  left join nrv_hq c on b.form_code_control=c.form_code_control";
		    nrSql = nrSql + "  left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
		    nrSql = nrSql + "  left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
		    nrSql = nrSql + "  left join mms_domain_values t3 on m.au=t3.codevalue and t3.domainid='ACCOUNTINGUNITS'";
		    nrSql = nrSql + "  left join mms_domain_values t4 on m.item_status=t4.codevalue and t4.domainid='ITEMSTATUS'";
		    nrSql = nrSql + "  where b.status_sus_no='Active'";
		    cnd="Y";
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("UNIT")){
		 			cndValue="and a.sus_no=?";
		 			nrSql =nrSql+cndValue;
		 			cnd="Y";
		 		} else {
		 			if(formcodeType.equalsIgnoreCase("COMMAND")){
		 				cndValue="substr(form_code_control,1,1)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		 				cndValue="substr(form_code_control,1,3)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("DIV")) {
		 				cndValue="substr(form_code_control,1,6)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("BDE")) {
		 				cndValue="substr(form_code_control,1,10)=?)";
		 			} else if(formcodeType.equalsIgnoreCase("LINE")) {
		 				cndValue=" arm_code=?)";
		 			}
		 			nrSql =nrSql +" and a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
		 			cnd="Y";
		 		}
	     	}
		 	
		 	if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
	     		nrSql =nrSql +" prf.prf_group_code=?";
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
		 		if(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE")){
		 			if(type_of_hldg.equalsIgnoreCase("ENGO")){
		 				nrSql =nrSql +" m.item_category='2'";
		 			}
		 			if(type_of_hldg.equalsIgnoreCase("ENGE")){
		 				nrSql =nrSql +" m.item_category='3'";
		 			}
		 			cnd="Y";		 			
		 		} else {
		 			nrSql =nrSql +" a.type_of_hldg=?";	
		 			cnd="Y";
		 		}
	     	}
		 	
		 	nrSql = nrSql + " group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17  order by b.form_code_control,a.sus_no,a.census_no,a.type_of_hldg,a.type_of_eqpt";
		    
		 	
		 	
		 	PreparedStatement stmt = conn.prepareStatement(nrSql);
		         
		    int f = 1;
		    if(formcode.length()>0){
		    	if(formcodeType.equalsIgnoreCase("UNIT")){
		 			stmt.setString(f, formcode);
		 		} else {
			    	if(formcodeType.equalsIgnoreCase("COMMAND")){
			    		stmt.setString(f, formcode.substring(0,1));
		     		}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		     			stmt.setString(f, formcode.substring(0,3));
		     		}else if(formcodeType.equalsIgnoreCase("DIV")) {
		     			stmt.setString(f, formcode.substring(0,6));
		     		}else if(formcodeType.equalsIgnoreCase("BDE")) {
		     			stmt.setString(f, formcode.substring(0,10));
		     		}else if(formcodeType.equalsIgnoreCase("LINE")) {
		     			stmt.setString(f, formcode);
		     		}
		 		}
		    	f++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(f, p_code);
		    	f++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	/*if(!(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE"))){
		    		stmt.setString(f, type_of_hldg);
		    	}*/
		    	
		    	if(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE")){
		 					 			
		 		} else {
		 			stmt.setString(f, type_of_hldg);
		 		}
		    	
		    }
		    System.out.println(stmt+"======"); 
		    ResultSet rs = stmt.executeQuery();   
		
			if(!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
            }else{
            	while(rs.next()){
            		ArrayList<String> list = new ArrayList<String>();
            		list.add(rs.getString("hq_name"));
            		list.add(rs.getString("sus_no"));
            		list.add(rs.getString("unit_name"));
            		list.add(rs.getString("prf_group"));
            		list.add(rs.getString("census_no"));
            		list.add(rs.getString("nomen"));
            		list.add(rs.getString("type_of_hldg_n"));
            		list.add(rs.getString("tothol"));
            		//list.add(rs.getString("upd_date"));
            		list.add(rs.getString("unsv"));
            		li.add(list);  
            	}
            }
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e){
    	   e.printStackTrace();
       }	
       return li;
   }

	@SuppressWarnings("unchecked")
	public Boolean checkIfExisteqpt_regn_no(String eqpt_regn_no) {
		String hql="from Mms_tb_regn_oth_mstr where eqpt_regn_no=:eqpt_regn_no";
		List<Mms_tb_regn_oth_mstr> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			Query query= session.createQuery(hql);
			query.setParameter("eqpt_regn_no",eqpt_regn_no);
			users = (List<Mms_tb_regn_oth_mstr>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}

	public String RegnNoGeneration(String prf_code, String eqpt_regn_no) {
		String Regn_seq_no = "";
		//-------------------------New Query for max number ---------------------------------------
		String list1 = new String();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String sql1="";
		  /*  sql1 = "select max(prfseq) as count from \r\n " +
		    		 " (select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_mstr_detl  where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n " +
		    				" UNION \r\n " +
		    				" select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_oth_mstr where regn_seq_no is not null  group by substr(regn_seq_no,0,8) ) AS tab";
		    */
		    
		    sql1 = "select max(prfseq) as count from (select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8)\r\n" + 
		    		"UNION\r\n" + 
		    		"select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_oth_mstr where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n" + 
		    		"UNION\r\n" + 
		    		"select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_depot_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n" + 
		    		") AS tab"; 
		    PreparedStatement stmt = conn.prepareStatement(sql1);
		    ResultSet rs1 = stmt.executeQuery();     
		    while(rs1.next()){
		    	list1 = rs1.getString("count");
	        }	
	        rs1.close();
	        stmt.close();
	    }catch (SQLException e) {
	    	e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch (SQLException e) {
			    }
			}
		}
	
		String serial= "";
		if(list1.equals(null)) {
			serial = "00000000";
		}else{
			String list2 = list1;
			serial = list2;
		}
		
		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%08d", serialNo);
		Regn_seq_no = prf_code + "N" + serial;
		return Regn_seq_no;
	}

	public String SaveHldgChange(String r, String eq, String fromSUS, String currentSUS, String from_prf,
			String current_prf, String fromCensusNo, String currentCensusNo, String Holding_type, String eqpt_type,
			String Tran_type, String auth_letter_no, String auth_letter_date, String op_status, int qt) {
		
		try {
			//Date date = new Date();
   		    Connection conn = null;
			conn = dataSource.getConnection();
		    String nrSql="";
		   // String nrCnd="";
		    
		    nrSql = "insert into mms_tb_regn_tr_log(regn_seq_no, eqpt_regn_no,from_sus_no,from_prf_code,from_census_no,";
		    nrSql = nrSql + " from_material_no,from_hldg_ty,from_eqpt_ty,old_value,cur_sus_no,cur_prf_code,cur_census_no,";
		    nrSql = nrSql + " cur_material_no,cur_hldg_ty,cur_eqpt_ty,new_value,tr_date,tran_type,auth_type,auth_no,auth_date,";
		    nrSql = nrSql + " regn_count,op_status,from_unit_status,cur_unit_status) ";
		    nrSql = nrSql + " values (?,?,?,?,?,'',?,?,'',?,?,?,'',?,?,'',localtimestamp,?,'IV',?,?,?,?,?,?)";
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		    stmt.setString(1, r);
		    stmt.setString(2, eq);
		    stmt.setString(3, fromSUS);
		    stmt.setString(4, from_prf);
		    stmt.setString(5, fromCensusNo);
		    stmt.setString(6, Holding_type);
		    stmt.setString(7, eqpt_type);
		    stmt.setString(8, currentSUS);
		    stmt.setString(9, current_prf);
		    stmt.setString(10, currentCensusNo);
		    stmt.setString(11, Holding_type);
		    stmt.setString(12, eqpt_type);
		    stmt.setString(13, Tran_type);
		    stmt.setString(14, auth_letter_no);
		    stmt.setString(15, auth_letter_date);
		    stmt.setInt(16, qt);
		    stmt.setString(17, op_status);
		    stmt.setString(18, "0");
		    stmt.setString(19, "0");
		    
		    stmt.executeUpdate(); 
		 	stmt.close();
    		conn.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
		return "";
	}

	public ArrayList<ArrayList<String>> mms_expire_list(String type_of_hldg, String formcodeType, String formcode,
			String p_code, String d_from, String d_to) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		    String[] d_from_ra=d_from.split("-");
		    String d_from_r=d_from_ra[0].concat(d_from_ra[1]);
		    
		    nrSql = "select distinct b.form_code_control,c.unit_name as hq_name,a.to_sus_no as sus_no,b.unit_name,prf.prf_group,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,";
		    nrSql = nrSql + "a.type_of_eqpt,t2.label as type_of_eqpt_n,a.regn_seq_no,a.eqpt_regn_no";
		    if (type_of_hldg.equalsIgnoreCase("A14")) {
		    	nrSql = nrSql + ",to_char(a.loan_expiry_date :: DATE,'dd-mm-yyyy') as expiry_date";
		    }
		    if (type_of_hldg.equalsIgnoreCase("A5")) {
		    	nrSql = nrSql + ",to_char(a.sector_expiry_date :: DATE,'dd-mm-yyyy') as expiry_date";
		    }
		    nrSql = nrSql + " from mms_tb_regn_oth_mstr a";
		    nrSql = nrSql + "  left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
		    nrSql = nrSql + "  left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
		    nrSql = nrSql + "  left join tb_miso_orbat_unt_dtl b on a.to_sus_no=b.sus_no";
		    nrSql = nrSql + "  left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
		    nrSql = nrSql + "  left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
		    nrSql = nrSql + "  left join nrv_hq c on b.form_code_control=c.form_code_control";
		    if (type_of_hldg.equalsIgnoreCase("A14")) {
		    	nrSql = nrSql + "  where a.loan_expiry_date is not null and a.loan_expiry_date::timestamp<(SELECT (date_trunc('MONTH', (?||'01')::date) + INTERVAL '1 MONTH - 1 day')::DATE) and a.type_of_hldg='A14'";
		    }
		    if (type_of_hldg.equalsIgnoreCase("A5")) {
		    	nrSql = nrSql + "  where a.sector_expiry_date is not null and a.sector_expiry_date::timestamp<(SELECT (date_trunc('MONTH', (?||'01')::date) + INTERVAL '1 MONTH - 1 day')::DATE) and a.type_of_hldg='A5'";
		    }
		    cnd="Y";
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("UNIT")){
		 			cndValue="and a.to_sus_no=?";
		 			nrSql =nrSql+cndValue;
		 			cnd="Y";
		 		} else {
		 			if(formcodeType.equalsIgnoreCase("COMMAND")){
		 				cndValue="substr(form_code_control,1,1)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		 				cndValue="substr(form_code_control,1,3)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("DIV")) {
		 				cndValue="substr(form_code_control,1,6)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("BDE")) {
		 				cndValue="substr(form_code_control,1,10)=?)";
		 			} else if(formcodeType.equalsIgnoreCase("LINE")) {
		 				cndValue=" arm_code=?)";
		 			}
		 			nrSql =nrSql +" and a.to_sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
		 			cnd="Y";
		 		}
	     	}
		 	
		 	if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
	     		nrSql =nrSql +" prf.prf_group_code=?";
	     		cnd="Y";
	     	}
		 	
		 	nrSql = nrSql + "  order by b.form_code_control,a.to_sus_no,a.census_no,a.type_of_hldg,a.type_of_eqpt";
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		         
		    int f = 1;
		    stmt.setString(f, d_from_r);
	    	f++;
		    if(formcode.length()>0){
		    	if(formcodeType.equalsIgnoreCase("UNIT")){
		 			stmt.setString(f, formcode);
		 		} else {
			    	if(formcodeType.equalsIgnoreCase("COMMAND")){
			    		stmt.setString(f, formcode.substring(0,1));
		     		}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		     			stmt.setString(f, formcode.substring(0,3));
		     		}else if(formcodeType.equalsIgnoreCase("DIV")) {
		     			stmt.setString(f, formcode.substring(0,6));
		     		}else if(formcodeType.equalsIgnoreCase("BDE")) {
		     			stmt.setString(f, formcode.substring(0,10));
		     		}else if(formcodeType.equalsIgnoreCase("LINE")) {
		     			stmt.setString(f, formcode);
		     		}
		 		}
		    	f++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(f, p_code);
		    	f++;
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
			String nrStr="";
            
			if(!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
            }else{
            	while(rs.next()){
            		ArrayList<String> list = new ArrayList<String>();
            		list.add(rs.getString("hq_name"));
            		list.add(rs.getString("sus_no"));
            		list.add(rs.getString("unit_name"));
            		list.add(rs.getString("prf_group"));
            		list.add(rs.getString("census_no"));
            		list.add(rs.getString("nomen"));
            		list.add(rs.getString("type_of_hldg_n"));
            		list.add(rs.getString("eqpt_regn_no"));
            		list.add(rs.getString("expiry_date"));
            		list.add(rs.getString("regn_seq_no"));
            		li.add(list);  
            	}
            }
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e){
    	   e.printStackTrace();
       }	
       return li;
		
	}
	
	public ArrayList<ArrayList<String>> mms_holdings_summ(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
	
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		
		    nrSql = "select distinct b.form_code_control,max(c.unit_name) as hq_name,a.sus_no,max(b.unit_name) as unit_name,m.prf_code,max(prf.prf_group) as prf_group,a.type_of_hldg,";
		    nrSql = nrSql + " max(t1.label) as type_of_hldg_n,a.type_of_eqpt,max(t2.label) as type_of_eqpt_n,sum(a.tothol) as tothol,to_char(max(upd_date),'DD-MM-YYYY') as upd_date from"; 
    		nrSql = nrSql + " (select sus_no,census_no,type_of_hldg,type_of_eqpt,tothol,upd_date from mms_tv_regn_mstr ";
		    cnd="N";
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("UNIT")){
		 			if(cnd.equalsIgnoreCase("N")){
			 			nrSql =nrSql +" where ";
		     		}else{
		     			nrSql =nrSql +" and ";
		     		}
		 			cndValue="sus_no=?";
		 			nrSql =nrSql+cndValue;
		 			cnd="Y";
		 		} else {
		 			if(formcodeType.equalsIgnoreCase("COMMAND")){
		 				cndValue="substr(form_code_control,1,1)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		 				cndValue="substr(form_code_control,1,3)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("DIV")) {
		 				cndValue="substr(form_code_control,1,6)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("BDE")) {
		 				cndValue="substr(form_code_control,1,10)=?)";
		 			} else if(formcodeType.equalsIgnoreCase("LINE")) {
		 				cndValue=" arm_code=?)";
		 			}
		 			if(cnd.equalsIgnoreCase("N")){
			 			nrSql =nrSql +" where ";
		     		}else{
		     			nrSql =nrSql +" and ";
		     		}
		 			nrSql =nrSql +" sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
		 			cnd="Y";
		 		}
	     	}
		 	
		 	if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
	     		nrSql =nrSql +" census_no in (select census_no from mms_tb_mlccs_mstr_detl where prf_code=?)";
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
		 		if(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE")){
		 			if(type_of_hldg.equalsIgnoreCase("ENGO")){
		 				nrSql =nrSql +" census_no in (select census_no from mms_tb_mlccs_mstr_detl where item_category='2')";
		 			}
		 			if(type_of_hldg.equalsIgnoreCase("ENGE")){
		 				nrSql =nrSql +" census_no in (select census_no from mms_tb_mlccs_mstr_detl where item_category='3')";
		 			}
		 			cnd="Y";		 			
		 		} else {
		 			nrSql =nrSql +" type_of_hldg=?";	
		 			cnd="Y";
		 		}
	     	}
		 	
		 	nrSql = nrSql + " ) a";
			nrSql = nrSql + " left join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and status_sus_no='Active'";
			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
			nrSql = nrSql + " left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' ";
			nrSql = nrSql + " left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'"; 
			nrSql = nrSql + " left join nrv_hq c on b.form_code_control=c.form_code_control";
			nrSql = nrSql + " group by b.form_code_control,a.sus_no,m.prf_code,type_of_hldg,type_of_eqpt";
		 	nrSql = nrSql + "  order by b.form_code_control,a.sus_no,m.prf_code,type_of_hldg,type_of_eqpt";
		 	
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		         
		    int f = 1;
		    if(formcode.length()>0){
		    	if(formcodeType.equalsIgnoreCase("UNIT")){
		 			stmt.setString(f, formcode);
		 		} else {
			    	if(formcodeType.equalsIgnoreCase("COMMAND")){
			    		stmt.setString(f, formcode.substring(0,1));
		     		}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		     			stmt.setString(f, formcode.substring(0,3));
		     		}else if(formcodeType.equalsIgnoreCase("DIV")) {
		     			stmt.setString(f, formcode.substring(0,6));
		     		}else if(formcodeType.equalsIgnoreCase("BDE")) {
		     			stmt.setString(f, formcode.substring(0,10));
		     		}else if(formcodeType.equalsIgnoreCase("LINE")) {
		     			stmt.setString(f, formcode);
		     		}
		 		}
		    	f++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(f, p_code);
		    	f++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	if(!(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE"))){
		    		stmt.setString(f, type_of_hldg);
		    	}
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
			String nrStr="";
            
			if(!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
            }else{
            	while(rs.next()){
            		ArrayList<String> list = new ArrayList<String>();
            		list.add(rs.getString("hq_name"));
            		list.add(rs.getString("sus_no"));
            		list.add(rs.getString("unit_name"));
            		list.add(rs.getString("prf_group"));
            		list.add(rs.getString("type_of_hldg_n"));
            		list.add(rs.getString("type_of_eqpt_n"));
            		list.add(rs.getString("tothol"));
            		list.add(rs.getString("upd_date"));
            		li.add(list);  
            	}
            }
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e){
    	   e.printStackTrace();
       }	
       return li;
   }
	
	
	//sana 18-11-2022
	
	
	
	public ArrayList<ArrayList<String>> mms_holdings_list_print(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		    nrSql = "select b.form_code_control,c.unit_name as hq_name,a.sus_no,b.unit_name,m.prf_code,prf.prf_group,a.census_no,m.nomen,m.cat_part_no,";
		    nrSql = nrSql + "  m.au,t3.label as au_n,m.item_status,t4.label as item_status_n,a.type_of_hldg,t1.label as type_of_hldg_n,";
		    nrSql = nrSql + "  a.type_of_eqpt,t2.label as type_of_eqpt_n,a.tothol,to_char(upd_date,'DD/MM/YYYY') as upd_date ";		
		    nrSql = nrSql + " from mms_tv_regn_mstr a";
		    nrSql = nrSql + "  left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
		    nrSql = nrSql + "  left join cue_tb_miso_prf_group_mst prf on m.prf_code=prf.prf_group_code";
		    nrSql = nrSql + "  left join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no";
		    nrSql = nrSql + "  left join nrv_hq c on b.form_code_control=c.form_code_control";
		    nrSql = nrSql + "  left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
		    nrSql = nrSql + "  left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
		    nrSql = nrSql + "  left join mms_domain_values t3 on m.au=t3.codevalue and t3.domainid='ACCOUNTINGUNITS'";
		    nrSql = nrSql + "  left join mms_domain_values t4 on m.item_status=t4.codevalue and t4.domainid='ITEMSTATUS'";
		    nrSql = nrSql + "  where b.status_sus_no='Active'";
		    cnd="Y";
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("UNIT")){
		 			cndValue="and a.sus_no=?";
		 			nrSql =nrSql+cndValue;
		 			cnd="Y";
		 		} else {
		 			if(formcodeType.equalsIgnoreCase("COMMAND")){
		 				cndValue="substr(form_code_control,1,1)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		 				cndValue="substr(form_code_control,1,3)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("DIV")) {
		 				cndValue="substr(form_code_control,1,6)=?)";
		 			}else if(formcodeType.equalsIgnoreCase("BDE")) {
		 				cndValue="substr(form_code_control,1,10)=?)";
		 			} else if(formcodeType.equalsIgnoreCase("LINE")) {
		 				cndValue=" arm_code=?)";
		 			}
		 			nrSql =nrSql +" and a.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
		 			cnd="Y";
		 		}
	     	}
		 	
		 	if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
	     		nrSql =nrSql +" prf.prf_group_code=?";
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSql =nrSql +" where ";
	     		}else{
	     			nrSql =nrSql +" and ";
	     		}
		 		if(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE")){
		 			if(type_of_hldg.equalsIgnoreCase("ENGO")){
		 				nrSql =nrSql +" m.item_category='2'";
		 			}
		 			if(type_of_hldg.equalsIgnoreCase("ENGE")){
		 				nrSql =nrSql +" m.item_category='3'";
		 			}
		 			cnd="Y";		 			
		 		} else {
		 			nrSql =nrSql +" a.type_of_hldg=?";	
		 			cnd="Y";
		 		}
	     	}
		 	
		 	nrSql = nrSql + "  order by b.form_code_control,a.sus_no,a.census_no,a.type_of_hldg,a.type_of_eqpt";
		    
		 	
		 	
		 	PreparedStatement stmt = conn.prepareStatement(nrSql);
		         
		    int f = 1;
		    if(formcode.length()>0){
		    	if(formcodeType.equalsIgnoreCase("UNIT")){
		 			stmt.setString(f, formcode);
		 		} else {
			    	if(formcodeType.equalsIgnoreCase("COMMAND")){
			    		stmt.setString(f, formcode.substring(0,1));
		     		}else if(formcodeType.equalsIgnoreCase("CORPS")) {
		     			stmt.setString(f, formcode.substring(0,3));
		     		}else if(formcodeType.equalsIgnoreCase("DIV")) {
		     			stmt.setString(f, formcode.substring(0,6));
		     		}else if(formcodeType.equalsIgnoreCase("BDE")) {
		     			stmt.setString(f, formcode.substring(0,10));
		     		}else if(formcodeType.equalsIgnoreCase("LINE")) {
		     			stmt.setString(f, formcode);
		     		}
		 		}
		    	f++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(f, p_code);
		    	f++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	/*if(!(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE"))){
		    		stmt.setString(f, type_of_hldg);
		    	}*/
		    	
		    	if(type_of_hldg.equalsIgnoreCase("ENGO") || type_of_hldg.equalsIgnoreCase("ENGE")){
		 					 			
		 		} else {
		 			stmt.setString(f, type_of_hldg);
		 		}
		    	
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
		
			if(!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
            }else{
            	while(rs.next()){
            		ArrayList<String> list = new ArrayList<String>();
            		list.add(rs.getString("hq_name"));
            		list.add(rs.getString("sus_no"));
            		list.add(rs.getString("unit_name"));
            		list.add(rs.getString("prf_group"));
            		list.add(rs.getString("census_no"));
            		list.add(rs.getString("nomen"));
            		list.add(rs.getString("type_of_hldg_n"));
            		list.add(rs.getString("tothol"));
            		list.add(rs.getString("upd_date"));
            		li.add(list);  
            	}
            }
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e){
    	   e.printStackTrace();
       }	
       return li;
   }
}