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
@Table(name = "tb_psg_census_change_in_trade_jco", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class TB_CHANGE_IN_TRADE_JCO {

	private int id;
	private int jco_id;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String td_authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date td_date_authority;
	private int trade;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_trade;
	private String initiated_from;
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

	public String getTd_authority() {
		return td_authority;
	}

	public void setTd_authority(String td_authority) {
		this.td_authority = td_authority;
	}

	public Date getTd_date_authority() {
		return td_date_authority;
	}

	public void setTd_date_authority(Date td_date_authority) {
		this.td_date_authority = td_date_authority;
	}

	public int getTrade() {
		return trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public Date getDate_of_trade() {
		return date_of_trade;
	}

	public void setDate_of_trade(Date date_of_trade) {
		this.date_of_trade = date_of_trade;
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


public String getReject_remarks() {
				return reject_remarks;
			}
			public void setReject_remarks(String  reject_remarks) {
				this.reject_remarks = reject_remarks;
			}
}
