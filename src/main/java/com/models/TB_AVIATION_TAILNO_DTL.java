package com.models;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//Created By Mitesh  (20-11-2024)

@Entity
@Table(name = "tb_aviation_tail_no_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_AVIATION_TAILNO_DTL {
	
	private int id;
	private String tail_no;
	private String sus_no;
	private String unit_name;
	private String eng_name;
	private String lh_eng_ser_no;
	private String lh_eng_hrs;
	private String rh_eng_ser_no;
	private String rh_eng_hrs;
	private Date lh_eng_installed_date;
	private Date rh_eng_installed_date;
	private Date date_of_acceptance_osft;
	private String std_nomclature;
	private Date created_on;
	private String created_by;
	private String country_isocode;
	private String purchase_cost;
	private String Classifications;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTail_no() {
		return tail_no;
	}
	public void setTail_no(String tail_no) {
		this.tail_no = tail_no;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getLh_eng_ser_no() {
		return lh_eng_ser_no;
	}
	public void setLh_eng_ser_no(String lh_eng_ser_no) {
		this.lh_eng_ser_no = lh_eng_ser_no;
	}
	public String getLh_eng_hrs() {
		return lh_eng_hrs;
	}
	public void setLh_eng_hrs(String lh_eng_hrs) {
		this.lh_eng_hrs = lh_eng_hrs;
	}
	public String getRh_eng_ser_no() {
		return rh_eng_ser_no;
	}
	public void setRh_eng_ser_no(String rh_eng_ser_no) {
		this.rh_eng_ser_no = rh_eng_ser_no;
	}
	public String getRh_eng_hrs() {
		return rh_eng_hrs;
	}
	public void setRh_eng_hrs(String rh_eng_hrs) {
		this.rh_eng_hrs = rh_eng_hrs;
	}
	public Date getLh_eng_installed_date() {
		return lh_eng_installed_date;
	}
	public void setLh_eng_installed_date(Date lh_eng_installed_date) {
		this.lh_eng_installed_date = lh_eng_installed_date;
	}
	public Date getRh_eng_installed_date() {
		return rh_eng_installed_date;
	}
	public void setRh_eng_installed_date(Date rh_eng_installed_date) {
		this.rh_eng_installed_date = rh_eng_installed_date;
	}
	public Date getDate_of_acceptance_osft() {
		return date_of_acceptance_osft;
	}
	public void setDate_of_acceptance_osft(Date date_of_acceptance_osft) {
		this.date_of_acceptance_osft = date_of_acceptance_osft;
	}
	public String getStd_nomclature() {
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature) {
		this.std_nomclature = std_nomclature;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCountry_isocode() {
		return country_isocode;
	}
	public void setCountry_isocode(String country_isocode) {
		this.country_isocode = country_isocode;
	}
	public String getPurchase_cost() {
		return purchase_cost;
	}
	public void setPurchase_cost(String purchase_cost) {
		this.purchase_cost = purchase_cost;
	}
	public String getClassifications() {
		return Classifications;
	}
	public void setClassifications(String classifications) {
		Classifications = classifications;
	}

}