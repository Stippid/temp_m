package com.models.psg.Transaction;
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
@Table(name = "tb_psg_census_cadet", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_CADET {
	private int id;
	private int pre_cadet_status;
	private String designation;
	private String employee_name;
	private String gazetted;
	private String civil_service;
	private Date from_date;
	private Date to_date;
	private String army_no;
	private String total_service;
	private String unit_reg;
	private int census_id;
	private BigInteger comm_id;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	private int competency;
	private String competency_other;
	private int status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getPre_cadet_status() {
		return pre_cadet_status;
	}
	public void setPre_cadet_status(int pre_cadet_status) {
		this.pre_cadet_status = pre_cadet_status;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getGazetted() {
		return gazetted;
	}
	public void setGazetted(String gazetted) {
		this.gazetted = gazetted;
	}
	public String getCivil_service() {
		return civil_service;
	}
	public void setCivil_service(String civil_service) {
		this.civil_service = civil_service;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getArmy_no() {
		return army_no;
	}
	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}
	public String getUnit_reg() {
		return unit_reg;
	}
	public void setUnit_reg(String unit_reg) {
		this.unit_reg = unit_reg;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	public String getTotal_service() {
		return total_service;
	}
	public void setTotal_service(String total_service) {
		this.total_service = total_service;
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
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCompetency() {
		return competency;
	}
	public void setCompetency(int competency) {
		this.competency = competency;
	}
	public String getCompetency_other() {
		return competency_other;
	}
	public void setCompetency_other(String competency_other) {
		this.competency_other = competency_other;
	}
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	
	
	
}
