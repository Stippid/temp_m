package com.controller.ExportFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.models.UserLogin;

public class ExportCSVFileController {
	DateWithTimeStampController file_name = new DateWithTimeStampController();
	public String exportToCSV(HttpServletResponse response,List<UserLogin> aList,String[] csvHeader1,String[] nameMapping) {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+file_name.currentDateWithTimeStampString()+".csv";
        response.setHeader(headerKey, headerValue);       
        try {
        	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        	csvWriter.writeHeader(csvHeader1);
        	
        	for (UserLogin user : aList) {
				csvWriter.write(user, nameMapping);
			}
        	csvWriter.close();
        	return "yes";
		} catch (IOException e) {
			return "no";
		}
    }
	
	public String exportToCSV_Map_String(HttpServletResponse response,List<Map<String, Object>> aList,String[] csvHeader1,String[] nameMapping) {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+file_name.currentDateWithTimeStampString()+".csv";
        response.setHeader(headerKey, headerValue);
        try {
        	ICsvMapWriter csvWriter = new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        	csvWriter.writeHeader(csvHeader1);
        	if(aList.size() > 0) {
	        	for (Map<String, Object> map : aList) {
					csvWriter.write(map, nameMapping);
				}
        	}
        	csvWriter.close();
        	return "yes";
		} catch (IOException e) {
			return "no";
		}
    }  
}