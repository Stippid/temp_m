package com.models.psg.Master;







import static javax.persistence.GenerationType.IDENTITY;







import java.util.Date;







import javax.persistence.Column;



import javax.persistence.Entity;



import javax.persistence.GeneratedValue;



import javax.persistence.Id;



import javax.persistence.Table;



import javax.persistence.UniqueConstraint;



import javax.validation.constraints.NotNull;







import org.hibernate.validator.constraints.NotBlank;



import org.springframework.format.annotation.DateTimeFormat;







@Entity



@Table(name = "tb_qualification", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })



public class TB_QUALIFICATION {



	



	private int id;



	 private String created_by;



	 @DateTimeFormat(pattern = "yyyy-MM-dd")



	 private Date  created_date;



	 private String modified_by;



	 @DateTimeFormat(pattern = "yyyy-MM-dd")



	 private Date modified_date;	



	 private String status;



	 private String qualification_type;



	 private int examination_passed;



	  private int degree; 



	 



	@Id



	@GeneratedValue(strategy = IDENTITY)



	@Column(name = "id", unique = true, nullable = false) 



	public int getId() {



		return id;



	}



	public void setId(int id) {



		this.id = id;



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



	public String getStatus() {



		return status;



	}



	public void setStatus(String status) {



		this.status = status;



	}



	public String getQualification_type() {



		return qualification_type;



	}



	public void setQualification_type(String qualification_type) {



		this.qualification_type = qualification_type;



	}



	public int getExamination_passed() {

		return examination_passed;

	}



	public void setExamination_passed(int examination_passed) {

		this.examination_passed = examination_passed;

	}



	public int getDegree() {

		return degree;

	}



	public void setDegree(int degree) {

		this.degree = degree;

	}























}



