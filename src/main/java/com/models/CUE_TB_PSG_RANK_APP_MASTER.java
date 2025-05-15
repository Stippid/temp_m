package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_psg_rank_app_master", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_PSG_RANK_APP_MASTER {
	
	private int id;
	private String  level_in_hierarchy;
	private String  description;
	private String  code;
	private String  parent_code;
	private String  creation_by;
	private String  creation_date;
	private String  approve_by;
	private String  approve_date;
	private String  modify_by;
	private String  modify_date;
	private String  filler_1;
	private String  filler_2;
	private String  filler_3;
	private BigInteger  version_no;
	private String  softdelete;
	private String  comm_rare;
	private String  status;
	private String  status_active;
	private String  remarks;
	private int  roleid;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevel_in_hierarchy() {
		return level_in_hierarchy;
	}
	public void setLevel_in_hierarchy(String level_in_hierarchy) {
		this.level_in_hierarchy = level_in_hierarchy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public String getFiller_1() {
		return filler_1;
	}
	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}
	public String getFiller_2() {
		return filler_2;
	}
	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}
	public String getFiller_3() {
		return filler_3;
	}
	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}
	public BigInteger getVersion_no() {
		return version_no;
	}
	public void setVersion_no(BigInteger version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getComm_rare() {
		return comm_rare;
	}
	public void setComm_rare(String comm_rare) {
		this.comm_rare = comm_rare;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_active() {
		return status_active;
	}
	public void setStatus_active(String status_active) {
		this.status_active = status_active;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	


}
