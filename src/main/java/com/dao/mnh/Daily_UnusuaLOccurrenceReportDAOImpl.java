package com.dao.mnh;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.persistance.util.HibernateUtil;

public class Daily_UnusuaLOccurrenceReportDAOImpl implements Daily_UnusuaLOccurrenceReportDAO {
	private DataSource dataSource;
			
			public void setDataSource(DataSource dataSource) {
				this.dataSource = dataSource;
			}


public List<Map<String, Object>> getsearch_Daily_unusual_Report(String sus1,String unit1,String cmd1,String frm_dt1,String to_dt1, String serv1,String cat1) {
			    
			    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			    Connection conn = null;
			    String q="";
			    String qry="";
			   
			    try{          
			            conn = dataSource.getConnection();                                        
			            PreparedStatement stmt =null;
			            
			            
			           
			            if(!sus1.equals("")){
			                    qry += " and r.sus_no=?";
			            }else {
			                    if(!cmd1.equals("ALL")){
			                            qry += " and substr(a.form_code_control,?,?)=?";
			                     } 
			            }
			            
			           
			            if(!frm_dt1.equals("") && !to_dt1.equals("")){
			                    String col = null;
			                    
			                            col = "dt_report";
			                    
			                    qry += " and to_char("+col+", 'YYYY-MM-DD') >= ? and to_char("+col+", 'YYYY-MM-DD') <= ?";
			            }
			            
			            if(!serv1.equals("ALL")){
			                    qry += " and r.service=?";
			            }
			            
			            if(!cat1.equals("ALL")){
		                    qry += " and r.category=?";
		            }
			            
			            
			  /*  q = "select r.persnl_no,r.rank,r.appointment,r.persnl_name,r.age_year,r.persnl_unit,to_char(r.date_time_incident,'DD-MM-YYYY HH12:MI:SS AM') as date_time_incident,"
								+ "a.unit_name,to_char(r.dt_report,'DD-MM-YYYY HH12:MI:SS AM') "
								+ "as date_time_admission,r.diagnosis,r.remarks from tb_med_unusual_occurrence r "
								+ "left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no "
								+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
								+ "where a.status_sus_no='Active' and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)"+qry;*/	
			            
			         // Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl   
			            q = "select r.persnl_no,r.rank,r.appointment,r.persnl_name,r.age_year,r.persnl_unit,to_char(r.date_time_incident,'DD-MM-YYYY HH12:MI:SS AM') as date_time_incident,"
								+ "a.unit_name,to_char(r.dt_report,'DD-MM-YYYY HH12:MI:SS AM') "
								+ "as date_time_admission,r.diagnosis,r.remarks from tb_med_unusual_occurrence r "
								+ "left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no "
								+ "left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control "
								+ "where a.status_sus_no='Active' "+qry;
			            
			            stmt=conn.prepareStatement(q);
			            
			            stmt.setInt(1, 1);
			        stmt.setInt(2, 1);
			        stmt.setString(3, "000000000");
			       /* stmt.setInt(4, 1);
			        stmt.setInt(5, 4);
			        stmt.setString(6, "9609");
			        stmt.setString(7, "9709");
			        stmt.setString(8, "3742");
			        stmt.setString(9, "6323");
			        stmt.setString(10, "3755");
			        stmt.setString(11, "3738");
			        stmt.setString(12, "3735");
			        stmt.setString(13, "3747");
			        stmt.setString(14, "1234");*/
			   
			        int j = 4;
			        if(!sus1.equals("")){
			                stmt.setString(j, sus1);
			                j++;
			        }else {
			                if(!cmd1.equals("ALL")){
			                        stmt.setInt(j, 1);
			                        j++;
			                        stmt.setInt(j, 1);
			                        j++;
			                        stmt.setString(j, cmd1.substring(0,1));
			                        j++;
			                }
			        }
			     
			       
			        if(!frm_dt1.equals("") && !to_dt1.equals("")){
			                stmt.setString(j, frm_dt1);
			                j++;
			               
			                stmt.setString(j, to_dt1);
			                j++;
			               
			        }
			        
			        if(!serv1.equals("ALL")){
			                stmt.setString(j, serv1);
			                j++;
			        }
			       
			        
			            
			             ResultSet rs = stmt.executeQuery();      
			            ResultSetMetaData metaData = rs.getMetaData();
			            int columnCount = metaData.getColumnCount();
			            while (rs.next()) {
			                    Map<String, Object> columns = new LinkedHashMap<String, Object>();
			                 for(int i = 1; i <= columnCount; i++){
			                         columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			                 }
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

//test by guru
	 public Long checkExitstingBasicData(String sus_no1,String dt_report,int id) {
			String hql="select count(id) from Tb_Med_Unusual_Occurrence where sus_no=:d1 and cast(dt_report as date)=cast(:d3 as date) and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no1);
			q1.setParameter("d3", dt_report);
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 public Long checkExitstingNilData(String sus_no1,String category, String dt_report,int id) {
			String hql="select count(id) from Tb_Med_Unusual_Occurrence where sus_no=:d1 and category=:d2 and cast(dt_report as date)=cast(:d3 as date) and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no1);
			q1.setParameter("d2", category);
			q1.setParameter("d3", dt_report);
			q1.setParameter("id", id);
			Long count_list1 =  (Long) q1.uniqueResult();	
			tx.commit();
			session.close();
			if(count_list1 != null)
			{
				return count_list1;
			}
			else
			{
				return (long) 0;
			}
			
		}
	 
	 
	 
	 //upto

}