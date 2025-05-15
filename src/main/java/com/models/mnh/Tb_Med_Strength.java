
package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_strength", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Strength {
	private int id;
	private String qtr;
	private int year;
	private String cmd;
	private int off_male;
	private int off_female;
	private int cadet;
	private int lady_cadet;
	private int mns;
	private int mns_cadet;
	private int jco;
	private int ort;
	private int dsc_jco;
	private int dsc_or;
	private int rect;
	private int total;
	private Date created_on;
	private String created_by;
	private int high_offr;
	private int high_jco;
	private int high_or;
	private String modified_by;
	private Date modified_on;
	
  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public int getOff_male() {
		return off_male;
	}
	public void setOff_male(int off_male) {
		this.off_male = off_male;
	}
	public int getOff_female() {
		return off_female;
	}
	public void setOff_female(int off_female) {
		this.off_female = off_female;
	}
	public int getCadet() {
		return cadet;
	}
	public void setCadet(int cadet) {
		this.cadet = cadet;
	}
	public int getLady_cadet() {
		return lady_cadet;
	}
	public void setLady_cadet(int lady_cadet) {
		this.lady_cadet = lady_cadet;
	}
	public int getMns() {
		return mns;
	}
	public void setMns(int mns) {
		this.mns = mns;
	}
	public int getMns_cadet() {
		return mns_cadet;
	}
	public void setMns_cadet(int mns_cadet) {
		this.mns_cadet = mns_cadet;
	}
	public int getJco() {
		return jco;
	}
	public void setJco(int jco) {
		this.jco = jco;
	}
	public int getOrt() {
		return ort;
	}
	public void setOrt(int ort) {
		this.ort = ort;
	}
	public int getDsc_jco() {
		return dsc_jco;
	}
	public void setDsc_jco(int dsc_jco) {
		this.dsc_jco = dsc_jco;
	}
	public int getDsc_or() {
		return dsc_or;
	}
	public void setDsc_or(int dsc_or) {
		this.dsc_or = dsc_or;
	}
	public int getRect() {
		return rect;
	}
	public void setRect(int rect) {
		this.rect = rect;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public int getHigh_offr() {
		return high_offr;
	}
	public void setHigh_offr(int high_offr) {
		this.high_offr = high_offr;
	}
	public int getHigh_jco() {
		return high_jco;
	}
	public void setHigh_jco(int high_jco) {
		this.high_jco = high_jco;
	}
	public int getHigh_or() {
		return high_or;
	}
	public void setHigh_or(int high_or) {
		this.high_or = high_or;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getQtr() {
		return qtr;
	}
	public void setQtr(String qtr) {
		this.qtr = qtr;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
