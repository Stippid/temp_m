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
@Table(name = "tb_miso_insp_army_core_values", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_ARMY_CORE_VALUES {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String integrity;
	private String loyalty;
	private String duty;
	private String respect;
	private String selfless_service;
	private String courage;
	private String honour;
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
	public String getIntegrity() {
		return integrity;
	}
	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}
	public String getLoyalty() {
		return loyalty;
	}
	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getRespect() {
		return respect;
	}
	public void setRespect(String respect) {
		this.respect = respect;
	}
	public String getSelfless_service() {
		return selfless_service;
	}
	public void setSelfless_service(String selfless_service) {
		this.selfless_service = selfless_service;
	}
	public String getCourage() {
		return courage;
	}
	public void setCourage(String courage) {
		this.courage = courage;
	}

	public String getHonour() {
		return honour;
	}
	public void setHonour(String honour) {
		this.honour = honour;
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
