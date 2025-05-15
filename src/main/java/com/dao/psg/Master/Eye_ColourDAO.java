package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_EYE_COLOUR;

public interface Eye_ColourDAO {

	public ArrayList<ArrayList<String>> GetSearch_Eye_Colour (String eye_cl_name,String status);
	public TB_EYE_COLOUR getEyeColourByid(int id);	
	public ArrayList<ArrayList<String>> GetSearch_Eye_ColourReport();
}
