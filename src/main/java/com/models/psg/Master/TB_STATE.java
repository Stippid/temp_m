package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "tb_psg_mstr_state", uniqueConstraints = {
@UniqueConstraint(columnNames = "state_id"),})


public class TB_STATE {
	
	  private int state_id;
	  @NotBlank(message="Please Enter State Name")
	  private String state_name;	 
       private Integer country_id;
       private String created_by;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date created_date;
		private String modify_by;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date modify_date;
		private String status;
		
       @Id
   	@GeneratedValue(strategy = IDENTITY)
   	@Column(name = "state_id", unique = true, nullable = false) 	  
	  public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Integer getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
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
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	  
    
   
}