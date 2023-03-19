package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


public class Bewertung {
	
	private int idBewertung;
	private String kommentar;
	private Kaufer kaufer;
	private Produkt produkt;
	private String sterne;
	private String statusbewertung;
	
	public Bewertung() {
		
	}
	public Bewertung(int idBewertung, String kommentar, Kaufer kaufer, Produkt produkt) {
		super();
		this.idBewertung = idBewertung;
		this.kommentar = kommentar;
		this.kaufer = kaufer;
		this.produkt =produkt;
	}
	
	
	
	
	
	
	public Kaufer getKaufer() {
		return kaufer;
	}
	public void setKaufer(Kaufer kaufer) {
		this.kaufer = kaufer;
	}
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	public String getSterne() {
		return sterne;
	}
	public void setSterne(String sterne) {
		this.sterne = sterne;
	}
	public int getIdBewertung() {
		return idBewertung;
	}
	public void setIdBewertung(int idBewertung) {
		this.idBewertung = idBewertung;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public Kaufer getIdKaufer() {
		return kaufer;
	}
	public void setIdKaufer(Kaufer kaufer) {
		this.kaufer = kaufer;
	}
	public Produkt getIdProdukt() {
		return produkt;
	}
	public void setIdProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
	

}
