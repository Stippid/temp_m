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
@Table(name = "tb_psg_census_bpet_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_BPET_JCO {

      private int id;
      private String bpet_qe;
      private String bpet_result;
      private int jco_id;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String modified_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
      private int year;
      private int status;
      private String initiated_from;
      private String unit_sus_no;
      private String bept_result_others;
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
      public String getBpet_qe() {
           return bpet_qe;
      }
      public void setBpet_qe(String bpet_qe) {
	  this.bpet_qe = bpet_qe;
      }
      public String getBpet_result() {
           return bpet_result;
      }
      public void setBpet_result(String bpet_result) {
	  this.bpet_result = bpet_result;
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
      public int getYear() {
           return year;
      }
      public void setYear(int year) {
	  this.year = year;
      }
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public String getInitiated_from() {
           return initiated_from;
      }
      public void setInitiated_from(String initiated_from) {
	  this.initiated_from = initiated_from;
      }
      public String getUnit_sus_no() {
           return unit_sus_no;
      }
      public void setUnit_sus_no(String unit_sus_no) {
	  this.unit_sus_no = unit_sus_no;
      }
      public String getBept_result_others() {
           return bept_result_others;
      }
      public void setBept_result_others(String bept_result_others) {
	  this.bept_result_others = bept_result_others;
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
		public void setReject_remarks(String  reject_remarks) {
			this.reject_remarks = reject_remarks;
		}

}
