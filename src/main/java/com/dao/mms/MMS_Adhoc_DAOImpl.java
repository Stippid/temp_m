package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MMS_Adhoc_DAOImpl implements MMS_Adhoc_DAO {
	
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> mms_adhoc_query() {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			String sql = "";
			sql = ("select a.form_code_control,a.sus_no,a.unit_name,upper(b.level_in_hierarchy) as lavel from\r\n" + 
					"(select distinct form_code_control,sus_no,unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active') a \r\n" + 
					"left join (select sus_no,level_in_hierarchy from tb_miso_orbat_codesform) b on a.sus_no=b.sus_no\r\n" + 
					"order by a.form_code_control,a.sus_no\r\n");
			PreparedStatement stmt  = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("form_code_control"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("lavel"));
				aList.add(list);
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
		return aList;
	}
	
	
	
	public ArrayList<ArrayList<String>> mms_ue_uh_Hirarlist(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    
		    nrSql = "select distinct hq.cmd_name,hq.coprs_name,hq.div_name,hq.bde_name,hq.arm_code,hq.arm_desc,hq.form_code_control,r.prf_code,pr.prf_group,r.item_code,ic.item_type,r.type_of_hldg,t1.label as type_of_hldg_n,r.type_of_eqpt,t2.label as type_of_eqpt_n,r.totue,r.totuh ";
		    nrSql = nrSql + " from ( select o1.form_code_control,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt,sum(ueqty) as totue,sum(uhqty) as totuh from (";
		    nrSql = nrSql + " select a.sus_no,b.prf_code,b.item_code,a.census_no,a.type_of_hldg,a.type_of_eqpt,0 as ueqty,a.tothol as uhqty";
		    nrSql = nrSql + " from mms_tv_regn_mstr a,mms_tb_mlccs_mstr_detl b where a.census_no=b.census_no";
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){ 
		    	nrSql = nrSql + " and a.type_of_hldg = ? ";
		    }
		    
		    if(formcodeType.equalsIgnoreCase("UNIT")){ 
		    	nrSql = nrSql + " and a.sus_no=?";
		    }
		    nrSql = nrSql + " union all";
		    nrSql = nrSql + " select sus_no,prf_group_code as prf_code,item_code,'00000000' as census_no,'A0' as type_of_hldg,(case when upper(type)='CES' then '2' else '1' end) as type_of_eqpt,total_ue_qty as ueqty,0 as uhqty from mms_ue_mview";
		    if(formcodeType.equalsIgnoreCase("UNIT")){ 
			    	nrSql = nrSql + " where sus_no=?";
		    }
		    nrSql = nrSql + ") p";
		    nrSql = nrSql + " inner join tb_miso_orbat_unt_dtl o1 on p.sus_no=o1.sus_no and o1.status_sus_no='Active'";
		   
		    if(formcodeType.equalsIgnoreCase("COMMAND")){
	 			nrSql = nrSql + " and substr(form_code_control,1,1)=? ";
     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
     			nrSql = nrSql + " and substr(form_code_control,1,3)=? ";
     		}else if(formcodeType.equalsIgnoreCase("DIV")){
     			nrSql = nrSql + " and substr(form_code_control,1,6)=? ";
     		}else if(formcodeType.equalsIgnoreCase("BDE")){
     			nrSql = nrSql + " and substr(form_code_control,1,10)=? ";
     		}
		    nrSql = nrSql + " group by o1.form_code_control,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt) r";
		    nrSql = nrSql + " left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code";
		    nrSql = nrSql + " left join cue_tb_miso_mms_item_mstr ic on r.item_code=ic.item_code and softdelete='Active'";
		    nrSql = nrSql + " left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
		    nrSql = nrSql + " left join mms_domain_values t2 on r.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
		    nrSql = nrSql + " left join orbat_all_details_view hq on r.form_code_control=hq.form_code_control and hq.status_sus_no='Active'";
		    nrSql = nrSql + "  order by r.prf_code,r.item_code,r.type_of_hldg,r.type_of_eqpt";
		 	
		 	PreparedStatement stmt = conn.prepareStatement(nrSql);
		    int g1=1;
		   	if(!type_of_hldg.equalsIgnoreCase("ALL")){ 
		    	stmt.setString(g1, type_of_hldg);
		    	g1++;
		    }
	    	if(formcodeType.equalsIgnoreCase("COMMAND")){
	    		stmt.setString(g1, formcode.substring(0,1));
     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
     			stmt.setString(g1, formcode.substring(0,3));
     		}else if(formcodeType.equalsIgnoreCase("DIV")){
     			stmt.setString(g1, formcode.substring(0,6));
     		}else if(formcodeType.equalsIgnoreCase("BDE")){
     			stmt.setString(g1, formcode.substring(0,10));
     		}else if(formcodeType.equalsIgnoreCase("UNIT")){
     			stmt.setString(g1, formcode);
     			g1++;
     			stmt.setString(g1, formcode);
     		}
	    	
		    ResultSet rs = stmt.executeQuery();   
	
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cmd_name"));
					list.add(rs.getString("coprs_name"));
					list.add(rs.getString("div_name"));
					list.add(rs.getString("bde_name"));
					list.add(rs.getString("arm_code"));
					list.add(rs.getString("arm_desc"));
					list.add(rs.getString("prf_code"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("item_code")); 
					list.add(rs.getString("item_type"));  
					list.add(rs.getString("type_of_hldg"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt"));
					list.add(rs.getString("type_of_eqpt_n"));
					Double tue= Double.parseDouble(rs.getString("totue"));
		        	Double tuh=Double.parseDouble(rs.getString("totuh"));
		        	Double tdefi= tue-tuh;
					list.add(rs.getString("totue"));
					list.add(rs.getString("totuh"));
					
					if(tdefi > 0) {
						list.add(String.valueOf(tdefi));
						list.add("");
					}
					
					if(tdefi <= 0) {
						list.add("");
						list.add(String.valueOf(tdefi));
					}
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
	 
	
	
	public ArrayList<ArrayList<String>> ObsList(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		  
		    nrSql = "select a.yr,to_char(concat(a.yr,'/',a.mth,'/01')::date, 'Mon') as mth,b.cmd_name,b.coprs_name,b.div_name,b.bde_name,b.arm_desc,b.form_code_control,a.sus_no,b.unit_name,a.cert_opt1,a.cert_opt2,a.obsn1,a.obsn1_res,a.obsn1_act,t1.label as status,a.data_upd_date from mms_tb_obsn_detl a\r\n" + 
		    		"left join orbat_all_details_view b on a.sus_no=b.sus_no\r\n" + 
		    		"left join mms_domain_values t1 on a.obsn1_act=t1.codevalue and domainid='OBSNSTATUS'\r\n" + 
		    		"order by a.yr desc,a.mth desc,b.form_code_control,a.sus_no";
		 
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("COMMAND")){
		 			cndValue="substr(form_code_control,1,1)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			cndValue="substr(form_code_control,1,3)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			cndValue="substr(form_code_control,1,6)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			cndValue="substr(form_code_control,1,10)=?)";
	     		}
	        }
		 	PreparedStatement stmt = conn.prepareStatement(nrSql);
		    
		    int g1=1;
		    if(formcode.length()>0){
		    	if(formcodeType.equalsIgnoreCase("COMMAND")){
		    		stmt.setString(g1, formcode.substring(0,1));
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			stmt.setString(g1, formcode.substring(0,3));
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			stmt.setString(g1, formcode.substring(0,6));
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			stmt.setString(g1, formcode.substring(0,10));
	     		}
		    	g1++;
		    }
		    
		    
		    ResultSet rs = stmt.executeQuery();   
			
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("mth"));
					list.add(rs.getString("yr"));
					list.add(rs.getString("cmd_name"));
					list.add(rs.getString("coprs_name"));
					list.add(rs.getString("div_name"));
					list.add(rs.getString("bde_name"));
					list.add(rs.getString("arm_desc"));
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no")); 
					list.add(rs.getString("unit_name"));  
					list.add(rs.getString("cert_opt1"));
					list.add(rs.getString("cert_opt2"));
					list.add(rs.getString("obsn1"));
					list.add(rs.getString("obsn1_res"));
					list.add(rs.getString("obsn1_act")); 
					list.add(rs.getString("status"));
					list.add(rs.getString("data_upd_date"));
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
	
	
	public ArrayList<ArrayList<String>> BarrList(String type_of_hldg, String formcodeType, String formcode, String p_code,
			String d_from, String d_to) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql="";
		    String cnd="N";
		    String cndValue="";
		   
		    nrSql = "\r\n" + 
		    		"select b.unit_name as hq_name,a.sus_no,o.unit_name,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hdlg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.eqpt_regn_no,a.barrel1_detl,a.barrel2_detl,a.barrel3_detl,a.barrel4_detl,a.service_status,t3.label as service_status_n,to_char(a.data_upd_date::Date, 'DD Mon YYYY') AS data_upd_date from mms_tb_regn_mstr_detl a\r\n" + 
		    		"left join nrv_hq b on a.sus_no=b.sus_no\r\n" + 
		    		"left join tb_miso_orbat_unt_dtl o on a.sus_no=o.sus_no\r\n" + 
		    		"left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
		    		"left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'\r\n" + 
		    		"left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'\r\n" + 
		    		"left join mms_domain_values t3 on a.service_status=t3.codevalue and t3.domainid='SERVICIABILITY'\r\n" +
		    		"order by a.sus_no";
		 
		 	if(formcode.length()>0){
		 		if(formcodeType.equalsIgnoreCase("COMMAND")){
		 			cndValue="substr(form_code_control,1,1)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			cndValue="substr(form_code_control,1,3)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			cndValue="substr(form_code_control,1,6)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			cndValue="substr(form_code_control,1,10)=?)";
	     		}
	        }
		 	
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		    
		    int g1=1;
		    if(formcode.length()>0){	
		    	if(formcodeType.equalsIgnoreCase("COMMAND")){
		    		stmt.setString(g1, formcode.substring(0,1));
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			stmt.setString(g1, formcode.substring(0,3));
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			stmt.setString(g1, formcode.substring(0,6));
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			stmt.setString(g1, formcode.substring(0,10));
	     		}
		    	g1++;
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
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("type_of_hldg"));
					list.add(rs.getString("type_of_hdlg_n"));
					list.add(rs.getString("type_of_eqpt"));
					list.add(rs.getString("type_of_eqpt_n"));
					list.add(rs.getString("eqpt_regn_no")); 
					list.add(rs.getString("barrel1_detl"));  
					list.add(rs.getString("barrel2_detl"));
					list.add(rs.getString("barrel3_detl"));
					list.add(rs.getString("barrel4_detl"));
					list.add(rs.getString("service_status_n"));
					list.add(rs.getString("data_upd_date"));
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
	
	
	public ArrayList<ArrayList<String>> Hirarueuh(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control ) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String nrSqlhead="";
			String nrSqltell="";
			String nrSqlCnd="";
		    String nrSql="";
		    String nrSqlFinal="";
		    String cnd="N";
		    String cndValue="";
		    if (fl_control.equalsIgnoreCase("LIST")) {
			    nrSqlhead = "select distinct hq.cmd_name,hq.coprs_name,hq.div_name,hq.bde_name,hq.arm_code,hq.arm_desc,hq.form_code_control,r.sus_no,hq.unit_name,r.prf_code,pr.prf_group,r.item_code,ic.item_type,r.type_of_hldg,t1.label as type_of_hldg_n,r.type_of_eqpt,t2.label as type_of_eqpt_n,r.totue,r.totuh";
			    nrSqlhead = nrSqlhead + " from ( select o1.form_code_control,p.sus_no,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt,sum(ueqty) as totue,sum(uhqty) as totuh from (";
		    }
		    nrSql = " select a.sus_no,b.prf_code,b.item_code,a.census_no,a.type_of_hldg,a.type_of_eqpt,0 as ueqty,a.tothol as uhqty";
		    nrSql = nrSql + " from mms_tv_regn_mstr a,mms_tb_mlccs_mstr_detl b where a.census_no=b.census_no";
		    if(formcodeType.equalsIgnoreCase("UNIT")){ 
		    	nrSql = nrSql + " and a.sus_no=?";
		    }
		    nrSql = nrSql + " union all";
		    nrSql = nrSql + " select sus_no,prf_group_code as prf_code,item_code,'00000000' as census_no,'A0' as type_of_hldg,(case when upper(type)='CES' then '2' else '1' end) as type_of_eqpt,total_ue_qty as ueqty,0 as uhqty from mms_ue_mview";
		    
		    if(formcodeType.equalsIgnoreCase("UNIT")){ 
			    	nrSql = nrSql + " where sus_no=?";
		    }

		    nrSqltell = ") p";
		    nrSqltell = nrSqltell + " left join tb_miso_orbat_unt_dtl o1 on p.sus_no=o1.sus_no and o1.status_sus_no='Active'";
		    nrSqltell = nrSqltell + " group by o1.form_code_control,p.sus_no,p.prf_code,p.item_code,p.type_of_hldg,p.type_of_eqpt) r";
		    nrSqltell = nrSqltell + " left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code";
		    nrSqltell = nrSqltell + " left join cue_tb_miso_mms_item_mstr ic on r.item_code=ic.item_code and softdelete='Active'";
		    nrSqltell = nrSqltell + " left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
		    nrSqltell = nrSqltell + " left join mms_domain_values t2 on r.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
		    nrSqltell = nrSqltell + " left join orbat_all_details_view hq on r.sus_no=hq.sus_no and hq.status_sus_no='Active'";
		  
		 	if(formcode.length()>0 && (!formcodeType.equalsIgnoreCase("UNIT"))){
		 		if(formcodeType.equalsIgnoreCase("COMMAND")){
		 			cndValue="substr(form_code_control,1,1)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			cndValue="substr(form_code_control,1,3)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			cndValue="substr(form_code_control,1,6)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			cndValue="substr(form_code_control,1,10)=?)";
	     		}
		 		nrSqlCnd =nrSqlCnd +" where r.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
	     		cnd="Y";
	        }
		 	
		    if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
		 		nrSqlCnd =nrSqlCnd +" (pr.prf_group_code=? or r.prf_code=?)";	
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
	     		if(cnd.equalsIgnoreCase("N")){
	     			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
	     		nrSqlCnd =nrSqlCnd +" r.type_of_hldg=?";	
	     		cnd="Y";
	     	}
		 	nrSqlCnd = nrSqlCnd + "  order by r.prf_code,r.item_code,r.type_of_hldg,r.type_of_eqpt";
		 	
		 	nrSqlFinal=nrSqlhead+nrSql+nrSqltell+nrSqlCnd;
		 	
		 	PreparedStatement stmt = conn.prepareStatement(nrSqlFinal);
		    int g1=1;
		    if(formcode.length()>0){		    	
		    	if(formcodeType.equalsIgnoreCase("COMMAND")){
		    		stmt.setString(g1, formcode.substring(0,1));
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			stmt.setString(g1, formcode.substring(0,3));
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			stmt.setString(g1, formcode.substring(0,6));
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			stmt.setString(g1, formcode.substring(0,10));
	     		}else if(formcodeType.equalsIgnoreCase("UNIT")){
	     			stmt.setString(g1, formcode);
	     			g1++;
	     			stmt.setString(g1, formcode);
	     		}
		    	g1++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(g1, p_code);
		    	g1++;
		    	stmt.setString(g1, p_code);
		    	g1++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	stmt.setString(g1, type_of_hldg);
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
			
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cmd_name"));
					list.add(rs.getString("coprs_name"));
					list.add(rs.getString("div_name"));
					list.add(rs.getString("bde_name"));
					list.add(rs.getString("arm_code"));
					list.add(rs.getString("arm_desc"));
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("prf_code"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("item_code")); 
					list.add(rs.getString("item_type"));  
					list.add(rs.getString("type_of_hldg"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt"));
					list.add(rs.getString("type_of_eqpt_n"));
					Double tue=Double.parseDouble(rs.getString("totue"));
		        	Double tuh=Double.parseDouble(rs.getString("totuh"));
		        	Double tdefi= tue-tuh;
					list.add(rs.getString("totue"));
					list.add(rs.getString("totuh"));
					if(tdefi > 0) {
						list.add(String.valueOf(tdefi));
						list.add("");
					}
					if(tdefi <= 0) {
						list.add("");
						list.add(String.valueOf(tdefi));
					}
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
	
	
	
	public ArrayList<ArrayList<String>> CmdAih(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control ) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String nrSqlhead="";
			String nrSqltell="";
			String nrSqlCnd="";
		    String nrSql="";
		    String nrSqlFinal="";
		    String cnd="N";
		    String cndValue="";
	
		    nrSqltell = "select\r\n" + 
		   		"	a.prf_group,\r\n" + 
		   		"	a.census_no,\r\n" + 
		   		"	a.nomen,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '1' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS sc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '1' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS sc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '2' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS ec_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '2' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS ec_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '3' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS wc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '3' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS wc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '4' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS cc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '4' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS cc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '5' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS nc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '5' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS nc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '6' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS atc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '6' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS atc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '7' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS anc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '7' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS anc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '8' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS swc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '8' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS swc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '9' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS un_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '9' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS un_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = 'A' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS sfc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = 'A' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS sfc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '0' AND a.type_of_hldg='A0' THEN a.ue ELSE 0 END) AS mod_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '0' AND a.type_of_hldg='A0' THEN a.uh ELSE 0 END) AS mod_uh,\r\n" + 
		   		"	sum(case when a.type_of_hldg='A16' then a.uh else 0 end) as ss_uh,\r\n" + 
		   		"	sum(case when a.type_of_hldg='A14' then a.uh else 0 end) as ls_uh,\r\n" + 
		   		"	sum(case when a.type_of_hldg='A5' then a.uh else 0 end) as ac_uh,\r\n" + 
		   		"	sum(case when a.type_of_hldg like 'R%' then a.uh else 0 end) as res_uh,\r\n" + 
		   		"	sum(case when a.type_of_hldg like 'D%' then a.uh else 0 end) as dep_uh\r\n" + 
		   		"\r\n" + 
		   		"from\r\n" + 
		   		"(select\r\n" + 
		   		"	o1.formation_code as comd_code,\r\n" + 
		   		"	m.sus_no,\r\n" + 
		   		"	\r\n" + 
		   		"	d.prf_code,\r\n" + 
		   		"	d.prf_group,\r\n" + 
		   		"	m.census_no,\r\n" + 
		   		"	d.nomen,\r\n" + 
		   		"	m.type_of_hldg,\r\n" + 
		   		"	t.label as type_of_hldg_n,\r\n" + 
		   		"	sum(c.base_auth+c.mod_auth+c.foot_auth) as ue,\r\n" + 
		   		"	sum(m.tothol) as uh\r\n" + 
		   		"from mms_tv_regn_mstr m \r\n" + 
		   		"inner join mms_tb_mlccs_mstr_detl d on d.census_no=m.census_no\r\n" + 
		   		"left join sus_weapon_wepe_with_wetpet c on c.sus_no=m.sus_no\r\n" + 
		   		"left join mms_domain_values t on t.codevalue=m.type_of_hldg and t.domainid='TYPEOFHOLDING'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u on u.sus_no=m.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o1 on o1.formation_code=substr(u.form_code_control,1,1) and upper(o1.level_in_hierarchy)='COMMAND'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u1 on substr(u1.form_code_control,1,1)=o1.formation_code and u1.sus_no=o1.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o2 on o2.formation_code=substr(u.form_code_control,2,2) and upper(o2.level_in_hierarchy)='CORPS'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u2 on substr(u2.form_code_control,2,2)=o2.formation_code and u2.sus_no=o2.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o3 on o3.formation_code=substr(u.form_code_control,4,3) and upper(o3.level_in_hierarchy)='DIVISION'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u3 on substr(u3.form_code_control,4,3)=o3.formation_code and u3.sus_no=o3.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o4 on o4.formation_code=substr(u.form_code_control,7,4) and upper(o4.level_in_hierarchy)='BRIGADE'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u4 on substr(u4.form_code_control,7,4)=o4.formation_code and u4.sus_no=o4.sus_no\r\n" + 
		   		"group by 1,2,3,4,5,6,7,8 -- ,9,10,11,12,13,14,15,16\r\n" + 
		   		"\r\n" + 
		   		") a\r\n" + 
		   		"left join tb_miso_orbat_codesform c on c.formation_code=a.comd_code and upper(c.level_in_hierarchy)='COMMAND'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl b on b.sus_no=c.sus_no \r\n"; 
		   	 
		   
		   
		 	if(formcode.length()>0 && (!formcodeType.equalsIgnoreCase("UNIT"))){
		 		if(formcodeType.equalsIgnoreCase("COMMAND")){
		 			cndValue="substr(form_code_control,1,1)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			cndValue="substr(form_code_control,1,3)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			cndValue="substr(form_code_control,1,6)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			cndValue="substr(form_code_control,1,10)=?)";
	     		}
		 		nrSqlCnd =nrSqlCnd +" where b.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue + " \r\n ";
	     		cnd="Y";
	        }
		 	
		    if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
		 		nrSqlCnd =nrSqlCnd +" ( a.prf_code=?) \r\n ";	
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
	     		if(cnd.equalsIgnoreCase("N")){
	     			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
	     		nrSqlCnd =nrSqlCnd +" r.type_of_hldg=?  \r\n";	
	     		cnd="Y";
	     	}
		 
		 	
		 	nrSqlFinal=nrSqlhead+nrSql+nrSqltell+nrSqlCnd;
		    nrSqlFinal += " group by 1,2,3";
		    
		 	PreparedStatement stmt = conn.prepareStatement(nrSqlFinal);
		    
		    int g1=1;
		    if(formcode.length()>0){		    	
		    	if(formcodeType.equalsIgnoreCase("COMMAND")){
		    		stmt.setString(g1, formcode.substring(0,1));
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			stmt.setString(g1, formcode.substring(0,3));
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			stmt.setString(g1, formcode.substring(0,6));
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			stmt.setString(g1, formcode.substring(0,10));
	     		}else if(formcodeType.equalsIgnoreCase("UNIT")){
	     			stmt.setString(g1, formcode);
	     			g1++;
	     			stmt.setString(g1, formcode);
	     		}
		    	g1++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	/*stmt.setString(g1, p_code);
		    	g1++;*/
		    	stmt.setString(g1, p_code);
		    	g1++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	stmt.setString(g1, type_of_hldg);
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
			
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}
			else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("sc_ue"));
					list.add(rs.getString("sc_uh"));
					list.add(rs.getString("ec_ue"));
					list.add(rs.getString("ec_uh"));
					list.add(rs.getString("wc_ue"));
					list.add(rs.getString("wc_uh"));
					list.add(rs.getString("cc_ue"));
					list.add(rs.getString("cc_uh"));
					list.add(rs.getString("nc_ue")); 
					list.add(rs.getString("nc_uh"));  
					list.add(rs.getString("atc_ue"));
					list.add(rs.getString("atc_uh"));
					list.add(rs.getString("anc_ue"));
					list.add(rs.getString("anc_uh"));
	       			list.add(rs.getString("swc_ue"));
					list.add(rs.getString("swc_uh"));
					list.add(rs.getString("un_ue"));
					list.add(rs.getString("un_uh"));
					list.add(rs.getString("sfc_ue")); 
					list.add(rs.getString("sfc_uh"));  
					list.add(rs.getString("mod_ue"));
					list.add(rs.getString("mod_uh"));
					list.add(rs.getString("ss_uh"));
					list.add(rs.getString("ls_uh"));
					list.add(rs.getString("ac_uh"));
					list.add(rs.getString("res_uh"));
					list.add(rs.getString("dep_uh"));
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
	

  public ArrayList<ArrayList<String>> showObslist(String sus_no) {
		String ncnd="  ";
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String nrSql="";
		 
		    nrSql = "select distinct to_char(concat(a.yr,'/',a.mth,'/01')::date, 'Mon') as mth,a.yr,a.sus_no,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.obsn1,a.obsn1_res,(case when a.obsn1_act is null then '1' else '2' end) as obsn1_act,t3.label as obsn1_act_n,a.data_upd_by,a.data_upd_date from mms_tb_obsn_detl a\r\n" + 
		    		"left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
		    		"left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'\r\n" + 
		    		"left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'\r\n" + 
		    		"left join mms_domain_values t3 on a.obsn1_act=t3.codevalue and t3.domainid='OBSNSTATUS'\r\n" + 
		    		"where sus_no=?";
		
			 PreparedStatement stmt = conn.prepareStatement(nrSql);
			 stmt.setString(1, sus_no);
			 
			 ResultSet rs = stmt.executeQuery();   
			 String nrStr="";
			 int ii=0;
			 
			 if(!rs.isBeforeFirst()){	
				 ArrayList<String> list = new ArrayList<String>();
				 list.add("NIL");
				 li.add(list); 
			 }else {
				 while(rs.next()){
					 ArrayList<String> list = new ArrayList<String>();
					 list.add(rs.getString("mth"));
		             list.add(rs.getString("yr"));
		             list.add(rs.getString("sus_no"));
		             list.add(rs.getString("census_no"));
		             list.add(rs.getString("nomen"));
		             list.add(rs.getString("type_of_hldg"));
		             list.add(rs.getString("type_of_hldg_n"));
		             list.add(rs.getString("type_of_eqpt"));
		             list.add(rs.getString("type_of_eqpt_n"));
		             list.add(rs.getString("obsn1"));
		             list.add(rs.getString("obsn1_res"));
		             list.add(rs.getString("obsn1_act"));
		             list.add(rs.getString("obsn1_act_n"));
		             list.add(rs.getString("data_upd_by"));
		             list.add(rs.getString("data_upd_date"));
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
  
  public ArrayList<ArrayList<String>> wastreportlist() {
		String ncnd="  ";
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String nrSql="";
	 
		    nrSql = "\r\n" + 
		    		"select\r\n" + 
		    		"	census_no,\r\n" + 
		    		"	nomen,\r\n" + 
		    		"	au,\r\n" + 
		    		"	sum(w_normal) as normal,\r\n" + 
		    		"	sum(w_bc) as battle_cas,\r\n" + 
		    		"	sum(w_oth) as others,\r\n" + 
		    		"	sum(w_depot) as depot\r\n" + 
		    		"from \r\n" + 
		    		"(SELECT\r\n" + 
		    		"	a.census_no,\r\n" + 
		    		"	d.nomen,\r\n" + 
		    		"	t1.label as au,\r\n" + 
		    		"	(case when a.d_type='UH' then a.tothol else 0 end) as w_normal,\r\n" + 
		    		"	(case when a.d_type in ('UH') then 0 else 0 end) as w_bc,\r\n" + 
		    		"	(case when a.d_type='UH' then 0 else 0 end) as w_oth,\r\n" + 
		    		"	(case when a.d_type='DH' then a.tothol else 0 end) as w_depot\r\n" + 
		    		"FROM \r\n" + 
		    		"(select * \r\n" + 
		    		"from mms_tv_regn_mstr \r\n" + 
		    		"where type_of_hldg='G0') a\r\n" + 
		    		"left join mms_tb_mlccs_mstr_detl d on d.census_no=a.census_no\r\n" + 
		    		"left join mms_domain_values t1 on d.au=t1.codevalue and t1.domainid='ACCOUNTINGUNITS'\r\n" + 
		    		") as foo\r\n" + 
		    		"group by 1,2,3";
		     
			 PreparedStatement stmt = conn.prepareStatement(nrSql); 
		
			 ResultSet rs = stmt.executeQuery();   
			 String nrStr="";
			 int ii=0;
			 
			 if(!rs.isBeforeFirst()){	
				 ArrayList<String> list = new ArrayList<String>();
				 list.add("NIL");
				 li.add(list); 
			 }else {
				 while(rs.next()){
					 ArrayList<String> list = new ArrayList<String>();
					 list.add(rs.getString("census_no"));
		             list.add(rs.getString("nomen"));
		             list.add(rs.getString("au"));
		             list.add(rs.getString("normal"));
		             list.add(rs.getString("battle_cas"));
		             list.add(rs.getString("others"));
		             list.add(rs.getString("depot"));
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
  
  
  public ArrayList<ArrayList<String>> CmdTypewiseHld(String type_of_hldg, String formcodeType, String formcode, String p_code,String p_i_code,
			String d_from, String d_to, String fl_control ) {
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String nrSqlhead="";
			String nrSqltell="";
			String nrSqlCnd="";
		    String nrSql="";
		    String nrSqlFinal="";
		    String cnd="N";
		    String cndValue="";
	
		    nrSqltell = "select\r\n" + 
		   		"	a.prf_group,\r\n" + 
		   		"	a.census_no,\r\n" + 
		   		"	a.nomen,\r\n" + 
		   		"	a.type_of_hldg_n,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '1' THEN ue ELSE 0 END) AS sc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '1' THEN uh ELSE 0 END) AS sc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '2' THEN ue ELSE 0 END) AS ec_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '2' THEN uh ELSE 0 END) AS ec_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '3' THEN ue ELSE 0 END) AS wc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '3' THEN uh ELSE 0 END) AS wc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '4' THEN ue ELSE 0 END) AS cc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '4' THEN uh ELSE 0 END) AS cc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '5' THEN ue ELSE 0 END) AS nc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '5' THEN uh ELSE 0 END) AS nc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '6' THEN ue ELSE 0 END) AS atc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '6' THEN uh ELSE 0 END) AS atc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '7' THEN ue ELSE 0 END) AS anc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '7' THEN uh ELSE 0 END) AS anc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '8' THEN ue ELSE 0 END) AS swc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '8' THEN uh ELSE 0 END) AS swc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '9' THEN ue ELSE 0 END) AS un_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '9' THEN uh ELSE 0 END) AS un_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = 'A' THEN ue ELSE 0 END) AS sfc_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = 'A' THEN uh ELSE 0 END) AS sfc_uh,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '0' THEN ue ELSE 0 END) AS mod_ue,\r\n" + 
		   		"	sum( CASE WHEN substr(b.form_code_control,1,1) = '0' THEN uh ELSE 0 END) AS mod_uh\r\n" + 
		   		"from\r\n" + 
		   		"(select\r\n" + 
		   		"	o1.formation_code as comd_code,\r\n" + 
		   		"	m.sus_no,\r\n" + 
		   		"	u.unit_name,\r\n" + 
		   		"	o1.sus_no as comd_sus,\r\n" + 
		   		"	u1.unit_name as comd_name,\r\n" + 
		   		"	o2.sus_no as corps_sus,\r\n" + 
		   		"	u2.unit_name as corps_name,\r\n" + 
		   		"	o3.sus_no as div_sus,\r\n" + 
		   		"	u3.unit_name as div_name,\r\n" + 
		   		"	o4.sus_no as bde_sus,\r\n" + 
		   		"	u4.unit_name as bde_name,\r\n" + 
		   		"	d.prf_code,\r\n" + 
		   		"	d.prf_group,\r\n" + 
		   		"	m.census_no,\r\n" + 
		   		"	d.nomen,\r\n" + 
		   		"	m.type_of_hldg,\r\n" + 
		   		"	t.label as type_of_hldg_n,\r\n" + 
		   		"	c.total_ue_qty as ue,\r\n" + 
		   		"	sum(m.tothol) as uh\r\n" + 
		   		"from mms_tv_regn_mstr m \r\n" + 
		   		"inner join mms_tb_mlccs_mstr_detl d on d.census_no=m.census_no\r\n" + 
		   		"left join mms_ue_mview c on c.sus_no=m.sus_no and c.prf_group_code=d.prf_code and c.item_code=d.item_code\r\n" + 
		   		"left join mms_domain_values t on m.type_of_hldg=t.codevalue and t.domainid='TYPEOFHOLDING'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u on u.sus_no=m.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o1 on o1.formation_code=substr(u.form_code_control,1,1) and upper(o1.level_in_hierarchy)='COMMAND'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u1 on substr(u1.form_code_control,1,1)=o1.formation_code and u1.sus_no=o1.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o2 on o2.formation_code=substr(u.form_code_control,2,2) and upper(o2.level_in_hierarchy)='CORPS'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u2 on substr(u2.form_code_control,2,2)=o2.formation_code and u2.sus_no=o2.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o3 on o3.formation_code=substr(u.form_code_control,4,3) and upper(o3.level_in_hierarchy)='DIVISION'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u3 on substr(u3.form_code_control,4,3)=o3.formation_code and u3.sus_no=o3.sus_no\r\n" + 
		   		"left join tb_miso_orbat_codesform o4 on o4.formation_code=substr(u.form_code_control,7,4) and upper(o4.level_in_hierarchy)='BRIGADE'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl u4 on substr(u4.form_code_control,7,4)=o4.formation_code and u4.sus_no=o4.sus_no\r\n" + 
		   		"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18) a\r\n" + 
		   		"left join tb_miso_orbat_codesform c on c.formation_code=a.comd_code and upper(c.level_in_hierarchy)='COMMAND'\r\n" + 
		   		"left join tb_miso_orbat_unt_dtl b on b.sus_no=c.sus_no\r\n" + 
		   		"group by 1,2,3,4\r\n" + 
		   		"\r\n" + 
		   		"";
		 
		 	if(formcode.length()>0 && (!formcodeType.equalsIgnoreCase("UNIT"))){
		 		if(formcodeType.equalsIgnoreCase("COMMAND")){
		 			cndValue="substr(form_code_control,1,1)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			cndValue="substr(form_code_control,1,3)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			cndValue="substr(form_code_control,1,6)=?)";
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			cndValue="substr(form_code_control,1,10)=?)";
	     		}
		 		nrSqlCnd =nrSqlCnd +" where r.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "+cndValue;
	     		cnd="Y";
	        }
		 	
		    if(!p_code.equalsIgnoreCase("ALL")){
		 		if(cnd.equalsIgnoreCase("N")){
		 			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
		 		nrSqlCnd =nrSqlCnd +" (pr.prf_group_code=? or a.prf_code=?)";	
	     		cnd="Y";
	     	}
		 	
		 	if(!type_of_hldg.equalsIgnoreCase("ALL")){
	     		if(cnd.equalsIgnoreCase("N")){
	     			nrSqlCnd =nrSqlCnd +" where ";
	     		}else{
	     			nrSqlCnd =nrSqlCnd +" and ";
	     		}
	     		nrSqlCnd =nrSqlCnd +" r.type_of_hldg=?";	
	     		cnd="Y";
	     	}
		 	nrSqlFinal=nrSqlhead+nrSql+nrSqltell+nrSqlCnd;
	
		    PreparedStatement stmt = conn.prepareStatement(nrSqlFinal);
		    
		    int g1=1;
		    if(formcode.length()>0){		    	
		    	if(formcodeType.equalsIgnoreCase("COMMAND")){
		    		stmt.setString(g1, formcode.substring(0,1));
	     		}else if(formcodeType.equalsIgnoreCase("CORPS")){
	     			stmt.setString(g1, formcode.substring(0,3));
	     		}else if(formcodeType.equalsIgnoreCase("DIV")){
	     			stmt.setString(g1, formcode.substring(0,6));
	     		}else if(formcodeType.equalsIgnoreCase("BDE")){
	     			stmt.setString(g1, formcode.substring(0,10));
	     		}else if(formcodeType.equalsIgnoreCase("UNIT")){
	     			stmt.setString(g1, formcode);
	     			g1++;
	     			stmt.setString(g1, formcode);
	     		}
		    	g1++;
		    }
		    
		    if(!p_code.equalsIgnoreCase("ALL")){
		    	stmt.setString(g1, p_code);
		    	g1++;
		    	stmt.setString(g1, p_code);
		    	g1++;
		    }
		    
		    if(!type_of_hldg.equalsIgnoreCase("ALL")){
		    	stmt.setString(g1, type_of_hldg);
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
		
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}
			else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("sc_ue"));
					list.add(rs.getString("sc_uh"));
					list.add(rs.getString("ec_ue"));
					list.add(rs.getString("ec_uh"));
					list.add(rs.getString("wc_ue"));
					list.add(rs.getString("wc_uh"));
					list.add(rs.getString("cc_ue"));
					list.add(rs.getString("cc_uh"));
					list.add(rs.getString("nc_ue")); 
					list.add(rs.getString("nc_uh"));  
					list.add(rs.getString("atc_ue"));
					list.add(rs.getString("atc_uh"));
					list.add(rs.getString("anc_ue"));
					list.add(rs.getString("anc_uh"));
					list.add(rs.getString("swc_ue"));
					list.add(rs.getString("swc_uh"));
					list.add(rs.getString("un_ue"));
					list.add(rs.getString("un_uh"));
					list.add(rs.getString("sfc_ue")); 
					list.add(rs.getString("sfc_uh"));  
					list.add(rs.getString("mod_ue"));
					list.add(rs.getString("mod_uh"));
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
