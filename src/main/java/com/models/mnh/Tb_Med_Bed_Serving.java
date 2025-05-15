package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_bed_serving", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Bed_Serving {
	private int id;
	private String sus_no;
	private String qtr;
	private int year;
	private int ofcr_max;
	private int ofcr_tot;
	private Double ofcr_avg;
	private int jco_max;
	private int jco_tot;
    private Double jco_avg;
	private int ofcr_fmly_max;
	private int ofcr_fmly_tot;
	private Double ofcr_fmly_avg;
	private int jco_fmly_max;
	private int jco_fmly_tot;
    private Double jco_fmly_avg;
	private String remarks;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;

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
	@Column(length = 10)
	public String getQtr() {
		return qtr;
	}
	public void setQtr(String qtr) {
		this.qtr = qtr;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getOfcr_max() {
		return ofcr_max;
	}
	public void setOfcr_max(int ofcr_max) {
		this.ofcr_max = ofcr_max;
	}
	public int getOfcr_tot() {
		return ofcr_tot;
	}
	public void setOfcr_tot(int ofcr_tot) {
		this.ofcr_tot = ofcr_tot;
	}
	public Double getOfcr_avg() {
		return ofcr_avg;
	}
	public void setOfcr_avg(Double ofcr_avg) {
		this.ofcr_avg = ofcr_avg;
	}
	public int getJco_max() {
		return jco_max;
	}
	public void setJco_max(int jco_max) {
		this.jco_max = jco_max;
	}
	public int getJco_tot() {
		return jco_tot;
	}
	public void setJco_tot(int jco_tot) {
		this.jco_tot = jco_tot;
	}
	public Double getJco_avg() {
		return jco_avg;
	}
	public void setJco_avg(Double jco_avg) {
		this.jco_avg = jco_avg;
	}
	public int getOfcr_fmly_max() {
		return ofcr_fmly_max;
	}
	public void setOfcr_fmly_max(int ofcr_fmly_max) {
		this.ofcr_fmly_max = ofcr_fmly_max;
	}
	public int getOfcr_fmly_tot() {
		return ofcr_fmly_tot;
	}
	public void setOfcr_fmly_tot(int ofcr_fmly_tot) {
		this.ofcr_fmly_tot = ofcr_fmly_tot;
	}
	public Double getOfcr_fmly_avg() {
		return ofcr_fmly_avg;
	}
	public void setOfcr_fmly_avg(Double ofcr_fmly_avg) {
		this.ofcr_fmly_avg = ofcr_fmly_avg;
	}
	public int getJco_fmly_max() {
		return jco_fmly_max;
	}
	public void setJco_fmly_max(int jco_fmly_max) {
		this.jco_fmly_max = jco_fmly_max;
	}
	public int getJco_fmly_tot() {
		return jco_fmly_tot;
	}
	public void setJco_fmly_tot(int jco_fmly_tot) {
		this.jco_fmly_tot = jco_fmly_tot;
	}
	public Double getJco_fmly_avg() {
		return jco_fmly_avg;
	}
	public void setJco_fmly_avg(Double jco_fmly_avg) {
		this.jco_fmly_avg = jco_fmly_avg;
	}
	@Column(length = 255)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
}