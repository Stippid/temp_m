package com.controller.mnh;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mstr_Icd_codeDAOImpl;
import com.dao.mnh.mstr_System_Code_DAO;
import com.dao.mnh.mstr_System_Code_DAOImpl;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.mnh.Tb_Med_System_Code;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_System_Code_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired 
	private mstr_System_Code_DAOImpl sys;
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	
	int flag = 0;
	String sys_code = "";
	String sys_code_value="";
	String sys_code_desc="";
	
	@RequestMapping(value = "/admin/mnh_sys_code", method = RequestMethod.GET)
	public ModelAndView mnh_sys_code(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		
		Boolean val = roledao.ScreenRedirect("mnh_sys_code", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("msg", msg);
		Mmap.put("flag", flag);
		Mmap.put("sys_code1", sys_code);
		Mmap.put("sys_code_value1", sys_code_value);
		Mmap.put("sys_code_desc1", sys_code_desc);
		
		return new ModelAndView("mnh_systemCodeTiles");
	}

	

	public List<String> getMaxorder_index(String sys_code) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("select cast(max(order_index) as text) from Tb_Med_System_Code where sys_code=:sys_code");
        q.setParameter("sys_code", sys_code);
        @SuppressWarnings("unchecked")
        List<String> list =(List<String>) q.list();
        tx.commit();
        session.close();
        return list;
    }
	
	@RequestMapping(value = "/sys_code_mstrAction", method = RequestMethod.POST)
	public ModelAndView sys_code_mstrAction(@ModelAttribute("sys_code_mstrCMD") Tb_Med_System_Code mb,HttpServletRequest request,
			ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
		
		Boolean val = roledao.ScreenRedirect("mnh_sys_code", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = mb.getId() > 0 ? mb.getId() : 0;	
		
		 String username = session.getAttribute("username").toString();
		 String sys_code = request.getParameter("sys_code");
		 String sys_code_value = request.getParameter("sys_code_value");
		 String sys_code_desc = request.getParameter("sys_code_desc");
		 String sys_code_value_desc = request.getParameter("sys_code_value_desc");
        
		
			if(sys_code == null || sys_code.equals("")){ 
				model.put("msg", "Please Enter the system code.");
			    return new ModelAndView("redirect:mnh_sys_code");
		    }
			if(validation.accession_noLength(sys_code) == false){
		 		model.put("msg",validation.sys_codeMSG);
				return new ModelAndView("redirect:mnh_sys_code");
			}
			if(sys_code_value == null  || sys_code_value.equals("")){ 
            	model.put("msg", "Please Enter the system code value.");
			    return new ModelAndView("redirect:mnh_sys_code");
		    }
           if(validation.DiseaseFamilyLength(sys_code_value) == false){
		 		model.put("msg",validation.sys_code_valueMSG);
				return new ModelAndView("redirect:mnh_sys_code");
			}
            if(sys_code_desc == null  || sys_code_desc.equals("")){ 
				model.put("msg", "Please Enter the system code  desc.");
				return new ModelAndView("redirect:mnh_sys_code");
			}
            if(validation.DiseasemmrLength(sys_code_desc) == false){
		 		model.put("msg",validation.sys_code_descMSG);
				return new ModelAndView("redirect:mnh_sys_code");
			}
            if(sys_code_value_desc == null  || sys_code_value_desc.equals("")){ 
				model.put("msg", "Please Enter the system code value desc.");
				return new ModelAndView("redirect:mnh_sys_code");
			}
            else if(validation.DiseaseNameLength(sys_code_value_desc) == false){
		 		model.put("msg",validation.sys_code_value_descMSG);
				return new ModelAndView("redirect:mnh_sys_code");
			}
            
            List<String> oi = getMaxorder_index(request.getParameter("sys_code"));
			int order_index = 0;

			if(oi.get(0) == null) {
			
				order_index = 1;
			}else {
				order_index = Integer.parseInt(oi.get(0))+ 1;
			}
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {
		 Long c= sys.checkExitstingsystemcode(mb.getSys_code(),mb.getSys_code_value(),id);
   	   
		 if(id==0){
			 mb.setCreated_by(username);
			 mb.setCreated_on(new Date());
			mb.setOrder_index(order_index);
			 if (c == 0) 
			 {
					 session1.save(mb);
					 session1.flush();
					 session1.clear();
					 model.put("msg", "Data Saved Successfully.");
				
			 } 
			 if (c > 0) 
			 {
					model.put("msg", "Data already Exist.");
				
			 }
			
			 return new ModelAndView("redirect:mnh_sys_code");
		 }else{
			 
			 mb.setModified_by(username);
			 mb.setModified_on(new Date());
			 String msg = sys.updateSystemCode(mb);
			 model.put("msg", msg);
			
			 tx.commit();
			
		 }
			}catch(RuntimeException e){
				try{
					tx.rollback();
					model.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(session1!=null){
					session1.close();
				}
			}

	         return new ModelAndView("redirect:mnh_sys_code");
   	   	 
      }
	
	@RequestMapping(value = "/admin/getsyscodeRpt", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getsyscodeRpt(@DatatablesParams DatatablesCriterias criterias,HttpSession session, HttpServletRequest request) {
		if(flag == 0) {
			return null;
		}else {
		DataSet<Map<String, Object>> dataSet = sys.DatatablesCriteriassyscode(criterias,sys_code,sys_code_value,sys_code_desc);
		return DatatablesResponse.build(dataSet, criterias);
	}
	}
	
	@RequestMapping(value = "/Search_mnh_sys_code", method = RequestMethod.POST)
	public ModelAndView Search_mnh_sys_code(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			String sys_code1,String sys_code_value1,String sys_code_desc1) {
		
		Boolean val = roledao.ScreenRedirect("redirect:mnh_sys_code", session.getAttribute("roleid").toString());
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		flag = 1;
		sys_code = sys_code1;
		sys_code_value = sys_code_value1;
		sys_code_desc = sys_code_desc1;
		return new ModelAndView("redirect:mnh_sys_code");
	}
	
	@RequestMapping(value = "/deleteSysCode" , method = RequestMethod.POST)
	public ModelAndView deleteSysCode(@ModelAttribute("id1") int id, HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg
			) {
		
        Boolean val = roledao.ScreenRedirect("redirect:mnh_sys_code", session1.getAttribute("roleid").toString());
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from Tb_Med_System_Code where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			model.put("msg",liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_sys_code");
	}
	
	
	
}
