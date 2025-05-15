package com.controller.psg.Jco_Service_Record;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Service_Record.View_JCODao;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class View_OfficerJCO_Controller {
	@Autowired
	View_JCODao UJD;
	
	@Autowired
	Search_UpdatedJcoOr_DataDao SD;
	
	@Autowired
	Psg_CommanDAO psg_com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	ValidationController valid = new ValidationController();
	
	psg_jco_CommonController common = new psg_jco_CommonController();
	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/View_JCODataUrl" , method = RequestMethod.GET)
	 public ModelAndView View_JCODataUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("View_JCODataUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		

		 String roleType = session.getAttribute("roleType").toString();
		/* Boolean val = roledao.ScreenRedirect("View_JCODataUrl", roleid);	
		
		  if(val == false) { return new ModelAndView("AccessTiles"); }*/
		  
		
			 String roleSusNo = session.getAttribute("roleSusNo").toString();
			 String roleAccess = session.getAttribute("roleAccess").toString();
			
	   		 if(roleAccess.equals("Unit")){
	   				Mmap.put("sus_no",roleSusNo);
	   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
	   			}
	   		Mmap.put("msg", msg);	
		 	Mmap.put("getRankjcoList", common.getRankjcoList());
			Mmap.put("getParentArmList", mcommon.getParentArmList());
		/*Mmap.put("list", UJD.Search_JCOData("","","","0","0",roleSusNo,roleType,"","","","","1"));
		*/

		 return new ModelAndView("Search_view_JCOTiles");
	 }
	
	@RequestMapping(value = "/admin/GetSearch_JCOData_view", method = RequestMethod.POST)
    public ModelAndView GetSearch_JCOData_view(ModelMap Mmap,HttpSession session,
                    @RequestParam(value = "msg", required = false) String msg,
                    @RequestParam(value = "army_no1", required = false) String army_no,
                    @RequestParam(value = "status1", required = false) String status,
                    @RequestParam(value = "rank1", required = false) String rank,
                    @RequestParam(value = "unit_name1", required = false) String unit_name,
                @RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
                @RequestParam(value = "name1", required = false) String name,
                    @RequestParam(value = "year_of_comm1", required = false) String year_of_comm,
                    @RequestParam(value = "year_of_dob1", required = false) String year_of_dob,
                @RequestParam(value = "p_arm1", required = false) String p_arm,
                @RequestParam(value = "comm_status1", required = false) String comm_status){
     
             unit_name = unit_name.replace("&#40;", "(");
            unit_name = unit_name.replace("&#41;", ")");
          String roleType = session.getAttribute("roleType").toString();
          String roleSusNo = session.getAttribute("roleSusNo").toString();
          String roleAccess = session.getAttribute("roleAccess").toString();
                       
                        if(roleAccess.equals("Unit")){
                                       Mmap.put("sus_no",roleSusNo);
                                       Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
                               }
                        if(unit_sus_no!="") {
                          if (!valid.SusNoLength(unit_sus_no)) {
                                            Mmap.put("msg", valid.SusNoMSG);
                                            return new ModelAndView("redirect:View_JCODataUrl");
                                    }
                          
                          if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
                                            Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
                                            return new ModelAndView("redirect:View_JCODataUrl");
                                    }
                  }
                     
                     if(army_no!="") {
                              if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
                                            Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
                                            return new ModelAndView("redirect:View_JCODataUrl");
                                    }                  
                          
                              if (army_no.length() < 2 || army_no.length() > 12) {
                                            Mmap.put("msg", "Army No Should Contain Maximum 12 Character");
                                            return new ModelAndView("redirect:View_JCODataUrl");
                                    }
                  }
                    
                     if(unit_name!="") {
                    	 if (!valid.isUnit(unit_name)) {
                             Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                             return new ModelAndView("redirect:View_JCODataUrl");
                     }
                          
//                          if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//                                            Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//                                            return new ModelAndView("redirect:View_JCODataUrl");
//                                    }
                  }
                     
                     if (year_of_comm != "") {
                             if (valid.isOnlyNumer(year_of_comm) == true) {
                                    Mmap.put("msg", " Year of Commission  " + valid.isOnlyNumerMSG);
                                    return new ModelAndView("redirect:View_JCODataUrl");
                            }
                            if (!valid.YearLength(year_of_comm)) {
                                    Mmap.put("msg", valid.YearMSG);
                                    return new ModelAndView("redirect:View_JCODataUrl");
                            }
                    }
                    
                     if (year_of_dob != "") {
                             if (valid.isOnlyNumer(year_of_dob) == true) {
                                    Mmap.put("msg", " Year of Commission  " + valid.isOnlyNumerMSG);
                                    return new ModelAndView("redirect:View_JCODataUrl");
                            }
                            if (!valid.YearLength(year_of_dob)) {
                                    Mmap.put("msg", valid.YearMSG);
                                    return new ModelAndView("redirect:View_JCODataUrl");
                            }
                    }
                      
                        ArrayList<ArrayList<String>> list = UJD.Search_JCOData(unit_name,unit_sus_no,army_no,status,rank,roleSusNo,roleType,name,year_of_comm,year_of_dob,p_arm,comm_status);
                    Mmap.put("list", list);
                    Mmap.put("size", list.size());
                    Mmap.put("unit_name1", unit_name);
                       Mmap.put("unit_sus_no1", unit_sus_no);
                    Mmap.put("army_no1", army_no);
                    Mmap.put("rank1", rank);
                    Mmap.put("status1", status);
                    Mmap.put("name1", name);
                    Mmap.put("year_of_comm1", year_of_comm);
                    Mmap.put("year_of_dob1", year_of_dob);
                    Mmap.put("p_arm1", p_arm);
                    Mmap.put("comm_status1", comm_status);
                    Mmap.put("getParentArmList", mcommon.getParentArmList());
                    Mmap.put("getRankjcoList", common.getRankjcoList());
            return new ModelAndView("Search_view_JCOTiles","Search_view_jco_CMD",new TB_CENSUS_JCO_OR_PARENT());
    }
	
	
	// view page open service record 
	@RequestMapping(value = "/view_JCOUrl", method = RequestMethod.POST)
	public ModelAndView view_JCOUrl(@ModelAttribute("idV") String updateid,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication, HttpServletRequest request,
			HttpSession sessionEdit,HttpSession session) throws NumberFormatException, ParseException  {
	 
	String jco_id = request.getParameter("jco_idV");
	String sus_no = request.getParameter("sus_noV");
	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String roleid = session.getAttribute("roleid").toString();
	int comm_status = Integer.parseInt(request.getParameter("comm_statusV"));

	Boolean val = roledao.ScreenRedirect("Search_UpdateOfficerDataUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
	}
	String roleAccess = session.getAttribute("roleAccess").toString();
		 if(roleAccess.equals("Unit")){
				model.put("sus_no",roleSusNo);
				model.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}else {
				roleSusNo=sus_no;
			}
		model.put("jco_id", jco_id);
		//1
		List<Map<String, Object>> list= UJD.View_JCODataByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);
		model.put("list", list);
		model.put("size", list.size());
		//2
		List<Map<String, Object>> listSH= UJD.Sh_UpadteJCODataByid(Integer.parseInt(updateid),jco_id,comm_status);
		model.put("listSH", listSH);
		model.put("sizeSH", listSH.size());
		
		List<Map<String, Object>> listCHILD= UJD.CHILD_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo);	
		model.put("listCHILD", listCHILD);
		model.put("sizeCHILD", listCHILD.size());
 
		//family details and spouse 
		List<Map<String, Object>> listFM= UJD.FM_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listFM", listFM);
		model.put("sizeFM", listFM.size());
		List<Map<String, Object>> listS= UJD.Spouse_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listS", listS);
		model.put("sizeS", listS.size());
		 
		List<Map<String, Object>> listAT= UJD.View_JCOAttachment_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listAT", listAT);
		model.put("sizeAT", listAT.size());
		
		//tenure
		List<Map<String, Object>> listTENU= UJD.TENU_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);		
		model.put("listTENU", listTENU);
		model.put("sizeTENU", listTENU.size());
		
		List<Map<String, Object>> listTENU_T= UJD.TENU_Total_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);
		model.put("listTENU_T", listTENU_T);
		model.put("sizeTENU_T", listTENU_T.size());

		List<Map<String, Object>> listFIS= UJD.Field_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo);	
		model.put("listFIS", listFIS);
		model.put("sizeFIS", listFIS.size());
		
		int monthc=0;
		for(int i=0; i<listTENU.size(); i++) {
			monthc += Integer.parseInt(String.valueOf(listTENU.get(i).get("month")) );
		}
		if(listFIS.size()>0) {
		monthc -= Integer.parseInt(String.valueOf(listFIS.get(0).get("peace")));
		}
		model.put("peace_month", monthc);
		
		//army course
		List<Map<String, Object>> listARM= UJD.ARM_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listARM", listARM);
		model.put("sizeARM", listARM.size());
        // promotional exam
		List<Map<String, Object>> listPE= UJD.PE_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listPE", listPE);
		model.put("sizePE", listPE.size());
		//acedemic Qualification
		List<Map<String, Object>> listAQ= UJD.AQ_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listAQ", listAQ);
		model.put("sizeAQ", listAQ.size());
		//11 professional qualities
		List<Map<String, Object>> listPTQ= UJD.PTQ_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listPTQ", listPTQ);
		model.put("sizePTQ", listPTQ.size());
		//indian lang
		List<Map<String, Object>> listIL= UJD.ILan_View_JCOByid(jco_id,roleSusNo,comm_status);
		model.put("listIL", listIL);
		model.put("sizeIL", listIL.size());
		//foreign lang
		List<Map<String, Object>> listFL= UJD.FLan_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listFL", listFL);
		model.put("sizeFL", listFL.size());
		//award
		List<Map<String, Object>> listAM= UJD.View_JCOAWARD_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listAM", listAM);
		model.put("sizeAM", listAM.size());
		//regimental
		List<Map<String, Object>> listRegimental= UJD.JCORegimental_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo);	
	    model.put("listRegimental", listRegimental);
		model.put("sizelistRegimental", listRegimental.size());
		//Battle & phy casulity
		List<Map<String, Object>> listBA= UJD.JCOBA_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listBA", listBA);
		model.put("sizeBA", listBA.size());
		//address
		List<Map<String, Object>> listAR= UJD.AR_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listAR", listAR);
		model.put("sizeAR", listAR.size());
		//BPET
		List<Map<String, Object>> listB= UJD.BA_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listB", listB);
		model.put("sizeB", listB.size());
		//Firing
		List<Map<String, Object>> listFS= UJD.FS_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listFS", listFS);
		model.put("sizeFS", listFS.size());
		//Foreign lang
		List<Map<String, Object>> listF= UJD.F_View_JCOByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listF", listF);
		model.put("sizeF", listF.size());
		/// Address//
		List<Map<String, Object>> listORM= UJD.JCOORM_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listORM", listORM);
		model.put("sizeORM", listORM.size());
		//20
		List<Map<String, Object>> listPDO= UJD.JCOPDO_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listPDO", listPDO);
		model.put("sizePDO", listPDO.size());
		//21
		List<Map<String, Object>> listPM= UJD.JCOPM_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listPM", listPM);
		model.put("sizePM", listPM.size());
		//22
		List<Map<String, Object>> listPS= UJD.JCOPS_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listPS", listPS);
		model.put("sizePS", listPS.size());
		//23
		List<Map<String, Object>> listNOK= UJD.JCONOK_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listNOK", listNOK);
		model.put("sizeNOK", listNOK.size());
		
		//25
		List<Map<String, Object>> listRD= UJD.JCORD_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listRD", listRD);
		model.put("sizeRD", listRD.size());
		
		 
		//24
		List<Map<String, Object>> listNA= UJD.JCONA_View_UpadteByid(Integer.parseInt(updateid),jco_id,roleSusNo,comm_status);	
		model.put("listNA", listNA);
		model.put("sizeNA", listNA.size());
		model.put("sus_no", sus_no);
		model.put("comm_status", comm_status);
		model.put("serving_status", SD.GetServingStatusJCO(Integer.parseInt(jco_id)).get(0).get(2));
		model.put("msg", msg);
	return new ModelAndView("View_JCOTiles");
 }

	@RequestMapping(value = "/Print_Record_ServiceJCO", method = RequestMethod.POST)
	public ModelAndView Print_Record_ServiceJCO(@ModelAttribute("jcoid1") String jco_id,ModelMap Mmap,
				@ModelAttribute("id1") String id,@ModelAttribute("comm_status1") String comm_status1,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
				HttpSession session,HttpServletRequest request) throws NumberFormatException, ParseException{
			
			String username = session.getAttribute("username").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			List<String> TH = new ArrayList<String>();
			int comm_status = Integer.parseInt(comm_status1);
			String sus_no = request.getParameter("sus_noV");
			String roleAccess = session.getAttribute("roleAccess").toString();
			 if(roleAccess.equals("Unit")){
				 Mmap.put("sus_no",roleSusNo);
				 Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				}else {
					roleSusNo=sus_no;
				}
			 String serving_status= SD.GetServingStatusJCO(Integer.parseInt(jco_id)).get(0).get(2);
		    //--- Personal No Details
	    	List<Map<String, Object>> listp= UJD.View_JCODataByid(Integer.parseInt(jco_id),jco_id,roleSusNo,comm_status);
	    	List<Map<String, Object>> listSH= UJD.Sh_UpadteJCODataByid(Integer.parseInt(jco_id),jco_id,comm_status) ;
	  	    // ---Family Details - No TABLE 
	    	List<Map<String, Object>> listFM=
	    	UJD.FM_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status);
		   //---- Spouse Details
		   List<Map<String, Object>> listS=  UJD.Spouse_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		   List<String> TH1 = new ArrayList<String>();
		   TH1.add("Marital Status"); 
		   TH1.add("Maiden Name"); 
		   TH1.add("Date of Marriage");
		   TH1.add("Date of Birth");
		   TH1.add("Place of Birth"); 
		   TH1.add("Nationality");
		   TH1.add("Aadhar No");
		   TH1.add("PAN"); 
		   TH1.add("Date of Divorce");
		   TH1.add("Spouse Service");
		   TH1.add("Army No"); 
		   //TH1.add("Other Service");
		   //---- Children Details
		   List<Map<String, Object>> listCHILD=  UJD.CHILD_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo);
		   List<String> TH2 = new ArrayList<String>(); 
		   TH2.add("Name");
		   TH2.add("Date of Birth");
		   TH2.add("Gender");
		   //---- AWARDS AND MEDAL 
		   List<Map<String, Object>> listAM= UJD.View_JCOAWARD_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status); 
		   List<String> TH3 = new ArrayList<String>();
		   TH3.add("Award/Medal Type"); 
		   TH3.add("Award/Medal");
		   TH3.add("Date of Award/Medal"); 
		   TH3.add("Unit Concerned");
		  //----------TENURE DETAILS 
		  List<Map<String, Object>> listTENU=  UJD.TENU_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status);
		  List<Map<String, Object>> listTENU_T=  UJD.TENU_Total_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status);
		  List<String> TH4 = new ArrayList<String>();
		  TH4.add("Unit Name");
		  TH4.add("Date of TOS"); 
		  TH4.add("Date of SOS");
		  TH4.add("Place"); 
		  TH4.add("Unit Loc Type"); 
		  TH4.add("Command");
		  TH4.add("Tenure (Months)");
		  //-------REGIMENTAL DUTIES 
		  List<Map<String, Object>> listRegim=  UJD.JCORegimental_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo) ; 
		  List<String> TH5 = new ArrayList<String>(); TH5.add("Unit Name");
		  TH5.add("Rank");
		  TH5.add("Appointment"); 
		  TH5.add("Date"); 
		  TH5.add("Place");
		  TH5.add("Unit Loc Type"); 
		  TH5.add("Command");
		  //---FIELD SERVICE - No TABLE 
		  List<Map<String, Object>> listFIS= UJD.Field_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo);
		  int monthc=0; for(int i=0; i<listTENU.size(); i++) { 
			  monthc += Integer.parseInt(String.valueOf(listTENU.get(i).get("month")) );
			  }
		  if(listFIS.size()>0) { 
			  monthc -= Integer.parseInt(String.valueOf(listFIS.get(0).get("peace"))); 
			  } 
		  String  peace_month = String.valueOf(monthc);
		  //---ARMY COURSE --- 
		  List<Map<String, Object>> listARM=  UJD.ARM_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH6 = new ArrayList<String>();
		  TH6.add("Course Name"); 
		  TH6.add("Grade");
		  TH6.add("Name of Institute");
		  TH6.add("From Date");
		  TH6.add("To Date");
		  //------PROMOTIONAL EXAM 
		  List<Map<String, Object>> listPE= UJD.PE_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		  List<String> TH7 = new ArrayList<String>(); 
		  TH7.add("Exam");
		  TH7.add("Month And Year of Exam");
		  //---ACADEMIC QUALIFICATIONS 
		  List<Map<String, Object>> listAQ=  UJD.AQ_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH8 = new ArrayList<String>();
		  TH8.add("Exam Passed"); 
		  TH8.add("Year of Passing"); 
		  TH8.add("Div/Class");
		  TH8.add("Subjects");
		  TH8.add("Institute");
		  //----PROFESSIONAL/TECHNICAL QUALIFICATIONS
		  List<Map<String, Object>> listPTQ=UJD.PTQ_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo,comm_status); 
		  List<String> TH9 = new ArrayList<String>();
		  TH9.add("Qualification Attained"); 
		  TH9.add("Year of Passing");
		  TH9.add("Div/Class"); 
		  TH9.add("Subjects");
		  //-----------LANGUAGE DETAILS 
		  List<Map<String, Object>> listIL=  UJD.ILan_View_JCOByid(jco_id,roleSusNo, comm_status);
		  List<String> TH10 = new ArrayList<String>();
		  TH10.add("Indian"); 
		  TH10.add("Language Std");
		  
		  //-----------FOREIGN DETAILS 
		  List<Map<String, Object>> listFL= UJD.FLan_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH11 = new ArrayList<String>();
		  TH11.add("Foreign");
		  TH11.add("Language Std");
		  TH11.add("Language Prof");
		  //----------FOREIGN COUNTRIES VISITED 
		  List<Map<String, Object>> listF=UJD.F_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH12 = new ArrayList<String>();
		  TH12.add("Country Visited"); 
		  TH12.add("Purpose of Visit");
		  TH12.add("From");
		  TH12.add("To"); 
		  TH12.add("Duration");
		  //----------BPET 
		  List<Map<String, Object>> listB= UJD.BA_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH13 = new ArrayList<String>();
		  TH13.add("BPET Result");
		  TH13.add("BPET Qe"); 
		  TH13.add("Year");
		  //----------FIRING STANDARD 
		  List<Map<String, Object>> listFS=  UJD.FS_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status); 
		  List<String> TH14 = new ArrayList<String>();
		  TH14.add("Firing Grade"); 
		  TH14.add("Firing Event Qe"); 
		  TH14.add("Year");
		  //----------BATTLE AND PHYSICAL CASULTY 
		  List<Map<String, Object>> listBA=  UJD.JCOBA_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status); 
		  List<String> TH15 = new ArrayList<String>();
		  TH15.add("Classification of Casualty");
		  TH15.add("Nature of Casualty");
		  TH15.add("Name of Operation"); 
		  TH15.add("Date of Casualty");
		  TH15.add("Cause of Casualty"); 
		  TH15.add("Exact Place/Area/Post");
		  //------ADDRESS DETAILS - No TABLE
		  //---ORIGINAL DOMICILE OF 
		  List<Map<String, Object>> listORM= UJD.JCOORM_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		  //----PRESENTLY DOMICILE OF 
		  List<Map<String, Object>> listPDO= UJD.JCOPDO_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		  //------PERMANENT ADDRESS
		  List<Map<String, Object>> listPM=	UJD.JCOPM_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status);
		  //---------PRESENT ADDRESS
		  List<Map<String, Object>> listPS= UJD.JCOPS_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		  //-----NOK DETAILS 
		  List<Map<String, Object>> listNOK=UJD.JCONOK_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo,  comm_status);
		  ///-----NOK ADDRESS 
		  List<Map<String, Object>> listNA= UJD.JCONA_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo, comm_status);
		  //---------ADDRESS RETIREMENT 
		  List<Map<String, Object>> listAR= UJD.AR_View_JCOByid(Integer.parseInt(jco_id),jco_id,roleSusNo,comm_status);
		List<Map<String, Object>> listRD= UJD.JCORD_View_UpadteByid(Integer.parseInt(jco_id),jco_id,roleSusNo,comm_status);
			 String Heading = "\nRecord Service";
			 String foot = " Report Generated on "+ new SimpleDateFormat("dd-MM-YYYY").format(new Date());
			 return new ModelAndView(new Download_Record_ServiceJCO("L",Heading,username,foot,TH,TH1,TH2,TH3,TH4,TH5,TH6,TH7,TH8,TH9,TH10,TH11,TH12,TH13,TH14,TH15,
					 listSH,listFM,listS,listCHILD,listAM,listTENU,listRegim,listFIS,listARM,listPE,listAQ,listPTQ,listIL,listFL,
					 listF,listB,listFS,listBA,listORM,listPDO,listPM,listPS,listNOK,listNA,listTENU_T,listAR, peace_month,listRD,serving_status),"userList",listp);
	       }
	
	

	
	@RequestMapping(value = "/GetSearch_JCOrecordCount", method = RequestMethod.POST)
	public @ResponseBody long GetSearch_JCOrecordCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String unit_name,String unit_sus_no,String army_no,String full_name,
			String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		
		return UJD.GetSearch_JCOrecordCountlist(Search, orderColunm, orderType, sessionUserId, unit_name, unit_sus_no, army_no,
				full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA, roleSusNo, roleType);
	}
	
		
	
	@RequestMapping(value = "/GetSearch_JCOrecorddata", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetSearch_JCOrecorddata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String unit_name,String unit_sus_no,String army_no,String full_name,
			String rank,String year_of_comm, String year_of_dob, String parent_arm, String statusA) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		return UJD.GetSearch_JCOrecorddata(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_name, unit_sus_no, army_no,
				full_name, rank,  year_of_comm,  year_of_dob,  parent_arm, statusA, roleSusNo, roleType);
	}
	
}
