package com.dao.psg.Civilian_Report;

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

public class Arm_Service_Wise_RegularEst_DaoImpl implements Arm_Service_Wise_RegularEst_Dao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	 
	 public ArrayList<ArrayList<String>> Search_Arm_Service_Wise_Regular()
		{
		 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					q="select  app.arm_desc,app.groupA_Gaz,app.groupB_Gaz,app.groupB_NonGaz_NonIn,app.groupC_NonGaz_NonIn,app.groupB_NonGaz_In,app.groupC_NonGaz_In,\r\n" + 
							"sum(app.non_total+app.non_total1) as total\r\n" + 
							"from\r\n" + 
							"(select a.arm_desc,\r\n" + 
							"    count(*) filter (where c.civ_group not in ('A') and c.classification_services = '2') as non_total,\r\n" + 
							"   count(*) filter (where c.civ_group not in ('C') and c.classification_services = '1') as non_total1,\r\n" + 
							"   count(*) filter (where c.civ_group = 'A' and c.classification_services = '1') as groupA_Gaz,\r\n" + 
							"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '1') as groupB_Gaz,\r\n" + 
							"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '2') as groupB_NonGaz_NonIn,\r\n" + 
							"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '2') as groupC_NonGaz_NonIn,\r\n" + 
							"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '1') as groupB_NonGaz_In,\r\n" + 
							"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '1') as groupC_NonGaz_In\r\n" + 
							"from tb_psg_civilian_dtl_main c \r\n" + 
							"inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no\r\n" + 
							"inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
							"where c.civilian_status = 'R' and c.status = '1' group by a.arm_desc) app\r\n" + 
							"group by app.arm_desc,app.groupA_Gaz,app.groupB_Gaz,app.groupB_NonGaz_NonIn,app.groupC_NonGaz_NonIn,app.groupB_NonGaz_In,app.groupC_NonGaz_In" ;
			
				
				stmt=conn.prepareStatement(q);
				System.err.println("query for agewise: \n" + stmt);
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      int i=0;
		     
		      while (rs.next()) {
		    	  i++;
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i));//0
		    	  list.add(rs.getString("arm_desc"));//0
		    	  list.add(rs.getString("groupA_Gaz"));//1
		    	  list.add(rs.getString("groupB_Gaz"));//2
		    	  list.add(rs.getString("groupB_NonGaz_NonIn"));//3
		    	  list.add(rs.getString("groupC_NonGaz_NonIn"));//4
		    	  list.add(rs.getString("groupB_NonGaz_In"));//5
				  list.add(rs.getString("groupC_NonGaz_In"));//6
		    	  list.add(rs.getString("total"));//7
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
	 
	 public ArrayList<ArrayList<String>> getLocation_Trn(String to_sus_no) {

			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

			Connection conn = null;

			try {

				conn = dataSource.getConnection();

				PreparedStatement stmt = null;

				String qry = "";



				qry = "select distinct a.code_value as location,a.mod_desc as trn_type\r\n" + 

						"from tb_miso_orbat_code a,\r\n" + 

						"tb_miso_orbat_code b,\r\n" + 

						"t_domain_value  c,\r\n" + 

						"tb_miso_orbat_unt_dtl n \r\n" + 

						"where a.code_type='Location' and b.code_type = 'Location' and n.status_sus_no='Active' \r\n" + 

						"and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and \r\n" + 

						"a.nrs_code = n.nrs_code and n.sus_no = ? ";

				

				stmt = conn.prepareStatement(qry);

				stmt.setString(1,to_sus_no);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("location"));	

					list.add(rs.getString("trn_type"));					

					alist.add(list);						

				}

				rs.close();

				stmt.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

			return alist;

		}
	 
	 public ArrayList<ArrayList<String>> getCivcommand(String roleSusNo) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String qry = "";

				qry = "select distinct c.userid,\r\n" + 
						"fv.sus_no as command,\r\n" + 
						"fvm.sus_no as corps,\r\n" + 
						"div.sus_no as division,\r\n" + 
						"bde.sus_no as brigade, \r\n" + 
						"fv.unit_name as cmd_unit,\r\n" + 
						"fvm.unit_name as corp_unit,\r\n" + 
						"div.unit_name as div_unit,\r\n" + 
						"bde.unit_name as bde_unit \r\n" + 
						"from logininformation c \r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.user_sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = c.user_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = c.user_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"left join all_fmn_view div  on orb.sus_no = c.user_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = c.user_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						"where c.user_sus_no = ?";
				stmt = conn.prepareStatement(qry);
				stmt.setString(1,roleSusNo);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("userid"));	
					list.add(rs.getString("command"));	
					list.add(rs.getString("corps"));	
					list.add(rs.getString("division"));	
					list.add(rs.getString("brigade"));	
					list.add(rs.getString("cmd_unit"));	
					list.add(rs.getString("corp_unit"));	
					list.add(rs.getString("div_unit"));	
					list.add(rs.getString("bde_unit"));	
					alist.add(list);
					
			
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return alist;
		}
	 
		public List<Map<String, Object>> getLastPostInOutCIVILIAN(int civ_id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";		
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
								 
				q = "select * from tb_psg_posting_in_out_civilian where civ_id=? and status not in ('-1','3')   order by id desc limit 2 ";

				stmt = conn.prepareStatement(q);
				stmt.setInt(1,civ_id);
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
}
