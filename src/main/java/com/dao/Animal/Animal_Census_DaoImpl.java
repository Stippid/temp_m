package com.dao.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.tms.Tms_AnimalDao;
import com.model.Animal.TB_ANIMAL_CENSUS_DTLS;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Animal_Census_DaoImpl implements Animal_Census_Dao {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public long GetSearch_censusCount_animal(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
			String status,String roleType) {
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		
		String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_sus_no, unit_name,personnel_no,
				  status,roleType,roleSusNo); 
			int total = 0;
			
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
			
			  
			  q="select count(app.*) from( select distinct a.id,orb.unit_name,  a.army_no, \r\n" + 
			  		"			  		a.name,a.unit_sus_no,a.reject_remarks,\r\n" + 
			  		//"			  		 ltrim(TO_CHAR(a.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,  \r\n" + 
			  		"			  		 a.microchip_no\r\n" + 
			  		//"					ltrim(TO_CHAR(a.date_of_tos ,'DD-MON-YYYY'),'0') as date_of_tos,a.modified_date\r\n" + 
			  		"			  		 FROM TB_ANIMAL_CENSUS_DTLS a  \r\n" + 
			  		"			  		 inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and status_sus_no='Active' \r\n" 
			  		+SearchValue+" and a.id != 0  order by a.modified_date desc) app " ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name,personnel_no, 
						  status,roleType , roleSusNo);
			
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
	
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search,String unit_sus_no,String unit_name,String personnel_no,
			  String status,String roleType, String roleSusNo ) {
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
				
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				
			}

			
			
			
			
			if(!unit_sus_no.equals("")) {
				flag += 1;
				
				stmt.setString(flag, unit_sus_no.toUpperCase());
				
			}
			
			
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());
			}
			
			
			if(!personnel_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+personnel_no.toUpperCase()+"%");
			}
		

		if(!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}
			
			
			
		if(!roleSusNo.equals("")) {
			flag += 1;
		
			stmt.setString(flag, "%"+roleSusNo.toUpperCase()+"%");
			
		}
		 
			
		
		
		}catch (Exception e) {}
		
		return stmt;
		
	}
	

		
	
	
			

	 public String GenerateQueryWhereClause_SQL(String Search,String unit_sus_no,String unit_name,String personnel_no,
				String status, String roleType,String roleSusNo) {
			String SearchValue ="";
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" where  ";
				SearchValue +="(  upper(a.army_no) like ? or upper(a.name) like ? "
						+ " or upper(a.microchip_no) like ? or upper(ltrim(TO_CHAR(a.date_of_birth,'DD-MON-YYYY'),'0')) like ? or upper(ltrim(TO_CHAR(a.date_of_tos,'DD-MON-YYYY'),'0')) like ? or upper(a.unit_sus_no) like ? "
						+ " or upper(orb.unit_name)  like ? or upper(a.reject_remarks)  like ? "
						+ "  ) ";
			}
		
			
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.sus_no  = ?";	
				}
				else {
					SearchValue += " where  orb.sus_no = ?";
				}
			}
			if( !unit_name.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.unit_name = ?  ";	
				}
				else {
					SearchValue += " where  orb.unit_name = ? ";
				}
			}
			
			if(!personnel_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += "  and upper(a.army_no) like ? ";
				}
				else {
					SearchValue += "  where upper(a.army_no) like ? ";
				}
				
			}			
					
			if(!status.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(a.status as character varying) = ? ";
				}
				else {
					SearchValue += " where  cast(a.status as character varying) = ? ";
				}
				
			}
			//260194
		         
			if( !roleSusNo.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(a.unit_sus_no) like ? ";	
				}
				else {
					SearchValue += " where upper(a.unit_sus_no) like ?";
				}
			}
		
			 
			return SearchValue;
		}
	 
	 public List<Map<String, Object>> GetSearch_censusdata_animal(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_sus_no,String unit_name,String personnel_no,
				String status,String roleType) 
		{
			
		
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
	    	String SearchValue = GenerateQueryWhereClause_SQL(Search, unit_sus_no, unit_name,personnel_no, status, roleType, roleSusNo);
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
				
			
				q=" select distinct a.id,orb.unit_name,  a.army_no, \r\n" + 
				  		"			  		a.name,a.unit_sus_no,a.reject_remarks,\r\n" + 
				  		"			  		 ltrim(TO_CHAR(a.date_of_birth ,'DD-MON-YYYY'),'0') as date_of_birth,  \r\n" + 
				  		"			  		 a.microchip_no,\r\n" + 
				  		"					ltrim(TO_CHAR(a.date_of_tos ,'DD-MON-YYYY'),'0') as date_of_tos,a.modified_date\r\n" + 
				  		"			  		 FROM TB_ANIMAL_CENSUS_DTLS a  \r\n" + 
				  		"			  		 inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = a.unit_sus_no and status_sus_no='Active' \r\n" 
				  		+SearchValue+" and a.id != 0  order by a.modified_date desc limit " +pageL+" OFFSET "+startPage+ "" ;
			
				
				
				
			
				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name, personnel_no,  status ,roleType, roleSusNo);
			
		      ResultSet rs = stmt.executeQuery();   
		     
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		  	while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String action = "";
				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";
				String f5 = "";
				String f7 = "";
				
				
				

				
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){delete1Data("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				     String View = "onclick=\"  {ViewData(" + rs.getString("id") + ")}\"";
					f = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
					
				
				   String Approved = "onclick=\" {open_approve(" + rs.getString("id") + ")}\"";
					f3 = "<i " + Approved
							+ " title='Approve Or Reject Data' style=' background: #31af91;border-radius: 24px;font-size: 15px; padding: 5px;'><i class='fa fa-check' style='color:white;'></i><i style='color:white;' class='fa fa-times'></i></i>";
				
				
				if (roleType.equals("DEO")) {
					if (status.equals("0")) {
						f7 += f1;
						f7 += f2;
						
					}
					if (status.equals("1")) {
						f7 += f;
						
						
					}
					if (status.equals("3")) {
						f7 += f1;
						f7 += f2;
					}
				}
				if (roleType.equals("APP")) {
					if (status.equals("1")) {
						f7 += f;
						
						
					}
					if (status.equals("0")) {
				         f7 += f3;
						
					}
					if (status.equals("3")) {
						f7 += f;
						
					}
					
				}
				if (roleType.equals("ALL")) {
					if (status.equals("0")) {
						f7 += f1;
						f7 += f2;
						f7 += f3;
						
					}
					if (status.equals("1")) {
						f7 += f+ " " +f1;
						
						
						
					}
					if (status.equals("3")) {
						f7 += f1;
						f7 += f2;
					}
				}
				columns.put("action", f7); // 5
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
	 
	 public TB_ANIMAL_CENSUS_DTLS getdogByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				TB_ANIMAL_CENSUS_DTLS updateid = (TB_ANIMAL_CENSUS_DTLS) sessionHQL.get(TB_ANIMAL_CENSUS_DTLS.class,id);
				tx.commit();
				return updateid;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
	 
	 public ArrayList<ArrayList<String>> GetAllcensus_Details(int id) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "SELECT \r\n" + 
						"    a.id,\r\n" + 
						"    a.animal_purchase_cost,\r\n" + 
						"    a.animal_type ,\r\n" + 
						"    a.army_no,\r\n" + 
						"    a.auth_letter_no,\r\n" + 
						"    e.type_breed as breed,\r\n" + 
						"    d.type_color as color,\r\n" + 
						"    a.dam_armyno,\r\n" + 
						"    a.dam_name,\r\n" + 
						"    ltrim(to_char(a.date_of_admitted,'DD-MON-YYYY'),'' ) as date_of_admitted ,\r\n" + 
						"    ltrim(to_char(a.date_of_auth,'DD-MON-YYYY'),'' ) as date_of_auth ,\r\n" + 
						"    ltrim(to_char(a.date_of_birth,'DD-MON-YYYY'),'' ) as date_of_birth ,\r\n" + 
						"    ltrim(to_char(a.date_of_tos,'DD-MON-YYYY'),'' ) as date_of_tos ,\r\n" + 
						"    a.fitness_deployment,\r\n" + 
						"    f.fitness_status,\r\n" + 
						"    a.gender,\r\n" + 
						"    a.microchip_no,\r\n" + 
						"    a.name,\r\n" + 
						"    a.sire_armyno,\r\n" + 
						"    a.sire_name,\r\n" + 
						"    g.source ,\r\n" + 
						"   c.type_splztn as specialization,\r\n" + 
						"    a.status,\r\n" + 
						"    b.type_dog as type_of_dog,\r\n" + 
						"    a.unit_sus_no,\r\n" +
						"	 o.unit_name,\r\n"+
						"    a.front_img_path,\r\n" +
						"    a.left_img_path,\r\n" +
						"    a.right_img_path\r\n" +
						"FROM tb_animal_census_dtls a \r\n" + 
						"inner join  tb_tms_dog_type b on b.id = a.type_of_dog \r\n" + 
						"inner join  tb_tms_specialization_master c on c.id = a.specialization \r\n" + 
						"inner join  tb_tms_color_master d on d.id = a.color \r\n" + 
						"inner join  tb_tms_breed_master e on e.id = a.breed \r\n" + 
						"inner join  tb_tms_animal_fitness_status f on f.id = a.fitness_status\r\n" + 
						"inner join  tb_tms_animal_source_master g on g.id = a.source\r\n" + 
						
						"inner join  tb_miso_orbat_unt_dtl o on o.sus_no=a.unit_sus_no and status_sus_no='Active'"+
						 " where a.id = ?";

				stmt = conn.prepareStatement(q);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("animal_purchase_cost")); // 0
					alist.add(rs.getString("animal_type")); // 1
					alist.add(rs.getString("army_no")); // 2
					alist.add(rs.getString("auth_letter_no")); // 3
					alist.add(rs.getString("unit_sus_no")); // 4
					alist.add(rs.getString("color")); // 5
					alist.add(rs.getString("breed")); // 6
					alist.add(rs.getString("dam_name")); // 7
					alist.add(rs.getString("dam_armyno")); // 8
					alist.add(rs.getString("date_of_auth")); // 9
					alist.add(rs.getString("date_of_admitted")); // 10
					alist.add(rs.getString("date_of_tos")); // 11
					alist.add(rs.getString("date_of_birth")); // 12
					alist.add(rs.getString("fitness_deployment")); // 13
					alist.add(rs.getString("fitness_status")); // 14
					alist.add(rs.getString("gender"));// 15
					alist.add(rs.getString("microchip_no"));// 16
					alist.add(rs.getString("name"));// 17
					alist.add(rs.getString("sire_armyno"));// 18
					alist.add(rs.getString("sire_name"));// 19
					alist.add(rs.getString("source"));// 20
					alist.add(rs.getString("specialization"));// 21
					alist.add(rs.getString("type_of_dog"));// 22
					alist.add(rs.getString("front_img_path"));// 23
					alist.add(rs.getString("left_img_path"));// 24
					alist.add(rs.getString("right_img_path"));// 25
					alist.add(rs.getString("id")); // 26
					alist.add(rs.getString("unit_name")); // 27
					alist.add(rs.getString("status")); // 28
					

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
		}
	 
	 public String getanimalIdentityImagePath(String id,String fildname) {
			String whr="";
			Connection conn = null;
			try {	
				
				if(id.equals("")) {
					id = "0";
				}
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select * from tb_animal_census_dtls where id=? ";	
				stmt = conn.prepareStatement(query);
			
				stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	           whr=rs.getString(fildname);
	 	          
	 	        }
	 		    rs.close();
	 	    	stmt.close();
	 			conn.close();
	     	} catch (SQLException e) {
	     			e.printStackTrace();
	     	}	
			return whr;
		}

	

}
