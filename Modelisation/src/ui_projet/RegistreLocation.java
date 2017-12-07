package ui_projet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultButtonModel;

import models.Client;
import models.Location;
import models.ParametresFacturation;
import models.Vehicule;

public class RegistreLocation {
	ParametresFacturation params;
	private ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
	private ArrayList<Client> clients = new ArrayList<Client>();
	private ArrayList<Location> locations = new ArrayList<Location>();

	public RegistreLocation() {
		params = new ParametresFacturation();

	}

	public void createVehicule(String type, String id, int km, int classe) {
		vehicules.add(new Vehicule(type, id, km, classe));
		writeVehicules();
	}

	public ParametresFacturation getParams() {
		return params;
	}

	public void setParams(ParametresFacturation params) {
		this.params = params;
		this.params.writeToFile();
	}

	public ArrayList<Vehicule> getVehiculeDisponible(Calendar start, Calendar end, int classe) {
		ArrayList<Vehicule> dispo = new ArrayList<Vehicule>();

		for (int i = 0; i < vehicules.size(); i++) {
			if (vehicules.get(i).isVehiculeDisponible(start, end) && vehicules.get(i).getClasse() == classe) {
				dispo.add(vehicules.get(i));
			}
		}
		return dispo;

	}

	public void createClient(String nom, String prenom, String telephone, String permisConduire, int numid) {

		clients.add(new Client(nom, prenom, telephone, permisConduire, numid));
		writeClientToFile();
	}

	public ArrayList<Client> searchClient(String param) {
		ArrayList<Client> resultats = new ArrayList<Client>();
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getTelephone().contains(param)) {
				resultats.add(clients.get(i));
			}
		}

		return resultats;
	}

	public Location createLocation(Client client, int numid) {
		Location loc = new Location(this.params, client, numid);
		locations.add(loc);
		return loc;
	}

	public ArrayList<Location> searchLocation(String phone) {
		ArrayList<Location> resultats = new ArrayList<>();
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).getClient().getTelephone().contains(phone)) {
				resultats.add(locations.get(i));
			}
		}
		return resultats;
	}

	/**
	 * Rendre vehicule non dispo pour une periode de temps
	 * @param dateDebut array temps
	 * @param dateFin
	 * @param immatriculation plaque
	 */
	public void rendreVehiculeNonDisponible(int[] dateDebut, int[] dateFin, String immatriculation) {
		for (int i = 0; i < vehicules.size(); i++) {
			if (vehicules.get(i).getImmatriculation().equals(immatriculation))
				vehicules.get(i).addIndisponiblePeriod(Magasin.makeCalendar(dateDebut), Magasin.makeCalendar(dateFin));
		}
		writeVehicules();
	}

	public void removeLocation(int numID, Calendar debut, Calendar fin, String immatriculation, String telephone) {

		for (int i = 0; i < vehicules.size(); i++) {
			if (vehicules.get(i).getImmatriculation().equals(immatriculation)) {
				vehicules.get(i).removeIndisponiblePeriod(debut, fin);
				writeVehicules();
				break;
			}
		}

		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).getPhoneNumber().contains(telephone)) {
				locations.remove(i);
			}
		}

	}

	public boolean removeVehicule(String id) {
		int index = -1;
		for (int i = 0; i < vehicules.size(); i++) {
			if (vehicules.get(i).getImmatriculation().equals(id.toUpperCase())) {
				index = i;
			}
		}
		if (index != -1) {
			vehicules.remove(index);
			writeVehicules();
			return true;
		} else
			return false;

	}

	public ParametresFacturation getParametres() {
		return params;
	}

	void loadVehicules() {
		ArrayList<String> bruteData = DbFileSystem.loadFromFile("vehicules.txt");

		vehicules = new ArrayList<Vehicule>();

		for (int i = 0; i < bruteData.size(); i++) {
			if (bruteData.get(i) != "" || bruteData.get(i) != "\n") {
				String[] data = bruteData.get(i).split(",");

				vehicules.add(new Vehicule(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));

				if (data.length > 4) {
					for (int j = 4; j < data.length; j++) {
						String date[] = data[j].split("/");
						String dateSplit[] = date[0].split(" ");
						String dateSplit1[] = date[1].split(" ");
						Calendar start = Calendar.getInstance();
						Calendar end = Calendar.getInstance();
						start.setTimeInMillis(0);
						end.setTimeInMillis(0);
						start.set(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
								Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[3]), 0);
						end.set(Integer.parseInt(dateSplit1[0]), Integer.parseInt(dateSplit1[1]),
								Integer.parseInt(dateSplit1[2]), Integer.parseInt(dateSplit1[3]), 0);

						vehicules.get(i).addIndisponiblePeriod(start, end);
					}
				}

			}
		}
	}

	void loadClientsFromFile() {
		ArrayList<String> bruteData = DbFileSystem.loadFromFile("clients.txt");

		clients = new ArrayList<Client>();
		for (int i = 0; i < bruteData.size(); i++) {
			if (bruteData.get(i) != "" || bruteData.get(i) != "\n") {

				String[] data = bruteData.get(i).split(",");
				clients.add(new Client(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
				System.out.println(data[4]);
			}
		}
	}

	private void writeClientToFile() {
		String toWrite = "";

		for (int i = 0; i < clients.size(); i++) {
			if (i != 0)
				toWrite += "\n";
			toWrite += clients.get(i).getNom() + "," + clients.get(i).getPrenom() + "," + clients.get(i).getTelephone()
					+ "," + clients.get(i).getPermisConduire() + "," + clients.get(i).getLocationNum();
		}
		DbFileSystem.writeToFile("clients.txt", toWrite);
	}

	void writeLocationsToFile() {
		String toWrite = "";

		if (locations.size() == 0) {
			PrintWriter writer;
			try {
				writer = new PrintWriter(new File("locations.txt"));
				writer.print("");
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		for (int i = 0; i < locations.size(); i++) {
			if (i != 0)
				toWrite += "\n";

			int assure = 0;
			if (locations.get(i).assurances) {
				assure = 1;
			}

			int kilo = 0;
			if (locations.get(i).kiloIllim)
				kilo = 1;

			toWrite += locations.get(i).startDateToString() + "/" + locations.get(i).endDateToString() + ","
					+ locations.get(i).getPhoneNumber() + "," + locations.get(i).getNumID() + ","
					+ locations.get(i).getImmatriculation() + "," + locations.get(i).getPhoneNumber() + "," + assure
					+ "," + kilo + "," + locations.get(i).classe;
		}

		DbFileSystem.writeToFile("locations.txt", toWrite);

	}

	void loadLocationsFromFile() {
		ArrayList<String> bruteData = DbFileSystem.loadFromFile("locations.txt");

		locations = new ArrayList<Location>();

		try {
			for (int i = 0; i < bruteData.size(); i++) {
				if (bruteData.get(i) != "" || bruteData.get(i) != "\n") {

					String[] data = bruteData.get(i).split(",");
					String[] time = data[0].split("/");
					String[] startDateStr = time[0].split(" ");
					String[] endDateStr = time[1].split(" ");
					int[] startDate = new int[4];
					int[] endDate = new int[4];
					for (int j = 0; j < startDateStr.length; j++) {
						startDate[j] = Integer.parseInt(startDateStr[j]);
						endDate[j] = Integer.parseInt(endDateStr[j]);
					}
					Client ref = null;
					ArrayList<Client> liste = searchClient(data[1]);
					if (liste.size() != 0)
						ref = liste.get(0);
					Location loc = new Location(ref, Magasin.getCurrentParametres(), startDate, endDate,
							Integer.parseInt(data[2]), data[3]);
					loc.setPhoneNumber(data[4]);
					loc.setAssurances(Integer.parseInt(data[5]));
					loc.setKiloIllim(Integer.parseInt(data[6]));
					loc.classe = Integer.parseInt(data[7]);
					locations.add(loc);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public void writeVehicules() {
		String toWrite = "";

		for (int i = 0; i < vehicules.size(); i++) {
			if (i != 0)
				toWrite += "\n";
			toWrite += vehicules.get(i).getType() + "," + vehicules.get(i).getImmatriculation().toUpperCase() + ","
					+ vehicules.get(i).getKm() + "," + vehicules.get(i).getClasse();
			if (vehicules.get(i).getDebutLocation().size() > 0 && vehicules.get(i).getFinLocation().size() > 0) {
				toWrite += ",";

				for (int j = 0; j < vehicules.get(i).getDebutLocation().size(); j++) {
					toWrite += (vehicules.get(i).getDebutLocation().get(j).get(1) + " "
							+ vehicules.get(i).getDebutLocation().get(j).get(2) + " "
							+ vehicules.get(i).getDebutLocation().get(j).get(5) + " "
							+ vehicules.get(i).getDebutLocation().get(j).get(11));
					toWrite += "/";
					toWrite += (vehicules.get(i).getFinLocation().get(j).get(1) + " "
							+ vehicules.get(i).getFinLocation().get(j).get(2) + " "
							+ vehicules.get(i).getFinLocation().get(j).get(5) + " "
							+ vehicules.get(i).getFinLocation().get(j).get(11));
					if (i < vehicules.get(i).getDebutLocation().size() - 1)
						toWrite += ",";
				}
			}
		}
		DbFileSystem.writeToFile("vehicules.txt", toWrite);
	}

}