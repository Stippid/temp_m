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
@Table(name = "cue_tb_miso_wepe_weapon_det", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_WEPE_WEAPON_DET {

	private int id;
	private String we_pe_no;
	private String entity;
	private String entity_cond;
	private String status;
	private String apprv_rejc_on;
	private String aprv_rejc_by;
	private double auth_weapon;
	private BigInteger amm_scl_on_man;
	private BigInteger amm_scl_for_reserve;
	private String remarks;
	private String footnote_no;
	private BigInteger version_no;
	private String softdelete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String item_seq_no;
	private String mct;
	private String ces_cces;
	private int roleid;
	private String vettedby_dte1;
	private String vettedby_dte2;
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
	public String getApprv_rejc_on() {
		return apprv_rejc_on;
	}
	public void setApprv_rejc_on(String apprv_rejc_on) {
		this.apprv_rejc_on = apprv_rejc_on;
	}
	public String getAprv_rejc_by() {
		return aprv_rejc_by;
	}
	public void setAprv_rejc_by(String aprv_rejc_by) {
		this.aprv_rejc_by = aprv_rejc_by;
	}
	public double getAuth_weapon() {
		return auth_weapon;
	}
	public void setAuth_weapon(double auth_weapon) {
		this.auth_weapon = auth_weapon;
	}
	public BigInteger getAmm_scl_on_man() {
		return amm_scl_on_man;
	}
	public void setAmm_scl_on_man(BigInteger amm_scl_on_man) {
		this.amm_scl_on_man = amm_scl_on_man;
	}
	public BigInteger getAmm_scl_for_reserve() {
		return amm_scl_for_reserve;
	}
	public void setAmm_scl_for_reserve(BigInteger amm_scl_for_reserve) {
		this.amm_scl_for_reserve = amm_scl_for_reserve;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFootnote_no() {
		return footnote_no;
	}
	public void setFootnote_no(String footnote_no) {
		this.footnote_no = footnote_no;
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
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public String getMct() {
		return mct;
	}
	public void setMct(String mct) {
		this.mct = mct;
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

	
}
