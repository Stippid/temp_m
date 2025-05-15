package com.models.HTO;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_orbat_oc_voucher" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_ORBAT_OC_VOUCHER {

	private int id;
	private String ba_no;
	private String sus_from;
	private String sus_to;
	private String reject_by;
	private Date reject_date_unit;
	private Date reject_date_fmn;
	private String reject_remarks_unit;
	private String reject_remarks_fmn;
	private String doc_unit;
	private String doc_fmn;
	private String imdt_higher_fmn;

	private String status;
	private String created_by;
	private Date created_date;
	private String approve_by;
	private Date approve_date;

	private String oc;
	private String voucher_status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public String getSus_from() {
		return sus_from;
	}
	public void setSus_from(String sus_from) {
		this.sus_from = sus_from;
	}
	public String getSus_to() {
		return sus_to;
	}
	public void setSus_to(String sus_to) {
		this.sus_to = sus_to;
	}
	public String getReject_by() {
		return reject_by;
	}
	public void setReject_by(String reject_by) {
		this.reject_by = reject_by;
	}
	public Date getReject_date_unit() {
		return reject_date_unit;
	}
	public void setReject_date_unit(Date reject_date_unit) {
		this.reject_date_unit = reject_date_unit;
	}
	public Date getReject_date_fmn() {
		return reject_date_fmn;
	}
	public void setReject_date_fmn(Date reject_date_fmn) {
		this.reject_date_fmn = reject_date_fmn;
	}
	public String getReject_remarks_unit() {
		return reject_remarks_unit;
	}
	public void setReject_remarks_unit(String reject_remarks_unit) {
		this.reject_remarks_unit = reject_remarks_unit;
	}
	public String getReject_remarks_fmn() {
		return reject_remarks_fmn;
	}
	public void setReject_remarks_fmn(String reject_remarks_fmn) {
		this.reject_remarks_fmn = reject_remarks_fmn;
	}
	public String getDoc_unit() {
		return doc_unit;
	}
	public void setDoc_unit(String doc_unit) {
		this.doc_unit = doc_unit;
	}
	public String getDoc_fmn() {
		return doc_fmn;
	}
	public void setDoc_fmn(String doc_fmn) {
		this.doc_fmn = doc_fmn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getOc() {
		return oc;
	}
	public void setOc(String oc) {
		this.oc = oc;
	}
	public String getVoucher_status() {
		return voucher_status;
	}
	public void setVoucher_status(String voucher_status) {
		this.voucher_status = voucher_status;
	}
	public String getImdt_higher_fmn() {
		return imdt_higher_fmn;
	}
	public void setImdt_higher_fmn(String imdt_higher_fmn) {
		this.imdt_higher_fmn = imdt_higher_fmn;
	}



}
