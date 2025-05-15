package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.util.ArrayList;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
public interface Change_Rank_History_DAO {
	public ArrayList<ArrayList<String>> change_rank(BigInteger comm_id,int census_id);
	

}
