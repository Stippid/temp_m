package com.models.mnh;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "med_principle_dis" )
public class Med_principle_dis {
	  
	  private String persnl_unit_desc;
	  private int off;
	  private int mns;
	  private int jco;
	  private int or;
	  private String disease_principal;
	  private String disease_type;
	  private int count;
	  
	@Id
	public String getPersnl_unit_desc() {
		return persnl_unit_desc;
	}
	public void setPersnl_unit_desc(String persnl_unit_desc) {
		this.persnl_unit_desc = persnl_unit_desc;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
