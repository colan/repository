package org.sample.web.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate= true)
public class Student extends BaseEntity {

	private static final long serialVersionUID = -5581899137002718588L;
	private String username;
	private String passwordMd5;
	
	@Column(name="username",columnDefinition="varchar(255)")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password_md5",columnDefinition="varchar(255)")
	public String getPasswordMd5() {
		return passwordMd5;
	}
	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}
}
