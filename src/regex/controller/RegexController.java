package regex.controller;

import regex.view.RegexFrame;

public class RegexController {
	
	private RegexFrame regexFrame;
	
	public RegexController(){
		this.regexFrame = new RegexFrame(this);
	}
	
	public void start(){
		
	}
	
	public RegexFrame getRegexFrame(){
		return regexFrame;
	}

}
