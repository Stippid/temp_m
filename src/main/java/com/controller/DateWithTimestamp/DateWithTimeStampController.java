package com.controller.DateWithTimestamp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateWithTimeStampController {
	 
	
	public String currentDateWithTimeStampString(){
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
	}
	
	
	public String getFromDateByMonthCount(int month) {
		 Calendar now = Calendar.getInstance();
		 now = Calendar.getInstance();
		 now.add(Calendar.MONTH, month);
		 
		 String y =  String.valueOf(now.get(Calendar.YEAR));
		 String m = String.valueOf(now.get(Calendar.MONTH) + 1);
		 String d = String.valueOf(now.get(Calendar.DATE));
		 
		 if(m.length() == 1) {
			 m = "0"+m;
		 }
		 if(d.length() == 1) {
			 d = "0"+d;
		 }
		 String fromDT= y + "-" +m+ "-" +d ;
		 return fromDT;
	}
	
	public String ConvertYYYY_MM_DDtoDD_MM_YYYY(Date date){
		if(date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String strDate = formatter.format(date);
		    return strDate;
		}else {
			return null;
		}
	}
}
