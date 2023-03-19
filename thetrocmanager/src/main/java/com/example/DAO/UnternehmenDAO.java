package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Foto;
import com.example.models.Unternehmen;

@Service
public class UnternehmenDAO {
	private Statement statement;
	private PreparedStatement preparedstatement;
	private String request;
	private ResultSet resultset;
	
	public UnternehmenDAO() {
		
	}
	
	public boolean infoSpeichern(Unternehmen unternehmen) {
		boolean rueckgabe= false;
		request ="INSERT INTO unternehmen(Nameunternehmen,Standort,Stadt,idVerkaufer,Latitude,Logitude) VALUES(?,?,?,?,?,?)";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, unternehmen.getName());
			preparedstatement.setString(2, unternehmen.getStandort());
			preparedstatement.setString(3, unternehmen.getStadt());
			preparedstatement.setInt(4, unternehmen.getVerkaufer().getIdVerkaufer());
			preparedstatement.setString(5, unternehmen.getGeolatidude());
			preparedstatement.setString(6, unternehmen.getGeolongitude());
			
			if(preparedstatement.executeUpdate()==1) {
				rueckgabe=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rueckgabe;
	}
	
	public boolean infoUpdate(Unternehmen unternehmen) {
		boolean rueckgabe= false;
		request ="UPDATE unternehmen SET Nameunternehmen=?, Standort=? ,Stadt=?,Latitude=?,Longitude=? WHERE idVerkaufer=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, unternehmen.getName());
			preparedstatement.setString(2, unternehmen.getStandort());
			preparedstatement.setString(3, unternehmen.getStadt());
			preparedstatement.setInt(6, unternehmen.getVerkaufer().getIdVerkaufer());
			preparedstatement.setString(4, unternehmen.getGeolatidude());
			preparedstatement.setString(5, unternehmen.getGeolongitude());
			
			if(preparedstatement.executeUpdate()==1) {
				rueckgabe=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rueckgabe;
	}
	
	public Unternehmen getUnternehmen(int idVerkaufer) {
		Unternehmen unternehmen = new Unternehmen();
		request ="SELECT * FROM unternehmen WHERE idVerkaufer=?" ;
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idVerkaufer);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				unternehmen.setIdUnternehmen(resultset.getInt("idUnternehmen"));
				unternehmen.setName(resultset.getString("Nameunternehmen"));
				unternehmen.setStandort(resultset.getString("Standort"));
				unternehmen.setStadt(resultset.getString("Stadt"));
				unternehmen.setGeolatidude(resultset.getString("Latitude"));
				unternehmen.setGeolongitude(resultset.getString("Longitude"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unternehmen;
	}
	
	
	
	
	public boolean isUnternehmenVorhanden(Unternehmen unternehmen) {
		boolean rueckgabe= false;
		
		request =" SELECT * FROM unternehmen ";
		try {
			statement = DBconnection.getInstance().getStatement();
			resultset = statement.executeQuery(request);
			while(resultset.next()) {
				if(resultset.getInt("idVerkaufer") == unternehmen.getVerkaufer().getIdVerkaufer()) {
					rueckgabe =true;
					break;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rueckgabe;
		
	}
	
	
	public boolean bilderUnternehmenSpeichern(int idUnternehmen,Foto foto) {
		boolean check = false;
		request = "INSERT INTO Bilderunternehmen(Namebildunternehmen,Groesse,Extension,idUnternehmen) VALUES(?,?,?,?)";
		
		try {
			preparedstatement =DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, foto.getName());
			preparedstatement.setString(2,String.valueOf(foto.getSize()));
			preparedstatement.setString(3, foto.getExtension());
			preparedstatement.setInt(4, idUnternehmen);
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
	
	
	public List<Foto> getBilderUnternehmen(Unternehmen unternehmen) {
		List<Foto>list = new ArrayList<>();
		Foto foto =null;
		request ="SELECT * FROM bilderunternehmen WHERE idUnternehmen=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, unternehmen.getIdUnternehmen());
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				foto = new Foto(resultset.getString("Namebildunternehmen"), Long.valueOf(resultset.getString("Groesse")), resultset.getString("Extension"),resultset.getInt("idBilderunternehmen"), unternehmen);
				list.add(foto);
			}
			unternehmen.setBilder(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBconnection.getInstance().closeConnection();
		}
		return list;
	}
	
	public boolean bildlöschen(Foto foto) {
		request ="DELETE FROM bilderunternehmen WHERE idBilderunternehmen=?";
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
		request ="SELECT * FROM bilderunternehmen WHERE Namebildunternehmen=? AND Groesse=?";
		Foto foto = new Foto();
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, size);
			resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				foto.setName(name);
				foto.setSize(Long.valueOf(size));
				foto.setIdFoto(resultset.getInt("idBilderunternehmen"));
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
	
	public boolean isAnzahlBilderErreicht(int idUnternehmen) {
		boolean rueckgabe = false;
		int rueck=0;
		request ="SELECT * FROM bilderunternehmen WHERE idUnternehmen=?";
		try {
			preparedstatement = DBconnection.getInstance().getPreparedStatement(request);
			preparedstatement.setInt(1, idUnternehmen);
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

}
