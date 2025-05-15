package com.controller.XMLReader;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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

import com.controller.psg.Commissioning.Search_UpdateComm_Controller;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.popup_history.Change_Appointment_History_DAO;
import com.dao.psg.xml.XMLDao;
import com.models.psg.Transaction.TB_CENSUS_ADDRESS;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_CENSUS_NOK;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.models.psg.update_census_data.TB_CENSUS_AWARDSNMEDAL;
import com.models.psg.update_census_data.TB_CENSUS_CDA_ACCOUNT_NO;
import com.models.psg.update_census_data.TB_CENSUS_CHILDREN;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_NON_EFFECTIVE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Edit_Xml_Controller {
	Search_UpdateComm_Controller  srch=new Search_UpdateComm_Controller();
	ValidationController valid = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();

	
	@Autowired
	private Change_Appointment_History_DAO changeAppointment;
	
	@Autowired
	Psg_CommanDAO psg_com;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	XMLDao xml_dao;
	@RequestMapping(value = "/admin/Edit_xml", method = RequestMethod.GET)
	public ModelAndView Edit_xml(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("Edit_XML_DataTiles");
	}
	@RequestMapping(value = "/admin/XML_Update_Data", method = RequestMethod.POST)
	public ModelAndView XML_Update_Data(ModelMap Mmap, HttpSession sessionA,String personnel_no_edit,String  comm_id_edit,String census_id_edit 
			,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) throws ParseException {
		Mmap.addAttribute("personnel_no_edit", personnel_no_edit);
		Mmap.addAttribute("comm_id_edit", comm_id_edit);
		Mmap.addAttribute("census_id_edit", census_id_edit);
		String comm_id=comm_id_edit;
		List<TB_CHANGE_OF_RANK> ChangeOfrank= srch.getrankData(new BigInteger(comm_id));
		List<TB_CHANGE_NAME> ChangeOfName= ChangeOfNameData_xml(new BigInteger(comm_id));
		List<TB_CHANGE_OF_APPOINTMENT> ChangeOfAppointment= get_Appointment_xml(new BigInteger(comm_id));
		List<TB_CENSUS_MEDICAL_CATEGORY> ChangeOfMedCat= get_medicaldata_xml(new BigInteger(comm_id));
		List<TB_CDA_ACC_NO> ChangeOfCDA=cda_GetData_xml (new BigInteger(comm_id));
		List<TB_CENSUS_AWARDSNMEDAL> ChangeOfmedal=getawardsNmedalData_xml (new BigInteger(comm_id));
		List<TB_CENSUS_NOK> ChangeOfNOK=nok_details_GetData_xml(new BigInteger(comm_id));
		List<TB_CENSUS_CHILDREN> ChangeOfChildDetails=getm_children_detailsData1_xml(new BigInteger(comm_id));
		List<TB_CENSUS_CDA_ACCOUNT_NO> ChangeOfContact=cda_acnt_no_GetData1(new BigInteger(comm_id));		
		List<TB_CENSUS_FAMILY_MRG> ChangeOfMarital=update_getfamily_marriageData1_xml(new BigInteger(comm_id));	
		List<TB_NON_EFFECTIVE> ChangeOfNoneffective=getNon_effective1_xml(new BigInteger(comm_id));	
		List<TB_POSTING_IN_OUT> ChangeOfPosting= getposting_in_data1_xml(new BigInteger(comm_id));
		
		
		
		
		
		Mmap.put("ChangeOfrank", ChangeOfrank.size());
		Mmap.put("ChangeOfName", ChangeOfName.size());
		Mmap.put("ChangeOfAppointment", ChangeOfAppointment.size());
		Mmap.put("ChangeOfMedCat", ChangeOfMedCat.size());
		Mmap.put("ChangeOfCDA", ChangeOfCDA.size());
		Mmap.put("ChangeOfmedal", ChangeOfmedal.size());
		Mmap.put("ChangeOfNOK", ChangeOfNOK.size());
		Mmap.put("ChangeOfNOK", ChangeOfNOK.size());
		Mmap.put("ChangeOfChildDetails", ChangeOfChildDetails.size());
		Mmap.put("ChangeOfContact", ChangeOfContact.size());
		Mmap.put("ChangeOfMarital", ChangeOfMarital.size());
		Mmap.put("ChangeOfNoneffective", ChangeOfNoneffective.size());
		Mmap.put("ChangeOfPosting", ChangeOfPosting.size());
		
		Mmap.put("getRelationList", mcommon.getRelationList());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getSeconded_To", mcommon.getSeconded_To());
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getNon_EffectiveList", mcommon.getNon_EffectiveList());
		Mmap.put("getLanguageList", mcommon.getLanguageList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());
		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", sessionA));
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getForeign_country", psg_com.getforiegnCountry());
		Mmap.put("getForiegnCountryList", mcommon.getForeign_country());
		Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("1"));
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getSpecializationList", mcommon.getSpecializationList());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());
		Mmap.put("getDivclass", mcommon.getDivclass());
		Mmap.put("getSubject", mcommon.getSubject());
		Mmap.put("getExamination", mcommon.getExamination());
		// Mmap.put("getCommandDetailsList", m.getCommandDetailsList());
		Mmap.put("getMedalList", mcommon.getMedalList());
		Mmap.put("getTypeofAppointementList", mcommon.getTypeofAppointementList());
		Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());
		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
		// Mmap.put("getVillageList", mcommon.getVillageList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getFiring_event_qe", mcommon.getFiring_event_qe());
		Mmap.put("getBPET_event_qe", mcommon.getBPET_event_qe());
		Mmap.put("getFiring_grade", mcommon.getFiring_grade());
		Mmap.put("getBPET_result", mcommon.getBPET_result());
		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());
		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());
		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		// Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
		// Mmap.put("getMedCityName", mcommon.getMedCityName("", session));
		// Mmap.put("getCountryList", mcommon.getCountryList());
		// Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());
		Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());
		Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());
		Mmap.put("getOFFR_Non_EffectiveList", mcommon.getOFFR_Non_EffectiveList(""));
		Mmap.put("getPSG_CommandList", psg_com.getPSG_CommandList());
		Mmap.put("getPSG_CorpsList", psg_com.getPSG_CorpsList());
		Mmap.put("getPSG_DivList", psg_com.getPSG_DivList());
		Mmap.put("getPSG_BdeList", psg_com.getPSG_BdeList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getOFFTypeofRankList", mcommon.getOFFTypeofRankList());
		Mmap.put("getChild_RelationshipList", mcommon.getChild_RelationshipList());
		Mmap.put("getChild_TypeList", mcommon.getChild_TypeList());
		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
		Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));
		Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));
		Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));
		Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));
		Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));
		Mmap.put("getOprationList", mcommon.getOprationList());
		Mmap.put("getDivclass", mcommon.getDivclass());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getExaminationTypeList", mcommon.getExaminationTypeList());
		// Mmap.put("getStreamList", mcommon.getStreamList());
		Mmap.put("getCourseName", mcommon.getCourseName());
		Mmap.put("getFdService", mcommon.getFdService());
		Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
		Mmap.put("getExamList", mcommon.getExamList());
		Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
		Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
		Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
		Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
		Mmap.put("getExservicemenList", mcommon.getExservicemenList());
		Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());
		Mmap.put("getMissingList", mcommon.getMissingList());
		Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
		// -----smit--------//
		Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());
		Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());
		Mmap.put("getSub_Clause", mcommon.getSub_Clause());
		Mmap.put("gettastatusList", mcommon.getstatusList());

		return new ModelAndView("Edit_XML_DataTiles");
	}
	
	
	@RequestMapping(value = "/admin/address_details_GetData_xml", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_ADDRESS> address_details_GetData_xml( HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

	

		String hqlUpdate = " from TB_CENSUS_ADDRESS where comm_id=:comm_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_ADDRESS> list = (List<TB_CENSUS_ADDRESS>) query.list();

		tx.commit();

		sessionHQL.close();

		

		return list;

	}

	
	
	
	@RequestMapping(value = "/admin/Non_Effective_Status_DetailsUrl_xml", method = RequestMethod.POST)
	 public ModelAndView Non_Effective_Status_DetailsUrl_xml(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "nes_comm_id", required = false) BigInteger nes_comm_id,
			 @RequestParam(value = "nes_census_id", required = false) String nes_census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Mmap.put("list",xml_dao.update_non_effective_status_details_xml(nes_comm_id));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Non_Effective_Status_DetailsTiles");
		
	 }
	
	@RequestMapping(value = "/admin/getNon_effective_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_NON_EFFECTIVE> getNon_effective_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		int id = Integer.parseInt(request.getParameter("p_id"));
		/// bisag 241122 V2 (change comm_id int to big int)
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_NON_EFFECTIVE where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE> list = (List<TB_NON_EFFECTIVE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public @ResponseBody List<TB_NON_EFFECTIVE> getNon_effective1_xml( BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_NON_EFFECTIVE where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_NON_EFFECTIVE> list = (List<TB_NON_EFFECTIVE>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public @ResponseBody List<TB_POSTING_IN_OUT> getposting_in_data1_xml( BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_POSTING_IN_OUT where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_POSTING_IN_OUT> list = (List<TB_POSTING_IN_OUT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/admin/update_getfamily_marriageData_xml", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData_xml(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

//		int id = Integer.parseInt(request.getParameter("p_id"));

		int event = Integer.parseInt(request.getParameter("marital_event"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		List<TB_CENSUS_FAMILY_MRG> list = null;

		boolean flag = true;

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where  comm_id=:comm_id";

		if (event != 0) {

			String hqlUpdate1 = "from TB_CENSUS_FAMILY_MRG where  comm_id=:comm_id and status=0 and type_of_event=:type_of_event";

			Query query = sessionHQL.createQuery(hqlUpdate1).setBigInteger("comm_id", comm_id)

					.setInteger("type_of_event", event);

			list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

			if (list.size() > 0) {

				flag = false;

			} else {

				hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13)";

			}

		} else {

			hqlUpdate += " and status=0 ";

		}

		if (flag) {

			Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);

			list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

		}

		tx.commit();

		sessionHQL.close();

		return list;

	}

	/*--------------------- For Approval ----------------------------------*/
	
	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData1_xml(BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id";

		hqlUpdate += " and status=0 ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/Change_In_Contact_DetailsUrl_XML", method = RequestMethod.POST)
	 public ModelAndView Change_In_Contact_DetailsUrl_XML(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "cd_comm_id", required = false) BigInteger cd_comm_id
			 ) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			List<ArrayList<String>> list = xml_dao.change_Contact_xml(cd_comm_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("Change_Contact_Details_History_Tiles");
	 }
	
	@RequestMapping(value = "/admin/cda_acnt_no_GetData_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
	
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where  comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();

		
		return list;
	}
	public @ResponseBody List<TB_CENSUS_CDA_ACCOUNT_NO> cda_acnt_no_GetData1(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_CDA_ACCOUNT_NO where comm_id=:comm_id and status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_CDA_ACCOUNT_NO> list = (List<TB_CENSUS_CDA_ACCOUNT_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	

	// ************************************END CDA ACCOUNT NO & CONTACT DETAIL*******************************************
	
	
	

	
	
	
	
	
	
	
 @RequestMapping(value = "/admin/Update_Child_dtlUrl_XML", method = RequestMethod.POST)
 public ModelAndView Update_Child_dtlUrl_XML(ModelMap Mmap, HttpSession session,
		 @RequestParam(value = "msg", required = false) String msg,
		 @RequestParam(value = "uc_comm_id", required = false) BigInteger uc_comm_id,
		 HttpServletRequest request) {
	 
	 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
        Mmap.put("list", xml_dao.update_child_dtl_XML(uc_comm_id));
		 Mmap.put("msg", msg);
		 return new ModelAndView("Update_Child_Details_Tiles");
	
 }

	
	public @ResponseBody List<TB_CENSUS_CHILDREN> getm_children_detailsData1_xml(BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_CHILDREN where  status='0' and comm_id=:comm_id order by id";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}
	
	@RequestMapping(value = "/admin/getm_children_detailsData_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_CHILDREN> getm_children_detailsData_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_CHILDREN where  status = '0' and comm_id=:comm_id order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_CHILDREN> list = (List<TB_CENSUS_CHILDREN>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	 @RequestMapping(value = "/Change_NOKUrl_xml", method = RequestMethod.POST)
	 public ModelAndView Change_NOKUrl_xml(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "nok_comm_id", required = false) BigInteger comm_id,
			 @RequestParam(value = "nok_census_id", required = false) String census_id) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			  List<ArrayList<String>> list = xml_dao.change_nok_history_dtl_xml(comm_id);
			  Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		return new ModelAndView("Change_Nok_History_Tiles");
	 }
	
	
	
	
	@RequestMapping(value = "/admin/nok_details_GetData_xml", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_NOK> nok_details_GetData_xml(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_NOK where  comm_id=:comm_id and  status = '0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_NOK> list = (List<TB_CENSUS_NOK>) query.list();
		tx.commit();
		sessionHQL.close();

		return list;

	}

	public @ResponseBody List<TB_CENSUS_NOK> nok_details_GetData_xml( BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_NOK where  comm_id=:comm_id and  status = '0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_NOK> list = (List<TB_CENSUS_NOK>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/admin/getawardsNmedalData_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory(). openSession();
		Transaction tx = sessionHQL.beginTransaction();
		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where  comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	@RequestMapping(value = "/admin/Update_Awards_MedalUrl_xml", method = RequestMethod.POST)
	 public ModelAndView Update_Awards_MedalUrl_xml(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "am_comm_id", required = false) BigInteger am_comm_id,
			 HttpServletRequest request) {
		 
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		Mmap.put("list", xml_dao.update_award_medal_history_xml(am_comm_id));
			 Mmap.put("msg", msg);
			 return new ModelAndView("Update_Awards_Medal_Tiles");
		
	 }
	
	public @ResponseBody List<TB_CENSUS_AWARDSNMEDAL> getawardsNmedalData_xml(BigInteger comm_id){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_AWARDSNMEDAL where  status='0' and comm_id=:comm_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_AWARDSNMEDAL> list = (List<TB_CENSUS_AWARDSNMEDAL>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}
	
	
	
	
	
	
	 @RequestMapping(value = "/Change_In_Appointment_Url_xml", method = RequestMethod.POST)
	 public ModelAndView Change_In_Appointment_Url_xml(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			 @RequestParam(value = "appointment_comm_id", required = false) BigInteger appointment_comm_id
			 ) {
		
		 Boolean val = roledao.ScreenRedirect("Update_Off_DataUrl", session.getAttribute("roleid").toString());
	        if(val == false) {
	                return new ModelAndView("AccessTiles");
	        }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<ArrayList<String>> list = changeAppointment.change_Appointment_xml(appointment_comm_id);
			 Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		
		return new ModelAndView("appointment_history_tiles");
	 }
	
	@RequestMapping(value = "/admin/cda_GetData_XML", method = RequestMethod.POST)
	public @ResponseBody List<TB_CDA_ACC_NO> cda_GetData_XML(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String status=request.getParameter("status");
		String hqlUpdate = " from TB_CDA_ACC_NO where  comm_id=:comm_id and status="+status+" order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CDA_ACC_NO> list = (List<TB_CDA_ACC_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
//	@RequestMapping(value = "/admin/cda_GetData", method = RequestMethod.POST)
	public @ResponseBody List<TB_CDA_ACC_NO> cda_GetData_xml(BigInteger comm_id)
			throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CDA_ACC_NO where  comm_id=:comm_id and status=0 order by id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CDA_ACC_NO> list = (List<TB_CDA_ACC_NO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}

	
	@RequestMapping(value = "/admin/change_of_name_GetData_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_NAME> change_of_name_GetData_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));
		String hqlUpdate = " from TB_CHANGE_NAME where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/get_Appointment_xml", method = RequestMethod.POST)
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		//int id = Integer.parseInt(request.getParameter("p_id"));
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where  comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	
	@RequestMapping(value = "/admin/madical_cat_GetData_xml", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> madical_cat_GetData_xml(ModelMap Mmap, HttpSession session,
			HttpServletRequest request)  {
		String msg = "";	
		String Shape = request.getParameter("Shape");	
		BigInteger p_id =new BigInteger(request.getParameter("p_id"));
		String approoval_status=request.getParameter("app_status");
		if(approoval_status!=null && !approoval_status.equals("")  && approoval_status.equals("1")) {
			return xml_dao.getShapeData_xml(Shape,p_id,1);
		}
		else if(approoval_status!=null && !approoval_status.equals("")  && approoval_status.equals("3")) {
			return xml_dao.getShapeData_xml(Shape,p_id,3);
		}
		else {
			
			return xml_dao.getShapeData_xml(Shape,p_id,0);
		}
	}
public @ResponseBody List<TB_CENSUS_MEDICAL_CATEGORY> get_medicaldata_xml(BigInteger comm_id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CENSUS_MEDICAL_CATEGORY where  comm_id=:comm_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_MEDICAL_CATEGORY> list = (List<TB_CENSUS_MEDICAL_CATEGORY>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	
	public @ResponseBody List<TB_CHANGE_OF_APPOINTMENT> get_Appointment_xml(BigInteger comm_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_OF_APPOINTMENT where  comm_id=:comm_id and status=0";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		List<TB_CHANGE_OF_APPOINTMENT> list = (List<TB_CHANGE_OF_APPOINTMENT>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public @ResponseBody List<TB_CHANGE_NAME> ChangeOfNameData_xml(BigInteger comm_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_CHANGE_NAME where  status='0' and comm_id=:comm_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CHANGE_NAME> list = (List<TB_CHANGE_NAME>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	@RequestMapping(value = "/admin/GetPosting_in_data_xml", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetPosting_in_data_xml(BigInteger comm_id ,String status) {
		List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
		data=xml_dao.getPost_in_data(comm_id,1,Integer.parseInt(status));
		return data;
	}
	@RequestMapping(value = "/admin/Edit_posting_in_Action_xml", method = RequestMethod.POST)
	public  @ResponseBody String Edit_posting_in_Action_xml(HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {
		String msg="";
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String from_sus_no = request.getParameter("from_sus_no");
			Date dt_of_tos1 = null;
			String dt_of_tos = request.getParameter("dt_of_tos");
			String dt_tos_pre = request.getParameter("dt_tos_pre");
			
			if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {
			msg="Please Enter Date of TOS";
//				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			if (!valid.isValidDate(dt_of_tos)) {
			msg=valid.isValidDateMSG + " of TOS";
			
			}
			if (dt_of_tos != "") {
				dt_of_tos1 = format.parse(dt_of_tos);
			}
			if (dt_of_tos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_tos1;
			        if (selectedDate.after(currentDate)) {
			       msg="Future dates are not allowed";
			        	//return new ModelAndView("redirect:Search_Posting_OutUrl");
			    }		   
			}
			if(dt_of_tos!=null && dt_tos_pre!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_tos");
				String preSos=dt_tos_pre;
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("/");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[2]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){
						msg="Invalid Date of TOS";
					//	return new ModelAndView("redirect:Search_Posting_OutUrl");
					}
				}
				else if(newSosY<preSosY){
				msg="Invalid Date of TOS";
					//return new ModelAndView("redirect:Search_Posting_OutUrl");
				}
			}
			int hh_id = Integer.parseInt(request.getParameter("update_id"));
			String rvalue = "";
			String hql = "";
	
				hql += "update TB_POSTING_IN_OUT";
		
			
			hql += "  set  dt_of_tos=:dt_of_tos,dt_of_sos=:dt_of_sos,status=0 where id=:h_id";
			Query query = sessionHQL.createQuery(hql)
					.setParameter("dt_of_tos", dt_of_tos1).setParameter("dt_of_sos", dt_of_tos1).setParameter("h_id", hh_id);
			rvalue = query.executeUpdate() > 0 ? "update" : "0";
			if(rvalue.equals("update"))
			{
				msg="Data Updated Successfully";
				
			}
			else {
				msg="Data Not Updated ";
			}
			tx.commit();
			sessionHQL.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
	@RequestMapping(value = "/getTargetSUSNoList_xml", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList_xml(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
			
		
				q = session.createQuery("select distinct sus_no from XML_FILE_UPLOAD where upper(sus_no) like :sus_no ").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	@RequestMapping(value = "/getTargetUnitNameList_xml", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitNameList_xml(String sus_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from  XML_FILE_UPLOAD where sus_no=:target_sus_no ");
		//26-01-1994
		q.setParameter("target_sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	
	@RequestMapping(value = "/getTargetUnitsNameActiveList_xml", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveList_xml(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
					q = session.createQuery("select distinct unit_name from  XML_FILE_UPLOAD where upper(unit_name) like :unit_name ").setMaxResults(10);
					q.setParameter("unit_name", unit_name.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;

	}
	
	
	@RequestMapping(value = "/getTargetSUSFromUNITNAME_xml", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSFromUNITNAME_xml(String unit_name, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery(
				"select distinct sus_no from XML_FILE_UPLOAD  where unit_name=:unit_name and status_sus_no='Active'");
		q.setParameter("unit_name", unit_name);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
}
