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
@Table(name = "tb_miso_insp_defi_equp_effi", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_DEFI_EQUP_EFFI {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String nomenclature;
	private String a;
	private String u;
	private String auth;
	private String held;
	private String defi;
	private String dues_in;
	private String dues_out;
	private String remarks;
	private Integer status;
	private String year;	
	private Date created_date;
	private String created_by;

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
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
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
	public String getDefi() {
		return defi;
	}
	public void setDefi(String defi) {
		this.defi = defi;
	}
	public String getDues_in() {
		return dues_in;
	}
	public void setDues_in(String dues_in) {
		this.dues_in = dues_in;
	}
	public String getDues_out() {
		return dues_out;
	}
	public void setDues_out(String dues_out) {
		this.dues_out = dues_out;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
