package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.models.psg.Master.TB_NATIONALITY;
import com.models.psg.Transaction.TB_MARITIAL_DISCORD;
import com.models.psg.Transaction.TB_Maritial_Discord_Status_Child;

public interface Maritial_DiscordDAO {
	
	public ArrayList<ArrayList<String>> search_maritial(String service_category1,String personnel_no,
			String status,String roleType,String from_date,String roleAccess,String roleSusNo, String rank,String close_status1,String cr_by,String cr_date );
	public TB_MARITIAL_DISCORD getmaritialByid(int id);
	public String GetUpdateMarital_DiscordParent(TB_MARITIAL_DISCORD obj,String username);
	public String GetUpdateMarital_DiscordChild(TB_Maritial_Discord_Status_Child obj,String username);
	public ArrayList<ArrayList<String>> Popup_Marital_Discord_History(BigInteger comm_id, int census_id);
	public ArrayList<ArrayList<String>> Marital_Discord_History(String id);
	public List<TB_MARITIAL_DISCORD> getMaritial_DiscordByid(int id);
	public List<TB_Maritial_Discord_Status_Child> getMaritial_DiscordChByid(int id);
	public ArrayList<ArrayList<String>> search_maritial_count(String personnel_no);
	
	
	
	public ArrayList<ArrayList<String>> search_maritial_table(String service_category1,String personnel_no,
			String roleType,String roleAccess,String roleSusNo);
	
}
