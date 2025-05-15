package com.controller.psg.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.dao.Jco_Master.ApptDAOImpl;
import com.dao.Jco_Master.Cause_Of_Non_EffectiveDAO_JCO;
import com.dao.Jco_Master.Class_DAO_JCO;
import com.dao.Jco_Master.Class_Pay_DAO_JCO;
import com.dao.Jco_Master.Gorkha_Domisile_DAO_JCO;
import com.dao.Jco_Master.Pay_GroupDAO_JCO;
import com.dao.Jco_Master.Promotional_Exam_DAO_JCO;
import com.dao.Jco_Master.RankDAOImpl;
import com.dao.Jco_Master.Record_OfficeDAO_JCO;
import com.dao.Jco_Master.Trade_DAO_JCO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian_Master.Cadre_CivilianDAO;
import com.dao.psg.Civilian_Master.CategoryDAO;
import com.dao.psg.Civilian_Master.Cause_Of_Non_Effective_CivilianDAO;
import com.dao.psg.Civilian_Master.DisabilityDAO;
import com.dao.psg.Civilian_Master.Pay_Level_DAO;
import com.dao.psg.Master.Army_actDAO;
import com.dao.psg.Master.AwardCatDAO;
import com.dao.psg.Master.BPETqtDAO;
import com.dao.psg.Master.BPETresultDAO;
import com.dao.psg.Master.BattalionDAO;
import com.dao.psg.Master.BloodDao;
import com.dao.psg.Master.Casuality_Dao;
import com.dao.psg.Master.Cause_Of_Non_EffectiveDAO;
import com.dao.psg.Master.City_DAO;
import com.dao.psg.Master.Civilian_DesignationDAO;
import com.dao.psg.Master.Civilian_TradeDAO;
import com.dao.psg.Master.Combination_Of_AllceaDAO;
import com.dao.psg.Master.CompanyDAO;
import com.dao.psg.Master.CopeCodeDAO;
import com.dao.psg.Master.CountryDao;
import com.dao.psg.Master.CourseDAO;
import com.dao.psg.Master.Course_InstituteDAO;
import com.dao.psg.Master.Course_TypeDAO;
import com.dao.psg.Master.DegreeDao;
import com.dao.psg.Master.Discipline_TrialedDAO;
import com.dao.psg.Master.DistrictDao;
import com.dao.psg.Master.DivDAO;
import com.dao.psg.Master.Examination_PassedDAO;
import com.dao.psg.Master.Eye_ColourDAO;
import com.dao.psg.Master.Filed_AreaDAO;
import com.dao.psg.Master.FiringQtDAO;
import com.dao.psg.Master.FiringResultDAO;
import com.dao.psg.Master.Foreign_language_DAO;
import com.dao.psg.Master.GenderDAO;
import com.dao.psg.Master.Hair_ColorDao;
import com.dao.psg.Master.HeightDAO;
import com.dao.psg.Master.Indian_languageDAO;
import com.dao.psg.Master.InstituteDAO;
import com.dao.psg.Master.LanguageProfessionDAO;
import com.dao.psg.Master.LanguageStandardDAO;
import com.dao.psg.Master.MaritialDAO;
import com.dao.psg.Master.Med_cat_valuesDAO;
import com.dao.psg.Master.Medal_TypeDAO;
import com.dao.psg.Master.Mother_TongueDAO;
import com.dao.psg.Master.Nationality_DAO;
import com.dao.psg.Master.OperationDAO;
import com.dao.psg.Master.Personnel_typeDAO;
import com.dao.psg.Master.PlatoonDAO;
import com.dao.psg.Master.ProfessionDAO;
import com.dao.psg.Master.PromotionalExamDAO;
import com.dao.psg.Master.Purposeof_VisitDAO;
import com.dao.psg.Master.QualificationDao;
import com.dao.psg.Master.RelationDAO;
import com.dao.psg.Master.Religion_DAO;
import com.dao.psg.Master.SecondedToDAO;
import com.dao.psg.Master.SpecializationDAO;
import com.dao.psg.Master.StateDao;
import com.dao.psg.Master.Sub_clauseDAO;
import com.dao.psg.Master.Subject_Dao;
import com.dao.psg.Master.Type_of_EmploymentDAO;
import com.dao.psg.Master.TypeofEntryDao;
import com.dao.psg.Master.commisionDao;
import com.dao.psg.ad_hoc.AdHocDAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class ReportController {
	
	@Autowired
	AdHocDAO ad;
	
	@Autowired
	private InstituteDAO instDao;
	@Autowired
	commisionDao cm_Dao;
	@Autowired
	private StateDao State_dao;
	@Autowired
	private DistrictDao District_dao;
	@Autowired
	private City_DAO city_dao;
	@Autowired
	private Nationality_DAO nat_dao;
	@Autowired
	private Religion_DAO reli_dao;
	@Autowired
	private MaritialDAO mari_dao;
	@Autowired
	private BloodDao bld;
	@Autowired
	private Hair_ColorDao hairdao;
	@Autowired
	private Eye_ColourDAO eyedao;
	@Autowired
	private Type_of_EmploymentDAO Emdao;
	@Autowired
	private Medal_TypeDAO Mdao;
	@Autowired
	private Course_TypeDAO ctdao;
	@Autowired
	private RelationDAO Rdao;
	@Autowired
	private GenderDAO gddao;
	@Autowired
	private DivDAO dvdao;
	@Autowired
	private Personnel_typeDAO ptypeDAO;
	@Autowired
	private CountryDao conDao;
	@Autowired
	private QualificationDao qualiDao;
	@Autowired
	private HeightDAO hDAO;
	@Autowired
	private Med_cat_valuesDAO mvalDao;
	@Autowired
	private OperationDAO opDao;
	@Autowired 
	private ProfessionDAO profDao;
	@Autowired
	private BattalionDAO batDao;
	@Autowired
	private CompanyDAO comDao;
	@Autowired
	private PlatoonDAO platDao;
	@Autowired
	private Indian_languageDAO indiDao;
	@Autowired
	private Foreign_language_DAO forLanDao;
	@Autowired
	private SpecializationDAO specDao;
	@Autowired
	private DegreeDao degDao;
	@Autowired
	private Examination_PassedDAO examDao;
	@Autowired
	private TypeofEntryDao typeDAO;
	@Autowired
	private CourseDAO commDao;
	@Autowired
	private Mother_TongueDAO mtDao;
	@Autowired
	private LanguageStandardDAO langstdDao;
	@Autowired
	private LanguageProfessionDAO langprofDao;
	@Autowired
	private Purposeof_VisitDAO purDAO;
	@Autowired
	private AwardCatDAO acat;
	@Autowired
	private PromotionalExamDAO pexam;
	@Autowired
	private SecondedToDAO STDAO;
	@Autowired
	private CopeCodeDAO ccDAO;
	@Autowired
	private BPETresultDAO bpet;
	@Autowired
	private BPETqtDAO qt;
	@Autowired
	private FiringResultDAO fire;
	@Autowired
	private FiringQtDAO fqt;
	@Autowired
	Filed_AreaDAO FRdao;
	@Autowired
	private Combination_Of_AllceaDAO COAdao;
	@Autowired
	private Casuality_Dao C1;
	@Autowired
	private Course_InstituteDAO CIdao;
	@Autowired
	private Civilian_TradeDAO CTdao;
	@Autowired
	private Civilian_DesignationDAO CDDAO;
	@Autowired
	private Cause_Of_Non_EffectiveDAO CNEDao;
	@Autowired
	private Discipline_TrialedDAO Didao;
	@Autowired
	private Sub_clauseDAO Ssdao;
	@Autowired
	private Army_actDAO Aadao;
	
	@Autowired
	private Pay_GroupDAO_JCO PGdao;
	@Autowired
	private Class_DAO_JCO CDAO;
	@Autowired
	private Trade_DAO_JCO TDAO;
	@Autowired
	Class_Pay_DAO_JCO Class_payDao;
	@Autowired
	Record_OfficeDAO_JCO rec_dao;

	@Autowired
	ApptDAOImpl aaptdao;
	
	@Autowired
	RankDAOImpl rankDAO;
	
	@Autowired
	Promotional_Exam_DAO_JCO pexamDao;
	@Autowired
	private Cause_Of_Non_EffectiveDAO_JCO CNEJcoDao;
	
	@Autowired
	private Cause_Of_Non_Effective_CivilianDAO CNECivilianDao;
	
	@Autowired
	private Cadre_CivilianDAO CadreCivilianDao;
	
	@Autowired
	private Gorkha_Domisile_DAO_JCO Gorkha_Domisile_dao_JCO;
	
	
	@Autowired
	private Pay_Level_DAO pl_dao;
	
	@Autowired
	private CategoryDAO cat_dao;
	
	@Autowired
	private DisabilityDAO dis_dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Subject_Dao sbtdao;
	
	@RequestMapping(value = "/Report_Casuality1", method = RequestMethod.POST)
	public ModelAndView Report_Casuality1(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality1_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = C1.Report_Casuality1();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CASUALITY1");
		String Heading = "\nCasuality1";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/Report_Casuality2", method = RequestMethod.POST)
	public ModelAndView Report_Casuality2(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		
		ArrayList<ArrayList<String>> C2list = C1.Search_Casuality2Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CASUALITY1");
		TH.add("CASUALITY2");
		String Heading = "\nCasuality2";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C2list);
	}
	
	@RequestMapping(value = "/Report_Casuality3", method = RequestMethod.POST)
	public ModelAndView Report_Casuality3(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality3_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C3list = C1.Search_Casuality3_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CASUALITY1");
		TH.add("CASUALITY2");
		TH.add("CASUALITY3");
		String Heading = "\nCasuality3";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C3list);
	}
	@RequestMapping(value = "/Institutereport", method = RequestMethod.POST)
	public ModelAndView Institutereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val1 = roledao.ScreenRedirect("institute", roleid);
		if (val1 == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		ArrayList<ArrayList<String>> CTlist = instDao.search_InstituteReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("INSTITUTE NAME");
		TH.add("BATTALION");
		TH.add("COMPANY");
		String Heading = "\nInstitute";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/CommissionTypeireport", method = RequestMethod.POST)
	public ModelAndView CommissionTypeireport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Commission_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = cm_Dao.search_commisionReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COMMISION TYPE");
		String Heading = "\nCOMMISION TYPE";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Statereport", method = RequestMethod.POST)
	public ModelAndView Statereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = State_dao.search_StateReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COUNTRY NAME");
		TH.add("STATE NAME");
		String Heading = "\nState";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Districtreport", method = RequestMethod.POST)
	public ModelAndView Districtreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = District_dao.search_DistrictReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COUNTRY NAME");
		TH.add("STATE NAME");
		TH.add("DISTRICT NAME");
		String Heading = "\nDistrict";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Cityreport", method = RequestMethod.POST)
	public ModelAndView Cityreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("City", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = city_dao.search_cityReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COUNTRY NAME");
		TH.add("STATE NAME");
		TH.add("DISTRICT NAME");
		TH.add("CITY NAME");
		String Heading = "\nCity";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Nationalityreport", method = RequestMethod.POST)
	public ModelAndView Nationalityreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nationality", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = nat_dao.search_nationlityReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COUNTRY NAME");
		TH.add("NATIONALITY NAME");
		String Heading = "\nNationality";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Religionreport", method = RequestMethod.POST)
	public ModelAndView Religionreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Religion", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		
		ArrayList<ArrayList<String>> CTlist = reli_dao.search_religionReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("RELIGION NAME");
		String Heading = "\nReligion";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/MaritalStatusreport", method = RequestMethod.POST)
	public ModelAndView MaritalStatusreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Marital_status", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = mari_dao.search_maritialReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("MARITAL CODE");
		TH.add("MARITAL NAME");
		String Heading = "\nMarital Status";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/BloodGroupreport", method = RequestMethod.POST)
	public ModelAndView BloodGroupreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Blood", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = bld.search_BloodGroupReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("BLOOD DESCRIPTION");
		String Heading = "\nBlood Group";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/HairColourreport", method = RequestMethod.POST)
	public ModelAndView HairColourreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Hair_Colour", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = hairdao.search_hair_colourReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COLOUR NAME");
		String Heading = "\nHair Colour";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/EyeColourreport", method = RequestMethod.POST)
	public ModelAndView EyeColourreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = eyedao.GetSearch_Eye_ColourReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COLOUR NAME");
		String Heading = "\nEye Colour";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

	@RequestMapping(value = "/EmploymentTypereport", method = RequestMethod.POST)
	public ModelAndView EmploymentTypereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Type_of_Employment", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Emdao.search_type_of_employmentReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("EMPLOYMENT NAME");
		String Heading = "\nType of Employment";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/MedalTypereport", method = RequestMethod.POST)
	public ModelAndView MedalTypereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Medal_TypeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Mdao.search_MedalTypeReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("MEDAL TYPE");
		TH.add("MEDAL NAME");
		TH.add("MEDAL ABBREVIATION");
		String Heading = "\nMedal Type";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/CourseTypereport", method = RequestMethod.POST)
	public ModelAndView CourseTypereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Course_Type_and_Course", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = ctdao.search_Course_typeReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Category");
		TH.add("COURSE NAME");
		TH.add("COURSE TYPE");
		TH.add("COURSE GROUP");
		String Heading = "\nCourse Type";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Relationreport", method = RequestMethod.POST)
	public ModelAndView Relationreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("RelationUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Rdao.search_relationReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("RELATION NAME");
		String Heading = "\nRelation";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Genderreport", method = RequestMethod.POST)
	public ModelAndView Genderreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Gender", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = gddao.search_genderReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("GENDER NAME");
		String Heading = "\nGender";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/divreport", method = RequestMethod.POST)
	public ModelAndView divreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = dvdao.search_divReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Div/Grade");
		String Heading = "\nDiv";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Subreport", method = RequestMethod.POST)
	public ModelAndView Subreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("DivGrade", roleid);	
//		 	if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//				return new ModelAndView("redirect:bodyParameterNotAllow");
//			}
		
		ArrayList<ArrayList<String>> CTlist = sbtdao.search_SubReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("SUBJECT");
		String Heading = "\nDiv";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/PersonnelTypereport", method = RequestMethod.POST)
	public ModelAndView PersonnelTypereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personnel_type", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = ptypeDAO.search_Personnel_type_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PERSONNEL NAME");
		String Heading = "\nPersonnel Type";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/Countryreport", method = RequestMethod.POST)
	public ModelAndView Countryreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Country", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = conDao.search_Country_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COUNTRY NAME");
		String Heading = "\nCountry";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/Qualificationreport", method = RequestMethod.POST)
	public ModelAndView Qualificationreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = qualiDao.search_qualificationReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("QUALIFICATION TYPE");
		TH.add("EXAMINATION PASSED");
		TH.add("DEGREE ACQUIRED");
		String Heading = "\nQualification";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Heightreport", method = RequestMethod.POST)
	public ModelAndView Heightreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Height", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = hDAO.search_height_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CENTIMETER");
		TH.add("INCH");
		String Heading = "\nHeight";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/MedicalValuesreport", method = RequestMethod.POST)
	public ModelAndView MedicalValuesreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Med_cat_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = mvalDao.search_med_cat_valuesReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("SHAPE STATUS");
		TH.add("VALUES");
		String Heading = "\nMedical Category Values";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Operationreport", method = RequestMethod.POST)
	public ModelAndView Operationreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Operation", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = opDao.search_OperaionReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("OPERATION NAME");
		String Heading = "\nOperation";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Proffesionreport", method = RequestMethod.POST)
	public ModelAndView Proffesionreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Profession", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = profDao.search_ProffesionReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PROFFESION NAME");
		String Heading = "\nProffesion";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Battalionreport", method = RequestMethod.POST)
	public ModelAndView Battalionreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Battalion", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = batDao.search_BattalionReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("BATTALION NAME");
		String Heading = "\nBattalion";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Companyreport", method = RequestMethod.POST)
	public ModelAndView Companyreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Company", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = comDao.search_CompanyReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("BATTALION NAME");
		TH.add("COMPANY NAME");
		String Heading = "\nCompany";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Platoonreport", method = RequestMethod.POST)
	public ModelAndView Platoonreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Platoon", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = platDao.search_PlatoonReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PLATOON NAME");
		String Heading = "\nPlatoon";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/IndianLanguagereport", method = RequestMethod.POST)
	public ModelAndView IndianLanguagereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = indiDao.search_indian_languageReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("LANGUAGE NAME");
		String Heading = "\nIndian Language";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/ForeignLanguagereport", method = RequestMethod.POST)
	public ModelAndView ForeignLanguagereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Foreign_Language_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = forLanDao.GetSearch_Foreign_LanguageReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("FOREIGN LANGUAGE");
		String Heading = "\nForeign Language";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Specializationreport", method = RequestMethod.POST)
	public ModelAndView Specializationreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = specDao.search_SpecializationReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("SPECIALIZATION");
		TH.add("STATUS");
		String Heading = "\nSpecialization";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Degreereport", method = RequestMethod.POST)
	public ModelAndView Degreereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = degDao.search_DegreeReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("DEGREE NAME");
		String Heading = "\nDegree";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

	@RequestMapping(value = "/ExaminationPassedreport", method = RequestMethod.POST)
	public ModelAndView ExaminationPassedreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Examination_PassedUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = examDao.search_ExaminationPassedReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("EXAMINATION PASSED");
		TH.add("QUALIFICATION TYPE");
		String Heading = "\nExamination Passed";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/TypeOfEntryreport", method = RequestMethod.POST)
	public ModelAndView TypeOfEntryreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("TypeofEntryUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = typeDAO.search_TypeEntryReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("TYPE OF ENTRY");
		TH.add("STATUS");
		String Heading = "\nType of Entry";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/CourseCommreport", method = RequestMethod.POST)
	public ModelAndView CourseCommreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CourseCommUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = commDao.search_course_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COURSE NAME");
		TH.add("STATUS");
		String Heading = "\nCourse Comm";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/MotherTonguereport", method = RequestMethod.POST)
	public ModelAndView MotherTonguereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = mtDao.search_M_T_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("MOTHER TONGUE");
		TH.add("STATUS");
		String Heading = "\nMother Tongue";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/LangStdreport", method = RequestMethod.POST)
	public ModelAndView LangStdreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = langstdDao.Search_LangSTD_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("LANGUAGE STANDARD");
		TH.add("STATUS");
		String Heading = "\nLanguage Standard";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/LangProfreport", method = RequestMethod.POST)
	public ModelAndView LangProfreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("LanguageProfession", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = langprofDao.Search_LangProf_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("LANGUAGE PROFESSIOIN");
		TH.add("STATUS");
		String Heading = "\nLanguage Profession";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/VisitPurposereport", method = RequestMethod.POST)
	public ModelAndView VisitPurposereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("visitPurposeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = purDAO.search_visitPurposeReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("VISIT PURPOSE");
		TH.add("STATUS");
		String Heading = "\nPurpose of Visit";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	
	@RequestMapping(value = "/AwardCatreport", method = RequestMethod.POST)
	public ModelAndView AwardCatreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("awardCategoryUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = acat.AwardCatReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("AWARD CATEGORY");
		TH.add("STATUS");
		String Heading = "\nAward Category";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/promoExamreport", method = RequestMethod.POST)
	public ModelAndView promoExamreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("promoExamUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = pexam.PromoExamReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PROMOTIONAL EXAM");
		TH.add("STATUS");
		String Heading = "\nPromotional Exam";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/STOreport", method = RequestMethod.POST)
	public ModelAndView STOreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = STDAO.SecondedToReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("SECONDED TO");
		TH.add("STATUS");
		String Heading = "\nSeconded To";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/CopeCodereport", method = RequestMethod.POST)
	public ModelAndView CopeCodereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CopecodeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = ccDAO.CopecodeReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("COPE CODE");
		TH.add("VALUE");
		TH.add("DESCRIPTION");
		TH.add("STATUS");
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/bpetResultreport", method = RequestMethod.POST)
	public ModelAndView bpetResultreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("BPETresultUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		ArrayList<ArrayList<String>> CTlist = bpet.BPETReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("BPET RESULT");
		TH.add("STATUS");
		String Heading = "\nBPET Result";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/bpetqtreport", method = RequestMethod.POST)
	public ModelAndView bpetqtreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("BPETqtUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		ArrayList<ArrayList<String>> CTlist = qt.BPETReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("BPET QT");
		TH.add("STATUS");
		String Heading = "\nBPET qt";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/FiringResultreport", method = RequestMethod.POST)
	public ModelAndView FiringResultreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("FiringResultUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = fire.FiringReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("FIRING RESULT");
		TH.add("STATUS");
		String Heading = "\nFiring Result";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/FiringQreport", method = RequestMethod.POST)
	public ModelAndView FiringQreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("FiringQtUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = fqt.FiringQtReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("FIRING QT");
		TH.add("STATUS");
		String Heading = "\nFiring Qt";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/FieldAreareport", method = RequestMethod.POST)
	public ModelAndView FieldAreareport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Field_Area_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = FRdao.search_Field_Area_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("FIELD AREA");
		TH.add("STATUS");
		String Heading = "\nField Area";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Combination_of_Allceareport", method = RequestMethod.POST)
	public ModelAndView Combination_of_Allceareport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = COAdao.search_Combination_Of_allcea_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("FD 1");
		TH.add("FD 2");
		TH.add("Status");
		String Heading = "\nCombination Of Allcea";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/course_institutereport", method = RequestMethod.POST)
	public ModelAndView course_institutereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Course_InstituteUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CIdao.search_Course_Institute_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CATEGORY");
		TH.add("COURSE INSTITUTE");
		TH.add("STATUS");
		String Heading = "\nCourse Institute";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/civilian_tradeReport", method = RequestMethod.POST)
	public ModelAndView civilian_tradeReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Civilian_TradeUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CTdao.search_Civilian_Trade_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CIVILIAN TRADE");
		TH.add("STATUS");
		String Heading = "\nCivilian Trade";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Civilian_Designationreport", method = RequestMethod.POST)
	public ModelAndView Civilian_Designationreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Civilian_DesignationUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CDDAO.Civilian_DesignationReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CLASSIFICATION OF SERVICES");
		TH.add("DESIGNATION");
		TH.add("GROUP");
		TH.add("STATUS");
		TH.add("CIVILIAN TRADE");
		String Heading = "\nCivilian Designation";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Causeof_non_effreport", method = RequestMethod.POST)
	public ModelAndView Causeof_non_effreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Cause_of_non_effUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CNEDao.Cause_Of_Noneff_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CAUSES NAME");
		TH.add("TYPE OF OFFICER");
		TH.add("STATUS");
		String Heading = "\nCause of Non Effective";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Discipline_TrialedReport", method = RequestMethod.POST)
	public ModelAndView Discipline_TrialedReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Didao.search_Disc_TrialedReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("DISCIPLINE TRIALED");
		TH.add("STATUS");
		String Heading = "\nDiscipline Trialed";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Sub_ClauseReport", method = RequestMethod.POST)
	public ModelAndView Sub_ClauseReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Sub_Clause_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = Ssdao.search_Sub_ClauseReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("SUB CLAUSE");
		TH.add("STATUS");
		String Heading = "\n\"Sub Clause";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/Army_ActReport", method = RequestMethod.POST)
	public ModelAndView Army_ActReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Army_Act_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = Aadao.search_Army_actReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("ARMY ACT SEC");
		TH.add("STATUS");
		String Heading = "\n\"Army Act Sec";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/Pay_groupreport", method = RequestMethod.POST)
	public ModelAndView Pay_groupreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Pay_Group_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = PGdao.Pay_Group_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PAY GROUP");
		TH.add("STATUS");
		String Heading = "\n\"PAY GROUP";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/ClassReport", method = RequestMethod.POST)
	public ModelAndView ClassReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Class_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = CDAO.Class_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CLASS");
		TH.add("STATUS");
		String Heading = "\n\"Class";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/TradeReport", method = RequestMethod.POST)
	public ModelAndView TradeReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Trade_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = TDAO.Trade_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("TRADE");
		TH.add("STATUS");
		String Heading = "\n\"Trade";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	@RequestMapping(value = "/Class_payport", method = RequestMethod.POST)
	public ModelAndView Class_payport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Class_Pay_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Class_payDao.search_Class_pay_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CLASS PAY");
		TH.add("STATUS");
		String Heading = "\nClass_pay";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	@RequestMapping(value = "/record_office_report", method = RequestMethod.POST)
	public ModelAndView record_office_report(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Record_office_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = rec_dao.search_Record_Office_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("RECORD OFFICE");
		TH.add("SUS NO");
		TH.add("STATUS");
		String Heading = "\nRecord Office";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

	

	@RequestMapping(value = "/Apptreport", method = RequestMethod.POST)
	public ModelAndView Apptreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Appointment_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = aaptdao.Appt_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("APPIONTMENT");
		TH.add("CATEGORY");
		TH.add("STATUS");
		String Heading = "\n\"APPOINTMENT";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}

	@RequestMapping(value = "/Rankreport", method = RequestMethod.POST)
	public ModelAndView Rankreport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {
	
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Rank", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> C1list = rankDAO.Rank_Report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("RANK");
		TH.add("CATEGORY");
		TH.add("STATUS");
		String Heading = "\n\"RANK";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", C1list);
	}
	
	@RequestMapping(value = "/promoExamreportJCO", method = RequestMethod.POST)
	public ModelAndView promoExamreportJCO(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PromoExamJCOUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = pexamDao.PromoExamReportJCO();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PROMOTIONAL EXAM");
		TH.add("STATUS");
		String Heading = "\nPromotional Exam";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}


	@RequestMapping(value = "/Causeof_non_effreport_JCO", method = RequestMethod.POST)
	public ModelAndView Causeof_non_effreport_JCO(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_JCOUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CNEJcoDao.Cause_Of_Noneff_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CAUSES NAME");
		TH.add("TYPE OF OFFICER");
		TH.add("STATUS");
		String Heading = "\nCause of Non Effective";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Causeof_non_effreport_Civilian", method = RequestMethod.POST)
	public ModelAndView Causeof_non_effreport_Civilian(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Cause_of_non_eff_civilianUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CNECivilianDao.Cause_Of_Noneff_civilian_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CAUSES NAME");
		TH.add("TYPE OF CIVILIAN");
		TH.add("TYPE OF REGULAR/NON-REGULAR");
		TH.add("STATUS");
		
		String Heading = "\nCause of Non Effective Civilian";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/CadreReport", method = RequestMethod.POST)
	public ModelAndView CadreReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Cadre_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = CadreCivilianDao.Cadre_civilian_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CADRE");
		TH.add("STATUS");
		
		String Heading = "\nCadre Civilian";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	/*@RequestMapping(value = "/Gorkha_DomisileReport", method = RequestMethod.POST)
	public ModelAndView Gorkha_DomisileReport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Casuality2_Url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = Gorkha_Domisile_dao_JCO.Gorkha_Domisile_report();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("DOMICILE");
		TH.add("STATUS");
		
		String Heading = "\nGorkha Domisile";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}*/
	
	@RequestMapping(value = "/Pay_level_Report", method = RequestMethod.POST)
	public ModelAndView Pay_level_Report(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("pay_level_url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = pl_dao.search_Pay_LevelReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("PAY LEVEL");
		String Heading = "\nPay Level";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Category_Report", method = RequestMethod.POST)
	public ModelAndView Category_Report(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("category_url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = cat_dao.search_CategoryReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("CATEGORY");
		String Heading = "\nCategory";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	@RequestMapping(value = "/Disability_Report", method = RequestMethod.POST)
	public ModelAndView Disability_Report(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("disability_url", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		ArrayList<ArrayList<String>> CTlist = dis_dao.search_DisabilityReport();
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("DISABILITY");
		String Heading = "\nDisability";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	
	
}

