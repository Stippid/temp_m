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
import com.models.assets.It_Asset_Peripherals;
import com.models.assets.TB_PERIPHERAL_CHILD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class PeripheralImportController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/Peripheralimportdata_Url",method = RequestMethod.GET)
	public ModelAndView Peripheralimportdata_Url(ModelMap model,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Peripheralimportdata_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		model.put("msg", msg);
		
		return new ModelAndView("peripheralimportdataTiles","PeripheralImportData_cmd", new It_Asset_Peripherals());
	}
	
	@RequestMapping(value = "/PeripheralImportData_Action" ,method = RequestMethod.POST )
	public ModelAndView PeripheralImportData_Action( @ModelAttribute("PeripheralImportData_cmd") It_Asset_Peripherals pudm, BindingResult result,
			@RequestParam(value = "excelfile", required = false) MultipartFile excelfile,@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Peripheralimportdata_Url", roleid);
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
							

				 TB_PERIPHERAL_CHILD obj = new TB_PERIPHERAL_CHILD();
							
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
			
					
					
					 if(request.getParameter("peripheral_type_"+i) != null && !request.getParameter("peripheral_type_"+i).equals(""))
							
						{
						 pudm.setAssets_type(Integer.parseInt(request.getParameter("peripheral_type_"+i)));
						}
						
					
					
					pudm.setModel_no(request.getParameter("model_number_"+i));
					pudm.setMachine_no(request.getParameter("machine_no_"+i));
					 if(request.getParameter("make/brand_name_"+i) != null && !request.getParameter("make/brand_name_"+i).equals(""))
							
						{
						 pudm.setMake_name(Integer.parseInt(request.getParameter("make/brand_name_"+i)));
						}
					
					 if(request.getParameter("model_name_"+i) != null && !request.getParameter("model_name_"+i).equals(""))
							
						{
						 pudm.setModel_name(Integer.parseInt(request.getParameter("model_name_"+i)));
						}
					
					
					 if(request.getParameter("type_of_peripheral_hw_"+i) != null && !request.getParameter("type_of_peripheral_hw_"+i).equals(""))
							
						{
							pudm.setType_of_hw(Integer.parseInt(request.getParameter("type_of_peripheral_hw_"+i)));
						}
					 

					 
					 if(request.getParameter("network_features_"+i) != null && !request.getParameter("network_features_"+i).equals(""))
							
						{
						 pudm.setManagement_layer(Integer.parseInt(request.getParameter("management_layer_"+i)));

						}
					 

					 
					 pudm.setDisplay_type(request.getParameter("display_type_"+i)); 
					 pudm.setUps_capacity(request.getParameter("ups_capacity_"+i));  
					 pudm.setNetwork_features(request.getParameter("network_features_"+i));
					 pudm.setHw_interface(request.getParameter("hardware_interface_"+i));
					 pudm.setConnectivity_type(request.getParameter("connectivity_type_"+i));  
					 pudm.setConnectivity_type(request.getParameter("connectivity_type_"+i));   
					 pudm.setPaper_size(request.getParameter("max_paper_size_"+i));   
					 
					 pudm.setMonochrome_color(request.getParameter("monochrome/color_"+i));   
					 pudm.setColour(request.getParameter("colour_"+i));  
					 pudm.setPhotography(request.getParameter("photography_"+i)); 
					 pudm.setScan(request.getParameter("scan_"+i));
					 pudm.setPrint(request.getParameter("print_"+i));
					 pudm.setCapacity(request.getParameter("capacity(lumens)_"+i)); 
					 pudm.setDisplay_connector(request.getParameter("display_connector_"+i));
					 pudm.setDisplay_size(request.getParameter("display_size_"+i));
					 pudm.setResolution(request.getParameter("resolution_"+i));
					 pudm.setNo_of_ports(request.getParameter("no_of_ports_"+i));
					 pudm.setIs_networked(request.getParameter("is_networked_"+i));
					 pudm.setRemarks(request.getParameter("remarks_"+i));
					 pudm.setYear_of_proc(request.getParameter("year_of_proc_"+i));
					 pudm.setYear_of_manufacturing(request.getParameter("year_of_manufacturing_"+i));
					 pudm.setType(request.getParameter("model_type_"+i));
					 pudm.setB_code(request.getParameter("budget_code_"+i));
					 pudm.setB_cost(request.getParameter("proc_cost_"+i));
					 pudm.setB_head(request.getParameter("budget_head_"+i));
					

					if(request.getParameter("ethernet_port_"+i) != null && !request.getParameter("ethernet_port_"+i).equals(""))
						
					{
						pudm.setEthernet_port(Integer.parseInt(request.getParameter("ethernet_port_"+i)));
					}
					
					pudm.setIp_address(request.getParameter("ip_address(for_networked_device)_"+i));
				
					pudm.setS_state(request.getParameter("serviceable_state_"+i));
					
					String un_serviceable_state =request.getParameter("un-serviceable_state_"+i);
					if(un_serviceable_state != null && !un_serviceable_state.equals(""))
						
					{
						pudm.setUnserviceable_state(Integer.parseInt(un_serviceable_state));
					}
					
						obj.setWarranty(warrenty_dt);
	                   //obj.setService_state(Integer.parseInt(("serviceable_state_"+i)));
						obj.setService_state(Integer.parseInt(request.getParameter("serviceable_state_"+i)));
	                   if(("serviceable_state_"+i).equals("2") || ("serviceable_state_"+i) == "2") {
	                   
	                   obj.setUnservice_state(Integer.parseInt(("un-serviceable_state_"+i)));
	                   obj.setUnsv_from_dt(unsv_from_dt1);
	                  
	                   }
					
				
					pudm.setUnsv_from_dt(unsv_from_dt1);
					pudm.setProc_date(proc_dt);
					pudm.setWarranty(warrenty_dt);
					pudm.setWarrantycheck(request.getParameter("warranty_"+i));
				
					pudm.setCreated_by(username);
					pudm.setSus_no(roleSusNo);
					pudm.setCreated_date(date);
					

						int c = (Integer) sessionHQL.save(pudm);
						obj.setP_id(c);
						sessionHQL.save(obj);
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
			return new ModelAndView("redirect:Peripheralimportdata_Url");
		}
}
