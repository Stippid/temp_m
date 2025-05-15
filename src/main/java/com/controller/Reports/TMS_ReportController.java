package com.controller.Reports;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.Reports.TMS_ReportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class TMS_ReportController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController validation = new ValidationController();
	
	@Autowired
	TMS_ReportDAO TMSReportDao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITSUrl", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITS(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITStiles");
	}

	@RequestMapping(value = "/1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITS_RP")
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITS_RP(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.ue_mms_hldngofbvehrprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("1_ALL_INDIA_HOLDING_OF_B_VEH_FOR_FIELD_NONFIELD_UNITStiles");
	}

	@RequestMapping(value = "/2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTSUrl", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTS(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTStiles");
	}

	@RequestMapping(value = "/2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTS_RP")
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTS_RP(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.ue_mms_hldngofbvehrprt1(from, to);
		
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("2_ALL_INDIA_HOLDING_OF_B_VEH_COMMAND_AND_DEPOTStiles");
	}

	@RequestMapping(value = "/3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMANDUrl", method = RequestMethod.GET)
	public ModelAndView FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMAND(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMANDUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMANDtiles");
	}

	@RequestMapping(value = "/3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMAND_RP")
	public ModelAndView FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMAND_RP(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMANDUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.ue_mms_bvehcmndwiserprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("3_FIT_OFF_ROAD_GS_B_VEHICLES_BY_COMMANDtiles");
	}

	@RequestMapping(value = "/4_ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Url", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Url(ModelMap Mmap,HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("4_ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2")
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2(ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao
				.ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITSDEPOT_2report(from, to);
	
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("4_ALL_INDIA_HOLDING_OF_B_VEHICLES_BY_CLASSIFICATION_WITH_ARMY_UNITS_DEPOT_2Tiles");
	}

	@RequestMapping(value = "/5_ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6URL", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6URL(ModelMap Mmap,HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6URL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("5_ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6Tiles");
	}

	@RequestMapping(value = "/ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6")
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6URL", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6_REPORT(from, to);
		
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("5_ALL_INDIA_HOLDING_OF_B_VEHS_COMMAND_AND_DEPOTS_Excl_Cl_5_6Tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019Url", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019Url(HttpServletRequest request,
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect(
				"AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019Url",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView(
				"AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019", method = RequestMethod.POST)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019(
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect(
				"AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019Url",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019report(from,
						to);
	
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_BY_SECOUND_THIRD_LINE_UNITS_STATE_AS_ON_31_MAR_2019tiles");
	}


	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019Url", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019Url(ModelMap Mmap,HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019Url",roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019reporttiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019report", method = RequestMethod.POST)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019report(ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019Url",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_STATE_AS_ON_31_MAR_2019reporttiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Url", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Url(HttpServletRequest request,
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Url",roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Urlreport", method = RequestMethod.POST)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Urlreport(
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019Url",roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019report(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_COMMANDWISE_EXCLUSIVELY_FOR_CAT_A_ESTB_STATE_AS_ON_31_MAR_2019tiles");
	}

	@RequestMapping(value = "/16_AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Url", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Url(HttpServletRequest request,
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("16_AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Url",roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("16_AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19", method = RequestMethod.POST)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19(
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect(
				"16_AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Url",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19_REPORT(from,to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("16_AUTHORISATION_AND_HOLDING_OF_EME_SPECIALIST_VEHICLES_WITH_ARMY_UNITS_STATE_AS_ON_31_MAR_19Tiles");
	}

	@RequestMapping(value = "/17_AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Url", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Url(HttpServletRequest request,
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect(
				"17_AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Url",
				roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView(
				"17_AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19")
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19(
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("17_AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Url",roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19_REPORT(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("17_AUTHORISATION_AND_HOLDING_OF_B_VEHICLES_WITH_ENGINEER_UNITS_BY_COMMANDS_STATE_AS_ON_31_MAR_19Tiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_COMMANDSUrl", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_COMMANDSUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_COMMANDSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_COMMANDStiles");
	}

	@RequestMapping(value = "/authorisation_and_holding_commandsrpt", method = RequestMethod.POST)
	public ModelAndView authorisation_and_holding_commandsrpt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_COMMANDSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.authorisation_and_holding_commandsrprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_COMMANDStiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITSUrl", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITSUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITStiles");
	}

	@RequestMapping(value = "/authorisation_and_holding_spl_veharmyrpt", method = RequestMethod.POST)
	public ModelAndView authorisation_and_holding_spl_veharmyrpt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> list = TMSReportDao.authorisation_and_holding_veharmyrprt(from,to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_SPL_VEHICLES_ARMY_UNITStiles");
	}

	@RequestMapping(value = "/AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITSUrl", method = RequestMethod.GET)
	public ModelAndView AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITSUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITStiles");
	}

	@RequestMapping(value = "/authorisation_and_holding_spl_veh_signalunitrpt", method = RequestMethod.POST)
	public ModelAndView authorisation_and_holding_spl_veh_signalunitrpt(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITSUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.authorisation_and_holding_vehunitrprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("AUTHORISATION_AND_HOLDING_OF_SPL_VEHS_WITH_SIGNAL_UNITStiles");
	}

	// 1
	
	@RequestMapping(value = "/1_PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS", method = RequestMethod.GET)
	public ModelAndView PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUStiles");
	}

	@RequestMapping(value = "/PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUSCURL", method = RequestMethod.POST)
	public ModelAndView PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUSCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao
				.PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUS_REPORT(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("PRF_WISE_ALL_INDIA_DEFI_SURPLUS_US_OF_B_VEHICLE_STATUStiles");
	}
	 
	// 1 End
	
	// 2
	
	@RequestMapping(value = "/2_ALL_INDIA_HOLDING_OF_B_VEH_POSN", method = RequestMethod.GET)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_POSN(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_ALL_INDIA_HOLDING_OF_B_VEH_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("ALL_INDIA_HOLDING_OF_B_VEH_POSNtiles");
	}

	@RequestMapping(value = "/ALL_INDIA_HOLDING_OF_B_VEH_POSNCURL", method = RequestMethod.POST)
	public ModelAndView ALL_INDIA_HOLDING_OF_B_VEH_POSNCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("2_ALL_INDIA_HOLDING_OF_B_VEH_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.ALL_INDIA_HOLDING_OF_B_VEH_POSNCURL_REPORT(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("ALL_INDIA_HOLDING_OF_B_VEH_POSNtiles");
	}
	
	// 2. End
	
	// 3.

	@RequestMapping(value = "/3_TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARS", method = RequestMethod.GET)
	public ModelAndView TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARS(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("3_TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARS", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARStiles");
	}

	@RequestMapping(value = "/TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARSCURL", method = RequestMethod.POST)
	public ModelAndView TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARSCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("3_TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARS", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARSCURL_REPORT(from, to);
	
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("TABLE_SHOWING_ANTICIPATED_DISCARD_FOR_NEXT_4_5_YEARStiles");
	}
	
	// 3. End
	
	// 4.

	@RequestMapping(value = "/4_RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD", method = RequestMethod.GET)
	public ModelAndView RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIODtiles");
	}

	@RequestMapping(value = "/RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIODCURL", method = RequestMethod.POST)
	public ModelAndView RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIODCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("4_RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIOD_REPORT(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("RECONCILIATION_STATEMENT_OF_B_VEHICLES_FOR_THE_PERIODtiles");
	}

	// 4. End
	
	// 5.
	
	@RequestMapping(value = "/5_COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", method = RequestMethod.GET)
	public ModelAndView COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("msg", msg);
		return new ModelAndView("COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSNtiles");
	}

	@RequestMapping(value = "/COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSNCURL")
	public ModelAndView COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSNCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "comd_name1", required = false) String comd_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(from, to,comd_name);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("comd_name1", comd_name);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);
		return new ModelAndView("COMMAND_SUMMARY_OF_GENERAL_B_VEHICLES_POSNtiles");
	}
	
	// 5. End
	
	// 6.

	@RequestMapping(value = "/6_CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", method = RequestMethod.GET)
	public ModelAndView CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("6_CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSNtiles");
	}

	@RequestMapping(value = "/CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSNCURL", method = RequestMethod.POST)
	public ModelAndView CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSNCURL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "corps_name1", required = false) String corps_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("6_CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(validation.checkUnit_nameLength(corps_name) == false){
			Mmap.put("msg","Corps Name Should Contain Atleast 100 Characters");
			return new ModelAndView("redirect:6_CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSN_REPORT(from, to,corps_name);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("corps_name1", corps_name);
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("CORPS_SUMMARY_OF_GENERAL_B_VEHICLES_POSNtiles");
	}
	
	// 6. End
	
	@RequestMapping(value = "/getcorps_name_report", method = RequestMethod.POST)
	public @ResponseBody List<String> getcorps_name_report(String comd_name, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl \r\n"
				+ " where sus_no in(select tbl_codesf0_.sus_no as col_0_0_ from \r\n"
				+ " Tbl_CodesForm tbl_codesf0_ where tbl_codesf0_.level_in_hierarchy='Corps') and upper(unit_name) like '%"
				+ comd_name.toUpperCase() + "%'  and status_sus_no='Active'").setMaxResults(20);
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
			
	@RequestMapping(value = "/5_COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs(CL_3)POSN_AS_ON_31_MAR_2019_TANKS_APCsUrl", method = RequestMethod.GET)
	public ModelAndView COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHsUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs(CL_3)POSN_AS_ON_31_MAR_2019_TANKS_APCsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getYear", TMSReportDao.getYearList());
		Mmap.put("msg", msg);
		return new ModelAndView("5_COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs(CL_3)POSN_AS_ON_31_MAR_2019_TANKS_APCstiles");
	}

	@RequestMapping(value = "/COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs_CL_3POSN_TANKS_APCsUrl", method = RequestMethod.POST)
	public ModelAndView COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs_CL_3POSN_TANKS_APCsUrl(ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("5_COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs(CL_3)POSN_AS_ON_31_MAR_2019_TANKS_APCsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.command_wise_usage_meteage_analysis_of_A_vehs_cl_3_POSN_TANKS_APCsrprt(from, to);
	
			Mmap.put("list", list);
			Mmap.put("getYear", TMSReportDao.getYearList());
		
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);
		return new ModelAndView("5_COMMAND_WISE_USAGE_METERAGE_ANALYSIS_OF_A_VEHs(CL_3)POSN_AS_ON_31_MAR_2019_TANKS_APCstiles");
	}

	@RequestMapping(value = "/20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl", method = RequestMethod.GET)
	public ModelAndView HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCstiles");
	}

	@RequestMapping(value = "/HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCs", method = RequestMethod.POST)
	public ModelAndView HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCs(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "arm_name1", required = false) String arm_name,
			@RequestParam(value = "prf_name1", required = false) String prf_name,
			@RequestParam(value = "force_type1", required = false) String force_type,
			@RequestParam(value = "unit_name1", required = false) String unit_name) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(validation.checkArmDescLength(arm_name) == false){
			Mmap.put("msg","ARM Name Should Contain Atleast 100 Characters");
			return new ModelAndView("redirect:20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl");
		}
		if(validation.group_nameLength(prf_name) == false){
			Mmap.put("msg","PRF Name Nomenclature Should Contain Maximum 35 Characters");
			return new ModelAndView("redirect:20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl");
		}
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg","Unit Name Should Contain Atleast 100 Characters");
			return new ModelAndView("redirect:20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl");
		}
		if(validation.checkForceTypeLength(force_type) == false){
			Mmap.put("msg","Force Type Should Contain Atleast 10 Characters");
			return new ModelAndView("redirect:20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCsUrl");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.holding_of_tanks_unit_wise_posn_mech_BN_APCsrprt(from, to, arm_name, prf_name, force_type, unit_name);
		
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);
		Mmap.put("arm_name1", arm_name);
		Mmap.put("prf_name1", prf_name);
		Mmap.put("force_type1", force_type);
		Mmap.put("unit_name1", unit_name);

		return new ModelAndView("20_HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCstiles");
	}

	@RequestMapping(value = "/25_HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGUrl", method = RequestMethod.GET)
	public ModelAndView HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGUrl(ModelMap Mmap,HttpServletRequest request,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("25_HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("25_HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGtiles");
	}

	@RequestMapping(value = "/HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDING", method = RequestMethod.POST)
	public ModelAndView HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDING(ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect(
				"25_HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.holding_state_of_A_vehs_mct_and_classification_wise_depot_wise_holdingsrprt(from, to);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("25_HOLDING_STATE_OF_A_VEHs_MCT_CLASSIFICATION_WISE_POSN_AS_ON_DEPOT_WISE_HOLDINGtiles");
	}

	@RequestMapping(value = "/26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNUrl", method = RequestMethod.GET)
	public ModelAndView HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("msg", msg);
		return new ModelAndView("26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNtiles");
	}

	@RequestMapping(value = "/HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSN", method = RequestMethod.POST)
	public ModelAndView HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSN(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to,
			@RequestParam(value = "comd_name1", required = false) String comd_name,
			@RequestParam(value = "force_type1", required = false) String force_type) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(validation.checkForceTypeLength(force_type) == false){
			Mmap.put("msg","Force Type Should Contain Atleast 10 Characters");
			return new ModelAndView("redirect:26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNUrl");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.holding_of_A_vehs_mct_and_classification_wise_posnrprt(from,to, comd_name, force_type);
	
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);
		Mmap.put("comd_name1", comd_name);
		Mmap.put("force_type1", force_type);

		return new ModelAndView("26_HOLDING_OF_A_VEHs_MCT_AND_CLASSIFICATION_WISE_POSNtiles");
	}

	@RequestMapping(value = "/12_HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNUrl", method = RequestMethod.GET)
	public ModelAndView HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("12_HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("12_HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNtiles");
	}

	@RequestMapping(value = "/HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNCUrl", method = RequestMethod.POST)
	public ModelAndView HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNCUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("12_HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNrprt(from,to);
		
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("12_HOLDING_OF_A_VEHICALES_COMMAND_AND_DEPOT_WISE_POSNtiles");
	}

	@RequestMapping(value = "/1_HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCESUrl", method = RequestMethod.GET)
	public ModelAndView HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCESUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCESUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("1_HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCEStiles");
	}

	@RequestMapping(value = "/HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCES", method = RequestMethod.POST)
	public ModelAndView HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCES(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "from_date1", required = false) String from,
			@RequestParam(value = "to_date1", required = false) String to) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("1_HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCESUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		ArrayList<ArrayList<String>> list = TMSReportDao.holding_of_A_veh_southern_command_nonfield_forcesrprt(from,to);
	
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("from_date1", from);
		Mmap.put("to_date1", to);

		return new ModelAndView("1_HOLDING_OF_A_VEHs_SOUTHERN_COMMAND_NON_FIELD_FORCEStiles");
	}
	
	@RequestMapping(value = "/getarm_name_report", method = RequestMethod.POST)
	public @ResponseBody List<String> getarm_name_report(String arm_name, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select arm_desc from Tb_Miso_Orabt_Arm_Code " + "where upper(arm_desc) like '%"
				+ arm_name.toUpperCase() + "%'").setMaxResults(20);
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
	
	@RequestMapping(value = "/getprf_name", method = RequestMethod.POST)
	public @ResponseBody List<String> getprf_name(String prf_name, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select group_name\r\n" + "from TB_TMS_MCT_SLOT_MASTER \r\n"
				+ "where upper(group_name) like '%" + prf_name.toUpperCase() + "%'").setMaxResults(20);
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
	
	@RequestMapping(value = "/getforce_type_report", method = RequestMethod.POST)
	public @ResponseBody List<String> getforce_type_report(String force_type, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct t.label from Miso_Orbat_Unt_Dtl u ,T_Domain_Value t where u.type_force=t.codevalue and t.domainid='TYPEOFFORCE' and "
						+ " upper(t.label) like '%" + force_type.toUpperCase() + "%'")
				.setMaxResults(20);
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
}