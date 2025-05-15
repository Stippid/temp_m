package com.models.psg.Jco_Transaction;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_miso_role_hdr_status_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_MISO_ROLE_HDR_STATUS_JCO {
	
	private int id;
	private int jco_id;
	private int re_call_status;
	private int marital_dis_status;
	private int field_ser_status;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJco_id() {
		return jco_id;
	}
	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
	}
	public int getRe_call_status() {
		return re_call_status;
	}
	public void setRe_call_status(int re_call_status) {
		this.re_call_status = re_call_status;
	}
	public int getMarital_dis_status() {
		return marital_dis_status;
	}
	public void setMarital_dis_status(int marital_dis_status) {
		this.marital_dis_status = marital_dis_status;
	}
	public int getField_ser_status() {
		return field_ser_status;
	}
	public void setField_ser_status(int field_ser_status) {
		this.field_ser_status = field_ser_status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

}
