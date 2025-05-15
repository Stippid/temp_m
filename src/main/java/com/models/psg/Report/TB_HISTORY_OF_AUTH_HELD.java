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
@Table(name = "tb_psg_history_of_auth_held", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_HISTORY_OF_AUTH_HELD {

	private int id;
	private int rank_auth;
	private int base_auth;
	private int mod_auth;
	private int foot_auth;
	private int total_auth;
	private int total_held;	
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
	public int getRank_auth() {
		return rank_auth;
	}
	public void setRank_auth(int rank_auth) {
		this.rank_auth = rank_auth;
	}
	public int getBase_auth() {
		return base_auth;
	}
	public void setBase_auth(int base_auth) {
		this.base_auth = base_auth;
	}
	public int getMod_auth() {
		return mod_auth;
	}
	public void setMod_auth(int mod_auth) {
		this.mod_auth = mod_auth;
	}
	public int getFoot_auth() {
		return foot_auth;
	}
	public void setFoot_auth(int foot_auth) {
		this.foot_auth = foot_auth;
	}
	public int getTotal_auth() {
		return total_auth;
	}
	public void setTotal_auth(int total_auth) {
		this.total_auth = total_auth;
	}
	public int getTotal_held() {
		return total_held;
	}
	public void setTotal_held(int total_held) {
		this.total_held = total_held;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	
}
