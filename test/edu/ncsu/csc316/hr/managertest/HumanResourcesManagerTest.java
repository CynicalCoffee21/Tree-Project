package edu.ncsu.csc316.hr.managertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.manager.HumanResourcesManager;
/**
 * HRM test class
 * @author Ryan Buchanan
 *
 */
public class HumanResourcesManagerTest {
	private HumanResourcesManager hrm;
	/**
	 * Set up
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {  
		//Ignore
	}
	/**
	 * These will be simple tests, most of the testing for this class occurs 
	 * through the UI and BBT's.
	 */
	@Test
	public void testHumanResourcesManager() {
		hrm = new HumanResourcesManager("input/sample", "input/sample-resume");
		if(hrm == null)
			fail("The manager failed to create .");
		//hrm = new HumanResourcesManager("input/sampleBad", "input/sample-resume");
		//if(hrm != null)
			//fail("hrm shouldn't have created with a bad file.");
	}	
	/** 
	 * Tests the remove employee functionality
	 */
	@Test
	public void testRemoveEmployee() {
		hrm = new HumanResourcesManager("input/sample", "input/sample-resume");
		assertTrue(hrm.removeEmployee("Sarah", "Jones").equals("Jane Doe"));
		String orgCorrect = "OrganizationalProfile[\n"
				+ "   Jane Doe\n" 
				+ "   John Smith\n"
				+ "   Thomas Webb\n"
				+ "   Suzanne Meadows\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "]";
		//System.out.println(hrm.generateOrganizationalProfile());
		assertTrue(hrm.generateOrganizationalProfile().equals(orgCorrect));
	}
	/**
	 * Using the second input file to test the removal method
	 */
	@Test
	public void testRemoveEmployeeTwo(){
		hrm = new HumanResourcesManager("input/sample2", "input/sample-resume3");		
		String sample2Correct = "OrganizationalProfile[\n" 
				+ "   Sarah Jones\n"
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Suzanne Meadows\n"
				+ "   Ian Hoffman\n"
				+ "   Jerry Seinfeld\n"
				+ "   Thomas Webb\n"
				+ "   Andrea Dorian\n"
				+ "   Sasha Hornady\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "   Ryan Buchanan\n"
				+ "   Remy Leonardo\n"
				+ "   Elaine Trackand\n"
				+ "]";
		String sample2CorrectRem = "OrganizationalProfile[\n" 
				+ "   Ian Hoffman\n"
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Suzanne Meadows\n"
				+ "   Andrea Dorian\n"
				+ "   Jerry Seinfeld\n"
				+ "   Thomas Webb\n"
				+ "   Elaine Trackand\n"
				+ "   Sasha Hornady\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "   Ryan Buchanan\n"
				+ "   Remy Leonardo\n"				
				+ "]";
		
		//System.out.println(hrm.generateOrganizationalProfile());	
		assertTrue(hrm.generateOrganizationalProfile().equals(sample2Correct));
		assertTrue(hrm.removeEmployee("Sarah", "Jones").equals("Ian Hoffman"));
		//System.out.println("actual: \n" +hrm.generateOrganizationalProfile());
		//System.out.println("expected: \n" + sample2CorrectRem);
		assertTrue(hrm.generateOrganizationalProfile().equals(sample2CorrectRem));
	}
	/**
	 * Using the fourth input file to test the removal method
	 */
	@Test
	public void testRemoveEmployeeThree(){
		hrm = new HumanResourcesManager("input/sample2", "input/sample-resume4");		
		String sample2Correct = "OrganizationalProfile[\n" 
				+ "   Sarah Jones\n"
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Suzanne Meadows\n"
				+ "   Ian Hoffman\n"
				+ "   Jerry Seinfeld\n"
				+ "   Thomas Webb\n"
				+ "   Andrea Dorian\n"
				+ "   Sasha Hornady\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "   Ryan Buchanan\n"
				+ "   Remy Leonardo\n"
				+ "   Elaine Trackand\n"
				+ "]";
		String sample2CorrectRem = "OrganizationalProfile[\n" 
				+ "   Ian Hoffman\n"
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Suzanne Meadows\n"
				+ "   Andrea Dorian\n"
				+ "   Jerry Seinfeld\n"
				+ "   Thomas Webb\n"
				+ "   Elaine Trackand\n"
				+ "   Sasha Hornady\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "   Ryan Buchanan\n"
				+ "   Remy Leonardo\n"
				+ "]";
		/*
		String sample2CorrectRem2 = "OrganizationalProfile[\n" 
				+ "   Ian Hoffman\n"
				+ "   John Smith\n"
				+ "   Thomas Webb\n"
				+ "   Suzanne Meadows\n"
				+ "   Andrea Dorian\n"
				+ "   Jerry Seinfeld\n"
				+ "   Jessica Daniels\n"
				+ "   Elaine Trackand\n"
				+ "   Sasha Hornady\n"
				+ "   Kyle DeMarcino\n"
				+ "   Ryan Buchanan\n"
				+ "   Remy Leonardo\n"
				+ "]";
		*/
		//System.out.println(hrm.generateOrganizationalProfile());	
		assertTrue(hrm.generateOrganizationalProfile().equals(sample2Correct));
		assertTrue(hrm.removeEmployee("Sarah", "Jones").equals("Ian Hoffman"));
	//	System.out.println("actual: \n" + hrm.generateOrganizationalProfile());
	//	System.out.println("expected: \n" + sample2CorrectRem);
		assertTrue(hrm.generateOrganizationalProfile().equals(sample2CorrectRem));
		System.out.println(hrm.removeEmployee("Jane", "Doe"));
		//assertTrue(hrm.removeEmployee("Jane", "Doe").equals("Thomas Webb"));
		System.out.println("actual: \n" + hrm.generateOrganizationalProfile());
	}
	/**
	 * Test to determine whether or not the organizational 
	 * profile prints according to the design document specifications.
	 */
	@Test
	public void testGenerateOrganizationalProfile() {
		hrm = new HumanResourcesManager("input/sample", "input/sample-resume");
		
		String orgCorrect = "OrganizationalProfile[\n"
				+ "   Sarah Jones\n" 
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Suzanne Meadows\n"
				+ "   Thomas Webb\n"
				+ "   Jessica Daniels\n"
				+ "   Kyle DeMarcino\n"
				+ "]";
		String orgInCorrect0 = "OrganizationalProfile[\n"
				+ "   Sarah Jones\n" 
				+ "   John Smith\n"
				+ "   Jane Doe\n"
				+ "   Kyle DeMarcino\n"
				+ "   Thomas Webb\n"
				+ "   Jessica Daniels\n"
				+ "   Suzanne Meadows\n"
				+ "]";
		String orgInCorrect1 = "OrganizationalProfile[\n"
				+ " Sarah Jones\n" 
				+ "  John Smith\n"
				+ "  Jane Doe\n"
				+ "  Suzanne Meadows\n"
				+ "   Thomas Webb\n"
				+ "    Jessica Daniels\n"
				+ "    Kyle DeMarcino\n"
				+ "]";
		//System.out.println(orgCorrect);
		
		assertFalse(hrm.generateOrganizationalProfile().equals(orgInCorrect0));
		assertFalse(hrm.generateOrganizationalProfile().equals(orgInCorrect1));
		assertTrue(hrm.generateOrganizationalProfile().equals(orgCorrect));
	}

}
