package com.models.psg.Jco_Update_JcoData;

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
@Table(name = "tb_psg_census_change_in_pay_group_jco", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_CHANGE_IN_PAY_GROUP_JCO {

	private int id;
	private int jco_id;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String gp_authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date gp_date_authority;
	private int gp_group;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_group;
	private String initiated_from;
	private int status;
	  private int cancel_status= -1;
	private String cancel_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancel_date;
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

	public int getJco_id() {
		return jco_id;
	}

	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
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

	public String getGp_authority() {
		return gp_authority;
	}

	public void setGp_authority(String gp_authority) {
		this.gp_authority = gp_authority;
	}

	public Date getGp_date_authority() {
		return gp_date_authority;
	}

	public void setGp_date_authority(Date gp_date_authority) {
		this.gp_date_authority = gp_date_authority;
	}


	public Date getDate_of_group() {
		return date_of_group;
	}

	public void setDate_of_group(Date date_of_group) {
		this.date_of_group = date_of_group;
	}

	public String getInitiated_from() {
		return initiated_from;
	}

	public void setInitiated_from(String initiated_from) {
		this.initiated_from = initiated_from;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public int getGp_group() {
		return gp_group;
	}

	public void setGp_group(int gp_group) {
		this.gp_group = gp_group;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
