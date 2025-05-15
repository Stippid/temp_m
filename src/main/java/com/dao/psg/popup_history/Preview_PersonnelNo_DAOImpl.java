package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Preview_PersonnelNo_DAOImpl implements Preview_PersonnelNo_DAO{
	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	@Override
	public ArrayList<ArrayList<String>> preview_personnelNo(String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		long comm_id = Long.parseLong(personnel_no);
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id != 0) {
				qry += " and cast(c.id as character varying)=? ";
			}
			
			/*q="select p.authority,p.new_personal_no,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date from tb_psg_trans_proposed_comm_letter c"+  
					" inner join tb_psg_change_of_comission p on c.id = p.comm_id\n" + 
					"where p.status::integer in (1,2)"+ qry +"order by p.id asc" ;
				stmt=conn.prepareStatement(q);*/
				
				
//			pranay 29.05	
			q="select p.authority,p.new_personal_no,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date from tb_psg_trans_proposed_comm_letter c"+  
					" inner join tb_psg_change_of_comission p on c.id = p.comm_id\n" + 
					"where p.status::integer in (1,2,-1)"+ qry +"order by p.id asc" ;
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				 				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("new_personal_no"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	@Override
	public ArrayList<ArrayList<String>> preview_cadetNo(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=?";
			}
			if(!personnel_no.equals("")) {
				qry1 += " where personnel_no = ? ";
			}
			
			/*q="select c.authority,c.cadet_no,TO_CHAR(c.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,c.created_by,\n" + 
					"TO_CHAR(c.created_date :: DATE, 'dd-MON-yyyy') as created_date,c.modified_by,TO_CHAR(c.modified_date :: DATE, 'dd-MON-yyyy') as modified_date \n" + 
					"from tb_psg_trans_proposed_comm_letter c \n" + qry1+
					"union all\n" + 
					"select p.authority,p.cadet_no,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_cadet p on c.id = p.comm_id\n" + 
					"where p.status::integer in (1,2)"+ qry ;
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,personnel_no);
				stmt.setString(2,String.valueOf(comm_id_i));*/	
			
			
//			pranay 29.05	
			q=	"SELECT p.authority,\n"
					+ "       p.cadet_no,\n"
					+ "       to_char(p.date_of_authority :: DATE, 'dd-MON-yyyy') AS date_of_authority,\n"
					+ "       p.created_by,\n"
					+ "       to_char(p.created_date :: DATE, 'dd-MON-yyyy') AS created_date,\n"
					+ "       p.modified_by,\n"
					+ "       to_char(p.modified_date :: DATE, 'dd-MON-yyyy') AS modified_date\n"
					+ "  FROM tb_psg_trans_proposed_comm_letter c\n"
					+ " INNER JOIN tb_psg_update_comm_cadet p\n"
					+ "    ON c.id = p.comm_id\n"
					+ " WHERE p.status::integer IN (1,2)"+ qry ;
				stmt=conn.prepareStatement(q);			
				
				stmt.setString(1,String.valueOf(comm_id_i));
				 				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cadet_no"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	@Override
	public ArrayList<ArrayList<String>> preview_courseNo(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as  character varying)=? ";
			}
			if(!personnel_no.equals("")) {
				qry1 += " where personnel_no = ? ";
			}
			
			/*q="select c.authority,c.batch_no,TO_CHAR(c.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,c.created_by,\n" + 
					"TO_CHAR(c.created_date :: DATE, 'dd-MON-yyyy') as created_date,c.modified_by,TO_CHAR(c.modified_date :: DATE, 'dd-MON-yyyy') as modified_date,cc.course_name from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_mstr_course_comm cc on cc.id = c.course\n" +qry1+ 
					"union all\n" + 
					"select p.authority,p.batch_no,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					",cc.course_name from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_course p on c.id = p.comm_id\n" + 
					"inner join tb_psg_mstr_course_comm cc on cc.id = p.course\n" + 
					"where p.status::integer in (1,2)"+ qry;
			
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,personnel_no);
				stmt.setString(2,String.valueOf(comm_id_i));	
				*/
			
//			pranay 29.05	
			
				q="SELECT p.authority,\n"
						+ "       p.batch_no,\n"
						+ "       to_char(p.date_of_authority :: DATE, 'dd-MON-yyyy') AS date_of_authority,\n"
						+ "       p.created_by,\n"
						+ "       to_char(p.created_date :: DATE, 'dd-MON-yyyy') AS created_date,\n"
						+ "       p.modified_by,\n"
						+ "       to_char(p.modified_date :: DATE, 'dd-MON-yyyy') AS modified_date,\n"
						+ "       cc.course_name\n"
						+ "  FROM tb_psg_trans_proposed_comm_letter c\n"
						+ " INNER JOIN tb_psg_update_comm_course p\n"
						+ "    ON c.id = p.comm_id\n"
						+ " INNER JOIN tb_psg_mstr_course_comm cc\n"
						+ "    ON cc.id = p.course\n"
						+ " WHERE p.status::integer IN (1,2)"+ qry;
				
					stmt=conn.prepareStatement(q);				
					
					stmt.setString(1,String.valueOf(comm_id_i));
				 				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("batch_no"));//0
					list.add(rs.getString("course_name"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	@Override
	public ArrayList<ArrayList<String>> preview_gender(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=? ";
			}
			if(!personnel_no.equals("")) {
				qry1 += " where personnel_no = ? ";
			}
			
			/*q="select c.authority,cc.gender_name,TO_CHAR(c.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,c.created_by,\n" + 
					"TO_CHAR(c.created_date :: DATE, 'dd-MON-yyyy') as created_date,c.modified_by,TO_CHAR(c.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					" from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_mstr_gender cc on cc.id = c.gender \n" +qry1+ 
					"union all\n" + 
					"select p.authority,cc.gender_name,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					" from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_gender p on c.id = p.comm_id\n" + 
					"inner join tb_psg_mstr_gender cc on cc.id = p.gender\n" + 
					"where p.status::integer in (1,2)"+ qry;
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,personnel_no);
				stmt.setString(2,String.valueOf(comm_id_i));*/
			
//			pranay 29.05	
			
			q=" SELECT p.authority,\n"
					+ "       cc.gender_name,\n"
					+ "       to_char(p.date_of_authority :: DATE, 'dd-MON-yyyy') AS date_of_authority,\n"
					+ "       p.created_by,\n"
					+ "       to_char(p.created_date :: DATE, 'dd-MON-yyyy') AS created_date,\n"
					+ "       p.modified_by,\n"
					+ "       to_char(p.modified_date :: DATE, 'dd-MON-yyyy') AS modified_date\n"
					+ "  FROM tb_psg_trans_proposed_comm_letter c\n"
					+ " INNER JOIN tb_psg_update_comm_gender p\n"
					+ "    ON c.id = p.comm_id\n"
					+ " INNER JOIN tb_psg_mstr_gender cc\n"
					+ "    ON cc.id = p.gender\n"
					+ " WHERE p.status::integer IN (1,2)"+ qry;
				stmt=conn.prepareStatement(q);
				
				//stmt.setString(1,personnel_no);
				stmt.setString(1,String.valueOf(comm_id_i));
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("gender_name"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	
	@Override
	public ArrayList<ArrayList<String>> preview_commission(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=? ";
			}
//			if(!personnel_no.equals("")) {
//				qry1 += " where personnel_no = ? ";
//			}
//			
			
			q="select p.authority,\n"
					+ "       cc.comn_name,\n"
					+ "       to_char(p.date_of_commission :: DATE, 'dd-MON-yyyy') as date_of_commission,\n"
					+ "       to_char(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,\n"
					+ "       p.created_by,\n"
					+ "       to_char(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,\n"
					+ "       p.modified_by,\n"
					+ "       to_char(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n"
					+ "  from tb_psg_trans_proposed_comm_letter c\n"
					+ " inner join tb_psg_update_comm_commission p\n"
					+ "    on c.id = p.comm_id\n"
					+ " inner join tb_psg_mstr_type_of_commission cc\n"
					+ "    on cc.id = p.type_of_comm_granted\n"
					+ " where p.status::integer in (1,2)\n"
					+ " "+ qry;
				stmt=conn.prepareStatement(q);
				System.err.println("query for preview commisio:" + stmt);
				//stmt.setString(1,personnel_no);
				stmt.setString(1,String.valueOf(comm_id_i));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("comn_name"));//0
					list.add(rs.getString("date_of_commission"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	@Override
	public ArrayList<ArrayList<String>> preview_dateofbirth(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=? ";
			}			
		
			
			q="select p.authority,TO_CHAR(p.date_of_birth :: DATE, 'dd-MON-yyyy') as date_of_birth,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					" from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_birth p on c.id = p.comm_id\n" + 
					"where p.status::integer in (1,2)"+ qry;
				stmt=conn.prepareStatement(q);				
			
				stmt.setString(1,String.valueOf(comm_id_i));				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("date_of_birth"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	@Override
	public ArrayList<ArrayList<String>> preview_armservice(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=? ";
			}
			
			q="select p.authority,cc.arm_desc as parent_arm,cc_arm.arm_desc as regiment,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by, \n" + 
					"					TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date \n" + 
					"					 from tb_psg_trans_proposed_comm_letter c \n" + 
					"					inner join tb_psg_inter_arm_transfer p on c.id = p.comm_id \n" + 
					"					inner join tb_miso_orbat_arm_code cc on cc.arm_code = p.parent_arm_service\n" + 
					"					left join tb_miso_orbat_arm_code cc_arm on cc_arm.arm_code = p.regt\n" + 
					"where p.status::integer in (1,2)"+ qry +"order by p.id asc" ;
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id_i));
				 				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("parent_arm"));//0
					list.add(rs.getString("regiment"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	
	
	
	@Override
	public ArrayList<ArrayList<String>> preview_unit(BigInteger comm_id,String personnel_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=?";
			}
		/*	if(!personnel_no.equals("")) {
				qry1 += " where personnel_no = ? ";
			}*/

			//keval08
			/*q="select p.unit_sus_no,orb.unit_name as unit_post_to,TO_CHAR(p.date_of_tos :: DATE, 'dd-MON-yyyy') as date_of_tos,p.authority,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					"from tb_psg_trans_proposed_comm_letter_history p \n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no =  p.unit_sus_no and orb.status_sus_no='Active'\n" + qry1+
					"UNION ALL\n" + 
					
					"select p.unit_sus_no,orb.unit_name as unit_post_to,TO_CHAR(p.date_of_tos :: DATE, 'dd-MON-yyyy') as date_of_tos,p.authority,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					" from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_unit p on c.id = p.comm_id\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no =  p.unit_sus_no and orb.status_sus_no='Active'\n" + 
					"where p.status::integer in (1,2)"+ qry ;
			*/
			
			//MITESH04/06
			
			q=	"select p.unit_sus_no,orb.unit_name as unit_post_to,TO_CHAR(p.date_of_tos :: DATE, 'dd-MON-yyyy') as date_of_tos,p.authority,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,p.created_by,\n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date\n" + 
					" from tb_psg_trans_proposed_comm_letter c\n" + 
					"inner join tb_psg_update_comm_unit p on c.id = p.comm_id\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no =  p.unit_sus_no and orb.status_sus_no='Active'\n" + 
					"where p.status::integer in (1,2)"+ qry ;
			
				stmt=conn.prepareStatement(q);
				
			/*	stmt.setString(1,personnel_no);*/
				stmt.setString(1,String.valueOf(comm_id_i));
				
				System.err.println("Query for unit history: \n" +stmt);
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("unit_sus_no"));//0
					list.add(rs.getString("unit_post_to"));
					list.add(rs.getString("date_of_tos"));
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
	@Override
	public ArrayList<ArrayList<String>> preview_rankNo(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		BigInteger comm_id_i = comm_id;
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_id_i != BigInteger.ZERO) {
				qry += " and cast(c.id as character varying)=? ";
			}
			
			q="select p.authority,cc.description as rank ,TO_CHAR(p.date_of_authority :: DATE, 'dd-MON-yyyy') as date_of_authority,\n" + 
					"p.created_by, \n" + 
					"TO_CHAR(p.created_date :: DATE, 'dd-MON-yyyy') as created_date,p.modified_by,TO_CHAR(p.modified_date :: DATE, 'dd-MON-yyyy') as modified_date \n" + 
					"from tb_psg_trans_proposed_comm_letter c \n" + 
					"inner join tb_psg_change_of_rank p on c.id = p.comm_id \n" + 
					"inner join cue_tb_psg_rank_app_master cc on cc.id = p.rank\n" + 
					"where  upper(level_in_hierarchy) = 'RANK' \n" + 
					"and parent_code ='0' and code in ('R000','R001','R002','R003','R004','R005','R006','R007','R008','R013','26243') and status_active = 'Active' \n" + 
					"and p.status::integer in (1,2)"+ qry +"order by p.id asc" ;
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id_i));
				 				
				System.err.println("rank history:  \n" + stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("rank"));//0
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("created_by"));//3
					list.add(rs.getString("created_date"));//4
					list.add(rs.getString("modified_by"));//5
					list.add(rs.getString("modified_date"));//6
					
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
}
