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

@Table(name = "tb_psg_census_ncc_exp", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_CENSUS_NCC_EXP {



	

	private int id;

	private int cen_id;

	private BigInteger comm_id;

	private String otu;

	private int type_of_entry;

	private String created_by;

	private Date created_on;

	private String modify_by;

	private Date modify_on;

	private int status;

	

	@Id

	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public int getCen_id() {

		return cen_id;

	}

	public void setCen_id(int cen_id) {

		this.cen_id = cen_id;

	}

	public String getCreated_by() {

		return created_by;

	}

	public void setCreated_by(String created_by) {

		this.created_by = created_by;

	}

	public Date getCreated_on() {

		return created_on;

	}

	public void setCreated_on(Date created_on) {

		this.created_on = created_on;

	}

	

	public Date getModify_on() {

		return modify_on;

	}

	public void setModify_on(Date modify_on) {

		this.modify_on = modify_on;

	}

	public String getModify_by() {

		return modify_by;

	}

	public void setModify_by(String modify_by) {

		this.modify_by = modify_by;

	}

	public int getType_of_entry() {

		return type_of_entry;

	}

	public void setType_of_entry(int type_of_entry) {

		this.type_of_entry = type_of_entry;

	}

	public String getOtu() {

		return otu;

	}

	public void setOtu(String otu) {

		this.otu = otu;

	}

	

	public int getStatus() {

		return status;

	}

	public void setStatus(int status) {

		this.status = status;

	}

	public BigInteger getComm_id() {
		return comm_id;
	}

	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}

}

