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

import com.controller.mms.MMS_COMMON_Controller;
import com.controller.validation.ValidationController;
import com.dao.cue.ItemMasterDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ItemmasterController {
	
	@Autowired
	private ItemMasterDAO itemmaster;
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	cueContoller M =new cueContoller();
	ValidationController validation = new ValidationController();
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	@RequestMapping(value = "/item_master", method = RequestMethod.GET)
	public ModelAndView item_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("item_master", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getaccunit", getaccunit());
		Mmap.put("getitemcat", getitemcat());
		Mmap.put("getitemgroup", getitemgroup());
		Mmap.put("getbroadcat", getbroadcat());
		Mmap.put("getDomainListClassData", M.getDomainListClassData());
		Mmap.put("ml_nodal", m1.getDomainListingData("SPONSERDTE"));
		Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		return new ModelAndView("item_masterTile");
	}	
	
	@RequestMapping(value = "/admin/item_master1", method = RequestMethod.POST)
	public ModelAndView item_master1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "prf_group1", required = false) String prf_group,
			@RequestParam(value = "item_type1", required = false) String item_type,
			@RequestParam(value = "item_group1", required = false) String item_group,
			@RequestParam(value = "class_item1", required = false) String class_item,
			@RequestParam(value = "category_code1", required = false) String category_code,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			
			List<Map<String, Object>> list =itemmaster.getAttributeFromItemMaster(prf_group,item_type,item_group,class_item, status,  roleType);
			Mmap.put("category_code1", category_code);
			Mmap.put("status1", status);
			Mmap.put("prf_group1", prf_group);
			Mmap.put("item_type1", item_type);
			Mmap.put("item_group1", item_group);
			Mmap.put("class_item1", class_item);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			Mmap.put("getaccunit", getaccunit());
			Mmap.put("getitemcat", getitemcat());
			Mmap.put("getitemgroup", getitemgroup());
			Mmap.put("getbroadcat", getbroadcat());
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		//	Mmap.put("ml_nodal", m1.getDomainListingData("SPONSERDTE"));
			Mmap.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("item_masterTile");
	}
	
	@RequestMapping(value = "item_masterAction", method = RequestMethod.POST)
	public ModelAndView item_masterAction(@ModelAttribute("item_masterCmd") CUE_TB_MISO_MMS_ITEM_MSTR im,HttpServletRequest request,ModelMap model,HttpSession session) {
	
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creation_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String item_type = request.getParameter("item_type");
		String prf_group = request.getParameter("prf_group");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		
		try
		{
		if(im.getPrf_group().equals("") || im.getPrf_group() == null ||  im.getPrf_group().equals(null) ||  im.getPrf_group().isEmpty())
		{
			model.put("msg", "Please Enter PRF Group");
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkFormationLength(im.getPrf_group_code())  == false){
			model.put("msg",validation.prfGrpCodeMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkLocationLength(im.getCos_sec_no())  == false){
			model.put("msg",validation.cosSecMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkConditionLength(im.getPrf_group())  == false){
			model.put("msg",validation.prfGrpMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(im.getItem_type().equals("") || im.getItem_type() == null ||  im.getItem_type().equals(null) || im.getItem_type().isEmpty())
		{
			model.put("msg", "Please Enter Nomenclature");
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkConditionLength(im.getItem_type())  == false){
			model.put("msg",validation.nomenMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkFormationLength(im.getItem_code())  == false){
			model.put("msg",validation.itemcodeMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(im.getItem_group().equals("0"))
		{
			model.put("msg", "Please Select Item Group");
			return new ModelAndView("redirect:item_master");
		}
		
		if(validation.checkBroadCategoryLength(im.getItem_group())  == false){
			model.put("msg",validation.itemGrpMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkBroadCategoryLength(im.getCategory_code())  == false){
			model.put("msg",validation.itemCatMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkParent_codeLength(im.getCritical_equipment())  == false){
			model.put("msg",validation.critiEqpMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(im.getEngr_stores_origin().equals("0"))
		{
			model.put("msg", "Please Select Broad Category of Stores");
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkBroadCategoryLength(im.getEngr_stores_origin())  == false){
			model.put("msg",validation.brdCatStrMSG);
			return new ModelAndView("redirect:item_master");
		}
		
		if(im.getAcc_units().equals("0"))
		{
			model.put("msg", "Please Select Accountng Unit");
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkLocationLength(im.getAcc_units())  == false){
			model.put("msg",validation.accUnitMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkFormationLength(im.getClass_item())  == false){
			model.put("msg",validation.classMSG);
			return new ModelAndView("redirect:item_master");
		}
		if(validation.checkRemarksLength(im.getRemarks())  == false){
			model.put("msg",validation.remarksMSG);
			return new ModelAndView("redirect:item_master");
	 }	
		Boolean e = itemmaster_exits(item_type);
	
		if(e.equals(false)) {
		im.setCreation_by(username);
		im.setCreation_date(new Date());
		im.setStatus("0");
		im.setStatus_active("Active");
		im.setSoftdelete("Active");
		
		Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
		sessionHQL1.beginTransaction();
		int did = (Integer) sessionHQL1.save(im);
		
		sessionHQL1.getTransaction().commit();
		sessionHQL1.close();
		model.put("msg", "Data saved Successfully");
		}
		else{
			model.put("msg", "Data Already Exists!");
			}
		}	
		catch (Exception e) {
			sessionHQL.getTransaction().rollback();
		}
		List<Map<String, Object>> list =itemmaster.getAttributeFromItemMaster(prf_group,"", "", "", "","");
		model.put("prf_group1", prf_group);
		model.put("list", list);
		model.put("list.size()", list.size());
		model.put("getaccunit", getaccunit());
		model.put("getitemcat", getitemcat());
		model.put("getitemgroup", getitemgroup());
		model.put("getbroadcat", getbroadcat());
		model.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("item_masterTile");
	}

	public List<Object[]> getaccunit() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid=:domainid order by label");
		q.setParameter("domainid", "ACCOUNTINGUNITS");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;  
	}
	
	public List<Object[]> getitemcat(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid=:domainid order by label");
		q.setParameter("domainid", "MMSITEMCATEGORY");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;  
	}
	
	public List<Object[]> getbroadcat() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid=:domainid order by label") ;
		q.setParameter("domainid", "ENGRSTORES_OF_ENGR_ORIGIN");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list ;
	}	
	
	@RequestMapping(value = "/getprfList", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getprfList(HttpSession sessionUserId,String prf_group) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct prf_group from CUE_TB_MISO_PRF_Mst where upper(prf_group) like:prf_group order by prf_group").setMaxResults(10);
		q.setParameter("prf_group",prf_group.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);
		
	}
	
	@RequestMapping(value = "/getcosno", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_PRF_Mst> getcosno(String prf_group,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_PRF_Mst where prf_group=:prf_group");
		q.setParameter("prf_group",prf_group);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_PRF_Mst> list = (List<CUE_TB_MISO_PRF_Mst>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	////////////////////////////////////////////////search////////////////////////////////////////////	
	
	@RequestMapping(value = "/admin/search_item_master", method = RequestMethod.GET)
	public ModelAndView search_item_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_item_master", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getitemgroup", getitemgroup());
		Mmap.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("search_item_masterTile");
	}
	@RequestMapping(value = "/admin/search_item_master1", method = RequestMethod.POST)
	public ModelAndView search_item_master1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "prf_group1", required = false) String prf_group,
			@RequestParam(value = "item_type1", required = false) String item_type,
			@RequestParam(value = "item_group1", required = false) String item_group,
			@RequestParam(value = "class_item1", required = false) String class_item,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("status1", status);
			Mmap.put("prf_group1", prf_group);
			Mmap.put("item_type1", item_type);
			Mmap.put("item_group1", item_group);
			Mmap.put("class_item1", class_item);
			
			List<Map<String, Object>> list =itemmaster.getAttributeFromItemMaster1(prf_group,item_type, item_group, class_item, status,roleType);
			 
			Mmap.put("list", list);
			
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		Mmap.put("getitemgroup", getitemgroup());
		Mmap.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("search_item_masterTile");
	}
	
	@RequestMapping(value = "/admin/ApprovedItemArmUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedItemArmUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "prf_group2", required = false) String prf_group,
		@RequestParam(value = "item_type2", required = false) String item_type,
		@RequestParam(value = "item_group2", required = false) String item_group,
		@RequestParam(value = "class_item2", required = false) String class_item,
		@RequestParam(value = "status2", required = false) String status){
		String roleType = session.getAttribute("roleType").toString();
		
		Mmap.put("msg", itemmaster.setApprovedItem(appid));	
		Mmap.put("status1", status);
		Mmap.put("prf_group1", prf_group);
		Mmap.put("item_type1", item_type);
		Mmap.put("item_group1", item_group);
		Mmap.put("class_item1", class_item);
		
		List<Map<String, Object>> list =itemmaster.getAttributeFromItemMaster1(prf_group,item_type, item_group, class_item, status,roleType);
		Mmap.put("list", list);
			
		 Mmap.put("roleType", roleType);
		 Mmap.put("list.size()", list.size());
		 Mmap.put("getitemgroup", getitemgroup());
		 Mmap.put("getDomainListClassData", M.getDomainListClassData());
		 return new ModelAndView("search_item_masterTile");
	}
	
	@RequestMapping(value = "/admin/rejectItemArmUrl", method = RequestMethod.POST)
	public ModelAndView rejectItemArmUrl(@ModelAttribute("rejectid") int rejectid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg
			,Authentication authentication,
			@RequestParam(value = "prf_group3", required = false) String prf_group,
			@RequestParam(value = "item_type3", required = false) String item_type,
			@RequestParam(value = "item_group3", required = false) String item_group,
			@RequestParam(value = "class_item3", required = false) String class_item,
			@RequestParam(value = "status3", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			
			Mmap.put("msg", itemmaster.setRejectItem(rejectid));
			Mmap.put("status1", status);
			Mmap.put("prf_group1", prf_group);
			Mmap.put("item_type1", item_type);
			Mmap.put("item_group1", item_group);
			Mmap.put("class_item1", class_item);
			
			List<Map<String, Object>> list =itemmaster.getAttributeFromItemMaster1(prf_group,item_type, item_group, class_item, status,roleType);
			Mmap.put("list", list);
				
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			Mmap.put("getitemgroup", getitemgroup());
			Mmap.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("search_item_masterTile");
	}
	
	@RequestMapping(value = "/deleteItemArmUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteItemArmUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(itemmaster.setDeleteItem(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/admin/updateItemArmUrl")
	public ModelAndView updateItemArmUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication
			,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("edititem_masterCmd", itemmaster.getCUE_TB_MISO_MMS_ITEM_MSTRid(updateid));
		model.put("msg", msg);
		model.put("getaccunit", getaccunit());
		model.put("getitemcat", getitemcat());
		model.put("getitemgroup", getitemgroup());
		model.put("getbroadcat", getbroadcat());
		model.put("ml_nodal", m1.getDomainListingData("SPONSERDTE"));
		model.put("getLine_DteList",roledao.getLine_DteList(""));
		model.put("getDomainListClassData", M.getDomainListClassData());
		return new ModelAndView("edit_details_of_item_masterTile");
	}
	
	@RequestMapping(value = "/edititem_masterAction", method = RequestMethod.POST) 
	@ResponseBody
	public ModelAndView edititem_masterAction(@ModelAttribute("edititem_masterCmd") CUE_TB_MISO_MMS_ITEM_MSTR updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session
			,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try
		{
             String item_group = request.getParameter("item_group");
			 if(item_group.equals("0") || item_group == "" || item_group.equals("") || item_group == null || item_group.isEmpty())
				{
				 	model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Item Group.");
					return new ModelAndView("redirect:updateItemArmUrl");
				}
			 if(validation.checkConditionLength(updateid.getItem_type())  == false){
					model.put("msg",validation.nomenMSG);
					return new ModelAndView("redirect:item_master");
				}
			 if(validation.checkBroadCategoryLength(updateid.getItem_group())  == false){
					model.put("msg",validation.itemGrpMSG);
					return new ModelAndView("redirect:item_master");
				}
				if(validation.checkBroadCategoryLength(updateid.getCategory_code())  == false){
					model.put("msg",validation.itemCatMSG);
					return new ModelAndView("redirect:item_master");
				}
				if(validation.checkParent_codeLength(updateid.getCritical_equipment())  == false){
					model.put("msg",validation.critiEqpMSG);
					return new ModelAndView("redirect:item_master");
				}
			 String engr_stores_origin = request.getParameter("engr_stores_origin");
			 if(engr_stores_origin.equals("0") || engr_stores_origin == "" || engr_stores_origin.equals("") || engr_stores_origin == null || engr_stores_origin.isEmpty())
				{
				 	model.put("updateid", updateid.getId());
					model.put("msg", "Please Enter Broad Category of Stores.");
					return new ModelAndView("redirect:updateItemArmUrl");
				}
			 if(validation.checkBroadCategoryLength(updateid.getEngr_stores_origin())  == false){
				 model.put("msg",validation.brdCatStrMSG);
					return new ModelAndView("redirect:item_master");
				}
				String acc_units = request.getParameter("acc_units");
				 if(acc_units.equals("0") || acc_units == "" || acc_units.equals("") || acc_units == null || acc_units.isEmpty())
					{
					 	model.put("updateid", updateid.getId());
						model.put("msg", "Please Enter Accounting Unit.");
						return new ModelAndView("redirect:updateItemArmUrl");
					}
				 if(validation.checkLocationLength(updateid.getAcc_units())  == false){
						model.put("msg",validation.accUnitMSG);
						return new ModelAndView("redirect:item_master");
					}
					if(validation.checkFormationLength(updateid.getClass_item())  == false){
						model.put("msg",validation.classMSG);
						return new ModelAndView("redirect:item_master");
					}
					if(validation.checkRemarksLength(updateid.getRemarks())  == false){
						model.put("msg",validation.remarksMSG);
						return new ModelAndView("redirect:item_master");
				 }	
			 
		String username = session.getAttribute("username").toString();
		itemmaster.UpdateItemMasterValue(updateid,username);		
		model.put("msg", "Updated Successfully");
		
		}	
		catch (Exception e) {
			
		}
		finally{
			
		}
		return new ModelAndView("redirect:item_master");
	} 
	
	@RequestMapping(value = "/getitemcodeUrl", method = RequestMethod.POST)
	public @ResponseBody List<String> getitemcodeUrl(String cos) {
		  String itemCode ="";
		    String prfcode = cos;
		    
		    char ch = prfcode.charAt(0);
		    
		    // You can also cast char to int
		    int castAscii = (int) ch;
		        
		    //Prf First Two Degits 
		    String first = String.format("%02d", castAscii - 64);

		    //Prf second Two Degits 
		        
		    String second = String.valueOf(prfcode.charAt(2)) + String.valueOf(prfcode.charAt(3)); 
		    
		    itemCode = first + second;
		        
		    // Serial No
		    Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("SELECT max(item_code) FROM CUE_TB_MISO_MMS_ITEM_MSTR where item_code like:itemCode");
			q.setParameter("itemCode", itemCode + "%");
			String list = (String) q.list().get(0);
			tx.commit();
			session.close();
			
			String serial = "";
			if(list == null) {
				serial = "000";
			}else {
				serial = String.valueOf(list.charAt(4))+String.valueOf(list.charAt(5))+String.valueOf(list.charAt(6));
			}
			int serialNo = Integer.parseInt(serial) + 1;
			serial = String.format("%03d", serialNo);
		    itemCode += serial;
		    
			int sum = 0;
			int length = itemCode.length()+1;
			for(int i = 0; i<itemCode.length();i++) {
				String multi = String.valueOf(itemCode.charAt(i)); 
				int ans = Integer.parseInt(multi) * length;
				sum += ans;
				length--;
				
			}
			int mod = sum % 11;
			
			Session sessionT = HibernateUtil.getSessionFactory().openSession();
			Transaction txT = sessionT.beginTransaction();
			Query qT = sessionT.createQuery("select label FROM T_Domain_Value where domainid='CHECKDIGIT' and codevalue=:mod");
			qT.setParameter("mod", String.valueOf(mod));
			@SuppressWarnings("unchecked")
			List<String> list1 =  (List<String>)  qT.list();
			txT.commit();
			sessionT.close();
			
			itemCode +=list1.get(0);
	            
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			List<String> listItemCode = new ArrayList();
	        
	        listItemCode.add(itemCode);
	        
	        return listItemCode;
	}
	
	public List<Object[]> getitemgroup(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid=:domainid order by label");
		q.setParameter("domainid", "ITEMGROUP");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getItemNameInMst", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getItemNameInMst(HttpSession sessionUserId,String item_type) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {		
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code !='' and status_active='Active' and upper(item_type) like:item_type order by item_type").setMaxResults(10);
		q.setParameter("item_type",item_type.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);
	}
	
	@SuppressWarnings("unchecked")
	public Boolean itemmaster_exits(String item_type) {
		String hql="SELECT m.item_type FROM CUE_TB_MISO_MMS_ITEM_MSTR m where m.item_type=:item_type";
		List<CUE_TB_MISO_MMS_ITEM_MSTR> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("item_type", item_type);
			users = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) query.list();			
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
		else
			return false;
	}
	
}