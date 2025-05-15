package com.models.psg.Master;

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
@Table(name = "tb_psg_mstr_bank", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_BANK {

	private int id;
	private String bank_name;
	private String bank_abb;
	private String ifsc;
	private String bank_address;
	private BigInteger bank_phone;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date; 
	private String status;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_abb() {
		return bank_abb;
	}
	public void setBank_abb(String bank_abb) {
		this.bank_abb = bank_abb;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBank_address() {
		return bank_address;
	}
	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}
	public BigInteger getBank_phone() {
		return bank_phone;
	}
	public void setBank_phone(BigInteger bank_phone) {
		this.bank_phone = bank_phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
