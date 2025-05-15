package com.controller.orbat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.cue.cueContoller;
import com.controller.validation.ValidationController;
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.Create_Unit_moveDAO;
import com.dao.orbat.ReliefDAO;
import com.dao.orbat.ReliefDAOImpl;
import com.models.Miso_Orbat_Relief_Prgm;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.T_Domain_Value;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

import ch.qos.logback.core.joran.conditional.IfAction;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Create_Unit_Mov_DetailsController {
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Create_Unit_moveDAO create_unitDAO;
	
	
	public ReliefDAO rdao = new ReliefDAOImpl();
	
	@RequestMapping(value = "/admin/relief_prog", method = RequestMethod.GET)
	public ModelAndView Relief_prog(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("relief_prog", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		Mmap.put("getImdtFmnList", getImdtFmnList());
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("relief_prog_orbatTiles");
	}
	@SuppressWarnings("unchecked")
	public Boolean isReliefData_exits(String sus_no) {
		String hql = "FROM Miso_Orbat_Relief_Prgm r where r.sus_no=:sus_no and (r.miso_status is null or r.miso_status!='1') ";
		List<Miso_Orbat_Relief_Prgm> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("sus_no", sus_no);
			users = (List<Miso_Orbat_Relief_Prgm>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/relief_progAction",method = RequestMethod.POST)
	public ModelAndView relief_progAction(@ModelAttribute("relief_progCMD") Miso_Orbat_Relief_Prgm rs,HttpServletRequest request,ModelMap model,HttpSession sessionA,
			@RequestParam(value = "upload_document1", required = false) MultipartFile upload_document) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("relief_prog", roleid);	
		
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		/*
		String upload_documentExt = FilenameUtils.getExtension(upload_document.getOriginalFilename());
		if(!upload_documentExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:relief_prog");
		}
		*/
		
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		String auth_let_no = request.getParameter("auth_let_no");
		String ser_no = request.getParameter("ser_no");
		
		if(ser_no.equals("") || ser_no.equals(0) )
		{
			model.put("msg", "Please Enter Ser No.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(auth_let_no.equals(""))
		{
			model.put("msg", "Please Enter Auth Letter No.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(validation.checkLetter_noLength(auth_let_no)  == false){
	 		model.put("msg",validation.auth_let_noMSG);
			return new ModelAndView("redirect:relief_prog");
		}
		String  orbatFilePath = sessionA.getAttribute("orbatFilePath").toString();
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
/*		if (!upload_document.isEmpty()) {
			String fname = "";
			// code modify by Paresh on 05/05/2020
			try {
				byte[] bytes = upload_document.getBytes();
				File dir = new File(orbatFilePath);
				if (!dir.exists()){
					dir.mkdirs();
				}
				fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_RELIEF_DOC.PDF";
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
				rs.setUpload_document(fname);
			}
			catch (Exception e) {
	       }
		}
*/		String date1 = request.getParameter("date1");
		String period_from = request.getParameter("period_from");
		String period_to = request.getParameter("period_to");
		
		if(date1.equals(""))
		{
			model.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(validation.checkDateLength(date1)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:relief_prog");
		}
		if(period_from.equals(""))
		{
			model.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(validation.checkDateLength(period_from)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:relief_prog");
		}
		if(period_to.equals(""))
		{
			model.put("msg", "Please Select Date.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(validation.checkDateLength(period_to)  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:relief_prog");
		}
/*		if(rs.getUpload_document().equals(""))
		{
			model.put("msg", "Please Upload Document.");
			return new ModelAndView("redirect:relief_prog");
		}
		if(validation.checkUpload_authLetterLength(rs.getUpload_document())  == false){
	 		model.put("msg",validation.uploaddocumentMSG);
			return new ModelAndView("redirect:relief_prog");
		}
*/		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		
		rs.setSd_created_by(username);
		rs.setSd_created_on(date);
		rs.setSd_status("0");

		int countlength = 0;
		if(!request.getParameter("count").equals("")) {
			countlength = Integer.parseInt(request.getParameter("count"));
			for(int i=1;i<=countlength;i++) {
				
				String ch=request.getParameter("imdt_fmn"+i+"");
				String ch1[] =ch.split(",");
				
				sessionHQL.flush();
				String sus = request.getParameter("sus_no_"+i);
				if(sus.equals(""))
				{
					model.put("msg", "Please Enter SUS No");
					return new ModelAndView("redirect:relief_prog");
				}else {
					if(validation.sus_noLength(sus)  == false){
				 		model.put("msg",validation.sus_noMSG);
						return new ModelAndView("redirect:relief_prog");
					}
				}
				String unit_name = request.getParameter("unit_name_"+i+"");
				if(isReliefData_exits(sus)) {
					model.put("msg", unit_name+" Already Exists ");
					return new ModelAndView("redirect:relief_prog");
				}

				if(unit_name.equals(""))
				{
					model.put("msg", "Please Enter Unit Name");
					return new ModelAndView("redirect:relief_prog");
				}else {
					unit_name = unit_name.replace("&#40;","(");
					unit_name = unit_name.replace("&#41;",")");
				}
				if(validation.checkUnit_nameLength(unit_name)  == false){
			 		model.put("msg",validation.unit_nameMSG);
					return new ModelAndView("redirect:relief_prog");
				}
				
				String rplc_by_unit_sus_no = request.getParameter("rplc_by_unit_sus_no"+i+"");
				
				
				if(!rplc_by_unit_sus_no.equals("")) {
					if(validation.sus_noLength(rplc_by_unit_sus_no)  == false){
				 		model.put("msg",validation.sus_noMSG);
						return new ModelAndView("redirect:relief_prog");
					}
				}
				
				
				String mode_of_tpt = request.getParameter("mode_of_tpt"+i+"") + "," + request.getParameter("mode_of_tptOther"+i+"");
				if(mode_of_tpt.equals(""))
				{
					model.put("msg", "Please Select Mode Of Tpt");
					return new ModelAndView("redirect:relief_prog");
				}
				
				String imdt_fmn1 = ch1[0];
				if(imdt_fmn1.equals(""))
				{
					model.put("msg", "Please Select imdt Higher Formation");
					return new ModelAndView("redirect:relief_prog");
				}
				
				if(sus != null) {
					rs.setSus_no(sus);
					//rs.setUnit_name(unit_name);
					rs.setImdt_fmn(imdt_fmn1);
					rs.setArm_name(request.getParameter("arm_name"+i+""));
					rs.setMode_of_tpt(mode_of_tpt);
					rs.setNmb_date(request.getParameter("nmb_date"+i+""));
					rs.setIndn_de_indn_pd(request.getParameter("indn_de_indn_pd"+i+""));
					rs.setLoc(request.getParameter("loc"+i+""));
					rs.setNrs_code(request.getParameter("nrs_code"+i+""));
					
					
					String typ_of_stn = request.getParameter("typ_of_stn"+i+"");
					typ_of_stn = typ_of_stn.replace("&#40;","(");
					typ_of_stn = typ_of_stn.replace("&#41;",")");
					
					String typ_of_terrain = request.getParameter("typ_of_terrain"+i+"");
					typ_of_terrain = typ_of_terrain.replace("&#40;","(");
					typ_of_terrain = typ_of_terrain.replace("&#41;",")");
					
					rs.setTyp_of_stn(typ_of_stn);
					rs.setTyp_of_terrain(typ_of_terrain);	
					rs.setMov_of_adv_party_dt(request.getParameter("mov_of_adv_party_dt"+i+""));
					rs.setRplc_by_unit_sus_no(rplc_by_unit_sus_no);	
					rs.setRelief_yes_no(request.getParameter("answer"+i+""));
					rs.setRemarks(request.getParameter("remarks"+i+""));
					rs.setType_of_cl(request.getParameter("type_of_cl"+i+""));
				
					
					if(all.getSusNoActiveList(sessionA,sus).size() != 0) {
						sessionHQL.beginTransaction();
						sessionHQL.save(rs);
						sessionHQL.getTransaction().commit();
						sessionHQL.clear();
						
						
					}else {
						model.put("msg", "SUS No Currently Not Active");
						return new ModelAndView("redirect:relief_prog");
					}
				}
			}
		}else {
			String sus_no = request.getParameter("sus_no");
			if(isReliefData_exits(sus_no)) {
				model.put("msg", sus_no+" Already Exists ");
				return new ModelAndView("redirect:relief_prog");
			}
			if(sus_no.equals(""))
			{
				model.put("msg", "Please Enter SUS No");
				return new ModelAndView("redirect:relief_prog");
			}
			if(validation.sus_noLength(sus_no)  == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:relief_prog");
			}
			String imdt_fmn = request.getParameter("imdt_fmn");
			if(imdt_fmn.equals("0"))
			{
				model.put("msg", "Please Select Imdt Higher FMN No");
				return new ModelAndView("redirect:relief_prog");
			}
			String arm_name = request.getParameter("arm_name");
			if(arm_name.equals(""))
			{
				model.put("msg", "Please Select Arm Name");
				return new ModelAndView("redirect:relief_prog");
			}
			/*String code = request.getParameter("code");
			if(validation.LocCodeLength(code)  == false){
		 		model.put("msg",validation.loc_codeMSG);
				return new ModelAndView("redirect:relief_prog");
			}*/
			sessionHQL.beginTransaction();
			sessionHQL.save(rs);
			sessionHQL.getTransaction().commit();
			sessionHQL.clear();
		}
		sessionHQL.close();
		model.put("msg", "Data Uploaded Successfully");
		return new ModelAndView("redirect:relief_prog");
	}
		
	@RequestMapping(value = "/admin/main_body_move", method = RequestMethod.GET)
	public ModelAndView Main_body_move(ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,HttpServletRequest request) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("main_body_move", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			List<String> list = getAllBodyDetailsList_Without_enc(roleSusNo,sessionA);
			if(list.size() != 0) {
				Mmap.put("roleAccess", roleAccess);
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
				Mmap.put("list", list);
			}else {
				Mmap.put("msg", msg);
				return new ModelAndView("No_Movement_OrbatTiles");
			}
		}else {
			String jsCSS = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
					"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
					"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script> " + 
					"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
					"<script src=\"js/JS_CSS/jquery-1.10.2.js\" type=\"text/javascript\"></script>\r\n" + 
					"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n" + 
					"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
					"<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>";
			
				Mmap.put("jsCSS", jsCSS);
		}
		
		Date date= new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("getImdtFmnList", getImdtFmnList());
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("main_body_move_orbatTiles");
	}
	
	public List<String> getAllBodyDetailsList_Without_enc(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select r.id,"
				+ "(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no = 'Active')  as unit_name,"
				+ "r.imdt_fmn,"
				+ "r.indn_de_indn_pd,"
				+ "r.arm_name,"
				+ "r.mode_of_tpt,"
				+ "r.nmb_date,"
				+ "r.loc,"
				+ "(select code_value from Tb_Miso_Orbat_Code where code_type='Location' and code = (select nrs_code from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location')) as nrs,"
				+ "r.typ_of_stn,"
				+ "r.typ_of_terrain,"
				+ "r.mov_of_adv_party_dt,"
				+ "r.rplc_by_unit_sus_no,"
				+ "(select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active')  as rplc_by_unit_name , r.relief_yes_no,r.type_of_cl,r.remarks "
				+ "from "
				+ "Miso_Orbat_Relief_Prgm r where r.sd_status = '1' and r.unit_status is null and r.sus_no=:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list(); 
		tx.commit();
		session.close();
		return list;
	}
	
	
	@RequestMapping(value = "/getAllBodyDetailsList"  , method = RequestMethod.POST)
	public @ResponseBody List<String> getAllBodyDetailsList(String sus_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		ArrayList<String> list = create_unitDAO.getAllBodyDetailsList(sus_no);
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		List<String> Finallist = new ArrayList<String>();
		
		if(list.size() > 0) {
			String unit_name1 = "";
			if(!sus_no.equals("")) {
				unit_name1 = all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0);
			}
			
			String id= new String(Base64.encodeBase64(c.doFinal(String.valueOf(list.get(0)).getBytes())));
			String unit_name= new String(Base64.encodeBase64(c.doFinal(unit_name1.getBytes())));
			String imdt_fmn= new String(Base64.encodeBase64(c.doFinal(list.get(2).getBytes())));
			String indn_de_indn_pd= new String(Base64.encodeBase64(c.doFinal(list.get(3).getBytes())));
			String arm_name= new String(Base64.encodeBase64(c.doFinal(list.get(4).getBytes())));
			String mode_of_tpt= new String(Base64.encodeBase64(c.doFinal(list.get(5).getBytes())));
			String nmb_date= new String(Base64.encodeBase64(c.doFinal(list.get(6).getBytes())));
			String loc= new String(Base64.encodeBase64(c.doFinal(list.get(7).getBytes())));
			String nrs= new String(Base64.encodeBase64(c.doFinal(list.get(8).getBytes())));
			String typ_of_stn= new String(Base64.encodeBase64(c.doFinal(list.get(9).getBytes())));
			String typ_of_terrain= new String(Base64.encodeBase64(c.doFinal(list.get(10).getBytes())));
			String mov_of_adv_party_dt= new String(Base64.encodeBase64(c.doFinal(list.get(11).getBytes())));
			
			String rplc_by_unit_sus_no= "";
			if(list.get(12) != null){
				rplc_by_unit_sus_no= new String(Base64.encodeBase64(c.doFinal(list.get(12).getBytes())));
			}else {
				rplc_by_unit_sus_no = "";
			}
			
			String rplc_by_unit_name= "";
			if(!list.get(12).equals("")) {
				rplc_by_unit_name = all.getActiveUnitNameFromSusNo_Without_Enc(list.get(12),sessionA).get(0);
				rplc_by_unit_name= new String(Base64.encodeBase64(c.doFinal(rplc_by_unit_name.getBytes())));
			}else {
				rplc_by_unit_name ="";
			}
			
			String auth_let_no= list.get(14);
			
			String relief_yes_no = new String(Base64.encodeBase64(c.doFinal(list.get(15).getBytes())));
			String type_of_cl = new String(Base64.encodeBase64(c.doFinal(list.get(17).getBytes()))); 
			String remarks = new String(Base64.encodeBase64(c.doFinal(list.get(16).getBytes())));		

			
			
			Finallist.add(id); 					// 0
			Finallist.add(unit_name);			// 1	
			Finallist.add(imdt_fmn);			// 2
			Finallist.add(indn_de_indn_pd);		// 3
			Finallist.add(arm_name);	
			Finallist.add(mode_of_tpt);	
			Finallist.add(nmb_date);		
			Finallist.add(loc);
			Finallist.add(nrs);
			Finallist.add(typ_of_stn);						
			Finallist.add(typ_of_terrain);  			
			Finallist.add(mov_of_adv_party_dt);		
			Finallist.add(rplc_by_unit_sus_no);	
			Finallist.add(rplc_by_unit_name);
			Finallist.add(relief_yes_no);
			Finallist.add(type_of_cl);
			Finallist.add(remarks);
			Finallist.add(enckey+"8HGjyR==");
		}
		return Finallist;
	}
	
	
	//@RequestMapping(value = "/getImdtFmnList")
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> getImdtFmnList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade')) and status_sus_no = 'Active' order by unit_name");
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	//===============================================================================================
	
	@RequestMapping(value = "/mainbody_Action",method = RequestMethod.POST)
	public ModelAndView mainbody_Action(@ModelAttribute("main_bodyCMD") Miso_Orbat_Relief_Prgm rs,HttpServletRequest request,ModelMap model,HttpSession sessionA,@RequestParam(value = "upload_document2", required = false) MultipartFile upload_document) 
	{	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("main_body_move", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String upload_documentExt = FilenameUtils.getExtension(upload_document.getOriginalFilename());
		if(!upload_documentExt.equals("pdf")) {
			model.put("msg", "Only *.pdf file extension allowed");
			return new ModelAndView("redirect:relief_prog");
		}
		
		String fname = "";
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String username = sessionA.getAttribute("username").toString();
		 
		int id = 0;
		if(request.getParameter("id").equals("")){
			model.put("msg", "Please Select Valid Data");
			return new ModelAndView("redirect:main_body_move");
		}else{
			id = Integer.parseInt(request.getParameter("id")); 
		}
		 
		 String sus_no = request.getParameter("sus_no");
		 if(all.getSusNoActiveList(sessionA,sus_no).size() == 0) {
			 model.put("msg", "Please Enter Active SUS No");
			 return new ModelAndView("redirect:main_body_move");
		 }
		 if(sus_no.equals(""))
		 {
			model.put("msg", "Please Enter SUS No");
			return new ModelAndView("redirect:main_body_move");
		 }
		if(validation.sus_noLength(sus_no)  == false){
		 	model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:main_body_move");
		}
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		if (!upload_document.isEmpty()) {
			String  orbatFilePath = sessionA.getAttribute("orbatFilePath").toString();
			try {
				byte[] bytes = upload_document.getBytes();
				File dir = new File(orbatFilePath);
				if (!dir.exists()){
					dir.mkdirs();
				}
				fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_MOVEBODYDOC.PDF";
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
				rs.setArr_dep_report(fname);
			}
			catch (Exception e) {
			}
		}		
		
		
		String dep_date = request.getParameter("dep_date");
		if(dep_date.equals(""))
		{
			model.put("msg", "Please Enter Dep Date");
			return new ModelAndView("redirect:main_body_move");
		}
		
		//new for nmb date validation
		
				SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String nmb_date = request.getParameter("nmb_date");
				System.out.println("nmb date is  + " + nmb_date);
				String relief = request.getParameter("relief_yes_no");
				System.out.println("relief date is  + " + relief);
				
				Date depDate;
				Date nmbDate;

				try {

				depDate=dateFormat.parse(dep_date);
				nmbDate=dateFormat.parse(nmb_date);

				}catch(Exception e){ 

					model.put("msg", "Invalid Date Format");
					return new ModelAndView("redirect:main_body_move");
				}
				 
				Date currentDate =new Date();
				System.out.println("current date is " + currentDate);
				

				Calendar calender = Calendar.getInstance();
				calender.setTime(nmbDate);
				
				System.out.println(depDate + "departue date");

				if ("Yes".equals(relief)){
				calender.add( Calendar.WEEK_OF_YEAR,-2 );
				Date twoWeekbeforeNmbDate = calender.getTime();

				if( depDate.before(twoWeekbeforeNmbDate)){
					model.put("msg", "You can not select Departure Date 2 Week Before NMB Date.");
					return new ModelAndView("redirect:main_body_move");
					}
				}
				
				else if ("No".equals(relief)){
					calender.add( Calendar.WEEK_OF_YEAR,-1 );
					Date oneWeekbeforeNmbDate = calender.getTime();

					if( depDate.before(oneWeekbeforeNmbDate)){
						model.put("msg", "You can not select Departure Date 1 Week Before NMB Date.");
						return new ModelAndView("redirect:main_body_move");
						}
					}
				
				if ("Yes".equals(relief)){
					calender.add( Calendar.WEEK_OF_YEAR,-2 );
					Date twoWeekbeforeNmbDate = calender.getTime();

					if( ((Date) currentDate).before(twoWeekbeforeNmbDate)){
						model.put("msg", "You can not Submit report  2 Week Before Than NMB Date.");
						return new ModelAndView("redirect:main_body_move");
						}
					}
				
				else if ("No".equals(relief)){
					calender.add( Calendar.WEEK_OF_YEAR,-1 );
					Date oneWeekbeforeNmbDate = calender.getTime();

					if( ((Date) currentDate).before(oneWeekbeforeNmbDate)){
						model.put("msg", "You can not Submit report 1 Week Before NMB Date.");
						return new ModelAndView("redirect:main_body_move");
						}
					}
				
		
		
		String arr_date = request.getParameter("arr_date");
		if(dep_date.equals(""))
		{
			 model.put("msg", "Please Enter Arrival Date");
			return new ModelAndView("redirect:main_body_move");
		}
//		if(rs.getArr_dep_report().equals("") || rs.getArr_dep_report().equals(null) )
//		{
//			model.put("msg", "Please Upload Document");
//			return new ModelAndView("redirect:main_body_move");
//		}
				
		String unit_auth_letter_no = request.getParameter("unit_auth_letter_no");
		if(unit_auth_letter_no.equals(""))
		{
			 model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:main_body_move");
		}
		if(validation.checkLetter_noLength(unit_auth_letter_no)  == false){
		 	model.put("msg",validation.auth_let_noMSG);
			return new ModelAndView("redirect:main_body_move");
		}
		
		String unit_auth_letter_date = request.getParameter("unit_auth_letter_date");
		if(unit_auth_letter_date.equals(""))
		{
			 model.put("msg", "Please Enter Auth Letter Date");
			 return new ModelAndView("redirect:main_body_move");
		}
		if(validation.checkDateLength(unit_auth_letter_date)  == false){
			model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:main_body_move");
		}
		 
		 
		 Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionUpdate.beginTransaction();
		
		 String hqlUpdate = "update Miso_Orbat_Relief_Prgm r set r.dep_date=:dep_date ,arr_date=:arr_date,arr_dep_report=:arr_dep_report ,unit_created_on=:unit_created_on,unit_status=:unit_status ,unit_created_by=:unit_created_by ,unit_auth_letter_no=:unit_auth_letter_no, unit_auth_letter_date=:unit_auth_letter_date where r.id = :id";
		 sessionUpdate.createQuery( hqlUpdate ).setString( "dep_date", dep_date).setString( "arr_date", arr_date).setString( "arr_dep_report", rs.getArr_dep_report()).setString( "unit_created_on", date).setString( "unit_created_by", username).setString( "unit_status","0").setString( "unit_auth_letter_no",unit_auth_letter_no).setString( "unit_auth_letter_date",unit_auth_letter_date).setInteger( "id",id).executeUpdate();
		
		 tx.commit();
		 sessionUpdate.close();
		 
		 model.put("msg", "Data Saved Successfully");
		 return new ModelAndView("redirect:main_body_move");
		
	}	
		
	


	@RequestMapping(value="/approved_sd_reliefReport", method = RequestMethod.GET)
	public ModelAndView approved_sd_reliefReport(ModelMap model,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		return new ModelAndView("approved_sd_reliefTile");
	}
	
	@RequestMapping(value="/approved_sd_reliefReport1", method = RequestMethod.POST)
	public ModelAndView approved_sd_reliefReport1(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "period_from1", required = false) String period_from,
			@RequestParam(value = "period_to1", required = false) String period_to,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "auth_letter1", required = false) String  auth_letter,
			@RequestParam(value = "ser_no1", required = false) String  ser_no,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			HttpSession sessionA) {
		
		auth_letter = auth_letter.replace("&#40;","(");
		auth_letter = auth_letter.replace("&#41;",")");
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
	//	String roleType = sessionA.getAttribute("roleType").toString();
		
		//model.put("roleType", roleType);
		if(!period_from.equals(""))
		{
			if(validation.checkDateLength(period_from)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_sd_reliefReport");
			}
		}
		if(!period_to.equals(""))
		{
			if(validation.checkDateLength(period_to)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_sd_reliefReport");
			}
		}
		
		if(status.equals("")){
			model.put("msg", "Please Select * mendetory Fields!");
			return new ModelAndView("redirect:approved_sd_reliefReport");
		}else{
			model.put("getReliefReportList", rdao.getReliefReportList(period_from,period_to,status,sus_no));
			model.put("period_from1",period_from);
			model.put("period_to1",period_to);
			model.put("status1",status);
			model.put("sus_no1",sus_no);
			model.put("unit_name1",unit_name);
		}
		
		if(!auth_letter.equals("")){
			model.put("getSearchReliefReportList", rdao.getSearchReliefReportList(auth_letter,ser_no,status));
			model.put("period_from1",period_from);
			model.put("period_to1",period_to);
			model.put("status1",status);
			model.put("auth_letter1",auth_letter);
		}
		return new ModelAndView("approved_sd_reliefTile");
	}
	
	@RequestMapping(value = "/approvedSdReliefDetails",method = RequestMethod.POST)
	public @ResponseBody ModelAndView approvedSdReliefDetails(ModelMap model,HttpServletRequest request,HttpSession sessionA,String auth_letter,String status,String ser_no) {

		auth_letter = auth_letter.replace("&#40;","(");
		auth_letter = auth_letter.replace("&#41;",")");
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}		
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("msg", rdao.approvedSdReliefDetails(auth_letter,status,ser_no,username,date)); 
		return new ModelAndView("redirect:approved_sd_reliefReport");
	}
	
	@RequestMapping(value = "/rejectSdReliefDetails",method = RequestMethod.POST)
	public @ResponseBody ModelAndView rejectSdReliefDetails(ModelMap model,HttpServletRequest request,HttpSession sessionA,String auth_letter,String status,String serno) {

		auth_letter = auth_letter.replace("&#40;","(");
		auth_letter = auth_letter.replace("&#41;",")");
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		model.put("msg", rdao.rejectedSdReliefDetails(auth_letter,serno,status,username,date)); 
		return new ModelAndView("redirect:approved_sd_reliefReport");
	}
	
	@RequestMapping(value = "/editRelief")
	public ModelAndView editRelief(@Valid @Validated @ModelAttribute("editId") int entryId,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA,HttpServletRequest request){
		String roleType = sessionA.getAttribute("roleType").toString();
		
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
				if(val == false) {	return new ModelAndView("AccessTiles"); 		}
				
		Miso_Orbat_Relief_Prgm relief = rdao.getLatLon(entryId);
		model.put("getImdtFmnList", getImdtFmnList());
		model.put("getArmNameList", all.getArmNameList());
		model.put("relief_progCMD",relief);	
		
		if(!relief.getSus_no().equals("")) {
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(relief.getSus_no(),sessionA).get(0));
		}
		if(!relief.getRplc_by_unit_sus_no().equals("")) {
			model.put("rplc_by_unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(relief.getRplc_by_unit_sus_no(),sessionA).get(0));
		}
		if(relief.getLoc() != null) {
			model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(relief.getLoc(),sessionA).get(0));
		}
		return new ModelAndView("showrelieftile");
	}

	@RequestMapping(value = "/deleteRelief",method = RequestMethod.POST)
	public ModelAndView deleteRelief(@Valid @Validated @ModelAttribute("deleteId") int entryId,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", rdao.deleteReliefPro(entryId));	
		return new ModelAndView("redirect:approved_sd_reliefReport");
	}
	
	@RequestMapping(value = "/updateRelief",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateRelief(Miso_Orbat_Relief_Prgm rp ,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_sd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		
    	if(rp.getSus_no().equals("")){
    		model.put("msg","Please Select SUS No");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(validation.sus_noLength(rp.getSus_no())  == false){
	 		model.put("msg",validation.sus_noMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:editRelief");
		}
    	/*if(rp.getUnit_name().equals("")){
    		model.put("msg","Please Select Unit Name");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(validation.checkUnit_nameLength(rp.getUnit_name())  == false){
	 		model.put("msg",validation.unit_nameMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:editRelief");
		}*/
		if(rp.getImdt_fmn().equals("")){
			model.put("msg","Please Select IMDT Higher Formation");
			model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(rp.getArm_name().equals("0")){
    		model.put("msg","Please Select Arm Code");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(rp.getNmb_date().equals("")){
    		model.put("msg","Please Select NMB Date");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(rp.getIndn_de_indn_pd().equals("")){
    		model.put("msg","Please Select Arm Code");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(rp.getLoc().equals("")){
    		model.put("msg","Please Select Location");
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:editRelief");
    	}
    	if(validation.LocCodeLength(rp.getLoc())  == false){
	 		model.put("msg",validation.loc_codeMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:editRelief");
		}
    	if(validation.checkTypeOfStnLength(rp.getTyp_of_stn())  == false){
	 		model.put("msg",validation.typ_of_stnMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:editRelief");
		}
    	if(validation.checkTypeOfTerrainLength(rp.getTyp_of_terrain())  == false){
	 		model.put("msg",validation.typ_of_terrainMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:editRelief");
		}
    	if(!rp.getRplc_by_unit_sus_no().equals("")){
        	if(validation.sus_noLength(rp.getRplc_by_unit_sus_no())  == false){
    	 		model.put("msg",validation.sus_noMSG);
    	 		model.put("editId", rp.getId());
    			return new ModelAndView("redirect:editRelief");
    		}
    	}
    	
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Miso_Orbat_Relief_Prgm where id=:id");
    	q.setParameter("id", rp.getId());
    	Miso_Orbat_Relief_Prgm Relief_ProgAll = (Miso_Orbat_Relief_Prgm) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		
		if(Relief_ProgAll.getId() > 0) {
			String typ_of_stn = rp.getTyp_of_stn();
			typ_of_stn = typ_of_stn.replace("&#40;","(");
			typ_of_stn = typ_of_stn.replace("&#41;",")");
			rp.setTyp_of_stn(typ_of_stn);
			
			String typ_of_terrain = rp.getTyp_of_terrain();
			typ_of_terrain = typ_of_terrain.replace("&#40;","(");
			typ_of_terrain = typ_of_terrain.replace("&#41;",")");
			rp.setTyp_of_terrain(typ_of_terrain);
			rp.setAuth_let_no(Relief_ProgAll.getAuth_let_no());
			rp.setDate1(Relief_ProgAll.getDate1());
			rp.setPeriod_from(Relief_ProgAll.getPeriod_from());
			rp.setPeriod_to(Relief_ProgAll.getPeriod_to());
			//rp.setUpload_document(Relief_ProgAll.getUpload_document());
			rp.setSd_created_by(Relief_ProgAll.getSd_created_by());
			rp.setSd_created_on(Relief_ProgAll.getSd_created_on());
			rp.setSd_status(Relief_ProgAll.getSd_status());
			//rp.setUpload_document(Relief_ProgAll.getUpload_document());
			rp.setSd_updated_on(date);
			rp.setSd_updated_by(username);
			rp.setSd_status("0");
			
			rdao.UpdateDataEntry(rp);		
			model.put("msg", "Data Updated Successfully");
		}else {
			model.put("msg", "Data Not Updated");
		}
	    return new ModelAndView("redirect:approved_sd_reliefReport");
	}	
	
	// REPORT SEARCH MAIN BODY MOVEMENT
	
	@RequestMapping(value="/approved_unit_main_body_move")
	public ModelAndView approved_unit_main_body_move(HttpSession sessionA ,ModelMap model,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_unit_main_body_move", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString(); 
	
		if(roleAccess.equals("Unit")){
			model.put("roleAccess", roleAccess);
			model.put("sus_no1",roleSusNo);
			model.put("unit_name1",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		}else {
			String jsCSS ="<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>" +
					"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
					"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
					"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>" + 
					"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
					"<script src=\"js/JS_CSS/jquery-1.10.2.js\" type=\"text/javascript\"></script>\r\n" + 
					"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n" + 
					"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n";
			model.put("jsCSS", jsCSS);
		}
		List<String> list = new ArrayList<>();
		model.put("getsearchMainBodyReportList", list);
		
		model.put("msg",msg);
		return new ModelAndView("approved_unit_main_body_moveTile");
	}
	
	// REPORT SEARCH MAIN BODY MOVEMENT
	
	@RequestMapping(value="/approved_unit_main_body_move1", method = RequestMethod.POST)
	public ModelAndView approved_unit_main_body_move1(ModelMap model,HttpServletRequest request
			,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "status1", required = false) String status,
			HttpSession sessionA) {
		
		unit_name = unit_name.replace("&#40;","(");
		unit_name = unit_name.replace("&#41;",")");
		
		if(validation.sus_noLength(sus_no)  == false){
	 		model.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:approved_unit_main_body_move");
		}
		if(validation.checkUnit_nameLength(unit_name)  == false){
	 		model.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:approved_unit_main_body_move");
		}
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString(); 
		Boolean val = roledao.ScreenRedirect("approved_unit_main_body_move", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(status != null && !status.equals("")){
			String roleType = sessionA.getAttribute("roleType").toString();
			
			model.put("sus_no1", sus_no);
			model.put("unit_name1", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			model.put("status1", status);
			
			if(roleAccess.equals("Unit")){
				List<Miso_Orbat_Relief_Prgm> list = rdao.getsearchMainBodyReportList(roleSusNo,status,"");
				if(list.size() != 0) {
					model.put("roleAccess", roleAccess);
					model.put("sus_no1",roleSusNo);
					if(!roleSusNo.equals("")) {
						model.put("unit_name1",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));	
					}
					model.put("getsearchMainBodyReportList", list);
				}
			}else {
				if(roleAccess.equals("Formation") ){
					model.put("roleType", "APP");
				}
				if(roleAccess.equals("MISO") ) {
					model.put("roleType", roleType);
				}
				
				String jsCSS ="<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>" +
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>" + 
						"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
						"<script src=\"js/JS_CSS/jquery-1.10.2.js\" type=\"text/javascript\"></script>\r\n" + 
						"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n" + 
						"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n";
				model.put("jsCSS", jsCSS);
				
				List<Miso_Orbat_Relief_Prgm> list = rdao.getsearchMainBodyReportList(sus_no,status,unit_name);
				model.put("getsearchMainBodyReportList", list);
			}
		}
		model.put("msg",msg);
		return new ModelAndView("approved_unit_main_body_moveTile");
	}
	
	// Approved SEARCH MAIN BODY MOVEMENT
	
	@RequestMapping(value = "/approvedUnitReliefDetails" , method = RequestMethod.POST)
	public ModelAndView approvedUnitReliefDetails(ModelMap model,HttpServletRequest request,HttpSession sessionA,int id2,String status2,String sus_no2) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString(); 
		Boolean val = roledao.ScreenRedirect("approved_unit_main_body_move", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(roleAccess.equals("Formation")) {
			roleType = "APP";
		}
		
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = sessionA.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		
		if(roleAccess.equals("Unit")){
			model.put("msg",rdao.approvedUnitReliefDetails(id2,status2,username,date,roleSusNo));
		}else {
			model.put("msg",rdao.approvedUnitReliefDetails(id2,status2,username,date,sus_no2));
		}
		return new ModelAndView("redirect:approved_unit_main_body_move");
	}
	
	// REPORT AMENDMENT IN RELIEF PROGRAM
	
	@RequestMapping(value="/getamendmentreport", method = RequestMethod.GET)
	public ModelAndView getamendmentreport(ModelMap model,HttpSession sessionA,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		
		String roleType = sessionA.getAttribute("roleType").toString();
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("getamendmentreport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		ArrayList<List<String>> list = rdao.getAmendmentReportList();
		for(int i = 0 ;i<list.size();i++) {
			String Update = "onclick=\"  if (confirm('Are You Sure you want to Amendment in Relief Program ?') ){edit("+list.get(i).get(0)+")}else{ return false;}\"";
    		String updateButton ="<i class='action_icons action_update' "+Update+" title='Amendment Data'></i>";
    		
    		String UpdateFunction = "function edit(id) {  document.getElementById(\"editId\").value=id;	 document.getElementById(\"editForm\").submit(); }";
    		
    		String button ="";
    		if(roleType.equals("ALL")) {
    			button += updateButton;
    			model.put("UpdateFunction",  UpdateFunction);
			}
			if(roleType.equals("APP")) {
			
			}
			if(roleType.equals("DEO")) {
				button += updateButton;
				model.put("UpdateFunction",  UpdateFunction);
			}
    		list.get(i).add(button);
    	}
		model.put("getAmendmentReportList",  list);
		
		model.put("msg",msg);
		return new ModelAndView("AmendmentReportTile");
	}
	
	// EDIT AMENDMENT IN RELIEF PROGRAM
	
	@RequestMapping(value = "/editamendment",method = RequestMethod.POST)
	public ModelAndView amendment(@Valid @Validated @ModelAttribute("editId") int entryId,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("getamendmentreport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		Miso_Orbat_Relief_Prgm reliefList = rdao.editamend(entryId);
		
		model.put("relief_progCMD", reliefList);
		model.put("getArmNameList", all.getArmNameList());
		model.put("getImdtFmnList", getImdtFmnList());
		
		if(!reliefList.getSus_no().equals("")) {
			model.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(reliefList.getSus_no(),sessionA).get(0));
		}
		if(!reliefList.getRplc_by_unit_sus_no().equals("")) {
			model.put("rplc_by_unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(reliefList.getRplc_by_unit_sus_no(),sessionA).get(0));
		}
		if(!reliefList.getLoc().equals("")) {
			model.put("LOC_NRS_TypeLOC_TrnType", all.getLOC_NRS_TypeLOC_TrnType(reliefList.getLoc(),sessionA).get(0));
		}
		return new ModelAndView("amendmenttile");
	}
	
	// EDIT Action AMENDMENT IN RELIEF PROGRAM
	
	@RequestMapping(value = "/updateAmendment",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateAmendment(Miso_Orbat_Relief_Prgm rp ,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("getamendmentreport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String getAuth_let_no1 = rp.getAuth_let_no();
		getAuth_let_no1 = getAuth_let_no1.replace("&#40;","(");
		getAuth_let_no1 = getAuth_let_no1.replace("&#41;",")");
		rp.setAuth_let_no(getAuth_let_no1);
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		if(rp.getAuth_let_no().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
		if(validation.checkLetter_noLength(rp.getAuth_let_no())  == false){
	 		model.put("msg",validation.auth_let_noMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(rp.getDate1().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
        }
    	if(validation.checkDateLength(rp.getDate1())  == false){
	 		model.put("msg",validation.dateMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(rp.getPeriod_from().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
        }
    	if(validation.checkDateLength(rp.getPeriod_from())  == false){
	 		model.put("msg",validation.dateMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(rp.getPeriod_to().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
        }
    	
    	
    	if(rp.getMov_of_adv_party_dt().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
        }

    	if(validation.checkDateLength(rp.getMov_of_adv_party_dt())  == false){
	 		model.put("msg",validation.dateMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	    	
    	if(validation.checkDateLength(rp.getPeriod_to())  == false){
	 		model.put("msg",validation.dateMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(rp.getSus_no().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(validation.sus_noLength(rp.getSus_no())  == false){
	 		model.put("msg",validation.sus_noMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	/*if(rp.getUnit_name().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(validation.checkUnit_nameLength(rp.getUnit_name())  == false){
	 		model.put("msg",validation.unit_nameMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}*/
		if(rp.getImdt_fmn().equals("")){
			model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(rp.getArm_name().equals("0")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(rp.getNmb_date().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(rp.getIndn_de_indn_pd().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(rp.getLoc().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(validation.LocCodeLength(rp.getLoc())  == false){
	 		model.put("msg",validation.loc_codeMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(validation.checkTypeOfStnLength(rp.getTyp_of_stn())  == false){  
	 		model.put("msg",validation.typ_of_stnMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	if(validation.checkTypeOfTerrainLength(rp.getTyp_of_terrain())  == false){
	 		model.put("msg",validation.typ_of_terrainMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}
    	/*if(rp.getRplc_by_unit_sus_no().equals("")){
    		model.put("editId", rp.getId());
    		return new ModelAndView("redirect:getamendmentreport");
    	}
    	if(validation.sus_noLength(rp.getRplc_by_unit_sus_no())  == false){
	 		model.put("msg",validation.sus_noMSG);
	 		model.put("editId", rp.getId());
			return new ModelAndView("redirect:getamendmentreport");
		}*/
    	rp.setSd_status("0");
    	
		rdao.UpdateAmendment(rp);		
		model.put("msg", "Data Updated Successfully");
	    return new ModelAndView("redirect:getamendmentreport");
	}	
	
	
	// Download Document From ID
    @RequestMapping(value = "/admin/getDownloadImageRelief", method = RequestMethod.POST)
    public ModelAndView getDownloadImageRelief(@ModelAttribute("id1") int id,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
         String EXTERNAL_FILE_PATH = "";
         List<Miso_Orbat_Relief_Prgm> list =  rdao.getRelief_ProgByid(id);
         if(list.size() > 0) {
        	 EXTERNAL_FILE_PATH = list.get(0).getUpload_document();
         }
         
         File file = null;
         file = new File(EXTERNAL_FILE_PATH);
         try{
        	 if(!file.exists()){
                        return new ModelAndView("redirect:getreliefreport?msg=Sorry. The file you are looking for does not exist");
             }
        }
        catch(Exception exception){
        	
        }
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
                mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = null;
        try {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
                return new ModelAndView("redirect:getreliefreport?msg=Download Successfully");
        } catch (FileNotFoundException e) {
        	
        }
        return new ModelAndView("redirect:getreliefreport?msg=Download Successfully");
    }

	@RequestMapping(value = "/getSusNoActiveBySD",method = RequestMethod.POST)
	public @ResponseBody List<String> getSusNoActiveBySD(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct sus_no from Miso_Orbat_Relief_Prgm where sd_status = '1' and unit_status is null and upper(sus_no) like :sus_no order by sus_no ").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
		}
		
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
	    // Enc Key Append Last value of List
	    if(list.size() != 0) {
	    	FinalList.add(enckey+"kQsHyg==");
	    }
	    return FinalList;
	}
	
	@RequestMapping(value = "/getSusNoActiveBySDAndUnitPending",method = RequestMethod.POST)
	public @ResponseBody List<String> getSusNoActiveBySDAndUnitPending(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Relief_Prgm where upper(sus_no) like :sus_no and sd_status='1' and unit_status in ('0','1') order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
		}
		if(roleAccess.equals("Formation")) {
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Relief_Prgm where upper(sus_no) like :sus_no and sd_status='1' and unit_status  in ('0','1') and imdt_fmn=:roleFormationNo order by sus_no ").setMaxResults(10);
			q.setParameter("roleFormationNo", roleFormationNo);
			q.setParameter("sus_no", sus_no.toUpperCase()+"%");
		}
		if(roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
			q = session.createQuery("select distinct sus_no from Miso_Orbat_Relief_Prgm where upper(sus_no) =:sus_no and sd_status='1' and unit_status in ('0','1') order by sus_no").setMaxResults(10);
			q.setParameter("sus_no", roleSusNo);
		}
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
		}
		
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
	    // Enc Key Append Last value of List
	    if(list.size() != 0) {
	    	FinalList.add(enckey+"kQsHyg==");
	    }
	    return FinalList;
	}
	
	
	@RequestMapping(value = "/getUnitNameActiveBySDAndUnitPending",method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitNameActiveBySDAndUnitPending(String unit_name,HttpSession sessionA) {
		
		unit_name = unit_name.replace("&#40;","(");
		unit_name = unit_name.replace("&#41;",")");
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		//String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no ='Active' and sus_no in (select distinct sus_no from Miso_Orbat_Relief_Prgm where sd_status='1' and unit_status in ('0','1') )").setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase()+"%");
		}
		if(roleAccess.equals("Formation")) {
			q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no ='Active' and sus_no in (select distinct sus_no from Miso_Orbat_Relief_Prgm where sd_status='1' and unit_status in ('0','1') and imdt_fmn=:roleFormationNo )").setMaxResults(10);
			q.setParameter("roleFormationNo", roleFormationNo);
			q.setParameter("unit_name","%"+unit_name.toUpperCase()+"%");
		}
		if(roleAccess.equals("Unit")) {
			q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no ='Active' and sus_no in (select distinct sus_no from Miso_Orbat_Relief_Prgm where upper(sus_no) =:sus_no and sd_status='1' and unit_status in ('0','1')) order by unit_name ").setMaxResults(10);
			q.setParameter("sus_no", roleSusNo);
			q.setParameter("unit_name","%"+unit_name.toUpperCase()+"%");
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
		}
		
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
	    // Enc Key Append Last value of List
	    if(list.size() != 0) {
	    	FinalList.add(enckey+"kQsHyg==");
	    }
	    return FinalList;
	}

	private cueContoller M = new cueContoller();
	
	///n///n///
	@RequestMapping(value="/approved_nsd_reliefReport", method = RequestMethod.GET)
	public ModelAndView approved_nsd_reliefReport(ModelMap model,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_nsd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		String select="<option value='0'>--Select--</option>";
		model.put("selectArmNameList",select);
		model.put("getArmNameList", M.getArmNameListRelifPgme());
		return new ModelAndView("approved_nsd_reliefTile");
	}
	
	@RequestMapping(value="/approved_nsd_reliefReport1", method = RequestMethod.POST)
	public ModelAndView approved_nsd_reliefReport1(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "period_from1", required = false) String period_from,
			@RequestParam(value = "period_to1", required = false) String period_to,
			@RequestParam(value = "status1", required = false) String status,
			HttpSession sessionA) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_nsd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
	
		if(!period_from.equals("")){
			if(validation.checkDateLength(period_from)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_nsd_reliefReport");
			}
		}
		if(!period_to.equals("")){
			if(validation.checkDateLength(period_to)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_nsd_reliefReport");
			}
		}
		if(status.equals("")){
			model.put("msg", "Please Select * mendetory Fields!");
			return new ModelAndView("redirect:approved_sd_reliefReport");
		}else{			
			model.put("getReliefReportList", rdao.getReliefNReportList(period_from,period_to,"1",status.substring(0, 2)));
			String select="<option value='0'>--Select--</option>";
			model.put("selectArmNameList",select);
			
			model.put("period_from1",period_from);
			model.put("period_to1",period_to);
			model.put("status1",status);
			model.put("getArmNameList", M.getArmNameListRelifPgme());
		}
		return new ModelAndView("approved_nsd_reliefTile");
	}
	
	@RequestMapping(value="/approved_nsd_reliefReport_lt", method = RequestMethod.POST)
	public ModelAndView approved_nsd_reliefReport1(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "period_from1", required = false) String period_from,
			@RequestParam(value = "period_to1", required = false) String period_to,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "auth_letter1", required = false) String  auth_letter,
			HttpSession sessionA) {
		
		auth_letter = auth_letter.replace("&#40;","(");
		auth_letter = auth_letter.replace("&#41;",")");
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("approved_nsd_reliefReport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(!period_from.equals("")){
			if(validation.checkDateLength(period_from)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_nsd_reliefReport");
			}
		}
		if(!period_to.equals("")){
			if(validation.checkDateLength(period_to)  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:approved_nsd_reliefReport");
			}
		}
		
		if(status.equals("")){
			model.put("msg", "Please Select * mendetory Fields!");
			return new ModelAndView("redirect:approved_nsd_reliefReport");
		}else{
			model.put("getReliefReportList", rdao.getReliefNReportList(period_from,period_to,"1",status.substring(0, 2)));
			String select="<option value='0'>--Select--</option>";
			model.put("selectArmNameList",select);
			model.put("period_from1",period_from);
			model.put("period_to1",period_to);
			model.put("status1",status);
			model.put("getArmNameList", M.getArmNameListRelifPgme());
		}
		
		if(!auth_letter.equals("")){
			model.put("getSearchReliefReportList", create_unitDAO.getSearchNReliefReportList(auth_letter,status));
			model.put("getEqptInstrList", create_unitDAO.getInstrList("E"));
			model.put("getVehInstrList", create_unitDAO.getInstrList("V"));
			model.put("period_from1",period_from);
			model.put("period_to1",period_to);
			model.put("status1",status);
			model.put("auth_letter1",auth_letter);
		}
		return new ModelAndView("approved_nsd_reliefTile");
	}
	
	@RequestMapping(value="/new_gen_final_relief", method = RequestMethod.GET)
	public ModelAndView new_gen_final_reliefTile(ModelMap model,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
		
		
		
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();	
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		if(roleAccess.equals("Unit")) {			
			q = session.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm where upper(sus_no) =:sus_no order by SUBSTRING(date1,1,4)");		
			q.setParameter("sus_no", roleSusNo);

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		
		tx.commit();
		session.close();
			if (list.size()==0)
			{
				return new ModelAndView("No_Movement_OrbatTiles");
			}
		model.put("roleSusNo",roleSusNo); 
		model.put("listyear",list);
		model.put("roleAccess",sessionA.getAttribute("roleAccess").toString());
		}
		
		String armsd="",qry = "";
		
		
		
		if(roleAccess.equals("MISO")) {							
			Session sessionB = HibernateUtil.getSessionFactory().openSession();
			Transaction txB = sessionB.beginTransaction();
			Query qB = null;				
								
			qB = sessionB.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm order by SUBSTRING(date1,1,4) desc");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) qB.list();
			txB.commit(); 
			sessionB.close(); 
			if (list.size()==0)
			{
				return new ModelAndView("AccessTiles");
			}
			
			model.put("viewArm",M.getSDArmNameList());	
			model.put("listyear",list);
			model.put("roleAccess",sessionA.getAttribute("roleAccess").toString());
			model.put("roleSubAccess",sessionA.getAttribute("roleSubAccess").toString());
		}
		
		
		
		if(roleAccess.equals("Formation")) {			
			if(roleSubAccess.equals("Command")) {
				qry +="SUBSTRING(imdt_fmn,1,1)||'000000000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,1)||'000000000'"; 
			}
			if(roleSubAccess.equals("Corps")) {
				qry +="SUBSTRING(imdt_fmn,1,3)||'0000000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,3)||'0000000'";
			}
			if(roleSubAccess.equals("Division")) {
				qry +="SUBSTRING(imdt_fmn,1,6)||'0000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,6)||'0000'";
			}
			if(roleSubAccess.equals("Brigade")) {
				qry +="imdt_fmn =:sus_no";
				armsd="imdt_fmn";
			} 
		}
		
		if(roleAccess.equals("Formation")) {							
				Session sessionB = HibernateUtil.getSessionFactory().openSession();
				Transaction txB = sessionB.beginTransaction();
				Query qB = null;				
								
				qB = sessionB.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm where "+qry+" order by SUBSTRING(date1,1,4) desc");
				qB.setParameter("sus_no", sessionA.getAttribute("roleFormationNo").toString()); 				
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) qB.list();
				txB.commit(); 
				sessionB.close(); 
				if (list.size()==0)
				{
					return new ModelAndView("AccessTiles");
				}
				
				model.put("viewArm",M.getArmNameListforSD(armsd,sessionA.getAttribute("roleFormationNo").toString()));	
				model.put("listyear",list);
				model.put("roleAccess",sessionA.getAttribute("roleAccess").toString());
				model.put("roleSubAccess",sessionA.getAttribute("roleSubAccess").toString());
			}
		
		
		return new ModelAndView("new_gen_final_reliefTile");
	}
	
	
	@RequestMapping(value="/new_gen_final_relief1", method = RequestMethod.POST)
	public ModelAndView new_gen_final_relief1(ModelMap model,HttpServletRequest request,
			@RequestParam(value = "listyear1", required = false) String listyear,
			@RequestParam(value = "viewArm1", required = false) String viewArm,
			HttpSession sessionA) {
		
		
		
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();	
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();

		
		if(roleAccess.equals("Unit")) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm where upper(sus_no) =:sus_no order by SUBSTRING(date1,1,4)  desc");
			q.setParameter("sus_no", roleSusNo);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
		
			tx.commit();
			session.close();
			model.put("listyear",list);
		}

		if(roleAccess.equals("Unit")) {

			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query q1 = null;
			q1 = session1.createQuery(" select  auth_let_no,date1,period_from,period_to,sd_status,count(*),ser_no from "
					+" Miso_Orbat_Relief_Prgm where substring(date1,1,4)=:list_year and sd_status='1' "
					+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm where sus_no=:sus_no) "
					+"and ser_no in (select distinct ser_no from Miso_Orbat_Relief_Prgm where sus_no=:sus_no) "
					+"group by auth_let_no,date1,period_from,period_to,sd_status,ser_no order by auth_let_no,to_number(ser_no,'99'),date1 desc ");					
			q1.setParameter("list_year", listyear);  
			q1.setParameter("sus_no", roleSusNo);
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q1.list();
			tx1.commit();
			session1.close();
			
			model.put("getReliefReportList",list1);
			model.put("listyear1",listyear);
		}

		if(roleAccess.equals("MISO")) {							
			Session sessionB = HibernateUtil.getSessionFactory().openSession();
			Transaction txB = sessionB.beginTransaction();
			Query qB = null;				
								
			qB = sessionB.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm order by SUBSTRING(date1,1,4) desc");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) qB.list();
			txB.commit(); 
			sessionB.close(); 
			if (list.size()==0)
			{
				return new ModelAndView("AccessTiles");
			}
			
			model.put("viewArm",M.getSDArmNameList());	
			model.put("listyear",list);
			model.put("roleAccess",sessionA.getAttribute("roleAccess").toString());
			model.put("roleSubAccess",sessionA.getAttribute("roleSubAccess").toString());
		}
				
		if(roleAccess.equals("MISO")) {	
			String qry1;
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx1 = session1.beginTransaction();
			Query q1 = null;				
			if (viewArm.equals("nothing"))
			{
			
			qry1 =   " select  auth_let_no,date1,period_from,period_to,sd_status,count(*),ser_no from "
					+" Miso_Orbat_Relief_Prgm where substring(date1,1,4)='"+listyear+"' and sd_status='1' "
					+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm) "
					+"group by auth_let_no,date1,period_from,period_to,sd_status,ser_no order by auth_let_no,to_number(ser_no,'99'),date1 desc ";
			
			}
			else
			{
			qry1 =   "select  auth_let_no,date1,period_from,period_to,sd_status,count(*),ser_no from "
					+" Miso_Orbat_Relief_Prgm where substring(arm_name,1,2)||'00'='"+viewArm+"' and substring(date1,1,4)='"+listyear+"' and sd_status='1' "
					+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm) "
					+" group by auth_let_no,date1,period_from,period_to,sd_status,ser_no order by auth_let_no,to_number(ser_no,'99'),date1 desc ";
			}
			
			q1 = session1.createQuery(qry1);
			//q1.setParameter("sus_no", sessionA.getAttribute("roleFormationNo").toString());				
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q1.list();
			tx1.commit();
			session1.close();
			model.put("viewArm",M.getSDArmNameList());
			model.put("getReliefReportList",list1);
			model.put("listyear1",listyear);
	}		
		
		
		
		String armsd="";
		String qry=" ",qry1 = "";
		
		if(roleAccess.equals("Formation")) {			
			if(roleSubAccess.equals("Command")) {
				//qry +="SUBSTRING(imdt_fmn,1,1)||'000000000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,1)||'000000000'"; 
			}
			if(roleSubAccess.equals("Corps")) {
				qry +="SUBSTRING(imdt_fmn,1,3)||'0000000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,3)||'0000000'";
			}
			if(roleSubAccess.equals("Division")) {
				qry +="SUBSTRING(imdt_fmn,1,6)||'0000' =:sus_no";
				armsd="SUBSTRING(imdt_fmn,1,6)||'0000'";
			}
			if(roleSubAccess.equals("Brigade")) {
				qry +="imdt_fmn =:sus_no";
				armsd="imdt_fmn";

			}						
		}
		
		if(roleAccess.equals("Formation")) {							
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = null;				
								
				//q = session.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm where "+qry+" order by SUBSTRING(date1,1,4)  desc");
				q = session.createQuery("select distinct SUBSTRING(date1,1,4) from Miso_Orbat_Relief_Prgm order by SUBSTRING(date1,1,4) desc");
				//q.setParameter("sus_no", sessionA.getAttribute("roleFormationNo").toString()); 				
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q.list();
				tx.commit(); 
				session.close(); 
				
				model.put("listyear",list);
				model.put("viewArm",M.getArmNameListforSD(armsd,sessionA.getAttribute("roleFormationNo").toString()));
				model.put("roleAccess",sessionA.getAttribute("roleAccess").toString());
				model.put("roleSubAccess",sessionA.getAttribute("roleSubAccess").toString());
			}

		
		if(roleAccess.equals("Formation")) {							  
				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx1 = session1.beginTransaction();
				Query q1 = null;				
				if (viewArm.equals("nothing"))
				{
				/*qry1 =   " select  auth_let_no,date1,period_from,period_to,sd_status,count(*) from "
						+" Miso_Orbat_Relief_Prgm where substring(date1,1,4)='"+listyear+"' and sd_status='1' "
						+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm where "+qry+" ) "
						+"group by auth_let_no,date1,period_from,period_to,sd_status order by date1 desc ";*/
				
				qry1 =   " select  auth_let_no,date1,period_from,period_to,sd_status,count(*),ser_no from "
						+" Miso_Orbat_Relief_Prgm where substring(date1,1,4)='"+listyear+"' and sd_status='1' "
						+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm) "
						+"group by auth_let_no,date1,period_from,period_to,sd_status,ser_no order by auth_let_no,to_number(ser_no,'99'),date1 desc ";
				
				}
				else
				{
				/*qry1 =   "select  auth_let_no,date1,period_from,period_to,sd_status,count(*) from "
						+" Miso_Orbat_Relief_Prgm where substring(arm_name,1,2)||'00'='"+viewArm+"' and substring(date1,1,4)='"+listyear+"' and sd_status='1' "
						+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm where "+qry+")"
						+"group by auth_let_no,date1,period_from,period_to,sd_status order by date1 desc ";
			*/	
				qry1 =   "select  auth_let_no,date1,period_from,period_to,sd_status,count(*),ser_no from "
						+" Miso_Orbat_Relief_Prgm where substring(arm_name,1,2)||'00'='"+viewArm+"' and substring(date1,1,4)='"+listyear+"' and sd_status='1' "
						+"and auth_let_no in (select distinct auth_let_no from Miso_Orbat_Relief_Prgm)"
						+" group by auth_let_no,date1,period_from,period_to,sd_status,ser_no order by auth_let_no,to_number(ser_no,'99'),date1 desc ";
				
				}
				
				q1 = session1.createQuery(qry1);
				//q1.setParameter("sus_no", sessionA.getAttribute("roleFormationNo").toString());				
				@SuppressWarnings("unchecked")
				List<String> list1 = (List<String>) q1.list();
				tx1.commit();
				session1.close();
				model.put("viewArm",M.getArmNameListforSD(armsd,sessionA.getAttribute("roleFormationNo").toString()));
				model.put("getReliefReportList",list1);
				model.put("listyear1",listyear);
		}
		model.put("roleSusNo",roleSusNo); 
		model.put("roleAccess",roleAccess);
		return new ModelAndView("new_gen_final_reliefTile");
	}
	
	@RequestMapping(value = "/getSDletterdetails", method = RequestMethod.POST)

		public @ResponseBody ArrayList<ArrayList<String>> getSDletterdetails (String authletno, String sno, HttpSession sessionUserId,
				String typerole)
				throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
				InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		
		
		String qry="";
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();	
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		
		qry=" a.auth_let_no='"+authletno+"' and a.ser_no='"+sno+"' ";
		
	
		
		if ( roleAccess.equals("Formation"))  {
					
				if(roleSubAccess.equals("Command")) {
					//qry+=" and a.ser_no in (select distinct ser_no from tb_miso_orbat_relief_prgm where a.auth_let_no='"+authletno+"' and SUBSTRING(imdt_fmn,1,1)||'000000000'= '"+sessionUserId.getAttribute("roleFormationNo").toString()+"') and a.auth_let_no='"+authletno+"' ";				
				}			
			
				if(roleSubAccess.equals("Corps")) {
					//qry+=" and a.ser_no in (select distinct ser_no from tb_miso_orbat_relief_prgm where a.auth_let_no='"+authletno+"' and SUBSTRING(imdt_fmn,1,3)||'0000000'= '"+sessionUserId.getAttribute("roleFormationNo").toString()+"')and a.auth_let_no='"+authletno+"' ";				
				}		
				
				if(roleSubAccess.equals("Division")) {
					//qry+=" and a.ser_no in (select distinct ser_no from tb_miso_orbat_relief_prgm where a.auth_let_no='"+authletno+"' and SUBSTRING(imdt_fmn,1,6)||'0000'= '"+sessionUserId.getAttribute("roleFormationNo").toString()+"') and a.auth_let_no='"+authletno+"' ";				
				}		
	
				if(roleSubAccess.equals("Brigade")) {
					//qry+=" and a.ser_no in (select distinct ser_no from tb_miso_orbat_relief_prgm where a.auth_let_no='"+authletno+"' and imdt_fmn= '"+sessionUserId.getAttribute("roleFormationNo").toString()+"') and a.auth_let_no='"+authletno+"'";				
				}		
			
			}
	
		if(roleAccess.equals("Unit")) {		
			qry=" a.ser_no in (select distinct ser_no from tb_miso_orbat_relief_prgm where auth_let_no='"+authletno+"' and sus_no='"+sessionUserId.getAttribute("roleSusNo").toString()+"') and a.auth_let_no='"+authletno+"'";
		}  
		
		if(authletno != null)
		{		   
			return create_unitDAO.getSDreliefreport(qry);
		}
		else
			return null;				
	}
	
	
	@RequestMapping(value = "/getExcelfor_relife_pgme", method = RequestMethod.POST)
	public ModelAndView getExcelfor_relife_pgme(HttpServletRequest request, ModelMap model, HttpSession session,
					
			@RequestParam(value = "msg", required = false) String msg) {
	 	
	 	String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		
		 /* if (val == false) {
			  return new ModelAndView("AccessTiles"); 
			  }
		 
		
		 if (request.getHeader("Referer") == null) { msg = ""; return new
				 ModelAndView("redirect:bodyParameterNotAllow"); 
		 }
		 
		 */
		ArrayList<List<String>> Excel = new ArrayList<List<String>>();
		String username ="";
		List<String> TH = new ArrayList<String>();	
		
			username="RELIEF_PGME";
			
			TH.add("Ser No");
			TH.add("From(Unit)");
			TH.add("NMB(yyyy-MM-dd)");		
			TH.add("To(Unit)");
			TH.add("Dt of Mov of Adv Party (yyyy-MM-dd)");
			TH.add("Mode of Tpt");
			TH.add("Indn/De-Indn Pd");
//			TH.add("RES");
			TH.add("Remarks");
			TH.add("Auth Letter No");	
			TH.add("Date (yyyy-MM-dd)");			
			TH.add("Period (From (yyyy-MM-dd))");
			TH.add("Period (To (yyyy-MM-dd))");
//			TH.add("Ser No of Letter");
//			TH.add("On Relief");
//			TH.add("Type of Cl");
			
	
		return new ModelAndView(new Export_Relief_pgme_formate("L", TH, username, Excel), "userList", Excel);
	}
	
	 
	@RequestMapping(value = "/exportRelifExcelAction", method = RequestMethod.POST)
    public ModelAndView exportRelifExcelAction(
            @RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
            HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

//        String table_name = request.getParameter("table_name");
        Integer countrow = Integer.parseInt(request.getParameter("countrow"));
        String username = session.getAttribute("username").toString();
        String roleSusNo = session.getAttribute("roleSusNo").toString();        
        String unit_exist = null;
        String unit_name_wrong = null;
        String hq_fmn_wrong = null;
        String loc_wrong = null;
        String date_formate_wrong = null;
        String Dt_of_Mov_of_Adv_Party_wrong = null;
        String date_wrong =null;
        String period_from_wrong =null;
        String period_to_wrong =null;
        String unit_name="";
        String nmb_new =null;
        String type_of_cln=null;
        String type_of_cln_wrong=null;
        String mode_of_tptn=null;
        String date_create = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        
        
        String Previous_Ser_no = null;
        
        // NEW 
        
        String from_hq = null;
        String from_locWithBrackets = null;
        String from_unit = null;
        
        //new to unit
        
        String hq = null;
        String locWithBrackets = null;
        String unit = null;
        
        // for alerts messaga
        StringBuilder message = new StringBuilder();
        boolean hasErrors = false;
        
        // array list to store serial numbers
        ArrayList<String> serialNumbers = new ArrayList<>();
        
         // Possible date formats
           String[] dateFormats = new String[] {
                     "yyyy-MM-dd","yyyy/MM" , "yyyy-MM","yyyy/MM/dd"
           };
           SimpleDateFormat[] dateFormatsNMB = {
        	        new SimpleDateFormat("yyyy-MM-dd"),
        	        new SimpleDateFormat("yyyy/MM/dd")
        	    };
           Session session1 = HibernateUtil.getSessionFactory().openSession();
           Transaction tx = session1.beginTransaction();
         try {
            
            for (int i = 0; i < countrow; i++) {
            
                
                String Ser_no = request.getParameter("Ser No_" + i).trim();
                String From_unit = request.getParameter("From(Unit)_" + i).trim();
                String nmb = request.getParameter("NMB(yyyy-MM-dd)_" + i).trim();
                String to_unit = request.getParameter("To(Unit)_" + i).trim();
                String Dt_of_Mov_of_Adv_Party = request.getParameter("Dt of Mov of Adv Party (yyyy-MM-dd)_" + i).trim();
                String mode_of_tpt = request.getParameter("Mode of Tpt_" + i).trim();
                String Indn_De_Indn_pd = request.getParameter("Indn/De-Indn Pd_" + i).trim();
//                String res = request.getParameter("RES_" + i).trim();
                String remarks = request.getParameter("Remarks_" + i);
                String auth_letter_no = request.getParameter("Auth Letter No_" + i).trim();
                String date = request.getParameter("Date (yyyy-MM-dd)_" + i).trim();
                String period_from = request.getParameter("Period (From (yyyy-MM-dd))_" + i).trim();
                String period_to = request.getParameter("Period (To (yyyy-MM-dd))_" + i).trim();
                
                
                if (!"undefined".equals(Ser_no)) {
                    System.err.println("Inside serial defined condition");
                    boolean isDuplicate = false;
                    
                    // Loop through existing serial numbers
                    for (String serial : serialNumbers) {
                        System.err.println("Inside serial defined condition 1");
                        if (serial.equals(Ser_no)) {
                            System.err.println("Inside serial defined condition 2");
                            isDuplicate = true;
                            break;  // Exit loop if duplicate found
                        }
                    }
                    
                    // Check if duplicate was found AFTER the loop
                    if (isDuplicate) {
                        System.err.println("Inside serial defined condition 3");
                        message.append("Duplicate serial Numbers are - ").append(Previous_Ser_no).append(" .\n");
                        hasErrors = true;
                    } else {
                        System.err.println("Inside array list condition");
                        serialNumbers.add(Ser_no);  // Add to list if no duplicate
                    }
                }

                
                if("undefined".equals(Ser_no)  || Ser_no == "undefined") {
                	Ser_no = Previous_Ser_no;
                }
                
                Previous_Ser_no = Ser_no;
                
                if(From_unit == null || From_unit.isEmpty() || From_unit == "" ||"undefined".equals(From_unit)  || From_unit == "undefined") {
                	message.append("Fropm unit can not be null  ").append(Previous_Ser_no).append(" .\n");
                    hasErrors = true;
                }
                
                //MEW 23-09-2024
                String unit_exist1 = null;
                String unit_name_wrong1 = null;
                String unit_name_wrong_to = null;
                String hq_fmn_wrong1 = null;
                String hq_fmn_wrong_to = null;
                String loc_wrong1 = null;
                String loc_wrong_to = null;
                String date_formate_wrong1 = null;
                String Dt_of_Mov_of_Adv_Party_wrong1 = null;
                String date_wrong1 =null;
                String period_from_wrong1 =null;
                String period_to_wrong1 =null;
                String unit_name1="";
                String nmb_new1 =null;
                String type_of_cln1=null;
                String type_of_cln_wrong1=null;
                String mode_of_tptn1=null;
                 
//                           String formattedDate = parseAndFormatDate(date, dateFormats);
                /*for (SimpleDateFormat dateFormat : dateFormatsNMB) {
                    try {
                        // Parse the date
                        Date dateDt_of_Mov_of_Adv_Party = dateFormat.parse(Dt_of_Mov_of_Adv_Party);
                        // Format the date to "YYYY-MM-DD" format
                        Dt_of_Mov_of_Adv_Party = new SimpleDateFormat("yyyy-MM-dd").format(dateDt_of_Mov_of_Adv_Party);
                        // Break the loop once successfully parsed
                        break;
                    } catch (ParseException e) {
                        // Date format doesn't match, try the next format
                    }
                }
                for (SimpleDateFormat dateFormat : dateFormatsNMB) {
                    try {
                        // Parse the date
                        Date datedate = dateFormat.parse(date);
                        // Format the date to "YYYY-MM-DD" format
                        date = new SimpleDateFormat("yyyy-MM-dd").format(datedate);
                        // Break the loop once successfully parsed
                        break;
                    } catch (ParseException e) {
                        // Date format doesn't match, try the next format
                    }
                }*/
                String[] fromUnitParts = From_unit.split(",");
                from_unit = fromUnitParts.length > 0 ? fromUnitParts[0].trim() : "";
                System.out.println("length from unit  +  " + fromUnitParts.length) ;
                from_locWithBrackets = fromUnitParts.length > 1 ? fromUnitParts[1].trim() : "";
                from_hq = fromUnitParts.length > 2 ? fromUnitParts[2].trim() : "";
                
                if (Dt_of_Mov_of_Adv_Party == null || Dt_of_Mov_of_Adv_Party.isEmpty() || Dt_of_Mov_of_Adv_Party == "" || "undefined".equals(Dt_of_Mov_of_Adv_Party)) {
                    message.append("Date of movement adv party data can not null at Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                if (mode_of_tpt == null || mode_of_tpt.isEmpty() || mode_of_tpt == "" || "undefined".equals(mode_of_tpt) ) {
                    message.append("Mode of transport data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                if (Indn_De_Indn_pd == null || Indn_De_Indn_pd.isEmpty() || Indn_De_Indn_pd == "" || "undefined".equals(Indn_De_Indn_pd)) {
                    message.append("Indn/De-indn data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                String remarks_na = "   ";
                if ("undefined".equals(remarks)  || remarks == "undefined" ) {
                	remarks = remarks_na;
                }
                
                if (auth_letter_no == null || auth_letter_no.isEmpty() || auth_letter_no == "" || "undefined".equals(auth_letter_no) ) {
                    message.append("Auth letter number data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                if (date == null || date.isEmpty() || date == ""  || "undefined".equals(date) ) {
                    message.append("Date column  data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                if (period_from == null || period_from.isEmpty() || period_from == ""  || "undefined".equals(period_from) ) {
                    message.append("Please Check period from data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                if (period_to == null || period_to.isEmpty() || period_to == "" || "undefined".equals(period_to) ) {
                    message.append("Please Check period to  data of these Sr. no can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                
                if (period_from.compareTo(period_to) > 0) {
                    message.append("period from date should be less than period to date").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                }
                
                
                if (!nmb.equalsIgnoreCase("On relief")) {
                	if (Dt_of_Mov_of_Adv_Party.compareTo(nmb) > 0) {
                        message.append("Date of Mov Adv Party should be less than NMB date").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                        hasErrors = true;
                    }
                }
                
                if (nmb.equalsIgnoreCase("On relief")) {
                	if(!"On relief".equals(nmb)) {
                	message.append("NMB value should be On relief at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                    hasErrors = true;
                	}
                }
                
                /*if(!"undefined".equals(remarks) || remarks != "" || remarks != null) {
                if (remarks.length() > 399) {
                	message.append("Remarks length should not greater than 400 at ser no   ").append(Previous_Ser_no).append(" .\n");
                    hasErrors = true;
                }
                }*/
                
                if (hasSpecialCharacters(remarks)) {
            	    message.append("Remarks should not contain special character.").append(Previous_Ser_no).append(" .\n");
                    hasErrors = true;
            	    }
                
                if (!isValidOnlyDigits(Ser_no)) {
            	    message.append("Ser no should contain only numbers.").append(Previous_Ser_no).append(" .\n");
                    hasErrors = true;
            	    }
                
                
                
               
                
                
                
                
                
                
                           if (!isValidDate(Dt_of_Mov_of_Adv_Party)) {
                        	    if (Dt_of_Mov_of_Adv_Party_wrong == null || Dt_of_Mov_of_Adv_Party_wrong.isEmpty() || Dt_of_Mov_of_Adv_Party_wrong.equalsIgnoreCase("null")) {
                        	        Dt_of_Mov_of_Adv_Party_wrong = Dt_of_Mov_of_Adv_Party;
                        	    } else {
                        	        Dt_of_Mov_of_Adv_Party_wrong += "," + Dt_of_Mov_of_Adv_Party;
                        	    }
                        	} else if (!isValidDate(date)) {
                        	    if (date_wrong == null || date_wrong.isEmpty() || date_wrong.equalsIgnoreCase("null")) {
                        	        date_wrong = Dt_of_Mov_of_Adv_Party;
                        	    } else {
                        	        date_wrong += "," + Dt_of_Mov_of_Adv_Party;
                        	    }
                        	} else if (!isValidDate(period_from)) {  
                        	    if (period_from_wrong == null || period_from_wrong.isEmpty() || period_from_wrong.equalsIgnoreCase("null")) {
                        	        period_from_wrong = period_from;
                        	    } else {
                        	        period_from_wrong += "," + period_from;
                        	    }
                        	} else if (!isValidDate(period_to)) { 
                        	    if (period_to_wrong == null || period_to_wrong.isEmpty() || period_to_wrong.equalsIgnoreCase("null")) {
                        	        period_to_wrong = period_to;
                        	    } else {
                        	        period_to_wrong += "," + period_to;
                        	    }
                                           }else {
                             
//                           String formattedDt_of_Mov_of_Adv_Party = parseAndFormatDate(Dt_of_Mov_of_Adv_Party, dateFormats);
                           
//                           String formattedDateNmb = "";
//                           String formattedPeriodFrom = parseAndFormatYearMonth(period_from, dateFormats);
//                                String formattedPeriodTo = parseAndFormatYearMonth(period_to, dateFormats);
//                                
//                                for (SimpleDateFormat dateFormat : dateFormatsNMB) {
//                                    try {
//                                        // Parse the date
//                                        Date dateNMB = dateFormat.parse(nmb);
//                                        // Format the date to "YYYY-MM-DD" format
//                                        nmb_new = new SimpleDateFormat("yyyy-MM-dd").format(dateNMB);
//                                        // Break the loop once successfully parsed
//                                        break;
//                                    } catch (ParseException e) {
//                                        // Date format doesn't match, try the next format
//                                    }
//                                }
                                
                                if (!isValidDate(nmb) && !nmb.equalsIgnoreCase("on relief")) {
                                	
                                	  
                                      	if (date_formate_wrong == null || date_formate_wrong.isEmpty()
                                                  || date_formate_wrong.equalsIgnoreCase("null")) {
                                      		date_formate_wrong = Ser_no;
                                          } else {
                                        	  date_formate_wrong += "," + Ser_no;
                                              }
//                                      } 
//                                   formattedDateNmb = parseAndFormatDate(nmb, dateFormats);
                            }
                            
                          // Check if nmb is "on relief"
                                else if (nmb.equalsIgnoreCase("on relief")) {
                                        try {
                                                // Fetch the data of the previous entry
                                                String previousNmb = request.getParameter("NMB(yyyy-MM-dd)_" + (i - 1)).trim();
                                                for (SimpleDateFormat dateFormat : dateFormatsNMB) {
                                                    try {
                                                        // Parse the date
                                                        Date PdateNMB = dateFormat.parse(previousNmb);
                                                        // Format the date to "YYYY-MM-DD" format
                                                        previousNmb = new SimpleDateFormat("yyyy-MM-dd").format(PdateNMB);
                                                        // Break the loop once successfully parsed
                                                        break;
                                                    } catch (ParseException e) {
                                                        // Date format doesn't match, try the next format
                                                    }
                                                }
                                                
                                                if (isValidDate(previousNmb)){
                                                 
                                               
//                                                String prDateNmb = parseAndFormatDate(previousNmb, dateFormats);

                                                String previousIndnDeIndnPd = request.getParameter("Indn/De-Indn Pd_" + (i - 1)).trim();
                                                

                                                // Parse the previous date string to LocalDate
                                                LocalDate previousFromDate = LocalDate.parse(previousNmb, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                                                // Parse the number of weeks from the dropdown of the previous entry
                                                int previousWeeks = Integer.parseInt(previousIndnDeIndnPd.split(" ")[0]); // Extract the number of weeks
                                                // Add weeks from the previous entry
                                                LocalDate toDate = previousFromDate.plusWeeks(previousWeeks);
                                                // Format the resulting date
                                                nmb = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                                }
                                        } catch (DateTimeParseException | NumberFormatException e) {
                                                // Handle parsing errors
                                                e.printStackTrace();
                                                // Default to the original value
                                              
                                        }
                                }
                                
//                                else {   
                                if (isValidDate(nmb) ) {
                              
                                
                // Split the from_unit value
                            fromUnitParts = From_unit.split(",");
                              from_unit = fromUnitParts.length > 0 ? fromUnitParts[0].trim() : "";
                            System.out.println("length from unit  +  " + fromUnitParts.length) ;
                             from_locWithBrackets = fromUnitParts.length > 1 ? fromUnitParts[1].trim() : "";
                             from_hq = fromUnitParts.length > 2 ? fromUnitParts[2].trim() : "";
                            
                            
                            if (from_unit == null || from_unit.isEmpty() || from_unit == "" ) {
                                message.append("Please Check from unit (unit name)  data of these Sr. nos ").append(Previous_Ser_no).append(" .\n");
                                hasErrors = true;
                            }
                            
                            if (from_locWithBrackets == null || from_locWithBrackets.isEmpty() || from_locWithBrackets == "" ) {
                                message.append("Please Check from unit  loc and type of cl data of these Sr. nos ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                hasErrors = true;
                            }
                            
                            if (from_hq == null || from_hq.isEmpty() || from_hq == "" ) {
                                message.append("Please Check from unit formation data of these Sr. nos ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                hasErrors = true;
                            }
                            
                            
                           // Further split from_locWithBrackets to extract loc and brackets value
                           String from_loc = "";
                           String from_brackets = "";
                           if (!from_locWithBrackets.isEmpty()) {
                                   int startBracket = from_locWithBrackets.indexOf("(");
                                   int endBracket = from_locWithBrackets.indexOf(")");
                                   if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
                                           from_loc = from_locWithBrackets.substring(0, startBracket).trim();
                                           from_brackets = from_locWithBrackets.substring(startBracket + 1, endBracket).trim();
                                           
                                           if (from_brackets == null || from_brackets.isEmpty() || from_brackets == "" ) {
                                               message.append("Please Check from unit type of classification data  can not null of these Sr. nos ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                               hasErrors = true;
                                           }
                                   } 
									/*
									 * else if(){ from_loc = from_locWithBrackets; // if no brackets found, use the
									 * whole part as loc }
									 */
                                   
                                   Query TypeCL = session1.createQuery( "FROM T_Domain_Value r where r.label=:label and r.domainid='RELIEF_PGME_CL'");
                                   TypeCL.setParameter("label", from_brackets);
                                   @SuppressWarnings("unchecked")
                                   List<T_Domain_Value> brackets2 = (List<T_Domain_Value>) TypeCL.list();
                                   
                                   
                                   
                                   
                                   
                                   
                                   if(from_brackets.isEmpty() || from_brackets == null || from_brackets=="" || 
                                		   brackets2.isEmpty() || brackets2 == null) {
                                	   if (Ser_no=="" || Ser_no =="undefined") {
                                		   
                                		   Ser_no= request.getParameter("Ser_no_" + (i - 1)).trim();
                                	   }
                                	   
                                 	  
                                     	if (type_of_cln == null || type_of_cln.isEmpty()
                                                 || type_of_cln.equalsIgnoreCase("null")) {
                                     		type_of_cln = Ser_no;
                                         } else {
                                        	 type_of_cln += "," + Ser_no;
                                             }
//                                     } 
                                     	 if (type_of_cln != null && !type_of_cln.isEmpty() && !type_of_cln.equalsIgnoreCase("null")) {
                                             message.append("Wrong classification data of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                             hasErrors = true;
                                         }
//                                  formattedDateNmb = parseAndFormatDate(nmb, dateFormats);
                           }
                           }
                           
                           
                // Split the to_unit value
                           String[] toUnitParts = to_unit.split(",");
                            unit = toUnitParts.length > 0 ? toUnitParts[0].trim() : "";
                             locWithBrackets = toUnitParts.length > 1 ? toUnitParts[1].trim() : "";
                             hq = toUnitParts.length > 2 ? toUnitParts[2].trim() : "";
                            
                            
                            if (unit == null || unit.isEmpty() || unit == "" ) {
                                message.append("Please Check to unit (unit name) data of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                hasErrors = true;
                            }
                            
                            if (locWithBrackets == null || locWithBrackets.isEmpty() || locWithBrackets == "" ) {
                                message.append("Please Check to unit location data of these Sr. nos can not null  ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                hasErrors = true;
                            }
                            
                            if (hq == null || hq.isEmpty() || hq == "" ) {
                                message.append("Please Check to unit formation data  can not null of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                hasErrors = true;
                            }

                           // Further split locWithBrackets to extract loc and brackets value
                           String loc = "";
                           String brackets = "";
                           if (!locWithBrackets.isEmpty()) {
                                   int startBracket = locWithBrackets.indexOf("(");
                                   int endBracket = locWithBrackets.indexOf(")");
                                   if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
                                           loc = locWithBrackets.substring(0, startBracket).trim();
                                           brackets = locWithBrackets.substring(startBracket + 1, endBracket).trim();
                                           
                                           if (brackets == null || brackets.isEmpty() || brackets == "" ) {
                                               message.append("Please Check to unit type of classification data  can not null of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                               hasErrors = true;
                                           }
                                   } 
//                                   else {
//                                           loc = locWithBrackets; // if no brackets found, use the whole part as loc
//                                   }
                                   
                                   Query TypeCL = session1.createQuery( "FROM T_Domain_Value r where r.label=:label and r.domainid='RELIEF_PGME_CL'");
                                   TypeCL.setParameter("label", brackets);
                                   @SuppressWarnings("unchecked")
                                   List<T_Domain_Value> brackets1 = (List<T_Domain_Value>) TypeCL.list();
                                   
                                   if(brackets.isEmpty() || brackets == null || brackets=="" || 
                                		   brackets1.isEmpty() || brackets1 == null ) {
                                      	
                                	   	if (Ser_no=="" || Ser_no =="undefine") {
                                		   
                                		   Ser_no= request.getParameter("Ser_no_" + (i - 1)).trim();
                                	   }
                                    	if (type_of_cln == null || type_of_cln.isEmpty()
                                                || type_of_cln.equalsIgnoreCase("null")) {
                                    		
                                    		type_of_cln = Ser_no;
                                        } else {
                                       	 type_of_cln += "," + Ser_no;
                                            }
                                    	 if (type_of_cln != null && !type_of_cln.isEmpty() && !type_of_cln.equalsIgnoreCase("null")) {
                                             message.append("Wrong classification data of the Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                             hasErrors = true;
                                         }
//                                    } 
//                                 formattedDateNmb = parseAndFormatDate(nmb, dateFormats);
                          }
                           }
//                           Date date1x = new SimpleDateFormat("dd-MM-yyyy").parse(date);
//                           String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date1x);
                           String[] datacmd = mode_of_tpt.split(Pattern.quote(","));
                          // List<String> tempValues = new ArrayList<>();

                           for (String mode : datacmd) {
                        	   
                        	   String upperCaseMode = mode.trim();
                        	   List<String> tempValues = new ArrayList<>();
                        	   
                        	   tempValues.add(upperCaseMode);
                        	   
                        	   
                           

                           Query mot = session1.createQuery("FROM T_Domain_Value r where r.label in (:label) and r.domainid = 'RELIEF_PGME_MOT'");
                           mot.setParameterList("label", tempValues);


                           @SuppressWarnings("unchecked")
                           List<T_Domain_Value> mot1 = (List<T_Domain_Value>) mot.list();
                           
                           if(mot1.isEmpty() || mot1 == null ) {
                           	if (mode_of_tptn == null || mode_of_tptn.isEmpty()
                                       || mode_of_tptn.equalsIgnoreCase("null")) {
                           		mode_of_tptn = mode_of_tpt;
                               } else {
                            	   mode_of_tptn += "," + mode_of_tpt;
                                   }
                           } else {
               
                Query checkQuery = session1.createQuery( "FROM Miso_Orbat_Unt_Dtl r where r.unit_name=:unit and r.status_sus_no='Active'");
                checkQuery.setParameter("unit", from_unit);
                @SuppressWarnings("unchecked")
                List<Miso_Orbat_Unt_Dtl> list1 = (List<Miso_Orbat_Unt_Dtl>) checkQuery.list();
                if(list1.isEmpty() || list1 == null ) {
                	if (unit_name_wrong1 == null || unit_name_wrong1.isEmpty()
                            || unit_name_wrong1.equalsIgnoreCase("null")) {
                		unit_name_wrong1 = from_unit;
                    } else {
                    	unit_name_wrong1 = from_unit;
                        }
                } else {
                Miso_Orbat_Unt_Dtl misoOrbatUntDtlto = list1.get(0); 

                Query Queryfrom = session1.createQuery( "FROM Miso_Orbat_Unt_Dtl r where r.unit_name=:unit and r.status_sus_no='Active'");
                Queryfrom.setParameter("unit", unit);
                @SuppressWarnings("unchecked")
                List<Miso_Orbat_Unt_Dtl> list2 = (List<Miso_Orbat_Unt_Dtl>) Queryfrom.list();
                
             
                
                if(list2.isEmpty() || list2 == null ) {
                	if (unit_name_wrong_to == null || unit_name_wrong_to.isEmpty()
                            || unit_name_wrong_to.equalsIgnoreCase("null")) {
                		unit_name_wrong_to = unit;
                    } else {
                    	unit_name_wrong_to =  unit;
                        }
                } else {
                Miso_Orbat_Unt_Dtl misoOrbatUntDtlfrom = list2.get(0); 
                
                
                Query from_Queryfmn = session1.createQuery( "from Miso_Orbat_Unt_Dtl \r\n"
                        + "where status_sus_no = 'Active' and unit_name=:bde");
                from_Queryfmn.setParameter("bde", from_hq);
                @SuppressWarnings("unchecked")
                List<Miso_Orbat_Unt_Dtl> listfrom = (List<Miso_Orbat_Unt_Dtl>) from_Queryfmn.list();

                if(listfrom.isEmpty() || listfrom == null ) {
                	if (hq_fmn_wrong1 == null || hq_fmn_wrong1.isEmpty()
                            || hq_fmn_wrong1.equalsIgnoreCase("null")) {
                		hq_fmn_wrong1 = from_hq;
                    } else {
                    	hq_fmn_wrong1 =  from_hq;
                        }
                } else {
                Miso_Orbat_Unt_Dtl misoOrbatUntDtlQueryfmnFrom = listfrom.get(0); 
                
                
                Query Queryfmn = session1.createQuery( "from Miso_Orbat_Unt_Dtl \r\n"
                        + "where status_sus_no = 'Active' and unit_name=:bde");
                Queryfmn.setParameter("bde", hq);
                @SuppressWarnings("unchecked")
                List<Miso_Orbat_Unt_Dtl> list3 = (List<Miso_Orbat_Unt_Dtl>) Queryfmn.list();

                if(list3.isEmpty() || list3 == null ) {
                	if (hq_fmn_wrong_to == null || hq_fmn_wrong_to.isEmpty()
                            || hq_fmn_wrong_to.equalsIgnoreCase("null")) {
                		hq_fmn_wrong_to = hq;
                    } else {
                    	hq_fmn_wrong_to=  hq;
                        }
                } else {
                	
                Miso_Orbat_Unt_Dtl misoOrbatUntDtlQueryfmn = list3.get(0); 
                
                

                
                Query QueryFromloc = session1.createQuery( "select distinct a.code_value as location,b.code_value as nrs,a.code as loc_code,a.mod_desc as trn_type,c.label as type_of_loc,\r\n"
                        + "    a.nrs_code from Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value   c where a.code_type='Location' \r\n"
                        + "    and b.code_type = 'Location' and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' \r\n"
                        + "    and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and a.code_value =:loc ").setMaxResults(1);
                QueryFromloc.setParameter("loc", from_loc);
                @SuppressWarnings("unchecked")
                Object[] from_result = (Object[]) QueryFromloc.uniqueResult();
                

                if(from_result == null || from_result.length == 0 ) {
                	if (loc_wrong1 == null || loc_wrong1.isEmpty()
                            || loc_wrong1.equalsIgnoreCase("null")) {
                		loc_wrong1 = from_loc;
                    } else {
                    	loc_wrong1 =  from_loc;
                        }
                } else {
                	
                	
                       String from_location = (String) from_result[0];
                     String from_nrs = (String) from_result[1];
                       String from_loc_code = (String) from_result[2];
                       String from_trn_type = (String) from_result[3];
                       String from_type_of_loc = (String) from_result[4];
                       String from_nrs_code = (String) from_result[5];
                       
                

                Query Queryloc = session1.createQuery( "select distinct a.code_value as location,b.code_value as nrs,a.code as loc_code,a.mod_desc as trn_type,c.label as type_of_loc,\r\n"
                        + "    a.nrs_code from Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value   c where a.code_type='Location' \r\n"
                        + "    and b.code_type = 'Location' and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' \r\n"
                        + "    and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and a.code_value =:loc").setMaxResults(1);
                Queryloc.setParameter("loc", loc);
                @SuppressWarnings("unchecked")
                Object[] result = (Object[]) Queryloc.uniqueResult();
                
                if(result == null || result.length == 0 ) {
                	if (loc_wrong_to == null || loc_wrong_to.isEmpty()
                            || loc_wrong_to.equalsIgnoreCase("null")) {
                		loc_wrong_to = loc;
                    } else {
                    	loc_wrong_to =  loc;
                        }
                } else {
                       String location = (String) result[0];
                     String nrs = (String) result[1];
                       String loc_code = (String) result[2];
                       String trn_type = (String) result[3];
                       String type_of_loc = (String) result[4];
                       String nrs_code = (String) result[5];
            
                    Query qry = session1.createQuery(
                            "select count(*) FROM Miso_Orbat_Relief_Prgm r where r.sus_no=(select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no='Active') and (r.miso_status is null or r.miso_status!='1')");
                    qry.setParameter("unit_name", from_unit);
                    Long countLong = (Long) qry.uniqueResult();
                    int count = countLong.intValue();
                    
                
                    String fromunit = misoOrbatUntDtlfrom.getSus_no();    
                    String from_hq_fmn = misoOrbatUntDtlQueryfmnFrom.getForm_code_control();
                    Query qry1 = session1.createQuery(
                               "select r.ser_no FROM Miso_Orbat_Relief_Prgm r where r.rplc_by_unit_sus_no = :from_unit and loc=:loc_code and type_of_cl=:type_of_cl and imdt_fmn=:imdt_fmn"
                        );
                        qry1.setParameter("from_unit", fromunit);
                        qry1.setParameter("loc_code", from_loc_code);
                        qry1.setParameter("type_of_cl", from_brackets);
                        qry1.setParameter("imdt_fmn", from_hq_fmn);
                        //qry1.setParameter("id", tb_id);
                        String ser_no = (String) qry1.uniqueResult();

                    if (ser_no != null && !ser_no.isEmpty()) {
                           Ser_no = ser_no;
                    }
            
                    
                    if (count == 1) {
                    if (unit_exist1 == null || unit_exist1.isEmpty()
                            || unit_exist1.equalsIgnoreCase("null")) {
                        unit_exist1 = from_unit;
                    } else {
                        unit_exist1 = from_unit;
                        }
//                    } else  {
//
//                        Miso_Orbat_Relief_Prgm entity = new Miso_Orbat_Relief_Prgm();
//                        entity.setAuth_let_no(auth_letter_no);
//                        entity.setDate1(date);
//                        entity.setSus_no(misoOrbatUntDtlfrom.getSus_no());
//                        entity.setImdt_fmn(misoOrbatUntDtlQueryfmn.getForm_code_control());
//                        entity.setArm_name(misoOrbatUntDtlto.getArm_code());
//                        entity.setMode_of_tpt(mode_of_tpt.toLowerCase()+",");
//                        entity.setNmb_date(nmb_new);
//                        entity.setIndn_de_indn_pd(Indn_De_Indn_pd);
//                        entity.setLoc(loc_code);
//                        entity.setTyp_of_stn(type_of_loc);
//                        entity.setTyp_of_terrain(trn_type);
//                        entity.setMov_of_adv_party_dt(Dt_of_Mov_of_Adv_Party);
//                        entity.setRplc_by_unit_sus_no(misoOrbatUntDtlto.getSus_no());
//                        entity.setPeriod_from(formattedPeriodFrom);
//                        entity.setPeriod_to(formattedPeriodTo);
//                        entity.setSd_status("0");
//                        entity.setSd_created_by(username);
//                        entity.setNrs_code(nrs_code);
//                        entity.setSd_created_on(date_create);
//                        if(nmb.equals("On Relief") || nmb=="on relief" || nmb=="ON RELIEF") {
//                            entity.setRelief_yes_no("1");
//                        }else {
//                            entity.setRelief_yes_no("0");
//                        }
////                        entity.setRelief_yes_no();
//                        entity.setRemarks(remarks);
//                        entity.setSer_no(Ser_no);
//                        entity.setType_of_cl(brackets);
//
//                        //session1.save(entity);
//                        int tb_id = (int) session1.save(entity);
                    }

//                }

                
												}
											}
										}
	                				}
                				}
                			}
	                	}
	                 }
                  }
                                
                                //new changes  23-09-2024
                                
                                if (unit_exist1 != null && !unit_exist1.isEmpty() && !unit_exist1.equalsIgnoreCase("null")) {
                                    message.append("These Units already exist: ").append(unit_exist1).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }

                                if (unit_name_wrong1 != null && !unit_name_wrong1.isEmpty() && !unit_name_wrong1.equalsIgnoreCase("null")) {
                                    message.append("These Unit Details are not Exist in MISO Database:-   ").append(unit_name_wrong1).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (unit_name_wrong_to != null && !unit_name_wrong_to.isEmpty() && !unit_name_wrong_to.equalsIgnoreCase("null")) {
                                    message.append("These Unit Details are not Exist in MISO Database:-   ").append(unit_name_wrong_to).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }

                                if (hq_fmn_wrong1 != null && !hq_fmn_wrong1.isEmpty() && !hq_fmn_wrong1.equalsIgnoreCase("null")) {
                                    message.append("These FMN Details are not exist in MISO Database: ").append(hq_fmn_wrong1).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (hq_fmn_wrong_to != null && !hq_fmn_wrong_to.isEmpty() && !hq_fmn_wrong_to.equalsIgnoreCase("null")) {
                                    message.append("These FMN Details are not exist in MISO Database: ").append(hq_fmn_wrong_to).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }

                                if (loc_wrong1 != null && !loc_wrong1.isEmpty() && !loc_wrong1.equalsIgnoreCase("null")) {
                                    message.append("These Location Details are not exist in MISO Database: ").append(loc_wrong1).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (loc_wrong_to != null && !loc_wrong_to.isEmpty() && !loc_wrong_to.equalsIgnoreCase("null")) {
                                    message.append("These Location Details are not exist in MISO Database: ").append(loc_wrong_to).append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (date_formate_wrong != null && !date_formate_wrong.isEmpty() && !date_formate_wrong.equalsIgnoreCase("null")) {
                                    message.append(date_formate_wrong).append(" :Check NMB date of these Sr. Nos.\n");
                                    hasErrors = true;
                                }

                                if (Dt_of_Mov_of_Adv_Party_wrong != null && !Dt_of_Mov_of_Adv_Party_wrong.isEmpty() && !Dt_of_Mov_of_Adv_Party_wrong.equalsIgnoreCase("null")) {
                                    message.append(Dt_of_Mov_of_Adv_Party_wrong).append(" :Dt of Mov of Adv Party date format must be yyyy-mm-dd").append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                if (date_wrong != null && !date_wrong.isEmpty() && !date_wrong.equalsIgnoreCase("null")) {
                                    message.append(date_wrong).append(" :Date format must be yyyy-mm-dd").append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                if (period_from_wrong != null && !period_from_wrong.isEmpty() && !period_from_wrong.equalsIgnoreCase("null")) {
                                    message.append(period_from_wrong).append(" :Period from date format must be yyyy-mm-dd").append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                if (period_to_wrong != null && !period_to_wrong.isEmpty() && !period_to_wrong.equalsIgnoreCase("null")) {
                                    message.append(period_to_wrong).append(" :Period to date format must be yyyy-mm-dd").append(" at ser no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n");
                                    hasErrors = true;
                                }
                                /*if (type_of_cln != null && !type_of_cln.isEmpty() && !type_of_cln.equalsIgnoreCase("null")) {
                                    message.append("Please Check classification data of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
                                    hasErrors = true;
                                }*/
                                if (mode_of_tptn != null && !mode_of_tptn.isEmpty() && !mode_of_tptn.equalsIgnoreCase("null")) {
                                    message.append("Please Check Mode of Transport data at ser no ").append(Previous_Ser_no).append(" " + mode_of_tptn).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                
                                
                                //new
                                
                                
                                if (unit == null ||  unit.isEmpty() || unit == "" || "".equals(unit)) {
                                    message.append("Please enter and Check to unit data at ser_ no .").append(Previous_Ser_no).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (locWithBrackets == null ||  locWithBrackets.isEmpty() || locWithBrackets == "") {
                                    message.append("Please check  to unit location  data at ser_ no .").append(Previous_Ser_no).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (hq == null ||  hq.isEmpty()  || hq =="") {
                                    message.append("Please check FMN for To unit data at ser_ no .").append(Previous_Ser_no).append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (from_unit == null ||  from_unit.isEmpty() || from_unit == "" || "".equals(from_unit ) || "".equals(from_unit) ){
                                    message.append("Please check  to unit location  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (from_locWithBrackets == null ||  from_locWithBrackets.isEmpty() || from_locWithBrackets == ""  || "".equals(from_locWithBrackets) ) {
                                    message.append("Please check  from  unit location  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
                                    hasErrors = true;
                                }
                                
                                if (from_hq == null ||  from_hq.isEmpty()  || from_hq == "" || "".equals(from_hq)) {
                                    message.append("Please check  from unit FMN  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
                                    hasErrors = true;
                                }
                 
				}
}
         }
            catch (Exception e) {                 
                       e.printStackTrace();
                       model.put("errorMsg", "An unexpected error occurred.");
                       return new ModelAndView("redirect:relief_prog_excel");
               }
         //StringBuilder message = new StringBuilder();
         //boolean hasErrors = false;

         /*if (unit_exist != null && !unit_exist.isEmpty() && !unit_exist.equalsIgnoreCase("null")) {
             message.append("These Units already exist: ").append(unit_exist).append(" .\n");
             hasErrors = true;
         }

         if (unit_name_wrong != null && !unit_name_wrong.isEmpty() && !unit_name_wrong.equalsIgnoreCase("null")) {
             message.append("These Unit Details are not Exist in MISO Database:-   ").append(unit_name_wrong).append(" .\n");
             hasErrors = true;
         }

         if (hq_fmn_wrong != null && !hq_fmn_wrong.isEmpty() && !hq_fmn_wrong.equalsIgnoreCase("null")) {
             message.append("These FMN Details are not exist in MISO Database: ").append(hq_fmn_wrong).append(" .\n");
             hasErrors = true;
         }

         if (loc_wrong != null && !loc_wrong.isEmpty() && !loc_wrong.equalsIgnoreCase("null")) {
             message.append("These Location Details are not exist in MISO Database: ").append(loc_wrong).append(" .\n");
             hasErrors = true;
         }
         if (date_formate_wrong != null && !date_formate_wrong.isEmpty() && !date_formate_wrong.equalsIgnoreCase("null")) {
             message.append(date_formate_wrong).append(" :Check NMB date of these Sr. Nos.\n");
             hasErrors = true;
         }

         if (Dt_of_Mov_of_Adv_Party_wrong != null && !Dt_of_Mov_of_Adv_Party_wrong.isEmpty() && !Dt_of_Mov_of_Adv_Party_wrong.equalsIgnoreCase("null")) {
             message.append(Dt_of_Mov_of_Adv_Party_wrong).append(" :Dt of Mov of Adv Party date format must be yyyy-mm-dd").append(" and from unit name is  ").append(from_unit).append(" .\n");
             hasErrors = true;
         }
         if (date_wrong != null && !date_wrong.isEmpty() && !date_wrong.equalsIgnoreCase("null")) {
             message.append(date_wrong).append(" :Date format must be yyyy-mm-dd").append(" and from unit name is  ").append(from_unit).append(" .\n");
             hasErrors = true;
         }
         if (period_from_wrong != null && !period_from_wrong.isEmpty() && !period_from_wrong.equalsIgnoreCase("null")) {
             message.append(period_from_wrong).append(" :Period from date format must be yyyy-mm-dd").append(" and from unit name is  ").append(from_unit).append(" .\n");
             hasErrors = true;
         }
         if (period_to_wrong != null && !period_to_wrong.isEmpty() && !period_to_wrong.equalsIgnoreCase("null")) {
             message.append(period_to_wrong).append(" :Period to date format must be yyyy-mm-dd").append(" and from unit name is  ").append(from_unit).append(" .\n");
             hasErrors = true;
         }
         if (type_of_cln != null && !type_of_cln.isEmpty() && !type_of_cln.equalsIgnoreCase("null")) {
             message.append("Please Check classification data of these Sr. no ").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
             hasErrors = true;
         }
         if (mode_of_tptn != null && !mode_of_tptn.isEmpty() && !mode_of_tptn.equalsIgnoreCase("null")) {
             message.append("Please Check Mode of Transport data at ser no ").append(Previous_Ser_no).append(" " + mode_of_tptn).append(" .\n");
             hasErrors = true;
         }
         
         
         
         //new
         
         
         if (unit == null ||  unit.isEmpty() || unit == "" || "".equals(unit)) {
             message.append("Please enter and Check to unit data at ser_ no .").append(Previous_Ser_no).append(" .\n");
             hasErrors = true;
         }
         
         if (locWithBrackets == null ||  locWithBrackets.isEmpty() || locWithBrackets == "") {
             message.append("Please check  to unit location  data at ser_ no .").append(Previous_Ser_no).append(" .\n");
             hasErrors = true;
         }
         
         if (hq == null ||  hq.isEmpty()  || hq =="") {
             message.append("Please check FMN for To unit data at ser_ no .").append(Previous_Ser_no).append(" .\n");
             hasErrors = true;
         }
         
         if (from_unit == null ||  from_unit.isEmpty() || from_unit == "" || "".equals(from_unit ) || "".equals(from_unit) ){
             message.append("Please check  to unit location  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
             hasErrors = true;
         }
         
         if (from_locWithBrackets == null ||  from_locWithBrackets.isEmpty() || from_locWithBrackets == ""  || "".equals(from_locWithBrackets) ) {
             message.append("Please check  from  unit location  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
             hasErrors = true;
         }
         
         if (from_hq == null ||  from_hq.isEmpty()  || from_hq == "" || "".equals(from_hq)) {
             message.append("Please check  from unit FMN  data at ser_ no .").append(Previous_Ser_no).append(" and from unit name is  ").append(from_unit).append(" .\n").append(" .\n");
             hasErrors = true;
         }*/
         
         
         
         
         
         
         
         
        
         
         if (!hasErrors) {
        	 try {
         for (int i = 0; i < countrow; i++) {
         
             
             String Ser_no = request.getParameter("Ser No_" + i).trim();
             String From_unit = request.getParameter("From(Unit)_" + i).trim();
             String nmb = request.getParameter("NMB(yyyy-MM-dd)_" + i).trim();
             String to_unit = request.getParameter("To(Unit)_" + i).trim();
             String Dt_of_Mov_of_Adv_Party = request.getParameter("Dt of Mov of Adv Party (yyyy-MM-dd)_" + i).trim();
             String mode_of_tpt = request.getParameter("Mode of Tpt_" + i).trim();
             String Indn_De_Indn_pd = request.getParameter("Indn/De-Indn Pd_" + i).trim();
//             String res = request.getParameter("RES_" + i).trim();
             String remarks = request.getParameter("Remarks_" + i);
             String auth_letter_no = request.getParameter("Auth Letter No_" + i).trim();
             String date = request.getParameter("Date (yyyy-MM-dd)_" + i).trim();
             String period_from = request.getParameter("Period (From (yyyy-MM-dd))_" + i).trim();
             String period_to = request.getParameter("Period (To (yyyy-MM-dd))_" + i).trim();
              
             
             if("undefined".equals(Ser_no)  || Ser_no == "undefined") {
             	Ser_no = Previous_Ser_no;
             }
             
             Previous_Ser_no = Ser_no;
             
             String remarks_na = "   ";
             if ("undefined".equals(remarks)  || remarks == "undefined" ) {
             	remarks = remarks_na;
             }
             
             
             
             
//          String formattedDate = parseAndFormatDate(date, dateFormats);
             for (SimpleDateFormat dateFormat : dateFormatsNMB) {
                 try {
                     // Parse the date
                     Date dateDt_of_Mov_of_Adv_Party = dateFormat.parse(Dt_of_Mov_of_Adv_Party);
                     // Format the date to "YYYY-MM-DD" format
                     Dt_of_Mov_of_Adv_Party = new SimpleDateFormat("yyyy-MM-dd").format(dateDt_of_Mov_of_Adv_Party);
                     // Break the loop once successfully parsed
                     break;
                 } catch (ParseException e) {
                     // Date format doesn't match, try the next format
                 }
             }
             for (SimpleDateFormat dateFormat : dateFormatsNMB) {
                 try {
                     // Parse the date
                     Date datedate = dateFormat.parse(date);
                     // Format the date to "YYYY-MM-DD" format
                     date = new SimpleDateFormat("yyyy-MM-dd").format(datedate);
                     // Break the loop once successfully parsed
                     break;
                 } catch (ParseException e) {
                     // Date format doesn't match, try the next format
                 }
                 
             }
	             for (SimpleDateFormat dateFormat : dateFormatsNMB) {
	                 try {
	                     
	                     Date dateperiod_from = dateFormat.parse(period_from);
	                     
	                     period_from = new SimpleDateFormat("yyyy-MM-dd").format(dateperiod_from);
	                     
	                     break;
	                 } catch (ParseException e) {
	                     
	                 }
	             }
	             
				for (SimpleDateFormat dateFormat : dateFormatsNMB) {
				                 try {
				                     
				                     Date dateperiod_to = dateFormat.parse(period_to);
				                     
				                     period_to = new SimpleDateFormat("yyyy-MM-dd").format(dateperiod_to);
				                     
				                     break;
				                 } catch (ParseException e) {
				                    
				                 }
				             }
             
                        if (!isValidDate(Dt_of_Mov_of_Adv_Party)) {
                     	    if (Dt_of_Mov_of_Adv_Party_wrong == null || Dt_of_Mov_of_Adv_Party_wrong.isEmpty() || Dt_of_Mov_of_Adv_Party_wrong.equalsIgnoreCase("null")) {
                     	        Dt_of_Mov_of_Adv_Party_wrong = Dt_of_Mov_of_Adv_Party;
                     	    } else {
                     	    	
                     	        Dt_of_Mov_of_Adv_Party_wrong += "," + Dt_of_Mov_of_Adv_Party;
                     	    }
                     	} else if (!isValidDate(date)) {
                     	    if (date_wrong == null || date_wrong.isEmpty() || date_wrong.equalsIgnoreCase("null")) {
                     	        date_wrong = Dt_of_Mov_of_Adv_Party;
                     	    } else {
                     	        date_wrong += "," + Dt_of_Mov_of_Adv_Party;
                     	    }
                     	} else if (!isValidDate(period_from)) {  
                     	    if (period_from_wrong == null || period_from_wrong.isEmpty() || period_from_wrong.equalsIgnoreCase("null")) {
                     	        period_from_wrong = period_from;
                     	    } else {
                     	        period_from_wrong += "," + period_from;
                     	    }
                     	} else if (!isValidDate(period_to)) { 
                     	    if (period_to_wrong == null || period_to_wrong.isEmpty() || period_to_wrong.equalsIgnoreCase("null")) {
                     	        period_to_wrong = period_to;
                     	    } else {
                     	        period_to_wrong += "," + period_to;
                     	    }
                                        }else {
                          
//                        String formattedDt_of_Mov_of_Adv_Party = parseAndFormatDate(Dt_of_Mov_of_Adv_Party, dateFormats);
                        
//                        String formattedDateNmb = "";
											String formattedPeriodFrom = parseAndFormatYearMonth(period_from,
													dateFormats);
											String formattedPeriodTo = parseAndFormatYearMonth(period_to, dateFormats);

											for (SimpleDateFormat dateFormat : dateFormatsNMB) {
												try {
													// Parse the date
													Date dateNMB = dateFormat.parse(nmb);
													// Format the date to "YYYY-MM-DD" format
													nmb_new = new SimpleDateFormat("yyyy-MM-dd").format(dateNMB);
													// Break the loop once successfully parsed
													break;
													} catch (ParseException e) {
													// Date format doesn't match, try the next format
												}
											}

											if (!isValidDate(nmb_new) && !nmb.equalsIgnoreCase("on relief")) {

												if (date_formate_wrong == null || date_formate_wrong.isEmpty()
														|| date_formate_wrong.equalsIgnoreCase("null")) {
													date_formate_wrong = Ser_no;
												} else {
													date_formate_wrong += "," + Ser_no;
												}
//                                   } 
//                                formattedDateNmb = parseAndFormatDate(nmb, dateFormats);
											}
                         
                       // Check if nmb is "on relief"
						else if (nmb.equalsIgnoreCase("on relief")) {
							try {
								// Fetch the data of the previous entry
								String previousNmb = request.getParameter("NMB(yyyy-MM-dd)_" + (i - 1)).trim();
								for (SimpleDateFormat dateFormat : dateFormatsNMB) {
									try {
										// Parse the date
										Date PdateNMB = dateFormat.parse(previousNmb);
										// Format the date to "YYYY-MM-DD" format
										previousNmb = new SimpleDateFormat("yyyy-MM-dd").format(PdateNMB);
										// Break the loop once successfully parsed
										break;
									} catch (ParseException e) {
										// Date format doesn't match, try the next format
									}
								}

								if (isValidDate(previousNmb)) {

//                                             String prDateNmb = parseAndFormatDate(previousNmb, dateFormats);

									String previousIndnDeIndnPd = request.getParameter("Indn/De-Indn Pd_" + (i - 1))
											.trim();

									// Parse the previous date string to LocalDate
									LocalDate previousFromDate = LocalDate.parse(previousNmb,
											DateTimeFormatter.ofPattern("yyyy-MM-dd"));

									// Parse the number of weeks from the dropdown of the previous entry
									int previousWeeks = Integer.parseInt(previousIndnDeIndnPd.split(" ")[0]); // Extract the  number of  weeks
									// Add weeks from the previous entry
									LocalDate toDate = previousFromDate.plusWeeks(previousWeeks);
									// Format the resulting date
									nmb_new = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
								}
							} catch (DateTimeParseException | NumberFormatException e) {
								// Handle parsing errors
								e.printStackTrace();
								// Default to the original value

							}
						}
                             
//                             else {   
                             if (isValidDate(nmb_new) ) {
                           
                             
             // Split the from_unit value
                        String[] fromUnitParts = From_unit.split(",");
                         from_unit = fromUnitParts.length > 0 ? fromUnitParts[0].trim() : "";
                         from_locWithBrackets = fromUnitParts.length > 1 ? fromUnitParts[1].trim() : "";
                         from_hq = fromUnitParts.length > 2 ? fromUnitParts[2].trim() : "";
                      
                        // Further split from_locWithBrackets to extract loc and brackets value
                        String from_loc = "";
                        String from_brackets = "";
                        if (!from_locWithBrackets.isEmpty()) {
                                int startBracket = from_locWithBrackets.indexOf("(");
                                int endBracket = from_locWithBrackets.indexOf(")");
                                if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
                                        from_loc = from_locWithBrackets.substring(0, startBracket).trim();
                                        from_brackets = from_locWithBrackets.substring(startBracket + 1, endBracket).trim();
                                } else {
                                        from_loc = from_locWithBrackets; // if no brackets found, use the whole part as loc
                                }
                        }
                        
                        
             // Split the to_unit value
                        String[] toUnitParts = to_unit.split(",");
                         unit = toUnitParts.length > 0 ? toUnitParts[0].trim() : "";
                        locWithBrackets = toUnitParts.length > 1 ? toUnitParts[1].trim() : "";
                        hq = toUnitParts.length > 2 ? toUnitParts[2].trim() : "";

                        // Further split locWithBrackets to extract loc and brackets value
                        String loc = "";
                        String brackets = "";
                        if (!locWithBrackets.isEmpty()) {
                                int startBracket = locWithBrackets.indexOf("(");
                                int endBracket = locWithBrackets.indexOf(")");
                                if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
                                        loc = locWithBrackets.substring(0, startBracket).trim();
                                        brackets = locWithBrackets.substring(startBracket + 1, endBracket).trim();
                                } else {
                                        loc = locWithBrackets; // if no brackets found, use the whole part as loc
                                }
                        }
//                        Date date1x = new SimpleDateFormat("dd-MM-yyyy").parse(date);
//                        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date1x);

            
             Query checkQuery = session1.createQuery( "FROM Miso_Orbat_Unt_Dtl r where r.unit_name=:unit and r.status_sus_no='Active'");
             checkQuery.setParameter("unit", unit);
             @SuppressWarnings("unchecked")
             List<Miso_Orbat_Unt_Dtl> list1 = (List<Miso_Orbat_Unt_Dtl>) checkQuery.list();
             if(list1.isEmpty() || list1 == null ) {
             	if (unit_name_wrong == null || unit_name_wrong.isEmpty() || unit_name_wrong.equalsIgnoreCase("null")) {
             		unit_name_wrong = unit;
                 } else {
                 	unit_name_wrong += "," + unit;
                     }
             } else {
             Miso_Orbat_Unt_Dtl misoOrbatUntDtlto = list1.get(0); 

             Query Queryfrom = session1.createQuery( "FROM Miso_Orbat_Unt_Dtl r where r.unit_name=:unit and r.status_sus_no='Active'");
             Queryfrom.setParameter("unit", from_unit);
             @SuppressWarnings("unchecked")
             List<Miso_Orbat_Unt_Dtl> list2 = (List<Miso_Orbat_Unt_Dtl>) Queryfrom.list();
             
          
             
             if(list2.isEmpty() || list2 == null ) {
             	if (unit_name_wrong == null || unit_name_wrong.isEmpty()
                         || unit_name_wrong.equalsIgnoreCase("null")) {
             		unit_name_wrong = from_unit;
                 } else {
                 	unit_name_wrong += "," + from_unit;
                     }
             } else {
             Miso_Orbat_Unt_Dtl misoOrbatUntDtlfrom = list2.get(0); 
             
             
             Query from_Queryfmn = session1.createQuery( "from Miso_Orbat_Unt_Dtl \r\n"
                     + "where status_sus_no = 'Active' and unit_name=:bde");
             from_Queryfmn.setParameter("bde", from_hq);
             @SuppressWarnings("unchecked")
             List<Miso_Orbat_Unt_Dtl> listfrom = (List<Miso_Orbat_Unt_Dtl>) from_Queryfmn.list();

             if(listfrom.isEmpty() || listfrom == null ) {
             	if (hq_fmn_wrong == null || hq_fmn_wrong.isEmpty()
                         || hq_fmn_wrong.equalsIgnoreCase("null")) {
             		hq_fmn_wrong = from_hq;
                 } else {
                 	hq_fmn_wrong += "," + from_hq;
                     }
             } else {
             Miso_Orbat_Unt_Dtl misoOrbatUntDtlQueryfmnFrom = listfrom.get(0); 
             
             
             Query Queryfmn = session1.createQuery( "from Miso_Orbat_Unt_Dtl \r\n"
                     + "where status_sus_no = 'Active' and unit_name=:bde");
             Queryfmn.setParameter("bde", hq);
             @SuppressWarnings("unchecked")
             List<Miso_Orbat_Unt_Dtl> list3 = (List<Miso_Orbat_Unt_Dtl>) Queryfmn.list();

             if(list3.isEmpty() || list3 == null ) {
             	if (hq_fmn_wrong == null || hq_fmn_wrong.isEmpty()
                         || hq_fmn_wrong.equalsIgnoreCase("null")) {
             		hq_fmn_wrong = hq;
                 } else {
                 	hq_fmn_wrong += "," + hq;
                     }
             } else {
             	
             Miso_Orbat_Unt_Dtl misoOrbatUntDtlQueryfmn = list3.get(0); 
             
             

             
             Query QueryFromloc = session1.createQuery( "select distinct a.code_value as location,b.code_value as nrs,a.code as loc_code,a.mod_desc as trn_type,c.label as type_of_loc,\r\n"
                     + "    a.nrs_code from Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value   c where a.code_type='Location' \r\n"
                     + "    and b.code_type = 'Location' and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' \r\n"
                     + "    and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and a.code_value =:loc").setMaxResults(1);
             QueryFromloc.setParameter("loc", from_loc);
             @SuppressWarnings("unchecked")
             Object[] from_result = (Object[]) QueryFromloc.uniqueResult();
             

             if(from_result == null || from_result.length == 0 ) {
             	if (loc_wrong == null || loc_wrong.isEmpty()
                         || loc_wrong.equalsIgnoreCase("null")) {
             		loc_wrong = from_loc;
                 } else {
                 	loc_wrong += "," + from_loc;
                     }
             } else {
             	
             	
                    String from_location = (String) from_result[0];
                  String from_nrs = (String) from_result[1];
                    String from_loc_code = (String) from_result[2];
                    String from_trn_type = (String) from_result[3];
                    String from_type_of_loc = (String) from_result[4];
                    String from_nrs_code = (String) from_result[5];
                    
             

             Query Queryloc = session1.createQuery( "select distinct a.code_value as location,b.code_value as nrs,a.code as loc_code,a.mod_desc as trn_type,c.label as type_of_loc,\r\n"
                     + "    a.nrs_code from Tb_Miso_Orbat_Code a,Tb_Miso_Orbat_Code b,T_Domain_Value   c where a.code_type='Location' \r\n"
                     + "    and b.code_type = 'Location' and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' \r\n"
                     + "    and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and a.code_value =:loc").setMaxResults(1);
             Queryloc.setParameter("loc", loc);
             @SuppressWarnings("unchecked")
             Object[] result = (Object[]) Queryloc.uniqueResult();
             
             if(result == null || result.length == 0 ) {
             	if (loc_wrong == null || loc_wrong.isEmpty()
                         || loc_wrong.equalsIgnoreCase("null")) {
             		loc_wrong = from_loc;
                 } else {
                 	loc_wrong += "," + loc;
                     }
             } else {
                    String location = (String) result[0];
                  String nrs = (String) result[1];
                    String loc_code = (String) result[2];
                    String trn_type = (String) result[3];
                    String type_of_loc = (String) result[4];
                    String nrs_code = (String) result[5];
         
                 Query qry = session1.createQuery(
                         "select count(*) FROM Miso_Orbat_Relief_Prgm r where r.sus_no=(select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no='Active') and (r.miso_status is null or r.miso_status!='1')");
                 qry.setParameter("unit_name", from_unit);
                 Long countLong = (Long) qry.uniqueResult();
                 int count = countLong.intValue();
                 
             
                 String fromunit = misoOrbatUntDtlfrom.getSus_no();    
                 String from_hq_fmn = misoOrbatUntDtlQueryfmnFrom.getForm_code_control();
                 Query qry1 = session1.createQuery(
                            "select r.ser_no FROM Miso_Orbat_Relief_Prgm r where r.rplc_by_unit_sus_no = :from_unit and loc=:loc_code and type_of_cl=:type_of_cl and imdt_fmn=:imdt_fmn"
                     );
                     qry1.setParameter("from_unit", fromunit);
                     qry1.setParameter("loc_code", from_loc_code);
                     qry1.setParameter("type_of_cl", from_brackets);
                     qry1.setParameter("imdt_fmn", from_hq_fmn);
                     //qry1.setParameter("id", tb_id);
                     String ser_no = (String) qry1.uniqueResult();

                 if (ser_no != null && !ser_no.isEmpty()) {
                        Ser_no = ser_no;
                 }
         
                 
                 if (count == 1) {
                 if (unit_exist == null || unit_exist.isEmpty()
                         || unit_exist.equalsIgnoreCase("null")) {
                     unit_exist = from_unit;
                 } else {
                     unit_exist += "," + from_unit;
                     }
                 } else  {

                     Miso_Orbat_Relief_Prgm entity = new Miso_Orbat_Relief_Prgm();
                     entity.setAuth_let_no(auth_letter_no);
                     entity.setDate1(date);
                     entity.setSus_no(misoOrbatUntDtlfrom.getSus_no());
                     entity.setImdt_fmn(misoOrbatUntDtlQueryfmn.getForm_code_control());
                     entity.setArm_name(misoOrbatUntDtlto.getArm_code());
                     entity.setMode_of_tpt(mode_of_tpt.toLowerCase()+",");
                     entity.setNmb_date(nmb_new);
                     entity.setIndn_de_indn_pd(Indn_De_Indn_pd);
                     entity.setLoc(loc_code);
                     entity.setTyp_of_stn(type_of_loc);
                     entity.setTyp_of_terrain(trn_type);
                     entity.setMov_of_adv_party_dt(Dt_of_Mov_of_Adv_Party);
                     entity.setRplc_by_unit_sus_no(misoOrbatUntDtlto.getSus_no());
                     entity.setPeriod_from(formattedPeriodFrom);
                     entity.setPeriod_to(formattedPeriodTo);
                     entity.setSd_status("0");
                     entity.setSd_created_by(username);
                     entity.setNrs_code(nrs_code);
                     entity.setSd_created_on(date_create);
                     if(nmb.trim().equals("On Relief")||nmb.trim().equals("On relief") || nmb.trim().equals("on relief") || nmb.trim().equals("ON RELIEF")) {
                         entity.setRelief_yes_no("1");
                     }else {
                         entity.setRelief_yes_no("0");
                     }
                     System.out.println("NMB Value :-" + nmb);
//                     entity.setRelief_yes_no();
                     entity.setRemarks(remarks);
                     entity.setSer_no(Ser_no);
                     entity.setType_of_cl(brackets);

                     //session1.save(entity);
                     int tb_id = (int) session1.save(entity);
                     
                 }
                
//             }
												}
											}
										}
		                			}
		                		}
		                	}
		                }
					}
				}
         message.append("Your Data Saved Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("errorMsg", "An unexpected error occurred.");
				return new ModelAndView("redirect:relief_prog_excel");
			}

		}
         
         tx.commit();
         session1.close();

         model.put("msg", message.toString());

        return new ModelAndView("redirect:relief_prog_excel");
    }
	

     private String parseAndFormatYearMonth(String dateStr, String[] dateFormats) throws ParseException {
                   for (String format : dateFormats) {
                           try {
                                   SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                                   Date date = sdf.parse(dateStr);
                                   SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
                                   return outputFormat.format(date);
                           } catch (ParseException e) {
                                   // Continue to the next format
                           }
                   }
                   throw new ParseException("Unparseable date: " + dateStr, -1);
           }

     private boolean isValidDate(String dateStr) {
    	 if (dateStr == null || "undefined".equals(dateStr) || "null".equalsIgnoreCase(dateStr) || dateStr.isEmpty()) {
    		    return false;
    		}
    	 	else {
    	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
    	    sdf1.setLenient(false); // Disable lenient parsing
    	    sdf2.setLenient(false); // Disable lenient parsing

    	    try {
    	    	Calendar cal = Calendar.getInstance();
                cal.setLenient(false);
                cal.setTime(sdf1.parse(dateStr));
                
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1; // Month is 0-based, so adding 1
                int day = cal.get(Calendar.DAY_OF_MONTH);
                
                // Check if the day of the month is valid for the given month and year
                if (day > daysInMonth(year, month)) {
                    return false;
                }
    	        return true; // Date is already in the desired format
    	    } catch (ParseException e1) {
    	        try {
    	            // Attempt to parse the date in yyyy/MM/dd format
    	        	sdf2.setLenient(false);
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setLenient(false);
                    cal2.setTime(sdf2.parse(dateStr));

                    int year = cal2.get(Calendar.YEAR);
                    int month = cal2.get(Calendar.MONTH) + 1; // Month is 0-based, so adding 1
                    int day = cal2.get(Calendar.DAY_OF_MONTH);

                    // Check if the day of the month is valid for the given month and year
                    if (day > daysInMonth(year, month)) {
                        return false;
                    }

                    // If parsing is successful, convert it to yyyy-MM-dd format
                    String convertedDate = sdf1.format(cal2.getTime());
                    System.out.println("Date converted from yyyy/MM/dd to yyyy-MM-dd: " + convertedDate);
                    return true;
    	        } catch (ParseException e2) {
    	            return false; // Parsing failed with both formats
    	        }
    	    }
    	 }
    	}
     
     private int daysInMonth(int year, int month) {
    	    if (month == 2) {
    	        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
    	            return 29; // Leap year
    	        } else {
    	            return 28;
    	        }
    	    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
    	        return 30;
    	    } else {
    	        return 31;
    	    }
    	}
     
     private boolean hasSpecialCharacters(String input) { 
    	 String regex = "[^a-zA-Z0-9_\\s/\\\\\\[\\]&,*.]"; 
    	 Pattern pattern = Pattern.compile(regex);
    	 Matcher matcher = pattern.matcher(input); 
    	 return matcher.find(); 
    	 }
     
     
     private boolean isValidOnlyDigits(String input) { 
    	 for (char c : input.toCharArray()) {
    		 if (!Character.isDigit(c)) {
    			 return false; 
    			 }
    		 } return true;
    		 }
     
     @RequestMapping(value = "/admin/relief_prog_excel", method = RequestMethod.GET)
		public ModelAndView relief_prog_excel(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("relief_prog_excel", roleid);	
		if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			if(request.getHeader("Referer") == null ) {
				msg = "";
		}
			Date date= new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			Mmap.put("getImdtFmnList", getImdtFmnList());
			Mmap.put("getArmNameList", all.getArmNameList());
			Mmap.put("date", date1);
			Mmap.put("msg", msg);
			return new ModelAndView("relief_prog_excelTiles");
		}
	
	
}