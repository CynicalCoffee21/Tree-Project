package edu.ncsu.csc316.hr.ui;

import java.io.File;
import java.util.Scanner;

import edu.ncsu.csc316.hr.manager.HumanResourcesManager;

/**
 * The User Interface for the Human Resources Manager system
 * @author Ryan Buchanan
 *
 */
public class HumanResourcesManagerUI {
	private static HumanResourcesManager hrm;
	/**
	 * Main method where all of the user interaction takes place.
	 * This method also handles the possibility of incorrect files/inputs.
	 * @param args command line arguments
	 */
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in); 
		String empFile, resFile, command, firstName, lastName;
		//A labeled loop that repeats until the user provides a valid employee file.
		employee: 
			while( true ){	
				System.out.println("Please enter a valid Employee file: ");			
				empFile = input.nextLine();
				if(!(new File(empFile).exists())){
					System.out.println("Invalid file path, please try another pathname.");
					continue employee;
				}
				break;
			}
		//A labeled loop that repeats until the user provides a valid resume file.
		resume: 
			while( true ){
				System.out.println("Please enter a valid Resume file: ");
				resFile = input.nextLine();
				if(!(new File(resFile).exists())){
					System.out.println("Invalid file path, please try another pathname.");
					continue resume;
				}
				break;
			}
			
		hrm = new HumanResourcesManager(empFile, resFile);
		
		mainMenu:
			while( true ){
				System.out.println(" "); //Line break & Main menu text
				System.out.println("Welcome to the main menu of the Human Resources Manager!");
				System.out.println("There are only a few commands that do anything, so please choose from the following: ");
				System.out.println("To quit the program type: 'exit' ");
				System.out.println("To generate an organizational profile type: 'organize' ");
				System.out.println("To remove an employee type: 'remove' ");
				System.out.println("To re-print this menu type: literally anything else.");
				
				command = input.nextLine();
				
				if( command.equals("exit") )
					break;
				else if( command.equals("organize") )
					System.out.println(hrm.generateOrganizationalProfile());
				else if( command.equals("remove")){
					System.out.println("Please enter the employee's first name: ");
					firstName = input.nextLine();
					System.out.println("Please enter the employee's last name: ");
					lastName  = input.nextLine();
					
					System.out.println("The new interim supervisor is: " + hrm.removeEmployee(firstName, lastName) );
					System.out.println(" ");
					
					continue mainMenu;
				}
				else
					continue mainMenu;
			}
		input.close();
		
		System.out.println("Goodbye");
	}
	
}
