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
@Table(name = "tb_psg_religious_denomination_3009")
public class TB_PSG_RELIGIOUS_DENOMINATION_3009 {

	private int id;
	private String religion;
	private String arms_services;
	private int jcos_posted_str_incl_ere_pers;
	private int or_posted_str_incl_ere_pers;
	private int auth_religious_teacher;
	private int held_religious_teacher;
	private String month;
	private String year;
	private String version;
	private String sus_no;
	private String approved_by;
	private String approved_date;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getArms_services() {
		return arms_services;
	}
	public void setArms_services(String arms_services) {
		this.arms_services = arms_services;
	}
	public int getJcos_posted_str_incl_ere_pers() {
		return jcos_posted_str_incl_ere_pers;
	}
	public void setJcos_posted_str_incl_ere_pers(int jcos_posted_str_incl_ere_pers) {
		this.jcos_posted_str_incl_ere_pers = jcos_posted_str_incl_ere_pers;
	}
	public int getOr_posted_str_incl_ere_pers() {
		return or_posted_str_incl_ere_pers;
	}
	public void setOr_posted_str_incl_ere_pers(int or_posted_str_incl_ere_pers) {
		this.or_posted_str_incl_ere_pers = or_posted_str_incl_ere_pers;
	}
	public int getAuth_religious_teacher() {
		return auth_religious_teacher;
	}
	public void setAuth_religious_teacher(int auth_religious_teacher) {
		this.auth_religious_teacher = auth_religious_teacher;
	}
	public int getHeld_religious_teacher() {
		return held_religious_teacher;
	}
	public void setHeld_religious_teacher(int held_religious_teacher) {
		this.held_religious_teacher = held_religious_teacher;
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

	
}
