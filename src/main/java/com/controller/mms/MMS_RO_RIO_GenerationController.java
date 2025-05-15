package com.controller.mms;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
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

import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.cue.cueContoller;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.ReportsController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_RO_RIO_GenerationDAO;
import com.dao.mms.MMS_Unit_hldgDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.MMS_TB_IMP_DRR_NEWFORMATE;
import com.models.MMS_TB_REGN_MSTR_DETL;
import com.models.MMS_TB_TEMPREGN_ROWISE_DTL;
import com.models.MMS_TB_RO_GENERATE_DTL;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_RO_RIO_GenerationController {

	@Autowired
	private MMS_RO_RIO_GenerationDAO m5DAO;

	@Autowired
	private Mms_Common_DAO mmsCommonDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private MMS_Unit_hldgDAO m2DAO;

	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	ReportsController rcont = new ReportsController();

	private cueContoller M = new cueContoller();

	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();

	ValidationController validation = new ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();

	int type_veh2 = 0;
	String prf_code2 = "";
	String sus_no12 = "";
	String unit_name12 = "";
	String cont_comd12 = "";
	String cont_corps12 = "";
	String cont_div12 = "";
	String cont_bde12 = "";
	String line_dte_sus12 = "";
	String mct_main_list12 = "";

	@RequestMapping(value = "/admin/mms_ro_generation_new", method = RequestMethod.GET)
	public ModelAndView mms_ro_generation_new(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("mms_ro_generation_new", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (request.getHeader("Referer") == null) {
			msg = "";
           }
		////////////////////
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				cont_comd12 = formation_code;
				Mmap.put("cont_comd1", cont_comd12);

				List<Tbl_CodesForm> comd = rcont.getFormationList("Command", formation_code);
				Mmap.put("getCommandList", comd);
				List<Tbl_CodesForm> corps = rcont.getFormationList("Corps", formation_code);
				Mmap.put("getCorpsList", corps);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectcorps", select);
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
				Mmap.put("selectLine_dte", select);
			}
			if (roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = rcont.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				cont_corps12 = cor;
				Mmap.put("cont_corps1", cont_corps12);

				List<Tbl_CodesForm> corps = rcont.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				List<Tbl_CodesForm> Bn = rcont.getFormationList("Division", cor);
				Mmap.put("getDivList", Bn);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectDiv", select);
				Mmap.put("selectBde", select);
				Mmap.put("selectLine_dte", select);
			}
			if (roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = rcont.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = rcont.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div12 = div;
				Mmap.put("cont_div1", cont_div12);

				List<Tbl_CodesForm> Bn = rcont.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);

				List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade", div);
				Mmap.put("getBdeList", bde);

				String select = "<option value='0'>--Select--</option>";
				Mmap.put("selectBde", select);

				Mmap.put("selectLine_dte", select);
			}
			if (roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd = rcont.getFormationList("Command", command);
				Mmap.put("getCommandList", comd);

				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps = rcont.getFormationList("Corps", cor);
				Mmap.put("getCorpsList", corps);

				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn = rcont.getFormationList("Division", div);
				Mmap.put("getDivList", Bn);

				cont_bde12 = roleFormationNo;
				Mmap.put("cont_bde1", cont_bde12);

				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = rcont.getFormationList("Brigade", bde_code);
				Mmap.put("getBdeList", bde);

			}
		} else if (roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd = all.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value=''>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList", roledao.getLine_DteList(""));
		} else if (roleAccess.equals("Line_dte")) {
			List<Tbl_CodesForm> comd = all.getCommandDetailsList();
			Mmap.put("getCommandList", comd);
			String selectComd = "<option value=''>--Select--</option>";
			String select = "<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps", select);
			Mmap.put("selectDiv", select);
			Mmap.put("selectBde", select);
			Mmap.put("getLine_DteList", roledao.getLine_DteList(roleSusNo));
		} // else {
//                    return new ModelAndView("AccessTiles");
//            }

		String formCode = "";
		if (!cont_bde12.equals("0") && !cont_bde12.equals("")) {
			formCode = cont_bde12;
		} else {
			if (!cont_div12.equals("0") && !cont_div12.equals("")) {
				formCode = cont_div12;
			} else {
				if (!cont_corps12.equals("0") && !cont_corps12.equals("")) {
					formCode = cont_corps12;
				} else {
					if (!cont_comd12.equals("-1") && !cont_comd12.equals("")) {
						formCode = cont_comd12;
					}
				}
			}
		}
		if (msg != null && msg.equals("ok")) {
			Mmap.put("type_veh", type_veh2);
			Mmap.put("prf_code", prf_code2);
			Mmap.put("sus_no1", sus_no12);
			Mmap.put("unit_name1", unit_name12);
			Mmap.put("cont_comd1", cont_comd12);
			Mmap.put("cont_corps1", cont_corps12);
			Mmap.put("cont_div1", cont_div12);
			Mmap.put("cont_bde1", cont_bde12);
			Mmap.put("line_dte_sus1", line_dte_sus12);
			Mmap.put("mct_main_list1", mct_main_list12);
		} else {
			type_veh2 = 0;
			prf_code2 = "";
			/* mct_main2 = ""; */
			sus_no12 = "";
			unit_name12 = "";
			cont_comd12 = "";
			cont_corps12 = "";
			cont_div12 = "";
			cont_bde12 = "";
			line_dte_sus12 = "";
			mct_main_list12 = "";
		}
		if (msg != null && msg.equals("ok")) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("m_11", mmsCommonDAO.getMMSPRFtListBySearch2(""));
		Mmap.put("unit_list", mmsCommonDAO.getUnitDetailsList());
		Mmap.put("getForceTypeList", getForceTypeList());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getItemGroupList", getItemGroupList());

		return new ModelAndView("mms_ro_rio_Tile");
	}

//	@RequestMapping(value = "/admin/getunit_ue_uh", method = RequestMethod.POST)
//    public ModelAndView getunit_ue_uh(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
//                    @RequestParam(value = "prfgroupcodes", required = false) String prfgroupcodes,
//                    @RequestParam(value = "unit_id", required = false) String unit_id){        
//      
//            
//             return new ModelAndView("redirect:mms_ro_rio_generation");
//    }

	@RequestMapping(value = "/unit_wies_prf_ue_uh", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> unit_wies_prf_ue_uh(
			@RequestParam(value = "prfcode", required = false) String prfcode,
			@RequestParam(value = "unit", required = false) String unit) throws SQLException {
	
		return m5DAO.getUnit_ue_uh(unit, prfcode);
	}

	@RequestMapping(value = "/get_depo_prf_groupwise", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_depo_prf_groupwise(
			@RequestParam(value = "selectedPRFs", required = false) String selectedPRFs) throws SQLException {

		return m5DAO.getprfgroup_wise_depo(selectedPRFs);
	}

	@RequestMapping(value = "/get_depots_item", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_depots_item(
			@RequestParam(value = "selectedPRFs", required = false) String selectedPRFs,
			@RequestParam(value = "depotSus", required = false) String deposelected) throws SQLException {

		return m5DAO.getdepot_item(selectedPRFs, deposelected);
	}

	@RequestMapping(value = "/get_item_depowise", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_item_depowise(
			@RequestParam(value = "selectedPRFs", required = false) String selectedPRFs,
			@RequestParam(value = "search", required = false) String search_value) throws SQLException {

		return m5DAO.getdept_wiseitem(selectedPRFs, search_value);
	}

	public List<CUE_TB_MISO_MMS_ITEM_MSTR> getItemGroupList() {
		Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionComnd.beginTransaction();
		Query q = sessionComnd
				.createQuery("select codevalue,label from T_Domain_Value where domainid = 'ITEMGROUP' order by label");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
		tx.commit();
		sessionComnd.close();
		return list;
	}

	@RequestMapping(value = "/unitanditemcode_wise_census", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> unitanditemcode_wise_census(
			@RequestParam(value = "itemcode", required = false) String item_code,
			@RequestParam(value = "sus_no", required = false) String sus_no,
			@RequestParam(value = "type_of_eqpt", required = false) String type_of_eqpt) throws SQLException {

		return m5DAO.unitanditemcode_wise_census(item_code, sus_no, type_of_eqpt);
	}

	public List<Object[]> getForceTypeList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct codevalue,label from T_Domain_Value where domainid='FORCETYPE' order by label");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;
	}

//	@RequestMapping(value = "/getforceList", method = RequestMethod.POST)
//	public List<Object[]> getforceList(){	
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		Query q = session.createQuery("select distinct d.codevalue,d.label FROM Miso_Orbat_Unt_Dtl orbt,T_Domain_Value d "
//					+ "where orbt.type_force = d.codevalue and  d.domainid='FORCETYPE' and "
//					+ "orbt.status_sus_no = 'Active' ");
//			//q.setParameter("arm_code", arm_code+"%");
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = (List<Object[]>) q.list();
//		tx.commit();
//		session.close();
//		return list;
//	}

	@RequestMapping(value = "/getforceList", method = RequestMethod.POST)
	public @ResponseBody List<Object[]> getforceList(String arm_code, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query q = session
				.createQuery("select distinct d.codevalue,d.label FROM Miso_Orbat_Unt_Dtl orbt,T_Domain_Value d "
						+ "where orbt.type_force = d.codevalue and  d.domainid='FORCETYPE' and "
						+ "orbt.arm_code like :arm_code and orbt.status_sus_no = 'Active' ");
		q.setParameter("arm_code", arm_code + "%");
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;

	}

	@RequestMapping(value = "/getcomndList", method = RequestMethod.POST)
	public @ResponseBody List<Object[]> getcomndList(String arm_code, String force_type, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String s = "select distinct SUBSTR(orbt.form_code_control,1,1),orbt.unit_name FROM Miso_Orbat_Unt_Dtl orbt,T_Domain_Value d  "
				+ "			where sus_no in(select sus_no from Tbl_CodesForm where level_in_hierarchy='Command') and  orbt.type_force = d.codevalue and "
				+ "						orbt.arm_code like :arm_code and orbt.status_sus_no = 'Active' ";
		if (!force_type.equals("")) {
			s += " and orbt.type_force=:force_type ";
		}

		Query q = session.createQuery(s);

		q.setParameter("arm_code", arm_code + "%");

		if (!force_type.equals("")) {
			q.setParameter("force_type", force_type);
		}

		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;

	}

	@RequestMapping(value = "/get_corps", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_corps(
			@RequestParam(value = "formationcode", required = false) String formationcode) throws SQLException {

		return m5DAO.get_corps(formationcode);
	}

	@RequestMapping(value = "/get_division", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_division(
			@RequestParam(value = "formationcodecorps", required = false) String formationcode) throws SQLException {

		return m5DAO.get_division(formationcode);
	}

	@RequestMapping(value = "/get_bde", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_bde(
			@RequestParam(value = "formationcodediv", required = false) String formationcode) throws SQLException {

		return m5DAO.get_bde(formationcode);
	}

	@RequestMapping(value = "/get_unit", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_unit(
			@RequestParam(value = "formationcodebde", required = false) String formationcode) throws SQLException {

		return m5DAO.get_unit(formationcode);
	}

	@RequestMapping(value = "/admin/generat_ro_action", method = RequestMethod.POST)
	public @ResponseBody String generat_ro_action(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request, @RequestParam(value = "rowData", required = false) String rowData,
			@RequestParam(value = "depot_sus_no", required = false) String depot_sus_no,
			@RequestParam(value = "ro_no", required = false) String ro_no) throws ParseException {

	String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		

		String[] entries = rowData.split(",");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		int n = 0;
		for (String entry : entries) {

			String[] values = entry.split(":");
			String censusNo = values[0];
			String unit_sus_no = values[1];
			String depot_susno = depot_sus_no;
			String itemCode = values[2];
			String itemUe = values[3];
			String itemUh = values[4];
			String prf_code = values[5];
			String type_of_eqpt = values[6];
			String quantity = values[7];
			Double qty = Double.parseDouble(quantity);
			String tempreg_no = null;
			n = n + 1;
			String Ro_no = ro_no + "/" + n;
			for (int i = 0; i < qty; i++) {
				Session sessionMax1 = HibernateUtil.getSessionFactory().openSession();
				Transaction txMax1 = sessionMax1.beginTransaction();

				String maxregno1 = "";
				String RegNo1 = "";
				String regno = "tempno";

				Query qMax1 = sessionMax1.createQuery(
						"SELECT  max(SUBSTRING(eqpt_regn_no,7,7)) FROM MMS_TB_REGN_MSTR_DETL where eqpt_regn_no like :eqpt_regn_no ");
				qMax1.setParameter("eqpt_regn_no", regno + "%");
				maxregno1 = (String) qMax1.list().get(0);

				if (maxregno1 == null || maxregno1.equals("") || maxregno1.equals(null)) {
					RegNo1 = "tempno00001";
				} else {
					String serial = maxregno1;
					int serialNo = Integer.parseInt(serial) + 1;
					String formattedSerialNo = String.format("%05d", serialNo);
					RegNo1 = regno + formattedSerialNo;

				}

				if (tempreg_no == null || tempreg_no.isEmpty() || tempreg_no.equalsIgnoreCase("null")) {
					tempreg_no = RegNo1;
				} else {
					tempreg_no += "," + RegNo1;
				}

				String Regn_seq_no_max = m2DAO.RegnNoGeneration1_UNIT(prf_code, "");
				String[] split_reg = Regn_seq_no_max.split("N");
				String Regn_seq_no = "";

				Regn_seq_no = split_reg[0] + "N" + String.format("%08d", (Integer.parseInt(split_reg[1]) + i));
				check_reg_no(Regn_seq_no, split_reg, i);

				MMS_TB_TEMPREGN_ROWISE_DTL regro = new MMS_TB_TEMPREGN_ROWISE_DTL();
				regro.setRo_no(Ro_no);
				regro.setRo_date(new Date());
				regro.setUnit_sus_no(unit_sus_no);
				regro.setDepot_sus_no(depot_susno);
				regro.setRegn_no(RegNo1);
				regro.setRegn_seq_no(Regn_seq_no);
				regro.setCensus_no(censusNo);
				regro.setPrf_code(prf_code);
				regro.setItem_code(prf_code);
				regro.setStatus(0);
				regro.setCreated_by(username);
				regro.setCreated_date(new Date());
				sessionMax1.save(regro);

				MMS_TB_REGN_MSTR_DETL reg = new MMS_TB_REGN_MSTR_DETL();
				reg.setSus_no(unit_sus_no);
				reg.setCensus_no(censusNo);
				reg.setRegn_seq_no(Regn_seq_no);
				reg.setType_of_hldg("A0");
				reg.setType_of_eqpt(type_of_eqpt);
				reg.setEqpt_regn_no(RegNo1);
				reg.setFrom_sus_no(unit_sus_no);
				reg.setTo_sus_no(unit_sus_no);
				reg.setService_status("1");
				reg.setPrf_code(itemCode);
				reg.setData_cr_date(new Date());
				reg.setData_cr_by(roleSusNo);
				reg.setOp_status("1");
				sessionMax1.save(reg);
				txMax1.commit();
				sessionMax1.close();

			}

			MMS_TB_RO_GENERATE_DTL ro = new MMS_TB_RO_GENERATE_DTL();
			ro.setCensus_no(censusNo);
			ro.setUnit_sus_no(unit_sus_no);
			ro.setDepots_sus_no(depot_susno);
			ro.setItem_code(itemCode);
			ro.setItem_ue(Integer.parseInt(itemUe));
			ro.setItem_uh(Integer.parseInt(itemUh));
			ro.setRo_issue_qnty(Double.parseDouble(quantity));
			ro.setYet_to_collect(Double.parseDouble(quantity));
			ro.setIssue_qnty(0);
			ro.setStatus(0);
			ro.setEqpt_regn_no(tempreg_no);
			ro.setRo_no(Ro_no);
			ro.setCreated_by(username);
			ro.setCreated_date(new Date());
			session1.save(ro);

			Query checkQuery = session1.createQuery(
					"FROM MMS_TB_IMP_DRR_NEWFORMATE WHERE recv_sus_no = :depot_sus AND census_no = :census_no");
			checkQuery.setParameter("depot_sus", depot_susno);
			checkQuery.setParameter("census_no", censusNo);
			List<?> existingRecords = checkQuery.list();

			MMS_TB_IMP_DRR_NEWFORMATE existingRecord = (MMS_TB_IMP_DRR_NEWFORMATE) existingRecords.get(0);
			double existingQuantity = existingRecord.getDepot_stock();
			existingRecord.setDepot_stock(existingQuantity - Double.parseDouble(quantity));
			session1.update(existingRecord);

		}
		tx.commit();
		session1.close();
		return rowData;
	}

	@RequestMapping(value = "/release_order")
	public ModelAndView release_order(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		 int userid = (Integer) session.getAttribute("userId");
		 String roleType = (String) session.getAttribute("roleType");
		 String accsLvl = (String) session.getAttribute("roleAccess");
		 String accssubLvl = (String) session.getAttribute("roleAccess");

		Boolean val = roledao.ScreenRedirect("release_order", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}

		model.put("msg", msg);
		return new ModelAndView("mmsroTiles");
	}

	@RequestMapping(value = "/getmms_ro_Data", method = RequestMethod.POST)
	public ModelAndView getmms_ro_Data(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "depot_sus_no1", required = false) String depot_sus_no,
			@RequestParam(value = "depot_name1", required = false) String depot_name,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "to_date1", required = false) String tdate,
			@RequestParam(value = "status1", required = false) String status) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		Boolean val = roledao.ScreenRedirect("release_order", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!sus_no.equals("") && sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		} else if (sus_no != "" & sus_no != null & !sus_no.equals("") & !sus_no.equals(null)
				& validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:release_order");
		}
		if (unit_name.length() > 100 & validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:release_order");
		}

		Mmap.put("depot_sus_no", depot_sus_no);
		Mmap.put("depot_name", depot_name);

		if (!fdate.equals("")) {
			Mmap.put("fdate", fdate);
		}
		if (!tdate.equals("")) {
			Mmap.put("tdate", tdate);
		}
		if (!status.equals("")) {
			Mmap.put("status", status);
		}
		ArrayList<ArrayList<String>> list = m5DAO.getROReport(sus_no, fdate, tdate, Integer.parseInt(status), roleType,
				depot_sus_no, sessionA);
		Mmap.put("list", list);

		return new ModelAndView("mmsroTiles");
	}

	@RequestMapping(value = "/admin/reject_ro_mms", method = RequestMethod.POST)
	public ModelAndView reject_ro_mms(@ModelAttribute("id1") int id, @ModelAttribute("depots_sus1") String depots_sus,
			@ModelAttribute("unit_sus1") String unit_sus, @ModelAttribute("census_no1") String census_no,
			@ModelAttribute("qty1") String qty, @ModelAttribute("yet_to_collect") String yet_to_collect,
			@ModelAttribute("sus_no3") String sus_no, @ModelAttribute("unit_name3") String unit_name,
			@ModelAttribute("depot_sus_no3") String depot_sus_no, @ModelAttribute("depot_name3") String depot_name,
			@ModelAttribute("from_date3") String from_date, @ModelAttribute("to_date3") String to_date,
			@ModelAttribute("status3") String status, HttpSession sessionA, HttpServletRequest request, ModelMap Mmap,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("release_order", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int yet_to_collect_qnty = 0;
		if (Double.parseDouble(qty) == Double.parseDouble(yet_to_collect)) {
			yet_to_collect_qnty = 1;
		}

		Boolean regdeleted = m5DAO.delete_reg_no(id, yet_to_collect_qnty);
		if (regdeleted == false) {
			Mmap.put("msg", "Data Reject Successfully");
			return new ModelAndView("redirect:release_order");
		}

		String hqlUpdate = "update MMS_TB_RO_GENERATE_DTL set status=:status,eqpt_regn_no=:eqpt_regn_no"
				+ " where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setInteger("id", id)
				.setString("eqpt_regn_no", "").executeUpdate();

		Query checkQuery = sessionHQL.createQuery(
				"FROM MMS_TB_IMP_DRR_NEWFORMATE WHERE recv_sus_no = :depot_sus AND census_no = :census_no");
		checkQuery.setParameter("depot_sus", depots_sus);
		checkQuery.setParameter("census_no", census_no);
		List<?> existingRecords = checkQuery.list();

		MMS_TB_IMP_DRR_NEWFORMATE existingRecord = (MMS_TB_IMP_DRR_NEWFORMATE) existingRecords.get(0);
		double existingQuantity = existingRecord.getDepot_stock();
		existingRecord.setDepot_stock(existingQuantity + Double.parseDouble(qty));
		sessionHQL.update(existingRecord);

		if (app > 0) {
			msg = "Data Reject Successfully";
		}
		tx.commit();
		sessionHQL.close();

		ArrayList<ArrayList<String>> list = m5DAO.getROReport(sus_no, from_date, to_date, Integer.parseInt(status), "",
				depot_sus_no, sessionA);
		Mmap.put("sus_no", sus_no);
		Mmap.put("unit_name", unit_name);
		Mmap.put("depot_sus_no", depot_sus_no);
		Mmap.put("depot_name", depot_name);
		Mmap.put("fdate", from_date);
		Mmap.put("tdate", to_date);
		Mmap.put("status", status);
		Mmap.put("msg", msg);
		Mmap.put("list", list);

		return new ModelAndView("mmsroTiles");
	}

	@RequestMapping(value = "/generatemmsRONo")
	public @ResponseBody List<String> generatemmsRONo(String roNo, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		ArrayList<String> list1 = new ArrayList<String>();
		String maxRo1 = "";
		String RoNo1 = "";
		Session sessionMax1 = HibernateUtil.getSessionFactory().openSession();
		Transaction txMax1 = sessionMax1.beginTransaction();
		Query qMax1 = sessionMax1
				.createQuery("SELECT  max(SUBSTRING(ro_no,8,5)) FROM MMS_TB_RO_GENERATE_DTL where ro_no like :roNo ");
		qMax1.setParameter("roNo", roNo + "%");
		maxRo1 = (String) qMax1.list().get(0);
		txMax1.commit();
		sessionMax1.close();
		if (maxRo1 == null || maxRo1.equals("") || maxRo1.equals(null)) {
			RoNo1 = "00001";
		} else {
			String serial = maxRo1;
			int serialNo = Integer.parseInt(serial) + 1;
			RoNo1 = String.format("%05d", serialNo);
		}
		list1.add(roNo + RoNo1);
		return list1;
	}

	public void check_reg_no(String Regn_seq_no, String[] split_reg, int i) {
		if (m2DAO.checkIfExistUnitHoldingeqpt_regn_no(Regn_seq_no).get(0) != null
				&& Integer.parseInt(m2DAO.checkIfExistUnitHoldingeqpt_regn_no(Regn_seq_no).get(0)) > 0) {
			i++;
			Regn_seq_no = split_reg[0] + "N" + String.format("%08d", (Integer.parseInt(split_reg[1]) + i));
			check_reg_no(Regn_seq_no, split_reg, i);

		}

	}

}
