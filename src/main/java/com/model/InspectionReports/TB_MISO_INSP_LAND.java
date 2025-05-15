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
@Table(name = "tb_miso_insp_land", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_LAND {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String defenceLandParticulars;
	private String landRecordRegisterMaintained;
	private String landDemarcated;
	private String landUtilized;
	private String vacantLandDetails;
	private String safetyMeasuresAdequate;
	private String evictionActionDetails;
	private String remarksSuggestions;	
	private Integer status;
	private String year;	
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

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
	public String getDefenceLandParticulars() {
		return defenceLandParticulars;
	}
	public void setDefenceLandParticulars(String defenceLandParticulars) {
		this.defenceLandParticulars = defenceLandParticulars;
	}
	public String getLandRecordRegisterMaintained() {
		return landRecordRegisterMaintained;
	}
	public void setLandRecordRegisterMaintained(String landRecordRegisterMaintained) {
		this.landRecordRegisterMaintained = landRecordRegisterMaintained;
	}
	public String getLandDemarcated() {
		return landDemarcated;
	}
	public void setLandDemarcated(String landDemarcated) {
		this.landDemarcated = landDemarcated;
	}
	public String getLandUtilized() {
		return landUtilized;
	}
	public void setLandUtilized(String landUtilized) {
		this.landUtilized = landUtilized;
	}
	public String getVacantLandDetails() {
		return vacantLandDetails;
	}
	public void setVacantLandDetails(String vacantLandDetails) {
		this.vacantLandDetails = vacantLandDetails;
	}
	public String getSafetyMeasuresAdequate() {
		return safetyMeasuresAdequate;
	}
	public void setSafetyMeasuresAdequate(String safetyMeasuresAdequate) {
		this.safetyMeasuresAdequate = safetyMeasuresAdequate;
	}
	public String getEvictionActionDetails() {
		return evictionActionDetails;
	}
	public void setEvictionActionDetails(String evictionActionDetails) {
		this.evictionActionDetails = evictionActionDetails;
	}
	public String getRemarksSuggestions() {
		return remarksSuggestions;
	}
	public void setRemarksSuggestions(String remarksSuggestions) {
		this.remarksSuggestions = remarksSuggestions;
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
	
}
