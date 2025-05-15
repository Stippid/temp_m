package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "state", uniqueConstraints = {
		@UniqueConstraint(columnNames = "stcode11")})
public class State {
	
	private int gid;
	private String stname;
	private String stcode11;
		
	@Column(name = "gid", unique = true, nullable = false)
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	@Column(name = "stname", nullable = false)
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stcode11", unique = true, nullable = false)
	public String getStcode11() {
		return stcode11;
	}
	public void setStcode11(String stcode11) {
		this.stcode11 = stcode11;
	}
}
