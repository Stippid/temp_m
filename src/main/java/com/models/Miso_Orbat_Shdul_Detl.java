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
@Table(name = "tb_miso_orbat_shdul_detl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class Miso_Orbat_Shdul_Detl {
	
	  	
		private int id;
	  	private String type_of_letter;
	  	private String letter_no;
	  	private String status;
	  	private String created_by;
	  	private Date created_on;
	  	private String modified_by;
	  	private Date modified_on;
	  	private String approved_rejected_by;
	  	private Date approved_rejected_on;
	  	private String goi_letter_no;
	  	private Date date_goi_letter;
	  	private String filter_1;
	  	private String filter_2;
	  	private String filter_3;
	  	private int version_no;
	  	private String soft_delete;
	  	private Date sanction_date;
	  	private String nature_move;
	  	private String remarks;
	  	private int letter_id;
	  	private String upload_goi_letter;
	  	private String upload_auth_letter;
	  	private String level_a;
		private String level_b;
	  
	  
	  
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
	  	public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public String getType_of_letter() {
			return type_of_letter;
		}
		public void setType_of_letter(String type_of_letter) {
			this.type_of_letter = type_of_letter;
		}
		
		public String getLetter_no() {
			return letter_no;
		}
		public void setLetter_no(String letter_no) {
			this.letter_no = letter_no;
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
		
		public String getApproved_rejected_by() {
			return approved_rejected_by;
		}
		public void setApproved_rejected_by(String approved_rejected_by) {
			this.approved_rejected_by = approved_rejected_by;
		}
		
		public Date getApproved_rejected_on() {
			return approved_rejected_on;
		}
		public void setApproved_rejected_on(Date approved_rejected_on) {
			this.approved_rejected_on = approved_rejected_on;
		}
		
		public String getGoi_letter_no() {
			return goi_letter_no;
		}
		public void setGoi_letter_no(String goi_letter_no) {
			this.goi_letter_no = goi_letter_no;
		}
		
		public Date getDate_goi_letter() {
			return date_goi_letter;
		}
		public void setDate_goi_letter(Date date_goi_letter) {
			this.date_goi_letter = date_goi_letter;
		}
		
		public String getFilter_1() {
			return filter_1;
		}
		public void setFilter_1(String filter_1) {
			this.filter_1 = filter_1;
		}
		
		public String getFilter_2() {
			return filter_2;
		}
		public void setFilter_2(String filter_2) {
			this.filter_2 = filter_2;
		}
		
		public String getFilter_3() {
			return filter_3;
		}
		public void setFilter_3(String filter_3) {
			this.filter_3 = filter_3;
		}
		
		public int getVersion_no() {
			return version_no;
		}
		public void setVersion_no(int version_no) {
			this.version_no = version_no;
		}
		
		public String getSoft_delete() {
			return soft_delete;
		}
		public void setSoft_delete(String soft_delete) {
			this.soft_delete = soft_delete;
		}
		
		public Date getSanction_date() {
			return sanction_date;
		}
		public void setSanction_date(Date sanction_date) {
			this.sanction_date = sanction_date;
		}
		
		public String getNature_move() {
			return nature_move;
		}
		public void setNature_move(String nature_move) {
			this.nature_move = nature_move;
		}
		
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
		public int getLetter_id() {
			return letter_id;
		}
		public void setLetter_id(int letter_id) {
			this.letter_id = letter_id;
		}
		
		public String getUpload_goi_letter() {
			return upload_goi_letter;
		}
		public void setUpload_goi_letter(String upload_goi_letter) {
			this.upload_goi_letter = upload_goi_letter;
		}
		public String getUpload_auth_letter() {
			return upload_auth_letter;
		}
		public void setUpload_auth_letter(String upload_auth_letter) {
			this.upload_auth_letter = upload_auth_letter;
		}
		public String getLevel_a() {
			return level_a;
		}
		public void setLevel_a(String level_a) {
			this.level_a = level_a;
		}
		public String getLevel_b() {
			return level_b;
		}
		public void setLevel_b(String level_b) {
			this.level_b = level_b;
		}
}
