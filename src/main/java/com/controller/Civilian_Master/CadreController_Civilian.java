package com.controller.Civilian_Master;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import  com.dao.psg.Civilian_Master.Cadre_CivilianDAO;
import com.models.psg.Civilian_Master.TB_PSG_MSTR_CADRE_CIVILIAN;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class CadreController_Civilian {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
//	@Autowired
//	Search_UpdateOffDataDao UOD;
	
	
	@Autowired
	Cadre_CivilianDAO CadreCivilianDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	private List<Map<String, Object>> lpInout;
	
	
	@RequestMapping(value = "/admin/Cadre_Url", method = RequestMethod.GET)
	 public ModelAndView Cause_of_non_eff_civilianUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		 Mmap.put("list", CadreCivilianDao.search_Cadre_civilian("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
	     Mmap.put("msg", msg);
		 return new ModelAndView("Cadre_CivilianTiles");
	 }
	
	@RequestMapping(value = "/CadreAction", method = RequestMethod.POST)
	public ModelAndView Cause_of_non_effectiveJCOAction(@ModelAttribute("CadreCMD") TB_PSG_MSTR_CADRE_CIVILIAN com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String cadre = request.getParameter("cadre").trim();
		String status = request.getParameter("status");
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		if (cadre.equals("") || cadre.equals("null")|| cadre.equals(null)) {
			 model.put("msg", "Please Enter Cadre");
			 return new ModelAndView("redirect:Cadre_Url");
			}
		if (cadre.length() >50) {
			model.put("msg", "Cadre Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:Cadre_Url");
		}
		if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
			model.put("msg", "Only Select Active Status");
			return new ModelAndView("redirect:Cadre_Url");
		}
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_CADRE_CIVILIAN where upper(cadre)=:cadre and status=:status AND id!=:id");
			q0.setParameter("cadre", cadre.toUpperCase());  
			q0.setParameter("status", status);  
			 
			q0.setParameter("id", Id);  
			Long c = (Long) q0.uniqueResult();
			
			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_date(new Date());
				com.setCadre(cadre);
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
		return new ModelAndView("redirect:Cadre_Url");
	}
	
	@RequestMapping(value = "/admin/Search_Cadre_Master", method = RequestMethod.POST)
	public ModelAndView Search_Cadre_Master(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cadre1", required = false) String cadre1,HttpServletRequest request,@RequestParam(value = "status1", required = false) String status1){
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		
			ArrayList<ArrayList<String>> list = CadreCivilianDao.search_Cadre_civilian(cadre1,status1);
		    Mmap.put("list", list);
		    Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		    Mmap.put("status1", status1);
			Mmap.put("cadre1", cadre1);
		return new ModelAndView("Cadre_CivilianTiles");
	}
	
	@RequestMapping(value = "/Edit_Cadre",method = RequestMethod.POST)
	public ModelAndView Edit_Cadre(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		TB_PSG_MSTR_CADRE_CIVILIAN CN = CadreCivilianDao.getCadreCivilianByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_CadreCMD", CN);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_Cadre_CivilianTiles");
	}
	
@RequestMapping(value = "/Edit_Cadre_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Cause_of_non_effectiveJCOAction(@ModelAttribute("Edit_CadreCMD") TB_PSG_MSTR_CADRE_CIVILIAN rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
	}

	
	
	String username = session.getAttribute("username").toString();
		
		int id = Integer.parseInt(request.getParameter("id"));
	    String cadre = request.getParameter("cadre").trim();
		String status = request.getParameter("status");	
			
		if (cadre.equals("") || cadre.equals("null")|| cadre.equals(null)) {
			TB_PSG_MSTR_CADRE_CIVILIAN authDetails = CadreCivilianDao.getCadreCivilianByid(id);
			 Mmap.put("Edit_CadreCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Cadre");
			 return new ModelAndView("Edit_Cadre_CivilianTiles");
			}
		if (cadre.length() >50) {
			TB_PSG_MSTR_CADRE_CIVILIAN authDetails = CadreCivilianDao.getCadreCivilianByid(id);
			 Mmap.put("Edit_CadreCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Cadre Length Should Be Less Than 50 Characters");
			return new ModelAndView("Edit_Cadre_CivilianTiles");
		}
		/* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			 TB_PSG_MSTR_CADRE_CIVILIAN authDetails = CadreCivilianDao.getCadreCivilianByid(id);
				 Mmap.put("Edit_CadreCMD", authDetails);
				 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				Mmap.put("msg", "Only Select Active Status");
				return new ModelAndView("Edit_Cadre_CivilianTiles");
			}*/
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
        
      /*  String causes_name = request.getParameter("causes_name").trim();
		int type_of_officer = Integer.parseInt(request.getParameter("type_of_officer"));*/

     
		
		if (cadre.equals("") || cadre.equals("null") || cadre.equals(null)) {
			TB_PSG_MSTR_CADRE_CIVILIAN CN = CadreCivilianDao.getCadreCivilianByid(id);
			 Mmap.put("Edit_CadreCMD", CN);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Cadre");
			return new ModelAndView("Edit_Cadre_CivilianTiles");
		}	
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_CADRE_CIVILIAN CN = CadreCivilianDao.getCadreCivilianByid(id);
			 Mmap.put("Edit_CadreCMD", CN);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_Cause_of_non_effective_CivilianTiles");
		}*/
		
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_CADRE_CIVILIAN where upper(cadre)=:cadre and status=:status AND id!=:id");
			q0.setParameter("cadre", cadre.toUpperCase());  
			q0.setParameter("status", status);  
			
			q0.setParameter("id", id);  
			
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_CADRE_CIVILIAN set cadre=:cadre,status=:status,"
					 		+ "modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("cadre",cadre)
			    				.setString("status",status)
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
		return new ModelAndView("redirect:Cadre_Url");
	}
	 
	 @RequestMapping(value = "/Delete_Cadre", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Cadre(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_CADRE_CIVILIAN set modified_by=:modified_by,modified_date=:modified_date,status=:status"
							+ " where id=:id";
	
	 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
			.setString("modified_by", username)
			.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Cadre_Url");
		}
}
