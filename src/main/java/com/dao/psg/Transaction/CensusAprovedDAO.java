package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface CensusAprovedDAO {
	public List<Map<String, Integer>> approveDetailOfcensusByid(int id);

	String getcensusIdentityImagePath(String id);
	
	public int getcensusid(BigInteger comm_id);
	public int checkdatapendingintable(BigInteger comm_id ,String columnNameToExclude);
	public int checkdatapendingintablecomm(BigInteger comm_id ,String columnNameToExclude);

}
