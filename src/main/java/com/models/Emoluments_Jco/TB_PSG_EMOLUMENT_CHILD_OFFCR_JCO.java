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
@Table(name = "tb_psg_emolument_child_offcr_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_PSG_EMOLUMENT_CHILD_OFFCR_JCO {
	
	private int id;
	private int p_id;
	private BigInteger jco_id;
	private String amount_release;
	private String amount_rel_v;
	private Date updated_as_on;
	private String reason;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private int status=0;
	private String amount_rem="0";
	 
	

	public String getAmount_rem() {
		return amount_rem;
	}
	public void setAmount_rem(String amount_rem) {
		this.amount_rem = amount_rem;
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
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
	
	
	public Date getUpdated_as_on() {
		return updated_as_on;
	}
	public void setUpdated_as_on(Date updated_as_on) {
		this.updated_as_on = updated_as_on;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAmount_release() {
		return amount_release;
	}
	public void setAmount_release(String amount_release) {
		this.amount_release = amount_release;
	}
	public String getAmount_rel_v() {
		return amount_rel_v;
	}
	public void setAmount_rel_v(String amount_rel_v) {
		this.amount_rel_v = amount_rel_v;
	}
	public BigInteger getJco_id() {
		return jco_id;
	}
	public void setJco_id(BigInteger jco_id) {
		this.jco_id = jco_id;
	}

}
