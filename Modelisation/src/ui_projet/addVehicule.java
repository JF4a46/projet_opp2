package ui_projet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import java.util.*;

public class addVehicule extends JPanel {

	private boolean admin;
	private defaultTextField type, price;
	private JButton menu, add, remove, info;
	private String vehicule[] = { "Classe Economique", "Classe Moyenne", "Classe Confort", "Classe de luxe",
			"Classe de vehicules utilitaires" };
	private ArrayList<String> vehicList = new ArrayList<String>(Arrays.asList(vehicule));
	private int prix[] = { 39, 49, 59, 99, 89 };
	private ArrayList<Integer> prixList = new ArrayList<Integer>();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> vJList;
	private JScrollPane listScroller;
	private JPanel current;
	private JFrame main;

	public addVehicule(JFrame frame, boolean admin) {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		initComponents();
		this.admin = admin;
		current = this;
		frame.add(this);
		main = frame;

		String[] titres = { "Type", "immatriculation", "km", "Classe 0-1-2-3-4-5 " };
		JLabel[] etiq = new JLabel[5];
		JTextField[] dataField = new JTextField[5];
		JButton[] addVicToggles = new JButton[2];

		for (int i = 0; i < dataField.length - 1; i++) {
			dataField[i] = new JTextField();
			etiq[i] = new JLabel(titres[i]);
			etiq[i].setBounds(10, 25 + i * 35, 150, 25);
			dataField[i].setBounds(170, 25 + i * 35, 150, 25);
			add(dataField[i]);
			add(etiq[i]);
		}
		etiq[dataField.length - 1] = new JLabel(titres[1]);
		dataField[dataField.length - 1] = new JTextField();
		etiq[dataField.length - 1].setBounds(10, 250, 150, 25);
		dataField[dataField.length - 1].setBounds(170, 250, 150, 25);
		add(etiq[dataField.length - 1]);
		add(dataField[dataField.length - 1]);

		addVicToggles[0] = new JButton("Ajouter vehicule");
		addVicToggles[0].setBounds(10, 180, 160, 25);
		add(addVicToggles[0]);
		addVicToggles[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Magasin.createVehicule(dataField[0].getText(), dataField[1].getText(),
						Integer.parseInt(dataField[2].getText()), Integer.parseInt(dataField[3].getText()));
				clear(dataField);
			}
		});

		addVicToggles[1] = new JButton("Retirer vehicule");
		addVicToggles[1].setBounds(10, 300, 160, 25);
		add(addVicToggles[1]);
		addVicToggles[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Magasin.removeVehicule(dataField[dataField.length - 1].getText().toUpperCase())) {
					JOptionPane.showMessageDialog(null, "Véhicule effacé");
					clear(dataField);
				} else
					JOptionPane.showMessageDialog(null, "Véhicule non trouvé");

			}
		});
	}

	protected void clear(JTextField[] dataField) {
		for (int i = 0; i < dataField.length; i++)
			dataField[i].setText("");
	}

	public void initComponents() {

		for (int i = 0; i < prix.length; i++) {
			int val = prix[i];
			prixList.add(val);
		}

		for (int i = 0; i < vehicList.size(); i++) {
			String val = vehicList.get(i);
			model.addElement(val);
		}

		type = new defaultTextField("Type de vehicule");
		price = new defaultTextField("Prix");
		menu = new JButton("Retour au menu");
		add = new JButton("Ajouter");
		remove = new JButton("Enlever");
		info = new JButton("Information");
		vJList = new JList<String>(model);
		listScroller = new JScrollPane();

		type.setBounds(50, 20, 180, 30);
		price.setBounds(50, 60, 180, 30);
		menu.setBounds(20, 400, 130, 30);
		add.setBounds(50, 260, 100, 30);
		remove.setBounds(200, 260, 100, 30);
		info.setBounds(350, 260, 100, 30);
		listScroller.setBounds(260, 20, 190, 200);
		listScroller.setViewportView(vJList);

		/*
		 * this.add(type); this.add(price); this.add(menu); this.add(add);
		 * this.add(remove); this.add(info); this.add(listScroller);
		 */
		this.add(menu);

		add.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (type.getText().equals("Type de vehicule") == false) {
					if (price.getText().equals("Prix") == false) {
						try {
							int num = Integer.parseInt(price.getText());
							prixList.add(num);
						} catch (NumberFormatException e) {

						}
					}
					// Magasin.createVehicule("Honda", 01, 1000, 0);
					vehicList.add(type.getText());
					model.addElement(type.getText());
					vJList.setModel(model);
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		remove.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedIndex = vJList.getSelectedIndex();
				if (selectedIndex != -1) {
					model.removeElementAt(selectedIndex);
					vehicList.remove(selectedIndex);
					prixList.remove(selectedIndex);
					vJList.setModel(model);
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		menu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				main.remove(current);
				MenuOption menu = new MenuOption(main, admin);
				SwingUtilities.updateComponentTreeUI(main);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		info.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedIndex = vJList.getSelectedIndex();
				if (selectedIndex != -1) {
					JFrame infoF = new JFrame("Info");
					infoF.setLayout(new BorderLayout());
					infoF.setBounds(250, 250, 280, 140);
					JPanel infoP = new JPanel();
					infoP.setLayout(null);
					infoP.setBounds(250, 250, 280, 140);
					JLabel vehicInfo = new JLabel("Type: " + vehicList.get(selectedIndex));
					JLabel prixInfo = new JLabel("Prix: " + prixList.get(selectedIndex));
					vehicInfo.setBounds(20, 20, 220, 30);
					prixInfo.setBounds(20, 60, 220, 30);
					infoP.add(vehicInfo);
					infoP.add(prixInfo);
					infoF.add(infoP);
					SwingUtilities.updateComponentTreeUI(infoF);
					infoF.setVisible(true);

				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

	}
}
