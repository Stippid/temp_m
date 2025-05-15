package com.dao.psg.Jco_Service_Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class View_JCODaoImpl implements View_JCODao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_JCOData(String unit_name,String unit_sus_no,String army_no,String status,String rank,String roleSusNo,String roleType
			,String name,String y_comm,String y_dob,String p_arm,String comm_status)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(comm_status.equals("1") || comm_status.equals("5")) {
				qry+=" and  tp.status in ('1','5')";
			}
			if(comm_status.equals("4")) {
				qry+=" and  tp.status ='4' ";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and tp.unit_sus_no = ?";
			}
			else if(!unit_sus_no.equals("")) {
				qry+=" and tp.unit_sus_no = ?";
			}		
			if(!army_no.equals("")) {
				qry += "  and tp.army_no = ? ";
			}
			if(!name.equals("")) {
				qry += "  and upper(tp.full_name) like ? ";
			}
			if(!rank.equals("") && !rank.equals("0")) {
				qry += "  and tp.rank= ? ";
			}
			if(!y_comm.equals("") && !y_comm.equals("0")) {
				qry += "and  date_part('year',enroll_dt)::text= ? ";
			}
			if(!y_dob.equals("") && !y_dob.equals("0")) {
				qry += "and  date_part('year',date_of_birth)::text= ? ";
			}
			if(!p_arm.equals("") && !p_arm.equals("0") ) {
				qry += "  and tp.arm_service= ? ";
			}
			
			q = "select distinct \r\n" + 
					"	tp.id ,\r\n" + 
					"	tp.army_no,\r\n" + 
					"	r.rank as rank,\r\n" + 
					"	tp.full_name,tp.unit_sus_no,\r\n" + 
					"	TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					" arm.arm_desc as parent_arm, "+
					"	tp.status,\r\n" + 
					"	mc.course_name,tp.id as jco_id,tp.marital_status,tp.modified_date ,u.unit_name,tp.status as comm_status\r\n" + 
					"	FROM tb_psg_census_jco_or_p tp \r\n" + 
					"	inner join tb_psg_mstr_rank_jco r on r.id = tp.rank  \r\n" + 
					" 	inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.arm_service "+
					"	inner join tb_miso_orbat_unt_dtl u on u.sus_no =tp.unit_sus_no and u.status_sus_no='Active'\r\n" + 
					"	left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" + 
					"	 join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" + 
					"	where tp.id !=  0   " + qry+
					 "order by tp.modified_date desc";
			stmt=conn.prepareStatement(q);
			System.err.println("Query for service record:--- \n "+stmt);
			
			
		    
				int j =1;
				if(!qry.equals(""))     
				{
			
					if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
					else if(!unit_sus_no.equals("")) {
						stmt.setString(j, unit_sus_no);
						j += 1;
					}		
					if(!army_no.equals("")) {
						stmt.setString(j, army_no);
						j += 1;
					}
					if(!name.equals("")) {
						stmt.setString(j, name.toUpperCase()+"%");
						j += 1;
					}
					if(!rank.equals("") && !rank.equals("0")) {
						stmt.setInt(j, Integer.parseInt(rank));
						j += 1;
					}
					if(!y_comm.equals("") && !y_comm.equals("0")) {
						stmt.setString(j, y_comm);
						j += 1;
					}
					if(!y_dob.equals("") && !y_dob.equals("0")) {
						stmt.setString(j, y_dob);
						j += 1;
					}
					if(!p_arm.equals("") && !p_arm.equals("0") ) {
						stmt.setString(j, p_arm);
						j += 1;
					}
				 
				
			}
				 
	      ResultSet rs = stmt.executeQuery();   
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	//list.add(rs.getString("cadet_no")); //0
				list.add(rs.getString("army_no")); //1
				list.add(rs.getString("rank"));				//2
				list.add(rs.getString("full_name")); //3
				list.add(rs.getString("unit_name")); //4
				//list.add(rs.getString("status")); //5
				String f1 = "";
				String View1 = "onclick=\"  {View1Data("+ rs.getString("id") + ",'"+rs.getString("jco_id")+"','"+rs.getString("unit_sus_no")+"','"+rs.getInt("comm_status")+"')}\"";
                f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
			     list.add(f1); //5
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
	
	
	
	public List<Map<String, Object>> View_JCODataByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			  if(comm_status == 1 || comm_status == 5) { 
				  comm_stats = "(?,?) ";
				  }
			  if(comm_status == 4) {
				  comm_stats = "(?) ";
				  }
			 
			if(!roleSusNo.equals("")) {
				qry+=" and c.unit_sus_no = ?";
			}
			if(!jco_id.equals("") ) {
				qry += " and c.id=? ";
			}
			 //260194
			q= "select c.id,COALESCE(c.army_no,'') as army_no ,\r\n" + 
					" COALESCE(c.full_name,'') as full_name,\r\n" + 
					" COALESCE(r.rank,'') as rank,\r\n" + 
					" COALESCE(TO_CHAR(cr.date_of_rank ,'DD-MON-YYYY'),'') as date_of_rank, case when (arm1.arm_desc is null or arm1.arm_desc='0') then arm.arm_desc else arm1.arm_desc end as regiment,\r\n" + 
					" COALESCE(TO_CHAR(c.enroll_dt,'DD-MON-YYYY'),'') as commission_date,\r\n" + 
					" COALESCE(TO_CHAR(c.date_of_seniority,'DD-MON-YYYY'),'') as date_of_seniority,\r\n" + 
					" COALESCE(TO_CHAR(c.modified_date ,'DD-MON-YYYY'),'') as  modified_date, COALESCE(a.appointment,'') as appointment,  COALESCE(TO_CHAR(ca.date_of_appointment,'DD-MON-YYYY'),'') as date_of_appointment,\r\n" + 
					" COALESCE(m.unit_name,'') as unit_name ,\r\n" + 
					" COALESCE(TO_CHAR(c.date_of_tos,'DD-MON-YYYY'),'') as date_of_tos,\r\n" + 
					" COALESCE(TO_CHAR(c.date_of_birth,'DD-MON-YYYY'),'') as date_of_birth,\r\n" + 
					"  COALESCE(gen.gender_name,'') as gender_name ,\r\n" + 
					" case when upper(rel.religion_name)='OTHERS' or  upper(rel.religion_name)='OTHER' then  COALESCE(c.religion_other,'')\r\n" + 
					"  else COALESCE(rel.religion_name,'') end as religion_name,  \r\n" + 
					" case when upper(nati.nationality_name)='OTHERS' or  upper(nati.nationality_name)='OTHER' then  COALESCE(c.nationality_other,'')\r\n" + 
					"  else COALESCE(nati.nationality_name,'') end as nationality_name,  \r\n" + 
					" case when upper(mol.mothertounge)='OTHERS' or  upper(mol.mothertounge)='OTHER' then  COALESCE(c.mother_tongue_other,'')\r\n" + 
					"  else COALESCE(mol.mothertounge,'') end as mothertounge,  \r\n" + 
					" case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(c.state_other,'')\r\n" + 
					"  else COALESCE(st.state_name,'') end as state_name,  \r\n" + 
					"  PGP_SYM_DECRYPT(c.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,\r\n" + 
					"  COALESCE(bd.blood_desc,'') as blood_desc,\r\n" + 
					" case when hgt.height_id=0 then '' else COALESCE(hgt.centimeter,'') end as centimeter,\r\n" + 
					" case when ms.marital_id=10 then '' else COALESCE( ms.marital_name,'') end as marital_name,\r\n" + 
					" COALESCE(ic.id_card_no,'') as id_card_no,\r\n" + 
					" COALESCE(TO_CHAR(ic.issue_dt ,'DD-MON-YYYY'),'') as issue_dt,\r\n" + 
					"case when med.date_of_authority is not null  then COALESCE(TO_CHAR(med.date_of_authority ,'DD-MON-YYYY'),'')\r\n" + 
					"else '' end as  medical,\r\n" + 
					" COALESCE(PGP_SYM_DECRYPT(cac.gmail ::bytea,current_setting('miso.version')),'') as gmail,\r\n" + 
					" COALESCE(PGP_SYM_DECRYPT(cac.mobile_no ::bytea,current_setting('miso.version')),'') as mobile_no, COALESCE(PGP_SYM_DECRYPT(cac.nic_mail ::bytea,current_setting('miso.version')),'') as nic_mail,\r\n" + 
					" ic.id as imgid,ic.identity_image \r\n" + 
					" from tb_psg_census_jco_or_p c  \r\n" + 
					" left join tb_psg_change_of_rank_jco cr on (cr.jco_id= c.id and cr.status=1) \r\n" + 
					" left join tb_psg_mstr_rank_jco r on r.id = cr.rank and r.status = 'active'\r\n" + 
					" left join tb_psg_change_of_appointment_jco ca on ca.jco_id= c.id \r\n" + 
					" left join tb_psg_mstr_appt_jco a on a.id = ca.appointment and a.status = 'active'\r\n" + 
					" left join tb_miso_orbat_unt_dtl m on m.sus_no = c.unit_sus_no and m.status_sus_no='Active'\r\n" + 
					" left join tb_psg_mstr_gender gen on gen.id=c.gender \r\n" + 
					" inner join tb_miso_orbat_arm_code  arm on (arm.arm_code =  c.arm_service  )\r\n" + 
					" left join tb_miso_orbat_arm_code arm1 on arm1.arm_code = c.regiment\r\n" + 
					" left  join tb_psg_mstr_religion rel on rel.religion_id=c.religion and rel.status = 'active'\r\n" + 
					" left join tb_psg_mstr_nationality nati on nati.nationality_id=c.nationality and nati.status='active'\r\n" + 
					"  left join tb_psg_update_census_address_of_birth_jco u_a on (u_a.jco_id=c.id and u_a.status=1)\r\n" + 
					" left join tb_psg_mstr_state st on st.state_id = u_a.state_of_birth	and st.status = 'active'	\r\n" + 
					" left join tb_psg_mothertounge mol on mol.id=c.mother_tongue and mol.status='active' \r\n" + 
					" left join tb_psg_mstr_blood bd on bd.id=c.blood_group and bd.status='active'\r\n" + 
					" left  join tb_psg_mstr_marital_status ms on c.marital_status = ms.marital_id \r\n" + 
					" left  join  tb_psg_identity_card_jco ic ON ic.jco_id = c.id and ic.status=1 \r\n" + 
					" left join tb_psg_census_contact_cda_account_details_jco cac on cac.jco_id=c.id\r\n" + 
					" left join tb_psg_medical_category_jco med on med.jco_id=c.id \r\n" + 
					" left join tb_psg_mstr_height hgt on hgt.height_id = c.height and hgt.status='active' where  c.status in  " + comm_stats +   qry ; 

			
			
			stmt=conn.prepareStatement(q);   	
		    int j =1;
		   
			  if(comm_status == 1 || comm_status == 5) { 
				  stmt.setInt(j, Integer.parseInt("1"));
				  j += 1;
				  stmt.setInt(j, Integer.parseInt("5"));
			      j += 1; 
			  } if(comm_status == 4) { 
				  stmt.setInt(j, Integer.parseInt("4"));
				  j += 1; 
				  }
			 
			 
			  if (!roleSusNo.equals("")) { 
				  stmt.setString(j, roleSusNo); 
				  j += 1;
				  }
			 
		    if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 	
		    ResultSet rs = stmt.executeQuery();
		    System.err.println("Query for service record 2:--- \n "+stmt);
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
					 
					
						if(rs.getObject(i)==null  )
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), "");
			 	    	}
			 	    	else if(metaData.getColumnLabel(i) == "identity_image" || metaData.getColumnLabel(i).equals("identity_image"))
			 	    	{
			 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
			 	    	}
						else
						{
							columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
						}

			 	    }
		 	    list.add(columns);
		 	 }
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	//shape
	public List<Map<String, Object>> Sh_UpadteJCODataByid(int id,String jco_id,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!jco_id.equals("")) {
				qry += " and m.jco_id=? ";
			} 
			
			q= "select  COALESCE(m.shape,'') as shape, COALESCE(m.cope,'') as cope,pcl.id\r\n" + 
					"from tb_psg_census_jco_or_p pcl \r\n" + 
					"left join tb_psg_medical_categoryhistory_jco m on pcl.id=m.jco_id where m.status=1   and  pcl.status in " + comm_stats + qry;
					  
	        		
	        
	        stmt=conn.prepareStatement(q);   	
		    int j =1;
		    
			if(comm_status == 1  || comm_status == 5) {
			 	stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
			
			
			
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			    
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	//
	
	public List<Map<String, Object>> FM_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and pcl.id =? ";
			} 
	     
			
	        q=" select pcl.father_name,TO_CHAR(pcl.father_dob,'DD-MON-YYYY') AS father_dob,pcl.mother_name,\r\n" + 
	        		" TO_CHAR(pcl.mother_dob,'DD-MON-YYYY') AS mother_dob \r\n" + 
	        		"  from tb_psg_census_jco_or_p pcl\r\n" + 
	        		" where  pcl.status in  " + comm_stats + qry; 
		    stmt=conn.prepareStatement(q);   	
		   
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	  stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				  stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				  stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}
		    if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
			 
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));

		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	///family
	public List<Map<String, Object>> Spouse_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and pcl.id =? ";
			} 
	     
			
			 q=" select COALESCE(ms.marital_name,'') as marital_name,fs.maiden_name,"+
		        		"TO_CHAR(fs.marriage_date,'DD-MON-YYYY') AS marriage_date,TO_CHAR(fs.date_of_birth,'DD-MON-YYYY') AS date_of_birth,\r\n" + 
		        		"fs.place_of_birth,\r\n "+
		        		 " case when upper(nati.nationality_name)='OTHERS' or  upper(nati.nationality_name)='OTHER' then  COALESCE(fs.other_nationality,'') \r\n" + 
		        		" else COALESCE(nati.nationality_name,'') end as nationality_name,\r\n" + 
		        		" PGP_SYM_DECRYPT(fs.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,COALESCE(PGP_SYM_DECRYPT(fs.pan_card ::bytea,current_setting('miso.version')),'') as pan_card,TO_CHAR(fs.divorce_date,'DD-MON-YYYY') AS divorce_date , case when  upper(t1.ex_servicemen)='OTHER' then fs.other_spouse_ser\r\n" + 
		        		" else COALESCE(t1.ex_servicemen::text,'') end as spouse_service ,COALESCE(fs.spouse_personal_no,'') as spouse_personal_no\r\n" + 
		        		
		        		"  from tb_psg_census_jco_or_p pcl\r\n" + 
		        		" inner join tb_psg_census_family_married_jco fs on fs.jco_id=pcl.id  \r\n" + 
		        		" left join tb_psg_mstr_nationality nati on nati.nationality_id=fs.nationality \r\n" + 
		        		" left join tb_psg_mstr_marital_status ms on ms.marital_id=fs.marital_status \r\n" + 
		        		"	left join tb_psg_mstr_exservicemen t1 on t1.id = fs.spouse_service "+
		        		" where  fs.status='1'  and  pcl.status in  " + comm_stats + qry; 
		    stmt=conn.prepareStatement(q);   	
		   
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	  stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				  stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				  stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}
		    if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
			 
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));

		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
//child
	public List<Map<String, Object>> CHILD_JCOByid(int id,String jco_id,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			if(!jco_id.equals("")) {
				qry += " cm.jco_id = ? ";
			}
		 
			  q="select distinct cm.id,cm.name,TO_CHAR(cm.date_of_birth,'DD-MON-YYYY') AS date_of_birth,d.label as gender  \r\n" + 
		        		" from tb_psg_census_jco_or_p pcl \r\n" + 
		        		" inner join tb_psg_census_children_jco cm on cm.jco_id = pcl.id and cm.status='1' \r\n" + 
		        		"inner join t_domain_value d on cast(cm.relationship as character varying)=d.codevalue  and  d.domainid='CHILD_RELATIONSHIP'\r\n" + 
		        		"where  " +qry + " ORDER BY cm.id\r\n" ;	
		    stmt=conn.prepareStatement(q);   
		    int j =1;
			
			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		    
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	//tenure
	public List<Map<String, Object>> TENU_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			if(!jco_id.equals("")) {
				qry += " op.jco_id=? ";
			}
	       
			 q= "select\r\n" + 
                     "COALESCE(orb.unit_name ,'') as unit_name ,\r\n" + 
                     "COALESCE(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY'),'') as dt_of_tos,\r\n" + 
                     "COALESCE(LEAD(TO_CHAR(op.dt_of_sos,'DD-MON-YYYY')) OVER (ORDER BY op.id ),'')as nextvalue ,\r\n" + 
                     "COALESCE(l.code_value,'') as place,\r\n" + 
                     "COALESCE(l1.label,'') as unit_loc_type,\r\n" + 
                     "COALESCE(fv.unit_name,'') as command,\r\n" + 
                     "CASE when   op.tenure_date is null then\r\n" + 
                     "CAST(EXTRACT(year FROM age(now()::timestamp without time zone,op.dt_of_tos))*12 + \r\n" + 
                     "     EXTRACT(month FROM age(now()::timestamp without time zone,op.dt_of_tos)) AS INTEGER)\r\n" + 
                     "else\r\n" + 
                     "         CAST(EXTRACT(year FROM age(op.tenure_date,op.dt_of_tos))*12 + \r\n" + 
                     "           EXTRACT(month FROM age(op.tenure_date,op.dt_of_tos)) AS INTEGER) +1   end as month     \r\n" + 
                     "from\r\n" + 
                     "tb_psg_census_jco_or_p c \r\n" + 
                     "left join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status in "+comm_stats + " and op.status='1' \r\n" + 
                     "left join   tb_psg_change_of_rank_jco r on r.id = op.rank \r\n" + 
                     "left join tb_miso_orbat_unt_dtl orb on orb.sus_no = op.to_sus_no and status_sus_no in ('Active','Inactive') \r\n" + 
                     "left join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
                     "left join t_domain_value l1   on l.type_loc=l1.codevalue and   l1.domainid='TYPEOFLOCATION'\r\n" + 
                     "left join all_fmn_view fv   on orb.sus_no = op.to_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) \r\n" + 
                     "                       =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
                     "left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = op.from_sus_no and orb1.status_sus_no in ('Active','Inactive')\r\n" + 
                     "left join   tb_psg_mstr_rank_jco r1 on r1.id =op.app_name and r1.status = 'active'\r\n" + 
                     "where " + qry + "order by op.id";                                   

	        		
		    stmt=conn.prepareStatement(q);
		    
		    int j =1;
 
		    if(comm_status == 1  || comm_status == 5) {
				stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
			
			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
				
			} 
			
		    ResultSet rs = stmt.executeQuery();
		    System.err.println("Query for tenure details :--- \n "+stmt);
		    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
		
			    int sum=0;
			    int i=1;
			    Map<String, Object> columns = null;
			while (rs.next()) {
				 columns = new LinkedHashMap<String, Object>();
		 	    for ( i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	  
		 	    list.add(columns);
		 	 }
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	public List<Map<String, Object>> TENU_Total_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
				if(!jco_id.equals("")) {
				qry += " op.jco_id=? ";
			}
	
	      //260194
				q= "select\r\n" + 
                        "COALESCE(orb.unit_name ,'') as unit_name ,\r\n" + 
                        "COALESCE(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY'),'') as dt_of_tos,\r\n" + 
                        "COALESCE(LEAD(TO_CHAR(op.dt_of_tos,'DD-MON-YYYY')) OVER (ORDER BY op.id ),'')as nextvalue ,\r\n" + 
                        "COALESCE(l.code_value,'') as place,\r\n" + 
                        "COALESCE(l1.label,'') as unit_loc_type,\r\n" + 
                        "COALESCE(fv.unit_name,'') as command,\r\n" + 
                        "CASE when   op.tenure_date is null then\r\n" + 
                        "CAST(EXTRACT(year FROM age(now()::timestamp without time zone,op.dt_of_tos))*12 + \r\n" + 
                        "     EXTRACT(month FROM age(now()::timestamp without time zone,op.dt_of_tos)) AS INTEGER)\r\n" + 
                        "else\r\n" + 
                        "         CAST(EXTRACT(year FROM age(op.tenure_date,op.dt_of_tos))*12 + \r\n" + 
                        "           EXTRACT(month FROM age(op.tenure_date,op.dt_of_tos)) AS INTEGER)+1 end as month     \r\n" + 
                        "from\r\n" + 
                        "tb_psg_census_jco_or_p c \r\n" + 
                        "left join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status in "+comm_stats + " and op.status='1' \r\n" + 
                        "left join   tb_psg_change_of_rank_jco r on r.id = op.rank \r\n" + 
                        "left join tb_miso_orbat_unt_dtl orb on orb.sus_no = op.to_sus_no and status_sus_no='Active'\r\n" + 
                        "left join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
                        "left join t_domain_value l1   on l.type_loc=l1.codevalue and   l1.domainid='TYPEOFLOCATION'\r\n" + 
                        "left join all_fmn_view fv   on orb.sus_no = op.to_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) \r\n" + 
                        "                       =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
                        "left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = op.from_sus_no and orb1.status_sus_no in ('Active','Inactive')\r\n" + 
                        "left join   tb_psg_mstr_rank_jco r1 on r1.id =op.app_name and r1.status = 'active'\r\n" + 
                        "where " + qry + "order by op.id";
			        		

	         
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
				stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
				
			} 
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			    int sum=0;
			    int i=1;
			    Map<String, Object> columns = null;
			while (rs.next()) {
				 columns = new LinkedHashMap<String, Object>();
		 	  
		 	    if(rs.getObject(7) != null && !rs.getObject(7).equals(""))
		 	    {
		 	    	sum+=Integer.parseInt(String.valueOf(rs.getObject(7)));
		 		}
		 	  
		 	 }
			 
			 columns.put("Total_tenure", sum);	
		 	    list.add(columns);
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	//field service
	public List<Map<String, Object>> Field_View_JCOByid(int id,String jco_id,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			if(!jco_id.equals("")) {
				qry += " and  ch.jco_id=? ";
			}
			 q="SELECT ch.personnel_no,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer))\r\n" + 
			 		"		  FROM tb_psg_field_service_p_jco p \r\n" + 
			 		"		  left join tb_psg_field_service_ch_jco ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and (fd_services in (select area_id from tb_psg_field_area_combination where cat_name='peace')\r\n" + 
			 		"		 or  ((SELECT count(chi.id) from tb_psg_field_service_p_jco pi left join tb_psg_field_service_ch_jco chi on pi.id=chi.p_id where chi.id!=ch1.id and ch1.jco_id=chi.jco_id and chi.to_date is not  null and\r\n" + 
			 		"		(ch1.from_date between chi.from_date and chi.to_date or ch1.to_date between chi.from_date and chi.to_date) and pi.fd_services \r\n" + 
			 		"			   not in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')) = 0 \r\n" + 
			 		"			  and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='field'))\r\n" + 
			 		"		 )),0) as PEACE,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) \r\n" + 
			 		"		  FROM tb_psg_field_service_p_jco p \r\n" + 
			 		"		  left join tb_psg_field_service_ch_jco ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='field')\r\n" + 
			 		"		 and  (SELECT count(chi.id) \r\n" + 
			 		"			   from tb_psg_field_service_p_jco pi \r\n" + 
			 		"			   left join tb_psg_field_service_ch_jco chi on pi.id=chi.p_id\r\n" + 
			 		"			   where chi.id!=ch1.id and ch1.jco_id=chi.jco_id and chi.to_date is not  null and\r\n" + 
			 		"		(ch1.from_date between chi.from_date and chi.to_date or ch1.to_date between chi.from_date and chi.to_date) and pi.fd_services\r\n" + 
			 		"			   not in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')) = 0\r\n" + 
			 		"		 ),0) as FIELD,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p_jco p \r\n" + 
			 		"		  left join tb_psg_field_service_ch_jco ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='high_alt')),0) as HIGH_ALT,\r\n" + 
			 		"COALESCE((select sum(cast (coalesce(duration,'0') as integer)) FROM tb_psg_field_service_p_jco p left join tb_psg_field_service_ch_jco ch1 on p.id=ch1.p_id \r\n" + 
			 		"		  where ch1.personnel_no=ch.personnel_no and duration!='' and fd_services in (select area_id from tb_psg_field_area_combination where cat_name='ci_ops')),0) as CI_OPS\r\n" + 
			 		"FROM tb_psg_field_service_p_jco f\r\n" + 
			 		"left join tb_psg_field_service_ch_jco ch on f.id=ch.p_id \r\n" + 
			 		"where ch.cancel_status != 1 "+qry+"  group by ch.personnel_no ";
	        		
		    stmt=conn.prepareStatement(q); 
		    
		    int j =1;
		    if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		  

		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			if (list.size()==0) {
				 Map<String, Object> columns1 = new LinkedHashMap<String, Object>();
			 	    for (int i = 1; i <= columnCount; i++) {
			 	    	columns1.put(metaData.getColumnLabel(i), 0);
			 	    }
			 	    list.add(columns1);
			}
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	
	
	//army course
		public List<Map<String, Object>> ARM_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and ac.jco_id=? ";
				}
		  	//sana
		        q= " select e.course_name,e.grade,e.course_institute,e.course_name,e.from_date,e.to_date from( select \r\n"+
		        		
		        		 " COALESCE(ac.course_name,'') as course_name,\r\n" + 
		        		 "case when upper(dv.div)='OTHERS' or  upper(dv.div)='OTHER' then  COALESCE(ac.ar_course_div_ot,'')\r\n" + 
			        		"  else COALESCE(dv.div,'') end as grade , \r\n" + 
		        		 "case when upper(ci.course_institute)='OTHERS' or  upper(ci.course_institute)='OTHER' then  COALESCE(ac.course_institute_other,'')\r\n" + 
			        		"  else COALESCE(ci.course_institute,'') end as course_institute , \r\n" + 
			        		 " ac.id,\r\n" + 
		        		"			COALESCE(TO_CHAR(ac.start_date ,'DD-MON-YYYY'),'') as from_date,\r\n" + 
		        		"			COALESCE(TO_CHAR(ac.date_of_completion ,'DD-MON-YYYY'),'') as to_date\r\n" + 
		        		"			from tb_psg_census_army_course_jco ac\r\n" + 
		        		"	        left join tb_psg_census_jco_or_p pcl on pcl.id=ac.jco_id \r\n" + 
		        		"			left join tb_psg_mstr_course_institute ci on ci.id=ac.course_institute \r\n" + 
		        		"	        left join tb_psg_mstr_div_grade dv on dv.id= cast (ac.div_grade as integer) and dv.status = 'active'  \r\n" + 
		        		"	        		where ac.status=1 and pcl.status in "+ comm_stats  + qry + " order by ac.id asc) as e";
			    stmt=conn.prepareStatement(q);   	
			    int j =1;
				
			    if(comm_status == 1  || comm_status == 5) {
						stmt.setInt(j, Integer.parseInt("1"));
						j += 1;
						stmt.setInt(j, Integer.parseInt("5"));
						j += 1;
					}
					if(comm_status == 4) {
						stmt.setInt(j, Integer.parseInt("4"));
						j += 1;
					}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}
				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			    ResultSet rs = stmt.executeQuery();    
			    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}



	
	public List<Map<String, Object>> PE_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}	
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and ex.jco_id=? ";
			}
			//sana
	        q= "select e.exam,e.date_of_passing  from (select \r\n" + 
	        		"	case when upper(td.promo_exam)='OTHERS' or  upper(td.promo_exam)='OTHER' then  COALESCE(ex.exam_other,'')\r\n" + 
	        		"  else COALESCE(td.promo_exam,'') end as exam , \r\n" + 
	  
	        		" to_char(CAST(concat(SUBSTRING(date_of_passing, 1, 4), '-' ,\r\n" + 
	        		"					SUBSTRING(date_of_passing, 6, 7) , '-' , '01') AS DATE) ,'Mon YYYY') as date_of_passing,ex.id \r\n" + 
	        		"from  tb_psg_census_promo_exam_jco ex\r\n" + 
	        		"left join tb_psg_census_jco_or_p pcl on pcl.id=ex.jco_id\r\n" + 
	        		"left join tb_psg_mstr_promotional_exam_jco td on cast(td.id as character varying)= ex.exam  \r\n" + 
	        		"where ex.status=1 and pcl.status in " + comm_stats  +qry +" order by ex.id asc) as e";
	        		
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
					stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		    	
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}

	//acedemic qualification
		public List<Map<String, Object>> AQ_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}
				
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and cq.jco_id=? ";
				}
				 
		        q= "select \r\n" +
		        		
		        		"COALESCE(eq.examination_passed,'') as examination_pass,\r\n" + 
		        		"COALESCE(cq.passing_year) as passing_year,\r\n" + 
		        		"case when upper(dg.div)='OTHERS' or  upper(dg.div)='OTHER' then  COALESCE(cq.class_other,'')\r\n" + 
		        		"  else COALESCE(dg.div,'') end as div_class,  \r\n" +
		        		"COALESCE(STRING_AGG (DISTINCT s.description , ','),'') as description,COALESCE(cq.institute ,'') as  institute \r\n" + 
		        		"from tb_psg_census_jco_or_p pcl\r\n" + 
		        		"left join tb_psg_census_qualification_jco cq on cq.jco_id = pcl.id \r\n" + 
		        		"left join tb_psg_mstr_examination_passed eq on cq.examination_pass = eq.id \r\n" + 
		        		"left join tb_psg_mstr_div_grade dg on dg.id=cq.div_class --and dg.status = 'active'\r\n" + 
		        		"left join tb_psg_census_subject s on  cast(s.id as character varying) = any(string_to_array(cq.subject  , ','))	\r\n" + 
		        		"where cq.status= 1  and  pcl.status in  "+comm_stats + " \r\n" + 
		        		" and cq.type='2' \r\n"  
		        		+qry  + 
		        		"group by 1,2,3,5";
		        		
			    stmt=conn.prepareStatement(q);  	
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
					stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				while (rs.next()) {
			 	
			 	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
			 	 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
			 	  
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}

	
	public List<Map<String, Object>> PTQ_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and cq.jco_id=? ";
			}
	     	        
	        q= "select \r\n" + 
	        		"CONCAT(d.degree,'-',s1.specialization) AS specialization,\r\n" + 
	        		"COALESCE(cq.passing_year,'0') as passing_year,case when upper(dg.div)='OTHERS' or  upper(dg.div)='OTHER' then  COALESCE(cq.class_other,'')\r\n" + 
	        		"  else COALESCE(dg.div,'') end as div_class ,\r\n" + 
	        		"COALESCE(STRING_AGG (DISTINCT s.description , ','),'') as description \r\n" + 
	        		" 	from tb_psg_census_jco_or_p pcl   \r\n" + 
	        		"left join tb_psg_census_qualification_jco cq on cq.jco_id = pcl.id  \r\n" + 
	        		"left join tb_psg_mstr_div_grade dg on dg.id=cq.div_class and dg.status = 'active' \r\n" + 
	        		"left join tb_psg_mstr_degree d on d.id =cq.degree and d.status = 'active' \r\n" + 
	        		"left join tb_psg_mstr_specialization s1 on s1.id=cq.specialization and s1.status = 'active' \r\n" + 
	        		"left join tb_psg_census_subject s on cast(s.id as character varying) = any(string_to_array(cq.subject  , ',')) " + 
	        		"where cq.status= 1 and  pcl.status in 	"+comm_stats + "  and cq.type='6' " +qry+ " group by 1,2,3";
	     
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
						stmt.setInt(j, Integer.parseInt("1"));
						j += 1;
						stmt.setInt(j, Integer.parseInt("5"));
						j += 1;
					}
					if(comm_status == 4) {
						stmt.setInt(j, Integer.parseInt("4"));
						j += 1;
					}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		    
		    ResultSet rs = stmt.executeQuery();    
		    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	public List<Map<String, Object>> ILan_View_JCOByid(String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
		
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			
			if(!jco_id.equals("")) {
				qry += " and cl.jco_id=? ";
			}
	        q="select \r\n" + 
					"case when upper(il.ind_language)='OTHERS' or  upper(il.ind_language)='OTHER' then  COALESCE(cl.other_language,'')\r\n" + 
	        		"  else COALESCE(il.ind_language,'') end as ind_language , \r\n" +
	        		"case when upper(ls.name)='OTHERS' or  upper(ls.name)='OTHER' then  COALESCE(cl.other_lang_std,'')\r\n" + 
	        		"  else COALESCE(ls.name,'') end as lan_std  \r\n" +
	        		"from tb_psg_census_jco_or_p pcl \r\n" + 
	        		"\r\n" + 
	        		"left join tb_psg_census_language_jco cl on cl.jco_id=pcl.id\r\n" + 
	        		"left join tb_psg_lang_std ls on ls.id = cl.lang_std and ls.status = 'active' \r\n" + 
	        		"left join tb_psg_mstr_indian_language il on il.id = cl.language and il.status = 'active' "
	        		+ " where cl.status= 1 and cl.language !='0'  and pcl.status in "+ comm_stats +qry;
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
				stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		    
			 
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i),rs.getObject(i).toString().toUpperCase());

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	//forign lang
	public List<Map<String, Object>> FLan_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and pcl.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and pcl.id=? ";
			}
	 
	        	
	        q=" select \r\n" + 
	        		" case when upper(fl.foreign_language_name)='OTHERS' or  upper(fl.foreign_language_name)='OTHER' then  COALESCE(cl.f_other_language,'')\r\n" + 
	        		"  else COALESCE(fl.foreign_language_name,'') end as foreign_language_name,  \r\n" +
	        		"case when upper(ls.name)='OTHERS' or  upper(ls.name)='OTHER' then  COALESCE(cl.f_other_lang_std,'')\r\n" + 
	        		"  else COALESCE(ls.name,'') end as for_std,  \r\n" +
	        		"case when upper(lp.name)='OTHERS' or  upper(lp.name)='OTHER' then  COALESCE(cl.f_other_prof,'')\r\n" + 
	        		"  else COALESCE(lp.name,'') end as for_prof  \r\n" +
	        		
	        		"from tb_psg_census_jco_or_p pcl \r\n" + 
	        		"left join tb_psg_census_language_jco cl on cl.jco_id=pcl.id\r\n" + 
	        		"left join tb_psg_lang_proof lp on lp.id= cl.f_lang_prof and lp.status = 'active' \r\n" + 
	        		"left join tb_psg_mstr_foreign_language fl on fl.id= cl.foreign_language and fl.status = 'active' \r\n" + 
	        		"left join tb_psg_lang_std ls on ls.id = cl.lang_std and ls.status = 'active' \r\n" + 
	        		 "where cl.status= '1'  and cl.foreign_language !='0' and pcl.status in "+ comm_stats+ qry ;
		    stmt=conn.prepareStatement(q);  
		  
		    int j =1;
		    
		    if(comm_status == 1  || comm_status == 5) {
				stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
				stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
				stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
		    	
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i),rs.getObject(i).toString().toUpperCase());

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	//award
	public List<Map<String, Object>> View_JCOAWARD_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String comm_stats = "";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			if(comm_status == 1  || comm_status == 5) {
				comm_stats = "(?,?) ";
			}
			if(comm_status == 4) {
				comm_stats = "(?) ";
			}
			
			if(!roleSusNo.equals("")) {
				qry+=" and p.unit_sus_no = ?";
			}
			if(!jco_id.equals("")) {
				qry += " and t_awm.jco_id=? ";
			}
	        q="select distinct COALESCE(td.award_cat,'') as medal_type,\r\n" + 
	        		"	        		COALESCE(m.medal_name,'') as award_medal,\r\n" + 
	        		"	        		COALESCE(TO_CHAR(t_awm.date_of_award ,'DD-MON-YYYY'),'') as date_of_award,\r\n" + 
	        		"	        		COALESCE(t_awm.unit,'') as unit \r\n" + 
	        		"	        	FROM tb_psg_census_jco_or_p p	\r\n" + 
	        		"				left JOIN tb_psg_update_census_family_details_jco t_f ON p.id =t_f.jco_id and p.status in " + comm_stats +
	        		"                left join tb_psg_census_awardsnmedal_jco t_awm on t_awm.jco_id = p.id\r\n" + 
	        		"	        		left join tb_psg_mstr_award_category td on cast(td.id as character varying)= t_awm.category_8 and td.status='active'\r\n" + 
	        		"	        		left join tb_psg_mstr_medal m on cast(m.id as character varying) = t_awm.select_desc and m.status='active'\r\n" + 
	        		"	        		where t_awm.status= 1 	" +qry ;
	        	
		    stmt=conn.prepareStatement(q);   	
		    int j =1;
		    if(comm_status == 1  || comm_status == 5) {
			 	stmt.setInt(j, Integer.parseInt("1"));
				j += 1;
			 	stmt.setInt(j, Integer.parseInt("5"));
				j += 1;
			}
			if(comm_status == 4) {
			 	stmt.setInt(j, Integer.parseInt("4"));
				j += 1;
			}
		    if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if( !jco_id.equals("")) {
				stmt.setInt(j, Integer.parseInt(jco_id));
				j += 1;	
			} 
			
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 for (int i = 1; i <= columnCount; i++) {
			 	    	if(rs.getObject(i)==null)
							columns.put(metaData.getColumnLabel(i), "");
						else
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
			 
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
	
	//regimental
	 public List<Map<String, Object>> JCORegimental_View_UpadteByid(int id,String jco_id,String roleSusNo) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(!jco_id.equals("")) {
					qry += " op.jco_id=? ";
				}
		           //260194
		         q= "select orb.unit_name,appt.rank,appt.appt,appt.dt as dt,l.code_value as place,l1.label as unit_loc_type,fv.unit_name as command\r\n" + 
		         		"from (select distinct op.to_sus_no as sus_no,'' as appt,\r\n" + 
		         		"COALESCE(ra1.rank,'')  as rank, TO_CHAR(rk_main.date_of_rank,'DD-MON-YYYY') as dt,rk_main.date_of_rank from \r\n" + 
		         		"tb_psg_census_jco_or_p c \r\n" + 
		         		"inner join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status not in ('0','3')  and op.status=1\r\n" + 
		         		"inner join\r\n" + 
		         		"tb_psg_change_of_rank_jco rk_main on c.id=rk_main.jco_id and rk_main.status != '-1'\r\n" + 
		         		"inner join tb_psg_mstr_rank_jco ra1 on   rk_main.rank = ra1.id \r\n" + 
		         		"and op.dt_of_tos <= rk_main.date_of_rank and  \r\n" + 
		         		"rk_main.date_of_rank  >=  c.enroll_dt\r\n" + 
		         		"AND (op.tenure_date is null OR op.tenure_date >=  rk_main.date_of_rank)\r\n" + 
		         		"left join t_domain_value d on rk_main.rank_type::character varying = d.codevalue and d.domainid='OFFR_RANK_TYPE'\r\n" + 
		         		"where"  + qry +"\r\n" + 
	
		         		"UNION ALL\r\n" + 
		         		"select distinct op.to_sus_no as sus_no,COALESCE(ra2.appointment,'')  as appt,'' as rank , TO_CHAR(app_main.date_of_appointment,'DD-MON-YYYY')  as dt,app_main.date_of_appointment as date_of_rank from \r\n" + 
		         		"tb_psg_census_jco_or_p c \r\n" + 
		         		"inner join tb_psg_posting_in_out_jco op on c.id=op.jco_id and c.status not in ('0','3')  and op.status=1\r\n" + 
		         		"inner join\r\n" + 
		         		"tb_psg_change_of_appointment_jco app_main on c.id=app_main.jco_id and app_main.status !='-1' \r\n" + 
		         		"inner join tb_psg_mstr_appt_jco ra2 on  ra2.id = app_main.appointment \r\n" + 
		         		"and op.dt_of_tos <= app_main.date_of_appointment   AND  \r\n" + 
		         		"app_main.date_of_appointment  >= c.enroll_dt \r\n" + 
		         		"AND (op.tenure_date is null OR op.tenure_date >=  app_main.date_of_appointment)\r\n" + 

		         		"where"  + qry +"    group by 1,2,3,4,5\r\n" + 
		         		"order by date_of_rank) appt\r\n" + 
		         		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = appt.sus_no and status_sus_no='Active'\r\n" + 
		         		"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 
		         		"inner join t_domain_value l1  on l.type_loc=l1.codevalue and  l1.domainid='TYPEOFLOCATION'\r\n" + 
		         		"inner join all_fmn_view fv  on orb.sus_no = appt.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
		         		"left join tb_miso_orbat_unt_dtl orb1 on orb1.sus_no = appt.sus_no and orb1.status_sus_no='Active' order by date_of_rank" + 
		         		"";
		        		
			    stmt=conn.prepareStatement(q);   	
			    int j =1;
 
			    if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				    int sum=0;
				    String c_appt = "";
				    String c_rank="";
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					   for (int i = 1; i <= columnCount; i++) {
				 	    	
				 	    	if(i == 2)
				 	    	{
				 	    
					 	    	if(rs.getObject(i) !=null && !rs.getObject(i).equals(""))
					 	    	{
					 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
					 	    		c_appt =String.valueOf( rs.getObject(i)).toUpperCase(); 
					 	    	}
					 	    	else
					 	    	{
					 	    		columns.put(metaData.getColumnLabel(i), c_appt);
					 	    	}
				 			
				 	    	}
				 	    	if(i == 3)
				 	    	{
				 	    
				 	    		if(rs.getObject(i) !=null && !rs.getObject(i).equals(""))
					 	    	{
					 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
					 	    		c_rank =String.valueOf( rs.getObject(i)).toUpperCase(); 
					 	    	}
					 	    	else
					 	    	{
					 	    		columns.put(metaData.getColumnLabel(i), c_rank);
					 	    	}
				 			
				 	    	}
				 	    	if(i != 2 && i !=3)
				 	    	{

				 	    		columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());
				 	    	}
				 	    }
			 	  
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}

	 public List<Map<String, Object>> JCOBA_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pq.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pq.id=? ";
				}
				//sana
				q="select ec.classification_of_casuality,ec.nature_of_casuality,ec.name_of_operation,ec.date_of_casuality,ec.cause_of_casuality,ec.exact_place from( select  pc.id,\r\n" + 
		        		"					\r\n" + 
		        		"					tdc.label as classification_of_casuality,\r\n" + 
		        		"					td.label as nature_of_casuality, \r\n" + 
		        		"					pc.name_of_operation,\r\n" + 
		        		"					TO_CHAR(pc.date_of_casuality ,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
		        		"					pc.exact_place,\r\n" + 
		        		"				   c1.casuality1 as cause_of_casuality\r\n" + 
		        		"					\r\n" + 
		        		"					from tb_psg_census_battle_physical_casuality_jco pc\r\n" + 
		        		"			\r\n" + 
		        		"					left join t_domain_value td on td.codevalue=pc.nature_of_casuality  and td.domainid='NATURE_OF_CASUALITY'\r\n" + 
		        		"					left join tb_psg_mstr_country con on con.id=cast(pc.country as integer)\r\n" + 
		        		"					left join tb_psg_mstr_state st on st.state_id=cast(pc.state as integer) \r\n" + 
		        		"					left join tb_psg_mstr_district dis on dis.district_id=cast(pc.district as integer) \r\n" + 
		        		"					left join tb_psg_mstr_city te on te.city_id=cast(pc.tehsil as integer)\r\n" + 
		        		"					left join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" + 
		        		 "                  left join tb_psg_mstr_casuality1 c1 on c1.id = cast(pc.cause_of_casuality_1 as integer) \r\n" + 
		        		
		        		"					left join tb_psg_census_jco_or_p pq on pq.id=pc.jco_id "+
		        		"					WHERE pq.status in " +comm_stats + qry + 
		        				 " order by id desc ) ec" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			  
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
				 	//stmt.setString(j, "1");
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
				 	//stmt.setString(j, "5");
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
				 //	stmt.setString(j, "4");
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
	 
	 //firing
	 public List<Map<String, Object>> FS_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}
				
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and fs.jco_id=? ";
				}
				//HET
		        q="select cd.firing_grade,cd.firing_event_qe,cd.yearf from(select distinct \r\n" + 
		        		"case when upper(fr.firing_result)='OTHERS' or  upper(fr.firing_result)='OTHER' then  COALESCE(fs.ot_firing_grade,'')\r\n" + 
		        		"  else COALESCE(fr.firing_result,'') end as firing_grade , \r\n" +
		        		"COALESCE(di.bpet_qt,'') as firing_event_qe,fs.id,\r\n" + 
		        		"COALESCE(fs.year,'0') as yearf \r\n" + 
		        		"from tb_psg_census_jco_or_p  pcl\r\n" + 
		        		"left join tb_psg_census_firing_standard_jco  fs on fs.jco_id= pcl.id\r\n" + 
		        		"left join tb_psg_mstr_firing_result  fr on cast(fr.id as character varying)= fs.firing_grade \r\n" + 
		        		"left join tb_psg_mstr_bpet_qt di on cast(di.id as character varying)=fs.firing_event_qe  \r\n" + 
		        		"where pcl.status in "+comm_stats + "and  fs.status = 1  " +qry +" order by fs.id asc) cd" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				
				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			    	
			    ResultSet rs = stmt.executeQuery();    
			    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		//BPET
		public List<Map<String, Object>> BA_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and bp.jco_id=? ";
				}
		        q="select \r\n" + 
		        		"case when upper(fr.bpet_result)='OTHERS' or  upper(fr.bpet_result)='OTHER' then  COALESCE(bp.bept_result_others,'')\r\n" + 
		        		"  else COALESCE(fr.bpet_result,'') end as bpet_result , \r\n" + 
		        		"COALESCE(di.bpet_qt,'') as bpet_qt,\r\n" + 
		        		"COALESCE(bp.year,'0') as yearb \r\n" + 
		        		"from tb_psg_census_jco_or_p pcl\r\n" + 
		        		"left join tb_psg_census_bpet_jco bp  on bp.jco_id= pcl.id\r\n" + 
		        		"left join tb_psg_mstr_bpet_result  fr on cast(fr.id as character varying)= bp.bpet_result \r\n" + 
		        		"left join tb_psg_mstr_bpet_qt di on cast(di.id as character varying)=bp.bpet_qe \r\n" + 
		        		"where pcl.status in "+comm_stats +"  and  bp.status = 1 " +qry ;
		        		
			    stmt=conn.prepareStatement(q);  
			    
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
			    ResultSet rs = stmt.executeQuery();    
			  
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		//foreign lang
		
		public List<Map<String, Object>> F_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and fc.jco_id=? ";
				}
				q="select \r\n" + 
						"case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(fc.other_country,'')\r\n" + 
		        		"  else COALESCE(co.name,'') end as name , \r\n" +
		        		"case when upper(td.visit_purpose)='OTHERS' or  upper(td.visit_purpose)='OTHER' then  COALESCE(fc.other_purpose_visit,'')\r\n" + 
		        		"  else COALESCE(td.visit_purpose,'') end as purpose_visit , \r\n" +
						 " TO_CHAR(fc.from_dt ,'DD-MON-YYYY') as from_dt,\r\n" + 
						"TO_CHAR(fc.to_dt ,'DD-MON-YYYY') as  to_dt,fc.period \r\n" + 
						"from tb_psg_census_jco_or_p pcl\r\n" + 
						"left join tb_psg_census_foreign_country_jco fc on fc.jco_id= pcl.id \r\n" + 
						"left join tb_psg_foreign_country co on co.id=fc.country  				\r\n" + 
						"left join tb_psg_mstr_purposeof_visit td on td.id::character varying=cast(fc.purpose_visit as character varying) \r\n" + 
						" and pcl.status in "+comm_stats +"  where fc.status= 1 \r\n" + 
						"" +qry ;
		        		
			    stmt=conn.prepareStatement(q);   	
			    int j =1;
				
				if(comm_status == 1  || comm_status == 5) {
					 	stmt.setInt(j, Integer.parseInt("1"));
						j += 1;
					 	stmt.setInt(j, Integer.parseInt("5"));
						j += 1;
					}
					if(comm_status == 4) {
						stmt.setInt(j, Integer.parseInt("4"));
						j += 1;
					}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			    ResultSet rs = stmt.executeQuery();    
			
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		//address strat
		public List<Map<String, Object>> JCOORM_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		    
		        
		        q="  select  \r\n" + 
		        		"case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(pcl.country_other,'')\r\n" + 
		        		"					else COALESCE(co.name,'') end as ori_country ,\r\n" + 
		        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(pcl.state_other,'')\r\n" + 
		        		"					else COALESCE(st.state_name,'') end as ori_state ,\r\n" + 
		        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(pcl.district_other,'')\r\n" + 
		        		"					else COALESCE(di.district_name,'') end as ori_dis " + 
		        		"					 from  tb_psg_census_jco_or_p  pcl \r\n" + 
		        		"	        		\r\n" + 
		        		"	        		left join tb_psg_mstr_country co on co.id= pcl.country_of_birth and co.status = 'active'\r\n" + 
		        		"	        		left join tb_psg_mstr_state st on st.state_id = pcl.state_of_birth and st.status = 'active'\r\n" + 
		        		"	        		left join tb_psg_mstr_district di on di.district_id = pcl.district_of_birth and di.status='active' \r\n" + 
		        		"	        		--left join tb_psg_mstr_city th on th.city_id = pcl.org_tehsil and th.status = 'active' \r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
	   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> JCOPDO_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		    
		        
		        q="select \r\n" + 
		        		"	case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(ca.pre_country_other,'')\r\n" + 
		        		"					else COALESCE(co.name,'') end as pre_country ,\r\n" + 
		        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(ca.pre_domicile_state_other,'')\r\n" + 
		        		"					else COALESCE(st.state_name,'') end as pre_state ,\r\n" + 
		        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(ca.pre_domicile_district_other,'')\r\n" + 
		        		"					else COALESCE(di.district_name,'') end as pre_dis ,\r\n" + 
		        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(ca.pre_domicile_tesil_other,'')\r\n" + 
		        		"					else COALESCE(th.city_name,'') end as pre_teh " + 
		        		"	        		from  tb_psg_census_jco_or_p pcl \r\n" + 
		        		"	        			        		left join tb_psg_census_address_jco ca  on pcl.id=ca.jco_id and ca.status=1 \r\n" + 
		        		"	        			        		left join tb_psg_mstr_country co on co.id= ca.pre_country and co.status = 'active'\r\n" + 
		        		"	        			        		left join tb_psg_mstr_state st on st.state_id = ca.pre_state and st.status = 'active'\r\n" + 
		        		"	        			        		left join tb_psg_mstr_district di on di.district_id = ca.pre_district and di.status='active'\r\n" + 
		        		"	        			        		left join tb_psg_mstr_city th on th.city_id = ca.pre_tesil and th.status = 'active' \r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
	   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			 
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> JCOPM_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		    
		        
		        q="select\r\n" + 
		        		" case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(add.per_home_addr_country_other,'')\r\n" + 
		        		"					else COALESCE(co.name,'') end as pm_country ,\r\n" + 
		        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(add.per_home_addr_state_other,'')\r\n" + 
		        		"					else COALESCE(st.state_name,'') end as pm_state ,\r\n" + 
		        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(add.per_home_addr_district_other,'')\r\n" + 
		        		"					else COALESCE(di.district_name,'') end as pm_dis ,\r\n" + 
		        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(add.per_home_addr_tehsil_other,'')\r\n" + 
		        		"					else COALESCE(th.city_name,'') end as pm_teh ," + 
		        		"	        		COALESCE(add.permanent_village,'') as permanent_village,\r\n" + 
		        		"	        		COALESCE(add.permanent_pin_code,'0') as permanent_pin_code,\r\n" + 
		        		"	        		COALESCE(add.permanent_near_railway_station,'') as permanent_near_railway_station,\r\n" + 
		        		"	        		COALESCE(add.permanent_border_area,'') as permanent_border_area \r\n" + 
		        		"	        		from tb_psg_census_jco_or_p pcl       	\r\n" + 
		        		"	        		left join tb_psg_census_address_jco add  on pcl.id=add.jco_id and add.status=1 \r\n" + 
		        		"	        		left join tb_psg_mstr_country co on co.id=add.permanent_country and co.status = 'active'\r\n" + 
		        		"	        		left join tb_psg_mstr_state st on st.state_id = add.permanent_state and st.status = 'active' \r\n" + 
		        		"	        		left join tb_psg_mstr_district di on di.district_id = add.permanent_district and di.status='active'\r\n" + 
		        		"	        		left join tb_psg_mstr_city th on th.city_id= add.permanent_tehsil and th.status = 'active' \r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
	   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> JCOPS_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		        
		        q="select "
		        		+ " case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(add.pers_addr_country_other,'')\r\n" + 
		        		"					else COALESCE(co.name,'') end as ps_country ,\r\n" + 
		        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(add.pers_addr_state_other,'')\r\n" + 
		        		"					else COALESCE(st.state_name,'') end as ps_state ,\r\n" + 
		        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(add.pers_addr_district_other,'')\r\n" + 
		        		"					else COALESCE(di.district_name,'') end as ps_dis ,\r\n" + 
		        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(add.pers_addr_tehsil_other,'')\r\n" + 
		        		"					else COALESCE(th.city_name,'') end as ps_teh ,	" + 
		        		"	        		COALESCE(add.present_village,'') as permanent_village, \r\n" + 
		        		"	        		COALESCE(add.present_pin_code,'0') as present_pin_code, \r\n" + 
		        		"	        		COALESCE(add.present_near_railway_station,'') as present_near_railway_station\r\n" + 
		        		"	        		from tb_psg_census_jco_or_p pcl \r\n" + 
		        		"	        		left join tb_psg_census_address_jco add on pcl.id=add.jco_id and add.status=1 \r\n" + 
		        		"	        		left join tb_psg_mstr_country co on co.id=add.present_country and co.status = 'active'\r\n" + 
		        		"	        		left join tb_psg_mstr_state st on st.state_id = add.present_state and st.status = 'active'  \r\n" + 
		        		"	        		left join tb_psg_mstr_district di on di.district_id = add.present_district and di.status='active' \r\n" + 
		        		"	        		left join tb_psg_mstr_city th on th.city_id = add.present_tehsil and th.status = 'active' \r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
	   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			  
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i),rs.getObject(i).toString().toUpperCase());

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> JCONOK_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		        
		        
		        q=" select distinct COALESCE(nok.nok_name ,'') as nok_name,pcl.id,\r\n" + 
		        		" case when upper(rel.relation_name)='OTHERS' or  upper(rel.relation_name)='OTHER' then  COALESCE(nok.relation_other,'')\r\n" + 
		        		"					else COALESCE(rel.relation_name,'') end as relation_name \r\n" + 
		        		"	        		from  tb_psg_census_jco_or_p  pcl\r\n" + 
		        		"	        		left join tb_psg_census_nok_jco nok  on pcl.id = nok.jco_id and nok.status=1 \r\n" + 
		        		"	        		left join tb_psg_mstr_relation rel on rel.id=nok.nok_relation and rel.status='active' \r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
		   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			  
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		
		public List<Map<String, Object>> JCONA_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		        
		        
		        
		        q="select"
		        		+ " case when upper(co.name)='OTHERS' or  upper(co.name)='OTHER' then  COALESCE(nok.ctry_other,'')\r\n" + 
		        		"					else COALESCE(co.name,'') end as nok_country ,\r\n" + 
		        		"					case when upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER' then  COALESCE(nok.st_other,'')\r\n" + 
		        		"					else COALESCE(st.state_name,'') end as nok_state ,\r\n" + 
		        		"					case when upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER' then  COALESCE(nok.dist_other,'')\r\n" + 
		        		"					else COALESCE(di.district_name,'') end as nok_dis ,\r\n" + 
		        		"					case when upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER' then  COALESCE(nok.tehsil_other,'')\r\n" + 
		        		"					else COALESCE(th.city_name,'') end as nok_teh ," + 
		        		"	        		COALESCE(nok.nok_village,'') as nok_village,\r\n" + 
		        		"	        		COALESCE(nok.nok_pin,'0') as nok_pin,\r\n" + 
		        		"	        		COALESCE(nok.nok_near_railway_station,'') as nok_near_railway_station, \r\n" + 
		        		"	        		COALESCE(CAST(PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version')) AS decimal(10,0)) ,'0') as nok_mobile_no \r\n" + 
		        		"	        		from tb_psg_census_jco_or_p  pcl \r\n" + 
		        		"	        		left join tb_psg_census_nok_jco nok on pcl.id = nok.jco_id and nok.status=1 \r\n" + 
		        		"	        		left join tb_psg_mstr_country co on co.id=nok.nok_country and co.status = 'active'\r\n" + 
		        		"	        		left join tb_psg_mstr_state st on st.state_id = nok.nok_state and st.status = 'active' \r\n" + 
		        		"	        		left join tb_psg_mstr_district di on di.district_id = nok.nok_district and di.status='active'\r\n" + 
		        		"	        		left join tb_psg_mstr_city th on th.city_id = nok.nok_tehsil and th.status = 'active'        		        		\r\n" + 
		        		"					WHERE pcl.status in " +comm_stats + qry + 
		   				 " order by pcl.id desc" ;
		        		
			    stmt=conn.prepareStatement(q);   	
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString().toUpperCase());

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> AR_View_JCOByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id=? ";
				}
		        
				  q="select \r\n" + 
			        		"COALESCE(CAST(PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version')) AS decimal(10,0)),'0') as nok_mobile_no , \r\n" + 
			        		"case when (upper(co.name)='OTHERS' or  upper(co.name)='OTHER') then COALESCE(add.per_home_addr_country_other,'')\r\n" + 
			        		"else COALESCE(co.name,'')\r\n" + 
			        		"end as ar_country,\r\n" + 
			        		"case when (upper(st.state_name)='OTHERS' or  upper(st.state_name)='OTHER') then COALESCE(add.per_home_addr_state_other,'')\r\n" + 
			        		"else COALESCE(st.state_name,'')\r\n" + 
			        		"end as ar_state,\r\n" + 
			        		"case when (upper(di.district_name)='OTHERS' or  upper(di.district_name)='OTHER') then COALESCE(add.per_home_addr_district_other,'')\r\n" + 
			        		"else COALESCE(di.district_name,'')\r\n" + 
			        		"end as ar_dis,\r\n" + 
			        		"case when (upper(th.city_name)='OTHERS' or  upper(th.city_name)='OTHER') then COALESCE(add.per_home_addr_tehsil_other,'')\r\n" + 
			        		"else COALESCE(th.city_name,'')\r\n" + 
			        		"end as ar_teh,\r\n" + 
			        		"COALESCE(add.permanent_village,'') as permanent_village,\r\n" + 
			        		"COALESCE(add.permanent_pin_code,'0') as permanent_pin_code,\r\n" + 
			        		"COALESCE(add.permanent_near_railway_station,'') as permanent_near_railway_station,\r\n" + 
			        		"COALESCE(add.permanent_border_area,'') as permanent_border_area\r\n" + 
			        		"from tb_psg_census_jco_or_p  pcl \r\n" + 
			        		"left join tb_psg_census_nok_jco nok on pcl.id = nok.jco_id and nok.status=1  \r\n" + 
			        		"left join tb_psg_census_address_jco add on pcl.id=add.jco_id and add.status=1 \r\n" + 
			        		"left join tb_psg_mstr_country co on co.id=add.permanent_country and co.status = 'active' \r\n" + 
			        		"left join tb_psg_mstr_state st on st.state_id = add.permanent_state and st.status = 'active'\r\n" + 
			        		"left join tb_psg_mstr_district di on di.district_id = add.permanent_district and di.status='active'  \r\n" + 
			        		"left join tb_psg_mstr_city th on th.city_id = add.permanent_tehsil and th.status = 'active' " + 
			        		"					WHERE pcl.status in " +comm_stats + qry + 
			   				 " order by pcl.id desc" ;
			        		
		        		
			    stmt=conn.prepareStatement(q);   	
			 
			    int j =1;
			    if(comm_status == 1  || comm_status == 5) {
			    	stmt.setInt(j, Integer.parseInt("1"));
					j += 1;
					stmt.setInt(j, Integer.parseInt("5"));
					j += 1;
				}
				if(comm_status == 4) {
					stmt.setInt(j, Integer.parseInt("4"));
					j += 1;
				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
			   
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				 
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		public List<Map<String, Object>> JCORD_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
//				IF(COMM_STATUS == 1  || COMM_STATUS == 5) {
//					COMM_STATS = "(?,?) ";
//				}
//				IF(COMM_STATUS == 4) {
//					COMM_STATS = "(?) ";
//				}	
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and ne.jco_id= ? ";
				}
		        
		        
				 q="select a.causes_name ,a.date_of_non_effective, COALESCE(b.date_of_non_effective,'') as date_of_release,COALESCE(b.causes_name,'') as cause_of_release \r\n" + 
			        		"from (select mne.causes_name,ne.jco_id,\r\n" + 
			        		"COALESCE(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'') as  date_of_non_effective\r\n" + 
			        		"		        		from tb_psg_census_jco_or_p pcl\r\n" + 
			        		"		        		inner join tb_psg_non_effective_jco ne on ne.jco_id=pcl.id and ne.status in ('1','2')\r\n" + 
			        		"		        		inner join tb_psg_mstr_cause_of_non_effective_jco mne on ne.cause_of_non_effective=mne.id::text and  mne.type_of_officer=1        		\r\n" + 
			        		"						where pcl.status in ('1','4') "+qry+"  order by ne.id limit 1) a\r\n" + 
			        		"left join 			\r\n" + 
			        		"(select mne.causes_name,ne.jco_id,\r\n" + 
			        		"COALESCE(TO_CHAR(ne.date_of_non_effective ,'DD-MON-YYYY'),'') as  date_of_non_effective\r\n" + 
			        		"		        		from tb_psg_census_jco_or_p pcl\r\n" + 
			        		"		        		inner join tb_psg_non_effective_jco ne on ne.jco_id=pcl.id and ne.status=1\r\n" + 
			        		"		        		inner join tb_psg_mstr_cause_of_non_effective_jco mne on ne.cause_of_non_effective=mne.id::text --and  mne.type_of_officer=2    \r\n" + 
			        		" 						inner join tb_psg_re_call_jco ren on ne.jco_id=ren.jco_id and ren.status=1\r\n" + 
			        		"						where pcl.status in ('4') "+qry+" order by ne.id desc limit 1	)	b on a.jco_id=b.jco_id";
			        		
		        		
			    stmt=conn.prepareStatement(q);   	
			  
			    int j =1;
//			    if(comm_status == 1  || comm_status == 5) {
//			    	stmt.setInt(j, Integer.parseInt("1"));
//					j += 1;
//					stmt.setInt(j, Integer.parseInt("5"));
//					j += 1;
//				}
//				if(comm_status == 4) {
//					stmt.setInt(j, Integer.parseInt("4"));
//					j += 1;
//				}
			    if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if( !jco_id.equals("")) {
					stmt.setInt(j, Integer.parseInt(jco_id));
					j += 1;	
				} 
				 if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}

					if( !jco_id.equals("")) {
						stmt.setInt(j, Integer.parseInt(jco_id));
						j += 1;	
					}
				
			    ResultSet rs = stmt.executeQuery();    
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				     
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					 for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
			 	    list.add(columns);
			 	 }
				      
				 rs.close();
				 stmt.close();
				 conn.close();
				 
		     }catch (SQLException e){
		    	 e.printStackTrace();
			 }finally{
				 if(conn != null){
					 try{
						 conn.close();
					 }catch (SQLException e){
					 }
				 }
			 }
			 return list;
			}
		
		
		public List<Map<String, Object>> View_JCOAttachment_View_UpadteByid(int id,String jco_id,String roleSusNo,int comm_status){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			String comm_stats = "";
			try{	  
				conn = dataSource.getConnection();					
				PreparedStatement stmt =null;
				if(comm_status == 1  || comm_status == 5) {
					comm_stats = "(?,?) ";
				}
				if(comm_status == 4) {
					comm_stats = "(?) ";
				}
				
				if(!roleSusNo.equals("")) {
					qry+=" and pcl.unit_sus_no = ?";
				}
				if(!jco_id.equals("")) {
					qry += " and pcl.id =? ";
				} 
				
				  q=" select att_movement_number,att_place,att_duration,att_reasons,TO_CHAR(at.att_date_of_move,'DD-MON-YYYY') AS att_date_of_move \r\n" +  
			        		"  from tb_psg_census_jco_or_p pcl \r\n" + 
			        		" left join tb_psg_census_attachment_details_jco at on pcl.id=at.jco_id  where  pcl.status in  " + comm_stats + qry; 
				    stmt=conn.prepareStatement(q);   	
				   
				    int j =1;
				    if(comm_status == 1  || comm_status == 5) {
					 	  stmt.setInt(j, Integer.parseInt("1"));
						j += 1;
						  stmt.setInt(j, Integer.parseInt("5"));
						j += 1;
					}
					if(comm_status == 4) {
						  stmt.setInt(j, Integer.parseInt("4"));
						j += 1;
					}
				    if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
				    if( !jco_id.equals("")) {
						stmt.setInt(j, Integer.parseInt(jco_id));
						j += 1;	
					} 
					 
				    ResultSet rs = stmt.executeQuery();    
				    
					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();
					     
					while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
				 	    for (int i = 1; i <= columnCount; i++) {
				 	    	if(rs.getObject(i)==null)
								columns.put(metaData.getColumnLabel(i), "");
							else
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				 	    }
				 	    list.add(columns);
				 	 }
					      
					 rs.close();
					 stmt.close();
					 conn.close();
					 
			     }catch (SQLException e){
			    	 e.printStackTrace();
				 }finally{
					 if(conn != null){
						 try{
							 conn.close();
						 }catch (SQLException e){
						 }
					 }
				 }
				 return list;
		}

		public long GetSearch_JCOrecordCountlist(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_name,String unit_sus_no,String army_no,String full_name,
				String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType) {
			
			String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_name, unit_sus_no, army_no,
					full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA,roleSusNo,roleType); 
				int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					
				
					 q="select count(app.*) from(    select distinct \r\n" + 
	                           "                        tp.id , \r\n" + 
	                           "                        tp.army_no,\r\n" + 
	                           "                        r.rank as rank,\r\n" + 
	                           "                        tp.full_name,tp.unit_sus_no,\r\n" + 
	                           "                    TO_CHAR(tp.date_of_birth   ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
	                           "                     arm.arm_desc as parent_arm, \r\n" + 
	                           "                        tp.status,\r\n" + 
	                           "                        mc.course_name,tp.id as jco_id,tp.marital_status,tp.modified_date ,u.unit_name,tp.status as comm_status \r\n" + 
	                           "                        FROM tb_psg_census_jco_or_p tp   \r\n" + 
	                           "                    inner join tb_psg_mstr_rank_jco r on r.id = tp.rank   \r\n" + 
	                           "                        inner join tb_miso_orbat_arm_code   arm on arm.arm_code = tp.arm_service \r\n" + 
	                           "                        inner join tb_miso_orbat_unt_dtl u on u.sus_no =tp.unit_sus_no and u.status_sus_no in ('Active','Inactive')\r\n" + 
	                           "                        left join tb_psg_mstr_course mc on mc.id=tp.id   \r\n" + 
	                           "                         join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" + 
	                           "             " +SearchValue+") app " ;
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_name, unit_sus_no, army_no,
							full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA, roleSusNo, roleType);
			
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
				  stmt,String Search,String unit_name,String unit_sus_no,String army_no,String full_name,
					String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType) {
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
					
					
					
				}

				
				
				
				
				if(!unit_sus_no.equals("")) {
					flag += 1;
					
					stmt.setString(flag, unit_sus_no);
					
				}
				if(!army_no.equals("")) {
					
					flag += 1;
					stmt.setString(flag, army_no);
				}
				if(!full_name.equals("")) {
					flag += 1;
					stmt.setString(flag, "%"+full_name.toUpperCase()+"%");
				}
				if(!rank.equals("0")) {
					flag += 1;
					stmt.setString(flag, rank);
				}
				if(!year_of_comm.equals("")) {
					flag += 1;
					stmt.setString(flag, year_of_comm);
				}
				if(!year_of_dob.equals("")) {
					flag += 1;
					stmt.setString(flag, year_of_dob);
				}
				if(!parent_arm.equals("0")) {
					flag += 1;
					stmt.setString(flag, parent_arm);
				}
				
	        
				  
				
			}catch (Exception e) {}
		
			return stmt;
			
		}

		 public String GenerateQueryWhereClause_SQL(String Search,String unit_name,String unit_sus_no,String army_no,String full_name,
					String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" where  ";
					SearchValue +="( upper(tp.army_no) like ? or upper(r.rank) like ? or upper(tp.full_name) like ? "
							+ " or upper(u.unit_name) like ?  "
							+ "  ) ";
				}
				
				
					
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and tp.unit_sus_no = ? ";	
					}
					else {
						SearchValue += " where tp.unit_sus_no = ? ";
					}
				}
				
				if( !army_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and tp.army_no = ? ";	
					}
					else {
						SearchValue += " where tp.army_no = ? ";
					}
				}
				
				if( !full_name.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and upper(tp.full_name) like ? ";	
					}
					else {
						SearchValue += " where  upper(tp.full_name) like ? ";
					}
				}
				if( !rank.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and tp.rank::text= ? ";	
					}
					else {
						SearchValue += " where  tp.rank::text= ? ";
					}
				}
				if( !year_of_comm.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  date_part('year',enroll_dt)::text= ?  ";	
					}
					else {
						SearchValue += " where    date_part('year',enroll_dt)::text= ?  ";
					}
				}
				
				if( !year_of_dob.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  date_part('year',date_of_birth)::text= ?  ";	
					}
					else {
						SearchValue += " where    date_part('year',date_of_birth)::text= ?  ";
					}
				}
				if( !parent_arm.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  tp.arm_service= ?  ";	
					}
					else {
						SearchValue += " where  tp.arm_service= ?  ";
					}
				}
				
				
				
				if(statusA.equals("1") || statusA.equals("5") ) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  tp.status in ('1','5')  ";	
					}
					else {
						SearchValue += " where  tp.status in ('1','5') ";
					}
				}
				
				if(statusA.equals("4")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  tp.status ='4'  ";	
					}
					else {
						SearchValue += " where  tp.status ='4' ";
					}
				}
				
				
				return SearchValue;
			}
		 
		 
		 
		 public List<Map<String, Object>> GetSearch_JCOrecorddata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String unit_name,String unit_sus_no,String army_no,String full_name,
					String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA,String roleSusNo, String roleType) 
			{
				

				
				
		    	String SearchValue = GenerateQueryWhereClause_SQL(Search,unit_name, unit_sus_no, army_no,
						full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA, roleSusNo, roleType);
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
					
				
					q="    select distinct \r\n" + 
                            "                        tp.id , \r\n" + 
                            "                        tp.army_no,\r\n" + 
                            "                        r.rank as rank,\r\n" + 
                            "                        tp.full_name,tp.unit_sus_no,\r\n" + 
                            "                    TO_CHAR(tp.date_of_birth   ,'DD-MON-YYYY') as date_of_birth,\r\n" + 
                            "                     arm.arm_desc as parent_arm, \r\n" + 
                            "                        tp.status,\r\n" + 
                            "                        mc.course_name,tp.id as jco_id,tp.marital_status,tp.modified_date ,u.unit_name,tp.status as comm_status \r\n" + 
                            "                        FROM tb_psg_census_jco_or_p tp   \r\n" + 
                            "                    inner join tb_psg_mstr_rank_jco r on r.id = tp.rank   \r\n" + 
                            "                        inner join tb_miso_orbat_arm_code   arm on arm.arm_code = tp.arm_service \r\n" + 
                            "                        inner join tb_miso_orbat_unt_dtl u on u.sus_no =tp.unit_sus_no and u.status_sus_no in ('Active','Inactive')\r\n" + 
                            "                        left join tb_psg_mstr_course mc on mc.id=tp.id   \r\n" + 
                            "                         join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" 
                            + ""+SearchValue + " order by tp.modified_date desc "   +
                            "     limit " +pageL+" OFFSET "+startPage+"";
					
				
				
					stmt=conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_name, unit_sus_no, army_no,
							full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA,roleSusNo, roleType);
					
			      ResultSet rs = stmt.executeQuery();   
			      
			      ResultSetMetaData metaData = rs.getMetaData();
			      int columnCount = metaData.getColumnCount();
			      
			  	while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String action = "";
					
					
					String f1 = "";
					String View1 = "onclick=\"  {View1Data("+ rs.getString("id") + ",'"+rs.getString("jco_id")+"','"+rs.getString("unit_sus_no")+"','"+rs.getInt("comm_status")+"')}\"";
	                f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					
					columns.put("action", f1); // 5
				  
					//columns.put("action",action);
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

	
}
