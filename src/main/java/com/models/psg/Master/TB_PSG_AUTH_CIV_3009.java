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
@Table(name = "tb_psg_auth_civ_3009")
public class TB_PSG_AUTH_CIV_3009 {
	private int id;
	private int gp_a_gazetted;
	private int gp_b_gazetted;
	private int gp_b_non_gazetted;
	private int gp_c_non_gazetted;
	
	private String month;
	private String year;
	private String version;
	private String sus_no;
	private String remarks;
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
	public int getGp_a_gazetted() {
		return gp_a_gazetted;
	}
	public void setGp_a_gazetted(int gp_a_gazetted) {
		this.gp_a_gazetted = gp_a_gazetted;
	}
	public int getGp_b_gazetted() {
		return gp_b_gazetted;
	}
	public void setGp_b_gazetted(int gp_b_gazetted) {
		this.gp_b_gazetted = gp_b_gazetted;
	}
	public int getGp_b_non_gazetted() {
		return gp_b_non_gazetted;
	}
	public void setGp_b_non_gazetted(int gp_b_non_gazetted) {
		this.gp_b_non_gazetted = gp_b_non_gazetted;
	}
	public int getGp_c_non_gazetted() {
		return gp_c_non_gazetted;
	}
	public void setGp_c_non_gazetted(int gp_c_non_gazetted) {
		this.gp_c_non_gazetted = gp_c_non_gazetted;
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
	private String approved_date;
	
	
	
	

}
