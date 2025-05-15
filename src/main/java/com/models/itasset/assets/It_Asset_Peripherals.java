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
@Table(name = "it_asset_peripherals", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class It_Asset_Peripherals {
	
	private int id;
	private int assets_type=0;
	private int type_of_hw=0;
	private String proc_cost;
	private String model_no;
	private String machine_no;
	private String ip_address;
	private String type;
	private String remarks;
	private String monochrome_color;
	private String is_networked;
	
	
	private String print;
	private String scan;
	private String photography;
	private String colour;
	private String capacity;
	private String resolution;
	private String no_of_ports;
	private String ups_capacity;
	
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String paper_size;
	private String display_size;
	private String display_connector;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date warranty;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date proc_date;
	private String b_head;
	private String b_code;
	private String b_cost;
	private String s_state;
	private int model_name=0;
	private int make_name=0;
	private int unserviceable_state=0;
	private int status=0;
	private String connectivity_type;
	private String hw_interface;
	private int ethernet_port=0;
	private int management_layer=0;
	private String network_features;
	private String v_display_size;
	private String v_display_connector;
	private String sus_no;
	private String display_type;
	private String year_of_proc;
	private String year_of_manufacturing;
	
	
	private String warrantycheck;
	
	
	//**  AHM BISAG **//
	
	private Date unsv_from_dt;
	
	private Date unsv_to_dt;
	
	//** END AHM BISAG **//
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType_of_hw() {
		return type_of_hw;
	}
	public void setType_of_hw(int type_of_hw) {
		this.type_of_hw = type_of_hw;
	}
	public String getProc_cost() {
		return proc_cost;
	}
	public void setProc_cost(String proc_cost) {
		this.proc_cost = proc_cost;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public String getMachine_no() {
		return machine_no;
	}
	public void setMachine_no(String machine_no) {
		this.machine_no = machine_no;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMonochrome_color() {
		return monochrome_color;
	}
	public void setMonochrome_color(String monochrome_color) {
		this.monochrome_color = monochrome_color;
	}
	public String getIs_networked() {
		return is_networked;
	}
	public void setIs_networked(String is_networked) {
		this.is_networked = is_networked;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public String getScan() {
		return scan;
	}
	public void setScan(String scan) {
		this.scan = scan;
	}
	public String getPhotography() {
		return photography;
	}
	public void setPhotography(String photography) {
		this.photography = photography;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getNo_of_ports() {
		return no_of_ports;
	}
	public void setNo_of_ports(String no_of_ports) {
		this.no_of_ports = no_of_ports;
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
	public String getUps_capacity() {
		return ups_capacity;
	}
	public void setUps_capacity(String ups_capacity) {
		this.ups_capacity = ups_capacity;
	}
	public int getAssets_type() {
		return assets_type;
	}
	public void setAssets_type(int assets_type) {
		this.assets_type = assets_type;
	}
	public String getPaper_size() {
		return paper_size;
	}
	public void setPaper_size(String paper_size) {
		this.paper_size = paper_size;
	}
	public String getDisplay_size() {
		return display_size;
	}
	public void setDisplay_size(String display_size) {
		this.display_size = display_size;
	}
	public String getDisplay_connector() {
		return display_connector;
	}
	public void setDisplay_connector(String display_connector) {
		this.display_connector = display_connector;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUnserviceable_state() {
		return unserviceable_state;
	}
	public void setUnserviceable_state(int unserviceable_state) {
		this.unserviceable_state = unserviceable_state;
	}
	public String getConnectivity_type() {
		return connectivity_type;
	}
	public void setConnectivity_type(String connectivity_type) {
		this.connectivity_type = connectivity_type;
	}
	
	public int getEthernet_port() {
		return ethernet_port;
	}
	public void setEthernet_port(int ethernet_port) {
		this.ethernet_port = ethernet_port;
	}
	public int getManagement_layer() {
		return management_layer;
	}
	public void setManagement_layer(int management_layer) {
		this.management_layer = management_layer;
	}
	
	public String getV_display_size() {
		return v_display_size;
	}
	public void setV_display_size(String v_display_size) {
		this.v_display_size = v_display_size;
	}
	public String getV_display_connector() {
		return v_display_connector;
	}
	public void setV_display_connector(String v_display_connector) {
		this.v_display_connector = v_display_connector;
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
	public String getDisplay_type() {
		return display_type;
	}
	public void setDisplay_type(String display_type) {
		this.display_type = display_type;
	}
	public String getYear_of_proc() {
		return year_of_proc;
	}
	public void setYear_of_proc(String year_of_proc) {
		this.year_of_proc = year_of_proc;
	}
	public String getYear_of_manufacturing() {
		return year_of_manufacturing;
	}
	public void setYear_of_manufacturing(String year_of_manufacturing) {
		this.year_of_manufacturing = year_of_manufacturing;
	}
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}

	//** AHM BISAG **//
	public Date getUnsv_from_dt() {
		return unsv_from_dt;
	}
	public void setUnsv_from_dt(Date unsv_from_dt) {
		this.unsv_from_dt = unsv_from_dt;
	}
	//** END AHM BISAG **//
	public Date getUnsv_to_dt() {
		return unsv_to_dt;
	}
	public void setUnsv_to_dt(Date unsv_to_dt) {
		this.unsv_to_dt = unsv_to_dt;
	}
	public String getHw_interface() {
		return hw_interface;
	}
	public void setHw_interface(String hw_interface) {
		this.hw_interface = hw_interface;
	}
	public String getNetwork_features() {
		return network_features;
	}
	public void setNetwork_features(String network_features) {
		this.network_features = network_features;
	}

	

}
