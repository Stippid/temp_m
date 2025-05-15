package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_provision_transport_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_PROVISION_TRANSPORT_MASTER {

	private int id;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String letter_no;
	private String month_cal;
	private String year_cal;
	private String we_pe_no;
	private String wet_pet_no;
	private String unit_type;
	private String force_type;
	private String no_of_units;	
	private String status;
	private String letter_date;
	private String we_type;
	private String sponsor_dire;
	private String status_delete;
	
  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getLetter_no() {
		return letter_no;
	}
	public void setLetter_no(String letter_no) {
		this.letter_no = letter_no;
	}
	public String getMonth_cal() {
		return month_cal;
	}
	public void setMonth_cal(String month_cal) {
		this.month_cal = month_cal;
	}
	public String getYear_cal() {
		return year_cal;
	}
	public void setYear_cal(String year_cal) {
		this.year_cal = year_cal;
	}
	public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}
	public String getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	
	public String getForce_type() {
		return force_type;
	}
	public void setForce_type(String force_type) {
		this.force_type = force_type;
	}
	public String getNo_of_units() {
		return no_of_units;
	}
	public void setNo_of_units(String no_of_units) {
		this.no_of_units = no_of_units;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLetter_date() {
		return letter_date;
	}
	public void setLetter_date(String letter_date) {
		this.letter_date = letter_date;
	}
	public String getWe_type() {
		return we_type;
	}
	public void setWe_type(String we_type) {
		this.we_type = we_type;
	}
	public String getSponsor_dire() {
		return sponsor_dire;
	}
	public void setSponsor_dire(String sponsor_dire) {
		this.sponsor_dire = sponsor_dire;
	}
	public String getStatus_delete() {
		return status_delete;
	}
	public void setStatus_delete(String status_delete) {
		this.status_delete = status_delete;
	}

	
	
}
