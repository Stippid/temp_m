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
import com.dao.psg.Master.CopeCodeDAO;
import com.models.psg.Master.TB_PSG_MSTR_COPECODE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class CopeCodeController {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	CopeCodeDAO ccDAO;
	
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/CopecodeUrl", method = RequestMethod.GET)
	 public ModelAndView CopecodeUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		 
		 Mmap.put("list", ccDAO.search_Copecode("","active","",""));
		 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("CopeCodeTiles");
	 }
	
	@RequestMapping(value = "/copecodeAction", method = RequestMethod.POST)
	public ModelAndView copecodeAction(@ModelAttribute("copecodeCMD") TB_PSG_MSTR_COPECODE com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String cope = request.getParameter("cope");
		String value = request.getParameter("value").trim();
		String description	= request.getParameter("description").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		 
		 if (cope.equals("0") || cope.equals("null")|| cope.equals(null)) {
			 model.put("msg", "Please Select Cope Code");
			 return new ModelAndView("redirect:CopecodeUrl");
			}
		 if (value.equals("") || value.equals("null")|| value.equals(null)) {
			 model.put("msg", "Please Enter Value");
			 return new ModelAndView("redirect:CopecodeUrl");
			}
		 if (description.equals("") || description.equals("null")|| description.equals(null)) {
			 model.put("msg", "Please Enter Discription");
			 return new ModelAndView("redirect:CopecodeUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:CopecodeUrl");
			}
		
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_COPECODE where cope_code=:cope and value=:value and description=:description");
			q0.setParameter("cope", cope).setParameter("value", value).setParameter("description", description);  
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setCope_code(cope);
				com.setValue(value);
				com.setDescription(description);
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
		return new ModelAndView("redirect:CopecodeUrl");
	}
	
	@RequestMapping(value = "/admin/getcopecodeSearch", method = RequestMethod.POST)
	public ModelAndView getcopecodeSearch(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "copeCode1", required = false) String copeCode1,
			@RequestParam(value = "value1", required = false) String value1,
			@RequestParam(value = "description1", required = false) String description1,
			@RequestParam(value = "status1", required = false) String status1){
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
			Mmap.put("copeCode1", copeCode1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = ccDAO.search_Copecode(copeCode1, status1,value1,description1);
		    Mmap.put("list", list);
		return new ModelAndView("CopeCodeTiles","copecodeCMD",new TB_PSG_MSTR_COPECODE());
	}
	
	@RequestMapping(value = "/Edit_copeCode",method=RequestMethod.POST)
	public ModelAndView Edit_copeCode(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		
		TB_PSG_MSTR_COPECODE authDetails = ccDAO.getmtByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_ccCMD", authDetails);
			 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_COPECODETiles");
	}
	
	
	@RequestMapping(value = "/Edit_CopeCodeAction", method = RequestMethod.POST)
	public ModelAndView Edit_CopeCodeAction(@ModelAttribute("Edit_CopeCodeCMD") TB_PSG_MSTR_COPECODE rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);
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
        
       String copecode = request.getParameter("cope");
       String value = request.getParameter("value").trim();
       String description = request.getParameter("description").trim();

		if (copecode.equals("") || copecode.equals("null") || copecode.equals(null)) {
			TB_PSG_MSTR_COPECODE authDetails = ccDAO.getmtByid(id);
			 Mmap.put("Edit_ccCMD", authDetails);
			 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Cope Code");
			return new ModelAndView("Edit_COPECODETiles");
		}	
		if (value.equals("") || value.equals("null") || value.equals(null)) {
			TB_PSG_MSTR_COPECODE authDetails = ccDAO.getmtByid(id);
			 Mmap.put("Edit_ccCMD", authDetails);
			 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Value");
			return new ModelAndView("Edit_COPECODETiles");
		}
		if (description.equals("") || description.equals("null") || description.equals(null)) {
			TB_PSG_MSTR_COPECODE authDetails = ccDAO.getmtByid(id);
			 Mmap.put("Edit_ccCMD", authDetails);
			 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Please Enter Description");
			return new ModelAndView("Edit_COPECODETiles");
		}
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_COPECODE authDetails = ccDAO.getmtByid(id);
			 Mmap.put("Edit_ccCMD", authDetails);
			 Mmap.put("getCopeCodeList", mcommon.getCopeCodeList());
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_COPECODETiles");
		}*/
		
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_COPECODE where cope_code=:copecode and value=:value and description=:description and id !=:id");
			q0.setParameter("copecode", copecode).setParameter("value", value).setParameter("description", description);  
			q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_COPECODE set cope_code=:copecode,value=:value,description=:description,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("copecode",copecode)
			    			  	.setString("value",value)
			    			  	.setString("description",description)
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
		return new ModelAndView("redirect:CopecodeUrl");
	}
	
	@RequestMapping(value = "/deleteCopeCodeURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteCopeCodeURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);
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
			 
			 String hqlUpdate = "update TB_PSG_MSTR_COPECODE set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
		return new ModelAndView("redirect:CopecodeUrl");
	}
}
