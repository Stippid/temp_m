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
import com.dao.psg.Master.FiringResultDAO;
import com.models.psg.Master.TB_PSG_MSTR_FIRING_RESULT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class FiringResultController {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	FiringResultDAO fire;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/FiringResultUrl", method = RequestMethod.GET)
	 public ModelAndView FiringResultUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		 Mmap.put("list", fire.search_FiringResult("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("FiringTiles");
	 }

	@RequestMapping(value = "/firingresultAction", method = RequestMethod.POST)
	public ModelAndView firingresultAction(@ModelAttribute("firingresultCMD") TB_PSG_MSTR_FIRING_RESULT com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		
		
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String firing = request.getParameter("firing").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (firing.equals("") || firing.equals("null")|| firing.equals(null)) {
			 model.put("msg", "Please Enter Firing Result");
			 return new ModelAndView("redirect:FiringResultUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:FiringResultUrl");
			}
		 
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_FIRING_RESULT where firing_result=:firing and status=:status and id !=:id");
			q0.setParameter("firing", firing);  
			q0.setParameter("status", status);  
			q0.setParameter("id", Id);  
			
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setFiring_result(firing);
				com.setStatus(status);
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
		return new ModelAndView("redirect:FiringResultUrl");
	}
	
	
	@RequestMapping(value = "/admin/getFiringResiltSearch", method = RequestMethod.POST)
	public ModelAndView getFiringResiltSearch(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "firing1", required = false) String firing1,
			@RequestParam(value = "status1", required = false) String status1){
		

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			Mmap.put("firing1", firing1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = fire.search_FiringResult(firing1, status1);
		    Mmap.put("list", list);
		return new ModelAndView("FiringTiles","firingresultCMD",new TB_PSG_MSTR_FIRING_RESULT());
	}
	
	@RequestMapping(value = "/Edit_FiringResult",method = RequestMethod.POST)
	public ModelAndView Edit_FiringResult(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit,HttpServletRequest request) {
		
		 
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			TB_PSG_MSTR_FIRING_RESULT authDetails = fire.getmtByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_fireCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_FiringTiles");
	}
	
	
	@RequestMapping(value = "/Edit_FiringAction", method = RequestMethod.POST)
	public ModelAndView Edit_FiringAction(@ModelAttribute("Edit_FiringCMD") TB_PSG_MSTR_FIRING_RESULT rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
	
		String status = request.getParameter("status");
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = session1.beginTransaction();
      
     String firing = request.getParameter("firing").trim();

		if (firing.equals("") || firing.equals("null") || firing.equals(null)) {
			TB_PSG_MSTR_FIRING_RESULT authDetails = fire.getmtByid(id);
			 Mmap.put("Edit_fireCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Firing Result");
			return new ModelAndView("Edit_FiringTiles");
		}	
		if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_FIRING_RESULT authDetails = fire.getmtByid(id);
			 Mmap.put("Edit_fireCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Select Status");
			return new ModelAndView("Edit_FiringTiles");
		}
		
      try {
			
      	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_FIRING_RESULT where firing_result=:firing and status=:status and id !=:id");
			q0.setParameter("firing", firing); 
			q0.setParameter("status", status);  
		    q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_FIRING_RESULT set firing_result=:firing,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("firing",firing)
			    			  	.setString("status", rs.getStatus())
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
		return new ModelAndView("redirect:FiringResultUrl");
	}
	
	
	@RequestMapping(value = "/deletefiringResultURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deletefiringResultURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);
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
			 
			 String hqlUpdate = "update TB_PSG_MSTR_FIRING_RESULT set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
		return new ModelAndView("redirect:FiringResultUrl");
	}

}
