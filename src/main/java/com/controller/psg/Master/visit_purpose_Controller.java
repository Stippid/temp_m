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
import com.dao.psg.Master.Purposeof_VisitDAO;
import com.models.psg.Master.TB_PSG_MSTR_PURPOSEOF_VISIT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class visit_purpose_Controller {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	Purposeof_VisitDAO purDao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/visitPurposeUrl", method = RequestMethod.GET)
	 public ModelAndView visitPurposeUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		 Mmap.put("list", purDao.search_visitPurposedtl("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("VisitPurposeTiles");
	 }
	
	@RequestMapping(value = "/VisitPurposeAction", method = RequestMethod.POST)
	public ModelAndView VisitPurposeAction(@ModelAttribute("VisitPurposeCMD") TB_PSG_MSTR_PURPOSEOF_VISIT com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String visitpurpose = request.getParameter("visitpurpose").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (visitpurpose.equals("") || visitpurpose.equals("null")|| visitpurpose.equals(null)) {
			 model.put("msg", "Please Enter Type Of Entry.");
			 return new ModelAndView("redirect:visitPurposeUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:visitPurposeUrl");
			}
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_PURPOSEOF_VISIT where visit_purpose=:visitpurpose and status=:status  and id !=:id");
			q0.setParameter("visitpurpose", visitpurpose);  
			q0.setParameter("status", status);
			q0.setParameter("id", Id);  
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setVisit_purpose(visitpurpose);
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
		return new ModelAndView("redirect:visitPurposeUrl");
	}
	
	@RequestMapping(value = "/admin/getVisitPurposeSearch", method = RequestMethod.POST)
	public ModelAndView getVisitPurposeSearch(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "visitpurpose1", required = false) String visitpurpose1,
			@RequestParam(value = "status1", required = false) String status1){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
			Mmap.put("visitpurpose1", visitpurpose1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = purDao.search_visitPurposedtl(visitpurpose1,status1);
		    Mmap.put("list", list);
		return new ModelAndView("VisitPurposeTiles","VisitPurposeCMD",new TB_PSG_MSTR_PURPOSEOF_VISIT());
	}
	
	@RequestMapping(value = "/Edit_VisitPurpose", method = RequestMethod.POST)
	public ModelAndView Edit_VisitPurpose(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit,HttpServletRequest request) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_PSG_MSTR_PURPOSEOF_VISIT authDetails = purDao.getmtByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_VisitPurposeCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_VistiPurposeTiles");
	}
	
	 @RequestMapping(value = "/Edit_purposevisitAction", method = RequestMethod.POST)
		public ModelAndView Edit_purposevisitAction(@ModelAttribute("Edit_purposevisitCMD") TB_PSG_MSTR_PURPOSEOF_VISIT rs,
				HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
	       String visitpurpose = request.getParameter("visitpurpose").trim();

			if (visitpurpose.equals("") || visitpurpose.equals("null") || visitpurpose.equals(null)) {
				TB_PSG_MSTR_PURPOSEOF_VISIT authDetails = purDao.getmtByid(id);
				 Mmap.put("Edit_VisitPurposeCMD", authDetails);
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("msg", "Please Enter Type Of Entry");
				return new ModelAndView("Edit_VistiPurposeTiles");
			}	
			if (rs.getStatus().equals("")) {
				TB_PSG_MSTR_PURPOSEOF_VISIT authDetails = purDao.getmtByid(id);
				 Mmap.put("Edit_VisitPurposeCMD", authDetails);
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				 Mmap.put("msg", "Please Select Status");
				return new ModelAndView("Edit_VistiPurposeTiles");
			}
			
	        try {
				
	        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_PURPOSEOF_VISIT where visit_purpose=:visitpurpose and status=:status  and id !=:id");
				q0.setParameter("visitpurpose", visitpurpose);  
				q0.setParameter("status", status);
			     q0.setParameter("id", id); 
					Long c = (Long) q0.uniqueResult();
					
					if(c==0) {
						 String hql = "update TB_PSG_MSTR_PURPOSEOF_VISIT set visit_purpose=:visitpurpose,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
									+ " where id=:id";
				                                   
				    	  Query query = session1.createQuery(hql)
				    			  	.setString("visitpurpose",visitpurpose)
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
			return new ModelAndView("redirect:visitPurposeUrl");
		}
	 
	 
	 @RequestMapping(value = "/deleteVisitURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deleteVisitURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_PURPOSEOF_VISIT set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:visitPurposeUrl");
		}
	
}
