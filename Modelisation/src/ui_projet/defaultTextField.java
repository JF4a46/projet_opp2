package ui_projet;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class defaultTextField extends JTextField implements FocusListener {
	private final String defaultText;
	private boolean seeDefault;
	
	public defaultTextField(final String defaultText) {
		super(defaultText);
		this.defaultText = defaultText;
		super.addFocusListener(this);
	}
	
	public String getText() {
		if (seeDefault == true) 
			return "";
		else
			return super.getText();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (this.getText().isEmpty()) {
			super.setText("");
			seeDefault = false;
		}
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (this.getText().isEmpty()) {
			super.setText(defaultText);
			seeDefault = true;
		}
		
	}
	
	

	
}
