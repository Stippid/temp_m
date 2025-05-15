package com.controller.psg.Commissioning;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Change_Of_Commision_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_CADET;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COURSE;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_GENDER;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_UNIT;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_UpdateComm_MnsController {
	
	@Autowired
	Search_UpdateComm_Dao scldao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	//CommanController m = new CommanController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();
	Update_Comm_Controller ucc = new Update_Comm_Controller();
	Change_Of_Commision_Controller coc = new Change_Of_Commision_Controller();
	PsgDashboardController das = new PsgDashboardController();
	BigInteger u_id = BigInteger.ZERO;
	
	
	
	@RequestMapping(value = "/admin/Search_S_C_L_MnsUrl", method = RequestMethod.GET)
	public ModelAndView Search_S_C_L_MnsUrl(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleType = session.getAttribute("roleType").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
//		 Boolean val = roledao.ScreenRedirect("Search_S_C_L_MnsUrl", roleid);
//		 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//				return new ModelAndView("redirect:bodyParameterNotAllow");
//			}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("list", scldao.Search_S_C_LData("", "0", "", "", "", "","",roleSusNo, roleType,roleAccess,true));
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		
		
		return new ModelAndView("Search_S_C_L_MnsTiles");
	}

	@RequestMapping(value = "/admin/GetS_C_LDataMns", method = RequestMethod.POST)
	public ModelAndView GetS_C_LDataMns(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
	        @RequestParam(value = "cr_date1", required = false) String cr_date
	      ){
		
		 boolean IsMns=true;
	        unit_name = unit_name.replace("&#40;", "(");
	        unit_name = unit_name.replace("&#41;", ")");
	        String roleType = session.getAttribute("roleType").toString();
	        String roleSusNo = session.getAttribute("roleSusNo").toString();
	        String roleAccess = session.getAttribute("roleAccess").toString();
	        
	        if(unit_sus_no!="") {
		    	  if (!valid.SusNoLength(unit_sus_no)) {
						Mmap.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
					}
		    	  
		    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
						return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
					}
		      }
			
	      if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
		        Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session) .get(0));
			}
	      
		 if(unit_name!="") {
			  if (!valid.isUnit(unit_name)) {
				  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
//				}
	      }
		 
		 if(personnel_no!="") {
			   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
				}
			  
			      if (personnel_no.length() < 7 || personnel_no.length() > 9) {
						Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
						return new ModelAndView("redirect:Search_S_C_L_MnsUrl");
					}
		     } 
		
		 ArrayList<ArrayList<String>> list = scldao.Search_S_C_LData(personnel_no,status,rank,unit_sus_no,unit_name,cr_by,cr_date,roleSusNo,roleType,roleAccess,IsMns );
	        Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("personnel_no1", personnel_no);
			Mmap.put("rank1", rank);
			Mmap.put("status1", status);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("unit_sus_no1", unit_sus_no);
			Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
			Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
            Mmap.put("cr_date1", cr_date);
			Mmap.put("cr_by1", cr_by);
		return new ModelAndView("Search_S_C_L_MnsTiles","SearchS_C_LCMD",new TB_TRANS_PROPOSED_COMM_LETTER());
	}
	
	
	
}
