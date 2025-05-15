package com.models.psg.Transaction;

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
@Table(name = "tb_psg_trans_proposed_comm_letter", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_TRANS_PROPOSED_COMM_LETTER {

	private BigInteger id;
	private String authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_authority;
	private String personnel_no;
	private String cadet_no;
	private String batch_no;
	private int course;
	private String name;
	private int gender;
	private int type_of_comm_granted;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_commission;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_seniority;
	private int rank;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_rank;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth;
	private String parent_arm;
	private String regiment;
	private String unit_sus_no;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_tos;
	private int update_comm_status;
	private String reject_remarks;
	private String parent_unit;
	private String parent_sus_no;
	private String tnai_no;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}
	public String getPersonnel_no() {
		return personnel_no;
	}
	public void setPersonnel_no(String personnel_no) {
		this.personnel_no = personnel_no;
	}
	public String getCadet_no() {
		return cadet_no;
	}
	public void setCadet_no(String cadet_no) {
		this.cadet_no = cadet_no;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getType_of_comm_granted() {
		return type_of_comm_granted;
	}
	public void setType_of_comm_granted(int type_of_comm_granted) {
		this.type_of_comm_granted = type_of_comm_granted;
	}
	public Date getDate_of_commission() {
		return date_of_commission;
	}
	public void setDate_of_commission(Date date_of_commission) {
		this.date_of_commission = date_of_commission;
	}
	public Date getDate_of_seniority() {
		return date_of_seniority;
	}
	public void setDate_of_seniority(Date date_of_seniority) {
		this.date_of_seniority = date_of_seniority;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getDate_of_rank() {
		return date_of_rank;
	}
	public void setDate_of_rank(Date date_of_rank) {
		this.date_of_rank = date_of_rank;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getParent_arm() {
		return parent_arm;
	}
	public void setParent_arm(String parent_arm) {
		this.parent_arm = parent_arm;
	}
	
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegiment() {
		return regiment;
	}
	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}
	public Date getDate_of_tos() {
		return date_of_tos;
	}
	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
	}
	public int getUpdate_comm_status() {
		return update_comm_status;
	}
	public void setUpdate_comm_status(int update_comm_status) {
		this.update_comm_status = update_comm_status;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getParent_unit() {
		return parent_unit;
	}
	public void setParent_unit(String parent_unit) {
		this.parent_unit = parent_unit;
	}
	public String getParent_sus_no() {
		return parent_sus_no;
	}
	public void setParent_sus_no(String parent_sus_no) {
		this.parent_sus_no = parent_sus_no;
	}


	public String getTnai_no() {
		return tnai_no;
	}
	public void setTnai_no(String tnai_no) {
		this.tnai_no = tnai_no;
	}

}
