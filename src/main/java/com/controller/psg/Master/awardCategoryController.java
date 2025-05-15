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
import com.dao.psg.Master.AwardCatDAO;
import com.models.psg.Master.TB_PSG_MSTR_AWARD_CAT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class awardCategoryController {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	
	@Autowired
	AwardCatDAO aCat;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/awardCategoryUrl", method = RequestMethod.GET)
	 public ModelAndView awardCategoryUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		 Mmap.put("list", aCat.search_AwardCat("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("AwardCategoryTiles");
	 }
	
	@RequestMapping(value = "/awardCategoryAction", method = RequestMethod.POST)
	public ModelAndView awardCategoryAction(@ModelAttribute("awardCategoryCMD") TB_PSG_MSTR_AWARD_CAT com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	 
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String awardCat = request.getParameter("awardCat").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (awardCat.equals("") || awardCat.equals("null")|| awardCat.equals(null)) {
			 model.put("msg", "Please Enter Award Category");
			 return new ModelAndView("redirect:awardCategoryUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:awardCategoryUrl");
			}
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_AWARD_CAT where award_cat=:awardCat");
			q0.setParameter("awardCat", awardCat);  
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setAward_cat(awardCat);
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
		return new ModelAndView("redirect:awardCategoryUrl");
	}
	
	@RequestMapping(value = "/admin/getAwardCatSearch", method = RequestMethod.POST)
	public ModelAndView getAwardCatSearch(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "awardCat1", required = false) String awardCat1,
			@RequestParam(value = "status1", required = false) String status1){
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
			Mmap.put("awardCat1", awardCat1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = aCat.search_AwardCat(awardCat1,status1);
		    Mmap.put("list", list);
		return new ModelAndView("AwardCategoryTiles","awardCategoryCMD",new TB_PSG_MSTR_AWARD_CAT());
	}
	
	@RequestMapping(value = "/Edit_awardCat",method = RequestMethod.POST)
	public ModelAndView Edit_awardCat(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		
		
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		TB_PSG_MSTR_AWARD_CAT authDetails = aCat.getmtByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_awardCatCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_AwardCatTiles");
	}
	
	@RequestMapping(value = "/Edit_Award_CategoryAction", method = RequestMethod.POST)
	public ModelAndView Edit_Award_CategoryAction(@ModelAttribute("Edit_Award_CategoryCMD") TB_PSG_MSTR_AWARD_CAT rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
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
        
       String awardCat = request.getParameter("awardCat").trim();

		if (awardCat.equals("") || awardCat.equals("null") || awardCat.equals(null)) {
			TB_PSG_MSTR_AWARD_CAT authDetails = aCat.getmtByid(id);
			 Mmap.put("Edit_awardCatCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Please Enter Award Category");
			return new ModelAndView("Edit_AwardCatTiles");
		}	
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_AWARD_CAT authDetails = aCat.getmtByid(id);
			 Mmap.put("Edit_awardCatCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_AwardCatTiles");
		}*/
		
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_AWARD_CAT where award_cat=:awardCat  and id !=:id");
			q0.setParameter("awardCat", awardCat);  
			q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_AWARD_CAT set award_cat=:award_cat,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("award_cat",awardCat)
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
		return new ModelAndView("redirect:awardCategoryUrl");
	}
	 
	 @RequestMapping(value = "/deleteAwardURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deleteAwardURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_AWARD_CAT set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:awardCategoryUrl");
		}

}
