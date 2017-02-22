package regex.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public boolean isValidFirstName(String firstName){
		return true;
	}
	
	public boolean isValidLastName(String lastName){
		return true;
	}
	
	public boolean isValidPhoneNumber(String phoneNumber){
		if (phoneNumber.matches("\\d{10}")){
			return true;
		}else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
			return true;
		}else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")){
			return true;
		}else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")){
			return true;
		}
		return false;
	}
	
	public boolean isValidEmail(String email){
		Matcher regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
		return regex.find();
	}
	
	public RegexFrame getRegexFrame(){
		return regexFrame;
	}

}
