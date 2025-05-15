package com.controller.psg.OfficerReport;

import java.sql.Timestamp;
import java.util.Date;

public class DateWithTimeStampController {
	 
	
	public String currentDateWithTimeStampString(){
		Date date = new Date();  
		Timestamp ts=new Timestamp(date.getTime());  
		return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
	}
}
