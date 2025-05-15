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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Tb_Miso_Orbat_Code;
import com.models.mnh.Tb_Med_Daily_Bed_Occupancy;
import com.persistance.util.HibernateUtil;

public class Daily_Morning_bed_state_ReportDAOImpl implements Daily_Morning_bed_state_ReportDAO {
	
	private DataSource dataSource;
			
			public void setDataSource(DataSource dataSource) {
				this.dataSource = dataSource;
			}
			
			
	
		
						
			public ArrayList<ArrayList<String>> getsearch_morning_bed_Report(String sus1,String unit1,String cmd1,String frm_dt1,String to_dt1) 
	          {
	           
	                 ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String>>();  
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
					                    
					                            col = "dt";
					                    
					                    qry += " and to_char("+col+", 'YYYY-MM-DD') >= ? and to_char("+col+", 'YYYY-MM-DD') <= ?";
					            }
					            
					        
					        /*   q = "select distinct r.id,a.unit_name,b.total_all,r.beds_laid_out,r.total_no_of_patients,r.sus_no\r\n" + 
			                   		"					from tb_med_daily_bed_occupancy r \r\n" + 
			                   		"					left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no \r\n" + 
			                   		"					left join tb_med_authorisation_all b on b.sus_no = r.sus_no \r\n" + 
			                   		"                    left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n" + 
			                   		"                    where a.status_sus_no='Active' and (r.sus_no,r.id) in \r\n" + 
			                   		"					(\r\n" + 
			                   		"					select r1.sus_no,r1.id\r\n" + 
			                   		"					from tb_med_daily_bed_occupancy r1 \r\n" + 
			                   		"					where  r1.sus_no=r.sus_no    group by r1.sus_no,r1.id \r\n" + 
			                   		"					order by id  desc limit 1\r\n" + 
			                   		"					) \r\n" + 
			                   		"					\r\n" + 
			                   		"                    and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)" +qry+ " order by id desc\r\n" ;  
			                 */
					           
					       	// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
					           
					           q = "select distinct r.id,a.unit_name,b.total_all,r.beds_laid_out,r.total_no_of_patients,r.sus_no\r\n" + 
				                   		"					from tb_med_daily_bed_occupancy r \r\n" + 
				                   		"					left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no \r\n" + 
				                   		"					left join tb_med_authorisation_all b on b.sus_no = r.sus_no \r\n" + 
				                   		"                    left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n" + 
				                   		"                    where a.status_sus_no='Active' and (r.sus_no,r.id) in \r\n" + 
				                   		"					(\r\n" + 
				                   		"					select r1.sus_no,r1.id\r\n" + 
				                   		"					from tb_med_daily_bed_occupancy r1 \r\n" + 
				                   		"					where  r1.sus_no=r.sus_no    group by r1.sus_no,r1.id \r\n" + 
				                   		"					order by id  desc limit 1\r\n" + 
				                   		"					) \r\n" + 
				                   		"					\r\n" + 
				                   		"                    " +qry+ " order by id desc\r\n" ;  
				                 
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
					        ResultSet rs = stmt.executeQuery();
					    
						    while(rs.next()){
					     		   
									ArrayList<String> list = new  ArrayList<String>();
									list.add(rs.getString("unit_name"));//0
									list.add(rs.getString("total_all"));//1
									list.add(rs.getString("beds_laid_out"));//2
									list.add(rs.getString("total_no_of_patients"));//3
									
								
										
                                String editData = "onclick=\"  {editData('"+rs.getString("sus_no")+"','"+rs.getString("unit_name")+"','"+rs.getString("total_all")+"','"+rs.getString("beds_laid_out")+"','"
                                +rs.getString("total_no_of_patients")+"')}\"";
 		                        String updateButton ="<i class='fa fa-eye' "+editData+" title='Edit Data'></i>";
										
						 	  			String f = "";
										f += updateButton;
										
						            	
						            	
						    			int sur = 0;
						    			int defi=0;
		     		          
		     		                  
		     		                      
		     		                        int Auth_f = 0;
		     		                        int beds_laid_out = 0;;
		     		                        if(rs.getString("total_all") == null)
		     		                        {
		     		                        	
		     		                        	Auth_f = 0;
		     		                        	
		     		                        }
		     		                        else
		     		                        {
		     		                        	
		     		                        	Auth_f = Integer.parseInt(rs.getString("total_all"));
		     		                        	
		     		                        }
		     		                        
		     		                        if(rs.getString("beds_laid_out") == null)
		     		                        {
		     		                        	beds_laid_out = 0;
		     		                        }
		     		                        else
		     		                        {
		     		                        	beds_laid_out = Integer.parseInt(rs.getString("beds_laid_out"));
		     		                        }
		     		                        
		     		                       
		     		                        if(beds_laid_out > 0 || Auth_f > 0)
		     		                        {
		     		                                
		     		                                int diff = Auth_f - beds_laid_out;
		     		                                if(diff > 0)
		     		                                {
		     		                                	defi = diff;
		     		                                        sur = 0;
		     		                                }
		     		                                else
		     		                                	
		     		                                	if(diff < 0)
		     		                                {
		     		                                	sur = diff;
	     		                                        defi = 0;
		     		                                }
		     		                        }
		     		                       
		     		                       list.add(f);//4
		     		                       list.add(String.valueOf(Math.abs(sur))); //5
		     		                      list.add(String.valueOf((defi))); //6 
		     		                     aList.add(list);
									    
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
									return aList;
							  	}	
		
///////////////////////----------view report morning bed state-------------///////////////////			
		
			public ArrayList<ArrayList<String>> getview_morning_bed_Report(String sus1) 
		          {
		           
		                 ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String>>();  
						    Connection conn = null;
						    String q="";
						    String qry="";
						   
						    try{          
						            conn = dataSource.getConnection();                                        
						            PreparedStatement stmt =null;

						            		
						           /* q = "select distinct r.id,a.unit_name,b.total_all,r.beds_laid_out,r.total_no_of_patients, ltrim(TO_CHAR(r.created_on ,'DD-MM-YYYY'),'0') as created_on \r\n" + 
					                   		"					from tb_med_daily_bed_occupancy r \r\n" + 
					                   		"					left join tb_miso_orbat_unt_dtl a on a.sus_no = r.sus_no \r\n" + 
					                   		"					left join tb_med_authorisation_all b on b.sus_no = r.sus_no \r\n" + 
					                   		"                    left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n" + 
					                   		"                    where a.status_sus_no='Active'\r\n" + 
					                   		"                    and substring(r.sus_no,?,?) in(?,?,?,?,?,?,?,?,?)  and  r.created_on > CURRENT_TIMESTAMP - INTERVAL '31 days' and r.sus_no=? " +qry+ "\r\n" ;  */
						            
						            
						         // Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
						            q = "select distinct r.id,a.unit_name,b.total_all,r.beds_laid_out,r.total_no_of_patients, ltrim(TO_CHAR(r.created_on ,'DD-MM-YYYY'),'0') as created_on \r\n" + 
					                   		"					from tb_med_daily_bed_occupancy r \r\n" + 
					                   		"					left join orbat_all_details_view_mnh a on a.sus_no = r.sus_no \r\n" + 
					                   		"					left join tb_med_authorisation_all b on b.sus_no = r.sus_no \r\n" + 
					                   		"                    left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control \r\n" + 
					                   		"                    where a.status_sus_no='Active'\r\n" + 
					                   		"                    and  r.created_on > CURRENT_TIMESTAMP - INTERVAL '31 days' and r.sus_no=? " +qry+ "\r\n" ;  
					                 
						            stmt=conn.prepareStatement(q);
							            
							        stmt.setInt(1, 1);
							        stmt.setInt(2, 1);
							        stmt.setString(3, "000000000");
							        
							        /*stmt.setInt(4, 1);
							        stmt.setInt(5, 4);
							        stmt.setString(6, "9609");
							        stmt.setString(7, "9709");
							        stmt.setString(8, "3742");
							        stmt.setString(9, "6323");
							        stmt.setString(10, "3755");
							        stmt.setString(11, "3738");
							        stmt.setString(12, "3735");
							        stmt.setString(13, "3747");
							        stmt.setString(14, "1234");
							   */
							    if(sus1 != "") {
						        	stmt.setString(4, sus1);
					            }
						        ResultSet rs = stmt.executeQuery();
							    while(rs.next()){
						     		   
										ArrayList<String> list = new  ArrayList<String>();
										list.add(rs.getString("unit_name"));//0
										list.add(rs.getString("total_all"));//1
										list.add(rs.getString("beds_laid_out"));//2
										list.add(rs.getString("total_no_of_patients"));//3
										
										
									
									
										
	                                String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("unit_name")+"','"+rs.getString("total_all")+"','"+rs.getString("beds_laid_out")+"','"
	                                +rs.getString("total_no_of_patients")+"')}else{ return false;}\"";
	 		                        String updateButton ="<i class='fa fa-eye' "+editData+" title='Edit Data'></i>";
											
							 	  			String f = "";
											f += updateButton;
											
							            	
							            	
							    			int sur = 0;
							    			int defi=0;
							    			
			     		                  
			     		                      
			     		                        int Auth_f = 0;
			     		                        int beds_laid_out = 0;;
			     		                        if(rs.getString("total_all") == null)
			     		                        {
			     		                        	
			     		                        	Auth_f = 0;
			     		                        	
			     		                        }
			     		                        else
			     		                        {
			     		                        	
			     		                        	Auth_f = Integer.parseInt(rs.getString("total_all"));
			     		                        	
			     		                        }
			     		                        
			     		                        if(rs.getString("beds_laid_out") == null)
			     		                        {
			     		                        	beds_laid_out = 0;
			     		                        }
			     		                        else
			     		                        {
			     		                        	beds_laid_out = Integer.parseInt(rs.getString("beds_laid_out"));
			     		                        }
			     		                        
			     		                       
			     		                        if(beds_laid_out > 0 || Auth_f > 0)
			     		                        {
			     		                                
			     		                                int diff = Auth_f - beds_laid_out;
			     		                                if(diff > 0)
			     		                                {
			     		                                	defi = diff;
			     		                                        sur = 0;
			     		                                }
			     		                                else
			     		                                	
			     		                                	if(diff < 0)
			     		                                {
			     		                                	sur = diff;
		     		                                        defi = 0;
			     		                                }
			     		                        }
			     		                       
			     		                       list.add(f);//4
			     		                       list.add(String.valueOf(Math.abs(sur))); //5
			     		                      list.add(String.valueOf((defi))); //6 
			     		                     list.add(rs.getString("created_on"));//7
												
			     		                     aList.add(list);
										    
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
										return aList;
								  	}
		
}
