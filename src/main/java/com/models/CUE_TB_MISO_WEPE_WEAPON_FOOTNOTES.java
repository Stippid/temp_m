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
@Table(name = "cue_tb_miso_wepe_weapon_footnotes", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES {
	
	private int id;
	private String  we_pe_no;
	private String  entity;
	private String  entity_cond;
	private String  status;
	private String  date_of_apprv_rejc;
	private String  aprv_rejc_by;
	private String  footnote_no;
	private String  type_of_footnote;
	private double  amt_inc_dec;
	private String  condition;
	private BigInteger  version_no;
	private String  softdelete;
	private String  created_by;
	private String  created_on;
	private String  modified_by;
	private String  modified_on;
	private String  remarks;
	private String  item_seq_no;
	private BigInteger  in_lieu_seq_no;
	private String location;
	private String formation;
	private String scenario;
	private String  scenario_unit;
	private int roleid;
	private String vettedby_dte1;
	private String vettedby_dte2;
	
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
	public String getFootnote_no() {
		return footnote_no;
	}
	public void setFootnote_no(String footnote_no) {
		this.footnote_no = footnote_no;
	}
	public String getType_of_footnote() {
		return type_of_footnote;
	}
	public void setType_of_footnote(String type_of_footnote) {
		this.type_of_footnote = type_of_footnote;
	}
	public double getAmt_inc_dec() {
		return amt_inc_dec;
	}
	public void setAmt_inc_dec(double amt_inc_dec) {
		this.amt_inc_dec = amt_inc_dec;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public BigInteger getIn_lieu_seq_no() {
		return in_lieu_seq_no;
	}
	public void setIn_lieu_seq_no(BigInteger in_lieu_seq_no) {
		this.in_lieu_seq_no = in_lieu_seq_no;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getScenario_unit() {
		return scenario_unit;
	}
	public void setScenario_unit(String scenario_unit) {
		this.scenario_unit = scenario_unit;
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

	
}
