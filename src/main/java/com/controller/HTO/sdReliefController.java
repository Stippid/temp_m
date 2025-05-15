package com.controller.HTO;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.orbat.Create_Unit_Mov_DetailsController;
import com.dao.HTO.sdReliefProgramDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UserLogin;
import com.models.UserRole;
import com.models.HTO.TB_ORBAT_HTO_USER;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class sdReliefController {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	Create_Unit_Mov_DetailsController mov = new Create_Unit_Mov_DetailsController();
	@Autowired
	RoleBaseMenuDAO roleDAO;

	@Autowired
	sdReliefProgramDAO sd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;


	@RequestMapping(value = "/sd_relief_program", method = RequestMethod.GET)
	public ModelAndView sd_relief_program(String subModule1,ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roleDAO.ScreenRedirect("sd_relief_program", roleid);
		//		if(val == false) {
		//			return new ModelAndView("AccessTiles");
		//		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();


		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct rplc_by_unit_sus_no from Miso_Orbat_Relief_Prgm where sus_no=:sus_no and (miso_status is null or miso_status!='1') ");
		q.setParameter("sus_no", roleSusNo);

		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String> ) q.list();
		tx.commit();
		session.close();

		String rplc_by_unit_sus_no= "";
		if(list.get(0) != null){
			rplc_by_unit_sus_no= list.get(0);
		}else {
			rplc_by_unit_sus_no = "";
		}


		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(rplc_by_unit_sus_no, sessionA).get(0));
			Mmap.put("to_sus_no", rplc_by_unit_sus_no);
		}

		Mmap.put("msg", msg);
		Mmap.put("roleid", roleid);
		Mmap.put("wepe", "1");  // for PERSONNEL value is 1
		//	Mmap.put("getsponserListCue", getsponserListCue());
		return new ModelAndView("sd_relief_program_Tiles");
	}

	@RequestMapping(value = "/getVehDeyailsTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getVehDeyailsTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType,String sus_no,String veh_type, HttpSession sessionUserId) throws SQLException {
		return sd.getVehDeyailsTable(startPage, pageLength, Search, orderColunm, orderType, sus_no,veh_type, sessionUserId);
	}
	@RequestMapping(value = "/getVehDeyailsTableCount", method = RequestMethod.POST)
	public @ResponseBody long getVehDeyailsTableCount(String Search,String orderColunm,String orderType,String sus_no,String veh_type,HttpSession sessionUserId,String msg) throws SQLException {
		return sd.getVehDeyailsTableCount(Search, orderColunm, orderType,sus_no, veh_type,sessionUserId);
	}

	@RequestMapping(value = "/getUnitNameFromSusBySD"  , method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitNameFromSusBySD(String sus_no,HttpSession sessionA) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		List<String> Finallist = new ArrayList<String>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct rplc_by_unit_sus_no from Miso_Orbat_Relief_Prgm where sus_no=:sus_no and (miso_status is null or miso_status!='1') ");
		q.setParameter("sus_no", sus_no);

		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String> ) q.list();
		tx.commit();
		session.close();

		String rplc_by_unit_sus_no= "";
		if(list.get(0) != null){
			rplc_by_unit_sus_no= new String(Base64.encodeBase64(c.doFinal(list.get(0).getBytes())));
		}else {
			rplc_by_unit_sus_no = "";
		}


		Finallist.add(rplc_by_unit_sus_no);

		Finallist.add(enckey+"8HGjyR==");

		return Finallist;
	}
	
	@RequestMapping(value = "/oc_adv_partyUrl", method = RequestMethod.GET)
	public ModelAndView oc_adv_partyUrl(String subModule1,ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			List<String> list = mov.getAllBodyDetailsList_Without_enc(roleSusNo,sessionA);
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
		
		Mmap.put("getImdtFmnList", mov.getImdtFmnList());
		Mmap.put("getArmNameList", all.getArmNameList());
		Mmap.put("getlocList", all.getLOCList());
		return new ModelAndView("oc_adv_party_Tiles");
	}
	
	@RequestMapping(value = "/getPersonnelDetailsTable", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPersonnelDetailsTable(int startPage, String Search,int pageLength, String orderColunm,
			String orderType,String sus_no, HttpSession sessionUserId) throws SQLException {
		return sd.getPersonnelDetailsTable(startPage, pageLength, Search, orderColunm, orderType, sus_no, sessionUserId);
	}
	@RequestMapping(value = "/getPersonnelDetailsCount", method = RequestMethod.POST)
	public @ResponseBody long getPersonnelDetailsCount(String Search,String orderColunm,String orderType,String sus_no,HttpSession sessionUserId,String msg) throws SQLException {
		return sd.getPersonnelDetailsCount(Search, orderColunm, orderType,sus_no,sessionUserId);
	}
	

	

	@RequestMapping(value = "/OC_Adv_Party_Action", method = RequestMethod.POST)
	public @ResponseBody String OC_Adv_Party_Action(HttpServletRequest request, HttpSession session) {

		String sus_no = request.getParameter("sus_no");
		String a1 = request.getParameter("a1");

		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String name = "ocAdvParty_"+sus_no;
		String pass ="123Bisag#";
		int roll = 549;
		 String returnMessage = "";
		try {
//			 String hql = "UPDATE TB_ORBAT_OC_VOUCHER set oc=:oc WHERE sus_no = :sus_no";
//	            Query queryMain = session.createQuery(hql);
//	            queryMain.setParameter("status1", "0");
//	            queryMain.setParameter("sus_no", sus_no);
//	            queryMain.executeUpdate();
	            
			if (!roledao.getuserExist(name).equals(false)) {
				returnMessage= "User '"+name+"' Already Exists!";
			} else {
				UserLogin p = new UserLogin();
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(pass);
				p.setPassword(hashedPassword);
				p.setUserName(name);
				p.setLogin_name(name);
				p.setCreated_by(username);
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
				p.setUser_sus_no(sus_no);
				
				
				p.setAc_dc_date(modifydate);
				p.setArmy_no(a1);
				p.setUser_formation_no("null");
				
				TB_ORBAT_HTO_USER hto = new TB_ORBAT_HTO_USER();
				hto.setCreated_by(username);
				hto.setCreated_date(new Date());
				hto.setLogin_name(name);
				hto.setSus_no(sus_no);
				hto.setPersonnel_no(a1);
				hto.setUser_name(name);
				sessionHQL.save(hto);
				
				UserRole role_tbl = new UserRole();

				int did = (Integer) sessionHQL.save(p);
				role_tbl.setRoleId(roll);
				role_tbl.setUserId(did);
				sessionHQL.save(role_tbl);

				roledao.userinsertdata("insert", did, roll);
				returnMessage= "User '"+name+"' Successfully Created!";
			}			
			
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				returnMessage= "Rollback transaction: " + e.getMessage();  
			} catch (RuntimeException rbe) {
				returnMessage= "Couldn't rollback: "+rbe.getMessage();
			}
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return returnMessage;
	}
	
}

