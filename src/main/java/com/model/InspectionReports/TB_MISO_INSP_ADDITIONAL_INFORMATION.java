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
@Table(name = "tb_miso_insp_additional_information", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_ADDITIONAL_INFORMATION {

	@Id
	@GeneratedValue
	private int id;
	
	private String state_public_Funds;
	private String state_discp;
	private String state_complaints_petitions;
	private String state_work_formation;
	private String peculiar_aspect;
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
	public String getState_public_Funds() {
		return state_public_Funds;
	}
	public void setState_public_Funds(String state_public_Funds) {
		this.state_public_Funds = state_public_Funds;
	}
	public String getState_discp() {
		return state_discp;
	}
	public void setState_discp(String state_discp) {
		this.state_discp = state_discp;
	}
	public String getState_complaints_petitions() {
		return state_complaints_petitions;
	}
	public void setState_complaints_petitions(String state_complaints_petitions) {
		this.state_complaints_petitions = state_complaints_petitions;
	}
	public String getState_work_formation() {
		return state_work_formation;
	}
	public void setState_work_formation(String state_work_formation) {
		this.state_work_formation = state_work_formation;
	}
	public String getPeculiar_aspect() {
		return peculiar_aspect;
	}
	public void setPeculiar_aspect(String peculiar_aspect) {
		this.peculiar_aspect = peculiar_aspect;
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
