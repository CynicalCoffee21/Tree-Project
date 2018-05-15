package edu.ncsu.csc316.hr.tree;
/**
 * The employee object. Used to populate the employee tree.
 * @author Ryan Buhanan
 *
 */
public class Employee {
	//Combination of the first and last name of the employee
	//Separated by a space
	private String name;
	//Id used to identify the employee 
	private String employeeId;
	//The degree status of the employee, initially blank
	private char degreeStat = ' '; 
	//Years of service, initially 0
	private int yearsOfService = -1;   
	/**
	 * Creates an employee using only two of the fields. 
	 * Used primarily for testing.
	 * @param name both first and last name combined
	 * @param employeeId Id of the employee
	 */
	public Employee(String name, String employeeId) {
		setName(name);
		setId(employeeId);
	}
	/**
	 * Creates an employee using all of the fields. 
	 * Used primarily for testing.
	 * @param name both first and last name combined
	 * @param employeeId Id of the employee
	 * @param degree degree status of the employee
	 * @param years years of service
	 */
	public Employee(String name, String employeeId, char degree, int years) {
		setName(name);
		setId(employeeId);
		setDegreeStat(degree);
		setYearsOfService(years);
	}
	/**
	 * Updates the current employee with the degree and years fields.
	 * @param degree degree status
	 * @param years years of service
	 */
	public void employeeUpdate(char degree, int years){
		setDegreeStat(degree);
		setYearsOfService(years);
	} 
	/**
	 * gets the full name
	 * @return the name
	 */
	public String getName() {
		return this.name; 
	}

	/**
	 * sets the employee name (full)
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the id
	 * @return the id
	 */
	public String getId() {
		return this.employeeId;
	}

	/**
	 * sets the id
	 * @param employeeId the id to set
	 */
	private void setId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * get degree status
	 * @return the degreeStat
	 */
	public char getDegreeStat() {
		return this.degreeStat;
	}

	/**
	 * set degree status
	 * @param degreeStat the degreeStat to set
	 */
	private void setDegreeStat(char degreeStat) {
		this.degreeStat = degreeStat;
	}

	/**
	 * get years of service
	 * @return the yearsOfService
	 */
	public int getYearsOfService() {
		return this.yearsOfService;
	}

	/**
	 * set years of service
	 * @param yearsOfService the yearsOfService to set
	 */
	private void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}
	/**
	 * Returns the Employee fields as a string.
	 * @return String that combines the currently non-null fields.
	 */
	public String toString(){
		//If the years of Service and degree status haven't been updated 
		//yet, then we don't want to report them. Mainly for testing.
		if(yearsOfService == -1 || degreeStat == ' '){
			return name + ", " + employeeId;
		}
		return name + ", " + employeeId + ", " + yearsOfService + ", " + degreeStat;
	}
	/**
	 * Equals, auto-generated
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (!employeeId.equals(other.employeeId))
			return false;
		if (degreeStat != other.degreeStat)
			return false;
		if (!name.equals(other.name))
			return false;
		if (yearsOfService != other.yearsOfService)
			return false;
		return true;
	}
}
