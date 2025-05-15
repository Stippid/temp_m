package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tms_bveh_unit_wise_issue_type_uh")
public class View_Tms_BVEH_Unit_Wise_Issue_Type_UH {

	private String unit_name;
	private String sus_no;
	private String command;
	private String corps;
	private String div;
	private String bde;
	
	private String loan;
	private String sector;
	private String acsfp;
	private String aginst_ue;
	private String over_ue;
	
	@Id
		
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		this.corps = corps;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getBde() {
		return bde;
	}
	public void setBde(String bde) {
		this.bde = bde;
	}
	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getAcsfp() {
		return acsfp;
	}
	public void setAcsfp(String acsfp) {
		this.acsfp = acsfp;
	}
	public String getAginst_ue() {
		return aginst_ue;
	}
	public void setAginst_ue(String aginst_ue) {
		this.aginst_ue = aginst_ue;
	}
	public String getOver_ue() {
		return over_ue;
	}
	public void setOver_ue(String over_ue) {
		this.over_ue = over_ue;
	}

	
}
