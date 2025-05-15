package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_posted_offrs_jco_or_3009")
public class TB_PSG_POSTED_OFFRS_JCO_OR_3009 {

	
	
	private int id;
	private String arms_services;

	private int brig_and_above_offrs;
	private int col_offrs;
	private int lt_col_offrs;
	private int maj_offrs;
	private int capt_lt_offrs;
	private int brig_and_above_mns_offrs;
	private int col_mns_offrs;
	private int lt_col_mns_offrs;
	private int maj_mns_offrs;
	
	private int capt_lt_mns_offrs;
	private int sub_major_jco;
	private int sub_jco;
	private int nb_sub_jco;
	private int warrant_officer_jco;
	private int hav_or;
	private int nk_or;
	private int lnk_sep_or;
	private int rects_or;
	
	private String month;
	private String year;
	private String version;
	private String sus_no;
	private String remarks;
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
	public String getArms_services() {
		return arms_services;
	}
	public void setArms_services(String arms_services) {
		this.arms_services = arms_services;
	}
	public int getBrig_and_above_offrs() {
		return brig_and_above_offrs;
	}
	public void setBrig_and_above_offrs(int brig_and_above_offrs) {
		this.brig_and_above_offrs = brig_and_above_offrs;
	}
	public int getCol_offrs() {
		return col_offrs;
	}
	public void setCol_offrs(int col_offrs) {
		this.col_offrs = col_offrs;
	}
	public int getLt_col_offrs() {
		return lt_col_offrs;
	}
	public void setLt_col_offrs(int lt_col_offrs) {
		this.lt_col_offrs = lt_col_offrs;
	}
	public int getMaj_offrs() {
		return maj_offrs;
	}
	public void setMaj_offrs(int maj_offrs) {
		this.maj_offrs = maj_offrs;
	}
	public int getCapt_lt_offrs() {
		return capt_lt_offrs;
	}
	public void setCapt_lt_offrs(int capt_lt_offrs) {
		this.capt_lt_offrs = capt_lt_offrs;
	}
	public int getBrig_and_above_mns_offrs() {
		return brig_and_above_mns_offrs;
	}
	public void setBrig_and_above_mns_offrs(int brig_and_above_mns_offrs) {
		this.brig_and_above_mns_offrs = brig_and_above_mns_offrs;
	}
	public int getCol_mns_offrs() {
		return col_mns_offrs;
	}
	public void setCol_mns_offrs(int col_mns_offrs) {
		this.col_mns_offrs = col_mns_offrs;
	}
	public int getLt_col_mns_offrs() {
		return lt_col_mns_offrs;
	}
	public void setLt_col_mns_offrs(int lt_col_mns_offrs) {
		this.lt_col_mns_offrs = lt_col_mns_offrs;
	}
	public int getMaj_mns_offrs() {
		return maj_mns_offrs;
	}
	public void setMaj_mns_offrs(int maj_mns_offrs) {
		this.maj_mns_offrs = maj_mns_offrs;
	}
	public int getCapt_lt_mns_offrs() {
		return capt_lt_mns_offrs;
	}
	public void setCapt_lt_mns_offrs(int capt_lt_mns_offrs) {
		this.capt_lt_mns_offrs = capt_lt_mns_offrs;
	}
	public int getSub_major_jco() {
		return sub_major_jco;
	}
	public void setSub_major_jco(int sub_major_jco) {
		this.sub_major_jco = sub_major_jco;
	}
	public int getSub_jco() {
		return sub_jco;
	}
	public void setSub_jco(int sub_jco) {
		this.sub_jco = sub_jco;
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

	public int getNb_sub_jco() {
		return nb_sub_jco;
	}
	public void setNb_sub_jco(int nb_sub_jco) {
		this.nb_sub_jco = nb_sub_jco;
	}
	public int getWarrant_officer_jco() {
		return warrant_officer_jco;
	}
	public void setWarrant_officer_jco(int warrant_officer_jco) {
		this.warrant_officer_jco = warrant_officer_jco;
	}
	public int getHav_or() {
		return hav_or;
	}
	public void setHav_or(int hav_or) {
		this.hav_or = hav_or;
	}
	public int getNk_or() {
		return nk_or;
	}
	public void setNk_or(int nk_or) {
		this.nk_or = nk_or;
	}
	public int getLnk_sep_or() {
		return lnk_sep_or;
	}
	public void setLnk_sep_or(int lnk_sep_or) {
		this.lnk_sep_or = lnk_sep_or;
	}
	public int getRects_or() {
		return rects_or;
	}
	public void setRects_or(int rects_or) {
		this.rects_or = rects_or;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
