package com.dao.mms;

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

public class MMS_ADMIN_DAOImpl implements MMS_ADMIN_DAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> UnitMcrStatusList(String deo, String stat, String mnth,String unit_name1,String unit_sus_no1,String cont_comd1,String cont_corps1,String cont_div1,String cont_bde1) {
		
		String whr="";
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();	
			
			
			if(!deo.equalsIgnoreCase("ALL") && !deo.equals("")){ 
				whr=whr+" and b.oper=?";
			}
			if(!stat.equalsIgnoreCase("ALL")){ 
				if(stat.equalsIgnoreCase("0")){
					whr=whr+" and p.status is null ";
				}
			    
				if (stat.equalsIgnoreCase("1")){
				    whr=whr+" and p.status ='APP' ";
				}
			}
			if( !unit_name1.equals("")) {				
				whr += " and  upper(o.unit_name) like ? ";					
		}						
		if( !unit_sus_no1.equals("")) {			
				whr += " and o.sus_no = ? ";					
		}	
			if( !cont_comd1.equals("")) {			
					whr += " and  o.form_code_control like ?  ";						
			}
			if( !cont_corps1.equals("0")) {				
					whr += " and  o.form_code_control like ?  ";				
			}
			if( !cont_div1.equals("0")) {				
					whr += " and  o.form_code_control like ?  ";				
			}
			if( !cont_bde1.equals("0")) {				
					whr += " and  o.form_code_control like ?  ";				
			}			
								
			
			String sql ="select \r\n" + 
					"	DISTINCT o.sus_no,o.unit_name,\r\n" + 
					"	fmn.unit_name as fmn_name, \r\n" + 
					"	case when p.status='APP' then p.status else 'DEF' end  as unit_status,\r\n" + 
					"	obsn.tot_o,obsn.totpend,obsn.totresl,\r\n" + 
					"	b.oper as deo,\r\n" +
					"	last_update.upd_date \r\n"+
					"from tb_miso_orbat_unt_dtl o  \r\n" + 
					"inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n" + 
					"left join all_fmn_view fmn on o.form_code_control = fmn.form_code_control\r\n" + 
					"left join baseunits b on o.sus_no=b.b_sus_no \r\n" + 
					"left join (select distinct sus_no,'APP' as status from mms_tb_unit_upload_document\r\n" + 
					"			where TO_CHAR(created_on,'yyyy-mm') = ? and doc is null\r\n" + 
					"			group by 1) p on o.sus_no=p.sus_no \r\n" + 
					"left join (select sus_no,count(*) as tot_o,\r\n" + 
					"			count(id) filter(where miso_reply is not null) as totresl,\r\n" + 
					"			count(id) filter(where miso_reply is null) as totpend\r\n" + 
					"			from mms_tb_unit_upload_document\r\n" + 
					"			where TO_CHAR(created_on,'yyyy-mm') = ? and doc is not null\r\n" + 
					"			group by 1) as obsn on o.sus_no=obsn.sus_no \r\n" + 
					"left join (select sus_no,TO_CHAR(max(created_on),'dd-mm-yyyy') as upd_date\r\n" + 
					"			from mms_tb_unit_upload_document\r\n" + 
					"			group by 1) last_update on o.sus_no=last_update.sus_no \r\n" +
					"where o.status_sus_no='Active' "+whr+" \r\n" + 
					"order by obsn.totpend";
			
			 
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 int g1=1;	
			 stmt.setString(g1, mnth);
			 g1++;
			 stmt.setString(g1, mnth);
			 if(!deo.equalsIgnoreCase("ALL") && !deo.equals("")){ 
				 g1++;
				 stmt.setString(g1, deo);
			 }
			 if(!unit_name1.equals("")) {
					g1++;
					stmt.setString(g1, unit_name1.toUpperCase());
					
				}
				if(!unit_sus_no1.equals("")) {
					 g1++;
					stmt.setString(g1, unit_sus_no1.toUpperCase());
					
				}
			 if(!cont_comd1.equals("")) {
				 g1++;
					stmt.setString(g1, cont_comd1.toUpperCase()+"%");
					
				}
				if(!cont_corps1.equals("0")) {
					 g1++;
					stmt.setString(g1, cont_corps1.toUpperCase()+"%");
					
				}
				if(!cont_div1.equals("0")) {
					 g1++;
					stmt.setString(g1, cont_div1.toUpperCase()+"%");
					
				}
				if(!cont_bde1.equals("0")) {
					 g1++;
					stmt.setString(g1, cont_bde1.toUpperCase()+"%");
					
				}
				
			
			 ResultSet rs = stmt.executeQuery();   
			 if(!rs.isBeforeFirst()){	
				 ArrayList<String> list = new ArrayList<String>();
				 list.add("NIL");
				 li.add(list); 
			 }else {
				 while(rs.next()){
					 ArrayList<String> list = new ArrayList<String>();
					 list.add(rs.getString("deo"));
					 list.add(rs.getString("fmn_name"));
					 list.add(rs.getString("sus_no"));
		             list.add(rs.getString("unit_name"));
		             list.add(rs.getString("unit_status"));
		             list.add(rs.getString("tot_o"));
		             list.add(rs.getString("totpend"));
		             list.add(rs.getString("totresl"));
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

	public ArrayList<ArrayList<String>> UnitObsnStatusList(String deo, String stat, String mnth,String m7_sus){
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String[] m = mnth.split("-");
		String mth = m[1];
		String yr = m[0];
		String ncnd="";
		 
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();	
			if(!deo.equalsIgnoreCase("ALL")){ 
				ncnd=ncnd+" and b.oper=?";
			}				   
			if(!stat.equalsIgnoreCase("ALL")){ 
				//ncnd=ncnd+" and ";
				if(stat.equalsIgnoreCase("1")){
					ncnd=ncnd+" and a.cert_opt1 = 'Y' ";
				}
				if(stat.equalsIgnoreCase("2")){
					ncnd=ncnd+" and a.cert_opt2 = 'Y' ";
				}
		    }
			if(!m7_sus.equalsIgnoreCase("")){ 
				ncnd=ncnd+" and ";
				ncnd=ncnd+" a.sus_no =?";
		    }
				
			String SqlQry="";
			/*SqlQry = "select distinct a.data_upd_date,a.mth,a.yr,b.oper as deo,a.sus_no,o.unit_name,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,";
			SqlQry = SqlQry + "a.type_of_eqpt,t2.label as type_of_eqpt_n,a.cert_opt1,a.cert_opt2,a.obsn1,a.obsn1_res,a.obsn1_act,";
	        SqlQry = SqlQry + "a.latest,a.obsn_status,t3.label as obsn_status_n,tr_id from mms_tb_obsn_detl a";
	        SqlQry = SqlQry + " left join tb_miso_orbat_unt_dtl o on a.sus_no=o.sus_no and o.status_sus_no='Active'";
	        SqlQry = SqlQry + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
	        SqlQry = SqlQry + " left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
	        SqlQry = SqlQry + " left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
	        SqlQry = SqlQry + " left join baseunits b on a.sus_no=b.b_sus_no";
	        SqlQry = SqlQry + " left join mms_domain_values t3 on a.obsn1_act=t3.codevalue and t3.domainid='OBSNSTATUS'";
	        SqlQry = SqlQry + " where mth=? and yr=? ";*/
			
			SqlQry = "select b.oper as deo,\r\n" + 
					"		a.sus_no,o.unit_name,\r\n" + 
					"		a.census_no,m.nomen,\r\n" + 
					"		t1.label as type_of_hldg_n,\r\n" + 
					"		t2.label as type_of_eqpt_n,\r\n" + 
					"		t3.label as obsn_status_n,\r\n" + 
					"		tr_id,\r\n" + 
					"		a.data_upd_date,\r\n" + 
					"		case when a.unit_remarks != '' then a.unit_remarks else '-' end as unit_remarks,\r\n" + 
					"		case when a.unit_upload_document != '' then '1' else '-' end as unit_upload_document\r\n" + 
					"		from mms_tb_obsn_detl a \r\n" + 
					"		left join tb_miso_orbat_unt_dtl o on a.sus_no=o.sus_no and o.status_sus_no='Active' \r\n" + 
					"		left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n" + 
					"		left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n" + 
					"		left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \r\n" + 
					"		left join baseunits b on a.sus_no=b.b_sus_no \r\n" + 
					"		left join mms_domain_values t3 on a.obsn1_act=t3.codevalue and t3.domainid='OBSNSTATUS' \r\n" + 
					"		where mth= ? and yr= ? ";
			
			     
	        if(ncnd.length()>0){
	        	SqlQry = SqlQry + ncnd;
			}
	        
	        SqlQry = SqlQry + " order by a.data_upd_date desc";
			   
			PreparedStatement stmt = conn.prepareStatement(SqlQry);
		
			
			int g1=1;
			stmt.setString(g1, mth);
			g1++;
			stmt.setString(g1, yr);
			g1++;
			if(!deo.equalsIgnoreCase("ALL")){ 
				stmt.setString(g1, deo);
				g1++;
			}
			if(!m7_sus.equalsIgnoreCase("")){ 
				stmt.setString(g1, m7_sus);
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
		    		list.add(rs.getString("deo"));
		    		list.add(rs.getString("sus_no"));
		    		list.add(rs.getString("unit_name"));
		    		list.add(rs.getString("census_no"));
		    		list.add(rs.getString("nomen"));
		    		list.add(rs.getString("type_of_hldg_n"));
		    		list.add(rs.getString("type_of_eqpt_n"));
		    		list.add(rs.getString("obsn_status_n"));
		    		list.add(rs.getString("tr_id"));
		    		list.add(rs.getString("data_upd_date"));
		    		list.add(rs.getString("unit_remarks"));
		    		list.add(rs.getString("unit_upload_document"));
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

	public ArrayList<ArrayList<String>> GetObsnData(int id) {
		Connection conn = null;
		String qry = "";
		String nrStr=""; 
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try{
			conn = dataSource.getConnection();
			qry ="select a.sus_no,(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no=a.sus_no),a.census_no,(select distinct nomen from mms_tb_mlccs_mstr_detl where census_no=a.census_no),";
			qry = qry + "a.type_of_hldg,(select distinct label from mms_domain_values where codevalue=a.type_of_hldg and domainid='TYPEOFHOLDING') as type_of_hldg_n,";
			qry = qry + "a.type_of_eqpt,(select distinct label from mms_domain_values where codevalue=a.type_of_eqpt and domainid='TYPEOFEQPT') as type_of_eqpt_n,";
			qry = qry + "a.obsn1,a.obsn1_res,";
			qry = qry + "a.obsn1_act from mms_tb_obsn_detl a where tr_id=?";
			
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();  
			
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt_n"));
					list.add(rs.getString("obsn1"));
					list.add(rs.getString("obsn1_res"));
					list.add(rs.getString("obsn1_act"));
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

	public ArrayList<ArrayList<String>> getallotedDEOList(String deo) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String ncnd="";
		try{	  
			conn = dataSource.getConnection();
			String nrSql="";
			
			if(!deo.equalsIgnoreCase("ALL")){ 
				if(ncnd.length()<=0){
					ncnd=ncnd+"Where b.oper=?";						
				}			   
			}
				
		    nrSql = "select (case when a.form_code_control is null then '0000000000' else a.form_code_control end) as form_code_control,c.unit_name as hq_name,";
		    nrSql = nrSql + " (case when a.sus_no is null then b.b_sus_no else a.sus_no end) as sus_no, (case when a.unit_name is null then b.b_unit_name else a.unit_name end) as unit_name,a.status_sus_no,b.b_reqd,b.oper,m.codevalue as code from ";
		    nrSql = nrSql + " (select form_code_control,sus_no,unit_name,status_sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active')a full outer join (select b_sus_no,b_unit_name,b_reqd,oper from baseunits) b on a.sus_no=b_sus_no";
		    nrSql = nrSql + " left join nrv_hq c on a.form_code_control=c.form_code_control";
		    nrSql = nrSql + " left join (select  domainid ,  codevalue ,  label from mms_domain_values where  domainid ='MMSDEO') m on b.oper = m.codevalue ";
		    
		    if (ncnd.length()>0) {
		    	 nrSql = nrSql + ncnd;
		    }
			
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		    
		    if(!deo.equalsIgnoreCase("ALL")){ 
		    	stmt.setString(1, deo);	
		    }
		    
		    ResultSet rs = stmt.executeQuery();   
			String nrStr="";
			int ii=0;
			if(!rs.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else {
				while(rs.next()){
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("hq_name"));
	            	list.add(rs.getString("sus_no"));
	            	list.add(rs.getString("unit_name"));
	            	list.add(rs.getString("status_sus_no"));
	            	list.add(rs.getString("b_reqd"));
	            	list.add(rs.getString("code"));
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

	public String mms_update_deoandeqptreqd(String b_reqd, String oper, String b_sus_no) {
		
		Connection conn = null; 
		int rs2 = 0;
		try{
			conn = dataSource.getConnection();
	        String nrSql1=""; 
	        nrSql1 = "update baseunits set b_reqd=?, oper=?";
	        nrSql1 = nrSql1+ " where b_sus_no in(?)";
	        PreparedStatement stmt = conn.prepareStatement(nrSql1);
	        
	        stmt.setString(1, b_reqd);
	        stmt.setString(2, oper);
	        stmt.setString(3, b_sus_no);
	        rs2 = stmt.executeUpdate();  
	        stmt.close();
	        conn.close();
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }	 
	    return rs2+"";
	}

	//Using DataTable With MockJax Report for SQL
	public List<Map<String, Object>> mms_unit_all_obsn_statusList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String sus_no,String month,String yr,String status) {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,sus_no,month,yr,status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "SELECT u.unit_name,d.doc,d.id,d.remark,TO_CHAR(d.created_on, 'dd-mm-yyyy') as created_on,coalesce(d.miso_reply,'') as miso_reply,TO_CHAR(d.dt_completion, 'dd-mm-yyyy') as dt_completion,completion_by \r\n" + 
					"	FROM public.mms_tb_unit_upload_document d\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl u on d.sus_no=u.sus_no and u.status_sus_no='Active'\r\n" + 
					"	where d.doc is not null " +SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,sus_no,month,yr,status);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if(metaData.getColumnLabel(i).equals("id")) {
						columns.put("doc","<a href='#' onclick='getDownloadUnitDoc("+rs.getObject(i)+")' class='btn btn-primary btn-sm'><i class='fa fa-download' aria-hidden='true'></i></a>" );
						columns.put(metaData.getColumnLabel(i),rs.getObject(i));
					}else if(metaData.getColumnLabel(i).equals("miso_reply")){
						String miso_reply = rs.getObject(i).toString();
						if (miso_reply.equals("")) {
		            		String input = "<textarea id='miso_reply"+rs.getObject(3)+"' name='miso_reply"+rs.getObject(3)+"' maxlength='250' placeholder='MISO Reply' class='form-control-sm form-control' rows='1' style='width:80%;display: inline;'>"
		            				+ "</textarea>&ensp;<button id='replyBtn' class='btn btn-success btn-sm' onclick='miso_reply("+rs.getObject(3)+");' style='border-radius: 5px;vertical-align: top;'>Reply</button>";
		            		columns.put(metaData.getColumnLabel(i), input);
		            	}else {
		            		columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		            	}
					}else {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
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
	
	public long mms_unit_all_obsn_statusCount(String Search,String sus_no,String month,String yr,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,sus_no,month,yr,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="SELECT count(*) \r\n" + 
					"	FROM public.mms_tb_unit_upload_document d\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl u on d.sus_no=u.sus_no and u.status_sus_no='Active'\r\n" + 
					"	where d.doc is not null " +SearchValue;
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,sus_no,month,yr,status);
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
	
	public String GenerateQueryWhereClause_SQL(String Search,String sus_no,String month,String yr,String status) {
		String SearchValue ="";
		if(!Search.equals("")) { // for Input Filter
			SearchValue +=" and ( lower(u.unit_name) like ? or lower(d.remark) like ? or lower(d.miso_reply) like ? or TO_CHAR(d.created_on, 'dd-mm-yyyy') like ? ) ";
		}
		if(!sus_no.equals("")) {
			SearchValue +=" and lower(d.sus_no) like ? ";
		}
		if(!month.equals("") && !yr.equals("")) {
			SearchValue +=" and TO_CHAR(d.created_on, 'mm') =? and TO_CHAR(d.created_on, 'yyyy') = ? ";
		}
		if(!status.equals("")) {
			if(status.equals("COMPLETED")) {
				SearchValue +=" and d.miso_reply is not null ";
			}
			if(status.equals("PENDING")) {
				SearchValue +=" and d.miso_reply is null ";
			}
		}
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String sus_no,String month,String yr,String status) {
		int flag = 0;
		try {
			Search = Search.toLowerCase();
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search+"%");
				flag += 1;
				stmt.setString(flag, Search+"%");
			}
			if(!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no+"%");
			}
			if(!month.equals("") && !yr.equals("")) {
				flag += 1;
				stmt.setString(flag, month);
				flag += 1;
				stmt.setString(flag, yr);
			}
				
		}catch (Exception e) {}
		return stmt;		
	}
	//Using DataTable With MockJax Report for SQL
	
	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
}
