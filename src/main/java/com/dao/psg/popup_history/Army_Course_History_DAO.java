package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.util.ArrayList;
public interface Army_Course_History_DAO {
	public ArrayList<ArrayList<String>> army_course(BigInteger comm_id,int census_id);

}
