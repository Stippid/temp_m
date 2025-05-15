package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "prf_wise_ue_uh_tms")
public class Prf_Wise_TrnsportUE_UH {
	
	
	private String prf_group;
	private String group_name;
	private String ue;
	private String uh;
	
	@Id
	public String getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
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
}
