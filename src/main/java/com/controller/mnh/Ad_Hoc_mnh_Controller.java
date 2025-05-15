package com.controller.mnh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.BisagN.MISO.CommanController;
import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.ExportFile.PDF_Adhoc_Report;
import com.controller.ExportFile.PDF_Adhoc_mnh_qry;
import com.controller.ExportFile.PDF_Cause_of_Wastage;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.Ad_Hoc_mnh_Dao;
import com.dao.mnh.Ad_Hoc_mnh_Query_Dao;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.orbat.ReportsDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.ad_hoc.AdHocDAO;
import com.models.Tbl_CodesForm;
import com.models.psg.Master.TB_BANK;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})

public class Ad_Hoc_mnh_Controller {
	
	@Autowired
	Ad_Hoc_mnh_Dao ad;
	
	@Autowired
	Ad_Hoc_mnh_Query_Dao adq;
	@Autowired
	MNH_Common_DAO cd;
	
	@Autowired
	private ReportsDAO reportDAO;
	

	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Psg_CommanDAO pdao;
	
	Psg_CommonController mcommon = new Psg_CommonController();

	
	CommanController cc = new CommanController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	MNH_CommonController mcom = new MNH_CommonController();
	Blood_Group_Controller b = new Blood_Group_Controller();
	
	String adhoc = "";
	
	@RequestMapping(value = "/admin/ad_hoc_mnh_Url", method = RequestMethod.GET)
	 public ModelAndView ad_hoc_mnh_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ad_hoc_mnh_Url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		//String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		Mmap.put("getMedSystemCodePERSNL_PRE", mcom.getMedSystemCode("PERSNL_PRE", "", session));
		Mmap.put("getMedSystemCodePERSNL_SUF", mcom.getMedSystemCode("PERSNL_SUF", "", session));
		Mmap.put("getMedSystemCodeSERVICE", mcom.getMedSystemCode("SERVICE", "", session));
		Mmap.put("getMedSystemCodeCATEGORY", mcom.getMedSystemCode("CATEGORY", "", session));
		//Mmap.put("getMNHRank", mcom.getMNHRank("","", session));	
        Mmap.put("ml_6", mcom.getMedSystemCode("ADMSNTYPE", "", session));
        Mmap.put("ml_7", mcom.getMedSystemCode("DISPOSAL", "", session));
      //Mmap.put("ml_7", mcom.getMedSystemCode("DISEASE", "", session));
        Mmap.put("ml_2", mcom.getMedSystemCode("SEX", "", session));
        Mmap.put("ml_5", mcom.getMedrelationList("", session));
        Mmap.put("getMedDisease_Type", mcom.getMedDisease_Type ());
	//  Mmap.put("getMedSystemCode_SERVICE", mcommon.getServiceList());
	    Mmap.put("getColumnList", pdao.getColumnList());
	    Mmap.put("getMNHCommandName", cd.getMNHCommandName());
	
		
		 
	    
	   
	    
		 if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String formation_code = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",formation_code);	
					Mmap.put("getCommandList",comd);
					List<Tbl_CodesForm> corps= b.getFormationList("Corps",formation_code);
					Mmap.put("getCorpsList",corps);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Corps")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Division")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					List<Tbl_CodesForm> bde=b.getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Brigade")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
				}
			}
			 if(roleAccess.equals("Unit")){
				 String roleSusNo = session.getAttribute("roleSusNo").toString();
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					
					List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
					roleFormationNo = formation.get(0);
					
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
					
			 }
			if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
				List<Tbl_CodesForm> comd=all.getCommandDetailsList();
				Mmap.put("getCommandList",comd);
				String selectComd ="<option value=''>--Select--</option>";
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcomd", selectComd);
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
		 
	     //Mmap.put("select",select);
		 
			 
	//	 Mmap.put("get_operator_List", ad.get_operator_List());
		 Mmap.put("formation",reportDAO.getAllFormationList());
		 Mmap.put("getCommandList", all.getCommandDetailsList());
	//	 Mmap.put("get_catgory_List_mnh", ad.get_catgory_List_mnh());
	    
	    // Mmap.put("roleSusNo", roleSusNo);
	     Mmap.put("roleAccess", roleAccess);
	    // List<Map<String, Object>> list = ad.search_adhoc_dtl("","","","","","","");
		//	Mmap.put("list",list);
            Mmap.put("msg", msg);
		 return new ModelAndView("ad_hoc_mnh_Tiles");
	 }
	
	@RequestMapping(value = "/admin/Ad_hoc_mnh_Action", method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> Ad_hoc_mnh_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
            
            
            String create_query = request.getParameter("create_query").toLowerCase();
            
            ArrayList<String> get_adhoc = get_adhoc_function(request);
            
          Map<String, Object> columns = new LinkedHashMap<String, Object>();
            
//            columns.put("msg", get_adhoc);
            
            boolean update = create_query.contains("update");
            boolean delete = create_query.contains("delete");
            boolean drop = create_query.contains("drop");
             
            List<Map<String, Object>>         lista = new ArrayList<Map<String, Object>>();
            
//            lista.add(columns);
//            if(update==true || delete==true || drop==true) {
//                    
//            }
//            else { 
//               lista= ad.Search_Pers_det(get_adhoc);
//            }
            if(get_adhoc.get(0).equals("")) {
            	//lista= ad.Search_Pers_det(get_adhoc.get(1));
            }
            else {
            	columns.put("msg", get_adhoc);
            	lista.add(columns);
			}
            
            Mmap.put("list", lista); 
            Mmap.put("size", lista.size()); 
           
            return lista;
            
            //////////////////////////////

        }
   

	
	@SuppressWarnings("null")
	public ArrayList<String> get_adhoc_function(HttpServletRequest request) {
            
   
    ArrayList<String> retlist = new ArrayList<String>();
    retlist.add("");
    retlist.add("");
    //String category = request.getParameter("hd_category");
    String off_q = "";
    String jco_q = "";
    String or_q = "";
    String fi_q = "";
    
    
    String cont_comd = null;
    String cont_corps = "";
    String cont_div = "";
    String cont_bde = "";
    String unit_name = "";
    String ct_part_i_ii = "";
    String type_of_location = "";
    String civilian_type = "";
    
  //  int month = 0;
    String year = "";
    if(request.getParameter("hd_cont_comd")!="") {
    	cont_comd = request.getParameter("hd_cont_comd");
    }
    
    if(request.getParameter("hd_cont_corps")!="") {
    	cont_corps = request.getParameter("hd_cont_corps");
    }
    
    if(request.getParameter("hd_cont_div")!="") {
    	cont_div = request.getParameter("hd_cont_div");
    }
    
    if(request.getParameter("hd_cont_bde")!="") {
    	cont_bde = request.getParameter("hd_cont_bde");
    }
    
    if(request.getParameter("hd_unit_name")!="") {
    	unit_name = request.getParameter("hd_unit_name");
    }
    
    if(request.getParameter("ct_part_i_ii")!="") {
    	ct_part_i_ii = request.getParameter("ct_part_i_ii");
    }
    
    if(request.getParameter("type_of_location")!="") {
    	type_of_location = request.getParameter("type_of_location");
    }
    
//    if(request.getParameter("month")!="") {
//    	month = Integer.parseInt(request.getParameter("month"));
//    }
   /* if(request.getParameter("year")!="") {
    	year = request.getParameter("year");
    }*/
    
    if(request.getParameter("civilian_type")!="") {
    	civilian_type = request.getParameter("civilian_type");
    }
    
    //String cont_corps = request.getParameter("cont_corps");
    
     boolean ntd1 =false;
     boolean hdc1 =false;
     boolean hdc2 =false;
    String query="";
    String tables = request.getParameter("hd_table");
    String[] arrOfStrtable = tables.split(",");
    
    
    String[] arrOfauth = {"tb_olap_personal_details-a1","tb_olap_no_of_pers_auth_rank_wise-a19"};
    
    String[] arrOfheld = {"tb_olap_personal_details-a1","tb_olap_no_of_pers_held_rank_wise-a20"};
    
    String[] arrOfauth_held = {"tb_olap_personal_details-a1","tb_olap_no_of_pers_auth_rank_wise-a19","tb_olap_no_of_pers_held_rank_wise-a20"};
    
    int vk = arrOfStrtable.length;
    
  // String[] cate_array = category.split(",");
   
   
   

	 
		if (Arrays.equals(arrOfStrtable, arrOfauth_held) || Arrays.equals(arrOfStrtable, arrOfauth)   ||  Arrays.equals(arrOfStrtable, arrOfheld) ) {
	     	   ArrayList<String> msg = get_adhoc_auth_held_function(request);
	     	   return msg;
	         }
	        else
	        {
	                                   String hd_column = request.getParameter("hd_column");
	                                   
	                                   String[] arrOfStr = String.valueOf(hd_column).split(","); 
	                                   
	                                   if (tables.equals("")) {
	                                	   retlist.set(0, "Please Select Details with Aliase");
	                       	        	   return retlist;
	                       			    }
	                                  
	                                   if (hd_column.equals("")) {
	                                       retlist.set(0, "Please Select Fileds");
	                                       return retlist;
	                                    }
	                                                   query+="SELECT distinct ";
	                                           
	                                    for (int i = 0; i < arrOfStr.length; i++) {        
	                                                   
	                                                   			 ntd1 =arrOfStr[i].contains("date");
	                                                   			 boolean ntd11 =arrOfStr[i].contains("dob");
	                                                   			 boolean ntd12 =arrOfStr[i].contains("dt");
	                                                             boolean ntd2 =arrOfStr[i].contains("aadhar_no");
	                                                             boolean ntd22 =arrOfStr[i].contains("aadhar_card");
	                                                             boolean ntd3 =arrOfStr[i].contains("pan_no");
	                                                             boolean ntd31 =arrOfStr[i].contains("pan_card");
	                                                             boolean ntd4 =arrOfStr[i].contains("mobile_no");
	                                                             boolean ntd5 =arrOfStr[i].contains("gmail");
	                                                             
	                                                             if(ntd1==true && !arrOfStr[i].equals("a15.date_of_passing")){
	                                                                   String sp =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " ltrim(TO_CHAR("+arrOfStr[i]+",'DD-MON-YYYY'),'0') as "+sp;
	                                                                   query+=hd_column1+",";
	                                                                   }
	                                                             else   if(ntd11 == true || ntd12 == true){
	                                                                   String sp11 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " ltrim(TO_CHAR("+arrOfStr[i]+",'DD-MON-YYYY'),'0') as "+sp11;
	                                                                   query+=hd_column1+",";
	                                                                   }
	                                                         else if(ntd2==true ||ntd22==true){
	                                        	                    String sp1 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp1;   
	                                                                  query+=hd_column1+",";
	                                                              }
	                                                             
	                                                        else  if(ntd3==true || ntd31==true){
	                                                                   String sp2 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp2;                                                     
	                                                                   query+=hd_column1+",";
	                                                              }
	                                                        else  if(ntd4==true){
	                                                                   String sp3 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp3;                                                             
	                                                                   query+=hd_column1+",";           
	                                                                   }
	                                                        else  if(ntd5==true){
	                                                                   String sp4 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                                   String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp4;   
	                                                                   query+=hd_column1+",";
	                                                              }
	                                                                    else
	                                                                           {
	                                                                                   query+=arrOfStr[i]+",";
	                                                                           }
	                                                   }
	                           
	                           String q1  = query.substring(0,query.length() - 1);
	                   
	                           query= q1+" FROM";
	                           String an = "";
	                           
	                           for (int ik = 0; ik < vk; ik++) {
	                                    String hd_table1 = arrOfStrtable[ik];
	                           
	                                    String[]  t1 = hd_table1.split("-");
	                                   
	                                    String[] aliase = hd_table1.split("-");
	                                    
	                                    if(ik==0) {
	                                    	an=aliase[1];
	                                    }
	                                   
	                                    String join ="";
	                                	  join +=  "INNER JOIN";
	                                   
	                                   if(ik > 0)
	                                   {
	                           
	                                        String hd_table = arrOfStrtable[ik];
	                                        String[] t = hd_table.split("-");
	                                        String[] aliase1 = hd_table.split("-");
	              
	                                           int ip = ik-1;
	                                           
	                                           String hd_table_p = arrOfStrtable[ip];
	                                           String[] t1_p = hd_table_p.split("-");
	                                           String[] aliase_p = hd_table_p.split("-");
	                                       
	                                           query+=" "+ t[0]+" "+aliase[1] + " ON " ;
	                                           
	                                      
	                                           
	                                           //vk=vk-1;
	                                           if(ik!=(vk-1)){
	                                                   query+=" "+join;
	                                           }
	                                   }
	                                   else
	                                   {
	                                           query+=" "+t1[0]+" "+aliase[1];
	                                           
	                                           if(vk==1){
	                                           }
	                                           else{
	                                        		   query+=" "+join;
	                                           }
	                                   }
	                           }
	                           
	                            String q  = query;
	                            int flag1=0;
	                            int flag2 = 0;
	                            int count_filter = Integer.parseInt(request.getParameter("count_filter"));
	                            int count_sort = Integer.parseInt(request.getParameter("count_sort"));

	                                   for (int i = 1; i <= count_filter; i++) {
	                                           String hd_column_filter = request.getParameter("hd_Filed_List"+i);
	                                           
	                                           
	                                           String operator = "";
	                                           String value = "";
	                                           
	                                           String v = request.getParameter("ne_value"+i);
	                                           String vdt = request.getParameter("value_dt"+i);
	                                           String fdt = request.getParameter("from_date"+i);
	                                           String tdt = request.getParameter("to_date"+i);    
	                                           String operator1 = request.getParameter("operator"+i);
	                                           String ne_valuef = request.getParameter("ne_valuef"+i);
	                                           String ne_valuet = request.getParameter("ne_valuet"+i);
	                                           
	                                           String andor = "";
	                                           int flag=0;
	                                           boolean ag = false;
	                                           boolean mo = false;
	                                           boolean ye = false;
	                                           boolean br = false;
	                                           boolean by = false;
	                                           boolean du = false;
	                                           boolean fy = false;
	                                           boolean flep = false;
	                                           boolean ss = false;
	                                           boolean he = false;
	                                           boolean yos = false;
	                                           boolean py = false;
	                                          
	                                            if(hd_column_filter!=null && !hd_column_filter.equals(null) && hd_column_filter!="null" && !hd_column_filter.equals("null")){
	                                            	
	                                            	ag =hd_column_filter.contains("age");
	                                            	//mo =hd_column_filter.contains("month");
	                                            	//ye =hd_column_filter.contains("year");
	                                            	br =hd_column_filter.contains("bpet_result");
	                                            	by =hd_column_filter.contains("bpet_year");
	                                            	du =hd_column_filter.contains("duration");
	                                            	fy =hd_column_filter.contains("firing_year");
	                                            	flep =hd_column_filter.contains("foreign_language_exam_pass");
	                                            	ss =hd_column_filter.contains("shape_status");
	                                            	he =hd_column_filter.contains("height");
	                                            	yos =hd_column_filter.contains("year_of_service");
	                                            	py =hd_column_filter.contains("passing_year");
	                                            	// sana 
	                                            	
	                                            	  boolean hdc =hd_column_filter.contains("date");
	    	                                             if (!operator1.equals("0") && hd_column_filter.equals("")) {
	    	                                                retlist.set(0, "Please Select Filed Name");
	    	                                                     return retlist;
	    	                                                      }
	    	                                             if (hd_column_filter.equals("")) {
	    	                                                 retlist.set(0, "Please Select Filed Name");
	    	                                                     return retlist;
	    	                                                    }
	    	                                             if (operator1.equals("0")) {
	    	                                                 retlist.set(0, "Please Select Operator");
	    	                                                     return retlist;
	    	                                                    }
	    	                                             if (!operator1.equals("between")) {
	    	                                             
	    	                                             if(hdc == true) {
	    	                                             if (vdt.equals("") || vdt.equals("DD/MM/YYYY")) {
	    	                                                   retlist.set(0, "Please Enter Value Date");
	    	                                                       return retlist;
	    	                                                      }
	    	                                             }
	    	                                             else {
	    	                                                 if (v.equals("") || v.equals("DD/MM/YYYY")) {
	    	                                                    retlist.set(0, "Please Enter Value");
	    	                                                    return retlist;
	    	                                                 }
	    	                                             }
	    	                                             }
	    	                                             
	    	                                             if (operator1.equals("between")) {
	    	                                            	 
	    	                                            	 if(ag == true || mo == true || ye == true || br == true || by == true || du == true
	    	                                                		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true) {
	    		                                            	 if (ne_valuef.equals("")) {
	    			                                                 retlist.set(0, "Please Enter From Value");
	    			                                                     return retlist;
	    			                                                 }
	    			                                             
	    			                                             if (ne_valuet.equals("")) {
	    			                                                retlist.set(0, "Please Enter To Value");
	    			                                                       return retlist;
	    			                                              }
	    		                                             }
	    	                                                 else {
	    	                                                     if (fdt.equals("") || fdt.equals("DD/MM/YYYY")) {
	    	    	                                                 retlist.set(0, "Please Select From Date");
	    	    	                                                     return retlist;
	    	    	                                                 }
	    	    	                                             
	    	    	                                             
	    	    	                                             if (tdt.equals("") || tdt.equals("DD/MM/YYYY")) {
	    	    	                                                retlist.set(0, "Please Select To Date");
	    	    	                                                       return retlist;
	    	    	                                                 }
	    	                                                 }
	    	                                             }
	    	                                             
	    	                                             ///
	                                            	
	                                                    if(i==1){
	                                                           q+= " WHERE 1=1 ";
	                                                           flag=1;
	                                                 
	                                                           String als = "";
	                                                    	   for (int nk = 0; nk < vk; nk++) {
	                                                                   String hd_table11 = arrOfStrtable[nk];
	                                                          
	                                                                          String[]  t1 = hd_table11.split("-");
	                                                                  
	                                                                  
	                                                                   String[] al = hd_table11.split("-");
	                                                           
	                                                                	if (nk == 0) {
	                                        								als = al[1];

	                                        								if (cont_comd != null && cont_comd != "0") {
	                                        									q += " and " + al[1] + ".form_code_control like ";
	                                        								}
	                                        							}
	                                                                   }
	                                                    	    
	                                                    	   if((( cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0  && !cont_corps.equals("0")) && !cont_div.equals("0")) && !cont_bde.equals("0")) {
	                                                    		   q= q+ "'"+cont_bde+"%'";
	                                                    	   }
	                                                           
	                                                           else if((cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0 && !cont_corps.equals("0")) && !cont_div.equals("0")) {
	                                                           	
	                                                           	q= q+ "'"+cont_div+"%'";
	                                                    	   }
	                                                          
	                                                           else if((cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0) && !cont_corps.equals("0")) {
	                                                    		   q= q+ "'"+cont_corps+"%'";
	                                                    	   }
	                                                            
	                                                           else if(cont_comd != null && cont_comd !="0" ) {
	                                                        		   q= q+"'"+cont_comd+"%'";
	                                                    	   }
	                                                    	   
//	                                                           if(month!=0 ) {
//	                                                        	   q+= " and " + als+".month="+ "'"+ month+"'"; 
//	                                                           }
//	                                                           if(!year.equals("") && !year.equals(null)) {
//	                                                        	   q+= " and " + als+".year="+ "'"+ year+"'"; 
//	                                                           }
	                                                    	   
	                                                    	   if(!unit_name.equals("")) {
	                                                    		   q= q + " and  upper("+als +".unit_name) like "+ "upper('"+unit_name+"%')";
	                                                    	   }
	                                                    	   
//	                                                    	   if(!ct_part_i_ii.equals("0")) {
//	                                                    		   q= q + " and  upper("+als +".ct_part_i_ii)="+ "upper('"+ct_part_i_ii+"')";
//	                                                    	   }
//	                                                    	   
//	                                                    	   if(!type_of_location.equals("0")) {
//	                                                    		   q= q + " and  upper("+als +".type_of_location) = "+ "upper('"+type_of_location+"')";
//	                                                    	   }
//	                                                    	   
//	                                                    	   if(!civilian_type.equals("0")) {
//	                                                    		   q= q + " and  upper("+als +".civilian_status) = "+ "upper('"+civilian_type+"')";
//	                                                    	   }
	                                                   }
	                                            }
	                                            
	                                            if(hd_column_filter==null ) {
	                                         	   hd_column_filter="";
	                                        }
	                                            
	                                           
	                                           if(!operator1.equals("0")){
	                                                   operator+=operator1;
	                                           }
	                                           
	                                          
	                                           if(v!=""){
	                                        	   if(operator.equals("like")) {
	                                        		   value+= "'"+v+"%'";
	                                        	   }
	                                        	   else {
	                                        		   value+= "'"+v+"'";
	                                        	   }
	                                       }
	                                           
	                                           if(!vdt.equals("") && !vdt.equals("DD/MM/YYYY")){
	                                        	   hdc2 =hd_column_filter.contains("date");
	                                        	   
	                                        	   if(hdc2 == true) {
	                                        		   value+= "'"+vdt+"'";
	                                        	   }
	                                   }
	                                       if(fdt !="" || fdt !=null || tdt !="" || tdt !=null || v !="0"   ) {
	                                    	   
	                                    	   if(operator.equals("between")) {
	                                    		   if(ag == true || mo == true || ye == true || br == true || by == true || du == true
	                                                		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true) {
	                                    			   value+=    "'" + ne_valuef+"'"+ " AND "  +"'"+ne_valuet+"'";
	                                    		   }
	                                    		   else {
	                                    			   value+=    "'" + fdt+"'"+ " AND "  +"'"+tdt+"'";
	                                    		   }
	                                    	   } 
	                                       }
	                                           String andor1 =  request.getParameter("andor"+i);
	                                           
	                                           if(!andor1.equals("-1")){
	                                                   andor+=andor1;
	                                           }
	                                           
	                                           if(hd_column_filter!=null && !hd_column_filter.equals(null) && hd_column_filter!="null" && !hd_column_filter.equals("null") && !hd_column_filter.equals("") )
	                                           {
	                                        	   flag1=1;
	                                        	   //q+=" and ";
	                                        	   if(flag2==0){
	                                        		   q+=" and ";
	                                        		   flag2++;
	                                           }
	                                        	   
	                                        	   hdc1 =hd_column_filter.contains("date");
	                                        	   
	                                        	   
	                                        	   if(operator.equals("like")) {
	                                        		   q+= "upper("+ hd_column_filter +")"  +" " + operator + " upper(" +value+")" +" " + andor + " ";
	                                        	   }
	                                        	  
	                                        	   else if(hdc1 == true && !operator.equals("between")) {
	                                        		   q+= ""+ hd_column_filter +" " + operator +  "(" +value+")"  +" " + andor + " ";
	                                        	   }
	                                        	   else if(hdc1 == true && operator.equals("between")) {
	                                        		   q+= ""+ hd_column_filter +" " + operator +  " " + value +" "  +" " + andor + " ";
	                                        	   }
	                                        	   else if((ag == true || mo == true || ye == true || br == true || by == true || du == true
	                                                		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true) && operator.equals("between")) {
	                                        		   q+= ""+ hd_column_filter +" " + operator +  " " + value +" "  +" " + andor + " ";
	                                        	   }
	                                        	   else {
	                                        		   q+= "upper("+ hd_column_filter +")"  +" " + operator + " (upper(" +value+"))" +" " + andor + " ";
	                                        	   }
	                                           }
	                                   }
	                                   
	                                   if(flag1!=1)
	                                   {
	                                	  
	                                	  String als = "";
	                                	   for (int nk = 0; nk < vk; nk++) {
	                                               String hd_table11 = arrOfStrtable[nk];
	                                                      String[]  t1 = hd_table11.split("-");
	                                              
	                                              
	                                               String[] al = hd_table11.split("-");
	                                             
	                                               if(nk==0) {
	                                                   als= al[1];
	                                                   
	                                                   q+=  " where 1=1 ";
	                                                   
	                                                   if(cont_comd != null && cont_comd !="0" ) {
	                                                           q+= " and " + al[1]+".form_code_control like ";
	                                                   }
	                                           }
	                                               }
	                                	   if((( cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0  && !cont_corps.equals("0")) && !cont_div.equals("0")) && !cont_bde.equals("0")) {
	                                		   q= q+ "'"+cont_bde+"%'";
	                                	   }
	                                       
	                                       else if((cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0 && !cont_corps.equals("0")) && !cont_div.equals("0")) {
	                                       	
	                                       	q= q+ "'"+cont_div+"%'";
	                                	   }
	                                      
	                                       else if((cont_comd != null && cont_comd !="0" && Integer.parseInt(cont_comd)>0) && !cont_corps.equals("0")) {
	                                		   q= q+ "'"+cont_corps+"%'";
	                                	   }
	                                        
	                                       else if(cont_comd != null && cont_comd !="0" ) {
	                                    		   q= q+"'"+cont_comd+"%'";
	                                	   }
//	                                       if(month!=0 ) {
//	                                    	   q+= " and " + als+".month="+ "'"+ month+"'"; 
//	                                       }
//	                                       if(!year.equals("") && year !=null ) {
//	                                    		   q+= " and " + als+".year="+ "'"+ year+"'";
//	                                       }
	                                        if(unit_name!=null && !unit_name.equals("")) {
	                                 		   q= q + " and upper("+als +".unit_name) like "+ "upper('"+unit_name+"%')";
	                                 	   }
	                                        
//	                                        if(!ct_part_i_ii.equals("0")) {
//	                                 		   q= q + " and  upper("+als +".ct_part_i_ii)="+ "upper('"+ct_part_i_ii+"')";
//	                                 	   }
//	                                        if(!type_of_location.equals("0")) {
//	                                 		   q= q + " and  upper("+als +".type_of_location) = "+ "upper('"+type_of_location+"')";
//	                                 	   }
	                                       
//	                                        if(!civilian_type.equals("0")) {
//                                     		   q= q + " and  upper("+als +".civilian_status) = "+ "upper('"+civilian_type+"')";
//                                     	   }
	                                   }
	                                   
	                                   
	                                   for (int n = 1; n <=count_sort; n++) {
	                                           String hd_column_sort = request.getParameter("hd_short_List"+n);
	                                           String direction = "";
	                                           
	                                                   if(hd_column_sort!=null && !hd_column_sort.equals(null) && hd_column_sort!="null" && !hd_column_sort.equals("null")){
	                                                	   String direction1 =  request.getParameter("direction"+n);

	                                                       if (!direction1.equals("0") && hd_column_sort.equals("0")) {
	                                                                       retlist.set(0, "Please Select Order");
	                                                                        return retlist;
	                                                                       } 
	                                                       if (hd_column_sort.equals("0")) {
	                                                           retlist.set(0, "Please Select Order");
	                                                            return retlist;
	                                                           } 
	    													if (direction1.equals("0")) {
	    														retlist.set(0, "Please Select Direction");
	    														return retlist;
	    													}
	                                                	   
	                                                	   if(n==1){
	                                                           q+= " ORDER BY ";
	                                                           }
	                                                   }
	                                           
	                                           if(n>1){
	                                                   q+=",";
	                                           }
	           
	                                           String direction1 =  request.getParameter("direction"+n);
	                                           if(!direction1.equals("0")){
	                                                   direction+= direction1;
	                                           }
	                                           
	                                           if(hd_column_sort==null) {
	                                            	   hd_column_sort="";
	                                           }
	                                           
	                                           
	                                           //String spn1 =hd_column_sort.substring(hd_column_sort.indexOf(".") + 1);
	                                           q+= hd_column_sort + " "+ direction;
	                   
	                                   }
	                                   
	                        q = q.replace("&#40;", "(");
	                        q = q.replace("&#41;", ")");
	                        q = q.replace("&#39;", "'");
	                        q = q.replace("less_than", "<");
	                        q = q.replace("greater_than", ">");
	                        q = q.replace("equal_to", "=");
	                        q = q.replace("greater_or_equal", ">=");
	                        q = q.replace("less_or_equal", "<=");
	                        q = q.replace("not_equal", "not in");
	                        
	                        
	                        retlist.set(1, q);
	                        
	        }
		
		   
   
  
    return retlist;
    
}


	@SuppressWarnings("null")
	public ArrayList<String> get_adhoc_auth_held_function(HttpServletRequest request) {
            
   
     ArrayList<String> retlist = new ArrayList<String>();
     retlist.add("");
     retlist.add("");
    String category = request.getParameter("hd_category");
    String cont_comd = null;
    String cont_corps = "";
    String cont_div = "";
    String cont_bde = "";
    String unit_name = "";
    String ct_part_i_ii = "";
    String type_of_location = "";
    String civilian_type = "";
  //  int month = 0;
    String year = "";
    if(request.getParameter("hd_cont_comd")!="") {
    	cont_comd = request.getParameter("hd_cont_comd");
    }
    
    if(request.getParameter("hd_cont_corps")!="") {
    	cont_corps = request.getParameter("hd_cont_corps");
    }
    
    
    if(request.getParameter("hd_cont_div")!="") {
    	cont_div = request.getParameter("hd_cont_div");
    }
    
    if(request.getParameter("hd_cont_bde")!="") {
    	cont_bde = request.getParameter("hd_cont_bde");
    }
    
    if(request.getParameter("hd_unit_name")!="") {
    	unit_name = request.getParameter("hd_unit_name");
    }
    
//    if(request.getParameter("ct_part_i_ii")!="") {
//    	ct_part_i_ii = request.getParameter("ct_part_i_ii");
//    }
//    
//    if(request.getParameter("type_of_location")!="") {
//    	type_of_location = request.getParameter("type_of_location");
//    }
    
//    if(request.getParameter("month")!="") {
//    	month = Integer.parseInt(request.getParameter("month"));
//    }
//    if(request.getParameter("year")!="") {
//    	year = request.getParameter("year");
//    }
    
//    if(request.getParameter("civilian_type")!="") {
//    	civilian_type = request.getParameter("civilian_type");
//    }
    
     boolean ntd1 =false;
     boolean pa = false;
     boolean hdc1 =false;
     boolean hdc2 =false;
    String query="";
    String tables = request.getParameter("hd_table");
    String[] arrOfStrtable = tables.split(",");
    int vk = arrOfStrtable.length;
  
                               String hd_column = request.getParameter("hd_column");
                               
                               String[] arrOfStr = String.valueOf(hd_column).split(","); 
                               
                               if (tables.equals("")) {
                            	   retlist.set(0, "Please Select Details with Aliase");
                   	        	   return retlist;
                   			    }
                              
                               if (hd_column.equals("")) {
                                   retlist.set(0, "Please Select Fileds");
                                   return retlist;
                                   
                                }

                                               query+="SELECT distinct ";
                                       
                                for (int i = 0; i < arrOfStr.length; i++) {        
                                               
                                               			ntd1 =arrOfStr[i].contains("date");
                                                         boolean ntd2 =arrOfStr[i].contains("aadhar_no");
                                                         boolean ntd3 =arrOfStr[i].contains("pan_no");
                                                         boolean ntd31 =arrOfStr[i].contains("pan_card");
                                                         
                                                         boolean ntd4 =arrOfStr[i].contains("mobile_no");
                                                         boolean ntd5 =arrOfStr[i].contains("gmail");
                                                         
                                                         boolean ntd6 =arrOfStr[i].contains("auth");
    	                                                 boolean ntd7 =arrOfStr[i].contains("held");
    	                                                 
    	                                                 pa = arrOfStr[i].contains("parent_arm_service"); 
    	                                                 
    	                                                 if(ntd1==true && !arrOfStr[i].equals("a15.date_of_passing")){
                                                               String sp =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
                                                               String hd_column1 =  " ltrim(TO_CHAR("+arrOfStr[i]+",'DD-MON-YYYY'),'0') as "+sp;
                                                               query+=hd_column1+",";
                                                               }
                                                         
                                                  else if(ntd2==true){

                                    	                    String sp1 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
                                                               String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp1;   
                                                   query+=hd_column1+",";
                                                          }
                                                         
                                                  else  if(ntd3==true || ntd31==true){
                                                               String sp2 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
                                                               String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp2;                                                     
                                                               query+=hd_column1+",";
                                                          }
                                                    else  if(ntd4==true){
                                                            
                                                               String sp3 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
                                                               String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp3;                                                             
                                                               query+=hd_column1+",";           
                                                               }
                                                    else  if(ntd5==true){

                                                               String sp4 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
                                                               String hd_column1 =  " PGP_SYM_DECRYPT("+arrOfStr[i]+" ::bytea,current_setting('miso.version')) as "+sp4;   
                                                               query+=hd_column1+",";
                                                          }
                                                         
                                                    else  if(ntd6==true){

 	                                          	       
	                                                    String up1 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                    String hd_column1 =  " COALESCE(sum(distinct "+arrOfStr[i]+" :: integer),'0' ) as  "+up1;   
	                                                    query+=hd_column1+",";
	                                               
	                                                  }
	                                            else  if(ntd7==true){
	                                   	
	                                                 
	                                                  String up2 =arrOfStr[i].substring(arrOfStr[i].indexOf(".") + 1);
	                                                  String hd_column1 =   " COALESCE(sum(distinct "+arrOfStr[i]+" :: integer),'0' ) as  "+up2;   
	                                                  query+=hd_column1+",";
	                                            
	                                          }
                                                                else
                                                                       {
                                                                               query+=arrOfStr[i]+",";
                                                                               
                                                                       }
                                               }
       
                       
                       String q1  = query.substring(0,query.length() - 1);
               
                       query= q1+" FROM";
                       
                       for (int ik = 0; ik < vk; ik++) {
                                String hd_table1 = arrOfStrtable[ik];
                       
                                       String[]  t1 = hd_table1.split("-");
                               
                                String[] aliase = hd_table1.split("-");
                               
                                String join ="";
                            	  join +=  "LEFT JOIN";
                               
                               if(ik > 0)
                               {
                       
                                String hd_table = arrOfStrtable[ik];
                                    String[] t = hd_table.split("-");
                                    String[] aliase1 = hd_table.split("-");
          
                                       
                                       int ip = ik-1;
                                       
                                       String hd_table_p = arrOfStrtable[ip];
                                       String[] t1_p = hd_table_p.split("-");
                                       String[] aliase_p = hd_table_p.split("-");
                                       
                                       
                                       
                                       query+=" "+ t[0]+" "+aliase[1] + " ON " ;
                                       
                                       if(pa == true) {
    	                             	   
       	                                query+=aliase1[1]+".unit_sus_no=";
       	                                
       	                                query+= "a1.unit_sus_no";
       	                                }
       	                                
       	                                else
       	                                {
       	                             	   query+=aliase1[1]+".rank=";
       	                                    
       	                                    query+= "a1.rank";
       	                                }
                                       
                                    
                                       
                                       //vk=vk-1;
                                       if(ik!=(vk-1)){
                                               query+=" "+join;
                                       }
                                       
                               }
                               else
                               {
                                       query+=" "+t1[0]+" "+aliase[1];
                                       
                                       
                                       if(vk==1){
                                       }
                                       else{
                                    		   query+=" "+join;
                                       }
                               }
                       }
                       
                        String q  = query;
                        int flag1=0;
                        int flag2 = 0;
                        int count_filter = Integer.parseInt(request.getParameter("count_filter"));
                        int count_sort = Integer.parseInt(request.getParameter("count_sort"));

                               for (int i = 1; i <= count_filter; i++) {
                                       String hd_column_filter = request.getParameter("hd_Filed_List"+i);
                                       
                                       String operator = "";
                                       String value = "";
                                       
                                       String v = request.getParameter("ne_value"+i);
                                       String vdt = request.getParameter("value_dt"+i);
                                       String fdt = request.getParameter("from_date"+i);
                                       String tdt = request.getParameter("to_date"+i);    
                                       String operator1 = request.getParameter("operator"+i);
                                      
                                       String ne_valuef = request.getParameter("ne_valuef"+i);
                                       String ne_valuet = request.getParameter("ne_valuet"+i);
                                       
                                       String andor = "";
                                       int flag=0;
                                       
                                 	  boolean ag =hd_column_filter.contains("age");
                                 	 // boolean mo =hd_column_filter.contains("month");
                                 	//  boolean ye =hd_column_filter.contains("year");
                                 	  boolean br =hd_column_filter.contains("bpet_result");
                                 	  boolean by =hd_column_filter.contains("bpet_year");
                                 	  boolean du =hd_column_filter.contains("duration");
                                 	  boolean fy =hd_column_filter.contains("firing_year");
                                 	  boolean flep =hd_column_filter.contains("foreign_language_exam_pass");
                                 	  boolean ss =hd_column_filter.contains("shape_status");
                                 	  boolean he =hd_column_filter.contains("height");
                                 	  boolean yos =hd_column_filter.contains("year_of_service");
                                 	  boolean py =hd_column_filter.contains("passing_year");
                                        if(hd_column_filter!=null && !hd_column_filter.equals(null) && hd_column_filter!="null" && !hd_column_filter.equals("null")){
                                        	
                                        	
                                        	// sana 
                                        	
                                        	  boolean hdc =hd_column_filter.contains("date");
	                                             if (!operator1.equals("0") && hd_column_filter.equals("")) {
	                                                retlist.set(0, "Please Select Filed Name");
	                                                     return retlist;
	                                                      }
	                                             if (hd_column_filter.equals("")) {
	                                                 retlist.set(0, "Please Select Filed Name");
	                                                     return retlist;
	                                                    }
	                                             if (operator1.equals("0")) {
	                                                 retlist.set(0, "Please Select Operator");
	                                                     return retlist;
	                                                    }
	                                             if (!operator1.equals("between")) {
	                                             
	                                             if(hdc == true) {
	                                             if (vdt.equals("") || vdt.equals("DD/MM/YYYY")) {
	                                                   retlist.set(0, "Please Enter Value Date");
	                                                       return retlist;
	                                                      }
	                                             }
	                                             else {
	                                                 if (v.equals("") || v.equals("DD/MM/YYYY")) {
	                                                    retlist.set(0, "Please Enter Value");
	                                                    return retlist;
	                                                 }
	                                             }
	                                             }
	                                             
	                                             if (operator1.equals("between")) {
	                                            	 
	                                            	 if(ag == true ||   br == true || by == true || du == true
	                                                		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true) {
		                                            	 if (ne_valuef.equals("")) {
			                                                 retlist.set(0, "Please Enter From Value");
			                                                     return retlist;
			                                                 }
			                                             
			                                             if (ne_valuet.equals("")) {
			                                                retlist.set(0, "Please Enter To Value");
			                                                       return retlist;
			                                              }
		                                             }
	                                                 else {
	                                                     if (fdt.equals("") || fdt.equals("DD/MM/YYYY")) {
	    	                                                 retlist.set(0, "Please Select From Date");
	    	                                                     return retlist;
	    	                                                 }
	    	                                             
	    	                                             
	    	                                             if (tdt.equals("") || tdt.equals("DD/MM/YYYY")) {
	    	                                                retlist.set(0, "Please Select To Date");
	    	                                                       return retlist;
	    	                                                 }
	                                                 }
	                                             }
	                                             
	                                             ///
                                        	
                                        	
                                                if(i==1){
                                                       q+= " WHERE 1=1 ";
                                                       flag=1;
                                             
                                                       String als = "";
                                                	   for (int nk = 0; nk < vk; nk++) {
                                                               String hd_table11 = arrOfStrtable[nk];
                                                      
                                                                      String[]  t1 = hd_table11.split("-");
                                                              
                                                              
                                                               String[] al = hd_table11.split("-");
                                                               if (nk == 0) {
                                    								als = al[1];

                                    								if (cont_comd != "0") {
                                    									q += " and " + al[1] + ".form_code_control like ";
                                    								}
                                    							}
                                                               }
                                                	
                                                	    
                                                	   
                                                	   
                                                	   if((( cont_comd !="0" && Integer.parseInt(cont_comd)>0  && !cont_corps.equals("0")) && !cont_div.equals("0")) && !cont_bde.equals("0")) {
                                                		   q= q+ "'"+cont_bde+"%'";
                                                	   }
                                                       
                                                       else if((cont_comd !="0" && Integer.parseInt(cont_comd)>0 && !cont_corps.equals("0")) && !cont_div.equals("0")) {
                                                       	
                                                       	q= q+ "'"+cont_div+"%'";
                                                	   }
                                                      
                                                       else if((cont_comd !="0" && Integer.parseInt(cont_comd)>0) && !cont_corps.equals("0")) {
                                                		   q= q+ "'"+cont_corps+"%'";
                                                	   }
                                                        
                                                       else if(cont_comd !="0") {
                                                    		   q= q+"'"+cont_comd+"%'";
                                                	   }
//                                                       if(month!=0 ) {
//                                                    	   q+= " and " + als+".month="+ "'"+ month+"'"; 
//                                                       }
//                                                       if(!year.equals("") && !year.equals(null)) {
//                                                    	   q+= " and " + als+".year="+ "'"+ year+"'"; 
//                                                       }
                                                	   
                                                	   if(!unit_name.equals("")) {
                                                		   q= q + " and  upper("+als +".unit_name) like "+ "upper('"+unit_name+"%')";
                                                	   }
                                                	   
                                                	   if(!ct_part_i_ii.equals("0")) {
                                                 		   q= q + " and  upper("+als +".ct_part_i_ii)="+ "upper('"+ct_part_i_ii+"')";
                                                 	   }
                                                	   
                                                	   if(!type_of_location.equals("0")) {
                                                 		   q= q + " and  upper("+als +".type_of_location) = "+ "upper('"+type_of_location+"')";
                                                 	   }
                                                	   
                                                	   if(!civilian_type.equals("0")) {
                                                 		   q= q + " and  upper("+als +".civilian_status) = "+ "upper('"+civilian_type+"')";
                                                 	   }
                                                	   
                                                	   
                                               }
                                        }
                                        
                                        if(hd_column_filter==null ) {
                                     	   hd_column_filter="";
                                    }
                                        
                                       
                                       if(!operator1.equals("0")){
                                               operator+=operator1;
                                       }
                                       
                                      
                                       if(v!=""){
                                    	   if(operator.equals("like")) {
                                    		   value+= "'"+v+"%'";
                                    	   }
                                    	   else {
                                    		   value+= "'"+v+"'";
                                    	   }
                                           
                                   }
                                       if(!vdt.equals("") && !vdt.equals("DD/MM/YYYY")){
                                    	   hdc2 =hd_column_filter.contains("date");
                                    	   if(hdc2 == true) {
                                    		   value+= "'"+vdt+"'";
                                    	   }
                                    	    
                                       
                               }
                                   if(fdt !="" || fdt !=null || tdt !="" || tdt !=null || v !="0"   ) {
                                	   
                                	   if(operator.equals("between")) {
                                		   if(ag == true || br == true || by == true || du == true
                                              		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true)  {
                                			   value+=    "'" + ne_valuef+"'"+ " AND "  +"'"+ne_valuet+"'";
                                		   }
                                		   else {
                                			   value+=    "'" + fdt+"'"+ " AND "  +"'"+tdt+"'";
                                		   }
                                		   
                                	   } 
                                   }
                                       String andor1 =  request.getParameter("andor"+i);
                                       
                                       if(!andor1.equals("-1")){
                                               andor+=andor1;
                                       }
                                       
                                       if(hd_column_filter!=null && !hd_column_filter.equals(null) && hd_column_filter!="null" && !hd_column_filter.equals("null") && !hd_column_filter.equals("") )
                                       {
                                    	   flag1=1;
                                    	   //q+=" and ";
                                    	   if(flag2==0){
                                    		   q+=" and ";
                                    		   flag2++;
                                       }
                                    	   
                                    	   hdc1 =hd_column_filter.contains("date");
                                    	   
                                    	   
                                    	   if(operator.equals("like")) {
                                    		   q+= "upper("+ hd_column_filter +")"  +" " + operator + " upper(" +value+")" +" " + andor + " ";
                                    	   }
                                    	  
                                    	   else if(hdc1 == true && !operator.equals("between")) {
                                    		   q+= ""+ hd_column_filter +" " + operator +  "(" +value+")"  +" " + andor + " ";
                                    	   }
                                    	   else if(hdc1 == true && operator.equals("between")) {
                                    		   q+= ""+ hd_column_filter +" " + operator +  " " + value +" "  +" " + andor + " ";
                                    	   }
                                    	   else if((ag == true  || br == true || by == true || du == true
                                              		 || fy == true || flep == true || ss == true || he == true || yos == true || py == true)  && operator.equals("between")) {
                                    		   q+= ""+ hd_column_filter +" " + operator +  " " + value +" "  +" " + andor + " ";
                                    	   }
                                    	   else {
                                    		   q+= "upper("+ hd_column_filter +")"  +" " + operator + " (upper(" +value+"))" +" " + andor + " ";
                                    	   }
                                       }
                               }
                               
                               if(flag1!=1)
                               {
                            	  String als = "";
                            	   for (int nk = 0; nk < vk; nk++) {
                                           String hd_table11 = arrOfStrtable[nk];
                                                  String[]  t1 = hd_table11.split("-");
                                           String[] al = hd_table11.split("-");
                                           
                                           if(nk==0) {
                                               als= al[1];
                                               q+=  " where 1=1 ";
                                               
                                               if(cont_comd !=null && cont_comd !="0" ) {
                                                       q+=  " and " +al[1]+".form_code_control like ";
                                               }
                                       }
                                           }
                            	    
                            	   if(((cont_comd !=null && cont_comd !="0" && Integer.parseInt(cont_comd)>0  && !cont_corps.equals("0")) && !cont_div.equals("0")) && !cont_bde.equals("0")) {
                            		   q= q+ "'"+cont_bde+"%'";
                            	   }
                                   
                                   else if((cont_comd !=null && cont_comd !="0" && Integer.parseInt(cont_comd)>0 && !cont_corps.equals("0")) && !cont_div.equals("0")) {
                                   	q= q+ "'"+cont_div+"%'";
                            	   }
                                  
                                   else if((cont_comd !=null && cont_comd !="0" && Integer.parseInt(cont_comd)>0) && !cont_corps.equals("0")) {
                            		   q= q+ "'"+cont_corps+"%'";
                            	   }
                                   else if(cont_comd !=null && cont_comd !="0" ) {
                                		   q= q+"'"+cont_comd+"%'";
                            	   }
//                                   if(month!=0 ) {
//                                	   q+= " and " + als+".month="+ "'"+ month+"'"; 
//                                   }
//                                   if(!year.equals("") && !year.equals(null)) {
//                                		   q+= " and " + als+".year="+ "'"+ year+"'";
//                                   }
                                   
                                   if(!unit_name.equals("")) {
                            		   q= q + " and upper("+als +".unit_name) like "+ "upper('"+unit_name+"%')";
                            	   }
                                   
                                   if(!ct_part_i_ii.equals("0")) {
                             		   q= q + " and  upper("+als +".ct_part_i_ii)="+ "upper('"+ct_part_i_ii+"')";
                             	   }
                                   
                                   if(!type_of_location.equals("0")) {
                             		   q= q + " and  upper("+als +".type_of_location) = "+ "upper('"+type_of_location+"')";
                             	   }
                                   
                                   if(!civilian_type.equals("0")) {
                             		   q= q + " and  upper("+als +".civilian_status) = "+ "upper('"+civilian_type+"')";
                             	   }
                               }
                               
                               
                               for (int n = 1; n <=count_sort; n++) {
                                       String hd_column_sort = request.getParameter("hd_short_List"+n);
                                       String direction = "";
                                       
                                               if(hd_column_sort!=null && !hd_column_sort.equals(null) && hd_column_sort!="null" && !hd_column_sort.equals("null")){
                                            	   String direction1 =  request.getParameter("direction"+n);

                                                   if (!direction1.equals("0") && hd_column_sort.equals("0")) {
                                                                   retlist.set(0, "Please Select Order");
                                                                    return retlist;
                                                                   } 
                                                   if (hd_column_sort.equals("0")) {
                                                       retlist.set(0, "Please Select Order");
                                                        return retlist;
                                                       } 
													if (direction1.equals("0")) {
														retlist.set(0, "Please Select Direction");
														return retlist;
													}
                                                                       
                                            	   
                                            	   
                                            	   if(n==1){
                                                       q+= " ORDER BY ";
                                                       }
                                               }
                                               
                                       
                                       if(n>1){
                                               q+=",";
                                       }
       
                                       String direction1 =  request.getParameter("direction"+n);
                                       if(!direction1.equals("0")){
                                               direction+= direction1;
                                       }
                                       
                                       if(hd_column_sort==null) {
                                        	   hd_column_sort="";
                                       }
                                      // String spn1 =hd_column_sort.substring(hd_column_sort.indexOf(".") + 1);
                                       q+= hd_column_sort + " "+ direction;
               
                               }
                               
                               
                               for (int lk = 0; lk < vk; lk++) {
    	                    	   
    	                    	   String hd_table = arrOfStrtable[lk];
    	                           String[] t = hd_table.split("-");
    	                           
    	                    	   String f_a = arrOfStrtable[0].substring(arrOfStrtable[0].indexOf("-") + 1);
    	                     
    	                        	   if(lk == arrOfStrtable.length-1) {
    	                        		   
    	                        		   if(pa == true) {
    									     q+=" group by "+f_a+".parent_arm_service";
    	                        		      }
    	                        		   
    	                        		  else{
    	                        				 q+=" group by "+f_a+".rank";
    	                              		   }
    								}
    	                      }

                               
                    q = q.replace("&#40;", "(");
                    q = q.replace("&#41;", ")");
                    q = q.replace("&#39;", "'");
                    q = q.replace("less_than", "<");
                    q = q.replace("greater_than", ">");
                    q = q.replace("equal_to", "=");
                    q = q.replace("greater_or_equal", ">=");
                    q = q.replace("less_or_equal", "<=");
                    q = q.replace("not_equal", "not in");
    
                    retlist.set(1, q);
                     return retlist;
    
}
 
	
	 


	
	
	@RequestMapping(value = "/get_categorywise_tabel_List_mnh", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> get_categorywise_tabel_List_mnh(String t11) {

	 ArrayList<ArrayList<String>> list = adq.get_categorywise_tabel_List(t11);
	
	return list;
 }
	 @RequestMapping(value = "/get_selected_field_List_mnh", method = RequestMethod.POST)
		public @ResponseBody List<String> get_selected_field_List_mnh(String tn) {
		 List<String> list = adq.get_selected_field_List(tn);
		return list;
	 }
	 
//	 @RequestMapping(value = "get_catgory_List_mnh", method = RequestMethod.POST)
//		@ResponseBody
//		public List<Map<String, Object>> get_catgory_List_mnh() {
//			List<Map<String, Object>> list = ad.get_catgory_List();
//			return list;
//		}
//	
	 @RequestMapping(value = "/get_group_by_List_mnh", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> get_group_by_List_mnh() {
		 List<Map<String, Object>> list = adq.get_group_by_List();
		return list;
	 }
	 
	 @RequestMapping(value = "/getMedDiseaseName1", method = RequestMethod.POST)

		public @ResponseBody List<String> getMedDiseaseName1(String disease_type) {

			Session session = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = session.beginTransaction();

			Query q = session.createQuery(
					"select id,upper(disease_name)  as disease_name from Tb_Med_Daily_Surv_Disease_Mstr where disease_type=:disease_type  order by disease_name");

		
			q.setParameter("disease_type", disease_type);

			@SuppressWarnings("unchecked")

			List<String> list = (List<String>) q.list();
			
			tx.commit();

			session.close();

			return list;

		}

		@RequestMapping(value = "/admin/getSearch_ad_hocMnh", method = RequestMethod.POST)
		public ModelAndView getSearch_ad_hocMnh(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "gender1", required = false) String gender1 ,
				@RequestParam(value = "unit_name2", required = false) String unit_name2 ,
				@RequestParam(value = "category1", required = false) String category1,
				@RequestParam(value = "rank1", required = false) String rank1,
				@RequestParam(value = "relation1", required = false) String relation1 ,
				@RequestParam(value = "admsn_type1", required = false) String admsn_type1 ,
				@RequestParam(value = "disposal1", required = false) String disposal1,
				@RequestParam(value = "command1", required = false) String command1){
			
			
		
			String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("Bank", roleid);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if (request.getHeader("Referer") == null) {
//				msg = "";
//				return new ModelAndView("redirect:bodyParameterNotAllow");
//			} 
			    Mmap.put("getMedDisease_Type", mcom.getMedDisease_Type ());
			    Mmap.put("getTargetUnitsNameActiveList", cc.getTargetUnitsNameActiveList (session,""));
			    Mmap.put("getMedSystemCodeSERVICE", mcom.getMedSystemCode("SERVICE", "", session));
				Mmap.put("getMedSystemCodeCATEGORY", mcom.getMedSystemCode("CATEGORY", "", session));
			    Mmap.put("getParentArmList", mcommon.getParentArmList() );
		        Mmap.put("getTypeofRankList", mcommon.getTypeofRankList() );
		        Mmap.put("ml_6", mcom.getMedSystemCode("ADMSNTYPE", "", session));
		        Mmap.put("ml_7", mcom.getMedSystemCode("DISPOSAL", "", session));
		      //Mmap.put("ml_7", mcom.getMedSystemCode("DISEASE", "", session));
		        Mmap.put("ml_2", mcom.getMedSystemCode("SEX", "", session));
		        Mmap.put("ml_5", mcom.getMedrelationList("", session));
		     //   Mmap.put("getMedDiseaseType", mcom.getMedDiseaseType("", session));
			    Mmap.put("getColumnList", pdao.getColumnList());
			    Mmap.put("getMNHCommandName", cd.getMNHCommandName());
			 
			    List<Map<String, Object>> list = ad.search_adhoc_dtl(gender1,unit_name2,category1,rank1,relation1,admsn_type1,disposal1,command1);
			 
			             Mmap.put("unit_name2", unit_name2);
						 Mmap.put("gender1", gender1);
						 Mmap.put("category1", category1);
						 Mmap.put("rank1", rank1);
					
						
						Mmap.put("relation1", relation1);
						Mmap.put("admsn_type1", admsn_type1);
						Mmap.put("disposal1", disposal1);
						Mmap.put("command1", command1);
			    Mmap.put("list", list);
			    Mmap.put("list.size()", list.size());
			return new ModelAndView("ad_hoc_mnh_Tiles");
		}
	
		
		@RequestMapping(value = "/admin/ad_hoc_mnh_Query_Url", method = RequestMethod.GET)
		 public ModelAndView ad_hoc_mnh_Query_Url(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			String roleid = session.getAttribute("roleid").toString();
		/*	Boolean val = roledao.ScreenRedirect("ad_hoc_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}*/
			//String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			String roleFormationNo = session.getAttribute("roleFormationNo").toString();
			
			 Mmap.put("getColumnList", pdao.getColumnList());
			 //String select="<option value='0'>--Select--</option>";
			 
			 if(roleAccess.equals("Formation")) {
					if(roleSubAccess.equals("Command")) {
						String formation_code = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd= b.getFormationList("Command",formation_code);	
						Mmap.put("getCommandList",comd);
						List<Tbl_CodesForm> corps= b.getFormationList("Corps",formation_code);
						Mmap.put("getCorpsList",corps);
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectcorps",select);
						Mmap.put("selectDiv",select);
						Mmap.put("selectBde",select);
					}
					if(roleSubAccess.equals("Corps")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd= b.getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						List<Tbl_CodesForm> Bn=b.getFormationList("Division",cor);
						Mmap.put("getDivList",Bn);
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectDiv",select);
						Mmap.put("selectBde",select);
					}
					if(roleSubAccess.equals("Division")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
						Mmap.put("getDivList",Bn);
						
						List<Tbl_CodesForm> bde=b.getFormationList("Brigade",div);
						Mmap.put("getBdeList",bde);
						
						String select="<option value='0'>--Select--</option>";
						Mmap.put("selectBde",select);
					}
					if(roleSubAccess.equals("Brigade")) {
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
						Mmap.put("getDivList",Bn);
						
						String bde_code = roleFormationNo;
						List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
						Mmap.put("getBdeList",bde);
					}
				}
				 if(roleAccess.equals("Unit")){
					 String roleSusNo = session.getAttribute("roleSusNo").toString();
						Mmap.put("sus_no",roleSusNo);
						Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
						
						List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
						roleFormationNo = formation.get(0);
						
						String command = String.valueOf(roleFormationNo.charAt(0));
						List<Tbl_CodesForm> comd=b.getFormationList("Command",command);
						Mmap.put("getCommandList",comd);
						
						String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
						List<Tbl_CodesForm> corps=b.getFormationList("Corps",cor);
						Mmap.put("getCorpsList",corps);
						
						String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
						List<Tbl_CodesForm> Bn=b.getFormationList("Division",div);
						Mmap.put("getDivList",Bn);
						
						String bde_code = roleFormationNo;
						List<Tbl_CodesForm> bde = b.getFormationList("Brigade",bde_code);
						Mmap.put("getBdeList",bde);
						
				 }
				if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
					List<Tbl_CodesForm> comd=all.getCommandDetailsList();
					Mmap.put("getCommandList",comd);
					String selectComd ="<option value=''>--Select--</option>";
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcomd", selectComd);
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
			 
		     //Mmap.put("select",select);
			 
		//	 List<String> list = ad.getTableNameList();
			 Mmap.put("get_table_name_List_mnh", adq.get_table_name_List_mnh());
			 Mmap.put("get_operator_List", adq.get_operator_List());
			// Mmap.put("formation",reportDAO.getAllFormationList());
			// Mmap.put("getCommandList", all.getCommandDetailsList());
			// Mmap.put("get_catgory_List", ad.get_catgory_List());
		     Mmap.put("msg", msg);
		   //  Mmap.put("roleSusNo", roleSusNo);
		     Mmap.put("roleAccess", roleAccess);
			 return new ModelAndView("ad_hoc_mnh_Query_Tiles");
		 }
		
		@RequestMapping(value = "/admin/Ad_hoc_query_Action_mnh", method = RequestMethod.POST)
	    public @ResponseBody List<Map<String, Object>> Ad_hoc_Mnh_query_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
	                    throws ParseException {
	            
	            
	            String create_query = request.getParameter("create_query").toLowerCase();
	            
	            ArrayList<String> get_adhoc = get_adhoc_function(request);
	            
	          Map<String, Object> columns = new LinkedHashMap<String, Object>();
	            
//	            columns.put("msg", get_adhoc);
	            
	            boolean update = create_query.contains("update");
	            boolean delete = create_query.contains("delete");
	            boolean drop = create_query.contains("drop");
	             
	            List<Map<String, Object>>         lista = new ArrayList<Map<String, Object>>();
	            
//	            lista.add(columns);
//	            if(update==true || delete==true || drop==true) {
//	                    
//	            }
//	            else { 
//	               lista= ad.Search_Pers_det(get_adhoc);
//	            }
	            if(get_adhoc.get(0).equals("")) {
	            	lista= adq.Search_mnh_Pers_det(get_adhoc.get(1));
	            }
	            else {
	            	columns.put("msg", get_adhoc);
	            	lista.add(columns);
				}
	            
	            Mmap.put("list", lista); 
	            Mmap.put("size", lista.size()); 
	           
	            return lista;
	            
	            //////////////////////////////

	        }
		
		
		
		@ResponseBody
		@RequestMapping(value = "/Download_Adhoc_mnh_qry_Serialize", method = RequestMethod.POST)
		public String Download_Adhoc_mnh_qry_Serialize(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {
			String get_adhoc = get_adhoc_function(request).get(1);

			adhoc = get_adhoc;
			return "SET";
		}

	@ResponseBody
	@RequestMapping(value = "/Download_Adhoc_mnh_Data", method = RequestMethod.POST)
	public ModelAndView Download_Adhoc_mnh_Data(ModelMap Mmap, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) throws ParseException {
		String header_Intialize = request.getParameter("header1");

		String[] strAr1 = header_Intialize.split(",");

		String get_adhoc = "";
		if (!adhoc.equals(""))
			get_adhoc = adhoc;

		ArrayList<ArrayList<String>> pdfprint = adq.getAdhoc_mnh_qryreport(get_adhoc);

		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "Adhoc Report";
		int total = pdfprint.size();

		List<String> TH = new ArrayList<String>();

		TH.add("Ser No");
		for (int i = 0; i < strAr1.length; i++) {
			TH.add(strAr1[i]);
		}
		int column_total = TH.size();

		return new ModelAndView(new PDF_Adhoc_mnh_qry(TH, username, Heading, pdfprint, total, column_total),
				"userList", pdfprint);

	}

	// ------------------------------Himadri---------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/Download_Adhoc_mnh_qry_Excel", method = RequestMethod.POST)
	public ModelAndView Download_Adhoc_Data_Excel(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) throws ParseException {
		String header_Intialize = request.getParameter("header2");

		String[] strAr1 = header_Intialize.split(",");

		String get_adhoc = "";
		if (!adhoc.equals(""))
			get_adhoc = adhoc;

		ArrayList<ArrayList<String>> pdfprint = adq.getAdhoc_mnh_qryreport(get_adhoc);

		String username = sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "Adhoc Report";

		List<String> TH = new ArrayList<String>();

		TH.add("Ser No");
		for (int i = 0; i < strAr1.length; i++) {
			TH.add(strAr1[i]);
		}

		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", pdfprint);

	}
 
	
	   
}
