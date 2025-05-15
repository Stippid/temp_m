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
@Table(name = "tb_psg_history_of_reemployed", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_HISTORY_OF_REEMPLOYED {

	private int id;
	private int appt_re;
	private int rank_re;
	private String name_re;
	private String personnel_no_re;
	private int regiment_re;
	private Date dt_of_tos_re;
	private Date dt_of_birth_re;
	private Date dt_of_comm_re;
	private Date dt_of_senoirity_re;
	private Date dt_of_appt_re;
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
	public int getAppt_re() {
		return appt_re;
	}
	public void setAppt_re(int appt_re) {
		this.appt_re = appt_re;
	}
	public int getRank_re() {
		return rank_re;
	}
	public void setRank_re(int rank_re) {
		this.rank_re = rank_re;
	}
	public String getName_re() {
		return name_re;
	}
	public void setName_re(String name_re) {
		this.name_re = name_re;
	}
	public String getPersonnel_no_re() {
		return personnel_no_re;
	}
	public void setPersonnel_no_re(String personnel_no_re) {
		this.personnel_no_re = personnel_no_re;
	}
	public int getRegiment_re() {
		return regiment_re;
	}
	public void setRegiment_re(int regiment_re) {
		this.regiment_re = regiment_re;
	}
	public Date getDt_of_tos_re() {
		return dt_of_tos_re;
	}
	public void setDt_of_tos_re(Date dt_of_tos_re) {
		this.dt_of_tos_re = dt_of_tos_re;
	}
	public Date getDt_of_birth_re() {
		return dt_of_birth_re;
	}
	public void setDt_of_birth_re(Date dt_of_birth_re) {
		this.dt_of_birth_re = dt_of_birth_re;
	}
	public Date getDt_of_comm_re() {
		return dt_of_comm_re;
	}
	public void setDt_of_comm_re(Date dt_of_comm_re) {
		this.dt_of_comm_re = dt_of_comm_re;
	}
	public Date getDt_of_senoirity_re() {
		return dt_of_senoirity_re;
	}
	public void setDt_of_senoirity_re(Date dt_of_senoirity_re) {
		this.dt_of_senoirity_re = dt_of_senoirity_re;
	}
	public Date getDt_of_appt_re() {
		return dt_of_appt_re;
	}
	public void setDt_of_appt_re(Date dt_of_appt_re) {
		this.dt_of_appt_re = dt_of_appt_re;
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
