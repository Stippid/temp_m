package com.dao.avation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_MISO_MSTR_COUNTRY;


//Created By Mitesh  (20-11-2024)

public interface TailNoDAO {

	public boolean ifExistActNo(String tail_no);
	public boolean ifExistRPASActNo(String tail_no);
	public boolean ifExistCHTLActNo(String tail_no);
	public @ResponseBody List<TB_MISO_MSTR_COUNTRY> Getallcountry() ;
	public ArrayList<ArrayList<String>> getTailNoCurrentStatus(String tail_no, String roleType);
	public ArrayList<ArrayList<String>> getCHTLTailNoCurrentStatus(String tail_no, String roleType);
	public ArrayList<ArrayList<String>> getRPASTailNoCurrentStatus(String tail_no, String roleType);
}
