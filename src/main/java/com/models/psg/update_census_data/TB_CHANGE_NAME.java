package com.models.psg.update_census_data;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

 
	@Entity
	@Table(name = "tb_psg_change_name", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
	public class TB_CHANGE_NAME {

		private int id;
		private int census_id;
		private BigInteger comm_id;
		private String authority;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date date_of_authority;
		private String name;
		private int status;
		private String created_by;
		private Date created_date;
		private String modified_by;
		private Date modified_date;
		private int cancel_status=-1;
		private String cancel_by;
		private Date cancel_date;
		private String reject_remarks;
		private Date change_in_name_date;
		
		public int getCancel_status() {
			return cancel_status;
		}
		public void setCancel_status(int cancel_status) {
			this.cancel_status = cancel_status;
		}
		public String getCancel_by() {
			return cancel_by;
		}
		public void setCancel_by(String cancel_by) {
			this.cancel_by = cancel_by;
		}
		public Date getCancel_date() {
			return cancel_date;
		}
		public void setCancel_date(Date cancel_date) {
			this.cancel_date = cancel_date;
		}
				
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCensus_id() {
			return census_id;
		}
		public void setCensus_id(int census_id) {
			this.census_id = census_id;
		}
		
		public BigInteger getComm_id() {
			return comm_id;
		}
		public void setComm_id(BigInteger comm_id) {
			this.comm_id = comm_id;
		}
		public String getAuthority() {
			return authority;
		}
		public void setAuthority(String authority) {
			this.authority = authority;
		}
		public Date getDate_of_authority() {
			return date_of_authority;
		}
		public void setDate_of_authority(Date date_of_authority) {
			this.date_of_authority = date_of_authority;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
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
		public String getReject_remarks() {
			return reject_remarks;
		}
		public void setReject_remarks(String reject_remarks) {
			this.reject_remarks = reject_remarks;
		}
		public Date getChange_in_name_date() {
			return change_in_name_date;
		}
		public void setChange_in_name_date(Date change_in_name_date) {
			this.change_in_name_date = change_in_name_date;
		}
	
	
	
	
	
	
}
