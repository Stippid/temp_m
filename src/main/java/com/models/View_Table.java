package com.models;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class View_Table {
	String unit_name;
	String command;
	String division;
	String brigade;
	String corps;
	String personnel_no;
	String name;
	String description;
	String date_of_commission;
	String date_of_seniority;
	String unit_sus_no;
	BigInteger id;
	String date_of_birth;

	public View_Table(String date_of_birth) {
		super();
		this.date_of_birth = date_of_birth;
	}

	public View_Table(BigInteger id, String personnel_no, String description, String date_of_birth,
			String date_of_commission, String date_of_seniority, String unit_sus_no, String name) {
		super();
		this.id = id;
		this.personnel_no = personnel_no;
		this.description = description;
		this.date_of_birth = date_of_birth;
		this.date_of_commission = date_of_commission;
		this.date_of_seniority = date_of_seniority;
		this.unit_sus_no = unit_sus_no;
		this.name = name;
	}

	public View_Table(String unit_name, String command, String division, String brigade, String corps,
			String personnel_no, String name) {
		super();
		this.unit_name = unit_name;
		this.command = command;
		this.division = division;
		this.brigade = brigade;
		this.corps = corps;
		this.personnel_no = personnel_no;
		this.name = name;
	}

	public String getPersonnel_no() {
		return personnel_no;
	}

	public void setPersonnel_no(String personnel_no) {
		this.personnel_no = personnel_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getBrigade() {
		return brigade;
	}

	public void setBrigade(String brigade) {
		this.brigade = brigade;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getDate_of_commission() {
		return date_of_commission;
	}

	public void setDate_of_commission(String date_of_commission) {
		this.date_of_commission = date_of_commission;
	}

	public String getDate_of_seniority() {
		return date_of_seniority;
	}

	public void setDate_of_seniority(String date_of_seniority) {
		this.date_of_seniority = date_of_seniority;
	}

	public String getUnit_sus_no() {
		return unit_sus_no;
	}

	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public View_Table(String unit_name, String command, String division, String brigade, String corps) {
		super();
		this.unit_name = unit_name;
		this.command = command;
		this.division = division;
		this.brigade = brigade;
		this.corps = corps;

	}

	public View_Table() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "View_Table [unit_name=" + unit_name + ", command=" + command + ", division=" + division + ", brigade="
				+ brigade + ", corps=" + corps + ", personnel_no=" + personnel_no + ", name=" + name + ", description="
				+ description + ", date_of_birth=" + date_of_birth + ", date_of_commission=" + date_of_commission
				+ ", date_of_seniority=" + date_of_seniority + ", unit_sus_no=" + unit_sus_no + ", id=" + id + "]";
	}

}
