package com.controller.psg.Enrollment_Reports;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Enrollment_Reports.Details_of_Childern_Age_WiseDAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Details_of_Children_Age_WiseController {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	
	@Autowired
	Details_of_Childern_Age_WiseDAO childAge;
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();
	psg_jco_CommonController jcom = new psg_jco_CommonController();

	@RequestMapping(value = "/admin/Details_of_Children_Age_Wise")
	public ModelAndView Details_of_Children_Age_Wise(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
//		Mmap.put("msg", msg);
	    
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Details_of_Children_Age_Wise", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
	 String roleSusNo = session.getAttribute("roleSusNo").toString();
	 String roleAccess = session.getAttribute("roleAccess").toString();
	
		 if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}
		 
		 
		Date date = new Date();
	String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		//String roleAccess = sessionA.getAttribute("roleAccess").toString();
	String roleSubAccess = session.getAttribute("roleSubAccess").toString();
	String roleFormationNo = session.getAttribute("roleFormationNo").toString();
	String yyyy = new SimpleDateFormat("yyyy").format(date);
	String MM = new SimpleDateFormat("MM").format(date);
	String dd = new SimpleDateFormat("dd").format(date);
	String posn_date = dd+"/"+MM+"/"+yyyy;
	
	
	Mmap.put("posn_date",posn_date);
	Mmap.put("getChild_RelationshipList", mcommon.getChild_RelationshipList());
	
	if(roleAccess.equals("Formation")) {
		if(roleSubAccess.equals("Command")) {
			String formation_code = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
			Mmap.put("getCommandList",comd);
			List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
			Mmap.put("getCorpsList",corps);
			
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",formation_code,"","","","","",sessionA);		
			Mmap.put("list_serv", list_serv);*/
		}
		if(roleSubAccess.equals("Corps")) {
			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd=getFormationList("Command",command);
			Mmap.put("getCommandList",comd);
			
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
			Mmap.put("getCorpsList",corps);
			
			List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
			Mmap.put("getDivList",Bn);
			
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
			/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
			Mmap.put("list_serv", list_serv);*/
		}
		if(roleSubAccess.equals("Division")) {
			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd=getFormationList("Command",command);
			Mmap.put("getCommandList",comd);
			
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
			Mmap.put("getCorpsList",corps);
			
			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn=getFormationList("Division",div);
			Mmap.put("getDivList",Bn);
			
			List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
			Mmap.put("getBdeList",bde);
			
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectBde",select);
			/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
			Mmap.put("list_serv", list_serv);*/
		}
		if(roleSubAccess.equals("Brigade")) {
			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd=getFormationList("Command",command);
			Mmap.put("getCommandList",comd);
			
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
			Mmap.put("getCorpsList",corps);
			
			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn=getFormationList("Division",div);
			Mmap.put("getDivList",Bn);
			
			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
			Mmap.put("getBdeList",bde);
			/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
			Mmap.put("list_serv", list_serv);*/
		}
	}
	 if(roleAccess.equals("Unit")){
		 //String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("unit_sus_no",roleSusNo);
			Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			
			List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
			roleFormationNo = formation.get(0);
			
			String command = String.valueOf(roleFormationNo.charAt(0));
			List<Tbl_CodesForm> comd=getFormationList("Command",command);
			Mmap.put("getCommandList",comd);
			
			String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
			List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
			Mmap.put("getCorpsList",corps);
			
			String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
			List<Tbl_CodesForm> Bn=getFormationList("Division",div);
			Mmap.put("getDivList",Bn);
			
			String bde_code = roleFormationNo;
			List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
			Mmap.put("getBdeList",bde);
			/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
			Mmap.put("list_serv", list_serv);*/
	 }
	if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
		List<Tbl_CodesForm> comd=m.getCommandDetailsList();
		Mmap.put("getCommandList",comd);
		String selectComd ="<option value=''>--Select--</option>";
		String select="<option value='0'>--Select--</option>";
		Mmap.put("selectcomd", selectComd);
		Mmap.put("selectcorps",select);
		Mmap.put("selectDiv",select);
		Mmap.put("selectBde",select);
	}
	
	/*String  roleid = sessionA.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("OrbatDetails_Report_Formation", roleid);	
	if(val == false) {
		return new ModelAndView("AccessTiles");
	}*/

	
	Mmap.put("msg", msg);
		 
	Mmap.put("date", date1);	
	Mmap.put("msg", msg);

		return new ModelAndView("Details_of_Children_Age_Wise_tiles");
		
	}
	
	
	
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}
	
	@RequestMapping(value = "/details_of_children_tablecount", method = RequestMethod.POST)
	public @ResponseBody long details_of_children_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no/*,String user_role_id*/,String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		
		return childAge.getdetails_of_children_tablecount(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde, unit_name, unit_sus_no
				/*, user_role_id*/, from_date, to_date, posn_date, typeID, gender);
	}
	
	
	@RequestMapping(value = "/details_of_children_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> details_of_children_table(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cont_comd,String cont_corps,String cont_div,String cont_bde, String unit_name,String unit_sus_no,
			/*String user_role_id,*/String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
					cont_comd = fcode_comd;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_comd+cont_bde.substring(1, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_comd+cont_div.substring(1, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
				    		}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_comd;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Corps")) {
					String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					cont_corps = fcode_corps;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_corps+cont_bde.substring(3, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_corps+cont_div.substring(3, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_corps;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_corps;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Division")) {
					String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					cont_div = fcode_div;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_div+cont_bde.substring(6, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_div;
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_div;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_div;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Brigade")) {
					cont_bde = roleFormationNo;
				}
			}
		return childAge.getdetails_of_children_table(startPage, pageLength, Search, orderColunm, orderType,sessionUserId, cont_comd, cont_corps, cont_div, cont_bde, 
				unit_name, unit_sus_no, /*user_role_id,*/from_date, to_date, posn_date, typeID, gender);
	}
	
	
	
	
	@RequestMapping(value = "/details_of_children_tablecountsum", method = RequestMethod.POST)
	public @ResponseBody long details_of_children_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde, String unit_name,String unit_sus_no, 
			String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		
		return childAge.getdetails_of_children_tablecountsum(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, gender);
	}


	@RequestMapping(value = "/details_of_children_tablesum", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> details_of_children_tablesum(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cont_comd,String cont_corps,String cont_div,String cont_bde, String unit_name,String unit_sus_no
			/*String user_role_id,*/, String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
			
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
					cont_comd = fcode_comd;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_comd+cont_bde.substring(1, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_comd+cont_div.substring(1, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
				    		}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_comd;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Corps")) {
					String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					cont_corps = fcode_corps;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_corps+cont_bde.substring(3, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_corps+cont_div.substring(3, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_corps;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_corps;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Division")) {
					String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					cont_div = fcode_div;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_div+cont_bde.substring(6, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_div;
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_div;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_div;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Brigade")) {
					cont_bde = roleFormationNo;
				}
			}
		return childAge.getdetails_of_children_tablesum(startPage, pageLength,Search,orderColunm, orderType,sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, gender);
	}
	
	@RequestMapping(value = "/details_of_children_jco_tablecountsum", method = RequestMethod.POST)
	public @ResponseBody long details_of_children_jco_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String cont_comd,String cont_corps,String cont_div,String cont_bde, String unit_name,String unit_sus_no, 
			String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		
		return childAge.getdetails_of_children_jco_tablecountsum(Search, orderColunm, orderType, sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, gender);
	}


	@RequestMapping(value = "/details_of_children_jco_tablesum", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> details_of_children_jco_tablesum(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cont_comd,String cont_corps,String cont_div,String cont_bde, String unit_name,String unit_sus_no
			/*String user_role_id,*/, String from_date, String to_date, String posn_date, String typeID, String gender) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
			
			if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
					cont_comd = fcode_comd;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_comd+cont_bde.substring(1, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_comd+cont_div.substring(1, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
				    		}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_comd;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Corps")) {
					String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					cont_corps = fcode_corps;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_corps+cont_bde.substring(3, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_corps+cont_div.substring(3, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_corps;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_corps;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Division")) {
					String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					cont_div = fcode_div;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_div+cont_bde.substring(6, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_div;
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_div;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_div;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Brigade")) {
					cont_bde = roleFormationNo;
				}
			}
		return childAge.getdetails_of_children_jco_tablesum(startPage, pageLength,Search,orderColunm, orderType,sessionUserId, cont_comd, cont_corps, cont_div, cont_bde,
				 unit_name, unit_sus_no, from_date, to_date, posn_date, typeID, gender);
	}
	
}