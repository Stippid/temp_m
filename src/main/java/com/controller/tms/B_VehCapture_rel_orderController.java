package com.controller.tms;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Capture_rel_orderDAO;
import com.dao.tms.Capture_rel_orderDAOImpl;
import com.dao.tms.RioDAO;
import com.dao.tms.RioDAOImpl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MCT_NODAL_DIR_MASTER;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_MAIN;
import com.models.TB_TMS_RO_VEHICLE_DTLS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class B_VehCapture_rel_orderController {

	@Autowired
	Capture_rel_orderDAO captureDAO = new Capture_rel_orderDAOImpl();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	B_VehSearchMVCRController s = new B_VehSearchMVCRController();
	ValidationController validation = new ValidationController();

	@Autowired
	RioDAO rioDAO = new RioDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();

	@RequestMapping(value = "/capture_rel_order", method = RequestMethod.GET)
	public ModelAndView capture_rel_order(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("capture_rel_order", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
		Mmap.put("msg", msg);
		Mmap.put("veh", "B");
		return new ModelAndView("capture_rel_orderTiles");
	}

	 @RequestMapping(value = "/c_capture_rel_order", method = RequestMethod.GET)
		public ModelAndView c_capture_rel_order(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("capture_rel_order", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
			Mmap.put("msg", msg);
			Mmap.put("veh", "C");
			return new ModelAndView("capture_rel_orderTiles");
		}

	 @RequestMapping(value = "/a_capture_rel_order", method = RequestMethod.GET)
		public ModelAndView a_capture_rel_order(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("capture_rel_order", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
			Mmap.put("msg", msg);
			Mmap.put("veh", "A");
			return new ModelAndView("capture_rel_orderTiles");
		}

	
	
	
	
	@RequestMapping(value = "/mvcr_last_updateDate", method = RequestMethod.POST)
	public @ResponseBody List<TB_TMS_MVCR_PARTA_MAIN> mvcr_last_updateDate(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		return rioDAO.getLastUpdateDateUnit(sus_no);
	}

	@RequestMapping(value = "/getMctNoDetailList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMctNoDetailList(String mct, String type_of_vehicle,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (type_of_vehicle == null || type_of_vehicle.equals("0") || type_of_vehicle == "0") {
			q = session.createQuery(
					"select distinct mct from TB_TMS_MCT_MASTER where  cast(mct as string) like :mct and status='ACTIVE'")
					.setMaxResults(10);
		} else {
			q = session.createQuery(
					"select distinct mct from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle and cast(mct as string) like :mct  and status='ACTIVE' ")
					.setMaxResults(10);
			q.setParameter("type_of_vehicle", type_of_vehicle);
		}
		q.setParameter("mct", mct + "%");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();

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
				String mct_number = String.valueOf(list.get(i));
				encCode = c.doFinal(mct_number.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	
	
	@RequestMapping(value = "/getMctNoDetailListfree", method = RequestMethod.POST)
    public @ResponseBody List<String> getMctNoDetailListfree(String mct, String type_of_vehicle,String issue_stock,String depot_sus_no,
            HttpSession sessionUserId) {
        int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String tableName;
        String bano;
        String ba_no;
        
        if (type_of_vehicle.equals("A")) {
               tableName = "TB_TMS_CENSUS_DRR_DIR_DTL";
               bano="d.ba_no";
               ba_no="ba_no";
        } else if (type_of_vehicle.equals("B")) {
               tableName = "TB_TMS_DRR_DIR_DTL";
               bano="d.ba_no";
               ba_no="ba_no";
        } else if (type_of_vehicle.equals("C")) {
               tableName = "TB_TMS_EMAR_DRR_DIR_DTL";
               bano="d.em_no";
               ba_no="em_no";
        } else {
             tableName = "";
             bano="";
             ba_no="";
        }
        
        Query q = null;
        if (issue_stock.equals("free_stock") && !depot_sus_no.equals("")) {
            q = session.createQuery(
                    "select distinct m.mct from TB_TMS_BANUM_DIRCTRY b, "+ tableName +" d,TB_TMS_MCT_MASTER m "
                    + "                 where m.status='ACTIVE' and b.ba_no="+ bano +" and d.sus_no=:sus_no     and   m.mct=b.mct   "
                    + "                     and "+bano+" not in (select "+ba_no+" from   "+ tableName +" where sus_no=:d_sus_no and   type_of_stock in ('3','4','5')) "
                    + "                and   cast(m.mct as string) like :mct and m.status='ACTIVE'   order by m.mct ")
                    .setMaxResults(10);
            
            q.setParameter("sus_no", depot_sus_no);            
            q.setParameter("d_sus_no", depot_sus_no);
        } else if (type_of_vehicle == null || type_of_vehicle.equals("0") || type_of_vehicle == "0" ){
            q = session.createQuery(
                    "select distinct m.mct from TB_TMS_BANUM_DIRCTRY b,"+tableName+" d,TB_TMS_MCT_MASTER m "
                    + "                 where m.status='ACTIVE' and b.ba_no="+bano+"   and "
                    + "                 m.mct=b.mct   "
                    + "               and "+bano+" not in (select "+ba_no+" from "+tableName+" where     type_of_stock in ('3','4','5')) "
                    + "                and   cast(m.mct as string) like :mct and m.status='ACTIVE'   order by m.mct ")
                    .setMaxResults(10);
        }
        else{
            q = session.createQuery(
                "select distinct m.mct from TB_TMS_BANUM_DIRCTRY b,"+tableName+" d,TB_TMS_MCT_MASTER m "
                    + "                   where m.status='ACTIVE' and b.ba_no="+bano+"   and "
                    + "                 m.mct=b.mct "
                    + "                 and "+bano+" not in (select "+ba_no+" from "+tableName+" where   type_of_stock in ('3','4','5')) "
                    + "                   and cast(m.mct as string) like :mct   and m.type_of_vehicle=:type_of_vehicle and m.status='ACTIVE' order by m.mct ")
                    .setMaxResults(10);
            q.setParameter("type_of_vehicle", type_of_vehicle);
        }
  
        q.setParameter("mct", mct + "%");
        
        @SuppressWarnings("unchecked")
        List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
      
        tx.commit();
        session.close();

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
                String mct_number = String.valueOf(list.get(i));
                encCode = c.doFinal(mct_number.getBytes());
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }
            String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
            FinalList.add(base64EncodedEncryptedCode);
        }
        FinalList.add(enckey + "4bsjyg==");
        return FinalList;
    }
	
	@RequestMapping(value = "/getStdNomenclatureListFromVeh_cat", method = RequestMethod.POST)
	public @ResponseBody List<String> getStdNomenclatureListFromVeh_cat(String mctNomen, String type_of_vehicle,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (type_of_vehicle.equals("0") || type_of_vehicle == "0") {
			q = session.createQuery(
					"select distinct std_nomclature from TB_TMS_MCT_MASTER where  UPPER(std_nomclature) like :mctNomen and status='ACTIVE'")
					.setMaxResults(10);
		} else {
			q = session.createQuery(
					"select distinct std_nomclature from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle and UPPER(std_nomclature) like :mctNomen and status='ACTIVE'")
					.setMaxResults(10);
			q.setParameter("type_of_vehicle", type_of_vehicle);
		}
		q.setParameter("mctNomen", "%" + mctNomen.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
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
				String nomen = String.valueOf(list.get(i));
				encCode = c.doFinal(nomen.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	
	

	@RequestMapping(value = "/getStdNomenclatureListFromVeh_catfree_stock", method = RequestMethod.POST)
	public @ResponseBody List<String> getStdNomenclatureListFromVeh_catfree_stock(String mctNomen, String type_of_vehicle,String issue_stock,String depot_sus_no,
			HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
      String tableName;
		
		if (type_of_vehicle.equals("A")) {
		    tableName = "TB_TMS_CENSUS_DRR_DIR_DTL";
		} else if (type_of_vehicle.equals("B")) {
		    tableName = "TB_TMS_DRR_DIR_DTL";
		} else if (type_of_vehicle.equals("C")) {
		    tableName = "TB_TMS_EMAR_DRR_DIR_DTL";
		} else {
		   tableName = "";
		}
		Query q = null;
		if (type_of_vehicle.equals("0")) {
		    q = session.createQuery(
		        "select distinct std_nomclature from TB_TMS_MCT_MASTER where UPPER(std_nomclature) like :mctNomen and status='ACTIVE'")
		        .setMaxResults(10);
		}
		else if (issue_stock.equals("free_stock") && !depot_sus_no.equals("")) {
		    q = session.createQuery("select distinct m.std_nomclature from TB_TMS_BANUM_DIRCTRY b,"+tableName+" d,TB_TMS_MCT_MASTER m "
		        + "where m.status='ACTIVE' and b.ba_no=d.ba_no and d.sus_no=:sus_no and "
		        + "m.mct=b.mct and d.type_of_stock=:stock "
		        + " and d.ba_no not in (select ba_no from "+tableName+" where sus_no=:d_sus_no and  type_of_stock in ('3','4','5'))  "
		        + " and UPPER(m.std_nomclature) like :mctNomen order by m.std_nomclature ")
		        .setMaxResults(10);
		    q.setParameter("sus_no", depot_sus_no);
		    q.setParameter("stock", "0");
		    q.setParameter("d_sus_no", depot_sus_no); 
		}
		else {
		    q = session.createQuery(
		        "select distinct std_nomclature from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle and UPPER(std_nomclature) like :mctNomen and status='ACTIVE'")
		        .setMaxResults(10);
		    q.setParameter("type_of_vehicle", type_of_vehicle);
		}
		q.setParameter("mctNomen", "%" + mctNomen.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
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
				String nomen = String.valueOf(list.get(i));
				encCode = c.doFinal(nomen.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getStdNomenclature")
	public @ResponseBody List<String> getStdNomenclature(BigInteger mct) {
		return captureDAO.getStdNomenclature(mct);
	}

	@RequestMapping(value = "/getRONoDetailList")
	public @ResponseBody List<TB_TMS_RO_MAIN> getRONoDetailList() {
		return captureDAO.getRONoDetailList();
	}

	@RequestMapping(value = "/getSUSNoDetailList")
	public @ResponseBody List<Miso_Orbat_Unt_Dtl> getSUSNoDetailList() {
		return captureDAO.getSUSNoDetailList();
	}

	@RequestMapping(value = "/getUnitNameFromSus", method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitNameFromSus(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getUnitNameFromSus(sus_no);
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

				encCode = c.doFinal(list.get(0).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "9bdhyg==");
		return FinalList;
	}

	@RequestMapping(value = "/getComdSusNoFromSus", method = RequestMethod.POST)
	public @ResponseBody List<String> getComdSusNoFromSus(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getComdSusNoFromSus(sus_no);
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getCorpSusNoFromSus", method = RequestMethod.POST)
	public @ResponseBody List<String> getCorpSusNoFromSus(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getCorpSusNoFromSus(sus_no);
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getNRSFromSus", method = RequestMethod.POST)
	public @ResponseBody List<String> getNRSFromSus(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getNRSFromSus(sus_no);
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
	//Mitesh 14-10-24
	@RequestMapping(value = "/getsdReliefFromSus", method = RequestMethod.POST)
	public @ResponseBody List<String> getsdReliefFromSus(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
	
		List<String> list = captureDAO.getsdReliefFromSus(sus_no);
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

	@RequestMapping(value = "/getDepotSUSNoDetailList", method = RequestMethod.POST)
	public @ResponseBody List<String> getDepotSUSNoDetailList(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct a.sus_no from TB_TMS_MCT_NODAL_DIR_MASTER a, Miso_Orbat_Unt_Dtl b where a.sus_no = b.sus_no and b.status_sus_no= 'Active' and  upper(a.sus_no) like :sus_no and a.type_of_agncy='Depot' ").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getDepotNameDetailList", method = RequestMethod.POST)
	public @ResponseBody List<String> getDepotNameDetailList(String unit_name, HttpSession sessionUserId) {
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Query q = null;
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Depot' and sus_no=:sus_no)  "
					+ "and upper(unit_name) like :unit_name and status_sus_no='Active' order by unit_name");

			q.setParameter("unit_name", "%" + unit_name.toUpperCase() + "%");
			q.setParameter("sus_no", roleSusNo);
		} else {
			q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in (select sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Depot')  "
					+ "and upper(unit_name) like :unit_name and status_sus_no='Active' order by unit_name");

			q.setParameter("unit_name", "%" + unit_name.toUpperCase() + "%");
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getDepotSUSFromDepotName")
	public @ResponseBody List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotSUSFromDepotName(String unit_name) {
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		return captureDAO.getDepotSUSFromDepotName(unit_name);
	}

	@RequestMapping(value = "/getDepotNameFromDepotSusNo")
	public @ResponseBody List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotNameFromDepotSusNo(String sus_no) {
		return captureDAO.getDepotNameFromDepotSusNo(sus_no);
	}

	@RequestMapping(value = "/getUEFromMCT", method = RequestMethod.POST)
	public @ResponseBody List<String> getUEFromMCT(String mct, String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String ue = "";
		if (!mct.equals("") && !sus_no.equals("")) {
			ue = captureDAO.getUEFromMCT_SUSNO(mct, sus_no);
		}
		List<String> FinalList = new ArrayList<String>();
		if (!ue.equals("")) {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}

			byte[] encCode = null;
			try {
				String total = String.valueOf(ue);
				encCode = c.doFinal(total.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getUHFromMCT", method = RequestMethod.POST)
	public @ResponseBody List<String> getUHFromMCT(BigInteger mct, String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getUHFromMCT(mct, sus_no);
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

	@RequestMapping(value = "/getPrfUHFromMCT", method = RequestMethod.POST)
	public @ResponseBody List<String> getPrfUHFromMCT(BigInteger mct, String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getPrfUHFromMCT(mct, sus_no);
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

	@RequestMapping(value = "/getPrfUEFromMCT", method = RequestMethod.POST)
	public @ResponseBody List<String> getPrfUEFromMCT(BigInteger mct, String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		List<String> list = captureDAO.getPrfUEFromMCT(mct, sus_no);
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
				String ue = "";
				if (list.get(i) != null) {
					ue = String.valueOf(list.get(i));
				}
				encCode = c.doFinal(ue.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}

	/*@RequestMapping(value = "/capture_rel_orderAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("capture_rel_orderCMD") TB_TMS_RO_MAIN ro,
			HttpServletRequest request, ModelMap model, HttpSession session) throws java.text.ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("capture_rel_order", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getParameter("ro_no1").equals("")) {
			model.put("msg", "Please Enter the Rel Order NO.");
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.ro_noLength(request.getParameter("ro_no1")) == false) {
			model.put("msg", validation.Ro_noMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (request.getParameter("accounting1").equals("")) {
			model.put("msg", "Please Select Accounting.");
			return new ModelAndView("redirect:capture_rel_order");
		} else if (request.getParameter("sus_no1").equals("")) {
			model.put("msg", "Please Enter SUS No.");
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.sus_noLength(request.getParameter("sus_no1")) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.checkUnit_nameLength(request.getParameter("unit_name")) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.sus_noLength(request.getParameter("command_sus_no")) == false) {
			model.put("msg", validation.command_susMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.checkUnit_nameLength(request.getParameter("comd_name")) == false) {
			model.put("msg", validation.command_nameMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (request.getParameter("issue_stock1").equals("")) {
			model.put("msg", "Please Select Issue stk.");
			return new ModelAndView("redirect:capture_rel_order");
		} else if (request.getParameter("depot_sus_no1").equals("")) {
			model.put("msg", "Please Enter Depot/Unit SUS No.");
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.sus_noLength(request.getParameter("depot_sus_no1")) == false) {
			model.put("msg", validation.depot_sus_noMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.checkUnit_nameLength(request.getParameter("depot_name1")) == false) {
			model.put("msg", validation.depotunit_nameMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.check255Length(request.getParameter("remarks1")) == false) {
			model.put("msg", validation.remarksMSGTMS);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.nrsLength(request.getParameter("nrs1")) == false) {
			model.put("msg", validation.nrsMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.code_no_fromLength(request.getParameter("quantity1")) == false) {
			model.put("msg", validation.quantityMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else if (validation.check255Length(request.getParameter("std_nomclature1")) == false) {
			model.put("msg", validation.std_nomclatureROMSG);
			return new ModelAndView("redirect:capture_rel_order");
		} else {
			Session sessionHQL = null;
			Transaction tx = null;
			try {
				sessionHQL = HibernateUtil.getSessionFactory().openSession();
				tx = sessionHQL.beginTransaction();

				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String username = session.getAttribute("username").toString();
				String issue_date = request.getParameter("issue_date1");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date issue_date1 = null;
				try {
					issue_date1 = formatter.parse(issue_date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				Date today_dt = new Date();
				if (issue_date1.compareTo(today_dt) > 0 && issue_date1.compareTo(today_dt) != 0) {
					model.put("msg", "Please Enter Valid Date Of Request.");
					return new ModelAndView("redirect:capture_rel_order");
				}
				ro.setRo_no(request.getParameter("ro_no1"));
				ro.setAccounting(request.getParameter("accounting1"));
				ro.setType_of_accounting(request.getParameter("type_of_accounting1"));
				ro.setSus_no(request.getParameter("sus_no1"));
				ro.setRo_date(date);
				ro.setOther_issue_type(request.getParameter("other_issue_type1"));
				ro.setIssue_stock(request.getParameter("issue_stock1"));
				// ro.setNrs(request.getParameter("nrs1"));
				ro.setDepot_sus_no(request.getParameter("depot_sus_no1"));
				ro.setCreation_date(new Date());
				ro.setValid_upto(new Date());
				ro.setCreation_by(username);
				ro.setStatus("0");
				ro.setVersion_no(1);
				ro.setDate_of_submission(issue_date1);
				int ro_qty = 0;
				int count = Integer.parseInt(request.getParameter("count"));
				for (int j = 1; j <= count; j++) {
					ro_qty += Integer.parseInt(request.getParameter("quantity" + j));
				}
				ro.setQuantity(ro_qty);
				// Session sessionRoMain = HibernateUtil.getSessionFactory().openSession();
				// sessionRoMain.beginTransaction();
				sessionHQL.save(ro);
				sessionHQL.flush();
				sessionHQL.clear();
				// sessionRoMain.getTransaction().commit();
				// sessionRoMain.close();
				for (int i = 1; i <= count; i++) {
					String type_of_ro = request.getParameter("type_of_ro" + i);
					String type_of_vehicle = request.getParameter("type_of_vehicle" + i);
					BigInteger mct = new BigInteger(request.getParameter("mct" + i));
					String mct1 = request.getParameter("mct");
					String std_nomclature = request.getParameter("std_nomclature" + i);
					if (type_of_ro.equals("3") && request.getParameter("loan_auth" + i).equals("")) {
						model.put("msg", "Please Enter Loan Authority.");
						return new ModelAndView("redirect:capture_rel_order");
					}
					if (type_of_ro.equals("3") && request.getParameter("loan_duration" + i).equals("")) {
						model.put("msg", "Please Enter Loan Duration.");
						return new ModelAndView("redirect:capture_rel_order");
					}
					if (type_of_vehicle.equals("")) {
						model.put("msg", "Please Select Type Of Vehicle.");
						return new ModelAndView("redirect:capture_rel_order");
					}
					if (request.getParameter("mct") != "") {
						model.put("msg", "Please Enter MCT No.");
						return new ModelAndView("redirect:capture_rel_order");
					} else if (validation.mctLength(request.getParameter("mct1")) == false) {
						model.put("msg", validation.mctMSG);
						return new ModelAndView("redirect:capture_rel_order");
					} else if (request.getParameter("inliuemct1") != ""
							& validation.mctLength(request.getParameter("inliuemct1")) == false) {
						model.put("msg", validation.InmctMSG);
						return new ModelAndView("redirect:capture_rel_order");
					} else if (request.getParameter("std_nomclature_inlieu1") != ""
							& validation.check255Length(request.getParameter("std_nomclature_inlieu1")) == false) {
						model.put("msg", validation.std_nomclatureROMSG);
						return new ModelAndView("redirect:capture_rel_order");
					}
					if (request.getParameter("accounting" + i).equals("")) {
						model.put("msg", "Please Select Accounting.");
						return new ModelAndView("redirect:capture_rel_order");
					}
					if (request.getParameter("depot_sus_no" + i).equals("")) {
						model.put("msg", "Please Select Depo SUS NO.");
						return new ModelAndView("redirect:capture_rel_order");
					}
					TB_TMS_RO_VEHICLE_DTLS rovehdtl = new TB_TMS_RO_VEHICLE_DTLS();
					rovehdtl.setAccounting(request.getParameter("accounting" + i));
					rovehdtl.setClass_vehicle("1");
					rovehdtl.setCommand_sus_no(request.getParameter("command_sus_no" + i));
					rovehdtl.setCreationdate(new Date());
					rovehdtl.setCreation_by(username);
					rovehdtl.setDepot_sus_no(request.getParameter("depot_sus_no" + i));
					rovehdtl.setSur_def("Y");
					rovehdtl.setType_of_vehicle(type_of_vehicle);
					rovehdtl.setStd_nomclature(std_nomclature);
					;
					String inliuemct = request.getParameter("inliuemct" + i);
					if (inliuemct.equals("")) {
						rovehdtl.setInliuemct("0");
					} else {
						rovehdtl.setInliuemct(request.getParameter("inliuemct" + i));
					}
					rovehdtl.setIssue_precedence(request.getParameter("issue_precedence" + i));
					rovehdtl.setIssue_stock(request.getParameter("issue_stock" + i));

					if (type_of_ro.equals("1")) {
						ro.setType_of_ro("Single RO");
						rovehdtl.setType_of_ro("Single RO");
					} else if (type_of_ro.equals("3")) {
						rovehdtl.setLoan_auth(request.getParameter("loan_auth" + i));
						rovehdtl.setLoan_duration(Integer.parseInt(request.getParameter("loan_duration" + i)));

						ro.setType_of_ro("Loan RO");
						rovehdtl.setType_of_ro("Loan RO");
					} else if (type_of_ro.equals("4")) {
						ro.setType_of_ro("NRU RO");
						rovehdtl.setType_of_ro("NRU RO");
					} else if (type_of_ro.equals("5")) {
						ro.setType_of_ro("Provisional RO");
						rovehdtl.setType_of_ro("Provisional RO");
					} else {
						model.put("msg", "Please Enter Type Of RO");
					}
					rovehdtl.setMct(mct);
					// rovehdtl.setNrs(request.getParameter("nrs"+i));
					rovehdtl.setOther_issue_type(request.getParameter("other_issue_type" + i));
					rovehdtl.setQuantity(Integer.parseInt(request.getParameter("quantity" + i)));
					rovehdtl.setRemarks(request.getParameter("remarks" + i));
					rovehdtl.setRo_no(request.getParameter("ro_no" + i));
					rovehdtl.setStatus("0");
					rovehdtl.setSus_no(request.getParameter("sus_no" + i));
					rovehdtl.setType_of_issue(request.getParameter("type_of_issue" + i));
					rovehdtl.setVersion_no(1);
					rovehdtl.setDate_of_submission(issue_date1);
					// Session session1 = HibernateUtil.getSessionFactory().openSession();
					// session1.beginTransaction();
					sessionHQL.save(rovehdtl);
					sessionHQL.flush();
					sessionHQL.clear();
					// session1.getTransaction().commit();
					// session1.close();
				}
				tx.commit();
				model.put("msg", "Release Order Created.");
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldn’t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		return new ModelAndView("redirect:capture_rel_order");
	}*/
	
	@RequestMapping(value = "/admin/capture_rel_orderAction", method = RequestMethod.POST)
	public @ResponseBody List<String> capture_rel_orderAction(HttpServletRequest request,HttpSession session) {
		List<String> list = new ArrayList<>();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("capture_rel_order", roleid);
		if (val == false) {
			list.add("You can't Access this URL");
			return list;
		}
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String username = session.getAttribute("username").toString();
		Date issue_date = null;
		try {
			issue_date = formatter.parse(date);
		} catch (java.text.ParseException e){	}
		
		String ro_no = request.getParameter("ro_no");
		String sus_no = request.getParameter("sus_no");
		String accounting = request.getParameter("accounting");
		String unit_name = request.getParameter("unit_name");
		String command_sus_no = request.getParameter("command_sus_no");
		String corps_sus_no = request.getParameter("corp_sus_no");
		String issue_stock = request.getParameter("issue_stock");
		String depot_sus_no = request.getParameter("depot_sus_no");
		String remarks = request.getParameter("remarks");
		String quantity = request.getParameter("quantity");
		String other_issue_type =request.getParameter("other_issue_type");
	
		if (ro_no.equals("")) {
			list.add("Please Enter the Rel Order NO.");
			return list;
		} else if (validation.ro_noLength(ro_no) == false) {
			list.add(validation.Ro_noMSG);
			return list;
		} else if (accounting.equals("")) {
			list.add("Please Select Accounting.");
			return list;
		} else if (sus_no.equals("")) {
			list.add("Please Enter SUS No.");
			return list;
		} else if (validation.sus_noLength(sus_no) == false) {
			list.add(validation.sus_noMSG);
			return list;
		} else if (validation.checkUnit_nameLength(unit_name) == false) {
			list.add(validation.unit_nameMSG);
			return list;
		} else if (validation.sus_noLength(command_sus_no) == false) {
			list.add(validation.command_susMSG);
			return list;
		} else if (issue_stock.equals("")) {
			list.add("Please Select Issue stk.");
			return list;
		} else if (depot_sus_no.equals("")) {
			list.add("Please Enter Depot/Unit SUS No.");
			return list;
		} else if (validation.sus_noLength(depot_sus_no) == false) {
			list.add(validation.depot_sus_noMSG);
			return list;
		} else if (validation.check255Length(remarks) == false) {
			list.add(validation.remarksMSGTMS);
			return list;
		} else if (validation.code_no_fromLength(quantity) == false) {
			list.add(validation.quantityMSG);
			return list;
		}else {
			Session sessionHQL = null;
			Transaction tx = null;
			try {
				sessionHQL = HibernateUtil.getSessionFactory().openSession();
				tx = sessionHQL.beginTransaction();
				TB_TMS_RO_MAIN ro = new TB_TMS_RO_MAIN();
				ro.setRo_no(ro_no);
				ro.setAccounting(accounting);
				ro.setSus_no(sus_no);
				ro.setRo_date(date);
				ro.setOther_issue_type(other_issue_type);
				ro.setIssue_stock(issue_stock);
				ro.setDepot_sus_no(depot_sus_no);
				ro.setCreation_date(new Date());
			    ro.setValid_upto(new Date());
				ro.setCreation_by(username);
				ro.setStatus("0");
				ro.setVersion_no(1);
				ro.setDate_of_submission(issue_date);
				ro.setQuantity(Integer.parseInt(quantity));
				
				String type_of_ro = request.getParameter("type_of_ro");
				String type_of_vehicle = request.getParameter("type_of_vehicle");
				BigInteger mct = new BigInteger(request.getParameter("mct"));
				String mct1 = request.getParameter("mct");
				String std_nomclature = request.getParameter("std_nomclature");
				String inliuemct = request.getParameter("inliuemct");
				String issue_precedence = request.getParameter("issue_precedence");
				String type_of_issue = request.getParameter("type_of_issue");
				String std_nomclature_inlieu = request.getParameter("std_nomclature_inlieu");
				
				if(type_of_ro.equals("3") && request.getParameter("loan_auth").equals("")){
					list.add("Please Enter Loan Authority.");
					return list;
				}
				if(type_of_ro.equals("3") && request.getParameter("loan_duration").equals("")){
					list.add("Please Enter Loan Duration.");
					return list;
				}
				if(type_of_vehicle.equals("")){
					list.add("Please Select Type Of Vehicle.");
					return list;
				}
				if(mct1.equals("")){
					list.add("Please Enter MCT No.");
					return list;
				} else if (validation.mctLength(mct1) == false) {
					list.add(validation.mctMSG);
					return list;
				} else if (!inliuemct.equals("") & validation.mctLength(inliuemct) == false) {
					list.add(validation.InmctMSG);
					return list;
				} else if (!std_nomclature_inlieu.equals("") & validation.check255Length(std_nomclature_inlieu) == false) {
					list.add(validation.std_nomclatureROMSG);
					return list;
				}
				TB_TMS_RO_VEHICLE_DTLS rovehdtl = new TB_TMS_RO_VEHICLE_DTLS();
				rovehdtl.setAccounting(accounting);
				rovehdtl.setClass_vehicle("1");
				rovehdtl.setCommand_sus_no(command_sus_no);
				rovehdtl.setCorps_sus_no(corps_sus_no);
				rovehdtl.setCreationdate(new Date());
				rovehdtl.setCreation_by(username);
				rovehdtl.setDepot_sus_no(depot_sus_no);
				
				rovehdtl.setSur_def("Y");
				rovehdtl.setType_of_vehicle(type_of_vehicle);
				rovehdtl.setStd_nomclature(std_nomclature);
				if (inliuemct.equals("")) {
					rovehdtl.setInliuemct("0");
				} else {
					rovehdtl.setInliuemct(inliuemct);
				}
				rovehdtl.setIssue_precedence(issue_precedence);
				rovehdtl.setIssue_stock(issue_stock);

				if (type_of_ro.equals("1")) {
					ro.setType_of_ro("Single RO");
					rovehdtl.setType_of_ro("Single RO");
				} else if (type_of_ro.equals("3")) {
					rovehdtl.setLoan_auth(request.getParameter("loan_auth"));
					rovehdtl.setLoan_duration(Integer.parseInt(request.getParameter("loan_duration")));

					ro.setType_of_ro("Loan RO");
					rovehdtl.setType_of_ro("Loan RO");
				} else if (type_of_ro.equals("4")) {
					ro.setType_of_ro("NRU RO");
					rovehdtl.setType_of_ro("NRU RO");
				} else if (type_of_ro.equals("5")) {
					ro.setType_of_ro("Conditional RO");
					rovehdtl.setType_of_ro("Conditional RO");
				} else {
					list.add("Please Enter Type Of RO");
					return list;
				}
				rovehdtl.setMct(mct);
				rovehdtl.setOther_issue_type(other_issue_type);
				rovehdtl.setQuantity(Integer.parseInt(quantity));
				rovehdtl.setRemarks(remarks);
				rovehdtl.setRo_no(ro_no);
				rovehdtl.setStatus("0");
				rovehdtl.setSus_no(sus_no);
				rovehdtl.setType_of_issue(type_of_issue);
				rovehdtl.setVersion_no(1);
				rovehdtl.setDate_of_submission(issue_date);
				
				sessionHQL.save(ro);
				sessionHQL.save(rovehdtl);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				list.add("Release Order Created.");
				return list;
			} catch (RuntimeException e) {
				tx.rollback();
				list.add("transaction roll back :"+e.getMessage());
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/search_capture_rel_order", method = RequestMethod.GET)
	public ModelAndView search_capture_rel_order(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_capture_rel_order", roleid);
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
		Mmap.put("roleType", roleType);
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		return new ModelAndView("search_capture_rel_orderTiles");
	}

	@RequestMapping(value = "/admin/getAttributeFromROMainAndVehDtl", method = RequestMethod.POST)
	public ModelAndView getAttributeFromROMainAndVehDtl( HttpSession sessionA,HttpServletRequest request,
			@ModelAttribute("depot_sus_no2") String depot_sus_no,@ModelAttribute("depot_name2") String depot_name,
			@ModelAttribute("sus_no2") String sus_no, @ModelAttribute("unit_name2") String unit_name,
			@ModelAttribute("issue_date2") String issue_date, @ModelAttribute("type_of_ro2") String type_of_ro,
			@ModelAttribute("to_date2") String to_date, @ModelAttribute("type_of_issue2") String type_of_issue,
			@ModelAttribute("status2") String status, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_capture_rel_order", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
		
		depot_name = depot_name.replace("&#40;", "(");
		depot_name = depot_name.replace("&#41;", ")");

		String fdt = request.getParameter("issue_date2");
		String tdt = request.getParameter("to_date2");

		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				model.put("msg", "To Date should not be less than From Date");
				return new ModelAndView("redirect:search_capture_rel_order");
			}
		}
		if (!sus_no.equals("") && sus_no.length() == 8) {
			model.put("sus_no", sus_no);
			model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		} else if (sus_no != "" & sus_no != null & !sus_no.equals("") & !sus_no.equals(null)
				& validation.sus_noLength(sus_no) == false) {
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:search_capture_rel_order");
		}
		
		model.put("depot_sus_no", depot_sus_no);
		model.put("depot_name",depot_name);
		

		if (validation.checkUnit_nameLength(unit_name) == false) {
			model.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:search_capture_rel_order");
		}

		model.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
		model.put("issue_date", issue_date);
		model.put("type_of_issue", type_of_issue);
		model.put("type_of_ro", type_of_ro);
		if (status != "") {
			model.put("status", status);
		}
		if (!issue_date.equals("") && to_date.equals("")) {
			model.put("to_date", to_date);
		}
		ArrayList<ArrayList<String>> list = captureDAO.SearchgetAttributeFromROMainAndVehDtl(status, issue_date,to_date, type_of_issue, type_of_ro, sus_no, depot_sus_no, sessionA);
		String appButton = "<a class='action_icons action_approve'  onclick='return approve_test();'>Approve</a>";
		String rejectButton = "<a class='action_icons action_reject'  onclick='return reject_test();'>Reject</a>";
		String deleteButton = "<a class='action_icons action_delete'  onclick='return delete_test();'>Delete</a>";

		if (status.equals("0")) {
			if (roleAccess.equals("MISO") && roleType.equals("ALL")) {
				model.put("appButton", appButton);
				model.put("rejectButton", rejectButton);
				model.put("deleteButton", deleteButton);
			}
			if (roleAccess.equals("MISO") && roleType.equals("APP")){
				model.put("appButton", appButton);
				model.put("rejectButton", rejectButton);
			}
			if (roleAccess.equals("MISO") && roleType.equals("DEO")){
				model.put("deleteButton", deleteButton);
			}
		} else if (status.equals("2")){
			if (roleAccess.equals("MISO") && (roleType.equals("DEO") || roleType.equals("ALL"))) {
				model.put("deleteButton", deleteButton);
			}
		}
		model.put("list", list);
		return new ModelAndView("search_capture_rel_orderTiles");
	}
	
	//Added by Mitesh17-10
	@RequestMapping(value = "/UpdateRoMain", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> updateRO(HttpServletRequest request,HttpSession session,String ro_no,String extended_date){
		
		return captureDAO.updateRomain(ro_no, extended_date);
	}
	
	
	@RequestMapping(value = "/getCancelRO", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCancelRO(HttpServletRequest request,HttpSession session,int id,String ro_no,String type_veh,String remarks){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String username = session.getAttribute("username").toString();
		Date cur_date = null;
		try {
			cur_date = formatter.parse(date);
		} catch (java.text.ParseException e){	}
		return captureDAO.getCancelRO(id,ro_no,type_veh,username,cur_date,remarks);
	}
	
	
	
	@RequestMapping(value = "/getRODetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getRODetails(HttpServletRequest request,HttpSession session,int id){
		String username = session.getAttribute("username").toString();
		return captureDAO.getRODetails(id);
	}
	

	@RequestMapping(value = "/admin/ApprovedRelOrderUrl", method = RequestMethod.POST)
	public @ResponseBody String ApprovedRelOrderUrl(@ModelAttribute("appid") String appids,
			@ModelAttribute("valid_upto") String valid_upto,ModelMap model, HttpSession session11,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			Authentication authentication) {
		String roleType = session11.getAttribute("roleType").toString();
		if (!roleType.equals("APP") && !roleType.equals("ALL")) {
			return "You are not Authorised to Approve RELEASE ORDER.";
		}
		
		TB_TMS_RIO_VEHICLE_DTLS riovehdtl = new TB_TMS_RIO_VEHICLE_DTLS();
		String returnMSG = "";
		String[] app_id = appids.split(",");		
		for (int i = 0; i < app_id.length; i++) {
			int appid = Integer.parseInt(app_id[i]);
			TB_TMS_RO_VEHICLE_DTLS listRio = captureDAO.getRIOVehDtls(appid);
		
			if (listRio.getId() > 0) {
				String type_of_veh = listRio.getType_of_vehicle();
				String ro_no=listRio.getRo_no();
				Session ses1 = HibernateUtil.getSessionFactory().openSession();
				Transaction t2 = ses1.beginTransaction();		
				Query q = ses1.createQuery(
						"select count(*) from TB_TMS_RIO_VEHICLE_DTLS where ro_no=:ro_no");
				q.setString("ro_no",ro_no);
				
				int ro = ((Number)q.uniqueResult()).intValue();
				if (ro == 0) {
					String approvedMsg = (String) captureDAO.setApprovedItem(appid, listRio.getRo_no(),session11.getAttribute("username").toString(),listRio.getType_of_vehicle());
					model.put("msg", approvedMsg);

					if (approvedMsg.equals("Release Order Approved Successfully.")) {

						riovehdtl.setAccounting(listRio.getAccounting());
						riovehdtl.setClass_vehicle(listRio.getClass_vehicle());
						riovehdtl.setCommand_sus_no(listRio.getCommand_sus_no());
						riovehdtl.setDepot_sus_no(listRio.getDepot_sus_no());
						String inliuemct = listRio.getInliuemct();
						if (inliuemct.equals("")) {
							riovehdtl.setInliuemct("0");
						} else {
							riovehdtl.setInliuemct(listRio.getInliuemct());
						}
						riovehdtl.setType_of_vehicle(listRio.getType_of_vehicle());
						riovehdtl.setIssue_precedence(listRio.getIssue_precedence());
						riovehdtl.setIssue_stock(listRio.getIssue_stock());
						riovehdtl.setLoan_auth(listRio.getLoan_auth());
						riovehdtl.setLoan_duration(listRio.getLoan_duration());
						riovehdtl.setMct(listRio.getMct());
						// riovehdtl.setNrs(listRio.getNrs());
						riovehdtl.setOther_issue_type(listRio.getOther_issue_type());
						riovehdtl.setQuantity(listRio.getQuantity());
						riovehdtl.setRemarks(listRio.getRemarks());
						riovehdtl.setRio_no("00");
						riovehdtl.setVersion_no(listRio.getVersion_no());
						riovehdtl.setType_of_release(listRio.getType_of_release());
						riovehdtl.setType_of_ro(listRio.getType_of_ro());
						riovehdtl.setType_of_issue(listRio.getType_of_issue());
						riovehdtl.setCorps_sus_no(listRio.getCorps_sus_no());
						riovehdtl.setSus_no(listRio.getSus_no());
						riovehdtl.setStatus("0");
						riovehdtl.setRo_no(listRio.getRo_no());
						riovehdtl.setIssue_date(listRio.getDate_of_submission());
						riovehdtl.setCreation_date(new Date());
						riovehdtl.setQuantity_status("Total Quantity");					
					
						Session session1 = HibernateUtil.getSessionFactory().openSession();
						session1.beginTransaction();
						int row = (Integer) session1.save(riovehdtl);
						session1.getTransaction().commit();
						session1.close();
						if (row > 0) {
							returnMSG = "Release Order Approved Successfully.";
						}
					} else {
						returnMSG = "Release Order Not Approved.";
					}
				} else if (ro>0){
					returnMSG = "Release Order Already Approved..";
				}else {
					returnMSG = "Invalid Input.";
				}
				
				}			
		}	
		return returnMSG;
	}




	@RequestMapping(value = "/admin/rejectRelOrderUrl", method = RequestMethod.POST)
	public @ResponseBody String rejectRelOrderUrl(@ModelAttribute("rejectid") int rejectid, ModelMap model,
			HttpSession sessionA, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") && !roleType.equals("ALL")) {
			return "You are not Authorised to Reject RELEASE ORDER.";
		}
		String msg1 = (String) captureDAO.setRejectItem(rejectid);
		model.put("msg", msg1);
		return msg1;
	}

	@RequestMapping(value = "/admin/deleteRelOrderUrl", method = RequestMethod.POST)
	public @ResponseBody String deleteRelOrderUrl(@ModelAttribute("deleteid") int deleteid, ModelMap model,
			HttpSession sessionA, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return "You are not Authorised to Delete RELEASE ORDER.";
		}
		String msg1 = (String) captureDAO.setDeleteItem(deleteid);
		model.put("msg", msg1);
		return msg1;
	}

	@RequestMapping(value = "/generateRONo")
	public @ResponseBody List<String> generateRONo(String roNo, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		ArrayList<String> list1 = new ArrayList<String>();
		String maxRo1 = "";
		String RoNo1 = "";
		Session sessionMax1 = HibernateUtil.getSessionFactory().openSession();
		Transaction txMax1 = sessionMax1.beginTransaction();
		Query qMax1 = sessionMax1
				.createQuery("SELECT  max(SUBSTRING(ro_no,8,5)) FROM TB_TMS_RO_MAIN where ro_no like :roNo ");
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
	

/*   NITIN V4 16/11/2022  */
	 @RequestMapping(value = "/Excel_data_ro", method = RequestMethod.POST)
		public ModelAndView Excel_data_rio(HttpServletRequest request,
				ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("status_ex") String status,
				@ModelAttribute("sus_no_ex") String sus_no,
				@ModelAttribute("unit_name_ex") String unit_name,
				@ModelAttribute("depot_sus_no_ex") String depot_sus_no,
				@ModelAttribute("depot_name_ex") String depot_name,
				@ModelAttribute("issue_date_ex") String issue_date,
				@ModelAttribute("to_date_ex") String to_date,
				@ModelAttribute("type_of_issue_ex") String type_of_issue,
				@ModelAttribute("type_of_ro_ex") String type_of_ro,
				@RequestParam(value = "msg", required = false) String msg) {

		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_capture_rel_order", roleid);
			String roleType = session.getAttribute("roleType").toString();
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
		  ArrayList<ArrayList<String>> Excel =captureDAO.SearchgetAttributeFromROMainAndVehDtl_excel(status, issue_date,to_date, type_of_issue, type_of_ro, sus_no, depot_sus_no, session);
		  
		 List<String> TH = new ArrayList<String>();
			
			
			TH.add("RO NO");
			TH.add("Unit name");
			TH.add("Command  Name");
			TH.add("Depot  Name");
			TH.add("Nomenclature");
			TH.add("Qty");
			TH.add("Type of Issue");
			TH.add("Date of Submission");
			TH.add("In Lieu Nomenclature");
			
			String Heading = "\nData Updated Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}

	 //Added by Mitesh(30/09/24)
	 
	 @RequestMapping(value = "/admin/bveh_release_order_updateURL", method = RequestMethod.POST)
		public ModelAndView ro_updateURL(@ModelAttribute("relid") int relid, ModelMap model,
				HttpSession session1, String type_of_veh, @RequestParam(value = "msg", required = false) String msg,
				Authentication authentication) {

			String roleid = session1.getAttribute("roleid").toString();
			/*Boolean val = roledao.ScreenRedirect("capture_rel_order_.search", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}*/

			Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_TMS_RO_VEHICLE_DTLS  where id=:id");
			q.setParameter("id", relid);
			TB_TMS_RO_VEHICLE_DTLS  upid = (TB_TMS_RO_VEHICLE_DTLS ) q.list().get(0);
			tx.commit();

			model.put("bveh_release_order_editCMD", upid);
			model.put("msg", msg);
			return new ModelAndView("bveh_release_oder_EditTiles");
		}
	 
	 
	 //added by Mitesh(26-11-2024)
	 @RequestMapping(value = "/getMctNoDetailListbyclasscode", method = RequestMethod.POST)
		public @ResponseBody List<String> getMctNoDetailListbyclasscode(String mct, String type_of_vehicle,String veh_class_code,HttpSession sessionUserId) {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if (type_of_vehicle == null || type_of_vehicle.equals("0") || type_of_vehicle == "0") {
				q = session.createQuery(
						"select distinct mct from TB_TMS_MCT_MASTER where  cast(mct as string) like :mct and veh_class_code=:veh_class_code and status='ACTIVE'")
						.setMaxResults(10);
				
			} else {
				q = session.createQuery(
						"select distinct mct from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle and cast(mct as string) like :mct and veh_class_code=:veh_class_code  and status='ACTIVE' ")
						.setMaxResults(10);
				q.setParameter("type_of_vehicle", type_of_vehicle);
			
			}
			q.setParameter("mct", mct + "%");
			q.setParameter("veh_class_code", veh_class_code);
			@SuppressWarnings("unchecked")
			List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
			tx.commit();
			session.close();

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
					String mct_number = String.valueOf(list.get(i));
					encCode = c.doFinal(mct_number.getBytes());
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
