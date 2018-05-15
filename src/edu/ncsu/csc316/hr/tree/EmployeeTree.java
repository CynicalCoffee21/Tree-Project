package edu.ncsu.csc316.hr.tree;
/**
 * This class is the implementation of the Employee List, which happens to be a tree style structure.
 * The reason for such an implementation, is because of the nature of the employee to supervisor
 * relationship. Each employee, save the CEO(s), are all part of a subtree with their department
 * supervisor at that particular subtree's root/branch. 
 * 
 * There are 2 ways of searching through the tree, using the employee Id or name.
 * 
 * There are 2 ways of inserting into the tree, either as the child of the root,
 * 		or as the child of a subtree.
 *  
 * There is only one deletion, which removes the employee (if found) and promotes a replacement.
 * 
 * There is also a method used for updating the employee resume information. 
 * 		This method is primarily used during IO, when reading in the resume file.
 * @author Ryan Buchanan
 *
 */
public class EmployeeTree {
	private EmployeeNode root; 
	private int totalSize;
	private boolean remEmpFound = false;
	private boolean remWasLeaf = false;
	/**
	 * The constructor, simply initializes size to 0 and sets the root to null to start.
	 */
	public EmployeeTree() {
		setRoot(null);
		this.totalSize = 0;
	}
	/**
	 * Returns the root node
	 * @return root.getEmployee().getName() 
	 */
	public String getRoot(){
		return root.getEmployee().getName();
	}
	/**
	 * Returns the size of the list.
	 * @return the size
	 */
	public int getSize() {
		return totalSize;
	}
	/**
	 * Sets the root Node to the given Node.
	 * @param root the root to set
	 */
	private void setRoot(EmployeeNode root) {
		this.root = root;
	}
	/**
	 * Primarily used for the insertion of the root node, and simply adds to the end of the list.
	 * @param e : the employee to insert into the tree.
	 */
	public void insert(Employee e){
		if(this.root == null){
			root = new EmployeeNode(e);
			totalSize++;
		}
		else{
			root.addChild(new EmployeeNode(e));
			totalSize++;
		}
	} 
	/**
	 * Inserts an employee Node to as a child of another employee Node.
	 * @param child : New employee.
	 * @param parId : Id of the parent Node.
	 */
	public void insertBelow(Employee child, String parId){
		if(this.root == null){
			root = new EmployeeNode(child);
			totalSize++;
			return;
		}
		if(root.getEmployee().getId().equals(parId)){
			root.addChild(new EmployeeNode(child));
			totalSize++;
			return;
		}
		EmployeeNode searcher = root;
		EmployeeNode temp = lookUpById(parId, searcher);
		if(temp != null){
			temp.addChild(new EmployeeNode(child));
			totalSize++;
		}
	}
	/**
	 * Looks up an employee by their ID. Recursive.
	 * @param employeeId Id to search for
	 * @param current current node
	 * @return The Node of the found employee
	 */
	private EmployeeNode lookUpById(String employeeId, EmployeeNode current){
		if(employeeId == null)
			return null;
		if(root == null)
			return null;	
		//If the name matches the root's name, return the root.
		if(current.getEmployee().getId().equals(employeeId))
			return current;
		//Otherwise, we need to search the root's children.
		else if( !current.isLeaf() ){
			EmployeeNode temp;
			for(int i = 0; i < current.getSize(); i++){
				 temp = lookUpById(employeeId, current.getChildren()[i]);
				 if(temp != null)
					 return temp;
			}
		} 
		return null;
		
	}
	/**
	 * This method checks the given list of employees to see which is most qualified
	 * to become an interim replacement.
	 * @param children list of candidate employees
	 * @param int size of the list
	 * @return qualified candidate
	 */
	private EmployeeNode qualCheck(EmployeeNode[] children, int size){
		int mostYears = 0; //Highest number years found so far
		int mostSupervised = 0; //Highest number of supervised so far
		int bestDegree = 0; //Highest degree so far
		char[] degrees = {'N', 'A' , 'B', 'M', 'P'}; //Degree list
		EmployeeNode[] sameYears = new EmployeeNode[size];
		EmployeeNode[] sameNumSup = new EmployeeNode[size];
		EmployeeNode[] sameDegree = new EmployeeNode[size];
		EmployeeNode topEmployee = null;
		//Initial check for years
		int numSameYears = 0;
		for(int i = 0; i < size; i++){
			int year = children[i].getEmployee().getYearsOfService();
			//If an employee has the highest number of years so far.
			if(year > mostYears){
				mostYears = year;
				topEmployee = children[i];
				numSameYears = 0;
				sameYears = new EmployeeNode[size]; //reset the array
				sameYears[numSameYears++] = children[i]; //add the new topEmployee to the array
				continue;
			}
			//If an employee has the same number of years as another
			if(year == mostYears){
				sameYears[numSameYears++] = children[i];
			}
		}
		//If we get through that loop and only have one person with
		//the highest amount of years, then return them.
		if(numSameYears <= 1)
			return topEmployee;
		//Else reset count and try for number of supervised employees
		//Hopefully, the list will get smaller as we go along.
		//Check for numSupervised
		int numSameNumSup = 0;
		for(int j = 0; j < numSameYears; j++){
			int numSup = sameYears[j].getSize();
			if(numSup > mostSupervised){
				mostSupervised = numSup;
				topEmployee = sameYears[j];
				numSameNumSup = 0;
				sameNumSup = new EmployeeNode[size];
				sameNumSup[numSameNumSup++] = sameYears[j];
				continue;
			}
			if(numSup == mostSupervised){
				sameNumSup[numSameNumSup++] = sameYears[j];
			}		
		}
		//Same check as before.
		if(numSameNumSup <= 1)
			return topEmployee;
		//Check for bestDegree
		int numSameDegree = 0;
		for(int k = 0; k < numSameNumSup; k++){
			char degree = sameNumSup[k].getEmployee().getDegreeStat();
			int status = 0;
			for(int d = 0; d < 5; d++){
				if(degree == degrees[d]){
					status = d;
				}
			}
			if(status > bestDegree){
				bestDegree = status;
				topEmployee = sameNumSup[k];
				sameDegree = new EmployeeNode[size];
				numSameDegree = 0;
				sameDegree[numSameDegree++] = sameNumSup[k];
				continue;
			}
			if(status == bestDegree){
				sameDegree[numSameDegree++] = sameNumSup[k];
			}
		}
		if(numSameDegree <= 1)
			return topEmployee;
		//Finally an alphabetical order check.
		String topName = "";
		for(int l = 0; l < numSameDegree; l++){
			String curName = sameDegree[l].getEmployee().getName();
			//If we are at the start of the loop, set the first index 
			//to be the top for comparisons.
			if(l == 0){
				topName = curName;
				topEmployee = sameDegree[l];
				continue;
			}
			//If the current name is alphabetically before the topName
			//replace the topName and topEmployee
			if(curName.compareTo(topName) < 0){
				curName = topName;
				topEmployee = sameDegree[l];
			}
		}
		return topEmployee;
		
	}
	/**
	 * Recursive method to search for an appropriate replacement employee. This method is incredibly
	 * complicated and sucked to write. I just hope it works correctly.
	 * @param employeeToDelete the employee to remove
	 * @param current The current node 
	 * @return The node of the interim replacement employee
	 */
	private EmployeeNode deleteHelper(String employeeToDelete, EmployeeNode current, EmployeeNode parent){
		EmployeeNode parentNode = parent;
		boolean currentIsRemoval = false;
		if(remWasLeaf)
			return null;
		if(current.getEmployee().getName().equals(employeeToDelete)){
			//Set the found variable.
			currentIsRemoval = true;
			remEmpFound = true;
		}
		//Base case: If we reach a leaf then we either found the employee, or they don't exist.
		if(current.isLeaf()){
			String currentId = current.getEmployee().getId();
			String childId;
			//If the employee we want to delete is a leaf node...delete it.
			if(remEmpFound){
				childId = current.getEmployee().getId();
				//Search and delete the node from the parents child list.
				for(int i = 0; i < parentNode.getSize(); i++){
					currentId = parentNode.getChildren()[i].getEmployee().getId();
					if(currentId.equals(childId)){
						parentNode.removeChild(parentNode.getChildren()[i].getEmployee());
					}
				}
				if(currentIsRemoval){
					remWasLeaf = true;
					return null;
				}
			}

			return null;
		}
		//Recursive Cases :
		if(!remEmpFound){
			//Just Search : If we haven't found the employee to remove yet, then keep looking for them.
			EmployeeNode interim = null;
			for(int i = 0; i < current.getSize(); i++){
				interim = deleteHelper(employeeToDelete, current.getChildren()[i], current);
			}
			return interim;
		}
		//Search and Replace 
		EmployeeNode qualified = qualCheck(current.getChildren(), current.getSize());	
		//If the qualified replacement is not a supervisor, then simply delete their spot.
		if(qualified.isLeaf()){
			//If the parent is null, then the current is the root
			if(parent == null){
				//Copy the roots children into qualified
				for(int i = 0; i < current.getSize(); i++){
					//Skip itself, so we can effectively remove it
					if(current.getChildren()[i].equals(qualified)){							
						continue; 
					}
					qualified.addChild(current.getChildren()[i]);
				}
				setRoot(qualified);
				return qualified;
			}
			for(int i = 0; i < parent.getSize(); i++){
				//Find the previous supervisor in the parents child list.
				if(parent.getChildren()[i].getEmployee().equals(current.getEmployee())){
					//We need to copy all of the children nodes from the previous supervisor
					// into the qualified employees child list, since they are the new supervisor.
					for(int j = 0; j < current.getSize(); j++){
						//skip the qualified employee, and leave their spot vacant.
						if(current.getChildren()[j].equals(qualified)){							
							continue;
						}
						qualified.addChild(current.getChildren()[j]);
					}
					//Replace the previous supervisor.
					parent.getChildren()[i] = qualified;
				}
			}
			return current;
		}
		//If the qualified employee is not a leaf, we recall the method using qualified as current
		//and current as the parent.
		EmployeeNode interim;
		interim = deleteHelper(employeeToDelete, qualified, current);
		//At this point, interim should be equal to qualified, 
		//after their position has been taken over. So its safe to replace the old children.
		interim.wipeChildren();
		for(int i = 0; i < current.getSize(); i++){
			//If we encounter interim in current's child list, skip it.
			if(current.getChildren()[i].equals(interim)){							
				continue; 
			}
			interim.addChild(current.getChildren()[i]);
		}
		//If we end up deleting the root, we set the new root and return the interim.
		if(root.getEmployee().getName().equals(current.getEmployee().getName())){
			setRoot(interim);
			return interim;
		}
		//Now have interim take current's place as a child of parent.
		for(int i = 0; i < parent.getSize(); i++){
			//Find current in the parent's child list.
			if(parent.getChildren()[i].getEmployee().equals(current.getEmployee())){
				parent.getChildren()[i] = interim; //replace
				break;
			}
		}
		//If the current employee is the one we wish to delete, then return their replacement.
		if(currentIsRemoval)
			return interim;
		//Otherwise, we still have some replacement to do, so return current.
		return current;

	}
	/**
	 * Returns a string representation of the interim employee
	 * who replaces the removed employee.
	 * @param name : ' first + " " + last ' from the HRM call to remove.
	 * @return String : The name of the replacement of the removed employee.
	 */
	public String delete(String name){
		//If employee not found : “Employee was not found.”
		
		if(root == null || name == null || lookUp(name) == null)
			return "Employee was not found.";
		
		EmployeeNode interim = deleteHelper(name, root, null);
		//If the return was null, then check the "found" boolean.
		//If the boolean is false, then the employee didn't exist.
		//Otherwise the delete was successful, but there was no replacement.
		if(interim == null && remEmpFound){
				remWasLeaf = false;
				remEmpFound = false; //Reset the boolean for future use.
				totalSize--;
				return "No interim supervisor";
		}
		totalSize--;
		if(interim == null)
			return null;
		return interim.getEmployee().getName();
		
	}
	/**
	 * This method searches the tree for an employee given their full name.
	 * @param name : The full name of the employee 'first + " " + last '
	 * @return The found employee, or null.
	 */
	public Employee lookUp(String name){
		if(root == null)
			return null;
		EmployeeNode searcher = root;
		return lookUpHelp(name, searcher);
	}
	/**
	 * Recursive method to search through the tree.
	 * @param name of the desired employee
	 * @param current node we are looking at
	 * @return Either the found employee, or null.
	 */
	private Employee lookUpHelp(String name, EmployeeNode current){
		if(name == null)
			return null;
		//If the name matches the root's name, return the root.
		if(current.getEmployee().getName().equals(name))
			return current.getEmployee();
		//Otherwise, we need to search the root's children.
		else if( !current.isLeaf() ){
			Employee temp;
			for(int i = 0; i < current.getSize(); i++){
				 temp = lookUpHelp(name, current.getChildren()[i]);
				 if(temp != null)
					 return temp;
			}
		}
		return null;
	}
	/**
	 * Helper for the toString method. 
	 * It is a recursive method that searches through the children arrays or Nodes.
	 * 
	 * Use Queue to insert children as I go across a row, then once I hit a nonleaf node,
	 *  push the children to the back of the queue. Add the queue as another parameter for toStringHelper.
	 * 
	 * @param list of EmployeeNode's  
	 * @return A built string that contains a print out of the children Nodes.
	 *
	private String toStringHelper( EmployeeNode[] childList ){
		StringBuilder childString = new StringBuilder();
		EmployeeNode[] grandChildren = new EmployeeNode[totalSize];
		int count = 0;
		int grandCount = 0;
		//Base Case
		if(childList[0] == null )
			return ""; 
		else{
		//Loop the childList, add names to string builder
		//Store any "nonleaf" nodes.
			while(childList[count] != null){
				EmployeeNode check = childList[count];
				childString.append("   " + check.getEmployee().getName() + "\n");
				//If the employee is not a lead, add them to the list
				if( !check.isLeaf() ){
					grandChildren[grandCount++] = check;
				}
				count++;
			}
			//Now loop through the list of "nonleaf" children, and recursively call
			//The method on their respective lists of children, in order.
			for(int i = 0; i < grandCount; i++)
				childString.append( toStringHelper( grandChildren[i].getChildren() ) );
		}
		
		return childString.toString();	
	}
	*/
	/**
	 * Creates and returns a String representation of the employee hierarchy.
	 * @return String representation of the employee hierarchy
	 */
	public String toString(){
		StringBuilder returnVal = new StringBuilder();
		if(root == null || getSize() == 0)
			return "No active employees";
		EmployeeNode[] queue = new EmployeeNode[totalSize];
		int back = 0;
		//EmployeeNode searcher = root;
		//returnVal.append( "   " + root.getEmployee().getName() + "\n");
		queue[back++] = root;
		//First search and append the children of the root.
		int front = 0;
		while(front < back){
			//POP
			EmployeeNode printNode = queue[front++];
			//queue[back] = null;
			//back--;
			//Print
			returnVal.append( "   " + printNode.getEmployee().getName() + "\n");
			//PUSH children
			if(!printNode.isLeaf()){
				for(int i = 0; i < printNode.getSize(); i++){
					queue[back++] = printNode.getChildren()[i];
				}
			}
		}
		//returnVal.append(  );
		
		// ... 
		//toStringHelper( root.getChildren() );
		return returnVal.toString();
	}
	/**
	 * This method is primarily used by the IO class, when reading the resume file.
	 * It searches the employee list for the given employee information, and updates it.
	 * @param employeeID : Id to search for
	 * @param degree : degree status of the employee
	 * @param years : years of service for the employee
	 * @return true/false boolean
	 */
	public boolean findAndUpdate(String employeeID, char degree, int years){

		EmployeeNode searcher = root;
		EmployeeNode temp = lookUpById(employeeID, searcher);
		if(temp == null)
			return false;
		else
			temp.getEmployee().employeeUpdate(degree, years);
		return true;
	}

}