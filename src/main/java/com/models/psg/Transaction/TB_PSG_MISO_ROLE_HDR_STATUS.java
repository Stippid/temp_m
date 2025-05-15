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
@Table(name = "tb_psg_miso_role_hdr_status", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_MISO_ROLE_HDR_STATUS {
	
	private int id;
	private BigInteger comm_id;
	private int re_emp_status;
	private int marital_dis_status;
	private int field_ser_status;
	private int seniority_status;
	private int cda_status;
	
	private int army_course_status;
	/*private int language_status;
	private int foreign_language_status;*/
	
	
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRe_emp_status() {
		return re_emp_status;
	}
	public void setRe_emp_status(int re_emp_status) {
		this.re_emp_status = re_emp_status;
	}
	
	public int getMarital_dis_status() {
		return marital_dis_status;
	}
	public void setMarital_dis_status(int marital_dis_status) {
		this.marital_dis_status = marital_dis_status;
	}
	public int getField_ser_status() {
		return field_ser_status;
	}
	public void setField_ser_status(int field_ser_status) {
		this.field_ser_status = field_ser_status;
	}
	public int getSeniority_status() {
		return seniority_status;
	}
	public void setSeniority_status(int seniority_status) {
		this.seniority_status = seniority_status;
	}
	public int getCda_status() {
		return cda_status;
	}
	public void setCda_status(int cda_status) {
		this.cda_status = cda_status;
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
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getArmy_course_status() {
		return army_course_status;
	}
	public void setArmy_course_status(int army_course_status) {
		this.army_course_status = army_course_status;
	}
	/*public int getLanguage_status() {
		return language_status;
	}
	public void setLanguage_status(int language_status) {
		this.language_status = language_status;
	}
	public int getForeign_language_status() {
		return foreign_language_status;
	}
	public void setForeign_language_status(int foreign_language_status) {
		this.foreign_language_status = foreign_language_status;
	}*/
	
	
}
