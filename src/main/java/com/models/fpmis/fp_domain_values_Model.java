package com.models.fpmis;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "fp.fp_domain_values", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_domain_values_Model {
	
	private int id;
	private String domainid;
	private String codevalue;
	private String label;
	private String label_s;
	private int disp_order;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomainid() {
		return domainid;
	}
	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel_s() {
		return label_s;
	}
	public void setLabel_s(String label_s) {
		this.label_s = label_s;
	}
	public int getDisp_order() {
		return disp_order;
	}
	public void setDisp_order(int disp_order) {
		this.disp_order = disp_order;
	}

}