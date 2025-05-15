package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mms_tb_ro_generate_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class MMS_TB_RO_GENERATE_DTL {
	
	
	
	private String depots_sus_no;
	private String unit_sus_no;	
	private String census_no;
	private double ro_issue_qnty;
	private String au;
	private Integer issue_qnty;
	private String item_code;
	private Integer item_ue;
	private Integer item_uh;
	private double yet_to_collect;
	private int status;
	private String ro_no;
	private String created_by;
	private String rejected_by;
	private String eqpt_regn_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date = new Date();	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date rejected_date;

	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepots_sus_no() {
		return depots_sus_no;
	}

	public void setDepots_sus_no(String depots_sus_no) {
		this.depots_sus_no = depots_sus_no;
	}

	public String getUnit_sus_no() {
		return unit_sus_no;
	}

	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}

	public String getCensus_no() {
		return census_no;
	}

	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}

	public Integer getIssue_qnty() {
		return issue_qnty;
	}

	public void setIssue_qnty(Integer issue_qnty) {
		this.issue_qnty = issue_qnty;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public Integer getItem_ue() {
		return item_ue;
	}

	public void setItem_ue(Integer item_ue) {
		this.item_ue = item_ue;
	}

	public Integer getItem_uh() {
		return item_uh;
	}

	public void setItem_uh(Integer item_uh) {
		this.item_uh = item_uh;
	}

	public double getYet_to_collect() {
		return yet_to_collect;
	}

	public void setYet_to_collect(Double yet_to_collect) {
		this.yet_to_collect = yet_to_collect;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getRejected_by() {
		return rejected_by;
	}

	public void setRejected_by(String rejected_by) {
		this.rejected_by = rejected_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getRejected_date() {
		return rejected_date;
	}

	public void setRejected_date(Date rejected_date) {
		this.rejected_date = rejected_date;
	}

	public double getRo_issue_qnty() {
		return ro_issue_qnty;
	}

	public void setRo_issue_qnty(Double ro_issue_qnty) {
		this.ro_issue_qnty = ro_issue_qnty;
	}

	public String getAu() {
		return au;
	}

	public void setAu(String au) {
		this.au = au;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}

	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}


	

	
}
