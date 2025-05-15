package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_DTL_HISTORY;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class MVCRDAOImp implements MVCRDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSet<TB_TMS_MVCR_PARTA_DTL> mvcrWithDatatablesCriterias(DatatablesCriterias criterias, String qry) {

		List<TB_TMS_MVCR_PARTA_DTL> metadata = findDepartmentCriteriasmob(criterias, qry);
		Long countFiltered = getFilteredCountmo(criterias, qry);
		Long count = getTotalCountmo(qry);

		return new DataSet<TB_TMS_MVCR_PARTA_DTL>(metadata, count, countFiltered);
	}

	@SuppressWarnings("unchecked")
	private List<TB_TMS_MVCR_PARTA_DTL> findDepartmentCriteriasmob(DatatablesCriterias criterias, String qry) {
		StringBuilder queryBuilder = null;

		if (qry.equals("")) {
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MVCR_PARTA_DTL d ");
		} else {
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MVCR_PARTA_DTL d " + qry);
		}

		/**
		 * Step 1: global and individual column filtering
		 */
		queryBuilder.append(getFilterQuerymo(criterias, queryBuilder));

		/**
		 * Step 2: sorting
		 */
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

		/**
		 * Step 3: paging
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<TB_TMS_MVCR_PARTA_DTL> list = (List<TB_TMS_MVCR_PARTA_DTL>) q.list();
		tx.commit();
		session.close();
		return list;
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
					if (columnDef.getName().equalsIgnoreCase("id"))

					{
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

	private Long getTotalCountmo(String qry) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;

		if (qry.equals("")) {
			q = session.createQuery("SELECT COUNT(d) FROM TB_TMS_MVCR_PARTA_DTL d  ");
		} else {
			q = session.createQuery("SELECT COUNT(d) FROM TB_TMS_MVCR_PARTA_DTL d  " + qry);
		}

		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountmo(DatatablesCriterias criterias, String qry) {
		StringBuilder queryBuilder = null;

		if (qry.equals("")) {
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MVCR_PARTA_DTL d  ");
		} else {
			queryBuilder = new StringBuilder("SELECT d FROM TB_TMS_MVCR_PARTA_DTL d  " + qry);
		}

		queryBuilder.append(getFilterQuerymo(criterias, queryBuilder));

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<TB_TMS_MVCR_PARTA_DTL> list = (List<TB_TMS_MVCR_PARTA_DTL>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	public ArrayList<List<String>> getSearchAttributeReportMvcr(String status, String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";
			if (status != "") {
				qry += " and n.status = ?";
			}
			if (sus_no != "") {
				qry += " and n.sus_no = ?";
			}
			sql = "select " + "distinct m.sus_no, " + "m.unit_name  " + "from " + "tb_tms_mvcr_parta_main n "
					+ "inner join tb_miso_orbat_unt_dtl m on n.sus_no=m.sus_no " + "where m.status_sus_no='Active'"
					+ qry;
			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;
			if (status != "") {
				stmt.setString(j, status);
				j += 1;
			}
			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));

				String View = "onclick=\"" + "View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='action_icons action_approve' " + View + " title='Approve Data'></i>";

				String partA = "onclick=\" partA('" + rs.getString("sus_no") + "')\"";
				String partAButton = "<i class='btn btn-default btn-xs' " + partA
						+ " title='View Data'>MVCR PART A</i>";

				String partB = "onclick=\"  partB('" + rs.getString("sus_no") + "') \"";
				String partBButton = "<i class='btn btn-default btn-xs' " + partB
						+ " title='View Data'>MVCR PART B</i>";

				String ApprovedC = "onclick=\"  View_C('" + rs.getString("sus_no") + "') \"";
				String partCButton = "<i class='btn btn-default btn-xs' " + ApprovedC
						+ " title='View Data'>MVCR PART C</i>";

				String ApprovedASSET = "onclick=\"  View_E('" + rs.getString("sus_no") + "') \"";
				String ASSETButton = "<i class='btn btn-default btn-xs' " + ApprovedASSET
						+ " title='View Data'>E - ASSET</i>";

				String f = "";
				if (status.equals("0")) {
					if (roleType.equals("ALL")) {
						f += appButton;
					}
					if (roleType.equals("APP")) {
						f += appButton;
					}
					if (roleType.equals("DEO")) {
					}
				} else if (status.equals("1")) {
					f += partAButton;
					f += partBButton;
					f += partCButton;
					f += ASSETButton;
				} else if (status.equals("2")) {
					if (roleType.equals("DEO") || roleType.equals("ALL")) {
						f += "No Action Required";
					}
				}
				list.add(f);
				alist.add(list);
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
		return alist;
	}

	public String setApprovedmvcr(String sus_no,String username) {
		Date dt = new Date();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int app = 0;
		try {
			
			   Session sessionMvcr = HibernateUtil.getSessionFactory().openSession();
				Transaction txMvcr = sessionMvcr.beginTransaction();
				Query qMvcr = sessionMvcr.createQuery("from TB_TMS_MVCR_PARTA_DTL where sus_no=:sus_no");			
				qMvcr.setParameter("sus_no", sus_no);
				@SuppressWarnings("unchecked")
				List<TB_TMS_MVCR_PARTA_DTL> ListMvcr = (List<TB_TMS_MVCR_PARTA_DTL>) qMvcr.list();
				
				for (TB_TMS_MVCR_PARTA_DTL mvcrPartaDtl : ListMvcr) {
				TB_TMS_MVCR_PARTA_DTL_HISTORY mvcr_dtl_htr = new TB_TMS_MVCR_PARTA_DTL_HISTORY();
				    mvcr_dtl_htr.setMvcr_parta_id(mvcrPartaDtl.getId());
				    mvcr_dtl_htr.setSus_no(mvcrPartaDtl.getSus_no());
				    mvcr_dtl_htr.setBa_no(mvcrPartaDtl.getBa_no());
				    mvcr_dtl_htr.setIssue_type(mvcrPartaDtl.getIssue_type());
				    mvcr_dtl_htr.setClassification(mvcrPartaDtl.getClassification());
				    mvcr_dtl_htr.setEpmnt_clasfctn(mvcrPartaDtl.getEpmnt_clasfctn());
				    mvcr_dtl_htr.setKms_run(mvcrPartaDtl.getKms_run());
				    mvcr_dtl_htr.setAuthrty_letter_no(mvcrPartaDtl.getAuthrty_letter_no());
				    mvcr_dtl_htr.setRemarks(mvcrPartaDtl.getRemarks());
				    mvcr_dtl_htr.setApp_rej_remarks(mvcrPartaDtl.getRej_remarks());
				    mvcr_dtl_htr.setStatus(2);
				    mvcr_dtl_htr.setMiso_rmk(mvcrPartaDtl.getMiso_rmk());
				    mvcr_dtl_htr.setSer_reason(mvcrPartaDtl.getSer_reason());
				    mvcr_dtl_htr.setSer_status(mvcrPartaDtl.getSer_status());
				    mvcr_dtl_htr.setCreation_by(username);
				    mvcr_dtl_htr.setCreation_date(new Date());
				    mvcr_dtl_htr.setApproved_by(username);
				    mvcr_dtl_htr.setApproved_date(new Date());
				    int is_data_updated =Integer.parseInt(mvcrPartaDtl.getStatus());
				    if(is_data_updated == 0) {
				    	   mvcr_dtl_htr.setData_updated(1);
				    }

				    sessionMvcr.save(mvcr_dtl_htr);
				
				}
			
			String hqlUpdate = "update TB_TMS_MVCR_PARTA_DTL c set c.status =:status, c.approve_date=:dt,c.modify_date=c.approve_date  where c.sus_no =:sus_no ";
			session.createQuery(hqlUpdate).setString("status", "1").setString("sus_no", sus_no).setDate("dt", dt).executeUpdate();

			String hqlUpdateMain = "update TB_TMS_MVCR_PARTA_MAIN c set c.status = :status, c.approve_date=:dt,approved_by=:approved_by,c.modify_date=c.approve_date  where c.sus_no =:sus_no ";
			app = session.createQuery(hqlUpdateMain).setString("status", "1").setString("sus_no", sus_no).setString("approved_by", username).setDate("dt", dt).executeUpdate();
			//update_mvcr_history_tb(sus_no); 

         
			txMvcr.commit();
			sessionMvcr.close();



			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		
		} finally {
			session.close();
		}
		if (app > 0) {
			return "MVCR Approved Successfully.";
		} else {
			return "MVCR not Approved";
		}
	}
	
	public String setRejectmvcr(String sus_no) {
		Date dt = new Date();
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int app = 0;
		try {
			String hqlUpdate = "update TB_TMS_MVCR_PARTA_DTL c set c.status =:status, c.modify_date=:dt where c.sus_no =:sus_no ";
			session.createQuery(hqlUpdate).setString("status", "2").setString("sus_no", sus_no).setDate("dt", dt).executeUpdate();

			String hqlUpdateMain = "update TB_TMS_MVCR_PARTA_MAIN c set c.status =:status, c.modify_date=:dt where c.sus_no =:sus_no ";
			app = session.createQuery(hqlUpdateMain).setString("status", "2").setString("sus_no", sus_no).setDate("dt", dt).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		
		} finally {
			session.close();
		}
		if (app > 0) {
			return "MVCR Rejected Successfully.";
		} else {
			return "MVCR not Rejected.";
		}
	}

	///////////////////////////////////// Part
	///////////////////////////////////// A////////////////////////////////////////////////
	
	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select o.unit_name, coalesce(o.cmd_name,'-') as cmd_name,\r\n" + 
					"	coalesce(o.coprs_name,'-') as coprs_name,coalesce(o.div_name,'-') as div_name,\r\n" + 
					"	coalesce(o.bde_name,'-') as bde_name ,\r\n" + 
					"	coalesce(string_agg(c.modification,','),'NA') as mod, \r\n" + 
					"	coalesce(o.location,'-') as loc,\r\n" + 
					"	coalesce(o.nrs,'-') as nrs" +
					"	from orbat_all_details_view o\r\n" + 
					"	left join cue_tb_wepe_link_sus_trans_mdfs c on o.sus_no = c.sus_no\r\n" + 
					"	where status_sus_no='Active' and o.sus_no = ? \r\n" + 
					"group by 1,2,3,4,5,7,8";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1,sus_no);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("error", "An Error Occure"+ e.getMessage());
			list.add(columns);
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
	
	
	public ArrayList<List<String>> getMVCR_PartA_List(String sus_no) {
		ArrayList<List<String>> aList = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sql = "select \r\n" + "	distinct M.ba_no ,\r\n" + "	J.mct,\r\n" + "	J.std_nomclature,\r\n"
					+ "	(SUBSTRING(M.ba_no,0,3) ) As yr,\r\n" + "	N.chasis_no,\r\n" + "	N.engine_no,\r\n"
					+ "	M.classification,\r\n" + "	M.kms_run,\r\n" + "	M.remarks,\r\n" + "	M.miso_rmk ,\r\n"
					+ "	SUBSTRING(M.ba_no, 3,10)\r\n" + "	from TB_TMS_MVCR_PARTA_DTL M \r\n"
					+ "	left join tb_tms_banum_dirctry N on M.ba_no = N.ba_no\r\n"
					+ "	left join tb_tms_mct_master J on N.mct=J.mct\r\n"
					+ "	where M.sus_no = ? ORDER BY SUBSTRING(M.ba_no, 3,10)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	/////////////////////////////////// part B Details UE
	/////////////////////////////////// UH////////////////////////////////////////////////

/*   NITIN V4 18/11/2022  */
public ArrayList<List<String>> getMVCR_PartB_DETAILS_UE_UH_List(String sus_no) {
		ArrayList<List<String>> aList = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sql = "select  b.mct_main_id,b.mct_nomen,\r\n" + 
					"	c.short_form as comd,\r\n" + 
					"	corps.coprs_name as corps,\r\n" + 
					"	div.div_name as div,\r\n" + 
					"	bde.bde_name as bde,\r\n" + 
					"	u.unit_name,\r\n" + 
					"	sum(a.ue) as ue,\r\n" + 
					"	sum(a.against_ue_s) as against_ue_s,\r\n" + 
					"	sum(a.against_ue_u) as against_ue_u,\r\n" + 
					"	sum(a.over_ue_s) as over_ue_s,\r\n" + 
					"	sum(a.over_ue_u) as over_ue_u,\r\n" + 
					"	sum(a.loan_s) as loan_s,\r\n" + 
					"	sum(a.loan_u) as loan_u,\r\n" + 
					"	sum(sec_store_s) as sec_store_s,\r\n" + 
					"	sum(sec_store_u) as sec_store_u,\r\n" + 
					"	sum(a.acsfp_s) as acsfp_s,\r\n" + 
					"	sum(a.acsfp_u) as acsfp_u,\r\n" + 
					"	sum(a.fresh_release) as fresh_release,\r\n" + 
					"	sum(gift_s) as gift_s,\r\n" + 
					"	sum(gift_u) as gift_u,\r\n" + 
					"	sum(pbd_s) as pbd_s,\r\n" + 
					"	sum(pbd_u) as pbd_u,\r\n" + 
					"	sum(op_s) as op_s,\r\n" + 
					"	sum(op_u) as op_u,\r\n" + 
					"	sum(trg_s) as trg_s,\r\n" + 
					"	sum(trg_u) as trg_u,\r\n" + 
					"	sum(wwr_s) as wwr_s,\r\n" + 
					"	sum(wwr_u) as wwr_u,\r\n" + 
					"	sum(opwks_s) as opwks_s,\r\n" + 
					"	sum(opwks_u) as opwks_u,\r\n" + 
					"	sum(others_s) as other_s,\r\n" + 
					"	sum(others_u) as other_u,\r\n" + 
					"	sum(a.total_uh_s) as total_uh_s,\r\n" + 
					"	sum(a.total_uh_u) as total_uh_u,\r\n" + 
					"	sum(a.total_uh) as total_uh\r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"left join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"left join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where a.type = 'B' and a.sus_no =?\r\n" + 
					" group by 1,2,3,4,5,6,7 ";
			
			
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
			/*	List<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}*/
				
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("mct_nomen")); //0
				list1.add(rs.getString("comd")); //1
				list1.add(rs.getString("corps"));//2
				list1.add(rs.getString("div"));//3
				list1.add(rs.getString("bde"));//4
				list1.add(rs.getString("unit_name"));//5
				list1.add(rs.getString("ue"));//6
				list1.add(rs.getString("against_ue_s"));//7
				list1.add(rs.getString("against_ue_u"));//8
				list1.add(rs.getString("over_ue_s"));//9
				list1.add(rs.getString("over_ue_u"));//10
				list1.add(rs.getString("loan_s"));//11
				list1.add(rs.getString("loan_u"));//12
				list1.add(rs.getString("sec_store_s"));//13
				list1.add(rs.getString("sec_store_u"));//14
				list1.add(rs.getString("acsfp_s"));//15
				list1.add(rs.getString("acsfp_u"));//16
				list1.add(rs.getString("pbd_s"));//17
				list1.add(rs.getString("pbd_u"));//18
				list1.add(rs.getString("fresh_release"));//19
				list1.add(rs.getString("gift_s"));//20
				list1.add(rs.getString("gift_u"));//21
				list1.add(rs.getString("total_uh_s"));//22
				list1.add(rs.getString("total_uh_u"));//23
				list1.add(rs.getString("total_uh"));//24
				
				
				int uh = 0;
				int ue = 0;
				if(rs.getString("total_uh") == null){
					uh = 0;
				}else{
					uh = Integer.parseInt(rs.getString("total_uh"));
				}
				if(rs.getString("ue") == null){
					ue = 0;
				}else{
					ue = Integer.parseInt(rs.getString("ue"));
				}
				if(ue >= 0 && uh >= 0){
					int diff = uh - ue;
					if(diff >= 0){
						sur = diff;
						defi = 0;
					}else{
						defi = diff;
						sur = 0;
					}
				}
				list1.add(String.valueOf(Math.abs(defi))); //25 
				defi = 0; 
				list1.add(String.valueOf(Math.abs(sur))); //26
				sur = 0;
				
				list1.add(rs.getString("op_s"));//27
				list1.add(rs.getString("op_u"));//28
				list1.add(rs.getString("trg_s"));//29
				list1.add(rs.getString("trg_u"));//30
				list1.add(rs.getString("wwr_s"));//31
				list1.add(rs.getString("wwr_u"));//32
				
				list1.add(rs.getString("opwks_s"));//33
				list1.add(rs.getString("opwks_u"));//34
				list1.add(rs.getString("other_s"));//35
				list1.add(rs.getString("other_u"));//36
				list1.add(rs.getString("mct_main_id"));//37
				
				
				aList.add(list1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}



	/////////////////////////////////// part B MCT UE
	/////////////////////////////////// UH////////////////////////////////////////////////

	/////////////////////////////////// part B PRF UE
	/////////////////////////////////// UH////////////////////////////////////////////////

	/////////////////////////////////// part
	/////////////////////////////////// C////////////////////////////////////////////////
	public List<String> getMvcrpartCList(String sus_no, String issue_type) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";
			if (issue_type != "") {
				qry += " and p.issue_type = ? ";
			} else {
				qry += " and (p.issue_type != '2' or p.issue_type != '6' or p.issue_type != '7' or p.issue_type != '5')";

			}
			sql ="select p.ba_no,coalesce(m.mct_main_id,'') as authority,coalesce(m.mct_nomen,'') as inlieu_mct,p.remarks,p.miso_rmk \r\n" + 
					"from TB_TMS_MVCR_PARTA_DTL p\r\n" + 
					"inner join TB_TMS_BANUM_DIRCTRY b on  b.ba_no =  p.ba_no\r\n" + 
					"left join TB_TMS_MCT_MAIN_MASTER m on substr(cast(b.mct as character varying),1,4) = m.mct_main_id\r\n" + 
					"where p.sus_no = ? "+qry+"  ORDER BY SUBSTRING(p.ba_no, 3,10)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_type.equals("")) {
				stmt.setString(2, issue_type);
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/////// E-Assets
	public ArrayList<ArrayList<String>> getAttributeFromMVCR_E_Asset(String sus_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String sql1 = "";
			String sql2 = "";
			double dep = 0.0;
			double th = 0.0;

			PreparedStatement stmt = null;

			sql2 = "SELECT depreciation_val as dep, threshold_val as th FROM tb_tms_e_asset_condition WHERE id=(select max(id) from tb_tms_e_asset_condition)";
			stmt = conn.prepareStatement(sql2);
			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				dep = (Float.parseFloat(rs1.getString("dep")));
				th = (Float.parseFloat(rs1.getString("th")));
			}
			rs1.close();
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			sql1 = "UPDATE tb_tms_banum_req_child t \r\n" + "    SET present_cost =( case \r\n"
					+ "			when cast(date_part('year',age(now(), t.year_of_entry)) as integer) <= 1 \r\n"
					+ "			then (case when present_cost = '' \r\n"
					+ "				then (cast(cast(purchas_cost as float)-(cast(purchas_cost as float)*" + (dep / 100)
					+ ") as character varying))\r\n"
					+ "				when present_cost != '' and cast(present_cost as float)-(cast(present_cost as float)*"
					+ (dep / 100) + ")>=(cast(purchas_cost as float)*" + (th / 100) + ")\r\n"
					+ "			        then (cast(cast(present_cost as float)-(cast(present_cost as float)*"
					+ (dep / 100) + ") as character varying))\r\n" + "			else present_cost\r\n"
					+ "			END )\r\n"
					+ "			when cast(date_part('year',age(now(), t.year_of_entry)) as integer) > 1 \r\n"
					+ "			then (case when present_cost = '' \r\n"
					+ "				then (cast(cast(purchas_cost as float)-(cast(purchas_cost as float)*(((date_part('year',age(now(), t.year_of_entry)))*10)/100)) as character varying))\r\n"
					+ "				when present_cost != '' and cast(present_cost as float)-(cast(present_cost as float)*(((date_part('year',age(now(), t.year_of_entry)))*10)/100)) >= (cast(purchas_cost as float)*'"
					+ th + "')\r\n"
					+ "			        then (cast(cast(present_cost as float)-(cast(present_cost as float)*(((date_part('year',age(now(), t.year_of_entry)))*10)/100)) as character varying))\r\n"
					+ "			else present_cost\r\n" + "			END )\r\n" + "			else present_cost\r\n"
					+ "			END ),update_status= cast(? as character varying)"
					+ "from tb_tms_mvcr_parta_dtl p,tb_tms_banum_dirctry d\r\n"
					+ "where d.ba_no = t.ba_no and t.update_status != cast(? as character varying)";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, year);
			stmt.setInt(2, year);
			int rs2 = stmt.executeUpdate();

			sql = "select distinct m.mct_nomen as nomen, " + "	SUBSTR(cast(d.mct as character varying),1,4) as mct1, "
					+ "	ROUND(sum( cast( r.present_cost  AS decimal) ), 2) as pre, " + "	count(d.ba_no) as uh, "
					+ "   (ROUND(sum( cast( r.present_cost  AS decimal) ), 2) * count(d.ba_no)) as total "
					+ " from \r\n" + "	tb_tms_mvcr_parta_dtl p \r\n"
					+ "	inner join tb_tms_banum_dirctry d on d.ba_no = p.ba_no and left(cast(d.mct as character varying), 4) = SUBSTRING(CAST(d.mct AS varchar),1,4)\r\n"
					+ "	left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4)\r\n"
					+ "	left join tb_tms_banum_req_child r on SUBSTRING(CAST(r.mct_number  AS varchar),1,4) =m.mct_main_id and r.ba_no = p.ba_no  and r.mct_number = d.mct\r\n"
					+ "	where p.sus_no =  ? group by m.mct_nomen,d.mct";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("mct1"));
				list.add(rs.getString("nomen"));
				list.add(rs.getString("pre"));
				list.add(rs.getString("uh"));
				list.add(rs.getString("total"));
				alist.add(list);
			}
			rs.close();
			// rs1.close();
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
		return alist;
	}

	public ArrayList<ArrayList<String>> getAttributeFromMCTwiseMvcr(String sus_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			
			sql = "select m.mct_nomen,v.mct_main_id,v.ue,v.total_uh\r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh v\r\n" + 
					"left join tb_tms_mct_main_master m on  v.mct_main_id = m.mct_main_id\r\n" + 
					"where v.sus_no =? and v.type='B' and (v.ue > 0 or v.total_uh > 0) order by v.prf_code ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
				alist.add(list);
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
		return alist;
	}

	public ArrayList<ArrayList<String>> getAttributeprfwiseMvcr(String sus_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select m.mct_nomen,v.mct_main_id,v.ue,v.total_uh,m.prf_code,s.group_name " + 
					" from tms_vehicle_status_a_b_c_with_ue_uh v\r\n" + 
					" left join tb_tms_mct_main_master m on  v.mct_main_id = m.mct_main_id\r\n" + 
					" left join tb_tms_mct_slot_master s on m.prf_code = s.prf_code " +
					" where v.sus_no =? and v.type='B' and (v.ue > 0 or v.total_uh > 0) order by v.prf_code ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public List<String> getBA_noFromIssueType(String sus_no, String mct, String issue_type) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			if (issue_type.equals("-1")) {
				sql += "select distinct ba_no,classification from tb_tms_mvcr_parta_dtl  where ba_no in (select ba_no from tb_tms_banum_dirctry where SUBSTRING(CAST(mct AS varchar),1,4) =?)  and sus_no = ?  ";
			} else {
				sql += "select distinct ba_no,classification from tb_tms_mvcr_parta_dtl  where ba_no in (select ba_no from tb_tms_banum_dirctry where SUBSTRING(CAST(mct AS varchar),1,4) =?)  and sus_no =? and issue_type=? ";
			}
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (issue_type.equals("-1")) {
				stmt.setString(1, mct);
				stmt.setString(2, sus_no);

			} else {
				stmt.setString(1, mct);
				stmt.setString(2, sus_no);
				stmt.setString(3, issue_type);
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
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

	public ArrayList<ArrayList<String>> search_unit_holding_detailsList(String sus_no) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select 	distinct m.mct_nomen,\r\n" + 
					"	 d.mct_main_id as mct_main_id,	\r\n" + 
					"	 coalesce((SELECT sum(a.auth_amt) AS total\r\n" + 
					"			   FROM (SELECT b_1.sus_no,a_1.we_pe_no,a_1.mct_no,a_1.auth_amt,' '::text AS condition,'BASEAUTH'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					where a_1.mct_no =d.mct_main_id and b_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,a_1.we_pe_no,b_1.mct_no,sum(b_1.amt_inc_dec) AS sum,''::text AS condition,'MOD'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"					WHERE a_1.status::text = '1'::text and b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.in_lieu_mct,b_1.actual_inlieu_auth,b_1.condition,'INLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.in_lieu_mct =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,b_1.amt_inc_dec,b_1.condition,'INCDEC'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"					where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no\r\n" + 
					"					UNION\r\n" + 
					"					SELECT DISTINCT a_1.sus_no,b_1.we_pe_no,b_1.mct_no,sum(b_1.actual_inlieu_auth) * '-1'::integer,' '::text AS condition,'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"					FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"					JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"					JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"					JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"				where b_1.mct_no =d.mct_main_id and a_1.sus_no =d.sus_no	 \r\n" + 
					"				GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + 
					"			) a\r\n" + 
					"			JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"			GROUP BY a.sus_no, a.we_pe_no, a.mct_no  ORDER BY a.we_pe_no ),0) as ue,\r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='5'  and p.sus_no =  d.sus_no) as against_ue,  \r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='3' and p.sus_no =  d.sus_no) as over_ue,   \r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='0' and p.sus_no =  d.sus_no) as loan,\r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='9' and p.sus_no =  d.sus_no) as sec_store,\r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='10' and p.sus_no =  d.sus_no) as acsfp,\r\n" +
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='F' and p.sus_no =  d.sus_no)  as freash_release,\r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and issue_type='4' and p.sus_no =  d.sus_no)  as gift,\r\n" + 
					"	(select count(p.issue_type) from tb_tms_mvcr_parta_dtl p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = d.mct_main_id) and (issue_type='5' or issue_type='3' or issue_type='0' or issue_type='9' or issue_type='10' or issue_type='F') and p.sus_no = d.sus_no) as total_uh \r\n" + 
					"from \r\n" + 
					"(select distinct a.mct_main_id,a.sus_no,sum(a.total) from \r\n" + 
					"	(select distinct SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,part.sus_no,count(d.ba_no) as total from tb_tms_mvcr_parta_dtl part \r\n" + 
					"		inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"		where part.sus_no= ?\r\n" + 
					"	 group by 1,2\r\n" + 
					"	UNION\r\n" + 
					"	SELECT a_1.mct_no as mct_main_id,b_1.sus_no,a_1.auth_amt  as total\r\n" + 
					"	   FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"	   JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"	 where b_1.sus_no =?\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.amt_inc_dec)  as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"	  WHERE a_1.status::text = '1'::text  and a_1.sus_no =?\r\n" + 
					"	  GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no        \r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.in_lieu_mct as mct_main_id,a_1.sus_no,b_1.actual_inlieu_auth as total\r\n" + 
					"	   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		 JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"		 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		where a_1.sus_no =?\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,b_1.amt_inc_dec as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		where a_1.sus_no =?\r\n" + 
					"	UNION\r\n" + 
					"	SELECT b_1.mct_no as mct_main_id,a_1.sus_no,sum(b_1.actual_inlieu_auth) * '-1'::integer as total\r\n" + 
					"		FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"		JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"		JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"		JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		where a_1.sus_no =?\r\n" + 
					"		GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no) as a\r\n" + 
					" 		where a.total > 0\r\n" + 
					"		group by 1,2 ) as d\r\n" + 
					"		left join tb_tms_mct_main_master m on  m.mct_main_id = d.mct_main_id\r\n" +
					" order by d.mct_main_id ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, sus_no);
			stmt.setString(5, sus_no);
			stmt.setString(6, sus_no);
		
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("mct_nomen"));
				list1.add(rs.getString("mct_main_id"));
				list1.add(rs.getString("ue"));
				list1.add(rs.getString("against_ue"));
				list1.add(rs.getString("over_ue"));
				list1.add(rs.getString("loan"));
				list1.add(rs.getString("sec_store"));
				list1.add(rs.getString("acsfp"));
				list1.add(rs.getString("freash_release"));
				list1.add(rs.getString("gift"));
				list1.add(rs.getString("total_uh"));
				list.add(list1);
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

	public ArrayList<ArrayList<String>> generate_baNo_presentCost(String sus_no, String mct) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select b.ba_no, r.present_cost  from TB_TMS_BANUM_DIRCTRY b,tb_tms_mvcr_parta_dtl p ,tb_tms_banum_req_child r where  SUBSTRING(cast(b.mct as varchar), 1, 4) = ?  and b.ba_no = p.ba_no and b.ba_no = r.ba_no and p.sus_no=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mct);
			stmt.setString(2, sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	public @ResponseBody List<TB_TMS_MVCR_PARTA_MAIN> getApproveDate(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_MVCR_PARTA_MAIN where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MVCR_PARTA_MAIN> list = (List<TB_TMS_MVCR_PARTA_MAIN>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public @ResponseBody List<String> getwepeno(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select wepe_trans_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no =:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public ArrayList<List<String>> getMVCRReportListPending(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select * from TB_TMS_MVCR_PARTA_DTL where sus_no=? ORDER BY SUBSTRING(ba_no, 3,10)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("ba_no"));// 1
				list.add(rs.getString("kms_run"));// 2
				list.add(rs.getString("classification"));// 3
				list.add(rs.getString("remarks")); // 4
				list.add(rs.getString("miso_rmk")); // 5

				String classification = "<select id='classification" + rs.getString("id") + "' name='classification"
						+ rs.getString("id") + "' disabled='true' > " + "<option value='1'>I</option>"
						+ "<option value='2'>II</option>" + "<option value='3'>III</option>"
						+ "<option value='4'>IV</option>" + "<option value='5'>V</option>"
						+ "<option value='6'>VI</option>" + "<option value='7'>VII</option>" + "</select>";

				String unitRmk = null;
				String misoRmk = null;
				if (rs.getString("remarks") == null) {
					unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk"
							+ rs.getString("id") + "' value='" + "" + "' style='width:80%;' disabled='true'/>";
				} else {
					unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk"
							+ rs.getString("id") + "' value='" + rs.getString("remarks")
							+ "' style='width:80%;' disabled='true'/>";
				}
				if (rs.getString("miso_rmk") == null) {
					misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk"
							+ rs.getString("id") + "' value='" + "" + "' style='width:80%;' disabled='true' />";
				} else {
					misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk"
							+ rs.getString("id") + "' value='" + rs.getString("miso_rmk")
							+ "' style='width:80%;' disabled='true' />";
				}

				String kms_run = "<input type='text' id='kms_run" + rs.getString("id") + "' name='kms_run"
						+ rs.getString("id") + "' value='" + rs.getString("kms_run")
						+ "' style='width:100%;' disabled='true' />";
				list.add(kms_run);
				list.add(classification);
				list.add(unitRmk);
				list.add(misoRmk);
				alist.add(list);
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
		return alist;
	}
	
	public ArrayList<List<String>> getMVCRReportListForApproval(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select * from TB_TMS_MVCR_PARTA_DTL where sus_no=? ORDER BY SUBSTRING(ba_no, 3,10)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("ba_no"));// 0
				list.add(rs.getString("kms_run"));// 1
				list.add(getClassification(rs.getString("classification"))); // 2
				
				String ser_status ="";
				String ser_reason = "";
				
				if (rs.getString("ser_status") == null) {
					ser_status = "<input type='checkbox' disabled='true' style='height:20px;'>";
					ser_reason = "";
				}else {
					if (rs.getString("ser_status").equals("1")) {
						ser_status = "<input type='checkbox' checked='checked' disabled='true' style='height:20px;'>";
						ser_reason = getSer_reason(rs.getString("ser_reason"));
					}else {
						ser_status = "<input type='checkbox' disabled='true' style='height:20px;'>";
						ser_reason = "";
					}
				}
				list.add(ser_status+ser_reason); // 3
				list.add(rs.getString("remarks")); // 4 unit Remark
				list.add(rs.getString("ser_status")); //5
				alist.add(list);
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
		return alist;
	}
	
	public String getSer_reason(String id) {
		String ser_reason ="";
		if(id.equals("1")) {
			ser_reason = "In Wksp for repair";
		}else if(id.equals("2")) {
			ser_reason = "Tyre/Tube/Bty Demand";
		}else if(id.equals("3")) {
			ser_reason = "MUA Demand";
		}else if(id.equals("4")) {
			ser_reason = "Accident";
		}else if(id.equals("5")) {
			ser_reason = "BOH";
		}else if(id.equals("6")) {
			ser_reason = "Under Discard";
		}else if(id.equals("7")) {
			ser_reason = "Defect/Follow Up Report";
		}else{
			ser_reason = "";
		}
		return ser_reason;
	}
	
	public String getClassification(String id) {
		String ser_reason ="";
		if(id.equals("1")) {
			ser_reason = "I";
		}else if(id.equals("2")) {
			ser_reason = "II";
		}else if(id.equals("3")) {
			ser_reason = "III";
		}else if(id.equals("4")) {
			ser_reason = "IV";
		}else if(id.equals("5")) {
			ser_reason = "V";
		}else if(id.equals("6")) {
			ser_reason = "VI";
		}else if(id.equals("7")) {
			ser_reason = "VII";
		}
		return ser_reason;
	}
	
	public ArrayList<List<String>> UpdategetMVCRReportListPending(String qry, String sus_no, String roleType,String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select * from TB_TMS_MVCR_PARTA_DTL where sus_no = ? and ba_no in (select ba_no from tb_tms_banum_dirctry) ORDER BY SUBSTRING(ba_no, 3,10) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			int Total = 0;
			int Update = 0;
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("ba_no"));// 1
				list.add(rs.getString("kms_run"));// 2
				list.add(rs.getString("classification"));// 3
				list.add(rs.getString("remarks")); // 4
				list.add(rs.getString("miso_rmk")); // 5
				
				String unitRmk = null;
				String misoRmk = null;
				String classification = null;
				String kms_run = null;
				String Last_km_run = null;
				String f = "";
				String div = "";
				String lastclass = "";
				String ser_status = "";
				String ser_reason = "";
				
				Total++;
				if (rs.getString("status") == "0" || rs.getString("status").equals("0")) {

					Last_km_run = "<label id='" + rs.getString("id") + "'>" + rs.getString("kms_run") + "</label>";
					lastclass = "<label style='display:none;' id='lastclass" + rs.getString("id") + "'>" + rs.getString("classification") + " </label>";
					
					int classi = 1;
					if(rs.getString("classification") != null) {
						classi = Integer.parseInt(rs.getString("classification"));
					}
					
					if (classi < 5 || rs.getString("authrty_letter_no") == null || rs.getString("authrty_letter_no").equals("") || rs.getString("authrty_letter_no") == "" || rs.getString("authrty_letter_no").equals(null)) {
						div = "<div id='" + rs.getString("ba_no") + "' style='display:none;'><input type='text' id='authrty_letter_no" + rs.getString("id") + "' name='authrty_letter_no" + rs.getString("id") + "' value='" + rs.getString("authrty_letter_no") + "' placeholder='Authority' style='width:100%;' /> </div>";
					} else {
						div = "<div id='" + rs.getString("ba_no") + "' ><input type='text' id='authrty_letter_no" + rs.getString("id") + "' name='authrty_letter_no" + rs.getString("id") + "' value='" + rs.getString("authrty_letter_no") + "' placeholder='Authority' style='width:100%;'  /> </div>";

					}
					String ChangeClassi = "onchange=ChangeClassification('" + rs.getString("ba_no") + "','"+rs.getString("id")+"')";
					classification = "<select id='classification" + rs.getString("id") + "' " + ChangeClassi
							+ "  name='classification" + rs.getString("id") + "'  > " + "<option value='1'>I</option>"
							+ "<option value='2'>II</option>" + "<option value='3'>III</option>"
							+ "<option value='4'>IV</option>" + "<option value='5'>V</option>"
							+ "<option value='6'>VI</option>" + "<option value='7'>VII</option>" + "</select>" + div;
					if (rs.getString("remarks") == null) {
						unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk" + rs.getString("id") + "' value='" + "" + "' style='width:90%;' disabled='true'/>";

					} else {
						unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk" + rs.getString("id") + "' value='" + rs.getString("remarks") + "' style='width:90%;' disabled='true'/>";

					}
					if (rs.getString("miso_rmk") == null) {
						misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk" + rs.getString("id") + "' value='" + "" + "' style='width:80%;' disabled='true' />";

					} else {
						misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk" + rs.getString("id") + "' value='" + rs.getString("miso_rmk") + "' style='width:80%;' disabled='true' />";

					}				
					///onchange={kmcheck('" + rs.getString("id") + "','" + rs.getString("ba_no") + "','" + rs.getString("kms_run") + "')}
					kms_run = "<input type='text'  id='kms_run"
							+ rs.getString("id") + "' name='kms_run" + rs.getString("id") + "' value='" + rs.getString("kms_run") + "' style='width:100%;'/>";

					//UNSV Change 070521
//					ser_reason = "<select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%' disabled='true'>" 
//							+ "<option value='0'>--Select--</option>"
//							+ "<option value='1'>In Wksp for repair</option>"
//							+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
//							+ "<option value='3'>MUA Demand</option>"
//							+ "<option value='4'>Accident</option>" 
//							+ "<option value='5'>BOH</option>"
//							+ "<option value='6'>Under Discard</option>"
//							+ "<option value='7'>Defect/Follow Up Report</option></select>";
				
					if (rs.getString("ser_reason") != null) { // Check if ser_reason is not null
					    ser_reason = "<select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%' disabled='true'>" 
								+ "<option value='0'" + (rs.getString("ser_reason").equals("0") ? " selected" : "") + ">--Select--</option>"
								+ "<option value='1'" + (rs.getString("ser_reason").equals("1") ? " selected" : "") + ">In Wksp for repair</option>"
								+ "<option value='2'" + (rs.getString("ser_reason").equals("2") ? " selected" : "") + ">Tyre/Tube/Bty Demand</option>" 
								+ "<option value='3'" + (rs.getString("ser_reason").equals("3") ? " selected" : "") + ">MUA Demand</option>"
								+ "<option value='4'" + (rs.getString("ser_reason").equals("4") ? " selected" : "") + ">Accident</option>" 
								+ "<option value='5'" + (rs.getString("ser_reason").equals("5") ? " selected" : "") + ">BOH</option>"
								+ "<option value='6'" + (rs.getString("ser_reason").equals("6") ? " selected" : "") + ">Under Discard</option>"
								+ "<option value='7'" + (rs.getString("ser_reason").equals("7") ? " selected" : "") + ">Defect/Follow Up Report</option></select>";
					} else {
						ser_reason = "<select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%' disabled='true'>" 
						+ "<option value='0'>--Select--</option>"
						+ "<option value='1'>In Wksp for repair</option>"
						+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
						+ "<option value='3'>MUA Demand</option>"
						+ "<option value='4'>Accident</option>" 
						+ "<option value='5'>BOH</option>"
						+ "<option value='6'>Under Discard</option>"
						+ "<option value='7'>Defect/Follow Up Report</option></select>";
					}
					
					
					
					
					
					String ChangeSer_Statusa = "onchange=ChangeSer_Status('" + rs.getString("ba_no") + "','"+rs.getString("id")+"','1')";
					String ChangeSer_Statusp = "onchange=ChangeSer_Status('" + rs.getString("ba_no") + "','"+rs.getString("id")+"','0')";
			
					if (rs.getString("ser_status") == null) {
						ser_status = "SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' value='0' disabled='true' style='height:20px;width:15%;'>"+
									"UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' value='1' disabled='true' style='height:20px;width:15%;'>";
						ser_reason = "";
					}else {
						if (rs.getString("ser_status").equals("1")) {
							ser_status = "SERV : <input type='radio'  id='ser_status"+rs.getString("id")+"' name='ser_status"+rs.getString("id")+"'disabled='true' style='height:20px;width:15%;' value='0' "+ChangeSer_Statusp+">"+
										"UNSV : <input type='radio'     id='ser_status"+rs.getString("id")+"' name='ser_status"+rs.getString("id")+"'  checked='checked' disabled='true' style='height:20px;width:15%;' value='1' "+ChangeSer_Statusa+">";
							ser_reason="<div id='ser_reason"+rs.getString("ba_no")+"' style='padding-top:5px;'>"+ser_reason+"</div>";
						}else {
							ser_status = "SERV : <input type='radio' checked='checked' id='ser_status"+rs.getString("id")+"' name='ser_status"+rs.getString("id")+"' disabled='true'  value='0' style='height:20px;width:15%;' "+ChangeSer_Statusp+">"+
									"UNSV : <input type='radio' id='ser_status"+rs.getString("id")+"' name='ser_status"+rs.getString("id")+"' disabled='true' style='height:20px;width:15%;' value='1' "+ChangeSer_Statusa+">";
							ser_reason = "";
						}
				
//						if(rs.getString("ser_status").equals("1")){
//							ser_status="SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusp+" value='0' style='height:20px;width:15%;'>"+
//									"UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' checked='checked' "+ChangeSer_Statusa+" value='1' style='height:20px;width:15%;'>";
//							ser_reason="<div id='ser_reason"+rs.getString("ba_no")+"' style='padding-top:5px;'>"+ser_reason+"</div>";
//						}else{
//							ser_status="SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusp+" value='0' style='height:20px;width:15%;'>"+
//									" UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusa+" value='1' style='height:20px;width:15%;'>";
//							ser_reason="<div id='ser_reason"+rs.getString("ba_no")+"' style='display:none;' style='padding-top:5px;'>"+ser_reason+"</div>";
//						}
					}
					//UNSV Change 070521
					
					String update = "onclick=submitData('" + rs.getString("ba_no") + "','" + rs.getString("id") + "')";
					f = "<button id='updateId" + rs.getString("id") + "' " + update + " class='btn btn-primary btn-sm' title='Update Data' style='padding: .1rem .1rem;'>Updated</button> &nbsp; &nbsp";
	Update++;
				} else {
					Last_km_run = "<label id='" + rs.getString("id") + "'>" + rs.getString("kms_run") + "</label>";
					lastclass = "<label style='display:none;' id='lastclass" + rs.getString("id") + "'>" + rs.getString("classification") + "</label>";

					int classi = 1;
					if(rs.getString("classification") != null) {
						classi = Integer.parseInt(rs.getString("classification"));
					}
					if (classi < 5 || rs.getString("authrty_letter_no") == null || rs.getString("authrty_letter_no").equals("") || rs.getString("authrty_letter_no") == "" || rs.getString("authrty_letter_no").equals(null)) {
						div = "<div id='"+rs.getString("ba_no")+"' style='display:none;'><input type='text' id='authrty_letter_no" + rs.getString("id") + "' name='authrty_letter_no" + rs.getString("id") + "' value='" + "" + "' placeholder='Authority' style='width:100%;'/> </div>";
					} else {
						div = "<div id='"+rs.getString("ba_no")+"'><input type='text' id='authrty_letter_no"+rs.getString("id")+"' name='authrty_letter_no" + rs.getString("id") + "' value='" + rs.getString("authrty_letter_no") + "' placeholder='Authority' style='width:100%;'/> </div>";
					}
					String ChangeClassi = "onchange=ChangeClassification('" + rs.getString("ba_no") + "','"+rs.getString("id")+"')";

					classification = "<select id='classification" + rs.getString("id") + "' " + ChangeClassi
							+ "  name='classification" + rs.getString("id") + "'  > " + "<option value='1'>I</option>"
							+ "<option value='2'>II</option>" + "<option value='3'>III</option>"
							+ "<option value='4'>IV</option>" + "<option value='5'>V</option>"
							+ "<option value='6'>VI</option>" + "<option value='7'>VII</option>" + "</select>" + div;
					if (rs.getString("remarks") == null) {
						unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk" + rs.getString("id") + "' value='" + "" + "' style='width:90%;' />";
					} else {
						unitRmk = "<input type='text' id='unitRmk" + rs.getString("id") + "' name='unitRmk" + rs.getString("id") + "' value='" + rs.getString("remarks") + "' style='width:90%;' />";
					}
					if (rs.getString("miso_rmk") == null) {
						misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk" + rs.getString("id") + "' value='" + "" + "' style='width:80%;'  />";
					} else {
						misoRmk = "<input type='text' id='misoRmk" + rs.getString("id") + "' name='misoRmk" + rs.getString("id") + "' value='" + rs.getString("miso_rmk") + "' style='width:80%;'  />";
					}
					kms_run = "<input type='text' onchange={kmcheck('" + rs.getString("id") + "','" + rs.getString("ba_no") + "','" + rs.getString("kms_run") + "')} id='kms_run"
							+ rs.getString("id") + "' name='kms_run" + rs.getString("id") + "' value='" + rs.getString("kms_run") + "' style='width:100%;'/>";


					//UNSV Change 070521
					ser_reason = "<select id='ser_reason" + rs.getString("id") + "' name='ser_reason" + rs.getString("id") + "' style='width:100%'>" 
							+ "<option value='0'>--Select--</option>"
							+ "<option value='1'>In Wksp for repair</option>"
							+ "<option value='2'>Tyre/Tube/Bty Demand</option>" 
							+ "<option value='3'>MUA Demand</option>"
							+ "<option value='4'>Accident</option>" 
							+ "<option value='5'>BOH</option>"
							+ "<option value='6'>Under Discard</option>"
							+ "<option value='7'>Defect/Follow Up Report</option></select>";
					
					String ChangeSer_Statusa = "onchange=ChangeSer_Status('" + rs.getString("ba_no") + "','"+rs.getString("id")+"','1')";
					String ChangeSer_Statusp = "onchange=ChangeSer_Status('" + rs.getString("ba_no") + "','"+rs.getString("id")+"','0')";
					
					if(rs.getString("ser_status") == null){
						ser_status = "SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusp+" value='0' style='height:20px;display:inline;width:15%;' value='USNV'>"
								+ "UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusa+" value='1' style='height:20px;width:15%;' value='SER'>";
						
						ser_reason = "<div id='ser_reason"+rs.getString("ba_no")+"' style='display:none;padding-top:5px;' >"+ser_reason+"</div>";
					}else{
						if(rs.getString("ser_status").equals("1")){
							ser_status="SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusp+" value='0' style='height:20px;width:15%;'>"+
									"UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' checked='checked' "+ChangeSer_Statusa+" value='1' style='height:20px;width:15%;'>";
							ser_reason="<div id='ser_reason"+rs.getString("ba_no")+"' style='padding-top:5px;'>"+ser_reason+"</div>";
						}else{
							ser_status="SERV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusp+" value='0' style='height:20px;width:15%;'>"+
									" UNSV : <input type='radio' id='ser_status" + rs.getString("id") + "' name='ser_status"+rs.getString("id")+"' "+ChangeSer_Statusa+" value='1' style='height:20px;width:15%;'>";
							ser_reason="<div id='ser_reason"+rs.getString("ba_no")+"' style='display:none;' style='padding-top:5px;'>"+ser_reason+"</div>";
						}
					}
					//UNSV Change 070521
					
					String update = "onclick=submitData('" + rs.getString("ba_no") + "','" + rs.getString("id") + "')";
					f = "<button id='updateId" + rs.getString("id") + "' " + update + " class='btn btn-primary btn-sm' title='Update Data' style='padding: .1rem .1rem;'>Update</button> &nbsp; &nbsp";

				}
				list.add(Last_km_run);// 6
				list.add(kms_run);// 7
				list.add(classification);// 8
				list.add(unitRmk);// 9
				list.add(misoRmk);// 10
				if(roleAccess.equals("MISO") || roleAccess.equals("Unit")) {
					list.add(f);// 11
				}else {
					list.add("NA");// 11
				}
				list.add(div);// 12
				list.add(lastclass);// 13
				list.add(String.valueOf(Total));// 14
				list.add(String.valueOf(Update));// 15
				list.add(ser_status + ser_reason);//16
				list.add(rs.getString("ser_reason")); //17
				alist.add(list);
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
		return alist;
	}
	
	public boolean update_mvcr_history_tb(String sus_no) {
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        String qry = " update tb_tms_mvcr_parta_dtl_history set status = 2 where ba_no in (select ba_no from tb_tms_mvcr_parta_dtl where sus_no = ? )";

	        PreparedStatement stmt = conn.prepareStatement(qry);
	        stmt.setString(1, sus_no);
	        int rowsAffected = stmt.executeUpdate();
	        stmt.close();

	       
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	   
	    return false;
	}
	
	
	
	
	//B Vehicle MVCR
	public ArrayList<List<String>> getMVCRList(String sus_no,String roleAccess) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                String sql = "";
                sql = "select distinct \r\n"
                		+ "part.ba_no,\r\n"
                		+ "part.classification,\r\n"
                		+ "COALESCE(kms_run,0) as km_run,\r\n"
                		+ "part.remarks,\r\n"
                		+ "part.engine_no,\r\n"
                		+ "chasis_no,\r\n"
                		+ "\r\n"
                		+ "case when oh_cal.km1 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_i_due,\r\n"
                		+ "case when oh_cal.km2 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_ii_due,\r\n"
                		+ "case when oh_cal.km3 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_iii_due,\r\n"
                		+ "\r\n"
                		+ "\r\n"
                		+ "       oh_i_done,\r\n"
                		+ "       oh_ii_done,\r\n"
                		+ "       oh_iii_done,\r\n"
                		+ "       mr_i_due,\r\n"
                		+ "       mr_i_done,\r\n"
                		+ "       mr_ii_due,\r\n"
                		+ "       mr_ii_done,\r\n"
                		+ "       mr_iii_due,\r\n"
                		+ "       mr_iii_done,\r\n"
                		+ "       mtd_launcher_type,\r\n"
                		+ "       launcher_regn_no,\r\n"
                		+ "       type_of_msls_fired,\r\n"
                		+ "       COALESCE(qty_of_msls_fired,0) as qty_of_msls_fired,\r\n"
                		+ "       launcher_due,\r\n"
                		+ "       launcher_done,\r\n"
                		+ "       COALESCE(qty_of_rds_fired,0) as qty_of_rds_fired,\r\n"
                		+ "       COALESCE(qty_of_rds_misfired,0) as qty_of_rds_misfired,\r\n"
                		+ "       type_of_br_sys,\r\n"
                		+ "       br_sys_ser,\r\n"
                		+ "       COALESCE(no_of_passes,0) as no_of_passes,\r\n"
                		+ "       COALESCE(total_passes,0) as total_passes,\r\n"
                		+ "       type_of_rdr,\r\n"
                		+ "       rdr_ser\r\n"
                		+ "	   \r\n"
                		+ "  from tb_tms_mvcr_parta_dtl part\r\n"
                		+ " left join tb_tms_banum_dirctry d\r\n"
                		+ "    on d.ba_no::text = part.ba_no::text\r\n"
                		+ "  left join tb_tms_mct_main_master m\r\n"
                		+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
                		+ "   and m.type_of_veh::text = 'B'::text\r\n"
                		+ " left join tb_miso_orbat_unt_dtl u\r\n"
                		+ "    on part.sus_no = u.sus_no\r\n"
                		+ "   and u.status_sus_no = 'Active'\r\n"
                		+ "  left join (\r\n"
                		+ "        select mct_main_id,\r\n"
                		+ "               max(case when oh_mr = '2' then km end) as km1,\r\n"
                		+ "               max(case when oh_mr = '2' then cast(vintage as INTEGER) end) as vintage1,\r\n"
                		+ "               max(case when oh_mr = '4' then km end)as km2,\r\n"
                		+ "               max(case when oh_mr = '4' then cast(vintage as INTEGER) end) as vintage2,\r\n"
                		+ "               max(case when oh_mr = '6' then km end) as km3,\r\n"
                		+ "               max(case when oh_mr = '6' then cast(vintage as INTEGER) end) as vintage3,\r\n"
                		+ "				  max(case when oh_mr = '1' then km end) as mr_km1,"
                		+ "				  max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as mr_vintage1,\r\n"
                		+ "               max(case when oh_mr = '3' then km end) as mr_km2,\r\n"
                		+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as mr_vintage2,\r\n"
                		+ "               max(case when oh_mr = '5' then km end) as mr_km3,\r\n"
                		+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as mr_vintage3\r\n"
                		+ "          from tb_tms_mct_main_oh_mr\r\n"
                		+ "         group by mct_main_id\r\n"
                		+ "       ) as oh_cal\r\n"
                		+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
                		+ " left join tb_miso_orbat_line_dte u1\r\n"
                		+ "    on m.sus_no=u1.line_dte_sus\r\n"
                		+ "  left join orbat_view_cmd c\r\n"
                		+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
                		+ "  left join orbat_view_corps corps\r\n"
                		+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
                		+ "  left join orbat_view_div div\r\n"
                		+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
                		+ "  left join orbat_view_bde bde\r\n"
                		+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
                		+ "  \r\n"
                		+ " left join (\r\n"
                		+ "        select part2.ba_no,\r\n"
                		+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
                		+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
                		+ "                     end as extracted_year\r\n"
                		+ "          from tb_tms_mvcr_parta_dtl part2\r\n"
                		+ "       ) as ba_registraion\r\n"
                		+ "    on ba_registraion.ba_no = part.ba_no \r\n"
                		+ "	where part.sus_no = ?\r\n"
                		+ "ORDER BY part.ba_no,9,10 \r\n";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sus_no);
                System.err.println("B-->"+stmt);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        List<String> list = new ArrayList<String>();  
                        list.add(rs.getString("ba_no"));
                        list.add(rs.getString("classification"));
                        list.add(rs.getString("km_run"));
                        list.add(rs.getString("remarks"));
                        list.add(rs.getString("engine_no"));
                        list.add(rs.getString("chasis_no"));
                        
                        list.add(rs.getString("oh_i_due"));
                        list.add(rs.getString("oh_i_done"));
                        list.add(rs.getString("oh_ii_due"));
                        list.add(rs.getString("oh_ii_done"));
                        list.add(rs.getString("oh_iii_due"));
                        list.add(rs.getString("oh_iii_done"));
                        
                        list.add(rs.getString("mr_i_due"));
                        list.add(rs.getString("mr_i_done"));
                        list.add(rs.getString("mr_ii_due"));
                        list.add(rs.getString("mr_ii_done"));
                        list.add(rs.getString("mr_iii_due"));
                        list.add(rs.getString("mr_iii_done"));
                        
                        list.add(rs.getString("mtd_launcher_type"));
                        list.add(rs.getString("launcher_regn_no"));
                        list.add(rs.getString("type_of_msls_fired"));
                        list.add(rs.getString("qty_of_msls_fired"));
                        list.add(rs.getString("launcher_due"));
                        list.add(rs.getString("launcher_done"));
                        list.add(rs.getString("qty_of_rds_fired"));
                        list.add(rs.getString("qty_of_rds_misfired"));
                        list.add(rs.getString("type_of_br_sys"));
                        list.add(rs.getString("br_sys_ser"));
                        list.add(rs.getString("no_of_passes"));
                        list.add(rs.getString("total_passes"));
                        list.add(rs.getString("type_of_rdr"));
                        list.add(rs.getString("rdr_ser"));


                        alist.add(list);
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
        return alist;
}
	
/*Add NEW Method by Mitesh*/
	public ArrayList<List<String>> getFullMVCRList(String sus_no,String roleAccess) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                String sql = "";
                sql = "select distinct part.id,part.sus_no,part.issue_type,part.classification,part.authrty_letter_no,part.creation_by,part.creation_date,part.app_rej_remarks,part.status,part.miso_rmk,part.ser_status,part.ser_reason,part.approved_by,part.approve_date1,\r\n"
                		+ "part.ba_no,\r\n"
                		+ "epmnt_clasfctn,\r\n"
                		+ "COALESCE(kms_run,0) as km_run,\r\n"
                		+ "part.remarks,\r\n"
                		+ "part.engine_no,\r\n"
                		+ "chasis_no,\r\n"
                		+ "\r\n"
                		+ "case when oh_cal.km1 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_i_due,\r\n"
                		+ "case when oh_cal.km2 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_ii_due,\r\n"
                		+ "case when oh_cal.km3 =0 then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
                		+ "else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
                		+ "end as oh_iii_due,\r\n"
                		+ "	   \r\n"
                 		+ "       oh_i_done,\r\n"
                		+ "       oh_ii_done,\r\n"
                		+ "       oh_iii_done,\r\n"
                		+ "       mr_i_due,\r\n"
                		+ "       mr_i_done,\r\n"
                		+ "       mr_ii_due,\r\n"
                		+ "       mr_ii_done,\r\n"
                		+ "       mr_iii_due,\r\n"
                		+ "       mr_iii_done,\r\n"
                		+ "	   mtd_launcher_type,\r\n"
                		+ "       launcher_regn_no,\r\n"
                		+ "       type_of_msls_fired,\r\n"
                		+ "       COALESCE(qty_of_msls_fired,0) as qty_of_msls_fired,\r\n"
                		+ "       launcher_due,\r\n"
                		+ "       launcher_done,\r\n"
                		+ "       COALESCE(qty_of_rds_fired,0) as qty_of_rds_fired,\r\n"
                		+ "       COALESCE(qty_of_rds_misfired,0) as qty_of_rds_misfired,\r\n"
                		+ "       type_of_br_sys,\r\n"
                		+ "       br_sys_ser,\r\n"
                		+ "       COALESCE(no_of_passes,0) as no_of_passes,\r\n"
                		+ "       COALESCE(total_passes,0) as total_passes,\r\n"
                		+ "       type_of_rdr,\r\n"
                		+ "       rdr_ser\r\n"
                		+ "	   \r\n"
                		+ "  from tb_tms_mvcr_parta_dtl part\r\n"
                		+ " left join tb_tms_banum_dirctry d\r\n"
                		+ "    on d.ba_no::text = part.ba_no::text\r\n"
                		+ "  left join tb_tms_mct_main_master m\r\n"
                		+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
                		+ "   and m.type_of_veh::text = 'B'::text\r\n"
                		+ " left join tb_miso_orbat_unt_dtl u\r\n"
                		+ "    on part.sus_no = u.sus_no\r\n"
                		+ "   and u.status_sus_no = 'Active'\r\n"
                		+ "  left join (\r\n"
                		+ "        select mct_main_id,\r\n"
                		+ "               max(case when oh_mr = '2' then km end) as km1,\r\n"
                		+ "               max(case when oh_mr = '2' then cast(vintage as INTEGER) end) as vintage1,\r\n"
                		+ "               max(case when oh_mr = '4' then km end)as km2,\r\n"
                		+ "               max(case when oh_mr = '4' then cast(vintage as INTEGER) end) as vintage2,\r\n"
                		+ "               max(case when oh_mr = '6' then km end) as km3,\r\n"
                		+ "               max(case when oh_mr = '6' then cast(vintage as INTEGER) end) as vintage3,\r\n"
                		+ "				  max(case when oh_mr = '1' then km end) as mr_km1,"
                		+ "				  max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as mr_vintage1,\r\n"
                		+ "               max(case when oh_mr = '3' then km end) as mr_km2,\r\n"
                		+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as mr_vintage2,\r\n"
                		+ "               max(case when oh_mr = '5' then km end) as mr_km3,\r\n"
                		+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as mr_vintage3\r\n"
                		+ "          from tb_tms_mct_main_oh_mr\r\n"
                		+ "         group by mct_main_id\r\n"
                		+ "       ) as oh_cal\r\n"
                		+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
                		+ " left join tb_miso_orbat_line_dte u1\r\n"
                		+ "    on m.sus_no=u1.line_dte_sus\r\n"
                		+ "  left join orbat_view_cmd c\r\n"
                		+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
                		+ "  left join orbat_view_corps corps\r\n"
                		+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
                		+ "  left join orbat_view_div div\r\n"
                		+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
                		+ "  left join orbat_view_bde bde\r\n"
                		+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
                		+ "  \r\n"
                		+ " left join (\r\n"
                		+ "        select part2.ba_no,\r\n"
                		+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
                		+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
                		+ "                     end as extracted_year\r\n"
                		+ "          from tb_tms_mvcr_parta_dtl part2\r\n"
                		+ "       ) as ba_registraion\r\n"
                		+ "    on ba_registraion.ba_no = part.ba_no \r\n"
                		+ "	where part.sus_no = ?\r\n"
                		+ "ORDER BY part.ba_no,9,10 \r\n";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sus_no);
                
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        List<String> list = new ArrayList<String>();
                        list.add(rs.getString("id"));//0
                        list.add(rs.getString("sus_no"));
                        list.add(rs.getString("issue_type"));
                        list.add(rs.getString("classification"));
                        list.add(rs.getString("authrty_letter_no"));
                        list.add(rs.getString("creation_by"));//5
                        list.add(rs.getString("creation_date"));
                        list.add(rs.getString("app_rej_remarks"));
                        list.add(rs.getString("status"));
                        list.add(rs.getString("miso_rmk"));
                        list.add(rs.getString("ser_status"));//10
                        list.add(rs.getString("ser_reason"));
                        list.add(rs.getString("approved_by"));
                        list.add(rs.getString("approve_date1"));
                        
                        list.add(rs.getString("ba_no"));
                        list.add(rs.getString("epmnt_clasfctn"));//15
                        list.add(rs.getString("km_run"));
                        list.add(rs.getString("remarks"));
                        list.add(rs.getString("engine_no"));
                        list.add(rs.getString("chasis_no"));
                        
                        list.add(rs.getString("oh_i_due"));
                        list.add(rs.getString("oh_i_done"));
                        list.add(rs.getString("oh_ii_due"));
                        list.add(rs.getString("oh_ii_done"));
                        list.add(rs.getString("oh_iii_due"));
                        list.add(rs.getString("oh_iii_done"));
                        
                        list.add(rs.getString("mr_i_due"));
                        list.add(rs.getString("mr_i_done"));
                        list.add(rs.getString("mr_ii_due"));
                        list.add(rs.getString("mr_ii_done"));
                        list.add(rs.getString("mr_iii_due"));
                        list.add(rs.getString("mr_iii_done"));
                        
                        list.add(rs.getString("mtd_launcher_type"));
                        list.add(rs.getString("launcher_regn_no"));
                        list.add(rs.getString("type_of_msls_fired"));
                        list.add(rs.getString("qty_of_msls_fired"));
                        list.add(rs.getString("launcher_due"));
                        list.add(rs.getString("launcher_done"));
                        list.add(rs.getString("qty_of_rds_fired"));
                        list.add(rs.getString("qty_of_rds_misfired"));
                        list.add(rs.getString("type_of_br_sys"));
                        list.add(rs.getString("br_sys_ser"));
                        list.add(rs.getString("no_of_passes"));
                        list.add(rs.getString("total_passes"));
                        list.add(rs.getString("type_of_rdr"));
                        list.add(rs.getString("rdr_ser"));


                        alist.add(list);
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
        return alist;
}
	 
	 
	 
		public int checkDataExists(String ba_no, String month,String depot_sus_no,String table_name) {
			int count = 0;
			Connection conn = null;
			try{
				String whr ="";
				
				conn = dataSource.getConnection();
				String sql1="";
			    sql1 = "select count(*) as count from "+table_name+"\r\n" + 
			    		"where ba_no = ? "+whr;
			    PreparedStatement stmt = conn.prepareStatement(sql1);
			    stmt.setString(1, ba_no);
			    ResultSet rs1 = stmt.executeQuery();     
			    while(rs1.next()){
			    	count = Integer.parseInt(rs1.getString("count"));
			    }	
			     rs1.close();
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
			return count;
		}
		}