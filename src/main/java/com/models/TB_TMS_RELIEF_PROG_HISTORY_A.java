package com.models;

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
@Table(name = "tb_tms_relief_prog_history_a" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_RELIEF_PROG_HISTORY_A {
	
	private int id;
	private String ba_no;
	private BigInteger mct;
	private String std_nomclature;
	private String engine_no;
	private String chasis_no;
	private String classification;
	private String depot_name;
	private Date approve_date;
	private String from_unit_sus_no;
	//private String from_unit_name;
	private String to_unit_sus_no;
	//private String to_unit_name;
	private String auth;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	@Column(name = "ba_no")
	public String getBa_no()
	{
		return ba_no;
	}
	public void setBa_no(String ba_no)
	{
		this.ba_no = ba_no;
	}

	@Column(name = "mct")
	public BigInteger getMct()
	{
		return mct;
	}
	public void setMct(BigInteger mct)
	{
		this.mct = mct;
	}

	@Column(name = "std_nomclature")
	public String getStd_nomclature()
	{
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature)
	{
		this.std_nomclature = std_nomclature;
	}

	@Column(name = "engine_no")
	public String getEngine_no()
	{
		return engine_no;
	}
	public void setEngine_no(String engine_no)
	{
		this.engine_no = engine_no;
	}

	@Column(name = "chasis_no")
	public String getChasis_no()
	{
		return chasis_no;
	}
	public void setChasis_no(String chasis_no)
	{
		this.chasis_no = chasis_no;
	}

	@Column(name = "classification")
	public String getClassification()
	{
		return classification;
	}
	public void setClassification(String classification)
	{
		this.classification = classification;
	}

	@Column(name = "depot_name")
	public String getDepot_name()
	{
		return depot_name;
	}
	public void setDepot_name(String depot_name)
	{
		this.depot_name = depot_name;
	}

	@Column(name = "approve_date")
	public Date getApprove_date()
	{
		return approve_date;
	}
	public void setApprove_date(Date approve_date)
	{
		this.approve_date = approve_date;
	}
	
	@Column(name = "from_unit_sus_no")
	public String getFrom_unit_sus_no()
	{
		return from_unit_sus_no;
	}
	public void setFrom_unit_sus_no(String from_unit_sus_no)
	{
		this.from_unit_sus_no = from_unit_sus_no;
	}
	
	/*@Column(name = "from_unit_name")
	public String getFrom_unit_name()
	{
		return from_unit_name;
	}
	public void setFrom_unit_name(String from_unit_name)
	{
		this.from_unit_name = from_unit_name;
	}*/

	@Column(name = "to_unit_sus_no")
	public String getTo_unit_sus_no()
	{
		return to_unit_sus_no;
	}
	public void setTo_unit_sus_no(String to_unit_sus_no)
	{
		this.to_unit_sus_no = to_unit_sus_no;
	}	
	
	/*@Column(name = "to_unit_name")
	public String getTo_unit_name()
	{
		return to_unit_name;
	}
	public void setTo_unit_name(String to_unit_name)
	{
		this.to_unit_name = to_unit_name;
	}*/
	
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

}
