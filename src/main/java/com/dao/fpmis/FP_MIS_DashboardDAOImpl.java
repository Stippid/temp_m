package com.dao.fpmis;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class FP_MIS_DashboardDAOImpl implements FP_MIS_DashboardDAO {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	@Autowired
	private FP_MIS_ReportsDAO fp_rptDao;

	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }   
    
	  public List<String> getMNHEncList(List<String> l, HttpSession s1) {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher ci = null;
			
			try {
				ci = hex_asciiDao.EncryptionSHA256Algo(s1,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			
			List<String> FList = new ArrayList<String>();
			
			for(int i=0; i<l.size();i++) {
				byte[] encCode = null;
				try {
					encCode = ci.doFinal(l.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FList.add(base64EncodedEncryptedCode);
			}
			
			if(l.size() != 0) {
				FList.add(enckey+"0fsjyg==");
			}
		
			return FList;
		}  
	  
	  public List<Map<String, Object>> getHoldingStateDatass(String roleAccess,String roleSubAccess,String roleFormationNo,String sus_no, String fin_year)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;

	  		try{	  
	  			conn = dataSource.getConnection();	
	  			String whr="",qry="";
	  			
	  			String priority="";
	  			if(roleAccess.equals("Formation")) {
	  				if(roleSubAccess.equals("Command")) {
	  					whr=String.valueOf(roleFormationNo.charAt(0));
	  				}
	  				if(roleSubAccess.equals("Corps")) {
	  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
	  				}
	  				if(roleSubAccess.equals("Division")) {
	  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
	  				}
	  				if(roleSubAccess.equals("Brigade")) {  	
	  					whr=roleFormationNo;
	  				}
	  				qry="select to_sus_no,(select unit_name from fp.fp_tv_orbat_dtl where sus_no='"+sus_no+"') as unit_name,sum(exp_amt) filter (where (tr_type = 'TFR')) as allot_amt,sum(exp_amt) filter (where (tr_type = 'EXP')) as expt_amt,(select sum(bkd_amt) as bkd_amt from fp.fp_tb_cda_detl20) as bkd_amt from fp.fp_tb_trans_detl20 where to_sus_no='"+sus_no+"' group by to_sus_no";
	  				
	  			}else {
	  				qry="select to_sus_no,(select unit_name from fp.fp_tv_orbat_dtl where sus_no='"+sus_no+"') as unit_name,sum(exp_amt) filter (where (tr_type = 'TFR')) as allot_amt,sum(exp_amt) filter (where (tr_type = 'EXP')) as expt_amt,(select sum(bkd_amt) as bkd_amt from fp.fp_tb_cda_detl20) as bkd_amt from fp.fp_tb_trans_detl20 where to_sus_no='"+sus_no+"' group by to_sus_no";
	  			}
	 			PreparedStatement stmt=conn.prepareStatement(qry);
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
	  
	  
	    public ArrayList<ArrayList<String>> nFundPerfMeter(HttpSession sessionA,String m1_tryear,String m1_lvl,String m1_hdlvl,String nUsrId) {
	    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    	Connection conn = null;    	
	    	ArrayList<ArrayList<String>> m1lvl = new ArrayList<ArrayList<String>>();    	
	    	m1lvl=fp_trDao.getunitBuglist("",sessionA,"SUS_5_XXXXXXXXXX_"+m1_lvl);
	    	
	    	String trhead="2:";
	    	
	    	String fileYr=fp_trDao.nGetAdmFinYear("CFY").substring(2,4);
	    	String cfy="20"+fileYr;
	    		    	
	    	String susdetl ="HQ_"+m1lvl.get(0).get(3)+"_"+m1lvl.get(0).get(0)+"_"+m1lvl.get(0).get(1);
	    	
	    	String sql1="";
		    String hdCnd="";
		    String hdCnd0="";
		    String hdCnd1="";
		    String rsfmt="CR";
		    String rsfmtv="1";
		    String rsfmtvd="2";
		    String fmncnd="";
		    if (rsfmt.equalsIgnoreCase("CR")) {
		    	rsfmtv="10000000";
		    	rsfmtvd="5";
		    } else {
		    	rsfmtv="1";
		    	rsfmtvd="2";
		    }
			try{	  
				conn = dataSource.getConnection();			    
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("1")) {
				    hdCnd="split_part(tr_head_to,':',1) ";
				    hdCnd0="split_part(fr_head_to,':',1) ";
				    hdCnd1="split_part(tr_head,':',1) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 1) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("2")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";		
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 3) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("3")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 6) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("4")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 10) +"%'";
			    } 		    
			    	sql1="";
				    	sql1 = sql1 + " select split_part(a.tr_head_to,':',2) as head,max(b.mnh_desc) as mnh_desc,";
				    	//sql1 = sql1 + " trunc(sum(fnd_amt)::numeric,2) as fndamt,";
				    	sql1 = sql1 + " (case when max(fr_sus_no)='A000001' then trunc(sum(alt_amt)::numeric,2) else trunc(sum(fnd_amt)::numeric,2) end) as fndamt,"; 				    	
				    	sql1 = sql1 + " trunc(sum(alt_amt)::numeric,2) as altamt,"; 
				    	sql1 = sql1 + " trunc(sum(exp_amt)::numeric,2) as expamt,"; 
				    	sql1 = sql1 + " trunc(sum(fwd_amt)::numeric,2) as fwdamt,"; 
				    	sql1 = sql1 + " trunc(sum(bkd_amt)::numeric,2) as bkdamt";
				    	/*sql1 = sql1 + " ,ngetper(sum(fnd_amt)::numeric,sum(alt_amt)::numeric) as fndalt";*/
				    	sql1 = sql1 + " ,(case when max(fr_sus_no)='A000001' then ngetper(sum(alt_amt)::numeric,sum(alt_amt)::numeric) else ngetper(sum(fnd_amt)::numeric,sum(alt_amt)::numeric) end) as fndalt"; 
				    	sql1 = sql1 + " ,ngetper(sum(fnd_amt)::numeric,sum(exp_amt)::numeric) as altexp";
				    	sql1 = sql1 + " ,ngetper(sum(fnd_amt)::numeric,sum(fwd_amt)::numeric) as altfwd";
				    	sql1 = sql1 + " ,ngetper(sum(fnd_amt)::numeric,sum(bkd_amt)::numeric) as altbkd";
				    	sql1 = sql1 + " ,ngetper(sum(exp_amt)::numeric,sum(fwd_amt)::numeric) as expfwd";
				    	sql1 = sql1 + " ,ngetper(sum(exp_amt)::numeric,sum(bkd_amt)::numeric) as expbkd";
				    	sql1 = sql1 + " ,ngetper(sum(fwd_amt)::numeric,sum(bkd_amt)::numeric) as fwdbkd";
				    	sql1 = sql1 + " from ("; 
				    	
				    	sql1 = sql1 + fp_rptDao.nFundbaseSql(susdetl, nUsrId, trhead, susdetl, cfy, sessionA,":FND::ALT:EXP:CDA");
				    	
				    	sql1 = sql1 + " ) a "; 
				    	sql1 = sql1 + " left join fp.fp_tv_head_dtl b on b.minor_head=split_part(a.tr_head_to,':',2) and tr_level='2'"; 
				    	sql1 = sql1 + " where a.to_sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " group by split_part(a.tr_head_to,':',2)"; 
				    	sql1 = sql1 + " having split_part(a.tr_head_to,':',2)<>''";
				    	sql1 = sql1 + " order by split_part(a.tr_head_to,':',2) desc";
				   System.out.println("sql-"+sql1);
			    PreparedStatement stmt = conn.prepareStatement(sql1);
			    ResultSet rs = stmt.executeQuery();   
			   
			    if(!rs.isBeforeFirst()) {	
			    	ArrayList<String> list = new ArrayList<String>();
			    	list.add("NIL");
			    	li.add(list);
			    }else{
			    	while(rs.next()){
			        	ArrayList<String> list2 = new ArrayList<String>();
			        	list2.add(rs.getString("head"));
			        	list2.add(rs.getString("fndalt"));
			        	list2.add(rs.getString("altexp"));
	     	        	list2.add(rs.getString("altfwd"));
	     	        	list2.add(rs.getString("expfwd"));
	     	        	list2.add(rs.getString("altbkd"));
	     	        	list2.add(rs.getString("expbkd"));
	     	        	list2.add(rs.getString("fwdbkd"));	     	        		     	        
	     	        	list2.add('"'+rs.getString("mnh_desc")+'"');	     	        	
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

	    
	    public ArrayList<ArrayList<String>> nFundPerfMeter_nkok(HttpSession sessionA,String m1_tryear,String m1_lvl,String m1_hdlvl,String nUsrId) {
	    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    	Connection conn = null;    	
	    	ArrayList<ArrayList<String>> m1lvl = new ArrayList<ArrayList<String>>();    	
	    	m1lvl=fp_trDao.getunitlist("",sessionA,"SUS_5_XXXXXXXXXX_"+m1_lvl);
	    	String fileYr=fp_trDao.nGetAdmFinYear("CFY").substring(2,4);
	    	String sql1="";
		    String hdCnd="";
		    String hdCnd0="";
		    String hdCnd1="";
		    String rsfmt="CR";
		    String rsfmtv="1";
		    String rsfmtvd="2";
		    String fmncnd="";
		    if (rsfmt.equalsIgnoreCase("CR")) {
		    	rsfmtv="10000000";
		    	rsfmtvd="5";
		    } else {
		    	rsfmtv="1";
		    	rsfmtvd="2";
		    }
			try{	  
				conn = dataSource.getConnection();			    
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("1")) {
				    hdCnd="split_part(tr_head_to,':',1) ";
				    hdCnd0="split_part(fr_head_to,':',1) ";
				    hdCnd1="split_part(tr_head,':',1) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 1) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("2")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";		
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 3) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("3")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 6) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("4")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 10) +"%'";
			    } 		    

			    	sql1="";
				    if (m1lvl.get(0).get(3).equalsIgnoreCase("-1")) {
				    	sql1 = sql1 + " select split_part(a.tr_head,':',2) as head,max(b.mnh_desc) as mnh_desc,";				    	
				    	sql1 = sql1 + " trunc(sum(altamt)::numeric,2) as altamt,"; 
				    	sql1 = sql1 + " trunc(sum(expamt)::numeric,2) as expamt,"; 
				    	sql1 = sql1 + " trunc(sum(fwdamt)::numeric,2) as fwdamt,"; 
				    	sql1 = sql1 + " trunc(sum(bkdamt)::numeric,2) as bkdamt";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(expamt)::numeric) as altexp";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(fwdamt)::numeric) as altfwd";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(bkdamt)::numeric) as altbkd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(fwdamt)::numeric) as expfwd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(bkdamt)::numeric) as expbkd";
				    	sql1 = sql1 + " ,ngetper(sum(fwdamt)::numeric,sum(bkdamt)::numeric) as fwdbkd";
				    	sql1 = sql1 + " from ("; 
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,p.to_sus_no as sus_no,p.tr_head_to as tr_head,p.altamt,p.expamt,p.edtamt,";
				    	sql1 = sql1 + " p.gstamt,p.othamt,p.fwdamt,p.bkdamt from ("; 
				    	sql1 = sql1 + " select a.to_sus_no,a.tr_head_to,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric,5,'R2C'::text) as altamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric,5,'R2C'::text) as expamt,";
				    	sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric,5,'R2C'::text) as edtamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric,5,'R2C'::text) as gstamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric,5,'R2C'::text) as othamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric,5,'R2C'::text) as fwdamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric,5,'R2C'::text) as bkdamt from ("; 
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,";;
				    	sql1 = sql1 + " 0 as gst_amt, 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"'  and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head ";
				    	sql1 = sql1 + " where tr_type in ('TFR')  and sus_no='"+m1lvl.get(0).get(1)+"')  ";
				    	sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt, ";
				    	sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
				    	sql1 = sql1 + " 0 as fwd_amt,0 as bkd_amt, tr_type from fp.fp_tb_trans_detl"+fileYr+" where fr_sus_no in  ( ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from (  WITH RECURSIVE nRecPrefUnit AS (  ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no  ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}				    	
				    	sql1 = sql1 + " )  t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head   ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') and tr_type in ('EXP','GST','EDT','OTH') ";
				    	sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,";
				    	sql1 = sql1 + " 0 as alt_amt, 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,";
				    	sql1 = sql1 + " a.bkd_amt as bkd_amt, 'CDA' as tr_type  from fp.fp_tb_cda_detl"+fileYr+" a where a.sus_no in  (  ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (   ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p   ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}	
				    	sql1 = sql1 + " )  t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ) a group by to_sus_no,tr_head_to ) p";
				    	sql1 = sql1 + " ) a "; 
				    	sql1 = sql1 + " left join fp.fp_tv_head_dtl b on b.minor_head=split_part(a.tr_head,':',2) and tr_level='2'"; 
				    	sql1 = sql1 + " where a.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"+m1lvl.get(0).get(1)+"') ";
 
				    	sql1 = sql1 + " group by split_part(a.tr_head,':',2)"; 
				    	sql1 = sql1 + " order by split_part(a.tr_head,':',2)";

				    	
				    } else {
				    	sql1 = sql1 + " select split_part(a.tr_head,':',2) as head,max(b.mnh_desc) as mnh_desc,";				    	
				    	sql1 = sql1 + " trunc(sum(altamt)::numeric,2) as altamt,"; 
				    	sql1 = sql1 + " trunc(sum(expamt)::numeric,2) as expamt,"; 
				    	sql1 = sql1 + " trunc(sum(fwdamt)::numeric,2) as fwdamt,"; 
				    	sql1 = sql1 + " trunc(sum(bkdamt)::numeric,2) as bkdamt";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(expamt)::numeric) as altexp";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(fwdamt)::numeric) as altfwd";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(bkdamt)::numeric) as altbkd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(fwdamt)::numeric) as expfwd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(bkdamt)::numeric) as expbkd";
				    	sql1 = sql1 + " ,ngetper(sum(fwdamt)::numeric,sum(bkdamt)::numeric) as fwdbkd";
				    	sql1 = sql1 + " from ("; 
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,p.to_sus_no as sus_no,p.tr_head_to as tr_head,p.altamt,p.expamt,p.edtamt,";
				    	sql1 = sql1 + " p.gstamt,p.othamt,p.fwdamt,p.bkdamt from ("; 
				    	sql1 = sql1 + " select a.to_sus_no,a.tr_head_to,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric,5,'R2C'::text) as altamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric,5,'R2C'::text) as expamt,";
				    	sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric,5,'R2C'::text) as edtamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric,5,'R2C'::text) as gstamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric,5,'R2C'::text) as othamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric,5,'R2C'::text) as fwdamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric,5,'R2C'::text) as bkdamt from ("; 
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,";;
				    	sql1 = sql1 + " 0 as gst_amt, 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"'  and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head ";
				    	sql1 = sql1 + " where tr_type in ('TFR')  and sus_no='"+m1lvl.get(0).get(1)+"')  ";
				    	sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt, ";
				    	sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
				    	sql1 = sql1 + " 0 as fwd_amt,0 as bkd_amt, tr_type from fp.fp_tb_trans_detl"+fileYr+" where fr_sus_no in  ( ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from (  WITH RECURSIVE nRecPrefUnit AS (  ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no  ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}				    	
				    	sql1 = sql1 + " )  t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head   ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') and tr_type in ('EXP','GST','EDT','OTH') ";
				    	sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,";
				    	sql1 = sql1 + " 0 as alt_amt, 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,";
				    	sql1 = sql1 + " a.bkd_amt as bkd_amt, 'CDA' as tr_type  from fp.fp_tb_cda_detl"+fileYr+" a where a.sus_no in  (  ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (   ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p   ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}	
				    	sql1 = sql1 + " )  t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ) a group by to_sus_no,tr_head_to ) p";
				    	sql1 = sql1 + " ) a "; 
				    	sql1 = sql1 + " left join fp.fp_tv_head_dtl b on b.minor_head=split_part(a.tr_head,':',2) and tr_level='2'"; 				    	
				    	sql1 = sql1 + " where a.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " group by split_part(a.tr_head,':',2)"; 
				    	sql1 = sql1 + " order by split_part(a.tr_head,':',2)";
				    	
				    }
			    PreparedStatement stmt = conn.prepareStatement(sql1);
			    ResultSet rs = stmt.executeQuery();   
			   
			    if(!rs.isBeforeFirst()) {	
			    	ArrayList<String> list = new ArrayList<String>();
			    	list.add("NIL");
			    	li.add(list);
			    }else{
			    	while(rs.next()){
			        	ArrayList<String> list2 = new ArrayList<String>();
	     	        	list2.add(rs.getString("head"));
	     	        	list2.add(rs.getString("altexp"));
	     	        	list2.add(rs.getString("altfwd"));
	     	        	list2.add(rs.getString("expfwd"));
	     	        	list2.add(rs.getString("altbkd"));
	     	        	list2.add(rs.getString("expbkd"));
	     	        	list2.add(rs.getString("fwdbkd"));
	     	        	list2.add('"'+rs.getString("mnh_desc")+'"');
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

	    public ArrayList<ArrayList<String>> nFundPerTrend(HttpSession sessionA,String m1_tryear,String m1_lvl,String m1_hdlvl,String nUsrId,String m1_hdlvlval) {
	    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	    	Connection conn = null;    	
	    	ArrayList<ArrayList<String>> m1lvl = new ArrayList<ArrayList<String>>();    	
	    	m1lvl=fp_trDao.getunitBuglist("",sessionA,"SUS_5_XXXXXXXXXX_"+m1_lvl);
	    	
	    	String fileYr=fp_trDao.nGetAdmFinYear("CFY").substring(2,4);
	    	String sql1="";
		    String hdCnd="";
		    String hdCnd0="";
		    String hdCnd1="";
		    String rsfmt="CR";
		    String rsfmtv="1";
		    String rsfmtvd="2";
		    String fmncnd="";
		    if (rsfmt.equalsIgnoreCase("CR")) {
		    	rsfmtv="10000000";
		    	rsfmtvd="5";
		    } else {
		    	rsfmtv="1";
		    	rsfmtvd="2";
		    }
			try{	  
				conn = dataSource.getConnection();			    
				if(m1lvl.get(0).get(3).equalsIgnoreCase("1")) {
				    hdCnd="split_part(tr_head_to,':',1) ";
				    hdCnd0="split_part(fr_head_to,':',1) ";
				    hdCnd1="split_part(tr_head,':',1) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 1) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("2")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2)) ";		
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 3) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("3")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 6) +"%'";
			    }
			    if(m1lvl.get(0).get(3).equalsIgnoreCase("4")) {
				    hdCnd="concat(split_part(tr_head_to,':',1),':',split_part(tr_head_to,':',2),':',split_part(tr_head_to,':',3),':',split_part(tr_head_to,':',4)) ";
				    hdCnd0="concat(split_part(fr_head_to,':',1),':',split_part(fr_head_to,':',2),':',split_part(fr_head_to,':',3),':',split_part(fr_head_to,':',4)) ";
				    hdCnd1="concat(split_part(tr_head,':',1),':',split_part(tr_head,':',2),':',split_part(tr_head,':',3),':',split_part(tr_head,':',4)) ";
				    fmncnd="q.form_code_control like '" +m1lvl.get(0).get(0).substring(0, 10) +"%'";
			    } 		  
			    	sql1="";
			    	if (m1lvl.get(0).get(3).equalsIgnoreCase("-1")) {
				    	sql1 = sql1 + " select split_part(a.tr_head,':',2) as head,max(b.mnh_desc) as mnh_desc,a.sus_no,max(c.unit_name) as unit_name,";
				    	sql1 = sql1 + " trunc(sum(altamt)::numeric,2) as altamt,"; 
				    	sql1 = sql1 + " trunc(sum(expamt)::numeric,2) as expamt,"; 
				    	sql1 = sql1 + " trunc(sum(fwdamt)::numeric,2) as fwdamt,"; 
				    	sql1 = sql1 + " trunc(sum(bkdamt)::numeric,2) as bkdamt";
				    	
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(expamt)::numeric) as altexp";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(fwdamt)::numeric) as altfwd";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(bkdamt)::numeric) as altbkd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(fwdamt)::numeric) as expfwd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(bkdamt)::numeric) as expbkd";
				    	sql1 = sql1 + " ,ngetper(sum(fwdamt)::numeric,sum(bkdamt)::numeric) as fwdbkd";
				    	sql1 = sql1 + " from (";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,p.to_sus_no as sus_no,p.tr_head_to as tr_head,p.altamt,p.expamt,p.edtamt,";
				    	sql1 = sql1 + " p.gstamt,p.othamt,p.fwdamt,p.bkdamt from ("; 
				    	sql1 = sql1 + " select a.to_sus_no,a.tr_head_to,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric,5,'R2C'::text) as altamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric,5,'R2C'::text) as expamt,";
				    	sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric,5,'R2C'::text) as edtamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric,5,'R2C'::text) as gstamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric,5,'R2C'::text) as othamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric,5,'R2C'::text) as fwdamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric,5,'R2C'::text) as bkdamt from ("; 
				    	
				    	
				    	sql1 = sql1 + " select a.fr_sus_no,a.tr_head,a.to_sus_no as to_sus_no,a.tr_head_to,exp_amt as alt_amt,0 as exp_amt,";
				    	sql1 = sql1 + " 0 as gst_amt, 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"+fileYr+" a ";
				    	
				    	sql1 = sql1 + " inner join (select distinct max(fund_lvl) as fund_lvl,fr_sus_no,to_sus_no,tr_head_to ";
				    	sql1 = sql1 + " from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where to_sus_no='"+m1lvl.get(0).get(1)+"' ";
				    	sql1 = sql1 + " and fr_sus_no<>to_sus_no group by fr_sus_no,to_sus_no,tr_head_to) b on b.to_sus_no=a.to_sus_no ";
				    	sql1 = sql1 + " and b.tr_head_to=a.tr_head_to";
				    	sql1 = sql1 + " and a.fund_lvl=b.fund_lvl";
				    	sql1 = sql1 + " where a.tr_type='TFR'  and a.to_sus_no='"+m1lvl.get(0).get(1)+"'"; 
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt, ";
				    	sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
				    	sql1 = sql1 + " 0 as fwd_amt,0 as bkd_amt, tr_type from fp.fp_tb_trans_detl"+fileYr+" where fr_sus_no in  ( ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from (  WITH RECURSIVE nRecPrefUnit AS (  ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no  ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}				    	
				    	sql1 = sql1 + " )  t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head   ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') and tr_type in ('EXP','GST','EDT','OTH') ";
				    	sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,";
				    	sql1 = sql1 + " 0 as alt_amt, 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,";
				    	sql1 = sql1 + " a.bkd_amt as bkd_amt, 'CDA' as tr_type  from fp.fp_tb_cda_detl"+fileYr+" a where a.sus_no in  (  ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (   ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p   ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}	
				    	sql1 = sql1 + " )  t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ) a group by to_sus_no,tr_head_to ) p";
				    	sql1 = sql1 + " ) a "; 
				    	sql1 = sql1 + " left join fp.fp_tv_head_dtl b on b.minor_head=split_part(a.tr_head,':',2) and tr_level='2'"; 
				    	sql1 = sql1 + " left join fp.fp_tv_orbat_dtl c on a.sus_no=c.sus_no";
				    	sql1 = sql1 + " where a.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and a.tr_head like '%:"+m1_hdlvlval+"%'"; 
				    	sql1 = sql1 + " group by a.sus_no,split_part(a.tr_head,':',2) "; 
				    	sql1 = sql1 + " order by split_part(a.tr_head,':',2),max(c.hq_type),a.sus_no";
				    	
				    } else {
				    	sql1 = sql1 + " select split_part(a.tr_head,':',2) as head,max(b.mnh_desc) as mnh_desc,a.sus_no,max(c.unit_name) as unit_name,";
				    	sql1 = sql1 + " trunc(sum(altamt)::numeric,2) as altamt,"; 
				    	sql1 = sql1 + " trunc(sum(expamt)::numeric,2) as expamt,"; 
				    	sql1 = sql1 + " trunc(sum(fwdamt)::numeric,2) as fwdamt,"; 
				    	sql1 = sql1 + " trunc(sum(bkdamt)::numeric,2) as bkdamt"; 
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(expamt)::numeric) as altexp";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(fwdamt)::numeric) as altfwd";
				    	sql1 = sql1 + " ,ngetper(sum(altamt)::numeric,sum(bkdamt)::numeric) as altbkd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(fwdamt)::numeric) as expfwd";
				    	sql1 = sql1 + " ,ngetper(sum(expamt)::numeric,sum(bkdamt)::numeric) as expbkd";
				    	sql1 = sql1 + " ,ngetper(sum(fwdamt)::numeric,sum(bkdamt)::numeric) as fwdbkd";
				    	sql1 = sql1 + " from (";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,p.to_sus_no as sus_no,p.tr_head_to as tr_head,p.altamt,p.expamt,p.edtamt,";
				    	sql1 = sql1 + " p.gstamt,p.othamt,p.fwdamt,p.bkdamt from ("; 
				    	sql1 = sql1 + " select a.to_sus_no,a.tr_head_to,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.alt_amt)::numeric,5,'R2C'::text) as altamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.exp_amt)::numeric,5,'R2C'::text) as expamt,";
				    	sql1 = sql1 + " nconvertinr(sum(a.edt_amt)::numeric,5,'R2C'::text) as edtamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.gst_amt)::numeric,5,'R2C'::text) as gstamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.oth_amt)::numeric,5,'R2C'::text) as othamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.fwd_amt)::numeric,5,'R2C'::text) as fwdamt,"; 
				    	sql1 = sql1 + " nconvertinr(sum(a.bkd_amt)::numeric,5,'R2C'::text) as bkdamt from ("; 
				    	
				    	
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,exp_amt as alt_amt,0 as exp_amt,";;
				    	sql1 = sql1 + " 0 as gst_amt, 0 as edt_amt,0 as oth_amt,0 as fwd_amt,0 as bkd_amt,tr_type from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"'  and tr_head_to in (select chl_head_code as head_code from fp.fp_tb_pref_head ";
				    	sql1 = sql1 + " where tr_type in ('TFR')  and sus_no='"+m1lvl.get(0).get(1)+"')  ";
				    	sql1 = sql1 + " and fund_lvl=(select coalesce(max(fund_lvl),0) as maxlvl from fp.fp_tb_funds_detl"+fileYr;
				    	sql1 = sql1 + " where fr_sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " union all  ";
				    	
				    	
				    	sql1 = sql1 + " select fr_sus_no,tr_head,to_sus_no as to_sus_no,tr_head_to,0 as alt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EXP' then exp_amt else 0 end) as exp_amt, ";
				    	sql1 = sql1 + " (case when tr_type='GST' then exp_amt else 0 end) as gst_amt, ";
				    	sql1 = sql1 + " (case when tr_type='EDT' then exp_amt else 0 end) as edt_amt, ";
				    	sql1 = sql1 + " (case when tr_type='OTH' then exp_amt else 0 end) as oth_amt,";
				    	sql1 = sql1 + " 0 as fwd_amt,0 as bkd_amt, tr_type from fp.fp_tb_trans_detl"+fileYr+" where fr_sus_no in  ( ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from (  WITH RECURSIVE nRecPrefUnit AS (  ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"'  union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p  ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no  ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}				    	
				    	sql1 = sql1 + " )  t )  and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head   ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') and tr_type in ('EXP','GST','EDT','OTH') ";
				    	sql1 = sql1 + " and to_sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	
				    	
				    	
				    	
				    	
				    	sql1 = sql1 + " union all  ";
				    	sql1 = sql1 + " select '"+m1lvl.get(0).get(1)+"' as fr_sus_no,a.tr_head,a.sus_no as to_sus_no,a.tr_head as tr_head_to,";
				    	sql1 = sql1 + " 0 as alt_amt, 0 as exp_amt,0 as gst_amt,0 as edt_amt,0 as oth_amt,a.fwd_amt as fwd_amt,";
				    	sql1 = sql1 + " a.bkd_amt as bkd_amt, 'CDA' as tr_type  from fp.fp_tb_cda_detl"+fileYr+" a where a.sus_no in  (  ";
				    	sql1 = sql1 + " select distinct t.chl_sus_no from ( WITH RECURSIVE nRecPrefUnit AS (   ";
				    	sql1 = sql1 + " select p.id,p.sus_no,p.chl_sus_no from fp.fp_tb_pref_unit p   ";
				    	sql1 = sql1 + " where p.sus_no='"+m1lvl.get(0).get(1)+"' union  select n.id,n.sus_no,n.chl_sus_no from fp.fp_tb_pref_unit n  ";
				    	sql1 = sql1 + " inner join nRecPrefUnit s  on s.chl_sus_no=s.sus_no  )  ";
				    	sql1 = sql1 + " select distinct p.sus_no,p.chl_sus_no from nRecPrefUnit p   ";
				    	sql1 = sql1 + " inner join fp.fp_tv_orbat_dtl q on p.sus_no=q.sus_no ";
				    	sql1 = sql1 + " where q.hq_type::integer>="+m1lvl.get(0).get(3);
				    	if (fmncnd.length()>0) {
				    		sql1 = sql1 + " and "+fmncnd;	
				    	}	
				    	sql1 = sql1 + " )  t ) and tr_head in (select chl_head_code as head_code from fp.fp_tb_pref_head  ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				    	sql1 = sql1 + " where sus_no='"+m1lvl.get(0).get(1)+"') ) a group by to_sus_no,tr_head_to ) p";
				    	sql1 = sql1 + " ) a "; 
				    	sql1 = sql1 + " left join fp.fp_tv_head_dtl b on b.minor_head=split_part(a.tr_head,':',2) and tr_level='2'"; 
				    	sql1 = sql1 + " left join fp.fp_tv_orbat_dtl c on a.sus_no=c.sus_no";
				    	sql1 = sql1 + " where a.sus_no in (select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no='"+m1lvl.get(0).get(1)+"') ";
				    	sql1 = sql1 + " and a.tr_head like '%:"+m1_hdlvlval+"%'"; 
				    	sql1 = sql1 + " group by a.sus_no,split_part(a.tr_head,':',2) "; 
				    	sql1 = sql1 + " order by split_part(a.tr_head,':',2),max(c.hq_type),a.sus_no";

				    }
			    
			    PreparedStatement stmt = conn.prepareStatement(sql1);
			    ResultSet rs = stmt.executeQuery();   
			   
			    if(!rs.isBeforeFirst()) {	
			    	ArrayList<String> list = new ArrayList<String>();
			    	list.add("NIL");
			    	li.add(list);
			    }else{
			    	while(rs.next()){
			        	ArrayList<String> list2 = new ArrayList<String>();
	     	    		list2.add('"'+rs.getString("sus_no")+'"');
	     	        	list2.add(rs.getString("head"));
	     	        	list2.add('"'+rs.getString("unit_name")+'"');
	     	        	list2.add(rs.getString("altamt"));
	     	        	list2.add(rs.getString("expamt"));
	     	        	list2.add(rs.getString("fwdamt"));
	     	        	list2.add(rs.getString("bkdamt"));	     	        	
	     	        	list2.add(rs.getString("altexp"));
	     	        	list2.add(rs.getString("altfwd"));
	     	        	list2.add(rs.getString("expfwd"));
	     	        	list2.add(rs.getString("altbkd"));
	     	        	list2.add(rs.getString("expbkd"));
	     	        	list2.add(rs.getString("fwdbkd"));
	     	        	list2.add('"'+rs.getString("mnh_desc")+'"');
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
	  
}