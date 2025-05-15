package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_mms_wet_mast_upload", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
		 })
public class cue_wet_pet {
	
	
	  private int id;
	  private String  approved_rejected_by;
	  private String  approved_rejected_on;
	  private String  created_by;
	  private String  created_on;
	  private String  equip_table_id;
	  private String  modified_by;
	  private String  modified_on;
	  private String  softdelete;
	  private String  status ;
	  private String  unit_type ;
	  private String  valid_from ;
	  private String  valid_till ;
	  private double version_no ;
	  private String  vettedby_dte1 ;
	  private String  vettedby_dte2 ;
	  private String  wet_pet ;
	  private String  wet_pet_no ;
	  private String  wet_pet_status ;
	  private String  doc_type;
	  private String  arm;
	  private String  sponsor_dire;
	  private String  doc_path;
	  private String  superseeded_wet_pet;
	  private String  remarks;
	  private int roleid;
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
	  
	  

		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
	
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getApproved_rejected_by() {
			return approved_rejected_by;
		}
		public void setApproved_rejected_by(String approved_rejected_by) {
			this.approved_rejected_by = approved_rejected_by;
		}
		public String getApproved_rejected_on() {
			return approved_rejected_on;
		}
		public void setApproved_rejected_on(String approved_rejected_on) {
			this.approved_rejected_on = approved_rejected_on;
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
		public String getEquip_table_id() {
			return equip_table_id;
		}
		public void setEquip_table_id(String equip_table_id) {
			this.equip_table_id = equip_table_id;
		}
		public String getModified_by() {
			return modified_by;
		}
		public void setModified_by(String modified_by) {
			this.modified_by = modified_by;
		}
		public String getModified_on() {
			return modified_on;
		}
		public void setModified_on(String modified_on) {
			this.modified_on = modified_on;
		}
		public String getSoftdelete() {
			return softdelete;
		}
		public void setSoftdelete(String softdelete) {
			this.softdelete = softdelete;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getUnit_type() {
			return unit_type;
		}
		public void setUnit_type(String unit_type) {
			this.unit_type = unit_type;
		}
		public String getValid_from() {
			return valid_from;
		}
		public void setValid_from(String valid_from) {
			this.valid_from = valid_from;
		}
		public String getValid_till() {
			return valid_till;
		}
		public void setValid_till(String valid_till) {
			this.valid_till = valid_till;
		}
		public double getVersion_no() {
			return version_no;
		}
		public void setVersion_no(double version_no) {
			this.version_no = version_no;
		}
		public String getVettedby_dte1() {
			return vettedby_dte1;
		}
		public void setVettedby_dte1(String vettedby_dte1) {
			this.vettedby_dte1 = vettedby_dte1;
		}
		public String getVettedby_dte2() {
			return vettedby_dte2;
		}
		public void setVettedby_dte2(String vettedby_dte2) {
			this.vettedby_dte2 = vettedby_dte2;
		}
		public String getWet_pet() {
			return wet_pet;
		}
		public void setWet_pet(String wet_pet) {
			this.wet_pet = wet_pet;
		}
		public String getWet_pet_no() {
			return wet_pet_no;
		}
		public void setWet_pet_no(String wet_pet_no) {
			this.wet_pet_no = wet_pet_no;
		}
		public String getWet_pet_status() {
			return wet_pet_status;
		}
		public void setWet_pet_status(String wet_pet_status) {
			this.wet_pet_status = wet_pet_status;
		}
		public String getDoc_type() {
			return doc_type;
		}
		public void setDoc_type(String doc_type) {
			this.doc_type = doc_type;
		}
		public String getArm() {
			return arm;
		}
		public void setArm(String arm) {
			this.arm = arm;
		}
		public String getSponsor_dire() {
			return sponsor_dire;
		}
		public void setSponsor_dire(String sponsor_dire) {
			this.sponsor_dire = sponsor_dire;
		}
		public String getDoc_path() {
			return doc_path;
		}
		public void setDoc_path(String doc_path) {
			this.doc_path = doc_path;
		}
		public String getSuperseeded_wet_pet() {
			return superseeded_wet_pet;
		}
		public void setSuperseeded_wet_pet(String superseeded_wet_pet) {
			this.superseeded_wet_pet = superseeded_wet_pet;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		


}
