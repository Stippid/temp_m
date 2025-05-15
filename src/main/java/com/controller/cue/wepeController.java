package com.controller.cue;

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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;

import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.cue.cueStandardEntitlementTransportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
//import com.itextpdf.text.log.SysoCounter;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class wepeController {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private Cue_wepe_conditionDAO masterDAO;
	@Autowired
	RoleBaseMenuDAO roledao;
	@Autowired
	private cueStandardEntitlementTransportDAO cueTransport;

	cueContoller M = new cueContoller();

	@RequestMapping(value = "/searchWePecondition_Pers", method = RequestMethod.GET)
	public ModelAndView searchWePecondition_Pers(String subModule3, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Boolean val = roledao.ScreenRedirect("searchWePecondition_Pers", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
	    if(roleAccess.equals("Line_dte")){	
				
				Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectLine_dte",select);
			    Mmap.put("selectArmNameList",select);
				Mmap.put("getArmNameList", M.getArmNameList());
				 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
			}
		Mmap.put("msg", msg);
		Mmap.put("wepe", "1"); // for PERSONNEL value is 1
		Mmap.put("roleid", roleid);
	//	Mmap.put("getArmNameList", M.getArmNameList());
	//	Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWePeconditionTile");
	}

	@RequestMapping(value = "/searchWePecondition_Wea", method = RequestMethod.GET)
	public ModelAndView searchWePecondition_Wea(String subModule3, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchWePecondition_Wea", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
		   String roleAccess = session.getAttribute("roleAccess").toString();
            if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("wepe", "3"); // for WEAPON value is 3
		Mmap.put("roleid", roleid);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWePeconditionTile");
	}

	@RequestMapping(value = "/searchWePecondition_Trans", method = RequestMethod.GET)
	public ModelAndView searchWePecondition_Trans(String subModule3, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Boolean val = roledao.ScreenRedirect("searchWePecondition_Trans", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		 
           if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("msg", msg);
		Mmap.put("wepe", "2"); // for TRANSPORT value is 2
		Mmap.put("roleid", roleid);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWePeconditionTile");
	}

	@RequestMapping(value = "/admin/searchWePecondition1", method = RequestMethod.POST)
	public ModelAndView searchWePecondition1(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "uploaded_wepe1", required = false) String uploaded_wepe,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "arm_desc1", required = false) String arm,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "getroleid1", required = false) String getroleid,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "setTypeOnclick1", required = false) String setTypeOnclick) {
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("wepe", setTypeOnclick);
		Mmap.put("we_pe01", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("status1", status);
		Mmap.put("uploaded_wepe1", uploaded_wepe);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("getroleid1", getroleid);

		List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition(we_pe, we_pe_no, uploaded_wepe,
				sponsor_dire, arm, doc_type, getroleid, status, roleType,roleAccess);
		
		  String roleSusNo = session.getAttribute("roleSusNo").toString();
           if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
		    Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
//		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWePeconditionTile");
	}

	@RequestMapping(value = "/admin/uploadWePecondition1", method = RequestMethod.POST)
	public ModelAndView uploadWePecondition1(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "uploaded_wepe1", required = false) String uploaded_wepe,
			@RequestParam(value = "we_pe01", required = false) String we_pe,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire1", required = false) String sponsor_dire,
			@RequestParam(value = "arm_desc1", required = false) String arm,
			@RequestParam(value = "wepetype1", required = false) String getTypeOnclick,
			@RequestParam(value = "doc_type1", required = false) String doc_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "setTypeOnclick1", required = false) String setTypeOnclick) {
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("wepe", setTypeOnclick);
		Mmap.put("we_pe01", we_pe);
		Mmap.put("status1", status);
		Mmap.put("uploaded_wepe1", uploaded_wepe);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm_desc1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("wepetype1", getTypeOnclick);
		List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition1(uploaded_wepe, we_pe_no, we_pe,
				sponsor_dire, arm, doc_type, status, setTypeOnclick);
		Mmap.put("list", list);
		Mmap.put("listsize", list.size());
		Mmap.put("roleType", roleType);
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
//		if (roleAccess == "Line_dte" && roleSubAccess == "Arm") {
//			Mmap.put("getArmNameList", M.getArmNameListParameter(roleArmCode));
//		} else {
//			Mmap.put("getArmNameList", M.getArmNameList());
//		}
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();		
	
	 if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
			Mmap.put("getArmNameList",M.getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", M.getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("upload_WE_PE_Super_Tiles");
	}

	@RequestMapping(value = "/update_WE_PE_conditionUrl", method = RequestMethod.POST)
	public ModelAndView update_WE_PE_conditionUrl(@ModelAttribute("updateType") String updateType,
			@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("WePeconditionEditCMD", masterDAO.getcue_wepeByid(updateid));
		model.put("wepetypesearch", updateType);
		//model.put("getsponserListCue", M.getsponserListCue());
		
		String roleSubAccess = sessionEdit.getAttribute("roleSubAccess").toString();
		String roleArmCode = sessionEdit.getAttribute("roleArmCode").toString();
		String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();		
		if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
			model.put("getArmNameList",M.getArmNameLine(roleSusNo));
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			model.put("selectLine_dte",select);
			model.put("selectArmNameList",select);
			model.put("getArmNameList", M.getArmNameList());
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		return new ModelAndView("Editupload_WE_PE_super_Tiles");
	}

	@RequestMapping(value = "/WePeconditionEditAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView WePeconditionEditAction(@ModelAttribute("WePeconditionEditCMD") CUE_TB_MISO_WEPECONDITIONS updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		String wepetypesearch = request.getParameter("getTypeOnclick");
		String val = request.getParameter("training_capacity");
		int tra_val = 0;
	//	String arm_val = "";

//		if (sessionEdit.getAttribute("roleAccess").equals("Line_dte")
//				&& sessionEdit.getAttribute("roleSubAccess").equals("Arm")) {
//			arm_val = request.getParameter("arm1");
//		} else {
		String	arm_val = request.getParameter("arm_sel");
		//}
		if (!val.isEmpty() && !val.equals(null) && !val.equals("null") && !val.equals(""))
			tra_val = updateid.getTraining_capacity();
		String username = session.getAttribute("username").toString();
		updateid.setTraining_capacity(tra_val);
		masterDAO.UpdateArtAttValue(updateid, username, arm_val);
		model.put("msg", "Updated Successfully");

		if (wepetypesearch.equals("1")) { // for PERSONNEL value is 1
			return new ModelAndView("redirect:upload_WE_PE_Super_Pers?wepetypesearch=" + wepetypesearch);
		}
		if (wepetypesearch.equals("3")) { // for WEAPON value is 3
			return new ModelAndView("redirect:upload_WE_PE_Super_Wea?wepetypesearch=" + wepetypesearch);
		}
		if (wepetypesearch.equals("2")) { // for TRANSPORT value is 2
			return new ModelAndView("redirect:upload_WE_PE_Super_Trans?wepetypesearch=" + wepetypesearch);
		} else {
			return new ModelAndView("");
		}

	}

	@RequestMapping(value = "/updaterejectaction", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectaction(HttpSession session,String remarks, String letter_no, int id) {
		System.err.println("Inside updaterejectaction Method wepecontroller.java");
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers1("cue_tb_miso_wepeconditions", remarks, id,
				letter_no,username);
		return list2;
	}

	@RequestMapping(value = "/delete_WE_PE_conditionUrlAdd", method = RequestMethod.POST)
	public @ResponseBody String delete_WE_PE_conditionUrlAdd(int deleteid,String superno) {
	String list2 = "";
		if (masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_WEAPON_DET") && masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_WEAPONS_MDFS") && masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES")
				&& masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_TRANSPORT_DET")&& masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_TRANS_FOOTNOTES")&& masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_TRANSPORT_MDFS")
				&& masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_PERS_DET") && masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_PERS_MDFS")&& masterDAO.check_data(superno,"CUE_TB_MISO_WEPE_PERS_FOOTNOTES")) {
			list2= masterDAO.setDeleteARM(deleteid);
		}
		else {
			list2 ="Can not delete this data. \n Pending copied data in STANDARD AUTHORISATION, INCREASE/DECREASE GENERAL NOTES, or SEARCH STANDARD AUTHORISATION screen. ";
		}
	
		return list2;
	}

	

	@RequestMapping(value = "/admin/Approved_WE_PE_condition", method = RequestMethod.POST)
	public ModelAndView Approved_WE_PE_Url(@ModelAttribute("appType") String appType, HttpServletRequest request,
			@ModelAttribute("appid") int appid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, @RequestParam(value = "uploaded_wepe2", required = false) String uploaded_wepe,
			@RequestParam(value = "we_pe02", required = false) String we_pe,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "sponsor_dire2", required = false) String sponsor_dire,
			@RequestParam(value = "arm2", required = false) String arm,
			@RequestParam(value = "arm_desc2", required = false) String arm_desc,
			@RequestParam(value = "doc_type2", required = false) String doc_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "suprcdd_we_pe_no2", required = false) String suprcdd_we_pe_no,
			@RequestParam(value = "getroleid2", required = false) String getroleid,
			@RequestParam(value = "table_title2", required = false) String table_title,
			@RequestParam(value = "setTypeOnclick2", required = false) String setTypeOnclick,
			@RequestParam(value = "superno2", required = false) String superno,
			@RequestParam(value = "nwepe2", required = false) String nwepe,
			@RequestParam(value = "type2", required = false) String type,
			@RequestParam(value = "copy2", required = false) String copy,
			@RequestParam(value = "statusp", required = false) String Pstatus	){
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();

		Mmap.put("wepe", setTypeOnclick);
		int roleid = (Integer) session.getAttribute("roleid");
		String username = session.getAttribute("username").toString();
		if(!superno.equals(""))
		{
			if(copy.equals("yes"))
			{
			Mmap.put("msg", masterDAO.updatecapdatawepe(we_pe_no,superno,roleid, username, roleType,nwepe,type,Pstatus));
		}
			}
		
		Mmap.put("msg", masterDAO.setApprovedwepecondition(appid, roleid, username, roleType, setTypeOnclick));
		Mmap.put("we_pe01", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("status1", status);
		Mmap.put("uploaded_wepe1", uploaded_wepe);
		Mmap.put("sponsor_dire1", sponsor_dire);
		Mmap.put("arm1", arm);
		Mmap.put("doc_type1", doc_type);
		Mmap.put("getroleid1", getroleid);
		List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition(we_pe, we_pe_no, uploaded_wepe,
				sponsor_dire, arm, doc_type, getroleid, status, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("searchWePeconditionTile");
	}

	////////////// zankar///////////

	@RequestMapping(value = "/getsupercddList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getsupercddList(String we_r, HttpSession sessionUserId,
			String we_pe_no)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte") ){
			qry = "select distinct we_pe_no from cue_wepe WHERE we_pe_no is not null and we_pe_no NOT IN (SELECT suprcdd_we_pe_no FROM cue_wepe WHERE suprcdd_we_pe_no IS NOT NULL) "
					+ "and we_pe =:we_r and status='1' and upper(we_pe_no) like:we_pe_no and sponsor_dire=:roleSusNo order by we_pe_no";
			}
			else {
				qry = "select distinct we_pe_no from cue_wepe WHERE  we_pe_no is not null and we_pe_no NOT IN (SELECT suprcdd_we_pe_no FROM cue_wepe WHERE suprcdd_we_pe_no IS NOT NULL) "
						+ "and we_pe =:we_r and status='1' and upper(we_pe_no) like:we_pe_no order by we_pe_no";
			}
		
		Query q = session.createQuery(qry).setMaxResults(10);
		q.setParameter("we_r", we_r);
		
		q.setParameter("we_pe_no", we_pe_no.toUpperCase() + "%");
		
		if( sessionUserId.getAttribute("roleAccess").toString().equals("Line_dte")){
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId, list1);
	}

	@RequestMapping(value = "/add_moreUrl", method = RequestMethod.POST)
	public ModelAndView add_moreUrl(@ModelAttribute("add_moreType") String add_moreType, HttpServletRequest request, HttpSession session, 
			@ModelAttribute("add_moreid") int add_moreid, ModelMap Mmap,
			@RequestParam(value = "we_pe_no01", required = false) String we_pe_no,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		Mmap.put("WePeconditionAddMoreCMD", masterDAO.getcue_wepeByid(add_moreid));
		Mmap.put("add_moreType", add_moreType);
		Mmap.put("we_pe_no01", we_pe_no);
		
		String roleType = session.getAttribute("roleType").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();

		List<Map<String, Object>> list = cueTransport.getViewTransEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list2 = cueTransport.getViewStdlnkTransModEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list3 = cueTransport.getViewStdlnkTransfotnoteEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list4 = cueTransport.getViewWeaponEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list6 = cueTransport.getViewStdlnkWeaponModEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list7 = cueTransport.getViewStdlnkWeaponfotnoteEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list9 = cueTransport.getViewPersonEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list11 = cueTransport.getViewStdlnkPersonModEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list12 = cueTransport.getViewStdlnkPersonfotnoteEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);
		List<Map<String, Object>> list13 = cueTransport.getViewStdlnkTransInlieuEntitlementDtladdMore(we_pe_no,"",roleType,roleAccess);

		Mmap.put("list", list);
		Mmap.put("list2", list2);
		Mmap.put("list3", list3);
		Mmap.put("list4", list4);
		Mmap.put("list6", list6);
		Mmap.put("list7", list7);
		Mmap.put("list9", list9);
		Mmap.put("list11", list11);
		Mmap.put("list12", list12);
		Mmap.put("list13", list13);
		Mmap.put("list.size()", list.size());
		Mmap.put("list2.size()", list2.size());
		Mmap.put("list3.size()", list3.size());
		Mmap.put("list4.size()", list4.size());
		Mmap.put("list6.size()", list6.size());
		Mmap.put("list7.size()", list7.size());
		Mmap.put("list9.size()", list9.size());
		Mmap.put("list11.size()", list11.size());
		Mmap.put("list12.size()", list12.size());
		Mmap.put("list13.size()", list13.size());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getsponserListCue", M.getsponserListCue());
		return new ModelAndView("add_moreTiles");
	}

}
