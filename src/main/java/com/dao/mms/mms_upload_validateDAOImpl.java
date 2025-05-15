package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class mms_upload_validateDAOImpl implements mms_upload_validateDAO{
	
	private DataSource dataSource;
	
	 public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
	 
	 public String GetPRFCodeByCensusNo(String census_no) {
			String PRFCode = "";
			//-------------------------New Query for max number ---------------------------------------
			Connection conn = null;
			try{
				   conn = dataSource.getConnection();
               String sql1="";
		       PreparedStatement stmt=null;

			   sql1 = "select distinct prf_code from mms_tb_mlccs_mstr_detl where census_no like ?"; 
			   stmt=conn.prepareStatement(sql1);
              stmt.setString(1,census_no+"%");
              ResultSet rs1 = stmt.executeQuery();  

			     while(rs1.next()){
			    	 PRFCode = rs1.getString("prf_code");
		         }	
		         rs1.close();
		         stmt.close();
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
			return PRFCode;
		}
	
	 
	 public String RegnNoGeneration1(String prf_code, String eqpt_regn_no) {
			String Regn_seq_no = "";
			
			//-------------------------New Query for max number ---------------------------------------
			
			String list1 = new String();
			Connection conn = null;
			try{
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();
		        String sql1="";
			    sql1 = "select max(prfseq) as count from (select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8)\r\n" + 
			    		"UNION\r\n" + 
			    		"select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_regn_oth_mstr where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n" + 
			    		"UNION\r\n" + 
			    		"select max(substr(regn_seq_no,9,16)) as prfseq from mms_tb_depot_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n" + 
			    		") AS tab"; 
			     ResultSet rs1 = stmt.executeQuery(sql1);     
			     while(rs1.next()){
			    	 	list1 = rs1.getString("count");
			    	    //list1.add(id);
		         }	
			     rs1.close();
		         stmt.close();
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
		String serial= "";
		if(list1.equals(null)) {
			serial = "00000000";
		}else {
			String list2 = list1;
			serial = list2;
		}
		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%08d", serialNo);
		Regn_seq_no = prf_code + "N" + serial;
		return Regn_seq_no;
	 }
	 
	 public String validationConfirm(String a) {
		 Connection conn = null;
		 try{	  
			 conn = dataSource.getConnection();
             PreparedStatement stmt=null;
			 String[] rodiv=a.split(":");
			 String nrSql="";			 
			 nrSql = "insert into mms_tb_regn_mstr_detl";
			 nrSql = nrSql + " (sus_no,prf_code,census_seq_no,census_no,type_of_hldg,type_of_eqpt,eqpt_regn_no,regn_seq_no,from_sus_no,from_tr_date,to_sus_no,";
		     nrSql = nrSql + " to_tr_date,eqpt_ser_no,eqpt_batch_no,eqpt_part_no,service_status,data_cr_by,data_cr_date,data_upd_by,data_upd_date,data_app_by,";
			 nrSql = nrSql + " data_app_date,op_status,tfr_status,rv_no,rv_date )";
			 nrSql = nrSql + " select issue_sus_no as sus_no,prf_code,census_seq_no,census_no,type_of_hldg,type_of_eqpt,eqpt_ser_no as eqpt_regn_no,'xxxxxxxx' as regn_seq_no,";
			 nrSql = nrSql + " issue_sus_no as from_sus_no,upload_date as from_tr_date,issue_sus_no as to_sus_no,data_cr_date as to_tr_date,eqpt_ser_no,eqpt_batch_no,";
			 nrSql = nrSql + " eqpt_part_no,'1' as service_status,data_cr_by,data_cr_date,data_upd_by,data_upd_date,data_app_by,data_app_date,'1' as op_status,";
			 nrSql = nrSql + " 'C' as tfr_status,iv_no as rv_no,iv_date as rv_date from mms_tb_imp_dir where issue_sus_no=? and iv_no=?";    
			 stmt=conn.prepareStatement(nrSql);
			 stmt.setString(0, rodiv[0]);
			 stmt.setString(1, rodiv[1]);
			 ResultSet rs1 = stmt.executeQuery();  
			 stmt.close();
			 conn.close();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }	
		 return a;	 
	 }
}