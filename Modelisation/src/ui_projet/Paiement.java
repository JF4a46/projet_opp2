package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Paiement extends JPanel{
	
	private boolean admin, nouvClient;
	private JLabel montantDueL, montantPayeL;
	private JTextField montantDueF, montantPayeF;
	private JButton pay, menu;
	private JPanel current;
	private JFrame main;
	
	public Paiement(JFrame frame, boolean admin, boolean nouvClient, int locationNumber) {
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
		montantDueL = new JLabel("Montant a payer");
		montantPayeL = new JLabel("Paiement");
		montantDueF = new JTextField();
		montantPayeF = new JTextField();
		pay = new JButton("Completer la transaction");
		menu = new JButton("Retour au menu");
		
		montantDueL.setBounds(50, 20, 200, 30);
		montantPayeL.setBounds(50, 60, 200, 30);
		montantDueF.setBounds(270, 20, 200, 30);
		montantPayeF.setBounds(270, 60, 200, 30);
		pay.setBounds(50, 100, 200, 30);
		menu.setBounds(50, 400, 180, 30);
		
		this.add(montantDueL);
		this.add(montantPayeL);
		this.add(montantDueF);
		this.add(montantPayeF);
		this.add(pay);
		this.add(menu);
		
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}});
		
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.remove(current);
				MenuOption menu = new MenuOption(main, admin);
				SwingUtilities.updateComponentTreeUI(main);
				
			}});
	}
}
