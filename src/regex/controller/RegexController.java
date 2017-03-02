package regex.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import regex.view.RegexFrame;

public class RegexController {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private RegexFrame regexFrame;
	
	public RegexController(){
		this.regexFrame = new RegexFrame(this);
	}
	
	public void start(){
		
	}
	
	public boolean validateFirstName(String firstName){
		if(firstName.length() < 2 || firstName.length() > 30){
			return false;
		}
		return firstName.matches("^[\\p{L} .'-]+$");
	}
	
	public boolean validateLastName(String lastName){
		if(lastName.length() < 2 || lastName.length() > 40){
			return false;
		}
		return lastName.matches("^[\\p{L} .'-]+$");
	}
	
	public boolean validatePhone(String phoneNumber){
		if(phoneNumber.length() < 10){
			return false;
		}else{
			phoneNumber = phoneNumber.replaceAll("\\s+", "");
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
			return validPhone;
		}
	}
	
	public String getValidPhoneDetails(String phoneNumber){
		String shownString = "";
		
		phoneNumber = phoneNumber.replaceAll("\\s+", "");
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
		
		if(validPhone){
			shownString += "Phone Number:    " + true + "\n";
			shownString += "Location of Phone:    " + phoneJSONObject.getString("location") + "\n\n";
		}else{
			shownString += "Phone Number:    " + false + "\n\n";
		}
		return shownString;
	}
	
	public boolean validateEmail(String email){
		if(email.contains("\\s+") || email.length() < 4){
			return false;
		}else{
			String emailURL = "http://apilayer.net/api/check?access_key=db2e54d32073ef1ae2585fd7a7799d1c&email=" + email +"&smtp=1&format=1";
			String emailJSON = getJSONFromURL(emailURL);
			JsonObject emailJSONObject = Jsoner.deserialize(emailJSON, new JsonObject());
			return emailJSONObject.getBoolean("format_valid") != null && emailJSONObject.getBoolean("format_valid");
		}
	}
	
	public String getValidEmailDetails(String email){
		String shownString = "";
		
		email = email.replaceAll("\\s+", "");
		String emailURL = "http://apilayer.net/api/check?access_key=db2e54d32073ef1ae2585fd7a7799d1c&email=" + email +"&smtp=1&format=1";
		String emailJSON = getJSONFromURL(emailURL);
		JsonObject emailJSONObject = Jsoner.deserialize(emailJSON, new JsonObject());
		boolean validEmail = emailJSONObject.getBoolean("format_valid") != null && emailJSONObject.getBoolean("format_valid");
		
		if(validEmail){
			shownString += "Email:    valid \n";
			shownString += "Mail Exchange record found:    " + emailJSONObject.getBoolean("mx_found") + "\n";
			shownString += "Simple Mail Transfer Protocol check:    " + emailJSONObject.getBoolean("smtp_check") + "\n";
			shownString += "Score (Higher is more legitimate. Max of 80):    " + emailJSONObject.getDouble("score") * 100;
		}else{
			shownString += "Email: invalid \n";
			shownString += "Did you mean " + emailJSONObject.getString("did_you_mean");
		}
		
		return shownString;
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
	
	public RegexFrame getRegexFrame(){
		return regexFrame;
	}

}
