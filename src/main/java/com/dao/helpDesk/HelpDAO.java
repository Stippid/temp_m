package com.dao.helpDesk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.Helpdesk.HD_TB_BISAG_FAQ;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;

public interface HelpDAO {
	public HD_TB_BISAG_TICKET_GENERATE gethelpbyId(int id);
	public List<Map<String, Object>> getSupportRequestJdbc();
	public List<Map<String, Object>> getActiveUserListJdbc(String qry);
	public List<Map<String, Object>> getUserLoginCountJdbc(String qry);
	public List<Map<String, Object>> getActiveUserCountJdbc(String qry);
	public List<Map<String, Object>> getNewTicketCountJdbc(String qry);
	public List<Map<String, Object>> getPendigTicketCountJdbc(String qry);
	public List<Map<String, Object>> getCompletedTicketCountJdbc(String qry);
	public List<Map<String, Object>> getRoleCountJdbc(String qry);
	public List<Map<String, Object>> getUserCountJdbc(String qry);
	public List<Map<String, Object>> getfeedbackrecCountJdbc(String qry);
	public List<Map<String, Object>> getFeatureReqCountJdbc(String qry);
	public List<Map<String, Object>> TicketSearchList(String section);
	public HD_TB_BISAG_FAQ geteditfaqbyId(int updateid);
	public String UpdateFAQDetailsValue(HD_TB_BISAG_FAQ faqValues);
	
	public List<Map<String, Object>> getHelpMngtListJdbc(String moduleid,String agent_name);
	public String UpdateViewDetailsValue(HD_TB_BISAG_TICKET_GENERATE uh, String agent_name);
	public List<Map<String, Object>> myTicketList(String ticket, String ticket_status, String from_date,String to_date, String help_topic, String userId, String roleid, String module, String label1);
	public List<Map<String, Object>> manageTicketList(String ticket,String ticket_status,String from_date,String to_date,String help_topic,String username,String roleid,String module,String label1,String moduleWhr,String typeWhr, String unit_name1, String cont_comd1, String role);
	public List<Map<String, Object>> getSearchMercuryList1(String msg, String valid_upto);
	public Boolean getmsgExist(String msg, Date valid_upto);
	public List<String> getLayoutlist();
	public Long checkExitstingUserManagement(int module,String agent_name) ;
	public ArrayList<ArrayList<String>> getNewfaqdetail1();
	public List<String> getsecList();
	public List<String> getqueList();
	public List<HD_TB_BISAG_TICKET_GENERATE> getmodule_help(int id);
	public List<Map<String, Object>> loggedin_report(List<String> userlist);
	public String getSupportRequest();
	public String getActiveUserList();
	public List<Map<String, Object>> getUserLoginCount();
	public List<Map<String, Object>> getActiveUserCount();
	public List<Map<String, Object>> getNewTicketCount();
	public List<Map<String, Object>> getPendigTicketCount();
	public List<Map<String, Object>> getCompletedTicketCount();
	public List<Map<String, Object>> getfeedbackrecCount();
	public List<Map<String, Object>> getFeatureReqCount();
	public List<Map<String, Object>> getRoleCount();
	public List<Map<String, Object>> getUserCount();
	public List<Map<String, Object>> getUserReport() ;
	public List<Map<String, Object>> ticketStatusNew();
	public List<Map<String, Object>> ticketStatusInProgress();
	public List<Map<String, Object>> ticketStatusCompleted();
	public List<Map<String, Object>> helpTopicFeatureRequest();
	public List<Map<String, Object>> helpTopicFeedback();
	public HD_TB_BISAG_TICKET_GENERATE getUploadScreenShotByid(int id);
	
	public List<String> getUserNameAutoComplateList(String agent_name,String whr);
	public List<String> getUseridByUserName(String userName);
	
	public List<Map<String, Object>> getWhatsNewList(int startPage, String pageLength, String Search,String orderColunm, String orderType, HttpSession session);
	public long getWhatsNewCount(String Search);
	public Object UpdateViewDetailsSD(HD_TB_BISAG_TICKET_GENERATE uh);
}