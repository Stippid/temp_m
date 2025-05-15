package com.controller.HelpDeskController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
import com.dao.helpDesk.HelpDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Helpdesk.HD_TB_BISAG_LOGIN_MARQUEE;
import com.models.login.TB_MISO_WHATS_NEW;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@SuppressWarnings("unused")

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class marqueeController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private HelpDAO helpDao;
	ValidationController validation = new ValidationController();
	
	@Autowired
	private HelpDAO helpdao;

	@RequestMapping(value = "/marquee", method = RequestMethod.GET)
	public ModelAndView marquee(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("marquee", roleid);
		if (val == false){
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("list", helpDao.getSearchMercuryList1("", ""));
		Mmap.put("msg", msg);
		return new ModelAndView("marqueeTiles");
	}

	@RequestMapping(value = "/marcuriesAction", method = RequestMethod.POST)
	public ModelAndView marcuriesAction(@ModelAttribute("marcuriescmd") HD_TB_BISAG_LOGIN_MARQUEE user,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String username = session.getAttribute("username").toString();
		String msg1 = request.getParameter("test_msg");
		String msg2 = request.getParameter("msg_old_name");
		user.setMsg(msg1);

		String valid_upto1 = request.getParameter("valid_upto");
		Date valid_upto = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (valid_upto1.equals("") && valid_upto1.equals(null)) {
				model.put("msg", "Please Enter Date.");
				return new ModelAndView("redirect:marcuriesurl");
			} else {
				valid_upto = formatter.parse(request.getParameter("valid_upto"));
			}

		} catch (ParseException e) {
		
		}
		Boolean val = true;
		if (msg2 == "") {
			if (user.getMsg().equals("")) {
				model.put("msg", "Please Insert marquee text");
				return new ModelAndView("redirect:marquee");
			} else if (validation.checkmsgMarqueeLength(user.getMsg()) == false) {
				model.put("msg", validation.msgMarqueeMSG);
				return new ModelAndView("redirect:marquee");
			}
			if (user.getValid_upto() == null) {
				model.put("msg", "Please select date");
				return new ModelAndView("redirect:marquee");
			}
			val = helpDao.getmsgExist(msg1, valid_upto);
			user.setValid_upto(valid_upto);
			user.setStatus("1");
			user.setCreated_by(username);
			user.setCreated_on(new Date());
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			int did = (Integer) sessionHQL.save(user);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			if (did > 0) {
				model.put("msg", "Data saved Successfully");
			}
		} else {
			int id = Integer.parseInt(request.getParameter("id_hid"));
			Session session1 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			String hql = "UPDATE HD_TB_BISAG_LOGIN_MARQUEE SET msg=:msg1, valid_upto=:valid_upto WHERE id=:id";
			Query query = session1.createQuery(hql).setString("msg1", msg1).setDate("valid_upto", valid_upto)
					.setInteger("id", id);
			int rowCount = query.executeUpdate();
			tx.commit();
			session1.close();
			if (rowCount > 0) {
				model.put("msg", "Updated Successfully");
			} else {
				model.put("msg", "Updated Not Successfully");
			}
		}
		List<Map<String, Object>> list = helpDao.getSearchMercuryList1("", "");
		model.put("list", list);
		model.put("list.size", list.size());
		return new ModelAndView("redirect:marquee");
	}
	
	// Start Whats New
	@RequestMapping(value = "/whats_new_url", method = RequestMethod.GET)
	public ModelAndView whats_new_url(ModelMap model, @RequestParam(value = "msg", required = false) String msg,HttpSession session) {
		String userId = session.getAttribute("userId").toString();
		model.put("msg", msg);
		return new ModelAndView("whats_new_urlTile");
	}
	DateWithTimeStampController timestamp = new DateWithTimeStampController();
	
	@RequestMapping(value = "/admin/whatsNewAction", method = RequestMethod.POST)
	public @ResponseBody List<String> whatsNewAction(@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,HttpServletRequest request,ModelMap model, HttpSession session) {
	
		String userId = session.getAttribute("userId").toString();
		List<String> list = new ArrayList<>();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			
			String heading = request.getParameter("heading");
			String description = request.getParameter("description");
			if(heading.equals("")) {
				list.add("Please enter Heading");
				return list;
			}if(description.equals("")) {
				list.add("Please enter Description");
				return list;
			}else {
				String username = session.getAttribute("username").toString();
				TB_MISO_WHATS_NEW w = new TB_MISO_WHATS_NEW();
				int userid = Integer.parseInt(session.getAttribute("userId").toString());
				String fname = "";
			
				if (!file_upload.isEmpty()) {
				
					String extension = "";
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = file_upload.getBytes();
					String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
					File dir = new File(helpdeskFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = file_upload.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() +"_"+ userId + "_WHATSNEW." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					w.setFile_upload(fname);
				}
					
				
				w.setHeading(heading.trim());
				w.setDescription(description.trim());
				w.setCreated_dt(new Date());
				w.setCreated_by(username);
				session1.save(w);
				tx.commit();
				list.add("Data Saved Successfully.");
			}
		} catch (Exception e) {
			tx.rollback();
			list.add("an Error ocurre data saving. " + e.getStackTrace());
		}finally {
			session1.close();
		}
		return list;
	}
	
	@RequestMapping(value = "/getWhatsNewList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getWhatsNewList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId){
		return helpdao.getWhatsNewList(startPage, pageLength, Search, orderColunm, orderType, sessionUserId);
	}

	@RequestMapping(value = "/getWhatsNewCount", method = RequestMethod.POST)
	public @ResponseBody long getWhatsNewCount(HttpSession sessionUserId, String Search, String name) {
		return helpdao.getWhatsNewCount(Search);
	}
	
	@RequestMapping(value = "/getDownloadWhatsNew", method = RequestMethod.POST)
	public void getDownloadPolicyURllogin(@ModelAttribute("whats_new_id") int id,ModelMap model ,HttpServletRequest request,HttpServletResponse response){
		String EXTERNAL_FILE_PATH = "";
		if(id != 0) {
			TB_MISO_WHATS_NEW m = getTB_MISO_WHATS_NEW(id);
			if(m.getFile_upload() != null) {
				EXTERNAL_FILE_PATH = m.getFile_upload();
			}
		}
		File file = null;
	    file = new File(EXTERNAL_FILE_PATH);
	    try{
	    	if(!file.exists()){
	    		String fullPath =  request.getRealPath("/")+"admin\\js\\img\\No_doc.pdf";
	    		file = new File(fullPath);
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
		} catch (IOException e) {
		
		}
	}
	@SuppressWarnings("unchecked")
	public TB_MISO_WHATS_NEW getTB_MISO_WHATS_NEW(int id){
		String qry = " from TB_MISO_WHATS_NEW where id=:id ";
		List<TB_MISO_WHATS_NEW> p = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(qry);
			q.setParameter("id",id);
			p = (List<TB_MISO_WHATS_NEW>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (p.size() > 0) {
			return p.get(0);
		}else {
			return null;
		}
	}
	//end Whats new
}