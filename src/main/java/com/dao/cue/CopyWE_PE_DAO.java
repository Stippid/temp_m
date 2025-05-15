package com.dao.cue;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;

public interface CopyWE_PE_DAO {
	
	
	public List<CUE_TB_MISO_WEPECONDITIONS> getWEPENODetailsList(String we_pe_no);
	
	
	/////--------- TRANSPORT
	public List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> getWEPENODetailsListFromTransFootnote(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> getWEPENODetailsListFromTransMDFS(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getWEPENODetailsListFromTransDet(String we_pe_no);
		
	////---------- PERSONNEL
	public List<CUE_TB_MISO_WEPE_PERS_DET> getWEPENODetailsListFromPersDet(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> getWEPENODetailsListFromPersFootnote(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_PERS_MDFS> getWEPENODetailsListFromPersMDFS(String we_pe_no);
	
	////---------- WEAPON
	public List<CUE_TB_MISO_WEPE_WEAPON_DET> getWEPENODetailsListFromWeapDet(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> getWEPENODetailsListFromWeapFootnote(String we_pe_no);
	public List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> getWEPENODetailsListFromWeapMDFS(String we_pe_no);
	
}
