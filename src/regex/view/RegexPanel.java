package regex.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegexPanel extends JPanel {
	
	private RegexFrame regexFrame;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel phoneNumberLabel;
	private JLabel emailLabel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JButton verifyButton;
	
	public RegexPanel(RegexFrame regexFrame){
		super();
		this.regexFrame = regexFrame;
		
		this.firstNameLabel = new JLabel("First Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.phoneNumberLabel = new JLabel("Phone Number");
		this.emailLabel = new JLabel("Email");
		
		this.firstNameField = new JTextField();
		this.lastNameField = new JTextField();
		this.phoneNumberField = new JTextField();
		this.emailField = new JTextField();
		
		this.verifyButton = new JButton("Verify Information");
		
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	private void setupPanel(){
		
	}
	
	private void setupLayout(){
		
	}
	
	private void setupListeners(){
		
	}

}
