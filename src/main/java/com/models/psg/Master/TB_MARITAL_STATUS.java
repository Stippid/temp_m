package com.models.psg.Master;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_mstr_marital_status", uniqueConstraints = {
@UniqueConstraint(columnNames = "marital_id"),})
public class TB_MARITAL_STATUS {

      private int marital_id;     
	  private String marital_code;     
	  private String marital_name;
	  private String created_by;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date created_date;
		private String modify_by;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date modify_date;
		private String status;
		
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "marital_id", unique = true, nullable = false)
		
		public int getMarital_id() {
			return marital_id;
		}
		public void setMarital_id(int marital_id) {
			this.marital_id = marital_id;
		}
		public String getMarital_code() {
			return marital_code;
		}
		public void setMarital_code(String marital_code) {
			this.marital_code = marital_code;
		}
		public String getMarital_name() {
			return marital_name;
		}
		public void setMarital_name(String marital_name) {
			this.marital_name = marital_name;
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
