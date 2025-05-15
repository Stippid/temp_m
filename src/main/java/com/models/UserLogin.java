package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "logininformation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userid"),
		@UniqueConstraint(columnNames = "username") })
public class UserLogin {
	
	private int userId;
	private String userName;
	private String password;
	private int enabled;
	private int accountNonExpired;
	private int accountNonLocked;
	private int credentialsNonExpired;	
	private String telephone;
	private String user_sus_no;
	private String user_formation_no;
	private String user_Arm_code;
	private String ac_dc_date;
	private String login_name;
	private String army_no;
	private String name;
	private int rank;
	private String created_by;
	private String modified_by;
 
	
	@Transient
	private String unit_name;
	
	@Transient
	private String role;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "enabled", nullable = false, length = 4)
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "accountNonExpired", nullable = false, length = 4)
	public int getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Column(name = "accountNonLocked", nullable = false, length = 4)
	public int getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Column(name = "credentialsNonExpired", nullable = false, length = 4)
	public int getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	
	/*@Column(name = "name_nodal_officer")
	public String getName_nodal_officer() {
		return name_nodal_officer;
	}
	public void setName_nodal_officer(String name_nodal_officer) {
		this.name_nodal_officer = name_nodal_officer;
	}
	
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Column(name = "pincode")
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	*/
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "created_by")
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	@Column(name = "modified_by")
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	/*@javax.persistence.Transient
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}*/
	/*public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}*/
	public String getUser_sus_no() {
		return user_sus_no;
	}
	public void setUser_sus_no(String user_sus_no) {
		this.user_sus_no = user_sus_no;
	}
	public String getUser_formation_no() {
		return user_formation_no;
	}
	public void setUser_formation_no(String user_formation_no) {
		this.user_formation_no = user_formation_no;
	}
	public String getUser_Arm_code() {
		return user_Arm_code;
	}
	public void setUser_Arm_code(String user_Arm_code) {
		this.user_Arm_code = user_Arm_code;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getAc_dc_date() {
		return ac_dc_date;
	}
	public void setAc_dc_date(String ac_dc_date) {
		this.ac_dc_date = ac_dc_date;
	}
	public String getArmy_no() {
		return army_no;
	}
	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}
	
	@Transient
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	
	@Transient
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	@Column(name = "name", nullable = true)
	@Transient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//@Column(name = "rank", nullable = true)
	@Transient
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	

} 
