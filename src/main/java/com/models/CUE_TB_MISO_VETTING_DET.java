package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_vetting_det", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_VETTING_DET 
{	
	private int id;	
	private String vetted_by;
	private String vetted_on;	
	private String we_pe_no;
	private String we_pe;	
	private String type;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	


	public String getVetted_by() {
		return vetted_by;
	}


	public void setVetted_by(String vetted_by) {
		this.vetted_by = vetted_by;
	}


	public String getVetted_on() {
		return vetted_on;
	}


	public void setVetted_on(String vetted_on) {
		this.vetted_on = vetted_on;
	}


	public String getWe_pe_no() {
		return we_pe_no;
	}


	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}


	public String getWe_pe() {
		return we_pe;
	}


	public void setWe_pe(String we_pe) {
		this.we_pe = we_pe;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
}
