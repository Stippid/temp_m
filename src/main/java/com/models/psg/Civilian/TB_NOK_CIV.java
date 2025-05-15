package com.models.psg.Civilian;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_civilian_nok_civ", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_NOK_CIV {
	
	private int id;
	private int civ_id;	
	private String authority;
	private Date date_authority;		
	private String nok_name;
	private String nok_mobile_no;
	private int nok_relation;
	private String nok_village;
	private int nok_state;
	private int nok_district;
	private int nok_tehsil;
	private int nok_pin;
	private int nok_country;
	private String nok_near_railway_station ;
	private String ctry_other ;
	private String st_other ;
	private String dist_other ;
	private String tehsil_other ;
	private String relation_other ;
	private String nok_rural_urban_semi;
	private int status;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String initiated_from;
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}	
	Date created_date;
	String created_by;
	Date modified_date;
	String modified_by;
	private String reject_remarks;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNok_name() {
		return nok_name;
	}
	public void setNok_name(String nok_name) {
		this.nok_name = nok_name;
	}
	public int getNok_relation() {
		return nok_relation;
	}
	public void setNok_relation(int nok_relation) {
		this.nok_relation = nok_relation;
	}
	public String getNok_village() {
		return nok_village;
	}
	public void setNok_village(String nok_village) {
		this.nok_village = nok_village;
	}
	public int getNok_state() {
		return nok_state;
	}
	public void setNok_state(int nok_state) {
		this.nok_state = nok_state;
	}
	public int getNok_district() {
		return nok_district;
	}
	public void setNok_district(int nok_district) {
		this.nok_district = nok_district;
	}
	public int getNok_tehsil() {
		return nok_tehsil;
	}
	public void setNok_tehsil(int nok_tehsil) {
		this.nok_tehsil = nok_tehsil;
	}
	public int getNok_pin() {
		return nok_pin;
	}
	public void setNok_pin(int nok_pin) {
		this.nok_pin = nok_pin;
	}
	public String getNok_near_railway_station() {
		return nok_near_railway_station;
	}
	public void setNok_near_railway_station(String nok_near_railway_station) {
		this.nok_near_railway_station = nok_near_railway_station;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_authority() {
		return date_authority;
	}
	public void setDate_authority(Date date_authority) {
		this.date_authority = date_authority;
	}
	public String getNok_rural_urban_semi() {
		return nok_rural_urban_semi;
	}
	public void setNok_rural_urban_semi(String nok_rural_urban_semi) {
		this.nok_rural_urban_semi = nok_rural_urban_semi;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNok_country() {
		return nok_country;
	}
	public void setNok_country(int nok_country) {
		this.nok_country = nok_country;
	}
	public String getCtry_other() {
		return ctry_other;
	}
	public void setCtry_other(String ctry_other) {
		this.ctry_other = ctry_other;
	}
	public String getSt_other() {
		return st_other;
	}
	public void setSt_other(String st_other) {
		this.st_other = st_other;
	}
	public String getDist_other() {
		return dist_other;
	}
	public void setDist_other(String dist_other) {
		this.dist_other = dist_other;
	}
	public String getTehsil_other() {
		return tehsil_other;
	}
	public void setTehsil_other(String tehsil_other) {
		this.tehsil_other = tehsil_other;
	}
	public String getRelation_other() {
		return relation_other;
	}
	public void setRelation_other(String relation_other) {
		this.relation_other = relation_other;
	}
	public int getCiv_id() {
		return civ_id;
	}
	public void setCiv_id(int civ_id) {
		this.civ_id = civ_id;
	}
	public String getInitiated_from() {
		return initiated_from;
	}
	public void setInitiated_from(String initiated_from) {
		this.initiated_from = initiated_from;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	@Column
	@ColumnTransformer(
		    read =  "pgp_sym_decrypt(" +
		            "    nok_mobile_no::bytea, " +
		            "current_setting('miso.version')" +
		            ")",
		    write = "pgp_sym_encrypt( " +
		            "    ?, " +
		            "current_setting('miso.version')" +
		            ") "
		)
	public String getNok_mobile_no() {
		return nok_mobile_no;
	}
	public void setNok_mobile_no(String nok_mobile_no) {
		this.nok_mobile_no = nok_mobile_no;
	}
}
