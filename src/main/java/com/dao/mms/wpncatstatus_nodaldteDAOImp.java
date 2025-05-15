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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_IUT;
import com.models.psg.Master.TB_PSG_AGENCY_MASTER;
import com.persistance.util.HibernateUtil;

public class wpncatstatus_nodaldteDAOImp implements wpncatstatus_nodaldteDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	

public List<Map<String, Object>> getwpncatnodal_dte(HttpSession session,String line_dte_sus)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="",whr ="";
			
  			if(!line_dte_sus.equals("")) {
				whr = " where a.nodal_dte = ? \r\n";
			}
  			qry ="select distinct a.item_group \r\n" + 
  					"from cue_tb_miso_mms_item_mstr \r\n" + 
  					"a inner join tb_miso_orbat_line_dte b on b.line_dte_sus= a.nodal_dte \r\n" + 
  					" "+whr+" order by item_group desc"; 
 			PreparedStatement stmt=conn.prepareStatement(qry); 			 			
 			
			if(!line_dte_sus.equals("")) {
				stmt.setString(1, line_dte_sus);
			}
			
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
	public List<Map<String, Object>> getPRFListbyItemGroup_nodal_dte(String type, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			String qry = "";
			qry = "select distinct item_code,item_type from cue_tb_miso_mms_item_mstr where   item_group = ? ";

			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, type);
		
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
	
	//code by prasanth pgmr//
	
	public List<Map<String, Object>> wep_eqpt_statusList_nodal_dte(int startPage,String pageLength,String orderColunm,String orderType,
			String Search,String line_dte_sus,String type_wep_eqpt,String item_no_list,	String comd,String corps,String div,String bde,String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			String whr = "";
			if(!sus_no.equals("")) {
				whr += " and u.sus_no='"+sus_no+"' ";
			}else {
				if (!bde.equals("0")){
					whr += " and  u.form_code_control like '"+bde+"%'";
				}else {
					if (!div.equals("0")){
						whr += " and  u.form_code_control like '"+div+"%'";
					}else {
						if (!corps.equals("0")){
							whr += "  and u.form_code_control like '"+corps+"%'";
						}else {
							if (comd != null){
								whr += " and  u.form_code_control like '"+comd+"%'";
							}
						}	
					}	
				}
			}

			if (!item_no_list.equals("")) {
				String item_noArray[] = item_no_list.split(":");
				for (int i = 0; i < item_noArray.length; i++) {
					if (i == 0) {
						whr += " and ( i.item_code = '"+item_noArray[i]+"' ";
					} else {
						whr += " or i.item_code = '"+item_noArray[i]+"' ";
					}

					if (i == (item_noArray.length - 1)) {
						whr += " ) ";
					}
				}
			}
			
			if(!line_dte_sus.equals("")) {
				whr += "and  i.nodal_dte='"+line_dte_sus+"' ";
			}
			if(!type_wep_eqpt.equals("0")) {
				whr += " and i.item_group='"+type_wep_eqpt+"' ";
			}

			conn = dataSource.getConnection();
			String sql = "";

			sql = "select c.short_form as comd,corps.coprs_name as corps,div.div_name as div,bde.bde_name as bde,\r\n" + 
					" 	u.unit_name,line.line_dte_name,i.item_type,\r\n" + 
					"	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'UE'::text),0) as UE, \r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text),0) as UH, \r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A14'::text),0) as LS, \r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A5'::text),0) as SS, \r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A16'::text),0) as AC, \r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg = 'R0'::text or r.type_of_hldg = 'R1'::text) and r.d_type = 'UH'),0) as wwr_unit,\r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE (r.type_of_hldg ='R6' or r.type_of_hldg = 'R0'::text) and r.d_type = 'DH'),0) as wwr_depot,\r\n" + 
					" 	COALESCE(sum(r.tothol) FILTER (WHERE r.type_of_hldg = 'A0'::text or r.type_of_hldg = 'A14' or r.type_of_hldg = 'A5' or r.type_of_hldg = 'A16' \r\n" + 
					" 	or (r.type_of_hldg = 'R0'::text and r.d_type = 'UH') or (r.type_of_hldg in ('R0','R6') and r.d_type = 'DH')),0) as total_uh\r\n" + 
					" from \r\n" + 
					" (select rg.d_type,rg.sus_no,b.item_code,rg.type_of_hldg,rg.tothol from mms_tv_regn_mstr rg \r\n" + 
					" 	inner join mms_tb_mlccs_mstr_detl b on rg.census_no=b.census_no  \r\n" + 
					"  	inner join tb_miso_orbat_unt_dtl u ON rg.sus_no = u.sus_no and u.status_sus_no ='Active'\r\n" + 
					"  	inner join cue_tb_miso_mms_item_mstr i on b.item_code=i.item_code \r\n"+
					"	where i.item_code is not null "+whr+" \r\n" + 
					"  union all \r\n" + 
					"select 'UE' as d_type,m.sus_no,m.item_code,'UE' as type_of_hldg,m.total_ue_qty \r\n" + 
					" from mms_ue_mview m\r\n" + 
					" inner join tb_miso_orbat_unt_dtl u ON m.sus_no = u.sus_no and u.status_sus_no ='Active' \r\n" + 
					" inner join cue_tb_miso_mms_item_mstr i on m.item_code=i.item_code \r\n"+
					"	where i.item_code is not null "+whr+" \r\n" +  
					"  ) r\r\n" + 
					" inner join tb_miso_orbat_unt_dtl u ON r.sus_no = u.sus_no and u.status_sus_no ='Active'\r\n" + 
					" inner join cue_tb_miso_mms_item_mstr i on r.item_code = i.item_code \r\n" + 
					" left join tb_miso_orbat_line_dte line on u.arm_code=line.arm_code\r\n" + 
					" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code \r\n" + 
					" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n" + 
					" left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code \r\n" + 
					" left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
					" group by 1,2,3,4,5,6,7\r\n" + 
					" order by 1,2,3,4,6,7";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
		
		
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
			e.printStackTrace();
			return list;
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
}