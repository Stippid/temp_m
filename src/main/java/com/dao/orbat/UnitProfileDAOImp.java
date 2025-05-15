package com.dao.orbat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.dao.Assets.interUnitTransf_DAO;
import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Relief_Prgm;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Cii_Unt_Dtl;
import com.models.Tb_Miso_Orbat_Mast_Fmn;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

import ch.qos.logback.core.joran.conditional.IfAction;

public class UnitProfileDAOImp implements UnitProfileDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Tbl_CodesForm> getFormation() {
		List<Tbl_CodesForm> code_list = new ArrayList<Tbl_CodesForm>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			sql1 = "select a.sus_no, a.unit_name, b.formation_code, b.level_in_hierarchy,a.form_code_control from tb_miso_orbat_unt_dtl a, tb_miso_orbat_codesform b where a.sus_no=b.sus_no and upper(b.level_in_hierarchy) in ('COMMAND','CORPS','DIVISION','BRIGADE') and UPPER(a.STATUS_SUS_NO) = 'ACTIVE' order by a.unit_name";
			ResultSet rs1 = stmt.executeQuery(sql1);
			for (int i = 0; rs1.next(); i++) {
				Tbl_CodesForm code = new Tbl_CodesForm();
				String sus_no = rs1.getString("sus_no");
				String unit_name = rs1.getString("unit_name");
				String formation_code = rs1.getString("formation_code");
				String level_in_hierarchy = rs1.getString("level_in_hierarchy");
				String form_code_control = rs1.getString("form_code_control");

				code.setSus_no(sus_no);
				code.setUnit_identifier(unit_name);
				code.setFormation_code(form_code_control);
				code.setLevel_in_hierarchy(level_in_hierarchy);
				code_list.add(code);
			}
			rs1.close();
			stmt.close();
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
		return code_list;
	}

	public DataSet<Map<String, Object>> findUnitProfileReportWithDatatablesCriterias(DatatablesCriterias criterias,
			String status12, String unit_name12, String sus_no12, String parent_arm12, String fmn12, String arm_name12,
			String loc12, String type_force12, String ct_part_i_ii12, String comm_depart_date12,
			String compltn_arrv_date12) {

		Date to_date1 = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String to_date = dateFormat.format(to_date1);

		Date currentDate = null;
		Date from = null;
		Date to = null;

		String qry = " where sus_no in (select sus_no from Tbl_CodesForm)";

		if (!status12.equals("0") && !status12.equals("")) {
			qry += " and status_sus_no = :status_sus_no ";
		}

		if (!unit_name12.equals("")) {
			qry += " and upper(unit_name) like :unit_name ";
		}

		if (!sus_no12.equals("")) {
			qry += " and upper(sus_no) like :sus_no ";
		}

		if (!parent_arm12.equals("0") && !parent_arm12.equals("")) {
			qry += " and sus_no like :sus_no ";
		}
		if (!fmn12.equals("0") && !fmn12.equals("")) {
			qry += " and form_code_control like :form_code_control ";
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

			String armList[] = arm_name12.split(",");
			String arm = "";
			for (int i = 0; i < armList.length; i++) {
				if (arm.equals("")) {
					arm = "'" + armList[i] + "'";
				} else {
					arm = arm + ",'" + armList[i] + "'";
				}
			}
			qry += " and arm_code in (" + arm + ") ";
		}
		if (!loc12.equals("0") && !loc12.equals("")) {
			qry += " and code = :code ";
		}

		if (!type_force12.equals("")) {
			qry += " and type_force = :type_force ";
		}
		if (!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")) {
			qry += " and ct_part_i_ii = :ct_part_i_ii ";
		}

		if (!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")) {
			qry += " and creation_on between :from and :to_date";
			try {
				from = dateFormat.parse(comm_depart_date12);
				currentDate = dateFormat.parse(to_date);
				to = null;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			qry += " and creation_on = :to ";
			try {
				to = dateFormat.parse(compltn_arrv_date12);
				currentDate = null;
				from = null;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			try {
				from = dateFormat.parse(comm_depart_date12);
				to = dateFormat.parse(compltn_arrv_date12);
				currentDate = null;
			} catch (ParseException e) {

			}
			qry += " and creation_on between :from and :to";
		}
		List<Map<String, Object>> metadata = findDepartmentCriteriasmob(criterias, qry, status12, unit_name12, sus_no12,
				parent_arm12, fmn12, arm_name12, loc12, type_force12, ct_part_i_ii12, comm_depart_date12,
				compltn_arrv_date12, currentDate, from, to);
		Long countFiltered = getFilteredCountmo(criterias, qry, status12, unit_name12, sus_no12, parent_arm12, fmn12,
				arm_name12, loc12, type_force12, ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12, currentDate,
				from, to);
		Long count = getTotalCountmo(qry, status12, unit_name12, sus_no12, parent_arm12, fmn12, arm_name12, loc12,
				type_force12, ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12, currentDate, from, to);

		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> findDepartmentCriteriasmob(DatatablesCriterias criterias, String qry,
			String status12, String unit_name12, String sus_no12, String parent_arm12, String fmn12, String arm_name12,
			String loc12, String type_force12, String ct_part_i_ii12, String comm_depart_date12,
			String compltn_arrv_date12, Date currentDate, Date from, Date to) {
		StringBuilder queryBuilder = null;

		if (qry.equals("")) {
			queryBuilder = new StringBuilder(
					"SELECT d.id,d.sus_no,d.unit_name FROM Miso_Orbat_Unt_Dtl d order by id desc");
		} else {
			queryBuilder = new StringBuilder("SELECT d.id,d.sus_no,d.unit_name FROM Miso_Orbat_Unt_Dtl d " + qry + " ");
		}
		queryBuilder.append(getFilterQuerymo(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("id")) {
					String st = " ORDER BY";
					int i = queryBuilder.indexOf(st);
					if (i != -1) {
						queryBuilder.delete(i, i + st.length());
					}
				} else if (columnDef.getName().contains("hodProfile.fullName")) {
					orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
				} else {
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

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		if (!status12.equals("0") && !status12.equals("")) {
			q.setParameter("status_sus_no", status12);
		}
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name", "%" + unit_name12.toUpperCase() + "%");
		}
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no", sus_no12.toUpperCase() + "%");
		}
		if (!parent_arm12.equals("0") && !parent_arm12.equals("")) {
			q.setParameter("sus_no", parent_arm12 + "%");
		}
		if (!fmn12.equals("0") && !fmn12.equals("")) {
			q.setParameter("form_code_control", fmn12);
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}
		if (!loc12.equals("0") && !loc12.equals("")) {
			q.setParameter("code", loc12);
		}

		if (!type_force12.equals("")) {
			q.setParameter("type_force", type_force12);
		}
		if (!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")) {
			q.setParameter("ct_part_i_ii", ct_part_i_ii12);
		}

		if (!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to_date", currentDate);
		}
		if (comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("to", to);
		}
		if (!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to", to);
		}
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();

		List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
		for (Object[] listObject : list) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("id", listObject[0]);
			columns.put("sus_no", listObject[1]);
			columns.put("unit_name", listObject[2]);
			l1.add(columns);
		}
		return l1;
	}

	private static StringBuilder getFilterQuerymo(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();

		/**
		 * Step 1.1: global filtering
		 */
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("id")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
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

	private Long getTotalCountmo(String qry, String status12, String unit_name12, String sus_no12, String parent_arm12,
			String fmn12, String arm_name12, String loc12, String type_force12, String ct_part_i_ii12,
			String comm_depart_date12, String compltn_arrv_date12, Date currentDate, Date from, Date to) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		q = session.createQuery("SELECT COUNT(d) FROM Miso_Orbat_Unt_Dtl d  " + qry);
		if (!status12.equals("0") && !status12.equals("")) {
			q.setParameter("status_sus_no", status12);
		}
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name", "%" + unit_name12.toUpperCase() + "%");
		}
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no", sus_no12.toUpperCase() + "%");
		}
		if (!parent_arm12.equals("0") && !parent_arm12.equals("")) {
			q.setParameter("sus_no", parent_arm12 + "%");
		}
		if (!fmn12.equals("0") && !fmn12.equals("")) {
			q.setParameter("form_code_control", fmn12);
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}
		if (!loc12.equals("0") && !loc12.equals("")) {
			q.setParameter("code", loc12);
		}

		if (!type_force12.equals("")) {
			q.setParameter("type_force", type_force12);
		}
		if (!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")) {
			q.setParameter("ct_part_i_ii", ct_part_i_ii12);
		}

		if (!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to_date", currentDate);
		}
		if (comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("to", to);
		}
		if (!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to", to);
		}
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountmo(DatatablesCriterias criterias, String qry, String status12, String unit_name12,
			String sus_no12, String parent_arm12, String fmn12, String arm_name12, String loc12, String type_force12,
			String ct_part_i_ii12, String comm_depart_date12, String compltn_arrv_date12, Date currentDate, Date from,
			Date to) {
		StringBuilder queryBuilder = null;

		queryBuilder = new StringBuilder("SELECT d FROM Miso_Orbat_Unt_Dtl d  " + qry + "");
		queryBuilder.append(getFilterQuerymo(criterias, queryBuilder));
		queryBuilder.append("  Order by d.id desc ");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		if (!status12.equals("0") && !status12.equals("")) {
			q.setParameter("status_sus_no", status12);
		}
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name", "%" + unit_name12.toUpperCase() + "%");
		}
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no", sus_no12.toUpperCase() + "%");
		}
		if (!parent_arm12.equals("0") && !parent_arm12.equals("")) {
			q.setParameter("sus_no", parent_arm12 + "%");
		}
		if (!fmn12.equals("0") && !fmn12.equals("")) {
			q.setParameter("form_code_control", fmn12);
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}
		if (!loc12.equals("0") && !loc12.equals("")) {
			q.setParameter("code", loc12);
		}

		if (!type_force12.equals("")) {
			q.setParameter("type_force", type_force12);
		}
		if (!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")) {
			q.setParameter("ct_part_i_ii", ct_part_i_ii12);
		}

		if (!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to_date", currentDate);
		}
		if (comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("to", to);
		}
		if (!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")) {
			q.setDate("from", from);
			q.setDate("to", to);
		}

		/**
		 * paging
		 */
		/*
		 * q.setFirstResult(criterias.getDisplayStart());
		 * q.setMaxResults(criterias.getDisplaySize());
		 */
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	public Miso_Orbat_Unt_Dtl getMiso_Orbat_Unt_DtlByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Miso_Orbat_Unt_Dtl where id=:id");
		q.setParameter("id", id);
		Miso_Orbat_Unt_Dtl list = (Miso_Orbat_Unt_Dtl) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public List<Miso_Orbat_Shdul_Detl> getMiso_Orbat_Shdul_DetlByid(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Miso_Orbat_Shdul_Detl where letter_id=:letter_id");
		q.setParameter("letter_id", id);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Shdul_Detl> list = (List<Miso_Orbat_Shdul_Detl>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Tbl_CodesForm> getFormationDetailsFromSusNo(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select level_in_hierarchy from Tbl_CodesForm where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// 28.10.2024

	public String setApprovedCII(int appid, String username) {
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();
			String hqlUpdate = "update Tb_Miso_Orbat_Cii_Unt_Dtl c set c.approved_rejected_on=:approved_rejected_on , c.approved_rejected_by=:approved_rejected_by , c.status_sus_no=:status_sus_no where c.id =:id and c.status_sus_no='Pending'";
			sessionHQL.createQuery(hqlUpdate).setDate("approved_rejected_on", new Date())
					.setString("approved_rejected_by", username).setString("status_sus_no", "Active")
					.setInteger("id", appid).executeUpdate();
			tx.commit();
			return "Data Approved Successfully";
		} catch (RuntimeException e) {
			tx.rollback();
			return "Data not Approved";
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public String setRejectCII(int appid, String username) {
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();
			String hqlUpdate = "update Tb_Miso_Orbat_Cii_Unt_Dtl c set c.approved_rejected_on=:approved_rejected_on , c.approved_rejected_by=:approved_rejected_by , c.status_sus_no = :status_sus_no where c.id = :id and c.status_sus_no != 'Reject'";
			sessionHQL.createQuery(hqlUpdate).setDate("approved_rejected_on", new Date())
					.setString("approved_rejected_by", username).setString("status_sus_no", "Reject")
					.setInteger("id", appid).executeUpdate();
			tx.commit();
			return "Data Rejected Successfully";
		} catch (RuntimeException e) {
			tx.rollback();
			return "Data not Rejected";
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public String setDeleteCII(int appid) {
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();
			String hql = "delete from Tb_Miso_Orbat_Cii_Unt_Dtl where id =:id and (status_sus_no='Pending' or status_sus_no='Reject')";
			Query query = sessionHQL.createQuery(hql);
			query.setInteger("id", appid);
			query.executeUpdate();
			tx.commit();
			return "Data Deleted Successfully";
		} catch (RuntimeException e) {
			tx.rollback();
			return "Data not Deleted";
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public Tb_Miso_Orbat_Cii_Unt_Dtl getTb_Miso_Orbat_Cii_Unt_DtlByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tb_Miso_Orbat_Cii_Unt_Dtl where id=:id");
		q.setParameter("id", id);
		Tb_Miso_Orbat_Cii_Unt_Dtl list = (Tb_Miso_Orbat_Cii_Unt_Dtl) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateCII(Tb_Miso_Orbat_Cii_Unt_Dtl artAttValues, String username, String sus_no, String unit_name,
			String arm_code, String fmn_code, String loc_code, String nrs_code, String fmn_name, Date from_date,
			Date to_date, String authority) {
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();
			String hql = "update Tb_Miso_Orbat_Cii_Unt_Dtl set sus_no=:sus_no ,unit_name=:unit_name , loc_code=:loc_code ,nrs_code=:nrs_code,fmn_code=:fmn_code ,arm_code=:arm_code,fmn_name=:fmn_name,from_date=:from_date,to_date=:to_date ,modified_by=:modified_by ,modified_on=:modified_on,authority=:authority ,status_sus_no='Pending' where id=:id";
			Query query = sessionHQL.createQuery(hql).setString("sus_no", sus_no).setString("unit_name", unit_name)
					.setString("arm_code", arm_code).setString("fmn_name", fmn_name).setString("fmn_code", fmn_code)
					.setString("loc_code", loc_code).setString("nrs_code", nrs_code).setDate("modified_on", new Date())
					.setDate("from_date", from_date).setDate("to_date", to_date).setString("authority", authority)
					.setString("modified_by", username).setInteger("id", artAttValues.getId());
			query.executeUpdate();
			tx.commit();
			return "Data Updated Successfully";
		} catch (RuntimeException e) {
			tx.rollback();
			return "Data not Updated";
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	// view
	public DataSet<Map<String, Object>> findUnitProfileReportWithDatatablesCriteriasCii(DatatablesCriterias criterias,
			String status12, String unit_name12, String sus_no12, String parent_arm12, String fmn12, String arm_name12,
			String loc12, String type_force12, String ct_part_i_ii12, String comm_depart_date12,
			String compltn_arrv_date12, String fmn_name12) {

		Date to_date1 = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String to_date = dateFormat.format(to_date1);

		Date currentDate = null;
		Date from = null;
		Date to = null;

		String qry = "and d.sus_no in (select sus_no from Tbl_CodesForm)";

		/*
		 * if(!status12.equals("0") && !status12.equals("")){ qry
		 * +=" and d.status_sus_no = :status_sus_no "; }
		 */

		if (!unit_name12.equals("")) {
			qry += " and upper(d.unit_name) like :unit_name1 ";
		}
		System.err.println("value of unit name " + unit_name12);
		if (!sus_no12.equals("")) {
			qry += " and upper(d.sus_no) like :sus_no1 ";
		}
		System.err.println("value of sus no " + sus_no12);
		/*
		 * if(!parent_arm12.equals("0") && !parent_arm12.equals("")){ qry
		 * +=" and d.sus_no like :sus_no "; }
		 */
		/*
		 * if(!fmn12.equals("0") && !fmn12.equals("")){ qry
		 * +=" and form_code_control like :form_code_control "; }
		 */

		if (!fmn_name12.equals("0") && !fmn_name12.equals("")) {
			qry += " and upper(d.fmn_name) like :fmn_name1 ";
		}
		System.err.println("value of sus no " + fmn_name12);
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

			String armList[] = arm_name12.split(",");
			String arm = "";
			for (int i = 0; i < armList.length; i++) {
				if (arm.equals("")) {
					arm = "'" + armList[i] + "'";
				} else {
					arm = arm + ",'" + armList[i] + "'";
				}
			}
			qry += " and d.arm_code in (" + arm + ") ";
		}

		System.err.println("value of sus no " + arm_name12);
		/*
		 * if(!loc12.equals("0") && !loc12.equals("")){ qry +=" and d.code = :code "; }
		 */

		/*
		 * if(!type_force12.equals("")){ qry +=" and d.type_force = :type_force "; }
		 * if(!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")){ qry
		 * +=" and d.ct_part_i_ii = :ct_part_i_ii "; }
		 */

		/*
		 * if(!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")){ qry
		 * +=" and d.created_on between :from and :to_date"; try { from =
		 * dateFormat.parse(comm_depart_date12); currentDate =
		 * dateFormat.parse(to_date); to = null; } catch (ParseException e) {
		 * e.printStackTrace(); } } if(comm_depart_date12.equals("") &&
		 * !compltn_arrv_date12.equals("")){ qry +=" and d.created_on = :to "; try { to
		 * = dateFormat.parse(compltn_arrv_date12); currentDate = null; from = null; }
		 * catch (ParseException e) { e.printStackTrace(); } }
		 * if(!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")){ try {
		 * from = dateFormat.parse(comm_depart_date12); to =
		 * dateFormat.parse(compltn_arrv_date12); currentDate = null; } catch
		 * (ParseException e) {
		 * 
		 * } qry +=" and d.created_on between :from and :to"; }
		 */
		List<Map<String, Object>> metadata = findDepartmentCriteriasmob_cii(criterias, qry, status12, unit_name12,
				sus_no12, parent_arm12, fmn12, arm_name12, loc12, type_force12, ct_part_i_ii12, comm_depart_date12,
				compltn_arrv_date12, currentDate, from, to, fmn_name12);
		Long countFiltered = getFilteredCountmo_cii(criterias, qry, status12, unit_name12, sus_no12, parent_arm12,
				fmn12, arm_name12, loc12, type_force12, ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12,
				currentDate, from, to, fmn_name12);
		Long count = getTotalCountmo_cii(qry, status12, unit_name12, sus_no12, parent_arm12, fmn12, arm_name12, loc12,
				type_force12, ct_part_i_ii12, comm_depart_date12, compltn_arrv_date12, currentDate, from, to,
				fmn_name12);

		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> findDepartmentCriteriasmob_cii(DatatablesCriterias criterias, String qry,
			String status12, String unit_name12, String sus_no12, String parent_arm12, String fmn12, String arm_name12,
			String loc12, String type_force12, String ct_part_i_ii12, String comm_depart_date12,
			String compltn_arrv_date12, Date currentDate, Date from, Date to, String fmn_name12) {
		StringBuilder queryBuilder = null;

		if (status12.equals("Active")) {
			queryBuilder = new StringBuilder("SELECT d.id,d.sus_no,d.unit_name FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ "Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Active' " + qry + "");
		} else {
			queryBuilder = new StringBuilder("SELECT d.id,d.sus_no,d.unit_name FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ " Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Inactive' " + qry + "");
		}
		queryBuilder.append(getFilterQuerymo_cii(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY d.id DESC");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("id")) {
					String st = " ORDER BY";
					int i = queryBuilder.indexOf(st);
					/*
					 * if (i != -1) { queryBuilder.delete(i, i + st.length()); }
					 */
				} else if (columnDef.getName().contains("hodProfile.fullName")) {
					orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
				} else {
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

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		System.err.println("VIEW UNIT CII " + q);
		/*
		 * if(!status12.equals("0") && !status12.equals("")){
		 * q.setParameter("status_sus_no", status12); }
		 */

		if (!fmn_name12.equals("")) {
			q.setParameter("fmn_name1", "%" + fmn_name12.toUpperCase() + "%");
		}
		System.err.println("value of fmn name in findDepartmentCriteriasmob_cii " + fmn_name12);
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name1", "%" + unit_name12.toUpperCase() + "%");
		}
		System.err.println("value of unit name in findDepartmentCriteriasmob_cii " + unit_name12);
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no1", sus_no12.toUpperCase() + "%");
		}
		System.err.println("value of sus no in findDepartmentCriteriasmob_cii " + sus_no12);

		/*
		 * if(!parent_arm12.equals("0") && !parent_arm12.equals("")){
		 * q.setParameter("sus_no", parent_arm12 +"%"); }
		 */
		/*
		 * if(!fmn12.equals("0") && !fmn12.equals("")){
		 * q.setParameter("form_code_control", fmn12); }
		 */
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}

		System.err.println("value of arm name in findDepartmentCriteriasmob_cii " + fmn_name12);

		/*
		 * if(!loc12.equals("0") && !loc12.equals("")){ q.setParameter("code", loc12); }
		 */

		/*
		 * if(!type_force12.equals("")){ q.setParameter("type_force", type_force12); }
		 * if(!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")){
		 * q.setParameter("ct_part_i_ii", ct_part_i_ii12); }
		 */

		/*
		 * if(!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")){
		 * q.setDate("from", from); q.setDate("to_date", currentDate); }
		 * if(comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")){
		 * q.setDate("to", to); } if(!comm_depart_date12.equals("") &&
		 * !compltn_arrv_date12.equals("")){ q.setDate("from", from); q.setDate("to",
		 * to); }
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Object[]> list = (List<Object[]>) q.list();
		System.err.println("data output list " + list);
		tx.commit();
		session.close();

		List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
		for (Object[] listObject : list) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("id", listObject[0]);
			columns.put("sus_no", listObject[1]);
			columns.put("unit_name", listObject[2]);
			l1.add(columns);
		}
		return l1;
	}

	private static StringBuilder getFilterQuerymo_cii(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();

		/**
		 * Step 1.1: global filtering
		 */
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" AND ( ");
			}
			/*
			 * else{ queryBuilder.append(" AND ("); }
			 */
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("id")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
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

	private Long getTotalCountmo_cii(String qry, String status12, String unit_name12, String sus_no12,
			String parent_arm12, String fmn12, String arm_name12, String loc12, String type_force12,
			String ct_part_i_ii12, String comm_depart_date12, String compltn_arrv_date12, Date currentDate, Date from,
			Date to, String fmn_name12) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		/*
		 * System.out.println("getTotalCountmo_cii qry " + qry); if(qry.contains("and"))
		 * { qry = "where d.sus_no in (select sus_no from Tbl_CodesForm)"; }
		 * 
		 * 
		 * q = session.createQuery("SELECT COUNT(d) FROM Tb_Miso_Orbat_Cii_Unt_Dtl d  "
		 * + qry);
		 */
		StringBuilder queryBuilder = null;

		if (status12.equals("Active")) {
			q = session.createQuery("SELECT COUNT(d) FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ "Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Active' " + qry);
		} else {
			q = session.createQuery("SELECT COUNT(d) FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ " Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Inactive' " + qry);
		}

		/*
		 * if(!status12.equals("0") && !status12.equals("")){
		 * q.setParameter("status_sus_no", status12); }
		 */
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name1", "%" + unit_name12.toUpperCase() + "%");
		}
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no1", sus_no12.toUpperCase() + "%");
		}
		/*
		 * if(!parent_arm12.equals("0") && !parent_arm12.equals("")){
		 * q.setParameter("sus_no", parent_arm12 +"%"); }
		 */
		if (!fmn_name12.equals("0") && !fmn_name12.equals("")) {
			q.setParameter("fmn_name1", fmn12);
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}
		/*
		 * if(!loc12.equals("0") && !loc12.equals("")){ q.setParameter("code", loc12); }
		 */

		/*
		 * if(!type_force12.equals("")){ q.setParameter("type_force", type_force12); }
		 * if(!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")){
		 * q.setParameter("ct_part_i_ii", ct_part_i_ii12); }
		 */

		/*
		 * if(!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")){
		 * q.setDate("from", from); q.setDate("to_date", currentDate); }
		 * if(comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")){
		 * q.setDate("to", to); } if(!comm_depart_date12.equals("") &&
		 * !compltn_arrv_date12.equals("")){ q.setDate("from", from); q.setDate("to",
		 * to); }
		 */
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountmo_cii(DatatablesCriterias criterias, String qry, String status12, String unit_name12,
			String sus_no12, String parent_arm12, String fmn12, String arm_name12, String loc12, String type_force12,
			String ct_part_i_ii12, String comm_depart_date12, String compltn_arrv_date12, Date currentDate, Date from,
			Date to, String fmn_name12) {
		StringBuilder queryBuilder = null;

		/*
		 * System.out.println("getFilteredCountmo_cii qry " + qry);
		 * if(qry.contains("and")) { qry =
		 * "where d.sus_no in (select sus_no from Tbl_CodesForm)"; }
		 */

		// queryBuilder = new StringBuilder("SELECT d FROM Tb_Miso_Orbat_Cii_Unt_Dtl d
		// "+qry +"");
		// StringBuilder queryBuilder = null;

		if (status12.equals("Active")) {
			queryBuilder = new StringBuilder("SELECT COUNT(d) FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ "Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Active' " + qry + "");
		} else {
			queryBuilder = new StringBuilder("SELECT COUNT(d) FROM Tb_Miso_Orbat_Cii_Unt_Dtl d, "
					+ " Miso_Orbat_Unt_Dtl b where d.sus_no=b.sus_no and d.status_sus_no='Active'  \r\n"
					+ "and b.status_sus_no='Inactive' " + qry + "");
		}
		queryBuilder.append(getFilterQuerymo_cii(criterias, queryBuilder));
		// queryBuilder.append(" ORDER by d.id desc ");
		System.out.println("queryBuilder value " + queryBuilder);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		/*
		 * if(!status12.equals("0") && !status12.equals("")){
		 * q.setParameter("status_sus_no", status12); }
		 */
		if (!unit_name12.equals("")) {
			q.setParameter("unit_name1", "%" + unit_name12.toUpperCase() + "%");
		}
		if (!sus_no12.equals("")) {
			q.setParameter("sus_no1", sus_no12.toUpperCase() + "%");
		}
		/*
		 * if(!parent_arm12.equals("0") && !parent_arm12.equals("")){
		 * q.setParameter("sus_no", parent_arm12 +"%"); }
		 */
		if (!fmn_name12.equals("0") && !fmn_name12.equals("")) {
			q.setParameter("fmn_name1", fmn12);
		}
		if (!arm_name12.equals("0") && !arm_name12.equals("")) {

		}
		/*
		 * if(!loc12.equals("0") && !loc12.equals("")){ q.setParameter("code", loc12); }
		 */

		/*
		 * if(!type_force12.equals("")){ q.setParameter("type_force", type_force12); }
		 */
		/*
		 * if(!ct_part_i_ii12.equals("0") && !ct_part_i_ii12.equals("")){
		 * q.setParameter("ct_part_i_ii", ct_part_i_ii12); }
		 */

		/*
		 * if(!comm_depart_date12.equals("") && compltn_arrv_date12.equals("")){
		 * q.setDate("from", from); q.setDate("to_date", currentDate); }
		 * if(comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")){
		 * q.setDate("to", to); }
		 */
		/*
		 * if(!comm_depart_date12.equals("") && !compltn_arrv_date12.equals("")){
		 * q.setDate("from", from); q.setDate("to", to); }
		 */

		/**
		 * paging
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	public Tb_Miso_Orbat_Mast_Fmn getTb_Miso_Orbat_Cii_Unt_DtlByFmn_code(String fmn_code) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tb_Miso_Orbat_Mast_Fmn where fmn_code=:fmn_code");
		q.setParameter("fmn_code", fmn_code);
		Tb_Miso_Orbat_Mast_Fmn list = (Tb_Miso_Orbat_Mast_Fmn) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

//NEW RAJ 14.10.2024

	@Override
	public List<String> findAllLevel1Options() {
		String sql = "SELECT DISTINCT level1 FROM tb_miso_orbat_mast_fmn WHERE status_record= '1' and TRIM(level1) != '' ";
		return executeQueryForList(sql, null);
	}

	@Override
	public List<String> findLevel2OptionsByLevel1(String level1) {
		String sql = "SELECT DISTINCT level2 FROM tb_miso_orbat_mast_fmn WHERE level1 = ? and status_record = '1' and TRIM(level2) != ''";
		return executeQueryForList(sql, level1);
	}

	@Override
	public List<String> findLevel3OptionsByLevel2(String level2) {
		String sql = "SELECT DISTINCT level3 FROM tb_miso_orbat_mast_fmn WHERE level2 = ? and status_record = '1' and TRIM(level3) != ''";
		return executeQueryForList(sql, level2);
	}

	@Override
	public List<String> findLevel4OptionsByLevel3(String level3) {
		String sql = "SELECT DISTINCT level4 FROM tb_miso_orbat_mast_fmn WHERE level3 = ? and status_record = '1' and TRIM(level4) != '' ";
		return executeQueryForList(sql, level3);
	}

	@Override
	public List<String> findLevel5OptionsByLevel4(String level4) {
		String sql = "SELECT DISTINCT level5 FROM tb_miso_orbat_mast_fmn WHERE level4 = ? and status_record = '1' and TRIM(level5) != '' ";
		return executeQueryForList(sql, level4);
	}

	@Override
	public List<String> findLevel6OptionsByLevel5(String level5) {
		String sql = "SELECT DISTINCT level6 FROM tb_miso_orbat_mast_fmn WHERE level5 = ? and status_record = '1' and TRIM(level6) != '' ";
		return executeQueryForList(sql, level5);
	}

	@Override
	public List<String> findLevel7OptionsByLevel6(String level6) {
		String sql = "SELECT DISTINCT level7 FROM tb_miso_orbat_mast_fmn WHERE level6 = ? and status_record = '1' and TRIM(level7) != '' ";
		return executeQueryForList(sql, level6);
	}

	@Override
	public List<String> findLevel8OptionsByLevel7(String level7) {
		String sql = "SELECT DISTINCT level8 FROM tb_miso_orbat_mast_fmn WHERE level7 = ? and status_record = '1' and TRIM(level8) != '' ";
		return executeQueryForList(sql, level7);
	}

	@Override
	public List<String> findLevel9OptionsByLevel8(String level8) {
		String sql = "SELECT DISTINCT level9 FROM tb_miso_orbat_mast_fmn WHERE level8 = ? and status_record = '1' and TRIM(level9) != '' ";
		return executeQueryForList(sql, level8);
	}

	@Override
	public List<String> findLevel10OptionsByLevel9(String level9) {
		String sql = "SELECT DISTINCT level10 FROM tb_miso_orbat_mast_fmn WHERE level9 = ? and status_record = '1' and TRIM(level10) != '' ";
		return executeQueryForList(sql, level9);
	}

// Repeat for levels 4 to 10

	private List<String> executeQueryForList(String sql, String param) {
		List<String> resultList = new ArrayList<>();

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			if (param != null) {
				ps.setString(1, param);
			}
			System.out.println("sql qury for updating levels" + sql + param);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				resultList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	/*public List<Map<String, Object>> GetSearch_comd_and_cont_list(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status) {

		//String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleName = sessionUserId.getAttribute("role").toString();	
		String roleType = sessionUserId.getAttribute("roleType").toString();		
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		Connection conn = null;
		String q = "";
		String qry = "";

		qry = "c.cmd_status = ?";
		
		if(roleName.equals("comd_cont_deo_sd9") || roleName.equals("comd_cont_app_sd9") ){
			qry = "c.sd9_status = ?";
		}
		
		if(roleName.equals("o_sd4_deo") || roleName.equals("o_sd4_app") ){
			qry = "c.sd4_status = ?";
		}
		if(roleName.equals("comd_cont_deo_sd5") || roleName.equals("comd_cont_app_sd5") ){
			qry = "c.sd5_status = ?";
		}
		
		if(roleName.equals("comd_cont_deo_mo") || roleName.equals("comd_cont_app_mo") ){
			qry = "c.mo_status = ?";
		}
		if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
			qry += "and c.unit_sus_no = ?";
		}
		
		if(roleSubAccess.equals("Command")){
			qry += "and c.created_cmd_susno = ?";
		}
		
	
		
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT DISTINCT\r\n"
					+ "	c.id,\r\n"
					+ "	c.auth_letter_no,\r\n"
					+ "COALESCE(CASE\r\n"
					+ "    WHEN c.cmd_status = 3 THEN c.remarks\r\n"
					+ "    ELSE c.current_status\r\n"
					+ "END, c.current_status) AS status,\r\n"
					+ "	u.unit_name AS unit_name,\r\n"
					+ "	uo.unit_name AS ops,\r\n"
					+ "	ui.unit_name AS is_name,\r\n"
					+ "	um.unit_name AS mil,\r\n"
					+ "	ut.unit_name AS techcont,\r\n"
					+ "	ud.unit_name AS discp,\r\n"
					+ "	ula.unit_name AS local_adm,\r\n"
					+ "	(select distinct code_value from tb_miso_orbat_code where code = c.loc_code and status_record = '1' )as loc_code\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON c.unit_sus_no=u.sus_no\r\n"
					+ "	AND u.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl uo ON c.ops_sus_no=uo.sus_no\r\n"
					+ "	AND uo.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ui ON c.is_sus_no=ui.sus_no\r\n"
					+ "	AND ui.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl um ON c.mil_sus_no=um.sus_no\r\n"
					+ "	AND um.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ut ON c.techcont_sus_no=ut.sus_no\r\n"
					+ "	AND ut.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ud ON c.discp_sus_no=ud.sus_no\r\n"
					+ "	AND ud.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ula ON c.local_adm_sus_no=ula.sus_no\r\n"
					+ "	AND ula.status_sus_no='Active'    where " + qry + " " + "   limit " + pageL + " OFFSET "
					+ startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(status));
			int t=2;
			if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
				stmt.setString(t, unit_sus_no);
				t=t+1;
			}
			
			if(roleSubAccess.equals("Command")){
				stmt.setString(t, roleSusNo);
				t=t+1;
			}
			
			
			

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String action = "";
				String f = "";			
				String f1 = "";
				String f2 = "";	
				String f3 = "";
			
				
				String viewAction = "onclick=\"viewComdAndContDetails(" + rs.getString("id") + ")\"";
				 f = "<i class='action_icons action_view eye-icon' " + viewAction + " title='View Comd And Cont Inst Data'></i>";

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>"; 
 
        	        String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("
			     + rs.getString("id") + ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
			
				if (status.equals("0")) {
					if (roleType.equals("DEO")) {
						action += f;
						action += f1;
					} else if (roleType.equals("APP")) {
						action += f;					
						action += f2;
						action += f3;

					}
				} else if (status.equals("1")) {
					action += f;
				} else if (status.equals("3")) {
					action += f;
					action += f1;
				}

				columns.put("action", action);
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
	}*/
	
	public List<Map<String, Object>> GetSearch_comd_and_cont_list(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status) {

		//String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleName = sessionUserId.getAttribute("role").toString();	
		String roleType = sessionUserId.getAttribute("roleType").toString();		
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		Connection conn = null;
		String q = "";
		String qry = "";

		if(!Search.equals("")) { // for Input Filter
			qry ="( upper(c.auth_letter_no) like ? or upper(u.unit_name) like ? or upper(loc_code) like ? "
					+ " or upper(uo.unit_name) like ? or upper(ui.unit_name) like ? or upper(um.unit_name) like ? "
					+ "or upper(ut.unit_name) like ? or upper(ud.unit_name) like ? or upper(ula.unit_name) like ? ) ";
		}
		
		if(qry.length() > 0) {
		    qry += " AND ";
		}
		qry += "c.cmd_status = ?";
		
		if(roleName.equals("comd_cont_deo_sd9") || roleName.equals("comd_cont_app_sd9") ){
			qry += "c.sd9_status = ?";
		}
		
		if(roleName.equals("o_sd4_deo") || roleName.equals("o_sd4_app") ){
			qry += "c.sd4_status = ?";
		}
		if(roleName.equals("comd_cont_deo_sd5") || roleName.equals("comd_cont_app_sd5") ){
			qry += "c.sd5_status = ?";
		}
		
		if(roleName.equals("comd_cont_deo_mo") || roleName.equals("comd_cont_app_mo") ){
			qry += "c.mo_status = ?";
		}
		if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
			qry += "and c.unit_sus_no = ?";
		}
		
		if(roleSubAccess.equals("Command")){
			qry += "and c.created_cmd_susno = ?";
		}
		
	
		
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT DISTINCT\r\n"
					+ "	c.id,\r\n"
					+ "	c.auth_letter_no,\r\n"
					+ "COALESCE(CASE\r\n"
					+ "    WHEN c.cmd_status = 3 THEN c.remarks\r\n"
					+ "    ELSE c.current_status\r\n"
					+ "END, c.current_status) AS status,\r\n"
					+ "	u.unit_name AS unit_name,\r\n"
					+ "	uo.unit_name AS ops,\r\n"
					+ "	ui.unit_name AS is_name,\r\n"
					+ "	um.unit_name AS mil,\r\n"
					+ "	ut.unit_name AS techcont,\r\n"
					+ "	ud.unit_name AS discp,\r\n"
					+ "	ula.unit_name AS local_adm,\r\n"
					+ "	(select distinct code_value from tb_miso_orbat_code where code = c.loc_code and status_record = '1' )as loc_code\r\n"
					+ "FROM\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON c.unit_sus_no=u.sus_no\r\n"
					+ "	AND u.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl uo ON c.ops_sus_no=uo.sus_no\r\n"
					+ "	AND uo.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ui ON c.is_sus_no=ui.sus_no\r\n"
					+ "	AND ui.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl um ON c.mil_sus_no=um.sus_no\r\n"
					+ "	AND um.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ut ON c.techcont_sus_no=ut.sus_no\r\n"
					+ "	AND ut.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ud ON c.discp_sus_no=ud.sus_no\r\n"
					+ "	AND ud.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ula ON c.local_adm_sus_no=ula.sus_no\r\n"
					+ "	AND ula.status_sus_no='Active'    where " + qry + " " + "   limit " + pageL + " OFFSET "
					+ startPage + "";

			stmt = conn.prepareStatement(q);
			
			int t=1;
			
			if (!Search.equals("")) {

				
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
			}
			stmt.setInt(t, Integer.parseInt(status));
			t=t+1;
			if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
				stmt.setString(t, unit_sus_no);
				t=t+1;
			}
			
			if(roleSubAccess.equals("Command")){
				stmt.setString(t, roleSusNo);
				t=t+1;
			}

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String action = "";
				String f = "";			
				String f1 = "";
				String f2 = "";	
				String f3 = "";
			
				
				String viewAction = "onclick=\"viewComdAndContDetails(" + rs.getString("id") + ")\"";
				 f = "<i class='action_icons action_view eye-icon' " + viewAction + " title='View Comd And Cont Inst Data'></i>";

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>"; 
 
        	        String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("
			     + rs.getString("id") + ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
			
				if (status.equals("0")) {
					if (roleType.equals("DEO")) {
						action += f;
						action += f1;
					} else if (roleType.equals("APP")) {
						action += f;					
						action += f2;
						action += f3;

					}
				} else if (status.equals("1")) {
					action += f;
				} else if (status.equals("3")) {
					action += f;
					action += f1;
				}

				columns.put("action", action);
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

	public long GetSearch_comd_and_cont_count(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status) {

		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleName = sessionUserId.getAttribute("role").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String q = null;
		String qry = "";

		if(!Search.equals("")) { // for Input Filter
			qry ="( upper(c.auth_letter_no) like ? or upper(u.unit_name) like ? or upper(loc_code) like ? "
					+ " or upper(uo.unit_name) like ? or upper(ui.unit_name) like ? or upper(um.unit_name) like ? "
					+ "or upper(ut.unit_name) like ? or upper(ud.unit_name) like ? or upper(ula.unit_name) like ? ) ";
		}
		
		if(qry.length() > 0) {
		    qry += " AND ";
		}
		qry += "c.cmd_status = ?";
		
		if(roleName.equals("comd_cont_deo_sd9") || roleName.equals("comd_cont_app_sd9") ){
			qry += "c.sd9_status = ?";
		}
		
		if(roleName.equals("o_sd4_deo") || roleName.equals("o_sd4_app") ){
			qry += "c.sd4_status = ?";
		}
		if(roleName.equals("comd_cont_deo_sd5") || roleName.equals("comd_cont_app_sd5") ){
			qry += "c.sd5_status = ?";
		}
		
		if(roleName.equals("comd_cont_deo_mo") || roleName.equals("comd_cont_app_mo") ){
			qry += "c.mo_status = ?";
		}
		if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
			qry += "and c.unit_sus_no = ?";
		}
		
		if(roleSubAccess.equals("Command")){
			qry += "and c.created_cmd_susno = ?";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(SELECT DISTINCT\r\n" + "	c.id,\r\n" + "	c.auth_letter_no,\r\n"
					+ "	c.current_status AS status,\r\n" + "	u.unit_name AS unit_name,\r\n"
					+ "	uo.unit_name AS ops,\r\n" + "	ui.unit_name AS is_name,\r\n" + "	um.unit_name AS mil,\r\n"
					+ "	ut.unit_name AS techcont,\r\n" + "	ud.unit_name AS discp,\r\n"
					+ "	ula.unit_name AS local_adm,\r\n" + "	c.loc_code\r\n" + "FROM\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON c.unit_sus_no=u.sus_no\r\n"
					+ "	AND u.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl uo ON c.ops_sus_no=uo.sus_no\r\n"
					+ "	AND uo.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ui ON c.is_sus_no=ui.sus_no\r\n"
					+ "	AND ui.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl um ON c.mil_sus_no=um.sus_no\r\n"
					+ "	AND um.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ut ON c.techcont_sus_no=ut.sus_no\r\n"
					+ "	AND ut.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ud ON c.discp_sus_no=ud.sus_no\r\n"
					+ "	AND ud.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ula ON c.local_adm_sus_no=ula.sus_no\r\n"
					+ "	AND ula.status_sus_no='Active'   where  " + qry + ") app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			int t=1;
			
			if (!Search.equals("")) {

				
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
				stmt.setString(t, Search.toUpperCase() + "%");
				t += 1;
			}
			stmt.setInt(t, Integer.parseInt(status));
			t=t+1;
			if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
				stmt.setString(t, unit_sus_no);
				t=t+1;
			}
			
			if(roleSubAccess.equals("Command")){
				stmt.setString(t, roleSusNo);
				t=t+1;
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
		return (long) total;
	}
	/*

	public long GetSearch_comd_and_cont_count(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId, String unit_sus_no, String status) {

		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		String qry = "";

		qry = "c.cmd_status = ?";
		if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
			qry += "and c.unit_sus_no = ?";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(app.*) from(SELECT DISTINCT\r\n" + "	c.id,\r\n" + "	c.auth_letter_no,\r\n"
					+ "	c.current_status AS status,\r\n" + "	u.unit_name AS unit_name,\r\n"
					+ "	uo.unit_name AS ops,\r\n" + "	ui.unit_name AS is_name,\r\n" + "	um.unit_name AS mil,\r\n"
					+ "	ut.unit_name AS techcont,\r\n" + "	ud.unit_name AS discp,\r\n"
					+ "	ula.unit_name AS local_adm,\r\n" + "	c.loc_code\r\n" + "FROM\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON c.unit_sus_no=u.sus_no\r\n"
					+ "	AND u.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl uo ON c.ops_sus_no=uo.sus_no\r\n"
					+ "	AND uo.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ui ON c.is_sus_no=ui.sus_no\r\n"
					+ "	AND ui.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl um ON c.mil_sus_no=um.sus_no\r\n"
					+ "	AND um.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ut ON c.techcont_sus_no=ut.sus_no\r\n"
					+ "	AND ut.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ud ON c.discp_sus_no=ud.sus_no\r\n"
					+ "	AND ud.status_sus_no='Active'\r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl ula ON c.local_adm_sus_no=ula.sus_no\r\n"
					+ "	AND ula.status_sus_no='Active'   where  " + qry + ") app ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(status));
			if (unit_sus_no != null && !unit_sus_no.isEmpty()) {
				stmt.setString(2, unit_sus_no);
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
		return (long) total;
	}*/

}