/**
 * 
 */
package edu.ncsu.csc316.hr.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.hr.tree.EmployeeTree;
/**
 * This is the controller class, for the MVC pattern that this project design follows.
 * It contains most of the relevant methods pertaining to the projects primary functionality.
 * 
 * Including: generateOrganizationalProfile and removeEmployee
 * 
 * @author Ryan Buchanan
 *
 */
public class HumanResourcesManager {
	private EmployeeTree employeeList;
	private HumanResourcesManagerIO ioProcessor;
	/**
	 * Constructs a new HR manager with the given input files
	 * 
	 * @param pathToEmployeeFile 
	 *            - the path to the employee input file
	 * @param pathToResumeFile
	 *            - the path to the resume input file
	 */
	public HumanResourcesManager(String pathToEmployeeFile, String pathToResumeFile) {
		employeeList = new EmployeeTree(); 
		ioProcessor = new HumanResourcesManagerIO();  
		
		try {
			ioProcessor.loadEmployees(pathToEmployeeFile, employeeList);
		} catch (NullPointerException | FileNotFoundException e) {
			//System.out.println("An error occured during the reading of: " + pathToEmployeeFile);
			//e.printStackTrace();
		}
		try {
			ioProcessor.loadResume(pathToResumeFile, employeeList);
		} catch (NullPointerException | FileNotFoundException e) {
			//System.out.println("An error occured during the reading of: " + pathToResumeFile);
			//e.printStackTrace();
		} 
		
	}
	/**
	 * Returns a string representation of the interim employee
	 * who replaces the removed employee.
	 * 
	 * @param first - the first name of the employee to remove
	 * @param last - the last name of the employee to remove
	 * @return the name of the employee who was promoted to interim supervisor
	 */
	public String removeEmployee(String first, String last)
	{
		return employeeList.delete(first + " " + last);
	}  

	/** 
	 * Returns the string representation of the organizational
	 * profile of the company using the given input employee file.
	 * 
	 * @return the organizational profile
	 */
	public String generateOrganizationalProfile()
	{
		StringBuilder returnVal = new StringBuilder();
		returnVal.append("OrganizationalProfile[\n");
		//...TODO (3 spaces followed by the full name, 1 per line)
		//I need to print the nodes followed by their children, 
		//before moving on to the children's children...etc.
		returnVal.append( employeeList.toString() );
		returnVal.append("]");
		return returnVal.toString();
	}

}
