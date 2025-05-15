package com.controller.mms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_UploadExcelDAO;
import com.dao.mms.mms_upload_validateDAO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_UploadExcelController {

	@Autowired
	public MMS_UploadExcelDAO m8DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController valid = new ValidationController();

	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();

	// DIR, DRR, MSR MODULE (1)-> (UPLOAD DIR SCREEN START)
	@RequestMapping(value = "/mms_upload_DIR", method = RequestMethod.GET)
	public ModelAndView exportExcelDIRData(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_upload_DIR", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_upload_DIRTiles");
	}
	// DIR, DRR, MSR MODULE (1)-> (UPLOAD DIR SCREEN END)

	// DIR, DRR, MSR MODULE (2)-> (UPLOAD DRR SCREEN START)
	@RequestMapping(value = "/mms_upload_DRR", method = RequestMethod.GET)
	public ModelAndView exportExcelDRRData(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_upload_DRR", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_upload_DRRTiles");
	}
	// DIR, DRR, MSR MODULE (2)-> (UPLOAD DRR SCREEN END)

	// DIR, DRR, MSR MODULE (3)-> (UPLOAD MSR SCREEN START)
	@RequestMapping(value = "/mms_upload_MSR", method = RequestMethod.GET)
	public ModelAndView exportExcelMSRData(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_upload_MSR", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg", msg);
		model.put("ml_1", m1.getDomainListingData("MSRFORMAT"));
		return new ModelAndView("mms_upload_MSRTiles");
	}
	// DIR, DRR, MSR MODULE (3)-> (UPLOAD MSR SCREEN END)

	// DIR, DRR, MSR MODULE (4)-> (VALIDATE DIR SCREEN START)
	@RequestMapping(value = "/mms_dir_upload_validate", method = RequestMethod.GET)
	public ModelAndView mms_dir_upload_validate(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_dir_upload_validate", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_dir_upload_validateTiles");
	}
	// DIR, DRR, MSR MODULE (4)-> (VALIDATE DIR SCREEN END)

	// DIR, DRR, MSR MODULE (5)-> (VALIDATE DRR SCREEN START)
	@RequestMapping(value = "/mms_drr_upload_validate", method = RequestMethod.GET)
	public ModelAndView mms_drr_upload_validate(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_drr_upload_validate", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_drr_upload_validateTiles");
	}
	// DIR, DRR, MSR MODULE (5)-> (VALIDATE DRR SCREEN END)

	// DIR, DRR, MSR MODULE (6)-> (VALIDATE MSR SCREEN START)
	@RequestMapping(value = "/mms_msr_upload_validate", method = RequestMethod.GET)
	public ModelAndView mms_msr_upload_validate(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_msr_upload_validate", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_msr_upload_validateTiles");
	}
	// DIR, DRR, MSR MODULE (6)-> (VALIDATE MSR SCREEN END)

	// Start Download Formate of DRR,DIR and MSR Excel File
	@RequestMapping(value = "/admin/getFormate_MMS_Upload_doc", method = RequestMethod.POST)
	public void getFormate_MMS_Upload_doc(@ModelAttribute("filename") String filename, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = null;
		if (filename.equals("mms_tb_imp_dir")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator+ "DIR_FORMAT.xlsx");
		}else if (filename.equals("mms_tb_imp_drr")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator+ "DRR_FORMAT.xlsx");
		}else if (filename.equals("mms_tb_imp_msr")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator+ "MSR_FORMAT.xlsx");
		}
		
		try {
			if (!file.exists()) {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
				file = new File(fullPath);
			}
		} catch (Exception exception) {
			
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (FileNotFoundException e) {
			
		}
	}
/*	@RequestMapping(value = "/admin/getFormate_MMS_Upload_doc", method = RequestMethod.POST)
	public void getFormate_MMS_Upload_doc(@ModelAttribute("filename") String filename, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = null;

		if (filename.equals("mms_tb_imp_dir")) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\DIR_FORMAT.xlsx";
			file = new File(fullPath);
		}
		else if (filename.equals("mms_tb_imp_drr")) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\DRR_FORMAT.xlsx";
			file = new File(fullPath);
		}else if (filename.equals("mms_tb_imp_msr")) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\MSR_FORMAT.xlsx";
			file = new File(fullPath);
		}else {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_doc.pdf";
			file = new File(fullPath);
		}
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (FileNotFoundException e) {
			
		}
	}*/
	// End Download Formate of DRR,DIR and MSR Excel File
	
	// (1)-> (DIR, DRR, MSR SCREEN METHODS START)
	@RequestMapping(value = "/exportExcelAction", method = RequestMethod.POST)
	public ModelAndView exportExcelAction(
			@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		String table_name = request.getParameter("table_name");
		int countrow = Integer.parseInt(request.getParameter("countrow"));
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		SimpleDateFormat formatter_dash = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		SimpleDateFormat formatter_slash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		if (table_name.equals("mms_tb_imp_dir") && !table_name.equals("mms_tb_imp_drr") && !table_name.equals("mms_tb_imp_msr")) {
			for (int i = 1; i < countrow; i++) {
				
				String census_no = request.getParameter("census_no_" + i);
				String month = new SimpleDateFormat("MM-yyyy").format(new Date());
				if(m8DAO.checkDataExits(census_no,month,roleSusNo,table_name) > 0) {
				
				}else {
					String material_no = request.getParameter("material_no_" + i);
					String nomen = request.getParameter("nomen_" + i);
					String cat_part_no = request.getParameter("cat_part_no_" + i);
					String eqpt_part_no = request.getParameter("eqpt_part_no_" + i);
					String eqpt_serial_no = request.getParameter("eqpt_serial_no_" + i);
					String eqpt_batch_no = request.getParameter("eqpt_batch_no_" + i);
					
					String issue_unit_name = request.getParameter("unit_name_" + i);
					String issue_sus_no = request.getParameter("sus_no_" + i);
					String status = request.getParameter("status_" + i);
					
					String issued_qty_dumy =request.getParameter("issued_qty_" + i);
					int issued_qty = 0;
					if(!issued_qty_dumy.equals("")) {
						issued_qty = Integer.parseInt(issued_qty_dumy);
					}
					int unit_price = Integer.parseInt(request.getParameter("unit_price_" + i));
					String eqpt_make = request.getParameter("eqpt_make_" + i);
					String eqpt_model = request.getParameter("eqpt_model_" + i);
					String iv_no = request.getParameter("iv_no_" + i);
					String iv_date1 = request.getParameter("iv_date_" + i);
					
					Date iv_date = null;
					if(iv_date1.contains("/")) {
						iv_date = formatter_slash.parse(iv_date1);
					}else if(iv_date1.contains("-")) {
						iv_date = formatter_dash.parse(iv_date1);
					}else {
						iv_date = null;
					}
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					Query qry = session1.createSQLQuery("insert into mms_tb_imp_dir "
							+ "(issue_depot_name,rv_no,rv_date,material_no,census_no,nomen,issued_qty,"
							+ "eqpt_make,eqpt_model,eqpt_ser_no,eqpt_batch_no,eqpt_part_no,"
							+ "unit_price,issue_unit_name,issue_sus_no,data_cr_by,data_cr_date,op_status,upload_date,cat_part_no)"
							+ "values"
							+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20)");
	
					
					qry.setParameter("a1", roleSusNo);
					qry.setParameter("a2", iv_no);
					qry.setParameter("a3", iv_date);
					qry.setParameter("a4", material_no);
					qry.setParameter("a5", census_no);
					qry.setParameter("a6", nomen);
					qry.setParameter("a7", issued_qty);
					qry.setParameter("a8", eqpt_make);
					qry.setParameter("a9", eqpt_model);
					qry.setParameter("a10", eqpt_serial_no);
					qry.setParameter("a11", eqpt_batch_no);
					qry.setParameter("a12", eqpt_part_no);
					qry.setParameter("a13", unit_price);
					qry.setParameter("a14", issue_unit_name);
					qry.setParameter("a15", issue_sus_no);
					qry.setParameter("a16", username);
					qry.setParameter("a17", new Date());
					qry.setParameter("a18", status);
					qry.setParameter("a19", new Date());
					qry.setParameter("a20", cat_part_no);
	
					qry.executeUpdate();
					tx.commit();
					session1.close();
				}
			}

			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mms_upload_DIR");
		}
		else if (table_name.equals("mms_tb_imp_drr") && !table_name.equals("mms_tb_imp_dir") && !table_name.equals("mms_tb_imp_msr")) {
			for (int i = 1; i < countrow; i++) {
				String census_no = request.getParameter("census_no_" + i);
				String month = new SimpleDateFormat("MM-yyyy").format(new Date());
				if(m8DAO.checkDataExits(census_no,month,roleSusNo,table_name) > 0) {
				
				}else {
					String recv_sus_no = request.getParameter("recv_sus_no_" + i);
					String material_no = request.getParameter("material_no_" + i);
					String nomen = request.getParameter("nomen_" + i);
					String brief_desc = request.getParameter("brief_desc_" + i);
					String type_of_hldg = request.getParameter("type_of_hldg_" + i);
					String type_of_eqpt = request.getParameter("type_of_eqpt_" + i);
					String cat_part_no = request.getParameter("cat_part_no_" + i);
					String eqpt_ser_no = request.getParameter("eqpt_ser_no_" + i);
					String eqpt_batch_no = request.getParameter("eqpt_batch_no_" + i);
					String eqpt_part_no = request.getParameter("eqpt_part_no_" + i);
					
					String recv_qty_dumy =request.getParameter("recv_qty_" + i);
					int recv_qty = 0;
					if(!recv_qty_dumy.equals("")) {
						recv_qty = Integer.parseInt(recv_qty_dumy);
					}
					
					String rv_no = request.getParameter("rv_no_" + i);
					String rv_date1 = request.getParameter("rv_date_" + i);
					
					
					Date rv_date = null;
					if(rv_date1.contains("/")) {
						rv_date = formatter_slash.parse(rv_date1);
					}else if(rv_date1.contains("-")) {
						rv_date = formatter_dash.parse(rv_date1);
					}else {
						rv_date = null;
					}
					
					String unit_price_dumy =request.getParameter("unit_price_" + i);
					int unit_price = 0;
					if(!unit_price_dumy.equals("")) {
						unit_price = Integer.parseInt(unit_price_dumy);
					}
					
					String eqpt_make = request.getParameter("eqpt_make_" + i);
					String eqpt_model = request.getParameter("eqpt_model_" + i);
					String cqa_note_date = request.getParameter("cqa_note_date_" + i);
					String origin_country = request.getParameter("origin_country_" + i);
					String induc_year = request.getParameter("induc_year_" + i);
					String nato_stk_no = request.getParameter("nato_stk_no_" + i);
					String def_cat_no_dcan = request.getParameter("def_cat_no_dcan_" + i);
					
			
	
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					Query q = session1.createSQLQuery("insert into mms_tb_imp_drr "
							+ "(recv_sus_no,rv_no,rv_date,material_no,census_no,nomen,recv_qty,"
							+ "eqpt_make,eqpt_model,eqpt_ser_no,eqpt_batch_no,eqpt_part_no,deposit_depot_name,data_cr_by,data_cr_date,op_status,brief_desc,type_of_hldg,type_of_eqpt,cat_part_no,unit_price,"
							+ "cqa_note_date,origin_country,induc_year,nato_stk_no,def_cat_no_dcan,upload_date) values(:a1,:a2,:a3,:a4,:a5,"
							+ ":a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24,:a25,:a26,:a27)");
	
					
					q.setParameter("a1", recv_sus_no);
					q.setParameter("a2", rv_no);
					q.setParameter("a3", rv_date);
					q.setParameter("a4", material_no);
					q.setParameter("a5", census_no);
					q.setParameter("a6", nomen);
					q.setParameter("a7", recv_qty);
					q.setParameter("a8", eqpt_make);
					q.setParameter("a9", eqpt_model);
					q.setParameter("a10", eqpt_ser_no);
					q.setParameter("a11", eqpt_batch_no);
					q.setParameter("a12", eqpt_part_no);
					q.setParameter("a13", roleSusNo);
					q.setParameter("a14", username);
					q.setParameter("a15", new Date());
					q.setParameter("a16", "0");
					q.setParameter("a17", brief_desc);
					q.setParameter("a18", type_of_hldg);
					q.setParameter("a19", type_of_eqpt);
					q.setParameter("a20", cat_part_no);
					q.setParameter("a21", unit_price);
					q.setParameter("a22", cqa_note_date);
					q.setParameter("a23", origin_country);
					q.setParameter("a24", induc_year );
					q.setParameter("a25", nato_stk_no);
					q.setParameter("a26", def_cat_no_dcan);
					q.setParameter("a27", new Date());
					
					q.executeUpdate();
					tx.commit();
					session1.close();
				}
			}
			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mms_upload_DRR");
		}
		else if (table_name.equals("mms_tb_imp_msr") && !table_name.equals("mms_tb_imp_drr") && !table_name.equals("mms_tb_imp_dir")) {
			for (int i = 1; i < countrow; i++) {
				String census_no = request.getParameter("census_no_" + i);
				String month = new SimpleDateFormat("MM-yyyy").format(new Date());
				if(m8DAO.checkDataExits(census_no,month,roleSusNo,table_name) > 0) {
					
					String Stk_type ="";
					if (request.getParameter("Stk_type_" + i).equals("OS_Stk") || request.getParameter("Stk_type_" + i).equals("Res_Stk")
							|| request.getParameter("Stk_type_" + i).equals("Dispersal_Stk")) {
						
						 Stk_type = request.getParameter("Stk_type_" + i);
					}
					else
					{
						
						model.put("msg", "Please Enter Valid Stk type");
						return new ModelAndView("redirect:mms_upload_MSR");
					}
				    String nomen = request.getParameter("nomen_" + i);
					String cat_part_no = request.getParameter("cat_part_no_"+i);
					String eqpt_ser_no = request.getParameter("eqpt_ser_no_"+i);
					String eqpt_batch_no = request.getParameter("eqpt_batch_no_"+i);
					
					int total_free_stock_held = 0;
					if(!request.getParameter("total_free_stock_held_" + i).equals("")) {
					
						if (valid.isOnlyNumer(request.getParameter("total_free_stock_held_" + i)) == true) {
							model.put("msg", "total_free_stock_held " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							total_free_stock_held = Integer.parseInt(request.getParameter("total_free_stock_held_" + i));
						
						}
					}
					
					int Recd_Stk_DGOF = 0;
					if(!request.getParameter("Recd_Stk_DGOF_" + i).equals("")) {
						

						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_DGOF_" + i)) == true) {
							model.put("msg", "Recd_Stk_DGOF " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_DGOF = Integer.parseInt(request.getParameter("Recd_Stk_DGOF_" + i));
						
						}
					}
					
					
					int Recd_Stk_Trade_Import = 0;
					if(!request.getParameter("Recd_Stk_Trade_Import_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Trade_Import_" + i)) == true) {
							model.put("msg", "Recd_Stk_Trade_Import " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Trade_Import = Integer.parseInt(request.getParameter("Recd_Stk_Trade_Import_" + i));
						
						}
						
						
						
					}
					int Recd_Stk_Repair = 0;
					if(!request.getParameter("Recd_Stk_Repair_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Repair_" + i)) == true) {
							model.put("msg", "Recd_Stk_Repair " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Repair = Integer.parseInt(request.getParameter("Recd_Stk_Repair_" + i));
						
						}
					}
					int Recd_Stk_Other = 0;
					if(!request.getParameter("Recd_Stk_Other_" + i).equals("")) {
						
					
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Other_" + i)) == true) {
							model.put("msg", "Recd_Stk_Other " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Other = Integer.parseInt(request.getParameter("Recd_Stk_Other_" + i));
						
						}
					
					}
					
					int Qty_Issued = 0;
					if(!request.getParameter("Qty_Issued_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Qty_Issued_" + i)) == true) {
							model.put("msg", "Qty_Issued " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Qty_Issued = Integer.parseInt(request.getParameter("Qty_Issued_" + i));
						
						}
						
					}
					int Qty_UnderIssue = 0;
					if(!request.getParameter("Qty_UnderIssue_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Qty_UnderIssue_" + i)) == true) {
							model.put("msg", "Qty_UnderIssue " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Qty_UnderIssue = Integer.parseInt(request.getParameter("Qty_UnderIssue_" + i));
						
						}
					}
					int VintageHeld = 0;
					if(!request.getParameter("VintageHeld_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("VintageHeld_" + i)) == true) {
							model.put("msg", "VintageHeld " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							VintageHeld = Integer.parseInt(request.getParameter("VintageHeld_" + i));
						
						}
					}
					int Res_held = 0;
					if(!request.getParameter("Res_held_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("Res_held_" + i)) == true) {
							model.put("msg", "Res held " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
						Res_held = Integer.parseInt(request.getParameter("Res_held_" + i));
						
						}
					}
					
				
					String Res_Type ="";
					if (request.getParameter("Res_Type_"+i).equals("AHQ RES") || request.getParameter("Res_Type_"+i).equals("CGSR")
							|| request.getParameter("Res_Type_"+i).equals("AHQ DS") || request.getParameter("Res_Type_"+i).equals("ETSR")
							||request.getParameter("Res_Type_"+i).equals("TRSS") || request.getParameter("Res_Type_"+i).equals("OTHER")) {
					
						Res_Type = request.getParameter("Res_Type_"+i);
					}
					else
					{
						
						model.put("msg", "Please Enter Valid Res type");
						return new ModelAndView("redirect:mms_upload_MSR");
					}
					int ro_qty = 0;
					if(!request.getParameter("ro_qty_" + i).equals("")) {
						ro_qty = Integer.parseInt(request.getParameter("ro_qty_" + i));
						if (valid.isOnlyNumer(request.getParameter("total_free_stock_held_" + i)) == true) {
							model.put("msg", "ro_qty " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							ro_qty = Integer.parseInt(request.getParameter("ro_qty_" + i));
						
						}
					}
					int rio_qty = 0;
					if(!request.getParameter("rio_qty_" + i).equals("")) {
						rio_qty = Integer.parseInt(request.getParameter("rio_qty_" + i));
						if (valid.isOnlyNumer(request.getParameter("rio_qty_" + i)) == true) {
							model.put("msg", "rio_qty " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							rio_qty = Integer.parseInt(request.getParameter("rio_qty_" + i));
						
						}
					}
					int total_issued_by = 0;
					if(!request.getParameter("total_issued_by_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("total_issued_by_" + i)) == true) {
							model.put("msg", "total_issued_by " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							total_issued_by = Integer.parseInt(request.getParameter("total_issued_by_" + i));
						
						}
					}
					int dues_in = 0;
					if(!request.getParameter("dues_in_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("dues_in_" + i)) == true) {
							model.put("msg", "dues_in " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							dues_in = Integer.parseInt(request.getParameter("dues_in_" + i));
						
						}
					}
				}
				
				
				else {
					
					
					
					String Stk_type ="";
					if (request.getParameter("Stk_type_" + i).equals("OS_Stk") || request.getParameter("Stk_type_" + i).equals("Res_Stk")
							|| request.getParameter("Stk_type_" + i).equals("Dispersal_Stk")) {
						
						 Stk_type = request.getParameter("Stk_type_" + i);
					}
					else
					{
						
						model.put("msg", "Please Enter Valid Stk type");
						return new ModelAndView("redirect:mms_upload_MSR");
					}
				    String nomen = request.getParameter("nomen_" + i);
					String cat_part_no = request.getParameter("cat_part_no_"+i);
					String eqpt_ser_no = request.getParameter("eqpt_ser_no_"+i);
					String eqpt_batch_no = request.getParameter("eqpt_batch_no_"+i);
					
					int total_free_stock_held = 0;
					if(!request.getParameter("total_free_stock_held_" + i).equals("")) {
					
						if (valid.isOnlyNumer(request.getParameter("total_free_stock_held_" + i)) == true) {
							model.put("msg", "total_free_stock_held " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							total_free_stock_held = Integer.parseInt(request.getParameter("total_free_stock_held_" + i));
						
						}
					}
					
					int Recd_Stk_DGOF = 0;
					if(!request.getParameter("Recd_Stk_DGOF_" + i).equals("")) {
						

						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_DGOF_" + i)) == true) {
							model.put("msg", "Recd_Stk_DGOF " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_DGOF = Integer.parseInt(request.getParameter("Recd_Stk_DGOF_" + i));
						
						}
					}
					
					
					int Recd_Stk_Trade_Import = 0;
					if(!request.getParameter("Recd_Stk_Trade_Import_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Trade_Import_" + i)) == true) {
							model.put("msg", "Recd_Stk_Trade_Import " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Trade_Import = Integer.parseInt(request.getParameter("Recd_Stk_Trade_Import_" + i));
						
						}
						
						
						
					}
					int Recd_Stk_Repair = 0;
					if(!request.getParameter("Recd_Stk_Repair_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Repair_" + i)) == true) {
							model.put("msg", "Recd_Stk_Repair " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Repair = Integer.parseInt(request.getParameter("Recd_Stk_Repair_" + i));
						
						}
					}
					int Recd_Stk_Other = 0;
					if(!request.getParameter("Recd_Stk_Other_" + i).equals("")) {
						
					
						if (valid.isOnlyNumer(request.getParameter("Recd_Stk_Other_" + i)) == true) {
							model.put("msg", "Recd_Stk_Other " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Recd_Stk_Other = Integer.parseInt(request.getParameter("Recd_Stk_Other_" + i));
						
						}
					
					}
					
					int Qty_Issued = 0;
					if(!request.getParameter("Qty_Issued_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Qty_Issued_" + i)) == true) {
							model.put("msg", "Qty_Issued " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Qty_Issued = Integer.parseInt(request.getParameter("Qty_Issued_" + i));
						
						}
						
					}
					int Qty_UnderIssue = 0;
					if(!request.getParameter("Qty_UnderIssue_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("Qty_UnderIssue_" + i)) == true) {
							model.put("msg", "Qty_UnderIssue " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							Qty_UnderIssue = Integer.parseInt(request.getParameter("Qty_UnderIssue_" + i));
						
						}
					}
					int VintageHeld = 0;
					if(!request.getParameter("VintageHeld_" + i).equals("")) {
						
						
						if (valid.isOnlyNumer(request.getParameter("VintageHeld_" + i)) == true) {
							model.put("msg", "VintageHeld " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							VintageHeld = Integer.parseInt(request.getParameter("VintageHeld_" + i));
						
						}
					}
					int Res_held = 0;
					if(!request.getParameter("Res_held_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("Res_held_" + i)) == true) {
							model.put("msg", "Res held " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
						Res_held = Integer.parseInt(request.getParameter("Res_held_" + i));
						
						}
					}
					
				
					String Res_Type ="";
					if (request.getParameter("Res_Type_"+i).equals("AHQ RES") || request.getParameter("Res_Type_"+i).equals("CGSR")
							|| request.getParameter("Res_Type_"+i).equals("AHQ DS") || request.getParameter("Res_Type_"+i).equals("ETSR")
							||request.getParameter("Res_Type_"+i).equals("TRSS") || request.getParameter("Res_Type_"+i).equals("OTHER")) {
					
						Res_Type = request.getParameter("Res_Type_"+i);
					}
					else
					{
						
						model.put("msg", "Please Enter Valid Res type");
						return new ModelAndView("redirect:mms_upload_MSR");
					}
					int ro_qty = 0;
					if(!request.getParameter("ro_qty_" + i).equals("")) {
						ro_qty = Integer.parseInt(request.getParameter("ro_qty_" + i));
						if (valid.isOnlyNumer(request.getParameter("total_free_stock_held_" + i)) == true) {
							model.put("msg", "ro_qty " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							ro_qty = Integer.parseInt(request.getParameter("ro_qty_" + i));
						
						}
					}
					int rio_qty = 0;
					if(!request.getParameter("rio_qty_" + i).equals("")) {
						rio_qty = Integer.parseInt(request.getParameter("rio_qty_" + i));
						if (valid.isOnlyNumer(request.getParameter("rio_qty_" + i)) == true) {
							model.put("msg", "rio_qty " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							rio_qty = Integer.parseInt(request.getParameter("rio_qty_" + i));
						
						}
					}
					int total_issued_by = 0;
					if(!request.getParameter("total_issued_by_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("total_issued_by_" + i)) == true) {
							model.put("msg", "total_issued_by " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							total_issued_by = Integer.parseInt(request.getParameter("total_issued_by_" + i));
						
						}
					}
					int dues_in = 0;
					if(!request.getParameter("dues_in_" + i).equals("")) {
						
						if (valid.isOnlyNumer(request.getParameter("dues_in_" + i)) == true) {
							model.put("msg", "dues_in " + valid.isOnlyNumerMSG);
							return new ModelAndView("redirect:mms_upload_MSR");
						}
						else
						{
							dues_in = Integer.parseInt(request.getParameter("dues_in_" + i));
						
						}
					}
					String depot_sus_no="";
					depot_sus_no=request.getParameter("depot_sus_no_"+i);
					String remarks = request.getParameter("remarks_" + i);
					Session session1 = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					Query qry = session1.createSQLQuery("insert into mms_tb_imp_msr "
							+ "(census_no,Stk_type,nomen,cat_part_no,eqpt_ser_no,eqpt_batch_no,total_free_stock_held,Recd_Stk_DGOF,Recd_Stk_Trade_Import,"
							+ "Recd_Stk_Repair,Recd_Stk_Other,Qty_Issued,Qty_UnderIssue," 
							+ "VintageHeld,Res_held,Res_Type,ro_qty,rio_qty,total_issued_by,dues_in,data_upd_by,data_upd_date,remarks,depot_sus_no)"
							+ "values"
							+ "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9,:a10,:a11,:a12,:a13,:a14,:a15,:a16,:a17,:a18,:a19,:a20,:a21,:a22,:a23,:a24)");
	
					qry.setParameter("a1", census_no);
					qry.setParameter("a2", Stk_type);
					qry.setParameter("a3", nomen);
					qry.setParameter("a4", cat_part_no);
					qry.setParameter("a5", eqpt_ser_no);
					qry.setParameter("a6", eqpt_batch_no);
					qry.setParameter("a7", total_free_stock_held);
					qry.setParameter("a8", Recd_Stk_DGOF);
					qry.setParameter("a9", Recd_Stk_Trade_Import);
					qry.setParameter("a10", Recd_Stk_Repair);
					qry.setParameter("a11", Recd_Stk_Other);
					qry.setParameter("a12", Qty_Issued);
					qry.setParameter("a13", Qty_UnderIssue);
					qry.setParameter("a14", VintageHeld);
					qry.setParameter("a15",Res_held);
					qry.setParameter("a16",Res_Type);
					qry.setParameter("a17", ro_qty);
					qry.setParameter("a18", rio_qty);
					qry.setParameter("a19", total_issued_by);
					qry.setParameter("a20", dues_in);
					qry.setParameter("a21", username);
					qry.setParameter("a22", new Date());
					qry.setParameter("a23", remarks);
					qry.setParameter("a24", roleSusNo);
					//qry.setParameter("a24", depot_sus_no);
					qry.executeUpdate();
					tx.commit();
					session1.close();
				}
			}
			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mms_upload_MSR");
		} else {
			model.put("msg", "Please Enter Valid Table Name");
			return new ModelAndView("redirect:mms_upload_DIR");
		}
	}
	// (1)-> (DIR, DRR, MSR SCREEN METHODS END)

	// DIR, DRR, MSR UPLOAD VALIDATE SCREEN START (2)

	@RequestMapping(value = "/admin/UploadExcelList", method = RequestMethod.POST)
	public ModelAndView UploadExcelList(@ModelAttribute("m1_sus") String m1_sus, String m1_unit, String m1_para,
			String m1_from,String m1_to, ModelMap model,HttpSession sessionA) {

	/*
		Date from=new Date();
		try {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(m1_from);
		} catch (ParseException e) {}  
		Date to=new Date();
		try {
			to = new SimpleDateFormat("yyyy-MM-dd").parse(m1_to);
		} catch (ParseException e) {}*/
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("MISO")) {
			model.put("m_1", m8DAO.uploadvalidatelist(m1_sus,m1_para,m1_from,m1_to));
		}else if (roleAccess.equals("Depot")) {
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			model.put("m_1", m8DAO.uploadvalidatelist(roleSusNo, m1_para,m1_from,m1_to));
		}
		model.put("m_2", m1_sus);
		model.put("m_3", m1_unit);
		model.put("m_4", m1_from);
		model.put("m_5", m1_to);

		if (m1_para.equals("DIR")) {
			return new ModelAndView("mms_dir_upload_validateTiles");
		}
		else if (m1_para.equals("DRR")) {
			return new ModelAndView("mms_drr_upload_validateTiles");
		}
		else if (m1_para.equals("MSR")) {
			return new ModelAndView("mms_msr_upload_validateTiles");
		}else {
			return null;
		}
	}

	@RequestMapping(value = "/ConfirmValidDir", method = RequestMethod.POST)
	public @ResponseBody String ConfirmValidDir(String a, HttpServletRequest request) {
		m8DAO.validationConfirmNew(a);
		return "Regd Inserted Successfully";
	}
	// DIR, DRR, MSR UPLOAD VALIDATE SCREEN END (2)
}
