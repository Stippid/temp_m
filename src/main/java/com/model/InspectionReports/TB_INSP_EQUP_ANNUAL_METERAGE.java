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
@Table(name = "tb_insp_equp_annual_meterage", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_INSP_EQUP_ANNUAL_METERAGE {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Integer status;
	private String type_of_duty;
	private String equp_authorize;
	private String equp_cove;
	private String equp_remark;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType_of_duty() {
		return type_of_duty;
	}
	public void setType_of_duty(String type_of_duty) {
		this.type_of_duty = type_of_duty;
	}
	public String getEqup_authorize() {
		return equp_authorize;
	}
	public void setEqup_authorize(String equp_authorize) {
		this.equp_authorize = equp_authorize;
	}
	public String getEqup_cove() {
		return equp_cove;
	}
	public void setEqup_cove(String equp_cove) {
		this.equp_cove = equp_cove;
	}
	public String getEqup_remark() {
		return equp_remark;
	}
	public void setEqup_remark(String equp_remark) {
		this.equp_remark = equp_remark;
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
