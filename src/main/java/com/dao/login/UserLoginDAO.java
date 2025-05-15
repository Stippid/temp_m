package com.dao.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_ANALYTICS;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.UserLogin;

public interface UserLoginDAO {
	
	public void save(UserLogin login);
	public void update(UserLogin login);
	public void delete(UserLogin login);
	public UserLogin findByRoleId(int userId);
	public List<UserLogin> list();
	public List<UserLogin> list(int start,int end);
	public Boolean authenticate(String userName, String password);
	public UserLogin findUser(String userName, String password);
	public UserLogin findUser(String userName);
	public DataSet<UserLogin> findUsersWithDatatablesCriterias(DatatablesCriterias criterias);
	public int getUserId(String userName);
	public String getDist_Code(int userid);
	public int getst_Code(int userid);
	
	public List<TB_LDAP_MODULE_MASTER> getModulelistanalytics(int roleid,Boolean medical);

	public List<TB_LDAP_SCREEN_ANALYTICS> getAnalytics(int roleid,int userid);
	public List<TB_LDAP_SCREEN_MASTER> getScreenlist(int  moduleid,int submoduleid,int roleid,int screenid);

	public Boolean isUserExist(String userName);
	public Boolean isEmailExist(String email);
	public Boolean isMobileExist(String mobile);
	public List<TB_LDAP_SCREEN_MASTER> getScreenlist(int roleid,int screenid);
	public List<TB_LDAP_MODULE_MASTER> getModulelist(int roleid,Boolean medical);
	public List<TB_LDAP_SUBMODULE_MASTER> getSubModulelist(int  moduleid,int roleid);
	public List<TB_LDAP_SCREEN_MASTER> getScreenlist(int  moduleid,int submoduleid,int roleid);
	
	public List<TB_LDAP_SCREEN_MASTER> getScreenlistHQ(int roleid);
	public List<Map<String, Object>> getAnalyticswithout(int roleid,int userid);
	public List<Map<String, Object>> getAnalytics(DatatablesCriterias criterias,int roleid,int userid);
	public String getLevel_Type(int userId);
	public String gethealthType(int userId);
	public String getnameHE(int userId);
	public String gettype_HE(int userId);
	public String getEmailid(int userid);
	public String getMobileno(int userid);
	public String getOwnership(int userId);

	public String getAllUserLoginData(int userId);
	
	/////////////////////// 
	public int getRoleIdByUserId(int userId);
	public int ChangeStatusToPendingInFmcrOfAVeh();
	
	public String getRoleUrl(String role);
	public String getRoleType(String role);
	
	public Role findRole_url(String role);
	
	public  List<Map<String, Object>>  getSearchMercuryList1(String msg,String valid_upto);
	public Boolean getmsgExist(String msg, Date valid_upto);
	public List<String> getLayoutlist();
	public String getScreenUrlFromAnalytics(int roleid,int userid,int screen_id);
}
