package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_awardsnmedal_details", uniqueConstraints = {@UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_AWARDSNMEDAL_DETAILS {
	
	String category;
	Date date_of_award;
	String unit;
	String bde;
	String div_subarea;
	String corps_area;
	String command;
	String authority;
	Date date_of_authority;
	String award_medal;
	String personal_no;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBde() {
		return bde;
	}
	public void setBde(String bde) {
		this.bde = bde;
	}
	public String getDiv_subarea() {
		return div_subarea;
	}
	public void setDiv_subarea(String div_subarea) {
		this.div_subarea = div_subarea;
	}
	public String getCorps_area() {
		return corps_area;
	}
	public void setCorps_area(String corps_area) {
		this.corps_area = corps_area;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getAward_medal() {
		return award_medal;
	}
	public void setAward_medal(String award_medal) {
		this.award_medal = award_medal;
	}
	public String getPersonal_no() {
		return personal_no;
	}
	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}
	public Date getDate_of_award() {
		return date_of_award;
	}
	public void setDate_of_award(Date date_of_award) {
		this.date_of_award = date_of_award;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}

	
}
