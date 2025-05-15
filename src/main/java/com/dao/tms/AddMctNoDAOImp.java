package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.models.TB_TMS_MCT_MASTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class AddMctNoDAOImp implements AddMctNoDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	} 
	@SuppressWarnings("unchecked")
	public Boolean ifExistMctNo(BigInteger mct) {
		String q = "from TB_TMS_MCT_MASTER where mct =:mct";
   	    List<TB_TMS_MCT_MASTER> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("mct", mct);
	   		created_by = (List<TB_TMS_MCT_MASTER>) query.list();
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
	
	public DataSet<TB_TMS_MCT_MASTER> findMctNoReportWithDatatablesCriterias(DatatablesCriterias criterias , String mct1,String type_of_vehicle1,String prf_group1,String mct_main_id1) {
		String qry = "";
		if(!mct1.equals("0") && !mct1.equals("")){
			qry +=" where mct = :mct";
    	}
    	if(!type_of_vehicle1.equals("0") && !type_of_vehicle1.equals("")){
    		if(qry.equals("")) {
    			qry +=" where type_of_vehicle = :type_of_vehicle ";
    		}else {
    			qry +=" and type_of_vehicle = :type_of_vehicle ";
    		}
		}
    	if(!prf_group1.equals("0") && !prf_group1.equals("")){
    		if(qry.equals("")) {
    			qry +=" where prf_group = :prf_group ";
    		}else {
    			qry +=" and prf_group = :prf_group ";
    		}
		}
    	if(!mct_main_id1.equals("0") && !mct_main_id1.equals("")){
    		if(qry.equals("")) {
    			qry +=" where cast(mct as string) like :mct_main_id ";
    		}else {
    			qry +=" and cast(mct as string) like :mct_main_id ";
    		}
		}
		List<TB_TMS_MCT_MASTER> metadata = findDepartmentCriteriasmob(criterias,qry,mct1,type_of_vehicle1,prf_group1,mct_main_id1);
		Long countFiltered = getFilteredCountmo(criterias,qry,mct1,type_of_vehicle1,prf_group1,mct_main_id1);
		Long count = getTotalCountmo(qry,mct1,type_of_vehicle1,prf_group1,mct_main_id1);
		return new DataSet<TB_TMS_MCT_MASTER>(metadata, count, countFiltered);
	}
	@SuppressWarnings("unchecked")
	private List<TB_TMS_MCT_MASTER> findDepartmentCriteriasmob(DatatablesCriterias criterias ,String qry,String mct1,String type_of_vehicle1,String prf_group1,String mct_main_id1) {
		StringBuilder queryBuilder = null;
		if(qry.equals("")){
			 queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MCT_MASTER d ");
		}else {
			 queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MCT_MASTER d "+ qry);
		}
		queryBuilder.append(getFilterQuerymo(criterias , queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if(columnDef.getName().equals("id"))
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
		
		if(!mct1.equals("0") && !mct1.equals("")){
			q.setParameter("mct", new BigInteger(mct1));
    	}
    	if(!type_of_vehicle1.equals("0") && !type_of_vehicle1.equals("")){
    		q.setParameter("type_of_vehicle", type_of_vehicle1);
    	}
    	if(!prf_group1.equals("0") && !prf_group1.equals("")){
    		q.setParameter("prf_group", prf_group1);
		}
    	if(!mct_main_id1.equals("0") && !mct_main_id1.equals("")){
    		q.setParameter("mct_main_id", mct_main_id1+"%");
		}
		
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQuerymo(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
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
					if(columnDef.getName().equalsIgnoreCase("id") || columnDef.getName().equalsIgnoreCase("mct") || columnDef.getName().equalsIgnoreCase("prf_group"))
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
		}
		queryBuilder.append(")");
		return queryBuilder;
	}
	
	private Long getTotalCountmo(String qry,String mct1,String type_of_vehicle1,String prf_group1,String mct_main_id1)  {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if(qry.equals("")){
			q = session.createQuery("SELECT COUNT(d) FROM TB_TMS_MCT_MASTER d  ");
		}else {
			q = session.createQuery("SELECT COUNT(d) FROM TB_TMS_MCT_MASTER d  " + qry);
		}
		if(!mct1.equals("0") && !mct1.equals("")){
			q.setParameter("mct", new BigInteger(mct1));
    	}
    	if(!type_of_vehicle1.equals("0") && !type_of_vehicle1.equals("")){
    		q.setParameter("type_of_vehicle", type_of_vehicle1);
    	}
    	if(!prf_group1.equals("0") && !prf_group1.equals("")){
    		q.setParameter("prf_group", prf_group1);
		}
    	if(!mct_main_id1.equals("0") && !mct_main_id1.equals("")){
    		q.setParameter("mct_main_id", mct_main_id1+"%");
		}
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountmo(DatatablesCriterias criterias ,String qry, String mct1,String type_of_vehicle1,String prf_group1,String mct_main_id1) {
		StringBuilder queryBuilder = null;
		if(qry.equals("")){
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MCT_MASTER d  ");
		}else {
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MCT_MASTER d  "+qry);
		}
		queryBuilder.append(getFilterQuerymo(criterias,queryBuilder));
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		if(!mct1.equals("0") && !mct1.equals("")){
			q.setParameter("mct", new BigInteger(mct1));
    	}
    	if(!type_of_vehicle1.equals("0") && !type_of_vehicle1.equals("")){
    		q.setParameter("type_of_vehicle", type_of_vehicle1);
    	}
    	if(!prf_group1.equals("0") && !prf_group1.equals("")){
    		q.setParameter("prf_group", prf_group1);
		}
    	if(!mct_main_id1.equals("0") && !mct_main_id1.equals("")){
    		q.setParameter("mct_main_id", mct_main_id1+"%");
		}
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}		
	
	public TB_TMS_MCT_MASTER getTB_TMS_MCT_MASTERByid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_MCT_MASTER where id=:id");
		q.setParameter("id", id);
		TB_TMS_MCT_MASTER list = (TB_TMS_MCT_MASTER) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public String UpdateMctNo(TB_TMS_MCT_MASTER mctmas,String mct_main_id, String mct_nomen,String make_id,String desc_make,String model_id,String desc_model,String username, Date date){
		String msg = null;
		Session session1=HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		 
		try{
			String hql1 = "update TB_TMS_MCT_MAIN_MASTER  set mct_nomen=:mct_nomen,prf_code=:prf_code where mct_main_id=:mct_main_id";
			Query query1 = session1.createQuery(hql1);
			query1.setParameter("mct_nomen", mct_nomen );
			query1.setParameter("mct_main_id", mct_main_id);
			query1.setParameter("prf_code", mctmas.getPrf_group());
			query1.executeUpdate();
			
			String hql2 = "update TB_TMS_MAKE_MASTER  set description=:description where mct_slot_id=:mct_slot_id and make_no=:make_no";
			Query query2 = session1.createQuery(hql2);
			query2.setParameter("description", desc_make );
			query2.setParameter("make_no", make_id);
			query2.setParameter("mct_slot_id", mct_main_id);
		    query2.executeUpdate();
		    
			String hql3 = "update TB_TMS_MODEL_MASTER  set description=:description where mct_main_id=:mct_main_id and make_id=:make_id and model_id=:model_id ";
		    Query query3 = session1.createQuery(hql3);
		    query3.setParameter("description", desc_model );
			query3.setParameter("model_id", model_id);
			query3.setParameter("mct_main_id", mct_main_id);
			query3.setParameter("make_id", make_id);
		    query3.executeUpdate();
		    
		    String std_nomclature = "";
		    if(mctmas.getType_of_vehicle().equals("A")) {
		    	std_nomclature = mct_nomen; 
			}else {
				std_nomclature = mct_nomen + " " + desc_make + " "+ desc_model;
			}
		    
		    String hql41 = "update TB_TMS_MCT_MASTER set prf_group=:prf_group where cast(mct as string) like :mct_main_id ";
		    Query query4 = session1.createQuery(hql41);
		    query4.setParameter("prf_group", mctmas.getPrf_group());
			query4.setParameter("mct_main_id", mct_main_id+"%");
			query4.executeUpdate();
			
			String hqldis = "update TB_TMS_MCT_DISCARD_CON set status=:status where  mct_main_id=:main_mct";
		 	Query querydis = session1.createQuery(hqldis).setString("main_mct", mct_main_id)
                     .setString("status","1");
		 	querydis.executeUpdate();
		 	String hqloh = "update TB_TMS_MCT_MAIN_OH_MR set status=:status where  mct_main_id=:main_mct";
	         Query queryoh = session1.createQuery(hqloh).setString("main_mct", mct_main_id)
	                         .setString("status","1");
	         queryoh.executeUpdate();
			    
			String hql = "update TB_TMS_MCT_MASTER  set  status='ACTIVE',std_nomclature=:std_nomclature,prf_group=:prf_group, type_of_vehicle=:type_of_vehicle, veh_class_code=:veh_class_code, purpose_of_vehicle=:purpose_of_vehicle, no_of_wheels=:no_of_wheels, auth_letter_no=:auth_letter_no, no_of_axles=:no_of_axles, axle_wts=:axle_wts, tonnage=:tonnage, drive=:drive, towing_capacity=:towing_capacity, type_fuel=:type_fuel, lift_capacity=:lift_capacity, sponsoring_dte=:sponsoring_dte , modify_by=:modify_by , modify_date=:modify_date where id=:id";
			Query query = session1.createQuery(hql);
			query.setParameter("std_nomclature", std_nomclature);
		    query.setParameter("prf_group", mctmas.getPrf_group());
		    query.setParameter("type_of_vehicle", mctmas.getType_of_vehicle());
		    query.setParameter("veh_class_code", mctmas.getVeh_class_code());
		    query.setParameter("purpose_of_vehicle", mctmas.getPurpose_of_vehicle());
		    query.setParameter("no_of_wheels", mctmas.getNo_of_wheels());
		    query.setParameter("auth_letter_no", mctmas.getAuth_letter_no());
			query.setParameter("no_of_axles", mctmas.getNo_of_axles());
			query.setParameter("axle_wts", mctmas.getAxle_wts());
			query.setParameter("tonnage", mctmas.getTonnage());
			query.setString("drive", mctmas.getDrive());
			query.setParameter("towing_capacity", mctmas.getTowing_capacity());
			query.setString("type_fuel", mctmas.getType_fuel());
			query.setParameter("lift_capacity", mctmas.getLift_capacity());
			query.setString("sponsoring_dte", mctmas.getSponsoring_dte());
			query.setString("modify_by", username);
			query.setDate("modify_date", date);
			query.setParameter("id", mctmas.getId());
			
		    int rowCount = query.executeUpdate();
			if(rowCount > 0) {
				msg =  "MCT  Update Successfully.";
			}else {
				msg = "MCT not Update.";
			}
			tx.commit();
		}catch (Exception e) {
			session1.getTransaction().rollback();
			
			e.printStackTrace();
			return null;
		}finally{
			session1.close();
		}
		return msg;
	}

	public String setDeleteMctNo(int id){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String msg = null;
	 	Query query = session.createQuery("update TB_TMS_MCT_MASTER set status='INACTIVE' where id =:id");
	    query.setInteger("id",id);
	    int rowCount = query.executeUpdate();
		if(rowCount > 0) {
			msg = "MCT Inactive Successfully.";
		}else {
			msg = "MCT not Inactive.";
		}
		tx.commit();
		session.close();
		return msg;
	}

//////////AddMCtNo dao
	

	@Override
	public List<Map<String, Object>> getdata_oh(int startPage, int pageLength, String Search,String type_of_veh,String line_dte, String sub_cat,String orderColunm,
			String orderType, HttpSession sessionUserId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String SerachValue="";
			if(!type_of_veh.equals("")&&!line_dte.equals(""))
			{
				
				SerachValue+="  where a.type_of_veh=? and a.sus_no=?    ";

			}
			if(!sub_cat.equals("")&&!sub_cat.equals("0"))
			{
				SerachValue+=" and  a.mct_main_id=?";
			}
if(!Search.equals(""))
{
	
	SerachValue+=" and ( upper( b.group_name) like ?  or upper(a.mct_nomen)  like ?     or  upper(d.std_nomclature) like ?) ";
	
	
}

if(type_of_veh.equals("0"))
{
	type_of_veh="A";
	
}
if(type_of_veh.equals("1"))
{
	type_of_veh="B";
}
if(type_of_veh.equals("2"))
{
	type_of_veh="C";
}
			
			
			q = "select distinct b.group_name as broadcat,a.mct_nomen as mct4Nomen ,d.std_nomclature as mct10nomen , \r\n"
					+ "COALESCE(c.total_tgt, 0) as total_tgt,COALESCE(c.service_mode, 0) as service_mode,COALESCE(c.industry_mode, 0) as industry_mode ,\r\n"
					+ "COALESCE(c.total_tgt_mgs, 0) as total_tgt_mgs,COALESCE(c.service_mode_mgs, 0) as service_mode_mgs,COALESCE(c.industry_mode_mgs, 0) as industry_mode_mgs,\r\n"
					+ "COALESCE(c.total_tgt_eme, 0) as total_tgt_eme,COALESCE(c.service_mode_eme, 0) as service_mode_eme,COALESCE(c.industry_mode_eme, 0) as industry_mode_eme ,\r\n"
					+ "COALESCE(c.total_tgt_os, 0) as total_tgt_os,COALESCE(c.service_mode_os, 0) as service_mode_os,COALESCE(c.industry_mode_os, 0) as industry_mode_os,\r\n"
					+ "c.remark as remark,a.mct_main_id"
					+ "	from  tb_tms_mct_main_master a\r\n"
					+ "					inner join tb_tms_mct_slot_master b on a.prf_code=b.prf_code\r\n"
					+ "					inner join tb_tms_mct_master d on  substring(d.mct::text, 1, 4)=a.mct_main_id::text\r\n"
					+ "					left join tb_tms_planning_of_oh c on c.mct_main_id=a.mct_main_id and c.status=0 "
					+ ""+ SerachValue
				+"     order by b.group_name " + " desc  limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
		
int flag=0;
if(!type_of_veh.equals("")&&!line_dte.equals(""))
{
	flag += 1;
	stmt.setString(flag, type_of_veh);
	flag += 1;
	stmt.setString(flag,line_dte);
}

if(!sub_cat.equals("")&&!sub_cat.equals("0"))
{
	flag += 1;
	stmt.setString(flag, sub_cat);
}
if(!Search.isEmpty()&&!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			
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

	@Override
	public long getdatacount_oh(String Search,String type_of_veh, String line_dte,String sub_cat,String orderColunm, String orderType, HttpSession sessionUserId) {
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
String SerachValue="";
if(!type_of_veh.equals("")&&!line_dte.equals(""))
{
	SerachValue+="  where a.type_of_veh=? and a.sus_no=?    ";
}
if(!sub_cat.equals("")&&!sub_cat.equals("0"))
{
	SerachValue+=" and  a.mct_main_id=?";
}
if(!Search.equals(""))
{
	SerachValue+=" and ( upper( b.group_name) like ?  or upper(a.mct_nomen)  like ?     or  upper(d.std_nomclature) like ?) ";
}





if(type_of_veh.equals("0"))
{
	type_of_veh="A";
}
if(type_of_veh.equals("1"))
{
	type_of_veh="B";
}
if(type_of_veh.equals("2"))
{
	type_of_veh="C";
}
		


			q = "select count(app.*) from(select distinct b.group_name as broadcat,a.mct_nomen as mct4Nomen ,d.std_nomclature as mct10nomen , \r\n"
					+ "COALESCE(c.total_tgt, 0) as total_tgt,COALESCE(c.service_mode, 0) as service_mode,COALESCE(c.industry_mode, 0) as industry_mode ,\r\n"
					+ "COALESCE(c.total_tgt_mgs, 0) as total_tgt_mgs,COALESCE(c.service_mode_mgs, 0) as service_mode_mgs,COALESCE(c.industry_mode_mgs, 0) as industry_mode_mgs,\r\n"
					+ "COALESCE(c.total_tgt_eme, 0) as total_tgt_eme,COALESCE(c.service_mode_eme, 0) as service_mode_eme,COALESCE(c.industry_mode_eme, 0) as industry_mode_eme ,\r\n"
					+ "COALESCE(c.total_tgt_os, 0) as total_tgt_os,COALESCE(c.service_mode_os, 0) as service_mode_os,COALESCE(c.industry_mode_os, 0) as industry_mode_os,\r\n"
					+ "c.remark as remark,a.mct_main_id	"
				
					+ "	from  tb_tms_mct_main_master a\r\n"
					+ "					inner join tb_tms_mct_slot_master b on a.prf_code=b.prf_code\r\n"
					+ "					inner join tb_tms_mct_master d on  substring(d.mct::text, 1, 4)=a.mct_main_id::text\r\n"
					+ "					left join tb_tms_planning_of_oh c on c.mct_main_id=a.mct_main_id and c.status=0  "+SerachValue
					+ " ) app ";

			PreparedStatement stmt = conn.prepareStatement(q);
				
			int flag=0;
			if(!type_of_veh.equals("")&&!line_dte.equals(""))
			{
				flag += 1;
				stmt.setString(flag, type_of_veh);
				flag += 1;
				stmt.setString(flag,line_dte);
			}
			if(!sub_cat.equals("")&&!sub_cat.equals("0"))
			{
				flag += 1;
				stmt.setString(flag, sub_cat);
			}

			if(!Search.isEmpty()&&!Search.equals("")) {
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						}
	

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
		
		
		return (long)total;
	}
	
	
	
}