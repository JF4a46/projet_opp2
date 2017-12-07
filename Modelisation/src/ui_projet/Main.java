package ui_projet;

import java.awt.*;
import java.util.Date;

import javax.swing.*;


/**
 * Salut, le programme fonctionne ainsi : Magasin est un transmetteur de message entre lUI et registreLocation. 
 * RegistreLocation gere la memoire sur des fichiers textes, contient les arrays Location, vehicule, clients. Elle charge la memoire a l<exectuion du programme automatiquement.
 * DbFileSystem ecrit dans des fichier textes aussi
 * 
 * Les classes de l'ui sont: 
 * LoginPanel qui est un menu de login
 * MenuOptions qui offre les operations de base : location, ajout vehicule, fin location, changer parametres, et mettre fin a une location.
 *
 *A partir de menu option, on a le choix entre ClientInfo(offre creation client + location), modifParams(changer les parametres) et ModVehic(ajouter/retirer des vehicules);
 *
 *Dans client info, une fois le client selectionner, on passe a LocationInfo pour creer la location.
 *
 *
 */


public class Main extends JFrame {
	
	public static void main(String[] args) {
		Magasin.wakeUp();

	

		JFrame location = new JFrame("Service de Location");
		location.setLayout(new BorderLayout());
		location.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		location.setBounds(100, 100, 500, 500);
		location.setVisible(true);

		LoginPanel nUI = new LoginPanel(location);
		SwingUtilities.updateComponentTreeUI(location);
	}

}
