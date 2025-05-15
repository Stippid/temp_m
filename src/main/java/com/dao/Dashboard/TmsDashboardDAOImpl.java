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
import com.itextpdf.text.log.SysoCounter;
import com.models.View_TMS_Bveh_Mvcr_Update_Status_Details;
import com.models.View_TMS_Cveh_Mvcr_Update_Status_Details;
import com.models.View_Tms_BVEH_Unit_Wise_Issue_Type_UH;
import com.models.Prf_Wise_TrnsportUE_UH;
import com.models.View_TMS_Aveh_Mvcr_Update_Status_Details;
import com.models.View_TMS_BVeh_Command_Wise_TrnsportUE_UH;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class TmsDashboardDAOImpl implements TmsDashboardDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<List<String>> gettypeOfVehicleList() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			/*
			 * String sql1 = "SELECT \r\n" + "	A.UPDATED as A_up,\r\n" +
			 * "	AA.YET_TO_UPDATE as A_yet ,\r\n" + "	B.UPDATED as B_up,\r\n" +
			 * "	BB.YET_TO_UPDATE as B_yet, \r\n" + "	C.UPDATED as C_up,\r\n" +
			 * "	CC.YET_TO_UPDATE as C_yet\r\n" + "FROM  \r\n" +
			 * "	(select count(distinct p.sus_no) as UPDATED from tb_tms_census_retrn_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS A, \r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as YET_TO_UPDATE from tb_tms_census_retrn_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS AA,\r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as UPDATED from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS B, \r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as YET_TO_UPDATE from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS BB,\r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as UPDATED from tb_tms_emar_report_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(cast(p.approve_date as date),'mm-yyyy'),'0')) AS C, \r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as YET_TO_UPDATE from tb_tms_emar_report_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(cast(p.approve_date  as date),'mm-yyyy'),'0')) AS CC "
			 * ;
			 */
			String sql1 = "SELECT \r\n" + "	A.UPDATED as A_up,\r\n" + "	AA.YET_TO_UPDATE as A_yet ,\r\n"
					+ "	B.UPDATED as B_up,\r\n" + "	BB.YET_TO_UPDATE as B_yet, \r\n" + "	C.UPDATED as C_up,\r\n"
					+ "	CC.YET_TO_UPDATE as C_yet\r\n" + "FROM  \r\n"
					+ "	(select count(distinct p.sus_no) as UPDATED from tb_tms_census_retrn_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS A, \r\n"
					+ "	(select count(distinct p.sus_no) as YET_TO_UPDATE \r\n"
					+ "	 from tb_tms_census_retrn_main p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no and  u.status_sus_no = 'Active'\r\n"
					+ "	 inner join tb_tms_census_retrn c on p.sus_no = c.sus_no\r\n"
					+ "	 where ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS AA,\r\n"
					+ "	(select count(distinct p.sus_no) as UPDATED from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS B, \r\n"
					+ "	(select count(distinct p.sus_no) as YET_TO_UPDATE \r\n" + "	 from TB_TMS_MVCR_PARTA_MAIN p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "	 inner join TB_TMS_MVCR_PARTA_DTL c on p.sus_no = c.sus_no\r\n"
					+ "	 where ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(p.approve_date,'mm-yyyy'),'0')) AS BB,\r\n"
					+ "	(select count(distinct p.sus_no) as UPDATED from tb_tms_emar_report_main p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and ltrim(TO_CHAR(now(),'mm-yyyy'),'0') = ltrim(TO_CHAR(cast(p.approve_date as date),'mm-yyyy'),'0')) AS C, \r\n"
					+ "	(select count(distinct p.sus_no) as YET_TO_UPDATE from \r\n"
					+ "	 tb_tms_emar_report_main p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "	 inner join tb_tms_emar_report c on p.sus_no = c.sus_no\r\n"
					+ "	 where ltrim(TO_CHAR(now(),'mm-yyyy'),'0') != ltrim(TO_CHAR(cast(p.approve_date  as date),'mm-yyyy'),'0')) AS CC ";

			System.out.println(stmt+"===stmt");
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("A_up"));
				list.add(rs1.getString("A_yet"));
				list.add(rs1.getString("B_up"));
				list.add(rs1.getString("B_yet"));
				list.add(rs1.getString("C_up"));
				list.add(rs1.getString("C_yet"));
				lista.add(list);
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
		return lista;
	}

	///////////////////////////////////////////////////////

	public List<String> getPendingApprovedStatusList() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			/*
			 * String sql1 = "	select p.pending as pad,\r\n" + "	a.approved as app,\r\n"
			 * + "	r.reject as rej \r\n" + "	from \r\n" +
			 * "	(select count(distinct p.sus_no) as pending from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and status='0') as p,\r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as approved from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and status='1') as a,\r\n"
			 * +
			 * "	(select count(distinct p.sus_no) as reject from TB_TMS_MVCR_PARTA_MAIN p,tb_miso_orbat_unt_dtl u where u.sus_no = p.sus_no AND u.status_sus_no = 'Active' and status='2') as r\r\n"
			 * ;
			 */

			String sql1 = "select p.pending as pad,\r\n" + "	a.approved as app,\r\n" + "	r.reject as rej \r\n"
					+ "	from \r\n" + "	(select count(distinct p.sus_no) as pending from \r\n"
					+ "	 TB_TMS_MVCR_PARTA_MAIN p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "	 inner join TB_TMS_MVCR_PARTA_DTL c on p.sus_no = c.sus_no\r\n"
					+ "	 where p.status='0') as p,\r\n" + "	(select count(distinct p.sus_no) as approved from \r\n"
					+ "	 TB_TMS_MVCR_PARTA_MAIN p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "	 inner join TB_TMS_MVCR_PARTA_DTL c on p.sus_no = c.sus_no\r\n"
					+ "	 where p.status='1') as a,\r\n" + "	(select count(distinct p.sus_no) as reject from \r\n"
					+ "	 TB_TMS_MVCR_PARTA_MAIN p\r\n"
					+ "	 inner join tb_miso_orbat_unt_dtl u on u.sus_no = p.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "	 inner join TB_TMS_MVCR_PARTA_DTL c on p.sus_no = c.sus_no\r\n" + "	 where p.status='2') as r";

		
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				list1.add(rs1.getString("pad"));
				list1.add(rs1.getString("app"));
				list1.add(rs1.getString("rej"));
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
		return list1;
	}
	/////////////////////////////////////////////////////////////////////////////////////////

	public List<List<String>> getPendingApprovedRoRioStatusList() {
		List<List<String>> list = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();

			String sql1 = "select ro_pen,rio_pen,ro_app,rio_app from\r\n"
					+ "(select count(ro_no) as ro_pen from tb_tms_ro_vehicle_dtls  where status='0') as ro_pen,\r\n"
					+ "(select count(ro_no) as rio_pen from tb_tms_rio_vehicle_dtls  where status='0') as rio_pen,\r\n"
					+ "(select count(ro_no) as ro_app from tb_tms_ro_vehicle_dtls  where status='1')  as ro_app,\r\n"
					+ "(select count(ro_no) as rio_app from tb_tms_rio_vehicle_dtls  where status='1')  as rio_app";

			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list1 = new ArrayList<String>();

				list1.add(rs1.getString("ro_pen"));
				list1.add(rs1.getString("rio_pen"));

				list.add(list1);

				list1 = new ArrayList<String>();

				list1.add(rs1.getString("ro_app"));
				list1.add(rs1.getString("rio_app"));

				list.add(list1);
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
		return list;
	}

	public Long getmvcrunitList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(
				"select count(distinct m.sus_no) from TB_TMS_MVCR_PARTA_MAIN m ,Miso_Orbat_Unt_Dtl u where m.sus_no=u.sus_no and status_sus_no='Active'");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long getNoOfPrfList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("SELECT COUNT(DISTINCT a.prf_group)  FROM Prf_Wise_TrnsportUE_UH a");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long getMCTdesList() {
		Long count = (long) 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select COUNT(id) as count from tb_tms_mct_main_master";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				count = rs1.getLong("count");
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
		return count;
	}

	public Long getMAKEList() {
		Long count = (long) 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select COUNT(id) as count from tb_tms_make_master";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				count = rs1.getLong("count");
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
		return count;
	}

	public Long getMODELList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(id) from TB_TMS_MODEL_MASTER");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long getAvgKMs() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select sum(kms_run)/count(*) from TB_TMS_MVCR_PARTA_DTL");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long getAvgYears() {
		Long count = (long) 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select sum(cast(ltrim(TO_CHAR(now(),'yyyy'),'0')  as integer)+1 - cast(ltrim(TO_CHAR(cast(year_of_entry as date),'yyyy'),'0')  as integer))/count(*) as year from tb_tms_banum_dirctry";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				count = rs1.getLong("year");
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
		return count;
	}

	public Long getUNITList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(
				"select count(id) as count from TB_TMS_MVCR_PARTA_DTL where classification in ('1','2','3','4')");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long getDEPOTList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(
				"select count(distinct ba_no) as count from TB_TMS_DRR_DIR_DTL where typ_of_retrn <> type_of_issue");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	// formation wise details

	public DataSet<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> DatatablesCriteriasFormationWiseueuh(
			DatatablesCriterias criterias) {
		List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> metadata = findDepartmentCriteriasWiseueuh(criterias);
		Long countFiltered = getFilteredCountueuh(criterias);
		Long count = getTotalCountueuh();
		return new DataSet<View_TMS_BVeh_Command_Wise_TrnsportUE_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> findDepartmentCriteriasWiseueuh(
			DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_BVeh_Command_Wise_TrnsportUE_UH d ");
		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> list = (List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryueuh(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountueuh() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_TMS_BVeh_Command_Wise_TrnsportUE_UH d ");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountueuh(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_BVeh_Command_Wise_TrnsportUE_UH d ");
		queryBuilder.append(getFilterQueryueuh(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH> list = (List<View_TMS_BVeh_Command_Wise_TrnsportUE_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// formation wise details End

	// prf wise details

	public DataSet<Prf_Wise_TrnsportUE_UH> DatatablesCriteriasPrfWiseueuh(DatatablesCriterias criterias) {
		List<Prf_Wise_TrnsportUE_UH> metadata = findDepartmentCriteriasprfueuh(criterias);
		Long countFiltered = getFilteredCountprf(criterias);
		Long count = getTotalCountprf();
		return new DataSet<Prf_Wise_TrnsportUE_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<Prf_Wise_TrnsportUE_UH> findDepartmentCriteriasprfueuh(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Prf_Wise_TrnsportUE_UH d ");
		queryBuilder.append(getFilterQueryprf(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Prf_Wise_TrnsportUE_UH> list = (List<Prf_Wise_TrnsportUE_UH>) q.list();

		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryprf(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountprf() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM Prf_Wise_TrnsportUE_UH d ");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountprf(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Prf_Wise_TrnsportUE_UH d ");
		queryBuilder.append(getFilterQueryprf(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<Prf_Wise_TrnsportUE_UH> list = (List<Prf_Wise_TrnsportUE_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	// mvcr wise details
	

	public DataSet<Map<String, Object>> DatatablesCriteriasMvcrstatus(DatatablesCriterias criterias, String whr){
		String search = "";
		if(!criterias.getSearch().toLowerCase().equals("")){
			search = " ( lower(a.sus_no) like ? or lower(a.cmd_name) like ? or lower(a.coprs_name) like ? or lower(a.div_name) like ? or lower(a.bde_name) like ? or lower(a.unit_name) like ? or lower(a.type_of_force) like ?) ";
			if(whr.equals("")){
				whr = " where "+ search;
			}else {
				whr = whr +" and "+ search;
			}
		}
		
		String mainQry = "select a.sus_no, a.cmd_name,a.coprs_name,a.div_name,a.bde_name,a.unit_name,a.type_of_force,string_agg(a.status::text, ','::text) as status,\r\n" + 
				"		max(a.app_date) as app_date,string_agg(a.approved_by::text, ','::text) as approved_by \r\n" + 
				"from tms_bveh_mvcr_update_state_view a \r\n" +
				 whr +
				" group by 1,2,3,4,5,6,7\r\n" + 
				"order by 1";
		
		String TotalCountQry = "select 	count(distinct a.sus_no)\r\n" + 
				"from tms_bveh_mvcr_update_state_view a "+ whr ;
		
		String FilteredCountQry = "select 	count(distinct a.sus_no)\r\n" + 
				"from tms_bveh_mvcr_update_state_view a " + whr;
		
		List<Map<String, Object>> metadata = findDepartmentCriteriasmvcr(criterias, mainQry, criterias.getSearch().toLowerCase());
		Long count = getTotalCountmv(TotalCountQry, criterias.getSearch().toLowerCase());
		Long countFiltered = getFilteredCountmv(FilteredCountQry, criterias.getSearch().toLowerCase());
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}

	private List<Map<String, Object>> findDepartmentCriteriasmvcr(DatatablesCriterias criterias,String qry,String search) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
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
		queryBuilder.append(" limit "+criterias.getDisplaySize()+" OFFSET "+ criterias.getDisplayStart()+"");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		if(!search.equals("")){
    			stmt.setString(1,"%"+ search+"%");
    			stmt.setString(2, "%"+search+"%");
    			stmt.setString(3, "%"+search+"%");
    			stmt.setString(4, "%"+search+"%");
    			stmt.setString(5, "%"+search+"%");
    			stmt.setString(6, "%"+search+"%");
    			stmt.setString(7, "%"+search+"%");
    		}
    		
    		ResultSet rs1 = stmt.executeQuery();
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("status", rs1.getString("status"));
    			columns.put("app_date", rs1.getString("app_date"));
    			columns.put("approved_by", rs1.getString("approved_by"));
    			columns.put("type_of_force", rs1.getString("type_of_force"));
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	private Long getTotalCountmv(String qry,String search) {
		Connection conn = null;
		Long count = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(qry);
    		if(!search.equals("")){
    			stmt.setString(1,"%"+ search+"%");
    			stmt.setString(2, "%"+search+"%");
    			stmt.setString(3, "%"+search+"%");
    			stmt.setString(4, "%"+search+"%");
    			stmt.setString(5, "%"+search+"%");
    			stmt.setString(6, "%"+search+"%");
    			stmt.setString(7, "%"+search+"%");
    		}
			ResultSet rs = stmt.executeQuery();   
			if (rs.next()) {
				count = rs.getLong(1);
	    	}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return count;
	}
	private Long getFilteredCountmv(String qry,String search){
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long FilteredCount = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		if(!search.equals("")){
    			stmt.setString(1,"%"+ search+"%");
    			stmt.setString(2, "%"+search+"%");
    			stmt.setString(3, "%"+search+"%");
    			stmt.setString(4, "%"+search+"%");
    			stmt.setString(5, "%"+search+"%");
    			stmt.setString(6, "%"+search+"%");
    			stmt.setString(7, "%"+search+"%");
    		
    		}
    		ResultSet rs = stmt.executeQuery();     
			if (rs.next()) {
				FilteredCount = rs.getLong(1);
	    	}
			rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    	
		}
		return FilteredCount;
	}
	
	
	/*public DataSet<View_TMS_Bveh_Mvcr_Update_Status_Details> DatatablesCriteriasMvcrstatus(DatatablesCriterias criterias, String whr){
		List<View_TMS_Bveh_Mvcr_Update_Status_Details> metadata = findDepartmentCriteriasmvcr(criterias, whr);
		Long countFiltered = getFilteredCountmv(criterias, whr);
		Long count = getTotalCountmv(whr);
		return new DataSet<View_TMS_Bveh_Mvcr_Update_Status_Details>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_TMS_Bveh_Mvcr_Update_Status_Details> findDepartmentCriteriasmvcr(DatatablesCriterias criterias,
			String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Bveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQuerymv(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_TMS_Bveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Bveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQuerymv(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountmv(String whr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_TMS_Bveh_Mvcr_Update_Status_Details d " + whr);
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountmv(DatatablesCriterias criterias,String whr){
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Bveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQuerymv(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_TMS_Bveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Bveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}*/
	// mvcr wise details End

	// Amcr wise details
	public DataSet<View_TMS_Aveh_Mvcr_Update_Status_Details> DatatablesCriteriasAMvcrstatus(DatatablesCriterias criterias, String whr){
		List<View_TMS_Aveh_Mvcr_Update_Status_Details> metadata = findDepartmentCriteriasAmvcr(criterias, whr);
		Long countFiltered = getFilteredCountAmv(criterias, whr);
		Long count = getTotalCountAmv(whr);
		return new DataSet<View_TMS_Aveh_Mvcr_Update_Status_Details>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_TMS_Aveh_Mvcr_Update_Status_Details> findDepartmentCriteriasAmvcr(DatatablesCriterias criterias,String whr){
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Aveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQueryAmv(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_TMS_Aveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Aveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryAmv(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountAmv(String whr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_TMS_Aveh_Mvcr_Update_Status_Details d " + whr);
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountAmv(DatatablesCriterias criterias, String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Aveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQueryAmv(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_TMS_Aveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Aveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// Amcr wise details End

	// Cmcr wise details

	public DataSet<View_TMS_Cveh_Mvcr_Update_Status_Details> DatatablesCriteriasCMvcrstatus(
			DatatablesCriterias criterias, String whr) {
		List<View_TMS_Cveh_Mvcr_Update_Status_Details> metadata = findDepartmentCriteriasCmvcr(criterias, whr);
		Long countFiltered = getFilteredCountCmv(criterias, whr);
		Long count = getTotalCountCmv(whr);
		return new DataSet<View_TMS_Cveh_Mvcr_Update_Status_Details>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_TMS_Cveh_Mvcr_Update_Status_Details> findDepartmentCriteriasCmvcr(DatatablesCriterias criterias,
			String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Cveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQueryCmv(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_TMS_Cveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Cveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryCmv(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountCmv(String whr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_TMS_Cveh_Mvcr_Update_Status_Details d " + whr);
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountCmv(DatatablesCriterias criterias, String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_TMS_Cveh_Mvcr_Update_Status_Details d " + whr);
		queryBuilder.append(getFilterQueryCmv(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_TMS_Cveh_Mvcr_Update_Status_Details> list = (List<View_TMS_Cveh_Mvcr_Update_Status_Details>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// Cmcr wise details End

	// Start Loan Store wise details

	public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasLoanStore(DatatablesCriterias criterias) {
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> metadata = findDepartmentCriteriasLoanStore(criterias);
		Long countFiltered = getFilteredCountLoanStore(criterias);
		Long count = getTotalCountLoanStore();
		return new DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> findDepartmentCriteriasLoanStore(
			DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where loan !='0'");
		queryBuilder.append(getFilterQueryLoanStore(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryLoanStore(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountLoanStore() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where loan !='0'");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountLoanStore(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where loan !='0'");
		queryBuilder.append(getFilterQueryLoanStore(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// Loan Store wise details End

	// Sector Store wise details

	public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasSectorStore(
			DatatablesCriterias criterias) {
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> metadata = DatatablesCriteriasSectorSt(criterias);
		Long countFiltered = getFilteredCountSectorStore(criterias);
		Long count = getTotalCountSectorStore();
		return new DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasSectorSt(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where sector !='0'");
		queryBuilder.append(getFilterQuerySectorStore(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQuerySectorStore(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountSectorStore() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where sector !='0'");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountSectorStore(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where sector !='0'");
		queryBuilder.append(getFilterQuerySectorStore(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// Sector Store wise details End

	// Aesp Store wise details

	public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasACSFPStore(DatatablesCriterias criterias) {
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> metadata = DatatablesCriteriasACSFPSt(criterias);
		Long countFiltered = getFilteredCountacsfpStore(criterias);
		Long count = getTotalCountacsfpStore();
		return new DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasACSFPSt(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where acsfp !='0'");
		queryBuilder.append(getFilterQueryacsfpStore(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryacsfpStore(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountacsfpStore() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where acsfp !='0'");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountacsfpStore(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where acsfp !='0'");
		queryBuilder.append(getFilterQueryacsfpStore(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// Aesp Store wise details End

	// over ue Store wise details

	public DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasOverUeStore(
			DatatablesCriterias criterias) {
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> metadata = DatatablesCriteriasOverUeSt(criterias);
		Long countFiltered = getFilteredCountover_ueStore(criterias);
		Long count = getTotalCountover_ueStore();
		return new DataSet<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> DatatablesCriteriasOverUeSt(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where over_ue !='0'");
		queryBuilder.append(getFilterQueryover_ueStore(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
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
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	private static StringBuilder getFilterQueryover_ueStore(DatatablesCriterias criterias,
			StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("")) {
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
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountover_ueStore() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where over_ue !='0'");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountover_ueStore(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM View_Tms_BVEH_Unit_Wise_Issue_Type_UH d where over_ue !='0'");
		queryBuilder.append(getFilterQueryover_ueStore(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH> list = (List<View_Tms_BVEH_Unit_Wise_Issue_Type_UH>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	// over ue Store wise details End

	public String getCommandWiseUE_UH_B_VEH_List() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select \r\n" + "	distinct c.short_form as command,\r\n" + "	sum(b.ue) as ue,\r\n"
					+ "	sum(b.uh) as uh, \r\n" + "	c.cmd_code " + "from \r\n"
					+ "	tms_bveh_command_wise_transport_ue_uh_view b\r\n"
					+ "	left join orbat_view_cmd c on c.cmd_code = substr(b.form_code_control,1,1)\r\n"
					+ "	where command is not null \r\n" + "   GROUP BY c.short_form,4";

			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				if (!rs1.getString("command").isEmpty()) {
					list.add(rs1.getString("command"));
					list.add(rs1.getString("ue"));
					list.add(rs1.getString("uh"));
					list.add(rs1.getString("cmd_code"));

					listA.add(list);
				}
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			if (i == 0) {
				list += "{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + ", 'comd_code' : '" + listA.get(i).get(3) + "'}";
			} else {
				list += ",{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + ", 'comd_code' : '" + listA.get(i).get(3) + "'}";
			}
		}
		list += "]";
		return list;
	}

	public ArrayList<List<String>> getCorpsWiseUE_UH_B_VEH_List(String comnd) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// get corps only
			String sql1 = "";
			sql1 = "select \r\n" + "		distinct c.coprs_name as corps,\r\n" + "		"
					+ " 	sum(b.ue) as ue,\r\n" + "		sum(b.uh) as uh, \r\n"
					+ " substr(form_code_control,1,3) as corps_code" + "	 from \r\n"
					+ "		tms_bveh_command_wise_transport_ue_uh_view b\r\n"
					+ "		left join orbat_view_corps c on c.corps_code = substr(b.form_code_control,2,2)\r\n"
					+ "		inner join orbat_view_cmd p on p.cmd_code like ? \r\n"
					+ "		where  c.coprs_name is not null and p.cmd_name= command \r\n"
					+ "	GROUP BY c.coprs_name,4";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, comnd + "%");
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("corps"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("corps_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getDivWiseUE_UH_B_VEH_List(String Corps) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct div,sum(ue) as ue,sum(uh) as uh, "
					+ " substr(form_code_control,1,6) as div_code " + "from tms_bveh_command_wise_transport_ue_uh_view "
					+ "where div is not null and div !='' and form_code_control like ? GROUP BY div,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Corps + "%");

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("div"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("div_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getBDEWiseUE_UH_B_VEH_List(String Div) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = " select distinct bde,sum(ue) as ue,sum(uh) as uh ,form_code_control as bde_code "
					+ " from tms_bveh_command_wise_transport_ue_uh_view "
					+ " where bde is not null and bde !='' and form_code_control like ? GROUP BY bde,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Div + "%");

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("bde"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("bde_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	/////////////////////////// A veh /////////////////////////////////////

	public String getCommandWiseUE_UH_A_VEH_List() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "	select distinct c.short_form as command,\r\n" + "		sum(a.ue) as ue,\r\n"
					+ "		sum(a.uh) as uh, \r\n" + "		c.cmd_code " + "	from \r\n"
					+ "		tms_aveh_command_wise_transport_ue_uh_view a\r\n"
					+ "		left join orbat_view_cmd c on c.cmd_code = substr(a.form_code_control,1,1)\r\n"
					+ "		where a.command is not null " + " GROUP BY c.short_form,c.cmd_code\r\n";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("cmd_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			if (i == 0) {
				list += "{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + " , 'comd_code' : '" + listA.get(i).get(3) + "'}";
			} else {
				list += ",{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + ", 'comd_code' : '" + listA.get(i).get(3) + "'}";
			}
		}
		list += "]";
		return list;
	}

	public ArrayList<List<String>> getCorpsWiseUE_UH_A_VEH_List(String comnd) {

		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select \r\n" + "		distinct c.coprs_name as corps,\r\n" + "		sum(b.ue) as ue,\r\n"
					+ "		sum(b.uh) as uh, \r\n" + "		substr(form_code_control,1,3) as corps_code"
					+ "	 from \r\n" + "		tms_aveh_command_wise_transport_ue_uh_view b\r\n"
					+ "		left join orbat_view_corps c on c.corps_code = substr(b.form_code_control,2,2)\r\n"
					+ "		inner join orbat_view_cmd p on p.cmd_code like ? \r\n"
					+ "		where  c.coprs_name is not null and p.cmd_name= command \r\n"
					+ "	GROUP BY c.coprs_name,substr(form_code_control,1,3)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, comnd + "%");

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("corps"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("corps_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getDivWiseUE_UH_A_VEH_List(String Corps) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct div,sum(ue) as ue,sum(uh) as uh,substr(form_code_control,1,6) as div_code \r\n"
					+ "	from tms_aveh_command_wise_transport_ue_uh_view \r\n"
					+ "	where div is not null and div !='' and form_code_control like ? GROUP BY div,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Corps + "%");

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("div"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("div_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getBDEWiseUE_UH_A_VEH_List(String Div) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct bde,sum(ue) as ue,sum(uh) as uh,form_code_control as bde_code \r\n"
					+ "from tms_aveh_command_wise_transport_ue_uh_view \r\n"
					+ "where bde is not null and bde !='' and form_code_control like ? GROUP BY bde,4	";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Div + "%");

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("bde"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("bde_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	////////////////// c vah///////////////////////////////////////

	public String getCommandWiseUE_UH_C_VEH_List() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select distinct c.short_form as command,\r\n" + "		sum(a.ue) as ue,\r\n"
					+ "		sum(a.uh) as uh, \r\n" + "		c.cmd_code " + "	from \r\n"
					+ "		tms_cveh_command_wise_transport_ue_uh_view a\r\n"
					+ "		left join orbat_view_cmd c on c.cmd_code = substr(a.form_code_control,1,1)\r\n"
					+ "		where a.command is not null GROUP BY c.short_form ,4";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("cmd_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			if (i == 0) {
				list += "{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + ", 'comd_code' : '" + listA.get(i).get(3) + "'}";
			} else {
				list += ",{'year' : '" + listA.get(i).get(0) + "' ,'ue' : " + listA.get(i).get(1) + " , 'uh' : "
						+ listA.get(i).get(2) + ", 'comd_code' : '" + listA.get(i).get(3) + "'}";
			}
		}
		list += "]";

		return list;
	}

	public ArrayList<List<String>> getCorpsWiseUE_UH_C_VEH_List(String comnd) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select \r\n" + "		distinct c.coprs_name as corps,\r\n" + "		sum(b.ue) as ue,\r\n"
					+ "		sum(b.uh) as uh, \r\n" + " 		substr(form_code_control,1,3) as corps_code "
					+ "	 from \r\n" + "		tms_cveh_command_wise_transport_ue_uh_view b\r\n"
					+ "		left join orbat_view_corps c on c.corps_code = substr(b.form_code_control,2,2)\r\n"
					+ "		inner join orbat_view_cmd p on p.cmd_code like ? \r\n"
					+ "		where  c.coprs_name is not null and p.cmd_name= command \r\n"
					+ "	GROUP BY c.coprs_name,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, comnd + "%");

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("corps"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("corps_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getDivWiseUE_UH_C_VEH_List(String Corps) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct div,sum(ue) as ue,sum(uh) as uh ,substr(form_code_control,1,6) as div_code "
					+ " from tms_cveh_command_wise_transport_ue_uh_view "
					+ " where div is not null and div !='' and form_code_control like ? GROUP BY div,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Corps + "%");

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("div"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("div_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public ArrayList<List<String>> getBDEWiseUE_UH_C_VEH_List(String Div) {

		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct bde,sum(ue) as ue,sum(uh) as uh ,form_code_control as bde_code "
					+ "from tms_cveh_command_wise_transport_ue_uh_view "
					+ "where bde is not null and bde !='' and form_code_control like ? GROUP BY bde,4";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Div + "%");

			ResultSet rs1 = stmt.executeQuery();
			for (int i = 0; rs1.next(); i++) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("bde"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("uh"));
				list.add(rs1.getString("bde_code"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public Long loanStoreTotal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select sum(cast(loan as int)) from View_Tms_BVEH_Unit_Wise_Issue_Type_UH");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long sectoreStoreTotal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select sum(cast(sector as int)) from View_Tms_BVEH_Unit_Wise_Issue_Type_UH");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long acsfpTotal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select sum(cast(acsfp as int)) from View_Tms_BVEH_Unit_Wise_Issue_Type_UH");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public Long overUeTotal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("select sum(cast(over_ue as int)) from View_Tms_BVEH_Unit_Wise_Issue_Type_UH");
		Long count = (Long) query.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public List<Map<String, Object>> exportDashCSV_A(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, String whr) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM tms_aveh_mcr_update_state_view " + whr + " ORDER BY " + orderColunm + " "
					+ orderType + " limit " + pageLength + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(sql);

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
			return list;
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

	public List<Map<String, Object>> exportDashCSV_B(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, String whr) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM tms_bveh_mvcr_update_state_view " + whr + " ORDER BY " + orderColunm + " "
					+ orderType + " limit " + pageLength + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(sql);

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
			return list;
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

	public List<Map<String, Object>> exportDashCSV_C(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, String whr) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM tms_cveh_mcr_update_state_view " + whr + " ORDER BY " + orderColunm + " "
					+ orderType + " limit " + pageLength + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(sql);

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
			return list;
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

}
