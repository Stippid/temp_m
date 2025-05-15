package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_pr_preventive_details", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Tb_Med_Pr_Preventive_Details {

	private int id;
	private String sho_name;
	private String sus_no;
	private String month;
	private int year;
	private String location;
	private Date date_from;
	private Date date_to;
	private String antilarval_measure_undertaken;
	
	private int residual_spray_done;
	private int entomonological_survey_done;
	private int units_inspected;
	private int sanitary_fittings_checked;
	private int number_of_free_chlorine_ckeck;
	private int chl_unsatisfactory;
	private int number_of_water_samples_sent_for_bacteriological_exam;
	private int bact_unsatisfactory;
	private int initial_inspection;
	private int periodic_inspection;
	
	private int malaria_number_of_slides_checked;
	private int malaria_p_vivax_officers;
	private int malaria_p_vivax_jcos;
	private int malaria_p_vivax_ors;
	private int malaria_p_vivax_family;
	
	private int malaria_p_falciparum_officers;	
	private int malaria_p_falciparum_jcos;
	private int malaria_p_falciparum_ors;
	private int malaria_p_falciparum_family;
	
	private String filaria_number_of_slides_checked;
	private int filaria_officers;
	private int filaria_jcos;
	private int filaria_ors;
	private int filaria_family;
	
	private int immunisation_bcg;
	private int immunisation_polio;
	private int immunisation_measles;
	private int immunisation_dpt;
	private int immunisation_hepatitis_b;
	private int immunisation_dt;
	private int immunisation_tt;
	
	private int special_pulse_polio;
	private int special_number_vaccinated;
	private int special_other_campaigns;
	private String special_details;
	
	
	private int cources_hygiene_and_sanitation;
	private int cources_vector_control;
	private int cources_water_supply;
	
	private int training_post_graduate_training;
	private int training_internee_officer_training;
	private int training_health_assistance_training;
	private int training_hygiene_sanitation_squad_training;
	
	
	private String modified_by;
	private String created_by;
	private Date modified_date;
	private Date created_date;
	
	private int causes_number_officers;
	private int causes_number_jcos;
	private int causes_number_ors;
	private int causes_rate_officers;
	private int causes_rate_jcos;
	private int causes_rate_ors;
	
	private int malaria_number_officers;
	private int malaria_number_jcos;
	private int malaria_number_ors;	
	private int malaria_rate_officers;
	private int malaria_rate_jcos;
	private int malaria_rate_ors;
	
	private int dengue_number_officers;
	private int dengue_number_jcos;
	private int dengue_number_ors;
	private int dengue_rate_officers;
	private int dengue_rate_jcos;
	private int dengue_rate_ors;	
	private String dengue_unit_name;
	
	private int chikungunya_number_officers;
	private int chikungunya_number_jcos;
	private int chikungunya_number_ors;
	private int chikungunya_rate_officers;
	private int chikungunya_rate_jcos;
	private int chikungunya_rate_ors;
	private String chikungunya_unit_name;
	
	private int std_number_officers;
	private int std_number_jcos;
	private int std_number_ors;
	private int std_rate_officers;
	private int std_rate_jcos;
	private int std_rate_ors;
	
	private int immune_surveillance_number_officers;
	private int immune_surveillance_number_jcos;
	private int immune_surveillance_number_ors;
	private int immune_surveillance_rate_officers;
	private int immune_surveillance_rate_jcos;
	private int immune_surveillance_rate_ors;
	
	private int viral_hepatitis_number_officers;
	private int viral_hepatitis_number_jcos;
	private int viral_hepatitis_number_ors;
	private int viral_hepatitis_rate_officers;
	private int viral_hepatitis_rate_jcos;
	private int viral_hepatitis_rate_ors;
	
	private int diarrhoea_number_officers;
	private int diarrhoea_number_jcos;
	private int diarrhoea_number_ors;
	private int diarrhoea_rate_officers;
	private int diarrhoea_rate_jcos;
	private int diarrhoea_rate_ors;
	
	private int respiratory_number_officers;
	private int respiratory_number_jcos;
	private int respiratory_number_ors;
	private int respiratory_rate_officers;
	private int respiratory_rate_jcos;
	private int respiratory_rate_ors;
	
	
	private int h1n1_influenza_number_officers;
	private int h1n1_influenza_number_jcos;
	private int h1n1_influenza_number_ors;
	private int h1n1_influenza_rate_officers;
	private int h1n1_influenza_rate_jcos;
	private int h1n1_influenza_rate_ors;
	
	
	private int skin_disease_number_officers;
	private int skin_disease_number_jcos;
	private int skin_disease_number_ors;
	private int skin_disease_rate_officers;
	private int skin_disease_rate_jcos;
	private int skin_disease_rate_ors;
	
	
	private int injuries_nea_number_officers;
	private int injuries_nea_number_jcos;
	private int injuries_nea_number_ors;
	private int injuries_nea_rate_officers;
	private int injuries_nea_rate_jcos;
	private int injuries_nea_rate_ors;
	
	
	
	private int effect_of_heat_number_officers;
	private int effect_of_heat_number_jcos;
	private int effect_of_heat_number_ors;
	private int effect_of_heat_rate_officers;
	private int effect_of_heat_rate_jcos;
	private int effect_of_heat_rate_ors;
	
	

	private int effect_of_cold_number_officers;
	private int effect_of_cold_number_jcos;
	private int effect_of_cold_number_ors;
	private int effect_of_cold_rate_officers;
	private int effect_of_cold_rate_jcos;
	private int effect_of_cold_rate_ors;
	
	private int effect_of_high_altitude_number_officers;
	private int effect_of_high_altitude_number_jcos;
	private int effect_of_high_altitude_number_ors;
	private int effect_of_high_altitude_rate_officers;
	private int effect_of_high_altitude_rate_jcos;
	private int effect_of_high_altitude_rate_ors;
	
	
	private int pulmonary_tuberculosis_number_officers;
	private int pulmonary_tuberculosis_number_jcos;
	private int pulmonary_tuberculosis_number_ors;
	private int pulmonary_tuberculosis_rate_officer;
	private int pulmonary_tuberculosis_rate_jcos;
	private int pulmonary_tuberculosis_rate_ors;
	
	
	
	private int number_of_dog_bites;
	private int number_of_rabies_cases;
	private int number_of_snake_bite_cases;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSho_name() {
		return sho_name;
	}

	public void setSho_name(String sho_name) {
		this.sho_name = sho_name;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	public String getAntilarval_measure_undertaken() {
		return antilarval_measure_undertaken;
	}

	public void setAntilarval_measure_undertaken(String antilarval_measure_undertaken) {
		this.antilarval_measure_undertaken = antilarval_measure_undertaken;
	}

	public int getResidual_spray_done() {
		return residual_spray_done;
	}

	public void setResidual_spray_done(int residual_spray_done) {
		this.residual_spray_done = residual_spray_done;
	}

	public int getEntomonological_survey_done() {
		return entomonological_survey_done;
	}

	public void setEntomonological_survey_done(int entomonological_survey_done) {
		this.entomonological_survey_done = entomonological_survey_done;
	}

	public int getUnits_inspected() {
		return units_inspected;
	}

	public void setUnits_inspected(int units_inspected) {
		this.units_inspected = units_inspected;
	}

	public int getSanitary_fittings_checked() {
		return sanitary_fittings_checked;
	}

	public void setSanitary_fittings_checked(int sanitary_fittings_checked) {
		this.sanitary_fittings_checked = sanitary_fittings_checked;
	}

	public int getNumber_of_free_chlorine_ckeck() {
		return number_of_free_chlorine_ckeck;
	}

	public void setNumber_of_free_chlorine_ckeck(int number_of_free_chlorine_ckeck) {
		this.number_of_free_chlorine_ckeck = number_of_free_chlorine_ckeck;
	}

	public int getChl_unsatisfactory() {
		return chl_unsatisfactory;
	}

	public void setChl_unsatisfactory(int chl_unsatisfactory) {
		this.chl_unsatisfactory = chl_unsatisfactory;
	}

	public int getNumber_of_water_samples_sent_for_bacteriological_exam() {
		return number_of_water_samples_sent_for_bacteriological_exam;
	}

	public void setNumber_of_water_samples_sent_for_bacteriological_exam(
			int number_of_water_samples_sent_for_bacteriological_exam) {
		this.number_of_water_samples_sent_for_bacteriological_exam = number_of_water_samples_sent_for_bacteriological_exam;
	}

	public int getBact_unsatisfactory() {
		return bact_unsatisfactory;
	}

	public void setBact_unsatisfactory(int bact_unsatisfactory) {
		this.bact_unsatisfactory = bact_unsatisfactory;
	}

	public int getInitial_inspection() {
		return initial_inspection;
	}

	public void setInitial_inspection(int initial_inspection) {
		this.initial_inspection = initial_inspection;
	}

	public int getPeriodic_inspection() {
		return periodic_inspection;
	}

	public void setPeriodic_inspection(int periodic_inspection) {
		this.periodic_inspection = periodic_inspection;
	}

	public int getMalaria_number_of_slides_checked() {
		return malaria_number_of_slides_checked;
	}

	public void setMalaria_number_of_slides_checked(int malaria_number_of_slides_checked) {
		this.malaria_number_of_slides_checked = malaria_number_of_slides_checked;
	}

	public int getMalaria_p_vivax_officers() {
		return malaria_p_vivax_officers;
	}

	public void setMalaria_p_vivax_officers(int malaria_p_vivax_officers) {
		this.malaria_p_vivax_officers = malaria_p_vivax_officers;
	}

	public int getMalaria_p_vivax_jcos() {
		return malaria_p_vivax_jcos;
	}

	public void setMalaria_p_vivax_jcos(int malaria_p_vivax_jcos) {
		this.malaria_p_vivax_jcos = malaria_p_vivax_jcos;
	}

	public int getMalaria_p_vivax_ors() {
		return malaria_p_vivax_ors;
	}

	public void setMalaria_p_vivax_ors(int malaria_p_vivax_ors) {
		this.malaria_p_vivax_ors = malaria_p_vivax_ors;
	}

	public int getMalaria_p_vivax_family() {
		return malaria_p_vivax_family;
	}

	public void setMalaria_p_vivax_family(int malaria_p_vivax_family) {
		this.malaria_p_vivax_family = malaria_p_vivax_family;
	}

	public int getMalaria_p_falciparum_officers() {
		return malaria_p_falciparum_officers;
	}

	public void setMalaria_p_falciparum_officers(int malaria_p_falciparum_officers) {
		this.malaria_p_falciparum_officers = malaria_p_falciparum_officers;
	}

	public int getMalaria_p_falciparum_jcos() {
		return malaria_p_falciparum_jcos;
	}

	public void setMalaria_p_falciparum_jcos(int malaria_p_falciparum_jcos) {
		this.malaria_p_falciparum_jcos = malaria_p_falciparum_jcos;
	}

	public int getMalaria_p_falciparum_ors() {
		return malaria_p_falciparum_ors;
	}

	public void setMalaria_p_falciparum_ors(int malaria_p_falciparum_ors) {
		this.malaria_p_falciparum_ors = malaria_p_falciparum_ors;
	}

	public int getMalaria_p_falciparum_family() {
		return malaria_p_falciparum_family;
	}

	public void setMalaria_p_falciparum_family(int malaria_p_falciparum_family) {
		this.malaria_p_falciparum_family = malaria_p_falciparum_family;
	}

	public String getFilaria_number_of_slides_checked() {
		return filaria_number_of_slides_checked;
	}

	public void setFilaria_number_of_slides_checked(String filaria_number_of_slides_checked) {
		this.filaria_number_of_slides_checked = filaria_number_of_slides_checked;
	}

	public int getFilaria_officers() {
		return filaria_officers;
	}

	public void setFilaria_officers(int filaria_officers) {
		this.filaria_officers = filaria_officers;
	}

	public int getFilaria_jcos() {
		return filaria_jcos;
	}

	public void setFilaria_jcos(int filaria_jcos) {
		this.filaria_jcos = filaria_jcos;
	}

	public int getFilaria_ors() {
		return filaria_ors;
	}

	public void setFilaria_ors(int filaria_ors) {
		this.filaria_ors = filaria_ors;
	}

	public int getFilaria_family() {
		return filaria_family;
	}

	public void setFilaria_family(int filaria_family) {
		this.filaria_family = filaria_family;
	}

	public int getImmunisation_bcg() {
		return immunisation_bcg;
	}

	public void setImmunisation_bcg(int immunisation_bcg) {
		this.immunisation_bcg = immunisation_bcg;
	}

	public int getImmunisation_polio() {
		return immunisation_polio;
	}

	public void setImmunisation_polio(int immunisation_polio) {
		this.immunisation_polio = immunisation_polio;
	}

	public int getImmunisation_measles() {
		return immunisation_measles;
	}

	public void setImmunisation_measles(int immunisation_measles) {
		this.immunisation_measles = immunisation_measles;
	}

	public int getImmunisation_dpt() {
		return immunisation_dpt;
	}

	public void setImmunisation_dpt(int immunisation_dpt) {
		this.immunisation_dpt = immunisation_dpt;
	}

	public int getImmunisation_hepatitis_b() {
		return immunisation_hepatitis_b;
	}

	public void setImmunisation_hepatitis_b(int immunisation_hepatitis_b) {
		this.immunisation_hepatitis_b = immunisation_hepatitis_b;
	}

	public int getImmunisation_dt() {
		return immunisation_dt;
	}

	public void setImmunisation_dt(int immunisation_dt) {
		this.immunisation_dt = immunisation_dt;
	}

	public int getImmunisation_tt() {
		return immunisation_tt;
	}

	public void setImmunisation_tt(int immunisation_tt) {
		this.immunisation_tt = immunisation_tt;
	}

	public int getSpecial_pulse_polio() {
		return special_pulse_polio;
	}

	public void setSpecial_pulse_polio(int special_pulse_polio) {
		this.special_pulse_polio = special_pulse_polio;
	}

	public int getSpecial_number_vaccinated() {
		return special_number_vaccinated;
	}

	public void setSpecial_number_vaccinated(int special_number_vaccinated) {
		this.special_number_vaccinated = special_number_vaccinated;
	}

	public int getSpecial_other_campaigns() {
		return special_other_campaigns;
	}

	public void setSpecial_other_campaigns(int special_other_campaigns) {
		this.special_other_campaigns = special_other_campaigns;
	}

	public String getSpecial_details() {
		return special_details;
	}

	public void setSpecial_details(String special_details) {
		this.special_details = special_details;
	}

	public int getCources_hygiene_and_sanitation() {
		return cources_hygiene_and_sanitation;
	}

	public void setCources_hygiene_and_sanitation(int cources_hygiene_and_sanitation) {
		this.cources_hygiene_and_sanitation = cources_hygiene_and_sanitation;
	}

	public int getCources_vector_control() {
		return cources_vector_control;
	}

	public void setCources_vector_control(int cources_vector_control) {
		this.cources_vector_control = cources_vector_control;
	}

	public int getCources_water_supply() {
		return cources_water_supply;
	}

	public void setCources_water_supply(int cources_water_supply) {
		this.cources_water_supply = cources_water_supply;
	}

	public int getTraining_post_graduate_training() {
		return training_post_graduate_training;
	}

	public void setTraining_post_graduate_training(int training_post_graduate_training) {
		this.training_post_graduate_training = training_post_graduate_training;
	}

	public int getTraining_internee_officer_training() {
		return training_internee_officer_training;
	}

	public void setTraining_internee_officer_training(int training_internee_officer_training) {
		this.training_internee_officer_training = training_internee_officer_training;
	}

	public int getTraining_health_assistance_training() {
		return training_health_assistance_training;
	}

	public void setTraining_health_assistance_training(int training_health_assistance_training) {
		this.training_health_assistance_training = training_health_assistance_training;
	}

	public int getTraining_hygiene_sanitation_squad_training() {
		return training_hygiene_sanitation_squad_training;
	}

	public void setTraining_hygiene_sanitation_squad_training(int training_hygiene_sanitation_squad_training) {
		this.training_hygiene_sanitation_squad_training = training_hygiene_sanitation_squad_training;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getCauses_number_officers() {
		return causes_number_officers;
	}

	public void setCauses_number_officers(int causes_number_officers) {
		this.causes_number_officers = causes_number_officers;
	}

	public int getCauses_number_jcos() {
		return causes_number_jcos;
	}

	public void setCauses_number_jcos(int causes_number_jcos) {
		this.causes_number_jcos = causes_number_jcos;
	}

	public int getCauses_number_ors() {
		return causes_number_ors;
	}

	public void setCauses_number_ors(int causes_number_ors) {
		this.causes_number_ors = causes_number_ors;
	}

	public int getCauses_rate_officers() {
		return causes_rate_officers;
	}

	public void setCauses_rate_officers(int causes_rate_officers) {
		this.causes_rate_officers = causes_rate_officers;
	}

	public int getCauses_rate_jcos() {
		return causes_rate_jcos;
	}

	public void setCauses_rate_jcos(int causes_rate_jcos) {
		this.causes_rate_jcos = causes_rate_jcos;
	}

	public int getCauses_rate_ors() {
		return causes_rate_ors;
	}

	public void setCauses_rate_ors(int causes_rate_ors) {
		this.causes_rate_ors = causes_rate_ors;
	}

	public int getMalaria_number_officers() {
		return malaria_number_officers;
	}

	public void setMalaria_number_officers(int malaria_number_officers) {
		this.malaria_number_officers = malaria_number_officers;
	}

	public int getMalaria_number_jcos() {
		return malaria_number_jcos;
	}

	public void setMalaria_number_jcos(int malaria_number_jcos) {
		this.malaria_number_jcos = malaria_number_jcos;
	}

	public int getMalaria_number_ors() {
		return malaria_number_ors;
	}

	public void setMalaria_number_ors(int malaria_number_ors) {
		this.malaria_number_ors = malaria_number_ors;
	}

	public int getMalaria_rate_officers() {
		return malaria_rate_officers;
	}

	public void setMalaria_rate_officers(int malaria_rate_officers) {
		this.malaria_rate_officers = malaria_rate_officers;
	}

	public int getMalaria_rate_jcos() {
		return malaria_rate_jcos;
	}

	public void setMalaria_rate_jcos(int malaria_rate_jcos) {
		this.malaria_rate_jcos = malaria_rate_jcos;
	}

	public int getMalaria_rate_ors() {
		return malaria_rate_ors;
	}

	public void setMalaria_rate_ors(int malaria_rate_ors) {
		this.malaria_rate_ors = malaria_rate_ors;
	}

	public int getDengue_number_officers() {
		return dengue_number_officers;
	}

	public void setDengue_number_officers(int dengue_number_officers) {
		this.dengue_number_officers = dengue_number_officers;
	}

	public int getDengue_number_jcos() {
		return dengue_number_jcos;
	}

	public void setDengue_number_jcos(int dengue_number_jcos) {
		this.dengue_number_jcos = dengue_number_jcos;
	}

	public int getDengue_number_ors() {
		return dengue_number_ors;
	}

	public void setDengue_number_ors(int dengue_number_ors) {
		this.dengue_number_ors = dengue_number_ors;
	}

	public int getDengue_rate_officers() {
		return dengue_rate_officers;
	}

	public void setDengue_rate_officers(int dengue_rate_officers) {
		this.dengue_rate_officers = dengue_rate_officers;
	}

	public int getDengue_rate_jcos() {
		return dengue_rate_jcos;
	}

	public void setDengue_rate_jcos(int dengue_rate_jcos) {
		this.dengue_rate_jcos = dengue_rate_jcos;
	}

	public int getDengue_rate_ors() {
		return dengue_rate_ors;
	}

	public void setDengue_rate_ors(int dengue_rate_ors) {
		this.dengue_rate_ors = dengue_rate_ors;
	}

	public String getDengue_unit_name() {
		return dengue_unit_name;
	}

	public void setDengue_unit_name(String dengue_unit_name) {
		this.dengue_unit_name = dengue_unit_name;
	}

	public int getChikungunya_number_officers() {
		return chikungunya_number_officers;
	}

	public void setChikungunya_number_officers(int chikungunya_number_officers) {
		this.chikungunya_number_officers = chikungunya_number_officers;
	}

	public int getChikungunya_number_jcos() {
		return chikungunya_number_jcos;
	}

	public void setChikungunya_number_jcos(int chikungunya_number_jcos) {
		this.chikungunya_number_jcos = chikungunya_number_jcos;
	}

	public int getChikungunya_number_ors() {
		return chikungunya_number_ors;
	}

	public void setChikungunya_number_ors(int chikungunya_number_ors) {
		this.chikungunya_number_ors = chikungunya_number_ors;
	}

	public int getChikungunya_rate_officers() {
		return chikungunya_rate_officers;
	}

	public void setChikungunya_rate_officers(int chikungunya_rate_officers) {
		this.chikungunya_rate_officers = chikungunya_rate_officers;
	}

	public int getChikungunya_rate_jcos() {
		return chikungunya_rate_jcos;
	}

	public void setChikungunya_rate_jcos(int chikungunya_rate_jcos) {
		this.chikungunya_rate_jcos = chikungunya_rate_jcos;
	}

	public int getChikungunya_rate_ors() {
		return chikungunya_rate_ors;
	}

	public void setChikungunya_rate_ors(int chikungunya_rate_ors) {
		this.chikungunya_rate_ors = chikungunya_rate_ors;
	}

	public String getChikungunya_unit_name() {
		return chikungunya_unit_name;
	}

	public void setChikungunya_unit_name(String chikungunya_unit_name) {
		this.chikungunya_unit_name = chikungunya_unit_name;
	}

	public int getStd_number_officers() {
		return std_number_officers;
	}

	public void setStd_number_officers(int std_number_officers) {
		this.std_number_officers = std_number_officers;
	}

	public int getStd_number_jcos() {
		return std_number_jcos;
	}

	public void setStd_number_jcos(int std_number_jcos) {
		this.std_number_jcos = std_number_jcos;
	}

	public int getStd_number_ors() {
		return std_number_ors;
	}

	public void setStd_number_ors(int std_number_ors) {
		this.std_number_ors = std_number_ors;
	}

	public int getStd_rate_officers() {
		return std_rate_officers;
	}

	public void setStd_rate_officers(int std_rate_officers) {
		this.std_rate_officers = std_rate_officers;
	}

	public int getStd_rate_jcos() {
		return std_rate_jcos;
	}

	public void setStd_rate_jcos(int std_rate_jcos) {
		this.std_rate_jcos = std_rate_jcos;
	}

	public int getStd_rate_ors() {
		return std_rate_ors;
	}

	public void setStd_rate_ors(int std_rate_ors) {
		this.std_rate_ors = std_rate_ors;
	}

	public int getImmune_surveillance_number_officers() {
		return immune_surveillance_number_officers;
	}

	public void setImmune_surveillance_number_officers(int immune_surveillance_number_officers) {
		this.immune_surveillance_number_officers = immune_surveillance_number_officers;
	}

	public int getImmune_surveillance_number_jcos() {
		return immune_surveillance_number_jcos;
	}

	public void setImmune_surveillance_number_jcos(int immune_surveillance_number_jcos) {
		this.immune_surveillance_number_jcos = immune_surveillance_number_jcos;
	}

	public int getImmune_surveillance_number_ors() {
		return immune_surveillance_number_ors;
	}

	public void setImmune_surveillance_number_ors(int immune_surveillance_number_ors) {
		this.immune_surveillance_number_ors = immune_surveillance_number_ors;
	}

	public int getImmune_surveillance_rate_officers() {
		return immune_surveillance_rate_officers;
	}

	public void setImmune_surveillance_rate_officers(int immune_surveillance_rate_officers) {
		this.immune_surveillance_rate_officers = immune_surveillance_rate_officers;
	}

	public int getImmune_surveillance_rate_jcos() {
		return immune_surveillance_rate_jcos;
	}

	public void setImmune_surveillance_rate_jcos(int immune_surveillance_rate_jcos) {
		this.immune_surveillance_rate_jcos = immune_surveillance_rate_jcos;
	}

	public int getImmune_surveillance_rate_ors() {
		return immune_surveillance_rate_ors;
	}

	public void setImmune_surveillance_rate_ors(int immune_surveillance_rate_ors) {
		this.immune_surveillance_rate_ors = immune_surveillance_rate_ors;
	}

	public int getViral_hepatitis_number_officers() {
		return viral_hepatitis_number_officers;
	}

	public void setViral_hepatitis_number_officers(int viral_hepatitis_number_officers) {
		this.viral_hepatitis_number_officers = viral_hepatitis_number_officers;
	}

	public int getViral_hepatitis_number_jcos() {
		return viral_hepatitis_number_jcos;
	}

	public void setViral_hepatitis_number_jcos(int viral_hepatitis_number_jcos) {
		this.viral_hepatitis_number_jcos = viral_hepatitis_number_jcos;
	}

	public int getViral_hepatitis_number_ors() {
		return viral_hepatitis_number_ors;
	}

	public void setViral_hepatitis_number_ors(int viral_hepatitis_number_ors) {
		this.viral_hepatitis_number_ors = viral_hepatitis_number_ors;
	}

	public int getViral_hepatitis_rate_officers() {
		return viral_hepatitis_rate_officers;
	}

	public void setViral_hepatitis_rate_officers(int viral_hepatitis_rate_officers) {
		this.viral_hepatitis_rate_officers = viral_hepatitis_rate_officers;
	}

	public int getViral_hepatitis_rate_jcos() {
		return viral_hepatitis_rate_jcos;
	}

	public void setViral_hepatitis_rate_jcos(int viral_hepatitis_rate_jcos) {
		this.viral_hepatitis_rate_jcos = viral_hepatitis_rate_jcos;
	}

	public int getViral_hepatitis_rate_ors() {
		return viral_hepatitis_rate_ors;
	}

	public void setViral_hepatitis_rate_ors(int viral_hepatitis_rate_ors) {
		this.viral_hepatitis_rate_ors = viral_hepatitis_rate_ors;
	}

	public int getDiarrhoea_number_officers() {
		return diarrhoea_number_officers;
	}

	public void setDiarrhoea_number_officers(int diarrhoea_number_officers) {
		this.diarrhoea_number_officers = diarrhoea_number_officers;
	}

	public int getDiarrhoea_number_jcos() {
		return diarrhoea_number_jcos;
	}

	public void setDiarrhoea_number_jcos(int diarrhoea_number_jcos) {
		this.diarrhoea_number_jcos = diarrhoea_number_jcos;
	}

	public int getDiarrhoea_number_ors() {
		return diarrhoea_number_ors;
	}

	public void setDiarrhoea_number_ors(int diarrhoea_number_ors) {
		this.diarrhoea_number_ors = diarrhoea_number_ors;
	}

	public int getDiarrhoea_rate_officers() {
		return diarrhoea_rate_officers;
	}

	public void setDiarrhoea_rate_officers(int diarrhoea_rate_officers) {
		this.diarrhoea_rate_officers = diarrhoea_rate_officers;
	}

	public int getDiarrhoea_rate_jcos() {
		return diarrhoea_rate_jcos;
	}

	public void setDiarrhoea_rate_jcos(int diarrhoea_rate_jcos) {
		this.diarrhoea_rate_jcos = diarrhoea_rate_jcos;
	}

	public int getDiarrhoea_rate_ors() {
		return diarrhoea_rate_ors;
	}

	public void setDiarrhoea_rate_ors(int diarrhoea_rate_ors) {
		this.diarrhoea_rate_ors = diarrhoea_rate_ors;
	}

	public int getRespiratory_number_officers() {
		return respiratory_number_officers;
	}

	public void setRespiratory_number_officers(int respiratory_number_officers) {
		this.respiratory_number_officers = respiratory_number_officers;
	}

	public int getRespiratory_number_jcos() {
		return respiratory_number_jcos;
	}

	public void setRespiratory_number_jcos(int respiratory_number_jcos) {
		this.respiratory_number_jcos = respiratory_number_jcos;
	}

	public int getRespiratory_number_ors() {
		return respiratory_number_ors;
	}

	public void setRespiratory_number_ors(int respiratory_number_ors) {
		this.respiratory_number_ors = respiratory_number_ors;
	}

	public int getRespiratory_rate_officers() {
		return respiratory_rate_officers;
	}

	public void setRespiratory_rate_officers(int respiratory_rate_officers) {
		this.respiratory_rate_officers = respiratory_rate_officers;
	}

	public int getRespiratory_rate_jcos() {
		return respiratory_rate_jcos;
	}

	public void setRespiratory_rate_jcos(int respiratory_rate_jcos) {
		this.respiratory_rate_jcos = respiratory_rate_jcos;
	}

	public int getRespiratory_rate_ors() {
		return respiratory_rate_ors;
	}

	public void setRespiratory_rate_ors(int respiratory_rate_ors) {
		this.respiratory_rate_ors = respiratory_rate_ors;
	}

	public int getH1n1_influenza_number_officers() {
		return h1n1_influenza_number_officers;
	}

	public void setH1n1_influenza_number_officers(int h1n1_influenza_number_officers) {
		this.h1n1_influenza_number_officers = h1n1_influenza_number_officers;
	}

	public int getH1n1_influenza_number_jcos() {
		return h1n1_influenza_number_jcos;
	}

	public void setH1n1_influenza_number_jcos(int h1n1_influenza_number_jcos) {
		this.h1n1_influenza_number_jcos = h1n1_influenza_number_jcos;
	}

	public int getH1n1_influenza_number_ors() {
		return h1n1_influenza_number_ors;
	}

	public void setH1n1_influenza_number_ors(int h1n1_influenza_number_ors) {
		this.h1n1_influenza_number_ors = h1n1_influenza_number_ors;
	}

	public int getH1n1_influenza_rate_officers() {
		return h1n1_influenza_rate_officers;
	}

	public void setH1n1_influenza_rate_officers(int h1n1_influenza_rate_officers) {
		this.h1n1_influenza_rate_officers = h1n1_influenza_rate_officers;
	}

	public int getH1n1_influenza_rate_jcos() {
		return h1n1_influenza_rate_jcos;
	}

	public void setH1n1_influenza_rate_jcos(int h1n1_influenza_rate_jcos) {
		this.h1n1_influenza_rate_jcos = h1n1_influenza_rate_jcos;
	}

	public int getH1n1_influenza_rate_ors() {
		return h1n1_influenza_rate_ors;
	}

	public void setH1n1_influenza_rate_ors(int h1n1_influenza_rate_ors) {
		this.h1n1_influenza_rate_ors = h1n1_influenza_rate_ors;
	}

	public int getSkin_disease_number_officers() {
		return skin_disease_number_officers;
	}

	public void setSkin_disease_number_officers(int skin_disease_number_officers) {
		this.skin_disease_number_officers = skin_disease_number_officers;
	}

	public int getSkin_disease_number_jcos() {
		return skin_disease_number_jcos;
	}

	public void setSkin_disease_number_jcos(int skin_disease_number_jcos) {
		this.skin_disease_number_jcos = skin_disease_number_jcos;
	}

	public int getSkin_disease_number_ors() {
		return skin_disease_number_ors;
	}

	public void setSkin_disease_number_ors(int skin_disease_number_ors) {
		this.skin_disease_number_ors = skin_disease_number_ors;
	}

	public int getSkin_disease_rate_officers() {
		return skin_disease_rate_officers;
	}

	public void setSkin_disease_rate_officers(int skin_disease_rate_officers) {
		this.skin_disease_rate_officers = skin_disease_rate_officers;
	}

	public int getSkin_disease_rate_jcos() {
		return skin_disease_rate_jcos;
	}

	public void setSkin_disease_rate_jcos(int skin_disease_rate_jcos) {
		this.skin_disease_rate_jcos = skin_disease_rate_jcos;
	}

	public int getSkin_disease_rate_ors() {
		return skin_disease_rate_ors;
	}

	public void setSkin_disease_rate_ors(int skin_disease_rate_ors) {
		this.skin_disease_rate_ors = skin_disease_rate_ors;
	}

	public int getInjuries_nea_number_officers() {
		return injuries_nea_number_officers;
	}

	public void setInjuries_nea_number_officers(int injuries_nea_number_officers) {
		this.injuries_nea_number_officers = injuries_nea_number_officers;
	}

	public int getInjuries_nea_number_jcos() {
		return injuries_nea_number_jcos;
	}

	public void setInjuries_nea_number_jcos(int injuries_nea_number_jcos) {
		this.injuries_nea_number_jcos = injuries_nea_number_jcos;
	}

	public int getInjuries_nea_number_ors() {
		return injuries_nea_number_ors;
	}

	public void setInjuries_nea_number_ors(int injuries_nea_number_ors) {
		this.injuries_nea_number_ors = injuries_nea_number_ors;
	}

	public int getInjuries_nea_rate_officers() {
		return injuries_nea_rate_officers;
	}

	public void setInjuries_nea_rate_officers(int injuries_nea_rate_officers) {
		this.injuries_nea_rate_officers = injuries_nea_rate_officers;
	}

	public int getInjuries_nea_rate_jcos() {
		return injuries_nea_rate_jcos;
	}

	public void setInjuries_nea_rate_jcos(int injuries_nea_rate_jcos) {
		this.injuries_nea_rate_jcos = injuries_nea_rate_jcos;
	}

	public int getInjuries_nea_rate_ors() {
		return injuries_nea_rate_ors;
	}

	public void setInjuries_nea_rate_ors(int injuries_nea_rate_ors) {
		this.injuries_nea_rate_ors = injuries_nea_rate_ors;
	}

	public int getEffect_of_heat_number_officers() {
		return effect_of_heat_number_officers;
	}

	public void setEffect_of_heat_number_officers(int effect_of_heat_number_officers) {
		this.effect_of_heat_number_officers = effect_of_heat_number_officers;
	}

	public int getEffect_of_heat_number_jcos() {
		return effect_of_heat_number_jcos;
	}

	public void setEffect_of_heat_number_jcos(int effect_of_heat_number_jcos) {
		this.effect_of_heat_number_jcos = effect_of_heat_number_jcos;
	}

	public int getEffect_of_heat_number_ors() {
		return effect_of_heat_number_ors;
	}

	public void setEffect_of_heat_number_ors(int effect_of_heat_number_ors) {
		this.effect_of_heat_number_ors = effect_of_heat_number_ors;
	}

	public int getEffect_of_heat_rate_officers() {
		return effect_of_heat_rate_officers;
	}

	public void setEffect_of_heat_rate_officers(int effect_of_heat_rate_officers) {
		this.effect_of_heat_rate_officers = effect_of_heat_rate_officers;
	}

	public int getEffect_of_heat_rate_jcos() {
		return effect_of_heat_rate_jcos;
	}

	public void setEffect_of_heat_rate_jcos(int effect_of_heat_rate_jcos) {
		this.effect_of_heat_rate_jcos = effect_of_heat_rate_jcos;
	}

	public int getEffect_of_heat_rate_ors() {
		return effect_of_heat_rate_ors;
	}

	public void setEffect_of_heat_rate_ors(int effect_of_heat_rate_ors) {
		this.effect_of_heat_rate_ors = effect_of_heat_rate_ors;
	}

	public int getEffect_of_cold_number_officers() {
		return effect_of_cold_number_officers;
	}

	public void setEffect_of_cold_number_officers(int effect_of_cold_number_officers) {
		this.effect_of_cold_number_officers = effect_of_cold_number_officers;
	}

	public int getEffect_of_cold_number_jcos() {
		return effect_of_cold_number_jcos;
	}

	public void setEffect_of_cold_number_jcos(int effect_of_cold_number_jcos) {
		this.effect_of_cold_number_jcos = effect_of_cold_number_jcos;
	}

	public int getEffect_of_cold_number_ors() {
		return effect_of_cold_number_ors;
	}

	public void setEffect_of_cold_number_ors(int effect_of_cold_number_ors) {
		this.effect_of_cold_number_ors = effect_of_cold_number_ors;
	}

	public int getEffect_of_cold_rate_officers() {
		return effect_of_cold_rate_officers;
	}

	public void setEffect_of_cold_rate_officers(int effect_of_cold_rate_officers) {
		this.effect_of_cold_rate_officers = effect_of_cold_rate_officers;
	}

	public int getEffect_of_cold_rate_jcos() {
		return effect_of_cold_rate_jcos;
	}

	public void setEffect_of_cold_rate_jcos(int effect_of_cold_rate_jcos) {
		this.effect_of_cold_rate_jcos = effect_of_cold_rate_jcos;
	}

	public int getEffect_of_cold_rate_ors() {
		return effect_of_cold_rate_ors;
	}

	public void setEffect_of_cold_rate_ors(int effect_of_cold_rate_ors) {
		this.effect_of_cold_rate_ors = effect_of_cold_rate_ors;
	}

	public int getEffect_of_high_altitude_number_officers() {
		return effect_of_high_altitude_number_officers;
	}

	public void setEffect_of_high_altitude_number_officers(int effect_of_high_altitude_number_officers) {
		this.effect_of_high_altitude_number_officers = effect_of_high_altitude_number_officers;
	}

	public int getEffect_of_high_altitude_number_jcos() {
		return effect_of_high_altitude_number_jcos;
	}

	public void setEffect_of_high_altitude_number_jcos(int effect_of_high_altitude_number_jcos) {
		this.effect_of_high_altitude_number_jcos = effect_of_high_altitude_number_jcos;
	}

	public int getEffect_of_high_altitude_number_ors() {
		return effect_of_high_altitude_number_ors;
	}

	public void setEffect_of_high_altitude_number_ors(int effect_of_high_altitude_number_ors) {
		this.effect_of_high_altitude_number_ors = effect_of_high_altitude_number_ors;
	}

	public int getEffect_of_high_altitude_rate_officers() {
		return effect_of_high_altitude_rate_officers;
	}

	public void setEffect_of_high_altitude_rate_officers(int effect_of_high_altitude_rate_officers) {
		this.effect_of_high_altitude_rate_officers = effect_of_high_altitude_rate_officers;
	}

	public int getEffect_of_high_altitude_rate_jcos() {
		return effect_of_high_altitude_rate_jcos;
	}

	public void setEffect_of_high_altitude_rate_jcos(int effect_of_high_altitude_rate_jcos) {
		this.effect_of_high_altitude_rate_jcos = effect_of_high_altitude_rate_jcos;
	}

	public int getEffect_of_high_altitude_rate_ors() {
		return effect_of_high_altitude_rate_ors;
	}

	public void setEffect_of_high_altitude_rate_ors(int effect_of_high_altitude_rate_ors) {
		this.effect_of_high_altitude_rate_ors = effect_of_high_altitude_rate_ors;
	}

	public int getPulmonary_tuberculosis_number_officers() {
		return pulmonary_tuberculosis_number_officers;
	}

	public void setPulmonary_tuberculosis_number_officers(int pulmonary_tuberculosis_number_officers) {
		this.pulmonary_tuberculosis_number_officers = pulmonary_tuberculosis_number_officers;
	}

	public int getPulmonary_tuberculosis_number_jcos() {
		return pulmonary_tuberculosis_number_jcos;
	}

	public void setPulmonary_tuberculosis_number_jcos(int pulmonary_tuberculosis_number_jcos) {
		this.pulmonary_tuberculosis_number_jcos = pulmonary_tuberculosis_number_jcos;
	}

	public int getPulmonary_tuberculosis_number_ors() {
		return pulmonary_tuberculosis_number_ors;
	}

	public void setPulmonary_tuberculosis_number_ors(int pulmonary_tuberculosis_number_ors) {
		this.pulmonary_tuberculosis_number_ors = pulmonary_tuberculosis_number_ors;
	}

	public int getPulmonary_tuberculosis_rate_officer() {
		return pulmonary_tuberculosis_rate_officer;
	}

	public void setPulmonary_tuberculosis_rate_officer(int pulmonary_tuberculosis_rate_officer) {
		this.pulmonary_tuberculosis_rate_officer = pulmonary_tuberculosis_rate_officer;
	}

	public int getPulmonary_tuberculosis_rate_jcos() {
		return pulmonary_tuberculosis_rate_jcos;
	}

	public void setPulmonary_tuberculosis_rate_jcos(int pulmonary_tuberculosis_rate_jcos) {
		this.pulmonary_tuberculosis_rate_jcos = pulmonary_tuberculosis_rate_jcos;
	}

	public int getPulmonary_tuberculosis_rate_ors() {
		return pulmonary_tuberculosis_rate_ors;
	}

	public void setPulmonary_tuberculosis_rate_ors(int pulmonary_tuberculosis_rate_ors) {
		this.pulmonary_tuberculosis_rate_ors = pulmonary_tuberculosis_rate_ors;
	}

	public int getNumber_of_dog_bites() {
		return number_of_dog_bites;
	}

	public void setNumber_of_dog_bites(int number_of_dog_bites) {
		this.number_of_dog_bites = number_of_dog_bites;
	}

	public int getNumber_of_rabies_cases() {
		return number_of_rabies_cases;
	}

	public void setNumber_of_rabies_cases(int number_of_rabies_cases) {
		this.number_of_rabies_cases = number_of_rabies_cases;
	}

	public int getNumber_of_snake_bite_cases() {
		return number_of_snake_bite_cases;
	}

	public void setNumber_of_snake_bite_cases(int number_of_snake_bite_cases) {
		this.number_of_snake_bite_cases = number_of_snake_bite_cases;
	}
	
	
	
	
}
