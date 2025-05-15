package com.dao.Animal;

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


public class Animal_Str_incr_decrDAOImpl implements Animal_Str_incr_decrDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Map<String, Object>> GetSearch_Incr_Decr_List(int startPage, int pageLength, String search,
	        String orderColunm, String orderType, HttpSession sessionUserId, String type, String army_no,
	        String from_sus_no, String to_sus_no, String status) {

	    String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	    String roleName = sessionUserId.getAttribute("role").toString();
	    String roleType = sessionUserId.getAttribute("roleType").toString();
	    String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
	    String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();

	    List<Map<String, Object>> list = new ArrayList<>();
	    Connection conn = null;

	    try {
	    	
	    	String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			
	        conn = dataSource.getConnection();
	        PreparedStatement stmt = null;

	        String q = "SELECT b.id,b.census_id,a.army_no, a.name, b.from_sus_no, b.to_sus_no,CAST(b.dt_of_tos AS DATE), CAST(b.dt_of_sos AS DATE),b.scenario " +
	                   "FROM TB_ANIMAL_CENSUS_DTLS a " +
	                   "LEFT JOIN TB_ANIMAL_STR_INCR_DECR b ON a.id = b.census_id " +
	                   "LEFT JOIN tb_miso_orbat_unt_dtl c ON a.unit_sus_no = c.sus_no AND c.status_sus_no = 'Active' " +
	                   "WHERE 1=1 ";

	        if (!army_no.equals("")) {
	            q += "AND a.army_no = ? ";
	        }
	        
	        

	        
	        if (!from_sus_no.equals("")) {
	            q += "AND b.from_sus_no = ? ";
	        }
	        if (!to_sus_no.equals("")) {
	            q += "AND b.to_sus_no = ? ";
	        }
	        if (!status.equals("")) {
	            q += "AND b.status = ? ";
	        }
	        
	        if (!type.equals("")) {
	            if (type.equals("1")) {
	                q += "AND b.scenario = '1' ";
	            } else if (type.equals("2")) {
	                q += "AND b.scenario = '2' ";
	            }
	        }
	        
	        q += " LIMIT "+pageL+" OFFSET " + startPage;

	        stmt = conn.prepareStatement(q);
	        int paramIndex = 1;

	        if (!army_no.equals("")) {
	            stmt.setString(paramIndex++, army_no);
	        }
	        if (!from_sus_no.equals("")) {
	            stmt.setString(paramIndex++, from_sus_no);
	        }
	        if (!to_sus_no.equals("")) {
	            stmt.setString(paramIndex++, to_sus_no);
	        }
	        if (!status.equals("")) {
	            stmt.setInt(paramIndex++, Integer.parseInt(status));
	        }
	        
	        ResultSet rs = stmt.executeQuery();
	        System.err.println("Query Anm Incr Decr --> " + stmt);
	        ResultSetMetaData metaData = rs.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        while (rs.next()) {
	            Map<String, Object> columns = new LinkedHashMap<>();
	            for (int i = 1; i <= columnCount; i++) {
	                columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	            }
	            
	            String id = rs.getString("id");
	            String census_id = rs.getString("census_id");
	            String to_sus_no1 = rs.getString("to_sus_no");
	            String action = "";
	            
	            String Approve = "onclick=\"if (confirm('Are You Sure You Want to Approve This?')) { approveData(" 
	                            + id + ", " + census_id + ", '" + to_sus_no1 + "'); } else { return false; }\"";

	            String approveBtn = "<i class='action_icons action_approve' " + Approve + " title='Approve'></i>";


	            String scenario = rs.getString("scenario"); 
	            String Edit = "onclick=\"editData(" + id + ", " + census_id + ", '" + scenario + "');\"";
	            String editBtn = "<i class='action_icons action_update' " + Edit + " title='Edit'></i>";

	            String Reject = "onclick=\"openRejectModal(" + rs.getString("id") + ", " + rs.getString("census_id") + ")\"";
	            String rejectButton = "<i class='action_icons action_reject' " + Reject + " title='Reject'></i>";


	            String Delete = "onclick=\"if (confirm('Are You Sure You Want to Delete This?')) { deleteData(" + id + ", " + census_id + "); } else { return false; }\"";
	            String deleteBtn = "<i class='action_icons action_delete' " + Delete + " title='Delete'></i>";
	            
	            /*action += approveBtn + " " + deleteBtn;*/
	            
	            if (roleType.equals("DEO")) {
					if (status.equals("0")) {
						action += editBtn + " " + deleteBtn;
			
						
					}
					if (status.equals("1")) {
						action += "Approved";
						
						
					}
					if (status.equals("3")) {
						action += editBtn + " " + deleteBtn;
					}
				}
				if (roleType.equals("APP")) {
					if (status.equals("1")) {
						action += "Approved";
						
						
					}
					if (status.equals("0")) {
						action += approveBtn + " " +rejectButton ;
						
					}
					if (status.equals("3")) {
						action += "Rejected" ;
						
					}
					
				}
				if (roleType.equals("ALL")) {
					if (status.equals("0")) {
						action += editBtn + " " + deleteBtn+ " " +approveBtn ;
			
						
					}
					if (status.equals("1")) {
						action += "Approved";
						
						
					}
					if (status.equals("3")) {
						action += editBtn + " " + deleteBtn;
					}
				}
	            
	            columns.put("action", action);
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
	                e.printStackTrace();
	            }
	        }
	    }
	    return list;
	}

	
 
 public long GetSearch_Incr_Decr_count(int startPage, int pageLength, String search,
	        String orderColunm, String orderType, HttpSession sessionUserId, String type, String army_no,
	        String from_sus_no, String to_sus_no, String status) {

	    long total = 0;
	    Connection conn = null;

	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement stmt = null;

	        String q = "SELECT COUNT(*) " +
	                   "FROM TB_ANIMAL_CENSUS_DTLS a " +
	                   "LEFT JOIN TB_ANIMAL_STR_INCR_DECR b ON a.id = b.census_id " +
	                   "LEFT JOIN tb_miso_orbat_unt_dtl c ON a.unit_sus_no = c.sus_no AND c.status_sus_no = 'Active' " +
	                   "WHERE 1=1 ";

	        if (!army_no.equals("")) {
	            q += "AND a.army_no = ? ";
	        }

	        

	        if (!from_sus_no.equals("")) {
	            q += "AND b.from_sus_no = ? ";
	        }
	        if (!to_sus_no.equals("")) {
	            q += "AND b.to_sus_no = ? ";
	        }
	        if (!status.equals("")) {
	            q += "AND b.status = ? ";
	        }
	        
	        if (!type.equals("")) {
	            if (type.equals("1")) {
	                q += "AND b.scenario = '1' ";
	            } else if (type.equals("2")) {
	                q += "AND b.scenario = '2' ";
	            }
	        }

	        stmt = conn.prepareStatement(q);
	        int paramIndex = 1;

	        if (!army_no.equals("")) {
	            stmt.setString(paramIndex++, army_no);
	        }
	        if (!from_sus_no.equals("")) {
	            stmt.setString(paramIndex++, from_sus_no);
	        }
	        if (!to_sus_no.equals("")) {
	            stmt.setString(paramIndex++, to_sus_no);
	        }
	        if (!status.equals("")) {
	            stmt.setInt(paramIndex++, Integer.parseInt(status));
	        }

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            total = rs.getLong(1);
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
	                e.printStackTrace();
	            }
	        }
	    }
	    return total;
	}
 
 public ArrayList<ArrayList<String>> getPostTenureDateAnimal(int census_id_animal){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
			try{			
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;		
					q="select to_char(dt_of_tos,'DD/MM/YYYY') as date from tB_ANIMAL_STR_INCR_DECR where census_id=? and status='1' order by dt_of_tos desc limit 1" ;				
				stmt=conn.prepareStatement(q);
				stmt.setInt(1, census_id_animal);
			
				ResultSet rs = stmt.executeQuery();   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("date"));
		    	  alist.add(list);
			     }	
		      System.err.println("getPostTenureDateAnimal STMT ----" + stmt);
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
 
	public Boolean getAnimalpernoAlreadyExits(int jco_id) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select case when count(p.id) > 0 then true else false end from tb_ANIMAL_STR_INCR_DECR p\r\n"
					+ "inner join tb_animal_census_dtls c on p.census_id=c.id where p.status = '0' and c.id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, jco_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				msg = rs.getBoolean("case");
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
		return msg;
	}
	
	
	
	//
	 public ArrayList<ArrayList<String>> GetCommDataApprove(int comm_id)

		{

			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

			Connection conn = null;

			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				q = "SELECT c.id, c.gender, c.date_of_birth, c.name, c.status, \r\n" + 
					" c.microchip_no, "+ 
					" (select post.dt_of_tos  from tb_ANIMAL_STR_INCR_DECR post where c.id = post.census_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc limit 1) as dt_of_tos, \r\n" + 
					" c.unit_sus_no \r\n" + 
					" FROM TB_ANIMAL_CENSUS_DTLS c \r\n" + 
					" WHERE c.status = '1'AND cast(c.id as character varying)=? " ;
				stmt = conn.prepareStatement(q);
				stmt.setString(1, String.valueOf(comm_id));
				System.err.println("GetCommDataApprove  Query" + stmt);
				ResultSet rs = stmt.executeQuery();

				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();

				while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("id")); // 0

					list.add(rs.getString("gender")); // 1

					list.add(rs.getString("date_of_birth"));// 2

					list.add(rs.getString("name"));// 3

					list.add(rs.getString("microchip_no"));// 4

					list.add(rs.getString("dt_of_tos"));// 5

					list.add(rs.getString("unit_sus_no"));// 6

					alist.add(list);

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

			return alist;

		}
	 
	 
	 //
	 
	//to get data of decr edit by id
	 
		 public List<Map<String, Object>> getAnml_Str_DecrByid(int id) {

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

				Connection conn = null;

				String q = "";

				String qry = "";

				try {

					conn = dataSource.getConnection();

					PreparedStatement stmt = null;
					
				    q = "SELECT b.id,b.census_id,a.army_no, a.name,a.microchip_no, b.from_sus_no, b.to_sus_no as unit_sus_no, b.dt_of_tos, b.dt_of_sos,b.scenario,b.auth_no as out_auth, " +
				    		   "b.dt_of_tos,b.dt_of_sos,b.auth_dt as out_auth_dt,c.unit_name  " +
			                   "FROM TB_ANIMAL_STR_INCR_DECR b " +
			                   "INNER JOIN TB_ANIMAL_CENSUS_DTLS a ON a.id = b.census_id " +
			                   "INNER JOIN tb_miso_orbat_unt_dtl c ON b.to_sus_no = c.sus_no AND c.status_sus_no = 'Active' where b.id = ? ";
					
					stmt = conn.prepareStatement(q);

					stmt.setInt(1, id);
					System.err.println("stmt--decr " + stmt);
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
		 
		 public List<Map<String, Object>> getAnml_Str_IncrByid(int id) {

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

				Connection conn = null;

				String q = "";

				String qry = "";

				try {

					conn = dataSource.getConnection();

					PreparedStatement stmt = null;
					
				    q = "SELECT b.id,b.census_id,a.army_no, a.name,a.microchip_no, b.from_sus_no, b.to_sus_no as unit_sus_no, b.dt_of_tos, b.dt_of_sos,b.scenario,b.auth_no as out_auth, " +
				    		   "b.dt_of_tos,b.dt_of_tos,c.unit_name " +
			                   "FROM TB_ANIMAL_STR_INCR_DECR b " +
			                   "INNER JOIN TB_ANIMAL_CENSUS_DTLS a ON a.id = b.census_id " +
			                   "INNER JOIN tb_miso_orbat_unt_dtl c ON b.to_sus_no = c.sus_no AND c.status_sus_no = 'Active' where b.id = ? ";
					
					stmt = conn.prepareStatement(q);

					stmt.setInt(1, id);
					System.err.println("stmt--INCR DATA BY ID" + stmt);
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
