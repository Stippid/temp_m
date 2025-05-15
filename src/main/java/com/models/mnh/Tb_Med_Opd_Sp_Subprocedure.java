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


@Entity
@Table(name = "tb_med_opd_sp_subprocedure", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Opd_Sp_Subprocedure {
	
	private int id;
	//private int dept_id;
	private int proc_id;
	private String subproc_name;
	private String status;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;

  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}*/
	public int getProc_id() {
		return proc_id;
	}
	public void setProc_id(int proc_id) {
		this.proc_id = proc_id;
	}
	@Column(length = 100)
	public String getSubproc_name() {
		return subproc_name;
	}
	public void setSubproc_name(String subproc_name) {
		this.subproc_name = subproc_name;
	}
	@Column(length = 20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	/*private Tb_Med_Opd_Sp_Procedure_master procedure;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proc_id", nullable = false)
	public Tb_Med_Opd_Sp_Procedure_master getProcedure() {
		return procedure;
	}
	public void setProcedure(Tb_Med_Opd_Sp_Procedure_master procedure) {
		this.procedure = procedure;
	}*/
	
	
	private Tb_Med_Opd_Sp_Department department_subproc;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id", nullable = false)
	public Tb_Med_Opd_Sp_Department getDepartment_subproc() {
		return department_subproc;
	}
	public void setDepartment_subproc(Tb_Med_Opd_Sp_Department department_subproc) {
		this.department_subproc = department_subproc;
	}

	
}