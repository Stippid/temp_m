/*package com.models;

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
@Table(name = "mms_tb_type_of_hldg", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_TYPE_OF_HLDG {
	
	private int id ;
	private String type_of_hldg;
	private String hldg_name;
	private String hldg_name_short; 
	private String data_cr_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date; 
	private String data_upd_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_upd_date; 
	private String data_app_by ;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_app_date;
	private String op_status;
	private String hldg_for;
	
	  private String col_type ;
	  
	  	@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType_of_hldg() {
			return type_of_hldg;
		}
		public void setType_of_hldg(String type_of_hldg) {
			this.type_of_hldg = type_of_hldg;
		}
		public String getHldg_name() {
			return hldg_name;
		}
		public void setHldg_name(String hldg_name) {
			this.hldg_name = hldg_name;
		}
		public String getHldg_name_short() {
			return hldg_name_short;
		}
		public void setHldg_name_short(String hldg_name_short) {
			this.hldg_name_short = hldg_name_short;
		}
		public String getData_cr_by() {
			return data_cr_by;
		}
		public void setData_cr_by(String data_cr_by) {
			this.data_cr_by = data_cr_by;
		}
		public Date getData_cr_date() {
			return data_cr_date;
		}
		public void setData_cr_date(Date data_cr_date) {
			this.data_cr_date = data_cr_date;
		}
		public String getData_upd_by() {
			return data_upd_by;
		}
		public void setData_upd_by(String data_upd_by) {
			this.data_upd_by = data_upd_by;
		}
		public Date getData_upd_date() {
			return data_upd_date;
		}
		public void setData_upd_date(Date data_upd_date) {
			this.data_upd_date = data_upd_date;
		}
		public String getData_app_by() {
			return data_app_by;
		}
		public void setData_app_by(String data_app_by) {
			this.data_app_by = data_app_by;
		}
		public Date getData_app_date() {
			return data_app_date;
		}
		public void setData_app_date(Date data_app_date) {
			this.data_app_date = data_app_date;
		}
		public String getOp_status() {
			return op_status;
		}
		public void setOp_status(String op_status) {
			this.op_status = op_status;
		}
		public String getHldg_for() {
			return hldg_for;
		}
		public void setHldg_for(String hldg_for) {
			this.hldg_for = hldg_for;
		}
		public String getCol_type() {
			return col_type;
		}
		public void setCol_type(String col_type) {
			this.col_type = col_type;
		}
		
		

}
*/