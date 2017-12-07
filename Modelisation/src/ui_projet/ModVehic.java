package ui_projet;

import javax.swing.*;

public class ModVehic extends JPanel {
	private JList<String> list;

	
	
	/**
	 * Create the panel.
	 */
	public ModVehic() {
		setLayout(null);
		defaultTextField type = new defaultTextField("Type de vehicule");
		defaultTextField price = new defaultTextField("Prix");
		
		type.setBounds(12, 13, 137, 30);
		price.setBounds(12, 62, 137, 30);
		add(type);
		add(price);
		
		//DefaultListModel<String> model = new DefaultListModel<String>();
		AbstractListModel<String> model = new AbstractListModel<String>() {
			String[] strings = {"Classe economique", "Classe moyenne", "Classe confort", "Classe luxe", "Classe de vehicules utilitaires", "Classe sport", "Classe aubaine"};

		    public int getSize() {
		        return strings.length;
		    }

		    public String getElementAt(int i) {
		        return strings[i];
		    }
		};
		
//		model.addElement("Classe economique");
//		model.addElement("Classe moyenne");
//		model.addElement("Classe confort");
//		model.addElement("Classe luxe");
//		model.addElement("Classe de vehicules utilitaires");
		list = new JList<String>();
		list.setLayoutOrientation(JList.VERTICAL);
		list.setModel(model);
		//list.setBounds(148, 166, 100, 72);
		
		//add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 13, 200, 112);
		scrollPane.setViewportView(list);
		add(scrollPane);
		
		

	}
}
