package com.example.DAO;

import java.io.File;
import java.io.InputStream;
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

import org.springframework.stereotype.Service;

import com.example.models.Foto;
import com.example.models.Person;
import com.example.models.Produkt;
import com.example.models.Unternehmen;
import com.example.models.Verkaufer;
import com.example.models.Warenkob;


@Service
public class VerkauferDAO {

	private Statement statement;
	private ResultSet resultset;
	private PreparedStatement preparedstatement;
	private String request;
	
	public boolean produktErstellung(Produkt produkt, int idVerkaufer) {
		boolean rueckgabe=false;
		request= "INSERT INTO produkte(Marke,Preis,Kategorie,Nameprodukte,Menge,Mengeeinheit,Beschreibung,Ablaufdatum,Onlinebis,Onlinetime,Dauerbisabholung,Status,W�hrung,idVerkaufer) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1,produkt.getMarke());
			preparedstatement.setString(2,produkt.getPreis());
			preparedstatement.setString(3,produkt.getKategorie());
			preparedstatement.setString(4,produkt.getName());
			preparedstatement.setString(5,produkt.getMenge());
			preparedstatement.setString(6, produkt.getMengeeinheit());
			preparedstatement.setString(7,produkt.getBeschreibung());
			preparedstatement.setString(8,produkt.getAblaufdatum());
			preparedstatement.setString(9,produkt.getOnlinebis());
			preparedstatement.setString(10,produkt.getOnlinetime());
			preparedstatement.setString(11,produkt.getDauerbisabholung());
			preparedstatement.setString(12,produkt.getStatus());
			preparedstatement.setString(13,produkt.getWährung());
			preparedstatement.setInt(14,idVerkaufer);
			
			int result = preparedstatement.executeUpdate();
			if(result==1) {
				rueckgabe=true;
			}
			
		} catch (SQLException e) {
			
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		
		return rueckgabe;
	}
	
	public Verkaufer getVerkaufer(Person person) {
		Verkaufer verkaufer = new Verkaufer();
		request = "SELECT * FROM verkaufer WHERE idPerson=?" ;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, person.getId());
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				verkaufer.setIdVerkaufer(resultset.getInt("idVerkaufer"));
				verkaufer.setStatus(resultset.getString("Status"));
				
			}
			verkaufer.setPerson(person);
			getListProdukt(verkaufer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return verkaufer;
	}
	
	public Map<String,Object> getAllVerkaufer(){
		Map<String,Object>map = new HashMap<>();
		List <Verkaufer>list = new ArrayList<>();
		userDAO dao1 = new userDAO();
		String request = "SELECT * FROM verkaufer as ver INNER JOIN unternehmen as unt ON ver.idVerkaufer= unt.idVerkaufer";
		Statement statement;
		int count=0;
		Verkaufer verkaufer = null; 
		Unternehmen unternehmen = null;
		try {
			statement = DBconnection.getInstance().getStatement();
			ResultSet resultset = statement.executeQuery(request);
			while (resultset.next()) {
				verkaufer = new Verkaufer();
				unternehmen = new Unternehmen();
				verkaufer.setIdVerkaufer(resultset.getInt("idVerkaufer"));
				verkaufer.setStatus(resultset.getString("Status"));
				verkaufer.setPerson(dao1.getPerson(resultset.getInt("idPerson")));
				unternehmen.setIdUnternehmen(resultset.getInt("idUnternehmen"));
				unternehmen.setName(resultset.getString("Nameunternehmen"));
				unternehmen.setStadt(resultset.getString("Stadt"));
				unternehmen.setStandort("Standort");
				unternehmen.setGeolatidude("Latitude");
				unternehmen.setGeolongitude("Longitude");
				getListProdukt(verkaufer);
				list.add(verkaufer);
				count++;
			}
			map.put("count", count);
			map.put("allsellers", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Size :"+list.size());
		return map;
	}
	public Map<String,Object> getVerkaufer(String email,String name,String vorname, String nameunternehmen){
		Map<String,Object>map = new HashMap<>();
		List <Verkaufer>list = new ArrayList<>();
		userDAO dao1 = new userDAO();
		String request = "SELECT * FROM person AS pers INNER JOIN Verkaufer AS ver ON pers.idPerson= ver.idPerson "
				+ "INNER JOIN unternehmen AS unt ON ver.idVerkaufer= unt.idVerkaufer "
				+ "WHERE pers.email LIKE ? || pers.nameperson LIKE ? || pers.vorname LIKE ? || unt.nameunternehmen LIKE ?";
		
		int count=0;
		Verkaufer verkaufer = null; 
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, "%"+email+"%");
			preparedstatement.setString(2, "%"+name+"%");
			preparedstatement.setString(3, "%"+vorname+"%");
			preparedstatement.setString(4, "%"+nameunternehmen+"%");
			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				verkaufer = new Verkaufer();
				verkaufer.setIdVerkaufer(resultset.getInt("idVerkaufer"));
				verkaufer.setStatus(resultset.getString("Status"));
				verkaufer.setPerson(dao1.getPerson(resultset.getInt("idPerson")));
				getListProdukt(verkaufer);
				list.add(verkaufer);
				count++;
			}
			map.put("count", count);
			map.put("allsellers", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
	
	
	public List<Produkt> getListProdukt(Verkaufer verkaufer){
		List<Produkt> list = new ArrayList<>();
		Produkt produkt;
		request = "SELECT * FROM produkte WHERE idVerkaufer=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, verkaufer.getIdVerkaufer());
			resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				produkt = new Produkt();
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
				produkt.setVerkaufer(verkaufer);
				list.add(produkt);
			}
			
			verkaufer.setProduktList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean bilderProduktSpeichern(int idProdukt,Foto foto) {
		boolean check = false;
		request = "INSERT INTO Bilderprodukt(Namebildprodukt,Groesse,Extension,Inhalt,idProdukte) VALUES(?,?,?,?,?)";
		
		try {
			preparedstatement =DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, foto.getName());
			preparedstatement.setString(2,String.valueOf(foto.getSize()));
			preparedstatement.setString(3, foto.getExtension());
			preparedstatement.setBinaryStream(4, foto.getInhalt());
			preparedstatement.setInt(5, idProdukt);
			int result = preparedstatement.executeUpdate();
			if(result==1) {
				check=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	public List<Foto> getBilderProdukt(Produkt produkt) {
		List<Foto>list = new ArrayList<>();
		Foto foto =null;
		request ="SELECT * FROM Bilderprodukt WHERE idProdukte=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, produkt.getIdProdukt());
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				foto = new Foto(resultset.getString("Namebildprodukt"), Long.valueOf(resultset.getString("Groesse")), resultset.getString("Extension"),resultset.getInt("idBilderprodukt"),resultset.getBinaryStream("Inhalt"), produkt);
				list.add(foto);
			}
			produkt.setProduktBilder(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		return list;
	}
	
	public boolean bildlöschen(Foto foto) {
		request ="DELETE FROM Bilderprodukt WHERE idBilderprodukt=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, foto.getIdFoto());
			if(preparedstatement.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Foto getBildProduktInfos(String name, String size) {
		request ="SELECT * FROM bilderprodukt WHERE Namebildprodukt=? AND Groesse=?";
		Foto foto = new Foto();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, size);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				foto.setName(name);
				foto.setSize(Long.valueOf(size));
				foto.setIdFoto(resultset.getInt("idBilderprodukt"));
				foto.setExtension(resultset.getString("Extension"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		return foto;
	}
	
	public boolean isAnzahlBilderErreicht(int idProdukt) {
		boolean rueckgabe = false;
		int rueck=0;
		request ="SELECT * FROM bilderprodukt WHERE idProdukte=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idProdukt);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				rueck++;
			}
			
			if(rueck<4) {
				rueckgabe = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		return rueckgabe;
	}
	
	
	public Foto getFirstFotoProdukt(int idprodukt) {
		request ="SELECT * FROM bilderprodukt WHERE idProdukte =?";
		Foto foto = null;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idprodukt);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				foto = new Foto();
				foto.setIdFoto(resultset.getInt("idBilderprodukt"));
				foto.setName(resultset.getString("Namebildprodukt"));
				foto.setExtension(resultset.getString("Extension"));
				foto.setSize(Long.valueOf(resultset.getString("Groesse")));
				
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		return foto;
	}
	
	public boolean produktVeroeffentlichen(int idprodukt,String funktion) {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		if(funktion.equals("online")) {
			request ="UPDATE produkte SET Status='online' WHERE idprodukte=?";
		}else if(funktion.equals("offline")) {
			request ="UPDATE produkte SET Status='offline' WHERE idprodukte=?";
		}
		
		boolean rueck= false;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idprodukt);
			if(preparedstatement.executeUpdate()==1) {
				
				request="UPDATE produkte SET Datum= ? WHERE idprodukte=?";
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setString(1, dateformat.format(new Date()));
				preparedstatement.setInt(2, idprodukt);
				if(preparedstatement.executeUpdate()==1) {
					rueck=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rueck;
	}
	
	public boolean produktloeschen(int idprodukt,String userEmail) {
		//zuerst alle bilder physich l�schen
		Produkt produkt = new Produkt();
		produkt.setIdProdukt(idprodukt);
		List <Foto> list = getBilderProdukt(produkt);
		for (Foto foto : list) {
			File file = new File("C:\\Users\\user\\eclipse-workspace\\Thetroc1\\src\\main\\webapp\\Dateien\\"+userEmail+"\\"+foto.getName());
			if(file.exists()) {
				file.delete();
			}
		}
		
		boolean check = false;
		try {
			
			if(getFirstFotoProdukt(idprodukt)!=null) {
				request="DELETE FROM bilderprodukt WHERE idprodukte=?";
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idprodukt);
				if(preparedstatement.execute()==false) {
					request = "DELETE FROM produkte WHERE idprodukte=?";
					preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
					preparedstatement.setInt(1, idprodukt);
					
					if(preparedstatement.execute()==false) {
						check= true;
					}
				}
			}else {
				request = "DELETE FROM produkte WHERE idprodukte=?";
				preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
				preparedstatement.setInt(1, idprodukt);
				if(preparedstatement.executeUpdate()==1) {
					check= true;
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	public List<Warenkob> getListAktuellBestellung(int idVerkaufer){
		request = "SELECT * FROM warenKob AS wa "
				+ "INNER JOIN produkte AS pr "
				+ "ON wa.idProdukte = pr.idProdukte "
				+ "INNER JOIN kaufer AS kau "
				+ "ON wa.idKaufer= kau.idKaufer "
				+ "INNER JOIN person AS pers "
				+ "ON pers.idPerson = kau.idPerson "
				+ "INNER JOIN verkaufer AS ver "
				+ "ON ver.idVerkaufer = pr.idVerkaufer "
				+ "WHERE ver.idVerkaufer = ? AND wa.Status='austehend'";
		Warenkob warenkob ;
		List <Warenkob> list = new ArrayList<>();
		List <Integer> idWarenkob = new ArrayList<>();
		 
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idVerkaufer);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				warenkob = new Warenkob();
				warenkob.setIdWarenkob(resultset.getInt("idWarenkob"));
				warenkob.setIdKaufer(new userDAO().getKaufer(new userDAO().getPerson(resultset.getInt("idPerson"))));
				warenkob.setProdukt(new userDAO().getProdukt(resultset.getInt("idProdukte")));
				warenkob.setMenge(resultset.getString("Menge"));
				if(!idWarenkob.contains(resultset.getInt("idWarenkob"))) {
					idWarenkob.add(resultset.getInt("idWarenkob"));
					list.add(warenkob);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public boolean updateWarenkob(String date,String status,int idWarenkob) {
		request="UPDATE warenkob SET Status=?, Datum=? WHERE idWarenkob=?";
		boolean result=false;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, status);
			preparedstatement.setString(2, date);
			preparedstatement.setInt(3, idWarenkob);
			
			if(preparedstatement.executeUpdate()==1) {
				result= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
 public Map<Integer, Map<String,String>> getProdid_Commentkaufer_sternkaufer_(int idKaufer){
	 request ="SELECT be.Kommentar,be.Sterne,be.idProdukte,kau.pseudo,be.idKaufer FROM bewertung AS be "
	 		+ "INNER JOIN produkte AS prod "
	 		+ "ON be.idProdukte = prod.idProdukte "
	 		+ "INNER JOIN verkaufer AS ver "
	 		+ "ON prod.idVerkaufer= ver.idVerkaufer "
	 		+ "INNER JOIN Kaufer AS kau "
	 		+ "ON kau.idKaufer = be.idKaufer "
	 		+ "WHERE ver.idVerkaufer =?";
	 		
	 Map<Integer, Map<String,String>> rueckgabe = new HashMap<>();
	 Map<String,String> kommentar = new HashMap<>();
	 List<Integer>idproduktList = new ArrayList<>();
	 
	 try {
		preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
		preparedstatement.setInt(1, idKaufer);
		ResultSet resultset = preparedstatement.executeQuery();
		
		while(resultset.next()) {
			if(!idproduktList.contains(resultset.getInt("idProdukte"))) {
				idproduktList.add(resultset.getInt("idProdukte"));
			}
		}
		resultset = preparedstatement.executeQuery();
		int counter=0;
		for(Integer id: idproduktList) {
			
			kommentar = new HashMap<>();
			while(resultset.next()) {
				if(resultset.getInt("idProdukte")==id) {
					kommentar.put(resultset.getString("Pseudo")+"-"+counter,resultset.getString("Kommentar")+"~"+resultset.getString("Sterne"));
					counter++;
				}
				
			}
			counter=0;
			rueckgabe.put(id, kommentar);
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 return rueckgabe;
 }
 
 public int checkIfverkauferKonfirm(int idVerkaufer) {
	 int rueck=0;
	 request ="SELECT * FROM warenkob AS wa "
	 		+ "INNER JOIN produkte AS pr "
	 		+ "ON wa.idProdukte = pr.idProdukte "
	 		+ " WHERE wa.status='bestaetigt'AND confirmverkaufer='-' AND dauerabhol='zeitabgelaufen' AND pr.idVerkaufer=?";
	 
	 try {
		preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
		preparedstatement.setInt(1, idVerkaufer);
		
		ResultSet resultset = preparedstatement.executeQuery();
		
		while(resultset.next()) {
			
			request="UPDATE produkte SET Status='offline' WHERE idVerkaufer=?";
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idVerkaufer);
			if(preparedstatement.executeUpdate()>0) {
				rueck=1;
			}
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return rueck;
 }
 
 public int updateprodukt (String type,String wert, int idverkaufer) {
	 int rueck =0;
	 if(type.equals("status")) {
		 request = "UPDATE produkte SET status=? WHERE idVerkaufer=?";
		 try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, wert);
			preparedstatement.setInt(2, idverkaufer);
			rueck = preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	
	return rueck; 
 }
 
 public int getwaitingcommandnummber(int idverkaufer) {
	 int rueck=0;
	 request = "SELECT * FROM warenkob AS wa "
	 		+ "INNER JOIN produkte AS prod "
	 		+ "ON wa.idProdukte = prod.idProdukte "
	 		+ "INNER JOIN verkaufer AS ver "
	 		+ "ON ver.idVerkaufer = prod.idVerkaufer "
	 		+ "WHERE prod.idVerkaufer = ? AND wa.Status='austehend'";
	 
	 try {
		preparedstatement= DBconnection.getInstance().getPreparedStatement(request);
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
 
 
}
