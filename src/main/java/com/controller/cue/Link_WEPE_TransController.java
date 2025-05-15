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
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.CUE_TB_WEPE_link_sus_trans_mdfs;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Link_WEPE_TransController {

	@Autowired
	private WEPELinkSusDAO linkdao;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	cueContoller M = new cueContoller();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/link_WEPE_sus_Trans", method = RequestMethod.GET)
	public ModelAndView link_WEPE_Trans(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("link_WEPE_sus_Trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(roleAccess.equals("Unit")) {
			Mmap.put("unit_sus_no1", roleSusNo);
			Mmap.put("unit_name1",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail2("",roleSusNo,"","","","");
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("msg", msg);
		return new ModelAndView("link_WEPE_sus_TransTiles","link_WEPE_TransCmd",new CUE_TB_WEPE_link_sus_perstransweapon());
	}
	
	@RequestMapping(value = "/getMoreModFootCmdTran", method = RequestMethod.POST)
	public ModelAndView getMoreModFootCmdTran(@ModelAttribute("wepe_id") String wepe_trans_no_id,@ModelAttribute("sus_no_id") String sus_no_id,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		model.put("table_view_trans", linkdao.getwepepersModiDetailsReport(wepe_trans_no_id,"cue_tb_miso_wepe_transport_mdfs"));
		model.put("table_view_trans_footnotes", linkdao.getwepetransModiDetailsFootnotesReport(wepe_trans_no_id));
		model.put("sus_no_t",sus_no_id);
		model.put("wepe_pers_no_t",wepe_trans_no_id);
		model.put("unit_name_t",request.getParameter("unit_id"));
		model.put("radio_doc_t", request.getParameter("radio_doc_id"));
		return new ModelAndView("link_WEPE_sus_TransTiles");
	}
	

	@RequestMapping(value = "/link_WEPE_TransAct", method = RequestMethod.POST)
	public ModelAndView link_WE_PE_unitAct(@ModelAttribute("link_WEPE_TransCmd") CUE_TB_WEPE_link_sus_perstransweapon rs,HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String unit_sus_no_trans = request.getParameter("sus_no");
		String wepe_trans_no = request.getParameter("wepe_trans_no");
		 String we_pe = request.getParameter("radio_doc");
		 String unit_name = request.getParameter("unit_name");
		 String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		 if(M.getCUEUnitsSUSNoActiveList(session,unit_sus_no_trans).size() == 0) {
				model.put("msg", "Please Enter Active SUS No");
				return new ModelAndView("redirect:link_WE_PE_unit");
		}
		 
		 if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
		{
			model.put("msg", "Please Select Document");
			return new ModelAndView("redirect:link_WEPE_sus_Trans");
		}
		 if(rs.getWepe_trans_no() == "")
			{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:link_WEPE_sus_Trans");
			}
//		 List<String> list_loc = linkdao.check_locationWeapTrans(wepe_trans_no, unit_sus_no_trans);
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
		
		 Boolean b = linkdao.isSus_noExist(unit_sus_no_trans);
		if( b.equals(false))
		{
			if(unit_sus_no_trans.length() > 8) {
				model.put("msg", "Please Enter Maximum 8 charactors");
			}
			else {
				rs.setRoleid(roleid);
				rs.setCreated_on_trans(creadtedate);
				rs.setCreated_by_trans(username);
				rs.setStatus_trans("0");
				Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
				sessionHQL1.beginTransaction();
				int did = (Integer) sessionHQL1.save(rs);
				sessionHQL1.getTransaction().commit();
				sessionHQL1.close();
			}
		}
		else {
			Session sessionHQL2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL2.beginTransaction();
			String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.wepe_trans_no = :wepe_trans_no , created_on_trans =:created_on_trans , created_by_trans =:created_by_trans ,status_trans =:status_trans,roleid=:roleid  where c.sus_no=:unit_sus_no_trans ";
			int up_id = sessionHQL2.createQuery( hqlUpdate ).setString( "wepe_trans_no", wepe_trans_no ).setString( "created_on_trans", creadtedate ).setString( "created_by_trans", username ).setString( "status_trans", "0" ).setInteger("roleid", roleid ).setString("unit_sus_no_trans", unit_sus_no_trans).executeUpdate();
			tx.commit();
			sessionHQL2.close();			
		}
		String ch = request.getParameter("id_check_trans_hidden");
		
		String ch_foot = request.getParameter("id_trans_foot_hidden");
		Boolean f_k = isdetailFKlink_trans(unit_sus_no_trans);
		Boolean f_k_foot = isdetailFKlink_Transfoot(unit_sus_no_trans);
		if( ch!= null && !ch.toString().equals("")) {
			ch = ch.replace("&#39;","'");
		if(f_k.equals(false)) {
			rs.setCreated_by(username);
			rs.setCreated_on(creadtedate);
			
			String ch1[]=ch.split(",");
			for(int i=0;i < ch1.length ; i++) {
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();	
				Query qry = session1.createSQLQuery("insert into CUE_TB_WEPE_link_sus_trans_mdfs (sus_no ,created_by ,created_on , we_pe_no,status,modification)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", unit_sus_no_trans);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", wepe_trans_no);
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
				Query qry = session1.createSQLQuery("insert into CUE_TB_WEPE_link_sus_trans_footnotes (sus_no ,created_by ,created_on , we_pe_no,status,fk_trans_foot)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", unit_sus_no_trans);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", wepe_trans_no);
				qry.setParameter("a5", "0");
				qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
				qry.executeUpdate();
			    tx.commit();
			    session1.close();
			}
		}
		}		
		model.put("msg", "Link WEPE Trans Data Saved and Updated Successfully !");	
		
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail2(wepe_trans_no,unit_sus_no_trans,"","",roleAccess,roleSusNo);
		model.put("wepe_trans_no1", wepe_trans_no);
		model.put("unit_sus_no1", unit_sus_no_trans);
		model.put("radio_doc01", we_pe);
		model.put("unit_name1", unit_name);
		model.put("list", list);
		model.put("id_check_hidden_txt", ch);
	    model.put("id_check_foot_hidden_txt", ch_foot);
	    return new ModelAndView("link_WEPE_sus_TransTiles");
	}
	
	public Boolean isdetailFKlink_trans(String unit_sus_no) {		
		String hql="delete FROM CUE_TB_WEPE_link_sus_trans_mdfs m where m.sus_no=:unit_sus_no  ";
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
	
	public Boolean isdetailFKlink_Transfoot(String unit_sus_no) {
		String hql="delete FROM CUE_TB_WEPE_link_sus_trans_footnotes m where m.sus_no=:unit_sus_no  ";
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
			
	@RequestMapping(value = "/viewMoreDetailModiTrans", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> viewMoreDetailModiTrans(HttpSession session,HttpServletRequest request,@RequestParam String mod) {
		String	wepe_trans_no =request.getParameter("wepe_pers_no");
		return linkdao.getViewmoreModiTransDetailsReport(wepe_trans_no,mod);
	}
			
	@RequestMapping(value = "/Search_link_WEPE_Trans", method = RequestMethod.GET)
	public ModelAndView Search_link_WEPE_Trans(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_link_WEPE_Trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		return new ModelAndView("Search_link_WEPE_TransTiles");
	}			
			
	@RequestMapping(value = "/admin/linkWEPETrans1", method = RequestMethod.POST)
	public ModelAndView linkWEPETrans1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no_trans,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "radio_doc01", required = false) String radio_doc,
			@RequestParam(value = "wepe_trans_no1", required = false) String wepe_trans_no,
			@RequestParam(value = "status1", required = false) String status_trans,
			@RequestParam(value = "id_check_trans_hidden1", required = false) String ch,
			@RequestParam(value = "id_trans_foot_hidden1", required = false) String ch_foot){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			
			ch = ch.replace("&#39;", "'");
			Mmap.put("status1", status_trans);
			Mmap.put("wepe_trans_no1", wepe_trans_no);
			Mmap.put("unit_sus_no1", unit_sus_no_trans);
			Mmap.put("radio_doc01", radio_doc);
			Mmap.put("unit_name1", unit_name);
						
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail2(wepe_trans_no,unit_sus_no_trans,status_trans,roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			Mmap.put("id_check_hidden_txt", ch);
		    Mmap.put("id_check_foot_hidden_txt", ch_foot);
		return new ModelAndView("link_WEPE_sus_TransTiles");
	}

	@RequestMapping(value = "/admin/searchLinkWEPETrans1", method = RequestMethod.POST)
	public ModelAndView searchLinkWEPETrans1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no_trans,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "wepe_trans_no1", required = false) String wepe_trans_no,
			@RequestParam(value = "radio_doc01", required = false) String wepe,
			@RequestParam(value = "status1", required = false) String status_trans){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("status1", status_trans);
			Mmap.put("wepe_trans_no1", wepe_trans_no);
			Mmap.put("unit_sus_no1", unit_sus_no_trans);
			Mmap.put("radio_doc01", wepe);
			Mmap.put("unit_name1", unit_name);
					
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail1(status_trans,wepe_trans_no,unit_sus_no_trans,roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);					
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("Search_link_WEPE_TransTiles");
	}
	
	@RequestMapping(value = "/getFootNotesTransDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFootNotesTransDetails(String sus_no,String wepe_no,HttpSession session) {
		String roleType = session.getAttribute("roleType").toString();
		return linkdao.getFootNotesTransDetails(sus_no,wepe_no);
	}

		@RequestMapping(value = "/ApprovedWEPELinkTransUrl", method = RequestMethod.POST)
		public ModelAndView ApprovedWEPELinkTransUrl(@ModelAttribute("appid") int appid,HttpSession session,@ModelAttribute("sus_no") String sus_no,@ModelAttribute("wepe_no") String wepe_no,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no_trans,
				@RequestParam(value = "unit_name2", required = false) String unit_name,
				@RequestParam(value = "wepe_trans_no2", required = false) String wepe_trans_no,
				@RequestParam(value = "radio_doc02", required = false) String wepe,
				@RequestParam(value = "status2", required = false) String status_trans){
			String roleType = session.getAttribute("roleType").toString();
			String username = session.getAttribute("username").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
            String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

			 Mmap.put("msg", linkdao.setApprovedWepeLinkTrans(appid,sus_no,wepe_no,username,creadtedate));	
			Mmap.put("status1", status_trans);
			Mmap.put("wepe_trans_no1", wepe_trans_no);
			Mmap.put("unit_sus_no1", unit_sus_no_trans);
			Mmap.put("radio_doc01", wepe);
			Mmap.put("unit_name1", unit_name);
						
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail1(status_trans,wepe_trans_no,unit_sus_no_trans,roleType,roleAccess,roleSusNo);
			
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("Search_link_WEPE_TransTiles");
		}	

		@RequestMapping(value = "/rejectWEPELinkTransUrl", method = RequestMethod.POST)
		public ModelAndView rejectWEPELinkTransUrl(@ModelAttribute("rejectid") int rejectid,ModelMap Mmap,@ModelAttribute("sus_no_r") String sus_no_r,@ModelAttribute("wepe_no_r") String wepe_no_r,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,
				@RequestParam(value = "unit_sus_no3", required = false) String unit_sus_no_trans,
				@RequestParam(value = "unit_name3", required = false) String unit_name,
				@RequestParam(value = "wepe_trans_no3", required = false) String wepe_trans_no,
				@RequestParam(value = "radio_doc03", required = false) String wepe,
				@RequestParam(value = "status3", required = false) String status_trans){
				
			
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("msg", linkdao.setRejectWepeLinkTrans(rejectid,sus_no_r,wepe_no_r));		
			Mmap.put("status1", status_trans);
			Mmap.put("wepe_trans_no1", wepe_trans_no);
			Mmap.put("unit_sus_no1", unit_sus_no_trans);
			Mmap.put("radio_doc01", wepe);
			Mmap.put("unit_name1", unit_name);
			List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail1(status_trans,wepe_trans_no,unit_sus_no_trans,roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("Search_link_WEPE_TransTiles");
		}
		
			
	@RequestMapping(value = "/deleteWEPELinkTransUrlAdd", method=RequestMethod.POST)
	public @ResponseBody List<String> deleteWEPELinkTransUrlAdd(int deleteid,String sus,String wepe) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(linkdao.setDeleteWepeLinkTrans(deleteid,sus,wepe));
		return list2;
	}
			

	@RequestMapping(value = "/delinkapprvWEPELinkTransUrl", method=RequestMethod.POST)
	public ModelAndView delinkapprvWEPELinkTransUrl(@ModelAttribute("delinkapprvid") int delinkapprvid,@ModelAttribute("sus_no_da") String sus_no_da
			,@ModelAttribute("wepe_no_da") String wepe_no_da,ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg
			,Authentication authentication,HttpSession session,
			@RequestParam(value = "unit_sus_no5", required = false) String unit_sus_no_trans,
			@RequestParam(value = "unit_name5", required = false) String unit_name,
			@RequestParam(value = "wepe_trans_no5", required = false) String wepe_trans_no,
			@RequestParam(value = "radio_doc05", required = false) String wepe,
			@RequestParam(value = "status5", required = false) String status_trans){
		
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("msg", linkdao.setDelinkapprvWepeLinkTrans(delinkapprvid,sus_no_da,wepe_no_da));	
		Mmap.put("status1", status_trans);
		Mmap.put("wepe_trans_no1", wepe_trans_no);
		Mmap.put("unit_sus_no1", unit_sus_no_trans);
		Mmap.put("radio_doc01", wepe);
		Mmap.put("unit_name1", unit_name);
					
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail1(status_trans,wepe_trans_no,unit_sus_no_trans,roleType,roleAccess,roleSusNo);
		
		Mmap.put("list", list);
		
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("Search_link_WEPE_TransTiles");
	}

	@RequestMapping(value = "/delinkrejectWEPELinkTransUrl", method=RequestMethod.POST)
	public ModelAndView delinkrejectWEPELinkTransUrl(@ModelAttribute("delinkrejid") int delinkrejectid,ModelMap Mmap
			, @RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session,
			@RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no_trans,
			@RequestParam(value = "unit_name6", required = false) String unit_name,
			@RequestParam(value = "wepe_trans_no6", required = false) String wepe_trans_no,
			@RequestParam(value = "radio_doc06", required = false) String wepe,
			@RequestParam(value = "status6", required = false) String status_trans){
		
		String roleType = session.getAttribute("roleType").toString();String roleAccess =  session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
					
		Mmap.put("msg", linkdao.setDelinkrejectWepeLinkTrans(delinkrejectid));				
		
		Mmap.put("status1", status_trans);
		Mmap.put("wepe_trans_no1", wepe_trans_no);
		Mmap.put("unit_sus_no1", unit_sus_no_trans);
		Mmap.put("radio_doc01", wepe);
		Mmap.put("unit_name1", unit_name);
					
		List<Map<String, Object>> list = linkdao.getSearchlinkWEPETransReportDetail1(status_trans,wepe_trans_no,unit_sus_no_trans,roleType,roleAccess,roleSusNo);
		
		Mmap.put("list", list);
		
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("Search_link_WEPE_TransTiles");
	}
			
	@RequestMapping(value = "/updateWEPELinkTransUrl", method=RequestMethod.POST)
	public ModelAndView updateWEPELinkTransUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@ModelAttribute("wepe_no_e") String wepe_no_e,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){

		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("editLinkTransCmd", linkdao.getCUE_TB_MISO_WEPE_PERS_MDFSidLink(updateid));
		model.put("table_view_trans", linkdao.getwepepersModiDetailsReport(wepe_no_e,"cue_tb_miso_wepe_transport_mdfs"));
		model.put("table_view_trans_footnotes", linkdao.getwepetransModiDetailsFootnotesReport(wepe_no_e));
		return new ModelAndView("editLink_WEPE_susTransTile");
	}
			
	@RequestMapping(value = "/edit_modification_link_trans_act", method=RequestMethod.POST)
	public ModelAndView edit_modification_link_trans_act(@ModelAttribute("editLinktransCmd") CUE_TB_WEPE_link_sus_trans_mdfs edit,HttpServletRequest request,ModelMap model,HttpSession session,HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String ch = request.getParameter("id_check_trans_hidden");
		
		String ch_foot = request.getParameter("id_trans_foot_hidden");
		String id_unit_hidden_trans = request.getParameter("id_unit_hidden_trans");
		String id_we_pe_no_hidden_trans = request.getParameter("id_we_pe_no_hidden_trans");
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans = :status,c.modified_on_trans=:modified_on_trans,c.modified_by_trans=:modified_by_trans  where c.sus_no = :sus_no";
		int app = session1.createQuery( hqlUpdate ).setString( "status", "0" ).setString("sus_no", id_unit_hidden_trans).setString("modified_by_trans", username).setString("modified_on_trans", modifydate).executeUpdate();
		tx1.commit();
		session1.close();
		Boolean f_k = isdetailFKlink_trans(id_unit_hidden_trans);
		Boolean f_k_foot = isdetailFKlink_Transfoot(id_unit_hidden_trans);
		if( ch!= null && !ch.toString().equals("")) {
			ch = ch.replace("&#39;","'");
		if(f_k.equals(false)) {
			edit.setCreated_by(username);
			edit.setCreated_on(creadtedate);
			
			String ch1[]=ch.split(",");
			for(int i=0;i < ch1.length ; i++) {
				Session session11 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session11.beginTransaction();	
				Query qry = session11.createSQLQuery("insert into CUE_TB_WEPE_link_sus_trans_mdfs (sus_no ,created_by ,created_on "
						+ ", we_pe_no,status,modification)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", id_unit_hidden_trans);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", id_we_pe_no_hidden_trans);
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
				Query qry = session11.createSQLQuery("insert into CUE_TB_WEPE_link_sus_trans_footnotes (sus_no"
						+ " ,created_by ,created_on , we_pe_no,status,fk_trans_foot)" 
				+ "values"
				+ "(:a1,:a2,:a3,:a4,:a5,:a6)");
				qry.setParameter("a1", id_unit_hidden_trans);
				qry.setParameter("a2", username);
				qry.setParameter("a3", creadtedate);
				qry.setParameter("a4", id_we_pe_no_hidden_trans);
				qry.setParameter("a5", "0");
				qry.setParameter("a6", Integer.parseInt(ch_f1[i]));
				qry.executeUpdate();
			    tx.commit();
			    session11.close();
			}
		}
		}		
		model.put("msg", "Modification and General Notes Data Updated Successfully !");
		return new ModelAndView("redirect:link_WEPE_sus_Trans");
	}			
			
	@RequestMapping(value = "/getLink_susno_transmdf",method=RequestMethod.POST)
	public @ResponseBody List<String> getLink_susno_transmdf(String unit_sus_no,String wepe_trans_no) {				
		return linkdao.getLink_sus_transmdf(unit_sus_no, wepe_trans_no);
	}
			
	@RequestMapping(value = "/getLink_susno_transfoot",method=RequestMethod.POST)
	public @ResponseBody List<String> getLink_susno_perfoot(String unit_sus_no,String wepe_trans_no) {				
		return linkdao.getLink_sus_transfoot(unit_sus_no, wepe_trans_no);
	}

	@RequestMapping(value = "/check_locationWeapTrans",method=RequestMethod.POST)
	public @ResponseBody List<String> check_locationWeapTrans(HttpSession session,
			HttpServletRequest request, @RequestParam String wepe_trans_no
			, @RequestParam   String  unit_sus_no) {				
		if(!wepe_trans_no.equals(""))
		{
			wepe_trans_no = wepe_trans_no.replace("&#40;", "(");
			wepe_trans_no = wepe_trans_no.replace("&#41;", ")");
		}
		return linkdao.check_locationWeapTrans(wepe_trans_no,unit_sus_no);
	}
	
	
	
}
