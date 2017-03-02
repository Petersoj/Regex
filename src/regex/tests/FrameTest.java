package regex.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import regex.controller.RegexController;
import regex.view.*
;

public class FrameTest
{

	private RegexFrame testFrame;
	@Before
	public void setUp() throws Exception
	{
		testFrame = new RegexFrame(new RegexController());
	}

	@After
	public void tearDown() throws Exception
	{
		testFrame = null;
	}

	@Test
	public void testFrameRequirements()
	{
		assertNotNull("Data member not initialized", testFrame.getRegexController());
		assertTrue("Data member is of incorrect type", testFrame.getRegexController() instanceof RegexController);
		assertNotNull("Data member not initialized", testFrame.getContentPane());
	}

}
