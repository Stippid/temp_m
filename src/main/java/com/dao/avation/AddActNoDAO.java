package com.dao.avation;

import java.math.BigInteger;

public interface AddActNoDAO {
	
	public Boolean ifExistActNo(BigInteger act);
	public Boolean ifExistRPASActNo(BigInteger act);

}
