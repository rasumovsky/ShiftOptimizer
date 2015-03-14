///////////////////////////////////////////////////////////////////////////////
// 
// Title:            Scheduler.java
// Files:            Employee.java, Employer.java, TimeSlot.java,
//                   GeneticOptimizer.java
//
// Authors:          Andrew Hard, Ezra Schricker 
// Email:            hardandrew1@gmail.com
// Created:          November 8, 2014
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 * The Scheduler class loads an employer's shift schedule, as well as the 
 * schedules of employees, and assigns employees to each shift. 
 *
 * @author Andrew Hard
 * @author Ezra Schricker
 */
public class Scheduler {
    
    private int nEmployees;
    private List<Employee> allEmployees;
    private Employer theEmployer;
    
    
    /**
     * The main method loads the employer and employee schedules, stores them,
     * and implements an optimization algorithm to find a good shift assignment
     * schedule.
     * @param args[0] The file containing Employer shifts.
     * @param args[1]...args[N] The files containing the N employee schedules.
     */
    public static void main(String[] args) {
	
	if (args.length < 2) {
	    System.out.println("Usage: java Scheduler ShiftFile EmployeeFiles");
	    System.exit(0);
	}
	
	// Initialize the Employer from the shift file provided:
	try {
	    Employer theEmployer = new Employer(args[0]);
	} catch (FileNotFoundException FNFE) {
	    System.out.printf("Error: could not find employer's shift file.");
	}
	
	// Loop over Employees to obtain their schedules:
	nEmployees = 0;
	allEmployees = new ArrayList<Employee>();
	for (int i = 1; i < args.length; i++) {
	    // Dummy name for now:
	    String currName = "dummy";
	    
	    try {
		allEmployees.add(new Employee(currName,args[i]));
		nEmployees++;
	    }
	    catch (FileNotFoundException FNFE) {
		System.out.printf("Error: could not find employee %s's file.");
	    }
	}
	
	/**
	 * At this point, the initial employee and employer files have been 
	 * provided. Prompt the user for what to do next. Here are some options:
	 *
	 * addEmployee employeeName
	 *    Allows the user to add a new employee to the program
	 *
	 * addToSchedule timeSlot
	 *    Allows the user to add a new item to the schedule of one employee
	 *
	 * add employer shift
	 *    Allows the user to add a shift to the employer's schedule
	 *
	 * add toFixedSchedule
	 *    Do a partial re-optimiziation by fitting in any new people or 
	 *    shifts while minimizing the number of changes overall.
	 *
	 * make schedule
	 *    Make a new schedule from scratch.
	 */
	//Prompt user with available options ?
	System.out.println("Options:")
	System.out.println("   add employee")
	System.out.println("   add toSchedule")
	System.out.println("   add shift")
	//System.out.println("   add timeslot")
	System.out.println("   find employee")
	System.out.println("   make schedule")
	System.out.println("   quit")
	
	// Prompt the user for a console input:
	Scanner stdin = new Scanner(System.in);
	boolean done = false;
	while (!done) {
	    System.out.print("Enter option : ");
	    String input = stdin.nextLine();
	    
	    // Only do something if the user enters at least one character
	    if (input.length() > 0) { 
		String[] commands = input.split(" "); //split input on a space for switch clauses
	    }
	    
	    switch (commands[0]) { //outer switch
		
	    case "add":
		
		switch (commands[1]) { //inner switch
		    
		case "employee":
		    
		    //prompt for name
		    System.out.println("Enter Employee Name :   ")
			String inputA = stdin.nextLine();
		    if (inputA.length() > 0) { 
			String newName = inputA;
		    }
		    Employee newEmp = new Employee(); //default no arg constructor
		    newEmp.myName(newName);
		    System.out.println("Enter shift :   ")
			String inputB = stdin.nextLine();
		    String delims = " ";
		    if (input.length() > 0) {  // if a shift is entered
			String[] splitLine = inputB.split(delims);
			boolean isBusyBool = (boolean)(Integer.parseInt(splitLine[2]));
			TimeSlot newSlot = new TimeSlot(Integer.parseInt(splitLine[0]), 
							Integer.parseInt(splitLine[1]),
							isBusyBool,
							Integer.parseInt(splitLine[3])
							);
			newEmp.add(newSlot);
			System.out.println("Timeslot added")
			    allEmployees.add(newEmp);
		    }
		    else {
			allEmployees.add(newEmp); //include only dummy/null timeslot if a hard return selected
			System.out.println("Dummy Timeslot added")
			    }
		    break;
		    
		case "employer":
		    
		    System.out.println("Enter shift :   ")
			String inputB = stdin.nextLine();
		    String delims = " ";
		    if (input.length() > 0) {  // if a shift is entered
			String[] splitLine = inputB.split(delims);
			boolean isBusyBool = (boolean)(Integer.parseInt(splitLine[2]));
			TimeSlot newSlot = new TimeSlot(Integer.parseInt(splitLine[0]), 
							Integer.parseInt(splitLine[1]),
							isBusyBool,
							Integer.parseInt(splitLine[3])
							);
			newEmp.add(newSlot);
			System.out.println("Shift added")
			    theEmployer.add(newEmp);
			break;
			
		    case "toSchedule":
			// searches for name, then adds to mySchedule
			// prompt for name
			System.out.println("Enter Employee Name :   ")
			String inputA = stdin.nextLine();
				if (inputA.length() > 0) { 
				String newName = inputA;
				}
			System.out.println("Enter shift :   ")
			String inputB = stdin.nextLine();
			String delims = " ";
			if (input.length() > 0) {  // if a shift is entered
				String[] splitLine = inputB.split(delims);
				boolean isBusyBool = (boolean)(Integer.parseInt(splitLine[2]));
				TimeSlot newSlot = new TimeSlot(Integer.parseInt(splitLine[0]), 
					Integer.parseInt(splitLine[1]),
					isBusyBool,
					Integer.parseInt(splitLine[3])
					);
			allEmployees.addToSchedule(newName, newSlot); 	//Does this need an allEmployees in front of it?	
			System.out.println("Shift added")	
			break;
			
			} //end inner switch

		break; //back to outer switch
		
	    case "make": 
			switch (command[1]) { //inner switch
			case "schedule": 
			//TODO
			break;
			}
		break;
		
		case "find":
			switch (command[1]) {
			case "employee": 
			//prompt for name
			System.out.println("Enter Employee Name :   ")
			String inputA = stdin.nextLine();
				if (inputA.length() > 0) { 
				String newName = inputA;
				}
			testName(newName); //this is test for whether employee exists, returns T/F
			//
			}
			break; //end inner switch, back to outer switch
			
		break;
		
	    case "quit":
		done = true;
		System.out.println("Exiting Scheduler");
		// does this need a hard exit command built in? or, does ending the While loop end the console?
		break;
		
	    default: //An improper command is invalid
		System.out.println("Invalid command");
		break;
	    }
	    
		}
	
	
		// HELPER METHODS
	
	 /**
     * Set up the iterator to traverse the allEmployees LinkedList
     * @returns: LinkedListIterator object
     */
    public Iterator<Employee> iterator() {
	return allEmployees.iterator();
    }
	
	
	/**
	 * A method to check for Employees name within Employee list
	 * I assume getName() will work here 
	 * @returns: true (if employee exists in list) or false
     */
	public testName(String testName) 
	{
	Iterator<Employee> findIter = this.iterator(); //i.e. iterator defined below
	
	while (findIter.hasNext()) {
		if (findIter.next().getName().equals(testName)) {
		return true;
		}
	// if fails to find testName, return false 
	return false;
	}

	/**
	 * A method to add shift to schedule of one employee
	 * I assume getName() will work here 
     */
	public addToSchedule(String findName, TimeSlot testSlot) 
	{
	Iterator<Employee> findIter = this.iterator(); //i.e. iterator defined below
	
	while (findIter.hasNext()) {
	    Employee newEmployee = findIter.next();
	    if (newEmployee.getName().equals(findName)) {
		newEmployee.add(testSlot);
		//mySchedule.add(testSlot);
		//mySortedSchedule.add(testSlot); // this seems redundant - doesn't add() do both of these - must be adding to wrong object
		}
	
	}
	
	
	/**
	* A method to add Employer shift
    */
	public addShift(TimeSlot testSlot)
	{
	theEmployer.add(timeSlot);
	}
	
	/**
	* A method to perform a partial re-optimization by fitting in new people while minimizing # of changes to existing schedule
    */
	public addToFixedSchedule()
	{
	//TODO
	}
	
	/**
	* A method make a new schedule from scratch
    */
	public make() 
	//Make a new schedule from scratch.
	//this needs to involve optimizer 
	{
	//TODO
	}
	
	
	
}	
