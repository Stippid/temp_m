package com.models;

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
@Table(name = "tb_miso_orbat_comd_cont", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class TB_MISO_ORBAT_COMD_CONT {
	
	private int id;
	private String unit_sus_no;
	private String auth_letter_no;
	private String auth_letter_date;	
	private String ops_sus_no;
	private String is_sus_no;
	private String mil_sus_no;
	private String techcont_sus_no;
	private String discp_sus_no;
	private String local_adm_sus_no;
	private String loc_code;
	private String nrs_code;
	private String from_date;	
	private String to_date;
	private String arm_code;
	private int cmd_status;
	private int mo_status;
	private int sd9_status;
	private int sd4_status;
	private int sd5_status;
	private int sd9_distribute_status;	
	private Date distribute_date;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String cmd_approve_by;	
	private Date cmd_approved_date;
	private String mo_approve_by;
	private Date mo_approved_date;
	private String reject_by;
	private Date rejected_date;
	private String sd4_approve_by;
	private Date sd4_approved_date;
	private String sd9_approve_by;
	private Date sd9_approved_date;
	private String remarks;
	private String current_status;
	private String version_no;
	private String created_cmd_susno;
	private Date sd5_approved_date;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getOps_sus_no() {
		return ops_sus_no;
	}
	public void setOps_sus_no(String ops_sus_no) {
		this.ops_sus_no = ops_sus_no;
	}
	public String getIs_sus_no() {
		return is_sus_no;
	}
	public void setIs_sus_no(String is_sus_no) {
		this.is_sus_no = is_sus_no;
	}
	public String getMil_sus_no() {
		return mil_sus_no;
	}
	public void setMil_sus_no(String mil_sus_no) {
		this.mil_sus_no = mil_sus_no;
	}
	public String getTechcont_sus_no() {
		return techcont_sus_no;
	}
	public void setTechcont_sus_no(String techcont_sus_no) {
		this.techcont_sus_no = techcont_sus_no;
	}
	public String getDiscp_sus_no() {
		return discp_sus_no;
	}
	public void setDiscp_sus_no(String discp_sus_no) {
		this.discp_sus_no = discp_sus_no;
	}
	public String getLocal_adm_sus_no() {
		return local_adm_sus_no;
	}
	public void setLocal_adm_sus_no(String local_adm_sus_no) {
		this.local_adm_sus_no = local_adm_sus_no;
	}
	public String getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public int getCmd_status() {
		return cmd_status;
	}
	public void setCmd_status(int cmd_status) {
		this.cmd_status = cmd_status;
	}
	public int getMo_status() {
		return mo_status;
	}
	public void setMo_status(int mo_status) {
		this.mo_status = mo_status;
	}
	public int getSd9_status() {
		return sd9_status;
	}
	public void setSd9_status(int sd9_status) {
		this.sd9_status = sd9_status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getCmd_approve_by() {
		return cmd_approve_by;
	}
	public void setCmd_approve_by(String cmd_approve_by) {
		this.cmd_approve_by = cmd_approve_by;
	}
	public Date getCmd_approved_date() {
		return cmd_approved_date;
	}
	public void setCmd_approved_date(Date cmd_approved_date) {
		this.cmd_approved_date = cmd_approved_date;
	}
	
	public String getAuth_letter_no() {
		return auth_letter_no;
	}
	public void setAuth_letter_no(String auth_letter_no) {
		this.auth_letter_no = auth_letter_no;
	}

	public String getMo_approve_by() {
		return mo_approve_by;
	}
	public void setMo_approve_by(String mo_approve_by) {
		this.mo_approve_by = mo_approve_by;
	}
	public Date getMo_approved_date() {
		return mo_approved_date;
	}
	public void setMo_approved_date(Date mo_approved_date) {
		this.mo_approved_date = mo_approved_date;
	}

	public String getSd9_approve_by() {
		return sd9_approve_by;
	}
	public void setSd9_approve_by(String sd9_approve_by) {
		this.sd9_approve_by = sd9_approve_by;
	}
	public Date getSd9_approved_date() {
		return sd9_approved_date;
	}
	public void setSd9_approved_date(Date sd9_approved_date) {
		this.sd9_approved_date = sd9_approved_date;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getAuth_letter_date() {
		return auth_letter_date;
	}
	public void setAuth_letter_date(String auth_letter_date) {
		this.auth_letter_date = auth_letter_date;
	}
	public int getSd9_distribute_status() {
		return sd9_distribute_status;
	}
	public void setSd9_distribute_status(int sd9_distribute_status) {
		this.sd9_distribute_status = sd9_distribute_status;
	}
	public int getSd4_status() {
		return sd4_status;
	}
	public void setSd4_status(int sd4_status) {
		this.sd4_status = sd4_status;
	}
	public int getSd5_status() {
		return sd5_status;
	}
	public void setSd5_status(int sd5_status) {
		this.sd5_status = sd5_status;
	}
	public Date getDistribute_date() {
		return distribute_date;
	}
	public void setDistribute_date(Date distribute_date) {
		this.distribute_date = distribute_date;
	}
	public String getSd4_approve_by() {
		return sd4_approve_by;
	}
	public void setSd4_approve_by(String sd4_approve_by) {
		this.sd4_approve_by = sd4_approve_by;
	}
	public Date getSd4_approved_date() {
		return sd4_approved_date;
	}
	public void setSd4_approved_date(Date sd4_approved_date) {
		this.sd4_approved_date = sd4_approved_date;
	}

	public String getNrs_code() {
		return nrs_code;
	}
	public void setNrs_code(String nrs_code) {
		this.nrs_code = nrs_code;
	}
	public String getCreated_cmd_susno() {
		return created_cmd_susno;
	}
	public void setCreated_cmd_susno(String created_cmd_susno) {
		this.created_cmd_susno = created_cmd_susno;
	}
	public String getReject_by() {
		return reject_by;
	}
	public void setReject_by(String reject_by) {
		this.reject_by = reject_by;
	}
	public Date getRejected_date() {
		return rejected_date;
	}
	public void setRejected_date(Date rejected_date) {
		this.rejected_date = rejected_date;
	}
	public Date getSd5_approved_date() {
		return sd5_approved_date;
	}
	public void setSd5_approved_date(Date sd5_approved_date) {
		this.sd5_approved_date = sd5_approved_date;
	}
	
	
	

}
