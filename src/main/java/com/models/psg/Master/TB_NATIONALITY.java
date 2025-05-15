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
@Table(name = "tb_psg_mstr_nationality", uniqueConstraints = {
@UniqueConstraint(columnNames = "nationality_id"),})

public class TB_NATIONALITY {

	  private int nationality_id;
	  @NotBlank(message="Please Enter Nationality Name")
	  private String nationality_name;
	  
	  /*@NotNull(message="Please Enter Country Name")
	  @Min(value=0,message="Please Enter Country Name")*/
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
		@Column(name = "nationality_id", unique = true, nullable = false)		
		  
		public int getNationality_id() {
			return nationality_id;
		}
		public void setNationality_id(int nationality_id) {
			this.nationality_id = nationality_id;
		}
		public String getNationality_name() {
			return nationality_name;
		}
		public void setNationality_name(String nationality_name) {
			this.nationality_name = nationality_name;
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
