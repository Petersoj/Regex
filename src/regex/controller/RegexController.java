package regex.controller;

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
		return firstName.matches("[a-zA-Z]*");
	}
	
	public boolean isValidLastName(String lastName){
		return lastName.matches("[a-zA-Z]*");
	}
	
	public RegexFrame getRegexFrame(){
		return regexFrame;
	}

}
