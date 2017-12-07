package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import models.ParametresFacturation;

@SuppressWarnings("serial")
public class ModifParams extends JPanel {

	private JFrame main;
	String[] info = { "Classe economique", "Classe moyenne", "Classe confort", "Classe de luxe", "Classe utilitaire",
			"Assurance", "Cout km", "Minimum Kilo", "Forfait illimite KM", "Frais de retard %", "Taxe provinciale",
			"Taxe federFale", "Cout litre d'essence" };
	String[] textBouton = { "Retour", "Sauvegarder" };
	JLabel[] arrayLab = new JLabel[info.length];
	JTextField[] arrayText = new JTextField[info.length];
	JButton[] boutons = new JButton[2];

	public ModifParams(JFrame frame) {
		setBounds(100, 100, 500, 500);
		setLayout(null);
		setSize(800, 600);
		frame.add(this);
		main = frame;
		initComponents();
	}

	private void initComponents() {

		for (int i = 0; i < boutons.length; i++) {
			boutons[i] = new JButton(textBouton[i]);
			boutons[i].setBounds(300 * i + 40, 410, 130, 25);
			add(boutons[i]);
		}

		ArrayList<Integer> entiers = Magasin.getCurrentParametres().getPricesInteger();
		ArrayList<Double> doubles = Magasin.getCurrentParametres().getInfoDoubles();

		for (int i = 0; i < arrayLab.length; i++) {
			arrayLab[i] = new JLabel(info[i]);
			arrayText[i] = new JTextField();

			if (i < entiers.size()) {
				arrayText[i].setText("" + entiers.get(i));
			} else
				arrayText[i].setText("" + doubles.get(i - (entiers.size())));

			arrayLab[i].setBounds(50, 30 * i + 20, 130, 25);
			arrayText[i].setBounds(200, 30 * i + 20, 150, 25);
			add(arrayLab[i]);
			add(arrayText[i]);

		}
		boutons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});

		boutons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}

	protected void save() {
		ParametresFacturation params;
		JTextField[] a = arrayText;
		params = new ParametresFacturation(Integer.parseInt(a[0].getText()), Integer.parseInt(a[1].getText()),
				Integer.parseInt(a[2].getText()), Integer.parseInt(a[3].getText()), Integer.parseInt(a[4].getText()),
				Double.parseDouble(a[5].getText()), Double.parseDouble(a[6].getText()),
				(int) Double.parseDouble(a[7].getText()), Double.parseDouble(a[8].getText()),
				Double.parseDouble(a[9].getText()), Double.parseDouble(a[10].getText()),
				Double.parseDouble(a[11].getText()), Double.parseDouble(a[12].getText()));

		Magasin.savegarderNouveauParametres(params);
		close();
	}

	private void close() {
		main.remove(this);
		MenuOption menu = new MenuOption(main, true);
		SwingUtilities.updateComponentTreeUI(main);
	}

	private void setData() {
		ParametresFacturation params = Magasin.getCurrentParametres();

		return;
	}
}
