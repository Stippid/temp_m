package com.models.Emoluments_Jco;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_emolument_parent_offcr_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_PSG_EMOLUMENT_PARENT_OFFCR_JCO {
	
	private int id;
	private BigInteger jco_id;
	private int agency_id;
	private int type_of_benefits_id;
	private String amount_due;
	private String amount_rel;
	private String amount_due_v;
	private String amount_rel_v;
	

	
	
	public String getAmount_due_v() {
		return amount_due_v;
	}
	public void setAmount_due_v(String amount_due_v) {
		this.amount_due_v = amount_due_v;
	}
	public String getAmount_rel_v() {
		return amount_rel_v;
	}
	public void setAmount_rel_v(String amount_rel_v) {
		this.amount_rel_v = amount_rel_v;
	}
	private String unit;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;

	private String reason;
	private Date dt_of_casulity;
	private int status;
	private int update_status;
	private String updated_as_on;
	
	private int itteration_status=0;
	private int close_status=-1;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public int getAgency_id() {
		return agency_id;
	}
	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}
	public int getType_of_benefits_id() {
		return type_of_benefits_id;
	}
	public void setType_of_benefits_id(int type_of_benefits_id) {
		this.type_of_benefits_id = type_of_benefits_id;
	}

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getAmount_rel() {
		return amount_rel;
	}
	public void setAmount_rel(String amount_rel) {
		this.amount_rel = amount_rel;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDt_of_casulity() {
		return dt_of_casulity;
	}
	public void setDt_of_casulity(Date dt_of_casulity) {
		this.dt_of_casulity = dt_of_casulity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUpdate_status() {
		return update_status;
	}
	public void setUpdate_status(int update_status) {
		this.update_status = update_status;
	}
	public String getAmount_due() {
		return amount_due;
	}
	public void setAmount_due(String amount_due) {
		this.amount_due = amount_due;
	}
	public String getUpdated_as_on() {
		return updated_as_on;
	}
	public void setUpdated_as_on(String updated_as_on) {
		this.updated_as_on = updated_as_on;
	}
	public int getItteration_status() {
		return itteration_status;
	}
	public void setItteration_status(int itteration_status) {
		this.itteration_status = itteration_status;
	}
	public int getClose_status() {
		return close_status;
	}
	public void setClose_status(int close_status) {
		this.close_status = close_status;
	}
	public BigInteger getJco_id() {
		return jco_id;
	}
	public void setJco_id(BigInteger jco_id) {
		this.jco_id = jco_id;
	}
 
	
}
