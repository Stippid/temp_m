package com.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mms_tv_adh_unit_status")
public class MMS_TV_ADH_UNIT_STATUS {

	  private String form_code_control;
	  private String hq_name;
	  private String sus_no;
	  private String unit_name;
	  private String status_sus_no;
	  /*private String unit_status;*/
	  private String latest;
	  private String mth;
	  private String yr;
	  private String deo;
	  private Integer tot_c;
	  
	  @Id
	  public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	public String getHq_name() {
		return hq_name;
	}
	public void setHq_name(String hq_name) {
		this.hq_name = hq_name;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getStatus_sus_no() {
		return status_sus_no;
	}
	public void setStatus_sus_no(String status_sus_no) {
		this.status_sus_no = status_sus_no;
	}
	/*public String getUnit_status() {
		return unit_status;
	}
	public void setUnit_status(String unit_status) {
		this.unit_status = unit_status;
	}*/
	public String getLatest() {
		return latest;
	}
	public void setLatest(String latest) {
		this.latest = latest;
	}
	public String getMth() {
		return mth;
	}
	public void setMth(String mth) {
		this.mth = mth;
	}
	public String getYr() {
		return yr;
	}
	public void setYr(String yr) {
		this.yr = yr;
	}
	public String getDeo() {
		return deo;
	}
	public void setDeo(String deo) {
		this.deo = deo;
	}
	public Integer getTot_c() {
		return tot_c;
	}
	public void setTot_c(Integer tot_c) {
		this.tot_c = tot_c;
	}
	public Integer getTot_o() {
		return tot_o;
	}
	public void setTot_o(Integer tot_o) {
		this.tot_o = tot_o;
	}
	public Integer getTotpend() {
		return totpend;
	}
	public void setTotpend(Integer totpend) {
		this.totpend = totpend;
	}
	public Integer getTotresl() {
		return totresl;
	}
	public void setTotresl(Integer totresl) {
		this.totresl = totresl;
	}
	public Integer getTotwet() {
		return totwet;
	}
	public void setTotwet(Integer totwet) {
		this.totwet = totwet;
	}
	private Integer tot_o;
	  private Integer totpend;
	  private Integer totresl;
	  private Integer totwet;
	
	
}
