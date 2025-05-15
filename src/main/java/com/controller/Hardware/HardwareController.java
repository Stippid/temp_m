package com.controller.Hardware;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.dao.Assets.Hardware_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class HardwareController {
	
	@Autowired
	private  Hardware_DAO cd1;

	It_CommonController it_comm = new It_CommonController();
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	

	
	@RequestMapping(value = "/admin/Search_HardwareUrl", method = RequestMethod.GET)
	public ModelAndView Search_HardwareUrl(ModelMap Mmap,HttpSession sessionUserId,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,HttpSession sessionEdit) {
	
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_HardwareUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			 Mmap.put("msg", msg);	
			 Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
			 Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
	
		return new ModelAndView("SearchHardwareTiles");
	}
	
	@RequestMapping(value = "/getFilteredhardware", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredhardware(int startPage,String pageLength,String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession sessionUserId) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
	
		return cd1.searchhardware(startPage,pageLength,Search, orderColunm,orderType,assets_type,b_head,b_cost,sessionUserId);
	}
	
	
	@RequestMapping(value = "/gethardwareTotalCount", method = RequestMethod.POST)
	public @ResponseBody long gethardwareTotalCount(String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession sessionUserId) throws SQLException {
		

	
		return cd1.gethardwareTotalCount(Search,orderColunm,orderType,assets_type,b_head,b_cost,sessionUserId);
	}
	
	
	
	@RequestMapping(value = "/admin/hardwareEditUrl", method = RequestMethod.POST)
	public ModelAndView hardwareEditUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updateid", required = false) int id) {
		
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_HardwareUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 
		Assets_Main assetupd =(Assets_Main)sessionHQL.get(Assets_Main.class, id);
		
		 Mmap.put("AntivirusList", it_comm.getAntivirus());
		 Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		 Mmap.put("processor_typeList", it_comm.getProcessorList());
		 Mmap.put("ramList", it_comm.getRamList());
		 Mmap.put("hddList", it_comm.getHDDList());
		 Mmap.put("osList", it_comm.getOperatingSystem());
		 Mmap.put("officeList", it_comm.getOffice());
		 Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		 Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		 Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		 Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		 Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		 Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		 Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		 
		 return new ModelAndView("AddhardwareTiles","MainAssetsCmd",assetupd);
	}
	
	
	@RequestMapping(value = "/updateAssetshardwareAction", method = RequestMethod.POST)
	public ModelAndView updateAssetshardwareAction(HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg
			)  {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_HardwareUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		

		
		 int asset_type = Integer.parseInt(request.getParameter("asset_type"));
		 String b_head = request.getParameter("b_head"); 
		 String b_cost = request.getParameter("b_cost");
		 int id=Integer.parseInt(request.getParameter("id"));
		
			String username = session.getAttribute("username").toString();
		
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();

		 	try {
		 		

		                    
					
		                { String hql = "update Assets_Main set asset_type=:asset_type,b_head=:b_head,b_cost=:b_cost,modified_by=:modified_by,modified_date=:modified_date"
									+ " where id=:id";
		                             
				    	  Query query = sessionHQL.createQuery(hql)
				    			  
				    			  	.setInteger("asset_type", asset_type)
				    			  	.setString("b_head",b_head)
				    			  	.setString("b_cost",b_cost)
				    			  	.setString("modified_by", username)
									.setDate("modified_date", new Date())
									.setInteger("id", id);
				                    msg = query.executeUpdate() > 0 ? "1" :"0";
				                    tx.commit(); 
				                    if(msg == "1") {
				                    	 model.put("msg", "Data Updated Successfully.");
				                    }
				                    else {
				                    	model.put("msg", "Data Not Updated.");
				                    }
					}
				                   
				                    
		                }          
	
			  catch(RuntimeException e){
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
		 	return new ModelAndView("redirect:Search_HardwareUrl");
	}
	
	
	@RequestMapping(value = "/MainAssetAction",method=RequestMethod.POST)
	public ModelAndView MainAssetAction(@ModelAttribute("MainAssetsCmd") Assets_Main asset,
			@RequestParam(value = "msg", required = false) String msg1,
			HttpServletRequest request,ModelMap model,HttpSession session) throws ParseException {
		
		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_HardwareUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg1 = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		//Date dob_dt = null;
		Date warrenty_dt = null;
		Date proc_dt = null;
		int id = asset.getId() > 0 ? asset.getId() : 0;
		String rediurl="";
		if (id == 0) {
			rediurl="redirect:hardwareEditUrl";
			
		}
		else
		{
			rediurl="redirect:Search_HardwareUrl";
		}
		
		String asset_type=request.getParameter("asset_type");
		String make_name=request.getParameter("make_name");
		String model_name=request.getParameter("model_name");
		String processor_type=request.getParameter("processor_type");
		String ram_capi=request.getParameter("ram_capi");
		String hdd_capi=request.getParameter("hdd_capi");
		String operating_system=request.getParameter("operating_system");
		String antiviruscheck=request.getParameter("antiviruscheck");
		String antivirus=request.getParameter("antivirus");
		String os_patch=request.getParameter("os_patch");
		String dply_envt=request.getParameter("dply_envt");
		String ram_slot_qty=request.getParameter("ram_slot_qty");
		String warranty=request.getParameter("warranty_dt");
		String warrantycheck=request.getParameter("warrantycheck");
		
		String cd_drive=request.getParameter("cd_drive");
		String s_state=request.getParameter("s_state");
		String unserviceable_state=request.getParameter("unserviceable_state");
		String b_cost=request.getParameter("b_cost");
		
		String proc_date=request.getParameter("proc_dt");
		
		String b_head=request.getParameter("b_head");
		String b_code=request.getParameter("b_code");
		int assetcount= Integer.parseInt(request.getParameter("assetcount"));
		
		if(asset_type==null || asset_type.equals("0") || asset_type.equals("")) {
			model.put("msg", "Please Select Computing Assets Type ");
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

		if(processor_type==null  || processor_type.equals("") ||  processor_type.equals("0")) {
			model.put("msg", "Please Select Processor Type");
			return new ModelAndView(rediurl);
		}
		if(ram_capi==null  || ram_capi.equals("") || ram_capi.equals("0")) {
			model.put("msg", "Please Select RAM Capacity ");
			return new ModelAndView(rediurl);
		}
		
		if(hdd_capi==null  || hdd_capi.equals("") || hdd_capi.equals("0")) {
			model.put("msg", "Please Select HDD Capacity");
			return new ModelAndView(rediurl);
		}
		
		if(operating_system==null  || operating_system.equals("") || operating_system.equals("0")) {
			model.put("msg", "Please Select Operating System ");
			return new ModelAndView(rediurl);
		}
		
		if(operating_system==null  || operating_system.equals("") || operating_system.equals("0")) {
			model.put("msg", "Please Select Operating System ");
			return new ModelAndView(rediurl);
		}
		if(antiviruscheck!=null  && antiviruscheck.toUpperCase().equals("YES") ) {
			if(antivirus==null  || antivirus.equals("") || antivirus.equals("0")) {
				model.put("msg", "Please Select AntiVirus");
				return new ModelAndView(rediurl);
			}
		}
		
		if(os_patch==null  || os_patch.equals("") || os_patch.equals("0")) {
			model.put("msg", "Please Select OS/Framware Activation and subsequent Patch Updation Mechanism  ");
			return new ModelAndView(rediurl);
		}
		if(dply_envt==null  || dply_envt.equals("") || dply_envt.equals("0")) {
			model.put("msg", "Please Select Dply Envt as Applicable ");
			return new ModelAndView(rediurl);
		}
		
		if(ram_slot_qty==null  || ram_slot_qty.equals("") || ram_slot_qty.equals("0")) {
			model.put("msg", "Please Enter RAM Slot Quantity");
			return new ModelAndView(rediurl);
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
		if(cd_drive==null  || cd_drive.equals("") ) {
			model.put("msg", "Please Check CD Drive!");
			return new ModelAndView(rediurl);
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
		for(int j=1;j<=assetcount;j++)
		{
			String model_no=request.getParameter("model_number"+j);
			String machine_no=request.getParameter("machine_number"+j);
			String mac_add=request.getParameter("mac_address"+j);
			
			if(model_no==null  || model_no=="") {
				model.put("msg", "Please  Enter Model Number");
				return new ModelAndView(rediurl);
			}
			if(machine_no==null  || machine_no=="") {
				model.put("msg", "Please  Enter Machine Number");
				return new ModelAndView(rediurl);
			}
			for(int k=j+1;k<=assetcount;k++){
				String machine_n=request.getParameter("machine_number"+k);
				if(machine_no.equals(machine_n)){
					model.put("msg", "You Have entered Duplicate machine number");
					return new ModelAndView(rediurl);
					
				}
			}
			if(mac_add==null  || mac_add=="") {
				model.put("msg", "Please  Enter  Mac Address");
				return new ModelAndView(rediurl);
			}
			
		}
		
		if(b_head==null  || b_head.equals("0") ) {
			model.put("msg", "Please Select Budget Head");
			return new ModelAndView(rediurl);
		}
		if(b_code==null  || b_code.equals("0") ) {
			model.put("msg", "Please Select Budget Code");
			return new ModelAndView(rediurl);
		}
		
		if(proc_date!=null  && !proc_date.equals("") && !proc_date.equals("DD/MM/YYYY")) {
			proc_dt = format.parse(proc_date);
		
		}
		
			String username = session.getAttribute("username").toString();
			 String roleSusNo = session.getAttribute("roleSusNo").toString();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
				
			
			 
			try{
				
				for (int i = 1; i <= assetcount; i++) {
					String machine_number =request.getParameter("machine_number"+i);
					String hqlUpdate="select count(id) from Assets_Main where id!=:id and machine_number=:machine_number ";
					Query query2 = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setString("machine_number",machine_number )
							.setMaxResults(1);
					Long c22 = (Long)  query2.uniqueResult();
					if(c22!=null && c22>0) {
						model.put("msg", "Computing Asset With Machine Number ("+machine_number+") Already Exist");
						
						return new ModelAndView(rediurl);
					}
				}
				if (id == 0) {
					for (int i = 1; i <= assetcount; i++) {
						
						String model_number=request.getParameter("model_number"+i);
						String machine_number=request.getParameter("machine_number"+i);
						String mac_address=request.getParameter("mac_address"+i);
						String ip_address=request.getParameter("ip_address"+i);
						Assets_Main T_asset= new Assets_Main();
						T_asset.setAsset_type(asset.getAsset_type());
						T_asset.setModel_number(model_number);
						T_asset.setMachine_number(machine_number);
						T_asset.setMac_address(mac_address);
						T_asset.setIp_address(ip_address);
						T_asset.setProcessor_type(asset.getProcessor_type());
						T_asset.setRam_capi(asset.getRam_capi());
						T_asset.setHdd_capi(asset.getHdd_capi());
						T_asset.setOperating_system(asset.getOperating_system());
						T_asset.setOffice(asset.getOffice());
						T_asset.setOs_patch(asset.getOs_patch());
						T_asset.setDply_envt(asset.getDply_envt());
						T_asset.setAntiviruscheck(asset.getAntiviruscheck());
						T_asset.setWarranty(warrenty_dt);
						T_asset.setWarrantycheck(asset.getWarrantycheck());
						T_asset.setAntivirus(asset.getAntivirus());
						T_asset.setProc_date(proc_dt);
						T_asset.setB_head(b_head);
						T_asset.setB_code(b_code);
						T_asset.setB_cost(b_cost);
						T_asset.setCd_drive(cd_drive);
						T_asset.setS_state(s_state);
						T_asset.setModel_name(asset.getModel_name());
						T_asset.setMake_name(asset.getMake_name());
						T_asset.setUnserviceable_state(asset.getUnserviceable_state());
						T_asset.setDimension(asset.getDimension());
						T_asset.setConnecting_tech(asset.getConnecting_tech());
						T_asset.setRam_slot_qty(asset.getRam_slot_qty());
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
					
					String model_number=request.getParameter("model_number1");
					String machine_number=request.getParameter("machine_number1");
					String mac_address=request.getParameter("mac_address1");
					String ip_address=request.getParameter("ip_address1");
					
					String hql = "update Assets_Main set modified_by=:modified_by ,status=:status,modified_date=:modified_date "
							+ ",asset_type=:asset_type ,model_number=:model_number ,machine_number=:machine_number ,mac_address=:mac_address "
							+ ",ip_address=:ip_address ,processor_type=:processor_type ,ram_capi=:ram_capi ,hdd_capi=:hdd_capi ,operating_system=:operating_system "
							+ ",office=:office ,os_patch=:os_patch ,dply_envt=:dply_envt ,antiviruscheck=:antiviruscheck ,antivirus=:antivirus, ram_slot_qty=:ram_slot_qty "
							+ ",warranty=:warranty ,warrantycheck=:warrantycheck,b_head=:b_head ,b_code=:b_code ,b_cost=:b_cost ,cd_drive=:cd_drive ,connecting_tech=:connecting_tech"
							+ ",s_state=:s_state ,model_name=:model_name ,make_name=:make_name ,unserviceable_state=:unserviceable_state,ethernet_port=:ethernet_port,proc_date=:proc_date"
							+ " where  id=:id";
					Query query = sessionHQL.createQuery(hql).setInteger("status", 0)
							.setInteger("id", id).setString("modified_by", username).setTimestamp("modified_date", date)
							.setInteger("asset_type", asset.getAsset_type()).setString("model_number", model_number).setString("machine_number", machine_number)
							.setString("mac_address", mac_address).setString("ip_address", ip_address).setInteger("processor_type", asset.getProcessor_type())
							.setInteger("ram_capi", asset.getRam_capi()).setInteger("hdd_capi", asset.getHdd_capi()).setInteger("operating_system", asset.getOperating_system())
							.setInteger("office", asset.getOffice()).setInteger("os_patch", asset.getOs_patch()).setInteger("dply_envt", asset.getDply_envt())
							.setString("antiviruscheck", asset.getAntiviruscheck()).setInteger("antivirus", asset.getAntivirus()).setTimestamp("warranty", warrenty_dt).setString("warrantycheck", asset.getWarrantycheck())
							.setString("b_head", asset.getB_head()).setString("b_code", asset.getB_code()).setString("b_cost", asset.getB_cost()).setString("cd_drive", asset.getCd_drive())
							.setString("s_state", asset.getS_state()).setInteger("model_name", asset.getModel_name()).setInteger("make_name", asset.getMake_name()).setInteger("ram_slot_qty", asset.getRam_slot_qty())
							.setInteger("connecting_tech", asset.getConnecting_tech()).setInteger("ethernet_port", asset.getEthernet_port()).setInteger("unserviceable_state", asset.getUnserviceable_state()).setTimestamp("proc_date", proc_dt);
					String msg = query.executeUpdate() > 0 ? "update" : "0";
					if (msg.equals("update")) {
						model.put("msg", "Data Updated Successfully.");
						return new ModelAndView("redirect:Search_HardwareUrl");
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
	
	@RequestMapping(value = "/HardwareDelete", method = RequestMethod.POST)
	public ModelAndView ComputingAssertsDelete(@ModelAttribute("id1") String id,@RequestParam(value = "msg", required = false) String msg,BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session1) {
		
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_HardwareUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "delete from Assets_Main where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id)).executeUpdate();						
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully");
			} else {
				liststr.add("Data not Deleted");
			}
			model.put("msg",liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Search_HardwareUrl");
	}	
	
}
