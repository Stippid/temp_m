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
@Table(name = "cue_tb_MISO_WEPE_Transport_Det", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_WEPE_TRANSPORT_DET {

	
	private int id;
	private String we_pe_no;
	private String entity;
	private String entity_cond;
	private String status;
	private String date_of_apprv_rejc;
	private String aprv_rejc_by;
	private String mct_no;
	private int auth_amt;
	private String footnote_no;
	private String filler_1;
	private String filler_2;
	private String filler_3;
	private BigInteger version_no;
	private String softdelete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String ces_cces;
	private String vettedby_dte1;  
	private String vettedby_dte2;
	private String  remarks;
	
	private String reject_remarks;
	private String reject_letter_no;
	
	private int roleid;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	/*private String reject_date;*/
	
	
  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getEntity_cond() {
		return entity_cond;
	}
	public void setEntity_cond(String entity_cond) {
		this.entity_cond = entity_cond;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_of_apprv_rejc() {
		return date_of_apprv_rejc;
	}
	public void setDate_of_apprv_rejc(String date_of_apprv_rejc) {
		this.date_of_apprv_rejc = date_of_apprv_rejc;
	}
	public String getAprv_rejc_by() {
		return aprv_rejc_by;
	}
	public void setAprv_rejc_by(String aprv_rejc_by) {
		this.aprv_rejc_by = aprv_rejc_by;
	}
	public String getMct_no() {
		return mct_no;
	}
	public void setMct_no(String mct_no) {
		this.mct_no = mct_no;
	}
	public int getAuth_amt() {
		return auth_amt;
	}
	public void setAuth_amt(int auth_amt) {
		this.auth_amt = auth_amt;
	}
	public String getFootnote_no() {
		return footnote_no;
	}
	public void setFootnote_no(String footnote_no) {
		this.footnote_no = footnote_no;
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
	public String getCes_cces() {
		return ces_cces;
	}
	public void setCes_cces(String ces_cces) {
		this.ces_cces = ces_cces;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	/*public String getReject_date() {
		return reject_date;
	}
	public void setReject_date(String reject_date) {
		this.reject_date = reject_date;
	}*/
	
	
	

}
