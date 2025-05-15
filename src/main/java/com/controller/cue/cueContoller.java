package com.controller.cue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.cue.CopyWE_PE_DAO;
import com.dao.cue.Copy_WE_PE_DAOImpl;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.Cue_wepe_conditionDAOImpl;
import com.dao.cue.IncrementDecrementDAO;
import com.dao.cue.WePeInceDecrTransportFootnoteDAO;
import com.dao.cue.ceoDAO;
import com.dao.cue.cueStandardEntitlementTransportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.CUE_TB_MISO_CES;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_PSG_RANK_APP_MASTER;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.T_Domain_Value;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class cueContoller {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private IncrementDecrementDAO masterDAO1;
	
	@Autowired
	RoleBaseMenuDAO roleDAO;
	
	CopyWE_PE_DAO copyWePeDAO = new Copy_WE_PE_DAOImpl();
	
	@Autowired
	Cue_wepe_conditionDAO cuewepe = new Cue_wepe_conditionDAOImpl();
	
	@Autowired
	private Cue_wepe_conditionDAO masterDAO;
	
	@Autowired
	private ceoDAO cdao;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	cueStandardEntitlementTransportDAO cueTransport;
	
	@Autowired
	WePeInceDecrTransportFootnoteDAO footNoteTransDAO;
	
	ValidationController validation = new ValidationController();
	
    @RequestMapping(value = "/upload_WE_PE_Super_Pers", method = RequestMethod.GET)
	public ModelAndView upload_WE_PE_Super_Pers(String subModule1,ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
    	String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roleDAO.ScreenRedirect("upload_WE_PE_Super_Pers", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
//		if(roleAccess == "Line_dte"  && roleSubAccess == "Arm" ){		
//			Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
//		}else {
//			Mmap.put("getArmNameList", getArmNameList());
//		}	
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	 if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
			
			//Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
		
			Mmap.put("getArmNameList",getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			//Mmap.put("getArmNameList", getArmNameList());
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("roleid", roleid);
		Mmap.put("wepe", "1");  // for PERSONNEL value is 1
	//	Mmap.put("getsponserListCue", getsponserListCue());
		return new ModelAndView("upload_WE_PE_Super_Tiles");
	}
	
    @RequestMapping(value = "/upload_WE_PE_Super_Wea", method = RequestMethod.GET)
	public ModelAndView upload_WE_PE_Super_Wea(String subModule1,ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roleDAO.ScreenRedirect("upload_WE_PE_Super_Wea", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
		
	
	 if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
			
			//Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
		
			Mmap.put("getArmNameList",getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			//Mmap.put("getArmNameList", getArmNameList());
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("roleid", roleid);
		Mmap.put("wepe", "3");  // for WEAPON value is 3
		Mmap.put("getsponserListCue", getsponserListCue());
		// Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		return new ModelAndView("upload_WE_PE_Super_Tiles");
	}

	
	@RequestMapping(value = "/upload_WE_PE_Super_Trans", method = RequestMethod.GET)
	public ModelAndView upload_WE_PE_Super_Trans(String subModule1,ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roleDAO.ScreenRedirect("upload_WE_PE_Super_Trans", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
//		if(roleAccess == "Line_dte"  && roleSubAccess == "Arm" ){		
//			Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
//		}else {
//			Mmap.put("getArmNameList", getArmNameList());
//		}
		
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
	 if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
			
			//Mmap.put("getArmNameList",getArmNameListParameter(roleArmCode));
		
			Mmap.put("getArmNameList",getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			//Mmap.put("getArmNameList", getArmNameList());
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("msg", msg);
		Mmap.put("roleid", roleid);
		Mmap.put("wepe", "2"); // for TRANSPORT value is 2
	//	Mmap.put("getsponserListCue", getsponserListCue());
		return new ModelAndView("upload_WE_PE_Super_Tiles");
	}	

	@RequestMapping(value = "/miso_cue_we_pe_fe_superAction", method = RequestMethod.POST)
	public ModelAndView miso_cue_we_pe_fe_superAction(@ModelAttribute("miso_cue_we_pe_fe_superCMD") CUE_TB_MISO_WEPECONDITIONS obj_cue_wepe,			 
			HttpServletRequest request,ModelMap model,HttpSession session) {		
			String wepetype=request.getParameter("setTypeOnclick");
			String roleType =session.getAttribute("roleType").toString();
			int roleid=Integer.parseInt(request.getParameter("getroleid"));
			obj_cue_wepe.setRoleid(roleid);	
			String username = session.getAttribute("username").toString();
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				String we_pe_no =request.getParameter("we_pe_no");
				String we_pe=request.getParameter("we_pe");
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx0 = sessionHQL.beginTransaction();
				
				try
				{
				 if(we_pe == ""  || we_pe == null || we_pe == "null" || we_pe.equals(null) )
					{
						model.put("msg", "Please Select WE/PE");
						
						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}						
					}	
				if(validation.checkWETypeLength(we_pe)  == false){
					model.put("msg",validation.wetypeMSG);

					if(wepetype.equals("1")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
					}else if(wepetype.equals("2")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
					}else if(wepetype.equals("3")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
					}	
				}
				 if(obj_cue_wepe.getWe_pe_no() == "")
					{
						model.put("msg", "Please Enter WE/PE No");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				 if(validation.checkWepeLength(obj_cue_wepe.getWe_pe_no())  == false){
						model.put("msg",validation.wepenoMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				 if(obj_cue_wepe.getUploaded_wepe()== "")
					{
						model.put("msg", "Please Enter Uploaded WE/PE No");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				 if(validation.checkWepeLength(obj_cue_wepe.getWe_pe_no())  == false){
						model.put("msg",validation.upwepenoMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				String answer = request.getParameter("answer");
				if(answer==null)
				{
					model.put("msg", "Please select New Document");

					if(wepetype.equals("1")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
					}else if(wepetype.equals("2")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
					}else if(wepetype.equals("3")) {
						return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
					}	
				}
				 if(answer.equals("No"))
				 {
					 if(obj_cue_wepe.getSuprcdd_we_pe_no().equals(""))
					 {
						 model.put("msg", "Please Select Superseeded Document No");

							if(wepetype.equals("1")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
							}else if(wepetype.equals("2")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
							}else if(wepetype.equals("3")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
							}	
					 }
					 
					 if(validation.checkWepeLength(obj_cue_wepe.getSuprcdd_we_pe_no())  == false){
							model.put("msg",validation.supWepenoMSG);

							if(wepetype.equals("1")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
							}else if(wepetype.equals("2")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
							}else if(wepetype.equals("3")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
							}	
						}
				 }
				 if(obj_cue_wepe.getTable_title().equals(""))
				 {
					 model.put("msg", "Please Enter Table_title");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				 }
				 if(validation.checkWepetabletittleLength(obj_cue_wepe.getTable_title())  == false){
						model.put("msg",validation.wepetitleMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				 if(obj_cue_wepe.getEff_frm_date().equals(""))
				 {
					 model.put("msg", "Please Select Effective From Date");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				 }
				 if(validation.checkDateLength(obj_cue_wepe.getEff_frm_date())  == false){
						model.put("msg",validation.dateMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				}
				 if(obj_cue_wepe.getEff_to_date().equals(""))
				 {
					 model.put("msg", "Please Select Effective To Date");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				 }
				 if(validation.checkDateLength(obj_cue_wepe.getEff_to_date())  == false){
						model.put("msg",validation.dateMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				}
				 if( obj_cue_wepe.getDoc_type().equals("0"))
					{
						model.put("msg", "Please Select Document Type");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
				 if(validation.checkModificationLength(obj_cue_wepe.getDoc_type())  == false){
					 	model.put("msg",validation.secclassMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
				 }	
					if( obj_cue_wepe.getArm().equals("0"))
					{
						model.put("msg", "Please Select Arm");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
					String arm_code = String.format("%04d", Integer.parseInt(obj_cue_wepe.getArm()));
					if(validation.checkArmCodeLength(arm_code)  == false){
				 		model.put("msg",validation.arm_codeMSG);

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
					if(obj_cue_wepe.getSponsor_dire().equals("0"))
					{
						model.put("msg", "Please Select Sponsor Directorate");

						if(wepetype.equals("1")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
						}else if(wepetype.equals("2")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
						}else if(wepetype.equals("3")) {
							return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
						}	
					}
					 if(validation.checkSponsorDireLength(obj_cue_wepe.getSponsor_dire())  == false){
							model.put("msg",validation.spodireMSG);

							if(wepetype.equals("1")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
							}else if(wepetype.equals("2")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
							}else if(wepetype.equals("3")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
							}	
					}	
					 if(wepetype.equals("1")) {
						 String training_capacity =  Double.toString(obj_cue_wepe.getTraining_capacity());
						 if(validation.checkAuth_amtLength(training_capacity)  == false){
								model.put("msg",validation.auth_amtMSG);

								if(wepetype.equals("1")) {
									return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
								}else if(wepetype.equals("2")) {
									return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
								}else if(wepetype.equals("3")) {
									return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
								}	
						 }
					 }
					 if(validation.checkLocationLength(obj_cue_wepe.getUnit_category())  == false){
							model.put("msg",validation.unitcatMSG);

							if(wepetype.equals("1")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
							}else if(wepetype.equals("2")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
							}else if(wepetype.equals("3")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
							}	
					 }	
					 if(validation.checkRemarksLength(obj_cue_wepe.getRemarks())  == false){
							model.put("msg",validation.remarksMSG);

							if(wepetype.equals("1")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Pers");
							}else if(wepetype.equals("2")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Trans");
							}else if(wepetype.equals("3")) {
								return new ModelAndView("redirect:upload_WE_PE_Super_Wea");
							}	
					 }	
			else 
			{
				obj_cue_wepe.setStatus("0");
			}
				Boolean e = checkDetailsOfWePecondition(we_pe_no,roleid);
				if(e.equals(false)) {
					obj_cue_wepe.setCreated_by(username);
					obj_cue_wepe.setCreated_on(creadtedate);
					obj_cue_wepe.setType(wepetype);
					Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
					sessionHQL1.beginTransaction();
					sessionHQL1.save(obj_cue_wepe);
					sessionHQL1.getTransaction().commit();
					sessionHQL1.close();
					model.put("msg", "Data saved Successfully");
				}
				else {
					model.put("msg", "Data Already Exists!");
					}
				}	
				catch (Exception e) {
					sessionHQL.getTransaction().rollback();
					
				}
					String p= request.getParameter("setTypeOnclick");
					model.put("wepe", p);
					List<Map<String, Object>> list = masterDAO.AttributeReportSearchWePecondition1("",we_pe_no,we_pe,"","","","","");
					model.put("list", list);
					model.put("listsize", list.size());
					model.put("we_pe01", we_pe);
					model.put("we_pe_no1", we_pe_no);
					
					model.put("getsponserListCue", getsponserListCue());
					String roleAccess = session.getAttribute("roleAccess").toString();
					String roleSubAccess = session.getAttribute("roleSubAccess").toString();
					String roleSusNo = session.getAttribute("roleSusNo").toString();
					String roleArmCode = session.getAttribute("roleArmCode").toString();
					 if(roleAccess.equals("Line_dte") && roleSubAccess.equals("Arm"))	{
						 model.put("getArmNameList",getArmNameLine(roleSusNo));
						 model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
						}else {
							String select="<option value='0'>--Select--</option>";
							model.put("selectLine_dte",select);
							model.put("selectArmNameList",select);
							model.put("getArmNameList", getArmNameList());
							model.put("getLine_DteList",roledao.getLine_DteList(""));
						}
					
			  return new ModelAndView("upload_WE_PE_Super_Tiles");							
		}
			
			
	@RequestMapping(value = "/miso_cue_comp_equip_schedule", method = RequestMethod.GET)
	public ModelAndView miso_cue_comp_equip_schedule(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();	
		Boolean val = roleDAO.ScreenRedirect("miso_cue_comp_equip_schedule", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("miso_cue_comp_equip_schedule_Tiles");
	}
	
	@RequestMapping(value = "/upload_WE_PE", method = RequestMethod.GET)
	public ModelAndView upload_WE_PE(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("upload_WE_PETiles");
	}
	
	@SuppressWarnings("unchecked")
	public Boolean checkDetailsOfWePecondition(String we_pe_no,int roleid) {				
		String hql="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no and roleid=:roleid";
		List<CUE_TB_MISO_WEPECONDITIONS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("roleid", roleid);
			users = (List<CUE_TB_MISO_WEPECONDITIONS>) query.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
		} 		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/miso_cue_comp_equip_scheduleAction", method=RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("miso_cue_comp_equip_scheduleCMD") CUE_TB_MISO_CES obj_ces_equip,
		HttpServletRequest request,ModelMap model,HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String username = session.getAttribute("username").toString();
		int roleid = (Integer)session.getAttribute("roleid");			
		obj_ces_equip.setRoleid(roleid);
		obj_ces_equip.setItem_seq_no(request.getParameter("item_seq_no"));
		obj_ces_equip.setCes_cces_name(request.getParameter("ces_cces_name"));
		String we_pe=request.getParameter("ces_cces");
		
		String ces_cces_no=request.getParameter("ces_cces_no");
		String item_seq_no=request.getParameter("item_seq_no");
		String ces_cces_name=request.getParameter("ces_cces_name");
		String ces_cces=request.getParameter("ces_cces");
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx0 = sessionHQL.beginTransaction();
			
			try
			{
				if(we_pe == ""  || we_pe == null || we_pe == "null" || we_pe.equals(null) )
				{
					model.put("msg", "Please Select CES Type");
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				if(validation.checkWETypeLength(we_pe)  == false){
			 		model.put("msg",validation.cestypeMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				if(obj_ces_equip.getCes_cces_no().equals("") || obj_ces_equip.getCes_cces_no() == "")
			 	{
					model.put("msg", "Please Enter CES/CCES No");
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
			 	}
				if(validation.checkScenarioLength(obj_ces_equip.getCes_cces_no())  == false){
			 		model.put("msg",validation.cesnoMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				if(validation.checkFormationLength(obj_ces_equip.getCes_cces_name())  == false){
			 		model.put("msg",validation.cesNameMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				
				if(validation.checkDateLength(obj_ces_equip.getEfctiv_date())  == false){
			 		model.put("msg",validation.dateMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				if(validation.checkDateLength(obj_ces_equip.getExpiry_date())  == false){
			 		model.put("msg",validation.dateMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				if(validation.checkFormationLength(obj_ces_equip.getItem_seq_no())  == false){
			 		model.put("msg",validation.nomenMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				}
				
				String auth_amt =  Double.toString(obj_ces_equip.getAuth_proposed());
				if(validation.checkAuth_amtLength(auth_amt)  == false){
			 		model.put("msg",validation.auth_amtMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				} 
				
				if(validation.checkRemarksLength(obj_ces_equip.getRemarks())  == false){
			 		model.put("msg",validation.remarksMSG);
					return new ModelAndView("redirect:miso_cue_comp_equip_schedule");
				 }
				 Boolean e = isdetailces_rank_exits( ces_cces_no,item_seq_no, ces_cces_name, ces_cces);
				 if(e.equals(false)) {	
					 
					 obj_ces_equip.setCreation_by(username);
					 obj_ces_equip.setCreation_date(date);				 
					 int did = (Integer) sessionHQL.save(obj_ces_equip);
					 sessionHQL.getTransaction().commit();		
					 sessionHQL.close();			
					 model.put("msg", "Data saved Successfully");
					}
					else {
					model.put("msg", "Data Already Exists!");
					}
			}			
			catch (Exception e) {
				sessionHQL.getTransaction().rollback();				
			}
	
			List<Map<String, Object>> list =cdao.getcceBySearch(ces_cces, ces_cces_no, "","")  ;
			model.put("ces_cces1", ces_cces);
			model.put("ces_cces_no1", ces_cces_no);
			model.put("ces_namem1", ces_cces_name);
			model.put("list", list);
			model.put("list.size()", list.size());
		return new ModelAndView("miso_cue_comp_equip_schedule_Tiles");			
	}	
	
	
	@SuppressWarnings("unchecked")
	public Boolean isdetailces_rank_exits(String ces_cces_no,String item_seq_no,String ces_cces_name,String ces_cces) {
		String hql=" FROM CUE_TB_MISO_CES m where m.ces_cces_no=:ces_cces_no and m.item_seq_no=:item_seq_no and m.ces_cces_name = :ces_cces_name and m.ces_cces = :ces_cces ";
		List<CUE_TB_MISO_CES> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("ces_cces_no", ces_cces_no);
			query.setParameter("item_seq_no", item_seq_no);
			query.setParameter("ces_cces_name", ces_cces_name);
			query.setParameter("ces_cces", ces_cces);
			users = (List<CUE_TB_MISO_CES>) query.list();			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		} 		
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	//end for complete equipment schedule
	
	//search for complete equipment schedule
	@RequestMapping(value = "/searchccename", method = RequestMethod.GET)
	public ModelAndView searchccename(ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();		
		Boolean val = roleDAO.ScreenRedirect("searchccename", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("searchccenameTile");
	}

	//end search for complete equipment schedule
	
	@RequestMapping(value = "/getWePenumber", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePenumber(String type,HttpSession htpsession,HttpSession sessionA,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		String arm =  htpsession.getAttribute("roleArmCode").toString();
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		String whr="";
		
		if(type != "" && type != null) {
			if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm") ){
//				qry="select distinct we_pe_no from cue_wepe where we_pe_no !='' and status='1' and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no and arm=:arm  order by we_pe_no";
				qry="select distinct we_pe_no from cue_wepe where we_pe_no !='' and status='1' and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no and sponsor_dire=:roleSusNo order by we_pe_no";

			
			}
			else
				qry="select distinct we_pe_no from cue_wepe where we_pe_no !='' and status='1' and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no order by we_pe_no";
		}
		else {
			qry="select distinct we_pe_no from cue_wepe where we_pe_no !='' and status='1' and upper(we_pe_no) like :uploaded_wepe order by we_pe_no";
		}
		Query q = session.createQuery(qry).setMaxResults(10);
		q.setParameter("we_pe",type );
		q.setParameter("we_pe_no", we_pe_no.toUpperCase()+"%");
		if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm") ){
//			q.setParameter("arm",arm);
			q.setParameter("roleSusNo", roleSusNo);
		}
		
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}
	@RequestMapping(value = "/getsuperseededvalue",method = RequestMethod.POST)
	public @ResponseBody List<cue_wepe> getsuperseededvalue(String val,HttpSession sessionUserId) 
	{	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wepe where we_pe_no=:we_pe_no order by we_pe_no") ;
		q.setParameter("we_pe_no", val);		
		@SuppressWarnings("unchecked")
		List<cue_wepe> list = (List<cue_wepe>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	//end super
	////
	
	public List<T_Domain_Value> getPersonCatListFinal(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from T_Domain_Value where domainid ='PERSON_CAT' and label is not null order by label");
		@SuppressWarnings("unchecked")
		List<T_Domain_Value>  list = (List<T_Domain_Value> ) q.list();
		tx.commit();
		session.close();
		return list; 
	}
	
	
	@RequestMapping(value = "/getTypeofRankcategoryListFinal", method = RequestMethod.POST)
	public List<T_Domain_Value> getTypeofRankcategoryListFinal() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from T_Domain_Value where domainid=:domainid");
		q.setParameter("domainid", "RANKCATEGORY");
		@SuppressWarnings("unchecked")
		List<T_Domain_Value>  list = (List<T_Domain_Value> ) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getPersonCatList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPersonCatList(HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid ='PERSON_CAT' and label is not null order by label");
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		return getDDLCommonList(sessionUserId,list);
	}
	
	
	@RequestMapping(value = "/getTypeofRankcategoryList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTypeofRankcategoryList(HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
		q.setParameter("domainid", "RANKCATEGORY");
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		return getDDLCommonList(sessionUserId,list);
	}	
	
	@RequestMapping(value = "/getTypeappt_tradeList",method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> getTypeappt_tradeList(String rnk,HttpSession sessionUserId,String appt_trade) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
            int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select distinct description from CUE_TB_PSG_RANK_APP_MASTER where upper(level_in_hierarchy) = :level_in_hierarchy and parent_code = :parent_code and upper(description) like :appt_trade order by description ").setMaxResults(10) ;                                
            q.setParameter("level_in_hierarchy", "APPOINTMENT");
            q.setParameter("parent_code", rnk);
            q.setParameter("appt_trade", appt_trade.toUpperCase()+"%");
            @SuppressWarnings("unchecked")
            List<String> list1 = (List<String>) q.list();
            tx.commit();
            session.close();
            return getAutoCommonList(sessionUserId,list1);        
    }

	@RequestMapping(value = "/getTypeofRankList",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTypeofRankList(String rnk,HttpSession sessionUserId)throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct code,description from CUE_TB_PSG_RANK_APP_MASTER where status ='1' and code is not null and upper(level_in_hierarchy) = :level_in_hierarchy and parent_code = :parent_code order by description ") ;				
		q.setParameter("level_in_hierarchy", "RANK");
		q.setParameter("parent_code", rnk);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return getDDLCommonList(sessionUserId,list) ;
	}
	
//	@RequestMapping(value = "/getappt_trade_codelist",method = RequestMethod.POST)
//	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getappt_trade_codelist(String a,HttpSession sessionUserId) {
//		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		Query q = session.createQuery("select distinct code from CUE_TB_PSG_RANK_APP_MASTER where description=:a ");
//		q.setParameter("a", a);		
//		@SuppressWarnings("unchecked")
//		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
//		tx.commit();
//		session.close();
//		return list;
//	}
	//vishakha_v1
	@RequestMapping(value = "/getappt_trade_codelist",method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getappt_trade_codelist(String a,String rank_cat, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct code from CUE_TB_PSG_RANK_APP_MASTER where description=:a and parent_code=:rank_cat");
		q.setParameter("a", a);		
		q.setParameter("rank_cat", rank_cat);	
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getappt_trade_codelist1",method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_PSG_RANK_APP_MASTER> getappt_trade_codelist1(String a,String b,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct code from CUE_TB_PSG_RANK_APP_MASTER where description=:a and parent_code=:b ");
		q.setParameter("a", a);		
		q.setParameter("b", b);		
		@SuppressWarnings("unchecked")
		List<CUE_TB_PSG_RANK_APP_MASTER> list = (List<CUE_TB_PSG_RANK_APP_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	/*@RequestMapping(value = "/getexistWePeCondiByNo")
	public @ResponseBody List<String> getexistWePeCondiByNo(String val,HttpSession sessionUserId) {		
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null) and we_pe_no = :we_pe_no order by we_pe_no") ;
		q.setParameter("we_pe_no", val);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}*/
	
	//////////////Common all page in use for search we/pe number by get all details	
	
	
	@RequestMapping(value = "/getWePeCondiByNoSearch",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNoSearch(String we_pe,HttpSession htpsession,String newRoleid,HttpSession sessionUserId,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		String qry="";
		if(roleAccess.equals("Line_dte")){	
			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null ) and type=:type and we_pe=:we_pe and upper(we_pe_no) like:we_pe_no and sponsor_dire=:roleSusNo order by we_pe_no";
		}else {
		qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null ) and type=:type and we_pe=:we_pe and upper(we_pe_no) like:we_pe_no order by we_pe_no";
		}
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("type", newRoleid);
		q.setParameter("we_pe", we_pe );
		q.setParameter("we_pe_no", we_pe_no.toUpperCase()+"%");
		if(roleAccess.equals("Line_dte")){	
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
		
	}
	
	@RequestMapping(value = "/getWePeCondiByNoSearch1", method = RequestMethod.POST) 
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNoSearch1(String we_pe,HttpSession htpsession,String newRoleid,HttpSession sessionUserId,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		String wepe="";
		if(newRoleid.equals("ap")) {
			wepe ="1";  // for PERSONNEL value is 1
		}
		if(newRoleid.equals("at")) {
			wepe ="2"; // for TRANSPORT value is 2
		}
		if(newRoleid.equals("aw")) {
			wepe ="3"; // for WEAPON value is 3
		}
		qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null ) and type=:wepe and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no order by we_pe_no";
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("wepe", wepe);
		q.setParameter("we_pe", we_pe);
		q.setParameter("we_pe_no", we_pe_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/getWePeCondiByNoInWEPECon1", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNoInWEPECon1(HttpSession htpsession,HttpSession sessionA,String type,String newRoleid,String suprcdd_we_pe_no)  throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		String wepe="";
		if(newRoleid.equals("ap")) {
			wepe ="1";  // for PERSONNEL value is 1
		}
		if(newRoleid.equals("at")) {
			wepe ="2"; // for TRANSPORT value is 2
		}
		if(newRoleid.equals("aw")) {
			wepe ="3"; // for WEAPON value is 3
		}
		if(type != "" && type != null)
		{
		qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:wepe)  and status='1' and type=:wepe and we_pe=:type and upper(we_pe_no) like :suprcdd_we_pe_no order by we_pe_no";
		}	
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("type", type);
		q.setParameter("wepe", wepe);
		q.setParameter("suprcdd_we_pe_no", suprcdd_we_pe_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}
	
	@RequestMapping(value = "/getUploadedDocCondiByNoSearch",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUploadedDocCondiByNoSearch(HttpSession sessionUserId,String uploaded_wepe) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		String qry="";
		if(roleAccess.equals("Line_dte")){	
		qry = "select distinct uploaded_wepe from CUE_TB_MISO_WEPECONDITIONS where uploaded_wepe is not null and uploaded_wepe !='' and upper(uploaded_wepe) like:uploaded_wepe and sponsor_dire=:roleSusNo order by uploaded_wepe";

		}else {
			qry = "select distinct uploaded_wepe from CUE_TB_MISO_WEPECONDITIONS where uploaded_wepe is not null and uploaded_wepe !='' and upper(uploaded_wepe) like:uploaded_wepe order by uploaded_wepe";

		}
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("uploaded_wepe", uploaded_wepe.toUpperCase()+"%");	
		if(roleAccess.equals("Line_dte")){	
			q.setParameter("roleSusNo", roleSusNo);
		}
		
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
	}
	
	//////////////Common all page in use for search we/pe number
	
	
	

@RequestMapping(value = "/getWePeCondiByNo",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNo(HttpSession htpsession,HttpSession sessionA,String we_pe_no,String type1,String newRoleid) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String code=htpsession.getAttribute("roleArmCode").toString();
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		String qry="";		
		String wepe="";
		if(newRoleid.equals("ap")) {
			wepe ="1"; // for PERSONNEL value is 1
		}
		if(newRoleid.equals("at")) {
			wepe ="2"; // for TRANSPORT value is 2
		}
		if(newRoleid.equals("aw")) {
			wepe ="3"; // for WEAPON value is 3
		}
		
		
		if(htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm")) {
//			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and type =:wepe and we_pe=:we_pe and code=:code and upper(we_pe_no) like :we_pe_no  and sponsor_dire=:roleSusNo order by we_pe_no ";
			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and type =:wepe and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no  and sponsor_dire=:roleSusNo order by we_pe_no ";

		
		}
		else
		{
			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and type =:wepe and we_pe=:we_pe and upper(we_pe_no) like :we_pe_no order by we_pe_no";
		}
		
		
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("wepe", wepe);
		q.setParameter("we_pe", type1);
		q.setParameter("we_pe_no","%"+we_pe_no.toUpperCase()+"%");
		if(htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm")) {
//			q.setParameter("code", code);
			q.setParameter("roleSusNo", roleSusNo);
		}	
		
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}	

	
	
	@RequestMapping(value = "/getWePeCondiByNoprov",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNoprov(HttpSession htpsession,String type1,String newRoleid,String we_pe_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry="";
		
		String wepe="";
		
		if(newRoleid.equals("at")) {
			wepe ="2"; // for TRANSPORT value is 2
		}
		if(newRoleid.equals("aw")) {
			wepe ="3"; // for WEAPON value is 3
		}
		qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and type =:wepe and upper(we_pe_no) like:we_pe_no order by we_pe_no";
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("wepe", wepe);	
		q.setParameter("we_pe_no", we_pe_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}
	
	@RequestMapping(value = "/getDetailsByWePeCondiNox", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getDetailsByWePeCondiNox(String val, String x,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct wet_pet_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:val and type =:x ") ;
		q.setParameter("val", val);
		q.setParameter("x", x);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getDetailsByWePeCondiNo", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getDetailsByWePeCondiNo(String val,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String code= sessionUserId.getAttribute("roleArmCode").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q=null;
		if(sessionUserId.getAttribute("roleAccess").toString() == "Line_dte" && sessionUserId.getAttribute("roleSubAccess").toString() == "Arm"  ) {
			q = session.createQuery(" from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no and code=:code") ;
			q.setParameter("code", code);
		} else {
			q = session.createQuery(" from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no") ;
		}
		q.setParameter("we_pe_no", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	
	@RequestMapping(value = "/getDetailsByWePeCondiUploadNo", method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getDetailsByWePeCondiUploadNo(String val,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		String code= sessionUserId.getAttribute("roleArmCode").toString();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q=null;
		if(sessionUserId.getAttribute("roleAccess").toString() == "Line_dte" && sessionUserId.getAttribute("roleSubAccess").toString() == "Arm"  ) {
			 q = session.createQuery(" from cue_wepe where we_pe_no=:we_pe_no and code=:code") ;
			 q.setParameter("code", code);
		}
		else
		 q = session.createQuery(" from cue_wepe where we_pe_no=:we_pe_no") ;
		q.setParameter("we_pe_no", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
		tx.commit();
		session.close();
		return list;
	}


	@RequestMapping(value = "/getWePeCondiByNoInWEPECon", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWePeCondiByNoInWEPECon(HttpSession htpsession,String type,String newRoleid,HttpSession sessionA,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {	
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
		Transaction tx = session.beginTransaction();
		
		String qry="";
		
		if(type != "" && type != null){	
			if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm") ){
				
				qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:newRoleid)  and status='1' and type=:newRoleid and we_pe=:type and upper(we_pe_no) like:we_pe_no and sponsor_dire=:roleSusNo order by we_pe_no";
			}
			else {
		qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:newRoleid)  and status='1' and type=:newRoleid and we_pe=:type and upper(we_pe_no) like:we_pe_no order by we_pe_no";
		}	
		}
		else {
			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:newRoleid)  and status='1' and type=:newRoleid and upper(we_pe_no) like:we_pe_no order by we_pe_no";
		}
		
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("newRoleid", newRoleid);
		q.setParameter("type", type);
		q.setParameter("we_pe_no", "%"+we_pe_no.toUpperCase()+"%");
		if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte")){
			q.setParameter("roleSusNo", roleSusNo);
		}
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}


	@RequestMapping(value = "/getArmNameListCue", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getArmNameListCue(String u,HttpSession sessionUserId)throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException  {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if( u!= "" &&  u!= null) {
			q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' and arm_code = substring(arm_code,1,2)||'00' and arm_code !=:u order by arm_desc");
			q.setParameter("u", u);
		}
		else {
			q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' and arm_code = substring(arm_code,1,2)||'00' order by arm_desc");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return getDDLCommonList(sessionUserId,list);
	}
	
	@RequestMapping(value = "/getArmNameByCode", method = RequestMethod.POST)
	public @ResponseBody List<Tb_Miso_Orabt_Arm_Code> getArmNameByCode(String parent_arm_name,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code =:arm_code");
		q.setParameter("arm_code", parent_arm_name);
		@SuppressWarnings("unchecked")
		List<Tb_Miso_Orabt_Arm_Code> list = (List<Tb_Miso_Orabt_Arm_Code>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	//////////////// end integrate
	//end 
	
	//rashmi
 
	@RequestMapping(value = "/getCUESusNoDetailsList", method = RequestMethod.POST)
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> getCUESusNoDetailsList(String sus_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getCUEWERadiocatDetailsList",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getCUEWERadiocatDetailsList(String wepe_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct we_pe from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:wepe_no and status=:status and we_pe_no is not null");
		q.setParameter("wepe_no", wepe_no);
		q.setParameter("status", "1");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getCUEUnitsNameActiveList",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCUEUnitsNameActiveList(HttpSession sessionA,String unit_name) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select distinct sus_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no is not null order by sus_no) and status_sus_no='Active' and upper(unit_name) like:unit_name order by unit_name").setMaxResults(10);
		q.setParameter("unit_name", unit_name.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}
	
	
	@RequestMapping(value = "/getwepe_pers_detBaselist",method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_PERS_DET> getwepe_pers_detBaselist(String r_c,String r,String c_p,String arm,String a_t,String we_pe,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT auth_amt  from CUE_TB_MISO_WEPE_PERS_DET where category_of_persn=:c_p and rank_cat=:r_c and app_trd_code=:a_t and arm_code = :arm and rank=:r and we_pe_no = :we_pe ");
		q.setParameter("r_c", r_c);
		q.setParameter("r", r);
		q.setParameter("c_p", c_p);
		q.setParameter("arm", arm);
		q.setParameter("a_t", a_t);
		q.setParameter("we_pe",we_pe);
		
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_DET> list = (List<CUE_TB_MISO_WEPE_PERS_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getCUEUnitDetailsList", method=RequestMethod.POST)
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> getCUEUnitDetailsList(String unitName,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Miso_Orbat_Unt_Dtl where unit_name=:unitName and status_sus_no='Active'");
		q.setParameter("unitName", unitName);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getCUEWENameDetailsList",method = RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getCUEWENameDetailsList(String raio_we,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe=:raio_we and status=:status and we_pe_no is not null order by we_pe_no");
		q.setParameter("raio_we", raio_we);
		q.setParameter("status", "1");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	/*@RequestMapping(value = "/getwepepersModiDetailsList")
	public @ResponseBody List<CUE_TB_MISO_WEPE_PERS_MDFS> getwepepersModiDetailsList(String we_pe_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select modification from CUE_TB_MISO_WEPE_PERS_MDFS where we_pe_no=:we_pe_no and  modification is not null order by modification");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_MDFS> list = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) q.list();
		tx.commit();
		session.close();
		return list;
	}*/

	@RequestMapping(value = "/getTypeofModiList",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTypeofModiList(HttpSession sessionUserId,String label) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct label from T_Domain_Value where domainid=:domainid and upper(label) like:label order by label");
		q.setParameter("domainid", "MODIFICATION");
		q.setParameter("label", label.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
	}
	
	///////////// rashmi new//////////////////////////
	
	
	@RequestMapping(value = "/getCUEUnitsSUSNoActiveList",method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCUEUnitsSUSNoActiveList(HttpSession sessionA,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String qry ="";
		
		Transaction tx = session.beginTransaction();
		if(roleAccess.equals("Line_dte")){	
			
			 qry = "select distinct sus_no from Miso_Orbat_Unt_Dtl \r\n"
			 		+ "	where status_sus_no='Active' and arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus =:roleSusNo )\r\n"
			 		+ "	 and upper(sus_no) like:sus_no  order by sus_no";
			
			
		}else {
		 qry = "select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and upper(sus_no) like:sus_no  order by sus_no";
		}
		Query q = session.createQuery(qry).setMaxResults(10) ;
		q.setParameter("sus_no", sus_no.toUpperCase()+"%");	
		if(roleAccess.equals("Line_dte")){	
			q.setParameter("roleSusNo", roleSusNo.toUpperCase());
		}
		@SuppressWarnings("unchecked")
		List<String>  list1 = (List<String> ) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionA,list1);
	}

	@RequestMapping(value = "/getCUEUnitnamebysusDetailsList", method=RequestMethod.POST)
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> getCUEUnitnamebysusDetailsList(String sus_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	////end for rashmi
	
	@RequestMapping(value = "/getitemcode",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_ITEM_MSTR> getitemcode(String val,HttpSession sessionUserId) 
	{		
		val = val.replace("&#40;","(");
		val = val.replace("&#41;",")");
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select  item_code from CUE_TB_MISO_MMS_ITEM_MSTR where item_type =:item_type ") ;
		q.setParameter("item_type", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getitemnamefromcode",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_ITEM_MSTR> getitemnamefromcode(String val,HttpSession sessionUserId){		
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select  item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code =:item_code ") ;
		q.setParameter("item_code", val);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getitemtype", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getitemtype(HttpSession sessionUserId,String item_type) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct item_type from CUE_TB_MISO_MMS_ITEM_MSTR where item_code !='' and status='1' and  status_active='Active' and upper(item_type) like:item_type order by item_type").setMaxResults(20) ;
		q.setParameter("item_type", "%"+item_type.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
	}
	
	@RequestMapping(value = "/getItemCodeList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getItemCodeList(HttpSession sessionUserId,String item_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct item_code from CUE_TB_MISO_MMS_ITEM_MSTR where item_code !='' and status='1' and  status_active='Active'  and upper(item_code) like:item_code order by item_code").setMaxResults(10) ;
		q.setParameter("item_code",item_code.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return getAutoCommonList(sessionUserId,list1);
	}
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/upload_WE", method = RequestMethod.GET)
	public ModelAndView upload_WE(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		String  roleid = session.getAttribute("roleid").toString();	
		Boolean val = roleDAO.ScreenRedirect("upload_WE", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleArmCode = session.getAttribute("roleArmCode").toString();
		
		if(roleAccess.equals("Line_dte")){	
			
			Mmap.put("getArmNameList",getArmNameLine(roleSusNo));
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte",select);
			Mmap.put("selectArmNameList",select);
			Mmap.put("getArmNameList", getArmNameList());
			 Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		
		Mmap.put("msg", msg);
		//Mmap.put("getArmNameList", getArmNameList());
		Mmap.put("getsponserListCue", getsponserListCue());
		return new ModelAndView("cueTiles" ,"cuewepeCMD",new cue_wepe());
	} 
	
	@RequestMapping(value = "/cuewepeAction1", method = RequestMethod.POST) 
	public ModelAndView cuewepeAction1(@ModelAttribute("cuewepeCMD") cue_wepe rs,@RequestParam(value = "doc_path1", required = false) MultipartFile doc_path1,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			int roleid = (Integer)session.getAttribute("roleid");			
			
			String roleType = session.getAttribute("roleType").toString();
			String we_pe_no = request.getParameter("we_pe_no");
			String we_pe = request.getParameter("we_pe");
			String sponsor_dire = request.getParameter("sponsor_dire");
			String arm = request.getParameter("arm");
			String doc_type = request.getParameter("doc_type");
			
			if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
			{
				model.put("msg", "Please Select Document ");
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(validation.checkWETypeLength(we_pe)  == false){
				model.put("msg",validation.wetypeMSG);
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(rs.getWe_pe_no().equals(""))
			{
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
				model.put("msg",validation.wepenoMSG);
				return new ModelAndView("redirect:upload_WE");
			}
			
			 if(rs.getTable_title().equals(""))
				{
					model.put("msg", "Please Enter Table Title");
					return new ModelAndView("redirect:upload_WE");
				}
			 
			 if(validation.checkWepetabletittleLength(rs.getTable_title())  == false){
					model.put("msg",validation.wepetitleMSG);
					return new ModelAndView("redirect:upload_WE");
				}
			 String answer = request.getParameter("answer");
			 if(answer==null)
			 {
				model.put("msg", "Please select New Document");
				return new ModelAndView("redirect:upload_WE");
			 }
			 if(answer.equals("No"))
			 {
				 if(rs.getSuprcdd_we_pe_no().equals(""))
				 {
					 model.put("msg", "Please Select Superseeded Document No");
					return new ModelAndView("redirect:upload_WE");
				 }
				 
				if(validation.checkWepeLength(rs.getSuprcdd_we_pe_no())  == false)
				 {
					model.put("msg",validation.spodocnoMSG);
					return new ModelAndView("redirect:upload_WE");
				 }
			 }
			 if(rs.getEff_frm_date().equals(""))
			 {
				 model.put("msg", "Please Select Effective From Date");
				 return new ModelAndView("redirect:upload_WE");
			 }
			 
			 if(validation.checkDateLength(rs.getEff_frm_date())  == false)
			 {
				model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:upload_WE");
			 }
			 
			 if(rs.getEff_to_date().equals(""))
			 {
				 model.put("msg", "Please Select Effective To Date");
				 return new ModelAndView("redirect:upload_WE");
			 }
			 
			 if(validation.checkDateLength(rs.getEff_to_date())  == false)
			 {
				model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:upload_WE");
			 }
			 
			 if(validation.checkModificationLength(rs.getDoc_type())  == false)
			 {
				model.put("msg",validation.secclassMSG);
				return new ModelAndView("redirect:upload_WE");
			 }
			 
			if( rs.getArm().equals("0"))
			{
				model.put("msg", "Please Select Arm");
				return new ModelAndView("redirect:upload_WE");
			}
			
			String arm_code = String.format("%04d", Integer.parseInt(rs.getArm()));
			if(validation.checkArmCodeLength(arm_code)  == false)
			{
		 		model.put("msg",validation.arm_codeMSG);
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(rs.getSponsor_dire().equals("0"))
			{
				model.put("msg", "Please Select Sponsor Directorate");
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(validation.checkSponsorDireLength(rs.getSponsor_dire())  == false)
			{
		 		model.put("msg",validation.spodireMSG);
				return new ModelAndView("redirect:upload_WE");
			}
			
			if(rs.getDoc_path() == "null" )
			{
				model.put("msg", "Please Enter Document Path");
				return new ModelAndView("redirect:upload_WE");
			}
		
			if(validation.checkRemarksLength(rs.getRemarks())  == false)
			{
		 		model.put("msg",validation.remarksMSG);
				return new ModelAndView("redirect:upload_WE");
			}
			
			String doc_path1Ext = FilenameUtils.getExtension(doc_path1.getOriginalFilename());
			final long fileSizeLimit =  Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
			if (doc_path1.getSize() > fileSizeLimit) {
				 model.put("msg", "File size should be less then 2 MB");
				 return new ModelAndView("redirect:upload_WE");
			}
			if(!doc_path1Ext.equals("pdf")) {
				model.put("msg", "Only *.pdf file extension allowed");
				return new ModelAndView("redirect:upload_WE");
			}
			
		Boolean e1 = checkDetailsOfWePeAmdnt(we_pe_no);
		if(e1.equals(false)) {
			String fname = "";
			DateWithTimeStampController timestamp = new DateWithTimeStampController();
			if (!doc_path1.isEmpty()) {
				// code modify by Paresh on 05/05/2020
				try {
					byte[] bytes = doc_path1.getBytes();
					CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
					if(fileValidation.check_PDF_File(bytes)) {
						String cueFilePath = session.getAttribute("cueFilePath").toString();
						File dir = new File(cueFilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_CUEDOC.PDF";
						File serverFile = new File(fname);	               
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);	                
						stream.close();
						rs.setDoc_path(fname);
					}else {
						model.put("msg", "Only *.pdf file extension allowed");
						return new ModelAndView("redirect:upload_WE");
					}
				}
				catch (Exception e) {
		       }
			} 
			rs.setRoleid(roleid);
			String username = session.getAttribute("username").toString();
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String table_title=request.getParameter("table_title");
			
			rs.setCreated_by(username);
			rs.setCreated_on(creadtedate);
			rs.setStatus("0");
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			sessionHQL.save(rs);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			model.put("msg", "Data saved Successfully");
		}
		else {
		model.put("msg", "Data Already Exists!");
		}
		List<Map<String, Object>> list =cuewepe.getAttributeFromCategorySearchWePe(we_pe,"",sponsor_dire,arm,doc_type,"",roleType);
			model.put("list", list);
			model.put("we_pe01", we_pe);
			model.put("listsize", list.size());
			model.put("doc_type1", doc_type);
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Line_dte")){	
			
				model.put("getArmNameList",getArmNameLine(roleSusNo));
				model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
			}else {
				String select="<option value='0'>--Select--</option>";
				model.put("selectLine_dte",select);
				model.put("selectArmNameList",select);
				model.put("getArmNameList", getArmNameList());
				model.put("getLine_DteList",roledao.getLine_DteList(""));
			}
			model.put("getsponserListCue", getsponserListCue());
		return new ModelAndView("cueTiles");
	}
		
		//////////////////// end zankar
		
		@SuppressWarnings("unchecked")
		public Boolean checkDetailsOfWePeAmdnt(String we_pe_no) {			
			String hql="select distinct we_pe_no from cue_wepe where we_pe_no=:we_pe_no order by we_pe_no";
			List<cue_wepe> users = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try {
				Query query= session.createQuery(hql);
				query.setParameter("we_pe_no", we_pe_no);
				users = (List<cue_wepe>) query.list();			
				tx.commit();
				session.close();
			} catch (Exception e) {
				session.getTransaction().rollback();
				
				return null;
			} 		
			if(users.size()>0)
			{
				return true;
			}
			return false;
		}

		@RequestMapping(value = "/getAutoWepen", method = RequestMethod.POST)	
		public @ResponseBody List<Map<String, Object>> getAutoWepen(String we_pe,  String item_code) {		
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			list2 =masterDAO1.getBase_autoDetail(we_pe,item_code);
			return list2;
		}
	
		@RequestMapping(value = "/search_appt_trades_rank", method = RequestMethod.GET)
		public ModelAndView search_appt_trades_rank(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("search_appt_trades_rankTiles");
		}
		
		@RequestMapping(value = "/getcategoryListFinal", method = RequestMethod.POST)
		public @ResponseBody List<T_Domain_Value> getcategoryListFinal(){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery(" from T_Domain_Value where domainid=:domainid"); //select codevalue,label
			q.setParameter("domainid", "RANKCATEGORY");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value>  list = (List<T_Domain_Value> ) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getcategoryList", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getcategoryList(HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
			q.setParameter("domainid", "RANKCATEGORY");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			return getDDLCommonList(sessionUserId,list);
		}
		
		@RequestMapping(value = "/getDetailsWePeCondiNo",method = RequestMethod.POST)
		public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getDetailsWePeCondiNo(String we_pe_no,HttpSession sessionUserId) {	
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery(" select distinct table_title from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no ") ;
			q.setParameter("we_pe_no", we_pe_no);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		
		@RequestMapping(value = "/getLocation",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getLocation(HttpSession sessionUserId,String code_value) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct code_value from Tb_Miso_Orbat_Code where code_type=:code_type and upper(code_value) like :code_value and status_record='1' ").setMaxResults(10);
			q.setParameter("code_type","Location");
			q.setParameter("code_value", code_value.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionUserId,list1);
		}

		@RequestMapping(value = "/getLocationByCode", method = RequestMethod.POST)
		public @ResponseBody List<Tb_Miso_Orbat_Code> getLocationByCode(String code_value,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct code from Tb_Miso_Orbat_Code where code_type=:code_type and code_value=:code_value and status_record='1' ");
			q.setParameter("code_type","Location");
			q.setParameter("code_value",code_value);
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}

		@RequestMapping(value = "/getLocationByName", method = RequestMethod.POST)
		public @ResponseBody List<Tb_Miso_Orbat_Code> getLocationByName(String code,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct code_value from Tb_Miso_Orbat_Code where code_type=:code_type and code=:code and status_record='1' ");
			q.setParameter("code_type","Location");
			q.setParameter("code",code);
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}	
		
		@RequestMapping(value = "/getFormationUrl", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getFormationUrl(HttpSession sessionUserId,String formation) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return footNoteTransDAO.getFormation(sessionUserId,formation);
		}
		@RequestMapping(value = "/getUnitUrl", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getUnitUrl(HttpSession sessionUserId,String unit) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return footNoteTransDAO.getUnit(sessionUserId,unit);
		}
		
		@RequestMapping(value = "/getWEPENoDetailsList", method = RequestMethod.POST)
		public @ResponseBody List<String> getWEPENoDetailsList(String we_pe_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct table_title from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no ");
			q.setParameter("we_pe_no", we_pe_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
//		changes by dev 04-12
		@RequestMapping(value = "/getCheckDublicateMCT_WEPENOList", method = RequestMethod.POST)
		public @ResponseBody List<Integer> getCheckDublicateMCT_WEPENOList(String mct_no,String we_pe_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select count(mct_no) from CUE_TB_MISO_WEPE_TRANSPORT_DET where mct_no=:mct_no and we_pe_no=:we_pe_no ");
			q.setParameter("we_pe_no", we_pe_no);
			q.setParameter("mct_no", mct_no);
			@SuppressWarnings("unchecked")
			List<Integer> list = q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getBaseAuthAmountDetailsList", method = RequestMethod.POST)
		public @ResponseBody List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getBaseAuthAmountDetailsList(String we_pe_no, String mct_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct auth_amt from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no and mct_no=:mct_no");
			q.setParameter("we_pe_no", we_pe_no);
			q.setParameter("mct_no", mct_no);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPE_TRANSPORT_DET> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q.list();
			tx.commit();
			session.close();
			
			return list;
	  }

		@RequestMapping(value = "/getStdNomclatureDetailsList",method = RequestMethod.POST)
		public @ResponseBody List<String> getStdNomclatureDetailsList(String std_nomclature,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct mct_main_id from TB_TMS_MCT_MAIN_MASTER where mct_nomen=:std_nomclature order by mct_main_id");
			q.setParameter("std_nomclature", std_nomclature);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getMctNoListDetail",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getMctNoListDetail(HttpSession sessionUserId,String mct_main_id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getMctNoList(sessionUserId,mct_main_id);
		}
		
		@RequestMapping(value = "/getStdNomenclatureListTrans",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getStdNomenclatureListTrans(HttpSession sessionUserId,String mct_nomen) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getStdNomenclatureList(sessionUserId, mct_nomen);
		}
//			changes by dev 04-12		
		@RequestMapping(value = "/getMctBYNomenclature",method = RequestMethod.POST)
		public @ResponseBody List<String> getMctBYNomenclature(String std_nomclature,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct mct_main_id from TB_TMS_MCT_MAIN_MASTER where mct_nomen=:std_nomclature order by mct_main_id");
			q.setParameter("std_nomclature", std_nomclature);
			@SuppressWarnings("unchecked")
			List<String> list = q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getUnitNameFromSusNo", method = RequestMethod.POST)
		public @ResponseBody List<String> getUnitNameFromSusNo(String unit_name,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name order by sus_no");
			q.setParameter("unit_name", unit_name);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
// changes by dev 04-12
@RequestMapping(value = "/getMctNoDetailsList",method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNoDetailsList(String mct,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct mct_nomen from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:mct order by mct_nomen");
		q.setParameter("mct", mct);
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		tx.commit();
		session.close();
		return list;
	}
	
		@RequestMapping(value = "/getCUEUnitSUSNoList_pers",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getCUEUnitSUSNoList_pers(HttpSession sessionUserId,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct sus_no from CUE_TB_WEPE_link_sus_perstransweapon where upper(sus_no) like:sus_no and status_pers='1' order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionUserId,list1);
		}
		
		@RequestMapping(value = "/getCUEUnitSUSNoList_wep", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getCUEUnitSUSNoList_wep(HttpSession sessionUserId,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct sus_no from CUE_TB_WEPE_link_sus_perstransweapon where upper(sus_no) like:sus_no and  status_weap='1' order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionUserId,list1);
		}
		
		@RequestMapping(value = "/getCUEUnitSUSNoList_trans", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getCUEUnitSUSNoList_trans(HttpSession sessionUserId,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct sus_no from CUE_TB_WEPE_link_sus_perstransweapon where upper(sus_no) like:sus_no and  status_trans='1' order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionUserId,list1);
		}

		
		@RequestMapping(value = "/getCUEUnitsList", method = RequestMethod.POST)
		public @ResponseBody List<String> getCUEUnitsList(String sus_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active' order by unit_name");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getwepePersno", method = RequestMethod.POST)
		public @ResponseBody List<String> getwepePersno(String sus_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct l.wepe_pers_no as we_pe_no,c.table_title from CUE_TB_WEPE_link_sus_perstransweapon l ,CUE_TB_MISO_WEPECONDITIONS c  where l.sus_no=:sus_no and l.status_pers='1' and l.wepe_pers_no = c.we_pe_no");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}	

			
		@RequestMapping(value = "/getwepeTransno", method = RequestMethod.POST)
		public @ResponseBody List<String> getwepeTransno(String sus_no,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct l.wepe_trans_no as we_pe_no,c.table_title from CUE_TB_WEPE_link_sus_perstransweapon l ,CUE_TB_MISO_WEPECONDITIONS c  where l.sus_no=:sus_no and l.status_trans='1' and l.wepe_trans_no = c.we_pe_no");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getwepeWeaponsno", method = RequestMethod.POST)
		public @ResponseBody List<String> getwepeWeaponsno(String sus_no,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());	
		Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct l.wepe_weapon_no as we_pe_no,c.table_title from CUE_TB_WEPE_link_sus_perstransweapon l ,CUE_TB_MISO_WEPECONDITIONS c  where l.sus_no=:sus_no and l.status_weap='1' and l.wepe_weapon_no = c.we_pe_no");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}	

		public List<Object[]> getDomainListClassData() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select label from MMS_Domain_Values where domainid='MMSCLASSA' order by disp_order");
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list();
			tx.commit();
			session.close();
			return list;
		}
			
			//////////////////enc-dec common for DDL///////////
		
		public List<Map<String, Object>> getDDLCommonList(HttpSession sessionUserId,List<Object[]>  list) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			List<Map<String, Object>> FinalList = new ArrayList<Map<String, Object>>();

			for(Object[] listObject: list){
		    	String columnName = (String)listObject[1];
		   		String columnCode = (String)listObject[0];
		   		
		   		byte[] enccolumnName = c.doFinal(columnName.getBytes());
			    String base64EncodedEncryptedcolumnName = new String(Base64.encodeBase64(enccolumnName));
			    
			    byte[] enccolumnCode = c.doFinal(columnCode.getBytes());
			    String base64EncodedEncryptedcolumnCode = new String(Base64.encodeBase64(enccolumnCode));
		   		
			    Map<String, Object> EncList = new LinkedHashMap<String, Object>();
		   		//ArrayList<Map<String, Object>> EncList = new ArrayList<Map<String, Object>>();
			    EncList.put("columnName",base64EncodedEncryptedcolumnName);
			    EncList.put("columnCode",base64EncodedEncryptedcolumnCode);
			    FinalList.add(EncList);
		   	}
			 //Enc Key Append Last value of List
			 Map<String, Object> EncKeyList = new LinkedHashMap<String, Object>();
			 String a1=enckey+"4bsjyg==";
		    if(list.size() != 0) {
		    	EncKeyList.put("columnName1",a1);
		    	EncKeyList.put("columnCode1","gDKfjjU+/PZ6k4WWTJB1IA==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		public List<Map<String, Object>> getAutoCommonList(HttpSession sessionUserId,List<String>  list) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			List<Map<String, Object>> FinalList = new ArrayList<Map<String, Object>>();

			for(String listObject: list ){
				String columnName = (String)listObject.toString();
		   		
		   		byte[] enccolumnName = c.doFinal(columnName.getBytes());
			    String base64EncodedEncryptedcolumnName = new String(Base64.encodeBase64(enccolumnName));
			    
			    Map<String, Object> EncList = new LinkedHashMap<String, Object>();
		   		 EncList.put("columnName",base64EncodedEncryptedcolumnName);
			   FinalList.add(EncList);
		   	}
			 //Enc Key Append Last value of List
			 Map<String, Object> EncKeyList = new LinkedHashMap<String, Object>();
			 String a1=enckey+"4bsjyg==";
		    if(list.size() != 0) {
		    	EncKeyList.put("columnName1",a1);
		     }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		
		
		public List<Object[]>  getArmNameList(){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' and arm_code = substring(arm_code,1,2)||'00' order by arm_desc");
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list();
			
			
			tx.commit();
			session.close();
			return list;
		}
		
		
		public List<Object[]>  getArmNameListParameter(String armCode){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' and arm_code = substring(arm_code,1,2)||'00' and arm_code=:arm_code order by arm_desc");
			q.setParameter("arm_code", armCode);
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list();
			tx.commit();
			session.close();
			return list;
		}
public List<Object[]>  getArmNameLine(String roleSusNo){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("\r\n" + 
					"select distinct a.arm_code,a.arm_desc from Tb_Miso_Orabt_Arm_Code a,TB_MISO_ORBAT_LINE_DTE b \r\n" + 
					"					 where a.arm_code = b.arm_code \r\n" + 
					"					 and a.arm_desc is not null and a.status='1'\r\n" + 
					"					 and ( a.arm_code = substring(a.arm_code,1,2)||'00' or a.arm_code in ('0000','0020','0030'))\r\n" + 
					"					 and (b.line_dte_sus=:rolesus or a.arm_code in ('0000','0020','0030'))\r\n" + 
					"					 order by a.arm_desc \r\n");
			
			
		
			q.setParameter("rolesus", roleSusNo);
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		
		public List<Object[]> getTypeofRankcategory(){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
			q.setParameter("domainid", "RANKCATEGORY");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			return list;
		}	
		
		public List<Object[]> getsponserListCue(){
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid =:domainid and label is not null order by label ");
			q.setParameter("domainid", "SPONSERDTE");
			@SuppressWarnings("unchecked")
			List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			return list;
		}
	//added by satya pgrmr//
		
		public List<Object[]>  getSDArmNameList(){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = null;
            q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' "
                                  + "and arm_code in (select distinct concat(substring(arm_name,1,2),'00') as relief_arm_code from Miso_Orbat_Relief_Prgm)");
            @SuppressWarnings("unchecked")
            List<Object[]> list = (List<Object[]>) q.list();
            tx.commit();
            session.close();
            return list;
        }
		
public List<Object[]>  getArmNameListforSD(String leftv,String rightv){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct arm_code,arm_desc from Tb_Miso_Orabt_Arm_Code where arm_desc is not null and status='1' and "
					+ " arm_code in (select distinct substring(arm_name,1,2)||'00' as arm_name from Miso_Orbat_Relief_Prgm \r\n" + 
					"	where "+leftv + "= '" + rightv +"')");
			
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list();
			tx.commit();
			session.close();
			return list;
		}




			public static String Get_button_info(ResultSetMetaData metaData, ResultSet rs) throws SQLException
							{
								String a="";
								int columnCount = metaData.getColumnCount();
								for (int i = 1; i <= columnCount; i++) {
									if(metaData.getColumnLabel(i).equals("cr_by")) {
                                            a+="Uploaded By :" +rs.getObject(i);
                                        }
									if(metaData.getColumnLabel(i).equals("cr_on")) {
                                            a+= "\nUploaded On :" +rs.getObject(i);
                                        }
//									if(metaData.getColumnLabel(i).equals("app_by")) {
//                                        	a+="\nApproved By :" +rs.getObject(i);
//										}
//									if(metaData.getColumnLabel(i).equals("app_on")) {
//											a+= "\nApproved On :" +rs.getObject(i);
//										}	
									if(metaData.getColumnLabel(i).equals("modi_by")) {
										if(rs.getObject(i)!=null) {
											a+="\nModified By :" +rs.getObject(i);
										}else {
											a+="\nModified By :";
										}
										}
                                	if(metaData.getColumnLabel(i).equals("modi_on")) {
                                		if(rs.getObject(i)!=null) {
                                			a+= "\nModified On :" +rs.getObject(i);
                                		}
                                		else {
                                			a+= "\nModified On :" ;
                                		}
                                			
                                		}
                                              
								}

								String LogButton = "<i class='fa fa-question-circle' " + " title=' " + a + " '></i>";
                                              return LogButton;
							}
			
			public static String Get_button_info1(ResultSetMetaData metaData, ResultSet rs) throws SQLException
			{
				String a="";
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					if(metaData.getColumnLabel(i).equals("cr_by")) {
                            a+="Uploaded By :" +rs.getObject(i);
                        }
					if(metaData.getColumnLabel(i).equals("cr_on")) {
                            a+= "\nUploaded On :" +rs.getObject(i);
                        }
					if(metaData.getColumnLabel(i).equals("app_by")) {
						if(rs.getObject(i)!=null) {
							a+="\nApproved By :" +rs.getObject(i);
						}else {
							a+="\nApproved By :";
						}
						}
					if(metaData.getColumnLabel(i).equals("app_on")) {
						if(rs.getObject(i)!=null) {
							a+= "\nApproved On :" +rs.getObject(i);
						}else {
							a+="\nApproved On :";
						}
							
					}
					if(metaData.getColumnLabel(i).equals("modi_by")) {
						if(rs.getObject(i)!=null) {
							a+="\nModified By :" +rs.getObject(i);
						}else {
							a+="\nModified By :";
						}
						}
                	if(metaData.getColumnLabel(i).equals("modi_on")) {
                		if(rs.getObject(i)!=null) {
                			a+= "\nModified On :" +rs.getObject(i);
                		}
                		else {
                			a+= "\nModified On :" ;
                		}
                			
                		}
                              
				}

				String LogButton = "<i class='fa fa-question-circle' " + " title=' " + a + " '></i>";
                              return LogButton;
			}

			public List<Object[]>  getArmNameListRelifPgme(){
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = null;
				q = session.createQuery("select distinct a.arm_code,a.arm_desc from Tb_Miso_Orabt_Arm_Code a ,Miso_Orbat_Relief_Prgm b \r\n"
						+ "where  a.arm_code=b.arm_name and arm_desc is not null and a.status='1' \r\n"
						+ "and a.arm_code = substring(a.arm_code,1,2)||'00' order by a.arm_desc");
				@SuppressWarnings("unchecked")
				List<Object[]> list = (List<Object[]>) q.list();
				tx.commit();
				session.close();
				return list;
			}
			

			//NEW 
						
						//RAJ 26.06
						@RequestMapping(value = "/getitemnamefromprfcode",method=RequestMethod.POST)
						public @ResponseBody List<CUE_TB_MISO_MMS_ITEM_MSTR> getitemnamefromprfcode(String prf_code,HttpSession sessionUserId)  {		
							int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
							Session session = HibernateUtil.getSessionFactory().openSession();
							Transaction tx = session.beginTransaction();				
							Query q = session.createQuery("select item_code, item_type from CUE_TB_MISO_MMS_ITEM_MSTR where prf_group_code =:prf_group_code ") ;
							q.setParameter("prf_group_code", prf_code);
							@SuppressWarnings("unchecked")		
							List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
							tx.commit();
							session.close();
							return list ;
						}
						
//added by dev 04-12
						
	@RequestMapping(value = "/getTransportUEListBySearch", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getTransportUEListBySearch(String nParaValue, HttpSession s1) {
		List<String> list = mmsCommonDAO.getTransportUEListBySearch(nParaValue);
				if (list.size() != 0) {
					List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
						return FList;
				} else {
					return list;
				}
			}
	@RequestMapping(value = "/getTransportUEprfcode",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_ITEM_MSTR> getTransportUEprfcode(String prf_code,HttpSession sessionUserId)  {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select mct_main_id, mct_nomen from TB_TMS_MCT_MAIN_MASTER where prf_code =:prf_code ") ;
		q.setParameter("prf_code", prf_code);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = q.list();
		tx.commit();
		session.close();
		return list ;
	}
		
// added by jainisha 18-11-24
						
		@RequestMapping(value = "/getPrfCodeListDetail",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getPrfCodeListDetail(HttpSession sessionUserId,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getPrfCodeList(sessionUserId,prf_code);
		}
						
		@RequestMapping(value = "/getPrfGpNameListDetail",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getPrfGpNameListDetail(HttpSession sessionUserId,String prf_gp_name) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getPrfGpNameList(sessionUserId,prf_gp_name);
		}
		@RequestMapping(value = "/getPrfCodeDetailsList",method = RequestMethod.POST)
		public @ResponseBody List<String> getPrfCodeDetailsList(String prf,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct group_name from TB_TMS_MCT_SLOT_MASTER where prf_code=:prf_code   order by group_name");
			q.setParameter("prf_code", prf);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		@RequestMapping(value = "/getGpNameDetailsList",method = RequestMethod.POST)
		public @ResponseBody List<String> getGpNameDetailsList(String group_name,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();		
			Query q = session.createQuery("select distinct prf_code from TB_TMS_MCT_SLOT_MASTER where group_name=:group_name");
			q.setParameter("group_name", group_name);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}			
			
		@RequestMapping(value = "/getMctNoListOfPrf",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getMctNoListOfPrf(HttpSession sessionUserId,String mct_main_id,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getMctNoListPrf(sessionUserId,mct_main_id,prf_code);
		}
		@RequestMapping(value = "/getStdNomenclaturePrfListTrans",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getStdNomenclaturePrfListTrans(HttpSession sessionUserId,String mct_nomen,String prf_code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {				
			return cueTransport.getMctNCListPrf(sessionUserId,mct_nomen,prf_code);
		}
		
		
		@RequestMapping(value = "/getWePeCondiByNoInReport", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getWePeCondiByNoInReport(HttpSession htpsession,String type,String newRoleid,HttpSession sessionA,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {	
			Session session = HibernateUtil.getSessionFactory().openSession();
			String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
			Transaction tx = session.beginTransaction();
			
			String qry="";
			
//			if(type != "" && type != null){	
//				if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm") ){
//					
//					qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:newRoleid)  and status='1' and type=:newRoleid and we_pe=:type and upper(we_pe_no) like:we_pe_no and sponsor_dire=:roleSusNo order by we_pe_no";
//				}
//				else {
//			qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null and type=:newRoleid)  and status='1' and type=:newRoleid and we_pe=:type and upper(we_pe_no) like:we_pe_no order by we_pe_no";
//			}	
//			}
//			else {
				qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null  and type=:type and upper(we_pe_no) like:we_pe_no order by we_pe_no";
//			}
			
			Query q = session.createQuery(qry).setMaxResults(10) ;
			q.setParameter("type", type);
			q.setParameter("we_pe_no", "%"+we_pe_no.toUpperCase()+"%");
//			if( htpsession.getAttribute("roleAccess").toString().equals("Line_dte")){
//				q.setParameter("roleSusNo", roleSusNo);
//			}
			@SuppressWarnings("unchecked")
			List<String>  list1 = (List<String> ) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionA,list1);
		}

		
		@RequestMapping(value = "/getTableTitleCondiReport", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getTableTitleCondiReport(HttpSession htpsession,String type,String newRoleid,HttpSession sessionA,String tableTitle) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {	
			Session session = HibernateUtil.getSessionFactory().openSession();
			String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
			Transaction tx = session.beginTransaction();
			
			String qry="";
			
				qry="select distinct table_title from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null   and type=:type and upper(table_title) like:table_title order by table_title";
			
			Query q = session.createQuery(qry).setMaxResults(10) ;
			q.setParameter("type", type);
			q.setParameter("table_title", "%"+tableTitle.toUpperCase()+"%");
			@SuppressWarnings("unchecked")
			List<String>  list1 = (List<String> ) q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionA,list1);
		}
		
		
		@RequestMapping(value = "/getDetailsByWePeCondiTitle", method = RequestMethod.POST)
		public @ResponseBody List<CUE_TB_MISO_WEPECONDITIONS> getDetailsByWePeCondiTitle(String val,String ap,HttpSession sessionUserId) {	
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			String code= sessionUserId.getAttribute("roleArmCode").toString();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q=null;
			if(sessionUserId.getAttribute("roleAccess").toString() == "Line_dte" && sessionUserId.getAttribute("roleSubAccess").toString() == "Arm"  ) {
				 q = session.createQuery(" from CUE_TB_MISO_WEPECONDITIONS where table_title=:table_title and code=:code") ;
				 q.setParameter("code", code);
			}
			else
			 q = session.createQuery(" from CUE_TB_MISO_WEPECONDITIONS where table_title=:table_title and type=:type") ;
			q.setParameter("table_title", val);
			q.setParameter("type", ap);
			@SuppressWarnings("unchecked")
			List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		@RequestMapping(value = "/getOrbatDetailsFromUnitNamecue",method = RequestMethod.POST)
		public @ResponseBody List<String> getOrbatDetailsFromUnitNamecue(String susno,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			List<String> Finallist = new ArrayList<String>();
				ArrayList<ArrayList<String>> wepe_list = cuewepe.getWepeData(susno);
				// Check if wepe_list is not null and not empty
				if (wepe_list != null && !wepe_list.isEmpty()) {
				    ArrayList<String> firstRow = wepe_list.get(0);
				    
				    // Check if firstRow is not null and has enough elements
				    if (firstRow != null && firstRow.size() >= 9) {
				        // Safe retrieval with null checks
				        String eff_from_date = firstRow.get(0) != null ? firstRow.get(0) : "";
				        String eff_to_date = firstRow.get(1) != null ? firstRow.get(1) : "";
				        String ct_part_i_ii = firstRow.get(2) != null ? firstRow.get(2) : "";
				        String type_force = firstRow.get(3) != null ? firstRow.get(3) : "";
				        String training_capacity = firstRow.get(4) != null ? firstRow.get(4) : "";
				        String unit_category = firstRow.get(5) != null ? firstRow.get(5) : "";
				        String wepe_no = firstRow.get(6) != null ? firstRow.get(6) : "";
				        String sponsor_dire = firstRow.get(7) != null ? firstRow.get(7) : "";
				        String userarm = firstRow.get(8) != null ? firstRow.get(8) : "";
				        String table_title = firstRow.get(9) != null ? firstRow.get(9) : "";
				        
				        Finallist.add(eff_from_date);			
						Finallist.add(eff_to_date);			
						Finallist.add(ct_part_i_ii);			
						Finallist.add(type_force);			
						Finallist.add(training_capacity);			
						Finallist.add(unit_category);		
						Finallist.add(wepe_no);	
						Finallist.add(sponsor_dire);		
						Finallist.add(userarm);	
						Finallist.add(table_title);	
				    } else {
				        // Handle case where firstRow is null or doesn't have enough elements
				     
				    }
				} else {
				    // Handle case where wepe_list is null or empty
				   
				}
				
				
		    return Finallist;
		}
		
		
		@RequestMapping(value = "/getWePeByNo",method=RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getWePeByNo(HttpSession htpsession,HttpSession sessionA,String we_pe_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String code=htpsession.getAttribute("roleArmCode").toString();
			String roleSusNo = htpsession.getAttribute("roleSusNo").toString();
			Session session = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = session.beginTransaction();
			String qry="";
			String wepe="";

			if(htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm")) {
				qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and upper(we_pe_no) like :we_pe_no  and sponsor_dire=:roleSusNo order by we_pe_no ";
			} else {
				qry="select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe_no is not null and we_pe_no NOT IN (select suprcdd_we_pe_no from CUE_TB_MISO_WEPECONDITIONS where suprcdd_we_pe_no is not null)  and status='1' and upper(we_pe_no) like :we_pe_no order by we_pe_no";
			}

			Query q = session.createQuery(qry).setMaxResults(10) ;
			q.setParameter("we_pe_no","%"+we_pe_no.toUpperCase()+"%");
			if(htpsession.getAttribute("roleAccess").toString().equals("Line_dte") && htpsession.getAttribute("roleSubAccess").toString().equals("Arm")) {
				//			q.setParameter("code", code);
				q.setParameter("roleSusNo", roleSusNo);
			}
			System.out.println("hello" + q);
			@SuppressWarnings("unchecked")
			List<String>  list1 = q.list();
			tx.commit();
			session.close();
			return getAutoCommonList(sessionA,list1);
		}

}

