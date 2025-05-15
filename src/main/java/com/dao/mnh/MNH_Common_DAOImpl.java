package com.dao.mnh;

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
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class MNH_Common_DAOImpl implements MNH_Common_DAO{
	
    HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
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
		
		// Enc Key Append Last value of List
		if(l.size() != 0) {
			FList.add(enckey+"0fsjyg==");
		}
	
		return FList;
	}

	public List<String> getMNHHirarNameBySUS(String FindWhat, String nSUSNo) {
		Connection conn = null;
		   List<String> l = new ArrayList<String>();
		   try{
			   conn = dataSource.getConnection();
			   String sq1 = "";
			   String cond = "";		 
				
			   if(FindWhat.equalsIgnoreCase("COMMAND")){
				   cond="concat(substring(a.form_code_control,?,?),?)";
				   //cond="concat(substring(a.form_code_control,1,1),'000000000')";
			   }else if(FindWhat.equalsIgnoreCase("CORPS")){
				   cond="concat(substring(a.form_code_control,?,?),?)";
				   //cond="concat(substring(a.form_code_control,1,3),'0000000')";
			   }else if(FindWhat.equalsIgnoreCase("DIVISION")){
				   cond="concat(substring(a.form_code_control,?,?),?)";
				   //cond="concat(substring(a.form_code_control,1,6),'0000')";
			   }else if(FindWhat.equalsIgnoreCase("BRIGADE")){
				   cond="a.form_code_control";
			   }else if(FindWhat.equalsIgnoreCase("HQ")){
				   cond="a.form_code_control";
			   }
				
			   sq1 = "select p.hq,q.sus_no as hq_sus_no,q.unit_name as hq_name,p.form_code_control,p.sus_no,p.unit_name from (";
			   
			  // sq1 = sq1 +" select "+ cond +" as hq,a.form_code_control,a.sus_no,a.unit_name from tb_miso_orbat_unt_dtl a";
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				
			   sq1 = sq1 +" select "+ cond +" as hq,a.form_code_control,a.sus_no,a.unit_name from orbat_all_details_view_mnh a";
			   
			   sq1 =  sq1 + " where a.sus_no=? and a.status_sus_no='Active') p left join nrv_hq q on p.hq=q.form_code_control";
			   
			   PreparedStatement stmt = conn.prepareStatement(sq1);
			   
			   if(FindWhat.equalsIgnoreCase("COMMAND")){
				   stmt.setInt(1, 1);
				   stmt.setInt(2, 1);
				   stmt.setString(3, "000000000");
				   stmt.setString(4, nSUSNo);
			   }else if(FindWhat.equalsIgnoreCase("CORPS")){
				   stmt.setInt(1, 1);
				   stmt.setInt(2, 3);
				   stmt.setString(3, "0000000");
				   stmt.setString(4, nSUSNo);
			   }else if(FindWhat.equalsIgnoreCase("DIVISION")){
				   stmt.setInt(1, 1);
				   stmt.setInt(2, 6);
				   stmt.setString(3, "0000");
				   stmt.setString(4, nSUSNo);
			   }else if(FindWhat.equalsIgnoreCase("BRIGADE")){
				   stmt.setString(1, nSUSNo);
			   }else if(FindWhat.equalsIgnoreCase("HQ")){
				   stmt.setString(1, nSUSNo);
			   }
			   
	           ResultSet rs = stmt.executeQuery();  
			
	           String str1="";
	           while(rs.next()){
	        	   str1=str1+rs.getString("hq");
	               str1=str1+":"+rs.getString("hq_name");
	               str1=str1+":"+rs.getString("form_code_control");
	               str1=str1+":"+rs.getString("sus_no");
	               str1=str1+":"+rs.getString("unit_name");
	               str1=str1+":"+rs.getString("hq_sus_no");
	               str1=str1+","; 
	           }  
	           l.add(str1);
	           
	  		   rs.close();
	  	       stmt.close();
	  		   conn.close();   
	      }catch(SQLException e){
	    	  e.printStackTrace();
	  	  }	 
		  return l;
	}
	
	public List<String> getMNHDistinctHirarName(String nHirar, String nCnd, String codelevel) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
            String sq1="";
            String cond = "";
            int start = 0;
    		int end = 0;
    		
    		if(codelevel.equalsIgnoreCase("COMMAND")) {
    			start = 1;
    			end = 1;
    		}else if(codelevel.equalsIgnoreCase("CORPS")) {
    			start = 1;
    			end = 3;
    		}else if(codelevel.equalsIgnoreCase("DIVISION")) {
    			start = 1;
    			end = 6;
    		}else if(codelevel.equalsIgnoreCase("BRIGADE")) {
    			start = 1;
    			end = 10;
    		}else if(codelevel.equalsIgnoreCase("UNIT")) {
    			start = 1;     
    			end = 10;
    		}
    		
    		//sq1 = "select distinct form_code_control,sus_no,unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' ";
    		
    		// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
    		
    		sq1 = "select distinct form_code_control,sus_no,unit_name from orbat_all_details_view_mnh where status_sus_no='Active' ";
    		
    		if(!codelevel.equalsIgnoreCase("LINE")) {
    			if (nCnd != null && !nCnd.equals("ALL")) {
    				if(nHirar.equalsIgnoreCase("COMMAND")) {
    					cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
    				}else if(nHirar.equalsIgnoreCase("CORPS")) {
    					cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
    				}else if(nHirar.equalsIgnoreCase("DIVISION")) {
    					cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
    				}else if(nHirar.equalsIgnoreCase("BRIGADE")) {
    					cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
    				}else if(nHirar.equalsIgnoreCase("UNIT")) {
    					cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
    					//cond = " and substring(form_code_control,"+start+","+end+")=substring('" + nCnd + "',"+start+","+end+") ";
    				}
    			}
    		}
    		
    		if(codelevel.equalsIgnoreCase("LINE")) {
    			if (nCnd.equalsIgnoreCase("ALL")) {
    				cond = cond+" and arm_code in (select distinct arm_code from tb_miso_orbat_arm_code) ";
    			} else {
    				cond = cond+" and arm_code=? ";
    				//cond = cond+" and arm_code='"+nCnd+"' ";
    			}			
    		} 
    		
    		if (!nHirar.equalsIgnoreCase("ALL")) {
    			if (nCnd.equalsIgnoreCase("ALL")) {
    				cond = cond+ " and sus_no in (select DISTINCT sus_no from tb_miso_orbat_codesform where upper(level_in_hierarchy) in (?)) ";
    				//cond = cond+ " and sus_no in (select DISTINCT sus_no from tb_miso_orbat_codesform where upper(level_in_hierarchy) in ('"+ nHirar + "')) ";
    			} else {
    				cond = cond + " and sus_no in (select DISTINCT sus_no from tb_miso_orbat_codesform where upper(level_in_hierarchy) in (?)) ";
    			}			
    		}
    		
    		if (!cond.equals(null)) {
    			sq1=sq1 +cond;
    		}
    		
    		PreparedStatement stmt = conn.prepareStatement(sq1);
    		if(!codelevel.equalsIgnoreCase("LINE")){
    			if(nCnd != null && !nCnd.equals("ALL")){
    				stmt.setInt(1, start);
    				stmt.setInt(2, end);
    				stmt.setString(3, nCnd);
    				stmt.setInt(4, start);
    				stmt.setInt(5, end);
    			}
    			if(!nHirar.equalsIgnoreCase("ALL")){
    				if (nCnd.equalsIgnoreCase("ALL")) {
    					stmt.setString(1, nHirar);
    				}else {
    					stmt.setString(6, nHirar);
    				}
    			}
    		}
    		
    		if(codelevel.equalsIgnoreCase("LINE")){
    			if(nCnd.equalsIgnoreCase("ALL")){
    				stmt.setString(1, nHirar);
    			}else {
    				stmt.setString(1, nCnd);
    				stmt.setString(2, nHirar);
    			}
    		}
    		
    		ResultSet rs = stmt.executeQuery();   
    		
    		String str1="";
            while(rs.next()){
            	str1=str1+rs.getString("form_code_control");
            	str1=str1+":"+rs.getString("sus_no");
            	str1=str1+":"+rs.getString("unit_name");
            	str1=str1+",";                            	  
            }  
            l.add(str1);
         
            rs.close();
            stmt.close();
            conn.close();
		}catch(SQLException e){
        	e.printStackTrace();
        }   
		return l;
	}

	public List<String> getMNHRank(String a1, String a2) {
		List<String> l = new ArrayList<String>();
        Connection conn = null;
        try{          
        	conn = dataSource.getConnection();
            String sq1="";                            
            sq1 = "select distinct rank_desc,rank_code,id from tb_med_rankcode where category_code=? and service=? order by rank_code";
            
            PreparedStatement stmt = conn.prepareStatement(sq1);
            stmt.setString(1, a1);
            stmt.setString(2, a2);
            ResultSet rs = stmt.executeQuery();   
           
            String str1="";
            while(rs.next()){
            	str1=str1+rs.getString("rank_desc");
            	str1=str1+":"+rs.getString("id");
            	str1=str1+",";                            	  
            }  
            l.add(str1);
           
            rs.close();
            stmt.close();
            conn.close();
       }catch(SQLException e){
    	   e.printStackTrace();
       }        
       return l;
	}

	public ArrayList<ArrayList<String>> getMNHRData(int userid) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			String sql1="";
		    String sql2="";
		    String nSUS="";
	    	String nRoleType="";
	    	String nRoleAccess="";
	    	String nRoleAccess1="";
	    	String nRoleAccess2="";
	    	String nCnd="";
	    	String nfmncode="";
	        String narmcode="";
	        
	        sql1 = "select a.userid,a.username,a.user_sus_no,a.user_formation_no,a.user_arm_code,d.role_id,c.role_type,c.access_lvl,c.sub_access_lvl,c.staff_lvl from"; 
		    sql1 = sql1 + " (select distinct userid,username,user_sus_no,user_formation_no,user_arm_code from logininformation where userid=?) a";
		    sql1 = sql1 + " left join (select distinct user_id,role_id from userroleinformation) d on a.userid=d.user_id";
		    sql1 = sql1 + " left join (select role_id,role_type,access_lvl,sub_access_lvl,staff_lvl from roleinformation) c on d.role_id=c.role_id";
		    
		    PreparedStatement stmt = conn.prepareStatement(sql1);
	        stmt.setInt(1, userid);
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()){
	        	nSUS=rs.getString("user_sus_no");
	        	nRoleAccess=rs.getString("access_lvl");
	        	nRoleType=rs.getString("role_type");
	        	if (nRoleType==null || nRoleType.equals(null)) { 
			    	nRoleType="DEO";
			    }	
			    nRoleAccess1=rs.getString("sub_access_lvl");
			    nRoleAccess2=rs.getString("staff_lvl");
			    nfmncode=rs.getString("user_formation_no");
			    narmcode=rs.getString("user_arm_code");
			}        
			
	        rs.close();
	        stmt.close();
	        
	        if(nRoleAccess.equalsIgnoreCase("UNIT")){
	        	nCnd=" and a.sus_no=?";//nSUS
	    	}
	    	if(nRoleAccess.equalsIgnoreCase("MISO")){
	    		nCnd="";
	    	}
	    	if(nRoleAccess.equalsIgnoreCase("FORMATION")){
	    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
		    		nCnd=" and substring(a.form_code_control,1,1)=?";
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
	    			nCnd=" and substring(a.form_code_control,1,3)=?";
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
	    			nCnd=" and substring(a.form_code_control,1,6)=?";
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
	    			nCnd=" and a.form_code_control=?";
	    		}
	    	}
	    	if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
	    		nCnd=" and a.arm_code=?";//narmcode
	    	}
	    	
	    	/*sql2 = "select distinct upper(COALESCE(b.level_in_hierarchy,'UNIT')) as lvl,a.sus_no,a.unit_name,a.form_code_control,a.arm_code from tb_miso_orbat_unt_dtl a left join 
	    	 * tb_miso_orbat_codesform b on a.sus_no=b.sus_no where a.status_sus_no='Active' "; */
		   
	    	// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
	    	
	    	sql2 = "select distinct upper(COALESCE(b.level_in_hierarchy,'UNIT')) as lvl,a.sus_no,a.unit_name,a.form_code_control,a.arm_code from orbat_all_details_view_mnh a left join tb_miso_orbat_codesform b on a.sus_no=b.sus_no where a.status_sus_no='Active' "; 
	    	sql2 = sql2 + nCnd;
		    sql2 = sql2 +" order by a.form_code_control,a.sus_no";
		   
		    PreparedStatement stmt2 = conn.prepareStatement(sql2);
		    
		    if(nRoleAccess.equalsIgnoreCase("UNIT")){
		    	stmt2.setString(1, nSUS);
		    }
		    if(nRoleAccess.equalsIgnoreCase("MISO")){
	    	}
		    if(nRoleAccess.equalsIgnoreCase("FORMATION")){
	    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
		    		stmt2.setString(1, nfmncode.substring(0, 1));
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
	    			stmt2.setString(1, nfmncode.substring(0, 3));
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
	    			stmt2.setString(1, nfmncode.substring(0, 6));
	    		}
	    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
	    			stmt2.setString(1, nfmncode);
	    		}
	    	}
		    if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
		    	stmt2.setString(1, narmcode);
		    }
		    
	        ResultSet rs2 = stmt2.executeQuery();
	        
	        if(!rs2.isBeforeFirst()) {	
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list); 
			}else{
				while(rs2.next()){
					ArrayList<String> list = new ArrayList<String>();
					if(nRoleAccess.equalsIgnoreCase("UNIT")){
						list.add("1");
						list.add("UNIT");
				    }
					if(nRoleAccess.equalsIgnoreCase("MISO")){
						list.add("9");
						list.add("MISO");
			    	}
					if(nRoleAccess.equalsIgnoreCase("FORMATION")){
			    		if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
			    			list.add("5");
			    			list.add("COMMAND");
			    		}
			    		if(nRoleAccess1.equalsIgnoreCase("CORPS")){
			    			list.add("4");
			    			list.add("CORPS");
			    		}
			    		if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
			    			list.add("3");
			    			list.add("DIVISION");
			    		}
			    		if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
			    			list.add("2");
			    			list.add("BRIGADE");
			    		}
			    	}
					if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
						list.add("6");
						list.add("LINE_DTE");
				    }
					list.add(rs2.getString("sus_no"));
					list.add(rs2.getString("unit_name"));
					list.add(rs2.getString("form_code_control"));
					list.add(rs2.getString("arm_code"));
					list.add(rs2.getString("lvl"));
					list.add(nRoleAccess.toUpperCase());
					list.add(nRoleType.toUpperCase());					
					li.add(list); 
				}
			}
	        
	        rs2.close();    
	        stmt2.close();
	        conn.close();
	        
		}catch(SQLException e){
	    	e.printStackTrace();
	    }
		
		return li;
	}
	
	
	public List<Map<String, String>> GetPersonnelNoDatamnh(String personnel_no) {
		
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "\r\n" + 
					"select c.id,c.cadet_no,c.batch_no,g.gender_name,to_char(c.date_of_birth,'YYYY-MM-DD') as date_of_birth,c.name,PGP_SYM_DECRYPT(p.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,\r\n" + 
					"cu.description,ct.course_name,c.parent_arm,c.regiment,cu.code as rankcode,c.date_of_commission,u.unit_name,cue.description as appointment,\r\n" + 
					"PGP_SYM_DECRYPT(con.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no\r\n" + 
					"FROM \r\n" + 
					"tb_psg_trans_proposed_comm_letter c \r\n" + 
					"left join tb_miso_orbat_unt_dtl u on c.unit_sus_no=u.sus_no\r\n" + 
					"left join tb_psg_mstr_gender g  on c.gender=g.id\r\n" + 
					"left join  tb_psg_mstr_course_comm ct on  c.course=ct.id\r\n" + 
					"left join cue_tb_psg_rank_app_master cu on cu.id=c.rank\r\n" + 
					"left join  tb_psg_census_detail_p p on p.comm_id =c.id\r\n" + 
					"left join  tb_psg_census_contact_cda_account_details con on con.comm_id =c.id\r\n" + 
					"left join  tb_psg_change_of_appointment ap on ap.comm_id =c.id\r\n" + 
					"left join  cue_tb_psg_rank_app_master cue on cue.id=ap.appointment \r\n" + 
					"and upper(cue.level_in_hierarchy) = 'APPOINTMENT' and cue.parent_code ='0'\r\n" + 
					"\r\n" + 
					"where upper(c.personnel_no) = ?  order by c.id desc limit 1 ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, personnel_no);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getString(i));
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
	

			        public List<String> getMNHCommandName() {
			                List<String> list1 = new ArrayList<String>();
			                Connection conn = null;
			                try {
			                        conn = dataSource.getConnection();
			                        PreparedStatement stmt = null;
			                        String sql1 = "select distinct id,unit_name from tb_miso_orbat_unt_dtl \r\n" + 
			                                        "where sus_no \r\n" + 
			                                        "in(select sus_no as col_0_0_ from tb_miso_orbat_codesform\r\n" + 
			                                        " where level_in_hierarchy='Command') and status_sus_no='Active' order by id";
			                        
//			                        String sql1 ="select distinct id,sus_no,cmd_name from orbat_all_details_view_mnh order by id";
			                        
			                       
			                        stmt = conn.prepareStatement(sql1);
			                        // stmt.setString(1, sus_no);
			                        ResultSet rs1 = stmt.executeQuery();
			                        while (rs1.next()) {
			                        
			                                list1.add(rs1.getString("unit_name"));
			                        }
			                        rs1.close();
			                        stmt.close();
			                } catch (SQLException e) {

			                } finally {
			                        try {
			                                conn.close();
			                        } catch (SQLException e) {
			                                return list1;
			                        }
			                }

			                return list1;
			        }

}
