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
@Table(name = "tb_miso_insp_training_ammunition", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_TRAINING_AMMUNITION {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String type_of_ammunition;
	private String au;
	private String qty_auth_full_trainning;
	private String recive_inclu_carried_forward;
	private String expended;	
	private String balance;
	private String reason;
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
	public String getType_of_ammunition() {
		return type_of_ammunition;
	}
	public void setType_of_ammunition(String type_of_ammunition) {
		this.type_of_ammunition = type_of_ammunition;
	}
	public String getAu() {
		return au;
	}
	public void setAu(String au) {
		this.au = au;
	}
	public String getQty_auth_full_trainning() {
		return qty_auth_full_trainning;
	}
	public void setQty_auth_full_trainning(String qty_auth_full_trainning) {
		this.qty_auth_full_trainning = qty_auth_full_trainning;
	}
	public String getRecive_inclu_carried_forward() {
		return recive_inclu_carried_forward;
	}
	public void setRecive_inclu_carried_forward(String recive_inclu_carried_forward) {
		this.recive_inclu_carried_forward = recive_inclu_carried_forward;
	}
	public String getExpended() {
		return expended;
	}
	public void setExpended(String expended) {
		this.expended = expended;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
