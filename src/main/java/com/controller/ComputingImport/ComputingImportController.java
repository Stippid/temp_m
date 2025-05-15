package com.controller.ComputingImport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
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

import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.Assets_Child;
import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ComputingImportController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	@RequestMapping(value = "/Computingimportdata_Url",method = RequestMethod.GET)
	public ModelAndView Computingimportdata_Url(ModelMap model,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Computingimportdata_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		return new ModelAndView("computingimportdataTiles","ComputingImportData_cmd", new Assets_Main());
	}
	
	
	@RequestMapping(value = "/ComputingImportData_Action" ,method = RequestMethod.POST )
	public ModelAndView ComputingImportData_Action( @ModelAttribute("ComputingImportData_cmd") Assets_Main pudm, BindingResult result,
			@RequestParam(value = "excelfile", required = false) MultipartFile excelfile,@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Computingimportdata_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
			String username = session.getAttribute("username").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			int countrow = Integer.parseInt(request.getParameter("countrow"));
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			 
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			 
			 Date date = new Date();
			
			 
			
			
			try{
							
				Assets_Child C_asset=new Assets_Child();
							
				for(int i=0;i<countrow;i++){
					
					
					Date warrenty_dt = null;
					 Date unsv_from_dt1 = null;
					 Date proc_dt = null;

					 
					 
					 String warranty_dt_s =request.getParameter("warranty_upto_"+i);
					 String unsv_from_dt1_s =request.getParameter("un-servicable_from_"+i);
					 String proc_dt_s= request.getParameter("proc_date_"+i);
					 
					 
					 if(warranty_dt_s!=null && !warranty_dt_s.equals("") && !warranty_dt_s.equals("yyyy-MM-dd"))
					 {
						
						 warrenty_dt = format.parse(request.getParameter("warranty_upto_"+i));
					 }
					 
					 
					 if(unsv_from_dt1_s!=null && !unsv_from_dt1_s.equals("") && !unsv_from_dt1_s.equals("yyyy-MM-dd"))
					 {
						 unsv_from_dt1 = format.parse(request.getParameter("un-servicable_from_"+i));
					 }
					 
					 
					 if(proc_dt_s!=null && !proc_dt_s.equals("") && !proc_dt_s.equals("yyyy-MM-dd"))
					 {
						 proc_dt = format.parse(request.getParameter("proc_date_"+i));
					 }
					
				
					
					 
					
					 if(request.getParameter("computing_assets_type_"+i) != null && !request.getParameter("computing_assets_type_"+i).equals(""))
							
						{
						 pudm.setAsset_type(Integer.parseInt(request.getParameter("computing_assets_type_"+i)));
						}
						
					
					
					pudm.setModel_number(request.getParameter("model_number_"+i));
					pudm.setMachine_number(request.getParameter("machine_no_"+i));
					 if(request.getParameter("make/brand_name_"+i) != null && !request.getParameter("make/brand_name_"+i).equals(""))
							
						{
						 pudm.setMake_name(Integer.parseInt(request.getParameter("make/brand_name_"+i)));
						}
					
					 if(request.getParameter("model_name_"+i) != null && !request.getParameter("model_name_"+i).equals(""))
							
						{
						 pudm.setModel_name(Integer.parseInt(request.getParameter("model_name_"+i)));
						}
					
					 if(request.getParameter("antivirus_"+i) != null && !request.getParameter("antivirus_"+i).equals(""))
							
						{
							pudm.setAntivirus(Integer.parseInt(request.getParameter("antivirus_"+i)));
						}
					
					
				
					pudm.setAntiviruscheck(request.getParameter("antivirus_installed_"+i));
					pudm.setB_code(request.getParameter("budget_code_"+i));
					pudm.setB_cost(request.getParameter("proc_cost_"+i));
					pudm.setB_head(request.getParameter("budget_head_"+i));
					pudm.setCd_drive(request.getParameter("cd_drive_"+i));
					if(request.getParameter("os/firmware_activation_and_subsequent_patch_updation_mechanism_"+i) != null && !request.getParameter("os/firmware_activation_and_subsequent_patch_updation_mechanism_"+i).equals(""))
					
					{
						pudm.setOs_patch(Integer.parseInt(request.getParameter("os/firmware_activation_and_subsequent_patch_updation_mechanism_"+i)));
					}

					
					if(request.getParameter("dply_envt_as_applicable_"+i) != null && !request.getParameter("dply_envt_as_applicable_"+i).equals(""))
						
					{
						pudm.setDply_envt(Integer.parseInt(request.getParameter("dply_envt_as_applicable_"+i)));
					}
					
					
					if(request.getParameter("hdd_capacity_"+i) != null && !request.getParameter("hdd_capacity_"+i).equals(""))
						
					{
						pudm.setHdd_capi(Integer.parseInt(request.getParameter("hdd_capacity_"+i)));
					}
					
					
					pudm.setIp_address(request.getParameter("ip_address_"+i));
					pudm.setMac_address(request.getParameter("mac_address_"+i));
					
						if(request.getParameter("office_"+i) != null && !request.getParameter("office_"+i).equals(""))
						
					{
						pudm.setOffice(Integer.parseInt(request.getParameter("office_"+i)));
					}					
					
						
						if(request.getParameter("operating_system_"+i) != null && !request.getParameter("operating_system_"+i).equals(""))
							
						{
							pudm.setOperating_system(Integer.parseInt(request.getParameter("operating_system_"+i)));
						}	
					
						
						if(request.getParameter("processor_type_"+i) != null && !request.getParameter("processor_type_"+i).equals(""))
											   
						{	
							pudm.setProcessor_type(Integer.parseInt(request.getParameter("processor_type_"+i)));
						}
					String ram_capacity =request.getParameter("ram_capacity_"+i);
						if(ram_capacity != null && !ram_capacity.equals(""))	
						{
							pudm.setRam_capi(Integer.parseInt(ram_capacity));
						}
						
						String ram_slot_quantity =request.getParameter("ram_slot_quantity_"+i);
						if(ram_slot_quantity != null && !ram_slot_quantity.equals(""))
							
						{
							pudm.setRam_slot_qty(Integer.parseInt(ram_slot_quantity));
						}
						
					
					pudm.setS_state(request.getParameter("serviceable_state_"+i));
					
					C_asset.setService_state(Integer.parseInt(request.getParameter("serviceable_state_"+i)));
					
					String un_serviceable_state =request.getParameter("un-serviceable_state_"+i);
					if(un_serviceable_state != null && !un_serviceable_state.equals(""))
						
					{
						pudm.setUnserviceable_state(Integer.parseInt(un_serviceable_state));
					}
					
						if(("serviceable_state_"+i).equals("2") || ("serviceable_state_"+i) == "2") {
						
						C_asset.setUnservice_state(pudm.getUnserviceable_state());
						
						//**  BISG AHM **//
						C_asset.setUnsv_from_dt(unsv_from_dt1);
						}
						else if(("serviceable_state_"+i).equals("1") || ("serviceable_state_"+i) == "1"){
							C_asset.setService_state(Integer.parseInt("serviceable_state_"+i));

						}

					pudm.setUnsv_from_dt(unsv_from_dt1);
					pudm.setProc_date(proc_dt);
					pudm.setWarranty(warrenty_dt);
					C_asset.setWarranty(warrenty_dt);
					pudm.setWarrantycheck(request.getParameter("warranty_"+i));
					pudm.setCreated_by(username);
					pudm.setCreated_date(date);
					pudm.setSus_no(roleSusNo);
			

					int c = (Integer) sessionHQL.save(pudm);
					C_asset.setP_id(c);
					sessionHQL.save(C_asset);
                    sessionHQL.flush();
					sessionHQL.clear();
					
			
					
				}
					
				model.put("msg", "Data Saved Successfully.");

					
				tx.commit();
			}catch(RuntimeException e){
				try{
					tx.rollback();
					model.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView("redirect:Computingimportdata_Url");
		}
}
