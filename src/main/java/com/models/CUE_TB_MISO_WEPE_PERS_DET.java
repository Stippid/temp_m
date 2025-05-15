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
@Table(name = "cue_tb_miso_wepe_pers_det", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_WEPE_PERS_DET {
	
	private int id;
	private String we_pe_no;
	private String entity;
	private String entity_cond;
	private String status;
	private String apprv_rejc_on;
	private String aprv_rejc_by;
	private String category_of_persn;
	private String rank_cat;
	private String appt_trade;
	private double auth_amt;
	private String footnote_no;
	private BigInteger version_no;
	private String softdelete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String arm_code;
	private String code_type;
	private String app_trd_code;
	private String rank;
	private String remarks;
	private String vettedby_dte1;
	private String vettedby_dte2;
	private int roleid;
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
	/*private String reject_remarks;
	private String reject_letter_no;*/
	
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
	public String getCategory_of_persn() {
		return category_of_persn;
	}
	public void setCategory_of_persn(String category_of_persn) {
		this.category_of_persn = category_of_persn;
	}
	public String getRank_cat() {
		return rank_cat;
	}
	public void setRank_cat(String rank_cat) {
		this.rank_cat = rank_cat;
	}
	public String getAppt_trade() {
		return appt_trade;
	}
	public void setAppt_trade(String appt_trade) {
		this.appt_trade = appt_trade;
	}
	public double getAuth_amt() {
		return auth_amt;
	}
	public void setAuth_amt(double auth_amt) {
		this.auth_amt = auth_amt;
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
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public String getApp_trd_code() {
		return app_trd_code;
	}
	public void setApp_trd_code(String app_trd_code) {
		this.app_trd_code = app_trd_code;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	/*public String getReject_remarks() {
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
	}*/
	
	


}
