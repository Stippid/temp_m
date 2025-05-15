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
@Table(name = "tb_psg_census_foreign_country", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_FOREIGN_COUNTRY {

	private int id;
	private int cen_id;
	private int country;
	private String other_country;
	private String period;
	private Date from_dt;
	private Date to_dt;
	private int purpose_visit;
	private String created_by;
	private Date created_on;
	private Date modified_date;
	private String modified_by;
	private int status;
	private BigInteger comm_id;
	private String other_purpose_visit;
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

	public int getCen_id() {
		return cen_id;
	}

	public void setCen_id(int cen_id) {
		this.cen_id = cen_id;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Date getFrom_dt() {
		return from_dt;
	}

	public void setFrom_dt(Date from_dt) {
		this.from_dt = from_dt;
	}

	public Date getTo_dt() {
		return to_dt;
	}

	public void setTo_dt(Date to_dt) {
		this.to_dt = to_dt;
	}



	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
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
	public int getPurpose_visit() {
		return purpose_visit;
	}

	public void setPurpose_visit(int purpose_visit) {
		this.purpose_visit = purpose_visit;
	}

	public String getOther_country() {
		return other_country;
	}

	public void setOther_country(String other_country) {
		this.other_country = other_country;
	}

	public String getOther_purpose_visit() {
		return other_purpose_visit;
	}

	public void setOther_purpose_visit(String other_purpose_visit) {
		this.other_purpose_visit = other_purpose_visit;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}

}