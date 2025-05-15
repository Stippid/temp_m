package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hsqldb.result.ResultMetaData;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_FMCR_HISTORY_TABLE;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_UPLOAD_DOC_MCR;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class MCRDAOImpl implements MCRDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public TB_TMS_CENSUS_RETURN editmcrA(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_CENSUS_RETURN where id=:id");
		q.setParameter("id", id);
		TB_TMS_CENSUS_RETURN list = (TB_TMS_CENSUS_RETURN) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public TB_TMS_CENSUS_RETURN UpdateMCR(TB_TMS_CENSUS_RETURN cp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(cp);
		session.getTransaction().commit();
		session.close();
		return cp;
	}

	public TB_TMS_CENSUS_RETURN Serachmcr(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_CENSUS_RETURN where id=:id");
		q.setParameter("id", id);
		TB_TMS_CENSUS_RETURN list = (TB_TMS_CENSUS_RETURN) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	
		

	public ArrayList<List<String>> getReportMcr(String status, String sus_no, String unit_name, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			String qry = "";
			String qry1 = "";

			if (status != "") {
				qry += " r.status = ?";
			}
			if (sus_no != "") {
				qry += " and r.sus_no = ?";
			}

			if (status != "") {
				qry1 += "status = ?";
			}
			if (sus_no != "") {
				qry1 += " and sus_no = ?";
			}

			if (qry == "") {
				sql = "select distinct r.sus_no,(select unit_name from  tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name from tb_tms_census_retrn_main r  where r.sus_no in (select sus_no from tb_tms_census_retrn)";
			} else {
				sql = "select distinct r.sus_no,(select unit_name from  tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name from tb_tms_census_retrn_main r where r.sus_no in (select sus_no from tb_tms_census_retrn where "
						+ qry1 + ") and" + qry;

			}
			stmt = conn.prepareStatement(sql);
			int j = 1;

			if (!status.equals("")) {

				stmt.setString(j, status);
				j += 1;
			}

			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}

			if (!status.equals("")) {

				stmt.setString(j, status);
				j += 1;
			}

			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("000"+stmt);
			while (rs.next()) {

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no")); // 0
				list.add(rs.getString("unit_name"));// 1

				String Approved = "onclick=\" View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='btn btn-default btn-xs' " + Approved + " title='View Data'>View</i>";

				String partA = "onclick=\" partA('" + rs.getString("sus_no") + "')\"";
				String partAButton = "<i class='btn btn-default btn-xs' " + partA
						+ " title='View Data'>FMCR PART A</i>";

				String partB = "onclick=\"sch('" + rs.getString("sus_no") + "')\"";
				String partBButton = "<i class='btn btn-default btn-xs' " + partB
						+ " title='View Data'>FMCR REPAIR SCH</i>";

				String ApprovedC = "onclick=\" ViewD_B('" + rs.getString("sus_no") + "')\"";
				String ApprovedButton = "<i class='btn btn-default btn-xs' " + ApprovedC
						+ " title='View Data'>FMCR PART B</i>";

				String ApprovedASSET = "onclick=\" ViewA_Aset('" + rs.getString("sus_no") + "')\"";
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
					f += ApprovedButton;
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

	public TB_TMS_CENSUS_RETURN mcrSearchedit(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_CENSUS_RETURN where id=:id");
		q.setParameter("id", id);
		TB_TMS_CENSUS_RETURN list = (TB_TMS_CENSUS_RETURN) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String setRejectmcr(String sus_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_CENSUS_RETURN c set c.status = :status where c.sus_no = :sus_no";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("sus_no", sus_no).executeUpdate();
		tx.commit();
		session.close();
		return String.valueOf(app);

	}

	public String setApprovedmcr(String sus_no,String username, String mct, String roleType) {
		Date dt = new Date();
		
		//update_mvcr_history_tb(sus_no); 
		Session sessionFmcr = HibernateUtil.getSessionFactory().openSession();
		Transaction txFmcr = sessionFmcr.beginTransaction();
		Query qFmcr = sessionFmcr.createQuery("from TB_TMS_CENSUS_RETURN where sus_no=:sus_no order by ba_no");			
		qFmcr.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		String g[]=mct.split(",");
		int i=0;
		
		List<TB_TMS_CENSUS_RETURN> ListFmcr = (List<TB_TMS_CENSUS_RETURN>) qFmcr.list();
		
		for (TB_TMS_CENSUS_RETURN fmcrPartaDtl : ListFmcr) {
			TB_TMS_FMCR_HISTORY_TABLE fmcr_dtl_htr = new TB_TMS_FMCR_HISTORY_TABLE();
			
			  int status = Integer.parseInt(fmcrPartaDtl.getStatus());
		
			int ass =fmcrPartaDtl.getId();
		    fmcr_dtl_htr.setFmcr_id(fmcrPartaDtl.getId());
		    fmcr_dtl_htr.setSus_no(fmcrPartaDtl.getSus_no());
		    fmcr_dtl_htr.setBa_no(fmcrPartaDtl.getBa_no());
		    fmcr_dtl_htr.setApprove_date(fmcrPartaDtl.getApprove_date());
		    fmcr_dtl_htr.setApproved_by(fmcrPartaDtl.getApproved_by());
		    fmcr_dtl_htr.setAprv_rejec_remarks(fmcrPartaDtl.getAprv_rejec_remarks());
//		    fmcr_dtl_htr.setAux_engine_run(fmcrPartaDtl.getAux_engine_run());
		    fmcr_dtl_htr.setAux_engn_clasfctn(fmcrPartaDtl.getAux_engn_clasfctn());
		    fmcr_dtl_htr.setAux_engn_hrs_run(fmcrPartaDtl.getAux_engn_hrs_run());
		    fmcr_dtl_htr.setAux_type(fmcrPartaDtl.getAux_type());
		    fmcr_dtl_htr.setCreation_by(username);
		    fmcr_dtl_htr.setCreation_date(new Date());
		    fmcr_dtl_htr.setDate_of_cens_retrn(fmcrPartaDtl.getDate_of_cens_retrn());
		    fmcr_dtl_htr.setDeleted_by(fmcrPartaDtl.getDeleted_by());
		    fmcr_dtl_htr.setDeleted_date(fmcrPartaDtl.getDeleted_date());
		    fmcr_dtl_htr.setDt_of_submsn(fmcrPartaDtl.getDt_of_submsn());
		    fmcr_dtl_htr.setEngine_hrs_run(Integer.parseInt(fmcrPartaDtl.getEngine_hrs_run()));
		    fmcr_dtl_htr.setEngine_kms_run(Integer.parseInt(fmcrPartaDtl.getEngine_kms_run()));
		    fmcr_dtl_htr.setEngine_type(fmcrPartaDtl.getEngine_type());
//		    fmcr_dtl_htr.setFiller_1(fmcrPartaDtl.getFiller_1());
//		    fmcr_dtl_htr.setFiller_2(fmcrPartaDtl.getFiller_2());
//		    fmcr_dtl_htr.setFiller_3(fmcrPartaDtl.getFiller_3());
		    fmcr_dtl_htr.setIssued_type(fmcrPartaDtl.getIssued_type());
		    fmcr_dtl_htr.setMain_gun_efc(fmcrPartaDtl.getMain_gun_efc());
		    fmcr_dtl_htr.setMain_gun_qr(fmcrPartaDtl.getMain_gun_qr());
		    fmcr_dtl_htr.setMain_gun_type(fmcrPartaDtl.getMain_gun_type());
		    fmcr_dtl_htr.setMain_radio_set(fmcrPartaDtl.getMain_radio_set());
		    fmcr_dtl_htr.setMain_radio_set_condn(fmcrPartaDtl.getMain_radio_set_condn());
		    fmcr_dtl_htr.setMain_radio_set_nomcltr(fmcrPartaDtl.getMain_radio_set_nomcltr());
		    fmcr_dtl_htr.setMain_radio_set_uh(fmcrPartaDtl.getMain_radio_set_uh());
		    fmcr_dtl_htr.setMiso_remarks(fmcrPartaDtl.getMiso_remarks());
		    fmcr_dtl_htr.setModify_by(fmcrPartaDtl.getModify_by());
		    fmcr_dtl_htr.setModify_date(fmcrPartaDtl.getModify_date());
		    fmcr_dtl_htr.setMr1_done(fmcrPartaDtl.getMr1_done());
		    fmcr_dtl_htr.setMr1_due(fmcrPartaDtl.getMr1_done());
		    fmcr_dtl_htr.setMr2_done(fmcrPartaDtl.getMr2_done());
		    fmcr_dtl_htr.setMr2_due(fmcrPartaDtl.getMr2_due());
		    fmcr_dtl_htr.setMr3_done(fmcrPartaDtl.getMr3_done());
		    fmcr_dtl_htr.setMr3_due(fmcrPartaDtl.getMr3_due());
		    fmcr_dtl_htr.setOh1_detl(fmcrPartaDtl.getOh1_detl());
		    fmcr_dtl_htr.setOh1_done(fmcrPartaDtl.getOh1_done());
		    fmcr_dtl_htr.setOh1_due(fmcrPartaDtl.getOh1_due());
//		    fmcr_dtl_htr.setOh2_detl(fmcrPartaDtl.getOh2_detl());
		    fmcr_dtl_htr.setOh2_done(fmcrPartaDtl.getOh2_done());
		    fmcr_dtl_htr.setOh2_due(fmcrPartaDtl.getOh2_due());
		    fmcr_dtl_htr.setSec_gun_type(fmcrPartaDtl.getSec_gun_type());
		    fmcr_dtl_htr.setSer_reason(fmcrPartaDtl.getSer_reason());
		    fmcr_dtl_htr.setSer_status(fmcrPartaDtl.getSer_status());
		    fmcr_dtl_htr.setSoftdelete(fmcrPartaDtl.getSoftdelete());
		    fmcr_dtl_htr.setStatus(fmcrPartaDtl.getStatus());
		    fmcr_dtl_htr.setSus_no(fmcrPartaDtl.getSus_no());
		    fmcr_dtl_htr.setTrack_kms(fmcrPartaDtl.getTrack_kms());
		    fmcr_dtl_htr.setUnit_remarks(fmcrPartaDtl.getUnit_remarks());
		    fmcr_dtl_htr.setVeh_km_run_period(fmcrPartaDtl.getVeh_km_run_period());
		    fmcr_dtl_htr.setVehcl_classfctn(fmcrPartaDtl.getVehcl_classfctn());
		    fmcr_dtl_htr.setVehcl_kms_run(fmcrPartaDtl.getVehcl_kms_run());
		    fmcr_dtl_htr.setVersion_no(fmcrPartaDtl.getVersion_no());
		//    fmcr_dtl_htr.setMct(new BigInteger(g[i]));
		    if(status == 0) {
		    fmcr_dtl_htr.setData_updated(1);	
		    }
//		    fmcr_dtl_htr.setCntry_of_orgn(fmcrPartaDtl.getCntry_of_orgn());
		    
		    sessionFmcr.save(fmcr_dtl_htr);
		i++;
		}
		
		txFmcr.commit();
		sessionFmcr.close();
		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_CENSUS_RETURN c set c.status = :status, c.approve_date=:approve_date, c.modify_date=c.approve_date  where c.sus_no = :sus_no ";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("sus_no", sus_no)
				.setDate("approve_date", dt).executeUpdate();
		tx.commit();
		if (app > 0) {
			tx = session.beginTransaction();
			String hqlUpdate1 = "update TB_TMS_CENSUS_RETURN_MAIN c set c.status = :status, c.approve_date=:approve_date, c.modify_date=c.approve_date  where c.sus_no = :sus_no ";
			int app1 = session.createQuery(hqlUpdate1).setString("status", "1").setString("sus_no", sus_no)
					.setDate("approve_date", dt).executeUpdate();
 
			tx.commit();
			session.close();
			if (app1 > 0) {
				return "FMCR Approved Successfully.";
			} else {
				return "FMCR not Approved Successfully.";
			}

		} else {
			return "FMCR not Approved Successfully.";
		}
	}

	public List<TB_TMS_UPLOAD_DOC_MCR> getdownload(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_UPLOAD_DOC_MCR where sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_UPLOAD_DOC_MCR> list = (List<TB_TMS_UPLOAD_DOC_MCR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public ArrayList<ArrayList<String>> getdetails_ue_uhMvcrlist(String sus_no) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql ="select  \r\n" + 
					"	distinct m.mct_nomen as nomen, \r\n" + 
					"	m.mct_main_id as mct,  \r\n" + 
					"	coalesce((SELECT  sum(a.auth_amt) AS total \r\n" + 
					"				FROM \r\n" + 
					"	(SELECT b_1.sus_no, \r\n" + 
					"		a_1.we_pe_no, \r\n" + 
					"		a_1.mct_no, \r\n" + 
					"		a_1.auth_amt, \r\n" + 
					"		' ' AS condition, \r\n" + 
					"		'BASEAUTH' AS typeofauth \r\n" + 
					"	FROM cue_tb_miso_wepe_transport_det a_1 \r\n" + 
					"	JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no = b_1.wepe_trans_no \r\n" + 
					"	 	AND b_1.status_trans = '1' AND  a_1.status = '1' \r\n" + 
					"	where a_1.mct_no =SUBSTRING(cast(master.mct as character varying), 1, 4) and b_1.sus_no =part.sus_no \r\n" + 
					"	UNION \r\n" + 
					"	SELECT a_1.sus_no, \r\n" + 
					"		a_1.we_pe_no, \r\n" + 
					"		b_1.mct_no, \r\n" + 
					"		sum(b_1.amt_inc_dec) AS sum, \r\n" + 
					"		'' AS condition, \r\n" + 
					"		'MOD' AS typeofauth \r\n" + 
					"	FROM cue_tb_wepe_link_sus_trans_mdfs a_1 \r\n" + 
					"	JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification = b_1.modification  \r\n" + 
					"		AND a_1.we_pe_no = b_1.we_pe_no AND b_1.status = '1' \r\n" + 
					"	WHERE a_1.status = '1' and b_1.mct_no =SUBSTRING(cast(master.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no \r\n" + 
					"	GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no \r\n" + 
					"	UNION \r\n" + 
					"	SELECT a_1.sus_no, \r\n" + 
					"		b_1.we_pe_no, \r\n" + 
					"		b_1.in_lieu_mct, \r\n" + 
					"		b_1.actual_inlieu_auth, \r\n" + 
					"		b_1.condition, \r\n" + 
					"		'INLIEU' AS typeofauth \r\n" + 
					"	FROM cue_tb_wepe_link_sus_trans_footnotes a_1 \r\n" + 
					"	JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote = '0' \r\n" + 
					"	JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no = c.mct_no AND b_1.we_pe_no = c.we_pe_no AND b_1.status = '1' AND a_1.status = '1' \r\n" + 
					"	JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no = link.wepe_trans_no AND link.status_trans = '1' \r\n" + 
					"	where b_1.in_lieu_mct =SUBSTRING(cast(master.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no \r\n" + 
					"	UNION \r\n" + 
					"	SELECT a_1.sus_no, \r\n" + 
					"		b_1.we_pe_no, \r\n" + 
					"		b_1.mct_no, \r\n" + 
					"		b_1.amt_inc_dec, \r\n" + 
					"		b_1.condition, \r\n" + 
					"		'INCDEC' AS typeofauth \r\n" + 
					"	FROM cue_tb_wepe_link_sus_trans_footnotes a_1 \r\n" + 
					"	JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote = '1' AND b_1.status = '1' \r\n" + 
					"	JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no = link.wepe_trans_no AND link.status_trans = '1' \r\n" + 
					"	where b_1.mct_no =SUBSTRING(cast(master.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no \r\n" + 
					"	UNION \r\n" + 
					"	SELECT DISTINCT a_1.sus_no, \r\n" + 
					"		b_1.we_pe_no, \r\n" + 
					"		b_1.mct_no, \r\n" + 
					"		sum(b_1.actual_inlieu_auth) * '-1'::integer, \r\n" + 
					"		' ' AS condition, \r\n" + 
					"		'REDUCEDUEINLIEU' AS typeofauth \r\n" + 
					"	FROM cue_tb_wepe_link_sus_trans_footnotes a_1 \r\n" + 
					"	JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote = '0' \r\n" + 
					"	JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no = c.mct_no AND b_1.we_pe_no = c.we_pe_no AND a_1.status = '1' \r\n" + 
					"	JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no = link.wepe_trans_no AND link.status_trans = '1' \r\n" + 
					"	where b_1.mct_no =SUBSTRING(cast(master.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no	  \r\n" + 
					"	GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no \r\n" + 
					"	) a \r\n" + 
					"JOIN tb_tms_mct_main_master b ON a.mct_no = b.mct_main_id \r\n" + 
					"GROUP BY a.sus_no, a.we_pe_no, a.mct_no \r\n" + 
					"ORDER BY a.we_pe_no),0) as UE, \r\n" + 
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where (issued_type='OP' or issued_type is null) and p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as OP, \r\n" + 
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where issued_type='TRG' and p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as TRG,\r\n" + 
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where issued_type='WWR' and p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as WWR,\r\n" + 
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where issued_type='LOAN' and p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as LOAN,\r\n" +
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where issued_type='SECTOR' and p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as SECTOR,\r\n" +
					"coalesce((select count(p.ba_no) from tb_tms_census_retrn p where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(master.mct  AS varchar),1,4)) and p.sus_no = part.sus_no),0) as UH \r\n" + 
					"from   \r\n" + 
					"tms_vehicle_status_a_b_c_with_ue_uh part   \r\n" + 
				//	"inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"inner join tb_tms_mct_master master on part.mct_main_id = SUBSTRING(cast(master.mct as character varying), 1, 4) and master.type_of_vehicle = 'A' \r\n" + 
					"inner join tb_tms_mct_main_master m on  m.mct_main_id = part.mct_main_id  \r\n" + 
					"where part.sus_no = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			System.err.println("1---"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("mct"));
				list.add(rs.getString("nomen"));
				int uh = 0;
				int ue = 0;
				if (rs.getString("UH") == null) {
					uh = 0;
				} else {
					uh = Integer.parseInt(rs.getString("UH"));
				}
				if (rs.getString("UE") == null) {
					ue = 0;
				} else {
					ue = Integer.parseInt(rs.getString("UE"));
				}
				list.add(String.valueOf(ue));
				list.add(rs.getString("OP"));
				list.add(rs.getString("TRG"));
				list.add(rs.getString("WWR"));
				
				list.add(String.valueOf(uh));
				if (ue >= 0 && uh >= 0) {
					int diff = uh - ue;
					if (diff >= 0) {
						sur = diff;
						defi = 0;
					} else {
						defi = diff;
						sur = 0;
					}
				}
				list.add(String.valueOf(Math.abs(sur)));
				list.add(String.valueOf(Math.abs(defi)));
				list.add(rs.getString("LOAN"));
				list.add(rs.getString("SECTOR"));
				aList.add(list);
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
		return aList;
	}

	/////// A veh E-Assets
	public ArrayList<ArrayList<String>> getdetailsE_AssetsMvclist(String sus_no) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String sql1 = "";
			String sql3 = "";
			String sql2 = "";
			double dep = 0.0;
			double th = 0.0;
			PreparedStatement stmt = null;
			sql2 = "SELECT depreciation_val as dep, threshold_val as th FROM tb_tms_e_asset_condition WHERE id=(select max(id) from tb_tms_e_asset_condition)";
			stmt = conn.prepareStatement(sql2);
			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				dep = (Float.parseFloat(rs1.getString("dep"))) / 100;
				th = (Float.parseFloat(rs1.getString("th"))) / 100;
			}
			rs1.close();

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);

			sql3 = "update tb_tms_banum_req_child set update_status = ? where present_cost !='' and update_status!=? ";
			stmt = conn.prepareStatement(sql3);
			stmt.setString(1, String.valueOf(year));
			stmt.setString(2, String.valueOf(year));
			int ar = stmt.executeUpdate();

			if (ar != 0) {

				sql1 = "UPDATE tb_tms_banum_req_child t \r\n" + "    SET present_cost =( case \r\n"
						+ "			when cast(date_part('year',age(now(), year_of_entry)) as integer) <= 1 \r\n"
						+ "			then (case when present_cost = '' \r\n"
						+ "				then (cast(cast(purchas_cost as float)-(cast(purchas_cost as float)*?) as character varying))\r\n"
						+ "				when present_cost != '' and cast(present_cost as float)-(cast(present_cost as float)*?)>=(cast(purchas_cost as float)*?)\r\n"
						+ "			        then (cast(cast(present_cost as float)-(cast(present_cost as float)*?) as character varying))\r\n"
						+ "			else present_cost\r\n" + "			END )\r\n"
						+ "			when cast(date_part('year',age(now(), year_of_entry)) as integer) > 1 \r\n"
						+ "			then (case when present_cost = '' \r\n"
						+ "				then (cast(cast(purchas_cost as float)-(cast(purchas_cost as float)*(((date_part('year',age(now(), year_of_entry)))*10)/100)) as character varying))\r\n"
						+ "				when present_cost != '' and cast(present_cost as float)-(cast(present_cost as float)*(((date_part('year',age(now(), year_of_entry)))*10)/100)) >= (cast(purchas_cost as float)*?)\r\n"
						+ "			        then (cast(cast(present_cost as float)-(cast(present_cost as float)*(((date_part('year',age(now(), year_of_entry)))*10)/100)) as character varying))\r\n"
						+ "			else present_cost\r\n" + "			END )\r\n" + "			else present_cost\r\n"
						+ "			END )\r\n" + "from tb_tms_census_retrn p,tb_tms_banum_dirctry d\r\n"
						+ "where d.ba_no = t.ba_no and p.sus_no=? ";

				stmt = conn.prepareStatement(sql1);
				
				stmt.setDouble(1, dep);
				stmt.setDouble(2, dep);
				stmt.setDouble(3, th);
				stmt.setDouble(4, dep);
				stmt.setDouble(5, th);
				stmt.setString(6, sus_no);
				stmt.executeUpdate();

				String sql6 = "";
				sql6 = "update tb_tms_banum_req_child set update_status=? where update_status = '0' and present_cost != ''";
				stmt = conn.prepareStatement(sql6);
				stmt.setInt(1, year);
				stmt.executeUpdate();
			}

			sql = "select \r\n" + "	d.mct as mct,\r\n" + "	m.std_nomclature as nomen,\r\n" + "	p.ba_no as bano,\r\n"
					+ "	r.purchas_cost as pur,\r\n" + "	r.present_cost as pre,\r\n" + "	r.year_of_entry as year,\r\n"
					+ "	(date_part('year', age(now(), (select p.year_of_entry  from tb_tms_banum_req_child p where d.ba_no = p.ba_no and p.mct_number = d.mct)))) as diff  \r\n"
					+ "from \r\n" + "tb_tms_census_retrn p \r\n"
					+ "inner join tb_tms_banum_dirctry d on d.ba_no = p.ba_no \r\n"
					+ "inner join tb_tms_mct_master m on m.mct = cast(d.mct as bigint ) and m.type_of_vehicle = 'A'\r\n"
					+ "left join tb_tms_banum_req_child r on r.ba_no = d.ba_no and r.mct_number = d.mct\r\n"
					+ "left join tb_tms_mct_main_master n on n.mct_main_id = SUBSTRING(CAST(p.mct  AS character varying),1,4) and n.mct_nomen is not null\r\n"
					+ "where p.sus_no=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			System.err.println("2---"+stmt);
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("mct"));
				list.add(rs.getString("nomen"));
				list.add(rs.getString("bano"));
				list.add(rs.getString("pur"));
				list.add(rs.getString("pre"));
				list.add(rs.getString("year"));
				alist.add(list);
			}
			rs.close();

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

	public ArrayList<List<String>> getmcrReportList(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "	 select c.ba_no, " + "	b.mct, " + "	m.std_nomclature, " + "	r.country_of_origin, "
					+ "	c.vehcl_classfctn, " + "        c.veh_km_run_period, " + "        c.vehcl_kms_run, "
					+ "        c.track_kms, " + "        c.engine_type, " + "        c.engine_kms_run, "
					+ "        c.engine_hrs_run, " + "        c.aux_engine_run, " + "        c.aux_engn_clasfctn, "
					+ "        c.aux_engn_hrs_run, " + "        c.main_gun_type, " + "        c.main_gun_efc, "
					+ "        c.main_gun_qr, " + "        c.sec_gun_type, " + "        c.main_radio_set_nomcltr, "
					+ "        c.main_radio_set, " + "        c.main_radio_set_condn, " + "        c.unit_remarks, "
					+ "        c.miso_remarks, " + "       c.id " + "from  " + "tb_tms_census_retrn c "
					+ "inner join TB_TMS_BANUM_DIRCTRY b on b.ba_no = c.ba_no  "
					+ "left join tb_tms_banum_req_child r on r.ba_no = c.ba_no "
					+ "left join TB_TMS_MCT_MASTER m on m.mct = b.mct "
					+ "where c.sus_no = ? and c.status = '0' order by  c.ba_no";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			System.err.println("3---"+stmt);
			while (rs.next()) {

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("ba_no")); // 0
				list.add(rs.getString("mct"));// 1
				list.add(rs.getString("std_nomclature"));// 2
				list.add(rs.getString("country_of_origin"));// 3
				list.add(rs.getString("vehcl_classfctn")); // 4
				list.add(rs.getString("veh_km_run_period")); // 5

				list.add(rs.getString("vehcl_kms_run")); // 6
				list.add(rs.getString("track_kms"));// 7
				list.add(rs.getString("engine_type"));// 8
				list.add(rs.getString("engine_kms_run"));// 9
				list.add(rs.getString("engine_hrs_run")); // 10
				list.add(rs.getString("aux_engine_run")); // 11
				list.add(rs.getString("aux_engn_clasfctn"));// 12
				list.add(rs.getString("aux_engn_hrs_run"));// 13
				list.add(rs.getString("main_gun_type")); // 14
				list.add(rs.getString("main_gun_efc")); // 15

				list.add(rs.getString("main_gun_qr")); // 16
				list.add(rs.getString("sec_gun_type"));// 17
				list.add(rs.getString("main_radio_set_nomcltr"));// 18
				list.add(rs.getString("main_radio_set")); // 19
				list.add(rs.getString("main_radio_set_condn")); // 20
				list.add(rs.getString("unit_remarks"));// 21
				list.add(rs.getString("miso_remarks")); // 22
				list.add(rs.getString("id")); // 23

				String open = "<button onclick=open1('" + rs.getString("id") + "')>OPEN</button> ";
				list.add(open);
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

	public ArrayList<List<String>> getmcrReportListpartA(String sus_no) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "	select c.ba_no,\r\n" + "        d.mct,\r\n" + "        child.country_of_origin,\r\n"
					+ "        m.std_nomclature,\r\n" + "        c.vehcl_classfctn,\r\n"
					+ "        c.veh_km_run_period,\r\n" + "        c.vehcl_kms_run,\r\n" + "        c.track_kms,\r\n"
					+ "        c.engine_type,\r\n" + "        c.engine_kms_run,\r\n" + "        c.engine_hrs_run,\r\n"
					+ "        c.aux_engine_run,\r\n" + "        c.aux_engn_clasfctn,\r\n"
					+ "        c.aux_engn_hrs_run,\r\n" + "        c.main_gun_type,\r\n" + "        c.main_gun_efc,\r\n"
					+ "        c.main_gun_qr,\r\n" + "        c.sec_gun_type,\r\n"
					+ "        c.main_radio_set_nomcltr,\r\n" + "        c.main_radio_set,\r\n"
					+ "        c.main_radio_set_condn,\r\n" + "        c.unit_remarks,\r\n"
					+ "        c.miso_remarks,\r\n" + "       c.id\r\n" + "        \r\n"
					+ "        from tb_tms_census_retrn c \r\n"
					+ "        left join TB_TMS_BANUM_DIRCTRY d on c.ba_no = d.ba_no and veh_cat = 'A'\r\n"
					+ "        left join tb_tms_banum_req_child child on c.ba_no = child.ba_no and child.veh_cat='A'\r\n"
					+ "        inner join TB_TMS_MCT_MASTER m on d.mct = m.mct and m.type_of_vehicle = 'A' \r\n"
					+ "        where c.sus_no=? order by ba_no";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			System.err.println("4---"+stmt);
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("ba_no")); // 0
				list.add(rs.getString("mct"));// 1
				list.add(rs.getString("std_nomclature"));// 2
				list.add(rs.getString("country_of_origin"));// 3
				list.add(rs.getString("vehcl_classfctn")); // 4
				list.add(rs.getString("veh_km_run_period")); // 5
				list.add(rs.getString("vehcl_kms_run")); // 6
				list.add(rs.getString("track_kms"));// 7
				list.add(rs.getString("engine_type"));// 8
				list.add(rs.getString("engine_kms_run"));// 9
				list.add(rs.getString("engine_hrs_run")); // 10
				list.add(rs.getString("aux_engine_run")); // 11
				list.add(rs.getString("aux_engn_clasfctn"));// 12
				list.add(rs.getString("aux_engn_hrs_run"));// 13
				list.add(rs.getString("main_gun_type")); // 14
				list.add(rs.getString("main_gun_efc")); // 15
				list.add(rs.getString("main_gun_qr")); // 16
				list.add(rs.getString("sec_gun_type"));// 17
				list.add(rs.getString("main_radio_set_nomcltr"));// 18
				list.add(rs.getString("main_radio_set")); // 19
				list.add(rs.getString("main_radio_set_condn")); // 20
				list.add(rs.getString("unit_remarks"));// 21
				list.add(rs.getString("miso_remarks")); // 22
				list.add(rs.getString("id")); // 23
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

	 public ArrayList<List<String>> getMCRList(String sus_no,String roleAccess) {
         ArrayList<List<String>> alist = new ArrayList<List<String>>();
         Connection conn = null;
         try {
                 conn = dataSource.getConnection();
                 String sql = "";
                 sql = "select distinct c.ba_no,m.std_nomclature,c.vehcl_classfctn, \r\n"
                                 + "c.vehcl_kms_run,c.engine_type,c.aux_engn_clasfctn, \r\n"
                                 + "c.main_gun_type,c.sec_gun_type,c.main_radio_set,\r\n"
                                 + "c.main_radio_set_condn,c.unit_remarks,c.miso_remarks,c.id, \r\n"
                                 + " c.status from tb_tms_census_retrn c \r\n"
                                 + "left join tb_tms_census_retrn_main d on c.sus_no = d.sus_no\r\n"
                                 + "left join TB_TMS_BANUM_DIRCTRY ba on c.ba_no = ba.ba_no\r\n"
                                 + "inner join TB_TMS_MCT_MASTER m on ba.mct=m.mct and type_of_vehicle = 'A'\r\n"
                                 + "where c.sus_no= ? order by c.ba_no";

                 PreparedStatement stmt = conn.prepareStatement(sql);
                 stmt.setString(1, sus_no);
                 System.out.println("---*-*-*-*-*-*-*-"+stmt);
                 ResultSet rs = stmt.executeQuery();

                 int total = 0;
                 int updated = 0;
                 String open = "";
                 while (rs.next()) {
                         List<String> list = new ArrayList<String>();
                         list.add(rs.getString("ba_no")); // 0
                         list.add(rs.getString("std_nomclature"));// 1
                         list.add(rs.getString("vehcl_classfctn")); // 2
                         list.add(rs.getString("vehcl_kms_run")); // 4
                         list.add(rs.getString("engine_type"));// 5
                         list.add(rs.getString("aux_engn_clasfctn"));// 6
                         list.add(rs.getString("main_gun_type")); // 7
                         list.add(rs.getString("sec_gun_type"));// 8
                         list.add(rs.getString("main_radio_set")); // 9
                         list.add(rs.getString("main_radio_set_condn")); // 10
                         list.add(rs.getString("unit_remarks"));// 11
                         list.add(rs.getString("miso_remarks")); // 12
                         list.add(rs.getString("id")); // 13
                         list.add(rs.getString("status")); // 14
                         total++;
                         if (rs.getString("status") == "0" || rs.getString("status").equals("0")) {
                                 updated++;
                                 
                                 open = "<button class='btn btn-primary btn-sm' onclick=open1('" + rs.getString("id")
                                 + "')>Updated</button> ";
                         } else {
                                 open = "<button class='btn btn-primary btn-sm' onclick=open1('" + rs.getString("id")
                                                 + "')>Update</button> ";
                         }
                         //NotUpdated = total - updated;
                 
                         if(roleAccess.equals("MISO") || roleAccess.equals("Unit")) {
                         list.add(open); // 15
                         }else {
                                 list.add("NA");// 15
                         }
                         list.add(String.valueOf(total)); // 15
                         list.add(String.valueOf(updated)); // 16
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
	 
		 
	 public ArrayList<List<String>> getFMCRList(String sus_no, String roleAccess) {
			ArrayList<List<String>> alist = new ArrayList<List<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				sql = "select distinct c.short_form as comd,\r\n"
						+ "       corps.coprs_name as corps,\r\n"
						+ "       div.div_name as div,\r\n"
						+ "       bde.bde_name as bde,\r\n"
						+ "       u.unit_name,\r\n"
						+ "       u1.line_dte_name as line_dte,\r\n"
						+ "       part.sus_no,\r\n"
						+ "       m.mct_nomen,\r\n"
						+ "       part.ba_no,\r\n"
						+ "       round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1)) * 0), 0) as kms_run,\r\n"
						+ "	   oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3,oh_cal.km4,oh_cal.vintage4,oh_cal.km5,oh_cal.vintage5,oh_cal.km6,oh_cal.vintage6,\r\n"
						+ "	   \r\n"
						+ "       part.vehcl_classfctn as classification,\r\n"
						+ "       '' as approve_date,\r\n"
						+ "       case when oh_cal.km1 is null or (oh_cal.km1 = 0 and oh_cal.vintage1 = 0) then null\r\n"
						+ "            when oh_cal.km1 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh1_due_date,\r\n"
						+ "       case when oh_cal.km2 is null or (oh_cal.km2 = 0 and oh_cal.vintage2 = 0) then null\r\n"
						+ "            when oh_cal.km2 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh2_due_date,\r\n"
						+ "       case when oh_cal.km3 is null or (oh_cal.km3 = 0 and oh_cal.vintage3 = 0) then null\r\n"
						+ "            when oh_cal.km3 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh3_due_date,\r\n"
						+ "       case when oh_cal.km4 is null or (oh_cal.km4 = 0 and oh_cal.vintage4 = 0) then null\r\n"
						+ "            when oh_cal.km4 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage4 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage4 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km4 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr1_due_date,\r\n"
						+ "       case when oh_cal.km5 is null or (oh_cal.km5 = 0 and oh_cal.vintage5 = 0) then null\r\n"
						+ "            when oh_cal.km5 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage5 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage5 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km5 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr2_due_date,\r\n"
						+ "       case when oh_cal.km6 is null or (oh_cal.km6 = 0 and oh_cal.vintage6 = 0) then null\r\n"
						+ "            when oh_cal.km6 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage6 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage6 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km6 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr3_due_date,\r\n"
						+ "       cin.oh1date,\r\n"
						+ "       cin.oh2date,\r\n"
						+ "       cin.oh3date,\r\n"
						+ "       cin.mr1date,\r\n"
						+ "       cin.mr2date,\r\n"
						+ "       cin.mr3date,\r\n"
						+ "	   \r\n"
						+ "	   d.engine_no,engine_type,low_tempered_bal,COALESCE(engine_hrs_current,0) as engine_hrs_current,\r\n"
						+ "COALESCE(engine_hr_total,0) as engine_hr_total,\r\n"
						+ "COALESCE(engine_failure_at,0) as engine_failure_at,\r\n"
						+ "TO_CHAR(engine_submission_dt,'DD-MM-YYYY') as engine_submission_dt,\r\n"
						+ "TO_CHAR(engine_repair_comp_dt,'DD-MM-YYYY') as engine_repair_comp_dt,\r\n"
						+ "air_compressor_no,air_compressor_type,COALESCE(air_compressor_total_hrs,0) as air_compressor_total_hrs,\r\n"
						+ "COALESCE(air_compressor_failure_at,0) as air_compressor_failure_at,\r\n"
						+ "TO_CHAR(air_compressor_submission_dt,'DD-MM-YYYY') as air_compressor_submission_dt,\r\n"
						+ "TO_CHAR(air_compressor_repair_comp_dt,'DD-MM-YYYY') as air_compressor_repair_comp_dt,\r\n"
						+ "sgb_no,sgb_type,\r\n"
						+ "COALESCE(sgb_total_km,0) as sgb_total_km,\r\n"
						+ "COALESCE(sgb_failure_at,0) as sgb_failure_at,\r\n"
						+ "TO_CHAR(sgb_submission_dt,'DD-MM-YYYY') as sgb_submission_dt,\r\n"
						+ "TO_CHAR(sgb_repair_comp_dt,'DD-MM-YYYY') as sgb_repair_comp_dt,igb_no,igb_type,\r\n"
						+ "COALESCE(igb_total_km,0) as igb_total_km,\r\n"
						+ "COALESCE(igb_failure_at,0) as igb_failure_at,\r\n"
						+ "TO_CHAR(igb_submission_dt,'DD-MM-YYYY') as igb_submission_dt,\r\n"
						+ "TO_CHAR(igb_repair_comp_dt,'DD-MM-YYYY') as igb_repair_comp_dt,\r\n"
						+ "COALESCE(track_kms,0) as track_kms,\r\n"
						+ "track_assy_ser,\r\n"
						+ "COALESCE(track_assy_failure_at,0) as track_assy_failure_at,\r\n"
						+ "COALESCE(sprocket_assy_total_km,0) as sprocket_assy_total_km,\r\n"
						+ "COALESCE(sprocket_assy_failure_at,0) as sprocket_assy_failure_at,\r\n"
						+ "sprocket_assy_ser,\r\n"
						+ "COALESCE(pump_drive_motor_hr,0) as pump_drive_motor_hr,\r\n"
						+ "pump_drive_motor_ser,\r\n"
						+ "COALESCE(pump_drive_motor_failure_at,0) as pump_drive_motor_failure_at,\r\n"
						+ "COALESCE(starter_genr_total_hr,0) as starter_genr_total_hr,\r\n"
						+ "starter_genr_ser,\r\n"
						+ "COALESCE(starter_genr_failure_at,0) as starter_genr_failure_at,\r\n"
						+ "turbo_charger_type,\r\n"
						+ "COALESCE(turbo_charger_defect_at,0) as turbo_charger_defect_at,\r\n"
						+ "TO_CHAR(turbo_charger_submission_dt,'DD-MM-YYYY') as turbo_charger_submission_dt,\r\n"
						+ "TO_CHAR(turbo_charger_repair_comp_dt,'DD-MM-YYYY') as turbo_charger_repair_comp_dt,\r\n"
						+ "TO_CHAR(engine_demand_dt,'DD-MM-YYYY') as engine_demand_dt,\r\n"
						+ "TO_CHAR(engine_rel_dt,'DD-MM-YYYY') as engine_rel_dt,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_auth,0) as fire_fight_sys_cyl_auth,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_held,0) as fire_fight_sys_cyl_held,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_empty,0) as fire_fight_sys_cyl_empty,\r\n"
						+ "TO_CHAR(fire_fight_cyl_demand_dt,'DD-MM-YYYY') as fire_fight_cyl_demand_dt,\r\n"
						+ "cbrn_sys_over_pressure,\r\n"
						+ "COALESCE(cbrn_filter_qty,0) as cbrn_filter_qty,\r\n"
						+ "cbrn_filter_ser,pkuza_ser,bas_a_ser,cbrn_spl_blower_ser,mine_plough_ser,emp_ser,mrs_ser,mrs_type,\r\n"
						+ "COALESCE(mrs_qty,0) as mrs_qty,\r\n"
						+ "dch_installed,\r\n"
						+ "dch_type,\r\n"
						+ "TO_CHAR(dch_installed_dt,'DD-MM-YYYY') as dch_installed_dt,dch_vintage,dch_ser,\r\n"
						+ "TO_CHAR(cti_install_dt,'DD-MM-YYYY') as cti_install_dt,cti_vintage,cti_ser,main_gun_article_no,type_gun_article,main_gun_article_qtr,\r\n"
						+ "main_gun_article_ser,\r\n"
						+ "COALESCE(main_gun_article_efc,0) as main_gun_article_efc,\r\n"
						+ "COALESCE(no_of_msls_fired,0) as no_of_msls_fired,\r\n"
						+ "COALESCE(no_of_msls_failed,0) as no_of_msls_failed,\r\n"
						+ "sec_gun_type,sec_gun_regn_no,sec_gun_ser,aa_gun_type,aa_gun_regn_no,aa_gun_ser,\r\n"
						+ "msl_launcher_regn_no,msl_launcher_ser,\r\n"
						+ "msl_launcher_oh_due,\r\n"
						+ "msl_lr_oh_done_dt,\r\n"
						+ "TO_CHAR(tisas_old_instln_dt,'DD-MM-YYYY') as tisas_old_instln_dt,\r\n"
						+ "TO_CHAR(tisas_old_vintage,'DD-MM-YYYY') as tisas_old_vintage,tisas_old_ser,\r\n"
						+ "TO_CHAR(tisas_new_instln_dt,'DD-MM-YYYY') as tisas_new_instln_dt,\r\n"
						+ "TO_CHAR(tisas_new_vintage,'DD-MM-YYYY') as tisas_new_vintage,\r\n"
						+ "tisas_new_ser,tifcs_type,tifcs_regn_no,\r\n"
						+ "TO_CHAR(tifcs_instln_dt,'DD-MM-YYYY') as tifcs_instln_dt,\r\n"
						+ "TO_CHAR(tifcs_vintage,'DD-MM-YYYY') as tifcs_vintage,\r\n"
						+ "COALESCE(relay_kr_hr,0) as relay_kr_hr,relay_kr_ser,\r\n"
						+ "COALESCE(relay_kr_failure_at,0) as relay_kr_failure_at,\r\n"
						+ "COALESCE(lrf_hr,0) as lrf_hr,\r\n"
						+ "lrf_ser,\r\n"
						+ "COALESCE(lrf_failure_at,0) as lrf_failure_at,\r\n"
						+ "COALESCE(gyro_direc_indi_hr,0) as gyro_direc_indi_hr,\r\n"
						+ "gyro_direc_indi_ser,\r\n"
						+ "COALESCE(gyro_direc_failure_at,0) as gyro_direc_failure_at,\r\n"
						+ "COALESCE(dist_box_hr,0) as dist_box_hr,\r\n"
						+ "dist_box_ser,\r\n"
						+ "COALESCE(dist_box_failure_at,0) as dist_box_failure_at\r\n"
						+ ",tim_fitted,\r\n"
						+ "TO_CHAR(tim_intls_dt,'DD-MM-YYYY') as tim_intls_dt,\r\n"
						+ "TO_CHAR(tim_vintage,'DD-MM-YYYY') as tim_vintage,tis_fitted,\r\n"
						+ "TO_CHAR(tis_instaln_dt,'DD-MM-YYYY') as tis_instaln_dt,\r\n"
						+ "TO_CHAR(tis_vintage,'DD-MM-YYYY') as tis_vintage,u_tim_fitted,\r\n"
						+ "TO_CHAR(u_tim_intsln_dt,'DD-MM-YYYY') as u_tim_intsln_dt,\r\n"
						+ "TO_CHAR(u_tim_vintage,'DD-MM-YYYY') as u_tim_vintage,m_tim_fitted,\r\n"
						+ "TO_CHAR(m_tim_instln_dt,'DD-MM-YYYY') as m_tim_instln_dt,\r\n"
						+ "TO_CHAR(m_tim_vintage,'DD-MM-YYYY') as m_tim_vintage,m_tisk_fitted,\r\n"
						+ "TO_CHAR(m_tisk_instals_dt,'DD-MM-YYYY') as m_tisk_instals_dt,\r\n"
						+ "TO_CHAR(m_tisk_vintage,'DD-MM-YYYY') as m_tisk_vintage,\r\n"
						+ "dns_fitted,\r\n"
						+ "TO_CHAR(dns_instln_dt,'DD-MM-YYYY') as dns_instln_dt,dns_ser,cti_fitted,\r\n"
						+ "TO_CHAR(cti_instln_dt,'DD-MM-YYYY') as cti_instln_dt,alns_fitted,alns_ser,gps_fitted,gps_ser,ecu_fitted,ecu_ser,spta_fitted,spta_ser,powerpack_ser,\r\n"
						+ "TO_CHAR(powerpack_demand_dt,'DD-MM-YYYY') as powerpack_demand_dt,\r\n"
						+ "stab_ser,\r\n"
						+ "TO_CHAR(stab_demand_dt,'DD-MM-YYYY') as stab_demand_dt,ifdss_ser,\r\n"
						+ "TO_CHAR(ifdss_demand_dt,'DD-MM-YYYY') as ifdss_demand_dt,gms_ser,\r\n"
						+ "TO_CHAR(gms_demand_dt,'DD-MM-YYYY') as gms_demand_dt,cps_mk_ser,\r\n"
						+ "TO_CHAR(cps_mk_demand_dt,'DD-MM-YYYY') as cps_mk_demand_dt,dvr_pnvd_ser,\r\n"
						+ "TO_CHAR(dvr_pnvd_demand_dt,'DD-MM-YYYY') as dvr_pnvd_demand_dt,\r\n"
						+ "fcc_ser,\r\n"
						+ "TO_CHAR(fcc_demand_dt,'DD-MM-YYYY') as fcc_demand_dt,\r\n"
						+ "COALESCE(bogie_wheels_held_onrd,0) as bogie_wheels_held_onrd,\r\n"
						+ "COALESCE(bogie_wheels_held_offrd,0) as bogie_wheels_held_offrd,\r\n"
						+ "TO_CHAR(bogie_wheels_demand_dt,'DD-MM-YYYY') as bogie_wheels_demand_dt,top_roll_ser,\r\n"
						+ "TO_CHAR(top_roll_demand_dt,'DD-MM-YYYY') as top_roll_demand_dt,\r\n"
						+ "COALESCE(track_pads_held_onrd,0) as track_pads_held_onrd,COALESCE(track_pads_held_offrd,0) as track_pads_held_offrd,\r\n"
						+ "TO_CHAR(track_pads_demand_dt,'DD-MM-YYYY') as track_pads_demand_dt,cdr_cont_stn_ser,\r\n"
						+ "TO_CHAR(cdr_cont_stn_demmand_dt,'DD-MM-YYYY') as cdr_cont_stn_demmand_dt,gcdu_ser,\r\n"
						+ "TO_CHAR(gcdu_demand_dt,'DD-MM-YYYY') as gcdu_demand_dt,low_tempered_bal\r\n"
						+ "  from tb_tms_census_retrn part\r\n"
						+ "  join tb_tms_banum_dirctry d\r\n"
						+ "    on d.ba_no::text = part.ba_no::text\r\n"
						+ "  join tb_tms_mct_main_master m\r\n"
						+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
						+ "   and m.type_of_veh::text = 'A'::text\r\n"
						+ "  join tb_miso_orbat_unt_dtl u\r\n"
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
						+ "               max(case when oh_mr = '1' then km end) as km4,\r\n"
						+ "               max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as vintage4,\r\n"
						+ "               max(case when oh_mr = '3' then km end)as km5,\r\n"
						+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as vintage5,\r\n"
						+ "               max(case when oh_mr = '5' then km end) as km6,\r\n"
						+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as vintage6\r\n"
						+ "          from tb_tms_mct_main_oh_mr\r\n"
						+ "         group by mct_main_id\r\n"
						+ "       ) as oh_cal\r\n"
						+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
						+ " inner join tb_miso_orbat_line_dte u1\r\n"
						+ "    on m.sus_no=u1.line_dte_sus\r\n"
						+ "  left join orbat_view_cmd c\r\n"
						+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
						+ "  left join orbat_view_corps corps\r\n"
						+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
						+ "  left join orbat_view_div div\r\n"
						+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
						+ "  left join orbat_view_bde bde\r\n"
						+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
						+ "  left join (\r\n"
						+ "        select ba_no,\r\n"
						+ "               status,\r\n"
						+ "               max(case when type_of_intervention = '2' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh1date,\r\n"
						+ "               max(case when type_of_intervention = '4' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh2date,\r\n"
						+ "               max(case when type_of_intervention = '6' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh3date,\r\n"
						+ "               max(case when type_of_intervention = '1' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr1date,\r\n"
						+ "               max(case when type_of_intervention = '3' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr2date,\r\n"
						+ "               max(case when type_of_intervention = '5' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr3date\r\n"
						+ "          from tb_tms_cin\r\n"
						+ "         where type_of_veh = 'A'\r\n"
						+ "           and status != '0'\r\n"
						+ "         group by ba_no,\r\n"
						+ "                  status\r\n"
						+ "       ) as cin\r\n"
						+ "    on cin.ba_no = part.ba_no\r\n"
						+ " inner join (\r\n"
						+ "        select part2.ba_no,\r\n"
						+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                     end as extracted_year\r\n"
						+ "          from tb_tms_census_retrn part2\r\n"
						+ "       ) as ba_registraion\r\n"
						+ "    on ba_registraion.ba_no = part.ba_no where part.sus_no = ? "
						+ " order by part.ba_no,\r\n"
						+ "          9,\r\n"
						+ "          10\r\n"
						+ "";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, sus_no);
				
				ResultSet rs = stmt.executeQuery();
				
				System.out.println("A Veh --> " + stmt);
				
				ResultSetMetaData r = rs.getMetaData();

				while (rs.next()) {
					List<String> list = new ArrayList<String>();
/*
					list.add(rs.getString("comd"));
					list.add(rs.getString("corps"));
					list.add(rs.getString("div"));
					list.add(rs.getString("bde"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("line_dte"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("mct_nomen"));*/
					list.add(rs.getString("ba_no"));
					list.add(rs.getString("kms_run"));
					list.add(rs.getString("classification"));
					
					list.add(rs.getString("km1"));
					list.add(rs.getString("vintage1"));
					list.add(rs.getString("km2"));
					list.add(rs.getString("vintage2"));
					list.add(rs.getString("km3"));
					list.add(rs.getString("vintage3"));
					list.add(rs.getString("km4"));
					list.add(rs.getString("vintage4"));
					list.add(rs.getString("km5"));
					list.add(rs.getString("vintage5"));
					list.add(rs.getString("km6"));
					list.add(rs.getString("vintage6"));
					list.add(rs.getString("oh1_due_date"));
					list.add(rs.getString("oh2_due_date"));
					list.add(rs.getString("oh3_due_date"));
					list.add(rs.getString("mr1_due_date"));
					list.add(rs.getString("mr2_due_date"));
					list.add(rs.getString("mr3_due_date"));
					
					list.add(rs.getString("oh1date"));
					list.add(rs.getString("oh2date"));
					list.add(rs.getString("oh3date"));
					list.add(rs.getString("mr1date"));
					list.add(rs.getString("mr2date"));
					list.add(rs.getString("mr3date"));
					
					list.add(rs.getString("engine_no"));
					list.add(rs.getString("engine_type"));
					list.add(rs.getString("engine_hrs_current"));
					list.add(rs.getString("engine_hr_total"));
					list.add(rs.getString("engine_failure_at"));
					list.add(rs.getString("engine_submission_dt"));
					list.add(rs.getString("engine_repair_comp_dt"));
					
					list.add(rs.getString("air_compressor_no"));
					list.add(rs.getString("air_compressor_type"));
					list.add(rs.getString("air_compressor_total_hrs"));
					list.add(rs.getString("air_compressor_failure_at"));
					list.add(rs.getString("air_compressor_submission_dt"));
					list.add(rs.getString("air_compressor_repair_comp_dt"));
					list.add(rs.getString("sgb_no"));
					list.add(rs.getString("sgb_type"));
					list.add(rs.getString("sgb_total_km"));
					list.add(rs.getString("sgb_failure_at"));
					list.add(rs.getString("sgb_submission_dt"));
					list.add(rs.getString("sgb_repair_comp_dt"));
					list.add(rs.getString("igb_no"));
					
					list.add(rs.getString("igb_type"));
					list.add(rs.getString("igb_total_km"));
					list.add(rs.getString("igb_failure_at"));
					list.add(rs.getString("igb_submission_dt"));
					list.add(rs.getString("igb_repair_comp_dt"));
					list.add(rs.getString("track_kms"));
					list.add(rs.getString("track_assy_ser"));
					list.add(rs.getString("track_assy_failure_at"));
					list.add(rs.getString("sprocket_assy_total_km"));
					list.add(rs.getString("sprocket_assy_failure_at"));
					
					list.add(rs.getString("sprocket_assy_ser"));
					list.add(rs.getString("pump_drive_motor_hr"));
					list.add(rs.getString("pump_drive_motor_ser"));
					list.add(rs.getString("pump_drive_motor_failure_at"));
					list.add(rs.getString("starter_genr_total_hr"));
					list.add(rs.getString("starter_genr_ser"));
					list.add(rs.getString("starter_genr_failure_at"));
					list.add(rs.getString("turbo_charger_type"));
					list.add(rs.getString("turbo_charger_defect_at"));
					list.add(rs.getString("turbo_charger_submission_dt"));
					
					list.add(rs.getString("turbo_charger_repair_comp_dt"));
					list.add(rs.getString("engine_demand_dt"));
					list.add(rs.getString("engine_rel_dt"));
					list.add(rs.getString("fire_fight_sys_cyl_auth"));
					list.add(rs.getString("fire_fight_sys_cyl_held"));
					list.add(rs.getString("fire_fight_sys_cyl_empty"));
					list.add(rs.getString("fire_fight_cyl_demand_dt"));
					list.add(rs.getString("cbrn_sys_over_pressure"));
					list.add(rs.getString("cbrn_filter_qty"));
					list.add(rs.getString("cbrn_filter_ser"));
					list.add(rs.getString("pkuza_ser"));
					
					list.add(rs.getString("bas_a_ser"));
					list.add(rs.getString("cbrn_spl_blower_ser"));
					list.add(rs.getString("mine_plough_ser"));
					list.add(rs.getString("emp_ser"));
					list.add(rs.getString("mrs_ser"));
					list.add(rs.getString("mrs_type"));
					list.add(rs.getString("mrs_qty"));
					list.add(rs.getString("dch_installed"));
					list.add(rs.getString("dch_type"));
					list.add(rs.getString("dch_installed_dt"));
					list.add(rs.getString("dch_vintage"));
					
					list.add(rs.getString("dch_ser"));
					list.add(rs.getString("cti_install_dt"));
					list.add(rs.getString("cti_vintage"));
					list.add(rs.getString("cti_ser"));
					list.add(rs.getString("main_gun_article_no"));
					list.add(rs.getString("type_gun_article"));
					list.add(rs.getString("main_gun_article_qtr"));
					list.add(rs.getString("main_gun_article_ser"));
					list.add(rs.getString("main_gun_article_efc"));
					list.add(rs.getString("no_of_msls_fired"));
					list.add(rs.getString("no_of_msls_failed"));
					
					list.add(rs.getString("sec_gun_type"));
					list.add(rs.getString("sec_gun_regn_no"));
					list.add(rs.getString("sec_gun_ser"));
					list.add(rs.getString("aa_gun_type"));
					list.add(rs.getString("aa_gun_regn_no"));
					list.add(rs.getString("aa_gun_ser"));
					list.add(rs.getString("msl_launcher_regn_no"));
					list.add(rs.getString("msl_launcher_ser"));
					list.add(rs.getString("msl_launcher_oh_due"));
					list.add(rs.getString("msl_lr_oh_done_dt"));
					list.add(rs.getString("tisas_old_instln_dt"));
					
					list.add(rs.getString("tisas_old_vintage"));
					list.add(rs.getString("tisas_old_ser"));
					list.add(rs.getString("tisas_new_instln_dt"));
					list.add(rs.getString("tisas_new_vintage"));
					list.add(rs.getString("tisas_new_ser"));
					list.add(rs.getString("tifcs_type"));
					list.add(rs.getString("tifcs_regn_no"));
					list.add(rs.getString("tifcs_instln_dt"));
					list.add(rs.getString("tifcs_vintage"));
					list.add(rs.getString("relay_kr_hr"));
					list.add(rs.getString("relay_kr_ser"));
					
					list.add(rs.getString("relay_kr_failure_at"));
					list.add(rs.getString("lrf_hr"));
					list.add(rs.getString("lrf_ser"));
					list.add(rs.getString("lrf_failure_at"));
					list.add(rs.getString("gyro_direc_indi_hr"));
					list.add(rs.getString("gyro_direc_indi_ser"));
					list.add(rs.getString("gyro_direc_failure_at"));
					list.add(rs.getString("dist_box_hr"));
					list.add(rs.getString("dist_box_ser"));
					list.add(rs.getString("dist_box_failure_at"));
					list.add(rs.getString("tim_fitted"));
					
					list.add(rs.getString("tim_intls_dt"));
					list.add(rs.getString("tim_vintage"));
					list.add(rs.getString("tis_fitted"));
					list.add(rs.getString("tis_instaln_dt"));
					list.add(rs.getString("tis_vintage"));
					list.add(rs.getString("u_tim_fitted"));
					list.add(rs.getString("u_tim_intsln_dt"));
					list.add(rs.getString("u_tim_vintage"));
					list.add(rs.getString("m_tim_fitted"));
					list.add(rs.getString("m_tim_instln_dt"));
					list.add(rs.getString("m_tim_vintage"));
					
					list.add(rs.getString("m_tisk_fitted"));
					list.add(rs.getString("m_tisk_instals_dt"));
					list.add(rs.getString("m_tisk_vintage"));
					list.add(rs.getString("dns_fitted"));
					list.add(rs.getString("dns_instln_dt"));
					list.add(rs.getString("dns_ser"));
					list.add(rs.getString("cti_fitted"));
					list.add(rs.getString("cti_instln_dt"));
					list.add(rs.getString("alns_fitted"));
					list.add(rs.getString("alns_ser"));
					list.add(rs.getString("gps_fitted"));
					
					list.add(rs.getString("gps_ser"));
					list.add(rs.getString("ecu_fitted"));
					list.add(rs.getString("ecu_ser"));
					list.add(rs.getString("spta_fitted"));
					list.add(rs.getString("spta_ser"));
					list.add(rs.getString("powerpack_ser"));
					list.add(rs.getString("powerpack_demand_dt"));
					list.add(rs.getString("stab_ser"));
					list.add(rs.getString("stab_demand_dt"));
					list.add(rs.getString("ifdss_ser"));
					list.add(rs.getString("ifdss_demand_dt"));
					
					list.add(rs.getString("gms_ser"));
			/*		list.add(rs.getString("gms_demand_dt"));*/
					list.add(rs.getString("cps_mk_ser"));
					list.add(rs.getString("cps_mk_demand_dt"));
					list.add(rs.getString("dvr_pnvd_ser"));
					list.add(rs.getString("dvr_pnvd_demand_dt"));
					list.add(rs.getString("fcc_ser"));
					list.add(rs.getString("fcc_demand_dt"));
					list.add(rs.getString("bogie_wheels_held_onrd"));
					list.add(rs.getString("bogie_wheels_held_offrd"));
					list.add(rs.getString("bogie_wheels_demand_dt"));
					list.add(rs.getString("top_roll_ser"));
					list.add(rs.getString("top_roll_demand_dt"));
					list.add(rs.getString("track_pads_held_onrd"));
					list.add(rs.getString("track_pads_held_offrd"));
					list.add(rs.getString("track_pads_demand_dt"));
					list.add(rs.getString("cdr_cont_stn_ser"));
					list.add(rs.getString("cdr_cont_stn_demmand_dt"));
					list.add(rs.getString("gcdu_ser"));
					list.add(rs.getString("gcdu_demand_dt"));
					list.add(rs.getString("low_tempered_bal"));
					

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
	 
//Added by Mitesh
	 public ArrayList<List<String>> getFullFMCRList(String sus_no, String roleAccess) {
			ArrayList<List<String>> alist = new ArrayList<List<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				sql = "select distinct "
						+ "part.id,part.sus_no,part.date_of_cens_retrn,d.mct,part.dt_of_submsn,part.vehcl_classfctn,part.vehcl_kms_run,part.engine_kms_run,part.engine_hrs_run,"
						+ "part.aux_engn_clasfctn,\r\n"
						+ "part.aux_engn_hrs_run,part.main_gun_type,part.main_gun_efc,part.main_gun_qr,part.main_radio_set_nomcltr,part.main_radio_set,part.main_radio_set_condn,part.unit_remarks,"
						+ "part.status,part.aprv_rejec_remarks,\r\n"
						+ "part.approved_by,part.approve_date,part.creation_by,part.creation_date,part.modify_by,part.modify_date,part.deleted_by,part.deleted_date,part.version_no,part.softdelete,"
						+ "c.short_form as comd,\r\n"
						+ "       corps.coprs_name as corps,\r\n"
						+ "       div.div_name as div,\r\n"
						+ "       bde.bde_name as bde,\r\n"
						+ "       u.unit_name,\r\n"
						+ "       u1.line_dte_name as line_dte,\r\n"
						+ "       part.sus_no,\r\n"
						+ "       m.mct_nomen,\r\n"
						+ "       part.ba_no,\r\n"
						+ "       round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1)) * 0), 0) as kms_run,\r\n"
						+ "	   oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3,oh_cal.km4,oh_cal.vintage4,oh_cal.km5,oh_cal.vintage5,oh_cal.km6,oh_cal.vintage6,\r\n"
						+ "	   \r\n"
						+ "       part.vehcl_classfctn as classification,\r\n"
						+ "       '' as approve_date,\r\n"
						+ "       case when oh_cal.km1 is null or (oh_cal.km1 = 0 and oh_cal.vintage1 = 0) then null\r\n"
						+ "            when oh_cal.km1 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh1_due_date,\r\n"
						+ "       case when oh_cal.km2 is null or (oh_cal.km2 = 0 and oh_cal.vintage2 = 0) then null\r\n"
						+ "            when oh_cal.km2 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh2_due_date,\r\n"
						+ "       case when oh_cal.km3 is null or (oh_cal.km3 = 0 and oh_cal.vintage3 = 0) then null\r\n"
						+ "            when oh_cal.km3 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh3_due_date,\r\n"
						+ "       case when oh_cal.km4 is null or (oh_cal.km4 = 0 and oh_cal.vintage4 = 0) then null\r\n"
						+ "            when oh_cal.km4 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage4 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage4 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km4 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr1_due_date,\r\n"
						+ "       case when oh_cal.km5 is null or (oh_cal.km5 = 0 and oh_cal.vintage5 = 0) then null\r\n"
						+ "            when oh_cal.km5 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage5 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage5 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km5 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr2_due_date,\r\n"
						+ "       case when oh_cal.km6 is null or (oh_cal.km6 = 0 and oh_cal.vintage6 = 0) then null\r\n"
						+ "            when oh_cal.km6 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage6 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage6 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km6 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr3_due_date,\r\n"
						+ "       cin.oh1date,\r\n"
						+ "       cin.oh2date,\r\n"
						+ "       cin.oh3date,\r\n"
						+ "       cin.mr1date,\r\n"
						+ "       cin.mr2date,\r\n"
						+ "       cin.mr3date,\r\n"
						+ "	   \r\n"
						+ "	   d.engine_no,engine_type,low_tempered_bal,COALESCE(engine_hrs_current,0) as engine_hrs_current,\r\n"
						+ "COALESCE(engine_hr_total,0) as engine_hr_total,\r\n"
						+ "COALESCE(engine_failure_at,0) as engine_failure_at,\r\n"
						+ "TO_CHAR(engine_submission_dt,'DD-MM-YYYY') as engine_submission_dt,\r\n"
						+ "TO_CHAR(engine_repair_comp_dt,'DD-MM-YYYY') as engine_repair_comp_dt,\r\n"
						+ "air_compressor_no,air_compressor_type,COALESCE(air_compressor_total_hrs,0) as air_compressor_total_hrs,\r\n"
						+ "COALESCE(air_compressor_failure_at,0) as air_compressor_failure_at,\r\n"
						+ "TO_CHAR(air_compressor_submission_dt,'DD-MM-YYYY') as air_compressor_submission_dt,\r\n"
						+ "TO_CHAR(air_compressor_repair_comp_dt,'DD-MM-YYYY') as air_compressor_repair_comp_dt,\r\n"
						+ "sgb_no,sgb_type,\r\n"
						+ "COALESCE(sgb_total_km,0) as sgb_total_km,\r\n"
						+ "COALESCE(sgb_failure_at,0) as sgb_failure_at,\r\n"
						+ "TO_CHAR(sgb_submission_dt,'DD-MM-YYYY') as sgb_submission_dt,\r\n"
						+ "TO_CHAR(sgb_repair_comp_dt,'DD-MM-YYYY') as sgb_repair_comp_dt,igb_no,igb_type,\r\n"
						+ "COALESCE(igb_total_km,0) as igb_total_km,\r\n"
						+ "COALESCE(igb_failure_at,0) as igb_failure_at,\r\n"
						+ "TO_CHAR(igb_submission_dt,'DD-MM-YYYY') as igb_submission_dt,\r\n"
						+ "TO_CHAR(igb_repair_comp_dt,'DD-MM-YYYY') as igb_repair_comp_dt,\r\n"
						+ "COALESCE(track_kms,0) as track_kms,\r\n"
						+ "track_assy_ser,\r\n"
						+ "COALESCE(track_assy_failure_at,0) as track_assy_failure_at,\r\n"
						+ "COALESCE(sprocket_assy_total_km,0) as sprocket_assy_total_km,\r\n"
						+ "COALESCE(sprocket_assy_failure_at,0) as sprocket_assy_failure_at,\r\n"
						+ "sprocket_assy_ser,\r\n"
						+ "COALESCE(pump_drive_motor_hr,0) as pump_drive_motor_hr,\r\n"
						+ "pump_drive_motor_ser,\r\n"
						+ "COALESCE(pump_drive_motor_failure_at,0) as pump_drive_motor_failure_at,\r\n"
						+ "COALESCE(starter_genr_total_hr,0) as starter_genr_total_hr,\r\n"
						+ "starter_genr_ser,\r\n"
						+ "COALESCE(starter_genr_failure_at,0) as starter_genr_failure_at,\r\n"
						+ "turbo_charger_type,\r\n"
						+ "COALESCE(turbo_charger_defect_at,0) as turbo_charger_defect_at,\r\n"
						+ "TO_CHAR(turbo_charger_submission_dt,'DD-MM-YYYY') as turbo_charger_submission_dt,\r\n"
						+ "TO_CHAR(turbo_charger_repair_comp_dt,'DD-MM-YYYY') as turbo_charger_repair_comp_dt,\r\n"
						+ "TO_CHAR(engine_demand_dt,'DD-MM-YYYY') as engine_demand_dt,\r\n"
						+ "TO_CHAR(engine_rel_dt,'DD-MM-YYYY') as engine_rel_dt,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_auth,0) as fire_fight_sys_cyl_auth,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_held,0) as fire_fight_sys_cyl_held,\r\n"
						+ "COALESCE(fire_fight_sys_cyl_empty,0) as fire_fight_sys_cyl_empty,\r\n"
						+ "TO_CHAR(fire_fight_cyl_demand_dt,'DD-MM-YYYY') as fire_fight_cyl_demand_dt,\r\n"
						+ "cbrn_sys_over_pressure,\r\n"
						+ "COALESCE(cbrn_filter_qty,0) as cbrn_filter_qty,\r\n"
						+ "cbrn_filter_ser,pkuza_ser,bas_a_ser,cbrn_spl_blower_ser,mine_plough_ser,emp_ser,mrs_ser,mrs_type,\r\n"
						+ "COALESCE(mrs_qty,0) as mrs_qty,\r\n"
						+ "dch_installed,\r\n"
						+ "dch_type,\r\n"
						+ "TO_CHAR(dch_installed_dt,'DD-MM-YYYY') as dch_installed_dt,dch_vintage,dch_ser,\r\n"
						+ "TO_CHAR(cti_install_dt,'DD-MM-YYYY') as cti_install_dt,cti_vintage,cti_ser,main_gun_article_no,type_gun_article,main_gun_article_qtr,\r\n"
						+ "main_gun_article_ser,\r\n"
						+ "COALESCE(main_gun_article_efc,0) as main_gun_article_efc,\r\n"
						+ "COALESCE(no_of_msls_fired,0) as no_of_msls_fired,\r\n"
						+ "COALESCE(no_of_msls_failed,0) as no_of_msls_failed,\r\n"
						+ "sec_gun_type,sec_gun_regn_no,sec_gun_ser,aa_gun_type,aa_gun_regn_no,aa_gun_ser,\r\n"
						+ "msl_launcher_regn_no,msl_launcher_ser,\r\n"
						+ "msl_launcher_oh_due,\r\n"
						+ "msl_lr_oh_done_dt,\r\n"
						+ "TO_CHAR(tisas_old_instln_dt,'DD-MM-YYYY') as tisas_old_instln_dt,\r\n"
						+ "TO_CHAR(tisas_old_vintage,'DD-MM-YYYY') as tisas_old_vintage,tisas_old_ser,\r\n"
						+ "TO_CHAR(tisas_new_instln_dt,'DD-MM-YYYY') as tisas_new_instln_dt,\r\n"
						+ "TO_CHAR(tisas_new_vintage,'DD-MM-YYYY') as tisas_new_vintage,\r\n"
						+ "tisas_new_ser,tifcs_type,tifcs_regn_no,\r\n"
						+ "TO_CHAR(tifcs_instln_dt,'DD-MM-YYYY') as tifcs_instln_dt,\r\n"
						+ "TO_CHAR(tifcs_vintage,'DD-MM-YYYY') as tifcs_vintage,\r\n"
						+ "COALESCE(relay_kr_hr,0) as relay_kr_hr,relay_kr_ser,\r\n"
						+ "COALESCE(relay_kr_failure_at,0) as relay_kr_failure_at,\r\n"
						+ "COALESCE(lrf_hr,0) as lrf_hr,\r\n"
						+ "lrf_ser,\r\n"
						+ "COALESCE(lrf_failure_at,0) as lrf_failure_at,\r\n"
						+ "COALESCE(gyro_direc_indi_hr,0) as gyro_direc_indi_hr,\r\n"
						+ "gyro_direc_indi_ser,\r\n"
						+ "COALESCE(gyro_direc_failure_at,0) as gyro_direc_failure_at,\r\n"
						+ "COALESCE(dist_box_hr,0) as dist_box_hr,\r\n"
						+ "dist_box_ser,\r\n"
						+ "COALESCE(dist_box_failure_at,0) as dist_box_failure_at\r\n"
						+ ",tim_fitted,\r\n"
						+ "TO_CHAR(tim_intls_dt,'DD-MM-YYYY') as tim_intls_dt,\r\n"
						+ "TO_CHAR(tim_vintage,'DD-MM-YYYY') as tim_vintage,tis_fitted,\r\n"
						+ "TO_CHAR(tis_instaln_dt,'DD-MM-YYYY') as tis_instaln_dt,\r\n"
						+ "TO_CHAR(tis_vintage,'DD-MM-YYYY') as tis_vintage,u_tim_fitted,\r\n"
						+ "TO_CHAR(u_tim_intsln_dt,'DD-MM-YYYY') as u_tim_intsln_dt,\r\n"
						+ "TO_CHAR(u_tim_vintage,'DD-MM-YYYY') as u_tim_vintage,m_tim_fitted,\r\n"
						+ "TO_CHAR(m_tim_instln_dt,'DD-MM-YYYY') as m_tim_instln_dt,\r\n"
						+ "TO_CHAR(m_tim_vintage,'DD-MM-YYYY') as m_tim_vintage,m_tisk_fitted,\r\n"
						+ "TO_CHAR(m_tisk_instals_dt,'DD-MM-YYYY') as m_tisk_instals_dt,\r\n"
						+ "TO_CHAR(m_tisk_vintage,'DD-MM-YYYY') as m_tisk_vintage,\r\n"
						+ "dns_fitted,\r\n"
						+ "TO_CHAR(dns_instln_dt,'DD-MM-YYYY') as dns_instln_dt,dns_ser,cti_fitted,\r\n"
						+ "TO_CHAR(cti_instln_dt,'DD-MM-YYYY') as cti_instln_dt,alns_fitted,alns_ser,gps_fitted,gps_ser,ecu_fitted,ecu_ser,spta_fitted,spta_ser,powerpack_ser,\r\n"
						+ "TO_CHAR(powerpack_demand_dt,'DD-MM-YYYY') as powerpack_demand_dt,\r\n"
						+ "stab_ser,\r\n"
						+ "TO_CHAR(stab_demand_dt,'DD-MM-YYYY') as stab_demand_dt,ifdss_ser,\r\n"
						+ "TO_CHAR(ifdss_demand_dt,'DD-MM-YYYY') as ifdss_demand_dt,gms_ser,\r\n"
						+ "TO_CHAR(gms_demand_dt,'DD-MM-YYYY') as gms_demand_dt,cps_mk_ser,\r\n"
						+ "TO_CHAR(cps_mk_demand_dt,'DD-MM-YYYY') as cps_mk_demand_dt,dvr_pnvd_ser,\r\n"
						+ "TO_CHAR(dvr_pnvd_demand_dt,'DD-MM-YYYY') as dvr_pnvd_demand_dt,\r\n"
						+ "fcc_ser,\r\n"
						+ "TO_CHAR(fcc_demand_dt,'DD-MM-YYYY') as fcc_demand_dt,\r\n"
						+ "COALESCE(bogie_wheels_held_onrd,0) as bogie_wheels_held_onrd,\r\n"
						+ "COALESCE(bogie_wheels_held_offrd,0) as bogie_wheels_held_offrd,\r\n"
						+ "TO_CHAR(bogie_wheels_demand_dt,'DD-MM-YYYY') as bogie_wheels_demand_dt,top_roll_ser,\r\n"
						+ "TO_CHAR(top_roll_demand_dt,'DD-MM-YYYY') as top_roll_demand_dt,\r\n"
						+ "COALESCE(track_pads_held_onrd,0) as track_pads_held_onrd,COALESCE(track_pads_held_offrd,0) as track_pads_held_offrd,\r\n"
						+ "TO_CHAR(track_pads_demand_dt,'DD-MM-YYYY') as track_pads_demand_dt,cdr_cont_stn_ser,\r\n"
						+ "TO_CHAR(cdr_cont_stn_demmand_dt,'DD-MM-YYYY') as cdr_cont_stn_demmand_dt,gcdu_ser,\r\n"
						+ "TO_CHAR(gcdu_demand_dt,'DD-MM-YYYY') as gcdu_demand_dt,low_tempered_bal\r\n"
						+ "  from tb_tms_census_retrn part\r\n"
						+ "  join tb_tms_banum_dirctry d\r\n"
						+ "    on d.ba_no::text = part.ba_no::text\r\n"
						+ "  join tb_tms_mct_main_master m\r\n"
						+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
						+ "   and m.type_of_veh::text = 'A'::text\r\n"
						+ "  join tb_miso_orbat_unt_dtl u\r\n"
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
						+ "               max(case when oh_mr = '1' then km end) as km4,\r\n"
						+ "               max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as vintage4,\r\n"
						+ "               max(case when oh_mr = '3' then km end)as km5,\r\n"
						+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as vintage5,\r\n"
						+ "               max(case when oh_mr = '5' then km end) as km6,\r\n"
						+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as vintage6\r\n"
						+ "          from tb_tms_mct_main_oh_mr\r\n"
						+ "         group by mct_main_id\r\n"
						+ "       ) as oh_cal\r\n"
						+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
						+ " inner join tb_miso_orbat_line_dte u1\r\n"
						+ "    on m.sus_no=u1.line_dte_sus\r\n"
						+ "  left join orbat_view_cmd c\r\n"
						+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
						+ "  left join orbat_view_corps corps\r\n"
						+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
						+ "  left join orbat_view_div div\r\n"
						+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
						+ "  left join orbat_view_bde bde\r\n"
						+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
						+ "  left join (\r\n"
						+ "        select ba_no,\r\n"
						+ "               status,\r\n"
						+ "               max(case when type_of_intervention = '2' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh1date,\r\n"
						+ "               max(case when type_of_intervention = '4' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh2date,\r\n"
						+ "               max(case when type_of_intervention = '6' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh3date,\r\n"
						+ "               max(case when type_of_intervention = '1' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr1date,\r\n"
						+ "               max(case when type_of_intervention = '3' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr2date,\r\n"
						+ "               max(case when type_of_intervention = '5' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr3date\r\n"
						+ "          from tb_tms_cin\r\n"
						+ "         where type_of_veh = 'A'\r\n"
						+ "           and status != '0'\r\n"
						+ "         group by ba_no,\r\n"
						+ "                  status\r\n"
						+ "       ) as cin\r\n"
						+ "    on cin.ba_no = part.ba_no\r\n"
						+ " inner join (\r\n"
						+ "        select part2.ba_no,\r\n"
						+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                     end as extracted_year\r\n"
						+ "          from tb_tms_census_retrn part2\r\n"
						+ "       ) as ba_registraion\r\n"
						+ "    on ba_registraion.ba_no = part.ba_no where part.sus_no = ? "
						+ " order by part.ba_no,\r\n"
						+ "          9,\r\n"
						+ "          10\r\n"
						+ "";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, sus_no);
				
				ResultSet rs = stmt.executeQuery();
				
				System.out.println("A Veh --> " + stmt);
				
				ResultSetMetaData r = rs.getMetaData();

				while (rs.next()) {
					List<String> list = new ArrayList<String>();
					
					list.add(rs.getString("id"));//0
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("date_of_cens_retrn"));
					list.add(rs.getString("mct"));
					list.add(rs.getString("dt_of_submsn"));
					list.add(rs.getString("vehcl_classfctn"));//5
					list.add(rs.getString("vehcl_kms_run"));
					list.add(rs.getString("engine_kms_run"));
					list.add(rs.getString("engine_hrs_run"));
					list.add(rs.getString("aux_engn_clasfctn"));
					list.add(rs.getString("aux_engn_hrs_run"));//10
					list.add(rs.getString("main_gun_type"));
					list.add(rs.getString("main_gun_efc"));
					list.add(rs.getString("main_gun_qr"));
					list.add(rs.getString("main_radio_set_nomcltr"));
					list.add(rs.getString("main_radio_set"));//15
					list.add(rs.getString("main_radio_set_condn"));
					list.add(rs.getString("unit_remarks"));
					list.add(rs.getString("status"));
					list.add(rs.getString("aprv_rejec_remarks"));
					list.add(rs.getString("approved_by"));//20
					list.add(rs.getString("approve_date"));
					list.add(rs.getString("creation_by"));
					list.add(rs.getString("creation_date"));
					list.add(rs.getString("modify_by"));
					list.add(rs.getString("modify_date"));//25
					list.add(rs.getString("deleted_by"));
					list.add(rs.getString("deleted_date"));
					list.add(rs.getString("version_no"));
					list.add(rs.getString("softdelete"));//29

					list.add(rs.getString("comd"));
					list.add(rs.getString("corps"));
					list.add(rs.getString("div"));
					list.add(rs.getString("bde"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("line_dte"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("mct_nomen"));
					list.add(rs.getString("ba_no"));
					list.add(rs.getString("kms_run"));
					list.add(rs.getString("classification"));
					
					
					list.add(rs.getString("km1"));
					list.add(rs.getString("vintage1"));
					list.add(rs.getString("km2"));
					list.add(rs.getString("vintage2"));
					list.add(rs.getString("km3"));
					list.add(rs.getString("vintage3"));
					list.add(rs.getString("km4"));
					list.add(rs.getString("vintage4"));
					list.add(rs.getString("km5"));
					list.add(rs.getString("vintage5"));
					list.add(rs.getString("km6"));
					list.add(rs.getString("vintage6"));
					list.add(rs.getString("oh1_due_date"));
					list.add(rs.getString("oh2_due_date"));
					list.add(rs.getString("oh3_due_date"));
					list.add(rs.getString("mr1_due_date"));
					list.add(rs.getString("mr2_due_date"));
					list.add(rs.getString("mr3_due_date"));
					
					list.add(rs.getString("oh1date"));
					list.add(rs.getString("oh2date"));
					list.add(rs.getString("oh3date"));
					list.add(rs.getString("mr1date"));
					list.add(rs.getString("mr2date"));
					list.add(rs.getString("mr3date"));
					
					list.add(rs.getString("engine_no"));
					list.add(rs.getString("engine_type"));
					list.add(rs.getString("engine_hrs_current"));
					list.add(rs.getString("engine_hr_total"));
					list.add(rs.getString("engine_failure_at"));
					list.add(rs.getString("engine_submission_dt"));
					list.add(rs.getString("engine_repair_comp_dt"));
					
					list.add(rs.getString("air_compressor_no"));
					list.add(rs.getString("air_compressor_type"));
					list.add(rs.getString("air_compressor_total_hrs"));
					list.add(rs.getString("air_compressor_failure_at"));
					list.add(rs.getString("air_compressor_submission_dt"));
					list.add(rs.getString("air_compressor_repair_comp_dt"));
					list.add(rs.getString("sgb_no"));
					list.add(rs.getString("sgb_type"));
					list.add(rs.getString("sgb_total_km"));
					list.add(rs.getString("sgb_failure_at"));
					list.add(rs.getString("sgb_submission_dt"));
					list.add(rs.getString("sgb_repair_comp_dt"));
					list.add(rs.getString("igb_no"));
					
					list.add(rs.getString("igb_type"));
					list.add(rs.getString("igb_total_km"));
					list.add(rs.getString("igb_failure_at"));
					list.add(rs.getString("igb_submission_dt"));
					list.add(rs.getString("igb_repair_comp_dt"));
					list.add(rs.getString("track_kms"));
					list.add(rs.getString("track_assy_ser"));
					list.add(rs.getString("track_assy_failure_at"));
					list.add(rs.getString("sprocket_assy_total_km"));
					list.add(rs.getString("sprocket_assy_failure_at"));
					
					list.add(rs.getString("sprocket_assy_ser"));
					list.add(rs.getString("pump_drive_motor_hr"));
					list.add(rs.getString("pump_drive_motor_ser"));
					list.add(rs.getString("pump_drive_motor_failure_at"));
					list.add(rs.getString("starter_genr_total_hr"));
					list.add(rs.getString("starter_genr_ser"));
					list.add(rs.getString("starter_genr_failure_at"));
					list.add(rs.getString("turbo_charger_type"));
					list.add(rs.getString("turbo_charger_defect_at"));
					list.add(rs.getString("turbo_charger_submission_dt"));
					
					list.add(rs.getString("turbo_charger_repair_comp_dt"));
					list.add(rs.getString("engine_demand_dt"));
					list.add(rs.getString("engine_rel_dt"));
					list.add(rs.getString("fire_fight_sys_cyl_auth"));
					list.add(rs.getString("fire_fight_sys_cyl_held"));
					list.add(rs.getString("fire_fight_sys_cyl_empty"));
					list.add(rs.getString("fire_fight_cyl_demand_dt"));
					list.add(rs.getString("cbrn_sys_over_pressure"));
					list.add(rs.getString("cbrn_filter_qty"));
					list.add(rs.getString("cbrn_filter_ser"));
					list.add(rs.getString("pkuza_ser"));
					
					list.add(rs.getString("bas_a_ser"));
					list.add(rs.getString("cbrn_spl_blower_ser"));
					list.add(rs.getString("mine_plough_ser"));
					list.add(rs.getString("emp_ser"));
					list.add(rs.getString("mrs_ser"));
					list.add(rs.getString("mrs_type"));
					list.add(rs.getString("mrs_qty"));
					list.add(rs.getString("dch_installed"));
					list.add(rs.getString("dch_type"));
					list.add(rs.getString("dch_installed_dt"));
					list.add(rs.getString("dch_vintage"));
					
					list.add(rs.getString("dch_ser"));
					list.add(rs.getString("cti_install_dt"));
					list.add(rs.getString("cti_vintage"));
					list.add(rs.getString("cti_ser"));
					list.add(rs.getString("main_gun_article_no"));
					list.add(rs.getString("type_gun_article"));
					list.add(rs.getString("main_gun_article_qtr"));
					list.add(rs.getString("main_gun_article_ser"));
					list.add(rs.getString("main_gun_article_efc"));
					list.add(rs.getString("no_of_msls_fired"));
					list.add(rs.getString("no_of_msls_failed"));
					
					list.add(rs.getString("sec_gun_type"));
					list.add(rs.getString("sec_gun_regn_no"));
					list.add(rs.getString("sec_gun_ser"));
					list.add(rs.getString("aa_gun_type"));
					list.add(rs.getString("aa_gun_regn_no"));
					list.add(rs.getString("aa_gun_ser"));
					list.add(rs.getString("msl_launcher_regn_no"));
					list.add(rs.getString("msl_launcher_ser"));
					list.add(rs.getString("msl_launcher_oh_due"));
					list.add(rs.getString("msl_lr_oh_done_dt"));
					list.add(rs.getString("tisas_old_instln_dt"));
					
					list.add(rs.getString("tisas_old_vintage"));
					list.add(rs.getString("tisas_old_ser"));
					list.add(rs.getString("tisas_new_instln_dt"));
					list.add(rs.getString("tisas_new_vintage"));
					list.add(rs.getString("tisas_new_ser"));
					list.add(rs.getString("tifcs_type"));
					list.add(rs.getString("tifcs_regn_no"));
					list.add(rs.getString("tifcs_instln_dt"));
					list.add(rs.getString("tifcs_vintage"));
					list.add(rs.getString("relay_kr_hr"));
					list.add(rs.getString("relay_kr_ser"));
					
					list.add(rs.getString("relay_kr_failure_at"));
					list.add(rs.getString("lrf_hr"));
					list.add(rs.getString("lrf_ser"));
					list.add(rs.getString("lrf_failure_at"));
					list.add(rs.getString("gyro_direc_indi_hr"));
					list.add(rs.getString("gyro_direc_indi_ser"));
					list.add(rs.getString("gyro_direc_failure_at"));
					list.add(rs.getString("dist_box_hr"));
					list.add(rs.getString("dist_box_ser"));
					list.add(rs.getString("dist_box_failure_at"));
					list.add(rs.getString("tim_fitted"));
					
					list.add(rs.getString("tim_intls_dt"));
					list.add(rs.getString("tim_vintage"));
					list.add(rs.getString("tis_fitted"));
					list.add(rs.getString("tis_instaln_dt"));
					list.add(rs.getString("tis_vintage"));
					list.add(rs.getString("u_tim_fitted"));
					list.add(rs.getString("u_tim_intsln_dt"));
					list.add(rs.getString("u_tim_vintage"));
					list.add(rs.getString("m_tim_fitted"));
					list.add(rs.getString("m_tim_instln_dt"));
					list.add(rs.getString("m_tim_vintage"));
					
					list.add(rs.getString("m_tisk_fitted"));
					list.add(rs.getString("m_tisk_instals_dt"));
					list.add(rs.getString("m_tisk_vintage"));
					list.add(rs.getString("dns_fitted"));
					list.add(rs.getString("dns_instln_dt"));
					list.add(rs.getString("dns_ser"));
					list.add(rs.getString("cti_fitted"));
					list.add(rs.getString("cti_instln_dt"));
					list.add(rs.getString("alns_fitted"));
					list.add(rs.getString("alns_ser"));
					list.add(rs.getString("gps_fitted"));
					
					list.add(rs.getString("gps_ser"));
					list.add(rs.getString("ecu_fitted"));
					list.add(rs.getString("ecu_ser"));
					list.add(rs.getString("spta_fitted"));
					list.add(rs.getString("spta_ser"));
					list.add(rs.getString("powerpack_ser"));
					list.add(rs.getString("powerpack_demand_dt"));
					list.add(rs.getString("stab_ser"));
					list.add(rs.getString("stab_demand_dt"));
					list.add(rs.getString("ifdss_ser"));
					list.add(rs.getString("ifdss_demand_dt"));
					
					list.add(rs.getString("gms_ser"));
					list.add(rs.getString("gms_demand_dt"));
					list.add(rs.getString("cps_mk_ser"));
					list.add(rs.getString("cps_mk_demand_dt"));
					list.add(rs.getString("dvr_pnvd_ser"));
					list.add(rs.getString("dvr_pnvd_demand_dt"));
					list.add(rs.getString("fcc_ser"));
					list.add(rs.getString("fcc_demand_dt"));
					list.add(rs.getString("bogie_wheels_held_onrd"));
					list.add(rs.getString("bogie_wheels_held_offrd"));
					list.add(rs.getString("bogie_wheels_demand_dt"));
					list.add(rs.getString("top_roll_ser"));
					list.add(rs.getString("top_roll_demand_dt"));
					list.add(rs.getString("track_pads_held_onrd"));
					list.add(rs.getString("track_pads_held_offrd"));
					list.add(rs.getString("track_pads_demand_dt"));
					list.add(rs.getString("cdr_cont_stn_ser"));
					list.add(rs.getString("cdr_cont_stn_demmand_dt"));
					list.add(rs.getString("gcdu_ser"));
					list.add(rs.getString("gcdu_demand_dt"));
					list.add(rs.getString("low_tempered_bal"));
					

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

	public ArrayList<List<String>> getRepairReport(String sus_no) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select c.ba_no,\r\n" + "        d.mct,\r\n" + "        child.country_of_origin,\r\n"
					+ "        m.std_nomclature,\r\n" + "        c.vehcl_classfctn,\r\n"
					+ "        c.veh_km_run_period,\r\n" + "        c.vehcl_kms_run,\r\n" + "        c.track_kms,\r\n"
					+ "        c.mr1_due,\r\n" + "        c.mr1_done,\r\n" + "        c.oh1_due,\r\n"
					+ "        c.oh1_detl,\r\n" + "        c.oh1_done,\r\n" + "        c.mr2_due,\r\n"
					+ "        c.mr2_done,\r\n" + "        c.oh2_due,\r\n" + "        c.oh2_detl,\r\n"
					+ "        c.oh2_done,\r\n" + "        c.mr3_due,\r\n" + "        c.mr3_done,\r\n"
					+ "       c.id\r\n" + "        \r\n" + "        from tb_tms_census_retrn c \r\n"
					+ "	left join TB_TMS_BANUM_DIRCTRY d on c.ba_no = d.ba_no and veh_cat = 'A'\r\n"
					+ "        left join tb_tms_banum_req_child child on c.ba_no = child.ba_no and child.veh_cat='A'\r\n"
					+ "        inner join TB_TMS_MCT_MASTER m on d.mct = m.mct and m.type_of_vehicle = 'A' \r\n"
					+ "\r\n" + "        where c.sus_no=? order by ba_no";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("ba_no")); // 0
				list.add(rs.getString("mct"));// 1
				list.add(rs.getString("std_nomclature"));// 2
				list.add(rs.getString("country_of_origin"));// 3
				list.add(rs.getString("vehcl_classfctn")); // 4
				list.add(rs.getString("veh_km_run_period")); // 5
				list.add(rs.getString("vehcl_kms_run")); // 6
				list.add(rs.getString("track_kms"));// 7
				list.add(rs.getString("mr1_due"));// 8
				list.add(rs.getString("mr1_done"));// 9
				list.add(rs.getString("oh1_due")); // 10
				list.add(rs.getString("oh1_detl")); // 11
				list.add(rs.getString("oh1_done"));// 12
				list.add(rs.getString("mr2_due"));// 13
				list.add(rs.getString("mr2_done")); // 14
				list.add(rs.getString("oh2_due")); // 15
				list.add(rs.getString("oh2_detl")); // 16
				list.add(rs.getString("oh2_done"));// 17
				list.add(rs.getString("mr3_due"));// 18
				list.add(rs.getString("mr3_done")); // 19
				list.add(rs.getString("id")); // 20
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

	public ArrayList<List<String>> getMCRReportList(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select distinct r.sus_no,(select unit_name from  tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name from tb_tms_census_retrn r where sus_no=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no")); // 0
				list.add(rs.getString("unit_name"));// 1

				String Approved = "onclick=\" View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='btn btn-default btn-xs' " + Approved + " title='View Data'>View</i>";

				String f = "";
				f += appButton;

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
}