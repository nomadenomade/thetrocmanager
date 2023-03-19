package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class Betreiber {
	
	private int idBetreiber;
	private Person person;
	
	public Betreiber() {
		
	}
	
	public Betreiber(Person person, int idBetreiber) {
		super();
		this.person = person;
		this.idBetreiber = idBetreiber;
	}
	
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getIdBetreiber() {
		return idBetreiber;
	}
	public void setIdBetreiber(int idBetreiber) {
		this.idBetreiber = idBetreiber;
	}

}
