package com.models.fpmis;

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
@Table(name = "fp.fp_tb_head_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class FP_MIS_Master_Model {
	
	private int id;
	private String tr_head;
	private String tr_level;
	private String major_head;
	private String minor_head;
	private String sub_head;
	private String sub_head1;
	private String sub_head2;
	private String head_desc;
	private String status;
	
	private Date data_cr_date;
	private String data_cr_by;
	private Date data_upd_date;
	private String data_upd_by;

	private String head_code;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTr_head() {
		return tr_head;
	}
	public void setTr_head(String tr_head) {
		this.tr_head = tr_head;
	}
	public String getTr_level() {
		return tr_level;
	}
	public void setTr_level(String tr_level) {
		this.tr_level = tr_level;
	}
	public String getMajor_head() {
		return major_head;
	}
	public void setMajor_head(String major_head) {
		this.major_head = major_head;
	}
	public String getMinor_head() {
		return minor_head;
	}
	public void setMinor_head(String minor_head) {
		this.minor_head = minor_head;
	}
	public String getSub_head() {
		return sub_head;
	}
	public void setSub_head(String sub_head) {
		this.sub_head = sub_head;
	}
	public String getSub_head1() {
		return sub_head1;
	}
	public void setSub_head1(String sub_head1) {
		this.sub_head1 = sub_head1;
	}
	public String getSub_head2() {
		return sub_head2;
	}
	public void setSub_head2(String sub_head2) {
		this.sub_head2 = sub_head2;
	}
	public String getHead_desc() {
		return head_desc;
	}
	public void setHead_desc(String head_desc) {
		this.head_desc = head_desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}
	public String getData_cr_by() {
		return data_cr_by;
	}
	public void setData_cr_by(String data_cr_by) {
		this.data_cr_by = data_cr_by;
	}
	public Date getData_upd_date() {
		return data_upd_date;
	}
	public void setData_upd_date(Date data_upd_date) {
		this.data_upd_date = data_upd_date;
	}
	public String getData_upd_by() {
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}
	public String getHead_code() {
		return head_code;
	}
	public void setHead_code(String head_code) {
		this.head_code = head_code;
	}
}