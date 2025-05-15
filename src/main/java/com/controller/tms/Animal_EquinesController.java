package com.controller.tms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.AnimalEquinesDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Animal_EquinesController {

	@Autowired
	private AnimalEquinesDAO newanimalDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	AllMethodsControllerTMS alltms = new AllMethodsControllerTMS();
	ValidationController validation = new ValidationController();

	///////////////// Army Dog ///////////////////////

	@RequestMapping(value = "/admin/Animal_Dog", method = RequestMethod.GET)
	public ModelAndView Animal_Dog(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Dog", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("Animal_DogTiles");
	}

	@RequestMapping(value = "/Dogreportlist", method = RequestMethod.POST)
	public ModelAndView Dogreportlist(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "re_date1", required = false) String tdate,
			@RequestParam(value = "unit_file_def1", required = false) String unit_file_def,
			@RequestParam(value = "fmn1", required = false) String fmn) throws ParseException {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Dog", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String fdt = request.getParameter("from_date1");
		String tdt = request.getParameter("re_date1");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		if (fdt != "") {
			if (format.parse(fdt).compareTo(today_dt) > 0 && format.parse(fdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Animal_Dog");
			}
		}
		if (tdt != "") {
			if (format.parse(tdt).compareTo(today_dt) > 0 && format.parse(tdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Animal_Dog");
			}
		}
		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				Mmap.put("msg", "Return As should not be less than From Date");
				return new ModelAndView("redirect:Animal_Dog");
			}
		}

		ArrayList<ArrayList<String>> list = newanimalDAO.Dogreportlist(sus_no, fdate, tdate);
		int sumUE = 0;
		int totalUH = 0;
		int totalsur = 0;
		int totaldefi = 0;
		int totalall = 0;
		Mmap.put("list", list);
		for (int i = 0; i < list.size(); i++) {
			String sumUE1 = list.get(i).get(1);
			String Sumsur1 = list.get(i).get(3);
			String Sumdefi1 = list.get(i).get(4);
			String SumTotal1 = list.get(i).get(5);

			sumUE = sumUE + Integer.parseInt(sumUE1);
			totalUH = totalUH + Integer.parseInt(list.get(i).get(2));
			totalsur += Integer.parseInt(Sumsur1);
			totaldefi += Integer.parseInt(Sumdefi1);
			totalall += Integer.parseInt(SumTotal1);
		}
		if (sus_no == "") {
			Mmap.put("msg", "Please Select SUS No");
		}

		else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:Animal_Dog");
		} else {
			Mmap.put("sumUE", sumUE);
			Mmap.put("totalUH", totalUH);
			Mmap.put("totalsur", totalsur);
			Mmap.put("totaldefi", totaldefi);
			Mmap.put("totalall", totalall);
			Mmap.put("sus_no1", sus_no);
			Mmap.put("unit_name1", unit_name);
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			Mmap.put("from_date1", fdate);
			Mmap.put("re_date1", tdate);
			Mmap.put("unit_file_def1", unit_file_def);
			Mmap.put("fmn1", fmn);
			Mmap.put("roleType", roleType);
			Mmap.put("msg", msg);
		}
		return new ModelAndView("Animal_DogTiles");
	}
	/////////////////////////////////////////

	/////////////////// Army Equines ////////////////////

	@RequestMapping(value = "/admin/Animal_Equines", method = RequestMethod.GET)
	public ModelAndView Animal_Equines(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Equines", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		return new ModelAndView("Animal_EquinesTiles");
	}

	@RequestMapping(value = "/equ_ue_uh", method = RequestMethod.POST)
	public ModelAndView equ_ue_uh(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "re_date1", required = false) String tdate,
			@RequestParam(value = "unit_file_def1", required = false) String unit_file_def,
			@RequestParam(value = "fmn1", required = false) String fmn) throws ParseException {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Animal_Equines", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		String fdt = request.getParameter("from_date1");
		String tdt = request.getParameter("re_date1");
		Date today_dt = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		if (fdt != "") {
			if (format.parse(fdt).compareTo(today_dt) > 0 && format.parse(fdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Animal_Equines");
			}
		} else if (tdt != "") {
			if (format.parse(tdt).compareTo(today_dt) > 0 && format.parse(tdt).compareTo(today_dt) != 0) {
				Mmap.put("msg", "Please Enter Valid Date Of Request.");
				return new ModelAndView("redirect:Animal_Equines");
			}
		}
		if (fdt != "" && tdt != "") {
			if (alltms.CompareDate(fdt, tdt) == 0) {
				Mmap.put("msg", "Return As should not be less than From Date");
				return new ModelAndView("redirect:Animal_Equines");
			}
		}

		ArrayList<ArrayList<String>> list = newanimalDAO.Equinesreportlist(sus_no, fdate, tdate);

		int sumUE = 0;
		int totalUH = 0;
		int totalsur = 0;
		int totaldefi = 0;
		int totalall = 0;
		Mmap.put("list", list);

		for (int i = 0; i < list.size(); i++) {
			String sumUE1 = list.get(i).get(1);
			String Sumsur1 = list.get(i).get(3);
			String Sumdefi1 = list.get(i).get(4);
			String SumTotal1 = list.get(i).get(5);

			sumUE = sumUE + Integer.parseInt(sumUE1);
			totalUH = totalUH + Integer.parseInt(list.get(i).get(2));
			totalsur += Integer.parseInt(Sumsur1);
			totaldefi += Integer.parseInt(Sumdefi1);
			totalall += Integer.parseInt(SumTotal1);
		}

		if (sus_no == "") {
			Mmap.put("msg", "Please Select SUS No");
		}

		else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:Animal_Equines");
		}

		else {
			Mmap.put("sumUE", sumUE);
			Mmap.put("totalUH", totalUH);
			Mmap.put("totalsur", totalsur);
			Mmap.put("totaldefi", totaldefi);
			Mmap.put("totalall", totalall);
			Mmap.put("sus_no1", sus_no);
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			Mmap.put("from_date1", fdate);
			Mmap.put("re_date1", tdate);
			Mmap.put("unit_file_def1", unit_file_def);
			Mmap.put("fmn1", fmn);
			Mmap.put("roleType", roleType);
			Mmap.put("msg", msg);
		}
		return new ModelAndView("Animal_EquinesTiles");
	}
}