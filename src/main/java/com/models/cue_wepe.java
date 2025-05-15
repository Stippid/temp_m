package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_wepe_upload_condition", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
	 })

public class cue_wepe {

	
	private int id;	
	private String ammend_status;
	private String approved_rejected_by;	
	private int auto_no_wet;
	private String code;
	 
	private String created_by;
	private String created_on;
	private String date_of_apprv_rejc;
	private String we_pe;
	private String doc_path;
	private Integer training_capacity;
	private String unit_category;
	
	private String remarks;
	
	private String wet_link_status;
	
	private int roleid;
	
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
	
	public String getTable_title() {
		return table_title;
	}
	public void setTable_title(String table_title) {
		this.table_title = table_title;
	}
	private String table_title;
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	public String getWe_pe() {
		return we_pe;
	}
	public void setWe_pe(String we_pe) {
		this.we_pe = we_pe;
	}
	private String date_of_sanctn;
/*	@NotNull*/
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private String eff_frm_date;
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private String eff_to_date;
	
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String modified_by;
	private String modified_on;
	private String pers_comp_status;
	private String softdelete;
	private String status;
	
	private String suprcdd_we_pe_no;
	
	 
	private String trans_comp_status;
	private String version_no;
	private String we_pe_no;
	private String weap_comp_status;
	private String versiowet_link_statusn_no;
	private String wet_pet_no;
	private String sponsor_dire;
	private String doc_type;
	private String arm;
	private String uploaded_wepe;
    private String unit_visible;
   
    private String reject_remarks;
	private String reject_letter_no;
	
	
	public String getUnit_visible() {
		return unit_visible;
	}
	public void setUnit_visible(String unit_visible) {
		this.unit_visible = unit_visible;
	}
	
	
	
	
	
	
	
	
	
	public String getUploaded_wepe() {
		return uploaded_wepe;
	}
	public void setUploaded_wepe(String uploaded_wepe) {
		this.uploaded_wepe = uploaded_wepe;
	}
	public Integer getTraining_capacity() {
		return training_capacity;
	}
	public void setTraining_capacity(Integer training_capacity) {
		this.training_capacity = training_capacity;
	}
	public String getUnit_category() {
		return unit_category;
	}
	public void setUnit_category(String unit_category) {
		this.unit_category = unit_category;
	}
	
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getSponsor_dire() {
		return sponsor_dire;
	}
	public void setSponsor_dire(String sponsor_dire) {
		this.sponsor_dire = sponsor_dire;
	}
	public String getArm() {
		return arm;
	}
	public void setArm(String arm) {
		this.arm = arm;
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
	public String getAmmend_status() {
		return ammend_status;
	}
	public void setAmmend_status(String ammend_status) {
		this.ammend_status = ammend_status;
	}
	public String getApproved_rejected_by() {
		return approved_rejected_by;
	}
	public void setApproved_rejected_by(String approved_rejected_by) {
		this.approved_rejected_by = approved_rejected_by;
	}
	public int getAuto_no_wet() {
		return auto_no_wet;
	}
	public void setAuto_no_wet(int auto_no_wet) {
		this.auto_no_wet = auto_no_wet;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getDate_of_apprv_rejc() {
		return date_of_apprv_rejc;
	}
	public void setDate_of_apprv_rejc(String date_of_apprv_rejc) {
		this.date_of_apprv_rejc = date_of_apprv_rejc;
	}	
	public String getDate_of_sanctn() {
		return date_of_sanctn;
	}
	public void setDate_of_sanctn(String date_of_sanctn) {
		this.date_of_sanctn = date_of_sanctn;
	}
	
	/*public String getEff_to_date() {
		return eff_to_date;
	}
	public void setEff_to_date(String eff_to_date) {
		this.eff_to_date = eff_to_date;
	}*/
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
	public String getPers_comp_status() {
		return pers_comp_status;
	}
	public void setPers_comp_status(String pers_comp_status) {
		this.pers_comp_status = pers_comp_status;
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
	public String getSuprcdd_we_pe_no() {
		return suprcdd_we_pe_no;
	}
	public void setSuprcdd_we_pe_no(String suprcdd_we_pe_no) {
		this.suprcdd_we_pe_no = suprcdd_we_pe_no;
	}
	 
	public String getTrans_comp_status() {
		return trans_comp_status;
	}
	public void setTrans_comp_status(String trans_comp_status) {
		this.trans_comp_status = trans_comp_status;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public String getWeap_comp_status() {
		return weap_comp_status;
	}
	public void setWeap_comp_status(String weap_comp_status) {
		this.weap_comp_status = weap_comp_status;
	}
	public String getVersiowet_link_statusn_no() {
		return versiowet_link_statusn_no;
	}
	public void setVersiowet_link_statusn_no(String versiowet_link_statusn_no) {
		this.versiowet_link_statusn_no = versiowet_link_statusn_no;
	}
	public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}
	 
	public String getEff_to_date() {
		return eff_to_date;
	}
	public void setEff_to_date(String eff_to_date) {
		this.eff_to_date = eff_to_date;
	}
	public String getEff_frm_date() {
		return eff_frm_date;
	}
	public void setEff_frm_date(String eff_frm_date) {
		this.eff_frm_date = eff_frm_date;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getWet_link_status() {
		return wet_link_status;
	}
	public void setWet_link_status(String wet_link_status) {
		this.wet_link_status = wet_link_status;
	}
	

	
	
	
	
	
}
