package models;

import java.util.ArrayList;
import java.util.Calendar;

public class Vehicule {
	private String type;

	private int km, classe;
	private String immatriculation;
	private ArrayList<Calendar> debutLocation = new ArrayList<Calendar>();
	private ArrayList<Calendar> finLocation = new ArrayList<Calendar>();

	public Vehicule(String type, String imma, int km, int classe) {
		this.type = type;
		this.immatriculation = imma;
		this.km = km;
		this.classe = classe;
	}

	public void addIndisponiblePeriod(Calendar calDebut, Calendar calFin) {
		debutLocation.add(calDebut);
		finLocation.add(calFin);
	}

	public void removeIndisponiblePeriod(Calendar calDebut, Calendar calFin) {
		for (int i = 0; i < debutLocation.size(); i++) {

			if (debutLocation.get(i).compareTo(calDebut) == 0) {
				debutLocation.remove(i);
				finLocation.remove(i);
			}
		}

	}

	public boolean isVehiculeDisponible(Calendar start, Calendar end) {

		for (int i = 0; i < debutLocation.size(); i++) {

			Integer[] indices = new Integer[4];
			indices[0] = start.compareTo(debutLocation.get(i));
			indices[1] = start.compareTo(finLocation.get(i));
			indices[2] = end.compareTo(debutLocation.get(i));
			indices[3] = end.compareTo(finLocation.get(i));

			if (!(indices[0] == indices[1] && indices[0] == indices[2] && indices[0] == indices[3]))
				return false;

		}

		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public ArrayList<Calendar> getDebutLocation() {
		return debutLocation;
	}

	public void setDebutLocation(ArrayList<Calendar> debutLocation) {
		this.debutLocation = debutLocation;
	}

	public ArrayList<Calendar> getFinLocation() {
		return finLocation;
	}

	public void setFinLocation(ArrayList<Calendar> finLocation) {
		this.finLocation = finLocation;
	}

}
