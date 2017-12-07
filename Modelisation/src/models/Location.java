package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import ui_projet.Magasin;

public class Location {

	private ParametresFacturation params;
	private String phoneNumber;
	private Client client;
	private Vehicule vehicule;
	private int id;
	private int[] startDate, endDate;
	private int montantDue;
	public boolean assurances;
	public boolean kiloIllim;
	private String immatriculation;
	public int classe;

	public Location(ParametresFacturation params, Client client, int Id) {
		this.id = Id;
		this.client = client;
		this.phoneNumber = client.getTelephone();
		client.setLocationNum(Id);
		this.params = params;
	}

	/*
	 * public Location(Client client) { ======= private String immatriculation;
	 * 
	 * public Location(ParametresFacturation params, Client client) { this.numID =
	 * IDNumber; this.client = client; IDNumber++; this.params = params; } public
	 * Location(Client client) { >>>>>>> cfcda3e8c0c77bf90c74228ac8bfd72971544b55
	 * this.numID = IDNumber; this.client = client;
	 * setPhoneNumber(client.getTelephone()); client.setLocationNum(numID);
	 * IDNumber++; <<<<<<< HEAD }
	 */
	// =======
	// }

	/*
	 * public Location(Client client, ParametresFacturation params, int[] startDate,
	 * int[] endDate, int numId, String immatriculation) { setClient(client);
	 * this.params = params; setStartDate(startDate); setEndDate(endDate);
	 * setPhoneNumber(client.getTelephone()); client.setLocationNum(id);
	 * setNumID(numId); }
	 */

	public Location(Client client, ParametresFacturation params, int[] startDate, int[] endDate, int numId,
			String immatriculation) {
		setClient(client);
		this.params = params;
		setStartDate(startDate);
		setEndDate(endDate);
		setPhoneNumber(client.getTelephone());
		client.setLocationNum(id);
		setNumID(numId);
		this.immatriculation = immatriculation;
	}

	// >>>>>>> cfcda3e8c0c77bf90c74228ac8bfd72971544b55

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public boolean isAssurances() {
		return assurances;
	}

	public boolean isKiloIllim() {
		return kiloIllim;
	}

	public int getNumID() {
		return id;
	}

	public void setNumID(int numID) {
		this.id = numID;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public int[] getStartDate() {
		return startDate;
	}

	public String startDateToString() {

		return "" + startDate[0] + " " + startDate[1] + " " + startDate[2] + " " + startDate[3];
	}

	public String endDateToString() {

		return "" + endDate[0] + " " + endDate[1] + " " + endDate[2] + " " + endDate[3];
	}

	public void setStartDate(int[] startDate) {
		this.startDate = startDate;
	}

	public int[] getEndDate() {
		return endDate;
	}

	public void setEndDate(int[] endDate) {
		this.endDate = endDate;
	}

	public int getMontantDue() {
		return montantDue;
	}

	public void setMontantDue(int montantDue) {
		this.montantDue = montantDue;
	}

	public void setAssurances(boolean assurances) {
		this.assurances = assurances;
	}

	public void setKiloIllim(int i) {

		if (i == 1)
			this.kiloIllim = true;
		else
			this.kiloIllim = false;

	}

	public double calculerPrix() {
		long jours = TimeUnit.MILLISECONDS.toDays(Magasin.makeCalendar(this.endDate).getTimeInMillis()
				- Magasin.makeCalendar(this.startDate).getTimeInMillis());

		double coutTotal = 0;
		double coutJour = 0;

		if (assurances) {
			coutTotal += params.getAssurance();
		}

		if (kiloIllim) {
			coutTotal += params.getForfaitIllimiteKM();
		}

		switch (classe) {
		case 0:
			coutJour = this.params.getClasseEconomique();
			break;
		case 1:
			coutJour = this.params.getClasseMoyenne();
			break;
		case 2:
			coutJour = this.params.getClasseConfort();
			break;
		case 3:
			coutJour = this.params.getClasseLuxe();
			break;
		case 4:
			coutJour = this.params.getClasseUtilitaire();
			break;
		}
		// TODO ajouter les options de cout supp.
		coutTotal += jours * coutJour;
		coutTotal = coutTotal + coutTotal * params.getTaxeFederale();
		coutTotal = coutTotal + coutTotal * params.getTaxeProvinciale();
		return coutTotal;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAssurances(int parseInt) {

		if (parseInt == 1) {
			setAssurances(true);

		} else
			setAssurances(false);

	}

	public void setKiloIllim(boolean b) {
		kiloIllim = b;
	}

}