package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_prf_group_mst", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_PRF_Mst {
	
	private int id;
	private String prf_group;
	private String coss_section;
	private String nodal_dte;
	private String created_by;
	private String created_on;
	private String status;
	private String prf_group_code;
	private String status_active;
	private String remarks;
	
	private int roleid;
	  public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}
	public String getCoss_section() {
		return coss_section;
	}
	public void setCoss_section(String coss_section) {
		this.coss_section = coss_section;
	}
	public String getNodal_dte() {
		return nodal_dte;
	}
	public void setNodal_dte(String nodal_dte) {
		this.nodal_dte = nodal_dte;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrf_group_code() {
		return prf_group_code;
	}
	public void setPrf_group_code(String prf_group_code) {
		this.prf_group_code = prf_group_code;
	}
	public String getStatus_active() {
		return status_active;
	}
	public void setStatus_active(String status_active) {
		this.status_active = status_active;
	}
	private String modified_by;
	private String modified_on;
	
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
