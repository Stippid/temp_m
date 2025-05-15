package com.controller.cue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

import com.controller.validation.ValidationController;
import com.dao.cue.CUE_PRF_MasterDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Prf_Master_Controller {
	@Autowired
	private CUE_PRF_MasterDAO masterDAO;// = new CUE_PRF_MasterDAOImpl();
	@Autowired
	private RoleBaseMenuDAO roledao ;
	cueContoller M =new cueContoller();
	 
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/prf_mstUrl", method = RequestMethod.GET)
	public ModelAndView prf_mstUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("prf_mstUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("prf_mstTile");
	}	

	@RequestMapping(value = "/admin/prf_mstUrl1", method = RequestMethod.POST)
	public ModelAndView prf_mstUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "prf_group1", required = false) String prf_group,
			@RequestParam(value = "coss_section1", required = false) String coss_section,
			@RequestParam(value = "nodal_dte1", required = false) String nodal_dte,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("status1", status);
			Mmap.put("prf_group1", prf_group);
			Mmap.put("coss_section1", coss_section);
			Mmap.put("nodal_dte1", nodal_dte);
			List<Map<String, Object>> list =masterDAO.getAttributeFromCategory2(prf_group,coss_section,nodal_dte,  status,  roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
		return new ModelAndView("prf_mstTile");
	}
	
	@RequestMapping(value = "/getnodal_dirList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getnodal_dirList(HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid=:domainid  order by label");
		q.setParameter("domainid", "SPONSERDTE");
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		return M.getDDLCommonList(sessionUserId,list);
	}

	@RequestMapping(value = "/getcoss_sectionList", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_PRF_Mst> getcoss_sectionList(HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select DISTINCT coss_section from CUE_TB_MISO_PRF_Mst order by coss_section");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_PRF_Mst> list = (List<CUE_TB_MISO_PRF_Mst>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean getprf_groupList(String prf_group) {
		String hql="";
		hql="from CUE_TB_MISO_PRF_Mst where prf_group =:prf_group";
		List<CUE_TB_MISO_PRF_Mst> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("prf_group", prf_group);
			users = (List<CUE_TB_MISO_PRF_Mst>) query.list();
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
	
	@RequestMapping(value = "/prf_mstAction", method = RequestMethod.POST)
	public ModelAndView parentsArmAction(@ModelAttribute("prf_mstCMD") CUE_TB_MISO_PRF_Mst p,HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String cos_ch= request.getParameter("cos_ch");
		String cos_= request.getParameter("cos");
		String cos_int= request.getParameter("cos_int");
		int roleid = (Integer)session.getAttribute("roleid");
		String roleType = session.getAttribute("roleType").toString();
		String  prf_group = request.getParameter("prf_group");
	
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		
		try
		{
			if(cos_ch == "" || cos_ch==null || cos_ch=="null" || cos_ch.equals(null))
			{
				model.put("msg", "Please Enter COS Section");
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(validation.checkIncDecLength(cos_ch)  == false)
			{
		 		model.put("msg",validation.cosSecMSG);
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(cos_int == "" || cos_int==null || cos_int=="null" || cos_int.equals(null))
			{
				model.put("msg", "Please Enter COS Section");
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(cos_int.length() == 1)
			{
				model.put("msg", "Please Enter Two-Digit No");
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(validation.checkMonthLength(cos_int)  == false)
			{
		 		model.put("msg",validation.cosSecMSG);
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(p.getPrf_group().equals("") || p.getPrf_group() ==null ||  p.getPrf_group().equals(null) ||  p.getPrf_group().isEmpty())
			{
				model.put("msg", "Please Enter PRF Groups");
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(validation.checkWepetabletittleLength(p.getPrf_group())  == false)
			{
				model.put("msg",validation.prfGrpMSG);
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(p.getNodal_dte().equals("0"))
			{
				model.put("msg", "Please Select Nodal Directorate");
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(validation.checkFormationLength(p.getNodal_dte())  == false)
			{
				model.put("msg",validation.nodleDteMSG);
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
			if(validation.checkRemarksLength(p.getRemarks())  == false)
			{
		 		model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:prf_mstUrl");
			}
			
		p.setRoleid(roleid);	
		Boolean e = getprf_groupList(prf_group);
		if(e.equals(false)) {	
			p.setCreated_by(username);
			p.setCreated_on(date);
			p.setStatus("0");		
			p.setStatus_active("Active");
			String coss_section;	
			coss_section=cos_ch+"-"+cos_int;
			p.setCoss_section(coss_section);
			Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
			sessionHQL1.beginTransaction();
			int did = (Integer) sessionHQL1.save(p);
			sessionHQL1.getTransaction().commit();
			sessionHQL1.close();
			model.put("msg", "Data saved Successfully");
		}
		else {
			model.put("msg", "PRF Group Already exists!");
		}
		}	
		catch (Exception e) {
			sessionHQL.getTransaction().rollback();
			tx0.commit();
			sessionHQL.close();
			
		}
		
		List<Map<String, Object>> list =masterDAO.getAttributeFromCategory2(prf_group,"", "", "","");
		model.put("list", list);
		model.put("prf_group1", prf_group);		
		return new ModelAndView("prf_mstTile");
	}
	
	@RequestMapping(value = "/search_prf_mstUrl", method = RequestMethod.GET)
	public ModelAndView search_prf_mstUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_prf_mstUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_prf_mstTile");
	}	
	
	@RequestMapping(value = "/admin/search_prf_mstUrl1", method = RequestMethod.POST)
	public ModelAndView search_prf_mstUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "prf_group1", required = false) String prf_group,
			@RequestParam(value = "coss_section1", required = false) String coss_section,
			@RequestParam(value = "nodal_dte1", required = false) String nodal_dte,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			
			Mmap.put("status1", status);
			Mmap.put("prf_group1", prf_group);
			Mmap.put("coss_section1", coss_section);
			Mmap.put("nodal_dte1", nodal_dte);
			
			List<Map<String, Object>> list =masterDAO.getAttributeFromCategory21(prf_group,coss_section, nodal_dte, status,  roleType);
			 
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
		return new ModelAndView("search_prf_mstTile");
	}
	
	@RequestMapping(value = "/admin/ApprovedprfMstArmUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedprfMstArmUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			@RequestParam(value = "prf_group2", required = false) String prf_group,
			@RequestParam(value = "coss_section2", required = false) String coss_section,
			@RequestParam(value = "nodal_dte2", required = false) String nodal_dte,
			@RequestParam(value = "status2", required = false) String status){
		Mmap.put("msg", masterDAO.setApprovedprfMst(appid));
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status);
		Mmap.put("prf_group1", prf_group);
		Mmap.put("coss_section1", coss_section);
		Mmap.put("nodal_dte1", nodal_dte);
		
		List<Map<String, Object>> list =masterDAO.getAttributeFromCategory21(prf_group,coss_section, nodal_dte, status,  roleType);
		 	
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_prf_mstTile");
	}
	
	@RequestMapping(value = "/admin/rejectPrf_MstUrl", method = RequestMethod.POST)
	public ModelAndView rejectArmUrl(@ModelAttribute("rejectid") int rejectid,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "prf_group3", required = false) String prf_group,
		@RequestParam(value = "coss_section3", required = false) String coss_section,
		@RequestParam(value = "nodal_dte3", required = false) String nodal_dte,
		@RequestParam(value = "status3", required = false) String status){
		Mmap.put("msg", masterDAO.setRejectprfMst(rejectid));
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status);
		Mmap.put("prf_group1", prf_group);
		Mmap.put("coss_section1", coss_section);
		Mmap.put("nodal_dte1", nodal_dte);
		
		List<Map<String, Object>> list =masterDAO.getAttributeFromCategory21(prf_group,coss_section, nodal_dte, status,  roleType);
		 	
		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_prf_mstTile");
	}
	
	@RequestMapping(value = "/deleteprf_mstUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteprf_mstUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(masterDAO.setDeleteprfMst(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/admin/updateprf_mstUrl")
	public ModelAndView updateprf_mstUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("Editprf_mstCMD", masterDAO.getCUE_TB_MISO_PRF_MstByid(updateid));	
		model.put("msg", msg);
		/*model.put("parent_code_type",request.getParameter("parent_arm"));*/
		return new ModelAndView("edit_prf_mstTile");
	}
	
	@RequestMapping(value = "/Editprf_mstAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Tb_Miso_Orbat_Code(@ModelAttribute("Editprf_mstCMD") CUE_TB_MISO_PRF_Mst updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
		
		String username = session.getAttribute("username").toString();
		if(updateid.getNodal_dte().equals("") || updateid.getNodal_dte() ==null ||  updateid.getNodal_dte().equals(null) ||  updateid.getNodal_dte().isEmpty())
		{
		 	model.put("updateid", updateid.getId());
			model.put("msg", "Please Select Nodal Directorate");
			return new ModelAndView("redirect:updateprf_mstUrl");
		}
		
		if(validation.checkFormationLength(updateid.getNodal_dte())  == false)
		{
	 		model.put("msg",validation.nodleDteMSG);
			return new ModelAndView("redirect:updateprf_mstUrl");
		}
		
			
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("msg", masterDAO.setUpdateprfMst(updateid,username,modifydate));
		}	
		catch (Exception e) {
			//session0.getTransaction().rollback();
		}
		finally{
			
			//tx0.commit();
			//session0.close();
		}
		
		return new ModelAndView("redirect:prf_mstUrl");
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value = "/getprf_code",method = RequestMethod.POST)
	public @ResponseBody List<String> getprf_code(String cos) {			
		 String prfCode ="";
		 String prfCode1 ="";
		String prfcode = cos;
		char ch = prfcode.charAt(0);
		   int castAscii = (int) ch;
		   String first = String.format("%02d", castAscii - 64);
		   char se = prfcode.charAt(1);
		   char th = prfcode.charAt(2);		 
		    
		   prfCode = first + se+th;
		   
		   char ch1 = prfcode.charAt(0);
		   String cos1= "";
		   cos1=ch1+"-" + se + th;
		   
		   Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query q1 = session1.createQuery("select count(*) from CUE_TB_MISO_PRF_Mst where coss_section=:coss_section");
			q1.setParameter("coss_section", cos1);
			@SuppressWarnings("unchecked")
			Long count1 = (Long)q1.uniqueResult();
			tx1.commit();
			session1.close();			
			
			String serial = "";
		   if(count1 !=0) {
		   
		    Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select max(SUBSTRING(prf_group_code,5)) from CUE_TB_MISO_PRF_Mst where coss_section=:coss_section");
			q.setParameter("coss_section", cos1);
			List list = (List) q.list();
			String filePath = list.get(0).toString();
			tx.commit();
			session.close();	
			
			if(list == null) {
				serial = "000";
			}else {
				serial = filePath;
			}
			
		   }
		   else
			   serial = "000";
		   		   
			Integer serialNo = Integer.parseInt(serial) + 1;
			serial = String.format("%03d", serialNo);
			prfCode += serial;
			  
			 @SuppressWarnings({ "rawtypes", "unchecked" })
				List<String> listItemCode = new ArrayList();
		        
		        listItemCode.add(prfCode);
		        
		        return listItemCode;
	}

}
