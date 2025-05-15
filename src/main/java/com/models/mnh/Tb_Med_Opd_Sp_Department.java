package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_opd_sp_department", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Opd_Sp_Department {
	
	private int id;
	private String dept_name;
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
	@Column(length =100)
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Column(length =20)
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
	
	private Set<Tb_Med_Opd_Sp_Procedure_master> proc = new HashSet<Tb_Med_Opd_Sp_Procedure_master>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Tb_Med_Opd_Sp_Procedure_master> getProc() {
		return proc;
	}
	public void setProc(Set<Tb_Med_Opd_Sp_Procedure_master> proc) {
		this.proc = proc;
	}
	
	private Set<Tb_Med_Opd_Sp_Subprocedure> dept_subproc = new HashSet<Tb_Med_Opd_Sp_Subprocedure>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department_subproc")
	public Set<Tb_Med_Opd_Sp_Subprocedure> getDept_subproc() {
		return dept_subproc;
	}
	public void setDept_subproc(Set<Tb_Med_Opd_Sp_Subprocedure> dept_subproc) {
		this.dept_subproc = dept_subproc;
	}
	
	
	private Set<Med_OPD_Investigation_Model> dept_opdspl = new HashSet<Med_OPD_Investigation_Model>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department_opd_spl_cases")
	public Set<Med_OPD_Investigation_Model> getDept_opdspl() {
		return dept_opdspl;
	}
	public void setDept_opdspl(Set<Med_OPD_Investigation_Model> dept_opdspl) {
		this.dept_opdspl = dept_opdspl;
	}
	
	
	
}