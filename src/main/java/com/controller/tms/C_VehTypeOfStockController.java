package com.controller.tms;

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
import org.hibernate.FlushMode;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Type_Of_Stock_ADAO;
import com.dao.tms.Type_Of_Stock_CDAO;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class C_VehTypeOfStockController {
	
	@Autowired
	private Type_Of_Stock_CDAO tosC;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	
	
	@RequestMapping(value = "/type_of_stock_c", method = RequestMethod.GET)
	public ModelAndView type_of_stock_c(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("type_of_stock_c", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("type_of_stock_c_Tiles","type_of_stock_c_CMD", new TB_TMS_MVCR_PARTA_DTL());
	}
	
	
	@RequestMapping(value = "/type_of_stock_c_Action", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("type_of_stock_c_CMD") TB_TMS_MVCR_PARTA_DTL rs,
			HttpServletRequest request,ModelMap model,HttpSession session) {
		model.put("msg", "Your Data Was Saved Successfully");
		return new ModelAndView("redirect:type_of_stock_c");	
	}
	
	
	@RequestMapping(value = "/admin/getTypeofstockC", method = RequestMethod.POST)
	public ModelAndView getTypeofstockC(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "mct1", required = false) String mct,
			@RequestParam(value = "type_of_stock_c1", required = false) String type_of_stock,
			@RequestParam(value = "depot_name1", required = false) String depot_name,
			@RequestParam(value = "std_nomclature1", required = false) String std_nomclature) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("type_of_stock_c", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}

		if (!sus_no.equals("") & sus_no.length() == 8 & !depot_name.equals("") & !type_of_stock.equals("")
				& !std_nomclature.equals("0")) {
			ArrayList<List<String>> list = tosC.getSearchStockC(sus_no, mct, type_of_stock, roleType);
			Mmap.put("sus_no", sus_no);
			Mmap.put("mct", mct);
			Mmap.put("type_of_stock", type_of_stock);
			Mmap.put("list", list);
			Mmap.put("std_nomclature", std_nomclature);
			Mmap.put("depot_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
			if (list.size() == 0) {
				Mmap.put("list", list);
			} else {
				String type_of_stock12 = "";
				for (int i = 0; i < list.size(); i++) {
					String type_of_stock_c1 = list.get(i).get(3);
					if (type_of_stock_c1 == null || type_of_stock_c1.equals("") || type_of_stock_c1.equals(null)) {
						type_of_stock12 += "$('select#type_of_stock" + list.get(i).get(4) + "').val('0') ;\n";
					} else {
						type_of_stock12 += "$('select#type_of_stock" + list.get(i).get(4) + "').val('"
								+ list.get(i).get(3) + "') ;\n";
					}
				}
				Mmap.put("type_of_stock_c1", type_of_stock12);
			}
		} else {

			if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
				Mmap.put("msg", "Please Select SUS No.");
			} else if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.depot_sus_noMSG);
				return new ModelAndView("redirect:type_of_stock_c");
			} else if (depot_name.equals("") || depot_name.equals(null) || depot_name == "" || depot_name == null) {
				Mmap.put("msg", "Please Select Depot Name.");
			} else if (validation.checkUnit_nameLength(depot_name) == false) {
				Mmap.put("msg", validation.depotunit_nameMSG);
				return new ModelAndView("redirect:type_of_stock_c");
			} else if (std_nomclature.equals("0") || std_nomclature == "0") {
				Mmap.put("msg", "Please Select Std Nomclature .");
			} else if (validation.std_nomclatureTMSLength(std_nomclature) == false) {
				Mmap.put("msg", validation.std_nomclatureTMSMSG);
				return new ModelAndView("redirect:type_of_stock_c");
			}

		}

		Mmap.put("sus_no", sus_no);
		if (sus_no != "" && sus_no.length() == 8) {
			Mmap.put("depot_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		}
		Mmap.put("mct", mct);
		Mmap.put("type_of_stock", type_of_stock);
		Mmap.put("std_nomclature", std_nomclature);
		return new ModelAndView("type_of_stock_c_Tiles");
	}
	
	
	@RequestMapping(value = "/UpdatestockC", method = RequestMethod.POST)
	public @ResponseBody String UpdatestockC(String type_of_stock,int id,HttpSession session1 ) {
		
		String  roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("type_of_stock_c", roleid);	
		if(val == false) {
			return "Access Denied.";
		}
			
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE TB_TMS_EMAR_DRR_DIR_DTL set type_of_stock=:type_of_stock where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("type_of_stock", type_of_stock);
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		return "Success";
	}
	
	
	@RequestMapping(value = "/getDepotSUSNoList_C", method = RequestMethod.POST)
	public @ResponseBody List<String> getDepotSUSNoList_C(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")  || roleAccess.equals("Depot")){
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct sus_no from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition = '1' and upper(sus_no) like :sus_no ").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;
	}
	
	
	@RequestMapping(value = "/getDepotUnitNameList_C", method = RequestMethod.POST)
	public @ResponseBody List<String> getDepotUnitNameList_C(String unit_name,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Query q = null;
		if(roleAccess.equals("Unit")  || roleAccess.equals("Depot")){ 
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and upper(unit_name) like :unit_name ").setMaxResults(10);
			q.setParameter("sus_no", roleSusNo);
		}else {
			q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name ").setMaxResults(10);
		}
		q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;	
	}
	
	
	@RequestMapping(value = "/getMCT_NomenList_C", method = RequestMethod.POST)
	public @ResponseBody List<String> getMCT_NomenList_C(String sus_no,HttpSession sessionA,String stock) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select distinct m.std_nomclature from TB_TMS_BANUM_DIRCTRY b,TB_TMS_EMAR_DRR_DIR_DTL d,TB_TMS_MCT_MASTER m "
				+ "where m.status='ACTIVE' and b.ba_no=d.em_no and d.sus_no=:sus_no and "
				+ "m.mct=b.mct and d.type_of_stock=:stock "
				+ " and d.em_no not in (select em_no from TB_TMS_EMAR_DRR_DIR_DTL where sus_no=:d_sus_no and  type_of_stock in ('3','4','5')) "
				+ "order by m.std_nomclature");
		q.setParameter("sus_no", sus_no);
		q.setParameter("stock", stock);
		q.setParameter("d_sus_no", sus_no);
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
		/*String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;*/
	}
	
	
	@RequestMapping(value = "/getNomclature_C", method = RequestMethod.POST)
	public @ResponseBody List<String> getNomclature_C(String nomen,HttpSession sessionUserId) {
		nomen = nomen.replace("&#40;","(");
		nomen = nomen.replace("&#41;",")");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();		
		Query q = session.createQuery("select distinct mct from TB_TMS_MCT_MASTER where status = 'ACTIVE' and std_nomclature=:nomen");
		q.setParameter("nomen", nomen);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		List<String> FinalList = new ArrayList<String>();
	    for(int i=0; i<list.size();i++) {
	    	byte[] encCode = null;
			try {
				String mct = String.valueOf(list.get(i));
				encCode = c.doFinal(mct.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		    FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey+"4bsjyg==");
	    return FinalList;
	}
}
