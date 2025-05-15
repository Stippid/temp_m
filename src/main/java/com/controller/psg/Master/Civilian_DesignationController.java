package com.controller.psg.Master;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.dao.psg.Master.Civilian_DesignationDAO;
import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_DESIGNATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Civilian_DesignationController {

	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	Civilian_DesignationDAO CDDAO;
	
	@Autowired
	RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Civilian_DesignationUrl", method = RequestMethod.GET)
	 public ModelAndView Civilian_DesignationUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 Mmap.put("getCivilianTrade", mcommon.getCivilianTrade());
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 ArrayList<ArrayList<String>> list = CDDAO.search_Civilian_designation("", "active", "0", "", "");
		 Mmap.put("list", list);
		    
		 return new ModelAndView("Civilian_DesignationUrlTiles");
	 }
	
	@RequestMapping(value = "/Civilian_DesignationAction", method = RequestMethod.POST)
	public ModelAndView Civilian_DesignationAction(@ModelAttribute("Civilian_DesignationCMD") TB_PSG_MSTR_CIVILIAN_DESIGNATION com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		
		String designation = request.getParameter("designation").trim();
		String status = request.getParameter("status");
		String classification_services = request.getParameter("classification_services");
		String group1 = request.getParameter("c_group");
		String civilian_trade = request.getParameter("civilian_trade");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (designation.equals("") || designation.equals("null")|| designation.equals(null)) {
			 model.put("msg", "Please Enter Civilian Designation");
			 return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		 if (classification_services.equals("") || classification_services.equals("null")|| classification_services.equals(null)) {
			 model.put("msg", "Please Enter Classification Of Services");
			 return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		 if (group1.equals("") || group1.equals("null")|| group1.equals(null)) {
			 model.put("msg", "Please Enter Civilian Group");
			 return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		 if (designation.equals("") || designation.equals("null")|| designation.equals(null)) {
			 model.put("msg", "Please Enter Award Category");
			 return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		 if (civilian_trade == "0" || civilian_trade.equals("0")) {
			 model.put("msg", "Please Enter Civilian Trade");
			 return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Civilian_DesignationUrl");
			}
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_CIVILIAN_DESIGNATION where designation=:designation and status=:status and id !=:id");
			q0.setParameter("designation", com.getDesignation());  
			q0.setParameter("status", com.getStatus());  
			q0.setParameter("id", com.getId());  
			Long c = (Long) q0.uniqueResult();
			if (id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setDesignation(designation);
				com.setStatus(status);
				com.setClassification_services(classification_services);
				com.setC_group(group1);
				com.setCivilian_trade(Integer.parseInt(civilian_trade));
				if (c == 0) {
					sessionHQL.save(com);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			}
		
			tx.commit();
		}catch(RuntimeException e){
			try{
				tx.rollback();
				model.put("msg", "roll back transaction");
			}catch(RuntimeException rbe){
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
		return new ModelAndView("redirect:Civilian_DesignationUrl");
	}
	
	@RequestMapping(value = "/admin/Search_Civilian_Designation", method = RequestMethod.POST)
	public ModelAndView Search_Civilian_Designation(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "classification_services1", required = false) String classification_services1,
			@RequestParam(value = "c_group1", required = false) String c_group1,
			@RequestParam(value = "civilian_trade1", required = false) String civilian_trade1,
			@RequestParam(value = "designation1", required = false) String designation1,
			@RequestParam(value = "status1", required = false) String status1){
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

			Mmap.put("classification_services1", classification_services1);
			Mmap.put("c_group1", c_group1);
			Mmap.put("civilian_trade1", civilian_trade1);
			Mmap.put("designation1", designation1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("getCivilianTrade", mcommon.getCivilianTrade());
			ArrayList<ArrayList<String>> list = CDDAO.search_Civilian_designation(designation1, status1, civilian_trade1, classification_services1, c_group1);
		    Mmap.put("list", list);
		return new ModelAndView("Civilian_DesignationUrlTiles");
	}
	
	@RequestMapping(value = "/Edit_Civilian_Designation",method = RequestMethod.POST)
	public ModelAndView Edit_Civilian_Designation(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		TB_PSG_MSTR_CIVILIAN_DESIGNATION CD = CDDAO.getCivilianDesByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_civilian_desCMD", CD);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("getCivilianTrade", mcommon.getCivilianTrade());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_Civilian_DesignationTiles");
	}
	
	@RequestMapping(value = "/Edit_civilian_des_Action", method = RequestMethod.POST)
	public ModelAndView Edit_civilian_des_Action(@ModelAttribute("Edit_civilian_desCMD") TB_PSG_MSTR_CIVILIAN_DESIGNATION rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        
       String designation = request.getParameter("designation").trim();
       String c_group = request.getParameter("c_group").trim();
       String classification_services = request.getParameter("classification_services").trim();
       String civilian_trade = request.getParameter("civilian_trade").trim();

       if (designation.equals("") || designation.equals("null") || designation.equals(null)) {
    	   TB_PSG_MSTR_CIVILIAN_DESIGNATION CD = CDDAO.getCivilianDesByid(id);
			 Mmap.put("Edit_civilian_desCMD", CD);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("getCivilianTrade", mcommon.getCivilianTrade());
			 Mmap.put("msg", "Please Enter Civilian Designation");
			return new ModelAndView("Edit_Civilian_DesignationTiles");
		}	
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			 TB_PSG_MSTR_CIVILIAN_DESIGNATION CD = CDDAO.getCivilianDesByid(id);
			 Mmap.put("Edit_civilian_desCMD", CD);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("getCivilianTrade", mcommon.getCivilianTrade());
			 Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Civilian_DesignationTiles");
		}*/
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CIVILIAN_DESIGNATION where designation=:designation  and id !=:id");
			q0.setParameter("designation", designation);  
			q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_CIVILIAN_DESIGNATION set designation=:designation,status=:status,c_group=:c_group,"
					 		+ "classification_services=:classification_services,civilian_trade=:civilian_trade,modified_by=:modified_by,modified_dt=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("designation",designation)
			    			  	.setString("status", rs.getStatus())
			    			  	.setString("c_group",c_group)
			    			  	.setString("classification_services",classification_services)
			    			  	.setInteger("civilian_trade",Integer.parseInt(civilian_trade))
								.setString("modified_by", username).setDate("modified_date", new Date())
								.setInteger("id",id);
			                    msg = query.executeUpdate() > 0 ? "1" :"0";
			                    tx.commit(); 
			                    
			                    if(msg == "1") {
			                    	Mmap.put("msg", "Data Updated Successfully.");
			                    }
			                    else {
			                    	Mmap.put("msg", "Data Not Updated.");
			                    }
				}
				else {
					Mmap.put("msg", "Data already Exist.");
				}
			
		  }catch(RuntimeException e){
	              try{
	                      tx.rollback();
	                      Mmap.put("msg", "roll back transaction");
	              }catch(RuntimeException rbe){
	            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
	              }
	              throw e;
	             
			}finally{
				if(session1!=null){
					session1.close();
				}
			}
		return new ModelAndView("redirect:Civilian_DesignationUrl");
	}
	
	@RequestMapping(value = "/deleteCivilian_Designation", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteCivilian_Designation(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		List<String> liststr = new ArrayList<String>();
		
		String username = session.getAttribute("username").toString();
		
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "update TB_PSG_MSTR_CIVILIAN_DESIGNATION set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
									+ " where id=:id";
			
			 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
					.setString("modified_by", username)
					.setDate("modified_dt", new Date()).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Civilian_DesignationUrl");
	}
}
