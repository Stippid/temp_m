package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_mstr_course", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_COURSE_TYPE {

	private int id;
	private String course_name;
	//private String course_type;
	private String course_gp;
	private String rank_type;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String status;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
//	public String getCourse_type() {
//		return course_type;
//	}
//	public void setCourse_type(String course_type) {
//		this.course_type = course_type;
//	}
	public String getCourse_gp() {
		return course_gp;
	}
	public void setCourse_gp(String course_gp) {
		this.course_gp = course_gp;
	}
	public String getRank_type() {
		return rank_type;
	}
	public void setRank_type(String rank_type) {
		this.rank_type = rank_type;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
