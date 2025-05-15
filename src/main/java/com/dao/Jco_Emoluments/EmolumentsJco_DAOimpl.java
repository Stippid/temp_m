package com.dao.Jco_Emoluments;

import java.math.BigInteger;
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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Emoluments.TB_PSG_EMOLUMENT_PARENT_OFFCR;
import com.models.Emoluments_Jco.TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO;
import com.persistance.util.HibernateUtil;

public class EmolumentsJco_DAOimpl implements EmolumentsJco_DAO {

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
 
	public ArrayList<ArrayList<String>> getsearch_Emolument_Jco(String personnel_no1,String cname1,String rank1, String from_date1,
			String ini_status1,String on_status1,String scase1,String cancel_status1 ){
		 
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			 
			 if (personnel_no1 != "" && personnel_no1 != null && personnel_no1 != "0") {
				qry += " and p.army_no = ?  ";
			 }
			 if (!cname1.equals("")) {
				qry += " and  p.first_name = ?  ";
			}
 
			 if (!rank1.equals("0")) {
				qry += " and   cast( p.rank as character varying)  = ?  ";
			}
			 	if (!from_date1.equals("")) {
				 qry += " and to_char(bc.date_of_casuality, 'DD/MM/YYYY') = ?";
				}
 
			//initial
		 if(scase1.equals("1") && ini_status1.equals("0") ) {
			 qry += " and cast( e.status as character varying)= ? and close_status=-1    ";
		 }
		 if(scase1.equals("1") && ini_status1.equals("1") ) {
			 qry += " and cast( e.status as character varying)= ? and close_status=-1  ";
		 }	
		 if(scase1.equals("1") && ini_status1.equals("3") ) {
			 qry += " and cast( e.status as character varying)= ? and close_status=-1 ";
		 }	
		//on going 
		 if(scase1.equals("2"))
		 {
			 qry += " and cast( e.status as character varying)  in ('1') and close_status=-1";
		 }
		 if(scase1.equals("2") && on_status1.equals("-1") ) {
			 qry += " and cast( e.update_status as character varying) in ('-1','0','1','3') and close_status=-1";
		 }
		 if(scase1.equals("2") && on_status1.equals("1")) {
			 qry += " and (itteration_status > 0 and close_status=-1) ";
			
		 }
		 if(scase1.equals("2") && on_status1.equals("3") ) {
			 qry += " and cast( e.update_status as character varying)= ?  ";
		 }
		 
		//Cancel
//		 if(scase1.equals("3")  )
//		 {
//			 qry += " and cast( e.update_status as character varying)  in ('1') ";
//		 }
		 if(scase1.equals("3") && cancel_status1.equals("0") ) {
			 qry += " and cast( e.close_status as character varying)= ?  ";
		 }
		 if(scase1.equals("3") && cancel_status1.equals("1") ) {
			 qry += " and cast( e.close_status as character varying)= ?  ";
		 }	
		
//			q=  "select distinct  p.id, p.personnel_no,p.name,r.description as rank,orb.unit_name as unit_name,e.status,p.rank,"
//					+ "e.update_status ,to_char(bc.date_of_casuality, 'DD/MM/YYYY') as date_of_casuality from\r\n" + 
//				"tb_psg_trans_proposed_comm_letter p inner join\r\n" + 
//				"tb_psg_emolument_parent_offcr e on p.id= e.comm_id inner join\r\n" + 
//				"cue_tb_psg_rank_app_master r on r.id = p.rank  and r.status_active='Active' inner join \r\n" + 
//				"tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active'  "
//				+ "inner join tb_psg_census_battle_physical_casuality bc   on bc.comm_id = e.comm_id "  +qry +  "   order by p.id desc";
//		
//			
			q= " select distinct  p.id, p.army_no,p.full_name,r.rank as rank,orb.unit_name as unit_name,e.status,p.rank,e.update_status " + 
				" from tb_psg_census_jco_or_p p\r\n" + 
				" inner join tb_psg_emolument_parent_offcr_jco e on p.id= e.jco_id\r\n" + 
				" inner join tb_psg_mstr_rank_jco r on r.id = p.rank  and r.status='active' \r\n" + 
				" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active' \r\n" + 
				" inner join tb_psg_census_battle_physical_casuality_jco bc   on bc.jco_id = e.jco_id " +qry +  "   order by p.id desc"; 
				 
 		
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if (personnel_no1 != "" && personnel_no1 != null && personnel_no1 != "0") {
					stmt.setString(j, personnel_no1);
					j += 1;
				}
				 if (!cname1.equals("")) {
				stmt.setString(j, cname1);
					j += 1;
				}
				 if (!rank1.equals("0")) {
					stmt.setString(j, rank1);
					j += 1;
				}
				 if (!from_date1.equals("")) {
						stmt.setString(j, from_date1);
						j += 1;
					}
 				  if(scase1.equals("1") && ini_status1.equals("0") ) {
					 stmt.setString(j, "0");
						j += 1; 
				 }
 				 if(scase1.equals("1") && ini_status1.equals("1") ) {
					 stmt.setString(j, "1");
						j += 1; 
				 }
 				 if(scase1.equals("1") && ini_status1.equals("3") ) {
					 stmt.setString(j, "3");
						j += 1; 
				 }
				 //ongoing 
				 
 				if(scase1.equals("2") && on_status1.equals("3") ) {
					 stmt.setString(j, "3");
						j += 1; 
				 }
				 if(scase1.equals("2") && on_status1.equals("4") ) {
					 stmt.setString(j, "4");
						j += 1; 
				 }
 				 
 				 
 				 //cancel
				 if(scase1.equals("3") && cancel_status1.equals("0") ) {
					 stmt.setString(j, "0");
						j += 1; 
				 }
 				 if(scase1.equals("3") && cancel_status1.equals("1") ) {
					 stmt.setString(j, "1");
						j += 1; 
				 }
 				 
 				 
 				 
 				 
				ResultSet rs = stmt.executeQuery();  
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("army_no"));
					list.add(rs.getString("full_name"));
					list.add(rs.getString("rank"));
					list.add(rs.getString("unit_name"));
					
					String f = "";
					String f1 = "";
					String f2 = "";
					String f3 = "";
					String f4 = "";
					String f5 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Edit This Emolument?') ){editData("+ rs.getInt("id") + ")}else{ return false;}\"";
					 	
					if(rs.getString("status").equals("0")) {
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					}
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Emolument?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					String View1 = "onclick=\"  {AppViewData("+ rs.getString("id") + ")}\"";
			        f2 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
			         	
			        String ApproveUpdate = "onclick=\"  if (confirm('Are You Sure You Want to Approve/Update This Emolument?') ){approveeditData("+ rs.getInt("id") + ","+scase1+","+on_status1+")}else{ return false;}\"";
					if(rs.getString("status").equals("1")) {
						if(scase1.equals("2") )
						{
							 f3 = "<i class='action_icons action_update'  " + ApproveUpdate + " title='Approve Update Data'></i>";
						}
			       
					} 
					
					String CloseUpdate = "onclick=\"  if (confirm('Are You Sure You Want to Approve/Update This Emolument?') ){approveeditData("+ rs.getInt("id") + ","+scase1+","+cancel_status1+")}else{ return false;}\"";
						if(rs.getString("status").equals("1")) {
							if(scase1.equals("3") )
							{
									f4 = "<i class='action_icons action_update'  " + CloseUpdate + " title='Approve Update Data'></i>";
							}
						} 
					
						
						String CloseHistory = "onclick=\"  if (confirm('Are You Sure You Want to  View  History?') ){HistoryData("+ rs.getInt("id")+")}else{ return false;}\"";
						if(rs.getString("status").equals("4")) {
							if(scase1.equals("3"))
							{
									f5 = "<i class='fa fa-eye'  " + CloseHistory + " title='Close  Data'></i>";
							}
						} 
						
						
						if(rs.getString("status").equals("1")) {
							if(scase1.equals("1"))
							{
									f5 = "<i class='fa fa-eye'  " + CloseHistory + " title='Close  Data'></i>";
							}
						} 
						
						
					   list.add(f);
					   list.add(f3);
					   list.add(f4);
					   list.add(f5);
					 
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

	public ArrayList<ArrayList<String>> search_maritial_count(String personnel_no)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
		
			if(personnel_no !="") {
				qry += " and upper(p.personnel_no) = ? ";
			}
			
			
			
			q="select COUNT(m.*) as count_id  from tb_psg_maritial_discord p \n" + 
					"inner join tb_psg_maritial_discord_status_child m on m.maritial_id = p.id \n" + 
					"inner join tb_psg_trans_proposed_comm_letter c \n" + 
					"on c.id =p.comm_id  where p.id !=0  and p.service_category = cast('1' as integer)    \n" + 
					"and   m.status = cast('1' as integer) and\n" + 
					"p.cancel_status in (-1,0,2) " +qry +  "";
					
				
				stmt=conn.prepareStatement(q);
             int j =1;
				
				
				if(personnel_no !="") {
					stmt.setString(j, personnel_no);
					j += 1;
				}
				
				
			
				ResultSet rs = stmt.executeQuery();
			
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("count_id"));//0
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
	public TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO getEmolumentsByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO updateid = (TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO) sessionHQL.get(TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	

	
	public ArrayList<ArrayList<String>> Get_EmoulData_Jco(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			

			
			q="select distinct p.id,p.army_no,p.full_name,r.rank as rank ,orb.unit_name as unit_name ,TO_CHAR(bc.date_of_casuality,'dd-Mon-yyyy') as dt_of_casulity,e.id as eid" + 
					"	from tb_psg_census_jco_or_p p \r\n" + 
					"	inner join tb_psg_emolument_parent_offcr_jco e on p.id::text= e.jco_id::text \r\n" + 
					"	inner join tb_psg_mstr_rank_jco r on r.id = p.rank  and r.status='active'  \r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active'"
					+ "inner join tb_psg_census_battle_physical_casuality_jco bc   on bc.jco_id::text = p.id::text\r\n" + 
					"inner join  (select max(id) as id ,jco_id::text from tb_psg_census_battle_physical_casuality_jco \r\n" + 
					"			 where status=1 group by jco_id) ng1 on bc.id=ng1.id  \r\n" + 
					"	 where p.id::text=?";
					
				
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,String.valueOf(comm_id));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("army_no"));//0
					list.add(rs.getString("full_name"));//1
					list.add(rs.getString("rank"));//2
					list.add(rs.getString("unit_name"));//3
					list.add(rs.getString("dt_of_casulity"));//4
					list.add(rs.getString("eid"));//4
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

	public List<Map<String, Object>>   GetPersonnelNoDataJcoEmoulCont(String personnel_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct c.army_no,c.id,c.full_name,\r\n" + 
					"c.regiment, cu.rank as rankcode,u.unit_name,bc.date_of_casuality \r\n" + 
					"from tb_psg_census_jco_or_p c inner join tb_psg_mstr_gender g on c.gender=g.id \r\n" + 
					"inner join tb_miso_orbat_unt_dtl u  on c.unit_sus_no=u.sus_no \r\n" + 
//					"inner join tb_psg_mstr_course_comm ct on ct.id=c.course \r\n" + 
					"inner join tb_psg_mstr_rank_jco cu  on cu.id=c.rank \r\n" + 
					"inner join tb_psg_census_battle_physical_casuality_jco bc   on bc.jco_id = c.id\r\n" + 
					"inner join  (select max(id) as id ,jco_id from tb_psg_census_battle_physical_casuality_jco where status=1 group by jco_id) ng1 on bc.id=ng1.id\r\n" + 
					"and bc.status='1'  where  c.army_no = ? order by c.army_no  ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, personnel_no);
			
		
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

	public ArrayList<ArrayList<String>> approve_update_Jco_eno_update(BigInteger comm_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
		
			if(comm_id != BigInteger.ZERO) {
				qry += " and cast(upper(p.jco_id) as character varying) = ? ";
			}
			
			
			
			q="update TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO OFR set amount_rel =t1.amt, amount_rel_v = to_char(t1.amt, 'FM9,999,999,999,999,999') FROM\r\n" + 
									"(select sum(amount_release::float) as amt,p_id \r\n" + 
	                		" from TB_PSG_EMOLUMENT_CHILD_OFFCR_JCO where   cast(jco_id as character varying)=?  group by p_id) t1  where OFR.id=t1.p_id";
					
				
				stmt=conn.prepareStatement(q);
             int j =1;
				
				
				if(comm_id != BigInteger.ZERO) {
					stmt.setString(j, String.valueOf(comm_id));
					j += 1;
				}
				
				
			
				 stmt.executeUpdate();

		     
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
	
	
	
	public ArrayList<String> approve_update_eno_Jco_iteration(BigInteger comm_id)
	{
		ArrayList<String> alist = new ArrayList<String>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
		
			if(comm_id != BigInteger.ZERO) {
				qry += " and  cast(upper(p.jco_id) as character varying) = ?  ";
				
			}
			
			
			
			q="select itteration_status from TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO where  cast(upper(jco_id::text) as character varying) = ?  ";
					
				
				stmt=conn.prepareStatement(q);
             int j =1;
				
				
				if(comm_id != BigInteger.ZERO) {
					stmt.setString(j, String.valueOf(comm_id));
					j += 1;
				}
				
				
			
				 stmt.executeQuery();
				 ResultSet rs = stmt.executeQuery();  
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("itteration_status"));//0
				    int  a = Integer.parseInt(rs.getString("itteration_status"))+1;
				    
				    
					alist.add(String.valueOf(a));
					
	 	        }
		     
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
	
	
	public ArrayList<ArrayList<String>> App_getm_emu_detailsData_Jco_list(BigInteger comm_id,String c_status,String i_status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			String qry="";
			if(c_status.equals("2") && !i_status.equals("") && i_status.equals("-1"))
			{
				qry+="and bc.status=0";
			}
			if(c_status.equals("2") && !i_status.equals("") && i_status.equals("1"))
			{
				qry+="and bc.status=1";
			}
			
			sql = "select distinct bc.jco_id,bc.id,oem.agency_id,oem.type_of_benefits_id,oem.amount_due_v,oem.amount_rel_v,bc.p_id,"+
					"oem.reason,bc.amount_rel_v as app_amount_rel,ltrim(TO_CHAR( bc.updated_as_on ,'DD/MM/YYYY'),'0') as  updated_as_on,bc.reason as reason_add,bc.status"
					+ ",oem.amount_due,oem.amount_rel \r\n" + 
					"from tb_psg_emolument_child_offcr_jco bc\r\n" + 
					"			inner join tb_psg_emolument_parent_offcr_jco oem on oem.id = bc.p_id  \r\n" + 
					"			inner join tb_psg_agency_mstr ae on ae.id = oem.agency_id\r\n" + 
					"			inner join tb_psg_benefits_mstr be on be.id = oem.type_of_benefits_id \r\n" + 
					"			where cast(bc.jco_id as character varying)=?   "+qry;

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));
			
			
			ResultSet rs = stmt.executeQuery();

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				
				String t =  rs.getString("amount_due");
				
				String df =  rs.getString("amount_rel");
				Double s = Double.parseDouble(t) - Double.parseDouble(df);

				list.add(rs.getString("agency_id"));// 0
				list.add(rs.getString("type_of_benefits_id"));// 1
				list.add(rs.getString("amount_due_v"));// 2
				list.add(rs.getString("amount_rel_v"));// 3
				list.add(rs.getString("reason"));//4
				list.add(rs.getString("app_amount_rel"));//5
				list.add(rs.getString("reason_add"));//6
				list.add(rs.getString("updated_as_on"));//7
				list.add(rs.getString("status"));//8
				list.add(rs.getString("p_id"));//9
				list.add(rs.getString("id"));//10
				list.add(s.toString());//11

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
	
	
	public ArrayList<ArrayList<String>> App_getm_emu_close_Jco_detailsData_list(BigInteger comm_id,String c_status,String i_status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			String qry="";
			if(c_status.equals("3") && !i_status.equals("") && i_status.equals("0"))
			{
				qry+="and oem.close_status='0' and bc.status='1'";  
			}
			if(c_status.equals("3") && !i_status.equals("") && i_status.equals("1"))
			{
				qry+="and oem.status='4' ";
			}
			
			sql = "select distinct bc.jco_id,bc.id,oem.agency_id,oem.type_of_benefits_id,oem.amount_due_v,oem.amount_rel_v,bc.p_id,"+
					"oem.reason,bc.amount_rel_v as app_amount_rel,ltrim(TO_CHAR( bc.updated_as_on ,'DD/MM/YYYY'),'0') as  updated_as_on,bc.reason as reason_add,bc.status "
					+ ",oem.amount_due,oem.amount_rel \r\n" + 
					"from tb_psg_emolument_child_offcr_jco bc\r\n" + 
					"			inner join tb_psg_emolument_parent_offcr_jco oem on oem.id = bc.p_id  \r\n" + 
					"			inner join tb_psg_agency_mstr ae on ae.id = oem.agency_id\r\n" + 
					"			inner join tb_psg_benefits_mstr be on be.id = oem.type_of_benefits_id \r\n" + 
					"			where cast(bc.jco_id as character varying)=?   "+qry;

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));
			
			
			ResultSet rs = stmt.executeQuery();

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				String t =  rs.getString("amount_due");
				
				String df =  rs.getString("amount_rel");
				Double s = Double.parseDouble(t) - Double.parseDouble(df);

				list.add(rs.getString("agency_id"));// 0
				list.add(rs.getString("type_of_benefits_id"));// 1
				list.add(rs.getString("amount_due_v"));// 2
				list.add(rs.getString("amount_rel_v"));// 3
				list.add(rs.getString("reason"));//4
				list.add(rs.getString("app_amount_rel"));//5
				list.add(rs.getString("reason_add"));//6
				list.add(rs.getString("updated_as_on"));//7
				list.add(rs.getString("status"));//8
				list.add(rs.getString("p_id"));//9
				list.add(rs.getString("id"));//10
				list.add(s.toString());//11

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
	
	
	
	
	
	public ArrayList<ArrayList<String>> App_getm_emu_Jco_detailsData_app_list(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select distinct bc.jco_id,bc.id,oem.agency_id,oem.type_of_benefits_id,oem.amount_due_v,oem.amount_rel_v,"+
					"oem.reason,bc.amount_rel_v as app_amount_rel,ltrim(TO_CHAR( bc.updated_as_on ,'DD/MM/YYYY'),'0') as  updated_as_on,bc.reason as reason_add \r\n" + 
					"from tb_psg_emolument_child_offcr_jco bc\r\n" + 
					"			inner join tb_psg_emolument_parent_offcr_jco oem on oem.id = bc.p_id  \r\n" + 
					"			inner join tb_psg_agency_mstr ae on ae.id = oem.agency_id\r\n" + 
					"			inner join tb_psg_benefits_mstr be on be.id = oem.type_of_benefits_id \r\n" + 
					"			where cast(bc.jco_id as character varying)=?  and bc.status='1' ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));
			
			
			ResultSet rs = stmt.executeQuery();

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
//
//				ch_bt_id = "<input type='hidden' id = 'ch_bt_id" + i + "' name='ch_bt_id" + i + "' value = '"
//						+ rs.getString("id") + "' size='1'>"; // 0
//
//				battery_no = "<input type='text' id = 'battery_no" + i + "'  name='battery_no" + i
//						+ "'  class = 'form-control' value = '" + rs.getString("battery_no") + "' maxlength='20' onkeypress='return alpha(event, this)'>";
//
//				battery_make = "<select id='battery_make" + i + "' name='battery_make" + i
//						+ "' class = 'form-control' onchange='getbatterysize(this.value," + i + ")'></select> ";
//
//				battery_size = "<select id='battery_size" + i + "' name='battery_size" + i
//						+ "' class = 'form-control' value = '" + rs.getString("battery_size") + "'></select>";
//
//				if (rs.isLast()) {
//					add_more = "<a class='btn btn-primary btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_catbbattery"
//							+ i + "' onclick='Getinput_tbody_catbbattery(" + i + ");'><i class='fa fa-plus'></i></a>";
//				}

//				list.add(ch_bt_id); // 0
//				list.add(battery_no); // 1
//				list.add(battery_make); // 2
//				list.add(battery_size); // 3
//				list.add(add_more); // 4
				list.add(rs.getString("agency_id"));// 0
				list.add(rs.getString("type_of_benefits_id"));// 1
				list.add(rs.getString("amount_due_v"));// 2
				list.add(rs.getString("amount_rel_v"));// 3
				list.add(rs.getString("reason"));//4
				list.add(rs.getString("app_amount_rel"));//5
				list.add(rs.getString("reason_add"));//6
				list.add(rs.getString("updated_as_on"));//7
				list.add(rs.getString("id"));//8

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
	
	
	public ArrayList<ArrayList<String>> View_Emoluments_History_Jco(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			q="select pc.army_no,ag.agency_name,bn.benefits_name,e_p.amount_due, e_c.amount_release,\r\n" + 
					"e_c.reason      ,ltrim(TO_CHAR( e_c.updated_as_on ,'DD/MM/YYYY'),'0') as  updated_as_on ,pc.full_name,r.rank as rank,e_c.amount_rem\r\n" + 
					"from tb_psg_emolument_parent_offcr_jco e_p\r\n" + 
					"inner join tb_psg_emolument_child_offcr_jco e_c on e_p.id=e_c.p_id\r\n" + 
					"inner join tb_psg_census_jco_or_p pc on e_p.jco_id=pc.id\r\n" + 
					"inner join tb_psg_agency_mstr ag on ag.id=e_p.agency_id\r\n" + 
					"inner join tb_psg_benefits_mstr bn on bn.id=e_p.type_of_benefits_id\r\n" + 
					"inner join tb_psg_mstr_rank_jco r on r.id = pc.rank  and r.status='active' "
					+ " where cast(e_p.jco_id as character varying) = ?   and e_c.status in (1,2) ";
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					//= to_char(t1.amt, 'FM9,999,999,999,999,999')
					  
					String t =  rs.getString("amount_due");
				
					String df =  rs.getString("amount_release");
					 
					Double s = Double.parseDouble(t) - Double.parseDouble(df);
					list.add(rs.getString("army_no"));//0
					list.add(rs.getString("agency_name"));//1
					list.add(rs.getString("benefits_name"));//2
					list.add(rs.getString("amount_due"));//3
					list.add(rs.getString("amount_release"));//4
					list.add(rs.getString("reason"));//5
					list.add(rs.getString("updated_as_on"));//6
					list.add(rs.getString("rank"));//7
					list.add(rs.getString("full_name"));//8
					list.add(s.toString());//9
					list.add(rs.getString("amount_rem"));//10
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
	
	public ArrayList<ArrayList<String>> getm_emu_detailsDataListEdit_Jco(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select e.id as e_id ,c.id as c_id ,e.jco_id,e.amount_rel,e.amount_rel_v,e.amount_due,e.amount_due_v,e.reason ,"
					+ " e.updated_as_on,e.agency_id ,e.type_of_benefits_id  " + 
					" from  tb_psg_emolument_parent_offcr_jco e\r\n" + 
					" inner join tb_psg_emolument_child_offcr_jco c on e.id= c.p_id\r\n" + 
 					" where cast(e.jco_id as character varying) =?   ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));
			
			
			ResultSet rs = stmt.executeQuery();

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
 

//				list.add(ch_bt_id); // 0
//				list.add(battery_no); // 1
//				list.add(battery_make); // 2
//				list.add(battery_size); // 3
//				list.add(add_more); // 4
				list.add(rs.getString("e_id"));// 0
				list.add(rs.getString("c_id"));// 1
				list.add(rs.getString("jco_id"));// 2
				list.add(rs.getString("amount_rel"));// 3
				list.add(rs.getString("amount_rel_v"));//4
				list.add(rs.getString("amount_due"));//5
				list.add(rs.getString("amount_due_v"));//6
				list.add(rs.getString("reason"));//7
				list.add(rs.getString("updated_as_on"));//8
				list.add(rs.getString("agency_id"));//9
				list.add(rs.getString("type_of_benefits_id"));//10
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
	
	
	
	
	public ArrayList<ArrayList<String>> getm_emu_Jco_detailsDataListEdit_childs(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select e.id as e_id ,c.id as c_id ,e.jco_id,e.amount_rel,e.amount_rel_v,e.amount_due,e.amount_due_v,e.reason ,"
					+ " e.updated_as_on,e.agency_id ,e.type_of_benefits_id ,c.status as child_status " + 
					" from  tb_psg_emolument_parent_offcr_jco e\r\n" + 
					" inner join tb_psg_emolument_child_offcr_jco c on e.id= c.p_id\r\n" + 
 					" where cast(e.jco_id as character varying)=? and c.status =1  ";

			
//			sql = "select e.id as e_id ,c.id as c_id ,e.comm_id,c.amount_release as amount_rel,c.amount_rel_v ,e.amount_due,e.amount_due_v,c.reason , c.updated_as_on,e.agency_id ,e.type_of_benefits_id \r\n" + 
//					" from  tb_psg_emolument_parent_offcr e\r\n" + 
//					" inner join tb_psg_emolument_child_offcr c on e.id= c.p_id\r\n" + 
//					" where cast(e.comm_id as character varying)=?  and c.status =1  ";
//			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(comm_id));
			
			
			
			ResultSet rs = stmt.executeQuery();

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				String t =  rs.getString("amount_due");
			
				String df =  rs.getString("amount_rel");
				Double s = Double.parseDouble(t) - Double.parseDouble(df);

//				list.add(ch_bt_id); // 0
//				list.add(battery_no); // 1
//				list.add(battery_make); // 2
//				list.add(battery_size); // 3
//				list.add(add_more); // 4
				list.add(rs.getString("e_id"));// 0
				list.add(rs.getString("c_id"));// 1
				list.add(rs.getString("jco_id"));// 2
				list.add(rs.getString("amount_rel"));// 3
				list.add(rs.getString("amount_rel_v"));//4
				list.add(rs.getString("amount_due"));//5
				list.add(rs.getString("amount_due_v"));//6
				list.add(rs.getString("reason"));//7
				list.add(rs.getString("updated_as_on"));//8
				list.add(rs.getString("agency_id"));//9
				list.add(rs.getString("type_of_benefits_id"));//10
				list.add(s.toString());//11
				list.add(rs.getString("child_status"));//12
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
