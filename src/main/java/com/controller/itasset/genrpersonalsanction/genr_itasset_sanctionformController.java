package com.controller.itasset.genrpersonalsanction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BisagN.DateWithTimestamp.DateWithTimeStampController;
import com.controller.ExportFile.Download_PDF_Personal;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.itasset.WorkOrder.genr_sanctionformDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.TB_IT_ASEET_PERSONAL_UPLOAD;
import com.models.assets.TB_IT_ASSET_GENR_SANCTION_FORM;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class genr_itasset_sanctionformController {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	private genr_sanctionformDAO genr_sanctionDAO;

	ValidationController validation = new ValidationController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	@RequestMapping(value = "/admin/genrOfItAsset", method = RequestMethod.GET)
	public ModelAndView genrOfItAsset(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("genrOfItAsset", roleid);
		String username = session.getAttribute("username").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		String batchid = genr_sanctionDAO.generateBatchId(roleSusNo);
		String batchId;

		if (!batchid.equals("0")) {
			String[] parts = batchid.split("/");
			int numericPart = Integer.parseInt(parts[parts.length - 1]);
			numericPart++;
			batchId = String.format("%s/%s/%03d", formattedDate, roleSusNo, numericPart);
		} else {
			batchId = String.format("%s/%s/%03d", formattedDate, roleSusNo, 1);
		}


		ArrayList<ArrayList<String>> list = genr_sanctionDAO.genrItAssetSanctionfomList(roleSusNo);
		Mmap.put("list", list);
		Mmap.put("batchId", batchId);
		Mmap.put("msg", msg);

		return new ModelAndView("genrItassetTiles");
	}

	@RequestMapping(value = "/gernItAssetSanctionFormAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> gernItAssetSanctionFormAction(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam Map<String, String> allParams) {

		Map<String, Object> response = new HashMap<>();
		List<TB_IT_ASSET_GENR_SANCTION_FORM> itAssetGenerList = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String username = session.getAttribute("username").toString();

		String ic_no =null;

		// Get and process array data when click add
		if(allParams != null && allParams.size() > 0 && allParams.containsKey("unit")) {
			TB_IT_ASSET_GENR_SANCTION_FORM itAsset = new TB_IT_ASSET_GENR_SANCTION_FORM();
			itAsset.setUnit_name(allParams.get("unit"));
			itAsset.setIc_no(allParams.get("icNo"));
			itAsset.setName(allParams.get("name"));
			itAsset.setRank(allParams.get("rank"));
			itAsset.setDob(allParams.get("dob"));
			itAsset.setDoc(allParams.get("doc"));
			itAsset.setDor(allParams.get("dor"));
			itAsset.setSus_no(allParams.get("unitSusNo"));
			itAsset.setEligibility(allParams.get("eligibility"));
			itAsset.setType_of_asset(allParams.get("assetTypeId"));
			itAsset.setBatch_id(allParams.get("batchId"));
			itAssetGenerList.add(itAsset);
			ic_no =allParams.get("icNo");
		}

		boolean isSaved = false;

		int count = 0;
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery("select count(id) from TB_IT_ASSET_GENR_SANCTION_FORM where ic_no=:ic_no and status = :status");
		q.setString("ic_no", ic_no);
		q.setInteger("status",0);
		count = ((Number)q.uniqueResult()).intValue();
		t2.commit();


		if(count >0) {
			response.put("success", true);
			response.put("message", "Data saved Successfully");
			response.put("batchId", itAssetGenerList.get(0).getBatch_id());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}


		if (!itAssetGenerList.isEmpty()) {
			isSaved = genr_sanctionDAO.saveItAssetGenerList(itAssetGenerList, username);
		}

		if (isSaved) {
			response.put("success", true);
			response.put("message", "Data saved Successfully");
			response.put("batchId", itAssetGenerList.get(0).getBatch_id());

		} else {
			response.put("success", false);
			response.put("message", "Data Not saved Successfully");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchItAssetSanctionFormAction", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> searchItAssetSanctionFormAction(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "batchId", required = false) String batchId) {

		Map<String, Object> response = new HashMap<>();
		List<TB_IT_ASSET_GENR_SANCTION_FORM> itAssetGenerList = new ArrayList<>();
		if (batchId != null && !batchId.isEmpty()) {
			itAssetGenerList = genr_sanctionDAO.searchItAssetSanctionfomList(batchId);
		}

		if (!itAssetGenerList.isEmpty()) {
			response.put("success", true);
			response.put("message", "Data get Successfully");
			response.put("data", itAssetGenerList);
		} else {
			response.put("success", false);
			response.put("message", "Data Not Available");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// PDF
	@RequestMapping(value = "/downloadPersonalPDF", method = RequestMethod.POST)
	public ModelAndView downloadPersonalPDF(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "batchId", required = false) String batchId,
			@RequestParam(value = "convDate", required = false) String convDate,
			@RequestParam(value = "convNo", required = false) String convNo) {


		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PersonalAssertsUrl", roleid);
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String unit_name = m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0);
		if (val == false) {
			// return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = formatter.format(date);
		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);
		ArrayList<ArrayList<String>> list_serving = genr_sanctionDAO.personalGenrSanctionData(batchId);
		List<String> TH = new ArrayList<String>();
		TH.add("Sr No");
		TH.add("Pers No");
		TH.add("Rk");
		TH.add("Name");
		TH.add("DoB");
		TH.add("DoC");
		// TH.add("DoS");
		TH.add("Dt of Retirement");
		TH.add("Eligibility");
		TH.add("Convening Order No");
		TH.add("Convening Order Dt");
		TH.add("Type of Asset");
		TH.add("Indl Sig\r\n"
				+ "(I declare that I comply by provns of Appx B of DDG IT POLICY B/03568/GEN/DDGIT (PROJ) DT: 29 NOV 24)\r\n"
				+ "");

		return new ModelAndView(new Download_PDF_Personal(TH, foot, username,batchId,unit_name), "userList",
				list_serving);
	}

	@RequestMapping(value = "/PersonalUploadAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> PersonalUploadAction(@RequestParam(value = "u_file", required = false) MultipartFile u_file,
			@RequestParam(value = "batchIdUpload", required = false) String batchId,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		Map<String, Object> response = new HashMap<>();


		if (batchId == null || batchId.trim().isEmpty()) {
			response.put("error", "Please enter Batch Id.");
			return response;
		}

		if (u_file == null || u_file.isEmpty()) {
			response.put("error", "Please choose a file.");
			return response;
		}

		String fileName = u_file.getOriginalFilename();

		// File name validation
		if (fileName != null) {
			String fileNameWithoutExt = fileName;
			int i = fileName.lastIndexOf('.');
			if (i >= 0) {
				fileNameWithoutExt = fileName.substring(0, i);
			}

			String expectedFileName = batchId.replace("/", "_");
			if (!fileNameWithoutExt.equals(expectedFileName + "_with_sign")) {
				response.put("msg", "File name does not match the batch ID format. Expected: " + expectedFileName + "_with_sign.pdf");
				return response;
			}
		}

		String fileExtension = "";
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			fileExtension = "." + fileName.substring(i + 1).toLowerCase();
		}

		List<String> allowedExtensions = Arrays.asList(".pdf");
		if (!allowedExtensions.contains(fileExtension)) {
			response.put("error", "Invalid file type. Please upload a *.pdf file.");
			return response;
		}
		long fileSizeLimit = 2 * 1024 * 1024; // 2MB in bytes
		if (u_file.getSize() > fileSizeLimit) {
			response.put("error", "File size exceeds 2MB. Please upload a smaller file.");
			return response;
		}

		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String fname = "";
		Date date = new Date();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_IT_ASEET_PERSONAL_UPLOAD PL_asset = new TB_IT_ASEET_PERSONAL_UPLOAD();
		Query q0 = sessionHQL.createQuery("select count(id) from TB_IT_ASEET_PERSONAL_UPLOAD where batch_id=:batch_id").setParameter("batch_id", batchId);
		Long c = (Long) q0.uniqueResult();
		String filepath="";
		try {
			if (!u_file.isEmpty()) {
				try {
					byte[] bytes = u_file.getBytes();
					String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();

					File dir = new File(mnhFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					fname = dir.getAbsolutePath() + File.separator + fileName;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					filepath = fname.replace("\\", "/");
				} catch (Exception e) {
					response.put("error", "Error during file processing: " + e.getMessage());
					return response;

				}
			}


			if (c == 0) {
				PL_asset.setCreated_by(username);
				PL_asset.setCreated_date(date);
				PL_asset.setUnit_app_status(0);
				PL_asset.setFinal_status(0);
				PL_asset.setUnit_sus_no(roleSusNo);
				PL_asset.setBatch_id(batchId);
				PL_asset.setSenction_file(filepath);
				PL_asset.setUnit_deo_status(0);
				sessionHQL.save(PL_asset);
				response.put("msg", "PDF Uploaded Successfully.");
			}else {
				String hql = "update TB_IT_ASEET_PERSONAL_UPLOAD set unit_deo_status=:unit_deo_status,senction_file=:senction_file,"
						+ "unit_sus_no=:unit_sus_no,modified_by=:modified_by,modified_date=:modified_date  "
						+ " where  batch_id=:batch_id";
				Query query = sessionHQL.createQuery(hql).setInteger("unit_deo_status",0).setString("senction_file",filepath).setString("unit_sus_no",roleSusNo).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setString("batch_id", batchId);

				String msg = query.executeUpdate() > 0 ? "PDF Updated Successfully." : "PDF Not Updated Successfully";
				response.put("msg",msg);
			}

			tx.commit();
		} catch (RuntimeException e) {
			response.put("error", "Error during PDF upload: " + e.getMessage());
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return response;
	}


	@RequestMapping(value = "/deleteItAssetSanctionFormAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteItAssetSanctionFormAction(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "personnelNo", required = false) String personnelNo, @RequestParam(value = "batchId", required = false) String batchId ) {

		Map<String, Object> response = new HashMap<>();
		boolean isDeleted = false;

		if (personnelNo != null && !personnelNo.isEmpty() && batchId != null && !batchId.isEmpty() ) {
			isDeleted=  genr_sanctionDAO.deleteItAssetSanctionForm(personnelNo,batchId);

		}
		if(isDeleted) {
			response.put("success", true);
			response.put("message", "Data deleted Successfully");
		}else{
			response.put("success", false);
			response.put("message", "Data Not Deleted Successfully");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@RequestMapping(value = "/updateConvNoandConvDateAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateConvNoandConvDateAction(
			Authentication authentication,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("batchId") String batchId,
			@RequestParam("convNo") String convNo,
			@RequestParam("convDate") String convDate) {

		Map<String, Object> response = new HashMap<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String username = session.getAttribute("username").toString();


		Boolean isupate = false;
		try {

			if (batchId != null && !batchId.isEmpty()) {

				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date convertedConvDate= format1.parse(convDate);
					java.sql.Date sqlDate = new java.sql.Date(convertedConvDate.getTime());
					isupate= genr_sanctionDAO.updateConvDetails(batchId,convNo, sqlDate);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}

			}
			isupate = true;
		}
		catch(Exception e) {
			isupate = false;
		}



		if (isupate) {
			response.put("success", true);
			response.put("message", "Data updated Successfully");
			response.put("batchId", batchId);

		} else {
			response.put("success", false);
			response.put("message", "Data Not updated Successfully");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}



	@RequestMapping(value = "/getPdfBatchIdWise", method = RequestMethod.GET)
	public void getPdfBatchIdWise(@RequestParam("batch_id") String batch_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = genr_sanctionDAO.getPdfPath(batch_id);

		if (filePath == null || filePath.isEmpty() || filePath.equals("null")) {
			response.setContentType("text/plain");
			response.getWriter().write("PDF Not Found: File path is missing or invalid.");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Path path = Paths.get(filePath);
		if (!Files.exists(path)) {
			response.setContentType("text/plain");
			response.getWriter().write("PDF Not Found: File does not exist at this path.");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		File downloadFile = path.toFile();

		try (FileInputStream inputStream = new FileInputStream(downloadFile);
				OutputStream outStream = response.getOutputStream()) {


			response.setContentType("application/pdf");
			response.setContentLength((int) downloadFile.length());
			response.setHeader("Content-Disposition", "inline; filename=\"" + downloadFile.getName() + "\"");

			//Prevent Caching
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}


		} catch (IOException e) {
			response.setContentType("text/plain");
			response.getWriter().write("Error while processing the PDF: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/finalSaveAndSubmitAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody public ResponseEntity<Map<String, Object>> finalSaveAndSubmitAction(
			Authentication authentication, HttpServletRequest request, HttpSession session,
			@RequestParam("batchId") String batchId) {

		Map<String, Object> response = new HashMap<>();

		boolean isUpdate = false;
		if (batchId != null && !batchId.isEmpty()) {
			isUpdate = genr_sanctionDAO.updatetypesStatus(batchId);
		}

		if (isUpdate) {
			response.put("success", true);
			response.put("message", "Data Save and Submit Successfully");
			response.put("batchId", batchId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put("success", false);
			response.put("message", "Data Not Save and Submit Successfully");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}



	@RequestMapping(value = "/deleteItAssetAction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteItAssetAction(Authentication authentication,
			HttpServletRequest request, HttpSession session, @RequestParam("batchId") String batchId,
			@RequestParam("u_file") String u_file) {

		Map<String, Object> response = new HashMap<>();
		boolean isUpdate = false;
		try {
			if (batchId != null && !batchId.isEmpty()) {
				isUpdate = genr_sanctionDAO.deleteStatus(batchId, u_file);
			}
		} catch (Exception e) {
			isUpdate = false;
			response.put("success", false);
			response.put("message", "Error during delete: " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (isUpdate) {
			response.put("success", true);
			response.put("message", "Data Deleted Successfully");
			response.put("batchId", batchId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put("success", false);
			response.put("message", "Could not delete the specified data");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}



	@RequestMapping(value = "/Genr_It_Assets_Upload", method = RequestMethod.GET)
	public ModelAndView Genr_It_Assets_Upload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = null;
		String username = null;
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		//		Boolean val = roledao.ScreenRedirect("Genr_It_Assets_Upload", roleid);
		//
		//		if (val == false) {
		//			return new ModelAndView("AccessTiles");
		//		}
		//		if (request.getHeader("Referer") == null) {
		//			msg = "";
		//		}
		String status = request.getParameter("status");
		if (status == null) {
			status = "0";
		}

		System.out.println(status);
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		return new ModelAndView("GenrItAssetsUploadTiles");
	}



	@RequestMapping(value = "/admin/Genr_It_Assets_Upload_Search", method = RequestMethod.POST)
	public ModelAndView Genr_It_Assets_Upload_Search(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "batch_id1", required = false) String batch_id,
			@RequestParam(value = "status1", required = false) String status){

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Mmap.put("status1", status);
		Mmap.put("batch_id1", batch_id);
		return new ModelAndView("GenrItAssetsUploadTiles");
	}


	@RequestMapping(value = "/getBatchIdWisePdfData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getBatchIdWisePdfData(int startPage,int pageLength,String Search,String orderColunm,String orderType, String roleSusNo,String status, String batch_id, HttpSession session) {
		return genr_sanctionDAO.getBatchIdWisePdfData(startPage, pageLength, Search, orderColunm, orderType, roleSusNo, status, batch_id, session);
	}


	@RequestMapping(value = "/getBatchIdWisePdfCount", method = RequestMethod.POST)
	public @ResponseBody long getBatchIdWisePdfCount(String Search,String orderColunm,String orderType, String roleSusNo, String status, String batch_id, HttpSession session) throws SQLException {

		return genr_sanctionDAO.getBatchIdWisePdfCount(Search,orderColunm,orderType,roleSusNo,status,batch_id,session);
	}
}