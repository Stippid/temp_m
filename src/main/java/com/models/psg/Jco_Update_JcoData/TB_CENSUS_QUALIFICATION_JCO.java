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
@Table(name = "tb_psg_census_qualification_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_QUALIFICATION_JCO {

      private int id;
      private int jco_id;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_on;
      private String modify_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modify_on;
      private int passing_year;
      private int type;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private int status;
      private String institute;
      private String exam_other;
      private String class_other;
      private int examination_pass;
      private int specialization;
      private int degree;
      private String degree_other;
      private String specialization_other;
      private int cancel_status= -1;
      private String cancel_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date cancel_date;
      private int div_class;
      private String subject;
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
      public int getPassing_year() {
           return passing_year;
      }
      public void setPassing_year(int passing_year) {
	  this.passing_year = passing_year;
      }
    
      public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public String getInstitute() {
           return institute;
      }
      public void setInstitute(String institute) {
	  this.institute = institute;
      }
      public String getExam_other() {
           return exam_other;
      }
      public void setExam_other(String exam_other) {
	  this.exam_other = exam_other;
      }
      public String getClass_other() {
           return class_other;
      }
      public void setClass_other(String class_other) {
	  this.class_other = class_other;
      }
      public int getExamination_pass() {
           return examination_pass;
      }
      public void setExamination_pass(int examination_pass) {
	  this.examination_pass = examination_pass;
      }
      public int getSpecialization() {
           return specialization;
      }
      public void setSpecialization(int specialization) {
	  this.specialization = specialization;
      }
      public int getDegree() {
           return degree;
      }
      public void setDegree(int degree) {
	  this.degree = degree;
      }
      public String getDegree_other() {
           return degree_other;
      }
      public void setDegree_other(String degree_other) {
	  this.degree_other = degree_other;
      }
      public String getSpecialization_other() {
           return specialization_other;
      }
      public void setSpecialization_other(String specialization_other) {
	  this.specialization_other = specialization_other;
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
      public int getDiv_class() {
           return div_class;
      }
      public void setDiv_class(int div_class) {
	  this.div_class = div_class;
      }
      public String getSubject() {
           return subject;
      }
      public void setSubject(String subject) {
	  this.subject = subject;
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
		public void setReject_remarks(String  reject_remarks) {
			this.reject_remarks = reject_remarks;
		}

}
