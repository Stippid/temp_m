package com.dao.psg.Transaction;

import java.util.ArrayList;

public interface NotificationDao {
	
	public int getRejectNotification(String susNo);
	public int getcountNotification(String susNo);
	public ArrayList<ArrayList<String>> getpersonalNotification(String susNo);
}
