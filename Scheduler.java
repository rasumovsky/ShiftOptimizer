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
	 * add to schedule
	 *    Do a partial re-optimiziation by fitting in any new people or 
	 *    shifts while minimizing the number of changes overall.
	 *
	 * make schedule
	 *    Make a new schedule from scratch.
	 */
	
	// Prompt the user for a console input:
	Scanner stdin = new Scanner(System.in);
	boolean done = false;
	while (!done) {
	    System.out.print("Enter option : ");
	    String input = stdin.nextLine();
	    
	    // Only do something if the user enters at least one character
	    if (input.length() > 0) { 
		String[] commands = input.split(" ");
	    }
	    
	    switch () {
		
	    case "add":
		// here we will need to add more switch-case clauses
		break;
		
	    case "make":
		break;
		
	    case "quit":
		done = true;
		System.out.println("Exiting Scheduler");
		break;
		
	    default: //An improper command is invalid
		System.out.println("Invalid command");
		break;
	    }
	    
	}
