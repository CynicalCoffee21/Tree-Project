package edu.ncsu.csc316.hr.treetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.tree.Employee;
/**
 * This is the test class for the employee object.
 * @author Ryan Buchanan
 *
 */
public class EmployeeTest {
	/**
	 * Set up
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Ignore
	}
	/**
	 * Constructor with only name and ID test
	 */
	@Test
	public void testEmployeeStringString() {
		Employee testEmp = new Employee("Ryan", "R123456789");
		if( !testEmp.getName().equals("Ryan") ) 
			fail("The employee name was incorrect: " + testEmp.getName());
		if( !testEmp.getId().equals("R123456789") )
			fail("The employee Id was incorrect.");
	}
	/**
	 * Test constructor with all parameters
	 */
	@Test
	public void testEmployeeStringStringCharInt() {
		Employee testEmp = new Employee("Ryan", "R123456789", 'N', 4);
		if( !testEmp.getName().equals("Ryan") )
			fail("The employee name was incorrect.");
		if( !testEmp.getId().equals("R123456789") )
			fail("The employee Id was incorrect.");
		if( testEmp.getDegreeStat() != 'N')
			fail("The employee Degree status was wrong");
		if( testEmp.getYearsOfService() != 4)
			fail("The emploee years of service was incorrect");
	}
	/**
	 * Tests the update method for employees
	 */
	@Test
	public void testEmployeeUpdate() {
		Employee testEmp = new Employee("Ryan", "R123456789");
		if( !testEmp.getName().equals("Ryan") )
			fail("The employee name was incorrect.");
		if( !testEmp.getId().equals("R123456789") )
			fail("The employee Id was incorrect.");
		if( testEmp.getDegreeStat() != ' ')
			fail("The employee Degree status was wrong: " + testEmp.getDegreeStat());
		if( testEmp.getYearsOfService() != -1)
			fail("The emploee years of service was incorrect");
		
		testEmp.employeeUpdate('N', 4);
		
		if( testEmp.getDegreeStat() != 'N')
			fail("The employee Degree status was wrong");
		if( testEmp.getYearsOfService() != 4)
			fail("The emploee years of service was incorrect");
	}
	/**
	 * Tests toString method
	 */
	@Test
	public void testToString() {

		Employee testEmp = new Employee("Ryan", "R123456789", 'N', 4);
		Employee testEmp2 = new Employee("Ryan", "R123456789");
		Employee testEmp3 = new Employee("Ryan", "R123456789", 'N', -1);
		Employee testEmp4 = new Employee("Ryan", "R123456789", ' ', 4);
		
		if( testEmp3.toString().isEmpty())
			fail("The string was empty.");
		if( !testEmp3.toString().equals("Ryan, R123456789"))
			fail("The string was incorrect: Ryan, R123456789  vs " + testEmp.toString());
		if( testEmp4.toString().isEmpty())
			fail("The string was empty.");
		if( !testEmp4.toString().equals("Ryan, R123456789"))
			fail("The string was incorrect: Ryan, R123456789  vs " + testEmp.toString());
		
		
		if( testEmp.toString().isEmpty()) 
			fail("The string was empty.");
		if( !testEmp.toString().equals("Ryan, R123456789, 4, N"))
			fail("The string was incorrect: Ryan, R123456789, 4, N vs " + testEmp.toString());
		if( testEmp2.toString().isEmpty())
			fail("The string was empty.");
		if( !testEmp2.toString().equals("Ryan, R123456789"))
			fail("The string was incorrect: Ryan, R123456789 vs " + testEmp.toString());
	}
	
	/**
	 * Test for equality
	 */
	@Test
	public void testEqualsEmployee() {
		Employee testEmp0 = new Employee("Ryan", "R123456789", 'N', 4);
		Employee testEmp1 = new Employee("Ryan", "R987654321", 'N', 4);
		Employee testEmp2 = new Employee("Ryan", "R564738291", 'N', 4);
		Employee testEmp3 = new Employee("Ryan", "R123456789", 'N', 4);
		
		Employee testEmp4 = new Employee("Jeff", "R123456789", 'B', 27);
		Employee testEmp5 = new Employee("Jeff", "R123456789", 'M', 27);
		Employee testEmp6 = new Employee("Jeff", "R123456789", 'N', 27);
		
		
		Employee testEmp7 = new Employee("Jeff", "R123456789", 'N', 27);
		Employee testEmp8 = new Employee("Jeff", "R123456789", 'N', 4);
		Employee testEmp9 = new Employee("Jeff", "R123456789", 'N', 12);
		
		Employee nullEmp = null;
		String notEmp = "Not an employee";
		
		assertFalse(testEmp0.equals(testEmp8));
		assertFalse(testEmp4.equals(testEmp5));
		assertFalse(testEmp4.equals(testEmp6));
		assertFalse(testEmp7.equals(testEmp8));
		assertFalse(testEmp7.equals(testEmp9));
		assertFalse(testEmp7.equals(testEmp4));
		
		assertFalse(testEmp0.equals(nullEmp));
		assertTrue(testEmp0.equals(testEmp0));
		assertFalse(testEmp0.equals(notEmp));
		
		if( testEmp0.equals(testEmp1) )
			fail("These should not be equal: " 
					+ testEmp0.getId() + ", " + testEmp1.getId());
		if( testEmp2.equals(testEmp3) )
			fail("These should not be equal: " 
					+ testEmp2.getId() + ", " + testEmp3.getId());
		if( testEmp1.equals(testEmp3) )
			fail("These should not be equal: " 
					+ testEmp1.getId() + ", " + testEmp3.getId());
		if( !testEmp0.equals(testEmp3) )
			fail("These should be equal: " 
					+ testEmp0.getId() + ", " + testEmp3.getId());
	}

}
