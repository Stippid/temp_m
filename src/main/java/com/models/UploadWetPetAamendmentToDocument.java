package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_wet_pet_amdt", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
	 })

public class UploadWetPetAamendmentToDocument {
	
	
	private int id;
	private String wet_pet_no;
	private String  doc_path;
	private String  amdt_title_wet_pet;
	private String  date_upload;
	private String  vetted_miso;
	private String  doc_uploaded_by;
	private String  remark;
	private String  status;
	
	private String reject_remarks;
	private String reject_letter_no;
	 private int roleid;
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
	  
		private String letter_no;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	public String getAmdt_title_wet_pet() {
		return amdt_title_wet_pet;
	}
	public void setAmdt_title_wet_pet(String amdt_title_wet_pet) {
		this.amdt_title_wet_pet = amdt_title_wet_pet;
	}
	public String getDate_upload() {
		return date_upload;
	}
	public void setDate_upload(String date_upload) {
		this.date_upload = date_upload;
	}
	public String getVetted_miso() {
		return vetted_miso;
	}
	public void setVetted_miso(String vetted_miso) {
		this.vetted_miso = vetted_miso;
	}
	public String getDoc_uploaded_by() {
		return doc_uploaded_by;
	}
	public void setDoc_uploaded_by(String doc_uploaded_by) {
		this.doc_uploaded_by = doc_uploaded_by;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getReject_letter_no() {
		return reject_letter_no;
	}
	public void setReject_letter_no(String reject_letter_no) {
		this.reject_letter_no = reject_letter_no;
	}
	
	
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}


	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}


	public String getLetter_no() {
		return letter_no;
	}
	public void setLetter_no(String letter_no) {
		this.letter_no = letter_no;
	}

	private String created_by;
	private String created_on;
	
	
	
	 
}
