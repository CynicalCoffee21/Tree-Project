package edu.ncsu.csc316.hr.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.hr.tree.Employee;
import edu.ncsu.csc316.hr.tree.EmployeeTree;
/**
 * The HRM_IO class is the I/O handler for the Human Resources Manager.
 * 
 * It reads the employee and resume files separately. The employee file should always be first,
 * since the resume helper method is dependent upon the Id of the employees already in the list.
 * 
 * @author Ryan Buchanan
 *
 */
public class HumanResourcesManagerIO {
	/** 
	 * Reads employees from filename, given that it exists, then calls a helper method to 
	 * create the employee objects needed to add to the list.
	 * @param filename file path of the employee file
	 * @param list the employee tree
	 * @return true/false whether or not the file was read correctly, without errors.
	 * @throws FileNotFoundException 
	 */
	public boolean loadEmployees(String filename, EmployeeTree list) throws FileNotFoundException{
		Scanner empScanner = new Scanner(new File(filename));
		//This is the Id of the previously read employee.
		String prevId = null;
		//This is the Id of the current parent node.
		String parId = null;
		//This is a pseudo-queue full of the Id's 
		String idqueue[] = new String[1024];
		//Size for keeping track of the elements in the pseudo-queue
		int size = 0;
		int numEmp = 0;
		
		while(empScanner.hasNextLine()){
			String line = empScanner.nextLine().trim();
			//This indicates that we are about to enter a new subtree/department.
			if(line.length() <= 0){
				continue;
			}
			
			if( line.charAt(0) == '(' ){			
				if( prevId != null ){
					idqueue[size++] = parId;
					parId = prevId;
				}
				continue;
			} //This is in case we reach the end of a particular subtree/department roster.
			else if( line.charAt(0) == ')'){	
				parId = idqueue[size - 1]; //Set the parent Id to the last one added to the queue.
				idqueue[size - 1] = null; //delete that Id from the queue, and dec. size.
				size--;
				continue;
			}
			//At this point, we know that there is relevant info to get from the line. 
			//(Employee to add)
			else {
				Employee newEmployee = processEmployee(line);
				//If there is any failure in inserting the new employees,
				//The whole thing explodes.
				if( newEmployee != null ){
					//If this is the first iteration, set the parId. 
					//Since the boss/CEO is always first in the file.
					if(numEmp == 0){
						parId = newEmployee.getId();
						prevId = newEmployee.getId();
						list.insert( newEmployee );
						numEmp++;
						//System.out.println(newEmployee.toString() + " Root ");
					} //In all other instances, we add below some parent node/supervisor. 
					else {
						list.insertBelow( newEmployee, parId );
						//System.out.println(newEmployee.toString() + " Parent = " + parId);
						prevId = newEmployee.getId();
						numEmp++;
					}
				} //If we get here, something bad happened. (NE == null)
				else {
					empScanner.close();
					return false;
				}
			}
		}
		empScanner.close();
		return true;
		
	}
	/**
	 * Process employee is a method used for creating Employee objects based on the input file.
	 * @param line a line gathered from the employee file
	 * @return the newly created employee.
	 */
	private Employee processEmployee(String line){
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		// ... 
		String firstname = lineScanner.next().trim();
		String lastname = lineScanner.next().trim();
		String employeeId = lineScanner.next().trim();
		// ...
		lineScanner.close();
		String name = firstname + " " + lastname;
		return new Employee(name, employeeId);
	}
	/**
	 * Reads the resume file and then updates the employees in the tree, with the new 
	 * information. 
	 * @param filename file path of the resume file 
	 * @param list the employee tree
	 * @return true/false whether or not the method reads the whole file without error or not.
	 * @throws FileNotFoundException
	 */
	public boolean loadResume(String filename, EmployeeTree list) throws FileNotFoundException{
		
		Scanner resScanner = new Scanner(new File(filename));
		//Skips the first line, which is pretty much just labels.
		resScanner.nextLine();
		String employeeId;
		int years;
		char degree;
				
		while(resScanner.hasNextLine()){
			String line = resScanner.nextLine().trim().replaceAll("\\s", "");
			if(line.length() == 0) 
				break;
			Scanner resScanner1 = new Scanner(line);
			resScanner1.useDelimiter(",");
			
			//This is the Id of the employee to search for.
			employeeId = resScanner1.next();
			//This is the years of service for the employee
			years = resScanner1.nextInt();
			//This is the degree char for the employee
			degree = resScanner1.nextLine().charAt(1);
			
			if( !findAndUpdate(employeeId, degree, years, list) ){
				resScanner.close();
				resScanner1.close();
				return false;
			}
			resScanner1.close();
		}		
		resScanner.close();
		return true;
	}
	/**
	 * Find and update the Employee in the tree with the matching Id parameter
	 * @param employeeId The Id of the employee to be updated
	 * @param degreeStat The degree status of the employee
	 * @param years The years of service of the employee
	 * @param list the employee tree
	 * @return true/false whether or not the employee was added to the tree successfully or not.
	 */
	private boolean findAndUpdate(String employeeId, char degreeStat, int years, EmployeeTree list) {
		//System.out.println("Information = " + Id + " : " + years + " : " + degreeStat );
		return list.findAndUpdate(employeeId, degreeStat, years);
	}
}
