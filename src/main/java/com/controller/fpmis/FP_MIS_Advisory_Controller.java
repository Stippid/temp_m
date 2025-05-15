package com.controller.fpmis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.dao.fpmis.FP_MIS_AdvisoryDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.UploadDocumentMVCR;
import com.models.fpmis.fp_tb_advisory_model;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class FP_MIS_Advisory_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private FP_MIS_AdvisoryDAO fpaddao;

	@Autowired   
	private FP_MIS_TransactionDAO fp_trDao;
	
	@Autowired
	private FP_MIS_Common_Controller mcommon;

	@RequestMapping(value = "/admin/fp_advisory_upd", method = RequestMethod.GET)
	public ModelAndView fp_advisory_upd(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		String rolesus = session.getAttribute("roleSusNo").toString();
		String rolefmn = session.getAttribute("roleFormationNo").toString();

		Boolean val = roledao.ScreenRedirect("fp_advisory_upd", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		String dateString = formatter.format(currentDate);

		String nUnty = null;
		nUnty = fp_trDao.getUnitType("", session, "SUS_" + rolesus);

		Mmap.put("n_4", nUnty);

		ArrayList<ArrayList<String>> list = fpaddao.getSearchAdvisoryData("", "", "", "");
		Mmap.put("list", list);
		Mmap.put("today", dateString);

		return new ModelAndView("fp_advisory_updTiles");
	}

	@RequestMapping(value = "/admin/Advisory_UploadAction", method = RequestMethod.POST)
	public @ResponseBody List<String> Advisory_UploadAction(
			@RequestParam(value = "adv_file", required = false) MultipartFile adv_file, HttpServletRequest request,
			ModelMap model, HttpSession session, HttpServletResponse response) {

		String roleid = session.getAttribute("roleid").toString();
		List<String> list = new ArrayList<>();
		Boolean val = roledao.ScreenRedirect("fp_advisory_upd", roleid);
		if(val == false) {
			list.add("Permission denied to upload advisory");
			return list;
		}
		final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());
		if (adv_file.getSize() > fileSizeLimit) {
			list.add("File size should be less then 2 MB");
			return list;
		}
		String fileExt = FilenameUtils.getExtension(adv_file.getOriginalFilename());
		if (!fileExt.equals("pdf")) {
			list.add("Only PDF files are permitted.");
			return list;
		}
		Date curr_dt = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date currentDate = new Date();
		String dateString = formatter.format(currentDate);
		
		String file_name = adv_file.getOriginalFilename();
		
		String fname = "";
		String advisory_in_details = request.getParameter("advisory_in_details");
		String adv_type = request.getParameter("adv_type");
		
		String data1 = advisory_in_details+"_"+adv_type;		
		String data2 = "_";
		String retmsg=mcommon.nChkData(data1,data2);
		
		if(retmsg.length()>0) {
			list.add(retmsg);
			return list;
		} else {
				
		if (!adv_file.isEmpty()) {
			try {
				curr_dt = formatter.parse(dateString);
				String upload_from_date = request.getParameter("frm_dt");
				String upload_to_date = request.getParameter("to_dt");
				Date date_from = formatter.parse(upload_from_date);
				Date date_to = formatter.parse(upload_to_date);
				if (date_from.before(curr_dt)) {
					list.add("From Date should not be less than today");
					return list;
				} else if (date_to.before(curr_dt)) {
					list.add("To Date should not be less than today");
					return list;
				} else if (date_from.after(date_to)) {
					list.add("To Date should be greater than From Date");
					return list;
				}
				
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String username = session.getAttribute("username").toString();
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Query q0 = sessionHQL
						.createQuery("select count(id) from fp_tb_advisory_model where file_name=:file_name");
				q0.setParameter("file_name", file_name);
				Long c = (Long) q0.uniqueResult();
				sessionHQL.close();

				if (c == 0) {
					byte[] bytes = adv_file.getBytes();
					CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
					if (fileValidation.check_PDF_File(bytes)) {
						String FilePath = "/srv" + File.separator + "FP" + File.separator;
						File dir = new File(FilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}

						fname = dir.getAbsolutePath() + File.separator + adv_file.getOriginalFilename();

						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						if (!fpaddao.UploadDataSave(roleSusNo, advisory_in_details, file_name, adv_type, date_from,
								date_to, username)) {
							list.add("Error - unable to upload advisory");
							serverFile.delete();
							return list;
						}

						list.add("Advisory File Uploaded Successfully");
						return list;
					} else {
						list.add("Only PDF files are permitted.");
						return list;
					}
				} else {
					list.add("File being upload is already uploaded with same name\nPlease Check file and try again.");
					return list;
				}

			} catch (Exception e) {
				list.add("Error - unable to upload advisory");
				return list;
			}
		}
	}
		return list;
	}

	@RequestMapping(value = "/SearchAdvisory", method = RequestMethod.POST)
	public ModelAndView SearchAdvisory(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "advisory_in_details1", required = false) String advisory_in_details,
			@RequestParam(value = "frm_dt1", required = false) String frm_dt,
			@RequestParam(value = "to_dt1", required = false) String to_dt,
			@RequestParam(value = "adv_type1", required = false) String adv_type,HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("fp_advisory_upd", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> list = fpaddao.getSearchAdvisoryData(advisory_in_details, frm_dt, to_dt, adv_type);
		Mmap.put("list", list);
		return new ModelAndView("fp_advisory_updTiles");
	}


	@RequestMapping(value = "/admin/DownloadAdvisory", method = RequestMethod.POST)
	public ModelAndView DownloadAdvisory(@ModelAttribute("filename") String filename, HttpSession sess, ModelMap model,
			HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws Exception {
		try {

			Boolean val = roledao.ScreenRedirect("fp_advisory_upd", sess.getAttribute("roleid").toString());
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				////return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			String file_name = fpaddao.getAdvisoryFileName(Integer.parseInt(request.getParameter("file_id")));

			if (file_name.length() > 0) {
				String FILE_PATH = "/srv" + File.separator + "FP" + File.separator + file_name;
				File file = null;
				file = new File(FILE_PATH);
				if (!file.exists()) {
					throw new FileNotFoundException("The file your are looking for doesn't exist");
				} else {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setContentLength((int) file.length());
					response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
					response.setHeader("Expires:", "0");
					response.setStatus(200);
					InputStream inputStream = null;
					inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				}
			} else {
				throw new FileNotFoundException("The file your are looking for doesn't exist");
			}
		} catch (Exception ex) {
			model.put("msg", "The file your are looking for doesn't exist");
		}
		return new ModelAndView("redirect:fp_advisory_upd");
	}

	@RequestMapping(value = "/admin/fp_msg_hld", method = RequestMethod.GET)
	public ModelAndView fp_msg_hld(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("fp_msg_hld", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		ArrayList<ArrayList<String>> list = fpaddao.getSearchMsgData();
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("fp_msg_hldTiles");
	}

	@RequestMapping(value = "/msg_save", method = RequestMethod.POST)
	public ModelAndView msg_save(ModelMap Mmap, HttpSession session, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String rolsus = (String) session.getAttribute("roleSusNo");
		Boolean val = roledao.ScreenRedirect("fp_msg_hld", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String msg1 = request.getParameter("msg1");
		String msgty = request.getParameter("msg_type");
		String dtfr = request.getParameter("date_from");
		String dtto = request.getParameter("date_to");
		String msgto = request.getParameter("msg_to");
		String tt = "";
		if(msg1.length() == 0 || msg1.equalsIgnoreCase(null)) {
			msg1 = "Please Enter the Message";
			Mmap.put("msg", msg1);
			return new ModelAndView("redirect:fp_msg_hld");
		}
		
		String data1 = msg1+"_"+msgty+"_"+dtfr+"_"+ dtto+"_"+msgto;		
		String data2 = "_";
		String retmsg=mcommon.nChkData(data1,data2);
		
		if(retmsg.length()>0) {
			Mmap.put("msg", retmsg);
			return new ModelAndView("redirect:fp_msg_hld");
		} else {
			tt = fpaddao.msg_save(rolsus, msg1, msgto, dtfr, dtto, msgty);	
		}
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt_fr = formatter.parse(dtfr);
		Date dt_to = formatter.parse(dtto);

		
		if (tt.equalsIgnoreCase("1")) {
			msg1 = "Message Executed Successfully.";
		} else {
			msg1 = "Message Not Executed.";
		}
		Mmap.put("msg", msg1);
		return new ModelAndView("redirect:fp_msg_hld");
	}

}