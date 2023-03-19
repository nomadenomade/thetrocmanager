package com.example.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class Verkaufer {
	
	
	private int idVerkaufer;
	private Person person;
	private Betreiber betreiber;
	
	private String status;
	private List <Produkt> produktList;
	private Unternehmen unternehmen;
	
	
	public Betreiber getBetreiber() {
		return betreiber;
	}

	public void setBetreiber(Betreiber betreiber) {
		this.betreiber = betreiber;
	}

	public List<Produkt> getProduktList() {
		return produktList;
	}

	public void setProduktList(List<Produkt> produktList) {
		this.produktList = produktList;
	}

	public Verkaufer(int idVerkaufer, Person person, Betreiber betreiber) {
		super();
		this.idVerkaufer = idVerkaufer;
		this.person = person;
		this.betreiber = betreiber;
	}
	
	public Verkaufer() {
		
	}
	
	
	public int getIdVerkaufer() {
		return idVerkaufer;
	}
	public void setIdVerkaufer(int idVerkaufer) {
		this.idVerkaufer = idVerkaufer;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Betreiber getIdBetreiber() {
		return betreiber;
	}
	public void setIdBetreiber(Betreiber betreiber) {
		this.betreiber =betreiber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Unternehmen getUnternehmen() {
		return unternehmen;
	}

	public void setUnternehmen(Unternehmen unternehmen) {
		this.unternehmen = unternehmen;
	}

	@Override
	public String toString() {
		return "Verkaufer [idVerkaufer=" + idVerkaufer + ", person=" + person + ", betreiber=" + betreiber + ", status="
				+ status + ", produktList=" + produktList + ", unternehmen=" + unternehmen + "]";
	}
	

}
