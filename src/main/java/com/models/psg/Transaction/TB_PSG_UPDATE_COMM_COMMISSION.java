package com.models.psg.Transaction;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "tb_psg_update_comm_commission", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_PSG_UPDATE_COMM_COMMISSION {
	private int id;
	private BigInteger comm_id;
	private int status;
	private String authority;
	private Date date_of_authority;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	private int type_of_comm_granted;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_commission;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getType_of_comm_granted() {
		return type_of_comm_granted;
	}
	public void setType_of_comm_granted(int type_of_comm_granted) {
		this.type_of_comm_granted = type_of_comm_granted;
	}
	public Date getDate_of_commission() {
		return date_of_commission;
	}
	public void setDate_of_commission(Date date_of_commission) {
		this.date_of_commission = date_of_commission;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
}
