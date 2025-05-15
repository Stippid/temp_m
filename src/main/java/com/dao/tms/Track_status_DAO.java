package com.dao.tms;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface  Track_status_DAO {
	
	public ArrayList<ArrayList<String>> a_search_track_status(HttpSession session,String mode,String py);
	public ArrayList<ArrayList<String>> b_search_track_status(HttpSession session,String mode,String py);
	public ArrayList<ArrayList<String>> c_search_track_status(HttpSession session,String mode,String py);
}
