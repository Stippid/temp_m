package com.dao.psg.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.models.psg.Report.TB_PSG_IAFF_3008_MAIN;

public interface Report_Serving_DAO {
	
	public ArrayList<ArrayList<String>> getldate(String LDate);
	public ArrayList<ArrayList<String>> Search_Report_Serving(String unit_sus_no,String FDate,String LDate,HttpSession session);
	public ArrayList<ArrayList<String>> Search_Report_3008_Super(String unit_sus_no,String FDate,String LDate,HttpSession session);
	public ArrayList<ArrayList<String>> Search_Report_3008_ReEmployed(String unit_sus_no,String FDate,String LDate,HttpSession session);
	public ArrayList<ArrayList<String>> Search_Report_3008_Deserter(String unit_sus_no,String FDate,String LDate,HttpSession session);
	public ArrayList<ArrayList<String>> Search_Report_3008_Auth_Held(String roleSusNo,String FDate,String LDate);
	//bisag v2 290822 (split auth and held)
	public ArrayList<ArrayList<String>> Search_Report_3008_Auth(String roleSusNo,String FDate,String LDate);
	public ArrayList<ArrayList<String>> Search_Report_3008_Held(String roleSusNo,String FDate,String LDate);
	//bisag v2 290822 (split auth and held) end
	
	public  List<TB_PSG_IAFF_3008_MAIN> Get_3008_VersionData(String month, String year, String roleSusNo);

	public ArrayList<ArrayList<String>> Search_Report_Serving_Approval(String month, String year , HttpSession session,String version ,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_3008_Super_Approval(String month, String year , HttpSession session,String version,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_3008_ReEmployed_Approval(String month, String year , HttpSession session,String version,String unit_sus_no);
	public ArrayList<ArrayList<String>> Search_Report_3008_Deserter_Approval(String month, String year , HttpSession session,String version,String unit_sus_no);

	///=====Chandani
		public ArrayList<ArrayList<String>> Search_Report_Version(String month, String year ,HttpSession session,String status,String unit_sus_no);
		public Boolean Delete_Report_3008(String username,String roleSusNo,String month, String year,String version) throws SQLException; 
		public String Delete_Report_3008_Version();
		public String Delete_Report_3008_Main();
		public String Delete_Report_3008_Serving();
		public String Delete_Report_3008_SuperNumarary();
		public String Delete_Report_3008_Re_Employment();
		public String Delete_Report_3008_Deserter();

		public Boolean Approve_Report_3008(String username,String roleSusNo,String month, String year,String version) throws SQLException; 
		public String Approve_Report_3008_Version();
		public String Approve_Report_3008_Main();
		public String Approve_Report_3008_Serving();
		public String Approve_Report_3008_SuperNumarary();
		public String Approve_Report_3008_Re_Employment();
		public String Approve_Report_3008_Deserter();
		//bisag v2 290822 (split auth and held)
		public String Approve_Report_3008_auth();
		public String Approve_Report_3008_held();
		//bisag v2 290822 (split auth and held) end
		public String Insert_Report_3008_Version();
		public String Update_Report_3008_Version();
		public String Insert_Report_3008_Main_Details();
	public String Insert_Report_3008_Serving();
	public String Insert_Report_3008_SuperNumarary();
	public String Insert_Report_3008_Re_Employeement();
	public String Insert_Report_3008_Deserter();
	//public Boolean Insert_Report_3008(String username,String roleSusNo,String FDate ,String month, String year ,String userId,String present_return_no,String present_return_dt,String observation,String LDate) throws SQLException ;
	public Boolean Insert_Report_3008(String username,String roleSusNo,String FDate ,String month, String year ,
			String userId,String present_return_no,String present_return_dt,String remarks,String LDate,String remarks1,String remarks2,String remarks3,String remarks4,String remarks5,String remarks6) throws SQLException ;
	
	
	//26-01-1994
	public ArrayList<String> getSusNoListForIAFF3008();
}
