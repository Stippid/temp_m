/*package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "provision_unitswise")
public class CUE_PROVISION_WEAPON_UNITSWISE {
	private int id;
	private int provision_id;
	private String month_cal;
	private String year_cal;
	private String we_pe_no;
	private String we_wet_no;
	private String we_wet_type;
	private String unit_type;
	private String force_type;
	private String item_type;
	private String item_code;
	private String sub_item_code;
	private String sub_item_type;
	private String ces_cces_no;
	private String detail;
	private Double authorization;
	private int no_units;
	private String ces_auth;    
	private Double auth_units_multi;       
	private Double ces_auth_units_multi;
	private String detail_new;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProvision_id() {
		return provision_id;
	}
	public void setProvision_id(int provision_id) {
		this.provision_id = provision_id;
	}
	public String getMonth_cal() {
		return month_cal;
	}
	public void setMonth_cal(String month_cal) {
		this.month_cal = month_cal;
	}
	public String getYear_cal() {
		return year_cal;
	}
	public void setYear_cal(String year_cal) {
		this.year_cal = year_cal;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public String getWe_wet_no() {
		return we_wet_no;
	}
	public void setWe_wet_no(String we_wet_no) {
		this.we_wet_no = we_wet_no;
	}
	public String getWe_wet_type() {
		return we_wet_type;
	}
	public void setWe_wet_type(String we_wet_type) {
		this.we_wet_type = we_wet_type;
	}
	public String getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	public String getForce_type() {
		return force_type;
	}
	public void setForce_type(String force_type) {
		this.force_type = force_type;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getCes_cces_no() {
		return ces_cces_no;
	}
	public void setCes_cces_no(String ces_cces_no) {
		this.ces_cces_no = ces_cces_no;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Double getAuth_units_multi() {
		return auth_units_multi;
	}
	public void setAuth_units_multi(Double auth_units_multi) {
		this.auth_units_multi = auth_units_multi;
	}
	public Double getCes_auth_units_multi() {
		return ces_auth_units_multi;
	}
	public void setCes_auth_units_multi(Double ces_auth_units_multi) {
		this.ces_auth_units_multi = ces_auth_units_multi;
	}
	public String getDetail_new() {
		return detail_new;
	}
	public void setDetail_new(String detail_new) {
		this.detail_new = detail_new;
	}
	public String getSub_item_code() {
		return sub_item_code;
	}
	public void setSub_item_code(String sub_item_code) {
		this.sub_item_code = sub_item_code;
	}
	public String getSub_item_type() {
		return sub_item_type;
	}
	public void setSub_item_type(String sub_item_type) {
		this.sub_item_type = sub_item_type;
	}
	public Double getAuthorization() {
		return authorization;
	}
	public void setAuthorization(Double authorization) {
		this.authorization = authorization;
	}
	public int getNo_units() {
		return no_units;
	}
	public void setNo_units(int no_units) {
		this.no_units = no_units;
	}
	public String getCes_auth() {
		return ces_auth;
	}
	public void setCes_auth(String ces_auth) {
		this.ces_auth = ces_auth;
	}	
}
*/