package com.pavan.projects.tambola.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tambola_users")
public class Users{
	@Id
	@Column(name="email")
	private String email;
	private String pwd;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Users(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Users [email=" + email + ", pwd=" + pwd + "]";
	}
	public Users() {
		super();
	}
	
}
