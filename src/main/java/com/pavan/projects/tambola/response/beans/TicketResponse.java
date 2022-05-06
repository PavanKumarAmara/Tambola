package com.pavan.projects.tambola.response.beans;

import java.util.List;

public class TicketResponse {
	private String email;
	private List<Integer[]> ticketValues;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer[]> getTicketValues() {
		return ticketValues;
	}
	public void setTicketValues(List<Integer[]> ticketValues) {
		this.ticketValues = ticketValues;
	}
	public TicketResponse(String email, List<Integer[]> ticketValues) {
		super();
		this.email = email;
		this.ticketValues = ticketValues;
	}
	public TicketResponse() {
		super();
	}
	@Override
	public String toString() {
		return "TicketResponse [email=" + email + ", ticketValues=" + ticketValues + "]";
	}
	
}	
