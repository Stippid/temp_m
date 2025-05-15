package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Actual_Str_UserDAOImpl implements Actual_Str_UserDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_actual_str_user()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

				q = "select u.ct_part_i_ii,a.arm_code,a.arm_desc,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMOURED CORPS') as AC,\n" + 
						"count(*) filter (where a.arm_desc = 'ARTILLERY') as ARTY,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY AIR DEFENCE') as AAD,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY AVIATION') as AVN,\n" + 
						"count(*) filter (where a.arm_desc = 'ENGINEERS') as ENG,\n" + 
						"count(*) filter (where a.arm_desc = 'SIGNALS') as SNG,\n" + 
						"count(*) filter (where a.arm_desc = 'GORKHA') as GR,\n" + 
						"count(*) filter (where a.arm_desc = 'INFANTRY') as INF,\n" + 
						"count(*) filter (where a.arm_desc = 'MECHANISED INFANTRY') as MECH,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY SERVICE CORPS') as ASC,\n" + 
						"count(*) filter (where a.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as EME,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY POSTAL SERVICES') as APS,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY EDUCATION CORPS') as AEC,\n" + 
						"count(*) filter (where a.arm_desc = 'INTELLIGENCE CORPS') as INT,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as APTC,\n" + 
						"count(*) filter (where a.arm_desc = 'GL & RASO') as SLRO,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY MEDICAL CORPS') as AMC,\n" + 
						"count(*) filter (where a.arm_desc = 'ARMY DENTAL CORPS') as ADC,\n" + 
						"count(*) filter (where a.arm_desc = 'REMOUNT AND VETERINARY CORPS') as RVC,\n" + 
						"count(*) filter (where a.arm_desc = 'MILITARY FARMS') as MF,count(*)\n" + 
						"from tb_miso_orbat_unt_dtl u\n" + 
						"left join tb_miso_orbat_arm_code a on a.arm_code=u.arm_code where  u.status_sus_no='Active' and u.ct_part_i_ii != '' and u.ct_part_i_ii ='CTPartI' or u.ct_part_i_ii ='CTPartII'\n" + 
						"group by 1,2,3 order by u.ct_part_i_ii";
		
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		     
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("ct_part_i_ii"));
					list.add(rs.getString("arm_code"));
					list.add(rs.getString("arm_desc"));					
					list.add(rs.getString("AC"));
					list.add(rs.getString("ARTY"));
					list.add(rs.getString("AAD"));					
					list.add(rs.getString("AVN"));
					list.add(rs.getString("ENG"));
					list.add(rs.getString("SNG"));					
					list.add(rs.getString("GR"));
					list.add(rs.getString("INF"));
					list.add(rs.getString("MECH"));					
					list.add(rs.getString("ASC"));
					list.add(rs.getString("EME"));
					list.add(rs.getString("APS"));					
					list.add(rs.getString("AEC"));
					list.add(rs.getString("INT"));
					list.add(rs.getString("APTC"));
					list.add(rs.getString("SLRO"));					
					list.add(rs.getString("AMC"));
					list.add(rs.getString("ADC"));
					list.add(rs.getString("RVC"));
					list.add(rs.getString("MF"));
					list.add(rs.getString("count"));
					
					
					alist.add(list);					
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
			return alist;
		}	
	
public ArrayList<ArrayList<String>> Search_pdf()  {

		
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select u.ct_part_i_ii,a.arm_code,a.arm_desc,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMOURED CORPS') as AC,\n" + 
					"count(*) filter (where a.arm_desc = 'ARTILLERY') as ARTY,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY AIR DEFENCE') as AAD,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY AVIATION') as AVN,\n" + 
					"count(*) filter (where a.arm_desc = 'ENGINEERS') as ENG,\n" + 
					"count(*) filter (where a.arm_desc = 'SIGNALS') as SNG,\n" + 
					"count(*) filter (where a.arm_desc = 'GORKHA') as GR,\n" + 
					"count(*) filter (where a.arm_desc = 'INFANTRY') as INF,\n" + 
					"count(*) filter (where a.arm_desc = 'MECHANISED INFANTRY') as MECH,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY SERVICE CORPS') as ASC,\n" + 
					"count(*) filter (where a.arm_desc = 'ELECTRONICS AND MECHANICAL ENGINEERS') as EME,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY POSTAL SERVICES') as APS,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY EDUCATION CORPS') as AEC,\n" + 
					"count(*) filter (where a.arm_desc = 'INTELLIGENCE CORPS') as INT,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY PHYSICAL TRAINING CORPS') as APTC,\n" + 
					"count(*) filter (where a.arm_desc = 'GL & RASO') as SLRO,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY MEDICAL CORPS') as AMC,\n" + 
					"count(*) filter (where a.arm_desc = 'ARMY DENTAL CORPS') as ADC,\n" + 
					"count(*) filter (where a.arm_desc = 'REMOUNT AND VETERINARY CORPS') as RVC,\n" + 
					"count(*) filter (where a.arm_desc = 'MILITARY FARMS') as MF,count(*)\n" + 
					"from tb_miso_orbat_unt_dtl u\n" + 
					"left join tb_miso_orbat_arm_code a on a.arm_code=u.arm_code where  u.status_sus_no='Active' and u.ct_part_i_ii != ''and u.ct_part_i_ii ='CTPartI' or u.ct_part_i_ii ='CTPartII'\n" + 
					"group by 1,2,3 order by u.ct_part_i_ii";

			
			
			stmt = conn.prepareStatement(q);
			
			
			int i = 1;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));
				list.add(rs.getString("ct_part_i_ii"));
				list.add(rs.getString("arm_code"));
				list.add(rs.getString("arm_desc"));					
				list.add(rs.getString("AC"));
				list.add(rs.getString("ARTY"));
				list.add(rs.getString("AAD"));					
				list.add(rs.getString("AVN"));
				list.add(rs.getString("ENG"));
				list.add(rs.getString("SNG"));					
				list.add(rs.getString("GR"));
				list.add(rs.getString("INF"));
				list.add(rs.getString("MECH"));					
				list.add(rs.getString("ASC"));
				list.add(rs.getString("EME"));
				list.add(rs.getString("APS"));					
				list.add(rs.getString("AEC"));
				list.add(rs.getString("INT"));
				list.add(rs.getString("APTC"));
				list.add(rs.getString("SLRO"));					
				list.add(rs.getString("AMC"));
				list.add(rs.getString("ADC"));
				list.add(rs.getString("RVC"));
				list.add(rs.getString("MF"));
				list.add(rs.getString("count"));
		
				alist.add(list);
				
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
	return alist;

	}

}
