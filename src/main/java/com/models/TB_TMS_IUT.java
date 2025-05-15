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
@Table(name = "tb_tms_iut", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_IUT {

	
	private int id;
	private String iut_authority_no;
	private String source_sus_no;
	private String target_sus_no;
	private int main_id;
	private String ba_no;
	private int status;
	private String vehical_type;
	private Date created_on;
	private String created_by;
	private String upload_voucher;
	private int userid;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSource_sus_no() {
		return source_sus_no;
	}
	public void setSource_sus_no(String source_sus_no) {
		this.source_sus_no = source_sus_no;
	}
	public String getTarget_sus_no() {
		return target_sus_no;
	}
	public void setTarget_sus_no(String target_sus_no) {
		this.target_sus_no = target_sus_no;
	}
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
	}
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getVehical_type() {
		return vehical_type;
	}
	public void setVehical_type(String vehical_type) {
		this.vehical_type = vehical_type;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getIut_authority_no() {
		return iut_authority_no;
	}
	public void setIut_authority_no(String iut_authority_no) {
		this.iut_authority_no = iut_authority_no;
	}
	public String getUpload_voucher() {
		return upload_voucher;
	}
	public void setUpload_voucher(String upload_voucher) {
		this.upload_voucher = upload_voucher;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	

	
	
	
}
