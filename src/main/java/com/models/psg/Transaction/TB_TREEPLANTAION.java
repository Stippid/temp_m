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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_treeplantaion", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_TREEPLANTAION {

	private int id;
	private String unit_sus_no;
	private String remarks;
	private String tree1qnty;
	private String tree2qnty;
	private String tree3qnty;	
	private String tree4qnty;
	private String doc_path_tree;

	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;

	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getTree1qnty() {
		return tree1qnty;
	}
	public void setTree1qnty(String tree1qnty) {
		this.tree1qnty = tree1qnty;
	}
	public String getTree2qnty() {
		return tree2qnty;
	}
	public void setTree2qnty(String tree2qnty) {
		this.tree2qnty = tree2qnty;
	}
	public String getTree3qnty() {
		return tree3qnty;
	}
	public void setTree3qnty(String tree3qnty) {
		this.tree3qnty = tree3qnty;
	}
	public String getTree4qnty() {
		return tree4qnty;
	}
	public void setTree4qnty(String tree4qnty) {
		this.tree4qnty = tree4qnty;
	}
	public String getDoc_path_tree() {
		return doc_path_tree;
	}
	public void setDoc_path_tree(String doc_path_tree) {
		this.doc_path_tree = doc_path_tree;
	}
	
	

}
