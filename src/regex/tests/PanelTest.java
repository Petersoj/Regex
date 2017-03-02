package regex.tests;

import static org.junit.Assert.*;

import java.awt.Component;
import regex.view.RegexPanel;
import regex.controller.RegexController;
import javax.swing.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PanelTest
{
	private RegexPanel testPanel;

	@Before
	public void setUp() throws Exception
	{
		testPanel = new RegexPanel(new RegexController());
	}

	@After
	public void tearDown() throws Exception
	{
		testPanel = null;
	}

	@Test
	public void testGUI()
	{
		int labelCount = 0;
		int fieldCount = 0;
		int listenerCount = 0;
		for(Component test : testPanel.getComponents())
		{
			if(test instanceof JTextField)
			{
				fieldCount++;
			}
			else if(test instanceof JLabel)
			{
				labelCount++;
			}
			if(test instanceof JButton)
			{
				if(((JButton) test).getActionListeners().length > 0 )
				{
					listenerCount++;
				}
			}
		}
		
		
		assertTrue("Not enough labels", labelCount > 3);
		assertTrue("Not enough fields", fieldCount > 3);
		assertTrue("Where is your listener??", listenerCount > 0);
	}

}
