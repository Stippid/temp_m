package com.model.InspectionReports;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_insp_training_ammunition", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_TRAINING_AMMUNITION {

	
	private int id;
	private String type_ammunition;
	private String a_u;
	private String quantity_authorised_training_Year;
	private String Received_carried_Forward;
	private String expended;
	private String balance;
	private String non_expenditure;
	
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
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

	public String getType_ammunition() {
		return type_ammunition;
	}

	public void setType_ammunition(String type_ammunition) {
		this.type_ammunition = type_ammunition;
	}

	public String getExpended() {
		return expended;
	}

	public void setExpended(String expended) {
		this.expended = expended;
	}

	public String getA_u() {
		return a_u;
	}

	public void setA_u(String a_u) {
		this.a_u = a_u;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getQuantity_authorised_training_Year() {
		return quantity_authorised_training_Year;
	}

	public void setQuantity_authorised_training_Year(String quantity_authorised_training_Year) {
		this.quantity_authorised_training_Year = quantity_authorised_training_Year;
	}

	public String getReceived_carried_Forward() {
		return Received_carried_Forward;
	}

	public void setReceived_carried_Forward(String received_carried_Forward) {
		Received_carried_Forward = received_carried_Forward;
	}

	public String getNon_expenditure() {
		return non_expenditure;
	}

	public void setNon_expenditure(String non_expenditure) {
		this.non_expenditure = non_expenditure;
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


	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
