package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_wepe_link_sus_pers_mdfs", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_wepe_link_sus_pers_mdfs {

	
	private int id;
	private String  sus_no;
	private String we_pe_no;
	private String appt_trade_code;
	private String  created_by;
	private String  created_on;
	private String  status;
	private int  mdfs_fk;
	private String modification;
	private Double amt_inc_dec;
	
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
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	
	public String getAppt_trade_code() {
		return appt_trade_code;
	}
	public void setAppt_trade_code(String appt_trade_code) {
		this.appt_trade_code = appt_trade_code;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	
	public Double getAmt_inc_dec() {
		return amt_inc_dec;
	}
	public void setAmt_inc_dec(Double amt_inc_dec) {
		this.amt_inc_dec = amt_inc_dec;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	public int getMdfs_fk() {
		return mdfs_fk;
	}
	public void setMdfs_fk(int mdfs_fk) {
		this.mdfs_fk = mdfs_fk;
	}
	
	
	
	
	
}
