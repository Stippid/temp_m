package com.models.mnh;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_imb", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Imb {
	private int id;
    //private String unit_name;
    private String persnl_no;
 
    private String category;
    private int age;
    private String name; 
    private int service_years; 
    private String persnl_unit;
    private String trade; 
    private String fmn;
    private String icd_nature1;
    private String icd_cause1 ;
    private String icd_nature2;
    private String icd_cause2;
    private String icd_nature3;
    private String icd_cause3;
    private String diagnosis_remarks;
    private Date date_imb;
    private int  percent;
    private Date date_place_origine ;
    private String attributable_aggravated;
    private String sus_no;
    private int roleid;
    private String icd_code;
    private String icd_cause;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private Date date_of_birth;
    private BigInteger adhar_card_no;
	private BigInteger contact_no;
	private String relation;
	private String sex;
	 private String service; 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * public String getUnit_name() { return unit_name; } public void
	 * setUnit_name(String unit_name) { this.unit_name = unit_name; }
	 */
	@Column(length = 20)
	public String getPersnl_no() {
		return persnl_no;
	}
	public void setPersnl_no(String persnl_no) {
		this.persnl_no = persnl_no;
	}
	
	@Column(length = 10)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Column(length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getService_years() {
		return service_years;
	}
	public void setService_years(int service_years) {
		this.service_years = service_years;
	}
	@Column(length = 100)
	public String getPersnl_unit() {
		return persnl_unit;
	}
	public void setPersnl_unit(String persnl_unit) {
		this.persnl_unit = persnl_unit;
	}
	@Column(length = 50)
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	@Column(length = 50)
	public String getFmn() {
		return fmn;
	}
	public void setFmn(String fmn) {
		this.fmn = fmn;
	}
	@Column(length = 10)
	public String getIcd_nature1() {
		return icd_nature1;
	}
	public void setIcd_nature1(String icd_nature1) {
		this.icd_nature1 = icd_nature1;
	}
	@Column(length = 10)
	public String getIcd_cause1() {
		return icd_cause1;
	}
	public void setIcd_cause1(String icd_cause1) {
		this.icd_cause1 = icd_cause1;
	}
	@Column(length = 10)
	public String getIcd_nature2() {
		return icd_nature2;
	}
	
	public void setIcd_nature2(String icd_nature2) {
		this.icd_nature2 = icd_nature2;
	}
	@Column(length = 10)
	public String getIcd_cause2() {
		return icd_cause2;
	}
	public void setIcd_cause2(String icd_cause2) {
		this.icd_cause2 = icd_cause2;
	}
	@Column(length = 10)
	public String getIcd_nature3() {
		return icd_nature3;
	}
	public void setIcd_nature3(String icd_nature3) {
		this.icd_nature3 = icd_nature3;
	}
	@Column(length = 10)
	public String getIcd_cause3() {
		return icd_cause3;
	}
	public void setIcd_cause3(String icd_cause3) {
		this.icd_cause3 = icd_cause3;
	}
	@Column(length = 250)
	public String getDiagnosis_remarks() {
		return diagnosis_remarks;
	}
	public void setDiagnosis_remarks(String diagnosis_remarks) {
		this.diagnosis_remarks = diagnosis_remarks;
	}
	public Date getDate_imb() {
		return date_imb;
	}
	public void setDate_imb(Date date_imb) {
		this.date_imb = date_imb;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public Date getDate_place_origine() {
		return date_place_origine;
	}
	public void setDate_place_origine(Date date_place_origine) {
		this.date_place_origine = date_place_origine;
	}
	@Column(length = 50)
	public String getAttributable_aggravated() {
		return attributable_aggravated;
	}
	public void setAttributable_aggravated(String attributable_aggravated) {
		this.attributable_aggravated = attributable_aggravated;
	}
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	@Column(length = 255)
	public String getIcd_code() {
		return icd_code;
	}
	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}
	@Column(length = 255)
	public String getIcd_cause() {
		return icd_cause;
	}
	public void setIcd_cause(String icd_cause) {
		this.icd_cause = icd_cause;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public BigInteger getAdhar_card_no() {
		return adhar_card_no;
	}
	public void setAdhar_card_no(BigInteger adhar_card_no) {
		this.adhar_card_no = adhar_card_no;
	}
	public BigInteger getContact_no() {
		return contact_no;
	}
	public void setContact_no(BigInteger contact_no) {
		this.contact_no = contact_no;
	}
	
	
	 public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}


	private Tb_Med_RankCode invalid_rank;
	   @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "rank", nullable = false)
		public Tb_Med_RankCode getInvalid_rank() {
			return invalid_rank;
		}
		public void setInvalid_rank(Tb_Med_RankCode invalid_rank) {
			this.invalid_rank = invalid_rank;
		}
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
	
	
}