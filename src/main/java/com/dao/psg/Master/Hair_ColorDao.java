package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_HAIR_COLOUR;

public interface Hair_ColorDao {

	
	public ArrayList<ArrayList<String>> search_hair_colour (String hair_cl_name , String status);
	public TB_HAIR_COLOUR getHairColourByid(int id);
	public ArrayList<ArrayList<String>> search_hair_colourReport();
}
