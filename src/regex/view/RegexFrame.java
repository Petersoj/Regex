package regex.view;

import javax.swing.JFrame;

import regex.controller.RegexController;

public class RegexFrame extends JFrame {
	
	private RegexController regexController;
	
	private RegexPanel regexPanel;
	
	public RegexFrame(RegexController regexController){
		super();
		this.regexController = regexController;
		this.regexPanel = new RegexPanel(this);
		
		this.setupFrame();
	}
	
	private void setupFrame(){
		this.setContentPane(regexPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Regex");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public RegexController getRegexController(){
		return regexController;
	}

}
