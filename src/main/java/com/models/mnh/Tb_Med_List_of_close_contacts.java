package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author BISAG
 *
 */
@Entity
@Table(name = "tb_med_list_of_close_contacts", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Tb_Med_List_of_close_contacts {
	
		private int id;
		private int eir_id;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date exposure_date;
		private String remarks;
		private String name_of_close_contact;
		private String created_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date created_on;
	    private String disease;
		private String repo_type;
	   private String modified_by;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date modified_on;
 
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getEir_id() {
			return eir_id;
		}
		public void setEir_id(int eir_id) {
			this.eir_id = eir_id;
		}
		public String getCreated_by() {
			return created_by;
		}
		@Column(length = 35)
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}
		public Date getCreated_on() {
			return created_on;
		}
		public void setCreated_on(Date created_on) {
			this.created_on = created_on;
		}
		@Column(length = 100)
		public String getDisease() {
			return disease;
		}
		public void setDisease(String disease) {
			this.disease = disease;
		}
		@Column(length = 50)
		public String getRepo_type() {
			return repo_type;
		}
		public void setRepo_type(String repo_type) {
			this.repo_type = repo_type;
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
		
		public String getName_of_close_contact() {
			return name_of_close_contact;
		}
		public void setName_of_close_contact(String name_of_close_contact) {
			this.name_of_close_contact = name_of_close_contact;
		}
		public Date getExposure_date() {
			return exposure_date;
		}
		public void setExposure_date(Date exposure_date) {
			this.exposure_date = exposure_date;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
	
}
