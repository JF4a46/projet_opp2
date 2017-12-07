package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import java.util.*;

public class MenuClient extends JPanel {

	private boolean admin, nouvClient;
	private JLabel text;
	private defaultTextField phoneNumber;
	private JButton button, menu;
	private JPanel current;
	private JFrame main;
	
	public MenuClient(JFrame frame, boolean admin, boolean nouvClient) {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		initComponents(admin, nouvClient);
		this.admin = admin;
		this.nouvClient = nouvClient;
		current = this;
		frame.add(this);
		main = frame;

	}

	public void initComponents(boolean admin, boolean nouvClient) {

		text = new JLabel("Entrer le numero de telephone du client");
		phoneNumber = new defaultTextField("Numero de telephone");
		if (nouvClient) {
			button = new JButton("Creer");
		} else {
			button = new JButton("Chercher");
		}
		menu = new JButton("Retour au menu");

		text.setBounds(50, 20, 280, 30);
		phoneNumber.setBounds(50, 60, 180, 30);
		button.setBounds(50, 100, 180, 30);
		menu.setBounds(50, 400, 180, 30);

		this.add(text);
		this.add(phoneNumber);
		this.add(button);
		this.add(menu);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{
				boolean nouvClient;
				main.remove(current);
				if (Magasin.searchClients(phoneNumber.getText()).isEmpty()) {
					nouvClient = true;
					System.out.println("" + nouvClient);
					ClientInfo info = new ClientInfo(main, admin, nouvClient, phoneNumber.getText());
				}else {
					nouvClient = false;
					ClientInfo info = new ClientInfo(main, admin, nouvClient, phoneNumber.getText());
				}
				SwingUtilities.updateComponentTreeUI(main);
			}
		});

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.remove(current);
				MenuOption menu = new MenuOption(main, admin);
				SwingUtilities.updateComponentTreeUI(main);

			}

		});

	}

}