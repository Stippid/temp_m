package com.controller.psg.Master;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.InstituteDAO;
import com.models.psg.Master.TB_BATTALION;
import com.models.psg.Master.TB_COMPANY;
import com.models.psg.Master.TB_INSTITUTE;
import com.models.psg.Master.TB_INSTITUTE_LIST;
import com.models.psg.Master.TB_PLATOON;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Institute_Controller {
	
	@Autowired
	private InstituteDAO instidao;
	@Autowired
	private Psg_CommonController com = new Psg_CommonController();
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/institute", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("msg", msg);
		Mmap.put("list", instidao.search_Institute("", "active"));
		Mmap.put("getStatusMasterList", com.getStatusMasterList());
		Mmap.put("getInstituteTypeList", com.getInstituteTypeList());
//		 Mmap.put("getCompanyList", com.getCompanyList());
		return new ModelAndView("InstituteTiles");
	}

	/******************************
	 * Save For Institute
	 ***********************************/
	@RequestMapping(value = "/InstituteAction", method = RequestMethod.POST)
	public ModelAndView InstituteAction(@ModelAttribute("InstituteCMD") TB_INSTITUTE_LIST IL,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String institute_name1 = request.getParameter("institute_name").trim();
		String institute_type = request.getParameter("institute_type");
		String btn_arry_id = request.getParameter("btn_arry_id");
		String company_arr_id = request.getParameter("company_arr_id");
		String status = request.getParameter("status");
		
		if (institute_name1 == null || institute_name1.equals("")) {
			model.put("msg", "Please Enter Institute Name.");
			return new ModelAndView("redirect:institute");
		}
		if (institute_type == null || institute_type.equals("")) {
			model.put("msg", "Please Select Institute Type.");
			return new ModelAndView("redirect:institute");
		}
		if (btn_arry_id == null || btn_arry_id.equals("")) {
			model.put("msg", "Please Choose Battalion.");
			return new ModelAndView("redirect:institute");
		}
		List<String> batList = new ArrayList<String>(Arrays.asList(btn_arry_id.split(",")));
		List<String> companyList = new ArrayList<String>(Arrays.asList(company_arr_id.split(",")));
		List<String> bat_int = new ArrayList<String>();
		if (!company_arr_id.equals("")) {
			for (String bat : companyList) {
				bat_int.add(bat.split("_")[0]);
			}
			batList.removeAll(bat_int);
		}
		
		try {
			IL.setInstitute_name(institute_name1);
			IL.setInstitute_type(institute_type);
			IL.setStatus(status);
			IL.setCreated_by(username);
			IL.setCreated_date(date);
			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_INSTITUTE_LIST where institute_name=:institute_name and status=:status and id !=:id");
			q0.setParameter("institute_name", IL.getInstitute_name());
			q0.setParameter("status", IL.getStatus());
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();
			if (id == 0) {
				if (c == 0) {
					sessionHQL.save(IL);
					TB_INSTITUTE I = new TB_INSTITUTE();
					for (int j = 0; j < batList.size(); j++) {
						I.setBn_id(Integer.parseInt(batList.get(j)));
						I.setCompany_id(0);
						I.setPlatoon_id(0);
						I.setInstitute_id(IL.getId());
						I.setCreated_by(username);
						I.setCreated_date(date);
						sessionHQL.save(I);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					if (!company_arr_id.equals("")) {
						for (int j = 0; j < companyList.size(); j++) {
							I.setBn_id(Integer.parseInt(companyList.get(j).split("_")[0]));
							I.setCompany_id(Integer.parseInt(companyList.get(j).split("_")[1]));
							I.setPlatoon_id(0);
							I.setInstitute_id(IL.getId());
							I.setCreated_by(username);
							I.setCreated_date(date);
							sessionHQL.save(I);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
					model.put("msg", "Data Saved Successfully.");
				} else {
					model.put("msg", "Data already Exist.");
				}
			} else {
				if (c == 0) {
					String hql = "DELETE FROM TB_INSTITUTE where institute_id=:id";
					Query query = sessionHQL.createQuery(hql).setInteger("id", id);
					query.executeUpdate();
					hql = "update  TB_INSTITUTE_LIST set institute_name=:institute_name,institute_type=:institute_type,status=:status,modified_by=:modified_by,modified_date=:modified_date where id=:id";
					Query query2 = sessionHQL.createQuery(hql).setInteger("id", id)
							.setString("institute_name", institute_name1).setString("institute_type", institute_type)
							.setString("status", status).setString("modified_by", username)
							.setTimestamp("modified_date", date);
					query2.executeUpdate();
					TB_INSTITUTE I = new TB_INSTITUTE();
					for (int j = 0; j < batList.size(); j++) {
						I.setBn_id(Integer.parseInt(batList.get(j)));
						I.setCompany_id(0);
						I.setPlatoon_id(0);
						I.setInstitute_id(IL.getId());
						I.setCreated_by(username);
						I.setCreated_date(date);
						sessionHQL.save(I);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					if (!company_arr_id.equals("")) {
						for (int j = 0; j < companyList.size(); j++) {
							I.setBn_id(Integer.parseInt(companyList.get(j).split("_")[0]));
							I.setCompany_id(Integer.parseInt(companyList.get(j).split("_")[1]));
							I.setPlatoon_id(0);
							I.setInstitute_id(IL.getId());
							I.setCreated_by(username);
							I.setCreated_date(date);
							sessionHQL.save(I);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data already Exist.");
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:institute");
	}

	/******************************
	 * Search For Institute
	 ***********************************/
	@RequestMapping(value = "/admin/GetSearch_Institute", method = RequestMethod.POST)
	public ModelAndView GetSearch_Institute(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "institute_name2", required = false) String institute_name,
			@RequestParam(value = "bn_id2", required = false) String bn_id,
			@RequestParam(value = "company_id2", required = false) String company_id,
			@RequestParam(value = "status2", required = false) String status) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		if (!institute_name.equals("")) {
			Mmap.put("institute_name2", institute_name);
		}
		ArrayList<ArrayList<String>> list = instidao.search_Institute(institute_name, status);
		Mmap.put("getBattalionList", com.getBattalionList());
		Mmap.put("getCompanyList", com.getCompanyList());
		Mmap.put("getStatusMasterList", com.getStatusMasterList());
		Mmap.put("list", list);
		Mmap.put("bn_id2", bn_id);
		Mmap.put("company_id2", company_id);
		Mmap.put("status2", status);
		return new ModelAndView("InstituteTiles");
	}

	/******************************
	 * Delete For Institute
	 ***********************************/
/*	@RequestMapping(value = "/Delete_Institute", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_hair_Colour(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String hql = "DELETE FROM TB_INSTITUTE where institute_id=:id";
			Query query = sessionHQL.createQuery(hql).setInteger("id", id);
			query.executeUpdate();
			String hqlUpdate = "delete from TB_INSTITUTE_LIST where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Data Not Deleted.");
				tx.rollback();
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			tx.rollback();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:institute");
	}*/
	
	@RequestMapping(value = "/Delete_Institute", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Institute(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		List<String> liststr = new ArrayList<String>();
		
		String username = session.getAttribute("username").toString();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "update TB_INSTITUTE set modify_by=:modify_by,modify_date=:modify_date,status=:status"
									+ " where institute_id=:id";
			
			 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
					.setString("modify_by", username)
					.setDate("modify_date", new Date()).setInteger("id", id).executeUpdate();
			 

			String hqlUpdate1 = "update TB_INSTITUTE_LIST set modified_by=:modified_by,modified_date=:modified_date,status=:status"
					+ " where id=:id";

			int app1 = sessionHQL.createQuery(hqlUpdate1).setString("status", "inactive").setString("modified_by", username)
					.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0 && app1 > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:institute");
	}



	/******************************
	 * Update For Institute
	 ***********************************/
	@RequestMapping(value = "/Edit_InstituteUrl" ,method=RequestMethod.POST)
	public ModelAndView Edit_InstituteUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		TB_INSTITUTE authDetails = instidao.getInstituteByid(Integer.parseInt(updateid));
		Mmap.put("Edit_InstituteCMD", authDetails);
		Mmap.put("getBattalionList", com.getBattalionList());
		Mmap.put("getCompanyList", com.getCompanyList());
		Mmap.put("getStatusMasterList", com.getStatusMasterList());
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_InstituteTiles");
	}

	@RequestMapping(value = "/Edit_InstituteAction", method = RequestMethod.POST)
	public ModelAndView Edit_InstituteAction(@ModelAttribute("Edit_InstituteCMD") TB_INSTITUTE rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String institute_name = request.getParameter("institute_name").trim();
		String status = request.getParameter("status");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			Query q0 = session1.createQuery(
					"select count(id) from TB_INSTITUTE where institute_name=:institute_name and bn_id=:bn_id and company_id=:company_id and status=:status and id !=:id");
			q0.setParameter("institute_name", institute_name);
			q0.setParameter("bn_id", rs.getBn_id());
			q0.setParameter("company_id", rs.getCompany_id());
			q0.setParameter("status", status);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();
			if (c == 0) {
				String hql = "update TB_INSTITUTE set institute_name=:institute_name,bn_id=:bn_id ,company_id=:company_id,status=:status,modify_by=:modify_by,modify_date=:modify_date"
						+ " where id=:id";
				Query query = session1.createQuery(hql).setString("institute_name", institute_name)
						.setInteger("bn_id", rs.getBn_id()).setInteger("company_id", rs.getCompany_id())
						.setString("status", rs.getStatus()).setString("modify_by", username)
						.setDate("modify_date", new Date()).setInteger("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}
			} else {
				model.put("msg", "Data already Exist.");
			}
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn?t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:institute");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/battalionurl", method = RequestMethod.POST)
	@ResponseBody
	public List<TB_BATTALION> battalionurl(ModelMap Mmap) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id, battalion_name from TB_BATTALION");
		List<TB_BATTALION> stlist1 = (List<TB_BATTALION>) q.list();
		tx.commit();
		sessionHQL.close();
		return stlist1;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/companyurl", method = RequestMethod.POST)
	@ResponseBody
	public List<TB_COMPANY> companyurl(ModelMap Mmap, HttpServletRequest request) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int btn_id = Integer.parseInt(request.getParameter("btn_id"));
		Query q = sessionHQL.createQuery("select id, company_name from TB_COMPANY where bat_id=:bat_id");
		q.setInteger("bat_id", btn_id);
		List<TB_COMPANY> stlist1 = (List<TB_COMPANY>) q.list();
		tx.commit();
		sessionHQL.close();
		return stlist1;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/platoonurl", method = RequestMethod.POST)
	@ResponseBody
	public List<TB_PLATOON> platoonurl(ModelMap Mmap) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("select id, platoon_name from TB_PLATOON");
		List<TB_PLATOON> stlist1 = (List<TB_PLATOON>) q.list();
		tx.commit();
		sessionHQL.close();
		return stlist1;
	}
}
