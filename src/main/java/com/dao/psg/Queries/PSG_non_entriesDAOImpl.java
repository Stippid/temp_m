package com.dao.psg.Queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class PSG_non_entriesDAOImpl implements PSG_non_entriesDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
   
	
////bisag v2 190922(new screen)
		
		public ArrayList<ArrayList<String>> search_officer_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
		{
			
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				qry = "a.id !=  0";
				if( !cont_comd.equals("0")) {
				
					qry += "  and orb.form_code_control like ?  ";	
					}
					
				
				if( !cont_corps.equals("0")) {
					
					qry += " and  orb.form_code_control like ?  ";	
				
				}
				if( !cont_div.equals("0")) {
					
					qry += " and  orb.form_code_control like ? ";
					}
				
				if( !cont_bde.equals("0")) {
					
					qry += " and  orb.form_code_control like ? ";
					}
				
				
				if( !unit_name.equals("")) {
					
					qry += " and  orb.unit_name = ? ";	
					
					
				}
				
				if( !sus_no.equals("")) {
					
					qry += " and orb.sus_no = ? ";	
					
				}
				q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n" + 
						"sum(case when a.status ='1'then 1 else 0 end) as approved,\r\n" + 
						"sum(case when a.status ='0'then 1 else 0 end) as pending,\r\n" + 
						"COUNT (a.Rank) AS TOTAL\r\n" + 
						"from tb_psg_trans_proposed_comm_letter a\r\n" + 
						"inner join tb_psg_census_detail_p b on b.comm_id = a.id  \r\n" + 
						"left join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n" + 
						"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'											\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n" + 
						"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no \r\n" + 
						" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						" where "+qry+" group by c.cmd_name,c.coprs_name,c.div_name,c.bde_name, c.unit_name   order by c.cmd_name ";
				
					stmt=conn.prepareStatement(q);
					if(!qry.equals(""))     
					{ int j =1;
					if(!cont_comd.equals("0")) {
						
						stmt.setString(j, cont_comd.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_corps.equals("0")) {
						
						stmt.setString(j, cont_corps.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_div.equals("0")) {
						
						stmt.setString(j, cont_div.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_bde.equals("0")) {
						
						stmt.setString(j, cont_bde.toUpperCase()+"%");
						j += 1;		
					}
					if(!unit_name.equals("")) {
						
						stmt.setString(j, unit_name.toUpperCase());
						j += 1;		
					}
					if(!sus_no.equals("")) {
						
						stmt.setString(j, sus_no.toUpperCase());
						j += 1;		
					}
					}
			   ResultSet rs = stmt.executeQuery();  
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("cmd_name"));//0
						list.add(rs.getString("coprs_name"));//1
						list.add(rs.getString("div_name"));//2
						list.add(rs.getString("bde_name"));//3
						list.add(rs.getString("unit_name"));//4
						list.add(rs.getString("approved"));//5
						list.add(rs.getString("pending"));//6
						list.add(rs.getString("TOTAL"));//7
						list.add(String.valueOf(rs.getString("TOTAL")));// 8
						
						
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
		
		///////////////////////
		
		public ArrayList<ArrayList<String>> search_jco_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
		{
			
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				qry = "a.id !=  0";
				if( !cont_comd.equals("0")) {
					
					qry += "  and orb.form_code_control like ?  ";	
					}
					
				
				if( !cont_corps.equals("0")) {
					
					qry += " and  orb.form_code_control like ?  ";	
				
				}
				if( !cont_div.equals("0")) {
					
					qry += " and  orb.form_code_control like ? ";
					}
				
				if( !cont_bde.equals("0")) {
					
					qry += " and  orb.form_code_control like ? ";
					}
				
				
				if( !unit_name.equals("")) {
					
					qry += " and  orb.unit_name = ? ";	
					
					
				}
				
				if( !sus_no.equals("")) {
					
					qry += " and orb.sus_no = ? ";	
					
				}
				q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name, \r\n" + 
						"sum(case when a.status ='1'then 1 else 0 end) as approved,\r\n" + 
						"sum(case when a.status ='0'then 1 else 0 end) as pending,\r\n" + 
						"COUNT (a.army_no) AS TOTAL\r\n" + 
						"from tb_psg_census_jco_or_p a\r\n" + 
						"left join orbat_all_details_view c on c.sus_no = a.unit_sus_no and c.status_sus_no = 'Active'\r\n" + 
						"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and orb.status_sus_no='Active'											\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = a.unit_sus_no\r\n" + 
						"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = a.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = a.unit_sus_no \r\n" + 
						" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						" where "+qry+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name ";
					stmt=conn.prepareStatement(q);
					if(!qry.equals(""))     
					{ int j =1;
					if(!cont_comd.equals("0")) {
						
						stmt.setString(j, cont_comd.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_corps.equals("0")) {
						
						stmt.setString(j, cont_corps.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_div.equals("0")) {
						
						stmt.setString(j, cont_div.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_bde.equals("0")) {
						
						stmt.setString(j, cont_bde.toUpperCase()+"%");
						j += 1;		
					}
					if(!unit_name.equals("")) {
						
						stmt.setString(j, unit_name.toUpperCase());
						j += 1;		
					}
					if(!sus_no.equals("")) {
						
						stmt.setString(j, sus_no.toUpperCase());
						j += 1;		
					}
					}
			   ResultSet rs = stmt.executeQuery();  
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("cmd_name"));//0
						list.add(rs.getString("coprs_name"));//1
						list.add(rs.getString("div_name"));//2
						list.add(rs.getString("bde_name"));//3
						list.add(rs.getString("unit_name"));//4
						list.add(rs.getString("approved"));//5
						list.add(rs.getString("pending"));//6
						list.add(rs.getString("TOTAL"));//7
					
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
		
		
		public ArrayList<ArrayList<String>> search_civilian_en_table(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				qry = "a.id !=  0";
			if( !cont_comd.equals("0")) {
				
				qry += "   and orb.form_code_control like ?  ";	
				}
				
			
			if( !cont_corps.equals("0")) {
				
				qry += " and  orb.form_code_control like ?  ";	
			
			}
			if( !cont_div.equals("0")) {
				
				qry += " and  orb.form_code_control like ? ";
				}
			
			if( !cont_bde.equals("0")) {
				
				qry += " and  orb.form_code_control like ? ";
				}
			
			
			if( !unit_name.equals("")) {
				
				qry += " and  orb.unit_name = ? ";	
				
				
			}
			
			if( !sus_no.equals("")) {
				
				qry += " and orb.sus_no = ? ";	
				
			}
		
			q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
					"from  orbat_all_details_view c \r\n" + 
					"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
					"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
					"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
					" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
					" where upper(orb.sus_no) not in (select upper(sus_no) \r\n" + 
					"from tb_psg_civilian_dtl) group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name ";
				
					stmt=conn.prepareStatement(q);
					if(!qry.equals(""))     
					{ int j =1;
					if(!cont_comd.equals("0")) {
						
						stmt.setString(j, cont_comd.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_corps.equals("0")) {
						
						stmt.setString(j, cont_corps.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_div.equals("0")) {
						
						stmt.setString(j, cont_div.toUpperCase()+"%");
						j += 1;		
					}
					if(!cont_bde.equals("0")) {
						
						stmt.setString(j, cont_bde.toUpperCase()+"%");
						j += 1;		
					}
					if(!unit_name.equals("")) {
						
						stmt.setString(j, unit_name.toUpperCase());
						j += 1;		
					}
					if(!sus_no.equals("")) {
						
						stmt.setString(j, sus_no.toUpperCase());
						j += 1;		
					}
					}
			   ResultSet rs = stmt.executeQuery();  
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("cmd_name"));//0
						list.add(rs.getString("coprs_name"));//1
						list.add(rs.getString("div_name"));//2
						list.add(rs.getString("bde_name"));//3
						list.add(rs.getString("unit_name"));//4
						list.add(rs.getString("approved"));//5
						list.add(rs.getString("pending"));//6
						list.add(rs.getString("TOTAL"));//7
					
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
		///////////officer
	////bisag v2 200922(converted in data table )
		 public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					SearchValue +="( upper(c.cmd_name) like ? or upper(c.coprs_name) like ? or upper(c.div_name) like ? "
							+ " or upper(c.bde_name) like ? or upper(c.unit_name) like ?  ) ";
				}
				
				if( !cont_comd.equals("0")) {
					/*if (SearchValue.contains("and")) {
						SearchValue += " where  orb.form_code_control like ?  ";	
					}
					else {*/
						SearchValue += " and orb.form_code_control like ? ";
					//}
				}
				if( !cont_corps.equals("0")) {
					/*	if (SearchValue.contains("and")) {
						SearchValue += " where  orb.form_code_control like ?  ";	
					}
					else {*/
						SearchValue += " and  orb.form_code_control like ? ";
					//}
				}
				if( !cont_div.equals("0")) {
					/*if (SearchValue.contains("and")) {
						SearchValue += " where  orb.form_code_control like ?  ";	
					}
					else {*/
						SearchValue += " and  orb.form_code_control like ? ";
					//}
				}
				if( !cont_bde.equals("0")) {
					/*if (SearchValue.contains("and")) {
						SearchValue += " where  orb.form_code_control like ?  ";	
					}
					else {*/
						SearchValue += " and  orb.form_code_control like ? ";
					//}
				}
				
			/*	if( !unit_name.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.unit_name = ? ";	
					}
					else {
						SearchValue += " where orb.unit_name like ?";
					}
				}
				
				
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and orb.sus_no = ? ";	
					}
					else {
						SearchValue += " where orb.sus_no = ? ";
					}
				}*/
				if( !user_role_id.equals("0")) {
					/*if (SearchValue.contains("and")) {
						SearchValue += " where cast(lm.userId as character varying) = ? ";	
					}
					else {*/
						SearchValue += " and cast(lm.userId as character varying) = ? ";
					//}
				}
				if( !from_date.equals("") && !to_date.equals("")) {
					/*if (SearchValue.contains("and")) {
						SearchValue += " where  cast(b.created_date as date) >= cast(? as date) and cast(b.created_date as date) <= cast(? as date) ";	
					}
					else {*/
						SearchValue += " and cast(b.created_date as date) >= cast(? as date) and cast(b.created_date as date) <= cast(? as date) ";
					//}
				}
				
				
				return SearchValue;
			}
		 
		 

			public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
					  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
						String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) {
				int flag = 0;
				try {
					if(!Search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						

						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						
					}

					if(!cont_comd.equals("0")) {
						flag += 1;
						stmt.setString(flag, cont_comd.toUpperCase()+"%");
						
					}
					if(!cont_corps.equals("0")) {
						flag += 1;
						stmt.setString(flag, cont_corps.toUpperCase()+"%");
						
					}
					if(!cont_div.equals("0")) {
						flag += 1;
						stmt.setString(flag, cont_div.toUpperCase()+"%");
						
					}
					if(!cont_bde.equals("0")) {
						flag += 1;
						stmt.setString(flag, cont_bde.toUpperCase()+"%");
						
					}
					/*if(!unit_name.equals("")) {
						flag += 1;
						stmt.setString(flag, unit_name.toUpperCase());
						
					}
					if(!unit_sus_no.equals("")) {
						flag += 1;
						stmt.setString(flag, unit_sus_no.toUpperCase());
						
					}*/
					if(!user_role_id.equals("0")) {
						flag += 1;
						stmt.setString(flag, user_role_id);
						
					}
					if( !from_date.equals("") && !to_date.equals("")) {
						flag += 1;
						stmt.setString(flag, from_date);
						flag += 1;
						stmt.setString(flag, to_date);
						
					}

				}catch (Exception e) {}
				
				return stmt;
				
			}

		 public List<Map<String, Object>> getsearch_officer_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) 
			{
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
			Connection conn = null;
			String q="";


			try{	
				String pageL = "";
		        
		        if(pageLength == -1){
					pageL = "ALL";
				}else {
					pageL = String.valueOf(pageLength);
				}
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
				
					q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
							"from  orbat_all_details_view c \r\n" + 
							"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
							" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
							" where upper(orb.sus_no) not in (select upper(unit_sus_no) \r\n" + 
							"from tb_psg_trans_proposed_comm_letter) "+SearchValue+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name  limit " +pageL+" OFFSET "+startPage+"";
					stmt=conn.prepareStatement(q);
				
					  stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name, unit_sus_no, user_role_id, from_date, to_date);
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
		
		 public long getsearch_officer_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) {
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date); 
				int total = 0;
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
					String q = null;
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						
					
					  q="select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
					  		"from  orbat_all_details_view c \r\n" + 
					  		"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
					  		"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
					  		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					  		"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					  		"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					  		"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
					  		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
					  		" where upper(orb.sus_no) not in (select upper(unit_sus_no) \r\n" + 
					  		"from tb_psg_trans_proposed_comm_letter) "+SearchValue+"group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name  ) app " ;
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name, unit_sus_no, user_role_id, from_date, to_date);
					
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {  
							total = rs.getInt(1);
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
					return (long) total;
				}
		 
		 
		 public List<Map<String, Object>> getsearch_jco_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) 
			{
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
			Connection conn = null;
			String q="";


			try{	
				String pageL = "";
		        
		        if(pageLength == -1){
					pageL = "ALL";
				}else {
					pageL = String.valueOf(pageLength);
				}
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
				
					q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
							"from  orbat_all_details_view c \r\n" + 
							"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
							" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
							" where upper(orb.sus_no) not in (select upper(unit_sus_no) from tb_psg_census_jco_or_p) "+SearchValue+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name \r\n" + 
							"  limit " +pageL+" OFFSET "+startPage+"";
					stmt=conn.prepareStatement(q);
				
					 stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name, unit_sus_no, user_role_id, from_date, to_date);
					 
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
		
		 public long getsearch_jco_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) {
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date); 
				int total = 0;
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
					String q = null;
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						
					
					  q="select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
					  		"from  orbat_all_details_view c \r\n" + 
					  		"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
					  		"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
					  		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					  		"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					  		"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					  		"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
					  		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
					  		" where upper(orb.sus_no) not in (select upper(unit_sus_no) \r\n" + 
					  		"from tb_psg_census_jco_or_p) "+SearchValue+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name  ) app " ;
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name, unit_sus_no, user_role_id, from_date, to_date);
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {  
							total = rs.getInt(1);
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
					return (long) total;
				}	
/////civ		 
		 public List<Map<String, Object>> getsearch_civilian_en_table_non(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) 
			{
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
			Connection conn = null;
			String q="";


			try{	
				String pageL = "";
		        
		        if(pageLength == -1){
					pageL = "ALL";
				}else {
					pageL = String.valueOf(pageLength);
				}
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
				
					q="select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
							"from  orbat_all_details_view c \r\n" + 
							"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
							" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
							" where upper(orb.sus_no) not in (select upper(sus_no) \r\n" + 
							"from tb_psg_civilian_dtl) "+SearchValue+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name  limit " +pageL+" OFFSET "+startPage+"";
					stmt=conn.prepareStatement(q);
				
					  stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name, unit_sus_no, user_role_id, from_date, to_date);
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
		
		 public long getsearch_civilian_en_tablecount_non(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String user_role_id,String from_date,String to_date) {
			 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, user_role_id, from_date, to_date); 
				int total = 0;
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
					String q = null;
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						
					
					  q="select count(app.*) from(select c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name\r\n" + 
					  		"from  orbat_all_details_view c \r\n" + 
					  		"left join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'											\r\n" + 
					  		"left join all_fmn_view fv  on orb.sus_no = c.sus_no\r\n" + 
					  		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					  		"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					  		"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					  		"left join all_fmn_view bde  on orb.sus_no = c.sus_no \r\n" + 
					  		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' left join logininformation lm on c.sus_no = lm.user_sus_no\r\n" + 
					  		" where upper(orb.sus_no) not in (select upper(sus_no) \r\n" + 
					  		"from tb_psg_civilian_dtl) "+SearchValue+" group by c.cmd_name, c.coprs_name,c.div_name,c.bde_name, c.unit_name  order by c.cmd_name " + 
					  		" ) app " ;
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
								 unit_name,  unit_sus_no, user_role_id, from_date, to_date);
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {  
							total = rs.getInt(1);
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
					return (long) total;
				}


		
}
