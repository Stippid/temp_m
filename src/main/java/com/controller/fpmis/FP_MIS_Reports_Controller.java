package com.controller.fpmis;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.fpmis.FP_MIS_MasterDAO;
import com.dao.fpmis.FP_MIS_ReportsDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.fpmis.FP_MIS_Master_Model;
import com.models.fpmis.fp_tb_proj_detl_model;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FP_MIS_Reports_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;

	@Autowired
	private FP_MIS_ReportsDAO fp_rptDao;

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	FP_MIS_Common_Controller mcommon = new FP_MIS_Common_Controller();

	@RequestMapping(value = "/fp_fund_status", method = RequestMethod.GET)
	public ModelAndView fp_fund_status(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();        
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();

		Boolean val = roledao.ScreenRedirect("fp_fund_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");         
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(rolesus.isEmpty()) {
			Mmap.put("msg", "SUS No not updated \nPlease contact MISO Helpdesk");
			return new ModelAndView("fp_fund_status_viewTiles");
		}
		
		String nPara = "";
		String nUnty = null;
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);

		String nParaValue = "NIL";
		String domainid = "";
		Mmap.put("n_2", nParaValue);
		Mmap.put("n_3", domainid);
		Mmap.put("n_4", nPara);
		if (!nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		} else {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		}

		Mmap.put("n_unt", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		Mmap.put("n_head", fp_trDao.getHeadlist("", sessionA, "2076"));
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", cfinli);
		if (nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_1", fp_rptDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "UT", nPara, "4", usrid,"RS"));
			Mmap.put("n_sel", cfinli + ":UT:" + nPara + ":4:" + usrid+":RS");
		} else {

			if (Integer.parseInt(nUnty) <= 1) {
				Mmap.put("n_1", fp_rptDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "1", usrid,"CR"));
				Mmap.put("n_sel", cfinli + ":HQ:" + nPara + ":1:" + usrid+":CR");
			} else {
				Mmap.put("n_1", fp_rptDao.nFundStatusHQHead(sessionA, cfinli, "", "HEADDT", "HQ", nPara, "4", usrid,"RS"));
				Mmap.put("n_sel", cfinli + ":HQ:" + nPara + ":4:" + usrid+":RS");
			}
		}
		Mmap.put("msg", msg);
		return new ModelAndView("fp_fund_status_viewTiles");
	}

	@RequestMapping(value = {"/fp_fund_recd", "/admin/fp_fund_recd"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView fp_fund_recd(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_fund_recd", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String nPara = "";
		String nUnty = null;
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		String nParaValue = "";
		String domainid = "";
		Mmap.put("n_2", nParaValue);
		Mmap.put("n_3", domainid);
		Mmap.put("n_4", nPara);
		if (!nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_hq", fp_trDao.getunitlist("", sessionA, "HQ_" + nPara));
		}
		Mmap.put("n_unt", fp_trDao.getunitlist("", sessionA, "UNIT_" + nPara));
		Mmap.put("n_head", fp_trDao.getHeadlist("", sessionA, nUnty));

		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "CFY");
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));

		Mmap.put("list", fp_trDao.FindFundList("", sessionA, rolesus, fp_trDao.nGetAdmFinYear("CFY")));

		Mmap.put("msg", msg);
		return new ModelAndView("fp_fund_recdTiles");
	}

	@RequestMapping(value = "/getFundYrList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getFundYrList(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		/*ArrayList<ArrayList<String>> list = fp_trDao.getFundYrList("", s1, nPara);*/	
		String rolesus = s1.getAttribute("roleSusNo").toString();
		ArrayList<ArrayList<String>> list = fp_trDao.FindFundList("", s1, rolesus, nPara);		
		return list;
	}

	@RequestMapping(value = "/admin/fp_fund_status_flt", method = RequestMethod.POST)
	public ModelAndView fp_fund_status_flt(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String m1_tryear,
			String m1_nomen, String m1_nomenin, String m1_slvl, String m1_lvl, String m1_hdlvl, String m1_rsfmt,ModelMap model,
			HttpSession sessionA, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String usrid = sessionA.getAttribute("username").toString();
		int userid = (Integer) sessionA.getAttribute("userId");
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String nPara = "";
		String nUnty = "";

		Boolean val = roledao.ScreenRedirect("fp_fund_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);

		String hqLvl[] = m1_lvl.split("_");

		if (rolefmn.equals("") || rolefmn.equals(null) || rolefmn.equals("null")) {
			model.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
			model.put("n_unt", fp_trDao.getunitlist("", sessionA, "UNIT_" + nPara));

		} else {
			model.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
			model.put("n_unt", fp_trDao.getunitlist("", sessionA, "UNIT_" + nPara));
		}
		model.put("n_4", nPara);
		model.put("n_head", fp_trDao.getHeadlist("", sessionA, nUnty));
		model.put("n_1", fp_rptDao.nFundStatusHQHead(sessionA, m1_tryear, m1_nomen, m1_nomenin, m1_slvl, m1_lvl,
				m1_hdlvl, usrid,m1_rsfmt));
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		model.put("n_finyr", finli);        
		model.put("n_cfinyr", cfinli);
		model.put("n_sel", m1_tryear + ":" + m1_slvl + ":" + m1_lvl + ":" + m1_hdlvl + ":" + usrid+ ":" + m1_rsfmt);
		return new ModelAndView("fp_fund_status_viewTiles");
	}
    
	@RequestMapping(value = "/admin/fp_dft_exl_rpt", method = RequestMethod.GET)
	public ModelAndView fp_dft_exl_rpt(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String m1_tryear,
			String m1_nomen, String m1_nomenin, String m1_slvl, String m1_lvl, String m1_hdlvl, ModelMap model,
			HttpSession sessionA, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String usrid = sessionA.getAttribute("username").toString();
		int userid = (Integer) sessionA.getAttribute("userId");
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		String nPara = "";
		String nUnty = fp_trDao.getUnitType("", sessionA, "FMN_" + rolefmn);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		m1_lvl = nPara;
		Boolean val = roledao.ScreenRedirect("fp_dft_exl_rpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "CFY"));
		model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype", fp1_Dao.FindDomainList("", sessionA, "BETYPE:disp_order"));
		model.put("n_sel", m1_tryear);
		return new ModelAndView("fp_dft_exl_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_fund_be_rpt", method = RequestMethod.GET)
	public ModelAndView fp_fund_be_rpt(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String m1_tryear,
			String m1_nomen, String m1_nomenin, String m1_slvl, String m1_lvl, String m1_hdlvl, ModelMap model,
			HttpSession sessionA, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String usrid = sessionA.getAttribute("username").toString();
		int userid = (Integer) sessionA.getAttribute("userId");
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		String nPara = "";
/*		String nUnty = fp_trDao.getUnitType("", sessionA, "FMN_" + rolefmn);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
*/		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		Boolean val = roledao.ScreenRedirect("fp_fund_be_rpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype", fp1_Dao.FindDomainList("", sessionA, "BETYPE:disp_order"));
		model.put("n_sel", m1_tryear);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_fund_tfr_confirm", method = RequestMethod.POST)
	public ModelAndView fp_fund_tfr_confirm(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String n1_trhead,
			String n1_frunt, String n1_blamt, String n1_tramt, String n1_tount, String n1_tohead, String n1_trtype,
			String n1_tryear, String n1_trrem, String n1_hdsumm, String n1_trpid, String n1_slvl, String n1_lvl,
			String n1_hdlvl, String nFlowControl, String n1_trAltType, String n1_gstval, String n1_exptype,String n1_rsfmt,
			ModelMap model, HttpSession sessionA, HttpServletRequest request) {
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		Boolean val = roledao.ScreenRedirect("fp_fund_status", roleid);
		System.out.println("-->"+n1_exptype);
		
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int yr11 = Year.now().getValue();
		String yr1 = n1_tryear;
		int yr12 = Integer.parseInt(yr1);
		String nUnty = null;
		if (rolefmn.equals("") || rolefmn.equals(null) || rolefmn.equals("null")) {
			nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		} else {
			nUnty = fp_trDao.getUnitType("", sessionA, "FMN_" + rolefmn);
		}
		String nPara = "";
		
		/*try {
			ArrayList<ArrayList<String>> li0 = new ArrayList<ArrayList<String>>();
			li0=fp_trDao.getUnitPrefDetl("", sessionA, "SUS_" + rolesus+"_"+n1_trhead);
			ArrayList<ArrayList<String>> li01 = new ArrayList<ArrayList<String>>();
			li01 = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + li0.get(0).get(1));
			rolefmn = li01.get(0).get(0);
			n1_frunt=li01.get(0).get(0)+"_"+li0.get(0).get(1);
			System.out.println("--From Fmn-"+n1_frunt+","+n1_tount);
		} catch (Exception e) {
			
		}*/
		
		//nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		if (yr1.length() == 0 || yr1.equalsIgnoreCase(null)) {
			model.put("msg", "Please Select the Year.");
			return new ModelAndView("redirect:fp_fund_recd");
		}
		if (yr12 > yr11) {
			model.put("msg", "Future Year cannot be allowed.");
			return new ModelAndView("redirect:fp_fund_recd");
		}
		if (yr1.length() < 4) {
			model.put("msg", "Year Format Require in YYYY.");
			return new ModelAndView("redirect:fp_fund_recd");
		}

		if (n1_tramt.equals("") || n1_tramt.equals(null)) {
			model.put("msg", "Amount can not be 0.");
			return new ModelAndView("redirect:fp_fund_recd");
		}

		String data1 = n1_trhead + "_" + n1_frunt + "_" + n1_tohead + "_" + n1_trtype + "_" + n1_trrem + "_" + n1_trpid
				+ "_" + n1_trAltType + "_" + n1_tount+"_"+n1_exptype;
		String data2 = n1_blamt + "_" + n1_tramt + "_" + n1_tryear;
		String retmsg = mcommon.nChkData(data1, data2);

		if (retmsg.length() > 0) {
			model.put("msg", retmsg);
			return new ModelAndView("redirect:fp_fund_recd");
		} else {

			String nRet = fp_trDao.fp_fund_tfr_confirm(n1_trhead, n1_frunt, n1_blamt, n1_tramt, n1_tount, n1_tohead,
					n1_trtype, n1_tryear, n1_trrem, n1_trpid, usrid, n1_trAltType, n1_exptype,"0");
			String[] n1gstval = null;
			
			String expid=fp_trDao.fp_fund_tfr_confirm_id(n1_trhead,n1_tramt,n1_trtype,n1_tryear);
			n1_trpid =expid;
			if (n1_gstval.replaceAll(":", "").length() > 0) {
				n1gstval = n1_gstval.split(":");
				if (Double.parseDouble(n1gstval[0]) > 0) {
					nRet = fp_trDao.fp_fund_tfr_confirm(n1_trhead, n1_frunt, n1_blamt, n1gstval[0], n1_tount, n1_tohead,
							"GST", n1_tryear, n1_trrem, n1_trpid, usrid, n1_trAltType, n1_exptype,expid);
				}
				if (Double.parseDouble(n1gstval[1]) > 0) {
					nRet = fp_trDao.fp_fund_tfr_confirm(n1_trhead, n1_frunt, n1_blamt, n1gstval[1], n1_tount, n1_tohead,
							"EDT", n1_tryear, n1_trrem, n1_trpid, usrid, n1_trAltType, n1_exptype,expid);
				}
				if (Double.parseDouble(n1gstval[2]) > 0) {
					nRet = fp_trDao.fp_fund_tfr_confirm(n1_trhead, n1_frunt, n1_blamt, n1gstval[2], n1_tount, n1_tohead,
							"OTH", n1_tryear, n1_trrem, n1_trpid, usrid, n1_trAltType, n1_exptype,expid);
				}
			}

			if (nRet.equals("1")) {
				msg = "Transaction Recorded Sucessfully.";
			} else {
				msg = "Transaction Declained.";
			}
			model.put("msg", msg);
		}

		if (n1_trtype.equals("FND")) {
			return new ModelAndView("redirect:fp_fund_recd");
		} else {
			if (!nUnty.equalsIgnoreCase("5")) {
				model.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "HQ_" + nPara));
			} else {
				model.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
			}
			model.put("n_4", nPara);
			model.put("n_unt", fp_trDao.getunitBuglist("", sessionA, "UNIT_" + nPara));
			model.put("n_head", fp_trDao.getHeadlist("", sessionA, "2076"));
			model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, ""));
			/*model.put("n_sel", n1_tryear + ":" + n1_slvl + ":" + n1_lvl + ":" + n1_hdlvl + ":" + usrid);*/
			model.put("n_sel", n1_tryear + ":" + n1_slvl + ":" + n1_lvl + ":" + n1_hdlvl + ":" + usrid+ ":" + n1_rsfmt);
			model.put("n_1",
					fp_rptDao.nFundStatusHQHead(sessionA, n1_tryear, "", "HEADDT", n1_slvl, n1_lvl, n1_hdlvl, usrid,n1_rsfmt));
			return new ModelAndView("fp_fund_status_viewTiles");
		}
	}

	@RequestMapping(value = "/nFundInDetl", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nFundInDetl(String nPara, HttpSession sessionA) {
		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_rptDao.nFundInDetlTr(nPara, usrid, sessionA);
		return list;
	}

	@RequestMapping(value = "/nFundInfoDBReport", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nFundInfoDBReport(String rptName, String bhllst, String bhdlst, String coln,
			String dspty, String rptagr, String rolesus, String cfy, HttpSession sessionA) {
		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = null;
		if (!coln.equalsIgnoreCase("null")) {
			list = fp_rptDao.nFundInfoDBReport(rptName, bhllst, bhdlst, coln, dspty, rptagr, rolesus, cfy, sessionA);
		}
		return list;
	}

	@RequestMapping(value = "/nFundInfoDBDetl", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nFundInfoDBDetl(String nPara, String rolesus, String cfy,
			HttpSession sessionA) {
		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_rptDao.nFundInfoDBDetl(nPara, usrid, rolesus, cfy, sessionA);
		return list;
	}
	
	@RequestMapping(value = "/nFundInfoDBDetl_n", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_n(String nPara, String rolesus, String cfy,
			HttpSession sessionA) {
		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_rptDao.nFundInfoDBDetl_n(nPara, usrid, rolesus, cfy, sessionA);
		return list;
	}

	@RequestMapping(value = "/nFundInDetlTr", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nFundInDetlTr(String nPara, HttpSession sessionA) {
		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_rptDao.nFundInDetl(nPara, usrid);
		return list;
	}

	@RequestMapping(value = "/admin/fp_Budg_status_flt", method = RequestMethod.POST)
	public ModelAndView fp_Budg_status_flt(
			@ModelAttribute("m1_nomen") @RequestParam(value = "msg", required = false) String msg, String m1_tryear,
			String m1_lvl, String m1_rsfmt, ModelMap model, HttpSession sessionA, HttpServletRequest request) {
		String m1_slvl = "";
		String nRpt = "4:" + m1_lvl;
		String roleid = sessionA.getAttribute("roleid").toString();
		String usrid = sessionA.getAttribute("username").toString();
		int userid = (Integer) sessionA.getAttribute("userId");
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		String nPara = "";
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		m1_lvl = nPara;
		Boolean val = roledao.ScreenRedirect("fp_fund_be_rpt", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String hqLvl[] = m1_lvl.split("_");

		model.put("n_1", fp_rptDao.nFundDraftPrint(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype", fp1_Dao.FindDomainList("", sessionA, "BETYPE:disp_order"));
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + usrid+":"+m1_rsfmt);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_rpt_fund_recv", method = RequestMethod.POST)
	public ModelAndView fp_rpt_fund_recv(
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
/*		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
*/		
		
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		
		String hqLvl[] = m1_lvl.split("_");

		model.put("n_1", fp_rptDao.nFundRecv(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype",n_rpttype);
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + m1_rsfmt);      
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_rpt_fund_allot", method = RequestMethod.POST)
	public ModelAndView fp_rpt_fund_allot(
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
/*		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
*/		
		
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		
		String hqLvl[] = m1_lvl.split("_");

		model.put("n_1", fp_rptDao.nFundAllot(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype",n_rpttype);
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + m1_rsfmt);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_rpt_fund_flow", method = RequestMethod.POST)
	public ModelAndView fp_rpt_fund_flow(
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
/*		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
*/		
		
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		
		String hqLvl[] = m1_lvl.split("_");

		model.put("n_1", fp_rptDao.nFundFlow(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		
		/*model.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_5_X00000000_" + rolesus));*/
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype",n_rpttype);
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + m1_rsfmt);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/admin/fp_rpt_fund_tree", method = RequestMethod.POST)
	public ModelAndView fp_rpt_fund_tree(
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
/*		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
*/		
		
		String nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		
		m1_lvl = nPara;
		
		String hqLvl[] = m1_lvl.split("_");

		model.put("n_1", fp_rptDao.nFundTree(m1_tryear, nPara, nRpt, usrid, "BHDSUMMM", sessionA, m1_rsfmt));
		
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "D"));
		model.put("n_curyr", fp_trDao.nGetAdmFinYear("CFY"));
		model.put("n_rpttype",n_rpttype);
		model.put("n_sel", m1_tryear + ":" + nRpt + ":" + m1_rsfmt);
		return new ModelAndView("fp_fund_BE_rptTiles");
	}

	@RequestMapping(value = "/fp_fund_projection", method = RequestMethod.GET)
	public ModelAndView fp_fund_projection(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_fund_projection", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Boolean hasWindowOpened = fp1_Dao.isWindowOpened("PRJ");

		if (!hasWindowOpened) {
			msg = "Projection Window closed for the period";
			Mmap.put("msg1", msg);
			Mmap.put("cur_sus", "CLOSED");
			//return new ModelAndView("WindowClosedTiles");
		} else {
			Mmap.put("n_head", fp_trDao.getHeadBuglist("", sessionA, rolesus));
			Mmap.put("cur_sus", rolesus);
			String prjwin = "CPY";
			Mmap.put("n_finyr", getAdmFinYear1(prjwin, sessionA));
			Mmap.put("list", fp_trDao.FindProjList("", sessionA, rolesus));
	
			String nUnty = null;
			String nPara = "";
			/*nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
			nPara = nUnty + "_" + rolefmn + "_" + rolesus;
			*/
			ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
			li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
			rolefmn = li.get(0).get(0);
			nUnty= li.get(0).get(3);
			nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
			
			if (!nUnty.equalsIgnoreCase("5")) {
				Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "HQ_" + nPara));
			} else {
				Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
			}
			Mmap.put("n_unt", fp_trDao.getunitlist("", sessionA, "UNIT_" + nPara));
			Mmap.put("msg", msg);
		}
		return new ModelAndView("fp_fund_projectionTiles");

	}

	@RequestMapping(value = "/getAdmFinYear1", method = RequestMethod.POST)
	public @ResponseBody String getAdmFinYear1(String nPara, HttpSession s1) {
		String list = fp_trDao.nGetAdmFinYear(nPara);
		return list;
	}

	@RequestMapping(value = "/fp_create_projection", method = RequestMethod.POST)
	public ModelAndView fp_create_projection(HttpServletRequest request, ModelMap Mmap, HttpSession sessionA) {

		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();

		String msg;
		Boolean val = roledao.ScreenRedirect("fp_fund_projection", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String remarks1 = request.getParameter("remarks");
		String amt1 = request.getParameter("amount");
		String trHead = request.getParameter("tr_head");

		Boolean hasWindowOpened = fp1_Dao.isWindowOpened("PRJ");
		try {
			String data1 = trHead + "_" + remarks1 + "_" + amt1;
			String data2 = "_";
			String retmsg = mcommon.nChkData(data1, data2);

			if (retmsg.length() > 0) {
				Mmap.put("msg", retmsg);
				return new ModelAndView("redirect:fp_fund_projection");
			} else {
				if (!hasWindowOpened) {
					msg = "Projection Window closed for the period";
					Mmap.put("msg", msg);
					return new ModelAndView("WindowClosedTiles");
				}
				
				Float projAmt = Float.parseFloat(request.getParameter("amount"));

				if (projAmt < 1 || projAmt.equals(null)) {
					Mmap.put("msg", "Invalid Projection Amount");
					return new ModelAndView("redirect:fp_fund_projection");
				}
				if (trHead.equalsIgnoreCase("NIL") || trHead.equalsIgnoreCase(null) || trHead.equalsIgnoreCase("")) {
					Mmap.put("msg", "Please select valid code head");
					return new ModelAndView("redirect:fp_fund_projection");
				}
				if (!fp_trDao.checkHeadBuglist(rolesus, trHead)) {
					Mmap.put("msg", "Please select valid code head");
					return new ModelAndView("redirect:fp_fund_projection");
				}

				if (request.getParameter("sus_no").length() == 0
						|| request.getParameter("sus_no").equalsIgnoreCase(null)) {
					Mmap.put("msg", "Please Select the Est.");
					return new ModelAndView("redirect:fp_fund_projection");
				}
				if (request.getParameter("fin_year").length() == 0
						|| request.getParameter("fin_year").equalsIgnoreCase(null)) {
					Mmap.put("msg", "Please Select the Year.");
					return new ModelAndView("redirect:fp_fund_projection");
				}

				String nPara = "";
				String nUnty = null;
				if (rolefmn.equals("") || rolefmn.equals(null) || rolefmn.equals("null")) {
					nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
				} else {
					nUnty = fp_trDao.getUnitType("", sessionA, "FMN_" + rolefmn);
				}
				nPara = nUnty + "_" + rolefmn + "_" + rolesus;
				ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
				if (nUnty.equals("5")) {
					li = fp_trDao.getunitlist("", sessionA, "SUS_" + nPara);
					rolefmn = li.get(0).get(0);
				}

				fp_tb_proj_detl_model m = new fp_tb_proj_detl_model();

				m.setSus_no(rolesus);
				m.setForm_code_control(rolefmn);
				m.setData_upd_by(usrid);
				m.setTr_head(trHead);
				m.setAmount(projAmt);
				m.setRemarks(remarks1);
				msg = fp1_Dao.createProjection(m) ? "Data Saved Successfully" : "Error - Unable to Save Record";
				Mmap.put("msg", msg);
				return new ModelAndView("redirect:fp_fund_projection");
			}
		} catch (Exception e) {
			Mmap.put("msg", "Invalid Data Found.");
			return new ModelAndView("redirect:fp_fund_projection");
		}
	}

	@RequestMapping(value = "/fp_check_projection", method = RequestMethod.POST)
	public @ResponseBody boolean fp_check_projection(String tr_head, HttpSession session) {
		String rolesus = session.getAttribute("roleSusNo").toString();
		return fp1_Dao.checkProjectionExists(rolesus, "PRJ", tr_head);
	}

	@RequestMapping(value = "/admin/fp_submit_allot", method = RequestMethod.POST)
	public ModelAndView fp_submit_allot(
			@ModelAttribute("service3") @RequestParam(value = "msg", required = false) String msg, String paras,
			String est_types, String fin_years, ModelMap model, HttpSession sessionA, HttpServletRequest request) {
		String rolesus = sessionA.getAttribute("roleSusNo").toString();

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_upload_app", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int a = fp_rptDao.nUploadSubmit(sessionA, paras, est_types, fin_years);
		if (a > 0) {
			msg = "Funds Allocated Succesfully";
		}
		model.put("msg", msg);
		model.put("n_rpttype", fp1_Dao.FindDomainList("", sessionA, "BETYPE:disp_order"));
		model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "CFY"));
		model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		return new ModelAndView("fp_upload_appTiles");
	}

	@RequestMapping(value = "/nGetAlertMsg", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> nGetAlertMsg(String nPara, HttpSession sessionA) {

		String usrid = sessionA.getAttribute("username").toString();
		ArrayList<ArrayList<String>> list = fp_rptDao.nGetAlertMsg(nPara, usrid);

		return list;
	}

	@RequestMapping(value = "/getProjectionList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getProjectionList(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.getProjectionList("", s1, nPara);

		return list;
	}

	@RequestMapping(value = "/getHeadBuglist", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<ArrayList<String>> getHeadBuglist(String nPara, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		ArrayList<ArrayList<String>> list = fp_trDao.getHeadBuglist("", s1, nPara);
		return list;
	}

	@RequestMapping(value = "/admin/fp_exp_cont_bill", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView fp_exp_cont_bill(
			@ModelAttribute("service3") @RequestParam(value = "msg", required = false) String msg, String paras,
			String est_types, String fin_years, ModelMap model, HttpSession sessionA, HttpServletRequest request) {
		String rolesus = sessionA.getAttribute("roleSusNo").toString();

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("fp_exp_cont_bill", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// //return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		model.put("msg", msg);
		model.put("nHeads", fp_trDao.getHeadBuglist("", sessionA, rolesus));
		//model.put("n_finyr", fp_trDao.FindFinYear("", sessionA, "CFY"));
		//model.put("n_cfinyr", fp_trDao.nGetAdmFinYear("CFY"));
		return new ModelAndView("fp_exp_cont_billTiles");
	}
}