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
@Table(name = "mms_tb_tempregn_rowise_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_TEMPREGN_ROWISE_DTL {
  
	  private int id;	  
	  private String ro_no; 
	  private String unit_sus_no;
	  private String depot_sus_no;
	  private String census_no; 
	  private String prf_code;
	  private String item_code; 
	  private String created_by;
	  private String modify_by;
	  private String regn_no;
	  private String regn_seq_no;
	  private int status;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date created_date; 

	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date modify_date;
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date ro_date; 
	
	  
	    @Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRo_no() {
		return ro_no;
	}
	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getDepot_sus_no() {
		return depot_sus_no;
	}
	public void setDepot_sus_no(String depot_sus_no) {
		this.depot_sus_no = depot_sus_no;
	}
	public String getCensus_no() {
		return census_no;
	}
	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public String getRegn_no() {
		return regn_no;
	}
	public void setRegn_no(String regn_no) {
		this.regn_no = regn_no;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Date getRo_date() {
		return ro_date;
	}
	public void setRo_date(Date ro_date) {
		this.ro_date = ro_date;
	}
	public String getRegn_seq_no() {
		return regn_seq_no;
	}
	public void setRegn_seq_no(String regn_seq_no) {
		this.regn_seq_no = regn_seq_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	  
	  
}
