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
@Table(name = "tb_insp_education_standards", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_EDUCATION_STANDARDS {

	private int id;
	private String jco_affected;
	private String jco_qualified;
	private String jco_not_qualified;
	private String jco_map;
	private String jco_education;
	private String jco_promotion;

	private String nco_affected;
	private String nco_qualified;
	private String nco_not_qualified;
	private String nco_map;
	private String nco_education;
	private String nco_promotion;

	private String or_affected;
	private String or_qualified;
	private String or_not_qualified;
	private String or_map;
	private String or_education;
	private String or_promotion;

	private String total_affected;
	private String total_qualified;
	private String total_not_qualified;
	private String total_map;
	private String total_education;
	private String total_promotion;

	private Date created_date;
	private String created_by;
	private String approved_by;
	private Date approved_date;
	private int status;
	private String year;
	private String sus_no;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJco_affected() {
		return jco_affected;
	}
	public void setJco_affected(String jco_affected) {
		this.jco_affected = jco_affected;
	}
	public String getJco_qualified() {
		return jco_qualified;
	}
	public void setJco_qualified(String jco_qualified) {
		this.jco_qualified = jco_qualified;
	}
	public String getJco_not_qualified() {
		return jco_not_qualified;
	}
	public void setJco_not_qualified(String jco_not_qualified) {
		this.jco_not_qualified = jco_not_qualified;
	}
	public String getJco_map() {
		return jco_map;
	}
	public void setJco_map(String jco_map) {
		this.jco_map = jco_map;
	}
	public String getJco_promotion() {
		return jco_promotion;
	}
	public void setJco_promotion(String jco_promotion) {
		this.jco_promotion = jco_promotion;
	}
	public String getNco_affected() {
		return nco_affected;
	}
	public void setNco_affected(String nco_affected) {
		this.nco_affected = nco_affected;
	}
	public String getNco_qualified() {
		return nco_qualified;
	}
	public void setNco_qualified(String nco_qualified) {
		this.nco_qualified = nco_qualified;
	}
	public String getNco_not_qualified() {
		return nco_not_qualified;
	}
	public void setNco_not_qualified(String nco_not_qualified) {
		this.nco_not_qualified = nco_not_qualified;
	}
	public String getNco_map() {
		return nco_map;
	}
	public void setNco_map(String nco_map) {
		this.nco_map = nco_map;
	}
	public String getNco_promotion() {
		return nco_promotion;
	}
	public void setNco_promotion(String nco_promotion) {
		this.nco_promotion = nco_promotion;
	}
	public String getOr_affected() {
		return or_affected;
	}
	public void setOr_affected(String or_affected) {
		this.or_affected = or_affected;
	}
	public String getOr_qualified() {
		return or_qualified;
	}
	public void setOr_qualified(String or_qualified) {
		this.or_qualified = or_qualified;
	}
	public String getOr_not_qualified() {
		return or_not_qualified;
	}
	public void setOr_not_qualified(String or_not_qualified) {
		this.or_not_qualified = or_not_qualified;
	}
	public String getOr_map() {
		return or_map;
	}
	public void setOr_map(String or_map) {
		this.or_map = or_map;
	}
	public String getOr_promotion() {
		return or_promotion;
	}
	public void setOr_promotion(String or_promotion) {
		this.or_promotion = or_promotion;
	}
	public String getTotal_affected() {
		return total_affected;
	}
	public void setTotal_affected(String total_affected) {
		this.total_affected = total_affected;
	}
	public String getTotal_qualified() {
		return total_qualified;
	}
	public void setTotal_qualified(String total_qualified) {
		this.total_qualified = total_qualified;
	}
	public String getTotal_not_qualified() {
		return total_not_qualified;
	}
	public void setTotal_not_qualified(String total_not_qualified) {
		this.total_not_qualified = total_not_qualified;
	}
	public String getTotal_map() {
		return total_map;
	}
	public void setTotal_map(String total_map) {
		this.total_map = total_map;
	}
	public String getTotal_promotion() {
		return total_promotion;
	}
	public void setTotal_promotion(String total_promotion) {
		this.total_promotion = total_promotion;
	}
	public String getCreated_by() {
		return created_by;
	}
	public String getJco_education() {
		return jco_education;
	}
	public void setJco_education(String jco_education) {
		this.jco_education = jco_education;
	}
	public String getNco_education() {
		return nco_education;
	}
	public void setNco_education(String nco_education) {
		this.nco_education = nco_education;
	}
	public String getOr_education() {
		return or_education;
	}
	public void setOr_education(String or_education) {
		this.or_education = or_education;
	}
	public String getTotal_education() {
		return total_education;
	}
	public void setTotal_education(String total_education) {
		this.total_education = total_education;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	


}
