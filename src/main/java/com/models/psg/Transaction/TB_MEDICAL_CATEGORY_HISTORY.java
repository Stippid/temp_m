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

@Entity
@Table(name = "tb_psg_medical_categoryHistory", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_MEDICAL_CATEGORY_HISTORY {
	 private int id;
	 private String shape;
	 private String cope;
	 private BigInteger comm_id;
	 private Date date_of_authority;
	 private int status;
	 private String med_classification_lmc;
	 
   @Id
   @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
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
	public void setStatus(int satus) {
		this.status = satus;
	}
	public String getCope() {
		return cope;
	}
	public void setCope(String cope) {
		this.cope = cope;
	}
	 
	public String getMed_classification_lmc() {
		return med_classification_lmc;
	}
	public void setMed_classification_lmc(String med_classification_lmc) {
		this.med_classification_lmc = med_classification_lmc;
	}
}
