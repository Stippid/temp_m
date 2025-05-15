package com.dao.psg.OfficerReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Actual_Str_Rank_WiseDAOImpl implements Actual_Str_Rank_WiseDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_actual_str_rank_wise()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		
		 try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;		

				q = "select p.parent_arm,a.arm_desc,\n" + 
						"count(*) filter (where r.description = 'FD MARSHAL') as FM,\n" + 
						"count(*) filter (where r.description = 'GEN') as GEN,\n" + 
						"count(*) filter (where r.description = 'LT GEN') as LT,\n" + 
						"count(*) filter (where r.description = 'MAJ GEN') as MAJGEN,\n" + 
						"count(*) filter (where r.description = 'BRIG') as BRIG,\n" + 
						"count(*) filter (where r.description = 'COL') as COL,\n" + 
						"count(*) filter (where r.description = 'COL [TS]') as COLTS,\n" + 
						"count(*) filter (where r.description = 'LT COL') as LTCOL,\n" + 
						"count(*) filter (where r.description = 'MAJ') as MAJ,\n" + 
						"count(*) filter (where r.description = 'CAPT') as CAPT,\n" + 
						"count(*) filter (where r.description = 'LT') as LT,count(p.rank),(Count(p.rank)* 100 / (Select Count(*)) )as percentage\n" + 
						"from CUE_TB_PSG_RANK_APP_MASTER r\n" + 
						"inner join tb_psg_trans_proposed_comm_letter p on p.rank=r.id\n" + 
						"inner join tb_miso_orbat_arm_code a on a.arm_code = p.parent_arm\n" + 
						"where upper(r.level_in_hierarchy) = 'RANK' group by 1,2 order by a.arm_desc";
		
		
			stmt = conn.prepareStatement(q);
			   
		      ResultSet rs = stmt.executeQuery();  
		    
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("parent_arm"));
					list.add(rs.getString("arm_desc"));
					list.add(rs.getString("FM"));	
					list.add(rs.getString("GEN"));
					list.add(rs.getString("LT"));
					list.add(rs.getString("MAJGEN"));
					list.add(rs.getString("BRIG"));
					list.add(rs.getString("COL"));
					list.add(rs.getString("COLTS"));
					list.add(rs.getString("LTCOL"));
					list.add(rs.getString("MAJ"));
					list.add(rs.getString("CAPT"));
					list.add(rs.getString("LT"));
					list.add(rs.getString("count"));
					list.add(rs.getString("percentage"));
				
				
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
			
			q = "select p.parent_arm,a.arm_desc,\n" + 
					"count(*) filter (where r.description = 'FD MARSHAL') as FM,\n" + 
					"count(*) filter (where r.description = 'GEN') as GEN,\n" + 
					"count(*) filter (where r.description = 'LT GEN') as LT,\n" + 
					"count(*) filter (where r.description = 'MAJ GEN') as MAJGEN,\n" + 
					"count(*) filter (where r.description = 'BRIG') as BRIG,\n" + 
					"count(*) filter (where r.description = 'COL') as COL,\n" + 
					"count(*) filter (where r.description = 'COL [TS]') as COLTS,\n" + 
					"count(*) filter (where r.description = 'LT COL') as LTCOL,\n" + 
					"count(*) filter (where r.description = 'MAJ') as MAJ,\n" + 
					"count(*) filter (where r.description = 'CAPT') as CAPT,\n" + 
					"count(*) filter (where r.description = 'LT') as LT,count(p.rank),(Count(p.rank)* 100 / (Select Count(*)) )as percentage\n" + 
					"from CUE_TB_PSG_RANK_APP_MASTER r\n" + 
					"inner join tb_psg_trans_proposed_comm_letter p on p.rank=r.id\n" + 
					"inner join tb_miso_orbat_arm_code a on a.arm_code = p.parent_arm\n" + 
					"where upper(r.level_in_hierarchy) = 'RANK' group by 1,2 order by a.arm_desc";
			
			stmt = conn.prepareStatement(q);
			

			ResultSet rs = stmt.executeQuery();
			int i=1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++));//0
				list.add(rs.getString("parent_arm"));//1
				list.add(rs.getString("arm_desc"));//2
				list.add(rs.getString("FM"));//3	
				list.add(rs.getString("GEN"));//4
				list.add(rs.getString("LT"));//5
				list.add(rs.getString("MAJGEN"));//6
				list.add(rs.getString("BRIG"));//7
				list.add(rs.getString("COL"));//8
				list.add(rs.getString("COLTS"));//9
				list.add(rs.getString("LTCOL"));//10
				list.add(rs.getString("MAJ"));//11
				list.add(rs.getString("CAPT"));//12
				list.add(rs.getString("LT"));//13
				list.add(rs.getString("count"));//14
				list.add(rs.getString("percentage"));//15
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
