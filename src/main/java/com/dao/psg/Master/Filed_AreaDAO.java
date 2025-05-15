package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_PSG_MSTR_FIELD_AREA;

public interface Filed_AreaDAO {

	public ArrayList<ArrayList<String>> search_Field_Area(String field_area,String status);
	public TB_PSG_MSTR_FIELD_AREA getFieldAreaByid(int id);
	public ArrayList<ArrayList<String>> search_Field_Area_report();
}
