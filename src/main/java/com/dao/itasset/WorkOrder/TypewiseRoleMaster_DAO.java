package com.dao.itasset.WorkOrder;

import java.util.ArrayList;
import java.util.List;

import com.models.assets.TB_IT_ASSET_TYPEWISE_ROLE_MASTER;


public interface TypewiseRoleMaster_DAO {
	
	public ArrayList<ArrayList<String>> getUnitType();

	public boolean typeWiseMasterDataSave(List<TB_IT_ASSET_TYPEWISE_ROLE_MASTER> roleMaster);

}
