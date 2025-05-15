package com.dao.fpmis;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class miso_proc_stateDAOImpl implements miso_proc_stateDAO {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;
	
	public @ResponseBody ArrayList<ArrayList<String>> getProcDetlsdfg(String enc, HttpSession s1, String nParaValue) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		String username = s1.getAttribute("username").toString();
		String rolesus = s1.getAttribute("roleSusNo").toString();
		String rolefmn = s1.getAttribute("roleFormationNo").toString();
		String nRet = "";
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {			
			if (rolesus.length()>0) {
				nParaValue = nParaValue.toUpperCase();
				String[] nPara = nParaValue.split("_");
				Connection conn = null;
				conn = dataSource.getConnection();
				String sql1 = "";
				System.out.println("-"+nParaValue);
				sql1 = " select id,psus_no,sus_no,chl_head_code,usr_id from fp.fp_tb_pref_head ";
				if (nPara[0].equalsIgnoreCase("SUS")) {
					sql1 = sql1 + " where sus_no='" + nPara[1] + "'";
				} else if (nPara[0].equalsIgnoreCase("PSUS")) {
					sql1 = sql1 + " where psus_no='" + nPara[1] + "'";
				} else if (nPara[0].equalsIgnoreCase("HEAD")) {
					sql1 = sql1 + " where chl_head_code='" + nPara[1] + "'";
				}
				if (!(nPara[2]==null || nPara[2].equalsIgnoreCase(null))) {
					sql1 = sql1 + " and chl_head_code='" + nPara[2] + "'";
				}
				sql1 = sql1 + " and usr_id<>'" + username + "'";
				System.out.println("--"+sql1);
				PreparedStatement stmt = conn.prepareStatement(sql1);
				ResultSet rs = stmt.executeQuery();
	
				if (!rs.isBeforeFirst()) {
					nRet = "";
				} else {
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("id"));
						list.add(rs.getString("psus_no"));
						list.add(rs.getString("sus_no"));
						list.add(rs.getString("chl_head_code"));
						list.add(rs.getString("usr_id"));
						if (enc.equalsIgnoreCase("")) {
							li.add(list);
						} else {
							if (list.size() != 0) {
								List<String> FList = fp1_Dao.getMNHEncList(list, s1);
								li.add((ArrayList<String>) FList);
							} else {
								li.add(list);
							}
						}
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public @ResponseBody List<Map<String, Object>> getProcDetls() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String nSql = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			nSql = "select a.id,proc_item,proc_so_ref,to_char(proc_so_date,'dd Mon yyyy') as proc_so_date,auth_unit,b.label as auth_unit_n,proc_qty,to_char(delvry_period,'dd Mon yyyy') as delvry_period,prog_qty,prog_stg,remarks,spon_agency,del_flag,cons_depot,cons_qty,coalesce(data_upd_by,data_cr_by) data_upd_by,to_char(coalesce(data_upd_date,data_cr_date),'dd Mon yyyy') as data_upd_date,proc_agency,proc_cate,proc_route from fp.miso_proc_state  a";
			nSql += " left join mms_domain_values b on b.domainid='ACCOUNTINGUNITS' and a.auth_unit=b.codevalue";
			nSql += " where del_flag='N'";
			stmt = conn.prepareStatement(nSql);
			System.out.println("getProcDetls"+stmt.toString());
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
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
	
	public @ResponseBody String misoprocupdate(HttpSession s1, String ngid,String ngitem, String ngsoref, 
			String ngsodate, String ngauthunit, String ngqty, String ngdelpd,String ngprogqty,
			String ngprogstg,String ngspondte,String ngrem,String ngconsdepot,String ngconsqty,String ngprocdte,String ngcate,String ngroute ) {
			
			String[] ngida=ngid.split("_");
			String nSql="";
			String data_cr_by = s1.getAttribute("username").toString();
			String del_flag = "N";
			String actmsg="";
			Connection conn = null;
			Date date=new Date(0);
			int out;
			try{	  
				//java.sql.Date.valueOf(ngdelpd)
				conn = dataSource.getConnection();				
				if (ngida[0].equalsIgnoreCase("A")) {
					del_flag = "N";
					PreparedStatement stmt =null;
					nSql = "insert into fp.miso_proc_state (proc_item,proc_so_ref,proc_so_date,auth_unit,proc_qty,delvry_period,prog_qty,prog_stg,spon_agency,remarks,data_cr_by,data_cr_date,del_flag,cons_depot,cons_qty,proc_agency,proc_cate,proc_route)";
					nSql +=" values (?,?,?::timestamp,?,?,?::timestamp,?,?,?,?,?,now(),?,?,?,?,?,?)";				
		            stmt=conn.prepareStatement(nSql);		            
		            stmt.setString(1, ngitem);
		            stmt.setString(2, ngsoref);
		            stmt.setString(3, ngsodate);
		            stmt.setString(4, ngauthunit);
		            stmt.setInt(5, Integer.parseInt(ngqty));
		            stmt.setString(6, ngdelpd);
		            stmt.setInt(7, Integer.parseInt(ngprogqty));
		            stmt.setString(8, ngprogstg);
		            stmt.setString(9, ngspondte);
		            stmt.setString(10, ngrem);
		            stmt.setString(11, data_cr_by);
		            stmt.setString(12, del_flag);
		            stmt.setString(13, ngconsdepot);
		            stmt.setInt(14, Integer.parseInt(ngconsqty));
		            stmt.setString(15, ngprocdte);
		            stmt.setString(16, ngcate);
		            stmt.setString(17, ngroute);
		            System.out.println("Insert sql - "+stmt.toString());
		            out = stmt.executeUpdate();            
		            stmt.close();
		            if(out > 0) {
		            	actmsg="Data Inserted Successfully.";
		            } else {
		            	actmsg="Data Insertion Failed.";
		            }
				}
				if (ngida[0].equalsIgnoreCase("U")) {
					del_flag = "N";
					PreparedStatement stmt =null;
					nSql = " update fp.miso_proc_state set proc_item=?,proc_so_ref=?,proc_so_date=?::timestamp,";
					nSql +=" auth_unit=?,proc_qty=?,delvry_period=?::timestamp,prog_qty=?,prog_stg=?,spon_agency=?,";
					nSql +=" remarks=?,data_upd_by=?,data_upd_date=now(),del_flag=?,cons_depot=?,cons_qty=?,proc_agency=?,proc_cate=?,proc_route=? where id=?::integer";
		            stmt=conn.prepareStatement(nSql);		            
		            stmt.setString(1, ngitem);
		            stmt.setString(2, ngsoref);
		            stmt.setString(3, ngsodate);
		            stmt.setString(4, ngauthunit);
		            stmt.setInt(5, Integer.parseInt(ngqty));
		            stmt.setString(6, ngdelpd);
		            stmt.setInt(7, Integer.parseInt(ngprogqty));
		            stmt.setString(8, ngprogstg);
		            stmt.setString(9, ngspondte);
		            stmt.setString(10, ngrem);
		            stmt.setString(11, data_cr_by);
		            stmt.setString(12, del_flag);		            
		            stmt.setString(13, ngconsdepot);
		            stmt.setString(14, ngconsqty);
		            stmt.setString(15, ngprocdte);
		            stmt.setString(16, ngcate);
		            stmt.setString(17, ngroute);
		            stmt.setInt(18, Integer.parseInt(ngida[1]));
		            System.out.println("Update sql - "+stmt.toString());
		            out = stmt.executeUpdate();            
		            stmt.close();
		            if(out > 0) {
		            	actmsg="Data Updated Successfully.";
		            } else {
		            	actmsg="Data Upadation Failed.";
		            }
				}
				if (ngida[0].equalsIgnoreCase("D")) {
					del_flag = "Y";
					PreparedStatement stmt =null;
					nSql = " update fp.miso_proc_state set del_flag=? where id=?::integer";
		            stmt=conn.prepareStatement(nSql);		            
		            stmt.setString(1, del_flag);
		            stmt.setString(2, ngida[1]);		            
		            System.out.println("Delete sql - "+stmt.toString());
		            out = stmt.executeUpdate();            
		            stmt.close();
		            if(out > 0) {
		            	actmsg="Data Deleted Successfully.";
		            } else {
		            	actmsg="Data Deletion Failed.";
		            }
				}
			 }catch (SQLException e) {
				 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch(SQLException e){
					 }
				 }
			 }
			 return actmsg;
	}
}
