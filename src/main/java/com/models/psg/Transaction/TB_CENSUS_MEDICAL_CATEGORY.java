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
@Table(name = "tb_psg_medical_category", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_MEDICAL_CATEGORY {

	private int id;
	private int cen_id;
	private BigInteger comm_id;
	private String shape;
	private int shape_status;
	private String shape_value;
	private Date from_date;
	private Date to_date;
	private String diagnosis;
	private String shape_sub_value;
	private String other;

	private String clasification;
	private String diagnosis_1bx;
	private Date from_date_1bx;
	private Date to_date_1bx;
	private String authority;
	private Date date_of_authority;

	private String created_by;
	private Date created_on;
	private String modify_by;
	private Date modify_on;
	private int status;

	private int cancel_status = -1;
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

	public Date getModify_on() {
		return modify_on;
	}

	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
	}

	public String getModify_by() {
		return modify_by;
	}

	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}

	public int getCen_id() {
		return cen_id;
	}

	public void setCen_id(int cen_id) {
		this.cen_id = cen_id;
	}

	

	public BigInteger getComm_id() {
		return comm_id;
	}

	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getShape_status() {
		return shape_status;
	}

	public void setShape_status(int shape_status) {
		this.shape_status = shape_status;
	}

	public String getShape_value() {
		return shape_value;
	}

	public void setShape_value(String shape_value) {
		this.shape_value = shape_value;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getDate_of_authority() {
		return date_of_authority;
	}

	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getClasification() {
		return clasification;
	}

	public void setClasification(String clasification) {
		this.clasification = clasification;
	}

	public String getShape_sub_value() {
		return shape_sub_value;
	}

	public void setShape_sub_value(String shape_sub_value) {
		this.shape_sub_value = shape_sub_value;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDiagnosis_1bx() {
		return diagnosis_1bx;
	}

	public void setDiagnosis_1bx(String diagnosis_1bx) {
		this.diagnosis_1bx = diagnosis_1bx;
	}

	public Date getFrom_date_1bx() {
		return from_date_1bx;
	}

	public void setFrom_date_1bx(Date from_date_1bx) {
		this.from_date_1bx = from_date_1bx;
	}

	public Date getTo_date_1bx() {
		return to_date_1bx;
	}

	public void setTo_date_1bx(Date to_date_1bx) {
		this.to_date_1bx = to_date_1bx;
	}

	public String getReject_remarks() {
		return reject_remarks;
	}

	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}

}
