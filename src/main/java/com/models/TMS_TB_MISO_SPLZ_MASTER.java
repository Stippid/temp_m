package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_specialization_master", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TMS_TB_MISO_SPLZ_MASTER {
      
	
	private int id;
	private String type_splztn;
	 private String anml_type;
	 private String created_by;
   private String modify_by;
		   private Date created_date;
		   private Date modify_date;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_splztn() {
		return type_splztn;
	}
	public void setType_splztn(String type_splztn) {
		this.type_splztn = type_splztn;
	}
	public String getAnml_type() {
		return anml_type;
	}
	public void setAnml_type(String anml_type) {
		this.anml_type = anml_type;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	
	
}
