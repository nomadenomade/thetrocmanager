package com.example.models;

import java.util.List;

public class Produkt {
	
	
	


	private int idProdukt;
	private String marke;
	private String preis;
	private String kategorie;
	private String Name;
	private String Menge;
	private String mengeeinheit;
	private String Beschreibung;
	private String ablaufdatum;
	private String onlinebis;
	private String onlinetime;
	private String dauerbisabholung;
	private String datum;
	private Verkaufer verkaufer;
	private String Status;
	private String Währung;
	private Warenkob warenkob;

	
	public Warenkob getWarenkob() {
		return warenkob;
	}


	public void setWarenkob(Warenkob warenkob) {
		this.warenkob = warenkob;
	}

	private List<Foto> produktBilder ;
	
	




	public List<Foto> getProduktBilder() {
		return produktBilder;
	}


	public void setProduktBilder(List<Foto> produktBilder) {
		this.produktBilder = produktBilder;
	}


	public Produkt(String preis, String kategorie, String name, String dauerbisabholung) {
		super();
		this.preis = preis;
		this.kategorie = kategorie;
		Name = name;
		this.dauerbisabholung = dauerbisabholung;
	}
	
	
	public Produkt(String preis, String kategorie, String name, String ablaufdatum, String onlinebis,
			String dauerbisabholung) {
		super();
		this.preis = preis;
		this.kategorie = kategorie;
		Name = name;
		this.ablaufdatum = ablaufdatum;
		this.onlinebis = onlinebis;
		this.dauerbisabholung = dauerbisabholung;
	}
	
	
	public Produkt() {
		super();
	}
	public Produkt(int idProdukt, String marke, String preis, String kategorie, String name, String menge,
			String beschreibung, String datum, Verkaufer verkaufer) {
		super();
		this.idProdukt = idProdukt;
		this.marke = marke;
		this.preis = preis;
		this.kategorie = kategorie;
		Name = name;
		Menge = menge;
		Beschreibung = beschreibung;
		this.datum = datum;
		this.verkaufer = verkaufer;
	}
	
	
	
	
	public String getAblaufdatum() {
		return ablaufdatum;
	}
	public void setAblaufdatum(String ablaufdatum) {
		this.ablaufdatum = ablaufdatum;
	}
	public String getOnlinebis() {
		return onlinebis;
	}
	public void setOnlinebis(String onlinebis) {
		this.onlinebis = onlinebis;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getDauerbisabholung() {
		return dauerbisabholung;
	}
	public void setDauerbisabholung(String dauerbisabholung) {
		this.dauerbisabholung = dauerbisabholung;
	}
	public Verkaufer getVerkaufer() {
		return verkaufer;
	}
	public void setVerkaufer(Verkaufer verkaufer) {
		this.verkaufer = verkaufer;
	}
	
	
	
	
	
	public int getIdProdukt() {
		return idProdukt;
	}
	public void setIdProdukt(int idProdukt) {
		this.idProdukt = idProdukt;
	}
	public String getMarke() {
		return marke;
	}
	public void setMarke(String marke) {
		this.marke = marke;
	}
	public String getPreis() {
		return preis;
	}
	public void setPreis(String preis) {
		this.preis = preis;
	}
	public String getKategorie() {
		return kategorie;
	}
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMenge() {
		return Menge;
	}
	public void setMenge(String menge) {
		Menge = menge;
	}
	public String getBeschreibung() {
		return Beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		Beschreibung = beschreibung;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Verkaufer getIdVerkaufer() {
		return verkaufer;
	}
	public void setIdVerkaufer(Verkaufer verkaufer) {
		this.verkaufer = verkaufer;
	}
	
	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}
	
	public String getWährung() {
		return Währung;
	}


	public void setWährung(String währung) {
		Währung = währung;
	}


	public String getMengeeinheit() {
		return mengeeinheit;
	}


	public void setMengeeinheit(String mengeeinheit) {
		this.mengeeinheit = mengeeinheit;
	}
	
	@Override
	public String toString() {
		return "Produkt [idProdukt=" + idProdukt + ", marke=" + marke + ", preis=" + preis + ", kategorie=" + kategorie
				+ ", Name=" + Name + ", Menge=" + Menge + ", mengeeinheit=" + mengeeinheit + ", Beschreibung="
				+ Beschreibung + ", ablaufdatum=" + ablaufdatum + ", onlinebis=" + onlinebis + ", onlinetime="
				+ onlinetime + ", dauerbisabholung=" + dauerbisabholung + ", datum=" + datum + ", Status=" + Status
				+ ", W�hrung=" + Währung + "]";
	}

}
