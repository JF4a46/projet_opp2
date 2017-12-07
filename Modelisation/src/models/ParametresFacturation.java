package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ParametresFacturation {
	private int classeEconomique, classeMoyenne, classeConfort, classeLuxe, classeUtilitaire, numeroSerie;

	private double assurance, coutkm, minimumKilo, forfaitIllimiteKM, retardPoucent, taxeProvinciale, taxeFederale,
			litreEssence;

	public ParametresFacturation() {
		loadFromFile();
	}

	public void writeToFile() {
		PrintWriter writer;
		try {

			writer = new PrintWriter("parametres.txt", "UTF-8");
			writer.println(classeEconomique + "," + classeMoyenne + "," + classeConfort + "," + classeLuxe + ","
					+ classeUtilitaire + "," + assurance + "," + coutkm + "," + minimumKilo + "," + forfaitIllimiteKM
					+ "," + retardPoucent + "," + taxeProvinciale + "," + taxeFederale + "," + litreEssence + ","
					+ numeroSerie);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadFromFile() {
		String array = null;
		try (BufferedReader br = new BufferedReader(new FileReader("parametres.txt"))) {
			array = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] data = array.split(",");
		classeEconomique = Integer.parseInt(data[0]);
		classeMoyenne = Integer.parseInt(data[1]);
		classeConfort = Integer.parseInt(data[2]);
		classeLuxe = Integer.parseInt(data[3]);
		classeUtilitaire = Integer.parseInt(data[4]);
		assurance = Double.parseDouble(data[5]);
		coutkm = Double.parseDouble(data[6]);
		minimumKilo = Double.parseDouble(data[7]);
		forfaitIllimiteKM = Double.parseDouble(data[8]);
		retardPoucent = Double.parseDouble(data[9]);
		taxeProvinciale = Double.parseDouble(data[10]);
		taxeFederale = Double.parseDouble(data[11]);
		litreEssence = Double.parseDouble(data[12]);
		numeroSerie = Integer.parseInt(data[13]);
	}

	public ParametresFacturation(int classeEconomique, int classeMoyenne, int classeConfort, int classeLuxe,
			int classeUtilitaire, double assurance, double coutkm, int minimumKilo, double forfaitIllimiteKM,
			double retardPoucent, double taxeProvinciale, double taxeFederale, double litreEssence) {
		this.classeEconomique = classeEconomique;
		this.classeMoyenne = classeMoyenne;
		this.classeLuxe = classeLuxe;
		this.setClasseConfort(classeConfort);
		this.classeUtilitaire = classeUtilitaire;
		this.assurance = assurance;
		this.coutkm = coutkm;
		this.minimumKilo = minimumKilo;
		this.forfaitIllimiteKM = forfaitIllimiteKM;
		this.retardPoucent = retardPoucent;
		this.taxeProvinciale = taxeProvinciale;
		this.taxeFederale = taxeFederale;
		this.litreEssence = litreEssence;
	}

	public int getNumeroSerie() {
		return ++numeroSerie;
	}

	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public ArrayList<Integer> getPricesInteger() {
		ArrayList<Integer> prices = new ArrayList<Integer>();
		prices.add(classeEconomique);
		prices.add(classeMoyenne);
		prices.add(classeConfort);
		prices.add(classeLuxe);
		prices.add(classeUtilitaire);

		return prices;
	}

	public ArrayList<Double> getInfoDoubles() {
		ArrayList<Double> params = new ArrayList<Double>();

		params.add(assurance);
		params.add(coutkm);
		params.add(minimumKilo);
		params.add(forfaitIllimiteKM);
		params.add(retardPoucent);
		params.add(taxeProvinciale);
		params.add(taxeFederale);
		params.add(litreEssence);

		return params;
	}

	public int getClasseEconomique() {
		return classeEconomique;
	}

	public void setClasseEconomique(int classeEconomique) {
		this.classeEconomique = classeEconomique;
	}

	public int getClasseMoyenne() {
		return classeMoyenne;
	}

	public void setClasseMoyenne(int classeMoyenne) {
		this.classeMoyenne = classeMoyenne;
	}

	public int getClasseLuxe() {
		return classeLuxe;
	}

	public void setClasseLuxe(int classeLuxe) {
		this.classeLuxe = classeLuxe;
	}

	public int getClasseUtilitaire() {
		return classeUtilitaire;
	}

	public void setClasseUtilitaire(int classeUtilitaire) {
		this.classeUtilitaire = classeUtilitaire;
	}

	public int getClasseConfort() {
		return classeConfort;
	}

	public void setClasseConfort(int classeConfort) {
		this.classeConfort = classeConfort;
	}

	public double getAssurance() {
		return assurance;
	}

	public void setAssurance(double assurance) {
		this.assurance = assurance;
	}

	public double getCoutkm() {
		return coutkm;
	}

	public void setCoutkm(double coutkm) {
		this.coutkm = coutkm;
	}

	public double getMinimumKilo() {
		return minimumKilo;
	}

	public void setMinimumKilo(double minimumKilo) {
		this.minimumKilo = minimumKilo;
	}

	public double getForfaitIllimiteKM() {
		return forfaitIllimiteKM;
	}

	public void setForfaitIllimiteKM(double forfaitIllimiteKM) {
		this.forfaitIllimiteKM = forfaitIllimiteKM;
	}

	public double getRetardPoucent() {
		return retardPoucent;
	}

	public void setRetardPoucent(double retardPoucent) {
		this.retardPoucent = retardPoucent;
	}

	public double getTaxeProvinciale() {
		return taxeProvinciale;
	}

	public void setTaxeProvinciale(double taxeProvinciale) {
		this.taxeProvinciale = taxeProvinciale;
	}

	public double getTaxeFederale() {
		return taxeFederale;
	}

	public void setTaxeFederale(double taxeFederale) {
		this.taxeFederale = taxeFederale;
	}

	public double getLitreEssence() {
		return litreEssence;
	}

	public void setLitreEssence(double litreEssence) {
		this.litreEssence = litreEssence;
	}

}