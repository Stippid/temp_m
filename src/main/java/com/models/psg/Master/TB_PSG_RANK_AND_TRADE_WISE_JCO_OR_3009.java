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
@Table(name = "tb_psg_rank_and_trade_wise_jco_or_3009")
public class TB_PSG_RANK_AND_TRADE_WISE_JCO_OR_3009 {

	
	private int id;
	private String arms_services;
	private String trade;

	private int sub_maj_jco;
	private int sub_jco;
	private int nb_sub_jco;
	private int warrant_officer_jco;
	private int hav_or;
	private int nk_or;
	private int lnk_sep_or;
	private int rects_or;
	private int total;
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
	public String getArms_services() {
		return arms_services;
	}
	public void setArms_services(String arms_services) {
		this.arms_services = arms_services;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public int getSub_maj_jco() {
		return sub_maj_jco;
	}
	public void setSub_maj_jco(int sub_maj_jco) {
		this.sub_maj_jco = sub_maj_jco;
	}
	public int getSub_jco() {
		return sub_jco;
	}
	public void setSub_jco(int sub_jco) {
		this.sub_jco = sub_jco;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
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
