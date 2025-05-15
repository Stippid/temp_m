package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_personal_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_PERSONAL_DETAILS {

	String personal_no;
	String cadet_no;
	String course_no;
	String course_comm;
	String pers_name;
	String gender;
	String type_of_commission_granted;
	Date date_of_commission;
	Date date_of_seniority;
	String rank;
	Date date_of_rank;
	Date date_of_birth;
	String unit_sus_no;
	String unit_posted_to;
	Date date_of_tos;
	String parent_arm_service;
	String regiment;
	String authority;
	Date date_of_authority;
	String country_of_birth;
	String state_of_birth;
	String district_of_birth;
	String place_of_birth;
	String nationality;
	String mother_tongue;
	String religion;
	String type_of_entry;
	String aadhar_no;
	String pan_no;
	String Commissioning_institute;
	String traning_institute;
	String comm_bn;
	String comm_coy;
	String trg_bn;
	String trg_coy;
	String maritial_status;
	String blood_group;
	String height;
	String id_card_no;
	Date date_of_issuing;
	String issuing_authority;
	String visible_identification_marks;
	String hair_colour;
	String eye_colour;
	String original_country;
	String original_state;
	String original_district;
	String original_tehsil;
	String presently_domicile_country;
	String presently_domicile_state;
	String presently_domicile_district;
	String presently_domicile_tehsil;
	String permanent_country;
	String permanent_state;
	String permanent_district;
	String permanent_tehsil;
	String permanent_village_town_city;
	String permanent_pin;
	String permanent_nearest_railway_station;
	String permanent_rural_urban_semi_urban;
	String permanent_border_area;
	String present_country;
	String present_state;
	String present_district;
	String present_tehsil;
	String present_village_town_city;
	String present_pin;
	String present_nearest_railway_station;
	String present_rural_urban_semi_urban;
	String nok_name;
	String nok_relation;
	String nok_country;
	String nok_state;
	String nok_district;
	String nok_tehsil;
	String nok_village_town_city;
	String nok_pin;
	String nok_nearest_railway_station;
	String nok_rural_urban_semi_urban;
	String nok_mobile_no;
	String father_name;
	Date date_of_birth_father;
	String father_aadhar_no;
	String father_pan_no;
	String father_place_of_birth;
	String father_service;
	String father_personal_no;
	String mother_name;
	Date date_of_mother;
	String mother_aadhar_no;
	String mother_pan_no;
	String mother_place_of_birth;
	String mother_service;
	String mother_personal_no;
	String pre_cadet_status;
	String pre_cadet_designation;
	String pre_cadet_name_of_employer;
	String pre_cadet_competency;
	String pre_cadet_gazetted_non_gazetted;
	String pre_cadet_pensionable_civil_service;
	String pre_cadet_service_no;
	String pre_cadet_unit_regiment;
	Date date_pre_cadet_from;
	Date date_pre_cadet_to;
	String pre_cadet_total_service_in_rank;
	String pre_cadet_ncc_experience;
	String rank_type;
	String appointment;
	Date date_of_appointment;
	String gmail;
	String nic_mail;
	String mobile_no;
	Date date_of_casulaty;
	String time_of_casuality;
	String onduty;
	String batnpc_country;
	String batnpc_state;
	String batnpc_district;
	String batnpc_tehsil;
	String batnpc_village_town_city;
	String batnpc_pin;
	String batnpc_exact_place_area_post;
	String batnpc_name_of_operation;
	String batnpc_sector;
	String batnpc_filed_area;
	String batnpc_whether_on;
	String batnpc_bde;
	String batnpc_div;
	String batnpc_corp;
	String batnpc_command;
	String batnpc_hospital_name;
	String batnpc_hospital_location;
	String batnpc_cause_of_casualty;
	String batnpc_circumstances;
	String batnpc_diagnosis;
	String batnpc_aid_to_civ;
	String batnpc_nok_informed;
	Date date_of_informing_batnpc;
	String batnpc_time_of_informing;
	String batnpc_method_of_informing;
	String batnpc_category_of_casualty;
	String army_act_sec;
	String sub_clause;
	String trialed_by;
	String punishment_awarded;
	String discipline_type_of_entry;
	Date date_of_discipline_award;
	String discipline_unit_name;
	Date date_from_which_change_in_seniority_is_effective;
	Date date_from_extension;
	Date date_to_extension;
	String seconded_to;
	Date date_secondment_with_effect_from;
	String cause_of_non_effective;
	Date date_of_non_effective;
	String non_eff_country;
	String non_eff_state;
	String non_eff_district;
	String non_eff_tehsil;
	String non_eff_village_town_city;
	String non_eff_pin;
	String non_eff_nearest_railway_station;
	String non_eff_rural_urban_semi_urban;
	String non_eff_border_area;
	String deserter_arms_type;
	String deserter_weapon;
	Date date_of_desertion;
	String cause_of_desertion;

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

	public String getCadet_no() {
		return cadet_no;
	}

	public void setCadet_no(String cadet_no) {
		this.cadet_no = cadet_no;
	}

	public String getCourse_no() {
		return course_no;
	}

	public void setCourse_no(String course_no) {
		this.course_no = course_no;
	}

	public String getCourse_comm() {
		return course_comm;
	}

	public void setCourse_comm(String course_comm) {
		this.course_comm = course_comm;
	}

	public String getPers_name() {
		return pers_name;
	}

	public void setPers_name(String pers_name) {
		this.pers_name = pers_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType_of_commission_granted() {
		return type_of_commission_granted;
	}

	public void setType_of_commission_granted(String type_of_commission_granted) {
		this.type_of_commission_granted = type_of_commission_granted;
	}

	public Date getDate_of_commission() {
		return date_of_commission;
	}

	public void setDate_of_commission(Date date_of_commission) {
		this.date_of_commission = date_of_commission;
	}

	public Date getDate_of_seniority() {
		return date_of_seniority;
	}

	public void setDate_of_seniority(Date date_of_seniority) {
		this.date_of_seniority = date_of_seniority;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Date getDate_of_rank() {
		return date_of_rank;
	}

	public void setDate_of_rank(Date date_of_rank) {
		this.date_of_rank = date_of_rank;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getUnit_sus_no() {
		return unit_sus_no;
	}

	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}

	public String getUnit_posted_to() {
		return unit_posted_to;
	}

	public void setUnit_posted_to(String unit_posted_to) {
		this.unit_posted_to = unit_posted_to;
	}

	public Date getDate_of_tos() {
		return date_of_tos;
	}

	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
	}

	public String getParent_arm_service() {
		return parent_arm_service;
	}

	public void setParent_arm_service(String parent_arm_service) {
		this.parent_arm_service = parent_arm_service;
	}

	public String getRegiment() {
		return regiment;
	}

	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getDate_of_authority() {
		return date_of_authority;
	}

	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}

	public String getCountry_of_birth() {
		return country_of_birth;
	}

	public void setCountry_of_birth(String country_of_birth) {
		this.country_of_birth = country_of_birth;
	}

	public String getState_of_birth() {
		return state_of_birth;
	}

	public void setState_of_birth(String state_of_birth) {
		this.state_of_birth = state_of_birth;
	}

	public String getDistrict_of_birth() {
		return district_of_birth;
	}

	public void setDistrict_of_birth(String district_of_birth) {
		this.district_of_birth = district_of_birth;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMother_tongue() {
		return mother_tongue;
	}

	public void setMother_tongue(String mother_tongue) {
		this.mother_tongue = mother_tongue;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getType_of_entry() {
		return type_of_entry;
	}

	public void setType_of_entry(String type_of_entry) {
		this.type_of_entry = type_of_entry;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getCommissioning_institute() {
		return Commissioning_institute;
	}

	public void setCommissioning_institute(String commissioning_institute) {
		Commissioning_institute = commissioning_institute;
	}

	public String getTraning_institute() {
		return traning_institute;
	}

	public void setTraning_institute(String traning_institute) {
		this.traning_institute = traning_institute;
	}

	public String getComm_bn() {
		return comm_bn;
	}

	public void setComm_bn(String comm_bn) {
		this.comm_bn = comm_bn;
	}

	public String getComm_coy() {
		return comm_coy;
	}

	public void setComm_coy(String comm_coy) {
		this.comm_coy = comm_coy;
	}

	public String getTrg_bn() {
		return trg_bn;
	}

	public void setTrg_bn(String trg_bn) {
		this.trg_bn = trg_bn;
	}

	public String getTrg_coy() {
		return trg_coy;
	}

	public void setTrg_coy(String trg_coy) {
		this.trg_coy = trg_coy;
	}

	public String getMaritial_status() {
		return maritial_status;
	}

	public void setMaritial_status(String maritial_status) {
		this.maritial_status = maritial_status;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getId_card_no() {
		return id_card_no;
	}

	public void setId_card_no(String id_card_no) {
		this.id_card_no = id_card_no;
	}

	public Date getDate_of_issuing() {
		return date_of_issuing;
	}

	public void setDate_of_issuing(Date date_of_issuing) {
		this.date_of_issuing = date_of_issuing;
	}

	public String getIssuing_authority() {
		return issuing_authority;
	}

	public void setIssuing_authority(String issuing_authority) {
		this.issuing_authority = issuing_authority;
	}

	public String getVisible_identification_marks() {
		return visible_identification_marks;
	}

	public void setVisible_identification_marks(String visible_identification_marks) {
		this.visible_identification_marks = visible_identification_marks;
	}

	public String getHair_colour() {
		return hair_colour;
	}

	public void setHair_colour(String hair_colour) {
		this.hair_colour = hair_colour;
	}

	public String getEye_colour() {
		return eye_colour;
	}

	public void setEye_colour(String eye_colour) {
		this.eye_colour = eye_colour;
	}

	public String getOriginal_country() {
		return original_country;
	}

	public void setOriginal_country(String original_country) {
		this.original_country = original_country;
	}

	public String getOriginal_state() {
		return original_state;
	}

	public void setOriginal_state(String original_state) {
		this.original_state = original_state;
	}

	public String getOriginal_district() {
		return original_district;
	}

	public void setOriginal_district(String original_district) {
		this.original_district = original_district;
	}

	public String getOriginal_tehsil() {
		return original_tehsil;
	}

	public void setOriginal_tehsil(String original_tehsil) {
		this.original_tehsil = original_tehsil;
	}

	public String getPresently_domicile_country() {
		return presently_domicile_country;
	}

	public void setPresently_domicile_country(String presently_domicile_country) {
		this.presently_domicile_country = presently_domicile_country;
	}

	public String getPresently_domicile_state() {
		return presently_domicile_state;
	}

	public void setPresently_domicile_state(String presently_domicile_state) {
		this.presently_domicile_state = presently_domicile_state;
	}

	public String getPresently_domicile_district() {
		return presently_domicile_district;
	}

	public void setPresently_domicile_district(String presently_domicile_district) {
		this.presently_domicile_district = presently_domicile_district;
	}

	public String getPresently_domicile_tehsil() {
		return presently_domicile_tehsil;
	}

	public void setPresently_domicile_tehsil(String presently_domicile_tehsil) {
		this.presently_domicile_tehsil = presently_domicile_tehsil;
	}

	public String getPermanent_country() {
		return permanent_country;
	}

	public void setPermanent_country(String permanent_country) {
		this.permanent_country = permanent_country;
	}

	public String getPermanent_state() {
		return permanent_state;
	}

	public void setPermanent_state(String permanent_state) {
		this.permanent_state = permanent_state;
	}

	public String getPermanent_district() {
		return permanent_district;
	}

	public void setPermanent_district(String permanent_district) {
		this.permanent_district = permanent_district;
	}

	public String getPermanent_tehsil() {
		return permanent_tehsil;
	}

	public void setPermanent_tehsil(String permanent_tehsil) {
		this.permanent_tehsil = permanent_tehsil;
	}

	public String getPermanent_village_town_city() {
		return permanent_village_town_city;
	}

	public void setPermanent_village_town_city(String permanent_village_town_city) {
		this.permanent_village_town_city = permanent_village_town_city;
	}

	public String getPermanent_pin() {
		return permanent_pin;
	}

	public void setPermanent_pin(String permanent_pin) {
		this.permanent_pin = permanent_pin;
	}

	public String getPermanent_nearest_railway_station() {
		return permanent_nearest_railway_station;
	}

	public void setPermanent_nearest_railway_station(String permanent_nearest_railway_station) {
		this.permanent_nearest_railway_station = permanent_nearest_railway_station;
	}

	public String getPermanent_rural_urban_semi_urban() {
		return permanent_rural_urban_semi_urban;
	}

	public void setPermanent_rural_urban_semi_urban(String permanent_rural_urban_semi_urban) {
		this.permanent_rural_urban_semi_urban = permanent_rural_urban_semi_urban;
	}

	public String getPermanent_border_area() {
		return permanent_border_area;
	}

	public void setPermanent_border_area(String permanent_border_area) {
		this.permanent_border_area = permanent_border_area;
	}

	public String getPresent_country() {
		return present_country;
	}

	public void setPresent_country(String present_country) {
		this.present_country = present_country;
	}

	public String getPresent_state() {
		return present_state;
	}

	public void setPresent_state(String present_state) {
		this.present_state = present_state;
	}

	public String getPresent_district() {
		return present_district;
	}

	public void setPresent_district(String present_district) {
		this.present_district = present_district;
	}

	public String getPresent_tehsil() {
		return present_tehsil;
	}

	public void setPresent_tehsil(String present_tehsil) {
		this.present_tehsil = present_tehsil;
	}

	public String getPresent_village_town_city() {
		return present_village_town_city;
	}

	public void setPresent_village_town_city(String present_village_town_city) {
		this.present_village_town_city = present_village_town_city;
	}

	public String getPresent_pin() {
		return present_pin;
	}

	public void setPresent_pin(String present_pin) {
		this.present_pin = present_pin;
	}

	public String getPresent_nearest_railway_station() {
		return present_nearest_railway_station;
	}

	public void setPresent_nearest_railway_station(String present_nearest_railway_station) {
		this.present_nearest_railway_station = present_nearest_railway_station;
	}

	public String getPresent_rural_urban_semi_urban() {
		return present_rural_urban_semi_urban;
	}

	public void setPresent_rural_urban_semi_urban(String present_rural_urban_semi_urban) {
		this.present_rural_urban_semi_urban = present_rural_urban_semi_urban;
	}

	public String getNok_name() {
		return nok_name;
	}

	public void setNok_name(String nok_name) {
		this.nok_name = nok_name;
	}

	public String getNok_relation() {
		return nok_relation;
	}

	public void setNok_relation(String nok_relation) {
		this.nok_relation = nok_relation;
	}

	public String getNok_country() {
		return nok_country;
	}

	public void setNok_country(String nok_country) {
		this.nok_country = nok_country;
	}

	public String getNok_state() {
		return nok_state;
	}

	public void setNok_state(String nok_state) {
		this.nok_state = nok_state;
	}

	public String getNok_district() {
		return nok_district;
	}

	public void setNok_district(String nok_district) {
		this.nok_district = nok_district;
	}

	public String getNok_tehsil() {
		return nok_tehsil;
	}

	public void setNok_tehsil(String nok_tehsil) {
		this.nok_tehsil = nok_tehsil;
	}

	public String getNok_village_town_city() {
		return nok_village_town_city;
	}

	public void setNok_village_town_city(String nok_village_town_city) {
		this.nok_village_town_city = nok_village_town_city;
	}

	public String getNok_pin() {
		return nok_pin;
	}

	public void setNok_pin(String nok_pin) {
		this.nok_pin = nok_pin;
	}

	public String getNok_nearest_railway_station() {
		return nok_nearest_railway_station;
	}

	public void setNok_nearest_railway_station(String nok_nearest_railway_station) {
		this.nok_nearest_railway_station = nok_nearest_railway_station;
	}

	public String getNok_rural_urban_semi_urban() {
		return nok_rural_urban_semi_urban;
	}

	public void setNok_rural_urban_semi_urban(String nok_rural_urban_semi_urban) {
		this.nok_rural_urban_semi_urban = nok_rural_urban_semi_urban;
	}

	public String getNok_mobile_no() {
		return nok_mobile_no;
	}

	public void setNok_mobile_no(String nok_mobile_no) {
		this.nok_mobile_no = nok_mobile_no;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public Date getDate_of_birth_father() {
		return date_of_birth_father;
	}

	public void setDate_of_birth_father(Date date_of_birth_father) {
		this.date_of_birth_father = date_of_birth_father;
	}

	public String getFather_aadhar_no() {
		return father_aadhar_no;
	}

	public void setFather_aadhar_no(String father_aadhar_no) {
		this.father_aadhar_no = father_aadhar_no;
	}

	public String getFather_pan_no() {
		return father_pan_no;
	}

	public void setFather_pan_no(String father_pan_no) {
		this.father_pan_no = father_pan_no;
	}

	public String getFather_place_of_birth() {
		return father_place_of_birth;
	}

	public void setFather_place_of_birth(String father_place_of_birth) {
		this.father_place_of_birth = father_place_of_birth;
	}

	public String getFather_service() {
		return father_service;
	}

	public void setFather_service(String father_service) {
		this.father_service = father_service;
	}

	public String getFather_personal_no() {
		return father_personal_no;
	}

	public void setFather_personal_no(String father_personal_no) {
		this.father_personal_no = father_personal_no;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public Date getDate_of_mother() {
		return date_of_mother;
	}

	public void setDate_of_mother(Date date_of_mother) {
		this.date_of_mother = date_of_mother;
	}

	public String getMother_aadhar_no() {
		return mother_aadhar_no;
	}

	public void setMother_aadhar_no(String mother_aadhar_no) {
		this.mother_aadhar_no = mother_aadhar_no;
	}

	public String getMother_pan_no() {
		return mother_pan_no;
	}

	public void setMother_pan_no(String mother_pan_no) {
		this.mother_pan_no = mother_pan_no;
	}

	public String getMother_place_of_birth() {
		return mother_place_of_birth;
	}

	public void setMother_place_of_birth(String mother_place_of_birth) {
		this.mother_place_of_birth = mother_place_of_birth;
	}

	public String getMother_service() {
		return mother_service;
	}

	public void setMother_service(String mother_service) {
		this.mother_service = mother_service;
	}

	public String getMother_personal_no() {
		return mother_personal_no;
	}

	public void setMother_personal_no(String mother_personal_no) {
		this.mother_personal_no = mother_personal_no;
	}

	public String getPre_cadet_status() {
		return pre_cadet_status;
	}

	public void setPre_cadet_status(String pre_cadet_status) {
		this.pre_cadet_status = pre_cadet_status;
	}

	public String getPre_cadet_designation() {
		return pre_cadet_designation;
	}

	public void setPre_cadet_designation(String pre_cadet_designation) {
		this.pre_cadet_designation = pre_cadet_designation;
	}

	public String getPre_cadet_name_of_employer() {
		return pre_cadet_name_of_employer;
	}

	public void setPre_cadet_name_of_employer(String pre_cadet_name_of_employer) {
		this.pre_cadet_name_of_employer = pre_cadet_name_of_employer;
	}

	public String getPre_cadet_competency() {
		return pre_cadet_competency;
	}

	public void setPre_cadet_competency(String pre_cadet_competency) {
		this.pre_cadet_competency = pre_cadet_competency;
	}

	public String getPre_cadet_gazetted_non_gazetted() {
		return pre_cadet_gazetted_non_gazetted;
	}

	public void setPre_cadet_gazetted_non_gazetted(String pre_cadet_gazetted_non_gazetted) {
		this.pre_cadet_gazetted_non_gazetted = pre_cadet_gazetted_non_gazetted;
	}

	public String getPre_cadet_pensionable_civil_service() {
		return pre_cadet_pensionable_civil_service;
	}

	public void setPre_cadet_pensionable_civil_service(String pre_cadet_pensionable_civil_service) {
		this.pre_cadet_pensionable_civil_service = pre_cadet_pensionable_civil_service;
	}

	public String getPre_cadet_service_no() {
		return pre_cadet_service_no;
	}

	public void setPre_cadet_service_no(String pre_cadet_service_no) {
		this.pre_cadet_service_no = pre_cadet_service_no;
	}

	public String getPre_cadet_unit_regiment() {
		return pre_cadet_unit_regiment;
	}

	public void setPre_cadet_unit_regiment(String pre_cadet_unit_regiment) {
		this.pre_cadet_unit_regiment = pre_cadet_unit_regiment;
	}

	public Date getDate_pre_cadet_from() {
		return date_pre_cadet_from;
	}

	public void setDate_pre_cadet_from(Date date_pre_cadet_from) {
		this.date_pre_cadet_from = date_pre_cadet_from;
	}

	public Date getDate_pre_cadet_to() {
		return date_pre_cadet_to;
	}

	public void setDate_pre_cadet_to(Date date_pre_cadet_to) {
		this.date_pre_cadet_to = date_pre_cadet_to;
	}

	public String getPre_cadet_total_service_in_rank() {
		return pre_cadet_total_service_in_rank;
	}

	public void setPre_cadet_total_service_in_rank(String pre_cadet_total_service_in_rank) {
		this.pre_cadet_total_service_in_rank = pre_cadet_total_service_in_rank;
	}

	public String getPre_cadet_ncc_experience() {
		return pre_cadet_ncc_experience;
	}

	public void setPre_cadet_ncc_experience(String pre_cadet_ncc_experience) {
		this.pre_cadet_ncc_experience = pre_cadet_ncc_experience;
	}

	public String getRank_type() {
		return rank_type;
	}

	public void setRank_type(String rank_type) {
		this.rank_type = rank_type;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public Date getDate_of_appointment() {
		return date_of_appointment;
	}

	public void setDate_of_appointment(Date date_of_appointment) {
		this.date_of_appointment = date_of_appointment;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getNic_mail() {
		return nic_mail;
	}

	public void setNic_mail(String nic_mail) {
		this.nic_mail = nic_mail;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Date getDate_of_casulaty() {
		return date_of_casulaty;
	}

	public void setDate_of_casulaty(Date date_of_casulaty) {
		this.date_of_casulaty = date_of_casulaty;
	}

	public String getTime_of_casuality() {
		return time_of_casuality;
	}

	public void setTime_of_casuality(String time_of_casuality) {
		this.time_of_casuality = time_of_casuality;
	}

	public String getOnduty() {
		return onduty;
	}

	public void setOnduty(String onduty) {
		this.onduty = onduty;
	}

	public String getBatnpc_country() {
		return batnpc_country;
	}

	public void setBatnpc_country(String batnpc_country) {
		this.batnpc_country = batnpc_country;
	}

	public String getBatnpc_state() {
		return batnpc_state;
	}

	public void setBatnpc_state(String batnpc_state) {
		this.batnpc_state = batnpc_state;
	}

	public String getBatnpc_district() {
		return batnpc_district;
	}

	public void setBatnpc_district(String batnpc_district) {
		this.batnpc_district = batnpc_district;
	}

	public String getBatnpc_tehsil() {
		return batnpc_tehsil;
	}

	public void setBatnpc_tehsil(String batnpc_tehsil) {
		this.batnpc_tehsil = batnpc_tehsil;
	}

	public String getBatnpc_village_town_city() {
		return batnpc_village_town_city;
	}

	public void setBatnpc_village_town_city(String batnpc_village_town_city) {
		this.batnpc_village_town_city = batnpc_village_town_city;
	}

	public String getBatnpc_pin() {
		return batnpc_pin;
	}

	public void setBatnpc_pin(String batnpc_pin) {
		this.batnpc_pin = batnpc_pin;
	}

	public String getBatnpc_exact_place_area_post() {
		return batnpc_exact_place_area_post;
	}

	public void setBatnpc_exact_place_area_post(String batnpc_exact_place_area_post) {
		this.batnpc_exact_place_area_post = batnpc_exact_place_area_post;
	}

	public String getBatnpc_name_of_operation() {
		return batnpc_name_of_operation;
	}

	public void setBatnpc_name_of_operation(String batnpc_name_of_operation) {
		this.batnpc_name_of_operation = batnpc_name_of_operation;
	}

	public String getBatnpc_sector() {
		return batnpc_sector;
	}

	public void setBatnpc_sector(String batnpc_sector) {
		this.batnpc_sector = batnpc_sector;
	}

	public String getBatnpc_filed_area() {
		return batnpc_filed_area;
	}

	public void setBatnpc_filed_area(String batnpc_filed_area) {
		this.batnpc_filed_area = batnpc_filed_area;
	}

	public String getBatnpc_whether_on() {
		return batnpc_whether_on;
	}

	public void setBatnpc_whether_on(String batnpc_whether_on) {
		this.batnpc_whether_on = batnpc_whether_on;
	}

	public String getBatnpc_bde() {
		return batnpc_bde;
	}

	public void setBatnpc_bde(String batnpc_bde) {
		this.batnpc_bde = batnpc_bde;
	}

	public String getBatnpc_div() {
		return batnpc_div;
	}

	public void setBatnpc_div(String batnpc_div) {
		this.batnpc_div = batnpc_div;
	}

	public String getBatnpc_corp() {
		return batnpc_corp;
	}

	public void setBatnpc_corp(String batnpc_corp) {
		this.batnpc_corp = batnpc_corp;
	}

	public String getBatnpc_command() {
		return batnpc_command;
	}

	public void setBatnpc_command(String batnpc_command) {
		this.batnpc_command = batnpc_command;
	}

	public String getBatnpc_hospital_name() {
		return batnpc_hospital_name;
	}

	public void setBatnpc_hospital_name(String batnpc_hospital_name) {
		this.batnpc_hospital_name = batnpc_hospital_name;
	}

	public String getBatnpc_hospital_location() {
		return batnpc_hospital_location;
	}

	public void setBatnpc_hospital_location(String batnpc_hospital_location) {
		this.batnpc_hospital_location = batnpc_hospital_location;
	}

	public String getBatnpc_cause_of_casualty() {
		return batnpc_cause_of_casualty;
	}

	public void setBatnpc_cause_of_casualty(String batnpc_cause_of_casualty) {
		this.batnpc_cause_of_casualty = batnpc_cause_of_casualty;
	}

	public String getBatnpc_circumstances() {
		return batnpc_circumstances;
	}

	public void setBatnpc_circumstances(String batnpc_circumstances) {
		this.batnpc_circumstances = batnpc_circumstances;
	}

	public String getBatnpc_diagnosis() {
		return batnpc_diagnosis;
	}

	public void setBatnpc_diagnosis(String batnpc_diagnosis) {
		this.batnpc_diagnosis = batnpc_diagnosis;
	}

	public String getBatnpc_aid_to_civ() {
		return batnpc_aid_to_civ;
	}

	public void setBatnpc_aid_to_civ(String batnpc_aid_to_civ) {
		this.batnpc_aid_to_civ = batnpc_aid_to_civ;
	}

	public String getBatnpc_nok_informed() {
		return batnpc_nok_informed;
	}

	public void setBatnpc_nok_informed(String batnpc_nok_informed) {
		this.batnpc_nok_informed = batnpc_nok_informed;
	}

	public Date getDate_of_informing_batnpc() {
		return date_of_informing_batnpc;
	}

	public void setDate_of_informing_batnpc(Date date_of_informing_batnpc) {
		this.date_of_informing_batnpc = date_of_informing_batnpc;
	}

	public String getBatnpc_time_of_informing() {
		return batnpc_time_of_informing;
	}

	public void setBatnpc_time_of_informing(String batnpc_time_of_informing) {
		this.batnpc_time_of_informing = batnpc_time_of_informing;
	}

	public String getBatnpc_method_of_informing() {
		return batnpc_method_of_informing;
	}

	public void setBatnpc_method_of_informing(String batnpc_method_of_informing) {
		this.batnpc_method_of_informing = batnpc_method_of_informing;
	}

	public String getBatnpc_category_of_casualty() {
		return batnpc_category_of_casualty;
	}

	public void setBatnpc_category_of_casualty(String batnpc_category_of_casualty) {
		this.batnpc_category_of_casualty = batnpc_category_of_casualty;
	}

	public String getArmy_act_sec() {
		return army_act_sec;
	}

	public void setArmy_act_sec(String army_act_sec) {
		this.army_act_sec = army_act_sec;
	}

	public String getSub_clause() {
		return sub_clause;
	}

	public void setSub_clause(String sub_clause) {
		this.sub_clause = sub_clause;
	}

	public String getTrialed_by() {
		return trialed_by;
	}

	public void setTrialed_by(String trialed_by) {
		this.trialed_by = trialed_by;
	}

	public String getPunishment_awarded() {
		return punishment_awarded;
	}

	public void setPunishment_awarded(String punishment_awarded) {
		this.punishment_awarded = punishment_awarded;
	}

	public String getDiscipline_type_of_entry() {
		return discipline_type_of_entry;
	}

	public void setDiscipline_type_of_entry(String discipline_type_of_entry) {
		this.discipline_type_of_entry = discipline_type_of_entry;
	}

	public Date getDate_of_discipline_award() {
		return date_of_discipline_award;
	}

	public void setDate_of_discipline_award(Date date_of_discipline_award) {
		this.date_of_discipline_award = date_of_discipline_award;
	}

	public String getDiscipline_unit_name() {
		return discipline_unit_name;
	}

	public void setDiscipline_unit_name(String discipline_unit_name) {
		this.discipline_unit_name = discipline_unit_name;
	}

	public Date getDate_from_which_change_in_seniority_is_effective() {
		return date_from_which_change_in_seniority_is_effective;
	}

	public void setDate_from_which_change_in_seniority_is_effective(
			Date date_from_which_change_in_seniority_is_effective) {
		this.date_from_which_change_in_seniority_is_effective = date_from_which_change_in_seniority_is_effective;
	}

	public Date getDate_from_extension() {
		return date_from_extension;
	}

	public void setDate_from_extension(Date date_from_extension) {
		this.date_from_extension = date_from_extension;
	}

	public Date getDate_to_extension() {
		return date_to_extension;
	}

	public void setDate_to_extension(Date date_to_extension) {
		this.date_to_extension = date_to_extension;
	}

	public String getSeconded_to() {
		return seconded_to;
	}

	public void setSeconded_to(String seconded_to) {
		this.seconded_to = seconded_to;
	}

	public Date getDate_secondment_with_effect_from() {
		return date_secondment_with_effect_from;
	}

	public void setDate_secondment_with_effect_from(Date date_secondment_with_effect_from) {
		this.date_secondment_with_effect_from = date_secondment_with_effect_from;
	}

	public String getCause_of_non_effective() {
		return cause_of_non_effective;
	}

	public void setCause_of_non_effective(String cause_of_non_effective) {
		this.cause_of_non_effective = cause_of_non_effective;
	}

	public Date getDate_of_non_effective() {
		return date_of_non_effective;
	}

	public void setDate_of_non_effective(Date date_of_non_effective) {
		this.date_of_non_effective = date_of_non_effective;
	}

	public String getNon_eff_country() {
		return non_eff_country;
	}

	public void setNon_eff_country(String non_eff_country) {
		this.non_eff_country = non_eff_country;
	}

	public String getNon_eff_state() {
		return non_eff_state;
	}

	public void setNon_eff_state(String non_eff_state) {
		this.non_eff_state = non_eff_state;
	}

	public String getNon_eff_district() {
		return non_eff_district;
	}

	public void setNon_eff_district(String non_eff_district) {
		this.non_eff_district = non_eff_district;
	}

	public String getNon_eff_tehsil() {
		return non_eff_tehsil;
	}

	public void setNon_eff_tehsil(String non_eff_tehsil) {
		this.non_eff_tehsil = non_eff_tehsil;
	}

	public String getNon_eff_village_town_city() {
		return non_eff_village_town_city;
	}

	public void setNon_eff_village_town_city(String non_eff_village_town_city) {
		this.non_eff_village_town_city = non_eff_village_town_city;
	}

	public String getNon_eff_pin() {
		return non_eff_pin;
	}

	public void setNon_eff_pin(String non_eff_pin) {
		this.non_eff_pin = non_eff_pin;
	}

	public String getNon_eff_nearest_railway_station() {
		return non_eff_nearest_railway_station;
	}

	public void setNon_eff_nearest_railway_station(String non_eff_nearest_railway_station) {
		this.non_eff_nearest_railway_station = non_eff_nearest_railway_station;
	}

	public String getNon_eff_rural_urban_semi_urban() {
		return non_eff_rural_urban_semi_urban;
	}

	public void setNon_eff_rural_urban_semi_urban(String non_eff_rural_urban_semi_urban) {
		this.non_eff_rural_urban_semi_urban = non_eff_rural_urban_semi_urban;
	}

	public String getNon_eff_border_area() {
		return non_eff_border_area;
	}

	public void setNon_eff_border_area(String non_eff_border_area) {
		this.non_eff_border_area = non_eff_border_area;
	}

	public String getDeserter_arms_type() {
		return deserter_arms_type;
	}

	public void setDeserter_arms_type(String deserter_arms_type) {
		this.deserter_arms_type = deserter_arms_type;
	}

	public String getDeserter_weapon() {
		return deserter_weapon;
	}

	public void setDeserter_weapon(String deserter_weapon) {
		this.deserter_weapon = deserter_weapon;
	}

	public Date getDate_of_desertion() {
		return date_of_desertion;
	}

	public void setDate_of_desertion(Date date_of_desertion) {
		this.date_of_desertion = date_of_desertion;
	}

	public String getCause_of_desertion() {
		return cause_of_desertion;
	}

	public void setCause_of_desertion(String cause_of_desertion) {
		this.cause_of_desertion = cause_of_desertion;
	}

}
