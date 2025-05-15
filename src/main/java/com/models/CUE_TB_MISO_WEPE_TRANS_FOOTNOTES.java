package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_wepe_trans_footnotes", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_WEPE_TRANS_FOOTNOTES {

	
	  private int id ;
	  private int actual_inlieu_auth; 
	  private String aprv_rejc_by;
	  private String condition;
	  private String created_by; 
	  private String created_on; 
	  private String date_of_apprv_rejc; 
	  private String entity; 
	  private String entity_cond; 
	  private String footnote_no; 
	  private String footnote_other; 
	  private String modified_by; 
	  private String modified_on; 
	  private String softdelete ;
	  private String status; 
	  private String type_of_footnote; 
	  private String vettedby_dte1; 
	  private String vettedby_dte2; 
	  private String we_pe_no;
	 /* private String inc_dec; 
	  private String std_nomclature; 
	  private String table_title; */
	  private String version_no; 
	  private String in_lieu_mct; 
	  private String mct_no; 
	  private int amt_inc_dec;
	  private String remarks; 
	  private String formation; 
	  private String location;
	  private String Scenario;
	  private String  scenario_unit;
	  
	  private String reject_remarks;
	  private String reject_letter_no;
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
	public int getActual_inlieu_auth() {
		return actual_inlieu_auth;
	}
	public void setActual_inlieu_auth(int actual_inlieu_auth) {
		this.actual_inlieu_auth = actual_inlieu_auth;
	}
	public String getAprv_rejc_by() {
		return aprv_rejc_by;
	}
	public void setAprv_rejc_by(String aprv_rejc_by) {
		this.aprv_rejc_by = aprv_rejc_by;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public String getFootnote_no() {
		return footnote_no;
	}
	public void setFootnote_no(String footnote_no) {
		this.footnote_no = footnote_no;
	}
	public String getFootnote_other() {
		return footnote_other;
	}
	public void setFootnote_other(String footnote_other) {
		this.footnote_other = footnote_other;
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
	public String getType_of_footnote() {
		return type_of_footnote;
	}
	public void setType_of_footnote(String type_of_footnote) {
		this.type_of_footnote = type_of_footnote;
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
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	/*public String getInc_dec() {
		return inc_dec;
	}
	public void setInc_dec(String inc_dec) {
		this.inc_dec = inc_dec;
	}
	public String getStd_nomclature() {
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature) {
		this.std_nomclature = std_nomclature;
	}
	public String getTable_title() {
		return table_title;
	}
	public void setTable_title(String table_title) {
		this.table_title = table_title;
	}*/
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getIn_lieu_mct() {
		return in_lieu_mct;
	}
	public void setIn_lieu_mct(String in_lieu_mct) {
		this.in_lieu_mct = in_lieu_mct;
	}
	public String getMct_no() {
		return mct_no;
	}
	public void setMct_no(String mct_no) {
		this.mct_no = mct_no;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAmt_inc_dec() {
		return amt_inc_dec;
	}
	public void setAmt_inc_dec(int amt_inc_dec) {
		this.amt_inc_dec = amt_inc_dec;
	}
	public String getScenario() {
		return Scenario;
	}
	public void setScenario(String scenario) {
		Scenario = scenario;
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
	public String getScenario_unit() {
		return scenario_unit;
	}
	public void setScenario_unit(String scenario_unit) {
		this.scenario_unit = scenario_unit;
	} 
	
	  
	  
}
