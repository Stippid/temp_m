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
@Table(name = "tb_miso_insp_animal", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_ANIMAL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String type;
	private String auth;
	private String held;
	private String sur;
	private String defi;	
	private String conditon;
	private String teatment;
	private String grooming;
	private String feeding;
	private String watering;
	private String clipping;	
	private String f_feet;
	private String saddlery;
	private String line_gear;
	private String accomodation;
	private String remarks;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getHeld() {
		return held;
	}
	public void setHeld(String held) {
		this.held = held;
	}
	public String getSur() {
		return sur;
	}
	public void setSur(String sur) {
		this.sur = sur;
	}
	public String getDefi() {
		return defi;
	}
	public void setDefi(String defi) {
		this.defi = defi;
	}
	public String getConditon() {
		return conditon;
	}
	public void setConditon(String conditon) {
		this.conditon = conditon;
	}
	public String getTeatment() {
		return teatment;
	}
	public void setTeatment(String teatment) {
		this.teatment = teatment;
	}
	public String getGrooming() {
		return grooming;
	}
	public void setGrooming(String grooming) {
		this.grooming = grooming;
	}
	public String getFeeding() {
		return feeding;
	}
	public void setFeeding(String feeding) {
		this.feeding = feeding;
	}
	public String getWatering() {
		return watering;
	}
	public void setWatering(String watering) {
		this.watering = watering;
	}
	public String getClipping() {
		return clipping;
	}
	public void setClipping(String clipping) {
		this.clipping = clipping;
	}
	public String getF_feet() {
		return f_feet;
	}
	public void setF_feet(String f_feet) {
		this.f_feet = f_feet;
	}
	public String getSaddlery() {
		return saddlery;
	}
	public void setSaddlery(String saddlery) {
		this.saddlery = saddlery;
	}
	public String getLine_gear() {
		return line_gear;
	}
	public void setLine_gear(String line_gear) {
		this.line_gear = line_gear;
	}
	public String getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
