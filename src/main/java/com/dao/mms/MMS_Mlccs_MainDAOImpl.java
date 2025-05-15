package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MMS_Mlccs_MainDAOImpl implements MMS_Mlccs_MainDAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> MlccsView(String nParaValue, String nPara, String domainid,String m1_class_category) { 
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	nParaValue=nParaValue.toUpperCase();
    	Connection conn = null;
    	
		try{	  
			conn = dataSource.getConnection();
		    String sql1="";
		    sql1 = " select p.coss_section,a.census_no,a.nomen,a.cat_part_no,a.prf_code,p.prf_group,a.item_code,"
		    		+ " t4.label as class_category,i.item_type as itemnomen,"; 
	        sql1 = sql1 + " t1.label AS AU,t2.label as item_status,t3.label as opstatus from mms_tb_mlccs_mstr_detl a";
	        sql1 = sql1 + " left join cue_tb_miso_prf_group_mst p on cast(a.prf_code as varchar)=p.prf_group_code"; 
	        sql1 = sql1 + " left join cue_tb_miso_mms_item_mstr i on a.item_code=i.item_code";
	        sql1 = sql1 + " left join mms_domain_values t1 on a.au=t1.codevalue and t1.domainid='ACCOUNTINGUNITS'";
	        sql1 = sql1 + " left join mms_domain_values t2 on a.item_status=t2.codevalue and t2.domainid='ITEMSTATUS'";
	        sql1 = sql1 + " left join mms_domain_values t3 on a.op_status=t3.codevalue and t3.domainid='STATUS'"
	        		+ "left join  mms_domain_values t4 on a.class_category=t4.codevalue  and t4.domainid='MMSCLASSA' ";

	        if (!(domainid.equalsIgnoreCase("ACSFP") || domainid.equalsIgnoreCase("ENGR"))) {
			    if(nParaValue!=""){			     	
			    	sql1 =sql1 + " where ";
			     	if(domainid.equalsIgnoreCase("NOM")){
			     		sql1 =sql1 + " a.nomen like ?";
			     	}else if(domainid.equalsIgnoreCase("CEN")){
			     		sql1 =sql1 + " a.census_no like ?";
			     	}else if(domainid.equalsIgnoreCase("PRF")){
			     		sql1 =sql1 + " p.prf_group like ?";
			     	}else if(domainid.equalsIgnoreCase("COS")){
			     		sql1 =sql1 + " p.coss_section like ?";
			     	}else if(domainid.equalsIgnoreCase("CAT")){
			     		sql1 =sql1 + " a.cat_part_no like ?";
			     	}else if (domainid.equalsIgnoreCase("STAT")){
			     		sql1 =sql1 + " t2.label like ?";
			     	}
			    }
	        } else {
	        	sql1 =sql1 + " where ";
	        	if (domainid.equalsIgnoreCase("ENGR")){
		     		sql1 =sql1 + " a.item_category='2'";
		     	}else if (domainid.equalsIgnoreCase("ACSFP")){
		     		sql1 =sql1 + " a.item_category='1'";
		     	}
	        }
	        if (!(m1_class_category.equals(""))) {
	        	sql1 =sql1 + " and a.class_category = ? ";
	        }
		    sql1 =sql1 + " order by p.coss_section,p.prf_group,a.item_code,a.census_no";
		    PreparedStatement stmt = conn.prepareStatement(sql1);
		    if (!(domainid.equalsIgnoreCase("ACSFP") || domainid.equalsIgnoreCase("ENGR"))) { 
		    	if(nParaValue!=""){	
		    		stmt.setString(1, "%"+nParaValue.toUpperCase()+"%");
		    	}
		    }
		    if (!(m1_class_category.equals(""))) {
		    	stmt.setString(2, m1_class_category);
	        }
		    System.out.println(stmt+"==============");
		    ResultSet rs = stmt.executeQuery();   
		   
		    if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list); 
		    }else{
		    	while(rs.next()){
     	    		ArrayList<String> list2 = new ArrayList<String>();
     	    		list2.add(rs.getString("coss_section"));
     	        	list2.add(rs.getString("prf_group"));
     	        	list2.add(rs.getString("census_no"));
     	        	list2.add(rs.getString("nomen"));   
     	        	list2.add(rs.getString("cat_part_no"));  
     	        	list2.add(rs.getString("au"));
     	        	list2.add(rs.getString("item_status"));
     	        	list2.add(rs.getString("item_code"));
     	        	list2.add(rs.getString("itemnomen"));
     	        	list2.add(rs.getString("class_category"));   
     			    li.add(list2);  
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
	
	public ArrayList<ArrayList<String>> NewReqEqptAppList(String q) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
			String sql1="";
			
			sql1 = "select a.id,to_char(a.data_cr_date,'dd-mm-yyyy') as data_cr_dat,a.req_agency,a.prf_code,p.prf_group,a.cat_part_no,a.nomen,a.op_status,a.reject_remarks,t1.label as op_status_n,a.alot_census_no from mms_tb_mlccs_new_req a left join cue_tb_miso_prf_group_mst p on a.prf_code=p.prf_group_code left join mms_domain_values t1 on a.op_status=t1.codevalue where t1.domainid='MMSSTATUS' order by data_cr_date desc";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();   
		    
		    if(!rs.isBeforeFirst()) {	
		    	ArrayList<String> list = new ArrayList<String>();
		    	list.add("NIL");
		    	li.add(list); 
		    }else{
		    	while(rs.next()){
		    		ArrayList<String> list2 = new ArrayList<String>();
		    		list2.add(rs.getString("id"));
	 	        	list2.add(rs.getString("data_cr_dat"));   
	 	        	list2.add(rs.getString("req_agency"));  
	 	        	list2.add(rs.getString("prf_group"));
	 	        	list2.add(rs.getString("cat_part_no"));  
	 	        	list2.add(rs.getString("nomen"));  
	 	        	list2.add(rs.getString("op_status_n"));
	 	        	list2.add(rs.getString("reject_remarks")); 
	 	        	list2.add(rs.getString("alot_census_no"));
	 	        	list2.add(rs.getString("prf_code")); 
		    		li.add(list2);  
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

	public List<String>   updatereject_mms_new_eqpt_qry(String remarks,String letter_no,Integer id){
		Connection conn = null;
    	List<String> list2 = new ArrayList<String>();
		Integer out=0;
		try{	  
			conn = dataSource.getConnection(); 
			String qry="";
			
			if(remarks!=null && letter_no==null){
				qry = "update mms_tb_mlccs_new_req set reject_remarks=?,op_status ='2',reject_date=localtimestamp where id=?";
			}
			else if(remarks==null && letter_no!=null){
				qry = "update mms_tb_mlccs_new_req set reject_letter_no=?,op_status ='2',reject_date=localtimestamp where id =?";
			}
			else if(remarks!=null && letter_no!=null){
				qry = "update mms_tb_mlccs_new_req set reject_letter_no=?,reject_remarks=?,op_status ='2',reject_date=localtimestamp where id =?";
			} 
			else{
				qry = "update mms_tb_mlccs_new_req set op_status ='2' where id =?";
			}	 
		    PreparedStatement stmt=conn.prepareStatement(qry);
		    
		    if(remarks!=null && letter_no==null){
		    	stmt.setString(1,remarks);
		    	stmt.setInt(2,id);
		    	
			}
			else if(remarks==null && letter_no!=null){
				stmt.setString(1,letter_no);
				stmt.setInt(2,id);
			}
			else if(remarks!=null && letter_no!=null){
				stmt.setString(1,letter_no);
				stmt.setString(2,remarks);
				stmt.setInt(3,id);
			} 
			else{
				stmt.setInt(1,id);
			}
		    out = stmt.executeUpdate(); 
		    conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
			    }
			}
		}
		if(out > 0){
			list2.add("Rejected Successfully.");
		}else{
			list2.add("Rejection Failed !"); 
		}
		return list2;
    }
}