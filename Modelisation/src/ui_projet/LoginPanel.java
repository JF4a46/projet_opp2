package ui_projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class LoginPanel extends JPanel {

	private defaultTextField username;
	private defaultTextField password;
	private JButton login;
	private JPanel current;
	private JFrame main;

	public LoginPanel(JFrame frame) {
		setLayout(null);
		setBounds(100, 100, 500, 500);
		initComponents();
		current = this;
		frame.add(this);
		main = frame;
	}

	private void initComponents() {
		username = new defaultTextField("ID");
		password = new defaultTextField("Mot de passe");
		login = new JButton("Login");

		username.setBounds(200, 40, 100, 30);
		password.setBounds(200, 80, 100, 30);
		login.setBounds(215, 120, 70, 30);

		this.add(username);
		this.add(password);
		this.add(login);
		username.setText("JM");
		password.setText("test");

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (username.getText().equals("JM") && password.getText().equals("test")) {
					// current.setVisible(false);
					main.remove(current);
					MenuOption menu = new MenuOption(main, true);
					SwingUtilities.updateComponentTreeUI(main);

				} else if (username.getText().equals("EMP") && password.getText().equals("test")) {
					// current.setVisible(false);
					main.remove(current);
					MenuOption menu = new MenuOption(main, false);
					SwingUtilities.updateComponentTreeUI(main);
				} else
					JOptionPane.showMessageDialog(null, "Identificateurs incorrects", "Erreur",
							JOptionPane.ERROR_MESSAGE);

			}

		});

	}

}
