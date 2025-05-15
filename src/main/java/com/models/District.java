package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "district", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dtcode11")})
public class District {
	
	private int gid;
	private String stateId;
	private String distId;
	private String distName;
	
	@Column(name = "gid", unique = true, nullable = false)
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
		
	@Column(name = "stcode11")
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dtcode11", unique = true, nullable = false)
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
		
	@Column(name = "name11")
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
}
