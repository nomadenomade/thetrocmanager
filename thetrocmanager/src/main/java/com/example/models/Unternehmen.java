package com.example.models;

import java.io.InputStream;
import java.util.List;

public class Unternehmen {
	private int idUnternehmen;
	private String Name;
	private String Standort;
	private String stadt;
	private String geolatidude;
	private String geolongitude;
	
	public String getGeolatidude() {
		return geolatidude;
	}
	public void setGeolatidude(String geolatidude) {
		this.geolatidude = geolatidude;
	}
	public String getGeolongitude() {
		return geolongitude;
	}
	public void setGeolongitude(String geolongitude) {
		this.geolongitude = geolongitude;
	}
	private Verkaufer verkaufer;
	private List<Foto> bilder;
	
	public Unternehmen() {
		super();
	}
	public Unternehmen(int idUnternehmen, String name, String standort, Verkaufer verkaufer) {
		super();
		this.idUnternehmen = idUnternehmen;
		Name = name;
		Standort = standort;
		this.verkaufer = verkaufer;
	}
	
	
	
	
	public int getIdUnternehmen() {
		return idUnternehmen;
	}
	public void setIdUnternehmen(int idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getStandort() {
		return Standort;
	}
	public void setStandort(String standort) {
		Standort = standort;
	}
	public Verkaufer getVerkaufer() {
		return verkaufer;
	}
	public void setVerkaufer(Verkaufer verkaufer) {
		this.verkaufer = verkaufer;
	}
	public List<Foto> getBilder() {
		return bilder;
	}
	public void setBilder(List<Foto> bilder) {
		this.bilder = bilder;
	}
	
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	
	
}
