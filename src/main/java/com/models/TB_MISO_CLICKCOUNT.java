package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
	@Entity
	@Table(name = "tb_miso_clickcount", uniqueConstraints = {
	@UniqueConstraint(columnNames = "id") })
	public class TB_MISO_CLICKCOUNT {
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
			
			private int user_id;	
			private int screen_id;
			@Transient
			private Date action_date;
			
			public int getUser_id() {
				return user_id;
			}
			public void setUser_id(int user_id) {
				this.user_id = user_id;
			}
			public int getScreen_id() {
				return screen_id;
			}
			public void setScreen_id(int screen_id) {
				this.screen_id = screen_id;
			}
			
			public Date getAction_date() {
				return action_date;
			}
			public void setAction_date(Date action_date) {
				this.action_date = action_date;
			}
		
}
