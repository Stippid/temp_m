package com.dao.psg.ad_hoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class AdHocDAOImp implements AdHocDAO{

	private DataSource dataSource;
	private DataSource dataSourceOLAP;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public void setDataSourceOLAP(DataSource dataSourceOLAP) {
		this.dataSourceOLAP = dataSourceOLAP;
	}
	
	/*public List<Map<String, Object>> Search_Pers_det(String hd_column,String filter,String sort)
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q =  "select " +  hd_column + " from tb_ad_hoc_parent  " + filter + sort; 
				//q = "SELECT * FROM information_schema.tables where table_schema='public';" 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
	}*/ 
	
	
	public List<Map<String, Object>> Search_Pers_det(String create_query)
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q =  create_query;
				//q = "SELECT a1.medicine FROM tb_olap_allerg_details a1";
				//q = "SELECT * FROM information_schema.tables where table_schema='public';" 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					 ArrayList<String> get_adhoc =  new ArrayList<String>();
					 get_adhoc.add("");
					 get_adhoc.add("");
					columns.put("msg", get_adhoc);
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
	
	public List<String> getTableNameList() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "SELECT table_name FROM information_schema.tables where table_catalog='miso_psg_olap' and table_schema='public'";
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("table_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}
	
	
	public List<String> get_table1_field_List(String t1) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "SELECT column_name from INFORMATION_SCHEMA.COLUMNS \r\n" + 
					"WHERE table_catalog='miso_psg_olap' and TABLE_NAME = ?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, t1);
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				list1.add(rs1.getString("column_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}
	
	
	public List<String> get_table2_field_List(String t2) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "SELECT column_name from INFORMATION_SCHEMA.COLUMNS \r\n" + 
					"WHERE table_catalog='miso_psg_olap' and TABLE_NAME = ?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, t2);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("column_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		
		return list1;
	}
	
	
	public List<String> get_select_field_List(String query) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			String sql1 = query;
			stmt = conn.prepareStatement(sql1);
			///stmt.setString(1, t2);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString(""));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}
	
	
	/*public List<Map<String, Object>> get_select_field_List(String query)
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q =  query; 
				//q = "SELECT * FROM information_schema.tables where table_schema='public';" 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
*/
	
	
/*	public List<String> get_operator_List() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select codevalue,label from tb_olap_t_domain_value where domainid='operator'";
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, t2);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("codevalue"));
				list1.add(rs1.getString("label"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		
		return list1;
	}*/
	
	
	
	public List<Map<String, Object>> get_operator_List()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q ="select codevalue,label from tb_olap_t_domain_value where domainid='operator'"; 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
	
	
	public List<Map<String, Object>> get_table_name_List()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				//q ="select codevalue,label from tb_olap_t_domain_value where domainid='table_name'"; 
				
				q ="select concat(p1.table_name,'-', p2.split_part) as codevalue,p1.label from (	\r\n" + 
						"(select codevalue as table_name,label,id from tb_olap_t_domain_value where domainid='table_name') p1\r\n" + 
						"inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from tb_olap_t_domain_value where domainid='table_name'\r\n" + 
						") p2 on p1.table_name = p2.table_name) order by p1.id asc";

				stmt = conn.prepareStatement(q);
				
				//stmt.setInt(1, civ_id);
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
	
	
	/*public ArrayList<ArrayList<String>> getToolsValueCRV(String cp) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;	
		
			String[] arrOfStr = String.valueOf(cp).split(","); 
			StringBuilder question_personal_list3 = new StringBuilder();

			for (int i = 0; i < arrOfStr.length; i++) {							
				if(i == arrOfStr.length-1)
				{
					question_personal_list3.append("?");
				}
				else
				{
					question_personal_list3.append("?").append(",");
				}				
			}
			
			if(arrOfStr.length > 0){
				qry += "WHERE upper(insp.ba_no) in ( "+ question_personal_list3 +" ) ";
			}
					
					q = "select distinct tc.tools_nomen, tc.tools_part_no, tc.tools_qty, aum.accounting_unit,insp.ba_no\r\n" + 
							"	from tb_dgbr_insp_tools_child tc\r\n" + 
							"	inner join tb_dgbr_insp_main_eqpt_child insp on tc.p_id = insp.id\r\n" + 
							"	INNER join tb_dgbr_tools_mstr tm on tc.tools_nomen = tm.tools_name\r\n" + 
							"	INNER join tb_dgbr_accounting_unit_mstr aum on tm.tools_accounting_unit = aum.id\r\n" + 
							"	 "+qry;
			stmt = conn.prepareStatement(q);

			int j =1;
			
			//stmt.setString(1, so_no.toUpperCase());
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
				  	stmt.setString(j, arrOfStr[i] );					
					j += 1;
				  }														 
			  }		
		
			ResultSet rs = stmt.executeQuery();  
			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> alist = new ArrayList<String>();
				
				String tools_nomen = "<label id='tools_nomen"+i+"'>"+ rs.getString("tools_nomen")+"</label>";
				alist.add(tools_nomen); //0
				String tools_part_no = "<label id='tools_part_no"+i+"'>"+ rs.getString("tools_part_no")+"</label>";
				alist.add(tools_part_no); //1
				String tools_qty = "<label id='tools_qty"+i+"'>"+ rs.getString("tools_qty")+"</label>";
				alist.add(tools_qty); //2
				String accounting_unit = "<label id='accounting_unit"+i+"'>"+ rs.getString("accounting_unit")+"</label>";
				alist.add(accounting_unit); //3
				
				list.add(alist);
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
	}*/
	
	
	public List<String> get_selected_field_List(String t2) {
		
		
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		String qry = "";
		String qry1 = "";
		String sql1 = "";
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			
			
			String[] arrOfStr = String.valueOf(t2).split(","); 
			StringBuilder select_list = new StringBuilder();

			for (int i = 0; i < arrOfStr.length; i++) {							
				if(i == arrOfStr.length-1)
				{
					select_list.append("?");
				}
				else
				{
					select_list.append("?").append(",");
				}	
				
				String[]  t1 = arrOfStr[i].split("-");
			  	//stmt.setString(j, t1[0] );
				
				
				if(t1[0].equals("tb_olap_no_of_pers_auth_rank_wise") || t1[0].equals("tb_olap_no_of_pers_held_rank_wise")) {
					qry1 += "and column_name not in('rank','sus_no')";
				}
			}
			
			if(arrOfStr.length > 0){
				qry += "in ( "+ select_list +" ) ";
			}
			
			
			//String sql1 = "SELECT column_name from INFORMATION_SCHEMA.COLUMNS \r\n" + qry;
			
			/*if(t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19,tb_olap_no_of_pers_held_rank_wise-a20") || t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19") || t2.equals("tb_olap_no_of_pers_held_rank_wise-a20")) {
				qry1 += "and column_name not in('rank','sus_no')";
			}*/
			
		/*	if(t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19,tb_olap_no_of_pers_held_rank_wise-a20") || t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19") || t2.equals("tb_olap_no_of_pers_held_rank_wise-a20") ) {
				sql1 = "select concat(p2.split_part,'.', p1.column_name) as column_name from (	\r\n" + 
						"	(SELECT column_name,TABLE_NAME from INFORMATION_SCHEMA.COLUMNS \r\n" + 
						"WHERE table_catalog='miso_psg_olap' and TABLE_NAME "+qry+" "+qry1+" ) p1 inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from tb_olap_t_domain_value where domainid='table_name'\r\n" + 
						"and codevalue "+qry+" ) p2 on p1.table_name = p2.table_name)";
			}
			else {*/
				sql1 = "select concat(p2.split_part,'.', p1.column_name) as column_name from (	\r\n" + 
						"	(SELECT column_name,TABLE_NAME from INFORMATION_SCHEMA.COLUMNS \r\n" + 
						"WHERE table_catalog='miso_psg_olap' and TABLE_NAME "+qry+" ) p1 inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from tb_olap_t_domain_value where domainid='table_name'\r\n" + 
						"and codevalue "+qry+" ) p2 on p1.table_name = p2.table_name)";
			//}
			
			
			
			int j =1;
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, t2);
			
			stmt.setString(1, t2);
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
					  
					  String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }	
			 
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
                    String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }		
			
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("column_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		
		return list1;
	}
	

	
	
	public ArrayList<ArrayList<String>> getAdhocreport(String create_query) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSourceOLAP.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
  
			 sql  = create_query;
		 stmt = conn.prepareStatement(sql);
		 
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String count = "1";
			int ct = Integer.parseInt(count);
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				count = ""+ct;
				ct++;
				list.add(count);
				for (int i = 1; i < columnCount + 1; i++) {
					
					String name = rs.getString(i);
					list.add(name);
				}
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
	
	
	
	public List<Map<String, Object>> get_group_by_List()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q ="select codevalue,label from tb_olap_t_domain_value where domainid='group_by'"; 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
	
	
	
	public List<Map<String, Object>> get_catgory_List()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSourceOLAP.getConnection();
				PreparedStatement stmt = null;

				q ="select codevalue,label from tb_olap_t_domain_value where domainid='catgory' "; 
                //and codevalue!='or'
				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
	
	
	
	public ArrayList<ArrayList<String>> get_categorywise_tabel_List(String t11) {
		
		
		 ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";
		String qry1 = "";
		String sql1 = "";
		try {
			conn = dataSourceOLAP.getConnection();
			PreparedStatement stmt = null;
			
			
			String[] arrOfStr = String.valueOf(t11).split(","); 
			StringBuilder select_list = new StringBuilder();

			for (int i = 0; i < arrOfStr.length; i++) {							
				if(i == arrOfStr.length-1)
				{
					select_list.append("?");
				}
				else
				{
					select_list.append("?").append(",");
				}	
				
				String[]  t1 = arrOfStr[i].split("-");
			  	//stmt.setString(j, t1[0] );
				
				
			}
			
			if(arrOfStr.length > 0){
				qry += " where p1.category in ( "+ select_list +" ) ";
			}
			
				//sql1 = "select codevalue,label from tb_olap_t_domain_value "+qry ;
				
				
			sql1 ="select concat(p1.table_name,'-', p2.split_part) as codevalue,p1.label from (	\r\n" + 
						"(select codevalue as table_name,label,id,category from tb_olap_t_domain_value where domainid='table_name') p1\r\n" + 
						"inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from tb_olap_t_domain_value where domainid='table_name'\r\n" + 
						") p2 on p1.table_name = p2.table_name) "+ qry +"order by p1.id asc";
				
			int j =1;
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, t11);
			
			//stmt.setString(1, t11);
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
					  
					  String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }	
			 
		
			
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				
				ArrayList<String> list2 = new ArrayList<String>();
				
				list2.add(rs1.getString("codevalue"));
				list2.add(rs1.getString("label"));
				list1.add(list2);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		
		return list1;
	}
	
	
	
}
