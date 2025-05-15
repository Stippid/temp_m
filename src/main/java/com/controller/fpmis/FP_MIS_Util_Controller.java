package com.controller.fpmis;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.*;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;

import org.apache.poi.ooxml.POIXMLProperties;     

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;


import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import com.dao.fpmis.FP_MIS_ReportsDAO;
import com.dao.fpmis.FP_MIS_TransactionDAO;      
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class FP_MIS_Util_Controller {

	

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired     
	private FP_MIS_ReportsDAO fp_rptDao;

	@Autowired
	private FP_MIS_TransactionDAO fp_trDao;

	HexatoAsciiDAO HexaDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/fp_info_board", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView fp_info_board(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String role = sessionA.getAttribute("role").toString().toUpperCase();

		Boolean val = roledao.ScreenRedirect("fp_info_board", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String nPara = "";
		String nUnty = null;
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);
		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);
		rolefmn = li.get(0).get(0);
		nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1);
		String hqnPara=nPara;
		String[] selsus1=nPara.split("_");		
		String nParaValue = "NIL";
		String domainid = "";
		Mmap.put("n_2", nParaValue);
		Mmap.put("n_3", domainid);
		Mmap.put("n_4", nPara);
		/*if (!nUnty.equalsIgnoreCase("5")) {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));			
		} else {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		}*/
		
		/*if (role.equalsIgnoreCase("FP_DTE") || role.equalsIgnoreCase("FP_VIEW") || role.equalsIgnoreCase("FPADMIN")) {			
						
		} else {
			
		}*/
		Mmap.put("n_head", fp_trDao.getHeadlist("", sessionA, "2076"));
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		Mmap.put("msg", msg);
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", cfinli);
		System.out.println("=="+hqnPara+","+nPara);
		//Mmap.put("n_hq", fp_trDao.getHirarwithFplist("", sessionA, "HQ_" + hqnPara));
		//Mmap.put("n_unt", fp_trDao.getUnitHirarlist("", sessionA, "SUS_" + nPara));
		//System.out.println("=="+fp_trDao.getHirarwithFplist("", sessionA, "HQ_" + hqnPara));
		String amtrs=request.getParameter("amt_rs");
		if (amtrs == null || amtrs.equalsIgnoreCase(null)) {
			if(Integer.parseInt(li.get(0).get(3))<=1) {
				amtrs="CR";
			} else {
				amtrs="RS";
			}
		}
		role=role.toUpperCase();
		if (role.equalsIgnoreCase("DTE_FP") || role.equalsIgnoreCase("FP_DTE") || role.equalsIgnoreCase("FP_VIEW") || role.equalsIgnoreCase("FPADMIN")  || role.indexOf("FP_HQ")>=0) {
			String selsus=request.getParameter("DtlBH");						
			if (selsus==null || selsus.length()<=0 || selsus.toUpperCase().indexOf("ALL")>=0) {
				li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + rolesus);				
				Mmap.put("n_hq", fp_trDao.getHirarwithFplist("", sessionA, "HQ_" + hqnPara));
				rolefmn = li.get(0).get(0);
				nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1)+"_"+li.get(0).get(4);
				Mmap.put("n_unt", fp_trDao.getUnitHirarlist("", sessionA, "SUS_" + nPara));
				if(Integer.parseInt(nUnty)>1) {
					//selsus="ALL_ALL_ALL";
					//nPara="ALL_ALL_ALL";
					if (Integer.parseInt(nUnty)>0) {
						//Mmap.put("list", fp_rptDao.nFundInfoDBDetl_n(nPara, usrid, nPara, cfinli, sessionA));
						Mmap.put("list", fp_rptDao.nFundInfoDBDetl_n1(nPara, usrid, nPara, cfinli, sessionA));
					}
				}
			} else {			
				selsus1=selsus.split("_");
				System.out.println("Modified SUS-"+selsus);
				li = fp_trDao.getunitlist("", sessionA, "SUS_5_XXXXXXXXXX_" + selsus1[2]);				
				Mmap.put("n_hq", fp_trDao.getHirarwithFplist("", sessionA, "HQ_" + hqnPara));
				rolefmn = li.get(0).get(0);
				nPara = li.get(0).get(3) + "_" + li.get(0).get(0) + "_" + li.get(0).get(1)+"_"+li.get(0).get(4);
				String nChld=li.get(0).get(4);
				Mmap.put("n_unt", fp_trDao.getUnitHirarlist("", sessionA, "SUS_" + nPara));				
				Mmap.put("list", fp_rptDao.nFundInfoDBDetl_n1(nPara, usrid, nPara, cfinli, sessionA));
			}			
			System.out.println("Calling - Final-"+nPara);						
			Mmap.put("nSel",selsus1[2]+"_"+amtrs);
			System.out.println("Calling Out - 1");
			return new ModelAndView("fp_ninfo_boardTiles");
		} else {
			Mmap.put("nSel",selsus1[2]+"_"+amtrs);
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
			return new ModelAndView("fp_info_boardTiles");	
		}
		
		
		
	}
	
	/*@RequestMapping(value = "/fp_info_board_n", method = RequestMethod.GET)
	public ModelAndView fp_info_board_n(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//int userid = (Integer) sessionA.getAttribute("userId");
		String usrid = sessionA.getAttribute("username").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String rolesus = sessionA.getAttribute("roleSusNo").toString();
		String rolefmn = sessionA.getAttribute("roleFormationNo").toString();
		String role = sessionA.getAttribute("role").toString().toUpperCase();

		Boolean val = roledao.ScreenRedirect("fp_info_board", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			////return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		String dtype = request.getParameter("datatype");
		String hqss = request.getParameter("hqsus");	
		String[] hqssa=hqss.split("_");
		if (!hqssa[2].equalsIgnoreCase("ALL")) {
			rolesus = hqssa[2];
		} 
		String nPara = "";
		String nUnty = null;
		nUnty = fp_trDao.getUnitType("", sessionA, "SUS_" + rolesus);

		nPara = nUnty + "_" + rolefmn + "_" + rolesus;
		String nParaValue = "NIL";
		String domainid = "";
		Mmap.put("n_2", nParaValue);
		Mmap.put("n_3", domainid);
		Mmap.put("n_4", nPara);
		if (role.equalsIgnoreCase("FP_DTE") || role.equalsIgnoreCase("FP_VIEW") || role.equalsIgnoreCase("FPADMIN")) {
			Mmap.put("n_hq", fp_trDao.getUnitHirarlist("", sessionA, "SUS_" + nPara));			
		} else {
			Mmap.put("n_hq", fp_trDao.getunitBuglist("", sessionA, "SUS_" + nPara));
		}
		Mmap.put("n_unt", fp_trDao.getunitlist("", sessionA, "UNIT_" + nPara));
		Mmap.put("n_head", fp_trDao.getHeadlist("", sessionA, "2076"));
		ArrayList<ArrayList<String>> finli = fp_trDao.FindFinYear("", sessionA, "");
		String cfinli = fp_trDao.nGetAdmFinYear("CFY");
		Mmap.put("msg", msg);
		Mmap.put("n_finyr", finli);
		Mmap.put("n_cfinyr", cfinli);
		if (role.equalsIgnoreCase("FP_DTE") || role.equalsIgnoreCase("FP_VIEW") || role.equalsIgnoreCase("FPADMIN")) {
			return new ModelAndView("fp_ninfo_boardTiles");
		} else {
			return new ModelAndView("fp_info_boardTiles");	
		}
	}*/

	  @SuppressWarnings("deprecation")
		@RequestMapping(value = "/admin/fp_cr_dft_exl", method = RequestMethod.POST)
		public void fp_cr_dft_exl(@ModelAttribute("filename") @RequestParam(value = "msg", required = false) String msg, String m1_tryear, String m1_lvl,String m1_rptLvl,ModelMap model,HttpSession sessionA,String rsfmt,HttpServletRequest request, HttpServletResponse response) throws IOException{
			String usrid = sessionA.getAttribute("username").toString();
			String roleid = sessionA.getAttribute("roleid").toString();
			String rolesus=sessionA.getAttribute("roleSusNo").toString();
			String rolefmn=sessionA.getAttribute("roleFormationNo").toString();
			String fname = "";
			if (m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				fname = "Draft_Allot_Report.xlsx";
			} else if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM")) {
				fname = "Draft_Fund_Allot_to_Holders.xlsx";
			} else {
				fname = "Draft_Fund_Allot_Summary.xlsx";
			}		
			String fileYr=m1_tryear.substring(2,4);
			String m1_slvl="";
			String nRpt=m1_lvl;
			String nPara="";
			
			String nUnty=null;
			nUnty=fp_trDao.getUnitType("",sessionA,"SUS_"+rolesus);		
			nPara=nUnty+"_"+rolefmn+"_"+rolesus;
			m1_lvl=nPara;
			String hqLvl[]=m1_lvl.split("_");
			ArrayList<ArrayList<String>> drfrpt = new ArrayList<ArrayList<String>>();
			
			if (m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				drfrpt=fp_rptDao.nFundDraftPrint_Ent(m1_tryear,m1_lvl,nRpt,usrid,m1_rptLvl,rsfmt);
			} else {
				drfrpt=fp_rptDao.nFundDraftPrint(m1_tryear,m1_lvl,nRpt,usrid,m1_rptLvl,sessionA,rsfmt);
			}		

			nUnty=fp_trDao.getUnitType("",sessionA,"SUS_"+rolesus);
			nPara=nUnty+"_"+rolefmn+"_"+rolesus;
			ArrayList<ArrayList<String>> bhlist = new ArrayList<ArrayList<String>>();
			bhlist=fp_trDao.getunitBuglist("",sessionA,"HQ_"+nPara);
			String sheetName = "";
			String password = "MISOZ";

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date currentDate = new Date();

			String dateString = formatter.format(currentDate);
			int m1tryear1=Integer.parseInt(m1_tryear)+1;

			if (m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				sheetName = "Draft_Allotment_" + m1_tryear + "-" + m1tryear1;
			} else {
				sheetName = "Summary_Report_" + m1_tryear + "-" + m1tryear1;
			}

			XSSFWorkbook wb = new XSSFWorkbook();

			XSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, sheetName);
			sheet.enableLocking();
			
			sheet.lockDeleteColumns(true);
			sheet.lockDeleteRows(true);
			sheet.lockFormatCells(true);
			sheet.lockFormatColumns(true);
			sheet.lockFormatRows(true);
			sheet.lockInsertColumns(true);
			sheet.lockInsertRows(true);
			sheet.lockObjects(true);
			//sheet.lockSelectLockedCells(false);
			sheet.lockSelectUnlockedCells(false);
			sheet.lockScenarios(true);
			
			sheet.lockInsertHyperlinks(true);
			sheet.lockSort(true);
			sheet.protectSheet(password);
			wb.setWorkbookPassword("password", HashAlgorithm.sha512);
			wb.lockStructure();
		    
			String encrypted_sus = "";
			
		    String enckey = HexaDao.getAlphaNumericString();
		    
			Cipher c = null;
			try {
				c = HexaDao.EncryptionSHA256Algo(sessionA,enckey);
				byte[] encode = null;
				encode = c.doFinal(rolesus.getBytes());
				encrypted_sus = new String(Base64.encodeBase64(encode));
				
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NullPointerException e1) {
				
				e1.printStackTrace();
			}
	   		
			String encAuthor = encrypted_sus+"0fsxzjyg=="+enckey;

			POIXMLProperties props = wb.getProperties();
			POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
			coreProp.setCreator(encAuthor);
			coreProp.setLastModifiedByUser(encAuthor);
		
			coreProp.setDescription("FP MIS");
			coreProp.setCategory("Budget Allocation Sheet");

			POIXMLProperties.CustomProperties custProp = props.getCustomProperties();
			custProp.addProperty("Author", encAuthor);
			custProp.addProperty("DOC", dateString);
			custProp.addProperty("Published", true);

			XSSFCellStyle unlockCellStyle = wb.createCellStyle();
			unlockCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN .getIndex());
			unlockCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			if(drfrpt.get(0).get(19).equalsIgnoreCase("CR")) {
				unlockCellStyle.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00000"));
			} else {
				unlockCellStyle.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00"));
			}
			unlockCellStyle.setAlignment(HorizontalAlignment.RIGHT);
			unlockCellStyle.setLocked(false);
			
			
			int rowIndex = 0;
			int colIndex = 0;
			int rowIndexhq = 0;
			int colIndexhq=18;
			int hqsmflag=0;
			int hqsmrow=3;
			
			
			XSSFCellStyle fS_sum = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			fS_sum.setFont(font);
			if(drfrpt.get(0).get(19).equalsIgnoreCase("CR")) {
				fS_sum.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00000"));
			} else {
				fS_sum.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00"));
			}
			fS_sum.setAlignment(HorizontalAlignment.RIGHT);
			
			XSSFCellStyle fS_fund = wb.createCellStyle();
			Font fontfund = wb.createFont();
			fontfund.setColor(IndexedColors.DARK_TEAL.getIndex());
			fS_fund.setFont(fontfund);
			if(drfrpt.get(0).get(19).equalsIgnoreCase("CR")) {
				fS_fund.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00000"));
			} else {
				fS_fund.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00"));
			}
			fS_fund.setAlignment(HorizontalAlignment.RIGHT);
			XSSFCellStyle fS_num = wb.createCellStyle();    
			Font fontn = wb.createFont();
			fS_num.setFont(fontn);
			if(drfrpt.get(0).get(19).equalsIgnoreCase("CR")) {
				fS_num.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00000"));
			} else {
				fS_num.setDataFormat(wb.createDataFormat().getFormat("##,##,##,##,##,##0.00"));
			}
			fS_num.setAlignment(HorizontalAlignment.RIGHT);
			XSSFCellStyle fS_fnum = wb.createCellStyle();
			Font fontfn = wb.createFont();
			fontfn.setColor(IndexedColors.BLUE.getIndex());
			fontfn.setFontHeightInPoints((short) 12);
			fS_fnum.setFont(fontfn);	
			fS_fnum.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			fS_fnum.setWrapText(true);
			fS_fnum.setAlignment(HorizontalAlignment.RIGHT );
			fS_fnum.setVerticalAlignment(VerticalAlignment.CENTER);
			fS_fnum.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			XSSFCellStyle fS_wtext = wb.createCellStyle();
			fS_wtext.setWrapText(true);
			
			XSSFCellStyle fS_Header = wb.createCellStyle();
			Font fonthd = wb.createFont();
			fonthd.setColor(IndexedColors.WHITE.getIndex());
			fonthd.setFontHeightInPoints((short) 12);
			fS_Header.setFont(fonthd);	
			fS_Header.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			fS_Header.setWrapText(true);
			fS_Header.setAlignment(HorizontalAlignment.CENTER );
			fS_Header.setVerticalAlignment(VerticalAlignment.TOP);
			fS_Header.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			XSSFCellStyle fS_smHeader = wb.createCellStyle();
			Font fontsmhd = wb.createFont();
			fontsmhd.setColor(IndexedColors.WHITE.getIndex());
			fS_smHeader.setFont(fontsmhd);	
			fS_smHeader.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			fS_smHeader.setWrapText(true);
			fS_smHeader.setAlignment(HorizontalAlignment.CENTER);
			fS_smHeader.setVerticalAlignment(VerticalAlignment.CENTER);
			fS_smHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
			
 
			XSSFRow row = sheet.createRow(rowIndex);		
			XSSFCell cellh1 = row.createCell(colIndex);
			cellh1.setCellValue("FR_SUS_NO");
			sheet.setColumnWidth(colIndex, 1500);
			colIndex++;		
			XSSFCell cellh2 = row.createCell(colIndex);
			cellh2.setCellValue("DFLAG");
			sheet.setColumnWidth(colIndex, 1500);
			colIndex++;		
			XSSFCell cellh3 = row.createCell(colIndex);
			cellh3.setCellValue("TR_HEAD");
			sheet.setColumnWidth(colIndex, 1500);
			colIndex++;		
			XSSFCell cell0 = row.createCell(colIndex);
			cell0.setCellValue("MAJOR_HEAD");
			sheet.setColumnWidth(colIndex, 500);
			colIndex++;		
			XSSFCell cell1 = row.createCell(colIndex);
			cell1.setCellValue("MINOR_HEAD");
			sheet.setColumnWidth(colIndex, 500);
			colIndex++;
			XSSFCell cell2 = row.createCell(colIndex);
			cell2.setCellValue("SUB_HEAD");
			sheet.setColumnWidth(colIndex, 500);
			colIndex++;	
			XSSFCell cell3 = row.createCell(colIndex);
			cell3.setCellValue("HEAD_CODE");
			sheet.setColumnWidth(colIndex, 2000);
			colIndex++;
			XSSFCell cell4 = row.createCell(colIndex);
			cell4.setCellValue("HEAD");
			sheet.setColumnWidth(colIndex, 7000);
			colIndex++;
			XSSFCell cell3a = row.createCell(colIndex);
			cell3a.setCellValue("FUND_RECD");
			sheet.setColumnWidth(colIndex, 4000);
			colIndex++;	
			XSSFCell cell5s;
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				cell5s = row.createCell(colIndex);
				cell5s.setCellValue("BG_SUS");
				sheet.setColumnWidth(colIndex, 3500);
				colIndex++;
				XSSFCell cell5a = row.createCell(colIndex);
				cell5a.setCellValue("BG_HLDER_FMN");
				sheet.setColumnWidth(colIndex, 3500);
				colIndex++;	
				XSSFCell cell5 = row.createCell(colIndex);
				cell5.setCellValue("BG_HLDER");
				sheet.setColumnWidth(colIndex, 5500);
				colIndex++;
			}	
			XSSFCell cell6 = row.createCell(colIndex);
			cell6.setCellValue("PRV_ALLOT");
			sheet.setColumnWidth(colIndex, 4000);
			colIndex++;	
			XSSFCell cell7 = row.createCell(colIndex);
			cell7.setCellValue("CUR_PROJ");
			sheet.setColumnWidth(colIndex, 4000);
			colIndex++;
			XSSFCell cell8 = row.createCell(colIndex);
			cell8.setCellValue("CUR_ALLOT");
			sheet.setColumnWidth(colIndex, 4500);
			colIndex++;
			XSSFCell cell9 = row.createCell(colIndex);
			cell9.setCellValue("FMT");
			sheet.setColumnWidth(colIndex, 1500);
			colIndex++;
			XSSFCell cell9a = row.createCell(colIndex);
			sheet.setColumnWidth(colIndex, 500);

			
			row.setZeroHeight(true);
			
			
			XSSFCell cellsmhq = row.createCell(colIndexhq);
			cellsmhq.setCellValue("");
			sheet.setColumnWidth(colIndexhq, 7000);
			
			XSSFCell cellsm = row.createCell(colIndexhq-1);
			cellsm.setCellValue("");
			sheet.setColumnWidth(colIndexhq-1, 4000);
			
			

			rowIndex++;
			colIndex = 0;
			
			row = sheet.createRow(rowIndex);
			cellh1 = row.createCell(colIndex);
			cellh1.setCellValue("");
			sheet.setColumnWidth(colIndex, 1500);
			cellh1.setCellStyle(fS_Header);
			colIndex++;		
			cellh2 = row.createCell(colIndex);
			cellh2.setCellValue("");
			sheet.setColumnWidth(colIndex, 1500);
			cellh2.setCellStyle(fS_Header);
			colIndex++;		
			cellh3 = row.createCell(colIndex);
			cellh3.setCellValue("");
			sheet.setColumnWidth(colIndex, 1500);
			cellh3.setCellStyle(fS_Header);
			colIndex++;		
			cell0 = row.createCell(colIndex);
			cell0.setCellValue("");
			sheet.setColumnWidth(colIndex, 500);
			cell0.setCellStyle(fS_Header);
			colIndex++;		
			cell1 = row.createCell(colIndex);
			cell1.setCellValue("");
			sheet.setColumnWidth(colIndex, 500);
			cell1.setCellStyle(fS_Header);
			colIndex++;
			cell2 = row.createCell(colIndex);
			cell2.setCellValue("");
			sheet.setColumnWidth(colIndex, 500);
			cell2.setCellStyle(fS_Header);
			colIndex++;	
			cell3 = row.createCell(colIndex);
			cell3.setCellValue("");
			sheet.setColumnWidth(colIndex, 2000);
			cell3.setCellStyle(fS_Header);
			colIndex++;	
			cell4 = row.createCell(colIndex);
			cell4.setCellValue("Head");
			sheet.setColumnWidth(colIndex, 7000);
			cell4.setCellStyle(fS_Header);
			colIndex++;	
			cell3a = row.createCell(colIndex);
			cell3a.setCellValue("Fund Recd");
			sheet.setColumnWidth(colIndex, 4000);
			cell3a.setCellStyle(fS_Header);
			colIndex++;	
			XSSFCell cell5;
			XSSFCell cell5a;
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				cell5s = row.createCell(colIndex);
				cell5s.setCellValue("SUS No");
				sheet.setColumnWidth(colIndex, 3500);
				colIndex++;
				cell5a = row.createCell(colIndex);
				cell5a.setCellValue("Budget Holder Adm");
				sheet.setColumnWidth(colIndex, 3500);
				colIndex++;	
				cell5 = row.createCell(colIndex);
				cell5.setCellValue("Budget Holder");
				sheet.setColumnWidth(colIndex, 5500);
				cell5.setCellStyle(fS_Header);
				colIndex++;
			}	
			cell6 = row.createCell(colIndex);
			cell6.setCellValue("Prev Yr Allocation");
			sheet.setColumnWidth(colIndex, 4000);
			cell6.setCellStyle(fS_Header);
			colIndex++;	
			cell7 = row.createCell(colIndex);
			cell7.setCellValue("Cur Yr Projection");
			sheet.setColumnWidth(colIndex, 4000);
			cell7.setCellStyle(fS_Header);      
			colIndex++;
			cell8 = row.createCell(colIndex);
			cell8.setCellValue("Cur Yr Allocation             .");
			sheet.setColumnWidth(colIndex, 4500);
			cell8.setCellStyle(fS_Header);
			colIndex++;
			XSSFCell cell8a = row.createCell(colIndex);
			cell8a.setCellValue(drfrpt.get(0).get(19));
			sheet.setColumnWidth(colIndex, 1500);
			colIndex++;
			
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				XSSFCell cell8i = row.createCell(colIndex+1);
				cell8i.setCellValue("Instr : Pl Change / Fill only in the Cells have LIGHT GREEN background as All other Cells are locked.");
				sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIndex+1,colIndex+3));
				cell8i.setCellStyle(fS_Header);
				colIndex++;
			}
			rowIndex++;
			String tmp="";
			String tmp3a="";    
			String tmp5="";
			String tmp6="";
			String tmp0="";
			String tmp1="";
			String tmp2="";
			String tmp3="";
			String tmp7="";
			String tmp8="";
			int dnonval=1;
			if (m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {		
				dnonval=1;
			}
			int innercol=0;
			int rowDIndex=0;
			int totDIndex1=0;
			Double nHdFund=0.00;
			
			SheetConditionalFormatting scf_sum = sheet.getSheetConditionalFormatting();
			ConditionalFormattingRule rule_scf_sum = null;
			PatternFormatting fill_scf_sum = null;
			ConditionalFormattingRule[] cfr_sum = null;
			CellRangeAddress[] range_sum =null;
			
			if (!drfrpt.get(0).get(0).equals("NIL")) {
			int colIdx = 3;
			while (rowDIndex < drfrpt.size()) {
				colIdx = 3;			
				if (!drfrpt.get(rowDIndex).get(1).equalsIgnoreCase(tmp0)) {				
					row = sheet.createRow(rowIndex);				
					cellh1 = row.createCell(0);
					cellh1.setCellValue(rolesus);
					if (drfrpt.get(rowDIndex).get(7).length()>0) {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("Y");
					} else {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("N");
					}
					cellh3 = row.createCell(2);
					cellh3.setCellValue(drfrpt.get(rowDIndex).get(0));
					
					
					cell0 = row.createCell(colIdx);
					cell0.setCellValue(drfrpt.get(rowDIndex).get(1)+" - "+drfrpt.get(rowDIndex).get(2));
					tmp0=drfrpt.get(rowDIndex).get(1);
					sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIdx,colIdx+4));
					
					XSSFCell cellh1a = row.createCell(14);
					cellh1a.setCellValue("Amt in "+drfrpt.get(rowDIndex).get(19));
					cellh1a.setCellStyle(fS_fnum);
					
					
					rowIndex++;
				} else {
					cell0 = row.createCell(colIdx);
					cell0.setCellValue("");
				}			
				colIdx++;
				if (!drfrpt.get(rowDIndex).get(3).equalsIgnoreCase(tmp1)) {
					row = sheet.createRow(rowIndex);
					
					cellh1 = row.createCell(0);
					cellh1.setCellValue(rolesus);
					if (drfrpt.get(rowDIndex).get(7).length()>0) {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("Y");
					} else {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("N");
					}
					cellh3 = row.createCell(2);
					cellh3.setCellValue(drfrpt.get(rowDIndex).get(0));
					
					cell1 = row.createCell(colIdx);
					cell1.setCellValue(drfrpt.get(rowDIndex).get(3)+" - "+drfrpt.get(rowDIndex).get(4));
					tmp1=drfrpt.get(rowDIndex).get(3);
					sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIdx,colIdx+3));
					rowIndex++;				
				} else {
					cell1 = row.createCell(colIdx);
					cell1.setCellValue("");
				}
				colIdx++;
				if (!drfrpt.get(rowDIndex).get(5).equalsIgnoreCase(tmp2)) {
					row = sheet.createRow(rowIndex);
					
					cellh1 = row.createCell(0);
					cellh1.setCellValue(rolesus);
					if (drfrpt.get(rowDIndex).get(7).length()>0) {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("Y");
					} else {
						cellh2 = row.createCell(1);
						cellh2.setCellValue("N");
					}
					cellh3 = row.createCell(2);
					cellh3.setCellValue(drfrpt.get(rowDIndex).get(0));
					
					cell2 = row.createCell(colIdx);
					cell2.setCellValue(drfrpt.get(rowDIndex).get(5)+" - "+drfrpt.get(rowDIndex).get(6));
					tmp2=drfrpt.get(rowDIndex).get(5);
					sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIdx,colIdx+2));
					rowIndex++;
				} else {
					cell2 = row.createCell(colIdx);
					cell2.setCellValue("");
				}
							
				row = sheet.createRow(rowIndex);
				
				cellh1 = row.createCell(0);
				cellh1.setCellValue(rolesus);
				if (drfrpt.get(rowDIndex).get(7).length()>0) {
					cellh2 = row.createCell(1);
					cellh2.setCellValue("Y");
				} else {
					cellh2 = row.createCell(1);
					cellh2.setCellValue("N");
				}
				cellh3 = row.createCell(2);
				cellh3.setCellValue(drfrpt.get(rowDIndex).get(0));
				
				if (!drfrpt.get(rowDIndex).get(0).equalsIgnoreCase(tmp3a)) {
				
					cell0 = row.createCell(colIdx);
					tmp0=drfrpt.get(rowDIndex).get(1);
					colIdx++;
					cell3 = row.createCell(colIdx);
					cell3.setCellValue(drfrpt.get(rowDIndex).get(7));
					colIdx++;
					cell4 = row.createCell(colIdx);
					cell4.setCellValue(drfrpt.get(rowDIndex).get(8));
					cell4.setCellStyle(fS_wtext);
					colIdx++;
					totDIndex1=rowIndex;
					XSSFCell cell4t;
					nHdFund=Double.parseDouble(drfrpt.get(rowDIndex).get(17))/dnonval;
					if (nHdFund>0) {
						cell4t = row.createCell(colIdx);
						cell4t.setCellValue(nHdFund);
						cell4t.setCellStyle(fS_fund);
					} else {
						cell4t = row.createCell(colIdx);      
						cell4t.setCellValue(Double.parseDouble("0.00"));	
						if (hqLvl[0].equalsIgnoreCase("-1")) {
							char celltotcol1=(char) (colIdx + 65);
							char celltotcol1z=(char) ((colIdx+6) + 65);
							String celltotFormula1 = "sum("+celltotcol1z+(rowIndex+1)+")";  //abc
							cell4t.setCellFormula(celltotFormula1);
						}
						cell4t.setCellStyle(fS_fund);
					}
					tmp3a=drfrpt.get(rowDIndex).get(0);
					colIdx++;
				} else {
					colIdx++;
					colIdx++;
					colIdx++;
					colIdx++;				
				}
				if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
					cell5s = row.createCell(colIdx);
					cell5s.setCellValue(drfrpt.get(rowDIndex).get(14));
					colIdx++;
					cell5a = row.createCell(colIdx);
					cell5a.setCellValue(drfrpt.get(rowDIndex).get(16));	
					colIdx++;
					cell5 = row.createCell(colIdx);
					cell5.setCellValue(drfrpt.get(rowDIndex).get(15));
					cell5.setCellStyle(fS_wtext);				
					colIdx++;
				}

				cell6 = row.createCell(colIdx);
				tmp6=drfrpt.get(rowDIndex).get(11);
				if (!(tmp6.length()<=0 || tmp6.equalsIgnoreCase("NULL") || tmp6.equalsIgnoreCase(null) || tmp6.equalsIgnoreCase(""))) {
					if (Double.parseDouble(drfrpt.get(rowDIndex).get(11))>0) {
						cell6.setCellValue(Double.parseDouble(drfrpt.get(rowDIndex).get(11))/dnonval);
						cell6.setCellStyle(fS_num);
					}
				} else {
					cell6.setCellValue(0);
				}
				colIdx++;
				tmp7=drfrpt.get(rowDIndex).get(12);
				cell7 = row.createCell(colIdx);
				if (!(tmp7.length()<=0 || tmp7.equalsIgnoreCase("NULL") || tmp7.equalsIgnoreCase(null) || tmp7.equalsIgnoreCase(""))) {
					if (Double.parseDouble(drfrpt.get(rowDIndex).get(12))>0) {
						cell7.setCellValue(Double.parseDouble(drfrpt.get(rowDIndex).get(12))/dnonval);
						cell7.setCellStyle(fS_num);
					}
				} else {
					cell7.setCellValue(0);
				}
				
				
				colIdx++;
				cell8 = row.createCell(colIdx);
				DecimalFormat df;
				if (drfrpt.get(0).get(19).equalsIgnoreCase("CR")){
					df=new DecimalFormat("#######0.00000");	
				} else {
					df=new DecimalFormat("#######0.00");
				}
				
				

				tmp8=drfrpt.get(rowDIndex).get(13);
				if (!(tmp8.length()<=0 || tmp8.equalsIgnoreCase("NULL") || tmp8.equalsIgnoreCase(null) || tmp8.equalsIgnoreCase(""))) {
						//if (Double.parseDouble(drfrpt.get(rowDIndex).get(13))>0) {
							cell8.setCellValue(Double.parseDouble(df.format(Double.parseDouble(drfrpt.get(rowDIndex).get(13))/dnonval)));
							cell8.setCellStyle(fS_fnum);
							cell8.setCellType(CellType.NUMERIC);
						//}
				} else {
					cell8.setCellValue(Double.parseDouble("0.00"));
					cell8.setCellStyle(fS_fnum);
				}
				
				
				if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
					cell8.setCellStyle(unlockCellStyle);
					
				}
				
				String cellflagFormula1 = "IF(B"+(rowIndex+1)+"=\"E\",\"Error\",\"\")";			
				row.createCell(colIdx+1).setCellFormula(cellflagFormula1);
				
				
				
				rowIndex++;			
				rowDIndex++;
				if ((m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) || (rowDIndex == drfrpt.size())) {							
					try {
						if ((!drfrpt.get(rowDIndex).get(0).equalsIgnoreCase(tmp3a) && (rowIndex-(totDIndex1+1))>0) || (rowDIndex == drfrpt.size()) ) {						
							char celltotcol0=(char) ((colIdx) + 65);
							char celltotcol0a0=(char) ((colIdx-6) + 65);
							char celltotcol0a=(char) ((colIdx-8) + 65);
							//String celltotFormula0 = "OR("+celltotcol0+(rowIndex+1)+">"+celltotcol0a0+(totDIndex1+1)+","+celltotcol0+(rowIndex+1)+"<0)";
							String celltotFormula0 = ""+celltotcol0+(rowIndex+1)+">"+celltotcol0a0+(totDIndex1+1)+"";
							String celltotFormula0a = ""+celltotcol0a+(totDIndex1+1)+":"+celltotcol0a0+(totDIndex1+1)+"";
							//String cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"N\")";
							//String cellcsfflagFormula= "IF(("+celltotFormula0+"),\"E\","+cellflagFormula+")";
							String cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"Y\")";
							
							String cellcsfflagFormula= "IF("+celltotFormula0+",\"E\","+cellflagFormula+")";
							
							row = sheet.getRow(rowIndex-1);
							row.getCell(1).setCellFormula(cellcsfflagFormula);
							
							rule_scf_sum = scf_sum.createConditionalFormattingRule(celltotFormula0);
							fill_scf_sum = rule_scf_sum.createPatternFormatting();
							fill_scf_sum.setFillBackgroundColor(IndexedColors.RED.index);
							fill_scf_sum.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
							cfr_sum = new ConditionalFormattingRule[] { rule_scf_sum };
							range_sum = new CellRangeAddress[] { CellRangeAddress.valueOf(celltotFormula0a) };
							scf_sum.addConditionalFormatting(range_sum, cfr_sum);
							
							row = sheet.createRow(rowIndex);
							XSSFCell celltot1 = row.createCell(colIdx);
							char celltotcol1=(char) (colIdx + 65);
							String celltotFormula1 = "SUM("+celltotcol1+(totDIndex1+1)+":"+celltotcol1+rowIndex+")";
							celltot1.setCellFormula(celltotFormula1);						
							celltot1.setCellStyle(fS_sum);
							
							if (hqLvl[0].equalsIgnoreCase("-1")) {
								XSSFCell celltot1a=sheet.getRow(totDIndex1).getCell(colIdx-6);
								celltot1a.setCellFormula(celltotFormula1);
							}
							
							XSSFCell celltot_1 = row.createCell(colIdx-1);
							char celltotcol_1=(char) ((colIdx-1) + 65);
							String celltotFormula_1 = "SUM("+celltotcol_1+(totDIndex1+1)+":"+celltotcol_1+rowIndex+")";
							celltot_1.setCellFormula(celltotFormula_1);
							celltot_1.setCellStyle(fS_sum);
							
							XSSFCell celltot_2 = row.createCell(colIdx-2);
							char celltotcol_2=(char) ((colIdx-2) + 65);
							String celltotFormula_2 = "SUM("+celltotcol_2+(totDIndex1+1)+":"+celltotcol_2+rowIndex+")";
							celltot_2.setCellFormula(celltotFormula_2);
							celltot_2.setCellStyle(fS_sum);
							
							
							XSSFCell celltot_t = row.createCell(colIdx-6);
							celltot_t.setCellValue("Total of "+drfrpt.get(rowDIndex-1).get(7));
							celltot_t.setCellStyle(fS_sum);
							rowIndex++;
							
							///// new added by narendra to remove -ve error
								celltotFormula0 = ""+celltotcol0+(totDIndex1+1)+">"+celltotFormula1+"";

								celltotFormula0 = ""+celltotFormula1+">"+celltotcol0a0+(totDIndex1+1)+"";
								
								
								celltotFormula0a = ""+celltotcol0a+(totDIndex1+1)+":"+celltotcol0a0+(totDIndex1+1)+"";
								cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"Y\")";
								cellcsfflagFormula= "IF("+celltotFormula0+",\"E\","+cellflagFormula+")";							
								row = sheet.getRow(rowIndex-3);
								row.getCell(1).setCellFormula(cellcsfflagFormula);
							
							
						} else {
							char celltotcol0=(char) ((colIdx) + 65);
							char celltotcol01=(char) ((colIdx+1) + 65);
							char celltotcol0a0=(char) ((colIdx-6) + 65);
							char celltotcol0a=(char) ((colIdx-8) + 65);
							//String celltotFormula0 = "OR("+celltotcol0+(totDIndex1+1)+">"+celltotcol0a0+(totDIndex1+1)+","+celltotcol0+(totDIndex1+1)+"<0)";
							String celltotFormula0 = ""+celltotcol0+(totDIndex1+1)+">"+     celltotcol0a0+(totDIndex1+1)   +"";
							String celltotFormula0a = ""+celltotcol0a+(totDIndex1+1)+":"+celltotcol0a0+(totDIndex1+1)+"";
							rule_scf_sum = scf_sum.createConditionalFormattingRule(celltotFormula0);
							fill_scf_sum = rule_scf_sum.createPatternFormatting();
							fill_scf_sum.setFillBackgroundColor(IndexedColors.RED.index);
							fill_scf_sum.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
							cfr_sum = new ConditionalFormattingRule[] { rule_scf_sum };
							range_sum = new CellRangeAddress[] { CellRangeAddress.valueOf(celltotFormula0a) };
							scf_sum.addConditionalFormatting(range_sum, cfr_sum);
							if (hqLvl[0].equalsIgnoreCase("-1")) {
								try {
									XSSFCell celltot1a=sheet.getRow(rowIndex-1).getCell(colIdx-6);
									double celltot1a1=sheet.getRow(rowIndex-1).getCell(colIdx).getNumericCellValue();
									celltot1a.setCellValue(celltot1a1);
								} catch (NullPointerException n) {
									XSSFCell celltot1a = sheet.getRow(rowIndex-1).createCell(colIdx-6);
									celltot1a.setCellValue(0);
								}
							}						
							
							//String cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"N\")";
							//String cellcsfflagFormula= "IF(("+celltotFormula0+"),\"E\","+cellflagFormula+")";
							
							///narendra
							String cellflagFormula="N";
							if (nUnty.equalsIgnoreCase("-1")) {								
								cellflagFormula = "IF(OR("+celltotcol0+(rowIndex)+">0,"+celltotcol0+(rowIndex)+"<0),\"Y\",\"N\")";
							} else {
								cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"Y\")";
							}							
							
							
							//celltotFormula1  summation of all similary row
							
							String cellcsfflagFormula= "IF("+celltotFormula0+",\"E\","+cellflagFormula+")";
							
							row = sheet.getRow(rowIndex-1);
							row.getCell(1).setCellFormula(cellcsfflagFormula);
						}
					} catch (IndexOutOfBoundsException e) {
					
					}
					
				}
				
				colIdx++;			
				if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
					if (hqsmflag==0) {
						cellsmhq = sheet.getRow(hqsmrow).createCell(colIndexhq-1);
						cellsmhq.setCellValue("Allotment Summary");
						XSSFCell cellsmhqs = sheet.getRow(hqsmrow).createCell(colIndexhq);
						char celltotcol_sm1=(char) ((colIndexhq) + 65);
						String celltotFormula_hq ="SUM("+celltotcol_sm1+(hqsmrow+3)+":"+celltotcol_sm1+(hqsmrow+500)+")";
						cellsmhqs.setCellFormula(celltotFormula_hq);
						cellsmhqs.setCellStyle(fS_sum);
					} 				
					if (hqsmflag==1) {			
						cellsmhq = sheet.getRow(hqsmrow).createCell(colIndexhq-1);
						cellsmhq.setCellValue("Budget Holder");
						sheet.setColumnWidth(colIndexhq-1, 7000);
						cellsmhq.setCellStyle(fS_smHeader);					
						cellsm = sheet.getRow(hqsmrow).createCell(colIndexhq);
						cellsm.setCellValue("Total Allotment");
						sheet.setColumnWidth(colIndexhq, 4000);
						cellsm.setCellStyle(fS_smHeader);						
					}
					if (hqsmflag>1) {		
						if (rowIndexhq<bhlist.size()) {
							XSSFCell celltot_sm = sheet.getRow(hqsmrow).createCell(colIndexhq);
							char celltotcol_sm=(char) ((colIndexhq) + 65);
							char celltotcol_sm1=(char) ((colIndexhq-1) + 65);
							XSSFCell celltot_smhq = sheet.getRow(hqsmrow).createCell(colIndexhq-1);
							celltot_smhq.setCellValue(bhlist.get(rowIndexhq).get(2));
							celltot_smhq.setCellStyle(fS_wtext);
							String celltotFormula_sm ="SUMPRODUCT(--($L$3:$L$5000="+celltotcol_sm1+(hqsmrow+1)+"),($O$3:$O$5000))";
							celltot_sm.setCellFormula(celltotFormula_sm);
							celltot_sm.setCellStyle(fS_sum);						
							rowIndexhq ++;
						}	
						
					}
					hqsmflag++;
					hqsmrow++;
				}
			}
			
			colIdx--;			
						char celltotcol0=(char) ((colIdx) + 65);
						char celltotcol0a0=(char) ((colIdx-6) + 65);
						char celltotcol0a=(char) ((colIdx-8) + 65);
						//String celltotFormula0 = "OR("+celltotcol0+(rowIndex+1)+">"+celltotcol0a0+(totDIndex1+1)+","+celltotcol0+(rowIndex+1)+"<0)";
						String celltotFormula0 = ""+celltotcol0+(rowIndex+1)+">"+celltotcol0a0+(totDIndex1+1)+"";
						String celltotFormula0a = ""+celltotcol0a+(totDIndex1+1)+":"+celltotcol0a0+(totDIndex1+1)+"";
						//String cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"N\")";
						String cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"Y\")";
						//String cellcsfflagFormula= "IF(("+celltotFormula0+"),\"E\","+cellflagFormula+")";
						String cellcsfflagFormula= "IF("+celltotFormula0+",\"E\","+cellflagFormula+")";
						row = sheet.getRow(rowIndex-1);
						row.getCell(1).setCellFormula(cellcsfflagFormula);
						rule_scf_sum = scf_sum.createConditionalFormattingRule(celltotFormula0);
						fill_scf_sum = rule_scf_sum.createPatternFormatting();
						fill_scf_sum.setFillBackgroundColor(IndexedColors.RED.index);
						fill_scf_sum.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
						cfr_sum = new ConditionalFormattingRule[] { rule_scf_sum };
						range_sum = new CellRangeAddress[] { CellRangeAddress.valueOf(celltotFormula0a) };
						scf_sum.addConditionalFormatting(range_sum, cfr_sum);
						row = sheet.createRow(rowIndex);
						XSSFCell celltot1 = row.createCell(colIdx);
						char celltotcol1=(char) (colIdx + 65);
						String celltotFormula1 = "SUM("+celltotcol1+(totDIndex1+1)+":"+celltotcol1+rowIndex+")";
						celltot1.setCellFormula(celltotFormula1);						
						celltot1.setCellStyle(fS_sum);
						
						if (hqLvl[0].equalsIgnoreCase("-1")) {
							XSSFCell celltot1a=sheet.getRow(totDIndex1).getCell(colIdx-6);
							celltot1a.setCellFormula(celltotFormula1);
						}
						
						XSSFCell celltot_1 = row.createCell(colIdx-1);
						char celltotcol_1=(char) ((colIdx-1) + 65);
						String celltotFormula_1 = "SUM("+celltotcol_1+(totDIndex1+1)+":"+celltotcol_1+rowIndex+")";
						celltot_1.setCellFormula(celltotFormula_1);
						celltot_1.setCellStyle(fS_sum);
						
						XSSFCell celltot_2 = row.createCell(colIdx-2);
						char celltotcol_2=(char) ((colIdx-2) + 65);
						String celltotFormula_2 = "SUM("+celltotcol_2+(totDIndex1+1)+":"+celltotcol_2+rowIndex+")";
						celltot_2.setCellFormula(celltotFormula_2);
						celltot_2.setCellStyle(fS_sum);
						
						
						XSSFCell celltot_t = row.createCell(colIdx-6);
						celltot_t.setCellValue("Total of "+drfrpt.get(rowDIndex-1).get(7));
						celltot_t.setCellStyle(fS_sum);
						rowIndex++;		
						
						/*///// newly added to remove -ve error
						celltotFormula0 = ""+celltotcol0+(totDIndex1+1)+">"+celltotFormula1+"";

						celltotFormula0 = ""+celltotFormula1+">"+celltotcol0a0+(totDIndex1+1)+"";
						
						
						celltotFormula0a = ""+celltotcol0a+(totDIndex1+1)+":"+celltotcol0a0+(totDIndex1+1)+"";
						cellflagFormula = "IF("+celltotcol0+(rowIndex)+">0,\"Y\",\"Y\")";
						cellcsfflagFormula= "IF("+celltotFormula0+",\"E\","+cellflagFormula+")";							
						row = sheet.getRow(rowIndex-3);
						row.getCell(1).setCellFormula(cellcsfflagFormula);*/
						
			}  
			
			sheet.setColumnHidden(0,true);
			sheet.setColumnHidden(1,true);
			sheet.setColumnHidden(2,true);      
			if (m1_rptLvl.equalsIgnoreCase("BBHDSUMM") || m1_rptLvl.equalsIgnoreCase("DFNDALLOT")) {
				sheet.setColumnHidden(9,true);
				sheet.setColumnHidden(10,true);
			}		
			sheet.createFreezePane( 0, 2);

			try (ByteArrayOutputStream waterMark = createWaterMark(usrid+"  "+dateString)) {
				sheet = putWatermarkToExcel(sheet, waterMark.toByteArray());
	        }
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition","attachment;filename=" + fname);
			response.setHeader("Expires:", "0");
			wb.write(response.getOutputStream());
			wb.close();
		}
	  
	  public ByteArrayOutputStream createWaterMark(String content) throws IOException {
			int width = 250;
			int height = 150;
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			String fontType = "Microsoft Yahei";
			int fontStyle = java.awt.Font.BOLD;
			int fontSize = 15;
			java.awt.Font font = new java.awt.Font(fontType, fontStyle, fontSize);
			Graphics2D g2d = image.createGraphics(); 
			image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
			g2d.dispose();
			g2d = image.createGraphics();
			g2d.setColor(new Color(236, 88, 104, 80));
			g2d.setStroke(new BasicStroke(1));
			g2d.setFont((java.awt.Font) font);
			g2d.rotate(-0.5, (double) image.getWidth() / 2, (double) image.getHeight() / 2);
			FontRenderContext context = g2d.getFontRenderContext();
			Rectangle2D bounds = ((java.awt.Font) font).getStringBounds(content, context);
			double x = (width - bounds.getWidth()) / 2;
			double y = (height - bounds.getHeight()) / 2;
			double ascent = -bounds.getY();
			double baseY = y + ascent;
			g2d.drawString(content, (int) x, (int) baseY);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g2d.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "png", os);
			return os;
		}

		public XSSFSheet putWatermarkToExcel(XSSFSheet sheet, byte[] bytes) {
			XSSFWorkbook workbook = sheet.getWorkbook();
			int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			String rID = sheet.addRelation(null, XSSFRelation.IMAGES, workbook.getAllPictures().get(pictureIdx))
					.getRelationship().getId();
			sheet.getCTWorksheet().addNewPicture().setId(rID);
			return sheet;
		}
		
		
		@SuppressWarnings("deprecation")
		@RequestMapping(value = "/admin/fp_cr_dft_word")
		public void fp_cr_dft_word(@ModelAttribute("filename") @RequestParam(value = "msg", required = false) String msg, String m1_lvl,String m1_rptLvl,ModelMap model,HttpSession sessionA,HttpServletRequest request, HttpServletResponse response) throws IOException{
			String usrid = sessionA.getAttribute("username").toString();
			String roleid = sessionA.getAttribute("roleid").toString();
			String rolesus=sessionA.getAttribute("roleSusNo").toString();
			String rolefmn=sessionA.getAttribute("roleFormationNo").toString();
			ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
			String m1lvl=m1_lvl;
			String m1rptLvl=m1_rptLvl.replaceAll(",","','");
			nDbData=fp_rptDao.getDOLetterData(m1lvl,m1rptLvl);
			File file =null;
			String fileName = ""+m1_lvl+"_Letters.docx";
			//String fileName = "nktest.docx";
			
					XWPFDocument doc = new XWPFDocument();
					try {
						// create table with 3 rows and 4 columns
						/*XWPFTable table = doc.createTable(3, 4);
						
						// write to first row, first column
						XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs().get(0);
						p1.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun r1 = p1.createRun();
						r1.setBold(true);
						r1.setText("ID");
						System.out.println("fp_cr_dft_word-3");
						// write to first row, second column
						XWPFParagraph p2 = table.getRow(0).getCell(1).getParagraphs().get(0);
						p2.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun r2 = p2.createRun();
						r2.setBold(true);
						r2.setText("First Name");
						System.out.println("fp_cr_dft_word-4");
						// write to first row, third column
						XWPFParagraph p3 = table.getRow(0).getCell(2).getParagraphs().get(0);
						p3.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun r3 = p3.createRun();
						r3.setBold(true);
						r3.setText("Last Name");
						System.out.println("fp_cr_dft_word-5");
						// write to first row, fourth column
						XWPFParagraph p4 = table.getRow(0).getCell(3).getParagraphs().get(0);
						p4.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun r4 = p4.createRun();
						r4.setBold(true);
						r4.setText("Email");
						System.out.println("fp_cr_dft_word-6");
						// write to second row
						table.getRow(1).getCell(0).setText("1000");
						table.getRow(1).getCell(1).setText("Soumitra");
						table.getRow(1).getCell(2).setText("Roy");
						table.getRow(1).getCell(3).setText("email@email.com");
						System.out.println("fp_cr_dft_word-7");
						// write to third row
						table.getRow(2).getCell(0).setText("1001");
						table.getRow(2).getCell(1).setText("John");
						table.getRow(2).getCell(2).setText("Joe");
						table.getRow(2).getCell(3).setText("email@email.com");
						System.out.println("fp_cr_dft_word-8");*/
						
						for(int i=0;i<nDbData.size();i++) {
							XWPFParagraph p1 = doc.createParagraph();
							p1.setSpacingBefore(0);
							p1.setSpacingBeforeLines(0);
							p1.setSpacingAfterLines(0);
							p1.setSpacingAfter(0);							
							p1.setAlignment(ParagraphAlignment.LEFT);													
							p1.setWordWrapped(true);
							
							XWPFRun r1 = p1.createRun();
							String t1 = nDbData.get(i).get(1)+" "+nDbData.get(i).get(2);
							r1.setText(t1);
							
							//r1.addCarriageReturn();
							XWPFParagraph p2 = doc.createParagraph();
							p2.setAlignment(ParagraphAlignment.LEFT);
							p2.setSpacingBefore(0);
							p2.setSpacingBeforeLines(0);
							p2.setSpacingAfterLines(0);
							p2.setSpacingAfter(0);							
							p2.setAlignment(ParagraphAlignment.LEFT);													
							p2.setWordWrapped(true);							
							XWPFRun r2 = p2.createRun();
							String t2 = nDbData.get(i).get(3);
							r2.setText(t2);
							//r2.addCarriageReturn();
							XWPFParagraph p3 = doc.createParagraph();
							p3.setAlignment(ParagraphAlignment.LEFT);						
							p3.setWordWrapped(true);						
							XWPFRun r3 = p3.createRun();
							String t3 = nDbData.get(i).get(4);
							r3.setText(t3);
							//r3.addCarriageReturn();
							XWPFParagraph p4 = doc.createParagraph();
							/*p4.setAlignment(ParagraphAlignment.LEFT);						
							p4.setWordWrapped(true);						
							XWPFRun r4 = p4.createRun();
							String t4 = nDbData.get(i).get(5);
							r4.setText(t4);*/
							//r4.addCarriageReturn();
							//r4.addCarriageReturn();
							//r4.addCarriageReturn();
							XWPFParagraph phead = doc.createParagraph();							
							phead.setAlignment(ParagraphAlignment.CENTER);						
							phead.setWordWrapped(true);						
							XWPFRun rhead = phead.createRun();
							rhead.addCarriageReturn();
							rhead.addCarriageReturn();
							String thead = "DSSC Exam";
							rhead.setText(thead);
							rhead.setBold(true);
							rhead.addCarriageReturn();
							rhead.addCarriageReturn();
							rhead.addCarriageReturn();
							
							
							XWPFParagraph ph1= doc.createParagraph();
							ph1.setAlignment(ParagraphAlignment.LEFT);						
							ph1.setWordWrapped(true);						
							XWPFRun rh1= ph1.createRun();
							String th1 = "1.    DSSC Course is an Important Facet in the career progression of young offrs.";
							rh1.setText(th1);
							//rh1.addCarriageReturn();
							
							XWPFParagraph ph2= doc.createParagraph();
							ph2.setAlignment(ParagraphAlignment.LEFT);						
							ph2.setWordWrapped(true);						
							XWPFRun rh2= ph2.createRun();
							String th2 = "2.    As you would be appearing for your first mandatory attempt for the course, I wish to emphasize the importance of diligent and meticulous prep.";
							rh2.setText(th2);
							//rh2.addCarriageReturn();
							
							XWPFParagraph ph3= doc.createParagraph();
							ph3.setAlignment(ParagraphAlignment.LEFT);						
							ph3.setWordWrapped(true);						
							XWPFRun rh3= ph3.createRun();
							String th3 = "3.    I am sanguine that you will put in the requisite efforts for clearing the exam. Wishing you the best.";
							rh3.setText(th3);
							rh3.addCarriageReturn();
							
							rh3.addBreak(BreakType.PAGE);
						}
						
						
						/*XWPFParagraph p1 = doc.createParagraph();
						p1.setAlignment(ParagraphAlignment.LEFT);						
						p1.setWordWrapped(true);						
						XWPFRun r1 = p1.createRun();
						String t1 = "To,";
						r1.setText(t1);

						
						
						
						
						
						// create a paragraph with Strike-Through text
						XWPFParagraph p5 = doc.createParagraph();
						// left alignment
						p5.setAlignment(ParagraphAlignment.LEFT);						
						// wrap words
						p5.setWordWrapped(true);						
						// XWPFRun object defines a region of text with a common set of
						// properties
						XWPFRun r5 = p5.createRun();
						String t5 = "Sample Paragraph Post. This is a sample Paragraph post. Sample Paragraph text is being cut and pasted again and again. This is a sample Paragraph post. peru-duellmans-poison-dart-frog.";
						r5.setText(t5);
						System.out.println("fp_cr_dft_word-9");						
						// make StrikeThrough
						//r5.setStrikeThrough(true);
						
						// create a paragraph with Underlined text
						XWPFParagraph p6 = doc.createParagraph();
						
						// left alignment
						p6.setAlignment(ParagraphAlignment.LEFT);
						
						// wrap words
						p6.setWordWrapped(true);
						
						// XWPFRun object defines a region of text with a common set of
						// properties
						XWPFRun r6 = p6.createRun();
						String t6 = "Sample Paragraph Post. This is a sample Paragraph post. Sample Paragraph text is being cut and pasted again and again. This is a sample Paragraph post. peru-duellmans-poison-dart-frog.";
						r6.setText(t6);
						
						// make Underlined
						r6.setUnderline(UnderlinePatterns.SINGLE);
						System.out.println("fp_cr_dft_word-10");
						
						
						
						
*/						
						file = new File("c:"+File.separator+"miso"+File.separator,fileName);
						String FilePath = "c:"+File.separator+"miso"+File.separator;
						File dir = new File(FilePath);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						
						String fname = dir.getAbsolutePath() + File.separator + fileName;

						file = new File(fname);
						
						OutputStream out = null;
						try {
							out = new FileOutputStream(file);
							doc.write(out);
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} finally {
						try {
							/*System.out.println("fp_cr_dft_word-12-"+file);								
							response.setContentType("application/ms-word");
							response.setHeader("Content-disposition","attachment;filename=" + file);
							response.setHeader("Expires:", "0");
							doc.write(response.getOutputStream());*/
							doc.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					msg="Pl Find the Created File at "+file;
					model.put("msg", msg);
					//return new ModelAndView("fp_DOLettersTiles");
					
		}
		
		@RequestMapping(value = "/admin/fpDOLetterss", method = RequestMethod.POST)
		public ModelAndView fpDOLetterss(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,String nPara) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("fplogininfo", roleid);
			/*if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				////return new ModelAndView("redirect:bodyParameterNotAllow");
			}*/
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String rolefmn=session.getAttribute("roleFormationNo").toString();
			int fy = Year.now().getValue();
			String fin_year = String.valueOf(fy-1) ;
			String tr_head = "";
			String nUnty=fp_trDao.getUnitType("",session,"FMN_"+rolefmn);
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("nPara", nPara);
			ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
			//nDbData = fp_rpDao.nLoginInfoData(session, "", "", "HEADDT", "HQ", nPara, "1","");
			Mmap.put("nList", nDbData);
			Mmap.put("msg", msg);
			return new ModelAndView("fp_DOLettersTiles");
		}
		
		@RequestMapping(value = "/fpdoletterdata", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> fpdoletterdata(HttpSession sessionA,String m1_lvl,String m1_rptLvl) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			String nUsrId=sessionA.getAttribute("userId").toString();
			ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
			String m1lvl=m1_lvl;
			String m1rptLvl="";
			if(!m1_rptLvl.equalsIgnoreCase(null)) {
				m1rptLvl=m1_rptLvl.replaceAll(",","','");
			} else {
				m1rptLvl="";
			}
			nDbData=fp_rptDao.getDOLetterData(m1lvl,m1rptLvl);
			return nDbData;
		}
		
		@RequestMapping(value = "/admin/fp_cr_dft_wordData")
		public @ResponseBody ArrayList<ArrayList<String>> fp_cr_dft_wordData (String m1_lvl,String m1_rptLvl,ModelMap model,HttpSession sessionA,HttpServletRequest request, HttpServletResponse response) {
			String usrid = sessionA.getAttribute("username").toString();
			String roleid = sessionA.getAttribute("roleid").toString();
			String rolesus=sessionA.getAttribute("roleSusNo").toString();
			String rolefmn=sessionA.getAttribute("roleFormationNo").toString();
			ArrayList<ArrayList<String>> nDbData = new ArrayList<ArrayList<String>>();
			String m1lvl=m1_lvl;
			String m1rptLvl=m1_rptLvl.replaceAll(",","','");
			nDbData=fp_rptDao.getDOLetterData(m1lvl,m1rptLvl);		
			return nDbData;
		}
		
}