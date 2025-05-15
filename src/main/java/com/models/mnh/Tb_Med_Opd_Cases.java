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
@Table(name="tb_med_opdcases", uniqueConstraints= {
@UniqueConstraint(columnNames="id")})
public class Tb_Med_Opd_Cases {
	
	private int id;
	private String sus_no;
	private int total_during_month;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private String qtr;
	private int officer_self;
	private int officer_family;
	private int jco_ors_self;
	private int jco_ors_family;
	private int ex_serv_self;
	private int ex_serv_family;
	private int para_mil_pers_self;
	private int para_mil_pers_family;
	private int civilian_self;
	private int civilian_family;
	private String remarks;
	private int year;
	private String ward;
	
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Integer getTotal_during_month() {
		return total_during_month;
	}
	public void setTotal_during_month(Integer total_during_month) {
		this.total_during_month = total_during_month;
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
	@Column(length = 35)
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	@Column(length = 10)
	public String getQtr() {
		return qtr;
	}
	public void setQtr(String qtr) {
		this.qtr = qtr;
	}
	public int getOfficer_self() {
		return officer_self;
	}
	public void setOfficer_self(int officer_self) {
		this.officer_self = officer_self;
	}
	public int getOfficer_family() {
		return officer_family;
	}
	public void setOfficer_family(int officer_family) {
		this.officer_family = officer_family;
	}
	public int getJco_ors_self() {
		return jco_ors_self;
	}
	public void setJco_ors_self(int jco_ors_self) {
		this.jco_ors_self = jco_ors_self;
	}
	public int getJco_ors_family() {
		return jco_ors_family;
	}
	public void setJco_ors_family(int jco_ors_family) {
		this.jco_ors_family = jco_ors_family;
	}
	public int getEx_serv_self() {
		return ex_serv_self;
	}
	public void setEx_serv_self(int ex_serv_self) {
		this.ex_serv_self = ex_serv_self;
	}
	public int getEx_serv_family() {
		return ex_serv_family;
	}
	public void setEx_serv_family(int ex_serv_family) {
		this.ex_serv_family = ex_serv_family;
	}
	public int getPara_mil_pers_self() {
		return para_mil_pers_self;
	}
	public void setPara_mil_pers_self(int para_mil_pers_self) {
		this.para_mil_pers_self = para_mil_pers_self;
	}
	public int getPara_mil_pers_family() {
		return para_mil_pers_family;
	}
	public void setPara_mil_pers_family(int para_mil_pers_family) {
		this.para_mil_pers_family = para_mil_pers_family;
	}
	public int getCivilian_self() {
		return civilian_self;
	}
	public void setCivilian_self(int civilian_self) {
		this.civilian_self = civilian_self;
	}
	public int getCivilian_family() {
		return civilian_family;
	}
	public void setCivilian_family(int civilian_family) {
		this.civilian_family = civilian_family;
	}
	@Column(length = 250)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
}