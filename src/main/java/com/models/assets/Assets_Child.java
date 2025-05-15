package com.models.assets;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "tb_assets_child", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})

public class Assets_Child {
	private int id;
	private Date warranty;
	private int service_state=0;
	private int unservice_state=0;
	private int p_id;
	private int status;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private String warrantycheck;

	private Date unsv_from_dt;
	private Date unsv_to_dt;


	private String ip_address;
	private String antiviruscheck;
	private int antivirus=0;
	private int ram_capi=0;
	private int hdd_capi=0;
	private int operating_system=0;
	private int office=0;
	private int os_patch=0;
	private int dply_envt;
	private int ram_slot_qty=0;
	private String b_head;
	private String b_code;
	private String b_cost;
	private String cd_drive;

	private Date last_format_done;
	private Date last_cyber_audit_date;
	private Date upgrade_repair_date;

	private String usernameID;
	private String domain_username;
	private String details_of_auth;
	private String reason_for_formatting;
	private String type_of_audit;
	private String audit_done_by;
	private String type_upgrade_repair;
	private String comp_upgrade_repair;
	private String comp_other;

	public Date getUnsv_from_dt() {
		return unsv_from_dt;
	}
	public void setUnsv_from_dt(Date unsv_from_dt) {
		this.unsv_from_dt = unsv_from_dt;
	}
	public Date getUnsv_to_dt() {
		return unsv_to_dt;
	}
	public void setUnsv_to_dt(Date unsv_to_dt) {
		this.unsv_to_dt = unsv_to_dt;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}

	public int getService_state() {
		return service_state;
	}
	public void setService_state(int service_state) {
		this.service_state = service_state;
	}
	public int getUnservice_state() {
		return unservice_state;
	}
	public void setUnservice_state(int unservice_state) {
		this.unservice_state = unservice_state;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getAntiviruscheck() {
		return antiviruscheck;
	}
	public void setAntiviruscheck(String antiviruscheck) {
		this.antiviruscheck = antiviruscheck;
	}
	public int getAntivirus() {
		return antivirus;
	}
	public void setAntivirus(int antivirus) {
		this.antivirus = antivirus;
	}
	public int getRam_capi() {
		return ram_capi;
	}
	public void setRam_capi(int ram_capi) {
		this.ram_capi = ram_capi;
	}
	public int getHdd_capi() {
		return hdd_capi;
	}
	public void setHdd_capi(int hdd_capi) {
		this.hdd_capi = hdd_capi;
	}
	public int getOperating_system() {
		return operating_system;
	}
	public void setOperating_system(int operating_system) {
		this.operating_system = operating_system;
	}
	public int getOffice() {
		return office;
	}
	public void setOffice(int office) {
		this.office = office;
	}
	public int getOs_patch() {
		return os_patch;
	}
	public void setOs_patch(int os_patch) {
		this.os_patch = os_patch;
	}
	public int getDply_envt() {
		return dply_envt;
	}
	public void setDply_envt(int dply_envt) {
		this.dply_envt = dply_envt;
	}
	public int getRam_slot_qty() {
		return ram_slot_qty;
	}
	public void setRam_slot_qty(int ram_slot_qty) {
		this.ram_slot_qty = ram_slot_qty;
	}
	public String getB_head() {
		return b_head;
	}
	public void setB_head(String b_head) {
		this.b_head = b_head;
	}
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getB_cost() {
		return b_cost;
	}
	public void setB_cost(String b_cost) {
		this.b_cost = b_cost;
	}
	public String getCd_drive() {
		return cd_drive;
	}
	public void setCd_drive(String cd_drive) {
		this.cd_drive = cd_drive;
	}
	public Date getLast_format_done() {
		return last_format_done;
	}
	public void setLast_format_done(Date last_format_done) {
		this.last_format_done = last_format_done;
	}
	public Date getLast_cyber_audit_date() {
		return last_cyber_audit_date;
	}
	public void setLast_cyber_audit_date(Date last_cyber_audit_date) {
		this.last_cyber_audit_date = last_cyber_audit_date;
	}
	public Date getUpgrade_repair_date() {
		return upgrade_repair_date;
	}
	public void setUpgrade_repair_date(Date upgrade_repair_date) {
		this.upgrade_repair_date = upgrade_repair_date;
	}
	public String getUsernameID() {
		return usernameID;
	}
	public void setUsernameID(String usernameID) {
		this.usernameID = usernameID;
	}
	public String getDomain_username() {
		return domain_username;
	}
	public void setDomain_username(String domain_username) {
		this.domain_username = domain_username;
	}
	public String getDetails_of_auth() {
		return details_of_auth;
	}
	public void setDetails_of_auth(String details_of_auth) {
		this.details_of_auth = details_of_auth;
	}
	public String getReason_for_formatting() {
		return reason_for_formatting;
	}
	public void setReason_for_formatting(String reason_for_formatting) {
		this.reason_for_formatting = reason_for_formatting;
	}
	public String getType_of_audit() {
		return type_of_audit;
	}
	public void setType_of_audit(String type_of_audit) {
		this.type_of_audit = type_of_audit;
	}
	public String getAudit_done_by() {
		return audit_done_by;
	}
	public void setAudit_done_by(String audit_done_by) {
		this.audit_done_by = audit_done_by;
	}
	public String getType_upgrade_repair() {
		return type_upgrade_repair;
	}
	public void setType_upgrade_repair(String type_upgrade_repair) {
		this.type_upgrade_repair = type_upgrade_repair;
	}
	public String getComp_upgrade_repair() {
		return comp_upgrade_repair;
	}
	public void setComp_upgrade_repair(String comp_upgrade_repair) {
		this.comp_upgrade_repair = comp_upgrade_repair;
	}
	public String getComp_other() {
		return comp_other;
	}
	public void setComp_other(String comp_other) {
		this.comp_other = comp_other;
	}


}
