package com.dao.fpmis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface FP_MIS_DashboardDAO {     
	
	public List<String> getMNHEncList(List<String> l,HttpSession s1);
	public List<Map<String, Object>> getHoldingStateDatass(String roleAccess,String roleSubAccess,String roleFormationNo,String sus_no, String fin_year);
	public ArrayList<ArrayList<String>> nFundPerfMeter(HttpSession sessionA,String m1_tryear,String m1_lvl,String m1_hdlvl,String nUsrId);
	public ArrayList<ArrayList<String>> nFundPerTrend(HttpSession sessionA,String m1_tryear,String m1_lvl,String m1_hdlvl,String nUsrId,String m1_hdlvlval);
}
