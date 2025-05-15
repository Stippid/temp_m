package com.models.psg.Jco_Transaction;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_marital_discord_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_MARITAL_DISCORD_JCO {
	
	private int id;
	private String personnel_no;
	private String name_lady;
	private String complaint;
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private Date dt_of_complaint;
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private int status;
	private int jco_id;
	private int cancel_status = -1;
	private Date cancel_date;
	private String cancel_by;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
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
	public Date getDt_of_complaint() {
		return dt_of_complaint;
	}
	public void setDt_of_complaint(Date dt_of_complaint) {
		this.dt_of_complaint = dt_of_complaint;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getJco_id() {
		return jco_id;
	}
	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
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
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	

}
