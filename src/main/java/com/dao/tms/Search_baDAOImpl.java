package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CONVERT_REQ;
import com.models.TB_TMS_RELIEF_PROG_HISTORY;
import com.models.Tms_Banum_Req_Child;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Search_baDAOImpl implements Search_baDAO{
	
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public  ArrayList<ArrayList<String>> getBaNoCurrentStatus(String ba_no,String veh_cat) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
    	try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=null;
			String sql1="";
			if(veh_cat.equals("A")) {
				sql1 ="select distinct ba_no ,mct,engine,chasis,status,\r\n" + 
						"	CASE 	WHEN 	case1 > 0 and case4 = 0\r\n" + 
						"		THEN 	(select sus_no from tb_tms_census_retrn where ba_no= ? limit 1)\r\n" + 
						"		WHEN 	case1 = 0 and case2 > 0 and case4 = 0\r\n" + 
						"		THEN 	(select sus_no from tb_tms_census_drr_dir_dtl where  ba_no= ? order by id desc limit 1)\r\n" + 
						"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no != '' and auction_out_other_agency = ''\r\n" + 
						"		THEN 	(select unit_sus_no from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition = '4' order by id desc limit 1)\r\n" + 
						"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no = '' and auction_out_other_agency != ''\r\n" + 
						"		THEN 	''\r\n" + 
						"	END AS sus_no ,\r\n" + 
						"\r\n" + 
						"	CASE 	WHEN 	case1 > 0 and case4 = 0\r\n" + 
						"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_census_retrn where ba_no= ? limit 1))\r\n" + 
						"		WHEN 	case1 = 0 and case2 > 0 and case4 = 0\r\n" + 
						"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_census_drr_dir_dtl where  ba_no= ? order by id desc limit 1)) \r\n" + 
						"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no != '' and auction_out_other_agency = ''\r\n" + 
						"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select unit_sus_no from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition = '4' order by id desc limit 1))\r\n" + 
						"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no = '' and auction_out_other_agency != ''\r\n" + 
						"		THEN 	(select other_agency from tb_tms_census_drr_dir_dtl where  ba_no= ? and issue_condition = '4' order by id desc limit 1)	\r\n" + 
						"	END AS unit_name \r\n" + 
						"from\r\n" + 
						"\r\n" + 
						"(select \r\n" + 
						"	b.ba_no as ba_no,\r\n" + 
						"	b.mct as mct,\r\n" + 
						"	b.engine_no as engine,\r\n" + 
						"	b.chasis_no as chasis,\r\n" + 
						"	b.status as status,\r\n" + 
						"	(select count(id) from tb_tms_census_retrn where  ba_no= ? limit 1) case1,\r\n" + 
						"	(select count(id) from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition != '4' limit 1) case2,\r\n" + 
						"	(select count(id) from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition = '4' limit 1 ) case4,\r\n" + 
						"	(select unit_sus_no from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition = '4' limit 1 ) auction_out_unit_sus_no,\r\n" + 
						"	(select other_agency from tb_tms_census_drr_dir_dtl where ba_no= ? and issue_condition = '4' limit 1 ) auction_out_other_agency\r\n" + 
						"from tb_tms_banum_dirctry  b\r\n" + 
						"where  b.ba_no= ?) fin";
    				stmt=conn.prepareStatement(sql1);
    				stmt.setString(1, ba_no);
    				stmt.setString(2, ba_no);
    				stmt.setString(3, ba_no);
    				stmt.setString(4, ba_no);
    				stmt.setString(5, ba_no);
    				stmt.setString(6, ba_no);
    				stmt.setString(7, ba_no);
    				stmt.setString(8, ba_no);
    				stmt.setString(9, ba_no);
    				stmt.setString(10, ba_no);
    				stmt.setString(11, ba_no);
    				stmt.setString(12, ba_no);
    				stmt.setString(13, ba_no);
    				
	    			ResultSet rs1 = stmt.executeQuery(); 
				    while(rs1.next()){
				    	  ArrayList<String> list = new ArrayList<String>();
				    	  list.add(rs1.getString("ba_no"));
				    	  list.add(rs1.getString("mct"));
				    	  list.add(rs1.getString("engine"));
				    	  list.add(rs1.getString("chasis"));
				    	  list.add(rs1.getString("status"));
				    	  list.add(rs1.getString("sus_no"));
				    	  list.add(rs1.getString("unit_name"));
				    	  alist.add(list);
			        }
				    rs1.close();
    		}
    		if(veh_cat.equals("B")) {

    			sql1 = "select distinct \r\n" + 
    					"	ba_no,\r\n" + 
    					"	mct,\r\n" + 
    					"	engine,\r\n" + 
    					"	chasis,\r\n" + 
    					"	status, \r\n" + 
    					"    	--Fetch SUS NO\r\n" + 
    					"	CASE 	WHEN 	case1 > 0  and case2 = 0  and case3 = 0 and case4 = 0 and relief = 0 and convert = 0  \r\n" + 
    					"		THEN 	(select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='0' and type_of_issue='1') and ba_no= ? and status='1')	  \r\n" + 
    					"		WHEN  case2 > 0  and case1 > 0  and case3 = 0 and case4 = 0 and relief = 0 and convert = 0 \r\n" + 
    					"		THEN 	(select unit_sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='1' and type_of_issue='0') and ba_no= ? and status='1')	  \r\n" + 
    					"		WHEN  relief > 0  and case1 > 0  and case2 > 0  and case3 = 0 and case4 = 0 and convert = 0\r\n" + 
    					"		THEN 	(select to_unit_sus_no from tb_tms_relief_prog_history where ba_no = ? ORDER BY approve_date DESC limit 1)	    \r\n" + 
    					"		WHEN   convert > 0  and case1 > 0  and case2 > 0 and case3 = 0 and case4 = 0 and relief = 0\r\n" + 
    					"		THEN 	(select sus_no from tb_tms_mvcr_parta_dtl where ba_no= ? and status='1')	 \r\n" + 
    					"		WHEN  case3 > 0  and case1 > 0  and case2 > 0 \r\n" + 
    					"		THEN 	(select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='0' and type_of_issue='0') and ba_no= ? and status='1')		  \r\n" + 
    					"		WHEN  case4 > 0  and case3 > 0  and case1 > 0  and case2 > 0\r\n" + 
    					"		THEN 	(select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='1' and type_of_issue='1') and ba_no= ? and status='1')\r\n" + 
    					"		WHEN  convert > 0 and case1 = 0  and case2 = 0  and case3 = 0 and case4 = 0 and relief = 0 \r\n" + 
    					"		THEN 	(select sus_no from tb_tms_convert_req where new_ba_no= ? and status='1')\r\n" + 
    					"		END AS sus_no ,\r\n" +
    					"	--Fetch UNIT NAME \r\n" +
    					"	CASE 	WHEN 	case1 > 0  and case2 = 0  and case3 = 0 and case4 = 0 and relief = 0 and convert = 0  \r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='0' and type_of_issue='1') and ba_no= ? and status='1')) 	    \r\n" + 
    					"		WHEN  case2 > 0  and case1 > 0  and case3 = 0 and case4 = 0 and relief = 0 and convert = 0  \r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select unit_sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='1' and type_of_issue='0') and ba_no= ? and status='1'))	   \r\n" + 
    					"		WHEN  relief > 0  and case1 > 0  and case2 > 0  and case3 = 0 and case4 = 0 and convert = 0\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select to_unit_sus_no from tb_tms_relief_prog_history where ba_no= ? ORDER BY approve_date DESC limit 1))	   \r\n" + 
    					"		WHEN   convert > 0  and case1 > 0  and case2 > 0 and case3 = 0 and case4 = 0 and relief = 0\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_mvcr_parta_dtl where ba_no= ? and status='1'))	\r\n" + 
    					"		WHEN  case3 > 0  and case1 > 0  and case2 > 0 \r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='0' and type_of_issue='0') and ba_no= ? and status='1'))\r\n" + 
    					"		WHEN  case4 > 0  and case3 > 0  and case1 > 0  and case2 > 0\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_drr_dir_dtl where (typ_of_retrn='1' and type_of_issue='1') and ba_no= ? and status='1'))\r\n" + 
    					"		WHEN  convert > 0 and case1 = 0  and case2 = 0  and case3 = 0 and case4 = 0 and relief = 0 \r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_convert_req where new_ba_no= ? and status='1'))	\r\n" + 
    					"		END AS unit_name , old_ba_no \r\n" + 
    					"from \r\n" + 
    					"	 (select \r\n" + 
    					"		b.ba_no as ba_no,\r\n" + 
    					"		b.mct as mct,\r\n" + 
    					"		b.engine_no as engine,\r\n" + 
    					"		b.chasis_no as chasis,\r\n" + 
    					"		b.status as status,\r\n" + 
    					"		b.old_ba_no, \r\n" + 
    					"		(select count(case when typ_of_retrn='0' and type_of_issue='1' then sus_no end) as c1 from tb_tms_drr_dir_dtl where ba_no= ? and status='1') as case1,\r\n" + 
    					"		(select count(case when typ_of_retrn='1' and type_of_issue='0' then sus_no end) as c1 from tb_tms_drr_dir_dtl where ba_no= ? and status='1') as case2,\r\n" + 
    					"		(select count(case when typ_of_retrn='0' and type_of_issue='0' then sus_no end) as c1 from tb_tms_drr_dir_dtl where ba_no= ? and status='1') as case3,\r\n" + 
    					"		(select count(case when typ_of_retrn='1' and type_of_issue='1' then sus_no end) as c1 from tb_tms_drr_dir_dtl where ba_no= ? and status='1') as case4,\r\n" + 
    					"		(select count(to_unit_sus_no) from tb_tms_relief_prog_history where  ba_no= ?) as relief,\r\n" + 
    					"		(select count(sus_no) from tb_tms_convert_req where new_ba_no= ? and status='1') as convert \r\n" + 
    					"	from tb_tms_banum_dirctry b\r\n" + 
    					"	where b.ba_no= ?) fin";
    			
        		stmt=conn.prepareStatement(sql1);
				stmt.setString(1, ba_no);
				stmt.setString(2, ba_no);
				stmt.setString(3, ba_no);
				stmt.setString(4, ba_no);
				stmt.setString(5, ba_no);
				stmt.setString(6, ba_no);
				stmt.setString(7, ba_no);
				stmt.setString(8, ba_no);
				stmt.setString(9, ba_no);
				stmt.setString(10, ba_no);
				stmt.setString(11, ba_no);
				stmt.setString(12, ba_no);
				stmt.setString(13, ba_no);
				stmt.setString(14, ba_no);
				stmt.setString(15, ba_no);
				stmt.setString(16, ba_no);
				stmt.setString(17, ba_no);
				stmt.setString(18, ba_no);
				stmt.setString(19, ba_no);
				stmt.setString(20, ba_no);
				stmt.setString(21, ba_no);
    			ResultSet rs1 = stmt.executeQuery(); 
    			
			    while(rs1.next()){
			    	  ArrayList<String> list = new ArrayList<String>();
			    	  list.add(rs1.getString("ba_no"));
			    	  list.add(rs1.getString("mct"));
			    	  list.add(rs1.getString("engine"));
			    	  list.add(rs1.getString("chasis"));
			    	  list.add(rs1.getString("status"));
			    	  list.add(rs1.getString("sus_no"));
			    	  list.add(rs1.getString("unit_name"));
			    	  list.add(rs1.getString("old_ba_no"));
			    	  alist.add(list);
		        }
			    rs1.close();
    		}
    		if(veh_cat.equals("C")) {
        		
    			sql1 = "select distinct ba_no ,mct,engine,chasis,status,\r\n" + 
    					"	CASE 	WHEN 	case1 > 0 and case4 = 0\r\n" + 
    					"		THEN 	(select sus_no from tb_tms_emar_report where em_no= ? limit 1)\r\n" + 
    					"		WHEN 	case1 = 0 and case2 > 0 and case4 = 0\r\n" + 
    					"		THEN 	(select sus_no from tb_tms_emar_drr_dir_dtl where  em_no= ? order by id desc limit 1)\r\n" + 
    					"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no != '' and auction_out_other_agency = ''\r\n" + 
    					"		THEN 	(select unit_sus_no from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition = '4' order by id desc limit 1)\r\n" + 
    					"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no = '' and auction_out_other_agency != ''\r\n" + 
    					"		THEN 	''\r\n" + 
    					"	END AS sus_no ,\r\n" + 
    					"\r\n" + 
    					"	CASE 	WHEN 	case1 > 0 and case4 = 0\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_emar_report where em_no= ? limit 1))\r\n" + 
    					"		WHEN 	case1 = 0 and case2 > 0 and case4 = 0\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select sus_no from tb_tms_emar_drr_dir_dtl where  em_no= ? order by id desc limit 1)) \r\n" + 
    					"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no != '' and auction_out_other_agency = ''\r\n" + 
    					"		THEN 	(select unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and sus_no = (select unit_sus_no from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition = '4' order by id desc limit 1))\r\n" + 
    					"		WHEN 	case1 = 0 and case4 > 0 and auction_out_unit_sus_no = '' and auction_out_other_agency != ''\r\n" + 
    					"		THEN 	(select other_agency from tb_tms_emar_drr_dir_dtl where  em_no= ? and issue_condition = '4' order by id desc limit 1)	\r\n" + 
    					"	END AS unit_name ,\r\n" + 
    					"  old_ba_no " +
    					"from\r\n" + 
    					"\r\n" + 
    					"(select \r\n" + 
    					"	b.ba_no as ba_no,\r\n" + 
    					"	b.mct as mct,\r\n" + 
    					"	b.engine_no as engine,\r\n" + 
    					"	b.chasis_no as chasis,\r\n" + 
    					"	b.status as status,\r\n" + 
    					"	b.old_ba_no, \r\n" + 
    					"	(select count(id) from tb_tms_emar_report where  em_no= ? limit 1) case1,\r\n" + 
    					"	(select count(id) from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition != '4' limit 1) case2,\r\n" + 
    					"	(select count(id) from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition = '4' limit 1 ) case4,\r\n" + 
    					"	(select unit_sus_no from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition = '4' limit 1 ) auction_out_unit_sus_no,\r\n" + 
    					"	(select other_agency from tb_tms_emar_drr_dir_dtl where em_no= ? and issue_condition = '4' limit 1 ) auction_out_other_agency\r\n" + 
    					"from tb_tms_banum_dirctry  b\r\n" + 
    					"where  b.ba_no= ?) fin";
    			
    			stmt=conn.prepareStatement(sql1);
				stmt.setString(1, ba_no);
				stmt.setString(2, ba_no);
				stmt.setString(3, ba_no);
				stmt.setString(4, ba_no);
				stmt.setString(5, ba_no);
				stmt.setString(6, ba_no);
				stmt.setString(7, ba_no);
				stmt.setString(8, ba_no);
				stmt.setString(9, ba_no);
				stmt.setString(10, ba_no);
				stmt.setString(11, ba_no);
				stmt.setString(12, ba_no);
				stmt.setString(13, ba_no);
				
    			ResultSet rs1 = stmt.executeQuery(); 
			    while(rs1.next()){
			    	  ArrayList<String> list = new ArrayList<String>();
			    	  list.add(rs1.getString("ba_no"));
			    	  list.add(rs1.getString("mct"));
			    	  list.add(rs1.getString("engine"));
			    	  list.add(rs1.getString("chasis"));
			    	  list.add(rs1.getString("status"));
			    	  list.add(rs1.getString("sus_no"));
			    	  list.add(rs1.getString("unit_name"));
			    	  list.add(rs1.getString("old_ba_no"));
			          alist.add(list);
		        }
			    rs1.close();
    		}
    		stmt.close();
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
    
	public List<TB_TMS_BANUM_DIRCTRY> getFreestockDetails(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		if(veh_cat.equals("A")) {
			q = session.createQuery("select distinct dir.ba_no," + 
					"	dir.mct, " + 
					"	(select tb_tms_mct1_.std_nomclature from TB_TMS_MCT_MASTER tb_tms_mct1_ where tb_tms_mct1_.mct=dir.mct), " + 
					"	dir.engine_no, " + 
					"	dir.chasis_no," + 
					"	d.classification," + 
					"	to_char(d.approved_date, 'dd-mm-yyyy')," + 
					"	(select u.unit_name from Miso_Orbat_Unt_Dtl u where u.sus_no =d.sus_no and u.status_sus_no='Active') " + 
					"	from " + 
					"	TB_TMS_CENSUS_DRR_DIR_DTL d " + 
					"	, TB_TMS_BANUM_DIRCTRY dir where dir.ba_no=d.ba_no "+
					"   and d.issue_condition='1' and d.ba_no=:ba_no");
		}
		if(veh_cat.equals("B")) {
			q = session.createQuery("select distinct dir.ba_no," + 
					"	dir.mct, " + 
					"	(select tb_tms_mct1_.std_nomclature from TB_TMS_MCT_MASTER tb_tms_mct1_ where tb_tms_mct1_.mct=dir.mct), " + 
					"	dir.engine_no, " + 
					"	dir.chasis_no," + 
					"	d.classification," + 
					"	to_char(d.approve_date, 'dd-mm-yyyy')," + 
					"	(select u.unit_name from Miso_Orbat_Unt_Dtl u where u.sus_no =d.sus_no and u.status_sus_no='Active') " + 
					"	from " + 
					"	TB_TMS_DRR_DIR_DTL d " + 
					"	, TB_TMS_BANUM_DIRCTRY dir where dir.ba_no=d.ba_no "+
					"   and d.typ_of_retrn='0' and d.type_of_issue='1' and d.ba_no=:ba_no");
		}
		if(veh_cat.equals("C")) {
			
			q = session.createQuery("select distinct dir.ba_no," + 
					"	dir.mct, " + 
					"	(select tb_tms_mct1_.std_nomclature from TB_TMS_MCT_MASTER tb_tms_mct1_ where tb_tms_mct1_.mct=dir.mct), " + 
					"	dir.engine_no, " + 
					"	dir.chasis_no," + 
					"	d.classification," + 
					"	to_char(d.approve_date, 'dd-mm-yyyy')," + 
					"	(select u.unit_name from Miso_Orbat_Unt_Dtl u where u.sus_no =d.sus_no and u.status_sus_no='Active') " + 
					"	from " + 
					"	TB_TMS_EMAR_DRR_DIR_DTL d " + 
					"	, TB_TMS_BANUM_DIRCTRY dir where dir.ba_no=d.em_no "+
					"   and d.issue_condition='1' and d.em_no =:ba_no");
		}
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public  List<TB_TMS_BANUM_DIRCTRY> getInitialIssueUnit(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		if(veh_cat.equals("A")) {
		
			
			q = session.createQuery("select \r\n" + 
					"	a.ba_no,\r\n" + 
					"	b.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = b.mct),\r\n" + 
					"	b.engine_no,\r\n" + 
					"	b.chasis_no,\r\n" + 
					"	a.classification,\r\n" + 
					"	to_char(a.approved_date, 'dd-mm-yyyy'),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = a.sus_no and status_sus_no='Active') ,\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = a.unit_sus_no and status_sus_no='Active' )\r\n" + 
					"from TB_TMS_CENSUS_DRR_DIR_DTL a,TB_TMS_BANUM_DIRCTRY b where a.ba_no=:ba_no and a.issue_condition='2' and a.ba_no = b.ba_no "); 
		}
		if(veh_cat.equals("B")) {
			q = session.createQuery("select d.ba_no,\r\n" + 
					"	d.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no,\r\n" + 
					"	d.chasis_no,\r\n" + 
					"	(select classification from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='0' and ba_no=:ba_no),\r\n" + 
					"	(select to_char(approve_date, 'dd-mm-yyyy') from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='0' and ba_no=:ba_no),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='0' and ba_no=:ba_no)and status_sus_no='Active') ,\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select unit_sus_no from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='0' and ba_no=:ba_no)and status_sus_no='Active') \r\n" + 
					"	from TB_TMS_BANUM_DIRCTRY d,TB_TMS_DRR_DIR_DTL e where typ_of_retrn='1' and type_of_issue='0' and d.ba_no = e.ba_no and d.ba_no = :ba_no");
			
		}
		if(veh_cat.equals("C")) {
			q = session.createQuery("select d.ba_no, \r\n" + 
					"d.mct,\r\n" + 
					"(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"d.engine_no,\r\n" + 
					"d.chasis_no,\r\n" + 
					"e.classification,\r\n" + 
					"to_char(e.approve_date, 'dd-mm-yyyy'),\r\n" + 
					"(select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no =e.sus_no and status_sus_no='Active'),\r\n" + 
					"(select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no =e.unit_sus_no and status_sus_no='Active')\r\n" + 
					"from TB_TMS_BANUM_DIRCTRY d,TB_TMS_EMAR_DRR_DIR_DTL e where e.issue_condition='2' and d.ba_no = e.em_no and d.ba_no = :ba_no" );
			
		}
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	
	public  List<TB_TMS_CONVERT_REQ> getConvertGsToSPLList(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		if(veh_cat.equals("B")) {
			q = session.createQuery("select  c.old_ba_no," + 
					"	c.old_mct_number," + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = c.old_mct_number)," + 
					"	c.new_ba_no," + 
					"	c.new_mct_number," + 
					"	c.new_nomencatre," + 
					"	c.sus_no," + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no=c.sus_no and status_sus_no='Active')," + 
					"	to_char(c.approve_date, 'dd-mm-yyyy')" + 
					"   from" + 
					"	TB_TMS_CONVERT_REQ c" + 
					"	where c.old_ba_no = :ba_no");
		}
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_CONVERT_REQ> list = (List<TB_TMS_CONVERT_REQ>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	
	public  List<TB_TMS_RELIEF_PROG_HISTORY> getTransferofVehicle(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		if(veh_cat.equals("A")) {
			q = session.createQuery("select ba_no,"
					+ "std_nomclature,"
					+ "classification,"
					+ " auth, "
					+ "to_char(approve_date, 'dd-mm-yyyy'),"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = from_unit_sus_no and status_sus_no='Active') ,"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = to_unit_sus_no and status_sus_no='Active') "
					+ "from TB_TMS_RELIEF_PROG_HISTORY_A where ba_no = :ba_no ORDER BY approve_date ASC");
		}
		if(veh_cat.equals("B")) {
			q = session.createQuery("select ba_no,"
					+ "std_nomclature,"
					+ "classification,"
					+ " auth, "
					+ "to_char(approve_date, 'dd-mm-yyyy'),"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = from_unit_sus_no and status_sus_no='Active') ,"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = to_unit_sus_no and status_sus_no='Active') "
					+ " from TB_TMS_RELIEF_PROG_HISTORY where ba_no = :ba_no ORDER BY approve_date ASC");
		}
		if(veh_cat.equals("C")) {
			q = session.createQuery("select ba_no,"
					+ "std_nomclature,"
					+ "classification,"
					+ " auth, "
					+ "to_char(approve_date, 'dd-mm-yyyy'),"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = from_unit_sus_no and status_sus_no='Active') ,"
					+" (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = to_unit_sus_no and status_sus_no='Active') "
					+ "from TB_TMS_RELIEF_PROG_HISTORY_C where ba_no = :ba_no ORDER BY approve_date ASC");
		}
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_RELIEF_PROG_HISTORY> list = (List<TB_TMS_RELIEF_PROG_HISTORY>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	public  List<TB_TMS_BANUM_DIRCTRY> getBackLoadDetails(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		if(veh_cat.equals("A")) {
			q = session.createQuery("  select dir.ba_no,\r\n" +  // q = session.createQuery("  select distinct	dir.ba_no,\r\n" +
					"	b.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where  mct = b.mct),\r\n" + 
					"	b.engine_no, \r\n" + 
					"	b.chasis_no,\r\n" + 
					"	dir.classification,\r\n" + 
					"	to_char(dir.approved_date, 'dd-mm-yyyy'),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = dir.sus_no and status_sus_no='Active') ,\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = dir.unit_sus_no and status_sus_no='Active') \r\n" + 
					"	from\r\n" + 
					"	TB_TMS_CENSUS_DRR_DIR_DTL dir,TB_TMS_BANUM_DIRCTRY b where dir.ba_no =:ba_no and dir.issue_condition='3' and dir.ba_no = b.ba_no  ");
			q.setParameter("ba_no", ba_no);
		}
		if(veh_cat.equals("B")) {
			q = session.createQuery("select d.ba_no,\r\n" + 
					"	d.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no,\r\n" + 
					"	d.chasis_no,\r\n" + 
					"	(select classification from TB_TMS_DRR_DIR_DTL where typ_of_retrn='0' and type_of_issue='0' and ba_no=:ba_no),\r\n" + 
					"	(select to_char(approve_date, 'dd-mm-yyyy') from TB_TMS_DRR_DIR_DTL where typ_of_retrn='0' and type_of_issue='0' and ba_no=:ba_no),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_DRR_DIR_DTL where typ_of_retrn='0' and type_of_issue='0' and ba_no=:ba_no)and status_sus_no='Active') ,\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select unit_sus_no from TB_TMS_DRR_DIR_DTL where typ_of_retrn='0' and type_of_issue='0' and ba_no=:ba_no)and status_sus_no='Active') \r\n" + 
					"	from TB_TMS_BANUM_DIRCTRY d,TB_TMS_DRR_DIR_DTL e where e.typ_of_retrn='0' and e.type_of_issue='0' and d.ba_no = e.ba_no and d.ba_no =:ba_no" );
			q.setParameter("ba_no", ba_no);
		}
		if(veh_cat.equals("C")) {
			q = session.createQuery("select dir.em_no,\r\n" +  //q = session.createQuery("select distinct	dir.em_no,\r\n" +
					"	 d.mct ,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no ,\r\n" + 
					"	d.chasis_no ,\r\n" + 
					"	dir.classification,\r\n" + 
					"	to_char(dir.approve_date, 'dd-mm-yyyy'),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = dir.sus_no and status_sus_no='Active') ,\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = dir.unit_sus_no and status_sus_no='Active') \r\n" + 
					"	from\r\n" + 
					"	TB_TMS_EMAR_DRR_DIR_DTL dir,TB_TMS_BANUM_DIRCTRY d where d.ba_no = dir.em_no and dir.em_no =:ba_no and dir.issue_condition='3'");
							q.setParameter("ba_no", ba_no);

					
		}
		@SuppressWarnings("unchecked")
		List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	public  List<TB_TMS_BANUM_DIRCTRY> getAuctionedDetail(String ba_no,String veh_cat) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		if(veh_cat.equals("A")) {
			q = session.createQuery("select distinct d.ba_no,\r\n" + 
					"	d.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no,\r\n" + 
					"	d.chasis_no,\r\n" + 
					"	(select classification from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='4' and ba_no= :ba_no),\r\n" + 
					"	(select to_char(approved_date, 'dd-mm-yyyy') from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='4' and ba_no=:ba_no),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='4' and ba_no=:ba_no)and status_sus_no='Active') ,\r\n" + 
					"	(select other_agency from TB_TMS_CENSUS_DRR_DIR_DTL where issue_condition='4' and ba_no=:ba_no)\r\n" + 
					"	from TB_TMS_BANUM_DIRCTRY d ,TB_TMS_CENSUS_DRR_DIR_DTL e where issue_condition='4' and d.ba_no=e.ba_no and d.ba_no =:ba_no" );
		}
		if(veh_cat.equals("B")) {
			q = session.createQuery("select distinct d.ba_no,\r\n" + 
					"	d.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no,\r\n" + 
					"	d.chasis_no,\r\n" + 
					"	(select classification from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='1' and ba_no=:ba_no),\r\n" + 
					"	(select to_char(approve_date, 'dd-mm-yyyy') from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='1' and ba_no=:ba_no),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='1' and ba_no=:ba_no)and status_sus_no='Active') ,\r\n" + 
					"	(select filler_1 from TB_TMS_DRR_DIR_DTL where typ_of_retrn='1' and type_of_issue='1' and ba_no=:ba_no)\r\n" + 
					"	from TB_TMS_BANUM_DIRCTRY d ,TB_TMS_DRR_DIR_DTL e where e.typ_of_retrn='1' and e.type_of_issue='1' and d.ba_no=e.ba_no and d.ba_no =:ba_no");
		}
		if(veh_cat.equals("C")) {
			q = session.createQuery("select distinct d.ba_no,\r\n" + 
					"	d.mct,\r\n" + 
					"	(select std_nomclature from TB_TMS_MCT_MASTER where mct = d.mct),\r\n" + 
					"	d.engine_no,\r\n" + 
					"	d.chasis_no,\r\n" + 
					"	(select classification from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition='4' and em_no=:ba_no),\r\n" + 
					"	(select to_char(approve_date, 'dd-mm-yyyy') from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition='4' and em_no=:ba_no),\r\n" + 
					"	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition='4' and em_no=:ba_no)and status_sus_no='Active') ,\r\n" + 
					"	(select other_agency from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition='4' and em_no=:ba_no) \r\n" + 
					"	from TB_TMS_BANUM_DIRCTRY d ,TB_TMS_EMAR_DRR_DIR_DTL e where issue_condition='4' and d.ba_no=e.em_no and d.ba_no =:ba_no" );
		}
		q.setParameter("ba_no", ba_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_BANUM_DIRCTRY> list = (List<TB_TMS_BANUM_DIRCTRY>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
    //----------------------------------------------------------- View Image -------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
	   public Boolean ifExistbano(String ba_no) {
		String q = "from Tms_Banum_Req_Child where ba_no=:ba_no";
	    List<Tms_Banum_Req_Child> created_by = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	    	Query query = session.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<Tms_Banum_Req_Child>) query.list();
	   		tx.commit();
	   		session.close();
	    }catch(Exception e) {
	    	session.getTransaction().rollback();
		    e.printStackTrace();
		    return null; 
	    }
	    if(created_by.size() > 0) {
	    	return true;
	    }
	       return false;
	}
}