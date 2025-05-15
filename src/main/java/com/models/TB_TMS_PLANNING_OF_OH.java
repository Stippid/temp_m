package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_planning_of_oh", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_PLANNING_OF_OH {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String mct_main_id;
	private String  veh_cat;
	private String  type_of_veh; 
	private int  total_tgt;
	private int  service_mode;
	public int getTotal_tgt_eme() {
		return total_tgt_eme;
	}
	public void setTotal_tgt_eme(int total_tgt_eme) {
		this.total_tgt_eme = total_tgt_eme;
	}
	public int getService_mode_eme() {
		return service_mode_eme;
	}
	public void setService_mode_eme(int service_mode_eme) {
		this.service_mode_eme = service_mode_eme;
	}
	public int getIndustry_mode_eme() {
		return industry_mode_eme;
	}
	public void setIndustry_mode_eme(int industry_mode_eme) {
		this.industry_mode_eme = industry_mode_eme;
	}
	public int getTotal_tgt_os() {
		return total_tgt_os;
	}
	public void setTotal_tgt_os(int total_tgt_os) {
		this.total_tgt_os = total_tgt_os;
	}
	public int getService_mode_os() {
		return service_mode_os;
	}
	public void setService_mode_os(int service_mode_os) {
		this.service_mode_os = service_mode_os;
	}
	public int getIndustry_mode_os() {
		return industry_mode_os;
	}
	public void setIndustry_mode_os(int industry_mode_os) {
		this.industry_mode_os = industry_mode_os;
	}
	private int  industry_mode;
	private int status;
	private String line_dte;
	private int  total_tgt_mgs;
	private int  service_mode_mgs;
	private int  industry_mode_mgs;
	private int  py;
	
	private int  total_tgt_eme;
	private int  service_mode_eme;
	private int  industry_mode_eme;
	
	private int  total_tgt_os;
	private int  service_mode_os;
	private int  industry_mode_os;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMct_main_id() {
		return mct_main_id;
	}
	public void setMct_main_id(String mct_main_id) {
		this.mct_main_id = mct_main_id;
	}
	public String getVeh_cat() {
		return veh_cat;
	}
	public void setVeh_cat(String veh_cat) {
		this.veh_cat = veh_cat;
	}
	public String getType_of_veh() {
		return type_of_veh;
	}
	public void setType_of_veh(String type_of_veh) {
		this.type_of_veh = type_of_veh;
	}
	public int getTotal_tgt() {
		return total_tgt;
	}
	public void setTotal_tgt(int total_tgt) {
		this.total_tgt = total_tgt;
	}
	public int getService_mode() {
		return service_mode;
	}
	public void setService_mode(int service_mode) {
		this.service_mode = service_mode;
	}
	public int getIndustry_mode() {
		return industry_mode;
	}
	public void setIndustry_mode(int industry_mode) {
		this.industry_mode = industry_mode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLine_dte() {
		return line_dte;
	}
	public void setLine_dte(String line_dte) {
		this.line_dte = line_dte;
	}
	public int getTotal_tgt_mgs() {
		return total_tgt_mgs;
	}
	public void setTotal_tgt_mgs(int total_tgt_mgs) {
		this.total_tgt_mgs = total_tgt_mgs;
	}
	public int getService_mode_mgs() {
		return service_mode_mgs;
	}
	public void setService_mode_mgs(int service_mode_mgs) {
		this.service_mode_mgs = service_mode_mgs;
	}
	public int getIndustry_mode_mgs() {
		return industry_mode_mgs;
	}
	public void setIndustry_mode_mgs(int industry_mode_mgs) {
		this.industry_mode_mgs = industry_mode_mgs;
	}
	
	public TB_TMS_PLANNING_OF_OH() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPy() {
		return py;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public TB_TMS_PLANNING_OF_OH(int id, String mct_main_id, String veh_cat, String type_of_veh, int total_tgt,
			int service_mode, int industry_mode, int status, String line_dte, int total_tgt_mgs, int service_mode_mgs,
			int industry_mode_mgs, int py, int total_tgt_eme, int service_mode_eme, int industry_mode_eme,
			int total_tgt_os, int service_mode_os, int industry_mode_os) {
		super();
		this.id = id;
		this.mct_main_id = mct_main_id;
		this.veh_cat = veh_cat;
		this.type_of_veh = type_of_veh;
		this.total_tgt = total_tgt;
		this.service_mode = service_mode;
		this.industry_mode = industry_mode;
		this.status = status;
		this.line_dte = line_dte;
		this.total_tgt_mgs = total_tgt_mgs;
		this.service_mode_mgs = service_mode_mgs;
		this.industry_mode_mgs = industry_mode_mgs;
		this.py = py;
		this.total_tgt_eme = total_tgt_eme;
		this.service_mode_eme = service_mode_eme;
		this.industry_mode_eme = industry_mode_eme;
		this.total_tgt_os = total_tgt_os;
		this.service_mode_os = service_mode_os;
		this.industry_mode_os = industry_mode_os;
	}
	
	
	
}
