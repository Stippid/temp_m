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
@Table(name = "tb_psg_canteen_card_details1_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CANTEEN_CARD_DETAILS1_JCO {

      private int id;
      private String name;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_birth;
      private String type_of_card;
      private String card_no;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String modified_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
      private int relation;
      private String initiated_from;
      private int jco_id;
      private int status;
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
      public String getName() {
           return name;
      }
      public void setName(String name) {
	  this.name = name;
      }
      public Date getDate_of_birth() {
           return date_of_birth;
      }
      public void setDate_of_birth(Date date_of_birth) {
	  this.date_of_birth = date_of_birth;
      }
      public String getType_of_card() {
           return type_of_card;
      }
      public void setType_of_card(String type_of_card) {
	  this.type_of_card = type_of_card;
      }
      public String getCard_no() {
           return card_no;
      }
      public void setCard_no(String card_no) {
	  this.card_no = card_no;
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
      public int getRelation() {
           return relation;
      }
      public void setRelation(int relation) {
	  this.relation = relation;
      }
      public String getInitiated_from() {
           return initiated_from;
      }
      public void setInitiated_from(String initiated_from) {
	  this.initiated_from = initiated_from;
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
