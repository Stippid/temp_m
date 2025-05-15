package com.controller.tms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Tms_ModelDAO;
import com.models.TB_TMS_MAKE_MASTER;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MODEL_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Common_Mct_ModelController {
	
	@Autowired
	Tms_ModelDAO tmsDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/tms_model_master", method = RequestMethod.GET)
	public ModelAndView tms_model_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("tms_model_master", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("tms_model_masterTiles");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ifmodelidExisits")
	public @ResponseBody Boolean ifmodelidExisits(@RequestParam String model_id,String mct_main_id,String make_id) {
		String hql="from TB_TMS_MODEL_MASTER where model_id = :model_id and  mct_main_id = :mct_main_id and  make_id = :make_id";
		List<TB_TMS_MODEL_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("model_id", model_id);
			query.setParameter("mct_main_id", mct_main_id);
			query.setParameter("make_id", make_id);
			users = (List<TB_TMS_MODEL_MASTER>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	

	@RequestMapping(value = "tms_model_masterAction", method = RequestMethod.POST)
	public ModelAndView tms_model_masterAction(@ModelAttribute("tms_model_masterCmd") TB_TMS_MODEL_MASTER m,HttpServletRequest request,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("tms_model_master", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		String  model_id = request.getParameter("model_id");
		String  mct_main_id = request.getParameter("mct_main_id");
		String  make_id = request.getParameter("make_id");
		String description = request.getParameter("description");
		
		Pattern pattern = Pattern.compile(".*[^0-9].*");	
		String strmodel_id = request.getParameter("model_id").toString();		
		if(!pattern.matcher(strmodel_id).matches() == false) 
		{
			  model.put("msg", "Please Enter valid MODEL Nomenclature.");
			  return new ModelAndView("redirect:tms_model_master");
		}
		 if(mct_main_id.equals("") || mct_main_id.equals("null") || mct_main_id.equals(null))
			{
				model.put("msg", "Please enter MCT Nomenclature.");
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(validation.mct_main_idLength(mct_main_id) == false){
		 		model.put("msg",validation.mct_main_idMSG);
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(make_id.equals("0") || make_id.equals("null") || make_id.equals(null))
			{
				model.put("msg", "Please Select MAKE Nomenclature.");
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(validation.make_noLength(make_id) == false){
		 		model.put("msg",validation.make_noMSG);
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(validation.make_noLength(model_id) == false){
		 		model.put("msg",validation.model_idMSG);
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(description.equals("") || description.equals("null") || description.equals(null))
			{
				model.put("msg", "Please enter MODEL Nomenclature.");
				return new ModelAndView("redirect:tms_model_master");
			}
		 else if(validation.descriptionLength(description) == false){
		 		model.put("msg",validation.descriptionModelMSG);
				return new ModelAndView("redirect:tms_model_master");
			}
			else
			{
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				try
				{
					if(ifmodelidExisits(model_id,mct_main_id,make_id) != false) {
						model.put("msg", "Your Model Id Already exists");
						return new ModelAndView("redirect:tms_model_master");
					}
					int userid = Integer.parseInt(session.getAttribute("userId").toString());
					String username = session.getAttribute("username").toString();
					String creation_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					m.setCreated_by(username);
					m.setCreated_on(creation_date);
					sessionHQL.beginTransaction();
					int did = (Integer) sessionHQL.save(m);
					sessionHQL.getTransaction().commit();
					model.put("msg", "MODEL Successfully Added.");
					return new ModelAndView("redirect:tms_model_master");
				}
				catch (Exception e) {
						sessionHQL.getTransaction().rollback();
						
						return null;
				} 
				finally
				{
					sessionHQL.close();
				}
			}
			
		}
	
	@RequestMapping(value = "/gettmsmodelid", method = RequestMethod.POST)
	@ResponseBody
	public String gettmsmodelid(String m_s,String m_k){
		 return tmsDao.gettmsmodelid(m_s,m_k) ;
	}
}