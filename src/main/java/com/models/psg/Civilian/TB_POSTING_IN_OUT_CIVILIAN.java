package com.models.psg.Civilian;

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
@Table(name = "tb_psg_posting_in_out_civilian", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_POSTING_IN_OUT_CIVILIAN {
	
	private int id;
	private int civ_id; 
	private int rank; 
	private String to_sus_no;
	private String from_sus_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dt_of_tos;
	private Date dt_of_sos;
	private int status; 
	private int notification_status;
	private String in_auth;
	private Date in_auth_dt;
	private String out_auth;	
	private Date out_auth_dt;	
	private String cmd_sus;
	private String corps_sus;
	private String div_sus;
	private String bde_sus;
	private String location;
	private String trn_type;
	private Date tenure_date;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String unit_description;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
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
	public int getCiv_id() {
		return civ_id;
	}
	public void setCiv_id(int civ_id) {
		this.civ_id = civ_id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}
	public String getFrom_sus_no() {
		return from_sus_no;
	}
	public void setFrom_sus_no(String from_sus_no) {
		this.from_sus_no = from_sus_no;
	}
	
	public Date getDt_of_tos() {
		return dt_of_tos;
	}
	public void setDt_of_tos(Date dt_of_tos) {
		this.dt_of_tos = dt_of_tos;
	}
	public Date getDt_of_sos() {
		return dt_of_sos;
	}
	public void setDt_of_sos(Date dt_of_sos) {
		this.dt_of_sos = dt_of_sos;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNotification_status() {
		return notification_status;
	}
	public void setNotification_status(int notification_status) {
		this.notification_status = notification_status;
	}
	public String getIn_auth() {
		return in_auth;
	}
	public void setIn_auth(String in_auth) {
		this.in_auth = in_auth;
	}
	public Date getIn_auth_dt() {
		return in_auth_dt;
	}
	public void setIn_auth_dt(Date in_auth_dt) {
		this.in_auth_dt = in_auth_dt;
	}
	public String getOut_auth() {
		return out_auth;
	}
	public void setOut_auth(String out_auth) {
		this.out_auth = out_auth;
	}
	public Date getOut_auth_dt() {
		return out_auth_dt;
	}
	public void setOut_auth_dt(Date out_auth_dt) {
		this.out_auth_dt = out_auth_dt;
	}
	public String getCmd_sus() {
		return cmd_sus;
	}
	public void setCmd_sus(String cmd_sus) {
		this.cmd_sus = cmd_sus;
	}
	public String getCorps_sus() {
		return corps_sus;
	}
	public void setCorps_sus(String corps_sus) {
		this.corps_sus = corps_sus;
	}
	public String getDiv_sus() {
		return div_sus;
	}
	public void setDiv_sus(String div_sus) {
		this.div_sus = div_sus;
	}
	public String getBde_sus() {
		return bde_sus;
	}
	public void setBde_sus(String bde_sus) {
		this.bde_sus = bde_sus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTrn_type() {
		return trn_type;
	}
	public void setTrn_type(String trn_type) {
		this.trn_type = trn_type;
	}
	public Date getTenure_date() {
		return tenure_date;
	}
	public void setTenure_date(Date tenure_date) {
		this.tenure_date = tenure_date;
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
	public String getUnit_description() {
		return unit_description;
	}
	public void setUnit_description(String unit_description) {
		this.unit_description = unit_description;
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
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
}
