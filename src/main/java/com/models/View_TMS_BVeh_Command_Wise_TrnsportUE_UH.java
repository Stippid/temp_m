package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tms_bveh_command_wise_transport_ue_uh_view")
public class View_TMS_BVeh_Command_Wise_TrnsportUE_UH {

	private String unit_name;
	private String sus_no;
	private String command;
	private String corps;
	private String div;
	private String bde;
	private String ue;
	private String uh;
	private String defi;
	
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
	public String getUe() {
		return ue;
	}
	public void setUe(String ue) {
		this.ue = ue;
	}
	public String getUh() {
		return uh;
	}
	public void setUh(String uh) {
		this.uh = uh;
	}
	public String getDefi() {
		return defi;
	}
	public void setDefi(String defi) {
		this.defi = defi;
	}
	
}
