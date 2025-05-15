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
import com.models.mnh.Tb_Med_Disease_Cause;
import com.persistance.util.HibernateUtil;

public class mstr_Icd_codeDAOImpl implements mstr_Icd_CodeDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
	// *******************Note::dublicate icd code*****************************************//

	
	public Long checkExitstingIcdCode(String icd_code,int id) {
	
		String hql="select count(id) from Tb_Med_Disease_Cause where icd_code=:d1 and id!=:id" ;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q= session.createQuery(hql);
		q.setParameter("d1", icd_code);
		q.setParameter("id", id);
		Long count_list =  (Long) q.uniqueResult();	
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
	
	
	// *******************Note::END*****************************************//
	
	// *******************Note::Search icd code*****************************************//
	
	public DataSet<Map<String, Object>> DatatablesCriteriasicdcode(DatatablesCriterias criterias , String icd_code,String icd_description) {
	String whr = "";	
	
	String flag = "Y";
	
	
	if (!icd_code.equals("") && !icd_code.equals(null)) {
		if (flag.equalsIgnoreCase("Y")) {
			whr += "  upper(icd_code) like upper(:icd_code)";
			flag = "N";
		} else {
			whr += " and upper(icd_code) like upper(:icd_code)";
		}
	}
	
	if (!icd_description.equals("") && !icd_description.equals(null)) {
		if (flag.equalsIgnoreCase("Y")) {
			whr += "  upper(icd_description) like upper(:icd_description)";
			flag = "N";
		} else {
			whr += " and upper(icd_description) like upper(:icd_description)" ;
		}
	}
	List<Map<String, Object>> metadata = findicdCriteriasforma(criterias,whr,icd_code,icd_description);
	Long countFiltered = getFilteredCountfo(criterias,whr,icd_code,icd_description);
	Long count = getTotalCountfo(whr,icd_code,icd_description);
	return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
   }
	

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> findicdCriteriasforma(DatatablesCriterias criterias , String whr,String icd_code,String icd_description) {
		StringBuilder queryBuilder = null;
		if(!whr.equals("")){
			whr = " Where " + whr;
		}
		queryBuilder = new StringBuilder("FROM Tb_Med_Disease_Cause d " + whr);
		
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
		
		if(!icd_code.equals("")) {
			q.setParameter("icd_code", icd_code+"%");
		}
		if(!icd_description.equals("")) {
			q.setParameter("icd_description", icd_description+"%");
		}
		
		List<Tb_Med_Disease_Cause> list1 = (List<Tb_Med_Disease_Cause>) q.list();
		tx.commit();
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<list1.size();i++) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();	
			columns.put("id", list1.get(i).getId());
			columns.put("icd_code", list1.get(i).getIcd_code());
			columns.put("icd_description", list1.get(i).getIcd_description());
			
			
			String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Part ?') ){editData("+ list1.get(i).getId() + ",'" +  list1.get(i).getIcd_code() + "',"
					+ "'" + list1.get(i).getIcd_description() + "','" +  list1.get(i).getDisease_mmr() + "',"
					+ "'" +  list1.get(i).getDisease_aso() + "','" +  list1.get(i).getDisease_principal() + "',"
					+ "'" +  list1.get(i).getDisease_type() + "','" +  list1.get(i).getBlock_description() + "',"
					+ "'" +  list1.get(i).getDisease_subtype() + "','" +  list1.get(i).getDisease_family() + "',"
					+ "'" +  list1.get(i).getDisease_children() + "','" +  list1.get(i).getDisease_cr_type() + "',"
					+ "'" +  list1.get(i).getDisease_cr_block_description() + "',"
					+ "'" +  list1.get(i).getShort_form() + "','" +  list1.get(i).getShort_desc() + "',"
							+ "'" +  list1.get(i).getType() + "')}else{ return false;}\"";
			String f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

			String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Part ?') ){deleteData(" + list1.get(i).getId() + ")}else{ return false;}\"";
			String f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			
			columns.put("action111", f + f1);
			
			
			list.add(columns);
			
			
		}
		sessionHQL.close();
		return list;
	}

	//////
	
	@SuppressWarnings("unchecked")
	private Long getFilteredCountfo(DatatablesCriterias criterias ,String whr,String icd_code,String icd_description) {
	StringBuilder queryBuilder = null;
	if(!whr.equals("")){
		whr = " Where " + whr;
	}
	queryBuilder = new StringBuilder("SELECT d FROM Tb_Med_Disease_Cause d "+ whr);
	
	queryBuilder.append(getFilterQueryfo(criterias,queryBuilder));
	
	Session session= HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery(queryBuilder.toString());
	if(!icd_code.equals("")) {
		q.setParameter("icd_code", icd_code);
	}
	if(!icd_description.equals("")) {
		q.setParameter("icd_description", icd_description);
	}
	
	List<Tb_Med_Disease_Cause> list = (List<Tb_Med_Disease_Cause>) q.list();
	tx.commit();
	session.close();
	return Long.parseLong(String.valueOf(list.size()));
}
	
	/////
	
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
		queryBuilder.append(" order by d.icd_code,d.icd_description ");
		return queryBuilder;
	}

	
	/////
	private Long getTotalCountfo(String whr,String icd_code,String icd_description) {
		if(!whr.equals("")){
			whr = " Where " + whr;
		}
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM Tb_Med_Disease_Cause d  "+ whr);
		if(!icd_code.equals("")) {
			q.setParameter("icd_code", icd_code);
		}
		if(!icd_description.equals("")) {
			q.setParameter("icd_description", icd_description);
		}
		
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
		
	// *******************Note::END*****************************************//
	
	
	
	
	///////////////////////
	
	
	
	public String updateicd_code(Tb_Med_Disease_Cause obj){
		 Session session1 = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		 try{
			 String hql = "update Tb_Med_Disease_Cause set icd_code=:icd_code,icd_description=:icd_description,disease_mmr=:disease_mmr,"
						+ "disease_aso=:disease_aso,disease_principal=:disease_principal,disease_type=:disease_type,block_description=:block_description,"
						+ "disease_subtype=:disease_subtype,disease_family=:disease_family,disease_children=:disease_children,"
						+ "disease_cr_type=:disease_cr_type,disease_cr_block_description=:disease_cr_block_description,short_form=:short_form,"
						+ "short_desc=:short_desc,type=:type,modified_on=:modified_on,modified_by=:modified_by where id=:id";	
			Query query = session1.createQuery(hql).setString("icd_code",obj.getIcd_code())
					     .setString("icd_description",obj.getIcd_description())
						 .setString("disease_mmr",obj.getDisease_mmr())
						 .setString("disease_aso",obj.getDisease_aso())
						 .setString("disease_principal",obj.getDisease_principal())
						 .setString("disease_type",obj.getDisease_type())
						 .setString("block_description",obj.getBlock_description())
						 .setString("disease_subtype",obj.getDisease_subtype())
						 .setString("disease_family",obj.getDisease_family())
						 .setString("disease_children",obj.getDisease_children())
						 .setString("disease_cr_type",obj.getDisease_cr_type())
						 .setString("disease_cr_block_description",obj.getDisease_cr_block_description())
						 .setString("short_form",obj.getShort_form())
						 .setString("short_desc",obj.getShort_desc())
						 .setString("type",obj.getType())
						 .setString("modified_by", obj.getModified_by())
						 .setTimestamp("modified_on", obj.getModified_on())
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
}

