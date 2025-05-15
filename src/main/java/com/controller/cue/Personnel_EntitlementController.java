package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.Personnel_EntitlementDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Personnel_EntitlementController {
	@Autowired
	private Personnel_EntitlementDAO pdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	cueContoller M = new cueContoller();	

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	

	@Autowired
	private Cue_wepe_conditionDAO vetting;	
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/pers_entitUrl", method = RequestMethod.GET)
	public ModelAndView pers_entitUrl(@ModelAttribute("getwe_pe_no") String getwe_pe_no ,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("pers_entitUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		Mmap.put("getwe_pe_no", getwe_pe_no);
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("pers_entitTile","pers_entitCmd",new CUE_TB_MISO_WEPE_PERS_DET());
	}
	
	@RequestMapping(value = "/admin/pers_entitUrl1", method = RequestMethod.POST)
	public ModelAndView pers_entitUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "category_of_persn1", required = false) String category_of_persn,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "appt_trade1", required = false) String appt_trade,
			@RequestParam(value = "parent_arm1", required = false) String parent_arm,
			@RequestParam(value = "auth_amt1", required = false) String auth_amt,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "regt1", required = false) String regt,
			@RequestParam(value = "ere1", required = false) String ere,
			@RequestParam(value = "total1", required = false) String total,
			@RequestParam(value = "we_pe1", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
			Mmap.put("we_pe1", we_pe);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("total1", total);
			Mmap.put("ere1", ere);
			Mmap.put("regt1", regt);
			Mmap.put("status1", status);
				
			List<Map<String, Object>> list= pdao.getSearchPersonnelEntitlementReport(we_pe_no,status,roleType,category_of_persn,rank,roleAccess);
			List<Map<String, Object>> list1 =pdao.getSummaryPersonnelEntitlementReport1(we_pe_no,status);
			Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
			Mmap.put("list", list);
			Mmap.put("list1", list1);
			Mmap.put("list.size()", list.size());	
			Mmap.put("list1.size()", list1.size());
			Mmap.put("roleType", roleType);
			
		return new ModelAndView("pers_entitTile");
	}
		
	@RequestMapping(value = "/pers_entitAct", method = RequestMethod.POST)
	public ModelAndView pers_entitAct(@ModelAttribute("pers_entitCmd") CUE_TB_MISO_WEPE_PERS_DET rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int roleid = (Integer)session.getAttribute("roleid");	
		String we_pe_no =request.getParameter("we_pe_no");
		String category_of_persn =request.getParameter("category_of_persn");
		String rank_cat =request.getParameter("rank_cat");
		String app_trd_code =request.getParameter("app_trd_code");
		String rank =request.getParameter("rank");
		String arm_code =request.getParameter("arm_code");
		String we_pe=request.getParameter("we_pe");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		
	try
		{
		 if(we_pe == ""  || we_pe == null || we_pe == "null" || we_pe.equals(null) )
			{
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:pers_entitUrl");
			}	
		 
		if(rs.getWe_pe_no().equals(""))
		 {
			 model.put("msg", "Please Enter WE/PE No");
			 return new ModelAndView("redirect:pers_entitUrl");
		 }
		if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
			model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:pers_entitUrl");
		}
		if(rs.getCategory_of_persn().equals(""))
		{
			model.put("msg", "Please Select Category of Personnel");
			return new ModelAndView("redirect:pers_entitUrl");
		}
		
		if(rs.getCategory_of_persn()!="")
		{
			if(rs.getCategory_of_persn().equals("1")) { 
				if(rs.getArm_code().equals(""))
				{
				model.put("msg", "Please Select Parent Arm");
				return new ModelAndView("redirect:pers_entitUrl");
				
				}
			}
			
		}
		if(rs.getRank_cat().equals(""))
		{
			model.put("msg", "Please Select Category");
			return new ModelAndView("redirect:pers_entitUrl");
			
		}
		if(rs.getAppt_trade() == "" )
		{
			model.put("msg", "Please Enter Common Appt/Trade");
			return new ModelAndView("redirect:pers_entitUrl");
			
		}
		if(rs.getRank() =="" || rs.getRank() == null )
		 {
				model.put("msg", "Please Select Rank");
				return new ModelAndView("redirect:pers_entitUrl");
				
			}
		if(rs.getAuth_amt() == 0.0 )
		{
			model.put("msg", "Please Enter Authorization Amount");
			return new ModelAndView("redirect:pers_entitUrl");
			
		}
		String auth_amt =  Double.toString(rs.getAuth_amt());
		if(validation.checkAuth_amtLength(auth_amt)  == false){
			model.put("msg",validation.auth_amtMSG);
			return new ModelAndView("redirect:pers_entitUrl");
		}
		if(validation.checkRemarksLength(rs.getRemarks())  == false)
		{
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:pers_entitUrl");
			
		}
		
		Boolean e = checkWePeNoExistsPersEnti(we_pe_no,category_of_persn,rank_cat,app_trd_code,rank,arm_code) ;
		if(e.equals(false)) {
		rs.setRoleid(roleid);	
		rs.setCreated_by(username);
		rs.setCreated_on(creadtedate);
		rs.setStatus("0");
				
		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		sessionHQL1.beginTransaction();
		sessionHQL1.save(rs);
		sessionHQL1.getTransaction().commit();
		sessionHQL1.close();
		model.put("msg", "Data saved Successfully");
		}
		else {
		model.put("msg", "Data Already Exists!");
		}
			}	
			catch (Exception e) {
				sessionHQL.getTransaction().rollback();
			}
			finally{
			
			}
		List<Map<String, Object>> list1 =pdao.getSummaryPersonnelEntitlementReport1(we_pe_no,"");
		/*List<Map<String, Object>> list= pdao.getSearchPersonnelEntitlementReport(we_pe_no,"","","","","");*/
		List<Map<String, Object>> list= pdao.getSearchPersonnelEntitlementReport(we_pe_no,"",roleType,"","",roleAccess);
		model.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		model.put("getPersonCatListFinal", M.getPersonCatListFinal());
		model.put("list", list);
		model.put("list1", list1);
		model.put("listsize", list.size());	 
		model.put("roleType", roleType);
		model.put("we_pe1", we_pe);
		model.put("we_pe_no1", we_pe_no);
		
		return new ModelAndView("pers_entitTile");
	}

	
	@SuppressWarnings("unchecked")
	public Boolean checkWePeNoExistsPersEnti(String we_pe_no,String category_of_persn,String rank_cat,String app_trd_code,String rank,String arm_code){
		String hql=" select distinct we_pe_no from CUE_TB_MISO_WEPE_PERS_DET where we_pe_no=:we_pe_no and category_of_persn=:category_of_persn and rank_cat=:rank_cat and app_trd_code=:app_trd_code and rank=:rank and arm_code=:arm_code " ;
		List<CUE_TB_MISO_WEPE_PERS_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q= session.createQuery(hql);
			q.setParameter("we_pe_no", we_pe_no);
			q.setParameter("category_of_persn", category_of_persn);
			q.setParameter("rank_cat", rank_cat);
			q.setParameter("app_trd_code", app_trd_code);
			q.setParameter("rank", rank);
			q.setParameter("arm_code", arm_code);
			users = (List<CUE_TB_MISO_WEPE_PERS_DET>) q.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	
	public @ResponseBody List<CUE_TB_MISO_WEPE_PERS_DET> getPersonnelEntitlementReport(String we_pe_no ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from CUE_TB_MISO_WEPE_PERS_DET where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_DET> list = (List<CUE_TB_MISO_WEPE_PERS_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}	

	@RequestMapping(value = "/search_pers_entitUrl", method = RequestMethod.GET)
	public ModelAndView search_pers_entitUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_pers_entitUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("searchPersonal_entitlementTile");
	}	
	
	@RequestMapping(value = "/admin/search_pers_entitUrl1", method = RequestMethod.POST)
	public ModelAndView search_pers_entitUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "category_of_persn1", required = false) String category_of_persn,
			@RequestParam(value = "rank_cat1", required = false) String rank_cat,
			@RequestParam(value = "appt_trade1", required = false) String appt_trade,
			@RequestParam(value = "parent_arm1", required = false) String parent_arm,
			@RequestParam(value = "auth_amt1", required = false) String auth_amt,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "regt1", required = false) String regt,
			@RequestParam(value = "ere1", required = false) String ere,
			@RequestParam(value = "total1", required = false) String total,
			@RequestParam(value = "we_pe1", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			 String roleAccess = session.getAttribute("roleAccess").toString();
			Mmap.put("we_pe1", we_pe);
			Mmap.put("total1", total);
			Mmap.put("ere1", ere);
			Mmap.put("regt1", regt);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("category_of_persn1", category_of_persn);
			Mmap.put("rank1", rank);
			
			 List<Map<String, Object>> list= pdao.getSearchPersonnelEntitlementReport1(we_pe_no,category_of_persn,rank,rank_cat,status,roleType,roleAccess);
			List<Map<String, Object>> list1 =pdao.getSummaryPersonnelEntitlementReport(we_pe_no,status);
			Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
			Mmap.put("list", list);
			Mmap.put("list1", list1);
			
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("list1.size()", list1.size());
		return new ModelAndView("searchPersonal_entitlementTile");
	}
	
	
	
	@RequestMapping(value = "/updaterejectaction_pers_enti", method = RequestMethod.POST)	 
	public @ResponseBody List<String> updaterejectaction_pers_enti(String remarks,String letter_no,int id) {
		List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("cue_tb_miso_wepe_pers_det",remarks,id,letter_no);
		return list2;
	}

	@RequestMapping(value = "/ApprovedPersonal_EntitalUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedPersonal_EntitalUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "category_of_persn2", required = false) String category_of_persn,
			@RequestParam(value = "rank_cat2", required = false) String rank_cat,
			@RequestParam(value = "appt_trade2", required = false) String appt_trade,
			@RequestParam(value = "parent_arm2", required = false) String parent_arm,
			@RequestParam(value = "auth_amt2", required = false) String auth_amt,
			@RequestParam(value = "rank2", required = false) String rank,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "regt2", required = false) String regt,
			@RequestParam(value = "ere2", required = false) String ere,
			@RequestParam(value = "total2", required = false) String total,
			@RequestParam(value = "we_pe2", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			String username = session.getAttribute("username").toString();
			String mst = pdao.setApprovedPersonal_EntitalARM(appid,username,we_pe_no);
			if(mst.equals("Approved Successfully")) {
				vetting.updateVettingDtl( we_pe_no, "1");
			}
			Mmap.put("msg", mst);	
			Mmap.put("we_pe1", we_pe);
			Mmap.put("total1", total);
			Mmap.put("ere1", ere);
			Mmap.put("regt1", regt);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("category_of_persn1", category_of_persn);
			Mmap.put("rank1", rank);
			 String roleAccess = session.getAttribute("roleAccess").toString();
			List<Map<String, Object>> list= pdao.getSearchPersonnelEntitlementReport1(we_pe_no,category_of_persn,rank,rank_cat,status,roleType,roleAccess);
			List<Map<String, Object>> list1 =pdao.getSummaryPersonnelEntitlementReport(we_pe_no,status);
			
			Mmap.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
			Mmap.put("getPersonCatListFinal", M.getPersonCatListFinal());
			Mmap.put("list", list);
			Mmap.put("list1", list1);			
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			Mmap.put("list1.size()", list1.size());
			return new ModelAndView("searchPersonal_entitlementTile");
	}
	
	@RequestMapping(value = "/getpers_det", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_PERS_DET> getpers_det(int id,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());	
		Session session= HibernateUtilNA.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = null;		 
			q = session.createQuery(" from CUE_TB_MISO_WEPE_PERS_DET where id=:id ");	
			q.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_PERS_DET> list = (List<CUE_TB_MISO_WEPE_PERS_DET>) q.list();
			tx.commit();
			session.close();
			return list;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getfootnode_det", method = RequestMethod.POST)
	public @ResponseBody Boolean getfootnode_det(@RequestParam String we_pe_no,@RequestParam String app_trd_code,@RequestParam String arm_code,@RequestParam String rank,@RequestParam String rank_cat,@RequestParam String category_of_persn) {
		String hql ="";
		hql="from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where  we_pe_no =:we_pe_no and appt_trade =:app_trd_code and arm_code =:arm_code and rank =:rank and rank_cat =:rank_cat and category_of_personnel =:category_of_persn";
		List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try { 
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("app_trd_code", app_trd_code);
			query.setParameter("arm_code", arm_code);
			query.setParameter("rank", rank);
			query.setParameter("rank_cat", rank_cat);
			query.setParameter("category_of_persn", category_of_persn);
			users = (List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getmdfs_det", method = RequestMethod.POST)
	public @ResponseBody Boolean getmdfs_det(@RequestParam String we_pe_no,@RequestParam String app_trd_code,@RequestParam String arm_code,@RequestParam String rank,@RequestParam String rank_cat,@RequestParam String category_of_persn) {
		String hql="from CUE_TB_MISO_WEPE_PERS_MDFS where  we_pe_no =:we_pe_no and appt_trade =:app_trd_code and arm_code =:arm_code and rank =:rank and rank_cat =:rank_cat and cat_per =:category_of_persn";
		List<CUE_TB_MISO_WEPE_PERS_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("app_trd_code", app_trd_code);
			query.setParameter("arm_code", arm_code);
			query.setParameter("rank", rank);
			query.setParameter("rank_cat", rank_cat);
			query.setParameter("category_of_persn", category_of_persn);
			users = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;	
	}	
	
	@RequestMapping(value = "/deletePersonal_EntitalUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deletePersonal_EntitalUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(pdao.setDeletePersonal_EntitalARM(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/updatePersonal_EntitalUrl")
	public ModelAndView updatePersonal_EntitalUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(roleType.equals("Line_Dte") & roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}	
		model.put("edit_pers_entitCmd", pdao.getCUE_TB_MISO_WEPE_PERS_DETByid(updateid));	
		model.put("msg", msg);
		model.put("getTypeofRankcategoryListFinal", M.getTypeofRankcategoryListFinal());
		model.put("getPersonCatListFinal", M.getPersonCatListFinal());
		return new ModelAndView("editPersonal_entitlementTile");
	}
	
	@RequestMapping(value = "/edit_pers_entitAct", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Tb_Miso_Orbat_Code(@ModelAttribute("edit_pers_entitCmd") CUE_TB_MISO_WEPE_PERS_DET updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(roleType.equals("Line_Dte") & roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
			String parent_arm1 = request.getParameter("arm_code");
			String parent_arm = request.getParameter("arm_code");
			String auth_amt1 = request.getParameter("auth_amt1");
		
			if(auth_amt1.equals("")) {
				auth_amt1 = "0.0";
			}
			Double auth_amt =  Double.parseDouble(auth_amt1);
			
			int id=updateid.getId();
			 if(auth_amt.equals("") || auth_amt.equals("0.0") || auth_amt==0.0)
			{
				model.put("updateid",id );
				model.put("msg", "Please Enter Authorization Amount");
				return new ModelAndView("redirect:updatePersonal_EntitalUrl");
				
			}
			 String auth_amtvali =  Double.toString(updateid.getAuth_amt());
				if(validation.checkAuth_amtLength(auth_amtvali)  == false){
					model.put("msg",validation.auth_amtMSG);
					return new ModelAndView("redirect:updatePersonal_EntitalUrl");
				}
			CUE_TB_MISO_WEPE_PERS_DET ctm= new CUE_TB_MISO_WEPE_PERS_DET();
			if(parent_arm1.equals("ERE"))
			{			
				ctm.setArm_code("arm_code");
			}		
			String username = session.getAttribute("username").toString();
			updateid.setAuth_amt(Double.parseDouble(auth_amt1));
		  
			model.put("msg", pdao.setUpdatePersonal_EntitalARM(updateid, username));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("redirect:pers_entitUrl");
	}
	
	
	@RequestMapping(value = "/getrank_des", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getrank_des(String rnk,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct description from CUE_TB_PSG_RANK_APP_MASTER where code =:rnk order by description ") ;
		q.setParameter("rnk", rnk);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getTypeofRankList_enti_der", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getTypeofRankList_enti(String rnk,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct description,code from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = :level_in_hierarchy and code = :code order by description ") ;				
		q.setParameter("level_in_hierarchy", "RANK");
		q.setParameter("code", rnk);
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

}
