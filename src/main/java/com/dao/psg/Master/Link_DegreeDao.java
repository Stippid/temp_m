package com.dao.psg.Master;

import java.util.ArrayList;

import com.models.psg.Master.TB_LINK_DEGREE;

public interface Link_DegreeDao {
	public ArrayList<ArrayList<String>> search_linkdegree(int degree,int specialization,String status);
	public TB_LINK_DEGREE getlinkdegreeByid(int id);
}
