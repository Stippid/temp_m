package com.controller.Computing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.Assets.computing_assets_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.Audit_Upload;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AuditUploadController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	computing_assets_DAO ForVis;

	ValidationController validation = new ValidationController();

	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@RequestMapping(value = "/AuditUploadUrl", method = RequestMethod.GET)
	public ModelAndView AuditUploadUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("AuditUploadUrl", session.getAttribute("roleid").toString());
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.addAttribute("roleSusTemp", roleSusNo);

		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleAccess", roleAccess);
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		return new ModelAndView("auditUploadTiles");
	}


	@RequestMapping(value = "/AuditUploadAction", method = RequestMethod.POST)
	public ModelAndView AuditUploadAction(@RequestParam(value = "u_file", required = false) MultipartFile u_file,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String extension = "";
		String fname = "";
		Date date = new Date();
		Date au_dt = null;

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		String type_of_audit = request.getParameter("type_of_audit");
		String audit_done_by = request.getParameter("audit_done_by");
		String audit_date = request.getParameter("audit_date");
		String sus_no = request.getParameter("unit_sus_no");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String roleAccess = session.getAttribute("roleAccess").toString();
		if (!roleAccess.equals("Unit")) {

			if(sus_no == null || sus_no.equals("null") || sus_no.equals("")) {
				model.put("msg", "Please Enter Unit Sus No.");
				return new ModelAndView("redirect:AuditUploadUrl");
			}
			if (!validation.isOnlyAlphabetNumeric(sus_no)) {
				model.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
				return new ModelAndView("redirect:AuditUploadUrl");
			}
			if (!validation.SusNoLength(sus_no)) {
				model.put("msg", validation.SusNoMSG);
				return new ModelAndView("redirect:AuditUploadUrl");
			}
			else {
				roleSusNo = sus_no;
			}


		}

		Audit_Upload AU_asset = new Audit_Upload();
		try {
			if (!u_file.isEmpty()) {
				try {
					byte[] bytes = u_file.getBytes();
					String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();

					File dir = new File(mnhFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = u_file.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+roleSusNo+"_Audit_Doc."+extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					AU_asset.setU_file(fname.replace("\\","/"));
				} catch (Exception e) {
				}
			}

			if(audit_date!=null  && !audit_date.equals("") && !audit_date.equals("DD/MM/YYYY")) {
				au_dt = format.parse(audit_date);

			}

			AU_asset.setSus_no(roleSusNo);
			AU_asset.setCreated_by(username);
			AU_asset.setCreated_date(date);
			AU_asset.setType_of_audit(type_of_audit);
			AU_asset.setAudit_done_by(audit_done_by);
			AU_asset.setAudit_date(au_dt);
			sessionHQL.save(AU_asset);
			sessionHQL.flush();
			sessionHQL.clear();
			model.put("msg", "Data Saved Successfully.");
			tx.commit();

		}catch(RuntimeException e){
			throw e;
		}finally{
			if(sessionHQL!=null){
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:AuditUploadUrl");
	}


	@RequestMapping(value = "/AuditUploadCount", method = RequestMethod.POST)
	public @ResponseBody long AuditUploadCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId) throws SQLException {
		return ForVis.getAuditUploadCount(Search, orderColunm, orderType, sessionUserId);
	}


	@RequestMapping(value = "/AuditUploadData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> AuditUploadData(int startPage,String Search,int pageLength,String orderColunm,
			String orderType,HttpSession sessionUserId) throws SQLException {
		return ForVis.getAuditUploadData(startPage, pageLength, Search, orderColunm, orderType,sessionUserId);
	}


	@SuppressWarnings("null")
	@RequestMapping(value = "/AuditDownload" , method = RequestMethod.GET)
	public void AuditDownload(@ModelAttribute("id") int id,ModelMap model, HttpServletRequest request,HttpServletResponse response,
			HttpSession sessionA, Authentication authentication) throws IOException, SQLException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Audit_Upload u_file_path=(Audit_Upload)sessionHQL.get(Audit_Upload.class,id);
		String filePath = u_file_path.getU_file();
		final int BUFFER_SIZE = 4096;

		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {

				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			@SuppressWarnings("deprecation")
			String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}
	}

}
