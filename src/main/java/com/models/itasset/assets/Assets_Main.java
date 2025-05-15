package com.models.itasset.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_asset_main", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Assets_Main {
	
	@Id
	@GeneratedValue
	private int id;
	private int asset_type=0;
	private String model_number;
	private String machine_number;
	private String mac_address;
	private String ip_address;
	private int processor_type=0;
	private int ram_capi=0;
	private int hdd_capi=0;
	private int operating_system=0;
	private int office=0;
	private int os_patch=0;
	private int dply_envt;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	
	private String antiviruscheck;
	private String warrantycheck;
	private int antivirus=0;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date warranty;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date proc_date;
	private String b_head;
	private String b_code;
	private String b_cost;
	private String cd_drive;
	private String s_state;
	private int model_name=0;
	private int make_name=0;
	private int unserviceable_state=0;
	private int status=0;
	
	private String part_no;
	private int ethernet_port=0;
	private String dimension;
	private int connecting_tech=0;
	private int ram_slot_qty=0;
	private String sus_no;
	
	private Date unsv_from_dt;
	private Date unsv_to_dt;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(int asset_type) {
		this.asset_type = asset_type;
	}
	public String getModel_number() {
		return model_number;
	}
	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}
	public String getMachine_number() {
		return machine_number;
	}
	public void setMachine_number(String machine_number) {
		this.machine_number = machine_number;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public int getProcessor_type() {
		return processor_type;
	}
	public void setProcessor_type(int processor_type) {
		this.processor_type = processor_type;
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
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
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
	
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
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
	public String getS_state() {
		return s_state;
	}
	public void setS_state(String s_state) {
		this.s_state = s_state;
	}
	
	
	public int getModel_name() {
		return model_name;
	}
	public void setModel_name(int model_name) {
		this.model_name = model_name;
	}
	public int getMake_name() {
		return make_name;
	}
	public void setMake_name(int make_name) {
		this.make_name = make_name;
	}
	public int getUnserviceable_state() {
		return unserviceable_state;
	}
	public void setUnserviceable_state(int unserviceable_state) {
		this.unserviceable_state = unserviceable_state;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPart_no() {
		return part_no;
	}
	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}
	public int getEthernet_port() {
		return ethernet_port;
	}
	public void setEthernet_port(int ethernet_port) {
		this.ethernet_port = ethernet_port;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public int getConnecting_tech() {
		return connecting_tech;
	}
	public void setConnecting_tech(int connecting_tech) {
		this.connecting_tech = connecting_tech;
	}
	public int getRam_slot_qty() {
		return ram_slot_qty;
	}
	public void setRam_slot_qty(int ram_slot_qty) {
		this.ram_slot_qty = ram_slot_qty;
	}
	public Date getProc_date() {
		return proc_date;
	}
	public void setProc_date(Date proc_date) {
		this.proc_date = proc_date;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}
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

	
	
}
