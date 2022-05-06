package com.pavan.projects.tambola.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tambola_ticket_values")
public class TicketValues {
	@Id
	@Column(name="ticket_id")
	private long ticketId;
	@Column(name="ticket_values")
	private String ticketValues;
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketValues() {
		return ticketValues;
	}
	public void setTicketValues(String ticketValues) {
		this.ticketValues = ticketValues;
	}
	public TicketValues(long ticketId, String ticketValues) {
		super();
		this.ticketId = ticketId;
		this.ticketValues = ticketValues;
	}
	public TicketValues() {
		super();
	}
	@Override
	public String toString() {
		return "TicketValues [ticketId=" + ticketId + ", ticketValues=" + ticketValues + "]";
	}
	
}
