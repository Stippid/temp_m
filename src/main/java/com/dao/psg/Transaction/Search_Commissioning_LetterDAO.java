package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

public interface Search_Commissioning_LetterDAO {

	public ArrayList<ArrayList<String>> Search_comm_letter(String unit_sus_no,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,String date_of_commission,String frm_dt1,String to_dt1,String roleType);
	public TB_TRANS_PROPOSED_COMM_LETTER getSearch_com_letterByid(BigInteger id);
	//public String GetUpdateSearch_Comm_Letter(TB_TRANS_PROPOSED_COMM_LETTER obj,int id);
	public String GetUpdateSearch_Comm_Letter(TB_TRANS_PROPOSED_COMM_LETTER obj,String username);
    public Boolean getPersonnelNoAlreadyExist(String personnel_no,BigInteger c_id); 
  public long GetSearch_Com_letterCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,
		  String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,String name,String status,
		  String type_of_comm_granted,String date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType,Boolean IsMns);
    
    public List<Map<String, Object>> GetSearch_Com_letterdata(int startPage, int pageLength, String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_sus_no,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,
			 String date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType, String roleType2,Boolean IsMns) throws SQLException;
    public Boolean getPersonnelNoAlreadyExistinpostinout(String personnel_no);
}

