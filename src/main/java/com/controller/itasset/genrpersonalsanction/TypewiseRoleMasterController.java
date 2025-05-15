package com.controller.itasset.genrpersonalsanction;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.itasset.WorkOrder.TypewiseRoleMaster_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.TB_IT_ASSET_TYPEWISE_ROLE_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TypewiseRoleMasterController {

	@Autowired
	DataSource dataSource;

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	TypewiseRoleMaster_DAO idref;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/admin/typewiseRoleMaster", method = RequestMethod.GET)
	public ModelAndView typewiseRoleMaster(ModelMap Mmap, HttpSession sessionUserId, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("typewiseRoleMaster", roleid);
		Boolean show = false;
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> list = idref.getUnitType();
		Mmap.put("list", list);
		return new ModelAndView("Type_RoleMaster_Tiles");
	}
	


	@RequestMapping(value = "/getRole_name", method = RequestMethod.POST)
	public @ResponseBody List<String> getRole_name(HttpSession sessionUserId, String Role_name)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select r.role as rolename  from Role r where UPPER(r.role) like :Role_name")
				.setMaxResults(10);
		q.setParameter("Role_name", Role_name.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list1.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		tx.commit();
		session.close();
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	  @RequestMapping(value = "/Type_RoleMaster_Action", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain;charset=UTF-8")
	    @ResponseBody
	    public String saveTypeRoleData(@RequestBody List<Map<String, String>> data, HttpServletRequest request) {
	        try {
	            String createdBy = (String) request.getSession().getAttribute("username");

	            if (createdBy == null) {
	                return "Error: User not authenticated. Please login.";
	            }

	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            Date createdDate = new Date();

	            List<TB_IT_ASSET_TYPEWISE_ROLE_MASTER> roleMasterList = new ArrayList<>(); 

	          
	            for (Map<String, String> row : data) {
	                TB_IT_ASSET_TYPEWISE_ROLE_MASTER roleMaster = new TB_IT_ASSET_TYPEWISE_ROLE_MASTER();
	                roleMaster.setUnit_type(row.get("unitType"));
	                roleMaster.setRole_name(row.get("roleName"));
	                roleMaster.setApplyhierarchy(row.get("applyHierarchy"));
	                roleMaster.setLevel_of_hierarchy(row.get("levelOfHierarchy"));
	                roleMaster.setCreated_by(createdBy);
	                roleMaster.setCreated_date(createdDate);
	                roleMaster.setModified_by(createdBy);
	                roleMaster.setModified_date(createdDate);
	                roleMaster.setStatus("1");

	                roleMasterList.add(roleMaster); 
	            }

	            if (!roleMasterList.isEmpty()) {
	                boolean save = idref.typeWiseMasterDataSave(roleMasterList);

	                if (save) {
	                    return "Data saved successfully";
	                } else {
	                    return "Error saving data to the database."; 
	                }
	            } else {
	                return "Data Not saved successfully"; 
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error saving data: " + e.getMessage();
	        }
	    }
	
	
}
