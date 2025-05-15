package com.dao.psg.Report;

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

public class SearchByIdentityDaoImpl implements SearchByIdentityDao {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Map<String, Object>> search_identity(String cat,String id_cardno,String pr_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//String flag = "Y";

//			if (!cat.equals("")) {
//				qry += " Where upper (battalion_name) like ?";
//				//flag = "N";
//			}
		
		if(cat.equals("1")) {
			if (!id_cardno.equals("")) {
				qry += " and pi.id_card_no=? ";
				//flag = "N";
			}
			if (!pr_no.equals("")) {
				qry += " and cl.personnel_no=? ";
				//flag = "N";
			}

			 
			q = "select pr.description,to_char(cl.date_of_birth,'dd-MON-yyyy') as date_of_birth,cl.name,pi.id_card_no,pi.id from tb_psg_trans_proposed_comm_letter cl \n" + 
					"inner join cue_tb_psg_rank_app_master pr on pr.id=cl.rank \n" + 
					"inner join tb_psg_identity_card pi on pi.comm_id=cl.id and pi.status=1 \n" + 
					"where cl.id!=0  "+ qry;
		}
		else if(cat.equals("2")) {
			
				if (!id_cardno.equals("")) {
					qry += " and pi.id_card_no=? ";
					//flag = "N";
				}
				if (!pr_no.equals("")) {
					qry += " and  pjc.army_no=? ";
					//flag = "N";
				}

				 
				q = "select pjc.full_name as name,pr.description,to_char(pjc.date_of_birth,'dd-MON-yyyy') as date_of_birth,pi.id_card_no,pi.id from tb_psg_census_jco_or_p pjc\n" + 
						"inner join cue_tb_psg_rank_app_master pr on pr.id=pjc.rank \n" + 
						"inner join tb_psg_identity_card_jco pi on pi.jco_id=pjc.id   and pi.status=1 \n " + 
						"where pjc.id!=0  "+ qry;
		}
		
		
		
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!id_cardno.equals("")) {
				stmt.setString(j, id_cardno);
				j++;
			}
			if (!pr_no.equals("")) {
				stmt.setString(j, pr_no);
				
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
