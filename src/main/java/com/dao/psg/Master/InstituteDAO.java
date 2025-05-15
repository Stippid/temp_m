package com.dao.psg.Master;
import java.util.ArrayList;
import com.models.psg.Master.TB_INSTITUTE;

public interface InstituteDAO {
	public ArrayList<ArrayList<String>> search_Institute (String institute_name,String status);
	public TB_INSTITUTE getInstituteByid(int id);
	public ArrayList<ArrayList<String>> search_InstituteReport();
}
