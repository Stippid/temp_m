package com.models;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "tb_ldap_screen_analytics", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_LDAP_SCREEN_ANALYTICS  {
	@Id
	@GeneratedValue(strategy = IDENTITY)
		private int id;
		@Column(name = "id")
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		private int screen_id;	
		@Column(name = "screen_id")
		public int getScreen_id() {
			return screen_id;
		}
		public void setScreen_id(int screen_id) {
			this.screen_id = screen_id;
		}

		private String screen_module;
		@Column(name = "screen_module")
		public String getScreen_module() {
			return screen_module;
		}
		public void setScreen_module(String screen_module) {
			this.screen_module = screen_module;
		}
		private int userid;
		@Column(name = "userid")
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		private int roleid;
		@Column(name = "roleid")
		public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}
		
		private String username;
		@Column(name = "username")
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String get(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		@Column(name = "module_id")
		private int module_id;
		public int getModule_id() {
			return module_id;
		}
		public void setModule_id(int module_id) {
			this.module_id = module_id;
		}
		@Column(name = "submodule_id")
		private int submodule_id;
		public int getSubmodule_id() {
			return submodule_id;
		}
		public void setSubmodule_id(int submodule_id) {
			this.submodule_id = submodule_id;
		}

}
