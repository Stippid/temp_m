package com.dao.fpmis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface FP_MIS_AdvisoryDAO {
	public boolean UploadDataSave(String roleSusNo,String advisory_in_details,String file_name,String adv_type,Date upload_from_date, Date upload_to_date,String username);
	public ArrayList<ArrayList<String>> getSearchAdvisoryData(String advisory_in_details1,String frm_dt1,String to_dt1,String adv_type);
	public String msg_save(String roleSusNo, String msg1, String msg_to, String dt_fr,String dt_to, String msgtype);
	public ArrayList<ArrayList<String>> getSearchMsgData();
	public String getAdvisoryFileName(int file_id);
}
