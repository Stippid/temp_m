package com.models;


import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tb_tms_child_gstospl_convert_req", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_CHILD_GSTOSPL_CONVERT_REQ
 {
	
	
	private int id ;
	private int   convert_req_id ;
	private String  sus_no ;	
	private String  level_in_hierarchy_username ;
	private String   level_in_hierarchy_sus_no ;	
	private int  status ;
	private String role_type;
	private String role_access;
	private String   approved_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   approve_date ;
	private String   creation_by;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   creation_date ;
	private String   modify_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")	
	private Date   modify_date ;	
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConvert_req_id() {
		return convert_req_id;
	}
	public void setConvert_req_id(int convert_req_id) {
		this.convert_req_id = convert_req_id;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getLevel_in_hierarchy_username() {
		return level_in_hierarchy_username;
	}
	public void setLevel_in_hierarchy_username(String level_in_hierarchy_username) {
		this.level_in_hierarchy_username = level_in_hierarchy_username;
	}
	public String getLevel_in_hierarchy_sus_no() {
		return level_in_hierarchy_sus_no;
	}
	public void setLevel_in_hierarchy_sus_no(String level_in_hierarchy_sus_no) {
		this.level_in_hierarchy_sus_no = level_in_hierarchy_sus_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getRole_access() {
		return role_access;
	}
	public void setRole_access(String role_access) {
		this.role_access = role_access;
	}
	
}
