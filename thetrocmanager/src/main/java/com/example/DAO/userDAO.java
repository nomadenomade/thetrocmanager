package com.example.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.models.Bewertung;
import com.example.models.Foto;
import com.example.models.Kaufer;
import com.example.models.Person;
import com.example.models.Produkt;
import com.example.models.Rechnung;
import com.example.models.Unternehmen;
import com.example.models.Verkaufer;
import com.example.models.Warenkob;

@Service
public class userDAO {
	private String request;
	private ResultSet resultset;
	private PreparedStatement preparedstatement;
	private Statement statement;

	public boolean registrierung(Person person) {
		boolean rueckgabe = false;
		if(!iSemailSchonVorhanden(person.getEmail())) {



			try {



					 if(person.getRolle()!=null && person.getRolle().equals("Kaufer")&& !isPseudovorhanden(person.getPseudo())) {

						 request = "INSERT INTO Person (nameperson,vorname,geburtsdatum,geburtsort,adresse,email,telephone,passwort,rolle,datum,warnunganzahl) VALUES(?,?,?,?,?,?,?,?,?,?,0)";

						 preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
						 preparedstatement.setString(1, person.getName());
						 preparedstatement.setString(2, person.getVorname());
						 preparedstatement.setString(3, person.getGeburtsdatum());
						 preparedstatement.setString(4, person.getGeburtsort());
						 preparedstatement.setString(5, person.getAdresse());
						 preparedstatement.setString(6, person.getEmail());
						 preparedstatement.setString(7, person.getTelephone());
						 preparedstatement.setString(8, person.getPasswort());
						 preparedstatement.setString(9, person.getRolle());
						 preparedstatement.setString(10, new Date().toString());

						 int result = preparedstatement.executeUpdate();

						 if(result==1) {
							 Person person1 = getPerson(person.getEmail());
							 request ="INSERT INTO Kaufer(Pseudo,idPerson,browserid) VALUES(?,?,'-')";
							 preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
							 preparedstatement.setString(1, person.getPseudo());
							 preparedstatement.setInt(2, person1.getId());
							 int rueck = preparedstatement.executeUpdate();

							 if(rueck==1) {
								 rueckgabe = true;
							 }
						 }

					 }else if(person.getRolle()!=null && person.getRolle().equals("Verkaufer")){


						 request = "INSERT INTO Person (nameperson,vorname,geburtsdatum,geburtsort,adresse,email,telephone,passwort,rolle,datum,warnunganzahl) VALUES(?,?,?,?,?,?,?,?,?,?,0)";

						 preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
						 preparedstatement.setString(1, person.getName());
						 preparedstatement.setString(2, person.getVorname());
						 preparedstatement.setString(3, person.getGeburtsdatum());
						 preparedstatement.setString(4, person.getGeburtsort());
						 preparedstatement.setString(5, person.getAdresse());
						 preparedstatement.setString(6, person.getEmail());
						 preparedstatement.setString(7, person.getTelephone());
						 preparedstatement.setString(8, person.getPasswort());
						 preparedstatement.setString(9, person.getRolle());
						 preparedstatement.setString(10, new Date().toString());

						 int result = preparedstatement.executeUpdate();

						 if(result==1) {
							 Person person1 = getPerson(person.getEmail());
							 request ="INSERT INTO verkaufer(idPerson) VALUES(?)";
							 preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
							 preparedstatement.setInt(1, person1.getId());
							 int rueck = preparedstatement.executeUpdate();

							 if(rueck==1) {
								 rueckgabe = true;
							 }
						 }

					 }


			} catch (SQLException e) {

			}finally {
				DBconnection.getInstance().closeConnection();
			}
		}


		return rueckgabe;
	}

	public Person getPerson(String email) {
		request ="SELECT * FROM Person WHERE email=?";
		Person person = new Person();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, email);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				person.setId(resultset.getInt("idPerson"));
				person.setName(resultset.getString("Nameperson"));
				person.setVorname(resultset.getString("Vorname"));
				person.setGeburtsdatum(resultset.getString("Geburtsdatum"));
				person.setGeburtsort(resultset.getString("Geburtsort"));
				person.setAdresse(resultset.getString("Adresse"));
				person.setEmail(resultset.getString("Email"));
				person.setPasswort(resultset.getString("Passwort"));
				person.setTelephone(resultset.getString("Telephone"));
				person.setRolle(resultset.getString("Rolle"));
				person.setDatum(resultset.getString("Datum"));
				person.setWarnunganzahl(resultset.getInt("warnunganzahl"));
				

			}
		} catch (SQLException e) {

		}
		return person;
	}

	
	public int updateperson(String type,Person person) {
		
		if(type.equals("warnunganzahl")) {
			request ="UPDATE Person SET warnunganzahl = ? WHERE idPerson=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, person.getWarnunganzahl());
				preparedstatement.setInt(2, person.getId());
				
				if(preparedstatement.executeUpdate()==1) {
					return 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}



	public boolean deletePerson(Person person) {
		boolean rueckgabe= false;
		Person person1 = getPerson(person.getEmail());
		if(person.getPseudo()!=null) {
			request = "DELETE FROM Kaufer where idPerson=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, person1.getId());
				int rueck = preparedstatement.executeUpdate();

			} catch (SQLException e) {

			}finally {
				DBconnection.getInstance().closeConnection();
			}
		}else if(person.getRolle()!=null && person.getRolle().equals("Verkaufer")) {
			request = "DELETE FROM Verkaufer where idPerson=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, person1.getId());
				int rueck = preparedstatement.executeUpdate();

			} catch (SQLException e) {

			}finally {
				DBconnection.getInstance().closeConnection();
			}
		}

		request ="DELETE FROM Person WHERE idPerson=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, person1.getId());
			int rueck = preparedstatement.executeUpdate();
			if(rueck==1) {
				rueckgabe = true;
			}

		} catch (SQLException e) {

		}finally {
			DBconnection.getInstance().closeConnection();
		}





		return rueckgabe;
	}

	public boolean iSemailSchonVorhanden(String email) {
		boolean rueckgabe=false;
		request = "SELECT * FROM Person ";
		try {
			statement = DBconnection.getInstance().getStatement();
			resultset =statement.executeQuery(request);
			while(resultset.next()) {
				if(resultset.getString("Email")!=null &&resultset.getString("Email").equals(email)) {
					rueckgabe = true;
					break;
				}
			}
		} catch (SQLException e) {

		}finally {
			DBconnection.getInstance().closeConnection();
		}

		return rueckgabe;
	}

	public boolean isPseudovorhanden(String pseudo) {
		boolean rueckgabe=false;
		request = "SELECT * FROM kaufer";
		try {
			statement = DBconnection.getInstance().getStatement();
			resultset= statement.executeQuery(request);
			while(resultset.next()) {
				if(resultset.getString("pseudo").equals(pseudo)) {
					rueckgabe=true;
					break;
				}
			}
		} catch (SQLException e) {

		}finally {
			DBconnection.getInstance().closeConnection();
		}


		return rueckgabe;
	}


	public Person einloggen(String email, String Passwort) {
		request ="SELECT * FROM Person WHERE email=? and passwort=?";
		Person person = new Person();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, email);
			preparedstatement.setString(2, Passwort);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				person.setId(resultset.getInt("idPerson"));
				person.setName(resultset.getString("Nameperson"));
				person.setVorname(resultset.getString("Vorname"));
				person.setGeburtsdatum(resultset.getString("Geburtsdatum"));
				person.setGeburtsort(resultset.getString("Geburtsort"));
				person.setAdresse(resultset.getString("Adresse"));
				person.setEmail(resultset.getString("Email"));
				person.setPasswort(resultset.getString("Passwort"));
				person.setTelephone(resultset.getString("Telephone"));
				person.setRolle(resultset.getString("Rolle"));
				person.setDatum(resultset.getString("Datum"));
				person.setWarnunganzahl(resultset.getInt("warnunganzahl"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	public Kaufer getKaufer(Person Person) {
		request = "SELECT * FROM kaufer WHERE idPerson=?";
		Kaufer kaufer = new Kaufer();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, Person.getId());
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				kaufer.setIdKaufer(resultset.getInt("idKaufer"));
				kaufer.setPseudo(resultset.getString("Pseudo"));
				break;
			}
			kaufer.setPerson(Person);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kaufer;

	}
	public Person getPerson(int idPerson) {
		request = "SELECT * FROM person WHERE idPerson=?";
		Person person = new Person();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idPerson);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				person.setId(resultset.getInt("idPerson"));
				person.setName(resultset.getString("Nameperson"));
				person.setVorname(resultset.getString("Vorname"));
				person.setGeburtsdatum(resultset.getString("Geburtsdatum"));
				person.setGeburtsort(resultset.getString("Geburtsort"));
				person.setAdresse(resultset.getString("Adresse"));
				person.setEmail(resultset.getString("Email"));
				person.setPasswort(resultset.getString("Passwort"));
				person.setTelephone(resultset.getString("Telephone"));
				person.setRolle(resultset.getString("Rolle"));
				person.setDatum(resultset.getString("Datum"));
				person.setWarnunganzahl(resultset.getInt("warnunganzahl"));
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return person;

	}

	
	//Berechnung des Abstands zwischen zwei location
	public double abstand(double lng1, double lat1,double lng2, double lat2) {
		double ab = (Math.acos(Math.sin(Math.toRadians(lat1))*Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(lng1-lng2)))*6367445)/1000;		
		return ab;
	}

	public String getDateFilter(String onlineseit,int rueck) {

		SimpleDateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String datestring = dataformat.format(date);
		String tab[]=datestring.split("-");
		String tag ="";
		int tage=0;

		if(rueck==1) {
			if(onlineseit.equals("heute")) {
				return datestring;
			}else if(onlineseit.equals("2 Tagen")) {

				if(Integer.valueOf(tab[0])>2) {
					if(datestring.split("-")[0].startsWith("0")) {
						tage = Integer.valueOf(tab[0].split("")[1])-1;
						if(datestring.split("-")[1].startsWith("0")) {
							tag +=""+tage+"-0"+tab[1]+"-"+tab[2];
						}else {
							tag +=""+tage+"-"+tab[1]+"-"+tab[2];
						}

					}else {
						tage = Integer.valueOf(tab[0])-1;
						if(datestring.split("-")[1].startsWith("0")) {
							tag +=""+tage+"-0"+tab[1]+"-"+tab[2];
						}else {
							tag +=""+tage+"-"+tab[1]+"-"+tab[2];
						}


					}
				}if(Integer.valueOf(tab[0])==1) {
					if(Integer.valueOf(tab[1])==1) {
						tag +="31-12-"+(Integer.valueOf(tab[2])-1);
					}else {
						if(datestring.split("-")[1].startsWith("0")) {
							tag +="31-0"+(Integer.valueOf(tab[1])-1)+"-"+tab[2];
						}else {
							tag +="31-"+(Integer.valueOf(tab[1])-1)+"-"+tab[2];
						}

					}
				}


			}

		}else {
			if(onlineseit.equals("heute")) {
				return datestring;
			}else if(onlineseit.equals("2 Tagen")) {

				if(Integer.valueOf(tab[0])>2) {
					if(datestring.split("-")[0].startsWith("0")) {
						tage = Integer.valueOf(tab[0].split("")[1])-2;
						if(datestring.split("-")[1].startsWith("0")) {
							tag +=""+tage+"-0"+tab[1]+"-"+tab[2];
						}else {
							tag +=""+tage+"-"+tab[1]+"-"+tab[2];
						}

					}else {
						tage = Integer.valueOf(tab[0])-2;
						if(datestring.split("-")[1].startsWith("0")) {
							tag +=""+tage+"-0"+tab[1]+"-"+tab[2];
						}else {
							tag +=""+tage+"-"+tab[1]+"-"+tab[2];
						}


					}
				}if(Integer.valueOf(tab[0])==1) {
					if(Integer.valueOf(tab[1])==1) {
						tag +="30-12-"+(Integer.valueOf(tab[2])-1);
					}else {
						if(datestring.split("-")[1].startsWith("0")) {
							tag +="30-0"+(Integer.valueOf(tab[1])-1)+"-"+tab[2];
						}else {
							tag +="30-"+(Integer.valueOf(tab[1])-1)+"-"+tab[2];
						}

					}
				}


			}
		}




		return tag;
	}


	public Produkt getProdukt(int idProdukt) {
		request="SELECT * FROM produkte AS p "
				+ "INNER JOIN bilderprodukt as bp ON p.idProdukte=bp.idProdukte "
				+ "INNER JOIN verkaufer AS v ON p.idVerkaufer=v.idVerkaufer "
				+ "INNER JOIN unternehmen AS u ON v.idverkaufer=u.idVerkaufer "
				+ "INNER JOIN bilderunternehmen AS bu ON u.idUnternehmen=bu.idUnternehmen "
				+ "INNER JOIN person AS pers ON v.idPerson=pers.idPerson "
				+ "WHERE p.idProdukte= ?";
		Produkt produkt=null;
		Verkaufer verkaufer;
		Unternehmen unternehm;
		Person person;
		List <Foto>fotoprodukt = new ArrayList();
		List <Foto>fotounternehmen = new ArrayList();
		List <String>imagename = new ArrayList();
		Foto foto1 =null;
		Foto foto2= null;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idProdukt);
			ResultSet resultset = preparedstatement.executeQuery();

			while(resultset.next()) {

				produkt= new Produkt();
				verkaufer = new Verkaufer();
				unternehm = new Unternehmen();
				person = new Person();
				foto1= new Foto();
				foto2= new Foto();

				produkt.setIdProdukt(resultset.getInt("idProdukte"));
				produkt.setName(resultset.getString("Nameprodukte"));
				produkt.setBeschreibung(resultset.getString("Beschreibung"));
				produkt.setMarke(resultset.getString("Marke"));
				produkt.setPreis(resultset.getString("Preis"));
				produkt.setWährung(resultset.getString("Währung"));
				produkt.setMenge(resultset.getString("Menge"));
				produkt.setMenge(resultset.getString("Mengeeinheit"));
				produkt.setKategorie(resultset.getString("Kategorie"));
				produkt.setDauerbisabholung(resultset.getString("Dauerbisabholung"));
				produkt.setOnlinebis(resultset.getString("Onlinebis"));
				produkt.setAblaufdatum(resultset.getString("Ablaufdatum"));
				produkt.setOnlinetime(resultset.getString("Onlinetime"));
				produkt.setStatus(resultset.getString("Status"));
				produkt.setIdProdukt(resultset.getInt("idProdukte"));
				produkt.setDatum(resultset.getString("Datum"));

				verkaufer.setIdVerkaufer(resultset.getInt("idVerkaufer"));
				person.setId(resultset.getInt("idPerson"));
				person.setEmail(resultset.getString("Email"));

				unternehm.setIdUnternehmen(resultset.getInt("idUnternehmen"));
				unternehm.setName(resultset.getString("Nameunternehmen"));
				unternehm.setStandort(resultset.getString("Standort"));
				unternehm.setStadt(resultset.getString("Stadt"));
				unternehm.setGeolatidude(resultset.getString("Latitude"));
				unternehm.setGeolongitude(resultset.getString("Longitude"));

				if(!imagename.contains(resultset.getString("Namebildprodukt"))) {
					foto1.setIdFoto(resultset.getInt("idBilderprodukt"));
					foto1.setName(resultset.getString("Namebildprodukt"));
					imagename.add(resultset.getString("Namebildprodukt"));
					fotoprodukt.add(foto1);
				}

				if(!imagename.contains(resultset.getString("Namebildunternehmen"))) {
					foto2.setIdFoto(resultset.getInt("idBilderunternehmen"));
					foto2.setName(resultset.getString("Namebildunternehmen"));
					imagename.add(resultset.getString("Namebildunternehmen"));
					fotounternehmen.add(foto2);
				}




				unternehm.setBilder(fotounternehmen);
				produkt.setProduktBilder(fotoprodukt);

				unternehm.setVerkaufer(verkaufer);
				verkaufer.setUnternehmen(unternehm);

				verkaufer.setPerson(person);
				produkt.setVerkaufer(verkaufer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return produkt;
	}


	
	public List<Warenkob> getListBestellungKunde(int idKaufer){
		List<Warenkob> list = new ArrayList<>();
		request="SELECT * FROM warenkob WHERE idKaufer=? ";
		Produkt produkt =null;
		Kaufer kaufer =new Kaufer();
		Warenkob warenkob = null;
		kaufer.setIdKaufer(idKaufer);
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1,idKaufer);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				warenkob = new Warenkob();
				produkt = getProdukt(resultset.getInt("idProdukte"));
				warenkob.setProdukt(produkt);
				warenkob.setDatum(resultset.getString("Datum"));
				warenkob.setMenge(resultset.getString("Menge"));
				warenkob.setStatus(resultset.getString("Status"));
				warenkob.setIdWarenkob(resultset.getInt("idWarenkob"));
				list.add(warenkob);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Warenkob> getListBestellungKundeOhnebewertet(int idKaufer){
		List<Warenkob> list = new ArrayList<>();
		request="SELECT * FROM warenkob WHERE idKaufer=? AND Status='bestaetigt' AND confirmkunde='ja' AND bewertet='-' ";
		Produkt produkt =null;
		Kaufer kaufer =new Kaufer();
		Warenkob warenkob = null;
		kaufer.setIdKaufer(idKaufer);
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1,idKaufer);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				warenkob = new Warenkob();
				warenkob.setDatum(resultset.getString("Datum"));
				warenkob.setMenge(resultset.getString("Menge"));
				warenkob.setStatus(resultset.getString("Status"));
				warenkob.setIdWarenkob(resultset.getInt("idWarenkob"));
				produkt = getProdukt(resultset.getInt("idProdukte"));
				produkt.setWarenkob(warenkob);
				warenkob.setProdukt(produkt);
				
				list.add(warenkob);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}


	public boolean deleteWarenkob(int idWarenkob) {
		request = "DELETE FROM warenkob WHERE idWarenkob=?";
		boolean rueck = false;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idWarenkob);
			if(preparedstatement.executeUpdate()==1) {
				rueck = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rueck;
	}


	public Map<String,List<String>>notification(int idkaufer) {
		List<String> produktname = new ArrayList<>();
		List<String> idwarenkob = new ArrayList<>();
		Map<String,List<String>> map = new HashMap<>();
		request = "SELECT * FROM warenkob AS wa "
				+ "INNER JOIN produkte AS pr ON pr.idProdukte = wa.idProdukte "
				+ "WHERE wa.idKaufer=? AND wa.Status='bestaetigt' AND notification='-'";

		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idkaufer);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				produktname.add(resultset.getString("nameprodukte"));
				idwarenkob.add(String.valueOf(resultset.getInt("idWarenkob")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("produktname", produktname);
		map.put("idwarenkob", idwarenkob);

		return map;
	}
    public boolean updateWarekobnachiduser(int iduser, String type) {
    	if(type.equals("kaufer")) {
    		request= "UPDATE warenkob SET notification='gelesen' WHERE idKaufer=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, iduser);
				if(preparedstatement.executeUpdate()==1) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else if(type.equals("verkaufer")) {
    		request= "UPDATE warenkob SET notification='gelesen' WHERE idVerkaufer=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, iduser);
				if(preparedstatement.executeUpdate()==1) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return false;
    }
	public boolean updateWarekob(int idwarenkob, String key,String lat,String lng) {
		if(key.equals("notification")){
			request= "UPDATE warenkob SET notification='gelesen' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if(key.equals("confirmkundeja")) {
			
			SimpleDateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String datestring = dataformat.format(date);
			
			
			request= "UPDATE warenkob SET confirmkunde='ja',confirmdate=? WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setString(1, datestring);
				preparedstatement.setInt(2, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					/*request= "UPDATE warenkob SET dauerabhol='-' WHERE idWarenkob=?";
					preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
					preparedstatement.setInt(1, idwarenkob);
					if(preparedstatement.executeUpdate()==1) {
						
					}
					*/
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("confirmkundenein")) {
			request= "UPDATE warenkob SET confirmkunde='nein' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					/*request= "UPDATE warenkob SET dauerabhol='-' WHERE idWarenkob=?";
					preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
					preparedstatement.setInt(1, idwarenkob);
					if(preparedstatement.executeUpdate()==1) {
						
					}
					*/
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("zeitabgelaufen")) {
			request= "UPDATE warenkob SET dauerabhol='zeitabgelaufen' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("confirmverkauferja")) {
			request= "UPDATE warenkob SET confirmverkaufer='ja' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("confirmverkaufernein")) {
			request= "UPDATE warenkob SET confirmverkaufer='nein' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("warnungcount")) {
			request= "UPDATE warenkob SET warnungcount='ja' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("geolocation")) {
			request= "UPDATE warenkob SET latitudek=?,longitudek=? WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setString(1, lat);
				preparedstatement.setString(2, lng);
				preparedstatement.setInt(3, idwarenkob);
				if(preparedstatement.executeUpdate()==1) {
					
					return true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return false;
	}
	
	public int checkaufantwort(String type, int idkaufer) {
		int rueck=0;
		if(type.equals("kaufer")) {
			request = "SELECT * FROM warenkob WHERE idKaufer=? AND status='bestaetigt' AND dauerabhol='zeitabgelaufen' AND confirmkunde='-'";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idkaufer);
				ResultSet  resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					rueck=1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return rueck;
	}
	
	public int checkaufantwort_kunde_verkaufer(String bereich, int id,Person person) {
		int rueck=0;
		if(bereich.equals("kaufer")) {
			request = "SELECT * FROM warenkob WHERE idKaufer=? AND status='bestaetigt'  AND warnungcount ='-' ";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, id);
				ResultSet  resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					
					if(resultset.getString("confirmkunde").equals("nein")&&resultset.getString("confirmverkaufer").equals("nein")) {
					
						int zahl= person.getWarnunganzahl()+1;
						person.setWarnunganzahl(zahl);
						updateperson("warnunganzahl", person);
						updateWarekob(resultset.getInt("idWarenkob"), "warnungcount",null,null);
						rueck=zahl;
					}else if(resultset.getString("confirmkunde").equals("ja")&&resultset.getString("confirmverkaufer").equals("nein")){
						Produkt prod = getProdukt(resultset.getInt("idProdukte"));
						double lat1 = Double.valueOf(prod.getVerkaufer().getUnternehmen().getGeolatidude());
						double lng1 = Double.valueOf(prod.getVerkaufer().getUnternehmen().getGeolongitude());
						
						
						if(resultset.getString("latitudek")!=null && resultset.getString("longitudek")!=null ){
							double lat2 = Double.valueOf(resultset.getString("latitudek"));
							double lng2 = Double.valueOf(resultset.getString("longitudek"));
							
							double difflat = Math.abs(lat1-lat2);
							double difflong = Math.abs( lng1-lng2);
							
							if(difflat<0.1 && difflong <0.1) {
								Person pers = prod.getVerkaufer().getPerson();
								int zahl = pers.getWarnunganzahl()+1;
								pers.setWarnunganzahl(zahl);
								updateperson("warnunganzahl", pers);
							}else {
								int zahl= person.getWarnunganzahl()+1;
								person.setWarnunganzahl(zahl);
								updateperson("warnunganzahl", person);
								updateWarekob(resultset.getInt("idWarenkob"), "warnungcount",null,null);
								rueck= zahl;
							}
						}
								
						
					}else if (resultset.getString("confirmkunde").equals("ja")&& resultset.getString("confirmverkaufer").equals("ja")) {
						int idverkaufer =0;
						String preisprodukt ="";
						String währung ="";
						String nameprodukt ="";
						
						SimpleDateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy");
						Date date = new Date();
						String datestring = dataformat.format(date);
						
						String req = "SELECT * FROM Produkte WHERE idProdukte=?";
						preparedstatement = DBconnection.getInstance().getPreparedStatement(req);
						preparedstatement.setInt(1, resultset.getInt("idProdukte"));
						ResultSet res = preparedstatement.executeQuery();
						while (res.next()) {
							idverkaufer = res.getInt("idVerkaufer");
							preisprodukt = res.getString("Preis");
							währung = res.getString("W�hrung");
							nameprodukt = res.getString("Nameprodukte");
							break;
						}
						
						if(idverkaufer !=0) {
							req = "INSERT INTO rechnung(Gesamtmenge,Datumrechnung,idWarenkob,idKaufer,idVerkaufer,"
									+ "Gesamtpreis,Nameprodukt) VALUES(1,?,?,?,?,?,?)";
							
							preparedstatement = DBconnection.getInstance().getPreparedStatement(req);
							preparedstatement.setString(1, datestring);
							preparedstatement.setInt(2, resultset.getInt("idWarenkob"));
							preparedstatement.setInt(3, resultset.getInt("idKaufer"));
							preparedstatement.setInt(4, idverkaufer);
							preparedstatement.setString(5, preisprodukt);
							preparedstatement.setString(6,nameprodukt);
							rueck=preparedstatement.executeUpdate();
							if(rueck==1) {
								updateWarekob(resultset.getInt("idWarenkob"), "warnungcount",null,null);
							}
							
							
						}
						
								
					}
				}
				
				request ="SELECT * FROM Person WHERE idPerson=?";
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, person.getId());
				resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					rueck = resultset.getInt("warnunganzahl");
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return rueck;
	}
	
	public List<Rechnung> getRechnung(String type, int id,String datum){
		List<Rechnung> listrechnung=new ArrayList<>();
		String request =null;
		Verkaufer verkaufer = null;
		Unternehmen unternehmen = null;
		Rechnung rechnung = null;
		if(type.equals("kaufer")) {
			request ="SELECT * FROM rechnung as rech INNER JOIN verkaufer as ver ON rech.idVerkaufer = ver.idVerkaufer"
				+" INNER JOIN unternehmen as unt ON unt.idVerkaufer = ver.idVerkaufer"
				+" WHERE rech.idKaufer=?";
			
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, id);
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					
					unternehmen = new Unternehmen();
					verkaufer = new Verkaufer();
					rechnung = new Rechnung();
					unternehmen.setName(resultset.getString("Nameunternehmen"));
					unternehmen.setStadt(resultset.getString("Stadt"));
					unternehmen.setStandort(resultset.getString("Standort"));
					verkaufer.setUnternehmen(unternehmen);
					
					rechnung.setNameProdukte(resultset.getString("Nameprodukt"));
					rechnung.setDatum(resultset.getString("Datumrechnung"));
					rechnung.setGesamtmenge(resultset.getString("Gesamtmenge"));
					rechnung.setGesamtpreis(resultset.getString("Gesamtpreis"));
					rechnung.setIdRechnung(resultset.getInt("idRechnung"));
					rechnung.setVerkaufer(verkaufer);
					
					listrechnung.add(rechnung);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("verkaufer")) {
			
		
		}else if(type.equals("rechnung_filtern_kaufer")) {
			request ="SELECT * FROM rechnung as rech INNER JOIN verkaufer as ver ON rech.idVerkaufer = ver.idVerkaufer"
					+" INNER JOIN unternehmen as unt ON unt.idVerkaufer = ver.idVerkaufer"
					+" WHERE rech.idKaufer=? AND Datumrechnung LIKE ? ";
			
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, id);
				preparedstatement.setString(2,"%"+datum+"%");
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					
					unternehmen = new Unternehmen();
					verkaufer = new Verkaufer();
					rechnung = new Rechnung();
					unternehmen.setName(resultset.getString("Nameunternehmen"));
					unternehmen.setStadt(resultset.getString("Stadt"));
					unternehmen.setStandort(resultset.getString("Standort"));
					verkaufer.setUnternehmen(unternehmen);
					
					rechnung.setNameProdukte(resultset.getString("Nameprodukt"));
					rechnung.setDatum(resultset.getString("Datumrechnung"));
					rechnung.setGesamtmenge(resultset.getString("Gesamtmenge"));
					rechnung.setGesamtpreis(resultset.getString("Gesamtpreis"));
					rechnung.setIdRechnung(resultset.getInt("idRechnung"));
					rechnung.setVerkaufer(verkaufer);
					
					listrechnung.add(rechnung);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return listrechnung;
	}
	
	public String SummeGeld(String type, int id_person) {
		String request = null;
		String summe ="0";
		
		if(type.equals("kaufer")) {
			//Geldausgabe
			request = "SELECT * FROM rechnung WHERE idKaufer=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, id_person);
			
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					
					summe = String.valueOf(Double.valueOf(summe)+ Double.valueOf(resultset.getString("Gesamtpreis")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(type.equals("verkaufer")) {
			// Summe der Verk�ufe
			request = "SELECT Gesamtpreis FROM rechnung WHERE idVerkaufer=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, id_person);
			
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					summe = String.valueOf(Double.valueOf(summe)+ Double.valueOf(resultset.getString("Gesamtpreis")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return summe;
	}
	public int insertBewertung(String kommentar, String sterne, int idkaufer, int idProdukte,int idWarenkob,String type) {
		request="INSERT INTO bewertung(Kommentar,idKaufer,idProdukte,Sterne) VALUES(?,?,?,?)";
		if(type.equals("ja")) {
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setString(1, kommentar);
				preparedstatement.setInt(2,idkaufer );
				preparedstatement.setInt(3,idProdukte );
				preparedstatement.setString(4,sterne );
				
				if(preparedstatement.executeUpdate()==1) {
					request = "UPDATE warenkob SET bewertet = 'ja' WHERE idWarenkob=?";
					preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
					preparedstatement.setInt(1, idWarenkob);
					return preparedstatement.executeUpdate();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("nein")){
			request = "UPDATE warenkob SET bewertet = 'ja' WHERE idWarenkob=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idWarenkob);
				return preparedstatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return 0;
	}
	
	
	public int checkIfBewertunggelesen(int idverkaufer) {
		int rueck=0;
		request =" SELECT * FROM bewertung AS be "
				+ "INNER JOIN produkte AS prod "
				+ "ON be.idProdukte = prod.idProdukte "
				+ "INNER JOIN verkaufer AS ver "
				+ "ON prod.idVerkaufer = ver.idVerkaufer "
				+ "WHERE ver.idVerkaufer =? "
				+ "AND be.Statuskom='-'";
		
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idverkaufer);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				rueck++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
			
		return rueck;
	}
	
	public int UpdateBewertung(String type, int idverkaufer) {
		int rueck=0;
		
		if(type.equals("commentalreadyread")) {
			request ="UPDATE bewertung SET Statuskom ='ja' "
					+ "WHERE idProdukte IN (SELECT prod.idProdukte FROM produkte as prod "
					+ "INNER JOIN verkaufer AS ver "
					+ "ON prod.idVerkaufer= ver.idVerkaufer "
					+ "WHERE ver.idVerkaufer = ?)";
			
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idverkaufer);
				rueck = preparedstatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return rueck;
	}
	
	public List<Warenkob> getListabgeholteBestellungKunde(int idKaufer){
		List<Warenkob> list = new ArrayList<>();
		request="SELECT * FROM warenkob WHERE idKaufer=? AND confirmkunde='ja' ";
		Produkt produkt =null;
		Kaufer kaufer =new Kaufer();
		Warenkob warenkob = null;
		kaufer.setIdKaufer(idKaufer);
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1,idKaufer);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				warenkob = new Warenkob();
				produkt = getProdukt(resultset.getInt("idProdukte"));
				warenkob.setProdukt(produkt);
				warenkob.setDatum(resultset.getString("Datum"));
				warenkob.setMenge(resultset.getString("Menge"));
				warenkob.setStatus(resultset.getString("Status"));
				warenkob.setIdWarenkob(resultset.getInt("idWarenkob"));
				warenkob.setConfirmdatumkunde(resultset.getString("confirmdate"));
				list.add(warenkob);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Warenkob> getListBestellungverkaufer(int idverkaufer){
		List<Warenkob> list = new ArrayList<>();
		List<Integer> listidwarenkob = new ArrayList<>();
		List<String> Pseudokaufer = new ArrayList<>();
		request="SELECT * FROM warenkob AS wa "
				+ "INNER JOIN produkte AS prod "
				+ "ON wa.idProdukte = prod.idProdukte "
				+ "INNER JOIN verkaufer AS verk "
				+ "ON prod.idVerkaufer = verk.idVerkaufer "
				+ "INNER JOIN kaufer as kau "
				+ "ON kau.idKaufer = wa.idKaufer "
				+ "WHERE verk.idVerkaufer=? ";
		
		Produkt produkt =null;
		Kaufer kaufer =new Kaufer();
		Warenkob warenkob = null;
		kaufer.setIdKaufer(idverkaufer);
		int counter =0;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1,idverkaufer);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				if(!listidwarenkob.contains(resultset.getInt("idWarenkob"))) {
					Pseudokaufer.add(resultset.getString("Pseudo"));
					
					listidwarenkob.add(resultset.getInt("idWarenkob"));
				}
				

			}
			
			for(int idwarenkob: listidwarenkob) {
				request = "SELECT * FROM warenkob WHERE idWarenkob=? AND confirmverkaufer='-'";
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idwarenkob);
				resultset = preparedstatement.executeQuery();
				
				while(resultset.next()) {
					kaufer = new Kaufer();
					kaufer.setPseudo(Pseudokaufer.get(counter));
					warenkob = new Warenkob();
					produkt = getProdukt(resultset.getInt("idProdukte"));
					warenkob.setProdukt(produkt);
					warenkob.setDatum(resultset.getString("Datum"));
					warenkob.setMenge(resultset.getString("Menge"));
					warenkob.setStatus(resultset.getString("Status"));
					warenkob.setIdWarenkob(resultset.getInt("idWarenkob"));
					warenkob.setKaufer(kaufer);
					list.add(warenkob);
					counter++;
					break;
				}
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Bewertung>getBewertungkundeseite(int idprodukt){
		
		List<Bewertung>list = new ArrayList<>();
		Kaufer kaufer = null;
		Bewertung bewertung =null;
		request="SELECT * FROM bewertung AS be "
				+ "INNER JOIN kaufer AS kau "
				+ "ON be.idKaufer=kau.idKaufer "
				+ "WHERE be.idProdukte = ?";
				
		
		
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idprodukt);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				kaufer = new Kaufer();
				kaufer.setIdKaufer(resultset.getInt("idKaufer"));
				kaufer.setPseudo(resultset.getString("pseudo"));
				bewertung = new Bewertung();
				bewertung.setIdBewertung(resultset.getInt("idBewertung"));
				bewertung.setKaufer(kaufer);
				bewertung.setKommentar(resultset.getString("kommentar"));
				bewertung.setSterne(resultset.getString("sterne"));
				list.add(bewertung);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int buttonNachweisAutoclick(int idkaufer,String browserid) {
		
		request = "UPDATE kaufer SET browserid=? WHERE idKaufer =? ";
		
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, browserid);	
			preparedstatement.setInt(2, idkaufer);
			return preparedstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	
	public int checkAufClickaufButtonNachweisAuto(int idkaufer,String type,String browserid) {
		
		int counter =0;
		if(type.equals("bevorclick")) {
			request = "SELECT * FROM kaufer WHERE idKaufer =? AND browserid='-'";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idkaufer);
				
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					counter++;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if(type.equals("nachclick")) {
			request = "SELECT * FROM kaufer WHERE idKaufer =? AND browserid=?";
			try {
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idkaufer);
				preparedstatement.setString(2, browserid);
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					counter++;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
		return counter;
		
	}
	
	
	public int checkifgeolocationvorhanden(int idwarenkob) {
		int rueck=0;
		request="SELECT * FROM warenkob WHERE idWarenkob=? AND latitudek='-' AND longitudek='-'";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idwarenkob);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				rueck=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rueck;
	}
	
	public Map<String,Object> getGroupBuyers(String name,String vorname,String email,String pseudo){
		Map<String,Object>map = new HashMap<>();
		List <Kaufer>list = new ArrayList<>();
		userDAO dao1 = new userDAO();
		String request = "SELECT * FROM person as pers INNER JOIN kaufer as kau ON pers.idPerson= kau.idPerson "
				+ "WHERE pers.nameperson LIKE ? OR pers.vorname LIKE ? OR pers.email LIKE ? OR kau.pseudo LIKE ? ";
	
		int count=0;
		Kaufer kaufer= null; 
		try {
			PreparedStatement preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, "%"+name+"%");
			preparedstatement.setString(2, "%"+vorname+"%");
			preparedstatement.setString(3, "%"+email+"%");
			preparedstatement.setString(4, "%"+pseudo+"%");
			
			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				kaufer = new Kaufer();
				kaufer.setIdKaufer(resultset.getInt("idKaufer"));
				kaufer.setPseudo(resultset.getString("pseudo"));
				kaufer.setListwarenkob( getListBestellungKunde(resultset.getInt("idKaufer")));
				kaufer.setListrechnung(getRechnung("kaufer", resultset.getInt("idKaufer"), null));
				kaufer.setPerson(getPerson(resultset.getString("email")));
				
				
				list.add(kaufer);
				count++;
			}
			map.put("count", count);
			map.put("buyers", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Size :"+list.size());
		return map;
	}

}
