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
@Table(name = "tb_miso_insp_op_preparedness", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_OP_PREPAREDNESS {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String operationalTaskCapability;
	private String shortageOfResources;
	private String manpowerDeficiency;
	private String trainingChallenges;
	private String operationalPreparednessDegree;

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
	public String getOperationalTaskCapability() {
		return operationalTaskCapability;
	}
	public void setOperationalTaskCapability(String operationalTaskCapability) {
		this.operationalTaskCapability = operationalTaskCapability;
	}
	public String getShortageOfResources() {
		return shortageOfResources;
	}
	public void setShortageOfResources(String shortageOfResources) {
		this.shortageOfResources = shortageOfResources;
	}
	public String getManpowerDeficiency() {
		return manpowerDeficiency;
	}
	public void setManpowerDeficiency(String manpowerDeficiency) {
		this.manpowerDeficiency = manpowerDeficiency;
	}
	public String getTrainingChallenges() {
		return trainingChallenges;
	}
	public void setTrainingChallenges(String trainingChallenges) {
		this.trainingChallenges = trainingChallenges;
	}
	public String getOperationalPreparednessDegree() {
		return operationalPreparednessDegree;
	}
	public void setOperationalPreparednessDegree(String operationalPreparednessDegree) {
		this.operationalPreparednessDegree = operationalPreparednessDegree;
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
