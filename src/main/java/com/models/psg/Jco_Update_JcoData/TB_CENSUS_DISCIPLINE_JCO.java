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
@Table(name = "tb_psg_census_discipline_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_DISCIPLINE_JCO {

      private int id;
      private int jco_id;
      private String description;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date award_dt;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
      private String modified_by;
      private int status;
      private String unit_name;
      private String disi_authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date disi_authority_date;
      private String type_of_entry_other;
      private int cancel_status= -1;
      private String cancel_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date cancel_date;
      private int trialed_by;
      private int type_of_entry;
      private int army_act_sec;
      private int sub_clause;
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
      public String getDescription() {
           return description;
      }
      public void setDescription(String description) {
	  this.description = description;
      }
      public Date getAward_dt() {
           return award_dt;
      }
      public void setAward_dt(Date award_dt) {
	  this.award_dt = award_dt;
      }
      public Date getCreated_date() {
           return created_date;
      }
      public void setCreated_date(Date created_date) {
	  this.created_date = created_date;
      }
      public String getCreated_by() {
           return created_by;
      }
      public void setCreated_by(String created_by) {
	  this.created_by = created_by;
      }
      public Date getModified_date() {
           return modified_date;
      }
      public void setModified_date(Date modified_date) {
	  this.modified_date = modified_date;
      }
      public String getModified_by() {
           return modified_by;
      }
      public void setModified_by(String modified_by) {
	  this.modified_by = modified_by;
      }
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public String getUnit_name() {
           return unit_name;
      }
      public void setUnit_name(String unit_name) {
	  this.unit_name = unit_name;
      }
      public String getDisi_authority() {
           return disi_authority;
      }
      public void setDisi_authority(String disi_authority) {
	  this.disi_authority = disi_authority;
      }
      public Date getDisi_authority_date() {
           return disi_authority_date;
      }
      public void setDisi_authority_date(Date disi_authority_date) {
	  this.disi_authority_date = disi_authority_date;
      }
      public String getType_of_entry_other() {
           return type_of_entry_other;
      }
      public void setType_of_entry_other(String type_of_entry_other) {
	  this.type_of_entry_other = type_of_entry_other;
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
      public int getTrialed_by() {
           return trialed_by;
      }
      public void setTrialed_by(int trialed_by) {
	  this.trialed_by = trialed_by;
      }
      public int getType_of_entry() {
           return type_of_entry;
      }
      public void setType_of_entry(int type_of_entry) {
	  this.type_of_entry = type_of_entry;
      }
      public int getArmy_act_sec() {
           return army_act_sec;
      }
      public void setArmy_act_sec(int army_act_sec) {
	  this.army_act_sec = army_act_sec;
      }
      public int getSub_clause() {
           return sub_clause;
      }
      public void setSub_clause(int sub_clause) {
	  this.sub_clause = sub_clause;
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
