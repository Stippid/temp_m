package com.controller.tms;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Upload_Excel_Dao;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Upload_Excel_Controller {

	@Autowired
	Upload_Excel_Dao TDAO;

//	@Autowired
//	private SessionFactory sessionFactory;

//	@Autowired
//	CommonController common;
@Autowired
private RoleBaseMenuDAO roledao;
//	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	Common_Mct_slotController M = new Common_Mct_slotController();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value="/upload_excel")
	public ModelAndView upload_excel(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg){	
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctmain", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg",msg);
		return new ModelAndView("upload_excelTiles");
	}

	
	
	public String fileupload(byte[] b, String name, int id, String type) {
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "TEMPEXCEL");
			// File dir = new File("/srv/BRO_REC_Documents/"+id);
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			fname = dir.getAbsolutePath() + File.separator + "tempinterview" + "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}
	
	

	
	/*   NITIN V4 18/11/2022  */
	@RequestMapping(value = "/upload_excel_act", method = RequestMethod.POST)
		public ModelAndView upload_excel_act(HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "u_file1", required = false) MultipartFile upload,
				@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
				
			
			
			DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String username = session.getAttribute("username").toString();
			try {
				
				File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
				
				FileInputStream fis = new FileInputStream(file);
				HSSFWorkbook wb = new HSSFWorkbook(fis);
				HSSFSheet sheet = wb.getSheetAt(0);
				HSSFRow row_head = sheet.getRow(0);
				
				//save excel start
					if (upload.isEmpty()) {
						model.put("msg", "Please Upload File.");
						return new ModelAndView("redirect:upload_excel");
					}
					
					String extention="";
					int z = upload.getOriginalFilename().lastIndexOf('.');
					if (z >= 0) {
						extention = upload.getOriginalFilename().substring(z + 1);
					}
					if(!extention.equals("xls")) {
						model.put("msg", "Please Select Excel File");
						return new ModelAndView("redirect:upload_excel");
					}
					
					if (!row_head.getCell(0).getStringCellValue().equals("ba_no")) {
						model.put("msg", "Please Enter Correct File Header for BA No.");
						return new ModelAndView("redirect:upload_excel");
					}
					if (!row_head.getCell(1).getStringCellValue().equals("chasis_no")) {
						model.put("msg", "Please Enter Correct File Header for Chasis No.");
						return new ModelAndView("redirect:upload_excel");
					}
					if (!row_head.getCell(2).getStringCellValue().equals("engine_no")) {
						model.put("msg", "Please Enter Correct File Header for Engine No.");
						return new ModelAndView("redirect:upload_excel");
					}
					
				
				int upcnt = 0;
					
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						String ba_no = "";
						
						String chasis_no = "";
						
						String engine_no = "";
							HSSFRow row = sheet.getRow(i);
							if (row.getCell(0) == null) {
								break;
							}
							
							for (int i1 = 0; i1 <= 2; i1++) {
								
								String varforval = "";
								
								if(i1==0) {
									varforval = "ba_no";
								}
								if(i1==1) {
									varforval = "chasis_no";
								}
								if(i1==2) {
									varforval = "engine_no";
								}
								
								Cell cell = row.getCell(i1);

								if(cell == null) {
									model.put("msg", "Please Enter "+varforval+" in row "+i);
									return new ModelAndView("redirect:upload_excel");
								}
								
								String value = "";
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									value = cell.getStringCellValue();
									break;
								case Cell.CELL_TYPE_NUMERIC:
									if (HSSFDateUtil.isCellDateFormatted(cell)) {
										value = String.valueOf(cell.getDateCellValue());
									} else {
										value = String.valueOf((long) cell.getNumericCellValue());
									}
									break;
								case Cell.CELL_TYPE_BOOLEAN:
									value = String.valueOf(cell.getBooleanCellValue());
									break;
								default:
								}
								
								if (row_head.getCell(i1).getStringCellValue().equals("ba_no")) {
									ba_no = value;

								}
								if (row_head.getCell(i1).getStringCellValue().equals("chasis_no")) {
									chasis_no = value;

								}
								if (row_head.getCell(i1).getStringCellValue().equals("engine_no")) {
									engine_no = value;

								}
								
						}
							String hql = "update TB_TMS_BANUM_DIRCTRY  set engine_no=:engine_no ,chasis_no=:chasis_no, \n"
									+" modify_by=:modify_by,modify_date=:modify_date"
						+" where substr(ba_no,3,7)=:ba_no";
						
							//ba_no = substr(ba_no,3,7);
						
							Query query = sessionHQL.createQuery(hql)
									.setParameter("engine_no", engine_no)
									.setParameter("chasis_no", chasis_no)
									.setParameter("ba_no", ba_no.substring(2,9))
									.setParameter("modify_by", username )
									.setParameter("modify_date", new Date() );
							
							upcnt = query.executeUpdate();
							
							if(upcnt == 0) {
								model.put("msg", "Unable to Update");
								//break;
							}
					}

						if(upcnt > 0) {
							model.put("msg", "Data Updated Successfully");
							tx.commit();
						}
						
			} catch (RuntimeException e) {
				
				try {
					model.put("msg", "roll back transaction");
					tx.rollback();
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:upload_excel");
		}


}


