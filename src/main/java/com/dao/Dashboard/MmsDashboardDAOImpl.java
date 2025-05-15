package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.models.MMS_TV_ADH_UNIT_STATUS;

import com.persistance.util.HibernateUtil;

@Service
@Repository
public class MmsDashboardDAOImpl implements MmsDashboardDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    } 
  
   public  ArrayList<ArrayList<String>> getcensusnostatustbl() {
	   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    		Connection conn = null;
    		try{	  
    			conn = dataSource.getConnection();
    			Statement stmt = (Statement)conn.createStatement();
    			
    			String sql1 = "select (select count(*) from mms_tb_mlccs_mstr_detl) as tot,(select count(*) from mms_tb_mlccs_mstr_detl where item_status='0') as curtot,\r\n" + 
    					"(select count(*) from mms_tb_mlccs_mstr_detl where item_status='1') as obetot,\r\n" + 
    					"(select count(*) from mms_tb_mlccs_mstr_detl where item_status='2') as obttot,\r\n" + 
    					"(select count(*) from mms_tb_mlccs_mstr_detl where item_status not in ('0','1','2')) as othtot" ; 
    					
    		    ResultSet rs1 = stmt.executeQuery(sql1);     
    			while(rs1.next()){
    				ArrayList<String> list1 = new ArrayList<String>();
    				list1.add(rs1.getString("tot"));
    				list1.add(rs1.getString("curtot"));
    				list1.add(rs1.getString("obetot"));
    				list1.add(rs1.getString("obttot"));
    				list1.add(rs1.getString("othtot"));
    				list.add(list1);
    	        }
    		    rs1.close();
    		    stmt.close();	     
    		}catch (SQLException e) {
    				//throw new RuntimeException(e);
    				e.printStackTrace();
    		} finally {
    			if (conn != null) {
    				try {
    					conn.close();
    				} 
    				catch (SQLException e) {
    				}
    			}
    		}
    	return list;
        }
   ///////////////////////////
   
   public  ArrayList<ArrayList<String>> getclofwpneqpttbl() {
	   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    		Connection conn = null;
    		try{	  
    			conn = dataSource.getConnection();
    			Statement stmt = (Statement)conn.createStatement();
    			
    			String sql1 = "select (select count(*) from mms_tb_mlccs_mstr_detl where class_category='1') as CL_A,\r\n" + 
    					"    	(select count(*) from mms_tb_mlccs_mstr_detl where class_category='2') as CL_B"; 
    					//"(select count(*) from mms_tb_mlccs_mstr_detl where class_category='2') as CL_C";
    			ResultSet rs1 = stmt.executeQuery(sql1);     
    			while(rs1.next()){
    				ArrayList<String> list1 = new ArrayList<String>();
    				list1.add(rs1.getString("CL_A"));
    				list1.add(rs1.getString("CL_B"));
    				//   list1.add(rs1.getString("CL_C"));
    				list.add(list1);
    	        }
    		    rs1.close();
    		    stmt.close();	     
    		}catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			if (conn != null) {
    				try {
    					conn.close();
    				} 
    				catch (SQLException e) {
    				}
    			}
    		}
    	return list;
        }
   ////////////////////////////////////////////////
   public  ArrayList<ArrayList<String>> getdefaulterunittbl() {
	   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    		Connection conn = null;
    		try{	  
    			conn = dataSource.getConnection();
    			Statement stmt = (Statement)conn.createStatement();
    			
    			/*String sql1 = "select substr(cast(now() as varchar),6,2) as mth,substr(cast(now() as varchar),1,4) as yr,p.totunits,p.totrecd as totrecd,(p.totunits-p.totrecd) as totdef from (\r\n" + 
    					"select (select count(*) as totunits from tb_miso_orbat_unt_dtl where status_sus_no='Active'  and sus_no in (select distinct sus_no from sus_weapon_wepe_with_wetpet)) as totunits,\r\n" + 
    					"(select count(sus_no) as totsus from mms_tb_obsn_detl where (cert_opt1='Y' or cert_opt2='Y') and  mth=substr(cast(now() as varchar),6,2) and  yr=substr(cast(now() as varchar),1,4)\r\n" + 
    					"group by mth,yr) as totrecd) p";*/
    			String sql = "select TO_CHAR(now(),'yyyy') as yr,TO_CHAR(now(),'mm') as mth,count(distinct o.sus_no) as totunits,\r\n" + 
    					"	count(distinct o.sus_no) filter (where p.status ='APP') as totrecd,\r\n" + 
    					"	count(distinct o.sus_no) filter (where p.status is null) as totdef\r\n" + 
    					"from tb_miso_orbat_unt_dtl o  \r\n" + 
    					"inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n" + 
    					"left join (select distinct sus_no,'APP' as status from mms_tb_unit_upload_document\r\n" + 
    					"	where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is null\r\n" + 
    					"	group by 1) p on o.sus_no=p.sus_no \r\n" + 
    					"where o.status_sus_no='Active'";
    			
    			
    			ResultSet rs1 = stmt.executeQuery(sql);
    			while(rs1.next()){
    				ArrayList<String> list1 = new ArrayList<String>();
    				list1.add(rs1.getString("mth"));
    				list1.add(rs1.getString("yr"));
    				list1.add(rs1.getString("totunits"));
    				list1.add(rs1.getString("totrecd"));
    				list1.add(rs1.getString("totdef"));
    				list.add(list1);
    	        }
    		    rs1.close();
    		    stmt.close();	     
    		}catch (SQLException e) {
    				//throw new RuntimeException(e);
    				e.printStackTrace();
    		} finally {
    			if (conn != null) {
    				try {
    					conn.close();
    				} 
    				catch (SQLException e) {
    				}
    			}
    		}
    		return list;
   }
   ////////////////////////////////
   public  ArrayList<ArrayList<String>> getunitwithmcrobsn() {
	   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    		Connection conn = null;
    		try{	  
    			conn = dataSource.getConnection();
    			Statement stmt = (Statement)conn.createStatement();
    			
    			/*String sql1 = "select (select count(*) as totunits from tb_miso_orbat_unt_dtl where status_sus_no='Active'  and sus_no in (select distinct sus_no from sus_weapon_wepe_with_wetpet)) as totunits,sum(notreslv) as totnres,sum(reslv) as totres from (\r\n" + 
    					"select distinct sus_no,(case when obsn1_act='1' then 1 else 0 end) as reslv,(case when obsn1_act='1' then 0 else 1 end) as notreslv from mms_tb_obsn_detl) p";*/
    			
    			String sql = "select \r\n" + 
    					"	count(distinct o.sus_no) as totunits,\r\n" + 
    					"	count(distinct o.sus_no) filter(where obsn.status ='OBSN') as totnres,\r\n" + 
    					"	count(distinct o.sus_no) - count(distinct o.sus_no) filter(where obsn.status ='OBSN') as totres\r\n" + 
    					"from tb_miso_orbat_unt_dtl o  \r\n" + 
    					"inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n" + 
    					"left join all_fmn_view fmn on o.form_code_control = fmn.form_code_control\r\n" + 
    					"left join baseunits b on o.sus_no=b.b_sus_no \r\n" + 
    					"left join (select sus_no,'OBSN' as status\r\n" + 
    					"			from mms_tb_unit_upload_document\r\n" + 
    					"			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is not null\r\n" + 
    					"			group by 1) as obsn on o.sus_no=obsn.sus_no \r\n" + 
    					"where o.status_sus_no='Active' ";
    					  
    			ResultSet rs1 = stmt.executeQuery(sql);     
    			while(rs1.next()){
    				ArrayList<String> list1 = new ArrayList<String>();
    				list1.add(rs1.getString("totunits"));
    				list1.add(rs1.getString("totnres"));
    				list1.add(rs1.getString("totres"));
    				list.add(list1);
    	        }
    		    rs1.close();
    		    stmt.close();	     
    		}catch (SQLException e) {
    				//throw new RuntimeException(e);
    				e.printStackTrace();
    		} finally {
    			if (conn != null) {
    				try {
    					conn.close();
    				} 
    				catch (SQLException e) {
    				}
    			}
    		}
    	return list;
        }
   //////////////////////////////////
   public  ArrayList<ArrayList<String>> getmmsRoRioStatustbl() {
	   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    		Connection conn = null;
    		try{	  
    			conn = dataSource.getConnection();
    			Statement stmt = (Statement)conn.createStatement();
    			
    			String sql1 = "select p.mth,p.yr,sum(p.totro) as totro,sum(p.rio_gen) as rio_gen,(sum(p.totro)-sum(p.rio_gen)) as rio_pend,sum(p.rio_part) as rio_part from (\r\n" + 
    					"	select substr(cast(now() as varchar),6,2) as mth,substr(cast(now() as varchar),1,4) as yr,count(ro_no) as totro,0 as rio_gen,0 as rio_pend,0 as rio_part from (\r\n" + 
    					"	select distinct ro_no,to_sus_no from mms_tb_ro_mstr where ltrim(TO_CHAR(data_upd_date,'mm-yyyy'),'0') = ltrim(TO_CHAR(now(),'mm-yyyy'),'0') ) a\r\n" + 
    					"	union all\r\n" + 
    					"	select substr(cast(now() as varchar),6,2) as mth,substr(cast(now() as varchar),1,4) as yr,0 as totro,count(rio_no) as rio_gen,0 as rio_pend,0 as rio_part from (\r\n" + 
    					"	select distinct rio_no,to_sus_no from mms_tb_rio_mstr where op_status='1' and ltrim(TO_CHAR(data_upd_date,'mm-yyyy'),'0') = ltrim(TO_CHAR(now(),'mm-yyyy'),'0') ) a\r\n" + 
    					"	union all \r\n" + 
    					"	select substr(cast(now() as varchar),6,2) as mth,substr(cast(now() as varchar),1,4) as yr,0 as totro,0 as rio_gen,count(rio_no) as rio_pend,0 as rio_part from (\r\n" + 
    					"	select distinct rio_no,to_sus_no from mms_tb_rio_mstr where op_status='0' and ltrim(TO_CHAR(data_upd_date,'mm-yyyy'),'0') = ltrim(TO_CHAR(now(),'mm-yyyy'),'0') ) a\r\n" + 
    					"	union all \r\n" + 
    					"	select substr(cast(now() as varchar),6,2) as mth,substr(cast(now() as varchar),1,4) as yr,0 as totro,0 as rio_gen,0 as rio_pend,0 as rio_part from \r\n" + 
    					"	(select ro_no,count(to_sus_no) as totsus from mms_tb_ro_mstr where ltrim(TO_CHAR(data_upd_date,'mm-yyyy'),'0') = ltrim(TO_CHAR(now(),'mm-yyyy'),'0') \r\n" + 
    					"	group by ro_no) a \r\n" + 
    					"	left join (select ro_no,count(to_sus_no) as totsus from mms_tb_rio_mstr where ltrim(TO_CHAR(data_upd_date,'mm-yyyy'),'0') = ltrim(TO_CHAR(now(),'mm-yyyy'),'0')\r\n" + 
    					"	group by ro_no) b on a.ro_no=b.ro_no where a.totsus<>b.totsus) p group by mth,yr\r\n" ;
    					
    					
    			ResultSet rs1 = stmt.executeQuery(sql1);     
    			while(rs1.next()){
    				ArrayList<String> list1 = new ArrayList<String>();
    				list1.add(rs1.getString("mth"));
    				list1.add(rs1.getString("yr"));
    				list1.add(rs1.getString("totro"));
    				list1.add(rs1.getString("rio_gen"));
    				list1.add(rs1.getString("rio_pend"));
    				list1.add(rs1.getString("rio_part"));
    				list.add(list1);
    	        }
    		    rs1.close();
    		    stmt.close();	     
    		}catch (SQLException e) {
    				e.printStackTrace();
    		} finally {
    			if (conn != null) {
    				try {
    					conn.close();
    				} 
    				catch (SQLException e) {
    				}
    			}
    		}
    	return list;
        }
   ///////////////////////////////////////////////////////
   
   public List<CUE_TB_MISO_PRF_Mst> getmmsprfnamenoList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("SELECT distinct prf_group_code as prf_code,prf_group FROM CUE_TB_MISO_PRF_Mst order by prf_group");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_PRF_Mst> count = (List<CUE_TB_MISO_PRF_Mst>) query.list();
		tx.commit();
		session.close();
		return count;
	}
   
    public Long getmmscensusTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("SELECT COUNT(census_no) FROM MMS_TB_MLCCS_MSTR_DETL WHERE active_status='Active'");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
    
   public Long getmmsprfTotalnoList() {
  		Session session= HibernateUtil.getSessionFactory().openSession();
  		session.setFlushMode(FlushMode.ALWAYS);
  		Transaction tx = session.beginTransaction();
  		Query query = session.createQuery("SELECT COUNT(prf_group_code) AS totprf FROM CUE_TB_MISO_PRF_Mst WHERE status_active='Active'"); 
  		Long count = (Long) query.list().get(0);
  		tx.commit();
  		session.close();
  		return count;
  	}
   
   public Long getmmsunitTotalnoList() {
 		Session session= HibernateUtil.getSessionFactory().openSession();
 		session.setFlushMode(FlushMode.ALWAYS);
 		Transaction tx = session.beginTransaction();
 		Query query = session.createQuery("select count(*) as totunits from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in (select distinct sus_no from sus_weapon_wepe_with_wetpet)"); 
 		Long count = (Long) query.list().get(0);
 		tx.commit();
 		session.close();
 		return count;
 	}
   
   public Long getmmsdepotTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(sus_no) from Tb_Miso_Orbat_Unit_Other_Function where function_mms='ORD DEP' and role='DEPOT'"); 
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
   
   public Long getmmssectorTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count (type_of_hldg) from MMS_TB_DEPOT_REGN_MSTR_DETL where type_of_hldg ='A5'"); 
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
   public Long getmmsloanTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count (type_of_hldg) from MMS_TB_DEPOT_REGN_MSTR_DETL where type_of_hldg ='A14'"); 
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
   public Long getmmsacsfpTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count (type_of_hldg) from MMS_TB_DEPOT_REGN_MSTR_DETL where type_of_hldg ='A16'"); 
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
   public Long getmmsengrTotalList() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count (type_of_hldg) from MMS_TB_DEPOT_REGN_MSTR_DETL where type_of_hldg ='A5'"); 
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
    
    ///////////////////prf wise bar/////////////////////////
       
       
	public ArrayList<List<String>> getchartdivPRFWiseList(String prf) {
    	   StringBuilder builder = new StringBuilder();
    	   StringBuilder builder1 = new StringBuilder();
    	   StringBuilder builder2 = new StringBuilder();
    	   
    	   if(!prf.equals("0")) {
    		   String[] prfArray = prf.split(",");
    		   builder.append(" (");    
    		   for( int i = 0 ; i < prfArray.length; i++ ) {
    			   builder.append(" prf_group_code = ? ");
    			   if (prfArray.length > i + 1) {
    				   builder.append(" or ");
    	            }
    		   }
    		   builder.append(")");
    		   
    		   builder1.append(" (");    
    		   for( int i = 0 ; i < prfArray.length; i++ ) {
    			   builder1.append(" m.prf_code = ? ");
    			   if (prfArray.length > i + 1) {
    				   builder1.append(" or ");
    	            }
    		   }
    		   builder1.append(")");
    		   
    		   builder2.append(" (");    
    		   for( int i = 0 ; i < prfArray.length; i++ ) {
    			   builder2.append(" prf_group_code = ? ");
    			   if (prfArray.length > i + 1) {
    				   builder2.append(" or ");
    	            }
    		   }
    		   builder2.append(")");
    	   }
    	   
    	   ArrayList<List<String>> listA = new ArrayList<List<String>>();
			Connection conn = null;
			try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String sql1 = "";
				if(prf.equals("0")) {
					sql1 ="select a.prf_code,c.prf_grp_short as prf_group,a.totalue as ue,b.totaluh as uh from \r\n" + 
							"(select prf_group_code as prf_code,sum(total_ue_qty) as totalue from mms_ue_mview  group by prf_group_code) a,\r\n" + 
							"(select m.prf_code,sum(a.tothol) as totaluh from mms_tv_regn_mstr a left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no group by m.prf_code) b,\r\n" + 
							"(select prf_grp_short,prf_group_code,priority_id from cue_tb_miso_prf_group_mst where priority_id > 0 order by priority_id) c\r\n" + 
							"where a.prf_code = b.prf_code and a.prf_code=c.prf_group_code order by c.priority_id limit 3";
				}else {
					sql1 = "select a.prf_code,c.prf_group as prf_group,a.totalue as ue,b.totaluh as uh from \r\n" + 
							"(select prf_group_code as prf_code,sum(total_ue_qty) as totalue from mms_ue_mview where "+builder+" group by prf_group_code) a,\r\n" + 
							"(select m.prf_code,sum(a.tothol) as totaluh from mms_tv_regn_mstr a left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no and "+builder1+" and m.prf_code is not null group by m.prf_code) b,\r\n" + 
							"(select prf_group,prf_group_code,priority_id from cue_tb_miso_prf_group_mst where "+builder2+" order by priority_id) c\r\n" + 
							"where a.prf_code = b.prf_code and a.prf_code=c.prf_group_code order by c.priority_id limit 3";
				}
				
				stmt=conn.prepareStatement(sql1);
				if(!prf.equals("0")) {
					String[] prfArray = prf.split(",");
					int count = 1;
					for (int p = 0; p < prfArray.length; p++) {
						stmt.setString(count++,prfArray[p]);
					}
					for (int p = 0; p < prfArray.length; p++) {
						stmt.setString(count++,prfArray[p]);
					}
					for (int p = 0; p < prfArray.length; p++) {
						stmt.setString(count++,prfArray[p]);
					}
				}
				
				ResultSet rs1 = stmt.executeQuery();  
				while(rs1.next()){
					List<String> list = new ArrayList<String>();
					list.add(rs1.getString("prf_group"));
					list.add(rs1.getString("ue"));
					list.add(rs1.getString("uh"));
					listA.add(list);
		        }	
			    rs1.close();
			    stmt.close();	     
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return listA;
		}
       
       //unit total wise details

     	public DataSet<MMS_TV_ADH_UNIT_STATUS> DatatablesCriteriasadhunit(DatatablesCriterias criterias) {
          List<MMS_TV_ADH_UNIT_STATUS> metadata = findDepartmentCriteriasumvcr(criterias);
     		Long countFiltered = getFilteredCountCmvu(criterias);
     		Long count = getTotalCountCmvu();
     		return new DataSet<MMS_TV_ADH_UNIT_STATUS>(metadata, count, countFiltered);
     	}
     	@SuppressWarnings("unchecked")
     	private List<MMS_TV_ADH_UNIT_STATUS> findDepartmentCriteriasumvcr(DatatablesCriterias criterias) {
     		StringBuilder queryBuilder = null;
     		queryBuilder = new StringBuilder("SELECT d FROM MMS_TV_ADH_UNIT_STATUS d ");
     		queryBuilder.append(getFilterQueryCmvu(criterias , queryBuilder));
     		if (criterias.hasOneSortedColumn()) {
     			List<String> orderParams = new ArrayList<String>();
     			queryBuilder.append(" ORDER BY ");
     			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
     				if(columnDef.getName().equals(""))
     				{
     					String st=" ORDER BY";
     					int i = queryBuilder.indexOf(st);
     					if (i != -1) {
     						queryBuilder.delete(i, i + st.length());
     					}
     				}
     				else if(columnDef.getName().contains("hodProfile.fullName")){
     					 orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
     				}
     				else{
     					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
     				}
     			}
     			Iterator<String> itr2 = orderParams.iterator();
     			while (itr2.hasNext()) {
     				queryBuilder.append(itr2.next());
     				if (itr2.hasNext()) {
     					queryBuilder.append(" , ");
     				}
     			}
     		}
     		Session session= HibernateUtil.getSessionFactory().openSession();
     		Transaction tx = session.beginTransaction();
     		Query q = session.createQuery(queryBuilder.toString());
     		q.setFirstResult(criterias.getDisplayStart());
     		q.setMaxResults(criterias.getDisplaySize());
     		List<MMS_TV_ADH_UNIT_STATUS> list = (List<MMS_TV_ADH_UNIT_STATUS>) q.list();
     		tx.commit();
     		session.close();
     		return list;
     	}

     	private static StringBuilder getFilterQueryCmvu(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
     		StringBuilder queryBuilder = new StringBuilder();
     		List<String> paramList = new ArrayList<String>();
     		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
     			if(!queryBuilder1.toString().contains("where")){
     				queryBuilder.append(" WHERE ");
     			}
     			else{
     				queryBuilder.append(" AND (");
     			}
     			for (ColumnDef columnDef : criterias.getColumnDefs()) {
     				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
						if(columnDef.getName().equalsIgnoreCase("tot_c")
								||columnDef.getName().equalsIgnoreCase("tot_o")
								||columnDef.getName().equalsIgnoreCase("totpend") 
								||columnDef.getName().equalsIgnoreCase("totresl")
								||columnDef.getName().equalsIgnoreCase("totwet"))
								{
									if(criterias.getSearch().matches("[0-9]+"))
									{
										paramList.add(" d." + columnDef.getName()	+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
									}
								}
								else{
									paramList.add(" LOWER(d." + columnDef.getName()	+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
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
     			queryBuilder.append(")");
     		}
     		return queryBuilder;
     	}
     	private Long getTotalCountCmvu() {
     		Session session= HibernateUtil.getSessionFactory().openSession();
     		Transaction tx = session.beginTransaction();
     		Query q = null;
     		q = session.createQuery("SELECT COUNT(d) FROM MMS_TV_ADH_UNIT_STATUS d ");
     		Long count = (Long) q.list().get(0);
     		tx.commit();
     		session.close();
     		return count;
     	}
     	@SuppressWarnings("unchecked")
     	private Long getFilteredCountCmvu(DatatablesCriterias criterias) {
     		StringBuilder queryBuilder = null;
     		queryBuilder = new StringBuilder("SELECT d FROM MMS_TV_ADH_UNIT_STATUS d ");
     		queryBuilder.append(getFilterQueryCmvu(criterias,queryBuilder));
     		Session session= HibernateUtil.getSessionFactory().openSession();
     		Transaction tx = session.beginTransaction();
     		Query q = session.createQuery(queryBuilder.toString());
     		List<MMS_TV_ADH_UNIT_STATUS> list = (List<MMS_TV_ADH_UNIT_STATUS>) q.list();
     		tx.commit();
     		session.close();
     		return Long.parseLong(String.valueOf(list.size()));
     	}			
     	// unittotal wise details End


       
     	
    	//Using DataTable With MockJax Report for SQL
    	public List<Map<String, Object>> getTotalUnitStatusReport(int startPage,String pageLength,String Search,String orderColunm,String orderType) {
    		if(pageLength.equals("-1")){
    			pageLength = "ALL";
    		}
    		String SearchValue = GenerateQueryWhereClause_SQL(Search);
    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    		Connection conn = null;
    		String q = "";
    		try {
    			conn = dataSource.getConnection();
    			q = "select \r\n" + 
    					"	DISTINCT o.sus_no,o.unit_name,\r\n" + 
    					"	fmn.unit_name as fmn_name, \r\n" + 
    					"	case when p.status='APP' then p.status else 'DEF' end  as unit_status,\r\n" + 
    					"	obsn.tot_o, \r\n" + 
    					"	last_update.upd_date \r\n" + 
    					"from tb_miso_orbat_unt_dtl o  \r\n" + 
    					"inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n" + 
    					"left join all_fmn_view fmn on o.form_code_control = fmn.form_code_control\r\n" + 
    					"left join baseunits b on o.sus_no=b.b_sus_no \r\n" + 
    					"left join (select distinct sus_no,'APP' as status from mms_tb_unit_upload_document\r\n" + 
    					"			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is null\r\n" + 
    					"			group by 1) p on o.sus_no=p.sus_no \r\n" + 
    					"left join (select sus_no,count(*) as tot_o,\r\n" + 
    					"			count(id) filter(where miso_reply is not null) as totresl,\r\n" + 
    					"			count(id) filter(where miso_reply is null) as totpend\r\n" + 
    					"			from mms_tb_unit_upload_document\r\n" + 
    					"			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is not null\r\n" + 
    					"			group by 1) as obsn on o.sus_no=obsn.sus_no \r\n" + 
    					"left join (select sus_no,TO_CHAR(max(created_on),'dd-mm-yyyy') as upd_date\r\n" + 
    					"			from mms_tb_unit_upload_document\r\n" + 
    					"			group by 1) last_update on o.sus_no=last_update.sus_no \r\n" + 
    					"where o.status_sus_no='Active' \r\n" +	SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
    			
    			PreparedStatement stmt = conn.prepareStatement(q);
    			stmt = setQueryWhereClause_SQL(stmt,Search);
    			ResultSet rs = stmt.executeQuery();
    			ResultSetMetaData metaData = rs.getMetaData();
    			int columnCount = metaData.getColumnCount();
    			while (rs.next()) {
    				Map<String, Object> columns = new LinkedHashMap<String, Object>();
    				for (int i = 1; i <= columnCount; i++) {
    					columns.put(metaData.getColumnLabel(i),rs.getObject(i));
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
    	public long getTotalUnitStatusReportCount(String Search) {
    		String SearchValue = GenerateQueryWhereClause_SQL(Search);
    		int total = 0;
    		String q = null;
    		Connection conn = null;
    		try {
    			conn = dataSource.getConnection();
    			q =" select \r\n" + 
    					"	count(distinct o.sus_no)\r\n" + 
    					"from tb_miso_orbat_unt_dtl o  \r\n" + 
    					"inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n" + 
    					"left join all_fmn_view fmn on o.form_code_control = fmn.form_code_control\r\n" + 
    					"left join baseunits b on o.sus_no=b.b_sus_no \r\n" + 
    					"left join (select distinct sus_no,'APP' as status from mms_tb_unit_upload_document\r\n" + 
    					"			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is null\r\n" + 
    					"			group by 1) p on o.sus_no=p.sus_no \r\n" + 
    					"left join (select sus_no,count(*) as tot_o,\r\n" + 
    					"			count(id) filter(where miso_reply is not null) as totresl,\r\n" + 
    					"			count(id) filter(where miso_reply is null) as totpend\r\n" + 
    					"			from mms_tb_unit_upload_document\r\n" + 
    					"			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is not null\r\n" + 
    					"			group by 1) as obsn on o.sus_no=obsn.sus_no \r\n" + 
    					"left join (select sus_no,TO_CHAR(max(created_on),'dd-mm-yyyy') as upd_date\r\n" + 
    					"			from mms_tb_unit_upload_document\r\n" + 
    					"			group by 1) last_update on o.sus_no=last_update.sus_no \r\n" + 
    					"where o.status_sus_no='Active' " +SearchValue;
    			
    			PreparedStatement stmt = conn.prepareStatement(q);
    			stmt = setQueryWhereClause_SQL(stmt,Search);
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
    	public String GenerateQueryWhereClause_SQL(String Search) {
    		String SearchValue ="";
    		if(!Search.equals("")) { // for Input Filter
    			SearchValue +=" and ( lower(o.sus_no) like ? or lower(o.unit_name) like ? or lower(fmn.unit_name) like ?  or lower(p.status) like ? or cast(last_update.upd_date as varchar) like ? ) ";
    		}
    		return SearchValue;
    	}
    	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
    		int flag = 0;
    		try {
    			Search = Search.toLowerCase();
    			if(!Search.equals("")) {
    				flag += 1;
    				stmt.setString(flag, "%"+Search+"%");
    				flag += 1;
    				stmt.setString(flag, "%"+Search+"%");
    				flag += 1;
    				stmt.setString(flag, "%"+Search+"%");
    				flag += 1;
    				stmt.setString(flag, "%"+Search+"%");
    				flag += 1;
    				stmt.setString(flag, "%"+Search+"%");
    			}
    		}catch (Exception e) {}
    		return stmt;		
    	}
    	//Using DataTable With MockJax Report for SQL
    	
     	
}
