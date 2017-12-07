package ui_projet;

import models.Client;
import models.Location;
import models.ParametresFacturation;
import models.Vehicule;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author
 *
 */

public final class Magasin {
	private static RegistreLocation registre = new RegistreLocation();

	private Magasin() {

	}

	/**
	 * 
	 * @param date
	 *            date de debut de la location desiree
	 * @param date2
	 *            date de fin
	 * 
	 *            Format de date[] = [annee,mois,jour,heure]
	 * 
	 * @return Retourne un array contenant les vehicules disponibles
	 */
	public static ArrayList<Vehicule> getVehiculesDisponible(int[] date, int[] date2, int classe) {
		return Magasin.getVehiculesDisponible(Magasin.makeCalendar(date), Magasin.makeCalendar(date2), classe);
	}

	/**
	 * @param debut
	 *            date de debut de la location desiree
	 * @param fin
	 *            date de fin
	 * 
	 *            Soit utilise getVehiculesDispo avec les arrays, soit declarer deux
	 *            objet calendar Pour declare calendar : Calendar cal =
	 *            Calendar.getInstance() cal.set(Annee, mois, jour, heure, 0)
	 * 
	 * 
	 * @return Retourne un array contenant les vehicules disponibles
	 */

	public static ArrayList<Vehicule> getVehiculesDisponible(Calendar debut, Calendar fin, int classe) {

		ArrayList<Vehicule> vicsDispo = registre.getVehiculeDisponible(debut, fin, classe);

		return vicsDispo;
	}

	public static void createVehicule(String type, String id, int km, int classe) {
		registre.createVehicule(type, id, km, classe);
	}

	public static void rendreVehiculeNonDisponible(int[] dateDebut, int[] dateFin, String immatriculation) {
		registre.rendreVehiculeNonDisponible(dateDebut, dateFin, immatriculation);
	}

	public static boolean removeVehicule(String string) {
		return registre.removeVehicule(string);
	}

	public static void createClient(String nom, String prenom, String telephone, String permisConduire, int numid) {

		registre.createClient(nom, prenom, telephone, permisConduire, numid);
	}

	public static void createLocation(int[] dateDebut, int[] dateFin, String immatriculation) {
		registre.rendreVehiculeNonDisponible(dateDebut, dateFin, immatriculation);
	}

	public static Location createLocation(Client client, int numid) {
		return registre.createLocation(client, numid);
		// registre.writeLocationsToFile();
	}

	public static Calendar makeCalendar(int[] date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(date[0], date[1], date[2], date[3], 0);

		return cal;
	}

	public static String dateToString(int[] date) {
		return ("" + date[0] + "/" + date[1] + "/" + date[2] + "/" + date[3]);
	}

	public static int[] stringToDate(String string) {
		int[] date = new int[4];
		ArrayList<String> dateString = new ArrayList<String>();
		String temp = "";

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != '/') {
				temp += string.charAt(i);
			}
			if (string.charAt(i) == '/' || i == (string.length() - 1)) {
				dateString.add(temp);
				temp = "";
			}
		}
		for (int i = 0; i < 4; i++) {
			date[i] = Integer.parseInt(dateString.get(i));
		}
		return date;
	}

	public static long[] getDaysFromCalendars(Calendar start, Calendar end) {
		int daysInMils = 86400000;
		long[] totalTimeReturn = new long[2];

		long startMils = start.getTimeInMillis();
		long endMils = end.getTimeInMillis();

		long time = endMils - startMils;

		totalTimeReturn[0] = time / 86400000;
		totalTimeReturn[1] = time % daysInMils;

		if (totalTimeReturn[1] != 0) {
			totalTimeReturn[1] = totalTimeReturn[1] / 3600000;
		}

		// System.out.println(java.util.Arrays.toString(totalTimeReturn));

		return totalTimeReturn;
	}

	/**
	 * 
	 * Pour retirer un vehicule, envoi immatriculation, date de debut et fin en
	 * calendrier
	 * 
	 * @param debut
	 *            date de debut de location
	 * @param fin
	 *            date de fin de location
	 * @param immatriculation
	 *            de vehicule pour retrouver le vehicule a re-rendre disponible
	 */

	public static void removeLocation(int numID, Calendar debut, Calendar fin, String immatriculation,
			String telephone) {
		registre.removeLocation(numID, debut, fin, immatriculation, telephone);
	}

	public static ArrayList<Client> searchClients(String param) {
		System.out.println("SearchMag");
		return registre.searchClient(param);
	}

	public static ArrayList<Location> searchLocation(String phone) {
		return registre.searchLocation(phone);
	}

	public static void savegarderNouveauParametres(ParametresFacturation params) {
		registre.setParams(params);
	}

	public static ParametresFacturation getCurrentParametres() {
		return registre.getParametres();
	}

	public static void save() {
		registre.writeLocationsToFile();
		registre.getParametres().writeToFile();
	}

	public static void wakeUp() {
		registre.loadVehicules();
		registre.loadClientsFromFile();
		registre.loadLocationsFromFile();
	}

}