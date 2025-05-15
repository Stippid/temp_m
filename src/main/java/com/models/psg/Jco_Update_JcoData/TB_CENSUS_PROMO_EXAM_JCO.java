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
@Table(name = "tb_psg_census_promo_exam_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_PROMO_EXAM_JCO {

      private int id;
      private int jco_id;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private String exam;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_on;
      private String modify_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modify_on;
      private int status;
      private String date_of_passing;
      private String exam_other;
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
      public int getJco_id() {
           return jco_id;
      }
      public void setJco_id(int jco_id) {
	  this.jco_id = jco_id;
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
      public String getExam() {
           return exam;
      }
      public void setExam(String exam) {
	  this.exam = exam;
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
      public String getModify_by() {
           return modify_by;
      }
      public void setModify_by(String modify_by) {
	  this.modify_by = modify_by;
      }
      public Date getModify_on() {
           return modify_on;
      }
      public void setModify_on(Date modify_on) {
	  this.modify_on = modify_on;
      }
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public String getDate_of_passing() {
           return date_of_passing;
      }
      public void setDate_of_passing(String date_of_passing) {
	  this.date_of_passing = date_of_passing;
      }
      public String getExam_other() {
           return exam_other;
      }
      public void setExam_other(String exam_other) {
	  this.exam_other = exam_other;
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
