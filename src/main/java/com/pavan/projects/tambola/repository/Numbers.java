package com.pavan.projects.tambola.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tambola_numbers")
public class Numbers {
	@Id
	@Column(name="gen_number")
	private int number;

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Numbers [number=" + number + "]";
	}

	public Numbers(int number) {
		super();
		this.number = number;
	}

	public Numbers() {
		super();
	}
	
}
