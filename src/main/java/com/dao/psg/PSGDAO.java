package com.dao.psg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_PSG_UPLOAD;
import com.models.UploadDocumentMVCR;

public interface PSGDAO {
	public ArrayList<ArrayList<String>> search_psg_upload();
	public List<TB_PSG_UPLOAD> getDocumentPSGImg(int id,String file_name, HttpSession sessionA);
	
	 public DataSet<Map<String, Object>> DatatablesCriteriasPSGreport(DatatablesCriterias criterias, String qry ,String roleSubAccess);
}
