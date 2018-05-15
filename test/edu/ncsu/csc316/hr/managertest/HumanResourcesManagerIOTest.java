package edu.ncsu.csc316.hr.managertest;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.ncsu.csc316.hr.manager.HumanResourcesManager;
import edu.ncsu.csc316.hr.manager.HumanResourcesManagerIO;
import edu.ncsu.csc316.hr.tree.Employee;
import edu.ncsu.csc316.hr.tree.EmployeeTree;
/**
 * This is the test of the IO class
 * @author Ryan Buchanan
 *
 */
public class HumanResourcesManagerIOTest {  
	private static HumanResourcesManagerIO io;
	private static EmployeeTree list;
	/**
	 * Before all the tests.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception{
		io = new HumanResourcesManagerIO();
		list = new EmployeeTree();
	}
	/** 
	 * Tests the load employee file.
	 */
	@Test
	public void testLoadEmployees() {
		try {
			if(!io.loadEmployees("input/sample", list))
				fail("Failed to read employee file: returned false");
			if(!io.loadEmployees("input/sample2", list))
				fail("Failed to read employee file: returned false");
		} catch (FileNotFoundException e) {
			fail("Failed to read employee file: exception caught");
			e.printStackTrace(); 
		}
	}
	/**
	 * Tests the load resume file.
	 */
	@Test
	public void testLoadResume() {
		try {
			io.loadEmployees("input/sample", list);	
			if(!io.loadResume("input/sample-resume", list))
				fail("Failed to read resume file: returned false"); 

			assertTrue(list.lookUp("Jessica Daniels") != null);
			Employee jess = new Employee("Jessica Daniels", "R228401745", 'M', 1);
			assertTrue(jess != null);
			//System.out.println(list.getRoot() + " <- Root");
			
			assertTrue(list.lookUp("Jessica Daniels").equals(jess));
			
		} catch (FileNotFoundException e) {
			fail("Failed to read resume file: exception caught");
			e.printStackTrace();
		}

		try {
			io.loadEmployees("input/sample2", list);	
			if(!io.loadResume("input/sample-resume2", list))
				fail("Failed to read resume file: returned false"); 
			
			assertTrue(list.lookUp("Jessica Daniels") != null);
			Employee jess = new Employee("Jessica Daniels", "R228401745", 'M', 5);
			assertTrue(jess != null);
			//System.out.println(list.getRoot() + " <- Root");
			//System.out.println(list.lookUp("Jessica Daniels").getName());
			//System.out.println(list.lookUp("Jessica Daniels").getId());
			//System.out.println(list.lookUp("Jessica Daniels").getDegreeStat());
			
			assertTrue(list.lookUp("Jessica Daniels").equals(jess));
			
			
		} catch (FileNotFoundException e) {
			fail("Failed to read resume file: exception caught");
			e.printStackTrace();
		}
	}
	/**
	 * Tests the organizational profiles
	 */
	@Test
	public void testOutPut(){
		HumanResourcesManager hrm = new HumanResourcesManager("input/sample2", "input/sample-resume2");
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

		//System.out.println(hrm.generateOrganizationalProfile());
		
		assertTrue(hrm.generateOrganizationalProfile().equals(sample2Correct));
	}
}
