package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_iut_track_status_upload_voucher", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER {

	
	private int id;
	private String ba_no;
	private String upload_voucher1;
	private String upload_voucher2;
	private String target_sus_no;

	
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

	public String getUpload_voucher1() {
		return upload_voucher1;
	}
	public void setUpload_voucher1(String upload_voucher1) {
		this.upload_voucher1 = upload_voucher1;
	}	
	public String getUpload_voucher2() {
		return upload_voucher2;
	}
	public void setUpload_voucher2(String upload_voucher2) {
		this.upload_voucher2 = upload_voucher2;
	}	
	public String getTarget_sus_no() {
		return target_sus_no;
	}

	public void setTarget_sus_no(String target_sus_no) {
		this.target_sus_no = target_sus_no;
}
}
