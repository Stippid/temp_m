	package com.dao.tms;
	
	import java.util.ArrayList;
	import java.util.List;

import com.models.TB_ANIMALS_AWARD_MASTER;
import com.models.TB_TMS_ANIMAL_FITNESS_STATUS;
	import com.models.TB_TMS_TYPE_DOG;
	import com.models.TB_TMS_TYPE_OF_ANIMAL_MASTER;
import com.models.TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER;
import com.models.TMS_TB_MISO_BREED_MASTER;
	import com.models.TMS_TB_MISO_COLOR_MASTER;
import com.models.TMS_TB_MISO_EMPLOYMENT_MASTER;
import com.models.TMS_TB_MISO_SOURCE_MASTER;
	import com.models.TMS_TB_MISO_SPLZ_MASTER;
import com.models.TMS_TB_MISO_VACCINE_MASTER;
import com.models.TMS_TB_UE_MASTER;
	import com.models.Tms_animals_details;
	
	public interface Tms_AnimalDao {
	
		public String setApprovedAnimal(String sus_no);
	
		public String setRejectAnimal(String sus_no);
	
		public Tms_animals_details gettms_animals_detailsByid(int id);
	
		public String UpdateAnimalAttValue(Tms_animals_details Doc1AttValues, String username);
	
		public List<Tms_animals_details> getANIMAL_EDITid(int id);
	
		public Tms_animals_details gettms_animals_loc_detailsByid(int id);
	
		public String setDeleteAnimal(int id);
	
		public ArrayList<ArrayList<String>> getanimalstatus(String sus_no, String anml_type, String unit_name,
				String type_dog, String type_equines, String fdate, String tdate, String roleType);
	
		public ArrayList<ArrayList<String>> getdetailsUEList(String anml_type, String sus_no, String unit_name,
				String ue_of_dogs, int specialization, String roleType);
	
		public ArrayList<ArrayList<String>> getAttributeDataSearchAnimalUEArmyEqn(String anml_type, String sus_no,
				String unit_name, String ue_of_equins, int type_equines, String roleType);
	
		public ArrayList<ArrayList<String>> getanimallocstatus(String anml_type, String army_num, String microchip_no,
				String roleType);
	
		public ArrayList<ArrayList<String>> getanimallocstatusequ(String anml_type, String remount_no, String microchip_no,
				String roleType);
	
		public ArrayList<ArrayList<String>> getAnimalapprovelocReportList(String army_num, String microchip_no,
				String roleType);
	
		public ArrayList<ArrayList<String>> getAnimalReportListequ(String remount_no, String microchip_no,String roleType);
	
		public ArrayList<ArrayList<String>> getAnimalAllData(String sus_no, String anml_type, String unit_name,
				String type_dog, String type_equines, String fdate, String tdate, String comd_sus_no,
				String comd_unit_name,String animal_status);
	
		public ArrayList<ArrayList<String>> getAnimalReportListappro(String sus_no);
	
		public ArrayList<ArrayList<String>> search_st(String sus_no, String unit_name, String status, String fdate,
				String tdate, String roleType);
	
		public ArrayList<ArrayList<String>> dog_sum_list(String sus_no, String unit_name, String anml_type);
	
		public ArrayList<ArrayList<String>> equ_sum_list(String sus_no, String unit_name, String anml_type);
	
		public String updateBreed(TMS_TB_MISO_BREED_MASTER obj);
	
		public String updateColor(TMS_TB_MISO_COLOR_MASTER obj);
	
		public String updateSpz(TMS_TB_MISO_SPLZ_MASTER obj);
	
		public String updateSource(TMS_TB_MISO_SOURCE_MASTER obj);
	
		public String updateTypeDog(TB_TMS_TYPE_DOG obj);
	
		public String updateFitness(TB_TMS_ANIMAL_FITNESS_STATUS obj);
	
		public String updateTypeEqu(TB_TMS_TYPE_OF_ANIMAL_MASTER obj);
	
		public ArrayList<ArrayList<String>> getpendinglist(String sus_no, String role);
	
		public String updateUeDog(TMS_TB_UE_MASTER obj);
	
		public String updateUeEqu(TMS_TB_UE_MASTER obj);

		public ArrayList<ArrayList<String>> all_data_print_ue_uh(String sus_no, String anml_type, String unit_name,
				String type_dog, String type_equines, String comd_sus_no,
				String cont_corps,String cont_div,String cont_bde);
		
		public String updateHospital(TMS_TB_MISO_ANIMAL_HOSPITAL_MASTER obj);
		public String updateVaccine(TMS_TB_MISO_VACCINE_MASTER bm);
		public String updateEmployment(TMS_TB_MISO_EMPLOYMENT_MASTER bm);
		public String updateAward(TB_ANIMALS_AWARD_MASTER obj);
		
	
	}
