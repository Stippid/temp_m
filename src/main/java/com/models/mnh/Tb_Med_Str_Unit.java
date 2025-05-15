package com.models.mnh;

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
@Table(name = "tb_med_str_unit", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Str_Unit {

	private int id;
	private String sus_no;
	private int officer;
	private int cadets;
	private int mns;
	private int jcos;
	private int rects;
	private int cive;
	private int civne;
	private int others;
	private int total_off;
	private int total_cadet;
	private int total_mns;
	private int total_jco;
	private int total_or;
	private int total_rect;
	private int total_cive;
	private int total_civne;
	private int total_other;
	private int total_lodger;
	private int total_nonlodger;
	private int total_strength;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private int off_lodg_for;
	private int off_nonlodg_for;
	private int off_total_str;
	private int cadet_lodg_for;
	private int cadet_nonlodg_for;
	private int cadet_total_str;
	private int mns_lodg_for;
	private int mns_nonlodg_for;
	private int mns_total_str;
	private int jco_lodg_for;
	private int jco_nonlodg_for;
	private int jco_total_str;
	private int or_lodg_for;
	private int or_nonlodg_for;
	private int or_total_str;
	private int rect_lodg_for;
	private int rect_nonlodg_for;
	private int rect_total_str;
	private int cive_lodg_for;
	private int cive_nonlodg_for;
	private int cive_total_str;
	private int civne_lodg_for;
	private int civne_nonlodg_for;
	private int civne_total_str;
	private int other_lodg_for;
	private int other_nonlodg_for;
	private int other_total_str;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOfficer() {
		return officer;
	}
	public void setOfficer(int officer) {
		this.officer = officer;
	}
	public int getCadets() {
		return cadets;
	}
	public void setCadets(int cadets) {
		this.cadets = cadets;
	}
	public int getMns() {
		return mns;
	}
	public void setMns(int mns) {
		this.mns = mns;
	}
	public int getJcos() {
		return jcos;
	}
	public void setJcos(int jcos) {
		this.jcos = jcos;
	}	
	public int getRects() {
		return rects;
	}
	public void setRects(int rects) {
		this.rects = rects;
	}
	public int getCive() {
		return cive;
	}
	public void setCive(int cive) {
		this.cive = cive;
	}
	public int getCivne() {
		return civne;
	}
	public void setCivne(int civne) {
		this.civne = civne;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
	}
	public int getTotal_off() {
		return total_off;
	}
	public void setTotal_off(int total_off) {
		this.total_off = total_off;
	}
	public int getTotal_cadet() {
		return total_cadet;
	}
	public void setTotal_cadet(int total_cadet) {
		this.total_cadet = total_cadet;
	}
	public int getTotal_mns() {
		return total_mns;
	}
	public void setTotal_mns(int total_mns) {
		this.total_mns = total_mns;
	}
	public int getTotal_jco() {
		return total_jco;
	}
	public void setTotal_jco(int total_jco) {
		this.total_jco = total_jco;
	}
	public int getTotal_or() {
		return total_or;
	}
	public void setTotal_or(int total_or) {
		this.total_or = total_or;
	}
	public int getTotal_rect() {
		return total_rect;
	}
	public void setTotal_rect(int total_rect) {
		this.total_rect = total_rect;
	}
	public int getTotal_cive() {
		return total_cive;
	}
	public void setTotal_cive(int total_cive) {
		this.total_cive = total_cive;
	}
	public int getTotal_civne() {
		return total_civne;
	}
	public void setTotal_civne(int total_civne) {
		this.total_civne = total_civne;
	}
	public int getTotal_other() {
		return total_other;
	}
	public void setTotal_other(int total_other) {
		this.total_other = total_other;
	}
	public int getTotal_lodger() {
		return total_lodger;
	}
	public void setTotal_lodger(int total_lodger) {
		this.total_lodger = total_lodger;
	}
	public int getTotal_nonlodger() {
		return total_nonlodger;
	}
	public void setTotal_nonlodger(int total_nonlodger) {
		this.total_nonlodger = total_nonlodger;
	}
	public int getTotal_strength() {
		return total_strength;
	}
	public void setTotal_strength(int total_strength) {
		this.total_strength = total_strength;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getOff_lodg_for() {
		return off_lodg_for;
	}
	public void setOff_lodg_for(int off_lodg_for) {
		this.off_lodg_for = off_lodg_for;
	}
	public int getOff_nonlodg_for() {
		return off_nonlodg_for;
	}
	public void setOff_nonlodg_for(int off_nonlodg_for) {
		this.off_nonlodg_for = off_nonlodg_for;
	}
	public int getOff_total_str() {
		return off_total_str;
	}
	public void setOff_total_str(int off_total_str) {
		this.off_total_str = off_total_str;
	}
	public int getCadet_lodg_for() {
		return cadet_lodg_for;
	}
	public void setCadet_lodg_for(int cadet_lodg_for) {
		this.cadet_lodg_for = cadet_lodg_for;
	}
	public int getCadet_nonlodg_for() {
		return cadet_nonlodg_for;
	}
	public void setCadet_nonlodg_for(int cadet_nonlodg_for) {
		this.cadet_nonlodg_for = cadet_nonlodg_for;
	}
	public int getCadet_total_str() {
		return cadet_total_str;
	}
	public void setCadet_total_str(int cadet_total_str) {
		this.cadet_total_str = cadet_total_str;
	}
	public int getMns_lodg_for() {
		return mns_lodg_for;
	}
	public void setMns_lodg_for(int mns_lodg_for) {
		this.mns_lodg_for = mns_lodg_for;
	}
	public int getMns_nonlodg_for() {
		return mns_nonlodg_for;
	}
	public void setMns_nonlodg_for(int mns_nonlodg_for) {
		this.mns_nonlodg_for = mns_nonlodg_for;
	}
	public int getMns_total_str() {
		return mns_total_str;
	}
	public void setMns_total_str(int mns_total_str) {
		this.mns_total_str = mns_total_str;
	}
	public int getJco_lodg_for() {
		return jco_lodg_for;
	}
	public void setJco_lodg_for(int jco_lodg_for) {
		this.jco_lodg_for = jco_lodg_for;
	}
	public int getJco_nonlodg_for() {
		return jco_nonlodg_for;
	}
	public void setJco_nonlodg_for(int jco_nonlodg_for) {
		this.jco_nonlodg_for = jco_nonlodg_for;
	}
	public int getJco_total_str() {
		return jco_total_str;
	}
	public void setJco_total_str(int jco_total_str) {
		this.jco_total_str = jco_total_str;
	}
	public int getOr_lodg_for() {
		return or_lodg_for;
	}
	public void setOr_lodg_for(int or_lodg_for) {
		this.or_lodg_for = or_lodg_for;
	}
	public int getOr_nonlodg_for() {
		return or_nonlodg_for;
	}
	public void setOr_nonlodg_for(int or_nonlodg_for) {
		this.or_nonlodg_for = or_nonlodg_for;
	}
	public int getOr_total_str() {
		return or_total_str;
	}
	public void setOr_total_str(int or_total_str) {
		this.or_total_str = or_total_str;
	}
	public int getRect_lodg_for() {
		return rect_lodg_for;
	}
	public void setRect_lodg_for(int rect_lodg_for) {
		this.rect_lodg_for = rect_lodg_for;
	}
	public int getRect_nonlodg_for() {
		return rect_nonlodg_for;
	}
	public void setRect_nonlodg_for(int rect_nonlodg_for) {
		this.rect_nonlodg_for = rect_nonlodg_for;
	}
	public int getRect_total_str() {
		return rect_total_str;
	}
	public void setRect_total_str(int rect_total_str) {
		this.rect_total_str = rect_total_str;
	}
	public int getCive_lodg_for() {
		return cive_lodg_for;
	}
	public void setCive_lodg_for(int cive_lodg_for) {
		this.cive_lodg_for = cive_lodg_for;
	}
	public int getCive_nonlodg_for() {
		return cive_nonlodg_for;
	}
	public void setCive_nonlodg_for(int cive_nonlodg_for) {
		this.cive_nonlodg_for = cive_nonlodg_for;
	}
	public int getCive_total_str() {
		return cive_total_str;
	}
	public void setCive_total_str(int cive_total_str) {
		this.cive_total_str = cive_total_str;
	}
	public int getCivne_lodg_for() {
		return civne_lodg_for;
	}
	public void setCivne_lodg_for(int civne_lodg_for) {
		this.civne_lodg_for = civne_lodg_for;
	}
	public int getCivne_nonlodg_for() {
		return civne_nonlodg_for;
	}
	public void setCivne_nonlodg_for(int civne_nonlodg_for) {
		this.civne_nonlodg_for = civne_nonlodg_for;
	}
	public int getCivne_total_str() {
		return civne_total_str;
	}
	public void setCivne_total_str(int civne_total_str) {
		this.civne_total_str = civne_total_str;
	}
	public int getOther_lodg_for() {
		return other_lodg_for;
	}
	public void setOther_lodg_for(int other_lodg_for) {
		this.other_lodg_for = other_lodg_for;
	}
	public int getOther_nonlodg_for() {
		return other_nonlodg_for;
	}
	public void setOther_nonlodg_for(int other_nonlodg_for) {
		this.other_nonlodg_for = other_nonlodg_for;
	}
	public int getOther_total_str() {
		return other_total_str;
	}
	public void setOther_total_str(int other_total_str) {
		this.other_total_str = other_total_str;
	}
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
}
