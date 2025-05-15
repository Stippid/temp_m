package com.models.psg.Jco_Update_Census;

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
@Table(name = "tb_psg_update_census_family_details_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") }) 

public class TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO {
	
	private int id;
	private String father_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date father_dob;
	private String father_place_birth;
	private String father_service;
	private String father_other;
	private String father_personal_no;
	private int father_profession;
	private String mother_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mother_dob;
	private String mother_place_birth;
	private String mother_service;
	private String mother_other;
	private String mother_personal_no;
	private int mother_profession;
	private int jco_id;
	private int status;
	private String authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_authority;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String father_proffother;
	private String mother_proffother;
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
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public Date getFather_dob() {
		return father_dob;
	}
	public void setFather_dob(Date father_dob) {
		this.father_dob = father_dob;
	}
	public String getFather_place_birth() {
		return father_place_birth;
	}
	public void setFather_place_birth(String father_place_birth) {
		this.father_place_birth = father_place_birth;
	}
	public String getFather_service() {
		return father_service;
	}
	public void setFather_service(String father_service) {
		this.father_service = father_service;
	}
	public String getFather_other() {
		return father_other;
	}
	public void setFather_other(String father_other) {
		this.father_other = father_other;
	}
	public String getFather_personal_no() {
		return father_personal_no;
	}
	public void setFather_personal_no(String father_personal_no) {
		this.father_personal_no = father_personal_no;
	}
	public int getFather_profession() {
		return father_profession;
	}
	public void setFather_profession(int father_profession) {
		this.father_profession = father_profession;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public Date getMother_dob() {
		return mother_dob;
	}
	public void setMother_dob(Date mother_dob) {
		this.mother_dob = mother_dob;
	}
	public String getMother_place_birth() {
		return mother_place_birth;
	}
	public void setMother_place_birth(String mother_place_birth) {
		this.mother_place_birth = mother_place_birth;
	}
	public String getMother_service() {
		return mother_service;
	}
	public void setMother_service(String mother_service) {
		this.mother_service = mother_service;
	}
	public String getMother_other() {
		return mother_other;
	}
	public void setMother_other(String mother_other) {
		this.mother_other = mother_other;
	}
	public String getMother_personal_no() {
		return mother_personal_no;
	}
	public void setMother_personal_no(String mother_personal_no) {
		this.mother_personal_no = mother_personal_no;
	}
	public int getMother_profession() {
		return mother_profession;
	}
	public void setMother_profession(int mother_profession) {
		this.mother_profession = mother_profession;
	}
	public int getJco_id() {
		return jco_id;
	}
	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getFather_proffother() {
		return father_proffother;
	}
	public void setFather_proffother(String father_proffother) {
		this.father_proffother = father_proffother;
	}
	public String getMother_proffother() {
		return mother_proffother;
	}
	public void setMother_proffother(String mother_proffother) {
		this.mother_proffother = mother_proffother;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}


}
