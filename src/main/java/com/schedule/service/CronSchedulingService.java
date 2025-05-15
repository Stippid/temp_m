package com.schedule.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.dao.Notification.notificatioDAO;
import com.dao.Reports.TMS_OLAP_CRONTABDAO;
import com.dao.psg.Report.ReportSearch_3009DAO;
import com.dao.psg.Report.Report_Serving_DAO;
//import com.dao.psg.Report.Report_Serving_mnsDAO;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAO;
import com.dao.psg.miso_olap.JDBCMISO_OLAPDAOImpl;
import com.dao.scheduleLock.scheduleLockDAO;
import com.dao.tms.TMSCronSchedulingDAO;

public class CronSchedulingService {
	@Autowired
	TMSCronSchedulingDAO trans;
	@Autowired
	TMS_OLAP_CRONTABDAO olap;

	@Autowired
	private Report_Serving_DAO RP;

	// @Autowired private Report_Serving_mnsDAO rsd;

	@Autowired
	private ReportSearch_3009DAO RS;
	
    @Autowired
    private scheduleLockDAO lockDAO;

	JDBCMISO_OLAPDAO o = new JDBCMISO_OLAPDAOImpl();
	
	private final String olapLockName = "olap_data_insert";

	@Scheduled(cron = "00 59 23 * * ?")
	public void monthEndSchedule() {
		final Calendar c = Calendar.getInstance();
		String date = String.valueOf(c.get(Calendar.DATE));

		String week = "";
		if (date.equals("7")) {
			week = "1";
			List<Map<String, Object>> list = trans.get_TRANSCTION_TMS_BVEH_UE_UH_DATA();
			if (list.size() > 0) {

			}
		}
		if (date.equals("14")) {
			week = "2";
			List<Map<String, Object>> list = trans.get_TRANSCTION_TMS_BVEH_UE_UH_DATA();
			if (list.size() > 0) {

			}
		}
		if (date.equals("21")) {
			week = "3";
			List<Map<String, Object>> list = trans.get_TRANSCTION_TMS_BVEH_UE_UH_DATA();
			if (list.size() > 0) {

			}
		}
		if (date.equals("28")) {
			week = "4";
			List<Map<String, Object>> list = trans.get_TRANSCTION_TMS_BVEH_UE_UH_DATA();
			if (list.size() > 0) {

			}
		}

	}

 //  @Scheduled(cron = "0 * * * * * ")
	@Scheduled(cron = "00 59 23 28-31 * ?")
	public void saveIAFF3008_MainDetails_olap() {
		 if (lockDAO.acquireLock(olapLockName)) {
			try {
				final Calendar c = Calendar.getInstance();
				if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
					Date datenow = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
					String fmdate = formatter.format(datenow);

					String Month1 = fmdate.substring(5, 7);
					//String year = fmdate.substring(0, 4);
					
					
					LocalDate currentDate = LocalDate.now();
					LocalDate previousMonth = currentDate.minusMonths(1);		
					int month = previousMonth.getMonthValue();
					int year = previousMonth.getYear();
					
					
					String formattedMonth = String.format("%02d",month);

					String FDate = fmdate.substring(0, 7) + "/" + "01";
					String LDate = year + Month1;					
				
					
					
					ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
					if (list_date.size() > 0) {
						LDate = String.valueOf(list_date.get(0).get(0));
					} else {
						LDate = FDate;
					}
					ArrayList<String> susNolist = RP.getSusNoListForIAFF3008();

					for (int i = 0; i < susNolist.size(); i++) {
						try {
							Boolean Insert = RP.Insert_Report_3008("MISO", susNolist.get(i), FDate, formattedMonth,  String.valueOf(year), "1",
									"", fmdate, "", LDate, "", "", "", "", "", "");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					/// INSERT DATA TO OLAP FROM TRNX DB ///
					o.iaff3008_MainDetails_olap();
					o.iaff3008_Serving_olap();
					o.iaff3008_SuperNumarary_olap();
					o.iaff3008_Re_EmoloyeeMent_olap();
					o.iaff3008_Deserter_olap();
				}
				
				
			} finally {
				lockDAO.releaseLock(olapLockName);
			}
		} else {
			System.out.println("Server "+ lockDAO.getServerIpAddress() + " skipped OLAP data insertion for "+ olapLockName );
		}
	}

	
	@Scheduled(cron = "00 59 23 28-31 * ?")
	
	public void saveIAFF3009_MainDetails_olap() {

		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			Date datenow = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
			String fmdate = formatter.format(datenow);

			String Month1 = fmdate.substring(5, 7);
			String year = fmdate.substring(0, 4);

			String FDate = fmdate.substring(0, 7) + "/" + "01";
			String LDate = year + Month1;
			ArrayList<ArrayList<String>> list_date = RP.getldate(LDate);
			if (list_date.size() > 0) {
				LDate = String.valueOf(list_date.get(0).get(0));
			} else {
				LDate = FDate;
			}
			ArrayList<String> susNolist = RS.getSusNoListForIAFF3009();

			for (int i = 0; i < susNolist.size(); i++) {
				try {
					Boolean Insert = RS.Insert_Report_3009("MISO", susNolist.get(i), FDate, Month1, year, "1", "",
							fmdate, "", LDate, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", "", "", "", "", "", "", "", "", "", "", true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			/// INSERT DATA TO OLAP FROM TRNX DB ///
			o.iaff3009_MainDetails_olap();
			o.iaff3009_authoffrs_olap();
			o.iaff3009_authciv_olap();
			o.iaff3009_postedoffrs_olap();
			o.iaff3009_postedciv_olap();
			o.iaff3009_rank_trad_olap();
			o.iaff3009_rel_denomination_olap();
			o.iaff3009_summary_olap();

			// bisag 240723 v2 (jco data trasmission on olap)
			/*
			 * o.iaff3009_rank_trad_olap_new(); o.iaff3009_rel_denomination_olap_new();
			 * o.iaff3009_summary_olap_new(); o.iaff3009_postedoffrs_olap_new();
			 * o.iaff3009_authoffrs_olap_new();
			 */
		}
	}

	// for mns officer
	

//	@Scheduled(cron = "00 59 23 28-31 * ?")
//	public void saveIAFF3008_MainDetails_mns_olap() {
//		// 26-01-1994
//		// unit iaff3008 data generation at month end
//		final Calendar c = Calendar.getInstance();
//		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
//			Date datenow = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
//			String fmdate = formatter.format(datenow);
//
//			String Month1 = fmdate.substring(5, 7);
//			String year = fmdate.substring(0, 4);
//
//			String FDate = fmdate.substring(0, 7) + "/" + "01";
//			String LDate = year + Month1;
//			ArrayList<ArrayList<String>> list_date = rsd.getldate(LDate);
//			if (list_date.size() > 0) {
//				LDate = String.valueOf(list_date.get(0).get(0));
//			} else {
//				LDate = FDate;
//			}
//			ArrayList<String> susNolist = rsd.getSusNoListForIAFF3008();
//
//			for (int i = 0; i < susNolist.size(); i++) {
//				try {
//					Boolean Insert = rsd.Insert_Report_3008("MISO", susNolist.get(i), FDate, Month1, year, "1", "",
//							fmdate, "", LDate);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			/// INSERT DATA TO OLAP FROM TRNX DB /// o.iaff3008_MainDetails_mns_olap();
//			o.iaff3008_Serving_olap_mns();
//			o.iaff3008_SuperNumarary_olap_mns();
//			o.iaff3008_Deserter_olap_mns();
//		}
//	}
	 

	@Autowired
	notificatioDAO notidao;

	@Scheduled(cron = "00 59 08 * * MON")
	public void send_notification() {

		notidao.sendnotificationSDMOV();
	}

}
