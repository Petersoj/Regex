package regex.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import regex.controller.RegexController;

public class RegexPanel extends JPanel {
	
	private RegexController regexController;
	
	private SpringLayout springLayout;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel phoneNumberLabel;
	private JLabel emailLabel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JButton verifyButton;
	
	public RegexPanel(RegexController regexController){
		super();
		
		this.regexController = regexController;
		
		this.springLayout = new SpringLayout();
		
		this.firstNameLabel = new JLabel("First Name:");
		this.lastNameLabel = new JLabel("Last Name:");
		this.phoneNumberLabel = new JLabel("Phone Number:");
		this.emailLabel = new JLabel("Email:");
		
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
		this.setLayout(springLayout);
		this.add(firstNameLabel);
		this.add(lastNameLabel);
		this.add(phoneNumberLabel);
		this.add(emailLabel);
		this.add(firstNameField);
		this.add(lastNameField);
		this.add(phoneNumberField);
		this.add(emailField);
		this.add(verifyButton);
	}
	
	private void setupLayout(){
		springLayout.putConstraint(SpringLayout.NORTH, firstNameLabel, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, firstNameLabel, 30, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.WEST, firstNameField, 30, SpringLayout.EAST, firstNameLabel);
		springLayout.putConstraint(SpringLayout.EAST, firstNameField, -30, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, firstNameField, 0, SpringLayout.VERTICAL_CENTER, firstNameLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, lastNameLabel, 30, SpringLayout.SOUTH, firstNameLabel);
		springLayout.putConstraint(SpringLayout.WEST, lastNameLabel, 0, SpringLayout.WEST, firstNameLabel);
		
		springLayout.putConstraint(SpringLayout.WEST, lastNameField, 0, SpringLayout.WEST, firstNameField);
		springLayout.putConstraint(SpringLayout.EAST, lastNameField, 0, SpringLayout.EAST, firstNameField);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, lastNameField, 0, SpringLayout.VERTICAL_CENTER, lastNameLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 30, SpringLayout.SOUTH, lastNameLabel);
		springLayout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 0, SpringLayout.WEST, lastNameLabel);
		
		springLayout.putConstraint(SpringLayout.WEST, phoneNumberField, 0, SpringLayout.WEST, firstNameField);
		springLayout.putConstraint(SpringLayout.EAST, phoneNumberField, 0, SpringLayout.EAST, firstNameField);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, phoneNumberField, 0, SpringLayout.VERTICAL_CENTER, phoneNumberLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 30, SpringLayout.SOUTH, phoneNumberLabel);
		springLayout.putConstraint(SpringLayout.WEST, emailLabel, 0, SpringLayout.WEST, phoneNumberLabel);
		
		springLayout.putConstraint(SpringLayout.WEST, emailField, 0, SpringLayout.WEST, firstNameField);
		springLayout.putConstraint(SpringLayout.EAST, emailField, 0, SpringLayout.EAST, firstNameField);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, emailField, 0, SpringLayout.VERTICAL_CENTER, emailLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, verifyButton, 30, SpringLayout.SOUTH, emailLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, verifyButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
	}
	
	private void setupListeners(){
		this.verifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean matchedFirstName = regexController.validateFirstName(firstNameField.getText());
				boolean matchedLastName = regexController.validateLastName(lastNameField.getText());
				String detailedPhone = regexController.getValidPhoneDetails(phoneNumberField.getText());
				String detailedEmail = regexController.getValidEmailDetails(emailField.getText());
				
				String shownString = "First Name:    " + matchedFirstName + "\n\n";
				shownString += "Last name:    " + matchedLastName + "\n\n";
				shownString += detailedPhone;
				shownString += detailedEmail;
				
				JOptionPane.showMessageDialog(regexController.getRegexFrame(), shownString);
			}
		});
	}
}
