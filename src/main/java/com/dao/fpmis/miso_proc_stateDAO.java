package com.dao.fpmis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface miso_proc_stateDAO {
	public @ResponseBody List<Map<String, Object>> getProcDetls();
	public @ResponseBody String misoprocupdate(HttpSession s1, String ngid,String ngitem, String ngsoref, 
			String ngsodate, String ngauthunit, String ngqty, String ngdelpd,String ngprogqty,
			String ngprogstg,String ngspondte,String ngrem,String ngconsdepot,String ngconsqty,String ngprocdte,String ngcate,String ngroute);
}

