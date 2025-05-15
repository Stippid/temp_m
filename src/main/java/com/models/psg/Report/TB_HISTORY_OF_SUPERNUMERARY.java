package com.models.psg.Report;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_history_of_supernumerary", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_HISTORY_OF_SUPERNUMERARY {

	private int id;
	private int appt_super;
	private int rank_super;
	private String name_super;
	private String personnel_no_super;
	private int regiment_super;
	private Date dt_of_tos_super;
	private Date dt_of_birth_super;
	private Date dt_of_comm_super;
	private Date dt_of_senoirity_super;
	private Date dt_of_appt_super;
	private BigInteger comm_id;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private int main_id;
	private int version;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppt_super() {
		return appt_super;
	}
	public void setAppt_super(int appt_super) {
		this.appt_super = appt_super;
	}
	public int getRank_super() {
		return rank_super;
	}
	public void setRank_super(int rank_super) {
		this.rank_super = rank_super;
	}
	public String getName_super() {
		return name_super;
	}
	public void setName_super(String name_super) {
		this.name_super = name_super;
	}
	public String getPersonnel_no_super() {
		return personnel_no_super;
	}
	public void setPersonnel_no_super(String personnel_no_super) {
		this.personnel_no_super = personnel_no_super;
	}
	public int getRegiment_super() {
		return regiment_super;
	}
	public void setRegiment_super(int regiment_super) {
		this.regiment_super = regiment_super;
	}
	public Date getDt_of_tos_super() {
		return dt_of_tos_super;
	}
	public void setDt_of_tos_super(Date dt_of_tos_super) {
		this.dt_of_tos_super = dt_of_tos_super;
	}
	public Date getDt_of_birth_super() {
		return dt_of_birth_super;
	}
	public void setDt_of_birth_super(Date dt_of_birth_super) {
		this.dt_of_birth_super = dt_of_birth_super;
	}
	public Date getDt_of_comm_super() {
		return dt_of_comm_super;
	}
	public void setDt_of_comm_super(Date dt_of_comm_super) {
		this.dt_of_comm_super = dt_of_comm_super;
	}
	public Date getDt_of_senoirity_super() {
		return dt_of_senoirity_super;
	}
	public void setDt_of_senoirity_super(Date dt_of_senoirity_super) {
		this.dt_of_senoirity_super = dt_of_senoirity_super;
	}
	public Date getDt_of_appt_super() {
		return dt_of_appt_super;
	}
	public void setDt_of_appt_super(Date dt_of_appt_super) {
		this.dt_of_appt_super = dt_of_appt_super;
	}
	
	

	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}	
}
