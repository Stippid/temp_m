package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_mstr_district", uniqueConstraints = {
@UniqueConstraint(columnNames = "district_id"),})
public class TB_DISTRICT {
	
	private int district_id;
	
	private String district_name;
	
	
	private Integer state_id;
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
		@Column(name = "district_id", unique = true, nullable = false)
		public int getDistrict_id() {
			return district_id;
		}
		public void setDistrict_id(int district_id) {
			this.district_id = district_id;
		}
		public String getDistrict_name() {
			return district_name;
		}
		public void setDistrict_name(String district_name) {
			this.district_name = district_name;
		}
		public Integer getState_id() {
			return state_id;
		}
		public void setState_id(Integer state_id) {
			this.state_id = state_id;
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