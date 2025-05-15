package com.dao.psg.Civilian_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.psg.Civilian_Master.TB_PSG_MSTR_CADRE_CIVILIAN;


public interface Cadre_CivilianDAO {

	public ArrayList<ArrayList<String>> search_Cadre_civilian(String cadre,String status);
	public TB_PSG_MSTR_CADRE_CIVILIAN getCadreCivilianByid(int id);
	public ArrayList<ArrayList<String>> Cadre_civilian_report();
	public ArrayList<ArrayList<String>> GetAllcomm_details(String comm_id);
}
