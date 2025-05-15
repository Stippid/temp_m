package com.models.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_audit_upload", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Audit_Upload {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Date created_date;
	private String created_by;
	private String type_of_audit;
	private String audit_done_by;
	private Date audit_date;
	private String u_file;

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
	public String getType_of_audit() {
		return type_of_audit;
	}
	public void setType_of_audit(String type_of_audit) {
		this.type_of_audit = type_of_audit;
	}
	public String getAudit_done_by() {
		return audit_done_by;
	}
	public void setAudit_done_by(String audit_done_by) {
		this.audit_done_by = audit_done_by;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public String getU_file() {
		return u_file;
	}
	public void setU_file(String u_file) {
		this.u_file = u_file;
	}

}
