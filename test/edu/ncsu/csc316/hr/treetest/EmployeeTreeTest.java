package edu.ncsu.csc316.hr.treetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.tree.Employee;
import edu.ncsu.csc316.hr.tree.EmployeeTree;
/**
 * The test class for the employeeTree class/structure
 * @author Ryan Buchanan
 *
 */
public class EmployeeTreeTest { 
	private EmployeeTree list;
	/**
	 * set up
	 */
	@Before
	public void setUp() throws Exception {
		list = new EmployeeTree();
	}
	/**
	 * Tests the employe TRee creation
	 */
	@Test
	public void testEmployeeTree() {
		if(list == null)
			fail("the list was null");
		if(list.getSize() != 0)
			fail("Size was incorrect");
	}
	/**
	 * Tests the normal insertion method
	 */
	@Test
	public void testInsert() {
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testEmp1 = new Employee("Jeff C", "R123456789", 'B', 2);
		Employee testEmp2 = new Employee("Andrea D", "R123456789", 'M', 3);
		Employee testEmp3 = new Employee("Caroline F", "R123456789", 'B', 1);
		
		list.insert(testEmp0);
		list.insertBelow(testEmp1, testEmp0.getId());
		list.insertBelow(testEmp2, testEmp1.getId());
		list.insertBelow(testEmp3, testEmp2.getId());
		
		assertTrue(testEmp0.equals(list.lookUp("Ryan B")));
		assertTrue(testEmp1.equals(list.lookUp("Jeff C")));
		assertTrue(testEmp2.equals(list.lookUp("Andrea D")));
		assertTrue(testEmp3.equals(list.lookUp("Caroline F")));
		
		if(list.lookUp("Jerry S") != null)
			fail("This person should not exist in the list");
		if(list.lookUp(null) != null)
			fail("This should be null, since the name was null");
	}
	/**
	 * Test insert below a given employee node based on id
	 */
	@Test
	public void testInsertBelow(){
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testEmp1 = new Employee("Jeff C", "R123456789", 'B', 2);
		Employee testEmp2 = new Employee("Andrea D", "R123456789", 'M', 3);
		Employee testEmp3 = new Employee("Caroline F", "R123456789", 'B', 1);
		
		list.insert(testEmp0);
		list.insertBelow(testEmp1, testEmp0.getId());
		list.insertBelow(testEmp2, testEmp1.getId());
		list.insertBelow(testEmp3, testEmp2.getId());
		
		assertTrue(testEmp0.equals(list.lookUp("Ryan B")));
		assertTrue(testEmp1.equals(list.lookUp("Jeff C")));
		assertTrue(testEmp2.equals(list.lookUp("Andrea D")));
		assertTrue(testEmp3.equals(list.lookUp("Caroline F")));
		
		if(list.lookUp("Jerry S") != null) 
			fail("This person should not exist in the list");
		if(list.lookUp(null) != null)
			fail("This should be null, since the name was null");
	} 
	/**
	 * tests the delete method for the tree
	 */
	@Test
	public void testDelete() {
		Employee testEmp0 = new Employee("Ryan B", "R123456786", 'N', 4);
		Employee testEmp1 = new Employee("Jeff C", "R123456787", 'B', 2);
		Employee testEmp2 = new Employee("Andrea D", "R123456788", 'M', 3);
		Employee testEmp3 = new Employee("Caroline F", "R123456789", 'B', 1);
		
		//Test for a null value
		assertTrue(list.delete("Ryan B").equals("Employee was not found."));
		assertTrue(list.delete(null).equals("Employee was not found."));
		
		list.insert(testEmp0);
		list.insertBelow(testEmp1, testEmp0.getId());
		list.insertBelow(testEmp2, testEmp0.getId());
		list.insertBelow(testEmp3, testEmp0.getId());
		
		assertTrue(testEmp0.equals(list.lookUp("Ryan B")));
		assertTrue(testEmp1.equals(list.lookUp("Jeff C")));
		assertTrue(testEmp2.equals(list.lookUp("Andrea D")));
		assertTrue(testEmp3.equals(list.lookUp("Caroline F")));
		System.out.println(list.toString());
		
		String del1 = "   Ryan B\n" + "   Jeff C\n" + "   Andrea D\n";
		String del2 = "   Andrea D\n" + "   Jeff C\n";
		//Deletion of a leaf node.
		//System.out.println(list.delete("Caroline F"));
		assertTrue(list.delete("Caroline F").equals("No interim supervisor"));
		assertTrue(list.getSize() == 3);
		System.out.println(list.toString());
		assertTrue(list.toString().equals(del1));
		//Deletion of the root node.
		//System.out.println(list.delete("Ryan B"));
		//System.out.println(list.toString());
		assertTrue(list.delete("Ryan B").equals("Andrea D"));
		assertTrue(list.getSize() == 2);
		assertTrue(list.toString().equals(del2));
	}
	/**
	 * Test the get root function
	 */
	@Test
	public void testGetRoot(){
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		list.insert(testEmp0);
		assertTrue(testEmp0.getName().equals(list.getRoot()));
	}
	/**
	 * tests the normal lookUp method
	 */
	@Test
	public void testLookUp() {
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testEmp1 = new Employee("Jeff C", "R123456789", 'B', 2);
		Employee testEmp2 = new Employee("Andrea D", "R123456789", 'M', 3);
		Employee testEmp3 = new Employee("Caroline F", "R123456789", 'B', 1);
		
		list.insert(testEmp0);
		list.insertBelow(testEmp1, testEmp0.getId());
		list.insertBelow(testEmp2, testEmp1.getId());
		list.insertBelow(testEmp3, testEmp2.getId());
		
		assertTrue(testEmp0.equals(list.lookUp("Ryan B")));
		assertTrue(testEmp1.equals(list.lookUp("Jeff C")));
		assertTrue(testEmp2.equals(list.lookUp("Andrea D")));
		assertTrue(testEmp3.equals(list.lookUp("Caroline F")));
		
		if(list.lookUp("Jerry S") != null)
			fail("This person should not exist in the list");
		if(list.lookUp(null) != null)
			fail("This should be null, since the name was null");
	}

}
