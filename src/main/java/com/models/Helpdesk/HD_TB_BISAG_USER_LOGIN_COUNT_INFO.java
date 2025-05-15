package com.models.Helpdesk;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "userlogincountinfo", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})

public class HD_TB_BISAG_USER_LOGIN_COUNT_INFO {
	
	private int id ;
	private int userid ;
	private String status ;
	private String loginstatus;
	private Date createddate ;
	
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}
	public Date getCreatedDate() {
		return createddate;
	}
	public void setCreatedDate(Date createddate) {
		this.createddate = createddate;
	}
}
