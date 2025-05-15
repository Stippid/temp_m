package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_Height;


public interface HeightDAO {
	
	public ArrayList<ArrayList<String>> search_height_Master(String centimeter,String inch,String status);
	public TB_Height getheightByid(int id);
	public ArrayList<ArrayList<String>> search_height_Report();
}
