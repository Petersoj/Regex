package regex.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import regex.controller.RegexController;

public class ControllerTest
{

	private RegexController testController;
	@Before
	public void setUp() throws Exception
	{
		testController = new RegexController();
	}

	@After
	public void tearDown() throws Exception
	{
		testController = null;
	}

	@Test
	public void testEmail()
	{
		assertTrue("Valid email fails", testController.validateEmail("cody.henrichsen@canyonsdistrict.org"));
		assertFalse("Invalid email passes", testController.validateEmail("cody.henrichsen@ canyonsdistrict.org"));
		assertFalse("Invalid email passes", testController.validateEmail("cody.henrichsen.canyonsdistrict.org"));
		
	}
	
	@Test
	public void testPhone()
	{
		assertTrue("Valid number fails", testController.validatePhone("8018787782")); //  slight modification to make a real phone number :P
		assertTrue("Valid number fails", testController.validatePhone("8019829055"));
		assertFalse("Invalid number passes", testController.validatePhone("801"));
		assertFalse("Invalid number passes", testController.validatePhone("80198290555"));
		assertFalse("Invalid number passes", testController.validatePhone("8012sfsdf"));	
	}
	
	@Test
	public void testFirstName()
	{
		assertTrue("Valid name fails", testController.validateFirstName("cody"));
		assertFalse("Invalid name passes", testController.validateFirstName("#$%^&*"));
		assertFalse("Invalid name passes", testController.validateFirstName("A"));
		assertFalse("Invalid name passes", testController.validateFirstName("hdhdhdhdhdhdhdhdhdhdhdhdhdhdhdh"));
	}
	
	@Test
	public void testLastName()
	{
		assertTrue("Valid name fails", testController.validateLastName("cody"));
		assertTrue("Valid name fails", testController.validateLastName("cody-henrichsen"));
		assertTrue("Valid name fails", testController.validateLastName("codyo'notreal"));
		assertFalse("Invalid name passes", testController.validateLastName("#$%^&*"));
		assertFalse("Invalid name passes", testController.validateLastName("A"));
		assertFalse("Invalid name passes", testController.validateLastName("eeeeeeeeeehdhdhdhdhdhdhdhdhdhdhdhdhdhdhdh"));
		
	}

}
