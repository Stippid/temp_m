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


@Entity
@Table(name = "tb_med_history_stay", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Tb_Med_History_Stay {
	
		private int id;
		private int eir_id;
		@DateTimeFormat(pattern = "dd-mm-dd")
		private Date history_date;
	    @DateTimeFormat(pattern = "HH:mm")
		private String history_time;
		private String history_loc;
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
		
		 public Date getHistory_date() {
			return history_date;
		}
		public void setHistory_date(Date history_date) {
			this.history_date = history_date;
		}
		public String getHistory_time() {
			return history_time;
		}
		public void setHistory_time(String history_time) {
			this.history_time = history_time;
		}

		
			public String getHistory_loc() {
				return history_loc;
			}
			public void setHistory_loc(String history_loc) {
				this.history_loc = history_loc;
			}
}
