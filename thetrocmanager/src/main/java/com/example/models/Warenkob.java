package com.example.models;

public class Warenkob {
	
	
	private int idWarenkob;
	private String menge;
	private Kaufer kaufer;
	private Produkt produkt;
	private String status;
	private String Datum;
	//Falls der Kaufer best�tig das Produkt abgeholt zu haben
	private String confirmdatumkunde;

	public String getConfirmdatumkunde() {
		return confirmdatumkunde;
	}
	public void setConfirmdatumkunde(String confirmdatumkunde) {
		this.confirmdatumkunde = confirmdatumkunde;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDatum() {
		return Datum;
	}
	public void setDatum(String datum) {
		Datum = datum;
	}
	public Warenkob() {
		super();
	}
	public Warenkob(int idWarenkob, String menge, Kaufer kaufer, Produkt produkt) {
		super();
		this.idWarenkob = idWarenkob;
		this.menge = menge;
		this.kaufer = kaufer;
		this.produkt = produkt;
	}
	
	
	
	
	public int getIdWarenkob() {
		return idWarenkob;
	}
	public void setIdWarenkob(int idWarenkob) {
		this.idWarenkob = idWarenkob;
	}
	public String getMenge() {
		return menge;
	}
	public void setMenge(String menge) {
		this.menge = menge;
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
