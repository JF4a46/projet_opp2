package models;

import java.util.Date;

public class Client {

	private String nom;
	private String prenom;
	private String telephone;
	private String permisConduire;
	private int locationNum;

	public Client(String nom, String prenom, String telephone, String permisConduire) {

		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.permisConduire = permisConduire;
	}

	public Client(String nom, String prenom, String telephone, String permisConduire, int numId) {

		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.permisConduire = permisConduire;
		setLocationNum(numId);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPermisConduire() {
		return permisConduire;
	}

	public void setPermisConduire(String permisConduire) {
		this.permisConduire = permisConduire;
	}

	public int getLocationNum() {
		return locationNum;
	}

	public void setLocationNum(int locationNum) {
		this.locationNum = locationNum;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", permisConduire="
				+ permisConduire + ", locationNum=" + locationNum + "]";
	}

}