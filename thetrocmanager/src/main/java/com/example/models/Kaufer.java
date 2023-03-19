package com.example.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class Kaufer {
	

	private int idKaufer;
	private Person person;
	private String pseudo;
	private String idbrowser;
	
	private List <Bewertung>listbewertung;
	
	
	public List<Bewertung> getListbewertung() {
		return listbewertung;
	}


	public void setListbewertung(List<Bewertung> listbewertung) {
		this.listbewertung = listbewertung;
	}


	public String getIdbrowser() {
		return idbrowser;
	}


	public void setIdbrowser(String idbrowser) {
		this.idbrowser = idbrowser;
	}


	public Kaufer() {
		//this(0,"",null);
	}
	
	
	public Kaufer(int idKaufer, String pseudo, Person person) {
		super();
		this.idKaufer = idKaufer;
		this.pseudo = pseudo;
		this.person = person;
	}
	
	
	
	
	public int getIdKaufer() {
		return idKaufer;
	}
	public void setIdKaufer(int idKaufer) {
		this.idKaufer = idKaufer;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	

}
