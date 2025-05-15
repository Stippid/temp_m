package com.dao.mms;

import java.util.ArrayList;
import java.util.List;

public interface MMS_Depot_hldgDAO {
	public ArrayList<ArrayList<String>> depotMcrList(String nParaValue);
	public Boolean ifExistDepotRIONo(String rio_no);
	public Boolean ifExistDepotEqptRegNo(String eqpt_regn_no);
}