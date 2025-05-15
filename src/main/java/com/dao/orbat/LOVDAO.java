package com.dao.orbat;



import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import com.models.Miso_Orbat_Unt_Dtl;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;

public interface LOVDAO {
	public DataSet<Miso_Orbat_Unt_Dtl> findLovOfUnitNameDatatablesCriteriasWithSusNo(DatatablesCriterias criterias ,String target_unit_name_lov1,String target_sus_no_lov1); 
}
