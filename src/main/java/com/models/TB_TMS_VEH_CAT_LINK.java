package com.models;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "tb_tms_veh_cat_line_dte", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_VEH_CAT_LINK {
	
	
	
	private int id;
	private String	line_dte;
	private String veh_cat_id;
	private String veh_type_id;
	private String mct_code;
	private String arm_code;
	private String  sus_no ;
	private String  unit_name ;
	private String  type_veh;
	private String  new_nomencatre ;
    private String	mct_number;
    private String   prf_code;
	private String   creation_by;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date   creation_date ;
	private String modify_by ;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date modify_date ;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
			
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	

	public String getNew_nomencatre() {
		return new_nomencatre;
	}
	public void setNew_nomencatre(String new_nomencatre) {
		this.new_nomencatre = new_nomencatre;
	}

	public String getLine_dte() {
		return line_dte;
	}
	public void setLine_dte(String line_dte) {
		this.line_dte = line_dte;
	}
	public String getVeh_cat_id() {
		return veh_cat_id;
	}
	public void setVeh_cat_id(String veh_cat_id) {
		this.veh_cat_id = veh_cat_id;
	}
	public String getVeh_type_id() {
		return veh_type_id;
	}
	public void setVeh_type_id(String veh_type_id) {
		this.veh_type_id = veh_type_id;
	}
	public String getMct_code() {
		return mct_code;
	}
	public void setMct_code(String mct_code) {
		this.mct_code = mct_code;
	}
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public String getType_veh() {
	return type_veh;
}
public void setType_veh(String type_veh) {
	this.type_veh = type_veh;
}
public String getMct_number() {
	return mct_number;
}
public void setMct_number(String mct_number) {
	this.mct_number = mct_number;
}
public String getPrf_code() {
	return prf_code;
}
public void setPrf_code(String prf_code) {
	this.prf_code = prf_code;
}
}
