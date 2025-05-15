package com.controller.itasset.genrpersonalsanction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Download_Final_sanction_PDF;
import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.dao.itasset.WorkOrder.genr_sanctionformDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.TB_IT_ASEET_PERSONAL_UPLOAD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class GenrItAssetSearchController {


	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private genr_sanctionformDAO genr_sanctionDAO;

	@Autowired
	DataSource dataSource;

	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@RequestMapping(value = "/admin/Genr_It_Assets_Search", method = RequestMethod.GET)
	public ModelAndView Genr_It_Assets_Search(ModelMap Mmap,HttpSession sessionUserId,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Genr_It_Assets_Search", roleid);
		Boolean show = false;
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();		
		String role = sessionUserId.getAttribute("role").toString();		
		
		Mmap.put("roleSusNo", roleSusNo);
		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
		}
		
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();		
		Query que = ses1.createQuery(
				"select distinct count(*) from TB_IT_ASSET_TYPEWISE_ROLE_MASTER where role_name=:role");
		que.setParameter("role", role);
		 Long count = (Long) que.uniqueResult();	
		
	      if(count>0) {    	  
	    	  
	    	  show = true;
	    	  ArrayList<ArrayList<String>> list = genr_sanctionDAO.CountTableData(role, sessionUserId);
				Mmap.put("list", list);
		  }

	
		Mmap.put("show", show);
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		Mmap.put("msg", msg);
		Mmap.put("unit_status", "0");
		Mmap.put("roleAccess", roleAccess);

		return new ModelAndView("GenrItAssetsSearchTiles");
	}
	

	@RequestMapping(value = "/admin/Genr_It_Assets_Search_Click", method = RequestMethod.POST)
	public ModelAndView Genr_It_Assets_Search_Click(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_status1", required = false) String unit_status){

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		if (roleAccess.equals("Unit")) {
			Mmap.put("unit_name", com.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
		}
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("unit_status1", unit_status);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		return new ModelAndView("GenrItAssetsSearchTiles");
	}


	@RequestMapping(value = "/GenrItAssetsSearchData", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GenrItAssetsSearchData(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String unit_sus_no, String unit_name, String unit_status, String batch_id, HttpSession session) throws SQLException {
		return genr_sanctionDAO.GenrItAssetsSearchData(startPage, pageLength, Search, orderColunm, orderType,unit_sus_no,unit_name,unit_status,batch_id,session);
	}


	@RequestMapping(value = "/GenrItAssetsSearchCount", method = RequestMethod.POST)
	public @ResponseBody long GenrItAssetsSearchCount(String Search,String orderColunm,String orderType,String unit_sus_no, String unit_name,
			String unit_status, String batch_id, HttpSession session) throws SQLException {

		return genr_sanctionDAO.GenrItAssetsSearchCount(Search, orderColunm, orderType, unit_sus_no, unit_name, unit_status, batch_id, session);
	}

	  @RequestMapping(value = "/GenrApproveAction", method = RequestMethod.POST)
	    public ModelAndView GenrApproveAction(
	            @RequestParam("b_id") String batch_id,
	            HttpServletRequest request, HttpSession session, ModelMap model,
	            @RequestParam(value = "msg", required = false) String msg,
	            Authentication authentication) {

	        msg = genr_sanctionDAO.genrApprovedataAction(batch_id, session);
	        model.put("msg", msg);
	        return new ModelAndView("redirect:Genr_It_Assets_Search");
	    }

	  @RequestMapping(value = "/GenrRejectAction", method = RequestMethod.POST)
	  public @ResponseBody ModelAndView GenrRejectAction(
	          @ModelAttribute("reid") int id,
	          BindingResult result,
	          HttpServletRequest request,
	          HttpSession sessionA,
	          ModelMap model,
	          @RequestParam(value = "msg", required = false) String msg,
	          @RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
	          Authentication authentication) {

	      List<String> liststr = new ArrayList<>();
	      String roleAccess = (String) sessionA.getAttribute("roleAccess");
	      String roleSubAccess = (String) sessionA.getAttribute("roleSubAccess");
	      String role = (String) sessionA.getAttribute("role");
	      String username = sessionA.getAttribute("username").toString();
	      int status_reject = 3;
	      String status_reject_str = "3";  
	      StringBuilder statusBuilder = new StringBuilder();
	      
	      Session ses1 = HibernateUtil.getSessionFactory().openSession();
	      Transaction t2 = ses1.beginTransaction();        
	      Query q = ses1.createQuery(
	              "select distinct t.role_name, t.applyhierarchy, t.level_of_hierarchy " +
	              "from TB_IT_ASSET_TYPEWISE_ROLE_MASTER t " +
	              "where t.role_name = :role");
	      q.setParameter("role", role);
	      List<Object[]> results = q.list();
	      
	      if (results.size() > 0) {                
	          Object[] firstResult = results.get(0);                
	          String roleName = (String) firstResult[0];
	          String applyHierarchy = (String) firstResult[1]; 
	          String levelOfHierarchy = (String) firstResult[2];
	          
	          if (roleName.equals(role) && applyHierarchy.equals("No")) {
	              statusBuilder.append("final_status = :finalStatus");
	          } else if (levelOfHierarchy.equals(roleSubAccess) && applyHierarchy.equals("Yes")) {
	              if(roleSubAccess.equals("Command")) {                                
	                  statusBuilder.append("comd_status = :statusRejectStr, final_status = :finalStatus");
	              }
	              if(roleSubAccess.equals("Corps")) {
	                  statusBuilder.append("corps_status = :statusRejectStr, final_status = :finalStatus");
	              }
	              if(roleSubAccess.equals("Division")) {
	                  statusBuilder.append("div_status = :statusRejectStr, final_status = :finalStatus");
	              }
	              if(roleSubAccess.equals("Brigade")) {
	                  statusBuilder.append("bde_status = :statusRejectStr, final_status = :finalStatus");
	              }
	          }
	      } else {
	          if(roleSubAccess.equals("Command")) {                                
	              statusBuilder.append("comd_status = :statusRejectStr, final_status = :finalStatus");
	          }
	          if(roleSubAccess.equals("Corps")) {
	              statusBuilder.append("corps_status = :statusRejectStr");
	          }
	          if(roleSubAccess.equals("Division")) {
	              statusBuilder.append("div_status = :statusRejectStr");
	          }
	          if(roleSubAccess.equals("Brigade")) {
	              statusBuilder.append("bde_status = :statusRejectStr");
	          }
	      }

	      Session sessionHQL = null;
	      Transaction tx = null;

	      try {
	          sessionHQL = HibernateUtil.getSessionFactory().openSession();
	          tx = sessionHQL.beginTransaction();

	          String hqlUpdate = "update TB_IT_ASEET_PERSONAL_UPLOAD set unit_app_status = :unit_app_status, " +
	                            "rejected_by = :rejected_by, rejected_date = :rejected_date, " +
	                            "reject_remarks = :reject_remarks" +
	                            (statusBuilder.length() > 0 ? ", " + statusBuilder.toString() : "") +
	                            " where id = :id";
	          
	          Query updateQuery = sessionHQL.createQuery(hqlUpdate)
	                  .setInteger("unit_app_status", status_reject)
	                  .setString("rejected_by", username)
	                  .setParameter("rejected_date", new Date())
	                  .setString("reject_remarks", reject_remarks)
	                  .setInteger("id", id);

	         
	          if (statusBuilder.length() > 0) {
	             
	              if (statusBuilder.toString().contains("statusRejectStr")) {
	                  updateQuery.setString("statusRejectStr", status_reject_str);
	              }
	           
	              if (statusBuilder.toString().contains("finalStatus")) {
	                  updateQuery.setInteger("finalStatus", status_reject);
	              }
	          }

	          int app = updateQuery.executeUpdate();

	          String batchId = (String) sessionHQL.createQuery("select batch_id from TB_IT_ASEET_PERSONAL_UPLOAD where id = :id")
	                  .setInteger("id", id)
	                  .uniqueResult();

	          int app1 = sessionHQL.createQuery("update TB_IT_ASSET_GENR_SANCTION_FORM set status = :status where batch_id = :batchId")
	                  .setInteger("status", status_reject)
	                  .setString("batchId", batchId)
	                  .executeUpdate();

	          tx.commit();
	          liststr.add(app > 0 && app1 > 0 ? "Rejected Successfully" : "Not Rejected Successfully.");
	      } catch (Exception e) {
	          if (tx != null) {
	              tx.rollback();
	          }
	          liststr.add("An error occurred while processing the request.");
	      } finally {
	          if (sessionHQL != null) {
	              sessionHQL.close();
	          }
	      }

	      model.put("msg", liststr.get(0));
	      return new ModelAndView("redirect:Genr_It_Assets_Search");
	  }

	

	  @RequestMapping(value = "/GenrView", method = RequestMethod.POST)
		public void viewPdf(@RequestParam("viewid") int id, HttpServletResponse response, HttpServletRequest request) {

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			FileInputStream fis = null;
			try {
				conn = dataSource.getConnection();
				String query = "SELECT senction_file FROM tb_it_asset_personal_upload WHERE id = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();

				if (rs.next()) {
					String pdfPath = rs.getString("senction_file");
					if (pdfPath != null && !pdfPath.isEmpty()) {
						File pdfFile = new File(pdfPath);
						if(pdfFile.exists() && pdfFile.isFile())
						{
							fis = new FileInputStream(pdfFile);
							response.setContentType("application/pdf");
							response.setHeader("Content-Disposition", "inline; filename=document.pdf");

							OutputStream out = response.getOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead;
							while ((bytesRead = fis.read(buffer)) != -1) {
								out.write(buffer, 0, bytesRead);
							}
							out.close();

						}
						else
						{
							response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid PDF Path or file doesn't exist.");
						}
					}
					else
					{
						response.sendError(HttpServletResponse.SC_NOT_FOUND, "No PDF file path found.");
					}
				}
				else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid ID");
				}

			} catch (SQLException | IOException e) {
				e.printStackTrace();
				try {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error Occurred while viewing PDF.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
					if (fis != null) {
						fis.close();
					}
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		}


	@RequestMapping(value = "/GenrDownload" , method = RequestMethod.GET)
	public void GenrDownload(@ModelAttribute("id") int id,ModelMap model, HttpServletRequest request,HttpServletResponse response,
			HttpSession sessionA, Authentication authentication) throws IOException, SQLException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		TB_IT_ASEET_PERSONAL_UPLOAD u_file_path=(TB_IT_ASEET_PERSONAL_UPLOAD)sessionHQL.get(TB_IT_ASEET_PERSONAL_UPLOAD.class,id);
		String filePath = u_file_path.getSenction_file();
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
	
	@RequestMapping(value = "/getSanctionDetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getSanctionDetails(HttpServletRequest request,HttpSession session,int id){
		return genr_sanctionDAO.getSactionDetails(id);
	}
	@RequestMapping(value = "/downloadFinalSanctionPdf", method = RequestMethod.POST)
	public ModelAndView downloadFinalSanctionPdf(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			Authentication authentication, HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "batch_id_sanction", required = false) String batchId) {


		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PersonalAssertsUrl", roleid);		
		
		if (val == false) {
			// return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String unit_name = "";
		String sanction_date = "";
        String per_no = "";
        String name = "";
        String rk = "";
        String appt = "";

        List<List<String>> sanctionList = genr_sanctionDAO.getSanctionAuthorityData(batchId);
        if (sanctionList != null && !sanctionList.isEmpty()) {
              List<String> firstRow = sanctionList.get(0);
             	per_no = firstRow.get(0) != null ? firstRow.get(0) : ""; 
                name = firstRow.get(1) != null ? firstRow.get(1) : ""; 
                rk = firstRow.get(2) != null ? firstRow.get(2) : "";
                appt = firstRow.get(3) != null ? firstRow.get(3) : "";
        }

    
		 
		String username = session.getAttribute("username").toString();
	
		Mmap.put("msg", msg);

//		Date date = new Date();	
//		String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);
		String foot = "";
		ArrayList<ArrayList<String>> list_finalsanction = genr_sanctionDAO.getFinalSanctionList(batchId);
		   Set<String> uniqueUnitNames = new LinkedHashSet<>(); 
	        if (list_finalsanction != null)
	        {
	            for (List<String> innerList : list_finalsanction) {
	                 if (innerList != null && innerList.size() > 3) {
	                	 unit_name = innerList.get(3);
	                	 
	                    if (unit_name != null && !unit_name.isEmpty()) {
	                    uniqueUnitNames.add(unit_name);

	                  }
	                    sanction_date = innerList.get(4);
	                 }

	               }
	        }
	        
		List<String> TH = new ArrayList<String>();
		TH.add("Sr No");
		TH.add("Control ID");
		TH.add("Batch ID");
		TH.add("Unit");
		TH.add("Dt of Sanction");
		TH.add("Pers No");
		TH.add("Rk");		
		TH.add("Name");
		TH.add("Type of Asset");
		TH.add("Convening Order No");
		TH.add("Convening Order Dt");
	


		return new ModelAndView(new Download_Final_sanction_PDF(TH, foot, username,batchId,unit_name,sanction_date,per_no,name,rk,appt), "userList",
				list_finalsanction);
	}
	
	@RequestMapping(value = "/GenrExcel", method = RequestMethod.POST)
	public ModelAndView GenrExcel(HttpServletRequest request,ModelMap model,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String genrReport1) {

		String unit_sus_no = request.getParameter("unit_sus_noE");
		String unit_name = request.getParameter("unit_nameE");
		String unit_status = request.getParameter("unit_statusE");
		String batch_id = request.getParameter("batch_idE");

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Genr_It_Assets_Search", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		ArrayList<ArrayList<String>> Excel = genr_sanctionDAO.GenrItAssetsExcelData(unit_sus_no,unit_name,unit_status,batch_id,session);

		List<String> TH = new ArrayList<String>();
		TH.add("BATCH ID");
		TH.add("CONTROL ID");
		TH.add("UNIT NAME");
		TH.add("CONVENING ORDER NO");
		TH.add("CONVENING DT");
		TH.add("STATUS");

		String Heading = "\nGENR IT ASSET";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
	}
}
