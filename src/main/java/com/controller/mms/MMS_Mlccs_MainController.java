package com.controller.mms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_Mlccs_MainDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.MMS_TB_MLCCS_MSTR_DETL;
import com.models.MMS_TB_MLCCS_NEW_REQ;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MMS_Mlccs_MainController {
	
	@Autowired
    private MMS_Mlccs_MainDAO m1DAO;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	ValidationController validation = new ValidationController();
	//MLCCS MODULE (1)-> (VIEW MLCCS SCREEN START)
	@RequestMapping(value = "/mms_mlccs_view", method = RequestMethod.GET)
	public ModelAndView mms_mlccs_view(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mms_mlccs_view",roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);
		Mmap.put("m_1", m1DAO.MlccsView("", "ALL", "NOM",""));
		Mmap.put("ml_4", m1.getDomainListingData("MMSCLASSA"));
		Mmap.put("msg", msg);
		return new ModelAndView("mms_mlccs_viewTiles");
	}
	//MLCCS MODULE (1)-> (VIEW MLCCS SCREEN END) 
	
	//MLCCS MODULE (2)-> (ADD NEW EQPT IN MLCCS SCREEN START)
	@RequestMapping(value = "/mms_mlccs_new_req", method = RequestMethod.GET)
	public ModelAndView cap_gs_pool(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mms_mlccs_new_req", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("item_ca", m1.getDomainListingData("MMSITEMCATEGORY"));
		Mmap.put("au_ca", m1.getDomainListingData("ACCOUNTINGUNITS"));
		Mmap.put("item_st", m1.getDomainListingData("ITEMSTATUS"));
		return new ModelAndView("mms_mlccs_new_reqTiles");
	}
	//MLCCS MODULE (2)-> (ADD NEW EQPT IN MLCCS SCREEN END)
	
	//MLCCS MODULE (3)-> (STATUS OF REQUEST SCREEN START)
	@RequestMapping(value = "/mms_mlccs_new_req_view", method = RequestMethod.GET)
	public ModelAndView mms_mlccs_new_req_view(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mms_mlccs_new_req_view", sessionA.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		Mmap.put("r_1", l1);		
		String q = null ;
		Mmap.put("m_1", m1DAO.NewReqEqptAppList(q));
		Mmap.put("msg", msg);
		return new ModelAndView("mms_mlccs_new_req_viewTiles");
	}
	//MLCCS MODULE (3)-> (STATUS OF REQUEST SCREEN END)
	
	//MLCCS MODULE (4)-> (CAPTURE MLCCS DETAILS SCREEN START)
	@RequestMapping(value = "/mms_mlccs_master", method = RequestMethod.GET)
	public ModelAndView mms_mlccs_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_mlccs_master", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("ml_1", m1.getDomainListingData("ACCOUNTINGUNITS"));
		Mmap.put("ml_2", m1.getDomainListingData("ITEMSTATUS"));
		Mmap.put("ml_3", m1.getDomainListingData("MMSITEMCATEGORY"));
		Mmap.put("ml_4", m1.getDomainListingData("MMSCLASSA"));
		Mmap.put("ml_5", m1.getDomainListingData("SPONSERDTE"));
		Mmap.put("ml_6", m1.getDomainListingData("DTEEQPTCATEGORY"));
		Mmap.put("ml_7", m1.getDomainListingData("EQPTPRIORITY"));
		Mmap.put("ml_8", m1.getDomainListingData("DIGESTCATEGORY"));
		return new ModelAndView("mms_mlccs_masterTiles");
	}
	//MLCCS MODULE (4)-> (CAPTURE MLCCS DETAILS SCREEN END)
	
	//MLCCS MODULE (5)-> (LINK EQPT WITH UE SCREEN START)
	@RequestMapping(value = "/mms_mlccs_link_with_itemcode", method = RequestMethod.GET)
	public ModelAndView mlccsDetails(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		Boolean val = roledao.ScreenRedirect("mms_mlccs_link_with_itemcode", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_mlccs_link_with_itemcodeTiles");
	}
	//MLCCS MODULE (5)-> (LINK EQPT WITH UE SCREEN END)
	
	//(1) -> VIEW MLCCS SCREEN METHODS START
	@RequestMapping(value = "/admin/mms_mlccs_list", method = RequestMethod.POST)
	public ModelAndView mms_mlccs_list(@ModelAttribute("m1_nomen") String m1_nomen,
			@ModelAttribute("m1_class_category") String m1_class_category,String m1_item,ModelMap model,HttpSession sessionA){
		
		Boolean val = roledao.ScreenRedirect("mms_mlccs_view", sessionA.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(sessionA);
		
		//ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(userid);
		model.put("r_1", l1);
		model.put("m_1", m1DAO.MlccsView(m1_nomen, "ALL", m1_item,m1_class_category));
		model.put("m_2", m1_nomen);
		model.put("m_3", m1_item);
		model.put("ml_4", m1.getDomainListingData("MMSCLASSA"));
		return new ModelAndView("mms_mlccs_viewTiles");	
	}
	
	@RequestMapping(value = "/mms_mlccs_mstr", method = RequestMethod.POST)
    public ModelAndView mms_mlccs_mstr(@RequestParam("census_nohid") String census_nohid,@RequestParam("cosid") Integer cosid,
    		@RequestParam("cos_sechid") String cos_sechid,@RequestParam("nPara") String nPara,ModelMap model,
    		@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_mlccs_master", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("cosid1", cosid);
        model.put("census_nohid1", census_nohid);
        model.put("cos_sechid1", cos_sechid);
        model.put("nPara1", nPara);
        model.put("ml_1", m1.getDomainListingData("ACCOUNTINGUNITS"));
        model.put("ml_2", m1.getDomainListingData("ITEMSTATUS"));
        model.put("ml_3", m1.getDomainListingData("MMSITEMCATEGORY"));
        model.put("ml_4", m1.getDomainListingData("MMSCLASSA"));
        model.put("ml_5", m1.getDomainListingData("SPONSERDTE"));
        model.put("ml_6", m1.getDomainListingData("DTEEQPTCATEGORY"));
        model.put("ml_7", m1.getDomainListingData("EQPTPRIORITY"));
        model.put("ml_8", m1.getDomainListingData("DIGESTCATEGORY"));
        if(nPara.equalsIgnoreCase("MODE")) {
        	List<String> a1 = mmsCommonDAO.getMMSMlccsList(census_nohid, "CENSUS");
        	model.put("a_1", a1);
        	
        }
   
        if(nPara.equalsIgnoreCase("APP")) {
        	List<MMS_TB_MLCCS_NEW_REQ> a2 = m1.getmmsNewReqList(cosid,session);
        
        	model.put("a_5", a2);
        	
        }
       
        return new ModelAndView("mms_mlccs_masterTiles");
    }
	
	
	
	//(1) -> VIEW MLCCS SCREEN METHODS END
	
	//(2) -> ADD NEW EQPT IN MLCCS SCREEN METHODS START
	@RequestMapping(value = "/getIntroAgencyList",method = RequestMethod.POST)
	public @ResponseBody List<String> getIntroAgencyList(String y,HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct label from MMS_Domain_Values where lower(label) like :label or Upper(label) like :label and "
				+ "domainid='SPONSERDTE'");
		q.setParameter("label", y.toLowerCase()+"%");
		q.setParameter("label", y.toUpperCase()+"%");
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		
		List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
		
		return FList;
	}
	
	@RequestMapping(value = "/mms_mlccs_new_reqAction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView mms_mlccs_new_reqAction(@ModelAttribute("mms_mlccs_new_reqCMD") MMS_TB_MLCCS_NEW_REQ rs,HttpServletRequest request,ModelMap model,HttpSession session,
			@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1) throws ParseException{

		Boolean val = roledao.ScreenRedirect("mms_mlccs_new_req", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String extension="";
		String fname = "";
		String username = session.getAttribute("username").toString();
		int roleid = (Integer)session.getAttribute("roleid");
		//Validation Start
		String req_agency = request.getParameter("req_agency");
		String auth_lett_no = request.getParameter("auth_lett_no");
		String auth_date_i = request.getParameter("auth_date");
		String cat_part_no = request.getParameter("cat_part_no");
		String nomen = request.getParameter("nomen").toUpperCase();
		String au = request.getParameter("au");
		String item_status = request.getParameter("item_status");
		String item_category = request.getParameter("item_category");
		
		//////
		if(req_agency.equals("")){
			model.put("msg", "Please Enter Inroducing Agency");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(validation.checkReqAgencyLength(rs.getReq_agency()) == false){
	 		model.put("msg",validation.req_agencyMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		
		if(auth_lett_no.equals("")){
			model.put("msg", "Please Enter Auth/Letter No.");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(validation.checkCatPartNoLength(rs.getAuth_lett_no()) == false){
	 		model.put("msg",validation.auth_lett_noMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		
	  if(auth_date_i.equals("")){
			model.put("msg", "Please Select Auth Date");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
	  
	  if(validation.checkDateLength(rs.getAuth_date()) == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
	  
		if(cat_part_no.equals("")){
			model.put("msg", "Please Enter Cat/Part No");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		if(validation.check30Length(rs.getCat_part_no()) == false){
	 		model.put("msg",validation.cat_part_noMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(nomen.equals("")){
			model.put("msg", "Please Enter Nomenclature");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(validation.check255Length(rs.getNomen()) == false){
	 		model.put("msg",validation.nomenMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(validation.check255Length(rs.getBrief_desc()) == false){
	 		model.put("msg",validation.brief_descMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(au.equals("")){
			model.put("msg", "Please Select Accounting Units");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(item_status.equals("")){
			model.put("msg", "Please Select Item Status");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(item_category.equals("")){
			model.put("msg", "Please Select Item Category");
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		if(validation.check255Length(rs.getSpl_remarks()) == false){
	 		model.put("msg",validation.disposal_remarksMSG);
			return new ModelAndView("redirect:mms_mlccs_new_req");
		}
		
		
		String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());        
	          if(!upload_imgEXt.toUpperCase().equals("jpg".toUpperCase()) && !upload_imgEXt.toUpperCase().equals("jpeg".toUpperCase())
	        		  && !upload_imgEXt.toUpperCase().equals("png".toUpperCase()) && !upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {
	                model.put("msg", "Only *.jpg, *.jpeg *.png *.pdf file extensions allowed");
	                return new ModelAndView("redirect:mms_mlccs_new_req");
	          }
				
	          
	          
	    int userid = Integer.parseInt(session.getAttribute("userId").toString());
		if (!doc_upload1.isEmpty()) {
			// code modify by Paresh on 05/05/2020 
            try {
            	DateWithTimeStampController timestamp = new DateWithTimeStampController();
                byte[] bytes = doc_upload1.getBytes();
                String  mmsFilePath = session.getAttribute("mmsFilePath").toString();
                File dir = new File(mmsFilePath);
                if (!dir.exists()){
                	dir.mkdirs();
                }
                String filename = doc_upload1.getOriginalFilename();
                int i = filename.lastIndexOf('.');
                if (i >= 0) {
                	extension = filename.substring(i+1);
                }
                fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MMSDOC."+extension;
                File serverFile = new File(fname);                       
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	            stream.write(bytes);                        
	            stream.close();
	            rs.setUpload_file_name(fname);
	            if(validation.checkImageAnmlLength(rs.getUpload_file_name()) == false){
	    	 		model.put("msg",validation.image_MMSMSG);
	    			return new ModelAndView("redirect:mms_mlccs_new_req");
	    		}
	        }
	        catch (Exception e) {
	        }
		}
		rs.setData_cr_by(username);		
		rs.setRoleid(roleid);	
		rs.setOp_status("0");
		rs.setNomen(nomen);
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		sessionHQL.save(rs);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		model.put("msg", "Data saved Successfully");
		return new ModelAndView("redirect:mms_mlccs_new_req");
	}
		
	//(2) -> ADD NEW EQPT IN MLCCS SCREEN METHODS END
	
	//(3) -> STATUS OF REQUEST SCREEN METHODS START
	
	@RequestMapping(value = "/updateRejectNewEqptReq", method = RequestMethod.POST)	 
	@ResponseBody public  List<String> updateRejectNewEqptReq(String remarks,String letter_no,Integer id) {

		List<String> list2  =m1DAO.updatereject_mms_new_eqpt_qry(remarks,letter_no,id);
		return list2;
	}
	
	//(3) -> STATUS OF REQUEST SCREEN METHODS END
	
	//(4) -> CAPTURE MLCCS DETAILS SCREEN METHODS START changes by mitesh (19-02-2025)
		@RequestMapping(value = "/mms_mlccs_masterAction",method = RequestMethod.POST)
		public @ResponseBody ModelAndView mms_mlccs_masterAction(@ModelAttribute("mms_mlccs_masterActionCMD") MMS_TB_MLCCS_MSTR_DETL rs,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
			Boolean val = roledao.ScreenRedirect("mms_mlccs_master", session.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String nPara=request.getParameter("nPara");
			String cen=request.getParameter("idhid");
			
			String cos_sec = request.getParameter("cos_sec");
			String census_no = request.getParameter("census_no");
			String nomen = request.getParameter("nomen");
			String auth_lett_no = request.getParameter("auth_lett_no");
			String auth_date = request.getParameter("auth_date");
			String prf_code = request.getParameter("prf_code");
			String item_code = request.getParameter("item_code");
			
			String cat_part_no = request.getParameter("cat_part_no");
			String au = request.getParameter("au");
			String brief_desc = request.getParameter("brief_desc");
			String item_status = request.getParameter("item_status");
			String item_category = request.getParameter("item_category");
			String class_category = request.getParameter("class_category");
			
			
			if(cos_sec.equals("")){
				 model.put("msg", "Please Select COS Section No");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkCosSecLength(rs.getCos_sec()) == false){
		 		model.put("msg",validation.cos_secMSG);
		 		 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(census_no.equals("")){
				 model.put("msg", "Please Select Census No");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkCenSusnoLength(rs.getCensus_no()) == false){
		 		model.put("msg",validation.census_noMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			
			if(nomen.equals("")){
				 model.put("msg", "Please Select the nomenclature");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			if(validation.check255Length(rs.getNomen()) == false){
		 		model.put("msg",validation.nomenMSG);
		 		 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			if(auth_lett_no.equals("")){
				 model.put("msg", "Please Enter the Auth Lette No");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			if(validation.checkCatPartNoLength(rs.getAuth_lett_no()) == false){
		 		model.put("msg",validation.auth_lett_noMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			
			
			Date auth_date_i = null;
			DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			try{
				if(auth_date.equals("")){
					model.put("msg", "Please Select the Date");
					if(nPara.equals("APP")) {
						return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
					}else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
						return new ModelAndView("redirect:mms_mlccs_view");
					}else{
						return new ModelAndView("redirect:mms_mlccs_master");
					} 
				}else{
					auth_date_i = formatter1.parse(request.getParameter("auth_date"));
				}
				
				if(validation.checkDateLength(rs.getAuth_date()) == false){
			 		model.put("msg",validation.dateMSG);
			 		if(nPara.equals("APP")) {
						return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
					}else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
						return new ModelAndView("redirect:mms_mlccs_view");
					}else{
						return new ModelAndView("redirect:mms_mlccs_master");
					} 
				}
			  
				
			}catch(ParseException e){         
				e.printStackTrace();
			}
			
			
			
			
			if(prf_code.equals("")){
				 model.put("msg", "Please Select PRF Group");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(item_code.equals("Select")){
				 model.put("msg", "Please Select Item Code");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(cat_part_no.equals("")){
				 model.put("msg", "Please Enter the Cat Part No");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.check30Length(rs.getCat_part_no()) == false){
		 		model.put("msg",validation.cat_part_noMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			if(au.equals("")){
				 model.put("msg", "Please Select the Accounting Units");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(brief_desc.equals("")){
				 model.put("msg", "Please Enter the Description");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			if(validation.check255Length(rs.getBrief_desc()) == false){
		 		model.put("msg",validation.brief_descMSG);
		 		 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(item_status.equals("")){
				 model.put("msg", "Please select the Item Status");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(item_category.equals("")){
				 model.put("msg", "Please select the Item Category");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(class_category.equals("")){
				 model.put("msg", "Please select the Class category");
				 if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			
			if(validation.checkOriginCountryLength(rs.getOrigin_country()) == false){
		 		model.put("msg",validation.origin_countryMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkNomenLength(rs.getManuf_agency()) == false){
		 		model.put("msg",validation.manuf_agencyMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkInducYearLength(rs.getInduc_year()) == false){
		 		model.put("msg",validation.induc_yearMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			if(validation.checkNomenLength(rs.getAhsp_agency()) == false){
		 		model.put("msg",validation.ahsp_agencyMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkCatPartNoLength(rs.getNato_stk_no()) == false){
		 		model.put("msg",validation.nato_stk_noMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.checkCatPartNoLength(rs.getDef_cat_no_dcan()) == false){
		 		model.put("msg",validation.def_cat_no_dcanMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			if(validation.check255Length(rs.getSpl_remarks()) == false){
		 		model.put("msg",validation.remarksMSG);
		 		if(nPara.equals("APP")) {
					 return new ModelAndView("redirect:mms_mlccs_new_req_view"); 
				 }else if(nPara.equals("VIEW") || nPara.equals("MODE")) {
					 return new ModelAndView("redirect:mms_mlccs_view");
				 }else {
					 return new ModelAndView("redirect:mms_mlccs_master");
				 } 
			}
			
			///Added by mitesh for get prf_group from master (19-02-2025)
	        Session sessionOrbat = HibernateUtil.getSessionFactory().openSession();
	        Transaction txOrbat = sessionOrbat.beginTransaction();
	        String queryStr ="select prf_group,prf_group_code from cue_tb_miso_prf_group_mst where status_active='Active' and prf_group_code=:prf_code";
	        Query query10 = sessionOrbat.createSQLQuery(queryStr);
	        query10.setParameter("prf_code", request.getParameter("prf_code"));
	        
	        List<Object[]> resultList = query10.list();

	        String prf_group = null, prf_code1 = null;

	        if (!resultList.isEmpty()) {
	            Object[] row = resultList.get(0); // Get the first row
	            prf_group = (String) row[0];
	            prf_code1 = (String) row[1];
	        }
	        txOrbat.commit();
	        sessionOrbat.close();
			
		 	Session sessions = HibernateUtil.getSessionFactory().openSession();
			Transaction txs = sessions.beginTransaction();	
			Query q=null;
			q = sessions.createQuery("select distinct census_no from MMS_TB_MLCCS_MSTR_DETL where census_no=:census_no");
			q.setParameter("census_no", request.getParameter("census_no"));
			@SuppressWarnings("unchecked")
			List<MMS_TB_MLCCS_MSTR_DETL> lists = (List<MMS_TB_MLCCS_MSTR_DETL>) q.list();
			txs.commit();
			sessions.close();
			Double cost =Double.parseDouble(request.getParameter("cost"));
			if(request.getParameter("cost")==""){
				rs.setCost(Double.parseDouble("0"));
			}
			else{
				rs.setCost(cost);
			}
			if(lists.size() > 0){
				Session session1 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				String hql = "update MMS_TB_MLCCS_MSTR_DETL set auth_lett_no=:auth_lett_no, auth_date=:auth_date ,cos_sec=:cos_sec , prf_code=:prf_code , prf_group=:prf_group, spl_remarks=:spl_remarks, cat_part_no=:cat_part_no, nomen=:nomen, brief_desc=:brief_desc, au=:au, item_status=:item_status, item_category=:item_category, origin_country=:origin_country, manuf_agency=:manuf_agency, ahsp_agency=:ahsp_agency, induc_year=:induc_year,"
						+ " nato_stk_no=:nato_stk_no, def_cat_no_dcan=:def_cat_no_dcan, ces_no=:ces_no, class_category=:class_category,dte_category=:dte_category,item_seq_no=:item_seq_no, item_code=:item_code, cost=:cost, op_status='1',digest_category=:digest_category,dte_eqpt_category=:dte_eqpt_category where census_no=:census_no";
			    Query query = session1.createQuery(hql).setString("auth_lett_no", request.getParameter("auth_lett_no")).setString("auth_date",request.getParameter("auth_date") ).setString("cos_sec", request.getParameter("cos_sec")).setString("prf_code",request.getParameter("prf_code")).setString("prf_group",prf_group).setString("spl_remarks",request.getParameter("spl_remarks")).setString("cat_part_no", request.getParameter("cat_part_no"))
			    		.setString("census_no", request.getParameter("census_no")).setString("nomen", request.getParameter("nomen")).setString("brief_desc", request.getParameter("brief_desc")).setString("au", request.getParameter("au")).setString("item_status", request.getParameter("item_status")).setString("item_category", request.getParameter("item_category")).setString("origin_country", request.getParameter("origin_country")).setString("manuf_agency", request.getParameter("manuf_agency"))
			    		.setString("ahsp_agency", request.getParameter("ahsp_agency")).setString("induc_year", request.getParameter("induc_year")).setString("nato_stk_no", request.getParameter("nato_stk_no")).setString("def_cat_no_dcan", request.getParameter("def_cat_no_dcan")).setString("ces_no", request.getParameter("ces_no")).setString("class_category", request.getParameter("class_category")).setString("dte_category", request.getParameter("dte_category"))
			    		.setString("item_seq_no", request.getParameter("item_seq_no")).setString("item_code", request.getParameter("item_code")).setDouble("cost", cost).setString("digest_category", request.getParameter("digest_category")).setString("dte_eqpt_category", request.getParameter("dte_eqpt_category"));
			    int rowCount = query.executeUpdate(	);
			    tx.commit();
				session1.close();
				if(rowCount > 0) {
					model.put("msg", "Data Updated Successfully...");
				}else {
					model.put("msg", "Data not Updated Successfully...");
				}
			}
			else{
				String username = session.getAttribute("username").toString();
				rs.setData_cr_by(username);
				
				rs.setAuth_lett_no(request.getParameter("auth_lett_no"));
				rs.setAuth_date(request.getParameter("auth_date"));
				rs.setCos_sec(request.getParameter("cos_sec"));
				rs.setPrf_code(request.getParameter("prf_code"));
				rs.setPrf_group(request.getParameter("prf_group"));
				rs.setCat_part_no(request.getParameter("cat_part_no"));
				rs.setCensus_no(request.getParameter("census_no"));
				rs.setNomen(request.getParameter("nomen"));
				rs.setBrief_desc(request.getParameter("brief_desc"));
				rs.setAu(request.getParameter("au"));
				rs.setItem_status(request.getParameter("item_status"));
				rs.setItem_category(request.getParameter("item_category"));
				rs.setOrigin_country(request.getParameter("origin_country"));
				rs.setManuf_agency(request.getParameter("manuf_agency"));
				rs.setAhsp_agency(request.getParameter("ahsp_agency"));
				rs.setInduc_year(request.getParameter("induc_year"));
				rs.setNato_stk_no(request.getParameter("nato_stk_no"));
				rs.setDef_cat_no_dcan(request.getParameter("def_cat_no_dcan"));
				rs.setCes_no(request.getParameter("ces_no"));
				rs.setUpload_file_name(request.getParameter("upload_file_name"));
				rs.setSpl_remarks(request.getParameter("spl_remarks"));
				rs.setRemarks(request.getParameter("remarks"));
				rs.setData_cr_by(username);
				rs.setData_upd_by(username);
				rs.setOp_status("1");
				rs.setClass_category(request.getParameter("class_category"));
				rs.setDte_category(request.getParameter("dte_category"));
				rs.setDte_eqpt_category(request.getParameter("dte_eqpt_category"));
				rs.setActive_status("Active");
				rs.setItem_seq_no(request.getParameter("item_seq_no"));
				rs.setItem_code(request.getParameter("item_code"));
				rs.setDigest_category(request.getParameter("digest_category"));
		
				rs.setData_cr_date(new Date());
				rs.setData_upd_date(new Date());
				
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				sessionHQL.beginTransaction();
				sessionHQL.save(rs);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();
				model.put("msg", "Data saved Successfully");
			}
			
			if(!(cen.equals("") || cen.equals(null))) {
				//update alloted cencus no 
				Session session2 = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx1 = session2.beginTransaction();
				Query  query1 = session2.createQuery("update MMS_TB_MLCCS_NEW_REQ set op_status='1',alot_census_no=:alot_census_no  where id=:id ");
				query1.setParameter("alot_census_no",request.getParameter("census_no"));
				query1.setParameter("id",Integer.parseInt(cen));
			    query1.executeUpdate();
			    tx1.commit();
			    session2.close();
			}
			
			if(nPara.equals("APP"))	{
				model.put("msg", "Data saved Successfully... ");
				return new ModelAndView("redirect:mms_mlccs_new_req_view");
			}
			else if(nPara.equals("VIEW") || nPara.equals("MODE"))	{
				model.put("msg", "Data Updated Successfully... ");
				return new ModelAndView("redirect:mms_mlccs_view");
			}
			else {
				model.put("msg", "Data saved Successfully...");
				return new ModelAndView("redirect:mms_mlccs_master");
			}
		}
	//(4) -> CAPTURE MLCCS DETAILS SCREEN METHODS END
	
	//(5) -> LINK EQPT WITH UE SCREEN METHODS START
	@RequestMapping(value = "/mms_link_itemcode",method = RequestMethod.POST)
	public @ResponseBody ModelAndView mms_link_itemcode(ModelMap model,@ModelAttribute("mms_link_itemcodeCMD") MMS_TB_MLCCS_MSTR_DETL updateid,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		Boolean val = roledao.ScreenRedirect("mms_mlccs_link_with_itemcode", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String census_no= request.getParameter("census_no");
		String nomen = request.getParameter("nomen");
		String item_code= request.getParameter("item_code");
		String cat_part_no= request.getParameter("cat_part_no");
		String prf_group= request.getParameter("prf_group");
		
		if(census_no.equals("")){
			 model.put("msg", "Please Enter Census No");
			 return new ModelAndView("redirect:mms_mlccs_link_with_itemcode"); 
		}
		if(validation.checkCenSusnoLength(updateid.getCensus_no()) == false){
	 		model.put("msg",validation.census_noMSG);
				 return new ModelAndView("redirect:mms_mlccs_link_with_itemcode"); 
		}
		
		if(nomen.equals("")){
			 model.put("msg", "Please Enter Nomenclature");
			 return new ModelAndView("redirect:mms_mlccs_link_with_itemcode"); 
		}
		if(validation.check255Length(updateid.getNomen()) == false){
	 		model.put("msg",validation.nomenMSG);
			return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
		}
		
		if(cat_part_no.equals("")){
			model.put("msg", "Please Enter Cat/Part No");
			return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
		}
		if(validation.check30Length(updateid.getCat_part_no()) == false){
	 		model.put("msg",validation.cat_part_noMSG);
			return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
		}
		
		if(prf_group.equals("")){
			model.put("msg", "Please Enter PRF Group No");
			return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
		}
		if(validation.checkPrf_CodeLength(updateid.getPrf_code()) == false){
	 		model.put("msg",validation.prfMSG);
			return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
		}
		
		if(item_code.equals("")){
			 model.put("msg", "Please Select Linked Item Code");
			 return new ModelAndView("redirect:mms_mlccs_link_with_itemcode"); 
		}
		
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		String hql = "update MMS_TB_MLCCS_MSTR_DETL  set item_code=:item_code where census_no=:census_no";
	    Query query = session1.createQuery(hql).setString("item_code", item_code).setString("census_no",census_no);
	  	int rowCount = query.executeUpdate();
		tx.commit();
		session1.close();
		if(rowCount > 0) {
			model.put("msg", "Data Update Successfully...");
		}else {
			model.put("msg", "Data not Update Successfully...");
		}
		return new ModelAndView("redirect:mms_mlccs_link_with_itemcode");
	}
	//(5) -> LINK EQPT WITH UE SCREEN METHODS END
}
