package com.dao.fpmis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

public interface FP_MIS_ReportsDAO {
	public List<String> getEncList(List<String> l,HttpSession s1);
	public ArrayList<ArrayList<String>> nFundStatus(String nParaValue, String nPara, String domainid);
	public ArrayList<ArrayList<String>> nFundStatusHQHead(HttpSession sessionA,String m1_tryear,String m1_nomen,String m1_nomenin,String m1_slvl,String m1_lvl,String m1_hdlvl,String nUsrId,String rsfmt);
	public ArrayList<ArrayList<String>> nFundInDetl(String nPara,String nUsrId);
	public int nFindFundL0(String id,HttpSession nSesn,String nFlag,String nUntTy, String m1_lvl,String rlLvl,String m1_tryear);
	public int nFindFundL2(String id,HttpSession nSesn,Connection nConn,String nFlag,String nUntTy,String m1_lvl,String rlLvl,String m1_tryear);
	public ArrayList<ArrayList<String>> nFundBEPrint(String m1_tryear,String m1_lvl,String m1_rpt,String nUsrId);
	public ArrayList<ArrayList<String>> nFundDraftPrint(String m1_tryear,String m1_lvl,String m1_rpt,String nUsrId,String m1_rptLvl, HttpSession sessionA,String rsfmt);
	public int nUpdUpload(String nUsrId);
	public int nUploadSubmit(HttpSession nSesn,String nPara,String nEstType,String m1_tryear);
	public ArrayList<ArrayList<String>> nGetAlertMsg(String nPara,String nUsrId);
	public ArrayList<ArrayList<String>> nFundDraftPrint_Ent(String m1_tryear,String m1_lvl,String m1_rpt,String nUsrId,String m1_rptLvl,String rsfmt);
	public ArrayList<ArrayList<String>> nCrFundDraftAllot(String m1_tryear,String m1_lvl,String para,String para1,HttpSession sessionA);
	public ArrayList<ArrayList<String>> nFundInfoDBDetl(String nPara,String nUsrId,String rolesus,String cfy,HttpSession sessionA);
	public ArrayList<ArrayList<String>> nFundInDetlTr(String nPara,String nUsrId,HttpSession sessionA);
	public String nFundbaseSql(String nPara,String nUsrId,String trhead,String susdetl,String cfy,HttpSession sessionA,String colList);
	public ArrayList<ArrayList<String>> nFundInfoDBReport(String rptName,String bhllst,String bhdlst,String coln,String dspty,String rptagr,String rolesus,String cfy,HttpSession sessionA);
	public ArrayList<ArrayList<String>> getUserDetails(String user_id);
	public boolean updateUserDetails(String user_id, String army_no, String sus_no, String uid, String sus_nodal);
	public List<Map<String, Object>> getAllUnitDtls(String param, int limit, HttpSession sess);
	public List<Map<String, Object>> encryptList(ResultSet rs, HttpSession s, List<String> skipCols);
	public ArrayList<ArrayList<String>> nFundStatusHQHeadCDB(HttpSession sessionA,String m1_tryear,String m1_nomen,String m1_nomenin,String m1_slvl,String m1_lvl,String m1_hdlvl,String nUsrId);
	public ArrayList<ArrayList<String>> nFundRecv(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,String m1_rptLvl, HttpSession sessionA, String rsfmt);
	public ArrayList<ArrayList<String>> nFundAllot(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,String m1_rptLvl, HttpSession sessionA, String rsfmt);
	public ArrayList<ArrayList<String>> nFundFlow(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,String m1_rptLvl, HttpSession sessionA, String rsfmt);
	public ArrayList<ArrayList<String>> nFundTree(String m1_tryear, String m1_lvl, String m1_rpt, String nUsrId,String m1_rptLvl, HttpSession sessionA, String rsfmt);
	public ArrayList<ArrayList<String>> getNewfaqdetail1();
	public ArrayList<ArrayList<String>> nLoginInfoData(HttpSession sessionA,String m1_tryear,String m1_nomen,String m1_nomenin,String m1_slvl,String m1_lvl,String m1_hdlvl,String nUsrId);
	public ArrayList<ArrayList<String>> getDOLetterData(String m1lvl,String m1rptlvl);
	public ArrayList<ArrayList<String>> fpCORBaseData(HttpSession sessionA,String nPara);
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_n(String nPara, String nUsrId, String rolesus, String cfy,HttpSession sessionA);
	public ArrayList<ArrayList<String>> nFundInfoDBDetl_n1(String nPara, String nUsrId, String rolesus, String cfy,HttpSession sessionA);
}
