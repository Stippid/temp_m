package com.models.psg.Transaction;

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
@Table(name = "tb_psg_maritial_discord", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_MARITIAL_DISCORD {

	private int id;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	private int service_category;
	private String personnel_no;
	private String name_lady;
	private String complaint;
	private Date dt_of_comp;
	private int close_status;
	
	private int status;
	private BigInteger comm_id;
	private int census_id;
	private int cancel_status = -1;
	private Date cancel_date;
	private String cancel_by;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getService_category() {
		return service_category;
	}
	public void setService_category(int service_category) {
		this.service_category = service_category;
	}
	public String getPersonnel_no() {
		return personnel_no;
	}
	public void setPersonnel_no(String personnel_no) {
		this.personnel_no = personnel_no;
	}
	
	public String getName_lady() {
		return name_lady;
	}
	public void setName_lady(String name_lady) {
		this.name_lady = name_lady;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public Date getDt_of_comp() {
		return dt_of_comp;
	}
	public void setDt_of_comp(Date dt_of_comp) {
		this.dt_of_comp = dt_of_comp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public int getClose_status() {
		return close_status;
	}
	public void setClose_status(int close_status) {
		this.close_status = close_status;
	}
	
}