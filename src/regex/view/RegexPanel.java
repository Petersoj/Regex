package regex.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import regex.controller.RegexController;

public class RegexPanel extends JPanel {
	
	private RegexFrame regexFrame;
	
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
	
	public RegexPanel(RegexFrame regexFrame){
		super();
		
		this.regexFrame = regexFrame;
		
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
				RegexController regexController = regexFrame.getRegexController();
				
				boolean matchedFirstName = regexController.isValidFirstName(firstNameField.getText());
				boolean matchedLastName = regexController.isValidLastName(lastNameField.getText());
				
				String phoneNumber = phoneNumberField.getText().replaceAll("\\s+", "");
				if(!phoneNumber.startsWith("1")){
					phoneNumber = "1" + phoneNumber;
				}
				boolean validPhone = false;
				JsonObject phoneJSONObject = null;
				if(phoneNumber.length() > 8){
					String phoneURL = "http://apilayer.net/api/validate?access_key=96d4141049100f8413bc5d3438c6e1f4&number=" + phoneNumber + "&country_code=&format=1";
					String phoneJSON = getJSONFromURL(phoneURL);
					phoneJSONObject = Jsoner.deserialize(phoneJSON, new JsonObject());
					validPhone = phoneJSONObject.getBoolean("valid") != null && phoneJSONObject.getBoolean("valid");
				}
				
				String email = emailField.getText().replaceAll("\\s+", "");
				String emailURL = "http://apilayer.net/api/check?access_key=db2e54d32073ef1ae2585fd7a7799d1c&email=" + email +"&smtp=1&format=1";
				String emailJSON = getJSONFromURL(emailURL);
				JsonObject emailJSONObject = Jsoner.deserialize(emailJSON, new JsonObject());
				boolean validEmail = emailJSONObject.getBoolean("format_valid") != null && emailJSONObject.getBoolean("format_valid");
				
				String shownString = "First Name:    " + matchedFirstName + "\n\n";
				shownString += "Last Name:    " + matchedLastName + "\n\n";
				if(validPhone){
					shownString += "Phone Number:    " + true + "\n";
					shownString += "Location of Phone:    " + phoneJSONObject.getString("location") + "\n\n";
				}else{
					shownString += "Phone Number:    " + false + "\n\n";
				}
				if(validEmail){
					shownString += "Email:    valid \n";
					shownString += "Mail Exchange record found:    " + emailJSONObject.getBoolean("mx_found") + "\n";
					shownString += "Simple Mail Transfer Protocol check:    " + emailJSONObject.getBoolean("smtp_check") + "\n";
					shownString += "Score (Higher is more legitimate. Max of 80):    " + emailJSONObject.getDouble("score") * 100;
				}else{
					shownString += "Email: invalid \n";
					shownString += "Did you mean " + emailJSONObject.getString("did_you_mean");
				}
				
				JOptionPane.showMessageDialog(regexFrame, shownString);
			}
		});
	}
	
	private String getJSONFromURL(String url){
		String json = "";
		try (InputStream input = new URL(url).openStream()){
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
			int cp;
			while((cp = reader.read()) != -1){
				json += (char) cp;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
