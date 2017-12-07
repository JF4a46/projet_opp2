package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;

import models.Client;

import models.Location;
import models.Vehicule;

public class LocationInfo extends JPanel {
	private boolean admin, nouvClient;
	private JLabel startDateL, endDateL, vehicTypeL, idVehicL, montantL, locationNumberL, assurancesL, kiloIllimL;
	private JTextField startDateF, endDateF, idVehicF, montantF, locationNumberF;
	private JComboBox<String> vehicTypeF;
	private JButton menu, save, change, pay, end;
	private JCheckBox assurances, kiloIllim;
	private JPanel current;
	private JFrame main;
	private Location location;
	private Client client;

	public void editable(boolean flag) {
		startDateF.setEditable(flag);
		endDateF.setEditable(flag);
	}

	public LocationInfo(JFrame frame, boolean admin, boolean nouvClient, int numID, Location location2, Client client) {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		this.admin = admin;
		this.nouvClient = nouvClient;
		current = this;
		frame.getContentPane().add(this);
		main = frame;
		initComponents(admin, nouvClient, numID);
		this.location = location2;
		this.client = client;
	}

	public void initComponents(boolean admin, boolean nouvClient, int numID) {
		JLabel format = new JLabel("AAAA/MM/JJ/HH");
		format.setBounds(50, 05, 200, 30);
		add(format);
		startDateL = new JLabel("Date de debut de la location");
		endDateL = new JLabel("Date de fin de la location");
		vehicTypeL = new JLabel("Classe du vehicule");
		idVehicL = new JLabel("Numero de plaque du vehicule");
		montantL = new JLabel("Montant a payer");
		locationNumberL = new JLabel("Numero de location");
		assurancesL = new JLabel("Assurances");
		kiloIllimL = new JLabel("Forfait kilometrage illimite");
		assurances = new JCheckBox();
		kiloIllim = new JCheckBox();
		startDateF = new JTextField();
		endDateF = new JTextField();
		String[] classes = new String[5];
		classes[0] = "Economique";
		classes[1] = "Moyenne";
		classes[2] = "Confort";
		classes[3] = "Luxe";
		classes[4] = "Utilitaire";
		vehicTypeF = new JComboBox<String>(classes);
		idVehicF = new JTextField();
		montantF = new JTextField();
		locationNumberF = new JTextField();
		locationNumberF.setText("" + numID);
		save = new JButton("Sauvegarder");
		change = new JButton("Modifier");
		pay = new JButton("Effectuer un paiement");
		end = new JButton("Terminer location");
		menu = new JButton("Retour au menu");

		startDateL.setBounds(50, 20, 200, 30);
		endDateL.setBounds(50, 60, 200, 30);
		vehicTypeL.setBounds(50, 100, 200, 30);
		idVehicL.setBounds(50, 140, 200, 30);
		montantL.setBounds(50, 180, 200, 30);
		locationNumberL.setBounds(50, 220, 200, 30);
		startDateF.setBounds(270, 20, 200, 30);
		endDateF.setBounds(270, 60, 200, 30);
		vehicTypeF.setBounds(270, 100, 200, 30);
		idVehicF.setBounds(270, 140, 200, 30);
		montantF.setBounds(270, 180, 200, 30);
		locationNumberF.setBounds(270, 220, 200, 30);
		assurancesL.setBounds(50, 260, 200, 30);
		assurances.setBounds(120, 260, 30, 30);
		kiloIllimL.setBounds(50, 300, 200, 30);
		kiloIllim.setBounds(200, 300, 30, 30);
		change.setBounds(270, 260, 200, 30);
		save.setBounds(270, 260, 200, 30);
		pay.setBounds(270, 300, 200, 30);
		end.setBounds(270, 340, 200, 30);
		menu.setBounds(50, 400, 180, 30);

		
		
		
		idVehicF.setEditable(false);
		montantF.setEditable(false);
		locationNumberF.setEditable(false);
		this.add(startDateL);
		this.add(endDateL);
		this.add(vehicTypeL);
		this.add(idVehicL);
		this.add(montantL);
		this.add(locationNumberL);
		this.add(startDateF);
		this.add(endDateF);
		this.add(vehicTypeF);
		this.add(idVehicF);
		this.add(montantF);
		this.add(locationNumberF);
		this.add(save);
		this.add(change);
		this.add(pay);
	//	this.add(end);
		this.add(menu);
		this.add(assurancesL);
		this.add(kiloIllimL);
		this.add(assurances);
		this.add(kiloIllim);
		// location = Magasin.searchLocation(numID).get(0);

		if (nouvClient) {
			change.setVisible(false);
		} else {
			//startDateF.setText(Magasin.dateToString(location.getStartDate()));
			//endDateF.setText(Magasin.dateToString(location.getEndDate()));
			//idVehicF.setText(location.getVehicule().getImmatriculation());
			//montantF.setText("" + location.getMontantDue());
			locationNumberF.setText("" + numID);
			save.setVisible(true);
			editable(true);
		}

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change.setVisible(true);
				save.setVisible(false);
				Vehicule vehicule;
				editable(false);
				client.setLocationNum(numID);
				if (nouvClient) {

					ArrayList<Vehicule> vehicules = Magasin.getVehiculesDisponible(
							Magasin.stringToDate(startDateF.getText()), Magasin.stringToDate(endDateF.getText()),
							vehicTypeF.getSelectedIndex());

					if (vehicules.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Aucun vehicule disponible", "Erreur",
								JOptionPane.ERROR_MESSAGE);
						change.setVisible(false);
						save.setVisible(true);
					} else {
						vehicule = vehicules.get(0);
						location.setStartDate(Magasin.stringToDate(startDateF.getText()));
						location.setEndDate(Magasin.stringToDate(endDateF.getText()));
						location.setVehicule(vehicule);
						location.setPhoneNumber(client.getTelephone());
						location.setImmatriculation(vehicule.getImmatriculation());
						location.classe = vehicule.getClasse();
						Magasin.rendreVehiculeNonDisponible(location.getStartDate(), location.getEndDate(),
								vehicule.getImmatriculation());
						Magasin.save();

					}
				}

				SwingUtilities.updateComponentTreeUI(main);

			}

		});

		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save.setVisible(true);
				change.setVisible(false);

				SwingUtilities.updateComponentTreeUI(main);

			}

		});

		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.remove(current);
				Paiement pay = new Paiement(main, admin, nouvClient, numID);
				SwingUtilities.updateComponentTreeUI(main);

			}

		});

		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.remove(current);
				Calendar start = Magasin.makeCalendar(Magasin.stringToDate(startDateF.getText()));
				Calendar end = Magasin.makeCalendar(Magasin.stringToDate(endDateF.getText()));
				//Magasin.removeLocation(location.getNumID(), start, end, idVehicF.getText());
				MenuOption menu = new MenuOption(main, admin);
				SwingUtilities.updateComponentTreeUI(main);
			}
		});

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.remove(current);
				MenuOption menu = new MenuOption(main, admin);
				SwingUtilities.updateComponentTreeUI(main);

			}
		});
		assurances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (assurances.isSelected()) {
					location.setAssurances(true);
				} else
					location.setAssurances(false);
			}
		});

		kiloIllim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (kiloIllim.isSelected()) {
					location.setKiloIllim(true);
				} else
					location.setKiloIllim(false);
			}
		});

	}

}