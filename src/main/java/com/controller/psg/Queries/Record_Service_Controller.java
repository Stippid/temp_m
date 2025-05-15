package com.controller.psg.Queries;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Queries.Record_ServiceDAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Record_Service_Controller {

	Psg_CommonController mcommon = new Psg_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	private Record_ServiceDAO RSDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Record_Service_Query", method = RequestMethod.GET)
	public ModelAndView Record_Service_Query(ModelMap Mmap, HttpSession sessionA,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Record_Service_Query", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getCommandList",m.getCommandDetailsList());
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
    	
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= bloodGP.getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps= bloodGP.getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps= bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn= bloodGP.getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps= bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn= bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde= bloodGP.getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps= bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn= bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
			}
		}
		 if(roleAccess.equals("Unit")){
			 String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
				
				List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
				roleFormationNo = formation.get(0);
				
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				
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
		Mmap.put("msg", msg);
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		return new ModelAndView("Record_Service_Query_Tiles");
	}
	
	 @RequestMapping(value = "/getRecord_ServiceReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getRecord_ServiceReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		 
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
		 
		 return RSDAO.getRecord_ServiceReportDataListDetails(startPage,pageLength,Search,orderColunm,orderType,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getRecord_ServiceTotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getRecord_ServiceTotalCount(HttpSession sessionUserId,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no){
	 	return RSDAO.getRecord_ServiceTotalCountDtl(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no);
	 }
	 
	 @RequestMapping(value = "/getpersonnel_noListApprovedReport", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_noListApprovedReport(String personnel_no, HttpSession sessionUserId,HttpServletRequest request) {
			
	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	    //try{
	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	            String roleType = sessionUserId.getAttribute("roleType").toString();
	            Query q= null;
	            String rsus=request.getParameter("roleSus");
	        
              
              
          	if (!roleSusNo.equals("")) {

          	   q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  p.unit_sus_no=:roleSusNo and (p.status='1' or p.status='5') and p.personnel_no  in "+
                       "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");

    			q.setParameter("roleSusNo", roleSusNo);

    		} else {
    			 q = sessionHQL.createQuery("select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p where  (p.status='1' or p.status='5') and p.personnel_no  in "+
                         "(select p.personnel_no from TB_CENSUS_DETAIL_Parent c where c.comm_id=p.id and c.status ='1' and (c.update_ofr_status='0' or c.update_ofr_status='1')) and upper(p.personnel_no)  like :personnel_no  order by p.personnel_no");
            
    		}
              
              
              
              //	q.setParameter("roleSusNo", roleSusNo); 
	            q.setParameter("personnel_no", personnel_no.toUpperCase()+"%");
	            @SuppressWarnings("unchecked")        
	            List<String> list = (List<String>) q.list();
	            tx.commit();	            
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
	                                encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
	                        } catch (IllegalBlockSizeException | BadPaddingException e) {
	                                e.printStackTrace();
	                        }
	                        String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
	                        FinalList.add(base64EncodedEncryptedCode);
	                }
	                FinalList.add(enckey + "4bsjyg==");
	                return FinalList;                   
	    	}
	 
	 @RequestMapping(value = "/Excel_Record_Service_query", method = RequestMethod.POST)
		public ModelAndView Excel_Command_Wise_STR_query(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1,@RequestParam(value = "msg", required = false) String msg) {
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Record_Service_Query", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 ArrayList<ArrayList<String>> Excel = RSDAO.Excel_Record_Service_Report();
			
			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("Command");
			TH.add("Corps/Area");
			TH.add("Div/ Sub Area");
			TH.add("Bde");
			TH.add("Unit");				
//			TH.add("SUS No");
			TH.add("Cadet No");
			TH.add("Personal No");				
			TH.add("Rank");
			TH.add("Name");
			
			String Heading = "\nService Record ";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
	}
