package com.dao.cue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.Cue_Line_DteContoller;
import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_VETTING_DET;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_wepe_conditionDAOImpl implements Cue_wepe_conditionDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


public List<Map<String, Object>> AttributeReportSearchWePecondition(String we_pe, String we_pe_no,
			String uploaded_wepe, String sponsor_dire, String arm, String doc_type, String getroleid, String status,
			String roleType, String roleAccess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (status != "" && status != null && status != "null") {
				if (!status.equals("all")) {
					qry += " and wcon.status = ?";
				}
			}
			else if (status == "") {
				if (roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
					qry += " and wcon.status = '1'";
				}
			}
			if (uploaded_wepe != "" && uploaded_wepe != null) {
				qry += " and wcon.uploaded_wepe = ?";
			}
			if (we_pe != "" && we_pe != null) {
				qry += " and wcon.we_pe = ?";
			}
			if (we_pe_no != "") {
				qry += " and wcon.we_pe_no = ?";
			}

//			if( !arm.equals("0")) {
//
//				qry += " and wcon.arm = ?";
//			}
			if (!sponsor_dire.equals("0") && !sponsor_dire.equals("") && !sponsor_dire.equals(null)) {
				qry += " and wcon.sponsor_dire = ?";
			}
			if (arm != null && !arm.equals(null) && arm != "" && !arm.equals("0")) {
				qry += " and wcon.arm = ?";
			}
			
			if (doc_type != "") {
				if (doc_type != "Confidential" || doc_type != "Restricted" || doc_type != "Secret") {
					qry += " and wcon.doc_type = ?";
				}
			}
			if (getroleid != "0" && getroleid != "" && getroleid != null && getroleid != "null") {
				qry += " and wcon.type = ?";
			}
			/*q = "select   wcon.id,wcon.we_pe,wcon.uploaded_wepe,wcon.we_pe_no,wcon.sponsor_dire,a.arm_desc,wcon.doc_type,wcon.reject_letter_no,wcon.reject_remarks,wcon.table_title,wcon.suprcdd_we_pe_no,wcon.status from cue_tb_miso_wepeconditions wcon,tb_miso_orbat_arm_code a "
					+ " where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from cue_tb_miso_wepeconditions where suprcdd_we_pe_no is not null) "
					+ " and wcon.arm  =a.arm_code " + qry + " order by wcon.we_pe_no, wcon.status ";*/
			///191223 v2 bisag(change sus no into line dte name)
		     q ="select distinct   wcon.id,wcon.we_pe,wcon.uploaded_wepe,wcon.we_pe_no,wcon.sponsor_dire,sd.line_dte_name,a.arm_desc,wcon.doc_type,\r\n" + 
		     		"wcon.reject_letter_no, wcon.reject_remarks,wcon.table_title,wcon.suprcdd_we_pe_no,wcon.status, \r\n" + 
		     		"		     		wcon.created_by as cr_by,wcon.created_on as cr_on,wcon.approved_rejected_by as app_by,wcon.date_of_apprv_rejc as app_on,\r\n" + 
		     		"		     		wcon.modified_by as modi_by,wcon.modified_on as modi_on,wcon.type ,wcon.eff_frm_date,wcon.eff_to_date,dtl.vetted_by,dtl.vetted_on,wcon.arm  " + 
		     		"					from cue_tb_miso_wepeconditions wcon\r\n" + 
		     		"					left join tb_miso_orbat_arm_code a  on wcon.arm=a.arm_code\r\n" + 
		     		"					LEFT join tb_miso_orbat_line_dte sd on sd.line_dte_sus=wcon.sponsor_dire  \r\n" +
		     		"					left join cue_tb_miso_vetting_det dtl on dtl.we_pe_no=wcon.we_pe_no and dtl.type=wcon.type\r\n" +  
		     		"	                 where wcon.we_pe_no is not null and wcon.we_pe_no NOT IN (select suprcdd_we_pe_no from cue_tb_miso_wepeconditions where suprcdd_we_pe_no is not null) \r\n"  
	                + qry + " order by wcon.we_pe_no, wcon.status ";


			stmt = conn.prepareStatement(q);
			int j = 1;
			if (status != "" && status != null && status != "null") {
				if (!status.equals("all")) {
					stmt.setString(j, status);
					j += 1;
				}
			}
			if (uploaded_wepe != "" && uploaded_wepe != null) {
				stmt.setString(j, uploaded_wepe);
				j += 1;
			}
			if (we_pe != "" && we_pe != null) {
				stmt.setString(j, we_pe);
				j += 1;
			}
			if (we_pe_no != "") {
				stmt.setString(j, we_pe_no);
				j += 1;
			}
	
//			if( !arm.equals("0")) {
//				stmt.setString(j, arm);
//				j += 1;
//			}
			if (!sponsor_dire.equals("0") && !sponsor_dire.equals("") && !sponsor_dire.equals(null)) {
				stmt.setString(j, sponsor_dire);
				j += 1;
			}
			if (arm != null && !arm.equals(null) && arm != "" && !arm.equals("0")) {
				stmt.setString(j, arm);
				j += 1;
			}
			if (doc_type != "") {
				if (doc_type != "Confidential" || doc_type != "Restricted" || doc_type != "Secret")
					stmt.setString(j, doc_type);
				j += 1;
			}
			if (getroleid != "0" && getroleid != "" && getroleid != null && getroleid != "null") {
				stmt.setString(j, getroleid);
			}
			ResultSet rs = stmt.executeQuery();
			System.err.println("search we pe:hhhhhhhhhhhhhhhhh : " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				  String LogButton = cueContoller.Get_button_info(metaData,rs);
                  String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
                  String LogButton2 = Cue_Line_DteContoller.Get_button_info2(metaData,rs);
                  
                  String Approved = "onclick=\"if (confirm('Are you sure you want to approve?')) { Approved(" +
                          rs.getObject(1) + ", '" + rs.getObject(12) + "','" + rs.getObject(4) + "','" + rs.getObject(20) + "','" + rs.getObject(13) + "'); } else{ return false;}\"";

				
				String appButton = "<i class='action_icons action_approve' " + Approved + " title='Approve Data'></i>";

				String Addmore = "onclick=\"  if (confirm('Are You Sure you want to View Data?') ){AddMoreData("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "','" + rs.getObject(5) + "','" + rs.getObject(25) + "')}else{ return false;}\"";
				String addmore = "<i class='action_icons action_view' " + Addmore + " title='View More Data'></i>";
				
				String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
						+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
						+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
				
				String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' " + Reject
						+ " title='Reject Data' data-toggle='modal'  ></i>";

				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
						+ rs.getObject(1) + ", '" + rs.getObject(12) + "')}else{ return false;}\"";
				
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
				String f = "";
				if (status.equals("0")) {
					if (roleType.equals("ALL")) {
						f += appButton;
						f += rejectButton;
						f += deleteButton;
						f += updateButton;
						f += LogButton;

					}
					if (roleType.equals("APP")) {
						f += appButton;
						f += rejectButton;
						f += LogButton;
					}
					if (roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
						f += deleteButton;
						f += updateButton;
						f += appButton;
						f += rejectButton;
						f += LogButton;

					}

				} else if (status.equals("1")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += rejectButton;
						f += LogButton1;

					}
					if (roleType.equals("DEO")) {
						f += appButton1;
						f += LogButton1;
					}

				} else if (status.equals("2")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += appButton;
					}
					if (roleType.equals("DEO") || roleType.equals("ALL")  || roleAccess.equals("Line_dte")) {

						f += deleteButton;
						f += updateButton;
					}

				}
				else if (status.equals("")) {
					if (roleType.equals("DEO") || roleAccess.equals("Line_dte") || roleAccess.equals("MISO")){
						
						f += addmore;
					}
					if (roleAccess.equals("Line_dte")) {
						//f+= LogButton2;
					}
				}

				columns.put(metaData.getColumnLabel(1), f);

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

	public List<Map<String, Object>> AttributeReportSearchWePecondition1(String uploaded_wepe, String we_pe_no,
			String we_pe, String sponsor_dire, String arm, String doc_type, String status, String setTypeOnclick) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!status.equals("all")) {
				qry += " and (wcon.status = '0' or wcon.status = '2' or wcon.status = '1')  ";
			}

			if (uploaded_wepe != "" && uploaded_wepe != null) {
				qry += " and wcon.uploaded_wepe = ?";
			}

			if (we_pe != "" && we_pe != null) {
				qry += " and wcon.we_pe = ?";
			}

			if (we_pe_no != "") {
				qry += " and wcon.we_pe_no = ?";
			}
			if (!sponsor_dire.equals("0") && !sponsor_dire.equals("") && !sponsor_dire.equals(null)) {
				qry += " and wcon.sponsor_dire = ?";
			}
			if (arm != null && !arm.equals(null) && arm != "" && !arm.equals("0")) {
				qry += " and wcon.arm = ?";
			}
			if (setTypeOnclick != "") {
				qry += " and wcon.type = ?";
			}
			if (doc_type != "") {
				if (!doc_type.equals("Confidential") && !doc_type.equals("Restricted") && !doc_type.equals("Secret"))
					qry += " and wcon.doc_type = ?";
			}
			///jainisha

		/*	q = "select   wcon.id,wcon.we_pe,wcon.uploaded_wepe,wcon.we_pe_no,wcon.sponsor_dire,a.arm_desc,wcon.doc_type,wcon.reject_letter_no,wcon.reject_remarks,wcon.table_title,wcon.suprcdd_we_pe_no,wcon.status from cue_tb_miso_wepeconditions wcon,tb_miso_orbat_arm_code a "
					+ " where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from cue_tb_miso_wepeconditions where suprcdd_we_pe_no is not null) "
					+ " and wcon.arm  =a.arm_code " + qry + " order by wcon.we_pe_no, wcon.status ";*/
			q ="select distinct  wcon.id,wcon.we_pe,wcon.uploaded_wepe,wcon.we_pe_no,wcon.sponsor_dire,sd.line_dte_name ,a.arm_desc,wcon.doc_type,wcon.reject_letter_no,wcon.reject_remarks,wcon.table_title,wcon.suprcdd_we_pe_no,wcon.status from cue_tb_miso_wepeconditions wcon,tb_miso_orbat_arm_code a ,tb_miso_orbat_line_dte sd \r\n"
					+ "where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from cue_tb_miso_wepeconditions where suprcdd_we_pe_no is not null) \r\n"
					+ "and wcon.arm  =a.arm_code and sd.line_dte_sus=wcon.sponsor_dire " + qry + " order by wcon.we_pe_no, wcon.status";
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (uploaded_wepe != "" && uploaded_wepe != null) {
				stmt.setString(j, uploaded_wepe);
				j += 1;
			}
			if (we_pe != "" && we_pe != null) {
				stmt.setString(j, we_pe);
				j += 1;
			}
			if (we_pe_no != "") {
				stmt.setString(j, we_pe_no);
				j += 1;
			}
			if (!sponsor_dire.equals("0") && !sponsor_dire.equals("") && !sponsor_dire.equals(null)) {
				stmt.setString(j, sponsor_dire);
				j += 1;
			}

			if (arm != null && !arm.equals(null) && arm != "" && !arm.equals("0")) {
				stmt.setString(j, arm);
				j += 1;
			}
			if (setTypeOnclick != "") {
				stmt.setString(j, setTypeOnclick);
				j += 1;
			}

			if (doc_type != "") {
				if (!doc_type.equals("Confidential") && !doc_type.equals("Restricted") && !doc_type.equals("Secret"))
					stmt.setString(j, doc_type);
			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String Addmore = "onclick=\"  if (confirm('Are You Sure you want to View Data?') ){AddMoreData("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String addmore = "<i class='action_icons action_view' " + Addmore + " title='View More Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";

				String f = "";
				if (rs.getObject(13).equals("1")) {
					f += addmore;
					f += appButton;
				} else {
					f += deleteButton;
					f += updateButton;
					f += addmore;
				}

				columns.put(metaData.getColumnLabel(1), f);

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

	public String setApprovedwepecondition(int appid, int roleid, String username, String roleType,
			String setTypeOnclick) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPECONDITIONS c set c.status = :status, approved_rejected_by=:approved_rejected_by, date_of_apprv_rejc=:date where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setInteger("id", appid).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}
	
	public String updatecapdatawepe(String we_pe_no,String superno,int roleid,String username,String roleType,String nwepe,String type,String Pstatus) {
		String ms="";
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		
		if(type.equals("3")) {
			 if(!(Pstatus.equals("2"))) {
		
		/////-------------------------- WEAPON DETAILS -----------------------------///////////////////////////////////
				 Query c=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no =:nwepe  and status='0'");
				 c.setParameter("nwepe", nwepe);
					Long count = (Long)c.uniqueResult();
					if(count==0) {
		Query q= session.createQuery(
				"from CUE_TB_MISO_WEPE_WEAPON_DET c where c.we_pe_no=:superno");
		q.setString("superno", superno);
		
		
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPON_DET> list1 = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) q.list();

		
			for(int i=0; i<list1.size(); i++) {	
				CUE_TB_MISO_WEPE_WEAPON_DET weapDtModel = new CUE_TB_MISO_WEPE_WEAPON_DET();
			double auth_weapon = new Double(list1.get(i).getAuth_weapon());
			weapDtModel.setAuth_weapon(auth_weapon);
			weapDtModel.setCreated_by(username);
			weapDtModel.setCreated_on(Date);
			weapDtModel.setRemarks(list1.get(i).getRemarks());
			weapDtModel.setStatus("0");
			weapDtModel.setWe_pe_no(nwepe);
			weapDtModel.setItem_seq_no(list1.get(i).getItem_seq_no());
			weapDtModel.setMct(list1.get(i).getMct());
			weapDtModel.setFootnote_no(list1.get(i).getFootnote_no());
			weapDtModel.setEntity(list1.get(i).getEntity());
			weapDtModel.setEntity_cond(list1.get(i).getEntity_cond());
			weapDtModel.setCes_cces(list1.get(i).getCes_cces());
			weapDtModel.setAmm_scl_for_reserve(list1.get(i).getAmm_scl_for_reserve());
			weapDtModel.setAmm_scl_on_man(list1.get(i).getAmm_scl_on_man());
			weapDtModel.setRoleid(roleid);
			int  a = (int)session.save(weapDtModel);
			
			if(a!=0) {
				ms="Data Saved";
			}else {
				ms="Data not Saved";
			}
					
		}
			 }
			
		/////-------------------------- WEAPON MODIFICATIOS -----------------------------///////////////////////////////////
			
					Query c1=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no =:nwepe  and status='0'");
					 c1.setParameter("nwepe", nwepe);
					Long count1 = (Long)c1.uniqueResult();
					if(count1==0) {
				
			Query q1= session.createQuery(
					"from CUE_TB_MISO_WEPE_WEAPONS_MDFS c where c.we_pe_no=:superno");
			q1.setString("superno", superno);
			
			
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> list2 = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) q1.list();

			
				for(int i=0; i<list2.size(); i++) {	
					CUE_TB_MISO_WEPE_WEAPONS_MDFS weapMdfsModel = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
				weapMdfsModel.setAmt_inc_dec(list2.get(i).getAmt_inc_dec());
				weapMdfsModel.setModification(list2.get(i).getModification());
				weapMdfsModel.setStatus("0");
				weapMdfsModel.setCreated_by(username);
				weapMdfsModel.setCreated_on(Date);
				weapMdfsModel.setWe_pe_no(nwepe);
				weapMdfsModel.setItem_seq_no(list2.get(i).getItem_seq_no());
				weapMdfsModel.setLocation(list2.get(i).getLocation());
				weapMdfsModel.setFormation(list2.get(i).getFormation());
				weapMdfsModel.setRemarks(list2.get(i).getRemarks());
				weapMdfsModel.setScenario(list2.get(i).getScenario());
				weapMdfsModel.setScenario_unit(list2.get(i).getScenario_unit());
				weapMdfsModel.setVersion_no(list2.get(i).getVersion_no());
				weapMdfsModel.setVettedby_dte1(list2.get(i).getVettedby_dte1());
				weapMdfsModel.setVettedby_dte2(list2.get(i).getVettedby_dte2());
				weapMdfsModel.setSoftdelete(list2.get(i).getSoftdelete());
				weapMdfsModel.setEntity(list2.get(i).getEntity());
				weapMdfsModel.setEntity_cond(list2.get(i).getEntity_cond());
				weapMdfsModel.setRoleid(roleid);			
				int  a = (int)session.save(weapMdfsModel);
			
				if(a!=0) {
					ms="Data Saved";
				}else {
					ms="Data not Saved";
				}
						
			}
					}
				
			/////-------------------------- WEAPON FOOTNOTE -----------------------------///////////////////////////////////
					Query c3=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where we_pe_no =:nwepe  and status='0'");
					 c3.setParameter("nwepe", nwepe);
					Long count3 = (Long)c3.uniqueResult();
					if(count3==0) {
				
				Query q2= session.createQuery(
						"from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES c where c.we_pe_no=:superno");
				q2.setString("superno", superno);
				
				
				@SuppressWarnings("unchecked")
				List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> list3 = (List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES>) q2.list();
				
				
					for(int i=0; i<list3.size(); i++) {	
						CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES weapFtModel = new CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES();
						weapFtModel.setAmt_inc_dec(list3.get(i).getAmt_inc_dec());
						weapFtModel.setCondition(list3.get(i).getCondition());
						weapFtModel.setCreated_by(username);
						weapFtModel.setCreated_on(Date);
						weapFtModel.setRemarks(list3.get(i).getRemarks());
						weapFtModel.setStatus("0");
						weapFtModel.setWe_pe_no(nwepe);
						weapFtModel.setItem_seq_no(list3.get(i).getItem_seq_no());
						weapFtModel.setLocation(list3.get(i).getLocation());
						weapFtModel.setFormation(list3.get(i).getFormation());
						weapFtModel.setScenario(list3.get(i).getScenario());
						weapFtModel.setScenario_unit(list3.get(i).getScenario_unit());
						weapFtModel.setRoleid(roleid);		
					int  a = (int)session.save(weapFtModel);
					if(a!=0) {
						ms="Data Saved";
					}else {
						ms="Data not Saved";
					}
							
				}
					
		}
	}
		}
		
		
		
else if(type.equals("2")) {
	 if(!(Pstatus.equals("2"))) {
			
			/////-------------------------- TRANSPORT DETAILS -----------------------------///////////////////////////////////
			
			
			Query c4=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no =:nwepe  and status='0'");
			 c4.setParameter("nwepe", nwepe);
			Long count4 = (Long)c4.uniqueResult();
			if(count4==0) {
			Query q4= session.createQuery(
					"from CUE_TB_MISO_WEPE_TRANSPORT_DET c where c.we_pe_no=:superno");
			q4.setString("superno", superno);
			
			
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_TRANSPORT_DET> listT1 = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q4.list();

			
				for(int i=0; i<listT1.size(); i++) {	
					CUE_TB_MISO_WEPE_TRANSPORT_DET transdtmodel = new CUE_TB_MISO_WEPE_TRANSPORT_DET();
					transdtmodel.setCes_cces(listT1.get(i).getCes_cces());
					transdtmodel.setEntity(listT1.get(i).getEntity());
					transdtmodel.setEntity_cond(listT1.get(i).getEntity_cond());
					transdtmodel.setFiller_1(listT1.get(i).getFiller_1());
					transdtmodel.setFiller_2(listT1.get(i).getFiller_2());
					transdtmodel.setFiller_3(listT1.get(i).getFiller_3());
					transdtmodel.setFootnote_no(listT1.get(i).getFootnote_no());
					transdtmodel.setSoftdelete(listT1.get(i).getSoftdelete());
					transdtmodel.setStatus("0");
					transdtmodel.setVettedby_dte1(listT1.get(i).getVettedby_dte1());
					transdtmodel.setVettedby_dte2(listT1.get(i).getVettedby_dte2());
					transdtmodel.setWe_pe_no(nwepe);
					transdtmodel.setMct_no(listT1.get(i).getMct_no());
					transdtmodel.setVersion_no(listT1.get(i).getVersion_no());
					transdtmodel.setRemarks(listT1.get(i).getRemarks());
					transdtmodel.setAuth_amt(listT1.get(i).getAuth_amt());
					transdtmodel.setCreated_by(username);
					transdtmodel.setCreated_on(Date);
					transdtmodel.setRoleid(roleid);	
					
				int  a = (int)session.save(transdtmodel);
				
				if(a!=0) {
					ms="Data Saved";
				}else {
					ms="Data not Saved";
				}
						
			}
			}
			/////-------------------------- TRANSPORT FOOTNOTE -----------------------------///////////////////////////////////
			Query c5=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no =:nwepe  and status='0'");
			 c5.setParameter("nwepe", nwepe);
			Long count5 = (Long)c5.uniqueResult();
			if(count5==0) {
				Query q5= session.createQuery(
						"from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES c where c.we_pe_no=:superno");
				q5.setString("superno", superno);
				
				
				@SuppressWarnings("unchecked")
				List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> listT2 = (List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES>) q5.list();
	
				
					for(int i=0; i<listT2.size(); i++) {	
						CUE_TB_MISO_WEPE_TRANS_FOOTNOTES ftmodel = new CUE_TB_MISO_WEPE_TRANS_FOOTNOTES();
						
						ftmodel.setCondition(listT2.get(i).getCondition());
						ftmodel.setCreated_by(username);
						ftmodel.setCreated_on(Date);
						ftmodel.setEntity(listT2.get(i).getEntity());
						ftmodel.setEntity_cond(listT2.get(i).getEntity_cond());
						ftmodel.setFootnote_no(listT2.get(i).getFootnote_no());
						ftmodel.setFootnote_other(listT2.get(i).getFootnote_other());
						ftmodel.setSoftdelete(listT2.get(i).getSoftdelete());
						ftmodel.setStatus("0");
						ftmodel.setType_of_footnote("1");
						ftmodel.setVettedby_dte1(listT2.get(i).getVettedby_dte1());
						ftmodel.setVettedby_dte2(listT2.get(i).getVettedby_dte2());
						ftmodel.setWe_pe_no(nwepe);
						ftmodel.setVersion_no(listT2.get(i).getVersion_no());
						ftmodel.setIn_lieu_mct(listT2.get(i).getIn_lieu_mct());
						ftmodel.setMct_no(listT2.get(i).getMct_no());
						ftmodel.setRemarks(listT2.get(i).getRemarks());
						ftmodel.setFormation(listT2.get(i).getFormation());
						ftmodel.setLocation(listT2.get(i).getLocation());
						ftmodel.setScenario(listT2.get(i).getScenario());
						ftmodel.setAmt_inc_dec(listT2.get(i).getAmt_inc_dec());
						ftmodel.setActual_inlieu_auth(listT2.get(i).getActual_inlieu_auth());
						ftmodel.setRoleid(roleid);
						ftmodel.setScenario_unit(listT2.get(i).getScenario_unit());	
		
					int  a = (int)session.save(ftmodel);
					
					if(a!=0) {
						ms="Data Saved";
					}else {
						ms="Data not Saved";
					}
							
				}
			}
					
				/////-------------------------- TRANSPORT FOOTNOTE -----------------------------///////////////////////////////////
			Query c6=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no =:nwepe  and status='0'");
			 c6.setParameter("nwepe", nwepe);
			Long count6 = (Long)c6.uniqueResult();
			if(count6==0) {
					
					Query q6= session.createQuery(
							"from CUE_TB_MISO_WEPE_TRANSPORT_MDFS c where c.we_pe_no=:superno");
					q6.setString("superno", superno);
					
					
					@SuppressWarnings("unchecked")
					List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> listT3 = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q6.list();
				
					
						for(int i=0; i<listT3.size(); i++) {	
							CUE_TB_MISO_WEPE_TRANSPORT_MDFS transmdfsmodel = new CUE_TB_MISO_WEPE_TRANSPORT_MDFS();
							
							transmdfsmodel.setCreated_by(username);
							transmdfsmodel.setCreated_on(Date);
							transmdfsmodel.setEntity(listT3.get(i).getEntity());
							transmdfsmodel.setEntity_cond(listT3.get(i).getEntity_cond());
							transmdfsmodel.setModification(listT3.get(i).getModification());
							transmdfsmodel.setRemarks(listT3.get(i).getRemarks());
							transmdfsmodel.setSoftdelete(listT3.get(i).getSoftdelete());
							transmdfsmodel.setStatus("0");
							transmdfsmodel.setVettedby_dte1(listT3.get(i).getVettedby_dte1());
							transmdfsmodel.setVettedby_dte2(listT3.get(i).getVettedby_dte2());						
						    transmdfsmodel.setWe_pe_no(nwepe);
							transmdfsmodel.setStd_nomclature(listT3.get(i).getStd_nomclature());
							transmdfsmodel.setInc_dec(listT3.get(i).getInc_dec());
							transmdfsmodel.setTable_title(listT3.get(i).getTable_title());
							transmdfsmodel.setMct_no(listT3.get(i).getMct_no());
							transmdfsmodel.setFormation(listT3.get(i).getFormation());
							transmdfsmodel.setLocation(listT3.get(i).getLocation());
							transmdfsmodel.setScenario(listT3.get(i).getScenario());
							transmdfsmodel.setAmt_inc_dec(listT3.get(i).getAmt_inc_dec());
							transmdfsmodel.setRoleid(roleid);
							transmdfsmodel.setScenario_unit(listT3.get(i).getScenario_unit());
							transmdfsmodel.setVersion_no(listT3.get(i).getVersion_no());

						int  a = (int)session.save(transmdfsmodel);
						if(a!=0) {
							ms="Data Saved";
						}else {
							ms="Data not Saved";
						}
					}
			}
	}
}
		
else if(type.equals("1")) {
	 if(!(Pstatus.equals("2"))) {
	
	
	/////-------------------------- PERSONNEL DETAILS -----------------------------///////////////////////////////////
	
	Query c7=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_DET where we_pe_no =:nwepe  and status='0'");
	 c7.setParameter("nwepe", nwepe);
	Long count7 = (Long)c7.uniqueResult();
	if(count7==0) {
	Query q7= session.createQuery(
			"from CUE_TB_MISO_WEPE_PERS_DET c where c.we_pe_no=:superno");
	q7.setString("superno", superno);
	
	
	@SuppressWarnings("unchecked")
	List<CUE_TB_MISO_WEPE_PERS_DET> listP1 = (List<CUE_TB_MISO_WEPE_PERS_DET>) q7.list();

	
		for(int i=0; i<listP1.size(); i++) {	
			CUE_TB_MISO_WEPE_PERS_DET prsdtmodel = new CUE_TB_MISO_WEPE_PERS_DET();
			prsdtmodel.setAppt_trade(listP1.get(i).getAppt_trade());
			prsdtmodel.setArm_code(listP1.get(i).getArm_code());
			prsdtmodel.setCategory_of_persn(listP1.get(i).getCategory_of_persn());
			prsdtmodel.setCode_type(listP1.get(i).getCode_type());
			prsdtmodel.setCreated_by(username);
			prsdtmodel.setCreated_on(Date);
			prsdtmodel.setEntity(listP1.get(i).getEntity());
			prsdtmodel.setEntity_cond(listP1.get(i).getEntity_cond());
			prsdtmodel.setFootnote_no(listP1.get(i).getFootnote_no());
			prsdtmodel.setRank(listP1.get(i).getRank());
			prsdtmodel.setRank_cat(listP1.get(i).getRank_cat());
			prsdtmodel.setSoftdelete(listP1.get(i).getSoftdelete());
			prsdtmodel.setStatus("0");
			prsdtmodel.setWe_pe_no(nwepe);
			prsdtmodel.setApp_trd_code(listP1.get(i).getApp_trd_code());
			prsdtmodel.setAuth_amt(listP1.get(i).getAuth_amt());
			prsdtmodel.setVettedby_dte1(listP1.get(i).getVettedby_dte1());
			prsdtmodel.setVersion_no(listP1.get(i).getVersion_no());
			prsdtmodel.setRoleid(roleid);
			
		int  a = (int)session.save(prsdtmodel);
		
		if(a!=0) {
			ms="Data Saved";
		}else {
			ms="Data not Saved";
		}
				
	}
	}
	/////-------------------------- PERSONNEL FOOTNOTE -----------------------------///////////////////////////////////
		
	Query c8=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_MDFS where we_pe_no =:nwepe  and status='0'");
	 c8.setParameter("nwepe", nwepe);
	Long count8 = (Long)c8.uniqueResult();
	if(count8==0) {

		Query q8= session.createQuery(
				"from CUE_TB_MISO_WEPE_PERS_MDFS c where c.we_pe_no=:superno");
		q8.setString("superno", superno);
		
		
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_MDFS> listP2 = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) q8.list();

		
			for(int i=0; i<listP2.size(); i++) {	
				CUE_TB_MISO_WEPE_PERS_MDFS prsmdfsmodel = new CUE_TB_MISO_WEPE_PERS_MDFS();
				
				prsmdfsmodel.setAmt_inc_dec(listP2.get(i).getAmt_inc_dec());
				prsmdfsmodel.setAppt_trade(listP2.get(i).getAppt_trade());
				prsmdfsmodel.setArm_code(listP2.get(i).getArm_code());
				prsmdfsmodel.setCat_per(listP2.get(i).getCat_per());
				prsmdfsmodel.setCreated_by(username);
				prsmdfsmodel.setCreated_on(Date);
				prsmdfsmodel.setEntity(listP2.get(i).getEntity());
				prsmdfsmodel.setEntity_cond(listP2.get(i).getEntity_cond());
				prsmdfsmodel.setModification(listP2.get(i).getModification());
				prsmdfsmodel.setRank(listP2.get(i).getRank());
				prsmdfsmodel.setRank_cat(listP2.get(i).getRank_cat());
				prsmdfsmodel.setRemarks(listP2.get(i).getRemarks());
				prsmdfsmodel.setSoftdelete(listP2.get(i).getSoftdelete());
				prsmdfsmodel.setStatus("0");
				prsmdfsmodel.setVersion_no(listP2.get(i).getVersion_no());
				prsmdfsmodel.setWe_pe_no(nwepe);
				prsmdfsmodel.setVettedby_dte1(listP2.get(i).getVettedby_dte1());
				prsmdfsmodel.setVettedby_dte2(listP2.get(i).getVettedby_dte2());
				prsmdfsmodel.setFormation(listP2.get(i).getFormation());
				prsmdfsmodel.setLocation(listP2.get(i).getLocation());
				prsmdfsmodel.setScenario(listP2.get(i).getScenario());
				prsmdfsmodel.setScenario_unit(listP2.get(i).getScenario_unit());
				prsmdfsmodel.setRoleid(roleid);					

			int  a = (int)session.save(prsmdfsmodel);
		
			if(a!=0) {
				ms="Data Saved";
			}else {
				ms="Data not Saved";
			}
					
		}
	}
			
		/////-------------------------- PERSONNEL FOOTNOTE -----------------------------///////////////////////////////////
	
	Query c9=session.createQuery("select count(*) from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where we_pe_no =:nwepe and status='0'");
	 c9.setParameter("nwepe", nwepe);
	Long count9 = (Long)c9.uniqueResult();
	if(count9==0) {
	
			Query q9= session.createQuery(
					"from CUE_TB_MISO_WEPE_PERS_FOOTNOTES c where c.we_pe_no=:superno");
			q9.setString("superno", superno);
			
			
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> listP3 = (List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES>) q9.list();			
				for(int i=0; i<listP3.size(); i++) {	
					CUE_TB_MISO_WEPE_PERS_FOOTNOTES prsftmodel = new CUE_TB_MISO_WEPE_PERS_FOOTNOTES();
					
					prsftmodel.setAmt_inc_dec(listP3.get(i).getAmt_inc_dec());
					prsftmodel.setAppt_trade(listP3.get(i).getAppt_trade());
					prsftmodel.setArm_code(listP3.get(i).getArm_code());
					prsftmodel.setCategory_of_personnel(listP3.get(i).getCategory_of_personnel());
					prsftmodel.setCondition(listP3.get(i).getCondition());
					prsftmodel.setCreated_by(username);
					prsftmodel.setCreated_on(Date);
					prsftmodel.setEntity(listP3.get(i).getEntity());
					prsftmodel.setEntity_cond(listP3.get(i).getEntity_cond());
					prsftmodel.setFootnote_no(listP3.get(i).getFootnote_no());
					prsftmodel.setFootnote_other(listP3.get(i).getFootnote_other());
					prsftmodel.setIn_lieu_appt_trade(listP3.get(i).getIn_lieu_appt_trade());
					prsftmodel.setIn_lieu_rank(listP3.get(i).getIn_lieu_rank());
					prsftmodel.setRank(listP3.get(i).getRank());
					prsftmodel.setRank_cat(listP3.get(i).getRank_cat());
					prsftmodel.setSoftdelete(listP3.get(i).getSoftdelete());
					prsftmodel.setStatus("0");
					prsftmodel.setType_of_footnote(listP3.get(i).getType_of_footnote());
					prsftmodel.setVersion_no(listP3.get(i).getVersion_no());
					prsftmodel.setWe_pe_no(nwepe);
					prsftmodel.setVettedby_dte1(listP3.get(i).getVettedby_dte1());
					prsftmodel.setVettedby_dte2(listP3.get(i).getVettedby_dte2());
					prsftmodel.setLocation(listP3.get(i).getLocation());
					prsftmodel.setFormation(listP3.get(i).getFormation());
					prsftmodel.setScenario(listP3.get(i).getScenario());
					prsftmodel.setScenario_unit(listP3.get(i).getScenario_unit());
					prsftmodel.setRoleid(roleid);

				int  a = (int)session.save(prsftmodel);
				if(a!=0) {
					ms="Data Saved";
				}else {
					ms="Data not Saved";
				}
			}
	}
	}
}
					
		tx.commit();
		session.close();
		return ms;
	}
		
	
	

	public String insertdata(String query) {
		Connection conn = null;
		String q = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();

			ps.close();
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
		return "inserted";
	}

	public String setDeleteARM(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPECONDITIONS where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", appid);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}

	public CUE_TB_MISO_WEPECONDITIONS getcue_wepeByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPECONDITIONS where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPECONDITIONS list = (CUE_TB_MISO_WEPECONDITIONS) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateArtAttValue(CUE_TB_MISO_WEPECONDITIONS artAttValues, String username, String arm_val) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hql = "update CUE_TB_MISO_WEPECONDITIONS  set sponsor_dire=:sponsor_dire,suprcdd_we_pe_no=:suprcdd_we_pe_no,arm=:arm,training_capacity=:training_capacity,status ='0',table_title=:table_title,eff_frm_date=:eff_frm_date,eff_to_date=:eff_to_date,doc_type=:doc_type,unit_category=:unit_category,modified_by=:modified_by,modified_on=:modified_on,uploaded_wepe=:uploaded_wepe where id=:id";
		Query query = session.createQuery(hql).setString("sponsor_dire", artAttValues.getSponsor_dire())
				.setString("suprcdd_we_pe_no", artAttValues.getSuprcdd_we_pe_no()).setString("arm", arm_val)
				.setInteger("training_capacity", artAttValues.getTraining_capacity())
				.setString("table_title", artAttValues.getTable_title())
				.setString("eff_frm_date", artAttValues.getEff_frm_date())
				.setString("eff_to_date", artAttValues.getEff_to_date())
				.setString("doc_type", artAttValues.getDoc_type())
				.setString("unit_category", artAttValues.getUnit_category())
				.setString("uploaded_wepe", artAttValues.getUploaded_wepe()).setString("modified_by", username)
				.setString("modified_on", modifydate).setInteger("id", artAttValues.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public List<Map<String, Object>> getSearch_inlieu_foot_transportReport(String status, String we_pe_no,
			String mct_no, String in_lieu_mct, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						qry += " and f.status = ?";
					}
				}
				if (we_pe_no != "") {
					qry += "and f.we_pe_no = ?";
				}

				if (mct_no != "") {
					qry += " and f.mct_no = ?";
				}

				if (in_lieu_mct != "") {
					qry += " and f.in_lieu_mct = ?";
				}

				q = "SELECT tblmct.mct_no,tblmct.std_nomclature as mct_name,tblinlieu.in_lieu_mct,tblinlieu.std_nomclature as inle_name,tblinlieu.actual_inlieu_auth ,tblinlieu.auth_amt, tblinlieu.total,tblinlieu.id,tblinlieu.we_pe_no,tblinlieu.status,tblinlieu.reject_letter_no,tblinlieu.reject_remarks,\r\n"
						+ "	tblinlieu.cr_by,\r\n"
						+ "	tblinlieu.cr_on,\r\n"
						+ "	tblinlieu.app_by,\r\n"
						+ "	tblinlieu.app_on,\r\n"
						+ "	tblinlieu.modi_by,\r\n"
						+ "	tblinlieu.modi_on "
						+ "	FROM ( (SELECT f.we_pe_no,f.in_lieu_mct, m.mct_nomen as std_nomclature,f.id,f.actual_inlieu_auth,tr.auth_amt,(tr.auth_amt -f.actual_inlieu_auth ) as total,f.status,f.reject_letter_no,f.reject_remarks,\r\n"
						+ "f.created_by as cr_by,f.created_on as cr_on,f.aprv_rejc_by as app_by,f.date_of_apprv_rejc as app_on,\r\n"
						+ "f.modified_by as modi_by,f.modified_on as modi_on  FROM tb_tms_mct_main_master m, cue_tb_miso_wepe_trans_footnotes f,cue_tb_miso_wepe_transport_det tr "
						+ "	WHERE m.mct_main_id = cast(f.in_lieu_mct as text) and f.type_of_footnote='0' and tr.we_pe_no=f.we_pe_no and tr.mct_no = f.mct_no  "
						+ qry
						+ ") tblinlieu JOIN (SELECT f.we_pe_no, f.mct_no, m.mct_nomen as std_nomclature,f.id FROM tb_tms_mct_main_master m, cue_tb_miso_wepe_trans_footnotes f WHERE "
						+ "	m.mct_main_id = cast(f.mct_no as text) and f.type_of_footnote='0' ) tblmct ON tblinlieu.id=tblmct.id ) order by tblinlieu.we_pe_no,tblmct.mct_no,tblinlieu.in_lieu_mct ";

				stmt = conn.prepareStatement(q);
				int j = 1;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}
				}
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;
				}
				if (mct_no != "") {
					stmt.setString(j, mct_no);
					j += 1;
				}
				if (in_lieu_mct != "") {
					stmt.setString(j, in_lieu_mct);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(8) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(8) + ");$('#rrr" + rs.getObject(8)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(8) + "' class='action_icons action_reject' "
							+ Reject + " title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(8) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(8) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					
					String f = "";
					if (status.equals("0")) {
						if (roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}
						if (roleType.equals("APP")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
						}
						if (roleType.equals("DEO")) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}

					} else if (status.equals("1")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += rejectButton;
							f += LogButton1;
						}
						if (roleType.equals("DEO")) {
							f += appButton1;
							f += LogButton1;
						}

					} else if (status.equals("2")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += appButton;
						}
						if (roleType.equals("DEO") || roleType.equals("ALL")) {

							f += deleteButton;
							f += updateButton;
						}

					}

					columns.put(metaData.getColumnLabel(8), f);

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
	}

	public List<Map<String, Object>> getSearch_inlieu_foot_transportReport1(String we_pe_no, String mct_no,
			String in_lieu_mct, String status, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (!status.equals("all")) {
					qry += " and (f.status = '0' or f.status = '2' or f.status = '1')  ";
				}

				if (we_pe_no != "") {
					qry += "and f.we_pe_no = ?";

				}

				if (mct_no != "") {
					qry += " and f.mct_no = ?";
				}

				if (in_lieu_mct != "") {
					qry += " and f.in_lieu_mct = ?";
				}

				q = "SELECT tblmct.mct_no,tblmct.std_nomclature as mct_name,tblinlieu.in_lieu_mct,tblinlieu.std_nomclature as inle_name,tblinlieu.actual_inlieu_auth ,tblinlieu.auth_amt, tblinlieu.total,tblinlieu.id,tblinlieu.we_pe_no,tblinlieu.status,tblinlieu.reject_letter_no,tblinlieu.reject_remarks "
						+ "	FROM ( (SELECT f.we_pe_no,f.in_lieu_mct, m.mct_nomen as std_nomclature,f.id,f.actual_inlieu_auth,tr.auth_amt,(tr.auth_amt -f.actual_inlieu_auth ) as total,f.status,f.reject_letter_no,f.reject_remarks FROM tb_tms_mct_main_master m, cue_tb_miso_wepe_trans_footnotes f,cue_tb_miso_wepe_transport_det tr "
						+ "	WHERE m.mct_main_id = cast(f.in_lieu_mct as text) and f.type_of_footnote='0' and tr.we_pe_no=f.we_pe_no and tr.mct_no = f.mct_no  "
						+ qry
						+ ") tblinlieu JOIN (SELECT f.we_pe_no, f.mct_no, m.mct_nomen as std_nomclature,f.id FROM tb_tms_mct_main_master m, cue_tb_miso_wepe_trans_footnotes f WHERE "
						+ "	m.mct_main_id = cast(f.mct_no as text) and f.type_of_footnote='0' ) tblmct ON tblinlieu.id=tblmct.id ) order by tblinlieu.we_pe_no,tblmct.mct_no,tblinlieu.in_lieu_mct ";

				stmt = conn.prepareStatement(q);
				int j = 1;
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;
				}

				if (mct_no != "") {
					stmt.setString(j, mct_no);
					j += 1;
				}

				if (in_lieu_mct != "") {
					stmt.setString(j, in_lieu_mct);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(8) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(8) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String f = "";
					if (rs.getObject(10).equals("1")) {
						f += appButton;
					} else {
						f += deleteButton;
						f += updateButton;
					}

					columns.put(metaData.getColumnLabel(8), f);

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
	}

	public List<Map<String, Object>> getAttributeFromCategorySearchWePe(String we_pe, String we_pe_no,
			String sponsor_dire, String arm, String doc_type, String status, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (!status.equals("all")) {
					qry += " and (c.status = '0' or c.status = '2' or c.status = '1')  ";
				}
				if (we_pe != "") {
					qry += " and c.we_pe = ?";
				}
				if (we_pe_no != "") {
					qry += " and c.we_pe_no = ?";
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					qry += " and c.sponsor_dire = ?";
				}
				if (arm != "" && !arm.equals("0")) {
					qry += " and c.arm = ?";
				}
				if (doc_type != "") {
					qry += " and c.doc_type = ?";
				}
				q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,sd.line_dte_name,c.doc_type,o.arm_desc,c.doc_type,\r\n"
						+ "c.eff_frm_date,c.eff_to_date,c.doc_path,c.status,c.reject_letter_no,c.reject_remarks,c.table_title,c.softdelete \r\n"
						+ "from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte sd where c.arm=o.arm_code and line_dte_sus=sponsor_dire\r\n"
						+ qry +  "order by c.we_pe,c.we_pe_no";
//				q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,c.sponsor_dire,c.doc_type,o.arm_desc,c.doc_type,c.eff_frm_date,c.eff_to_date,c.doc_path,c.status,c.reject_letter_no,c.reject_remarks,c.table_title,c.softdelete from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o where c.arm=o.arm_code  "
//						+ qry + " order by c.we_pe,c.we_pe_no ";
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (we_pe != "") {
					stmt.setString(j, we_pe);
					j += 1;
				}
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;

				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					stmt.setString(j, sponsor_dire);
					j += 1;
				}
				if (arm != "" && !arm.equals("0")) {
					stmt.setString(j, arm);
					j += 1;
				}
				if (doc_type != "") {
					stmt.setString(j, doc_type);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete12 = "onclick=\"  if (confirm('Please Download') ){getDownloadImageWePeUpload("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton2 = "<i " + Delete12 + ">Download</i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String f = "";
					if (rs.getObject(13).equals("1")) {
						f += appButton1;
					} else {
						f += deleteButton;
						f += updateButton;
					}

					columns.put(metaData.getColumnLabel(17), deleteButton2);
					columns.put(metaData.getColumnLabel(1), f);

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
	}

	public List<Map<String, Object>> getAttributeFromCategorySearchWePe1(String we_pe, String we_pe_no, String doc_type,
            String arm, String sponsor_dire, String status, String roleType, String from_date, String to_date,String roleAccess) {
        {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
                PreparedStatement stmt = null;
                String q = "";
                String qry = "";

                if (status != "" && status != null && status != "null") {
                    if (!status.equals("all")) {
                        qry += " and c.status =?";
                    }
                }
                if (we_pe != "") {
                    qry += " and c.we_pe =?";
                }
                if (we_pe_no != "") {
                    qry += " and c.we_pe_no =?";
                }
                if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
                    qry += " and c.sponsor_dire =?";
                }
                if (arm != "" && !arm.equals("0")) {
                    qry += " and c.arm =?";
                }
                if (doc_type != "") {
                    qry += " and c.doc_type =?";
                }
                if (from_date != "" && to_date != "") {
                    qry += " and c.created_on is not null and cast(concat(substr(c.created_on,7,4),'-',substr(c.created_on,4,2),'-',substr(c.created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
                }
                  q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,sd.line_dte_name,c.doc_type,o.arm_desc,\r\n"
                  		+ "c.doc_type,c.eff_frm_date,c.eff_to_date,c.status,c.reject_letter_no,c.reject_remarks,c.doc_path,c.table_title,"
                  		+ "c.created_by as cr_by,c.created_on as cr_on,c.approved_rejected_by as app_by,c.date_of_apprv_rejc as app_on,"
                  		+ "c.modified_by as modi_by,c.modified_on as modi_on\r\n"
                  		+ "from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte sd  where c.arm=o.arm_code and line_dte_sus=sponsor_dire \r\n"
                  		+ " and c.we_pe_no not in (select distinct suprcdd_we_pe_no from cue_tb_miso_wepe_upload_condition where suprcdd_we_pe_no is not null \r\n" + 
                  		"											  or trim(suprcdd_we_pe_no) ='') \r\n"
                  		+ qry + "order by c.we_pe,c.we_pe_no";
//                q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,c.sponsor_dire,c.doc_type,o.arm_desc,c.doc_type,c.eff_frm_date,c.eff_to_date,c.status,c.reject_letter_no,c.reject_remarks,c.doc_path,c.table_title"
//                        + " from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o where c.arm=o.arm_code   "
//                        + qry + " order by c.we_pe,c.we_pe_no ";
                stmt = conn.prepareStatement(q);
                int j = 1;
                if (status != "" && status != null && status != "null") {
                    if (!status.equals("all")) {
                        stmt.setString(j, status);
                        j += 1;
                    }
                }
                if(we_pe != "") {
                    stmt.setString(j, we_pe);
                    j += 1;
                }
                if(we_pe_no != "") {
                    stmt.setString(j, we_pe_no);
                    j += 1;
                }
                if(sponsor_dire != "" && !sponsor_dire.equals("0")) {
                    stmt.setString(j, sponsor_dire);
                    j += 1;
                }
                if(arm != "" && !arm.equals("0")) {
                    stmt.setString(j, arm);
                    j += 1;
                }
                if(doc_type != "") {
                    stmt.setString(j, doc_type);
                    j += 1;
                }
                if(from_date != "" && to_date != ""){
                    stmt.setString(j, from_date);
                    j += 1;
                    stmt.setString(j, to_date);
                    j += 1;
                }
                
                
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();

                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
//                	String a="";
                    Map<String, Object> columns = new LinkedHashMap<String, Object>();

                    for (int i = 1; i <= columnCount; i++) {
                        columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//
//                        if(metaData.getColumnLabel(i).equals("cr_by")) {
//							a+="Uploaded By :" +rs.getObject(i);
//						}
//						if(metaData.getColumnLabel(i).equals("cr_on")) {
//							a+= "\nUploaded On :" +rs.getObject(i);
//						}
						
						
						
                    }
                    String LogButton = cueContoller.Get_button_info(metaData,rs);
                    String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
                    
//                    		"<i class='fa fa-question-circle' " + " title=' " + a + " '></i>";
                    String Approved = "onclick=\"   if (confirm('Are you sure you want to approve?') ){Approved("
                            + rs.getObject(1) + ")}else{ return false;}\"";
                    String appButton = "<i class='action_icons action_approve' " + Approved
                            + " title='Approve Data'></i>";

                    String Reject = "onclick=\"   if (confirm('Are you sure you want to reject?') ){Reject("
                            + rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
                            + "').attr('data-target','#rejectModal')}else{ return false;}\"";
                    String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
                            + Reject + " title='Reject Data' data-toggle='modal'   ></i>";

                    String Delete1 = "onclick=\"   if (confirm('Are you sure you want to delete?') ){Delete1("
                            + rs.getObject(1) + ")}else{ return false;}\"";
                    String deleteButton = "<i class='action_icons action_delete' " + Delete1
                            + " title='Delete Data'></i>";

                    String Update = "onclick=\"   if (confirm('Are you sure you want to update?') ){Update("
                            + rs.getObject(1) + ")}else{ return false;}\"";
                    String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

                    String appButton1 = "<i class='action_icons action_approved'   title='Approve Data'></i>";
                    if (rs.getString("doc_path") != null) {
                        String Delete12 = "onclick=\"   if (confirm('Please Download') ){getDownloadImage("
                                + rs.getString("id") + ")}else{ return false;}\"";
                        String deleteButton2 = "<i " + Delete12 + ">Download</i>";

                        columns.put(metaData.getColumnLabel(columnCount - 1), deleteButton2);
                    }

                    String f = "";
                   
                    if (status.equals("0")) {
                        if (roleType.equals("ALL")) {
                            f += appButton;
                            f += rejectButton;
                            f += deleteButton;
                            f += updateButton;
                            f += LogButton;
                        }
                        if (roleType.equals("APP")) {
                            f += appButton;
                            f += rejectButton;
                            f += LogButton;
                        }
                        if (roleType.equals("DEO")// || roleAccess.equals("Line_dte")
                        		) {
                            f += deleteButton;
                            f += updateButton;
                            f += LogButton;
                        }
                        
                        if (roleType.equals("VIEW")) {
                            f += appButton;
                            f += rejectButton;
                            f += deleteButton;
                            f += updateButton;
                        }

                    } else if (status.equals("1")) {
                        if (roleType.equals("APP") || roleType.equals("ALL")) {
                            f += rejectButton;
                            f += LogButton1;
                        }
                        if (roleType.equals("DEO")) {
                            f += appButton1;
                            f += LogButton1;
                        }

                    } else if (status.equals("2")) {
                        if (roleType.equals("APP") || roleType.equals("ALL")) {
                            f += appButton;
                        }
                        if (roleType.equals("DEO") || roleType.equals("ALL")//|| roleAccess.equals("Line_dte")
                        		) {

                            f += deleteButton;
                            f += updateButton;
                        }
                       
                    }


                    columns.put(metaData.getColumnLabel(1), f);

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
    }


	public List<Map<String, Object>> getWEPElinkwithWETPET(String we_pe, String we_pe_no, String roleType,
			String wet_link_status) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (!wet_link_status.equals("all")) {
					qry += " and (wet_link_status = '0' or wet_link_status = '2' or wet_link_status = '1')  ";
				}

				if (we_pe != "") {
					qry += " and  we_pe = ?";
				}

				if (we_pe_no != "") {
					qry += " and we_pe_no = ?";
				}

				qry = qry.substring(4, qry.length());

				q = "select distinct id,we_pe,we_pe_no,table_title,wet_pet_no,wet_link_status,unit_visible from cue_tb_miso_wepe_upload_condition where "
						+ qry + " order by we_pe_no";
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (we_pe != "") {
					stmt.setString(j, we_pe);
					j += 1;
				}
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String f = "";

					f += deleteButton;
					f += updateButton;
					columns.put(metaData.getColumnLabel(1), f);
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
	}

	public List<Map<String, Object>> getWEPElinkwithWETPET1(String we_pe, String we_pe_no, String wet_link_status,
			String roleType,String roleAccess,String roleSusNo) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (wet_link_status != "" && wet_link_status != null && wet_link_status != "null") {
					if (!wet_link_status.equals("all")) {
						qry += " and wet_link_status = ?";
					}
				}
				if (we_pe != "") {
					qry += " and  we_pe = ?";
				}

				if (we_pe_no != "") {
					qry += " and we_pe_no = ?";
				}

				qry = qry.substring(4, qry.length());
				
				
			
				if(roleAccess.equals("Line_dte")) {

					//qry += " and sponsor_dire = ?";
					
					if (qry != "") {
						qry += " and sponsor_dire = ?";
					}
					else {
						qry +=" where sponsor_dire = ?" ;
					}

				}

				q = "select distinct id,we_pe,we_pe_no,table_title,wet_pet_no,wet_link_status,unit_visible  ,\r\n"
						+ "created_by as cr_by,created_on as cr_on,approved_rejected_by as app_by,date_of_apprv_rejc as app_on,\r\n"
						+ "	modified_by as modi_by,modified_on as modi_on "
						+ "from cue_tb_miso_wepe_upload_condition where "
						+ qry + " order by we_pe_no";
				stmt = conn.prepareStatement(q);

				int j = 1;
				if (wet_link_status != "" && wet_link_status != null && wet_link_status != "null") {
					if (!wet_link_status.equals("all")) {
						stmt.setString(j, wet_link_status);
						j += 1;
					}
				}
				if (we_pe != "") {
					stmt.setString(j, we_pe);
					j += 1;
				}

				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
				}

				
				if(roleAccess.equals("Line_dte")) {
					stmt.setString(j, roleSusNo);

				}
				
				
				System.out.println("search linked uploaded we/pe to wet/pet:-"  + stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject1("
							+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
							+ Reject + " title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					String f = "";
					if (wet_link_status.equals("0")) {
						if (roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}
						if (roleType.equals("APP")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
						}
						if (roleType.equals("DEO")) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}

					} else if (wet_link_status.equals("1")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += rejectButton;
							f += LogButton1;
						}
						if (roleType.equals("DEO")) {
							f += appButton1;
							f += LogButton1;
						}

					} else if (wet_link_status.equals("2")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += appButton;
						}
						if (roleType.equals("DEO") || roleType.equals("ALL")) {

							f += deleteButton;
							f += updateButton;
						}

					}

					columns.put(metaData.getColumnLabel(1), f);

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
	}
	

	public List<Map<String, Object>> getAttributeFromCategorySearchWetPet(String wet_pet, String wet_pet_no,String status, String roleType,String from_date,String to_date, String roleAccess,String roleSusNo) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						qry += " and d.status = ?";
					}
				}
				if (wet_pet_no != "") {
					qry += " and d.wet_pet_no = ?";
				}
				if (from_date != "" && to_date != "") {
					qry += " and d.created_on is not null and cast(concat(substr(created_on,7,4),'-',substr(created_on,4,2),'-',substr(created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
				}
				
				
				if(roleAccess.equals("Line_dte")) {

					qry += " and c.sponsor_dire = ?";

				}
				
				
				if (qry != "") {
					qry = " where " + qry.substring(4, qry.length());
				}
				
				

				q = " select distinct d.wet_pet_no,d.doc_path,d.remark,d.status,d.id,d.reject_letter_no,d.reject_remarks,d.vetted_miso,d.created_by as cr_by,d.created_on as cr_on from cue_tb_miso_wet_pet_amdt d "
				+ "inner join cue_tb_miso_mms_wet_mast_upload c on c.wet_pet_no=d.wet_pet_no"+ qry + " order by wet_pet_no";
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}
				}
				if (wet_pet_no != ""){
					stmt.setString(j, wet_pet_no);
					j += 1;
				}
				if(from_date != "" && to_date != ""){
					stmt.setString(j, from_date);
					j += 1;
					stmt.setString(j, to_date);
					j += 1;
				}
				
				if(roleAccess.equals("Line_dte")) {
					stmt.setString(j, roleSusNo);

				}
				
				
				System.out.println("heloo wet pet amdt" + stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String Delete12 = "onclick=\"  if (confirm('Please Download') ){getDownloadImageWetPetAmdt("+ rs.getObject(5) + ")}else{ return false;}\"";
					String deleteButton2 = "<a " + Delete12 + ">Download</a>";
					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+ rs.getObject(5) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved+ " title='Approve Data'></i>";
					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+ rs.getObject(5) + ");$('#rrr" + rs.getObject(5)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(5) + "' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(5)+")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' "+Delete1+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					String f = "";
					if (status.equals("0")) {
						if (roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}
						if (roleType.equals("APP")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
						}
						if (roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}

						if (roleType.equals("VIEW")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
						}
						
					} else if (status.equals("1")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += rejectButton;
							f += LogButton1;
						}
						if (roleType.equals("DEO")) {
							f += appButton1;
							f += LogButton1;
						}

					} else if (status.equals("2")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += appButton;
						}
						if (roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")) {

							f += deleteButton;
							f += updateButton;
						}

					}
					columns.put(metaData.getColumnLabel(8), deleteButton2);
					columns.put(metaData.getColumnLabel(5), f);
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
	}


	@Override
	public boolean check_data(String superno, String tablename) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		if(superno!="") {
		Query q= session.createQuery(
				"select count(*) from "+ tablename+ " where we_pe_no =:superno and status='0' ");

		q.setString("superno", superno);
		
		
		Long count1 = (Long)q.uniqueResult();
	
		if(count1 == 0) {
		return true;
		}
		
	}else {
		return true;
	}
		
		
		
		return false;

}
	 public ArrayList<ArrayList<String>> getWepeData(String sus_no) {
	    	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try{	  
	    		conn = dataSource.getConnection();
	    		String sql= "select distinct con.eff_frm_date,con.eff_to_date,untdtl.ct_part_i_ii,t.label,con.training_capacity,\r\n"
	    				+ "con.unit_category,susl.wepe_pers_no,con.sponsor_dire,con.arm,con.table_title\r\n"
	    				+ " from tb_Miso_Orbat_Unt_Dtl untdtl\r\n"
	    				+ "left join cue_tb_wepe_link_sus_perstransweapon susl on susl.sus_no=untdtl.sus_no\r\n"
	    				+ "left join CUE_TB_MISO_WEPECONDITIONS con on con.we_pe_no=susl.wepe_pers_no\r\n"
	    				+ "inner join t_domain_value t on t.codevalue=untdtl.type_force and domainid='TYPEOFFORCE'\r\n"
	    				+ "where status_sus_no='Active' and untdtl.sus_no= ?";
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setString(1, sus_no);
				
				ResultSet rs1 = stmt.executeQuery();     
	    		for(int i =0 ; rs1.next() ;i++) {
	    			ArrayList<String> list = new ArrayList<String>();
	    			list.add(rs1.getString("eff_frm_date"));
	    			list.add(rs1.getString("eff_to_date"));
	    			list.add(rs1.getString("ct_part_i_ii"));
	    			list.add(rs1.getString("label"));
	    			list.add(rs1.getString("training_capacity"));
	    			list.add(rs1.getString("unit_category"));
	    			list.add(rs1.getString("wepe_pers_no"));
	    			list.add(rs1.getString("sponsor_dire"));
	    			list.add(rs1.getString("arm"));
	    			list.add(rs1.getString("table_title"));
	    			alist.add(list);
	    		}
	    		rs1.close();
	    		stmt.close();
	    	}catch (SQLException e) {
	    		e.printStackTrace();
			}
			return alist;
	    }
	 
	 public String updatePersDetails(String we_pe_no, String type, String username,String wepe) {
		    Session session = null;
		    Transaction tx = null;
		    String message = null;
		    
		    try {
		        session = HibernateUtilNA.getSessionFactory().openSession();
		        tx = session.beginTransaction();
		        
		        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		        int totalUpdatedRows = 0;
		        
		        // Use equals() for String comparison instead of ==
		        if ("1".equals(type)) {
		            String hqlUpdatem = "update CUE_TB_MISO_WEPE_PERS_DET c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		        int    updatedRows = session.createQuery(hqlUpdatem)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		        totalUpdatedRows += updatedRows;
		            
		            String hqlUpdatempmd = "update CUE_TB_MISO_WEPE_PERS_MDFS c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatempmd)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		            String hqlUpdatempfn = "update CUE_TB_MISO_WEPE_PERS_FOOTNOTES c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatempfn)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		            
		        } else if ("2".equals(type)) {
		            String hqlUpdate1 = "update CUE_TB_MISO_WEPE_TRANSPORT_DET c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		           int updatedRows = session.createQuery(hqlUpdate1)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		           totalUpdatedRows += updatedRows;
		           
		            String hqlUpdatetmd = "update CUE_TB_MISO_WEPE_TRANSPORT_MDFS c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatetmd)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		            
		            String hqlUpdatetfn = "update CUE_TB_MISO_WEPE_TRANS_FOOTNOTES c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatetfn)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		            
		        } else if ("3".equals(type)) {
		            String hqlUpdate2 = "update CUE_TB_MISO_WEPE_WEAPON_DET c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		           int updatedRows = session.createQuery(hqlUpdate2)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		           totalUpdatedRows += updatedRows;
		            
		            String hqlUpdatewmd = "update CUE_TB_MISO_WEPE_WEAPONS_MDFS c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatewmd)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		            
		            String hqlUpdatewfn = "update CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES c set c.vettedby_dte1 = :vettedby_dte1, c.vettedby_dte2 = :vettedby_dte2 where c.we_pe_no = :we_pe_no and c.status = :status";
		            updatedRows = session.createQuery(hqlUpdatewfn	)
		                               .setString("vettedby_dte1", username)
		                               .setString("vettedby_dte2", currentDate)
		                               .setString("we_pe_no", we_pe_no)
		                               .setString("status", "1")
		                               .executeUpdate();
		            totalUpdatedRows += updatedRows;
		        }
		        updateOrInsertVettingDtl( we_pe_no, username, type,wepe);
		        if (totalUpdatedRows > 0) {
		            message = "Your Data is Vetted Successfully";
		            tx.commit();
		        } else {
		            message = "No records were Found";
		            tx.rollback();
		        }
		        
		    } catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();
		        }
		        message = "Error updating data: " + e.getMessage();
		    } finally {
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }
		    
		    return message;
		}
	 
	
	 public boolean iswe_pe_exits(String we_pe_no, String type) {
	     String hql = "SELECT COUNT(*) FROM CUE_TB_MISO_VETTING_DET c WHERE c.we_pe_no = :we_pe_no and type =:type";
	     Session session = HibernateUtil.getSessionFactory().openSession();
	     Transaction tx = null; // Initialize tx outside try block
	     Boolean exists = false; // Default to false for safety

	     try {
	         tx = session.beginTransaction();
	         Query query = session.createQuery(hql);
	         query.setParameter("we_pe_no", we_pe_no);
	         query.setParameter("type", type);

	         Long count = (Long) query.uniqueResult();
	          exists = count != null && count > 0;

	          tx.commit();

	     } catch (Exception e) {
	         if(tx != null) {
	              tx.rollback();
	         }
	        exists = false; 
	     } finally {
	       if (session != null){
	         session.close();
	       }
	     }
	     return exists;
	 }

	 public void updateOrInsertVettingDtl(String we_pe_no, String username, String type, String wepe) {

	     Session session = HibernateUtil.getSessionFactory().openSession();
	     Transaction tx = null;

	    try{
	         tx = session.beginTransaction(); 

	        String currentDate;
	        currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	          if (iswe_pe_exits(we_pe_no, type)) {
	             String hqlUpdate = "UPDATE CUE_TB_MISO_VETTING_DET c SET c.vetted_by = :vetted_by, c.vetted_on = :vetted_on, c.we_pe = :we_pe WHERE c.we_pe_no = :we_pe_no and c.type = :type";
	             Query query = session.createQuery(hqlUpdate);

	             query.setParameter("vetted_by", username);
	             query.setParameter("vetted_on", currentDate);
	             query.setParameter("we_pe", wepe);
	              query.setParameter("type", type);
	             query.setParameter("we_pe_no", we_pe_no);
	             int rowsUpdated = query.executeUpdate();

	         } else {
	             CUE_TB_MISO_VETTING_DET vettingDtl = new CUE_TB_MISO_VETTING_DET(); // Assuming your entity class name
	             vettingDtl.setVetted_by(username);
	             vettingDtl.setVetted_on(currentDate);
	             vettingDtl.setWe_pe_no(we_pe_no);
	             vettingDtl.setWe_pe(wepe);
	             vettingDtl.setType(type);

	             session.save(vettingDtl);
	         }
	        tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	 }
		


	 public void updateVettingDtl(String we_pe_no, String type) {
	     // Corrected the equals(true)
	    if (iswe_pe_exits(we_pe_no, type)) {
	        Session ses1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction t2 = null;
	        try {
	            t2 = ses1.beginTransaction(); 

	           
	            String hqlUpdate = "UPDATE CUE_TB_MISO_VETTING_DET c SET c.vetted_by = :vetted_by, c.vetted_on = :vetted_on  WHERE c.we_pe_no = :we_pe_no and c.type= :type  ";

	             Query query = ses1.createQuery(hqlUpdate);
	            query.setParameter("vetted_by", ""); 
	            query.setParameter("vetted_on", ""); 
	            query.setParameter("type", type);
	            query.setParameter("we_pe_no", we_pe_no);

	             int rowsUpdated = query.executeUpdate();
	            if (rowsUpdated > 0) {
	                 System.out.println("Rows updated: " + rowsUpdated); 
	            } else {
	                  System.out.println("No Rows updated.");
	            }
	             t2.commit(); //Commit here

	         } catch (Exception e) {
	              if(t2 != null){
	                  t2.rollback();
	              }
	            System.err.println("Error updating vetting details: " + e.getMessage());
	            // Handle the exception appropriately (log, rethrow, etc.).
	        }
	         finally{
	            if(ses1 != null){
	                 ses1.close();
	            }
	         }

	    }
	}
	
}
