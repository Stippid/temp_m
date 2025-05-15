package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_med_OPD_SPL", uniqueConstraints= {
	@UniqueConstraint(columnNames="id")})

public class Med_OPD_Investigation_Model {
	private int id;
	//private String unit_name;
	private String sus_no;
	//private String cmd;
	private String quater;
	private String yr;
	private String department;
	//private String procedure;
	//private String sub_procedure;
	private int opd_count;
	private int proc_id;
	//private int dept_id;
	private int subproc_id;
	
	private String record_status;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_on ;
	private String created_by;
	
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private Date modified_on;
	private  String modified_by ;
	
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private Date apprvd_at_unit_on;
	private String apprvr_at_unit_by;
	
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private Date apprvd_at_miso_on;
	private String apprvr_at_miso_by;

	/*@DateTimeFormat(pattern = "dd/MM/yyyy")*/
	private Date time_stamp;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 100)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	/*public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getSub_procedure() {
		return sub_procedure;
	}
	public void setSub_procedure(String sub_procedure) {
		this.sub_procedure = sub_procedure;
	}*/
	public int getOpd_count() {
		return opd_count;
	}
	public void setOpd_count(int opd_count) {
		this.opd_count = opd_count;
	}
	@Column(length = 10)
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getApprvd_at_unit_on() {
		return apprvd_at_unit_on;
	}
	public void setApprvd_at_unit_on(Date apprvd_at_unit_on) {
		this.apprvd_at_unit_on = apprvd_at_unit_on;
	}
	@Column(length = 35)
	public String getApprvr_at_unit_by() {
		return apprvr_at_unit_by;
	}
	public void setApprvr_at_unit_by(String apprvr_at_unit_by) {
		this.apprvr_at_unit_by = apprvr_at_unit_by;
	}
	public Date getApprvd_at_miso_on() {
		return apprvd_at_miso_on;
	}
	public void setApprvd_at_miso_on(Date apprvd_at_miso_on) {
		this.apprvd_at_miso_on = apprvd_at_miso_on;
	}
	@Column(length = 35)
	public String getApprvr_at_miso_by() {
		return apprvr_at_miso_by;
	}
	public void setApprvr_at_miso_by(String apprvr_at_miso_by) {
		this.apprvr_at_miso_by = apprvr_at_miso_by;
	}
	public Date getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}

	/*
	 * public String getUnit_name() { return unit_name; } public void
	 * setUnit_name(String unit_name) { this.unit_name = unit_name; }
	 */
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	/*
	 * public String getCmd() { return cmd; } public void setCmd(String cmd) {
	 * this.cmd = cmd; }
	 */
	@Column(length = 10)
	public String getQuater() {
		return quater;
	}
	public void setQuater(String quater) {
		this.quater = quater;
	}
	@Column(length = 4)
	public String getYr() {
		return yr;
	}
	public void setYr(String yr) {
		this.yr = yr;
	}
	/*public int getProc_id() {
		return proc_id;
	}
	public void setProc_id(int proc_id) {
		this.proc_id = proc_id;
	}*/
/*	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}*/
	/*public int getSubproc_id() {
		return subproc_id;
	}
	public void setSubproc_id(int subproc_id) {
		this.subproc_id = subproc_id;
	}*/

	
	public int getProc_id() {
		return proc_id;
	}
	public void setProc_id(int proc_id) {
		this.proc_id = proc_id;
	}
	public int getSubproc_id() {
		return subproc_id;
	}
	public void setSubproc_id(int subproc_id) {
		this.subproc_id = subproc_id;
	}

	
	/*private Tb_Med_Opd_Sp_Procedure_master procedure_opd_spl_cases;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proc_id", nullable = false)
	public Tb_Med_Opd_Sp_Procedure_master getProcedure_opd_spl_cases() {
		return procedure_opd_spl_cases;
	}
	public void setProcedure_opd_spl_cases(Tb_Med_Opd_Sp_Procedure_master procedure_opd_spl_cases) {
		this.procedure_opd_spl_cases = procedure_opd_spl_cases;
	}

	private Tb_Med_Opd_Sp_Subprocedure subprocedure_opd_spl_cases;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subproc_id", nullable = false)
	public Tb_Med_Opd_Sp_Subprocedure getSubprocedure_opd_spl_cases() {
		return subprocedure_opd_spl_cases;
	}
	public void setSubprocedure_opd_spl_cases(Tb_Med_Opd_Sp_Subprocedure subprocedure_opd_spl_cases) {
		this.subprocedure_opd_spl_cases = subprocedure_opd_spl_cases;
	}*/

	private Tb_Med_Opd_Sp_Department department_opd_spl_cases;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id", nullable = false)
	public Tb_Med_Opd_Sp_Department getDepartment_opd_spl_cases() {
		return department_opd_spl_cases;
	}
	public void setDepartment_opd_spl_cases(Tb_Med_Opd_Sp_Department department_opd_spl_cases) {
		this.department_opd_spl_cases = department_opd_spl_cases;
	}
	
	

	
	
	
}