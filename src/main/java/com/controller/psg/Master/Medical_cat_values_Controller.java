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
import com.dao.psg.Master.Med_cat_valuesDAO;
import com.models.psg.Master.TB_MED_CAT_VALUES;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Medical_cat_values_Controller {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	private Med_cat_valuesDAO med_cat_values_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;


	
	@RequestMapping(value = "/admin/Med_cat_Url", method = RequestMethod.GET)
	 public ModelAndView District(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 Mmap.put("msg", msg);
		 Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
		 Mmap.put("list", med_cat_values_dao.search_med_cat_values("0","","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 return new ModelAndView("Med_cat_values_Tiles" , "med_cat_valuesCMD",new TB_MED_CAT_VALUES() );
	 }
	
	@RequestMapping(value = "/med_cat_valuesAction",method=RequestMethod.POST)
	public ModelAndView med_cat_valuesAction(@ModelAttribute("med_cat_valuesCMD") TB_MED_CAT_VALUES mcv,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			int id = mcv.getId() > 0 ? mcv.getId() : 0;
			
			String username = session.getAttribute("username").toString();
			Date date = new Date();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction	tx = sessionHQL.beginTransaction();
			 
			 String shape_status1 = request.getParameter("shape_status");
			 String values1 = request.getParameter("values").trim();
			 
			 if (mcv.getShape_status() == "0" || mcv.getShape_status() == null || mcv.getShape_status().equals(null)) {
					model.put("msg", "Please Select Shape Status");
		
					return new ModelAndView("redirect:Med_cat_Url");
				}
		
				if (values1.equals("") || values1.equals("null")
						|| values1.equals(null)) {
					model.put("msg", "Please Enter Shape Value");
		
					return new ModelAndView("redirect:Med_cat_Url");
				}
				if (mcv.getStatus() == "inactive" || mcv.getStatus().equals("inactive")) {
					
					model.put("msg", "Only Select Active Status.");
		
					return new ModelAndView("redirect:Med_cat_Url");
		
				}
				
			 try{
				
				Query q0 = sessionHQL.createQuery("select count(id) from TB_MED_CAT_VALUES  where shape_status=:shape_status and status=:status  and values=:values");
				q0.setParameter("shape_status", mcv.getShape_status()); 
				q0.setParameter("values", mcv.getValues());  
				q0.setParameter("status", mcv.getStatus());
				Long c = (Long) q0.uniqueResult();
				
				if (id == 0) {
					mcv.setShape_status(shape_status1);
					mcv.setValues(values1);
					mcv.setCreated_by(username);
					mcv.setCreated_date(date);
					if (c == 0) {
						sessionHQL.save(mcv);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				else {
					
					if (c == 0) {
						
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
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:Med_cat_Url");
		}
	
	@RequestMapping(value = "/admin/GetSearch_med_cat_values", method = RequestMethod.POST)
	public ModelAndView GetSearch_med_cat_values(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "shape_status2", required = false) String shape_status2,
			@RequestParam(value = "values2", required = false) String values2,
			@RequestParam(value = "status2", required = false) String status2
			){
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			if(!values2.equals("")) {
				Mmap.put("values2", values2);
			}
			ArrayList<ArrayList<String>> list = med_cat_values_dao.search_med_cat_values(shape_status2,values2,status2);
				Mmap.put("list", list);
				Mmap.put("status2", status2);
				Mmap.put("shape_status2", shape_status2);
				Mmap.put("values2", values2);
				Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				
			return new ModelAndView("Med_cat_values_Tiles","med_cat_valuesCMD", new TB_MED_CAT_VALUES());
	}
	
	/*@RequestMapping(value = "/Delete_med_cat_values" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_med_cat_values(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from TB_MED_CAT_VALUES where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Data Delete Successfully.");
			} else {
				liststr.add("Data Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Med_cat_Url");
	}*/
	
	@RequestMapping(value = "/Delete_med_cat_values", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_med_cat_values(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		List<String> liststr = new ArrayList<String>();
		
		String username = session.getAttribute("username").toString();
		
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "update TB_MED_CAT_VALUES set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
		return new ModelAndView("redirect:Med_cat_Url");
	}
	@RequestMapping(value = "/Edit_med_cat_values",method=RequestMethod.POST)
	public ModelAndView Edit_med_cat_values(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}		
		TB_MED_CAT_VALUES med_cat_values = med_cat_values_dao.get_med_cat_valuesByid(Integer.parseInt(updateid));
		Mmap.put("Edit_med_cat_valuesCMD", med_cat_values);
		
		Mmap.put("msg", msg);
		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
		Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		return new ModelAndView("Edit_Med_cat_values_Tiles");
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/Edit_med_cat_valuesAction", method = RequestMethod.POST)
	public ModelAndView Edit_med_cat_valuesAction(@ModelAttribute("Edit_med_cat_valuesCMD") TB_MED_CAT_VALUES mcv,
			HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		int id =Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		Date date = new Date();
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction	tx = sessionHQL.beginTransaction();
		 
		 String shape_status1 = request.getParameter("shape_status");
		 String status = request.getParameter("status");
		 String values1 = request.getParameter("values").trim();	
		 
				if (mcv.getShape_status() == "0" || mcv.getShape_status() == null || mcv.getShape_status().equals(null)) {
					TB_MED_CAT_VALUES med_cat_values = med_cat_values_dao.get_med_cat_valuesByid((id));
					model.put("Edit_med_cat_valuesCMD", med_cat_values);
					model.put("getShapeStatusList", mcommon.getShapeStatusList());
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Select Shape Status");
					return new ModelAndView("Edit_Med_cat_values_Tiles");
				}
		
				if (values1.equals("") || values1.equals("null") || values1.equals(null)) {
					TB_MED_CAT_VALUES med_cat_values = med_cat_values_dao.get_med_cat_valuesByid((id));
					model.put("Edit_med_cat_valuesCMD", med_cat_values);
					model.put("getShapeStatusList", mcommon.getShapeStatusList());
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Enter Values");
					return new ModelAndView("Edit_Med_cat_values_Tiles");
				}
				/*if (mcv.getStatus() == "inactive" || mcv.getStatus().equals("inactive")) {
					TB_MED_CAT_VALUES med_cat_values = med_cat_values_dao.get_med_cat_valuesByid((id));
					model.put("Edit_med_cat_valuesCMD", med_cat_values);
					model.put("getShapeStatusList", mcommon.getShapeStatusList());
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_Med_cat_values_Tiles");
		
				}*/
	
		 	try {
		 		
		 		Query q0 = sessionHQL.createQuery("select count(id) from TB_MED_CAT_VALUES  where shape_status=:shape_status and values=:values and status=:status and id !=:id");
				q0.setParameter("shape_status", mcv.getShape_status()); 
				q0.setParameter("values", mcv.getValues()); 
				q0.setParameter("id", id);
				q0.setParameter("status", status); 
				Long c = (Long) q0.uniqueResult();
					
					if(c==0) {
						 String hql = "update TB_MED_CAT_VALUES set shape_status=:shape_status,values=:values,modified_by=:modified_by,modified_date=:modified_date,status=:status"
									+ " where id=:id";
						 
				                                   
				    	  Query query = sessionHQL.createQuery(hql)
				    			  	.setString("shape_status",shape_status1)
				    			  	.setString("values",values1)
				    			  	.setString("status",status)
									.setString("modified_by", username)
									.setDate("modified_date", new Date())
									.setInteger("id",id);
				                    msg = query.executeUpdate() > 0 ? "0" :"1";
				                    tx.commit(); 
				                    
				                    if(msg == "0") {
				                    	 model.put("msg", "Data Updated Successfully.");
				                    }
				                    else {
				                    	model.put("msg", "Data Not Updated.");
				                    }
					}
					else {
						model.put("msg", "Data already Exist.");
					}
				
			  }catch(RuntimeException e){
	              try{
	                      tx.rollback();
	                      model.put("msg", "roll back transaction");
	              }catch(RuntimeException rbe){
	                      model.put("msg", "Couldn�t roll back transaction " + rbe);
	              }
	              throw e;
	             
			}finally{
				if(sessionHQL!=null){
					sessionHQL.close();
				}
			}
		return new ModelAndView("redirect:Med_cat_Url");
	}

}
