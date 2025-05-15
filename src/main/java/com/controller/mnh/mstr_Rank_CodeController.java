package com.controller.mnh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.mstr_Rank_CodeDAO;
import com.models.mnh.Tb_Med_RankCode;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_Rank_CodeController {	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private mstr_Rank_CodeDAO rankdao;
	
	
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	MNH_CommonController mcommon = new MNH_CommonController();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	
	@RequestMapping(value = "/admin/mnh_rank_master", method = RequestMethod.GET)
	public ModelAndView mnh_rank_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_rank_master", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE","",session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY","",session));
		Mmap.put("msg", msg);

		return new ModelAndView("mnh_rank_masterTiles");
	}
	
	@RequestMapping(value = "/createRankAction",method=RequestMethod.POST)
	public ModelAndView createRank(@ModelAttribute("createRankCMD") Tb_Med_RankCode rm,
			@RequestParam(value = "msg", required = false) String msg1,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		
		
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mnh_rank_master", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(request.getParameter("service").equals("-1") || request.getParameter("service").equals(null)){ 
			model.put("msg", "Please Select Service.");
			return new ModelAndView("redirect:mnh_rank_master");
		}
		if(request.getParameter("category_code").equals("-1") || request.getParameter("category_code").equals(null)){ 
			model.put("msg", "Please Select Category");
			return new ModelAndView("redirect:mnh_rank_master");
		}
		if(request.getParameter("rank_code").equals("") || request.getParameter("rank_code").equals(null)){ 
			model.put("msg", "Please Enter Rank code");
			return new ModelAndView("redirect:mnh_rank_master");
		}
		if(validation.RankCodeLength(request.getParameter("rank_code")) == false){
	 		model.put("msg",validation.rank_codeMSG);
			return new ModelAndView("redirect:mnh_rank_master");
		}
		if(request.getParameter("rank_desc").equals("")){ 
			model.put("msg", "Please Enter Rank desc");
			return new ModelAndView("redirect:mnh_rank_master");
		}
		if(validation.RankDescLength(request.getParameter("rank_desc")) == false){
	 		model.put("msg",validation.rank_descMSG);
			return new ModelAndView("redirect:mnh_rank_master");
		}
			 
			int id = rm.getId() > 0 ? rm.getId() : 0;
			
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			try{
				
				Query q0 = sessionHQL.createQuery("select count(id) from Tb_Med_RankCode where service=:service and category_code=:category_code and upper(rank_code)=:rank_code and upper(rank_desc)=:rank_desc and id !=:id");
				q0.setParameter("service", rm.getService());  
				q0.setParameter("category_code", rm.getCategory_code());
				q0.setParameter("rank_code", rm.getRank_code().toUpperCase());
				q0.setParameter("rank_desc", rm.getRank_desc().toUpperCase());
				q0.setParameter("id", id);  
				Long c = (Long) q0.uniqueResult();

				if (id == 0) {
					rm.setCreated_by(username);
					rm.setCreated_on(date);
					if (c == 0) {
						sessionHQL.save(rm);
						sessionHQL.flush();
						sessionHQL.clear();
						model.put("msg", "Data Saved Successfully.");

					} else {
						model.put("msg", "Data already Exist.");
					}
				}
				else {
					rm.setModified_by(username);
					rm.setModified_on(date);
					if (c == 0) {
						String msg = rankdao.updaterankcode(rm);
						model.put("msg", msg);
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
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:mnh_rank_master");
		}
	
	
	@RequestMapping(value = "/getSearchRankMaster",method=RequestMethod.POST)
	public ModelAndView getSearchRankMaster(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "service1", required = false) String service1,HttpServletRequest request,
		@RequestParam(value = "category_code1", required = false) String category_code1,
		@RequestParam(value = "rank_code1", required = false) String rank_code1,
		@RequestParam(value = "rank_desc1", required = false) String rank_desc1){
	 	
		Boolean val = roledao.ScreenRedirect("mnh_rank_master", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE","",session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY","",session));
	 	Mmap.put("service1", service1);
	 	Mmap.put("category_code1", category_code1);
	 	Mmap.put("rank_code1", rank_code1);
	 	Mmap.put("rank_desc1", rank_desc1);
	 	ArrayList<ArrayList<String>> list =rankdao.getSearchRankMaster(service1,category_code1,rank_code1,rank_desc1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("mnh_rank_masterTiles");
	}
	
	
	
	@RequestMapping(value = "/deleteRankMasterURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deleteRankMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		Boolean val = roledao.ScreenRedirect("mnh_rank_master", sessionA.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from Tb_Med_RankCode where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UnSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:mnh_rank_master");
	}
	
}
