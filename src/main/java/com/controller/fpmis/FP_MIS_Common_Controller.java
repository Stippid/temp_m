package com.controller.fpmis;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dao.fpmis.FP_MIS_MasterDAO;
import com.dao.fpmis.FP_MIS_ReportsDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;

import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class FP_MIS_Common_Controller {
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	@Autowired
	private FP_MIS_ReportsDAO fp_rptDao;

	HexatoAsciiDAOImpl HexaDao = new HexatoAsciiDAOImpl();
	
	@RequestMapping(value = "/getMajorHeadCode") 
	public @ResponseBody List<String> getMajorHeadCode(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct major_head,head_desc,tr_head from FP_MIS_Master_Model where status='1' and tr_level='1' order by major_head");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = fp1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getMinorHeadCode") 
	public @ResponseBody List<String> getMinorHeadCode(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct minor_head,head_desc,tr_head from FP_MIS_Master_Model where status='1' and tr_level='2' and (minor_head is not null or minor_head<>'') order by minor_head");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = fp1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getSubHeadCode") 
	public @ResponseBody List<String> getSubHeadCode(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//String qry = "select distinct sub_head,head_desc,tr_head from FP_MIS_Master_Model where status='1' and tr_level='3' order by sub_head";
		String qry = "select distinct sub_head,head_desc,tr_head from FP_MIS_Master_Model where status='1' and tr_level='3' order by sub_head";
		
		Query q = session.createQuery(qry);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = fp1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getSubHead1Code") 
	public @ResponseBody List<String> getSubHead1Code(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sub_head1,head_desc,tr_head from FP_MIS_Master_Model where status='1' and (split_part(tr_head,':',5)='' or split_part(tr_head,':',5) is null) and (sub_head1 is not null or sub_head1<>'') order by sub_head1");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = fp1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/getSubHead2Code") 
	public @ResponseBody List<String> getSubHead2Code(String enc, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sub_head2,head_desc,tr_head from FP_MIS_Master_Model where status='1' and (split_part(tr_head,':',6)='' or split_part(tr_head,':',6) is null) and (sub_head2 is not null or sub_head2<>'') order by sub_head2");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (enc.equalsIgnoreCase("")) {
			return list;
		} else {
			if (list.size() != 0) {
				List<String> FList = fp1_Dao.getMNHEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
	}

	@RequestMapping(value = "/FindHeadDesc", method = RequestMethod.POST)
	@ResponseBody
	public List<String> FindHeadDesc(String nPara, HttpSession s1) {
		String[] nPara1 = nPara.split(":");
		String nPara2 = nPara.replace(nPara1[0] + ":", "");
		String nPara3 = nPara.substring((nPara1[0] + ":").length(), nPara.length());
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct tr_head,head_desc from FP_MIS_Master_Model where tr_head=:code");
		q.setParameter("code", nPara3);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/FindUnitDesc", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> FindUnitDesc(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.FindUnitList("", s1, nPara);
		return list;
	}

	@RequestMapping(value = "/ListUnitDesc", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> ListUnitDesc(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.getunitBuglist("", s1, nPara);
		return list;
	}

	@RequestMapping(value = "/ListHeadDesc", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> ListHeadDesc(String nPara, String nParaa, HttpSession s1) {
		String nPara2 = "";
		String nPara3 = "";
		String[] nPara1 = null;
		ArrayList<ArrayList<String>> list;
		if (nPara.length() > 0) {
			if (nParaa.equalsIgnoreCase("ST") || nParaa.equalsIgnoreCase("EX")) {
				nPara1 = nPara.split(":");
				nPara2 = nPara.replace(nPara1[0] + ":", "");
				nPara3 = nPara.substring((nPara1[0] + ":").length(), nPara.length());
			} else {
				nPara2 = nPara;
				nPara3 = nPara;
			}
		} else {
			nPara2 = "";
			nPara3 = "";
		}
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		if (nParaa.equalsIgnoreCase("ST") || nParaa.equalsIgnoreCase("EX")) {
			list = fp_trDao.getHeadlist("", s1, nPara3);
		} else {
			list = fp_trDao.getHeadBuglist("", s1, nPara3);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/uploadBEAction", method = RequestMethod.POST)
	public ModelAndView uploadBEAction(@RequestParam(value = "excelfile", required = false) MultipartFile excelfile,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws Exception {
		String nMsg = "";
		try {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("fp_be_upload", roleid);
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
			String author = request.getParameter("author");
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			if (author.length() == 0) {
				System.err.println("\n\n Metadata not found");
				throw new Exception("1. Permission denied to upload");
			}

			String[] auth = author.split("0fsxzjyg==");
			String dec_sus = "";

			if (auth.length != 2) {
				System.err.println("\n\n Metadata not proper");
				throw new Exception("2. Permission denied to upload");
			} else {
				dec_sus = HexaDao.decrypt(auth[0], auth[1], session);
				if (dec_sus.length() == 0) {
					System.err.println("\n\n SUS decrypting failed");
					throw new Exception("3. Permission denied to upload");
				} else if (!dec_sus.equalsIgnoreCase(roleSusNo)) {
					System.err.println("\n\n SUS decrypted not matched with rolesus");
					throw new Exception("4. Permission denied to upload");
				}
			}

			String fin_year = request.getParameter("fin_year");  //fp_trDao.nGetAdmFinYear("CFY");
			String upload_for = request.getParameter("fp_upldfor");
			Date date = new Date();
			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query qry;

			String conc_flag = "Y";
			String app_flag = "Y";
			String submit_flag = "Y";

			String tr_head = "";

			String fr_sus_no = "";
			String dflag = "N";
			String head_code = "";
			String head_desc = "";
			String bg_sus = "";
			String bg_hlder_fmn = "";
			String bg_hlder = "";
			String prv_allot_s = "";
			String cur_proj_s = "";
			Double cur_allot_s = 0.0;
			Date upload_date;
			Date data_upd_date;
			String data_upd_by = "";
			String bg_holder_name = "";
			String code_head = "";
			String deno = request.getParameter("deno").toString();

			int denoFactor = 1;
			String cur_all = "";

			if (deno.length() == 0 || deno.equalsIgnoreCase("RS")) {
				denoFactor = 1;
			} else if (deno.equalsIgnoreCase("LC")) {
				denoFactor = (int) 1e+5;

			} else if (deno.equalsIgnoreCase("CR")) {
				denoFactor = (int) 1e+7;
			} else {
				denoFactor = 1;
				deno = "RS";
			}

			String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);

			List<String> errorList = new ArrayList<String>();
			List<String> bg_hdCodeList = fp_trDao.getAllBudgetHolderWithHead(roleSusNo);
			String prevCode = "";

			for (int i = 1; i < countrow; i++) {

				bg_sus = request.getParameter("BG_SUS_" + i);
				tr_head = request.getParameter("TR_HEAD_" + i);
				fr_sus_no = request.getParameter("FR_SUS_NO_" + i);
				code_head = request.getParameter("HEAD_" + i);
				
				bg_holder_name = request.getParameter("BG_HLDER_" + i);

				if (bg_sus != null && tr_head != null && tr_head.length() > 4) {
					
					if(code_head == null) {
						code_head = prevCode;
					}
				
					prevCode = code_head;
					//System.out.println("tr_head :: "+tr_head+"\bg_sus :: "+bg_sus+"\n");
					if (!bg_hdCodeList.contains(bg_sus + "-" + tr_head)) {
						errorList.add(code_head + "(" + tr_head + ") not mapped with " + bg_holder_name + "(" + bg_sus + ")");
					}
				}
			}

			if (errorList.size() > 0) {
				model.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
				model.put("n_finyr", fp_trDao.FindFinYear("", session, "CFY"));
				model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
				model.put("errorList", errorList);
				return new ModelAndView("fp_be_uploadTiles");
			}

			if (!fp1_Dao.getCheckDataUpdation(conc_flag, app_flag, submit_flag)) {

				try {
					String retmsg = nChkData(fin_year + "_" + upload_for, "");
					if (retmsg.length() > 0) {
						model.put("msg", retmsg);
						return new ModelAndView("redirect:fp_be_upload");
					}

					Query qry0 = session0.createSQLQuery(
							"delete from fp.fp_tmp_be_upload where fr_sus_no=:sus_no and fin_year=:fin_year and upload_for=:upload_for");
					qry0.setParameter("sus_no", roleSusNo);
					qry0.setParameter("fin_year", fin_year);
					qry0.setParameter("upload_for", upload_for);
					qry0.executeUpdate();
					String data1 = "";
					String data2 = "";
					System.out.println("Entering checking");

					for (int i = 1; i < countrow; i++) {

						bg_sus = request.getParameter("BG_SUS_" + i);
						tr_head = request.getParameter("TR_HEAD_" + i);
						fr_sus_no = request.getParameter("FR_SUS_NO_" + i);
						if (bg_sus != null && tr_head != null && tr_head.length() > 4) {
							dflag = request.getParameter("DFLAG_" + i);

							head_code = request.getParameter("HEAD_CODE_" + i);
							head_desc = "";

							bg_hlder_fmn = request.getParameter("BG_HLDER_FMN_" + i);
							bg_hlder = request.getParameter("BG_HLDER_" + i);
							prv_allot_s = request.getParameter("PRV_ALLOT_" + i);
							cur_proj_s = request.getParameter("CUR_PROJ_" + i);
							cur_all = request.getParameter("CUR_ALLOT_" + i);

							data1 = bg_sus + "_" + tr_head + "_" + dflag + "_" + head_code + "_" + bg_hlder_fmn + "_"
									+ bg_hlder;
							data2 = prv_allot_s + "_" + cur_proj_s + "_" + cur_all;
							retmsg = nChkData(data1, data2);

							if (retmsg.length() > 0) {
								throw new Exception(retmsg);
							}

							if (roleSusNo.equalsIgnoreCase(fr_sus_no)) {  

								if (cur_all.length() == 0 || cur_all.equalsIgnoreCase(null)) {
									cur_all = "0";
								}

								cur_allot_s = Double.parseDouble(cur_all);
								upload_date = date;

								data_upd_date = date;
								data_upd_by = username;
								//System.out.println("Cur allt s - " + cur_allot_s + " Upload date - " + upload_date);
								qry = session1.createSQLQuery("insert into fp.fp_tmp_be_upload "
										+ "(fr_sus_no,dflag,tr_head,head_code,head_desc,bg_sus,bg_hlder_fmn,bg_hlder,prv_allot,cur_proj,cur_allot,upload_for,prv_allot_s,cur_proj_s,cur_allot_s,data_upd_date,data_upd_by,upload_date,fin_year,upd_flag,conc_flag,app_flag,submit_flag,rs_fmt)"
										+ "values"
										+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24)");

								qry.setParameter("a1", roleSusNo);
								qry.setParameter("a2", dflag);
								qry.setParameter("a3", tr_head);
								qry.setParameter("a4", head_code);
								qry.setParameter("a5", head_desc);
								qry.setParameter("a6", bg_sus);
								qry.setParameter("a7", bg_hlder_fmn);
								qry.setParameter("a8", bg_hlder);

								qry.setParameter("a9", 0);
								qry.setParameter("a10", 0);
								qry.setParameter("a11", 0);
								qry.setParameter("a12", upload_for);
								qry.setParameter("a13", prv_allot_s);

								qry.setParameter("a14", cur_proj_s);
								qry.setParameter("a15", cur_allot_s.toString());
								qry.setParameter("a16", data_upd_date);
								qry.setParameter("a17", data_upd_by);
								qry.setParameter("a18", upload_date);
								qry.setParameter("a19", fin_year);
								qry.setParameter("a20", "N");

								if (nUnty.equalsIgnoreCase("0")) {
									qry.setParameter("a21", "Y");
								} else {
									qry.setParameter("a21", "N");
								}
								qry.setParameter("a22", "N");
								qry.setParameter("a23", "N");
								qry.setParameter("a24", deno);

								qry.executeUpdate();
							} else {
								System.err.println("\n\nBudget Head/Budget Holder not mapped." + fr_sus_no + ","
										+ bg_sus + "," + tr_head);
								throw new Exception("Budget Head/Budget Holder not mapped " + tr_head);
							}
						} else if (dflag.equalsIgnoreCase("E")) {
							throw new Exception(
									"A calculation error found in excel file \nPlease amend the allocation and try again");
						}
					}
					tx0.commit();
					tx1.commit();
					nMsg = "First Level Excel Uploaded Successfully.\nPlease Proceed for Second Level Approval\n\nFP -> Allocation -> Approval of Upload";
				} catch (SQLException e) {
					nMsg = "Error - unable to perform action";
					tx0.rollback();
					tx1.rollback();
				} catch (Exception e) {
						System.out.println("exce "+e.getMessage());
					if (e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1
							|| e.getMessage().indexOf("calculation") > -1) {
						nMsg = e.getMessage();
					} else
						nMsg = "Error -- unable to perform action";

					tx0.rollback();
					tx1.rollback();
				} finally {
					session0.close();
					session1.close();
				}
			} else
				nMsg = "Data already Exist";
		} catch (Exception e) {
			System.out.println("Exception");
			if (e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1) {
				nMsg = e.getMessage();
			} else
				nMsg = "Error - unable to perform action";
		}
		model.put("msg", nMsg);
		return new ModelAndView("redirect:fp_be_upload");
	}
	
	

	@RequestMapping(value = "/uploadBEAction_old", method = RequestMethod.POST)
	public ModelAndView uploadBEAction_old(@RequestParam(value = "excelfile", required = false) MultipartFile excelfile,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws Exception {
		String nMsg = "";
		try {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("fp_be_upload", roleid);
			if (!val) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				////return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			int countrow = 0;

			countrow = Integer.parseInt(request.getParameter("countrow"));
			String username = session.getAttribute("username").toString();
			String author = request.getParameter("author");
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			if (author.length() == 0) {
				System.err.println("\n\n Metadata not found");
				throw new Exception("1. Permission denied to upload");
			}

			String[] auth = author.split("0fsxzjyg==");
			String dec_sus = "";

			if (auth.length != 2) {
				System.err.println("\n\n Metadata not proper");
				throw new Exception("2. Permission denied to upload");
			} else {
				dec_sus = HexaDao.decrypt(auth[0], auth[1], session);
				if (dec_sus.length() == 0) {
					System.err.println("\n\n SUS decrypting failed");
					throw new Exception("3. Permission denied to upload");
				} else if (!dec_sus.equalsIgnoreCase(roleSusNo)) {
					System.err.println("\n\n SUS decrypted not matched with rolesus");
					throw new Exception("4. Permission denied to upload");
				}
			}

			String fin_year = fp_trDao.nGetAdmFinYear("CFY");
			String upload_for = request.getParameter("fp_upldfor");
			Date date = new Date();
			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query qry;

			String conc_flag = "Y";
			String app_flag = "Y";
			String submit_flag = "Y";

			String tr_head = "";

			String fr_sus_no = "";
			String dflag = "N";
			String head_code = "";
			String head_desc = "";
			String bg_sus = "";
			String bg_hlder_fmn = "";
			String bg_hlder = "";
			String prv_allot_s = "";
			String cur_proj_s = "";
			Double cur_allot_s = 0.0;
			Date upload_date;
			Date data_upd_date;
			String data_upd_by = "";
			String bg_holder_name = "";
			String code_head = "";
			String deno = request.getParameter("deno").toString();

			int denoFactor = 1;
			String cur_all = "";

			if (deno.length() == 0 || deno.equalsIgnoreCase("RS")) {
				denoFactor = 1;
			} else if (deno.equalsIgnoreCase("LC")) {
				denoFactor = (int) 1e+5;

			} else if (deno.equalsIgnoreCase("CR")) {
				denoFactor = (int) 1e+7;
			} else {
				denoFactor = 1;
				deno = "RS";
			}

			String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);

			List<String> errorList = new ArrayList<String>();
			for (int i = 1; i < countrow; i++) {

				bg_sus = request.getParameter("BG_SUS_" + i);
				tr_head = request.getParameter("TR_HEAD_" + i);
				fr_sus_no = request.getParameter("FR_SUS_NO_" + i);
				code_head = request.getParameter("HEAD_" + i);
				bg_holder_name = request.getParameter("BG_HLDER_" + i);

				if (bg_sus != null && tr_head != null && tr_head.length() > 4) {
					if (!fp_trDao.checkBudgetHolderWithHead(fr_sus_no, bg_sus, tr_head)) {
						errorList.add(code_head+"("+tr_head + ") not mapped with "+ bg_holder_name +"("+ bg_sus + ")");
					}
				}
			}

			if (errorList.size() > 0) {
				model.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
				model.put("n_finyr", fp_trDao.FindFinYear("", session, ""));
				model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
				model.put("errorList", errorList);
				return new ModelAndView("fp_be_uploadTiles");
			}
			
			if (!fp1_Dao.getCheckDataUpdation(conc_flag, app_flag, submit_flag)) {

				try {
					String retmsg= nChkData(fin_year+"_"+upload_for,"");
					if(retmsg.length()>0) {
						model.put("msg", retmsg);
						return new ModelAndView("redirect:fp_be_upload");
					}
					
					Query qry0 = session0.createSQLQuery(
							"delete from fp.fp_tmp_be_upload where fr_sus_no=:sus_no and fin_year=:fin_year and upload_for=:upload_for");
					qry0.setParameter("sus_no", roleSusNo);
					qry0.setParameter("fin_year", fin_year);
					qry0.setParameter("upload_for", upload_for);
					qry0.executeUpdate();
					String data1 ="";
					String data2 ="";
					System.out.println("Entering checking");
					
					for (int i = 1; i < countrow; i++) {
						
						bg_sus = request.getParameter("BG_SUS_" + i);
						tr_head = request.getParameter("TR_HEAD_" + i);
						fr_sus_no = request.getParameter("FR_SUS_NO_" + i);
						if (bg_sus != null && tr_head != null && tr_head.length() > 4) {
							dflag = request.getParameter("DFLAG_" + i);
							
							head_code = request.getParameter("HEAD_CODE_" + i);
							head_desc = "";

							bg_hlder_fmn = request.getParameter("BG_HLDER_FMN_" + i);
							bg_hlder = request.getParameter("BG_HLDER_" + i);
							prv_allot_s = request.getParameter("PRV_ALLOT_" + i);
							cur_proj_s = request.getParameter("CUR_PROJ_" + i);
							cur_all = request.getParameter("CUR_ALLOT_" + i);

							data1 = bg_sus + "_" + tr_head + "_" + dflag + "_" + head_code + "_" + bg_hlder_fmn + "_"+ bg_hlder;
							data2 = prv_allot_s + "_" + cur_proj_s + "_" + cur_all;							
							retmsg = nChkData(data1, data2);
							
							if(retmsg.length()>0) {
								throw new Exception(retmsg);
							}

							if (roleSusNo.equalsIgnoreCase(fr_sus_no)) {

								if (cur_all.length() == 0 || cur_all.equalsIgnoreCase(null)) {
									cur_all = "0";
								}

								cur_allot_s = Double.parseDouble(cur_all);
								upload_date = date;

								data_upd_date = date;
								data_upd_by = username;
								System.out.println("Cur allt s - " + cur_allot_s + " Upload date - " + upload_date);
								qry = session1.createSQLQuery("insert into fp.fp_tmp_be_upload "
										+ "(fr_sus_no,dflag,tr_head,head_code,head_desc,bg_sus,bg_hlder_fmn,bg_hlder,prv_allot,cur_proj,cur_allot,upload_for,prv_allot_s,cur_proj_s,cur_allot_s,data_upd_date,data_upd_by,upload_date,fin_year,upd_flag,conc_flag,app_flag,submit_flag,rs_fmt)"
										+ "values"
										+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24)");

								qry.setParameter("a1", roleSusNo);
								qry.setParameter("a2", dflag);
								qry.setParameter("a3", tr_head);
								qry.setParameter("a4", head_code);
								qry.setParameter("a5", head_desc);
								qry.setParameter("a6", bg_sus);
								qry.setParameter("a7", bg_hlder_fmn);
								qry.setParameter("a8", bg_hlder);

								qry.setParameter("a9", 0);
								qry.setParameter("a10", 0);
								qry.setParameter("a11", 0);
								qry.setParameter("a12", upload_for);
								qry.setParameter("a13", prv_allot_s);

								qry.setParameter("a14", cur_proj_s);
								qry.setParameter("a15", cur_allot_s.toString());
								qry.setParameter("a16", data_upd_date);
								qry.setParameter("a17", data_upd_by);
								qry.setParameter("a18", upload_date);
								qry.setParameter("a19", fin_year);
								qry.setParameter("a20", "N");

								if (nUnty.equalsIgnoreCase("0")) {
									qry.setParameter("a21", "Y");
								} else {
									qry.setParameter("a21", "N");
								}
								qry.setParameter("a22", "N");
								qry.setParameter("a23", "N");
								qry.setParameter("a24", deno);

								qry.executeUpdate();
							} else {
								System.err.println("\n\nBudget Head/Budget Holder not mapped."+fr_sus_no+","+ bg_sus+","+ tr_head);
								throw new Exception("Budget Head/Budget Holder not mapped "+tr_head);
							}
						} else if (dflag.equalsIgnoreCase("E")) {
							throw new Exception(
									"A calculation error found in excel file \nPlease amend the allocation and try again");
						}
					}
					tx0.commit();
					tx1.commit();
					nMsg = "First Level Excel Uploaded Successfully.\nPlease Proceed for Second Level Approval\n\nFP -> Allocation -> Approval of Upload";
				} catch (SQLException e) {
					nMsg = "Error - unable to perform action";
					tx0.rollback();
					tx1.rollback();
				} catch (Exception e) {

				if(e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1|| e.getMessage().indexOf("calculation") > -1) {
						nMsg = e.getMessage();
					} else
						nMsg = "Error -- unable to perform action";

					tx0.rollback();
					tx1.rollback();
				} finally {
					session0.close();
					session1.close();
				}
			} else
				nMsg = "Data already Exist";
		} catch (Exception e) {
			if(e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1) {
				nMsg = e.getMessage();
			} else
				nMsg = "Error - unable to perform action";
		}
		model.put("msg", nMsg);
		return new ModelAndView("redirect:fp_be_upload");
	}
	
	@RequestMapping(value = "/uploadBEActionqqqqq", method = RequestMethod.POST)
	public ModelAndView uploadBEActionqqqqq(@RequestParam(value = "excelfile", required = false) MultipartFile excelfile,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws Exception {
		String nMsg = "";
		try {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("fp_be_upload", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				////return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			int countrow = 0;

			countrow = Integer.parseInt(request.getParameter("countrow"));
			String username = session.getAttribute("username").toString();
			String author = request.getParameter("author");
			String roleSusNo = session.getAttribute("roleSusNo").toString();

			if (author.length() == 0) {
				System.err.println("\n\n Metadata not found");
				throw new Exception("1. Permission denied to upload");
			}

			String[] auth = author.split("0fsxzjyg==");
			String dec_sus = "";

			if (auth.length != 2) {
				System.err.println("\n\n Metadata not proper");
				throw new Exception("2. Permission denied to upload");
			} else {
				dec_sus = HexaDao.decrypt(auth[0], auth[1], session);
				if (dec_sus.length() == 0) {
					System.err.println("\n\n SUS decrypting failed");
					throw new Exception("3. Permission denied to upload");
				} else if (!dec_sus.equalsIgnoreCase(roleSusNo)) {
					System.err.println("\n\n SUS decrypted not matched with rolesus");
					throw new Exception("4. Permission denied to upload");
				}
			}

			String fin_year = fp_trDao.nGetAdmFinYear("CFY");
			String upload_for = request.getParameter("fp_upldfor");
			Date date = new Date();
			Session session0 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx0 = session0.beginTransaction();
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query qry;

			String conc_flag = "Y";
			String app_flag = "Y";
			String submit_flag = "Y";

			String tr_head = "";

			String fr_sus_no = "";
			String dflag = "N";
			String head_code = "";
			String head_desc = "";
			String bg_sus = "";
			String bg_hlder_fmn = "";
			String bg_hlder = "";
			String prv_allot_s = "";
			String cur_proj_s = "";
			Double cur_allot_s = 0.0;
			Date upload_date;
			Date data_upd_date;
			String data_upd_by = "";
			String bg_holder_name = "";
			String code_head = "";

			String deno = request.getParameter("deno").toString();

			int denoFactor = 1;
			String cur_all = "";

			if (deno.length() == 0 || deno.equalsIgnoreCase("RS")) {
				denoFactor = 1;
			} else if (deno.equalsIgnoreCase("LC")) {
				denoFactor = (int) 1e+5;

			} else if (deno.equalsIgnoreCase("CR")) {
				denoFactor = (int) 1e+7;
			} else {
				denoFactor = 1;
				deno = "RS";
			}

			String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);

			if (!fp1_Dao.getCheckDataUpdation(conc_flag, app_flag, submit_flag)) {

				try {
					String retmsg= nChkData(fin_year+"_"+upload_for,"");
					if(retmsg.length()>0) {
						model.put("msg", retmsg);
						return new ModelAndView("redirect:fp_be_upload");
					}
					
					Query qry0 = session0.createSQLQuery(
							"delete from fp.fp_tmp_be_upload where fr_sus_no=:sus_no and fin_year=:fin_year and upload_for=:upload_for");
					qry0.setParameter("sus_no", roleSusNo);
					qry0.setParameter("fin_year", fin_year);
					qry0.setParameter("upload_for", upload_for);
					qry0.executeUpdate();
					String data1 ="";
					String data2 ="";
					for (int i = 1; i < countrow; i++) {
						
						bg_sus = request.getParameter("BG_SUS_" + i);
						tr_head = request.getParameter("TR_HEAD_" + i);
						fr_sus_no = request.getParameter("FR_SUS_NO_" + i);
						if (bg_sus != null && tr_head != null && tr_head.length() > 4) {
							dflag = request.getParameter("DFLAG_" + i);
							
							head_code = request.getParameter("HEAD_CODE_" + i);
							head_desc = "";

							bg_hlder_fmn = request.getParameter("BG_HLDER_FMN_" + i);
							bg_hlder = request.getParameter("BG_HLDER_" + i);
							prv_allot_s = request.getParameter("PRV_ALLOT_" + i);
							cur_proj_s = request.getParameter("CUR_PROJ_" + i);
							cur_all = request.getParameter("CUR_ALLOT_" + i);
							
							data1 = bg_sus+"_"+tr_head+"_"+dflag+"_"+head_code+"_"+bg_hlder_fmn+"_"+bg_hlder;
							data2 = prv_allot_s+"_"+cur_proj_s+"_"+cur_all;
							retmsg= nChkData(data1,data2);
							if(retmsg.length()>0) {
								throw new Exception(retmsg);
								//return new ModelAndView("redirect:fp_be_upload");
							}
							if (fp_trDao.checkBudgetHolderWithHead(fr_sus_no, bg_sus, tr_head)
									&& roleSusNo.equalsIgnoreCase(fr_sus_no)) {

								

								if (cur_all.length() == 0 || cur_all.equalsIgnoreCase(null)) {
									cur_all = "0";
								}

								cur_allot_s = Double.parseDouble(cur_all);
								upload_date = date;

								data_upd_date = date;
								data_upd_by = username;
								qry = session1.createSQLQuery("insert into fp.fp_tmp_be_upload "
										+ "(fr_sus_no,dflag,tr_head,head_code,head_desc,bg_sus,bg_hlder_fmn,bg_hlder,prv_allot,cur_proj,cur_allot,upload_for,prv_allot_s,cur_proj_s,cur_allot_s,data_upd_date,data_upd_by,upload_date,fin_year,upd_flag,conc_flag,app_flag,submit_flag,rs_fmt)"
										+ "values"
										+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24)");

								qry.setParameter("a1", roleSusNo);
								qry.setParameter("a2", dflag);
								qry.setParameter("a3", tr_head);
								qry.setParameter("a4", head_code);
								qry.setParameter("a5", head_desc);
								qry.setParameter("a6", bg_sus);
								qry.setParameter("a7", bg_hlder_fmn);
								qry.setParameter("a8", bg_hlder);

								qry.setParameter("a9", 0);
								qry.setParameter("a10", 0);
								qry.setParameter("a11", 0);
								qry.setParameter("a12", upload_for);
								qry.setParameter("a13", prv_allot_s);

								qry.setParameter("a14", cur_proj_s);
								qry.setParameter("a15", cur_allot_s.toString());
								qry.setParameter("a16", data_upd_date);
								qry.setParameter("a17", data_upd_by);
								qry.setParameter("a18", upload_date);
								qry.setParameter("a19", fin_year);
								qry.setParameter("a20", "N");

								if (nUnty.equalsIgnoreCase("0")) {
									qry.setParameter("a21", "Y");
								} else {
									qry.setParameter("a21", "N");
								}
								qry.setParameter("a22", "N");
								qry.setParameter("a23", "N");
								qry.setParameter("a24", deno);

								qry.executeUpdate();
							} else {
								System.err.println("\n\nBudget Head/Budget Holder not mapped."+fr_sus_no+","+ bg_sus+","+ tr_head);
								throw new Exception("Budget Head/Budget Holder not mapped "+tr_head);
							}
						} else if (dflag.equalsIgnoreCase("E")) {
							throw new Exception(
									"A calculation error found in excel file \nPlease amend the allocation and try again");
						}
					}
					tx0.commit();
					tx1.commit();
					nMsg = "First Level Excel Uploaded Successfully.\nPlease Proceed for Second Level Approval\n\nFP -> Allocation -> Approval of Upload";
				} catch (SQLException e) {
					nMsg = "Error - unable to perform action";
					tx0.rollback();
					tx1.rollback();
				} catch (Exception e) {
					
					if(e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1 || e.getMessage().indexOf("calculation") > -1) {
						nMsg = e.getMessage();
					}
					else
						nMsg = "Error -- unable to perform action";

					tx0.rollback();
					tx1.rollback();
				} finally {
					session0.close();
					session1.close();
				}
			} else
				nMsg = "Data already Exist";
		} catch (Exception e) {
			//nMsg = e.getMessage().equalsIgnoreCase("Permission denied to upload") ? e.getMessage(): "Error - unable to perform action";
			
			if(e.getMessage().indexOf("Permission") > -1 || e.getMessage().indexOf("Budget") > -1) {
				nMsg = e.getMessage();
			}
			else
				nMsg = "Error - unable to perform action";
		}
		model.put("msg", nMsg);
		return new ModelAndView("redirect:fp_be_upload");
	}

	public boolean validateBudgetHolderDtls(ArrayList<ArrayList<String>> ls, String parent_sus, String child_sus,
			String code_head) {
		boolean isExists = false;
		for (ArrayList<String> l : ls) {
			if (l.get(0).equalsIgnoreCase(parent_sus) && l.get(1).equalsIgnoreCase(child_sus)
					&& l.get(2).equalsIgnoreCase(code_head)) {
				isExists = true;
				break;
			}
		}
		return isExists;
	}

	@RequestMapping(value = "/getHeadPrefList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getHeadPrefList(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.getHeadPreflist("", s1, nPara);
		return list;
	}

	@RequestMapping(value = "/getfpHeadAssignList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getfpHeadAssignList(String nPara, HttpSession s1, String userid1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String usrid2 = s1.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_trDao.getHeadAssignPreflist("", s1, nPara, usrid2);
		return list;
	}

	@RequestMapping(value = "/getUnitPrefList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getUnitPrefList(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.getUnitPrefList("", s1, nPara);
		return list;
	}

	@RequestMapping(value = "/getfpUnitAssignList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getfpUnitAssignList(String nPara, String nPara1, HttpSession s1,
			String userid1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String usrid2 = s1.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_trDao.getUnitAssignPreflist("", s1, nPara, nPara1, usrid2);
		return list;
	}

	@RequestMapping(value = "/getAllFormationList", method = RequestMethod.POST) 
	public @ResponseBody List<String> getAllFormationList(String a1, String a2, HttpSession s1) {
		List<String> list = fp_trDao.getAllFormationList(a1, a2);
		if (list.size() != 0) {
			List<String> FList = fp1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getAllFormationListAuto", method = RequestMethod.POST) 
	public @ResponseBody List<String> getAllFormationListAuto(String a1, HttpSession s1) {
		List<String> list = fp_trDao.getAllFormationListAuto(s1.getAttribute("roleSusNo").toString());
		if (list.size() != 0) {
			List<String> FList = fp1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getSusIncrList", method = RequestMethod.POST) 
	public @ResponseBody List<String> getSusIncrList(String a1, HttpSession s1, String a2) {
		List<String> list = fp_trDao.getSusIncrList(a1, a2);
		if (list.size() != 0) {
			List<String> FList = fp1_Dao.getMNHEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getAdmFinYear", method = RequestMethod.POST) 
	public @ResponseBody String getAdmFinYear(String nPara, HttpSession s1) {
		String list = fp_trDao.nGetAdmFinYear(nPara);
		return list;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/cr_dft_fund_Action", method = RequestMethod.POST)
	public ModelAndView cr_dft_fund_Action(HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg)
			throws Exception {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_cr_dft_fund_allot", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int countrow = 0;
		countrow = Integer.parseInt(request.getParameter("dftlistsize"));
		List<String> indata = new ArrayList<String>();
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String fin_year = request.getParameter("fin_year");
		String upload_for = request.getParameter("est_type");
		String amtrs = request.getParameter("amt_rs");
		System.out.println("cr_dft_fund_Action-fin_year-"+fin_year);
		
		Date date = new Date();
		String nMsg = "";
		String inndata = "";
		int SqlInjCnt = 0;
		for (int i = 0; i < countrow; i++) {
			inndata = request.getParameter("ddftlist_" + i + "_1");
			indata.add(inndata);
			if (nFpInjKeys(inndata)) {
				SqlInjCnt++;
				break;
			}
		}
		if (SqlInjCnt == 0) {
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			Query qry;
			String conc_flag = "Y";
			String app_flag = "Y";
			String submit_flag = "Y";

			String tr_head = "";
			String fr_sus_no = "";
			String dflag = "N";
			String head_code = "";
			String head_desc = "";
			String bg_sus = "";
			String bg_hlder_fmn = "";
			String bg_hlder = "";
			String prv_allot_s = "";
			String cur_proj_s = "";
			String cur_allot_s = "";
			String cur_fund = "";
			Date upload_date;
			Date data_upd_date;
			String data_upd_by = "";
			String tr_head_s = "";
			String nUnty = fp_trDao.getUnitType("", session, "SUS_" + roleSusNo);

			Session sess = HibernateUtilNA.getSessionFactory().openSession();
			Transaction txn = sess.beginTransaction();
			String hql = "delete from fp.fp_tb_dft_allot where fr_sus_no=:fr_sus_no and fin_year=:fin_year and upload_for=:upload_for";								
		    Query query = sess.createSQLQuery(hql);							    
		    query.setString("fr_sus_no", roleSusNo);
		    query.setString("fin_year", fin_year);
		    query.setString("upload_for", upload_for);
		    int rowCount = query.executeUpdate();
		    txn.commit();
		    sess.close();
		    
			String data[];
			for (int i = 0; i < countrow; i++) {
				data = request.getParameter("ddftlist_" + i + "_1").toString().split("_");
				if (data.length > 0) {
					bg_sus = data[5];
					tr_head = data[0];
				}

				if (bg_sus != null && tr_head != null) {

					fr_sus_no = roleSusNo;
					dflag = "N";

					head_code = data[1];
					head_desc = data[2];

					bg_hlder_fmn = data[7];
					bg_hlder = data[6];
					try {
						prv_allot_s = data[3];
						prv_allot_s = prv_allot_s.replaceAll(",", "");
					} catch (NullPointerException n) {
						prv_allot_s = "0";
					}
					try {
						cur_proj_s = data[4];
						cur_proj_s = cur_proj_s.replaceAll(",", "");
					} catch (NullPointerException n) {
						cur_proj_s = "0";
					}
					try {
						cur_allot_s = request.getParameter("ddftlist_" + i + "_13");
						cur_allot_s = cur_allot_s.replaceAll(",", "");
					} catch (NullPointerException n) {
						cur_allot_s = "0";
					}
					if (!tr_head.equalsIgnoreCase(tr_head_s)) {
						try {
							cur_fund = data[8];
							cur_fund = cur_fund.replaceAll(",", "");
						} catch (NullPointerException n) {
							cur_fund = "0";
						}
						tr_head_s = tr_head;
					}

					upload_date = date;
					data_upd_date = date;
					data_upd_by = username;

					qry = session1.createSQLQuery("insert into fp.fp_tb_dft_allot "
							+ "(fr_sus_no,dflag,tr_head,head_code,head_desc,bg_sus,bg_hlder_fmn,bg_hlder,prv_allot,cur_proj,cur_allot,upload_for,prv_allot_s,cur_proj_s,cur_allot_s,data_upd_date,data_upd_by,upload_date,fin_year,upd_flag,app_flag,submit_flag,cur_fund_s,rs_fmt)"
							+ "values"
							+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a22,:a23,:a24,:a25)");

					qry.setParameter("a1", fr_sus_no);
					qry.setParameter("a2", dflag);
					qry.setParameter("a3", tr_head);
					qry.setParameter("a4", head_code);
					qry.setParameter("a5", head_desc);
					qry.setParameter("a6", bg_sus);
					qry.setParameter("a7", bg_hlder_fmn);
					qry.setParameter("a8", bg_hlder);

					qry.setParameter("a9", 0);
					qry.setParameter("a10", 0);
					qry.setParameter("a11", 0);

					qry.setParameter("a12", upload_for);
					qry.setParameter("a13", prv_allot_s);
					qry.setParameter("a14", cur_proj_s);
					qry.setParameter("a15", cur_allot_s);
					qry.setParameter("a16", data_upd_date);
					qry.setParameter("a17", data_upd_by);
					qry.setParameter("a18", upload_date);
					qry.setParameter("a19", fin_year);
					qry.setParameter("a20", "N");
					qry.setParameter("a22", "N");
					qry.setParameter("a23", "N");
					qry.setParameter("a24", cur_fund);
					qry.setParameter("a25", amtrs);
					qry.executeUpdate();
				}
			}
			tx.commit();
			session1.close();
			if (nUnty.equalsIgnoreCase("0")) {
				//nMsg = "Draft Data Uploaded Successfully.\nPlease Proceed for Concourance Approval to FP Dte.";
				nMsg = "Draft Data Created Successfully.\nPlease Proceed to Download Draft Fund Report\n\nAllocation -> Draft Fund Report.";
			} else {
				nMsg = "Draft Data Created Successfully.\nPlease Proceed to Download Draft Fund Report\n\nAllocation -> Draft Fund Report.";
			}
		} else {
			nMsg = "Data Cannot be Saved as It is Containing Some Malicious Script.\n\nPlease Re-generate / Allot and Submit Again...";
		}
		model.put("msg", nMsg);
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", session, "CFY");
		model.put("n_finyr", finli);
		model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype", fp1_Dao.FindDomainList("", session, "BETYPE:disp_order"));
		return new ModelAndView("fp_cr_dft_fund_allotTiles");
	}

	public static String getClientIpAddress(HttpServletRequest request) {
		String xForwardedForHeader = request.getHeader("X-Forwarded-For");
		if (xForwardedForHeader == null) {
			return request.getRemoteAddr();
		} else {
			return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
		}
	}

	@RequestMapping(value = "/bodyParameterNotAllow")
	public void bodyParameterNotAllow(HttpServletRequest request,HttpServletResponse response,ModelMap m) throws IOException {

		request.getSession().removeAttribute("JSESSIONID");
		request.getSession().removeAttribute("userId");
		request.getSession(false).invalidate();
		response.sendRedirect("login");
		
	}
	
	public Boolean nFpInjKeys(String nValue) {
		//String[] SqlInjKeys = { "'", "%", "\\","+", "||", "--", "/*", "script", "src", "eval",">", "<", "expression", "onload","&gt","&lt",";","~"};
		String[] SqlInjKeys = { "'", "%", "\\","+", "||", "--", "/*", "script", "src", "eval(",">", "<", "expression", "onload","&gt","&lt",";","~","*","^","#","iframe","embed","meta","applet","plugin","href"};
		String[] valArray = nValue.split("_");
		for (int i = 0; i < valArray.length; i++) {
			for (int j = 0; j < SqlInjKeys.length; j++) {
				if (valArray[i].toUpperCase().indexOf(SqlInjKeys[j].toUpperCase()) >= 0) {
					System.out.println("nFpInj - "+valArray[i].toUpperCase());
					return true;
				}
			}
		}
		return false;
	}
	
	public String nChkData(String strdata, String numdata) {
		
		//String no_pattern = "[0-9]*\\.?[0-9]*";
		//String no_pattern = "[0-9]*\\.?[0-9]*\\.?[0-9]*";
		String no_pattern = "[-]?[0-9]*\\.?[0-9]*";
		String ret = "";
		if (strdata.length() > 0) {
			//System.out.println("Str data - "+strdata);
			if (nFpInjKeys(strdata)) {
				ret = "Error - Invalid Input found. Process Terminated.";
				return ret;
			}
		}

		if (numdata.length() > 0) {
			String[] d1 = numdata.split("_");
			for (int k = 0; k < d1.length; k++) {
				if (!d1[k].matches(no_pattern)) {
					ret = "Error - Invalid Number found. Process Terminated.";
					break;
				}
			}
		}
		return ret;
	}

}