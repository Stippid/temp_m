package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_orbat_mast_fmn", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class Tb_Miso_Orbat_Mast_Fmn {
	
	private int id;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;
	private String level8;
	private String level9;
	private String level10;
	private String fmn_code;
	private String fmn_name;
	private String arm_code;
	private String version_no;
	private String status_record;
	private String created_by;	
	private Date created_on;
	private String modified_by;	
	private Date modified_on;
	private String approved_rejected_by;
	private Date approved_rejected_on;
	
	
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	public String getStatus_record() {
		return status_record;
	}
	public void setStatus_record(String status_record) {
		this.status_record = status_record;
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
	
	
	public String getApproved_rejected_by() {
		return approved_rejected_by;
	}
	public void setApproved_rejected_by(String approved_rejected_by) {
		this.approved_rejected_by = approved_rejected_by;
	}
	
	
	public Date getApproved_rejected_on() {
		return approved_rejected_on;
	}
	public void setApproved_rejected_on(Date approved_rejected_on) {
		this.approved_rejected_on = approved_rejected_on;
	}
	
	
	
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	public String getLevel6() {
		return level6;
	}
	public void setLevel6(String level6) {
		this.level6 = level6;
	}
	public String getLevel7() {
		return level7;
	}
	public void setLevel7(String level7) {
		this.level7 = level7;
	}
	public String getLevel8() {
		return level8;
	}
	public void setLevel8(String level8) {
		this.level8 = level8;
	}
	public String getLevel9() {
		return level9;
	}
	public void setLevel9(String level9) {
		this.level9 = level9;
	}
	public String getLevel10() {
		return level10;
	}
	public void setLevel10(String level10) {
		this.level10 = level10;
	}
	public String getFmn_code() {
		return fmn_code;
	}
	public void setFmn_code(String fmn_code) {
		this.fmn_code = fmn_code;
	}
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getFmn_name() {
		return fmn_name;
	}
	public void setFmn_name(String fmn_name) {
		this.fmn_name = fmn_name;
	}
	
	
	
	

}
