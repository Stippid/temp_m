package com.dao.psg.popup_history;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Add_Language_History_DAO {
	public ArrayList<ArrayList<String>> add_languages(BigInteger comm_id,int cen_id);
	public ArrayList<ArrayList<String>> add_forigen_languages(BigInteger comm_id,int cen_id);

}
