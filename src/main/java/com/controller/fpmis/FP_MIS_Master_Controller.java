package com.controller.fpmis;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.fpmis.FP_MIS_MasterDAO;
import com.dao.fpmis.FP_MIS_ReportsDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Role;
import com.models.fpmis.FP_MIS_Master_Model;
import com.models.fpmis.fp_domain_values_Model;
import com.models.fpmis.fp_tb_admin_control_model;
import com.models.fpmis.fp_tb_cda_detl20_model;
import com.models.fpmis.fp_tb_norbat_dtl_model;
import com.models.fpmis.fp_tb_pref_head_model;
import com.models.fpmis.fp_tb_pref_unit_model;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FP_MIS_Master_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private FP_MIS_MasterDAO fpmisao;

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	@Autowired
	private FP_MIS_ReportsDAO fp_rptDao;

	FP_MIS_Common_Controller mcommon = new FP_MIS_Common_Controller();

	@RequestMapping(value = "/admin/fp_head_master", method = RequestMethod.GET)
	public ModelAndView fp_head_master(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_head_master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getMajorHeadCode", mcommon.getMajorHeadCode("", session));
		Mmap.put("getMinorHeadCode", mcommon.getMinorHeadCode("", session));
		Mmap.put("getSubHeadCode", mcommon.getSubHeadCode("", session));
		/*Mmap.put("getSubHead1Code", mcommon.getSubHead1Code("", session));
		Mmap.put("getSubHead2Code", mcommon.getSubHead2Code("", session));*/
		ArrayList<ArrayList<String>> list = fpmisao.getSearchHeadMaster("", "", "", "", "");
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_head_masterTiles");
	}

	@RequestMapping(value = "/createHeadAction", method = RequestMethod.POST)
	public ModelAndView createHeadAction(@ModelAttribute("createHeadCMD") FP_MIS_Master_Model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_head_master", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (request.getParameter("head_code").equals("") || request.getParameter("head_code").equals(null)) {
			model.put("msg", "Please Enter Head Code.");
			return new ModelAndView("redirect:fp_head_master");
		}

		if (request.getParameter("head_desc").equals("") || request.getParameter("head_desc").equals(null)) {
			model.put("msg", "Please Enter Head Description.");
			return new ModelAndView("redirect:fp_head_master");
		}

		if (request.getParameter("major_head").equals("-1")) {
			model.put("msg", "Please Select the Major Head.");
			return new ModelAndView("redirect:fp_head_master");
		}

		String major_head = request.getParameter("major_head");
		String head_desc = request.getParameter("head_desc");
		String head_code = request.getParameter("head_code");
		String minor_head = request.getParameter("minor_head");
		String sub_head = request.getParameter("sub_head");
		
		/*String[] s_head = sub_head.split(":");
		sub_head = "";
		if(s_head.length == 3) {
			sub_head = s_head[2];
		}*/
		
		String status = request.getParameter("status");
		String data1 = major_head + "_" + head_desc + "_" + head_code + "_" + minor_head + "_" + sub_head + "_"
				+ status;
		String data2 = "_";
		String retmsg = mcommon.nChkData(data1, data2);

		if (retmsg.length() > 0) {
			model.put("msg", retmsg);
			return new ModelAndView("redirect:fp_head_master");
		} else {

			String tr_head = "";
			String tr_level = "";

			int id = rm.getId() > 0 ? rm.getId() : 0;

			Date date = new Date();
			String username = session.getAttribute("username").toString();

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {

				Query q0 = sessionHQL.createQuery(
						"select count(id) from FP_MIS_Master_Model where head_code=:head_code and id !=:id");
				q0.setParameter("head_code", rm.getMajor_head());

				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					rm.setData_cr_by(username);
					rm.setData_cr_date(date);

					if (c == 0) {
						fpmisao.RegdDataTransfer(tr_head, tr_level, major_head, minor_head, sub_head, head_desc, status,
								head_code, username);
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");

					}
				} else {
					rm.setData_upd_by(username);
					rm.setData_upd_date(date);

					if (c == 0) {
						msg = fpmisao.updatefpheadcode(rm);
						model.put("msg", msg);

					} else {
						model.put("msg", "Data already Exist.");

					}
				}
				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					model.put("msg", "Error - unable to perform action");

				} catch (RuntimeException rbe) {
					model.put("msg", "Error - unable to perform action");

				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		return new ModelAndView("redirect:fp_head_master");
	}

	@RequestMapping(value = "/getSearchHeadMaster", method = RequestMethod.POST)
	public ModelAndView getSearchHeadMaster(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "head_code1", required = false) String head_code1,
			@RequestParam(value = "head_desc1", required = false) String head_desc1,
			@RequestParam(value = "major_head1", required = false) String major_head1,
			@RequestParam(value = "minor_head1", required = false) String minor_head1,
			@RequestParam(value = "sub_head11", required = false) String sub_head11, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("fp_head_master", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("head_code1", head_code1);
		Mmap.put("head_desc1", head_desc1);
		Mmap.put("major_head1", major_head1);
		Mmap.put("minor_head1", minor_head1);
		Mmap.put("sub_head11", sub_head11);
		ArrayList<ArrayList<String>> list = fpmisao.getSearchHeadMaster(head_code1, head_desc1, major_head1,
				minor_head1, sub_head11);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());

		Mmap.put("getMajorHeadCode", mcommon.getMajorHeadCode("", session));
		Mmap.put("getMinorHeadCode", mcommon.getMinorHeadCode("", session));
		Mmap.put("getSubHeadCode", mcommon.getSubHeadCode("", session));
		return new ModelAndView("fp_head_masterTiles");
	}

	@RequestMapping(value = "/deleteHeadMasterURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteHeadMasterURL(@ModelAttribute("id1") int id1, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();

		Boolean val = roledao.ScreenRedirect("fp_head_master", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		try {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = "delete from fp.fp_tb_head_mstr where id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id1).executeUpdate();

			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:fp_head_master");
	}

	@RequestMapping(value = "/admin/fp_be_upload", method = RequestMethod.GET)
	public ModelAndView fp_be_upload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("fp_be_upload", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, "CFY"));
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		Mmap.put("msg", msg);
		Mmap.put("n_sel",":" + fp_trDao.nGetAdmFinYear("CFY"));
		return new ModelAndView("fp_be_uploadTiles");
	}

	@RequestMapping(value = "/admin/fp_head_pref", method = RequestMethod.GET)
	public ModelAndView fp_head_pref(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String username = (String) session.getAttribute("username");
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_head_pref", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("user_id", username);
		String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);
		String nPara = nUnty + "_" + rolefmn + "_" + roleSusNo;
		Mmap.put("n_unt", fp_trDao.getunitBuglist("", session, "HQ_" + nPara));
		Mmap.put("msg", msg);
		String nPvnYr=fp_trDao.nGetAdmFinYear("PVN");				
		if (!fp_trDao.nGetAdmFinYear("PVN").equalsIgnoreCase("XXXX")) {
			int nPvnYr1=Integer.parseInt(nPvnYr)+1;
			Mmap.put("n_noed", "N");
			Mmap.put("n_noed_msg", "Provisional BE for next FY ("+nPvnYr +" - "+nPvnYr1+") is in active state. Changes in Code Head List not allowed.");			
		} else {
			Mmap.put("n_noed", "Y");
			Mmap.put("n_noed_msg", "");
		}
		
		return new ModelAndView("fp_head_prefTiles");
	}

	@RequestMapping(value = "/fp_head_assignAction", method = RequestMethod.POST)
	public ModelAndView fp_head_assignAction(@ModelAttribute("fp_head_assignCMD") fp_tb_pref_head_model rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_head_pref", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String username = session.getAttribute("username").toString();
		String sus = request.getParameter("unit_tar");
		String user = request.getParameter("username");
		String f_sus[] = sus.split("__");
		String sus_no = request.getParameter("sus_no");
		if (user.equals("")) {
			model.put("msg", "Please Select the User Name");
			return new ModelAndView("redirect:fp_head_pref");
		}

		if (sus.equals("")) {
			model.put("msg", "Please Select the Head to Assign");
			return new ModelAndView("redirect:fp_head_pref");
		}
		String data1 = sus_no + "_" + sus_no;
		String data2 = "_";
		System.out.println(data1+","+data2);
		String retmsg = mcommon.nChkData(data1, data2);
		System.out.println(data1+","+data2+","+retmsg);

		if (retmsg.length() > 0) {
			model.put("msg", retmsg);
			return new ModelAndView("redirect:fp_head_pref");
		}
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Delete start");
		Query hqlUpdate = session1.createSQLQuery("delete from fp.fp_tb_pref_head where sus_no=:sus_no and psus_no=:roleSusNo");
		//Query hqlUpdate = session1.createSQLQuery("delete from fp.fp_tb_pref_head where psus_no=:sus_no");
		Transaction tx = session1.beginTransaction();		
		hqlUpdate.setParameter("sus_no", sus_no);
		hqlUpdate.setParameter("roleSusNo", roleSusNo);	
		hqlUpdate.executeUpdate();
		System.out.println("Deleted data in fp.fp_tb_pref_head-"+sus_no+"-"+roleSusNo);
		try {
			for (int i = 0; i < f_sus.length; i++) {      
				data1 = sus_no + "_" + user + "_" + f_sus[i];
				retmsg = mcommon.nChkData(data1, data2);
				if (retmsg.length() > 0) {
					throw new Exception(retmsg);				
				}
				System.out.println("Processing-"+sus_no+"-"+roleSusNo+"-"+f_sus[i]);
				rs.setUsr_id(user);
				rs.setSus_no(sus_no);
				rs.setChl_head_code(f_sus[i]);
				rs.setPsus_no(roleSusNo);

				session1.save(rs);
				session1.flush();
				session1.clear();
			}
			System.out.println("Added New Selection in fp.fp_tb_pref_head.");			
			//fp_trDao.refeshMViews("");
			msg = "Head Preference Assigned Successfully";
			
		} catch (Exception e) {
			System.out.println("e2-"+e);
			msg = "Error - unable to perform action";
			tx.rollback();
		} finally {
			session1.close();
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:fp_head_pref");
	}

	@RequestMapping(value = "/admin/fp_unit_pref", method = RequestMethod.GET)
	public ModelAndView fp_unit_pref(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_unit_pref", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String nUnty = fp_trDao.getUnitType("", session, "FMN_" + rolefmn);

		String nPara = nUnty + "_" + rolefmn + "_" + roleSusNo;
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("n_unt", fp_trDao.getunitBuglist("", session, "SUS_5_XXXXXXXXXX_" + roleSusNo));
		Mmap.put("msg", msg);
		String nPvnYr=fp_trDao.nGetAdmFinYear("PVN");
		if (!fp_trDao.nGetAdmFinYear("PVN").equalsIgnoreCase("XXXX")) {
			int nPvnYr1=Integer.parseInt(nPvnYr)+1;
			Mmap.put("n_noed", "N");
			Mmap.put("n_noed_msg", "Provisional BE for next FY ("+nPvnYr +" - "+nPvnYr1+") is in active state. Changes in Budget Holders List not allowed.");			
		} else {
			Mmap.put("n_noed", "Y");
			Mmap.put("n_noed_msg", "");
		}		
		return new ModelAndView("fp_unit_prefTiles");
	}

	@RequestMapping(value = "/fp_unit_assignAction", method = RequestMethod.POST)
	public ModelAndView fp_unit_assignAction(@ModelAttribute("fp_unit_assignCMD") fp_tb_pref_unit_model rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_head_pref", roleid);
		String sus_no = session.getAttribute("roleSusNo").toString();
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String sus = request.getParameter("unit_tar");
		String user = request.getParameter("username");
		String f_sus[] = sus.split("__");
		if (user.equals("")) {
			model.put("msg", "Please Select the User Name");
			return new ModelAndView("redirect:fp_unit_pref");
		}

		if (sus.equals("")) {
			model.put("msg", "Please Select the Unit to Assign");
			return new ModelAndView("redirect:fp_unit_pref");
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query hqlUpdate = null;

		try {
			String data2 = "_";
			String data1 = "";
			String retmsg = "";

			hqlUpdate = session1.createSQLQuery("select distinct chl_sus_no from fp.fp_tb_pref_unit where sus_no=:sus_no");
			hqlUpdate.setParameter("sus_no", sus_no);

			@SuppressWarnings("unchecked")
			List<String> chl_list = (List<String>) hqlUpdate.list();
			chl_list.removeAll(Arrays.asList(f_sus));

			hqlUpdate = null;
			hqlUpdate = session1.createSQLQuery("delete from fp.fp_tb_pref_unit where sus_no=:sus_no");
			hqlUpdate.setParameter("sus_no", sus_no);
			hqlUpdate.executeUpdate();

			for (String s : chl_list) {
				hqlUpdate = session1.createSQLQuery("delete from fp.fp_tb_pref_head where sus_no=:sus_no and psus_no=:psus_no");
				hqlUpdate.setParameter("sus_no", s);
				hqlUpdate.setParameter("psus_no", sus_no);
				hqlUpdate.executeUpdate();
			}

			for (int i = 0; i < f_sus.length; i++) {

				data1 = sus_no + "_" + user + "_" + f_sus[i];
				retmsg = mcommon.nChkData(data1, data2);
				if (retmsg.length() > 0) {
					throw new Exception(retmsg);
				}
				rs.setUsr_id(user);
				rs.setSus_no(sus_no);
				rs.setChl_sus_no(f_sus[i]);

				session1.save(rs);
				session1.flush();
				session1.clear();
			}
			msg = "Unit Preference Assigned Successfully";

		} catch (SQLException e) {
			msg = "Error - unable to perform action";
			e.printStackTrace();
			tx.rollback();
		} catch (Exception e) {
			msg = "Error - unable to perform action";
			e.printStackTrace();
			tx.rollback();
		} finally {
			session1.close();
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:fp_unit_pref");
	}
	
	@RequestMapping(value = "/admin/fp_norbat_dtls", method = RequestMethod.GET)
	public ModelAndView fp_norbat_dtls(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_norbat_dtl", roleid);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("branchs", fp_trDao.getAllBranchList());
		Mmap.put("msg", msg);
		return new ModelAndView("fp_norbat_dtlsTiles");
	}

	@RequestMapping(value = "/createNewBudgetHolder", method = RequestMethod.POST)
	public ModelAndView createNewBudgetHolder(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_norbat_dtl", roleid);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String unit_name = request.getParameter("unit_name");
		String sus_no = request.getParameter("sus_no");
		String psus_no = request.getParameter("psus_no");
		String hq_type = request.getParameter("hq_type");
		String form_code_control = request.getParameter("form_code_control");
		String status_sus_no = request.getParameter("status_sus_no");
		String remarks = request.getParameter("remarks");
		String entity_type = request.getParameter("entity_type");

		String data1 = unit_name + "_" + sus_no + "_" + hq_type + "_" + form_code_control + "_" + status_sus_no + "_"
				+ remarks + "_" + entity_type;
		String data2 = "_";
		String retmsg = mcommon.nChkData(data1, data2);

		if (retmsg.length() > 0) {
			model.put("msg", retmsg);
			return new ModelAndView("redirect:fp_norbat_dtls");
		} else {

			try {

				if (hq_type.length() == 0 || hq_type.equalsIgnoreCase(null)) {
					model.put("msg", "Please Select Hq Type.");
					return new ModelAndView("redirect:fp_norbat_dtls");
				}

				if (form_code_control.length() == 0 || form_code_control.equalsIgnoreCase(null)
						|| form_code_control.equalsIgnoreCase("-1")) {
					model.put("msg", "Please Select Under Formation.");
					return new ModelAndView("redirect:fp_norbat_dtls");
				}

				if (unit_name.length() == 0 || unit_name.equalsIgnoreCase(null)) {
					model.put("msg", "Please Enter the Unit Name.");
					return new ModelAndView("redirect:fp_norbat_dtls");
				}

				if (sus_no.length() == 0 || sus_no.equalsIgnoreCase(null)) {
					model.put("msg", "Please Generate SUS No.");
					return new ModelAndView("redirect:fp_norbat_dtls");
				}

				if (status_sus_no.length() == 0 || status_sus_no.equalsIgnoreCase(null)) {
					model.put("msg", "Please Select Status");
					return new ModelAndView("redirect:fp_norbat_dtls");
				}

				if (entity_type.equalsIgnoreCase("2")) {
					psus_no = roleSusNo;
				}
				String username = session.getAttribute("username").toString();

				model.put("msg", fpmisao.createBudgetHolder(sus_no, psus_no, form_code_control, unit_name,
						status_sus_no, remarks, hq_type, username));

			} catch (Exception e) {
				model.put("msg", "Error - unable to perform action");
			}
		}
		return new ModelAndView("redirect:fp_norbat_dtls");
	}

	@RequestMapping(value = "/admin/fp_norbat_dtl", method = RequestMethod.GET)
	public ModelAndView fp_norbat_dtl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_norbat_dtl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("fp_norbat_dtlTiles");
	}

	@RequestMapping(value = "/createUnitProfileActionFP", method = RequestMethod.POST)
	public ModelAndView createUnitProfileActionFP(@ModelAttribute("createUnitProfileFPCMD") fp_tb_norbat_dtl_model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_norbat_dtl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String unit_name = request.getParameter("unit_name");
		String sus_no = request.getParameter("sus_no");
		String hq_type = request.getParameter("hq_type");
		String form_code_control = request.getParameter("form_code_control");
		String status_sus_no = request.getParameter("status_sus_no");
		String remarks = request.getParameter("remarks");
		String fmn_sus_no1 = request.getParameter("fmn_sus_no1");
		String psus_no=fmn_sus_no1;
		
		String data1 = unit_name + "_" + sus_no + "_" + hq_type + "_" + form_code_control + "_" + status_sus_no + "_"
				+ remarks;
		String data2 = "_";
		String retmsg = mcommon.nChkData(data1, data2);

		if (retmsg.length() > 0) {
			model.put("msg", retmsg);
			return new ModelAndView("redirect:fp_fund_recd");
		} else {

			String tr_head = "";
			String tr_level = "";

			int id = rm.getId() > 0 ? rm.getId() : 0;
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {

				if (sus_no.length() == 0 || sus_no.equalsIgnoreCase(null)) {
					model.put("msg", "Please Generate SUS No.");
					return new ModelAndView("redirect:fp_norbat_dtl");
				}
				if (unit_name.length() == 0 || unit_name.equalsIgnoreCase(null)) {
					model.put("msg", "Please Enter the Unit Name.");
					return new ModelAndView("redirect:fp_norbat_dtl");
				}
				if (hq_type.length() == 0 || hq_type.equalsIgnoreCase(null)) {
					model.put("msg", "Please Select Hq Type.");
					return new ModelAndView("redirect:fp_norbat_dtl");
				}
				if (form_code_control.length() == 0 || form_code_control.equalsIgnoreCase(null)
						|| form_code_control.equalsIgnoreCase("-1")) {
					model.put("msg", "Please Select Under Formation.");
					return new ModelAndView("redirect:fp_norbat_dtl");
				}

				Query q0 = sessionHQL
						.createQuery("select count(id) from fp_tb_norbat_dtl_model where sus_no=:sus_no and id !=:id");
				q0.setParameter("sus_no", rm.getSus_no());
				q0.setParameter("id", id);
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					if (c == 0) {
						fpmisao.UnitDataTransfer(sus_no, form_code_control, unit_name, status_sus_no, remarks, hq_type,
								username,psus_no);
						model.put("msg", "Data Saved Successfully.");
					} else {
						model.put("msg", "Data already Exist.");
					}
				} else {
					if (c == 0) {
						msg = fpmisao.updateUnitProfilee(rm);
						model.put("msg", msg);
					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				tx.commit();
			} catch (Exception e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (Exception rbe) {
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		return new ModelAndView("redirect:fp_norbat_dtl");
	}

	@RequestMapping(value = "/admin/fp_cda_status", method = RequestMethod.GET)
	public ModelAndView fp_cda_status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Boolean val = roledao.ScreenRedirect("fp_cda_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", session, "");
		String cFinYr = fp_trDao.nGetAdmFinYear("CFY");
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", cFinYr);
		ArrayList<ArrayList<String>> list = fpmisao.getSearchCDAFunds1(cFinYr, roleSusNo);
		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("today", date1);
		Mmap.put("list.size()", list.size());
		Mmap.put("msg", msg);
		Mmap.put("date", date1);
		Mmap.put("cdaoff", fpmisao.CDAOffList("",session,""));
		return new ModelAndView("fp_cda_statusTiles");
	}

	@RequestMapping(value = "/getSearchCDAStatus", method = RequestMethod.POST)
	public ModelAndView getSearchCDAStatus(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cda_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String finyr = fp_trDao.nGetAdmFinYear("CFY");

		ArrayList<ArrayList<String>> list = fpmisao.getSearchCDAFunds(finyr, roleSusNo);
		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, ""));
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		return new ModelAndView("fp_cda_statusTiles");
	}

	@RequestMapping(value = "/admin/fp_admin_control", method = RequestMethod.GET)
	public ModelAndView fp_admin_control(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_admin_control", roleid);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("date", session.getAttribute("curDate").toString());
		Mmap.put("date", date1);
		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, ""));

		Mmap.put("n_rpttype", fpmisao.FindDomainList("", session, "WINCONTROL:disp_order"));
		Mmap.put("list", fp_trDao.getSearchPrj("2020", "PRJ"));
		Mmap.put("msg", msg);
		return new ModelAndView("fp_admin_controlTiles");
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/fp_save_admin_control", method = RequestMethod.POST)
	public ModelAndView fp_save_admin_control(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_admin_control", roleid);
		String username = session.getAttribute("username").toString();

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		fp_tb_admin_control_model am = new fp_tb_admin_control_model();

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date currentDate = new Date();
		String dateString = formatter.format(currentDate);

		Date date_from = null;
		Date date_to = null;
		String date_from1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String date_to1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		Date curr_dt = null;

		try {
			String nEst1 = request.getParameter("est_type");
			date_from = formatter.parse(request.getParameter("date_from"));
			date_to = formatter.parse(request.getParameter("date_to"));

			date_from1 = new SimpleDateFormat("dd MMM yyyy").format(date_from);
			date_to1 = new SimpleDateFormat("dd MMM yyyy").format(date_to);

			curr_dt = formatter.parse(dateString);
			if (nEst1.equalsIgnoreCase("CFY")) {

			} else {
				if (date_from.before(curr_dt)) {
					Mmap.put("msg", "From Date should not be less than today");
					return new ModelAndView("redirect:fp_admin_control");
				} else if (date_to.before(curr_dt)) {
					Mmap.put("msg", "To Date should not be less than today");
					return new ModelAndView("redirect:fp_admin_control");
				} else if (date_from.after(date_to)) {
					Mmap.put("msg", "To Date should be greater than From Date");
					return new ModelAndView("redirect:fp_admin_control");
				}
				if (nEst1 == null || nEst1.equalsIgnoreCase("-1")) {
					Mmap.put("msg", "Please Select Estimate Type.");
					return new ModelAndView("redirect:fp_admin_control");
				}
			}
		} catch (java.text.ParseException e) {
			Mmap.put("msg", "Invalid Date Format");
			return new ModelAndView("redirect:fp_admin_control");
		}

		am.setEst_type(request.getParameter("est_type"));
		am.setFin_year(request.getParameter("fin_year"));
		am.setData_upd_by(username);
		am.setDate_from(date_from);
		am.setDate_to(date_to);
		am.setStatus(request.getParameter("status"));
		boolean hasSaved = fpmisao.saveAdminControl(am);

		if (hasSaved) {
			msg = "Data Saved Successfully";
			String nFin = request.getParameter("fin_year");
			int nFin1 = (Integer.parseInt(nFin) + 1);
			String nEst = request.getParameter("est_type") + "WIN";
			String nMsg = "";
			if (nEst.equalsIgnoreCase("PRJWIN")) {
				nMsg = "BE Projection Window ";
				nMsg = nMsg + " is now open for Financial Year " + nFin + " - " + nFin1;
				nMsg = nMsg + " from " + date_from1 + " to " + date_to1;
			} else if (nEst.equalsIgnoreCase("RADWIN")) {
				nMsg = "Re-Adjustment Window ";
				nMsg = nMsg + " is now open for Financial Year " + nFin + " - " + nFin1;
				nMsg = nMsg + " from " + date_from1 + " to " + date_to1;
			} else if (nEst.equalsIgnoreCase("CFYWIN")) {
				nMsg = "Current Financial Year is now Changed to " + nFin + " - " + nFin1;
			} else if (nEst.equalsIgnoreCase("CPYWIN")) {
				nMsg = "Current Projection Year is now Changed to " + nFin + " - " + nFin1;
			} else {
				nMsg = "";
			}
			if (nMsg.length() > 0) {
				Boolean b = fp1_Dao.nSaveAlertMsg(nMsg, nEst, "");
			}
		} else
			msg = "Error - Unable to perform action";
		Mmap.put("msg", msg);

		return new ModelAndView("redirect:fp_admin_control");
	}

	@RequestMapping(value = "/BkdList", method = RequestMethod.POST)
	public @ResponseBody List<String> BkdList(String id, String roleSusNo, HttpSession s1) {
		List<String> list = fpmisao.getSearchCDABook(id, roleSusNo);
		return list;
	}

	@RequestMapping(value = "/fp_cda_statusAction", method = RequestMethod.POST)
	public ModelAndView fp_cda_statusAction(@ModelAttribute("fp_cda_statusCMD") fp_tb_cda_detl20_model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cda_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		// Double fwd_amt1 = Double.parseDouble(fwd_amt);
		try {
			
			String fwd_amt = request.getParameter("fwd_amt2").replaceAll(",", "");
			String fwd_ref = request.getParameter("fwd_ref2");
			String fwd_date = request.getParameter("fwd_date2");
			String fwdid = request.getParameter("fwdid");
			String fwdid2 = request.getParameter("fwdid2");
			String tr_head_to12 = request.getParameter("tr_head_to12");
			String total_amt2 = request.getParameter("total_amt2").replaceAll(",", "");
			String finyr2 = request.getParameter("fp_finYr2");
			String unitcd2 = request.getParameter("cda_unit_code2");
			String cdaunit2 = request.getParameter("unit_cda2");			
			if (fwd_amt == null || fwd_amt.equals("") || fwd_amt == "0") {
				model.put("msg", "Please Enter the Fwd Amount");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (Double.parseDouble(fwd_amt) > Double.parseDouble(total_amt2)) {
				model.put("msg", "Fwd Amount is higher than Expenditure");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (fwd_date == null || fwd_date.equals("")) {
				model.put("msg", "Please Select the Date");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (fwd_ref.equalsIgnoreCase(null) || fwd_ref.equals("")) {
				model.put("msg", "Please Enter the Reference");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (fwd_ref.equalsIgnoreCase(null) || fwd_ref.equals("")) {
				model.put("msg", "Please Enter the Reference");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (unitcd2.equalsIgnoreCase(null) || unitcd2.equals("")) {
				model.put("msg", "Please Enter Unit Code");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (cdaunit2.equalsIgnoreCase(null) || cdaunit2.equals("")) {
				model.put("msg", "Please Unit's CDA");
				return new ModelAndView("redirect:fp_cda_status");
			}

			String username = session.getAttribute("username").toString();
			String data1 = fwd_ref + "_" + fwd_date + "_" + tr_head_to12+"_"+unitcd2+"_"+cdaunit2;
			String data2 = fwdid + "_" + fwdid2 + "_" + total_amt2 + "_" + finyr2;
			String retmsg = mcommon.nChkData(data1, data2);
			if (retmsg.length() > 0) {
				model.put("msg", retmsg);
				return new ModelAndView("redirect:fp_cda_status");
			} else {
				msg = fpmisao.FwdDataTransfer(roleSusNo, fwd_amt, fwd_ref, fwd_date, fwdid2, username, tr_head_to12,
						finyr2,unitcd2,cdaunit2);
			}
		} catch (Exception e) {
			model.put("msg", "Invalid Data found.");
			return new ModelAndView("redirect:fp_cda_status");
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:fp_cda_status");
	}

	@RequestMapping(value = "/getCDAFwdBookingDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getCDAFwdBookingDetails(int expID, HttpSession session) {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String finYr = fp_trDao.nGetAdmFinYear("CFY").substring(2, 4);

		ArrayList<ArrayList<String>> data = fp1_Dao.getCDAFwdBookingDetails(finYr, sus_no, expID);

		return data;
	}
	
	@RequestMapping(value = "/getexpBookingDetails", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getexpBookingDetails(int expID, HttpSession session) {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String finYr = fp_trDao.nGetAdmFinYear("CFY").substring(2, 4);
		ArrayList<ArrayList<String>> data = fp1_Dao.getexpBookingDetails(finYr, sus_no, expID);

		return data;
	}

	@RequestMapping(value = "/fp_cda_bookAction", method = RequestMethod.POST)
	public ModelAndView fp_cda_bookAction(@ModelAttribute("fp_cda_statusCMD") fp_tb_cda_detl20_model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cda_status", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		try {
			Double bkd_amt = Double.parseDouble(request.getParameter("bkd_amt1").replaceAll(",", "").toString());
			String bkd_ref = request.getParameter("bkd_ref1");
			String bkd_remarks = request.getParameter("bkd_remarks1");
			String bkd_date = request.getParameter("bkd_date1");
			int exp_id = Integer.parseInt(request.getParameter("exp_id"));
			int bkd_id = Integer.parseInt(request.getParameter("bkd_id1"));
			
			if (bkd_amt == null || bkd_amt <= 0) {
				model.put("msg", "Please Enter the Booked Amount");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (bkd_date == null || bkd_date.equalsIgnoreCase("")) {
				model.put("msg", "Please Select the Date");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (bkd_ref == null || bkd_ref.equalsIgnoreCase("")) {
				model.put("msg", "Please Enter the Reference");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (bkd_remarks == null || bkd_remarks.equalsIgnoreCase("")) {
				model.put("msg", "Please Enter the Remarks");
				return new ModelAndView("redirect:fp_cda_status");
			}
			String data1 = bkd_ref + "_" + bkd_remarks + "_" + bkd_date;
			String data2 = exp_id + "_" + bkd_id;
			String retmsg = mcommon.nChkData(data1, data2);

			if (retmsg.length() > 0) {
				model.put("msg", retmsg);
				return new ModelAndView("redirect:fp_cda_status");
			} else {
				String username = session.getAttribute("username").toString();
				msg = fpmisao.BkdDataTransfer(roleSusNo, bkd_amt, bkd_ref, bkd_remarks, bkd_date, exp_id, bkd_id,
						username);
			}
		} catch (Exception e) {
			model.put("msg", "Invalid Data Found.");
			return new ModelAndView("redirect:fp_cda_status");
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:fp_cda_status");
	}

	@RequestMapping(value = "/fp_exp_delAction", method = RequestMethod.POST)
	public ModelAndView fp_exp_delAction(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cda_status", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		try {
			String del_remarks = request.getParameter("del_remarks5");
			String exp_id = request.getParameter("exp_id5");
			String bkd_id = request.getParameter("bkd_id5");
			if (exp_id == null || exp_id.equalsIgnoreCase("")) {
				model.put("msg", "Expenditure Not Found. Try Again");
				return new ModelAndView("redirect:fp_cda_status");
			}
			if (del_remarks == null || del_remarks.equalsIgnoreCase("")) {
				model.put("msg", "Please Enter the Remarks");
				return new ModelAndView("redirect:fp_cda_status");
			}
			String data1 = del_remarks;
			String data2 = exp_id + "_" + bkd_id;
			String retmsg = mcommon.nChkData(data1, data2);

			if (retmsg.length() > 0) {
				model.put("msg", retmsg);
				return new ModelAndView("redirect:fp_cda_status");
			} else {
				String username = session.getAttribute("username").toString();
				msg = fpmisao.delExpData(roleSusNo, del_remarks, exp_id,bkd_id, username);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg", "Invalid Data Found.");
			return new ModelAndView("redirect:fp_cda_status");
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:fp_cda_status");
	}

	
	@RequestMapping(value = "/admin/fp_finyr_creation", method = RequestMethod.GET)
	public ModelAndView fp_finyr_creation(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String year1 = "";
		ArrayList<ArrayList<String>> list = fpmisao.getSearchFinYr(year1);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_finyr_creationTiles");
	}

	@RequestMapping(value = "/FinyrCreateFormAction", method = RequestMethod.POST)
	public ModelAndView FinyrCreateFormAction(@ModelAttribute("FinyrCreateFormCMD") fp_domain_values_Model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int yr11 = Year.now().getValue() + 1;
		String domainid = "FINYEAR";
		String codevalue = request.getParameter("year");
		int yr12 = 0;

		String label = request.getParameter("year1");
		String lable_s1 = "";
		String lable_s2 = "";
		String lable_s = "";
		if (label.length() == 11) {
			lable_s1 = label.substring(0, 7);
			lable_s2 = label.substring(9, 11);
			lable_s = lable_s1.concat(lable_s2);
		} else {
			model.put("msg", "Please Enter the Year.");
			return new ModelAndView("redirect:fp_finyr_creation");

		}
		String disp_order = codevalue;

		try {
			yr12 = Integer.parseInt(codevalue);

			if (codevalue.length() == 0 || codevalue.equalsIgnoreCase(null)) {
				model.put("msg", "Please Enter the Year.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (yr12 > yr11) {
				model.put("msg", "Future Year cannot be allowed.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (codevalue.length() < 4) {
				model.put("msg", "Year Format Require in YYYY.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}

			String data1 = label + "_" + lable_s1 + "_" + lable_s2 + "_" + lable_s + "_" + disp_order;
			String data2 = "_";
			String retmsg = mcommon.nChkData(data1, data2);

			if (codevalue.length() == 0 || codevalue.equalsIgnoreCase(null) || codevalue.length() < 4) {
				model.put("msg", "Please Enter the Year.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (retmsg.length() > 0) {
				model.put("msg", retmsg);
				return new ModelAndView("redirect:fp_finyr_creation");
			} else {
				ArrayList<ArrayList<String>> listc = fpmisao.getCheckDomain(codevalue);
				if (listc.size() == 0) {
					fpmisao.DomDataTransfer(domainid, codevalue, label, lable_s, disp_order);
					model.put("msg", "Data Saved Successfully.");
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
		} catch (Exception e) {
			model.put("msg", "Enter Valid Year.");
		}
		return new ModelAndView("redirect:fp_finyr_creation");
	}
	
	
	
	
	@RequestMapping(value = "/FinyrCreateFormAction1111", method = RequestMethod.POST)
	public ModelAndView FinyrCreateFormAction1111(@ModelAttribute("FinyrCreateFormCMD") fp_domain_values_Model rm,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int yr11 = Year.now().getValue();
		String domainid = "FINYEAR";
		String codevalue = request.getParameter("year");
		int yr12 = 0;

		String label = request.getParameter("year1");
		String lable_s1 = "";
		String lable_s2 = "";
		String lable_s = "";
		if (label.length() == 11) {
			lable_s1 = label.substring(0, 7);
			lable_s2 = label.substring(9, 11);
			lable_s = lable_s1.concat(lable_s2);
		} else {
			model.put("msg", "Please Enter the Year.");
			return new ModelAndView("redirect:fp_finyr_creation");

		}
		String disp_order = codevalue;

		try {
			yr12 = Integer.parseInt(codevalue);

			if (codevalue.length() == 0 || codevalue.equalsIgnoreCase(null)) {
				model.put("msg", "Please Enter the Year.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (yr12 > yr11) {
				model.put("msg", "Future Year cannot be allowed.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (codevalue.length() < 4) {
				model.put("msg", "Year Format Require in YYYY.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}

			String data1 = label + "_" + lable_s1 + "_" + lable_s2 + "_" + lable_s + "_" + disp_order;
			String data2 = "_";
			String retmsg = mcommon.nChkData(data1, data2);

			if (codevalue.length() == 0 || codevalue.equalsIgnoreCase(null) || codevalue.length() < 4) {
				model.put("msg", "Please Enter the Year.");
				return new ModelAndView("redirect:fp_finyr_creation");
			}
			if (retmsg.length() > 0) {
				model.put("msg", retmsg);
				return new ModelAndView("redirect:fp_finyr_creation");
			} else {
				ArrayList<ArrayList<String>> listc = fpmisao.getCheckDomain(codevalue);
				if (listc.size() == 0) {
					fpmisao.DomDataTransfer(domainid, codevalue, label, lable_s, disp_order);
					model.put("msg", "Data Saved Successfully.");
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
		} catch (Exception e) {
			model.put("msg", "Enter Valid Year.");
		}
		return new ModelAndView("redirect:fp_finyr_creation");
	}

	@RequestMapping(value = "/getSearchFinYr", method = RequestMethod.POST)
	public ModelAndView getSearchFinYr(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year2", required = false) String year2, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("year", year2);
		ArrayList<ArrayList<String>> list = fpmisao.getSearchFinYr("");
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		ArrayList<ArrayList<String>> listt = fpmisao.getSearchTable(year2);
		Mmap.put("listt", listt);
		Mmap.put("listt.size()", listt.size());
		return new ModelAndView("fp_finyr_creationTiles");
	}
	
	@RequestMapping(value = "/getCreateTable", method = RequestMethod.POST)
	public ModelAndView getCreateTable(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year3", required = false) String year3, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> listc = fpmisao.getCheckTable(year3);
		if (listc.size() == 0) {
			msg = fpmisao.getCreateTableF(year3) ? "Table(s) Created Successfully"
					: "Error - unable to create table(s)";
			Mmap.put("msg", msg);
		} else {
			Mmap.put("msg", "Table already Exist.");
		}
		return new ModelAndView("redirect:fp_finyr_creation");
	}
	
	

	@RequestMapping(value = "/getCreateTable1111", method = RequestMethod.POST)
	public ModelAndView getCreateTable1111(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year3", required = false) String year3, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_finyr_creation", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			ArrayList<ArrayList<String>> listc = fpmisao.getCheckTable(year3);
			if (listc.size() == 0) {
				fpmisao.getCreateTableF(year3);
				Mmap.put("msg", "Table Created Successfully.");
			} else {
				Mmap.put("msg", "Table already Exist.");
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:fp_finyr_creation");
	}

	@RequestMapping(value = "/admin/fp_upload_app", method = RequestMethod.GET)
	public ModelAndView fp_upload_app(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_upload_app", roleid);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> listc = fp1_Dao.getCheckUpdData("Y", "N", "N");
		Mmap.put("conc_req", listc.size());
		Mmap.put("sus_no", roleSusNo);
		Mmap.put("date", session.getAttribute("curDate").toString());
		Mmap.put("date", date1);
		Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, "CFY"));
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		Mmap.put("msg", msg);
		Mmap.put("n_sel",":" + fp_trDao.nGetAdmFinYear("CFY"));
		return new ModelAndView("fp_upload_appTiles");
	}

	@RequestMapping(value = "/UploadDataList", method = RequestMethod.POST)
	public ModelAndView UploadDataList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus1,
			@RequestParam(value = "est_type2", required = false) String est_type2,
			@RequestParam(value = "fin_year2", required = false) String fin_year2, HttpServletRequest request) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_upload_app", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		int yr11 = Year.now().getValue();
		String yr1 = fin_year2;
		int yr12 = Integer.parseInt(yr1);

		if (yr1.length() == 0 || yr1.equalsIgnoreCase(null)) {
			Mmap.put("msg", "Please Select the Year.");
			return new ModelAndView("redirect:fp_upload_app");
		}
		if (yr12 > yr11) {
			Mmap.put("msg", "Future Year cannot be allowed.");
			return new ModelAndView("redirect:fp_upload_app");
		}
		if (yr1.length() < 4) {
			Mmap.put("msg", "Year Format Require in YYYY.");
			return new ModelAndView("redirect:fp_upload_app");
		}
		if (est_type2.equals("") || est_type2.equals(null)) {
			Mmap.put("msg", "Please Select Estimate Type.");
			return new ModelAndView("redirect:fp_upload_app");
		}
		Mmap.put("fin_year2", fin_year2);
		ArrayList<ArrayList<String>> list = fpmisao.search_upd_datay(est_type2, fin_year2, roleSusNo);

		ArrayList<ArrayList<String>> listc = fp1_Dao.getCheckUpdData("Y", "N", "N");
		Mmap.put("conc_req", listc.size());

		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("list.size()", list.size());
		if (list.size() == 0) {
			Mmap.put("msg", "No Data Available");
		}
		Mmap.put("est_type", est_type2);
		Mmap.put("fin_year2", fin_year2);
		Mmap.put("sus1", sus1);
		Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, "CFY"));
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		Mmap.put("n_sel",est_type2+":" + fin_year2);
		return new ModelAndView("fp_upload_appTiles");
	}

	@RequestMapping(value = "/UploadDataUpd", method = RequestMethod.POST)
	public ModelAndView UploadDataUpd(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updid", required = false) String updid,
			@RequestParam(value = "cur_allot2", required = false) String cur_allot2,
			@RequestParam(value = "sus11", required = false) String sus11,
			@RequestParam(value = "est_type21", required = false) String est_type21,
			@RequestParam(value = "fin_year21", required = false) String fin_year21, HttpServletRequest request) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_upload_app", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		Mmap.put("updid", updid);
		msg = fpmisao.updatefpupload(updid, cur_allot2);
		Mmap.put("msg", msg);

		Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));

		Mmap.put("n_finyr", fp_trDao.FindFinYear("", session, ""));
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		ArrayList<ArrayList<String>> list = fpmisao.search_upd_datay(est_type21, fin_year21, sus11);
		Mmap.put("list", list);

		ArrayList<ArrayList<String>> listc = fp1_Dao.getCheckUpdData("Y", "N", "N");
		Mmap.put("conc_req", listc.size());

		Mmap.put("est_type21", est_type21);
		Mmap.put("fin_year21", fin_year21);
		Mmap.put("sus11", sus11);

		return new ModelAndView("fp_upload_appTiles");
	}

	@RequestMapping(value = "/admin/fp_cr_dft_fund_allot", method = RequestMethod.GET)
	public ModelAndView fp_cr_dft_fund_allot(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cr_dft_fund_allot", roleid);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);
		String m1_lvl = nUnty + "_" + rolefmn + "_" + roleSusNo;

		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", session, "CFY");
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
		Mmap.put("msg", msg);
		return new ModelAndView("fp_cr_dft_fund_allotTiles");
	}

	@RequestMapping(value = "/GetDftFundData", method = RequestMethod.POST)
	public ModelAndView GetDftFundData(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus1", required = false) String sus1,
			@RequestParam(value = "est_type2", required = false) String est_type2,
			@RequestParam(value = "fin_year2", required = false) String fin_year2,
			@RequestParam(value = "para1", required = false) String para1,
			@RequestParam(value = "para2", required = false) String para2, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_cr_dft_fund_allot", session.getAttribute("roleid").toString());
		System.out.println("fin_year2-"+fin_year2);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		int yr11 = Year.now().getValue();
		String yr1 = fin_year2;
		int yr12 = 0;
		try {
			if (yr1 != "" || yr1 != null) {
				yr12 = Integer.parseInt(yr1);
			}

			if (yr1.length() == 0 || yr1.equalsIgnoreCase(null)) {
				Mmap.put("msg", "Please Select the Year.");
				return new ModelAndView("redirect:fp_cr_dft_fund_allot");
			}
			if (yr12 > yr11) {
				Mmap.put("msg", "Future Year cannot be allowed.");
				return new ModelAndView("redirect:fp_cr_dft_fund_allot");
			}
			if (yr1.length() < 4) {
				Mmap.put("msg", "Year Format Require in YYYY.");
				return new ModelAndView("redirect:fp_cr_dft_fund_allot");
			}

			if (est_type2.equals("") || est_type2.equals(null)) {
				Mmap.put("msg", "Please Select Estimate Type.");
				return new ModelAndView("redirect:fp_cr_dft_fund_allot");
			}

			String data1 = sus1 + "_" + est_type2;
			String data2 = fin_year2;
			String retmsg = mcommon.nChkData(data1, data2);

			if (retmsg.length() > 0) {
				Mmap.put("msg", retmsg);
				return new ModelAndView("redirect:fp_cr_dft_fund_allot");
			} else {

				Mmap.put("fin_year2", fin_year2);
				ArrayList<ArrayList<String>> list = fpmisao.search_upd_datay(est_type2, fin_year2, sus1);

				ArrayList<ArrayList<String>> listc = fp1_Dao.getCheckUpdData("Y", "N", "N");
				Mmap.put("conc_req", listc.size());
				Mmap.put("list", list);
				Mmap.put("date", date1);
				String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);
				String m1_lvl = nUnty + "_" + rolefmn + "_" + roleSusNo;
				ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", session, "CFY");
				Mmap.put("n_finyr", finli);
				String cfy = fp_trDao.nGetAdmFinYear("CFY");
				Mmap.put("n_cfinyr", cfy);
				Mmap.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
				ArrayList<ArrayList<String>> dftlist;
				dftlist = fp_rptDao.nCrFundDraftAllot(fin_year2, m1_lvl, para1, para2, session);
				Mmap.put("dftlist", dftlist);
				Mmap.put("n_sel", est_type2 + ":" + fin_year2 + ":" + para1 + ":" + para2);
				return new ModelAndView("fp_cr_dft_fund_allotTiles");
			}
		} catch (Exception e) {
			Mmap.put("dftlist", "Invalid Data Found.");
			return new ModelAndView("fp_cr_dft_fund_allotTiles");
		}
	}

	// GET USER DTLS FOR UPDATE
	@RequestMapping(value = "/fp_user_details", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView fp_user_details(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "usr_id", required = false) String usr_id, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_user_details", roleid);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (msg != null) {
			Mmap.put("msg", msg);
		}
		if (usr_id != null) {
			Mmap.put("data", fp_rptDao.getUserDetails(usr_id));
		}
		return new ModelAndView("fp_update_userTiles");
	}

	// UPDATE USER
	@RequestMapping(value = "/fp_update_user", method = RequestMethod.POST)
	public ModelAndView fp_update_user(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "u_id", required = true) String u_id,
			@RequestParam(value = "user_id", required = true) String user_id,
			@RequestParam(value = "army_no", required = true) String army_no,
			@RequestParam(value = "sus_nodal", required = true) String sus_nodal,
			@RequestParam(value = "sus_no", required = true) String sus_no, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_user_details", roleid);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		if (u_id.length() == 0 || u_id.equalsIgnoreCase(null) || user_id.length() == 0
				|| user_id.equalsIgnoreCase(null)) {
			Mmap.put("msg", "Please Select User ID");
			return new ModelAndView("redirect:fp_user_details");
		}
		if (army_no.length() == 0 || army_no.equalsIgnoreCase(null)) {
			Mmap.put("msg", "Please Enter Army No");
			return new ModelAndView("redirect:fp_user_details");
		}
		if (sus_no.length() == 0 || sus_no.equalsIgnoreCase(null)) {
			Mmap.put("msg", "Please Select Unit");
			return new ModelAndView("redirect:fp_user_details");
		}

		msg = fp_rptDao.updateUserDetails(user_id, army_no, sus_no, u_id, sus_nodal) ? "User Details updated successfully"
				: "Error - unable to perform action";
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:fp_user_details");
	}

	// Auto Complete SUS No for Non Orbat Units
	@RequestMapping(value = "/getNonOrbatSusNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getNonOrbatSusNoList(String role_id, HttpSession sessionUserId, String sus_no) {

		Role roleList = roledao.getRoleNameListbyid(Integer.parseInt(role_id));
		String access_lvl = roleList.getAccess_lvl();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (access_lvl.equals("FP")) {
			q = session.createQuery(
					"select distinct sus_no from fp_tb_norbat_dtl_model where status_sus_no='Active' and sus_no ilike :sus_no")
					.setMaxResults(10);
			q.setParameter("sus_no", sus_no + "%");
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Auto Complete SUS No for Non Orbat Units
	@RequestMapping(value = "/getAllOrbatUnitSusList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getAllOrbatUnitSusList(String param, HttpSession session) {
		List<Map<String, Object>> l = fp_rptDao.getAllUnitDtls(param, 10, session);
		return l;
	}


	// CDA UPLOAD EXCEL
	@RequestMapping(value = "/admin/fp_cda_upload", method = RequestMethod.GET)
	public ModelAndView fp_cda_upload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_cda_upload", roleid);
		if (!val) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("fp_cda_uploadTiles");
	}

	// CDA UPLOAD SAVE 
	@RequestMapping(value = "/uploadCDAAction", method = RequestMethod.POST)
	public ModelAndView uploadCDAAction(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws Exception {

		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sess.beginTransaction();

		try {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("fp_cda_upload", roleid);
			String sus_no = session.getAttribute("roleSusNo").toString();
			if (!val) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				// //return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			int countrow = 0;
			countrow = Integer.parseInt(request.getParameter("countrow"));
			String username = session.getAttribute("username").toString();
			String ser_no = "";
			String acc_head = "";
			
			String alloc_amt = "";
			String chrg_amt = "";
			String book_amt = "";
			
			String data1 = "";
			String data2 = "";			
			Double allocation_amt = 0.0;
			Double charged_amt = 0.0;
			Double booking_amt = 0.0;
			Double booking_percent = 0.0;
			Query qry;
			String crs_dt = request.getParameter("course_doc1");
				
			fp1_Dao.deletecdaupload(crs_dt);
			
			/*Session sessd = HibernateUtilNA.getSessionFactory().openSession();
			Transaction txnd = sessd.beginTransaction();
			String hqld = "delete from fp.fp_tb_cda_upload WHERE booking_date='"+crs_dt+"'";								
		    Query query = sessd.createSQLQuery(hqld);							    
		    int rowCountd = query.executeUpdate();
		    txnd.commit();
		    sessd.close();*/
			for (int i = 1; i <= countrow; i++) {

				ser_no = request.getParameter("ser_no_" + i);
				acc_head = request.getParameter("account_head_" + i);
				
				alloc_amt = request.getParameter("allocation_amt_" + i);
				chrg_amt = request.getParameter("charged_amt_" + i);
				book_amt = request.getParameter("booking_amt_" + i);
				
				data1 = ser_no.trim()+"_"+acc_head.trim();
				data2 = alloc_amt.trim() +"_"+chrg_amt.trim()+"_"+book_amt.trim();
				msg= mcommon.nChkData(data1,data2);
				if(msg.length()>0) {
					throw new Exception(msg);
				}
			
				allocation_amt = Double.parseDouble((alloc_amt.toString().equalsIgnoreCase("") ? "0" : alloc_amt));
				charged_amt = Double.parseDouble((chrg_amt.toString().equalsIgnoreCase("") ? "0" : chrg_amt));
				booking_amt = Double.parseDouble((book_amt.toString().equalsIgnoreCase("") ? "0" : book_amt));
				
				booking_percent = allocation_amt == 0.0 ? 0.0 : (booking_amt/allocation_amt)*100;
				qry = sess.createSQLQuery("INSERT INTO fp.fp_tb_cda_upload(ser_no, acc_head, allocation_amt, charged_amt, booking_amt, booking_percent, data_cr_by,booking_date,cr_sus_no) VALUES "
						+ "(:ser_no, :acc_head, :allocation_amt, :charged_amt, :booking_amt, :booking_percent,:user,:bk_date,:cr_sus_no)");
				qry.setString("ser_no", ser_no);
				qry.setString("acc_head", acc_head);
				qry.setDouble("allocation_amt", allocation_amt);
				qry.setDouble("charged_amt", charged_amt);
				qry.setDouble("booking_amt", booking_amt);
				qry.setDouble("booking_percent",booking_percent);
				qry.setString("user", username);
				qry.setString("bk_date", crs_dt);
				qry.setString("cr_sus_no", sus_no);				
				qry.executeUpdate();
				qry = null;
			}
			msg = "CGDA Booking Data Uploaded Successfully";

		} catch (Exception e) {
			//System.err.println(e);
			msg = "Error - Unable to Perform Action";
			tx.rollback();
		} finally {
			sess.close();
		}

		model.put("msg", msg);
		return new ModelAndView("redirect:fp_cda_upload");
	}

	@RequestMapping(value = "/getCDAUploadedSheet", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getCDAUploadedSheet(String expID, HttpSession session) {
		String sus_no = session.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> data = fp1_Dao.getCDAUploadedSheet(expID);
		return data;
	}
	
	@RequestMapping(value = "/admin/fp_rpt_cda_report", method = RequestMethod.POST)
	public ModelAndView fp_rpt_cda_report(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String m1_tryear,
			String m1_lvl, String m1_rsfmt, ModelMap model, HttpSession sessionA, HttpServletRequest request) {
		String m1_slvl = "";
		String n_rpttype=m1_lvl;
		String nRpt = "4:" + m1_lvl;
		String roleid = sessionA.getAttribute("roleid").toString();
		String usrid = sessionA.getAttribute("username").toString();
		int userid = (Integer) sessionA.getAttribute("userId");
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_fund_be_rpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		String nPara = "";
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		
		String hqLvl[] = m1_lvl.split("_");

		ArrayList<ArrayList<String>> list = fpmisao.getSearchCDAFunds(m1_tryear, rolesus);
		model.put("n_1", list);
		
		//model.put("n_1", fp_rptDao. .nFundTree(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype",n_rpttype);
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + m1_rsfmt);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}
	
	
	@RequestMapping(value = "/getExpFunds", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getExpFunds(String chead,String csus, HttpSession session) {
		List<Map<String, Object>> nlist = fp_trDao.getExpFundsData(chead,csus, session);
		return nlist;
	}

}