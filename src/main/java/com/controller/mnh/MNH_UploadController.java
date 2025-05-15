package com.controller.mnh;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.models.mnh.File_Upload_Model;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class MNH_UploadController {
	
	@Autowired
	private MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	//UPLOAD MODULE (1)-> (AROGYA DATA UPLOAD SCREEN START)
	/*@RequestMapping(value = "/admin/mnh_upload_arogyaData", method = RequestMethod.GET)
	public ModelAndView mnh_upload_arogyaData(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_upload_arogyaData", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("a_2", l1.get(0).get(2));
		Mmap.put("a_3", l1.get(0).get(3));
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_upload_u1Tiles");
	}*/
	//UPLOAD MODULE (1)-> (AROGYA DATA UPLOAD SCREEN END)
	
	//UPLOAD MODULE (2)-> (EXCEL DATA UPLOAD SCREEN START)
	/*@RequestMapping(value = "/admin/mnh_upload_ExcelData", method = RequestMethod.GET)
	public ModelAndView mnh_upload_ExcelData(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mnh_upload_ExcelData", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_upload_excelTiles");
	}*/
	//UPLOAD MODULE (2)-> (EXCEL DATA UPLOAD SCREEN END)
	
	//UPLOAD MODULE (3)-> (FILE DATA UPLOAD SCREEN START)
	@RequestMapping(value = "/admin/upload_other_format_Data", method = RequestMethod.GET)
	public ModelAndView upload_other_format_Data(ModelMap Mmap,HttpSession session, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		int userid = (Integer)session.getAttribute("userId");
		Boolean val = roledao.ScreenRedirect("upload_other_format_Data", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		ArrayList<ArrayList<String>> l1 = mnh1_Dao.getMNHRData(userid);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_upload_u3Tiles");
	}
	//UPLOAD MODULE (3)-> (FILE DATA UPLOAD SCREEN START)

	@RequestMapping(value = "/File_Data_UploadAction", method = RequestMethod.POST)
	public ModelAndView File_Data_UploadAction(@ModelAttribute("File_Data_UploadCmd") File_Upload_Model rs,
			BindingResult bindingResult, HttpServletRequest request, ModelMap model, HttpSession sessionA,
			@RequestParam(value = "doc1", required = false) MultipartFile doc1,@RequestParam(value = "msg", required = false) String msg) {
		Boolean val = roledao.ScreenRedirect("upload_other_format_Data", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		 if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String username = (String) sessionA.getAttribute("username");
		String sus_no1 = request.getParameter("sus_no");
		String extension = "";
		String fname = "";
		if (!doc1.isEmpty()) {
			try {
				byte[] bytes = doc1.getBytes();
				String mnhFilePath = sessionA.getAttribute("mnhFilePath").toString();
				File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = doc1.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+sus_no1+"_MNH_Doc."+extension;
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				rs.setUpload_file(fname);
			} catch (Exception e) {
			}
		}
		if (sus_no1.equals("")) {
			model.put("msg", "Please Enter the SUS No");
			return new ModelAndView("redirect:upload_other_format_Data");
		}
		rs.setCreated_on(new Date());
		rs.setCreated_by(username);
		rs.setSus_no(sus_no1);
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		sessionHQL.beginTransaction();
		sessionHQL.save(rs);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		model.put("msg", "Data saved Successfully");
		return new ModelAndView("redirect:upload_other_format_Data");
	}
}