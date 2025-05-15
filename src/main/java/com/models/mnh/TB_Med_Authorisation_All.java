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
@Table(name = "tb_med_authorisation_all", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_Med_Authorisation_All {
	
  private int id ;
 // private int sr_no;
  private String sus_no;
  //private String unit_name;
  private String command;
  private int off_auth;
  private int jco_or_auth;
  private int off_fam_auth;
  private int jco_or_fam_auth;
  private int total_all;
  private int laid_out;
  private int others;
  private String modified_by;
  private Date modified_on;
  
  
private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_on; 


	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
	  return id;
    }
    public void setId(int id) {
    	this.id = id;
    }

	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	/*@Column(length = 100)
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}*/
	@Column(length = 50)
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getOff_auth() {
		return off_auth;
	}
	public void setOff_auth(int off_auth) {
		this.off_auth = off_auth;
	}
	public int getJco_or_auth() {
		return jco_or_auth;
	}
	public void setJco_or_auth(int jco_or_auth) {
		this.jco_or_auth = jco_or_auth;
	}
	public int getOff_fam_auth() {
		return off_fam_auth;
	}
	public void setOff_fam_auth(int off_fam_auth) {
		this.off_fam_auth = off_fam_auth;
	}
	public int getJco_or_fam_auth() {
		return jco_or_fam_auth;
	}
	public void setJco_or_fam_auth(int jco_or_fam_auth) {
		this.jco_or_fam_auth = jco_or_fam_auth;
	}
	public int getTotal_all() {
		return total_all;
	}
	public void setTotal_all(int total_all) {
		this.total_all = total_all;
	}	
    public int getLaid_out() {
	return laid_out;
    }
   public void setLaid_out(int laid_out) {
	this.laid_out = laid_out;
}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
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
	
	
	
}