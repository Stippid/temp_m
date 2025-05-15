package com.controller.mnh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.SHO_FHO_strengthDao;
import com.models.mnh.Tb_Med_Str_Unit;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FHO_SHO_StrengthController {
	@Autowired
	RoleBaseMenuDAO roledao;
	
	
	@Autowired
	MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	 SHO_FHO_strengthDao St_Dao;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	@RequestMapping(value = "/admin/mnh_input_SHOstrengthDetails", method = RequestMethod.GET)
	public ModelAndView mnh_input_SHOstrengthDetails(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mnh_input_SHOstrengthDetails", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}		
		
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_SHOstrengthTiles");
	}
	
	@RequestMapping(value = "/admin/mnh_input_search_SHOstrengthDetails", method = RequestMethod.GET)
	public ModelAndView mnh_input_search_SHOstrengthDetails(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String username = (String) session.getAttribute("username");
		int roleid = (Integer) session.getAttribute("roleid");
		int userid = (Integer) session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl = (String) session.getAttribute("roleAccess");
		Boolean val = roledao.ScreenRedirect("mnh_input_search_SHOstrengthDetails",session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	       
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_input_SHOstrength_searchTiles");
	}
	
	@RequestMapping(value = "/search_sho_strength_input", method = RequestMethod.POST)
	public ModelAndView search_sho_strength_input(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			HttpServletRequest request) throws SQLException {	
		int userid = (Integer) session.getAttribute("userId");

		Boolean val = roledao.ScreenRedirect("mnh_input_search_SHOstrengthDetails",	session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);

		model.put("r_1", l1);
		List<Map<String, Object>> list = St_Dao.search_sho_strength_input1(sus1);
		model.put("list", list);
		model.put("size", list.size());
		model.put("sus1", sus1);
		return new ModelAndView("mnh_input_SHOstrength_searchTiles");
	}

		

	@RequestMapping(value = "/sho_strength_inputAction", method = RequestMethod.POST)
	public ModelAndView sho_strength_inputAction(@ModelAttribute("sho_strength_inputCMD") Tb_Med_Str_Unit rs,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		
		Boolean val = roledao.ScreenRedirect("mnh_input_SHOstrengthDetails",session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("id_hid");

		String unit_name1 = request.getParameter("unit_name1");
		String sus_no1 = request.getParameter("sus_no1");

		try {
			if (unit_name1.equals("")) {
				model.put("msg", "Please Enter the Unit Name");
				return new ModelAndView("redirect:mnh_input_SHOstrengthDetails");
			}
			else if(validation.DiseasemmrLength(request.getParameter("unit_name1")) == false){
				model.put("msg",validation.unit_nameMSG);
               return new ModelAndView("redirect:mnh_input_SHOstrengthDetails");
				}
			else if (sus_no1.equals("")) {
				model.put("msg", "Please Enter the SUS No");
				return new ModelAndView("redirect:mnh_input_SHOstrengthDetails");
			}
			else if(validation.sus_noLength(request.getParameter("sus_no1")) == false){
				model.put("msg",validation.sus_noMSG);
               return new ModelAndView("redirect:mnh_input_SHOstrengthDetails");
				}
			
		} catch (Exception e) {
		}
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			if (id.equals("")) {
				rs.setCreated_on(new Date());
				rs.setCreated_by(username);
				rs.setSus_no(sus_no1);

				session1.save(rs);

				session1.flush();
				session1.clear();
				model.put("msg", "Data saved Successfully");
				return new ModelAndView("redirect:mnh_input_SHOstrengthDetails");
			} else {

				String hql = "update Tb_Med_Str_Unit set off_lodg_for=:a1,cadet_lodg_for=:a2,mns_lodg_for=:a3,jco_lodg_for=:a4,or_lodg_for=:a5,"
						+ "rect_lodg_for=:a6,cive_lodg_for=:a7,civne_lodg_for=:a8,other_lodg_for=:a9,total_lodger=:a10,"
						+ "off_nonlodg_for=:b1,cadet_nonlodg_for=:b2,mns_nonlodg_for=:b3,jco_nonlodg_for=:b4,or_nonlodg_for=:b5,"
						+ "rect_nonlodg_for=:b6,cive_nonlodg_for=:b7,civne_nonlodg_for=:b8,other_nonlodg_for=:b9,total_nonlodger=:b10,"
						+ "modified_by=:m1,modified_on=:m2 where id=:id";

				Query query = session1.createQuery(hql)
						.setInteger("a1", Integer.parseInt(request.getParameter("off_lodg_for")))
						.setInteger("a2", Integer.parseInt(request.getParameter("cadet_lodg_for")))
						.setInteger("a3", Integer.parseInt(request.getParameter("mns_lodg_for")))
						.setInteger("a4", Integer.parseInt(request.getParameter("jco_lodg_for")))
						.setInteger("a5", Integer.parseInt(request.getParameter("or_lodg_for")))
						.setInteger("a6", Integer.parseInt(request.getParameter("rect_lodg_for")))
						.setInteger("a7", Integer.parseInt(request.getParameter("cive_lodg_for")))
						.setInteger("a8", Integer.parseInt(request.getParameter("civne_lodg_for")))
						.setInteger("a9", Integer.parseInt(request.getParameter("other_lodg_for")))
						.setInteger("a10", Integer.parseInt(request.getParameter("total_lodger")))
						.setInteger("b1", Integer.parseInt(request.getParameter("off_nonlodg_for")))
						.setInteger("b2", Integer.parseInt(request.getParameter("cadet_nonlodg_for")))
						.setInteger("b3", Integer.parseInt(request.getParameter("mns_nonlodg_for")))
						.setInteger("b4", Integer.parseInt(request.getParameter("jco_nonlodg_for")))
						.setInteger("b5", Integer.parseInt(request.getParameter("or_nonlodg_for")))
						.setInteger("b6", Integer.parseInt(request.getParameter("rect_nonlodg_for")))
						.setInteger("b7", Integer.parseInt(request.getParameter("cive_nonlodg_for")))
						.setInteger("b8", Integer.parseInt(request.getParameter("civne_nonlodg_for")))
						.setInteger("b9", Integer.parseInt(request.getParameter("other_nonlodg_for")))
						.setInteger("b10", Integer.parseInt(request.getParameter("total_nonlodger")))
						.setString("m1", username).setTimestamp("m2", new Date())
						.setInteger("id", Integer.parseInt(id));

				int rowCount = query.executeUpdate();
				
				// tx.commit(); session1.close();
				 
				if (rowCount > 0) {
					model.put("msg", "Data Updated Successfully");
				} else {
					model.put("msg", "Data Not Updated Successfully");
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn’t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}

		return new ModelAndView("redirect:mnh_input_search_SHOstrengthDetails");
	}
	@RequestMapping(value = "/edit_sho_strength_input",method = RequestMethod.POST)
	public ModelAndView edit_sho_strength_input(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute(value = "id2") Integer id2) {

		Boolean val = roledao.ScreenRedirect("mnh_input_search_SHOstrengthDetails",
				session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		int userid = (Integer) session.getAttribute("userId");
		

		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		
		Session s1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s1.beginTransaction();
		Query q = null;
		q = s1.createQuery("from Tb_Med_Str_Unit where id=:id");
		q.setInteger("id", id2);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();

		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		Mmap.put("size", list.size());
		Mmap.put("list", list.get(0));
		return new ModelAndView("mnh_input_SHOstrengthTiles");
	}
}
