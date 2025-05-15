package com.controller.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MNH_ValidationController {

	
	
	public String rank_codeMSG = "Rank Should Contain Maximum 10 Digits.";
	public String icd_codeMSG = "ICD Code Should Contain Maximum 10 Digits.";
	public String dateMSG = "Date Should Contain Maximum 10 Characters";
	public String sys_codeMSG = "System Code Should Contain Maximum 30 Characters";
	public String amn_dateMSG = "Amn Date Should Contain Maximum 10 Characters";
	public String valid_uptoMSG = "Valid Date Should Contain Maximum 10 Characters";
	public String from_dateMSG = "From Date Should Contain Maximum 10 Digits.";	
	public String to_dateMSG = "To Date Should Contain Maximum 10 Digits.";
	public Boolean RankCodeLength(String rank_code) {
		if (rank_code.length() > 10) {
			return false; 
		} else {
			return true;
		}
	}
	
	public String rank_descMSG = "Rank Description Should Contain Maximum 100 Characters"; 
	public Boolean RankDescLength(String rank_desc) {
		if(rank_desc.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String icd_descriptionMSG = "ICD Description Should Contain Maximum 255 Characters";
	public String disease_principalMSG = "disease Principal Should Contain Maximum 255 Characters";
	public String disease_typeMSG = "disease Type Should Contain Maximum 255 Characters";
	public String block_descriptionMSG = "Block Description Should Contain Maximum 255 Characters";
	public String disease_subtypeMSG = "disease Sub Type Should Contain Maximum 255 Characters";
	public String disease_cr_typeMSG = "disease CR Type Should Contain Maximum 255 Characters";
	public String disease_cr_block_descriptionMSG = "disease CR Block Description Should Contain Maximum 255 Characters";
	public String short_descMSG = "ICD Short Description Should Contain Maximum 255 Characters";
	public String remarksMSG = "Remarks Should Contain Maximum 255 Characters";
	public String Icd_codeMSG = "ICD Code Should Contain Maximum 255 Characters";
	public String icd_causeMSG = "ICD Cause Should Contain Maximum 255 Characters";
	public String death_summaryMSG = "Death Summary Should Contain Maximum 255 Characters";
	public String address_of_nokMSG = "Address Of Nok Should Contain Maximum 255 Characters";
	public String cause_of_deathMSG = "Cause Of Death Should Contain Maximum 255 Characters";
	//public String remarksMSG = "Remark Should Contain Maximum 255 Characters";
	public Boolean IcdDescriptionLength(String icd_description) {
		if(icd_description.length() > 255) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String disease_mmrMSG = "disease MMR Should Contain Maximum 100 Characters";
	public String disease_asoMSG = "disease ASO Should Contain Maximum 100 Characters";
	public String short_formMSG = "ICD Short Form Contain Maximum 100 Characters";
	public String dept_nameMSG = "Department Name Contain Maximum 100 Characters";
	public String proc_nameMSG = "Procedure Name Contain Maximum 100 Characters";
	public String subproc_nameMSG = "Sub Procedure Name Contain Maximum 100 Characters";
	public String target_diseasesMSG = "Target disease Contain Maximum 100 Characters";
	public String target_diseases_subMSG = "Target Sub disease Contain Maximum 100 Characters";
	public String investigationMSG = "investigation Contain Maximum 100 Characters";
	public String sys_code_descMSG = "System Code Description Contain Maximum 100 Characters";
	public String unit_nameMSG = "Unit Name Should Contain Maximum 100 Characters";
	public String sho_fho_MSG = "SHO/FHO Name Should Contain Maximum 100 Characters";
	public String hospital_nameMSG = "Hospital Name Should Contain Maximum 100 Characters";
	public String NameMSG = "Name Contain Maximum 100 Characters";
	//public String hospital_nameMSG = "Hospital Name Contain Maximum 100 Characters";
	public String persnl_unitMSG = "Persnl Unit Contain Maximum 100 Characters";
	public String persnl_nameMSG = "Persnl Name Contain Maximum 100 Characters";
	public String oprationMSG = "Operation Contain Maximum 100 Characters";
	public String locationMSG = "Location Contain Maximum 100 Characters";
	public String bde_div_corps_comdMSG = "Bde Div Corps Comd Contain Maximum 100 Characters";
	public String name_of_nokMSG = "Name Of Nok Contain Maximum 100 Characters";

	public String UnitMSG = "Unit Contain Maximum 100 Characters";
	public Boolean DiseasemmrLength(String disease_mmr) {
		if(disease_mmr.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String disease_familyMSG = "disease Family Should Contain Maximum 50 Characters";
	public String disease_childrenMSG = "disease Children Should Contain Maximum 50 Characters";
	public String disease_type1MSG = "disease Type Should Contain Maximum 50 Characters";
	public String sys_code_valueMSG = "System Code Value Should Contain Maximum 50 Characters";
	public String FmnMSG = "FMN Should Contain Maximum 50 Characters";
	public String attributable_aggravatedMSG = "Attributable Aggravated Should Contain Maximum 50 Characters";
	public String sectorMSG = "Sector Should Contain Maximum 50 Characters";
	public String possible_place_siwswMSG = "Possible place Should Contain Maximum 50 Characters";
	public String appointmentMSG = "Appointment Should Contain Maximum 50 Characters";
	public Boolean DiseaseFamilyLength(String disease_family) {
		if(disease_family.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String disease_nameMSG = "disease Name Should Contain Maximum 150 Characters";
	public String sys_code_value_descMSG = "System Code Value Description Should Contain Maximum 150 Characters";
	public String persnl_unithivMSG = "Persnl Unit Contain Maximum 150 Characters";
	public Boolean DiseaseNameLength(String disease_name) {
		if(disease_name.length() > 150) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	
	public String messageMSG = "Message Should Contain Maximum 250 Characters";
	public String remarkMSG = "Remarks Should Contain Maximum 250 Characters";
	public String diagnosis_remarksMSG = "Diagnosis Remarks Should Contain Maximum 250 Characters";
	public String other_details_elisa_screeningMSG = "Details Should Contain Maximum 250 Characters";
	public String other_details_source_infectionMSG = "Details Should Contain Maximum 250 Characters";
	public String persl_unitMSG = "Personnel Unit Should Contain Maximum 250 Characters";
	public String diagnosisMSG = "Diagnosis Should Contain Maximum 250 Characters";
	public Boolean MessageLength(String message) {
		if(message.length() > 250) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	
	public String sus_noMSG = "Please Enter 8 Digit SUS no";
	public Boolean sus_noLength(String sus_no) {
		if(sus_no.length() != 8) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	
	public String persnl_no2MSG = "Persnl No  Contain Maximum 20 Characters";
	public Boolean persnl_noLength(String persnl_no) {
		if(persnl_no.length() > 20) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	
	public String and_noMSG = "Please Enter 15 Digit SUS no";
	public Boolean and_noLength(String and_no) {
	if(and_no.length() > 15) {
	    	return false;
	    }else {
	    	return true;
	    }
	
	}
	
	public String lab_reference_noMSG = "Lab reference No  Contain Maximum 40 Characters";
	public Boolean lab_reference_noLength(String lab_reference_no) {
	if(lab_reference_no.length() > 40) {
	    	return false;
	    }else {
	    	return true;
	    }
	
	}
	
	public String accession_noMSG = "Accession no Contain Maximum 30 Characters";
	public Boolean accession_noLength(String accession_no) {
		if(accession_no.length() > 30) {
			
		    	return false;
		    }else {
		    	return true;
		    }
		
		}
	
	public String yearMSG = "Year Should Contain Maximum 4 Characters"; 
	public Boolean checkYearLength(String yr1) 
	{
		if(yr1.length() > 4)
		{
			return false;
		}
		else
		{
		return true;
		}
	}
	
	public String adharnoMSG = "Adhaar No Should Contain Maximum 12 Or 14 Characters";
    public Boolean adhar_noLength(String adhar_card_no1) {
            if(adhar_card_no1.length() <= 12 && adhar_card_no1.length() >= 14) {
                   return false;
           }else {
                   return true;
           }
}

	
	public String check_future_quarter = "Future Quarter is not allowed to select";
	public Boolean check_future_quarter(String user_q) {
			
		 String curDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
		 
		
		 String Month = curDate.substring(3,5);
	
		 String quarter="";
		
		 int mon = Integer.parseInt(Month);
		
		 if(mon > 0 && mon <=3)
		 {
			 quarter="q1";
		 }
		 else if (mon > 3 && mon <=6)
		 {
			 quarter="q2";
		 }
		 else if (mon > 6 && mon <=9)
		 {
			 quarter="q3";
		 }
		 else
		 {
			 quarter="q4";
		 }
		
		 int u_q =Integer.parseInt(user_q.substring(1));
		 int c_q= Integer.parseInt(quarter.substring(1));
		 
		
		 
		 if(u_q > c_q)
		 {
			 return false;
		 }
		 else
		 {
			 return true;
		 }
		 
		 
		}
	
	public int CompareDate(Date from_date1, Date to_date1) {
		if (from_date1.compareTo(to_date1) < 0) {
			return 0;
		} else {
			return 1;
		}
	}
	

}
