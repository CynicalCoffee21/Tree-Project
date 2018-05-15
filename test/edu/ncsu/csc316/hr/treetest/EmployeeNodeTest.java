/**
 * 
 */
package edu.ncsu.csc316.hr.treetest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.hr.tree.Employee;
import edu.ncsu.csc316.hr.tree.EmployeeNode;

/**
 * Employee Node Test class
 * @author Ryan Buchanan
 *
 */
public class EmployeeNodeTest {

	/** 
	 * Set up method 
	 * @throws java.lang.Exception
	 */ 
	@Before
	public void setUp() throws Exception {
		//Ignore
	}

	/**
	 * tests the constructor
	 * Test method for {@link edu.ncsu.csc316.hr.tree.EmployeeNode#EmployeeNode(edu.ncsu.csc316.hr.tree.Employee)}.
	 */
	@Test
	public void testEmployeeNode() {
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		EmployeeNode testNode = new EmployeeNode(testEmp0);
		assertTrue(testNode != null);
		assertTrue(testNode.getEmployee().equals(testEmp0));
		assertTrue(testNode.getSize() == 0);
		
		assertTrue(testNode.getEmployee().getName().equals(testEmp0.getName()));
		
		for(int i = 0; i < 10; i++)
			assertTrue(testNode.getChildren()[i] == null);	
	}
	/**
	 * Tests the wipe children functionality
	 */
	@Test
	public void testWipeChildred(){		
		Employee testchild0 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild1 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild2 = new Employee("Ryan F", "R123456789", 'D', 7);
		EmployeeNode testchildNode0 = new EmployeeNode(testchild0);
		EmployeeNode testchildNode1 = new EmployeeNode(testchild1);
		EmployeeNode testchildNode2 = new EmployeeNode(testchild2);
		
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		EmployeeNode testNode = new EmployeeNode(testEmp0);
		
		testNode.addChild(testchildNode0);
		testNode.addChild(testchildNode1);
		testNode.addChild(testchildNode2);
		for(int i = 0; i < testNode.getSize(); i++)
			assertTrue(testNode.getChildren()[i] != null);	
		
		testNode.wipeChildren();
		
		assertTrue(testNode.isLeaf());
		for(int i = 0; i < 10; i++)
			assertTrue(testNode.getChildren()[i] == null);	
	}
	/**
	 * Tests the adding of a child
	 * Test method for {@link edu.ncsu.csc316.hr.tree.EmployeeNode#addChild(edu.ncsu.csc316.hr.tree.EmployeeNode)}.
	 */
	@Test
	public void testAddChild() {
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testchild0 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild1 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild2 = new Employee("Ryan F", "R123456789", 'D', 7);
		Employee testchild3 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testchild4 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild5 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild6 = new Employee("Ryan F", "R123456789", 'D', 7);
		Employee testchild7 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testchild8 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild9 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild10 = new Employee("Ryan F", "R123456789", 'D', 7);
		Employee testchild11 = new Employee("Ryan F", "R123456789", 'D', 7);
		EmployeeNode testchildNode0 = new EmployeeNode(testchild0);
		EmployeeNode testchildNode1 = new EmployeeNode(testchild1);
		EmployeeNode testchildNode2 = new EmployeeNode(testchild2);
		EmployeeNode testchildNode3 = new EmployeeNode(testchild3);
		EmployeeNode testchildNode4 = new EmployeeNode(testchild4);
		EmployeeNode testchildNode5 = new EmployeeNode(testchild5);
		EmployeeNode testchildNode6 = new EmployeeNode(testchild6);
		EmployeeNode testchildNode7 = new EmployeeNode(testchild7);
		EmployeeNode testchildNode8 = new EmployeeNode(testchild8);
		EmployeeNode testchildNode9 = new EmployeeNode(testchild9);
		EmployeeNode testchildNode10 = new EmployeeNode(testchild10);
		EmployeeNode testchildNode11 = new EmployeeNode(testchild11);
		
		EmployeeNode testNode = new EmployeeNode(testEmp0);
		assertTrue(testNode.isLeaf());
		
		testNode.addChild(testchildNode0);
		testNode.addChild(testchildNode1);
		testNode.addChild(testchildNode2);
		testNode.addChild(testchildNode3);
		testNode.addChild(testchildNode4);
		testNode.addChild(testchildNode5);
		testNode.addChild(testchildNode6);
		testNode.addChild(testchildNode7);
		testNode.addChild(testchildNode8);
		testNode.addChild(testchildNode9);
		testNode.addChild(testchildNode10);
		testNode.addChild(testchildNode11);
		
		assertFalse(testNode.isLeaf());
		//System.out.println(testNode.getSize()); 
		assertTrue(testNode.getSize() == 12);
		
		for(int i = 0; i < testNode.getSize(); i++)
			assertTrue(testNode.getChildren()[i] != null);
		
		assertTrue(testNode.getChildren()[0].getEmployee().equals(testchild0));
		assertTrue(testNode.getChildren()[1].getEmployee().equals(testchild1));
		assertTrue(testNode.getChildren()[2].getEmployee().equals(testchild2));
		assertTrue(testNode.getChildren()[3].getEmployee().equals(testchild3));
		assertTrue(testNode.getChildren()[4].getEmployee().equals(testchild4));
		assertTrue(testNode.getChildren()[5].getEmployee().equals(testchild5));
		assertTrue(testNode.getChildren()[6].getEmployee().equals(testchild6));
		assertTrue(testNode.getChildren()[7].getEmployee().equals(testchild7));
		assertTrue(testNode.getChildren()[8].getEmployee().equals(testchild8));
		assertTrue(testNode.getChildren()[9].getEmployee().equals(testchild9));
		assertTrue(testNode.getChildren()[10].getEmployee().equals(testchild10));
		assertTrue(testNode.getChildren()[11].getEmployee().equals(testchild11));

	}
	/**
	 * Determines whether the isLeaf method works properly.
	 */
	@Test
	public void testIsLeaf(){
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testchild0 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild1 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild2 = new Employee("Ryan F", "R123456789", 'D', 7);
		EmployeeNode testchildNode0 = new EmployeeNode(testchild0);
		EmployeeNode testchildNode1 = new EmployeeNode(testchild1);
		EmployeeNode testchildNode2 = new EmployeeNode(testchild2);
		
		EmployeeNode testNode = new EmployeeNode(testEmp0);
		assertTrue(testNode.isLeaf());
		testNode.addChild(testchildNode0);
		testNode.addChild(testchildNode1);
		testNode.addChild(testchildNode2);
		assertFalse(testNode.isLeaf());
	}
	/**
	 * Test for the removal of a child
	 * Test method for {@link edu.ncsu.csc316.hr.tree.EmployeeNode#removeChild(edu.ncsu.csc316.hr.tree.EmployeeNode)}.
	 */
	@Test
	public void testRemoveChild() {
		Employee testEmp0 = new Employee("Ryan B", "R123456789", 'N', 4);
		Employee testchild0 = new Employee("Ryan C", "R123456789", 'B', 5);
		Employee testchild1 = new Employee("Ryan D", "R123456789", 'M', 6);
		Employee testchild2 = new Employee("Ryan F", "R123456789", 'D', 7);
		EmployeeNode testchildNode0 = new EmployeeNode(testchild0);
		EmployeeNode testchildNode1 = new EmployeeNode(testchild1);
		EmployeeNode testchildNode2 = new EmployeeNode(testchild2);
		
		EmployeeNode testNode = new EmployeeNode(testEmp0);
		assertTrue(testNode.isLeaf());
		testNode.addChild(testchildNode0);
		testNode.addChild(testchildNode1);
		testNode.addChild(testchildNode2);
		assertFalse(testNode.isLeaf());

		assertTrue(testNode.removeChild(testchild1)); //D
		assertTrue(testNode.removeChild(testchild0)); //C
		assertTrue(testNode.removeChild(testchild2)); //FS
		assertTrue(testNode.getSize() == 0);
	}

}
