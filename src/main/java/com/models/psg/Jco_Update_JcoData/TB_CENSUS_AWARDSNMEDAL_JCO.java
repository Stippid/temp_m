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
@Table(name = "tb_psg_census_awardsnmedal_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_AWARDSNMEDAL_JCO {

      private int id;
      private String created_by;
      private String category_8;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_award;
      private String unit;
      private String bde;
      private String div_subarea;
      private String corps_area;
      private String command;
      private String modify_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modify_on;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_on;
      private int status;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private int jco_id;
      private String initiated_from;
      private String select_desc;
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
      public String getCreated_by() {
           return created_by;
      }
      public void setCreated_by(String created_by) {
	  this.created_by = created_by;
      }
      public String getCategory_8() {
           return category_8;
      }
      public void setCategory_8(String category_8) {
	  this.category_8 = category_8;
      }
      public Date getDate_of_award() {
           return date_of_award;
      }
      public void setDate_of_award(Date date_of_award) {
	  this.date_of_award = date_of_award;
      }
      public String getUnit() {
           return unit;
      }
      public void setUnit(String unit) {
	  this.unit = unit;
      }
      public String getBde() {
           return bde;
      }
      public void setBde(String bde) {
	  this.bde = bde;
      }
      public String getDiv_subarea() {
           return div_subarea;
      }
      public void setDiv_subarea(String div_subarea) {
	  this.div_subarea = div_subarea;
      }
      public String getCorps_area() {
           return corps_area;
      }
      public void setCorps_area(String corps_area) {
	  this.corps_area = corps_area;
      }
      public String getCommand() {
           return command;
      }
      public void setCommand(String command) {
	  this.command = command;
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
      public Date getCreated_on() {
           return created_on;
      }
      public void setCreated_on(Date created_on) {
	  this.created_on = created_on;
      }
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
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
      public String getSelect_desc() {
           return select_desc;
      }
      public void setSelect_desc(String select_desc) {
	  this.select_desc = select_desc;
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
		public void setReject_remarks(String   reject_remarks) {
			this.reject_remarks = reject_remarks;
		}

}
