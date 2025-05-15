package com.controller.Hardware;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.dao.Assets.Peripheral_Hardware_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class HardwarePeripherals {
	
	@Autowired
	private  Peripheral_Hardware_DAO cd;
	
	It_CommonController it_comm = new It_CommonController();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	
	
	@RequestMapping(value = "/admin/Search_PeripheralHardwareUrl", method = RequestMethod.GET)
	public ModelAndView Search_PeripheralHardwareUrl(ModelMap Mmap, HttpSession session,
			HttpServletRequest request,
			 @RequestParam(value = "msg", required = false) String msg)
  {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_PeripheralHardwareUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}


			  Mmap.put("msg", msg);
			  Mmap.put("status1", "0");
			  Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
			  Mmap.put("getPeripheral", it_comm.getPeripheral());

		return new ModelAndView("SearchHardwarePeripheralTiles");
	}
	
	@RequestMapping(value = "/getFilteredPeripheral12", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredPeripheral12(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,HttpSession sessionUserId) throws SQLException {
	
		
		return cd.Search_Peripheral(startPage, pageLength,Search,orderColunm,orderType,status,assets_type,year_of_manufacturing,
				machine_make,machine_no,model_no,from_date,to_date,sessionUserId);
	}


	@RequestMapping(value = "/getTotalPeripheralCount12", method = RequestMethod.POST)
	public @ResponseBody long getTotalPeripheralCount12(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,HttpSession sessionUserId) throws SQLException {
		
		return cd.getPeripheralCountList(Search, orderColunm, orderType, status,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date
				,to_date, sessionUserId);
	}
	
	
	@RequestMapping(value = "/admin/Search_PeripheralUrl_12", method = RequestMethod.POST)
	public ModelAndView Search_PeripheralUrl_12(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_make1", required = false) String machine_make,
			@RequestParam(value = "machine_no1", required = false) String machine_no,
			@RequestParam(value = "model_no1", required = false) String model_no,
			@RequestParam(value = "year_of_manufacturing1", required = false) String year_of_manufacturing,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status){
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralHardwareUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		    Mmap.put("machine_make1", machine_make);
		    Mmap.put("machine_no1", machine_no);
		    Mmap.put("model_no1", model_no);
		    Mmap.put("from_date1", from_date);
		    Mmap.put("to_date1", to_date);
		    Mmap.put("assets_type1", assets_type);
		    Mmap.put("status1", status);
		    Mmap.put("year_of_manufacturing1", year_of_manufacturing);
		    Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		    Mmap.put("getPeripheral", it_comm.getPeripheral());

		return new ModelAndView("SearchHardwarePeripheralTiles");
	}
	
	@RequestMapping(value = "/admin/peripheralhardwareEditUrl", method = RequestMethod.POST)
	public ModelAndView peripheralhardwareEditUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updateid", required = false) int id) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_PeripheralHardwareUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 
		 It_Asset_Peripherals assetupd =(It_Asset_Peripherals)sessionHQL.get(It_Asset_Peripherals.class, id);
		
		 Mmap.put("getPeripheral", it_comm.getPeripheral());
		 Mmap.put("YearOfProc", it_comm.getYearOfProc());
		 Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		 Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		 Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		 Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		 Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		 Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		 Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		 Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		 Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		 Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		 Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		 Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		
		 
		 return new ModelAndView("AddhardwarePeripheralTiles","PeripheralCmd",assetupd);
	}
	
	@RequestMapping(value = "/PeripheralAction1",method=RequestMethod.POST)
	public ModelAndView PeripheralAction1(@ModelAttribute("PeripheralCmd") It_Asset_Peripherals peripheral,
			@RequestParam(value = "msg", required = false) String msg1,
			
			HttpServletRequest request,ModelMap model,HttpSession session) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralHardwareUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg1 = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


			String roleSusNo = session.getAttribute("roleSusNo").toString();
			int id = peripheral.getId() > 0 ? peripheral.getId() : 0;
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = new Date();
			Date warrenty_dt = null;
			Date proc_dt = null;
			
			
			String rediurl="";
			if (id == 0) {
				rediurl="redirect:peripheralhardwareEditUrl";
				
			}
			else {
				rediurl="redirect:Search_PeripheralHardwareUrl";
			}
			String assets_type=request.getParameter("assets_type");
			String make_name=request.getParameter("make_name");
			String model_name=request.getParameter("model_name");
			
			String type_of_hw=request.getParameter("type_of_hw");
			String type=request.getParameter("type");

			
			String warranty=request.getParameter("warrantydt");
			String warrantycheck=request.getParameter("warrantycheck");
			
			String s_state=request.getParameter("s_state");
			String unserviceable_state=request.getParameter("unserviceable_state");
			
			String proc_date=request.getParameter("proc_dt");
			String b_head=request.getParameter("b_head");
			String b_code=request.getParameter("b_code");
			int assetcount= Integer.parseInt(request.getParameter("assetcount"));
			
			if(assets_type==null || assets_type.equals("0") || assets_type.equals("")) {
				model.put("msg", "Please Select Peripheral Type ");
				return new ModelAndView(rediurl);
			}
			if(make_name==null || make_name.equals("0") || make_name.equals("")) {
				model.put("msg", "Please Select Make/Brand Name ");
				return new ModelAndView(rediurl);
			}
			if(model_name==null || model_name.equals("0") || model_name.equals("")) {
				model.put("msg", "Please Select Model Name ");
				return new ModelAndView(rediurl);
			}
			
			if(type_of_hw==null || type_of_hw.equals("0") || type_of_hw.equals("")) {
				model.put("msg", "Please Select Type of Peripheral HW ");
				return new ModelAndView(rediurl);
			}
			
			if(type==null || type.equals("0") || type.equals("")) {
				model.put("msg", "Please Select Model Type ");
				return new ModelAndView(rediurl);
			}
			
			for(int j=1;j<=assetcount;j++)
			{
				String model_number=request.getParameter("model_no"+j);
				String machine_number=request.getParameter("machine_no"+j);
				String is_networked=request.getParameter("is_networked"+j);
				String ip_address=request.getParameter("ip_address"+j);
				

				
				
				if(model_number==null  || model_number=="") {
					model.put("msg", "Please  Enter Model Number");
					return new ModelAndView(rediurl);
				}
				if(machine_number==null  || machine_number=="") {
					model.put("msg", "Please  Enter Machine Number");
					return new ModelAndView(rediurl);
				}
				for(int k=j+1;k<=assetcount;k++){
					String machine_n=request.getParameter("machine_no"+k);
					if(machine_number.equals(machine_n)){
						model.put("msg", "You Have entered Duplicate machine number");
						return new ModelAndView(rediurl);
						
					}
				}

				if(is_networked!=null  && is_networked.toUpperCase().equals("YES") ) {
				if(ip_address==null  || ip_address.equals("") || ip_address.equals("0")) {
					model.put("msg", "Please Enter IP Address(For Networked Device)");
					return new ModelAndView(rediurl);
				}
			}
			}
		
			
			if(warrantycheck!=null  && warrantycheck.toUpperCase().equals("YES") ) {
				
				if(warranty==null  || warranty.equals("") || warranty.equals("DD/MM/YYYY")) {
					model.put("msg", "Please Select Warranty");
					return new ModelAndView(rediurl);
				}
				else {
					warrenty_dt = format.parse(warranty);
				}

			}
			
			
			
			if(s_state==null  || s_state.equals("") || s_state.equals("0")) {
				model.put("msg", "Please Select Serviceable State");
				return new ModelAndView(rediurl);
			}
			if(s_state.equals("2")) {
				if(unserviceable_state==null  || unserviceable_state.equals("") || unserviceable_state.equals("0")) {
					model.put("msg", "Please Select UN-Serviceable State");
					return new ModelAndView(rediurl);
				}
			}
			
			if(proc_date!=null  && !proc_date.equals("") && !proc_date.equals("DD/MM/YYYY")) {
				proc_dt = format.parse(proc_date);
			
			}
			
			if(b_head==null  || b_head.equals("0") ) {
				model.put("msg", "Please Enter Budget Head");
				return new ModelAndView(rediurl);
			}
			if(b_code==null  || b_code.equals("0") ) {
				model.put("msg", "Please Enter Budget Code");
				return new ModelAndView(rediurl);
			}
			
			String username = session.getAttribute("username").toString();
			
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			 
			try{		
				
				for (int i = 1; i <= assetcount; i++) {
					String machine_no =request.getParameter("machine_no"+i);
					String hqlUpdate="select count(id) from It_Asset_Peripherals where id!=:id and machine_no=:machine_no ";
					Query query2 = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setString("machine_no",machine_no )
							.setMaxResults(1);
					Long c22 = (Long)  query2.uniqueResult();
					if(c22!=null && c22>0) {
						model.put("msg", "Peripheral Asset With This Machine Number Already Exist");
						
						return new ModelAndView(rediurl);
					}
				}
				
				if (id == 0) {
					
					for (int i = 1; i <= assetcount; i++) {
						String model_no=request.getParameter("model_no"+i);
						String machine_no=request.getParameter("machine_no"+i);
						String is_networked=request.getParameter("is_networked"+i);
						String ip_address=request.getParameter("ip_address"+i);
					
						It_Asset_Peripherals T_asset= new It_Asset_Peripherals();
						T_asset.setAssets_type(peripheral.getAssets_type());
						T_asset.setModel_no(model_no);
						T_asset.setMachine_no(machine_no);
						T_asset.setType_of_hw(peripheral.getType_of_hw());
						T_asset.setYear_of_proc(peripheral.getYear_of_proc());
						T_asset.setYear_of_manufacturing(peripheral.getYear_of_manufacturing());
						T_asset.setIp_address(ip_address);
						T_asset.setType(peripheral.getType());
						T_asset.setRemarks(peripheral.getRemarks());
						T_asset.setMonochrome_color(peripheral.getMonochrome_color());
						T_asset.setIs_networked(is_networked);
						T_asset.setPrint(peripheral.getPrint());
						T_asset.setScan(peripheral.getScan());
						T_asset.setPhotography(peripheral.getPhotography());
						T_asset.setColour(peripheral.getColour());
						T_asset.setCapacity(peripheral.getCapacity());
						T_asset.setResolution(peripheral.getResolution());
						T_asset.setNo_of_ports(peripheral.getNo_of_ports());
						T_asset.setUps_capacity(peripheral.getUps_capacity());
						T_asset.setPaper_size(peripheral.getPaper_size());
						T_asset.setDisplay_size(peripheral.getDisplay_size());
						T_asset.setDisplay_connector(peripheral.getDisplay_connector());
						T_asset.setWarranty(warrenty_dt);
						T_asset.setWarrantycheck(peripheral.getWarrantycheck());
						T_asset.setProc_date(proc_dt);
						T_asset.setB_head(peripheral.getB_head());
						T_asset.setB_code(peripheral.getB_code());
						T_asset.setB_cost(peripheral.getB_cost());
						T_asset.setS_state(peripheral.getS_state());
						T_asset.setModel_name(peripheral.getModel_name());
						T_asset.setMake_name(peripheral.getMake_name());
						T_asset.setUnserviceable_state(peripheral.getUnserviceable_state());
						T_asset.setConnectivity_type(peripheral.getConnectivity_type());
						T_asset.setHw_interface(request.getParameter("hd_hw_interface"));
						T_asset.setEthernet_port(peripheral.getEthernet_port());
						T_asset.setManagement_layer(peripheral.getManagement_layer());
						T_asset.setNetwork_features(request.getParameter("hd_network_features"));
						T_asset.setV_display_size(peripheral.getV_display_size());
						T_asset.setV_display_connector(peripheral.getV_display_connector());
						T_asset.setDisplay_type(peripheral.getDisplay_type());
						
						T_asset.setCreated_by(username);
						T_asset.setCreated_date(date);
						T_asset.setSus_no(roleSusNo);
						if (unserviceable_state.equals("1")) {
							T_asset.setStatus(4);
						}
						else
							T_asset.setStatus(0);

							sessionHQL.save(T_asset);
							sessionHQL.flush();
							sessionHQL.clear();

					}
					model.put("msg", "Data Saved Successfully.");

				}
				else {
					
					String model_no=request.getParameter("model_no1");
					String machine_no=request.getParameter("machine_no1");
					String is_networked=request.getParameter("is_networked1");
					String ip_address=request.getParameter("ip_address1");
					
					String hql = "update  It_Asset_Peripherals set modified_by=:modified_by,status=:status,modified_date=:modified_date,assets_type=:assets_type,\r\n" + 
							"type_of_hw=:type_of_hw,year_of_proc=:year_of_proc,year_of_manufacturing=:year_of_manufacturing,make_name=:make_name,\r\n" + 
							"model_name=:model_name,model_no=:model_number,machine_no=:machine_number,proc_cost=:proc_cost,remarks=:remarks,type=:type,\r\n" + 
							"ups_capacity=:ups_capacity,monochrome_color=:monochrome_color,is_networked=:is_networked,\r\n" + 
							"ip_address=:ip_address,capacity=:capacity,print=:print,scan=:scan,photography=:photography,colour=:colour, \r\n" + 
							"resolution=:resolution,no_of_ports=:no_of_ports,paper_size=:paper_size,display_size=:display_size,display_type=:display_type, \r\n" + 
							"display_connector=:display_connector,warranty=:warranty,warrantycheck=:warrantycheck,s_state=:s_state,unserviceable_state=:unserviceable_state, \r\n" + 
							"b_head=:b_head,b_code=:b_code,b_cost=:b_cost,connectivity_type=:connectivity_type, hw_interface=:hw_interface, \r\n"+
							" ethernet_port=:ethernet_port, management_layer=:management_layer, network_features=:network_features, v_display_size=:v_display_size, \r\n"+
							" v_display_connector=:v_display_connector, proc_date=:proc_date "+
							" where id=:id ";
					
					Query query = sessionHQL.createQuery(hql).setInteger("status", 0)
							.setInteger("id", id).setString("modified_by", username).setTimestamp("modified_date", date)
							.setInteger("assets_type", peripheral.getAssets_type()).setInteger("type_of_hw", peripheral.getType_of_hw()).setString("year_of_proc", peripheral.getYear_of_proc())
							.setString("year_of_manufacturing", peripheral.getYear_of_manufacturing()).setInteger("make_name", peripheral.getMake_name()).setInteger("model_name", peripheral.getModel_name())
							.setString("model_number", model_no).setString("machine_number", machine_no)
							.setString("proc_cost", peripheral.getProc_cost())
							.setString("remarks", peripheral.getRemarks()).setString("type", peripheral.getType()).setString("ups_capacity", peripheral.getUps_capacity())
							.setString("monochrome_color", peripheral.getMonochrome_color()).setString("is_networked", is_networked)
							.setString("ip_address", ip_address).setString("capacity", peripheral.getCapacity())
							.setString("print", peripheral.getPrint()).setString("scan", peripheral.getScan()).setString("photography", peripheral.getPhotography()).setString("colour", peripheral.getColour())
							.setString("resolution", peripheral.getResolution()).setString("no_of_ports", peripheral.getNo_of_ports())
							.setString("paper_size", peripheral.getPaper_size()).setString("display_size", peripheral.getDisplay_size())
							.setString("display_connector", peripheral.getDisplay_connector()).setString("display_type", peripheral.getDisplay_type())
							.setTimestamp("warranty", warrenty_dt).setString("warrantycheck", peripheral.getWarrantycheck()).setTimestamp("proc_date", proc_dt)
							.setString("s_state", peripheral.getS_state()).setInteger("unserviceable_state", peripheral.getUnserviceable_state())
							.setString("b_head", peripheral.getB_head()).setString("b_code", peripheral.getB_code()).setString("b_cost", peripheral.getB_cost())
							.setString("hw_interface", request.getParameter("hd_hw_interface")).setString("connectivity_type", peripheral.getConnectivity_type()).setInteger("ethernet_port", peripheral.getEthernet_port())
							.setInteger("management_layer", peripheral.getManagement_layer()).setString("network_features", request.getParameter("hd_network_features")).setString("v_display_size", peripheral.getV_display_size())
							.setString("v_display_connector", peripheral.getV_display_connector());
							
				
					String msg = query.executeUpdate() > 0 ? "update" : "0";
					if (msg.equals("update")) {
						model.put("msg", "Data Updated Successfully.");
						return new ModelAndView("redirect:Search_PeripheralHardwareUrl");
					}
				}
			
				tx.commit();
			}catch(RuntimeException e){
				try{
					tx.rollback();
					model.put("msg", "roll back transaction");
				}catch(RuntimeException rbe){
					model.put("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	
			return new ModelAndView(rediurl);
		}

}
