package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_patient_details", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Scrutiny_Search_Model {
	private int id;
	
	private String medical_unit;
	private String and_no;
	private String diet;
	private Date admsn_date;
	private Date dschrg_date;
	
	  private String ward;
	  private String name;
	  private int age_year;
	  private int age_month;
	  private int age_days;
	  private String sex;
	  private String marital_status;
	  private String relationship;
	  private String nbb;
	  private Double nbb_weight;
	  private String persnl_no;
	  private String category;
	  private String rank;
	  private String persnl_name;
	  private int persnl_age_year;
	  private int persnl_age_month;
	  private String persnl_sex;
	  private int service_years;
	  private int service_months;
	  private String persnl_unit;
	  private String persnl_unit_desc;
	  private String station;
	  private String formation;
	  private String arm_corps;
	  private String trade;
	  private String religion;
	  private String class1;
	  private String persnl_marital_status;
	  private String dist_origin;
	  private String state_origin;
	  private String records_office;
	  private String arrival_time;
	  private String admsn_type;
	  private String trnsfrd_from;
	  private String trnsfrd_from_desc;
	  private String trnsfrd_and_no;
	  private String on_list;
	  private String mlc;
	  private String mlc_date;
	  private String injry_rpt;
	  private Date injry_rpt_date;
	  private String med_ctgry_s;
	  private String med_ctgry_h;
	  private String med_ctgry_a;
	  private String med_ctgry_p;
	  private String med_ctgry_e;
	  private String disposal;
	  private String trnsfrd_to;
	  private String trnsfrd_to_desc;
	  private String rejection_reason;
	  private String patient_status;
	  private String admsn_status;
	  private String dschrg_status;
	  private Date created_on;
	  private String created_by;
	  private Date modified_on;	  
	  private String modified_by;
	  private Date apprvd_at_unit_on; 
	  private String apprvr_at_unit_by;
	  private Date apprvd_at_miso_on;
	  private String apprvr_at_miso_by;
	  private Date time_stamp;
	  private String nok_name;
	  private String nok_relation;
	  private String nok_address;
	  private String cda_no;
	  private String type_injury;
	  private String details_sickleave;
	  private String injury_report_a;
	  private String discharge_ward;
	  private String transport_mode;
	  private String other_details;
	  private String ward_no_a;
	  private String ward_no_d;
	  private String not_patient;
	  private String dependent_details;
	  private String discharge_remarks;
	  private String ab64;
	  private String icd_remarks_a;
	  private String icd_remarks_d;
	  private String id_card;
	  private String diagnosis_code1a;
	  private String admsn_dschrg_flag_a;
	  private String icd_cause_code1a;
	  private int priority_index1a;
	  private String diagnosis_code2a;
	  private String icd_cause_code2a;
	  private int priority_index2a;
	  private String diagnosis_code3a;
	  private String icd_cause_code3a;
	  private int priority_index3a;
	  private String diagnosis_code4a;
	  private String icd_cause_code4a;
	  private int priority_index4a;
	  private String diagnosis_code5a;
	  private String icd_cause_code5a;
	  private int priority_index5a;
	  private String diagnosis_code6a;
	  private String icd_cause_code6a;
	  private int priority_index6a;
	  private String diagnosis_code7a;
	  private String icd_cause_code7a;
	  private int priority_index7a;
	  private String checked_by;
	  private String diagnosis_code1d;
	  private String admsn_dschrg_flag_d;
	  private String icd_cause_code1d;
	  private int priority_index1d;
	  private String diagnosis_code2d;
	  private String icd_cause_code2d;
	  private int priority_index2d;
	  private String diagnosis_code3d;
	  private String icd_cause_code3d;
	  private int priority_index3d;
	  private String diagnosis_code4d;
	  private String icd_cause_code4d;
	  private int priority_index4d;
	  private String diagnosis_code5d;
	  private String icd_cause_code5d;
	  private int priority_index5d;
	  private String diagnosis_code6d;
	  private String icd_cause_code6d;
	  private int priority_index6d;
	  private String diagnosis_code7d;
	  private String icd_cause_code7d;
	  private int priority_index7d;
	  private Date updated_on;
	  private String fmn_approved_by;
	  private Date fmn_approved_on; 
	  private String condition1;
  	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 8)
	public String getMedical_unit() {
		return medical_unit;
	}
	public void setMedical_unit(String medical_unit) {
		this.medical_unit = medical_unit;
	}
	@Column(length = 20)
	public String getAnd_no() {
		return and_no;
	}
	public void setAnd_no(String and_no) {
		this.and_no = and_no;
	}
	@Column(length = 30)
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public Date getAdmsn_date() {
		return admsn_date;
	}
	public void setAdmsn_date(Date admsn_date) {
		this.admsn_date = admsn_date;
	}
	public Date getDschrg_date() {
		return dschrg_date;
	}
	public void setDschrg_date(Date dschrg_date) {
		this.dschrg_date = dschrg_date;
	}
	@Column(length = 30)
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	@Column(length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 3)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(length = 30)
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	@Column(length = 30)
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(length = 3)
	public String getNbb() {
		return nbb;
	}
	public void setNbb(String nbb) {
		this.nbb = nbb;
	}
	@Column(length = 20)
	public String getPersnl_no() {
		return persnl_no;
	}
	public void setPersnl_no(String persnl_no) {
		this.persnl_no = persnl_no;
	}
	@Column(length = 30)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(length = 20)
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Column(length = 100)
	public String getPersnl_name() {
		return persnl_name;
	}
	public void setPersnl_name(String persnl_name) {
		this.persnl_name = persnl_name;
	}
	@Column(length = 3)
	public String getPersnl_sex() {
		return persnl_sex;
	}
	public void setPersnl_sex(String persnl_sex) {
		this.persnl_sex = persnl_sex;
	}
	@Column(length = 100)
	public String getPersnl_unit() {
		return persnl_unit;
	}
	public void setPersnl_unit(String persnl_unit) {
		this.persnl_unit = persnl_unit;
	}
	@Column(length = 100)
	public String getPersnl_unit_desc() {
		return persnl_unit_desc;
	}
	public void setPersnl_unit_desc(String persnl_unit_desc) {
		this.persnl_unit_desc = persnl_unit_desc;
	}
	@Column(length = 150)
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	@Column(length = 100)
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	@Column(length = 50)
	public String getArm_corps() {
		return arm_corps;
	}
	public void setArm_corps(String arm_corps) {
		this.arm_corps = arm_corps;
	}
	@Column(length = 30)
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	@Column(length = 30)
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	@Column(length = 30)
	public String getPersnl_marital_status() {
		return persnl_marital_status;
	}
	public void setPersnl_marital_status(String persnl_marital_status) {
		this.persnl_marital_status = persnl_marital_status;
	}
	@Column(length = 150)
	public String getDist_origin() {
		return dist_origin;
	}
	public void setDist_origin(String dist_origin) {
		this.dist_origin = dist_origin;
	}
	@Column(length = 150)
	public String getState_origin() {
		return state_origin;
	}
	public void setState_origin(String state_origin) {
		this.state_origin = state_origin;
	}
	@Column(length = 250)
	public String getRecords_office() {
		return records_office;
	}
	public void setRecords_office(String records_office) {
		this.records_office = records_office;
	}
	@Column(length = 15)
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	@Column(length = 30)
	public String getAdmsn_type() {
		return admsn_type;
	}
	public void setAdmsn_type(String admsn_type) {
		this.admsn_type = admsn_type;
	}
	@Column(length = 100)
	public String getTrnsfrd_from() {
		return trnsfrd_from;
	}
	public void setTrnsfrd_from(String trnsfrd_from) {
		this.trnsfrd_from = trnsfrd_from;
	}
	@Column(length = 100)
	public String getTrnsfrd_from_desc() {
		return trnsfrd_from_desc;
	}
	public void setTrnsfrd_from_desc(String trnsfrd_from_desc) {
		this.trnsfrd_from_desc = trnsfrd_from_desc;
	}
	@Column(length = 30)
	public String getTrnsfrd_and_no() {
		return trnsfrd_and_no;
	}
	public void setTrnsfrd_and_no(String trnsfrd_and_no) {
		this.trnsfrd_and_no = trnsfrd_and_no;
	}
	@Column(length = 30)
	public String getOn_list() {
		return on_list;
	}
	public void setOn_list(String on_list) {
		this.on_list = on_list;
	}
	@Column(length =1)
	public String getMlc() {
		return mlc;
	}
	public void setMlc(String mlc) {
		this.mlc = mlc;
	}
	@Column(length = 30)
	public String getMlc_date() {
		return mlc_date;
	}
	public void setMlc_date(String mlc_date) {
		this.mlc_date = mlc_date;
	}
	@Column(length = 1)
	public String getInjry_rpt() {
		return injry_rpt;
	}
	public void setInjry_rpt(String injry_rpt) {
		this.injry_rpt = injry_rpt;
	}
	@Column(length = 6)
	public String getMed_ctgry_s() {
		return med_ctgry_s;
	}
	public void setMed_ctgry_s(String med_ctgry_s) {
		this.med_ctgry_s = med_ctgry_s;
	}
	@Column(length = 6)
	public String getMed_ctgry_h() {
		return med_ctgry_h;
	}
	public void setMed_ctgry_h(String med_ctgry_h) {
		this.med_ctgry_h = med_ctgry_h;
	}
	@Column(length = 6)
	public String getMed_ctgry_a() {
		return med_ctgry_a;
	}
	public void setMed_ctgry_a(String med_ctgry_a) {
		this.med_ctgry_a = med_ctgry_a;
	}
	@Column(length = 6)
	public String getMed_ctgry_p() {
		return med_ctgry_p;
	}
	public void setMed_ctgry_p(String med_ctgry_p) {
		this.med_ctgry_p = med_ctgry_p;
	}
	@Column(length = 6)
	public String getMed_ctgry_e() {
		return med_ctgry_e;
	}
	public void setMed_ctgry_e(String med_ctgry_e) {
		this.med_ctgry_e = med_ctgry_e;
	}
	@Column(length = 20)
	public String getDisposal() {
		return disposal;
	}
	public void setDisposal(String disposal) {
		this.disposal = disposal;
	}
	@Column(length = 100)
	public String getTrnsfrd_to() {
		return trnsfrd_to;
	}
	public void setTrnsfrd_to(String trnsfrd_to) {
		this.trnsfrd_to = trnsfrd_to;
	}
	@Column(length = 100)
	public String getTrnsfrd_to_desc() {
		return trnsfrd_to_desc;
	}
	public void setTrnsfrd_to_desc(String trnsfrd_to_desc) {
		this.trnsfrd_to_desc = trnsfrd_to_desc;
	}
	@Column(length = 250)
	public String getRejection_reason() {
		return rejection_reason;
	}
	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}
	@Column(length = 30)
	public String getPatient_status() {
		return patient_status;
	}
	public void setPatient_status(String patient_status) {
		this.patient_status = patient_status;
	}
	@Column(length = 30)
	public String getAdmsn_status() {
		return admsn_status;
	}
	public void setAdmsn_status(String admsn_status) {
		this.admsn_status = admsn_status;
	}
	@Column(length = 30)
	public String getDschrg_status() {
		return dschrg_status;
	}
	public void setDschrg_status(String dschrg_status) {
		this.dschrg_status = dschrg_status;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	@Column(length = 35)
	public String getApprvr_at_unit_by() {
		return apprvr_at_unit_by;
	}
	public void setApprvr_at_unit_by(String apprvr_at_unit_by) {
		this.apprvr_at_unit_by = apprvr_at_unit_by;
	}
	@Column(length = 35)
	public String getApprvr_at_miso_by() {
		return apprvr_at_miso_by;
	}
	public void setApprvr_at_miso_by(String apprvr_at_miso_by) {
		this.apprvr_at_miso_by = apprvr_at_miso_by;
	}
	@Column(length = 100)
	public String getNok_name() {
		return nok_name;
	}
	public void setNok_name(String nok_name) {
		this.nok_name = nok_name;
	}
	@Column(length = 30)
	public String getNok_relation() {
		return nok_relation;
	}
	public void setNok_relation(String nok_relation) {
		this.nok_relation = nok_relation;
	}
	@Column(length = 300)
	public String getNok_address() {
		return nok_address;
	}
	public void setNok_address(String nok_address) {
		this.nok_address = nok_address;
	}
	@Column(length = 50)
	public String getCda_no() {
		return cda_no;
	}
	public void setCda_no(String cda_no) {
		this.cda_no = cda_no;
	}
	@Column(length = 30)
	public String getType_injury() {
		return type_injury;
	}
	public void setType_injury(String type_injury) {
		this.type_injury = type_injury;
	}
	@Column(length = 150)
	public String getDetails_sickleave() {
		return details_sickleave;
	}
	public void setDetails_sickleave(String details_sickleave) {
		this.details_sickleave = details_sickleave;
	}
	@Column(length = 6)
	public String getInjury_report_a() {
		return injury_report_a;
	}
	public void setInjury_report_a(String injury_report_a) {
		this.injury_report_a = injury_report_a;
	}
	@Column(length = 30)
	public String getDischarge_ward() {
		return discharge_ward;
	}
	public void setDischarge_ward(String discharge_ward) {
		this.discharge_ward = discharge_ward;
	}
	@Column(length = 20)
	public String getTransport_mode() {
		return transport_mode;
	}
	public void setTransport_mode(String transport_mode) {
		this.transport_mode = transport_mode;
	}
	@Column(length = 30)
	public String getOther_details() {
		return other_details;
	}
	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}
	@Column(length = 6)
	public String getWard_no_a() {
		return ward_no_a;
	}
	public void setWard_no_a(String ward_no_a) {
		this.ward_no_a = ward_no_a;
	}
	@Column(length = 6)
	public String getWard_no_d() {
		return ward_no_d;
	}
	public void setWard_no_d(String ward_no_d) {
		this.ward_no_d = ward_no_d;
	}
	@Column(length = 1)
	public String getNot_patient() {
		return not_patient;
	}
	public void setNot_patient(String not_patient) {
		this.not_patient = not_patient;
	}
	@Column(length = 30)
	public String getDependent_details() {
		return dependent_details;
	}
	public void setDependent_details(String dependent_details) {
		this.dependent_details = dependent_details;
	}
	@Column(length = 300)
	public String getDischarge_remarks() {
		return discharge_remarks;
	}
	public void setDischarge_remarks(String discharge_remarks) {
		this.discharge_remarks = discharge_remarks;
	}
	@Column(length = 30)
	public String getAb64() {
		return ab64;
	}
	public void setAb64(String ab64) {
		this.ab64 = ab64;
	}
	@Column(length = 300)
	public String getIcd_remarks_a() {
		return icd_remarks_a;
	}
	public void setIcd_remarks_a(String icd_remarks_a) {
		this.icd_remarks_a = icd_remarks_a;
	}
	@Column(length = 300)
	public String getIcd_remarks_d() {
		return icd_remarks_d;
	}
	public void setIcd_remarks_d(String icd_remarks_d) {
		this.icd_remarks_d = icd_remarks_d;
	}
	@Column(length = 150)
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	@Column(length = 30)
	public String getDiagnosis_code1a() {
		return diagnosis_code1a;
	}
	public void setDiagnosis_code1a(String diagnosis_code1a) {
		this.diagnosis_code1a = diagnosis_code1a;
	}
	@Column(length = 3)
	public String getAdmsn_dschrg_flag_a() {
		return admsn_dschrg_flag_a;
	}
	public void setAdmsn_dschrg_flag_a(String admsn_dschrg_flag_a) {
		this.admsn_dschrg_flag_a = admsn_dschrg_flag_a;
	}
	@Column(length = 30)
	public String getIcd_cause_code1a() {
		return icd_cause_code1a;
	}
	public void setIcd_cause_code1a(String icd_cause_code1a) {
		this.icd_cause_code1a = icd_cause_code1a;
	}
	@Column(length = 30)
	public String getDiagnosis_code2a() {
		return diagnosis_code2a;
	}
	public void setDiagnosis_code2a(String diagnosis_code2a) {
		this.diagnosis_code2a = diagnosis_code2a;
	}
	@Column(length = 30)
	public String getIcd_cause_code2a() {
		return icd_cause_code2a;
	}
	public void setIcd_cause_code2a(String icd_cause_code2a) {
		this.icd_cause_code2a = icd_cause_code2a;
	}
	@Column(length = 30)
	public String getDiagnosis_code3a() {
		return diagnosis_code3a;
	}
	public void setDiagnosis_code3a(String diagnosis_code3a) {
		this.diagnosis_code3a = diagnosis_code3a;
	}
	@Column(length = 30)
	public String getIcd_cause_code3a() {
		return icd_cause_code3a;
	}
	public void setIcd_cause_code3a(String icd_cause_code3a) {
		this.icd_cause_code3a = icd_cause_code3a;
	}
	@Column(length = 30)
	public String getDiagnosis_code4a() {
		return diagnosis_code4a;
	}
	public void setDiagnosis_code4a(String diagnosis_code4a) {
		this.diagnosis_code4a = diagnosis_code4a;
	}
	@Column(length = 30)
	public String getIcd_cause_code4a() {
		return icd_cause_code4a;
	}
	public void setIcd_cause_code4a(String icd_cause_code4a) {
		this.icd_cause_code4a = icd_cause_code4a;
	}
	@Column(length = 30)
	public String getDiagnosis_code5a() {
		return diagnosis_code5a;
	}
	public void setDiagnosis_code5a(String diagnosis_code5a) {
		this.diagnosis_code5a = diagnosis_code5a;
	}
	@Column(length = 30)
	public String getIcd_cause_code5a() {
		return icd_cause_code5a;
	}
	public void setIcd_cause_code5a(String icd_cause_code5a) {
		this.icd_cause_code5a = icd_cause_code5a;
	}
	@Column(length = 30)
	public String getDiagnosis_code6a() {
		return diagnosis_code6a;
	}
	public void setDiagnosis_code6a(String diagnosis_code6a) {
		this.diagnosis_code6a = diagnosis_code6a;
	}
	@Column(length = 30)
	public String getIcd_cause_code6a() {
		return icd_cause_code6a;
	}
	public void setIcd_cause_code6a(String icd_cause_code6a) {
		this.icd_cause_code6a = icd_cause_code6a;
	}
	@Column(length = 30)
	public String getDiagnosis_code7a() {
		return diagnosis_code7a;
	}
	public void setDiagnosis_code7a(String diagnosis_code7a) {
		this.diagnosis_code7a = diagnosis_code7a;
	}
	@Column(length = 30)
	public String getIcd_cause_code7a() {
		return icd_cause_code7a;
	}
	public void setIcd_cause_code7a(String icd_cause_code7a) {
		this.icd_cause_code7a = icd_cause_code7a;
	}
	@Column(length = 35)
	public String getChecked_by() {
		return checked_by;
	}
	public void setChecked_by(String checked_by) {
		this.checked_by = checked_by;
	}
	@Column(length = 30)
	public String getDiagnosis_code1d() {
		return diagnosis_code1d;
	}
	public void setDiagnosis_code1d(String diagnosis_code1d) {
		this.diagnosis_code1d = diagnosis_code1d;
	}
	@Column(length = 1)
	public String getAdmsn_dschrg_flag_d() {
		return admsn_dschrg_flag_d;
	}
	public void setAdmsn_dschrg_flag_d(String admsn_dschrg_flag_d) {
		this.admsn_dschrg_flag_d = admsn_dschrg_flag_d;
	}
	@Column(length = 30)
	public String getIcd_cause_code1d() {
		return icd_cause_code1d;
	}
	public void setIcd_cause_code1d(String icd_cause_code1d) {
		this.icd_cause_code1d = icd_cause_code1d;
	}
	@Column(length = 30)
	public String getDiagnosis_code2d() {
		return diagnosis_code2d;
	}
	public void setDiagnosis_code2d(String diagnosis_code2d) {
		this.diagnosis_code2d = diagnosis_code2d;
	}
	@Column(length = 30)
	public String getIcd_cause_code2d() {
		return icd_cause_code2d;
	}
	public void setIcd_cause_code2d(String icd_cause_code2d) {
		this.icd_cause_code2d = icd_cause_code2d;
	}
	@Column(length = 30)
	public String getDiagnosis_code3d() {
		return diagnosis_code3d;
	}
	public void setDiagnosis_code3d(String diagnosis_code3d) {
		this.diagnosis_code3d = diagnosis_code3d;
	}
	@Column(length = 30)
	public String getIcd_cause_code3d() {
		return icd_cause_code3d;
	}
	public void setIcd_cause_code3d(String icd_cause_code3d) {
		this.icd_cause_code3d = icd_cause_code3d;
	}
	@Column(length = 30)
	public String getDiagnosis_code4d() {
		return diagnosis_code4d;
	}
	public void setDiagnosis_code4d(String diagnosis_code4d) {
		this.diagnosis_code4d = diagnosis_code4d;
	}
	@Column(length = 30)
	public String getIcd_cause_code4d() {
		return icd_cause_code4d;
	}
	public void setIcd_cause_code4d(String icd_cause_code4d) {
		this.icd_cause_code4d = icd_cause_code4d;
	}
	@Column(length = 30)
	public String getDiagnosis_code5d() {
		return diagnosis_code5d;
	}
	public void setDiagnosis_code5d(String diagnosis_code5d) {
		this.diagnosis_code5d = diagnosis_code5d;
	}
	@Column(length = 30)
	public String getIcd_cause_code5d() {
		return icd_cause_code5d;
	}
	public void setIcd_cause_code5d(String icd_cause_code5d) {
		this.icd_cause_code5d = icd_cause_code5d;
	}
	@Column(length = 30)
	public String getDiagnosis_code6d() {
		return diagnosis_code6d;
	}
	public void setDiagnosis_code6d(String diagnosis_code6d) {
		this.diagnosis_code6d = diagnosis_code6d;
	}
	@Column(length = 30)
	public String getIcd_cause_code6d() {
		return icd_cause_code6d;
	}
	public void setIcd_cause_code6d(String icd_cause_code6d) {
		this.icd_cause_code6d = icd_cause_code6d;
	}
	@Column(length = 30)
	public String getDiagnosis_code7d() {
		return diagnosis_code7d;
	}
	public void setDiagnosis_code7d(String diagnosis_code7d) {
		this.diagnosis_code7d = diagnosis_code7d;
	}
	@Column(length = 30)
	public String getIcd_cause_code7d() {
		return icd_cause_code7d;
	}
	public void setIcd_cause_code7d(String icd_cause_code7d) {
		this.icd_cause_code7d = icd_cause_code7d;
	}

	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
	public int getAge_year() {
		return age_year;
	}
	public void setAge_year(int age_year) {
		this.age_year = age_year;
	}
	public int getAge_month() {
		return age_month;
	}
	public void setAge_month(int age_month) {
		this.age_month = age_month;
	}
	public int getAge_days() {
		return age_days;
	}
	public void setAge_days(int age_days) {
		this.age_days = age_days;
	}
	public Double getNbb_weight() {
		return nbb_weight;
	}
	public void setNbb_weight(Double nbb_weight) {
		this.nbb_weight = nbb_weight;
	}
	public int getPersnl_age_year() {
		return persnl_age_year;
	}
	public void setPersnl_age_year(int persnl_age_year) {
		this.persnl_age_year = persnl_age_year;
	}
	public int getPersnl_age_month() {
		return persnl_age_month;
	}
	public void setPersnl_age_month(int persnl_age_month) {
		this.persnl_age_month = persnl_age_month;
	}
	public int getService_years() {
		return service_years;
	}
	public void setService_years(int service_years) {
		this.service_years = service_years;
	}
	public int getService_months() {
		return service_months;
	}
	public void setService_months(int service_months) {
		this.service_months = service_months;
	}
	public Date getInjry_rpt_date() {
		return injry_rpt_date;
	}
	public void setInjry_rpt_date(Date injry_rpt_date) {
		this.injry_rpt_date = injry_rpt_date;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Date getApprvd_at_unit_on() {
		return apprvd_at_unit_on;
	}
	public void setApprvd_at_unit_on(Date apprvd_at_unit_on) {
		this.apprvd_at_unit_on = apprvd_at_unit_on;
	}
	public Date getApprvd_at_miso_on() {
		return apprvd_at_miso_on;
	}
	public void setApprvd_at_miso_on(Date apprvd_at_miso_on) {
		this.apprvd_at_miso_on = apprvd_at_miso_on;
	}
	public Date getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}
	public int getPriority_index1a() {
		return priority_index1a;
	}
	public void setPriority_index1a(int priority_index1a) {
		this.priority_index1a = priority_index1a;
	}
	public int getPriority_index2a() {
		return priority_index2a;
	}
	public void setPriority_index2a(int priority_index2a) {
		this.priority_index2a = priority_index2a;
	}
	public int getPriority_index3a() {
		return priority_index3a;
	}
	public void setPriority_index3a(int priority_index3a) {
		this.priority_index3a = priority_index3a;
	}
	public int getPriority_index4a() {
		return priority_index4a;
	}
	public void setPriority_index4a(int priority_index4a) {
		this.priority_index4a = priority_index4a;
	}
	public int getPriority_index5a() {
		return priority_index5a;
	}
	public void setPriority_index5a(int priority_index5a) {
		this.priority_index5a = priority_index5a;
	}
	public int getPriority_index6a() {
		return priority_index6a;
	}
	public void setPriority_index6a(int priority_index6a) {
		this.priority_index6a = priority_index6a;
	}
	public int getPriority_index7a() {
		return priority_index7a;
	}
	public void setPriority_index7a(int priority_index7a) {
		this.priority_index7a = priority_index7a;
	}
	public int getPriority_index1d() {
		return priority_index1d;
	}
	public void setPriority_index1d(int priority_index1d) {
		this.priority_index1d = priority_index1d;
	}
	public int getPriority_index2d() {
		return priority_index2d;
	}
	public void setPriority_index2d(int priority_index2d) {
		this.priority_index2d = priority_index2d;
	}
	public int getPriority_index3d() {
		return priority_index3d;
	}
	public void setPriority_index3d(int priority_index3d) {
		this.priority_index3d = priority_index3d;
	}
	public int getPriority_index4d() {
		return priority_index4d;
	}
	public void setPriority_index4d(int priority_index4d) {
		this.priority_index4d = priority_index4d;
	}
	public int getPriority_index5d() {
		return priority_index5d;
	}
	public void setPriority_index5d(int priority_index5d) {
		this.priority_index5d = priority_index5d;
	}
	public int getPriority_index6d() {
		return priority_index6d;
	}
	public void setPriority_index6d(int priority_index6d) {
		this.priority_index6d = priority_index6d;
	}
	public int getPriority_index7d() {
		return priority_index7d;
	}
	public void setPriority_index7d(int priority_index7d) {
		this.priority_index7d = priority_index7d;
	}
	@Column(length = 30)
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	@Column(length = 35)
	public String getFmn_approved_by() {
		return fmn_approved_by;
	}
	public void setFmn_approved_by(String fmn_approved_by) {
		this.fmn_approved_by = fmn_approved_by;
	}
	public Date getFmn_approved_on() {
		return fmn_approved_on;
	}
	public void setFmn_approved_on(Date fmn_approved_on) {
		this.fmn_approved_on = fmn_approved_on;
	}
	@Column(length = 255)
	public String getCondition1() {
		return condition1;
	}
	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}
}