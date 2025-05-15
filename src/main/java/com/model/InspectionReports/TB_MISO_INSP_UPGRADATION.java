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
@Table(name = "tb_miso_insp_upgradation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_UPGRADATION {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String trade;
	private String affected_up_gradation_class_iv;
	private String affected_up_gradation_class_iii;
	private String affected_up_gradation_class_ii;
	private String affected_up_gradation_class_i;
	private String during_up_gradation_class_iv;
	private String during_up_gradation_class_iii;
	private String during_up_gradation_class_ii;
	private String during_up_gradation_class_i;
	private String total_available_class_iv;
	private String total_available_class_iii;
	private String total_available_class_ii;
	private String total_available_class_i;
	private String status;
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
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getAffected_up_gradation_class_iv() {
		return affected_up_gradation_class_iv;
	}
	public void setAffected_up_gradation_class_iv(String affected_up_gradation_class_iv) {
		this.affected_up_gradation_class_iv = affected_up_gradation_class_iv;
	}
	public String getAffected_up_gradation_class_iii() {
		return affected_up_gradation_class_iii;
	}
	public void setAffected_up_gradation_class_iii(String affected_up_gradation_class_iii) {
		this.affected_up_gradation_class_iii = affected_up_gradation_class_iii;
	}
	public String getAffected_up_gradation_class_ii() {
		return affected_up_gradation_class_ii;
	}
	public void setAffected_up_gradation_class_ii(String affected_up_gradation_class_ii) {
		this.affected_up_gradation_class_ii = affected_up_gradation_class_ii;
	}
	public String getAffected_up_gradation_class_i() {
		return affected_up_gradation_class_i;
	}
	public void setAffected_up_gradation_class_i(String affected_up_gradation_class_i) {
		this.affected_up_gradation_class_i = affected_up_gradation_class_i;
	}
	public String getDuring_up_gradation_class_iv() {
		return during_up_gradation_class_iv;
	}
	public void setDuring_up_gradation_class_iv(String during_up_gradation_class_iv) {
		this.during_up_gradation_class_iv = during_up_gradation_class_iv;
	}
	public String getDuring_up_gradation_class_iii() {
		return during_up_gradation_class_iii;
	}
	public void setDuring_up_gradation_class_iii(String during_up_gradation_class_iii) {
		this.during_up_gradation_class_iii = during_up_gradation_class_iii;
	}
	public String getDuring_up_gradation_class_ii() {
		return during_up_gradation_class_ii;
	}
	public void setDuring_up_gradation_class_ii(String during_up_gradation_class_ii) {
		this.during_up_gradation_class_ii = during_up_gradation_class_ii;
	}
	public String getDuring_up_gradation_class_i() {
		return during_up_gradation_class_i;
	}
	public void setDuring_up_gradation_class_i(String during_up_gradation_class_i) {
		this.during_up_gradation_class_i = during_up_gradation_class_i;
	}
	public String getTotal_available_class_iv() {
		return total_available_class_iv;
	}
	public void setTotal_available_class_iv(String total_available_class_iv) {
		this.total_available_class_iv = total_available_class_iv;
	}
	public String getTotal_available_class_iii() {
		return total_available_class_iii;
	}
	public void setTotal_available_class_iii(String total_available_class_iii) {
		this.total_available_class_iii = total_available_class_iii;
	}
	public String getTotal_available_class_ii() {
		return total_available_class_ii;
	}
	public void setTotal_available_class_ii(String total_available_class_ii) {
		this.total_available_class_ii = total_available_class_ii;
	}
	public String getTotal_available_class_i() {
		return total_available_class_i;
	}
	public void setTotal_available_class_i(String total_available_class_i) {
		this.total_available_class_i = total_available_class_i;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
