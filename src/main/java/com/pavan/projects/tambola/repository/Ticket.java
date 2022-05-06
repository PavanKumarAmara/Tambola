package com.pavan.projects.tambola.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tambola_ticket")
public class Ticket {
	private String email;
	@Id
	@Column(name="ticket_id")
	private long number;
	
	public Ticket() {
		super();
	}

	public Ticket(String email, long number) {
		super();
		this.email = email;
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Ticket [email=" + email + ", number=" + number + "]";
	}
	
	
}
