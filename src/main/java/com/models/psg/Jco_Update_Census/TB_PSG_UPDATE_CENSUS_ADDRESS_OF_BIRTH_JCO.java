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
@Table(name = "tb_psg_update_census_address_of_birth_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_UPDATE_CENSUS_ADDRESS_OF_BIRTH_JCO {

	private int id;
	private String place_of_birth;
	private int country_of_birth;
	private int state_of_birth;
	private int district_of_birth;
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
	
	private String country_other;
	private String state_other;
	private String district_other;
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
	public String getPlace_of_birth() {
		return place_of_birth;
	}
	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}
	public int getCountry_of_birth() {
		return country_of_birth;
	}
	public void setCountry_of_birth(int country_of_birth) {
		this.country_of_birth = country_of_birth;
	}
	public int getState_of_birth() {
		return state_of_birth;
	}
	public void setState_of_birth(int state_of_birth) {
		this.state_of_birth = state_of_birth;
	}
	public int getDistrict_of_birth() {
		return district_of_birth;
	}
	public void setDistrict_of_birth(int district_of_birth) {
		this.district_of_birth = district_of_birth;
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
	public String getCountry_other() {
		return country_other;
	}
	public void setCountry_other(String country_other) {
		this.country_other = country_other;
	}
	public String getState_other() {
		return state_other;
	}
	public void setState_other(String state_other) {
		this.state_other = state_other;
	}
	public String getDistrict_other() {
		return district_other;
	}
	public void setDistrict_other(String district_other) {
		this.district_other = district_other;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	
	
}
