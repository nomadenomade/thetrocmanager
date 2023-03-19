package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class Person {
	
	
	private int id;
	private String name;
	private String vorname;
	private String geburtsdatum;
	private String geburtsort ;
	private String adresse;
	private String email;
	private String telephone;
	private String passwort ;
	private String datum;
	private String rolle;
	private String Pseudo;
	private int warnunganzahl;
	
	
	



	public Person(String name, String vorname, String geburtsdatum, String geburtsort, String adresse, String email,
			String telephone, String passwort, String rolle,String pseudo,String datum) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.geburtsort = geburtsort;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.passwort = passwort;
		this.datum = datum;
		this.rolle = rolle;
		this.Pseudo =pseudo;
	}

	
	
	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getGeburtsort() {
		return geburtsort;
	}

	public void setGeburtsort(String geburtsort) {
		this.geburtsort = geburtsort;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}


	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	public String getRolle() {
		// TODO Auto-generated method stub
		return rolle;
	}



	public String getPseudo() {
		return Pseudo;
	}



	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
	}
	
	public int getWarnunganzahl() {
		return warnunganzahl;
	}



	public void setWarnunganzahl(int warnunganzahl) {
		this.warnunganzahl = warnunganzahl;
	}
	
	

}
