package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;




@Entity

@Table(name = "tb_tms_cin", uniqueConstraints = {@UniqueConstraint(columnNames = "cin_id") })
public class TB_TMS_CIN {
	
	private int cin_id;
	private String ba_no;
	private String type_of_veh;
	private int kms;
	private int vintage;
	private Date date;
	private String created_by;
	private String base_workshop;
	private String status;
	private String oh_status;
	private Date oh_date;
	private Date dt_arrival;
	private String remark;
	private String qr_status;
	private String sus_no;
	private String mode;
	private String py;
	private String type_of_intervention;
	
	
	public String getType_of_intervention() {
		return type_of_intervention;
	}
	public void setType_of_intervention(String type_of_intervention) {
		this.type_of_intervention = type_of_intervention;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	@Id

	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "cin_id", unique = true, nullable = false)

	
	public int getCin_id() {
		return cin_id;
	}
	public void setCin_id(int cin_id) {
		this.cin_id = cin_id;
	}
	
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public String getType_of_veh() {
		return type_of_veh;
	}
	public void setType_of_veh(String type_of_veh) {
		this.type_of_veh = type_of_veh;
	}
	public int getKms() {
		return kms;
	}
	public void setKms(int kms) {
		this.kms = kms;
	}
	public int getVintage() {
		return vintage;
	}
	public void setVintage(int vintage) {
		this.vintage = vintage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getBase_workshop() {
		return base_workshop;
	}
	public void setBase_workshop(String base_workshop) {
		this.base_workshop = base_workshop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOh_status() {
		return oh_status;
	}
	public void setOh_status(String oh_status) {
		this.oh_status = oh_status;
	}
	public Date getOh_date() {
		return oh_date;
	}
	public void setOh_date(Date oh_date) {
		this.oh_date = oh_date;
	}
	public Date getDt_arrival() {
		return dt_arrival;
	}
	public void setDt_arrival(Date dt_arrival) {
		this.dt_arrival = dt_arrival;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQr_status() {
		return qr_status;
	}
	public void setQr_status(String qr_status) {
		this.qr_status = qr_status;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	
	
	
	
	
	
	

}
