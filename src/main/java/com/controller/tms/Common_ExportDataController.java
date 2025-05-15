package com.controller.tms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.ExportDataDAO;
import com.dao.tms.ExportDataDAOImpl;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Common_ExportDataController {
	@Autowired
	ExportDataDAO exprt;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/export_data", method = RequestMethod.GET)
	public ModelAndView export_data(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("export_data", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("export_dataTiles");
	}

	@RequestMapping(value = "/admin/getAttributeFromMctMainMaster", method = RequestMethod.POST)
	public ModelAndView getAttributeFromMctMainMaster(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "tbname", required = false) String table_name,
			@RequestParam(value = "statusname", required = false) String statusname) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("export_data", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("table_name", table_name);
		if (table_name == "PRF" || table_name.equals("PRF")) {
			table_name = "select code_no_from as \"CODE FROM\",code_no_to \"CODE TO\",group_name as \"PRF GROUP NAME\",prf_code as \"PRF CODE\",vehicle_class_code as \"VEHICLE CLASS CODE\",reserve as \"RESERVE\",type_of_veh as \"TYPE OF VEHICLE\" from tb_tms_mct_slot_master";
		} else if (table_name == "MAIN" || table_name.equals("MAIN")) {
			table_name = "select a.mct_nomen as \"MCT NOMEN\",a.mct_main_id as \"MCT MAIN\",a.prf_code as \"PRF CODE\",b.group_name as \"PRF GROUP NAME\",\r\n" + 
					"a.vehicle_class_code as \"VEHICLE CLASS CODE\",a.type_of_veh as \"TYPE OF VEHICLE\" \r\n" + 
					"from tb_tms_mct_main_master a\r\n" + 
					"inner join tb_tms_mct_slot_master b on a.prf_code = b.prf_code";
		} else if (table_name == "MAKE" || table_name.equals("MAKE")) {
			table_name = "select description as \" DESCRIPTION\",make_no as \"MAKE\",mct_slot_id as \"MCT MAIN\" ,b.mct_nomen as \"MCT NOMEN\"\r\n" + 
					"from tb_tms_make_master a\r\n" + 
					"inner join tb_tms_mct_main_master b on  a.mct_slot_id =b.mct_main_id";
		} else if (table_name == "MODEL" || table_name.equals("MODEL")) {
			table_name = "select description as \" DESCRIPTION\",model_id as \"MODEL\",mct_main_id as \"MCT MAIN\",make_id as \"MAKE\"  from tb_tms_model_master";
		} else if (table_name == "NODAL" || table_name.equals("NODAL")) {
			table_name = "select a.sus_no as \"NODEL DTE/DEPOT SUS NO\",b.unit_name as \"NODEL DTE/DEPOT NAME\",a.type_of_agncy as \"TYPE OF AGENCY\",a.depot_code as \"DEPOT CODE\",a.type_of_veh as \"TYPE OF CODE\" from tb_tms_mct_nodal_dir_master a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and b.status_sus_no='Active'";
		} else if (table_name == "AGENCY" || table_name.equals("AGENCY")) {
			table_name = "select a.sus_no as \"SUS No\" ,b.unit_name as \"UNIT NAME\"\r\n" + 
					"from tb_tms_mct_req_agency_master a\r\n" + 
					"left join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and b.status_sus_no='Active'";
		} else if (table_name == "UPLOAD" || table_name.equals("UPLOAD")) {
			table_name = "select m.id as \"DOC ID\",m.createddatetime as \"UPLOADED DATE\",u.unit_name as \"UNIT NAME\",m.sus_no as \"SUS No\",m.status as \"STATUS\",m.downloadon as \"DOWNLOAD ON\",m.downloadby as \"DOWNLOAD BY\" from tb_tms_upload_document_mvcr m inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and m.sus_no = u.sus_no  where status='"  +statusname+ "'  order by m.status";
		} else if (table_name == "UPLOADA" || table_name.equals("UPLOADA")) {
			table_name = "select m.id as \"DOC ID\",u.unit_name as \"UNIT NAME\",m.sus_no as \"SUS No\",m.createddatetime as \"UPLOADED DATE\",m.status as \"STATUS\",m.downloadon as \"DOWNLOAD ON\",m.downloadby as \"DOWNLOAD BY\" from tb_tms_upload_document_mcr m inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and m.sus_no = u.sus_no  where status='"+statusname+"' order by m.status ";
		}	 else {
			Mmap.put("msg", "Please Select The Table.");
		}
		ArrayList<ArrayList<String>> list = exprt.getAttributeFromMctMainMaster(table_name);
		if (list.size() == 0) {
			Mmap.put("msg", "Data Not Available.");
		} else {
			Mmap.put("list", list);
			Mmap.put("listth", list.get(0));
			Mmap.put("listthSize", list.get(0));
			Mmap.put("listSize", list.size());
			Mmap.put("listSize", list.size());
			Mmap.put("statusname", statusname);
		}
		return new ModelAndView("export_dataTiles");
	}
	
	
}