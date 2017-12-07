package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import models.Client;
import models.Location;

public class MenuOption extends JPanel {

	private boolean admin;
	private JButton crLocation, client, end, vehicule, modParametre;
	private JPanel current;
	private JFrame main;
	private JButton retour = new JButton("Retour de véhicule");

	public MenuOption(JFrame frame, boolean admin) {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		initComponents(true);
		this.admin = admin;
		current = this;
		frame.add(this);
		main = frame;
	}

	private void initComponents(boolean admin) {
		crLocation = new JButton("Creer location");
		client = new JButton("Liste des clients");
		end = new JButton("Deconnexion");
		vehicule = new JButton("Liste des vehicules");
		modParametre = new JButton("Modifier les parametres");
		retour.setBounds(50, 100, 180, 30);
		crLocation.setBounds(50, 20, 180, 30);
		client.setBounds(270, 20, 180, 30);
		vehicule.setBounds(50, 60, 180, 30);
		modParametre.setBounds(270, 60, 180, 30);
		end.setBounds(190, 160, 120, 30);
		add(retour);

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String phone = JOptionPane.showInputDialog("Entrez le numero de telephone du client");

				Client client = Magasin.searchClients(phone).get(0);
				Location location = Magasin.searchLocation(phone).get(0);

				JOptionPane.showMessageDialog(null, "Vous devez faire payer " + location.calculerPrix());

				JOptionPane.showMessageDialog(null, "Montant a ete paye");

				Magasin.removeLocation(0, Magasin.makeCalendar(location.getStartDate()),
						Magasin.makeCalendar(location.getEndDate()), location.getImmatriculation(), phone);

				Magasin.save();

			}

		});

		crLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create location doit avoir deux args int[] annee mois jour heure
				// Magasin.createLocation();
				main.remove(current);
				MenuClient create = new MenuClient(main, admin, true);
				SwingUtilities.updateComponentTreeUI(main);
			}

		});

		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.remove(current);
				MenuClient create = new MenuClient(main, admin, false);
				SwingUtilities.updateComponentTreeUI(main);
			}

		});

		vehicule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.remove(current);
				addVehicule addV = new addVehicule(main, admin);
				SwingUtilities.updateComponentTreeUI(main);
			}

		});

		modParametre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				main.remove(current);
				ModifParams params = new ModifParams(main);
				SwingUtilities.updateComponentTreeUI(main);

			}

		});

		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.remove(current);
				LoginPanel nUI = new LoginPanel(main);
				SwingUtilities.updateComponentTreeUI(main);
			}

		});

		this.add(crLocation);
		this.add(client);
		this.add(end);

		if (admin) {
			this.add(vehicule);
			this.add(modParametre);
		}

	}
}
