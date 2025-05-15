package com.models.psg.Jco_Update_JcoData;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_census_attachment_details_jco", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_ATTACHMENT_DETAILS_JCO {

	private int id;
	private int jco_id;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String initiated_from;
	private int status;
	private int cancel_status = -1;
	private String cancel_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cancel_date;
	private String att_authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date att_date_authority;
	private String att_movement_number;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date att_date_of_move;
	private String att_sus_no;
	private String att_place;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date att_from;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date att_to;
	private String att_reasons;
	private String att_duration;
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

	public String getInitiated_from() {
		return initiated_from;
	}

	public void setInitiated_from(String initiated_from) {
		this.initiated_from = initiated_from;
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

	public String getAtt_authority() {
		return att_authority;
	}

	public void setAtt_authority(String att_authority) {
		this.att_authority = att_authority;
	}

	public Date getAtt_date_authority() {
		return att_date_authority;
	}

	public void setAtt_date_authority(Date att_date_authority) {
		this.att_date_authority = att_date_authority;
	}

	public String getAtt_movement_number() {
		return att_movement_number;
	}

	public void setAtt_movement_number(String att_movement_number) {
		this.att_movement_number = att_movement_number;
	}

	public Date getAtt_date_of_move() {
		return att_date_of_move;
	}

	public void setAtt_date_of_move(Date att_date_of_move) {
		this.att_date_of_move = att_date_of_move;
	}

	public String getAtt_sus_no() {
		return att_sus_no;
	}

	public void setAtt_sus_no(String att_sus_no) {
		this.att_sus_no = att_sus_no;
	}

	public String getAtt_place() {
		return att_place;
	}

	public void setAtt_place(String att_place) {
		this.att_place = att_place;
	}

	public Date getAtt_from() {
		return att_from;
	}

	public void setAtt_from(Date att_from) {
		this.att_from = att_from;
	}

	public Date getAtt_to() {
		return att_to;
	}

	public void setAtt_to(Date att_to) {
		this.att_to = att_to;
	}

	public String getAtt_reasons() {
		return att_reasons;
	}

	public void setAtt_reasons(String att_reasons) {
		this.att_reasons = att_reasons;
	}

	public String getAtt_duration() {
		return att_duration;
	}

	public void setAtt_duration(String att_duration) {
		this.att_duration = att_duration;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String   reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
