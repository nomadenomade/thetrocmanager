package com.example.models;

import java.io.InputStream;

public class Foto {
	private String name;
	private long size;
	private String extension;
	private int idFoto;
	private InputStream Inhalt;
	
	private Produkt produkt;
	private Unternehmen unternehmen;
	
	public Foto(String name, int size, String extension) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
	}
	public Foto () {
		
	}
	
	
	
	public Foto(String name, long size, String extension, int idFoto, Unternehmen unternehmen) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.idFoto = idFoto;
		this.unternehmen = unternehmen;
	}
	public Foto(String name, long size, String extension, int idFoto, InputStream inhalt, Produkt produkt) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.idFoto = idFoto;
		Inhalt = inhalt;
		this.produkt = produkt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long l) {
		this.size = l;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public int getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
	public InputStream getInhalt() {
		return Inhalt;
	}
	public void setInhalt(InputStream inhalt) {
		Inhalt = inhalt;
	}
	public Unternehmen getUnternehmen() {
		return unternehmen;
	}
	public void setUnternehmen(Unternehmen unternehmen) {
		this.unternehmen = unternehmen;
	}
	@Override
	public String toString() {
		return "Foto [name=" + name + ", size=" + size + ", extension=" + extension + ", idFoto=" + idFoto + ", Inhalt="
				+ Inhalt + "]";
	}
	
	

}
