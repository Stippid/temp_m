package com.dao.orbat;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;
import com.persistance.util.HibernateUtil;

public class LOVDAOImpl implements LOVDAO {
	
	// LOV SUS NO & UNIT NAME
	public DataSet<Miso_Orbat_Unt_Dtl> findLovOfUnitNameDatatablesCriteriasWithSusNo(DatatablesCriterias criterias,String target_unit_name_lov1,String target_sus_no_lov1) {
		String qry =" where status_sus_no = 'Active'";
    	if(!target_unit_name_lov1.equals("0") && !target_unit_name_lov1.equals("")){
			qry +=" and upper(unit_name) like :unit_name ";
    	}
    	if(!target_sus_no_lov1.equals("0") && !target_sus_no_lov1.equals("")){
			qry +=" and upper(sus_no) like :sus_no ";
    	}
		
		List<Miso_Orbat_Unt_Dtl> metadata = findDepartmentCriteriasmobUnit(criterias,qry,target_unit_name_lov1,target_sus_no_lov1);
		Long countFiltered = getFilteredCountmoUnit(criterias,qry,target_unit_name_lov1,target_sus_no_lov1);
		Long count = getTotalCountmoUnit(qry,target_unit_name_lov1,target_sus_no_lov1);
		return new DataSet<Miso_Orbat_Unt_Dtl>(metadata, count, countFiltered);
	}
	@SuppressWarnings("unchecked")
	private List<Miso_Orbat_Unt_Dtl> findDepartmentCriteriasmobUnit(DatatablesCriterias criterias ,String qry,String target_unit_name_lov1,String target_sus_no_lov1) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Miso_Orbat_Unt_Dtl d "+ qry);
		queryBuilder.append(getFilterQuerymoUnit(criterias , queryBuilder));
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
		if(!target_unit_name_lov1.equals("0") && !target_unit_name_lov1.equals("")){
			q.setParameter("unit_name", "%"+ target_unit_name_lov1.toUpperCase() +"%");
		}
    	if(!target_sus_no_lov1.equals("0") && !target_sus_no_lov1.equals("")){
    		q.setParameter("sus_no", "%"+ target_sus_no_lov1.toUpperCase() +"%");
		}
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
     	return list;
	}

	private static StringBuilder getFilterQuerymoUnit(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
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
		return queryBuilder;
	}
			
	private Long getTotalCountmoUnit(String qry,String target_unit_name_lov1,String target_sus_no_lov1) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM Miso_Orbat_Unt_Dtl d " + qry);
		if(!target_unit_name_lov1.equals("0") && !target_unit_name_lov1.equals("")){
			q.setParameter("unit_name", "%"+ target_unit_name_lov1.toUpperCase() +"%");
		}
    	if(!target_sus_no_lov1.equals("0") && !target_sus_no_lov1.equals("")){
    		q.setParameter("sus_no", "%"+ target_sus_no_lov1.toUpperCase() +"%");
		}
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	@SuppressWarnings("unchecked")
	private Long getFilteredCountmoUnit(DatatablesCriterias criterias ,String qry,String target_unit_name_lov1,String target_sus_no_lov1) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Miso_Orbat_Unt_Dtl d "+qry);
		queryBuilder.append(getFilterQuerymoUnit(criterias,queryBuilder));
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		if(!target_unit_name_lov1.equals("0") && !target_unit_name_lov1.equals("")){
			q.setParameter("unit_name", "%"+ target_unit_name_lov1.toUpperCase() +"%");
		}
    	if(!target_sus_no_lov1.equals("0") && !target_sus_no_lov1.equals("")){
    		q.setParameter("sus_no", "%"+ target_sus_no_lov1.toUpperCase() +"%");
		}
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
}