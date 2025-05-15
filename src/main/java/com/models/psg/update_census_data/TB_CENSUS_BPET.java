package com.models.psg.update_census_data;

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
@Table(name = "tb_psg_census_bpet", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_BPET {
	
	private int id;
	private int census_id;
	private BigInteger comm_id;
	private String BPET_result;
	private String BPET_qe;
	private int year;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	private String unit_sus_no;
	private String bept_result_others;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String reject_remarks;
	
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	
	
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getBPET_result() {
		return BPET_result;
	}
	public void setBPET_result(String bPET_result) {
		BPET_result = bPET_result;
	}
	public String getBPET_qe() {
		return BPET_qe;
	}
	public void setBPET_qe(String bPET_qe) {
		BPET_qe = bPET_qe;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getBept_result_others() {
		return bept_result_others;
	}
	public void setBept_result_others(String bept_result_others) {
		this.bept_result_others = bept_result_others;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
}
