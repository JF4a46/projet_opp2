/*
/ * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Client;

/**
 *
 * @author Joel
 * Une query vers la base de données
 */
public class ClientStatement extends dbStatement {
	public ClientStatement(){
        super(dbConnect.getStatement());
        this.setTable("client");
    }
    
    public void save(Client client){
    	this.setValue("nom", client.getNom());
    	this.setValue("prenom", client.getPrenom());
//    	this.setValue("telephone_maison", client.getTelephoneMaison());
//    	this.setValue("cellulaire", client.getCellulaire());
//    	this.setValue("telephone_bureau", client.getTelephoneBureau());
//    	this.setValue("date_naissance", client.getDateNaissance().toString());
//    	this.setValue("courriel", client.getCourriel());
//    	this.setValue("adresse", client.getAdresse());
    	this.setValue("permis_conduire", client.getPermisConduire());
    	this.setValue("classe_conduite", "");
    	this.setValue("programme_point", "");
    	try {
			this.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return;
    }

    public ArrayList<Integer> getClientIdByPhone(String phone) {
    	ArrayList<Integer> clientId = new ArrayList<Integer>();
    	this.setColumn("id");
    	this.where("telephone_maison", phone, "string", "or");
    	this.where("cellulaire", phone, "string", "or");
    	this.where("telephone_bureau", phone, "string", "or");
    	try{
    		ResultSet rs = this.select();

			while (rs.next()) {
				clientId.add(rs.getInt("id"));
			}
    	} catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	return clientId;
    }
}

