package com.dao.mnh;

import java.math.BigDecimal;
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


public class Ad_Hoc_mnh_DaoImpl implements Ad_Hoc_mnh_Dao {
	private DataSource dataSource;
	private DataSource dataSourceOLAP;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public void setDataSourceOLAP(DataSource dataSourceOLAP) {
		this.dataSourceOLAP = dataSourceOLAP;
	}
	
	private static final List<Map<String, Object>> List = null;
	
	
	
//	public List<String> getTableNameList() {
//		List<String> list1 = new ArrayList<String>();
//		Connection conn = null;
//		try {
//			conn = dataSourceOLAP.getConnection();
//			PreparedStatement stmt = null;
//			String sql1 = "SELECT table_name FROM information_schema.tables where table_catalog='miso_psg_olap' and table_schema='public'";
//			stmt = conn.prepareStatement(sql1);
//			//stmt.setString(1, sus_no);
//			ResultSet rs1 = stmt.executeQuery();
//			while (rs1.next()) {
//				list1.add(rs1.getString("table_name"));
//			}
//			rs1.close();
//			stmt.close();
//		} catch (SQLException e) {
//			
//		}finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				return list1;
//			}
//		}
//		return list1;
//	}
	
	//Get Formation
		public List<Map<String, Object>> getAllFormationList() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "select * from all_fmn_view order by form_code_control";
				PreparedStatement stmt = conn.prepareStatement(sql);

				ResultSet rs1 = stmt.executeQuery();
				ResultSetMetaData metaData = rs1.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs1.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs1.getObject(i));
					}
					list.add(columns);
				}
				rs1.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
	
			
		
	
	
		 public List<Map<String, Object>> search_adhoc_dtl(String gender1, String unit_name2,String category1,String rank1, String relation1,String admsn_type1,String disposal1,String command1) {
			 
			
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				
				Connection conn = null;
				String q="";
				String qry="";
				try{	  
					conn = dataSource.getConnection();					
					PreparedStatement stmt =null;
				
					if(!gender1.equals("-1")){
						if(gender1.equals("MALE")) {
							gender1 = "M";
						}
				        if(gender1.equals("FEMALE")) {
				        	gender1 = "F";
						}
				        if(gender1.equals("OTHER")) {
				        	gender1 = "O";
						}
						
						qry += " and r.sex = ?";
					} 
					if(!unit_name2.equals("")){
						qry += " and o.unit_name = ?";
					} 
					if(!category1.equals("-1")){
						qry += " and c.sys_code_value = ?";
					} 
					
					if(!rank1.equals("-1")){
						qry += " and m.id = ?";
					} 
					
					
					if(!relation1.equals("-1")){
						qry += " and r.relationship = ?";
					} 
					if(!admsn_type1.equals("-1")){
						qry += " and r.admsn_type = ?";
					} 
					
					if(!disposal1.equals("-1")){
						qry += " and r.disposal = ?";
					} 
					if(!command1.equals("-1")){
						qry += " and  o.cmd_name = ?";
					} 

					
					q="select distinct r.id,r.and_no,r.name,c.sys_code_value,r.relationship,r.admsn_type,r.sex,r.disposal,o.unit_name,o.cmd_name,r.rank,r.medical_unit    \r\n" + 
							"from tb_med_patient_details r \r\n" + 
							"inner join orbat_all_details_view_mnh o on o.sus_no = r.medical_unit\r\n" + 
							"inner join tb_med_system_code c on c.sys_code_value = r.category \r\n" +
							"inner join tb_med_rankcode m on m.rank_desc = r.rank \r\n" +
					    	"where r.id != 0   " +qry;
	
					
					
                  
					   stmt=conn.prepareStatement(q);
					  
					int j=1;  
					if(!gender1.equals("-1")){
				 		stmt.setString(j, gender1);
				 		j+=1;
				 	}
					if(!unit_name2.equals("")){
				 		stmt.setString(j, unit_name2);
				 		j+=1;
				 	}
					
					if(!category1.equals("-1")){
				 		stmt.setString(j, category1);
				 		j+=1;
				 	}
					if(!rank1.equals("-1")){
				 		stmt.setLong(j, Integer.parseInt(rank1));
				 		j+=1;
				 	}
					
					if(!relation1.equals("-1")){
				 		stmt.setString(j, relation1);
				 		j+=1;
				 	}
					if(!admsn_type1.equals("-1")){
				 		stmt.setString(j, admsn_type1);
				 		j+=1;
				 	}
					
					if(!disposal1.equals("-1")){
				 		stmt.setString(j, disposal1);
				 		j+=1;
				 	}
					
					if(!command1.equals("-1")){
				 		stmt.setString(j, command1);
				 		j+=1;
				 	}
//					
//					
					
//					if(!contact1.equals("")){
//						stmt.setBigDecimal(j, BigDecimal.valueOf(Long.parseLong(contact1))); //or you can try below
//				    	j++;
//					} 
						 
				 	ResultSet rs = stmt.executeQuery(); 
				 	 
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
					while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 	    for(int i = 1; i <= columnCount; i++){
				 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				 	    }
//		 	            String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
//						String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
//						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
//						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
		 	  			String f = "";
				//		f += updateButton;
					//	f += deleteButton;
		  				columns.put(metaData.getColumnLabel(1), f);
		 	            list.add(columns);
				 	 }
				     rs.close();
				     stmt.close();
				     conn.close();
			     }catch(SQLException e){
			    	 e.printStackTrace();
				 }finally{
					 if(conn != null){
						 try{
							 conn.close();
						 }catch(SQLException e){
						 }
					 }
				 }
				 return list;
			}

	
		 
		 
		
			
		
}
