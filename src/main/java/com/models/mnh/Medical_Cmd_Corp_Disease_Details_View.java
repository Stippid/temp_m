package com.models.mnh;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medical_cmd_corp_disease_details_view" )
public class Medical_Cmd_Corp_Disease_Details_View {

	private String sus_no;
	private String unit_name;
	private String cmd_name;
	private String coprs_name;
	private String div_name;
	private String bde_name;
	private int off;
	private int mns;
	private int jco;
	private int or;
	private String disease_principal;
	private String disease_type;
	private String block_description;
	private int total;
	private int yr;
	
	@Id
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
	public String getCmd_name() {
		return cmd_name;
	}
	public void setCmd_name(String cmd_name) {
		this.cmd_name = cmd_name;
	}
	public String getCoprs_name() {
		return coprs_name;
	}
	public void setCoprs_name(String coprs_name) {
		this.coprs_name = coprs_name;
	}
	public String getDiv_name() {
		return div_name;
	}
	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}
	public String getBde_name() {
		return bde_name;
	}
	public void setBde_name(String bde_name) {
		this.bde_name = bde_name;
	}
	public int getOff() {
		return off;
	}
	public void setOff(int off) {
		this.off = off;
	}
	public int getMns() {
		return mns;
	}
	public void setMns(int mns) {
		this.mns = mns;
	}
	public int getJco() {
		return jco;
	}
	public void setJco(int jco) {
		this.jco = jco;
	}
	public int getOr() {
		return or;
	}
	public void setOr(int or) {
		this.or = or;
	}
	public String getDisease_principal() {
		return disease_principal;
	}
	public void setDisease_principal(String disease_principal) {
		this.disease_principal = disease_principal;
	}
	public String getDisease_type() {
		return disease_type;
	}
	public void setDisease_type(String disease_type) {
		this.disease_type = disease_type;
	}
	public String getBlock_description() {
		return block_description;
	}
	public void setBlock_description(String block_description) {
		this.block_description = block_description;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getYr() {
		return yr;
	}
	public void setYr(int yr) {
		this.yr = yr;
	}
	
}
