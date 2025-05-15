package com.models.psg.Civilian_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "tb_psg_mstr_cause_of_non_effective_civilian", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_CIVILIAN {
	private int id;
	private String causes_name;
	private int type_of_civilian;
	private String type_of_regular_or_nonregular;
	private String status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCauses_name() {
		return causes_name;
	}
	public void setCauses_name(String causes_name) {
		this.causes_name = causes_name;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getType_of_civilian() {
		return type_of_civilian;
	}
	public void setType_of_civilian(int type_of_civilian) {
		this.type_of_civilian = type_of_civilian;
	}
	public String getType_of_regular_or_nonregular() {
		return type_of_regular_or_nonregular;
	}
	public void setType_of_regular_or_nonregular(String type_of_regular_or_nonregular) {
		this.type_of_regular_or_nonregular = type_of_regular_or_nonregular;
	}
	
	
	
}
