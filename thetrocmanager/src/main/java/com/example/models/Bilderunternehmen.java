package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


public class Bilderunternehmen {

	private int idFoto;
	private String name;
	private long size;
	private String extension;
	private Unternehmen unternehmen;
	
	public Bilderunternehmen(String name, int size, String extension) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
	}
	public Bilderunternehmen () {

	}
	
	
	
	public Bilderunternehmen(String name, long size, String extension, int idFoto, Unternehmen unternehmen) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.idFoto = idFoto;
		this.unternehmen = unternehmen;
	}
	public Bilderunternehmen(String name, long size, String extension, int idFoto) {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.idFoto = idFoto;
		
		
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
	
	
	
	public Unternehmen getUnternehmen() {
		return unternehmen;
	}
	public void setUnternehmen(Unternehmen unternehmen) {
		this.unternehmen = unternehmen;
	}
	@Override
	public String toString() {
		return "Foto [name=" + name + ", size=" + size + ", extension=" + extension + ", idFoto=" + idFoto + "]";
	}
	
	

}
