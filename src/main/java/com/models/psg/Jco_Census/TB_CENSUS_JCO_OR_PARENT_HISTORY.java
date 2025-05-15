package com.models.psg.Jco_Census;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
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
@Table(name = "tb_psg_census_jco_or_p_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_JCO_OR_PARENT_HISTORY {
	
	private int id;
	private int jco_his_id;
	private String full_name;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String place_of_birth;
	private int district_of_birth;
	private int state_of_birth;
	private int country_of_birth;
	private int nationality;
	private int religion;
	private int marital_status;
	private int blood_group;
	private int height;
	private int mother_tongue;
	private int gender;
	private int rank;
	private String aadhar_no;
	private String pan_no;

	private String father_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date father_dob;
	private String father_place_birth;
	private int father_profession;
	private String mother_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mother_dob;
	private String mother_place_birth;
	private int mother_profession;
	private String army_no;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_seniority;
	private int trade;
	private String arm_service;
	private String regiment;
	private String unit_sus_no;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_attestation;
	private String category;
	
	
	private int update_jco_status;
	private String unit_posted_to;
	private String created_by;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private int status;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date enroll_dt;
	private int class_enroll;
	private int class_pay;
	private int pay_group;
	private String record_office_sus;
	private String record_office;
	private String father_service;

	private String father_other;

	private String father_personal_no;
	
	private String mother_service;

	private String mother_other;

	private String mother_personal_no;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_tos;
	private int update_census_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJco_his_id() {
		return jco_his_id;
	}
	public void setJco_his_id(int jco_his_id) {
		this.jco_his_id = jco_his_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
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
	public String getPlace_of_birth() {
		return place_of_birth;
	}
	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}
	public int getDistrict_of_birth() {
		return district_of_birth;
	}
	public void setDistrict_of_birth(int district_of_birth) {
		this.district_of_birth = district_of_birth;
	}
	public int getState_of_birth() {
		return state_of_birth;
	}
	public void setState_of_birth(int state_of_birth) {
		this.state_of_birth = state_of_birth;
	}
	public int getCountry_of_birth() {
		return country_of_birth;
	}
	public void setCountry_of_birth(int country_of_birth) {
		this.country_of_birth = country_of_birth;
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
	public int getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(int marital_status) {
		this.marital_status = marital_status;
	}
	public int getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(int blood_group) {
		this.blood_group = blood_group;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getMother_tongue() {
		return mother_tongue;
	}
	public void setMother_tongue(int mother_tongue) {
		this.mother_tongue = mother_tongue;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public Date getFather_dob() {
		return father_dob;
	}
	public void setFather_dob(Date father_dob) {
		this.father_dob = father_dob;
	}
	public String getFather_place_birth() {
		return father_place_birth;
	}
	public void setFather_place_birth(String father_place_birth) {
		this.father_place_birth = father_place_birth;
	}
	public int getFather_profession() {
		return father_profession;
	}
	public void setFather_profession(int father_profession) {
		this.father_profession = father_profession;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public Date getMother_dob() {
		return mother_dob;
	}
	public void setMother_dob(Date mother_dob) {
		this.mother_dob = mother_dob;
	}
	public String getMother_place_birth() {
		return mother_place_birth;
	}
	public void setMother_place_birth(String mother_place_birth) {
		this.mother_place_birth = mother_place_birth;
	}
	public int getMother_profession() {
		return mother_profession;
	}
	public void setMother_profession(int mother_profession) {
		this.mother_profession = mother_profession;
	}
	public String getArmy_no() {
		return army_no;
	}
	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}
	public Date getDate_of_seniority() {
		return date_of_seniority;
	}
	public void setDate_of_seniority(Date date_of_seniority) {
		this.date_of_seniority = date_of_seniority;
	}
	public int getTrade() {
		return trade;
	}
	public void setTrade(int trade) {
		this.trade = trade;
	}
	public String getArm_service() {
		return arm_service;
	}
	public void setArm_service(String arm_service) {
		this.arm_service = arm_service;
	}
	public String getRegiment() {
		return regiment;
	}
	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_attestation() {
		return date_of_attestation;
	}
	public void setDate_of_attestation(Date date_of_attestation) {
		this.date_of_attestation = date_of_attestation;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUpdate_jco_status() {
		return update_jco_status;
	}
	public void setUpdate_jco_status(int update_jco_status) {
		this.update_jco_status = update_jco_status;
	}
	public String getUnit_posted_to() {
		return unit_posted_to;
	}
	public void setUnit_posted_to(String unit_posted_to) {
		this.unit_posted_to = unit_posted_to;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getEnroll_dt() {
		return enroll_dt;
	}
	public void setEnroll_dt(Date enroll_dt) {
		this.enroll_dt = enroll_dt;
	}
	public int getClass_enroll() {
		return class_enroll;
	}
	public void setClass_enroll(int class_enroll) {
		this.class_enroll = class_enroll;
	}
	public int getClass_pay() {
		return class_pay;
	}
	public void setClass_pay(int class_pay) {
		this.class_pay = class_pay;
	}
	public int getPay_group() {
		return pay_group;
	}
	public void setPay_group(int pay_group) {
		this.pay_group = pay_group;
	}
	public String getRecord_office_sus() {
		return record_office_sus;
	}
	public void setRecord_office_sus(String record_office_sus) {
		this.record_office_sus = record_office_sus;
	}
	public String getRecord_office() {
		return record_office;
	}
	public void setRecord_office(String record_office) {
		this.record_office = record_office;
	}
	public String getFather_service() {
		return father_service;
	}
	public void setFather_service(String father_service) {
		this.father_service = father_service;
	}
	public String getFather_other() {
		return father_other;
	}
	public void setFather_other(String father_other) {
		this.father_other = father_other;
	}
	public String getFather_personal_no() {
		return father_personal_no;
	}
	public void setFather_personal_no(String father_personal_no) {
		this.father_personal_no = father_personal_no;
	}
	public String getMother_service() {
		return mother_service;
	}
	public void setMother_service(String mother_service) {
		this.mother_service = mother_service;
	}
	public String getMother_other() {
		return mother_other;
	}
	public void setMother_other(String mother_other) {
		this.mother_other = mother_other;
	}
	public String getMother_personal_no() {
		return mother_personal_no;
	}
	public void setMother_personal_no(String mother_personal_no) {
		this.mother_personal_no = mother_personal_no;
	}
	public Date getDate_of_tos() {
		return date_of_tos;
	}
	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
	}
	public int getUpdate_census_status() {
		return update_census_status;
	}
	public void setUpdate_census_status(int update_census_status) {
		this.update_census_status = update_census_status;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    aadhar_no::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	
	

}
