package com.controller.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.cue.WEPELinkSusDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.CUE_TB_wepe_link_sus_pers_mdfs;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Link_WE_PE_unitController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private WEPELinkSusDAO linkdao;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	cueContoller M = new cueContoller();
	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/link_WE_PE_unit", method = RequestMethod.GET)
	public ModelAndView link_we_pe(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("link_WE_PE_unit", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(roleAccess.equals("Unit")) {
			Mmap.put("unit_sus_no1", roleSusNo);
			Mmap.put("unit_name1",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			

			List<Map<String, Object>> list =linkdao.getSearchlinkWEPEPersReportDetail("","",roleSusNo,"");
			Mmap.put("list", list);
			
			Mmap.put("list.size()", list.size());
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("msg", msg);
		return new ModelAndView("link_WE_PE_unitTiles","link_WE_PE_unitCmd",new CUE_TB_WEPE_link_sus_perstransweapon());
	}
	

	@RequestMapping(value = "/admin/link_WE_PE_unit1", method = RequestMethod.POST)
	public ModelAndView link_WE_PE_unit1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "radio_doc01", required = false) String radio_doc,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "id_check_hidden1", required = false) String ch,
			@RequestParam(value = "id_check_foot_hidden1", required = false) String ch_foot){
		
			ch = ch.replace("&#39;", "'");
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("radio_doc01", radio_doc);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("unit_sus_no1", unit_sus_no);
		
			List<Map<String, Object>> list =linkdao.getSearchlinkWEPEPersReportDetail(we_pe_no,status,unit_sus_no,roleType);
			System.out.println(list+"cont");
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		    Mmap.put("roleType", roleType);
		    Mmap.put("id_check_hidden_txt", ch);
		    Mmap.put("id_check_foot_hidden_txt", ch_foot);
		return new ModelAndView("link_WE_PE_unitTiles");
	}
	
	@RequestMapping(value = "/getFootNotesPersDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFootNotesPersDetails(String sus_no,String wepe_no,HttpSession session) {
		String roleType = session.getAttribute("roleType").toString();
		return linkdao.getFootNotesPersDetails(sus_no,wepe_no);
	}
	
	@RequestMapping(value = "/admin/searchLinkWEPEPers1", method = RequestMethod.POST)
	public ModelAndView searchLinkWEPEPers1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "radio_doc01", required = false) String radio_doc,
			@RequestParam(value = "wepe_pers_no1", required = false) String wepe_pers_no,
			@RequestParam(value = "status1", required = false) String status_pers){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("status1", status_pers);
			Mmap.put("wepe_pers_no1", wepe_pers_no);
			Mmap.put("unit_sus_no1", unit_sus_no);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("radio_doc01", radio_doc);
			
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPEPersReportDetail1(status_pers,wepe_pers_no,unit_sus_no,unit_name,roleType,roleAccess,roleSusNo);
			
			Mmap.put("list", list);
		    Mmap.put("roleType", roleType);
		    Mmap.put("list.size()", list.size());
		return new ModelAndView("Search_link_WEPE_PersTiles");
	}
	
	@RequestMapping(value = "/getMoreModFootCmd", method = RequestMethod.POST)
	public ModelAndView moreMDFSFoot(@ModelAttribute("wepe_id") String wepe_pers_no_id,@ModelAttribute("sus_no_id") String sus_no_id,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		model.put("table_view", linkdao.getwepepersModiDetailsReport(wepe_pers_no_id,"cue_tb_miso_wepe_pers_mdfs"));
		model.put("table_view_footnotes", linkdao.getwepepersModiDetailsFootnotesReport(wepe_pers_no_id));
		model.put("sus_no_v",sus_no_id);
		model.put("wepe_pers_no_v",wepe_pers_no_id);
		model.put("unit_name_v",request.getParameter("unit_id"));
		model.put("radio_doc_v", request.getParameter("radio_doc_id"));
	return new ModelAndView("link_WE_PE_unitTiles");
	}
	
	@RequestMapping(value = "/link_WE_PE_unitAct", method = RequestMethod.POST)
	public ModelAndView link_WE_PE_unitAct(@ModelAttribute("link_WE_PE_unitCmd") CUE_TB_WEPE_link_sus_perstransweapon rs,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String unit_sus_no = request.getParameter("sus_no");
		String wepe_pers_no = request.getParameter("wepe_pers_no");
		 String unit_name = request.getParameter("unit_name");
		 String we_pe = request.getParameter("radio_doc");
		 
		if(M.getCUEUnitsSUSNoActiveList(session,unit_sus_no).size() == 0) {
			model.put("msg", "Please Enter Active SUS No");
			return new ModelAndView("redirect:link_WE_PE_unit");
		}
		
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:link_WE_PE_unit");
		}
		 if(rs.getWepe_pers_no() == "")
			{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:link_WE_PE_unit");
			}
		 
//		 List<String> list_loc = linkdao.check_locationWeappers(wepe_pers_no,unit_sus_no);
//			
//			if(!list_loc.isEmpty())
//			{
//				Boolean result_b=false;
//				for(String s:list_loc)
//				{
//					if(s.equals("t"))
//					{
//						result_b=true;
//						break;
//					}
//			}
//				if(result_b!=true)
//				{
//					model.put("msg", "Location Does Not Match");
//					return new ModelAndView("redirect:link_WEPE_sus_Wep");
//				}
//			}
		 
		 
		 
		 
		Boolean b = linkdao.isSus_noExist(unit_sus_no);
		if( b.equals(false))
		{
			if(unit_sus_no.length() > 8) {
				model.put("msg", "Please Enter Maximum 8 charactors");
			}
			else {
				rs.setRoleid(roleid);
				rs.setCreated_on(creadtedate);
				rs.setCreated_by(username);
				rs.setStatus_pers("0");
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				sessionHQL.beginTransaction();
				int did = (Integer) sessionHQL.save(rs);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();		
			}
		}
		else {			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.wepe_pers_no = :wepe_pers_no ,created_on = :created_on , created_by =:created_by , status_pers =:status_pers,roleid=:roleid where c.sus_no =:unit_sus_no ";
			int up_id = sessionHQL.createQuery( hqlUpdate ).setString( "wepe_pers_no", wepe_pers_no ).setString( "created_on", creadtedate ).setString( "created_by", username ).setString( "status_pers", "0" ).setString( "unit_sus_no", unit_sus_no ).setInteger("roleid", roleid ).executeUpdate();
			tx.commit();
			sessionHQL.close();			
		}
		
		String ch = request.getParameter("id_check_hidden");
		String ch_foot = request.getParameter("id_check_foot_hidden");
		Boolean f_k = isdetailFKlink(unit_sus_no);
		Boolean f_k_foot = isdetailFKlink_foot(unit_sus_no);
		
		if( ch!= null && !ch.toString().equals("")) {
			ch = ch.replace("&#39;","'");
		if(f_k.equals(false)) {
			rs.setCreated_by(username);
			rs.setCreated_on(creadtedate);
					
			String ch1[]=ch.split(",");
			for(int i=0;i < ch1.length ; i++) {
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();	
				Query qry = session1.createSQLQuery("insert into cue_tb_wepe_link_sus_pers_mdfs "
				+ "(sus_no ,created_by ,created_on , we_pe_no,status,modification)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", unit_sus_no);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", wepe_pers_no);
				qry.setParameter("a5", "0");
				qry.setParameter("a6", ch1[i].replace("'", ""));
				qry.executeUpdate();
			    tx.commit();
			    session1.close();
			}
		}
		}
		if(ch_foot!= null  && !ch_foot.toString().equals("")) {		
		if(f_k_foot.equals(false)) {
			String ch_f1[]=ch_foot.split(",");
			for(int i=0;i < ch_f1.length ; i++) {
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();	
				Query qry = session1.createSQLQuery("insert into CUE_TB_WEPE_link_sus_pers_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,foot_fk)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", unit_sus_no);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", wepe_pers_no);
				qry.setParameter("a5", "0");
				qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
				qry.executeUpdate();
			    tx.commit();
			    session1.close();
			}
		}
		}
		model.put("msg", "Link WE/PE Data Saved and Updated Successfully");		
		String roleType = session.getAttribute("roleType").toString();		
		
		List<Map<String, Object>> list =linkdao.getSearchlinkWEPEPersReportDetail(wepe_pers_no,"",unit_sus_no,"");
		model.put("we_pe_no1", wepe_pers_no);
		model.put("unit_sus_no1", unit_sus_no);
		model.put("radio_doc01", we_pe);
		model.put("list", list);
	    model.put("roleType", roleType);
	    model.put("id_check_hidden_txt", ch);
	    model.put("id_check_foot_hidden_txt", ch_foot);
		return new ModelAndView("link_WE_PE_unitTiles");
	}	
	
	public Boolean isdetailFKlink(String unit_sus_no) {
		String hql="delete FROM CUE_TB_wepe_link_sus_pers_mdfs m where m.sus_no=:unit_sus_no ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("unit_sus_no", unit_sus_no);
			
			query.executeUpdate();			
			tx.commit();
			session.close(); 
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		} 		
		return false;
	}
	
	public Boolean isdetailFKlink_foot(String unit_sus_no) {
		String hql="delete FROM CUE_TB_WEPE_link_sus_pers_footnotes m where m.sus_no=:unit_sus_no";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("unit_sus_no", unit_sus_no);
			
			query.executeUpdate();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 
		
		return false;
		}
	
		@RequestMapping(value = "/viewMoreDetailModiPers", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> viewMoreDetailModiPers(HttpSession session,HttpServletRequest request,@RequestParam String mod) {
			String wepe_pers_no =request.getParameter("wepe_pers_no");		
		return linkdao.getViewmoreModipersDetailsReport(wepe_pers_no,mod);
		}		
		
		@RequestMapping(value = "/Search_link_WEPE_Pers", method = RequestMethod.GET)
		public ModelAndView Search_link_WEPE_Pers(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			Boolean val = roledao.ScreenRedirect("Search_link_WEPE_Pers", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("Search_link_WEPE_PersTiles");
		}	

		@RequestMapping(value = "/ApprovedWEPELinkPersUrl", method = RequestMethod.POST)
		public ModelAndView ApprovedWEPELinkPersUrl(@ModelAttribute("appid") int appid,@ModelAttribute("sus_no") String sus_no,@ModelAttribute("wepe_no") String wepe_no,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
				@RequestParam(value = "unit_name2", required = false) String unit_name,
				@RequestParam(value = "wepe_pers_no2", required = false) String wepe_pers_no,
				@RequestParam(value = "radio_doc02", required = false) String wepe,
				@RequestParam(value = "status2", required = false) String status_pers){
				
			String username = session.getAttribute("username").toString();
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess =  session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				Mmap.put("msg", linkdao.setApprovedWepeLink(appid,sus_no,wepe_no,username,creadtedate));	
				
				Mmap.put("status1", status_pers);
				Mmap.put("wepe_pers_no1", wepe_pers_no);
				Mmap.put("unit_sus_no1", unit_sus_no);
				Mmap.put("unit_name1", unit_name);
				List<Map<String, Object>> list = linkdao.getSearchlinkWEPEPersReportDetail1(status_pers,wepe_pers_no,unit_sus_no,unit_name,roleType, roleAccess,roleSusNo);
					
				Mmap.put("list", list);
				
				Mmap.put("roleType", roleType);
				Mmap.put("list.size()", list.size());
				return new ModelAndView("Search_link_WEPE_PersTiles");
			}	
			
		@RequestMapping(value = "/rejectWEPELinkPersUrl", method = RequestMethod.POST)
		public ModelAndView rejectWEPELinkPersUrl(@ModelAttribute("rejectid") int rejectid,ModelMap Mmap,@ModelAttribute("sus_no_r") String sus_no_r
				,@ModelAttribute("wepe_no_r") String wepe_no_r,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,
				@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no,
				@RequestParam(value = "unit_name5", required = false) String unit_name,
				@RequestParam(value = "wepe_pers_no5", required = false) String wepe_pers_no,
				@RequestParam(value = "status5", required = false) String status_pers,
				@RequestParam(value = "radio_doc05", required = false) String wepe){
				String roleType = session.getAttribute("roleType").toString();
				
				Mmap.put("msg", linkdao.setRejectWepeLink(rejectid,sus_no_r,wepe_no_r));
				String roleAccess =  session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				List<Map<String, Object>> list = linkdao.getSearchlinkWEPEPersReportDetail1(status_pers,wepe_pers_no,unit_sus_no,unit_name,roleType,roleAccess,roleSusNo);
				Mmap.put("list", list);
				Mmap.put("status1", status_pers);
				Mmap.put("wepe_pers_no1", wepe_pers_no);
				Mmap.put("unit_sus_no1", unit_sus_no);
				Mmap.put("unit_name1", unit_name);
				Mmap.put("radio_doc01", wepe);
				
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("Search_link_WEPE_PersTiles");	
		}
		
		@RequestMapping(value = "/deleteWEPELinkPersUrlAdd", method = RequestMethod.POST)
		public @ResponseBody List<String> deleteWEPELinkPersUrlAdd(int deleteid,String sus,String wepe) {
			List<String> list2 = new ArrayList<String>();
			list2.add(linkdao.setDeleteWepeLink(deleteid,sus,wepe));
			return list2;
		}
		
		@RequestMapping(value = "/delinkapprvWEPELinkPersUrl", method = RequestMethod.POST)
		public ModelAndView delinkapprvWEPELinkPersUrl(@ModelAttribute("delinkapprvid") int delinkapprvid,@ModelAttribute("sus_no_dr") String sus_no_r
				,@ModelAttribute("wepe_no_dr") String wepe_no_r,ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg
				,Authentication authentication,HttpSession session,
				@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no,
				@RequestParam(value = "unit_name3", required = false) String unit_name,
				@RequestParam(value = "wepe_pers_no3", required = false) String wepe_pers_no,
				@RequestParam(value = "status3", required = false) String status_pers,
				@RequestParam(value = "radio_doc03", required = false) String wepe){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess =  session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				Mmap.put("msg", linkdao.setDelinkapprvWepeLink(delinkapprvid,sus_no_r,wepe_no_r));
				
				List<Map<String, Object>> list = linkdao.getSearchlinkWEPEPersReportDetail1(status_pers,wepe_pers_no,unit_sus_no,unit_name,roleType,roleAccess,roleSusNo);
				Mmap.put("list", list);
				Mmap.put("status1", status_pers);
				Mmap.put("wepe_pers_no1", wepe_pers_no);
				Mmap.put("unit_sus_no1", unit_sus_no);
				Mmap.put("unit_name1", unit_name);
				Mmap.put("radio_doc01", wepe);
				
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("Search_link_WEPE_PersTiles");
		}
		
		@RequestMapping(value = "/delinkrejectWEPELinkPersUrl", method = RequestMethod.POST)
		public ModelAndView delinkrejectWEPELinkPersUrl(@ModelAttribute("delinkrejid") int delinkrejectid,ModelMap Mmap, 
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,
				@RequestParam(value = "unit_sus_no4", required = false) String unit_sus_no,
				@RequestParam(value = "unit_name4", required = false) String unit_name,
				@RequestParam(value = "wepe_pers_no4", required = false) String wepe_pers_no,
				@RequestParam(value = "status4", required = false) String status_pers,
				@RequestParam(value = "radio_doc04", required = false) String wepe){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess =  session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				Mmap.put("msg", linkdao.setDelinkrejectWepeLink(delinkrejectid));		
				
				List<Map<String, Object>> list = linkdao.getSearchlinkWEPEPersReportDetail1(status_pers,wepe_pers_no,unit_sus_no,unit_name,roleType,roleAccess,roleSusNo);
				Mmap.put("list", list);
				Mmap.put("status1", status_pers);
				Mmap.put("wepe_pers_no1", wepe_pers_no);
				Mmap.put("unit_sus_no1", unit_sus_no);
				Mmap.put("unit_name1", unit_name);
				Mmap.put("radio_doc01", wepe);
				
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("Search_link_WEPE_PersTiles");
		}
		
		@RequestMapping(value = "/updateWEPELinkPersUrl", method = RequestMethod.POST)
		public ModelAndView updateWEPELinkPersUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@ModelAttribute("wepe_no_e") String wepe_no_e,@RequestParam(value = "msg", required = false) String msg,Authentication authentication
				,HttpSession sessionEdit){
			
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			model.put("editLinkpersCmd", linkdao.getCUE_TB_MISO_WEPE_PERS_MDFSidLink(updateid));
			model.put("table_view", linkdao.getwepepersModiDetailsReport(wepe_no_e,"cue_tb_miso_wepe_pers_mdfs"));
			model.put("table_view_footnotes", linkdao.getwepepersModiDetailsFootnotesReport(wepe_no_e));
			return new ModelAndView("editLink_WEPE_susPersTile");
		}
		
		@RequestMapping(value = "/edit_modification_link_act", method = RequestMethod.POST)
		public ModelAndView edit_modification_link_act(@ModelAttribute("editLinkpersCmd") CUE_TB_wepe_link_sus_pers_mdfs edit,HttpServletRequest request,ModelMap model,HttpSession session,HttpSession sessionEdit) {
			String roleType = sessionEdit.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			String username = session.getAttribute("username").toString();
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String ch = request.getParameter("id_check_hidden");		
			String ch_foot = request.getParameter("id_check_foot_hidden");
			
			String id_unit_hidden = request.getParameter("id_unit_hidden");
			String id_we_pe_no_hidden = request.getParameter("id_we_pe_no_hidden");
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			//CUE_TB_WEPE_link_sus_perstransweapon main = new CUE_TB_WEPE_link_sus_perstransweapon();
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers = :status,c.modified_by=:modified_by,c.modified_on=:modified_on where c.sus_no = :sus_no";
			int app = session1.createQuery( hqlUpdate ).setString( "status", "0" ).setString("modified_by", username).setString("modified_on", modifydate).setString("sus_no", id_unit_hidden).executeUpdate();
			tx1.commit();
			session1.close();
			Boolean f_k = isdetailFKlink(id_unit_hidden);
			Boolean f_k_foot = isdetailFKlink_foot(id_unit_hidden);
			
			if( ch!= null && !ch.toString().equals("")) {
				ch = ch.replace("&#39;","'");
			if(f_k.equals(false)) {
				edit.setCreated_by(username);
				edit.setCreated_on(creadtedate);
								
				String ch1[]=ch.split(",");
				for(int i=0;i < ch1.length ; i++) {
					Session session11 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session11.beginTransaction();	
					Query qry = session11.createSQLQuery("insert into cue_tb_wepe_link_sus_pers_mdfs "
					+ "(sus_no ,created_by ,created_on , we_pe_no,status,modification)" 
					+ "values"
					+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", id_unit_hidden);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", id_we_pe_no_hidden);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", ch1[i].replace("'", ""));
					qry.executeUpdate();
				    tx.commit();
				    session11.close();
				}
			}
			}
			
			if(ch_foot!= null  && !ch_foot.toString().equals("")) {
				ch_foot = ch_foot.replace("&#39;","");
			if(f_k_foot.equals(false)) {
				
				String ch_f1[]=ch_foot.split(",");
				for(int i=0;i < ch_f1.length ; i++) {
					Session session11 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session11.beginTransaction();	
					Query qry = session11.createSQLQuery("insert into CUE_TB_WEPE_link_sus_pers_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,foot_fk)" 
					+ "values"
					+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
					qry.setParameter("a1", id_unit_hidden);
					qry.setParameter("a2", username);
					qry.setParameter("a3", creadtedate);
					qry.setParameter("a4", id_we_pe_no_hidden);
					qry.setParameter("a5", "0");
					qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
					qry.executeUpdate();
				    tx.commit();
				    session11.close();
				}
			}
			}		
			model.put("msg", "Modification and General Notes Data Updated Successfully");
			return new ModelAndView("redirect:link_WE_PE_unit");
		}
	
		@RequestMapping(value = "/getLink_susno_permdf",method=RequestMethod.POST)
		public @ResponseBody List<String> getLink_susno_permdf(String unit_sus_no,String wepe_pers_no) {			
			return linkdao.getLink_sus_permdf(unit_sus_no, wepe_pers_no);
		}
		
		@RequestMapping(value = "/getLink_susno_perfoot",method=RequestMethod.POST)
		public @ResponseBody List<String> getLink_susno_perfoot(String unit_sus_no,String wepe_pers_no) {			
			return linkdao.getLink_sus_perfoot(unit_sus_no, wepe_pers_no);
		}
	
		@RequestMapping(value = "/getSearchsusbywepenoDetailsList",method=RequestMethod.POST)
		public @ResponseBody List<CUE_TB_WEPE_link_sus_perstransweapon> getSearchsusbywepenoDetailsList(String sus_no,HttpSession sessionUserId) {
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select wepe_pers_no,wepe_trans_no,wepe_weapon_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus_no ");
			q.setParameter("sus_no", sus_no);			
			@SuppressWarnings("unchecked")
			List<CUE_TB_WEPE_link_sus_perstransweapon> list = (List<CUE_TB_WEPE_link_sus_perstransweapon>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getsus_nobywepeperDetailsList",method=RequestMethod.POST)
		public @ResponseBody List<CUE_TB_WEPE_link_sus_perstransweapon> getsus_nobywepeperDetailsList(String wepe_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery(" from CUE_TB_WEPE_link_sus_perstransweapon where wepe_pers_no=:wepe_no ");
			q.setParameter("wepe_no", wepe_no);			
			@SuppressWarnings("unchecked")
			List<CUE_TB_WEPE_link_sus_perstransweapon> list = (List<CUE_TB_WEPE_link_sus_perstransweapon>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getSusNolinkDetailsList",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getSusNolinkDetailsList(HttpSession sessionA,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct sus_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no is not null and upper(sus_no) like:sus_no order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			List<String>  list1 = (List<String> ) q.list();
			tx.commit();
			session.close();
			return M.getAutoCommonList(sessionA,list1);
		}
		
		@RequestMapping(value = "/getOrbatDetails", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getOrbatDetails(String unit_sus_no) {
			List<Map<String, Object>> list= linkdao.getOrbatDetails(unit_sus_no);
			return list;
		}
		
		
		
		
		@RequestMapping(value = "/check_locationWeappers",method=RequestMethod.POST)
		public @ResponseBody List<String> check_locationWeappers(HttpSession session,
				HttpServletRequest request, @RequestParam String wepe_trans_no
				, @RequestParam   String  unit_sus_no) {				
		
			return linkdao.check_locationWeappers(wepe_trans_no,unit_sus_no);
		}
}
