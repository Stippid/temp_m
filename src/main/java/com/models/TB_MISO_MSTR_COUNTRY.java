package com.models;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_mstr_country", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_MSTR_COUNTRY {
	
	private int id;
	private String isoalpha2;
	private String isoalpha3;
	private String isonumeric;
	private String fips;
	private String country;
	private String capital;
	private String area;
	private String continent;
	
	
	public String getIsoalpha2() {
		return isoalpha2;
	}
	public void setIsoalpha2(String isoalpha2) {
		this.isoalpha2 = isoalpha2;
	}
	public String getIsoalpha3() {
		return isoalpha3;
	}
	public void setIsoalpha3(String isoalpha3) {
		this.isoalpha3 = isoalpha3;
	}
	public String getIsonumeric() {
		return isonumeric;
	}
	public void setIsonumeric(String isonumeric) {
		this.isonumeric = isonumeric;
	}
	public String getFips() {
		return fips;
	}
	public void setFips(String fips) {
		this.fips = fips;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
