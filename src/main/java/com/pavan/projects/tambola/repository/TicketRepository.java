package com.pavan.projects.tambola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	Ticket findByEmail(String email);
}
