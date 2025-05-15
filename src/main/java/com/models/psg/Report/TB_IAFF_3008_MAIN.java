package com.models.psg.Report;

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
@Table(name = "tb_psg_iaff_3008_main", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_IAFF_3008_MAIN {

	private int id;
	private String month;
	private int year;
	/*private String present_return_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date present_return_dt;
	private String last_return_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date last_return_dt;*/
	private String sus_no;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private int status;
	private String command;
	private String corp;
	private String div;
	private String brigade;
	private String establish_no;
	private String location;
	private String type_of_location;
	private String parent_type;
	private int version;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	/*public String getPresent_return_no() {
		return present_return_no;
	}
	public void setPresent_return_no(String present_return_no) {
		this.present_return_no = present_return_no;
	}
	public Date getPresent_return_dt() {
		return present_return_dt;
	}
	public void setPresent_return_dt(Date present_return_dt) {
		this.present_return_dt = present_return_dt;
	}
	public String getLast_return_no() {
		return last_return_no;
	}
	public void setLast_return_no(String last_return_no) {
		this.last_return_no = last_return_no;
	}
	public Date getLast_return_dt() {
		return last_return_dt;
	}
	public void setLast_return_dt(Date last_return_dt) {
		this.last_return_dt = last_return_dt;
	}*/
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getBrigade() {
		return brigade;
	}
	public void setBrigade(String brigade) {
		this.brigade = brigade;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType_of_location() {
		return type_of_location;
	}
	public void setType_of_location(String type_of_location) {
		this.type_of_location = type_of_location;
	}
	public String getParent_type() {
		return parent_type;
	}
	public void setParent_type(String parent_type) {
		this.parent_type = parent_type;
	}
	public String getEstablish_no() {
		return establish_no;
	}
	public void setEstablish_no(String establish_no) {
		this.establish_no = establish_no;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
