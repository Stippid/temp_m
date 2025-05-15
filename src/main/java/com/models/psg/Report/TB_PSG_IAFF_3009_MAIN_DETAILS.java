package com.models.psg.Report;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_iaff_3009_main_details", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_IAFF_3009_MAIN_DETAILS {
	
	private int id;
	private String month;
	private String year;
	private String version;
	private String sus_no;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String we_pe_no;
	private String auth;
	private String held;
	private String present_return_no;
	private Date present_return_dt;
	private String cmd_sus;
	private String cmd_unit;
	private String corp_sus;
	private String corp_unit;
	private String div_sus;
	private String div_unit;
	private String bde_sus;
	private String bde_unit;
	private String observation;
	private String remarks;
	private String distributionlist;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getHeld() {
		return held;
	}
	public void setHeld(String held) {
		this.held = held;
	}
	public String getPresent_return_no() {
		return present_return_no;
	}
	public void setPresent_return_no(String present_return_no) {
		this.present_return_no = present_return_no;
	}
	public Date getPresent_return_dt() {
		return present_return_dt;
	}
	public void setPresent_return_dt(Date present_return_dt) {
		this.present_return_dt = present_return_dt;
	}
	public String getCmd_sus() {
		return cmd_sus;
	}
	public void setCmd_sus(String cmd_sus) {
		this.cmd_sus = cmd_sus;
	}
	public String getCmd_unit() {
		return cmd_unit;
	}
	public void setCmd_unit(String cmd_unit) {
		this.cmd_unit = cmd_unit;
	}
	public String getCorp_sus() {
		return corp_sus;
	}
	public void setCorp_sus(String corp_sus) {
		this.corp_sus = corp_sus;
	}
	public String getCorp_unit() {
		return corp_unit;
	}
	public void setCorp_unit(String corp_unit) {
		this.corp_unit = corp_unit;
	}
	public String getDiv_sus() {
		return div_sus;
	}
	public void setDiv_sus(String div_sus) {
		this.div_sus = div_sus;
	}
	public String getDiv_unit() {
		return div_unit;
	}
	public void setDiv_unit(String div_unit) {
		this.div_unit = div_unit;
	}
	public String getBde_sus() {
		return bde_sus;
	}
	public void setBde_sus(String bde_sus) {
		this.bde_sus = bde_sus;
	}
	public String getBde_unit() {
		return bde_unit;
	}
	public void setBde_unit(String bde_unit) {
		this.bde_unit = bde_unit;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDistributionlist() {
		return distributionlist;
	}
	public void setDistributionlist(String distributionlist) {
		this.distributionlist = distributionlist;
	}
	
}
