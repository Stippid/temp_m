package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_auth_str_jco_or_3009")
public class TB_PSG_AUTH_STR_JCO_OR_3009 {

	
	private int id;
	private String arm_services;
	private int offrs;
	private int mns_offrs;
	private int jcos;
	private int ors;
	private String approved_by;
	private String approved_date;
	private String month;
	private String year;
	private String version;
	private String sus_no;
	private String remarks;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArm_services() {
		return arm_services;
	}

	public void setArm_services(String arm_services) {
		this.arm_services = arm_services;
	}

	public int getOffrs() {
		return offrs;
	}

	public void setOffrs(int offrs) {
		this.offrs = offrs;
	}

	public int getMns_offrs() {
		return mns_offrs;
	}

	public void setMns_offrs(int mns_offrs) {
		this.mns_offrs = mns_offrs;
	}

	public int getJcos() {
		return jcos;
	}

	public void setJcos(int jcos) {
		this.jcos = jcos;
	}

	public int getOr() {
		return ors;
	}

	public void setOr(int ors) {
		this.ors = ors;
	}



	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getOrs() {
		return ors;
	}
	public void setOrs(int ors) {
		this.ors = ors;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSus_no() {
		return sus_no;
	}


	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}


	
}



