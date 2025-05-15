package com.dao.psg;

import java.sql.Connection;
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

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_PSG_UPLOAD;
import com.models.UploadDocumentMVCR;
import com.persistance.util.HibernateUtil;

public class PSGdDAOIMPL implements  PSGDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_psg_upload()
	{
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
 		
		Query q = sessionHQL.createQuery("from TB_PSG_UPLOAD order by id desc");
		
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPLOAD> dlist = (List<TB_PSG_UPLOAD>) q.list();
		tx.commit();
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		for(int i =0 ;i<dlist.size();i++) {
			ArrayList<String> list = new ArrayList<String>();
			/*list.add(dlist.get(i).getBase_wksp_nam);
			list.add(String.valueOf(dlist.get(i).getR4_condition()));*/
			String f = "";
			String f1 = "";
			String f2 = "";
			String f3 = "";
			
			String f4 = "";
			String f5 = "";
			/*String f6= "";
			String f = "";*/
			
			String Update = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_offrs_do_2_iaff_3010')}\"";
			f = "<i class='action_icons action_update'  " + Update + " title='download'></i>";
			String Delete1 = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_str_return_iaff_3008')}\"";
			f1 = "<i class='action_icons action_update'  " + Delete1 + " title='download'></i>";
			
			String Update1 = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_offrs__arrival_rp')}\"";
			f2 = "<i class='action_icons action_update'  " + Update1 + " title='download'></i>";
			String Delete2 = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_jco_do_2')}\"";
			f3 = "<i class='action_icons action_update'  " + Delete2 + " title='download'></i>";
			
			
			String Update3 = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_str_return_iaff_3009_a_b')}\"";
			f4 = "<i class='action_icons action_update'  " + Update3 + " title='download'></i>";
			String Delete3 = "onclick=\"{getDownloadImage("+ dlist.get(i).getId() +",'up_statics_pers_cns')}\"";
			f5 = "<i class='action_icons action_update'  " + Delete3 + " title='download'></i>";
			
			
			list.add(String.valueOf(dlist.get(i).getId()));	
			list.add(String.valueOf(dlist.get(i).getSus_no()));
				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(f4);
				list.add(f5);
			
			alist.add(list);
		}
		sessionHQL.close();
		return alist;    
	}
	
	
	public List<TB_PSG_UPLOAD> getDocumentPSGImg(int id,String file_name, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("Unit")) {
			q = session.createQuery("from TB_PSG_UPLOAD where id=:id and sus_no=:sus_no");
			q.setParameter("id", id);
			q.setParameter("sus_no", roleSusNo);
		} else {
			q = session.createQuery("from TB_PSG_UPLOAD where id=:id");
			q.setParameter("id", id);
		}

		@SuppressWarnings("unchecked")
		List<TB_PSG_UPLOAD> list = (List<TB_PSG_UPLOAD>) q.list();
		
		tx.commit();
		session.close();
		return list;
	}
	
	
	  public DataSet<Map<String, Object>> DatatablesCriteriasPSGreport(DatatablesCriterias criterias, String qry ,String roleSubAccess) { 		    	
		  List<Map<String, Object>> metadata = findDepartmentCriteriasforma1(criterias,qry,roleSubAccess);
			Long countFiltered = getFilteredCountfo(criterias,qry); 
			Long count = getTotalCountfo(qry); //
			return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
		
		}
	  
	  
	/*	@SuppressWarnings("unchecked")
		private List<Map<String, Object>> findDepartmentCriteriasforma(DatatablesCriterias criterias , String qry,String roleSubAccess) {
			StringBuilder queryBuilder = null;
			
			
			if(qry.equals("")){
			 queryBuilder = new StringBuilder("FROM TB_PSG_UPLOAD b ");
			}else {
			 queryBuilder = new StringBuilder("FROM TB_PSG_UPLOAD b " +qry);
			}
			queryBuilder.append(getFilterQueryfo(criterias , queryBuilder));
			if (criterias.hasOneSortedColumn()) {
				List<String> orderParams = new ArrayList<String>();
				Iterator<String> itr2 = orderParams.iterator();
				while (itr2.hasNext()) {
					queryBuilder.append(itr2.next());
					if (itr2.hasNext()) {
						queryBuilder.append(" , ");
					}
				}
			}
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
			
			Query q = sessionHQL.createQuery(queryBuilder.toString());
			
			q.setFirstResult(criterias.getDisplayStart());
			q.setMaxResults(criterias.getDisplaySize());
			
			List<TB_PSG_UPLOAD> list1 = (List<TB_PSG_UPLOAD>) q.list();
			tx.commit();
			
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0;i<list1.size();i++) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("id", criterias.getDisplayStart()+i+1);
				
				
				String f="";
				String f1="";
				String f2="";
				String f3="";
				String f4="";
				String f5="";
				String Up1 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_offrs_do_2_iaff_3010')}\"";
				f = "<i class='action_icons action_update'  " + Up1 + " title='download'></i>";
				
				String Up2 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_str_return_iaff_3008')}\"";
				f1 = "<i class='action_icons action_update'  " + Up2 + " title='download'></i>";
				
				String Up3 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_offrs__arrival_rp')}\"";
				f2 = "<i class='action_icons action_update'  " + Up3 + " title='download'></i>";
				
				String Up4 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_jco_do_2')}\"";
				f3 = "<i class='action_icons action_update'  " + Up4 + " title='download'></i>";
				
				
				String Up5 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_str_return_iaff_3009_a_b')}\"";
				f4 = "<i class='action_icons action_update'  " + Up5 + " title='download'></i>";
				
				String Up6 = "onclick=\"{getDownloadImage("+ list1.get(i).getId() +",'up_statics_pers_cns')}\"";
				f5 = "<i class='action_icons action_update'  " + Up6 + " title='download'></i>";
				
				
				columns.put("up_offrs_do_2_iaff_3010", f);
				columns.put("up_str_return_iaff_3008", f1);
				columns.put("up_offrs__arrival_rp", f2);
				columns.put("up_jco_do_2", f3);
				columns.put("up_str_return_iaff_3009_a_b", f4);
				columns.put("up_statics_pers_cns", f5);
				
				
				
			
				
				list.add(columns);
			
			}
			sessionHQL.close();
			return list;
		}*/

		private List<Map<String, Object>> findDepartmentCriteriasforma1(DatatablesCriterias criterias,String qry,String roleSubAccess) {
			String q = null;
			
			
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			Connection conn = null;
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;
				
				
				
				
					q = "select distinct l.id,l.sus_no, l.up_offrs_do_2_iaff_3010,"+ 
						" l.up_str_return_iaff_3008, l.up_str_return_iaff_3008,l.up_offrs__arrival_rp,"+
						" l.up_str_return_iaff_3009_a_b, l.up_jco_do_2,l.up_statics_pers_cns,SUBSTRING(l.date,1,5) ||\r\n" + 
						"TO_CHAR(\r\n" + 
						"    TO_DATE (SUBSTRING(l.date,6,2)  ::text, 'MM'), 'Month'\r\n" + 
						"    ) AS date,d.unit_name"+
						" from tb_psg_upload l " + 
						" inner join tb_miso_orbat_unt_dtl d on l.sus_no= d.sus_no" ;
				
				
					q += getFilterQueryfo(criterias , q) + "  group by  1,2,3,4,5,6,7,8,9,10,11 order by d.unit_name";
					
					
					if (criterias.hasOneSortedColumn()) {
						List<String> orderParams = new ArrayList<String>();
						
					
						Iterator<String> itr2 = orderParams.iterator();
						while (itr2.hasNext()) {
							q += itr2.next();
							if (itr2.hasNext()) {
								q += " , ";
							}
						}
					}
				
				stmt=conn.prepareStatement(q);
					   ResultSet rs = stmt.executeQuery();
					   ResultSetMetaData metaData = rs.getMetaData();
					   int columnCount = metaData.getColumnCount();
					   int count = 0;
					   while (rs.next()) {
						   count += 1;
						   Map<String, Object> columns = new LinkedHashMap<String, Object>();
						   columns.put("sr", criterias.getDisplayStart() + count);
						   for (int i = 1; i <= columnCount; i++) {
				 	           	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						   }
				 	           
						   String f="";
							String f1="";
							String f2="";
							String f3="";
							String f4="";
							String f5="";
							
							
							/*String Up1 = "onclick=\"if (confirm('Are you sure you want to update?') ){getDownloadImage("+rs.getObject(1)+")}else{ return false;}\"";
							 f ="<i class='action_icons action_update' "+Up1+" title='Edit Data'></i>";*/
			 	  	
							
							String Up1 = "onclick=\"{getDownloadImage("+ rs.getObject(1) +",'up_offrs_do_2_iaff_3010')}\"";
							f = "<i class='action_icons action_download'  " + Up1 + " title='download'></i>";
								
							String Up2 = "onclick=\"{getDownloadImage("+ rs.getObject(1) +",'up_str_return_iaff_3008')}\"";
							f1 = "<i class='action_icons action_download'  " + Up2 + " title='download'></i>";
							
							String Up3 = "onclick=\"{getDownloadImage("+ rs.getObject(1) +",'up_offrs__arrival_rp')}\"";
							f2 = "<i class='action_icons action_download'  " + Up3 + " title='download'></i>";
							
							String Up4 = "onclick=\"{getDownloadImage("+ rs.getObject(1) +",'up_jco_do_2')}\"";
							f3 = "<i class='action_icons action_download'  " + Up4 + " title='download'></i>";
							
							
							String Up5 = "onclick=\"{getDownloadImage("+ rs.getObject(1) +",'up_str_return_iaff_3009_a_b')}\"";
							f4 = "<i class='action_icons action_download'  " + Up5 + " title='download'></i>";
							
							
							
							
							columns.put("up_offrs_do_2_iaff_3010", f);
							columns.put("up_str_return_iaff_3008", f1);
							columns.put("up_offrs__arrival_rp", f2);
							columns.put("up_jco_do_2", f3);
							columns.put("up_str_return_iaff_3009_a_b", f4);
							
				 	  		list.add(columns);
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
					return list;
		}

	  
		private Long getFilteredCountfo(DatatablesCriterias criterias,String qry ) {
	    	String q = null;
	
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			Connection conn = null;
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;
				if( qry.equals("")){
					q = "select distinct l.id,l.sus_no, l.up_offrs_do_2_iaff_3010,"+ 
							" l.up_str_return_iaff_3008, l.up_str_return_iaff_3008,l.up_offrs__arrival_rp,"+
							" l.up_str_return_iaff_3009_a_b, l.up_jco_do_2,l.up_statics_pers_cns,SUBSTRING(l.date,1,5) ||\r\n" + 
							"TO_CHAR(\r\n" + 
							"    TO_DATE (SUBSTRING(l.date,6,2)  ::text, 'MM'), 'Month'\r\n" + 
							"    ) AS date,d.unit_name"+
							" from tb_psg_upload l " + 
							" inner join tb_miso_orbat_unt_dtl d on l.sus_no= d.sus_no";  
							
				}
					q += getFilterQueryfo(criterias , q) + "  group by  1,2,3,4,5,6,7,8,9,10,11 order by d.unit_name";
					
					stmt=conn.prepareStatement(q);
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
					
			   return Long.parseLong(String.valueOf(list.size()));
	    }
	    
	    private static StringBuilder getFilterQueryfo(DatatablesCriterias criterias,String queryBuilder2) {
			StringBuilder queryBuilder = new StringBuilder();
			List<String> paramList = new ArrayList<String>();
			if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
				if(!queryBuilder2.toString().contains("where")){
					queryBuilder.append(" where ");
				}
				else{
					queryBuilder.append(" AND (");
				}
			
				
				for (ColumnDef columnDef : criterias.getColumnDefs()) {
					if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
						if(columnDef.getName().equals("unit_name")) {
							paramList.add(" LOWER(d.unit_name) LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
						}
						if(columnDef.getName().equals("date")) {
							paramList.add(" LOWER(SUBSTRING(l.date,1,5) ||\r\n" + 
									"TO_CHAR(\r\n" + 
									"    TO_DATE (SUBSTRING(l.date,6,2)  ::text, 'MM'), 'Month'\r\n" + 
									"    ) ) LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
						}
					}
				}
				
				Iterator<String> itr = paramList.iterator();
				while (itr.hasNext()) {
					queryBuilder.append(itr.next());
					if (itr.hasNext()) {
						queryBuilder.append(" OR ");
					}
				}
				//queryBuilder.append(")");
			}
			return queryBuilder;
		}
	    
	    private Long getTotalCountfo(String qry) { 
			
	    	 int columnCount=0;
	    	String q = null;				
			Connection conn = null;
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;
				if( qry.equals("")){
					q = "select COUNT(l.id)"+ 
							" from tb_psg_upload l " + 
							" inner join tb_miso_orbat_unt_dtl d on l.sus_no= d.sus_no" ; 
							
				}else {
			
					
					q = "select COUNT(l.id)"+ 
							" from tb_psg_upload l " + 
							" inner join tb_miso_orbat_unt_dtl d on l.sus_no= d.sus_no"  + qry ;
				}
				
					
					stmt=conn.prepareStatement(q);
					   ResultSet rs = stmt.executeQuery();
					   ResultSetMetaData metaData = rs.getMetaData();
					    columnCount = metaData.getColumnCount();						      
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
			   return (long) columnCount;
		}
	    
}
