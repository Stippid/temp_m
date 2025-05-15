package com.models.psg.Civilian;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_civilian_dtl_main", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CIVILIAN_MAIN {
	
	    int id;
		String sus_no;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date dob;
		int gender;
		String classification_services;
		String civ_group;
		int category_belongs;
		String service_status;
		int classification_trade;
		String civ_type;
		String whether_ex_serviceman;
		String whether_person_disability;
		String post_initialy_appointed;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date joining_date_gov_service;
		int designation;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date designation_date;
		int pay_level;
		String father_name;
		String mother_name;
		int state_original;
		int district_original;
		int tehsil_origin;
		int state_present;
		int district_present;
		int tehsil_present;
		int nationality;
		int religion;
		int mother_tongue;
		String aadhar_card;
		int non_effective;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date date_non_effective;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date created_date;
		String created_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date modified_date;
		String modified_by;
		String civilian_status;
		int country_original;
		int country_present;
		int status;
		String employee_no;
		String authority;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date dt_of_authority;
		String pan_no;
		String gender_other;
		String full_name;
		int cadre;
		String original_country_other;
		String original_state_other;
		String original_district_other;
		String original_tehshil_other;
		String present_country_other;
		String present_state_other;
		String present_district_other;
		String present_tehshil_other;
		String classification_trade_other;
		String religion_other;
		String mother_tongue_other;
		String nationality_other;
		String pay_level_other;
		String first_name;
		String middle_name;
		String last_name;
		int cancel_status = -1;
	    String cancel_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date cancel_date;
		String service_other;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		Date date_of_tos;
	
		public int getCancel_status() {
			return cancel_status;
		}
		public void setCancel_status(int cancel_status) {
			this.cancel_status = cancel_status;
		}
		public String getCancel_by() {
			return cancel_by;
		}
		public void setCancel_by(String cancel_by) {
			this.cancel_by = cancel_by;
		}
		public Date getCancel_date() {
			return cancel_date;
		}
		public void setCancel_date(Date cancel_date) {
			this.cancel_date = cancel_date;
		}
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSus_no() {
			return sus_no;
		}
		public void setSus_no(String sus_no) {
			this.sus_no = sus_no;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public int getGender() {
			return gender;
		}
		public void setGender(int gender) {
			this.gender = gender;
		}
		public String getClassification_services() {
			return classification_services;
		}
		public void setClassification_services(String classification_services) {
			this.classification_services = classification_services;
		}
		public String getCiv_group() {
			return civ_group;
		}
		public void setCiv_group(String civ_group) {
			this.civ_group = civ_group;
		}
		public int getCategory_belongs() {
			return category_belongs;
		}
		public void setCategory_belongs(int category_belongs) {
			this.category_belongs = category_belongs;
		}
		public String getService_status() {
			return service_status;
		}
		public void setService_status(String service_status) {
			this.service_status = service_status;
		}
		public int getClassification_trade() {
			return classification_trade;
		}
		public void setClassification_trade(int classification_trade) {
			this.classification_trade = classification_trade;
		}
		public String getCiv_type() {
			return civ_type;
		}
		public void setCiv_type(String civ_type) {
			this.civ_type = civ_type;
		}
		public String getWhether_ex_serviceman() {
			return whether_ex_serviceman;
		}
		public void setWhether_ex_serviceman(String whether_ex_serviceman) {
			this.whether_ex_serviceman = whether_ex_serviceman;
		}
		public String getWhether_person_disability() {
			return whether_person_disability;
		}
		public void setWhether_person_disability(String whether_person_disability) {
			this.whether_person_disability = whether_person_disability;
		}
		public String getPost_initialy_appointed() {
			return post_initialy_appointed;
		}
		public void setPost_initialy_appointed(String post_initialy_appointed) {
			this.post_initialy_appointed = post_initialy_appointed;
		}
		public Date getJoining_date_gov_service() {
			return joining_date_gov_service;
		}
		public void setJoining_date_gov_service(Date joining_date_gov_service) {
			this.joining_date_gov_service = joining_date_gov_service;
		}
		public int getDesignation() {
			return designation;
		}
		public void setDesignation(int designation) {
			this.designation = designation;
		}
		public Date getDesignation_date() {
			return designation_date;
		}
		public void setDesignation_date(Date designation_date) {
			this.designation_date = designation_date;
		}
		public int getPay_level() {
			return pay_level;
		}
		public void setPay_level(int pay_level) {
			this.pay_level = pay_level;
		}
		public String getFather_name() {
			return father_name;
		}
		public void setFather_name(String father_name) {
			this.father_name = father_name;
		}
		public String getMother_name() {
			return mother_name;
		}
		public void setMother_name(String mother_name) {
			this.mother_name = mother_name;
		}
		public int getState_original() {
			return state_original;
		}
		public void setState_original(int state_original) {
			this.state_original = state_original;
		}
		public int getDistrict_original() {
			return district_original;
		}
		public void setDistrict_original(int district_original) {
			this.district_original = district_original;
		}
		public int getTehsil_origin() {
			return tehsil_origin;
		}
		public void setTehsil_origin(int tehsil_origin) {
			this.tehsil_origin = tehsil_origin;
		}
		public int getState_present() {
			return state_present;
		}
		public void setState_present(int state_present) {
			this.state_present = state_present;
		}
		public int getDistrict_present() {
			return district_present;
		}
		public void setDistrict_present(int district_present) {
			this.district_present = district_present;
		}
		public int getTehsil_present() {
			return tehsil_present;
		}
		public void setTehsil_present(int tehsil_present) {
			this.tehsil_present = tehsil_present;
		}
		public int getNationality() {
			return nationality;
		}
		public void setNationality(int nationality) {
			this.nationality = nationality;
		}
		public int getReligion() {
			return religion;
		}
		public void setReligion(int religion) {
			this.religion = religion;
		}
		public int getMother_tongue() {
			return mother_tongue;
		}
		public void setMother_tongue(int mother_tongue) {
			this.mother_tongue = mother_tongue;
		}
		
		@Column
		@ColumnTransformer(
			    read =  "pgp_sym_decrypt(" +
			            "    aadhar_card::bytea, " +
			            "current_setting('miso.version')" +
			            ")",
			    write = "pgp_sym_encrypt( " +
			            "    ?, " +
			            "current_setting('miso.version')" +
			            ") "
			)
		public String getAadhar_card() {
			return aadhar_card;
		}
		public void setAadhar_card(String aadhar_card) {
			this.aadhar_card = aadhar_card;
		}
		public int getNon_effective() {
			return non_effective;
		}
		public void setNon_effective(int non_effective) {
			this.non_effective = non_effective;
		}
		public Date getDate_non_effective() {
			return date_non_effective;
		}
		public void setDate_non_effective(Date date_non_effective) {
			this.date_non_effective = date_non_effective;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
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
		public String getModified_by() {
			return modified_by;
		}
		public void setModified_by(String modified_by) {
			this.modified_by = modified_by;
		}
		public String getCivilian_status() {
			return civilian_status;
		}
		public void setCivilian_status(String civilian_status) {
			this.civilian_status = civilian_status;
		}
		public int getCountry_original() {
			return country_original;
		}
		public void setCountry_original(int country_original) {
			this.country_original = country_original;
		}
		public int getCountry_present() {
			return country_present;
		}
		public void setCountry_present(int country_present) {
			this.country_present = country_present;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getEmployee_no() {
			return employee_no;
		}
		public void setEmployee_no(String employee_no) {
			this.employee_no = employee_no;
		}
		public String getAuthority() {
			return authority;
		}
		public void setAuthority(String authority) {
			this.authority = authority;
		}
		public Date getDt_of_authority() {
			return dt_of_authority;
		}
		public void setDt_of_authority(Date dt_of_authority) {
			this.dt_of_authority = dt_of_authority;
		}
		
		@Column
		@ColumnTransformer(
			    read =  "pgp_sym_decrypt(" +
			            "    pan_no::bytea, " +
			            "current_setting('miso.version')" +
			            ")",
			    write = "pgp_sym_encrypt( " +
			            "    ?, " +
			            "current_setting('miso.version')" +
			            ") "
			)
		public String getPan_no() {
			return pan_no;
		}
		public void setPan_no(String pan_no) {
			this.pan_no = pan_no;
		}
		public String getGender_other() {
			return gender_other;
		}
		public void setGender_other(String gender_other) {
			this.gender_other = gender_other;
		}
		public String getFull_name() {
			return full_name;
		}
		public void setFull_name(String full_name) {
			this.full_name = full_name;
		}
		public String getOriginal_country_other() {
			return original_country_other;
		}
		public void setOriginal_country_other(String original_country_other) {
			this.original_country_other = original_country_other;
		}
		public String getOriginal_state_other() {
			return original_state_other;
		}
		public void setOriginal_state_other(String original_state_other) {
			this.original_state_other = original_state_other;
		}
		public String getOriginal_district_other() {
			return original_district_other;
		}
		public void setOriginal_district_other(String original_district_other) {
			this.original_district_other = original_district_other;
		}
		public String getOriginal_tehshil_other() {
			return original_tehshil_other;
		}
		public void setOriginal_tehshil_other(String original_tehshil_other) {
			this.original_tehshil_other = original_tehshil_other;
		}
		public String getPresent_country_other() {
			return present_country_other;
		}
		public void setPresent_country_other(String present_country_other) {
			this.present_country_other = present_country_other;
		}
		public String getPresent_state_other() {
			return present_state_other;
		}
		public void setPresent_state_other(String present_state_other) {
			this.present_state_other = present_state_other;
		}
		public String getPresent_district_other() {
			return present_district_other;
		}
		public void setPresent_district_other(String present_district_other) {
			this.present_district_other = present_district_other;
		}
		public String getPresent_tehshil_other() {
			return present_tehshil_other;
		}
		public void setPresent_tehshil_other(String present_tehshil_other) {
			this.present_tehshil_other = present_tehshil_other;
		}
		public int getCadre() {
			return cadre;
		}
		public void setCadre(int cadre) {
			this.cadre = cadre;
		}
		public String getClassification_trade_other() {
			return classification_trade_other;
		}
		public void setClassification_trade_other(String classification_trade_other) {
			this.classification_trade_other = classification_trade_other;
		}
		public String getReligion_other() {
			return religion_other;
		}
		public void setReligion_other(String religion_other) {
			this.religion_other = religion_other;
		}
		public String getMother_tongue_other() {
			return mother_tongue_other;
		}
		public void setMother_tongue_other(String mother_tongue_other) {
			this.mother_tongue_other = mother_tongue_other;
		}
		public String getNationality_other() {
			return nationality_other;
		}
		public void setNationality_other(String nationality_other) {
			this.nationality_other = nationality_other;
		}
		public String getPay_level_other() {
			return pay_level_other;
		}
		public void setPay_level_other(String pay_level_other) {
			this.pay_level_other = pay_level_other;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getMiddle_name() {
			return middle_name;
		}
		public void setMiddle_name(String middle_name) {
			this.middle_name = middle_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		
		public String getService_other() {
			return service_other;
		}
		public void setService_other(String service_other) {
			this.service_other = service_other;
		}
		public Date getDate_of_tos() {
			return date_of_tos;
		}
		public void setDate_of_tos(Date date_of_tos) {
			this.date_of_tos = date_of_tos;
		}
		
		
		

}
