package com.dao.psg.Jco_Transaction;

import java.util.ArrayList;
import java.util.List;

import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_JCO;
import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO;

public interface Marital_DiscordJCO_DAO {
	
	public String GetUpdateMarital_DiscordJCOParent(TB_PSG_MARITAL_DISCORD_JCO obj,String username);
	public String GetUpdateMarital_DiscordJCOChild(TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO obj,String username);
	
	public ArrayList<ArrayList<String>> Search_JCOMarital(String personnel_no,String status,String roleType,String from_date,String cr_by,String cr_date,String roleAccess,String roleSusNo);
	public TB_PSG_MARITAL_DISCORD_JCO getmaritalByid(int id);
	
	public ArrayList<ArrayList<String>> Marital_DiscordJCO_History(String id);
	
	public ArrayList<ArrayList<String>> Popup_Marital_DiscordJCO_History(int jco_id);
	
	public List<TB_PSG_MARITAL_DISCORD_JCO> getMarital_DiscordByidJCO(int id);
	public List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> getMarital_DiscordChildByidJCO(int id);
}
