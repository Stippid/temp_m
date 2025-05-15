package com.model.InspectionReports;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_insp_human_resource_developement", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_HUMAN_RESOURCE_DEVELOPEMENT {

	
	@Id
	@GeneratedValue
	private int id;
	
	private String junior_leader;
	private String welfare_troops;
	private String measures_resolution;
	private String enhancement_education;
	private String training_career;
	private String training_provided;
	private String audit_objection;
	private String state_complaints;
	private String arc;
	private String miscellaneous;
	private String personal_no;
	
	private Integer status;
	private String year;	
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;
private String sus_no;
	
	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public String getJunior_leader() {
		return junior_leader;
	}
	public void setJunior_leader(String junior_leader) {
		this.junior_leader = junior_leader;
	}
	public String getWelfare_troops() {
		return welfare_troops;
	}
	public void setWelfare_troops(String welfare_troops) {
		this.welfare_troops = welfare_troops;
	}
	public String getMeasures_resolution() {
		return measures_resolution;
	}
	public void setMeasures_resolution(String measures_resolution) {
		this.measures_resolution = measures_resolution;
	}
	public String getEnhancement_education() {
		return enhancement_education;
	}
	public void setEnhancement_education(String enhancement_education) {
		this.enhancement_education = enhancement_education;
	}
	public String getTraining_career() {
		return training_career;
	}
	public void setTraining_career(String training_career) {
		this.training_career = training_career;
	}
	public String getTraining_provided() {
		return training_provided;
	}
	public void setTraining_provided(String training_provided) {
		this.training_provided = training_provided;
	}
	public String getAudit_objection() {
		return audit_objection;
	}
	public void setAudit_objection(String audit_objection) {
		this.audit_objection = audit_objection;
	}
	public String getState_complaints() {
		return state_complaints;
	}
	public void setState_complaints(String state_complaints) {
		this.state_complaints = state_complaints;
	}
	public String getArc() {
		return arc;
	}
	public void setArc(String arc) {
		this.arc = arc;
	}
	public String getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getPersonal_no() {
		return personal_no;
	}
	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}
	
}
