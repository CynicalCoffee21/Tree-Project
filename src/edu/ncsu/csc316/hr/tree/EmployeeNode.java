package edu.ncsu.csc316.hr.tree;
/**
 * Node class that contains the data and child pointers.
 * 
 * @author Ryan Buchanan
 *
 */
public class EmployeeNode {
	private static int capacity = 10;
	private Employee data;
	private EmployeeNode[] children; 
	private int size; 
	/**
	 * Constructor
	 * @param data the employee for the node
	 */
	public EmployeeNode( Employee data ){ 
		setData(data);
		this.children = new EmployeeNode[capacity];
		setSize(0);
	}
	/**
	 * This method completely wipes the current list of employees by setting children
	 * to a new list.
	 */
	public void wipeChildren(){
		this.children = new EmployeeNode[capacity];
		setSize(0);
	}
	/**
	 * Returns the employee
	 * @return this.data is the employee
	 */
	public Employee getEmployee(){
		return this.data;
	}
	/**
	 * Sets the employee
	 * @param employee
	 */
	private void setData( Employee employee){
		this.data = employee;
	}
	/**
	 * Adds a child node to the array of nodes. 
	 * 	If the capacity is reached, then it will be doubled and things will be copied over.
	 * @param newNode New node
	 */
	public void addChild( EmployeeNode newNode ){
		if(getSize() >= capacity){
			capacity *= 2;
			EmployeeNode[] temp = new EmployeeNode[capacity];
			for(int i = 0; i < getSize(); i++)
				temp[i] = this.children[i];
			this.children = temp; 
		}   
		this.children[size++] = newNode;
	}
	/**
	 * Returns true if the current node has no children
	 * @return true/false boolean
	 */
	public boolean isLeaf(){
		if(this.getSize() == 0){
			return true;
		}
		return false;
	}
	/**
	 * Returns the current list of children
	 * @return this.children the children array 
	 */
	public EmployeeNode[] getChildren(){
		return this.children;
	}
	/**
	 * Remove a child node from the children array.
	 * @param child Node to remove
	 * @return true/false
	 */
	public boolean removeChild( Employee child ){
		for(int i = 0; i < getSize(); i++){
			if(children[i].getEmployee().equals(child)){
				for(int j = i; j < getSize() - 1; j++){
					children[j] = children[j + 1];
				}
				children[getSize() - 1] = null;
				this.size--;
				return true;
			}
		}			
		return false;
	}
	/**
	 * Return the size of the children array
	 * @return the size of children array
	 */
	public int getSize() {
		return this.size;
	}
	/**
	 * set the size of the children array
	 * @param size of children array
	 */
	private void setSize(int size) {
		this.size = size;
	}
	
}
