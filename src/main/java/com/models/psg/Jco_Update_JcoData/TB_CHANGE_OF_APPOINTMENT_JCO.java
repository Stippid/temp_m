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
@Table(name = "tb_psg_change_of_appointment_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CHANGE_OF_APPOINTMENT_JCO {

      private int id;
      private String appt_authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date appt_date_of_authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_appointment;
      private int jco_id;
      private int status;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String modified_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
      private int appointment;
      private int cancel_status= -1;
      private String cancel_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date cancel_date;
      private String initiated_from;
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
      public String getAppt_authority() {
           return appt_authority;
      }
      public void setAppt_authority(String appt_authority) {
	  this.appt_authority = appt_authority;
      }
      public Date getAppt_date_of_authority() {
           return appt_date_of_authority;
      }
      public void setAppt_date_of_authority(Date appt_date_of_authority) {
	  this.appt_date_of_authority = appt_date_of_authority;
      }
      public Date getDate_of_appointment() {
           return date_of_appointment;
      }
      public void setDate_of_appointment(Date date_of_appointment) {
	  this.date_of_appointment = date_of_appointment;
      }
      public int getJco_id() {
           return jco_id;
      }
      public void setJco_id(int jco_id) {
	  this.jco_id = jco_id;
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
      public int getAppointment() {
           return appointment;
      }
      public void setAppointment(int appointment) {
	  this.appointment = appointment;
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
      public String getInitiated_from() {
           return initiated_from;
      }
      public void setInitiated_from(String initiated_from) {
	  this.initiated_from = initiated_from;
      }

     public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
