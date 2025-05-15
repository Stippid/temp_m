package com.controller.EmolumentsController;


import java.io.ObjectInputStream.GetField;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.ParseDouble;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.Emoluments.Emoluments_DAO;
import com.dao.Emoluments.emolumentDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.search_Emoluments_wise_dataDao;
import com.models.Emoluments.TB_PSG_EMOLUMENT_CHILD_OFFCR;
import com.models.Emoluments.TB_PSG_EMOLUMENT_PARENT_OFFCR;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Jco_Transaction.TB_RE_CALL;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_SIBLINGS;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class EmolumentsController {

	@Autowired
	private Emoluments_DAO tm_dao;
	
	@Autowired
	private emolumentDAO edao;
	
	@Autowired
	private search_Emoluments_wise_dataDao cd;
	PsgDashboardController das = new PsgDashboardController();
	
	
	
	Psg_CommonController p_comm = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	

	
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	Psg_CommonController mcommon = new Psg_CommonController();

	@RequestMapping(value = "/admin/Emolument", method = RequestMethod.GET)
	public ModelAndView Emolument(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, HttpServletResponse response) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Emolument", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		Mmap.put("getAgencyList", mcommon.getAgencyList());
		Mmap.put("msg", msg);
		return new ModelAndView("Emolument_Tiles");

	}
 
	
	@RequestMapping(value = "/Emolument_Action", method = RequestMethod.POST)
	public ModelAndView Emolument_Action(@ModelAttribute("Emolument_CMD") TB_PSG_EMOLUMENT_PARENT_OFFCR EP,
			BindingResult result, HttpServletRequest request, ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Emolument", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = EP.getId() > 0 ? EP.getId() : 0;
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String username = session.getAttribute("username").toString();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
 		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		 
		String count_hidden1 = request.getParameter("count_hidden");
		String dt_of_casuality = request.getParameter("dt_of_casuality");
		
		int count_hidden = 1;
		if (count_hidden1 != null && count_hidden1 != "") {
			count_hidden = Integer.parseInt(count_hidden1);
		}
		 
		try {
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_EMOLUMENT_PARENT_OFFCR where id !=:id");
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();
//			if (id == 0) {

//				if (c == 0) {
			for (int j = 1; j <= count_hidden; j++) {
				
				String agency_id = request.getParameter("agency_id"+j);
				String type_of_benefits_id = request.getParameter("type_of_benefits_id"+j);
				String reason = request.getParameter("reason"+j);
				String updated_as_on = request.getParameter("updated_as_on"+j);
				
				String amount_rel = request.getParameter("amount_release"+j);
				String amount_rel_v = amount_rel.replace(",","");
				
				String amount_due = request.getParameter("amount_due"+j);
				String amount_due_v = amount_due.replace(",","");
				
				Date c_dt = null;
				Date u_dt = null;
				
//				String dt_of_casuality = request.getParameter("dt_of_casuality"+j);
				/*
				 * if (dt_of_casuality != null && !dt_of_casuality.trim().equals("") &&
				 * !dt_of_casuality.equals("DD/MM/YYYY")) { c_dt =
				 * format.parse(dt_of_casuality); }
				 */
			
//				String update_dt = request.getParameter("updated_as_on");
//				
//				if (update_dt != null && !update_dt.trim().equals("") && !update_dt.equals("DD/MM/YYYY")) {
//					u_dt = format.parse(update_dt);
//				}
//				 
				
						EP.setCreated_by(username);
						EP.setCreated_dt(new Date());
						EP.setComm_id(comm_id);
						if(agency_id != null) {
							EP.setAgency_id(Integer.parseInt(agency_id));	
						}
						 
 						if(type_of_benefits_id != null) {
 						EP.setType_of_benefits_id(Integer.parseInt(type_of_benefits_id));
 						}
 						EP.setAmount_rel(amount_rel_v);
 						EP.setAmount_rel_v(amount_rel);
 						if(amount_due != null) {
 						EP.setAmount_due(amount_due_v); 
 						EP.setAmount_due_v(amount_due);
 						}
 						EP.setReason(reason);
 						EP.setUpdated_as_on(updated_as_on);
 						EP.setStatus(0);
 						EP.setClose_status(-1);
 						//EP.setDt_of_casulity(c_dt);
						sessionHQL.save(EP);
						
						
						
						TB_PSG_EMOLUMENT_CHILD_OFFCR EC = new TB_PSG_EMOLUMENT_CHILD_OFFCR();
						String amt_rem ="0";
					 		EC.setComm_id(comm_id);
					 		if(amount_rel != null && amount_rel !="null" && amount_rel !="") {
					 		double rem =Double.parseDouble(EP.getAmount_due() ) - Double.parseDouble(EP.getAmount_rel() );
					 		 amt_rem =String.valueOf(rem);
							EC.setAmount_release(amount_rel_v);
							EC.setAmount_rel_v(amount_rel);
					 		}
							// EC.setUpdated_as_on(u_dt);
							EC.setCreated_by(username);
							EC.setCreated_dt(new Date());
 							EC.setReason(reason);
 							EC.setStatus(0);
 							EC.setP_id(EP.getId());
 							EC.setAmount_rem(amt_rem);
							sessionHQL.save(EC);
							sessionHQL.flush();
							sessionHQL.clear();
						}

					Mmap.put("msg", "Data Saved Successfully.");
//				} 
//			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				Mmap.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Emolument");
	}
	
	
	
	@RequestMapping(value = "/Search_Emoluments", method = RequestMethod.GET)
	public ModelAndView Search_Emoluments(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
//		String roleid = session.getAttribute("roleid").toString();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//		}
//		if (roleAccess.equals("Unit")) {
//			Mmap.put("sus_no", roleSusNo);
//			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
//		}
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		//Mmap.put("list", tm_dao.getsearch_Emolument("", "0", "0", "", "", roleSusNo, roleid));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("search_EmolumentsTiles");
	}
	
	@RequestMapping(value = "/admin/getsearch_Emolument", method = RequestMethod.POST)
	public ModelAndView getsearch_Emolument(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no1,
			@RequestParam(value = "cname1", required = false) String cname1,
			@RequestParam(value = "rank1", required = false) String rank1,
			@RequestParam(value = "from_date1", required = false) String from_date1,
			@RequestParam(value = "ini_status1", required = false) String ini_status1,
			@RequestParam(value = "on_status1", required = false) String on_status1,
			@RequestParam(value = "scase1", required = false) String scase1,
			@RequestParam(value = "cancel_status1", required = false) String cancel_status1,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {
	 	
		String roleType = session.getAttribute("roleType").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
//		String roleid = session.getAttribute("roleid").toString();
		String roleid = session.getAttribute("roleid").toString();
		
		Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("list", tm_dao.getsearch_Emolument(personnel_no1, cname1,rank1, from_date1, ini_status1, on_status1,scase1,cancel_status1,cr_by,cr_date,roleType)); 
		Mmap.put("personnel_no1", personnel_no1);
		Mmap.put("cname1", cname1);
		Mmap.put("rank1", rank1);
		Mmap.put("from_date1", from_date1);
		Mmap.put("ini_status1", ini_status1);
		Mmap.put("on_status1", on_status1);
		Mmap.put("cancel_status1", cancel_status1);
		Mmap.put("scase1", scase1);
		//Mmap.put("list", list);
		Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		Mmap.put("cr_date1", cr_date);
		Mmap.put("cr_by1", cr_by);
		return new ModelAndView("search_EmolumentsTiles");
	}
	///EDIT 
	///EDIT 
		@RequestMapping(value = "/Edit_Emoluments",method = RequestMethod.POST )
		public ModelAndView Edit_Emoluments(@ModelAttribute("updateid") BigInteger comm_id, ModelMap model, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
		   
			String roleid = sessionEdit.getAttribute("roleid").toString();
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
//			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			
			
		     List<ArrayList<String>> list = tm_dao.Get_EmoulData(comm_id);
			  ArrayList<ArrayList<String>> alist =tm_dao.getm_emu_detailsDataListEdit(comm_id);
			model.put("list", list);
			model.put("getAgencyList", mcommon.getAgencyList());
		 	model.put("roleid", roleid);
		 	model.put("comm_id", comm_id);
			model.put("alist", alist);
			String roleType = sessionEdit.getAttribute("roleType").toString();
			model.put("roleType", roleType);
			
			 model.put("msg", msg);
			 return new ModelAndView("edit_EmolumentsTiles");
		}
		
	
//	@RequestMapping(value = "/admin/getm_emu_detailsData", method = RequestMethod.POST)
//
//	public @ResponseBody List<TB_PSG_EMOLUMENT_PARENT_OFFCR> getm_emu_detailsData(ModelMap Mmap, HttpSession session,
//
//			HttpServletRequest request) throws ParseException {
//
//		String msg = "";
//
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//
//		Transaction tx = sessionHQL.beginTransaction();
//
//		int comm_id = Integer.parseInt(request.getParameter("comm_id"));
//
//		String hqlUpdate = " from TB_PSG_EMOLUMENT_PARENT_OFFCR where   comm_id=:comm_id order by id ";
//
//		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("comm_id", comm_id);
//
//
//		List<TB_PSG_EMOLUMENT_PARENT_OFFCR> list = (List<TB_PSG_EMOLUMENT_PARENT_OFFCR>) query.list();
//
//		tx.commit();

//		sessionHQL.close();
// 
//		return list;
//
//	}
	
		
		
		 @RequestMapping(value = "/admin/getm_emu_detailsData", method = RequestMethod.POST)

			public @ResponseBody ArrayList<ArrayList<String>> getm_emu_detailsDatac(ModelMap Mmap, HttpSession session,

					HttpServletRequest request) throws ParseException {

			 		BigInteger comm_id =  new BigInteger(request.getParameter("comm_id"));
				
			 	  ArrayList<ArrayList<String>> alist =tm_dao.getm_emu_detailsDataListEdit_childs(comm_id);

		 
				return alist;

			}
	
	@RequestMapping(value = "/admin/App_getm_emu_detailsData", method = RequestMethod.POST)

	public @ResponseBody ArrayList<ArrayList<String>> App_getm_emu_detailsData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {
//		String msg = "";
//
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//
//		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String c_status = request.getParameter("c_status");
		String i_status = request.getParameter("i_status");
		
		
		
		
		  ArrayList<ArrayList<String>> alist =tm_dao.App_getm_emu_detailsData_list(comm_id,c_status,i_status);
		

//		String hqlUpdate = " from TB_PSG_EMOLUMENT_CHILD_OFFCR where   comm_id=:comm_id  and status=0 order by id ";
//
//		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("comm_id", comm_id);
//
//
//		List<TB_PSG_EMOLUMENT_CHILD_OFFCR> list = (List<TB_PSG_EMOLUMENT_CHILD_OFFCR>) query.list();
//
//		tx.commit();
//		sessionHQL.close();
 
		return alist;

	}
	
	
	
	@RequestMapping(value = "/admin/App_getm_emu_close_detailsData", method = RequestMethod.POST)

	public @ResponseBody ArrayList<ArrayList<String>> App_getm_emu_close_detailsData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

//		String msg = "";
//
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//
//		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		
		String c_status = request.getParameter("c_status");
		String i_status = request.getParameter("i_status");
		
		
		
		
		  ArrayList<ArrayList<String>> alist =tm_dao.App_getm_emu_close_detailsData_list(comm_id,c_status,i_status);
		

//		String hqlUpdate = " from TB_PSG_EMOLUMENT_CHILD_OFFCR where   comm_id=:comm_id  and status=0 order by id ";
//
//		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("comm_id", comm_id);
//
//
//		List<TB_PSG_EMOLUMENT_CHILD_OFFCR> list = (List<TB_PSG_EMOLUMENT_CHILD_OFFCR>) query.list();
//
//		tx.commit();
//		sessionHQL.close();
 
		return alist;

	}
	
	
	
	
	@RequestMapping(value = "/admin/App_getm_emu_detailsData_app", method = RequestMethod.POST)

	public @ResponseBody ArrayList<ArrayList<String>> App_getm_emu_detailsData_app(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

//		String msg = "";
//
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//
//		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		
		
		
		
		  ArrayList<ArrayList<String>> alist =tm_dao.App_getm_emu_detailsData_app_list(comm_id);
		

//		String hqlUpdate = " from TB_PSG_EMOLUMENT_CHILD_OFFCR where   comm_id=:comm_id  and status=0 order by id ";
//
//		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("comm_id", comm_id);
//
//
//		List<TB_PSG_EMOLUMENT_CHILD_OFFCR> list = (List<TB_PSG_EMOLUMENT_CHILD_OFFCR>) query.list();
//
//		tx.commit();
//		sessionHQL.close();
 
		return alist;

	}
	
	
	
	 
	@RequestMapping(value = "/admin/Edit_Emolument_Action", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Edit_Emolument_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		
		int sShape_count = Integer.parseInt(request.getParameter("count_ins").toString()); //total count
		int sShapeOld_count = Integer.parseInt(request.getParameter("count_hidden_old").toString());// oldcount
		String msg = "";
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		int diffrence;
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();

		//try {
		  	
		////////////////////////////// UPDATE ////////////////////////////
		for (int i = 1; i <= sShapeOld_count; i++) {
			
			String agency_id = request.getParameter("agency_id"+i);
			String type_of_benefits_id = request.getParameter("type_of_benefits_id"+i);
			String amount_rel = request.getParameter("amount_release"+i);
			String amount_rel_v = amount_rel.replace(",","");
			String amount_due = request.getParameter("amount_due"+i);
			String amount_due_v = amount_due.replace(",","");
			String reason = request.getParameter("reason"+i);
			String updated_as_on = request.getParameter("updated_as_on"+i);
			String shape_ch = request.getParameter("e_fid"+i);
			String c_fid = request.getParameter("c_fid"+i);
			
			
				
		TB_PSG_EMOLUMENT_PARENT_OFFCR Mad_cat =(TB_PSG_EMOLUMENT_PARENT_OFFCR)session3.get(TB_PSG_EMOLUMENT_PARENT_OFFCR.class, Integer.parseInt(shape_ch));
		TB_PSG_EMOLUMENT_CHILD_OFFCR ec =(TB_PSG_EMOLUMENT_CHILD_OFFCR)session3.get(TB_PSG_EMOLUMENT_CHILD_OFFCR.class, Integer.parseInt(c_fid));
		
			if(agency_id != null) {
			Mad_cat.setAgency_id(Integer.parseInt(agency_id));	
			}
			if(type_of_benefits_id != null) {
				Mad_cat.setType_of_benefits_id(Integer.parseInt(type_of_benefits_id));
			}
			Mad_cat.setComm_id(comm_id); 
			Mad_cat.setAmount_rel(amount_rel_v);
			Mad_cat.setAmount_rel_v(amount_rel);
			Mad_cat.setUpdated_as_on(updated_as_on);
			Mad_cat.setAmount_due(amount_due_v); 
			Mad_cat.setAmount_due_v(amount_due);
			Mad_cat.setReason(reason);
			Mad_cat.setStatus(0);
			session3.update(Mad_cat);
		
			ec.setComm_id(comm_id);
			String amt_rem=ec.getAmount_rem();
			if(amount_rel != null && amount_rel !="null" && amount_rel !="") {
				ec.setAmount_release(amount_rel_v);
				
				double rem =Double.parseDouble(Mad_cat.getAmount_due() ) - Double.parseDouble(Mad_cat.getAmount_rel() );
		 		 amt_rem =String.valueOf(rem);
			}
			ec.setCreated_by(username);
			ec.setCreated_dt(new Date());
			ec.setReason(reason);
			ec.setStatus(0);
			ec.setP_id(Mad_cat.getId());
			ec.setAmount_rem(amt_rem);
			session3.update(ec);
			
			
			
		//child
  	
		session3.flush();
		session3.clear();						

		}

		//////////////////////// Logic for INSERT
		diffrence = sShape_count - sShapeOld_count;
		if (diffrence != 0) {
			TB_PSG_EMOLUMENT_PARENT_OFFCR EP = new TB_PSG_EMOLUMENT_PARENT_OFFCR();
			TB_PSG_EMOLUMENT_CHILD_OFFCR EC = new TB_PSG_EMOLUMENT_CHILD_OFFCR();
			
			for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {
				String shape_ch = request.getParameter("e_fid"+i);
				
				String agency_id = request.getParameter("agency_id"+i);
				String type_of_benefits_id = request.getParameter("type_of_benefits_id"+i);
				String amount_rel = request.getParameter("amount_release"+i);
				String amount_rel_v = amount_rel.replace(",","");
				String amount_due = request.getParameter("amount_due"+i);
				String amount_due_v = amount_due.replace(",","");
				String reason = request.getParameter("reason"+i);
				String updated_as_on = request.getParameter("updated_as_on"+i);
				 
				
					EP.setComm_id(comm_id);
					if(agency_id != null) {
					EP.setAgency_id(Integer.parseInt(agency_id));	
					}
					if(type_of_benefits_id != null) {
					EP.setType_of_benefits_id(Integer.parseInt(type_of_benefits_id));
					}
					EP.setComm_id(comm_id);
					EP.setAmount_rel(amount_rel_v);
					EP.setAmount_rel_v(amount_rel);
					EP.setUpdated_as_on(updated_as_on);
					EP.setAmount_due(amount_due_v); 
					EP.setAmount_due_v(amount_due);
					EP.setReason(reason);
					EP.setStatus(0);
					session3.save(EP);
					
					//child
					EC.setComm_id(comm_id);
					String amt_rem= EC.getAmount_rem();
					if(amount_rel != null && amount_rel !="null" && amount_rel !="") {
						EC.setAmount_release(amount_rel_v);
					
						double rem =Double.parseDouble(EP.getAmount_due() ) - Double.parseDouble(EP.getAmount_rel() );
				 		 amt_rem =String.valueOf(rem);
					}
					EC.setAmount_rel_v(amount_rel);
 					EC.setCreated_by(username);
 					EC.setCreated_dt(new Date());
 					EC.setReason(reason);
					EC.setStatus(0);
 					EC.setP_id(EP.getId());
 					EC.setAmount_rem(amt_rem);
 					session3.save(EC);
					
					
				session3.flush();
				session3.clear();
			}
		}
  
		// END LOGIC OF INSERT
				
					tx3.commit();
			
				
		
		msg="1";
		if(msg == "1") {
            Mmap.put("msg", "Data Updated Successfully.");
   }
   else {
   	Mmap.put("msg", "Data Not Updated.");
   }

		
//		} catch (RuntimeException e) {
//			try {
//				tx3.rollback();
//				
//				msg = "Data not Updated";
//			} catch (RuntimeException rbe) {
//				msg = "Data not Updated";
//			
//			}
//		
//		} finally {
//			if (session3 != null) {
//				session3.close();
//			}
//		}	
		
		//return msg;
		return new ModelAndView("redirect:Search_Emoluments");
	}
	
	
	
	@RequestMapping(value = "/getPersonnelNoAlreadyExistem", method = RequestMethod.POST)
	public @ResponseBody Boolean getPersonnelNoAlreadyExistem(HttpServletRequest request, HttpSession sessionA,
			String personnel_no) throws HibernateException, ParseException {
		
		
		return edao.getPersonnelNoAlreadyExistemolu(personnel_no);

	}	 
	
	
	//Approve Update EDIT 
		@RequestMapping(value = "/Approve_Update_Emoluments",method = RequestMethod.POST )
		public ModelAndView Approve_Update_Emoluments(@ModelAttribute("approveupdateid") BigInteger comm_id,
				@ModelAttribute("ongoing_status") String c_status,
				@ModelAttribute("p_status") String i_status,HttpSession session, ModelMap model, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
		    
			String roleid = session.getAttribute("roleid").toString();
			
			Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
//			Boolean val = roledao.ScreenRedirect("form", roleid);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if (request.getHeader("Referer") == null) {
//				msg = "";
//			}
			
//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//				return new ModelAndView("redirect:bodyParameterNotAllow");
//			}
			 //TB_PSG_EMOLUMENT_PARENT_OFFCR EmeDetails = tm_dao.getEmolumentsByid(Integer.parseInt(updateid));
			 //model.put("Edit_Emolument_CMD", EmeDetails);
		     List<ArrayList<String>> list = tm_dao.Get_EmoulData(comm_id);
		     
		     
			model.put("list", list);
			model.put("getAgencyList", mcommon.getAgencyList());
			String roleType = sessionEdit.getAttribute("roleType").toString();
			model.put("roleType", roleType);
			
			  //ArrayList<ArrayList<String>> alist =tm_dao.App_getm_emu_detailsData_list(Integer.parseInt(comm_id));
				//model.put("alist", alist);
			
		 	model.put("roleid", roleid);
		 	model.put("comm_id", comm_id);
		 	model.put("c_status", c_status);
		 	model.put("i_status", i_status);
		 	
			//model.put("getMedSystemCodeQuaQUARTER", mcommon.getMedSystemCodeQua("QUARTER", "", sessionEdit));
			model.put("msg", msg);
			//model.put("m12", mcommon.getMedSystemCode("WARD", "", sessionEdit));
			return new ModelAndView("Approve_update_EmolumentsTiles");
		}
		
		@RequestMapping(value = "/Approve_Edit_Emolument_Action", method = RequestMethod.POST)
		public ModelAndView Approve_Edit_Emolument_Action(@ModelAttribute("Approve_Edit_Emolument_CMD") TB_PSG_EMOLUMENT_CHILD_OFFCR EP_NEW,
				BindingResult result, HttpServletRequest request, ModelMap Mmap, HttpSession session)
				throws ParseException {

		
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			String msg = "";
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			String username = session.getAttribute("username").toString();
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	 		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
			
			String count_hidden1 = request.getParameter("count_hidden_old");
			
			
			int count_hidden = 1;
			if (count_hidden1 != null && count_hidden1 != "") {
				count_hidden = Integer.parseInt(count_hidden1);
			}
			 
			try {
				/*
				 * Query q0 = sessionHQL.
				 * createQuery("select count(id) from TB_PSG_EMOLUMENT_CHILD_OFFCR where id !=:id"
				 * ); q0.setParameter("id", id); Long c = (Long) q0.uniqueResult();
				 */
//				if (id == 0) {

//					if (c == 0) {
				
				
				for (int j = 1; j <= count_hidden; j++) {
					
					int e_fid = Integer.parseInt(request.getParameter("e_fid"+j));
					int c_id =0;
					if(request.getParameter("c_id"+j) != null && !request.getParameter("c_id"+j).equals(""))
					{ 
						c_id =Integer.parseInt(request.getParameter("c_id"+j));
						
					}
					
					String amount_rel = request.getParameter("amount_release_add"+j);
					String amount_rel_v = amount_rel.replace(",","");
					String reason = request.getParameter("reason_add"+j);
					String updated_as_on = request.getParameter("updated_as_on_add"+j);
				
					Date c_dt = null;
					Date u_dt = null;
					
//					String dt_of_casuality = request.getParameter("dt_of_casuality"+j);
			
//					String update_dt = request.getParameter("updated_as_on");
//					
//					if (update_dt != null && !update_dt.trim().equals("") && !update_dt.equals("DD/MM/YYYY")) {
//						u_dt = format.parse(update_dt);
//					}
//					 //KAJAL CHAUHAN
						
				   
					
					 String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set update_status=:update_status where comm_id=:comm_id";

		                

		                int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_status",(-1)).setBigInteger("comm_id",comm_id) .executeUpdate();
//		                tx.commit();
					
					
					if (updated_as_on != null && !updated_as_on.trim().equals("") && !updated_as_on.equals("DD/MM/YYYY")) {
	 					c_dt = format.parse(updated_as_on);
	 			}
				//	TB_PSG_EMOLUMENT_PARENT_OFFCR Mad_cat = new TB_PSG_EMOLUMENT_PARENT_OFFCR();
					TB_PSG_EMOLUMENT_PARENT_OFFCR Mad_cat =(TB_PSG_EMOLUMENT_PARENT_OFFCR)sessionHQL.get(TB_PSG_EMOLUMENT_PARENT_OFFCR.class, e_fid);
					 String amt_rem ="0";
					if(Mad_cat != null)
					{
						
						 double rem =Double.parseDouble(Mad_cat.getAmount_due() ) - Double.parseDouble(Mad_cat.getAmount_rel() );
					 	 amt_rem =String.valueOf(rem);
					}
						TB_PSG_EMOLUMENT_CHILD_OFFCR EP=null;
						if(c_id==0)
						{
							EP = new  TB_PSG_EMOLUMENT_CHILD_OFFCR();
						}
						if(c_id>0)
						{
							EP =(TB_PSG_EMOLUMENT_CHILD_OFFCR)sessionHQL.get(TB_PSG_EMOLUMENT_CHILD_OFFCR.class, c_id);
								 
						}
							EP.setCreated_by(username);
							EP.setCreated_dt(new Date());
							EP.setComm_id(comm_id);
							
	 						
	 						EP.setAmount_release(amount_rel_v);
	 						EP.setAmount_rel_v(amount_rel);
	 						EP.setP_id(e_fid);
	 						EP.setReason(reason);
	 						EP.setUpdated_as_on(c_dt);
	 						
	 						if(Mad_cat != null)
	 						{
	 							double sum_amt =Double.parseDouble(Mad_cat.getAmount_rel())  + Double.parseDouble(amount_rel_v);
	 							 double rem =Double.parseDouble(Mad_cat.getAmount_due() ) - Double.parseDouble(String.valueOf(sum_amt) );
	 						 	 amt_rem =String.valueOf(rem);
	 						}
	 						
	 						EP.setAmount_rem(amt_rem);
							sessionHQL.saveOrUpdate(EP);
						
								sessionHQL.flush();
								sessionHQL.clear();
							}

						Mmap.put("msg", "Data Saved Successfully.");
//					} 
//				}
				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					Mmap.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:Search_Emoluments");
		}
		
	
	
	
	
	
		  @RequestMapping(value = "/Reject_eno", method = RequestMethod.POST)

	        public @ResponseBody String Reject_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
			  BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

	                List<String> liststr = new ArrayList<String>();
	                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	                Transaction tx = sessionHQL.beginTransaction();
	                String username = sessionA.getAttribute("username").toString();
	                String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set status=:status where comm_id=:comm_id";
	                int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)
	                                .setBigInteger("comm_id", comm_id)
	                                .executeUpdate();
	String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id";

	                int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setBigInteger("comm_id", comm_id)
	                                .executeUpdate();
	                
	                if (app > 0) {
	                        liststr.add("Data Rejected Successfully.");
	                } else {
	                        liststr.add("Data Not Rejected.");
	                }

	                model.put("msg", liststr.get(0));
	                tx.commit();
	                sessionHQL.close();
	                return  liststr.get(0);

	        }

		  @RequestMapping(value = "/approve_eno", method = RequestMethod.POST)

	        public @ResponseBody String approve_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
			  BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

	        

	                List<String> liststr = new ArrayList<String>();

	                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	                Transaction tx = sessionHQL.beginTransaction();

	                String username = sessionA.getAttribute("username").toString();

	                
	                				
	              
	                String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set status=:status,update_status=:update_status where comm_id=:comm_id";

	                

	                int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("update_status",(-1))

	                                .setBigInteger("comm_id", comm_id)

	                                .executeUpdate();
	                
	                String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status where comm_id=:comm_id";

	                
	                int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setBigInteger("comm_id", comm_id)

	                                .executeUpdate();
	                
	                
	                
	                if (app > 0) {

	                        liststr.add("Approved Successfully.");

	                } else {

	                        liststr.add("Data Not Approved.");

	                }

	                model.put("msg", liststr.get(0));

	                tx.commit();

	                sessionHQL.close();

	                return  liststr.get(0);

	        }
		  
		  
		  
		  @RequestMapping(value = "/approve_update_eno", method = RequestMethod.POST)

	        public @ResponseBody String approve_update_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
			  BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

//	         int count_hidden_old=Integer.parseInt(request.getParameter("count_hidden_old"));


	                List<String> liststr = new ArrayList<String>();

	                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	                Transaction tx = sessionHQL.beginTransaction();

	                String username = sessionA.getAttribute("username").toString();
	                int itteration_status = 0;
					int itteration_status_d=itteration_status+1;
	                
	                
	                ArrayList<ArrayList<String>> alist =tm_dao.approve_update_eno_update(comm_id);
	                

	                ArrayList<String> alist1 =tm_dao.approve_update_eno_iteration(comm_id);
	                
	                
	               
	                
	                
	             
	                
	                
	                
	                
//	                int app_u = sessionHQL.createQuery(hqlUpdate_u).setInteger("comm_id", comm_id)
//
//                        .executeUpdate();
//	                
	                
	                
	                String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set update_status=:update_status , itteration_status=:itteration_status where comm_id=:comm_id";

	                
	                
	                int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_status", 1).setParameter("itteration_status",Integer.parseInt(alist1.get(0)))

	                                .setBigInteger("comm_id", comm_id)

	                                .executeUpdate();
	                
	                String hqlUpdate2 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id AND status=1";
	           	 

	                int app1 = sessionHQL.createQuery(hqlUpdate2)
	                		.setInteger("status",2)
	                		.setBigInteger("comm_id", comm_id) .executeUpdate();
	                
	                
	                String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id AND status=0";
	 

	                int app2 = sessionHQL.createQuery(hqlUpdate1)
	                		.setInteger("status", 1)
	                		.setBigInteger("comm_id", comm_id) .executeUpdate();
	                
	                
	                
	                if (app > 0) {

	                        liststr.add("Approved Successfully.");

	                } else {

	                        liststr.add("Data Not Approved.");

	                }

	                model.put("msg", liststr.get(0));

	                tx.commit();

	                sessionHQL.close();

	                return  liststr.get(0);

	        }
		  
	
		  
		  
		  @RequestMapping(value = "/Reject_App_Update_eno", method = RequestMethod.POST)

	        public @ResponseBody String Reject_App_Update_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
			  BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

	                List<String> liststr = new ArrayList<String>();
	                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	                Transaction tx = sessionHQL.beginTransaction();
	                String username = sessionA.getAttribute("username").toString();
	                String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set update_status=:update_status where comm_id=:comm_id";
	                int app = sessionHQL.createQuery(hqlUpdate).setInteger("update_status", 3)
	                                .setBigInteger("comm_id", comm_id)
	                                .executeUpdate();
	                String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id";

	                int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setBigInteger("comm_id", comm_id)
	                                .executeUpdate();
	                
	                if (app > 0) {
	                        liststr.add("Data Rejected Successfully.");
	                } else {
	                        liststr.add("Data Not Rejected.");
	                }

	                model.put("msg", liststr.get(0));
	                tx.commit();
	                sessionHQL.close();
	                return  liststr.get(0);

	        }
		  
		  
		  
	

			@RequestMapping(value = "/GetPersonnelNoDataEmoul", method = RequestMethod.POST)

	    	public @ResponseBody List<Map<String, Object>> GetPersonnelNoDataEmoul(String personnel_no) {

	    		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

	    		Transaction tx = sessionHQL.beginTransaction();
	    		List<Map<String, Object>> list  = tm_dao.getPersonnelNoDataEmoulCont(personnel_no);

	    			tx.commit();

	    			return list;
	    	}
			
			
			 @RequestMapping(value = "/admin/view_Emoluments_HistoryUrl", method = RequestMethod.POST)
			 public ModelAndView view_Emoluments_HistoryUrl(ModelMap Mmap, HttpSession session,
					 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
					 @RequestParam(value = "comm_id_a", required = false) BigInteger comm_id) {

					String roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Search_Emoluments", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
					}
				 
				
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
					Mmap.put("list",tm_dao.View_Emoluments_History(comm_id));			 
					Mmap.put("msg", msg);
					 return new ModelAndView("Emoluments_History_Popup_Tiles");
				
			 }
		  
			 
			 
			 
			
			 
			 
			 @RequestMapping(value = "/admin/getm_emu_detailsDataListEdit", method = RequestMethod.POST)

				public @ResponseBody ArrayList<ArrayList<String>> getm_emu_detailsDataListEdit(ModelMap Mmap, HttpSession session,

						HttpServletRequest request) throws ParseException {

				 BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
					
				 	  ArrayList<ArrayList<String>> alist =tm_dao.getm_emu_detailsDataListEdit(comm_id);
 
			 
					return alist;

				}
			 
			 @RequestMapping(value = "/approve_close_eno", method = RequestMethod.POST)

		        public @ResponseBody String approve_close_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
				 BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

//		         int count_hidden_old=Integer.parseInt(request.getParameter("count_hidden_old"));


		                List<String> liststr = new ArrayList<String>();

		                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		                Transaction tx = sessionHQL.beginTransaction();

		                String username = sessionA.getAttribute("username").toString();
		                int itteration_status = 0;
						int itteration_status_d=itteration_status+1;
		                
		                
		                ArrayList<ArrayList<String>> alist =tm_dao.approve_update_eno_update(comm_id);
		                

		                ArrayList<String> alist1 =tm_dao.approve_update_eno_iteration(comm_id);
		                
		              String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set status=:status,close_status=:close_status where comm_id=:comm_id";

		                
		                
		                int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 4).setInteger("close_status", 1) .setBigInteger("comm_id", comm_id).executeUpdate();
		                
		            	String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id and status=0";
		                
		                int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setBigInteger("comm_id", comm_id)
		                	                                .executeUpdate();
		               
		                
		                if (app > 0) {

		                        liststr.add("Approved Successfully.");

		                } else {

		                        liststr.add("Data Not Approved.");

		                }

		                model.put("msg", liststr.get(0));

		                tx.commit();

		                sessionHQL.close();

		                return  liststr.get(0);

		        }
			 
			 @RequestMapping(value = "/reject_close_eno", method = RequestMethod.POST)

		        public @ResponseBody String reject_close_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
				 BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

//		         int count_hidden_old=Integer.parseInt(request.getParameter("count_hidden_old"));


		                List<String> liststr = new ArrayList<String>();

		                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		                Transaction tx = sessionHQL.beginTransaction();

		                String username = sessionA.getAttribute("username").toString();
		                int itteration_status = 0;
						int itteration_status_d=itteration_status+1;
		                
		                
		                ArrayList<ArrayList<String>> alist =tm_dao.approve_update_eno_update(comm_id);
		                

		                ArrayList<String> alist1 =tm_dao.approve_update_eno_iteration(comm_id);
		                
		              String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set close_status=:status where comm_id=:comm_id";

		                
		                
		                int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", -1) .setBigInteger("comm_id", comm_id).executeUpdate();
		                
		                if (app > 0) {

		                        liststr.add("Approved Successfully.");

		                } else {

		                        liststr.add("Data Not Approved.");

		                }

		                model.put("msg", liststr.get(0));

		                tx.commit();

		                sessionHQL.close();

		                return  liststr.get(0);

		        }
			 
			 
			 
		/*	 @RequestMapping(value = "/Close_App_Update_eno", method = RequestMethod.POST)

		        public @ResponseBody String Close_App_Update_eno(HttpServletRequest request, HttpSession sessionA, ModelMap model) {
					BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		                List<String> liststr = new ArrayList<String>();
		                Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		                Transaction tx = sessionHQL.beginTransaction();
		                String username = sessionA.getAttribute("username").toString();
		                String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set close_status=:close_status where comm_id=:comm_id";
		                int app = sessionHQL.createQuery(hqlUpdate).setInteger("close_status", 0)
		                                .setBigInteger("comm_id"	, comm_id)
		                                .executeUpdate();
//		String hqlUpdate1 = "update TB_PSG_EMOLUMENT_CHILD_OFFCR set status=:status  where comm_id=:comm_id";
	//
//		                int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setInteger("comm_id", comm_id)
//		                                .executeUpdate();
//		                
		                if (app > 0) {
		                        liststr.add("Data Closed Successfully.");
		                } else {
		                        liststr.add("Data Not Closed.");
		                }

		                model.put("msg", liststr.get(0));
		                tx.commit();
		                sessionHQL.close();
		                return  liststr.get(0);

		        }*/
			 
			 @RequestMapping(value = "/admin/Close_App_Update_eno", method = RequestMethod.POST)
				public @ResponseBody String Close_App_Update_eno(ModelMap Mmap, HttpSession session, HttpServletRequest request)
						throws ParseException {

					Date date = new Date();
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

					Date dt_authority = null;
					Date dt_rank = null;
					String username = session.getAttribute("username").toString();

					Session sessionhql = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = sessionhql.beginTransaction();

					//String username = session.getAttribute("username").toString();
				
					
			 		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
					
					String count_hidden1 = request.getParameter("count_hidden_old");
					
					
					int count_hidden = 1;
					if (count_hidden1 != null && count_hidden1 != "") {
						count_hidden = Integer.parseInt(count_hidden1);
					}
					String msg = ""; 
					//try {
						/*
						 * Query q0 = sessionHQL.
						 * createQuery("select count(id) from TB_PSG_EMOLUMENT_CHILD_OFFCR where id !=:id"
						 * ); q0.setParameter("id", id); Long c = (Long) q0.uniqueResult();
						 */
//						if (id == 0) {

//							if (c == 0) {
						
					String e_fid = request.getParameter("e_fid");
						
							
//							String e_fid = request.getParameter("e_fid"+j);
							int c_id =0;
							if(request.getParameter("c_id") != null && !request.getParameter("c_id").equals(""))
							{ 
								c_id =Integer.parseInt(request.getParameter("c_id"));
								
							}
							
							String amount_rel = request.getParameter("amount_release_add");
							String amount_rel_v = amount_rel.replace(",","");
							String reason = request.getParameter("reason_add");
							String updated_as_on = request.getParameter("updated_as_on_add");
						
							Date c_dt = null;
							Date u_dt = null;
							
//							String dt_of_casuality = request.getParameter("dt_of_casuality"+j);
					
//							String update_dt = request.getParameter("updated_as_on");
//							
//							if (update_dt != null && !update_dt.trim().equals("") && !update_dt.equals("DD/MM/YYYY")) {
//								u_dt = format.parse(update_dt);
//							}
//							 //KAJAL CHAUHAN
								
							
							
							
							if (updated_as_on != null && !updated_as_on.trim().equals("") && !updated_as_on.equals("DD/MM/YYYY")) {
			 					c_dt = format.parse(updated_as_on);
			 			}
							//TB_PSG_EMOLUMENT_PARENT_OFFCR Mad_cat = new TB_PSG_EMOLUMENT_PARENT_OFFCR();
							TB_PSG_EMOLUMENT_PARENT_OFFCR Mad_cat =(TB_PSG_EMOLUMENT_PARENT_OFFCR)sessionhql.get(TB_PSG_EMOLUMENT_PARENT_OFFCR.class, Integer.parseInt(e_fid));
							 String amt_rem ="0";
							if(Mad_cat != null)
							{
								
								 double rem =Double.parseDouble(Mad_cat.getAmount_due() ) - Double.parseDouble(Mad_cat.getAmount_rel() );
							 	 amt_rem =String.valueOf(rem);
							}
								TB_PSG_EMOLUMENT_CHILD_OFFCR EP=null;
								if(c_id==0)
								{
									EP = new  TB_PSG_EMOLUMENT_CHILD_OFFCR();
								}
								if(c_id>0)
								{
									EP =(TB_PSG_EMOLUMENT_CHILD_OFFCR)sessionhql.get(TB_PSG_EMOLUMENT_CHILD_OFFCR.class, c_id);
										 
								}
									EP.setCreated_by(username);
									EP.setCreated_dt(new Date());
									EP.setComm_id(comm_id);
									
			 						
			 						EP.setAmount_release(amount_rel_v);
			 						EP.setAmount_rel_v(amount_rel);
			 						EP.setP_id(Integer.parseInt(e_fid));
			 						EP.setReason(reason);
			 						EP.setUpdated_as_on(c_dt);
			 						
			 						if(Mad_cat != null)
			 						{
			 							double sum_amt =Double.parseDouble(Mad_cat.getAmount_rel())  + Double.parseDouble(amount_rel_v);
			 							 double rem =Double.parseDouble(Mad_cat.getAmount_due() ) - Double.parseDouble(String.valueOf(sum_amt) );
			 						 	 amt_rem =String.valueOf(rem);
			 						}
			 						
			 						EP.setAmount_rem(amt_rem);
			 						//sessionhql.saveOrUpdate(EP);
			 						int id = (int) sessionhql.save(EP);
			 						msg = Integer.toString(id);
			 					
									

						 String hqlUpdate = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set close_status=:close_status where comm_id=:comm_id";
						 Query app = sessionhql.createQuery(hqlUpdate).setInteger("close_status", 0)
			                                .setBigInteger("comm_id"	, comm_id)
			                                ;
			                msg = app.executeUpdate() > 0 ? "update" : "0";
						 String hqlUpdate1 = "update TB_PSG_EMOLUMENT_PARENT_OFFCR set update_status=:update_status where comm_id=:comm_id";

			                

						 Query app1 = sessionhql.createQuery(hqlUpdate1).setInteger("update_status",(-1)).setBigInteger("comm_id",comm_id);
		              		msg = app1.executeUpdate() > 0 ? "update" : "0";
						
						tx.commit();
				/*	} catch (RuntimeException e) {
						try {
							tx.rollback();
							msg = "0";
						} catch (RuntimeException rbe) {
							msg = "0";
						}

					} finally {
						if (sessionhql != null) {
							sessionhql.close();
						}
					}*/

					return msg;
				}
			 
			 
			 
}
