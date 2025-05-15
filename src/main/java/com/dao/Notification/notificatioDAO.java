package com.dao.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.psg.Civilian_Master.TB_PSG_MSTR_CADRE_CIVILIAN;


public interface notificatioDAO {

	
	public ArrayList<ArrayList<String>> getnotificationList(Integer userid);
	public List<String> getUserIdForNotif(Integer module_id);
	public String getUnitNameFromUserId(int user_id);
	public ArrayList<ArrayList<String>> getnotificationList_new(Integer userid);
	
	public String sendnotificationSDMOV();
}
