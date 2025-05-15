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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_history_of_serving", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_HISTORY_OF_SERVING {

	private int id;
	private int appt_serv;
	private int rank_serv;
	private String name_serv;
	private String personnel_no_serv;
	private int regiment_serv;
	private Date dt_of_tos_serv;
	private Date dt_of_birth_serv;
	private Date dt_of_comm_serv;
	private Date dt_of_senoirity_serv;
	private Date dt_of_appt_serv;
	private BigInteger comm_id;
	private String present_return_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date present_return_dt;
	private String last_return_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date last_return_dt;
	private String sus_no;
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
	public int getAppt_serv() {
		return appt_serv;
	}
	public void setAppt_serv(int appt_serv) {
		this.appt_serv = appt_serv;
	}
	public int getRank_serv() {
		return rank_serv;
	}
	public void setRank_serv(int rank_serv) {
		this.rank_serv = rank_serv;
	}
	public String getName_serv() {
		return name_serv;
	}
	public void setName_serv(String name_serv) {
		this.name_serv = name_serv;
	}
	public String getPersonnel_no_serv() {
		return personnel_no_serv;
	}
	public void setPersonnel_no_serv(String personnel_no_serv) {
		this.personnel_no_serv = personnel_no_serv;
	}
	public int getRegiment_serv() {
		return regiment_serv;
	}
	public void setRegiment_serv(int regiment_serv) {
		this.regiment_serv = regiment_serv;
	}
	public Date getDt_of_tos_serv() {
		return dt_of_tos_serv;
	}
	public void setDt_of_tos_serv(Date dt_of_tos_serv) {
		this.dt_of_tos_serv = dt_of_tos_serv;
	}
	public Date getDt_of_birth_serv() {
		return dt_of_birth_serv;
	}
	public void setDt_of_birth_serv(Date dt_of_birth_serv) {
		this.dt_of_birth_serv = dt_of_birth_serv;
	}
	public Date getDt_of_comm_serv() {
		return dt_of_comm_serv;
	}
	public void setDt_of_comm_serv(Date dt_of_comm_serv) {
		this.dt_of_comm_serv = dt_of_comm_serv;
	}
	public Date getDt_of_senoirity_serv() {
		return dt_of_senoirity_serv;
	}
	public void setDt_of_senoirity_serv(Date dt_of_senoirity_serv) {
		this.dt_of_senoirity_serv = dt_of_senoirity_serv;
	}
	public Date getDt_of_appt_serv() {
		return dt_of_appt_serv;
	}
	public void setDt_of_appt_serv(Date dt_of_appt_serv) {
		this.dt_of_appt_serv = dt_of_appt_serv;
	}
	
	
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public String getPresent_return_no() {
		return present_return_no;
	}
	public void setPresent_return_no(String present_return_no) {
		this.present_return_no = present_return_no;
	}
	public Date getPresent_return_dt() {
		return present_return_dt;
	}
	public void setPresent_return_dt(Date present_return_dt) {
		this.present_return_dt = present_return_dt;
	}
	public String getLast_return_no() {
		return last_return_no;
	}
	public void setLast_return_no(String last_return_no) {
		this.last_return_no = last_return_no;
	}
	public Date getLast_return_dt() {
		return last_return_dt;
	}
	public void setLast_return_dt(Date last_return_dt) {
		this.last_return_dt = last_return_dt;
	}	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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

	/*public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getBrigade() {
		return brigade;
	}
	public void setBrigade(String brigade) {
		this.brigade = brigade;
	}*/
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
