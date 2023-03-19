package com.example.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Rechnung {
	
	private int idRechnung;
	private String gesamtmenge;
	private String datum;
	private String nameProdukte;
	private String Gesamtpreis;
	private Warenkob warenkob;
	
	private Kaufer kaufer;
	private Verkaufer verkaufer;
	
	public Warenkob getWarenkob() {
		return warenkob;
	}
	public void setWarenkob(Warenkob warenkob) {
		this.warenkob = warenkob;
	}
	public Kaufer getKaufer() {
		return kaufer;
	}
	public void setKaufer(Kaufer kaufer) {
		this.kaufer = kaufer;
	}
	public Verkaufer getVerkaufer() {
		return verkaufer;
	}
	public void setVerkaufer(Verkaufer verkaufer) {
		this.verkaufer = verkaufer;
	}
	public String getNameProdukte() {
		return nameProdukte;
	}
	public void setNameProdukte(String nameProdukte) {
		this.nameProdukte = nameProdukte;
	}
	public String getGesamtpreis() {
		return Gesamtpreis;
	}
	public void setGesamtpreis(String gesamtpreis) {
		Gesamtpreis = gesamtpreis;
	}
	public Rechnung() {
		this(0,"","",null);
	}
	public Rechnung(int idRechnung, String gesamtmenge, String datum, Warenkob warenkob) {
		super();
		this.idRechnung = idRechnung;
	
		this.gesamtmenge = gesamtmenge;
		this.datum = datum;
		this.warenkob =warenkob;
	}
	
	
	
	
	
	
	public int getIdRechnung() {
		return idRechnung;
	}
	public void setIdRechnung(int idRechnung) {
		this.idRechnung = idRechnung;
	}
	
	public String getGesamtmenge() {
		return gesamtmenge;
	}
	public void setGesamtmenge(String gesamtmenge) {
		this.gesamtmenge = gesamtmenge;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Warenkob getIdWarenkob() {
		return warenkob;
	}
	public void setIdWarenkob(Warenkob warenkob) {
		this.warenkob = warenkob;
	}
	@Override
	public String toString() {
		return "Rechnung [idRechnung=" + idRechnung + ", gesamtmenge=" + gesamtmenge + ", datum=" + datum
				+ ", warenkob=" + warenkob + ", kaufer=" + kaufer + ", verkaufer=" + verkaufer + ", nameProdukte="
				+ nameProdukte + ", Gesamtpreis=" + Gesamtpreis + "]";
	}
	
	

}
