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
@Table(name = "tb_med_food_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Tb_Med_food_history {
	
		private int id;
		private int eir_id;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date food_date;
		
		private String food_time;
		private String food_consumed;
		private String other_food_consumed;
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
		
		public String getFood_time() {
			return food_time;
		}
		
		public void setFood_time(String food_time) {
			this.food_time = food_time;
		}
		
		public String getFood_consumed() {
			return food_consumed;
		}
		
		public void setFood_consumed(String food_consumed) {
			this.food_consumed = food_consumed;
		}
		
		public String getOther_food_consumed() {
			return other_food_consumed;
		}
		
		public void setOther_food_consumed(String other_food_consumed) {
			this.other_food_consumed = other_food_consumed;
		}
		
		
		public Date getFood_date() {
			return food_date;
		}
		public void setFood_date(Date food_date) {
			this.food_date = food_date;
		}

	
}
