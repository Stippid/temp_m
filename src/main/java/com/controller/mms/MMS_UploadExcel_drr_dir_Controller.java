package com.controller.mms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_UploadExcelDAO;
import com.models.MMS_TB_IMP_DIR_NEWFORMATE;
import com.models.MMS_TB_IMP_DRR_NEWFORMATE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_UploadExcel_drr_dir_Controller {

	@Autowired
	public MMS_UploadExcelDAO m8DAO;

	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController valid = new ValidationController();

	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();

	// DIR, DRR, MSR MODULE (1)-> (UPLOAD DIR SCREEN START)
	@RequestMapping(value = "/mms_upload_DIR_New", method = RequestMethod.GET)
	public ModelAndView importExcelDIRData(ModelMap Mmap, HttpSession session,
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

	@RequestMapping(value = "/mms_upload_DRR_New", method = RequestMethod.GET)
	public ModelAndView importExcelDRRData(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Boolean val = roledao.ScreenRedirect("mms_upload_DRR_New", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("mms_upload_DRR_NewTiles");
	}

	@RequestMapping(value = "/admin/getFormate_MMS_Upload_doc_new", method = RequestMethod.POST)
	public void getFormate_MMS_Upload_doc_new(@ModelAttribute("filename") String filename, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = null;
		if (filename.equals("mms_tb_imp_dir")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator + "DIR_FORMAT.xlsx");
		} else if (filename.equals("mms_tb_imp_depo_stock")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator + "Depot_Stock_FORMAT.xlsx");
		} else if (filename.equals("mms_tb_imp_msr")) {
			file = new File("/srv" + File.separator + "MMS_FORMAT" + File.separator + "MSR_FORMAT.xlsx");
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

	@RequestMapping(value = "/importExcelAction", method = RequestMethod.POST)
	public ModelAndView importExcelAction(
			@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		String table_name = request.getParameter("table_name");
		int countrow = Integer.parseInt(request.getParameter("countrow"));
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();		
		String census_not_present = null;
		String dublicate_regno = null;
		 try {
		if (table_name.equals("mms_tb_imp_dir_newformate") && !table_name.equals("mms_tb_imp_drr_newformate")) {
			for (int i = 1; i < countrow+1; i++) {
				try {
				String material_no = request.getParameter("Material_No_" + i).trim();
				String census_no = request.getParameter("Census_No_" + i).trim();
				String condition = request.getParameter("Condition_" + i).trim();
				String Nomen = request.getParameter("Nomen_" + i).trim();
				String cat_part = request.getParameter("Cat_Part_No_" + i).trim();
				String eqpt_part_no = request.getParameter("Eqpt_Part_No_" + i).trim();
				String eqpt_serial_no = request.getParameter("Eqpt_Serial_No_" + i).trim();
				String eqpt_bathch_no = request.getParameter("Eqpt_Batch_No_" + i).trim();
				String issuing_sus_no = request.getParameter("Issuing_SUS_no_" + i).trim();
				String receiving_sus_no = request.getParameter("Recv_SUS_No_" + i).trim();
				String unit_name = request.getParameter("Unit_Name_" + i).trim();
				String status = request.getParameter("Status_" + i).trim();
				String au = request.getParameter("AU_" + i).trim();
				String issued_qty = request.getParameter("Issued_Qty_" + i).trim();
				String regn_no = request.getParameter("Eqpt_Regn_No_" + i).trim();
				String unit_price = request.getParameter("Unit_Price_" + i).trim();
				String eqpt_make = request.getParameter("Eqpt_Make_" + i).trim();
				String eqpt_model = request.getParameter("Eqpt_Model_" + i).trim();
				String Ro_No = request.getParameter("Ro_No_" + i).trim();
				String iv_no = request.getParameter("IV_No_" + i).trim();
				String iv_date = request.getParameter("IV_Date_" + i).trim();
				String data_upd_by = request.getParameter("Data_Upd_By_" + i).trim();
				String data_upd_date = request.getParameter("Data_Upd_Date_" + i).trim();

				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();  //Double.parseDouble(issued_qty) == 1.0
				if (Double.parseDouble(issued_qty) == 1.0) {
					Query qry = session1.createQuery(
							"select count(*) FROM MMS_TB_IMP_DIR_NEWFORMATE WHERE regn_no =:regn_no and census_no =:census_no");
					qry.setParameter("regn_no", regn_no);
					qry.setParameter("census_no", census_no);
					Long countLong = (Long) qry.uniqueResult();
					int count = countLong.intValue();
					if (count >= 1) {
						dublicate_regno += "," + census_no;
					} else {

						MMS_TB_IMP_DIR_NEWFORMATE dir = new MMS_TB_IMP_DIR_NEWFORMATE();
						dir.setCondition(condition);
						dir.setMaterial_no(material_no);
						dir.setCensus_no(census_no);
						dir.setNomen(Nomen);
						dir.setCat_par_no(cat_part);
						dir.setEqpt_part_no(eqpt_part_no);
						dir.setEqpt_serial_no(eqpt_serial_no);
						dir.setEqpt_batch_no(eqpt_bathch_no);
						dir.setUnit_name(unit_name);
						dir.setStatus(status);
						dir.setIssued_qty(Double.parseDouble(issued_qty));					
						dir.setUnit_price(unit_price);
						dir.setEqpt_make(eqpt_make);
						dir.setEqpt_model(eqpt_model);
						dir.setIssuing_sus_no(issuing_sus_no);
						dir.setReceiving_sus_no(receiving_sus_no);
						dir.setAu(au);
						dir.setRegn_no(regn_no);
						dir.setRo_no(Ro_No);
						dir.setIv_no(iv_no);
						dir.setIv_date(iv_date);
						dir.setData_upd_by(data_upd_by);
						dir.setData_upd_date(data_upd_date);
						dir.setUploaded_dt(new Date());
						dir.setCreated_dt(new Date());
						session1.save(dir);

						String[] result = m8DAO.getregno(regn_no, Ro_No, census_no, issuing_sus_no, receiving_sus_no,
								session);
						if (result != null) {
							String regNo = result[0];
							String yetToCollect = result[1];
							String issued_qnty = result[2];
							int ro_id=Integer.parseInt( result[3]);
						
                        Double newValue = Double.parseDouble(yetToCollect) - 1;
						
							
							Double issu_qnty =  Double.parseDouble(issued_qnty) + 1;

							String hqlUpdate = "update MMS_TB_TEMPREGN_ROWISE_DTL set regn_no=:regn_no,status=:status"
									+ " where regn_no=:regNo";
							session1.createQuery(hqlUpdate).setString("regn_no", regn_no).setString("regNo", regNo).setInteger("status", 1)
									.executeUpdate();

							String hqlUpdate1 = "update MMS_TB_RO_GENERATE_DTL set yet_to_collect=:qnty,issue_qnty=:issue_qnty"
									+ " where id=:ro_id";
							session1.createQuery(hqlUpdate1).setInteger("ro_id", ro_id)
									.setParameter("qnty", newValue).setParameter("issue_qnty", issu_qnty)
									.executeUpdate();
							
							String hqlUpdate2 = "update MMS_TB_REGN_MSTR_DETL set eqpt_regn_no=:regn_no"
									+ " where eqpt_regn_no=:regNo";
							session1.createQuery(hqlUpdate2).setString("regn_no", regn_no).setString("regNo", regNo)
							.executeUpdate();

						}

					}
				} else {
					String[] regnNumbers = regn_no.split(",");
					for (int j = 0; j < regnNumbers.length; j++) {
						int seq=j+1;
						 String regnNumber = regnNumbers[j];
						Query qry = session1.createQuery(
								"select count(*) FROM MMS_TB_IMP_DIR_NEWFORMATE WHERE regn_no =:regn_no and census_no =:census_no");
						qry.setParameter("regn_no", regnNumber.trim());
						qry.setParameter("census_no", census_no);
						Long countLong = (Long) qry.uniqueResult();
						int count = countLong.intValue();
						if (count >= 1) {
							dublicate_regno += "," + census_no;
						} else {

							MMS_TB_IMP_DIR_NEWFORMATE dir = new MMS_TB_IMP_DIR_NEWFORMATE();
							dir.setCondition(condition);
							dir.setMaterial_no(material_no);
							dir.setCensus_no(census_no);
							dir.setNomen(Nomen);
							dir.setRegn_no(regnNumber);
							dir.setCat_par_no(cat_part);
							dir.setEqpt_part_no(eqpt_part_no);
							dir.setEqpt_serial_no(eqpt_serial_no);
							dir.setEqpt_batch_no(eqpt_bathch_no);
							dir.setUnit_name(unit_name);
							dir.setStatus(status);
							dir.setIssued_qty(Double.parseDouble(issued_qty));
							dir.setUnit_price(unit_price);
							dir.setEqpt_make(eqpt_make);
							dir.setEqpt_model(eqpt_model);
							dir.setIssuing_sus_no(issuing_sus_no);
							dir.setReceiving_sus_no(receiving_sus_no);
							dir.setRo_no(Ro_No);
							dir.setAu(au);
							dir.setIv_no(iv_no);
							dir.setIv_date(iv_date);
							dir.setData_upd_by(data_upd_by);
							dir.setData_upd_date(data_upd_date);
							dir.setUploaded_dt(new Date());
							dir.setCreated_dt(new Date());
							session1.save(dir);
							
							String[] result = m8DAO.getrgnoqntymorethanone(regnNumber, Ro_No, census_no, issuing_sus_no, receiving_sus_no,seq,
									session);
							if (result != null) {
								String regNo = result[0];
								String yetToCollect = result[1];
								String issued_qnty = result[2];
								int ro_id=Integer.parseInt( result[3]);
								//float floatValue = Float.parseFloat(yetToCollect);
								//float newValue = floatValue - 1;
								Double newValue = Double.parseDouble(yetToCollect) - seq;

								Double issu_qnty = Double.parseDouble(issued_qnty) + seq;

								String hqlUpdate = "update MMS_TB_TEMPREGN_ROWISE_DTL set regn_no=:regn_no"
										+ " where regn_no=:regNo";
								session1.createQuery(hqlUpdate).setString("regn_no", regnNumber).setString("regNo", regNo)
										.executeUpdate();

								String hqlUpdate1 = "update MMS_TB_RO_GENERATE_DTL set yet_to_collect=:qnty,issue_qnty=:issue_qnty,status=:status"
										+ " where id=:ro_id";
								session1.createQuery(hqlUpdate1).setInteger("ro_id", ro_id)
										.setParameter("qnty", newValue).setParameter("issue_qnty", issu_qnty).setInteger("status",1)
										.executeUpdate();
								
								String hqlUpdate2 = "update MMS_TB_REGN_MSTR_DETL set eqpt_regn_no=:regn_no"
										+ " where eqpt_regn_no=:regNo";
								session1.createQuery(hqlUpdate2).setString("regn_no", regnNumber).setString("regNo", regNo)
								.executeUpdate();

							}

						}
					}
				}

				tx.commit();
				session1.close();
			}
				catch (Exception e) {		     
			        e.printStackTrace();
			        model.put("errorMsg", "An unexpected error occurred.");
			        return new ModelAndView("mms_upload_DRR_New");
			           }
		} 
			model.put("msg", "Your Data Saved Successfully");
			return new ModelAndView("redirect:mms_upload_DRR_New");
		} else if (table_name.equals("mms_tb_imp_drr_newformate") && !table_name.equals("mms_tb_imp_dir")) {
			try {
			for (int i = 1; i < countrow+1; i++) {
				String issue_sus_no = request.getParameter("Issue_SUS_no_" + i).trim();
				String depot_sus = request.getParameter("Recv_SUS_No_" + i).trim();
				String material_no = request.getParameter("Material_No_" + i).trim();
				String census_no = request.getParameter("Census_No_" + i).trim();
				String Nomen = request.getParameter("Nomen_" + i).trim();
				String Brief_Desc = request.getParameter("Brief_Desc_" + i).trim();
				String type_of_hldg = request.getParameter("Type_of_Hldg_" + i).trim();
				String type_of_eqpt = request.getParameter("Type_of_Eqpt_" + i).trim();
				String cat_part_no = request.getParameter("Cat_Part_No_" + i).trim();
				String eqpt_batch_no = request.getParameter("Eqpt_Batch_No_" + i).trim();
				String eqpt_part_no = request.getParameter("Eqpt_Part_No_" + i).trim();
				String conditon = request.getParameter("Condition_" + i).trim();
				String depot_stock = request.getParameter("Recv_Qty_" + i).trim();
				String eqpt_regn_no = request.getParameter("Eqpt_Regn_No_" + i).trim();
				String authorize_unit = request.getParameter("Authorize_unit_" + i).trim();
				String unit_price = request.getParameter("Unit_Price_" + i).trim();
				String eqpt_make = request.getParameter("Eqpt_Make_" + i).trim();
				String eqpt_model = request.getParameter("Eqpt_Model_" + i).trim();
				String upload_file_name = request.getParameter("Upload_File_Name_" + i).trim();
				String cqa_note_date = request.getParameter("CQA_Note_Date_" + i).trim();
				String origin_country = request.getParameter("Origin_Country_" + i).trim();
				String def_cat_no_dcan = request.getParameter("Def_Cat_No_DCAN_" + i).trim();

				Session session1 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session1.beginTransaction();
				Query checkQuery = session1.createQuery(
						"FROM MMS_TB_IMP_DRR_NEWFORMATE WHERE recv_sus_no = :depot_sus AND census_no = :census_no");
				checkQuery.setParameter("depot_sus", depot_sus);
				checkQuery.setParameter("census_no", census_no);
				List<?> existingRecords = checkQuery.list();
				if (existingRecords != null && !existingRecords.isEmpty()) {

					MMS_TB_IMP_DRR_NEWFORMATE existingRecord = (MMS_TB_IMP_DRR_NEWFORMATE) existingRecords.get(0);
					Double existingQuantity = existingRecord.getDepot_stock();
					existingRecord.setDepot_stock(existingQuantity + Double.parseDouble(depot_stock));
					existingRecord.setUploaded_dt(new Date());
					session1.update(existingRecord);

				} else {

					Query qry = session1
							.createQuery("select count(*) FROM MMS_TB_MLCCS_MSTR_DETL WHERE census_no =:census_no");
					qry.setParameter("census_no", census_no);
					Long countLong = (Long) qry.uniqueResult();
					int count = countLong.intValue();

					if (count == 0) {
						if (census_not_present == null || census_not_present.isEmpty()
								|| census_not_present.equalsIgnoreCase("null")) {
							census_not_present = census_no;
						} else {
							census_not_present += "," + census_no;
						}
					} else {

						MMS_TB_IMP_DRR_NEWFORMATE entity = new MMS_TB_IMP_DRR_NEWFORMATE();
						entity.setIssue_sus_no(issue_sus_no);
						entity.setRecv_sus_no(depot_sus);
						entity.setMaterial_no(material_no);
						entity.setCensus_no(census_no);
						entity.setNomen(Nomen);
						entity.setBrief_Desc(Brief_Desc);
						entity.setType_of_hldg(type_of_hldg);
						entity.setType_of_eqpt(type_of_eqpt);
						entity.setCat_part_no(cat_part_no);
						entity.setEqpt_batch_no(eqpt_batch_no);
						entity.setEqpt_part_no(eqpt_part_no);
						entity.setUnit_price(unit_price);
						entity.setDepot_stock(Double.parseDouble(depot_stock));
						entity.setEqpt_make(eqpt_make);
						entity.setEqpt_model(eqpt_model);
						entity.setUpload_file_name(upload_file_name);
						entity.setCqa_note_date(cqa_note_date);
						entity.setOrigin_country(origin_country);
						entity.setDef_cat_no_dcan(def_cat_no_dcan);
						entity.setUploaded_dt(new Date());
						entity.setCreated_dt(new Date());
						entity.setCondition(conditon);
						entity.setAu(authorize_unit);
						entity.setEqpt_regn_no(eqpt_regn_no);
						entity.setCreated_by(username);

						session1.save(entity);
					}

				}

				tx.commit();
				session1.close();
			}
		 } catch (Exception e) {		     
		        e.printStackTrace();
		        model.put("errorMsg", "An unexpected error occurred.");
		        return new ModelAndView("mms_upload_DRR_New");
		    }
		}
		 } catch (Exception e) {		     
		        e.printStackTrace();
		        model.put("errorMsg", "An unexpected error occurred.");
		        return new ModelAndView("mms_upload_DRR_New");
		    }
		model.put("msg", "Your Data Saved Successfully");
		if (census_not_present != null && !census_not_present.isEmpty()
				&& !census_not_present.equalsIgnoreCase("null")) {
			model.put("msg", "CensusNo Not Prestn : " + census_not_present + " ");
		}

		return new ModelAndView("redirect:mms_upload_DRR_New");
	}
	
	@RequestMapping(value = "/getExcelfordir_drr", method = RequestMethod.POST)
	public ModelAndView getExcelfordir_drr(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeofformate,		
			@RequestParam(value = "msg", required = false) String msg) {
	 	
	 	String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		System.err.println(roleid);
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		/*
		 * if (val == false) { return new ModelAndView("AccessTiles"); }
		 */
		/*
		 * if (request.getHeader("Referer") == null) { msg = ""; return new
		 * ModelAndView("redirect:bodyParameterNotAllow"); }
		 */
		ArrayList<List<String>> Excel = new ArrayList<List<String>>();
		String username ="";
		List<String> TH = new ArrayList<String>();	
		if(typeofformate.equals("DRR")) {
			username="DRR";
			
			TH.add("Issue_SUS_no");
			TH.add("Recv_SUS_No");
			TH.add("Material_No");		
			TH.add("Census_No");
			TH.add("Nomen");
			TH.add("Brief_Desc");
			TH.add("Type_of_Hldg");
			TH.add("Type_of_Eqpt");
			TH.add("Cat_Part_No");
			TH.add("Eqpt_Batch_No");	
			TH.add("Eqpt_Part_No");			
			TH.add("Condition");
			TH.add("Recv_Qty");
			TH.add("Eqpt_Regn_No");
			TH.add("Authorize_unit");
			TH.add("Unit_Price");
			TH.add("Eqpt_Make");
			TH.add("Eqpt_Model");
			TH.add("Upload_File_Name");
			TH.add("CQA_Note_Date");			
			TH.add("Origin_Country");
			TH.add("Induc_Year");			
			TH.add("Nato_Stk_No");
			TH.add("Def_Cat_No_DCAN");
			TH.add("Data_Updated_By");			
			TH.add("Data_Upd_Dt");
			TH.add("RV_Date");
			
			
		}else {
			username="DIR";
			
			TH.add("Material_No");
			TH.add("Census_No");
			TH.add("Condition");		
			TH.add("Nomen");
			TH.add("Cat_Part_No");
			TH.add("Eqpt_Part_No");
			TH.add("Eqpt_Serial_No");
			TH.add("Eqpt_Batch_No");
			TH.add("Issuing_SUS_no");
			TH.add("Recv_SUS_No");	
			TH.add("Unit_Name");			
			TH.add("Status");
			TH.add("AU");
			TH.add("Issued_Qty");
			TH.add("Eqpt_Regn_No");
			TH.add("Unit_Price");
			TH.add("Eqpt_Make");
			TH.add("Eqpt_Model");
			TH.add("Ro_No");
			TH.add("IV_No");			
			TH.add("IV_Date");
			TH.add("Data_Upd_By");			
			TH.add("Data_Upd_Date");			
		}	

	
		return new ModelAndView(new Export_dir_drr_formate("L", TH, username, Excel), "userList", Excel);
	}

}
