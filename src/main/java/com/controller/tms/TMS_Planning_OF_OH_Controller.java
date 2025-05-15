package com.controller.tms;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.AddMctNoDAO;
import com.dao.tms.Qfd_DAO;
import com.models.TB_TMS_MCT_SLOT_MASTER;
import com.models.TB_TMS_PLANNING_OF_OH;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TMS_Planning_OF_OH_Controller {

	@Autowired
	AddMctNoDAO dao;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private  Qfd_DAO Q_dao;
	
	@RequestMapping(value = "/QH_url")
	public ModelAndView QH_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Qfd_url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if(request.getHeader("Referer") == null ) {
//			msg = "";
//		}
		String select="<option value='0'>-- Select --</option>";
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Line_dte")) {
			model.put("roleAccess",roleAccess);
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
		
			model.put("roleAccess",roleAccess);
			model.put("selectLine_dte", select);
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		model.put("getLine_DteList2",roledao.getLine_DteList(""));
		return new ModelAndView("QH_abc_Tiles");
	}
	
	@RequestMapping(value = "/get_count_oh", method = RequestMethod.POST)
	public @ResponseBody long  get_count_oh(String Search,String type_of_veh,String line_dte,String sub_cat,
			 HttpSession sessionUserId
		) {
		String roleType = sessionUserId.getAttribute("roleType").toString();
		
return dao.getdatacount_oh(Search,type_of_veh,line_dte, sub_cat,"", "", sessionUserId);
	}
	

	@RequestMapping(value = "/get_count_data_oh", method = RequestMethod.POST)
	public @ResponseBody  List<Map<String, Object>>  get_count_data_oh(int startPage, int pageLength, String Search,String type_of_veh,
			String line_dte,		String sub_cat,	String orderColunm, String orderType, HttpSession sessionUserId ) {
		return dao.getdata_oh(startPage, pageLength, Search,type_of_veh,line_dte,sub_cat, orderColunm, orderType, sessionUserId
			);
	}
	
	@RequestMapping(value = "/Save_Oh_single", method = RequestMethod.POST)
	public @ResponseBody String Save_Oh_single(String type_of_veh, String line_dte, String service_model,String service_model_mgs,String service_model_eme,String service_model_os,
			String mct_main_id, String mct_nomen, String industry_mod, String industry_mod_mgs,  String industry_mod_eme,  String industry_mod_os, 
			String totaltgt, String totaltgt_mgs, String totaltgt_eme, String totaltgt_os,
			String remark,
			String py,HttpSession sessionUserId) {
		String msg = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("mct_main_id"+mct_main_id+"industry_mod_mgs"+industry_mod_mgs+"industry_mod_mgs"+industry_mod_mgs);
		Query q = session.createQuery("from TB_TMS_PLANNING_OF_OH where mct_main_id=:mct_main_id and status=0 order by id desc ");
		q.setString("mct_main_id", mct_main_id);
		List<TB_TMS_PLANNING_OF_OH> result = q.list();

		@SuppressWarnings("unchecked")
		List<TB_TMS_PLANNING_OF_OH> list = (List<TB_TMS_PLANNING_OF_OH>) q.list();
		if(list.isEmpty())
		{
			TB_TMS_PLANNING_OF_OH tm = new TB_TMS_PLANNING_OF_OH();
			tm.setIndustry_mode_mgs(Integer.parseInt(industry_mod_mgs));
			tm.setIndustry_mode(Integer.parseInt(industry_mod));
			tm.setIndustry_mode_eme(Integer.parseInt(industry_mod_eme));
			tm.setIndustry_mode_os(Integer.parseInt(industry_mod_os));
			
			
			tm.setMct_main_id(mct_main_id);
			
			tm.setService_mode(Integer.parseInt(service_model));
			tm.setService_mode_mgs(Integer.parseInt(service_model_mgs));
			tm.setService_mode_eme(Integer.parseInt(service_model_eme));
			tm.setService_mode_os(Integer.parseInt(service_model_os));
			
			
			tm.setVeh_cat(mct_nomen);
			tm.setType_of_veh(type_of_veh);
			
			tm.setTotal_tgt(Integer.parseInt(totaltgt));
			tm.setTotal_tgt_mgs(Integer.parseInt(totaltgt_mgs));
			tm.setTotal_tgt_eme(Integer.parseInt(totaltgt_eme));
			tm.setTotal_tgt_os(Integer.parseInt(totaltgt_os));
			
			
			tm.setStatus(0);
			tm.setPy(Integer.parseInt(py));
			int a = (int) session.save(tm);
			tx.commit();
			session.close();
			if (a != 0) {
				msg = "Saved Successfully";
			} else {
				msg = "Data Not Saved ";
			}
			return msg;
		}
		else{

			String hqlUpdate = "update TB_TMS_PLANNING_OF_OH set py=:py ,industry_mode=:industry_mode, "
					+ "industry_mode_mgs=:industry_mode_mgs, industry_mode_eme=:industry_mode_eme,industry_mode_os=:industry_mode_os,"
	                + "service_mode_mgs=:service_mode_mgs, service_mode=:service_mode, "
	                + "service_mode_eme=:service_mode_eme,service_mode_os=:service_mode_os,"
	                + "veh_cat=:veh_cat, type_of_veh=:type_of_veh, total_tgt=:total_tgt,total_tgt_mgs=:total_tgt_mgs, "
	                + "total_tgt_eme=:total_tgt_eme,total_tgt_os=:total_tgt_os,"
	                + "status=:status where mct_main_id=:mct_main_id and status=0 and id=:id";
	int app = session.createQuery(hqlUpdate)
			 	.setInteger("py", Integer.parseInt(py))
	                .setInteger("industry_mode", Integer.parseInt(industry_mod))
	                .setInteger("service_mode", Integer.parseInt(service_model))
	                .setInteger("industry_mode_os", Integer.parseInt(industry_mod_os))
	                .setInteger("service_mode_os", Integer.parseInt(service_model_os))
	                .setInteger("industry_mode_eme", Integer.parseInt(industry_mod_eme))
	                .setInteger("service_mode_eme", Integer.parseInt(service_model_eme))
	                .setInteger("industry_mode_mgs", Integer.parseInt(industry_mod_mgs))
	                .setInteger("service_mode_mgs", Integer.parseInt(service_model_mgs))
	                .setString("veh_cat", mct_nomen)
	                .setString("type_of_veh", type_of_veh)
	                .setInteger("total_tgt", Integer.parseInt(totaltgt))
	                .setInteger("total_tgt_mgs", Integer.parseInt(totaltgt_mgs))
	                 .setInteger("total_tgt_eme", Integer.parseInt(totaltgt_eme))
	                .setInteger("total_tgt_os", Integer.parseInt(totaltgt_os))
	                .setInteger("status", 0) 
	                .setString("mct_main_id", mct_main_id)
	                .setInteger("id", list.get(0).getId())
	                .executeUpdate();
		
			if (app > 0) {
				msg = " Updated Successfully.";
			} 
		else
		{
			msg="Data Not Updated .";
		}
			tx.commit();
			session.close();			
		}
		
		
		
		return msg;
	}
	
}
