package com.models.psg.Jco_Update_JcoData;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_inter_arm_transfer_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_INTER_ARM_TRANSFER_JCO {

      private int id;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private String record_office_sus;
      private String record_office_unit;
      private String parent_arm_service;
      private String regt;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date with_effect_from;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String modified_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
      private int status;
      private int jco_id;
      private String initiated_from;
      private int cancel_status= -1;
      private String cancel_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date cancel_date;
      private String reject_remarks;

  


      @Id
      @GeneratedValue(strategy = IDENTITY)
      @Column(name = "id", unique = true, nullable = false)


      public int getId() {
           return id;
      }
      public void setId(int id) {
	  this.id = id;
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
     
      public String getRecord_office_sus() {
		return record_office_sus;
	}
	public void setRecord_office_sus(String record_office_sus) {
		this.record_office_sus = record_office_sus;
	}
	public String getRecord_office_unit() {
		return record_office_unit;
	}
	public void setRecord_office_unit(String record_office_unit) {
		this.record_office_unit = record_office_unit;
	}
	public String getParent_arm_service() {
           return parent_arm_service;
      }
      public void setParent_arm_service(String parent_arm_service) {
	  this.parent_arm_service = parent_arm_service;
      }
      public String getRegt() {
           return regt;
      }
      public void setRegt(String regt) {
	  this.regt = regt;
      }
      public Date getWith_effect_from() {
           return with_effect_from;
      }
      public void setWith_effect_from(Date with_effect_from) {
	  this.with_effect_from = with_effect_from;
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
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public int getJco_id() {
           return jco_id;
      }
      public void setJco_id(int jco_id) {
	  this.jco_id = jco_id;
      }
      public String getInitiated_from() {
           return initiated_from;
      }
      public void setInitiated_from(String initiated_from) {
	  this.initiated_from = initiated_from;
      }
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
      public String getReject_remarks() {
			return reject_remarks;
		}
		public void setReject_remarks(String reject_remarks) {
			this.reject_remarks = reject_remarks;
		}
}
