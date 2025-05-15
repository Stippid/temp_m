package com.dao.mnh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.mnh.Tb_Med_System_Code;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class mstr_System_Code_DAOImpl implements mstr_System_Code_DAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public Long checkExitstingsystemcode(String old_d1,String old_d2,int id) {
		String hql="select count(id)  from Tb_Med_System_Code where sys_code=:d1 and sys_code_value=:d2 and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q4= session.createQuery(hql);
		q4.setParameter("d1", old_d1);
		q4.setParameter("d2", old_d2);
		q4.setParameter("id", id);
		Long count_list =  (Long) q4.uniqueResult();	
		tx.commit();
		session.close();
		if(count_list != null)
		{
			return count_list;
		}
		else
		{
			return (long) 0;
		}
	}
	public String updateSystemCode(Tb_Med_System_Code obj){
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		
		String msg = "";
		try{
			String hql = "update Tb_Med_System_Code set sys_code=:sys_code,sys_code_value=:sys_code_value,sys_code_desc=:sys_code_desc,"
				 		+ "sys_code_value_desc=:sys_code_value_desc,status=:status,modified_by=:modified_by,modified_on=:modified_on where id=:id";	
			Query query = session1.createQuery(hql).setString("sys_code",obj.getSys_code()).setString("sys_code_value",obj.getSys_code_value())
						 .setString("sys_code_desc",obj.getSys_code_desc())
						 .setString("sys_code_value_desc",obj.getSys_code_value_desc())
						 .setString("status",obj.getStatus()).setString("modified_by", obj.getModified_by()).setTimestamp("modified_on", obj.getModified_on())
						 .setInteger("id",obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();
		 }
		 catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		 }
		 finally {
			 session1.close();
		 }
		 return msg;
	     }
	
	
	public DataSet<Map<String, Object>> DatatablesCriteriassyscode(DatatablesCriterias criterias ,String sys_code,String sys_code_value,String sys_code_desc) {
		
		String whr = "";
		String flag = "Y";
	
		if (!sys_code.equals("") && !sys_code.equals(null)) {
			if (flag.equalsIgnoreCase("Y")) {
				whr += " upper(sys_code) like upper(:sys_code)";
				flag = "N";
			} else {
				whr += " and upper(sys_code) like upper(:sys_code)";
			}
		}
		
		if (!sys_code_value.equals("") && !sys_code_value.equals(null)) {
			if (flag.equalsIgnoreCase("Y")) {
				whr += "  upper(sys_code_value) like upper(:sys_code_value)";
				flag = "N";
			} else {
				whr += " and upper(sys_code_value) like upper(:sys_code_value)" ;
			}
		}
		if (!sys_code_desc.equals("") && !sys_code_desc.equals(null)) {
			if (flag.equalsIgnoreCase("Y")) {
				whr += "  upper(sys_code_desc) like upper(:sys_code_desc)";
				flag = "N";
			} else {
				whr += " and upper(sys_code_desc) like upper(:sys_code_desc)" ;
			}
		}
		List<Map<String, Object>> metadata = findDepartmentCriteriasforma(criterias,whr, sys_code,sys_code_value,sys_code_desc);
		Long countFiltered = getFilteredCountfo(criterias,whr,sys_code,sys_code_value,sys_code_desc);
		Long count = getTotalCountfo(whr,sys_code,sys_code_value,sys_code_desc);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> findDepartmentCriteriasforma(DatatablesCriterias criterias , String whr,String sys_code,String sys_code_value,String sys_code_desc) {
		StringBuilder queryBuilder = null;
		if(!whr.equals("")){
			whr = " Where " + whr;
		}
		queryBuilder = new StringBuilder("FROM Tb_Med_System_Code d " + whr);
		/**
		* Step 1: global and individual column filtering
		*/
		queryBuilder.append(getFilterQueryfo(criterias , queryBuilder));
		/**
		* Step 2: sorting
		*/
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			if(!queryBuilder.toString().contains("order by")){
				queryBuilder.append(" ORDER BY ");
			}
			else{
				queryBuilder.append(" , ");
			}
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if(columnDef.getName().contains("d.sys_code_desc")){
					 orderParams.add("d.sys_code_desc" + columnDef.getSortDirection());
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
		
		/**
		* Step 3: paging
		*/
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		if(!sys_code.equals("")) {
			q.setParameter("sys_code", sys_code+"%");
		}
		if(!sys_code_value.equals("")) {
			q.setParameter("sys_code_value", sys_code_value+"%");
		}
		if(!sys_code_desc.equals("")) {
			q.setParameter("sys_code_desc", sys_code_desc+"%");
		}
		List<Tb_Med_System_Code> list1 = (List<Tb_Med_System_Code>) q.list();
		tx.commit();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list1.size();i++) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();	
			columns.put("id", list1.get(i).getId());
			columns.put("sys_code", list1.get(i).getSys_code());
			columns.put("sys_code_value", list1.get(i).getSys_code_value());
			columns.put("sys_code_desc", list1.get(i).getSys_code_desc());
			columns.put("sys_code_value_desc", list1.get(i).getSys_code_value_desc());
			String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Part ?') ){editData("+ list1.get(i).getId() + ",'" +  list1.get(i).getSys_code() + "','" + list1.get(i).getSys_code_value() + "','" + list1.get(i).getSys_code_desc() + "','" + list1.get(i).getSys_code_value_desc() + "','" + list1.get(i).getStatus() + "')}else{ return false;}\"";
			String f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Part ?') ){deleteData(" + list1.get(i).getId() + ")}else{ return false;}\"";
			String f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			
			columns.put("action111", f + f1);
			list.add(columns);
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private  Long getFilteredCountfo(DatatablesCriterias criterias ,String whr,String sys_code,String sys_code_value,String sys_code_desc)
	{
		StringBuilder queryBuilder = null;
		if(!whr.equals("")){
			whr = " Where " + whr;
		}
		queryBuilder = new StringBuilder("SELECT d FROM Tb_Med_System_Code d "+ whr);
		
		queryBuilder.append(getFilterQueryfo(criterias,queryBuilder));
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		if(!sys_code.equals("")) {
			q.setParameter("sys_code", sys_code);
		}
		if(!sys_code_value.equals("")) {
			q.setParameter("sys_code_value", sys_code_value);
		}
		if(!sys_code_desc.equals("")) {
			q.setParameter("sys_code_desc", sys_code_desc);
		}
		List<Tb_Med_System_Code> list = (List<Tb_Med_System_Code>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	
	private Long getTotalCountfo( String whr,String sys_code,String sys_code_value,String sys_code_desc) {

	
		if(!whr.equals("")){
			whr = " Where " + whr;
		}
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM Tb_Med_System_Code d  "+ whr);
		if(!sys_code.equals("")) {
			q.setParameter("sys_code", sys_code);
		}
		if(!sys_code_value.equals("")) {
			q.setParameter("sys_code_value", sys_code_value);
		}
		if(!sys_code_desc.equals("")) {
			q.setParameter("sys_code_desc", sys_code_desc);
		}
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
   }
	
	
	private static StringBuilder getFilterQueryfo(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
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
					if(columnDef.getName().equalsIgnoreCase("id"))
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
		queryBuilder.append(" order by d.id desc");
		return queryBuilder;
	}
	

	public String setdeletesyrvcodeURL(int deleteid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from Tb_Med_System_Code where id=:id";
		int rowCount = session.createQuery( hqlUpdate ).setInteger("id",deleteid).executeUpdate();
	 	tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}
	}
}