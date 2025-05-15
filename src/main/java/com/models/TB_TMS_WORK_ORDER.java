package com.models;

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
@Table(name = "tb_tms_work_order", uniqueConstraints = {@UniqueConstraint(columnNames = "wo_no")})
public class TB_TMS_WORK_ORDER {
	
	
	private int wo_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date wo_date;
	private String unit_name;
	private String sus_no;
	private String wksp;
	private String station;
	private String ba_no;
	private String type_of_veh;
	private String nature_of_work;
	private String remark;
	private String status;
	private String mct;
	private String nomenclature;
	private String unit_station;
	private String created_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_dt;
	private String modify_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modify_dt;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wo_no", unique = true, nullable = false)
	
	public int getWo_no() {
		return wo_no;
	}
	public void setWo_no(int wo_no) {
		this.wo_no = wo_no;
	}
	public Date getWo_date() {
		return wo_date;
	}
	public void setWo_date(Date wo_date) {
		this.wo_date = wo_date;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getWksp() {
		return wksp;
	}
	public void setWksp(String wksp) {
		this.wksp = wksp;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
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
	public String getNature_of_work() {
		return nature_of_work;
	}
	public void setNature_of_work(String nature_of_work) {
		this.nature_of_work = nature_of_work;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_dt() {
		return modify_dt;
	}
	public void setModify_dt(Date modify_dt) {
		this.modify_dt = modify_dt;
	}
	public String getMct() {
		return mct;
	}
	public void setMct(String mct) {
		this.mct = mct;
	}
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public String getUnit_station() {
		return unit_station;
	}
	public void setUnit_station(String unit_station) {
		this.unit_station = unit_station;
	}

}
