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
@Table(name = "tb_psg_census_language_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_LANGUAGE_JCO {

      private int id;
      private int jco_id;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_on;
      private int f_lang_prof;
      private int foreign_language;
      private int lang_std;
      private int language;
      private String modify_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modify_on;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private int status;
      private String f_exam_pass;
      private String other_language;
      private String other_lang_std;
      private String f_other_language;
      private String f_other_lang_std;
      private String f_other_prof;
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
      public int getF_lang_prof() {
           return f_lang_prof;
      }
      public void setF_lang_prof(int f_lang_prof) {
	  this.f_lang_prof = f_lang_prof;
      }
      public int getForeign_language() {
           return foreign_language;
      }
      public void setForeign_language(int foreign_language) {
	  this.foreign_language = foreign_language;
      }
      public int getLang_std() {
           return lang_std;
      }
      public void setLang_std(int lang_std) {
	  this.lang_std = lang_std;
      }
      public int getLanguage() {
           return language;
      }
      public void setLanguage(int language) {
	  this.language = language;
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
      public String getF_exam_pass() {
           return f_exam_pass;
      }
      public void setF_exam_pass(String f_exam_pass) {
	  this.f_exam_pass = f_exam_pass;
      }
      public String getOther_language() {
           return other_language;
      }
      public void setOther_language(String other_language) {
	  this.other_language = other_language;
      }
      public String getOther_lang_std() {
           return other_lang_std;
      }
      public void setOther_lang_std(String other_lang_std) {
	  this.other_lang_std = other_lang_std;
      }
      public String getF_other_language() {
           return f_other_language;
      }
      public void setF_other_language(String f_other_language) {
	  this.f_other_language = f_other_language;
      }
      public String getF_other_lang_std() {
           return f_other_lang_std;
      }
      public void setF_other_lang_std(String f_other_lang_std) {
	  this.f_other_lang_std = f_other_lang_std;
      }
      public String getF_other_prof() {
           return f_other_prof;
      }
      public void setF_other_prof(String f_other_prof) {
	  this.f_other_prof = f_other_prof;
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
		public void setReject_remarks(String  reject_remarks) {
			this.reject_remarks = reject_remarks;
		}
}
